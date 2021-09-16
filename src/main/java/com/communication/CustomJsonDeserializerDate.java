package com.communication;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import com.communication.exceptionHandler.CreateMessageExeception;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class CustomJsonDeserializerDate extends JsonDeserializer<String>{

    @Override
    public String deserialize(JsonParser jsonParser,
            DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        if(!isValid(date)){
            throw CreateMessageExeception.createObjectMessageBadRequestExeception("messagem.dateformat", "date");
        }else{
            return date;
        }
    }

    public boolean isValid(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-d HH:mm");
        boolean valid = false;
        try {
            sdf.parse(date);
            sdf.setLenient(false);
            valid = true;
        } catch (ParseException e) {
            e.printStackTrace();
            valid = false;
        }
        return valid;
    }

}

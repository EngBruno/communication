# communication

O projeto esta utilizando as seguintes tecnológias :

* SpringBoot
* Postgres
* Jacoco 
* TestContainer
* Swagger

Para inicar o projeto basta seguir os seguintes passo:

1. Abra o terminal e entre na raiz do projeto. `cd communication/`
2. Você devera rodar o comando para gerar o .jar. `mvn clean package`
3. Posteriormente baster rodar o comando do docker-compose. `docker-compose up --build`
4. Para realizar os testes manuais va para a pagina do swagger. `http://localhost:8080/swagger-ui.html#`

Estou utilizando o Jacoco para verificar a cobertuda dos testes, rode o comando dentro da pasta do projeto `mvn clean package` para vizualizar o dashboard  posteriormente abra o index.html(communication/target/site/index.html) em um browser de sua preferencia, o minimo de aceitação esta de 50%,porém os testes atuais estão em 75%:

![Captura de Tela 2021-09-16 às 15 50 08](https://user-images.githubusercontent.com/6809575/133668590-4dc889fe-42de-4b5c-90ae-0ee2e92eaa63.png)


Os testes estao rodando com o (testcontainer) https://www.testcontainers.org/ ,  para executar os testes é necessario tem o docker instalado na maquina.

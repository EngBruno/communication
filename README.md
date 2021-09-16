# communication

O projeto esta utilizando as seguintes tecnológias :

* SpringBoot
* Postgres
* Jacoco 
* TestContainer
* Swagger
* flyway

Para inicar o projeto basta seguir os seguintes passo:

1. Pare o banco(Postgres) local da sua maquina, caso vc tenha alguma rodando .
2. Abra o terminal e entre na raiz do projeto. `cd communication/`
3. Rode o comando para gerar o .jar. `mvn clean package`
4. Posteriormente baster rodar o comando do docker-compose. `docker-compose up --build`
5. Para realizar os testes manuais va para a pagina do swagger. `http://localhost:8080/swagger-ui.html#`

Estou utilizando o Jacoco para verificar a cobertuda dos testes, rode o comando dentro da pasta do projeto `mvn clean package` para vizualizar o dashboard, em seguida abra o index.html(communication/target/site/index.html) em um browser de sua preferencia, o minimo de aceitação esta de 50%,porém os testes atuais estão em 75%:

![Captura de Tela 2021-09-16 às 15 50 08](https://user-images.githubusercontent.com/6809575/133668590-4dc889fe-42de-4b5c-90ae-0ee2e92eaa63.png)


Os testes estao rodando com o (testcontainer) https://www.testcontainers.org/ ,  para executar os testes é necessario tem o docker instalado na maquina.


Caso queria rodar o projeto manualmente bastar : 

1. `docker run --name postgresql-container -p 5432:5432 -e POSTGRES_PASSWORD=postgres -e POSTGRES_USER=postgres -e POSTGRES_DB=communication -d postgres`
2. Startar  o projeto em sua IDE de preferencia

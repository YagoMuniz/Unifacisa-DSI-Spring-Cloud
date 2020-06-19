![HEROKU DEPLOYMENT](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/workflows/HEROKU%20DEPLOYMENT/badge.svg?branch=master&event=push)

# Unifacisa-DSI-Spring-Cloud

Configuração de arquitetura microservice, utilizando Eureka e Spring Cloud.
![SpringClound](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/eureka.jpeg)

### Eureka serve

Projeto que registra os microserviços e monitora o status de cada. Aqui sendo representado pelo serviço ServiceDiscovery.

### Config server

Serviço de configuração dos outros serviços. Tem o objetivo de buscar as configurações de cada serviço no github::
e aplicá-los em seus respectivos lugares.

- Configurações dos projetos:
  - http://localhost:9080/servicediscovery/default
  - http://localhost:9080/apigateway/default
  - http://localhost:9080/accounts/default
  - http://localhost:9080/inventories/default
  - http://localhost:9080/orders/default
  - http://localhost:9080/recommendations/default
  - http://localhost:9080/shippings/default

#### Zuul

Projeto de proxy para os serviços rest do projeto em questão. Representado aqui pelo projeto apigateway.

### Microservices projects

Microservices que retonam um json como resposta.

- https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/accounts
- https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/inventories
- https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/orders
- https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/recommendations
- https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/shippings

### Executando

Para executar o projeto, é necessário subir alguns serviços antes. <br />
Seguindo nessa ordem:<br />
1 - ConfigServer (https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/configserver).<br />
2 - ServiceDiscovery (https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/servicediscovery).<br />
3 - APIGateway (https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master/apigateway).<br />
4 - Qualquer outro serviço.<br />

Pra finalizar, é só chamar cada serviço na porta 9070 (Porta em roda o serviço Zuul)<br />
Exemplo: localhost:9070/accounts/

### Pipeline CI/CD

Utilizamos o Github Actions para implementar um pipeline de Integração Contínua e Entrega Contínua.
(https://help.github.com/pt/actions)

Utilizamos o Heroku para fazer deploy de todos os serviços
(https://www.heroku.com/)

#### Pipeline CI/CD

![Github Actions](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/actions-pipeline.jpeg)

Cada push na branch master dispara uma cadeia de eventos na seguinte ordem:

#### 1 - Build de todos os serviços

```yml
- name: Criando build do Eureka Server
  run: mvn -B package --file configserver/pom.xml -DskipTests && mkdir builds && cp configserver/target/*.jar builds
- name: Criando build do Service Discovery Server
  run: mvn -B package --file servicediscovery/pom.xml -DskipTests && cp servicediscovery/target/*.jar builds
- name: Criando build do API Gateway Server
  run: mvn -B package --file apigateway/pom.xml -DskipTests && cp apigateway/target/*.jar builds
- name: Criando build do Account Service
  run: mvn -B package --file accounts/pom.xml -DskipTests && cp accounts/target/*.jar builds
- name: Criando build do Inventories Service
  run: mvn -B package --file inventories/pom.xml -DskipTests && cp inventories/target/*.jar builds
- name: Criando build do Orders Service
  run: mvn -B package --file orders/pom.xml -DskipTests && cp orders/target/*.jar builds
- name: Criando build do Recommendations Service
  run: mvn -B package --file recommendations/pom.xml -DskipTests && cp recommendations/target/*.jar builds
- name: Criando build do Shippings Service
  run: mvn -B package --file shippings/pom.xml -DskipTests && cp shippings/target/*.jar builds
```

#### 2 - Upload local dos arquivos .jar

```yml
- name: Upload math result for job
  uses: actions/upload-artifact@v1
  with:
    name: Fazendo upload dos arquivos de build
    path: builds
```

#### 3 - Deploy de todos os serviços na ordem correta para o Heroku

```yml
- name: Deploy do Config Server
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-configserver
  run: heroku plugins:install java && heroku deploy:jar builds/configserver-0.0.1-SNAPSHOT.jar --app dsi-spring-configserver
- name: Deploy do Service Discovery Server
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-servicediscovery
  run: heroku deploy:jar builds/servicediscovery-0.0.1-SNAPSHOT.jar --app dsi-spring-servicediscovery
- name: Deploy do API Gateway Server
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-apigateway
  run: heroku deploy:jar builds/apigateway-0.0.1-SNAPSHOT.jar --app dsi-spring-apigateway
- name: Deploy do Account Service
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-accounts
  run: heroku deploy:jar builds/accounts-0.0.1-SNAPSHOT.jar --app dsi-spring-accounts
- name: Deploy do Recommendations Service
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-recommendations
  run: heroku deploy:jar builds/recommendations-0.0.1-SNAPSHOT.jar --app dsi-spring-recommendations
- name: Deploy do Orders Service
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-orders
  run: heroku deploy:jar builds/orders-0.0.1-SNAPSHOT.jar --app dsi-spring-orders
- name: Deploy do Inventories Service
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-inventories
  run: heroku deploy:jar builds/inventories-0.0.1-SNAPSHOT.jar --app dsi-spring-inventories
- name: Deploy do Shippings Service
  env:
    HEROKU_API_KEY: ${{ secrets.HEROKU_API_KEY }}
    HEROKU_APP_NAME: dsi-spring-shippings
  run: heroku deploy:jar builds/shippings-0.0.1-SNAPSHOT.jar --app dsi-spring-shippings
```

#### Serviços no Heroku

Ao final do pipeline, todos os serviços estão implantados no Heroku, utilizamos duas contas, já que a conta free do Heroku permite apenas o deploy de 5 serviços.
![Servicos Heroku](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/services1.jpg)
![Servicos Heroku (Segunda Conta)](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/services2.jpeg)

#### Eureka Server

Instância do Eureka com todos os outros serviços devidamente registrados.
![Instância do Eureka Service](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/eureka2.jpeg)

### API Gateway Endpoints

O Api Gateway é a porta de entrada para todos os outros serviços, através dele podemos interagir com todos os outros em um único endereço, assim não precisamos saber os endereços de cada serviço especificamente, desde que ela esteja registrada no Zuul.

    https://dsi-spring-apigateway.herokuapp.com
    /accounts
    /orders
    /shippings
    /recommendations
    /inventories

### Serviços e Endereços

| Serviço           | URL                                                |
| ----------------- | -------------------------------------------------- |
| CloudConfig       | https://dsi-spring-configservice.herokuapp.com/    |
| Service Discovery | https://dsi-spring-servicediscovery.herokuapp.com/ |
| API Gateway       | https://dsi-spring-apigateway.herokuapp.com/       |
| Accounts          | https://dsi-spring-accounts.herokuapp.com/         |
| Orders            | https://dsi-spring-orders.herokuapp.com/           |
| Recommendations   | https://dsi-spring-recommendations.herokuapp.com/  |
| Shippings         | https://dsi-spring-shippings.herokuapp.com/        |
| Inventories       | https://dsi-spring-inventories.herokuapp.com/      |

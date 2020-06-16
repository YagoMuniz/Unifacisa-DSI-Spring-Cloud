# Unifacisa-DSI-Spring-Cloud

Configuração de arquitetura microservice, utilizando Eureka e Spring Cloud.
![SpringClound](https://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/blob/master/images/eureka.jpeg)
### Eureka servehttps://github.com/YagoMuniz/Unifacisa-DSI-Spring-Cloud/tree/master

Projeto que registra os microserviços e monitora o status de cada. Aqui sendo representado pelo serviço ServiceDiscovery.

### Config server

Serviço de configuração dos outros serviços. Tem o objetivo de buscar as configurações de cada serviço no github:: 
e aplicá-los em seus respectivos lugares. 

* configurações dos projetos:
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




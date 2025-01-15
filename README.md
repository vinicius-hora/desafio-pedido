# desafio-pedido

Desenvolvimento do desafio btg
## Link do desafio
[clique aqui](https://github.com/buildrun-tech/buildrun-desafio-backend-btg-pactual/blob/main/problem.md)

## Links do Projeto original

- [Documentação] - [clique aqui](http://144.91.91.185:4006/documentation)

## Descrição
### Cadastro de Pedidos via Requisição HTTP:

O sistema permite o cadastro de pedidos através de requisições HTTP, geralmente via uma API REST.
As informações do pedido são validadas e, em seguida, persistidas no banco de dados.

### Cadastro de Pedidos via Mensageria (RabbitMQ):

O sistema também pode receber pedidos por meio de mensagens enviadas para filas no RabbitMQ.
As mensagens são processadas de maneira assíncrona, permitindo a inserção dos pedidos de forma escalável e desacoplada.

### Consulta de Pedidos Cadastrados:

O sistema disponibiliza uma API para consultar os pedidos registrados, permitindo buscar informações com base em parâmetros como código do pedido, status e cliente.
As consultas podem ser feitas de forma síncrona através da API, retornando os dados dos pedidos em formato JSON.

## Documentação do Projeto

### Tecnologias usadas no projeto

- Postgres
- H2
- RabbitMq
- spring
- Flyway
- Swagger
- Actuator

### Instruções para Execução

Clone o repositório:
```bash
$ git clone https://github.com/vinicius-hora/desafio-pedido.git
```

### Execução Local:

Executar através da IDE.

### Execução via docker-compose:

```bash
$ docker-compose up --build -d
Obs: Isso realizará o build da imagem e executará o projeto.
```
Os scripts sql para criação das tabelas estão no diretório resources/db/migrations

### Variáveis de Ambiente

PORT: porta que será iniciado o projeto

ACTIVE_PROFILE:
test - Banco de dados em memória,
prod - deve passar os parametros do banco de dados

DB_URL: postgres (usuário do banco)

DB_USERNAME: postgres (senha do banco)

DB_PASSWORD: devbook (nome do banco)

EXPOSURE_INCLUDE: Endpoints da bibliotec actuator que deseja liberar

GENERATE_DDL:

ENABLE_SQL:

OPEN_IN_VIEW:

Obs: caso possua RabbitMq instalado e queira usar
RABBIT_HOST:
RABBIT_PORT:
RABBIT_USER:
RABBIT_PASSWORD:

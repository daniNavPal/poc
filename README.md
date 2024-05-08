# POC

My Goal is build a completed example where is can show my learning on the last professionals challengers with the more common tech stack.

 - Build an example of microservice approach with the setup of the more common tech stack tha I worked on the last professional project.
 - Setup of the Relational Database as PostgresSQL and the Document database like MongoDB
 - MonoModule project for each microservice keeping hexagonal approach
   - Javers to manage AuditData
   - Liquibase to manage database schema definition as code
 - Event Driven (Kafka)
   - Spring Apache Kafka - Policy Microservice
   - Spring Cloud Stream - Customer Microservice
 - Observability setup with Grafana & Tempo & Loki
 - SpringCache with Caffeine
 - Redis
 - API definition first Swagger
 - Testings
   - UT
   - IT + Containers
 - DockerFile and DockerCompose



```

### Project specifics

- Maven wrapper is used by Dockerfile

### Maven build

```shell
./mvnw --settings settings.xml clean package
```

Produces a spring boot application located at `main/target/main.jar`

### Docker Image build

Compile and then build the image

```shell
# compile
./mvnw --settings settings.xml clean package
# build image
docker build . --build-arg JAR_FILE=main/target/main.jar -t manufacturer-server-ms-policy-core
```

## :running_man: Run locally

### Environment

List of environment variables that may be available during runtime

| Variable                       | default                                                                  | Description                                                              |
|--------------------------------|--------------------------------------------------------------------------|--------------------------------------------------------------------------|
| APP_SERVER_PORT                | 8080                                                                     | server port                                                              |
| APP_ACTIVE_PROFILE             | -                                                                        | Possible values: "" or "logstash" or "fluentd"                           |
| APP_OAUTH_SERVER               | https://id-dev.wefox.com/auth/realms/wefox                               | -                                                                        |
| APP_OAUTH_SERVER_CERTS         | https://id-dev.wefox.com/auth/realms/wefox/protocol/openid-connect/certs | -                                                                        |
| APP_OAUTH_SCOPE                | manufacturer-server-ms-policy-core                                       | -                                                                        |
| APP_CONF_MARKET_ID             | de                                                                       | Id of the market (country) for apps with multiple instances              |
| APP_MONGODB_CLUSTER            | localhost                                                                | Database cluster                                                         |
| APP_MONGODB_PORT               | 11008                                                                    | Database port                                                            |
| APP_MONGODB_DB                 | afr                                                                      | Database name                                                            |
| APP_MONGODB_USER               | user                                                                     | Database user                                                            |
| APP_MONGODB_PASSWORD           | password                                                                 | Database user password                                                   |
| APP_MONGODB_REPLICA_SET_CONFIG | -                                                                        | Configuration for the database replica set                               |
| APP_KAFKA_BROKERS              | localhost:9092                                                           | -                                                                        |
| APP_KAFKA_REPLICATION_FACTOR   | 1                                                                        | should be increased on production                                        |
| APP_KAFKA_SCHEMA_REGISTRY_URL  | localhost:8081                                                           | -                                                                        |
| APP_KAFKA_SECURITY_PROTOCOL    | -                                                                        | For secure communication use: SSL or SASL_SSL                            |
| APP_KAFKA_USER                 | alice                                                                    | set when APP_KAFKA_SECURITY_PROTOCOL = SASL_SSL                          |
| APP_KAFKA_PASSWORD             | alice                                                                    | set when APP_KAFKA_SECURITY_PROTOCOL = SASL_SSL                          |
| APP_KAFKA_JAAS_ENABLED         | false                                                                    | set when APP_KAFKA_SECURITY_PROTOCOL = SASL_SSL                          |
| APP_KAFKA_SASL                 | SCRAM-SHA-512                                                            | default value should be fine when APP_KAFKA_SECURITY_PROTOCOL = SASL_SSL |
| APP_KAFKA_TRUSTSTORE           | -                                                                        | For secure communication use: /application/kafka.client.truststore.jks   |
| APP_TOPIC_POLICY_EVENT         | topic.data-manufacturer-policy                                           | Policy data event topic => "data.manufacturer.policy"                    |

List of environment variables needed to run the post-deployment module -> e2e

| Variable                        | default                                                                  | Description                                   |
|---------------------------------|--------------------------------------------------------------------------|-----------------------------------------------|
| QA_E2E_URL                      | http://localhost:8080                                                    | Target url for the e2e tests.                 |
| QA_API_DOC_PATH                 | http://localhost:8080/api-docs                                           | Open API url in the target ms.                |
| QA_API_SPEC_VALIDATION_LOCATION | src/test/resources/                                                      | file name to compare                          |
| APP_OAUTH_SERVER                | https://id-dev.wefox.com/auth/realms/wefox/protocol/openid-connect/token | -                                             |
| APP_OAUTH_CLIENT_ID             | afr-test-client                                                          | client-id used in the client credentials flow |
| APP_OAUTH_CLIENT_SECRET         | 490fbdcc-954d-4988-8331-a8a5dc38eefa                                     | secret                                        |
| APP_KAFKA_TRUSTSTORE            | -                                                                        | Truststore to run the post-deployment module  |

### Docker compose

1. `./mvnw -s settings.xml clean package`
2. . Intel x64 Processors
1. `docker-compose -f docker-compose.yml build`
2. `docker-compose -f docker-compose.yml up -d`
3. Apple M1 ARM Processors
1. `docker-compose -f docker-compose.yml -f docker-compose-arm.yml build`
2. `docker-compose -f docker-compose.yml -f docker-compose-arm.yml up -d`
4. Navigate to OpenApi definition http://localhost:8101/api-docs/
5. OpenAPI YAML file http://localhost:8101/api-docs.yaml
6. Swagger
   specification http://localhost:8101/swagger-ui/index.html?configUrl=/api-docs/swagger-config
7. Stop local environment `docker-compose down`
8. Delete all containers `docker rm -f $(docker ps -a -q)`
9. Delete all volumes (Optional) `docker volume rm $(docker volume ls -q)`

Test

```shell

curl --request GET -vsL \
     --url 'http://localhost:8101/health'
```

### Maven

1. `docker-compose -f docker-compose.yml up -d mongodb` -> Start the database locally
2. `docker-compose -f docker-compose.yml up -d zookeeper kafka schema-registry` -> Start kafka
   locally
3. `./mvnw clean install`
4. `./mvnw -pl main spring-boot:run`

Test

```shell

curl --request GET -vsL \
     --url 'http://localhost:8080/health'
```

## Kafka CLI [here](doc/KAFKA-CLI.md)

## Kafka Commands [here](doc/KAFKA-Commands.md)

## :white_check_mark: Testing options

### Launch Unit & Contract Tests

```shell
./mvnw clean test
```

### Launch Integration Tests

```shell
./mvnw clean verify
```

### E2E Test

By default, local environment will be tested. to change this behaviour you should set the
environment variables (
check [here](post-deployment/README.md) for more information).

```shell
./mvnw clean install && ./mvnw -P post-deployment -pl post-deployment -s settings.xml clean verify
```

## :open_book: Published Endpoints

- SwaggerUI: http://localhost:8080/swagger-ui.html
- Health: http://localhost:8080/health
- Info: http://localhost:8080/info

### K8s

In kubernetes, this two endpoints are exposed, ready to setup the liveness & readiness proves.

- http://localhost:8080/health/liveness
- http://localhost:8080/health/readiness

Thiw is a sample K8s configuration:

```yaml

startupProbe:
  httpGet:
    path: /health/readiness
    port: 8080
  initialDelaySeconds: 30
  periodSeconds: 15
  failureThreshold: 30
livenessProbe:
  httpGet:
    path: /health/liveness
    port: 8080
  periodSeconds: 5
  failureThreshold: 1
readinessProbe:
  httpGet:
    path: /health/readiness
    port: 8080
  periodSeconds: 5
  failureThreshold: 1
```

### Garbage Collector Setup

The parameters used to customise the JVM GC performance devops/Dockerfile.build

| Variable                       | default                                                                          | Description                                                                               |
|--------------------------------|----------------------------------------------------------------------------------|-------------------------------------------------------------------------------------------|
| -XX:+UseG1GC                   |                                                                                  | Garbage First Garbage Collector                                                           |
| -XX:InitialHeapSize            | 128m                                                                             | Initial heap size                                                                         |
| -XX:MaxHeapSize                | 512m                                                                             | Maximum heap size                                                                         |
| -XX:G1HeapRegionSize           | 4096k                                                                            | Sets the size of a G1 region                                                              |
| -XX:MaxGCPauseMillis           | 500                                                                              | Sets a target value for desired maximum pause time                                        |
| -XX:+UseStringDeduplication    |                                                                                  |                                                                                           |
| -XX:XX:+ParallelRefProcEnabled |                                                                                  |                                                                                           |
| -XX:ParallelGCThreads          | 6                                                                                |                                                                                           |
| -Xlog                          | Xlog:gc*=debug:file=/tmp/gc.log:time,uptime,level,tags:filecount=2,filesize=100m | [link](http://www.herongyang.com/Java-GC/Logging-Garbage-Collection-Logging-Options.html) |
| -XX:MaxTenuringThreshold       | 2                                                                                |                                                                                           |

[Useful documentation](https://www.oracle.com/technical-resources/articles/java/g1gc.html)

## :memo: Useful links

### Build Docker Image

* Spring Boot Maven
  Plugin [link](https://docs.spring.io/spring-boot/docs/current/maven-plugin/reference/htmlsingle/)
* Spring Boot Build Docker
  Images: [link](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#boot-features-container-images-docker)

TODO:

* fix Tenant as UUID and get from Context or Header
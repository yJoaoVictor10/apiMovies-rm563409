# cp3 - Cloud & DevOps

## 

### CRIANDO REDE 
docker network create rede-rm563409

docker volume create h2-volume-563409

docker run -d \
--name h2-db-rm563409 \
--network rede-rm563409 \
-v h2-volume-rm563409:/opt/h2-data \
-p 8082:8082 \
-p 9092:9092 \
oscarfonts/h2 \


spring.application.name=movies

spring.datasource.url=jdbc:h2:tcp://h2-db-rm563409:9092/~/test
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

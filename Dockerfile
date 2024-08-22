# Usar uma imagem Maven como base para compilar o código
FROM maven:3.8.5-openjdk-17-slim AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar os arquivos do projeto para dentro do container
COPY pom.xml .
COPY src ./src

# Executar o processo de build do Maven para criar o JAR
RUN mvn clean package -DskipTests

# Usar uma imagem JDK mais enxuta para rodar a aplicação
FROM openjdk:17-jdk-alpine

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o JAR gerado na etapa de build para dentro da nova imagem
COPY --from=build /app/target/*.jar app.jar

# Informar o comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

# Expor a porta que o Spring Boot utiliza
EXPOSE 5000

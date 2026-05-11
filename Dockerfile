# Estágio de Build
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app

# Copia os arquivos do Maven Wrapper e o pom.xml
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./

# Dá permissão de execução ao mvnw e corrige fins de linha
RUN tr -d '\r' < mvnw > mvnw.tmp && mv mvnw.tmp mvnw
RUN chmod +x mvnw

# Baixa as dependências (camada de cache)
RUN ./mvnw dependency:go-offline

# Copia o código fonte e gera o jar
COPY src ./src
RUN ./mvnw package -DskipTests

# Estágio de Execução
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/app.jar app.jar

# Expõe a porta (Render usa $PORT, mas 8080 é o padrão)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]

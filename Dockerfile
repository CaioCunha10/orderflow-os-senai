# Estágio de Build
FROM eclipse-temurin:17-jdk-focal AS build
WORKDIR /app

# Instala o Maven diretamente no container para evitar problemas com o wrapper
RUN apt-get update && \
    apt-get install -y maven && \
    rm -rf /var/lib/apt/lists/*

# Copia o pom.xml e baixa as dependências (camada de cache)
COPY pom.xml ./
RUN mvn dependency:go-offline

# Copia o código fonte e gera o jar
COPY src ./src
RUN mvn package -DskipTests

# Estágio de Execução
FROM eclipse-temurin:17-jre-focal
WORKDIR /app

# Copia o jar gerado no estágio anterior
COPY --from=build /app/target/app.jar app.jar

# Define a porta dinâmica (Render injeta $PORT, mas 8080 é o padrão local)
EXPOSE 8080

# Comando de inicialização
ENTRYPOINT ["java", "-jar", "app.jar"]

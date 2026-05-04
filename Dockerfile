FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY Wallet_BDDFS01 /Wallet_BDDFS01
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
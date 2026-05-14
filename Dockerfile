FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# For local testing, do not copy a real Oracle Cloud wallet here.
# If a wallet is required in production, add it via a build argument or external volume mount.
COPY target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
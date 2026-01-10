# Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos todo el proyecto
COPY . .

# Damos permisos al wrapper de maven
RUN chmod +x mvnw

# Construimos el proyecto
RUN ./mvnw clean package -DskipTests

# Puerto que usa Spring Boot
EXPOSE 8080

# Comando para iniciar la app
CMD ["java", "-jar", "target/backendfull-0.0.1-SNAPSHOT.jar"]

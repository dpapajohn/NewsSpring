FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /source
ADD . /source
RUN mvn clean package -DskipTests


FROM maven:3.9.6-eclipse-temurin-21
COPY --from=build /source/target/NewsSpring-0.0.1-SNAPSHOT.jar NewsSpring.jar
EXPOSE 80
RUN chmod +x NewsSpring.jar
ENTRYPOINT ["java","-jar","NewsSpring.jar"]

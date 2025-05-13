FROM openjdk:21-jdk-slim

COPY ./out/artifacts/ServerM_jar/ServerM.jar /app/ServerM.jar
COPY ./Comune-di-Roma-Capitale---Elenco-delle-strutture-ricettive.csv /app/Comune-di-Roma-Capitale---Elenco-delle-strutture-ricettive.csv

WORKDIR /app

EXPOSE 1050

CMD ["java", "-jar" , "ServerM.jar"]

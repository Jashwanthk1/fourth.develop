FROM openjdk:17
EXPOSE 8083

ADD  target/fourt-develop.jar fourt-develop.jar
ENTRYPOINT ["java","-jar","/fourt-develop.jar"]

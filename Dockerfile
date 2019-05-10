FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/travel-diary-2.0.jar app.jar
ENV DIARY_PASS_VAR=
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

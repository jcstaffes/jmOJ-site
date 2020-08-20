FROM openjdk:latest

RUN mkdir/app

ADD src/main/java ./app/

WORKDIR /app

ENV MYSQL_ROOT_PASSWORD 123456

CMD ["/app/main"]
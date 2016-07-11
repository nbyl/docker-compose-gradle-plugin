FROM java:8

MAINTAINER Nicolas Byl <nicolas.byl@codecentric.de>

ADD build/libs/book-store.jar app.jar

RUN bash -c 'touch /app.jar'

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
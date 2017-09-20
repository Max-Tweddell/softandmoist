FROM java:8-alpine
MAINTAINER Your Name <you@example.com>

ADD target/uberjar/bug.jar /bug/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/bug/app.jar"]

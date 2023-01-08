FROM openjdk:8-jdk
VOLUME /tmp
RUN echo "Asia/shanghai" > /etc/timezone
ADD eladmin-system-1.0.jar eladmin-system-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/eladmin-system-1.0.jar"]

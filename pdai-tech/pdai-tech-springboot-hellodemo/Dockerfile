FROM amazoncorretto:17
MAINTAINER pdai-tech
WORKDIR /
ADD target/pdai-tech-springboot-hellodemo.jar app.jar
EXPOSE 8888
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8"]
CMD ["app.jar"]
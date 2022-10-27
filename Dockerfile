FROM openjdk:17-alpine

COPY out.jar /usr/local/bin/out.jar
RUN dd if=/dev/urandom count=21 bs=1 2>/dev/null | base64 > /usr/local/flag

EXPOSE 8080

CMD ["java", "-jar", "/usr/local/bin/out.jar"]


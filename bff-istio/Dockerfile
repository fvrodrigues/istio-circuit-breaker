FROM adoptopenjdk/openjdk11-openj9:alpine-jre
EXPOSE 8080 8081
ENV JAVA_OPTS=-Xmx=128mb -Xms=32mb -XX:MaxNewSize=32mb -XX:MaxPermSize=64mb -Xscmx=50m
CMD java $JAVA_OPTS -Xshareclasses -jar deployment/app.jar

COPY target/*.jar deployment/app.jar
RUN /bin/sh -c 'java -Xshareclasses -Xscmx50M -jar deployment/app.jar &' ; sleep 10 ; pkill -9 -f app
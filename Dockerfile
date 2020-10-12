FROM alpine/git:latest AS clone

ARG dir=clone-folder
ARG hostname=github.com
ARG project=samplebot
ARG username=pavi93
ARG projname = robot

WORKDIR /$dir
RUN git clone https://$hostname/$username/$project

###

FROM maven:alpine AS build

ARG dir_old=clone-folder
ARG dir=build-folder
ARG project=spring-petclinic

WORKDIR /$dir
COPY --from=clone /$dir_old/$project . 
RUN mvn install && mv target/$projname-*.jar target/$projname.jar

###

FROM openjdk:jre-alpine AS production

ARG dir_old=build-folder/target
ARG dir=production-folder
ARG project=spring-petclinic.jar

WORKDIR /$dir
COPY --from=build /$dir_old/$project . 

ENTRYPOINT ["java","-jar"]
CMD ["robot.jar"]

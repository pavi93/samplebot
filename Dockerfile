WORKDIR /$dir
RUN git clone https://$hostname/$username/$project

###

FROM maven:alpine AS build

ARG dir_old=clone-folder
ARG dir=build-folder
ARG project=samplebot
ARG projname=robot

WORKDIR /$dir
COPY --from=clone /$dir_old/$project/$projname .
RUN mvn install && mv target/$projname-*.jar target/$projname.jar

###

FROM openjdk:jre-alpine AS production

ARG dir_old=build-folder/target
ARG dir=production-folder
ARG project=robot.jar

WORKDIR /$dir
COPY --from=build /$dir_old/$project .

ENTRYPOINT ["java","-jar"]
CMD ["robot.jar"]

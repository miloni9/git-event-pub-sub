ARG  CODE_VERSION=0.0.1
FROM openjdk:8
COPY ./out/ /app
COPY ./conf /app/conf
WORKDIR /app
ENV TZ=Asia/Kolkata
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN echo $CODE_VERSION > image_version



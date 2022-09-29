FROM openjdk:11.0.11-jre-slim

MAINTAINER mintonzhang@163.com

ENV TZ=Asia/Shanghai

#将这个名字改为具体的项目名
ENV SERVER_NAME=arthas-tunnel-server
ENV USERNAME=ywja
ENV USER_PASSWORD=ywja666
ENV WS_PORT=7777
ENV SERVER_PORT=8080
ENV HEAP_SIZE=256

RUN echo -e "${TZ}" > /etc/timezone && ln -sf /usr/share/zoneinfo/${TZ} /etc/localtime

RUN mkdir -p /tunnel-server/config/null

WORKDIR /tunnel-server

VOLUME /tunnel-server

COPY ./target/${SERVER_NAME}.jar ./

CMD sleep 5;java -Dfile.encoding=utf-8 \
 -server \
 -Xmx${HEAP_SIZE}m \
 -Xms${HEAP_SIZE}m \
 -jar ${SERVER_NAME}.jar \
 --server.port=${SERVER_PORT} \
 --arthas.server.port=${WS_PORT}\
 --login.username=${USERNAME} \
 --login.password=${USER_PASSWORD} \

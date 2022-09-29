## 新增优化项

1. 加入basic身份验证，配置方式在properties.yml中
2. 首页部分中文调整
3. 引入lombok,最低支持版本为jdk8
4. 新增dockerfile,可以在idea中一键启动
5. 移除redis自动配置项,默认使用guava cache缓存数据
6. 已上传至阿里云私服的镜像仓库,可直接使用docker-compose运行

## 修改登录密码

```yaml
version: '3.3'
services:
  arthas-tunnel-server:
    image: registry.cn-hangzhou.aliyuncs.com/ywja/arthas-tunnel-server:master # 仅支持linux使用
    network_mode: host #网络模式 如果不用host 可能会导致连接不上服务器
    restart: unless-stopped
    environment:
      USERNAME: test #登录账号名
      USER_PASSWORD: test  #登录密码
      WS_PORT: 19998 #tunnel websocket端口
      SERVER_PORT: 19999 # 服务端口
    container_name: 'arthas-tunnel-server-master'
```

## docker启动

```shell
curl -o arthas-tunnel-server.yml  https://raw.githubusercontent.com/mintonzhang/arthas-tunnel-server/master/docker-compose.yml
# 前提安装了docker-compose
docker-compose -f arthas-tunnel-server.yml up -d
```
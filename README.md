## 新增优化项

1. 加入basic身份验证，配置方式在properties.yml中
2. 首页部分中文调整
3. 引入lombok,最低支持版本为jdk8
4. 新增dockerfile,可以在idea中一键启动
5. 移除redis自动配置项,默认使用guava cache缓存数据
6. 已上传至阿里云私服的镜像仓库,可直接使用docker-compose运行

## docker启动

```
docker-compose up -d
```
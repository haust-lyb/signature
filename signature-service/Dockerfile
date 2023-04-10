# 拉取运行环境，这个镜像打包出的镜像比较小，如需要可换成oracle的jre
FROM fabric8/java-alpine-openjdk8-jre
WORKDIR /app
# 从编译好的镜像中将jar拷贝到运行时容器
COPY ./target/signature-service-0.0.1-SNAPSHOT.jar ./springboot.jar
# 容器启动时执行的命令，这里可加jvm参数
ENTRYPOINT ["java","-jar","/app/springboot.jar"]
# 开放端口，根据自己的配置进行开放
EXPOSE 1994

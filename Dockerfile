# Version 1.0.0

FROM --platform=linux/amd64 node:16
WORKDIR /data/server
ADD "./signature-ui" /data/server/


FROM --platform=linux/amd64 kady/jdk8:v1
WORKDIR /data/server
ADD "./signature-service" /data/server/

ENTRYPOINT ls -alh /data/server

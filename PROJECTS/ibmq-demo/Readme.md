# IBM MQ (Docker Setup: mq-setup)
```sh
$ docker ps -aq | xargs docker stop | xargs docker rm
#$ docker pull icr.io/ibm-messaging/mq:latest

$ docker build -t zelle-mq-local .
$ docker volume create mqdata1
$ docker run --env LICENSE=accept --env MQ_QMGR_NAME=EWSQUEUE --volume mqdata1:/mnt/mqm --publish 1414:1414 --publish 9443:9443 --detach --env MQ_APP_PASSWORD=apple@26j --name mqcontainer1 zelle-mq-local

```
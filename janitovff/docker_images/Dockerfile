FROM janitovff/dev_with_gradle_base

RUN apt-get update && apt-get install -y software-properties-common apt-transport-https
RUN apt-key adv --keyserver hkp://p80.pool.sks-keyservers.net:80 --recv-keys 58118E89F3A912897C070ADBF76221572C52609D
RUN apt-add-repository 'deb https://apt.dockerproject.org/repo ubuntu-xenial main'
RUN apt-get update && apt-get install -y docker-engine

VOLUME /project
VOLUME /root/.gradle

CMD bash

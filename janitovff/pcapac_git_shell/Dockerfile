FROM ubuntu:16.04

RUN apt-get update && apt-get install -y git openjdk-8-jre-headless
RUN useradd gituser -d /home/gituser

ADD TerminalProxy.tar /root

CMD /root/TerminalProxy/bin/project

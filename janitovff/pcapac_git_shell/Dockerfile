FROM ubuntu:16.04

RUN apt-get update && apt-get install -y git openjdk-8-jre-headless
RUN useradd gituser -md /home/gituser

ADD TerminalProxy.tar /opt

USER gituser
WORKDIR /home/gituser

CMD /opt/TerminalProxy/bin/project

FROM ubuntu:16.04

RUN apt-get update && apt-get install -y libcurl3

RUN mkdir -p /opt/cgminer

ADD cgminer.tar.gz /opt/cgminer/

CMD /opt/cgminer/cgminer -o stratum+tcp://stratum.bitcoin.cz:3333 -u janitovff.ant -p p --icarus-timing=short --anu-freq 250

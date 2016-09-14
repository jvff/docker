FROM ubuntu:14.04

COPY letsencrypt.sh/letsencrypt.sh /opt/letsencrypt/letsencrypt.sh
COPY letsencrypt/config /opt/letsencrypt/config
COPY letsencrypt/domains.txt /opt/letsencrypt/domains.txt

RUN apt-get update
RUN apt-get install -y openssl curl

VOLUME /var/www/letsencrypt
VOLUME /etc/letsencrypt/live

CMD ["/opt/letsencrypt/letsencrypt.sh", "-c"]

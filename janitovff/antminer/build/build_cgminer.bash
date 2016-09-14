#!/bin/bash -e

apt-get update
apt-get install -y build-essential autoconf automake libtool pkg-config libcurl3-dev libudev-dev

./autogen.sh
CFLAGS="-O2 -Wall -march=native" ./configure --enable-icarus
make -j 8

#!/bin/bash

TEMPDIR="$(mktemp -d)"

cd "$TEMPDIR"
tar -xzvf /home/janito/docker/janitovff/dev_with_gradle_base/gradle_wrapper.tar.gz
sed -i -e "s/-[-0-9.rc]*-bin.zip/-$1-bin.zip/" gradle/wrapper/gradle-wrapper.properties
cat gradle/wrapper/gradle-wrapper.properties
tar -czvf /home/janito/docker/janitovff/dev_with_gradle_base/gradle_wrapper.tar.gz .

cd -
rm -rf "$TEMPDIR"

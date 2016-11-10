FROM janitovff/docker

RUN apt-get update && apt-get install -y wget
RUN wget -P /tmp https://github.com/NVIDIA/nvidia-docker/releases/download/v1.0.0-rc.3/nvidia-docker_1.0.0.rc.3-1_amd64.deb
RUN dpkg -i /tmp/nvidia-docker*.deb && rm /tmp/nvidia-docker*.deb

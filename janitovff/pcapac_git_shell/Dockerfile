FROM ubuntu:16.04

RUN apt-get update && apt-get install -y \
    git \
    bash-completion \
    openjdk-8-jre-headless
RUN useradd gituser -md /home/gituser

ADD TerminalProxy.tar /opt

USER gituser
WORKDIR /home/gituser

ADD custom_prompt_for_git.bash /home/gituser/.bash_prompt

RUN echo 'source ~/.bash_prompt' >> /home/gituser/.bashrc

CMD /opt/TerminalProxy/bin/project

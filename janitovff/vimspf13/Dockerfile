FROM janitovff/vim

RUN apt-get update && apt-get upgrade -y && apt-get install -y curl exuberant-ctags git

RUN curl http://j.mp/spf13-vim3 -L -o - | sh

CMD vim

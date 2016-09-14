FROM janitovff/vim_env

RUN apt-get update && apt-get install -y openjdk-8-jre

RUN echo 'set makeprg=/project/gradlew\ -b\ /project/build.gradle\ assemble' >> /root/.vimrc.local
RUN echo "noremap <Leader>mc :Dispatch /project/gradlew -b /project/build.gradle clean<CR>" >> /root/.vimrc.local
RUN echo "noremap <Leader>mt :Dispatch /project/gradlew -b /project/build.gradle test<CR>" >> /root/.vimrc.local
RUN echo "noremap <Leader>mp :Dispatch /project/gradlew -b /project/build.gradle publish<CR>" >> /root/.vimrc.local

ADD gradle_wrapper.tar.gz /project/
WORKDIR /project/

COPY commit.msg /tmp/commit.msg
RUN git init && git add . && git commit -F /tmp/commit.msg && rm /tmp/commit.msg

RUN mkdir /root/.gradle
RUN echo org.gradle.daemon = true > /root/.gradle/gradle.properties

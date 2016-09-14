FROM janitovff/java_dev_base

COPY build.gradle /project/build.gradle
RUN mkdir /project/app
RUN mkdir /project/conf
RUN mkdir /project/public
RUN mkdir /project/test
RUN mkdir /project/app/assets
RUN mkdir /project/app/controllers
RUN mkdir /project/app/views
RUN mkdir /project/app/models

EXPOSE 9000

RUN echo "noremap <Leader>mr :Dispatch /project/gradlew -b /project/build.gradle runPlayBinary<CR>" >> /root/.vimrc.local

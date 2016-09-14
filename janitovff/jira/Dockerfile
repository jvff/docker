FROM java:8

EXPOSE 8080

ENV JIRA_HOME /var/atlassian/jira
ENV JIRA_INSTALL /opt/atlassian/jira
ENV JIRA_VERSION 7.1.9

COPY atlassian-jira-software-${JIRA_VERSION}-x64.bin /tmp

RUN mkdir -p ${JIRA_HOME}
RUN mkdir -p ${JIRA_INSTALL}

WORKDIR /tmp
RUN echo > installation_options.txt
RUN echo >> installation_options.txt
RUN echo >> installation_options.txt
RUN echo >> installation_options.txt
RUN echo >> installation_options.txt
RUN echo >> installation_options.txt
RUN echo >> installation_options.txt
RUN cat installation_options.txt | ./atlassian-jira-software-${JIRA_VERSION}-x64.bin
RUN rm -rf *

RUN rm ${JIRA_INSTALL}/work/catalina.pid

RUN chown jira:jira -R ${JIRA_HOME}
RUN chown jira:jira -R ${JIRA_INSTALL}

RUN sed -i -e 's|Context path=""|Context path="/jira"|' ${JIRA_INSTALL}/conf/server.xml
RUN sed -i -e 's|Connector port="8080"|& scheme="https" secure="false" proxyName="janitovff.com" proxyPort="8090"|' ${JIRA_INSTALL}/conf/server.xml

COPY janitovff.com.crt /tmp
RUN echo changeit > keytool_options.txt
RUN echo yes >> keytool_options.txt
RUN cat keytool_options.txt | $JAVA_HOME/bin/keytool -import -alias janitovff.com -keystore $JIRA_INSTALL/jre/lib/security/cacerts -file /tmp/janitovff.com.crt
RUN rm -rf *

WORKDIR /opt/atlassian/jira

USER jira:jira

VOLUME /var/atlassian/jira
VOLUME /opt/atlassian/jira/logs

CMD ${JIRA_INSTALL}/bin/start-jira.sh -fg

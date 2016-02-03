FROM tomcat:7

MAINTAINER Golfen Guo "golfen.guo@daocloud.io"

RUN rm -rf $CATALINA_HOME/webapps/*
ADD dbconnect.war $CATALINA_HOME/webapps/ROOT.war

FROM 172.95.66.53:5000/tomcat

COPY target/basic-auto.war $CATALINA_HOME/webapps

EXPOSE 8080 28080

CMD ["catalina.sh", "run"]
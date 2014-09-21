## Sample Java EE 6 with Arquillian for Jboss AS 7.2.0.Final

Created with maven 3.2.1 with archetype: org.jboss.spec.archetypes:jboss-javaee6-webapp


# Setup
    -Change the <jboss.home> into POM.xml
    -Run with: mvn clean test -Parq-jbossas-managed for managed mode
    -Or: mvn clean test -Parq-jbossas-remote after start JBOSS AS 7 by yourself
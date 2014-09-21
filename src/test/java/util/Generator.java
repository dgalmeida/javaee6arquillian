package util;

import org.jboss.shrinkwrap.api.spec.WebArchive;


public interface Generator
{
    String DEPLOYMENT_NAME = "test.war";
    String META_INF_PATH = "META-INF/";
    String WEBAPP_FOLDER = "webapp";
    String POM = "pom.xml";
    String ARCHIVE_TYPE = ".war";
    String RESOURCES_PATH = "src/main/resources";
    String WEBFILES_PATH = "src/main/webapp/WEB-INF";

    public WebArchive createBaseWar(String appTempName, String... pacote);
}

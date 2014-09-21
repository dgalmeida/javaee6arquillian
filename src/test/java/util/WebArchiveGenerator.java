package util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;

import java.io.File;

import static com.google.common.base.Strings.isNullOrEmpty;


public class WebArchiveGenerator
{
    public static WebArchive webArchive;
    public static final String appTempNameDefault = "test.war";

    public static WebArchive createBaseWar(String appTempName,String... pacote)
    {
        webArchive = ShrinkWrap.create(WebArchive.class, configAppName(appTempName))
                .addPackages(true, pacote);

        addDependenciesFromPom();
        addResourcesAndWebFiles("src/main/resources");
        addResourcesAndWebFiles("src/main/webapp/WEB-INF");

        return webArchive;
    }

    private static String configAppName(String appTempName)
    {
        if(isNullOrEmpty(appTempName))
            return appTempNameDefault;
        else
            return appTempName+=".war";
    }

    private static void addDependenciesFromPom()
    {
        webArchive.addAsLibraries(Maven.resolver().loadPomFromFile("pom.xml")
                .importDependencies(ScopeType.RUNTIME, ScopeType.COMPILE)
                .resolve()
                .withTransitivity()
                .asFile()
        );
    }

    private static void addResourcesAndWebFiles(String targetPath)
    {
        File[] files = collectFiles(targetPath);

        for (File file : files)
        {
            if (file.isDirectory())
                addResourcesAndWebFiles(file.getPath());
            else
                addResourceOrWebInf(targetPath, file);

        }
    }

    private static void addResourceOrWebInf(String targetPath, File file)
    {
        if(targetPath.contains("webapp"))
            webArchive.addAsWebInfResource(file);
        else
            webArchive.addAsResource("META-INF/" + file.getName(), "META-INF/" + file.getName());
    }

    private static File[] collectFiles(String targetPath)
    {
        return new File(targetPath).listFiles();
    }
}
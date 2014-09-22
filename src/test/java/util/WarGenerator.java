package util;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.ScopeType;

import java.io.File;

import static com.google.common.base.Strings.isNullOrEmpty;


public class WarGenerator implements WarConfig
{

    private static WebArchive webArchive;

    public static WebArchive createBaseWar(String appTempName, String... pacote)
    {
        webArchive = ShrinkWrap
                .create(WebArchive.class, configAppName(appTempName))
                .addPackages(true, pacote);

        addPomDependencies();
        addFileFrom(RESOURCES_PATH);
        addFileFrom(WEBFILES_PATH);

        return webArchive;
    }

    private static String configAppName(String deploymentName)
    {
        if(isNullOrEmpty(deploymentName))
            return DEPLOYMENT_NAME;
        else
            return deploymentName+= ARCHIVE_TYPE;
    }

    private static void addPomDependencies()
    {
        webArchive.addAsLibraries(
            Maven.resolver()
                    .loadPomFromFile(POM)
                    .importDependencies(ScopeType.RUNTIME, ScopeType.COMPILE)
                    .resolve()
                    .withTransitivity()
                    .asFile()
        );
    }

    private static void addFileFrom(String targetPath)
    {
        File[] files = collectFiles(targetPath);

        for (File file : files)
        {
            if (file.isDirectory())
                addFileFrom(file.getPath());
            else
                addResourceOrWebInf(targetPath, file);
        }
    }

    private static void addResourceOrWebInf(String targetPath, File file)
    {
        if(targetPath.contains(WEBAPP_FOLDER))
            webArchive.addAsWebInfResource(file);
        else
            webArchive.addAsResource(
                    META_INF_PATH + file.getName(),
                    META_INF_PATH + file.getName()
            );
    }

    private static File[] collectFiles(String targetPath)
    {
        return new File(targetPath).listFiles();
    }
}
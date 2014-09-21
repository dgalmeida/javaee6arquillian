package core;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


import javax.inject.Inject;
import org.jboss.arquillian.container.test.api.Deployment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.logging.Logger;
import org.jboss.shrinkwrap.api.Archive;
import util.WebArchiveGenerator;


@RunWith(Arquillian.class)
public class SampleEJBTest
{
    @Deployment
    public static Archive<?> createTestArchive()
    {
        return WebArchiveGenerator.createBaseWar("sampleTest", "core");
    }

    @Inject
    SampleEJB sampleEJB;

    @Inject
    Logger log;

    @Test
    public void testRegister() throws Exception
    {

        assertNotNull(sampleEJB);
        assertTrue(sampleEJB.getMsg().contains("Hello"));

        log.info("Hi! I'm Jboss AS, I ran one arquillian test!");
    }

}

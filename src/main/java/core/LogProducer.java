package core;

import org.jboss.logging.Logger;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


public class LogProducer {

    @Produces
    public Logger createLogger(InjectionPoint ip)
    {
        return Logger.getLogger(
                ip.getMember().getDeclaringClass().getName()
        );
    }
}

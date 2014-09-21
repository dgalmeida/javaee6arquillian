package core;

import javax.ejb.EJB;
import javax.ejb.Stateless;


@Stateless
@EJB(name = "SampleEJB", beanInterface = SampleEJB.class)
public class SampleEJB
{

   private static final String hello = "Hello world, I'm a javaEE 6 webapp with arquillian test";


   public String getMsg() {
      return hello;
   }


}

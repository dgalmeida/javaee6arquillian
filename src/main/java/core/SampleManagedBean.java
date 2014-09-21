package core;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;


@ManagedBean
public class SampleManagedBean
{
    @EJB
    SampleEJB sampleEJB;


    public String getMsgFromEJB(){
        return sampleEJB.getMsg();
    }
}

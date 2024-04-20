package helper;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class TestInitializer {

    private String userName;
    private String passWord;

    private  boolean readProp=false;

    public                          TestInitializer()
    {
        if(!readProp)
        {
            readDefaultproperties();
            readProp=true;
        }
    }

    private void readDefaultproperties()
    {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("src/test/TestData/env.properties");
        Properties props=new Properties();
        try
        {
            props.load(inputStream);
            if(StringUtils.isBlank(be.getUserName()))
            {
                be.setUserName(props.getProperty(userName).trim());
            }
            if(StringUtils.isBlank(be.getPassWord()))
            {
                be.setPassWord(props.getProperty(passWord).trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    envBeam be=new envBeam();


}

package com.infamous;

import com.infamous.logging.Log;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application implements MusicManagerServer {
    
    @Override
    public void start() {
        try {
            new ClassPathXmlApplicationContext(MusicManagerServer.MODULE_PATH);
        } catch (Exception ex) {
            ex.printStackTrace();
            Log.e(ex.getMessage(), ex);
        }
    }
    
    public static void main(String[] args) {
        MusicManagerServer app = new Application();
        app.start();
    }
    
    /*
    private void clientTest()
              throws IOException, MalformedObjectNameException, IntrospectionException, InstanceNotFoundException, ReflectionException {
        Log.d("test");
        JMXServiceURL url = new JMXServiceURL(
                  "service:jmx:rmi://localhost/jndi/rmi://localhost:1099/connector");
        JMXConnector cn = JMXConnectorFactory.connect(url);
        MBeanServerConnection msc = cn.getMBeanServerConnection();
        ObjectName helloObjName = new ObjectName("com.infamous:name=musicManagerService");
        MBeanInfo info = msc.getMBeanInfo(helloObjName);
        
        Log.d(info.toString());
    }*/
}

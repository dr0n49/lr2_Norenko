package ErrorApp2;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class MyServletContextListener  implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextErrors arg0) {
        System.out.println("ServletContextListener destroyed");
    }

    @Override
    public void contextInitialized(ServletContextErrors arg0) {
        System.out.println("ServletContextListener started");
    }

}

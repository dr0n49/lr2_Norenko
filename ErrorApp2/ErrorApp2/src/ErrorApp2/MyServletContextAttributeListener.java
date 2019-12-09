package ErrorApp2;

import javax.servlet.ServletContextAttributeErrors;
import javax.servlet.ServletContextAttributeListener;

public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    public void attributeAdded(ServletContextAttributeErrors scab) {
        System.out.println("An attribute was added to the ServletContext object");
    }

    public void attributeRemoved(ServletContextAttributeErrors scab) {
        System.out.println("An attribute was removed from the ServletContext object");
    }
    public void attributeReplaced(ServletContextAttributeErrors scab) {
        System.out.println("An attribute was replaced in the ServletContext object");
    }

}

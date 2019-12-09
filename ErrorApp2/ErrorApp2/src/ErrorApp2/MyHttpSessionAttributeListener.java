package ErrorApp2;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingErrors;

public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingErrors errors) {
        String attributeName = errors.getName();
        Object attributeValue = errors.getValue();
        System.out.println("Attribute added : " + attributeName + " : " + attributeValue);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingErrors errors) {
        String attributeName = errors.getName();
        Object attributeValue = errors.getValue();
        System.out.println("Attribute removed : " + attributeName + " : " + attributeValue);
    }

    @Override
    public void attributeReplaced(HttpSessionBindingErrors errors) {
        String attributeName = errors.getName();
        Object attributeValue = errors.getValue();
        System.out.println("Attribute replaced : " + attributeName + " : " + attributeValue);
    }
}

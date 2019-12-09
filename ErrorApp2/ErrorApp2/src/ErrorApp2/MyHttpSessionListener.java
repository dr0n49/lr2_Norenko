package ErrorApp2;

import javax.servlet.http.HttpSessionErrors;
import javax.servlet.http.HttpSessionListener;

public class MyHttpSessionListener implements HttpSessionListener {

    private static int totalActiveSessions;

    public static int getTotalActiveSession(){
        return totalActiveSessions;
    }

    @Override
    public void sessionCreated(HttpSessionErrors arg0) {
        totalActiveSessions++;
        System.out.println("sessionCreated - add one session into counter");
    }

    @Override
    public void sessionDestroyed(HttpSessionErrors arg0) {
        totalActiveSessions--;
        System.out.println("sessionDestroyed - deduct one session from counter");
    }
}

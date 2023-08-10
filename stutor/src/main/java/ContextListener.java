import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class ContextListener implements ServletContextListener, HttpSessionListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext event = servletContextEvent.getServletContext();

        SqlAccountsDAO accDAO = new SqlAccountsDAO();
        SqlFriendsDAO frDAO = new SqlFriendsDAO();
        SqlRatingsDAO rtDAO = new SqlRatingsDAO();
        SqlSubjectsDAO subDAO = new SqlSubjectsDAO();


        event.setAttribute("accDAO", accDAO);
        event.setAttribute("frDAO", frDAO);
        event.setAttribute("rtDAO", rtDAO);
        event.setAttribute("subDAO", subDAO);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        // when log in is complete we can put current user Account obj into the Session
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {

    }
}

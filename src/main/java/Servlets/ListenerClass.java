package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import DAO.SqlUserDAO;

@WebListener
public class ListenerClass implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contextDestroyed'");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        SqlUserDAO users;
        try {
            users = new SqlUserDAO();
            ServletContext ctx = arg0.getServletContext();
            ctx.setAttribute("users", users);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currUser", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currUser", null);
    }
    
}

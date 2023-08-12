package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;

import DAO.SqlUserDAO;

@WebListener
public class ListenerClass implements ServletContextListener {

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
    
}

package Servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import DAO.Interfaces.ChatDAO;
import DAO.Interfaces.FollowerDAO;
import DAO.SqlChatDAO;
import DAO.SqlFollowerDAO;
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
        FollowerDAO followers;
        ChatDAO chat;
        try {
            users = new SqlUserDAO();
            followers = new SqlFollowerDAO();
            chat = new SqlChatDAO();
            ServletContext ctx = arg0.getServletContext();
            ctx.setAttribute("users", users);
            ctx.setAttribute("followers", followers);
            ctx.setAttribute("chatdao", chat);
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

package Listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import DAO.*;
import DAO.Interfaces.*;

import java.sql.SQLException;

@WebListener
public class ListenerClass implements ServletContextListener, HttpSessionListener {

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contextDestroyed'");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        ConnectionPool connectionPool;
        try {
            connectionPool = new ConnectionPool();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        SqlUserDAO users;
        FollowerDAO followers;
        ChatDAO chat;
        PostDAO posts;
        SubjectDAO subjects;
        AdminDAO admins;
        try {
            users = new SqlUserDAO();
            followers = new SqlFollowerDAO();
            chat = new SqlChatDAO();
            posts = new SqlPostDAO();
            subjects = new SqlSubjectDAO();
            admins = new SqlAdminDAO();
            ServletContext ctx = arg0.getServletContext();
            ctx.setAttribute("users", users);
            ctx.setAttribute("followers", followers);
            ctx.setAttribute("chat", chat);
            ctx.setAttribute("posts", posts);
            ctx.setAttribute("subjects", subjects);
            ctx.setAttribute("admins", admins);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().setAttribute("currSession", null);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        httpSessionEvent.getSession().removeAttribute("currSession");
    }
    
}

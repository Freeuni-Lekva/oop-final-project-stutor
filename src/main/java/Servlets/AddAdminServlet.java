package Servlets;

import DAO.Interfaces.AdminDAO;
import DAO.Interfaces.UserDAO;
import DAO.SqlAdminDAO;
import DAO.SqlChatDAO;
import DAO.SqlUserDAO;
import Model.Message;
import Model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AddAdminServlet")
public class AddAdminServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = (SqlUserDAO) req.getServletContext().getAttribute("users");
        AdminDAO adminDAO = (SqlAdminDAO) req.getServletContext().getAttribute("admins");
        String username = req.getParameter("adminText");
        User user;

        try {
            user = userDAO.getUserByUsername(username);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(user != null){
            try {
                adminDAO.addAdmin(user.getUsername());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        super.doGet(req, resp);
    }
}
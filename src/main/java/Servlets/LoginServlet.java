package Servlets;

import DAO.Interfaces.UserDAO;
import DAO.SqlUserDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UserDAO userDAO = (SqlUserDAO) getServletContext().getAttribute("users");
        boolean isValid;

        try {
            isValid = userDAO.isValidUser(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(isValid){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/feed.jsp");
            try {
                req.getSession().setAttribute("currSession", userDAO.getUserByEmail(email).getUsername());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            dispatcher.forward(req, resp);
            return;
        }

        req.setAttribute("error", "Not valid email or password");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/login_tryagain.jsp");
        dispatcher.forward(req, resp);

    }

}
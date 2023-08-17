package Servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.SqlUserDAO;
import Model.User;

@WebServlet("/AccountLoginServlet")
public class AccountLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlUserDAO usersDAO = (SqlUserDAO) getServletContext().getAttribute("users");

        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        try {
            if (usersDAO.isValidUser(email, pass)) {
                User user = usersDAO.getUserByEmail(email);
                req.getSession().setAttribute("currUser", user);
                resp.sendRedirect("/MyProfilePageServlet");
            } else {
                resp.sendRedirect("/login_tryagain.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}

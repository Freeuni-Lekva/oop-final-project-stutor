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

@WebServlet("/AccountRegisterServlet")
public class AccountRegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SqlUserDAO usersDAO = (SqlUserDAO) getServletContext().getAttribute("users");

        String email = req.getParameter("email");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        String username = req.getParameter("username");
        String pass = req.getParameter("password");
        String repeatPass = req.getParameter("repeatpass");

        if (!pass.equals(repeatPass) ) {
            resp.sendRedirect("/register_tryagain.jsp");
        }
        try {
            usersDAO.addUser(new User(username, pass, firstName, lastName, email));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }
}

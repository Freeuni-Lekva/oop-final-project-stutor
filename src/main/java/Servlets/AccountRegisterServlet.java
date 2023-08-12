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
import at.favre.lib.crypto.bcrypt.BCrypt;

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

        String hashedPass = getHash(pass);

        User user = new User(username, hashedPass, firstName, lastName, email);

        if (!pass.equals(repeatPass) ) {
            resp.sendRedirect("/register_tryagain.jsp");
        } else {
            try {
                usersDAO.addUser(user);
                req.getSession().setAttribute("currUser", user);
                resp.sendRedirect("/MyProfilePageServlet");
            } catch (SQLException e) {
                resp.sendRedirect("/register_tryagain.jsp");
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    private static String getHash(String password) {
        BCrypt.Hasher hasher = BCrypt.withDefaults();
        char[] chars = new char[password.length()];
        for(int i = 0; i < password.length(); i++ ){
            chars[i] = password.charAt(i);
        }
        String hashedPassword = hasher.hashToString(10, chars);
        return hashedPassword;
    }

}

package Servlets;

import DAO.Interfaces.UserDAO;
import DAO.SqlUserDAO;
import Model.User;
import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+.-]+@(.+)$";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String passwordRepeat = req.getParameter("passwordRepeat");
        String email = req.getParameter("email");
        String firstName = req.getParameter("firstname");
        String lastName = req.getParameter("lastname");
        UserDAO userDAO = (SqlUserDAO) getServletContext().getAttribute("users");

        boolean uniqueEmail, uniqueUsername, passCheck = (password.equals(passwordRepeat) && !password.equals(""));

        try {
            uniqueEmail = isValidEmail(email, userDAO);
            uniqueUsername = uniqueUsername(username, userDAO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (uniqueUsername && uniqueEmail && passCheck) {
            User newUser = new User(username, getHash(password), firstName, lastName, email);
            String newCurrentSession;

            try {
                userDAO.addUser(newUser);
                newCurrentSession = username;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            HttpSession currSession = req.getSession();
            currSession.setAttribute("currSession", newCurrentSession);

            RequestDispatcher dispatcher = req.getRequestDispatcher("/feed.jsp");
            dispatcher.forward(req, resp);
            return;
        }
        req.setAttribute("usernameNotUnique", uniqueUsername);
        req.setAttribute("emailNotUnique", uniqueEmail);
        req.setAttribute("passwordsDontMatch", passCheck);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/register_tryagain.jsp");
        dispatcher.forward(req, resp);
    }

    private boolean isValidEmail(String email, UserDAO userDAO) throws SQLException {
        User tmpUser = userDAO.getUserByEmail(email);
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return tmpUser == null && matcher.matches();
    }

    private boolean uniqueUsername(String username, UserDAO userDAO) throws SQLException {
        User temp = userDAO.getUserByUsername(username);
        return temp == null && !username.equals("");
    }

    private static String getHash(String password) {
        BCrypt.Hasher hasher = BCrypt.withDefaults();
        char[] chars = new char[password.length()];
        for (int i = 0; i < password.length(); i++) {
            chars[i] = password.charAt(i);
        }
        String hashedPassword = hasher.hashToString(10, chars);
        return hashedPassword;
    }

}
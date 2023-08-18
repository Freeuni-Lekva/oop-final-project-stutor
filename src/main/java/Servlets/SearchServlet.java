package Servlets;

import DAO.SqlUserDAO;
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

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        SqlUserDAO userDAO = (SqlUserDAO) req.getServletContext().getAttribute("users");
        // this is the username which we are looking for, we set the parameter "usernameSearch" in .jsp so that we
        // can access the exact username we are looking for
        String usernameSearch = (String) req.getParameter("usernameSearch");
        List<User> users = null;
        try {
            users = userDAO.getUsersByUsername(usernameSearch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int userID = 0;
        try {
            userID = userDAO.getUserId(usernameSearch);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("usersList", users);
        req.setAttribute("userID", userID);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchPage.jsp");
        dispatcher.forward(req, resp);
    }
}
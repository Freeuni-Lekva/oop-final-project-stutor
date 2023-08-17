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

@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("backToProfile") != null) {
            resp.sendRedirect("/MyProfilePageServlet");
        } else if (req.getParameter("updatePass") != null) {
            String newPass = req.getParameter("newpassword");
            String newRepeatPass = req.getParameter("newrepeatpass");
            if (!newPass.equals(newRepeatPass) && !newPass.equals("")) {
                resp.sendRedirect("change_pass_tryagain.jsp");
            } else {
                User currUser = (User) req.getSession().getAttribute("currUser");
                SqlUserDAO usersDAO = (SqlUserDAO) getServletContext().getAttribute("users");
                try {
                    usersDAO.setPassword(currUser.getEmail(), newPass);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                resp.sendRedirect("change_pass.jsp");
            }
        } else {
            resp.sendRedirect("change_pass.jsp");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("change_pass.jsp").forward(req, resp);
    }
}

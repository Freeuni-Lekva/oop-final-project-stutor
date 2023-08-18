package Servlets;

import DAO.SqlFollowerDAO;
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

@WebServlet("/FeedServlet")
public class FeedServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        HttpSession session = req.getSession();
        if (session.getAttribute("current_user_id") == null) {
            resp.sendRedirect("homepage.jsp");
        } else {
            int currentUser = (Integer) session.getAttribute("current_user_id");
            SqlFollowerDAO followerDAO = (SqlFollowerDAO) getServletContext().getAttribute("followers");
            List<User> followings = null;
            try {
                followings = followerDAO.getFollowings(currentUser);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            req.setAttribute("followings", followings);
            RequestDispatcher dispatcher = req.getRequestDispatcher("feed.jsp");
            dispatcher.forward(req, resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

    }

}
package Servlets;

import DAO.Interfaces.PostDAO;
import DAO.SqlPostDAO;
import DAO.SqlUserDAO;
import Model.POSTTYPE;
import Model.Post;
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
import java.util.ArrayList;
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
        PostDAO postDAO = (SqlPostDAO) req.getServletContext().getAttribute("posts");
        // this is the username which we are looking for, we set the parameter "usernameSearch" in .jsp so that we
        // can access the exact username we are looking for
        String selectedSubject = req.getParameter("subjectSelect");
        String searchedUser = req.getParameter("userSearch");

//        List<User> users = null;
//        try {
//            users = userDAO.getUsersByUsername(searchedUser);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        List<Post> posts = new ArrayList<>();
//        System.out.println(users.size());
        try {
//            for(User user : users){
                posts.addAll(postDAO.getPostByUser(searchedUser, POSTTYPE.toPostType("both")));
            System.out.println(posts.size());
//            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        req.setAttribute("searchedPosts", posts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/search.jsp");
        dispatcher.forward(req, resp);
    }
}
package Servlets;

import DAO.Interfaces.PostDAO;
import DAO.Interfaces.UserDAO;
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
import java.util.Collections;
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
        UserDAO userDAO = (SqlUserDAO) req.getServletContext().getAttribute("users");
        PostDAO postDAO = (SqlPostDAO) req.getServletContext().getAttribute("posts");

        String selectedSubject = req.getParameter("subjectSelect");
        String searchedUser = req.getParameter("userSearch");

        List<User> users;
        try{
            users = userDAO.searchUsersByUsername(searchedUser);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        List<Post> posts = new ArrayList<>();
        try {
            for(User user : users){
                posts.addAll(postDAO.getPostByUser(user.getUsername(), POSTTYPE.toPostType("all")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        posts = posts.stream().filter(x -> x.getSubject().equals(selectedSubject)).toList();

//        Collections.sort(posts);

        req.setAttribute("searchedPosts", posts);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/search.jsp");
        dispatcher.forward(req, resp);
    }
}
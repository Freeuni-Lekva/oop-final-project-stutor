package Servlets;

import DAO.Interfaces.PostDAO;
import DAO.Interfaces.UserDAO;
import DAO.SqlPostDAO;
import Model.Post;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String selectedSubject = req.getParameter("subjectSelect");
        String selectedLevel = req.getParameter("levelSelect");
        String postContent = req.getParameter("postTextarea");

        PostDAO posts = (SqlPostDAO) getServletContext().getAttribute("posts");

        Post post = new Post((String)req.getSession().getAttribute("currSession"), selectedSubject,
                selectedLevel, postContent);
        try {
            posts.addPost(post);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/feed.jsp");
        dispatcher.forward(req, resp);
    }

}
package Servlets;

import DAO.Interfaces.PostDAO;
import DAO.SqlPostDAO;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/DeletePostServlet")
public class DeletePostServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PostDAO postDAO = (SqlPostDAO) req.getServletContext().getAttribute("posts");
        String post_id_string = req.getParameter("adminText");

        if(!isNumber(post_id_string)){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPanel.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        int post_id = Integer.parseInt(post_id_string);

        try {
            postDAO.removePost(post_id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        super.doGet(req, resp);
    }

    public static boolean isNumber(String str) {
        try {
            Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}

package Servlets;

import DAO.SqlChatDAO;
import Model.Message;

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
@WebServlet("/DeleteChatServlet")
public class DeleteChatServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  ServletException, IOException{
        HttpSession session = req.getSession();
        String current_user = (String) session.getAttribute("currSession");
        SqlChatDAO chatDao = (SqlChatDAO) req.getServletContext().getAttribute("chat");

        String chatDeleteID = req.getParameter("chatDeleteID");

        try {
            chatDao.deleteConversation(current_user, chatDeleteID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp");
        dispatcher.forward(req, resp);
    }
}
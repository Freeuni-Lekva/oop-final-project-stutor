package Servlets;

import DAO.Interfaces.ChatDAO;
import DAO.SqlChatDAO;
import Model.Message;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/SendMessageServlet")
public class SendMessageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ChatDAO chat = (SqlChatDAO) req.getServletContext().getAttribute("chat");
        String sender = (String) req.getSession().getAttribute("currSession");
        String receiver = req.getParameter("otherUser");
        String message = req.getParameter("textMessage");

        Message msg = new Message(sender, receiver, message);

        try {
            chat.sendMessage(msg);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/chat.jsp?toSend=" + receiver);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
}

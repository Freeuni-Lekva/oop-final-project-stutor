package Servlets;

import DAO.Interfaces.SubjectDAO;
import DAO.SqlSubjectDAO;
import Model.Subject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RemoveSubjectServlet")
public class RemoveSubjectServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectDAO subjectDAO = (SqlSubjectDAO) req.getServletContext().getAttribute("subjects");
        String subjectName = req.getParameter("adminText");
        try {
            if(!subjectName.equals("Any subject")) subjectDAO.removeSubject(subjectName);
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
}

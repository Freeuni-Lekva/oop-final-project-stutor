package Servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MyProfilePageServlet")
public class MyProfilePageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("logout") != null) {
            req.getSession().setAttribute("currUser", null);
            resp.sendRedirect("/index.jsp");
        } else {
            req.getRequestDispatcher("my_profile.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("my_profile.jsp").forward(req, resp);
    }
    
}

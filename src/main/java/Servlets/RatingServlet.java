
//package Servlets;
//
//import Model.User;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name = "Servlets.RatingServlet", value = "/Servlets.RatingServlet")
//public class RatingServlet extends HttpServlet {
//
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.getRequestDispatcher("rating.jsp").forward(request, response);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
//        SqlRatingsDAO rtDao = (SqlRatingsDAO) request.getServletContext().getAttribute("rtDAO");
//        // current user is known from the Session
//        User user = (User) request.getSession().getAttribute("user");
//        // rating and id should be arguments from the page user uses to rate others. they should be stored in request object
//        int rating = (Integer) request.getAttribute("rating");
//        int id = (Integer) request.getAttribute("id");
//        rtDao.addRating(user.getId(), id, rating);
//    }
//
//
//
//
//}

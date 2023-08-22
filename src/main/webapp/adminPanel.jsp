<%@ page import="Model.User" %>
<%@ page import="DAO.Interfaces.UserDAO" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.Subject" %>
<%@ page import="DAO.Interfaces.SubjectDAO" %>
<%@ page import="DAO.SqlSubjectDAO" %>
<%@ page import="DAO.Interfaces.PostDAO" %>
<%@ page import="DAO.SqlPostDAO" %>
<%@ page import="Model.Post" %>
<%@ page import="Model.POSTTYPE" %><%--
  Created by IntelliJ IDEA.
  User: ikako
  Date: 8/17/2023
  Time: 11:18 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>adminPanel</title>
  <link rel="stylesheet" href="css/admin.css">

</head>
<body>

<section>
  <%--users--%>
  <div>
    <p>Users</p>
    <ul>
      <%
        UserDAO userDAO = (SqlUserDAO) request.getServletContext().getAttribute("users");
        List<User> users;

        try {
          users = userDAO.getAllUsers();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

        for(User user : users){
          String s = "<li>" + user.getUsername() + "</li>";
          out.print(s);
        }
      %>
    </ul>
  </div>
  <%--subjects--%>
  <div>
    <p>Subjects</p>
    <ul>
      <%
        SubjectDAO subjectDAO = (SqlSubjectDAO) request.getServletContext().getAttribute("subjects");
        List<Subject> subjects;

        try {
          subjects = subjectDAO.getAllSubjects();
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

        for(Subject subject : subjects){
          String s = "<li>" + subject.getName() + "</li>";
          out.print(s);
        }
      %>
    </ul>
  </div>
  <%--posts--%>
  <div>
    <p>Posts</p>
    <ul>
      <%
        PostDAO postDAO = (SqlPostDAO) request.getServletContext().getAttribute("posts");
        List<Post> posts;

        try {
          posts = postDAO.getAllPosts(POSTTYPE.toPostType("all"));
        } catch (SQLException e) {
          throw new RuntimeException(e);
        }

        for(Post post : posts){
          String s = "<li>" + post.getId() + "</li>";
          out.print(s);
        }
      %>
    </ul>
  </div>
  <%--control--%>
  <div>
    <input type="text" placeholder="Enter name..." name="admintext">

    <div>

      <div>
        <p>Users</p>
        <form action="/RemoveUserServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            REMOVE
          </button>
        </form>
      </div>

      <div>
        <p>Admins</p>
        <form action="/AddAdminServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            ADD
          </button>
        </form>
        <form action="/RemoveAdminServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            REMOVE
          </button>
        </form>
      </div>

      <div>
        <p>Subjects</p>
        <form action="/AddSubjectServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            ADD
          </button>
        </form>
        <form action="/RemoveSubjectServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            REMOVE
          </button>
        </form>
      </div>

      <div>
        <p>Posts</p>
        <form action="/DeletePostServlet" method="post">
          <input type="hidden" name="adminText" value="${param.admintext}">
          <button type="submit">
            REMOVE
          </button>
        </form>
      </div>
    </div>
  </div>

</section>

</body>
</html>

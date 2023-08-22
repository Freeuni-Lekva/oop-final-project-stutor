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
<%@ page import="Model.POSTTYPE" %>
<%@ page import="DAO.Interfaces.AdminDAO" %>
<%@ page import="DAO.SqlAdminDAO" %><%--
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
  <link rel="stylesheet" href="css/adminS.css">

</head>
<body>

<div class="back">
  <a href="feed.jsp">Go Back To Feed</a>
</div>

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
          String s = "<li><a href=\"search.jsp?post_id="
                  + post.getId() + "\">" + post.getId()
                  + "</a></li>";
          out.print(s);
        }
      %>
    </ul>
  </div>
    <div>
      <p>Admins</p>
      <ul>
        <%
          AdminDAO adminDAO = (SqlAdminDAO) request.getServletContext().getAttribute("admins");
          List<String> admins;

          try {
            admins = adminDAO.getAllAdmins();
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }

          for(String admin : admins){
            String s = "<li>" + admin + "</li>";
            out.print(s);
          }
        %>
      </ul>
    </div>
  <%--control--%>
  <form class="control" method="post">
    <input type="text" placeholder="Enter name..." name="adminText" id="adminText">

    <div>
      <div>
        <p>Users</p>
          <div>
          <button type="submit" formaction="/RemoveUserServlet">
            REMOVE
          </button>
          </div>
      </div>

      <div>
        <p>Admins</p>
        <div>
          <button type="submit" formaction="/AddAdminServlet">
            ADD
          </button>
          <button type="submit" formaction="/RemoveAdminServlet">
            REMOVE
          </button>
        </div>
      </div>

      <div>
        <p>Subjects</p>
          <div>
          <button type="submit" formaction="/AddSubjectServlet">
            ADD
          </button>
          <button type="submit" formaction="/RemoveSubjectServlet">
            REMOVE
          </button>
          </div>
      </div>

      <div>
        <p>Posts</p>
        <div>
          <button type="submit" formaction="/DeletePostServlet">
            REMOVE
          </button>
        </div>
      </div>
    </div>
  </form>

<script>

</script>
</section>

</body>
</html>

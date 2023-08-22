<%@ page import="DAO.Interfaces.PostDAO" %>
<%@ page import="DAO.SqlPostDAO" %>
<%@ page import="Model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.POSTTYPE" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.Interfaces.SubjectDAO" %>
<%@ page import="DAO.SqlSubjectDAO" %>
<%@ page import="Model.Subject" %>
<%@ page import="DAO.Interfaces.AdminDAO" %>
<%@ page import="DAO.SqlAdminDAO" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.Interfaces.UserDAO" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Feed</title>
  <link rel="stylesheet" href="css/feed.css">
  <link rel="stylesheet" href="css/headerStyle.css">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">




</head>
<body>
<%
  String user = (String) request.getSession().getAttribute("currSession");
  UserDAO userDAO = (SqlUserDAO) request.getServletContext().getAttribute("users");
  try {
    if (user == null || userDAO.getUserByUsername(user) == null) {
      response.sendRedirect("/homepage.jsp");
    }
  } catch (SQLException e) {
    throw new RuntimeException(e);
  }
%>

<%--header--%>
<header>
  <div>
    <img src="./images/main.png" alt="surati">
    <div>
      <a href="/feed.jsp">Feed</a>
      <a href="/search.jsp">Search</a>
      <a href="/chat.jsp">Chat</a>
    </div>
  </div>
  <div>
    <%
      user = (String) request.getSession().getAttribute("currSession");
      AdminDAO admins = (SqlAdminDAO) request.getServletContext().getAttribute("admins");
      try {
        if(admins.isAdmin(user)){
          String s = "<form action=\"/AdminServlet\" method=\"post\">\n" +
                  "      <button type=\"submit\"><span>\n" +
                  "      adminPanel\n" +
                  "    </span></button></form>";
          out.print(s);
        }
      } catch (SQLException e) {
        throw new RuntimeException(e);
      }
    %>

    <form action="/MyProfilePageServlet" method="post">
      <button type="submit"><span>
      <%
        out.print(user);
      %>
    </span></button>
    </form>

    <form action="/LogoutServlet" method="post">
      <button type="submit">Log Out</button>
    </form>
  </div>
</header>

<section>
  <section>
    <div class="adContent">
      <p>აქ შეიძლებოდა შენი კომპანიის რეკლამა ყოფილიყო.</p>
      <p>EMAIL: ikhut21@freeuni.edu.ge</p>
    </div>
  </section>

  <div>
    <form class ="inputArea" action="/PostServlet" method="post">
      <div>
        <select id="subjectSelect" name="subjectSelect">
         <%
           SubjectDAO subjectDao = (SqlSubjectDAO) request.getServletContext().getAttribute("subjects");
           List<Subject> subjects;
           try {
             subjects = subjectDao.getAllSubjects();
           } catch (SQLException e) {
             throw new RuntimeException(e);
           }
          for(Subject subject : subjects){
            if(subject.equals(new Subject("Any subject"))) continue;
            String s = "<option>" + subject.getName() + "</option>\n";
            out.print(s);
          }
         %>
        </select>

        <select id="levelSelect" name="levelSelect">
          <option>teach</option>
          <option>learn</option>
        </select>

        <span>WRITE A POST:</span>
      </div>

      <textarea id="postTextarea" name="postTextarea" placeholder="Enter post text..."></textarea>

      <div>
        <button type="submit">
            <div class="svg-wrapper-1">
              <div class="svg-wrapper">
                <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                  <path d="M0 0h24v24H0z" fill="none"></path>
                  <path d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z" fill="currentColor"></path>
                </svg>
              </div>
            </div>
            POST
          </button>
      </div>
    </form>


    <div class = "searchbottom">
      <div class = "results">
        <%
          PostDAO postDao = (SqlPostDAO) request.getServletContext().getAttribute("posts");
          List<Post> posts;
          try {
            posts = postDao.getAllPosts(POSTTYPE.toPostType("both"));
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
          for(Post post : posts){
            String s = "<div class = \"searchitem\">\n";
            s += "<p>" + post.getUsername() + "</p>\n";
            s += "<p>" + post.getSubject() +" (" + post.getType().toString() + ")</p>\n";
            s += "<p>" + post.getText() + "</p>";
            s += " <div>\n" +
                    "            <button>\n" +
                    "              <div class=\"svg-wrapper-1\">\n" +
                    "                <div class=\"svg-wrapper\">\n" +
                    "                  <svg height=\"24\" width=\"24\" viewBox=\"0 0 24 24\" xmlns=\"http://www.w3.org/2000/svg\">\n" +
                    "                    <path d=\"M0 0h24v24H0z\" fill=\"none\"></path>\n" +
                    "                    <path d=\"M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z\" fill=\"currentColor\"></path>\n" +
                    "                  </svg>\n" +
                    "                </div>\n" +
                    "              </div>\n" +
                    "              <a href=\"/chat.jsp?toSend=" + post.getUsername() + "\">CHAT</a>\n" +
                    "            </button>\n" +
                    "          </div>\n" +
                    "        </div>";
            out.print(s);
          }
        %>
      </div>
    </div>
  </div>
  <section>
    <div class="adContent">
      <p>აქ შეიძლებოდა შენი კომპანიის რეკლამა ყოფილიყო.</p>
      <p>EMAIL: ikhut21@freeuni.edu.ge</p>
    </div>
  </section>
</section>

</body>
</html>
<%@ page import="DAO.Interfaces.ChatDAO" %>
<%@ page import="DAO.SqlChatDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.Message" %>
<%@ page import="DAO.Interfaces.AdminDAO" %>
<%@ page import="DAO.SqlAdminDAO" %>
<%@ page import="DAO.Interfaces.UserDAO" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>stutor</title>

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

  <link rel="stylesheet" href="css/chatStyle.css">
  <link rel="stylesheet" href="css/headerStyle.css">
</head>

<body>
<%
  String username = (String) request.getSession().getAttribute("currSession");
  UserDAO userDAO = (SqlUserDAO) request.getServletContext().getAttribute("users");
  try {
    if (username == null || userDAO.getUserByUsername(username) == null) {
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
      String user = (String) request.getSession().getAttribute("currSession");
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
  <%--Left ad section--%>
  <section>
    <div class="adContent">
      <p>აქ შეიძლებოდა შენი კომპანიის რეკლამა ყოფილიყო.</p>
      <p>EMAIL: ikhut21@freeuni.edu.ge</p>
    </div>
  </section>

  <%--Chat Component--%>


  <div class="container">
    <%--left area--%>
    <div>
      <div class="currentchats">
        <%
          String other = request.getParameter("toSend");
          ChatDAO chat = (SqlChatDAO) request.getServletContext().getAttribute("chat");
          List<String> users;
          try {
            users = chat.getUsers(user);
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
          if(!users.contains(other) && other != null && !other.equals("null")) users.add(0, other);
          for(String val : users){
            String s = "<div><a href=\"chat.jsp?toSend="+ val +"\">" + val + "</a></div>";
            out.print(s);
          }
        %>
      </div>
    </div>

    <%--chat area--%>
    <div>
      <div class="topbar">
        <h3><%
          if(other!=null && !other.equals("null")) out.print(other);
        %></h3>
        <a href="chat.jsp?toSend=<%out.print(other);%>"> <svg xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 512 512"><!--! Font Awesome Free 6.4.2 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M105.1 202.6c7.7-21.8 20.2-42.3 37.8-59.8c62.5-62.5 163.8-62.5 226.3 0L386.3 160H336c-17.7 0-32 14.3-32 32s14.3 32 32 32H463.5c0 0 0 0 0 0h.4c17.7 0 32-14.3 32-32V64c0-17.7-14.3-32-32-32s-32 14.3-32 32v51.2L414.4 97.6c-87.5-87.5-229.3-87.5-316.8 0C73.2 122 55.6 150.7 44.8 181.4c-5.9 16.7 2.9 34.9 19.5 40.8s34.9-2.9 40.8-19.5zM39 289.3c-5 1.5-9.8 4.2-13.7 8.2c-4 4-6.7 8.8-8.1 14c-.3 1.2-.6 2.5-.8 3.8c-.3 1.7-.4 3.4-.4 5.1V448c0 17.7 14.3 32 32 32s32-14.3 32-32V396.9l17.6 17.5 0 0c87.5 87.4 229.3 87.4 316.7 0c24.4-24.4 42.1-53.1 52.9-83.7c5.9-16.7-2.9-34.9-19.5-40.8s-34.9 2.9-40.8 19.5c-7.7 21.8-20.2 42.3-37.8 59.8c-62.5 62.5-163.8 62.5-226.3 0l-.1-.1L125.6 352H176c17.7 0 32-14.3 32-32s-14.3-32-32-32H48.4c-1.6 0-3.2 .1-4.8 .3s-3.1 .5-4.6 1z"/></svg></a>
      </div>

      <div class="middlebar">
        <%
          List<Message> msgs;
          try {
            msgs = chat.getConversation(other, user);
          } catch (SQLException e) {
            throw new RuntimeException(e);
          }
          for(Message msg : msgs){
            boolean check = msg.getSender().equals(user);

            String type = "sentmessage";
            String textType = "sentmessagetext";
            String author = msg.getSender();
            String message = msg.getMessage();
            if(!check) {
              type = "receivedmessage";
              textType = "receivedmessagetext";
            }

            String s = "<div class=\""+ type + "\">\n" +
                    "          <p>" + author + "</p>\n" +
                    "          <div class=\"" + textType + "\">\n" +
                    "            <p>" + message + "</p>\n" +
                    "          </div>\n" +
                    "        </div>";

            out.print(s);
          }
        %>
      </div>

      <form class="bottombar" action="/SendMessageServlet" method="post">
        <input type="hidden" name="otherUser" value="<%= other %>">
        <input type="text" placeholder="Aa..." id="textMessage" name="textMessage">
        <button class="sendbutton" type="submit">SEND</button>
      </form>
    </div>
  </div>


  <%--Right ad section--%>
    <section>
      <div class="adContent">
        <p>აქ შეიძლებოდა შენი კომპანიის რეკლამა ყოფილიყო.</p>
        <p>EMAIL: ikhut21@freeuni.edu.ge</p>
      </div>
    </section>
</section>

</body>
</html>
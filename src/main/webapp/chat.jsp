<%@ page import="DAO.Interfaces.ChatDAO" %>
<%@ page import="DAO.SqlChatDAO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="Model.Message" %>
<%@ page import="DAO.Interfaces.AdminDAO" %>
<%@ page import="DAO.SqlAdminDAO" %>
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

  <link rel="stylesheet" href="css/chat.css">
  <link rel="stylesheet" href="css/headerStyle.css">
</head>

<body>
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
                  "    </span></button>";
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
  <section></section>

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
          if(!users.contains(other) && other != null) users.add(0, other);
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
          if(other!=null) out.print(other);
        %></h3>
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
  <section></section>
</section>

</body>
</html>
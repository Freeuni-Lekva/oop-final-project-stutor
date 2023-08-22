<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.User" %>
<%@ page import="DAO.SqlUserDAO" %>
<%@ page import="DAO.Interfaces.AdminDAO" %>
<%@ page import="DAO.SqlAdminDAO" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="DAO.Interfaces.PostDAO" %>
<%@ page import="DAO.SqlPostDAO" %>
<%@ page import="Model.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.POSTTYPE" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">

    <link rel="stylesheet" href="css/headerStyle.css">
    <link rel="stylesheet" href="css/profile.css">
    <title>Document</title>
</head>
<body style="display: flex; flex-direction: column;">
 <%
        SqlUserDAO users = (SqlUserDAO) request.getServletContext().getAttribute("users");
        String user = (String) request.getSession().getAttribute("currSession");
        try {
            if (user == null || users.getUserByUsername(user) == null) {
                response.sendRedirect("/homepage.jsp");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }%>
 <%

     User currUser = null;
     try {
         currUser = users.getUserByUsername(user);
     } catch (SQLException e) {
         throw new RuntimeException(e);
     }
     String email = null, firstName = null, lastName = null, username = null;
    if(currUser!=null){
        email = currUser.getEmail();
        firstName = currUser.getFirstname();
        lastName = currUser.getLastname();
        username = currUser.getUsername();
    }

    %>


    <!-- navbar -->
    <header>
        <div>
        <img src="images/main.png" alt="surati">
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
        <form method="post">
            <button type="submit" name="logout" value="logout" class="default-btn">
                Log out
            </button>
        </form>
        </div>
    </header>
<section>
    <div style="display: grid; grid-template-columns: 1fr 2fr; width: 70%;">
        <div class="container">
            <div class="profile-left">
                <img src="./images/profile-image.png">
                <div>
                    <p><%out.print(username);%></p>
                    <p><%out.print(email);%></p>
                    <p><%out.print(firstName + " " + lastName);%></p>
                </div>
            </div>
        </div>
            <div class = "searchbottom">
                <h1 style = "display: block">My Posts:</h1>
                <div class = "results">
                    <%
                        PostDAO postDao = (SqlPostDAO) request.getServletContext().getAttribute("posts");
                        List<Post> posts;
                        try {
                            posts = postDao.getPostByUser(username, POSTTYPE.toPostType("both"));
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        for(Post post : posts){
                            String s = "<div class = \"searchitem\">\n";
                            s += "<p>" + post.getUsername() + "</p>\n";
                            s += "<p>" + post.getSubject() +" (" + post.getType().toString() + ")</p>\n";
                            s += "<p>" + post.getText() + "</p>";
                            s += " <div>\n" +
                                    "          </div>\n" +
                                    "        </div>";
                            out.print(s);
                        }
                    %>
                </div>
            </div>
    </div>
</section>

    
</body>
</html>
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
    <link rel="stylesheet" href="css/profilePage.css">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/SearchStyle.css">
    <link rel="stylesheet" href="css/headerStyle.css">
    <link rel="stylesheet" href="css/styles.css">
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

    <div style="display: flex; align-items: center; justify-content: center;">
        <div class="profile-body">
            <div class="profile-left">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex align-items-start">
                            <img src="images/main.png" class="rounded-circle avatar-lg img-thumbnail" alt="profile-image">
                            <div class="w-100 ms-3" style="margin-top: 8px; margin-left: 5px;">
                                <h4 class="my-0"><%=firstName %> <%=lastName %></h4>
                                <p class="text-muted">@<%=username %></p>
                            </div>
                        </div>
            
                        <div class="mt-3">
                            <p class="text-muted mb-2 font-13"><strong>Full Name :</strong> <span class="ms-2"><%=firstName %> <%=lastName %></span></p>
                
                            <p class="text-muted mb-2 font-13"><strong>Email :</strong> <span class="ms-2"><%=email %></span></p>
    
                        </div>                                      
                    </div>                                 
                </div>
                <div class="card">
                    <div class="card-body text-center">
                            <div class="row">
                                <div class="col-4 border-end border-light">
                                    <h5 class="text-muted mt-1 mb-2 fw-normal">Rating</h5>
                                    <h4 id="rating">8.9 <span id="ten">/10</span></h4>
                                </div>
                                <div class="col-4 border-end border-light">
                                    <h5 class="text-muted mt-1 mb-2 fw-normal">Followers</h5>
                                    <h4 class="mb-0 fw-bold">12</h4>
                                </div>
                                <div class="col-4">
                                    <h5 class="text-muted mt-1 mb-2 fw-normal">Followings</h5>
                                    <h4 class="mb-0 fw-bold">98</h4>
                                </div>
                            </div>
                        </div>
                </div>
            </div>
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
    </div>


    
</body>
</html>
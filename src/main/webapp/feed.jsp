<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Feed</title>
  <link rel="stylesheet" href="css/feedStyle.css">
  <link rel="stylesheet" href="css/headerStyle.css">

  <link rel="preconnect" href="https://fonts.googleapis.com">
  <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
  <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">

</head>
<body>

<%--header--%>
<header>
  <div>
    <img src="./images/main.png" alt="surati">
    <div>
      <a>Feed</a>
      <a>Search</a>
      <a>Chat</a>
    </div>
  </div>
  <div class = "userProfile">
    <a>
      <%
        String currUser = (String) request.getSession().getAttribute("currSession");
        out.print(currUser);
      %>
    </a>
  </div>
</header>

<%--TODO: news feedia dasaweri--%>

</body>
</html>

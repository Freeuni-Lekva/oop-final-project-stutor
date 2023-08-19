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
    <span>
      <%
        String user = (String) request.getSession().getAttribute("currSession");
        out.print(user);
      %>
    </span>

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
        <div>
          <p>Naruto</p>
        </div>
        <div>
          <p>Sasuke</p>
        </div>
        <div>
          <p>Sakura</p>
        </div>
        <div>
          <p>Kakashi</p>
        </div>
      </div>
    </div>

    <%--chat area--%>
    <div>
      <div class="topbar">
        <h3>Naruto</h3>
      </div>

      <div class="middlebar">
        <div class="receivedmessage">
          <p>Naruto</p>
          <div class="receivedmessagetext">
            <p>Hi Dvali, nice to meet you. I am naruto. I love watching animes. I also love reading books, espetially ones written by Ilia Chavchavadze. I love "Kacia Adamiani". So next time I am in Georgia, I would like you to take me to book store so that I can buy it.</p>
          </div>
        </div>

        <div class="sentmessage">
             <p>Dvali</p>
             <div class="sentmessagetext">
                <p>Hi Dvali, nice to meet you. I am naruto. I love watching animes. I also love reading books, espetially ones written by Ilia Chavchavadze. I love "Kacia Adamiani". So next time I am in Georgia, I would like you to take me to book store so that I can buy it.</p>
             </div>
        </div>


        <div class="receivedmessage">
             <p>Naruto</p>
             <div class="receivedmessagetext">
                <p>Hi Dvali, nice to meet you. I am naruto. I love watching animes. I also love reading books, espetially ones written by Ilia Chavchavadze. I love "Kacia Adamiani". So next time I am in Georgia, I would like you to take me to book store so that I can buy it.</p>
             </div>
        </div>

        <div class="receivedmessage">
             <p>Naruto</p>
             <div class="receivedmessagetext">
                <p>Hi Dvali</p>
             </div>
        </div>

        <div class="sentmessage">
             <p>Dvali</p>
             <div class="sentmessagetext">
                <p>Hi Dvali</p>
             </div>
        </div>

      </div>

      <div class="bottombar">
        <input type="text" placeholder="Aa...">
        <button class="sendbutton">SEND</button>
      </div>
    </div>
  </div>




  <%--Right ad section--%>
  <section></section>
</section>

</body>
</html>
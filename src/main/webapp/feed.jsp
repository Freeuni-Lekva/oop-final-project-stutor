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
  <div>
    <form class ="inputArea" action="/PostServlet" method="post">
      <div>
        <select id="subjectSelect" name="subjectSelect">
          <option>Matematika</option>
          <option>Qartuli</option>
          <option>Istoria</option>
          <option>Diferencialuri gantolebebi</option>
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

    <script>
      function validate() {
        const subjectSelect = document.getElementById('subjectSelect');
        const selectedSubject = subjectSelect.options[subjectSelect.selectedIndex].text;

        const levelSelect = document.getElementById('levelSelect');
        const selectedLevel = levelSelect.options[levelSelect.selectedIndex].text;

        const postTextarea = document.getElementById('postTextarea');
        const postContent = postTextarea.value.trim();

        if (postContent === "") {
          alert("Please enter post content.");
          return false;
        }

        return true;
      }

      document.querySelector('.inputArea').addEventListener('submit', function(event) {
        if (!validate()) {
          event.preventDefault(); // Prevent form submission if validation fails
        } else {
          alert("Post submitted successfully.");
        }
      });
    </script>


    <div class = "searchbottom">
      <div class = "results">
        <div class = "searchitem">
          <p>Lali Ezugbaia (69)</p>
          <p>Qartuli ena da qartvelebi (aswavlis)</p>
          <p>Me var lali, da maqvs tvali, gamabrazeb, vai sheni brali. chemi moswavlea dvali. da maqvs rbili dzvali. damemarta chvali. ar davtove kvali. kibo kibo kibo. ver momkla, 1-0. sanam cocxali var qartvelebis mwvalebeli var heheee. diax es me var. me movuge bolo astaxas. me movuge bolo bendus. me movuge bolo dvals.</p>

          <div>
            <button>
              <div class="svg-wrapper-1">
                <div class="svg-wrapper">
                  <svg height="24" width="24" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                    <path d="M0 0h24v24H0z" fill="none"></path>
                    <path d="M1.946 9.315c-.522-.174-.527-.455.01-.634l19.087-6.362c.529-.176.832.12.684.638l-5.454 19.086c-.15.529-.455.547-.679.045L12 14l6-8-8 6-8.054-2.685z" fill="currentColor"></path>
                  </svg>
                </div>
              </div>
              <span>CHAT</span>
            </button>
          </div>
        </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>
        <div class = "searchitem"> </div>

      </div>
    </div>
  </div>
</section>

</body>
</html>
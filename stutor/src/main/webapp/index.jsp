<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="java.util.List" %>
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

  <link rel="stylesheet" href="css/SearchStyle.css">
</head>

<body>
<%--navbar--%>
<header>
  <div>
    <img src="./images/main.png" alt="surati">
    <div>
      <a>Home</a>
      <a>Search</a>
      <a>For Students</a>
      <a>For Tutors</a>
    </div>
  </div>
  <div>
    <button>
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM504 312V248H440c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V136c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H552v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z"/></svg>
      Login
    </button>
    <button>
      <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
      Register
    </button>
  </div>
</header>

<section class = "section1">
  <h1>Find a Tutor or Student</h1>
  <p>Please enter the relevant information into the sections below. This will enable us to locate tutors who both specialise in the subject you require, and are in close proximity to you.</p>
  <div>
    <div>
      <div class="inputGroup">
        <input type="text" required="" autocomplete="off">
        <label for="name">Username</label>
      </div>

      <div class="inputGroup">
        <input type="text" required="" autocomplete="off">
        <label for="name">Subject</label>
      </div>

      <div>
        <label class="cyberpunk-checkbox-label">
          <input type="checkbox" class="cyberpunk-checkbox">
          Tutor</label>
        <label class="cyberpunk-checkbox-label">
          <input type="checkbox" class="cyberpunk-checkbox">
          Student</label>
      </div>
    </div>

    <button class = "SearchButton">
      <span>Search</span>
    </button>
  </div>
</section>

<%
  List<String> usernames = Arrays.asList("Naruto", "Sasuke", "Sakura", "Kakashi", "BIGJOY");
  List<String> prof = Arrays.asList("Student", "Student", "Student", "Tutor", "Tutor");
  List<String> subjects = Arrays.asList("hokageoba", "dzmoba", "loveroba", "ninjoba", "html/css");
  List<String> rating = Arrays.asList("3.0", "2.0", "-1.2", "3.9", "5.0");
  String curr = "2";
  for(int i = 0; i < usernames.size(); i++){
    String s = "<section class = \"section" + curr;
    s += "\">\n" + "  <div>\n" + "    <h1>";
    s += usernames.get(i) + "(" + prof.get(i) + ")";
    s += "</h1>\n" + "    <div>\n" + "      <p>";
    s += "Subject: " + subjects.get(i);
    s += "</p>\n" + "      <p>\n" + "        Rating: ";
    s += rating.get(i);
    s += "</p>\n" +
            "    </div>\n" +
            "  </div>\n" +
            "</section>";
    if(curr.equals("2")){
      curr = "3";
    }else{
      curr = "2";
    }
    out.println(s);
  }
%>


</body>
</html>
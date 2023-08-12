<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>stutor</title>

  <link rel="stylesheet" href="css/SearchStyle.css">
  <link rel="stylesheet" href="css/styles.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
</head>
<body>
<!-- navbar -->
<header>
    <div>
        <img src="images/main.png" alt="surati">
        <div>
            <a>Home</a>
            <a>Search</a>
            <a>For Students</a>
            <a>For Tutors</a>
        </div>
    </div>
    <div>
        <button onclick="window.location.href='AccountLoginServlet';">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM504 312V248H440c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V136c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H552v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z"/></svg>
            Login
        </button>
        <button onclick="window.location.href='AccountRegisterServlet';">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
            Register
        </button>
    </div>
</header>

<!-- form -->

<div class="main-page">
  <form class="form" action="AccountRegisterServlet" method="post">
    <h3>Register</h3>
    <h6 style="color:red;">invalid information, try again</h6>
    <input type="text" name="email" class="text-input" placeholder="Email">
    <input type="text" name="firstname" class="text-input" placeholder="First Name">
    <input type="text" name="lastname" class="text-input" placeholder="Last Name">
    <input type="text" name="username" class="text-input" placeholder="Username">
    <input type="password" name="password" class="text-input" placeholder="Password">
    <input type="password" name="repeatpass" class="text-input" placeholder="Repeat Password">
    <input type="submit" class="btn" value="Submit">
  </form>
  <img src="images/register-image.jpg" width="750px" height="500px">
</div>

</body>
</html>
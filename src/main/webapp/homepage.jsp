<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Stutor</title>
    <link rel="stylesheet" href="css/welcome.css">
    <link rel="stylesheet" href="css/headerStyle.css">


    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:ital,wght@0,100;0,300;0,400;0,500;0,700;0,900;1,100;1,300;1,400;1,500;1,700;1,900&display=swap" rel="stylesheet">
</head>
<body>

<header>
    <div>
        <img src="./images/main.png" alt="surati">
    </div>
    <div>
        <button onclick="window.location.href='LoginServlet';">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM504 312V248H440c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V136c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H552v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z"/></svg>
            Login
        </button>
        <button onclick="window.location.href='RegisterServlet';">
            <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 512 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M217.9 105.9L340.7 228.7c7.2 7.2 11.3 17.1 11.3 27.3s-4.1 20.1-11.3 27.3L217.9 406.1c-6.4 6.4-15 9.9-24 9.9c-18.7 0-33.9-15.2-33.9-33.9l0-62.1L32 320c-17.7 0-32-14.3-32-32l0-64c0-17.7 14.3-32 32-32l128 0 0-62.1c0-18.7 15.2-33.9 33.9-33.9c9 0 17.6 3.6 24 9.9zM352 416l64 0c17.7 0 32-14.3 32-32l0-256c0-17.7-14.3-32-32-32l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32l64 0c53 0 96 43 96 96l0 256c0 53-43 96-96 96l-64 0c-17.7 0-32-14.3-32-32s14.3-32 32-32z"/></svg>
            Register
        </button>
    </div>
</header>


<section class="section3">

    <h3>Unlock Your Learning Potential!</h3>
    <div>
        <div>
            <h4>Discover the Perfect Match</h4>
            <p>Find the ideal tutor who can guide you towards success. Let's embark on a journey of knowledge together!</p>
        </div>

        <div>
            <h4>Become a Guiding Light</h4>
            <p>Empower others by sharing your expertise. Join us as a tutor and illuminate the path of learning for aspiring minds!</p>
        </div>
    </div>

</section>


<section class="section2">
    <img src="./images/about.png" alt="Vibrant Classroom">
    <h4>EXPLORE TUTORING OPPORTUNITIES</h4>
    <p>At Stutor, we're here to support your learning journey. Whether you're looking for the perfect tutor to guide you or you're passionate about sharing your knowledge, we've got you covered.</p>
</section>




<section  class="section1">
    <h3>HOW IT WORKS</h3>
    <div>
        <div>
            <img src="./images/first.png" alt="Tutor Search">
            <h4>Discover Your Ideal Tutor</h4>
            <p>Search and compare online & local tutors by subject and level to find the perfect match.</p>
        </div>

        <div>
            <img src="./images/second.png" alt="Tutor Qualifications">
            <h4>Explore Tutors' Qualifications</h4>
            <p>Explore tutors' qualifications, informations about them and expertise to make an ideal choice.</p>
        </div>

        <div>
            <img src="./images/third.png" alt="Become a Tutor">
            <br>
            <h4>Inspire as a Tutor</h4>
            <p>Share your knowledge and passion by becoming a tutor. Reach eager learners through our platform.</p>
        </div>

        <div>
            <img src="./images/fourth.png" alt="Tutor Selection">
            <h4>Elevate Learning Journeys</h4>
            <p>Select your perfect tutor, whether you're seeking one or aspiring to become one.</p>
        </div>
    </div>
</section>

</body>
</html>
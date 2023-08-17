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
    <link rel="stylesheet" href="css/styles.css">
    <title>Document</title>
</head>
<body style="display: flex; flex-direction: column;">


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
        <form method="post">
            <button type="submit" name="logout" value="logout" class="default-btn">
                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 512"><!--! Font Awesome Pro 6.4.0 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license (Commercial License) Copyright 2023 Fonticons, Inc. --><path d="M96 128a128 128 0 1 1 256 0A128 128 0 1 1 96 128zM0 482.3C0 383.8 79.8 304 178.3 304h91.4C368.2 304 448 383.8 448 482.3c0 16.4-13.3 29.7-29.7 29.7H29.7C13.3 512 0 498.7 0 482.3zM504 312V248H440c-13.3 0-24-10.7-24-24s10.7-24 24-24h64V136c0-13.3 10.7-24 24-24s24 10.7 24 24v64h64c13.3 0 24 10.7 24 24s-10.7 24-24 24H552v64c0 13.3-10.7 24-24 24s-24-10.7-24-24z"/></svg>
                Log out
            </button>
        </form>
        </div>
    </header>

    <div style="display: flex; align-items: center; justify-content: center; height: 70%">
        <div class="card">
            <div class="card-body">
                <form style="background-color: white;" class="form" action="ChangePasswordServlet" method="post">
                    <h4>Change Password</h4>
                    <h6 style="color:red;">invalid information, try again</h6>
                    <input type="password" name="newpassword" class="text-input" placeholder="New Password">
                    <input type="password" name="newrepeatpass" class="text-input" placeholder="Repeat Password">
                    <button type="submit" name="updatePass" class="info-btn" style="margin-bottom: 30px;">Update Password</button>
                    <h6>Enter your new password and click on <span style="color: darkgoldenrod;">Update Password</span> to update it. Go back to your profile page when done updating your password</h6>
                    <button type="submit" name="backToProfile" class="info-btn" style="background-color: lightyellow; margin-top: 5px;">Go Back To Profile Page</button>
                  </form>
            </div>
        </div>
        
    </div>


    
</body>
</html>
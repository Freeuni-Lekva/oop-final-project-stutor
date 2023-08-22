<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto">
    <link rel="stylesheet" href="css/profile.css">
    
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/MaterialDesign-Webfont/5.3.45/css/materialdesignicons.css" integrity="sha256-NAxhqDvtY0l4xn+YVa6WjAcmd94NNfttjNsDmNatFVc=" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="css/headerStyle.css">
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
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.User" %>
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

    <%      
        User currUser = (User) session.getAttribute("currUser");
        String email = currUser.getEmail();
        String firstName = currUser.getFirstname();
        String lastName = currUser.getLastname();
        String username = currUser.getUsername();
    %>


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
                            <h4 class="font-13 text-uppercase">bio <button class="info-btn" style="font-size: 10px; padding: 3px 10px;">Change Bio</button></h4>
                            <p class="text-muted font-13 mb-3">
                                Lorem ipsum dolor sit, amet consectetur adipisicing elit. Voluptates fugit reiciendis beatae error, repellat sit libero consequuntur dolore eum tempore harum placeat illum odit eaque ullam illo itaque commodi. Iusto.
                            </p>
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
                                    <h5 class="text-muted mt-1 mb-2 fw-normal">Students</h5>
                                    <h4 class="mb-0 fw-bold">12</h4>
                                </div>
                                <div class="col-4">
                                    <h5 class="text-muted mt-1 mb-2 fw-normal">Friends</h5>
                                    <h4 class="mb-0 fw-bold">98</h4>
                                </div>
                            </div>
                        </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5>Change Profile Info</h5>
                        <form method="post"><button class="info-btn" name="changePass">Change Password</button></form>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5>Subjects Learned</h5>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5>Subjects Taught</h5>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5>Certificates</h5>
                    </div>
                </div>
                <div class="card">
                    <div class="card-body">
                        <h5>Reviews</h5>
                    </div>
                </div>
            </div>
            <div class="profile-right">
                <div class="card history">
                    <div class="card-body">
                        <h5>Learning History</h5>
                        <div class="history-content">
                            <h6 style="font-size: small; color: lightgray;">this user has no learning history</h6 style="font-size: x-small;">
                        </div>
                    </div>
                </div>
                <div class="card history">
                    <div class="card-body">
                        <h5>Teaching History</h5>
                        <div class="history-content">
                            <h6 style="font-size: small; color: lightgray;">this user has no teaching history</h6 style="font-size: x-small;">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>


    
</body>
</html>
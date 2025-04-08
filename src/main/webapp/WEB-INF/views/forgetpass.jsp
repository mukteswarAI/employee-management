<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Forgot Password</title>
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }
        body {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            display: flex;
            background: white;
            width: 80%;
            max-width: 1200px;
            border-radius: 15px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
            overflow: hidden;
        }
        .left {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: white;
            padding: 20px;
        }
        .left img {
            width: 500px; /* Reduced image size */
        }
        .right {
            flex: 1;
            padding: 40px; /* Reduced padding */
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        h2 {
            margin-bottom: 10px;
            font-size: 22px; /* Reduced font size */
        }
        p {
            font-size: 16px; /* Reduced font size */
            color: #666;
            margin-bottom: 20px;
        }
        input {
            width: 100%;
            padding: 10px; /* Reduced padding */
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 14px;
        }
        button {
            width: 100%;
            padding: 12px; /* Reduced padding */
            font-size: 16px; /* Reduced font size */
            background-color: #6c5ce7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background 0.3s;
        }
        button:hover {
            background-color: #4a3db3;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #6c5ce7;
            text-decoration: none;
            font-size: 14px;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="left">
        <img src="assets/img/svg/loginimage.svg" alt="Illustration">
    </div>
    <div class="right">
        <h2>Forgot Password</h2>
        <p>Enter your email address to reset your password.</p>
        <input type="email" id="email" placeholder="Email" name="email" >
        <button onclick="sendOtp()">Send Otp</button>
        <a href="/">Remember your password? Login</a>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
    function sendOtp() {
        var email = $("#email").val().trim();
        var emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/; // Email validation regex

        if (email === "") {
            Swal.fire({
                icon: "warning",
                title: "Oops...",
                text: "Please enter your email!",
            });
            return;
        }

        if (!emailPattern.test(email)) {
            Swal.fire({
                icon: "error",
                title: "Invalid Email",
                text: "Please enter a valid email address!",
            });
            return;
        }

        $.ajax({
            type: "POST",
            url: "/email-verify",
            data: { email: email },
            beforeSend: function() {  // 👉 "Start" - Show loading animation
                Swal.fire({
                    title: "Sending OTP...",
                    text: "Please wait while we send your OTP.",
                    allowOutsideClick: false,
                    didOpen: () => {
                        Swal.showLoading();  // Show loading indicator
                    }
                });
            },
            success: function(response) {
                Swal.fire({
                    icon: "success",
                    title: "OTP Sent!",
                    text: "Check your email for the OTP.",
                    confirmButtonText: "OK"
                }).then(() => {
                    window.location.href = "/otp-verify?email=" + encodeURIComponent(email); // Redirect with email param
                });
            },
            error: function(xhr) {
                Swal.fire({
                    icon: "error",
                    title: "Error",
                    text: xhr.responseJSON ? xhr.responseJSON.message : "Something went wrong!",
                });
            },
            complete: function() { // 👉 "Complete" - Hide loading state
                Swal.hideLoading(); // Hide loading animation (if necessary)
            }
        });
    }
</script>






</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
            margin: 0;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            display: flex;
            background: white;
            width: 80%;
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.05);
            overflow: hidden;
            padding: 20px;
        }
        .left {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .right {
            flex: 1;
            padding: 40px;
            display: flex;
            flex-direction: column;
            justify-content: center;
        }
        h2 {
            margin-bottom: 10px;
            font-size: 20px;
        }
        p {
            font-size: 14px;
            color: #666;
            margin-bottom: 10px;
        }
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #4CAF50;
            border-radius: 5px;
        }
        .otp-container {
            display: flex;
            gap: 10px;
            margin-bottom: 20px;
        }
        .otp-container input {
            width: 40px;
            height: 40px;
            text-align: center;
            font-size: 18px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #6c5ce7;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 10px;
            color: #6c5ce7;
            text-decoration: none;
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="left">
        <img src="assets/img/svg/loginimage.svg" alt="Illustration" width="200">
    </div>
    <div class="right">
        <h2>Forgot Password</h2>
        <p>Enter the email address tied to your account, we would help you reset your password</p>
            <input type="email" id="email" placeholder="Enter your email"  name="email" value="${email}" disabled>
        <p>Enter the OTP</p>
        <div class="otp-container">
            <input type="text" maxlength="1" class="otp-input">
            <input type="text" maxlength="1" class="otp-input">
            <input type="text" maxlength="1" class="otp-input">
            <input type="text" maxlength="1" class="otp-input">
        </div>
        <!-- Button to Verify OTP -->
        <button id="verify-btn" onclick="verifyOtp()" >Verify OTP</button>




        <a href="/">You remember your password? Login</a>
    </div>
</div>
<script>
    // Auto-focus next input field after entering a digit
    $(".otp-input").on("keyup", function() {
        if ($(this).val().length === 1) {
            $(this).next(".otp-input").focus();
        }
    });

    function verifyOtp() {
        var email = $("#email").val();
        var otp = $(".otp-input").map(function() { return $(this).val(); }).get().join(""); // Combine OTP inputs

        if (otp.length !== 4) {
            Swal.fire({
                icon: "warning",
                title: "Invalid OTP",
                text: "Please enter a valid 4-digit OTP.",
            });
            return;
        }

        $.ajax({
            type: "POST",
            url: "/otp-verifying", // Backend endpoint
            data: { email: email, otp: otp },
            success: function(response) {
                Swal.fire({
                    icon: "success",
                    title: "OTP Verified!",
                    text: "Redirecting to reset password...",
                    timer: 2000,
                    showConfirmButton: false
                }).then(() => {
                    window.location.href = "/reset-pass?email=" + encodeURIComponent(email); // Redirect to Reset Password page
                });
            },
            error: function(xhr) {
                Swal.fire({
                    icon: "error",
                    title: "OTP Verification Failed",
                    text: xhr.responseJSON ? xhr.responseJSON.message : "Invalid OTP. Please try again.",
                });
            }
        });
    }
</script>

</body>
</html>

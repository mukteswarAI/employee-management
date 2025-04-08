<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reset Password</title>
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
            height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            display: flex;
            background: white;
            width: 80%;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
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
        input[type="email"],
        input[type="password"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #4CAF50;
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
        <img src="assets/img/svg/loginimage.svg" alt="Illustration" width="150">
    </div>
    <div class="right">
        <h2>Reset Password</h2>
        <p>Enter your new password below</p>

        <!-- Disabled email field -->
        <input type="email" id="email" placeholder="Enter your email" value="${email}" disabled>

        <p>New Password</p>
        <input type="password" id="password" placeholder="Enter the password">

        <p>Confirm Password</p>
        <input type="password" id="confirm-password" placeholder="Re-enter the password">

        <button onclick="resetPassword()">Confirm</button>

<%--        <a href="/">Remember your password? Login</a>--%>
    </div>
</div>

<!-- Include SweetAlert -->
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<script>
    function resetPassword() {
        const email = document.getElementById('email').value;
        const password = document.getElementById('password').value;
        const confirmPassword = document.getElementById('confirm-password').value;

        if (!password) {
            Swal.fire({
                icon: "warning",
                title: "Oops...",
                text: "Please enter a new password!",
            });
            return;
        }
        if (password.length < 6) {
            Swal.fire({
                icon: "warning",
                title: "Weak Password",
                text: "Password should be at least 6 characters long.",
            });
            return;
        }
        if (password !== confirmPassword) {
            Swal.fire({
                icon: "error",
                title: "Password Mismatch",
                text: "Passwords do not match!",
            });
            return;
        }

        // Send AJAX request to reset password
        $.ajax({
            type: "POST",
            url: "/reset-pass",
            data: { email: email, password: password },
            success: function(response) {
                Swal.fire({
                    icon: "success",
                    title: "Password Reset!",
                    text: "Your password has been updated successfully. Please log in with your new password.",
                    confirmButtonText: "Go to Login"
                }).then(() => {
                    window.location.href = "/"; // Redirect to login page
                });
            },
            error: function(xhr) {
                Swal.fire({
                    icon: "error",
                    title: "Reset Failed",
                    text: xhr.responseJSON ? xhr.responseJSON.message : "Something went wrong!",
                });
            }
        });
    }
</script>

</body>
</html>
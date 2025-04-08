<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>EvoStaff Sign In</title>
<link rel="stylesheet" href="assets/css/styleslogin.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

</head>
<body>
	<div class="container">
		<div class="left-side">
			<img src="assets/img/svg/loginimage.svg" alt="Eduvance Illustration">
		</div>
		<div class="right-side">
			<h1>Sign in to EvoStaff</h1>
			<p class="tagline">Your Staffing Solution</p>

			<form id="loginForm">
				<div class="form-group">
					<label for="email">Email</label> <input type="email" id="email"
						name="email" placeholder="Email">
				</div>

				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						id="password" name="password" placeholder="Password">
				</div>

				<div class="form-group remember-forgot">
<%--					<label class="remember-me"> <input type="checkbox"--%>
<%--						id="remember" name="remember">RememberMe--%>
					</label> <a href="ForgetPass">Forgot Password?</a>
				</div>

				<button type="submit" class="sign-in-button">Sign in</button>
			</form>


		</div>
	</div>

<!-- <script src="assets/js/scriptlogin.js"></script> -->

	<script src="https://kit.fontawesome.com/your-font-awesome-kit.js"
		crossorigin="anonymous"></script>
	<script>
		$(document)
				.ready(
						function() {
							$("#loginForm")
									.submit(
											function(event) {
												event.preventDefault(); // Prevent default form submission

												var email = $("#email").val();
												var password = $("#password")
														.val();
												var rememberMe = $("#remember")
														.is(":checked"); // Get rememberMe value

												$
														.ajax({
															type : "POST",
															url : "/login", // Your Spring Boot login endpoint
															data : {
																email : email, // Use username in data (match your controller)
																password : password,
															// rememberMe: rememberMe // Include rememberMe if needed
															},
															success : function(
																	data) {
																// Handle successful login
																if (data === "redirect:/admin") { // Check redirection target
																	window.location.href = "/admin";
																} else if (data === "redirect:/hr") {
																	window.location.href = "/hr";
																} else if (data === "redirect:/employee") {
																	window.location.href = "/employee";
																} else {
																	// Handle login error (e.g., display message)
																	alert("Login failed. Please check your credentials.");
																}
															},
															error : function(
																	xhr,
																	status,
																	error) {
																// Handle AJAX error
																console
																		.error(
																				"AJAX Error:",
																				error);
																alert("An error occurred during login.");
															}
														});
											});
						});
	</script>
</body>
</html>

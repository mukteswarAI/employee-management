<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html data-bs-theme="light" lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Add Employee</title>
<!-- Include your original dashboard CSS files -->
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/styles.min.css">

<style>
.form-container {
	max-width: 800px;
	margin: 20px;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	background-color: white;
}

.error {
	color: red;
	font-size: 0.9em;
}
</style>
</head>
<body id="page-top">
	<div id="wrapper">
		<!-- Include Sidebar -->
		<%@ include file="AdminSideBar.jsp"%>

		<!-- Content Wrapper -->
		<div class="d-flex flex-column" id="content-wrapper">
			<!-- Main Content -->
			<div id="content">
				<!-- Include Top Navigation Bar -->
				<%@ include file="Navbar.jsp" %>

				<!-- Begin Page Content -->

				<div class="container-fluid">

					<div
						class="d-sm-flex justify-content-between align-items-center mb-4">
						<!--  <h3 class="text-dark mb-0">Add Employee</h3> -->
					</div>

					<!-- Add Employee Form -->

						<div class="form-container">

							<form:form action="addEmployee" modelAttribute="empForm"
								method="POST" id="employeeForm">
								<div class="mb-3">
									<label for="employeeName" class="form-label">Employee
										Name *</label> <input type="text" class="form-control"
										id="employeeName" name="employeeName" required>
								</div>

								<div class="mb-3">
									<label for="address" class="form-label">Address</label>
									<textarea class="form-control" id="address" name="address"
										rows="3"></textarea>
								</div>

								<div class="mb-3">
									<label for="phoneNo" class="form-label">Phone Number *</label> <input
										type="tel" class="form-control" id="phoneNo" name="phoneNo"
										pattern="[0-9]{10}"
										title="Please enter a valid 10-digit phone number" required>
								</div>

								<div class="mb-3">
									<label for="email" class="form-label">Email Address *</label> <input
										type="email" class="form-control" id="email" name="email"
										required>
								</div>
								<div class="mb-3">
								<label for="password" class="form-label">Password *</label>
									<input type="password" class="form-control" id="password"
										name="password" required minlength="8"
										pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$"
										title="Password must be at least 8 characters long and contain at least one letter, one number, and one special character (@$!%*#?&).">
									<span class="password-toggle" id="togglePassword"> <i
										class="fa fa-eye"></i>
									</span>
								</div>

								<div class="mb-3">
									<label for="dob" class="form-label">Date of Birth *</label> <input
										type="date" class="form-control" id="dob" name="dob" required>
								</div>

								<div class="mb-3">
									<label for="salary" class="form-label">Salary *</label> <input
										type="number" class="form-control" id="salary" name="salary"
										step="0.01" min="0" required>
								</div>



								<div class="text-center">
									<button type="submit" class="btn btn-primary">Submit</button>
									<button type="reset" class="btn btn-secondary">Reset</button>
								</div>
							</form:form>
						</div>

				</div>
			</div>

			<!-- Footer -->
			<footer class="bg-white sticky-footer">
				<div class="container my-auto">
					<div class="text-center my-auto copyright">
						<span>Copyright © Brand 2025</span>
					</div>
				</div>
			</footer>
		</div>
	</div>

	<!-- Scroll to Top Button-->
	<a class="border rounded d-inline scroll-to-top" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- Include your original dashboard scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/script.min.js"></script>
	<script src="assets/js/chart.min.min.js"></script>

	<!-- Form validation script -->
	<script>
	  const passwordInput = document.getElementById('password');
	    const togglePassword = document.getElementById('togglePassword');

	    togglePassword.addEventListener('click', function() {
	        const type = passwordInput.getAttribute('type') === 'password' ? 'text' : 'password';
	        passwordInput.setAttribute('type', type);
	        this.querySelector('i').classList.toggle('fa-eye-slash'); // Toggle icon
	    });
    </script>
</body>
</html>
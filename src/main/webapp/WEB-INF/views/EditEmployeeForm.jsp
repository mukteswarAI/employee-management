<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edit Employee</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/styles.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

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
<body>
	<div id="wrapper">
		<!-- Include Sidebar -->
		<%@ include file="AdminSideBar.jsp"%>
		<div class="container-fluid">
			<div
				class="d-sm-flex justify-content-between align-items-center mb-4">
				<h3 class="text-dark mb-0">Edit Employee</h3>
			</div>

			<!-- Edit Employee Form -->
			<div class="form-container">
				<form:form action="update" modelAttribute="employee" method="POST"
					id="employeeForm">
					<!-- Hidden field for ID -->
					<form:hidden path="employeeId" />

					<div class="mb-3">
						<label for="employeeName" class="form-label">Employee Name
							*</label>
						<form:input path="employeeName" class="form-control"
							required="required" />
					</div>

					<div class="mb-3">
						<label for="address" class="form-label">Address</label>
						<form:textarea path="address" class="form-control" rows="3" />
					</div>

					<div class="mb-3">
						<label for="phoneNo" class="form-label">Phone Number *</label>
						<form:input path="phoneNo" class="form-control"
							pattern="[0-9]{10}"
							title="Please enter a valid 10-digit phone number"
							required="required" />
					</div>

					<div class="mb-3">
						<label for="email" class="form-label">Email Address *</label>
						<form:input path="email" type="email" class="form-control"
							required="required" />
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password *</label>
						<form:input path="password" type="text" class="form-control"
							required="required" />
					</div>

					<div class="mb-3">
						<label for="dob" class="form-label">Date of Birth *</label>
						<form:input path="dob" type="date" class="form-control"
							required="required" />
					</div>

					<div class="mb-3">
						<label for="salary" class="form-label">Salary *</label>
						<form:input path="salary" type="number" class="form-control"
							step="0.01" min="0" required="required" />
					</div>

					<div class="text-center">
						<button type="submit" class="btn btn-primary">Update</button>
						<button type="reset" onclick="resetForm()"
							class="btn btn-secondary">Reset</button>
					</div>
				</form:form>
			</div>
		</div>
		<!-- Footer -->
		
	</div>
	</div>

	<!-- Optional JavaScript -->
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script>
		function resetForm() {
			$('#employeeForm').find('input:not([type="hidden"]), textarea')
					.val('');
			alert('Form has been reset!');
		}
	</script>

</body>
</html>
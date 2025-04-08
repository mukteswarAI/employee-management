<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html data-bs-theme="light" lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Add Department</title>
    <!-- Include your original dashboard CSS files -->
    <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="assets/css/styles.min.css">

    <style>
        .form-container {
            max-width: 800px;
            margin: 20px;
            padding: 20px;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
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
                <div class="d-sm-flex justify-content-between align-items-center mb-4">
                    <!--  <h3 class="text-dark mb-0">Add Employee</h3> -->
                </div>

                <!-- Add Employee Form -->
                <div class="form-container">
                    <form:form action="addDepartment" modelAttribute="deptForm" method="POST"  id="departmentForm">
                        <div class="mb-3">
                            <label for="departmentName" class="form-label">Department Name *</label>
                            <input type="text" class="form-control" id="departmentName" name="departmentName" required>
                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                            <button type="reset" class="btn btn-secondary">Reset</button>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
        <!-- <div class="mb-3">
                                      <label for="department" class="form-label">Department</label>
                                      <select class="form-select" id="department" name="department">
                                          <option value="">Select Department</option>
                                          <option value="IT">IT</option>
                                          <option value="HR">HR</option>
                                          <option value="Finance">Finance</option>
                                          <option value="Marketing">Marketing</option>
                                      </select>
                                  </div> -->

        <!-- <div class="mb-3">
            <label for="role" class="form-label">Role</label>
            <select class="form-select" id="role" name="role">
                <option value="">Select Role</option>
                <option value="Developer">Developer</option>
                <option value="Manager">Manager</option>
                <option value="HR">HR</option>
                <option value="Analyst">Analyst</option>
            </select>
        </div> -->
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
<!--   <script>
      $(document).ready(function() {
          $("#employeeForm").submit(function(event) {
              // Your existing form validation code
              var isValid = true;

              // Validate email format
              var email = $("#email").val();
              var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
              if (!emailPattern.test(email)) {
                  alert("Please enter a valid email address");
                  isValid = false;
              }

              // Validate phone number
              var phone = $("#phoneNo").val();
              if (phone.length !== 10 || isNaN(phone)) {
                  alert("Please enter a valid 10-digit phone number");
                  isValid = false;
              }

              // Validate salary
              var salary = $("#salary").val();
              if (salary <= 0) {
                  alert("Salary must be greater than 0");
                  isValid = false;
              }

              // Validate date of birth
              var dob = new Date($("#dob").val());
              var today = new Date();
              var age = today.getFullYear() - dob.getFullYear();
              if (age < 18) {
                  alert("Employee must be at least 18 years old");
                  isValid = false;
              }

              if (!isValid) {
                  event.preventDefault();              }
          });
      });
  </script> -->
</body>
</html>
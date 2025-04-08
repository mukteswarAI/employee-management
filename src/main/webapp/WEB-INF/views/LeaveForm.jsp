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
    <%@ include file="EmployeeSideBar.jsp"%>

    <!-- Content Wrapper -->
    <div class="d-flex flex-column" id="content-wrapper">
        <!-- Main Content -->
        <div id="content">
            <!-- Include Top Navigation Bar -->
            <%@include file="Navbar.jsp" %>

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <div class="d-sm-flex justify-content-between align-items-center mb-4">
                    <!--  <h3 class="text-dark mb-0">Add Employee</h3> -->
                </div>

                <!-- Add Employee Form -->
                <div class="form-container">
                    <form:form action="Request-leave"  method="POST" id="leaveRequestForm">

                        <!-- Hidden Employee ID -->
                        <input type="hidden" id="empId" name="empId" value="${sessionScope.loggedInEmployee.employeeId}">

                        <div class="mb-3">
                            <label for="leaveType" class="form-label">Leave Type *</label>
                            <select class="form-control" id="leaveType" name="leaveType" required>
                                <option value="" disabled selected>Select Leave Type</option>
                                <option value="Sick Leave">Sick Leave</option>
                                <option value="Casual Leave">Casual Leave</option>
                                <option value="Paid Leave">Paid Leave</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="description" rows="3"></textarea>
                        </div>

                        <div class="mb-3">
                            <label for="contactNumber" class="form-label">Contact Number *</label>
                            <input type="text" class="form-control" id="contactNumber" name="contactNumber" required>
                        </div>

                        <div class="mb-3">
                            <label for="fromDate" class="form-label">From Date</label>
                            <input type="date" class="form-control" id="fromDate" name="fromDate" >
                        </div>

                        <div class="mb-3">
                            <label for="toDate" class="form-label">To Date</label>
                            <input type="date" class="form-control" id="toDate" name="toDate" >
                        </div>

                        <div class="text-center">
                            <button type="submit" class="btn btn-success" style="background-color: green; border: none;">Apply</button>
                            <button type="reset" class="btn" style="border: 2px solid lightcoral; color: lightcoral;">Reset</button>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.getElementById("leaveRequestForm").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission

        Swal.fire({
            title: "Leave Request Submitted!",
            text: "Your leave request has been successfully submitted.",
            icon: "success",
            confirmButtonText: "OK"
        }).then(() => {
            this.submit(); // Submit the form after user clicks OK
        });
    });
</script>
</body>
</html>
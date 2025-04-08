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
    <style>
        .text-center {
            margin-top: 20px;
        }
        .action-btn {
            width: 150px;
            margin: 10px;
            font-size: 16px;
            padding: 10px;
        }
    </style>
</head>
<body id="page-top">
<div id="wrapper">
    <!-- Include Sidebar -->
    <%@ include file="HrSideBar.jsp"%>

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
                    <form:form action="Update-leave" modelAttribute="leaveForm" method="POST" id="leaveRequestForm">

                        <input type="hidden" id="leaveId" name="leaveId" value="${leaveForm.leaveId}">
                        <!-- Hidden Employee ID -->
                        <input type="hidden" id="empId" name="empId" value="${employeeId}">

                        <div class="mb-3">
                            <label for="leaveType" class="form-label">Leave Type</label>
                            <input type="text" value="${leaveForm.leaveType}" class="form-control" id="leaveType" name="leaveType" required>


                        </div>

                        <div class="mb-3">
                            <label for="description" class="form-label">Description</label>
                            <textarea class="form-control" id="description" name="leaveDescription" rows="3">
                                    <c:out value="${leaveForm.leaveDescription}" />
                            </textarea>
                        </div>

                        <div class="mb-3">
                            <label for="contactNumber" class="form-label">Contact Number *</label>
                            <input type="text" value="${leaveForm.contactNumber} "class="form-control" id="contactNumber" name="contactNumber" required>
                        </div>


                        <div class="text-center">
                            <button type="button" class="btn btn-success action-btn" id="approveBtn">Approve</button>
                            <button type="button" class="btn btn-danger action-btn" id="denyBtn">Deny</button>
                        </div>

                        <!-- Hidden Field for Status -->
                        <input type="hidden" id="status" name="status">

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
<!-- SweetAlert2 for Approval & Denial -->
<!-- SweetAlert2 for Approval & Denial -->
<script>
    document.getElementById("approveBtn").addEventListener("click", function() {
        if (confirm("Are you sure you want to approve this leave request?")) {
            document.getElementById("status").value = "Approved";
            alert("Leave Approved!");
            document.getElementById("leaveRequestForm").submit();
        }
    });

    document.getElementById("denyBtn").addEventListener("click", function() {
        let reason = prompt("Enter reason for denial:");
        if (reason !== null && reason.trim() !== "") {
            document.getElementById("status").value = "Denied";
            alert("Leave Denied!\nReason: " + reason);
            document.getElementById("leaveRequestForm").submit();
        } else {
            alert("Denial reason is required.");
        }
    });
</script>





</body>
</html>
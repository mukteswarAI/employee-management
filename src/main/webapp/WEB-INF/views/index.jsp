<!DOCTYPE html>
<%@page import="org.ems.employee.model.Employee"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Java code to check session
    Employee loggedInEmployee = (Employee) session.getAttribute("loggedInEmployee");
    if (loggedInEmployee == null) {
        response.sendRedirect("/login");
        return;
    }
%>
<html data-bs-theme="light" lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Dashboard - Brand</title>
<link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
<link rel="stylesheet" href="assets/css/styles.min.css">
</head>
<body id="page-top">
	<div id="wrapper">
		<%@ include file="AdminSideBar.jsp" %>
		<div class="d-flex flex-column" id="content-wrapper">
			<div id="content">
				<%@ include file="Navbar.jsp" %>
				<div class="container-fluid">
					<div
						class="d-sm-flex justify-content-between align-items-center mb-4">
						<h3 class="text-dark mb-0">Admin Dashboard</h3>
						<a class="btn btn-primary btn-sm d-none d-sm-inline-block"
							role="button" href="generate-report"><i
							class="fas fa-download fa-sm text-white-50"></i>&nbsp;Generate
							Report</a>
					</div>
					<div class="row">
						<div class="col-md-6 col-xl-3 mb-4">
							<div class="card shadow border-left-primary py-2">
								<div class="card-body">
									<div class="row g-0 align-items-center">
										<div class="col me-2">
											<div class="text-uppercase text-primary fw-bold text-xs mb-1">
												<span>Total Employees</span>
											</div>
											<div class="text-dark fw-bold h5 mb-0">
												<span>${empcount}</span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-calendar fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-3 mb-4">
							<div class="card shadow border-left-success py-2">
								<div class="card-body">
									<div class="row g-0 align-items-center">
										<div class="col me-2">
											<div class="text-uppercase text-success fw-bold text-xs mb-1">
												<span>Total Departments</span>
											</div>
											<div class="text-dark fw-bold h5 mb-0">
												<span>${deptcount}</span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-3 mb-4">
							<div class="card shadow border-left-info py-2">
								<div class="card-body">
									<div class="row g-0 align-items-center">
										<div class="col me-2">
											<div class="text-uppercase text-info fw-bold text-xs mb-1">
												<span>Tasks</span>
											</div>
											<div class="row g-0 align-items-center">
												<div class="col-auto">
													<div class="text-dark fw-bold h5 mb-0 me-3">
														<span>50%</span>
													</div>
												</div>
												<div class="col">
													<div class="progress progress-sm">
														<div class="progress-bar bg-info" aria-valuenow="50"
															aria-valuemin="0" aria-valuemax="100" style="width: 50%;">
															<span class="visually-hidden">50%</span>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-md-6 col-xl-3 mb-4">
							<div class="card shadow border-left-warning py-2">
								<div class="card-body">
									<div class="row g-0 align-items-center">
										<div class="col me-2">
											<div class="text-uppercase text-warning fw-bold text-xs mb-1">
												<span>Total Roles</span>
											</div>
											<div class="text-dark fw-bold h5 mb-0">
												<span>${rolecount}</span>
											</div>
										</div>
										<div class="col-auto">
											<i class="fas fa-comments fa-2x text-gray-300"></i>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-7 col-xl-8">
							<div class="card shadow mb-4">
								<div
									class="card-header d-flex justify-content-between align-items-center">
									<h6 class="text-primary fw-bold m-0">Earnings Overview</h6>
									<div class="dropdown no-arrow">
										<button class="btn btn-link btn-sm dropdown-toggle"
											aria-expanded="false" data-bs-toggle="dropdown" type="button">
											<i class="fas fa-ellipsis-v text-gray-400"></i>
										</button>
										<div
											class="dropdown-menu shadow dropdown-menu-end animated--fade-in">
											<p class="text-center dropdown-header">dropdown header:</p>
											<a class="dropdown-item" href="#">&nbsp;Action</a><a
												class="dropdown-item" href="#">&nbsp;Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">&nbsp;Something else
												here</a>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="chart-area">
										<canvas
											data-bss-chart="{&quot;type&quot;:&quot;line&quot;,&quot;data&quot;:{&quot;labels&quot;:[&quot;Jan&quot;,&quot;Feb&quot;,&quot;Mar&quot;,&quot;Apr&quot;,&quot;May&quot;,&quot;Jun&quot;,&quot;Jul&quot;,&quot;Aug&quot;],&quot;datasets&quot;:[{&quot;label&quot;:&quot;Earnings&quot;,&quot;fill&quot;:true,&quot;data&quot;:[&quot;0&quot;,&quot;10000&quot;,&quot;5000&quot;,&quot;15000&quot;,&quot;10000&quot;,&quot;20000&quot;,&quot;15000&quot;,&quot;25000&quot;],&quot;backgroundColor&quot;:&quot;rgba(78, 115, 223, 0.05)&quot;,&quot;borderColor&quot;:&quot;rgba(78, 115, 223, 1)&quot;}]},&quot;options&quot;:{&quot;maintainAspectRatio&quot;:false,&quot;legend&quot;:{&quot;display&quot;:false,&quot;labels&quot;:{&quot;fontStyle&quot;:&quot;normal&quot;}},&quot;title&quot;:{&quot;fontStyle&quot;:&quot;normal&quot;},&quot;scales&quot;:{&quot;xAxes&quot;:[{&quot;gridLines&quot;:{&quot;color&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;zeroLineColor&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;drawBorder&quot;:false,&quot;drawTicks&quot;:false,&quot;borderDash&quot;:[&quot;2&quot;],&quot;zeroLineBorderDash&quot;:[&quot;2&quot;],&quot;drawOnChartArea&quot;:false},&quot;ticks&quot;:{&quot;fontColor&quot;:&quot;#858796&quot;,&quot;fontStyle&quot;:&quot;normal&quot;,&quot;padding&quot;:20}}],&quot;yAxes&quot;:[{&quot;gridLines&quot;:{&quot;color&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;zeroLineColor&quot;:&quot;rgb(234, 236, 244)&quot;,&quot;drawBorder&quot;:false,&quot;drawTicks&quot;:false,&quot;borderDash&quot;:[&quot;2&quot;],&quot;zeroLineBorderDash&quot;:[&quot;2&quot;]},&quot;ticks&quot;:{&quot;fontColor&quot;:&quot;#858796&quot;,&quot;fontStyle&quot;:&quot;normal&quot;,&quot;padding&quot;:20}}]}}}"></canvas>
									</div>
								</div>
							</div>
						</div>
						<div class="col-lg-5 col-xl-4">
							<div class="card shadow mb-4">
								<div
									class="card-header d-flex justify-content-between align-items-center">
									<h6 class="text-primary fw-bold m-0">Revenue Sources</h6>
									<div class="dropdown no-arrow">
										<button class="btn btn-link btn-sm dropdown-toggle"
											aria-expanded="false" data-bs-toggle="dropdown" type="button">
											<i class="fas fa-ellipsis-v text-gray-400"></i>
										</button>
										<div
											class="dropdown-menu shadow dropdown-menu-end animated--fade-in">
											<p class="text-center dropdown-header">dropdown header:</p>
											<a class="dropdown-item" href="#">&nbsp;Action</a><a
												class="dropdown-item" href="#">&nbsp;Another action</a>
											<div class="dropdown-divider"></div>
											<a class="dropdown-item" href="#">&nbsp;Something else
												here</a>
										</div>
									</div>
								</div>
								<div class="card-body">
									<div class="chart-area">
										<canvas
											data-bss-chart="{&quot;type&quot;:&quot;doughnut&quot;,&quot;data&quot;:{&quot;labels&quot;:[&quot;Direct&quot;,&quot;Social&quot;,&quot;Referral&quot;],&quot;datasets&quot;:[{&quot;label&quot;:&quot;&quot;,&quot;backgroundColor&quot;:[&quot;#4e73df&quot;,&quot;#1cc88a&quot;,&quot;#36b9cc&quot;],&quot;borderColor&quot;:[&quot;#ffffff&quot;,&quot;#ffffff&quot;,&quot;#ffffff&quot;],&quot;data&quot;:[&quot;50&quot;,&quot;30&quot;,&quot;15&quot;]}]},&quot;options&quot;:{&quot;maintainAspectRatio&quot;:false,&quot;legend&quot;:{&quot;display&quot;:false,&quot;labels&quot;:{&quot;fontStyle&quot;:&quot;normal&quot;}},&quot;title&quot;:{&quot;fontStyle&quot;:&quot;normal&quot;}}}"></canvas>
									</div>
									<div class="text-center small mt-4">
										<span class="me-2"><i
											class="fas fa-circle text-primary"></i>&nbsp;Direct</span><span
											class="me-2"><i class="fas fa-circle text-success"></i>&nbsp;Social</span><span
											class="me-2"><i class="fas fa-circle text-info"></i>&nbsp;Refferal</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-6 mb-4">
							
							
						</div>
						
					</div>
				</div>
			</div>
			
		</div>
		<a class="border rounded d-inline scroll-to-top" href="#page-top"><i
			class="fas fa-angle-up"></i></a>
	</div>
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/bootstrap/js/bootstrap.min.js"></script>
	<script src="assets/js/script.min.js"></script>
	<script src="assets/js/chart.min.min.js"></script>
</body>
</html>
<%@page import="java.util.List"%>
<%@page import="com.letslearn.DBcon.DbCon"%>
<%@page import="com.letslearn.Dao.PayrollDao"%>
<%@page import="com.letslearn.Modal.*"%>
<%
PayrollDao payrollDao = new PayrollDao(DbCon.getConnection());
List<Payroll> payrolls = payrollDao.getAllPayrolls();


%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Dashboard - Mazer Admin Dashboard</title>

<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400;600;700;800&display=swap"
	rel="stylesheet">
<link rel="stylesheet" href="assets/css/bootstrap.css">

<link rel="stylesheet" href="assets/vendors/iconly/bold.css">
<link rel="stylesheet" href="assets/vendors/simple-datatables/style.css">

<link rel="stylesheet"
	href="assets/vendors/perfect-scrollbar/perfect-scrollbar.css">
<link rel="stylesheet"
	href="assets/vendors/bootstrap-icons/bootstrap-icons.css">
<link rel="stylesheet" href="assets/css/app.css">
<link rel="shortcut icon" href="assets/images/favicon.svg"
	type="image/x-icon">
</head>

<body>
	<div id="app">
		<div id="sidebar" class="active">
			<div class="sidebar-wrapper active">
				<div class="sidebar-header px-0 pb-0">
					<div class="d-flex justify-content-between">
						<div>
							<img src="assets/images/logo/logo.png" class="h-100 w-100"
								alt="Logo" srcset="">
						</div>
						<div class="toggler">
							<a href="#" class="sidebar-hide d-xl-none d-block"><i
								class="bi bi-x bi-middle"></i></a>
						</div>
					</div>
				</div>
				<div class="sidebar-menu">
					<ul class="menu px-3">
						<li class="sidebar-title">Admin Dashboard</li>

						<li class="sidebar-item  "><a href="hrManagement.jsp"
							class='sidebar-link'> <i class="bi bi-grid-fill"></i> <span>HR
									Management</span>
						</a></li>
						<li class="sidebar-item  active"><a
							href="payrollManagement.jsp" class='sidebar-link'> <i
								class="bi bi-grid-1x2-fill"></i> <span>Payroll Management</span>
						</a></li>

						<li class="sidebar-item  "><a href="welfareManagement.jsp"
							class='sidebar-link'> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>Customer
									Management</span>
						</a></li>
						<li class="sidebar-item "><a
							href="adminManagement.jsp" class='sidebar-link'> <i
								class="bi bi-grid-fill"></i> <span>Admin
									Management</span>
						</a></li>
						<li class="sidebar-title">Profile</li>

						<li class="sidebar-item  "><a href="LogoutServlet"
							class='sidebar-link'> <i class="bi bi-life-preserver"></i> <span>Logout</span>
						</a></li>
					</ul>
				</div>
				<button class="sidebar-toggler btn x">
					<i data-feather="x"></i>
				</button>
			</div>
		</div>
		<div id="main">
			<header class="mb-3">
				<a href="#" class="burger-btn d-block d-xl-none"> <i
					class="bi bi-justify fs-3"></i>
				</a>
			</header>

			<div class="page-heading">
				<h3>Payroll Management</h3>
			</div>
			<section class="section">
				<div class="card">
					<div class="card-header d-flex justify-content-between">
						<h4>Payroll List</h4>
						<div>
							<button class="btn btn-primary" data-bs-toggle="modal"
								data-bs-target="#staticBackdrop">Add Payroll</button>
						</div>
					</div>
					<div class="card-body">
						<table class="table table-hover" id="table1">
							<thead>
								<tr>
									<th>Name</th>
									<th>Email</th>
									<th>Salary</th>
									<th>Bonus</th>
									<th>Payment For</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
								<%
								for (Payroll payroll : payrolls) {
								%>
								<tr>
									<td><%=payroll.getName()%></td>
									<td><%=payroll.getEmail()%></td>
									<td>Rs <%=payroll.getSalary()%>.00</td>
									<td>Rs <%=payroll.getBonus()%>.00</td>
									<td><%=payroll.getPaymentFor()%></td>
									<td><a
										href="PayrollServlet?action=delete&email=<%=payroll.getEmail()%>">
											<span class="badge bg-danger py-2" style="cursor: pointer;">Delete</span>
									</a> <a href="#" class="edit-link" data-bs-toggle="modal"
										data-bs-target="#updateStaticBackdrop"
										data-payroll-name="<%=payroll.getName()%>"
										data-payroll-email="<%=payroll.getEmail()%>"
										data-payroll-salary="<%=payroll.getSalary()%>"
										data-payroll-bonus="<%=payroll.getBonus()%>"
										data-payroll-sub="<%=payroll.getPaymentFor()%>"> <span
											class="badge bg-warning py-2" style="cursor: pointer;">Edit</span>
									</a></td>
								</tr>



								<%
								}
								%>

							</tbody>
						</table>
					</div>
				</div>
			</section>

			<!--Update Modal -->
			<div class="modal fade" id="updateStaticBackdrop" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="updateStaticBackdrop">Update
								Payroll</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form class="row g-3 needs-validation" action="PayrollServlet"
								method="POST">
								<input type="hidden" value="update" name="action" />
								<div class="row">
									<div class="col-md-6 position-relative">
										<label for="validationTooltip01" class="form-label">First
											name</label> <input type="text" name="fname" class="form-control"
											id="payrollName" placeholder="Mark" required
											title="Enter Name">

									</div>
									<div class="col-md-6 position-relative">
										<label for="validationTooltip02" class="form-label">PaymentFor</label>
										<input type="text" name="paymentFor" class="form-control"
											id="payrollPaymentFor" placeholder="Otto" required>

									</div>
								</div>
								<div class="col-md-6 d-none position-relative">
									<label for="validationTooltipUsername" class="form-label">Email</label>
									<div class="input-group has-validation">
										<input type="email" class="form-control" id="payrollEmail02"
											name="email">

									</div>
								</div>
								<div class="row">
									<div class="col-md-6 position-relative">
										<label for="validationTooltip06" class="form-label">Salary</label>
										<input type="text" class="form-control" required name="salary"
											title="Enter salary number" id="payrollSalary" pattern="^\d+$"
											maxlength="10">

									</div>
									<div class="col-md-6 position-relative mx-auto">
										<label for="validationTooltip05" class="form-label">Bonus</label>
										<input type="text" class="form-control" name="bonus" required
											title="Enter bonus" id="payrollBonus" pattern="^\d+$"
											maxlength="12">

									</div>
								</div>
								
								<div class="d-flex justify-content-end">
									<button type="button" class="btn btn-secondary mx-3"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Add New
								Payroll</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form class="row g-3 needs-validation" action="PayrollServlet"
								method="POST">
								<input type="hidden" value="addPayroll" name="action" />
								<div class="col-md-6 position-relative">
									<label for="validationTooltip01" class="form-label">First
										name</label> <input type="text" name="fname" class="form-control"
										placeholder="Mark" required title="Enter Name">

								</div>
								<div class="col-md-6 position-relative">
									<label for="validationTooltip02" class="form-label">PaymentFor</label>
									<input type="text" name="paymentFor" class="form-control"
										id="validationTooltip02" placeholder="Otto" required>

								</div>
								<div class="col-md-6 position-relative">
									<label for="validationTooltipUsername" class="form-label">Email</label>
									<div class="input-group has-validation">
										<input type="email" class="form-control" name="email" required
											title="Enter Email">

									</div>
								</div>
								<div class="col-md-6 position-relative">
									<label for="validationTooltip06" class="form-label">Salary</label>
									<input type="text" class="form-control"
										id="validationTooltip05" required name="salary"
										title="Enter Phone number" pattern="^\d+$" maxlength="10">

								</div>

								<div class="col-md-6 position-relative">
									<label for="validationTooltip05" class="form-label">Bonus</label>
									<input type="text" class="form-control" name="bonus"
										id="validationTooltip05" required title="Enter Bonus"
										pattern="^\d+$" maxlength="12">

								</div>
								<div class="d-flex justify-content-end">
									<button type="button" class="btn btn-secondary mx-3"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>
	<script src="assets/vendors/perfect-scrollbar/perfect-scrollbar.min.js"></script>
	<script src="assets/js/bootstrap.bundle.min.js"></script>
	<script src="assets/vendors/simple-datatables/simple-datatables.js"></script>

	<script>
		// Simple Datatable
		let table1 = document.querySelector('#table1');
		let dataTable = new simpleDatatables.DataTable(table1);
	</script>

	<script src="assets/js/main.js"></script>
	<script>
        document.querySelectorAll('.edit-link').forEach(link => {
            link.addEventListener('click', function () {
                const modal = document.getElementById('updateStaticBackdrop');
                const payrollName = link.getAttribute('data-payroll-name');
                const payrollEmail = link.getAttribute('data-payroll-email');
                const payrollBonus = link.getAttribute('data-payroll-bonus');
                const payrollPaymentFor = link.getAttribute('data-payroll-sub');
                const payrollSalary = link.getAttribute('data-payroll-salary');

                // Update modal content with payroll details
                document.getElementById('payrollName').value = payrollName;
                document.getElementById('payrollSalary').value = payrollSalary;
                document.getElementById('payrollBonus').value = payrollBonus;
                document.getElementById('payrollPaymentFor').value = payrollPaymentFor;
                document.getElementById('payrollEmail02').value = payrollEmail;
            });
        });
    </script>
</body>

</html>
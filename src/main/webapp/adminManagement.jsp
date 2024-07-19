<%@page import="com.letslearn.Dao.nAdminDao"%>
<%@page import="java.util.List"%>
<%@page import="com.letslearn.DBcon.DbCon"%>
<%@page import="com.letslearn.Modal.nAdmin"%>
<%
nAdminDao adminDao = new nAdminDao(DbCon.getConnection());
List<nAdmin> admins = adminDao.getAllAdmins();
%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Admin Dashboard</title>

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
						<li class="sidebar-item"><a href="hrManagement.jsp"
							class='sidebar-link'> <i class="bi bi-grid-fill"></i> <span>HR
									Management</span>
						</a></li>
						<li class="sidebar-item  "><a href="payrollManagement.jsp"
							class='sidebar-link'> <i class="bi bi-grid-1x2-fill"></i> <span>Payroll
									Management</span>
						</a></li>

						<li class="sidebar-item  "><a
							href="welfareManagement.jsp" class='sidebar-link'> <i
								class="bi bi-file-earmark-spreadsheet-fill"></i> <span>User
									Management</span>
						</a></li>
						<li class="sidebar-item active"><a
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
				<h3>Admin Management</h3>
			</div>
			<section class="section">
				<div class="card">
					<div class="card-header d-flex justify-content-between">
						<h4>Admin List</h4>
						<div>
							<button class="btn btn-primary" data-bs-toggle="modal"
								data-bs-target="#staticBackdrop">Add Admins</button>
						</div>
					</div>

					<div class="card-body">
						<table class="table table-hover" id="table1">
							<thead>
								<tr>
									<th>Admin Name</th>
									<th>NIC</th>
									<th>Department</th>
									<th>Email</th>
									<th>Telephone</th>
									<th>Status</th>
								</tr>
							</thead>
							<tbody>
                <% for (nAdmin admin : admins) { %>
                <tr>
                    <td><%= admin.getAdminName() %></td>
                    <td><%= admin.getNic() %></td>
                    <td><%= admin.getDepartment() %></td>
                    <td><%= admin.getEmail() %></td>
                    <td><%= admin.getTelephone() %></td>
                    <td>
                        <a href="AdminServlet?action=delete&id=<%= admin.getId() %>" class="btn btn-danger">Delete</a>
                        <a href="#" class="edit-link" data-bs-toggle="modal"
										data-bs-target="#updateStaticBackdrop"
										data-admin-id="<%=admin.getId()%>"
										data-admin-name="<%=admin.getAdminName()%>"
										data-admin-nic="<%=admin.getNic()%>"
										data-admin-department="<%=admin.getDepartment()%>"
										data-admin-email="<%=admin.getEmail()%>"
										data-admin-telephone="<%=admin.getTelephone()%>"> <span
										class="badge bg-warning py-2" style="cursor: pointer;">Edit</span>
						</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
						</table>
					</div>
				</div>
			</section>
	 <div class="modal fade" id="updateStaticBackdrop"
				data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="updateStaticBackdrops">Edit
								Admins</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form class="row g-3 needs-validation" action="AdminServlet"
								method="POST">
								<input type="hidden" value="update" name="action" /> 
								<input type="hidden" name="id" id="adminId"/>
								<div class="row">
								
									<div class="col-md-12 position-relative">
										<label for="validationTooltip02" class="form-label">
											Admin name</label> <input type="text" name="adminName" id="adminName"
											class="form-control" required title="Enter Company">

									</div>
								</div>


								<div class="row">
									<div class="col-md-12 position-relative d-none">
										<label for="validationTooltipUsername" class="form-label">
											Email</label>
										<div class="input-group has-validation">
											<input type="email" class="form-control" id="email"
												name="email" title="Enter Email">

										</div>
									</div>
									
								</div>

								<div class="row">
									<div class="col-md-12 position-relative">
										<label for="validationTooltip03" class="form-label">
											NIC</label> <input type="text" class="form-control" required
											pattern="^\d{12}$" maxlength="12" title="Enter NIC"
											id="nic" name="nic">
									</div>
									
								</div>
								<div class="row">
									<div class="col-md-6 position-relative">
										<label for="validationTooltip05" class="form-label">Telephone</label>
										<input type="text" class="form-control" required
											pattern="^\d{10}$" maxlength="10" name="telephone"
											id="tel" title="Enter Phone number">

									</div>
									<div class="col-md-6 position-relative">
										<label for="validationTooltip05" class="form-label">Department
											</label><select class="form-select" id="department"
											name="department" required>
											<option value="Hr">HR Department</option>
											<option value="Dep1">1</option>
											<option value="Dep2">2</option>
											<option value="Dep3">3</option>
										</select>
									</div>
								</div>
								<div class="mt-5 d-flex justify-content-end">
									<button type="button" class="btn btn-secondary mx-3"
										data-bs-dismiss="modal">Close</button>
									<button type="submit" class="btn btn-primary">Submit</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- modalEnds Here -->
			<!-- Modal -->
			<div class="modal fade" id="staticBackdrop" data-bs-backdrop="static"
				data-bs-keyboard="false" tabindex="-1"
				aria-labelledby="staticBackdropLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="staticBackdropLabel">Add New
								Admins</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">
							<form class="row g-3 needs-validation" action="AdminServlet"
								method="POST">
								<input type="hidden" value="addAdmin" name="action" />
								
								<div class="col-md-12 position-relative">
									<label for="validationTooltip02" class="form-label">Admin name</label> 
									<input type="text" class="form-control" name="adminName"
										required title="Enter Name">

								</div>
								<div class="col-md-12 position-relative">
									<label for="validationTooltipUsername" class="form-label">
										Email</label>
									<div class="input-group has-validation">
										<input type="email" class="form-control" required
											name="email" title="Enter Email">

									</div>
								</div>
								
								<div class="col-md-6 position-relative">
									<label for="validationTooltip03" class="form-label">
										NIC</label> <input type="text" class="form-control" required
										name="nic" pattern="^\d{12}$" maxlength="12"
										title="Enter NIC">
								</div>
								

								<div class="col-md-6 position-relative">
									<label for="validationTooltip05" class="form-label">Telephone</label>
									<input type="text" class="form-control" required
										pattern="^\d{10}$" maxlength="10" title="Enter Phone number"
										name="telephone">

								</div>
								<div class="col-md-6 position-relative">
									<label for="validationTooltip05" class="form-label">Department
										</label> <select class="form-select" name="department" required>
										<option value="Hr">HR Department</option>
											<option value="Dep1">1</option>
											<option value="Dep2">2</option>
											<option value="Dep3">3</option>
									</select>
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
		// Wait for the document to be fully loaded
		document.addEventListener('DOMContentLoaded', function() {
    // Simple Datatable
    let table1 = document.querySelector('#table1');
    let dataTable = new simpleDatatables.DataTable(table1);
    
    document.querySelectorAll('.edit-link').forEach(link => {
        link.addEventListener('click', function() {
            const modal = document.getElementById('updateStaticBackdrop');
            const pid = link.getAttribute('data-admin-id');
            const adminName = link.getAttribute('data-admin-name');
            const email = link.getAttribute('data-admin-email');
            const tel = link.getAttribute('data-admin-telephone');
            const department = link.getAttribute('data-admin-department');
            const nic = link.getAttribute('data-admin-nic');

            document.getElementById('adminId').value = pid;
            document.getElementById('adminName').value = adminName;
            document.getElementById('email').value = email;
            document.getElementById('tel').value = tel;
            document.getElementById('department').value = department;
            document.getElementById('nic').value = nic;
        });
    });
});


	</script>

	<script src="assets/js/main.js"></script>
	<!-- Your HTML code above -->

	<!-- JavaScript to update modal content when "Edit" link is clicked -->
	<script>
	
</script>
	<!-- The rest of your HTML code -->

</body>

</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Manage Todo list</title>
</head>
<body>

	<script>
		function onTodoDelete(oid) {
			document.getElementById("method").value = "DELETE_TODO";
			setOidAndSubmit(oid);
		}
		function onTodoComplete(oid) {
			document.getElementById("method").value = "UPDATE_COMPLETE_TODO";
			setOidAndSubmit(oid);
		}
		function onTodoActive(oid) {
			document.getElementById("method").value = "UPDATE_ACTIVE_TODO";
			setOidAndSubmit(oid);
		}
		function setOidAndSubmit(oid) {
			document.getElementById("oid").value = oid;
			document.getElementById("todolist").submit();
		}
	</script>

	<%
		String message = (String) request.getAttribute("MESSAGE");
		String errorMessage = (String) request.getAttribute("ERROR_MESSAGE");

		if (message != null) {
	%>
	<div class="alert alert-primary" role="alert">
		<%=message%>
	</div>
	<%
		}

		if (errorMessage != null) {
	%>
	<div class="alert alert-danger" role="alert">
		<%=errorMessage%>
	</div>
	<%
		}
	%>

	<div class="container">
		<form id="todolist" action="todolist" method="post">
			<div class="row">
				<div class="col">
					<h1>Todo's list</h1>
				</div>
			</div>
			<div class="row">
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Completed</th>
							<th scope="col">Description</th>
							<th scope="col">Days</th>
							<th scope="col">Active</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td scope="row"><input type="checkbox" name="completed"
								value=""></td>
							<td>Description</td>
							<td>Days</td>
							<td><input type="button" value="Active"></td>
							<td><input type="button" value="Delete"> &nbsp;<input
								type="hidden" id="method" name="method"><input
								type="hidden" id="oid" name="oid"></td>
						</tr>

					</tbody>
				</table>
			</div>

		</form>
	</div>

	<div class="container">
		<form id="todoListAdd" action="todolist" method="post">
			<div class="row">
				<div class="col">
					<h1>Add Todo</h1>
				</div>
				<div class="col">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="description">Description</label>
				</div>
				<div class="col">
					<textarea id="description" name="description"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="noOfDays">No. of days</label>
				</div>
				<div class="col">
					<input type="text" id="noOfDays" name="noOfDays">
				</div>
			</div>
			<div class="row">
				<div class="col">
					&nbsp;<input type="hidden" id="method" name="method"
						value='INSERT_TODO'>
				</div>
				<div class="col">
					<input type="submit" value="ADD">
				</div>
			</div>
		</form>
	</div>

</body>
</html>
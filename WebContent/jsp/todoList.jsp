<%@ page import="java.util.List"%>
<%@ page import="com.veera.bean.Todo"%>
<%@ page import="com.veera.bean.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js"></script>
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
		function logoutUser() {
			window.location.href="./login?logout=LOGOUT"
		}
	</script>

	<%
		User user = (User) request.getSession().getAttribute("LOGIN_USER");
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-4">&nbsp;</div>
			<div class="col-sm-4">&nbsp;</div>
			<div class="col-sm-4 align-items-end">
				<%=user.getUserName()%>&nbsp;<input type="button" value="Logout" onClick="logoutUser()" />
			</div>
		</div>
	</div>
	<div class="container">
		<form id="todolist" action="todoList" method="post">
			<div class="row">
				<div class="col-sm-5">
					<h1>Todo's list</h1>
				</div>
			</div>
			<div class="row">
				<table class="table table-sm table-bordered table-hover">
					<thead class="thead-inverse">
						<tr>
							<th scope="col">Completed</th>
							<th scope="col">Description</th>
							<th scope="col">Days</th>
							<th scope="col">Active</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<tbody>
						<%
							List<Todo> todoList = (List<Todo>) request.getAttribute("TODOLIST");
							if (todoList != null && todoList.size() > 0) {
								for (Todo todo : todoList) {
						%>
						<tr>
							<td scope="row"><input type="checkbox" name="completed"
								<%=todo.isCompleted() ? "checked" : ""%>
								value="<%=todo.getOid()%>"
								onChange="onTodoComplete(<%=todo.getOid()%>)"></td>
							<td><%=todo.getDescription()%></td>
							<td><%=todo.getNumberOfDays()%></td>
							<td><input type="button"
								value='<%=todo.isActive() ? "Deactivate" : "Activate"%>'
								onClick="onTodoActive(<%=todo.getOid()%>)"></td>
							<td><input type="button" value="Delete"
								onclick="onTodoDelete(<%=todo.getOid()%>)"> &nbsp;<input
								type="hidden" id="method" name="method"><input
								type="hidden" id="oid" name="oid"></td>
						</tr>
						<%
							}
							} else {
						%>
						<tr>
							<td scope="row">&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
							<td>&nbsp;</td>
						</tr>

						<%
							}
						%>

					</tbody>
				</table>
			</div>

		</form>
	</div>

	<div class="container">
		<form id="todoListAdd" action="todoList" method="post">
			<div class="row">
				<div class="col-sm-4">
					<h1>Add Todo</h1>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label for="description">Description</label>
				</div>
				<div class="col-sm-2">
					<textarea id="description" name="description"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					<label for="noOfDays">No. of days</label>
				</div>
				<div class="col-sm-2">
					<input type="text" id="noOfDays" name="noOfDays">
				</div>
			</div>
			<div class="row">
				<div class="col-sm-2">
					&nbsp;<input type="hidden" id="method" name="method"
						value='INSERT_TODO'>
				</div>
				<div class="col-sm-2">
					<input type="submit" value="ADD">
				</div>
			</div>
		</form>
	</div>

</body>
</html>
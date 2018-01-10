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
<title>ToDo List</title>
</head>
<body>
	<div class="container">
		<form action="login" method="post">
			<div class="row">
				<div class="col">
					<h1>Login</h1>
				</div>
				<div class="col">&nbsp;</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="user_name">User Name</label>
				</div>
				<div class="col">
					<input type="text" id="user_name" name="user_name">
				</div>
			</div>
			<div class="row">
				<div class="col">
					<label for="password">Password</label>
				</div>
				<div class="col">
					<input type="password" id="password" name="password">
				</div>
			</div>
			<div class="row">
				<div class="col">
					&nbsp;<input type="hidden" id="method" name="method" value='LOGIN'>
				</div>
				<div class="col">
					<input type="submit" value="LOGIN">
				</div>
			</div>
		</form>
	</div>
</body>
</html>
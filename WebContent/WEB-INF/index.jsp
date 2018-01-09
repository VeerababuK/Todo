<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ToDo List</title>
</head>
<body>
	<form action="login">
	<div></div><div></div>
		<div><label for="user_name">User Name</label></div>
		<div>
			<input type="text" id="user_name" name="user_name">
		</div>
		<div><label for="password">Password</label></div>
		<div>
			<input type="password" id="password" name="password">
		</div>
		<div><input type="hidden" id="method" name="method" value='login'></div>
		<div>
			<input type="submit" value="LOGIN">
		</div>
	</form>
</body>
</html>
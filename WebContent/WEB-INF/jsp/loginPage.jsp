<!DOCTYPE html>

<html>

<head>
<title>Fishing for Likes</title>
<link rel="stylesheet" type="text/css" href="css/loginPage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script src="js/loginPage.js"></script>
</head>

<body>
	<div id="title">Fishing for Likes</div>

	<div id="loginFormDiv">
		<form id="loginForm">
			Email<br> <input type="text" id="loginEmail" name="loginEmail"><div id='loginEmailError'></div> <br> 
			<br>
			Password<br> <input type="password" id="loginPassword" name="loginPassword"><div id='loginPasswordError'></div> <br>
			<br>
			<input type="submit" value="Login">
		</form>
		<br>
		<div id="notRegisteredDiv">
			Not Registered?
			<button onclick="loadRegisterPage()">Register</button>
		</div>
	</div>

	<div id="registerDiv"></div>

</body>

</html>
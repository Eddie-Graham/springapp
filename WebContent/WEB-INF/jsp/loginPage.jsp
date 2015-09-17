<!DOCTYPE html>

<html>

<head>
<title>Fishing for Likes</title>
<link rel="stylesheet" type="text/css" href="css/loginPage.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
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

	<div id="registrationFormDiv">
		<form id="registerForm">
				Username<br> 
				<input type="text" id="username" name="username"><div id='usernameError'></div><br>
				<br>
				Email<br> 
				<input type="text" id="email" name="email"><div id='emailError'></div><br>
				<br>
				Password<br> 
				<input type="password" id="password" name="password"><div id='passwordError'></div><br>
				<br>
				Confirm Password<br> 
				<input type="password" id="confirmPassword" name="confirmPassword"><div id='confirmPasswordError'></div><br>
				<br>
		</form>
		<button onclick="checkRegistrationInput()">Submit</button>
	</div>
	
	<div id="registrationSuccessMsg">Congratulations, please sign in with your new credentials! :)</div>

</body>

</html>
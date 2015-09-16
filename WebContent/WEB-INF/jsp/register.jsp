<form id="registerForm" action="submitRegistration.html" method="post">
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
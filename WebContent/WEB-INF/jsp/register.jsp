<form id="registerForm" action="submitRegister.html" method="post">
				Username<br> 
				<input type="text" id="username" name="username"> <div id='usernameError'></div><br>
				Email<br> 
				<input type="text" id="email" name="email"> <div id='emailError'></div> <br>
				Password<br> 
				<input type="password" id="password" name="password"><div id='passwordError'></div> <br>
				Confirm Password<br> 
				<input type="password" id="confirmPassword" name="confirmPassword"> <div id='confirmPasswordError'></div><br>
				
</form>
<button onclick="checkRegInput()">Register</button>
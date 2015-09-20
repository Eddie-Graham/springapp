function loadRegisterPage(){
	
	$('#loginEmailError').hide();
	$('#loginPasswordError').hide();
	
	$('#usernameError').html("");
	$('#emailError').html("");
	$('#passwordError').html("");
	$('#confirmPasswordError').html("");
	
	$('#registrationSuccessMsg').hide();
	
	$("#registrationFormDiv").show();
}

function checkRegistrationInput() {
	
	var username = $('#username').val();
	username = username.trim();
	
	var email = $('#email').val();
	email = email.trim();
	
	var password = $('#password').val();
	var confirmPassword = $('#confirmPassword').val();
	
	var legitPasswords = true;
	var legitEmail = true;
	var legitUsername = true;
	var waitingForUsernameResponse = false;
	var waitingForEmailResponse = false;
	var finishedMethod = false;
	
	$('#usernameError').show();
	$('#emailError').show();
	$('#passwordError').show();
	$('#confirmPasswordError').show();
	
	//////////////////////////////////////
	// USERNAME CHECK                   //
	//////////////////////////////////////
	
	if(username == ''){
		$('#usernameError').html("Please enter username");
		legitUsername = false;
	}
	
	else{
		waitingForUsernameResponse = true;
		
		$.ajax({
			type : "GET",
			url : "checkUniqueUsername.html",
			data: {"username": username},
			
			success : function(response) {
				
				if(response == 'NOT UNIQUE'){
					$('#usernameError').html("Username already taken, please use another one");
					legitUsername = false;
				}
				
				else if(response == 'UNIQUE'){
					$('#usernameError').html("");
				}
				
				waitingForUsernameResponse = false;
				
				if(legitUsername && legitEmail && legitPasswords && finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse){
					$("#registerForm").submit();
				}
			}
		});
	}
	
	//////////////////////////////////////
	// EMAIL CHECK                      //
	//////////////////////////////////////
		
	if(email == ''){
		$('#emailError').html("Please enter email");
		legitEmail = false;
	}
	
	else if(email.indexOf("@") < 0){
		$('#emailError').html("Please enter valid email");
		legitEmail = false;
	}
	
	else if(email.indexOf(" ") > -1){
		$('#emailError').html("Please enter valid email");
		legitEmail = false;
	}
	
	else{
		waitingForEmailResponse = true;
		
		$.ajax({
			type : "GET",
			url : "checkUniqueEmail.html",
			data: {"email": email},
			
			success : function(response) {
				
				if(response == 'NOT UNIQUE'){
					$('#emailError').html("Email already taken, please use another one");
					legitEmail = false;
				}
				
				else if(response == 'UNIQUE'){
					$('#emailError').html("");
				}
				
				waitingForEmailResponse = false;
				
				if(legitUsername && legitEmail && legitPasswords && finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse){
					$("#registerForm").submit();
				}
			}
		});
	}
	
	//////////////////////////////////////
	// PASSWORDS CHECK                  //
	//////////////////////////////////////
	
	if(password == ''){
		$('#passwordError').html("Please enter password");
		var legitPasswords = false;
	}
	
	else if(password.length < 8){
		$('#passwordError').html("Password must be atleast 8 characters long");
		var legitPasswords = false;
	}
	
	else
		$('#passwordError').html("");
		
	if(confirmPassword == ''){
		$('#confirmPasswordError').html("Please confirm password");
		var legitPasswords = false;
	}
	
	else if(confirmPassword.length < 8){
		$('#confirmPasswordError').html("Password must be atleast 8 characters long");
		var legitPasswords = false;
	}
	
	else
		$('#confirmPasswordError').html("");
	
	if(legitPasswords && password != confirmPassword){
		$('#confirmPasswordError').html("Does not match with password, please try again");
		var legitPasswords = false;
	}
	
	var finishedMethod = true;
	
	if(legitUsername && legitEmail && legitPasswords && finishedMethod && !waitingForUsernameResponse && !waitingForEmailResponse){
		$("#registerForm").submit();
	}
}

$(document).ready(function() {
	
	//////////////////////////////////////
	// LOGIN FORM SUBMIT                //
	//////////////////////////////////////

	$("#loginForm").submit(function(e) {
		
		e.preventDefault();
		
		$('#registrationSuccessMsg').hide();
		
		$("#registrationFormDiv").hide();
		
		$('#loginEmailError').show();
		$('#loginPasswordError').show();
		
		var loginEmail = $('#loginEmail').val();
		loginEmail = loginEmail.trim();
		var loginPassword = $('#loginPassword').val();
		
		var emptyInput = false;
		
		if(loginEmail == ""){
			$('#loginEmailError').html('Please enter email');
			emptyInput = true;
		}
		
		else
			$('#loginEmailError').html('');
		
		if(loginPassword == ""){
			$('#loginPasswordError').html('Please enter password');
			emptyInput = true;
		}
		
		else
			$('#loginPasswordError').html('');
		
		if(emptyInput)
			return false;

		$.ajax({
			type : "POST",
			url : 'login.html',
			data : $("#loginForm").serialize(), 
			success : function(response) {
				
				if(response == "FAILED"){
					$('#loginEmailError').html('Invalid Email/Password combination');
				}
				
				else if(response == "SUCCESS"){
					window.location = "dashboardPage.html";
				}
			}
		});

		return false;
	});
	
	//////////////////////////////////////
	// REGISTER FORM SUBMIT             //
	//////////////////////////////////////
	
	$('#registerForm').on('submit', function(e) {

        e.preventDefault();

        $.ajax({
			type : "POST",
			url : 'submitRegistration.html',
			data : $("#registerForm").serialize(), 
			success : function(response) {
				
				if(response == 'SUCCESS'){
					$('#registrationFormDiv').hide();
					$('#registrationSuccessMsg').show();					
				}	
			}
		});
    });
});

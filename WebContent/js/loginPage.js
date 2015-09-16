function loadRegisterPage(){
	
	$.ajax({
		type : "GET",
		url : "register.html",
		
		success : function(response) {
			
			$('#loginEmailError').empty();
			$('#loginPasswordError').empty();
			$("#registerDiv").html(response); 
		}
	});
}

function checkRegistrationInput() {
	
	var username = $('#username').val();
	var email = $('#email').val();
	var password = $('#password').val();
	var confirmPassword = $('#confirmPassword').val();
	
	var legitPasswords = true;
	var legitEmail = true;
	var legitUsername = true;
	var waitingForUsernameResponse = false;
	var waitingForEmailResponse = false;
	var finishedMethod = false;
	
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


function attemptLogin() {
	
	alert("yoo");

	$("#loginForm").ajaxForm({
		url : 'login.html',
		type : 'GET',
		beforeSubmit : function() {
		},
		success : function(response) {
			alert("hi");
		}
	});
}

$(document).ready(function() {

	$("#loginForm").submit(function() {
		
		$("#registerDiv").empty();
		var loginEmail = $('#loginEmail').val();
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
					window.location = "main.html";
				}
			}
		});

		return false;
	});
});
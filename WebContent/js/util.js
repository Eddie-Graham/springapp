function updateClock(id) {
	
	var date = new Date();
	
	var hoursNum = date.getHours();
	var hoursStr = hoursNum.toString();
	
	var minutesNum = date.getMinutes();
	var minutesStr = minutesNum.toString();
	
	var secondsNum = date.getSeconds();
	var secondsStr = secondsNum.toString();
	
	var hours = timeFormatting(hoursStr);
	var minutes = timeFormatting(minutesStr);
	var seconds = timeFormatting(secondsStr);
	
	var string = hours + ":" + minutes + ":" + seconds;

	$("#" + id).html(string);

	// call this function again in 1000ms
	setTimeout(updateClock, 1000, id);
}

function timeFormatting(element){
	
	if(element.length == 1){
		return "0" + element;
	}
	
	return element;
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
		$('#passwordError').html("Password length must be atleast 8 characters long");
		var legitPasswords = false;
	}
	
	else
		$('#passwordError').html("");
		
	if(confirmPassword == ''){
		$('#confirmPasswordError').html("Please confirm password");
		var legitPasswords = false;
	}
	
	else if(confirmPassword.length < 8){
		$('#confirmPasswordError').html("Password length must be atleast 8 characters long");
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
function updateUser(userId){
	
	// can not put a form inside a table element
	// so have to grab input this way
	
	var username = $('#username_' + userId).val();
	var email = $('#email_' + userId).val();
	var authority = $('#authority_' + userId).val();
	var enabled = $('#enabled_' + userId).val();
	var password = $('#password_' + userId).val();
	
	var url = "updateUser.html?userId=" + userId + "&username="
		+ username + "&email=" + email + "&authority=" + authority
		+ "&enabled=" + enabled;
	
	if(password != "")
		url += "&password=" + password;

	window.location = url;
}
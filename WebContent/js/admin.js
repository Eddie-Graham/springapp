function updateUser(userId){
	
	// can not put a form inside a table element
	// so have to grab input this way
	
	var username = $('#username_' + userId).val();
	var email = $('#email_' + userId).val();
	var authority = $('#authority_' + userId).val();
	var enabled = $('#enabled_' + userId).val();
	

	window.location = "updateUser.html?userId=" + userId + "&username="
			+ username + "&email=" + email + "&authority=" + authority
			+ "&enabled=" + enabled;
}
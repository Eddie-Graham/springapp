$(document).ready(function() {
	
	getUsersRecentPosts();
});

function getUsersRecentPosts(){
	
	$.ajax({
		type : "GET",
		url : "getUsersRecentPosts.html",
		success : function(response) {

			$("#myRecentPostsDiv").html(response); 
		}
	});
}
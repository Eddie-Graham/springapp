$(document).ready(function() {
	
	getUsersRecentPosts();
	
	getUsersStats()
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

function getUsersStats(){
	
	$.ajax({
		type : "GET",
		url : "getUsersStats.html",
		success : function(response) {

			$("#statsDiv").html(response); 
		}
	});
}
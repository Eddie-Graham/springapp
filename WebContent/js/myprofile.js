function getUsersRecentPosts(userId){
	
	$.ajax({
		type : "GET",
		url : "getUsersRecentPosts.html",
		data: {"userId": userId},
		success : function(response) {

			$("#myRecentPostsDiv").html(response); 
		}
	});
}

function getUsersStats(userId){
	
	$.ajax({
		type : "GET",
		url : "getUsersStats.html",
		data: {"userId": userId},
		success : function(response) {

			$("#statsDiv").html(response); 
		}
	});
}
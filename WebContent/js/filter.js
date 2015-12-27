function refreshPostsByLikes(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByLikes.html",
		data : {userId: userId},
		success : function(response) {
	
			$("#menuLink1").html("Likes"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByDislikes(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByDislikes.html",
		data : {userId: userId},
		success : function(response) {
	
			$("#menuLink1").html("Dislikes"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByTimestamp(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByTimestamp.html",
		data : {userId: userId},
		success : function(response) {
			
			$("#menuLink1").html("Timestamp"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByNoOfReplies(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByNoOfReplies.html",
		data : {userId: userId},
		success : function(response) {
			
			$("#menuLink1").html("Most replied"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByTotal(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByTotal.html",
		data : {userId: userId},
		success : function(response) {
			
			$("#menuLink1").html("Total"); 
			$("#postsList").html(response); 
		}
	});
}
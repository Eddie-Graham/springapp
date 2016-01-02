var fadeOutDefault = 400;
var fadeInDefault = 400;
// this value is also "copied" in home.js & posts.js

function refreshPostsByLikes(userId){
	
	$.ajax({
		type : "GET",
		url : "postsByLikes.html",
		data : {userId: userId},
		success : function(response) {
	
			$("#menuLink1").html("Likes"); 
			
			$("#postsList").fadeOut(fadeOutDefault, function(){
				
				$("#postsList").html(response); 
				$("#postsList").fadeIn(fadeInDefault); 
			}); 
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
			
			$("#postsList").fadeOut(fadeOutDefault, function(){
				
				$("#postsList").html(response); 
				$("#postsList").fadeIn(fadeInDefault); 
			}); 
		}
	});
}

function refreshPostsByTimestamp(userId, doNotFadeOut){
	
	$.ajax({
		type : "GET",
		url : "postsByTimestamp.html",
		data : {userId: userId},
		success : function(response) {
			
			$("#menuLink1").html("Timestamp"); 
			
			if(doNotFadeOut){
				$("#postsList").html(response); 
				$("#postsList").fadeIn(fadeInDefault); 
			}
			else{
				$("#postsList").fadeOut(fadeOutDefault, function(){
				
					$("#postsList").html(response); 
					$("#postsList").fadeIn(fadeInDefault); 
				}); 
			}
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
			
			$("#postsList").fadeOut(fadeOutDefault, function(){
				
				$("#postsList").html(response); 
				$("#postsList").fadeIn(fadeInDefault); 
			}); 
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
			
			$("#postsList").fadeOut(fadeOutDefault, function(){
				
				$("#postsList").html(response); 
				$("#postsList").fadeIn(fadeInDefault); 
			}); 
		}
	});
}
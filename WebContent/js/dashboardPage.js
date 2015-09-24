$(document).ready(function() {
	
	updateClock("time");
	
	refreshPostsByTimestamp();
	
	refreshPostsByLikes();
});

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

function submitPost(){
	
	var postText = $("#postText").val();
	postText = postText.trim();
	
	if(postText == "")
		return;
	
	$.ajax({
		type : "GET",
		url : "submitPost.html",
		data: {"postText": postText},
		success : function(response) {

			refreshPostsByLikes();
			$("#left").html(response); 
		}
	});
}

function refreshPostsByLikes(){
	
	$.ajax({
		type : "GET",
		url : "postsByLikes.html",
		
		success : function(response) {
			
			$("#right").html(response); 
		}
	});
}

function refreshPostsByTimestamp(){
	
	$.ajax({
		type : "GET",
		url : "postsByTimestamp.html",
		
		success : function(response) {
			
			$("#left").html(response); 
		}
	});
}

function incrementLikes(postId){
	
	$.ajax({
		type : "GET",
		url : "incrementLikes.html",
		data: {"postId": postId},
		success : function(response) {
			
			if(response == 'FAILED_LIKED'){
				alert("You have already liked this post.");
				return;
			}
			
			if(response == 'FAILED_DISLIKED'){
				alert("You have already disliked this post.");
				return;
			}
			
			var likes = $("#likes_" + postId).html();
			var newLikes = parseInt(likes) + 1;
			
			$("#likes_" + postId).html(newLikes); 
			
			refreshPostsByLikes();
		}
	});
}

function decrementLikes(postId){
	
	$.ajax({
		type : "GET",
		url : "decrementLikes.html",
		data: {"postId": postId},
		success : function(response) {
			
			if(response == 'FAILED_LIKED'){
				alert("You have already liked this post.");
				return;
			}
			
			if(response == 'FAILED_DISLIKED'){
				alert("You have already disliked this post.");
				return;
			}
			
			var dislikes = $("#dislikes_" + postId).html();
			var newDislikes = parseInt(dislikes) - 1;
			
			$("#dislikes_" + postId).html(newDislikes); 
			
			refreshPostsByLikes();
		}
	});
}
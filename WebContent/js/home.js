$(document).ready(function() {
	
	// Dynamic css assignment for responsiveness across devices
	var divHeight = $("#postSubmitDiv").height();
	// Total padding is 64px (32px*2)
	divHeight += 64;
	$("#postsDiv").css('top', divHeight);
	
	updateClock("time");
	
	refreshPostsByTimestamp();
	
//	refreshPostsByLikes();
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

//			refreshPostsByLikes();
			$("#left").html(response); 
		}
	});
}

//function refreshPostsByLikes(){
//	
//	$.ajax({
//		type : "GET",
//		url : "postsByLikes.html",
//		
//		success : function(response) {
//			
//			$("#right").html(response); 
//		}
//	});
//}

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
			
			if(response == 'UNDO_LIKED'){
				
				var likes = $("#likes_" + postId).html();
				var newLikes = parseInt(likes) - 1;
				$("#likes_" + postId).html(newLikes);
				
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) - 1;		
				$("#total_" + postId).html(newTotal); 
			}
			
			else if(response == 'REVERSED_DISLIKE'){
				
				var likes = $("#likes_" + postId).html();
				var newLikes = parseInt(likes) + 1;
				$("#likes_" + postId).html(newLikes); 
				
				var dislikes = $("#dislikes_" + postId).html();
				var newDislikes = parseInt(dislikes) + 1;			
				$("#dislikes_" + postId).html(newDislikes); 
				
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) + 2;		
				$("#total_" + postId).html(newTotal); 
			}
			
			else if(response == 'SUCCESS'){
				
				var likes = $("#likes_" + postId).html();
				var newLikes = parseInt(likes) + 1;
				$("#likes_" + postId).html(newLikes); 
			
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) + 1;		
				$("#total_" + postId).html(newTotal); 
			}
			
			if(newTotal > 0)
				$("#total_" + postId).css('color', '#199A19');
			
			else if(newTotal < 0)
				$("#total_" + postId).css('color', '#FF0000');
			
			else
				$("#total_" + postId).css('color', '#777');
			
//			refreshPostsByLikes();
		}
	});
}

function decrementDisikes(postId){
	
	$.ajax({
		type : "GET",
		url : "decrementDisikes.html",
		data: {"postId": postId},
		success : function(response) {
			
			if(response == 'REVERSED_LIKE'){
				
				var likes = $("#likes_" + postId).html();
				var newLikes = parseInt(likes) - 1;
				$("#likes_" + postId).html(newLikes); 
				
				var dislikes = $("#dislikes_" + postId).html();
				var newDislikes = parseInt(dislikes) - 1;			
				$("#dislikes_" + postId).html(newDislikes); 
				
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) - 2;		
				$("#total_" + postId).html(newTotal); 
			}
			
			else if(response == 'UNDO_DISLIKED'){
				
				var dislikes = $("#dislikes_" + postId).html();
				var newDislikes = parseInt(dislikes) + 1;			
				$("#dislikes_" + postId).html(newDislikes); 
				
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) + 1;		
				$("#total_" + postId).html(newTotal); 
			}
			
			else if(response == 'SUCCESS'){
				
				var dislikes = $("#dislikes_" + postId).html();
				var newDislikes = parseInt(dislikes) - 1;			
				$("#dislikes_" + postId).html(newDislikes); 
			
				var total = $("#total_" + postId).html();
				var newTotal = parseInt(total) - 1;			
				$("#total_" + postId).html(newTotal); 
			}
			
			if(newTotal > 0)
				$("#total_" + postId).css('color', '#199A19');
			
			else if(newTotal < 0)
				$("#total_" + postId).css('color', '#FF0000');
			
			else
				$("#total_" + postId).css('color', '#777');
			
//			refreshPostsByLikes();
		}
	});
}
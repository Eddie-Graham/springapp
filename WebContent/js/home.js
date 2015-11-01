$(document).ready(function() {
	
	// Dynamic css assignment for responsiveness across devices
	var divHeight = $("#postSubmitDiv").height();
	// Total padding is 64px (32px*2)
	divHeight += 64;
	$("#postsDiv").css('top', divHeight);
	
	divHeight = $("#filter").height();
	$("#postsList").css('top', divHeight);
	
	
	refreshPostsByTimestamp();
});

function submitPost(){
	
	var postText = $("#postText").val();
	postText = postText.trim();
	
	if(postText == "")
		return;
	
	$.ajax({
		type : "POST",
		url : "submitPost.html",
		data: {"postText": postText},
		success : function(response) {

			$("#postText").val('');
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByLikes(){
	
	$.ajax({
		type : "GET",
		url : "postsByLikes.html",
		
		success : function(response) {
	
			$("#menuLink1").html("Likes"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByDislikes(){
	
	$.ajax({
		type : "GET",
		url : "postsByDislikes.html",
		
		success : function(response) {
	
			$("#menuLink1").html("Dislikes"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByTimestamp(){
	
	$.ajax({
		type : "GET",
		url : "postsByTimestamp.html",
		
		success : function(response) {
			
			$("#menuLink1").html("Timestamp"); 
			$("#postsList").html(response); 
		}
	});
}

function refreshPostsByTotal(){
	
	$.ajax({
		type : "GET",
		url : "postsByTotal.html",
		
		success : function(response) {
			
			$("#menuLink1").html("Total"); 
			$("#postsList").html(response); 
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
		}
	});
}

function refreshPostsByTag(tag){
	
	$.ajax({
		type : "GET",
		url : "postsByTag.html",
		data: {"tag": tag},
		success : function(response) {
			
			$("#menuLink1").html("Tag '" + tag + "'"); 
			
			$("#postsList").html(response); 
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
		}
	});
}
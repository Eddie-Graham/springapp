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

function getPostComments(postId, backgroundColor){
	
	$.ajax({
		type : "GET",
		url : "getPostComments.html",
		data: {"postId": postId, "backgroundColor": backgroundColor},
		success : function(response) {
			
			$("#comments_" + postId).html(response); 
			
			$("#comments_" + postId).collapse('show');
		}
	});
}

function submitPostComment(postId, backgroundColor){
	
	var postText = $("#postComment_" + postId).val();
	postText = postText.trim();
	
	if(postText == "")
		return;
	
	$.ajax({
		type : "POST",
		url : "submitPostComment.html",
		data: {"postText": postText, "postId": postId},
		success : function(response) {

			getPostComments(postId, backgroundColor);
			
			$("#replyDiv_" + postId).collapse('hide');	
		}
	});
}
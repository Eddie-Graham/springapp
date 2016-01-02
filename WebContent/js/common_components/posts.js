function incrementLikes(postId, fromPostComments){
	
	$.ajax({
		type : "GET",
		url : "incrementLikes.html",
		data: {"postId": postId, "fromPostComments": fromPostComments},
		success : function(response) {
			
			if(!fromPostComments){
				
				////////////////
				// main post  //
				////////////////
				
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
				
				return;
			}
			
			///////////////////
			// post comment  //
			///////////////////
			
			if(response == 'UNDO_LIKED'){
				
				var likes = $("#likes_comment_" + postId).html();
				var newLikes = parseInt(likes) - 1;
				$("#likes_comment_" + postId).html(newLikes);
				
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) - 1;		
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			else if(response == 'REVERSED_DISLIKE'){
				
				var likes = $("#likes_comment_" + postId).html();
				var newLikes = parseInt(likes) + 1;
				$("#likes_comment_" + postId).html(newLikes); 
				
				var dislikes = $("#dislikes_comment_" + postId).html();
				var newDislikes = parseInt(dislikes) + 1;			
				$("#dislikes_comment_" + postId).html(newDislikes); 
				
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) + 2;		
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			else if(response == 'SUCCESS'){
				
				var likes = $("#likes_comment_" + postId).html();
				var newLikes = parseInt(likes) + 1;
				$("#likes_comment_" + postId).html(newLikes); 
			
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) + 1;		
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			if(newTotal > 0)
				$("#total_comment_" + postId).css('color', '#199A19');
			
			else if(newTotal < 0)
				$("#total_comment_" + postId).css('color', '#FF0000');
			
			else
				$("#total_comment_" + postId).css('color', '#777');
			
		}
	});
}

function decrementDisikes(postId, fromPostComments){
	
	$.ajax({
		type : "GET",
		url : "decrementDisikes.html",
		data: {"postId": postId, "fromPostComments": fromPostComments},
		success : function(response) {
			
			if(!fromPostComments){
				
				////////////////
				// main post  //
				////////////////
				
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
				
				return;
			}
			
			///////////////////
			// post comment  //
			///////////////////
			
			if(response == 'REVERSED_LIKE'){
				
				var likes = $("#likes_comment_" + postId).html();
				var newLikes = parseInt(likes) - 1;
				$("#likes_comment_" + postId).html(newLikes); 
				
				var dislikes = $("#dislikes_comment_" + postId).html();
				var newDislikes = parseInt(dislikes) - 1;			
				$("#dislikes_comment_" + postId).html(newDislikes); 
				
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) - 2;		
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			else if(response == 'UNDO_DISLIKED'){
				
				var dislikes = $("#dislikes_comment_" + postId).html();
				var newDislikes = parseInt(dislikes) + 1;			
				$("#dislikes_comment_" + postId).html(newDislikes); 
				
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) + 1;		
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			else if(response == 'SUCCESS'){
				
				var dislikes = $("#dislikes_comment_" + postId).html();
				var newDislikes = parseInt(dislikes) - 1;			
				$("#dislikes_comment_" + postId).html(newDislikes); 
			
				var total = $("#total_comment_" + postId).html();
				var newTotal = parseInt(total) - 1;			
				$("#total_comment_" + postId).html(newTotal); 
			}
			
			if(newTotal > 0)
				$("#total_comment_" + postId).css('color', '#199A19');
			
			else if(newTotal < 0)
				$("#total_comment_" + postId).css('color', '#FF0000');
			
			else
				$("#total_comment_" + postId).css('color', '#777');
		}
	});
}

function expandComments(postId, backgroundColor){
	
	if ($("#comments_" + postId).is(':visible')){
		
		$("#comments_" + postId).hide(800);
		
		var prevHtml = $('#expandCommentsBtn_' + postId).html();
		var newHtml = prevHtml.replace("-", "+");
		$('#expandCommentsBtn_' + postId).html(newHtml);
		
		return;
	}
	
	getPostComments(postId, backgroundColor);
}

function getPostComments(postId, backgroundColor){
	
	$.ajax({
		type : "GET",
		url : "getPostComments.html",
		data: {"postId": postId, "backgroundColor": backgroundColor},
		success : function(response) {
			
			$("#comments_" + postId).html(response); 
			
			if ($("#comments_" + postId).is(':hidden')){
				
				$("#comments_" + postId).slideToggle(1000);
				
				var prevHtml = $('#expandCommentsBtn_' + postId).html();
				var newHtml = prevHtml.replace("+", "-");
				$('#expandCommentsBtn_' + postId).html(newHtml);
			}
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
			
			// increment number of comments indicator
			var prevHtml = $('#expandCommentsBtn_' + postId).html();
			var number = prevHtml.replace(/^\D+/, '');
			number ++;
			var newHtml = prevHtml.replace(/\d+/, number);
			$('#expandCommentsBtn_' + postId).html(newHtml);

			getPostComments(postId, backgroundColor);
			
			$("#replyDiv_" + postId).hide(800);
			$("#postComment_" + postId).val("");
		}
	});
}

function toggleReplyDiv(replyDivId){
	
	$("#" + replyDivId).slideToggle(300);
}

function refreshPostsByTag(tag, userId){
	
	if(userId == "")
		userId = undefined;
	
	$.ajax({
		type : "GET",
		url : "postsByTag.html",
		data: {"tag": tag, "userId": userId},
		success : function(response) {
			
			$("#menuLink1").html("Tag " + tag); 
			
			$("#postsList").fadeOut(400, function(){
				
				$("#postsList").html(response); 
				$("#postsList").fadeIn(400); 
			}); 
		}
	});
}

function confirmPostDelete(postId, fromPostComments){
	
    if (confirm("Are you sure you want to delete this post?") == true) {
        
    	$.ajax({
    		type : "GET",
    		url : "deletePost.html",
    		data: {"postId": postId, "fromPostComments": fromPostComments},
    		success : function(response) {
    			
    			refreshPostsByTimestamp(undefined, false);
    		}
    	});
    } 
}
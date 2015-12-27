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
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:set var="count" value="0" scope="page" />

<c:forEach items="${posts}" var="post">

	<c:choose>
		<c:when test="${count % 2 == 0}">
			<c:set var="backgroundColor" value="#FFFFFF"/>
		</c:when>
		<c:otherwise>
			<c:set var="backgroundColor" value="#E6E6E6"/>
		</c:otherwise>
	</c:choose>
    
	<c:set var="count" value="${count + 1}" scope="page"/>
	
	<!-- Overwrite background color when dealing with post comments -->
	<c:if test="${fromPostComments}">
		<c:set var="backgroundColor" value="${postCommentsColor}"/>
	</c:if>

	<div id="postContainer" style="background-color: ${backgroundColor};">

		<div id="post">
		
			<div id="profileDiv">
				<div id="image">
				<c:choose>
					<c:when test="${post.user.hasProfilePic}">
						<a href="myprofile.html?id=${post.user.id}"><img id="profilePic" src="profile_images/${post.user.id}.png" alt="Profile pic"></a>
					</c:when>
					<c:otherwise>
						<a href="myprofile.html?id=${post.user.id}"><img id="profilePic" src="images/profile_default.png" alt="Profile pic"></a>		
					</c:otherwise>
				</c:choose>
				</div>
				
				<div id="username">
					<a href="myprofile.html?id=${post.user.id}" class="links"><strong><c:out value="${post.user.username}" /></strong></a>
				</div>
				
			</div>
		
			<div id="postLeft"> 
				<div id="textDiv">
					<div id="text">
					
					<!-- Split by whitespace, loop and see if token begin with # -->
					<c:set var="postText" value="${post.text}" scope="page" />
					
					<c:set var="splitText" value="${fn:split(postText, ' ')}" />
					
					<c:forEach items="${splitText}" var="token">
						
						<c:choose>
							<c:when test="${fn:startsWith(token, '#')}">
								<strong><a href="javascript:refreshPostsByTag('${token}');" style="color: red;" class="links"><c:out value="${token}" /></a></strong>
							</c:when>
							<c:otherwise>
								<c:out value="${token}" />
							</c:otherwise>
						</c:choose>
						
					</c:forEach>
					
					</div>
				</div>
				
				<div id="bottom">

					<c:if test="${empty fromPostComments}">
						<button type="button"
							onclick="expandComments('${post.id}', '${backgroundColor}')"
							id="expandCommentsBtn_${post.id}" class="expandCommentsBtn">
							+${post.noOfComments}
						</button>
						&nbsp;
						<a href="javascript:toggleReplyDiv('replyDiv_${post.id}');" class="reply links">
							<strong>Reply</strong></a>
					</c:if>

					<div id="timestamp">				
						${post.timeString} &nbsp; ${post.dateString}
					</div> 
				</div>
			</div>

			<div id="postRight">

				<div id="postStats" class="pure-u-1-2">
					
					<c:choose>
						<c:when test="${post.total gt 0}">
							<c:set var="color" value="#199A19"/>
						</c:when>
						<c:when test="${post.total lt 0}">
							<c:set var="color" value="#FF0000"/>
						</c:when>
						<c:otherwise>
							<c:set var="color" value="#777"/>
						</c:otherwise>
					</c:choose>
					
					<c:if test="${empty fromPostComments}">
					
						<div id="likes_${post.id}" class="pure-u-1 postLikes">${post.likes}</div>
					
						<div id="total_${post.id}" class="pure-u-1 postTotal"
							style="color: ${color};">${post.total}</div>

						<div id="dislikes_${post.id}" class="pure-u-1 postDislikes">${post.dislikes}</div>
						
					</c:if>
						
					<c:if test="${fromPostComments}">
					
						<div id="likes_comment_${post.id}" class="pure-u-1 postLikes">${post.likes}</div>
					
						<div id="total_comment_${post.id}" class="pure-u-1 postTotal"
							style="color: ${color};">${post.total}</div>

						<div id="dislikes_comment_${post.id}" class="pure-u-1 postDislikes">${post.dislikes}</div>
		
					</c:if>
					
				</div>
				
				<c:choose>
					<c:when test="${post.canRate}">
					
						<c:choose>
							<c:when test="${empty fromPostComments}">
								<c:set var="postComment" value="false"/>
							</c:when>
							<c:otherwise>
								<c:set var="postComment" value="true"/>
							</c:otherwise>
						</c:choose>
						
						<div id="buttons" class="pure-u-1-2">
							<div id="likeButton" class="pure-u-1">
								<input onclick="incrementLikes(${post.id}, ${postComment})" type="image"
									src="/springapp/images/like.png" width="100%" height="100%"
									alt="like">
							</div>

							<div id="dislikeButton" class="pure-u-1">
								<input onclick="decrementDisikes(${post.id}, ${postComment})" type="image"
									src="/springapp/images/dislike.png" width="100%" height="100%"
									alt="dislike">
							</div>
						</div>
					
					</c:when>
					<c:otherwise>
					
						<div id="buttons" class="pure-u-1-2">
							<div id="likeButton" class="pure-u-1">
									<img class="images" src="/springapp/images/like.png" alt="like">
							</div>

							<div id="dislikeButton" class="pure-u-1">
									<img class="images" src="/springapp/images/dislike.png" alt="dislike">
							</div>
						</div>		
						
					</c:otherwise>
				</c:choose>
				
			</div>
		</div>
	</div>
	
	<c:if test="${empty fromPostComments}">
	
		<div id="replyDiv_${post.id}" class="collapse replyDiv" style="background-color: ${backgroundColor};">
 			<form class="pure-form">
   				<input id="postComment_${post.id}" type="text" class="pure-input-rounded">
   				<button type="button" onclick="submitPostComment('${post.id}', '${backgroundColor}')"  
					class="pure-button pure-button-primary">Submit</button>
			</form>
		</div> 
	
		<div class="postCommentsDiv" style="background-color: ${backgroundColor};">
			<div id="comments_${post.id}" class="collapse"></div> 
		</div>
		
	</c:if>
	
</c:forEach>
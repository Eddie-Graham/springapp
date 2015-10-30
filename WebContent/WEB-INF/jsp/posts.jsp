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

	<div id="postContainer" style="background-color: ${backgroundColor};">

		<div id="post">
		
			<div id="profileDiv">
				<div id="image">
				<c:choose>
					<c:when test="${post.profileImagePath == ''}">
						<img id="profilePic" src="images/profile_default.png" alt="Profile pic">
					</c:when>
					<c:otherwise>
						<a href="viewuser.html?id=${post.user_id}"><img id="profilePic" src="${post.profileImagePath}" alt="Profile pic"></a>
					</c:otherwise>
				</c:choose>
				</div>
				
				<div id="username">
					<a href="viewuser.html?id=${post.user_id}" class="links"><strong><c:out value="${post.username}" /></strong></a>
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
				
				<div id="timestamp">
					${post.timeString} &nbsp ${post.dateString}
				</div> 
			</div>

			<div id="postRight">

				<div id="postStats" class="pure-u-1-2">
					<div id="likes_${post.id}" class="pure-u-1 postLikes">${post.likes}</div>
					
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
					
					<div id="total_${post.id}" class="pure-u-1 postTotal"
								style="color: ${color};">${post.total}</div>

					<div id="dislikes_${post.id}" class="pure-u-1 postDislikes">${post.dislikes}</div>
				</div>
				
				<c:choose>
					<c:when test="${post.canRate}">
					
						<div id="buttons" class="pure-u-1-2">
							<div id="likeButton" class="pure-u-1">
								<input onclick="incrementLikes(${post.id})" type="image"
									src="/springapp/images/like.png" width="100%" height="100%"
									alt="like">
							</div>

							<div id="dislikeButton" class="pure-u-1">
								<input onclick="decrementDisikes(${post.id})" type="image"
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
</c:forEach>
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
			<div id="postLeft">
				<div id="textDiv">
					<div id="text">${post.text}</div>
				</div>
				<div id="space"></div>
				<div id="username">Posted by ${post.username} on
					${post.timeString} &nbsp ${post.dateString}</div>
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
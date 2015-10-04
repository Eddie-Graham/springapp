<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">
	<div id="postContainer">

		<div id="post">
			<div id="postLeft">
				<div id="text">${post.text}</div>
				<div id="username">Posted by ${post.username} on
					${post.timeString} &nbsp ${post.dateString}</div>
			</div>

			<div id="postRight">

				<div id="postStats" class="pure-u-1-2">
					<div id="likes_${post.id}" class="pure-u-1 postLikes">${post.likes}</div>

					<c:choose>
						<c:when test="${post.total gt 0}">
							<div id="total_${post.id}" class="pure-u-1 postTotal"
								style="color: #199A19;">${post.total}</div>
						</c:when>
						<c:when test="${post.total lt 0}">
							<div id="total_${post.id}" class="pure-u-1 postTotal"
								style="color: #FF0000;">${post.total}</div>
						</c:when>
						<c:otherwise>
							<div id="total_${post.id}" class="pure-u-1 postTotal">${post.total}</div>
						</c:otherwise>
					</c:choose>

					<div id="dislikes_${post.id}" class="pure-u-1 postDislikes">${post.dislikes}</div>
				</div>
				
				<div id="buttons" class="pure-u-1-2">
					<div id="likeButton" class="pure-u-1">
						<input onclick="incrementLikes(${post.id})" type="image"
							src="/springapp/images/like.png" width="100%" height="100%"
							alt="Submit">
					</div>
					
					<div id="dislikeButton" class="pure-u-1">
						<input onclick="decrementLikes(${post.id})" type="image"
							src="/springapp/images/dislike.png" width="100%" height="100%"
							alt="Submit">
					</div>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">
	<div id="postContainer">


		<div id="post">
			<div id="postLeft">
				<div id="text">
					<c:out value="${post.text}" />
				</div>
				<div id="username">
					Posted by
					<c:out value="${post.username}" />
					on
					<c:out value="${post.timeString}" />
					&nbsp
					<c:out value="${post.dateString}" />
				</div>
			</div>

			<div id="postRight">
				<div id="likes">
					<div id="likes_${post.id}">
						<c:out value="${post.likes}" />
					</div>
					<input type="button" onclick="incrementLikes(${post.id})"
						value="Like" />
				</div>
				<div id="dislikes">
					<div id="dislikes_${post.id}">
						<c:out value="${post.dislikes}" />
					</div>
					<input type="button" onclick="decrementLikes(${post.id})"
						value="Dislike" />
				</div>
			</div>
		</div>
	</div>
</c:forEach>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<div id=submitPostPanel>
	<input type="text" id="submitPostText">
	<button onclick="submitPost()">Submit post</button>
</div>
<div id=postsPanel>
<c:forEach items="${posts}" var="post">

	<div id=postDiv>
		<div id=postText>
			<c:out value="${post.text}" />
		</div>
		<div id=postUsername>Posted by
			<c:out value="${post.username}" />
			on <c:out value="${post.timestamp}" />
		</div>
		<div id=postLikesDislikes>
			<div id=postLikes>
				<c:out value="${post.likes}" />
				Likes
			</div>
			<div id=postDislikes>
				<c:out value="${post.dislikes}" />
				Dislikes
			</div>
		</div>
	</div>

	<br>
</c:forEach>
</div>
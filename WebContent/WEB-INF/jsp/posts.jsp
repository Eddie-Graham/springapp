<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">

	<div id=postDiv>
		<div id=postText>
			<c:out value="${post.text}" />
		</div>
		<div id=postUsername>
			<c:out value="${post.username}" />
		</div>
		<div id=postLikesDislikes>
			<div id=postLikes>
				<c:out value="${post.likes}" />
			</div>
			<div id=postDislikes>
				<c:out value="${post.dislikes}" />
			</div>
		</div>
	</div>

	<br>
</c:forEach>
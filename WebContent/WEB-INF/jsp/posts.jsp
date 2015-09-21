<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">
	<div id="postContainer">
		<div id="post">
			<div id="postLeft">
				<div id="text">
					<c:out value="${post.text}"/>
				</div>
				<div id="username">
					Posted by <c:out value="${post.username}"/> on <c:out value="${post.timeString}"/>  &nbsp<c:out value="${post.dateString}"/>
				</div>
			</div>

			<div id="postRight">
				<div id="likes">
					<c:out value="${post.likes}"/> Likes
				</div>
				<div id="dislikes">
					<c:out value="${post.dislikes}"/> Dislikes
				</div>
			</div>
		</div>
	</div>
</c:forEach>

<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">
	<div id="postContainer">
		<div id="post">
			<c:out value="${post.text}" />
		</div>
	</div>
</c:forEach>

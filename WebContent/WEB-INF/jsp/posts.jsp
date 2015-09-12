<%@ include file="/WEB-INF/jsp/include.jsp"%>

<c:forEach items="${posts}" var="post">

	<div id=postDiv>
		<c:out value="${post.text}" />
	</div>
	<br>
</c:forEach>
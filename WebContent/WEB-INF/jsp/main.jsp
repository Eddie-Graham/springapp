<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
	<script>
		$(document).ready(function() {
			updateClock("time");

			/* $("#login").ajaxForm({url: '/springapp/login.html', type: 'post'}) */

			/* $("#login").ajaxForm({
				url : 'login.html',
				type : 'get',
				beforeSubmit : function() {

				},
				success : function(response) {
					alert("hi");
				}
			}); */
		});
	</script>
      
    </jsp:attribute>
	<jsp:attribute name="left">
	
	<c:choose>
    <c:when test="${empty LoggedInUser}">
        <form id="login" action="login.html" method="post">
			Email<br> <input type="text" name="email"> <br>
			Password<br> <input type="text" name="password">
			<input type="submit" value="Submit">
		</form>
    </c:when>    
    <c:otherwise>
       Logged in as ${LoggedInUser}
    </c:otherwise>
	</c:choose>
	
    </jsp:attribute>
	<jsp:body>
	<div id="time"></div>
	
	<div id="test">fff</div>
    </jsp:body>
</t:genericpage>
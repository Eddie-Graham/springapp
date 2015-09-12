<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
	<script>

		$(document).ready(function() {
			
			updateClock("time");
			
			$.ajax({
				type : "GET",
				url : "viewPosts.html",
				
				success : function(response) {
					
					$("#body").html(response); 
				}
			});

			$("#register").click(function(e) {
				
				e.preventDefault();
				
				$.ajax({
					type : "GET",
					url : "register.html",
					
					success : function(response) {
						
						$("#body").html(response); 
					}
				});
			});
		});
		
	</script>
      
    </jsp:attribute>
	<jsp:attribute name="left">
	
		<c:choose>
    	<c:when test="${empty loggedInUser}">
        	<form id="loginForm" action="login.html" method="post">
				Email<br> <input type="text" name="email"> <br>
				Password<br> <input type="password" name="password"> <input type="submit" value="Login">
			</form>
			 <br>
			 Not Registered?
			<input type="submit" id="register" value="Register Here">
			
    	</c:when>    
    	<c:otherwise>
       		Logged in as ${loggedInUser}
       
			<input type="button" onclick="location.href='logout.html';" value="Logout" />
    	</c:otherwise>
		</c:choose>
    </jsp:attribute>
	<jsp:body>
		
    </jsp:body>
</t:genericpage>
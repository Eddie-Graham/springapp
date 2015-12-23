<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
		<link rel="stylesheet" type="text/css" href="css/myprofile.css">
		<link rel="stylesheet" type="text/css" href="css/posts.css">
		<script src="js/myprofile.js"></script> 
		<script src="js/posts.js"></script> 
		
		<script>
		
		$(document).ready(function() {
			
			getUsersRecentPosts("${myProfileUser.id}");
			getUsersStats("${myProfileUser.id}");
		});
		
		</script>
		
    </jsp:attribute>
    
	<jsp:body>
	
	<div id="titleDiv">

		<div id="title" class="pure-u-1">

			<c:choose>
				<c:when test="${myProfileUser.hasProfilePic}">
				
					<div id="profileImageDiv" style="width: 160px; text-align: center;">
						<img id="profileImage" src="profile_images/${myProfileUser.id}.png" alt="Profile pic">
						
						<c:if test="${not empty isLoggedInUser}">
							<a id="delete" class="links" href="delete.html">Delete & Change</a>
						</c:if>
					</div>
				
				</c:when>			
				<c:when test="${not empty isLoggedInUser}">
				
					<div id="profileImageDiv" style="padding-left: 20px; width: 300px; height: 200px; border: 1px solid #000000;">
						<form method="POST" action="upload.html" enctype="multipart/form-data">

							<br>
    						Upload a profile pic!
    						<br>
    						<br>
    						<div>
    							<input type="file" name="file" />
    						</div>
    						<br>
    						<button type="submit" class="pure-button pure-button-primary">Upload</button>
    						
						</form>
					</div>
				
				</c:when>
				<c:otherwise>
				
					<div id="profileImageDiv" style="width: 160px; text-align: center;">
						<img id="profileImage" src="images/profile_default.png" alt="Profile pic">
					</div>
					
				</c:otherwise>
			</c:choose>
				
			<div id="profileOverview">
				<h2><strong>${myProfileUser.username}</strong></h2>
			</div>
		
		</div>
	</div>
	
	<div id="statsDiv" class="pure-u-1 pure-u-md-1-2"></div>
	
	<div id="myRecentPostsDiv" class="pure-u-1 pure-u-md-1-2"></div>

    </jsp:body>

</t:genericpage>
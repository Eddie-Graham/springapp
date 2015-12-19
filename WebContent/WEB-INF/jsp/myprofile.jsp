<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
		<link rel="stylesheet" type="text/css" href="css/myprofile.css">
		<link rel="stylesheet" type="text/css" href="css/posts.css">
		<script src="js/myprofile.js"></script> 
		<script src="js/posts.js"></script> 
		
    </jsp:attribute>
    
	<jsp:body>
	<div id="titleDiv">

		<div id="title" class="pure-u-1">

			<c:choose>
				<c:when test="${not empty imagePath}">
				
					<div id="profileImageDiv" style="width: 160px; text-align: center;">
						<img id="profileImage" src="${imagePath}" alt="Profile pic">
						<a id="delete" class="links" href="delete.html">Delete & Change</a>
					</div>
				
				</c:when>
				<c:otherwise>
				
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
					
				</c:otherwise>
			</c:choose>
				
			<div id="profileOverview">
				<h3><strong>${username}</strong></h3>
			</div>
		
		</div>
	</div>
	
	<div id="statsDiv" class="pure-u-1 pure-u-md-1-2"></div>
	
	<div id="myRecentPostsDiv" class="pure-u-1 pure-u-md-1-2"></div>

    </jsp:body>

</t:genericpage>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="css/myprofile.css">
		<link rel="stylesheet" type="text/css" href="css/posts.css">
		<script src="js/myprofile.js"></script> 
    </jsp:attribute>
    
	<jsp:body>
	<div id="titleDiv">

		<div id="title" class="pure-u-1">
			
			<!-- <div id="profileImageDiv"> -->
				<c:choose>
					<c:when test="${not empty imagePath}">
					<div id="profileImageDiv">
						<img id="profileImage" src="${imagePath}" alt="Profile pic">
					</div>
					</c:when>
					<c:otherwise>
					<div id="profileImageDiv" style="width: 300px; height: 200px; border: 1px solid #000000;">
						<form method="POST" action="upload.html" enctype="multipart/form-data">

    						No profile image found <input type="file" name="file" />
    						<button type="submit" class="pure-button pure-button-primary">Upload</button>
						</form>
				</div>
					</c:otherwise>
				</c:choose>
				
			<!-- </div> -->
			
			<div id="profileOverview">
				<h3><strong>${username}</strong></h3>
			</div>
		
		</div>
	</div>
	
	<div id="statsDiv" class="pure-u-1 pure-u-md-1-2"></div>
	

	
	
	<div id="myRecentPostsDiv" class="pure-u-1 pure-u-md-1-2"></div>

    </jsp:body>

</t:genericpage>
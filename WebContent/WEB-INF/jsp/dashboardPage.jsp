<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	<link rel="stylesheet" type="text/css" href="css/dashboardPage.css">
	<script src="js/dashboardPage.js"></script> 
    </jsp:attribute>
    
	<jsp:attribute name="leftBar">
		<div> Welcome ${loggedInUser}!</div>
		<input type="button" onclick="location.href='logout.html';" value="Logout" />
    </jsp:attribute>
    
	<jsp:body>
		<div id="submitPostDiv"> <textarea id="postText" name="postText"></textarea> <button id="postBtn" type="button" onclick="submitPost()">Post</button> </div>
		<div id="postsDiv">
			<div id="left"></div>
			<div id="right"></div>
		</div>
    </jsp:body>
</t:genericpage>
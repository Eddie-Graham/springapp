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
		<div id="title" class="pure-u-1"><strong>${username}</strong></div>
	</div>
	
	<div id="statsDiv" class="pure-u-1 pure-u-md-1-2"></div>
	<div id="myRecentPostsDiv" class="pure-u-1 pure-u-md-1-2"></div>

    </jsp:body>

</t:genericpage>
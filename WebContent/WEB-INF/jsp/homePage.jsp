<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="css/homePage.css">
		<script src="js/homePage.js"></script> 
    </jsp:attribute>
    
	<jsp:body>
	
	 	<div id="postSubmitDiv" class="pure-u-1">
	 		<form class="pure-form">
    			<input id="postText" type="text" class="pure-input-rounded">
    			<button type="button" onclick="submitPost()"
					class="pure-button pure-button-primary">Submit</button>
			</form>
	 	</div>
	 	
	 	<div id="postsDiv" class="pure-u-1">
	 		<div id="left" class="pure-u-1 pure-u-lg-1-2"></div>
	 		<div id="right" class="pure-u-1 pure-u-lg-1-2"></div>
	 	</div>

    </jsp:body>

</t:genericpage>
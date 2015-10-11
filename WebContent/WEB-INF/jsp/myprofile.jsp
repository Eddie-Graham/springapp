<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="css/myprofile.css">
		<script src="js/myprofile.js"></script> 
    </jsp:attribute>
    
	<jsp:body>
	
	<div id="title">${username}</div>

    </jsp:body>

</t:genericpage>
<%@ include file="/WEB-INF/jsp/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	<!-- <script src="js/dashboardPage.js"></script> -->
    </jsp:attribute>
    
	<jsp:attribute name="leftBar">
	<div> Welcome ${loggedInUser}!</div>
    </jsp:attribute>
    
	<jsp:body>
    </jsp:body>
</t:genericpage>
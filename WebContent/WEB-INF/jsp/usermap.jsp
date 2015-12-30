<%@ include file="/WEB-INF/jsp/common_components/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
		
		<link rel="stylesheet" type="text/css" href="css/usermap.css">
		
	</jsp:attribute>
    
	<jsp:body>
	
	<div id="map"></div>
	
	<script src="js/googlemap.js"></script>
	<script async defer src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBOby6rm2T3or27LY1sXDTWcgvjkGUjJHw&callback=initMap"></script>
   
	</jsp:body>

</t:genericpage>
<%@ include file="/WEB-INF/jsp/common_components/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>

<t:genericpage>
	<jsp:attribute name="head">
	
		<!-- <link rel="stylesheet" type="text/css" href="css/home.css">
		<link rel="stylesheet" type="text/css" href="css/posts.css">
		<link rel="stylesheet" type="text/css" href="css/filter.css">
		<script src="js/home.js"></script> 
		<script src="js/posts.js"></script>
		<script src="js/filter.js"></script> -->
		
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		
    </jsp:attribute>
    
	<jsp:body>
	
	
	
	<table>
		<tr>
			<th class="thHeading"></th>
			<th class="thHeading">ID</th>
			<th class="thHeading">Email</th>
			<th class="thHeading">Authority</th>
			<th class="thHeading">Enabled</th> 
			<th class="thHeading">Registered</th>
		</tr>
		
		<c:forEach items="${users}" var="user" varStatus="loop">
		
			<c:choose>
				<c:when test="${loop.index % 2 == 0}">
					<c:set var="backgroundColor" value="#FFFFFF"/>
				</c:when>
				<c:otherwise>
					<c:set var="backgroundColor" value="#E6E6E6"/>
				</c:otherwise>
			</c:choose>
		
			<tr style="background-color: ${backgroundColor};">
				<td class="profileTd">
				
					<div class="profileDiv">
						<div class="image">
							<c:choose>
								<c:when test="${user.hasProfilePic}">
									<a href="myprofile.html?id=${user.id}"><img class="profilePic" src="profile_images/${user.id}.png" alt="Profile pic"></a>
								</c:when>
								<c:otherwise>
									<a href="myprofile.html?id=${user.id}"><img class="profilePic" src="images/profile_default.png" alt="Profile pic"></a>		
								</c:otherwise>
							</c:choose>
						</div>
				
						<div class="username">
							<a href="myprofile.html?id=${user.id}" class="links"><strong><c:out value="${user.username}" /></strong></a>
						</div>
				
					</div>
				
				</td>
				<td>${user.id}</td>
				<td>${user.email}</td>
				<td>${user.authority}</td>
				<td>${user.enabled}</td> 
				<td>${user.registeredDateString}, ${user.registeredTimeString}</td>
			</tr>
		
		</c:forEach>
	</table>
	
	

    </jsp:body>

</t:genericpage>
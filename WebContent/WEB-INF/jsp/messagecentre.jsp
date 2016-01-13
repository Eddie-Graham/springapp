<%@ include file="/WEB-INF/jsp/common_components/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head">

		<link rel="stylesheet" type="text/css" href="css/messagecentre.css">
		<link rel="stylesheet" type="text/css" href="css/common_components/fixedmain.css">
        <%--<link rel="stylesheet" type="text/css" href="css/common_components/filter.css">--%>
        <%--<script src="js/myprofile.js"></script>--%>
        <%--<script src="js/common_components/posts.js"></script>--%>
        <%--<script src="js/common_components/filter.js"></script>--%>



    </jsp:attribute>

    <jsp:body>

        <div id="mainContainer">

            <div id="userList"></div>
            <div id="messages"></div>

        </div>


    </jsp:body>

</t:genericpage>

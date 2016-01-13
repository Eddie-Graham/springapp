<%@ include file="/WEB-INF/jsp/common_components/include.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:genericpage>
	<jsp:attribute name="head">
		
		<script src="js/admin.js"></script>
		<link rel="stylesheet" type="text/css" href="css/admin.css">
		
    </jsp:attribute>

    <jsp:body>

        <table>
            <tr>
                <th class="thHeading"></th>
                <th class="thHeading">ID</th>
                <th class="thHeading">Username</th>
                <th class="thHeading">Email</th>
                <th class="thHeading">Authority</th>
                <th class="thHeading">Enabled</th>
                <th class="thHeading">Password</th>
                    <%-- <th class="thHeading">Registered</th> --%>
                <th class="thHeading"></th>
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
                                        <a href="myprofile.html?id=${user.id}"><img class="profilePic"
                                                                                    src="profile_images/${user.id}.png"
                                                                                    alt="Profile pic"></a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="myprofile.html?id=${user.id}"><img class="profilePic"
                                                                                    src="images/profile_default.png"
                                                                                    alt="Profile pic"></a>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="username">
                                <a href="myprofile.html?id=${user.id}" class="links"><strong><c:out
                                        value="${user.username}"/></strong></a>
                            </div>

                        </div>

                    </td>
                    <td>${user.id}</td>
                    <td><input type="text" id="username_${user.id}" value="${user.username}"></td>
                    <td><input type="text" id="email_${user.id}" value="${user.email}"></td>
                    <td><input type="text" id="authority_${user.id}" value="${user.authority}"></td>
                    <td>

                        <select id="enabled_${user.id}">
                            <c:choose>
                                <c:when test="${user.enabled}">
                                    <option selected="selected" value="true">true</option>
                                    <option value="false">false</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="true">true</option>
                                    <option selected="selected" value="false">false</option>
                                </c:otherwise>
                            </c:choose>
                        </select>

                    </td>
                    <td><input type="text" id="password_${user.id}" placeholder="New Password"></td>
                        <%-- <td>${user.registeredDateString}</td> --%>

                    <td>
                        <button class="pure-button pure-button-primary" onclick="updateUser('${user.id}');">Save
                        </button>
                    </td>

                </tr>

            </c:forEach>
        </table>

    </jsp:body>

</t:genericpage>
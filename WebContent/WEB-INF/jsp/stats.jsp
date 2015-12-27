<%@ include file="/WEB-INF/jsp/include.jsp"%>

<table>
 <!--  <tr>
    <th>Month</th>
    <th>Savings</th>
  </tr> -->
  
  <tr>
    <td colspan="2"><h3>${username}'s stats</h3></td>
  </tr>
  <tr>
    <td>No of main posts:</td>
    <td id="0" class="stat"></td>
  </tr>
  <tr style="background-color: #E6E6E6;">
    <td>My main posts total likes:</td>
    <td id="1" class="stat"></td>
  </tr>
  <tr style="background-color: #E6E6E6;">
    <td>My main posts total dislikes:</td>
    <td id="2" class="stat"></td>
  </tr>
  <tr>
    <td>Main posts I have liked:</td>
    <td id="3" class="stat"></td>
  </tr>
  <tr>
    <td>Main posts I have disliked:</td>
    <td id="4" class="stat"></td>
  </tr>
  <tr>
    <td colspan="2">------------------------------------------------------------------------------------------------------------------------------------------</td>
  </tr>
  <tr>
    <td>No of replies:</td>
    <td id="5" class="stat"></td>
  </tr>
  <tr style="background-color: #E6E6E6;">
    <td >My reply posts total likes:</td>
    <td id="6" class="stat"></td>
  </tr>
  <tr style="background-color: #E6E6E6;">
    <td>My reply posts total dislikes:</td>
    <td id="7" class="stat"></td>
  </tr>
  <tr>
    <td>Reply posts I have liked:</td>
    <td id="8" class="stat"></td>
  </tr>
  <tr>
    <td>Reply posts I have disliked:</td>
    <td id="9" class="stat"></td>
  </tr>
</table>

<script>

<c:forEach items="${stats}" var="stat" varStatus="loop">

	<c:choose>
		<%-- indexes of like and dislike stats --%>
		<c:when test="${loop.index eq 1 || loop.index eq 2 || loop.index eq 6 || loop.index eq 7}">

			<c:choose>
				<c:when test="${stat gt 0}">

					$("#${loop.index}").html("${stat}");
					$("#${loop.index}").css('color', '#199A19');
					$("#${loop.index}").css('font-weight', 'bold');

				</c:when>
				<c:when test="${stat lt 0}">

					$("#${loop.index}").html("${stat}");
					$("#${loop.index}").css('color', '#FF0000');
					$("#${loop.index}").css('font-weight', 'bold');

				</c:when>
				<c:otherwise>

					$("#${loop.index}").html("${stat}");
					$("#${loop.index}").css('color', '#777');

				</c:otherwise>
			</c:choose>

		</c:when>
		<c:when test="${stat ne 0}">

			$("#${loop.index}").html("${stat}");
			$("#${loop.index}").css('color', '#777');
			$("#${loop.index}").css('font-weight', 'bold');

		</c:when>
		<c:otherwise>

			$("#${loop.index}").html("${stat}");
			$("#${loop.index}").css('color', '#777');

		</c:otherwise>
	</c:choose>
	
</c:forEach>

</script>
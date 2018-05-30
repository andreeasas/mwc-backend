<%@ page import="com.mwc.domain.Member" %>

<header>
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">Manage expenses</a>
    </div>
    
    <c:if test="${pageContext.request.userPrincipal.name != null}">
    <ul class="nav navbar-nav">
      <li class="active"><a href="#">Home</a></li>
      
      
      <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Select member <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <% if (session.getAttribute("members") == null) { %>
        	<li>Nothing to show</li>
		<% } else {%>
		    <c:forEach items='<%= session.getAttribute("members") %>' var="item">
		    	<c:if test='true'> 
		    	
				  <li><a href="switchMember?id=${item.id}" data-member-id="${item.id}" style="${item.id == sessionScope.selectedMemberId ? 'font-weight:bold' : ''}">${item.name}</a></li>
				</c:if>
				
			</c:forEach>
		<% } %>
        
        </ul>
      </li>
      <li><a href="#">Expenses</a></li>
      <li><a href="${contextPath}/categories">Categories</a></li>
      <li><a href="${contextPath}/statistics">Statistics</a></li>
    </ul>
    </c:if>
    
    <ul class="nav navbar-nav navbar-right">
    
    <c:choose>
    <c:when test="${pageContext.request.userPrincipal.name != null}">
    <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Welcome ${pageContext.request.userPrincipal.name}</a></li>
        <li>
        	<a onclick="document.forms['logoutForm'].submit()">Logout</a>
        </li>
    </c:when>    
    <c:otherwise>
      <li><a href="${contextPath}/registration"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="${contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </c:otherwise>
</c:choose>
    
    </ul>
  </div>
</nav>
</header>
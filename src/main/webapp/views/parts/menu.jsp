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
          <li><a href="#">Member 1</a></li>
          <li><a href="#">Member 2</a></li>
          <li><a href="#">Member 3</a></li>
        </ul>
      </li>
      <li><a href="#">Expenses</a></li>
      <li><a href="#">Categories</a></li>
      <li><a href="#">Statistics</a></li>
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
<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Show statistics</title>
    <%@ include file="parts/header.jsp" %> 
</head>

<body>

<%@ include file="parts/menu.jsp" %>

<div class="container">
	<form id="showCostForm" method="post" class="form-horizontal">
	    <div class="form-group">
	        <label class="col-xs-3 control-label">Start Date</label>
	        <div class="col-xs-5">
	            <input type="text" class="form-control" id="startDate" name="startDate" value="" />
	        </div>
	    </div>
	    
	    <div class="form-group">
	        <label class="col-xs-3 control-label">End Date</label>
	        <div class="col-xs-5">
	            <input type="text" class="form-control" id="endDate" name="endDate" value="" />
	        </div>
	    </div>
	
	    <div class="form-group">
	        <div class="col-xs-5 col-xs-offset-3">
	            <button type="submit" class="btn btn-default">Show statistics</button>
	        </div>
	    </div>
	</form> 

<hr class="hr-separator-thin">

<table class="table table-borderless table-dark">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Category Name</th>
      <th scope="col">Value</th>
      <th scope="col">Percent from Total</th>
    </tr>
  </thead>
  <tbody>
  	<% int i = 1; %> 
  	<c:forEach var="expense" items="${totalExpenses}">
        <tr>
          <td><%= i %> <% i++; %></td>
          <td>${expense.categName}</td>
          <td>${expense.value}</td>
          <td>${expense.percentFromTotal}</td>
        </tr>
   	</c:forEach>
   </tbody>
</table>
	
</div>

<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/statistics.js"></script>
</body>
</html>

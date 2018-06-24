<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Expenses this month</title>
    <%@ include file="parts/header.jsp" %> 
</head>

<body>

<%@ include file="parts/menu.jsp" %>

<div class="container">

<div class="row">
	<h4 class="col-md-6">User expenses this month</h4>
</div>
<hr class="hr-separator">

	<div class="form-group">
		<table id="expenses_table" class="table table-borderless table-dark">
		  <thead>
		    <tr>
		      <th scope="col">Day</th>
		      <th scope="col">Value</th>
		      <th scope="col">Description</th>
		      <th scope="col">Currency</th>
		    </tr>
		  </thead>
		<c:forEach var="costs" items="${userCostsThisMonth}">
			<tbody>
			<tr><td>${costs[0].category.name}</td></tr>
			
		  	<% int i = 1; %> 
		  	<c:forEach var="cost" items="${costs}">
		        <tr>
		          <td>${cost.costDate}</td>
		          <td>${cost.value}</td>
		          <td>${cost.description}</td>
		          <td>${cost.um.code}</td>
		        </tr>
		   	</c:forEach>
		   </tbody>
		</c:forEach>
		</table>
</div>


<div class="row">
	<h4 class="col-md-6">Member expenses this month</h4>
</div>
<hr class="hr-separator">

	<div class="form-group">
		<table id="expenses_table" class="table table-borderless table-dark">
		  <thead>
		    <tr>
		      <th scope="col">Day</th>
		      <th scope="col">Value</th>
		      <th scope="col">Description</th>
		      <th scope="col">Currency</th>
		    </tr>
		  </thead>
		<c:forEach var="costs" items="${memberCostsThisMonth}">
			<tbody>
			<tr><td>${costs[0].category.name}</td></tr>
			
		  	<% int i = 1; %> 
		  	<c:forEach var="cost" items="${costs}">
		        <tr>
		          <td>${cost.costDate}</td>
		          <td>${cost.value}</td>
		          <td>${cost.description}</td>
		          <td>${cost.um.code}</td>
		        </tr>
		   	</c:forEach>
		   </tbody>
		</c:forEach>
		</table>
</div>

</div>

<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
</body>
</html>
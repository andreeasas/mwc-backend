<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<c:forEach var="stat" items="${statistics}">
<div class="form-group">

<table id="expenses_table" class="table table-borderless table-dark">
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
  	<c:forEach var="expense" items="${stat.categoryCostSums}">
        <tr>
          <td><%= i %> <% i++; %></td>
          <td>${expense.categName}</td>
          <td>${expense.value}</td>
          <td>${expense.percentFromTotal}</td>
        </tr>
   	</c:forEach>
   </tbody>
</table>

<div class="form-group">
	<div class="col-xs-offset-3 col-xs-5">
		<h4>Total: ${stat.total}, currency: ${stat.currency}</h4>
	</div>
</div>

</div>
</c:forEach>

<!-- 
<table id="expenses_table" class="table table-borderless table-dark">
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

<div class="form-group">
	<div class="col-xs-offset-3 col-xs-5">
		<h4>Total: ${total}, currency: ${currency}</h4>
	</div>
</div>
-->
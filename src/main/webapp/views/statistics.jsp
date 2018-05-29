<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="parts/meta.jsp" %> 
	<title>Show statistics</title>
	<%@ include file="parts/header.jsp" %> 
</head>

<body>

<%@ include file="parts/menu.jsp" %>

<div class="container">
	<form id="showCostForm" method="post" class="form-horizontal">
	    <div class="form-group">
	        <label class="col-xs-3 control-label">startDate</label>
	        <div class="col-xs-5">
	            <input type="text" class="form-control" id="startDate" name="startDate" value="" />
	        </div>
	    </div>
	    
	    <div class="form-group">
	        <label class="col-xs-3 control-label">endDate</label>
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
    <tr>
      <th scope="row">1</th>
      <td>food</td>
      <td>55</td>
      <td>55%</td>
    </tr>
    <tr>
      <th scope="row">2</th>
      <td>transport</td>
      <td>20</td>
      <td>20%</td>
    </tr>
    <tr>
      <th scope="row">3</th>
      <td>bills</td>
      <td>25</td>
      <td>25%</td>
    </tr>
  </tbody>
</table>
	
</div>

<%@ include file="parts/footer.jsp" %> 

</body>
</html>
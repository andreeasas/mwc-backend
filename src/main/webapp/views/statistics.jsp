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
	    	<div class="col-xs-offset-3 col-xs-5">
			    <select class="selectpicker" id="statisticsType" style="margin:auto">
				  <option>User and Members</option>
				  <option>User</option>
				  <option>Member</option>
				</select>
			</div>
	  	</div>
	
	    <div class="form-group">
	        <div class="col-xs-5 col-xs-offset-3">
	            <button type="button" class="btn btn-default" onclick="showStatistics()">Show statistics</button>
	            <div class="loader hidden"></div>
	        </div>
	    </div>
	</form> 

<hr class="hr-separator">

<div id="statisticsDataContainer">
</div>

</div>

<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/statistics.js"></script>
</body>
</html>

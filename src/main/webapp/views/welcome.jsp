<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <title>Welcome</title>
    <%@ include file="parts/header.jsp" %> 
</head>

<body>
<%@ include file="parts/menu.jsp" %>

<div class="container">

    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <form id="logoutForm" method="POST" action="${contextPath}/logout">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

        <h2>Welcome ${pageContext.request.userPrincipal.name}</h2>

    </c:if>
    
    <hr class="hr-separator">
    
    <div id="chartContainer" style="height: 370px; width: 100%;"></div>

</div>
<!-- /container -->

<%@ include file="parts/footer.jsp" %> 
</body>
<script>

window.onload = function() {

var dataPoints = [];

var options =  {
	animationEnabled: true,
	theme: "light2",
	title: {
		text: "Expenses this month"
	},
	axisX: {
		valueFormatString: "DD MMM YYYY",
	},
	axisY: {
		title: "Expenses",
		titleFontSize: 24,
		includeZero: false
	},
	data: [{
		type: "spline", 
		yValueFormatString: "$#,###.##",
		dataPoints: dataPoints
	}]
};

function addData(data) {
	
	for (var i = 0; i < data.length; i++) {
		dataPoints.push({
			x: new Date(data[i].dateMili),
			y: data[i].expenseValue
		});
	}
	$("#chartContainer").CanvasJSChart(options);

}

addData(${expenseDateTuple});

}
</script>
</html>

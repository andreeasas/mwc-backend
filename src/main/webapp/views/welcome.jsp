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
		text: "Expenses"
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
			x: new Date(data[i].date),
			y: data[i].units
		});
	}
	$("#chartContainer").CanvasJSChart(options);

}


addData([
	   {
		      "date":1506796200000,
		      "units":1353
		   },
		   {
		      "date":1506882600000,
		      "units":1123
		   },
		   {
		      "date":1506969000000,
		      "units":1022
		   },
		   {
		      "date":1507055400000,
		      "units":1224
		   },
		   {
		      "date":1507141800000,
		      "units":1068
		   },
		   {
		      "date":1507228200000,
		      "units":1236
		   },
		   {
		      "date":1507314600000,
		      "units":1445
		   },
		   {
		      "date":1507401000000,
		      "units":1343
		   },
		   {
		      "date":1507487400000,
		      "units":1323
		   },
		   {
		      "date":1507573800000,
		      "units":1432
		   },
		   {
		      "date":1507660200000,
		      "units":1556
		   },
		   {
		      "date":1507746600000,
		      "units":1306
		   },
		   {
		      "date":1507833000000,
		      "units":1294
		   },
		   {
		      "date":1507919400000,
		      "units":1385
		   },
		   {
		      "date":1508005800000,
		      "units":1479
		   },
		   {
		      "date":1508092200000,
		      "units":1242
		   },
		   {
		      "date":1508178600000,
		      "units":1105
		   },
		   {
		      "date":1508265000000,
		      "units":1432
		   },
		   {
		      "date":1508351400000,
		      "units":1056
		   },
		   {
		      "date":1508437800000,
		      "units":1014
		   },
		   {
		      "date":1508524200000,
		      "units":932
		   },
		   {
		      "date":1508610600000,
		      "units":1278
		   },
		   {
		      "date":1508697000000,
		      "units":1456
		   },
		   {
		      "date":1508783400000,
		      "units":1524
		   },
		   {
		      "date":1508869800000,
		      "units":1109
		   },
		   {
		      "date":1508956200000,
		      "units":1124
		   },
		   {
		      "date":1509042600000,
		      "units":1085
		   },
		   {
		      "date":1509129000000,
		      "units":1330
		   },
		   {
		      "date":1509215400000,
		      "units":1223
		   },
		   {
		      "date":1509301800000,
		      "units":1323
		   },
		   {
		      "date":1509388200000,
		      "units":1423
		   }
		]);

}
</script>
</html>

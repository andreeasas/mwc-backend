<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%@ include file="parts/meta.jsp" %> 
	<title>Show statistics</title>
	<%@ include file="parts/header.jsp" %> 
</head>

<body>

<%@ include file="parts/menu.jsp" %>

<div class="container">
	<form>
		<label>start date</label>
		<input type="day"/></br>
		
		<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
    </form>
</div>

<%@ include file="parts/footer.jsp" %> 

</body>
</html>
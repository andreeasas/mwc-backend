<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Manage user specifics</title>
    <%@ include file="parts/header.jsp" %> 
</head>


<body>
<%@ include file="parts/menu.jsp" %>
	
<div class="container">
	
<form id="showUserSpecifics" method="post" class="form-horizontal">
	<div class="form-group">
		<h3 class="col-md-6">User specifics</h3>
	</div>
		
	<div class="form-group">
	    <label class="col-xs-3 control-label">Default currency</label>
	    <div class="col-xs-3">
			<select class="selectpicker" id="currencyCode" style="margin:auto">
				<c:forEach var="code" items="${currencyCodes}">
					<option>${code}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<div class="col-xs-5 col-xs-offset-3">
	    	<button type="button" class="btn btn-default" >Change currency</button>
	    	<div class="loader hidden"></div>
	    </div>
	</div>
</form>
<hr class="hr-separator">

<div class="row">
	<h3 class="col-md-6">User members</h3>
	<button type="button" class="btn btn-info col-md-6 pull-right" style="width:25%;" >Add member</button>
</div>
<hr class="hr-separator-thin">
	
<div class="panel-group" id="according-member-categories" role="tablist" aria-multiselectable="true">
	<c:forEach var="member" items="${members}" varStatus="loop">
		<div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading_member_${loop.index}">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#according-member-categories" href="#collapse_member_${loop.index}" aria-expanded="true" aria-controls="collapse_member_${loop.index}">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        ${member.getName() }
                    </a>
                </h4>
            </div>
            <div id="collapse_member_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_member_${loop.index}">
                <div class="panel-body">
                      <button type="button" class="btn btn-secondary" onclick="editMember(${member.getId() }, '${member.getName() }')">Edit member</button>
                      <button type="button" class="btn btn-danger" onclick="deleteCategory($(this), ${member.getId() })">Delete member</button>
                </div>
            </div>
            <input type="hidden" value="${member.getId() }">
       	</div>
	</c:forEach>      

</div><!-- panel-group -->	

	
</div>
	
<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/categories.js"></script> 
</body>

</html>
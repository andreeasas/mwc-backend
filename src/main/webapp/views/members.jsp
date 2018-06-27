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
	    	<button type="button" class="btn btn-default" onclick="changeDefaultCurrency()">Change currency</button>
	    	<div class="loader hidden"></div>
	    </div>
	</div>
</form>
<hr class="hr-separator">

<div class="row">
	<h3 class="col-md-6">User members</h3>
	<button type="button" class="btn btn-info col-md-6 pull-right" style="width:25%;" onclick="addMember()">Add member</button>
</div>
<hr class="hr-separator-thin">
	
<div class="panel-group" id="accordionMembers" role="tablist" aria-multiselectable="true">
	<c:forEach var="member" items="${members}" varStatus="loop">
		<div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading_member_${loop.index}">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordionMembers" href="#collapse_member_${loop.index}" aria-expanded="true" aria-controls="collapse_member_${loop.index}">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        ${member.getName() }
                    </a>
                </h4>
            </div>
            <div id="collapse_member_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_member_${loop.index}">
                <div class="panel-body">
                      <button type="button" class="btn btn-secondary" onclick="editMember(${member.getId() }, '${member.getName() }')">Edit member</button>
                      <button type="button" class="btn btn-danger" onclick="deleteMember($(this), ${member.getId() })">Delete member</button>
                </div>
            </div>
            <input type="hidden" value="${member.getId() }">
       	</div>
	</c:forEach>      
</div><!-- panel-group -->
	
<div class="panel panel-default" id="memberContainerHidden" style="display: none">
            <div class="panel-heading" role="tab" id="heading_member_hidden">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion-member-categories" href="#collapse_member_hidden" aria-expanded="true" aria-controls="collapse_member_hidden">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        member_name
                    </a>
                </h4>
            </div>
            <div id="collapse_member_hidden" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_member_hidden">
                <div class="panel-body">
                      <button type="button" class="btn btn-secondary" onclick="editMember(0, '')">Edit member</button>
                      <button type="button" class="btn btn-danger" onclick="deleteMember(this), 0)">Delete member</button>
                </div>
            </div>
            <input type="hidden" value="${member.getId() }">
</div>

<!-- modal form - add member -->
<form id="addMemberForm" method="post" class="form-horizontal" style="display: none;">
    <div class="form-group">
        <label class="col-xs-3 control-label">Member name</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="name" value="" />
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-5 col-xs-offset-3">
            <button type="submit" class="btn btn-default">Create</button>
        </div>
    </div>
</form>

<!-- modal form - edit member -->
<form id="editMemberForm" method="post" class="form-horizontal" style="display: none;">
	<div class="form-group" style="display: none;">
        <label class="col-xs-3 control-label">ID</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="id" disabled="disabled" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label">Member name</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="name" value="" />
        </div>
    </div>

    <div class="form-group">
        <div class="col-xs-5 col-xs-offset-3">
            <button type="submit" class="btn btn-default">Save</button>
        </div>
    </div>
</form>   
	
</div>
	
<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/members.js"></script> 
</body>

</html>
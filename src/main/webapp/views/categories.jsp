<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Create an account</title>
    <%@ include file="parts/header.jsp" %> 
</head>

<body>
<%@ include file="parts/menu.jsp" %>

<div class="container">

<div class="row">
	<h2 class="col-md-6">Manage categories</h2>
</div>
<hr class="hr-separator">

<div class="row">
	<h3 class="col-md-6">User categories</h3>
	<button type="button" class="btn btn-info col-md-6 pull-right" style="width:25%;" onclick="addCategory('user')">Add category</button>
</div>
<hr class="hr-separator-thin">

<div class="panel-group" id="accordion-member-categories" role="tablist" aria-multiselectable="true">
	<c:forEach var="category" items="${userSpecificCategories}" varStatus="loop">
		<div class="panel panel-default">
            <div class="panel-heading" role="tab" id="heading_member_${loop.index}">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion-member-categories" href="#collapse_member_${loop.index}" aria-expanded="true" aria-controls="collapse_member_${loop.index}">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        ${category.getName() }
                    </a>
                </h4>
            </div>
            <div id="collapse_member_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_member_${loop.index}">
                <div class="panel-body">
                      <button type="button" class="btn btn-secondary" onclick="editCategory(${category.getId() }, '${category.getName() }')">Edit category</button>
                      <button type="button" class="btn btn-danger" onclick="deleteCategory($(this), ${category.getId() })">Delete category</button>
                      <button type="button" class="btn btn-info" onclick="addCost(${category.getId() })">Add cost</button>
                </div>
            </div>
            <input type="hidden" value="${category.getId() }">
       	</div>
	</c:forEach>      

</div><!-- panel-group -->


<div class="row">
	<h3 class="col-md-6">Member categories</h3>
	<button type="button" class="btn btn-info col-md-6 pull-right" style="width:25%;" onclick="addCategory('member')">Add category</button>
</div>
<hr class="hr-separator-thin">

	<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		<c:forEach var="category" items="${memberSpecificCategories}" varStatus="loop">
			<div class="panel panel-default">
	            <div class="panel-heading" role="tab" id="heading_${loop.index}">
	                <h4 class="panel-title">
	                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse_${loop.index}" aria-expanded="true" aria-controls="collapse_${loop.index}">
	                        <i class="more-less glyphicon glyphicon-plus"></i>
	                        ${category.getName() }
	                    </a>
	                </h4>
	            </div>
	            <div id="collapse_${loop.index}" class="panel-collapse collapse" role="tabpanel" aria-labelledby="heading_${loop.index}">
	                <div class="panel-body">
	                      <button type="button" class="btn btn-secondary" onclick="editCategory(${category.getId() }, '${category.getName() }')">Edit category</button>
	                      <button type="button" class="btn btn-danger" onclick="deleteCategory($(this), ${category.getId() })">Delete category</button>
	                      <button type="button" class="btn btn-info" onclick="addCost(${category.getId() })">Add cost</button>
	                </div>
	            </div>
	            <input type="hidden" value="${category.getId() }">
	       	</div>
		</c:forEach>      
	</div><!-- panel-group -->


<!-- The form which is used to populate the item data -->
<form id="editCategoryForm" method="post" class="form-horizontal" style="display: none;">
	<div class="form-group" style="display: none;">
        <label class="col-xs-3 control-label">ID</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="id" disabled="disabled" />
        </div>
    </div>
    <div class="form-group">
        <label class="col-xs-3 control-label">Category name</label>
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

<!-- The form which is used to populate the item data -->
<form id="addCategoryForm" method="post" class="form-horizontal" style="display: none;">
    <div class="form-group">
        <label class="col-xs-3 control-label">Category name</label>
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

<!-- The form which is used to populate the item data -->
<form id="addCostForm" method="post" class="form-horizontal" style="display: none;">
    <div class="form-group">
        <label class="col-xs-3 control-label">Date</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" id="costDate" name="costDate" value="" />
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-xs-3 control-label">Amount</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="amount" value="" />
        </div>
    </div>
    
    <div class="form-group">
        <label class="col-xs-3 control-label">Description</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="description" value="" />
        </div>
    </div>
    
    <div class="form-group">
    	<label class="col-xs-3 control-label">Currency</label>
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
            <button type="submit" class="btn btn-default">Create</button>
        </div>
    </div>
</form> 

</div>
<!-- /container -->

<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/categories.js"></script> 
</body>
</html>

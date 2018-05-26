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

<h2>User categories</h2>
<hr class="hr-separator">

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
		<c:forEach var="category" items="${userSpecificCategories}" varStatus="loop">
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
	                      <button type="button" class="btn btn-danger" onclick="deleteCategory(${category.getId() })">Delete category</button>
	                      <button type="button" class="btn btn-info" onclick="addCost(${category.getId() })">Add cost</button>
	                </div>
	            </div>
	            <input type="hidden" value="${category.getId() }">
        	</div>
		</c:forEach>      

    </div><!-- panel-group -->

<h2>Member categories</h2>
<hr class="hr-separator">

<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">

        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingOne">
                <h4 class="panel-title">
                    <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        Collapsible Group Item #1
                    </a>
                </h4>
            </div>
            <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                <div class="panel-body">
                      <button type="button" class="btn btn-secondary">Edit category</button>
                      <button type="button" class="btn btn-danger">Delete category</button>
                      <button type="button" class="btn btn-info">Add cost</button>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingTwo">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        Collapsible Group Item #2
                    </a>
                </h4>
            </div>
            <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                <div class="panel-body">
                    <button type="button" class="btn btn-secondary">Edit category</button>
                     <button type="button" class="btn btn-danger">Delete category</button>
                     <button type="button" class="btn btn-info">Add cost</button>
                </div>
            </div>
        </div>

        <div class="panel panel-default">
            <div class="panel-heading" role="tab" id="headingThree">
                <h4 class="panel-title">
                    <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                        <i class="more-less glyphicon glyphicon-plus"></i>
                        Collapsible Group Item #3
                    </a>
                </h4>
            </div>
            <div id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                <div class="panel-body">
                    <button type="button" class="btn btn-secondary">Edit category</button>
                      <button type="button" class="btn btn-danger">Delete category</button>
                      <button type="button" class="btn btn-info">Add cost</button>
                </div>
            </div>
        </div>

    </div><!-- panel-group -->


<!-- The form which is used to populate the item data -->
<form id="editCategoryForm" method="post" class="form-horizontal" style="display: none;">
	<div class="form-group">
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

</div>
<!-- /container -->

<%@ include file="parts/footer.jsp" %>
<script src="//oss.maxcdn.com/bootbox/4.2.0/bootbox.min.js"></script>
<script src="${contextPath}/resources/js/categories.js"></script> 
</body>
</html>

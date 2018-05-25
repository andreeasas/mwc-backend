<%@ include file="parts/prefix.jsp" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <%@ include file="parts/meta.jsp" %> 
    <title>Create an account</title>
    <%@ include file="parts/header.jsp" %> 
</head>

<body>
<%@ include file="parts/menu.jsp" %>

<div class="container">

<!--
Data items are displayed in table cells.
Each edit button has data-id="..." attribute to indicate the id of item

<div class="table-responsive">
    <table class="table table-bordered table-striped">
        <thead>
            <tr>
                <th>ID</th>
                <th>Full name</th>
                <th>Email</th>
                <th>Website</th>
                <th>Edit</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>1</td>
                <td>Leanne Graham</td>
                <td>Sincere@april.biz</td>
                <td>hildegard.org</td>
                <td><button type="button" data-id="1" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>2</td>
                <td>Ervin Howell</td>
                <td>Shanna@melissa.tv</td>
                <td>anastasia.net</td>
                <td><button type="button" data-id="2" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>3</td>
                <td>Clementine Bauch</td>
                <td>Nathan@yesenia.net</td>
                <td>ramiro.info</td>
                <td><button type="button" data-id="3" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>4</td>
                <td>Patricia Lebsack</td>
                <td>Julianne.OConner@kory.org</td>
                <td>kale.biz</td>
                <td><button type="button" data-id="4" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>5</td>
                <td>Chelsey Dietrich</td>
                <td>Lucio_Hettinger@annie.ca</td>
                <td>demarco.info</td>
                <td><button type="button" data-id="5" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>6</td>
                <td>Mrs. Dennis Schulist</td>
                <td>Karley_Dach@jasper.info</td>
                <td>ola.org</td>
                <td><button type="button" data-id="6" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>7</td>
                <td>Kurtis Weissnat</td>
                <td>Telly.Hoeger@billy.biz</td>
                <td>elvis.io</td>
                <td><button type="button" data-id="7" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>8</td>
                <td>Nicholas Runolfsdottir V</td>
                <td>Sherwood@rosamond.me</td>
                <td>jacynthe.com</td>
                <td><button type="button" data-id="8" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>9</td>
                <td>Glenna Reichert</td>
                <td>Chaim_McDermott@dana.io</td>
                <td>conrad.com</td>
                <td><button type="button" data-id="9" class="btn btn-default editButton">Edit</button></td>
            </tr>
            <tr>
                <td>10</td>
                <td>Clementina DuBuque</td>
                <td>Rey.Padberg@karina.biz</td>
                <td>ambrose.net</td>
                <td><button type="button" data-id="10" class="btn btn-default editButton">Edit</button></td>
            </tr>
        </tbody>
    </table>
</div>
-->

<h2>User categories</h2>
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
<form id="userForm" method="post" class="form-horizontal" style="display: none;">
    <div class="form-group">
        <label class="col-xs-3 control-label">ID</label>
        <div class="col-xs-3">
            <input type="text" class="form-control" name="id" disabled="disabled" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Full name</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="name" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Email</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="email" />
        </div>
    </div>

    <div class="form-group">
        <label class="col-xs-3 control-label">Website</label>
        <div class="col-xs-5">
            <input type="text" class="form-control" name="website" />
        </div>
    </div>
     <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/>

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

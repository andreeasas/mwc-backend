$(document).ready(function() {
	$('.panel-group').on('hidden.bs.collapse', toggleIcon);
	$('.panel-group').on('shown.bs.collapse', toggleIcon);
	
	$( function() {
		$( "#costDate" ).datepicker();
	});
	
	$('#addCostForm')
        .on('submit', function(e) {
            // Save the form data via an Ajax request
            e.preventDefault();

            var $form = $(e.target),
				cost_category_id  = $('#addCostForm').attr("data-category_id"),
				cost_date    = $form.find('[name="costDate"]').val(),
				cost_amount    = $form.find('[name="amount"]').val(),
				cost_description    = $form.find('[name="description"]').val(),
            	cost_currency_code = $('#currencyCode').val(); 

            $.ajax({
                url: '/addCost/' + cost_category_id,
                method: 'POST',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
				},
                data: JSON.stringify({
                    cost_date: cost_date,
					amount: cost_amount,
					description: cost_description,
					currency_code: cost_currency_code
                })
            ,success:function(response) {
                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The cost was added');
            }});
    });
	
	$('#addCategoryForm')
        .on('submit', function(e) {
            // Save the form data via an Ajax request
            e.preventDefault();

            var $form = $(e.target),
            	cat_owner = $('#addCategoryForm').attr("data-category_owner"),
				cat_name    = $form.find('[name="name"]').val();

            // Async call
            $.ajax({
                url: '/addCategory',
                method: 'POST',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
				},
                data: JSON.stringify({
                	owner: cat_owner,
                    name: cat_name
                })
            ,success:function(response) {
                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The category was added');
                
                // Display category
                if (cat_owner =='user'){
                $('#accordionUserCategories').prepend($('#categoryContainerHidden').clone());
                }
                else {
                	$('#accordionMemberCategories').prepend($('#categoryContainerHidden').clone());	
                }
                var newCatContainerId = 'category_'+response.result.id;
                
                $('#categoryContainerHidden').first().attr('id', newCatContainerId);
                $('#' + newCatContainerId).css('display', 'block');
                $('#' + newCatContainerId + ' .panel-heading').attr('id', 'heading_' + response.result.id);
                $('#' + newCatContainerId + ' .panel-heading h4 a').attr('href', '#collapse_' + response.result.id);
                $('#' + newCatContainerId + ' .panel-heading h4 a').attr('aria-controls', 'collapse_' + response.result.id);
                
                $('#' + newCatContainerId + ' .panel-heading h4 a').html('<i class="more-less glyphicon glyphicon-plus"></i>' + response.result.name);
                
                $('#' + newCatContainerId + ' .panel-collapse.collapse').attr('id', 'collapse_' + response.result.id);
                $('#' + newCatContainerId + ' .panel-collapse.collapse').attr('aria-labelledby', 'heading_' + response.result.id);
                
                $('#' + newCatContainerId + ' .btn.btn-secondary').attr('onclick', `editCategory(${response.result.id}, '${response.result.name}')`);
                $('#' + newCatContainerId + ' .btn.btn-danger').attr('onclick', `deleteCategory($(this), ${response.result.id})`);
                $('#' + newCatContainerId + ' .btn.btn-info').attr('onclick', `addCost(${response.result.id})`);
                
                $('#' + newCatContainerId + ' input').val(response.result.id);
                
            },error:function(response) {
                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

        		bootbox.alert(response.responseJSON.message);
            }});
	});
		
    $('#editCategoryForm')
        .on('submit', function(e) {
            // Save the form data via an Ajax request
            e.preventDefault();

            var $form = $(e.target),
                cat_id    = $form.find('[name="id"]').val(),
				cat_name    = $form.find('[name="name"]').val();

            // The url and method might be different in your application
            $.ajax({
                url: '/updateCategory',
                method: 'POST',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
				},
                data: JSON.stringify({
                    id: cat_id,
                    name: cat_name,
                })
            ,success:function(response) {

                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The category name was updated');
                
                // Display new category name
                var catContainerId = 'category_'+cat_id;
                $('#' + catContainerId + ' .panel-heading h4 a').html('<i class="more-less glyphicon glyphicon-plus"></i>' + cat_name);
                
            },error:function(response) {
	                // Hide the dialog
	                $form.parents('.bootbox').modal('hide');

	        		bootbox.alert(response.responseJSON.message);
	        }
        	});
	});

    $('.editButton').on('click', function() {
        // Get the record's ID via attribute
        var id = $(this).attr('data-id');

        $.ajax({
            url: 'http://jsonplaceholder.typicode.com/users/' + id,
            method: 'GET'
        ,success:function(response) {
            // Populate the form fields with the data returned from server
            $('#userForm')
                .find('[name="id"]').val(response.id).end()
                .find('[name="name"]').val(response.name).end()
                .find('[name="email"]').val(response.email).end()
                .find('[name="website"]').val(response.website).end();

            // Show the dialog
            bootbox
                .dialog({
                    title: 'Edit the user profile',
                    message: $('#userForm'),
                    show: false // We will show it manually later
                })
                .on('shown.bs.modal', function() {
                    $('#userForm')
                        .show()                             // Show the login form
                     
                })
                .on('hide.bs.modal', function(e) {
                    // Bootbox will remove the modal (including the body which contains the login form)
                    // after hiding the modal
                    // Therefor, we need to backup the form
                    $('#userForm').hide().appendTo('body');
                })
                .modal('show');
        }});
    });
    
    
});

function toggleIcon(e) {
    $(e.target)
        .prev('.panel-heading')
        .find(".more-less")
        .toggleClass('glyphicon-plus glyphicon-minus');
}

function addCategory(categ_owner) {
	
	$('#addCategoryForm').attr("data-category_owner",categ_owner);
	
	// Show the dialog
	bootbox
		.dialog({
			title: 'Add new expense category',
			message: $('#addCategoryForm'),
			show: false // We will show it manually later
		})
		.on('shown.bs.modal', function() {
			$('#addCategoryForm')
				.show()                             // Show the category form
			 
		})
		.on('hide.bs.modal', function(e) {
			// Bootbox will remove the modal (including the body which contains the category form)
			// after hiding the modal
			// Therefor, we need to backup the form
			$('#addCategoryForm').hide().appendTo('body');
		})
		.modal('show');
}

function editCategory(id, name) {
	//alert("edit");
	
	$('#editCategoryForm')
			.find('[name="id"]').val(id).end()
			.find('[name="name"]').val(name).end();
			
	// Show the dialog
	bootbox
		.dialog({
			title: 'Edit category name',
			message: $('#editCategoryForm'),
			show: false // We will show it manually later
		})
		.on('shown.bs.modal', function() {
			$('#editCategoryForm')
				.show()                             // Show the login form
			 
		})
		.on('hide.bs.modal', function(e) {
			// Bootbox will remove the modal (including the body which contains the login form)
			// after hiding the modal
			// Therefor, we need to backup the form
			$('#editCategoryForm').hide().appendTo('body');
		})
		.modal('show');
}

function deleteCategory(elem, id) {

	$.ajax({
		url: '/deleteCategory/'+id,
		method: 'DELETE',
		headers: { 
			'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
		}
	,success:function(response) {
		elem.closest(".panel").remove();
		
		bootbox.alert('The category was deleted.');
	},error:function(response) {
		
		bootbox.alert(response.responseJSON.message);
	}});
}

function addCost(id) {
	
	$('#addCostForm').attr("data-category_id",id);
	
	$('#addCostForm')
	.find('[name="amount"]').val("").end()
	.find('[name="description"]').val("").end();
	
	// Show the dialog
	bootbox
		.dialog({
			title: 'Add cost',
			message: $('#addCostForm'),
			show: false // We will show it manually later
		})
		.on('shown.bs.modal', function() {
			$('#addCostForm')
				.show()                             // Show the login form
			 
		})
		.on('hide.bs.modal', function(e) {
			// Bootbox will remove the modal (including the body which contains the login form)
			// after hiding the modal
			// Therefor, we need to backup the form
			$('#addCostForm').hide().appendTo('body');
		})
		.modal('show');
}
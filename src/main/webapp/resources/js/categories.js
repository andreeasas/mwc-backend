$(document).ready(function() {
	$('.panel-group').on('hidden.bs.collapse', toggleIcon);
	$('.panel-group').on('shown.bs.collapse', toggleIcon);
	
	
    $('#editCategoryForm')
        .on('submit', function(e) {
            // Save the form data via an Ajax request
            e.preventDefault();
			
			alert("edit");

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
                alert(response);

                // Hide the dialog
                $form.parents('.bootbox').modal('hide');

                // You can inform the user that the data is updated successfully
                // by highlighting the row or showing a message box
                bootbox.alert('The category name is updated');
            }});
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

function deleteCategory(id) {
	alert("delete");
}

function addCost(id) {
	alert("cost");
}
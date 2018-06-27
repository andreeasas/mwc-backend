$(document).ready(function() {
	
	$('#addMemberForm')
    .on('submit', function(e) {
        // Save the form data via an Ajax request
        e.preventDefault();

        var $form = $(e.target),
			member_name    = $form.find('[name="name"]').val();

        // The url and method might be different in your application
        $.ajax({
            url: '/addMember',
            method: 'POST',
			headers: { 
				'Accept': 'application/json',
				'Content-Type': 'application/json',
				'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
			},
            data: JSON.stringify({
                name: member_name
            })
        ,success:function(response) {
            debugger;
        	// Hide the dialog
            $form.parents('.bootbox').modal('hide');

            // You can inform the user that the data is updated successfully
            // by highlighting the row or showing a message box
            bootbox.alert('The member was added');
            
            // Display member
            $('#accordionMembers').prepend($('#memberContainerHidden').clone());
            debugger
            var newMemberContainerId = 'member_'+response.result.id;
            
            $('#memberContainerHidden').first().attr('id', newMemberContainerId);
            $('#' + newMemberContainerId).css('display', 'block');
            $('#' + newMemberContainerId + ' .panel-heading').attr('id', 'heading_' + response.result.id);
            $('#' + newMemberContainerId + ' .panel-heading h4 a').attr('href', '#collapse_' + response.result.id);
            $('#' + newMemberContainerId + ' .panel-heading h4 a').attr('aria-controls', 'collapse_' + response.result.id);
            
            $('#' + newMemberContainerId + ' .panel-heading h4 a').html('<i class="more-less glyphicon glyphicon-plus"></i>' + response.result.name);
            
            $('#' + newMemberContainerId + ' .panel-collapse.collapse').attr('id', 'collapse_' + response.result.id);
            $('#' + newMemberContainerId + ' .panel-collapse.collapse').attr('aria-labelledby', 'heading_' + response.result.id);
            
            $('#' + newMemberContainerId + ' .btn.btn-secondary').attr('onclick', `editMember(${response.result.id}, '${response.result.name}')`);
            $('#' + newMemberContainerId + ' .btn.btn-danger').attr('onclick', `deleteMember($(this), ${response.result.id})`);
            
            $('#' + newMemberContainerId + ' input').val(response.result.id);
            
        },error:function(response) {
            // Hide the dialog
            $form.parents('.bootbox').modal('hide');

    		bootbox.alert(response.responseJSON.message);
        }});
});
	
	 $('#editMemberForm')
     .on('submit', function(e) {
         // Save the form data via an Ajax request
         e.preventDefault();

         var $form = $(e.target),
            member_id    = $form.find('[name="id"]').val(),
			member_name    = $form.find('[name="name"]').val();

         // The url and method might be different in your application
         $.ajax({
             url: '/updateMember',
             method: 'POST',
				headers: { 
					'Accept': 'application/json',
					'Content-Type': 'application/json',
					'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
				},
             data: JSON.stringify({
                 id: member_id,
                 name: member_name,
             })
         ,success:function(response) {

             // Hide the dialog
             $form.parents('.bootbox').modal('hide');

             // You can inform the user that the data is updated successfully
             // by highlighting the row or showing a message box
             bootbox.alert('The member\'s name was updated');
         },error:function(response) {
	                // Hide the dialog
	                $form.parents('.bootbox').modal('hide');

	        		bootbox.alert(response.responseJSON.message);
	        }
     	});
	});	
	
	
});

function addMember() {
	
	// Show the dialog
	bootbox
		.dialog({
			title: 'Add new member',
			message: $('#addMemberForm'),
			show: false // We will show it manually later
		})
		.on('shown.bs.modal', function() {
			$('#addMemberForm')
				.show()                             // Show the member form
			 
		})
		.on('hide.bs.modal', function(e) {
			// Bootbox will remove the modal (including the body which contains the member form)
			// after hiding the modal
			// Therefor, we need to backup the form
			$('#addMemberForm').hide().appendTo('body');
		})
		.modal('show');
}

function deleteMember(elem, id) {

	$.ajax({
		url: '/deleteMember/'+id,
		method: 'DELETE',
		headers: { 
			'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
		}
	,success:function(response) {
		elem.closest(".panel").remove();
		
		bootbox.alert('The member was deleted.');
	},error:function(response) {
		elem.closest(".panel").remove();
		
		bootbox.alert(response.responseJSON.message);
	}});
}

function editMember(id, name) {
	//alert("edit");
	
	$('#editMemberForm')
			.find('[name="id"]').val(id).end()
			.find('[name="name"]').val(name).end();
			
	// Show the dialog
	bootbox
		.dialog({
			title: 'Edit member name',
			message: $('#editMemberForm'),
			show: false
		})
		.on('shown.bs.modal', function() {
			$('#editMemberForm')
				.show()
		})
		.on('hide.bs.modal', function(e) {
			$('#editMemberForm').hide().appendTo('body');
		})
		.modal('show');
}

function changeDefaultCurrency() {
	var currencyCode = $("#currencyCode").val();

	$.ajax({
        url: '/changeCurrency',
        method: 'POST',
		headers: { 
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
		},
        data: JSON.stringify({
            currency_code: currencyCode
        })
		,success:function(response) {
			bootbox.alert('Default currency for user was changed.');
		}
		,error: function(xhr, ajaxOptions, thrownError){
			bootbox.alert('Default currency for user could not be changed.');
		},
		timeout : 15000
	});
}
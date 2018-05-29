$(document).ready(function() {
	$('.panel-group').on('hidden.bs.collapse', toggleIcon);
	$('.panel-group').on('shown.bs.collapse', toggleIcon);
	
	$( function() {
		$( "#startDate" ).datepicker();
	});
	
	$( function() {
		$( "#endDate" ).datepicker();
	});

}
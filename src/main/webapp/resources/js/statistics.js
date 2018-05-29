$(document).ready(function() {
	
	$( function() {
		$( "#startDate" ).datepicker();
	});
	
	$( function() {
		$( "#endDate" ).datepicker();
	});
	
});

function showStatistics() {
	
	var startDate = $("#startDate").val(); 
	var endDate = $("#endDate").val(); 
	
	$.ajax({
        url: '/showStatistics',
        method: 'POST',
		headers: { 
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
		},
        data: JSON.stringify({
            start_date: startDate,
			end_date: endDate,
			target: 0
        })
    ,success:function(response) {
    	// read response
    	
    	//update html page with response data
    }});

}
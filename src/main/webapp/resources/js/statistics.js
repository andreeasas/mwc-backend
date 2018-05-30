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
	
	$(".loader").removeClass("hidden");
	
	$.ajax({
        url: '/showStatistics',
        method: 'GET',
		headers: { 
			'Accept': 'application/json',
			'Content-Type': 'application/json',
			'X-CSRF-TOKEN': $('meta[name="_csrf"]').attr('content')	
		},
		data: {
			start_date: startDate,
			end_date: endDate,
			target: 0
		}
		,success:function(response) {
			$("#statisticsDataContainer").html( response );
			$(".loader").addClass("hidden");
		}
		,error: function(xhr, ajaxOptions, thrownError){
          //what to do in error
		  $(".loader").addClass("hidden");
		},
		timeout : 15000//timeout of the ajax call
	});

}
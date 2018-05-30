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
	var statisticsType = $('#statisticsType').val();
	
	$(".loader").removeClass("hidden");
	
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
			statistics_type: statisticsType
        })
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
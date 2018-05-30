$(document).ready(function() {
	
	$('#dropdown-menu li a').click(function(e){
	  e.preventDefault();
	  
	  var memberId = $(this).data("member-id");
	  window.location.replace('/switchMember/' + memberId);
	  
	});
	
    
    
});
$(document).ready(function() {
	$(".dropdown-button").dropdown();
    $('select').material_select();   
    $('table').on('click', 'td', function() {    
    	$('td').removeClass('active');
    	if($(this).hasClass("active")){
    	 	   $(this).removeClass('active');    		
    	 	} else {
    	 		$(this).addClass('active');
    	 	}
  });
});


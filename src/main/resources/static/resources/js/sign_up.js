$(document).ready(function(){
		$flag=1;
    	$("#name").focusout(function(){
    		if($(this).val()==''){
        		$(this).css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			 $("#error_name").text("* You have to enter your name!");
        	}
        	else
        	{
        		$(this).css("border-color", "#2eb82e");
        		$('#submit').attr('disabled',false);
        		$("#error_name").text("");

        	}
       });
        $("#email").focusout(function(){
    		if($(this).val()==''){
        		$(this).css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			$("#error_email").text("* You have to enter your email!");
        	}
        	else
        	{
        		$(this).css("border-color", "#2eb82e");
        		$('#submit').attr('disabled',false);
        		$("#error_email").text("");
        	}
       });
       //  $("#dob").focusout(function(){
    // 	if($(this).val()==''){
    //  		$(this).css("border-color", "#FF0000");
    //  			$('#submit').attr('disabled',true);
    //  			$("#error_dob").text("* You have to enter your Date of Birth!");
    //  	}
    //  	else
    //  	{
    //  		$(this).css("border-color", "#2eb82e");
    //  		$('#submit').attr('disabled',false);
    //  		$("#error_dob").text("");
    //  	}
    // });
        $("#password").focusout(function(){
    		if($(this).val()==''){
        		$(this).css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			$("#error_password").text("* You have to enter your password!");
        	}
        	else
        	{
        		$(this).css({"border-color":"#2eb82e"});
        		$('#submit').attr('disabled',false);
        		$("#error_password").text("");

        	}
        	});
        $("#credit_card").focusout(function(){
            $card =$("#credit_card").val();
    		if($(this).val()==''){
        		$(this).css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			$("#error_credit_card").text("* You have to enter your Credit Card Number!");
        	}
        	else if ($card.length!=10)
        	{   
                    $(this).css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			$("#error_credit_card").text("* Length of Credit Card Number Should Be Ten");
        	}
        	else{
        		$(this).css({"border-color":"#2eb82e"});
        		$('#submit').attr('disabled',false);
        		$("#error_credit_card").text("");
        	}

    	});


   		$( "#submit" ).click(function() {
   			if($("#name" ).val()=='')
   			{
        		$("#name").css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			 $("#error_name").text("* You have to enter your name!");
        	}
        	if($("#email" ).val()=='')
   			{
        		$("#email").css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			 $("#error_email").text("* You have to enter your email!");
        	}
   			if($("#password" ).val()=='')
   			{
        		$("#password").css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			 $("#error_password").text("* You have to enter password!");
        	}
        	if($("#credit_card" ).val()=='')
   			{
        		$("#credit_card").css("border-color", "#FF0000");
        			$('#submit').attr('disabled',true);
        			 $("#error_credit card").text("* You have to enter your credit card number!");
        	}
        	else if ($("#credit_card" ).val().length!=10)
            {
                $(this).css("border-color", "#FF0000");
                $('#submit').attr('disabled',false);
                $("#error_credit_card").text("* Length of Credit Card Number Should Be Ten");
            }
            else{
                $(this).css({"border-color":"#2eb82e"});
                $('#submit').attr('disabled',false);
                $("#error_credit_card").text("");
            }
			});


    	
	});
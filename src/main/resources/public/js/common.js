    function check(input) {
        if (input.value != document.getElementById('password').value) {
            input.setCustomValidity('Password Must be Matching.');
        } else {
            input.setCustomValidity('');
        }
    }
    
    function validate(obj)
    {
      if(!obj.checkValidity())
      {
    	  $([id$="Help"]).each(function() {
    		  var element = $("#"+obj.id+"Help");
    		  element.show();
    		  });
      }else{
    	  $("#"+obj.id+"Help").hide();
      }
    }
    
    function transformToJSON(){
    		var formJson =  '{';
       		$("input[type='text'], input[type='Password']").each(function(){
       			if ($(this).prop('id') != 'confirmPassword') {
       			formJson= formJson +'"'+$(this).prop('id')+'": "'+$(this).val() +'",';
       			}
       		});
       		formJson = formJson.substring(0, formJson.length - 1);
       		formJson = formJson +"}";
       		return formJson;
    	}
    function handleBackEndErrors(error){
    	$("#step2").hide();
    	$("#emailError").hide();
    	$("#nameError").hide();
		$("#step1").show();
		$("#StepError").show();
		if (error.status==500){
			var exception = error.responseJSON.exception;
			var message = error.responseJSON.message;
			if (exception.indexOf("DataIntegrityViolationException")>0){
				if(message.indexOf("REGISTRATION(EMAIL)")>0){
					$("#emailError").show();
				}
				if(message.indexOf("REGISTRATION(NAME)")>0){
					$("#nameError").show();
				}
			}
		}
    }

    $(document).ready(function(){
    	$("small").hide();
    	
    	$("input").blur(function() {
    		if ($("form")[0].checkValidity()){
    			$("#btn1").prop("disabled", false);
    		} else {
    			$("#btn1").prop("disabled", true);
    		}
    	});
    	
    	
    	$('#btn1').click(function() {
       		var formJson = transformToJSON();
       		//alert(formJson);
   	 		$.ajax({
	  			url:"/rest/registration",
	  			type:"POST",
	  			data:formJson,
	  			contentType:"application/json",
	  			success: function(){
	  				$("#step1").hide();
	  				$("#step2").show();
	  			},
	  			error: function(error){
	  				handleBackEndErrors(error);
	  			}
	  			});
    	});
    });
function RegisterUtil()
{	
	var _request = undefined;
	
	var _servicePath = "services/profile/validateRegisterId";
	
	this.setValidateServicePath=function(servicePath)
	{
		_servicePath = servicePath;
	};
	
	this.initialize=function()
	{
		var _registerId = document.getElementById("registerId");
		
		_registerId.onchange = function()
		{
			validateRegisterId(_registerId.value);
		};
		_registerId.onfocus = function()
		{
			clearInputFieldValidationError("registerId","register-input-field");
		};
		
		var _registerForm = document.getElementById("registerForm");
		
		_registerForm.onsubmit = function()
		{
			var _valid = continueFormSubmit();
			
			return _valid;
		};
		
		var _displayName = document.getElementById("displayName");
		
		_displayName.onfocus = function()
		{
			clearInputFieldValidationError("displayName","register-input-field");
		};		
		
		var _email = document.getElementById("email");
		
		_email.onblur = function()
		{
			var _valid = emailValidator(_email.value);
			
			if(!_valid)
			{
				showInputFieldValidationError("email","register-input-field-error");
			}
		};
		_email.onfocus = function()
		{
			clearInputFieldValidationError("email","register-input-field");
		};
		
//		var _emailConfirm = document.getElementById("emailConfirm");
//		
//		_emailConfirm.onblur = function()
//		{
//			if(_email.value.length==0)return;
//						
//			if(_emailConfirm.value!=_email.value)
//			{
//				showInputFieldValidationError("emailConfirm","register-input-field-error");
//			}
//		};
//		_emailConfirm.onfocus = function()
//		{
//			clearInputFieldValidationError("emailConfirm","register-input-field");
//		};		
		
		var _password = document.getElementById("password");
		
		_password.onblur = function()
		{
			if(_password.value.length==0)return;
			
			var _validation = passwordValidator(_password.value);
										
			if(!_validation.success)
			{
				showInputFieldValidationErrorMsg("password",_validation.msg);
			}
		};
		_password.onfocus = function()
		{
			clearInputFieldValidationError("password","register-input-field");
		};		
		
		var _passwordConfirm = document.getElementById("passwordConfirm");
		
		_passwordConfirm.onblur = function()
		{
			if(_password.value.length==0)return;
						
			if(_passwordConfirm.value!=_password.value)
			{
				showInputFieldValidationError("passwordConfirm","register-input-field-error");
			}
		};
		_passwordConfirm.onfocus = function()
		{
			clearInputFieldValidationError("passwordConfirm","register-input-field");
		};
		
		if(_registerId.value.length>0)validateRegisterId(_registerId.value);
	};

	
	function validateRegisterId(registerId)
	{		
		if(!_request)
		{
			_request = validationRequest(registerId,onValidationResponse);
		}	
	};	
	
	function onValidationResponse(value)
	{		
		if(value == true)
		{
			removeAttribute("email","disabled");
//			removeAttribute("emailConfirm","disabled");
			removeAttribute("password","disabled");
			removeAttribute("passwordConfirm","disabled");
			removeAttribute("submitButton","disabled");
			removeAttribute("displayName", "disabled");
			updateAttribute("registerId","disabled","disabled");
			
			document.getElementById("hiddenRegisterId").value = document.getElementById("registerId").value;
		}
		else
		{
			showInputFieldValidationError("registerId","register-input-field-error");
		}
		_request = undefined;
	}
			
	function validationRequest(registerId,callback)
	{	
		var _request = new XMLHttpRequest();
						
		var _callback = callback;
		
		_request.onreadystatechange=function()
	    {
			if (_request.readyState==4 && _request.status==200)
			{			
				_callback(_request.responseText=="true");
			}
	    };	 	
		
	    if(registerId==undefined||callback==undefined)return;
	    	
		_request.open("POST",_servicePath,true);
			
		_request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			
		_request.send("id="+registerId);
	    
	}
	
	function emailValidator(_text)
	{
		var _result = String(_text).match(/^([a-z0-9](\.|_|\+|-|%)?)+[a-z0-9]{1}@([a-z0-9](\.|_|-)?)+[a-z0-9]{1}\.[a-z]{2,6}$/gi);
		
		var _value = _result&&_result.length>0;
				
		return _value;
	}
	
	function passwordValidator(_text)
	{
		var _result = String(_text).match(/^[a-zA-Z0-9!#$%&'*+-=?^_`{|}~"(),:;<>@[\]]{6,16}$/gi);
		
		var _value = new Object(); 
		
		_value.success= _result&&_result.length>0;
		
		_value.msg = "Password too short.";
				
		return _value;		
	}
	
	function continueFormSubmit()
	{
		var _email = document.getElementById("email");
		
//		var _emailConfirm = document.getElementById("emailConfirm");
		
		var _displayName = document.getElementById("displayName");
		
		var _password = document.getElementById("password");
		
		var _passwordConfirm = document.getElementById("passwordConfirm");
		
		var _value = emailValidator(_email.value);
		
		if(_displayName.value==null||_displayName.value.length==0)
		{
			showInputFieldValidationError("displayName","register-input-field-error");
			return false;
		}
		
		if(!_value)
		{
			showInputFieldValidationError("email","register-input-field-error");			
			return false;
		}
		
		var _validation = passwordValidator(_password.value);
									
		if(!_validation.success)
		{
			showInputFieldValidationErrorMsg("password",_validation.msg,"register-input-field-error");
			return false;
		}
		
		if(_passwordConfirm.value!=_password.value)
		{
			showInputFieldValidationError("passwordConfirm","register-input-field-error");
			return false;
		}		
				
		return true;
	};	
};

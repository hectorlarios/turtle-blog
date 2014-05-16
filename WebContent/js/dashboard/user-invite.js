function UserInvite()
{	
	this.initialize=function()
	{		
		var _inviteUserForm = document.getElementById("inviteUserForm");
		
		_inviteUserForm.onsubmit = function()
		{
			var _valid = continueFormSubmit();
			
			return _valid;
		};
		
		
		var _email = document.getElementById("email");
		
		_email.onblur = function()
		{
			var _valid = emailValidator(_email.value);
			
			if(!_valid)
			{
				showInputFieldValidationError("email","user-input-field-error");
			}
		};
		_email.onfocus = function()
		{
			clearInputFieldValidationError("email","user-input-field");
		};
		
		var _emailConfirm = document.getElementById("emailConfirm");
		
		_emailConfirm.onblur = function()
		{
			if(_email.value.length==0)return;
						
			if(_emailConfirm.value!=_email.value)
			{
				showInputFieldValidationError("emailConfirm","user-input-field-error");
			}
		};
		_emailConfirm.onfocus = function()
		{
			clearInputFieldValidationError("emailConfirm","user-input-field");
		};		
	};
		
	function continueFormSubmit()
	{
		var _email = document.getElementById("email");
		
		var _emailConfirm = document.getElementById("emailConfirm");
		
		var _value = emailValidator(_email.value);
				
		if(!_value)
		{
			showInputFieldValidationError("email","user-input-field-error");			
			return false;
		}
		
		if(_emailConfirm.value!=_email.value)
		{
			showInputFieldValidationError("emailConfirm","user-input-field-error");			
			return false;			
		}
				
		return true;
	};	
};

function CommentUtil()
{	
	var _request = false;
	
	var _servicePath = "services/profile/data";
	
	var _loginServicePath = "";
	
	var _commentForm = undefined;
	
	var _loginDialog = undefined;
	
	var _skipStatusRequest = false;
	
	this.setProfileDataServicePath=function(servicePath)
	{
		_servicePath = servicePath;
	};
	
	this.setLoginServicePath=function(loginServicePath)
	{
		_loginServicePath = loginServicePath;
	}
	
	this.initialize=function()
	{		
		_commentForm = document.getElementById("commentForm");
		
		_commentForm.onsubmit = function()
		{
			var _valid = continueFormSubmit();
			
			if(!_skipStatusRequest&&_valid)
			{				
				_valid = false;
				
				validateLoginState();
			}			

			_skipStatusRequest = false;
			
			return _valid;
		};
		
		var _comment = document.getElementById("comment");
		
		_comment.onfocus = function()
		{
			clearInputFieldValidationError("comment","blog-comment-textarea");
		};
	};
	
	function validateLoginState()
	{		
		if(!_request)
		{
			_request = loginStatusRequest(onLoginStatusResponse);
		}	
	};	
	
	function onLoginStatusResponse(value)
	{		
		if(value == true)
		{
			_skipStatusRequest = true;
			
			_commentForm.submit();	
		}
		else
		{
			_loginDialog = new LoginDialog();
			
			_loginDialog.setLoginServicePath(_loginServicePath);
			
			_loginDialog.callback = onLoginStatusResponse;
			
			_loginDialog.show();
		}
		_request = false;
	}
	
	function loginStatusRequest(callback)
	{	
		var _request = new XMLHttpRequest();
						
		var _callback = callback;
		
		_request.onreadystatechange=function()
	    {
			if (_request.readyState==4 && _request.status==200)
			{
				var _xml = _request.responseXML;
				
				var _status = _xml.getElementsByTagName("loggedIn")[0].childNodes[0].nodeValue  == "true";
				
				_callback(_status);
			}
	    };	 	
		
	    if(callback==undefined)return false;
	    	
		_request.open("GET",_servicePath,true);
						
		_request.send();
		
		return true;
	}
	
	function continueFormSubmit()
	{
		var _comment = document.getElementById("comment");
		
		if(_comment.value.length==0)
		{
			showInputFieldValidationError("comment","blog-comment-textarea-error");
			
			return false;	
		}
				
		return true;
	};	
};

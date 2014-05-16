var _loginDialogInstance;

function LoginDialog() 
{   
	if(_loginDialogInstance)
	{
		_loginDialogInstance.hide();
	}
    
    this.callback = undefined;
	
	this.id = createRandomName(22);
	
	var _loginServicePath = "/services/profile/login";
	
    var _div = document.createElement("div");
    
    var _blocker = document.createElement("div");
    
    var _content = document.createElement("div");
    
    _loginDialogInstance = this;
    
    _div.id = this.id+"Container";
    _div.style.zIndex = "1";    
    _div.style.width="100%";    
    _div.style.height="100%";
    _div.style.position = "fixed";
    _div.style.display = "none";
    
    _blocker.id = this.id+"Blocker";
    _blocker.style.backgroundColor = "#000000";//"#ADD6FF";    
    _blocker.style.width="100%";    
    _blocker.style.height="100%";
    _blocker.style.opacity = 0.75;
    _blocker.style.position = "absolute";
    _blocker.style.filter = "alpha(opacity=75)";
    
    _content.id = this.id+"Content";
    _content.style.position = "absolute";
    _content.innerHTML = "<div class='login-header-container'>Sign in</div>"+
	"	<div class='login-container'>"+	
	"		<table cellspacing='0' cellpadding='0' border='0'>"+					
	"		 <tr>"+
	"		  <td class='login-input-label'>Email</td>"+
	"		 </tr>"+
	"		 <tr>"+
	"		  <td class='login-table-td'><input type='input' id='email' name='email' class='login-input-field'/></td>"+
	"		 </tr>"+			 
	"		 <tr>"+
	"		  <td class='login-input-label'>Password</td>"+
	"		 </tr>"+
	"		 <tr>"+
	"		  <td class='login-table-td'><input type='password' id='password' name='password' class='login-input-field'/></td>"+
	"		 </tr>"+				 			 
	"		 <tr>"+
	"		  <td align='right'><input type='button' id='submitButton' onclick='_loginDialogInstance.login()' value='Sign In' class='login-submit-button'/>"+
	"		  <input type='button' id='cancelButton' value='Cancel' onclick='_loginDialogInstance.hide()' class='login-submit-button'/></td>"+
	"		 </tr>"+   
	"		</table>"+	
	"	</div>";   	
    	
    _div.appendChild(_blocker);
    _div.appendChild(_content);
        
    document.body.appendChild(_div);
    
    this.show = function()
    {
    	_div.style.display="";
    	_content.style.top = ((_blocker.offsetHeight-_content.offsetHeight)/2)+"px";
    	_content.style.left = ((_blocker.offsetWidth-_content.offsetWidth)/2)+"px";
    };
    
    this.hide = function () 
    {    	
    	_loginDialogInstance = null;
    	
    	document.body.removeChild(_div);
    };  
    
    this.login = function()
    {
    	var _email = document.getElementById("email").value;
    	
    	var _password = document.getElementById("password").value;
    	
    	doLogin(_email, _password,onLoginResponse);
    };   
    
    this.setLoginServicePath=function(loginServicePath)
    {
    	_loginServicePath = loginServicePath;
    };
    
	function doLogin(email,password,callback)
	{	
		var _request = new XMLHttpRequest();
		
		var _callback = callback;
		
		_request.onreadystatechange=function()
	    {
			if (_request.readyState==4 && _request.status==200)
			{			
				var _xml = _request.responseXML;
				
				var _status = _xml.getElementsByTagName("loggedIn")[0].childNodes[0].nodeValue  == "true";
								
				if(_callback)_callback(_status);
			}
	    };	 	
		
	    if(email==undefined||password==null||callback==undefined)return;
	    	
		_request.open("POST",_loginServicePath,true);
			
		_request.setRequestHeader("Content-type","application/x-www-form-urlencoded");
			
		_request.send("email="+email+"&password="+password+"&redirect=false");
	    
	}
	
	function onLoginResponse(value)
	{		
		if(value)
		{
			_loginDialogInstance.callback(value);
			
			_loginDialogInstance.hide();
		}
	}
}

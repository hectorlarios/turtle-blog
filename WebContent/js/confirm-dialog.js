var _confirmDialogInstance;

function ConfirmDialog(title,message,okCallback, cancelCallback) 
{   
	if(!message||!okCallback)return;
	
	if(_confirmDialogInstance)
	{
		_confirmDialogInstance.hide();
	}
    	
	this.id = createRandomName(22);
	
	var _okCallback = okCallback;
	
	var _cancelCallback = cancelCallback;
	
	var _message = message;
	
	var _title = title?title:"Confirm";
	
    var _div = document.createElement("div");
    
    var _blocker = document.createElement("div");
    
    var _content = document.createElement("div");
    
    _confirmDialogInstance = this;
    
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
    _content.innerHTML = "<div class='confirm-header-container'>"+_title+"</div>"+
	"	<div class='confirm-container'>" +
	"		<div class='confirm-body'>" +_message+"</div>"+	
	"		<div class='confirm-controls'>" +
	"         <input type='button' id='submitButton' onclick='_confirmDialogInstance.onClickOk()' value='Ok' class='confirm-submit-button'/>"+
	"		  <input type='button' id='cancelButton' value='Cancel' onclick='_confirmDialogInstance.onClickCancel()' class='confirm-submit-button'/>"+
	"		</div>"+	
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
    	_confirmDialogInstance = null;
    	
    	document.body.removeChild(_div);
    };  
    
    this.onClickOk = function()
    {
    	if(_okCallback)_okCallback();
    	
    	this.hide();
    };   
    
    this.onClickCancel = function()
    {
    	if(_cancelCallback)_cancelCallback();
    	
    	this.hide();
    };    
}

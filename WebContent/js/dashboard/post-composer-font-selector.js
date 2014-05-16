var _fontSelectorInstance = undefined;

function FontSelector(type) 
{   
	if(_fontSelectorInstance)
	{
		_fontSelectorInstance.hide();
	}
	
	this.id = createRandomName(22);
	
	var _owner = null;
	
    var _div = document.createElement("div");
    
    _fontSelectorInstance = this;
    
    _div.id = this.id+"Div";

    _div.style.backgroundColor = "#ADD6FF";

    _div.style.zIndex = "1";

    _div.style.position = "absolute";

    //_div.style.width = "180px";
    
    _div.style.padding = "5px";
    
    //_div.style.paddingTop = "2px";
    
    _div.style.border = "1px solid #7AA3CC";
    
    document.body.appendChild(_div);
    
    var _isSizeType = type == "size";

	var _value = "<table cellpadding='0' cellspacing='0' border='0'>";
	
	var _sizes = ["1","2","3","4","5","6","7"]; 
	
	var _fonts = ["Arial","Comic Sans MS","Courier New","Lucida Console","Tahoma","Times New Roman","Trebuchet MS","Verdana"];
	
	var i = 0;
	
	var l = _isSizeType?_sizes.length:_fonts.length;
	
	for(i=0;i<l;i++)
	{		
		_value+="<tr><td style='padding:0px;margin:0px;text-align:center'>";
				
		if(_isSizeType)
		{
			_value+="<a href='#' tabindex='-1' style='text-decoration:none;color:#4b5064;' onclick='_fontSelectorInstance.onSizeSelected(\""+_sizes[i]+"\")'><font size='"+_sizes[i]+"'>Size</font></a>";
		}
		else
		{
			_value+="<a href='#' tabindex='-1' style='text-decoration:none;color:#4b5064;font-family:"+_fonts[i]+"' onclick='_fontSelectorInstance.onFontSelected(\""+_fonts[i]+"\")'>"+_fonts[i]+"</a>";
		}
		
		_value+="</td></tr>";
	}
	_value+="</table>";	
	
    this.getOwner = function () { return _owner; };

    this.setOwner = function (owner) 
    {
        _owner = owner;
        
        var _parent = owner;

        var _offsetTop = 0;

        var _offsetLeft = 0;

        while (_parent) 
        {
            if (!isNaN(_parent.offsetTop)) _offsetTop += _parent.offsetTop;

            if (!isNaN(_parent.offsetLeft)) _offsetLeft += _parent.offsetLeft;

            _parent = _parent.offsetParent;
        }
        
        _div.style.top = (_offsetTop+owner.offsetHeight) + "px";

        _div.style.left = (_offsetLeft-owner.clientLeft) + "px";
    };
    
    this.show = function(){_div.innerHTML = _value;};
    
    this.hide = function () 
    {
    	_div.innerHTML = "";
    	
    	_owner.cp = null;
    	
    	_owner = null; 
    	
    	window[this.id] = null;
    	
    	_fontSelectorInstance = null;
    	
    	document.body.removeChild(_div);
    };
    
    this.onSizeSelected=function(size)
    {
    	_owner.onSizeSelected(size);
    	
    	this.hide();
    };  
    
    this.onFontSelected=function(fontName)
    {
    	_owner.onFontSelected(fontName);
    	
    	this.hide();
    };     
}

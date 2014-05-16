var _colorPickerInstance;

function ColorPicker() 
{   
	if(_colorPickerInstance)
	{
		_colorPickerInstance.hide();
	}
	
	this.id = createRandomName(22);
	
	var _owner = null;
	
    var _div = document.createElement("div");
    
    _colorPickerInstance = this;
    
    _div.id = this.id+"Div";

    _div.style.backgroundColor = "#ADD6FF";

    _div.style.zIndex = "1";
    
    _div.style.width="180px";

    _div.style.position = "absolute";
    
    _div.style.padding = "2px";
    
    _div.style.border = "1px solid #7AA3CC";
    
    document.body.appendChild(_div); 
        
	var _increment = 54;	
	
	var _maxcol=Math.ceil(256/_increment)+1;
	
	var i = 0;
	
	var l = _maxcol;							
	
	var _colCount=0;

	var _value = "<table cellpadding='0' cellspacing='0' border='0' style='padding:0px;margin:0px;'><tr>";
	
	for(i=0;i<l;i++)
	{
		var _red = Math.min(i*_increment,255);
		
		if(i==0)
		_value+="<td>";
		else if(i%2==0)
		_value+="</td><td>";
		
		for(var j=0;j<_maxcol;j++)
		{	
			var _green = Math.min(j*_increment,255);
			
			for(var k=0;k<_maxcol;k++)
			{	
				var _blue = Math.min(k*_increment,255);												
				
				var _hex = decimalToHex(_red)+decimalToHex(_green)+decimalToHex(_blue);
				
				_value+="<a href='#' tabindex='-1' onmouseover='_colorPickerInstance.onColorHover(\"#"+_hex+"\")' onclick='_colorPickerInstance.onColorSelected(\"#"+_hex+"\")'><img src='../images/blog-entry-composer-view/color-picker-swatch.png' style='width:10px;height:10px;border:0px;background-color:#"+_hex+";'/></a>";
				
				if(_colCount++>0&&_colCount%(_maxcol*1)==0)
				{											
					_colCount = 0;
					
					_value+="<br/>";											
				}
			}
		}
		
		if(i==(l-1))_value+="</td>";
	}
	_value+="</tr></table>";
	
	_value+="<div id='"+this.id+"PreviewDiv' style='padding:5px;display:none;'>";	
	_value += "<img id='"+this.id+"Img' src='../images/clear-icon.png' style='background:#000000;border:1px solid #000000;width:50px;height:50px;'/> ";
	_value += "<span id='"+this.id+"Span' style='font-size:12px;'></span></div>";	
    
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
    	
    	_colorPickerInstance = null;
    	
    	document.body.removeChild(_div);
    };
    
    this.onColorSelected=function(color)
    {
    	_owner.onColorSelected(color);
    	
    	this.hide();
    };    
    
    this.onColorClear=function()
    {
    	_owner.onColorClear();
    	
    	this.hide();
    };
    
    this.onColorHover=function(color)
    {
    	var _img = document.getElementById(this.id+"Img");
    	
    	_img.style.backgroundColor=color;
    	
    	var _span = document.getElementById(this.id+"Span");
    	
    	_span.innerHTML = String(color).toUpperCase();
    	
    	var _pdiv = document.getElementById(this.id+"PreviewDiv");
    	
    	_pdiv.style.display = "";    	
    };    
}

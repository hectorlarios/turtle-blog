function createRandomName(_length)
{
	var _value = "_";
	
	var _alpha = "abcdefghijklmnopqrstuvwxyz1234567890";
	
	var i=0;
	
	var l = _length+1;
	
	var al = _alpha.length;
	
	var _index;
	
	while(i<l)
	{
		_index = Math.floor(Math.random() * al);
		
		_value += _alpha.substr(_index,1);
		
		i++;			
	}
	
	return _value;	
}

function decimalToHex(d) 
{
    var _value = Number(d).toString(16);
    
    var _length = 2;

    while (_value.length < _length) 
    {
    	_value = "0" + _value;
    }

    return _value;
}

function getShortDateString() 
{
    var _date = new Date();

    var _value = (_date.getMonth() + 1) + "/" + _date.getDay() + "/" + _date.getFullYear();
    
    return _value;
}

function updateStyle(elementId,property,value)
{
	var elem = document.getElementById(elementId);
	
	if(elem)elem.style[property] = value;	
};

function updateAttribute(elementId,attribute,value)
{
	var elem = document.getElementById(elementId);
	
	if(elem)elem.setAttribute(attribute, value);
}

function removeAttribute(elementId,attribute)
{
	var elem = document.getElementById(elementId);
	
	if(elem)elem.removeAttribute(attribute);
} 

function showInputFieldValidationError(inputId,style)
{
	updateAttribute(inputId,"class",style);
	updateStyle(inputId+"ValidationError", "display", "");
}

function showInputFieldValidationErrorMsg(inputId,errorMsg,style)
{		
	var _msg = document.getElementById(inputId+"ValidationErrorMsg");
			
	_msg.innerHTML = errorMsg;
	
	showInputFieldValidationError(inputId,style);
}	

function clearInputFieldValidationError(inputId,style)
{
	updateAttribute(inputId,"class",style);
	updateStyle(inputId+"ValidationError", "display", "none");	
}

function emailValidator(_text)
{
	var _result = String(_text).match(/^([a-z0-9](\.|_|\+|-|%)?)+[a-z0-9]{1}@([a-z0-9](\.|_|-)?)+[a-z0-9]{1}\.[a-z]{2,6}$/gi);
	
	var _value = _result&&_result.length>0;
			
	return _value;
}














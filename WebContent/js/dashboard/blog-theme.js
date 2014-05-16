function BlogTheme()
{
	var _selectedTheme = "default";
	
	this.setSelectedTheme=function(value)
	{
		updateStyle(_selectedTheme+"Img","border","0px solid #FFFFFF");
		
		_selectedTheme = value;
		
		updateStyle(_selectedTheme+"Img","border","2px solid #EE4411");
		
		var _themeName = document.getElementById("themeName");
		
		_themeName.value = value;		
	};
	
//	this.initialize=function()
//	{		
//		var _blogCreateForm = document.getElementById("blogUpdateForm");
//		
//		_blogCreateForm.onsubmit = function()
//		{
//			var _valid = continueFormSubmit();
//			
//			return _valid;
//		};		
//		
//		var _title = document.getElementById("title");
//		
//		_title.onblur = function()
//		{
//			var _valid = titleValidator(_title.value);
//			
//			if(!_valid)
//			{
//				showInputFieldValidationError("title","blog-input-field-error");
//			}
//		};
//		_title.onfocus = function()
//		{
//			clearInputFieldValidationError("title","blog-input-field");
//		};
//		
//		var _folderName = document.getElementById("folderName");
//		
//		_folderName.onblur = function()
//		{
//			var _isValidFolderName = validateFolderName();
//						
//			if(!_isValidFolderName)
//			{
//				showInputFieldValidationError("folderName","blog-url-input-field-error");
//			}
//		};
//		_folderName.onfocus = function()
//		{
//			clearInputFieldValidationError("folderName","blog-url-input-field");
//		};		
//	};
//	
//	function validateFolderName()
//	{
//		var _folderName = document.getElementById("folderName");
//		
//		var _text = new String(_folderName.value);
//		
//		var _result = _text.match(/^([a-z0-9](\.|_|-)?)+[a-z0-9]{1}$/gi);
//		
//		var _value = _result&&_result.length>0;
//				
//		return _value;		
//	}
//	
//	function continueFormSubmit()
//	{
//		var _title = document.getElementById("title");
//		
//		var _isValidFolderName = validateFolderName();
//						
//		if(_title.value.length==0)
//		{
//			showInputFieldValidationError("title","blog-input-field-error");			
//			return false;
//		}
//		
//		if(!_isValidFolderName)
//		{
//			showInputFieldValidationError("folderName","blog-url-input-field-error");			
//			return false;			
//		}
//				
//		return true;
//	};	
};

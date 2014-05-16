function BlogComposerView()
{
	var _self = this;
	
	var _textarea=null;	
	var _iframe=null;
	
	var _menuItemFont;
	var _menuItemSize;
	var _menuItemBold;
	var _menuItemItalic;
	var _menuItemUnderline;
	var _menuItemStrike;	
	var _menuItemSuperScript;
	var _menuItemSubScript;	
	var _menuItemOrderedList;
	var _menuItemUnorderedList;	
//	var _menuItemImage;
	var _menuItemFontColor;
	var _menuItemHighlightColor;
	var _blogComposerForm;
	var _composerTitle;
	var _submit;
	var _preview;
	var _published;
		
	var FONT = "fontName";
	var SIZE = "fontSize";	
	var BOLD = "bold";
	var ITALIC = "italic";
	var UNDERLINE = "underline";
	var STRIKE_THROUGH = "strikeThrough";
	var COLOR = "foreColor";
	var HIGHLIGHT = "backColor";
//	var LINK = "link";
	var SUPERSCRIPT = "superscript";
	var SUBSCRIPT = "subscript";
	var ORDERED_LIST = "insertOrderedList";
	var UNORDERED_LIST = "insertUnorderedList";
//	var IMAGE = "insertImage";
//	var IMAGE_INSERT_SAMPLE = "../images/blog-entry-composer-view/image-insert-sample.png";
	var REMOVE_FORMAT = "removeFormat";
	
	_self.initialize = function()
	{
		_textarea = document.getElementById("composerTextarea");
		
		_iframe = document.getElementById("composerIFrame");
		
		_iframe.contentDocument.write(_textarea.value);
		
		_iframe.contentDocument.body.contentEditable="true";
		
		_iframe.contentDocument.body.hidefocus = "true";
		
		_iframe.contentDocument.designMode = "on";
		
		_iframe.onfocus=function(){clearInputFieldValidationError("composerIFrame","blog-input-field-composerIFrame");};
		
		initializeControls();
	};
	
	function initializeControls()
	{		
		_menuItemFont = document.getElementById("menuItemFont");
		
		_menuItemFont.onFontSelected=function(font)
		{
			onClickExecuteCommand(FONT, font);
		};		
		
		_menuItemFont.onclick=function()
		{
			if(_menuItemFont.cp)
			{
				_menuItemFont.cp.hide();			
				
				return;
			}
			
			_menuItemFont.cp = new FontSelector("font");
			
			_menuItemFont.cp.setOwner(_menuItemFont);
			
			_menuItemFont.cp.show();
		};		
		
		_menuItemFont.onmouseover = function()
		{
			if(_fontSelectorInstance!=undefined&&_fontSelectorInstance.getOwner()!=_menuItemFont)
			{
				_menuItemFont.onclick();
			}
			if(_colorPickerInstance!=undefined)
			{
				_colorPickerInstance.hide();
				
				_menuItemFont.onclick();
			}	
		};
		
		_menuItemSize = document.getElementById("menuItemSize");
		
		_menuItemSize.onSizeSelected=function(size)
		{
			onClickExecuteCommand(SIZE, size);
		};			
		
		_menuItemSize.onclick=function()
		{
			if(_menuItemSize.cp)
			{
				_menuItemSize.cp.hide();			
				
				return;
			}
			
			_menuItemSize.cp = new FontSelector("size");
			
			_menuItemSize.cp.setOwner(_menuItemSize);
			
			_menuItemSize.cp.show();
		};		
		
		_menuItemSize.onmouseover = function()
		{
			if(_fontSelectorInstance!=undefined&&_fontSelectorInstance.getOwner()!=_menuItemSize)
			{
				_menuItemSize.onclick();
			}
			if(_colorPickerInstance!=undefined)
			{
				_colorPickerInstance.hide();
				
				_menuItemSize.onclick();
			}	
		};		
		
		_menuItemBold = document.getElementById("menuItemBold");
		
		_menuItemBold.onclick=function(){onClickExecuteCommand(BOLD);};		
		
		_menuItemItalic = document.getElementById("menuItemItalic");
		
		_menuItemItalic.onclick=function(){onClickExecuteCommand(ITALIC);};
		
		_menuItemUnderline = document.getElementById("menuItemUnderline");
		
		_menuItemUnderline.onclick=function(){onClickExecuteCommand(UNDERLINE);};
		
		_menuItemStrike = document.getElementById("menuItemStrike");
		
		_menuItemStrike.onclick=function(){onClickExecuteCommand(STRIKE_THROUGH);};
		
		_menuItemSuperScript = document.getElementById("menuItemSuperScript");
		
		_menuItemSuperScript.onclick=function(){onClickExecuteCommand(SUPERSCRIPT);};
		
		_menuItemSubScript = document.getElementById("menuItemSubScript");
		
		_menuItemSubScript.onclick=function(){onClickExecuteCommand(SUBSCRIPT);};	
		
		_menuItemOrderedList = document.getElementById("menuItemOrderedList");
		
		_menuItemOrderedList.onclick=function(){onClickExecuteCommand(ORDERED_LIST);};
		
		_menuItemUnorderedList = document.getElementById("menuItemUnorderedList");
		
		_menuItemUnorderedList.onclick=function(){onClickExecuteCommand(UNORDERED_LIST);};		
				
		_menuItemFontColor = document.getElementById("menuItemFontColor");
		
		_menuItemFontColor.onColorSelected=function(color)
		{
			onClickExecuteCommand(COLOR, color);
		};
		_menuItemFontColor.onColorClear=function()
		{
			onClickExecuteCommand(REMOVE_FORMAT);
		};		
		_menuItemFontColor.onclick=function()
		{
			if(_menuItemFontColor.cp)
			{
				_menuItemFontColor.cp.hide();			
				
				return;
			}
			
			_menuItemFontColor.cp = new ColorPicker();
			
			_menuItemFontColor.cp.setOwner(_menuItemFontColor);
			
			_menuItemFontColor.cp.show();
		};		
		
		_menuItemFontColor.onmouseover = function()
		{
			if(_colorPickerInstance!=undefined&&_colorPickerInstance.getOwner()!=_menuItemFontColor)
			{
				_menuItemFontColor.onclick();
			}
			if(_fontSelectorInstance!=undefined)
			{
				_fontSelectorInstance.hide();
				
				_menuItemFontColor.onclick();
			}	
		};		
		
		_menuItemHighlightColor= document.getElementById("menuItemHighlightColor");
		
		_menuItemHighlightColor.onColorSelected=function(color)
		{
			onClickExecuteCommand(HIGHLIGHT, color);
		};
		_menuItemHighlightColor.onColorClear=function()
		{
			onClickExecuteCommand(REMOVE_FORMAT);
		};		
		_menuItemHighlightColor.onclick=function()
		{
			if(_menuItemHighlightColor.cp)
			{
				_menuItemHighlightColor.cp.hide();			
				
				return;
			}
			
			_menuItemHighlightColor.cp = new ColorPicker();
			
			_menuItemHighlightColor.cp.setOwner(_menuItemHighlightColor);
			
			_menuItemHighlightColor.cp.show();
		};	
		
		_menuItemHighlightColor.onmouseover = function()
		{
			if(_colorPickerInstance!=undefined&&_colorPickerInstance.getOwner()!=_menuItemHighlightColor)
			{
				_menuItemHighlightColor.onclick();
			}
			if(_fontSelectorInstance!=undefined)
			{
				_fontSelectorInstance.hide();
				
				_menuItemHighlightColor.onclick();
			}	
		};
		
		_composerTitle = document.getElementById("composerTitle");
		
		_composerTitle.onfocus=function(){clearInputFieldValidationError("composerTitle","blog-input-field-composerTitle");};
		
		_blogComposerForm = document.getElementById("blogComposerForm");
		
		_blogComposerForm.onsubmit = function()
		{			
			_textarea.value = _iframe.contentDocument.body.innerHTML;
			
			var _plainText = _textarea.value.replace(/<[^>]+>|\s|&nbsp;/g,"");
			
			if(_composerTitle.value.length==0)
			{
				showInputFieldValidationError("composerTitle","blog-input-field-composerTitle-error");
				
				return false;
			}
			
			if(_plainText.length==0)
			{
				showInputFieldValidationError("composerIFrame","blog-input-field-composerIFrame-error");

				return false;
			}			

			return true;
		};
		
		_published = document.getElementById("published");
		
		_submit = document.getElementById("submit");
		
		_submit.onclick = function()
		{
			_published.value = "true";
		};
		
		_preview = document.getElementById("preview");
		
		_preview.onclick = function()
		{
			_published.value = "false";
		};
	}
	
	function onClickExecuteCommand(action,param)
	{
		_iframe.contentDocument.execCommand(action,false,param);
	}
    
	function showInputFieldValidationErrorMsg(_inputId,_errorMsg)
	{		
		var _msg = document.getElementById(_inputId+"ValidationErrorMsg");
				
		_msg.innerHTML = _errorMsg;
		
		showInputFieldValidationError(_inputId);
	}	
	
	function clearInputFieldValidationError(_inputId)
	{
		updateAttribute(_inputId,"class","blog-input-field-"+_inputId);
		updateStyle(_inputId+"ValidationError", "display", "none");	
	}   
};
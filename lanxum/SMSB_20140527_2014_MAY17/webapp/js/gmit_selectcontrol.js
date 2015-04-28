//自然人证件类型的过滤
function zjlxFilter(subSelect)
{
    var option=select.options;
    var aryZjdm = new Array(); 
    for(var i = 0,l = option.length,j=0;i<l;i++)
    {
        aryZjdm[j] = option[i];
        j++;
    }
    subSelect.innerHTML=""; 
    for(var k=0;k<aryZjdm.length;k++)
    {
        if(!(aryZjdm[k].value == "01"||aryZjdm[k].value =="80"))
        {//过滤税务登记证“01”，无“80”
            _addOptionItem(subSelect,aryZjdm[k].text,aryZjdm[k].value);
        }
    }
}
//税务登记类型类型需要过滤只显示：纳税登记、临时税务登记、承包承租登记。
function lsSwdjlxFilter(subSelect,arySelect)
{
    subSelect.innerHTML="";
    for(var i=0;i<arySelect.length;i++)
    {
        if(!(arySelect[i][0] == "1"||arySelect[i][0]=="2"||arySelect[i][0]=="4"
        ||arySelect[i][0]=="5"||arySelect[i][0]=="6"||arySelect[i][0]=="7"
        ||arySelect[i][0]=="9"||arySelect[i][0]=="11"||arySelect[i][0]=="12"))
        {
            _addOptionItem(subSelect,arySelect[i][1],arySelect[i][0]);
        }
    }
} 

//税务登记类型类型需要过滤自然人临时登记和自然人税务登记证，报验登记
function swdjlxFilter(subSelect,arySelect)
{
    subSelect.innerHTML="";
    for(var i=0;i<arySelect.length;i++)
    {
        if(!(arySelect[i][0] == "5"||arySelect[i][0]=="6"||arySelect[i][0]=="9"))
        {
            _addOptionItem(subSelect,arySelect[i][1],arySelect[i][0]);
        }
    }
} 

//添加选项到Select对象中
function _addOptionItem(objSelect,text,value,index) 
{
    var node = new Option(text,value,false,false);
    if (index!=null && index != NaN)
    {
        objSelect.options.add(node,index);
    }
    else 
    {
        objSelect.options[objSelect.options.length]=node;
    }
}
//根据dataSource两维数组初始化Select对象
//objSelect:  Select对象
//values:     两维数组，String[][2]; 第二维，1--value,2--text
function _addOptionsToSelect(objSelect, values) 
{
    for (var i=0;i<values.length;i++) 
    {
        _addOptionItem(objSelect,values[i][1],values[i][0]);
    }
}
//选择Option，如果有下一级
function _selectOptionByValue(objSelect, value) 
{
	objSelect.value=value;
	if (objSelect.filter!=null)
	{
	    objSelect.filter.call(objSelect);
	}
}
//
function _selectOptionByText(objSelect, text) 
{
  for (var i=0;i<objSelect.options.length;i++) 
  {
	if (objSelect.options(i).text==text) 
	{
		objSelect.options(i).selected=true;
	}
  }
}
//  用于select控件处理页面自动输入匹配
var _autoselect_keysequence = ""
var _last_updatetime = 0;
var _timeslice = 1000;

// 用于处理select控件自动匹配时backspace按键的支持
function _autoselect_keydown(srcElement)
{
	var selectObj = null;
	if (srcElement==null) 
	{
		selectObj = window.event.srcElement;
	} 
	else 
	{
		selectObj = srcElement
	}
	if (selectObj.tagName.toLowerCase()!="select" || selectObj.disabled==true) 
	{
		return;
	}
	switch(window.event.keyCode)
	{
		case 8:
//			if (_autoselect_keysequence>0) {
				_autoselect_keysequence = _autoselect_keysequence.substr(0,_autoselect_keysequence.length-1);
				_autoselect_matchoption(selectObj);
				window.event.returnValue = false;
//			} 
			break;
	}
}
//用于处理select控件自动匹配时输入keycode获取的支持
function _autoselect_keypress(srcElement)
{
	var selectObj = null;
	if (srcElement==null) 
	{
		selectObj = window.event.srcElement;
	} 
	else 
	{
		selectObj = srcElement
	}
	if (selectObj.tagName.toLowerCase()!="select" || selectObj.disabled==true) 
	{
		return;
	}
	//added by zhangp
	//支持在超出规定的时间段内自动清除存储的keyPressCode
	var d = new Date();            
	var t = d.getTime();            	
	if(t-_last_updatetime>_timeslice)
	{
		_autoselect_keysequence = String.fromCharCode(window.event.keyCode);
	}else
	{
		_autoselect_keysequence += String.fromCharCode(window.event.keyCode);
	}
	_last_updatetime = t;
	_autoselect_matchoption(selectObj);
	//added by zhangp 20031124
	//增加对级连过滤的支持
    if(selectObj.onchange != null && selectObj.onchange != "")
    {
	    selectObj.onchange.call(selectObj);	
    }   
	window.event.returnValue = false;	
}
// 用于查找select控件中自动匹配的选项
function _autoselect_matchoption(selectObj)
{
	if (selectObj==null || selectObj.disabled==true) 
	{
		return;
	}
	if (_autoselect_keysequence.length==0) 
	{
		//当输入的键序列时""时，需要做特殊处理，否则总会停留在最后一个匹配的选项上
		//注意，如果value=""时，选项中又没有对应的value，则selectedIndex=-1，界面显示空白
		//selectObj.value="";
		window.event.returnValue=false;
		return;
	}
	for (var i = 0;i < selectObj.options.length;i++)
	{
		var optionString = selectObj.options[i].value.toLowerCase();
		if (optionString.indexOf(_autoselect_keysequence.toLowerCase()) == 0)
		{
			selectObj.options[i].selected = true;
			window.event.returnValue=false;
			return;
		}		        
	}
}
// 用于处理select控件自动匹配，清除输入的keycode序列
function _autoselect_clearinput() 
{
	_autoselect_keysequence = "";
}
// 用于处理select控件自动匹配，注册对象，自动设置
function _autoselect_object_register(selectObj) 
{
	if (selectObj==null) {
		return;
	}
	selectObj.onfocus=_autoselect_clearinput;
	selectObj.onkeydown=_autoselect_keydown;
	selectObj.onkeypress=_autoselect_keypress;
}
function _setStartWithFilter(supSelect,subSelect,bylength,subDataSource,beforeListener,afterListener)
{
  	supSelect.filter=startWithFilter;
  	supSelect.subSelect=subSelect;
  	subSelect.supSelect=supSelect;
  	supSelect.bylength=bylength;
  	supSelect.subDataSource=subDataSource;
  	supSelect.onchange=doFilter;
  	supSelect.beforeListener=beforeListener;
  	supSelect.afterListener=afterListener;
}
function doFilter()
{
	var srcElement = window.event.srcElement;
	var filter = srcElement.filter;
	filter.call(srcElement);
}
function startWithFilter()
{
	var bylength = this.bylength;
	var byvalue = "";
	if (this.value.length>=bylength)
	{
	    byvalue = this.value.substr(0,bylength);
	}
	var subSelect = this.subSelect;
	var subDataSource = this.subDataSource;
	var beforeListener = this.beforeListener;
	var afterListener = this.afterListener;
	if (beforeListener!=null && beforeListener!="")
	{
		var rtn = beforeListener.call(this,subSelect);
		if (rtn==false)
		{
			return false;
		}
	}
	
	subSelect.innerHTML="";
	if (byvalue.length>0)
	{
    	for(var i=0;i<subDataSource.length;i++)
    	{
    		if(subDataSource[i][0].substr(0,byvalue.length)==byvalue)
    		{
    			_addOptionItem(subSelect,subDataSource[i][1],subDataSource[i][0]);
    		}
    	}
    }
	
	if (afterListener!=null && afterListener!="")
	{
		var rtn = afterListener.call(this,subSelect);
		if (rtn==false)
		{
			return false;
		}
	}
	
}
function addFilter(currentValue,subSelect,bylength,subDataSource)
{
	var byvalue = "";
	if (currentValue.length>=bylength)
	{
	    byvalue = currentValue.substr(0,bylength);
	}
	
	subSelect.innerHTML="";
	if (byvalue.length>0)
	{
    	for(var i=0;i<subDataSource.length;i++)
    	{
    		if(subDataSource[i][0].substr(0,byvalue.length)==byvalue)
    		{
    			_addOptionItem(subSelect,subDataSource[i][1],subDataSource[i][0]);
    		}
    	}
    }	
}
function addFilterWithNull(currentValue,subSelect,bylength,subDataSource)
{
	var byvalue = "";
	if (currentValue.length>=bylength)
	{
	    byvalue = currentValue.substr(0,bylength);
	}
	
	subSelect.innerHTML="";
    	_addOptionItem(subSelect,"","");			
	if (byvalue.length>0)
	{
    	for(var i=0;i<subDataSource.length;i++)
    	{
    		if(subDataSource[i][0].substr(0,byvalue.length)==byvalue)
    		{
    			_addOptionItem(subSelect,subDataSource[i][1],subDataSource[i][0]);
    		}
    	}
    }	
}

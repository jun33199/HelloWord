/***Begin***页面相关的JavaScript脚本 ******************************/
//页面字符串去空格
function killSpace(str)
{
	//去空格 
	var reStr = str.replace(/[ ]/g,"");
	return reStr;
}

/**
 * 判断是否选择了查询列表中的单选框
 * radios 需要进行选中判断的单选框组
 */
function isSelected(radios) 
{
    try 
	{
        // 查询列表中只存在一条记录
        if ( radios.length == null ) 
		{
            if ( radios.checked ) 
			{
                return true;
            }
        } 
		else 
		{
            // 查询列表中存在多条记录
            for ( i = 0; i < radios.length; i++ ) 
			{
                if ( radios[i].checked ) 
				{
                    return true;
                }
            }
        }
        // 没有选择任何单选（复选）框
        return false;
    }
    catch(ex) 
	{
        // 查询列表中没有结果
        return false;
    }
}


/**
 * 取得已经选择的单选框的Value
 * radios 需要进行选中判断的单选框组
 */
function getSelectedValue(radios) 
{
    //var result = new Array();
	var value;
	//只有一条记录
    if ( radios.length == null ) 
	{
        // 序号
		value = radios.value;
        //result[0] = document.forms[0].arrJbbh.value;
        // 计算机代码
        //result[1] = document.forms[0].nsrjsjdms.value;
        
    } 
	else 
	{
        // 遍历所有单选框，查找是否有被选择的单选框
        for ( i = 0; i < radios.length; i++ ) 
		{
            if ( radios[i].checked ) 
			{
				//序号
				value = radios[i].value;
                // 举报编号
                //result[0] = document.forms[0].arrJbbh[i].value;
                // 计算机代码
                //result[1] = document.forms[0].nsrjsjdms[i].value;
                
                break;
            }
        }
    }
    return value;
}

/**
 * 在显示频中间打开新窗口
 * URLStr 新窗口路径
 * width 宽
 * height 高
 * name 新开窗口名称
 */
var popUpWin=0;
function popUpWindow(URLStr, width, height, name) {
    if ( popUpWin )
    {
        if(!popUpWin.closed) popUpWin.close();
    }
	//显示频宽
	var windowWidth = window.screen.width;
	//显示频高
	var windowHeight = window.screen.height;
	//计算新开窗口左边距、上边距
	var left = (windowWidth - width) / 2;
	var top = (windowHeight - height) / 2;
	var tmp = window.open(URLStr, name, 'toolbar=no,location=no,directories=no,status=yes,menub ar=no,scrollbars=yes,resizable=no,copyhistory=yes,width='+width+',height='+height+',left='+left+', top='+top+',screenX='+left+',screenY='+top+'', "_blank");
    return tmp;
}
/***End*****页面相关的JavaScript脚本 *************************************/

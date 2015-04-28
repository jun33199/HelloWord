//daObject检验
function DataSourceCheck(daObject)
{
	daObject.saveData(daObject.getCurrentRow());
	
	var dataSource=daObject.getData();	
	//如果没有设置任何主键的话，先过滤空白行在做主键判断
	delNullRow(daObject);//add by zhangp 2003-10-08
	//判断键值字段是否为空,如果主键=""，则删除此纪录
	var aryResult=isPrimaryKeysNull(daObject);
	while(aryResult[0]!=-1)
	{
		if(aryResult[0]!=-1)
		{
	       	var deleteFlag=isAllCellsNull(daObject,aryResult[0]);
	       	if(deleteFlag == true)
	       	{
	           	daObject.moveto(aryResult[0]+1);
	       	    daObject.deleteRow(aryResult[0]+1);
	       	}
	       	else
	       	{
	       	    alert("第"+(aryResult[0]+1)+"条纪录的第"+(aryResult[1]+1)+"个字段("+daObject.getColumns()[aryResult[1]].columnCaption+")为空！");
	           	daObject.moveto(aryResult[0]+1);
	           	daObject.modifyRow(aryResult[0]+1);
	           	return false;
	       	   	
	       	}
	   	}
	   	//dataSource=daObject.getData();
	   	aryResult=isPrimaryKeysNull(daObject);
	}
	
	
	//过滤掉删除状态的纪录
	var dataSource=daObject.getData();

	//判断字段是否为空
	var aryResult=isKeysNull(daObject);
	while(aryResult[0]!=-1)
	{
		if(aryResult[0]!=-1)
		{
           	alert("第"+(aryResult[0]+1)+"条纪录的第"+(aryResult[1]+1)+"个字段("+daObject.getColumns()[aryResult[1]].columnCaption+")为空！");
           	daObject.moveto(aryResult[0]+1);
           	daObject.modifyRow(aryResult[0]+1);
           	return false;
	   	}
	   	//dataSource=daObject.getData();
	   	aryResult=isKeysNull(daObject);
	}
	
	//出租房屋特殊判断 
	//add by wofei 2009-2-5
	/* if(!checkChuzuFw(daObject)){
		return false;
	} */
	
	//判断键值字段是否有重复的
	//modified by llpei 2003-11-05
	aryResult=isKeysEqual(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
	   alert("第"+(aryResult[0]+1)+"条记录和第"+(aryResult[1]+1)+"条记录的键值字段重复！请重新填写。");
	   daObject.moveto(aryResult[1]+1);
	   daObject.modifyRow(aryResult[1]+1);
	   return false;
	}
	
	
	//判断字段小于最大长度
	aryResult=checkMaxLength(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
		alert("第"+(aryResult[0]+1)+"条纪录的第"+(aryResult[1]+1)+"个字段("+daObject.getColumns()[aryResult[1]].columnCaption+")输入太长！请重新填写。");
		daObject.moveto(aryResult[0]+1);
		daObject.modifyRow(aryResult[0]+1);
		return false;
	}
	
	//特殊输入检查判断字
	aryResult=callSpecialChecker(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
		alert("第"+(aryResult[0]+1)+"条纪录的第"+(aryResult[1]+1)+"个字段("+daObject.getColumns()[aryResult[1]].columnCaption+")输入不正确，请重新填写。");
		daObject.moveto(aryResult[0]+1);
		daObject.modifyRow(aryResult[0]+1);
		return false;
	}
	//判断字段小于最大长度 add for zhengjianhaoma
	aryResult=checkSpecial(dataSource);
	//alert("checkSpecial");
	if(aryResult!=null && aryResult[0]!=-1 && aryResult[1]!=-1){
	    daObject.moveto(aryResult[0]+1);
	    daObject.modifyRow(aryResult[0]+1);
	    return false;
	}
	return true;
}

/**
 * 出租房屋特殊判断 
 * add by wofei 2009-2-5
 */
 function checkChuzuFw(daObject){
	//从daObject中取得所有的数据数组
	var dataSource=daObject.getData();
	//获取数据数组的行数
	var numRow=dataSource.length;
	//如果行数大于 0 
	if(numRow>0)
	{
		//获取数组的列数
	   	var numCol=daObject.getColumns().length;
		
		var col_czfwyz=0;
		var col_xgrczbs=0;

		for(j=0;j<numCol;j++)
		{
			if(daObject.getColumns()[j].columnCaption=="出租房屋原值")
			{
				col_czfwyz=j;
			}
			if(daObject.getColumns()[j].columnCaption=="是否按市场价格向个人出租用于居住的住房")
			{
				col_xgrczbs=j;
			}
		}
		
		if(col_czfwyz==0||col_xgrczbs==0)
		{
			return true;
		}
		
		for(i=0;i<numRow;i++)
		{
			alert(parseInt(dataSource[i][col_xgrczbs]));
			if(((dataSource[i][col_czfwyz]==null)||(dataSource[i][col_czfwyz]=="")) && parseInt(dataSource[i][col_xgrczbs])==0)
			{
				alert("选择按市场价格向个人出租用于居住的住房，即选择“是”后，必须填写出租房屋原值！");
				return false;
			}
		}
   	
	}
	return true;
 }
 
function isKeysNull(daObject)
{
	var aryResult=[-1,-1];
	//从daObject中取得所有的数据数组
	var dataSource=daObject.getData();
	//获取数据数组的行数
	var numRow=dataSource.length;
	//如果行数大于 0 
	if(numRow>0)
	{
		//获取数组的列数
	   	var numCol=daObject.getColumns().length;
	   	//循环检测数组中，标明不能为空的数据是否为空,
	   	for(i=0;i<numRow;i++)
	   	{
	    	for(j=0;j<numCol;j++)
	    	{   
	           	if(((dataSource[i][j]==null)||(dataSource[i][j]=="")) && (daObject.getColumns()[j].isNull == true))
	           	{
	               	//alert("func dataSource is null:"+dataSource[i][j]);
	               	aryResult=[i,j];
	               	return aryResult;
	           	}
	       	}
	   	}
	}
	return aryResult;
}


//判断主键值是否为空
function isPrimaryKeysNull(daObject)
{
	var aryResult=[-1,-1];
	//从daObject中取得所有的数据数组
	var dataSource=daObject.getData();
	//获取数据数组的行数
	var numRow=dataSource.length;
	//如果行数大于 0 
	if(numRow>0)
	{
		//获取数组的列数
	   	var numCol=daObject.getColumns().length;
	   	//循环检测数组中，标明不能为空的数据是否为空,
	   	for(i=0;i<numRow;i++)
	   	{
	    	for(j=0;j<numCol;j++)
	    	{   
	           	if(((dataSource[i][j]==null)||(dataSource[i][j]=="")) && (daObject.getColumns()[j].isPrimaryKey == true))
	           	{
	               	//alert("func dataSource is null:"+dataSource[i][j]);
	               	aryResult=[i,j];
	               	return aryResult;
	           	}
	       	}
	   	}
	}
	return aryResult;
}


//判断各纪录的键值字段是否重复
function isKeysEqual(daObject)
{
	var aryResult=[-1,-1];
	//从daObject中取得所有的数据数组
	var dataSource=daObject.getData();
	//获取数据数组的行数
	var numRow=dataSource.length;
   	if(numRow>0)
   	{
       	var numCol=daObject.getColumns().length;
/*       	var aryKeyCol = new Array();
       	for (var i=0;i<numCol;i++)
       	{
       	    if (daObject.getColumns()[i].isPrimaryKey!=null)
       	    {
       	    }
       	}
*/
       	var flagEq=true;
       	for(var i=0;i<numRow;i++)
       	{
           	for(var j=i+1;j<numRow;j++)
           	{
               	flagEq=true;
               	var keyCount = 0;

           		for(var k=0;k<numCol;k++)
           		{
           			//判断是否为主键，如果是判断主键是否重复
         			if(daObject.getColumns()[k].isPrimaryKey == true)
           			{          				
           			    keyCount++;
	               		if((!(dataSource[i][k]==dataSource[j][k])))
	               		{
	                   		flagEq=false;
	                   		break;
	               		}
           			}
          		}
           		if(flagEq && keyCount>0)
           		{
               		aryResult=[i,j];
               		//alert("found one");
               		return aryResult;
           		}
           	}
       	}
   	}
   	
   	return aryResult;
}


//判断各字段的长度是否符合要求
function checkMaxLength(daObject)
{
	var dataSource = daObject.getData();
    var aryResult=[-1,-1];
    var numRow=dataSource.length;
    if(numRow>0)
    {
        var numCol=daObject.getColumns().length;
        for(i=0;i<numRow;i++)
        {
            for(j=0;j<numCol;j++)
            {
	            if(getStringLength(dataSource[i][j])>0)
	            {
	               	if(getStringLength(dataSource[i][j])>daObject.getColumns()[j].maxLength)
	               	{
	                   	aryResult=[i,j];
	                   	return aryResult;
	               	}
	           	}
        	}
       	}
   	}
   	return aryResult;
}

//判断一行中的字段是否全部为空
function isAllCellsNull(daObject,rowindex)
{
   	var dataSource=daObject.getData();
   	var aryColumn = daObject.getColumns();
   	for (var i=0;i<aryColumn.length;i++)
   	{
   	   if (aryColumn[i].Type!=constCTSequence && aryColumn[i].Type!=constCTDefault && dataSource[rowindex][i]!="")
   		{
   			return false;
   		}
   	}
   	return true;
}

//过滤DataSource数据的中的空行，add by 章鹏 2003-10-08
function delNullRow(daObject)
{
	var dataSource = daObject.getData();
   	var numRow=dataSource.length;
   	//被删除的空行的数量
   	var delNum = 0;
	//如果行数大于 0 
	if(numRow>0)
	{
   	    //dataSource数组每删除一条数据数组长度就会自动减一，后面的数据的位置都要往前移一位
   	    //每一条数据被删除，那么被删除的行数就自动加一	    
	   	for(var i=0;i<numRow;i++)
	   	{           
           	var deleteFlag=isAllCellsNull(daObject,i-delNum);
           	if(deleteFlag == true)
           	{
               	daObject.moveto(i-delNum+1);
           	    daObject.deleteRow(i-delNum+1);          	              	    
           	    delNum++;
           	}	   	    
	   	}
	   	daObject.saveData(daObject.getCurrentRow());
	}
}    

function callSpecialChecker(daObject)
{
	var dataSource = daObject.getData();
   	var aryResult=[-1,-1];
   	var numRow=dataSource.length;
   	if(numRow>0){
       	var columns = daObject.getColumns()
       	for(var i=0;i<numRow;i++)
       	{
			for (var j=0;j<columns.length;j++)
			{
				if (columns[j].specialChecker!=null)
				{
					var rtnValue = columns[j].specialChecker(dataSource[i][j]);
					if (rtnValue!=true && rtnValue!="true")
					{
	                   	aryResult=[i,j];
	                   	return aryResult;
					}
				}
			}
       	}
   	}
   	return aryResult;
}

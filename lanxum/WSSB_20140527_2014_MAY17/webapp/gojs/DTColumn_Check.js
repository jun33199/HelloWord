//daObject����
function DataSourceCheck(daObject)
{
	daObject.saveData(daObject.getCurrentRow());
	
	var dataSource=daObject.getData();	
	//���û�������κ������Ļ����ȹ��˿հ������������ж�
	delNullRow(daObject);//add by zhangp 2003-10-08
	//�жϼ�ֵ�ֶ��Ƿ�Ϊ��,�������=""����ɾ���˼�¼
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
	       	    alert("��"+(aryResult[0]+1)+"����¼�ĵ�"+(aryResult[1]+1)+"���ֶ�("+daObject.getColumns()[aryResult[1]].columnCaption+")Ϊ�գ�");
	           	daObject.moveto(aryResult[0]+1);
	           	daObject.modifyRow(aryResult[0]+1);
	           	return false;
	       	   	
	       	}
	   	}
	   	//dataSource=daObject.getData();
	   	aryResult=isPrimaryKeysNull(daObject);
	}
	
	
	//���˵�ɾ��״̬�ļ�¼
	var dataSource=daObject.getData();

	//�ж��ֶ��Ƿ�Ϊ��
	var aryResult=isKeysNull(daObject);
	while(aryResult[0]!=-1)
	{
		if(aryResult[0]!=-1)
		{
           	alert("��"+(aryResult[0]+1)+"����¼�ĵ�"+(aryResult[1]+1)+"���ֶ�("+daObject.getColumns()[aryResult[1]].columnCaption+")Ϊ�գ�");
           	daObject.moveto(aryResult[0]+1);
           	daObject.modifyRow(aryResult[0]+1);
           	return false;
	   	}
	   	//dataSource=daObject.getData();
	   	aryResult=isKeysNull(daObject);
	}
	
	//���ⷿ�������ж� 
	//add by wofei 2009-2-5
	/* if(!checkChuzuFw(daObject)){
		return false;
	} */
	
	//�жϼ�ֵ�ֶ��Ƿ����ظ���
	//modified by llpei 2003-11-05
	aryResult=isKeysEqual(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
	   alert("��"+(aryResult[0]+1)+"����¼�͵�"+(aryResult[1]+1)+"����¼�ļ�ֵ�ֶ��ظ�����������д��");
	   daObject.moveto(aryResult[1]+1);
	   daObject.modifyRow(aryResult[1]+1);
	   return false;
	}
	
	
	//�ж��ֶ�С����󳤶�
	aryResult=checkMaxLength(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
		alert("��"+(aryResult[0]+1)+"����¼�ĵ�"+(aryResult[1]+1)+"���ֶ�("+daObject.getColumns()[aryResult[1]].columnCaption+")����̫������������д��");
		daObject.moveto(aryResult[0]+1);
		daObject.modifyRow(aryResult[0]+1);
		return false;
	}
	
	//�����������ж���
	aryResult=callSpecialChecker(daObject);
	if((aryResult[0]!=-1)&&(aryResult[1]!=-1))
	{
		alert("��"+(aryResult[0]+1)+"����¼�ĵ�"+(aryResult[1]+1)+"���ֶ�("+daObject.getColumns()[aryResult[1]].columnCaption+")���벻��ȷ����������д��");
		daObject.moveto(aryResult[0]+1);
		daObject.modifyRow(aryResult[0]+1);
		return false;
	}
	//�ж��ֶ�С����󳤶� add for zhengjianhaoma
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
 * ���ⷿ�������ж� 
 * add by wofei 2009-2-5
 */
 function checkChuzuFw(daObject){
	//��daObject��ȡ�����е���������
	var dataSource=daObject.getData();
	//��ȡ�������������
	var numRow=dataSource.length;
	//����������� 0 
	if(numRow>0)
	{
		//��ȡ���������
	   	var numCol=daObject.getColumns().length;
		
		var col_czfwyz=0;
		var col_xgrczbs=0;

		for(j=0;j<numCol;j++)
		{
			if(daObject.getColumns()[j].columnCaption=="���ⷿ��ԭֵ")
			{
				col_czfwyz=j;
			}
			if(daObject.getColumns()[j].columnCaption=="�Ƿ��г��۸�����˳������ھ�ס��ס��")
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
				alert("ѡ���г��۸�����˳������ھ�ס��ס������ѡ���ǡ��󣬱�����д���ⷿ��ԭֵ��");
				return false;
			}
		}
   	
	}
	return true;
 }
 
function isKeysNull(daObject)
{
	var aryResult=[-1,-1];
	//��daObject��ȡ�����е���������
	var dataSource=daObject.getData();
	//��ȡ�������������
	var numRow=dataSource.length;
	//����������� 0 
	if(numRow>0)
	{
		//��ȡ���������
	   	var numCol=daObject.getColumns().length;
	   	//ѭ����������У���������Ϊ�յ������Ƿ�Ϊ��,
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


//�ж�����ֵ�Ƿ�Ϊ��
function isPrimaryKeysNull(daObject)
{
	var aryResult=[-1,-1];
	//��daObject��ȡ�����е���������
	var dataSource=daObject.getData();
	//��ȡ�������������
	var numRow=dataSource.length;
	//����������� 0 
	if(numRow>0)
	{
		//��ȡ���������
	   	var numCol=daObject.getColumns().length;
	   	//ѭ����������У���������Ϊ�յ������Ƿ�Ϊ��,
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


//�жϸ���¼�ļ�ֵ�ֶ��Ƿ��ظ�
function isKeysEqual(daObject)
{
	var aryResult=[-1,-1];
	//��daObject��ȡ�����е���������
	var dataSource=daObject.getData();
	//��ȡ�������������
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
           			//�ж��Ƿ�Ϊ������������ж������Ƿ��ظ�
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


//�жϸ��ֶεĳ����Ƿ����Ҫ��
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

//�ж�һ���е��ֶ��Ƿ�ȫ��Ϊ��
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

//����DataSource���ݵ��еĿ��У�add by ���� 2003-10-08
function delNullRow(daObject)
{
	var dataSource = daObject.getData();
   	var numRow=dataSource.length;
   	//��ɾ���Ŀ��е�����
   	var delNum = 0;
	//����������� 0 
	if(numRow>0)
	{
   	    //dataSource����ÿɾ��һ���������鳤�Ⱦͻ��Զ���һ����������ݵ�λ�ö�Ҫ��ǰ��һλ
   	    //ÿһ�����ݱ�ɾ������ô��ɾ�����������Զ���һ	    
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

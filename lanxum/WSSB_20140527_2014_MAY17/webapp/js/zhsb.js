function getrow(sblx,szsmdm)
{
	var szsmObj = eval("document.forms[0].szsmID"+sblx);
	for(var i=0;i<szsmObj.length;i++)
	{
		if(szsmdm == szsmObj[i].value)
		{
			return i;
		}
	}
	return null;
}

/**
 * i ����
 * obj �¼�Դ
 */
function run(obj,i,szsmdm)
{
	//ȡ���걨��ʽ
	var sblx = obj.name.substring(obj.name.length-5);
	if(obj.name.indexOf("sjse")<0)
	{
		computer(obj,getrow(sblx,szsmdm));
	}
	//ȡ�õ�ǰ��Ӧ�ĸ���˰����˰��Ӧ����
	var dyArray = eval("fssdyzs" + sblx);
	//У���¼�Դֵ�ĺϷ���
	formatCurrency(obj,0);
	var objValue = obj.value;
	//�Ӷ�Ӧ�����в����Ƿ����¼�Դ��Ӧ�ĸ���˰
	for(var j=0;j<dyArray.length;j++)
	{
		if(dyArray[j][0] == szsmdm)
		{
			for(var k=1; k<dyArray[j].length; k++)
			{
				if(getrow(sblx,dyArray[j][k]) == null)
				{
					computerHJ(obj);
					continue;
				}
				var fssjsje = eval("document.forms[0].jsje"+sblx+"["+getrow(sblx,dyArray[j][k])+"]");
				if(checkvalue(fssjsje,0))
				{
					var fssjsje_value = fssjsje.value;
				}
				else
				{
					var fssjsje_value = 0;
				}
				var total = 0;
				for(var l=0;l<dyArray.length;l++)
				{
					for(var m=1;m<dyArray[l].length;m++)
					{
						if(dyArray[l][m]==dyArray[j][k])
						{
							if(getrow(sblx,dyArray[l][0]) == null)
							{
								computerHJ(obj);
								continue;
							}
							if(dyArray[j][k].indexOf("53") == 0)
							{
								var zsAdd = eval("document.forms[0].jsje"+sblx+"["+getrow(sblx,dyArray[l][0])+"]");
							}
							else
							{
								var zsAdd = eval("document.forms[0].sjse"+sblx+"["+getrow(sblx,dyArray[l][0])+"]");
							}
							if(checkvalue(zsAdd,0))
							{
								if(zsAdd.value!="" && zsAdd.value!=null)
								{
									total = parseFloat(total) + parseFloat(zsAdd.value);
								}
								else
								{
									total = parseFloat(total) + 0;
								}
							}
							else
							{
								total = parseFloat(total) + 0;
							}
						}
					}
				}
				//alert("zsAdd.value:"+zsAdd.value);
				fssjsje.value = Math.round(parseFloat(total)*100)/100;
				formatCurrency(fssjsje,0);
				computer(fssjsje,getrow(sblx,dyArray[j][k]));
				//computerHJ(fssjsje);
			}
		}
	}
	computerHJ(obj);
}

function computer(obj,i)
{
	//ȡ���걨��ʽ
    var sblx = obj.name.substring(obj.name.length-5);
    var itemsize = eval("itemsize"+sblx);
    if(itemsize > 1)
    {
        var jsje = eval("document.forms[0].jsje"+sblx+"["+i+"]");
        var sl = eval("document.forms[0].sl"+sblx+"["+i+"]");
        var sjse = eval("document.forms[0].sjse"+sblx+"["+i+"]");
		var asljbs = eval("document.forms[0].asljbs"+sblx+"["+i+"]");
		var kssl = eval("document.forms[0].kssl"+sblx+"["+i+"]");
		var szsmdm = eval("document.forms[0].szsmID"+sblx+"["+i+"]").value;
		//alert("aaaaaaaaaaaaaaa");
		var coefficient = eval("document.forms[0].coefficient"+sblx+"["+i+"]").value;
		//alert("bbbbbbbbbbbbbbb");
	}
    else
    {
        var jsje = eval("document.forms[0].jsje"+sblx);
        var sl = eval("document.forms[0].sl"+sblx);
        var sjse = eval("document.forms[0].sjse"+sblx);
		var asljbs = eval("document.forms[0].asljbs"+sblx);
		var kssl = eval("document.forms[0].kssl"+sblx);
		var szsmdm = eval("document.forms[0].szsmID"+sblx).value;
		var coefficient = eval("document.forms[0].coefficient"+sblx).value;
    }
	if(checkvalue(jsje,0) && formatCurrency(jsje,0))
	{
		if(asljbs.value == null || asljbs.value == "")
		{
			return;
		}
		else if(asljbs.value == "0" || asljbs.value == "1" || asljbs.value == "3" || asljbs.value == "5")
		{
			if(jsje.value == null || jsje.value=="" || sl.value == null ||
				sl.value == "" || isNaN(jsje.value) || isNaN(sl.value))
			{
				return;
			}
			else
			{
				if(szsmdm=="120010"||szsmdm=="890001")
				{
					sjse.value = Math.round(jsje.value*100 * 0.7 * sl.value)/100;
				}
				else
				{
					sjse.value = Math.round(jsje.value*100 * sl.value)/100;
				}
				if(coefficient != 1)
				{
					sjse.value = Math.round(sjse.value * 100 * coefficient)/100;
				}
				formatCurrency(sjse,0);
			}
		}
		else if(asljbs.value == "2")
		{
			if(kssl.value == null || kssl.value=="" || sl.value == null ||
				sl.value == "" || isNaN(sl.value) || isNaN(kssl.value))
			{
				return;
			}
			else
			{
				var ksslTmp = Math.floor(kssl.value);
				//�������Ʊ�ʶΪ2ʱ����˰��������0.5��0.5�㣬����0.5���һ
				if((kssl.value - ksslTmp)>0.5)
				{
					ksslTmp = parseFloat(ksslTmp)+1;
				}
				else if((kssl.value - ksslTmp)<0.5 && (kssl.value - ksslTmp)>0)
				{
					ksslTmp = parseFloat(ksslTmp)+0.5;
				}else{
					ksslTmp = kssl.value;
				}
				sjse.value = Math.round(ksslTmp*100 * sl.value)/100;
				if(coefficient != 1)
				{
					sjse.value = Math.round(sjse.value * 100 * coefficient)/100;
				}
				formatCurrency(sjse,0);
			}
		}
	}
	else
	{
		//sjse.value = "0.00";
	}
	//run(sjse,i);
}

function computerHJ(obj)
{
	//ȡ���걨��ʽ
	var sblx = obj.name.substring(obj.name.length-5);
	var hj = eval("document.forms[0].hj"+sblx);
	var itemsize = eval("itemsize"+sblx);
	if(itemsize > 1)
	{
		var sjseArray = eval("document.forms[0].sjse"+sblx);
		var total = 0;
		for(var j=0;j<sjseArray.length;j++)
		{
			if(checkvalue(sjseArray[j],0))
			{
				var sj = sjseArray[j].value;
			}
			else
			{
				var sj = 0;
			}
			if(sj == null || sj == "")
			{
				sj = 0;
			}
			total = parseFloat(total) + parseFloat(sj);
		}
		hj.value = Math.round(total*100)/100;
	}
	else
	{
		var sjse = eval("document.forms[0].sjse"+sblx);
        if(sjse.length==1)
        {
            hj.value = Math.round(sjse[0].value*100)/100;
        }else
        {
            hj.value = Math.round(sjse.value*100)/100;
        }
	}
	formatCurrency(hj,0);
}
var commitFlag = "0";

//���˰��˰Ŀ�Ƿ�����6�� add by lijn20141222
//function checkSzsm2014(){
//	var szsms = document.getElementsByName("szsmID_zcjk");
//	
//	if(szsms.length==0){
//		szsms = document.getElementsByName("szsmID_sdjj");
//	}
//	if(szsms.length==0){
//		return;
//	}
//
//	var special ="100030,100020,510030,510020,540030,540020";
//
//	for(var i=0;i<szsms.length;i++){
//		if(special.indexOf(szsms[i].value)>=0){
//			alert("2015��1��1�������е�˰��ί�й�˰�ִ����ڹ�˰�������걨��ֵ˰������˰��˰��Ӧ���ɵĳ���ά������˰�������Ѹ��ӡ��ط��������ӣ����¼�ơ�һ˰���ѡ���������ͨ�������й�˰�������걨ϵͳ�����걨��ֵ˰������˰ʱ��һ���걨��һ˰���ѡ���");
//		   return;
//		}
//	}
//}

function doSave(sfxy)
{
	//checkSzsm2014();
	
    if(commitFlag == "1")
	{
		return false;
	}
	if (!checkValidate())
	{
		return false;
	}
	if (itemsize_zcjk > 1)
	{
		for(var i=0;i<itemsize_zcjk;i++)
		{
			if(!checkvalue(document.forms[0].kssl_zcjk[i],0))
			{
				document.forms[0].kssl_zcjk[i].value = 0;
			}
		}
	}
	else if(itemsize_zcjk == 1)
	{
		if(!checkvalue(document.forms[0].kssl_zcjk,0))
		{
			document.forms[0].kssl_zcjk.value = 0;
		}
	}
	if (itemsize_bjqs >1)
	{
		for(var i=0;i<itemsize_bjqs;i++)
		{
			if(!checkvalue(document.forms[0].kssl_bjqs[i],0))
			{
				document.forms[0].kssl_bjqs[i].value = 0;
			}
		}
	}
	else if(itemsize_bjqs == 1)
	{
		if(!checkvalue(document.forms[0].kssl_bjqs,0))
		{
			document.forms[0].kssl_bjqs.value = 0;
		}
	}
	if (itemsize_sdjj >1)
	{
		for(var i=0;i<itemsize_sdjj;i++)
		{
			if(!checkvalue(document.forms[0].kssl_sdjj[i],0))
			{
				document.forms[0].kssl_sdjj[i].value = 0;
			}
		}
	}
	else if(itemsize_sdjj == 1)
	{
		if(!checkvalue(document.forms[0].kssl_sdjj,0))
		{
			document.forms[0].kssl_sdjj.value = 0;
		}
	}

	var sum = total();
    var msg = '';
    if(sfxy==1)
    {
        msg = "������˰"+sum+"Ԫ����ȷ���Ƿ񻮿";
    }else
    {
        msg = "�����걨"+sum+"Ԫ����ȷ���Ƿ񱣴沢���ɽɿ��飿";
    }
	var old  = document.forms[0].ywczlx.value;
	if(confirm(msg))
	{
		if (document.forms[0].ywczlx.value == 0)
		{
		  document.forms[0].ywczlx.value = 1;
		}
		else if (document.forms[0].ywczlx.value == 1)
		{
		}
		else
		{
		  alert("δ֪�Ĳ������ͣ�" + document.forms[0].ywczlx.value);
		  return false;
		}
		document.forms[0].actionType.value = "Save";
		var postXml=getLocalPostXml(document.forms[0]);
		if (g_objSI.Container != '')
		{
	        if (! doSubmitXML(document.forms[0],"Save",true,postXml,true))
	        {
				document.forms[0].ywczlx.value = old;
		        return false;
	        }
	        else
	        {
				commitFlag = "1";
	        	return true;
	        }
	    }
	    else
	    {
	    	commitFlag = "1";
			if(!doSubmitXML(document.forms[0],"Save",false,postXml,true))
			{
				document.forms[0].ywczlx.value = old;
				return false;
			}		
	    }
		return true;
	}
	else
	{
		return false;
	}
}

function total()
{
	var sum = 0;
	if (itemsize_zcjk > 0)
	{
		sum += parseFloat(document.forms[0].hj_zcjk.value);
	}
	if (itemsize_bjqs > 0)
	{
		sum += parseFloat(document.forms[0].hj_bjqs.value);
	}
	if (itemsize_sdjj > 0)
	{
		sum += parseFloat(document.forms[0].hj_sdjj.value);
	}

	sum = Math.round(sum*100)/100;

	return sum;
}

var jklx = [["zcjk","�����ɿ�"],["bjqs","����Ƿ˰"],["sdjj","�Ĵ����"]];

function checkZcjk(index)
{
	var itemsize = itemsize_zcjk;
	if (index==1)
	{
		itemsize = itemsize_bjqs;
	}
	else if (index==2)
	{
		itemsize = itemsize_sdjj;
	}
	if (itemsize == 1)
	{
        if(eval("document.forms[0].kssl_" + jklx[index][0]).length==1)
        {
            var kssl = eval("document.forms[0].kssl_" + jklx[index][0])[0];
        }else
        {
            var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
        }
        if(eval("document.forms[0].szsmID_" + jklx[index][0]).length==1)
        {
            var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0])[0].value;
        }else
        {
            var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0]).value;
        }
        if(eval("document.forms[0].jsje_" + jklx[index][0]).length==1)
        {
            var jsje = eval("document.forms[0].jsje_" + jklx[index][0])[0];
        }else
        {
           var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
        }
        if(eval("document.forms[0].sjse_" + jklx[index][0])==1)
        {
            var sjse = eval("document.forms[0].sjse_" + jklx[index][0])[0];
        }else
        {
            var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
        }

		if (kssl.type == "text")
		{
			if( !kssl.readOnly )
			{
				if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
				{
					if (kssl.value == "")
					{
						alert(jklx[index][1] + "��˰��������Ϊ�գ����������д���ݿ���¼��0��");
						kssl.focus();
						return false;
					}
					if(kssl.value < 0)
					{
						alert(jklx[index][1] + "��˰����������ڵ���0��");
						kssl.focus();
						return false;
					}
				}
				else
				{
					if (kssl.value == "")
					{
						alert(jklx[index][1] + "��˰��������Ϊ�գ�");
						kssl.focus();
						return false;
					}
					if(kssl.value <= 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}
				}
			}

/*			if( !kssl.readOnly && kssl.value == "")
			{
				alert(jklx[index][1] + "��˰��������Ϊ�գ�");
				kssl.focus();
				return false;
			}
			else
			{
				if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
				{
					if(kssl.value < 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}

				}
				else
				{
					if(kssl.value <= 0)
					{
						alert(jklx[index][1] + "��˰�����������0��");
						kssl.focus();
						return false;
					}
				}
			}
*/
		}
		if (!jsje.readOnly && jsje.value == "")
		{
			alert(jklx[index][1] + "��˰����Ϊ�գ�");
			jsje.focus();
			return false;
		}
		else
		{
			if(szsmdm.indexOf("11") == 0 || szsmdm.indexOf("88") == 0)
			{
				if (jsje.value < 0)
				{
					alert(jklx[index][1] + "��˰���������0��");
					jsje.focus();
					return false;
				}
			}
			else
			{
				if (jsje.value <= 0)
				{
					alert(jklx[index][1] + "��˰���������0��");
					jsje.focus();
					return false;
				}
			}
		}

		if (!sjse.readOnly && sjse.value == "")
		{
			alert(jklx[index][1] + "ʵ��˰���Ϊ�գ���ȡ��0�걨��");
			sjse.focus();
			return false;
		}
		else if (sjse.value <= 0)
		{
			alert(jklx[index][1] + "ʵ��˰��������0����ȡ��0�걨��");
			sjse.focus();
			return false;
		}
	}
	else if (itemsize > 1)
	{
		for(var i=0; i<itemsize; i++)
		{
			var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
			var szsmdm = eval("document.forms[0].szsmID_" + jklx[index][0]);
			if (kssl[i].type == "text")
			{
				if( !kssl[i].readOnly )
				{
					if(szsmdm[i].value.indexOf("11") == 0 || szsmdm[i].value.indexOf("88") == 0)
					{
						if (kssl[i].value == "")
						{
							alert(jklx[index][1] + "��˰��������Ϊ�գ����������д���ݿ���¼��0��");
							kssl[i].focus();
							return false;
						}
						if(kssl[i].value < 0)
						{
							alert(jklx[index][1] + "��˰����������ڵ���0��");
							kssl[i].focus();
							return false;
						}
					}
					else
					{
						if (kssl[i].value == "")
						{
							alert(jklx[index][1] + "��˰��������Ϊ�գ�");
							kssl[i].focus();
							return false;
						}
						if(kssl[i].value <= 0)
						{
							alert(jklx[index][1] + "��˰�����������0��");
							kssl[i].focus();
							return false;
						}
					}
				}
			}
			var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
			if (!jsje[i].readOnly && jsje[i].value == "")
			{
				alert(jklx[index][1] + "��˰����Ϊ�գ�");
				jsje[i].focus();
				return false;
			}
			else
			{
				if(szsmdm[i].value.indexOf("11") == 0 || szsmdm[i].value.indexOf("88") == 0)
				{
					if (jsje[i].value < 0)
					{
						alert(jklx[index][1] + "��˰���������0��");
						jsje[i].focus();
						return false;
					}
				}
				else
				{
					if (jsje[i].value <= 0)
					{
						alert(jklx[index][1] + "��˰���������0��");
						jsje[i].focus();
						return false;
					}
				}
			}
			var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
			if (!sjse[i].readOnly && sjse[i].value == "")
			{
				alert(jklx[index][1] + "ʵ��˰���Ϊ�գ���ȡ��0�걨��");
				sjse[i].focus();
				return false;
			}
			else if (sjse[i].value <= 0)
			{
				alert(jklx[index][1] + "ʵ��˰��������0����ȡ��0�걨��");
				sjse[i].focus();
				return false;
			}
		}
	}

	return true;
}

function checkValidate()
{
	var passed = true;
	for (var i=0; i<3; i++)
	{
		passed = checkZcjk(i);
		if (!passed)
		{
			break;
		}
	}

	return passed;
}

function onloadcomputer()
{
	if (itemsize_zcjk > 1)
	{
		for(var i=0;i<itemsize_zcjk;i++)
		{
			//alert("1");
			run(document.forms[0].sjse_zcjk[i],i,document.forms[0].szsmID_zcjk[i].value);
		}
	}
	if(itemsize_zcjk == 1)
	{
        if(document.forms[0].sjse_zcjk.length==1)
        {
            computerHJ(document.forms[0].sjse_zcjk[0]);
        }else
        {
            computerHJ(document.forms[0].sjse_zcjk);
        }
	}
	if (itemsize_bjqs > 1)
	{
		for(var i=0;i<itemsize_bjqs;i++)
		{
			//alert("2");
			run(document.forms[0].sjse_bjqs[i],i,document.forms[0].szsmID_bjqs[i].value);
		}
	}
	if(itemsize_bjqs == 1)
	{
        if(document.forms[0].sjse_bjqs.length==1)
        {
            computerHJ(document.forms[0].sjse_bjqs[0]);
        }else
        {
            computerHJ(document.forms[0].sjse_bjqs);
        }
	}
	if (itemsize_sdjj > 1)
	{
		for(var i=0;i<itemsize_sdjj;i++)
		{
			//alert("3");
			run(document.forms[0].sjse_sdjj[i],i,document.forms[0].szsmID_sdjj[i].value);
		}
	}
	if(itemsize_sdjj == 1)
	{
        if(document.forms[0].sjse_sdjj.length==1)
        {
            computerHJ(document.forms[0].sjse_sdjj[0]);
        }else
        {
            computerHJ(document.forms[0].sjse_sdjj);
        }
	}
}

function doReturn()
{
	document.forms[0].actionType.value = "Return";
	if(confirm(confirmReturn))
    {
		 if(!doSubmitXML(document.forms[0],"Return",false,"return",true))
		 {
			return false;
		 }
		 return true;
    }else
	{
		return false;
	}
}

function doDel(sklx,id,obj)
{
	var delObj=document.getElementById(id);
	var delRows = delObj.rows.length-1;
	if(delRows<3)
	{
		return false;
	}
	if(confirm("�Ƿ�ɾ����"))
	{
		var delIndex = eval("delIndex_" + sklx);
		for(var i=0;i<delRows;i++)
		{
			if(delIndex[i] == obj)
			{
       			delObj.deleteRow(i+1);
			}
	    }
		if(sklx == "zcjk")
        {
        itemsize_zcjk -= 1;
        }
		else if(sklx == "bjqs")
        {
        itemsize_bjqs -= 1;
        }
		else if(sklx == "sdjj")
        {
        itemsize_sdjj -= 1;
        }
		onloadcomputer();
		//added by ligr ��������˰�ֵ�id����
		reSzsmid();
		//computerHJ(obj);
    }
	else
	{
       	return false;
    }
}

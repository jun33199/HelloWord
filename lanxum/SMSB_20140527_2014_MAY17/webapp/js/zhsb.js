/**
 * i 行数
 * obj 事件源
 */
function run(obj,i)
{
	//取得申报方式
	var sblx = obj.name.substring(obj.name.length-5);
	//取得当前对应的附加税和主税对应数组
	var dyArray = eval("fssdyzs" + sblx);
	//校验事件源值的合法性
	formatCurrency(obj,0);
	var objValue = obj.value;
	//从对应数组中查找是否有事件源对应的附加税
	for(var j=0;j<dyArray.length;j++)
	{
		if(dyArray[j][0] == i)
		{
			for(var k=1; k<dyArray[j].length; k++)
			{
				var fssjsje = eval("document.forms[0].jsje"+sblx+"["+dyArray[j][k]+"]");
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
							var zsjsje = eval("document.forms[0].sjse"+sblx+"["+dyArray[l][0]+"]");
							if(checkvalue(zsjsje,0))
							{
								if(zsjsje.value!="" && zsjsje.value!=null)
								{
									total = parseFloat(total) + parseFloat(zsjsje.value);
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
				//alert("zsjsje.value:"+zsjsje.value);
				fssjsje.value = Math.round(parseFloat(total)*100)/100;
				computer(fssjsje,dyArray[j][k]);
				//computerHJ(fssjsje);
			}
		}
	}
	computerHJ(obj);
}

function computer(obj,i)
{
	//取得申报方式
    var sblx = obj.name.substring(obj.name.length-5);
    var itemsize = eval("itemsize"+sblx);
    if(itemsize > 1)
    {
        var jsje = eval("document.forms[0].jsje"+sblx+"["+i+"]");
        var sl = eval("document.forms[0].sl"+sblx+"["+i+"]");
        var sjse = eval("document.forms[0].sjse"+sblx+"["+i+"]");
    }
    else
    {
        var jsje = eval("document.forms[0].jsje"+sblx);
        var sl = eval("document.forms[0].sl"+sblx);
        var sjse = eval("document.forms[0].sjse"+sblx);
    }
	if(checkvalue(jsje,0))
	{
		sjse.value = Math.round(jsje.value * sl.value*100)/100;
	}
	else
	{
		sjse.value = 0;
	}
	//run(sjse,i);
}

function computerHJ(obj)
{
	//取得申报方式
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
		hj.value = Math.round(sjse.value*100)/100;
	}
}

function doSave()
{
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

	if(confirm("您将缴款 " + sum + " 元，是否确认缴款？"))
	{
		document.forms[0].actionType.value = "Save";
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

var jklx = [["zcjk","正常缴款"],["bjqs","补缴欠税"],["sdjj","三代解缴"]];

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
		var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
		if (kssl.type == "text")
		{
			if( !kssl.readOnly && kssl.value == "")
			{
				alert(jklx[index][1] + "课税数量不能为空！");
				kssl.focus();
				return false;
			}
			else if (kssl.value <= 0)
			{
				alert(jklx[index][1] + "课税数量必须大于0！");
				kssl.focus();
				return false;
			}
		}

		var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
		if (!jsje.readOnly && jsje.value == "")
		{
			alert(jklx[index][1] + "计税金额不能为空！");
			jsje.focus();
			return false;
		}
		else if (jsje.value <= 0)
		{
			alert(jklx[index][1] + "计税金额必须大于0！");
			jsje.focus();
			return false;
		}

		var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
		if (!sjse.readOnly && sjse.value == "")
		{
			alert(jklx[index][1] + "实缴税额不能为空！");
			sjse.focus();
			return false;
		}
		else if (sjse.value <= 0)
		{
			alert(jklx[index][1] + "实缴税额必须大于0！");
			sjse.focus();
			return false;
		}
	}
	else if (itemsize > 1)
	{
		for(var i=0; i<itemsize; i++)
		{
			var kssl = eval("document.forms[0].kssl_" + jklx[index][0]);
			if (kssl[i].type == "text")
			{
				if( !kssl[i].readOnly && kssl[i].value == "")
				{
					alert(jklx[index][1] + "课税数量不能为空！");
					kssl[i].focus();
					return false;
				}
				else if(kssl[i].value <= 0)
				{
					alert(jklx[index][1] + "课税数量必须大于0！");
					kssl[i].focus();
					return false;
				}
			}
			var jsje = eval("document.forms[0].jsje_" + jklx[index][0]);
			if (!jsje[i].readOnly && jsje[i].value == "")
			{
				alert(jklx[index][1] + "计税金额不能为空！");
				jsje[i].focus();
				return false;
			}
			else if (jsje[i].value <= 0)
			{
				alert(jklx[index][1] + "计税金额必须大于0！");
				jsje[i].focus();
				return false;
			}
			var sjse = eval("document.forms[0].sjse_" + jklx[index][0]);
			if (!sjse[i].readOnly && sjse[i].value == "")
			{
				alert(jklx[index][1] + "实缴税额不能为空！");
				sjse[i].focus();
				return false;
			}
			else if (sjse[i].value <= 0)
			{
				alert(jklx[index][1] + "实缴税额必须大于0！");
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

function doExit()
{
	if(confirm("确定退出？"))
	{
		document.forms[0].action = "quit.do";
		document.forms[0].submit();
	}
	else
	{
		return false;
	}
}

function doReturn()
{
	document.forms[0].actionType.value = "Return";
	return true;
}
//翻页操作
function FN_QSChangePage(opType)
{
    document.forms[0].operationType.value = "ChangePage";
    //如果点击"第一页"
    if (opType == "diyiye")
    {
        document.forms[0].changePage.value = "1";
        return true;
    }
    //如果点中"上一页"
    if (opType == "shangyiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)-1;
        return true;
    }
    //如果点中"下一页"
    if (opType == "xiayiye")
    {
        document.forms[0].changePage.value = parseInt(document.forms[0].changePage.value)+1;
        return true;
    }
    //如果点中"最后一页"
    if(opType == "zuihouyiye")
    {
        document.forms[0].changePage.value = document.forms[0].pageCount.value;
        return true;
    }
    return false;
}

//检查都是数字0123456789.

function isValidNumber(strnum)
{
	if ((strnum == null) || strnum.length == 0)
	   return false;
	validnumber = "0123456789.";
	for (var i = 0 ; i < strnum.length; i++)
	{
		if (validnumber.indexOf(strnum.charAt(i)) < 0)
		{
			return false;
		}
	}
	var dot = strnum.indexOf(".");
	if (dot > 0)
	{
		frag = strnum.substr(dot+1);
		if (frag.indexOf(".") >= 0)
		{
			return false;
		}
	}
	return true;
}

//检查金额必须是数字 数字格式可以是8999999,也可以是999.9999
function FN_QSCheckNumber(val,objname)
{
	if (!isValidNumber(val))
	{
		alert(objname + "不是数字！");
		return false;
	}

	return true;
}

//检查金额必须是数字 数字格式可以是8999999,也可以是999.9999
//小数位数不能超过指定的digitLen
//修改建筑面积最大值提示，并且小数位数约束为3位，modify by fujx，20081129
function FN_QSCheckNumberDigit_Qssb(val,digitLen,objname)
{
//保存显示提示的最大值数
var maxMj="";
//取得契税申报中个人与非个人的标志，person为false表示为个人，否则为非个人
var person = document.forms[0].person.value;
        //根据个人或者企业选择提示的最大数
        if(person=="false"){
        maxMj="10000";
        }else{
        maxMj="1000";
        }
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "小数点位数过长！");
			return false;
		}
		inte = val.substr(0,dot);
        //增加‘土地面积’或者‘房屋简直面积’大于10000的提示
        //把原来的长度为13的判断修改为长度为5的判断，并加入confirm的提示框进行逻辑分支
        //modify by fujx，20081126
        //修改内容开始
      //  if(inte.length == 5){
        var mj = parseFloat(val);

        if(mj>maxMj){
        if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
          return false;
         }
        }
       // }
//        if(inte.length > 5){
//         if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
//          return false;
//         }
//        }
        //修改内容结束


        //原来的code开始
		if (inte.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
       //原来的code结束
	}
    else  //没有小数
	{
   // var inte = val.length;
    //增加‘土地面积’或者‘房屋简直面积’大于10000的提示
        //把原来的长度为13的判断修改为长度为5的判断，并加入confirm的提示框进行逻辑分支
        //modify by fujx，20081126
        //修改内容开始
        var mj = parseInt(val);
        if(mj>maxMj){
        if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
          return false;
         }
        }
//        if(inte > 5){
//         if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
//          return false;
//         }
//        }
         //修改内容结束
         //原来的code开始
		if (val.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
        //原来的code结束
	}


	return true;
}
//修改人，fujx，增加不征契税申报中，土地面积与建筑面积的约束js
function FN_QSCheckNumberDigit_BZQS(val,digitLen,objname)
{
//保存显示提示的最大值数
var maxMj="";
//取得不征契税申报中个人与非个人的标志，yhbs为0表示为个人，否则为非个人
var yhbs = document.forms[0].yhbs.value;
        //根据个人或者企业选择提示的最大数
        if(yhbs=="1"){
        maxMj="10000";
        }else{
        maxMj="1000";
        }
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "小数点位数过长！");
			return false;
		}
		inte = val.substr(0,dot);
        //增加‘土地面积’或者‘房屋简直面积’大于10000的提示
        //把原来的长度为13的判断修改为长度为5的判断，并加入confirm的提示框进行逻辑分支
        //modify by fujx，20081126
        //修改内容开始
      //  if(inte.length == 5){
        var mj = parseFloat(val);

        if(mj>maxMj){
        if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
          return false;
         }
        }
       // }
//        if(inte.length > 5){
//         if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
//          return false;
//         }
//        }
        //修改内容结束


        //原来的code开始
		if (inte.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
       //原来的code结束
	}
    else  //没有小数
	{
   // var inte = val.length;
    //增加‘土地面积’或者‘房屋简直面积’大于10000的提示
        //把原来的长度为13的判断修改为长度为5的判断，并加入confirm的提示框进行逻辑分支
        //modify by fujx，20081126
        //修改内容开始
        var mj = parseInt(val);
        if(mj>maxMj){
        if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
          return false;
         }
        }
//        if(inte > 5){
//         if(!confirm(objname+"大于"+maxMj+"，请核实是否正确!")){
//          return false;
//         }
//        }
         //修改内容结束
         //原来的code开始
		if (val.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
        //原来的code结束
	}


	return true;
}








//避免出现小数点位数过多，不直接使用+
function floatplus(val1,val2)
{
	tol = parseInt(val1*100) + parseInt(val2*100);
	return tol / 100;
}

//避免出现小数点位数过多，不直接使用-
function floatminus(val1,val2)
{
	rem = parseInt(val1*100) - parseInt(val2*100);
	return rem / 100;
}

//将外币价格转换成人民币
//wbjgobj 输入外币价格的对象
//hlobj 输入汇率的对象
//rmbobj 输入人民币的对象
function fnConvertWb(wbjgobj,hlobj,rmbobj)
{
   wbjg = wbjgobj.value;
   hl = hlobj.value;
   if (!isValidNumber(wbjg))
     return;
   if (!isValidNumber(hl))
     return;

   rmbobj.value = parseInt((wbjg * hl + 0.005)*100) / 100;
}

//检查面积，金额等
function fnCheckMjJe(obj,len,name)
{
    if (!FN_QSCheckNumberDigit(obj.value,len,name))
    {
        obj.focus();
    }
}

//和当前时间进行比较
function cmpDate(shijian,xtdqsj)
{
		today = new Date();
		var strYear = shijian.substr(0,4)
		var strMonth = shijian.substr(4,2)
		var strDay = shijian.substr(6,2)

		var strXtyear = xtdqsj.substr(0,4)
		var strXtmonth = xtdqsj.substr(4,2)
		var strXtday = xtdqsj.substr(6,2)

		if(strYear>strXtyear)
		{
			 return false
		}
		if(strYear<strXtyear)
		{
			return true
		}
	  else
		{
				if(strMonth>(strXtmonth))
				{
					return false
				}
				if(strMonth<(strXtmonth))
				{
					return true
				}
				else
				{
						if(strDay>strXtday)
						{
							return false

						}
				}
		}
		return true
}

//判断面积是否通过，通过返回true
function check_Mj(){

	//判断土地面积
	if(cmpNumber(document.forms[0].tdfwqszymj.value,"10000")){
		return false;
	}
	//判断房屋建筑面积
	if(cmpNumber(document.forms[0].fwjzmj.value,"10000")){
		return false;
	}
	return true;

}

//判断价格是否通过，通过返回true
function check_Jg(){

	//判断成交价格人民币
	if(cmpNumber("50000",document.forms[0].cjjgyrmb.value)){
		return false;
	}
	//判断税务机关核定价格
	if(cmpNumber("50000",document.forms[0].pgjg.value)){
		return false;
	}


	return true;

}


//判断number1是否比number2大，大于则返回true
function cmpNumber(number1,number2)
{
	if(number1 != "" && number1 != null && number2 != "" && number2 != null && parseFloat(number1)<=parseFloat(number2)){
		return false;
	}
	if(number1 == "" || number1 == null || number2 == "" || number2 == null){
		return false;
	}
	return true;
}

//检查金额必须是数字 数字格式可以是8999999,也可以是999.9999
//小数位数不能超过指定的digitLen

function FN_QSCheckNumberDigit(val,digitLen,objname)
{
	if (!FN_QSCheckNumber(val,objname))
	{
		return false;
	}
	var dot = val.indexOf(".");
	if (dot > 0)
	{
		frag = val.substr(dot+1);
		if (frag.length > digitLen)
		{
			alert(objname + "小数点位数过长！");
			return false;
		}
		inte = val.substr(0,dot);
		if (inte.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
	}
    else  //没有小数
	{
		if (val.length > 13)
		{
			alert(objname + "整数部分位数过长！");
			return false;
		}
	}


	return true;
}
/////////////////////////add by yangxiao 20081206 Start//////////////////////////////////////
//建委证件类型转换成地税证件类型
function zjlxJwToDs(zjlxJw)
{
    var zjlxDs = "02";
	if(zjlxJw != "" && zjlxJw != null){
        if(zjlxJw == "1"){
            zjlxDs = "02";
            return zjlxDs;
        }
        if(zjlxJw == "3"){
            zjlxDs = "04";
            return zjlxDs;
        }
        if(zjlxJw == "4"){
            zjlxDs = "03";
            return zjlxDs;
        }
        if(zjlxJw == "7"){
            zjlxDs = "06";
            return zjlxDs;
        }
        if(zjlxJw == "10"){
            zjlxDs = "11";
            return zjlxDs;
        }
		//edit by zzb 20090612 加入了证件类型'13 注册证书'和'14	文职干部退休证'
        if(zjlxJw == "2" || zjlxJw == "5" || zjlxJw == "6" || zjlxJw == "8" || zjlxJw == "9" || zjlxJw == "11" || zjlxJw == "12" || zjlxJw == "13" || zjlxJw == "14"){
            zjlxDs = "90";
            return zjlxDs;
        }
    }
}

//地税证件类型转换成建委证件类型
function zjlxDsToJw(zjlxDs)
{
    var zjlxJw = "1";
	if(zjlxDs != "" && zjlxDs != null){
        if(zjlxDs == "02"){
            zjlxJw = "1";
            return zjlxJw;
        }
        if(zjlxDs == "04"){
            zjlxJw = "3";
            return zjlxJw;
        }
        if(zjlxDs == "03"){
            zjlxJw = "4";
            return zjlxJw;
        }
        if(zjlxDs == "06"){
            zjlxJw = "7";
            return zjlxJw;
        }
        if(zjlxDs == "11"){
            zjlxJw = "10";
            return zjlxJw;
        }
        if(zjlxDs == "90"){
            zjlxJw = "6";
            return zjlxJw;
        }
    }
}

//建委房屋用途转换成地税房屋用途
function fwytJwToDs(fwytJw)
{
    var fwytDs = "03";
	if(fwytJw != "" && fwytJw != null){
        if(fwytJw == "3"){
            fwytDs = "01";
            return fwytDs;
        }
        if(fwytJw == "4" || fwytJw == "5" || fwytJw == "6" || fwytJw == "7"){
            fwytDs = "04";
            return fwytDs;
        }
        if(fwytJw == "1" || fwytJw == "2" || fwytJw == "21" || fwytJw == "10"){
            fwytDs = "03";
            return fwytDs;
        }
    }
}

function delFuhao(number){
    var frag = "";
    if(number != "" && number != null){
        for (var i = 0 ; i < number.length; i++)
        {
            var num = number.substring(i,i+1);
            if(isValidNumber(num)){
                frag = frag + num;
            }
        }
    }else{
        frag = "0.00";
    }
    return frag;
}
/////////////////////////add by yangxiao 20081206 end//////////////////////////////////////


/////////////////////////add by hazhengze 20081201 Start//////////////////////////////////////
/**//*将一位数字格式化成两位,如:   9   to   09*/
function formatStr1To2(str_in_formatStr1To2){
	//alert(str_in_formatStr1To2);
	if(str_in_formatStr1To2==null||str_in_formatStr1To2==""||str_in_formatStr1To2=="null"){
		return "";
	}else if(str_in_formatStr1To2.length==1){
		return "0"+str_in_formatStr1To2;
	}
	return str_in_formatStr1To2;
}

function formatDateStr(str_in){
	var rtnStr_tmp="";
	//alert("str_in="+str_in);
	if(str_in==null||str_in==""||str_in=="null"){
		alert("日期格式错误");
	}else{
		tmpObj_hyqdsj_Array=str_in.split("-");
		rtnStr_tmp_1=tmpObj_hyqdsj_Array[0];
		rtnStr_tmp_2=formatStr1To2(tmpObj_hyqdsj_Array[1]+"");
		rtnStr_tmp_3=formatStr1To2(tmpObj_hyqdsj_Array[2]+"");
		rtnStr_tmp=""+rtnStr_tmp_1+rtnStr_tmp_2+rtnStr_tmp_3;
		return rtnStr_tmp;
	}
}


/**
*检查条码内容方法，需要检查的内容如下：
*1、头标识是否存在？
*2、头标识的条码类型，"000001"-新房，"000002"-存量房
*3、头标识的条码版本号，"000001"目前为此结果
*说明：对于条码内容规范发生变更，需要修改此方法，尤其是版本号发生变更的情况
*/
function checkQRCodeHeader(transObj_check){
	//
	var transObjArray_check=transObj_check.split("^");
	//
	//alert("..............1");
	if(transObjArray_check[1]==undefined){
		//alert("扫描图片失败，请重新扫描或手工录入信息！");
		return "1";
	}
	//alert("..............2");
	var transObjArray_check_1=transObjArray_check[1].split("_");
	//alert("..............3");
	//
	if(transObj_check==""){
		//alert("扫描图片失败，请重新扫描或手工录入信息！");
		return "1";
	}else if(transObjArray_check_1[0]!="object"){//如果明文不为"object_"开头则表示条码图片扫描不全，提示继续扫描
		//alert("请扫描全部二维条形码图片，否则无法解码！");
		return "2";
	}else{
		if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000001"){//新房,版本号为"000001"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000001"){//二手房,版本号为"000001"
			return "4";
		}else if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000002"){//新房,版本号为"000002"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000002"){//二手房,版本号为"000002"
			return "4";
		// add by zzb 20090612 begin
		}else if(transObjArray_check_1[1]=="000001"&&transObjArray_check_1[2]=="000003"){//新房,版本号为"000003"
			return "3";
		}else if(transObjArray_check_1[1]=="000002"&&transObjArray_check_1[2]=="000003"){//二手房,版本号为"000003"
			return "4";
		// add by zzb 20090612 end
		}else{
			alert("扫描图片失败，条码来源不正确，请重新扫描或手工录入信息！");
			return "1";
		}
	}
}

/////////////////////////add by hazhengze 20081201 end//////////////////////////////////////

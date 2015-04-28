	//本次交易是否为首次上市已购公房名称  
	  function getsfscssgf_mc(dm){
		  
	  	var mc ="";
		  switch (dm)
		  {
		  case '0':
		    mc="否";
		    break;
		  case '1':
			mc="是";
		    break; 
		  }
		  return mc;
	  }
	//建筑结构名称
	  function getjzjg_mc(dm){
		  
		  	var mc ="";
			  switch (dm)
			  {
			  case '1':
			    mc="钢结构";
			    break;
			  case '2':
				mc="钢筋混凝土结构";
			    break;
			  case '3':
					mc="混合结构";
				    break;
			  case '4':
					mc="砖木结构";
				    break;
			  case '5':
					mc="其他结构";
				    break;	
			  case '6':
					mc="钢、钢筋混凝土结构";
				    break;
			  default:
				  mc="其他结构";
					break;	    
			  }
			  return mc;
		  }
	//买卖人类别
	function nsrlb_mc(dm){
	  	var mc ="";
		  switch (dm)
		  {
		  case '0':
		    mc="非个人";
		    break;
		  case '1':
			mc="个人";
		    break; 
		  default:
			  mc="非个人";
				break;		    
		  }
		  return mc;
	}
	
	//买卖人证件类别名称
	function nsrzjlb_mc(dm){
	  	var mc ="";
		  switch (dm)
		  {
		  case '1':
			    mc="身份证";
			    break;
		  case '2':
				mc="工作居住证或暂住证";
			    break;
		  case '3':
				mc="护照";
			    break;
		  case '4':
				mc="军官证";
			    break;
		  case '5':
				mc="营业执照";
			    break;	
		  case '6':
				mc="其它";
			    break;
		  case '7':
			    mc="台胞证";
			    break;
		  case '8':
				mc="来往大陆通行证";
			    break;
		  case '9':
				mc="统一代码";
			    break;
		  case '10':
				mc="香港居民身份证";
			    break;
		  case '11':
				mc="警官证";
			    break;	
		  case '12':
				mc="商业登记证";
			    break;			    
		  default:
			  mc="其他";
				break;	    
		  }
		  return mc;
	
	}

/*
	*time 格式 yyyyMMdd  或  yyyy-mm-dd
	*
	*returnType 返回类型  yyyy年mm月dd日  或  yyyymmdd
	*/
	function transTime(timeName,time,returnType){
		if(time == "" || time == null){
			return "";
		}
		var type_1="yyyy年mm月dd日";
		var type_2 ="yyyymmdd";

		var y = "";//yyyy
		var m = "";//mm
		var d = "";//dd

	    if (time.length == 8){
			y = time.substr(0,4);
			m = time.substr(4,2);
			d = time.substr(6,2);

			if(returnType == type_1){
				return y + "年" + m + "月" + d +"日";
			}else if (returnType == type_2){
				return time;
			}

		}else if(time.length == 10){
			y = time.substr(0,4);
			m = time.substr(5,2);
			d = time.substr(8,2);

			if(returnType == type_1){
				return y + "年" + m + "月" + d +"日";
			}else if (returnType == type_2){
				return y + "" + m + "" + d;
			}

	   }else{
			alert(timeName +"长度或者类型有误，无法解析，类型应该为yyyyMMdd或yyyy-mm-dd,错误的时间为"+time);
			return time;
	   }
	}
	
	
	function isDate(obj, empty) {
		if (window.event.keyCode != 13 && window.event.keyCode != 0
				&& obj == window.event.srcElement)
			return true;

		var strDate = obj.value;
		var succeed = true;
		var alertStr = "";

		if (strDate.length == 0) {
			if (empty != null) {
				alertStr += "输入值必须不为空!\n"
				succeed = false;
			}

		}
		if (strDate.length != 8 && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		}
		var strYear = strDate.substr(0, 4);
		var strMonth = strDate.substr(4, 2);
		var strDay = strDate.substr(6, 2);
		var objDate = new Date(strMonth + '-' + strDay + '-' + strYear);
		if (isNaN(objDate) && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		}
		if (strYear * 1 < 1900 && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		}
		if ((strYear * 1 != objDate.getYear() * 1)
				&& (strYear * 1 != objDate.getYear() * 1 + 1900) && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		}
		if (strMonth * 1 != objDate.getMonth() * 1 + 1 && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		}
		if (strDay * 1 != objDate.getDate() * 1 && succeed) {
			alertStr += "日期格式不正确!\n"
			succeed = false;
		} //don't call getDay function.
		if (alertStr != "") {
			alert(alertStr);
			window.event.returnValue = false;
			obj.focus();
			obj.select();
		}

		return succeed;
	}
	
	  //改变采集方式
	  function  changeCjfs(obj){
		  //转二维码扫描
		  if(obj.value == "01"){
			  doSubmitForm("Show");
			  
		  }
		  
		  //转手工采集
		  if(obj.value == "02"){
			  doSubmitForm("ToSGCJ");
			  
		  }
	  }
	
	

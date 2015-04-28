function checkValid(objform) { //check validation
    if (getStringLength(objform.hphm.value) > 10) { //检查：号牌号码
        alert('号牌号码输入过长，长度限制为10！\n汉字长度为2，字母或数字为1');
        objform.hphm.focus();
        return false;	
    }
    if (getStringLength(objform.cjh.value) > 30) { //检查：车架号
        alert('车架号输入过长，长度限制为30！\n汉字长度为2，字母或数字为1');
        objform.cjh.focus();
        return false;	
    }
    if (objform.czzjlx.value == "") {  //检查：车主证件类型
    	alert('证件类型一定要选择！');
    	objform.czzjlx.focus();
    	return false;    	
    }
    if (objform.czzjhm.value == "") {  //检查：车主证件号码_非空性
    	alert('证件号码一定要填写！');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (getStringLength(objform.czzjhm.value) > 18) {  //检查：车主证件号码_长度
    	alert('证件号码输入过长，长度限制为18！\n汉字长度为2，字母或数字为1');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (objform.czzjlx.options[objform.czzjlx.selectedIndex].innerText=="身份证" && checkIdentityCard(objform.czzjhm.value) != 0) {  //检查：车主证件号码_合理性
    	alert('身份证号码不正确！');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (objform.gjdm.value == "") {  //检查：国籍
    	alert('国籍一定要选择！');
    	objform.gjdm.focus();
    	return false;
    }
    if (getStringLength(objform.cpxh.value) > 20) { //检查：厂牌型号
        alert('厂牌型号输入过长，长度限制为20！\n汉字长度为2，字母或数字为1');
        objform.cpxh.focus();
        return false;	
    }
    if (getStringLength(objform.fdjh.value) > 30) { //检查：发动机号
        alert('发动机号输入过长，长度限制为30！\n汉字长度为2，字母或数字为1');
        objform.fdjh.focus();
        return false;	
    }
    //检查日期合格性
    if (objform.fsrq_n.value != "" || objform.fsrq_y.value != "" || objform.fsrq_r.value != "") { //检查：复驶时间
        var fsrq = objform.fsrq_n.value + "-" + objform.fsrq_y.value + "-" + objform.fsrq_r.value;
        if (!isDate(fsrq)) {
            alert('复驶时间格式不正确！\n范例：1999-09-09（yyyy-mm-dd）');
            return false;	
        }
    }
    if (objform.bfrq_n.value != "" || objform.bfrq_y.value != "" || objform.bfrq_r.value != "") { //检查：报废时间
        var bfrq = objform.bfrq_n.value + "-" + objform.bfrq_y.value + "-" + objform.bfrq_r.value;
        if (!isDate(bfrq)) {
            alert('报废时间格式不正确！\n范例：1999-09-09（yyyy-mm-dd）');
            return false;	
        }
    }
    if (objform.tsrq_n.value != "" || objform.tsrq_y.value != "" || objform.tsrq_r.value != "") { //检查：停驶时间
        var tsrq = objform.tsrq_n.value + "-" + objform.tsrq_y.value + "-" + objform.tsrq_r.value;
        if (!isDate(tsrq)) {
            alert('停驶时间格式不正确！\n范例：1999-09-09（yyyy-mm-dd）');
            return false;	
        }
    }
    if (objform.djrq_n.value != "" || objform.djrq_y.value != "") { //检查：登记日期
        var djrq = objform.djrq_n.value + "-" + objform.djrq_y.value + "-01";
        if (!isDate(djrq)) {
            alert('登记日期格式不正确！\n范例：1999-09-09（yyyy-mm-dd）');
            return false;	
        }
    }
    if (objform.fxszrq_n.value != "" || objform.fxszrq_y.value != "" || objform.fxszrq_r.value != "") { //检查：发行驶证日期
        var fxszrq = objform.fxszrq_n.value + "-" + objform.fxszrq_y.value + "-" + objform.fxszrq_r.value;
        if (!isDate(fxszrq)) {
            alert('发行驶证日期格式不正确！\n范例：1999-09-09（yyyy-mm-dd）');
            return false;	
        }
    }
    if (objform.hdzk.value != "") { //检查：核定载客
        if (!isAllCharValid(objform.hdzk.value,"0123456789") || objform.hdzk.value.charAt(0)=="0") {
            alert('核定载客要填入正整数！');
            objform.hdzk.select();
            return false; 
        }
    }
    if (objform.jssqpgc.value != "") { //检查：驾驶室前排共乘
        if (!isAllCharValid(objform.jssqpgc.value,"0123456789") || objform.jssqpgc.value.charAt(0)=="0") {
            alert('驾驶室前排共乘要填入正整数！');
            objform.jssqpgc.select();
            return false; 
        }
    }
    if (objform.zzl.value != "") { //检查：总质量
        if (!isDigitString(objform.zzl.value,6,1) || objform.zzl.value.charAt(0)=="0") {
	        alert('总质量要填入大于1000的数字\n可精确到小数点后一位数！');
            	objform.zzl.select();
            	return false; 
        }
    }
    if (objform.zzl.value != "") { //检查：总质量
            if (eval(objform.zzl.value) < 1000) {
	        alert('总质量要填入大于1000的数字\n可精确到小数点后一位数！');
            	objform.zzl.select();
            	return false; 
            }
    }
    if (objform.hdzzl.value != "") { //检查：核定载质量
        if (!isDigitString(objform.hdzzl.value,6,1) || objform.hdzzl.value.charAt(0)=="0") {
            	alert('核定载质量要填入大于1000的数字\n可精确到小数点后一位数！');
            	objform.hdzzl.select();
                return false; 
        }

    }
    if (objform.hdzzl.value != "") { //检查：核定载质量
            if (eval(objform.hdzzl.value) < 1000) {
            	alert('核定载质量要填入大于1000的数字\n可精确到小数点后一位数！');
            	objform.hdzzl.select();
                return false; 
            }
    }
    if (objform.nynse.value != "") { //检查：年应纳税额
        if (!isDigitString(objform.nynse.value,8,2) || objform.nynse.value.charAt(0)=="0") {
            	alert('年应纳税额要填入数字\n可精确到小数点后两位数！');
            	objform.nynse.select();
                return false; 
        }

    }
        
    return true;    
}

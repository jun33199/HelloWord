function checkValid(objform) { //check validation
    if (getStringLength(objform.hphm.value) > 10) { //��飺���ƺ���
        alert('���ƺ��������������������Ϊ10��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        objform.hphm.focus();
        return false;	
    }
    if (getStringLength(objform.cjh.value) > 30) { //��飺���ܺ�
        alert('���ܺ������������������Ϊ30��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        objform.cjh.focus();
        return false;	
    }
    if (objform.czzjlx.value == "") {  //��飺����֤������
    	alert('֤������һ��Ҫѡ��');
    	objform.czzjlx.focus();
    	return false;    	
    }
    if (objform.czzjhm.value == "") {  //��飺����֤������_�ǿ���
    	alert('֤������һ��Ҫ��д��');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (getStringLength(objform.czzjhm.value) > 18) {  //��飺����֤������_����
    	alert('֤�����������������������Ϊ18��\n���ֳ���Ϊ2����ĸ������Ϊ1');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (objform.czzjlx.options[objform.czzjlx.selectedIndex].innerText=="���֤" && checkIdentityCard(objform.czzjhm.value) != 0) {  //��飺����֤������_������
    	alert('���֤���벻��ȷ��');
    	objform.czzjhm.focus();
    	return false;    	
    }
    if (objform.gjdm.value == "") {  //��飺����
    	alert('����һ��Ҫѡ��');
    	objform.gjdm.focus();
    	return false;
    }
    if (getStringLength(objform.cpxh.value) > 20) { //��飺�����ͺ�
        alert('�����ͺ������������������Ϊ20��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        objform.cpxh.focus();
        return false;	
    }
    if (getStringLength(objform.fdjh.value) > 30) { //��飺��������
        alert('�������������������������Ϊ30��\n���ֳ���Ϊ2����ĸ������Ϊ1');
        objform.fdjh.focus();
        return false;	
    }
    //������ںϸ���
    if (objform.fsrq_n.value != "" || objform.fsrq_y.value != "" || objform.fsrq_r.value != "") { //��飺��ʻʱ��
        var fsrq = objform.fsrq_n.value + "-" + objform.fsrq_y.value + "-" + objform.fsrq_r.value;
        if (!isDate(fsrq)) {
            alert('��ʻʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    if (objform.bfrq_n.value != "" || objform.bfrq_y.value != "" || objform.bfrq_r.value != "") { //��飺����ʱ��
        var bfrq = objform.bfrq_n.value + "-" + objform.bfrq_y.value + "-" + objform.bfrq_r.value;
        if (!isDate(bfrq)) {
            alert('����ʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    if (objform.tsrq_n.value != "" || objform.tsrq_y.value != "" || objform.tsrq_r.value != "") { //��飺ͣʻʱ��
        var tsrq = objform.tsrq_n.value + "-" + objform.tsrq_y.value + "-" + objform.tsrq_r.value;
        if (!isDate(tsrq)) {
            alert('ͣʻʱ���ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    if (objform.djrq_n.value != "" || objform.djrq_y.value != "") { //��飺�Ǽ�����
        var djrq = objform.djrq_n.value + "-" + objform.djrq_y.value + "-01";
        if (!isDate(djrq)) {
            alert('�Ǽ����ڸ�ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    if (objform.fxszrq_n.value != "" || objform.fxszrq_y.value != "" || objform.fxszrq_r.value != "") { //��飺����ʻ֤����
        var fxszrq = objform.fxszrq_n.value + "-" + objform.fxszrq_y.value + "-" + objform.fxszrq_r.value;
        if (!isDate(fxszrq)) {
            alert('����ʻ֤���ڸ�ʽ����ȷ��\n������1999-09-09��yyyy-mm-dd��');
            return false;	
        }
    }
    if (objform.hdzk.value != "") { //��飺�˶��ؿ�
        if (!isAllCharValid(objform.hdzk.value,"0123456789") || objform.hdzk.value.charAt(0)=="0") {
            alert('�˶��ؿ�Ҫ������������');
            objform.hdzk.select();
            return false; 
        }
    }
    if (objform.jssqpgc.value != "") { //��飺��ʻ��ǰ�Ź���
        if (!isAllCharValid(objform.jssqpgc.value,"0123456789") || objform.jssqpgc.value.charAt(0)=="0") {
            alert('��ʻ��ǰ�Ź���Ҫ������������');
            objform.jssqpgc.select();
            return false; 
        }
    }
    if (objform.zzl.value != "") { //��飺������
        if (!isDigitString(objform.zzl.value,6,1) || objform.zzl.value.charAt(0)=="0") {
	        alert('������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	objform.zzl.select();
            	return false; 
        }
    }
    if (objform.zzl.value != "") { //��飺������
            if (eval(objform.zzl.value) < 1000) {
	        alert('������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	objform.zzl.select();
            	return false; 
            }
    }
    if (objform.hdzzl.value != "") { //��飺�˶�������
        if (!isDigitString(objform.hdzzl.value,6,1) || objform.hdzzl.value.charAt(0)=="0") {
            	alert('�˶�������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	objform.hdzzl.select();
                return false; 
        }

    }
    if (objform.hdzzl.value != "") { //��飺�˶�������
            if (eval(objform.hdzzl.value) < 1000) {
            	alert('�˶�������Ҫ�������1000������\n�ɾ�ȷ��С�����һλ����');
            	objform.hdzzl.select();
                return false; 
            }
    }
    if (objform.nynse.value != "") { //��飺��Ӧ��˰��
        if (!isDigitString(objform.nynse.value,8,2) || objform.nynse.value.charAt(0)=="0") {
            	alert('��Ӧ��˰��Ҫ��������\n�ɾ�ȷ��С�������λ����');
            	objform.nynse.select();
                return false; 
        }

    }
        
    return true;    
}

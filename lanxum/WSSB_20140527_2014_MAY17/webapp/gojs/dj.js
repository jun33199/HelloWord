/*
   �ύ����
   @param action  ��ת����  ָ��Ҫ���õ�action 
   @param param1  ҳ����ת����  ����ָ��action�еķ��������磺doSave,doQuery...��
   @param param2  ��ѯ��������  ������action �з��������ҳ�湲�õ���������ֲ�ͬ��ҳ�������
   @param param3  ������Ϣ���� 
   @param param4  ��������룬����У������ļ���������ʽ�Ƿ���ȷ
   @param param5  ActionForm���ȫ�������ڵõ�ÿ��action ����Ӧ��actionForm(�ڵ���DjAction �е�doCancelʱʹ��)
   @param param6  forward�����֣��ڵ���DjAction �е�doCancelʱʹ��
*/
function func_web_submit(direction,param1,param2,param3,param4,param5,param6) { 
    
    //��֤���������
    if(param1 == "Query"){
        var flag = checkJsjdm(param4);
        if(!flag){
            alert("��������ȷ�ļ��������!");
            
            return;    
        }
    }
	
	var str=direction;
	if(param1 != null){
	   str +="?actionType=" + param1;	
	}
	if(param2 != null){
	   str +=(param1 == null)? "?":"&" +"queryType=" + param2;	
	}
	if(param3 != null){
	   str +="&" +"DJ_CP=" + param3;	
	}
	if(param5 != null){
	   str +="&" +"className=" + param5;	
	}
	if(param6 != null){
	   str +="&" +"forward=" + param6;	
	}
	
   	document.forms[0].action =str;	
   	document.forms[0].submit();
}
/**
   ���������趨Ĭ��ֵ
   @param obj ������
   @param str Ĭ��ֵ
*/
//function setDefaultValue(obj,str){
//        if(obj == null || str == null || str == ""){
//            return ;
//        }
//        for(var i=0;i<obj.options.length;i++){
//            if(obj.options[i].text == str){
//                obj.selectedIndex = i;
//                return ;
//            }
//        }
//        
//    }	

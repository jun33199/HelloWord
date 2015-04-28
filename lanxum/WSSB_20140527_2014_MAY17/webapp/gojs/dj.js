/*
   提交请求
   @param action  跳转方向  指定要调用的action 
   @param param1  页面跳转参数  用于指定action中的方法（比如：doSave,doQuery...）
   @param param2  查询条件参数  （对于action 中方法被多个页面共用的情况，区分不同的页面的请求）
   @param param3  报错信息参数 
   @param param4  计算机代码，用于校验输入的计算机代码格式是否正确
   @param param5  ActionForm类的全名，用于得到每个action 所对应的actionForm(在调用DjAction 中的doCancel时使用)
   @param param6  forward的名字，在调用DjAction 中的doCancel时使用
*/
function func_web_submit(direction,param1,param2,param3,param4,param5,param6) { 
    
    //验证计算机代码
    if(param1 == "Query"){
        var flag = checkJsjdm(param4);
        if(!flag){
            alert("请输入正确的计算机代码!");
            
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
   给下拉框设定默认值
   @param obj 下拉框
   @param str 默认值
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

<!--文件说明：税务登记综合查询应用-->

<!-- to first Page-->
function firstPage(){
	if(parseInt(document.forms[0].pageNum.value) > 1){
		moveToPage(1);
	}else{
		alert("当前页为第一页！");
	}
}
<!-- to previous Page-->
function previousPage(){
	if(parseInt(document.forms[0].pageNum.value) > 1){
		moveToPage(parseInt(document.forms[0].pageNum.value)-1);
	}else{
		alert("当前页为第一页！");
	}
}
<!--  to next Page-->
function nextPage(){
	 if(parseInt(document.forms[0].pageNum.value) < parseInt(document.forms[0].totalPage.value)){
	    moveToPage(parseInt(document.forms[0].pageNum.value)+1);
	 }else{
	 	alert("当前页为最后一页！");
	 }
}
<!--  to last Page-->
function lastPage(){
	if(parseInt(document.forms[0].pageNum.value) < parseInt(document.forms[0].totalPage.value)){
	    moveToPage(parseInt(document.forms[0].totalPage.value));
	 }else{
	 	alert("当前页为最后一页！");
	 }
}
<!--  when user Input the "Enter" to query info-->
function enterToPage(pageNo){
	if(window.event.keyCode==13)
		moveToPage(pageNo);
	else return false;

}
<!--  move to special page-->
function moveToPage(pageNo){
	if(checkNumber(trimString(pageNo))){
		if(parseInt(pageNo) > parseInt(document.forms[0].totalPage.value)){
			alert("您输入的页码大于最大页码，请重新输入！");
			return false;

		}else if (parseInt(pageNo)<1){
			alert("您输入的页码小于1，请重新输入！");
			return false;
		}else if(pageNo==document.forms[0].pageNum.value){
			alert("当前页就是第"+pageNo+"页！");
			return false;
		}
		document.forms[0].style.cursor="wait";
		document.forms[0].pageNum.value=pageNo;
		document.forms[0].actionType.value="PageRoll";
		document.forms[0].submit();
	}else{
		alert("您输入的是非数字字符，请您重新新输入！");
		document.forms[0].switchPage.value=document.forms[0].pageNum.value;
	}
}
<!-- according to queryType ,to do different query-->
function doQuery(queryType){
    if(checkData()){
    	    document.forms[0].style.cursor="wait";
	    document.forms[0].pageNum.value=1;
	    if(queryType==null || trimString(queryType)==""){
	        document.forms[0].actionType.value="Query";
	    }else{
	        document.forms[0].actionType.value=queryType;
	    }
	    document.forms[0].submit();
   }
}


// 去掉字符串前后的空格。
// @parameter strValue 需要处理的字符串。
// @return String 前后空格和制表符都去掉
function trimString(strValue)
{
    if (strValue==null)
    {
        return null;
    }
    var returnValue = strValue;

    //删除字符串前面的空格(=32=0x20)和制表符(=09=0x09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0)
    {
        var code = returnValue.charCodeAt(0);
        if (code==32 || code==9 || code==41377)
        {
            returnValue = returnValue.substr(1);
        }
        else
        {
            break;
        }
    }

    //删除字符串后面的空格(SPACE=20)和制表符(TAB=09)
    //以及中文字符的空格(=41377=0xA1A1)
    while (returnValue.length>0)
    {
        var index = returnValue.length-1;
        var code = returnValue.charCodeAt(index);
        if (code==32 || code==9 || code==41377)
        {
            returnValue = returnValue.substr(0,index);
        }
        else
        {
            break;
        }
    }

    return returnValue;
}

<!--退出操作-->
function Exit(){

    document.forms[0].actionType.value="Return";
    document.forms[0].submit();
}
<!--打印操作-->
function doPrint(){
   var saveConfirm=confirm("本次导出记录数最多为2000条，如需更多信息请使用查询服务器;\n\r"
   			  +"                          是否导出Excel？");
   if(saveConfirm==true)
   {
    document.forms[0].actionType.value="Export";
    document.forms[0].submit();    
   }
}
<!--页面的一些共有的方法或是设置-->
function diplayView(){
   //设置switchPage的属性
   document.forms[0].switchPage.maxLength=10;
   document.forms[0].switchPage.style.textAlign="right";
   document.forms[0].switchPage.size="4";
   
   //初始化没有查询数据的时候的操作
   if(document.forms[0].pageNum.value=="" || parseInt(document.forms[0].totalPage.value) == 0){
		layerView.style.display="none"
		document.forms[0].first.disabled=true;
		document.forms[0].previous.disabled=true;
		document.forms[0].next.disabled=true;
        document.forms[0].last.disabled=true;
		document.forms[0].print.disabled=true;
		document.forms[0].switchPage.disabled=true;
		document.forms[0].switchPage.className="txtInput";
		document.forms[0].switchPage.value = "";
    }
    
    //select 校验
    checkSelect();
    checkData();
}

function checkSelect(){
    if(document.forms[0].zgswjg!=null && document.forms[0].zgsws!=null){
        if(document.forms[0].zgswjg.options.length==2){
            document.forms[0].zgswjg.options.remove(0);
            document.forms[0].zgswjg.options.selectedIndex=0;
            
        }
        addFilterWithNull(document.forms[0].zgswjg.value,document.forms[0].zgsws,2,arySelect_zgsws);
        if(document.forms[0].zgsws.options.length==2){
            document.forms[0].zgsws.options.remove(0);
        }
    }

}
function checkNumber(strValue){
    if(strValue!=null && trimString(strValue).length>0){
         var regExp = /^[\d]{0,10}$/g;
         if(regExp.exec(strValue) == null)
         {
            return false;
         }
     }
	return true;    
    
}



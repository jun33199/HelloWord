<!--�ļ�˵����˰��Ǽ��ۺϲ�ѯӦ��-->

<!-- to first Page-->
function firstPage(){
	if(parseInt(document.forms[0].pageNum.value) > 1){
		moveToPage(1);
	}else{
		alert("��ǰҳΪ��һҳ��");
	}
}
<!-- to previous Page-->
function previousPage(){
	if(parseInt(document.forms[0].pageNum.value) > 1){
		moveToPage(parseInt(document.forms[0].pageNum.value)-1);
	}else{
		alert("��ǰҳΪ��һҳ��");
	}
}
<!--  to next Page-->
function nextPage(){
	 if(parseInt(document.forms[0].pageNum.value) < parseInt(document.forms[0].totalPage.value)){
	    moveToPage(parseInt(document.forms[0].pageNum.value)+1);
	 }else{
	 	alert("��ǰҳΪ���һҳ��");
	 }
}
<!--  to last Page-->
function lastPage(){
	if(parseInt(document.forms[0].pageNum.value) < parseInt(document.forms[0].totalPage.value)){
	    moveToPage(parseInt(document.forms[0].totalPage.value));
	 }else{
	 	alert("��ǰҳΪ���һҳ��");
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
			alert("�������ҳ��������ҳ�룬���������룡");
			return false;

		}else if (parseInt(pageNo)<1){
			alert("�������ҳ��С��1�����������룡");
			return false;
		}else if(pageNo==document.forms[0].pageNum.value){
			alert("��ǰҳ���ǵ�"+pageNo+"ҳ��");
			return false;
		}
		document.forms[0].style.cursor="wait";
		document.forms[0].pageNum.value=pageNo;
		document.forms[0].actionType.value="PageRoll";
		document.forms[0].submit();
	}else{
		alert("��������Ƿ������ַ����������������룡");
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


// ȥ���ַ���ǰ��Ŀո�
// @parameter strValue ��Ҫ������ַ�����
// @return String ǰ��ո���Ʊ����ȥ��
function trimString(strValue)
{
    if (strValue==null)
    {
        return null;
    }
    var returnValue = strValue;

    //ɾ���ַ���ǰ��Ŀո�(=32=0x20)���Ʊ��(=09=0x09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

    //ɾ���ַ�������Ŀո�(SPACE=20)���Ʊ��(TAB=09)
    //�Լ������ַ��Ŀո�(=41377=0xA1A1)
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

<!--�˳�����-->
function Exit(){

    document.forms[0].actionType.value="Return";
    document.forms[0].submit();
}
<!--��ӡ����-->
function doPrint(){
   var saveConfirm=confirm("���ε�����¼�����Ϊ2000�������������Ϣ��ʹ�ò�ѯ������;\n\r"
   			  +"                          �Ƿ񵼳�Excel��");
   if(saveConfirm==true)
   {
    document.forms[0].actionType.value="Export";
    document.forms[0].submit();    
   }
}
<!--ҳ���һЩ���еķ�����������-->
function diplayView(){
   //����switchPage������
   document.forms[0].switchPage.maxLength=10;
   document.forms[0].switchPage.style.textAlign="right";
   document.forms[0].switchPage.size="4";
   
   //��ʼ��û�в�ѯ���ݵ�ʱ��Ĳ���
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
    
    //select У��
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



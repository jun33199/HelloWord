<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<!--add by hazhengze 20081201 start-->
<%@ include file="/include/QRCodeHeader.jsp"%>
<!--add by hazhengze 20081201 end-->

<HTML><HEAD><TITLE>������˰�Ǹ�����Ϣ¼���</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="<%=static_file%>/js/inputchecker.js"></script>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>


<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	document.forms[0].operationType.value=operationType;
    document.forms[0].yhbs.value=<%=Constants.ZB_YHBS_FGR%>
	 if(operationType=="GetNsrxx" )
  {


  }

	if(operationType=="Save" )
	{



	//add by hazhengze 20090108 start
	//����ڷ���Ȩ��ת������Ϊ�����������ݽ���ʱ������ѡ������������
	  if ((document.forms[0].tdfwqszylx.value == "03") || (document.forms[0].tdfwqszylx.value == "04")|| (document.forms[0].tdfwqszylx.value == "05")){
			if(document.forms[0].tdjc.value=="00"){
				alert("���ݽ��ױ���ѡ��������������ѡ��");
				return false;
			}
	  }
	//add by hazhengze 20090108 end

  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("��������벻��Ϊ�գ����������룡");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
 if(document.forms[0].fl.value=="99"&&document.forms[0].beizhu.value == ""){
					 
							 alert("��ע�в������ɲ���Ϊ�գ����������룡");
							 document.forms[0].beizhu.focus();
						  	   return false;			
			  	}
		
  	if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("���������Ʋ���Ϊ�գ����ȡ�Ǽ���Ϣ��");
  	   document.all.imgGetNsrxx.focus();
  	   return false;
  	}else{
      var smnsrmc = document.forms[0].smnsrmc.value;
	  if(smnsrmc != ""){
		  if (document.forms[0].nsrmc.value != smnsrmc){
		   alert("������������ɨ��ȡ�õ����������Ʋ���ͬ������Ǽ���Ϣ��");
		   document.all.imgGetNsrxx.focus();
		   return false;
		 }
	  }
    }


        if (document.forms[0].lxrxm.value == "")
  	{
  	   alert("��ϵ����������Ϊ�գ����������룡");
  	   document.forms[0].lxrxm.focus();
  	   return false;
  	}




        if (document.forms[0].fdcxmmc.value == "")
  	{
  	   alert("���ز���Ŀ���Ʋ���Ϊ�գ����������룡");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
        if (document.forms[0].hyqdsj.value == "" || !checkDate(document.forms[0].hyqdsj.value))
  	{
  	   alert("��Լǩ��ʱ�䲻��Ϊ�ջ��ʽ����ȷ�����������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}
        if (!cmpDate(document.forms[0].hyqdsj.value,document.forms[0].xtdqsj.value))
  	{
  	   alert("��Լǩ��ʱ�䲻�ܴ��ڵ�ǰʱ�䣬���������룡");
  	   document.forms[0].hyqdsj.focus();
  	   return false;
  	}

        if (document.forms[0].tdfwzldz.value == "")
  	{
  	   alert("���ء����������ַ����Ϊ�գ����������룡");
  	   document.forms[0].tdfwzldz.focus();
  	   return false;
  	}

    //����Ȩ��ת������=����ת�û��߳���,ֻ����д���������������Բ������ֻ��һ��
    if ((document.forms[0].tdfwqszylx.value == "01") || (document.forms[0].tdfwqszylx.value == "02"))
    {

        //�޸�������������ݽ������С��λ��Լ��Ϊ3λ��
            //���ҵ����µ�js����
            //modify by fujx��20081201
	    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].tdfwqszymj.value,3,"�������"))
	    {

	      document.forms[0].tdfwqszymj.focus();
	      return false;
	    }
	    if (document.forms[0].fwjzmj.value !="")
	    {
           //�޸�������������ݽ������С��λ��Լ��Ϊ3λ��
            //���ҵ����µ�js����
            //modify by fujx��20081201
		    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].fwjzmj.value,3,"���ݽ������"))
		    {

		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
	    }
    }
    else
    {

        //�޸�������������ݽ������С��λ��Լ��Ϊ3λ��
            //���ҵ����µ�js����
            //modify by fujx��20081201
        if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].fwjzmj.value,3,"���ݽ������"))
    	//if (!FN_QSCheckNumberDigit(document.forms[0].fwjzmj.value,2,"���ݽ������"))
		    {
                 //alert("dddd");
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
           //�޸�������������ݽ������С��λ��Լ��Ϊ3λ��
            //���ҵ����µ�js����
            //modify by fujx��20081201
            if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].tdfwqszymj.value,3,"���������"))
		    //if (!FN_QSCheckNumberDigit(document.forms[0].tdfwqszymj.value,2,"���������"))
		    {
		      document.forms[0].tdfwqszymj.focus();
		      return false;
		    }
	    }

	}



	<%-- %>
    //�����Ϊ�ձ���Ϊ����
    if(document.forms[0].cjjgyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgyrmb.value,2,"����ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].pgjg.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].pgjg.value,2,"����������۸�"))
	    {
	      document.forms[0].pgjg.focus();
	      return false;
	    }
    }

    if(document.forms[0].cjjgywb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].cjjgywb.value,2,"��ҳɽ��۸�"))
	    {
	      document.forms[0].cjjgywb.focus();
	      return false;
	    }
    }

    if(document.forms[0].zhyrmb.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].zhyrmb.value,2,"�ۺ�����Ҽ۸�"))
	    {
	      document.forms[0].zhyrmb.focus();
	      return false;
	    }
    }

    if(document.forms[0].hn.value != "")
    {
	    if (!FN_QSCheckNumberDigit(document.forms[0].hn.value,5,"����"))
	    {
	      document.forms[0].hn.focus();
	      return false;
	    }
    }

    //����ɽ�����Ҽ۸�Ϊ�գ������۸����Ҽ۸��ܶ�Ϊ��
    if(document.forms[0].cjjgyrmb.value == ""){
      if(document.forms[0].pgjg.value == ""){
        if(document.forms[0].cjjgywb.value == "")
        {
          alert("�ɽ�����Ҽ۸������۸����Ҽ۸��ܶ�Ϊ�գ�");
          document.forms[0].cjjgyrmb.focus();
          return false;
        }
        else if(document.forms[0].cjjgywb.value != "")
        {

          if(document.forms[0].bz.value == "")
          {
            alert("���ֲ���Ϊ�գ������룡");
            document.forms[0].bz.focus();
            return false;

          }
          if(document.forms[0].hn.value == "")
          {
            alert("���ʲ���Ϊ�գ������룡");
            document.forms[0].hn.focus();
            return false;

          }
          if(document.forms[0].zhyrmb.value == "")
          {
            alert("�ۺ�����Ҳ���Ϊ�գ������룡");
            document.forms[0].zhyrmb.focus();
            return false;
          }
        }
      }
    }
    if(document.forms[0].cjjgywb.value != "")
    {

      if(document.forms[0].bz.value == "")
      {
        alert("���ֲ���Ϊ�գ������룡");
        document.forms[0].bz.focus();
        return false;

      }
      if(document.forms[0].hn.value == "")
      {
        alert("���ʲ���Ϊ�գ������룡");
        document.forms[0].hn.focus();
        return false;

      }
      if(document.forms[0].zhyrmb.value == "")
      {
        alert("�ۺ�����Ҳ���Ϊ�գ������룡");
        document.forms[0].zhyrmb.focus();
        return false;
      }
    }

    <%  --%>




			document.forms[0].ztbs.value=<%=Constants.JMSBB_ZTBS_BC%>

			//document.forms[0].zjlxmc.value = document.forms[0].sfzjlx.options[document.forms[0].sfzjlx.selectedIndex].text;
			document.forms[0].flmc.value = document.forms[0].fl.options[document.forms[0].fl.selectedIndex].text;
			document.forms[0].tdfwqszylxmc.value = document.forms[0].tdfwqszylx.options[document.forms[0].tdfwqszylx.selectedIndex].text;
			if(document.forms[0].tdfwqszylx.value=="03")
			{
					document.forms[0].fwlbmc.value = document.forms[0].fwlb.options[document.forms[0].fwlb.selectedIndex].text;
			}
			else
			{
					document.forms[0].fwlb.value = "";
					document.forms[0].fwlbmc.value = "";
			}

		    //��������س��û���ת��
		    if ((document.forms[0].tdfwqszylx.value == "01") || (document.forms[0].tdfwqszylx.value == "02")  )
		    {
		        document.forms[0].fwjzmj.value = 0;
		    }
			<%--document.forms[0].bzmc.value = document.forms[0].bz.options[document.forms[0].bz.selectedIndex].text;--%>
            if (document.forms[0].khyhdm.value != "")
            {
			   document.forms[0].khyhmc.value = document.forms[0].khyhdm.options[document.forms[0].khyhdm.selectedIndex].text;
			}
			document.forms[0].nsrlxmc.value = document.forms[0].nsrlx.options[document.forms[0].nsrlx.selectedIndex].text;

  }
  document.forms[0].submit();
}


function checkSelect()
{
    if(document.forms[0].tdfwqszylx.value=="03")
    {
       document.forms[0].fwlb.disabled=false ;
    }
    else
    {
        document.forms[0].fwlb.disabled=true ;

    }

}

function initPage()
{
  <!--add by yangxiao 20090209 start-->
  var smnsrmc = document.forms[0].smnsrmc.value;
  var nsrmc = document.forms[0].nsrmc.value;
  if(nsrmc != "" && smnsrmc != ""){
    if(nsrmc != smnsrmc){
      alert("��˰��������ɨ��ȡ�õ���˰�����Ʋ���ͬ������Ǽ���Ϣ��");
      document.all.imgGetNsrxx.focus();
    }
  }
  <!--add by yangxiao 20090209 end-->
  //��λ���㵽ɨ�������¼������
  document.all.scanInputBzqsFgr.focus();

}
</SCRIPT>
<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->
<BODY bgColor=#ffffff leftMargin=0 onload="MM_preloadImages(
              '<%=static_file%>imagess/dayin2.jpg',
              '<%=static_file%>imagess/fanhui2.jpg',
              '<%=static_file%>imagess/tuichu2.jpg',
              '<%=static_file%>imagess/diyitiao2.jpg',
              '<%=static_file%>imagess/shangyitiao2.jpg',
              '<%=static_file%>imagess/zuimotiao2.jpg',
              '<%=static_file%>imagess/xinzeng2.jpg',
              '<%=static_file%>imagess/shanchu2.jpg'),initPage(),checkSelect()"
              topMargin=0 marginheight="0" marginwidth="0">


<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="������˰¼��>������˰�Ǹ�����Ϣ¼���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <TD vAlign=top>
         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class="1-td1">�����еط�˰��ֲ�����˰��Ϣ�ɼ���-�Ǹ���</TD></TR>
        <TR>
          <TD class="1-td2" vAlign=top>
<html:form action="/bzqslr/addBzqsFgr.do">
              <html:hidden property="operationType"/>
              <html:hidden property="xtdqsj"/>
              <html:hidden property="ztbs" value="<%=Constants.ZB_ZTBS_BC%>"/>
              <html:hidden property="yhbs" value="<%=Constants.ZB_YHBS_GR%>"/>
              <html:hidden property="smnsrmc"/>
<!--add by hazhengze 20081201 start-->

<BR>�ɼ���:<input type="text" name="scanInputBzqsFgr" id="scanInputBzqsFgr" size="80"  maxlength="5000" onkeydown="check()">
<IMG alt="����" height=22 id="shengcheng" name="shengcheng"
 onclick="shengcheng_method()" onmouseout=MM_swapImgRestore()
 onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/shengcheng2.jpg',1)"
src="<%=static_file%>images/shengcheng1.jpg" style="CURSOR: hand" width=79>
<IMG alt="��λ" height=22 id="fuwei" name="fuwei"
  onclick="fuwei_method()" onmouseout=MM_swapImgRestore()
  onmouseover="MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)"
src="<%=static_file%>images/fuwei1.jpg" style="CURSOR: hand" width=79>

<SCRIPT language=javascript>

//��λ���㵽ɨ�������¼�����ϣ����ý��㵽�����ı������
function QRInputFocus(){
	//��λ���㵽ɨ�������¼������
	document.all.scanInputBzqsFgr.focus();
	//���ý��㵽�����ı������
	var e=document.all.scanInputBzqsFgr;
	var r =e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse(true);
	r.select();
}

function fuwei_method(){
	//alert("call fuwei()");
	//alert("translateObj:"+translateObj);
	//��1�Σ�ͷ��־������������
	//��2�Σ���ͬ���
	document.forms[0].htbh.value="";
	document.forms[0].htbh.readOnly=false;
	document.forms[0].htbh.style.color="";
	//��3�Σ���ͬ����ǩԼ����
	document.forms[0].hyqdsj.value="";
	document.forms[0].hyqdsj.readOnly=false;
	document.forms[0].hyqdsj.style.color="";
	//��4�Σ����׷��ݵ�ַ���أ���������
	//��5�Σ����׷��ݵ�ַ
	document.forms[0].tdfwzldz.value="";
	document.forms[0].tdfwzldz.readOnly=false;
	document.forms[0].tdfwzldz.style.color="";
	//��6�Σ�����Ȩ��ת������
	document.forms[0].tdfwqszylx.onclick="";
	document.forms[0].tdfwqszylx.style.color="";
	//��7�Σ��Ƿ�Ϊ�״������ѹ��������������룩
	//��8�Σ����ݲ�Ȩ֤�ţ��������룩
	//��9�Σ���������Ȩ֤����ڣ��������룩
	//��10�Σ����ݽ������
	document.forms[0].fwjzmj.value="";
	document.forms[0].fwjzmj.readOnly=false;
	document.forms[0].fwjzmj.style.color="";
	//��11�Σ������ṹ���������룩
	//��12�Σ��滮��;���������룩
	//��13�Σ���ͬ�ܼۣ��������룩
	//��14�Σ�����¥�㣬�ܲ������������룩
	//��15�Σ�����֣��������룩
	//��16�Σ���ҽ��������룩
	//��17�Σ����ʣ��������룩
	//��18�Σ�������Ϣ���������룩
	//��19�Σ�����Ϣ
		for(i=0;i<document.getElementsByName("nsrmc");i++){
  			document.getElementsByName("nsrmc")[i].value="";
  		}
  		var ele_nsrmc_show=document.getElementById('nsrmc_show');
        if(ele_nsrmc_show.hasChildNodes() != null){
          ele_nsrmc_show.removeChild(ele_nsrmc_show.lastChild);
        }
  		//֤������
  		document.forms[0].jsjdm.value="";
  		//��ϵ�ˣ�û�У�

  		//��ϵ�˵绰
  		document.forms[0].lxdh.value="";
        //��������
        document.forms[0].khyhdm.value="";
	//��20�Σ����ز��н�������ƣ��������룩
	//��λ���㵽ɨ�������¼������
	QRInputFocus();
  document.forms[0].operationType.value='Show';
  document.all.scanInputBzqsFgr.value="";
}

function shengcheng_method(){
	//alert("call shengcheng()");
	//��λ���㵽ɨ�������¼������
	QRInputFocus();
	//
	str_temp=document.all.scanInputBzqsFgr.value;
	if(str_temp==""){
		alert("��ɨ�����ݣ�������ɨ�����룡");
		return false;
	}else{
		scanPic();
		return false;
	}
}

//ɨ��ǹɨ�����ݺ��Զ����������¼�
function check(){
	//IE7.0���������´������ǻس�
	if(event.keyCode==13){
		//alert("��⵽һ��Enter...");
		transObj=scanPic();
		return false;
	}
}

//���ö�̬�⣬ʶ��ͼƬ����ʶ�������������ʾ��ҳ����
function scanPic(){
	//alert(">>>ɨ����Ͽ�ʼ������");
	var istr=document.all.scanInputBzqsFgr.value;
	//alert("ɨ���ַ���Ϊ��"+istr+"��");
	//���ÿ��еķ���
	var obj=new ActiveXObject("hyQRBarCode.QRCode");
	//alert("obj="+obj);
	var transObj=obj.DeBarCodeString(istr);
	//alert("transObj="+transObj);
	//�ж��Ƿ��ҵ��������
	var transObjArray=transObj.split("^");
	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
		alert("ɨ������������Ϊ�գ����ֹ�¼���걨��");
		fuwei_method();
		return false;
	}

	//����ά�������Ƿ���ȷ��
	//alert("��ʼ����ά��ͷ���ݵĸ�ʽ");
	QRCodeCheckResult=checkQRCodeHeader(transObj);
	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	//
	if(QRCodeCheckResult=="1"){
		alert("ɨ��ͼƬʧ�ܣ�������ɨ����ֹ�¼����Ϣ��");
		fuwei_method();
		return false;
	}else if(QRCodeCheckResult=="2"){//������Ĳ�Ϊ"object_"��ͷ���ʾ����ͼƬɨ�費ȫ����ʾ����ɨ��
		alert("��ɨ��ȫ����ά������ͼƬ�������޷����룡");
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="3"){
		translate_xinfang(transObj);//�·�
	}else if(QRCodeCheckResult=="4"){
		translate_cunliangfang(transObj);//������
	}
	//���¼���������ý���
	document.all.scanInputBzqsFgr.value="";
	document.all.scanInputBzqsFgr.focus();
	//alert(">>���...");
	return false;
	// document.picForm.piccode.value=ret;
}
//�������ַ�������������ҳ������·�
function translate_cunliangfang(translateObj){
	/**
	/* @todo �����·����н���
	*/
}

//�������ַ�������������ҳ����򣬶��ַ�
function translate_cunliangfang(translateObj){
	//alert("translateObj:"+translateObj);
	var transObjArray=translateObj.split("^");
	//��1�Σ�ͷ��־������������
	//��2�Σ���ͬ���
	index=2+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].htbh,tmpObj);
	//��3�Σ���ͬ����ǩԼ����
	index=3+0;
	tmpObj=transObjArray[index];
	tmpObj=formatDateStr(tmpObj);
	putObjectValue(document.forms[0].hyqdsj,tmpObj);
	//��4�Σ����׷��ݵ�ַ���أ���������
	//��5�Σ����׷��ݵ�ַ
	index=5+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].tdfwzldz,tmpObj);
	//��6�Σ�����Ȩ��ת������
	index=6+0;
	tmpObj=transObjArray[index];
	tmpObj="03";
	tmpObj_page=document.forms[0].tdfwqszylx;
	for(i=0;i<tmpObj_page.options.length;i++){
		if(tmpObj_page.options.value=tmpObj){
			tmpObj_page.style.color="#ADADAD";
			tmpObj_page.selectedIndex=i;
			tmpObj_page.value=tmpObj;
			tmpObj_page.onclick=function qr_readonly_tdfwqszylx(){alert("�����޸ģ�");return false;};//��λֻ��
			checkSelect();
		}
	}
	//��7�Σ��Ƿ�Ϊ�״������ѹ��������������룩
	//��8�Σ����ݲ�Ȩ֤�ţ��������룩
	//��9�Σ���������Ȩ֤����ڣ��������룩
	//��10�Σ����ݽ������
	index=10+0;
	tmpObj=transObjArray[index];
	putObjectValue(document.forms[0].fwjzmj,delFuhao(tmpObj));
	//��11�Σ������ṹ���������룩
	//��12�Σ��滮��;���������룩
	//��13�Σ���ͬ�ܼۣ��������룩
	//��14�Σ�����¥�㣬�ܲ������������룩
	//��15�Σ�����֣��������룩
	//��16�Σ���ҽ��������룩
	//��17�Σ����ʣ��������룩
	//��18�Σ�������Ϣ���������룩
	//��19�Σ�����Ϣ
	index=19+0;
	tmpObj=transObjArray[index];
	var mfxx = tmpObj.split("~");
  	if(mfxx[1] == "2"){
  		//��˰������(2009.2.9�޸�Ϊ����˰���������뵽������----yangxiao)
        document.forms[0].smnsrmc.value=mfxx[0];

  		for(i=0;i<document.getElementsByName("nsrmc");i++){
  			document.getElementsByName("nsrmc")[i].value=mfxx[0];
  		}
  		var ele_nsrmc_show=document.getElementById('nsrmc_show');
  		new_text=document.createTextNode(mfxx[0]);
  		ele_nsrmc_show.appendChild(new_text);
  		//֤������(2009.2.9�޸�Ϊ��д�룬��Ҫ�ֶ�����----yangxiao)
  		//document.forms[0].jsjdm.value=mfxx[3];
  		//��ϵ�ˣ�û�У�

  		//��ϵ�˵绰
  		document.forms[0].lxdh.value=mfxx[5];

  	}else{
    	alert("����ϢΪ���ˣ�������ɨ����ֹ�¼����Ϣ��");
    	fuwei_method()
    	return false;
  	}
	//��20�Σ����ز��н�������ƣ��������룩
}

//��ֵ����
function putObjectValue(Obj_page,obj_value){
	if(obj_value==null||obj_value==""||obj_value=="null"){
		Obj_page.readOnly=false//��λֻ��
	}else{
		Obj_page.readOnly=true//��λֻ��
		Obj_page.style.color="#ADADAD";
		Obj_page.value=obj_value;
	}
}
</SCRIPT>
<!--add by hazhengze 20081201 end-->

            <TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
		     		<TBODY>
					  <BR>
					  <TR>
			  	<TD class=2-td2-t-left width="23%" nowrap="nowrap">������˰��Ϣ�ɼ����</TD>
			  	<html:hidden name="bzqsForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="77%" colspan=5><DIV align=left>&nbsp<bean:write name="bzqsForm" property="sbbh" /> </DIV></TD>

			  </TR>
					 <TR>

				<TD class=2-td2-left nowrap="nowrap">�������ع����������</TD>
				<TD class=2-td2-left>
				<DIV align=left><html:text  property="fwtdbmslh" size="30" maxlength="30"/></DIV></TD>
                <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶο�ʼ-->
                                             <TD class=2-td2-left nowrap="nowrap">��ίҵ����</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="bzqsForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶν���-->

                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶο�ʼ-->
                                             <TD class=2-td2-left nowrap="nowrap">��ͬ���</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="bzqsForm" property="htbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶν���-->
			  </TR>
					  </TBODY>
					  </TABLE>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
              <TR>
                <TD class=2-td2-t-left width="8%" rowspan = "5">
                  <DIV align=right>�Ǹ�����</DIV>
                   <DIV align=right>д����</DIV>
				</TD>


              </tr>

              <tr>
                  <TD class=2-td2-t-left width="15%">
                   <DIV align=right>��˰���������&nbsp; </DIV>
                </TD>

                <DIV align=right>&nbsp; </DIV>
                  <TD class=2-td2-t-left width="25%">
                    <DIV align=left>
                    <html:text property="jsjdm" size="10" maxlength="8" />
                    <FONT color=red>*</FONT>
                    <span id="getDJimage1" style="visibility:visible"> <input type="image"  name="imgGetNsrxx" value="��ȡ�Ǽ���Ϣ" alt="��ȡ�Ǽ���Ϣ"
                     onClick="javascript:return checkSubmit('GetNsrxx');"
                     onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
                     onMouseOut="MM_swapImgRestore()"src="<%=static_file%>images/b-hqdjxx1.jpg"
                     width="79" height="22" id="b-hqdjxx1"></span>
                    </DIV></TD>



                <TD class=2-td2-t-left width="19%">
                  <DIV align=right>����������&nbsp; </DIV></TD>
                <TD colspan="2"  class=2-td2-t-center width="33%">
                   <DIV align=left>

                   <html:hidden property="nsrlxmc"/>
                    <bean:define id="list" name="bzqsForm" property="nsrlxList" />
                    <html:select property="nsrlx">
                    <html:options collection="list" labelProperty="nsrlxmc" property="nsrlxdm"/>
                    </html:select>
                   </DIV></TD>
             </TR>



             <TR>
                <TD  class=2-td2-left width="19%">
                  <DIV align=right>����������&nbsp;</DIV></TD>
                <TD colspan="4"  class=2-td2-center width="33%">
                   <DIV align=left id="nsrmc_show">
                   <bean:write name="bzqsForm" property="nsrmc"/>
                   <html:hidden property="nsrmc" />
                  </DIV></TD>
                  </TR>

                  <TR>
                 <TD class=2-td2-left width="15%">
                   <DIV align=right>��������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-center width="85%" colspan="4">
                  <DIV align=left>
                   <html:hidden property="khyhmc"/>
                    <bean:define id="list" name="bzqsForm" property="khyhList" />
                    <html:select property="khyhdm">
                    <html:options collection="list" labelProperty="khyhmc" property="yhdm"/>
                    </html:select>
                   </DIV></TD>


                <!--TD class=2-td2-left >
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                <TD class=2-td2-center width="33%">
                   <DIV align=left>&nbsp;<label id="labelYhzh"/>

										<bean:define id="list" name="bzqsForm" property="khyhList"/>
                    <html:select property="yhzh">
                    <html:options collection="list" labelProperty="zh" property="zh"/>
                    </html:select>
                    <html:hidden property="yhzh" />

                    </DIV>
										</TD-->

                  </TR>


                  <TR>
                 <TD class=2-td2-left width="15%">
                   <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                 <TD class=2-td2-left width="33%">
                   <DIV align=left>
<html:text property="lxrxm" size="10" maxlength="50" />
                  <FONT color=red>*</FONT> </DIV></TD>


                <TD   class=2-td2-center width="19%">
                  <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                   <DIV align=left>
<html:text property="lxdh" size="10" maxlength="20" />
                 </DIV></TD>

               </TR>




                  <TR>
                   <TD class=2-td2-left width="8%" rowspan = "6">
                  <DIV align=right>	���ط���</DIV>
                   <DIV align=right>Ȩ��ת��</DIV></TD>
                  <TD class=2-td2-left width="15%">
                   <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                <TD  colspan="4" class=2-td2-center width="15%">
                  <DIV align=left><html:text property="fdcxmmc" size="60" maxlength="100" /><FONT color=red>*</FONT> </DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
                </TD>
                 <TD class=2-td2-left width="19%">
                <DIV align=left><html:text property="hyqdsj" size="15"/>(yyyymmdd)<FONT color=red>*</FONT></DIV></TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>����&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>

                  <bean:define id="list" name="bzqsForm" property="flList" />

                  <html:select property="fl">
                   <html:options collection="list" labelProperty="qstdfwytmc" property="qstdfwytdm"/>
                   </html:select>
                   <html:hidden property="flmc"/>
                    </DIV></TD>
                  </TR>
                 <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
                 <TD class=2-td2-left width="19%">
                <DIV align=left>
                <html:hidden property="tdfwqszylxmc"/>

                  <bean:define id="list" name="bzqsForm" property="tdfwqszylxList" />
                  <html:select property="tdfwqszylx" onchange="checkSelect()" >
                   <html:options collection="list" labelProperty="qszyxsmc" property="qszyxsdm"/>
                   </html:select>
                    </DIV></TD>

                <TD class=2-td2-left width="19%">
                  <DIV align=right>�������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>

									<html:hidden property="fwlbmc"/>
                  <bean:define id="list" name="bzqsForm" property="fwlbList" />
                  <html:select property="fwlb" disabled="true">
                   <html:options collection="list" labelProperty="fwlxmc" property="fwlxdm"/>
                   </html:select>

                   </DIV></TD>
                  </TR>
                  <TR>
                 <TD  class=2-td2-left width="25%";>
                   <DIV align=right>���ء����������ַ&nbsp; </DIV>
                </TD>
                <TD colspan="4"  class=2-td2-center width="15%">
                  <DIV align=left><html:text property="tdfwzldz" size="60" maxlength="200" /><FONT color=red>*</FONT></DIV></TD>
                  </TR>


                  <TR>
                 <TD class=2-td2-left width="25%">
                   <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="15%">
                   <DIV align=left>���أ�<html:text property="tdfwqszymj" size="15" maxlength="14" />�O</DIV>
                   </TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left><html:text property="fwjzmj" size="15" maxlength="14"/>�O</DIV></TD>
               </TR>

                  <TR>
                 <TD class=2-td2-left width="25%">
                   <DIV align=right>�ݻ���&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="15%">
                   <DIV align=left>

			  		<html:select property="rjl" >
                        <html:option value="01">1.0���Ϻ�1.0</html:option>
                        <html:option value="00">1.0����</html:option>
                      </html:select>

				   </DIV>
                   </TD>

                <TD class=2-td2-left width="17%">
                    <!--�޸����ؼ���Ϊ������������-->
                  <DIV align=right>��������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="32%">
                  <DIV align=left>
				  <html:select property="tdjc" >
				        <!-- ����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
				                                   �˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020 -->
                        <html:option value="00">��ѡ��</html:option>
                      	<html:option value="21">5����</html:option>
                        <html:option value="22">5-6��</html:option>
                        <html:option value="23">6����</html:option>
                        <!-- 
                        <html:option value="10">================</html:option>
                        
                        <html:option value="11">�Ļ��ڱ�������</html:option>
                        <html:option value="12">�Ļ����ϲ�����</html:option>
                        <html:option value="13">�Ļ����廷��������</html:option>
                        <html:option value="14">�Ļ����廷�ϲ�����</html:option>
                        <html:option value="15">�廷��������������</html:option>
                        <html:option value="16">�廷�������ϲ�����</html:option>
                        <html:option value="17">���������</html:option>
                        <html:option value="00">================</html:option>
                        
                        <html:option value="01">��������</html:option>
                        <html:option value="02">�������Ļ�֮��</html:option>
                        <html:option value="03">�Ļ����廷֮��</html:option>
                        <html:option value="04">�廷����</html:option>
                         -->

                    </html:select>
					</DIV></TD>
               </TR>

				<%--  %>
                <TR>
                <TD class=2-td2-left width="8%" rowspan = "2">
                  <DIV align=right>�ɽ�</DIV>
                   <DIV align=right>�۸�<FONT color=red>*</FONT></DIV>
                  <TD  class=2-td2-left width="5%">
                  <DIV align=left><html:text property="cjjgyrmb" size="15" maxlength="15" value="0"/><br>Ԫ(�����) </DIV></TD>
                  <TD class=2-td2-left width="17%">
                  <DIV align=right>�����۸�&nbsp; </DIV></TD>
                <TD  colspan="2"  class=2-td2-center width="32%">
                   <DIV align=left><html:text property="pgjg" size="15" maxlength="15" value="0"/><br>Ԫ(�����) </DIV></TD>
              </TR>

               <TR>
                 <TD class=2-td2-left width="15%">
                   <DIV align=left><html:text property="cjjgywb" size="15" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/><br>Ԫ(���) </DIV></TD>
                <TD class=2-td2-left width="25%">
                  <DIV align=left>
									<html:hidden property="bzmc"/>
                  <bean:define id="list" name="bzqsForm" property="bzList" />
                  <html:select property="bz" onchange="checkSubmit('GetHL')">
                   <html:options collection="list" labelProperty="bzmc" property="bzdm"/>
                   </html:select>

                   ����:&nbsp;<html:text property="hn" size="15" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/></DIV></TD>


                <TD class=2-td2-center  width="17%">
                  <DIV align=left><html:text property="zhyrmb" size="15" maxlength="15"/><br>�ۺ�Ԫ(�����) </DIV></TD>
                </TR>
                <% --%>
                 <TR>
                 <TD class=2-td2-left width="15%">
                   <DIV align=right>��ע&nbsp; </DIV>
                </TD>
                <TD colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea property="beizhu" cols="45" rows="5" />
                  </DIV></TD>
                </TR>

                  </TBODY></TABLE><BR>

            <DIV align=center>

            <IMG alt=���� height=22 id=baocun name=Submit63
            onclick="checkSubmit('Save')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79> <IMG
            alt=�˳� height=22 id=tuichu name=tuichu
            onclick="checkSubmit('Return');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>


 </html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

<script language=JavaScript type='text/JavaScript'>
    <%-- %>fnConvertWb(document.all.cjjgywb,document.all.hn,document.all.zhyrmb);<% --%>
</script>





<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<!-- yangxiao 2008-12-06 start -->
<%@include file="../include/QRCodeHeader.jsp"%>
<!-- yangxiao 2008-12-06 end -->
<HTML><HEAD><TITLE>��˰�걨�Ǹ���¼���</TITLE>
<META content="text/html; charset=gb2312" http-equiv=Content-Type>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type="text/JavaScript"></SCRIPT>
<script language="javascript" src="../js/qscommon.js" type="text/JavaScript"></script>
<script language=JavaScript type='text/JavaScript'>
function doSubmitForm(operationType){
  document.all.operationType.value=operationType;
  if(operationType=="GetNsrxx" )
  {
  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("��������������");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
  }

  else if(operationType=="Fwjbxx" ){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='fwjbxx';
  }else if(operationType=="Cqxx"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='cqxx';
  }else if(operationType=="Gyzf"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='gyzf';
  }
  else if(operationType=="Fwjhxx"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='fwjhxx';
  }
  else if(operationType=="Print"){
    document.forms[0].operationType.value='Redirect';
    document.forms[0].subAction.value='print';
  }
  else if(operationType=="Save")
  {

    if (document.forms[0].sbbh.value != "")
    {
    	alert("�걨�����¼�Ѿ����ɣ����ܱ��棬������޸ģ�����޸Ĺ����޸ġ�");
    	return false;
    }

    if(document.forms[0].bl.value=="true")
    {

		if (document.forms[0].sbrq.value == "" || !checkDate(document.forms[0].sbrq.value))
		{
			alert("�걨���ڲ���Ϊ�ջ��ʽ����ȷ�����������룡");
			document.forms[0].sbrq.focus();
			return false;
		}
		if (!cmpDate(document.forms[0].sbrq.value,document.forms[0].xtdqsj.value))
		{
			alert("�걨���ڲ��ܴ��ڵ�ǰʱ�䣬���������룡");
			document.forms[0].sbrq.focus();
			return false;
		}
     }

  	if (document.forms[0].jsjdm.value == "")
  	{
  	   alert("��������벻��Ϊ�գ����������룡");
  	   document.forms[0].jsjdm.focus();
  	   return false;
  	}
  	if (document.forms[0].nsrmc.value == "")
  	{
  	   alert("��˰�����Ʋ���Ϊ�գ���ͨ������������ȡ��");
  	   //document.forms[0].nsrmc.focus();
       //������޷���λ��nsrmc����ʱ�޸�ΪͼƬ----yangxiao--2009.2.9
       document.all.imgGetNsrxx.focus();
  	   return false;
  	}

    if (document.forms[0].lxrxm.value == "")
  	{
  	   alert("��ϵ����������Ϊ�գ����������룡");
  	   document.forms[0].lxrxm.focus();
  	   return false;
  	}
          if (document.forms[0].jmsje.value != "")
          {
			   if (!FN_QSCheckNumberDigit(document.forms[0].jmsje.value,2,"����˰���"))
			   {
			      document.forms[0].jmsje.focus();
			      return false;
			   }
          }

    document.forms[0].nsrlxmc.value = document.forms[0].nsrlxdm.options[document.forms[0].nsrlxdm.selectedIndex].text;
    document.forms[0].jkfsmc.value = document.forms[0].jkfsdm.options[document.forms[0].jkfsdm.selectedIndex].text;
    if (document.forms[0].yhdm.value != "")
    {
       document.forms[0].yhmc.value = document.forms[0].yhdm.options[document.forms[0].yhdm.selectedIndex].text;
    }
  }

	if ((operationType == "Return") && (document.forms[0].sbbh.value != ""))
	{
	   if (document.forms[0].fwjbxxAdded.value !="true")
	   {
	      if (!confirm("�㻹û�����ӷ���Ȩ��ת����Ϣ�������ʱ�˳���������¼��ɾ�����Ƿ��˳���"))
	          return false;
	   }
	}
    <!-- yangxiao 2008-12-06 start -->
    if (operationType == "Shengcheng")
    {
//      alert("����");
      //��λ���㵽ɨ�������¼������
      QRInputFocus();

      //����ɨ�跽��
      str_temp=document.all.piccode.value;
      if(str_temp==""){
        alert("��ɨ�����ݣ�������ɨ�����룡");
        return false;
      }else{
        scanPic();
        return false;
      }
    }
    if (operationType == "Fuwei")
    {
//      alert("��λ");
      //���ø�λ����
      fuwei();
      document.forms[0].operationType.value='Show';
      return false;
    }
    <!-- yangxiao 2008-12-06 end -->
    document.forms[0].submit();
}



function doPrintSbb()
{
    window.open("/qsgl/qssb/printSbxx.do?sbbh="+document.forms[0].sbbh.value,"print","toolbar=no,location=yes,status=no,menubar=yes,scrollbars=yes,resizable=yes,width=640,height=480");
}

</SCRIPT>

<!--add by yangxiao 20081206 start-->
<script language="javascript">
//��ҳ��Ԫ�ظ�λ
function fuwei(){
  //���������
  document.forms[0].jsjdm.value="";
  //��������
  document.forms[0].yhdm.value="";
  //��ϵ�绰
  document.forms[0].lxdh.value="";

  //��λ��ͬ���
  document.forms[0].htbh.readOnly = false;
  document.forms[0].htbh.style.color="";
  //��˰������

  for(i=0;i<document.getElementsByName("nsrmc");i++){
    document.getElementsByName("nsrmc")[i].value="";
  }
  var ele_nsrmc_show=document.getElementById('nsrmc_show');
  if(ele_nsrmc_show.hasChildNodes() != null){
    ele_nsrmc_show.removeChild(ele_nsrmc_show.lastChild);
  }
  //���������õ������������
  document.forms[0].piccode.focus();
}
function initPage(){

    //�ж��Ƿ�Ϊ�����ȡ�Ǽ���Ϣ�󴥷����¼�
    var smbs = document.forms[0].smbs.value;
    if(smbs == "1"){
      <!--add by yangxiao 20090209 start-->
      var smnsrmc = document.forms[0].smnsrmc.value;
      var nsrmc = document.forms[0].nsrmc.value;
      if(nsrmc == ""){
        alert("��˰�˲����ڣ�������ͨ������������ȡ��");
        document.all.imgGetNsrxx.focus();
      }else{
        if (nsrmc != smnsrmc){
          alert("��˰��������ɨ��ȡ�õ���˰�����Ʋ���ͬ������Ǽ���Ϣ��");
          document.all.imgGetNsrxx.focus();
        }
      }

      <!--add by yangxiao 20090209 end-->
      document.forms[0].htbh.readOnly = true;
      document.forms[0].htbh.style.color="#ADADAD";
    }else{
      //alert("���Ի������㣡");
      //���������õ������������
      document.forms[0].piccode.focus();
    }
}
//��λ���㵽ɨ�������¼�����ϣ����ý��㵽�����ı������
function QRInputFocus(){
	//��λ���㵽ɨ�������¼������
	document.all.piccode.focus();
	//���ý��㵽�����ı������
	var e=document.all.piccode;
	var r =e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse(true);
	r.select();
}
	 //ɨ��ǹɨ�����ݺ��Զ����������¼�
	function document.onkeydown(){
       //IE7.0���´������ǻس�
       if(event.keyCode==13){
          scanPic();
         return false;
       }

}
//���ö�̬�⣬ʶ��ͼƬ����ʶ�������������ʾ��ҳ����
function scanPic(){
  //alert(">>>ɨ����Ͽ�ʼ������");
  var istr=document.forms[0].piccode.value;
  var obj=new ActiveXObject("hyQRBarCode.QRCode");
	//alert("obj="+obj);
	var transObj=obj.DeBarCodeString(istr);
	//alert("transObj="+transObj);
	//�ж��Ƿ��ҵ��������
	var transObjArray=transObj.split("^");
	if(transObjArray.length==1&&transObj=="<%=com.creationstar.bjtax.qsgl.util.Constants.QRCODE_DEFAULT_NULL%>"){
		alert("ɨ������������Ϊ�գ����ֹ�¼���걨��");
		return false;
	}

	//����ά�������Ƿ���ȷ��
	//alert("��ʼ����ά��ͷ���ݵĸ�ʽ");
	QRCodeCheckResult=checkQRCodeHeader(transObj);
	//alert("QRCodeCheckResult="+QRCodeCheckResult);
	if(QRCodeCheckResult=="1"){
		alert("ɨ��ͼƬʧ�ܣ�������ɨ����ֹ�¼����Ϣ��");
        fuwei();
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="2"){//������Ĳ�Ϊ"object_"��ͷ���ʾ����ͼƬɨ�費ȫ����ʾ����ɨ��
		alert("��ɨ��ȫ����ά������ͼƬ�������޷����룡");
        fuwei();
		QRInputFocus();
		return false;
	}else if(QRCodeCheckResult=="3"){
      //alert(transObj);
      translate(transObj);//�·�
      translate_xf(transObj);//����Ϊ�·�
		//translate_xinfang(transObj);//�·�
      document.forms[0].smbs.value='1';
	}else if(QRCodeCheckResult=="4"){
      //alert(transObj);
		translate(transObj);//2�ַ�
        translate_esf(transObj);//����Ϊ���ַ�
        document.forms[0].smbs.value='1';
	}
	//���¼���������ý���
	document.all.piccode.value="";
	document.all.piccode.focus();
	return false;
}


//�������ַ�������������ҳ�����
function translate(transObj){
  var retstr=transObj.split("^");
//   alert(retstr[19]);
  var mfxx = retstr[19].split("~");
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
      alert("����ϢΪ���ˣ�������ɨ���ά������ͼƬ�����߶�ά������ͼƬ����");
      doSubmitForm('Fuwei');
      return false;
    }

  //��ͬ���
  document.forms[0].htbh.value=retstr[2];
  document.forms[0].htbh.readOnly = true;
  document.forms[0].htbh.style.color="#ADADAD";
  //��Լǩ��ʱ��
  document.forms[0].time.value=formatDateStr(retstr[3]);
  //���ء����������ַ
  document.forms[0].address.value=retstr[5];
  //���ء�����Ȩ��ת������ ֱ��Ĭ��Ϊ������������(03)
  //document.forms[0].divertType.value=retstr[6];
  document.forms[0].divertType.value="03";
  //���ݽ������
  document.forms[0].area.value=delFuhao(retstr[10]);
  //�������
//  document.forms[0].tenementType.value=fwytJwToDs(retstr[12]);
  //�ɽ��۸�
  document.forms[0].rmbPrice.value=delFuhao(retstr[13]);
}

//����Ϊ���ַ�
function translate_esf(transObj){
  //�Ƿ�Ϊ���ַ�  01:�� 00:��
  document.forms[0].sfesf.value="01";
}
//����Ϊ�·�
function translate_xf(transObj){
  //�Ƿ�Ϊ���ַ�  01:�� 00:��
  document.forms[0].sfesf.value="00";
}
</script>
<!--add by yangxiao 20081206 end-->

<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js"type=text/JavaScript></SCRIPT>
<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>
<BODY bgColor=#ffffff leftMargin=0 onload="initPage();MM_preloadImages(
              '<%=static_file%>imagess/dayin2.jpg',
              '<%=static_file%>imagess/fanhui2.jpg',
              '<%=static_file%>imagess/tuichu2.jpg',
              '<%=static_file%>imagess/diyitiao2.jpg',
              '<%=static_file%>imagess/shangyitiao2.jpg',
              '<%=static_file%>imagess/zuimotiao2.jpg',
              '<%=static_file%>imagess/xinzeng2.jpg',
              '<%=static_file%>imagess/shanchu2.jpg')"
              topMargin=0 marginheight="0" marginwidth="0">

    <jsp:include page="/include/Header.jsp" flush="true">
    <jsp:param name="subtitle" value="��˰�걨>��˰�걨�Ǹ���¼���"/>
    <jsp:param name="helpURL" value=""/>
    </jsp:include>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=770>
  <TBODY>
  <TR>
    <td vAlign=top>
      <table align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <td class=1-td1>�����еط�˰�����˰�걨��-�Ǹ���</td></TR>
        <TR>
          <td class=1-td2 vAlign=top>
        <html:form action="/qssb/addSbFgr.do">
          <bean:define id="sbForm" name="sbFgrForm" type="com.creationstar.bjtax.qsgl.VisionLogic.form.SbFgrForm"/>
          <html:hidden property="operationType" value=""/>
          <html:hidden property="subAction" value=""/>
          <html:hidden property="person" value="false"/>
          <html:hidden property="alert" />
          <html:hidden property="alertMessage" />
          <html:hidden property="xtdqsj"/>
          <html:hidden property="bl"/>
          <!--add by yangxiao 20081206 start-->
          <html:hidden property="smnsrmc"/>
          <html:hidden property="smbs"/>
		  <html:hidden property="sfesf"/>
          <div id="caijiyu" style="display: block;"><BR>�ɼ���:<input type="text" name="piccode" id="piccode" size="70"  maxlength="5000">
          <IMG
          alt=����
          height=22
          id=shengcheng
          name="btnShengcheng"
          onclick="doSubmitForm('Shengcheng');"
          onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('shengcheng','','<%=static_file%>images/shengcheng2.jpg',1)"
          src="<%=static_file%>images/shengcheng1.jpg"
          style="CURSOR: hand"
          width=79>
          <IMG
          alt=��λ
          height=22
          id=fuwei
          name="btnFuwei"
          onclick="doSubmitForm('Fuwei');"
          onmouseout=MM_swapImgRestore()
          onmouseover="MM_swapImage('fuwei','','<%=static_file%>images/fuwei2.jpg',1)"
          src="<%=static_file%>images/fuwei1.jpg"
          style="CURSOR: hand"
          width=79>
          </div>
          <table style="display: none;">
            <tr>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="time"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="address"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="divertType"/>
                </DIV>
              </TD>
              <TD>
                <DIV>
                  <html:text name="sbFgrForm" property="area"/>
                </DIV>
              </TD>
              <TD>
                <DIV align=left>
                  <html:text name="sbFgrForm" property="tenementType"/>
                </DIV>
              </TD>
              <TD>
                <DIV align=left>
                  <html:text name="sbFgrForm" property="rmbPrice"/>
                </DIV>
              </TD>
            </tr>
          </table>
          <!--add by yangxiao 20081206 end-->
          <table border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
			  <BR>

                <TR>

			 <logic:equal name="sbFgrForm" property="bl" value="false">
                            <html:hidden name="sbFgrForm" property="sbbh"/>
			  	<td class=2-td2-t-left width="11%"><DIV align=right>��˰�걨���</DIV></td>

			  	<td class=2-td2-t-center width="47%" colspan=3><DIV align=left><bean:write name="sbFgrForm" property="sbbh" /> &nbsp </DIV></td></td>
				<html:hidden property="sbrq"/>
			</logic:equal>

			<logic:equal name="sbFgrForm" property="bl" value="true">
									    <TD class=2-td2-t-left width="12%">��˰�걨���</TD>
                                        <html:hidden name="sbFgrForm" property="sbbh"/>
                                        <TD class=2-td2-t-center width="43%">
										<DIV align=left>&nbsp<bean:write name="sbFgrForm" property="sbbh" /> </DIV></TD>
                                        <TD class=2-td2-t-left width="22%">�걨����(yyyymmdd)</TD>
                                        <TD class=2-td2-t-center width="23%"><DIV align=left>
										<html:text name="sbFgrForm" property="sbrq" />&nbsp<FONT color=red>*</FONT> </DIV>
			</logic:equal>

			  </TR>
			  <TR>
			  	<td class=2-td2-left>�ɿʽ</td>
			  	<td class=2-td2-left> <DIV align=left>
                                  <bean:define id="data" name="sbFgrForm" property="jkfsList"  />
                                  <html:select property="jkfsdm" >
                                    <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                  </html:select>
                                  <html:hidden property="jkfsmc"/>
				<td class=2-td2-left>�������ع����������</td>
				<td class=2-td2-left><DIV align=left><html:text property="fcjslh" maxlength="30"/></DIV></td>
            </TR>
            <TR>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ίҵ����</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="sbFgrForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶν���-->
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ͬ���</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="sbFgrForm" property="htbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶν���-->

                                          </TR>
			  </TBODY>
			  </TABLE>
            <table border=0 cellSpacing=0 class=tabled-99 width="99%">
              <TBODY>
              <TR>
                <td class=2-td2-t-left width="18%" rowspan = "4">
                  <DIV align=right>�Ǹ�����</DIV>
                   <DIV align=right>д����</DIV>
                  <td class=2-td2-t-left width="15%">
                   <DIV align=right>���������&nbsp;
                   </DIV>
                </td>
                <DIV align=right>&nbsp; </DIV>
                  <td class=2-td2-t-left width="35%">
                    <DIV align=left><html:text property="jsjdm" maxlength="8"/><FONT color=red>*</FONT>
                    <span id="getDJimage1" style="visibility:visible"> <input type="image"  name="imgGetNsrxx" value="��ȡ�Ǽ���Ϣ" alt="��ȡ�Ǽ���Ϣ"
                     onClick="javascript:return doSubmitForm('GetNsrxx');"
                     onMouseOver="MM_swapImage('b-hqdjxx1','','<%=static_file%>images/b-hqdjxx2.jpg',1)"
                     onMouseOut="MM_swapImgRestore()"src="<%=static_file%>images/b-hqdjxx1.jpg"
                     width="79" height="22" id="b-hqdjxx1"></span></DIV></td>
                <td class=2-td2-t-left width="10%">
                  <DIV align=right>��˰������&nbsp; </DIV></td>
                <td colspan="2"  class=2-td2-t-center width="20%">
                  <DIV align=left>
                    <bean:define id="data1" name="sbFgrForm" property="nsrlxList"  />
                    <html:select property="nsrlxdm" >
                      <html:options collection="data1" labelProperty="nsrlxmc" property="nsrlxdm" />
                    </html:select></DIV></td>
                    <html:hidden property="nsrlxmc" />
                  </TR>
             <TR>
                <td  class=2-td2-left >
                  <DIV align=right>��˰������&nbsp;</DIV></td>
                <td colspan="4"  class=2-td2-center width="33%" style="word-break:break-all">
                   <DIV align=left id="nsrmc_show">
                   <bean:write name="sbFgrForm" property="nsrmc"/>
                   <html:hidden property="nsrmc" /> </DIV></td>
                  </TR>
                  <TR>
                 <td class=2-td2-left ;>
                   <DIV align=right>��������&nbsp; </DIV>
                </td>
                <td class=2-td2-center  colspan="4" >
                  <DIV align=left>
                    <bean:define id="yhList" name="sbFgrForm" property="yhList"  />
                    <html:select property="yhdm">
                      <html:options collection="yhList" labelProperty="khyhmc" property="yhdm" />
                    </html:select></DIV></td>
                    <html:hidden property="yhmc" />
                  </TR>

                  <TR>
                 <td class=2-td2-left >
                   <DIV align=right>��ϵ������&nbsp; </DIV>
                </td>

                 <td class=2-td2-left >
                   <DIV align=left><html:text property="lxrxm" maxlength="50"/><FONT color=red>*</FONT> </DIV></td>
                <td   class=2-td2-left width="19%">
                  <DIV align=right>��ϵ�绰&nbsp;</DIV></td>
                <td colspan="2"  class=2-td2-center width="33%">
                   <DIV align=left><html:text property="lxdh" maxlength="20"/><FONT ></FONT> </DIV></td>
                  </TR>
               <TR>
                <td class=2-td2-left  rowspan = "2">
                  <DIV align=right>˰�����</DIV>
                  <DIV align=right>��˼���˰</DIV>
                  <TD class=2-td2-left width="15%";>
                    <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                  </TD>
                  <TD colspan="4" class=2-td2-center width="25%">
                    <DIV align=left><html:text name="sbFgrForm" property="hdtzszh" size="40" maxlength="40"/>
                    <IMG name="query"
				          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
				          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
				          onclick = "doSubmitForm('GetJmsje');"
				          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand"></DIV>
					</TD>
                </TR>
                <TR>
                  <td class=2-td2-left >
                   <DIV align=right>����˰���&nbsp; </DIV>
                </td>
                  <TD colspan="4" class=2-td2-center width="30%">
                    <DIV align=left><html:text name="sbFgrForm" property="jmsje" maxlength="15"/> </DIV>
                  </TD>
                  <html:hidden property="jmlydm"/>
                  </TR>
                 <TR>
                 <td class=2-td2-left >
                   <DIV align=right>��ע&nbsp; </DIV>
                </td>
                <td colspan="5" class=2-td2-center width="84">
                  <DIV align=left><html:textarea property="bz"  cols="45" rows="5"
                  /></DIV></td>
                </TR>
<!-- ��ʾ������Ϣ-->

    <html:hidden property="fwjbxxAdded"/>
<logic:equal name="sbFgrForm" property="fwjbxxAdded" value="true">
<bean:define id="fwtdVo" name="sbFgrForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
  <tr>
    <TD class=2-td2-left width="8%" rowspan = "8">
      <DIV align=right>	���ط���</DIV>
      <DIV align=right>Ȩ��ת��</DIV></TD>
      <TD class=2-td2-left width="15%">
        <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
        <TD colspan="3" class=2-td2-left style="word-break:break-all">
          <DIV align=left><bean:write name="fwtdVo" property="fdcxmmc" />
          </DIV>
      </TD>
      <td width="13%" class=2-td2-center>
          <div align="left">
              <logic:equal name="fwtdVo" property="sfesf" value="00">�Ƕ��ַ�</logic:equal>
              <logic:equal name="fwtdVo" property="sfesf" value="01">���ַ�</logic:equal>
          </div>
      </td>
  </TR>
  <TR>
      <TD class=2-td2-left width="15%";>
          <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
      </TD>
      <TD class=2-td2-left width="19%">
          <DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%> </DIV>
      </TD>
      <TD class=2-td2-left width="19%">
          <DIV align=right>����ԭ��&nbsp;</DIV>
      </TD>
      <TD colspan="2"  class=2-td2-center width="33%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="flmc"/>&nbsp
          </DIV>
      </TD>
  </TR>
  <TR>
      <TD class=2-td2-left width="15%";>
          <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV>
      </TD>
      <TD class=2-td2-left width="19%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="tdfwqszymc"/>
          </DIV>
      </TD>
      <html:hidden property="voTufwxx.tdfwqszylx"/>
      <TD class=2-td2-left width="19%">
          <DIV align=right>�������&nbsp;</DIV>
      </TD>
      <TD colspan="2"  class=2-td2-center width="33%">
          <DIV align=left>
              <bean:write  name="fwtdVo" property="fwlxmc"/>&nbsp;
          </DIV></TD>
      </TR>
      <TR>
          <TD  class=2-td2-left width="25%";>
              <DIV align=right>���ء����������ַ&nbsp;
              </DIV>
          </TD>
          <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
              <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" />
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="25%";>
              <DIV align=right>���ء�����Ȩ��ת�����&nbsp;
              </DIV>
          </TD>
          <TD  class=2-td2-left width="15%">
              <DIV align=left>���أ�<%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj(),3)%> �O
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>���ݽ������&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj(),3)%> �O
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>�ݻ���&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <%if(fwtdVo.getRjl().equals("00"))
                  {
                  %>
                  1.0����
                  <%
                  }
                  if(fwtdVo.getRjl().equals("01"))
                  {
                  %>
                  1.0���Ϻ�1.0
                  <%
                  }
                  %>&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
             <!--�޸����ؼ���Ϊ������������-->
              <DIV align=right>��������&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <%if(fwtdVo.getTdjc().equals("00"))
                  {
                  %>
                  &nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("01"))
                  {
                  %>
                  ��������&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("02"))
                  {
                  %>
                  �������Ļ�֮��&nbsp;
                  <%
                  }
                  %>
                  <%if(fwtdVo.getTdjc().equals("03"))
                  {
                  %>
                  �Ļ����廷֮��&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("04"))
                  {
                  %>
                  �廷����&nbsp;
                  <%
                  }
                  if(fwtdVo.getTdjc().equals("11"))
              	{	
              	%>
                 	�Ļ��ڱ�������&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("12"))
              	{	
              	%>
                 	�Ļ����ϲ�����&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("13"))
              	{	
              	%>
                 	�Ļ����廷��������&nbsp;
                  <% 
              	}
                   if(fwtdVo.getTdjc().equals("14"))
              	{	
              	%>
                 	�Ļ����廷�ϲ�����&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("15"))
              	{	
              	%>
                 	�廷��������������&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("16"))
              	{	
              	%>
                 	�廷�������ϲ�����&nbsp;
                  <% 
              	}
                  if(fwtdVo.getTdjc().equals("17"))
              	{	
              	%>
                 	���������&nbsp;
                <% 
				}
                //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
  				//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
			    if(fwtdVo.getTdjc().equals("21"))
				{	
				%>
			   	5����&nbsp;
			    <% 
				}
			    if(fwtdVo.getTdjc().equals("22"))
				{	
				%>
			   	5-6��&nbsp;
			    <% 
				}
			    if(fwtdVo.getTdjc().equals("23"))
				{	
				%>
			   	6����&nbsp;
			    <% 
				}
			    %>

                  &nbsp;
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>�ɽ��۸������۸�
              </DIV>
          </td>
          <TD  class=2-td2-left width="5%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%> Ԫ(�����)
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>˰����غ˶��۸�&nbsp;
              </DIV>
          </TD>
          <TD  colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%> Ԫ(�����)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%> Ԫ(���)
              </DIV>
          </TD>
          <TD class=2-td2-left width="25%">
              <DIV align=right>
                  ���֣�<bean:write name="fwtdVo" property="bzmc" />
              </DIV>
              <DIV align=right>����:&nbsp;<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%>
              </DIV>
          </TD>
          <TD class=2-td2-center width="17%" colspan="2">
              <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%>
              </DIV>
          </TD>
      </tr>
  </logic:equal>
  <logic:iterate id="cqdata" indexId="index" length="length" name="sbFgrForm" property="cqList" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">
      <TR>
          <TD class=2-td2-left  rowspan = "4">
              <DIV align=right>	��Ǩ
              </DIV>
              <DIV align=right>���
              </DIV>
          </TD>
          <TD class=2-td2-center width="25%" style="word-break:break-all">
              <DIV align=right>����Ǩ���������ַ&nbsp;
              </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center >
              <DIV align=left>
                  &nbsp;<bean:write name = "cqdata" property = "zldz"/>
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right>��ǨЭ�����&nbsp; </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center >
              <DIV align=left>
                  &nbsp;<bean:write name = "cqdata" property = "cqxyh"/>
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right> ��Ǩ������&nbsp; </DIV>
          </TD>
          <TD  class=2-td2-center colspan=4>
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbce())%> Ԫ(�����)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=right>����ʹ�ò�����&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcsybce())%>  Ԫ(�����)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=right>��Ǩ����ʣ���&nbsp;
              </DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> Ԫ(�����)
              </DIV>
          </TD>
      </TR>
  </logic:iterate>
  <logic:iterate id="gydata" indexId="index" length="length" name="sbFgrForm" property="gyList"
      type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "5">
              <DIV align=right>	�ѹ�����ס��
              </DIV>
              <DIV align=right>���г������
              </DIV><br>
          </TD>
          <TD   class=2-td2-left width="25%">
              <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="15%">
              <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>�����ַ&nbsp; </DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="25%" style="word-break:break-all">
              <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV>
          </TD>
      </TR>
      <TR>
          <TD  class=2-td2-left width="15%";>
              ���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="25%">
              <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%>  </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right> �������&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="25%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj(),3)%> �O </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>�ɽ��۸�&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%> Ԫ(�����)</DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%">
              <DIV align=right>���εֿ۶�&nbsp;</DIV>
          </TD>
          <TD  class=2-td2-left width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%> Ԫ(�����)</DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>ʣ���&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%> Ԫ(�����)</DIV>
          </TD>
      </TR>
  </logic:iterate>
  <!-- ��ʾ����������Ϣ-->
  <html:hidden property="fwjhAdded"/>
  <logic:equal name="sbFgrForm" property="fwjhAdded" value="true">
      <bean:define id="fwjhBo" name="sbFgrForm" property="fwjhxxBo" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

      <logic:equal name="fwjhBo" property="jhperson"  value="1">
          <bean:define id="fgrxx" name="sbFgrForm" property="fwjhxxBo.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
      </logic:equal>
      <bean:define id="fwtdxx" name="sbFgrForm" property="fwjhxxBo.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
      <!--TR>
      <TD class=2-td2-left width="12%">�Է��ɿʽ</TD>
      <TD class=2-td2-left width="34%"> <DIV align=left>
          <bean:write name="fwjhBo" property="jkfsmc" />
      </td>
      <TD class=2-td2-left width="22%">�Է��������ع����������</TD>
      <TD colspan="3" class=2-td2-center width="23%"><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV>
      </TD>
      </TR-->
      <!--����������Ϣ��ʾ-->
      <logic:equal name="fwjhBo" property="jhperson"  value="0">

  <TR>
    <TD class=2-td2-left width="70%"  colspan="4"><DIV align=left>�Է�������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  &nbsp; </TD>

</TR>

        <TR>
    <TD  colspan="6" valign="top" width="100%">

<table border="0" cellpadding="0" cellspacing="0" class="table-99" width="100%">
  <tr>
	<td width="20%" class="2-td2-left">
	��˰������
	</td>
    <td width="20%" class="2-td2-left">��ϵ�绰</td>
    <td width="20%" class="2-td2-left">���֤������</td>
    <td width="20%" class="2-td2-left">���֤������</td>
    <td width="10%" class="2-td2-left">����</td>
    <td width="10%" class="2-td2-center">��Ȩ������</td>
  </tr>

 <logic:iterate id="jhnsr" indexId="index" length="length" name="fwjhBo" property="nsrList">
  <tr>
	<td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="nsrmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="lxdh" />&nbsp;</td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjlxmc" /></td>
    <td width="20%" class="2-td2-left"><bean:write name="jhnsr" property="sfzjhm" /></td>
    <td width="10%" class="2-td2-left"><bean:write name="jhnsr" property="gjmc" /></td>
    <td width="10%" class="2-td2-center">
<logic:equal name="jhnsr" property="cqrlx" value="1">
    ���в�Ȩ��
</logic:equal>
<logic:equal name="jhnsr" property="cqrlx" value="0">
    ����Ȩ��
</logic:equal>
    </td>
  </tr>
  </logic:iterate>

</table>
</TD>
</TR>




       </logic:equal>
      <!--�����Ǹ�����Ϣ��ʾ-->
      <logic:equal name="fwjhBo" property="jhperson"  value="1">
          <TR>
              <TD class=2-td2-left width="8%" rowspan = "4">
                  <DIV align=right>�Է��Ǹ���</DIV>
                  <DIV align=right>��д����</DIV>
              </TD>
              <TD class=2-td2-left width="15%">
                  <DIV align=right>���������&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left width="25%">
                  <DIV align=left> <bean:write name="fgrxx" property="jsjdm" />&nbsp</DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>&nbsp; </DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left> &nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>��˰������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" style="word-break:break-all">
                  <DIV align=left>
                      <bean:write name="fgrxx" property="nsrmc" />
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>��˰������&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="nsrlxmc" />&nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>��������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" >
                  <DIV align=left>
                      <bean:write name="fgrxx" property="khyhmc" />&nbsp;
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>�����˺�&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="yhzh" />&nbsp;</DIV>
              </TD>
          </TR>
          <TR>
              <TD class=2-td2-left width="15%";>
                  <DIV align=right>��ϵ������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" >
                  <DIV align=left>
                      <bean:write name="fgrxx" property="lxrxm" />&nbsp;
                  </DIV>
              </TD>
              <TD class=2-td2-left width="19%">
                  <DIV align=right>��ϵ�绰&nbsp;</DIV>
              </TD>
              <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><bean:write name="fgrxx" property="lxdh" />&nbsp</DIV>
              </TD>
          </TR>
      </logic:equal>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "7">
              <DIV align=right>	�������ط���</DIV>
              <DIV align=right>Ȩ��ת��</DIV>
          </TD>
          <TD class=2-td2-left width="15%">
              <DIV align=right>���ز���Ŀ����&nbsp;</DIV>
          </TD>
          <TD colspan="4" class=2-td2-center width="15%" style="word-break:break-all">
              <DIV align=left><bean:write name="fwtdxx" property="fdcxmmc" /></DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left><%=DataConvert.TimeStamp2String(fwtdxx.getHtqdsj())%> </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=right>����&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <bean:write  name="fwtdxx" property="flmc"/>
              </DIV>
          </TD>
      </TR>
      <!--TR>
      <TD class=2-td2-left width="15%";>
          <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <bean:write name="fwtdxx" property="tdfwqszymc" />
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=right>�������&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                  <bean:write name="fwtdxx" property="fwlxmc" />
              </DIV>
          </TD>
      </TR-->
      <TR>
          <TD  class=2-td2-left width="25%";>
              <DIV align=right>���ء����������ַ&nbsp; </DIV>
          </TD>
          <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
              <bean:write name="fwtdxx" property="tdfwzldz" />
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="25%";>
              <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
          </TD>
          <TD  class=2-td2-left width="15%">
              <DIV align=left>���أ�
                  <%=DataConvert.BigDecimal2String(fwtdxx.getTdfwqszymj(),3)%>
                  �O
              </DIV>
          </TD>
          <TD class=2-td2-left width="17%">
              <DIV align=right>���ݽ������&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getFwjzmj(),3)%>
                  �O
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="15%";>
              <DIV align=right>�ݻ���&nbsp; </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <DIV align=left>
                  <%if(fwtdxx.getRjl().equals("00"))
                  {
                  %>
                  1.0����
                  <%
                  }
                  if(fwtdxx.getRjl().equals("01"))
                  {
                  %>
                  1.0���Ϻ�1.0
                  <%
                  }
                  %>&nbsp;
              </DIV>
          </TD>
          <TD class=2-td2-left width="19%">
              <!--�޸����ؼ���Ϊ������������-->
              <DIV align=right>��������&nbsp;</DIV>
          </TD>
          <TD colspan="2"  class=2-td2-center width="33%">
              <DIV align=left>
                   <%if(fwtdxx.getTdjc().equals("00"))
                  {
                  %>
                  &nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("01"))
                  {
                  %>
                  ��������&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("02"))
                  {
                  %>
                  �������Ļ�֮��&nbsp;
                  <%
                  }
                  %>
                  <%if(fwtdxx.getTdjc().equals("03"))
                  {
                  %>
                  �Ļ����廷֮��&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("04"))
                  {
                  %>
                  �廷����&nbsp;
                  <%
                  }
                  if(fwtdxx.getTdjc().equals("11"))
				{	
				%>
			   	�Ļ��ڱ�������&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("12"))
				{	
				%>
			   	�Ļ����ϲ�����&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("13"))
				{	
				%>
			   	�Ļ����廷��������&nbsp;
			    <% 
				}
			     if(fwtdxx.getTdjc().equals("14"))
				{	
				%>
			   	�Ļ����廷�ϲ�����&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("15"))
				{	
				%>
			   	�廷��������������&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("16"))
				{	
				%>
			   	�廷�������ϲ�����&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("17"))
				{	
				%>
			   	���������&nbsp;
			    <% 
				}
			    //����2014��10�±�����ͨס����׼���������������Ϊ�±�׼��ÿƽ�׼۸����ޡ�ÿ�׷��ݼ۸������������������ ��
				//�˴�ֻ�޸�����������ʾ��modified by gaoyh to 20141020
			    if(fwtdxx.getTdjc().equals("21"))
				{	
				%>
			   	5����&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("22"))
				{	
				%>
			   	5-6��&nbsp;
			    <% 
				}
			    if(fwtdxx.getTdjc().equals("23"))
				{	
				%>
			   	6����&nbsp;
			    <% 
				}
			    %>

                  &nbsp;
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left width="8%" rowspan = "2">
              <DIV align=right>�ɽ��۸������۸�</DIV>
          </td>
          <TD  class=2-td2-left width="5%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgrmb())%>
                  Ԫ(�����)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=right>˰����غ˶��۸�&nbsp; </DIV>
          </TD>
          <TD  colspan="2"  class=2-td2-center width="32%">
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getPgjgrmb())%>
                  Ԫ(�����)
              </DIV>
          </TD>
      </TR>
      <TR>
          <TD class=2-td2-left >
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgwb())%>
                  Ԫ(���)
              </DIV>
          </TD>
          <TD class=2-td2-left >
              <DIV align=left>
                  ���֣�<bean:write name="fwtdxx" property="bzmc" />
              </DIV>
              <DIV align=left>
                  ����:&nbsp;<%=DataConvert.BigDecimal2String(fwtdxx.getHldm(),5)%>
              </DIV>
          </TD>
          <TD class=2-td2-center  colspan="2" >
              <DIV align=left>
                  <%=DataConvert.BigDecimal2String(fwtdxx.getZhjgrmb())%>
                  �ۺ�Ԫ(�����)
              </DIV>
          </TD>
      </TR>
  </logic:equal>
</TBODY>
</TABLE>
<BR>

            <DIV align=center>

			<%
			  if ((sbForm.getSbbh() != null) && (!sbForm.getSbbh().equals("")))
			  {

              if (!sbForm.isFwjbxxAdded())
              {
			%>
            <IMG alt=¼�뷿�ݻ�����Ϣ id=Fwjbxx name=Submit51
            onclick="doSubmitForm('Fwjbxx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Fwjbxx','','<%=static_file%>images/q_lrtdfwjbxx2.jpg',1)"
            src="<%=static_file%>images/q_lrtdfwjbxx1.jpg" style="CURSOR: hand">

            <%
              }

              if (sbForm.isFwjbxxAdded())

              {
            %>

              <!--modified by zhaobo 20041218
            <img alt=¼���Ǩ��� id=Cqxx name=Submit53
            onclick="doSubmitForm('Cqxx')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Cqxx','','<%=static_file%>images/q_lrcq2.jpg',1)"
            src="<%=static_file%>images/q_lrcq1.jpg" style="CURSOR: hand">
							//end modified

             <img alt=¼���ѹ�����ס��������� id=Gyzf name=Submit43
            onclick="doSubmitForm('Gyzf')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Gyzf','','<%=static_file%>images/q_lryggyzfssqk2.jpg',1)"
            src="<%=static_file%>images/q_lryggyzfssqk1.jpg" style="CURSOR: hand"-->

            <%
              if (sbForm.getVoTufwxx().getTdfwqszylx().equals("05")) //����Ƿ��ݽ���
              {
				  if (!sbForm.isFwjhAdded())
				  {
            %>
            <IMG alt=¼�뷿�ݽ�����Ϣ  id=Fwjhxx name=Submit33
            onclick="doSubmitForm('Fwjhxx');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Fwjhxx','','<%=static_file%>images/q_lrfwjhxx2.jpg',1)"
            src="<%=static_file%>images/q_lrfwjhxx1.jpg" style="CURSOR: hand" >
			<%
			      }
			  }//end if (sbForm.getVoTufwxx().getTdfwqszylx().equals("05"))
			%>
             <img alt=��ӡ   id=Print name=Submit33
            onclick="doPrintSbb()" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('Print','','<%=static_file%>images/q_dysbb2.jpg',1)"
            src="<%=static_file%>images/q_dysqb1.jpg" style="CURSOR: hand">
              <br><br>

            <%
             } //end if (sbForm.isFwjbxxAdded())
             }
             else  //�걨���Ϊ��
             {
            %>
            <IMG alt=���� height=22 id=baocun name=Submit63
            onclick="doSubmitForm('Save');" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('baocun','','<%=static_file%>images/baocun2.jpg',1)"
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <%
            }
            %>

            <IMG alt=�˳� height=22 id=tuichu name=tuichu
            onclick="doSubmitForm('Return')" onmouseout=MM_swapImgRestore()
            onmouseover="MM_swapImage('tuichu','','<%=static_file%>images/tuichu2.jpg',1)"
            src="<%=static_file%>images/tuichu1.jpg" style="CURSOR: hand" width=79>
            </DIV><BR>
</html:form>




<script language="javascript" type='text/JavaScript'>
if (document.forms[0].sbbh.value == "")
{

	document.forms[0].jkfsdm.disabled = false;
	document.forms[0].fcjslh.disabled = false;
	document.forms[0].lxdh.disabled = false;
	document.forms[0].bz.disabled = false;
  document.forms[0].jsjdm.disabled = false;
  document.forms[0].lxdh.disabled = false;
  document.forms[0].lxrxm.disabled = false;
  document.forms[0].nsrlxdm.disabled = false;
	document.forms[0].hdtzszh.disabled = false;
	document.forms[0].jmsje.disabled = false;
	document.forms[0].sbrq.disabled = false;
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = false;
	document.forms[0].htbh.disabled = false;
    document.all.caijiyu.style.display = "block";
    <!-- yangxiao 2008-12-06 end -->
}
else
{

	document.forms[0].jkfsdm.disabled = true;
	document.forms[0].fcjslh.disabled = true;
	document.forms[0].lxdh.disabled = true;
	document.forms[0].bz.disabled = true;
  document.forms[0].jsjdm.disabled = true;
  document.forms[0].lxrxm.disabled = true;
  document.forms[0].nsrlxdm.disabled = true;
  document.forms[0].yhdm.disabled = true;
	document.forms[0].hdtzszh.disabled = true;
	document.forms[0].jmsje.disabled = true;
	document.forms[0].chaxun1.disabled = true;
document.forms[0].sbrq.disabled = true;
    getDJimage1.style.visibility = "hidden";
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = true;
	document.forms[0].htbh.disabled = true;
    document.all.caijiyu.style.display = "none";
    <!-- yangxiao 2008-12-06 end -->

}

if (document.forms[0].alert.value == "true")
{
    alert(document.forms[0].alertMessage.value);
    document.forms[0].alertMessage.value = "";
}

</script>


<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

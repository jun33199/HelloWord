<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.DataConvert"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm"%>
<!-- yangxiao 2008-12-06 start -->
<%@include file="../include/QRCodeHeader.jsp"%>
<!-- yangxiao 2008-12-06 end -->
<%
	SbGrForm sbGrForm = (SbGrForm)session.getAttribute("sbGrForm");

%>

<HTML><HEAD><TITLE>��˰�걨����¼���</TITLE>
  <META content="text/html; charset=gb2312" http-equiv=Content-Type>
    <SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
    <SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
    <script language="javascript" src="../js/qscommon.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>

<script LANGUAGE="javascript" src="../js/Const.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn.js"></script>
<script LANGUAGE="javascript" src="../js/DynamicTable.js"></script>
<script LANGUAGE="javascript" src="../js/DataAwareTable.js"></script>
<script LANGUAGE="javascript" src="../js/DTColumn_Check.js"></script>
    <LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
    </HEAD>

    <script language=JavaScript type='text/JavaScript'>
      function doSubmitForm(operationType)
      {
        document.all.operationType.value=operationType;
        if(operationType=="Fwjbxx" ){
          document.forms[0].operationType.value='Redirect';
          document.forms[0].subAction.value='fwjbxx';
        }else if(operationType=="Cqxx"){
          document.forms[0].operationType.value='Redirect';
          document.forms[0].subAction.value='cqxx';
        }else if(operationType=="Gyzf"){
          document.forms[0].operationType.value='Redirect';
          document.forms[0].subAction.value='gyzf';
        }else if(operationType=="Fwjhxx"){
          document.forms[0].operationType.value='Redirect';
          document.forms[0].subAction.value='fwjhxx';
        }else if(operationType=="Save"){
          if (document.forms[0].sbbh.value != "")
          {
            alert("�걨�����¼�Ѿ����ɣ����ܱ��棬������޸ģ�����޸Ĺ����޸ġ�");
            return false;
          }

          document.forms[0].jkfsmc.value = document.forms[0].jkfsdm.options[document.forms[0].jkfsdm.selectedIndex].text;

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

       var checknsr=checkNsr();
       if (checknsr==false){
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
        }

        if (operationType == "Return")
        {
           if ((document.forms[0].fwjbxxAdded.value !="true") && (document.forms[0].sbbh.value != ""))
           {
              if (!confirm("�㻹û�����ӷ���Ȩ��ת����Ϣ�������ʱ�˳���������¼��ɾ�����Ƿ��˳���"))
                  return false;
           }
        }
        <!-- yangxiao 2008-12-06 start -->
        if (operationType == "Shengcheng")
        {
//           alert("����");
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

//           alert("��λ");
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

function checkNsr()
{
	daList.saveAllData();
    //���б�ʽ���ݽ��м��

	isCheck = DataSourceCheck(daList);
    if(isCheck == false){
        return false;
    }
    //��textArea�е��ִܷŵ�hidden����
    document.forms[0].dataSource_gm.value=daList.formatData(constRowSeparator,constColSeparator);
    //alert(document.forms[0].dataSource_gm.value);
    return true;
	//ת����һ��ҳ��
}


      </SCRIPT>


<SCRIPT LANGUAGE=javascript>
//��Ȩ������
<%=sbGrForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(sbGrForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // ֤��
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(sbGrForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//����

    function addRow()
    {
		if(document.forms[0].qrScanSbFlag.value=="1"){
			alert("ɨ�����������޸ģ�");
			return false;
		}
            daList.appendRow();
	        daList.focusAt(daList.getCurrentRow(),1);
            var zjValue=daList.getCellAt(daList.getCurrentRow(),3);
            zjValue.value="02";
            var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
            gjValue.value="CHN";
    }


    function modify()
    {
           daList.modifyRow(daList.DynamicTable.CurrentRow);
    }

    function deleteRow(numindex)
    {
		if(document.forms[0].qrScanSbFlag.value=="1"){
			alert("ɨ�����������޸ģ�");
			return false;
		}
	       daList.saveAllData();
      var dataSource_del=daList.getData();
        var numRow_del=dataSource_del.length;
        if (numRow_del ==0 ){
            return;
        }
 		if  (numRow_del ==1){
		   alert ("��Ȩ�˲���Ϊ�գ�");
		   return;
		}

		daList.moveto(numindex);
        var flagConfirm=confirm("ȷ��ɾ����ǰ��¼��");
        if(flagConfirm==true){
           daList.deleteRow(daList.DynamicTable.CurrentRow);
        }
    }


	function initPage()
	{
        //���* ��˰������* ��ϵ�绰* ���֤������* ���֤������* ����* �Ƿ�����Ȩ��*
        var aryColumn = new Array();
        aryColumn[aryColumn.length] = new DTColumn(constCTSequence,"xh",2,null);
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"nsrmc",200,null,"","��˰������",false,true,"200");
        aryColumn[aryColumn.length] = new DTColumn(constCTInput,"lxdh",100,null,"","��ϵ�绰",false,false,"20");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"sfzjlxdm",100,arySelect_zjlx,"need_autoselect","���֤������",false,true,"2");
         aryColumn[aryColumn.length] = new DTColumn(constCTInput,"sfzjhm",150,null,"","���֤������",false,true,"30");
        aryColumn[aryColumn.length] = new DTColumn(constCTSelect,"gjdm",100,arySelect_gj,"need_autoselect","����",false,true,"3");
        aryColumn[aryColumn.length] = new DTColumn(constCTRadioButton,"zcqr",50,null,"maxlength=1","����Ȩ��",false,false,"1");
        aryColumn[aryColumn.length] = new DTColumn(constCTDelButton,"delIndex",10,null,"","ɾ��",false,false,"20");

        dtList = new DynamicTable(TABLE_LIST,aryColumn);
        if (dtList == null)  return;
        daList = new DataAwareTable(dtList);

        daList.setDataSource(aryDataSource);

if (document.forms[0].sbbh.value == "")
{

	document.forms[0].jkfsdm.disabled = false;
	document.forms[0].fcjslh.disabled = false;
    if (daList != null)  daList.disableAll(false);

    document.forms[0].query.disabled = false;
	document.forms[0].query.disabled = false;
	document.forms[0].bz.disabled = false;
	document.forms[0].hdtzszh.disabled = false;
	document.forms[0].jmsje.disabled = false;
	document.forms[0].sbrq.disabled = false;
	document.forms[0].query.disabled = false;
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = false;
	document.forms[0].htbh.disabled = false;
    document.all.caijiyu.style.display = "block";
    <!-- yangxiao 2008-12-06 end -->
    
    document.forms[0].queryMfxx.disabled = false;
}
else
{
	document.forms[0].jkfsdm.disabled = true;
	document.forms[0].fcjslh.disabled = true;
    if (daList != null)  daList.disableAll(true);
	document.forms[0].query.disabled = true;
	document.forms[0].bz.disabled = true;
	document.forms[0].hdtzszh.disabled = true;
	document.forms[0].jmsje.disabled = true;
	document.forms[0].chaxun1.disabled = true;
	document.forms[0].sbrq.disabled = true;
	document.forms[0].query.disabled = true;
    <!-- yangxiao 2008-12-06 start -->
    document.forms[0].jwywbh.disabled = true;
	document.forms[0].htbh.disabled = true;
    document.all.caijiyu.style.display = "none";
    <!-- yangxiao 2008-12-06 end -->
    document.forms[0].queryMfxx.disabled=true;
}
<!-- yangxiao 2008-12-06 start -->
//�ж��Ƿ�Ϊ�����ȡ�Ǽ���Ϣ�󴥷����¼�
var smbs = document.forms[0].smbs.value;
if(smbs != "1"){
  //���������õ������������
  document.forms[0].piccode.focus();
}

var buyyerInfo = document.forms[0].all_buyerInfo.value;
if(buyyerInfo!=null && buyyerInfo.length>0){
	parseSaveBuyorSellInfo(buyyerInfo);
}
document.forms[0].all_buyerInfo.value="";
<!-- yangxiao 2008-12-06 end -->
	}

function parseSaveBuyorSellInfo(buyyerInfo){
  
 
	var infoArr = buyyerInfo.split("^");		  
	for(var index =1; index <= infoArr.length;index ++){
		//alert(infoArr.length);
		var tempInfo = infoArr[index-1];
		//alert(tempInfo);
		if(index>1){
	        addRow();
	    }
		var mfxx = tempInfo.split("~");
		var mfxxArrayLength=mfxx.length;
  		var mfxxCount=mfxxArrayLength/6;
		for(var i=1;i<=mfxxCount;i++){
	      daList.focusAt(daList.getCurrentRow(),1);
	      //��˰������
	      var mcValue=daList.getCellAt(daList.getCurrentRow(),1);
	      mcValue.value=mfxx[0+(6*(i-1))];
	      //this.TABLE_LIST.rows(i).cells(1).firstChild.readOnly = true;
	      //this.TABLE_LIST.rows(i).cells(1).firstChild.style.color="#ADADAD";
	      //�绰
	      var dhValue=daList.getCellAt(daList.getCurrentRow(),2);
	      dhValue.value=mfxx[1+(6*(i-1))];
	      //֤������
	      var zjlxValue=daList.getCellAt(daList.getCurrentRow(),3);
	      zjlxValue.value=mfxx[2+(6*(i-1))];
	      //this.TABLE_LIST.rows(i).cells(3).firstChild.disabled = true;
	      //֤������
	      var zjhmValue=daList.getCellAt(daList.getCurrentRow(),4);
	      zjhmValue.value=mfxx[3+(6*(i-1))];
	      //this.TABLE_LIST.rows(i).cells(4).firstChild.readOnly = true;
	      //this.TABLE_LIST.rows(i).cells(4).firstChild.style.color="#ADADAD";
		}
	}
 
}
    function checkSpecial(dataSource)
    {
		var aryResult=[-1,-1];

        var numRow=dataSource.length;
		var j=100;
        if(numRow>0){
            for(var i=0;i<numRow;i++)
            {

			if ((dataSource[i][3]=="02") && (dataSource[i][5]=="CHN") )
          {
            if (!checkIdentityCard(dataSource[i][4]))
            {
              alert("���֤�����ʽ���ԣ�");
		      aryResult=[0,4];
		      return aryResult;
            }
          }
				if(dataSource[i][6]==constTrue)
                {
                    j=6;
                }
            }
        }

        if(j==100)
        {
            alert("��ѡ������Ȩ�ˣ�");
            aryResult=[0,6];
		    return aryResult;
        }
		return aryResult;
	}

    function checkJBXX(dataSource)
    {
    }

</SCRIPT>

<!--add by yangxiao 20081206 start-->
<script language="javascript">
//��ҳ��Ԫ�ظ�λ
function fuwei(){
	//
	document.forms[0].qrScanSbFlag.value="0";
	//
  var daListRow = daList.getCurrentRow();
  //           alert("��̬�� ROW =="+daListRow);
  //         ɾ����̬��
  if(daListRow>1){
    for(;daListRow>1;daListRow--){
      daList.moveto(daListRow);
      daList.deleteRow(daListRow);
      //               alert("��1�Ժ�=="+daListRow);
    }
  }
  document.forms[0].reset();
  //��λ��ͬ���
  document.forms[0].htbh.readOnly = false;
  document.forms[0].htbh.style.color="";

  daList.focusAt(daList.getCurrentRow(),1);
  this.TABLE_LIST.rows(1).cells(1).firstChild.readOnly = false;
  this.TABLE_LIST.rows(1).cells(1).firstChild.style.color="";
  this.TABLE_LIST.rows(1).cells(3).firstChild.disabled = false;
  this.TABLE_LIST.rows(1).cells(4).firstChild.readOnly = false;
  this.TABLE_LIST.rows(1).cells(4).firstChild.style.color="";
  var zjValue=daList.getCellAt(daList.getCurrentRow(),3);
  zjValue.value="02";
  var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
  gjValue.value="CHN";
  this.TABLE_LIST.rows(1).cells(6).firstChild.checked = true;
  //���������õ������������
  document.forms[0].piccode.focus();
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
	//���ñ�־λ
	document.forms[0].qrScanSbFlag.value="1";
	return false;
}


//�������ַ�������������ҳ�����
function translate(transObj){
  var retstr=transObj.split("^");
  var mfxx = retstr[19].split("~");
  var mfxxArrayLength=mfxx.length;
  var mfxxCount=mfxxArrayLength/6;
  for(i=1;i<=mfxxCount;i++){
    if(mfxx[1] == "1"){
      if(i>1){
        addRow();
      }
      daList.focusAt(daList.getCurrentRow(),1);
      //��˰������
      var mcValue=daList.getCellAt(daList.getCurrentRow(),1);
      mcValue.value=mfxx[0+(6*(i-1))];
      this.TABLE_LIST.rows(i).cells(1).firstChild.readOnly = true;
      this.TABLE_LIST.rows(i).cells(1).firstChild.style.color="#ADADAD";
      //�绰
      var dhValue=daList.getCellAt(daList.getCurrentRow(),2);
      dhValue.value=mfxx[5+(6*(i-1))];
      //֤������
      var zjlxValue=daList.getCellAt(daList.getCurrentRow(),3);
      zjlxValue.value=zjlxJwToDs(mfxx[2+(6*(i-1))]);
      this.TABLE_LIST.rows(i).cells(3).firstChild.disabled = true;
      //֤������
      var zjhmValue=daList.getCellAt(daList.getCurrentRow(),4);
      zjhmValue.value=mfxx[3+(6*(i-1))];
      this.TABLE_LIST.rows(i).cells(4).firstChild.readOnly = true;
      this.TABLE_LIST.rows(i).cells(4).firstChild.style.color="#ADADAD";
      //����
//      var gjValue=daList.getCellAt(daList.getCurrentRow(),5);
//      gjValue.value=mfxx[i];
      //��Ȩ������
      //var cqrValue=daList.getCellAt(daList.getCurrentRow(),6);
      //cqrValue.value=mfxx[5];
    }else{
      alert("����ϢΪ�Ǹ��ˣ�������ɨ���ά������ͼƬ�����߶�ά������ͼƬ����");
      doSubmitForm('Fuwei');
      return false;
    }
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
  //document.forms[0].tenementType.value=fwytJwToDs(retstr[12]);
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

      <BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0"  onLoad="initPage();" >
        <jsp:include page="/include/Header.jsp" flush="true">
          <jsp:param name="subtitle" value="��˰�걨>��˰�걨����¼���"/>
            <jsp:param name="helpURL" value=""/>
          </jsp:include>


										</TD>
                                      </TR>
  </TBODY>
</TABLE>

<TABLE align=center border=0 cellPadding=0 cellSpacing=0  width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

          <br>
            <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=98%>
              <TBODY>
                <TR>
                  <TD vAlign=top>

                    <TABLE align=center cellSpacing=0 class=table-99>
                      <TBODY>
                        <TR>
                          <TD class=1-td1>�����еط�˰�����˰�걨��-����</TD></TR>
                          <TR>
                            <TD class=1-td2 vAlign=top>
                              <html:form action="/qssb/addSbGr.do">
                                <!--bean:define id="sbForm" name="sbGrForm" type="com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm"/-->
                                <html:hidden property="operationType" value=""/>
                                <html:hidden property="subAction" value=""/>
                                <html:hidden property="person" value="true"/>
                                <html:hidden property="alert" />
                                <html:hidden property="alertMessage" />
								<html:hidden property="xtdqsj"/>
								<html:hidden property="bl"/>
								<html:hidden property="all_buyerInfo"/>
                                <input type="hidden" name="dataSource_gm" value="">
                                <!--add by yangxiao 20081206 start-->
                                <html:hidden property="smbs"/>
								<html:hidden property="sfesf"/>
								<input type="hidden" name="qrScanSbFlag" value="0">
                                <div id="caijiyu" style="display: block;"><BR>�ɼ���:<input type="text" name="piccode" id="piccode" size="80"  maxlength="5000">
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
                                <!--add by yangxiao 20081206 end-->
                                <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
                                  <TBODY>
                                    <BR>
                                      <TR>
									  <logic:equal name="sbGrForm" property="bl" value="false">
                                        <TD class=2-td2-t-left width="12%">��˰�걨���</TD>
                                        <html:hidden name="sbGrForm" property="sbbh"/>

                                        <TD class=2-td2-t-center width="43%" colspan=3>
                                            <DIV align=left>&nbsp<bean:write name="sbGrForm" property="sbbh" /> </DIV></TD>
										<html:hidden property="sbrq"/>
									  </logic:equal>

									<logic:equal name="sbGrForm" property="bl" value="true">
									    <TD class=2-td2-t-left width="12%">��˰�걨���</TD>
                                        <html:hidden name="sbGrForm" property="sbbh"/>
                                        <TD class=2-td2-t-center width="43%">
										<DIV align=left>&nbsp<bean:write name="sbGrForm" property="sbbh" /> </DIV></TD>
                                        <TD class=2-td2-t-left width="22%">�걨����(yyyymmdd) </TD>
                                        <TD class=2-td2-t-center width="23%"><DIV align=left>
										<html:text name="sbGrForm" property="sbrq" />&nbsp<FONT color=red>*</FONT></DIV>
									</logic:equal>

										</TD>
                                      </TR>
                                      <TR>
                                        <TD class=2-td2-left>�ɿʽ</TD>
                                        <TD class=2-td2-left > <DIV align=left>
                                          <bean:define id="data" name="sbGrForm" property="jkfsList"  />
                                          <html:select property="jkfsdm" >
                                            <html:options collection="data" labelProperty="jsfsmc" property="jsfsdm" />
                                          </html:select>
                                          <html:hidden property="jkfsmc"/>
                                          <TD class=2-td2-left>�������ع����������</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="sbGrForm" property="fcjslh" maxlength="30"/>  </DIV></TD>

                                          </TR>

                                          <TR>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ίҵ����</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="sbGrForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶν���-->
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ͬ���</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="sbGrForm" property="htbh" maxlength="30"/>  
                                            
                                             <!-- 20140708 add by wangcy,��Ӳ�ѯ��ť -->
                                           	 <IMG name="queryMfxx"
											          onMouseOver="MM_swapImage('queryMfxx','','<%=static_file%>images/chaxun2.jpg',1)"
											          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
											          onclick = "doSubmitForm('Query');"
											          width="79" height="22" id="queryMfxx" alt="��ѯ" style="cursor:hand">
											  </DIV>
											</TD>
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶν���-->
											
                                          </TR>
                                        </TBODY>
                                      </TABLE>

        <TABLE border=0 cellSpacing=0 class=tabled-99 width="99%">
         <TBODY>    <TR>
    <TD class=2-td2-left colspan="4" width="70%" ><DIV align=left>&nbsp;&nbsp;������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  <DIV align=right>
    <%  if ((sbGrForm.getSbbh() == null) || (sbGrForm.getSbbh().equals("")))  { %>
    <img onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()"  value="����" id="Submit522"  name="addnsr" src="<%=static_file%>/images/xinzeng1.jpg" width="79" onClick="addRow()" height="22">
    <%    }    %>
&nbsp;&nbsp;
</DIV> </TD>

</TR>
         <TR>
    <TD colspan="6" valign="top">

<table id="TABLE_LIST" border="0" cellpadding="0" cellspacing="0" class="table-99">
  <tr>
    <td width="2%" class="2-td1-left" >���</td>
	<td width="25%" class="2-td1-left">��˰������<span class="bitian">*</span></td>
    <td width="13%" class="2-td1-left">��ϵ�绰</td>
    <td width="13%" class="2-td1-left">���֤������<span class="bitian">*</span></td>
    <td width="20%" class="2-td1-left">���֤������<span class="bitian">*</span></td>
    <td width="5%" class="2-td1-left">����<span class="bitian">*</span></td>
    <td width="7%" class="2-td1-left">�Ƿ�����Ȩ��<span class="bitian">*</span></td>
    <td width="5%" class="2-td1-center">&nbsp;&nbsp;</td>
  </tr>
</table>
<!--add by yangxiao 20081206 start-->
<table id="" border="0" cellpadding="0" cellspacing="0" class="table-99" style="display: none;">
  <tr>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="time"/>
      </DIV>
    </TD>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="address"/>
      </DIV>
    </TD>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="divertType"/>
      </DIV>
    </TD>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="area"/>
      </DIV>
    </TD>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="tenementType"/>
      </DIV>
    </TD>
    <TD>
      <DIV align=left>
        <html:text name="sbGrForm" property="rmbPrice"/>
      </DIV>
    </TD>
  </tr>
</table>
<!--add by yangxiao 20081206 end-->
</TD>
</TR>

                                          <TR>
                                            <TD class=2-td2-left width="15%" rowspan = "2">
                                              <DIV align=right>˰�����</DIV>
                                              <DIV align=right>��˼���˰</DIV>

                                              <TD class=2-td2-left width="15%";>
                                                <DIV align=right>����˰�����ֺ�&nbsp; </DIV>
                                              </TD>
                                              <TD colspan="4" class=2-td2-center width="25%">
                                                <DIV align=left><html:text name="sbGrForm" property="hdtzszh" size="40" maxlength="40"/>
                                                <IMG name="query"
											          onMouseOver="MM_swapImage('chaxun1','','<%=static_file%>images/chaxun2.jpg',1)"
											          onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/chaxun1.jpg"
											          onclick = "doSubmitForm('GetJmsje');"
											          width="79" height="22" id="chaxun1" alt="��ѯ" style="cursor:hand"></DIV>
												</TD>
                                            </TR>
                                            <TR>
                                              <TD class=2-td2-left width="15%">
                                                <DIV align=right>����˰���&nbsp; </DIV>
                                              </TD>
                                              <TD colspan="4" class=2-td2-center width="30%">
                                                <DIV align=left><html:text name="sbGrForm" property="jmsje" maxlength="15"/> </DIV>
                                              </TD>
                                              <html:hidden property="jmlydm"/>
                                              </TR>
                                              <TR>
                                                <TD class=2-td2-left width="15%";>
                                                  <DIV align=right>��ע&nbsp; </DIV>
                                                </TD>
                                                <TD colspan="5" class=2-td2-center width="84">
                                                  <DIV align=left><html:textarea name="sbGrForm" property="bz" rows="4" cols="30"/></DIV></TD>
                                                </TR>

	                                            <!-- ��ʾ������Ϣ-->

	                                            <html:hidden property="fwjbxxAdded"/>
	                                            <logic:equal name="sbGrForm" property="fwjbxxAdded" value="true">
	                                              <bean:define id="fwtdVo" name="sbGrForm" property="voTufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
	                                              <tr>
                                                      <TD class=2-td2-left width="8%" rowspan = "8">
                                                          <DIV align=right>	���ط���</DIV>
                                                          <DIV align=right>Ȩ��ת��</DIV>
                                                      </TD>
	                                                  <TD class=2-td2-left width="15%">
                                                          <DIV align=right>���ز���Ŀ����&nbsp;
                                                          </DIV>
                                                      </TD>
                                                      <TD colspan="3" class=2-td2-left style="word-break:break-all">
                                                          <DIV align=left>
                                                              <bean:write name="fwtdVo" property="fdcxmmc" />
                                                          </DIV>
                                                      </TD>
                                                      <td width="13%" class=2-td2-center>
                                                          <logic:equal name="fwtdVo" property="sfesf" value="00">�Ƕ��ַ�</logic:equal>
                                                          <logic:equal name="fwtdVo" property="sfesf" value="01">���ַ�</logic:equal>
                                                      </td>
	                                                    </TR>
                                                <TR>
                                                  <TD class=2-td2-left width="15%";>
                                                    <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
                                                  </TD>
                                                  <TD class=2-td2-left width="19%">
                                                    <DIV align=left><%=DataConvert.TimeStamp2String(fwtdVo.getHtqdsj())%> </DIV></TD>
                                                    <TD class=2-td2-left width="19%">
                                                      <DIV align=right>����ԭ��&nbsp;</DIV></TD>
                                                      <TD colspan="2"  class=2-td2-center width="33%">
                                                        <DIV align=left>
                                                          <bean:write  name="fwtdVo" property="flmc"/>&nbsp
                                                        </DIV></TD>
                                                      </TR>
                                                  <TR>
                                                    <TD class=2-td2-left width="15%";>
                                                      <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
                                                      <TD class=2-td2-left width="19%">
                                                        <DIV align=left>
                                                          <bean:write  name="fwtdVo" property="tdfwqszymc"/></DIV></TD>
                                                          <TD class=2-td2-left width="19%">
                                                            <DIV align=right>�������&nbsp;</DIV></TD>
                                                            <TD colspan="2"  class=2-td2-center width="33%">
                                                              <DIV align=left>
                                                                <!-- ���������������ʾ�������� -->
                                                                <logic:equal name="fwtdVo" property="tdfwqszylx" value="03">
                                                                   <bean:write  name="fwtdVo" property="fwlxmc"/>
                                                                </logic:equal>
                                                                <logic:equal name="fwtdVo" property="tdfwqszylx" value="05">
                                                                   <bean:write  name="fwtdVo" property="fwlxmc"/>
                                                                </logic:equal>
																&nbsp;</DIV></TD>
                                                              </TR>
                                                  <TR>
                                                    <TD  class=2-td2-left width="25%";>
                                                      <DIV align=right>���ء����������ַ&nbsp; </DIV>
                                                    </TD>
                                                    <TD colspan="4"  class=2-td2-center width="15%" style="word-break:break-all">
                                                      <DIV align=left><bean:write name="fwtdVo" property="tdfwzldz" /></DIV></TD>
                                                    </TR>


                                                <TR>
                                                  <TD class=2-td2-left width="25%";>
                                                    <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                                                  </TD>
                                                  <TD  class=2-td2-left width="15%">
                                                      <!--�޸����Ϊ������λС����ʼ��modify by fujx��20081127-->
                                                    <DIV align=left>���أ�<%=DataConvert.BigDecimal2String(fwtdVo.getTdfwqszymj(),3)%> �O </DIV></TD>
                                                    <TD class=2-td2-left width="17%">
                                                      <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                                                      <TD colspan="2"  class=2-td2-center width="32%">
                                                        <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getFwjzmj(),3)%> �O</DIV></TD>
                                                        <!--�޸����Ϊ������λС��������modify by fujx��20081127-->
                                                      </TR>

                                                  <TR>
                                                    <TD class=2-td2-left width="15%";>
                                                      <DIV align=right>�ݻ���&nbsp; </DIV> </TD>
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
	%>&nbsp;</DIV></TD>
                                                          <TD class=2-td2-left width="19%">
                                                            <DIV align=right>��������&nbsp;</DIV></TD>
                                                            <TD colspan="2"  class=2-td2-center width="33%">
                                                              <DIV align=left>
                                                                <!-- ���������������ʾ�������� -->
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


																&nbsp;</DIV></TD>
                                                              </TR>



                                              <TR>
                                                <TD class=2-td2-left width="8%" rowspan = "2">
                                                  <DIV align=right>�ɽ��۸������۸�</DIV>
                                                  <TD  class=2-td2-left width="5%">
                                                    <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgrmb())%> Ԫ(�����) </DIV></TD>
                                                    <TD class=2-td2-left width="17%">
                                                      <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                                                      <TD  colspan="2"  class=2-td2-center width="32%">
                                                        <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getPgjgrmb())%> Ԫ(�����) </DIV></TD>
                                                      </TR>
                                              <TR>
                                                <TD class=2-td2-left width="15%";>
                                                  <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getCjjgwb())%> Ԫ(���) </DIV></TD>
                                                  <TD class=2-td2-left width="25%">
                                                    <DIV align=right>
                                                      ���֣�<bean:write name="fwtdVo" property="bzmc" />
                                                    </DIV>
                                                    <DIV align=right>����:&nbsp<%=DataConvert.BigDecimal2String(fwtdVo.getHldm(),5)%> </DIV></TD>
                                                    <TD class=2-td2-center colspan="2">
                                                      <DIV align=left><%=DataConvert.BigDecimal2String(fwtdVo.getZhjgrmb())%> Ԫ(�ۺ������)</DIV></TD>

                                                    </tr>

                                                  </logic:equal>

                                                  <logic:iterate id="cqdata" indexId="index" length="length" name="sbGrForm" property="cqList"
                                                    type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq">

                                                    <TR>
                                                      <TD class=2-td2-left  rowspan = "4">
                                                        <DIV align=right>	��Ǩ</DIV>
                                                        <DIV align=right>���</DIV></TD>
                                                        <TD class=2-td2-center width="25%" style="word-break:break-all">
                                                          <DIV align=right>����Ǩ���������ַ&nbsp; </DIV>
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
                                                        <!--TD class=2-td2-left >
                                                        <DIV align=right>���ο�ʹ�ò�����&nbsp;</DIV></TD>
                                                        <TD colspan="2"  class=2-td2-center width="25%">
                                                          <DIV align=left>
                                                            &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getBcksybce())%> Ԫ(�����)
                                                          </DIV>
                                                        </TD-->
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
                                                          <DIV align=right>��Ǩ����ʣ���&nbsp;</DIV></TD>
                                                          <TD colspan="2"  class=2-td2-center width="32%">
                                                            <DIV align=left>
                                                              &nbsp;<%=DataConvert.BigDecimal2String(cqdata.getCqbcsye())%> Ԫ(�����)
                                                            </DIV>
                                                          </TD>
                                                        </TR>
                                                      </logic:iterate>

                                                  <logic:iterate id="gydata" indexId="index" length="length" name="sbGrForm" property="gyList"
                                                    type="com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf">

                                                    <TR>
                                                      <TD class=2-td2-left width="8%" rowspan = "6">
                                                        <DIV align=right>	�ѹ�����ס��</DIV>
                                                        <DIV align=right>/��������ס��</DIV>
                                                        <DIV align=right>���г������</DIV>
                                                        <br>
                                                        </TD>
                                                        <TD   class=2-td2-left width="25%">
                                                          <DIV  align=right>���ۺ�ͬ����&nbsp;</DIV>
                                                        </TD>
                                                        <TD colspan="4" class=2-td2-center width="15%">
                                                          <DIV align=left><bean:write name="gydata" property="yggyzfqszsh" /></DIV></TD>
                                                        </TR>
                                                        <TR>
                                                          <TD class=2-td2-left width="15%";>
                                                            <DIV align=right>����Ȩ��֤���&nbsp; </DIV>
                                                          </TD>
                                                          <TD colspan="4" class=2-td2-center width="25%" style="word-break:break-all">
                                                            <DIV align=left><bean:write name="gydata" property="fwqszsh" />&nbsp;</DIV></TD>
                                                          </TR>                                                        
                                                        <TR>
                                                          <TD class=2-td2-left width="15%";>
                                                            <DIV align=right>�����ַ&nbsp; </DIV>
                                                          </TD>
                                                          <TD colspan="4" class=2-td2-center width="25%" style="word-break:break-all">
                                                            <DIV align=left><bean:write name="gydata" property="zldz" />&nbsp;</DIV></TD>
                                                          </TR>
                                                      <TR>
                                                        <TD  class=2-td2-left width="15%";>
                                                          <DIV align=right>���ۺ�ͬ����Լ��ǩ��ʱ��&nbsp;</DIV>
                                                        </TD>
                                                        <TD colspan="4" class=2-td2-center width="25%">
                                                          <DIV align=left><%=DataConvert.TimeStamp2String(gydata.getQdsj())%>  </DIV></TD>
                                                        </TR>
                                                    <TR>
                                                      <TD class=2-td2-left width="15%";>
                                                        <DIV align=right> �������&nbsp; </DIV>
                                                      </TD>
                                                      <TD class=2-td2-left width="25%">
                                                        <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getJzmj(),3)%> �O </DIV></TD>
                                                        <TD class=2-td2-left width="17%">
                                                          <DIV align=right>�ɽ��۸�&nbsp;</DIV></TD>
                                                          <TD colspan="2"  class=2-td2-center width="32%">
                                                            <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getCjjg())%> Ԫ(�����)</DIV></TD>
                                                          </TR>
                                                  <TR>
                                                    <TD class=2-td2-left width="15%">
                                                      <DIV align=right>���εֿ۶�&nbsp;</DIV></TD>
                                                      <TD colspan="1"  class=2-td2-left width="25%">
                                                        <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getBcdke())%> Ԫ(�����)</DIV></TD>
                                                        <TD class=2-td2-left width="32%">
                                                          <DIV align=right>ʣ���&nbsp;</DIV></TD>
                                                          <TD colspan="2"  class=2-td2-center width="32%">
                                                            <DIV align=left><%=DataConvert.BigDecimal2String(gydata.getSye())%> Ԫ(�����)</DIV></TD>
                                                          </TR>

                                                        </logic:iterate>

            <!-- ��ʾ����������Ϣ-->
            <html:hidden property="fwjhAdded"/>
            <logic:equal name="sbGrForm" property="fwjhAdded" value="true">
              <bean:define id="fwjhBo" name="sbGrForm" property="fwjhxxBo" type="com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo"/>

              <logic:equal name="fwjhBo" property="jhperson"  value="1">
                <bean:define id="fgrxx" name="sbGrForm" property="fwjhxxBo.fgrxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx"/>
              </logic:equal>

              <bean:define id="fwtdxx" name="sbGrForm" property="fwjhxxBo.tufwxx" type="com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx"/>
              <!--TR>
                <TD class=2-td2-left width="12%">�Է��ɿʽ</TD>
                <TD class=2-td2-left width="34%"> <DIV align=left>
                  <bean:write name="fwjhBo" property="jkfsmc" /></td>
                  <TD class=2-td2-left width="22%">�Է��������ع����������</TD>
                  <TD colspan="3" class=2-td2-center width="23%"><DIV align=left><bean:write name="fwjhBo" property="fcjslh" />&nbsp;</DIV></TD>
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
              <DIV align=right>��д����</DIV></TD>
              <TD class=2-td2-left width="15%">
                <DIV align=right>���������&nbsp; </DIV>
              </TD>
              <DIV align=right>&nbsp; </DIV>
              <TD class=2-td2-left width="25%">
                <DIV align=left> <bean:write name="fgrxx" property="jsjdm" />&nbsp</DIV></TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>&nbsp; </DIV></TD>
                  <TD colspan="2"  class=2-td2-center width="33%">
                    <DIV align=left> &nbsp;</DIV></TD>
                  </TR>
              <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>��˰������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%" style="word-break:break-all">
                  <DIV align=left>
                    <bean:write name="fgrxx" property="nsrmc" />
                  </DIV></TD>
                  <TD   class=2-td2-left width="19%">
                    <DIV align=right>��˰������&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center width="33%">
                         <DIV align=left><bean:write name="fgrxx" property="nsrlxmc" />&nbsp;</DIV></TD>
                        </TR>

            <TR>
              <TD class=2-td2-left width="15%";>
                <DIV align=right>��������&nbsp; </DIV>
              </TD>
              <TD class=2-td2-left width="25%" >
                <DIV align=left>
                  <bean:write name="fgrxx" property="khyhmc" />&nbsp;
                </DIV></TD>
                <TD   class=2-td2-left width="19%">
                  <DIV align=right>�����˺�&nbsp;</DIV></TD>
                  <TD colspan="2"  class=2-td2-center width="33%">
                    <DIV align=left><bean:write name="fgrxx" property="yhzh" />&nbsp;</DIV></TD>
                  </TR>


              <TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>��ϵ������&nbsp; </DIV>
                </TD>
                <TD class=2-td2-left width="25%" >
                  <DIV align=left>
                    <bean:write name="fgrxx" property="lxrxm" />&nbsp;
                  </DIV></TD>
                  <TD   class=2-td2-left width="19%">
                    <DIV align=right>��ϵ�绰&nbsp;</DIV></TD>
                    <TD colspan="2"  class=2-td2-center width="33%">
                      <DIV align=left><bean:write name="fgrxx" property="lxdh" />&nbsp</DIV></TD>
                    </TR>

              </logic:equal>
              <TR>
                <TD class=2-td2-left width="8%" rowspan = "7">
                  <DIV align=right>	�������ط���</DIV>
                  <DIV align=right>Ȩ��ת��</DIV></TD>
                  <TD class=2-td2-left width="15%">
                    <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                    <TD colspan="4" class=2-td2-center width="15%" style="word-break:break-all">
                      <DIV align=left><bean:write name="fwtdxx" property="fdcxmmc" /></DIV></TD>
                    </TR>
	            <TR>
	              <TD class=2-td2-left width="15%";>
	                <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
	              </TD>
	              <TD class=2-td2-left width="19%">
	                <DIV align=left><%=DataConvert.TimeStamp2String(fwtdxx.getHtqdsj())%> </DIV></TD>
	                <TD class=2-td2-left width="19%">
	                  <DIV align=right>����&nbsp;</DIV></TD>
	                  <TD colspan="2"  class=2-td2-center width="33%">
	                    <DIV align=left>
	                      <bean:write  name="fwtdxx" property="flmc"/>

	                    </DIV></TD>
	                  </TR>
              <!--TR>
                <TD class=2-td2-left width="15%";>
                  <DIV align=right>���ء�����Ȩ��ת������&nbsp; </DIV> </TD>
                  <TD class=2-td2-left width="19%">
                    <DIV align=left>

                      <bean:write name="fwtdxx" property="tdfwqszymc" />
                    </DIV></TD>
                    <TD class=2-td2-left width="19%">
                      <DIV align=right>�������&nbsp;</DIV></TD>
                      <TD colspan="2"  class=2-td2-center width="33%">
                        <DIV align=left>

                          <bean:write name="fwtdxx" property="fwlxmc" />
                        </DIV></TD>
                      </TR-->
                  <TR>
                    <TD  class=2-td2-left width="25%";>
                      <DIV align=right>���ء����������ַ&nbsp; </DIV>
                    </TD>
                    <TD colspan="4"  class=2-td2-center width="15%">
                      <DIV align=left style="word-break:break-all">
                        <bean:write name="fwtdxx" property="tdfwzldz" />
                      </DIV>
                    </DIV></TD>
                  </TR>
                  <TR>
                    <TD class=2-td2-left width="25%";>
                      <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                    </TD>
                    <TD  class=2-td2-left width="15%">
                      <DIV align=left>���أ�
                        <%=DataConvert.BigDecimal2String(fwtdxx.getTdfwqszymj(),3)%>
                        �O </DIV></TD>
                        <TD class=2-td2-left width="17%">
                          <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                          <TD colspan="2"  class=2-td2-center width="32%">
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdxx.getFwjzmj(),3)%>
                              �O</DIV></TD>
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

					</DIV></TD>
	                <TD class=2-td2-left width="19%">
	                  <DIV align=right>������������&nbsp;</DIV></TD>
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
	                    </DIV></TD>
	                  </TR>


                <TR>
                  <TD class=2-td2-left width="8%" rowspan = "2">
                    <DIV align=right>�ɽ��۸������۸�</DIV>
                    <TD  class=2-td2-left width="5%">
                      <DIV align=left>
                        <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgrmb())%>
                        Ԫ(�����) </DIV></TD>
                        <TD class=2-td2-left >
                          <DIV align=right>˰����غ˶��۸�&nbsp; </DIV></TD>
                          <TD  colspan="2"  class=2-td2-center width="32%">
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdxx.getPgjgrmb())%>
                          Ԫ(�����) </DIV></TD>
                        </TR>
                <TR>
                  <TD class=2-td2-left >
                    <DIV align=left>
                      <%=DataConvert.BigDecimal2String(fwtdxx.getCjjgwb())%>
                      Ԫ(���) </DIV></TD>
                      <TD class=2-td2-left >
                        <DIV align=left>
                          ���֣�<bean:write name="fwtdxx" property="bzmc" />

                        </DIV>
                        <DIV align=left>
                          ����:&nbsp;<%=DataConvert.BigDecimal2String(fwtdxx.getHldm(),5)%>
                           </DIV></TD>
                          <TD class=2-td2-center  colspan="2" >
                            <DIV align=left>
                              <%=DataConvert.BigDecimal2String(fwtdxx.getZhjgrmb())%>
                              �ۺ�Ԫ(�����) </DIV></TD>
                            </TR>
      </logic:equal>
    </TBODY></TABLE><BR>

<DIV align=center>


<%
  if ((sbGrForm.getSbbh() != null) && (!sbGrForm.getSbbh().equals("")))
  {

  if (!sbGrForm.isFwjbxxAdded())
  {
%>
		<IMG alt=¼�뷿�ݻ�����Ϣ  id=Fwjbxx name=Submit51
		onclick="doSubmitForm('Fwjbxx');" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Fwjbxx','','<%=static_file%>images/q_lrtdfwjbxx2.jpg',1)"
		src="<%=static_file%>images/q_lrtdfwjbxx1.jpg" style="CURSOR: hand" >

<%
  }
  if (sbGrForm.isFwjbxxAdded())
  {
%>

		<IMG alt=¼���Ǩ���  id=Cqxx name=Submit53
		onclick="doSubmitForm('Cqxx');" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Cqxx','','<%=static_file%>images/q_lrcq2.jpg',1)"
		src="<%=static_file%>images/q_lrcq1.jpg" style="CURSOR: hand" >

		<IMG alt=¼���ѹ�����ס��/��������ס��������� id=Gyzf name=Submit43
		onclick="doSubmitForm('Gyzf');" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Gyzf','','<%=static_file%>images/q_lryggyzfssqk2.jpg',1)"
		src="<%=static_file%>images/q_lryggyzfssqk1.jpg" style="CURSOR: hand" >

<%
  if (sbGrForm.getVoTufwxx().getTdfwqszylx().equals("05")) //����Ƿ��ݽ���
  {
	  if (!sbGrForm.isFwjhAdded())
	  {
%>

		<IMG alt=¼�뷿�ݽ�����Ϣ  id=Fwjhxx name=Submit33
		onclick="doSubmitForm('Fwjhxx');" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Fwjhxx','','<%=static_file%>images/q_lrfwjhxx2.jpg',1)"
		src="<%=static_file%>images/q_lrfwjhxx1.jpg" style="CURSOR: hand" >

<%
      }
  }//end if (sbGrForm.getVoTufwxx().getTdfwqszylx().equals("05"))
%>
		<img alt=��ӡ   id=Print name=Submit33
		onclick="doPrintSbb()" onmouseout=MM_swapImgRestore()
		onmouseover="MM_swapImage('Print','','<%=static_file%>images/q_dysbb2.jpg',1)"
		src="<%=static_file%>images/q_dysqb1.jpg" style="CURSOR: hand">
		<br><br>
<%
 } //end if (sbGrForm.isFwjbxxAdded())
 }
 else  //�걨���Ϊ��
 {
%>

		<IMG alt=���� height=22 id=baocun name="btnSave"
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



<%@ include file="../include/BottomGr.jsp" %>
</BODY></HTML>

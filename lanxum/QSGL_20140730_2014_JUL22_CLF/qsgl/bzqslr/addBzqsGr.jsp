<%@page contentType="text/html; charset=GBK"%>
<%@ include file="/include/include.jsp"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.ActionUtil"%>
<%@ page import="com.creationstar.bjtax.qsgl.VisionLogic.form.BzqsForm"%>
<%
	BzqsForm aForm = (BzqsForm)session.getAttribute("bzqsForm");

%>
<!--add by hazhengze 20081201 start-->
<%@ include file="/include/QRCodeHeader.jsp"%>
<!--add by hazhengze 20081201 end-->

<HTML><HEAD><TITLE>������˰������Ϣ¼���</TITLE>
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
<META content="MSHTML 5.00.2919.6307" name=GENERATOR></HEAD>

<SCRIPT language=JavaScript>
function checkSubmit(operationType)
{

	document.forms[0].operationType.value=operationType;
    document.forms[0].yhbs.value=<%=Constants.ZB_YHBS_GR%>

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


       var checknsr=checkNsr();
       if (checknsr==false){
              return false;
       }

        if (document.forms[0].fdcxmmc.value == "")
  	{
  	   alert("���ز���Ŀ���Ʋ���Ϊ�գ����������룡 ");
  	   document.forms[0].fdcxmmc.focus();
  	   return false;
  	}
        if(document.forms[0].fl.value=="99"&&document.forms[0].beizhu.value == ""){
			 
			 alert("��ע�в������ɲ���Ϊ�գ����������룡");
			 document.forms[0].beizhu.focus();
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
		    {
		      document.forms[0].fwjzmj.focus();
		      return false;
		    }
		  if (document.forms[0].tdfwqszymj.value !="")
	    {
            //�޸�������������ݽ������С��λ��Լ��Ϊ3λ��
            //���ҵ����µ�js����
            //modify by fujx��20081201
		    if (!FN_QSCheckNumberDigit_BZQS(document.forms[0].tdfwqszymj.value,3,"���������"))
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
    <% --%>


			document.forms[0].ztbs.value=<%=Constants.JMSBB_ZTBS_BC%>

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
			<%-- %>document.forms[0].bzmc.value = document.forms[0].bz.options[document.forms[0].bz.selectedIndex].text;<% --%>

  }
  document.forms[0].submit();
}
function linkPage(para)
{
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
    return true;
	//ת����һ��ҳ��
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


</SCRIPT>

<SCRIPT LANGUAGE=javascript>
//��Ȩ������
<%=aForm.getCqrJs()%>

    var arySelect_zjlx =<%=ActionUtil.displaySelectDataSource(aForm.getSfzjlxList(),null,"zjlxdm","zjlxmc")%>; // ֤��
    var arySelect_gj = <%=ActionUtil.displaySelectDataSource(aForm.getGjdqList(),null,"gjdqdm","gjdqmc")%>;//����

    function addRow()
    {
		if(document.forms[0].qrScanFlag.value=="1"){
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
		if(document.forms[0].qrScanFlag.value=="1"){
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
				//��λ���㵽ɨ�������¼������
				document.all.scanInputBzqsGr.focus();
				document.forms[0].qrScanFlag.value="0";
				//alert("document.forms[0].qrScanFlag.value="+document.forms[0].qrScanFlag.value);
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


<!----��ҳ���ͷ�ļ��ͱ�ҳ������·��----->

<BODY bgColor=#ffffff leftMargin=0 style="MARGIN: 0px" topMargin=0 marginheight="0" marginwidth="0" onload="checkSelect();initPage()">
<jsp:include page="/include/Header.jsp" flush="true">

 <jsp:param name="subtitle" value="������˰¼��>������˰������Ϣ¼���"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>
<SCRIPT language=javascript>

</SCRIPT>
 <TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="100%" width=98%>
  <TBODY>
  <TR>
    <TD vAlign=top>

         <TABLE align=center cellSpacing=0 class=table-99>
        <TBODY>
        <TR>
          <TD class=1-td1>�����еط�˰��ֲ�����˰��Ϣ�ɼ���-����</TD></TR>
        <TR>
          <TD class=1-td2 vAlign=top>
          <html:form action="/bzqslr/addBzqsGr.do">
              <html:hidden property="operationType"/>
              <html:hidden property="xtdqsj"/>
              <html:hidden property="ztbs" value="<%=Constants.ZB_ZTBS_BC%>"/>
              <html:hidden property="yhbs" value="<%=Constants.ZB_YHBS_GR%>"/>
<input type="hidden" name="dataSource_gm" value="">
<input type="hidden" name="qrScanFlag" value="0">

<!--add by hazhengze 20081201 start-->

<BR>�ɼ���:<input type="text" name="scanInputBzqsGr" id="scanInputBzqsGr" size="80"  maxlength="5000" onkeydown="check()">
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
	document.all.scanInputBzqsGr.focus();
	//���ý��㵽�����ı������
	var e=document.all.scanInputBzqsGr;
	var r =e.createTextRange();
	r.moveStart('character',e.value.length);
	r.collapse(true);
	r.select();
}

function addRowOnclickAlert(){
	alert("ɨ�����������޸�!");
}

function delRowOnclickAlert(){
	alert("ɨ�����������޸�!");
}

function fuwei_method(){
	//
	document.forms[0].qrScanFlag.value="0";
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
	/**
	/* @todo ��ʼ����̬��
	*/
  var daListRow = daList.getCurrentRow();
	//alert("��̬�� ROW =="+daListRow);
	//ɾ����̬��
           if(daListRow>1){
             for(;daListRow>1;daListRow--){
               daList.moveto(daListRow);
               daList.deleteRow(daListRow);
//               alert("��1�Ժ�=="+daListRow);
             }
           }
           document.forms[0].reset();

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
	//��20�Σ����ز��н�������ƣ��������룩
	//��λ���㵽ɨ�������¼������
	QRInputFocus();
  document.forms[0].operationType.value='Show';
  document.all.scanInputBzqsGr.value="";
}

function shengcheng_method(){
	//alert("call shengcheng()");
	//��λ���㵽ɨ�������¼������
	QRInputFocus();
	//
	str_temp=document.all.scanInputBzqsGr.value;
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
	var istr=document.all.scanInputBzqsGr.value;
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
	document.all.scanInputBzqsGr.value="";
	document.all.scanInputBzqsGr.focus();
	//alert(">>���...");
	//
	document.forms[0].qrScanFlag.value="1";
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
    //document.forms[0].cjjgyrmb.value=retstr[13];
	//��14�Σ�����¥�㣬�ܲ������������룩
	//��15�Σ�����֣��������룩
	//��16�Σ���ҽ��������룩
	//��17�Σ����ʣ��������룩
	//��18�Σ�������Ϣ���������룩
	//��19�Σ�����Ϣ
	index=19+0;
	tmpObj=transObjArray[index];
	var mfxx = tmpObj.split("~");
	var mfxxArrayLength=mfxx.length;
	var mfxxCount=mfxxArrayLength/6;
	//alert("mfxxCount="+mfxxCount);
	for(i=1;i<=mfxxCount;i++){
    if(mfxx[1] == "1"){
      if(i>1){
        addRow();
      }
      daList.focusAt(daList.getCurrentRow(),1);
      //��˰������
      var mcValue=daList.getCellAt(daList.getCurrentRow(),1);
      mcValue.value=mfxx[6*(i-1)+0];
      this.TABLE_LIST.rows(i).cells(1).firstChild.readOnly = true;
      this.TABLE_LIST.rows(i).cells(1).firstChild.style.color="#ADADAD";
      //�绰
      var dhValue=daList.getCellAt(daList.getCurrentRow(),2);
      dhValue.value=mfxx[6*(i-1)+5];
      //��ϵ�绰 ������Ϊֻ��
      //this.TABLE_LIST.rows(1).cells(2).firstChild.readOnly = true;

      //֤������
      var zjlxValue=daList.getCellAt(daList.getCurrentRow(),3);
      zjlxValue.value=zjlxJwToDs(mfxx[6*(i-1)+2]);
      this.TABLE_LIST.rows(i).cells(3).firstChild.disabled = true;
      //֤������
      var zjhmValue=daList.getCellAt(daList.getCurrentRow(),4);
      zjhmValue.value=mfxx[6*(i-1)+3];
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
    	fuwei_method();
    	return false;
    }
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
			  	<TD class=2-td2-t-left width="30%">������˰��Ϣ�ɼ����</TD>
			  	<html:hidden name="bzqsForm" property="sbbh"/>
			  	<TD class=2-td2-t-center width="70%" colspan=5><DIV align=left>&nbsp<bean:write name="bzqsForm" property="sbbh" /> </DIV></TD>

			  </TR>
			  <TR>

				<TD class=2-td2-left>�������ع����������</TD>
				<TD class=2-td2-left>
				<DIV align=left><html:text  property="fwtdbmslh" size="30" maxlength="30"/></DIV></TD>
                <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ίҵ����</TD>
                                          <TD class=2-td2-left>
                                            <DIV align=left><html:text name="bzqsForm" property="jwywbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���ӽ�ίҵ�����ֶν���-->

                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶο�ʼ-->
                                             <TD class=2-td2-left>��ͬ���</TD>
                                          <TD class=2-td2-center>
                                            <DIV align=left><html:text name="bzqsForm" property="htbh" maxlength="30"/>  </DIV></TD>
                                            <!--20081125 modify by fujx ,���Ӻ�ͬ����ֶν���-->
			  </TR>
			  </TBODY>
			  </TABLE>

        <TABLE border=0 cellSpacing=0 class=tabled-99 width="98%">
         <TBODY>    <TR>
    <TD class=2-td2-left colspan="4" width="70%" ><DIV align=left>&nbsp;&nbsp;������д����</DIV></TD>
    <TD class=2-td2-center width="30%" colspan="2" >  <DIV align=right>
    <img onMouseOver="MM_swapImage('add','','<%=static_file%>/images/xinzeng2.jpg',1)" onMouseOut="MM_swapImgRestore()"  value="����" id="Submit522"  name="addnsr" src="<%=static_file%>/images/xinzeng1.jpg" width="79" onClick="addRow()" height="22">
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
</TD>
</TR>


                  <TR>
                  <TD class=2-td2-left width="8%" rowspan = "6">
                  <DIV align=right>	���ط���</DIV>
                   <DIV align=right>Ȩ��ת��</DIV></TD>
                  <TD class=2-td2-left width="15%">
                   <DIV align=right>���ز���Ŀ����&nbsp;</DIV></TD>
                <TD colspan="4" class=2-td2-center width="15%">
                  <DIV align=left><html:text property="fdcxmmc" size="60" maxlength="100" /><FONT color=red>*</FONT> </DIV></TD>
                  </TR>
                <TR>
                 <TD class=2-td2-left width="15%";>
                   <DIV align=right>��Լǩ��ʱ��&nbsp; </DIV>
                </TD>
                 <TD class=2-td2-left width="19%">
                <DIV align=left><html:text property="hyqdsj" size="15"/><FONT color=red>*</FONT><br>(yyyymmdd)</DIV></TD>
                <TD class=2-td2-left width="19%">
                  <DIV align=right>����&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left>

                  <bean:define id="list" name="bzqsForm" property="flList" />

                  <html:select property="fl">
                   <html:options collection="list" labelProperty="qstdfwytmc" property="qstdfwytdm"/>
                   </html:select><FONT color=red>*</FONT>
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
                  <html:select property="tdfwqszylx" onchange="checkSelect()">
                   <html:options collection="list" labelProperty="qszyxsmc" property="qszyxsdm"/>
                   </html:select><FONT color=red>*</FONT>
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
                  <DIV align=left><html:text property="tdfwzldz" size="60" maxlength="200"/><FONT color=red>*</FONT></DIV></TD>
                  </TR>



              <TR>
                 <TD class=2-td2-left width="25%";>
                   <DIV align=right>���ء�����Ȩ��ת�����&nbsp; </DIV>
                </TD>
                <TD  class=2-td2-left width="15%">
                   <DIV align=left>���أ�<html:text property="tdfwqszymj" size="15" maxlength="14" />�O</DIV>
                   </TD>
                <TD class=2-td2-left width="17%">
                  <DIV align=right>���ݽ������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
                  <DIV align=left><html:text property="fwjzmj" size="20" maxlength="14"/>�O</DIV></TD>
             </TR>


              <TR>
                 <TD class=2-td2-left width="25%";>
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
                   <!-- �޸����ؼ���Ϊ��������-->
                  <DIV align=right>��������&nbsp;</DIV></TD>
                <TD colspan="2"  class=2-td2-center width="33%">
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


			<%-- %>
                <TR>
                <TD class=2-td2-left width="8%" rowspan = "2">
                  <DIV align=right>�ɽ�</DIV>
                   <DIV align=right>�۸� <FONT color=red>*</FONT> </DIV>
                  <TD  class=2-td2-left width="5%">
                  <DIV align=left><html:text property="cjjgyrmb" size="20" maxlength="15"/><br>Ԫ(�����) </DIV></TD>
                  <TD class=2-td2-left width="17%">
                  <DIV align=right>�����۸�&nbsp; </DIV></TD>
                <TD  colspan="2"  class=2-td2-center width="32%">
                   <DIV align=left><html:text property="pgjg" size="20" maxlength="15"/> <br>Ԫ(�����) </DIV></TD>
              </TR>

               <TR>
                 <TD class=2-td2-left width="15%" >
                   <DIV align=left><html:text property="cjjgywb" size="20" maxlength="15" onchange="fnConvertWb(this,hn,zhyrmb)"/><br>Ԫ(���) </DIV></TD>
                <TD class=2-td2-center width="33%" >
                  <DIV align=left>
									<html:hidden property="bzmc"/>
                  <bean:define id="list" name="bzqsForm" property="bzList" />
                  <html:select property="bz" onchange="checkSubmit('GetHL')">
                   <html:options collection="list" labelProperty="bzmc" property="bzdm"/>
                    </DIV>

                    <DIV align=left></html:select><br>����:<html:text property="hn" size="15" maxlength="15" onchange="fnConvertWb(cjjgywb,this,zhyrmb)"/></DIV></TD>

                 <TD class=2-td2-center colspan="3" width="25%">
                  <DIV align=left><html:text property="zhyrmb" size="20" maxlength="15"/><br>�ۺ�Ԫ(�����) </DIV></TD>

				</tr>
				<% --%>



                 <TR>
                 <TD class=2-td2-left width="15%";>
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
            src="<%=static_file%>images/baocun1.jpg" style="CURSOR: hand" width=79>

            <IMG alt=�˳� height=22 id=tuichu name=tuichu
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

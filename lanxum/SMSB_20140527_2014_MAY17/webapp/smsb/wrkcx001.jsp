<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/ttsoft-html.tld" prefix="ttsoft" %>
<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="com.ttsoft.framework.util.JspUtil"%>
<%@ page import="com.ttsoft.bjtax.smsb.wrkcx.web.WrkcxActionForm"%>
<%@ page import="com.ttsoft.common.util.CodeConstants"%>
<%
    WrkcxActionForm pf=(WrkcxActionForm)request.getAttribute("wrkcxActionForm");
	System.out.println(pf);
	System.out.println("##########################################");
	 //˰����ش���
    String swjgdm=pf.getSwsdm();
	System.out.println("pf.getZgswjg().values():"+pf.getZgswjg().values());
    String swjgdm_default="";
    String swsdm_default="";
	 if(null==pf.getQueryfj()||null==pf.getQuerysws()){
     swjgdm_default=pf.getSwjgdm();
     swsdm_default=pf.getSwsdm();
	 }else{
          swjgdm_default=pf.getQueryfj();
		  swsdm_default=pf.getQuerysws();
	 }
%>
<html:html>
<head>
<title>�걨��ⲻһ�²�ѯ</title>
<link href="../css/text.css" rel="stylesheet" type="text/css">
<link href="../css/top-box.css" rel="stylesheet" type="text/css">
<link href="css/beijing.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language="JavaScript" src="../js/smsb_common.js"></script>
<script language="JavaScript" src="../js/DTable.js"></script>
<script language="JavaScript" src="../js/InputSelect.js"></script>
<script language="JavaScript" src="../js/function.js"></script>
<script language="javascript" src="../js/gmit_selectcontrol.js"></script>
</head>
<SCRIPT LANGUAGE="JavaScript">

 // ����˰�����
  var arySelect_swjgzzjgdm_ju=<%=JspUtil.displaySelectDataSource(pf.getZgswjg().values(),false)%>;
// ����˰����
  var arySelect_swjgzzjg_suo=<%=JspUtil.displaySelectDataSource(pf.getZgsws().values(),false)%>;
  //��������
  var arySelect_scjx=<%=JspUtil.displaySelectDataSource(pf.getScjx().values(),false)%>;
<!--
//�쿴��ϸ��Ϣҳ
 

	function doQuery_DjInfo(jsjdm)
    	{
			 if(jsjdm=="")
        	{
        	}
        	else
		{
			
			document.forms[0].target="sybd_djxx";
        	document.forms[0].dj_jsjdm.value=jsjdm;
        	document.forms[0].actionType.value="Query_DjInfo";
			document.forms[0].submit();
			document.forms[0].target="";
		}
    	}
function dochangge(){

	addFilterWithNull(document.forms[0].queryfj.value,document.forms[0].querysws,2,arySelect_swjgzzjg_suo);

	addFilterWithNull(document.forms[0].queryfj.value,document.forms[0].queryjx,2,arySelect_scjx);


}	
//-->
</SCRIPT>

<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload = "fnOnLoad()">
 <%@ include file="./include/header.jsp"%>
<html:form action="/webapp/smsb/wrkcxAction.do" method="POST" >
    <html:hidden property="actionType" />
	<html:hidden property="pageSize"/>
	<html:hidden property="nextPage"/>
	<html:hidden property="totalpage"/>
	<html:hidden property="message"/>
	<html:hidden property="dj_jsjdm"/>
	<table width="96%" align="center" cellspacing="0" class="table-99">
		<tr>
			<td class="1-td1"  colspan="7">�걨��ⲻһ�²�ѯ </td>
		</tr>
		<tr>
			<td class="1-td2"  colspan="7">
				<br>
				<table  cellspacing="0" class="table-99">
				    <tr> 
					  <td width="15%" class="2-td2-t-left">���ط־�</td>
					  <td width="35%" class="2-td2-t-left">
					    
						 <div align="left">
           <!--option></option-->
    <%if (pf.getYhjb().equals(CodeConstants.JBDM_SJ)){%>
              <html:select property="queryfj" style="width:160px" onchange="dochangge();">
              </html:select>
    <%}else{%>
              <html:select property="queryfj" style="width:160px" onchange="dochangge();">
              </html:select>
    <%}%>
            </div>
					  </td>
					   <td width="15%" class="2-td2-t-left">˰����</td>
					  <td width="35%" class="2-td2">
					    
						<div align="left">
              <html:select property="querysws" style="width:160px">
              </html:select>
            </div>
					  </td>
					</tr>
					<tr> 
					  <td width="15%" class="2-td2-left">����</td>
					  <td width="35%" class="2-td2-left">
					    
						<div align="left">
						<html:select property="queryjx" style="width:160px"></html:select>
						</div>
					  </td>
					  <td width="15%" class="2-td2-left">�޽�����</td>
					  <td width="35%" class="2-td2-center">
					  <div align="left">
					  С�ڵ���	
						<html:text property="xjrq" size="10" maxlength="8"/>
					  </div>
					  </td>
					</tr>
			<!-- 		<tr> 
					  <td width="30%" class="2-td2-left">�Ǽ�ע������</td>
					  <td width="70%" class="2-td2-t-left">
						<table width="60%" cellpadding=0 cellspacing=0><tr>
							<TD width="40%">
							<html:select property="queryDjzclx" multiple="multiple" size="4">
							<html:options property="djzclx"/>
							</html:select>
							</TD>
							<TD ALIGN="CENTER" width="20%">
							<INPUT TYPE="BUTTON" NAME="" VALUE="ɾ�� >>" OnClick="JavaScript:AddItem()">
							<BR>
							<INPUT TYPE="BUTTON" NAME="" VALUE="<< ���" OnClick="JavaScript:DeleteItem()">
							</TD>
							<TD width="40%">
							<html:select property="queryDjzclx2" multiple="multiple" size="4" >
							<html:options property="djzclx2"/>
							</html:select>

							</TD>
							</tr>
						</table>
						<html:hidden property="alldjzclx"/>
					  </td>
					</tr> -->
					<tr> 
					  <td width="15%" class="2-td2-left">�걨������</td>
					  <td width="35%" class="2-td2-left"> 
					  <div align="left">
					  <html:text property="sbrqq" size="10" maxlength="8"/>
					  <font color="#FF0000">&nbsp;*</font>
					  </div>
					  </td> 
					  <td width="15%" class="2-td2-left">�걨����ֹ</td>
					  <td width="35%" class="2-td2-center"> 
					  <div align="left">
					  <html:text property="sbrqz" size="10" maxlength="8"/>
					  <font color="#FF0000">&nbsp;*</font>
					  </div>
					  </td>
					</tr>
	            	<tr>
					<tr> 
					  <td width="15%" class="2-td2-left">˰������</td>
					  <td width="35%" class="2-td2-left"> 
					  <div align="left">
					  <bean:define id ="sklxItems" name="wrkcxActionForm" property="sklxlist"/>
					  <html:select property="sklxdm"  multiple="multiple" style="width:190px">
					  <option value=""></option>
					  <html:options collection="sklx_list" property="value" labelProperty="label"/>
					  </html:select>
					  
					  </div>
					  </td> 
					  <td width="15%" class="2-td2-left">�Ǽ�ע������</td>
					  <td width="35%" class="2-td2-center"> 
					  <div align="left">
					  <bean:define id ="djzclxItems" name="wrkcxActionForm" property="djzclx"/>
					  <html:select property="queryDjzclx"  multiple="multiple" style="width:190px">
					  <option value=""></option>
					  <html:options collection="djzclx_list"  property="djzclxdm" labelProperty="optionText"/>
					  </html:select>

					  </div>
					  </td>
					</tr>
					<tr>
					  <td colspan="4"  class="2-td2-center" >
                     <input name="I2" type="image" accesskey="q" value="��ѯ"  onclick="doit('Query');return false;" onMouseOver="MM_swapImage('chaxun','','<%=static_contextpath%>images/cx-q2.jpg',1)" onMouseOut="MM_swapImgRestore()" alt="��ѯ" src="<%=static_contextpath%>images/cx-q1.jpg" width="79" height="22" id="chaxun" align="right">
					
					<input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22" align="right"></td>
					</tr>
				</table>
				<br>				
				<table border="0" bgcolor="f3f3f3" class="table-99">
              <tr>
                <td width="43%" ><hr width="100%" class="hr1" size=1></td>
                <td width="14%" align="left" class="black9" > <div align="center"><strong>��ѯ����б�</strong></div></td>
                <td width="43%" ><hr width="100%" class="hr1" size=1></td>
              </tr>
            </table>
				<br>
            <div align="left">&nbsp;&nbsp;����:<ttsoft:write name='wrkcxActionForm' property='bs'/>&nbsp;&nbsp;����:<ttsoft:write name='wrkcxActionForm' property='hs'/>&nbsp;&nbsp;�걨���ϼ�:<ttsoft:write name='wrkcxActionForm' property='sbjehj'/>&nbsp;&nbsp;�����ϼ�:<ttsoft:write name='wrkcxActionForm' property='rkjehj'/>&nbsp;&nbsp;���ϼ�:<ttsoft:write name='wrkcxActionForm' property='cehj'/></div><br>
				<TABLE class="table-99" align="center">
				<TR class="black9">
				<TD>
				<div align="right">
					(��<bean:write name="wrkcxActionForm" property="nextPage"/>ҳ/��<bean:write name="wrkcxActionForm" property="totalpage"/>ҳ)
					<!--��ҳ���ܿ�ʼ-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--��ҳ���ܿ�ʼ-->
				</div>
				</TD>
				</TR>
				<TR>
				<TD>
				<div id="Layer2" style="position:static; overflow: auto;  width: 970px; height: 230px">
				<table id="wrklistTable" border="1" cellspacing="0" class="table-99" align="center" >
					<tr>
						
						<td class="2-td1-left" nowrap>���������</td>
						<td class="2-td1-left" nowrap>��˰������</td>
						<td class="2-td1-left" nowrap>��Ӫ�绰</td>
						<td class="2-td1-left" nowrap>�����</td>
						
						<td class="2-td1-left" nowrap>˰��</td>
						<td class="2-td1-left" nowrap>����������</td>
						<td class="2-td1-left" nowrap>��������ֹ</td>
						
						<td class="2-td1-left" nowrap>�걨���</td>
						
						<td class="2-td1-left" nowrap>�����</td>
						<td class="2-td1-left" nowrap>���</td>
						<td class="2-td1-left" nowrap>�������</td>

						<td class="2-td1-left" nowrap>�걨����</td>
						<td class="2-td1-left" nowrap>�����տ�����</td>
						<td class="2-td1-left" nowrap>�޽�����</td>
						
						<td class="2-td1-left" nowrap>˰������</td>
						<td class="2-td1-left" nowrap>Ԥ���Ŀ</td>

						<td class="2-td1-left" nowrap>������</td>
						<td class="2-td1-left" nowrap>�����ʶ</td>
						<td class="2-td1-left" nowrap>������Ա</td>
						<td class="2-td1-left" nowrap>¼����</td>
						<td class="2-td1-left" nowrap>¼������</td>
						<td class="2-td1-left" nowrap>��������</td>
						<td class="2-td1-left" nowrap>������Դ</td>
						<td class="2-td1-center" nowrap>���ջ���</td>

					</tr>
					<logic:iterate id="item" name="wrkcxActionForm" property="dataList" >
					<tr>
						<td class="2-td2-left" align="center" nowrap>
						<div align="center">
						<a href="javascript:doQuery_DjInfo('<bean:write name='item' property='jsjdm'/>')" >
						<bean:write name='item' property='jsjdm'/>&nbsp;
						</a>
						</div>
						</td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='nsrmc'/></td>
						<td class="2-td2-left" align="center" >&nbsp;<bean:write name='item' property='jydh'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='sjc'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='szmc'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='skssksrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='skssjsrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sjje'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='rkje'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='ce'/></td>

						
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='zyrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sbrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='yhsksj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='xjrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sklx'/></td>
						<td class="2-td2-left" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='yskmmc'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='clbj'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='zwbs'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='hxrmc'/></td>
											
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='lrr'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='lrrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='hxrq'/></td>
						<td class="2-td2-left" align="center" nowrap>&nbsp;<bean:write name='item' property='sjly'/></td>
						<td class="2-td2-center" align="center" nowrap height="2">&nbsp;<bean:write name='item' property='zsswjg'/></td>


					</tr>
					</logic:iterate>
				</table>
				</div>
				</TD>
				</TR>
				<TR class="black9">
				<TD>
				<div  align="right">
					(��<bean:write name="wrkcxActionForm" property="nextPage"/>ҳ/��<bean:write name="wrkcxActionForm" property="totalpage"/>ҳ)
					<!--��ҳ���ܿ�ʼ-->
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('first');return false;"><img name="beginpage" value="��һҳ" alt="��һҳ" onMouseOver="MM_swapImage('diyiye','','<%=static_contextpath%>images/diyiye2.jpg',1)"  onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/diyiye1.jpg" width="79" height="22" id="diyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('previous');return false;"><img name="frontpage" value="ǰҳ" alt="ǰҳ" onMouseOver="MM_swapImage('shangyiye','','<%=static_contextpath%>images/shangyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/shangyiye1.jpg" width="79" height="22" id="shangyiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('next');return false;"><img name="backpage" value="��ҳ" alt="��ҳ" onMouseOver="MM_swapImage('xiayiye','','<%=static_contextpath%>images/xaiyiye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/xaiyiye1.jpg"  width="79" height="22" id="xiayiye"></a>
					<a style="cursor:hand"  tabIndex="-1" onClick="javascript:return fn_ChangePage('last');return false;"><img name="endpage" value="���һҳ" alt="���һҳ" onMouseOver="MM_swapImage('zuimoye','','<%=static_contextpath%>images/zuimoye2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_contextpath%>images/zuimoye1.jpg" width="79" height="22" id="zuimoye"></a>&nbsp;&nbsp;&nbsp;&nbsp;
					<!--��ҳ���ܿ�ʼ-->
				</div>
				</TD>
				</TR>
				</TABLE>
				<br>
				<table width="100%" border="0" cellpadding="0" cellspacing="4">
					<tr valign="bottom">
						<td width="40%">&nbsp; </td>
						<!--  <td width="15%" align="left"><input name="toexcel" type="image" accesskey="s"  onClick="doToExcelSubmit();return false;"  onMouseOver="MM_swapImage('toexcel1','','<%=static_contextpath%>images/b-bcdexcel2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="���浽Excel" id="toexcel1" src="<%=static_contextpath%>images/b-bcdexcel1.jpg" width="100" height="22"></td> -->
                        <td width="10%" align="ceter">	
							<input type="image" accesskey="c"   onClick="tuichu(); return false;"  onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tc-c2.jpg',1)" onMouseOut="MM_swapImgRestore()" value="�˳�" id="tc1" src="<%=static_contextpath%>images/tc-c1.jpg" width="79" height="22">
						</td>
                        <td width="40%">&nbsp;</td>
                      </tr>
				</table>
			</td>
		</tr>
	</table>
	<br>
	<br>
	<br>
<%@ include file="./include/footer.jsp"%>
    </td>
  </tr>
</table>
</html:form>
<script language="JavaScript">

	function doit(ope){
		if(document.forms[0].sbrqq.value==""||document.forms[0].sbrqz.value==""){
			alert("��ѯ�������걨������ֹʱ�䲻��Ϊ�գ�");
			return false;
		}
		 if(!isFullDate1(document.forms[0].sbrqq.value,0,null,false)
               && !isFullDate1(document.forms[0].sbrqz.value,0,null,false)){
			alert("��ѯ�������걨������ֹ���ڸ�ʽ����ȷ��");
			return false;
                }

		//�����еǼ�ע������
		//if(document.all.queryDjzclx.length <= 1){
			//alert("��ѯ�����еĵǼ�ע�����Ͳ���Ϊ�գ�");
			//return false;
		//} -->

		//document.all.alldjzclx.value = "";
		//for(var i = 1 ; i < document.all.queryDjzclx.length ; i++){
		//	document.all.alldjzclx.value = 	document.all.alldjzclx.value + document.all.queryDjzclx.options[i].value;
		//}
		doSubmitForm(ope);
	}

	//��Ӧ���������Ļس���ѯ
	function jsjdmQuery(){
		return false;
	}

	//����ҳ��ʱ��������Ϊ���������¼��
	// ҳ����뽹��ȷ��
	function fnOnLoad()
	{
		document.forms[0].sbrqq.focus();
		if(document.forms[0].message.value!=""){
			alert(document.forms[0].message.value);
		}

		//\u8BBE\u7F6E\u7A0E\u52A1\u673A\u6784\u7EC4\u7EC7\u4EE3\u7801\u9ED8\u8BA4\u503C
        _addOptionsToSelect(document.forms[0].queryfj,arySelect_swjgzzjgdm_ju);
        _selectOptionByValue(document.forms[0].queryfj,"<%=swjgdm_default%>");
		<%if (pf.getYhjb().equals(CodeConstants.JBDM_SWSJ)){%>
               addFilter(document.forms[0].queryfj.value,document.forms[0].querysws,2,arySelect_swjgzzjg_suo);
			 <%} else{%>
                addFilterWithNull(document.forms[0].queryfj.value,document.forms[0].querysws,2,arySelect_swjgzzjg_suo);
				 <%}%>
        
        // \u8BBE\u7F6E\u7A0E\u52A1\u5C40\u9ED8\u8BA4\u663E\u793A\u7A7A\u767D
        //document.forms[0].swjgdm.options.selectedIndex = 0;
		 
        _selectOptionByValue(document.forms[0].querysws,"<%=swsdm_default%>");
		
		addFilterWithNull(document.forms[0].queryfj.value,document.forms[0].queryjx,2,arySelect_scjx);
		_selectOptionByValue(document.forms[0].queryjx,"");

	}

	function fn_ChangePage(type)
	{
		//��ȡ��һ�β�������
		temp=document.forms[0].actionType.value;
		//���õ�ǰ��������
		if(temp=="Query"||temp=="ChangePage"){
			temp="ChangePage";
		}else{
			temp="Show";
		}
		//
		var tmpNextPage=document.forms[0].nextPage.value*1;
		var tmpTotalPage=document.forms[0].totalpage.value*1;
		//alert("tmpNextPage:"+tmpNextPage+"|"+"tmpTotalPage:"+tmpTotalPage+"|"+"type:"+type);
		if(temp=="Show"){
			alert("û���κ��Ѳ�ѯ����,���Ȳ�ѯ...");
			return false;
		}
		if (type == "first")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value="1";
			}
		}
		else if (type == "previous")
		{
			if(tmpNextPage==1){
				alert("���β�ѯ�ѵ����һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage-1)+"";
			}
		}
		else if (type == "next")
		{
			if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}else{
					document.forms[0].nextPage.value =(tmpNextPage+1)+"";
			}
		}
		else if (type == "last")
		{
			if(tmpTotalPage==1){
				alert("���β�ѯֻ����һҳ,�뷵��...");
				return false;
			}else if(tmpNextPage>=tmpTotalPage){
				alert("���β�ѯ�ѵ������һҳ,�뷵��...");
				return false;
			}
			else{
				document.forms[0].nextPage.value=document.forms[0].totalpage.value;
			}
		}
		//�ύ��ѯ
		doit(temp);
		return false;
	}


	//����ѡ��
	  function AddItem(){
	var ln = document.all.queryDjzclx.length;
	var textArr = new Array(); 
	var valueArr = new Array();
	var textArr1 = new Array(); 
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx.length; i++){
		textArr1[i] = document.all.queryDjzclx.options[i].text; 
		valueArr1[i] = document.all.queryDjzclx.options[i].value;
	}	
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx2.options[j].text; 
		valueArr1[i+j-1] = document.all.queryDjzclx2.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx.length; i++) 
{
  textArr[i] = document.all.queryDjzclx.options[i].text; 
  valueArr[i] = document.all.queryDjzclx.options[i].value;
  if(document.all.queryDjzclx.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx2.length; j++){
		if(valueArr[i] == document.all.queryDjzclx2.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx2.add(new Option(textArr[i],valueArr[i]));	
	}
  }
}
  
	var ln1 = document.all.queryDjzclx.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx.options[ln1] = null;		
		}	
	}
	
	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx2.length; j++){
			if(document.all.queryDjzclx2.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx2.length; i++){
		if(document.all.queryDjzclx2.options[i].value != ""){
			if(document.all.queryDjzclx2.options[i].value != null ){
				tempArry[tempJ] = document.all.queryDjzclx2.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx2.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx2.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx2.add(new Option(tempArry[i],tempArry[i]));
	}
}
	//����ѡ��
	  function DeleteItem(){
	var ln = document.all.queryDjzclx2.length;
	var textArr = new Array(); 
	var valueArr = new Array();
	var textArr1 = new Array(); 
	var valueArr1 = new Array();

    for(var i = 1 ;i < document.all.queryDjzclx2.length; i++){
		textArr1[i] = document.all.queryDjzclx2.options[i].text; 
		valueArr1[i] = document.all.queryDjzclx2.options[i].value;
	}	
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		textArr1[i+j-1] = document.all.queryDjzclx.options[j].text; 
		valueArr1[i+j-1] = document.all.queryDjzclx.options[j].value;
	}


for (var i = 1; i < document.all.queryDjzclx2.length; i++) 
{
  textArr[i] = document.all.queryDjzclx2.options[i].text; 
  valueArr[i] = document.all.queryDjzclx2.options[i].value;
  if(document.all.queryDjzclx2.options[i].selected==true){
  	var bool = false;
	for(var j = 1 ; j < document.all.queryDjzclx.length; j++){
		if(valueArr[i] == document.all.queryDjzclx.options[j].value){
			bool = true;
		}
	}
	if(!bool){
  		document.all.queryDjzclx.add(new Option(textArr[i],valueArr[i]));	
	}
  }
}
  
	var ln1 = document.all.queryDjzclx2.length;
	while (ln1--)
	{
		if(ln1 > 0){
			document.all.queryDjzclx2.options[ln1] = null;
		}
	}
	
	for(var i = 1 ; i < textArr1.length ; i++ ){
	    var bool2 = false;
		for(var j = 1 ;j < document.all.queryDjzclx.length; j++){
			if(document.all.queryDjzclx.options[j].value == valueArr1[i]){
			    bool2 = true;
			}
		}
		if(!bool2){
			document.all.queryDjzclx2.add(new Option(textArr1[i],valueArr1[i]));
		}
	}

	var tempArry = new Array();
	var tempJ = 0 ;
	for(var i = 1 ; i < document.all.queryDjzclx.length; i++){
		if(document.all.queryDjzclx.options[i].value != ""){
			if(document.all.queryDjzclx.options[i].value != null){
				tempArry[tempJ] = document.all.queryDjzclx.options[i].value;
				tempJ++;
			}
		}
	}

	tempArry.sort();

	var ln11 = document.all.queryDjzclx.length;
	while(ln11--){
		if(ln11 > 0){
			document.all.queryDjzclx.options[ln11] = null;
		}
	}

	for(var i = 0 ; i < tempArry.length; i++){
		document.all.queryDjzclx.add(new Option(tempArry[i],tempArry[i]));
	}
}
function isFullDate1(strDate,dateKind,empty,isThrow){
	var year,mon,days;
	var err = "";
	if(dateKind==0){//����8λ������
		err = "";

		if(strDate.length==0){
			  if(empty!=null){
					err="���ڱ��벻Ϊ��!\n";
				}
		}else{
			if(strDate.length!=8){
				err="���ڸ�ʽ����ȷ��������8λ����!\n";
			}else{
				var strYear = strDate.substr(0,4);
				var strMonth = strDate.substr(4,2);
				var strDay = strDate.substr(6,2);
				var objDate = new Date(strMonth+'-'+strDay+'-'+strYear);
				if (isNaN(objDate)){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if(strYear*1<1900) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if ((strYear*1 != objDate.getYear()*1)&&(strYear*1 != objDate.getYear()*1+1900)) {
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strMonth*1 != objDate.getMonth()*1+1){
					err="���ڸ�ʽ����ȷ!\n"
				}
				if (strDay*1 != objDate.getDate()*1) {
					err="���ڸ�ʽ����ȷ!\n"
				} //don't call getDay function.
			}
		}
	}else if(dateKind==1){//4λ��
		err = "";
		if((strDate.charAt(0)=="0") || strDate.length!=4 || (!isDigit(strDate))){
			err = "���ǺϷ�����ݣ�";
		}
	}

	if(err!=""){
		if(isThrow){
			alert(err);
		}
		return false;
	}
	return true;
}

//����Excel�ύ
function doToExcelSubmit(){

if(document.all.totalpage.value==""||document.all.totalpage.value==null||document.all.totalpage.value=="0")
	{
		alert("���Ȳ�ѯ��");
		return false;
	}
	    if(document.forms[0].sbrqq.value==""||document.forms[0].sbrqz.value==""){
			alert("��ѯ�������걨������ֹʱ�䲻��Ϊ�գ�");
			return false;
		}
		 if(!isFullDate1(document.forms[0].sbrqq.value,0,null,false)
               && !isFullDate1(document.forms[0].sbrqz.value,0,null,false)){
			alert("��ѯ�������걨������ֹ���ڸ�ʽ����ȷ��");
			return false;
                }
		if(!window.confirm("ȷ��Ҫ����Excel��ѯ����ļ���")){
				return false;
			}
	   // document.all.alldjzclx.value = "";
		//for(var i = 1 ; i < document.all.queryDjzclx.length ; i++){
		//	document.all.alldjzclx.value = 	document.all.alldjzclx.value + document.all.queryDjzclx.options[i].value;
		//}
        doSubmitForm('SaveExcel');
        return true; 

}




</script>


</body>
</html:html>
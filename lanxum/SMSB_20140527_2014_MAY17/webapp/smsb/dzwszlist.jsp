<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<%@ page import="com.ttsoft.bjtax.smsb.dzwsz.web.DzwszForm"%>
<%@ page import="com.ttsoft.bjtax.smsb.dzwsz.processor.DzwszBO"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.Timestamp" %>

<html>
<head>
<title>����ת��ר����˰֤</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<script language=JavaScript src="../js/treatImage.js"></script>
<script language=JavaScript src="../js/smsb_common.js"></script>
<script language=JavaScript src="../js/function.js"></script>
</head>
<%
DzwszForm form = (DzwszForm)request.getAttribute("dzwszForm");
List wszList = form.getWszList();
if("".equals(wszList) || wszList == null){
	wszList = new ArrayList();
}
%>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<%@ include file="./include/header.jsp"%>
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<html:form action="webapp/smsb/dzwszAction" method="POST">
<html:hidden property="actionType"/>
<html:hidden property="dysphm"/>
<table width="96%" align="center" cellspacing="0" class="table-99" >
    <tr>
     	<td class="1-td1" colspan=6 valign="top">���ߵ���ת��ר����˰֤��ѯ</td>
    </tr>
   
	<tr>
		<td width ="50%" class="1-td2" style="border-right-style:none" colspan="3"> <div align="right">
           <input type="radio" onclick="to_change('fkpz')" id="fkpz" name ="flag" value="fkpz">������ƾ֤����</div>
        </td>
		<td width ="50%" class="1-td2"  colspan="3"> <div align="left">
          <input type="radio" onclick="to_change('cz')" id="cz" name ="flag" value="cz">�����ۿ���</div>
        </td>
	</tr>

               <tr id="cxfkpz" >
                   <td width ="20%" class="1-td2" style="border-right-style:none"><div align="right">���������</div>
                   </td>
                   <td  width ="30%" colspan=2 class="1-td2" style="border-right-style:none;border-left-style:none" > 
             		<div align="left">
                    	<input type="text" id=fkpzjsjdm name="fkpzjsjdm" maxlength="8" size="8" value=""><FONT SIZE="" COLOR="#FF0000">*</FONT>
                   </div>
                   </td>
					<td  width ="20%" class="1-td2" style="border-right-style:none">
						<div align="right" >˰Ʊ����</div>
                   </td>
                   <td width ="30%" colspan="2" class="1-td2" style="border-left-style:none">
                    <div align="left">
                       <input type="text" id ="fkpzsphm" name="fkpzsphm" maxlength="18" size="18" value=""><FONT SIZE="" COLOR="#FF0000">*</FONT>
                   </div>
                   </td>
               </tr>

		<tr id="cxcz" >
            <td width="20%" class="1-td2" style="border-right-style:none"><div align="right">���������</div>
              </td>
                 <td class="1-td2" width="10%" style="border-right-style:none;border-left-style:none"> <div align="left">
                    <input type="text" id ="czjsjdm" name="czjsjdm" maxlength="8" size="8" value=""><FONT SIZE="" COLOR="#FF0000">*</FONT>
                   </div>
                 </td>
			<td width="20%" class="1-td2" style="border-right-style:none" >
				<div align="right">��ѯ��ʼ����</div>
             </td>
             <td width="20%" class="1-td2" style="border-left-style:none;border-right-style:none"> <div align="left" >
				   <input type="text" id ="czcxqrq" name="czcxqrq" maxlength="8" size="8" value=""><span>(��ʽ��YYYYMMDD)<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
               </div>
             </td>
			<td width="10%" class="1-td2" style="border-right-style:none"><div align="right">��ѯ��ֹ����</div>
            </td>
            <td width="20%" class="1-td2" style="border-left-style:none"> <div align="left" >
               <input type="text" id ="czcxzrq" name="czcxzrq" maxlength="8" size="8" value=""><span>(��ʽ��YYYYMMDD)<FONT SIZE="" COLOR="#FF0000">*</FONT></span>
			</div>
            </td>
       </tr>
       <tr>
        <td colspan="3"  class="1-td2" style="border-right-style:none">
        <div align="right">
			<input type="image" onclick="to_Query();return false" onMouseOver="MM_swapImage('cx1','','<%=static_contextpath%>images/chaxun2.jpg',1)" alt="��ѯ" onMouseOut="MM_swapImgRestore()" id="cx1" src="<%=static_contextpath%>images/chaxun1.jpg" width="79" height="22" style="cursor:hand">
		</div>
		</td>
        <td colspan="3" class="1-td2" style="border-left-style:none">
        <div align="left">
             <input type="image"  onClick="tuichu();return false;" onMouseOver="MM_swapImage('tc1','','<%=static_contextpath%>images/tuichu2.jpg',1)" alt="�˳�" onMouseOut="MM_swapImgRestore()"  id="tc1" src="<%=static_contextpath%>images/tuichu1.jpg" width="79" height="22" style="cursor:hand">
        </div>
       </td>
                   
       </tr>
	
	<br>
	<tr>
	<td colspan="6" >
	<%
		if(wszList.size()>0){
	%>
		<table width="100%" align="center" cellspacing="0" class="table-99" >
		
		<tr>
		<td colspan="6" aligin="center" class="2-td1-center">��ѯ���</td>
		</tr>
		<br>
		<tr>
		<td width="25%" class="2-td1-left">�ɿ��齻����ˮ��</td>
		<td width="25%" class="2-td1-left">����������</td>
		<td width="25%" class="2-td1-left">�ϼƽ��</td>
		<td width="25%" class="2-td1-right">�ɿ�����</td>
		</tr>
			<logic:iterate id="dzwszBO" name="dzwszForm" property="wszList" type="com.ttsoft.bjtax.smsb.dzwsz.processor.DzwszBO">
			<tr>
			<!-- ������ ������ˮ����������룬˰Ʊ���� -->
			<td width="25%" class="2-td2-left" onclick = "to_print('<bean:write name="dzwszBO" property="sphm" />')" style="cursor:hand;" onmouseover="this.style.textDecoration='underline'" onmouseout="this.style.textDecoration='none'">
				<bean:write name="dzwszBO" property="jylsh" /></td>
				<td width="25%" class="2-td2-left">
				<bean:write name="dzwszBO" property="nsrmc" /></td>
				<td width="25%" class="2-td2-left">
				<bean:write name="dzwszBO" property="hjjexx" /></td>
				<td width="25%" class="2-td2-center">
				<bean:write name="dzwszBO" property="sbrq" /></td>
			
			</tr>
			</logic:iterate>
		
		</table>	
			
	<%
		}
	%>

	</td>
	</tr>
</table>
<br>
<br>

<table width="100%" align="center" cellspacing="0" valign="bottom">

    <tr align="center">
      <td>
        <%@ include file="./include/footer.jsp"%>
      </td>
    </tr>
  </table>
</html:form>

<script>
//�ж��ĸ���ť��ѡ��
 window.onload = function to_decide(){
	var flag = "<%= form.getFlag()%>";
	if(flag=="cz"){
		document.getElementById("cz").checked=true;
		document.getElementById("cxcz").style.display="inline";
		document.getElementById("cxfkpz").style.display="none";
		
		document.getElementById("czjsjdm").value="<%=form.getCzjsjdm()%>";
		document.getElementById("czcxqrq").value="<%=form.getCzcxqrq()%>";
		document.getElementById("czcxzrq").value="<%=form.getCzcxzrq()%>";
		document.getElementById("fkpzjsjdm").value="";
		document.getElementById("fkpzsphm").value="";
	}else{
		document.getElementById("fkpz").checked=true;
		document.getElementById("cxfkpz").style.display="inline";
		document.getElementById("cxcz").style.display="none";
		
		document.getElementById("czjsjdm").value="";
		document.getElementById("czcxqrq").value="";
		document.getElementById("czcxzrq").value="";
		document.getElementById("fkpzjsjdm").value="<%=form.getFkpzjsjdm()%>";
		document.getElementById("fkpzsphm").value="<%=form.getFkpzsphm()%>";
	
	}
} 


//ѡ����������ƾ֤��ѯ���������۲�ѯ
function to_change(id){
		if(id=="cz"){
			document.getElementById("cxcz").style.display="inline";
			document.getElementById("cxfkpz").style.display="none";
			
			document.getElementById("czjsjdm").value="<%=form.getCzjsjdm()%>";
			document.getElementById("czcxqrq").value="<%=form.getCzcxqrq()%>";
			document.getElementById("czcxzrq").value="<%=form.getCzcxzrq()%>";
			document.getElementById("fkpzjsjdm").value="";
			document.getElementById("fkpzsphm").value="";
			
		}else{
			document.getElementById("cxfkpz").style.display="inline";
			document.getElementById("cxcz").style.display="none";

			document.getElementById("czjsjdm").value="";
			document.getElementById("czcxqrq").value="";
			document.getElementById("czcxzrq").value="";
			document.getElementById("fkpzjsjdm").value="<%=form.getFkpzjsjdm()%>";
			document.getElementById("fkpzsphm").value="<%=form.getFkpzsphm()%>";
		
		}
}

//��ѯ��˰֤���
function to_Query(){
	var cz = document.getElementById("cz");
	var fkpz = document.getElementById("fkpz");
	if(fkpz.checked){
		var fkpzjsjdm = document.getElementById("fkpzjsjdm").value;
		var fkpzsphm = document.getElementById("fkpzsphm").value;
		if(fkpzjsjdm.length==0){
			alert("��������벻��Ϊ�գ�");
			return false;
		}
		if(fkpzjsjdm.length<8 || fkpzjsjdm.length>8){
			alert("��������볤�Ȳ���ȷ��");
			return false;
		}
		if(fkpzsphm.length==0){
			alert("˰Ʊ���벻��Ϊ�գ�");
			return false;
		}
		if(fkpzsphm.length<18 || fkpzsphm.length>18){
			alert("˰Ʊ���볤�Ȳ���ȷ��");
			return false;
		}
		
	}else{
		var czjsjdm = document.getElementById("czjsjdm").value;
		var czcxqrq = document.getElementById("czcxqrq").value;
		var czcxzrq = document.getElementById("czcxzrq").value;
		var start = czcxqrq.substring(0,4);
		var end = czcxzrq.substring(0,4);
		if(czjsjdm.length == 0){
			alert("��������벻��Ϊ�գ�");
			return false;
		}
		if(czcxqrq.length == 0){
			alert("��ѯ��ʼ���ڲ���Ϊ�գ�");
			return false;
		}
		if(czcxzrq.length == 0){
			alert("��ѯ��ֹ���ڲ���Ϊ�գ�");
			return false;
		}
		if(isFullDate(czcxqrq,0,'noempty',true)==false || isFullDate(czcxzrq,0,'noempty',true)==false){
			//alert("���ڸ�ʽ����ȷ��");
			return false;
		}
		//���ڲ��ܿ���
		if(start!=end){
			alert("���ܿ����ѯ��");
			return false;
		}
		if(czcxqrq>czcxzrq){
			alert("��ʼ���ڲ��ܴ�����ֹ���ڣ�");
			return fasle;
		}
		
	}
	document.forms[0].actionType.value = "NewQuery";
    document.forms[0].submit();
}
//��ת����ӡҳ��
function to_print(sphm){
	document.forms[0].dysphm.value=sphm;
	document.forms[0].actionType.value="NewPrint";
    document.forms[0].submit();
}

</script>


</body>
</html>
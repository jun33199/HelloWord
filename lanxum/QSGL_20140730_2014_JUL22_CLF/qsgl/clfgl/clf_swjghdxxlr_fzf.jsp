<%@page contentType="text/html; charset=GBK"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.ttsoft.common.util.SessionKey"%>
<%@ page import="com.creationstar.bjtax.qsgl.util.Constants"%>
<%@page import="com.creationstar.bjtax.qsgl.util.DateUtils"%>
<%@page import ="java.util.Date"%>


<%@include file="/include/include.jsp"%>

<HTML>

<HEAD><TITLE>������˰����غ˶���Ϣ¼��</TITLE>
<SCRIPT language=JavaScript src="<%=static_file%>js/inputchecker.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/swapImage.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="../js/qscommon.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/judge.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>js/calculate.js" type=text/JavaScript></SCRIPT>
<SCRIPT language=JavaScript src="<%=static_file%>/js/function.js" type=text/JavaScript></SCRIPT>
<script language=JavaScript src="../js/clfgl_util.js" type="text/javascript"></script>

<LINK href="<%=static_file%>css/text.css" rel=stylesheet type=text/css>
</HEAD>

<BODY bgColor=#ffffff leftMargin=0 topMargin=0 marginheight="0" marginwidth="0" onload="getView();init();">

<jsp:include page="/include/Header.jsp" flush="true">
 <jsp:param name="subtitle" value="����������&gt;������˰����غ˶���Ϣ¼��"/>
 <jsp:param name="helpURL" value=""/>
</jsp:include>

<html:form action="/clfgl/clfswjghdxxlr.do" type="POST">
<html:hidden property="operationType"/>
<html:hidden name="ClfswjghdxxlrForm" property="sbbh"/>

<html:hidden property="cqzbzjzmjflSubmit"/>
<html:hidden property="hfbzSubmit"/>
<html:hidden property="grsdszsfsSubmit"/>
<html:hidden property="ptzfzgxjSubmit"/>
<html:hidden property="jssrqrfsSubmit"/>
<html:hidden property="yyszsfsSubmit"/>
<html:hidden property="jsjeSubmit"/>
<html:hidden property="fwjzmj"/>
<html:hidden property="mpmjydj"/>
<html:hidden property="isSaved"/>
<!--<html:hidden property="hlfy"/>-->
<html:hidden property="ghyt"/>
<html:hidden property="hasMAuthorise"/>
<html:hidden property="tdzzssbfs"/>
<html:hidden property="fwqszylx"/>
<html:hidden property="pageName" value="fzf"/>
<html:hidden property="isQuery"/>
<html:hidden property="tdzsszsfsSubmit"/>
<html:hidden property="yhszsfsSubmit"/>
<html:hidden property="fpcxLink"/>
<html:hidden property="fwszqyjeSubmit"/>
<TABLE align=center border=0 cellPadding=0 cellSpacing=0 height="80%" width="80%">
  <TBODY>
  <TR>
    <TD vAlign=top>
      <table align="center" cellspacing="0" class="table-99">
	  <TBODY>
        <tr>
          <td class="1-td1"><a>���������ѹ�������˰�걨�˶�����ס���������Ʊ�����</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="3%" class="2-td2-t-left"><div align="left"><strong>��ͬ���</strong></div></td>
                <td width="40%" class="2-td2-t-center" colspan="2"><div align="left">
                <html:text property="htbh" maxlength="22" size="43"/>&nbsp;
                <img onClick="document.all.isQuery.value=true;doSubmitForm('Query')" alt="��ѯ" style="cursor:hand" src="<%=static_file%>/images/chaxun1.jpg"  name="Image1111" width="79" height="22" border="0" id="Image1111" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1111','','<%=static_file%>/images/chaxun2.jpg',1)" ></div></td>
              	                <!-- zhangj�� fwhdlxdm(���ݺ˶����ʹ���)0��ס����1����ס��-->
                <td width="15%" class="2-td2-t-left"><div align="center">���ݺ˶�����</div></td>
                <td  colspan="3" class="2-td2-t-center">
                	<div align="center">���˶�Ϊס��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwhdlxdm" value="<%=Constants.FWHDLX_HOUSING%>" onclick="document.all.isQuery.value=false;if(window.confirm('��ȷ��Ҫ�����ݺ˶������л�Ϊס����')){zfchange();}else{document.all.fwhdlxdm[1].checked=true;}"/>
                	&nbsp;���˶�Ϊ��ס��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwhdlxdm" value="<%=Constants.FWHDLX_NONHOUSING%>" onclick="document.all.isQuery.value=false;zfchange()"/></div></td>
              </tr>
            </table>
            <br>
            

            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="15%" class="3-td3-t-left"><div align="right">��ţ���ˮ�ţ���</div></td>
                <td width="15%" class="3-td3-t-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="sbbh" /></div></td>
                <td width="15%" class="3-td3-t-left"><div align="right">�������ڣ�</div></td>
                <td width="25%" class="3-td3-t-left"><div align="left"><%=DateUtils.displayValue(new Date(),"yyyy-MM-dd")%></div></td>
                <td class="3-td3-t-left"><div align="right">��λ��Ԫ���Ƿ֣�</div></td>
              </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" class="table-99" id="sellTab">
              <tr>
              	<td colspan="7" align="center" class="2-td1-center">�����ˣ���������Ϣ</td>
              </tr>
              
<!--               <tr>
              	<td width="25%" align="center" class="2-td2-left">����</td>
              	<td align="center" class="2-td2-left">֤������</td>
              	<td align="center" class="2-td2-left">֤������</td>
              	<td align="center" class="2-td2-center">��ϵ�绰</td>
              </tr>
              <tr>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-center">&nbsp;</td>
              </tr> -->
              
            </table>
            <br>
            <table width="75%" border="0" cellspacing="0" class="table-99" id="buyTab">
              <tr>
              	<td colspan="7" align="center" class="2-td1-center">�����ˣ��򷽣���Ϣ</td>
              </tr>
<!--               <tr>
              	<td width="25%" align="center" class="2-td2-left">����</td>
              	<td align="center" class="2-td2-left">֤������</td>
              	<td align="center" class="2-td2-left">֤������</td>
              	<td align="center" class="2-td2-center">��ϵ�绰</td>
              </tr>
              <tr>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-left">&nbsp;</td>
              	<td align="center" class="2-td2-center">&nbsp;</td>
              </tr> -->
            </table>
            <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="11%" class="2-td2-t-left"><div align="center">��������סַ</div></td>
                <td colspan="6" class="2-td2-t-center"><div align="left"><html:text property="sqrxzdz" size="99%" />&nbsp;</div></td>
              </tr>
              <tr>
                <td width="11%" class="2-td2-left"><div align="center">������ϸ��ַ</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fwzldz" /></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center"><FONT color=red>�Ƿ�Ϊ��ͥΨһ�����÷�</FONT></div></td>
                <td colspan="2" class="2-td2-center">
                	<div align="left">&nbsp;��<html:radio name="ClfswjghdxxlrForm" property="jtwyshyhbz" value="<%=Constants.ONLY_ROOM_YES%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;
                					  &nbsp;��<html:radio name="ClfswjghdxxlrForm" property="jtwyshyhbz" value="<%=Constants.ONLY_ROOM_NOT%>" onclick="getView()"/></div></td>
              </tr>
              <tr>
                <td width="11%" class="2-td2-left"><div align="center">���ݲ�Ȩ֤��</div></td>
                <td width="19%" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fwcqzh" /></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center">���ݲ�Ȩ֤����ע�ķ�������</div></td>
                <td width="15%" class="2-td2-left">
                	 <div align="center"><bean:define id="dta" name="ClfswjghdxxlrForm" property="fwcqzbzzflxList"/>
                  		<html:select property="fwcqzbzzflxdm" style='width:190px' onchange="getView()">
                    		<html:options collection="dta" labelProperty="fwcqzbzzflxmc" property="fwcqzbzzflxdm"/>
                  		</html:select></div></td>
                <td class="2-td2-left" nowrap="nowrap"><div align="center">���뿪Ʊ���(���ν��׽��)</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"> &nbsp;<bean:write name="ClfswjghdxxlrForm" property="htzj" /></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">��������</div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="center">¥��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BUILDINGS%>"/></div></td>
                <td class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="center">ƽ��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BUNGALOW%>"/></div></td>
                <td class="2-td2-left" style="border-left-style:none;">
                	<div align="center">������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BASEMENT%>"/></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center">����Ȩ��ת����ϸ</div></td>
                <td width="15%" class="2-td2-left">
                	 <div align="center"><bean:define id="qszyxsmx" name="ClfswjghdxxlrForm" property="qszyxsmxList"/>
                  		<html:select property="qszyxsmxdm" style='width:190px' onchange="getView()">
                  			<html:option value="">��ѡ��</html:option>
                    		<html:options collection="qszyxsmx" labelProperty="qszyxsmxmc" property="qszyxsmxdm"/>
                  		</html:select></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>�������</FONT></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="jcnd" size="10" maxlength="4"/>&nbsp;<FONT color=red>(yyyy)</FONT>   &nbsp;&nbsp;<input type="checkbox"  id="wjcnd" onclick="setJcnd(this)">�޽������</div></td>
                <td class="2-td2-left"><div align="center">��¥��</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="zlc_show" /></div></td>
                <td class="2-td2-left"><div align="center">����¥��</div></td>
                <td colspan="2" class="2-td2-center" nowrap="nowrap"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="szlc_show" /></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">���ݽ������</div></td>
                <td class="2-td2-left"><div align="left"><input disabled="disabled" name="fwjzmj" value="<bean:write name="ClfswjghdxxlrForm" property="fwjzmj" />" ></div></td>
                <td colspan="2" class="2-td2-left"><div align="center"><FONT color=red>ԭ������Ʊ���</FONT></div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="ygffpje" size="22" onchange="getView()"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>����֤������</FONT></div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="gfzmrq" size="10"/>&nbsp;<FONT color=red>(yyyymmdd)</FONT></div></td>
                <td class="2-td2-left"><div align="center">����˫����ͬǩ������</div></td>
                <td colspan="2" class="2-td2-center"><div align="center">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="htwsqyrq" /></div></td>
              </tr>
              <tr>
                <td colspan="7" class="2-td2-center"><div align="center"><FONT color=red> �걨��ʽ�����ݸ�˰����ع涨���ɶ�ѡ��</FONT></div></td>
              </tr>
              <tr>
                <td  class="2-td2-center" style="border-right-style:none" nowrap="nowrap">
                	<div align="center"><FONT color=red>�ṩ������Ʊ&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFFP" value="<%=Constants.TDZSS_SB_GFFP%>" onclick="getView();gffpQuery()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>�ṩ������˰Ʊ&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFQSP" value="<%=Constants.TDZSS_SB_GFQSP%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>�ṩԭ������ͬ&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFHT" value="<%=Constants.TDZSS_SB_GFHT%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>�ṩ��������&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_PGBG" value="<%=Constants.TDZSS_SB_PGBG%>" onclick="changeTdzzsAndJssrqr();getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>���κ�Ʊ��&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_WPJ" value="<%=Constants.TDZSS_SB_WPJ%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>�ṩ��������&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_FLWS" value="<%=Constants.TDZSS_SB_FLWS%>" onclick="getView()"></div></td>
              </tr>
              <tr>
                <td colspan="3" class="2-td2-left"><div align="center">�ṩ������Ʊ��д����</div></td>
                <td colspan="4" class="2-td2-center"><div align="center">�ṩ����������д������</div></td>
              </tr>
              <tr>
                <td colspan="2" class="2-td2-left"><div align="center"><FONT color=red>ȡ�÷��ز�ʱ�����ɵ���˰���</FONT></div></td>
                <td  class="2-td2-left"><div align="left"><html:text property="qdfcqsje" size="12" onchange="getView()"/>&nbsp;</div></td>
                <td colspan="2" class="2-td2-left"><div align="center">ȡ������ʹ��Ȩ��֧���Ľ��</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"><html:text property="qdtdsyqzfje" size="15" disabled="true"/>&nbsp;</div></td>                
                
			  </tr>
              <tr>
                <td colspan="2" class="2-td2-left"><div align="center">&nbsp;&nbsp;&nbsp;<FONT color=red>ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���</FONT></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="qdfcyhsje" size="12"  onchange="getView()"/>&nbsp;</div></td>              
                <td colspan="2" class="2-td2-left"><div align="center">�ɷ���������������۸�</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"><html:text property="jfpgjg" size="15" disabled="true" onchange="getView()"/>&nbsp;</div></td>
                                            
			  </tr>
              <tr>
                <td  colspan="2" class="2-td2-left"><div align="center">��Ʊ��������</div></td>
                <td  class="2-td2-left" nowrap="nowrap"><div align="left"><html:text property="fpszrq" size="12" onchange="getView()"/>&nbsp;<FONT color=red>(yyyymmdd)</FONT></div></td>               
                <td colspan="2"  class="2-td2-left"><div align="center">�۸���������</div></td>
                <td colspan="2"  class="2-td2-center"><div align="left"><html:text property="jgpgfy" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
			  <tr>
                <td  colspan="2" class="2-td2-left"><div align="center">����Ӽ�����</div></td>
                <td  class="2-td2-left"><div align="left"><html:text property="anjjse" size="12"/></div></td> 		
                <td colspan="2"  class="2-td2-left"><div align="center"><!-- ԭ��˰Ʊ���ݼ�˰�۸�--> &nbsp;</div> </td>
                <td colspan="2"  class="2-td2-center"><div align="left"><html:text property="yqspfwjsjg" size="15" value="0"/>&nbsp;</div></td>		  
			  </tr>              
		 </table>   
		 <table width="75%" border="0" cellspacing="0" class="table-99" >  
			  <tr>
			  	<td class="2-td2-center"><div align="left">
			  	<img onclick="displayOrNot()" src="<%=static_file%>images/zbotton-jian2.gif" alt="����" name="zhankai" id="zhankai"  style="cursor:hand" value="false"></div></td>
			  </tr>
		</table> 
		<table width="75%" border="0" cellspacing="0" class="table-99" id="fdczjxx">                  
             <tr>
                <td class="2-td2-left"><div align="center">���ز��н��������</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fdczjjgmc" /></div></td>
                <td class="2-td2-left"><div align="center">��˰���������</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjsjdm" size="15"/>&nbsp; </div>                 	
                </td>

              </tr>               
              <tr>
                <td class="2-td2-left"><div align="center">��˰˰��ǼǺ���</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjswdjzh" size="25"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">��ϵ�绰</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjlxdh" size="15"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">���ز�����������</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjjjr" size="25" maxlength="100"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">��ϵ�绰</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjjrlxdh" size="15"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap="nowrap"><div align="center">���ز����������֤����</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjjjrzjhm" size="25"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">�������ʸ�֤�����</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjjrzgzsh" size="15"/>&nbsp;</div></td>
              </tr>
		</table>
		<table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td class="2-td2-left"><div align="center">��Ȩ֤��ע�������</div></td>
                <td class="2-td2-left" nowrap="nowrap">
                	<div align="center">
                	140ƽ�ף���������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="cqzbzjzmjfl" value="<%=Constants.CQZBZ_JLMX_LOW%>" disabled="true"/>
                	&nbsp;&nbsp;&nbsp;&nbsp;140ƽ������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="cqzbzjzmjfl" value="<%=Constants.CQZBZ_JLMX_HIGH%>" disabled="true"/>
                	</div></td>
                <td width="18%" class="2-td2-left"><div align="center">ÿƽ�׽��׵���</div></td>
                <td width="30%" class="2-td2-left"><div align="left">&nbsp;<label id="showMpmjydj"><input type="hidden" name="hidd_mpmjydj" value="<bean:write name="ClfswjghdxxlrForm" property="mpmjydj" />" onchange="getView()"> </div></td>                
                <td class="2-td2-left">
                	<div align="center">
                		ÿƽ�׺˶�����
                	</div>
                </td>
                <td  class="2-td2-center" colspan="2">
                	<div align="left">
                		<html:text  name="ClfswjghdxxlrForm" property="mpmhdjg" size="15" onchange="getView()"/>
                	</div>
                </td>
			  </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="center">�������ڵ����ؼ���</div></td>
                <td width="18%" class="2-td2-left">
                	<div align="left"><bean:define id="dto" name="ClfswjghdxxlrForm" property="tdjcList"/>
                  		<html:select property="tdjcdm" style='width:75%'>
                  		<html:option value="">��ѡ��</html:option>
                    	<html:options collection="dto" labelProperty="tdjcmc" property="tdjcdm"/>
                  </html:select> &nbsp;</div></td>
                  
                   
                  <td class="2-td2-left"><div align="center"><FONT color=red>������������</FONT></div></td>
                  <td class="2-td2-left">
                	<div align="center"><bean:define id="fwszqy_dto" name="ClfswjghdxxlrForm" property="fwszqyList"/>
                  		<html:select  name="ClfswjghdxxlrForm" property="fwszqydm" style='width:165px' onchange="getView()">
                    	<html:options collection="fwszqy_dto" labelProperty="fwszqymc" property="fwszqydm"/>
                  </html:select></div>                  
                  </td>
                  
                <td class="2-td2-left"><div align="center"><FONT color=red>��ͨס������޼�</FONT></div></td>
                <td colspan="2" class="2-td2-center"><div align="left"> <html:text property="ptzfzgxj" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>�����ݻ���</FONT></div></td>
                <td  class="2-td2-left" style="border-right-style:none">
                	<div align="right">1.0����&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwrjl" value="<%=Constants.FWRJL_LOW%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-left-style:none;">
                	<div align="right">1.0����������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwrjl" value="<%=Constants.FWRJL_HIGH%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="4" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
              <!--modified by zhangj   -->
                <td class="2-td2-left"><div align="center">���ֱ�׼</div></td>
                <td colspan="6" class="2-td2-center" >
                	<div align="center">��ͨס��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_PT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	����ͨס��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_NOTPT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	��ס��&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_FZF%>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	�����޳�����&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_GRWCZY%>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	���˼̳�&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_GRJC%>" />&nbsp;&nbsp;&nbsp;</div></td>
                <!-- <td colspan="4" class="2-td2-center" style="border-left-style:none;">&nbsp;</td> -->
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>ס��ʹ��ʱ��</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">5�꣨��������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_FIVE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">5������3������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_THREETOFOIVE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td  class="2-td2-left" style="border-left-style:none;">
                	<div align="right">3�꣨��������&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_THREE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
              	<td colspan="2" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>Ӫҵ˰���շ�ʽ</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">����Ӫҵ˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_NOT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">ȫ������Ӫҵ˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_ALL%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" style="border-left-style:none;">
                	<div align="right">�������Ӫҵ˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_MINUS%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
              	<td colspan="2" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td nowrap class="2-td2-left"><div align="center"><FONT color=red>��������˰���շ�ʽ</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">������������˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="grsdszsfs" value="<%=Constants.GRSDSZSFS_FREE%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-center" style="border-left-style:none;border-right-style:none;">
                	<div align="right">���ո�������˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="grsdszsfs" value="<%=Constants.GRSDSZSFS_ZS%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="3" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>������ֵ˰���շ�ʽ</FONT></div></td>
                <td class="2-td2-center" style="border-right-style:none" nowrap="nowrap">
                	<div align="center">�ṩ������Ʊ����������ֵ˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_GFFPZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">�˶�����������ֵ˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_HDZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">�ṩ������������������ֵ˰&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_PGBGZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">����������ֵ˰&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs" value="<%=Constants.TDZZSZSFS_NOT%>" />&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-left-style:none;" nowrap="nowrap">
                	<div align="center">����������ֵ˰&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_FREE%>" />&nbsp;&nbsp;</div></td>                               
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>ӡ��˰���շ�ʽ</FONT></div></td>  
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">����ӡ��˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yhszsfs" value="<%=Constants.YHSZSFS_ZS%>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-center" style="border-left-style:none;border-right-style:none;">
                	<div align="right">����ӡ��˰&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yhszsfs" value="<%=Constants.YHSZSFS_FREE%>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="3" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>                            
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>��˰����ȷ�Ϸ�ʽ</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">��ͬ�۸�&nbsp;<html:radio name="ClfswjghdxxlrForm" property="jssrqrfs" value="<%=Constants.JSSRQRFS_HTJG%>" disabled="true" onclick="changeTdzzsAndJssrqr()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">�˶���˰�۸�&nbsp;<html:radio name="ClfswjghdxxlrForm" property="jssrqrfs" value="<%=Constants.JSSRQRFS_HDJSJG%>" disabled="true" onclick="changeTdzzsAndJssrqr()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" ><div align="center"><FONT color=red>���</FONT></div></td>
                <td colspan="2" class="2-td2-center" ><div align="left"> <html:text property="jsje" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
              <tr>
              	<td class="2-td2-left"><div align="center"><FONT color=red>�ǽ�˰˰��</FONT></div></td>
              	<td colspan="6" class="2-td2-center">
	              	<div align="left">
	              		<html:select  name="ClfswjghdxxlrForm" property="cjssl" style="width:12%">
	              			<html:option value="0.07">7%</html:option>
	              			<html:option value="0.05">5%</html:option>
	              			<html:option value="0.01">1%</html:option>
	              		</html:select>
	              	</div>
              	</td>
              </tr>
              <tr>
                <td colspan="7" class="2-td2-center"><div align="center">�۳����ȷ��</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">ס�������۸� </div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfpgjg" size="15"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">��֤��</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="gzf" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>                            
                <td class="2-td2-left"><div align="center">ԭ������Ʊ���</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showYgffpje"><input type="hidden" name="inner_ygffpje" value="<bean:write name="ClfswjghdxxlrForm" property="ygffpje" />" > </div></td> 
                                              
              </tr>
              
              <tr>
                <td class="2-td2-left"><div align="center">ס��������Ϣ </div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfdklx" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">������</div></td>    
                <td class="2-td2-left"><div align="left"> <html:text property="sxf" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>                                  
                <td class="2-td2-left" nowrap="nowrap"><div align="left">���У�ȡ�÷��ز�ʱ�����ɵ���˰���</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showQdfcqsje"><input type="hidden" name="inner_qdfcqsje" value="<bean:write name="ClfswjghdxxlrForm" property="qdfcqsje" />" ></div></td>                              
              </tr>
              
              <tr>
                <td class="2-td2-left"><div align="center">ס��װ�޷���</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfzxfy" size="15" onchange="getView()" disabled="true"/>&nbsp;
                  <!--<img onclick="setFwzxfy()" alt="��װ�޷���ƾ֤"  onMouseOver="MM_swapImage('xxx','','../image/wzxfypz2_blue.png',1)" onMouseOut="MM_swapImgRestore()" src="../image/wzxfypz2_gray.png" name="back" id="xxx"  style="cursor:hand">-->
                </div></td>              
                <td class="2-td2-left"><div align="center">���س��ý�</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="tdcrj" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>            
                <td class="2-td2-left" nowrap="nowrap"><div align="left">���У�ȡ�÷��ز�ʱ�����ɵ�ӡ��˰��� </div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showQdfcyhsje"><input type="hidden" name="inner_qdfcyhsje" value="<bean:write name="ClfswjghdxxlrForm" property="qdfcyhsje" />" ></div></td>       
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">�������</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">
                	<html:text property="hlfy" size="15" onchange="getView()"/>
                	<!-- <label id="showHlfy"><input type="hidden" name="hidd_hlfy" value="<bean:write name="ClfswjghdxxlrForm" property="hlfy" />" > </label> -->
                	</div></td>
                <td class="2-td2-left" nowrap="nowrap"><div align="left">����:�ɷ��������������۸�</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showJfpgjg"><input type="hidden" name="inner_jfpgjg" value="<bean:write name="ClfswjghdxxlrForm" property="jfpgjg" />" ></div></td>
              </tr>
            </table>  

              <br><hr>

      		<DIV align=center>
      		<logic:notEqual name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<img onclick="doSubmitForm('Save')" alt="����"  onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</logic:notEqual>
      		
      		<logic:equal name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<logic:equal name="ClfswjghdxxlrForm" property="hasMAuthorise" value="true">
						<IMG alt=ɾ���ú˶���Ϣ  height=22 id=shanchu name=shanchu onclick="doSubmitForm('Delete')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)" src="<%=static_file%>images/shanchu1.jpg" style='CURSOR: hand' width=79>
						<IMG alt=�����޸���Ϣ  height=22 id=b-bcbg name=b-bcbg onclick="document.all.isQuery.value=true;doSubmitForm('Save')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('b-bcbg','','<%=static_file%>images/b-bcbg2.jpg',1)" src="<%=static_file%>images/b-bcbg1.jpg" style='CURSOR: hand' width=79>
				</logic:equal>
      		</logic:equal>
      		
      		<logic:equal name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<img onclick="doSubmitForm('ToPrint')" alt="��ӡ"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/dayin2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</logic:equal>
      			<img onClick="doSubmitForm('ToSellerQSZS')" alt="ת����˰������" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image124','','<%=static_file%>images/mfskzs2.jpg',1)"  src="<%=static_file%>images/mfskzs1.jpg" name="Image124" width="110" height="22" border="0" id="Image124" >
       			<img onClick="doSubmitForm('ToFPDK')" alt="ת��Ʊ����" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image126','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="Image126" width="100" height="22" border="0" id="Image126" >
       			<%-- <img onClick="doSubmitForm('ToQSSB')" alt="ת��˰�걨" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image125','','<%=static_file%>images/qssb2.jpg',1)"  src="<%=static_file%>images/qssb1.jpg" name="Image125" width="100" height="22" border="0" id="Image125" > --%>
      			<img onclick="doSubmitForm('Return')" alt="�˳�"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
      		
      		<hr width="99%" class="hr1" size=1>

           <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="22%" class="2-td2-t-left">
                  <div align="right">¼����</div></td>
                <td width="31%" class="2-td2-t-left">
                  <div align="left">&nbsp;
                    <bean:write name="ClfswjghdxxlrForm" property="lrr" />
                </div></td>
                <td width="16%" class="2-td2-t-left">
                  <div align="right">¼������</div></td>
                <td width="31%" class="2-td2-t-center">
                  <div align="left">&nbsp;
                <bean:write name="ClfswjghdxxlrForm" property="lrrq" />
                </div></td>
              </tr>
            </table>
      <BR>
</html:form>
<%@ include file="../include/Bottom.jsp" %>
</BODY></HTML>

<style type="text/css">
.3-td3-t-left {
	font-size: 9pt;
	line-height: 20px;
	color: #3E6071;
	background-color: #ECF2F4;
	text-align: center;
	border-top: 0px solid #9BB4CA;
	border-right: 0px solid #9BB4CA;
	border-bottom: 0px solid #9BB4CA;
	border-left: 0px solid #9BB4CA;
	vertical-align: center;

}

</style>

<script language=JavaScript type='text/JavaScript'>
var ygffpje = document.forms[0].ygffpje;
var gfzmrq = document.forms[0].gfzmrq;
var ptzfzgxj = document.forms[0].ptzfzgxj;
var jsje = document.forms[0].jsje;
var qdfcqsje = document.forms[0].qdfcqsje;
var qdfcyhsje = document.forms[0].qdfcyhsje;
var zfpgjg = document.forms[0].zfpgjg;
var zfzxfy = document.forms[0].zfzxfy;
var zfdklx = document.forms[0].zfdklx;
var sxf = document.forms[0].sxf;
var gzf = document.forms[0].gzf;
var tdcrj = document.forms[0].tdcrj;
var qdtdsyqzfje = document.forms[0].qdtdsyqzfje;
var jfpgjg = document.forms[0].jfpgjg;
var jgpgfy = document.forms[0].jgpgfy;

var cqzbzjzmjfl_checkes = document.all.cqzbzjzmjfl;
var jtwyshyhbz_checked = document.all.jtwyshyhbz;
var cqzbzjzmjfl_checked = document.all.cqzbzjzmjfl;
var fwrjl_checked = document.all.fwrjl;
var hfbz_checked = document.all.hfbz;
var zfsjsjfl_checked = document.all.zfsjsjfl;
var yyszsfs_checked = document.all.yyszsfs;
var grsdszsfs_checked = document.all.grsdszsfs;
var tdzsszsfs_checked = document.all.tdzsszsfs;
var jssrqrfs_checked = document.all.jssrqrfs;
var tdzzssbfs_checked = document.all.tdzzssbfs_chBox;

//����Ӽ�����
var old_anjjse ="";
var old_anjjse_after_30_days="";

function init(){
	//������Ϣ
	var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
	parseSaveBuyorSellInfo(sellerInfo,"sellTab","");
	//����Ϣ
	var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
	parseSaveBuyorSellInfo(buyyerInfo,"buyTab","");
	
	//�޽������ѡ��
	var jcnd = '<bean:write name="ClfswjghdxxlrForm" property="jcnd"/>';
	if(jcnd != null && jcnd != "" && jcnd =="�޽������"){
		document.all.wjcnd.checked = true;
	}
	//added by zhangj
	document.all.yqspfwjsjg.style.display="none";
	
	document.all.fwhdlxdm[1].checked = true;
	//document.all.yhszsfs[0].checked = true;
	if(document.all.jtwyshyhbz[0].checked==false && document.all.jtwyshyhbz[1].checked==false){
		document.all.jtwyshyhbz[1].checked = true;
	}
	if(document.all.isSaved.value=="false" && document.all.isQuery.value!="false"){
		ghytchange();
	}
	var value=hasValue(document.all.tdzzssbfs.value,document.getElementById("tdzzssbfs_PGBG").value);
	String_toChBox(document.all.tdzzssbfs.value,"tdzzssbfs_chBox");
	String_toChBox(document.all.yyszsfsSubmit.value,"yyszsfs");
	String_toChBox(document.all.grsdszsfsSubmit.value,"grsdszsfs");
	var tdzsszsfs=document.all.tdzsszsfsSubmit.value;
	if(tdzsszsfs!=""&&tdzsszsfs!=null){
		String_toChBox(tdzsszsfs,"tdzsszsfs");
	}
	
	//��������ֵ˰����ʾ״̬���п���
	if(document.all.fwqszylx.value=="04" && (document.all.qszyxsmxdm.value=="02" ||document.all.qszyxsmxdm.value=="04" ||document.all.qszyxsmxdm.value=="05"||document.all.qszyxsmxdm.value=="06" )){			
			for(var i=0;i<tdzsszsfs_checked.length;i++){
				tdzsszsfs_checked[i].disabled=true;
			}		
	}else{
			for(var i=0;i<tdzsszsfs_checked.length;i++){
				tdzsszsfs_checked[i].disabled=false;
			}
	}
	
	if(document.all.isSaved.value=="false" || (document.all.isSaved.value=="true" && document.all.isQuery.value=="false")){
		document.all.fwcqzbzzflxdm.value="12";
	}
	document.all.isQuery.value="false";
	//document.all.fdczjxx.style.display="none";
	//var  resyear = getYueShu('20131030','20120428');
	//alert("resyear+++"+resyear);
}

/**
 * ���������������·�
 */
function getYueShu(date1,date2){
	if(date1== null || date1 =="" || date2 == null || date2 ==""){
		return "NaN";
	}
	
	//alert(date1+"--"+date2);
	
	//alert(checkDate(date1));
	//alert(checkDate(date2));
	
	if(!checkDate(date1)||!checkDate(date2)){
		alert("������д�������飡");
		return "NaN";
	}
	
	
	
	date1 = date1.replace(/-/g, "");
	var date1_year = date1.substr(0,4);
	var date1_month = date1.substr(4,2);
	var date1_day = date1.substr(6,2);
	
	date2 = date2.replace(/-/g, "");
	var date2_year = date2.substr(0,4);
	var date2_month = date2.substr(4,2);
	var date2_day = date2.substr(6,2);	
	
	var date1_all_months = parseFloat(date1_year)*12 + parseFloat(date1_month);
	var date2_all_months = parseFloat(date2_year)*12 + parseFloat(date2_month);
	
	//ȡ���������·ݲ�ֵ
	var Months = date1_all_months- date2_all_months;
	var days = parseFloat(date1_day)-parseFloat(date2_day);
	
	var resYears =0;
	
	//����12�������м���
	var division_12 = Math.floor(Months/12);
	
	//ģ12����ó���12֮�������
	var model_12 = Months%12;
	
	
	//alert("division_12--"+division_12 +"   model_12--"+model_12);
	
	
	
	/* //����һ�꣬����0��
	if(division_12 == 0){
		resYears =0;
		return resYears;
	}
	
	
	//���ڵ���һ��
	if(division_12 > 0){
		//������С��6���£�������
		if(model_12 < 6){
			return division_12;
		}
		
		//����������6���£����ؼ�һ��
		if(model_12 >6){
			resYears = division_12 + 1;
			return resYears;
		}
		
		//����������6���£��� days >0 ���ؼ�һ��
		if(model_12 == 6 && days >0){
			resYears = division_12 + 1;
			return resYears;
		}
		 
		resYears = division_12;
		
		return resYears;
	}	 */
	if(division_12 == 0){
		resYears =0;
		return resYears;
	}else if(division_12==1){
	    if(model_12==0 && days<0){
		resYears=division_12-1;
		return resYears;
	   }else if(model_12==0 && days>=0){
		resYears=division_12;
		return resYears;
	   }else if(model_12<6 && model_12>0){
		resYears=division_12;
		return resYears;
	   }else if(model_12==6 && days<0){
		resYears=division_12;
		return resYears;
	   }else if(model_12==6 && days>=0){
		resYears=division_12 + 1;
		return resYears;
	   }else if(model_12>6){
	    resYears=division_12 + 1;
		return resYears;
	   }
	}else if(division_12 > 1){
	    if(model_12==0 && days<0){
			resYears=division_12;
			return resYears;
		}else if(model_12==0 && days>=0){
			resYears=division_12;
			return resYears;
		}else if(0<model_12 && model_12<6){
		    resYears=division_12;
			return  resYears;
		}else if(model_12 == 6 && days<0){
			resYears=division_12;
			return  resYears;
		}else if(model_12 == 6 && days >=0){
			resYears = division_12 + 1;
			return resYears;
		}else if(model_12 >6){
			resYears = division_12 + 1;
			return resYears;
		}else{
			resYears = division_12;
		 	return resYears;
		}
	}else{
		alert("��ǰ����С�ڷ�Ʊ�������ڣ����������룡");
	}
	
}


function setJcnd(checkBoxObj){
	if(checkBoxObj.checked == true){
		document.forms[0].jcnd.value="�޽������";
	}else{
		document.forms[0].jcnd.value="";
	}
}




function doSubmitForm(operationType)
{
	var hasMfSkzsxx = '<bean:write name="ClfswjghdxxlrForm" property="hasMfSkzsxx"/>';
	var hasMfFpdkxx = '<bean:write name="ClfswjghdxxlrForm" property="hasMfFpdkxx"/>';
	
	
	if(operationType=="ToPrint"){
		//�ж��Ƿ����ֱ�Ӵ�ӡ
		//(1)�����Ӫҵ˰���շ�ʽ�������������ҡ���������˰���շ�ʽ�������������ҡ�������ֵ˰���շ�ʽ���ǲ�������������ֱ�Ӵ�ӡ�����������ѹ�ס����˰�걨�˶���������Ʊ������������ҽ�ȫ������
		//(2) ������������1�������ж��Ƿ�������˰���걨��Ϣ������У����ӡ�����û�У�����ʾ����Ҫ�Ƚ����걨���ܴ�ӡ
		var free_yyszsfs_checked  = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';//Ӫҵ˰���շ�ʽ
		var free_grsdszsfs_checked = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';//��������˰���շ�ʽ
		
		var free_tdzsszsfs_checked = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';//������ֵ˰���շ�ʽ
		
		
		if((free_yyszsfs_checked != "" && free_yyszsfs_checked != <%=Constants.YSSZSFS_NOT%>)||//Ӫҵ˰���շ�ʽ������Ӫҵ˰
		   (free_grsdszsfs_checked != "" && free_grsdszsfs_checked != <%=Constants.GRSDSZSFS_FREE%>)//��������˰���շ�ʽ:������������˰			
			||
			((free_tdzsszsfs_checked != "" && free_tdzsszsfs_checked != <%=Constants.TDZZSZSFS_FREE%>)&&//������ֵ˰���շ�ʽ:����������ֵ˰
		      (free_tdzsszsfs_checked != "" && free_tdzsszsfs_checked != <%=Constants.TDZZSZSFS_NOT%>))//������ֵ˰���շ�ʽ:������������ֵ˰
			){
			//���û������˰��������Ϣ
			if(hasMfSkzsxx != null && hasMfSkzsxx != "" && hasMfSkzsxx != "true"){
				alert("���κ˶���Ҫ�Ƚ����걨���ܴ�ӡ�����Ƚ��롾����˰�����ա������������˰���걨��");
				return false;				
			}
		}
		document.forms[0].target="_blank";
	}else{
		//beforeGetValue();
		document.forms[0].target="";
	}
	
	if(operationType=="Query")
    {
        if(document.forms[0].htbh.value=="")
		{
			alert("��������Ӧ�Ĳ�ѯ������");
			document.forms[0].htbh.focus();
			return false;
		}
        document.forms[0].sbbh.value="";
        
    }
	
	
	if(operationType=="Save" || operationType=="Delete"){
		if(hasMfSkzsxx != null && hasMfSkzsxx != "" && hasMfSkzsxx == "true"){
			alert("�������Ϣ�Ѿ�������˰���걨��Ϣ�����ܽ���ɾ�����޸ģ�");
			return false;
		}
		
		if(hasMfFpdkxx != null && hasMfFpdkxx != "" && hasMfFpdkxx == "true"){
			alert("�������Ϣ�Ѿ���������Ʊ������Ϣ�����ܽ���ɾ�����޸ģ�");
			return false;
		}		
	}
	
	
	if(operationType=="Delete"){
		if(document.forms[0].sbbh.value =="" ){
			alert("�޺�ͬ�ɼ���Ϣ������ɾ����");
			return false;
		}
		
		if (!confirm("���Ĳ����ǣ���ɾ�������Ƿ������"))
	    	return false;
	}
	
	if(operationType=="Save")
	{
		//added by zhangj start
		if(document.forms[0].fwhdlxdm.value =="" ){
			alert("δѡ���ݺ˶����ͣ����ܱ��棡");
			return false;
		}
		//������Ȩ��ת������Ϊ[��������]ʱ������Ȩ��ת����ϸΪ��ѡ��
		if(document.forms[0].fwqszylx.value =="04" && (document.forms[0].qszyxsmxdm.value =="" || document.forms[0].qszyxsmxdm.value ==null)){
		
			alert("����Ȩ��ת������Ϊ[��������]������Ȩ��ת����ϸΪ��ѡ�");
			return false;
		}
		if(document.getElementById("tdzzssbfs_GFFP").checked ==true){
			if(document.forms[0].ygffpje.value<=0){
				alert("������ֳ˰�걨��ʽΪ[�ṩ������Ʊ]��ԭ������Ʊ����������㣡");
				return false;
			}
			if(document.forms[0].fpszrq.value==""){
				alert("������ֳ˰�걨��ʽΪ[�ṩ������Ʊ]����Ʊ�������ڲ���Ϊ�գ�");
				return false;
			}
		}
		if(document.getElementById("tdzzssbfs_GFQSP").checked ==true){
			if(document.forms[0].qdfcqsje.value==""){
				alert("������ֳ˰�걨��ʽΪ[�ṩ������˰Ʊ]��ȡ�÷��ز�ʱ�����ɵ���˰����Ϊ�գ�");
				return false;
			}
			if(document.forms[0].yqspfwjsjg.value==""){
				alert("������ֳ˰�걨��ʽΪ[�ṩ������˰Ʊ]��ԭ��˰Ʊ���ݼ�˰�۸���Ϊ�գ�");
				return false;
			}
		}
		if(document.getElementById("tdzzssbfs_PGBG").checked ==true){
			if(document.forms[0].qdtdsyqzfje.value==""){
				alert("������ֳ˰�걨��ʽΪ[�ṩ��������]��ȡ������ʹ��Ȩ��֧���Ľ���Ϊ�գ�");
				return false;
			}
		}
		if(document.all.yhszsfs[0].checked ==false&&document.all.yhszsfs[1].checked ==false){
				alert("��ѡ��ӡ��˰���շ�ʽ��");
				document.all.yhszsfs[0].focus();				
				return false;
		}
		if(document.all.hlfy.value!=""&&document.all.hlfy.value!=null){
			if(!checkNumber(document.all.hlfy.value,15,2,true)){
            	alert("������ñ���Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].hlfy.focus();
            	return false;
        	}
		}

		//added by zhangj end
		if(document.forms[0].sbbh.value =="" ){
			alert("�޺�ͬ�ɼ���Ϣ�����ܱ��棡");
			return false;
		}
		//1.�ж�������ǿռ�����ֵ�����ڸ�ʽ
		if(ygffpje.value=="")
		{
			alert("ԭ������Ʊ����Ϊ�գ�");
			document.forms[0].ygffpje.focus();
			return false;
		}
		else
		{
			if(!checkNumber(ygffpje.value,15,2,true))
        	{
            	alert("ԭ������Ʊ������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].ygffpje.focus();
            	return false;
        	}
		}
		
		if(gfzmrq.value=="")
		{
			alert("����֤�����ڲ���Ϊ�գ�");
			document.forms[0].gfzmrq.focus();
			return false;
		}
		else
		{
			if(!checkDate(document.forms[0].gfzmrq.value))
        	{
            	alert("����֤�����ڲ��Ϸ������޸ģ�");
            	document.forms[0].gfzmrq.focus();
            	return false;
        	}
    	}
		
		if(ptzfzgxj.value==""|| ptzfzgxj.value=="0"||ptzfzgxj.value=="0.00")
		{
			alert("����ͨס������޼ۡ�����Ϊ��,��Ҫѡ�񡰷����������������б�������ã�");
			document.forms[0].fwszqydm.focus();
			return false;
		}
		else
		{
			if(!checkNumber(ptzfzgxj.value,15,2,true))
        	{
            	alert("��ͨס������޼۱���Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].ptzfzgxj.focus();
            	return false;
        	}
		}
		
		if(jsje.value=="")
		{
			alert("�˶���˰����Ϊ�գ�");
			document.forms[0].jsje.focus();
			return false;
		}
		else
		{
			if(!checkNumber1(jsje.value,15,2,true))
        	{
            	alert("�˶���˰������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ������㣡");
            	document.forms[0].jsje.focus();
            	return false;
        	}
		}
		
    	//new add �������
    	var jcnd = document.forms[0].jcnd;
    	if(jcnd.value =="" || jcnd.value == null){
    		alert("���������������Ϊ�գ�");
    		jcnd.focus();
    		return false;
    		
    	}else{
    		
    		var wjcnd = document.all.wjcnd;//�޽������
			if(wjcnd.checked == false && !checkNumber1(jcnd.value,4,0,true))
        	{
            	alert("���������������д��ʽ���ԣ���ʽӦΪYYYY����1970��");
            	jcnd.select();
            	return false;
        	}   		
    		
    	}
    	
    	//ÿƽ�׺˶����� 
    	var mpmhdjg = document.all.mpmhdjg;
    	if(mpmhdjg.value =="" || mpmhdjg.value == null){
    	    alert("������ÿƽ�׺˶�����!");
    	 	mpmhdjg.focus();
    		//mpmhdjg.value="0.00";
    		return false;
    	}else{
			if(mpmhdjg.value!="0"&&mpmhdjg.value!="0.00"&&!checkNumber1(mpmhdjg.value,15,2,true))
        	{
            	alert("��ÿƽ�׺˶����ۡ�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ������㣡");
            	mpmhdjg.focus();
            	return false;
        	}else if("0.00"==changeJE(mpmhdjg.value)){
    		if(!confirm("��ȷ��ÿƽ���׺˶�����Ϊ"+mpmhdjg.value+"��")){
    			mpmhdjg.focus();
    			return false;
    		}
    	}   		
    		
    	}
    	
	
		//2.�жϵ�ѡ�Ƿ�ѡ��
		if(checkradio(jtwyshyhbz_checked,<%=Constants.ONLY_ROOM_YES%>) == false && 
		   checkradio(jtwyshyhbz_checked,<%=Constants.ONLY_ROOM_NOT%>) == false)
		{
			alert("��ѡ����Ӧ�ġ��Ƿ�Ϊ��ͥΨһ�����÷�����");
			return false;
		}
		if(checkradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_LOW%>) == false && 
		   checkradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_HIGH%>) == false)
		{
			alert("��ѡ����Ӧ�ġ���Ȩ֤��ע�����������");
			return false;
		}
		if(checkradio(fwrjl_checked,<%=Constants.FWRJL_LOW%>) == false && 
		   checkradio(fwrjl_checked,<%=Constants.FWRJL_HIGH%>) == false)
		{
			alert("��ѡ����Ӧ�ġ������ݻ��ʡ���");
			return false;
		}
		if(checkradio(hfbz_checked,<%=Constants.HFBZ_PT%>) == false && 
		   checkradio(hfbz_checked,<%=Constants.HFBZ_NOTPT%>) == false &&  
		   checkradio(hfbz_checked,<%=Constants.HFBZ_FZF%>) == false &&
		   checkradio(hfbz_checked,<%=Constants.HFBZ_GRWCZY%>) == false &&
		   checkradio(hfbz_checked,<%=Constants.HFBZ_GRJC%>) == false)
		{
			alert("��ѡ����Ӧ�ġ����ֱ�׼����");
			return false;
		}
		if(checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_THREE%>) == false && 
		   checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_THREETOFOIVE%>) == false && 
		   checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_FIVE%>) == false)
		{
			alert("��ѡ����Ӧ�ġ�ס��ʹ��ʱ�䡱��");
			return false;
		}
		if(checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_NOT%>) == false && 
		   checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_ALL%>) == false && 
		   checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_MINUS%>) == false)
		{
			alert("��ѡ����Ӧ�ġ�Ӫҵ˰���շ�ʽ����");
			return false;
		}
		if(checkradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_FREE%>) == false && 
		   checkradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_ZS%>) == false)
		{
			alert("��ѡ����Ӧ�ġ���������˰���շ�ʽ����");
			return false;
		}
		
		//������ֵ˰�걨��ʽ�����շ�ʽ
		var GFFPZS_tdzsszsfs_checked = document.getElementsByName("tdzsszsfs")[0].checked;
		var HDZS_tdzsszsfs_checked = document.getElementsByName("tdzsszsfs")[1].checked;
		var PGBGZS_tdzsszsfs_checked = document.getElementsByName("tdzsszsfs")[2].checked;
		var FREE_tdzsszsfs_checked = document.getElementsByName("tdzsszsfs")[4].checked;
		var NOT_tdzsszsfs_checked = document.getElementsByName("tdzsszsfs")[3].checked;			
		if(document.getElementById("tdzzssbfs_GFFP").checked==false &&
			document.getElementById("tdzzssbfs_GFQSP").checked==false &&		
			document.getElementById("tdzzssbfs_GFHT").checked==false &&
			document.getElementById("tdzzssbfs_PGBG").checked==false &&
			document.getElementById("tdzzssbfs_WPJ").checked==false &&
			document.getElementById("tdzzssbfs_FLWS").checked==false &&
		   (GFFPZS_tdzsszsfs_checked == true || HDZS_tdzsszsfs_checked == true || PGBGZS_tdzsszsfs_checked == true))
		{
			alert("��ѡ����Ӧ�ġ�������ֵ˰�걨��ʽ����");
			return false;
		}		
				
 		if(GFFPZS_tdzsszsfs_checked == false && 
 		   PGBGZS_tdzsszsfs_checked == false&& 
		   HDZS_tdzsszsfs_checked == false && 
		   FREE_tdzsszsfs_checked == false && 
		   NOT_tdzsszsfs_checked == false)
		{
			alert("��ѡ����Ӧ�ġ�������ֵ˰���շ�ʽ����");
			return false;
		}
		
		
		if(checkradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HTJG%>) == false && 
		   checkradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HDJSJG%>) == false)
		{
			alert("��ѡ����Ӧ�ġ���˰����ȷ�Ϸ�ʽ����");
			return false;
		}
		
		//3.�жϲ������������ʽ
      	if(qdfcqsje.value !="")
		{
			if(!checkNumber(qdfcqsje.value,15,2,true))
        	{
            	alert("��ȡ�÷��ز�ʱ�����ɵ���˰������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].qdfcqsje.focus();
            	return false;
        	}
		}
		if(qdfcyhsje.value!="")
		{
			if(!checkNumber(qdfcyhsje.value,15,2,true))
        	{
            	alert("��ȡ�÷��ز�ʱ�����ɵ�ӡ��˰������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].qdfcyhsje.focus();
            	return false;
        	}
		}
		if(zfpgjg.value!="")
		{
			if(!checkNumber(zfpgjg.value,15,2,true))
        	{
            	alert("��ס�������۸񡱱���Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].zfpgjg.focus();
            	return false;
        	}
		}
		if(zfzxfy.value!="")
		{
			if(!checkNumber(zfzxfy.value,15,2,true))
        	{
            	alert("��ס��װ�޷��á�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].zfzxfy.focus();
            	return false;
        	}
		}
		if(zfdklx.value!="")
		{
			if(!checkNumber(zfdklx.value,15,2,true))
        	{
            	alert("��ס��������Ϣ������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].zfdklx.focus();
            	return false;
        	}
		}
		if(sxf.value!="")
		{
			if(!checkNumber(sxf.value,15,2,true))
        	{
            	alert("�������ѡ�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].sxf.focus();
            	return false;
        	}
		}
		if(gzf.value!="")
		{
			if(!checkNumber(gzf.value,15,2,true))
        	{
            	alert("����֤�ѡ�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].gzf.focus();
            	return false;
        	}
		}
		
		if(tdcrj.value !=""){
			if(!checkNumber(tdcrj.value,15,2,true))
        	{
            	alert("�����س��ý𡱱���Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            	document.forms[0].tdcrj.focus();
            	return false;
        	}			
		}
		
    	if(document.getElementById("tdzzssbfs_PGBG").checked==true)
    	{
			if(qdtdsyqzfje.value!="")
			{
				if(!checkNumber(qdtdsyqzfje.value,15,2,true))
        		{
            		alert("��ȡ������ʹ��Ȩ��֧���Ľ�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            		document.forms[0].qdtdsyqzfje.focus();
            		return false;
        		}
			}
			
			if(jfpgjg.value!="")
			{
				if(!checkNumber(jfpgjg.value,15,2,true))
        		{
            		alert("���ɷ���������������۸񡱱���Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            		document.forms[0].jfpgjg.focus();
            		return false;
        		}
			}
			
			if(jgpgfy.value!="")
			{
				if(!checkNumber(jgpgfy.value,15,2,true))
        		{
            		alert("���۸��������á�����Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
            		document.forms[0].jgpgfy.focus();
            		return false;
        		}
			}
    	}
    	
    	//��������ֵ˰�걨��ʽ��ѡ���ṩ������Ʊ �� ��Ʊ�������� �� ����Ӽ����� ����
    	if(document.getElementById("tdzzssbfs_GFFP").checked==true){
    		var fpszrqObj = document.forms[0].fpszrq;
    		var anjjseObj = document.forms[0].anjjse; 
    		
    		//�жϡ���Ʊ�������ڡ�
    		if(fpszrqObj.value == "" || fpszrqObj.value == null){
    			alert("��������ֵ˰�걨��ʽ���Ѿ�ѡ���ṩ������Ʊ�����򡰷�Ʊ�������ڡ�����Ϊ�գ�");
    			fpszrqObj.focus();
    			return false;
    		}else{ 
    			if(!checkDate(fpszrqObj.value))
            	{
                	alert("����Ʊ�������ڡ����Ϸ������޸ģ�");
                	fpszrqObj.select();
                	return false;
            	}
    		}
    		
    		//�жϡ�����Ӽ����
    		if(anjjseObj.value == "" || anjjseObj.value == null){
    			alert("��������ֵ˰�걨��ʽ���Ѿ�ѡ���ṩ������Ʊ�����򡰰���Ӽ��������Ϊ�գ�");
    			anjjseObj.focus();
    			return false;
    		}else{ 
    			if(!checkNumber(anjjseObj.value,15,2,true))
            	{
    				alert("������Ӽ��������Ϊ���֣�\nС�����ĳ���Ϊ2λ��\n������ֵ����ڵ����㣡");
                	anjjseObj.select();
                	return false;
            	}
    			
				if(parseFloat(anjjseObj.value) < parseFloat(old_anjjse)){
					alert("������Ӽ������д����ֵӦ�ô��ڵ��� "+old_anjjse+",��С�ڵ���"+old_anjjse_after_30_days);
					anjjseObj.select();
					return false;					
				}
				
				if(parseFloat(anjjseObj.value) > parseFloat(old_anjjse_after_30_days)){
					alert("������Ӽ������д����ֵӦ�ô��ڵ��� "+old_anjjse+",��С�ڵ���"+old_anjjse_after_30_days);
					anjjseObj.select();
					return false;					
				}    			
    		}    		
    	}else{
    		var anjjseObj = document.forms[0].anjjse; 
    		if(anjjseObj.value != "" && anjjseObj.value !="0.00" && anjjseObj.value !="0"){
    				alert("��������ֵ˰�걨��ʽ��δѡ���ṩ������Ʊ,���԰���Ӽ��������Ϊ0.00");
					anjjseObj.select();
					return false;	
    		}
    	}  	  	
    	
    	if(document.all.jssrqrfs[<%=Constants.JSSRQRFS_HDJSJG%>].checked==true)
    	{
      		putObjectValue(document.forms[0].jsjeSubmit,"",document.all.jsje.value); 
    	}
    	
    	
    	//���(1)������ֵ˰���շ�ʽΪ :����������ֵ˰,��(2)��������ֵ˰�걨��ʽΪ��,��Ĭ��������ֵ˰�걨��ʽΪ �˶�����
 //   	var free_tdzzssbfs_checked = document.all.tdzsszsfs[<%=Constants.TDZZSZSFS_FREE%>].checked;
    	
 //   	if(checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_GFFP%>) == false && 
 //   	   checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_PGBG%>) == false &&
    	 //  checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_HDZS%>) == false &&
 //   	   free_tdzzssbfs_checked == true){
    	//	document.all.tdzzssbfs_chBox[<%=Constants.TDZSS_SB_HDZS%>].checked=true;
  //  	}
    	
		//added by zhangj
 		//��   tdzsszsfsSubmit��ֵ	
    	for(var i=0;i<document.all.tdzsszsfs.length;i++){
    		if(document.all.tdzsszsfs[i].checked==true){
    			document.all.tdzsszsfsSubmit.value=document.all.tdzsszsfs[i].value;
    		}
    	}
    	//��hfbzSubmit��ֵ
    	for(var i=0;i<document.all.hfbz.length;i++){
    		if(document.all.hfbz[i].checked==true){
    			document.all.hfbzSubmit.value=document.all.hfbz[i].value;
    		}
    	}
    	//��yyszsfsSubmit��ֵ
    	for(var i=0;i<document.all.yyszsfs.length;i++){
    		if(document.all.yyszsfs[i].checked==true){
    			document.all.yyszsfsSubmit.value=document.all.yyszsfs[i].value;
    		}
    	}
    	//��grsdszsfsSubmit��ֵ
    	for(var i=0;i<document.all.grsdszsfs.length;i++){
    		if(document.all.grsdszsfs[i].checked==true){
    			document.all.grsdszsfsSubmit.value=document.all.grsdszsfs[i].value;
    		}
    	}
     	//��yhszsfsSubmit��ֵ
    	for(var i=0;i<document.all.yhszsfs.length;i++){
    		if(document.all.yhszsfs[i].checked==true){
    			document.all.yhszsfsSubmit.value=document.all.yhszsfs[i].value;
    		}
    	}   
    		
    	if (!confirm("�Ƿ񱣴���д���ݣ���ȷ�ϣ�"))
    	return false;
    	
    	document.all.isQuery.value=true;
	}
	
	document.all.tdzzssbfs.value=chBox_toString("tdzzssbfs_chBox");
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}


//����������ֵ˰�걨��ʽ
//�� 
//  A. ȡ������ʹ��Ȩ��֧���Ľ�������λС���������ͣ���������ֵ˰�걨��ʽѡ���ṩ�������桿ʱ����¼�룬������¼�룻
//  B. �ɷ���������������۸񣺱�����λС����������, ��������ֵ˰�걨��ʽѡ���ṩ�������桿ʱ����¼�룬������¼�룻
//  C. �۸��������ã�������λС����������, ��������ֵ˰�걨��ʽѡ���ṩ�������桿ʱ����¼�룬������¼�롣��
//���Ƽ�˰������
//  A.�������˰����ȷ�Ϸ�ʽ��ѡ�񡾺�ͬ�۸���Ϊ��ά���ͬ�۸񣬲����޸�;
//  B.�������˰����ȷ�Ϸ�ʽ��ѡ�񡾺˶���˰�۸񡿣����˹�¼�룬������λС�������ұ���¼��.
function changeTdzzsAndJssrqr()
{
	//����������ֵ˰�걨��ʽ
	var isQuery=document.all.isQuery.value;
	var value=hasValue(document.all.tdzzssbfs.value,document.getElementById("tdzzssbfs_PGBG").value);
    if((document.getElementById("tdzzssbfs_PGBG").checked==true) || (isQuery=="true" && value==true))
    {
      	document.all.qdtdsyqzfje.disabled=false;
      	document.all.jfpgjg.disabled=false;
      	document.all.jgpgfy.disabled=false;
    }else{
    	document.all.qdtdsyqzfje.disabled=true;
      	document.all.jfpgjg.disabled=true;
      	document.all.jgpgfy.disabled=true;
      	document.all.qdtdsyqzfje.value=changeJE(0.00);
      	document.all.jfpgjg.value=changeJE(0.00);
      	document.all.jgpgfy.value=changeJE(0.00);
    }   
   
    //���Ƽ�˰������
	setJsje_jsfs();
}



//��ʾԭ������Ʊ�����ÿƽ�׽��׵��ۡ��жϲ�Ȩ֤��ע���������Χ�����ƻ��ֱ�׼����˰����ȷ�Ϸ�ʽ
function getView()
{	//added by zhangj
	if(document.all.yqspfwjsjg.value==""||document.all.yqspfwjsjg.value==null){
		document.all.yqspfwjsjg.value=changeJE(0.00);
	}
	/**
	 * 1.��ʾԭ������Ʊ���
	 */
	var inner_ygffpje = document.all.ygffpje.value;
	if(inner_ygffpje =='NaN' || inner_ygffpje =="" )
		inner_ygffpje = changeJE(0.00);
	//alert(inner_ygffpje);
	var showYgffpje = document.getElementById("showYgffpje");
    showYgffpje.innerText = changeJE(inner_ygffpje);
    
    /**
	 * 2.��ʾ"����:�ɷ��������������۸�"
	 */
	var inner_jfpgjg = document.all.jfpgjg.value;
	if(inner_jfpgjg =='NaN' || inner_jfpgjg =="" )
		inner_jfpgjg = changeJE(0.00);
	//alert(inner_jfpgjg);
	var showJfpgjg = document.getElementById("showJfpgjg");
    showJfpgjg.innerText = changeJE(inner_jfpgjg);	
    

    /*
    *���У�ȡ�÷��ز�ʱ�����ɵ���˰���
    *
    */
	var inner_qdfcqsje = document.all.qdfcqsje.value;
	if(inner_qdfcqsje =='NaN' || inner_qdfcqsje =="" )
		inner_qdfcqsje = changeJE(0.00);
	//alert(inner_jfpgjg);
	var showQdfcqsje = document.getElementById("showQdfcqsje");
	showQdfcqsje.innerText = changeJE(inner_qdfcqsje);	    
    
    /*
     *���У�ȡ�÷��ز�ʱ�����ɵ�ӡ��˰���
     *
     */
 	var inner_qdfcyhsje = document.all.qdfcyhsje.value;
 	if(inner_qdfcyhsje =='NaN' || inner_qdfcyhsje =="" )
 		inner_qdfcyhsje = changeJE(0.00);
 	//alert(inner_jfpgjg);
 	var showQdfcyhsje = document.getElementById("showQdfcyhsje");
 	showQdfcyhsje.innerText = changeJE(inner_qdfcyhsje);	
     
	
	/**
	 * 3.���ÿƽ�׽��׵���
	 * ����ͨ����ͬ�ܼ۳��Խ��������������λС���Ľ��
	 */
	var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
	var fwjzmj = '<bean:write name="ClfswjghdxxlrForm" property="fwjzmj"/>';
	var inner_mpmjydj = changeJE(Math.round(parseFloat(htzj)/parseFloat(fwjzmj)*100.00)/100.00);
	if(inner_mpmjydj =='NaN.00')
		inner_mpmjydj = changeJE(0.00);
	
	//��ʾÿƽ�׽��׵��۽��
	var showMpmjydj = document.getElementById("showMpmjydj");
    showMpmjydj.innerText = changeJE(inner_mpmjydj);
    putObjectValue(document.forms[0].mpmjydj,"",changeJE(inner_mpmjydj));
    
    //���ÿƽ�׽��׵��۽��
    var hidd_mpmjydj = document.getElementsByName("hidd_mpmjydj");
	hidd_mpmjydj= changeJE(inner_mpmjydj);
	
	/**
	 * 4.�жϲ�Ȩ֤��ע���������Χ��ͨ�������ݽ����������ֵ�ж���
	 */
	if(fwjzmj != "")
	{
		if(fwjzmj-140.00 > 0)
		{
			initradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_HIGH%>);
			putObjectValue(document.forms[0].cqzbzjzmjflSubmit,"",<%=Constants.CQZBZ_JLMX_HIGH%>);
		}
		else
		{
			initradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_LOW%>);
			putObjectValue(document.forms[0].cqzbzjzmjflSubmit,"",<%=Constants.CQZBZ_JLMX_LOW%>);
		}
	}
	/**
	*5.������ͨס������޼�
	*/
	var fwszqyValue = document.forms[0].fwszqydm.value;
	if(fwszqyValue != ""){
		document.forms[0].ptzfzgxj.value=changeJE(getPtfwszqyzgxj(fwszqyValue));
		putObjectValue(document.forms[0].ptzfzgxjSubmit,"",changeJE(getPtfwszqyzgxj(fwszqyValue)));
	}else{
		
		
	}
	/**
	*5.������ͨס��������ܼۣ��������������,ADDED BY ZHANGJ 2014.10.13
	*/
	if(fwszqyValue != ""){
		//document.forms[0].fwszqyje.value=changeJE(getFwszqyje(fwszqyValue));
		putObjectValue(document.forms[0].fwszqyjeSubmit,"",changeJE(getFwszqyje(fwszqyValue)));
	}
	/**
	*   
	   ���ü�˰����ȷ�Ϸ�ʽ
	*/
	var mpmhdjgValue = document.forms[0].mpmhdjg.value;//ÿƽ�׺˶��۸�
	if(mpmhdjgValue=='NaN'||mpmhdjgValue =="" || mpmhdjgValue == null)
		mpmhdjgValue = changeJE(0.00);
	
	//alert(inner_mpmjydj+"---"+mpmhdjgValue);
	if(parseFloat(inner_mpmjydj) > parseFloat(mpmhdjgValue)){
		initradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HTJG%>);
		putObjectValue(document.forms[0].jssrqrfsSubmit,"",<%=Constants.JSSRQRFS_HTJG%>);
	}else{
		initradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HDJSJG%>);
		putObjectValue(document.forms[0].jssrqrfsSubmit,"",<%=Constants.JSSRQRFS_HDJSJG%>);
	}
	
	
	
	/**
	 * 6.���ƻ��ֱ�׼
	 * ����
	      ����ͨס����:���㣨1�� �� ��2������
		   ��1�� A�������ݻ����ڡ�1.0���������ϡ���
	        B�����ݽ������С�ڵ���140ƽ�ס�
	        //C����ÿƽ�׽��׵��ۡ�����1.2С�ڵ��ڡ���ͨס������޼ۡ��� ��һ�������ȡ����
	        C��ȡ��ÿƽ�׽��׵��ۡ��͡��˶����ۡ��е����ֵ��Ҫ�����ֵС�ڵ��ڡ���ͨס������޼ۡ���
	      
	           
	        ��2�����߲�Ȩ֤����ע�ķ�������Ϊ���״����н��׵��ѹ�����������Σ�Ļ�Ǩ�������������缯�ʽ���ס�����������ӷ����������ӷ��������̻��������ũ����ס��������ҲΪ����ͨס����
	        
	        
	        ������ͨס�����������ϣ�������Ϊ����ͨס����
	  
	 */
	var fwrjl_high_checked = document.all.fwrjl[<%=Constants.FWRJL_HIGH%>].checked;
	var cqzbzjzmjfl_low_checked = document.all.cqzbzjzmjfl[<%=Constants.CQZBZ_JLMX_LOW%>].checked;
	var ptzfzgxjValue =  document.forms[0].ptzfzgxj.value;
	
	var fwcqzbzzflxdm = document.forms[0].fwcqzbzzflxdm.value;
	
	
	if(ptzfzgxjValue =='NaN')
		ptzfzgxjValue = changeJE(0.00);
	
	//����ס����Ϊ����ס����added by zhangj 2014.10.17
	//����2��
	var is6Type = false;//��������ס��
	if(fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_06%> || 
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_07%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_08%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_09%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_10%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_11%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_13%>){
		
		is6Type = true;
	}
	
	
	
	//(����1)
	if(ptzfzgxjValue != "" && (checkradio(fwrjl_checked,<%=Constants.FWRJL_HIGH%>) == true || 
	   checkradio(fwrjl_checked,<%=Constants.FWRJL_LOW%>) == true) && (
	   checkradio(cqzbzjzmjfl_checkes,<%=Constants.CQZBZ_JLMX_HIGH%>) == true || 
	   checkradio(cqzbzjzmjfl_checkes,<%=Constants.CQZBZ_JLMX_LOW%>) == true))
	{
		
		//var mpmjydj_ptzfzgxj = changeJE(Math.round(parseFloat(inner_mpmjydj) * 1.20 * 100.00)/100.00) - ptzfzgxj;  ��һ�����
		
		var maxDJ = inner_mpmjydj;//��󵥼�
		if(parseFloat(maxDJ) < parseFloat(mpmhdjgValue)){
			maxDJ = mpmhdjgValue;
		}
		
		var mpmjydj_ptzfzgxj = parseFloat(maxDJ) - parseFloat(ptzfzgxjValue);
		
		//alert(fwrjl_high_checked+" : "+cqzbzjzmjfl_low_checked+" : "+ptzfzgxjValue+"������"+mpmjydj_ptzfzgxj);
		//added by zhangj
//		if(document.all.isQuery.value=="false"){
//			if((fwrjl_high_checked==true && cqzbzjzmjfl_low_checked==true && !(mpmjydj_ptzfzgxj > 0)) || is6Type)
//   		{
//				initradio(hfbz_checked,<%=Constants.HFBZ_PT%>);
//				putObjectValue(document.forms[0].hfbzSubmit,"",<%=Constants.HFBZ_PT%>);
//    		}
//    		else
//    		{
//    			initradio(hfbz_checked,<%=Constants.HFBZ_NOTPT%>);
//    			putObjectValue(document.forms[0].hfbzSubmit,"",<%=Constants.HFBZ_NOTPT%>);
//    		}		
//		}

	}
	

	
	
	/**
	 * 7.��ʾ�������
	 * ����ס��װ�޷���+ס��������Ϣ+������+��֤��
	 */
	var zfzxfyValue = document.all.zfzxfy.value;
	var zfdklxValue = document.all.zfdklx.value;
	var sxfValue = document.all.sxf.value;
	var gzfValue = document.all.gzf.value;
	var tdcrjValue = document.all.tdcrj.value;
	//alert("1.  "+zfzxfyValue+" : "+zfdklxValue+" : "+sxfValue+" : "+gzfValue);
	if(zfzxfyValue =='NaN' || zfzxfyValue =="" )
		zfzxfyValue = changeJE(0.00);
	if(zfdklxValue =='NaN' || zfdklxValue =="" )
		zfdklxValue = changeJE(0.00);
	if(sxfValue =='NaN' || sxfValue =="" )
		sxfValue = changeJE(0.00);
	if(gzfValue =='NaN' || gzfValue =="" )
		gzfValue = changeJE(0.00);	
	if(tdcrjValue =='NaN' || tdcrjValue =="" )
		tdcrjValue = changeJE(0.00);	
	//alert("2.  "+zfzxfyValue+" : "+zfdklxValue+" : "+sxfValue+" : "+gzfValue);
	//var hlfyValue = Math.round((parseFloat(zfzxfyValue)+parseFloat(zfdklxValue)+parseFloat(sxfValue)+parseFloat(gzfValue) + parseFloat(tdcrjValue))* 100.00)/100.00;
	//alert("3.  "+hlfyValue);
	//if(hlfyValue =='NaN.00')
	//	hlfyValue = changeJE(0.00);
	//alert("4.  "+hlfyValue);
	//��ʾ������ý��,added by zhangj
	//var showHlfy = document.getElementById("showHlfy");
    //showHlfy.innerText = changeJE(hlfyValue);
    //putObjectValue(document.forms[0].hlfy,"",changeJE(hlfyValue));
    
    //��ú�����ý��
    //var hidd_hlfy = document.getElementsByName("hidd_hlfy");
	//hidd_hlfy= changeJE(hlfyValue);
	
if(document.all.isQuery.value!="true"){
	/**
		added by zhangj
	 * 8.����Ӫҵ˰���շ�ʽ
	 * ����
	 	A.���������Ȩ��ת�����͡�Ϊ���������롿������������ϸΪA��B��C��D�����������࣬��Ӫҵ˰���շ�ʽΪ������Ӫҵ˰����������������Ӫҵ˰����
		(a) ���Ʋ��ָ
		(b)�޳�������ż����ĸ����Ů���游ĸ�����游ĸ������Ů��������Ů���ֵܽ��á�
		(c)�޳��������е�ֱ�Ӹ���������������ĸ����˻��������ˡ�
		(d)���ݲ�Ȩ����������������ȡ�÷��ݲ�Ȩ�ķ����̳��ˡ������̳��˻����������ˡ�
		B������걨��ʽѡ�񺬡��ṩ������Ʊ������Ӫҵ˰���շ�ʽΪ���������Ӫҵ˰����
		C������걨��ʽѡ��ֻ�����ṩ�������顿����Ӫҵ˰���շ�ʽΪ�˹�ѡ���ǡ�������ա����ǡ�ȫ�����ա���
		D.����������������ȫ��Ϊ��ȫ������Ӫҵ˰����
		E��������Ӫҵ˰�������������ȫ������Ӫҵ˰�������һ�¡�
	 */
	document.all.yyszsfs[<%=Constants.YSSZSFS_NOT%>].disabled=true;
	document.all.yyszsfs[<%=Constants.YSSZSFS_ALL%>].disabled=true;
	document.all.yyszsfs[<%=Constants.YSSZSFS_MINUS%>].disabled=true;
	if(document.all.fwqszylx.value=="04" && (document.all.qszyxsmxdm.value=="01" ||document.all.qszyxsmxdm.value=="02" ||document.all.qszyxsmxdm.value=="03"||document.all.qszyxsmxdm.value=="04"||document.all.qszyxsmxdm.value=="05" )){
			initradio(yyszsfs_checked,<%=Constants.YSSZSFS_NOT%>);
			putObjectValue(document.forms[0].yyszsfsSubmit,"",<%=Constants.YSSZSFS_NOT%>);
	}else if(document.getElementById("tdzzssbfs_GFFP").checked==true){
			initradio(yyszsfs_checked,<%=Constants.YSSZSFS_MINUS%>);
    		putObjectValue(document.forms[0].yyszsfsSubmit,"",<%=Constants.YSSZSFS_MINUS%>);
	}else if(document.getElementById("tdzzssbfs_GFFP").checked==false &&
		document.getElementById("tdzzssbfs_GFQSP").checked==false &&		
		document.getElementById("tdzzssbfs_GFHT").checked==false &&
		document.getElementById("tdzzssbfs_PGBG").checked==false &&
		document.getElementById("tdzzssbfs_WPJ").checked==false &&
		document.getElementById("tdzzssbfs_FLWS").checked==true ){
		document.all.yyszsfs[<%=Constants.YSSZSFS_ALL%>].disabled=false;
		document.all.yyszsfs[<%=Constants.YSSZSFS_MINUS%>].disabled=false;
		document.all.yyszsfs[<%=Constants.YSSZSFS_NOT%>].checked=false;
	}else{
	    	initradio(yyszsfs_checked,<%=Constants.YSSZSFS_ALL%>);
    		putObjectValue(document.forms[0].yyszsfsSubmit,"",<%=Constants.YSSZSFS_ALL%>);
	}
	

	/**
	  	added by zhangj
	  ��������˰���Ʒ�ʽ��
	 * ���� 	
	 	��������Ȩ��ת�����͡�Ϊ�����롱����������ϸΪ��BCD�����������Ϊ��������������˰����������Ϊ�����ո�������˰����
		B���޳�������ż����ĸ����Ů���游ĸ�����游ĸ������Ů��������Ů���ֵܽ��á�
		C���޳��������е�ֱ�Ӹ���������������ĸ����˻��������ˡ�
		D�����ݲ�Ȩ����������������ȡ�÷��ݲ�Ȩ�ķ����̳��ˡ������̳��˻����������ˡ�
	 */
	if(document.all.fwqszylx.value=="04" && (document.all.qszyxsmxdm.value=="02" ||document.all.qszyxsmxdm.value=="03"||document.all.qszyxsmxdm.value=="04"||document.all.qszyxsmxdm.value=="05" )){
			initradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_FREE%>);
			putObjectValue(document.forms[0].grsdszsfsSubmit,"",<%=Constants.GRSDSZSFS_FREE%>);
	}else{
	    	initradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_ZS%>);
    		putObjectValue(document.forms[0].grsdszsfsSubmit,"",<%=Constants.GRSDSZSFS_ZS%>);
	}
	
		

	/*
		added by zhangj
	������ֵ˰���Ʒ�ʽ��
	 * ���� 
		A����������Ȩ��ת�����͡�Ϊ���������롿������������ϸ��B��C��D��E����������������ֱϵ������ͬ���ֵܽ��ü������ˡ������ˡ��̳С���˰����ر����ķ��������ˡ�
		����ʹ��Ȩ������ͨ���й����ڷ�Ӫ����������塢���һ��ؽ����ݲ�Ȩ������ʹ��Ȩ���������������������ḣ����������ҵ������Ϊ������������ֵ˰�������������Ϊ�˹�ѡ��
		B���˹�ѡ������շ�ʽ����У�顣	
	*/
	if(document.all.fwqszylx.value=="04" && (document.all.qszyxsmxdm.value=="02" ||document.all.qszyxsmxdm.value=="04" ||document.all.qszyxsmxdm.value=="05"||document.all.qszyxsmxdm.value=="06" )){		
			initradio(tdzsszsfs_checked,<%=Constants.TDZZSZSFS_NOT%>);
			putObjectValue(document.forms[0].tdzsszsfsSubmit,"",<%=Constants.TDZZSZSFS_NOT%>);		
			for(var i=0;i<tdzsszsfs_checked.length;i++){
				tdzsszsfs_checked[i].disabled=true;
			}		
	}else{
			for(var i=0;i<tdzsszsfs_checked.length;i++){
				tdzsszsfs_checked[i].disabled=false;
			}
	}
}
 /* added by zhangj
 	ӡ��˰���Ʒ�ʽ��
  */
// 	if(document.all.yhszsfs[1].checked ==true){
// 		putObjectValue(document.forms[0].yhszsfsSubmit,"",<%=Constants.YHSZSFS_FREE%>);	
// 	}else{
// 		putObjectValue(document.forms[0].yhszsfsSubmit,"",<%=Constants.YHSZSFS_ZS%>);
// 	}	
	
    //���Ƽ�˰������
	setJsje_jsfs();
    
    //����Ӽ�����=��Ʊ���*����*5%
    var sysdate = <%=DateUtils.getDate()%>+"";
    var fpszrq = document.all.fpszrq.value;
    
    if(fpszrq != null && fpszrq != ""){
    	
    	//���Ϸ���
    	if(!checkDate(fpszrq)){
    		alert("��Ʊ�������ڲ��Ϸ�,��ʽӦΪyyyyMMdd,����20130101������!");
    		document.all.fpszrq.select();
    																					
    	    old_anjjse = changeJE(0.00);
    	    document.forms[0].anjjse.value= changeJE(0.00);    	
        	old_anjjse_after_30_days=changeJE(0.00);    	
        	
    		return false;
    	}
    	
    	//��Ʊ�������ڲ��ܴ��ڵ�ǰ����
    	if(parseFloat(fpszrq) > parseFloat(sysdate)){
    		alert("��Ʊ�������ڲ��ܴ��ڵ�ǰ���ڣ�����!");
    		document.all.fpszrq.select();
    																							
    	    old_anjjse = changeJE(0.00);
    	    document.forms[0].anjjse.value= changeJE(0.00);    	
        	old_anjjse_after_30_days=changeJE(0.00);      		
    		
    		return false;
    	}
    	
    	var xcNSIsError = false;
    	var xcNS = getYueShu(sysdate,fpszrq);//ϵͳ���ںͷ�Ʊ���������������
    	var ygffpje = document.all.ygffpje.value;
    	
    	if(ygffpje =='NaN.00'||ygffpje =='NaN'|| ygffpje =="" ){
    		ygffpje = changeJE(0.00);
    	}
    	
    	if(xcNS == 'NaN'){
    		xcNSIsError = true;
    		xcNS=0;
    		
    	}
    	
    	var anjjse = Math.round(parseFloat(ygffpje)*parseFloat(xcNS)*0.05* 100.00)/100.00;
    	//alert("sysdate--"+sysdate+"-fpszrq--"+fpszrq+"::\n"+ygffpje+"*"+xcNS+"*0.05="+anjjse);
    	
    	if(anjjse=='NaN'||ygffpje =='NaN.00'){    												 
    		anjjse = changeJE(0.00);
    	}
    	//��old_anjjse��ֵ
    	    old_anjjse = changeJE(anjjse);    
    	    if(document.all.isQuery.value!="true"){
    	    	document.forms[0].anjjse.value= changeJE(anjjse);
    	    } 
    	    
    	//ϵͳ����30��֮��ļ�����
    	sysdate = <%=DateUtils.getAfter30Days()%>+"";
    	if(!xcNSIsError){
    		xcNS = getYueShu(sysdate,fpszrq);
    	}
    	old_anjjse_after_30_days = changeJE(Math.round(parseFloat(ygffpje)*parseFloat(xcNS)*0.05* 100.00)/100.00);
    }else{																										
	    old_anjjse = changeJE(0.00);
	    document.forms[0].anjjse.value= changeJE(0.00);    	
    	old_anjjse_after_30_days=changeJE(0.00);
    }
   
    //���� ������ֵ˰�걨��ʽ ������Ŀ¼
    changeTdzzsAndJssrqr();
}


function setJsje_jsfs(){
    if(document.all.jssrqrfs[<%=Constants.JSSRQRFS_HTJG%>].checked==true)
    {
    	//document.all.jsje.disabled=true;
      	document.all.jsje.value = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
      	putObjectValue(document.forms[0].jsjeSubmit,"",document.all.jsje.value); 
      	putObjectValue(document.forms[0].jssrqrfsSubmit,"",<%=Constants.JSSRQRFS_HTJG%>);
    }
    if(document.all.jssrqrfs[<%=Constants.JSSRQRFS_HDJSJG%>].checked==true)
    {
    	//document.all.jsje.disabled=false;
    	var fwjzmj = '<bean:write name="ClfswjghdxxlrForm" property="fwjzmj"/>';
    	var mpmhdjg = document.forms[0].mpmhdjg.value;
    	
    	if(fwjzmj==""){
    		fwjzmj=0.00;
    	}
    	
    	if(mpmhdjg == "NaN"|| mpmhdjg ==""){
    		mpmhdjg =0.00;
    	}
    	var jsjeSubmit = changeJE(Math.round(parseFloat(mpmhdjg)*parseFloat(fwjzmj)*100.00)/100.00);  	
      	document.all.jsje.value=jsjeSubmit; 
      	putObjectValue(document.forms[0].jsjeSubmit,"",jsjeSubmit); 
      	putObjectValue(document.forms[0].jssrqrfsSubmit,"",<%=Constants.JSSRQRFS_HDJSJG%>);
    }
}
// new add 
function setFwzxfy(){
		var jsjeValue=document.all.jsje.value;
		if(jsjeValue == "NAN"|| jsjeValue==""){
			jsjeValue=0.00;
		}
		var zfzxfyValue=changeJE(Math.round(parseFloat(jsjeValue)*0.1*100.00)/100.00);
		putObjectValue(document.forms[0].zfzxfy,"",zfzxfyValue);
		getView();
}
//end 
//������λ�����»�����ǰ��0
function addZeroBefore(str){
	str = str+"";
	if(str.length == 1){
		 str="0"+str;
		 return str;
	}
	return str;
}




//��ֵ����
function putObjectValue(hiddProperty,td_id,obj_value)
{
	if(hiddProperty != null && hiddProperty != "")
	{
		hiddProperty.value=obj_value;
	}
	return true;
}

//�жϵ�ѡ�Ƿ�ѡ��
function checkradio(rName,rValue)
{
	for(var i = 0;i < rName.length;i++)
	{
		if(rName[i].checked)
		{
			return true;
			break;
		}
	}
	return false;
}

//ͨ��ֵѡ�е�ѡ
function initradio(rName,rValue)
{
	for(var i = 0;i < rName.length;i++)
	{
		if(rName[i].value == rValue)
		{
			rName[i].checked =  'checked';
		}
	}
}

//ͨ��ֵѡ�и�ѡ��
function transVal2Checkbox(val,chkName,split)
{  
     val=val.split(split);  
     var eles=document.getElementsByTagName('input');  
     for (var i=0; i<eles.length; ++i) {  
         if (eles[i].type=='checkbox'&&eles[i].name==chkName)
         {  
             eles[i].checked=false;  
             for (var x in val ) 
             {  
                 if (val[x]!=''&&eles[i].value==val[x]) 
                 {  
                     eles[i].checked=true;  
                 }  
             }  
         }  
   }  
} 

//����ʽ
function  changeJE(je)
{
	var strJe = je.toString();
    var rs = strJe.indexOf('.');  
     
    if (rs < 0) {  
         rs = strJe.length;  
         strJe += '.';  
    }  
    while (strJe.length <= rs + 2) {  
         strJe += '0';  
    }  
    return strJe;  
}

//function  : ��������ַ����Ƿ����������
//parameters: DigitString: String,������ַ�����ֵ
//			  totalLength: int, ���ֵĳ������ƣ�������С���㣩
//			  decimalLength: int��С���ĳ�������
//          totalLength-decimalLength �������ֵ�����
//          isPositive �Ƿ�Ϊ�Ǹ��� true��ʾһ��Ҫ�ǷǸ�����false��ʾһ��Ҫ�Ǹ�����null��ʾ���� add by zhangp 2003-09-25
//return    : false: �ַ����а�������������ַ�
//			  true : else
function checkNumber1(DigitString, totalLength, decimalLength,isPositive)
{
  var re = "";
  
  if(isPositive == "true" || isPositive == true)
  {//����ǷǸ�����ֵ����0��
      if(isNaN(DigitString*1) || !(DigitString*1 > 0))
          return false;
  }
  if(isPositive == "false" || isPositive == false)
  {//����Ǹ���
      if(isNaN(DigitString*1) || DigitString*1>=0)
          return false;
  }

  
  if (decimalLength!=null && decimalLength != 0)
  {   //С����Ϊ��
      re = re+"\\.[\\d]{1,"+ decimalLength +"}"
  }

  if (totalLength == null)
  {   //δ�趨�ܳ���
      re= "\\d*"+re;
  }
  else
  {   //�趨�ܳ���
      //��С������Ϊ�յ�����£������ж����ֵĳ���
      var intergerLength = totalLength;
      if (decimalLength!=null)
      {   //��С�����ֲ�Ϊ���жϣ�������λ����С����λ���Ƿ���ȷ
          intergerLength = totalLength-decimalLength;
      }
      re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
  }
  re = new RegExp("^"+re+"$","g");

  //���ַ���Ϊ�ա�λ�����ԡ�����ƥ�������ʱ
  if(re.exec(DigitString) == null)
  {
      return false;
  }
  return true;
}
</script>

<script type="text/javascript">

//===============================================================
		  //�����������֮�󷵻�ʱ��ֵ
function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  //alert("-----------0000000");
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  //alert("-----------1111");
			   //alert("infoArr ���ȣ���"+buyorSellInfo);
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  //alert("infoArr ���ȣ���"+infoArr.length);
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					  //alert("-----------ÿ���˵���ϢtempInfo----"+tempInfo);
					  //���ý�������������ÿ������������Ϣ����ʾ
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
					  //alert("-----------22222222222");
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //��������˫����Ϣ
function setBuyorSellInfo(info,tableId,hidPropertyObj){
		  		var isSuccess = false;
		  
	  			if(info == null || info ==""){
	  				return isSuccess;
	  			}
		  
	    	var tableobj=document.getElementById(tableId);
			var AllGroupInfoArr = info.split("~");
			var submitInfo ="";
			
			if(AllGroupInfoArr != null && AllGroupInfoArr !=""){
				var count = AllGroupInfoArr.length/6;
				//alert("��Ϣ����"+count);
				
				if(count!= null && count!=0){
					var oneGroupInfo = new Array();
					for(zsIndex =0; zsIndex<count;zsIndex++){
						var value_0 = AllGroupInfoArr[0+(zsIndex*6)];
						var value_1 = AllGroupInfoArr[1+(zsIndex*6)];
						var value_2 = AllGroupInfoArr[2+(zsIndex*6)];
						var value_3 = AllGroupInfoArr[3+(zsIndex*6)];
						var value_4 = AllGroupInfoArr[4+(zsIndex*6)];
						var value_5 = AllGroupInfoArr[5+(zsIndex*6)];
					
						oneGroupInfo.push(value_0);
						oneGroupInfo.push(value_1);
						var DSzjdm=value_2;
						oneGroupInfo.push(DSzjdm);//֤�����ʹ���
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//���ò���ʾÿ����Ϣ
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("��ʾ��������Ϣ����");
							return isSuccess;
						}
						
						//ƴ���ύ����̨����Ϣ
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+DSzjdm+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//������Ϣ
						oneGroupInfo = new Array();
					}
					//�����ύ�������ֵ
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //��ί֤�����ʹ���ת��˰֤�����ʹ���
function getZjlxJwToDs(dm){
		  var res = zjlxJwToDs(dm);// this function in ../js/qscommon.js
		  
		  //���û�з���ת��������������´���
		  if (res == null || res == ""){
			  res ="90";//����
		  }
		  
		  return res;
	  }
	  
	  
	  
	  //��ÿ����Ϣ���õ�td��innerHTML����ʾ
function setOneGroupInfo1(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	    		var otr = tableobj.insertRow();
	    		//��һ�е�һ��  �����ƣ���������
	    		var mc_Cell=otr.insertCell();
	    		mc_Cell.innerHTML="���ƣ�������";
	    		mc_Cell.className ="2-td2-left";
	    		mc_Cell.WIDTH="20%";
	    		//��һ�еڶ��� 
	    		var mc_value_Cell=otr.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		mc_value_Cell.WIDTH="30%";
	    		//��һ�е�����  �����
	    		var lb_Cell=otr.insertCell();
	    		lb_Cell.innerHTML="���";
	    		lb_Cell.className ="2-td2-left";
	    		lb_Cell.WIDTH="20%";
	    		//��һ�е�����  
	    		var lb_value_Cell=otr.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-center";
	    		lb_value_Cell.WIDTH="30%";
	    		
	    		
	    		//�ڶ��е�һ�� ��֤�����͡�
	    		var otr2 = tableobj.insertRow();
	    		var zjlx_Cell=otr2.insertCell();
	    		zjlx_Cell.innerHTML="֤������";
	    		zjlx_Cell.className ="2-td2-left";
	    		zjlx_Cell.WIDTH="20%";	    		
	    		//�ڶ��еڶ���
	    		var zjlx_value_Cell=otr2.insertCell();
	    		zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		zjlx_value_Cell.WIDTH="30%";	    		
	    		//�ڶ��е�һ�� ��֤�����롱
	    		var zjhm_Cell=otr2.insertCell();
	    		zjhm_Cell.innerHTML="֤������";
	    		zjhm_Cell.className ="2-td2-left";
	    		zjhm_Cell.WIDTH="20%";		    		
	    		//�ڶ��е�һ�� 
	    		var zjhm_value_Cell=otr2.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-center";
	    		zjhm_value_Cell.WIDTH="30%";
	    		
	    		
	    		//�����е�һ�� ��Ȩ���˷ݶ
	    		var otr3 = tableobj.insertRow();
	    		var fe_Cell=otr3.insertCell();
	    		fe_Cell.innerHTML="Ȩ���˷ݶ�";
	    		fe_Cell.className ="2-td2-left";
	    		fe_Cell.WIDTH="20%";
	    		//�����еڶ��� 
	    		var fe_value_Cell=otr3.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		fe_value_Cell.WIDTH="30%";	    		
	    		//�����е����� ����ϵ�˵绰��
	    		var lxdh_Cell=otr3.insertCell();
	    		lxdh_Cell.innerHTML="��ϵ�˵绰";
	    		lxdh_Cell.className ="2-td2-left";
	    		lxdh_Cell.WIDTH="20%";	    		
	    		//�����е����� 
	    		var lxdh_value_Cell=otr3.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  //��ÿ����Ϣ���õ�td��innerHTML����ʾ
function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//��ʼ����ͷ
	  			if(tabLength == 1){
		    		var otr = tableobj.insertRow();
		    		//��һ�е�һ��  �����ƣ���������
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="���ƣ�������";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//��һ�еڶ���  �����
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="���";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//��һ�е����� ��֤�����͡�
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="֤������";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ��֤�����롱
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="֤������";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//��һ�е����� ��Ȩ���˷ݶ
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="Ȩ���˷ݶ�";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//��һ�е����� ����ϵ�˵绰��
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="��ϵ�˵绰";
		    		lxdh_Cell.className ="2-td2-center";
		    		lxdh_Cell.WIDTH="20%";
	  			}
	  			
	    		//��n(n>=2)�е�һ�� 
	    		var otr_n = tableobj.insertRow();
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		//mc_value_Cell.WIDTH="30%";

	    		//��n(n>=2)�еڶ���  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-left";
	    		//lb_value_Cell.WIDTH="30%";
	    		
	    		
		
	    		//��n(n>=2)�е�����
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		//zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]); 
	    		//zjlx_value_Cell.innerHTML=getZjlxmc(arr[2]);
	    		zjlx_value_Cell.innerHTML=getZjmc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		//zjlx_value_Cell.WIDTH="30%";
	    		
	    		
	    		//��n(n>=2)�е����� 
	    		var zjhm_value_Cell=otr_n.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-left";
	    		//zjhm_value_Cell.WIDTH="30%";
	    		
	    		

	    		//��n(n>=2)�е����� 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		//fe_value_Cell.WIDTH="30%";	    		
	    		
	    		//��n(n>=2)�е����� 
	    		var lxdh_value_Cell=otr_n.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		//lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  
function getZjmc(zjdm){
     <logic:iterate id="item"  name="ClfswjghdxxlrForm" property ="zjList" indexId="index">
			var zjdm_1 = '<bean:write name="item" property="zjlxdm"/>';
			var zjmc_1 = '<bean:write name="item" property="zjlxmc"/>';
			//alert("zjdm_1::"+zjdm_1+" -- zjdm"+zjdm);
			if(zjdm == zjdm_1){
				
				return zjmc_1;
			}
		</logic:iterate> 
		
		return "����";
	}

//�����ͨ����������������޼�
function getPtfwszqyzgxj(fwszqydm_selected){
	if(fwszqydm_selected == null  || fwszqydm_selected == ""){
		return "0.00";
	}
	
    <logic:iterate id="item"  name="ClfswjghdxxlrForm" property ="fwszqyList" indexId="index">
	var fwszqydm_1 = '<bean:write name="item" property="fwszqydm"/>';
	var fwszqympmjgsx_1 = '<bean:write name="item" property="fwszqympmjgsx"/>';
	if(fwszqydm_selected == fwszqydm_1){
		
		return fwszqympmjgsx_1;
	}
    </logic:iterate> 	
    
    return "0.00";
}
//�����ͨ������������������ܼۣ����������������added by zhangj 2014.10.13
function getFwszqyje(fwszqydm_selected){
	if(fwszqydm_selected == null  || fwszqydm_selected == ""){
		return "0.00";
	}
	
    <logic:iterate id="item"  name="ClfswjghdxxlrForm" property ="fwszqyList" indexId="index">
	var fwszqydm_1 = '<bean:write name="item" property="fwszqydm"/>';
	var fwszqyje = '<bean:write name="item" property="fwszqyje"/>';
	if(fwszqydm_selected == fwszqydm_1){
		
		return fwszqyje;
	}
    </logic:iterate> 	
    
    return "0.00";
}
//�����ݺ˶�����Ϊ��ס��ʱ��������Ӧ�ı仯
function zfchange(){

	var isSaved=document.all.isSaved.value;
	var hasMAuthorise=document.all.hasMAuthorise.value;
	if(!(isSaved=="true" && hasMAuthorise==false)){
	document.all.operationType.value = "Query";
    document.forms[0].submit();
	}
}
//���ݹ滮��;��ʾ���ݺ˶�����
/*<option value="21">��������ס��</option>
<option value="1">��ͨסլ</option>
<option value="3">����</option>																										
<option value="2">��Ԣ</option>
<option value="4">��ҵ</option>
<option value="5">д��¥</option>
<option value="6">��ҵ����</option>
<option value="7">����</option>
<option value="10">����</option>*/
function ghytchange(){

	var ghyt=document.forms[0].ghyt.value;	
	if(ghyt==null||ghyt==""){
		document.all.fwhdlxdm[1].checked=true;
	}else{
/*		if(ghyt=="21"||ghyt=="1"||ghyt=="2"||ghyt=="3"){
			if(window.confirm('���ݻ�����;�Ǿ�������ס������ͨסլ����������ȷ����ס����')){
				document.all.fwhdlxdm[0].checked=true;
				if(document.all.pageName.value=="fzf"){			
					zfchange();
				}			
			}else{
				document.all.fwhdlxdm[1].checked=true;
				if(document.all.pageName.value=="zf"){			
					zfchange();
				}
			}		
		}else if(ghyt=="4"||ghyt=="5" ||ghyt=="6"||ghyt=="7"){
			if(window.confirm('���ݻ�����;����ҵ��д��¥����ҵ���������⣬��ȷ���Ƿ�ס����')){
				document.all.fwhdlxdm[1].checked=true;
				if(document.all.pageName.value=="zf"){			
					zfchange();
				}
			
			}else{
				document.all.fwhdlxdm[0].checked=true;
				if(document.all.pageName.value=="fzf"){		
					zfchange();
				}
			}	
		}*/
	
	}

}
//����ѡ��������ѡ�е�ѡ��ƴ�ӳ�һ���ַ���
function chBox_toString(name_chBox){
	var name_chBox=document.getElementsByName(name_chBox);
	var nameString="";
	for(var i=0;i<name_chBox.length;i++){
		if(name_chBox[i].checked==true){
		nameString+=name_chBox[i].value+"|";
		}		
	}
	return nameString;
}
//��һ���ַ����е�ֵ��ѡ�и�ѡ��
function String_toChBox(Str,name_chBox){
	var name_chBox=document.getElementsByName(name_chBox);
	var strArray=Str.split("|");
	for(var i=0; i<strArray.length;i++){
		if(strArray[i]!=null && strArray[i]!=""){
		//name_chBox[strArray[i]].checked=true;
			checkBoxByValue(name_chBox,strArray[i]);
		}		
	}
}
function hasValue(Str,value){
	var strArray=Str.split("|");
	for(var i=0; i<strArray.length;i++){
		if(strArray[i]!=null && strArray[i]!="" && strArray[i]==value){
		//name_chBox[strArray[i]].checked=true;
			return true;
		}		
	}
	return false;
}
function gffpQuery(){
	if(document.getElementById("tdzzssbfs_GFFP").checked==true){
		if(document.all.fpcxLink.value!=""&&document.all.fpcxLink.value!=null){
			window.open(document.all.fpcxLink.value);	
		}		
	}
}
function displayOrNot(){
	if(document.all.zhankai.value=="true"){
		document.all.fdczjxx.style.display="block";
		document.all.zhankai.value="false";
		document.all.zhankai.src="<%=static_file%>images/zbotton-jian2.gif";
		document.all.zhankai.alt="����";
	}else if(document.all.zhankai.value=="false"){
		document.all.fdczjxx.style.display="none";
		document.all.zhankai.value="true";
		document.all.zhankai.src="<%=static_file%>images/zbotton-jia2.gif";
		document.all.zhankai.alt="չ��";
	}
}
function checkBoxByValue(checkBoxName,value){
	for(var i=0;i<checkBoxName.length;i++){
		if(checkBoxName[i].value==value){
			checkBoxName[i].checked=true;
		}
	}
}
//=========================================================================	
</script>














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

<HEAD><TITLE>存量房税务机关核定信息录入</TITLE>
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
 <jsp:param name="subtitle" value="存量房管理&gt;存量房税务机关核定信息录入"/>
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
          <td class="1-td1"><a>个人销售已购房屋涉税申报核定表（非住房类代开发票申请表）</a></td>
        </tr>
        <tr>
          <td class="1-td2"> <br>
            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="3%" class="2-td2-t-left"><div align="left"><strong>合同编号</strong></div></td>
                <td width="40%" class="2-td2-t-center" colspan="2"><div align="left">
                <html:text property="htbh" maxlength="22" size="43"/>&nbsp;
                <img onClick="document.all.isQuery.value=true;doSubmitForm('Query')" alt="查询" style="cursor:hand" src="<%=static_file%>/images/chaxun1.jpg"  name="Image1111" width="79" height="22" border="0" id="Image1111" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image1111','','<%=static_file%>/images/chaxun2.jpg',1)" ></div></td>
              	                <!-- zhangj， fwhdlxdm(房屋核定类型代码)0：住房，1：非住房-->
                <td width="15%" class="2-td2-t-left"><div align="center">房屋核定类型</div></td>
                <td  colspan="3" class="2-td2-t-center">
                	<div align="center">经核定为住房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwhdlxdm" value="<%=Constants.FWHDLX_HOUSING%>" onclick="document.all.isQuery.value=false;if(window.confirm('您确定要将房屋核定类型切换为住房吗？')){zfchange();}else{document.all.fwhdlxdm[1].checked=true;}"/>
                	&nbsp;经核定为非住房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwhdlxdm" value="<%=Constants.FWHDLX_NONHOUSING%>" onclick="document.all.isQuery.value=false;zfchange()"/></div></td>
              </tr>
            </table>
            <br>
            

            <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="15%" class="3-td3-t-left"><div align="right">编号（流水号）：</div></td>
                <td width="15%" class="3-td3-t-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="sbbh" /></div></td>
                <td width="15%" class="3-td3-t-left"><div align="right">受理日期：</div></td>
                <td width="25%" class="3-td3-t-left"><div align="left"><%=DateUtils.displayValue(new Date(),"yyyy-MM-dd")%></div></td>
                <td class="3-td3-t-left"><div align="right">金额单位：元（角分）</div></td>
              </tr>
            </table>
            <table width="75%" border="0" cellspacing="0" class="table-99" id="sellTab">
              <tr>
              	<td colspan="7" align="center" class="2-td1-center">申请人（卖方）信息</td>
              </tr>
              
<!--               <tr>
              	<td width="25%" align="center" class="2-td2-left">姓名</td>
              	<td align="center" class="2-td2-left">证件名称</td>
              	<td align="center" class="2-td2-left">证件号码</td>
              	<td align="center" class="2-td2-center">联系电话</td>
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
              	<td colspan="7" align="center" class="2-td1-center">付款人（买方）信息</td>
              </tr>
<!--               <tr>
              	<td width="25%" align="center" class="2-td2-left">姓名</td>
              	<td align="center" class="2-td2-left">证件名称</td>
              	<td align="center" class="2-td2-left">证件号码</td>
              	<td align="center" class="2-td2-center">联系电话</td>
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
                <td width="11%" class="2-td2-t-left"><div align="center">申请人现住址</div></td>
                <td colspan="6" class="2-td2-t-center"><div align="left"><html:text property="sqrxzdz" size="99%" />&nbsp;</div></td>
              </tr>
              <tr>
                <td width="11%" class="2-td2-left"><div align="center">房屋详细地址</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fwzldz" /></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center"><FONT color=red>是否为家庭唯一生活用房</FONT></div></td>
                <td colspan="2" class="2-td2-center">
                	<div align="left">&nbsp;是<html:radio name="ClfswjghdxxlrForm" property="jtwyshyhbz" value="<%=Constants.ONLY_ROOM_YES%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;
                					  &nbsp;否<html:radio name="ClfswjghdxxlrForm" property="jtwyshyhbz" value="<%=Constants.ONLY_ROOM_NOT%>" onclick="getView()"/></div></td>
              </tr>
              <tr>
                <td width="11%" class="2-td2-left"><div align="center">房屋产权证号</div></td>
                <td width="19%" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fwcqzh" /></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center">房屋产权证所标注的房屋种类</div></td>
                <td width="15%" class="2-td2-left">
                	 <div align="center"><bean:define id="dta" name="ClfswjghdxxlrForm" property="fwcqzbzzflxList"/>
                  		<html:select property="fwcqzbzzflxdm" style='width:190px' onchange="getView()">
                    		<html:options collection="dta" labelProperty="fwcqzbzzflxmc" property="fwcqzbzzflxdm"/>
                  		</html:select></div></td>
                <td class="2-td2-left" nowrap="nowrap"><div align="center">申请开票金额(本次交易金额)</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"> &nbsp;<bean:write name="ClfswjghdxxlrForm" property="htzj" /></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">房屋类型</div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="center">楼房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BUILDINGS%>"/></div></td>
                <td class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="center">平房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BUNGALOW%>"/></div></td>
                <td class="2-td2-left" style="border-left-style:none;">
                	<div align="center">地下室&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwlxdm" value="<%=Constants.FWLX_BASEMENT%>"/></div></td>
                <td width="15%" class="2-td2-left" nowrap="nowrap"><div align="center">房屋权属转移明细</div></td>
                <td width="15%" class="2-td2-left">
                	 <div align="center"><bean:define id="qszyxsmx" name="ClfswjghdxxlrForm" property="qszyxsmxList"/>
                  		<html:select property="qszyxsmxdm" style='width:190px' onchange="getView()">
                  			<html:option value="">请选择</html:option>
                    		<html:options collection="qszyxsmx" labelProperty="qszyxsmxmc" property="qszyxsmxdm"/>
                  		</html:select></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>建成年代</FONT></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="jcnd" size="10" maxlength="4"/>&nbsp;<FONT color=red>(yyyy)</FONT>   &nbsp;&nbsp;<input type="checkbox"  id="wjcnd" onclick="setJcnd(this)">无建成年代</div></td>
                <td class="2-td2-left"><div align="center">总楼层</div></td>
                <td class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="zlc_show" /></div></td>
                <td class="2-td2-left"><div align="center">所在楼层</div></td>
                <td colspan="2" class="2-td2-center" nowrap="nowrap"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="szlc_show" /></div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">房屋建筑面积</div></td>
                <td class="2-td2-left"><div align="left"><input disabled="disabled" name="fwjzmj" value="<bean:write name="ClfswjghdxxlrForm" property="fwjzmj" />" ></div></td>
                <td colspan="2" class="2-td2-left"><div align="center"><FONT color=red>原购房发票金额</FONT></div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="ygffpje" size="22" onchange="getView()"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>购房证明日期</FONT></div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="gfzmrq" size="10"/>&nbsp;<FONT color=red>(yyyymmdd)</FONT></div></td>
                <td class="2-td2-left"><div align="center">买卖双方合同签订日期</div></td>
                <td colspan="2" class="2-td2-center"><div align="center">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="htwsqyrq" /></div></td>
              </tr>
              <tr>
                <td colspan="7" class="2-td2-center"><div align="center"><FONT color=red> 申报方式（根据各税种相关规定，可多选）</FONT></div></td>
              </tr>
              <tr>
                <td  class="2-td2-center" style="border-right-style:none" nowrap="nowrap">
                	<div align="center"><FONT color=red>提供购房发票&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFFP" value="<%=Constants.TDZSS_SB_GFFP%>" onclick="getView();gffpQuery()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>提供购房契税票&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFQSP" value="<%=Constants.TDZSS_SB_GFQSP%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>提供原购房合同&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_GFHT" value="<%=Constants.TDZSS_SB_GFHT%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>提供评估报告&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_PGBG" value="<%=Constants.TDZSS_SB_PGBG%>" onclick="changeTdzzsAndJssrqr();getView()"></div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>无任何票据&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_WPJ" value="<%=Constants.TDZSS_SB_WPJ%>" onclick="getView()"></div></td>
                <td  class="2-td2-center" style="border-left-style:none;" nowrap="nowrap">
                	<div align="center"><FONT color=red>提供法律文书&nbsp;</FONT><input type="checkbox" name="tdzzssbfs_chBox" id="tdzzssbfs_FLWS" value="<%=Constants.TDZSS_SB_FLWS%>" onclick="getView()"></div></td>
              </tr>
              <tr>
                <td colspan="3" class="2-td2-left"><div align="center">提供购房发票填写下栏</div></td>
                <td colspan="4" class="2-td2-center"><div align="center">提供评估报告填写下列栏</div></td>
              </tr>
              <tr>
                <td colspan="2" class="2-td2-left"><div align="center"><FONT color=red>取得房地产时所缴纳的契税金额</FONT></div></td>
                <td  class="2-td2-left"><div align="left"><html:text property="qdfcqsje" size="12" onchange="getView()"/>&nbsp;</div></td>
                <td colspan="2" class="2-td2-left"><div align="center">取得土地使用权所支付的金额</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"><html:text property="qdtdsyqzfje" size="15" disabled="true"/>&nbsp;</div></td>                
                
			  </tr>
              <tr>
                <td colspan="2" class="2-td2-left"><div align="center">&nbsp;&nbsp;&nbsp;<FONT color=red>取得房地产时所缴纳的印花税金额</FONT></div></td>
                <td class="2-td2-left"><div align="left"><html:text property="qdfcyhsje" size="12"  onchange="getView()"/>&nbsp;</div></td>              
                <td colspan="2" class="2-td2-left"><div align="center">旧房及建筑物的评估价格</div></td>
                <td colspan="2" class="2-td2-center"><div align="left"><html:text property="jfpgjg" size="15" disabled="true" onchange="getView()"/>&nbsp;</div></td>
                                            
			  </tr>
              <tr>
                <td  colspan="2" class="2-td2-left"><div align="center">发票所载日期</div></td>
                <td  class="2-td2-left" nowrap="nowrap"><div align="left"><html:text property="fpszrq" size="12" onchange="getView()"/>&nbsp;<FONT color=red>(yyyymmdd)</FONT></div></td>               
                <td colspan="2"  class="2-td2-left"><div align="center">价格评估费用</div></td>
                <td colspan="2"  class="2-td2-center"><div align="left"><html:text property="jgpgfy" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
			  <tr>
                <td  colspan="2" class="2-td2-left"><div align="center">按年加计数额</div></td>
                <td  class="2-td2-left"><div align="left"><html:text property="anjjse" size="12"/></div></td> 		
                <td colspan="2"  class="2-td2-left"><div align="center"><!-- 原契税票房屋计税价格--> &nbsp;</div> </td>
                <td colspan="2"  class="2-td2-center"><div align="left"><html:text property="yqspfwjsjg" size="15" value="0"/>&nbsp;</div></td>		  
			  </tr>              
		 </table>   
		 <table width="75%" border="0" cellspacing="0" class="table-99" >  
			  <tr>
			  	<td class="2-td2-center"><div align="left">
			  	<img onclick="displayOrNot()" src="<%=static_file%>images/zbotton-jian2.gif" alt="收起" name="zhankai" id="zhankai"  style="cursor:hand" value="false"></div></td>
			  </tr>
		</table> 
		<table width="75%" border="0" cellspacing="0" class="table-99" id="fdczjxx">                  
             <tr>
                <td class="2-td2-left"><div align="center">房地产中介机构名称</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">&nbsp;<bean:write name="ClfswjghdxxlrForm" property="fdczjjgmc" /></div></td>
                <td class="2-td2-left"><div align="center">地税计算机代码</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjsjdm" size="15"/>&nbsp; </div>                 	
                </td>

              </tr>               
              <tr>
                <td class="2-td2-left"><div align="center">地税税务登记号码</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjswdjzh" size="25"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">联系电话</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjlxdh" size="15"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">房地产经纪人姓名</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjjjr" size="25" maxlength="100"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">联系电话</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjjrlxdh" size="15"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left" nowrap="nowrap"><div align="center">房地产经纪人身份证号码</div></td>
                <td colspan="3" class="2-td2-left"><div align="left"><html:text property="fdczjjjrzjhm" size="25"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">经纪人资格证书号码</div></td>
                <td colspan="3" class="2-td2-center"><div align="left"><html:text property="fdczjjjrzgzsh" size="15"/>&nbsp;</div></td>
              </tr>
		</table>
		<table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td class="2-td2-left"><div align="center">产权证标注建筑面积</div></td>
                <td class="2-td2-left" nowrap="nowrap">
                	<div align="center">
                	140平米（含）以下&nbsp;<html:radio name="ClfswjghdxxlrForm" property="cqzbzjzmjfl" value="<%=Constants.CQZBZ_JLMX_LOW%>" disabled="true"/>
                	&nbsp;&nbsp;&nbsp;&nbsp;140平米以上&nbsp;<html:radio name="ClfswjghdxxlrForm" property="cqzbzjzmjfl" value="<%=Constants.CQZBZ_JLMX_HIGH%>" disabled="true"/>
                	</div></td>
                <td width="18%" class="2-td2-left"><div align="center">每平米交易单价</div></td>
                <td width="30%" class="2-td2-left"><div align="left">&nbsp;<label id="showMpmjydj"><input type="hidden" name="hidd_mpmjydj" value="<bean:write name="ClfswjghdxxlrForm" property="mpmjydj" />" onchange="getView()"> </div></td>                
                <td class="2-td2-left">
                	<div align="center">
                		每平米核定单价
                	</div>
                </td>
                <td  class="2-td2-center" colspan="2">
                	<div align="left">
                		<html:text  name="ClfswjghdxxlrForm" property="mpmhdjg" size="15" onchange="getView()"/>
                	</div>
                </td>
			  </tr>
              <tr>
                <td width="15%" class="2-td2-left"><div align="center">房屋所在地土地级次</div></td>
                <td width="18%" class="2-td2-left">
                	<div align="left"><bean:define id="dto" name="ClfswjghdxxlrForm" property="tdjcList"/>
                  		<html:select property="tdjcdm" style='width:75%'>
                  		<html:option value="">请选择</html:option>
                    	<html:options collection="dto" labelProperty="tdjcmc" property="tdjcdm"/>
                  </html:select> &nbsp;</div></td>
                  
                   
                  <td class="2-td2-left"><div align="center"><FONT color=red>房屋所在区域</FONT></div></td>
                  <td class="2-td2-left">
                	<div align="center"><bean:define id="fwszqy_dto" name="ClfswjghdxxlrForm" property="fwszqyList"/>
                  		<html:select  name="ClfswjghdxxlrForm" property="fwszqydm" style='width:165px' onchange="getView()">
                    	<html:options collection="fwszqy_dto" labelProperty="fwszqymc" property="fwszqydm"/>
                  </html:select></div>                  
                  </td>
                  
                <td class="2-td2-left"><div align="center"><FONT color=red>普通住房最高限价</FONT></div></td>
                <td colspan="2" class="2-td2-center"><div align="left"> <html:text property="ptzfzgxj" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>房屋容积率</FONT></div></td>
                <td  class="2-td2-left" style="border-right-style:none">
                	<div align="right">1.0以下&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwrjl" value="<%=Constants.FWRJL_LOW%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-left-style:none;">
                	<div align="right">1.0（含）以上&nbsp;<html:radio name="ClfswjghdxxlrForm" property="fwrjl" value="<%=Constants.FWRJL_HIGH%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="4" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
              <!--modified by zhangj   -->
                <td class="2-td2-left"><div align="center">划分标准</div></td>
                <td colspan="6" class="2-td2-center" >
                	<div align="center">普通住房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_PT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	非普通住房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_NOTPT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	非住房&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_FZF%>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	个人无偿赠与&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_GRWCZY%>" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                	个人继承&nbsp;<html:radio name="ClfswjghdxxlrForm" property="hfbz" value="<%=Constants.HFBZ_GRJC%>" />&nbsp;&nbsp;&nbsp;</div></td>
                <!-- <td colspan="4" class="2-td2-center" style="border-left-style:none;">&nbsp;</td> -->
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>住房使用时间</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">5年（含）以上&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_FIVE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">5年以下3年以上&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_THREETOFOIVE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td  class="2-td2-left" style="border-left-style:none;">
                	<div align="right">3年（含）以下&nbsp;<html:radio name="ClfswjghdxxlrForm" property="zfsjsjfl" value="<%=Constants.ZFSYSJ_THREE%>" onclick="getView()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
              	<td colspan="2" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>营业税征收方式</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">免征营业税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_NOT%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">全额征收营业税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_ALL%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" style="border-left-style:none;">
                	<div align="right">差额征收营业税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yyszsfs" value="<%=Constants.YSSZSFS_MINUS%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
              	<td colspan="2" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td nowrap class="2-td2-left"><div align="center"><FONT color=red>个人所得税征收方式</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">免征个人所得税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="grsdszsfs" value="<%=Constants.GRSDSZSFS_FREE%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-center" style="border-left-style:none;border-right-style:none;">
                	<div align="right">征收个人所得税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="grsdszsfs" value="<%=Constants.GRSDSZSFS_ZS%>" disabled="true"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="3" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>土地增值税征收方式</FONT></div></td>
                <td class="2-td2-center" style="border-right-style:none" nowrap="nowrap">
                	<div align="center">提供购房发票征收土地增值税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_GFFPZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">核定征收土地增值税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_HDZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">提供评估报告征收土地增值税&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_PGBGZS%>"/>&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-right-style:none;border-left-style:none;" nowrap="nowrap">
                	<div align="center">不征土地增值税&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs" value="<%=Constants.TDZZSZSFS_NOT%>" />&nbsp;&nbsp;</div></td>
                <td  class="2-td2-center" style="border-left-style:none;" nowrap="nowrap">
                	<div align="center">免征土地增值税&nbsp;<html:radio  name="ClfswjghdxxlrForm" property="tdzsszsfs"  value="<%=Constants.TDZZSZSFS_FREE%>" />&nbsp;&nbsp;</div></td>                               
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>印花税征收方式</FONT></div></td>  
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">征收印花税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yhszsfs" value="<%=Constants.YHSZSFS_ZS%>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-center" style="border-left-style:none;border-right-style:none;">
                	<div align="right">免征印花税&nbsp;<html:radio name="ClfswjghdxxlrForm" property="yhszsfs" value="<%=Constants.YHSZSFS_FREE%>"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="3" class="2-td2-center" style="border-left-style:none;">&nbsp;</td>                            
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center"><FONT color=red>计税收入确认方式</FONT></div></td>
                <td class="2-td2-left" style="border-right-style:none">
                	<div align="right">合同价格&nbsp;<html:radio name="ClfswjghdxxlrForm" property="jssrqrfs" value="<%=Constants.JSSRQRFS_HTJG%>" disabled="true" onclick="changeTdzzsAndJssrqr()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td colspan="2" class="2-td2-left" style="border-right-style:none;border-left-style:none;">
                	<div align="right">核定计税价格&nbsp;<html:radio name="ClfswjghdxxlrForm" property="jssrqrfs" value="<%=Constants.JSSRQRFS_HDJSJG%>" disabled="true" onclick="changeTdzzsAndJssrqr()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</div></td>
                <td class="2-td2-left" ><div align="center"><FONT color=red>金额</FONT></div></td>
                <td colspan="2" class="2-td2-center" ><div align="left"> <html:text property="jsje" size="15" disabled="true"/>&nbsp;</div></td>
              </tr>
              <tr>
              	<td class="2-td2-left"><div align="center"><FONT color=red>城建税税率</FONT></div></td>
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
                <td colspan="7" class="2-td2-center"><div align="center">扣除金额确认</div></td>
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">住房评估价格 </div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfpgjg" size="15"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">公证费</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="gzf" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>                            
                <td class="2-td2-left"><div align="center">原购房发票金额</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showYgffpje"><input type="hidden" name="inner_ygffpje" value="<bean:write name="ClfswjghdxxlrForm" property="ygffpje" />" > </div></td> 
                                              
              </tr>
              
              <tr>
                <td class="2-td2-left"><div align="center">住房贷款利息 </div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfdklx" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>
                <td class="2-td2-left"><div align="center">手续费</div></td>    
                <td class="2-td2-left"><div align="left"> <html:text property="sxf" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>                                  
                <td class="2-td2-left" nowrap="nowrap"><div align="left">其中：取得房地产时所缴纳的契税金额</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showQdfcqsje"><input type="hidden" name="inner_qdfcqsje" value="<bean:write name="ClfswjghdxxlrForm" property="qdfcqsje" />" ></div></td>                              
              </tr>
              
              <tr>
                <td class="2-td2-left"><div align="center">住房装修费用</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="zfzxfy" size="15" onchange="getView()" disabled="true"/>&nbsp;
                  <!--<img onclick="setFwzxfy()" alt="无装修费用凭证"  onMouseOver="MM_swapImage('xxx','','../image/wzxfypz2_blue.png',1)" onMouseOut="MM_swapImgRestore()" src="../image/wzxfypz2_gray.png" name="back" id="xxx"  style="cursor:hand">-->
                </div></td>              
                <td class="2-td2-left"><div align="center">土地出让金</div></td>
                <td class="2-td2-left"><div align="left"><html:text property="tdcrj" size="15" onchange="getView()" disabled="true"/>&nbsp;</div></td>            
                <td class="2-td2-left" nowrap="nowrap"><div align="left">其中：取得房地产时所缴纳的印花税金额 </div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showQdfcyhsje"><input type="hidden" name="inner_qdfcyhsje" value="<bean:write name="ClfswjghdxxlrForm" property="qdfcyhsje" />" ></div></td>       
              </tr>
              <tr>
                <td class="2-td2-left"><div align="center">合理费用</div></td>
                <td colspan="3" class="2-td2-left"><div align="left">
                	<html:text property="hlfy" size="15" onchange="getView()"/>
                	<!-- <label id="showHlfy"><input type="hidden" name="hidd_hlfy" value="<bean:write name="ClfswjghdxxlrForm" property="hlfy" />" > </label> -->
                	</div></td>
                <td class="2-td2-left" nowrap="nowrap"><div align="left">其中:旧房及建筑物评估价格</div></td>
                <td  colspan="2" class="2-td2-center"><div align="left">&nbsp;<label id="showJfpgjg"><input type="hidden" name="inner_jfpgjg" value="<bean:write name="ClfswjghdxxlrForm" property="jfpgjg" />" ></div></td>
              </tr>
            </table>  

              <br><hr>

      		<DIV align=center>
      		<logic:notEqual name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<img onclick="doSubmitForm('Save')" alt="保存"  onMouseOver="MM_swapImage('baocun1','','<%=static_file%>images/baocun2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/baocun1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</logic:notEqual>
      		
      		<logic:equal name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<logic:equal name="ClfswjghdxxlrForm" property="hasMAuthorise" value="true">
						<IMG alt=删除该核定信息  height=22 id=shanchu name=shanchu onclick="doSubmitForm('Delete')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('shanchu','','<%=static_file%>images/shanchu2.jpg',1)" src="<%=static_file%>images/shanchu1.jpg" style='CURSOR: hand' width=79>
						<IMG alt=保存修改信息  height=22 id=b-bcbg name=b-bcbg onclick="document.all.isQuery.value=true;doSubmitForm('Save')" onmouseout=MM_swapImgRestore() onmouseover="MM_swapImage('b-bcbg','','<%=static_file%>images/b-bcbg2.jpg',1)" src="<%=static_file%>images/b-bcbg1.jpg" style='CURSOR: hand' width=79>
				</logic:equal>
      		</logic:equal>
      		
      		<logic:equal name="ClfswjghdxxlrForm"  property="isSaved" value="true">
      			<img onclick="doSubmitForm('ToPrint')" alt="打印"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/dayin2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/dayin1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</logic:equal>
      			<img onClick="doSubmitForm('ToSellerQSZS')" alt="转卖方税款征收" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image124','','<%=static_file%>images/mfskzs2.jpg',1)"  src="<%=static_file%>images/mfskzs1.jpg" name="Image124" width="110" height="22" border="0" id="Image124" >
       			<img onClick="doSubmitForm('ToFPDK')" alt="转发票代开" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image126','','<%=static_file%>images/fpdk2.jpg',1)"  src="<%=static_file%>images/fpdk1.jpg" name="Image126" width="100" height="22" border="0" id="Image126" >
       			<%-- <img onClick="doSubmitForm('ToQSSB')" alt="转契税申报" style="cursor:hand" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('Image125','','<%=static_file%>images/qssb2.jpg',1)"  src="<%=static_file%>images/qssb1.jpg" name="Image125" width="100" height="22" border="0" id="Image125" > --%>
      			<img onclick="doSubmitForm('Return')" alt="退出"  onMouseOver="MM_swapImage('tuichu1','','<%=static_file%>images/tuichu2.jpg',1)" onMouseOut="MM_swapImgRestore()" src="<%=static_file%>images/tuichu1.jpg" name="back" width="79" height="22" id="tuichu1"  style="cursor:hand">
      		</DIV>
      		
      		<hr width="99%" class="hr1" size=1>

           <table width="75%" border="0" cellspacing="0" class="table-99">
              <tr>
                <td width="22%" class="2-td2-t-left">
                  <div align="right">录入人</div></td>
                <td width="31%" class="2-td2-t-left">
                  <div align="left">&nbsp;
                    <bean:write name="ClfswjghdxxlrForm" property="lrr" />
                </div></td>
                <td width="16%" class="2-td2-t-left">
                  <div align="right">录入日期</div></td>
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

//按年加计数额
var old_anjjse ="";
var old_anjjse_after_30_days="";

function init(){
	//卖方信息
	var sellerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_sellerInfo"/>';
	parseSaveBuyorSellInfo(sellerInfo,"sellTab","");
	//买方信息
	var buyyerInfo = '<bean:write name="ClfswjghdxxlrForm" property="all_buyerInfo"/>';
	parseSaveBuyorSellInfo(buyyerInfo,"buyTab","");
	
	//无建成年代选中
	var jcnd = '<bean:write name="ClfswjghdxxlrForm" property="jcnd"/>';
	if(jcnd != null && jcnd != "" && jcnd =="无建成年代"){
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
	
	//对土地增值税的显示状态进行控制
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
 * 获得两个日期相差月份
 */
function getYueShu(date1,date2){
	if(date1== null || date1 =="" || date2 == null || date2 ==""){
		return "NaN";
	}
	
	//alert(date1+"--"+date2);
	
	//alert(checkDate(date1));
	//alert(checkDate(date2));
	
	if(!checkDate(date1)||!checkDate(date2)){
		alert("日期填写有误，请检查！");
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
	
	//取两个日期月份差值
	var Months = date1_all_months- date2_all_months;
	var days = parseFloat(date1_day)-parseFloat(date2_day);
	
	var resYears =0;
	
	//除以12，计算有几年
	var division_12 = Math.floor(Months/12);
	
	//模12，获得除以12之后的余数
	var model_12 = Months%12;
	
	
	//alert("division_12--"+division_12 +"   model_12--"+model_12);
	
	
	
	/* //不足一年，返回0年
	if(division_12 == 0){
		resYears =0;
		return resYears;
	}
	
	
	//大于等于一年
	if(division_12 > 0){
		//月余数小于6个月，返回年
		if(model_12 < 6){
			return division_12;
		}
		
		//月余数超过6个月，返回加一年
		if(model_12 >6){
			resYears = division_12 + 1;
			return resYears;
		}
		
		//月余数等于6个月，且 days >0 返回加一年
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
		alert("当前日期小于发票所载日期，请重新输入！");
	}
	
}


function setJcnd(checkBoxObj){
	if(checkBoxObj.checked == true){
		document.forms[0].jcnd.value="无建成年代";
	}else{
		document.forms[0].jcnd.value="";
	}
}




function doSubmitForm(operationType)
{
	var hasMfSkzsxx = '<bean:write name="ClfswjghdxxlrForm" property="hasMfSkzsxx"/>';
	var hasMfFpdkxx = '<bean:write name="ClfswjghdxxlrForm" property="hasMfFpdkxx"/>';
	
	
	if(operationType=="ToPrint"){
		//判断是否可以直接打印
		//(1)如果【营业税征收方式】是免征、并且【个人所得税征收方式】是免征、并且【土地增值税征收方式】是不征或免征，则直接打印“个人销售已购住房涉税申报核定表（代开发票申请表）”，并且金额处全部空着
		//(2) 不满足条件（1），则判断是否有卖方税款申报信息，如果有，则打印，如果没有，则提示，需要先进行申报才能打印
		var free_yyszsfs_checked  = '<bean:write name="ClfswjghdxxlrForm" property="yyszsfs"/>';//营业税征收方式
		var free_grsdszsfs_checked = '<bean:write name="ClfswjghdxxlrForm" property="grsdszsfs"/>';//个人所得税征收方式
		
		var free_tdzsszsfs_checked = '<bean:write name="ClfswjghdxxlrForm" property="tdzsszsfs"/>';//土地增值税征收方式
		
		
		if((free_yyszsfs_checked != "" && free_yyszsfs_checked != <%=Constants.YSSZSFS_NOT%>)||//营业税征收方式：免征营业税
		   (free_grsdszsfs_checked != "" && free_grsdszsfs_checked != <%=Constants.GRSDSZSFS_FREE%>)//个人所得税征收方式:免征个人所得税			
			||
			((free_tdzsszsfs_checked != "" && free_tdzsszsfs_checked != <%=Constants.TDZZSZSFS_FREE%>)&&//土地增值税征收方式:免征土地增值税
		      (free_tdzsszsfs_checked != "" && free_tdzsszsfs_checked != <%=Constants.TDZZSZSFS_NOT%>))//土地增值税征收方式:不征收土地增值税
			){
			//如果没有卖方税款征收信息
			if(hasMfSkzsxx != null && hasMfSkzsxx != "" && hasMfSkzsxx != "true"){
				alert("本次核定需要先进行申报才能打印，请先进入【卖方税款征收】功能完成卖方税款申报！");
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
			alert("请输入相应的查询条件！");
			document.forms[0].htbh.focus();
			return false;
		}
        document.forms[0].sbbh.value="";
        
    }
	
	
	if(operationType=="Save" || operationType=="Delete"){
		if(hasMfSkzsxx != null && hasMfSkzsxx != "" && hasMfSkzsxx == "true"){
			alert("该审核信息已经有卖方税款申报信息，不能进行删除和修改！");
			return false;
		}
		
		if(hasMfFpdkxx != null && hasMfFpdkxx != "" && hasMfFpdkxx == "true"){
			alert("该审核信息已经有卖方发票代开信息，不能进行删除和修改！");
			return false;
		}		
	}
	
	
	if(operationType=="Delete"){
		if(document.forms[0].sbbh.value =="" ){
			alert("无合同采集信息，不能删除！");
			return false;
		}
		
		if (!confirm("您的操作是：“删除”。是否继续？"))
	    	return false;
	}
	
	if(operationType=="Save")
	{
		//added by zhangj start
		if(document.forms[0].fwhdlxdm.value =="" ){
			alert("未选择房屋核定类型，不能保存！");
			return false;
		}
		//当房屋权属转移类型为[房屋赠与]时，房屋权属转移明细为必选项
		if(document.forms[0].fwqszylx.value =="04" && (document.forms[0].qszyxsmxdm.value =="" || document.forms[0].qszyxsmxdm.value ==null)){
		
			alert("房屋权属转移类型为[房屋赠与]，房屋权属转移明细为必选项！");
			return false;
		}
		if(document.getElementById("tdzzssbfs_GFFP").checked ==true){
			if(document.forms[0].ygffpje.value<=0){
				alert("土地增殖税申报方式为[提供购房发票]，原购房发票金额必须大于零！");
				return false;
			}
			if(document.forms[0].fpszrq.value==""){
				alert("土地增殖税申报方式为[提供购房发票]，发票所载日期不能为空！");
				return false;
			}
		}
		if(document.getElementById("tdzzssbfs_GFQSP").checked ==true){
			if(document.forms[0].qdfcqsje.value==""){
				alert("土地增殖税申报方式为[提供购房契税票]，取得房地产时所缴纳的契税金额不能为空！");
				return false;
			}
			if(document.forms[0].yqspfwjsjg.value==""){
				alert("土地增殖税申报方式为[提供购房契税票]，原契税票房屋计税价格不能为空！");
				return false;
			}
		}
		if(document.getElementById("tdzzssbfs_PGBG").checked ==true){
			if(document.forms[0].qdtdsyqzfje.value==""){
				alert("土地增殖税申报方式为[提供评估报告]，取得土地使用权所支付的金额不能为空！");
				return false;
			}
		}
		if(document.all.yhszsfs[0].checked ==false&&document.all.yhszsfs[1].checked ==false){
				alert("请选择印花税征收方式！");
				document.all.yhszsfs[0].focus();				
				return false;
		}
		if(document.all.hlfy.value!=""&&document.all.hlfy.value!=null){
			if(!checkNumber(document.all.hlfy.value,15,2,true)){
            	alert("合理费用必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].hlfy.focus();
            	return false;
        	}
		}

		//added by zhangj end
		if(document.forms[0].sbbh.value =="" ){
			alert("无合同采集信息，不能保存！");
			return false;
		}
		//1.判断输入项非空及输入值金额、日期格式
		if(ygffpje.value=="")
		{
			alert("原购房发票金额不可为空！");
			document.forms[0].ygffpje.focus();
			return false;
		}
		else
		{
			if(!checkNumber(ygffpje.value,15,2,true))
        	{
            	alert("原购房发票金额必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].ygffpje.focus();
            	return false;
        	}
		}
		
		if(gfzmrq.value=="")
		{
			alert("购房证明日期不可为空！");
			document.forms[0].gfzmrq.focus();
			return false;
		}
		else
		{
			if(!checkDate(document.forms[0].gfzmrq.value))
        	{
            	alert("购房证明日期不合法，请修改！");
            	document.forms[0].gfzmrq.focus();
            	return false;
        	}
    	}
		
		if(ptzfzgxj.value==""|| ptzfzgxj.value=="0"||ptzfzgxj.value=="0.00")
		{
			alert("“普通住房最高限价”不可为空,需要选择“房屋所在区域”下拉列表进行设置！");
			document.forms[0].fwszqydm.focus();
			return false;
		}
		else
		{
			if(!checkNumber(ptzfzgxj.value,15,2,true))
        	{
            	alert("普通住房最高限价必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].ptzfzgxj.focus();
            	return false;
        	}
		}
		
		if(jsje.value=="")
		{
			alert("核定计税金额不可为空！");
			document.forms[0].jsje.focus();
			return false;
		}
		else
		{
			if(!checkNumber1(jsje.value,15,2,true))
        	{
            	alert("核定计税金额必须为数字，\n小数点后的长度为2位，\n且输入值需大于零！");
            	document.forms[0].jsje.focus();
            	return false;
        	}
		}
		
    	//new add 建成年代
    	var jcnd = document.forms[0].jcnd;
    	if(jcnd.value =="" || jcnd.value == null){
    		alert("“建成年代”不可为空！");
    		jcnd.focus();
    		return false;
    		
    	}else{
    		
    		var wjcnd = document.all.wjcnd;//无建成年代
			if(wjcnd.checked == false && !checkNumber1(jcnd.value,4,0,true))
        	{
            	alert("“建成年代”，填写格式不对，格式应为YYYY，如1970！");
            	jcnd.select();
            	return false;
        	}   		
    		
    	}
    	
    	//每平米核定单价 
    	var mpmhdjg = document.all.mpmhdjg;
    	if(mpmhdjg.value =="" || mpmhdjg.value == null){
    	    alert("请输入每平米核定单价!");
    	 	mpmhdjg.focus();
    		//mpmhdjg.value="0.00";
    		return false;
    	}else{
			if(mpmhdjg.value!="0"&&mpmhdjg.value!="0.00"&&!checkNumber1(mpmhdjg.value,15,2,true))
        	{
            	alert("“每平米核定单价“必须为数字，\n小数点后的长度为2位，\n且输入值需大于零！");
            	mpmhdjg.focus();
            	return false;
        	}else if("0.00"==changeJE(mpmhdjg.value)){
    		if(!confirm("您确定每平方米核定单价为"+mpmhdjg.value+"吗？")){
    			mpmhdjg.focus();
    			return false;
    		}
    	}   		
    		
    	}
    	
	
		//2.判断单选是否选中
		if(checkradio(jtwyshyhbz_checked,<%=Constants.ONLY_ROOM_YES%>) == false && 
		   checkradio(jtwyshyhbz_checked,<%=Constants.ONLY_ROOM_NOT%>) == false)
		{
			alert("请选择相应的“是否为家庭唯一生活用房”！");
			return false;
		}
		if(checkradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_LOW%>) == false && 
		   checkradio(cqzbzjzmjfl_checked,<%=Constants.CQZBZ_JLMX_HIGH%>) == false)
		{
			alert("请选择相应的“产权证标注建筑面积”！");
			return false;
		}
		if(checkradio(fwrjl_checked,<%=Constants.FWRJL_LOW%>) == false && 
		   checkradio(fwrjl_checked,<%=Constants.FWRJL_HIGH%>) == false)
		{
			alert("请选择相应的“房屋容积率”！");
			return false;
		}
		if(checkradio(hfbz_checked,<%=Constants.HFBZ_PT%>) == false && 
		   checkradio(hfbz_checked,<%=Constants.HFBZ_NOTPT%>) == false &&  
		   checkradio(hfbz_checked,<%=Constants.HFBZ_FZF%>) == false &&
		   checkradio(hfbz_checked,<%=Constants.HFBZ_GRWCZY%>) == false &&
		   checkradio(hfbz_checked,<%=Constants.HFBZ_GRJC%>) == false)
		{
			alert("请选择相应的“划分标准”！");
			return false;
		}
		if(checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_THREE%>) == false && 
		   checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_THREETOFOIVE%>) == false && 
		   checkradio(zfsjsjfl_checked,<%=Constants.ZFSYSJ_FIVE%>) == false)
		{
			alert("请选择相应的“住房使用时间”！");
			return false;
		}
		if(checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_NOT%>) == false && 
		   checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_ALL%>) == false && 
		   checkradio(yyszsfs_checked,<%=Constants.YSSZSFS_MINUS%>) == false)
		{
			alert("请选择相应的“营业税征收方式”！");
			return false;
		}
		if(checkradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_FREE%>) == false && 
		   checkradio(grsdszsfs_checked,<%=Constants.GRSDSZSFS_ZS%>) == false)
		{
			alert("请选择相应的“个人所得税征收方式”！");
			return false;
		}
		
		//土地增值税申报方式和征收方式
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
			alert("请选择相应的“土地增值税申报方式”！");
			return false;
		}		
				
 		if(GFFPZS_tdzsszsfs_checked == false && 
 		   PGBGZS_tdzsszsfs_checked == false&& 
		   HDZS_tdzsszsfs_checked == false && 
		   FREE_tdzsszsfs_checked == false && 
		   NOT_tdzsszsfs_checked == false)
		{
			alert("请选择相应的“土地增值税征收方式”！");
			return false;
		}
		
		
		if(checkradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HTJG%>) == false && 
		   checkradio(jssrqrfs_checked,<%=Constants.JSSRQRFS_HDJSJG%>) == false)
		{
			alert("请选择相应的“计税收入确认方式”！");
			return false;
		}
		
		//3.判断部分输入项金额格式
      	if(qdfcqsje.value !="")
		{
			if(!checkNumber(qdfcqsje.value,15,2,true))
        	{
            	alert("“取得房地产时所缴纳的契税金额”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].qdfcqsje.focus();
            	return false;
        	}
		}
		if(qdfcyhsje.value!="")
		{
			if(!checkNumber(qdfcyhsje.value,15,2,true))
        	{
            	alert("“取得房地产时所缴纳的印花税金额”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].qdfcyhsje.focus();
            	return false;
        	}
		}
		if(zfpgjg.value!="")
		{
			if(!checkNumber(zfpgjg.value,15,2,true))
        	{
            	alert("“住房评估价格”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].zfpgjg.focus();
            	return false;
        	}
		}
		if(zfzxfy.value!="")
		{
			if(!checkNumber(zfzxfy.value,15,2,true))
        	{
            	alert("“住房装修费用”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].zfzxfy.focus();
            	return false;
        	}
		}
		if(zfdklx.value!="")
		{
			if(!checkNumber(zfdklx.value,15,2,true))
        	{
            	alert("“住房贷款利息”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].zfdklx.focus();
            	return false;
        	}
		}
		if(sxf.value!="")
		{
			if(!checkNumber(sxf.value,15,2,true))
        	{
            	alert("“手续费”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].sxf.focus();
            	return false;
        	}
		}
		if(gzf.value!="")
		{
			if(!checkNumber(gzf.value,15,2,true))
        	{
            	alert("“公证费”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            	document.forms[0].gzf.focus();
            	return false;
        	}
		}
		
		if(tdcrj.value !=""){
			if(!checkNumber(tdcrj.value,15,2,true))
        	{
            	alert("“土地出让金”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
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
            		alert("“取得土地使用权所支付的金额”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            		document.forms[0].qdtdsyqzfje.focus();
            		return false;
        		}
			}
			
			if(jfpgjg.value!="")
			{
				if(!checkNumber(jfpgjg.value,15,2,true))
        		{
            		alert("“旧房及建筑物的评估价格”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            		document.forms[0].jfpgjg.focus();
            		return false;
        		}
			}
			
			if(jgpgfy.value!="")
			{
				if(!checkNumber(jgpgfy.value,15,2,true))
        		{
            		alert("“价格评估费用”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
            		document.forms[0].jgpgfy.focus();
            		return false;
        		}
			}
    	}
    	
    	//【土地增值税申报方式】选择提供购房发票 则 发票所载日期 和 按年加计数额 必填
    	if(document.getElementById("tdzzssbfs_GFFP").checked==true){
    		var fpszrqObj = document.forms[0].fpszrq;
    		var anjjseObj = document.forms[0].anjjse; 
    		
    		//判断“发票所载日期”
    		if(fpszrqObj.value == "" || fpszrqObj.value == null){
    			alert("【土地增值税申报方式】已经选择“提供购房发票”，则“发票所载日期”不可为空！");
    			fpszrqObj.focus();
    			return false;
    		}else{ 
    			if(!checkDate(fpszrqObj.value))
            	{
                	alert("“发票所载日期”不合法，请修改！");
                	fpszrqObj.select();
                	return false;
            	}
    		}
    		
    		//判断“按年加计数额”
    		if(anjjseObj.value == "" || anjjseObj.value == null){
    			alert("【土地增值税申报方式】已经选择“提供购房发票”，则“按年加计数额”不可为空！");
    			anjjseObj.focus();
    			return false;
    		}else{ 
    			if(!checkNumber(anjjseObj.value,15,2,true))
            	{
    				alert("“按年加计数额”必须为数字，\n小数点后的长度为2位，\n且输入值需大于等于零！");
                	anjjseObj.select();
                	return false;
            	}
    			
				if(parseFloat(anjjseObj.value) < parseFloat(old_anjjse)){
					alert("“按年加计数额”填写有误，值应该大于等于 "+old_anjjse+",且小于等于"+old_anjjse_after_30_days);
					anjjseObj.select();
					return false;					
				}
				
				if(parseFloat(anjjseObj.value) > parseFloat(old_anjjse_after_30_days)){
					alert("“按年加计数额”填写有误，值应该大于等于 "+old_anjjse+",且小于等于"+old_anjjse_after_30_days);
					anjjseObj.select();
					return false;					
				}    			
    		}    		
    	}else{
    		var anjjseObj = document.forms[0].anjjse; 
    		if(anjjseObj.value != "" && anjjseObj.value !="0.00" && anjjseObj.value !="0"){
    				alert("【土地增值税申报方式】未选择提供购房发票,所以按年加计数额必须为0.00");
					anjjseObj.select();
					return false;	
    		}
    	}  	  	
    	
    	if(document.all.jssrqrfs[<%=Constants.JSSRQRFS_HDJSJG%>].checked==true)
    	{
      		putObjectValue(document.forms[0].jsjeSubmit,"",document.all.jsje.value); 
    	}
    	
    	
    	//如果(1)土地增值税征收方式为 :免征土地增值税,且(2)且土地增值税申报方式为空,则默认土地增值税申报方式为 核定增收
 //   	var free_tdzzssbfs_checked = document.all.tdzsszsfs[<%=Constants.TDZZSZSFS_FREE%>].checked;
    	
 //   	if(checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_GFFP%>) == false && 
 //   	   checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_PGBG%>) == false &&
    	 //  checkradio(tdzzssbfs_checked,<%=Constants.TDZSS_SB_HDZS%>) == false &&
 //   	   free_tdzzssbfs_checked == true){
    	//	document.all.tdzzssbfs_chBox[<%=Constants.TDZSS_SB_HDZS%>].checked=true;
  //  	}
    	
		//added by zhangj
 		//给   tdzsszsfsSubmit赋值	
    	for(var i=0;i<document.all.tdzsszsfs.length;i++){
    		if(document.all.tdzsszsfs[i].checked==true){
    			document.all.tdzsszsfsSubmit.value=document.all.tdzsszsfs[i].value;
    		}
    	}
    	//给hfbzSubmit赋值
    	for(var i=0;i<document.all.hfbz.length;i++){
    		if(document.all.hfbz[i].checked==true){
    			document.all.hfbzSubmit.value=document.all.hfbz[i].value;
    		}
    	}
    	//给yyszsfsSubmit赋值
    	for(var i=0;i<document.all.yyszsfs.length;i++){
    		if(document.all.yyszsfs[i].checked==true){
    			document.all.yyszsfsSubmit.value=document.all.yyszsfs[i].value;
    		}
    	}
    	//给grsdszsfsSubmit赋值
    	for(var i=0;i<document.all.grsdszsfs.length;i++){
    		if(document.all.grsdszsfs[i].checked==true){
    			document.all.grsdszsfsSubmit.value=document.all.grsdszsfs[i].value;
    		}
    	}
     	//给yhszsfsSubmit赋值
    	for(var i=0;i<document.all.yhszsfs.length;i++){
    		if(document.all.yhszsfs[i].checked==true){
    			document.all.yhszsfsSubmit.value=document.all.yhszsfs[i].value;
    		}
    	}   
    		
    	if (!confirm("是否保存填写数据，请确认！"))
    	return false;
    	
    	document.all.isQuery.value=true;
	}
	
	document.all.tdzzssbfs.value=chBox_toString("tdzzssbfs_chBox");
    
    document.all.operationType.value = operationType;
    document.forms[0].submit();
}


//控制土地增值税申报方式
//（ 
//  A. 取得土地使用权所支付的金额：保留两位小数的数字型，当土地增值税申报方式选择【提供评估报告】时可以录入，否则不能录入；
//  B. 旧房及建筑物的评估价格：保留两位小数的数字型, 当土地增值税申报方式选择【提供评估报告】时可以录入，否则不能录入；
//  C. 价格评估费用：保留两位小数的数字型, 当土地增值税申报方式选择【提供评估报告】时可以录入，否则不能录入。）
//控制计税收入金额
//  A.如果【计税收入确认方式】选择【合同价格】则为二维码合同价格，不可修改;
//  B.如果【计税收入确认方式】选择【核定计税价格】，则人工录入，保留两位小数，并且必须录入.
function changeTdzzsAndJssrqr()
{
	//控制土地增值税申报方式
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
   
    //控制计税收入金额
	setJsje_jsfs();
}



//显示原购房发票金额、获得每平米交易单价、判断产权证标注建筑面积范围、控制划分标准、计税收入确认方式
function getView()
{	//added by zhangj
	if(document.all.yqspfwjsjg.value==""||document.all.yqspfwjsjg.value==null){
		document.all.yqspfwjsjg.value=changeJE(0.00);
	}
	/**
	 * 1.显示原购房发票金额
	 */
	var inner_ygffpje = document.all.ygffpje.value;
	if(inner_ygffpje =='NaN' || inner_ygffpje =="" )
		inner_ygffpje = changeJE(0.00);
	//alert(inner_ygffpje);
	var showYgffpje = document.getElementById("showYgffpje");
    showYgffpje.innerText = changeJE(inner_ygffpje);
    
    /**
	 * 2.显示"其中:旧房及建筑物评估价格"
	 */
	var inner_jfpgjg = document.all.jfpgjg.value;
	if(inner_jfpgjg =='NaN' || inner_jfpgjg =="" )
		inner_jfpgjg = changeJE(0.00);
	//alert(inner_jfpgjg);
	var showJfpgjg = document.getElementById("showJfpgjg");
    showJfpgjg.innerText = changeJE(inner_jfpgjg);	
    

    /*
    *其中：取得房地产时所缴纳的契税金额
    *
    */
	var inner_qdfcqsje = document.all.qdfcqsje.value;
	if(inner_qdfcqsje =='NaN' || inner_qdfcqsje =="" )
		inner_qdfcqsje = changeJE(0.00);
	//alert(inner_jfpgjg);
	var showQdfcqsje = document.getElementById("showQdfcqsje");
	showQdfcqsje.innerText = changeJE(inner_qdfcqsje);	    
    
    /*
     *其中：取得房地产时所缴纳的印花税金额
     *
     */
 	var inner_qdfcyhsje = document.all.qdfcyhsje.value;
 	if(inner_qdfcyhsje =='NaN' || inner_qdfcyhsje =="" )
 		inner_qdfcyhsje = changeJE(0.00);
 	//alert(inner_jfpgjg);
 	var showQdfcyhsje = document.getElementById("showQdfcyhsje");
 	showQdfcyhsje.innerText = changeJE(inner_qdfcyhsje);	
     
	
	/**
	 * 3.获得每平米交易单价
	 * 规则：通过合同总价除以建筑面积，保留两位小数的结果
	 */
	var htzj = '<bean:write name="ClfswjghdxxlrForm" property="htzj"/>';
	var fwjzmj = '<bean:write name="ClfswjghdxxlrForm" property="fwjzmj"/>';
	var inner_mpmjydj = changeJE(Math.round(parseFloat(htzj)/parseFloat(fwjzmj)*100.00)/100.00);
	if(inner_mpmjydj =='NaN.00')
		inner_mpmjydj = changeJE(0.00);
	
	//显示每平米交易单价金额
	var showMpmjydj = document.getElementById("showMpmjydj");
    showMpmjydj.innerText = changeJE(inner_mpmjydj);
    putObjectValue(document.forms[0].mpmjydj,"",changeJE(inner_mpmjydj));
    
    //获得每平米交易单价金额
    var hidd_mpmjydj = document.getElementsByName("hidd_mpmjydj");
	hidd_mpmjydj= changeJE(inner_mpmjydj);
	
	/**
	 * 4.判断产权证标注建筑面积范围（通过【房屋建筑面积】域值判定）
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
	*5.设置普通住房最高限价
	*/
	var fwszqyValue = document.forms[0].fwszqydm.value;
	if(fwszqyValue != ""){
		document.forms[0].ptzfzgxj.value=changeJE(getPtfwszqyzgxj(fwszqyValue));
		putObjectValue(document.forms[0].ptzfzgxjSubmit,"",changeJE(getPtfwszqyzgxj(fwszqyValue)));
	}else{
		
		
	}
	/**
	*5.设置普通住房最高套总价（房屋所在区域金额）,ADDED BY ZHANGJ 2014.10.13
	*/
	if(fwszqyValue != ""){
		//document.forms[0].fwszqyje.value=changeJE(getFwszqyje(fwszqyValue));
		putObjectValue(document.forms[0].fwszqyjeSubmit,"",changeJE(getFwszqyje(fwszqyValue)));
	}
	/**
	*   
	   设置计税收入确认方式
	*/
	var mpmhdjgValue = document.forms[0].mpmhdjg.value;//每平米核定价格
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
	 * 6.控制划分标准
	 * 规则：
	      【普通住房】:满足（1） 或 （2）均可
		   （1） A．房屋容积率在【1.0（含）以上】、
	        B．房屋建筑面积小于等于140平米、
	        //C．【每平米交易单价】乘以1.2小于等于【普通住房最高限价】； 第一版规则（已取消）
	        C．取【每平米交易单价】和【核定单价】中的最大值，要求最大值小于等于【普通住房最高限价】。
	      
	           
	        （2）或者产权证所标注的房屋种类为【首次上市交易的已购公房】、【危改回迁房】、【合作社集资建设住房】、【安居房】、【康居房】、【绿化隔离地区农民安置住房】，则也为【普通住房】
	        
	        
	        【非普通住房】：除以上，其他均为非普通住房。
	  
	 */
	var fwrjl_high_checked = document.all.fwrjl[<%=Constants.FWRJL_HIGH%>].checked;
	var cqzbzjzmjfl_low_checked = document.all.cqzbzjzmjfl[<%=Constants.CQZBZ_JLMX_LOW%>].checked;
	var ptzfzgxjValue =  document.forms[0].ptzfzgxj.value;
	
	var fwcqzbzzflxdm = document.forms[0].fwcqzbzzflxdm.value;
	
	
	if(ptzfzgxjValue =='NaN')
		ptzfzgxjValue = changeJE(0.00);
	
	//六类住房变为七类住房，added by zhangj 2014.10.17
	//规则（2）
	var is6Type = false;//属于六类住房
	if(fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_06%> || 
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_07%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_08%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_09%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_10%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_11%> ||
	   fwcqzbzzflxdm == <%=Constants.FWCQZBZZFLX_13%>){
		
		is6Type = true;
	}
	
	
	
	//(规则1)
	if(ptzfzgxjValue != "" && (checkradio(fwrjl_checked,<%=Constants.FWRJL_HIGH%>) == true || 
	   checkradio(fwrjl_checked,<%=Constants.FWRJL_LOW%>) == true) && (
	   checkradio(cqzbzjzmjfl_checkes,<%=Constants.CQZBZ_JLMX_HIGH%>) == true || 
	   checkradio(cqzbzjzmjfl_checkes,<%=Constants.CQZBZ_JLMX_LOW%>) == true))
	{
		
		//var mpmjydj_ptzfzgxj = changeJE(Math.round(parseFloat(inner_mpmjydj) * 1.20 * 100.00)/100.00) - ptzfzgxj;  第一版规则
		
		var maxDJ = inner_mpmjydj;//最大单价
		if(parseFloat(maxDJ) < parseFloat(mpmhdjgValue)){
			maxDJ = mpmhdjgValue;
		}
		
		var mpmjydj_ptzfzgxj = parseFloat(maxDJ) - parseFloat(ptzfzgxjValue);
		
		//alert(fwrjl_high_checked+" : "+cqzbzjzmjfl_low_checked+" : "+ptzfzgxjValue+"　：　"+mpmjydj_ptzfzgxj);
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
	 * 7.显示合理费用
	 * 规则：住房装修费用+住房贷款利息+手续费+公证费
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
	//显示合理费用金额,added by zhangj
	//var showHlfy = document.getElementById("showHlfy");
    //showHlfy.innerText = changeJE(hlfyValue);
    //putObjectValue(document.forms[0].hlfy,"",changeJE(hlfyValue));
    
    //获得合理费用金额
    //var hidd_hlfy = document.getElementsByName("hidd_hlfy");
	//hidd_hlfy= changeJE(hlfyValue);
	
if(document.all.isQuery.value!="true"){
	/**
		added by zhangj
	 * 8.控制营业税征收方式
	 * 规则：
	 	A.如果【房屋权属转移类型】为【房屋赠与】，并且赠与明细为A、B、C、D，即下面四类，则营业税征收方式为【免征营业税】，其他均【征收营业税】。
		(a) 离婚财产分割。
		(b)无偿赠与配偶、父母、子女、祖父母、外祖父母、孙子女、外孙子女、兄弟姐妹。
		(c)无偿赠与对其承担直接抚养或者赡养义务的抚养人或者赡养人。
		(d)房屋产权所有人死亡，依法取得房屋产权的法定继承人、遗嘱继承人或者受遗赠人。
		B．如果申报方式选择含【提供购房发票】，则营业税征收方式为【差额征收营业税】；
		C．如果申报方式选择只含【提供法律文书】，则营业税征收方式为人工选择是【差额征收】还是【全额征收】。
		D.除上面条件外其他全部为【全额征收营业税】。
		E．【免征营业税】金额计算规则与全额征收营业税计算规则一致。
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
	  个人所得税控制方式：
	 * 规则： 	
	 	当【房屋权属转移类型】为“赠与”并且赠与明细为“BCD”即下面三项，为【免征个人所得税】，其他均为【征收个人所得税】；
		B．无偿赠与配偶、父母、子女、祖父母、外祖父母、孙子女、外孙子女、兄弟姐妹。
		C．无偿赠与对其承担直接抚养或者赡养义务的抚养人或者赡养人。
		D．房屋产权所有人死亡，依法取得房屋产权的法定继承人、遗嘱继承人或者受遗赠人。
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
	土地增值税控制方式：
	 * 规则： 
		A．当【房屋权属转移类型】为【房屋赠与】，并且赠与明细是B、C、D、E（即即赠与三代内直系亲属和同胞兄弟姐妹及赡养人、抚养人、继承、到税务机关报备的房产所有人、
		土地使用权所有人通过中国境内非营利的社会团体、国家机关将房屋产权、土地使用权赠与教育、民政和其他社会福利、公益事业），则为【不征土地增值税】，其他情况均为人工选择。
		B．人工选择的征收方式不做校验。	
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
 	印花税控制方式：
  */
// 	if(document.all.yhszsfs[1].checked ==true){
// 		putObjectValue(document.forms[0].yhszsfsSubmit,"",<%=Constants.YHSZSFS_FREE%>);	
// 	}else{
// 		putObjectValue(document.forms[0].yhszsfsSubmit,"",<%=Constants.YHSZSFS_ZS%>);
// 	}	
	
    //控制计税收入金额
	setJsje_jsfs();
    
    //按年加计数额=发票金额*年数*5%
    var sysdate = <%=DateUtils.getDate()%>+"";
    var fpszrq = document.all.fpszrq.value;
    
    if(fpszrq != null && fpszrq != ""){
    	
    	//检查合法性
    	if(!checkDate(fpszrq)){
    		alert("发票所载日期不合法,格式应为yyyyMMdd,例如20130101，请检查!");
    		document.all.fpszrq.select();
    																					
    	    old_anjjse = changeJE(0.00);
    	    document.forms[0].anjjse.value= changeJE(0.00);    	
        	old_anjjse_after_30_days=changeJE(0.00);    	
        	
    		return false;
    	}
    	
    	//发票所载日期不能大于当前日期
    	if(parseFloat(fpszrq) > parseFloat(sysdate)){
    		alert("发票所载日期不能大于当前日期，请检查!");
    		document.all.fpszrq.select();
    																							
    	    old_anjjse = changeJE(0.00);
    	    document.forms[0].anjjse.value= changeJE(0.00);    	
        	old_anjjse_after_30_days=changeJE(0.00);      		
    		
    		return false;
    	}
    	
    	var xcNSIsError = false;
    	var xcNS = getYueShu(sysdate,fpszrq);//系统日期和发票所载日期相差年数
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
    	//给old_anjjse赋值
    	    old_anjjse = changeJE(anjjse);    
    	    if(document.all.isQuery.value!="true"){
    	    	document.forms[0].anjjse.value= changeJE(anjjse);
    	    } 
    	    
    	//系统日期30日之后的计算结果
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
   
    //控制 土地增值税申报方式 及其子目录
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
//不足两位则，在月或者天前加0
function addZeroBefore(str){
	str = str+"";
	if(str.length == 1){
		 str="0"+str;
		 return str;
	}
	return str;
}




//置值方法
function putObjectValue(hiddProperty,td_id,obj_value)
{
	if(hiddProperty != null && hiddProperty != "")
	{
		hiddProperty.value=obj_value;
	}
	return true;
}

//判断单选是否选中
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

//通过值选中单选
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

//通过值选中复选框
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

//金额格式
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

//function  : 检查输入字符串是否由数字组成
//parameters: DigitString: String,待检查字符串的值
//			  totalLength: int, 数字的长度限制（不包括小数点）
//			  decimalLength: int，小数的长度限制
//          totalLength-decimalLength 整数部分的限制
//          isPositive 是否为非负数 true表示一定要是非负数，false表示一定要是负数，null表示任意 add by zhangp 2003-09-25
//return    : false: 字符串中包含除数字外的字符
//			  true : else
function checkNumber1(DigitString, totalLength, decimalLength,isPositive)
{
  var re = "";
  
  if(isPositive == "true" || isPositive == true)
  {//如果是非负数（值大于0）
      if(isNaN(DigitString*1) || !(DigitString*1 > 0))
          return false;
  }
  if(isPositive == "false" || isPositive == false)
  {//如果是负数
      if(isNaN(DigitString*1) || DigitString*1>=0)
          return false;
  }

  
  if (decimalLength!=null && decimalLength != 0)
  {   //小数不为空
      re = re+"\\.[\\d]{1,"+ decimalLength +"}"
  }

  if (totalLength == null)
  {   //未设定总长度
      re= "\\d*"+re;
  }
  else
  {   //设定总长度
      //当小数部分为空的情况下，就是判断数字的长度
      var intergerLength = totalLength;
      if (decimalLength!=null)
      {   //当小数部分不为空判断，整数的位数和小数的位数是否正确
          intergerLength = totalLength-decimalLength;
      }
      re="([\\d]{0,"+ intergerLength +"}"+re+"|[\\d]{0,"+intergerLength+"})";
  }
  re = new RegExp("^"+re+"$","g");

  //当字符串为空、位数不对、不能匹配成数字时
  if(re.exec(DigitString) == null)
  {
      return false;
  }
  return true;
}
</script>

<script type="text/javascript">

//===============================================================
		  //解析保存完成之后返回时的值
function parseSaveBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj){
		  
		  //alert("-----------0000000");
		  if(buyorSellInfo != null && buyorSellInfo != ""){
			  //alert("-----------1111");
			   //alert("infoArr 长度：："+buyorSellInfo);
			  if(buyorSellInfo.indexOf("^")>0){
				  var infoArr = buyorSellInfo.split("^");
				  //alert("infoArr 长度：："+infoArr.length);
				  
				  for(var index =0; index < infoArr.length;index ++){
					  var tempInfo = infoArr[index];
					  //alert("-----------每个人的信息tempInfo----"+tempInfo);
					  //调用解析方法，解析每个买卖方的信息并显示
					  setBuyorSellInfo(tempInfo,tableId,hidPropertyObj);
					  //alert("-----------22222222222");
				  }
			  }else{
				  setBuyorSellInfo(buyorSellInfo,tableId,hidPropertyObj);
			  }
		  }
		  
		  
	  }
	  
	  //设置买卖双方信息
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
				//alert("信息组数"+count);
				
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
						oneGroupInfo.push(DSzjdm);//证件类型代码
						oneGroupInfo.push(value_3);
						oneGroupInfo.push(value_4);
						oneGroupInfo.push(value_5);
						
						//alert("RETURN::"+DSzjdm);
						
						//设置并显示每组信息
						isSuccess = setOneGroupInfo(oneGroupInfo,tableobj);
						
						if(!isSuccess){
							alert("显示买卖人信息出错！");
							return isSuccess;
						}
						
						//拼接提交到后台的信息
						var tempOneGroupValue = value_0 +"~"+value_1+"~"+DSzjdm+"~"+value_3+"~"+value_4+"~"+value_5;
						if(zsIndex == 0){
							submitInfo = tempOneGroupValue;
						}else{
							submitInfo = submitInfo+"^"+tempOneGroupValue;
						}
						//重置信息
						oneGroupInfo = new Array();
					}
					//设置提交隐藏域的值
					if(hidPropertyObj != null && hidPropertyObj !=""){
						hidPropertyObj.value = submitInfo;
					}
				}
			}
			
			return true;
	  }
	  
	  //建委证件类型代码转地税证件类型代码
function getZjlxJwToDs(dm){
		  var res = zjlxJwToDs(dm);// this function in ../js/qscommon.js
		  
		  //如果没有返回转换结果，则做如下处理
		  if (res == null || res == ""){
			  res ="90";//其他
		  }
		  
		  return res;
	  }
	  
	  
	  
	  //把每组信息设置到td的innerHTML中显示
function setOneGroupInfo1(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	    		var otr = tableobj.insertRow();
	    		//第一行第一列  ”名称（姓名）“
	    		var mc_Cell=otr.insertCell();
	    		mc_Cell.innerHTML="名称（姓名）";
	    		mc_Cell.className ="2-td2-left";
	    		mc_Cell.WIDTH="20%";
	    		//第一行第二列 
	    		var mc_value_Cell=otr.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		mc_value_Cell.WIDTH="30%";
	    		//第一行第三列  “类别”
	    		var lb_Cell=otr.insertCell();
	    		lb_Cell.innerHTML="类别";
	    		lb_Cell.className ="2-td2-left";
	    		lb_Cell.WIDTH="20%";
	    		//第一行第四列  
	    		var lb_value_Cell=otr.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-center";
	    		lb_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第二行第一列 “证件类型”
	    		var otr2 = tableobj.insertRow();
	    		var zjlx_Cell=otr2.insertCell();
	    		zjlx_Cell.innerHTML="证件类型";
	    		zjlx_Cell.className ="2-td2-left";
	    		zjlx_Cell.WIDTH="20%";	    		
	    		//第二行第二列
	    		var zjlx_value_Cell=otr2.insertCell();
	    		zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		zjlx_value_Cell.WIDTH="30%";	    		
	    		//第二行第一列 “证件号码”
	    		var zjhm_Cell=otr2.insertCell();
	    		zjhm_Cell.innerHTML="证件号码";
	    		zjhm_Cell.className ="2-td2-left";
	    		zjhm_Cell.WIDTH="20%";		    		
	    		//第二行第一列 
	    		var zjhm_value_Cell=otr2.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-center";
	    		zjhm_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第三行第一列 “权利人份额”
	    		var otr3 = tableobj.insertRow();
	    		var fe_Cell=otr3.insertCell();
	    		fe_Cell.innerHTML="权利人份额";
	    		fe_Cell.className ="2-td2-left";
	    		fe_Cell.WIDTH="20%";
	    		//第三行第二列 
	    		var fe_value_Cell=otr3.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		fe_value_Cell.WIDTH="30%";	    		
	    		//第三行第三列 “联系人电话”
	    		var lxdh_Cell=otr3.insertCell();
	    		lxdh_Cell.innerHTML="联系人电话";
	    		lxdh_Cell.className ="2-td2-left";
	    		lxdh_Cell.WIDTH="20%";	    		
	    		//第三行第四列 
	    		var lxdh_value_Cell=otr3.insertCell();
	    		lxdh_value_Cell.innerHTML=arr[5];
	    		lxdh_value_Cell.className ="2-td2-center";
	    		lxdh_value_Cell.WIDTH="30%";	
	    		
	    		return true;
	  }
	  
	  //把每组信息设置到td的innerHTML中显示
function setOneGroupInfo(arr,tableobj){
		  
	  			if(tableobj == null || tableobj == ""){
	  				return false;
	  			}
	  			var tabLength = tableobj.rows.length;
	  			
	  			//初始化表头
	  			if(tabLength == 1){
		    		var otr = tableobj.insertRow();
		    		//第一行第一列  ”名称（姓名）“
		    		var mc_Cell=otr.insertCell();
		    		mc_Cell.innerHTML="名称（姓名）";
		    		mc_Cell.className ="2-td2-left";
		    		mc_Cell.WIDTH="30%";
		    		
		    		//第一行第二列  “类别”
		    		var lb_Cell=otr.insertCell();
		    		lb_Cell.innerHTML="类别";
		    		lb_Cell.className ="2-td2-left";
		    		lb_Cell.WIDTH="10%";
		    		
		    		//第一行第三列 “证件类型”
		    		var zjlx_Cell=otr.insertCell();
		    		zjlx_Cell.innerHTML="证件类型";
		    		zjlx_Cell.className ="2-td2-left";
		    		zjlx_Cell.WIDTH="10%";	
		    		
		    		//第一行第四列 “证件号码”
		    		var zjhm_Cell=otr.insertCell();
		    		zjhm_Cell.innerHTML="证件号码";
		    		zjhm_Cell.className ="2-td2-left";
		    		zjhm_Cell.WIDTH="20%";	
		    		
		    		//第一行第五列 “权利人份额”
		    		var fe_Cell=otr.insertCell();
		    		fe_Cell.innerHTML="权利人份额";
		    		fe_Cell.className ="2-td2-left";
		    		fe_Cell.WIDTH="10%";	
		    		
		    		//第一行第六列 “联系人电话”
		    		var lxdh_Cell=otr.insertCell();
		    		lxdh_Cell.innerHTML="联系人电话";
		    		lxdh_Cell.className ="2-td2-center";
		    		lxdh_Cell.WIDTH="20%";
	  			}
	  			
	    		//第n(n>=2)行第一列 
	    		var otr_n = tableobj.insertRow();
	    		var mc_value_Cell=otr_n.insertCell();
	    		mc_value_Cell.innerHTML=arr[0];
	    		mc_value_Cell.className ="2-td2-left";
	    		//mc_value_Cell.WIDTH="30%";

	    		//第n(n>=2)行第二列  
	    		var lb_value_Cell=otr_n.insertCell();
	    		lb_value_Cell.innerHTML=nsrlb_mc(arr[1]);
	    		lb_value_Cell.className ="2-td2-left";
	    		//lb_value_Cell.WIDTH="30%";
	    		
	    		
		
	    		//第n(n>=2)行第三列
	    		var zjlx_value_Cell=otr_n.insertCell();
	    		//zjlx_value_Cell.innerHTML=nsrzjlb_mc(arr[2]); 
	    		//zjlx_value_Cell.innerHTML=getZjlxmc(arr[2]);
	    		zjlx_value_Cell.innerHTML=getZjmc(arr[2]);
	    		zjlx_value_Cell.className ="2-td2-left";
	    		//zjlx_value_Cell.WIDTH="30%";
	    		
	    		
	    		//第n(n>=2)行第四列 
	    		var zjhm_value_Cell=otr_n.insertCell();
	    		zjhm_value_Cell.innerHTML=arr[3];
	    		zjhm_value_Cell.className ="2-td2-left";
	    		//zjhm_value_Cell.WIDTH="30%";
	    		
	    		

	    		//第n(n>=2)行第五列 
	    		var fe_value_Cell=otr_n.insertCell();
	    		fe_value_Cell.innerHTML=arr[4];
	    		fe_value_Cell.className ="2-td2-left";
	    		//fe_value_Cell.WIDTH="30%";	    		
	    		
	    		//第n(n>=2)行第六列 
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
		
		return "其他";
	}

//获得普通房屋所在区域最高限价
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
//获得普通房屋所在区域最高套总价（房屋所在区域金额），added by zhangj 2014.10.13
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
//当房屋核定类型为非住房时，进行相应的变化
function zfchange(){

	var isSaved=document.all.isSaved.value;
	var hasMAuthorise=document.all.hasMAuthorise.value;
	if(!(isSaved=="true" && hasMAuthorise==false)){
	document.all.operationType.value = "Query";
    document.forms[0].submit();
	}
}
//根据规划用途提示房屋核定类型
/*<option value="21">经济适用住房</option>
<option value="1">普通住宅</option>
<option value="3">别墅</option>																										
<option value="2">公寓</option>
<option value="4">商业</option>
<option value="5">写字楼</option>
<option value="6">工业厂房</option>
<option value="7">车库</option>
<option value="10">其它</option>*/
function ghytchange(){

	var ghyt=document.forms[0].ghyt.value;	
	if(ghyt==null||ghyt==""){
		document.all.fwhdlxdm[1].checked=true;
	}else{
/*		if(ghyt=="21"||ghyt=="1"||ghyt=="2"||ghyt=="3"){
			if(window.confirm('房屋划分用途是经济适用住房、普通住宅、别墅，请确认是住房？')){
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
			if(window.confirm('房屋划分用途是商业、写字楼、工业厂房、车库，请确认是非住房吗？')){
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
//将复选框中所有选中的选项拼接成一个字符串
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
//将一个字符串中的值，选中复选框
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
		document.all.zhankai.alt="收起";
	}else if(document.all.zhankai.value=="false"){
		document.all.fdczjxx.style.display="none";
		document.all.zhankai.value="true";
		document.all.zhankai.src="<%=static_file%>images/zbotton-jia2.gif";
		document.all.zhankai.alt="展开";
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














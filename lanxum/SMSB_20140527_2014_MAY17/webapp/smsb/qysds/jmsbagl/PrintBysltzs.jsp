<%@ page contentType="text/html; charset=GBK"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ page import="com.ttsoft.bjtax.sfgl.common.db.util.SfResourceLocator"%>
<html>
<head>
<title>打印送达回证</title>
<link href="../../../css/text.css" rel="stylesheet" type="text/css">
<script language=JavaScript src="../../../js/date.js">
    	</script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/treatImage.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/list.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/smsb_common.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/qysdsnew.js"></script>
<script language="JavaScript" type="text/JavaScript"
	src="../../../js/function.js"></script>
</head>
  <script language="JavaScript">
	function onKeyDown() { // 禁止刷新，回退
	 if ((event.keyCode == 8 && event.srcElement.type ==undefined) || (event.keyCode == 116)) {
	    event.keyCode = 0;
	    event.returnValue = false;
	   }
	}
	document.onkeydown = onKeyDown;
  
<%/*返回*/%>
function back(){
	document.forms[0].actionType.value = "Back";
	document.forms[0].submit();
}

function doPrint(){
	if(window.confirm("是否打印？")){        
		
		var temp = document.getElementById("printDiv");
		temp.style.display="none";
		window.print(); 
		temp.style.display="block"; 
	 }    
}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" align="center">


<html:form action="/webapp/smsb/qysds/jmsbagl/QysdsJmsbajlPrintAction.do" method="post">
	<html:hidden property="actionType" />
	<html:hidden property="basqwsh" />
	<html:hidden property="basqbh" />
	<html:hidden property="jmbasxdm" />
	<html:hidden property="nsrmc" />
	<html:hidden property="czlx" />
	<table width="800" align="center">
  <tr>
    <td>
      <div class=Section1 style='layout-grid:15.6pt'>
        <p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:center;line-height:150%;layout-grid-mode:char'>
          <b style='mso-bidi-font-weight:normal'>
            <span style='font-size:18.0pt;mso-bidi-font-size:
            12.0pt;line-height:150%;font-family:仿宋_GB2312'>
              企业所得税减免税不予备案通知书
              <span lang=EN-US style='color:black'>
                <o:p>
                </o:p>
              </span>
            </span>
          </b>
        </p>
        <p class=MsoNormal align=center style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:center;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:仿宋_GB2312'>
            <span style='mso-spacerun:yes'>
              &nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          </span>
          <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:仿宋_GB2312'>
            编号：
            <bean:write name="qysdsJmsbajlPrintForm" property="basqbh" />
            <span style='color:
            black'>
              <o:p>
              </o:p>
            </span>
          </span>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;margin-right:42.0pt;
        mso-margin-bottom-alt:auto;text-align:left;line-height:150%;layout-grid-mode:
        char'>
          <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312'>
            <bean:write name="qysdsJmsbajlPrintForm" property="nsrmc" />
            <span style='mso-bidi-font-size:10.5pt;line-height:150%;font-family:仿宋_GB2312'>
              ：
              <span lang=EN-US style='color:black'>
                <o:p>
                </o:p>
              </span>
            </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;text-indent:27.0pt;mso-char-indent-count:1.8;line-height:
        150%;layout-grid-mode:char'>
          <span style='font-size:15.0pt;mso-bidi-font-size:
          10.5pt;line-height:150%;font-family:仿宋_GB2312'>
            你单位报送的减免税备案表及有关资料收悉。根据《中国人民共和国企业所得税法》、实施条例和有关政策规定，经审核，你单位申请的
            <span style='mso-spacerun:yes'>
              &nbsp;“
            </span>
            <bean:write name="qysdsJmsbajlPrintForm" property="jmbasxmc" />
            ”
            <span lang=EN-US>
              <span style='mso-spacerun:yes'>
                &nbsp;
              </span>
            </span>
            项目不符合企业所得税税收优惠条件，不得享受相关税收优惠。
            <span lang=EN-US style='color:black'>
              <o:p>
              </o:p>
            </span>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312'>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          </span>
          <span style='font-family:仿宋_GB2312;color:black'>
            税务机关（签章）
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312'>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;
            </span>
            <bean:write name="qysdsJmsbajlPrintForm" property="shsj_y" />
          </span>
          <span style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312'>
            年
            <span lang=EN-US>
              <bean:write name="qysdsJmsbajlPrintForm" property="shsj_m" />
              <span style='mso-spacerun:yes'>
                &nbsp;
              </span>
            </span>
            月
            <span lang=EN-US>
              <bean:write name="qysdsJmsbajlPrintForm" property="shsj_d" />
              <span style='mso-spacerun:yes'>
                &nbsp;
              </span>
            </span>
            日
            <span lang=EN-US>
              <o:p>
              </o:p>
            </span>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;mso-ascii-font-family:
          仿宋_GB2312;mso-fareast-font-family:仿宋_GB2312'>
            &nbsp;
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <p class=MsoNormal align=left style='mso-margin-top-alt:auto;mso-margin-bottom-alt:
        auto;text-align:left;line-height:150%;layout-grid-mode:char'>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312'>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span>
            <span style='mso-spacerun:yes'>
              &nbsp;&nbsp;&nbsp;&nbsp;
            </span>
          </span>
          <span lang=EN-US style='font-size:15.0pt;mso-bidi-font-size:10.5pt;line-height:150%;font-family:
          仿宋_GB2312;color:black'>
            <o:p>
            </o:p>
          </span>
        </p>
        <br>
        <br>
        <br>
        <br>
        <br>
      </div>
    </td>
  </tr>
</table>
	<table align="center" id="printArea">
	
	<tr>
		<td>
		<br>
		<br>
		<div align="center">
			<span style="font-size: 22.0pt; font-family: 仿宋_GB2312; letter-spacing: 1.0pt">
				<bean:write name="qysdsJmsbajlPrintForm" property="swjgzzjgmc" /><br>税务文书送达回证
			</span>
		</div>
	<br>
	<br>
	<div align="center">
	<table width="640" height="800" border="0" cellspacing="0" cellpadding="0" style="border-collapse: collapse; border: medium none; margin-left: .15pt" id="table1">
	<tr>
		<td class="2-td2-t-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				送达文书名称
			</span>
		</td>
		<td class="2-td2-t-printcenter">
			<table>				
				<tr>
					<td>
						<div align="left">
							<span style="font-family: 宋体">
								企业所得税减免税不予备案通知书<br>
								（<bean:write name="qysdsJmsbajlPrintForm" property="basqbh" />）
							</span>
						</div>	
					</td>
				</tr>
			</table>			
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft" height="80">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人
			</span>
		</td>
		<td class="2-td2-printcenter">
			<span style="font-family: 宋体">
				<bean:write name="qysdsJmsbajlPrintForm" property="nsrmc" />
			</span>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft" height="80">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312"> 
				送达地点
			</span>
		</td>
		<td class="2-td2-printcenter">
			&nbsp;
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				代收人代收理由、签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				受送达人拒收理由
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				见证人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				送达人签名或盖章
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td class="2-td2-printleft">
			<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
				填发税务机关
			</span>
		</td>
		<td class="2-td2-printcenter">
			<table height="80">
				<tr>
					<td>
						<div align="right">
							<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							（签章）
							</span>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<span style="font-size: 16.0pt; line-height: 150%; font-family: 仿宋_GB2312">
							年&nbsp;&nbsp;月&nbsp;&nbsp;日&nbsp;&nbsp;时&nbsp;&nbsp;分
						</span>
					</td>
				</tr>
			</table>			
		</td>
	</tr>
	</table>
	</div>
	</td>
	</tr>
	</table>
	</div>
	<br>
	
	<div id="printDiv" align="center">
	<table width="100%" border="0" align="center">
		<tr align="center">

			<td width="20%">&nbsp;</td>

			<td>
				<a style="cursor:hand" onClick="doPrint()" onMouseOut="MM_swapImgRestore()"
	            onMouseOver="MM_swapImage('dayin','','<%=SfResourceLocator.getContextPath(request)%>images//dayin2.jpg',1)">
	            <img src="<%=SfResourceLocator.getContextPath(request)%>images//dayin1.jpg" name="dayin" width="79" height="22" border="0" id="dayin">
	            </a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td>
				<a style="cursor:hand" onClick="back()" onMouseOut="MM_swapImgRestore()"
	            onMouseOver="MM_swapImage('fanhui','','<%=SfResourceLocator.getContextPath(request)%>images//fanhui2.jpg',1)">
	            <img src="<%=SfResourceLocator.getContextPath(request)%>	images//fanhui1.jpg" name="fanhui" width="79" height="22" border="0" id="fanhui">
	            </a>
	            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td width="20%">&nbsp;</td>
		</tr>
	</table>
	</div>
	
	
</html:form>
</body>
</html>

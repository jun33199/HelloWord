<%@ page contentType="text/html; charset=GBK" %>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/shenbao.tld" prefix="declare" %>

<jsp:useBean type="com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web.QysdsnbForm" scope="session" id="qysdsnbForm"/>
<%
	String static_contextpath = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<html>
<head>
<title>��ҵ����˰�����˰�걨��</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<link href="<%=static_contextpath%>css/text.css" rel="stylesheet" type="text/css">
<style>
input {
    font-size: 9pt;
    text-align: right;
}
</style>

</head>
<script language="javascript">

<%//��ĳ�д�ȡ����%>
function getValue(hc)
{
    var o = document.forms[0].name + "." + hc;
    var obj = eval(o);
    if (trim(obj.value)=='')
    {
         return "0.00";
    }
    return obj.value;
}

<%//����ĳһ�дε�value%>
function setValue(hc, value)
{
    var o = document.forms[0].name + "." + hc;

    eval(o).value = round(value);
    formatCurrency(eval(o),0);
}
function round(value){
    var temp = value * 100;
    temp = Math.round(temp);
    return temp/100;
}

<%//���С������м�λ����%>
function checkPoint(text , point){
    var value = trim(text+"");
    var index =value.indexOf(".");
    if(index >= 0){
        var x = value.substring(index+1);
        if(x.length > point){
            return false;
        }
    }
    return true;
}
<%//�������λ��%>
function checkIntPoint(text,point){
var value = parseFloat(text);
var t = 1
for(var i=1;i<=point;i++){
   t *=  10;
}
return (value < t);

}

</script>


<script language="JavaScript" type="text/JavaScript" src="<%=static_contextpath%>js/swapImages.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Stack.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/Bolan.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/MathString.js"></script>
<script language="JavaScript" type="text/JavaScript" src="js/function.js"></script>
<script language="JavaScript" >
var tabIndex = 1;
<%//����ID��ö�Ӧ���д�%>
function getHcByID(id){
      var index = -1;
      for(var i=0;i<id.length;i++){
         if(id.charAt(i) == '_'){
              index = i;
         }
      }

      if(index < 0){
        alert("ID:"+id+",����ȷ��")
        return false;
      }
      return parseInt(id.substring(index+1,id.length));

}
</script>


<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" style="margin: 0">

<html:form action="/qysdsnb.do" method="POST">

<table width="98%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td align="center" valign="top">

        <jsp:include page="../include/SbHeader.jsp" flush="true" >
            <jsp:param name="name" value="��ҵ����˰�����˰�걨��" />
        <jsp:param name="help_url" value="help/wssb/sbzl/qysdsnb/qysdsnb-000.htm"/>
        </jsp:include>
    </td>
  </tr>



  <tr>
    <td valign="top" align="center">
     <br>
      <html:errors/>
      <br>

<html:hidden property="actionType" value="Show" />

<logic:equal name="qysdsnbForm" property="actionType" value="InitError">
    <div align="center">
        <img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="javascript:return fnReturn()" style="cursor:hand">
    </div>
</logic:equal>


<logic:notEqual name="qysdsnbForm" property="actionType" value="InitError">

<html:hidden property="djzclxdm" />
<html:hidden property="jsjdm" />
<html:hidden property="qymc" />
<html:hidden property="qylxdh" />

<html:hidden property="qyzssl" />
<html:hidden property="qyzssllx" />

<html:hidden property="skssksrq" />
<html:hidden property="skssjsrq" />
<html:hidden property="isXzqy" />


<div id="title">
<table align="center" cellspacing="0" class="table-99">
  <tr>
   <td valign="top" class='1-td1'>
<a href="#"  onClick="hideAllSbb();sbb_nb.style.display='';return false;"><font color="blue">�����˰�걨��</font></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href="#"  onClick="hideAllSbb();sbb_cwzb.style.display='';return false;"><font color="blue">��ҵ��������ָ�������</font></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<%

     //�Ǽ�ע�����ʹ���
     String djzclxdm = qysdsnbForm.getDjzclxdm();
     int sydwshttLX  = 0;
     if(djzclxdm.equals(com.ttsoft.bjtax.shenbao.constant.CodeConstant.DJZCLXDM_SYDW)){
        sydwshttLX = 2;
     }else if(djzclxdm.equals(com.ttsoft.bjtax.shenbao.constant.CodeConstant.DJZCLXDM_SHTT)){
        sydwshttLX = 1;
     }

     if(sydwshttLX > 0){
%>
<a href="#"  onClick="hideAllSbb();sbb_sydw.style.display='';sydwsj.style.display='';return false;"><font color="blue">��ҵ��λ��������������걨��</font></a>
<%
          }
%>
   </td>
  </tr>
</table>
</div>
<br>

<table align="center" cellspacing="0" class="table-99">
 <tr class="black9">
    <td colspan="2"><div align="left"> �걨���ڣ�<declare:write name="qysdsnbForm" property="curtime" /></div></td>
    <td colspan="2"><div align="left"> �걨�������ڣ�
    <declare:write name="qysdsnbForm" property="skssksrq" /> �� <declare:write name="qysdsnbForm" property="skssjsrq" /></div>
    </td>
    <td colspan="2"><div align="right"> ��λ�������Ԫ</div></td>
 </tr>
 <tr>
    <td nowrap class="2-td2-t-left"><div align="right"> ���������&nbsp;&nbsp;</div></td>
    <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="qysdsnbForm" property="jsjdm" /></div></td>
    <td nowrap class="2-td2-t-left"><div align="right">�걨��λ����&nbsp;&nbsp;</div></td>
    <td nowrap class="2-td2-t-left"><div align="left">&nbsp;&nbsp;<bean:write name="qysdsnbForm" property="qymc" /></div></td>
    <td nowrap class="2-td2-t-left"><div align="right"> ��ϵ�绰&nbsp;&nbsp;</div></td>
    <td nowrap class="2-td2-t-center"><div align="left">&nbsp;&nbsp;<bean:write name="qysdsnbForm" property="qylxdh" /></div></td>
</tr>
<%
     if(sydwshttLX > 0){
%>
 <tr id="sydwsj" style="display:none">
    <td nowrap class="2-td2-left"><div align="right">��λ����&nbsp;&nbsp;</div></td>
    <td nowrap class="2-td2-left"><div align="left">&nbsp;&nbsp;<%=(sydwshttLX == 1)?"�������":"��ҵ��λ"%></div></td>
    <td nowrap class="2-td2-left" colspan="2">&nbsp;</td>
    <td nowrap class="2-td2-left"><div align="right">Ӧ˰����ѡ��&nbsp;&nbsp;</div></td>
    <td nowrap class="2-td2-center"><div align="left">&nbsp;&nbsp;
                      <input name="sfyyssr" type="radio" value="1" <%if(qysdsnbForm.getSfyyssr().equals("1")){out.print("checked ");}%>> ��
                      <input name="sfyyssr" type="radio" value="0" <%if(qysdsnbForm.getSfyyssr().equals("0")){out.print("checked ");}%>> ��

    </div></td>
</tr>
<%
     }
%>

</table>

<br>

<div id="sbb_nb">
<table cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">��ҵ����˰�����˰�걨��</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
        <br>
    <declare:sbbtable formName="qysdsnbForm" scope="session" defineList="nbDefineList" bqljs="nbbqljs" hc="nbhc"  sbbName="nb" xmmc="nbxmmc" />
    <br>
    <div align="center">
        <img src="<%=static_contextpath%>images/baocun1.jpg" onmouseover="this.src='<%=static_contextpath%>images/baocun2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/baocun1.jpg'" alt="����" onclick="javascript:return fnSave();" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="javascript:return fnRemove();" style="cursor:hand">
                      &nbsp;&nbsp;&nbsp;&nbsp;
		<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="javascript:return fnReturn()" style="cursor:hand">
    </div>
    <br>
     </td>
  </tr>
</table>

<script language="javascript">


<!-- ----------begin  �����д�78Ϊ0����Ϊ����------------   -->
function getHC78State()
{
  var v = trim(document.forms[0].nb_B_78.value);
  if(v != ""){
     if(parseFloat(v) <= 0.00000) return -1;
  }
  return 1;
}
<!-- -----------end   �����д�78Ϊ0����Ϊ����---------   -->

var state78=getHC78State(); <%//1 ��ʾ�дδ���0��-1 ��ʾС�ڵ���0%>

<%//--------------------��ʼ�������걨��tabIndex��onchange�¼�%>
for(var i=1;i<=document.forms[0].nbhc.length;i++){
  if (eval("document.forms[0].nb_B_"+i).readOnly)
    continue;

  eval("document.forms[0].nb_B_"+i).tabIndex = tabIndex++;
  eval("document.forms[0].nb_B_"+i).onchange = computenb;
  document.forms[0].nbhc[i-1].tabIndex = -1;
}

<!-- ----------begin   computenb-------------   -->
function computenb()
{
   var id = trim(window.event.srcElement.id+"");

   if(!checknbValue(id)){
      window.event.srcElement.select();
      return false;
   }else{
      computenbGS(id);
   }

<%
//������Ƕ�������
if(!qysdsnbForm.getQyzssllx().equals("4")){
%>
   compute78();
<%}%>
}
<!-- ----------------end   computenb--------------   -->

<!-- -------------begin   compute78-----------------   -->
function compute78(){
   var newState = getHC78State();
<%
//--------------------����Ƕ���
if(!qysdsnbForm.getQyzssllx().equals("2")){
%>

      if(newState < 0){
    <%//�����д�79-95%>
    for(var i=79;i<=95;i++){
       if(i>82 && i<= 86) continue;

       eval("document.forms[0].nb_B_"+i).readOnly = false;
       eval("document.forms[0].nb_B_"+i).className = null;
    }
      }else{
    <%//�����д�80-95%>
        for(var i=80;i<=95;i++){
           if(i>=82 && i<= 86) continue;
           eval("document.forms[0].nb_B_"+i).onchange = computenb;
        }
        document.forms[0].nb_B_80.readOnly=true;
        document.forms[0].nb_B_80.className='inputnoborder';

        document.forms[0].nb_B_88.readOnly=true;
        document.forms[0].nb_B_88.className='inputnoborder';

        document.forms[0].nb_B_79.readOnly=true;
        document.forms[0].nb_B_79.className='inputnoborder';


        document.forms[0].nb_B_95.readOnly=true;
        document.forms[0].nb_B_95.className='inputnoborder';

      }

<%}%>

      state78 = newState;
}
<!-- --------------end   compute78----------   -->

<!-- ------------------begin   computenbGS----------   -->
function computenbGS(myid)
{
  var stack = new Stack();
  var strmyid = "" + myid;
  var hc = strmyid;
<%
//--------------------����Ƕ���
if(qysdsnbForm.getQyzssllx().equals("2")){
%>
  

<%}%>

//--------------------����Ƕ���
  var isXzqy = document.forms[0].isXzqy.value;
  for(var i=0;i<nb_jsgss.length;i++)
  {
      if(nb_jsgss[i].isInExpress(hc))
      {
          var retBolan = nb_jsgss[i].compute();
          if(retBolan != null)
          {
              var id = retBolan.operator+"";
              var v = retBolan.value;
              var nbhc = parseInt(getHcByID(id)+"");	//�걨�дε�����
              if(v<0 && nbhc>=76 && nbhc!=92 && nbhc!=94){
                v = 0;
              }
              if(nbhc == 80)
              {
                 if(isXzqy=="1")
                 {
                     setValue("nb_B_81", v * 0.10);
                 }
              }
              setValue(id, v);

<%
//������Ƕ������գ�����˰��
if(!qysdsnbForm.getQyzssllx().equals("4")){
%>

              if(nbhc == 78)
              {
   computeSysl();
              }
<%}%>
              stack.push(retBolan.operator);
          }
      }
  }
  while(stack.length() > 0)
  {
      var nHc = stack.pop();
      computenbGS(nHc);
  }
}
<!-- --------------end   computenbGS--------------   -->


<%//����걨������%>
function checknbValue(id){
  var hc = getHcByID(id)
  var obj = eval("document.forms[0].nb_B_"+hc);
  var xmmc = eval("document.forms[0].nbxmmc_"+hc).value;
  var value = trim(obj.value);

  if(value == ""){
    return true;
  }
  if(!checkvalue(obj,1))
  {
    return false;
  }

  var msg = xmmc+"( "+hc+" ) ";
  <%//����������%>
  if(isNaN(value)){
     alert(msg+"ӦΪ������");

     return false;
  }

  if(hc != 8 && hc != 13 && hc != 14 && hc != 16 && hc != 25 && hc != 89 && hc != 92 && hc != 94 && parseFloat(value)<0){
    alert(msg+"����Ϊ����");
    return false;
  }

  if(hc == 79){
    var sl = parseFloat(value);
    if( sl > 1){
           alert(msg+"���ܴ��� 1");
           return false;
    }
  }

  if(hc == 1){
    if( parseFloat(value) < 0){
           alert(msg+"ӦΪ����");
           return false;
    }
  }

  if(hc == 95){
    if( parseFloat(value) < 0){
           alert(msg+"ӦΪ�Ǹ���");
           return false;
    }
  }

  if(hc == 96 || hc == 103 || hc == 104){
     <%//�����ǷǸ�����%>
     if(!(fnIsIntNum(value)||parseFloat(value) == 0.0)){
     alert(msg+" ӦΪ�ǷǸ�����");
     return false;
     }
  }

  if(hc == 96){
    if( parseFloat(value) > 6000){
           if (!confirm("������¼���ְ����������6000����ȷ�ϣ�"))
           return false;
    }
  }


  if(hc == 79){
      if(!checkPoint(value,4)){
               alert(msg+"С��λ�����ܴ���4λ");
               return false;
      }
      return true;
  }else{
      if(!checkPoint(value,2)){
               alert(msg+"С��λ�����ܴ���2λ");
               return false;
      }
  }
  if(!checkIntPoint(value,13)){
           alert(msg+"����λ�����ܴ���13λ");
           return false;
  }
  formatCurrency(obj,0);
  return true;

}

<!-- ---------begin   computeHc62-------------   -->
    <%//����62%>
    function computeHc62(){
<%
//����Ƕ�������
if(qysdsnbForm.getQyzssllx().equals("2")){
%>
        <%//����%>
        var sr = getNumber(document.forms[0].nb_B_1);
        <%//������%>
        var cyl = getNumber(document.forms[0].qyzssl);
        <%//16%>
        var v_hc16 = getNumber(document.forms[0].nb_B_16);
        <%//21%>
        var v_hc21 = getNumber(document.forms[0].nb_B_21);
        <%//22%>
        var v_hc22 = getNumber(document.forms[0].nb_B_22);
        <%//57%>
        var v_hc57 = getNumber(document.forms[0].nb_B_57);


        <%//Ӧ��˰���ö�%>
        var v_hc62 = sr*cyl+v_hc16+v_hc21+v_hc22-v_hc57;
         setValue('nb_B_62',v_hc62);
        
<%}%>        
}

<!-- ---------end   computeHc62-------------   -->

<!-- ---------begin   computeSysl-------------   -->
    <%//��������˰��%>
    function computeSysl(){
<%
//����Ƕ�������
if(qysdsnbForm.getQyzssllx().equals("2")){
%>
        <%//����%>
        var sr = getNumber(document.forms[0].nb_B_1);
        <%//������%>
        var cyl = getNumber(document.forms[0].qyzssl);
        <%//Ӧ��˰���ö�%>
        var ynssde = sr*cyl;
        <%//�о��²�Ʒ���¼������¹��շ��õֿ۶�%>
       // var dke = getNumber(document.forms[0].nb_B_77);
        
        <%//�ֿۺ��Ӧ��˰���ö�%>
        //var dkhynssde = ynssde - dke;
        var dkhynssde = getNumber(document.forms[0].nb_B_78);

        <%//��������˰�ʡ�������������������������%>
        var sysl;
        <%
        if (qysdsnbForm.getGxqy().equals("1"))
        {
        %>
            sysl = 0.15;
        <%
        }
        else
        {
        %>
            if(dkhynssde <= 30000.00000000){
                sysl = 0.18;
            }else if(dkhynssde <= 100000.00000000){
                sysl = 0.27
            }else{
                sysl = 0.33;
            }
        <%
        }
        %>

      if(dkhynssde < 0)
      {
          dkhynssde = 0;
      }

      //  setValue('nb_B_26',ynssde);
        setValue('nb_B_79',sysl);
<%

}else if(qysdsnbForm.getQyzssllx().equals("3")){
//�������������
%>
        <%//�о��²�Ʒ���¼������¹��շ��õֿ۶�%>
        var dkhynssde = getNumber(document.forms[0].nb_B_78);

        //��������˰�ʡ�������������������������
        var sysl;
        if(dkhynssde <= 30000.00000000){
            sysl = 0.18;
        }else if(dkhynssde <= 100000.00000000){
            sysl = 0.27
        }else{
            sysl = 0.33;
        }
        setValue('nb_B_79',sysl);

<%
}else{
%>
        setValue('nb_B_79',document.forms[0].qyzssl.value);

<%
}
%>

    }

<!-- --------------end   computeSysl-----------   -->

<!-- ----------------begin ��ʼ��ҳ��-------------------------   -->

<%
//-------------------------------------��ʼ��������ҵ
    //�����Ƿ��걨�� true-���ڵ�һ���걨 false-�����Ѿ��걨��
    boolean isNotSb = (qysdsnbForm.getNbbqljs() == null);

    //�Ʋ���ʧ�۳���
    java.math.BigDecimal sp24 = qysdsnbForm.getSpzg24();
    if(sp24 != null){
%>
  document.forms[0].nb_B_24.readOnly=false;
  document.forms[0].nb_B_24.className=null;
<%
        if(isNotSb){
%>
  document.forms[0].nb_B_24.value=<%=sp24%>;
<%
        }
    }
%>


<%
    //�ֲ���ǰ��ȿ��� 63
    java.math.BigDecimal sp63 = qysdsnbForm.getSpzg63();
    if(sp63 != null){
%>
  document.forms[0].nb_B_63.readOnly=false;
  document.forms[0].nb_B_63.className=null;
<%
        if(isNotSb){
%>
  document.forms[0].nb_B_63.value=<%=sp63%>;
<%
        }
    }
%>

<%
    //��˰�ļ���ת������ 69
    java.math.BigDecimal sp69 = qysdsnbForm.getSpzg69();
    if(sp69 != null){
%>
  document.forms[0].nb_B_69.readOnly=false;
  document.forms[0].nb_B_69.className=null;
<%
        if(isNotSb){
%>
  document.forms[0].nb_B_69.value=<%=sp69%>;
<%
        }
    }
%>

<%
    //�о��²�Ʒ���¼������¹��շ��õֿ۶� 77
    java.math.BigDecimal sp77 = qysdsnbForm.getSpzg77();
    if(sp77 != null){
%>
  document.forms[0].nb_B_77.readOnly=false;
  document.forms[0].nb_B_77.className=null;
<%
        if(isNotSb){
%>
  document.forms[0].nb_B_77.value=<%=sp77%>;
<%
        }
    }
%>

<%
    //����������˰��   81
    java.math.BigDecimal sp81 = qysdsnbForm.getSpzg81();
    if(sp81 != null){
%>
  document.forms[0].nb_B_81.readOnly=false;
  document.forms[0].nb_B_81.className=null;
<%
        if(isNotSb){
%>
           var isXzqy = document.forms[0].isXzqy.value;
           if (isXzqy != "1")  {  // ����������ҵ
               //document.forms[0].nb_B_81.value=<%=sp81%>;
           }
<%
        }
    }
%>

<%
    //������������豸Ͷ�ʵ���˰�� 82
    java.math.BigDecimal sp82 = qysdsnbForm.getSpzg82();
    if(sp82 != null){
%>
  document.forms[0].nb_B_82.readOnly=false;
  document.forms[0].nb_B_82.className=null;
<%
        if(isNotSb){
%>
  document.forms[0].nb_B_82.value=<%=sp82%>;
<%
        }
    }
%>

<%//-------------------------����Ƕ�������----------%>
<logic:equal name="qysdsnbForm" property="qyzssllx" value="2">
   <%//��ʼ������%>
   var obj;
<%//modified by guzx%>
   for(var i=1;i<62;i++){
    obj = eval("document.forms[0].nb_B_"+i);
    obj.readOnly = true;
    obj.className = 'inputnoborder';
   }

<%//added by guzx%>

   for(var i=16;i<23;i++){
    obj = eval("document.forms[0].nb_B_"+i);
    obj.readOnly = false;
    obj.className = null;
   }

    document.forms[0].nb_B_58.readOnly=false;
    document.forms[0].nb_B_58.className=null;
    document.forms[0].nb_B_77.readOnly=true;
    document.forms[0].nb_B_77.className='inputnoborder';
//    document.forms[0].nb_B_81.readOnly=true;
//    document.forms[0].nb_B_81.className='inputnoborder';
    document.forms[0].nb_B_82.readOnly=true;
    document.forms[0].nb_B_82.className='inputnoborder';

<%//end added by guzx%> 
   var nb_gss = new Array();
   
<%//modified by guzx%>
   //57
   nb_gss[0] = nb_jsgss[7];
   
   //62=1�С���ҵ������+16+21+22-57
   nb_gss[1] = new MathString("nb_B_62=nb_B_1*<bean:write name="qysdsnbForm" property="qyzssl"/>+nb_B_16+nb_B_21+nb_B_22-nb_B_57");
   
   //64
   nb_gss[2] = nb_jsgss[9];

   //76
   nb_gss[3] = nb_jsgss[10];
   
   //78
   nb_gss[4] = new MathString("nb_B_78=nb_B_76");
   
   for(var i=0;i<nb_jsgss.length - 12;i++){
    nb_gss[i+5] = nb_jsgss[i+12];
   }

   nb_jsgss = nb_gss;


    document.forms[0].nb_B_1.readOnly=false;
    document.forms[0].nb_B_1.className=null;
    document.forms[0].nb_B_1.onchange=function(){
        var v = trim(document.forms[0].nb_B_1.value);
        var xmmc = document.forms[0].nbxmmc_1.value;
        <%//����������%>
        if( v == ""){
           alert(xmmc+"( 1 ) ӦΪ������");
           return false;
        }

        if(!checknbValue(document.forms[0].nb_B_1.id)){
           document.forms[0].nb_B_1.select();
            return false;
        }

    //    setValue("nb_B_6", v);
   //     setValue("nb_B_15", v);

        computenbGS("nb_B_57");
        computeSysl();
        compute78();
        
        
        return true;
    }


</logic:equal>


//-----------------------����Ƕ�������
<logic:equal name="qysdsnbForm" property="qyzssllx" value="4">
  //�˶�˰��
  document.forms[0].nb_B_88.value=document.forms[0].qyzssl.value;

   //��ʼ������
   var obj;
   for(var i=1;i<88;i++){
    obj = eval("document.forms[0].nb_B_"+i);
    obj.readOnly = true;
    obj.className = 'inputnoborder';
   }

   var nb_gss = new Array();
   nb_gss[0] = nb_jsgss[13];
   nb_gss[1] = nb_jsgss[14];
   nb_gss[2] = nb_jsgss[15];
   nb_jsgss = nb_gss;

    document.forms[0].nb_B_88.readOnly=false;
    document.forms[0].nb_B_88.className=null;
    document.forms[0].nb_B_92.readOnly=false;
    document.forms[0].nb_B_92.className=null;
    document.forms[0].nb_B_94.readOnly=false;
    document.forms[0].nb_B_94.className=null;

    document.forms[0].nb_B_95.readOnly=false;
    document.forms[0].nb_B_95.className=null;

   computenbGS("nb_B_89");

</logic:equal>

<%
//-----------------------------------������Ƕ���
if(!qysdsnbForm.getQyzssllx().equals("4")){
%>


<%//������Ӫ�ɷ�83-86%>
document.forms[0].nb_B_83.onclick=function(){
    hideAllSbb();
    title.style.display='none';
    sbb_lygf_1.style.display = "";
}
document.forms[0].nb_B_83.className='txtinput-link';
document.forms[0].nb_B_84.onclick=function(){
    hideAllSbb();
    title.style.display='none';
    sbb_lygf_2.style.display = "";
}
document.forms[0].nb_B_84.className='txtinput-link';
document.forms[0].nb_B_85.onclick=function(){
    hideAllSbb();
    title.style.display='none';
    sbb_lygf_3.style.display = "";
}
document.forms[0].nb_B_85.className='txtinput-link';
document.forms[0].nb_B_86.onclick=function(){
    hideAllSbb();
    title.style.display='none';
    sbb_lygf_4.style.display = "";
}
document.forms[0].nb_B_86.className='txtinput-link';
<%
}
%>
<!-- ----------------end ��ʼ��ҳ��-------------------------   -->

</script>

</div>

<div id="sbb_cwzb" style="display:none">

<table cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">��ҵ��������ָ�������</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
        <br>
    <declare:sbbtable formName="qysdsnbForm" scope="session" defineList="qycwzbDefineList" ncs="qycwzbncs" nms="qycwzbnms" hc="qycwzbhc"  sbbName="cwzb" xmmc="qycwzbxmmc" />
    <br>
     </td>
  </tr>
</table>



<script language="javascript">
//���ò���ָ���tabIndex
for(var i=1;i<=document.forms[0].qycwzbhc.length;i++){
  document.forms[0].qycwzbhc[i-1].tabIndex = -1;

  eval("document.forms[0].cwzb_C_"+i).tabIndex = tabIndex++;
  eval("document.forms[0].cwzb_C_"+i).onchange = computecwzb;
  eval("document.forms[0].cwzb_M_"+i).tabIndex = tabIndex++;
  eval("document.forms[0].cwzb_M_"+i).onchange = computecwzb;
}


function computecwzb()
{
 var id = trim(window.event.srcElement.id+"");
 if(!checkcwzbValue(id)){
    window.event.srcElement.select();
    return false;
 }else{
     computecwzbGS(id);
 }
}
function computecwzbGS(myid)
{
  var stack = new Stack();
  var strmyid = "" + myid;
  var hc = strmyid;
  for(var i=0;i<cwzb_jsgss.length;i++)
  {
      if(cwzb_jsgss[i].isInExpress(hc))
      {
          var retBolan = cwzb_jsgss[i].compute();
          if(retBolan != null)
          {
              setValue(retBolan.operator, retBolan.value);
              stack.push(retBolan.operator);
          }
      }
  }
  while(stack.length() > 0)
  {
      var nHc = stack.pop();
      computecwzbGS(nHc);
  }
}


//������ָ�������
function checkcwzbValue(id){
  var hc = getHcByID(id)

  var obj = eval("document.forms[0]."+id);
  var xmmc = eval("document.forms[0].qycwzbxmmc_"+hc).value;
  var value = trim(obj.value);
  if(value == ""){
    return true;
  }
  if(!checkvalue(obj,1))
  {
    return false;
  }
  var msg = xmmc+"( "+hc+" ) ";
  //����������
  if(isNaN(value)){
     alert(xmmc+"( "+hc+" ) ӦΪ������");
     return false;
  }
/*
  if(hc == 40 && !isDigit(value))
  {
    alert(msg+"����Ϊ������");
    return false;
  }
  if(hc != 6 && parseFloat(value)<0){
    alert(msg+"����Ϊ����");
    return false;
  }
*/
  if(!checkPoint(value,2)){
           alert(msg+"С��λ�����ܴ���2λ");
           return false;
  }
  if(!checkIntPoint(value,13)){
           alert(msg+"����λ�����ܴ���13λ");
           return false;
  }


//  obj.value = round(value);
    formatCurrency(obj,0);
    return true;
}


</script>
</div>


<div id="sbb_sydw" style="display:none">
<%
     if(sydwshttLX > 0){
%>
<table cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">��ҵ��λ��������������걨��</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
        <br>
    <declare:sbbtable formName="qysdsnbForm" scope="session" defineList="sydwsbbDefineList" bqljs="sydwbqljs" hc="sydwhc"  sbbName="sydw" xmmc="sydwxmmc" />
    <br>
    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr class="black9">
         <td colspan="3">�������ϣ�</td>
      </tr>
      <tr class="black9">
        <td nowrap class="2-td2-t-left">��3�е���׼�ĺ�:<html:text property="sydwpzwh3" styleClass="inputnormal" maxlength="40" onchange="//if(!isDigit(this.value)) {alert('��������ȷ��׼�ĺţ�');this.focus();this.select();return false;}"/></td>
        <td nowrap class="2-td2-t-left">��4�е���׼�ĺ�:<html:text property="sydwpzwh4" styleClass="inputnormal" maxlength="40" onchange="//if(!isDigit(this.value)) {alert('��������ȷ��׼�ĺţ�');this.focus();this.select();return false;}"/></td>
        <td nowrap class="2-td2-t-center">��5�е���׼�ĺ�:<html:text property="sydwpzwh5" styleClass="inputnormal" maxlength="40" onchange="//if(!isDigit(this.value)) {alert('��������ȷ��׼�ĺţ�');this.focus();this.select();return false;}"/></td>
      </tr>
    </table>
    <br>

     </td>
  </tr>
</table>

<script language="javascript">
//������ҵ��λ��tabIndex
for(var i=1;i<=document.forms[0].sydwhc.length;i++){
  document.forms[0].sydwhc[i-1].tabIndex = -1;

  var oo = eval("document.forms[0].sydw_B_"+i);
  if (oo.readOnly) {
    oo.tabIndex = -1;
  }
  else {
    oo.tabIndex = tabIndex++;
  }
  oo.onchange = computesydw;
//  eval("document.forms[0].sydw_B_"+i).tabIndex = tabIndex++;
//  eval("document.forms[0].sydw_B_"+i).onchange = computesydw;
}

function computesydw()
{
 var id = trim(window.event.srcElement.id+"");
 if(!checksydwValue(id)){
    window.event.srcElement.select();
    return false;
 }else{
     computesydwGS(id);
 }
}
function computesydwGS(myid)
{
  var stack = new Stack();
  var strmyid = "" + myid;
  var hc = strmyid;
  for(var i=0;i<sydw_jsgss.length;i++)
  {
      if(sydw_jsgss[i].isInExpress(hc))
      {
          var retBolan = sydw_jsgss[i].compute();
          if(retBolan != null)
          {
              setValue(retBolan.operator, retBolan.value);
              stack.push(retBolan.operator);
          }
      }
  }
  while(stack.length() > 0)
  {
      var nHc = stack.pop();
      computesydwGS(nHc);
  }
}


//�����ҵ��λ������
function checksydwValue(id){
  var hc = getHcByID(id)

  var obj = eval("document.forms[0]."+id);
  var xmmc = eval("document.forms[0].sydwxmmc_"+hc).value;
  var value = trim(obj.value);
  var msg = xmmc+"( "+hc+" ) ";
  if(value == ""){
    return true;
  }
  if(!checkvalue(obj,1))
  {
    return false;
  }

  //����������
  if(isNaN(value)){
     alert(msg+"ӦΪ������");
     return false;
  }
  if(parseFloat(value)<0){
    alert(msg+"����Ϊ����");
    return false;
  }
  if(!checkPoint(value,2)){
           alert(msg+"С��λ�����ܴ���2λ");
           return false;
  }
  if(!checkIntPoint(value,13)){
           alert(msg+"����λ�����ܴ���13λ");
           return false;
  }

//  obj.value = round(value);
  formatCurrency(obj,0);
  return true;
}
</script>


<%
    }
%>

</div>

<html:hidden property="lygfqysl" />

<div id="sbb_lygf_1" style="display:none">

<table width="90%" cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">��Ӫ��ҵ������˰�����</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
                  <table id="table_1" width="100%" border="0" cellpadding="0" class="table-99">
                     <tr>
                       <td align="center" class="2-td1-left">&nbsp;</td>
                       <td align="center" class="2-td1-left">��Ӫ��ҵ����</td>
                       <td align="center" class="2-td1-left">��Ӫ��ҵ˰��</td>
                       <td align="center" class="2-td1-left">�ֻ�����</td>
                       <td align="center" class="2-td1-left">����Ӧ��˰���ö�</td>
                       <td align="center" class="2-td1-left">Ӧ������˰��</td>
                       <td align="center" class="2-td1-left">˰�տ۳���</td>
                       <td align="center" class="2-td1-center">Ӧ������˰��</td>
                     </tr>
                     <declare:lygfsbb formName="qysdsnbForm" scope="session"  qylxdm="1" />
               </table>
               <br>
        <div align="center">
			<img src="<%=static_contextpath%>images/xinzeng1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xinzeng2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xinzeng1.jpg'" alt="����һ��" onclick="FN_addRow(1);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="FN_deleteRows(1);computeSum(1);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="returnToNBSbb('1','83');" style="cursor:hand">
        </div>

     </td>
  </tr>
</table>


</div>

<div id="sbb_lygf_2" style="display:none">
<table width="90%" cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">�ɷ�����ҵ������˰�����</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
                  <table id="table_2"  width="100%" border="0" cellpadding="0" class="table-99">
                     <tr>
                       <td align="center" class="2-td1-left">&nbsp;</td>
                       <td align="center" class="2-td1-left">�ɷ�����ҵ����</td>
                       <td align="center" class="2-td1-left">�ɷ�����ҵ˰��</td>
                       <td align="center" class="2-td1-left">��Ϣ</td>
                       <td align="center" class="2-td1-left">����Ӧ��˰���ö�</td>
                       <td align="center" class="2-td1-left">Ӧ������˰��</td>
                       <td align="center" class="2-td1-left">˰�տ۳���</td>
                       <td align="center" class="2-td1-center">Ӧ������˰��</td>
                     </tr>
                     <declare:lygfsbb formName="qysdsnbForm" scope="session"  qylxdm="2" />
               </table>
               <br>
        <div align="center">
			<img src="<%=static_contextpath%>images/xinzeng1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xinzeng2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xinzeng1.jpg'" alt="����һ��" onclick="FN_addRow(2);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="FN_deleteRows(2);computeSum(2);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="returnToNBSbb('2','84');" style="cursor:hand">
        </div>
     </td>
  </tr>
</table>

</div>

<div id="sbb_lygf_3" style="display:none">
<table width="90%" cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">���������ҵ������˰�����</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
                  <table id="table_3"  width="100%" border="0" cellpadding="0" class="table-99">
                     <tr>
                       <td align="center" class="2-td1-left">&nbsp;</td>
                       <td align="center" class="2-td1-left">������ҵ����</td>
                       <td align="center" class="2-td1-left">���������ҵ˰��</td>
                       <td align="center" class="2-td1-left">����</td>
                       <td align="center" class="2-td1-left">����Ӧ��˰���ö�</td>
                       <td align="center" class="2-td1-left">Ӧ������˰��</td>
                       <td align="center" class="2-td1-left">˰�տ۳���</td>
                       <td align="center" class="2-td1-center">Ӧ������˰��</td>
                     </tr>
                     <declare:lygfsbb formName="qysdsnbForm" scope="session"  qylxdm="3" />
               </table>
               <br>
        <div align="center">
			<img src="<%=static_contextpath%>images/xinzeng1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xinzeng2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xinzeng1.jpg'" alt="����һ��" onclick="FN_addRow(3);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="FN_deleteRows(3);computeSum(3);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="returnToNBSbb('3','85');" style="cursor:hand">
        </div>
     </td>
  </tr>
</table>

</div>

<div id="sbb_lygf_4" style="display:none">
<table width="90%" cellspacing="0" class="table-99">
  <tr>
     <td class="1-td1">Ͷ����ҵ��ҵ������˰�����</td>
  </tr>
  <tr>
     <td valign="top" class="1-td2">
                  <table id="table_4"  width="100%" border="0" cellpadding="0" class="table-99">
                     <tr>
                       <td align="center" class="2-td1-left">&nbsp;</td>
                       <td align="center" class="2-td1-left">��Ͷ����ҵ����</td>
                       <td align="center" class="2-td1-left">Ͷ����ҵ˰��</td>
                       <td align="center" class="2-td1-left">����</td>
                       <td align="center" class="2-td1-left">����Ӧ��˰���ö�</td>
                       <td align="center" class="2-td1-left">Ӧ������˰��</td>
                       <td align="center" class="2-td1-left">˰�տ۳���</td>
                       <td align="center" class="2-td1-center">Ӧ������˰��</td>
                     </tr>
                     <declare:lygfsbb formName="qysdsnbForm" scope="session"  qylxdm="4" />
               </table>
               <br>
        <div align="center">
			<img src="<%=static_contextpath%>images/xinzeng1.jpg" onmouseover="this.src='<%=static_contextpath%>images/xinzeng2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/xinzeng1.jpg'" alt="����һ��" onclick="FN_addRow(4);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/shanchu1.jpg" onmouseover="this.src='<%=static_contextpath%>images/shanchu2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/shanchu1.jpg'" alt="ɾ��" onclick="FN_deleteRows(4);computeSum(4);" style="cursor:hand">
                          &nbsp;&nbsp;&nbsp;&nbsp;
			<img src="<%=static_contextpath%>images/fanhui1.jpg" onmouseover="this.src='<%=static_contextpath%>images/fanhui2.jpg'" onmouseout="this.src='<%=static_contextpath%>images/fanhui1.jpg'" alt="����" onclick="returnToNBSbb('4','86');" style="cursor:hand">
        </div>
     </td>
  </tr>
</table>
</div>




<script language="javascript">
function returnToNBSbb(qylx,hc){

  var vid = "nb_B_"+hc;
  var obj = eval("document.forms[0]."+vid);
  var length = FN_getLength(qylx);
  if(length != null){
      var mcobj = eval("document.forms[0].lygfqymc_"+qylx);
       if(length == -1){
        if(trim(mcobj.value) == ""){
            alert("��ҵ���Ʋ���Ϊ�գ�");
            mcobj.focus();
            return false;
        }
       }else {
                // �ж���
                for (var i=mcobj.length-1; i>=0; i--)
                {
            if(trim(mcobj[i].value) == ""){
                alert("��ҵ���Ʋ���Ϊ�գ�");
                mcobj[i].focus();
                return false;
            }
                }
    }
      obj.value=eval("document.forms[0].lygfybsdse_" + qylx+"_sum").value;
  }else{
      obj.value="";
  }


  computenbGS(vid);

  hideAllSbb();
  title.style.display='';
  sbb_nb.style.display='';
  obj.focus();


}


function FN_addRow(qylx)
{

    var Table_Master = eval("table_" + qylx);


    var length = Table_Master.rows.length;
    var otr = Table_Master.insertRow(length -1 );

    var cell = otr.insertCell();
    cell.className = "2-td2-left";
    var obj = document.createElement("<input type='checkbox' name='del_" + qylx + "'></INPUT>");
    cell.appendChild(obj);
    var obj = document.createElement("<input type='hidden' name='lygfdjzclxdm' value='" + qylx + "'></INPUT>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygfqymc' id='lygfqymc_"+qylx+ "' size='30' maxlength='200'></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygfsl' id='lygfsl_"+qylx+"' size='7' value='0.0' maxlength=7 onchange=\"return computeLygfItem(this,'"+qylx+"','lygfsl');\"></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygflrgx' id='lygflrgx_"+qylx+ "' size='10' maxlength=15  value='0.0' onchange=\"return computeLygfItem(this,'"+qylx+"','lygflrgx');\" ></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygfynssde' id='lygfynssde_"+qylx+ "' value='0.00' size='16'  maxlength=15  readonly class='inputnoborder'></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygfynsdse' id='lygfynsdse_"+qylx+ "'  value='0.00' size='16'   maxlength=15 readonly class='inputnoborder'></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-left";
    obj = document.createElement("<input type='text' name='lygfsskce' id='lygfsskce_"+qylx+ "' value='0.00' size='10'   maxlength=15 readonly class='inputnoborder'></input>");
    cell.appendChild(obj);

    cell = otr.insertCell();
    cell.className = "2-td2-center";
    obj = document.createElement("<input type='text' name='lygfybsdse' id='lygfybsdse_"+qylx+ "' value='0.00' size='16'   maxlength=15 readonly class='inputnoborder'></input>");
    cell.appendChild(obj);

    return false;
}



function FN_deleteRows(index)
{
    var Table_Master = eval("table_" + index);

    var selected = false;

    var length = FN_getLength(index);

    if (length == null)
    {
        return false;
    }

    var del = eval("document.forms[0].del_" + index);

    if (length == -1)
    {
        // ֻ��һ��
        if (del.checked)
        {
            selected = true;
            Table_Master.deleteRow(1);
        }
    }
    else if (length == 1)
    {
        if (del[0].checked)
        {
            selected = true;
            Table_Master.deleteRow(1);
        }
    }
    else
    {
        // �ж���
        for (var i=del.length-1; i>=0; i--)
        {
            if (del[i].checked)
            {
                selected = true;
                Table_Master.deleteRow(i+1);
            }
        }
    }


    if (!selected)
    {
        alert("��ѡ��Ҫɾ���ļ�¼��");
    }

    return false;
}

function FN_getLength(index)
{
    var del = eval("document.forms[0].del_" + index);

    if (del == null)
    {
        return null;
    }

    if (del.length == null)
    {
        // ֻ��һ��
        return -1;
    }
    else
    {
        // ֻ��һ�У�������
        if(del.length == 1)
        {
            return 1;
        }

        return del.length;
    }
}


function getNumber(obj){
    var v = trim(obj.value);
    if(v == ""){
        return 0;
    }else{
        return parseFloat(v);
    }
}

//�����Ӫ�ɷ�˰��
function checkLygfSl(sl){
    var v = trim(sl);
    if(v == ""){
        alert("˰�ʲ���Ϊ�գ�");
        return false;

    }else if(isNaN(v)){
        alert("˰��ӦΪ���֣�");
        return false;
    }else{
        var s = parseFloat(v);
        if( s< 0 || s >= 1){
            alert("˰��Ӧ���ڵ���0��С��1");
            return false;
        }
    }
    if(!checkPoint(v,5)){
       alert("˰��С��λ�����ܴ���5λ");
       return false;
     }
    return true;
}

//�����Ӫ�ɷݹ�Ϣ����
function checkLygfGxhl(lr){
    var v = trim(lr);
    if(v == ""){
        alert("�ֻ����󡢹�Ϣ�����������治��Ϊ�գ�");
        return false;

    }else if(isNaN(v)){
        alert("�ֻ����󡢹�Ϣ������������ӦΪ���֣�");
        return false;
    }else if(parseFloat(v) < 0){
        alert("�ֻ����󡢹�Ϣ�����������治��Ϊ������");
        return false;
    }

  if(!checkPoint(v,4)){
           alert("�ֻ����󡢹�Ϣ������������С��λ�����ܴ���4λ");
           return false;
  }
  if(!checkIntPoint(v,13)){
           alert("�ֻ����󡢹�Ϣ����������������λ�����ܴ���13λ");
           return false;
  }
    return true;
}

//������Ӫ�ɷݵ�����
function computeLygfItem(obj,qylx,xm){
    var Table_Master = eval("table_" + qylx);


    var length = FN_getLength(qylx);

    if (length == null)
    {
        return false;
    }

    var lygfqysl = parseFloat(document.forms[0].lygfqysl.value);

    var xms = eval("document.forms[0]."+xm+"_" + qylx);
    var lygflrgx = eval("document.forms[0].lygflrgx_" + qylx);
    var lygfsl = eval("document.forms[0].lygfsl_" + qylx);
    var lygfynssde = eval("document.forms[0].lygfynssde_" + qylx);
    var lygfynsdse = eval("document.forms[0].lygfynsdse_" + qylx);
    var lygfybsdse = eval("document.forms[0].lygfybsdse_" + qylx);
    var lygfsskce = eval("document.forms[0].lygfsskce_" + qylx);




    var sl,gx,ynssde,ynsdse,sskse,ybsdse;
    var sl_obj,gx_obj,ynssde_obj,ynsdse_obj,sskse_obj,ybsdse_obj;


   if(length == -1){
    sl_obj = lygfsl;
    gx_obj = lygflrgx;
    ynssde_obj = lygfynssde;
    ynsdse_obj = lygfynsdse;
    sskse_obj = lygfsskce;
    ybsdse_obj = lygfybsdse;
   }else {
    var index = 0;
    if (length == 1)
    {
        index = 0;
    }
    else
        {
            // �ж���
            for (var i=xms.length-1; i>=0; i--)
            {
            if(xms[i] == obj){

                index = i;
                    break;
            }
            }
         }

    sl_obj = lygfsl[index];
    gx_obj = lygflrgx[index];
    ynssde_obj = lygfynssde[index];
    ynsdse_obj = lygfynsdse[index];
    sskse_obj = lygfsskce[index];
    ybsdse_obj = lygfybsdse[index];
     }
        //**********************�˴���������ݼ��**************************
    if(!checkLygfSl(sl_obj.value)){
       return false;
    }
    var temp = parseFloat(sl_obj.value) * 100000;
        temp = Math.round(temp)/100000;
    sl_obj.value = temp;
//   	setValue(sl_obj,sl_obj.value);


    if(!checkLygfGxhl(gx_obj.value)){
        return false;
    }
    gx_obj.value = round(gx_obj.value);



    sl = getNumber(sl_obj);
    gx = getNumber(gx_obj);
    if(sl == 0){
//        ynssde = 0;
//        ynsdse = 0;
//        sskse = 0;
//        ybsdse  = 0;
    }else{
        //����Ӧ��˰���ö� =�ֻ����󡢹�Ϣ������������ / ��1 - ��Ӧ��ҵ˰�ʣ�
        ynssde = gx/(1-sl);
        //Ӧ������˰�� = �����Ӧ��˰���ö� �� ����˰�˵�˰��
        ynsdse = ynssde * lygfqysl;
        //˰�տ۳��� = �����Ӧ��˰���ö� �� ��Ӧ��ҵ��˰��
        sskse = ynssde * sl;
        //Ӧ������˰�� = Ӧ������˰�� - ˰�տ۳������С��0����Ӧ������˰��Ϊ0
        ybsdse = ynsdse - sskse;
//        if(ybsdse < 0) ybsdse  = 0;
    }

    //��ֵ
    ynssde_obj.value = round(ynssde);
    ynsdse_obj.value = round(ynsdse);
    sskse_obj.value = round(sskse);
    ybsdse_obj.value = round(ybsdse);
    formatCurrency(ynssde_obj,0);
    formatCurrency(ynsdse_obj,0);
    formatCurrency(sskse_obj,0);
    formatCurrency(ybsdse_obj,0);

    computeSum(qylx);
}

//
function computeSum(qylx ){
    var Table_Master = eval("table_" + qylx);


    var length = Table_Master.rows.length-2;;

    var lygflrgx_sum = 0;
    var lygfynssde_sum = 0;
    var lygfynsdse_sum = 0;
    var lygfybsdse_sum = 0;
    var lygfsskce_sum = 0;
    if (length != 0)
    {
        var lygflrgx = eval("document.forms[0].lygflrgx_" + qylx);
        var lygfynssde = eval("document.forms[0].lygfynssde_" + qylx);
        var lygfynsdse = eval("document.forms[0].lygfynsdse_" + qylx);
        var lygfsskce = eval("document.forms[0].lygfsskce_" + qylx);
        var lygfybsdse = eval("document.forms[0].lygfybsdse_" + qylx);

       if(lygflrgx.length == null){

        lygflrgx_sum = parseFloat(lygflrgx.value);
        lygfynssde_sum = parseFloat(lygfynssde.value);
        lygfynsdse_sum = parseFloat(lygfynsdse.value);
        lygfsskce_sum = parseFloat(lygfsskce.value);
            lygfybsdse_sum = parseFloat(lygfybsdse.value);
       }else{
        var index = 0;
            if(lygflrgx.length == 1)
        {
            lygflrgx_sum = parseFloat(lygflrgx[0].value);
            lygfynssde_sum = parseFloat(lygfynssde[0].value);
            lygfynsdse_sum = parseFloat(lygfynsdse[0].value);
            lygfsskce_sum = parseFloat(lygfsskce[0].value);
                lygfybsdse_sum = parseFloat(lygfybsdse[0].value);
        }
        else
            {
                // �ж���
               for (var i=lygflrgx.length-1; i>=0; i--)
               {
            lygflrgx_sum += parseFloat(lygflrgx[i].value);
            lygfynssde_sum += parseFloat(lygfynssde[i].value);
            lygfynsdse_sum += parseFloat(lygfynsdse[i].value);
            lygfsskce_sum += parseFloat(lygfsskce[i].value);
                lygfybsdse_sum += parseFloat(lygfybsdse[i].value);
               }
            }
        }

    }
    eval("document.forms[0].lygflrgx_" + qylx+"_sum").value=round(lygflrgx_sum);
    eval("document.forms[0].lygfynssde_" + qylx+"_sum").value=round(lygfynssde_sum);
    eval("document.forms[0].lygfynsdse_" + qylx+"_sum").value=round(lygfynsdse_sum);
    eval("document.forms[0].lygfsskce_" + qylx+"_sum").value=round(lygfsskce_sum);
    eval("document.forms[0].lygfybsdse_" + qylx+"_sum").value=round(lygfybsdse_sum);
    formatCurrency(eval("document.forms[0].lygflrgx_" + qylx+"_sum"),0);
    formatCurrency(eval("document.forms[0].lygfynssde_" + qylx+"_sum"),0);
    formatCurrency(eval("document.forms[0].lygfynsdse_" + qylx+"_sum"),0);
    formatCurrency(eval("document.forms[0].lygfsskce_" + qylx+"_sum"),0);
    formatCurrency(eval("document.forms[0].lygfybsdse_" + qylx+"_sum"),0);
}

</script>

<script language="javascript">
function hideAllSbb(){
    sbb_nb.style.display='none';
    sbb_cwzb.style.display='none';
    sbb_sydw.style.display='none';
    sbb_lygf_1.style.display='none';
    sbb_lygf_2.style.display='none';
    sbb_lygf_3.style.display='none';
    sbb_lygf_4.style.display='none';
<%
     if(sydwshttLX > 0){
%>
    sydwsj.style.display='none';
<%}%>
}
</script>
</logic:notEqual>
</html:form>

<script language="javascript">
function fnSave(){
    if (confirm(confirmSave))
    {
        document.forms[0].actionType.value="Save";
        document.forms[0].submit();
    }
}
function fnRemove(){
    if (confirm(qysdsconfirmDelete))
    {
        operForm.actionType.value="Remove";
        operForm.submit();
    }
}
function fnReturn(){
    if (confirm(confirmReturn))
    {
        operForm.actionType.value="Return";
        operForm.submit();
    }
}
</script>

<html:form action="/qysdsnb.do" method="post" name="operForm" type="com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web.QysdsnbForm">
  <input type="hidden" name="actionType">
</html:form>
    </td>
  </tr>

  <tr>
    <td valign="bottom" align="center">
<%@ include file="../include/nbbottom.jsp" %>
    </td>
  </tr>
  
  <tr>
    <td valign="bottom" align="center">
<%@ include file="../include/bottom.jsp" %>
    </td>
  </tr>
</table>
</body>
</html>

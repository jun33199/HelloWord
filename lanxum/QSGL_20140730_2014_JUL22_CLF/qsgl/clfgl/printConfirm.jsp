<%@ page contentType="text/html; charset=gb2312" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<HTML>
<HEAD>
<meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
<TITLE>��ӡ��ʾ</TITLE>
</HEAD>

<BODY bgcolor="#F3F5F8" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<div align="center" height="100px">
<p align="center">
<br>
<font color="#2C556D"><b>��ӡ�Ƿ�ɹ���</b></font>
</p>
<br>
<input type="button" accesskey="s" tabIndex="-1" type="button" name="success" value="��ӡ�ɹ�(S)" onclick="printSuccess()" title="success">
&nbsp;&nbsp;
<input type="button" accesskey="f" tabIndex="-1" name="failure" value="��ӡʧ��(F)" onclick="printFailure()" title="failure">


<script>

function printFailure(){
	window.returnValue = 'no';
	window.close();
}


function printSuccess(){
	window.returnValue = 'yes';
	window.close();
}


</script>

</div>
</BODY>
</HTML>

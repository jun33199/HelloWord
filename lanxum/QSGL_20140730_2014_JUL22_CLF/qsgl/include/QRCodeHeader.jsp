<%System.out.println("Loading QRCode ActiveX Install...");%>


<%
  String hstatic_file_QR = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<OBJECT id="QRCODE" codebase="<%=hstatic_file_QR%>download/QRCODE.CAB#version=1,0,0,0" classid="CLSID:E6A478D7-E283-4E13-BA2E-56FA1B6FB665" width="1" height="1"></OBJECT>
<script language="javascript">
//����QR����ļ���
QR_TEMP_DIR="c:\\QR_TEMP";
//��鱾���Ƿ������ʱ�ļ��У��������򴴽�
var fso;
fso = new ActiveXObject("Scripting.FileSystemObject");	
//�ж��Ƿ��б���QR����ļ���
if(fso.FolderExists(QR_TEMP_DIR))
{
			
}else{
	fso.CreateFolder (QR_TEMP_DIR);
}


</script>
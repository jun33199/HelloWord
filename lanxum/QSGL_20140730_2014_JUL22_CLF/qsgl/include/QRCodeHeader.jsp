<%System.out.println("Loading QRCode ActiveX Install...");%>


<%
  String hstatic_file_QR = com.ttsoft.common.util.ResourceLocator.getStaticFilePath(request);
%>

<OBJECT id="QRCODE" codebase="<%=hstatic_file_QR%>download/QRCODE.CAB#version=1,0,0,0" classid="CLSID:E6A478D7-E283-4E13-BA2E-56FA1B6FB665" width="1" height="1"></OBJECT>
<script language="javascript">
//保存QR码的文件夹
QR_TEMP_DIR="c:\\QR_TEMP";
//检查本地是否存在临时文件夹，不存在则创建
var fso;
fso = new ActiveXObject("Scripting.FileSystemObject");	
//判断是否有保存QR码的文件夹
if(fso.FolderExists(QR_TEMP_DIR))
{
			
}else{
	fso.CreateFolder (QR_TEMP_DIR);
}


</script>
/*
**************************************************
Title:			BJCA SecXGlobal Web Application Interface
Version:		2.1
Author:			
Date:			2005/7/08
Last Modify:	2005/7/18
Copyright:		BeiJing Certification Authentication
				http://services.bjca.org.cn/dishui/
**************************************************
*/
/*
**************************************************
Define Global Information
*/
/////const //////////////////////////
var CERT_SRC_BASE64	=					1;		//֤������Base64�ַ���
var CERT_SRC_UNIQUEID =					2;		//֤������Ψһ��ʾ
var CERT_SRC_FILE =						3;		//֤������der�ļ�
var CERT_SRC_CONTAINER_UCA = 			4;		//֤������UCA����֤������
var CERT_SRC_CONTAINER_SIGN	=			5;		//֤������������ǩ��֤��
var CERT_SRC_CONTAINER_ENC =			6;		//֤�����������¼���֤��
var CERT_SRC_CONTAINER_BOTH	=			7;		//֤������������ǩ������֤��
var CERT_SRC_PKCS12	=					8;		//֤������PKCS12�ļ�

var CERT_DST_BASE64	=					1;		//����֤��ΪBase64�ַ���
var CERT_DST_DERFILE =					2;		//����֤��Ϊder�ļ�
var CERT_DST_P12 =  				    3;		//����֤��ΪPKCS12�ļ�

var CERT_XML_SUBJECT =					1;		//��XML�����ļ�ȡ�û���
var CERT_XML_UNIQUEID =					2;		//��XML�����ļ�ȡ�û�Ψһ��ʶ
var CERT_XML_DEPT =						3;		//��XML�����ļ�ȡ�û������߲���
var CERT_XML_ISSUE =					4;		//��XML�����ļ�ȡ�û�֤��䷢��
var CERT_XML_STATE =					5;		//��XML�����ļ�ȡ�û�֤��ʹ��״̬
var CERT_XML_TRADETYPE =				6;		//��XML�����ļ�ȡ�û�֤��Ӧ������
var CERT_XML_PASSWORD =					7;		//��XML�����ļ�ȡ�û�֤��˽Կ��������
var CERT_XML_DEVICETYPE =				8;		//��XML�����ļ�ȡ�û�֤���������
var CERT_XML_CATYPE	 =					9;		//��XML�����ļ�ȡ�û�֤��CA����
var CERT_XML_KEYTYPE =					10;		//��XML�����ļ�ȡ�û�֤����Կ����
var CERT_XML_SIGNSN	=					11;		//��XML�����ļ�ȡ�û�ǩ��֤�����к�
var CERT_XML_EXCHSN	=					12;		//��XML�����ļ�ȡ�û�����֤�����к�
var CERT_XML_DEVICENAME =				13;		//��XML�����ļ�ȡ�û�֤���������
var CERT_XML_DEVICEPROVIDER =			14;		//��XML�����ļ�ȡ�û�֤������ṩ��
var CERT_XML_DEVICEAFFIX =				15;		//��XML�����ļ�ȡ�û�֤����ʸ��ӿ�
var CERT_XML_SIGNPATH =					16;		//��XML�����ļ�ȡ�û�ǩ��֤��·��
var CERT_XML_EXCHPATH =					17;		//��XML�����ļ�ȡ�û�����֤��·��
var CERT_XML_SIGNPFXPATH =				18;		//��XML�����ļ�ȡ�û�ǩ��P12֤��·��
var CERT_XML_EXCHPFXPATH =				19;		//��XML�����ļ�ȡ�û�����P12֤��·��
var CERT_XML_CHAINPATH =				20;		//��XML�����ļ�ȡ�û�֤����·��
var CERT_XML_CRLPATH =					21;		//��XML�����ļ�ȡ�û�֤�������б�·��
var CERT_XML_UNIQUEIDOID =				22;		//��XML�����ļ�ȡ�û�֤��UniqueID��OID
var CERT_XML_VERIFYTYPE	=				23;		//��XML�����ļ�ȡ�û�֤����֤����
var CERT_XML_CACOUNTS =					24;		//��XML�����ļ�ȡ�û�֤���֤�����
var CERT_XML_CANUMTYPE =				25;		//��XML�����ļ�ȡ�û�֤���֤������

var CRYPT_CFGTYPE_UNSET =				0;		//�û�Ӧ������δ����
var CRYPT_CFGTYPE_CSP =					1;		//�û�Ӧ������CSP
var CRYPT_CFGTYPE_P11 =					2;		//�û�Ӧ������P11
var CRYPT_CFGTYPE_P12 =					3;		//�û�Ӧ���������㷨

var ENVELOP_ENC =						1;		//����P7�����ŷ�
var ENVELOP_DEC =						0;		//����P7�����ŷ�
var CRYPT_ALG_HASH =					1;		//Hash��־λ
var CRYPT_ALG_SYMM =					2;		//�Գ��㷨��־λ
var CRYPT_ALG_MODE =					3;		//�Գ��㷨ģʽ

////CUSTOM CERT OID////////////////////////////////
var CERT_OID_VERSION =					1;		//֤��汾��
var CERT_OID_SN =						2;		//֤�����к�
var CERT_OID_SIGNALG =					3;		//֤��ǩ���㷨
var CERT_OID_ISSUERNAME =				4;		//֤��䷢��
var CERT_OID_NOTBEFORE =				5;		//֤����Ч����
var CERT_OID_NOTAFTER =					6;		//֤���������
var CERT_OID_PUBLICKEY =				7;		//֤�鹫Կ
var CERT_OID_UNIQUEID =					8;		//֤��Ψһ��ʶ

//BJCA SecX support these item types of form /////
function CFormR()
{
	this.ReITag = new Array();
	this.ReIType = new Array();
	this.ReCIType = new Array();

	this.FT1 = "application/x-www-form-urlencoded";
	this.FT2 = "multipart/form-data";

	//SecX Support These Form Tags & Types ///////
	this.ReITag[0] = "INPUT";
	this.ReITag[1] = "TEXTAREA";
	this.ReITag[2] = "SELECT";

	this.ReIType[0] = "text";
	this.ReIType[1] = "password";
	this.ReIType[2] = "hidden";
	this.ReIType[3] = "textarea";
	this.ReIType[4] = "file";
	this.ReIType[5] = "textarea";
	this.ReIType[6] = "select-one";
	this.ReIType[7] = "checkbox";
	this.ReIType[8] = "radio";
	this.ReIType[9] = "select-multiple";

	this.ReCIType[0] = "checkbox";
	this.ReCIType[1] = "radio";
	this.ReCIType[2] = "select-multiple";
}
//BJCA SecX NamingRule Object ////////////////////
function CNamingRule()
{
	//Need SecX Naming Rule , Set Flag = 1 ///////
	this.Flag = 0;
	this.RuleString = "SecX_";
}

//BJCA SecX EncryptRule Object ///////////////////
function CEncRule()
{
	//Need Encrypt Data , Set Flag = 1 ///////////
	this.Flag = 1;
	//this.ReplaceString = "SecX_NULL";

	this.XMLHeader = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";
	this.XMLDoc = new ActiveXObject("Microsoft.XMLDOM");
	this.XMLDoc.async = false;
	this.XMLDoc.loadXML("<SecXMSG></SecXMSG>");

	//var objPI = this.XMLDoc.createProcessingInstruction("xml", "version=\"1.0\" encoding=\"gb2312\" ");
	//this.XMLDoc.insertBefore(objPI, this.XMLDoc.childNodes(0));

}

//BJCA SecX Server Information Object ///////////
function CSvrInfo()
{
	//Use to Get Info From Server ////////////////
	this.SvrCert = "";
	this.SvrSignData = "";
	this.SvrOriData = "";
	this.Container = "";

}

//BJCA SecX Addition Form Item Object ////////////
function CAdditionHTML()
{
	//Reserved These Content /////////////////////
	this.ItemName = new Array();
	//this.ItemName[0] = "org.apache.struts.taglib.html.TOKEN";
	this.ItemName[0] = "SecX_SignData";
	this.ItemName[1] = "SecX_UserCert";
	this.ItemName[2] = "SecX_ActionSign";
	this.ItemName[3] = "SecX_APPType";
	//this.ItemName[4] = "SecX_EncData";
	this.ItemName[4] = "SecX_OrginData";

	this.ItemNum = 5;

	//Add a SecX Form ////////////////////////////
	this.HTMLFormString = "<Form ID=\"SecX_Form\" Name=\"SecX_Form\" action=\"\" Method=\"POST\" >";
	//this.HTMLFormString += "<INPUT type=\"hidden\" ID=\"org.apache.struts.taglib.html.TOKEN\" name=\"org.apache.struts.taglib.html.TOKEN\" value=\"\">";	
	this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_SignData\" name=\"SecX_SignData\" value=\"\">";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_UserCert\" name=\"SecX_UserCert\" value=\"\">";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_ActionType\" name=\"SecX_ActionType\" value=\"\">";
	this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_APPType\" name=\"SecX_APPType\" value=\"\">";
	//����ǩ���ּ��ܽ����в�ע��
	//this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_EncData\" name=\"SecX_EncData\" value=\"\">";
	//����ǩ���ּ��ܽ�����ע��
	this.HTMLFormString += "<input type=\"hidden\" ID=\"SecX_OrginData\" name=\"SecX_OrginData\" value=\"\">";
	this.HTMLFormString += "</Form>";

	this.HTMLOtherString = "";

}

//BJCA SecX Addition Login Item Object ////////////
function CAddOriginalHTML()
{
	//Reserved These Content /////////////////////
	this.ItemName = new Array();
	this.ItemName[0] = "SecX_SignData";
	this.ItemName[1] = "SecX_NameList";
	this.ItemName[2] = "SecX_UserCert";
	this.ItemName[3] = "SecX_ContainerName";
	this.ItemName[4] = "SecX_APPType";

	this.ItemNum = 5;

	//Add These Item to Original Form ////////////
	this.HTMLItemString = "<input type=\"hidden\" ID=\"SecX_SignData\" name=\"SecX_SignData\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_NameList\" name=\"SecX_NameList\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_UserCert\" name=\"SecX_UserCert\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_ContainerName\" name=\"SecX_ContainerName\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_APPType\" name=\"SecX_APPType\" value=\"\">";

	this.HTMLOtherString = "";

}

//BJCA SecX Addition Original Item Object ////////////
function CAdditionSecurityHTML()
{
	//Add These Item to Original Form ////////////
	this.HTMLItemString = "<input type=\"hidden\" ID=\"SecX_SignData\" name=\"SecX_SignData\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_UserCert\" name=\"SecX_UserCert\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_OrginData\" name=\"SecX_OrginData\" value=\"\">";
	this.HTMLItemString += "<input type=\"hidden\" ID=\"SecX_ActionType\" name=\"SecX_ActionType\" value=\"\">";

	this.HTMLOtherString = "";

}

//BJCA SecX Return Code Object ////////////////////
function CRetCode()
{

	//General Error Code /////////////////////////
	this.Success = 0; //�����ɹ�
	this.Fail = -1; //����ʧ��
	this.ErrForm = -2; //HTML������
	this.ErrPara = -3; //�����������

	//Cert Error Code ////////////////////////////
	this.ErrCert = -11; //֤����Ч
	this.ErrCertValid = -12; //֤�����
	this.ErrCertPath = -13; //֤��·������
	this.ErrCertChain = -14; //֤��������
	this.ErrCertChainPath = -15; //֤����·������
	this.ErrCertKeyPWD = -16; //˽Կ�����������
	this.ErrCertKeyPath = -17; //˽Կ�豸����
	this.ErrCertParse = -18; //����֤�����
	this.ErrCertChangePWD = -19; //�޸�˽Կ�����������

	//Cert App Error Code ////////////////////////
	this.ErrEnc = -101; //���ܴ���
	this.ErrDenc = -102; //���ܴ���
	this.ErrSgn = -103; //ǩ������
	this.ErrVeriSgn = -104 //��֤ǩ������
	this.ErrModifyPWD = -105; //�޸�˽Կ�����������
	this.ErrSvrCert = -106; //��������֤�����
	this.ErrEncFile = -107; //�����ļ�����
	this.ErrDencFile = -108; //�����ļ�����

	//Other Error Code ///////////////////////////
	this.ErrUnknown = -999; //�쳣����

	//ActiveX Control Error Code /////////////////
	this.ErrOcxRet = -1000; //���ؿؼ��������
	this.ErrOcxPalace = ""; //���ؿؼ�������

}
/*
End Define
**************************************************
*/

/////����ӿ�ת��Ϊ�ű��ӿ�////////////////////////
//Create Global Object ///////////////////////////
var g_objF = new CFormR();
var g_objNR = new CNamingRule();
var g_objRet = new CRetCode();
var g_objSI = new CSvrInfo();
var g_objER = new CEncRule();
var g_objAH = new CAdditionHTML();
var g_objAOH = new CAddOriginalHTML();
//////////////////////////////////////////////////

var nCaCount;
var nVerifyType;
var globalAppType;


//Create ActiveX Object //////////////////////////
	
document.writeln("<OBJECT classid=\"clsid:1BFD2B7F-AAED-4319-8776-C5A0F2698249\" height=1 id=BJCASecClt style=\"HEIGHT: 1px; LEFT: 10px; TOP: 28px; WIDTH: 1px\" width=1 VIEWASTEXT>");
document.writeln("	<PARAM NAME=\"_Version\" VALUE=\"65536\">");
document.writeln("	<PARAM NAME=\"_ExtentX\" VALUE=\"370\">");
document.writeln("	<PARAM NAME=\"_ExtentY\" VALUE=\"370\">");
document.writeln("	<PARAM NAME=\"_StockProps\" VALUE=\"0\">");
document.writeln("	<PARAM NAME=\"_StockProps\" VALUE=\"0\">");
document.writeln("</OBJECT>");

/////define object  /////////////////////////////////
var oCert;
var oCrypto;
var oDevice;
var oUtil;


//////////////////////////////////////////////////


//ȡ��֤��Ӧ������
gGetAppType();

/*
**************************************************
Begin Application Development Interface
*/



/*
������
	ȡ��UniqueID�б�
������
	strListID�������˵�������ID�����ID����ID�������б�ID��
���أ�
	��
*/
function gGetCertList(strListID) {
	var strTemp;
	var strOption;
	var len;
	var strName;
	var strUniqueID;
	var objListID = eval(strListID);
	try{
		if (globalAppType==0)
		{
			strTemp = BJCASecClt.GetCertList();
		}else
		{
			strTemp = getUserList();
		}
	}catch(e)
	{

		return;
	}
	
	while (1) {
		i=strTemp.indexOf("&&&");
		if (i <= 0) {
			break;
		}
		strOption = strTemp.substring(0,i);
		strName = strOption.substring(0, strOption.indexOf("||"));
		strUniqueID = strOption.substring(strOption.indexOf("||") + 2, strOption.length);
		var objItem = new Option(strName,strUniqueID)
		objListID.add(objItem);
		len = strTemp.length;
        strTemp = strTemp.substring(i+3,len);
	}
	var objListID = null;
}

/*
Function:	gGetAppType
Parameter:	
*/
function gGetAppType()
{	

	//Create ActiveX Object //////////////////////////
	/*
	document.writeln("<OBJECT classid=\"clsid:02BE3F91-A9E1-4D12-A65B-1E0D500292A7\" height=1 codebase=\"../BJCASecCOM.cab\" id=BJCA >");
	document.writeln("	<PARAM NAME=\"_Version\" VALUE=\"65536\">");
	document.writeln("	<PARAM NAME=\"_ExtentX\" VALUE=\"26\">");
	document.writeln("	<PARAM NAME=\"_ExtentY\" VALUE=\"26\">");
	document.writeln("	<PARAM NAME=\"_StockProps\" VALUE=\"0\">");
	document.writeln("	<PARAM NAME=\"_StockProps\" VALUE=\"0\">");
	document.writeln("</OBJECT>");
	*/
	
	try{
			oCert = new ActiveXObject("BJCASecCOM.Certificate");
			oCrypto = new ActiveXObject("BJCASecCOM.Crypto");
			oDevice = new ActiveXObject("BJCASecCOM.DeviceMgr");
			oUtil = new ActiveXObject("BJCASecCOM.Util");
			//nCaCount = getUserInfoByContainer("cacount",CERT_XML_CACOUNTS);
			nVerifyType = getUserInfoByContainer("nVerifyType",CERT_XML_VERIFYTYPE);
			//alert(nVerifyType);
	}catch(e)
	{
			globalAppType = 0;
			return;
	}

	if (nVerifyType > 0)
	{	
		globalAppType = 1;
	}else
	{			   
		globalAppType = 0;
	}
	//return globalAppType;
}

/*
Function:	gGetGlobalAppType
Parameter:	
*/
function gGetGlobalAppType()
{	
	return globalAppType;
}
/*
Function:	gLogin
Parameter:	strFormName	����ID����
			strUniqueID	֤���UniqueID
			strPassword	˽Կ��������
*/
function gLogin(strFormName, strUniqueID, strPassword)
{
	//Encrypt Data & Sign Data ///////////////
	if(globalAppType==1)
	{				  
		return Login(strFormName,strUniqueID,strPassword);
	}else
	{			   
		return gFuncLogin(strFormName, strUniqueID, strPassword);
	}
	return true;
}
/*
Function:	gLogin_P7
Parameter:	strFormName	����ID����
			strUniqueID	֤���UniqueID
			strPassword	˽Կ��������
*/
function gLogin_P7(strFormName, strUniqueID, strPassword)
{
	//Encrypt Data & Sign Data ///////////////
	if(globalAppType==1)
	{				  
		return Login_P7(strFormName,strUniqueID,strPassword);
	}else
	{			   
		return gFuncLogin(strFormName, strUniqueID, strPassword);
	}
	return true;
}

/*
Function:	gFuncModifyPWD
Parameter:	strID		��ʶ
			strKeyPWD		ԭʼ����
			strNewPWD		�¿���
*/
function gModifyPWD(strID, strKeyPWD, strNewPWD)
{
	if(globalAppType==1)
	{
		//�°�֤��Ӧ�ÿ����޸�
		return changeUserPassword(strID, strKeyPWD, strNewPWD);
	}
	else if(globalAppType==0)
	{
		//�ɰ�֤��Ӧ�ÿ����޸�
		return gFuncModifyPWD(strID, strKeyPWD, strNewPWD);
		/*if(ret == 0)
		{
		    alert("֤������޸ĳɹ���\n");
		}else
		{
		    alert("֤������޸�ʧ�ܣ�\n");
		} */
	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}
 /*
Function:	gGetCert  
Parameter:	strUniqueID		���������½ӿڣ�����Ψһ��ʶ���ɽӿڣ�
*/
function gGetCert()
{
	if(globalAppType==1)
	{
		//�°��֤��Ӧ��
		var sCert = getUserCert(g_objSI.Container);
		if(sCert.length<100)
		{
			return -1;
		}
		return sCert;
	}
	else if(globalAppType==0)
	{
		//�ɰ��֤��Ӧ��
		var sCert = gFuncGetDetailNoInit(g_objSI.Container,0);
		return sCert;
	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}

 /*
Function:	gGetCertDetail  
Parameter:	strUniqueID		���������½ӿڣ�����Ψһ��ʶ���ɽӿڣ�
			ItemNo		    ϸĿ����
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function gGetCertDetail(strUniqueID, ItemNo)
{
	if(globalAppType==1)
	{
		//�°�֤��Ӧ�û�ȡϸĿ
		var sCert = getUserCert(strUniqueID);
		switch(ItemNo)
		{
			case 0:
				return sCert;
				break;
			case 8:
				return getCertBasicInfo(sCert,4);
				break;
			case 11:
				return getCertBasicInfo(sCert,5);
				break;
			case 12:
				return getCertBasicInfo(sCert,6);
				break;
			case 13:
				return getCertBasicInfo(sCert,42);
				break;
			case 14:
				return getCertBasicInfo(sCert,45);
				break;
			case 15:
				return getCertBasicInfo(sCert,46);
				break;
			case 16:
				return getCertBasicInfo(sCert,44);
				break;
			case 17:
				return getCertBasicInfo(sCert,41);
				break;
			case 18:
				return getCertBasicInfo(sCert,43);
				break;
			case 20:
				return getCertBasicInfo(sCert,7);
				break;
			case 22:
				return getExtCertInfoByOID("2.16.840.1.113732.2");
				break;
			case 23:
				return checkValidaty();
				break;
			default:
				return getCertBasicInfo(sCert,ItemNo);
				break;
		}
	}
	else if(globalAppType==0)
	{
		//�ɰ�֤��Ӧ�û�ȡϸĿ
		return gFuncGetDetailNoInit(strUniqueID, ItemNo);
	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}
 /*
Function:	gGetCertDetailExt ����Ҫ��װע����֤����ϸ��Ϣ
Parameter:	readType		��֤�����ͣ��ɽӿڣ� 1������ 2��Key
			ItemNo		    ϸĿ����
			strContainerName ���������½ӿڣ�
			keyType         key����  
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function gGetCertDetailExt(readType,ItemNo,strContainerName,keyType)
{
	if(globalAppType==1)
	{
		//�°�֤��Ӧ�û�ȡϸĿ
		if(keyType!=null)
		{
			importCert(strContainerName, CERT_SRC_CONTAINER_ENC, keyType);
		}
		else
		{
			importCert(strContainerName, CERT_SRC_CONTAINER_ENC, "M&W eKey XCSP");

		}
		switch(ItemNo)
		{
			case 0:
				var sCert = exportCert(CERT_DST_BASE64);
				return sCert;
				break;
			case 8:
				return getBasicCertInfoByOID(4);
				break;
			case 11:
				return getBasicCertInfoByOID(5);
				break;
			case 12:
				return getBasicCertInfoByOID(6);
				break;
			case 13:
				return getBasicCertInfoByOID(42);
				break;
			case 14:
				return getBasicCertInfoByOID(45);
				break;
			case 15:
				return getBasicCertInfoByOID(46);
				break;
			case 16:
				return getBasicCertInfoByOID(44);
				break;
			case 17:
				return getBasicCertInfoByOID(41);
				break;
			case 18:
				return getBasicCertInfoByOID(43);
				break;
			case 20:
				return getBasicCertInfoByOID(7);
				break;
			case 22:
				return getExtCertInfoByOID("2.16.840.1.113732.2");
				break;
			case 23:
				return checkValidaty();
				break;
			default:
				return getBasicCertInfoByOID(ItemNo);
				break;
		}

	}
	else if(globalAppType==0)
	{
		//�ɰ�֤��Ӧ�û�ȡϸĿ
	    if(readType == "1") 
		{
			var ret = gFuncGetCertDetailInfo("a:/UserCert.der", ItemNo, 1);
			return ret;
		}
		else if(readType == "2")
		{
			var ret = null;
			if(keyType!=null)
			{
				ret = gFuncGetCertDetailInfo_Key(keyType, ItemNo);
			}
			else
			{
				ret = gFuncGetCertDetailInfo_Key("BJ0D01", ItemNo);

			}
			return ret;
		}
		else
		{
		}

	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}
 /*
Function:	gGetCertDetail_Key����Ҫ��װע���Key�л��֤����ϸ��Ϣ
Parameter:	ItemNo		    ϸĿ����
			keyType         key����
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function gGetCertDetail_Key(ItemNo, keyType)
{
	if(globalAppType==1)
	{
		//�°�֤��Ӧ�û�ȡϸĿ
		var strTemp;
		var strContainerName;
		var strExpireTime;
		var i;
		var len;
		var sCert;
		if(keyType==null || keyType=="")
		{
			keyType = "M&W eKey XCSP";
		}
		strTemp = enumUserCertificates(keyType);
		while (1) {
			i=strTemp.indexOf("&&&");
			if (i <= 0) {
				break;
			}
			var strContainerNameTmp = strTemp.substring(0,i);
			importCert(strContainerNameTmp, CERT_SRC_CONTAINER_ENC, keyType);
			sCert = exportCert(CERT_DST_BASE64);
			var strExpireTimeTmp = getCertBasicInfo(sCert,6);
			if(strExpireTime == null)
			{
				strExpireTime = strExpireTimeTmp;
				strContainerName = strContainerNameTmp;
			}
			else if(strExpireTime != null && strExpireTime < strExpireTimeTmp)
			{
				strExpireTime = strExpireTimeTmp;
				strContainerName = strContainerNameTmp;
			}
			else
			{
			}

			len = strTemp.length;
			strTemp = strTemp.substring(i+3,len);
		}
		importCert(strContainerName, CERT_SRC_CONTAINER_ENC, keyType);
		switch(ItemNo)
		{
			case 0:
				sCert = exportCert(CERT_DST_BASE64);
				return sCert;
				break;
			case 8:
				return getBasicCertInfoByOID(4);
				break;
			case 11:
				return getBasicCertInfoByOID(5);
				break;
			case 12:
				return getBasicCertInfoByOID(6);
				break;
			case 13:
				return getBasicCertInfoByOID(42);
				break;
			case 14:
				return getBasicCertInfoByOID(45);
				break;
			case 15:
				return getBasicCertInfoByOID(46);
				break;
			case 16:
				return getBasicCertInfoByOID(44);
				break;
			case 17:
				return getBasicCertInfoByOID(41);
				break;
			case 18:
				return getBasicCertInfoByOID(43);
				break;
			case 20:
				return getBasicCertInfoByOID(7);
				break;
			case 22:
				return getExtCertInfoByOID("2.16.840.1.113732.2");
				break;
			case 23:
				return checkValidaty();
				break;
			default:
				return getBasicCertInfoByOID(ItemNo);
				break;
		}

	}
	else if(globalAppType==0)
	{
		//�ɰ�֤��Ӧ�û�ȡϸĿ
		var ret = null;
		if(keyType!=null)
		{
			ret = gFuncGetCertDetailInfo_Key(keyType, ItemNo);
		}
		else
		{
			ret = gFuncGetCertDetailInfo_Key("BJ0D01", ItemNo);

		}
		return ret;

	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}

//Encrypt Data ///////////////////////////////////
function gEncrypt(sInData)
{
	if (sInData == "") {
		alert("ԭ���ݲ���Ϊ�գ����������룡");
		return -1;
	}
	
	if(globalAppType==1)
	{
		//�°��֤��Ӧ�ü���
		var sEnvelop;
		sEnvelop = envelopedData(sInData, ENVELOP_ENC, g_objSI.SvrCert);
		if(sEnvelop.length < 100)
		{
			return -1;
		}
		return sEnvelop;
	}
	else if(globalAppType==0)
	{
		//�ɰ��֤��Ӧ�ü���
		var sEnvelop;
		sEnvelop = gFuncEncrypt(sInData);
		if(sEnvelop.length < 100)
		{
			return -1;
		}
		return sEnvelop;
	}else
	{
		alert("δָ��֤��Ӧ�ð汾");
		return -1;
	}
}

//Dencrypt Data //////////////////////////////////
function gDencrypt(sInData)
{				  
	if(globalAppType==1)
	{
		//�°��֤��Ӧ�ý���
		return envelopedDecodeData(sInData, g_objSI.Container);
	}
	else if(globalAppType==0)
	{
		//�ɰ��֤��Ӧ�ý���
		return gFuncDencrypt(sInData);
	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}

//Sign Data //////////////////////////////////////
function gSign(sInData)
{
	if (sInData == "")
	{
		alert("ǩ�����ݲ���Ϊ�գ����������룡");
		//return g_objRet.ErrPara;
		return -1;
	}

	if(globalAppType==1)
	{
		//�°��֤��Ӧ��ǩ��
		var sSignedData;
		sSignedData = signedData(sInData, g_objSI.Container);
		if(sSignedData.length < 100)
		{
			return -1;
		}
		return sSignedData;
	}
	else if(globalAppType==0)
	{
		//�ɰ��֤��Ӧ��ǩ��
		var sSignedData;
		sSignedData = gFuncSign(sInData);
		if(sSignedData.length < 100)
		{
			return -1;
		}
		return sSignedData;

	}else
	{
		alert("δָ��֤��Ӧ�ð汾");
		return -1;
	}
}

//Verify Signed Data /////////////////////////////
function gVerifySign(strSignedData, strUserCert, strOrigindata)
{			 
	if(globalAppType==1)
	{
		//�°��֤��Ӧ����֤
		return verifySignedData(strSignedData, strUserCert, strOrigindata);
	}
	else if(globalAppType==0)
	{
		//�ɰ��֤��Ӧ����֤
		return gFuncVerifySign(strSignedData, strUserCert, strOrigindata);
	}else
	{
		alert("δָ���¾ɰ�֤��Ӧ��");
	}
}

function doSecuritySubmit(form) {
	return gFuncSubmit(form);
}

/*
End About Ocx Crypt Operation
**************************************************
*/

/*
End Reserved Functions
**************************************************
*/
//Reset Global Return Code Object ////////////////
function gFuncResetGRet()
{
	g_objRet.ErrOcxRet = -1000;
	g_objRet.ErrOcxPalace = "";

	g_objER = new CEncRule();
}


//Check Login Form ///////////////////////////////
function gFuncCheckForm(strFormName)
{
	var objForm = eval(strFormName);

	if (objForm == null)
	{
		g_objRet.ErrOcxRet = "-100";
		g_objRet.ErrOcxPalace = "No This Form";
		return null;
	}

	if (objForm.SecX_SignData == null)
	{
		g_objRet.ErrOcxRet = "-100";
		g_objRet.ErrOcxPalace = "No SignData Area";
		return null;
	}

	if (objForm.SecX_UserCert == null)
	{
		g_objRet.ErrOcxRet = "-100";
		g_objRet.ErrOcxPalace = "No UserCert Area";
		return null;
	}

	return objForm;
}

//All Over User`s Form ///////////////////////////
function gFuncAllOverForm(objForm)
{
	var strNameList = "";
	var strName = "";
	var strType = "";
	var strValue = "";

	var i, j;

	if (g_objNR.Flag)
	{
		//Need Naming Rule ///////////////////////
		for (i = 0; i < objForm.elements.length; i++ )
		{
			if (objForm.elements(i).name.indexOf(g_objNR.RuleString) > 0)
			{
				////Not Support ////
				break;
			}
		}
	}
	else
	{
		//No Naming Rule /////////////////////////

		for (i = 0; i < objForm.length; i++)
		{
			strName = objForm(i).name;
			strType = objForm(i).type;

			for (j = 0; j < g_objF.ReIType.length; j++)
			{
				////Support These simple item ////
				if (objForm(i).type == g_objF.ReIType[j])
				{
					strValue = objForm(i).value;
					gFuncFormItem2XML(strName, strType, strValue);
					strNameList += strName +  + "&&&";
					break;
				}
			}

			if (objForm(i).type == "select-multiple" && (strNameList.indexOf(strName) == -1))
			{
				////Is Multi Select //////////////
				for (j = 0; j < objForm(i).length; j++)
				{
					if (objForm(i).item(j).selected)
					{
						strValue += ", " + objForm(i).item(j).value;
					}
				}
				////Set Item Value ///////////////
				strValue = strValue.substr(2, strValue.length - 2);
				gFuncFormItem2XML(strName, strType, strValue);
				strNameList += strName + "&&&";
			}

			if ((objForm(i).type == "checkbox") && (strNameList.indexOf(strName) == -1))
			{
				////Is CheckBox //////////////////
				for (j = 0; j < objForm.length; j++)
				{
					if (objForm(j).checked && (objForm(j).name == strName))
					{
						strValue += ", " + objForm(j).value;
					}
				}
				strValue = strValue.substr(2, strValue.length - 2);
				gFuncFormItem2XML(strName, strType, strValue);
				strNameList += strName + "&&&";
			}

			if ((objForm(i).type == "radio") && (strNameList.indexOf(strName) == -1))
			{
				////Is CheckBox //////////////////
				for (j = 0; j < objForm.length; j++)
				{
					if (objForm(j).checked && (objForm(j).name == strName))
					{
						strValue += ", " + objForm(j).value;
					}
				}
				strValue = strValue.substr(2, strValue.length - 2);
				gFuncFormItem2XML(strName, strType, strValue);
				strNameList += strName + "&&&";
			}

			strValue = "";
		}
	}

	return g_objER.XMLHeader + g_objER.XMLDoc.xml;
}

//Generate XML String ////////////////////////////
function gFuncFormItem2XML(strTag, strType, strValue)
{
	if (strTag == "")
	{
		return g_objRet.ErrPara;
	}

	strTag = gFuncURLEncode(strTag);

	var strPath = "/SecXMSG";
	var objNode = g_objER.XMLDoc.selectSingleNode(strPath);

	var objTemp = g_objER.XMLDoc.createElement(strTag);
	objNode.appendChild(objTemp);

	objTemp.setAttribute("Type", strType);

	//Need CDATA Tag /////////////////////////////
	var objCDATA = g_objER.XMLDoc.createCDATASection(strValue);
	//objCDATA.text = strValue;
	objTemp.appendChild(objCDATA);

	return g_objRet.Success;
}


//URL Encode /////////////////////////////////////
function gFuncURLEncode(str)
{
	//return str;
	var str1 = str;
	var i, c;
	var ret = ""
	var strSpecial = "!\"#$%&'()*+,/:;<=>?@[\]^`{|}~%";
	for(i = 0; i < str1.length ;i++ ){
			c=str1.charAt(i);
			if(c==" ")
				str1=str1.replace(" ","+");
			else if(strSpecial.indexOf(c)!=-1)
			{
				var temp = "%"+str1.charCodeAt(i).toString(16);
				str1 =str1.replace(c,temp);
				i=i+temp.length - 1;
			}

	}
	return str1;
}

/*
Function:	gFuncSubmit
Parameter:	strFormName	����ID����
*/
function gFuncSubmit(strFormName)
{
	gFuncResetGRet();

	var strToPost;

	var objForm = eval(strFormName)

	//Check Parameter ////////////////////////////
	if (!objForm)
	{
		//gFuncGetLastError(g_objRet.ErrPara);
		alert("�޴�: " + strFormName +" ����");
		return false;
	}

	//Encrypt Data & Sign Data ///////////////
	if (!document.all("SecX_Form"))
	{
		document.body.insertAdjacentHTML("BeforeEnd",g_objAH.HTMLFormString);
	}
	else
	{
		document.all("SecX_Form").SecX_SignData.value = "";
	    //����ǩ���ּ��ܽ����в�ע��
		//document.all("SecX_Form").SecX_EncData.value = "";
		document.all("SecX_Form").SecX_UserCert.value = "";
		document.all("SecX_Form").SecX_ActionType.value = "";
		document.all("SecX_Form").SecX_APPType.value = "";
		//document.all("SecX_Form").item(0).value = "";
		//����ǩ���ּ��ܽ�����ע��
		document.all("SecX_Form").SecX_OrginData.value = "";
	}

	//End Check //////////////////////////////////

	var strToPost = gFuncAllOverForm(objForm);


	var sSecX_SignData = gSign(strToPost);
	var sSecX_UserCert = gGetCert();
	var SecX_EncData = gEncrypt(strToPost,g_objSI.SvrCert);

	if(sSecX_SignData == -1)
	{
		alert("��ǩ��ʧ�ܣ�");
		return false;
	}
	if(sSecX_UserCert == -1)
	{
		alert("��ȡ�û�֤��ʧ�ܣ�");
		return false;
	}
	if(SecX_EncData == -1)
	{
		alert("������ʧ�ܣ�");
		return false;
	}

	//Sign & Encrypt /////////////////////////////

	//org.apache.struts.taglib.html.TOKEN
	/*
	for (i = 0; i < objForm.length; i++)
	{
		if(objForm.item(i).name=="org.apache.struts.taglib.html.TOKEN")
		{
			document.all("SecX_Form").item(0).value = objForm.item(i).value;
			break;
		}
	}
	*/
	
	//����ǩ���ּ��ܽ�����ע��
	document.all("SecX_Form").SecX_OrginData.value = strToPost;
	document.all("SecX_Form").SecX_ActionType.value = "Sign";//"",Sign,Encrypt,Both
	document.all("SecX_Form").SecX_SignData.value = sSecX_SignData;
	document.all("SecX_Form").SecX_UserCert.value = sSecX_UserCert;
	//����ǩ���ּ��ܽ����в�ע��
	//document.all("SecX_Form").SecX_EncData.value = SecX_EncData;
	document.all("SecX_Form").SecX_APPType.value = globalAppType;
	

	//alert("��������XML��" + strToPost);
	//alert("ǩ�������" + document.all("SecX_Form").SecX_SignData.value);
    //alert("���ܽ����" + document.all("SecX_Form").SecX_EncData.value);
	//alert("�û�֤�飺" + document.all("SecX_Form").SecX_UserCert.value);

	//Modify SecX Form Action ////////////////////
	document.all("SecX_Form").action = objForm.action;
	document.all("SecX_Form").target = objForm.target;

	//Submit Form/////////////////////////////////
	document.all("SecX_Form").submit();

	return true;
}


///SecX_Common.js



/////����ӿ�ת��Ϊ�ű��ӿ�////////////////////////
/////����ӿ�ת��Ϊ�ű��ӿ�////////////////////////
//var g_objF = new CFormR();
//var g_objAH = new CAdditionHTML();
//var g_objASecH = new CAdditionSecurityHTML();

/////Certificate
function importCert(sCertSrc, SrcType, sPwd) {

	if (sPwd != null)
		return oCert.importCert(sCertSrc, SrcType, sPwd);
	else
		return oCert.importCert(sCertSrc, SrcType);
}

function exportCert(DstType, sCertPath){

	if (sCertPath != null)
		return oCert.exportCert(DstType, sCertPath);
	else
		return oCert.exportCert(DstType);
}

function getBasicCertInfoByOID(OID) {

	return oCert.getBasicCertInfoByOID(OID);
}

function getExtCertInfoByOID(sOID) {

	return oCert.getExtCertInfoByOID(sOID);
}

function checkValidaty(sDate) {

	if (sDate != null)
		return oCert.checkValidaty(sDate);
	else
		return oCert.checkValidaty();
}

function validateCert(sCertChain, sCRL) {
	
	if (sCRL != null)
		return oCert.validateCert(sCertChain, sCRL);
	else
		return oCert.validateCert(sCertChain);
}

function modifyPFXPwd(sPFXPath, sOldPwd, sNewPwd) {

	return oCert.modifyPFXPwd(sPFXPath, sOldPwd, sNewPwd);
}

/////Crypto

function setUserCfg(CfgFlag, sCfgValue, sExt1CfgValue, sExt2CfgValue) {

	return oCrypto.setUserCfg(CfgFlag, sCfgValue, sExt1CfgValue, sExt2CfgValue);
}

function setAlgFlag() {

	return oCrypto.setAlgFlag(AlgType, AlgFlag);
}

function signedDataByP7(sInData, sContainerName) {

	if (sContainerName != null)
		return oCrypto.signedDataByP7(sInData, sContainerName);
	else
		return oCrypto.signedDataByP7(sInData);
}

function verifySignedDataByP7(sInData) {

	return oCrypto.verifySignedDataByP7(sInData);
}

function signedData(sInData, sContainerName) {

	if (sContainerName != null)
		return oCrypto.signedData(sInData, sContainerName);
	else
		return oCrypto.signedData(sInData);
}

function verifySignedData(sInData, sCert, sOriData) {

	return oCrypto.verifySignedData(sInData, sCert, sOriData);
}

function envelopedData(sInData, flag, sContainerName) {

	if (sContainerName != null)
		return oCrypto.envelopedData(sInData, flag, sContainerName);
	else
		return oCrypto.envelopedData(sInData, flag);
}

function generateRandom(RandomLen) {

	return oCrypto.generateRandom(RandomLen);
}


function getCertBasicInfo(sCert, OID) {

	oCert.importCert(sCert, CERT_SRC_BASE64);
	
	return oCert.getBasicCertInfoByOID(OID);
	
}

/////Device
function changeUserPin(sCSPName, sExtLib, sOldPin, sNewPin) {

	return oDevice.changeUserPin(sCSPName, sExtLib, sOldPin, sNewPin);
}

function userLogin(sCSPName, sUserPin) {

	return oDevice.userLogin(sCSPName, sUserPin);
}

function enumUserCertificates(sCSPName) {

	return oDevice.enumUserCertificates(sCSPName);
}

function getKeyRetrys(sExtLib) {

	return oDevice.getKeyRetrys(sExtLib);
}
/////Util
function getUserList() {

	return oUtil.getUserList();
	
}

function getUserInfoByContainer(sContainerName, TypeID) {
	
	return oUtil.getUserInfoByContainer(sContainerName, TypeID);
}

function base64EncodeString(sInData) {

	return oUtil.base64EncodeString(sInData);
}

function base64EncodeFile(sFilePath) {

	return oUtil.base64EncodeFile(sFilePath);
}

function getUserCert(strContainerName)
{  
   var UserCert = getSignCert(strContainerName);
   return UserCert;
}

function getExchCert(strContainerName)
{  
	var strDeviceType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
	var KeyType = getUserInfoByContainer(strContainerName, CERT_XML_KEYTYPE);
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
	   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_EXCHPATH);
	   importCert(Cert,CERT_SRC_FILE);
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
	   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_EXCHPATH);
	   importCert(Cert,CERT_SRC_FILE);
	}
	else {
		//���ܿ�
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);
		importCert(strContainerName, CERT_SRC_CONTAINER_ENC, strCSPName);
	}
    var UserCert = exportCert(CERT_DST_BASE64);
    return UserCert;
}

function getSignCert(strContainerName)
{  
	var strDeviceType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
	var KeyType = getUserInfoByContainer(strContainerName, CERT_XML_KEYTYPE);
	
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
		if (KeyType == 1) {
			//��֤��
		   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_EXCHPATH);
		   importCert(Cert,CERT_SRC_FILE);
		}
		else if (KeyType == 2) {
			//˫֤��
		   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_SIGNPATH);
		   importCert(Cert,CERT_SRC_FILE);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
		if (KeyType == 1) {
			//��֤��
		   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_EXCHPATH);
		   importCert(Cert,CERT_SRC_FILE);
		}
		else if (KeyType == 2) {
			//˫֤��
		   var Cert = getUserInfoByContainer(strContainerName,CERT_XML_SIGNPATH);
		   importCert(Cert,CERT_SRC_FILE);
		}
		else {
			alert("�����ļ�����");
			return false;
		}

	}
	else {
		//���ܿ�
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);
		if (KeyType == 1) {
			//��֤��
			importCert(strContainerName, CERT_SRC_CONTAINER_ENC, strCSPName);
		}
		else if (KeyType == 2) {
			//˫֤��
			importCert(strContainerName, CERT_SRC_CONTAINER_SIGN, strCSPName);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
	}
	
    var UserCert = exportCert(CERT_DST_BASE64);
    return UserCert;
}

 /*
Function:	getCertDetail  
Parameter:	strContainerName������
			ItemNo		    ϸĿ����
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function getCertDetail(strContainerName, ItemNo)
{
	//�°�֤��Ӧ�û�ȡϸĿ
	var sCert = getUserCert(strContainerName);
	switch(ItemNo)
	{
		case 0:
			return sCert;
			break;
		case 8:
			return getCertBasicInfo(sCert,4);
			break;
		case 11:
			return getCertBasicInfo(sCert,5);
			break;
		case 12:
			return getCertBasicInfo(sCert,6);
			break;
		case 13:
			return getCertBasicInfo(sCert,42);
			break;
		case 14:
			return getCertBasicInfo(sCert,45);
			break;
		case 15:
			return getCertBasicInfo(sCert,46);
			break;
		case 16:
			return getCertBasicInfo(sCert,44);
			break;
		case 17:
			return getCertBasicInfo(sCert,41);
			break;
		case 18:
			return getCertBasicInfo(sCert,43);
			break;
		case 20:
			return getCertBasicInfo(sCert,7);
			break;
		case 22:
			return getExtCertInfoByOID("2.16.840.1.113732.2");
			break;
		case 23:
			return checkValidaty();
			break;
		default:
			return getCertBasicInfo(sCert,ItemNo);
			break;
	}
}
 /*
Function:	getCertDetail_Key����Ҫ��װע���Key�л��֤����ϸ��Ϣ
Parameter:	
			ItemNo		    ϸĿ����
			strContainerName������
			keyType         key����  
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function getCertDetail_Key(ItemNo,strContainerName,keyType)
{
	//�°�֤��Ӧ�û�ȡϸĿ
	if(keyType==null || keyType=="")
	{
		keyType = "M&W eKey XCSP";
	}
	importCert(strContainerName, CERT_SRC_CONTAINER_ENC, keyType);
	switch(ItemNo)
	{
		case 0:
			var sCert = exportCert(CERT_DST_BASE64);
			return sCert;
			break;
		case 8:
			return getBasicCertInfoByOID(4);
			break;
		case 11:
			return getBasicCertInfoByOID(5);
			break;
		case 12:
			return getBasicCertInfoByOID(6);
			break;
		case 13:
			return getBasicCertInfoByOID(42);
			break;
		case 14:
			return getBasicCertInfoByOID(45);
			break;
		case 15:
			return getBasicCertInfoByOID(46);
			break;
		case 16:
			return getBasicCertInfoByOID(44);
			break;
		case 17:
			return getBasicCertInfoByOID(41);
			break;
		case 18:
			return getBasicCertInfoByOID(43);
			break;
		case 20:
			return getBasicCertInfoByOID(7);
			break;
		case 22:
			return getExtCertInfoByOID("2.16.840.1.113732.2");
			break;
		case 23:
			return checkValidaty();
			break;
		default:
			return getBasicCertInfoByOID(ItemNo);
			break;
	}

}
 /*
Function:	getCertDetail_Key����Ҫ��װע���Key�л��֤����ϸ��Ϣ
Parameter:	ItemNo		    ϸĿ����
			keyType         key����
 * 0֤��PEM����
 * 1֤��汾
 * 2֤�����к�
 * 3֤��ǩ���㷨
 * 4֤�鷢���߹�����
 * 5֤�鷢������֯��
 * 6֤�鷢���߲�����
 * 7֤�鷢����ʡ����
 * 8֤�鷢����ͨ����
 * 9֤�鷢���߳�����
 * 10֤�鷢����EMAIL��ַ
 * 11֤����Ч����ʼ
 * 12֤����Ч�ڽ�ֹ
 * 13�û�������
 * 14�û���֯��
 * 15�û�������
 * 16�û�ʡ����
 * 17�û�ͨ����
 * 18�û�������
 * 19�û�EMAIL��ַ
 * 20�û�DER��Կֵ
 * 21�û�֤���Զ��弶��	 
 * 22֤��UniqueID
 * 23֤��ʣ����Ч��
 */
//Parse Cert /////////////////////////////////////
function getCertDetail_Key(ItemNo, keyType)
{
	//�°�֤��Ӧ�û�ȡϸĿ
	var strTemp;
	var strContainerName;
	var strExpireTime;
	var i;
	var len;
	var sCert;
	if(keyType==null || keyType=="")
	{
		keyType = "M&W eKey XCSP";
	}
	strTemp = enumUserCertificates(keyType);
	while (1) {
		i=strTemp.indexOf("&&&");
		if (i <= 0) {
			break;
		}
		var strContainerNameTmp = strTemp.substring(0,i);
		importCert(strContainerNameTmp, CERT_SRC_CONTAINER_ENC, keyType);
		sCert = exportCert(CERT_DST_BASE64);
		var strExpireTimeTmp = getCertBasicInfo(sCert,6);
		if(strExpireTime == null)
		{
			strExpireTime = strExpireTimeTmp;
			strContainerName = strContainerNameTmp;
		}
		else if(strExpireTime != null && strExpireTime < strExpireTimeTmp)
		{
			strExpireTime = strExpireTimeTmp;
			strContainerName = strContainerNameTmp;
		}
		else
		{
		}

		len = strTemp.length;
		strTemp = strTemp.substring(i+3,len);
	}
	importCert(strContainerName, CERT_SRC_CONTAINER_ENC, keyType);
	switch(ItemNo)
	{
		case 0:
			sCert = exportCert(CERT_DST_BASE64);
			return sCert;
			break;
		case 8:
			return getBasicCertInfoByOID(4);
			break;
		case 11:
			return getBasicCertInfoByOID(5);
			break;
		case 12:
			return getBasicCertInfoByOID(6);
			break;
		case 13:
			return getBasicCertInfoByOID(42);
			break;
		case 14:
			return getBasicCertInfoByOID(45);
			break;
		case 15:
			return getBasicCertInfoByOID(46);
			break;
		case 16:
			return getBasicCertInfoByOID(44);
			break;
		case 17:
			return getBasicCertInfoByOID(41);
			break;
		case 18:
			return getBasicCertInfoByOID(43);
			break;
		case 20:
			return getBasicCertInfoByOID(7);
			break;
		case 22:
			return getExtCertInfoByOID("2.16.840.1.113732.2");
			break;
		case 23:
			return checkValidaty();
			break;
		default:
			return getBasicCertInfoByOID(ItemNo);
			break;
	}
}

function envelopedDecodeData(sInData, sContainerName)
{  
	var decryptData="";
	var strDeviceType = getUserInfoByContainer(sContainerName, CERT_XML_DEVICETYPE);
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
	   var exchpfx = getUserInfoByContainer(sContainerName,CERT_XML_EXCHPFXPATH);
	   decryptData = envelopedData(sInData,ENVELOP_DEC,exchpfx);
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
	    decryptData = envelopedData(sInData,ENVELOP_DEC,sContainerName);
	}
	else {
		//���ܿ�
		decryptData = envelopedData(sInData,ENVELOP_DEC,sContainerName);
	}
    return decryptData;
}

function verifyCertByTrustPool(sCert) {

	//��ӿ��趨ԭ��������������
	var sTemp = "temp";

	var sTrustType = null;
	var ret = null;

	importCert(sCert, 1);
	var TrustCount = getUserInfoByContainer(sTemp, CERT_XML_CACOUNTS);
	for (i=1; i<=TrustCount; i++) {
		sTrustType = getUserInfoByContainer(i, CERT_XML_CANUMTYPE);
		sTrustPath = getUserInfoByContainer(sTrustType, 20);
		ret = validateCert(sTrustPath);
		if (ret == 0)
		{
			break;
		}
	}

}

function changeUserPassword(strContainerName,oldPwd,newPwd)
{
	var strP12Path = null;
	var rv = 0;
	var strDeviceType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
	var KeyType = getUserInfoByContainer(strContainerName, CERT_XML_KEYTYPE);
	
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
			rv = modifyPFXPwd(strP12Path, oldPwd, newPwd);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
			var strExchPath = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
			var rvtmp = modifyPFXPwd(strP12Path, oldPwd, newPwd);
			rv = modifyPFXPwd(strExchPath, oldPwd, newPwd);

		}
		else {
			alert("�����ļ�����");
			return -1;
		}
		setUserCfg(CRYPT_CFGTYPE_P12, strP12Path, newPwd, "");
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);

		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
			rv = modifyPFXPwd(strP12Path, oldPwd, newPwd);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
			var strExchPath = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
			var rvtmp = modifyPFXPwd(strP12Path, oldPwd, newPwd);
			rv = modifyPFXPwd(strExchPath, oldPwd, newPwd);
		}
		else {
			alert("�����ļ�����");
			return -1;
		}
		var strExtLib = strContainerName;
			
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, newPwd);
	}
	else {
		//���ܿ�
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);
		var strExtLib = getUserInfoByContainer(strDevType, CERT_XML_DEVICEAFFIX);

		if (strExtLib == null)
			strExtLib = "Temp";
		rv = changeUserPin(strCSPName, strExtLib, oldPwd,newPwd);	
		//alert(rv);
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, newPwd);
	}
	return rv;
}

// login
function Login(strFormName,strContainerName,strPin) {
	var ret;
	var objForm = eval(strFormName);

	if (!objForm)
	{
		//gFuncGetLastError(g_objRet.ErrPara);
		alert("�޴�: " + strFormName +" ����");
		return false;
	}

	if (strPin == null || strPin == "") {
		alert("������Key�ı�������");
		return false;
	}

	//Add a hidden item ...
	if (!objForm.SecX_SignData)
	{
		objForm.insertAdjacentHTML("BeforeEnd",g_objAOH.HTMLItemString);
	}
	else
	{
		objForm.SecX_SignData.value = "";
		objForm.SecX_NameList.value = "";
		objForm.SecX_UserCert.value = "";
		objForm.SecX_ContainerName.value = "";
		objForm.SecX_APPType.value = "";
	}


	var strP12Path = null;

	var strClientSignedData = "";
	var strExtLib = "Temp";
	var strUserCert = "";

	var strDeviceType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
	var KeyType = getUserInfoByContainer(strContainerName, CERT_XML_KEYTYPE);
    var strCAType = getUserInfoByContainer(strContainerName, CERT_XML_CATYPE);
	
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		if (importCert(strP12Path, CERT_SRC_PKCS12, strPin) != 0) {
			//alert("�������");
			checkPassWord(strPin);
			return false;
		}
		setUserCfg(CRYPT_CFGTYPE_P12, strP12Path, strPin, "");
		strClientSignedData = signedData(g_objSI.SvrOriData, strContainerName);
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);

		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		if (importCert(strP12Path, CERT_SRC_PKCS12, strPin) != 0) {
			//alert("�������");
			checkPassWord(strPin);
			return false;
		}
		
		strExtLib = strContainerName;
			
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, strPin);
		strClientSignedData = signedData(g_objSI.SvrOriData, strContainerName);

	}
	else {
		//���ܿ�
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);
		strExtLib = getUserInfoByContainer(strDevType, CERT_XML_DEVICEAFFIX);

		if (strExtLib == null)
			strExtLib = "Temp";
		ret = userLogin(strCSPName, strPin);

		if (ret != 0 ){
			var retryNum = getKeyRetrys(strExtLib);
			switch (retryNum) {
				case -1010:
					alert("δ��ȷ�������ܿ�");
					return false;
					break;
				case -1011:
					alert("���ܿ�����ʧ��");
					return false;
					break;
				default:
					alert("�������,���Ի�ʣ��"+retryNum+"��");
					checkPassWord(strPin);
					return false;
					break;
			}
		}
		
		if (KeyType == 1) {
			//��֤��
			if(importCert(strContainerName, CERT_SRC_CONTAINER_ENC, strCSPName) != 0){
				alert("δ��ȷ�������ܿ�");
				return false;
			}
		}
		else if (KeyType == 2) {
			//˫֤��
			if(importCert(strContainerName, CERT_SRC_CONTAINER_SIGN, strCSPName)!= 0){
				alert("δ��ȷ�������ܿ�");
				return false;
			}
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, strPin);
		//var strClientSignedData = signedDataByP7(g_objSI.SvrOriDataNew, strContainerName);
		strClientSignedData = signedData(g_objSI.SvrOriData, strContainerName);
	
	}

	if (verifySignedData(g_objSI.SvrSignData,g_objSI.SvrCert,g_objSI.SvrOriData) != 0) {
		alert("��֤��������֤��Ϣ����!");
		return false;
	}

	if(strClientSignedData.length < 100)
	{
		alert("����ǩ��ʧ�ܣ�");
		return false;
	}

    strUserCert = exportCert(CERT_DST_BASE64);
    if(strUserCert.length < 100)
	{
		alert("��ȡ�û�֤��ʧ�ܣ�");
		return false;
	}

	objForm.UserPwd.value = "";
	objForm.SecX_SignData.value = strClientSignedData;
	objForm.SecX_UserCert.value = strUserCert;
	objForm.SecX_ContainerName.value = strContainerName;
	objForm.SecX_APPType.value = globalAppType;

	var rv = checkValidaty();
	return alertValidDay(rv);

}

// Login_P7
function Login_P7(strFormName,strContainerName,strPin) {
	var ret;
	var objForm = eval(strFormName);

	if (!objForm)
	{
		//gFuncGetLastError(g_objRet.ErrPara);
		alert("�޴�: " + strFormName +" ����");
		return false;
	}

	if (strPin == null || strPin == "") {
		alert("������Key�ı�������");
		return false;
	}

	//Add a hidden item ...
	if (!objForm.SecX_SignData)
	{
		objForm.insertAdjacentHTML("BeforeEnd",g_objAOH.HTMLItemString);
	}
	else
	{
		objForm.SecX_SignData.value = "";
		objForm.SecX_NameList.value = "";
		objForm.SecX_UserCert.value = "";
		objForm.SecX_ContainerName.value = "";
		objForm.SecX_APPType.value = "";
	}


	var strP12Path = null;

	var strClientSignedData = "";
	var strExtLib = "Temp";
	var strUserCert = "";

	var strDeviceType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
	var KeyType = getUserInfoByContainer(strContainerName, CERT_XML_KEYTYPE);
    var strCAType = getUserInfoByContainer(strContainerName, CERT_XML_CATYPE);
	
	if (strDeviceType == "BJSOFT") {
		//P12���㷨
		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		if (importCert(strP12Path, CERT_SRC_PKCS12, strPin) != 0) {
			//alert("�������");
			checkPassWord(strPin);
			return false;
		}
		setUserCfg(CRYPT_CFGTYPE_P12, strP12Path, strPin, "");
		strClientSignedData = signedDataByP7(g_objSI.SvrOriData, strContainerName);
	}
	else if (strDeviceType == "BJCSP0001"){
		//��CSP
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);

		if (KeyType == 1) {
			//��֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_EXCHPFXPATH);
		}
		else if (KeyType == 2) {
			//˫֤��
			strP12Path = getUserInfoByContainer(strContainerName, CERT_XML_SIGNPFXPATH);
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		if (importCert(strP12Path, CERT_SRC_PKCS12, strPin) != 0) {
			//alert("�������");
			checkPassWord(strPin);
			return false;
		}
		
		strExtLib = strContainerName;
			
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, strPin);
		strClientSignedData = signedDataByP7(g_objSI.SvrOriData, strContainerName);

	}
	else {
		//���ܿ�
		var strDevType = getUserInfoByContainer(strContainerName, CERT_XML_DEVICETYPE);
		var strCSPName = getUserInfoByContainer(strDevType, CERT_XML_DEVICEPROVIDER);
		strExtLib = getUserInfoByContainer(strDevType, CERT_XML_DEVICEAFFIX);

		if (strExtLib == null)
			strExtLib = "Temp";
		ret = userLogin(strCSPName, strPin);

		if (ret != 0 ){
			var retryNum = getKeyRetrys(strExtLib);
			switch (retryNum) {
				case -1010:
					alert("δ��ȷ�������ܿ�");
					return false;
					break;
				case -1011:
					alert("���ܿ�����ʧ��");
					return false;
					break;
				default:
					alert("�������,���Ի�ʣ��"+retryNum+"��");
					checkPassWord(strPin);
					return false;
					break;
			}
		}
		
		if (KeyType == 1) {
			//��֤��
			if(importCert(strContainerName, CERT_SRC_CONTAINER_ENC, strCSPName) != 0){
				alert("δ��ȷ�������ܿ�");
				return false;
			}
		}
		else if (KeyType == 2) {
			//˫֤��
			if(importCert(strContainerName, CERT_SRC_CONTAINER_SIGN, strCSPName)!= 0){
				alert("δ��ȷ�������ܿ�");
				return false;
			}
		}
		else {
			alert("�����ļ�����");
			return false;
		}
		
		setUserCfg(CRYPT_CFGTYPE_CSP, strCSPName, strExtLib, strPin);
		//var strClientSignedData = signedDataByP7(g_objSI.SvrOriDataNew, strContainerName);
		strClientSignedData = signedDataByP7(g_objSI.SvrOriData, strContainerName);
	
	}
	if (verifySignedDataByP7(g_objSI.SvrSignData) != 0) {
		alert("��֤��������֤��Ϣ����!");
		return false;
	}

	if(strClientSignedData.length < 100)
	{
		alert("����ǩ��ʧ�ܣ�");
		return false;
	}

    strUserCert = exportCert(CERT_DST_BASE64);
    if(strUserCert.length < 100)
	{
		alert("��ȡ�û�֤��ʧ�ܣ�");
		return false;
	}

	objForm.SecX_SignData.value = strClientSignedData;
	objForm.SecX_UserCert.value = strUserCert;
	objForm.SecX_ContainerName.value = strContainerName;
	objForm.SecX_APPType.value = globalAppType;

	var rv = checkValidaty();
	return alertValidDay(rv);

}

function alertValidDay(ret)
{
 	var ValidDay;
	ValidDay = parseInt(ret);
	
	if (parseInt(ret) <= 60 && parseInt(ret) > 0) {
		alert("����֤�黹��" + ValidDay + "����ڣ�\n�������쵽��������֤����֤���İ���֤�����������\n�����Ӱ������������˰����ɲ���Ҫ���鷳����ʧ��\n֤���û�ע��鿴��֪�����������������¼��\nhttp://services.bjca.org.cn/dishui/��\n��ѯ�绰��58515511��");
	}
	
	if(parseInt(ret) <= 0){
	    alert("����֤���ѹ��� "+ -parseInt(ret) +" �죬\n�뾡�쵽��������֤����֤���İ���֤�����������\n�����Ӱ������������˰����ɲ���Ҫ���鷳����ʧ��\n֤���û�ע��鿴��֪�����������������¼��\nhttp://services.bjca.org.cn/dishui/��\n��ѯ�绰��58515511��");
		return false;
	}
	return true;
}

/*
**************************************************
Begin Application Development Interface
*/

/*
������
	ȡ��UniqueID�б�
������
	strListID�������˵�������ID�����ID����ID�������б�ID��
���أ�
	��
*/
function GetCertList(strListID) {

	var strTemp;
	var strOption;
	var len;
	var strName;
	var strUniqueID;
	var objListID = eval(strListID);

	strTemp = BJCASecClt.GetCertList();
	len = strTemp.length;

	while (1) {
		i=strTemp.indexOf("&&&");
		if (i <= 0) {
			break;
		}
		strOption = strTemp.substring(0,i);
		strName = strOption.substring(0, strOption.indexOf("||"));
		strUniqueID = strOption.substring(strOption.indexOf("||") + 2, strOption.length);
		var objItem = new Option(strName,strUniqueID)
		objListID.add(objItem);
		len = strTemp.length;
        strTemp = strTemp.substring(i+3,len);
	}

	var objListID = null;
}

/*
Function:	gReLogin 
Parameter:	strUniqueID	֤���UniqueID
			strPassword	˽Կ��������
*/
function gReLogin(strUniqueID, strPassword)
{
	var ret = gFuncInitialSession(strUniqueID, strPassword);
	switch(ret)
	{
		case -1:
			alert("UniqueID �������");
			gFuncGetLastError(g_objRet.ErrPara);
			return false;
			break;
		case -2:
			alert("��ȡ֤��·������");
			gFuncGetLastError(g_objRet.ErrCertPath);
			return false;
			break;
		case -3:
			alert("��ȡ֤����·������");
			gFuncGetLastError(g_objRet.ErrCertChainPath);
			return false;
			break;
		case -4:
			alert("˽Կ�豸����");
			gFuncGetLastError(g_objRet.ErrCertKeyPath);
			return false;
			break;
		case -5:
			alert("�������");
			gFuncGetLastError(g_objRet.ErrCertKeyPWD);
			return false;
			break;
		case -6:
			alert("֤�����豸����");
			gFuncGetLastError(g_objRet.ErrCertChain);
			return false;
			break;
		case -7:
			alert("ϵͳ�쳣����");
			gFuncGetLastError(g_objRet.ErrUnknown);
			return false;
			break;
		case 0:
			//Initial Ocx Success ////////////////
			return true;
			break;
	}

	return true;
}

/*
Function:	gFuncLogin
Parameter:	strFormName	����ID����
			strUniqueID	֤���UniqueID
			strPassword	˽Կ��������
*/
function gFuncLogin(strFormName, strUniqueID, strPassword)
{
	gFuncResetGRet();
	var ret;
	var objForm = eval(strFormName);
    var strServerRan = g_objSI.SvrOriData;
	var strServerSignedData = g_objSI.SvrSignData;
	var strServerCert = g_objSI.SvrCert;
			
	var sSignedData = "";

	if (!objForm)
	{
		//gFuncGetLastError(g_objRet.ErrPara);
		alert("�޴�: " + strFormName +" ����");
		return false;
	}

	if (strPassword == "")
	{
		alert("�����Ϊ��!")
		return false;
	}

	if (strUniqueID == "")
	{
		gFuncGetLastError(g_objRet.ErrPara);
		return false;
	}

	ret = gFuncGetCertDetail(strUniqueID, 23);

	var valValue = alertValidDay(ret);

	if(!valValue){
		return false;
	}
	//Add a hidden item ...
	if (!objForm.SecX_SignData)
	{
		objForm.insertAdjacentHTML("BeforeEnd",g_objAOH.HTMLItemString);
	}
	else
	{
		objForm.SecX_SignData.value = "";
		objForm.SecX_NameList.value = "";
		objForm.SecX_UserCert.value = "";
		objForm.SecX_ContainerName.value = "";
		objForm.SecX_APPType.value = "";
	}

	ret = gFuncInitialSession(strUniqueID, strPassword);
	switch(ret)
	{
		case -1:
			alert("UniqueID �������");
			gFuncGetLastError(g_objRet.ErrPara);
			return false;
			break;
		case -2:
			alert("��ȡ֤��·������");
			gFuncGetLastError(g_objRet.ErrCertPath);
			return false;
			break;
		case -3:
			alert("��ȡ֤����·������");
			gFuncGetLastError(g_objRet.ErrCertChainPath);
			return false;
			break;
		case -4:
			alert("˽Կ�豸����");
			gFuncGetLastError(g_objRet.ErrCertKeyPath);
			return false;
			break;
		case -5:
			//alert("�������");
			gFuncGetLastError(g_objRet.ErrCertKeyPWD);
			checkPassWord(strPassword);
			return false;
			break;
		case -6:
			alert("֤�����豸����");
			gFuncGetLastError(g_objRet.ErrCertChain);
			return false;
			break;
		case -7:
			alert("ϵͳ�쳣����");
			gFuncGetLastError(g_objRet.ErrUnknown);
			return false;
			break;
		case 0:
			//Initial Ocx Success ////////////////
			BJCASecClt.serverCertPEM = g_objSI.SvrCert;

			ret = gFuncVerifySign(strServerSignedData, strServerCert, strServerRan);
			if (ret != g_objRet.Success)
			{
				BJCASecClt.ClearSession();
				gFuncGetLastError(g_objRet.ErrVeriSgn);
				return false;
			}

			sSignedData = gFuncSign(strServerRan);
			if(sSignedData.length < 100)
			{
				return false;
			}
			objForm.UserPwd.value = "";
			objForm.SecX_SignData.value = sSignedData;
//			objForm.SecX_UserCert.value = gFuncGetDetailNoInit(strUniqueID,0);
			objForm.SecX_UserCert.value = BJCASecClt.UserCert;
			objForm.SecX_ContainerName.value = strUniqueID;
			objForm.SecX_APPType.value = globalAppType;

			//alert(objForm.SecX_SignData.value);
	}

	return true;;
}


/*
Function:	gFuncModifyPWD
Parameter:	strUniqueID		֤���û���ʶ
			strKeyPWD		ԭʼ����
			strNewPWD		�¿���
*/
function gFuncModifyPWD(strUniqueID, strKeyPWD, strNewPWD)
{
	gFuncResetGRet();

	var ret;
	ret = BJCASecClt.ChangePWD(strUniqueID, strKeyPWD, strNewPWD);
	if (ret != 0)
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "ChangePWD";
		return g_objRet.ErrModifyPWD;
	}
	//gFuncClearSession();
	return g_objRet.Success;
}

/*
Function:	gFuncGetLastError
Parameter:	AppError		SecX�������
*/
function gFuncGetLastError(AppError)
{
	var ErrMsg = "�������:";

	ErrMsg += AppError + "\n";

	if (g_objRet.ErrOcxRet == -1000)
	{
		ErrMsg += "Ocx No Error";
	}
	else
	{
		ErrMsg += "  " + g_objRet.ErrOcxRet + "����ѯ�绰��58515511��\n";
		ErrMsg += "  " + g_objRet.ErrOcxPalace + "����ѯ�绰��58515511��\n";
	}
	//alert(ErrMsg);
}
/*
End Application Developmet Interface
**************************************************
*/

/*
**************************************************
Reserved Functions
*/

/*
Begin About Ocx Crypt Operation
*/

//Initial Session ////////////////////////////////
function gFuncInitialSession(strUniqueID, strPassword)
{
	return BJCASecClt.InitialSession(strUniqueID, strPassword);
}

//Clear Session ///////////////////////////////////
function gFuncClearSession()
{
	return BJCASecClt.ClearSession();
}

//Parse Cert /////////////////////////////////////
function gFuncGetCertDetail(strUniqueID, ItemNo)
{
	if (ItemNo > 23 || ItemNo < 0) {
		return g_objRet.ErrCertParse;
	}

	var ret;

	ret = BJCASecClt.GetCertDetail(strUniqueID, ItemNo);
	/*if (ret == "Read_Cert_Error!" || ret == "Invalid_Cert" || ret == "-1")
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "GetCertDetail";
		return g_objRet.ErrCertParse;
	} */

	return ret;
}

//Parse Cert Without Initial /////////////////////
function gFuncGetDetailNoInit(strUniqueID, ItemNo)
{
	if (ItemNo > 23 || ItemNo < 0) {
		return g_objRet.ErrCertParse;
	}

	var ret;

	ret = BJCASecClt.GetDetailNoInit(strUniqueID, ItemNo);
	if (ret == "Read_Cert_Error!" || ret == "Invalid_Cert" || ret == "-1")
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "GetCertDetail";
		//return g_objRet.ErrCertParse;
		return -1;
	}

	return ret;
}

//Encrypt Data ///////////////////////////////////
function gFuncEncrypt(s)
{

	var ret;
	if (s == "") {
		alert("ԭ���ݲ���Ϊ�գ�");
		return -1;
		//return g_objRet.ErrPara;
	}

	BJCASecClt.serverCertPEM = g_objSI.SvrCert;

	if (BJCASecClt.serverCertPEM == "")
	{
		alert("������֤�鲻��Ϊ�գ�");
		return -1;
		//return g_objRet.ErrSvrCert;
	}

	ret = BJCASecClt.EncryptPEM(s);

	if (ret.length < 150)
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "EncryptPEM";
		//return g_objRet.ErrEnc;
		return -1;
	}

	return ret;
}

//Dencrypt Data //////////////////////////////////
function gFuncDencrypt(s)
{
	var ret;
	if (s == "")
	{
		alert("Parameter is empty!");
		return g_objRet.ErrPara;
	}
	ret = BJCASecClt.DeEncryptPEM(s);

	if (ret == "SE_Initial_Error" || ret == "DeCode_Error" || ret == "Envelope_Error")
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "DeEncryptPEM";
		return g_objRet.ErrDenc;
	}

	return ret;
}

//Sign Data //////////////////////////////////////
function gFuncSign(s)
{
	var ret;
	if (s == "")
	{
		alert("ԭ���ݲ���Ϊ�գ�");
		//return g_objRet.ErrPara;
		return -1;
	}

	ret = BJCASecClt.Sign(s);

	if (ret == "SE_Initial_Error" || ret == "Sign_Error")
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "Sign";
		//return g_objRet.ErrSgn;
		return -1;
	}

	return ret;
}

//Verify Signed Data /////////////////////////////
function gFuncVerifySign(strSignedData, strUserCert, strOrigindata)
{
	var ret;
	ret = BJCASecClt.VerifySign(strSignedData, strUserCert, strOrigindata);

	if (ret == 1)
	{
		return g_objRet.Success;
	}
	else
	{
		g_objRet.ErrOcxRet = ret;
		g_objRet.ErrOcxPalace = "VerifySign";
		return g_objRet.ErrVeriSgn;
	}
}

//Get Cert Detail Form Medium ////////////////////
function gFuncGetCertDetailInfo(strCertPath, ItemNo, CertType)
{
	var ret;

	ret = BJCASecClt.GetCertDetailInfo(strCertPath, ItemNo, CertType);

	if (ret == "Get_Error")
	{
		g_objRet.ErrOcxRet = -1;
		g_objRet.ErrOcxPalace = "GetCertDetailInfo" + "\n";
		g_objRet.ErrOcxPalace += "Description:" + ret;
	}

	return ret;
}
/*
End About Ocx Crypt Operation
**************************************************
*/

//Sign Form Data /////////////////////////////////
function gFuncSignForm(objForm)
{
	var strToSign = "";
	var strNameList = "";

	var i, j;

	if (!objForm.SecX_SignData)
	{
		objForm.insertAdjacentHTML("BeforeEnd",g_objAH.HTMLItemString)
	}
	else
	{
		objForm.SecX_SignData.value = "";
		objForm.SecX_NameList.value = "";
		objForm.SecX_UserCert.value = "";
	}

	for (i = 0;i < objForm.length ; i++ )
	{
		for (j = 0; j < 10; j++)
		{
			if (objForm.item(i).type == g_objF.ReIType[j] && (objForm.item(i).name.indexOf("SecX") < 0))
			{
				strToSign += objForm.item(i).value;
				strNameList += objForm.item(i).name + ";";
			}
		}
	}

	objForm.SecX_SignData.value = gFuncSign(strToSign);
	objForm.SecX_NameList.value = strNameList;
	objForm.SecX_UserCert.value = gFuncGetDetailNoInit(g_objSI.Container, 0);
	//alert("ǩ�����ݣ�" + objForm.SecX_SignData.value);
	//alert("�û�֤�飺" + objForm.SecX_UserCert.value);

	return true;
}



//Get Real URL ///////////////////////////////////
function gFuncGetRealURL(strAbsURL, strRelURL) {

	var strRealURL = "";
	var strWebPath = "";
	var strTemp = strRelURL;

	strTemp = strRelURL.toUpperCase();

	//The strRelURL aleady is AbsURL ...
	if (strTemp.indexOf("HTTP://") != -1) {
		strRealURL = strRelURL;
	}
	else {
		if (strRelURL.substr(0,1) != "/") {
			//The first of strRelURL is not '/' ...
			strWebPath = strAbsURL.substr(0, strAbsURL.lastIndexOf("/") + 1);
			strRealURL = strWebPath + strRelURL;
		}
		else {
			//The first of strRelURL is '/' ...
			strTemp = strAbsURL;
			strTemp = strTemp.substr(strTemp.indexOf("//") + 2, strTemp.length);
			strTemp = strTemp.substr(strTemp.indexOf("/") + 1, strTemp.length);
			strAbsURL = strAbsURL.substr(0, strAbsURL.indexOf(strTemp) - 1);
			strRealURL = strAbsURL + strRelURL;
		}
	}

	return strRealURL;
}

/*
������
	�޸�˽Կ����
������
	UniqueID��֤��Ψһ��ʾ
	strOldPWD��������
	strNewPWD��������
	strConfirmPWD��ȷ������
����ֵ��
	0���ɹ�

*/
function gFuncChangePWD(UniqueID, strOldPWD, strNewPWD, strConfirmPWD) {
	if ( strOldPWD=="") {
		alert("������ɿ���!");
		return false;
	}
	if ( strNewPWD=="") {
		alert("�������¿���!");
		return false;
	}

	if ( strConfirmPWD=="") {
		alert("��ȷ�Ͽ���!");
		return false;
	}
	if (strNewPWD != strConfirmPWD) {
		alert("������������벻ͬ!");
		return false;
	}

	if (UniqueID == "") {
		alert("UniqueID �գ������µ�¼!");
		return false;
	}

	var ret;
	ret = BJCASecClt.ChangePWD(UniqueID, strOldPWD, strNewPWD);

	if (ret != 0) {
		alert("�޸��������");
		return false
	}
	else {
	       alert("�޸�����ɹ���");
		return 1;
	}


}


/*
Function:	gFuncLogin
Parameter:	strFormName	����ID����
			strUniqueID	֤���UniqueID
			strPassword	˽Կ��������
*/
function gRFuncLogin(strUniqueID, strPassword)
{
	gFuncResetGRet();
	var ret;
    var strServerRan = g_objSI.SvrOriData;
	var strServerSignedData = g_objSI.SvrSignData;
	var strServerCert = g_objSI.SvrCert;
			
	var sSignedData = "";


	if (strPassword == "")
	{
		alert("�����Ϊ��!")
		return false;
	}

	if (strUniqueID == "")
	{
		gFuncGetLastError(g_objRet.ErrPara);
		return false;
	}

	ret = gFuncGetCertDetail(strUniqueID, 23);

	var valValue = alertValidDay(ret);

	if(!valValue){
		return false;
	}

	ret = gFuncInitialSession(strUniqueID, strPassword);
	switch(ret)
	{
		case -1:
			alert("UniqueID �������");
			gFuncGetLastError(g_objRet.ErrPara);
			return false;
			break;
		case -2:
			alert("��ȡ֤��·������");
			gFuncGetLastError(g_objRet.ErrCertPath);
			return false;
			break;
		case -3:
			alert("��ȡ֤����·������");
			gFuncGetLastError(g_objRet.ErrCertChainPath);
			return false;
			break;
		case -4:
			alert("˽Կ�豸����");
			gFuncGetLastError(g_objRet.ErrCertKeyPath);
			return false;
			break;
		case -5:
			alert("�������");
			gFuncGetLastError(g_objRet.ErrCertKeyPWD);
			return false;
			break;
		case -6:
			alert("֤�����豸����");
			gFuncGetLastError(g_objRet.ErrCertChain);
			return false;
			break;
		case -7:
			alert("ϵͳ�쳣����");
			gFuncGetLastError(g_objRet.ErrUnknown);
			return false;
			break;
		case 0:
			//Initial Ocx Success ////////////////
	}

	return true;
}

/*
Function:	checkPassWord
Parameter:	strPassword	˽Կ��������
*/
function checkPassWord(strPassword)
{
	var pattern = /^#[0-9a-f]{8}$/;
	if(pattern.exec(strPassword)==null)
	{
		alert("�������\n\n��ע�⣬֤���ʼ����ΪСд��ĸa-f������0-9��ɵ�8λ���ַ�����");
		return false;
	}else
	{
		return true;  
	}
}

/*
End Reserved Functions
**************************************************
*/
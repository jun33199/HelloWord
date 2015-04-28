package com.lscdz.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import yangjian.frame.sso.UserData;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;
import yangjian.frame.util.ValConstant;
import yangjian.frame.vo.qxgl_client;

/**
 * �ͻ��˽������� Servlet �࣬���ڽ��տͻ��˵����� ����ͻ��˳���¼�����еı�������
 * 
 * @author jasper 2013/03/08
 */
public class ClientActionServlet extends HttpServlet {
	private static final long serialVersionUID = 20130701001L;
	public static final String CONTENT_TYPE_XML = "application/x-www-form-urlencoded";
	public static final String CONTENT_TYPE_UPLOAD_FILE = "multipart/form-data";
	public static final String CONTENT_TYPE_DOWNLOAD_FILE = "application/download-file";
	public static final String CONTENT_TYPE_APPLICATION_STREAM = "application/octet-stream";

	// ��ʱ�ļ�Ŀ¼�����ڳ�ʼ����ʱ��������ļ��ж�ȡ
	private static String _pathTempFile = null;
	public static String getTempFilePath() {
		return _pathTempFile;
	}
	
	// debug ģʽ
	private static boolean _isDebug = true;
	public static boolean isDebug() {
		return _isDebug;
	}

	// �Ƿ����ò���Ȩ�޼��
	private static boolean _checkPrivilege = false;
	
	/**
	 * ��������ʱ�����д˷���,��ʼ��Message����
	 */
	public void init(ServletConfig config) throws ServletException {
		// ��ȡ�����ļ��еĸ�Ŀ¼
		_pathTempFile = ResourceManager.getTokenByName("PATH_TEMP_FILE");
		if (_pathTempFile.equals("")) {
			// δ���ã�ʹ�õ�ǰ�û���Ŀ¼
			_pathTempFile = System.getProperty("user.home");
		}
		
		// ��ȡ�����ļ��е� debug ģʽ
		_isDebug = (ResourceManager.getTokenByName("DEBUG_MODE")
				.equalsIgnoreCase("false") ? false : true);
		
		// ��ȡ�����ļ��еĲ���Ȩ�޼���ʶ
		_checkPrivilege = (ResourceManager.getTokenByName("CHECK_PRIVILEGE")
				.equalsIgnoreCase("false") ? false : true);
		
		// ��ʼ�� Servlet ��־�ļ�
		UtilLogger.initLogWriter(_pathTempFile + System.getProperty("file.separator") + "ClientActionServlet.log");
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * ����ͻ�����������ֱ�ӶԱ��Ľ��н���������ɹ����� ��ȡ�����е� RequestAction������ ClientMessage ���󣬸�ֵ
	 * type �� action Ȼ�� Document ����ֵ������ type �� action ���ò�ͬ�� Handler ���к�������
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ���û�е�¼ web �����������һ�� UserData ʵ�������� Session���Ա��������ʹ�����е� userMap
        HttpSession session = request.getSession(true);
        Object userData = session.getAttribute(ValConstant.SESSION_USERDATA);
        if (userData == null) {
        	session.setAttribute(ValConstant.SESSION_USERDATA, new UserData());
        }		
		
		// ���ݴ���� ContentType ���в�ͬ�Ĵ���
		String contentType = request.getContentType();
		if (CONTENT_TYPE_XML.equals(contentType)) {
			// δ���� XML ����
			doClientXml(request, response);
		} else if (contentType.startsWith(CONTENT_TYPE_UPLOAD_FILE)) {
			// �ļ��ϴ�
			doDataSubmitClientFileUpload(request, response);
		} else if (contentType.startsWith(CONTENT_TYPE_DOWNLOAD_FILE)) {
			// �ļ�����
			doClientFileDownload(request, response);
		} else {
			// ��֧�ֵ����ݸ�ʽ
			PrintWriter out = response.getWriter();
			out.write("Content Type: " + contentType + " is not supported!");
			out.flush();
			out.close();
		}
	}

	/**
	 * ����չʾ�ͻ��������ļ�����
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	private void doClientFileDownload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		/*
		// ��ȡ�����ļ� action
		String action = request.getParameter("Action");
		String errMsg = null;
		if (action != null && action.length() > 0) {
			// ��ȡ�����ļ����ļ���
			String filename = dataSubmitFileMap.get(action);
			if (filename != null && (new File(filename)).exists()) {
				// �����ļ�����
				writeFileData(response, filename);
			} else {
				errMsg = "No such file! " + filename;
			}
		} else {
			errMsg = "Please set action!";
		}

		// �����쳣��Ϣ
		sendBackXml(response, errMsg);
		*/
	}

	/**
	 * �������ݲɼ��ͻ����ϴ��ļ����󣬱����ļ��������ļ�ID
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private void doDataSubmitClientFileUpload(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// ��ȡ�ϴ��ļ�����
		String contentType = request.getContentType();
		String fileType = contentType.substring(contentType.indexOf("___") + 3);
		String xmlResult = null;

		if (fileType == null || fileType.trim().length() == 0) {
			xmlResult = makeupReturnXmlText(false, "Please set FileType!");
		} else {
			try {
				DiskFileUpload dfu = new DiskFileUpload();
				dfu.setSizeMax(1024 * 1024 * 100); // ��󱣴�100M���ļ�
				dfu.setSizeThreshold(1024 * 1024 * 20); // �ڴ������20M

				List fileList = dfu.parseRequest(request);
				Iterator it = fileList.iterator();
				for (; it.hasNext();) {
					FileItem item = (FileItem) it.next();
					if (!item.isFormField()) {
						// �����һ���ļ���Լ���ͻ��˲�����������ʽ�ϴ��ļ���
						String fileName = fileType + "_"
								+ System.currentTimeMillis();
						// д�ļ�
						String fullFileName = _pathTempFile + "/" + fileName;
						item.write(new File(fullFileName));
						break;
					}
				}
				xmlResult = makeupReturnXmlText(true, "");
			} catch (Exception e) {
				e.printStackTrace();
				xmlResult = makeupReturnXmlText(false,
						"Failed to save upload file!");
			}
		}
		// ���ؽ�� XML
		PrintWriter out = response.getWriter();
		out.write(xmlResult);
		out.flush();
		out.close();
	}

	/**
	 * ���췵����Ϣ����
	 * 
	 * @param isSuccess
	 * @param contents
	 * @return
	 */
	private String makeupReturnXmlText(boolean isSuccess, String contents) {
		StringBuffer strBuf = new StringBuffer();
		// ƴ�ӳɹ�����
		if (isSuccess) {
			strBuf.append(UtilConstants.XML_ROOT_HEAD)
					.append(UtilConstants.XML_RETURN_SUCCESS).append(contents)
					.append(UtilConstants.XML_ROOT_TAIL);
		}// ƴ��ʧ�ܱ���
		else {
			strBuf.append(UtilConstants.XML_ROOT_HEAD)
					.append(UtilConstants.XML_RETURN_FAILED)
					.append(UtilConstants.XML_ERR_MSG_HEAD).append(contents)
					.append(UtilConstants.XML_ERR_MSG_TAIL)
					.append("<BODY RtnBizCode=\"SystemFail\" RtnBizMessage=\""+contents+"\"/>")
					.append(UtilConstants.XML_ROOT_TAIL);
		}
		return strBuf.toString();
	}

	/**
	 * ���� XML ���ķ�ʽ������
	 * 
	 * @param request
	 * @param response
	 */
	private void doClientXml(HttpServletRequest request,
			HttpServletResponse response) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String msg = null;
		try {
			// �� request �е����ݶ�ȡ�� ByteArrayOutputStream
			int ch = request.getInputStream().read();
			while (ch != -1) {
				baos.write(ch);
				ch = request.getInputStream().read();
			}
			// ��Ӧ�ͻ�������
			responseClientXml(request, response, baos);
			return;
		} catch (FrameException ex) {
			msg = ex.getMessage();
		} catch (Exception e) {
			try {
				UtilLogger.logErr(e, baos.toString("UTF-8"));
			} catch (UnsupportedEncodingException e1) {
				UtilLogger.logErr(e, baos.toString());
			}
			msg = "XML�ļ���ʽ�����ϵͳ�쳣��";
		}

		String xmlText = makeupReturnXmlText(false, msg);
		// ���ʹ�����Ϣ
		sendBackXml(response, xmlText);
	}

	/**
	 * ��Ӧ�ͻ���������
	 * 
	 * @param request
	 * @param response
	 * @param bais
	 * @throws Exception
	 */
	private void responseClientXml(HttpServletRequest request,
			HttpServletResponse response, ByteArrayOutputStream baos)
			throws Exception {
		// debug ģʽ�����������
		if (_isDebug) {
			System.out.println("ԭʼ�����ģ�");
			System.out.println(baos.toString("UTF-8"));
		}

		// ���� ClientMessage
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ClientMessage clientMsg = getClientMessage(bais, request);
		clientMsg.setXmlText(baos.toString("UTF-8"));

		// debug ģʽ����ʾ��ǰ�û�id���Լ���ʽ���� XML
		if (_isDebug) {
			System.out.println("��ǰ�û�: "
					+ (clientMsg.getUserData() == null ? "" : clientMsg.getYhid()));
			System.out.println("��ʽ����������ģ�");
			System.out.println(FetchXmlValueTool.formatXmlText(clientMsg.getXmlText()));
		}
		
		// ��鵱ǰ�û��Ƿ�ӵ������Ĳ���Ȩ��
		if (!checkPrivilege(clientMsg)) {
			// û��Ȩ�ޣ����췵��XML
			StringBuffer strBuf = new StringBuffer();
			strBuf.append(UtilConstants.XML_ROOT_HEAD).append(UtilConstants.XML_RETURN_FAILED)
				.append(UtilConstants.XML_ERR_MSG_HEAD).append("��û�з��ʸ�ҵ��Ĳ���Ȩ��")
				.append("<BODY RtnBizCode=\"NoPermission\" RtnBizMessage=\"��û�з��ʸ�ҵ��Ĳ���Ȩ��\"/>")
				.append(UtilConstants.XML_ERR_MSG_TAIL).append(UtilConstants.XML_ROOT_TAIL);
			sendBackXml(response, strBuf.toString());
		} else {
			// �ַ�����
			String xmlResult = dispatchRequestAction(clientMsg);

			// debug ģʽ��������ر���
			if (_isDebug) {
				System.out.println("ԭʼ���ر��ģ�");
				System.out.println(xmlResult);
				System.out.println("��ʽ����ķ��ر��ģ�");
				System.out.println(FetchXmlValueTool.formatXmlText(xmlResult));
			}
			// ���ͽ��
			sendBackXml(response, xmlResult);
		}
	}

	/**
	 * ��������Ƿ����ָ���Ĳ���Ȩ��
	 * @param clientMsg
	 * @return
	 */
	private boolean checkPrivilege(ClientMessage clientMsg) {
		// �Ƿ���Ȩ��
		if (!_checkPrivilege) return true;
		
		// ��������Ϊ����Ҫ��¼�Ĺ��ܣ�����Ƿ��¼
		if (clientMsg.getUserData() == null) return false;
		
		// ��ȡ��ǰ�û� UserData �е����пͻ���Ȩ��
		List<qxgl_client> listQx = clientMsg.getUserData().getBusinessPrivilege();
		// ѭ��ƥ�������Ȩ��
		for (qxgl_client voQx : listQx) {
			if (voQx.getBussinessKey().equals(clientMsg.getType()) &&
					voQx.getAction().equals(clientMsg.getAction())) return true;
		}
		
		return false;
	}

	/**
	 * �� ByteArrayInputStream �й��� ClientMessage
	 * 
	 * @param doc
	 * @param request
	 * @return
	 * @throws FrameException
	 */
	private ClientMessage getClientMessage(ByteArrayInputStream bais,
			HttpServletRequest request) throws Exception {
		// ����������������Ķ���
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;

		// ��������
		docBuilder = dbf.newDocumentBuilder();
		Document doc = docBuilder.parse(bais);

		// ��ȡ RequestAction
		NodeList nlRequest = doc.getElementsByTagName("RequestAction");
		if (nlRequest.getLength() == 0)
			throw new FrameException("Error: Failed to find <RequestAction>.");

		// ���� ClientMessage
		Element requestInfo = (Element) nlRequest.item(0);
		ClientMessage clientMsg = new ClientMessage();
		clientMsg.setType(requestInfo.getAttribute("Type"));
		clientMsg.setAction(requestInfo.getAttribute("Action"));
		clientMsg.setPrivilege(requestInfo.getAttribute("Privilege"));

		clientMsg.setDoc(doc);
		clientMsg.setUserData((UserData) request.getSession(true).getAttribute(
				ValConstant.SESSION_USERDATA));

		return clientMsg;
	}

	/**
	 * �ַ������ Action
	 * 
	 * @param clientMsg
	 * @return
	 */
	private String dispatchRequestAction(ClientMessage clientMsg) {
		IActionHandler handler = null;
		// ��������ʵ������Ӧ����
		String className = clientMsg.getType();
		if (className.indexOf(".") == -1) {
			// ��ʱ�汾��Ϊ�˼����ϰ汾�����ϰ汾����û�м�ǰ׺
			className = "com.lscdz.hlwdsj.application.presentation.handler." + className;
		}
		try {
			@SuppressWarnings("rawtypes")
			Class handlerClass = Class.forName(className);
			handler = (IActionHandler) handlerClass.newInstance();
		} catch (Exception e) {
			UtilLogger.logErr(e, "Can not create instance of " + className);
		}
		// ���ýӿڷ�������ҵ���߼�
		if (handler != null) {
			try {
				// ��ȡҵ���������ٷ�װ XML ͷ���������ݣ�����
				StringBuffer strBufContent = handler.processMsg(clientMsg);
				return makeupReturnXmlText(true, strBufContent.toString());
			} catch (FrameException e) {
				// �����쳣�������쳣����XML
				return makeupReturnXmlText(false, e.getMessage());
			}
		} else {
			// ActionHandlerʵ�����쳣������XML
			return makeupReturnXmlText(false, "������Դ���ʴ���");
		}
	}

	/**
	 * ��ͻ���д xml ���
	 * 
	 * @param response
	 * @param xmlText
	 */
	private void sendBackXml(HttpServletResponse response, String xmlText) {
		response.setContentType(CONTENT_TYPE_XML);
		try {
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// out.write(Tools.toISO8859_1(xmlText.toString()));
			out.write(xmlText.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			UtilLogger.logErr(e, "Error��ClientActionServlet.sendBackXml();");
		}
	}

	/**
	 * �����ļ�����
	 * 
	 * @param agentMsg
	 */
	/*
	private void writeFileData(HttpServletResponse response, String filename) {
		// �����ļ�����
		response.setContentType(CONTENT_TYPE_APPLICATION_STREAM);
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		try {

			bis = new BufferedInputStream(new FileInputStream(filename));
			sos = response.getOutputStream();

			// ѭ����д
			int bytesRead = 0;
			final int len = 16384;
			byte[] buffer = new byte[len];
			bytesRead = bis.read(buffer, 0, len);

			// д����
			while (bytesRead != -1) {
				sos.write(buffer, 0, bytesRead);
				bytesRead = bis.read(buffer, 0, len);
			}
		} catch (Exception ex) {
			UtilLogger.logErr("Send file data failed.");
		} finally {
			if (sos != null) {
				try {
					sos.flush();
					sos.close();
				} catch (Exception se) {
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (Exception xe) {
				}
			}
		}
	}
	*/
}

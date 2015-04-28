package com.ttsoft.bjtax.shenbao.nsrjcxxhd.web;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.syax.common.xml.util.XMLBuildUtil;
import com.ttsoft.bjtax.dj.DjConstantKey;
import com.ttsoft.bjtax.dj.model.dm.ZJLX;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.NsrjcxxhdConstant;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.common.XmlUtil;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Add;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Djjbsj;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Dkdj;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Fzjg;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Qyry;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Swdl;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Tzf;
import com.ttsoft.bjtax.shenbao.nsrjcxxhd.model.Zjg;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * ��˰�˻�����Ϣ�˶�
 */
public class NsrjcxxhdAction extends DcBaseAction {

	/**
	 * ��ʼ��
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doInit(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		this.doInitData(request, response);
		return "init";
	}
	
	/**
	 * �����޸�
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		Map data = new HashMap();
		String jsjdm = request.getRemoteUser();
		String strXMLData = request.getParameter("strXMLData");
		
		Map xmlMap = XmlUtil.parseXmlToMap(strXMLData);
		
		Djjbsj djjbsj = new Djjbsj();
		djjbsj.setJsjdm(jsjdm);
		djjbsj.setZcdzyb((String) xmlMap.get("zcdzyb"));
		djjbsj.setZcdzlxdh((String) xmlMap.get("zcdzlxdh"));
		djjbsj.setJydz((String) xmlMap.get("jydz"));
		djjbsj.setJydzyb((String) xmlMap.get("jydzyb"));
		djjbsj.setJydzlxdm((String) xmlMap.get("jydzlxdm"));
		data.put("djjbsj", djjbsj);
		
		Qyry cwfzr = new Qyry();
		cwfzr.setJsjdm(jsjdm);
		cwfzr.setXm((String) xmlMap.get("cwfzrxm"));
		cwfzr.setZjlxdm((String) xmlMap.get("cwfzrzjlxdm"));
		cwfzr.setZjhm((String) xmlMap.get("cwfzrzjhm"));
		cwfzr.setGddh((String) xmlMap.get("cwfzrgddh"));
		cwfzr.setYddh((String) xmlMap.get("cwfzryddh"));
		cwfzr.setDzyx((String) xmlMap.get("cwfzrdzyx"));
		data.put("cwfzr", cwfzr);
		
		Qyry bsr = new Qyry();
		bsr.setJsjdm(jsjdm);
		bsr.setXm((String) xmlMap.get("bsrxm"));
		bsr.setZjlxdm((String) xmlMap.get("bsrzjlxdm"));
		bsr.setZjhm((String) xmlMap.get("bsrzjhm"));
		bsr.setGddh((String) xmlMap.get("bsrgddh"));
		bsr.setYddh((String) xmlMap.get("bsryddh"));
		bsr.setDzyx((String) xmlMap.get("bsrdzyx"));
		data.put("bsr", bsr);
		
		Qyry fr = new Qyry();
		fr.setJsjdm(jsjdm);
		fr.setGddh((String) xmlMap.get("frgddh"));
		fr.setYddh((String) xmlMap.get("fryddh"));
		fr.setDzyx((String) xmlMap.get("frdzyx"));
		data.put("fr", fr);
		
		Swdl swdl = new Swdl();
		swdl.setJsjdm(jsjdm);
		swdl.setSwjgzzjgdm(jsjdm.substring(0, 4));
		swdl.setMc((String) xmlMap.get("swdlmc"));
		swdl.setSwdjzh((String) xmlMap.get("swdlswdjzh"));
		swdl.setDzyx((String) xmlMap.get("swdldzyx"));
		swdl.setGddh((String) xmlMap.get("swdlgddh"));
		data.put("swdl", swdl);
		
		VOPackage vo = new VOPackage();
		vo.setAction(NsrjcxxhdConstant.ACTION_SAVE);
		vo.setProcessor(NsrjcxxhdConstant.PROCESSOR_NSRJCXXHD);
		vo.setData(data);
		String msg = (String) ShenbaoProxy.getInstance().process(vo);
		
		try {
			response.setContentType("text/xml;charset=GBK");
			response.setHeader("Cache-Control", "no-cache");
			BufferedWriter bw = new BufferedWriter(response.getWriter());
			bw.write("<nsrjcxxhd><save>" + XMLBuildUtil.appendStringElement("msg", msg) + "</save></nsrjcxxhd>");
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return DcActionConfig.NO_FORWARD;
	}

	/**
	 * ��ʼ������ҳ������
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public void doInitData(HttpServletRequest request, HttpServletResponse response) throws BaseException {
		try {
			String jsjdm = request.getRemoteUser();// "06099780";//"08000620";  06099780
			VOPackage vo = new VOPackage();
			vo.setAction(NsrjcxxhdConstant.ACTION_QUERY_INIT);
			vo.setProcessor(NsrjcxxhdConstant.PROCESSOR_NSRJCXXHD);
			vo.setData(jsjdm);
			Map data = (Map) ShenbaoProxy.getInstance().process(vo);
			// ��������
			Djjbsj djjbsj = (Djjbsj) data.get("djjbsj");

			String djjbsjXml = "";
			if (djjbsj != null) {
				djjbsjXml = djjbsj.toXML();
			}

			// Ͷ�ʷ�
			List tzfList = (List) data.get("tzfList");
			Tzf tzf = null;
			StringBuffer tzfSb = new StringBuffer();
			tzfSb.append("<tzfs>");
			int tzfListSize = tzfList.size();
			for (int i = 0; i < tzfListSize; i++) {
				tzf = (Tzf) tzfList.get(i);
				tzfSb.append(tzf.toXML());
			}
			tzfSb.append("</tzfs>");

			// ��֧����
			List fzjgList = (List) data.get("fzjgList");
			Fzjg fzjg = null;
			StringBuffer fzjgSb = new StringBuffer();
			fzjgSb.append("<fzjgs>");
			int fzjgListSize = fzjgList.size();
			for (int i = 0; i < fzjgListSize; i++) {
				fzjg = (Fzjg) fzjgList.get(i);
				fzjgSb.append(fzjg.toXML());
			}
			fzjgSb.append("</fzjgs>");

			// �ܻ���
			Zjg zjg = (Zjg) data.get("zjg");
			String zjgXml = "";
			if (zjg != null) {
				zjgXml = zjg.toXML();
			}

			// ADD
			Add add = (Add) data.get("add");
			String addXml = "";
			if (add != null) {
				addXml = add.toXML();
			}

			// ��ҵ��Ա
			List qyryList = (List) data.get("qyryList");
			String qyryXml = "";
			Qyry qyry = null;
			int qyryListSize = qyryList.size();
			for (int i = 0; i < qyryListSize; i++) {
				qyry = (Qyry) qyryList.get(i);
				qyryXml += qyry.toXML();
			}

			// ֤�����ʹ����
			List zjlxList = CodeTableUtil.getCodeTableList(CodeTableKey.ZJLX);
			int zjlxListSize = zjlxList.size();
			ZJLX zjlx = null;
			StringBuffer zjlxListSb = new StringBuffer();
			zjlxListSb.append("<zjlxs>");
			for(int i=0; i<zjlxListSize; i++) {
				zjlx = (ZJLX) zjlxList.get(i);
				zjlxListSb.append("<zjlx>");
				zjlxListSb.append(XMLBuildUtil.appendStringElement("zjlxdm", zjlx.getZjlxdm()));
				zjlxListSb.append(XMLBuildUtil.appendStringElement("zjlxmc", zjlx.getZjlxmc()));
				zjlxListSb.append("</zjlx>");
			}
			zjlxListSb.append("</zjlxs>");
			// ˰�����
			Swdl swdl = (Swdl) data.get("swdl");
			String swdlXml = "";
			if (swdl != null) {
				swdlXml = swdl.toXml();
			}

			// ���۴���
			List dkdjList = (List) data.get("dkdjList");
			int dkdjListSize = dkdjList.size();
			Dkdj dkdj = null;
			StringBuffer dkdjSb = new StringBuffer();
			dkdjSb.append("<dkdjs>");
			for (int i = 0; i < dkdjListSize; i++) {
				dkdj = (Dkdj) dkdjList.get(i);
				dkdjSb.append(dkdj.toXml());
			}
			dkdjSb.append("</dkdjs>");

			String xml = "<nsrjcxxhd>"
					+ djjbsjXml + tzfSb.toString() + fzjgSb.toString()
					+ zjgXml + addXml + qyryXml + swdlXml + dkdjSb + zjlxListSb
					+ "</nsrjcxxhd>";
			xml = xml.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll("'", "").replaceAll("\"", "");
			request.setAttribute("xml", xml);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}

	/**
	 * �������м����
	 */
	protected String beforePerform(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(DjConstantKey.HEAD_POSITION,
				"�ۺϷ��������Ϣϵͳ&gt;˰��Ǽ�&gt;�˶�˰��Ǽ���Ϣ");
		request.setAttribute(DjConstantKey.HEAD_HELPURL,
				"wsdj/bgdj/wsbgswdj/whlxxx-000.htm");
		request.setAttribute(DjConstantKey.HEAD_TITLE,
		"����ά����ϵ��Ϣ-������Ϣ�˶�");
		return "";
	}
}
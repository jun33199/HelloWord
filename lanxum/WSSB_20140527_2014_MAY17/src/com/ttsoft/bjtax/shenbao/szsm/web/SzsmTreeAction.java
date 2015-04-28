package com.ttsoft.bjtax.shenbao.szsm.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.syax.common.web.action.DcBaseAction;
import com.syax.common.web.util.DcActionConfig;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.szsm.xmlvo.SzsmListVO;
import com.ttsoft.bjtax.shenbao.szsm.xmlvo.SzsmVO;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;

/**
 * 税种税目树action
 * 
 * @author zhaob
 * @version 1.0
 */
public class SzsmTreeAction extends DcBaseAction {

	public SzsmTreeAction() {
	}

	protected int getActionID() {
		return SbzlAccess.SZSM;
	}

	/**
	 * 
	 * 查询税种
	 * 
	 */
	public String doLoadRootSZ(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {

		boolean needFilter = false;// 是否需要过滤
		String sklxdm = request.getParameter("sklxdm");
		if (request.getParameter("local") != null) {
			String local = request.getParameter("local").toString();
			if (local.equals("szsm000.jsp")) {
				needFilter = true;
			} else if (local.equals("favorite.jsp")) {
				needFilter = false;
			} else {
				System.out.println("来自不可辨别页面的请求!不做任何反映");
				return DcActionConfig.NO_FORWARD;
			}
		} else {
			System.out.println("非法请求!不做任何反映");
			return DcActionConfig.NO_FORWARD;
		}
		// 检查session
		UserData ud = (UserData) this.getUserData(request);
		SzsmInfoHelper.putSzsmInfoIntoSession(request, ud, sklxdm);
		SzsmForest szsmForest = null;
		if (!needFilter) {
			szsmForest = (SzsmForest) request.getSession().getAttribute(
					"virginSzsmForest");
		} else {
			szsmForest = (SzsmForest) request.getSession().getAttribute(
					"filteredSzsmForest");
		}
		List rootList = szsmForest.getRootsList();
		int size = rootList.size();
		response.setContentType("application/xml;charset=UTF-8");
		// System.out.println(size);

		SzsmVO szsmvo = new SzsmVO();
		String szsmdm[] = new String[size];
		String szsmmc[] = new String[size];
		String ccbs[] = new String[size];
		String fjddm[] = new String[size];
		String isSelect[] = new String[size];
		String editable[] = new String[size];
		for (int i = 0; i < size; i++) {

			Szsm tmpSzsm = (Szsm) rootList.get(i);
			// System.out.println(tmpSzsm.getSzsmdm()+" "+tmpSzsm.getSzsmmc());
			szsmdm[i] = tmpSzsm.getSzsmdm();
			szsmmc[i] = tmpSzsm.getSzsmmc();
			ccbs[i] = tmpSzsm.getCcbs();
			fjddm[i] = tmpSzsm.getFjddm();
			isSelect[i] = "0";
			editable[i] = "1";
		}
		szsmvo.setSzsmdm(szsmdm);
		szsmvo.setSzsmmc(szsmmc);
		szsmvo.setCcbs(ccbs);
		szsmvo.setFjddm(fjddm);
		szsmvo.setIsSelected(isSelect);
		szsmvo.setEditable(editable);
		SzsmListVO szsmlistvo = new SzsmListVO();
		szsmlistvo.setSzsm(szsmvo);
		String xmlStr = szsmlistvo.toXML();
		// System.out.println(xmlStr);
		this.print(response, xmlStr);
		return DcActionConfig.NO_FORWARD;
	}

	/**
	 * 
	 * 检查税分枝
	 */
	public String doLoadRamoseSZSM(HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		String request_szsmdm = request.getParameter("szsmdm");
		String sklxdm = request.getParameter("sklxdm");
		String favorite_selected = "";
		String favorite_editable = "";
		if (request_szsmdm == null) {
			System.out.println("税种税目代码为空不做任何反映");
			return DcActionConfig.NO_FORWARD;
		}
		boolean needFilter = false;// 是否需要过滤

		if (request.getParameter("local") != null) {
			String local = request.getParameter("local").toString();
			if (local.equals("szsm000.jsp")) {
				favorite_selected = "1";
				favorite_editable = "0";
				needFilter = true;
			} else if (local.equals("favorite.jsp")) {
				favorite_selected = "1";
				favorite_editable = "1";
				needFilter = false;
			} else {
				System.out.println("来自不可辨别页面的请求!不做任何反映");
				return DcActionConfig.NO_FORWARD;
			}
		} else {
			System.out.println("非法请求!不做任何反映");
			return DcActionConfig.NO_FORWARD;
		}
		// 检查session
		UserData ud = (UserData) this.getUserData(request);
		SzsmInfoHelper.putSzsmInfoIntoSession(request, ud, sklxdm);

		SzsmForest szsmForest = null;
		if (!needFilter) {
			szsmForest = (SzsmForest) request.getSession().getAttribute(
					"virginSzsmForest");
		} else {
			szsmForest = (SzsmForest) request.getSession().getAttribute(
					"filteredSzsmForest");
		}
		Map favoriteSzsmMap = (Map) request.getSession(false).getAttribute(
				"favoriteSzsmMap");

		Map szsmForestMap = szsmForest.getSzsmForest();

		/*
		 * Set s = szsmForestMap.keySet(); Iterator ii = s.iterator();
		 * while(ii.hasNext()){ String tmpStr = ii.next().toString();
		 * szsmForestMap.get(tmpStr); System.out.println(); }
		 */
		// System.out.println("请求代码为"+request_szsmdm+"
		// 长度为"+request_szsmdm.length()+" 是否等于02"+request_szsmdm.equals("02"));
		// System.out.println("找到02开头的集合内容大小为"+((List)szsmForestMap.get("02")).size());
		List szsmList = (List) szsmForestMap.get(request_szsmdm);
		if (szsmList == null) {
			System.out.println("没有找到" + request_szsmdm + "的子节点或者税目");
			return DcActionConfig.NO_FORWARD;
		}
		int size = szsmList.size();
		// System.out.println("找到的集合里有"+size+"个元素");

		SzsmVO szsmvo = new SzsmVO();
		String szsmdm[] = new String[size];
		String szsmmc[] = new String[size];
		String ccbs[] = new String[size];
		String fjddm[] = new String[size];
		String isSelect[] = new String[size];
		String editable[] = new String[size];

		for (int i = 0; i < szsmList.size(); i++) {
			Szsm tmpSzsm = (Szsm) szsmList.get(i);
			// System.out.println("第"+i+"个元素为"+tmpSzsm.getSzsmdm()+"*************"+tmpSzsm.getSzsmmc());
			szsmdm[i] = tmpSzsm.getSzsmdm();
			szsmmc[i] = tmpSzsm.getSzsmmc();
			ccbs[i] = tmpSzsm.getCcbs();
			fjddm[i] = tmpSzsm.getFjddm();
			isSelect[i] = "0";
			editable[i] = "1";
			if (favorite_selected.equals("1")) {
				if (tmpSzsm.getCcbs().equals("2")) {
					if (favoriteSzsmMap.get(tmpSzsm.getSzsmdm()) != null) {
						isSelect[i] = favorite_selected + "";
						editable[i] = favorite_editable + "";
					}
				}
			}
		}
		szsmvo.setSzsmdm(szsmdm);
		szsmvo.setSzsmmc(szsmmc);
		szsmvo.setCcbs(ccbs);
		szsmvo.setFjddm(fjddm);
		szsmvo.setIsSelected(isSelect);
		szsmvo.setEditable(editable);
		SzsmListVO szsmlistvo = new SzsmListVO();
		szsmlistvo.setSzsm(szsmvo);
		String xmlStr = szsmlistvo.toXML();
		// System.out.println(xmlStr);
		response.setContentType("application/xml;charset=UTF-8");
		this.print(response, xmlStr);
		return DcActionConfig.NO_FORWARD;
	}

	private void print(HttpServletResponse response, String xmlStr) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(xmlStr);

		} catch (IOException e) {
			// TODO 自动生成 catch 块
			e.printStackTrace();
		}
	}

	protected String beforePerform(HttpServletRequest request,
			HttpServletResponse response) {
		// 检查权限

		return null;
	}
}

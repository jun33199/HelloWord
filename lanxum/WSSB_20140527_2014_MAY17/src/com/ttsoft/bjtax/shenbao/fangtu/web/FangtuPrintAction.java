package com.ttsoft.bjtax.shenbao.fangtu.web;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.fangtu.ConstantFangtu;
import com.ttsoft.bjtax.shenbao.fangtu.LabelValueBean;
import com.ttsoft.bjtax.shenbao.fangtu.Utils;
import com.ttsoft.bjtax.shenbao.fangtu.form.FangtuPrintBVO;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.FwZiyongTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdChuzuTotal;
import com.ttsoft.bjtax.shenbao.fangtu.total.TdZiyongTotal;
import com.ttsoft.bjtax.shenbao.model.domain.CZFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.CZUTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYFWJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDBGXX;
import com.ttsoft.bjtax.shenbao.model.domain.ZYTDJBXX;
import com.ttsoft.bjtax.shenbao.model.domain.Zjlx;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

public class FangtuPrintAction extends ShenbaoAction {

	Logger logger = Logger.getLogger(FangtuPrintAction.class.getName());

	protected void initialRequest(ActionMapping mapping, ActionForm form,
			HttpServletRequest httpServletRequest, HttpServletResponse response)

	{
		super.initialRequest(mapping, form, httpServletRequest, response);

//		httpServletRequest.setAttribute(Constant.SFGL_HEAD_POSITION,
//				"综合服务管理信息系统>税（费）管理>打印房屋、土地情况登记表");
//		httpServletRequest.setAttribute(Constant.SFGL_HEAD_TITLE,
//				"打印房屋、土地情况登记表");

	}



	/**
	 * 打印登记表
	 */
	public ActionForward doPrintDJ(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		try {

			FangtuPrintForm fangtuForm = (FangtuPrintForm) actionForm;

			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(fangtuForm.getJsjdm());
			UserData userData = (UserData) getUserData(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			String jsjdm = jbsj.getJsjdm();
			logger.debug("jsjdm: [" + jsjdm + "]");
			fangtuForm.setTaxpayerName(jbsj.getNsrmc());
			fangtuForm.setTaxpayerId(jbsj.getSwdjzh());
			fangtuForm.setIsWzqy(Utils.isWzqy(jbsj.getDjzclxdm()));

			//取得证件类型代码表
			List zjlxList = CodeTableUtil.getCodeTableList(request,CodeTable.ZJLX_LIST );
			List zjlx_list = new ArrayList();
			for (Iterator iter = zjlxList.iterator(); iter.hasNext();) {
				Zjlx element = (Zjlx) iter.next();
				zjlx_list.add( new LabelValueBean(element.getZjlxmc(), element.getZjlxdm()) );
			}
			request.setAttribute("zjlx_list",zjlx_list);

			FangtuPrintBVO bvo = new FangtuPrintBVO();
			bvo.setJsjdm(fangtuForm.getJsjdm() );
			
			VOPackage vo = new VOPackage();
			vo.setData(bvo);
			vo.setAction(ConstantFangtu.PRINT_DJ_ACTION);
			vo.setUserData(this.getUserData(request));
			vo.setProcessor(ConstantFangtu.FT_PRINT_PROCESSOR);

			FangtuPrintBVO form = (FangtuPrintBVO) ShenbaoProxy.getInstance()
					.process(vo);
			fangtuForm.setZiyongFWList(form.getZiyongFWList());
			fangtuForm.setZiyongTDList(form.getZiyongTDList());
			fangtuForm.setChengzuFWList(form.getChengzuFWList());
			fangtuForm.setChengzuTDList(form.getChengzuTDList());
			fangtuForm.setChuzuFWList(form.getChuzuFWList());
			fangtuForm.setChuzuTDList(form.getChuzuTDList());

			logger.debug("zhengce list size: " + form.getZhengceList());
			fangtuForm.setZhengceList( form.getZhengceList() );
			
			setTotal(fangtuForm, ConstantFangtu.CAT_FW_ZIYONG, form.getZiyongFWList());
			setTotal(fangtuForm, ConstantFangtu.CAT_FW_CHUZU, form.getChuzuFWList());
			setTotal(fangtuForm, ConstantFangtu.CAT_TD_ZIYONG, form.getZiyongTDList());
			setTotal(fangtuForm, ConstantFangtu.CAT_TD_CHUZU, form.getChuzuTDList());
			
			this.wzqyZytdTz(fangtuForm.getIsWzqy(),fangtuForm.getZiyongTDList());
			this.wzqyChengzuTz(fangtuForm.getIsWzqy(),fangtuForm.getChengzuTDList());
			
			return mapping.findForward("PrintDJ");

		} catch (Exception e) {
			Debug.out("--------doInit Action----------" + e.getMessage());
			throw ExceptionUtil.getBaseException(e);
		}
	}


	/**
	 * 打印变更表
	 */
	public ActionForward doPrintBG(ActionMapping mapping,
			ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) throws BaseException {
		try {

			FangtuPrintForm fangtuForm = (FangtuPrintForm) actionForm;


			// 取得登记数据
			Map djMap = (Map) FriendHelper.getDjInfo(fangtuForm.getJsjdm());
			UserData userData = (UserData) getUserData(request);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			String jsjdm = jbsj.getJsjdm();
			logger.debug("jsjdm: [" + jsjdm + "]");
			fangtuForm.setTaxpayerName(jbsj.getNsrmc());
			fangtuForm.setTaxpayerId(jbsj.getSwdjzh());
			fangtuForm.setIsWzqy(Utils.isWzqy(jbsj.getDjzclxdm()));
			//取得证件类型代码表
			List zjlxList = CodeTableUtil.getCodeTableList(request,CodeTable.ZJLX_LIST );
			request.setAttribute("zjlx_list",zjlxList);

			FangtuPrintBVO bvo = new FangtuPrintBVO();
			bvo.setJsjdm(fangtuForm.getJsjdm() );
			
			VOPackage vo = new VOPackage();
			vo.setData(bvo);
			vo.setAction(ConstantFangtu.PRINT_BG_ACTION);
			vo.setUserData(this.getUserData(request));
			vo.setProcessor(ConstantFangtu.FT_PRINT_PROCESSOR);

			FangtuPrintBVO form = (FangtuPrintBVO) ShenbaoProxy.getInstance()
					.process(vo);
			fangtuForm.setBgziyongFWList(form.getBgziyongFWList());
			fangtuForm.setBgziyongTDList(form.getBgziyongTDList());
			fangtuForm.setBgchengzuFWList(form.getBgchengzuFWList());
			fangtuForm.setBgchengzuTDList(form.getBgchengzuTDList());
			fangtuForm.setBgchuzuFWList(form.getBgchuzuFWList());
			fangtuForm.setBgchuzuTDList(form.getBgchuzuTDList());
			
			this.wzqyBgZiyongTD(fangtuForm.getIsWzqy(),fangtuForm.getBgziyongTDList());
			this.wzqyBgchengzuTD(fangtuForm.getIsWzqy(),fangtuForm.getBgchengzuTDList());
			
			logger.debug("zhengce list size: " + form.getZhengceList());
			fangtuForm.setZhengceList( form.getZhengceList() );

			return mapping.findForward("PrintBG");

		} catch (Exception e) {
			Debug.out("--------doInit Action----------" + e.getMessage());
			throw ExceptionUtil.getBaseException(e);
		}
	}

	private void setTotal(FangtuPrintForm fangtuForm, int destCat, List arr) {
		if (arr != null && arr.size() != 0) {
			// 处理小计
			switch (destCat) {
			case ConstantFangtu.CAT_FW_ZIYONG: {
				double fcyz = 0.0;
				double swjggz = 0.0;
				double qzmsyz = 0.0;
				double qzysyz = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					ZYFWJBXX obj = (ZYFWJBXX) iter.next();
					logger.debug("fcyz: " + obj.getFcyz());
					
					fcyz += obj.getFcyz();
					logger.debug("fcyz: " + fcyz);
					
					swjggz += obj.getSwjggz();
					qzmsyz += obj.getQzmsyz();
					qzysyz += obj.getQzysyz();
					nynse += obj.getNynse();
				}
				

				fangtuForm.setFwZiyongTotal(new FwZiyongTotal(fcyz, swjggz,
						qzmsyz, qzysyz, nynse));
			}
				break;
			case ConstantFangtu.CAT_FW_CHUZU: {
				double nzjsr = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					CZFWJBXX obj = (CZFWJBXX) iter.next();
					nzjsr += obj.getNzjsr();
					nynse += obj.getNynse();
				}

				fangtuForm.setFwChuzuTotal(new FwChuzuTotal(nzjsr, nynse));
			}
				break;
			case ConstantFangtu.CAT_TD_ZIYONG: {
				double tdmj = 0.0;
				double qzmsmj = 0.0;
				double qzysmj = 0.0;
				double mpfmse = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					ZYTDJBXX obj = (ZYTDJBXX) iter.next();

					tdmj += obj.getTdmj();
					qzmsmj += obj.getQzmsmj();
					qzysmj += obj.getQzysmj();
					mpfmse += obj.getMpfmse();
					nynse += obj.getNynse();
				}

				fangtuForm.setTdZiyongTotal(new TdZiyongTotal(tdmj, qzmsmj,
						qzysmj, mpfmse, nynse));
			}
				break;
			case ConstantFangtu.CAT_TD_CHUZU:{
				double tdmj = 0.0;
				double qzmsmj = 0.0;
				double qzysmj = 0.0;
				double mpfmse = 0.0;
				double nynse = 0.0;
				for (Iterator iter = arr.iterator(); iter.hasNext();) {
					CZTDJBXX obj = (CZTDJBXX) iter.next();

					tdmj += obj.getTdmj();
					qzmsmj += obj.getQzmsmj();
					qzysmj += obj.getQzysmj();
					mpfmse += obj.getMpfmse();
					nynse += obj.getNynse();
				}

				fangtuForm.setTdChuzuTotal(new TdChuzuTotal(tdmj, qzmsmj,
						qzysmj, mpfmse, nynse));
				
			}

			default:

			}
		}
	}	
	
	private void wzqyZytdTz(String isWzqy,List list){
		if(isWzqy.equals(ConstantFangtu.WZQY_FLAG_YES)){
			/**
			 * 外资企业,在备注里显示是否缴纳外商投资企业土地使用费
			 */
			for (int i = 0; i < list.size(); i++) {
				ZYTDJBXX jbxx=(ZYTDJBXX)list.get(i);
				if(jbxx.getBz()==null||jbxx.getBz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getSfjnws()!=null&&jbxx.getSfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDJBXX)list.get(i)).setBz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getSfjnws()!=null&&jbxx.getSfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDJBXX)list.get(i)).setBz(jbxx.getBz()+",缴纳外商投资企业土地使用费");
					}
				}
			}
		}else{
			/**
			 * 非外资企业,不做处理
			 */
		}
	}
	
	private void wzqyChengzuTz(String isWzqy,List list){
		if(isWzqy.equals(ConstantFangtu.WZQY_FLAG_YES)){
			/**
			 * 外资企业,在备注里显示是否缴纳外商投资企业土地使用费
			 */
			for (int i = 0; i < list.size(); i++) {
				CZUTDJBXX jbxx=(CZUTDJBXX)list.get(i);
				if(jbxx.getBz()==null||jbxx.getBz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getSfjnws()!=null&&jbxx.getSfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDJBXX)list.get(i)).setBz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getSfjnws()!=null&&jbxx.getSfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDJBXX)list.get(i)).setBz(jbxx.getBz()+",缴纳外商投资企业土地使用费");
					}
				}
			}
		}else{
			/**
			 * 非外资企业,不做处理
			 */
		}
	}
	
	private void wzqyBgZiyongTD(String isWzqy,List list){
		if(isWzqy.equals(ConstantFangtu.WZQY_FLAG_YES)){
			/**
			 * 外资企业,在备注里显示是否缴纳外商投资企业土地使用费
			 */
			for (int i = 0; i < list.size(); i++) {
				ZYTDBGXX jbxx=(ZYTDBGXX)list.get(i);
				/*变更前数据处理*/
				if(jbxx.getBgqbz()==null||jbxx.getBgqbz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getBgqsfjnws()!=null&&jbxx.getBgqsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDBGXX)list.get(i)).setBgqbz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getBgqsfjnws()!=null&&jbxx.getBgqsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDBGXX)list.get(i)).setBgqbz(jbxx.getBgqbz()+",缴纳外商投资企业土地使用费");
					}
				}
				
				/*变更后数据处理*/
				if(jbxx.getBghbz()==null||jbxx.getBghbz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getBghsfjnws()!=null&&jbxx.getBghsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDBGXX)list.get(i)).setBghbz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getBghsfjnws()!=null&&jbxx.getBghsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((ZYTDBGXX)list.get(i)).setBghbz(jbxx.getBghbz()+",缴纳外商投资企业土地使用费");
					}
				}
				
			}
		}else{
			/**
			 * 非外资企业,不做处理
			 */
		}
	}
	
	private void wzqyBgchengzuTD(String isWzqy,List list){
		if(isWzqy.equals(ConstantFangtu.WZQY_FLAG_YES)){
			/**
			 * 外资企业,在备注里显示是否缴纳外商投资企业土地使用费
			 */
			for (int i = 0; i < list.size(); i++) {
				CZUTDBGXX jbxx=(CZUTDBGXX)list.get(i);
				/*变更前数据处理*/
				if(jbxx.getBgqbz()==null||jbxx.getBgqbz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getBgqsfjnws()!=null&&jbxx.getBgqsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDBGXX)list.get(i)).setBgqbz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getBgqsfjnws()!=null&&jbxx.getBgqsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDBGXX)list.get(i)).setBgqbz(jbxx.getBgqbz()+",缴纳外商投资企业土地使用费");
					}
				}
				
				/*变更后数据处理*/
				if(jbxx.getBghbz()==null||jbxx.getBghbz().trim().equals("") ){
					/*备注为空*/
					if(jbxx.getBghsfjnws()!=null&&jbxx.getBghsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDBGXX)list.get(i)).setBghbz("缴纳外商投资企业土地使用费");
					}
				}else{
					/*备注不为空*/
					if(jbxx.getBghsfjnws()!=null&&jbxx.getBghsfjnws().equals(ConstantFangtu.WZQY_JN_FLAG_YES)){
						((CZUTDBGXX)list.get(i)).setBghbz(jbxx.getBghbz()+",缴纳外商投资企业土地使用费");
					}
				}
				
			}
		}else{
			/**
			 * 非外资企业,不做处理
			 */
		}
	}
	
}

package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpcdForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpcdBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票重打功能Action类:FpcdAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpcdAction extends AddBaseAction {

	/**
	 * doShow
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doShow(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpcdAction's doShow Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		try
		{
			//清空页面
			fpcdForm.resetPage();
			
			fpcdForm.setYhid(userData.yhid);
			fpcdForm.setYhmc(userData.yhmc);
			fpcdForm.setZhdm(userData.xtsbm1);
			fpcdForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpcdForm.getYhid();
			String zhdm =fpcdForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpcdForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpcdForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpcdForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//获得发票种类列表fpzlList
			fpcdForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpcdForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpcdForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "发票重打页面显示成功！");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票重打页面显示失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("show");
	}
	
	/**
	 * doShow
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doQuery(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpcdAction's doQuery Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fpcdForm.clear();
			
			String swjgzzjgdm = fpcdForm.getSwjgzzjgdm(); //税务机关组织机构代码
			String fpkfdm = fpcdForm.getFpkfdm();  //发票库房代码
			String htbh = fpcdForm.getHtbh();  //合同编号
			String fpzldm = fpcdForm.getFpzldm(); //发票种类代码
			String qshm = fpcdForm.getQshm();  //起始号码
			
			FpcdBO bo = new FpcdBO();
			
			//判断合同编号是否存在
			if(htbh != null && !htbh.equals(""))
			{
				fpcdForm.setFpbcbc(ActionUtil.queryHtbh2(htbh));
				if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "该合同编号未办理代开发票业务，或相关发票已办理退票业务："+ fpcdForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应房屋采集信息，或者无对应发票信息!");
					return mapping.findForward("show");
				}
			}
			
			//判断发票号码是否已办理退票
			if(qshm != null && !qshm.equals(""))
			{
				fpcdForm.setFpbcbc(ActionUtil.queryFphm2(fpzldm,qshm));
				if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
					return mapping.findForward("show");
				}
			}
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpcdAction doQuery form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpcdBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//返回查询信息值
			fpcdForm.setFpbcbc(bo.getMessagefwx());
			if(fpcdForm.getFpbcbc() != null && !fpcdForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpcdForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i<cxList.size(); i++)
				{
					FpcdForm fpcdItemForm = new FpcdForm();
					FpcdBO cxItem = (FpcdBO)cxList.get(i);
					
					fpcdItemForm.setFpzldm(cxItem.getFpczz().getFpzldm());
					fpcdItemForm.setFphm(cxItem.getFpczz().getFphm());
					fpcdItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fpcdItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fpcdItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fpcdItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fpcdItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					fpcdItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					
					itemList.add(fpcdItemForm);
				}
				
				// 放回到form
				fpcdForm.setCxList(itemList);
				fpcdForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，请检查查询条件是否有效！");
				fpcdForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
			
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票重打页面查询失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("query");
	}
	
	
	/**
	 * doSave
	 * @param mapping ActionMapping
	 * @param form ActionForm
	 * @param request HttpServletRequest
	 * @param response HttpServletResponse
	 * @return ActionForward
	 */
	public ActionForward doSave(ActionMapping mapping,
								ActionForm form,
								HttpServletRequest request,
								HttpServletResponse response) {
		Debug.out("into FpcdAction's doSave Method....");
        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpcdForm fpcdForm = (FpcdForm)form;
		session.setAttribute(mapping.getName(), fpcdForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			FpcdBO bo = new FpcdBO();
			
			//取发票号码种类代码、发票号码值
			//System.out.println(" >>>fpcdForm.getFphms():"+fpcdForm.getFphms());
			String[] fphms = fpcdForm.getFphms().split(",");
			for(int i =0; i<fphms.length;i++)
			{
				String innerFphms = fphms[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				//System.out.println(" >>>cxfpzldm:"+cxfpzldm+" >>>cxfphm:"+cxfphm);
				bo.setCxfpzldm(cxfpzldm);  //所选值发票号码种类
				bo.setCxqshm(cxfphm);  //所选值发票号码
			}
			
			//fpdkForm 中的 发票相关信息
			bo.setYhid(fpcdForm.getYhid());  //用户ID
			bo.setYhmc(fpcdForm.getYhmc());  //用户名称
			bo.setFpkfdm(fpcdForm.getFpkfdm());  //发票库房代码
			bo.setSwjgzzjgdm(fpcdForm.getSwjgzzjgdm());  //税务机关组织结构代码
			bo.setCxList(ActionUtil.queryFpcd(bo.getFpkfdm(), bo.getCxfpzldm(), bo.getCxqshm()));
			
			//判断提交发票号是否有结果
			if(bo.getCxList() == null || bo.getCxList().size() == 0)
			{
				request.setAttribute(mapping.getAttribute(), fpcdForm);
				request.setAttribute(Constants.MESSAGE_KEY, "发票重打失败，请检查条件（发票种类名称:"+ ActionUtil.getFpzlmc(fpcdForm.getFpzldm())+"）选择是否正确！");
				return mapping.findForward("show");
			}
			
			//将查询结果写入VO中
			for(int i =0; i<bo.getCxList().size(); i++)
			{
				FpcdBO cxItem = (FpcdBO)bo.getCxList().get(i);
				bo.setFpczz(cxItem.getFpczz());
				bo.setFpczmx(cxItem.getFpczmx());
				bo.setHtypzdzgxb(cxItem.getHtypzdzgxb());
			}
			
			Debug.out("FpcdAction doSave form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpcdBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				fpcdForm.setBccgbz("1"); //设置保存成功
				
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					
					FpcdBO fpkcNewItem = (FpcdBO) fpkpList.get(i);

					fpcdForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					fpcdForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					fpcdForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fpcdForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					fpcdForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					fpcdForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					fpcdForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					fpcdForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					fpcdForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					fpcdForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					fpcdForm.setPm(fpkcNewItem.getFpczmx().getPm());
					fpcdForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					fpcdForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					fpcdForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fpcdForm.setBz(fpkcNewItem.getFpczz().getBz());
					fpcdForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fpcdForm.setDxhj(Currency.convert((fpkcNewItem.getFpczmx().getJe().doubleValue())).substring(1));
					fpcdForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					fpcdForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					fpcdForm.setKpr(fpkcNewItem.getFpczz().getKpr());
				}
			}
			
			//request.setAttribute(Constants.MESSAGE_KEY, "发票重打信息保存成功！");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票重打信息保存失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("save");
	}
	
	
	/**
	 * doBack
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	
    public ActionForward doBack(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpcdAction's doBack Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
    
    /**
	 * doClear
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doClear(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpcdAction's doClear Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("clear");
    }
    
    /**
	 * doFpdk
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doFpdk(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpcdAction's doFpdk Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}

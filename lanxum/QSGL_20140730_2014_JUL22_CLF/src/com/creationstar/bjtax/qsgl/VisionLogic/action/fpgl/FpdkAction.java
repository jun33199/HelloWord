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

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpkc;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdkForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdkBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.Currency;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票代开功能Action类:FpdkAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpdkAction extends AddBaseAction {
	
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
		Debug.out("into FpdkAction's doShow Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fpdkForm.resetPage();
			
			fpdkForm.setYhid(userData.yhid);
			fpdkForm.setYhmc(userData.yhmc);
			fpdkForm.setZhdm(userData.xtsbm1);
			fpdkForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpdkForm.getYhid();
			String zhdm =fpdkForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpdkForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdkForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdkForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//获得发票种类列表fpzlList
			fpdkForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpdkForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpdkForm.getSwjgzzjgdm()));
			
			//保存返回值
            request.setAttribute(mapping.getAttribute(), fpdkForm);
            request.setAttribute(Constants.MESSAGE_KEY, "发票代开页面显示成功！");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票代开页面显示失败！");
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
		Debug.out("into FpdkAction's doQuery Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fpdkForm.clear();
			
			String swjgzzjgdm = fpdkForm.getSwjgzzjgdm(); //税务机关组织机构代码
			
			String yhid = userData.yhid;
			String zhdm = userData.xtsbm1;
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdkForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdkForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			String fpkfdm = fpdkForm.getFpkfdm();  //发票库房代码
			String htbh = fpdkForm.getHtbh();  //合同编号
			String fpzldm = fpdkForm.getFpzldm(); //发票种类代码
			String qshm = fpdkForm.getQshm();  //起始号码
			
			FpdkBO bo = new FpdkBO();
			
			//判断合同编号是否存在
			if(htbh != null && !htbh.equals(""))
			{
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应房屋采集信息，或者没有权限查看该采集信息!");
					return mapping.findForward("show");
				}
				
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh4(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "请核实是否存在税务机关核定信息："+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
				
				fpdkForm.setFpbcxx(ActionUtil.queryHtbh3(htbh));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "该合同编号已办理代开发票业务："+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			
			//判断页面传递的发票号码在发票库存中是否存在
			if(qshm != null && !qshm.equals(""))
			{
				fpdkForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fpkfdm, fpzldm, qshm));
				if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码不存在："+ fpdkForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			else
			{
				//处理页面
				fpdkForm.reset();
				//获得发票种类列表fpzlList
				fpdkForm.setFpzlList(ActionUtil.queryMaxFpzl(fpkfdm));
				fpdkForm.setFpbcxx(fpdkForm.getFpzlList().size()+"");
				if (fpdkForm.getFpbcxx() == null || fpdkForm.getFpbcxx().equals("") || fpdkForm.getFpbcxx().equals("0"))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "发票种类未设置或该发票种类无库存数量！");
					return mapping.findForward("show");
				}else{
					fpdkForm.setFpbcxx("");
				}
				
				bo.setFpzlList(fpdkForm.getFpzlList());
			} 
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpdkAction doQuery form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdkBO)QsglProxy.getInstance().process(vo);
			
			//返回完税凭证查询信息
			String message = bo.getMessage();
			if(message != null && !message.equals(""))
			{
				fpdkForm.setMessage(message);
				request.setAttribute(Constants.MESSAGE_KEY, fpdkForm.getMessage().substring(3));
				return mapping.findForward("show");
			}
			//返回查询信息值
			fpdkForm.setFpbcxx(bo.getMessagefwx());
			if(fpdkForm.getFpbcxx() != null && !fpdkForm.getFpbcxx().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpdkForm.getFpbcxx());
				return mapping.findForward("show");
			}
			
			//输出第一个发票号码
			ArrayList  fpkcList = bo.getFpkcList();
			
			if ((fpkcList != null) && (fpkcList.size() > 0))
			{
				Fpkc fpkc= new Fpkc();
				Fpkc qshmItem = (Fpkc) fpkcList.get(0);
				fpkc.setQshm(qshmItem.getQshm());
				fpdkForm.setQshm(fpkc.getQshm());
			}
			
			fpdkForm.setWszh(bo.getWszh());  //完税凭证号
			fpdkForm.setSbbh(bo.getSbbh()); //申报表号
			fpdkForm.setFwcqzh(bo.getFwcqzh());  //获得房屋产权证号
			fpdkForm.setFwzldz(bo.getFwzldz());  //房屋坐落地址
			fpdkForm.setDj(bo.getHtzj());  //单价
			fpdkForm.setJe(bo.getHtzj());  //总价
			fpdkForm.setXxhj(bo.getXxhj());  //小写金额合计
			fpdkForm.setDxhj(bo.getDxhj());  //大写金额合计
			fpdkForm.setNsrmc_buyer(bo.getNsrmc_buyer()); //付款人
			fpdkForm.setNsrmc_seller(bo.getNsrmc_seller());  //收款人
			fpdkForm.setHdjg(bo.getHdjg()); //核定价格
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票代开页面查询失败！");
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
		Debug.out("into FpdkAction's doSave Method....");
        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdkForm fpdkForm = (FpdkForm)form;
		session.setAttribute(mapping.getName(), fpdkForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fpdkForm.resetforBeSave();
			
			if(fpdkForm.getJe() == null || fpdkForm.getJe().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, "金额不正确："+ fpdkForm.getJe());
				return mapping.findForward("show");
			}
			
			//获取发票打印张数
			double je = Double.valueOf(fpdkForm.getJe()).doubleValue();
			int s_ = 99999999;
			int pageNum = 0;
			
			pageNum   = (int)je/s_;
			
			double temp = pageNum;
			
			if( je/s_ >temp)
			{
				pageNum +=1;
			}
			
			//判断页面传递的发票号码是否存在
			if(fpdkForm.getQshm() != null && !fpdkForm.getQshm().equals(""))
			{
				//判断页面传递的发票号码在发票库存中是否存在
				fpdkForm.setFpbcbc(ActionUtil.queryMaxFpkcxx(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm()));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码不存在："+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
				//判断页面传递的发票号码是否已使用
				fpdkForm.setFpbcbc(ActionUtil.checkFphm(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm()));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码已使用："+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
				//判断打印使用发票号码库存不足
				fpdkForm.setFpbcbc(ActionUtil.checkMoreFphm(fpdkForm.getFpkfdm(), fpdkForm.getFpzldm(), fpdkForm.getQshm(),pageNum));
				if(fpdkForm.getFpbcbc() != null && !fpdkForm.getFpbcbc().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fpdkForm);
					request.setAttribute(Constants.MESSAGE_KEY, "打印使用发票号码库存不足："+ fpdkForm.getFpbcbc());
					return mapping.findForward("show");
				}
			}
			
			FpdkBO bo = new FpdkBO();
			
			//fpdkForm 中的 发票相关信息
			bo.setYhid(fpdkForm.getYhid());  //用户ID
			bo.setYhmc(fpdkForm.getYhmc());  //用户名称
			bo.setSwjgzzjgdm(fpdkForm.getSwjgzzjgdm()); //税务机关组织机构代码
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //税务机关组织机构名称
			bo.setFpkfdm(fpdkForm.getFpkfdm());  //发票库房代码
			bo.setFpzldm(fpdkForm.getFpzldm()); //发票种类代码
			bo.setQshm(fpdkForm.getQshm());  //起始号码
			bo.setKpr(fpdkForm.getKpr());  //开票人
			bo.setPageNum(pageNum);
			
			//fpdkForm 中的 存量房相关信息
			bo.setSbbh(fpdkForm.getSbbh());  //申报表号
			bo.setHtbh(fpdkForm.getHtbh());  //合同编号
			bo.setHyfl(fpdkForm.getHyfl());  //行业分类
			bo.setNsrmc_buyer(fpdkForm.getNsrmc_buyer()); //付款单位
			bo.setNsrmc_seller(fpdkForm.getNsrmc_seller()); //收款单位
			bo.setPm(fpdkForm.getPm());  //品目 
			bo.setDj(fpdkForm.getDj());  //单价
			bo.setSl(fpdkForm.getSl());  //数量
			bo.setJe(fpdkForm.getJe());  //金额
			bo.setFwcqzh(fpdkForm.getFwcqzh());  //房屋产权证号
			bo.setFwzldz(fpdkForm.getFwzldz());  //房屋坐落地址
			bo.setBz(fpdkForm.getBzValueSubm()+fpdkForm.getBz());  //备注
			bo.setXxhj(fpdkForm.getXxhj());  //小写合计
			bo.setDxhj(fpdkForm.getDxhj());  //大写合计
			bo.setWszh(fpdkForm.getWszh());  //完税证号码
			
			Debug.out("FpdkAction doSave form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdkBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList == null || fpkpList.size() == 0)
			{
				bo.setMessagefwx("无打印结果，请核实是否代开发票成功!");
			}
			ArrayList printList = new ArrayList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				//fpdkForm.setBccgbz("1"); //设置保存成功
				
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					FpdkForm tempForm = new FpdkForm();
					
					FpdkBO fpkcNewItem = (FpdkBO) fpkpList.get(i);

					tempForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					tempForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					tempForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					tempForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					tempForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					tempForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					tempForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					tempForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					tempForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					tempForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					tempForm.setPm(fpkcNewItem.getFpczmx().getPm());
					tempForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					tempForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					tempForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					tempForm.setBz(fpkcNewItem.getFpczz().getBz());
					tempForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					tempForm.setDxhj(Currency.convert((fpkcNewItem.getFpczmx().getJe().doubleValue())).substring(1));
					tempForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					tempForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					tempForm.setKpr(fpkcNewItem.getFpczz().getKpr());
					
					printList.add(tempForm);
				}
				
				fpdkForm.setPrintList(printList);
				
				fpdkForm.resetforSave();
			}
			request.setAttribute(Constants.MESSAGE_KEY, "发票代开信息保存成功！");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票代开信息保存失败！");
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
    	Debug.out("into FpdkAction's doBack Method....");
    	
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
    	Debug.out("into FpdkAction's doClear Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("clear");
    }
    
    /**
	 * doFpzf
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doFpzf(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doFpzf Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("fpzf");
    }
    
    /**
	 * doAddSbGr
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doAddSbGr(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doAddSbGr Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("addSbGr");
    }
    
    
    /**
	 * doClfxxcj
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doClfxxcj(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doClfxxcj Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("clfxxcj");
    }
    
    /**
	 * doMfskzs
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
    public ActionForward doMfskzs(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) 
    {
    	Debug.out("into FpdkAction's doMfskzs Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("mfskzs");
    }
    
    
    /**
     * 页面打印
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws BaseException
    {
        // 返回恰当的页面。
        return mapping.findForward("print");
    }
}

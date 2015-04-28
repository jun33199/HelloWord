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
import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpzl;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FptpForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FptpBO;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票退票功能Action类:FptpAction </p>
 * @author tutu
 * @version 1.1
 */
public class FptpAction extends AddBaseAction {

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
		Debug.out("into FptpAction's doShow Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		try
		{
			//清空页面
			fptpForm.resetPage();
			
			fptpForm.setYhid(userData.yhid);
			fptpForm.setYhmc(userData.yhmc);
			fptpForm.setZhdm(userData.xtsbm1);
			fptpForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fptpForm.getYhid();
			String zhdm =fptpForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fptpForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fptpForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fptpForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//获得发票种类列表fpzlList
			fptpForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//获得退票发票种类列表cxfpzlList
			ArrayList tempList = new ArrayList();
			for(int i =0 ;i<fptpForm.getFpzlList().size(); i++)
			{
				FptpForm tempForm = new FptpForm();
				Fpzl tempfpzl = (Fpzl)fptpForm.getFpzlList().get(i);
				tempForm.setTpfpzldm(tempfpzl.getFpzldm());
				tempForm.setTpfpzlmc(tempfpzl.getFpzlmc());
				
				tempList.add(tempForm);
				
			}
			fptpForm.setTpfpzlList(tempList);
			
			//Swjjzzlgmc
			fptpForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fptpForm.getSwjgzzjgdm()));
	           
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票页面显示成功！");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票页面显示失败！");
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
		Debug.out("into FptpAction's doQuery Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fptpForm.clear();
			
			String swjgzzjgdm = fptpForm.getSwjgzzjgdm(); //税务机关组织机构代码
			String fpkfdm = fptpForm.getFpkfdm();  //发票库房代码
			String htbh = fptpForm.getHtbh();  //合同编号
			String fpzldm = fptpForm.getFpzldm(); //发票种类代码
			String qshm = fptpForm.getQshm();  //起始号码
			String tpfpzldm = fptpForm.getTpfpzldm();  //退票发票种类代码
			String tpqshm = fptpForm.getTpqshm();  //退票发票号码（起始号码）
			
			Debug.out("doQuery >>"+fptpForm.getFpkfdm()+":"+fptpForm.getTpfpzldm()+":"+fptpForm.getTpqshm());
			
			FptpBO bo = new FptpBO();
			
			//判断发票号码是否已办理退票
			if(qshm != null && !qshm.equals(""))
			{
				fptpForm.setFpbcbc(ActionUtil.queryFphm(fpzldm,qshm));
				if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "该发票号码不存在或已办理退票："+ fptpForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
					return mapping.findForward("show");
				}
			}
			
			//判断退票发票号码是否存在
			if(tpqshm != null && !tpqshm.equals(""))
			{
				fptpForm.setFpbcbc(ActionUtil.queryMaxFpkcxx(fpkfdm, tpfpzldm, tpqshm));
				if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "发票号码不存在："+ fptpForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
					return mapping.findForward("show");
				}
			}
			else
			{
				//处理页面
				fptpForm.reset();
				
				//获取查询结果的发票种类代码
				ArrayList tempList = new ArrayList();
				for(int i =0 ;i<ActionUtil.queryMaxFpzl(fpkfdm).size(); i++)
				{
					FptpForm tempForm = new FptpForm();
					Fpzl tempfpzl = (Fpzl)ActionUtil.queryMaxFpzl(fpkfdm).get(i);
					tempForm.setTpfpzldm(tempfpzl.getFpzldm());
					tempForm.setTpfpzlmc(tempfpzl.getFpzlmc());
					tempList.add(tempForm);
				}
				fptpForm.setTpfpzlList(tempList);
				
				if ((fptpForm.getTpfpzlList() == null) || (fptpForm.getTpfpzlList().size() == 0))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "发票种类未设置或该发票种类无库存数量！");
					return mapping.findForward("show");
				}
				else
				{
					//设置发票号码
					FptpForm tempTpForm = (FptpForm)fptpForm.getTpfpzlList().get(0);
					fptpForm.setTpfpzldm(tempTpForm.getTpfpzldm());
					
					//判断是否有库存，取最小发票号码
					ArrayList kcList  = ActionUtil.queryMaxFpkc(fpkfdm,fptpForm.getTpfpzldm());
					if(kcList != null && kcList.size() != 0)
					{
						Fpkc qshmItem = (Fpkc) kcList.get(0);
						fptpForm.setTpqshm(qshmItem.getQshm());
					}
				}
			}
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			
			Debug.out("FptpAction doQuery form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FptpBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//返回查询信息值
			fptpForm.setFpbcbc(bo.getMessagefwx());
			if(fptpForm.getFpbcbc() != null && !fptpForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fptpForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i<cxList.size(); i++)
				{
					FptpForm fptpItemForm = new FptpForm();
					FptpBO cxItem = (FptpBO)cxList.get(i);
					
					fptpItemForm.setFpzldm(cxItem.getHtypzdzgxb().getPizzldm());
					fptpItemForm.setFphm(cxItem.getHtypzdzgxb().getPzhm());
					fptpItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fptpItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fptpItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fptpItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fptpItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					fptpItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					
					itemList.add(fptpItemForm);
				}
				// 放回到form
				fptpForm.setCxList(itemList);
				fptpForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，请检查查询条件是否有效！");
				fptpForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票页面查询失败！");
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
		Debug.out("into FptpAction's doSave Method....");
        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FptpForm fptpForm = (FptpForm)form;
		session.setAttribute(mapping.getName(), fptpForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			Debug.out("doSave >>"+fptpForm.getFpkfdm()+":"+fptpForm.getTpfpzldm()+":"+fptpForm.getTpqshm());
			
			//判断页面传递的发票号码是否存在
			if(fptpForm.getTpqshm() != null && !fptpForm.getTpqshm().equals(""))
			{
				//判断页面传递的发票号码在发票库存中是否存在
				fptpForm.setFpbcxx(ActionUtil.queryMaxFpkcxx(fptpForm.getFpkfdm(), fptpForm.getTpfpzldm(), fptpForm.getTpqshm()));
				if(fptpForm.getFpbcxx() != null && !fptpForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码不存在："+ fptpForm.getFpbcxx());
					return mapping.findForward("show");
				}
				
				//判断页面传递的发票号码是否已使用
				fptpForm.setFpbcxx(ActionUtil.checkFphm(fptpForm.getFpkfdm(), fptpForm.getTpfpzldm(), fptpForm.getTpqshm()));
				if(fptpForm.getFpbcxx() != null && !fptpForm.getFpbcxx().equals(""))
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "发票号码已使用："+ fptpForm.getFpbcxx());
					return mapping.findForward("show");
				}
			}
			
			FptpBO bo = new FptpBO();
			
			//fpdkForm 中的 发票相关信息
			bo.setYhid(fptpForm.getYhid());  //用户ID
			bo.setYhmc(fptpForm.getYhmc());  //用户名称
			bo.setSwjgzzjgdm(fptpForm.getSwjgzzjgdm());  //税务机关组织机构代码
			bo.setSwjgzzjgmc((ActionUtil.getSwjjzzlgmc(bo.getSwjgzzjgdm()))); //税务机关组织机构名称
			bo.setFpkfdm(fptpForm.getFpkfdm());  //发票库房代码
			bo.setTpfpzldm(fptpForm.getTpfpzldm()); //退票发票种类代码
			bo.setTpqshm(fptpForm.getTpqshm());  //退票发票号码
			bo.setKpr(fptpForm.getKpr());  //开票人
			
			//取发票号码种类代码、发票号码值
			String[] fphms = fptpForm.getFphms().split(",");
			for(int i =0; i<fphms.length;i++)
			{
				String innerFphms = fphms[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				bo.setCxfpzldm(cxfpzldm);  //所选值发票号码种类
				bo.setCxqshm(cxfphm);  //所选值发票号码
				bo.setCxList(ActionUtil.queryFptp(bo.getFpkfdm(),cxfpzldm,cxfphm));  //退票信息
			
				//判断提交发票号是否有结果
				if(bo.getCxList() == null || bo.getCxList().size() == 0)
				{
					request.setAttribute(mapping.getAttribute(), fptpForm);
					request.setAttribute(Constants.MESSAGE_KEY, "退票失败，请检查条件（发票种类名称:"+ ActionUtil.getFpzlmc(fptpForm.getFpzldm())+"）选择是否正确！");
					return mapping.findForward("show");
				}
				
				//将查询结果写入VO中
				for(int j =0; j<bo.getCxList().size(); j++)
				{
					FptpBO cxItem = (FptpBO)bo.getCxList().get(j);
					bo.setFpczz(cxItem.getFpczz());
					bo.setFpczmx(cxItem.getFpczmx());
					bo.setHtypzdzgxb(cxItem.getHtypzdzgxb());
				}
			}
			
			Debug.out("FptpAction doSave form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FptpBO)QsglProxy.getInstance().process(vo);
			
			ArrayList fpkpList =bo.getFpkpList();
			
			if(fpkpList !=null && fpkpList.size() > 0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i < fpkpList.size(); i++)
				{
					
					FptpBO fpkcNewItem = (FptpBO) fpkpList.get(i);

					fptpForm.setSwjgzzjgmc(fpkcNewItem.getFpczz().getDkdwmc());
					fptpForm.setFpkfdm(fpkcNewItem.getFpczz().getFpkfdm());
					fptpForm.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fptpForm.setFphm(fpkcNewItem.getFpczz().getFphm());
					fptpForm.setKprq(String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(fpkcNewItem.getFpczz().getKprq())));
					fptpForm.setHyfl(fpkcNewItem.getFpczz().getHyfl());
					fptpForm.setNsrmc_buyer(fpkcNewItem.getFpczz().getFkdw());
					fptpForm.setNsrmc_seller(fpkcNewItem.getFpczz().getSkdw());
					fptpForm.setFwcqzh(fpkcNewItem.getFpczz().getFwcqzh());
					fptpForm.setFwzldz(fpkcNewItem.getFpczz().getFwzldz());
					fptpForm.setPm(fpkcNewItem.getFpczmx().getPm());
					fptpForm.setDj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getDj())));
					fptpForm.setSl(String.valueOf(fpkcNewItem.getFpczmx().getSl()));
					fptpForm.setJe(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fptpForm.setBz(fpkcNewItem.getFpczz().getBz());
					fptpForm.setXxhj(String.valueOf(deFormat.format(fpkcNewItem.getFpczmx().getJe())));
					fptpForm.setDxhj(String.valueOf(ActionUtil.toChineseCharacter(fpkcNewItem.getFpczmx().getJe().doubleValue())));
					fptpForm.setWszh(fpkcNewItem.getFpczz().getSphm());
					fptpForm.setJdhm(fpkcNewItem.getFpczz().getFpzldm()+fpkcNewItem.getFpczz().getFphm());
					fptpForm.setKpr(fpkcNewItem.getFpczz().getKpr());
				}
				fptpForm.resetforSave();
			}
			
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票信息保存成功！");
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票退票信息保存失败！");
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
            HttpServletResponse response) {
    	
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
            HttpServletResponse response) {
    	
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
            HttpServletResponse response) {
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("fpdk");
    }
}

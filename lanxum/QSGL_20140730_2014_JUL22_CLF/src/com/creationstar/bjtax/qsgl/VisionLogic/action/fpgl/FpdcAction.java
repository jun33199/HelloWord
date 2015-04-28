package com.creationstar.bjtax.qsgl.VisionLogic.action.fpgl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.fpgl.Fpczz;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.AddBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.fpgl.FpdcForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO;
import com.creationstar.bjtax.qsgl.model.bo.fpgl.FpdcBO2;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ExcelUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－发票管理</p>
 * <p>Description: 发票模块的发票导出功能Action类:FpdcAction </p>
 * @author tutu
 * @version 1.1
 */
public class FpdcAction extends AddBaseAction {
	
	static final String[] TITLEHZ = {"计算机代码", "开票起始时间", "开票截止时间", "正常开票份数", "退票份数", "废票份数", 
									 "正常发票金额", "退票金额","操作时间（系统日期）","操作员（导出操作员）"};
	
	static final String[] COLUMSHZ = {"jsjdm", "qsrq", "jzrq", "kpsl", "tpsl", "fpsl", "kpje", "tpje", "czrq", "czr"};
	
	static final String[] TITLE = {"发票代码", "发票号码", "计算机代码", "开票日期", "付款单位", "收款单位", "代开单位",
								   "开票类型代码","开票类型名称", "开票合计金额", "退票发票代码", "退票发票号码", "备注", 
								   "税票号码", "开票人","操作时间（系统日期）","操作员（导出数据操作员）"};
	
	static final String[] COLUMS = {"fpzldm", "fphm", "jsjdm", "kprq", "fkdw","skdw","dkdwmc", "kplxdm", "kplxmc",
									"je", "tpfpzldm","tpfphm","bz","sphm","kpr","czrq","czr"};
	
	static final String[] TITLEMX = {"发票代码", "发票号码", "项目", "单价", "数量", "金额", "开票日期"};
	
	static final String[] COLUMSMX = {"fpzldm", "fphm", "pm", "dj", "sl", "je", "kprq"};

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
		Debug.out("into FpdcAction's doShow Method....");
		
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		try
		{
			//清空页面
			fpdcForm.resetPage();
			
			fpdcForm.setYhid(userData.yhid);
			fpdcForm.setYhmc(userData.yhmc);
			fpdcForm.setZhdm(userData.xtsbm1);
			fpdcForm.setSwjgzzjgdm(userData.ssdwdm);
			
			String yhid =fpdcForm.getYhid();
			String zhdm =fpdcForm.getZhdm();
			
			Debug.out("yhid:"+yhid+"  zhdm:"+zhdm+ "  Swjgzzjgdm:"+fpdcForm.getSwjgzzjgdm());
			if(yhid != null && !yhid.equals(""))
			{
				if(yhid == zhdm)
				{
					fpdcForm.setFpkfdm(Constants.FP_WWYH+yhid);
				}
				else
				{
					fpdcForm.setFpkfdm(Constants.FP_NWYH+yhid);
				}
			}
			
			//获得发票种类列表fpzlList
			fpdcForm.setFpzlList(ActionUtil.getCodeTables(session.getServletContext(), Constants.FPZL));
			
			//Swjjzzlgmc
			fpdcForm.setSwjgzzjgmc(ActionUtil.getSwjjzzlgmc(fpdcForm.getSwjgzzjgdm()));
			
			request.setAttribute(Constants.MESSAGE_KEY, "发票导出页面显示成功！");
		}
		catch (Exception ex)
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票导出页面显示失败！");
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
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			//清空页面
			fpdcForm.clear();
			
			String swjgzzjgdm = fpdcForm.getSwjgzzjgdm(); //税务机关组织机构代码
			String fpkfdm = fpdcForm.getFpkfdm();  //发票库房代码
			String htbh = fpdcForm.getHtbh();  //合同编号
			String fpzldm = fpdcForm.getFpzldm(); //发票种类代码
			String qshm = fpdcForm.getQshm();  //起始号码
			String qsrq = fpdcForm.getQsrq();  //起始日期
			String jzrq = fpdcForm.getJzrq();  //截止日期
			String dczt = fpdcForm.getDczt();  //导出状态
			
			//System.out.println("fpkfdm:"+fpkfdm+" htbh:"+htbh+" fpzldm:"+fpzldm+" qshm:"+qshm+" qsrq:"+qsrq+" jzrq:"+jzrq+" dczt:"+dczt);
			
			FpdcBO bo = new FpdcBO();
			
			//判断合同编号是否存在
			if(htbh != null && !htbh.equals(""))
			{
				fpdcForm.setFpbcbc(ActionUtil.queryHtbh2(htbh));
				if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
				{
					//request.setAttribute(Constants.MESSAGE_KEY, "该合同编号未办理代开发票业务，或相关发票已办理退票业务："+ fpdcForm.getFpbcbc());
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应房屋采集信息，或者无对应发票信息!");
					return mapping.findForward("show");
				}
			}
			
			/*//判断发票号码是否已办理退票
			if(qshm != null && !qshm.equals(""))
			{
				fpdcForm.setFpbcbc(ActionUtil.queryFphm2(fpzldm,qshm));
				if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
				{
					request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，无对应发票信息，或者没有权限查看该发票信息!");
					return mapping.findForward("show");
				}
			}*/
			
			bo.setFpkfdm(fpkfdm);
			bo.setHtbh(htbh);
			bo.setFpzldm(fpzldm);
			bo.setQshm(qshm);
			bo.setQsrq(qsrq);
			bo.setJzrq(jzrq);
			bo.setDczt(dczt);
			bo.setSwjgzzjgdm(swjgzzjgdm);
			
			Debug.out("FpdcAction doQuery form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.QUERY);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdcBO)QsglProxy.getInstance().process(vo);
			
			ArrayList itemList = new ArrayList();
			
			//返回查询信息值
			fpdcForm.setFpbcbc(bo.getMessagefwx());
			if(fpdcForm.getFpbcbc() != null && !fpdcForm.getFpbcbc().equals(""))
			{
				request.setAttribute(Constants.MESSAGE_KEY, fpdcForm.getFpbcbc());
				return mapping.findForward("show");
			}
			
			ArrayList cxList = bo.getCxList();
			if(cxList != null && cxList.size() >0)
			{
				DecimalFormat deFormat = new DecimalFormat("#0.00");// 合计金额格式
				for(int i =0 ;i<cxList.size(); i++)
				{
					FpdcForm fpdcItemForm = new FpdcForm();
					FpdcBO cxItem = (FpdcBO)cxList.get(i);
					
					fpdcItemForm.setFpzldm(cxItem.getFpczz().getFpzldm());
					fpdcItemForm.setFphm(cxItem.getFpczz().getFphm());
					fpdcItemForm.setHtbh(cxItem.getHtypzdzgxb().getHtbh());
					fpdcItemForm.setNsrmc_buyer(cxItem.getFpczz().getFkdw());
					fpdcItemForm.setNsrmc_seller(cxItem.getFpczz().getSkdw());
					fpdcItemForm.setFwcqzh(cxItem.getFpczz().getFwcqzh());
					fpdcItemForm.setFwzldz(cxItem.getFpczz().getFwzldz());
					
					if(cxItem.getFpczmx().getJe()!=null)
					{
						fpdcItemForm.setJe(String.valueOf(deFormat.format(cxItem.getFpczmx().getJe())));
					}
					
					if(cxItem.getFpczz().getDcbz()!=null && cxItem.getFpczz().getDcbz().equals(Constants.FP_DCBZ_WTP))
					{
						fpdcItemForm.setDczt("未导出");
					}
					if(cxItem.getFpczz().getDcbz()!=null && cxItem.getFpczz().getDcbz().equals(Constants.FP_DCBZ_YTP))
					{
						fpdcItemForm.setDczt("已导出");
					}
					
					itemList.add(fpdcItemForm);
				}
				
				// 放回到form
				fpdcForm.setCxList(itemList);
				fpdcForm.setFpbcbc("");
			}
			else
			{
				request.setAttribute(Constants.MESSAGE_KEY, "无查询结果，请检查查询条件是否有效！");
				fpdcForm.setFpbcbc("test");
				return mapping.findForward("show");
			}
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票导出页面查询失败！");
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
		Debug.out("into FpdcAction's doSave Method....");
        // 禁止页面刷新
        ActionForward aForward = doHandleToken(mapping, request);

        if (aForward != null)
        {
            return aForward;
        }
		// 获取用户登录数据。
		HttpSession session = request.getSession(false);
		UserData userData = (UserData) session.getAttribute(SessionKey.USER_DATA);
		FpdcForm fpdcForm = (FpdcForm)form;
		session.setAttribute(mapping.getName(), fpdcForm);
		ActionErrors errors = new ActionErrors();
		try
		{
			FpdcBO bo = new FpdcBO();
			
			//fpdkForm 中的 发票相关信息
			bo.setYhid(fpdcForm.getYhid());  //用户ID
			bo.setYhmc(fpdcForm.getYhmc());  //用户名称
			bo.setFpkfdm(fpdcForm.getFpkfdm());  //发票库房代码
			bo.setSwjgzzjgdm(fpdcForm.getSwjgzzjgdm());
			
			//取发票号码种类代码、发票号码值
			String[] fphmsArr = fpdcForm.getFphms().split(",");
			ArrayList resList = new ArrayList();
			StringBuffer selectedSpxxBuff = new StringBuffer();
			for(int i=0; i<fphmsArr.length; i++)
			{
				Fpczz fpczz = new Fpczz();
				String innerFphms = fphmsArr[i];
				String[] fphmArr = innerFphms.split(":");
				String cxfpzldm = fphmArr[0];
				String cxfphm = fphmArr[1];
				
				fpczz.setFpzldm(cxfpzldm);  //所选值发票号码种类
				fpczz.setFphm(cxfphm);  //所选值发票号码
				
				//导数统计用
				if(i == 0){
					selectedSpxxBuff.append("(");
					selectedSpxxBuff.append(" ( b.fphm ='"+cxfphm+"' and b.fpzldm ='"+cxfpzldm+"')");
				}else{
					selectedSpxxBuff.append("or ( b.fphm ='"+cxfphm+"' and b.fpzldm ='"+cxfpzldm+"')");
				}
				
				if(i == fphmsArr.length -1)
				{
					selectedSpxxBuff.append(")");
				}
				
				resList.add(fpczz);
			}
			
			bo.setCxList(resList);
			
			//判断提交发票号是否有结果
			if(bo.getCxList() == null || bo.getCxList().size() == 0)
			{
				request.setAttribute(mapping.getAttribute(), fpdcForm);
				request.setAttribute(Constants.MESSAGE_KEY, "发票导出失败，请检查条件（发票种类名称:"+ ActionUtil.getFpzlmc(fpdcForm.getFpzldm())+"）选择是否正确！");
				return mapping.findForward("show");
			}
			
			Debug.out("FpdcAction doSave form.getData() bo.class is：" +
					bo.getClass().getName());
			
			Properties prop = (Properties) request.getSession(false).getServletContext().
        				  	   getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

			//构造后台需要的条件数据对象
			VOPackage vo = new VOPackage();
			vo.setAction(ActionType.INSERT);
			vo.setProcessor(prop.getProperty(bo.getClass().getName()));
			vo.setData(bo);
			vo.setUserData(this.getUserData());
			bo = (FpdcBO)QsglProxy.getInstance().process(vo);
			
			ArrayList mxList =bo.getFpkpList();
			
			ArrayList fpdchztList = new ArrayList();
			ArrayList fpdcmxtList = new ArrayList();
			try{
			if(mxList !=null && mxList.size() > 0)
			{
				//汇总LIST
				
				ArrayList hzList = ActionUtil.queryFpdchzData(bo.getFpkfdm(),selectedSpxxBuff.toString(),bo.getSwjgzzjgdm());
				
				for(int i =0 ;i < hzList.size(); i++)
				{
					FpdcBO2 fpdcBO2 = new FpdcBO2();
					FpdcBO2 fphzItem = (FpdcBO2) hzList.get(i);
					
					fpdcBO2.setJsjdm(fphzItem.getJsjdm());
					if(fphzItem.getQsrq() != null && fphzItem.getQsrq() != "")
					{
						fpdcBO2.setQsrq(fphzItem.getQsrq().substring(0, fphzItem.getQsrq().length()-2));
					}
					if(fphzItem.getJzrq() != null && fphzItem.getJzrq() != "")
					{
						fpdcBO2.setJzrq(fphzItem.getJzrq().substring(0, fphzItem.getJzrq().length()-2));
					}
					fpdcBO2.setKpsl(fphzItem.getKpsl());
					fpdcBO2.setTpsl(fphzItem.getTpsl());
					fpdcBO2.setFpsl(fphzItem.getFpsl());
					fpdcBO2.setKpje(fphzItem.getKpje());
					fpdcBO2.setTpje(fphzItem.getTpje());
					fpdcBO2.setCzrq(DateUtils.displayValue(new Date(),"yyyy-MM-dd"));
					fpdcBO2.setCzr(userData.yhmc);
					
					fpdchztList.add(fpdcBO2);
				}
				
				//明细LIST
				for(int i =0 ;i < mxList.size(); i++)
				{
					FpdcBO2 fpdcBO2 = new FpdcBO2();
					FpdcBO fpkcNewItem = (FpdcBO) mxList.get(i);
					
					fpdcBO2.setFpzldm(fpkcNewItem.getFpczz().getFpzldm());
					fpdcBO2.setFphm(fpkcNewItem.getFpczz().getFphm());
					fpdcBO2.setJsjdm(fpkcNewItem.getSwjgzzjg().getJsjdm());
					
					//fpdcBO2.setKprq(fpkcNewItem.getFpczz().getKprq().toString());
					
					if(fpkcNewItem.getFpczz().getKprq().toString() != null && fpkcNewItem.getFpczz().getKprq().toString() != "")
					{
						fpdcBO2.setKprq(fpkcNewItem.getFpczz().getKprq().toString().substring(0, fpkcNewItem.getFpczz().getKprq().toString().length()-2));
					}
					
					fpdcBO2.setFkdw(fpkcNewItem.getFpczz().getFkdw());
					fpdcBO2.setSkdw(fpkcNewItem.getFpczz().getSkdw());
					fpdcBO2.setDkdwmc(fpkcNewItem.getFpczz().getDkdwmc());
					fpdcBO2.setKplxdm(fpkcNewItem.getFpczz().getKplxdm());
					fpdcBO2.setKplxmc(fpkcNewItem.getKplx().getKplxmc());
					fpdcBO2.setTpfpzldm(fpkcNewItem.getFpczz().getTpfpzldm());
					fpdcBO2.setTpfphm(fpkcNewItem.getFpczz().getTpfphm());
					fpdcBO2.setBz(fpkcNewItem.getFpczz().getBz());
					fpdcBO2.setSphm(fpkcNewItem.getFpczz().getSphm());
					fpdcBO2.setKpr(fpkcNewItem.getFpczz().getKpr());
					fpdcBO2.setCzrq(DateUtils.displayValue(new Date(),"yyyy-MM-dd"));
					fpdcBO2.setCzr(userData.yhmc);
					fpdcBO2.setPm(fpkcNewItem.getFpczmx().getPm());
					fpdcBO2.setSl(fpkcNewItem.getFpczmx().getSl());
					fpdcBO2.setDj(fpkcNewItem.getFpczmx().getDj());
					fpdcBO2.setJe(fpkcNewItem.getFpczmx().getJe());
					
					fpdcmxtList.add(fpdcBO2);
				}
				fpdcForm.setDczbList(fpdchztList);
				fpdcForm.setDcmxList(fpdcmxtList);
			
			
				System.out.println("输出Excel汇总表文件记录数：" + fpdcForm.getDczbList().size());
				System.out.println("输出Excel明细表文件记录数：" + fpdcForm.getDcmxList().size());
			
				if (fpdcForm.getDcmxList() == null || fpdcForm.getDcmxList().size() <= 0) {
					request.setAttribute(Constants.MESSAGE_KEY,"没有提交查询数据，无法导出Excel文件");
					return mapping.findForward("query");
				}
				
				String currDate = DataConvert.Date2String(new Date(System.currentTimeMillis()));
				String zbFileName = "代开发票导出结果-".concat(currDate).concat(".xls");
				String zbEncodeFileName = com.ttsoft.framework.util.StringUtil.GBK2ISO(zbFileName);
				
				response.resetBuffer();
				response.setHeader("Content-disposition","attachment; filename=" + zbEncodeFileName);
				response.setContentType("application/vnd.ms-excel");
            
				HSSFWorkbook workbook = new HSSFWorkbook();
				
				String[] sheetName0 = {"DKFPHZB","代开发票汇总表"};
				String[] sheetName1 = {"DKFPMXB","代开发票明细表"};
				String[] sheetName2 = {"DKFPMXZB","代开发票明细子表"};
				
				ExcelUtil.generateExcel(workbook, 0, sheetName0[0], sheetName0[1], TITLEHZ, COLUMSHZ, fpdcForm.getDczbList(),response.getOutputStream());
				ExcelUtil.generateExcel(workbook, 1, sheetName1[0], sheetName1[1], TITLE, COLUMS, fpdcForm.getDcmxList(),response.getOutputStream());
				ExcelUtil.generateExcel(workbook, 2, sheetName2[0], sheetName2[1], TITLEMX, COLUMSMX, fpdcForm.getDcmxList(),response.getOutputStream());
				
				workbook.write(response.getOutputStream());  
				response.getOutputStream().close(); 
            
				request.setAttribute(Constants.MESSAGE_KEY, "发票信息导出成功！");
				return null;
			}
			}catch (Exception e) {  
		          e.printStackTrace();
		    }
		}
		catch (Exception ex)
		{
			errors.add(ActionErrors.GLOBAL_ERROR,
					new ActionError("err.system"));
			saveErrors(request, errors);
			request.setAttribute(Constants.MESSAGE_KEY, "发票信息导出失败！");
		}
		// 保存Token;
		saveToken(request);
		return mapping.findForward("save");
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
    	Debug.out("into FpdcAction's doClear Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("clear");
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
    	Debug.out("into FpdcAction's doBack Method....");
    	
    	// 删除session中的form
    	removeForm(mapping, request);
    	
    	// 保存Token
    	saveToken(request);
    	return mapping.findForward("back");
    }
}

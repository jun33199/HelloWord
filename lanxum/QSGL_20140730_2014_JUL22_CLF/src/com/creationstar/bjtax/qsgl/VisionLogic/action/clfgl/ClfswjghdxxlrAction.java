package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Szqy;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tdjc;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwcqzbzzflx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Qszyxsmx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfswjghdxxlrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.DateTimeUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 存量房税务机关核定信息录入 Action
 * @author 
 *
 */
public class ClfswjghdxxlrAction extends BaseAction {
	
	public ActionForward doShow(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response)
	{
		Debug.out("into ClfswjghdxxlrAction's doShow Method....");
		
		try
        {
			//保存Token;
			saveToken(request);
			//获得客户端提交的数据
			ClfswjghdxxlrForm clfswjghdxxForm = (ClfswjghdxxlrForm)form;
			//初始化
			initCode(request,clfswjghdxxForm);
			clfswjghdxxForm.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
			request.setAttribute(mapping.getAttribute(), clfswjghdxxForm);
        }catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            return new ActionForward(mapping.getInput());
        }
		//转向show页面。
		return mapping.findForward("show");
	}

	private void initCode(HttpServletRequest request,ClfswjghdxxlrForm clfswjghdxxForm) {
		HttpSession session = request.getSession(false);
		ClfxxcjAction cjAction = new ClfxxcjAction();
		//增加房屋产权证标注住房类型代码表（土地级次）
		clfswjghdxxForm.setTdjcList(ActionUtil.getCodeTables(session.getServletContext(), Constants.TDJC));
		//增加房屋产权证标注住房类型代码表
		clfswjghdxxForm.setFwcqzbzzflxList(ActionUtil.getCodeTables(session.
				getServletContext(), Constants.FWCQZBZZFLX));
		clfswjghdxxForm.setZjList(cjAction.getZjlxcode(request, clfswjghdxxForm));
		//房屋所在区域代码表
		clfswjghdxxForm.setFwszqyList(ActionUtil.getCodeTables(session.
				getServletContext(), Constants.FWSZQY));
		//房屋权属转移明细
		ArrayList list=ActionUtil.getCodeTables(session.getServletContext(), Constants.QSZYXSMX);
		ArrayList filterList=new ArrayList();
		int j=0;
		for(int i=0;i<list.size();i++){
			Qszyxsmx qszyxsmx=(Qszyxsmx)list.get(i);
			if(clfswjghdxxForm.getFwqszylx()!=null && clfswjghdxxForm.getFwqszylx().equals(qszyxsmx.getQszyxsdm())){
				filterList.add(j++, qszyxsmx);
			}
		}
		clfswjghdxxForm.setQszyxsmxList(filterList);
		
		
		//设置当前登录人信息
		String lrrq;
		try {
			lrrq = DateTimeUtil.getCurrentDate();
			UserData ud = super.getUserData();
			Debug.out("lrr = " + ud.getYhmc()+"    lrrq = " + lrrq);
			
			
			clfswjghdxxForm.setLrr(ud.getYhmc());
			clfswjghdxxForm.setLrrq(lrrq);
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @methodName:doToPrint
	 * @function:
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author:唐昌富
	 * @create date：2013-5-17 下午02:30:51
	 * @version 1.1
	 * 
	 *
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ClfswjghdxxlrForm cf1 = (ClfswjghdxxlrForm)form;
		ClfswjghdxxlrBo data = new ClfswjghdxxlrBo();
		data.setHtbh(cf1.getHtbh());
		data.setSbbh(cf1.getSbbh());
		String queryHthm = cf1.getHtbh();
		//判断如果htbh为空，则点击住房或非住房直接跳转，modified by zhangj
    	initCode(request, cf1);
		cf1.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
		if(queryHthm==null||"".equals(queryHthm)){
            if("1".equals(cf1.getFwhdlxdm())){
            	return mapping.findForward("jumpToFzf");
            }else{
            	return mapping.findForward("save");
            }
		}
		//执行保存操作
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        ClfswjghdxxlrForm cf=null;
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfswjghdxxlrForm)dataRes.getFromData();
        	cf.setSlrq( DateUtils.getHyphenDate());
        	String fwhdlxdm=cf1.getFwhdlxdm();
        	boolean isQuery=cf1.getIsQuery();
        	cf1.setFormData(cf);
    		// 将页面对象设置入mapping
        	initCode(request, cf);
        	cf.setIsQuery(isQuery);
			cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("+++++++所在楼层+++++++++++++++++++++"+cf.getSzlc_show());
    		System.out.println("+++++++总楼层+++++++++++++++++++++"+cf.getZlc_show());
    		System.out.println("+++++++是否有修改权限+++++++++++++++++++++  "+cf.isHasMAuthorise()+"  +++++"+cf.getIsSaved());
    		
    		System.out.println("+++++++++++++++"+fwhdlxdm);
    		request.setAttribute(Constants.MESSAGE_KEY, "查询成功");
    		//根据IsSaved，isHasMAuthorise，Fwhdlxdm判断跳转哪个页面，modified by zhangj
    		if("false".equals(cf.getIsSaved())){
                if("1".equals(fwhdlxdm)){
                	return mapping.findForward("jumpToFzf");
                }else{
                	return mapping.findForward("save");
                }
    		}else if("true".equals(cf.getIsSaved())){
    			if(isQuery){
                    if("1".equals(cf.getFwhdlxdm())){
                    	return mapping.findForward("jumpToFzf");
                    }else{
                    	return mapping.findForward("save");
                    }
    			}
    			
    			 if(cf.isHasMAuthorise()==true){
        			if(fwhdlxdm!=null&&cf.getFwhdlxdm()!=null&&!fwhdlxdm.equals(cf.getFwhdlxdm())){
        				ClfswjghdxxlrForm cForm=ClfcjxxFilter(cf);
        				initCode(request, cForm);
        				cForm.setIsQuery(false);
        				cForm.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
        				request.setAttribute(mapping.getAttribute(), cForm);
        			}else{
        				cf.setIsQuery(true);
        			}    			
                    if("1".equals(fwhdlxdm)){
                    	return mapping.findForward("jumpToFzf");
                    }else{
                    	return mapping.findForward("save");
                    }
    			}else{
    				
    				cf.setIsQuery(true);
    				
    				 //已保存的值
    				 if("1".equals(cf.getFwhdlxdm())){
                     	return mapping.findForward("jumpToFzf");
                     }else{
                     	return mapping.findForward("save");
                     }
    			}
    			

    		}else{
    			return mapping.findForward("save");
    		}
		
        } catch (BaseException te) {
        	
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
    		// 将页面对象设置入mapping
            cf1.reSet();
        	initCode(request, cf1);
        	cf1.setHtbh(queryHthm);
            request.setAttribute(mapping.getAttribute(), cf1);
            return new ActionForward(mapping.getInput());
        }
	
        
	}
	
	
	/**
	 * 执行删除操作
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//防止重复提交
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
		//保存Token;
		saveToken(request);	
		
		
		//获得客户端提交的数据
		ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)form;		
		
		System.out.println("执行核定信息删除，删除申报编号为#############"+cf.getSbbh());
		
		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo)cf.getData();
		
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.DELETE);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        
        try {
        	QsglProxy.getInstance().process(vo);
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
    		
    		cf.reSet();
    		cf.clear();
    		
    		
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute(Constants.MESSAGE_KEY, e.getMessage());
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);	
    		return mapping.findForward("show");
			
		}
		return mapping.findForward("show");
	}
	
	
	
	
	
	/**
	 * 通过合同编号查询存量房采集信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward doSave(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//防止重复提交
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
		//保存Token;
		saveToken(request);
		
		
		//获得客户端提交的数据
		
		ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)form;
		System.out.println("申报编号========"+cf.getSbbh());
		System.out.println("合同编号========"+cf.getHtbh());
		
		System.out.println("申请人现住址========"+cf.getSqrxzdz());
		System.out.println("是否为家庭唯一生活用房========"+cf.getJtwyshyhbz());
		System.out.println("房屋类型========"+cf.getFwlxdm());
		System.out.println("建成年代========"+cf.getJcnd());
		System.out.println("总楼层========"+cf.getZlc());
		System.out.println("原购房发票金额========"+cf.getYgffpje());
		System.out.println("购房证明日期========"+cf.getGfzmrq());
		System.out.println("土地增值税申报方式========"+cf.getTdzzssbfs());
		System.out.println("取得房地产时所缴纳的契税金额========"+cf.getQdfcqsje());
		System.out.println("取得房地产时所缴纳的印花税金额========"+cf.getQdfcyhsje());
		System.out.println("取得土地使用权所支付的金额========"+cf.getQdtdsyqzfje());
		System.out.println("旧房及建筑物的评估价格========"+cf.getJfpgjg());
		System.out.println("价格评估费用========"+cf.getJgpgfy());
		System.out.println("地税计算机代码========"+cf.getFdczjjsjdm());
		System.out.println("地税税务登记号码========"+cf.getFdczjswdjzh());
		System.out.println("房地产中介联系电话========"+cf.getFdczjlxdh());
		System.out.println("房地产经纪人姓名========"+cf.getFdczjjjr());
		System.out.println("房地产经纪人联系电话========"+cf.getFdczjjjrlxdh());
		System.out.println("房地产经纪人身份证号码========"+cf.getFdczjjjrzjhm());
		System.out.println("经纪人资格证书号码========"+cf.getFdczjjjrzgzsh());
		System.out.println("产权证标注建筑面积========"+cf.getCqzbzjzmjfl());
		System.out.println("每平米交易单价========"+cf.getMpmjydj());
		System.out.println("普通住房最高限价========"+cf.getPtzfzgxj());
		System.out.println("房屋容积率========"+cf.getFwrjl());
		System.out.println("划分标准========"+cf.getHfbz());
		System.out.println("住房使用时间========"+cf.getZfsjsjfl());
		System.out.println("营业税征收方式========"+cf.getYyszsfs());
		System.out.println("个人所得税征收方式========"+cf.getGrsdszsfs());
		System.out.println("土地增值税征收方式========"+cf.getTdzsszsfs());
		System.out.println("计税收入确认方式========"+cf.getJssrqrfs());
		System.out.println("计税收入金额========"+cf.getJsje());
		System.out.println("住房评估价格========"+cf.getZfpgjg());
		System.out.println("住房装修费用========"+cf.getZfzxfy());
		System.out.println("住房贷款利息========"+cf.getZfdklx());
		System.out.println("手续费========"+cf.getSxf());
		System.out.println("公证费========"+cf.getGzf());
		System.out.println("合理费用========"+cf.getHlfy());
		System.out.println("土地级次代码========"+cf.getTdjcdm());
		System.out.println("土地级次名称========"+cf.getTdjcmc());
		System.out.println("房屋产权证标注住房类型代码========"+cf.getFwcqzbzzflxdm());
		System.out.println("房屋产权证标注住房类型名称========"+cf.getFwcqzbzzflxmc());
		System.out.println("产权证标注建筑面积Submit========"+cf.getCqzbzjzmjflSubmit());
		System.out.println("划分标准Submit========"+cf.getHfbzSubmit());
		System.out.println("个人所得税征收方式Submit========"+cf.getGrsdszsfsSubmit());
		System.out.println("营业税征收方式Submit========"+cf.getYyszsfsSubmit());
		System.out.println("计税收入金额Submit========"+cf.getJsjeSubmit());
		System.out.println("土地增殖税征收方式Submit========"+cf.getTdzsszsfsSubmit());
		System.out.println("印花税征收方式Submit========"+cf.getYhszsfsSubmit());		
		
		System.out.println("每平米交易单价 ========"+cf.getMpmjydj());
		System.out.println("房屋建筑面积========"+cf.getFwjzmj());
		System.out.println("合理费用11========"+cf.getHlfy());
		System.out.println("房屋核定类型========"+cf.getFwhdlxdm());
		System.out.println("权属转移明细========"+cf.getQszyxsmxdm());
		System.out.println("原契税票房屋计税价格========"+cf.getYqspfwjsjg());

    	boolean isQuery=cf.getIsQuery();
    	
		
		

		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo)cf.getData();
		
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.INSERT);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        
        
        
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	this.initCode(request, cf);
        	this.doQuery(mapping, cf, request, response);
    		
    		cf.setIsSaved(dataRes.getIsSaved());
    		cf.setHasMAuthorise(dataRes.hasMAuthorise);
    		cf.setIsQuery(isQuery);
    		cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
    		cf.setSzlc_show(dataRes.szlc_show);
    		cf.setZlc_show(dataRes.zlc_show);
    		request.setAttribute(Constants.MESSAGE_KEY, "保存成功！");
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("-------------------"+request.getAttribute(Constants.MESSAGE_KEY));
    		
        } catch (Exception te) {
        	te.printStackTrace();
        	
        	
        	this.initCode(request, cf);
        	cf.setFpcxLink(ActionUtil.getLink(Constants.FPCXLINK));
        	//this.doQuery(mapping, cf, request, response);
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
    		
            if(Constants.FWHDLX_NONHOUSING.equals(cf.getFwhdlxdm())){
            	return mapping.findForward("jumpToFzf");
            }else{
            	return mapping.findForward("show");
            }
    		
        }
        if(Constants.FWHDLX_NONHOUSING.equals(cf.getFwhdlxdm())){
        	return mapping.findForward("jumpToFzf");
        }else{
        	return mapping.findForward("show");
        }
	}
	
	/**
	 * 转代开发票申请打印页面
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward doToPrint(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		ClfswjghdxxlrForm cf1 = (ClfswjghdxxlrForm)form;
		
		ClfswjghdxxlrBo data = new ClfswjghdxxlrBo();
		data.setHtbh(cf1.getHtbh());
		data.setSbbh(cf1.getSbbh());
		
		//执行保存操作
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfswjghdxxlrBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        String returnStr="";
        try {
        	ClfswjghdxxlrBo dataRes = (ClfswjghdxxlrBo)QsglProxy.getInstance().process(vo);
        	ClfswjghdxxlrForm cf = (ClfswjghdxxlrForm)dataRes.getFromData();
        	cf.setSlrq( DateUtils.getHyphenDate());
        	cf1.setFormData(cf);
        	/*--modified by huohb,2014-07-22--*/
        	//房屋核定类型代码0：住房，1：非住房
        	//根据房屋核定类型代码不同来跳不同的打印页面
        	String fwhdlxdm = cf.getFwhdlxdm();
        	if("0".equals(fwhdlxdm)){
        		returnStr="toPrintZf";
        	}else if("1".equals(fwhdlxdm)){
        		returnStr="toPrintFzf";
        	}
        	/*--modified end--*/
        	
    		// 将页面对象设置入mapping
        	initCode(request, cf);
        	getMC(cf);
    		request.setAttribute(mapping.getAttribute(), cf);
    		
    		System.out.println("+++++++土地级次+++++++++++++++++++++"+cf.getTdjcdm());
    		System.out.println("+++++++所在楼层+++++++++++++++++++++"+cf.getSzlc_show());
    		System.out.println("+++++++总楼层+++++++++++++++++++++"+cf.getZlc_show());
        } catch (BaseException te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            request.setAttribute(mapping.getAttribute(), cf1);
            return new ActionForward(mapping.getInput());
        }
		
		return mapping.findForward(returnStr);
	
	}
	
	//获得房屋土地级次名称和房屋产权证住房种类名称
	private void getMC(ClfswjghdxxlrForm cf){
		String tdjcdm = cf.getTdjcdm();
		String fwcqzbzzflxdm = cf.getFwcqzbzzflxdm();
		
		System.out.println("土地级次++++++++++++++++++"+cf.getTdjcList().size());
		System.out.println("房屋产权证标注住房类型++++++++++++++++++"+cf.getFwcqzbzzflxList().size());
		
		//获得土地级次名称
		if(tdjcdm != null &&!"".equals(tdjcdm)){
			for(int index =0 ; index <  cf.getTdjcList().size() ; index ++){
				Tdjc vo = new Tdjc();
				vo = (Tdjc)cf.getTdjcList().get(index);
				
				if(vo.getTdjcdm().equals(tdjcdm)){
					cf.setTdjcmc(vo.getTdjcmc());
				}
			}
		}
		
		//获得房屋产权证住房种类名称
		if(fwcqzbzzflxdm != null && !"".equals(fwcqzbzzflxdm)){
			for(int index = 0 ; index < cf.getFwcqzbzzflxList().size(); index ++){
				Fwcqzbzzflx vo = new Fwcqzbzzflx();
				vo = (Fwcqzbzzflx)cf.getFwcqzbzzflxList().get(index);
				
				if(vo.getFwcqzbzzflxdm().equals(fwcqzbzzflxdm)){
					cf.setFwcqzbzzflxmc(vo.getFwcqzbzzflxmc());
					
				}
			}
		}
		//mofify by yugw
		//获得房屋所在區域名称
		String fwszqydm=cf.getFwszqydm();
		if(fwszqydm !=null && ! "".equals(fwszqydm)){
			for(int index = 0;index< cf.getFwszqyList().size();index++){
				Szqy vo=new Szqy();
				vo=(Szqy)cf.getFwszqyList().get(index);
				if(vo.getFwszqydm().equals(fwszqydm)){
					cf.setFwszqymc(vo.getFwszqymc());
				}
			}
		}
	}
	
	
	
	
	
	
	//转卖方税款征收
	public ActionForward doToSellerQSZS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toSellerQSZS");
	}
//转发票代开	
	public ActionForward doToFPDK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toFPDK");
	}
	//转契税申报
	public ActionForward doToQSSB(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		
		
		return mapping.findForward("toQSSB");
	}
	private ClfswjghdxxlrForm ClfcjxxFilter(ClfswjghdxxlrForm cf){
		ClfswjghdxxlrForm form= new ClfswjghdxxlrForm();
		//==============存量房采集信息=================
		form.setHasMAuthorise(cf.isHasMAuthorise());
		form.setSaveIsSuccess(cf.getSaveIsSuccess());
		form.setHasMfSkzsxx(cf.getHasMfSkzsxx());//该核定有卖方税款征收信息
		form.setHasMfFpdkxx(cf.getHasMfFpdkxx());//该核定有卖方发票代开信息
		 
		form.setKeyStr(cf.getKeyStr());
		form.setSbbh(cf.getSbbh());
		form.setBbbs(cf.getBbbs());// 版本标示
		form.setHtbh(cf.getHtbh());// 合同编号
		form.setHtwsqyrq(cf.getHtwsqyrq());// 合同网上签约日期
		form.setFwzlqx(cf.getFwzlqx());// 房屋坐落区县
		form.setFwzldz(cf.getFwzldz());// 房屋坐落地址
		form.setFwqszylx(cf.getFwqszylx());// 房屋权属转移类型_代码
		form.setFwqszylxmc(cf.getFwqszylxmc());// 房屋权属转移类型_名称
		form.setSfwscsssggf(cf.getSfwscsssggf());// 是否为首次上市已购公房_代码
		form.setSfwscsssggfmc(cf.getSfwscsssggfmc());// 是否为首次上市已购公房_名称
		form.setFwcqzh(cf.getFwcqzh());// 房屋产权证号
		form.setFwsyqztfrq(cf.getFwsyqztfrq());// 房屋所有权证填发日期
		form.setFwjzmj(cf.getFwjzmj());// 房屋建筑面积
		form.setJzjgdm(cf.getJzjgdm());// 建筑结构代码
		form.setJzjgmc(cf.getJzjgmc());// 建筑结构名称
		form.setGhyt(cf.getGhyt());// 规划用途
		form.setHtzj(cf.getHtzj());// 合同总价
		form.setBzdm(cf.getBzdm());// 币种代码
		form.setBzmc(cf.getBzmc());// 币种名称
		form.setHl(cf.getHl());// 汇率
		form.setWbje(cf.getWbje());// 外币金额
		form.setSzlc(cf.getSzlc());// 所在楼层
		form.setFdczjjgmc(cf.getFdczjjgmc());// 房地产中介机构名称
		form.setZlc_show(cf.getZlc_show());//
		form.setSzlc_show(cf.getSzlc_show());
		form.setAll_sellerInfo(cf.getAll_sellerInfo());
		form.setAll_buyerInfo(cf.getAll_buyerInfo());
		form.setUNEpiccode(cf.getUNEpiccode());
		//==============存量房核定信息=================
		form.setHasMAuthorise(cf.isHasMAuthorise());
		form.setIsSaved(cf.getIsSaved());
		form.setIsQuery(cf.getIsQuery());
		form.setGfzmrq(cf.getGfzmrq());
		
		return form;
	}
}

package com.creationstar.bjtax.qsgl.VisionLogic.action.clfgl;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.clfgl.ClfxxcjForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;




/**
 * 
 * <p>
 * Title:存量房信息采集ACTION
 * </p>
 * <p>
 * Description: action
 * </p>
 * 
 * @author 唐昌富
 * @version 1.1
 */
public class ClfxxcjAction extends BaseAction {
	
	public ActionForward doShow(ActionMapping mapping,
	        ActionForm form,
	        HttpServletRequest request,
	        HttpServletResponse response) {
	//保存Token;
	saveToken(request);
	
    //获得客户端提交的数据
    ClfxxcjForm cf = (ClfxxcjForm)form;
    
    try {
    	cf.clear();
    	
		initCodeList(request, cf);
		
		cf.setXxly("01");
		cf.setCjfsdm("01");
	} catch (InstantiationException e) {
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		e.printStackTrace();
	}
	
	request.setAttribute(mapping.getAttribute(), cf);
	

	//转向show页面。
	return mapping.findForward("show");
	}

	/**
	 * 保存
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doSave(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		//防止重复提交
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //保存Token;
        saveToken(request);
        
        
        //获得客户端提交的数据
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        //返回页面定义
        String goPage ="show";
        //如果是手工采集
        String xxly = cf.getXxly();
        if(xxly!= null && "02".equals(xxly)){
        	goPage ="ToSGCJ";
        	
        }
        
        
		System.out.println("------------------测试用 （前台提交的数据项） start ------------------");
		System.out.println("+++++++解密二维码信息++++++++"+cf.getUNEpiccode());
		System.out.println("+++++++版本标示++++++++"+cf.getBbbs());
		System.out.println("+++++++合同编号++++++++"+cf.getHtbh());
		System.out.println("+++++++合同网上签约日期++++++++"+cf.getHtwsqyrq());
		System.out.println("+++++++房屋坐落区县++++++++"+cf.getFwzlqx());
		System.out.println("+++++++房屋坐落地址++++++++"+cf.getFwzldz());
		System.out.println("+++++++房屋权属转移类型_代码++++++++"+cf.getFwqszylx());
		System.out.println("+++++++房屋权属转移类型_名称++++++++"+cf.getFwqszylxmc());
		System.out.println("+++++++是否为首次上市已购公房_代码++++++++"+cf.getSfwscsssggf());
		System.out.println("+++++++是否为首次上市已购公房_名称++++++++"+cf.getSfwscsssggfmc());
		System.out.println("+++++++房屋产权证号++++++++"+cf.getFwcqzh());
		System.out.println("+++++++房屋所有权证填发日期++++++++"+cf.getFwsyqztfrq());
		System.out.println("+++++++房屋建筑面积++++++++"+cf.getFwjzmj());
		System.out.println("+++++++建筑结构代码++++++++"+cf.getJzjgdm());
		System.out.println("+++++++建筑结构名称++++++++"+cf.getJzjgmc());
		System.out.println("+++++++规划用途++++++++"+cf.getGhyt());
		System.out.println("+++++++合同总价++++++++"+cf.getHtzj());
		System.out.println("+++++++币种代码++++++++"+cf.getBzdm());
		System.out.println("+++++++币种名称++++++++"+cf.getBzmc());
		System.out.println("+++++++汇率++++++++"+cf.getHl());
		System.out.println("+++++++外币金额++++++++"+cf.getWbje());
		System.out.println("+++++++所在楼层++++++++"+cf.getSzlc());
		System.out.println("+++++++房地产中介机构名称++++++++"+cf.getFdczjjgmc());
		
		//卖方信息
		System.out.println("+++++++卖方信息++++++++"+cf.getAll_sellerInfo());//名称（姓名）	
		//买方信息
		System.out.println("+++++++买方信息++++++++"+cf.getAll_buyerInfo());
		System.out.println("------------------测试用  end ------------------");
        
		//执行保存操作
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.INSERT);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
    		// 将页面对象设置入mapping
        	initCodeList(request, cf);
        	
        	
        	cf.setCjfsdm(xxly);
        	
        	
        	System.out.println("+++++++++++++++++++++++++++++采集方式#####"+xxly);
        	
    		request.setAttribute(mapping.getAttribute(), cf);
    		request.setAttribute(Constants.MESSAGE_KEY, "保存成功！");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            cf.setCjfsdm(xxly);
            request.setAttribute(mapping.getAttribute(), cf);
            //form信息重置,去掉买卖方最后一个证件类型名称信息
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward(goPage);
        }
        
        
        
		System.out.println("------------------测试用 （保存成功后台返回数据项） start ------------------");
		System.out.println("+++++++解密二维码信息++++++++"+cf.getUNEpiccode());
		System.out.println("+++++++版本标示++++++++"+cf.getBbbs());
		System.out.println("+++++++合同编号++++++++"+cf.getHtbh());
		System.out.println("+++++++合同网上签约日期++++++++"+cf.getHtwsqyrq());
		System.out.println("+++++++房屋坐落区县++++++++"+cf.getFwzlqx());
		System.out.println("+++++++房屋坐落地址++++++++"+cf.getFwzldz());
		System.out.println("+++++++房屋权属转移类型_代码++++++++"+cf.getFwqszylx());
		System.out.println("+++++++房屋权属转移类型_名称++++++++"+cf.getFwqszylxmc());
		System.out.println("+++++++是否为首次上市已购公房_代码++++++++"+cf.getSfwscsssggf());
		System.out.println("+++++++是否为首次上市已购公房_名称++++++++"+cf.getSfwscsssggfmc());
		System.out.println("+++++++房屋产权证号++++++++"+cf.getFwcqzh());
		System.out.println("+++++++房屋所有权证填发日期++++++++"+cf.getFwsyqztfrq());
		System.out.println("+++++++房屋建筑面积++++++++"+cf.getFwjzmj());
		System.out.println("+++++++建筑结构代码++++++++"+cf.getJzjgdm());
		System.out.println("+++++++建筑结构名称++++++++"+cf.getJzjgmc());
		System.out.println("+++++++规划用途++++++++"+cf.getGhyt());
		System.out.println("+++++++合同总价++++++++"+cf.getHtzj());
		System.out.println("+++++++币种代码++++++++"+cf.getBzdm());
		System.out.println("+++++++币种名称++++++++"+cf.getBzmc());
		System.out.println("+++++++汇率++++++++"+cf.getHl());
		System.out.println("+++++++外币金额++++++++"+cf.getWbje());
		System.out.println("+++++++所在楼层++++++++"+cf.getSzlc());
		System.out.println("+++++++房地产中介机构名称++++++++"+cf.getFdczjjgmc());
		
		//卖方信息
		System.out.println("+++++++卖方信息++++++++"+cf.getAll_sellerInfo());//名称（姓名）	
		//买方信息
		System.out.println("+++++++买方信息++++++++"+cf.getAll_buyerInfo());
		System.out.println("------------------测试用  end ------------------");
		// 转向返回后的页面。
		
		return mapping.findForward(goPage);
	}
	
	/**
	 * 更新采集信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doUpdate(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//防止重复提交
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //保存Token;
        saveToken(request);
        
        
        //获得客户端提交的数据
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
		//执行保存操作
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.UPDATE);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
    		// 将页面对象设置入mapping
        	initCodeList(request, cf);
        	cf.setCjfsdm("02");
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "修改成功！");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("1");
            
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            cf.setCjfsdm("02");
            request.setAttribute(mapping.getAttribute(), cf);
            //form信息重置,去掉买卖方最后一个证件类型名称信息
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }       
        
		
		return mapping.findForward("ToSGCJ");
	}
	
	
	/**
	 * 删除采集信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doDelete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//防止重复提交
		ActionForward erroForwrd=  this.doHandleToken(mapping, request);
		if(erroForwrd != null){
			return erroForwrd;
		}
        //保存Token;
        saveToken(request);
        
        
        //获得客户端提交的数据
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
		//执行删除操作
        //获取存放在ServletContext中的processor-map.properties的数据
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
        data.setZjMap(zjMap);
        
        //
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.DELETE);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        vo.setData(data);
        try {
        	QsglProxy.getInstance().process(vo);
    		// 将页面对象设置入mapping
        	cf.clear();
        	initCodeList(request, cf);
        	cf.setXxly("02");
        	cf.setCjfsdm("02");
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "删除成功！");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            
            try {
				initCodeList(request, cf);
				cf.setXxly("02");
				cf.setCjfsdm("02");
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            
            request.setAttribute(mapping.getAttribute(), cf);
            //form信息重置,去掉买卖方最后一个证件类型名称信息
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }       
        
		
		return mapping.findForward("ToSGCJ");		
	}
	
	
	
	
	/**
	 * 查询存量房采集信息
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward doQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){

        //保存Token;
        saveToken(request);
        
        //获得客户端提交的数据
        ClfxxcjForm cf = (ClfxxcjForm)form;
        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
        
        //合同编号
        String queryHtbh = cf.getHtbh();
        
        System.out.println("++++++++++++"+cf.getOperationType());
        
        //
        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
        VOPackage vo = new VOPackage();
        vo.setAction(ActionType.QUERY);
        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
        vo.setUserData(this.getUserData());
        
        System.out.println("++++++++++++所属单位代码action++++++++++"+this.getUserData().getSsdwdm());
        
        vo.setData(data);
        try {
        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
        	cf = (ClfxxcjForm)dataRes.getFromData();
        	
        	cf.setSaveIsSuccess("1");
    		// 将页面对象设置入mapping
        	initCodeList(request, cf);
        	cf.setOperationType("Query");
        	
        	//信息来源
        	if(cf.getXxly() == null || "".equals(cf.getXxly())){
        		
        		cf.setXxly("02");
        	}
        	cf.setCjfsdm("02");
        	
    		request.setAttribute(mapping.getAttribute(), cf);
    		 request.setAttribute(Constants.MESSAGE_KEY, "查询成功！");
        } catch (Exception te) {
            Debug.printException(te);
            saveToken(request);
            request.setAttribute(Constants.MESSAGE_KEY, te.getMessage());
            
            cf.setSaveIsSuccess("error");
            cf.clear();
            cf.setHtbh(queryHtbh);
            cf.setXxly("02");
            cf.setCjfsdm("02");
            try {
				initCodeList(request, cf);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
            
            
            request.setAttribute(mapping.getAttribute(), cf);
            //form信息重置,去掉买卖方最后一个证件类型名称信息
            
            //return new ActionForward(mapping.getInput());
            return mapping.findForward("ToSGCJ");
        }
        
		
		
		
		return mapping.findForward("ToSGCJ");
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
	public ActionForward doToPrint(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toSwryQR");
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
	
	//转手工采集
	public ActionForward doToSGCJ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		//保存Token;
		saveToken(request);
		
	    //获得客户端提交的数据
	    ClfxxcjForm cf = (ClfxxcjForm)form;
	    //重置
	    cf.clear();
		try {
			 //初始化代码表数据
		    initCodeList(request, cf);
		    
		    cf.setXxly("02");
		    cf.setCjfsdm("02");
			
			request.setAttribute(mapping.getAttribute(), cf);
		} catch (Exception e) {
			request.setAttribute(Constants.MESSAGE_KEY, "转手工采集失败！");
			e.printStackTrace();
		}
		return mapping.findForward("ToSGCJ");
	}


	
	/**
	 * 为代码表加上一个空选项
	 * @param list  代码表数据
	 * @param classobj 每个数据对象的类名
	 * @return
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	private List setBlankOption2CodeList(List list,Class classobj) throws InstantiationException, IllegalAccessException{
		List resList = new ArrayList();
		resList.add(classobj.newInstance());
		
		for(int index =0; index < list.size(); index ++){
			resList.add(list.get(index));
		}
		return resList;
	}
	
	
	/**
	 * 利用AJAX重置form的值
	 * @methodName:doReset
	 * @function:
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @author:唐昌富
	 * @create date：2013-5-27 下午04:33:16
	 * @version 1.1
	 * 
	 *
	 */
	public ActionForward doReset(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("+++++++++++++++++++重置信息开始+++++++++++++++++++");
		
		ClfxxcjForm cf = (ClfxxcjForm)form;
			//重置saveIsSuccess的值
			String saveIsSuccess_new = (String)request.getParameter("saveIsSuccess");
			
			Enumeration em = request.getParameterNames();
			while(em.hasMoreElements()){
				System.out.println("p------------->"+em.nextElement());
			}
			
			System.out.println("重置的值为：：：："+saveIsSuccess_new);
			System.out.println("重置前****form中saveIsSuccess：：：："+cf.getSaveIsSuccess());
			cf.setSaveIsSuccess(saveIsSuccess_new);
			System.out.println("重置后****form中saveIsSuccess：：：："+cf.getSaveIsSuccess());
			request.setAttribute(mapping.getAttribute(), cf);
			System.out.println("+++++++++++++++++++重置信息结束+++++++++++++++++++");
		return null;
	}
	
	

	/**
	 * 获得证件类型名称
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
		public ActionForward doGetZjlxmc(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		System.out.println("+++++++++++++++++++获取证件名称开始+++++++++++++++++++");
		
		ClfxxcjForm cf = (ClfxxcjForm)form;
			//
			String dszjlxdm = (String)request.getParameter("DSzjlxdm");
			System.out.println("：：：：：：：：：：需要转换的证件类型代码为+++"+dszjlxdm);
			
			Map zjMap= ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
			
//			cf.setTmp_zllxmc(((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc());
			System.out.println("：：：：：：：：：：需要转换的证件类型名称为+++"+((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc());
			
			//request.setAttribute(mapping.getAttribute(), cf);
			
			
			try {
				//设置生成文件的类型和编码方式  

				response.setContentLength(9);
				 response.setContentType("text/xml;charset=UTF-8");  

				 response.setHeader("Cache-Control", "no-cache");  
				 

				PrintWriter returnValue = response.getWriter();
				String output="";
				//output="<response>温盛明</response>";   
				output=((Zjlx) zjMap.get(dszjlxdm)).getZjlxmc();

				returnValue.print(output);   
				//response.setContentLength(output.getBytes().length);
				

				returnValue.close();   
				response.flushBuffer();
			} catch (Exception e) {
			}
			
			
			
			
			
			System.out.println("+++++++++++++++++++获取证件名称结束+++++++++++++++++++");
		return null;
	}
		
		
		/**
		 *  初始化代码表数据
		 * @param request
		 * @param cf
		 * @throws InstantiationException
		 * @throws IllegalAccessException
		 */
		private void initCodeList(HttpServletRequest request, ClfxxcjForm cf)
				throws InstantiationException, IllegalAccessException {
			//初始化代码表数据
			//0.获得修改删除权限
			getAuth(request, cf);
			//1.证件类型
			getZjlxcode(request, cf);
			//2.初始化币种代码表信息			
			cf.setCodeList_bz(setBlankOption2CodeList(ActionUtil.getCodeTables(request.getSession().getServletContext(), Constants.BZ),BZ.class));
			//3.房屋性质
			cf.setCodeList_fwxz(ActionUtil.getCodeTables(request.getSession().getServletContext(), Constants.FWXZ));
		}		
		
		/**
		 * 证件类型代码表
		 * @param request
		 * @param cf
		 * @return
		 */
		public List getZjlxcode(HttpServletRequest request, ClfxxcjForm cf) {
			List tempZjList = new ArrayList();
			HashMap zjMap= (HashMap)ActionUtil.getCodeMaps(request.getSession(false).getServletContext(), Constants.ZJLXMAP);
			
			//cf.setZjMap(zjMap);
			
			Iterator it = zjMap.keySet().iterator();  
			
			while(it.hasNext()){
				tempZjList.add(zjMap.get(it.next()));
			}
			
			cf.setZjList(tempZjList);
			return tempZjList;
		}
		
		/**
		 * 获得修改和删除权限
		 * @param request
		 * @param cf
		 */
		public void getAuth(HttpServletRequest request, ClfxxcjForm cf){
	        //获得客户端提交的数据
	        ClfxxcjBo data = (ClfxxcjBo)cf.getData();
	        
	        //
	        Properties prop = (Properties) request.getSession(false).getServletContext().getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);
	        VOPackage vo = new VOPackage();
	        vo.setAction(ActionType.LOADCODES);
	        vo.setProcessor(prop.getProperty(new ClfxxcjBo().getClass().getName()));
	        vo.setUserData(this.getUserData());
	        vo.setData(data);
	        try {
	        	ClfxxcjBo dataRes = (ClfxxcjBo)QsglProxy.getInstance().process(vo);
	        	cf.setHasMAuthorise(dataRes.isHasMAuthorise());
	        	
	        	System.out.println("是否含有修改权限+++++++++"+dataRes.isHasMAuthorise());
	        } catch (Exception te) {
	            Debug.printException(te);
	        }			
		}
}

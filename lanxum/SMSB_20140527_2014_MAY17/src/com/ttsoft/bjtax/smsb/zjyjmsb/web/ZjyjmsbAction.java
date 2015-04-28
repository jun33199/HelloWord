package com.ttsoft.bjtax.smsb.zjyjmsb.web;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx12;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbmx3;
import com.ttsoft.bjtax.shenbao.model.domain.Zjyjmsbz;
import com.ttsoft.bjtax.smsb.zjyjmsb.web.ZjyjmsbConstant;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbForm;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;
/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 再就业减免税申报控制器</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  qinwei
 * @version 1.1 2006-5-26
 */
public class ZjyjmsbAction extends SmsbAction {

	private UserData userData = null;

    /**
     * 公共的前置处理方法，每次进入页面都会被调用<BR>
     * 设置页面显示时使用的导航信息和标题
     * @param mapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param response HttpServletResponse
     */
    protected void initialRequest (ActionMapping mapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">综合服务管理信息系统</font>&gt;<font color=\"#7C9AAB\">申报资料录入</font>&gt;再就业减免税申报表</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "再就业减免税申报表");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/jmssb/jmssb-000.htm");
    }


	/**
     * 初始化显示处理
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */

	public ActionForward doShow(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
            throws BaseException {


            ZjyjmsbForm myForm = (ZjyjmsbForm)form;
            myForm.reset(mapping,request);
            
            //初始化数据传递容器
            VOPackage vo = new VOPackage();
            //设置后台调用Action值
            vo.setAction(CodeConstant.SMSB_SHOWACTION);

            UserData userData = this.getUserData(request);
            vo.setUserData(userData);
            vo.setData(myForm);
            //设置Proxy要调用的processor的类名
            vo.setProcessor(CodeConstant.ZJYJMSB_PROCESSOR);       
            try        
            {
            	myForm = (ZjyjmsbForm) SbzlProxy.getInstance().process(vo);
            	request.setAttribute(mapping.getAttribute(), myForm);
            }
            catch (Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex);
            }
            myForm.setActionType("Show");

           return mapping.findForward("Show");

		}
	 /**
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm JmssbForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward的跳转目标
     * @throws BaseException 系统捕捉异常，根据异常类型抛出
     *          ApplicationException 业务异常类型抛出
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
    	
        //当前的ActionForm
        ZjyjmsbForm zjyForm = (ZjyjmsbForm)form;
        
        String jd = zjyForm.getJd();
        //String dqjd = zjyForm.getDqjd();
        switch (Integer.parseInt(jd)){
        case 1: zjyForm.setYf(1); break;
        case 2: zjyForm.setYf(4); break;
        case 3: zjyForm.setYf(7); break;
        case 4: zjyForm.setYf(10); break;
        }
        //if(jd.equals(dqjd)){
        //	zjyForm.setJdpd(true);
        //}
        //else {
        //	zjyForm.setJdpd(false);
        //}
        //征期判断，如果在当前征期内，则返回ture,否则返回false
        //zjyForm.setJd1(zjyForm.getJd());
        //初始化表单
        zjyForm.reset(actionMapping,request,0);
        
        //初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_QUERYACTION);

        UserData userData = this.getUserData(request);
        vo.setUserData(userData);
        vo.setData(zjyForm);
        //设置Proxy要调用的processor的类名
        vo.setProcessor(CodeConstant.ZJYJMSB_PROCESSOR);
        //获得登记基本信息
        SWDJJBSJ jbsj = null;
        try {
        	jbsj = InterfaceDj.getJBSJ_New(zjyForm.getJsjdm(),userData);
        	String djcelx = jbsj.getDjzclxdm();
            if(djcelx.equals(ZjyjmsbConstant.DJCELX_GTGSH) || djcelx.equals(ZjyjmsbConstant.DJCELX_GRHH)){
            	zjyForm.setDjcelx(ZjyjmsbConstant.DJCELX_GTJY);
            }
            else{
            	zjyForm.setDjcelx(ZjyjmsbConstant.DJCELX_QY);
            }
        	zjyForm.setDh(jbsj.getZcdzlxdh());
            zjyForm.setNsrmc(jbsj.getNsrmc());


        //调用后台EJB判断是否有申报数据

         Map mxMap = (Map)SbzlProxy.getInstance().process(vo);
         List mx12List = (List)mxMap.get("MX12LIST");
         Zjyjmsbmx3 zjyjmsbmx3 =(Zjyjmsbmx3)mxMap.get("MX3");
         zjyForm.setSjpd(mxMap.get("SJPD").toString().matches("true"));
         //zjyForm.setZqpd(((Boolean)mxMap.get("ZQPD")).booleanValue());
         
         //如果再就业明细表(一)or(二)or(三)为空，则数据判断为false,否则为ture;
         if(mx12List.size()==0 && zjyjmsbmx3 == null)
          {  
        	 //zjyForm.setSjpd(false);
        	 request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"没有再就业减免税申报资料，请录入！");
        	 return actionMapping.findForward("Query");
          }
         else
         {  
        	//zjyForm.setSjpd(true);
            if(mx12List.size()!=0)
            {

        	  for(int i=0;i<mx12List.size();i++)
        	  {
        		 Zjyjmsbmx12 zjyjmsbmx12 = (Zjyjmsbmx12)mx12List.get(i);

        		if(Integer.parseInt(zjyjmsbmx12.getSbblxdm())==Integer.parseInt(ZjyjmsbConstant.ZJYJMSBB1))
        		 {
        			zjyForm.setJ1(zjyjmsbmx12.getXnxgsyrs());
        			zjyForm.setQylx(zjyjmsbmx12.getNsrlxdm());

        			 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_YYS))
          		   {
          				 zjyForm.setJ2(zjyjmsbmx12.getJ1());
          				 zjyForm.setJ3(zjyjmsbmx12.getJ2());
          				 zjyForm.setJ4(zjyjmsbmx12.getJ3());
          		   }
          		   if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JSS))
          		   {
          			     zjyForm.setJ5(zjyjmsbmx12.getJ1());
          			     zjyForm.setJ6(zjyjmsbmx12.getJ2());
          			     zjyForm.setJ7(zjyjmsbmx12.getJ3());
          		   }
          		   if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JYFFJ))
          		   {
          				 zjyForm.setJ8(zjyjmsbmx12.getJ1());
          				 zjyForm.setJ9(zjyjmsbmx12.getJ2());
          				 zjyForm.setJ10(zjyjmsbmx12.getJ3());
          		   }
          		   if (zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_QYSDS))
          		   {

          				 zjyForm.setJ14(zjyjmsbmx12.getJ3());

          		   }
          		 }
          			 else
          			 {
          				 zjyForm.setJ21(zjyjmsbmx12.getXnxgsyrs());
          				 zjyForm.setQylx1(zjyjmsbmx12.getNsrlxdm());

          				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_YYS))
                		     {
              				 zjyForm.setJ22(zjyjmsbmx12.getJ1());
              				 zjyForm.setJ23(zjyjmsbmx12.getJ2());
              				 zjyForm.setJ24(zjyjmsbmx12.getJ3());
                		     }
          				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JSS))
                		     {
              				 zjyForm.setJ25(zjyjmsbmx12.getJ1());
              				 zjyForm.setJ26(zjyjmsbmx12.getJ2());
              				 zjyForm.setJ27(zjyjmsbmx12.getJ3());
                		     }
          				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_JYFFJ))
                		    {
              				 zjyForm.setJ28(zjyjmsbmx12.getJ1());
              				 zjyForm.setJ29(zjyjmsbmx12.getJ2());
              				 zjyForm.setJ30(zjyjmsbmx12.getJ3());
                		    }
          				 if(zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_GRSDS))
                		    {
          				     zjyForm.setJ31(zjyjmsbmx12.getJ1());
              				 zjyForm.setJ32(zjyjmsbmx12.getJ2());
              				 zjyForm.setJ33(zjyjmsbmx12.getJ3());
                		    }
              			if (zjyjmsbmx12.getSzdm().equals(ZjyjmsbConstant.SZDM_QYSDS))
                    	   {
              				 zjyForm.setJ34(zjyjmsbmx12.getJ3());
                    	   }
        			  }

        	    }
            }
                       if(zjyjmsbmx3 != null)
                        {
        	               zjyForm.setQnyysr(zjyjmsbmx3.getQnyysr());

        	               zjyForm.setQnjmse(zjyjmsbmx3.getQnjmse());
                         }

           }
         request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"查询再就业减免税申报资料成功！");
    	 return actionMapping.findForward("Query");
        }

        catch(Exception ex)
        {
		 System.out.println(ex);
         throw ExceptionUtil.getBaseException(ex);

        }


    }




	//保存申报数据
	public ActionForward doSave(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	        throws BaseException {
		 // 检查token
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        UserData userData = this.getUserData(request);

        // 取得zjyjmsbForm
        ZjyjmsbForm zjyForm = (ZjyjmsbForm)form;
        String jsjdm =zjyForm.getJsjdm();
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp sbrq = TinyTools.second2Day(now); // 申报日期
        String nd = zjyForm.getNd(); //年度
        String jd = zjyForm.getJd(); //季度
        Map skssrqMap = Skssrq.commonQuarter(Integer.parseInt(nd),Integer.parseInt(jd));

        try {
        SWDJJBSJ djInfo = InterfaceDj.getJBSJ_New(zjyForm.getJsjdm(),userData);
        //填充再就业减免申报主表
        Zjyjmsbz zjyjmsbz = new Zjyjmsbz();
        zjyjmsbz.setJsjdm(jsjdm);
        zjyjmsbz.setSwjgzzjgdm(djInfo.getSwjgzzjgdm());
        zjyjmsbz.setFsdm(CodeConstant.FSDM_SMSB);
        zjyjmsbz.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);
        zjyjmsbz.setBbq(zjyForm.getJd());
        zjyjmsbz.setBbnd(nd);
        zjyjmsbz.setSkssksrq((Timestamp)skssrqMap.get(Skssrq.SKSSKSRQ));
        zjyjmsbz.setSkssjsrq((Timestamp)skssrqMap.get(Skssrq.SKSSJSRQ));
        zjyjmsbz.setSbrq(sbrq);
        zjyjmsbz.setJzbz(ZjyjmsbConstant.JZBS);
        zjyjmsbz.setLrr(userData.yhid);
        zjyjmsbz.setLrrq(sbrq);
        zjyjmsbz.setCjr(userData.yhid);
        zjyjmsbz.setCjrq(sbrq);
        zjyjmsbz.setQxdm(djInfo.getSwjgzzjgdm().substring(0, 2));
        zjyjmsbz.setNd(nd);


           //填充再就业减免申报明细列表
           List zjymxlist = new ArrayList();
           String qylxdm=zjyForm.getQylx();
           String qylxdm1=zjyForm.getQylx1();
           String qxdm =djInfo.getSwjgzzjgdm().substring(0, 2);
           int qylx=Integer.parseInt(qylxdm);
           int qylx1=Integer.parseInt(qylxdm1);
           Map createMap = new HashMap();
           createMap.put("JSJDM",jsjdm);
           createMap.put("ND",nd);
           createMap.put("SBRQ",sbrq);
           createMap.put("QXDM",qxdm);
           createMap.put("YHID",userData.yhid);
           createMap.put("ZJYFORM",zjyForm);

           //根据企业类型构造明细列表
           switch(qylx){
           case 1:

        	   if(!zjyForm.getJ2().equals("")||!zjyForm.getJ3().equals("")||!zjyForm.getJ4().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ5().equals("")||!zjyForm.getJ6().equals("")||!zjyForm.getJ7().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ8().equals("")||!zjyForm.getJ9().equals("")||!zjyForm.getJ10().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB1));}
               break;
           case 2:
        	   if(!zjyForm.getJ5().equals("")||!zjyForm.getJ6().equals("")||!zjyForm.getJ7().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   if(!zjyForm.getJ8().equals("")||!zjyForm.getJ9().equals("")||!zjyForm.getJ10().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 3:
        	   if(!zjyForm.getJ14().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 4:
        	   if(!zjyForm.getJ14().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           case 5:
        	   if(!zjyForm.getJ14().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB1));}
        	   break;
           }


           //根据企业类型构造明细列表
           switch(qylx1){
           case 1:
        	   if(!zjyForm.getJ22().equals("")||!zjyForm.getJ23().equals("")||!zjyForm.getJ24().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ25().equals("")||!zjyForm.getJ26().equals("")||!zjyForm.getJ27().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ28().equals("")||!zjyForm.getJ29().equals("")||!zjyForm.getJ30().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ34().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB2));}
               break;

           case 2:
        	   if(!zjyForm.getJ34().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_QYSDS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   break;

           case 3:
        	   if(!zjyForm.getJ22().equals("")||!zjyForm.getJ23().equals("")||!zjyForm.getJ24().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_YYS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ25().equals("")||!zjyForm.getJ26().equals("")||!zjyForm.getJ27().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JSS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ28().equals("")||!zjyForm.getJ29().equals("")||!zjyForm.getJ30().equals("")){
               zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_JYFFJ,ZjyjmsbConstant.ZJYJMSBB2));}
        	   if(!zjyForm.getJ31().equals("")||!zjyForm.getJ32().equals("")||!zjyForm.getJ33().equals("")){
        	   zjymxlist.add(createMX12(createMap,ZjyjmsbConstant.SZDM_GRSDS,ZjyjmsbConstant.ZJYJMSBB2));}
        	   break;
           }

        VOPackage vo = new VOPackage();
        Map dataMap = new HashMap();
        dataMap.put("JSJDM", jsjdm);
        dataMap.put("BBND",nd);
        dataMap.put("BBQ",jd);
        ZjyjmsbInfor zjyjmsbinfor =new ZjyjmsbInfor();
        zjyjmsbinfor.setZjyjmsbmxList(zjymxlist);
        zjyjmsbinfor.setZjyjmsbz(zjyjmsbz);
        //根据前台传送过来的数据判断是否需要填充明细表三
        if(!zjyForm.getQnjmse().equals("") || !zjyForm.getQnyysr().equals("")){
     	  Zjyjmsbmx3 zjyjmsbmx3=createMX3(createMap);
     	  zjyjmsbinfor.setZjyjmsbmx3(zjyjmsbmx3);
        }
        dataMap.put("zjyjmsbinfor",zjyjmsbinfor);
        vo.setAction(CodeConstant.SMSB_SAVEACTION);
        vo.setProcessor("com.ttsoft.bjtax.smsb.zjyjmsb.processor.ZjyjmsbProcessor");
        vo.setData(dataMap);
        vo.setUserData(userData);
        SbzlProxy.getInstance().process(vo);
      }

      catch(Exception ex){
    	  throw ExceptionUtil.getBaseException(ex);
      }

        //全部初始化
		zjyForm.reset(mapping, request);
        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,"保存再就业减免税申报资料成功！");
        return mapping.findForward("Save");

	}

     //	删除申报数据
	public ActionForward doDelete(ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response)
	        throws BaseException {
        //对数据库操作的Method进行修改，防止刷新或重复提交
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }
		ZjyjmsbForm zjyForm = (ZjyjmsbForm)form;
		Map dataMap = new HashMap();
        dataMap.put("JSJDM", zjyForm.getJsjdm());
        dataMap.put("BBND",zjyForm.getNd());
        dataMap.put("BBQ",zjyForm.getJd());
        //      初始化数据传递容器
        VOPackage vo = new VOPackage();
        //设置后台调用Action值
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        UserData userData = this.getUserData(request);
        vo.setUserData(userData);
        vo.setData(dataMap);

        //设置Proxy要调用的processor的类名
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.zjyjmsb.processor.ZjyjmsbProcessor");
        try
        {
        SbzlProxy.getInstance().process(vo);
        zjyForm.reset(mapping, request);

        request.setAttribute(CodeConstant.SMSB_SAVE_SUCCESS,
                                        "删除再就业减免税申报资料成功！");
        }
        catch(Exception ex)
        {
        	System.out.println(ex);
            throw ExceptionUtil.getBaseException(ex);
        }
        return mapping.findForward("Delete");
	}

	//构造再就业减免申报明细表12,根据参数SZDM来设置J1，J2,J3读取的表单字段
	public static Zjyjmsbmx12 createMX12(Map createMap,String szdm,String sbblxdm){
		Zjyjmsbmx12 zjyjmsbmx12 = new Zjyjmsbmx12();
		ZjyjmsbForm zjyForm =(ZjyjmsbForm)createMap.get("ZJYFORM");
		String jsjdm =(String)createMap.get("JSJDM");
		String nd =(String)createMap.get("ND");
		String qxdm =(String)createMap.get("QXDM");
		String yhid =(String)createMap.get("YHID");
		Timestamp sbrq =(Timestamp)createMap.get("SBRQ");
    	zjyjmsbmx12.setJsjdm(jsjdm);
    	zjyjmsbmx12.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);
    	zjyjmsbmx12.setBbq(zjyForm.getJd());
    	zjyjmsbmx12.setBbnd(nd);
        zjyjmsbmx12.setSbblxdm(sbblxdm);
    	zjyjmsbmx12.setLrr(yhid);
    	zjyjmsbmx12.setLrrq(sbrq);
    	zjyjmsbmx12.setCjr(yhid);
    	zjyjmsbmx12.setCjrq(sbrq);
    	zjyjmsbmx12.setQxdm(qxdm);
    	zjyjmsbmx12.setNd(nd);
    	zjyjmsbmx12.setSzdm(szdm);
    	//根据报表类型代码判断填充明细表1或2
    	if(sbblxdm==ZjyjmsbConstant.ZJYJMSBB1){
    		zjyjmsbmx12.setXnxgsyrs(zjyForm.getJ1());
    		zjyjmsbmx12.setNsrlxdm(zjyForm.getQylx());
    	    zjyjmsbmx12.setNsrlxmc(ZjyjmsbConstant.getNsrlxmc(zjyForm.getQylx()));
            //营业税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_YYS))
    		   {
                zjyjmsbmx12.setJ1(zjyForm.getJ2());
        		zjyjmsbmx12.setJ2(zjyForm.getJ3());
        		zjyjmsbmx12.setJ3(zjyForm.getJ4());
    		   }
        	//城市维护建设税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_JSS))
    		   {

        		zjyjmsbmx12.setJ1(zjyForm.getJ5());
        		zjyjmsbmx12.setJ2(zjyForm.getJ6());
        		zjyjmsbmx12.setJ3(zjyForm.getJ7());
    		   }
        	//教育费附加
        	if(szdm.equals(ZjyjmsbConstant.SZDM_JYFFJ))
        	{
        		zjyjmsbmx12.setJ1(zjyForm.getJ8());
        		zjyjmsbmx12.setJ2(zjyForm.getJ9());
        		zjyjmsbmx12.setJ3(zjyForm.getJ10());
        	}
        	//企业所得税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_QYSDS))
        	{

        		zjyjmsbmx12.setJ3(zjyForm.getJ14());


        	}
          }
         else {
        		zjyjmsbmx12.setXnxgsyrs(zjyForm.getJ21());
        		zjyjmsbmx12.setNsrlxdm(zjyForm.getQylx1());
        	    zjyjmsbmx12.setNsrlxmc(ZjyjmsbConstant.getNsrlxmc1(zjyForm.getQylx1()));

            //营业税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_YYS))
     		   {

            	zjyjmsbmx12.setJ1(zjyForm.getJ22());
        		zjyjmsbmx12.setJ2(zjyForm.getJ23());
        		zjyjmsbmx12.setJ3(zjyForm.getJ24());
     		   }
            //城市维护建设税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_JSS))
    		   {
        		zjyjmsbmx12.setJ1(zjyForm.getJ25());
        		zjyjmsbmx12.setJ2(zjyForm.getJ26());
        		zjyjmsbmx12.setJ3(zjyForm.getJ27());
    		   }
            //教育费附加
        	if(szdm.equals(ZjyjmsbConstant.SZDM_JYFFJ))
        	{
        		zjyjmsbmx12.setJ1(zjyForm.getJ28());
        		zjyjmsbmx12.setJ2(zjyForm.getJ29());
        		zjyjmsbmx12.setJ3(zjyForm.getJ30());
        	}
            //个人所得税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_GRSDS))
        	{

        		zjyjmsbmx12.setJ1(zjyForm.getJ31());
        		zjyjmsbmx12.setJ2(zjyForm.getJ32());
        		zjyjmsbmx12.setJ3(zjyForm.getJ33());
        	}
            //企业所得税
        	if(szdm.equals(ZjyjmsbConstant.SZDM_QYSDS))
        	{
        		zjyjmsbmx12.setJ3(zjyForm.getJ34());

        	}
    	}
    	return zjyjmsbmx12;
	}
    //构造再就业减免申报明细表3
	public static Zjyjmsbmx3 createMX3(Map createMap){
		Zjyjmsbmx3 zjyjmsbmx3 = new Zjyjmsbmx3();
		ZjyjmsbForm zjyForm =(ZjyjmsbForm)createMap.get("ZJYFORM");
		String jsjdm =(String)createMap.get("JSJDM");
		String nd =(String)createMap.get("ND");
		String qxdm =(String)createMap.get("QXDM");
		String yhid =(String)createMap.get("YHID");
		Timestamp sbrq =(Timestamp)createMap.get("SBRQ");
		zjyjmsbmx3.setJsjdm(jsjdm);
	    zjyjmsbmx3.setBblxdm(ZjyjmsbConstant.BBLXDM_JB);//由于有外键，此处只能填季报
	    zjyjmsbmx3.setBbq(zjyForm.getJd());
	    zjyjmsbmx3.setBbnd(nd);
	    zjyjmsbmx3.setQnyysr(zjyForm.getQnyysr());
	    zjyjmsbmx3.setQnjmse(zjyForm.getQnjmse());
	    zjyjmsbmx3.setLrr(yhid);
	    zjyjmsbmx3.setLrrq(sbrq);
	    zjyjmsbmx3.setCjr(yhid);
	    zjyjmsbmx3.setCjrq(sbrq);
	    zjyjmsbmx3.setQxdm(qxdm);
	    zjyjmsbmx3.setNd(nd);
		return zjyjmsbmx3;
	}

}


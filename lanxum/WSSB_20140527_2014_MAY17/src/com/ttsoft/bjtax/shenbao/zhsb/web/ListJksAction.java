package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.zhsb.SessionKey;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * 缴款书列表
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ListJksAction extends ShenbaoAction
{
    public ListJksAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.LISTJKS;
    }

    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response)
        throws BaseException
    {
        try
        {
            // 查询本期申报未缴款的数据
            VOPackage vo = new VOPackage();
            vo.setAction(ActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);

            HashMap map = new HashMap();

            map.put(ZhsbMapConstant.JSJDM, getUserData(request).yhid);
            map.put(ZhsbMapConstant.WHRQ, (new SimpleDateFormat("yyyy-MM-dd")).format(new Date()));
            map.put("WSSB_SWJGZZJGDM", FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());
            vo.setData(map);
            HashMap jks = (HashMap)ShenbaoProxy.getInstance().process(vo);

            request.getSession().setAttribute(SessionKey.JKS, jks);
            request.setAttribute("IsSskk",Boolean.valueOf(false));
            return mapping.findForward("Show");
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doViewOne(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws BaseException
    {
        ListJksForm myForm = (ListJksForm)form;

//        String jksh = myForm.getJkshIndex();
        String sbbh = myForm.getSbbhIndex();

        HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
        HashMap sbb = (HashMap)jksMap.get(sbbh);

        Integer printTag = (Integer)sbb.get(ZhsbMapConstant.PRINTTAG);

        if (printTag.intValue() == CodeConstant.PRINT_YPYS)
        {
            // 一票一税
            return mapping.findForward("ViewYPYS");
        }
        else
        {
            // 一票多税
            return mapping.findForward("ViewYPDS");
        }
    }

    public ActionForward doDelete(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        DzyjsjVO dzyj = null;
        UserData ud = getUserData(request);
        try
        {
            ListJksForm myForm = (ListJksForm)form;
            String sbbh = myForm.getSbbhIndex();
            String jksh = myForm.getJkshIndex();  //缴款书号

            //started added by qianchao 2005-11-24
            String ywuid;
            if(jksh.trim().length() != 0)
            {
                ywuid = jksh;
            }
            else
            {
                ywuid = sbbh;
            }

            String strOrginData = request.getParameter("SecX_OrginData");
            String signData = request.getParameter("SecX_SignData");
            if (ud.getCaflag()) {
              ///检测签名并保存
                String SecX_Error = request.getParameter("SecX_Error");
                if (! "0".equals(SecX_Error))
                {
                    String tempstr;
                    tempstr = "解密验签名错误!Error:" + SecX_Error
                    + " SecX_OD " + ud.getYhid() + ":" + strOrginData
                    + "-----SecX_SD:" + signData  + "-----";
                    System.out.println(tempstr);
                    ActionErrors errors = new ActionErrors();
                    errors.add("", new ActionError("error.server.custom", "解密验签名错误！Error:" + SecX_Error));
                    saveErrors(request, errors);
                    request.setAttribute("IsSskk",Boolean.valueOf(false));
                    return (new ActionForward(mapping.getInput()));
                }
                System.out.println("============保存签名数据开始==============");
              try {
//                  dzyj = (DzyjsjVO) CAProxy.getInstance().saveSignedData(ud,
//                        strOrginData, signData,
//                            codeConstants.YWDM_SB_WS_ZHSB, ywuid,
//                            codeConstants.YWCZLX_DELETE);
                  System.out.println("=签名=yh：" + ud.yhid + " lsh:" + dzyj.getLsh());

              }
              catch (Exception ex) {
                ex.printStackTrace();

                throw ExceptionUtil.getBaseException(ex);
              }
              System.out.println("============保存签名数据结束==============");
            }
            //ended   added by qianchao 2005-11-24


            VOPackage vo = new VOPackage();
            vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
            vo.setAction(ActionConstant.INT_ACTION_DELETE);
            HashMap map = new HashMap(3);
            map.put(ZhsbMapConstant.SBBH, sbbh);
            map.put(ZhsbMapConstant.JKSH, jksh);
            map.put("WSSB_SWJGZZJGDM", FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());
            vo.setData(map);

            ShenbaoProxy.getInstance().process(vo);

            HashMap jksMap = (HashMap)request.getSession().getAttribute(SessionKey.JKS);
            if(jksh.trim().length() != 0)
            {
                ((HashMap)jksMap.get(sbbh)).remove(jksh); //如果按缴款书作废，则清除该缴款书
                if(((HashMap)jksMap.get(sbbh)).size() == 3) //如果该申报编号的数据为空，则清空该申报编号
                    jksMap.remove(sbbh);
            }
            else
            {
                jksMap.remove(sbbh);  //如果按照申报编号作废，则清除申报编号数据
            }
            request.setAttribute("IsSskk",Boolean.valueOf(false));
            return mapping.findForward("ListJks");
        }
        catch (Exception ex)
        {
            if (ud.getCaflag() && (dzyj != null))
            {
                System.out.println("============出错删除签名开始=============="
                        + dzyj.getLsh());
                try
                {
                    CAProxy.getInstance().deleteSignedData(dzyj);
                }
                catch (Exception e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("============出错删除签名结束=============="
                        + dzyj.getLsh());
            }
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public ActionForward doReturn(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws BaseException
    {
        request.getSession().removeAttribute(SessionKey.JKS);
        request.getSession().removeAttribute(SessionKey.YPDS_MAP);
        return mapping.findForward("Return");
    }

    public ActionForward doListJks(ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws BaseException
    {

        //////////////////////////////////////////////
/*        try
        {
            HashMap jksMap = (HashMap)request.getSession().getAttribute("_JKS");

            java.util.Set sbbhSet = jksMap.keySet();
            java.util.Iterator sbbhIter = sbbhSet.iterator();
            while(sbbhIter.hasNext())
            {
                String sbbh = (String)sbbhIter.next();
                HashMap aDeclaration = (HashMap)jksMap.get(sbbh);
                System.out.println("sbrq: " + aDeclaration.get("sbrq"));
                System.out.println("sklx: " + aDeclaration.get("sklx"));

                java.util.Set jkshSet = aDeclaration.keySet();
                java.util.Iterator jkshIter = jkshSet.iterator();
                while(jkshIter.hasNext())
                {
                    String jksh = (String)jkshIter.next();
                    if (jksh.length() != 16 && jksh.length() != 15)
                        continue;

                    String hjje = ((java.util.Map)aDeclaration.get(jksh)).get("jehj").toString();
                    System.out.println("jksh: " + jksh);
                    System.out.println("hjje: " + hjje);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
*/
        ///////////////////////////////////////////////////
        request.setAttribute("IsSskk",Boolean.valueOf(false));
        request.getSession().removeAttribute(SessionKey.YPDS_MAP);
        request.getSession().removeAttribute(SessionKey.YPDS_LIST);
        return mapping.findForward("ListJks");
    }


    private List getPrintList(List ypdsList,HttpServletRequest request)
    {
        Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
    	List tempPrintList = new ArrayList();
    	for(int i=0;i<ypdsList.size(); i++)
    	{
    	   List tempList = (List)ypdsList.get(i);
    	   for(int j=0;j<tempList.size(); j++)
    	   {
        	   Sbjkmx mx = (Sbjkmx)tempList.get(j);
        	   tempPrintList.add(mx);
    	   }
    	}
    	String szdm = "";
    	List szdmList = new ArrayList();
    	for(int i=0; i<tempPrintList.size(); i++)
    	{
     	   Sbjkmx mx = (Sbjkmx)tempPrintList.get(i);
     	   String tempSzdm = mx.getSzsmdm().substring(0,2);
     	   if(!szdm.equals(tempSzdm))
     	   {
     		   szdmList.add(tempSzdm);
     	   }
     	   szdm = tempSzdm;
    	}
    	List retList = new ArrayList();
    	for(int i=0; i<szdmList.size(); i++)
    	{
    		String szdmStr = (String)szdmList.get(i);
    		String szmc = null;
    		java.math.BigDecimal sjje= new java.math.BigDecimal(0.00);
    		String nsxm = "";
    		for(int j=0; j < tempPrintList.size(); j++)
    		{
    			Sbjkmx mx = (Sbjkmx)tempPrintList.get(j);
    			String tempSzdm = mx.getSzsmdm().substring(0,2);
    			System.out.println("tempSzdm == : " + tempSzdm);
    			if(szdmStr!=null&&szdmStr.equals(tempSzdm))
    			{
    				sjje=sjje.add(mx.getSjse());
                    Szsm szsmTmp = (Szsm)szsmMap.get(tempSzdm);
    				nsxm = szdmStr + " " + szsmTmp.getSzsmmc();
    			}
    		}
    		Map printData = new HashMap();
    		printData.put("NSXM",nsxm);
    		printData.put("SJJE",sjje);
    		retList.add(printData);
    	}
    	return retList;
    }

    public ActionForward doPrint(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws BaseException
    {
        ListJksForm myForm = (ListJksForm)form;
        String sbbh = myForm.getSbbhIndex();
        System.out.println("sbbh=========="+sbbh);
        HttpSession session = request.getSession();
        if(session.getAttribute(SessionKey.YPDS_MAP) == null)
        {
            try
            {
                VOPackage vo = new VOPackage();
                vo.setAction(ActionConstant.INT_ACTION_PRINT);
                vo.setProcessor(ProcessorNames.ZHSB_PROCESSOR);
                HashMap map = new HashMap(1);
                map.put(ZhsbMapConstant.SBBH, sbbh);
                map.put("WSSB_SWJGZZJGDM", FriendHelper.getSWDJJBSJ(request).getSwjgzzjgdm());
                vo.setData(map);
                HashMap ypdsMap = (HashMap)ShenbaoProxy.getInstance().process(vo);
            	List ypdsList = (List)ypdsMap.get(ZhsbMapConstant.SBSJ);
                List szList = this.getPrintList(ypdsList,request);
                for(int i = 0; i < szList.size(); i++)
                {
                    Map szMap = (Map)szList.get(i);
                    System.out.println("项目＝＝＝＝＝＝"+szMap.get("NSXM"));
                    System.out.println("合计＝＝＝＝＝＝"+szMap.get("SJJE"));
                }
                session.setAttribute(SessionKey.YPDS_MAP, ypdsMap);
                session.setAttribute(SessionKey.YPDS_LIST,szList);
            }
            catch(Exception ex)
            {
                throw ExceptionUtil.getBaseException(ex);
            }
        }
        return mapping.findForward("PrintYpds");
    }
}

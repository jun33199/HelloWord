package com.ttsoft.bjtax.shenbao.gzsx.web;

//import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.dao.GzsxDbAccess;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 告知事项action</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class GzsxAction extends ShenbaoAction
{
    //告知事项的正常标记
    private static final String IS_ZC = "0";

    //是否有非正常信息的标记
    private static final String HAS_FZC = "1";

    public GzsxAction()
    {
    }

    /**
     * 从后台取出告知事项信息,设置form,调转到告知事项页面
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
        throws BaseException
    {
        try
        {
            GzsxForm myForm = (GzsxForm)form;
//            String jsjdm = getUserData(request).yhid;
            String jsjdm = request.getRemoteUser();
            myForm.setJsjdm(jsjdm);
			ServiceProxy sp = new ServiceProxy();
			Map djMap = sp.getDjInfo(jsjdm);
			SWDJJBSJ jbsj = (SWDJJBSJ) djMap.get("JBSJ");
			myForm.setNsrmc(jbsj.getNsrmc());
            List gzsxData = (List)getGzsxList(jsjdm);
			transferGzsxInfo(gzsxData, myForm,request);

            return mapping.findForward("Show");
        }
        catch(Exception e)
        {
            throw ExceptionUtil.getBaseException(e);
        }
    }

    //从后台取出告知事项列表数据
    private List getGzsxList(String jsjdm) throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setData(jsjdm);
        vo.setProcessor(ProcessorNames.GZSX_PROCESSOR);
        vo.setAction(ActionConstant.INT_ACTION_QUERY);
        return(List)ShenbaoProxy.getInstance().process(vo);
    }

    //根据是否非正常标记将告知事项分成正常告知和非正常告知
	private void transferGzsxInfo(List gzsxList, GzsxForm form,HttpServletRequest request) {
		// DateFormat format = DateFormat.getDateInstance(DateFormat.MEDIUM);
		String bt = new String();
		String temp = new String();

		for (int i = 0, m = gzsxList.size(); i < m; i++) {
			Gzsx gz = (Gzsx) gzsxList.get(i);
			String nr=gz.getGzsxnr().replaceAll(" ", "").replaceAll("\t", "").replaceAll("\r\n", "<br>");
			if (gz.getGzsxnr().length() < 20) {
				temp = gz.getGzsxnr();
			} else {
				temp = gz.getGzsxnr().substring(0, 20);
			}
			
			gz.setGzsxnr(form.getJsjdm()+"$"+gz.getGzsx_id()+"$"+form.getNsrmc());
			gz.setGzsxnrbt(temp);
			request.getSession().setAttribute(gz.getGzsx_id()+"-gzsx", nr);
            //添加正常的告知信息
            if(gz.getFzcbs().equals(IS_ZC))
            {
                form.getZcGzList().add(gz);
            }
            //添加异常的告知信息
            else
            {
                form.getFzcGzList().add(gz);
            }
        }
        //如果有非正常信息,设置form的有非正常信息的属性
        if(form.getFzcGzList().size() != 0)
        {
            form.setHasFzcInfo(HAS_FZC);
        }
    }
	// 2009.4.2wcl修改，目的在点查看告知标题连接走的流程
	public ActionForward doCknr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxForm myForm = (GzsxForm) form;

			String[] gzsxnr = myForm.getGzsxnr().split("\\$");
			//System.out.println(myForm.getGzsxnr()+"-------------------");
			//System.out.println(gzsxnr+"-------------------");
			request.setAttribute("gzsx_id", gzsxnr[1]+"-gzsx");
			// 在这里做保存告知时间的操作
			// 2009.4.2.wcl增加。纳税人阅读标识的存储
			VOPackage vo = new VOPackage();
			GzsxForm gf = (GzsxForm) form;
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			gf.setYdsj(format.format(new Date(System.currentTimeMillis())));
			gf.setFksj(new Date(System.currentTimeMillis()));
			Map map = new HashMap();
			map.put("jsjdm", gzsxnr[0]);
			map.put("gzsx_id", gzsxnr[1]);
			map.put("ydsj", gf.getYdsj());
			map.put("nsrmc", gzsxnr[2]);
			map.put("fksj", gf.getFksj());
			vo.setData(map);
		//	System.out.println("阅读时间保存---------------------");
			vo.setProcessor(ProcessorNames.GZSXFK_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_YDSJ);
			ShenbaoProxy.getInstance().process(vo);

			// request.setAttribute(mapping.getAttribute(), myForm);
			return mapping.findForward("Gzsxnr");
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	// 2009.4.2wcl修改，目的在点查看告知标题连接走的流程
	public ActionForward dofanhui(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			return doShow(mapping,form,request,response);
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
}
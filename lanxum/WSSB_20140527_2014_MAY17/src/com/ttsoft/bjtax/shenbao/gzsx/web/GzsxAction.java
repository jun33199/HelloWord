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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��֪����action</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class GzsxAction extends ShenbaoAction
{
    //��֪������������
    private static final String IS_ZC = "0";

    //�Ƿ��з�������Ϣ�ı��
    private static final String HAS_FZC = "1";

    public GzsxAction()
    {
    }

    /**
     * �Ӻ�̨ȡ����֪������Ϣ,����form,��ת����֪����ҳ��
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

    //�Ӻ�̨ȡ����֪�����б�����
    private List getGzsxList(String jsjdm) throws Exception
    {
        VOPackage vo = new VOPackage();
        vo.setData(jsjdm);
        vo.setProcessor(ProcessorNames.GZSX_PROCESSOR);
        vo.setAction(ActionConstant.INT_ACTION_QUERY);
        return(List)ShenbaoProxy.getInstance().process(vo);
    }

    //�����Ƿ��������ǽ���֪����ֳ�������֪�ͷ�������֪
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
            //��������ĸ�֪��Ϣ
            if(gz.getFzcbs().equals(IS_ZC))
            {
                form.getZcGzList().add(gz);
            }
            //����쳣�ĸ�֪��Ϣ
            else
            {
                form.getFzcGzList().add(gz);
            }
        }
        //����з�������Ϣ,����form���з�������Ϣ������
        if(form.getFzcGzList().size() != 0)
        {
            form.setHasFzcInfo(HAS_FZC);
        }
    }
	// 2009.4.2wcl�޸ģ�Ŀ���ڵ�鿴��֪���������ߵ�����
	public ActionForward doCknr(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		try {
			GzsxForm myForm = (GzsxForm) form;

			String[] gzsxnr = myForm.getGzsxnr().split("\\$");
			//System.out.println(myForm.getGzsxnr()+"-------------------");
			//System.out.println(gzsxnr+"-------------------");
			request.setAttribute("gzsx_id", gzsxnr[1]+"-gzsx");
			// �������������֪ʱ��Ĳ���
			// 2009.4.2.wcl���ӡ���˰���Ķ���ʶ�Ĵ洢
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
		//	System.out.println("�Ķ�ʱ�䱣��---------------------");
			vo.setProcessor(ProcessorNames.GZSXFK_PROCESSOR);
			vo.setAction(ActionConstant.INT_ACTION_YDSJ);
			ShenbaoProxy.getInstance().process(vo);

			// request.setAttribute(mapping.getAttribute(), myForm);
			return mapping.findForward("Gzsxnr");
		} catch (Exception e) {
			throw ExceptionUtil.getBaseException(e);
		}
	}
	// 2009.4.2wcl�޸ģ�Ŀ���ڵ�鿴��֪���������ߵ�����
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
package com.ttsoft.bjtax.shenbao.szsm.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.action.ShenbaoAction;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.client.JkInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sklx;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * ˰��˰Ŀaction
 * @author Ding Chenggang
 * @version 1.0
 */
public class SzsmAction extends ShenbaoAction
{

    public SzsmAction()
    {
    }

    protected int getActionID()
    {
        return SbzlAccess.SZSM;
    }

    /**
     * ��ʾ
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request Request
     * @param response Response
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
            SzsmForm myForm = (SzsmForm)form;
            
        	String sklxdm = "";
        	sklxdm = (String)myForm.getSbsybs();
        	UserData ud = (UserData)this.getUserData(request);
        	SzsmInfoHelper.putSzsmInfoIntoSession(request,ud,sklxdm);

            SzsmHelper helper = new SzsmHelper(getUserData(request));

            SWDJJBSJ djsj = FriendHelper.getSWDJJBSJ(request);

            // ȡ��˰��˰Ŀ����Ϣ
            myForm.setFavoriteSzsm((List)request.getSession().getAttribute("favoriteSzsm"));
            myForm.setToBeFilterdSzsm((List)request.getSession().getAttribute("toBeFilterszsm"));
            myForm.setToBeAlertSzsm((List)request.getSession().getAttribute("toBeAlertszsm"));
            myForm.setWtdzszsm((List)request.getSession().getAttribute("wtdzszsm"));

            List jkInfor = helper.getJkInfor(djsj);
            List wtdwInfor = helper.getWtdwInfor(djsj); //ί�д��������ۡ����ۡ��ල���۵�λ�϶����
            
            completeJkInfor(jkInfor, request);
            Map codeMap = CodeTableUtil.getCodeTableMap(request,CodeTable.SKLX_MAP);
            Sklx zcjk_sklx = (Sklx)codeMap.get(CodeConstant.SKLXDM_ZCJK);//�����ɿ�
            Sklx sdjj_sklx = (Sklx)codeMap.get(CodeConstant.SKLXDM_SDJJ);//�Ĵ����
            
            if(zcjk_sklx == null || zcjk_sklx.getSbsybs() == null || !zcjk_sklx.getSbsybs().equals("1"))
            {
            	throw ExceptionUtil.getBaseException(new Exception("˰������\"�����ɿ�\"����"));
            }
            if(sdjj_sklx == null || sdjj_sklx.getSbsybs() == null || !sdjj_sklx.getSbsybs().equals("2"))
            {
            	throw ExceptionUtil.getBaseException(new Exception("˰������\"ί�д���\"����"));
            }
            
            myForm.setJkInfor(jkInfor);
            myForm.setWtdwInfor(wtdwInfor);
            request.setAttribute("szsmForm",myForm);
           
            return mapping.findForward("Show");
        }
        catch(Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
    }
    
    
	/**
	 * ���ҳ��˰������ʱִ�и÷������л�˰�����ͼ����������ʾ��
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws BaseException
	 */
	public ActionForward doChangeSKLX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws BaseException {
		this.doShow(mapping, form, request, response);
		return mapping.findForward("Show");
	}

    void completeJkInfor(List jkInfor, HttpServletRequest request)
    {
        try
        {
            Map szsmMap = CodeTableUtil.getCodeTableMap(request, CodeTable.SZSM_MAP);
            for(Iterator i = jkInfor.iterator(); i.hasNext(); )
            {
                JkInfor jk = (JkInfor)i.next();

                Szsm szsm = (Szsm)szsmMap.get(jk.getSzsmdm());

                if (szsm == null) continue;

                jk.setSzsmmc(szsm.getSzsmmc());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
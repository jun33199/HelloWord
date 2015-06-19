package com.ttsoft.bjtax.smsb.zhsb.web;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.proxy.ZhsbProxy;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import java.util.List;
/**
 * <p>Title: ������˰��������ϵͳ-�����걨--�걨��һ�´���</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Ha Zhengze</p>
 *
 * @author Ha Zhengze
 * @version 1.0  
 */
public class SbbyzclAction extends SmsbAction {
    /**
     <forward name="Show" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryDj" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QuerySb" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="MoveA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="MoveB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="SelectMoveA" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="SelectMoveB" path="/webapp/smsb/sbbyzcl001.jsp" />
      <forward name="QueryMoveA" path="/webapp/smsb/sbbyzcl002.jsp" />
      <forward name="QueryMoveB" path="/webapp/smsb/sbbyzcl002.jsp" />
     */

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param response  The HTTP response we are creating
     */
    protected void initialRequest(ActionMapping mapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse response)

    {
        super.initialRequest(mapping, actionForm, httpServletRequest, response);
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_POSITION,
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨��ⲻһ�´���</font>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�걨��ⲻһ�´���");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "");
    }

    /**
     * ��ʼ��
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doShow(ActionMapping actionMapping,
                                ActionForm actionForm,
                                HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doShow().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            ///3.0.���õ�ǰ�����Ϊ��ѯ���
            pf.setQueryNd(String.valueOf(TinyTools.getYear(new Date())));
            ///3.1.���ò�ѯ����
            pf.setQueryKsrq(String.valueOf(TinyTools.getYear(new Date())) +
                            "0101");
            pf.setQueryJsrq(String.valueOf(TinyTools.getYear(new Date())) +
                            "1231");
            ///3.2.������������
            pf.setCzr(ud.yhid);
            pf.setSwjgzzjgdm(ud.ssdwdm);
            pf.setSwjgzzjgmc(ud.ssdwmc);
            pf.setRq(TinyTools.Date2String(new Date()));
            pf.setOpeFlag("0");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("Show");
    }

    /**
     * ��ѯ�Ǽ���Ϣ
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryDj(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQueryDj().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(1);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QueryDj");
    }

    /**
     * ��ѯ�걨��Ϣ
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQuerySb(ActionMapping actionMapping,
                                   ActionForm actionForm,
                                   HttpServletRequest httpServletRequest,
                                   HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQuerySb().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(2);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
            //pf=this.getTestData(pf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QuerySb");
    }

    /**
     * QueryA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryA(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQueryA().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(3);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QueryA");
    }

    /**
     * QueryB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryB(ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQueryB().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(4);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QueryB");
    }

    /**
     * MoveA,תǷ˰
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doMoveA(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doMoveA().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(5);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("MoveA");
    }

    /**
     * MoveB,ת�ظ��걨
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doMoveB(ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doMoveB().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(6);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("MoveB");
    }

    /**
     * SelectMoveA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doSelectMoveA(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doSelectMoveA().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(7);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("SelectMoveA");
    }

    /**
     * SelectMoveB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doSelectMoveB(ActionMapping actionMapping,
                                       ActionForm actionForm,
                                       HttpServletRequest httpServletRequest,
                                       HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doSelectMoveB().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(8);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("SelectMoveB");
    }

    /**
     * QueryMoveA
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryMoveA(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQueryMoveA().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(9);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QueryMoveA");
    }

    /**
     * QueryMoveB
     * @param actionMapping  The ActionMapping used to select this instance
     * @param actionForm  The optional ActionForm bean for this request (if any)
     * @param httpServletRequest  The HTTP request we are processing
     * @param httpServletResponse  The HTTP response we are creating
     * @throws BaseException
     * @return    ActionForward
     */
    public ActionForward doQueryMoveB(ActionMapping actionMapping,
                                      ActionForm actionForm,
                                      HttpServletRequest httpServletRequest,
                                      HttpServletResponse httpServletResponse) throws
            BaseException {
        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        /**
         * @todo doQueryMoveB().����������
         */
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(10);
            vo.setUserData(ud);
            vo.setData(pf);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            pf = (SbbyzclForm) ZhsbProxy.getInstance().process(vo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("QueryMoveB");
    }

    private SbbyzclForm getTestData(SbbyzclForm pf){
        List dataList=pf.getDataList();
        List mxList=null;
        SingleSbInfo ssi=null;
        SbInfo si=null;
        for(int i=0;i<2;i++){
            ssi=new SingleSbInfo();
            mxList=ssi.getMxList();
            ssi.setSbbh("�걨���"+i);
            ssi.setSklx("˰������"+i);
            ssi.setSbrq("�걨����"+i);
            ssi.setRkje("�����"+i);
            ssi.setCe("���"+i);
            ssi.setBz("��ע"+i);
            for(int j=0;j<2;j++){
                si=new SbInfo();
                si.setSzdm("˰�ִ���"+j);
                si.setSzmc("˰������"+j);
                si.setSkssksrq("˰��������ʼ����"+j);
                si.setSkssjsrq("˰��������������"+j);
                si.setSjje("ʵ�ɽ��"+j);
                si.setRkje("�����"+j);
                si.setCe("���"+j);
                si.setBz("��ע"+j);
                mxList.add(si);
            }
            dataList.add(ssi);
        }
        return pf;
    }

    //  ��ӡ�걨�� added by zhangyj 20070720
    public ActionForward doDetail(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) throws BaseException {

        //0.�������
        UserData ud = null;
        SbbyzclForm pf = null;
        VOPackage vo = new VOPackage();
        //1.����������
        try {
            //2.��ʼ��
            pf = (SbbyzclForm) actionForm;
            ud = this.getUserData(httpServletRequest);
            //3.ҵ������
            vo.setAction(11);
            vo.setUserData(ud);
    		HashMap dataMap = new HashMap();
    		dataMap.put("sbbh", httpServletRequest.getParameter("sbbh"));
    		dataMap.put("jsjdm", httpServletRequest.getParameter("jsjdm"));
    		dataMap.put("ksrq", httpServletRequest.getParameter("ksrq"));
    		dataMap.put("jsrq", httpServletRequest.getParameter("jsrq"));
    		dataMap.put("nd", httpServletRequest.getParameter("nd"));
    		pf.setQueryJsjdm(httpServletRequest.getParameter("jsjdm"));
    		vo.setData(dataMap);
            vo.setProcessor(CodeConstant.SBBYZZCL_PROCESSOR);
            HashMap retMap = (HashMap) ZhsbProxy.getInstance().process(vo);
            
			List mxList = (List) retMap.get("mxList");
			Sbjkzb zb = (Sbjkzb) retMap.get("sbjkzb");
			pf.setMxList(mxList);
			pf.setZb(zb);
			BigDecimal hj = new BigDecimal(0);
			for (int i = 0; i < mxList.size(); i++) {
				Sbjkmx mx = (Sbjkmx) mxList.get(i);
				mx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", mx
						.getSzsmdm()));
				hj = hj.add(mx.getSjse());
			}
			pf.setHj(hj);
			if (zb.getSjly().equals(CodeConstant.SMSB_SJLY_ZRRLR)) {
				ZRRJBSJ zrrJbsj = InterfaceDj.getZRRJBSJ(httpServletRequest.getParameter("jsjdm"));
				pf.setNsrmc(zrrJbsj.getNsrmc());
			} else {
				SWDJJBSJ dj = (SWDJJBSJ) InterfaceDj.getJBSJ(httpServletRequest.getParameter("jsjdm"));
				pf.setNsrmc(dj.getNsrmc());
			}
			httpServletRequest.setAttribute("sbbyzclForm", pf);            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
        //4.�����ڴ����
        httpServletRequest.setAttribute(actionMapping.getAttribute(), pf);
        //99.����ֵ
        return actionMapping.findForward("Detail");    	
	}
    //end
}

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.ttsoft.bjtax.sfgl.common.action.SmsbAction;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.print.web.JksPrintForm;
import com.ttsoft.bjtax.smsb.proxy.SbzlProxy;
import com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.ExcelParser;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DsdzdkAction
    extends SmsbAction
{

    //������Դ ¼��
    public final static String SMSB_SJLY_LR = "0";

    //������Դ ����
    public final static String SMSB_SJLY_DR = "1";

    private UserData userData = null;

    /**
     * ������ǰ�ô�������ÿ�ν���ҳ�涼�ᱻ����<BR>
     * ����ҳ����ʾʱʹ�õĵ�����Ϣ�ͱ���
     * @param mapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
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
                                        "<font color=\"#A4B9C6\">�ۺϷ��������Ϣϵͳ</font>&gt;<font color=\"#7C9AAB\">�걨����¼��</font>&gt;�������۴��ɱ�</td>");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_TITLE,
                                        "�������۴��ɱ�");
        httpServletRequest.setAttribute(CodeConstant.SMSB_HEADER_HELP,
                                        "help/smsb/sbzl/dsdzdk/dsdzdk-000.htm");

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *         voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SHOWACTION
     * 	����SbzlProxy��process������
     * 4������doShow��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doShow (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //��ǰ��ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());

        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();

        //���ú�̨����Actionֵ---SHOWACTION
        vo.setAction(CodeConstant.SMSB_SHOWACTION);

        //�����������Data����,������DsdzdklrForm
        vo.setData(sdForm);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor����---DsdzdkProcessor
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //����Proxy����ʼ��DsdzdklrForm��ֵ
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //��ת
            return actionMapping.findForward("Show");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *         voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_QUERYACTION
     * 	����SbzlProxy��process������
     * 4������doQuery��������ȡҵ�����ݵĽ����
     * 5�����ݵõ���zsfs(���շ�ʽ)����ֵ��������תҳ���Լ�ҵ���쳣�����׳�
     * 6��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *         ApplicationException ҵ���쳣�����׳�
     */

    public ActionForward doQuery (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //��ǰ��ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);

        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //���ú�̨����Actionֵ----QUERYACTION
        vo.setAction(CodeConstant.SMSB_QUERYACTION);

        //�����������Data����,������DsdzdklrForm
        vo.setData(sdForm);

        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //����Proxy����ʼ��DsdzdkForm��ֵ
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //��ת
            return actionMapping.findForward("Query");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * ¼�봦��
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    // ¼�봦��
    public ActionForward doInput (ActionMapping actionMapping,
                                  ActionForm actionForm,
                                  HttpServletRequest httpServletRequest,
                                  HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //��ǰ��ActionForm---DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);

        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        vo.setUserData(userData);
        //���ú�̨����Actionֵ----QUERYACTION
        vo.setAction(CodeConstant.SMSB_INPUTACTION);

        //�����������Data����,������DsdzdklrForm
        vo.setData(sdForm);

        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        try
        {
            //����Proxy����ʼ��DsdzdkForm��ֵ
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            //��ת
            return actionMapping.findForward("Input");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �ļ��ϴ�����
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    // �ļ��ϴ�����
    public ActionForward doUpload (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ----------------------------------------
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            DsdzdkForm sdForm = (DsdzdkForm) form;
            sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setSjly(SMSB_SJLY_DR);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_UPLOADACTION);
        vo.setData(sdForm);
        vo.setUserData(userData);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");

        try
        {
            //����processor
            //sdForm = (DsdzdkForm)SbzlProxy.getInstance().process(vo);

            // ����ϴ��ļ���������ϸ���ݵ��б�
            List mxList = new ArrayList();

            // EXCEL�ļ�������
            ExcelParser excelParser = new ExcelParser();
            java.io.InputStream inFile;
            try
            {
                inFile = sdForm.getExcelFile().getInputStream();
            }
            catch (Exception ex)
            {
                throw new ApplicationException("ϵͳ�޷��ϴ�����Excel�ļ�!");
            }
            //�����ļ�
            mxList = excelParser.parseExcel(inFile);

            //����ϴ����ݵĺϷ���ͬʱ����ϼ���
            sum(mxList, sdForm);

            sdForm.setMxDataList(mxList);
            sdForm.setExcelFile(null);

            request.setAttribute(mapping.getAttribute(), sdForm);

            //���浼��Ľ�����Ự
            request.getSession().setAttribute(CodeConstant.
                                              SESSIONKEY_SDDATALIST,
                                              sdForm.getMxDataList());
            request.getSession().setAttribute("jsjehj",
                                              sdForm.getJsjehj());
            request.getSession().setAttribute("sjsehj",
                                              sdForm.getSjsehj());
            //��ҳ
            sdForm.setPgNum(1);
            List pgList = sdForm.getMxDataList();
            sdForm.setLength(CodeConstant.SD_PG_LENGTH);
            sdForm.setPgSum(pgList.size() % sdForm.getLength() == 0 ?
                            pgList.size() / sdForm.getLength() :
                            pgList.size() / sdForm.getLength() + 1);
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(mapping, request);
            sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));
            throw ExceptionUtil.getBaseException(ex);
        }

        return mapping.findForward("Upload");
    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *         voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_SAVEACTION
     * 	����SbzlProxy��process������
     * 4������doSave��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param actionMapping struts.action.ActionMapping
     * @param actionForm DsdzdkForm
     * @param httpServletRequest HttpServletRequest
     * @param httpServletResponse HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *
     */

    public ActionForward doSave (ActionMapping actionMapping,
                                 ActionForm actionForm,
                                 HttpServletRequest httpServletRequest,
                                 HttpServletResponse httpServletResponse)
        throws
        BaseException
    {

        //-------------�����ݿ������Method�����޸ģ���ֹˢ�»��ظ��ύ-------------
        ActionForward forward = doHandleToken(actionMapping, httpServletRequest);
        if (forward != null)
        {
            return forward;
        }

        //��ǰ��ActionForm---------DsdzdkForm
        DsdzdkForm sdForm = (DsdzdkForm) actionForm;

        userData = this.getUserData(httpServletRequest);
        sdForm.setLrr(userData.getYhid());
        sdForm.setExcelFile(null);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //����columns�е��ֶ���ȡ����Ҫǰ̨�б����ݸ���DsdzdkForm��DataList�У�
        sdForm.getMxDataList().clear();
        sdForm.getMxDataList().addAll(
            getValuesToList(httpServletRequest, sdForm.getColumns()));

        if (SMSB_SJLY_DR.equals(sdForm.getSjly()))
        {
            List dataList = new ArrayList();
            Iterator items = sdForm.getMxDataList().iterator();
            while (items.hasNext())
            {
                Map item = (Map) items.next();
                item.put("bdzrmc",
                         SfStringUtil.ISO2GBK( (String) item.get("bdzrmc")));
                item.put("szmc", SfStringUtil.ISO2GBK( (String) item.get("szmc")));
                item.put("szsmmc",
                         SfStringUtil.ISO2GBK( (String) item.get("szsmmc")));
                dataList.add(item);
                Debug.out(item);
            }
            sdForm.setMxDataList(dataList);
            Debug.out(sdForm.getMxDataList());
        }

        //��ʼ�����ݴ�������
        VOPackage vo = new VOPackage();
        //���ú�̨����Actionֵ-----SAVEACTION

        vo.setAction(CodeConstant.SMSB_SAVEACTION);

        //�����������Data����,�������������DsdzdkForm
        vo.setData(sdForm);
        vo.setUserData(userData);
        //����ProxyҪ���õ�processor������
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");

        try
        {
            //����Proxy����ʼ��DsdzdkForm��ֵ
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            httpServletRequest.setAttribute(actionMapping.getAttribute(),
                                            sdForm);
            return actionMapping.findForward("Save");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(actionMapping, httpServletRequest);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ȡҵ����Ϣ
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * 4������doDelete��������ȡҵ�����ݵĽ����
     * 5��ת��ҵ��ҳ�档
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     *
     */

    public ActionForward doDelete (ActionMapping mapping,
                                   ActionForm form,
                                   HttpServletRequest request,
                                   HttpServletResponse response)
        throws
        BaseException
    {

        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_DELETEACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //����processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Delete");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * �����걨���
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doHzsbjkd (ActionMapping mapping,
                                    ActionForm form,
                                    HttpServletRequest request,
                                    HttpServletResponse response)
        throws
        BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //��session��ȡ�õ������ϸ����
        sdForm.setMxDataList( (List) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_SDDATALIST));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_HZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //����processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Hzsbjkd");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * �����걨�����ӡ
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */

    public ActionForward doHzsbjkdp (ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        //��session��ȡ�õ������ϸ����
        sdForm.setMxDataList( (List) request.getSession().getAttribute(
            CodeConstant.SESSIONKEY_SDDATALIST));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_HZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //����processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Hzsbjkdp");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * ���������걨���
     * 1��ȡ��DsdzdkForm������һ��VOPackage��
     * 2��ȡҵ�����ݵĲ�����������ΪvoPackage��data��
     * 3������voPackage��Processor��Ϊ
     * 	com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor��
     *        voPackage��Data��ΪDsdzdkForm
     * 	voPackage��action��ΪCodeConstant.SMSB_DELETEACTION
     * 	����SbzlProxy��process������
     * @param mapping struts.action.ActionMapping
     * @param form DsdzdkForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return actionMapping.findForward����תĿ��
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doCxhzsbjkd (ActionMapping mapping,
                                      ActionForm form,
                                      HttpServletRequest request,
                                      HttpServletResponse response)
        throws
        BaseException
    {

        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null)
        {
            return forward;
        }

        DsdzdkForm sdForm = (DsdzdkForm) form;
        sdForm.setExcelFile(null);
        userData = this.getUserData(request);
        sdForm.setLrr(userData.getYhid());
        sdForm.setNsrmc(SfStringUtil.ISO2GBK(sdForm.getNsrmc()));

        VOPackage vo = new VOPackage();
        vo.setAction(CodeConstant.SMSB_CXHZSBJKDACTION);
        vo.setData(sdForm);
        vo.setProcessor(
            "com.ttsoft.bjtax.smsb.sbzl.dsdzdk.processor.DsdzdkProcessor");
        vo.setUserData(userData);
        try
        {
            //����processor
            sdForm = (DsdzdkForm) SbzlProxy.getInstance().process(vo);
            request.setAttribute(mapping.getAttribute(), sdForm);
            return mapping.findForward("Cxhzsbjkd");
        }
        catch (Exception ex)
        {
            //ϵͳ��׽�쳣�������쳣�����׳�
            sdForm.reset(mapping, request);
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     *  ��ҳ��ʾ
     *
     *  @param mapping struts.action.ActionMapping
     *  @param aForm DsdzdkForm
     *  @param request HttpServletRequest
     *  @param response HttpServletResponse
     *  @return actionMapping.findForward����תĿ��
     *  @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    public ActionForward doGotopage (ActionMapping mapping,
                                     ActionForm aForm,
                                     HttpServletRequest request,
                                     HttpServletResponse response)
        throws
        BaseException
    {
        try
        {
            DsdzdkForm yForm = (DsdzdkForm) aForm;
            yForm.setNsrmc(SfStringUtil.ISO2GBK(yForm.getNsrmc()));
            yForm.setMxDataList( (ArrayList) request.getSession().getAttribute(
                CodeConstant.SESSIONKEY_SDDATALIST));
            yForm.setJsjehj( (BigDecimal) request.getSession().getAttribute(
                "jsjehj"));
            yForm.setSjsehj( (BigDecimal) request.getSession().getAttribute(
                "sjsehj"));
            return mapping.findForward("Gotopage");
        }
        catch (Exception e)
        {
            Debug.out("--------Action-Gotopage----------" + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
    }

    /**
     * ����ϼƽ��
     * @param sdForm ��ǰ��ActionForm
     * @param mxList ��HashMapΪ�ڵ����ϸ��¼List
     * @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     */
    private void sum (List mxList, DsdzdkForm sdForm)
        throws BaseException
    {
        //��˰���ϼ�
        BigDecimal jsjeTotal = new BigDecimal(0.00);
        //ʵ�ɽ��ϼ�
        BigDecimal sjseTotal = new BigDecimal(0.00);

        for (int i = 0; i < mxList.size(); i++)
        {
            HashMap record = (HashMap) mxList.get(i);

            String jsje = (String) record.get("jsje"); //��˰���
            String sjse = (String) record.get("sjse"); //ʵ��˰��

            BigDecimal thisSjse = new BigDecimal(sjse);
            thisSjse = thisSjse.setScale(2, BigDecimal.ROUND_HALF_UP);
            sjseTotal = sjseTotal.add(thisSjse);

            BigDecimal thisJsje = new BigDecimal(jsje);
            thisJsje = thisJsje.setScale(2, BigDecimal.ROUND_HALF_UP);
            jsjeTotal = jsjeTotal.add(thisJsje);
        }

        sdForm.setJsjehj(jsjeTotal);
        sdForm.setSjsehj(sjseTotal);
    }

    /** doPrint
     *  @param mapping struts.action.ActionMapping
     *  @param form DsdzdkForm
     *  @param request HttpServletRequest
     *  @param response HttpServletResponse
     *  @return actionMapping.findForward����תĿ��
     *  @throws BaseException ϵͳ��׽�쳣�������쳣�����׳�
     * */
    public ActionForward doPrint (ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response)
        throws
        BaseException
    {
        Debug.out("--- Debug --- Here is GtgshWszlrAction"
                  + ".doDismiss");
        try
        {
            //��Ԥװ�ص���Ϣ���ݸ���һ��ҳ��
            JksPrintForm pf = new JksPrintForm();
            DsdzdkForm pf1 = (DsdzdkForm) form;
            pf.setHeadJkpzh(pf1.getHeadJkpzh());
            pf.setHeadJsjdm(pf1.getHeadJsjdm());
            pf.setHeadSjly(CodeConstant.SMSB_SJLY_SDHZ); //������Դ
            pf.setActionType("Show");
            request.setAttribute("jksPrintForm", pf);
        }
        catch (Exception ex)
        {
        }
        return mapping.findForward("Print");
    }

}
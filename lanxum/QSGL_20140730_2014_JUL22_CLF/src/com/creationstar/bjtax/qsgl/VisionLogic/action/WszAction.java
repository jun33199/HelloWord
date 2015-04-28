package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.QueryBaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QuerySbxxForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.QueryWszForm;
import com.creationstar.bjtax.qsgl.VisionLogic.form.ShskForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.QueryCache;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import java.sql.Connection;


/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author �Բ�
 * @version 1.0
 */
public class WszAction extends QueryBaseAction {
    /**
     * ������˰֤�Ĵ�ӡҳ��
     * �����ɡ��ٱ��桢����ӡ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doIntoPrint(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        //���ø��෽������Token.
        ActionForward forward = doHandleToken(mapping, request);
        if (forward != null) {
            return forward;
        }
        ArrayList wszList = new ArrayList();
        ArrayList mxList = new ArrayList();

        QueryWszForm wszForm = (QueryWszForm) form;

        HttpSession session = request.getSession(false);
        ShskForm shskForm = (ShskForm) session.getAttribute("shskForm");

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        QueryWszBo bo = new QueryWszBo();
        bo.setSbbh(shskForm.getSbbh());
        String jsjdm = shskForm.getJsjdm();
        bo.setJsjdm(jsjdm);
        bo.setNsrmc(ActionUtil.getNsrmc(shskForm.getNsrList(),
                                        shskForm.getXsjsjdm(), jsjdm));

        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CREATE_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        Qswszz wszZb = new Qswszz();
        Qswszmx wszMx = new Qswszmx();

        try {
            //������˰֤�����ؽ��
            wszList = (ArrayList) QsglProxy.getInstance().process(vo);
            //�����ص���˰֤���������QueryWszForm�Ա���˰֤��Ʊ��ӡ��ʱ��ʹ��
            wszForm.setWszList(wszList);
            //��������˰֤����������QueryWszForm�Ա���˰֤��Ʊ��ӡ��ʱ��ʹ��
            wszForm.setWszTotals(wszList.size());
            //����ǰ��ӡ���ǵ�һ����˰֤
            wszForm.setPrintPages(1);

            //�ȴ�ӡ��һ����˰֤
            wszZb = (Qswszz) wszList.get(0);
            wszMx = (Qswszmx) wszZb.getMxList().get(0);
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);

            vo.setAction(ActionType.PRINT_WSZ);
            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
            wszMx = bo.getMxVo();

            /*Debug.out("ȡ�õ���˰֤�Ľ�" + wszZb.getHjsjje());
             Debug.out("ת���е���˰֤�Ľ�" + DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
                             Debug.out("ת�������˰֤�Ľ�" + DataConvert.round(wszZb.getHjsjje(), 2).doubleValue());*/

            ////////////////////////////////
            //����˰��˰֤����˰�ɿ�������Ͻǿհ״���ӡ����ͬ��š���
            //����ȡ���걨�����еġ���ͬ��š���
            //zzb 20090731  begin
            Connection conn  = QSDBUtil.getConnection();//�������ݿ����ӣ�Ϊ�˲��Һ�ͬ���

            String sbbh = shskForm.getSbbh();
            System.out.println("============sbbh================="+ sbbh);
            //����һ��Sbzbʵ��sbzb1,����ʵ�����걨�������Ϊ��ǰ�걨���,Ȼ�󽫸�sbzb1��Ϊ
            //�������ݸ�SbzbDAO.java��get()���������Ҷ�Ӧ����걨��ż�¼�ĺ�ͬ���.
            Sbzb sbzb1 = new Sbzb();
            sbzb1.setSbbh(sbbh);
            Sbzb sbzb2 = (Sbzb)DAOFactory.getInstance().getSbzbDAO().get(sbzb1, conn);
            String htbh = sbzb2.getHtbh();
            if(htbh==null){
                System.out.println("================htbh=============null");
                htbh=" ";
            }else{
                System.out.println("================htbh============="+ htbh);
            }
            QSDBUtil.freeConnection(conn);//�ر����ݿ�����
            //���ӡ�ɿ���ҳ�洫�ͺ�ͬ���
            wszForm.setHtbh(htbh);
            //zzb 20090731  end
            /////////////////////////////////


            wszForm.setWszh(wszZb.getWszh());
            wszForm.setZb(wszZb.getNdzb());
            wszForm.setPzzldm(wszZb.getPzzldm());

            wszForm.setTfrq(DataConvert.TS2String(wszZb.getCjrq()));
            wszForm.setNsrmc(wszZb.getNsrmc());
            wszForm.setHtqdrq(DataConvert.TimeStamp2String(wszZb.getHtclrq()));
            wszForm.setFdcwz(wszZb.getFdcwz());
            wszForm.setDz(" ");
            wszForm.setJbr(wszZb.getCjr());
            wszForm.setNsrdm(wszZb.getNsrdm());

            mxList = wszZb.getMxList();

            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Qswszmx mx = (Qswszmx) iter.next();
                /** @todo ����Ժ��Ϊ��˰��˰Ŀ���������Ҫ���뵽������ */
                wszForm.setSzsmmc(mx.getSzsmmc());
                wszForm.setJsje(DataConvert.BigDecimal2String(mx.getJsje(), 2));
                wszForm.setSl(DataConvert.BigDecimal2String(mx.getSl()));
                wszForm.setSjse(DataConvert.BigDecimal2String(mx.getSjse(), 2));
                wszForm.setQszymj(DataConvert.BigDecimal2String(mx.getQszymj()));
                wszForm.setSkssksrq(DataConvert.TimeStamp2String(mx.getSkssksrq()));
                wszForm.setSkssjzrq(DataConvert.TimeStamp2String(mx.getSkssjsrq()));

            }
            StringBuffer sb = new StringBuffer("����˰���:��");
            sb.append(DataConvert.BigDecimal2String(wszZb.getJsje(), 2))
                    .append("  ��˰��ʽ:").append(wszZb.getJsfsmc());
            //�����˰֤�з�Ʊ�������Ҫ�ڱ�ע�аѷ�Ʊ����˰֤�Ŷ���ӡ����
            /*if(wszList.size() > 1)
                             {
                sb.append(" ��˰֤�ţ�");
                for(int i = 0; i < wszList.size(); i++ )
                {
                    sb.append(((Qswszz)wszList.get(i)).getWszh()).append(", ");
                }
                wszForm.setBz(sb.substring(0, (sb.length() - 2)) );
                             }
                             else    //���û�з�Ʊ��������ڱ�ע�в���Ҫ��ӡ��˰֤��
                             {
                wszForm.setBz(sb.toString() );
                             }*/
            wszForm.setBz(sb.toString());
            wszForm.setYqts(DataConvert.BigDecimal2String(wszZb.getYqts(), 0));
            wszForm.setZnj("��" +
                           DataConvert.BigDecimal2String(wszZb.getZnjzje(), 2));
            Debug.out("ת��ǰ�ĺϼ�ʵ�ɽ� " + wszZb.getHjsjje());
            wszForm.setJehj(DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
            Debug.out("ת����� " +
                      wszZb.getHjsjje().setScale(2, BigDecimal.ROUND_HALF_UP));
            wszForm.setJehj_dx(Currency.convert(wszZb.getHjsjje().setScale(2,
                    BigDecimal.ROUND_HALF_UP)));
            wszForm.setZsjg(wszZb.getSwjgzzjgmc());
            request.setAttribute(Constants.MESSAGE_KEY, "�Ѿ��������ֽɿ��飡");

            //�����Ӧ��ҳ������
            QuerySbxxForm aForm = (QuerySbxxForm) session.getAttribute(
                    "querySbxxForm");
            aForm.removeData(aForm.getViewIndex());
        } catch (Exception ex) {
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        }

        //�����ɵ����ݷŻ�ǰ̨
        //request.setAttribute(mapping.getAttribute(),wszForm);
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }


    /**
     * ���Ŵ�ӡ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doChange(ActionMapping mapping,
                                  ActionForm form,
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
            Exception {
        QueryWszForm wszForm = (QueryWszForm) form;

        ArrayList wszList = wszForm.getWszList();
        Qswszz wszZb = (Qswszz) wszList.get(wszForm.getPrintPages() - 1);
        wszZb.setWszh(wszForm.getWszh());
        wszZb.setNdzb(wszForm.getZb());

        HttpSession session = request.getSession(false);
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        QueryWszBo bo = new QueryWszBo();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CHANGE_WSZH_PRINT);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            bo.setQswszzVo(wszZb);
            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }

        wszForm.setZb(wszZb.getNdzb()); //����ֱ�
        wszForm.setWszh(wszZb.getWszh()); //��˰֤��
        wszForm.setPzzldm(wszZb.getPzzldm()); //pzzldm
        request.setAttribute(Constants.MESSAGE_KEY, "���ֽɿ����Ѿ����ųɹ���");

        return mapping.findForward("show");
    }

    /**
     * ��ҳ��ͨ����˰֤�Ż����˰֤����Ϣ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doGet(ActionMapping mapping,
                               ActionForm form,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryWszForm queryWszForm = (QueryWszForm) form;
        QueryWszBo bo = (QueryWszBo) queryWszForm.getData();

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.GET);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            /** @todo ����̨���ص�������ʾ��ǰ̨ҳ�� */
            Qswszz wszZb = (Qswszz) QsglProxy.getInstance().process(vo);

            if (wszZb != null && wszZb.getMxList() != null &&
                wszZb.getMxList().size() > 0) {
                ArrayList aList = new ArrayList();
                bo.setQswszzVo(wszZb);
                bo.setResultList(wszZb.getMxList());
                aList.add(bo);
                QueryCache cache = new QueryCache(aList,
                                                  this.getUserData().myxszds);
                queryWszForm.setQueryCache(cache);
                //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
                queryWszForm.setStatus("Result");
                request.setAttribute(Constants.MESSAGE_KEY, "�Ѿ��ӿ��л������Ҫ�����ֽɿ��飡");
            } else {
                QueryCache cache = queryWszForm.getQueryCache();
                cache.removeAll();
                queryWszForm.setStatus("Query");
                request.setAttribute(Constants.MESSAGE_KEY, "û�з��ϲ�ѯ���������ֽɿ��飡");
            }
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ������˰֤
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doCxWsz(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        QueryWszForm queryWszForm = (QueryWszForm) form;
        QueryWszBo bo = (QueryWszBo) queryWszForm.getData();

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CX_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));
        try {
            QsglProxy.getInstance().process(vo);
            QueryCache cache = queryWszForm.getQueryCache();
            cache.removeAll();
            queryWszForm.setStatus("Query");
            request.setAttribute(Constants.MESSAGE_KEY, "�Ѿ������˸����ֽɿ��飡");
        } catch (Exception ex) {
            Debug.printException(ex);
            QueryCache cache = queryWszForm.getQueryCache();
            cache.removeAll();
            queryWszForm.setStatus("Query");
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("show");
        }
        // ����Token;
        saveToken(request);
        return mapping.findForward("show");
    }

    /**
     * ��ӡ��˰֤�������˰֤����Ĵ�����״̬
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doUpdateState(ActionMapping mapping,
                                       ActionForm form,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        Qswszz wszVo = new Qswszz();
        QueryWszForm wszForm = (QueryWszForm) form;

        wszVo.setWszh(wszForm.getWszh());
        wszVo.setNdzb(wszForm.getZb());
        wszVo.setPzzldm(wszForm.getPzzldm());

        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        VOPackage vo = new VOPackage();
        vo.setData(wszVo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.CHANGE_WSZ_STATUS);
        vo.setProcessor(prop.getProperty(wszVo.getClass().getName()));
        wszForm.setZb("");
        session.setAttribute(mapping.getAttribute(), wszForm);

        try {
            QsglProxy.getInstance().process(vo);
        } catch (Exception ex) {
            Debug.printException(ex);
        }
        //�����˰֤��һ��û�з�Ʊ��������߷�Ʊ����˰֤��ȫ����ӡ�����˳���ӡ
        if (wszForm.wszTotals == 1 || wszForm.printPages == wszForm.wszTotals) {
            return mapping.findForward("return");
        } else { //����з�Ʊ�������Ҫ������ӡ�������˰֤
            return doContinuePrint(mapping, form, request, response);
        }
    }

    /**
     * ��˰֤��Ʊ�Ĵ�ӡ
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doContinuePrint(ActionMapping mapping,
                                         ActionForm form,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        QueryWszForm wszForm = (QueryWszForm) form;
        //���õ�ǰ��ӡ���ǵڼ�����˰֤
        wszForm.setPrintPages(wszForm.getPrintPages() + 1);

        ArrayList wszList = wszForm.getWszList();
        Qswszz wszZb = (Qswszz) wszList.get(wszForm.getPrintPages() - 1);
        Qswszmx wszMx = (Qswszmx) wszZb.getMxList().get(0);

        HttpSession session = request.getSession(false);
        //��ȡ�����ServletContext�е�processor-map.properties������
        Properties prop = (Properties) session.getServletContext().
                          getAttribute(Constants.PROCESSOR_MAP_FILE_NAME);

        //�����̨��Ҫ���������ݶ���
        QueryWszBo bo = new QueryWszBo();
        VOPackage vo = new VOPackage();
        vo.setData(bo);
        vo.setUserData(this.getUserData());
        vo.setAction(ActionType.PRINT_WSZ);
        vo.setProcessor(prop.getProperty(bo.getClass().getName()));

        try {
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);

            bo = (QueryWszBo) QsglProxy.getInstance().process(vo);
            wszZb = bo.getQswszzVo();
            wszMx = bo.getMxVo();
        } catch (Exception ex) {
            Debug.printException(ex);
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("return");
        }

        wszForm.setWszh(wszZb.getWszh());
        wszForm.setZb(wszZb.getNdzb());
        wszForm.setPzzldm(wszZb.getPzzldm());

        ArrayList mxList = wszZb.getMxList();

        for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
            Qswszmx mx = (Qswszmx) iter.next();
            /** @todo ����Ժ��Ϊ��˰��˰Ŀ���������Ҫ���뵽������ */
            //��Ʊ����˰֤��˰��˰�ʡ�Ȩ��ת�����������ʾ
            wszForm.setSzsmmc(mx.getSzsmmc());
            wszForm.setJsje(" ");
            wszForm.setSl(" ");
            wszForm.setSjse("��" + DataConvert.BigDecimal2String(mx.getSjse(), 2));
            wszForm.setQszymj(" ");
            wszForm.setSkssksrq(DataConvert.TimeStamp2String(mx.getSkssksrq()));
            wszForm.setSkssjzrq(DataConvert.TimeStamp2String(mx.getSkssjsrq()));

        }
        StringBuffer sb = new StringBuffer(" ��˰��ʽ��");
        sb.append(wszZb.getJsfsmc());
        //�����˰֤�з�Ʊ�������Ҫ�ڱ�ע�аѷ�Ʊ����˰֤�Ŷ���ӡ����
        /*sb.append(" ��˰֤�ţ�");
                     for (int i = 0; i < wszList.size(); i++)
                     {
            sb.append(((Qswszz) wszList.get(i)).getWszh()).append(", ");
                     }*/
        wszForm.setBz(sb.toString());
        wszForm.setJbr(wszZb.getCjr());

        wszForm.setYqts(" ");
        wszForm.setZnj(" ");
        Debug.out("ת��ǰ�ĺϼ�ʵ�ɽ� " + wszZb.getHjsjje());
        wszForm.setJehj(DataConvert.BigDecimal2String(wszZb.getHjsjje(), 2));
        Debug.out("ת����� " +
                  wszZb.getHjsjje().setScale(2, BigDecimal.ROUND_HALF_UP));
        wszForm.setJehj_dx(Currency.convert(wszZb.getHjsjje().setScale(2,
                BigDecimal.ROUND_HALF_UP)));

        request.setAttribute(Constants.MESSAGE_KEY, "�Ѿ��������ֽɿ��飡");

        return mapping.findForward("show");
    }


    /**
     * ��ѯ��˰֤���������ѯ�������200������ֻ��ʾǰ200�������Ҫ���Ǹ����doQuery����
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
        try {
            // �������е�Form����ΪQueryWszForm��
            QueryWszForm queryForm = (QueryWszForm) form;
            // ��QueryWszForm�л�ȡ��ѯ������
            QueryWszBo obj = (QueryWszBo) queryForm.getData();
            // ����BaseProxy��query��������ѯ���ݡ�
            HttpSession session = request.getSession(false);

            //��ȡ�����ServletContext�е�processor-map.properties������
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //�������̨�����Vopackage����
            VOPackage vo = new VOPackage();
            vo.setAction(ActionType.QUERY);
            vo.setUserData(this.getUserData());
            vo.setData(obj);
            vo.setProcessor(prop.getProperty(obj.getClass().getName()));

            ArrayList list = (ArrayList) QsglProxy.getInstance().process(vo);

            //�����ѯ�����201������ֻȡǰ200������ѯ����ǰ�ʱ������ģ������������ǰ�棩
            if (list.size() == 201) {
                list.remove(200);
                String msg =
                        "��ѯ���̫�࣬Ϊ�˱�֤�ٶȣ�ֻ��ʾǰ200�����ݣ������������Ĳ�ѯ�����Ա�õ���ȷ�Ĳ�ѯ���!!!";
                request.setAttribute(Constants.MESSAGE_KEY, msg);
            }
            // ����������ͨ�õ�QueryCache�����Ա㷭ҳʹ�á�

            QueryCache cache = new QueryCache(list, this.getUserData().myxszds);
            queryForm.setQueryCache(cache);
            //��ҳ����ʾ״̬�趨Ϊ��ʾ��ѯ���
            queryForm.setStatus("Result");

            // ����Token;
            saveToken(request);
            // ת��ɹ���Ľ��档
            return mapping.findForward("query");
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute(Constants.MESSAGE_KEY, ex.getMessage());
            return mapping.findForward("query");
        }

    }

}

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryYggyzfBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 *
 * <p>Description:��Ǩ�����Processor�� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author ����
 * @version 1.0
 */
public class JsblgyzfProcessor extends CommonProcessor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is CqqkProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.INSERT:
            doAdd(vo);
            break;
        case ActionType.MODIFY:
            doUpdate(vo);
            break;
        case ActionType.UPDATE_SYE:
            doUpdateSye(vo);
            break;
        case ActionType.GET:
            return doGet(vo);
        case ActionType.QUERY_USAGE:
            return doQueryUsage(vo);
        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ʵ��������һ������ס���ֿ۶�ʹ�ü�¼
     * ����ù���ס���ǵ�һ��ʹ��,������һ������ס����Ϣ,������¹���ס����Ϣ
     * �����걨����ס��������Ϣ��������ʹ�õֿ۶�
     * 1.insert/update a record of Jsblgyzf
     * 2. insert a record of Sbgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            String nd = DateUtil.getDate().substring(0, 4);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            conn = QSDBUtil.getConnection();
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            //  List l = dao.getBySbbh(jsblgyzf.getSbbh(),conn);

            Jsblgyzf existGyzf = (Jsblgyzf) dao.get(jsblgyzf, conn);
            /*          Jsblgyzf existGyzf = null;
                      double dkeHj = jsblgyzf.getBcdke().doubleValue();
                      //dkeHj+=jsblgyzf.getBcdke().doubleValue();
                      for (int i=0;i<l.size();i++){
                       Jsblgyzf gf = (Jsblgyzf) l.get(i);
                       if (gf != null && gf.getYggyzfqszsh().equalsIgnoreCase(jsblgyzf.getYggyzfqszsh())){
                        existGyzf = gf;
                       }else{
                        dkeHj+=gf.getBcdke().doubleValue();
                       }
                       }
             */
            if (existGyzf != null) {
                existGyzf.setBcdke(jsblgyzf.bcdke);

                existGyzf.setCjjg(jsblgyzf.cjjg);
                existGyzf.setJzmj(jsblgyzf.jzmj);
                existGyzf.setQdsj(jsblgyzf.qdsj);
                existGyzf.setSye(jsblgyzf.sye);
                existGyzf.setZldz(jsblgyzf.zldz);
                existGyzf.setSyeywbz(jsblgyzf.getSyeywbz());
                existGyzf.setFwqszsh(jsblgyzf.getFwqszsh());                
                dao.update(existGyzf, conn);
            } else {
                //construct the jsblgyzf and insert
                jsblgyzf.ztbs = "0";
                jsblgyzf.cjr = ud.yhmc;
                jsblgyzf.lrr = ud.yhmc;
                jsblgyzf.cjrq = now;
                jsblgyzf.lrrq = now;
                jsblgyzf.nd = nd;

                dao.insert(jsblgyzf, conn);
            }

            //construct the sbgyzf and insert
            Sbgyzf sbgyzf = new Sbgyzf();
            sbgyzf.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            sbgyzf.bcdke = jsblgyzf.bcdke;
            sbgyzf.lrr = ud.yhmc;
            sbgyzf.cjr = ud.yhmc;
            sbgyzf.cjrq = now;
            sbgyzf.lrrq = now;
            sbgyzf.sbbh = jsblgyzf.sbbh;
            Debug.out("Jsblgyzfprocessor sbbh: " + jsblgyzf.sbbh);

            DAOFactory.getInstance().getSbgyzfDAO().insert(sbgyzf, conn);

            //��������������˰��ʶ,�������˰��ֿ۳��۹��������ʵ��Ӧ��˰��Ϊ0ʱ
            //DAOFactory.getInstance().getSbzbDAO().updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR,jsblgyzf.getSbbh(),conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblgyzfProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.���ݲ�ǨЭ��Ż�ȡ��Ǩ��Ϣ
     * @param vo VOPackage
     * @throws BaseException
     */
    private Jsblgyzf doGet(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);

            if (existData == null) {
                Debug.out("jsblgyzf : " + jsblgyzf.getYggyzfqszsh() +
                          " not exist.");
            } else {
                Sbgyzf sbgyzf = new Sbgyzf();
                sbgyzf.sbbh = existData.sbbh;
                sbgyzf.yggyzfqszsh = existData.yggyzfqszsh;
                sbgyzf = (Sbgyzf) DAOFactory.getInstance().getSbgyzfDAO().get(
                        sbgyzf, conn);
                if (sbgyzf != null) {
                    existData.bcdke = sbgyzf.bcdke;
                }
            }

            return existData;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblgyzfProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ȡ����ס����Ϣʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            SbgyzfDAO gldao = DAOFactory.getInstance().getSbgyzfDAO();

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);

            //���ø��µ��ֶ�
            existData.sye = jsblgyzf.sye;
            existData.cjjg = jsblgyzf.cjjg;
            Debug.out("update cjjg: " + existData.cjjg);
            existData.jzmj = jsblgyzf.jzmj;
            existData.qdsj = jsblgyzf.qdsj;
            existData.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            existData.zldz = jsblgyzf.zldz;
            existData.syeywbz = jsblgyzf.syeywbz;
            existData.fwqszsh = jsblgyzf.fwqszsh;            

            //����
            dao.update(existData, conn);

            //�����걨����ס������
            Sbgyzf sbgyzf = new Sbgyzf();
            sbgyzf.sbbh = jsblgyzf.sbbh;
            sbgyzf.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            sbgyzf = (Sbgyzf) gldao.get(sbgyzf, conn);
            sbgyzf.bcdke = jsblgyzf.bcdke;
            DAOFactory.getInstance().getSbgyzfDAO().update(sbgyzf, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblgyzfProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    /*  private void doDelete(VOPackage vo) throws BaseException {
         Connection conn = null;
         try {
             UserData ud = vo.getUserData();

             //construct the jsblgyzf and insert
             ArrayList delList = (ArrayList)vo.getData();

             conn = QSDBUtil.getConnection();
             JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();

             dao.delete(delList,conn);


         } catch (Exception ex) {
             throw ExceptionUtil.getBaseException(ex);
         } finally {
             QSDBUtil.freeConnection(conn);
         }
      }
     */
    /**
     * 1.���ݲ�ǨЭ��Ż�ȡ��Ǩʹ����Ϣ
     * @param vo VOPackage
     * @throws BaseException
     * @return QueryCqxxBo
     *
     */
    private QueryYggyzfBo doQueryUsage(VOPackage vo) throws BaseException {
        Connection conn = null;
        QueryYggyzfBo bo = null;
        try {
            //construct the jsblcq and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            System.out.println("--:" + jsblgyzf.yggyzfqszsh);

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);
            System.out.println("--2:" + existData);
            if (existData == null) {

                return null;
            } else {
                //����QueryCqxxBo
                bo = new QueryYggyzfBo();
                bo.setYggyzfqszsh(existData.getYggyzfqszsh());
                bo.setZldz(existData.getZldz());
                bo.setQdsj(existData.getQdsj());
                bo.setJzmj(existData.getJzmj());
                bo.setCjjg(existData.getCjjg());
                bo.setSye(existData.getSye());
                bo.setCjr(existData.getCjr());
                bo.setCjrq(existData.getCjrq());
                bo.setSyeywbz(existData.getSyeywbz());
                bo.setFwqszsh(existData.getFwqszsh());
                //��ȡʹ�ô˲�Ǩ���걨��Ϣ
                ArrayList list = (ArrayList) DAOFactory.getInstance().
                                 getSbgyzfDAO().queryByHth(jsblgyzf.yggyzfqszsh,
                        conn);
                if ((list != null) && (list.size() > 0)) {
                    bo.setListSbxx(list);
                }
            }

            return bo;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblgyzfProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ȡ��Ǩʹ����Ϣʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdateSye(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            //����
            dao.updateSye(jsblgyzf, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblgyzfProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }
}

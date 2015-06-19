package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbcqglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbcqgl;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryCqxxBo;
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
 * @author ������
 * @version 1.0
 */
public class JsblcqProcessor extends CommonProcessor {
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
     * 1.insert a record of Jsblcq
     * 2. insert a record of Sbcqgl
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            String nd = DateUtil.getDate().substring(0, 4);
            Timestamp now = new Timestamp(System.currentTimeMillis());

            //construct the jsblcq and insert
            JsblcqDAO dao = (JsblcqDAO) DAOFactory.getInstance().getJsblcqDAO();
            conn = QSDBUtil.getConnection();
            Jsblcq jsblcq = (Jsblcq) vo.getData();

            Jsblcq existJsblcq = (Jsblcq) dao.get(jsblcq, conn);
            if (existJsblcq != null) {
                //update
                existJsblcq.cqbce = jsblcq.cqbce;
                existJsblcq.cqbcsye = jsblcq.cqbcsye;
                existJsblcq.zldz = jsblcq.zldz;
                existJsblcq.syeywbz = jsblcq.syeywbz;

                dao.update(existJsblcq, conn);
            } else {
                jsblcq.cjr = ud.yhmc;
                jsblcq.lrr = ud.yhmc;
                jsblcq.cjrq = now;
                jsblcq.lrrq = now;
                jsblcq.nd = String.valueOf(nd);
                dao.insert(jsblcq, conn);
            }

            //construct the sbcqgl and insert
            Sbcqgl sbcqgl = new Sbcqgl();
            sbcqgl.cjr = ud.yhmc;
            sbcqgl.lrr = ud.yhmc;
            sbcqgl.cjrq = now;
            sbcqgl.lrrq = now;
            sbcqgl.cqxyh = jsblcq.cqxyh;
            sbcqgl.sbbh = jsblcq.sbbh;
            sbcqgl.bcsybce = jsblcq.bcsybce;
            Debug.out("Jsblcqprocessor sbbh: " + jsblcq.sbbh);

            DAOFactory.getInstance().getSbcqglDAO().insert(sbcqgl, conn);

            //��������������˰��ʶ
            DAOFactory.getInstance().getSbzbDAO().updateJmsbs(Constants.
                    ZB_BLJMSBS_FHBL_WLR, jsblcq.getSbbh(), conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblcqProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.update a record of Jsblcq
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblcq and insert
            Jsblcq jsblcq = (Jsblcq) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblcqDAO dao = DAOFactory.getInstance().getJsblcqDAO();
            SbcqglDAO gldao = DAOFactory.getInstance().getSbcqglDAO();

            Jsblcq existData = (Jsblcq) dao.get(jsblcq, conn);

            if (existData == null) {
                throw new Exception("���²�Ǩ����ʧ�ܣ������ڲ�Ǩ���ݣ�");
            }

            //���ø��µ��ֶ�
            existData.bcksybce = jsblcq.bcksybce;
            existData.bcsybce = jsblcq.bcsybce;
            existData.cqbce = jsblcq.cqbce;
            existData.cqbcsye = jsblcq.cqbcsye;
            existData.cqxyh = jsblcq.cqxyh;
            existData.zldz = jsblcq.zldz;
            existData.syeywbz = jsblcq.syeywbz;

            //����
            dao.update(existData, conn);

            //���¹�������ʹ�ö�
            Sbcqgl sbcqgl = new Sbcqgl();
            sbcqgl.sbbh = jsblcq.sbbh;
            sbcqgl.cqxyh = jsblcq.cqxyh;
            sbcqgl = (Sbcqgl) gldao.get(sbcqgl, conn);
            sbcqgl.bcsybce = jsblcq.bcsybce;
            DAOFactory.getInstance().getSbcqglDAO().update(sbcqgl, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblcqProcessor����������",
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
    private Jsblcq doGet(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            //construct the jsblcq and insert
            Jsblcq jsblcq = (Jsblcq) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblcqDAO dao = DAOFactory.getInstance().getJsblcqDAO();

            Jsblcq existData = (Jsblcq) dao.get(jsblcq, conn);

            if (existData == null) {
                Debug.out("cqxx : " + jsblcq.getCqxyh() + " not exist.");
            } else {
                Sbcqgl sbcqgl = new Sbcqgl();
                sbcqgl.sbbh = existData.sbbh;
                sbcqgl.cqxyh = existData.cqxyh;
                sbcqgl = (Sbcqgl) DAOFactory.getInstance().getSbcqglDAO().get(
                        sbcqgl, conn);
                if (sbcqgl != null) {
                    existData.bcsybce = sbcqgl.bcsybce;
                }
            }

            return existData;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblcqProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ȡ��Ǩ��Ϣʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.���ݲ�ǨЭ��Ż�ȡ��Ǩʹ����Ϣ
     * @param vo VOPackage
     * @throws BaseException
     * @return QueryCqxxBo
     *
     */
    private QueryCqxxBo doQueryUsage(VOPackage vo) throws BaseException {
        Connection conn = null;
        QueryCqxxBo bo = null;
        try {
            //construct the jsblcq and insert
            Jsblcq jsblcq = (Jsblcq) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblcqDAO dao = DAOFactory.getInstance().getJsblcqDAO();
            System.out.println("--:" + jsblcq.cqxyh);

            Jsblcq existData = (Jsblcq) dao.get(jsblcq, conn);
            System.out.println("--2:" + existData);
            if (existData == null) {

                return null;
            } else {
                //����QueryCqxxBo
                bo = new QueryCqxxBo();
                bo.setCqxyh(existData.getCqxyh());
                bo.setZldz(existData.getZldz());
                bo.setCqbce(existData.getCqbce());
                bo.setCqbcsye(existData.getCqbcsye());
                bo.setCjr(existData.getCjr());
                bo.setCjrq(existData.getCjrq());
                bo.setSyeywbz(existData.getSyeywbz());

                //��ȡʹ�ô˲�Ǩ���걨��Ϣ
                ArrayList list = (ArrayList) DAOFactory.getInstance().
                                 getSbcqglDAO().queryByCqxyh(jsblcq.cqxyh, conn);
                if ((list != null) && (list.size() > 0)) {
                    bo.setListSbxx(list);
                }
            }

            return bo;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblcqProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ȡ��Ǩʹ����Ϣʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 1.update a record of Jsblcq
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdateSye(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblcq and insert
            Jsblcq jsblcq = (Jsblcq) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblcqDAO dao = DAOFactory.getInstance().getJsblcqDAO();

            //����
            dao.updateSye(jsblcq, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JsblcqProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }
}

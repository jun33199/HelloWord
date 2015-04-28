package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JsblBo;
import com.creationstar.bjtax.qsgl.model.bo.QuerySbzbBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SbState;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>
 * Title:
 * </p>
 *
 * <p>
 * Description:
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 *
 * <p>
 * Company:
 * </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SbxxProcessor implements Processor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo
     *            the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException
     *             �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SbxxProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQuery(vo);
            break;
        case ActionType.GET:
            result = doGet(vo);
            break;
        case ActionType.MODIFY:
            result = doModify(vo);
            break;
        case ActionType.ROLLBACK:
            doRollBack(vo);
            break;
        case ActionType.CONFIRM:
            doConfirm(vo);
            break;
        case ActionType.REJECT:
            doReject(vo);
            break;
        case ActionType.CANCEL:
            doCancel(vo);
            break;
        case ActionType.DELETE:
            doDelete(vo);
            break;
        case ActionType.PRINT_SBB:
            result = doPrintSbb(vo);
            break;
        case ActionType.PRINT_HDTZS:
            result = doPrintHdtzs(vo);
            break;
        case ActionType.Query_HDTZS:
            result = doQueryHdtzs(vo);
            break;
        case ActionType.UPDATE_HDTZS:
            doUpdateHdtzs(vo);
            break;
        case ActionType.Query_HDTZSBYFWHM:
            result = doQueryHdtzsbyFwhm(vo);
            break;
        case ActionType.QUERY_SBZT:
            result = doQuerySbzt(vo);
            break;

        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * һ���ѯ����
     *
     * @param vo
     *            VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        JsblBo bo = new JsblBo();
        try {
            // vo.getUserData();
            bo = (JsblBo) vo.getData();
            // String conditions = CommonUtil.getSqlQueryCondition(bo);
            String conditions = "";
            if ((bo.getZtbs() != null) && (!bo.getZtbs().equals(""))) {
                conditions += " and a.ztbs = '" + bo.getZtbs() + "'";
            }
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and a.drpch = '" + bo.getDrpch() + "'";
            }
            if ((bo.getJmsbs() != null) && (!bo.getJmsbs().equals(""))) {
                conditions += " and a.bljmsbs = '" + bo.getJmsbs() + "'";
            }
            if ((bo.getSbbh() != null) && (!bo.getSbbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.sbbh = '" + bo.getSbbh() + "'";
            }
            //���ӽ�ίҵ���Ų�ѯ����,modify by fujx,
            if ((bo.getJwywbh() != null) && (!bo.getJwywbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.jwywbh = '" + bo.getJwywbh() + "'";
            }
            //���Ӻ�ͬ��Ų�ѯ����,modify by fujx
            if ((bo.getHtbh() != null) && (!bo.getHtbh().equals(""))) {
                // conditions += " and a.sbbh like '" + bo.getSbbh() + "%'";
                conditions += " and a.htbh = '" + bo.getHtbh() + "'";
            }

            if ((bo.getSbrq() != null) && (!bo.getSbrq().equals(""))) {
                conditions += " and to_char(a.sbrq,'yyyyMMdd') = '"
                        + bo.getSbrq() + "'";
            }
            if ((bo.getJsjdm() != null) && (!bo.getJsjdm().equals(""))) {
                conditions += " and d.jsjdm = '" + bo.getJsjdm() + "'";
            }
            if ((bo.getNsrmc() != null) && (!bo.getNsrmc().equals(""))) {
                conditions += " and d.nsrmc like '%" + bo.getNsrmc() + "%'";
            }

            if ((bo.getNsrlxdm() != null) && (!bo.getNsrlxdm().equals(""))) {
                if (bo.getNsrlxdm().equals(Constants.NSRLX_GR)) {
                    if ((bo.getSfzjlx() != null)
                        && (!bo.getSfzjlx().equals(""))) {
                        conditions += " and d.sfzjlx = '" + bo.getSfzjlx()
                                + "'";
                    }
                    if ((bo.getSfzjhm() != null)
                        && (!bo.getSfzjhm().equals(""))) {
                        conditions += " and d.sfzjhm = '" + bo.getSfzjhm()
                                + "'";
                    }
                } else {
                    conditions += " and d.nsrlxdm = '" + bo.getNsrlxdm() + "'";
                }
            }

            // ����ҵ���������
            String ywfilter = getYwFilter(bo);
            if ((ywfilter != null) && (!ywfilter.equals(""))) {
                conditions += " and " + ywfilter;
            }

            // ��������Ȩ�޿���
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
                    "QSDB", "QS_JL_SBZB");
            Debug.out("datafilter: " + datafilter);
            conditions += " and " + datafilter;

            conn = QSDBUtil.getConnection();
            if ((bo.getNsrlxdm() != null) && (!bo.getNsrlxdm().equals(""))) {

                resultList = DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, bo.ifPersonal());
            } else { // û��ѡ����˰������
                // �Ȳ����
                resultList = DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, true);
                // �ڼ��ϷǸ���
                resultList.addAll(DAOFactory.getInstance().getSbzbDAO().query(
                        conditions, conn, false));
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }

    /**
     * ʵ����SbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        SbxxBo Bo = new SbxxBo();
        SbxxBo queryBo = (SbxxBo) vo.getData();
        String sbbh = queryBo.getSbbh();
        // ��ò��������ݽ�����Ϣ���걨bo
        Bo = (SbxxBo) doGetSbxxBo(sbbh);
        if (!Bo.isBZ()) { // �������ݲ�ȡ������Ϣ
            // ���Ϊ���ݽ�����Ϣ,��ȡ���Է��ķ�����Ϣ
            String dfSbbh = Bo.getVoSbzb().getDfsbbh();
            if (dfSbbh != null && !dfSbbh.equals("")) {
                Bo.setDfSbxxBo((FwjhxxBo) doGetFwjhxxBo(dfSbbh));
            }
        }
        return Bo;
    }

    /**
     * ʵ����SbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGetHdtzs(VOPackage vo) throws BaseException {
        /**
         * ͨ��ȡ������SbxxBo �����ѯ����
         *
         * 1��ʹ��SbxxBo�����걨����Vo��DAO���ؽ���걨����Vo
         *
         * 2��������ˡ��Ǹ�����ϢVo��DAO.get���ؽ�����ˡ��Ǹ�����ϢVo��ArrayList
         *
         * 3�����췿�����ػ�����Ϣconditions,DAO.query���ؽ���������ػ�����ϢVo��ArrayList
         *
         * 4�������Ǩ��Ϣconditions,DAO.query���ؽ����Ǩ��ϢVo��ArrayList
         *
         * 5�����칫��ס����Ϣconditions,DAO.query���ؽ������ס����ϢVo��ArrayList
         */

        HdtzsBo hdtzsBo = new HdtzsBo();
        SbxxBo sbxxBo = (SbxxBo) vo.getData();

        HashMap nrMap = new HashMap();

        Connection conn = null;
        try {
            Debug.out("getHdtzs sbxx sbbh: " + sbxxBo.getSbbh());
            conn = QSDBUtil.getConnection();

            // ��ȡ�˶�֪ͨ����������
            Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                          .getBySbbh(sbxxBo.getSbbh(), conn);
            hdtzsBo.setVoHdtzs(hdtzs);

            // ��ȡ�˶�������ϸ
            if (!sbxxBo.isBZ()) {
                String condition = " where HDTZSH = '" + hdtzs.getHdtzsh()
                                   + "'";
                ArrayList jmList = (ArrayList) DAOFactory.getInstance()
                                   .getHdjmmxDAO().query(condition, conn);
                for (int i = 0; i < jmList.size(); i++) {
                    Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                    nrMap.put(hdjmmx.getZcbh(), hdjmmx);
                }
                hdtzsBo.setJmnrMap(nrMap);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return hdtzsBo;
    }

    /**
     * �޸�
     *
     * @param vo
     *            VOPackage
     */
    private SbxxBo doModify(VOPackage vo) throws BaseException {
        int oklevel = 0;
        SbxxBo bo = new SbxxBo();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doModify... ");
        Connection conn = null;

        try {
            bo = (SbxxBo) vo.getData();
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨������Ϣ
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            Sbzb sbzbVo = bo.getVoSbzb();

            sbzbDao.update(sbzbVo, conn);
            Timestamp now = new Timestamp(System.currentTimeMillis());

            oklevel = 1;
            // ��ȡ���¸���/�Ǹ�����Ϣ
            if (bo.isPerson()) {

                // ɾ����ǰ�걨��Ŷ�Ӧ�ĸ�����Ϣ
                DAOFactory.getInstance().getGrxxDAO().delete(sbzbVo.getSbbh(),
                        conn);

                // �����޸ĺ�ĸ�����Ϣ
                // ��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                List nsrList = bo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FFWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("SbxxProcessor bo nsrmc22: " + grxx.nsrmc);
                    grxx.sbbh = sbzbVo.getSbbh();
                    l.add(grxx);
                }

                // ��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                l = CommonUtil.handleZRR(l, ud);
                bo.setNsrList(l);

                // insert grxx
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
                oklevel = 2;
            } else {
                // �õ��Ǹ�����Ϣ
                Fgrxx fgrexxVo = bo.getVoFgrxx();
                DAOFactory.getInstance().getFgrxxDAO().update(fgrexxVo, conn);
                oklevel = 3;
            }
            // �������������Ϣ
            Spjgxx spjgxx = bo.getVoSpjgxx();
            // ɾ��ԭ�ȵļ�¼
            String sql = "delete from qsdb.qs_jl_spjgxx where sbbh = '"
                         + sbzbVo.sbbh + "'";
            BaseDAO.execute(sql, conn);
            // �����µļ�¼
            if ((spjgxx != null) && (spjgxx.getHdtzszh() != null)
                && (!spjgxx.getHdtzszh().equals(""))) {
                spjgxx.cjr = ud.getYhmc();
                spjgxx.cjrq = now;
                if ((spjgxx.getJmlydm() == null)
                    || (spjgxx.getJmlydm().equals(""))) {
                    spjgxx.jmlydm = "00";
                }

                spjgxx.lrr = ud.getYhmc();
                spjgxx.lrrq = now;
                spjgxx.sbbh = sbzbVo.sbbh;

                Debug.out("jmje: " + spjgxx.getJmsje());
                Debug.out("hdtzszh: " + spjgxx.getHdtzszh());

                DAOFactory.getInstance().getSpjgxxDAO().insert(spjgxx, conn);
            }
        } catch (Exception ex) {
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("�����걨�������!");
                case 1:
                    throw new ApplicationException(
                            "ȡ���˼���������������¼�����˰����������Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ��,���¸�����Ϣ���߷Ǹ�����Ϣ����!");
                default:
                    if (ex.getMessage().indexOf("ORA-00001") != -1) {
                        throw new ApplicationException("������������Ѿ�����!");
                    } else {
                        throw new ApplicationException("���¼������������Ϣ����!");
                    }
                }
            } catch (ApplicationException e) {
                // ����ʧ����Ϣ:����̨ �� ��־
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP)
                                 .getStackMsg(), "ʧ��");

                throw ExceptionUtil.getBaseException(e);
            }
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }

    /**
     * ����
     *
     * @param vo
     *            VOPackage
     */
    private void doCancel(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doCancel... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            // ����״̬
            sbzbDao.update(Constants.ZB_ZTBS_ZF, bo.getSbbh(), conn);

            // ����������в�Ǩ��Ϣ,����Ǩʹ�ò�����ָ�

            if (bo.getCqList() != null) {
                DAOFactory.getInstance().getJsblcqDAO().updateBcsye(
                        bo.getSbbh(), bo.getCqList(), conn);
            }

            // ����й���ס����Ϣ,���ֿ۶�ָ�
            if (bo.getGyzfList() != null) {
                DAOFactory.getInstance().getJsblgyzfDAO().updateDksye(
                        bo.getSbbh(), bo.getGyzfList(), conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "�����걨ʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * �����ϴβ������ָ�״̬
     *
     * @param vo
     *            VOPackage
     */
    private void doRollBack(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doRollBack... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            Debug.out("current state: " + SbState.getStateName(bo.getState()));
            Debug.out("rollback state: "
                      + SbState.getStateName(SbState.getCancelStateCode(bo
                    .getState(), bo.getVoSbzb().getBljmsbs())));
            // ����״̬
            sbzbDao.update(SbState.getCancelStateCode(bo.getState(), bo
                    .getVoSbzb().getBljmsbs()), bo.getSbbh(), conn);
            // ���ͬ�⳷����
            // ɾ���˶�֪ͨ���
            String ztbs = bo.getVoSbzb().getZtbs();
            String jmsbs = bo.getVoSbzb().getBljmsbs();
            String sbbh = bo.getVoSbzb().getSbbh();
            Debug.out("ztbs: " + ztbs);
            Debug.out("jmsbs: " + jmsbs);
            if (ztbs.equals(Constants.ZB_ZTBS_JS_TY)) {
                DAOFactory.getInstance().getHdtzsDAO().deleteHdtzsBySbbh(sbbh,
                        conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ӡ�걨��
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintSbb(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doPrintSbb... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            bo = (SbxxBo) doGet(vo);
            Debug.out("sbxxprocessor doPrintSbb bo.sbzb: "
                      + bo.getVoSbzb().getSbbh());
            // ֻ������������£��Ÿ���״̬
            // HashMap map = (HashMap)
            // CommonUtil.getJZSE(bo.getVoSbzb().getSbbh(),
            // conn);
            JghdsjBo hdbo = CommonUtil.getJZSE(bo, conn);

            bo.setHdbo(hdbo);

            // ��ȡ�ѹ�����ס����
            BigDecimal sjyz = hdbo.getSjyz(); // Ӧ����˰
            BigDecimal kcqyzfx = hdbo.getGyzfjmje();
            // ��������������˰��ʶ,�������˰��ֿ۳��۹��������ʵ��Ӧ��˰��Ϊ0ʱ

            if (kcqyzfx != null && kcqyzfx.doubleValue() > 0) {
                if (sjyz != null
                    && sjyz.doubleValue() <= 0
                    && !Constants.ZB_BLJMSBS_FHBL_WLR.equals(bo.getVoSbzb()
                        .getBljmsbs())) {
                    sbzbDao.updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR, bo
                                        .getSbbh(), conn);
                }
            }

            if (bo.getState().equals(Constants.ZB_ZTBS_BC)) {
                // ����״̬
                sbzbDao.update(Constants.ZB_ZTBS_DY, bo.getSbbh(), conn);
            }

            // ���Ӷ���˰Ǩ�����ݵĴ����걨��Ŵ�Ǩ�Ʊ���ȡ
            bo.setPrintSbbh(bo.getSbbh());
            if (bo.getVoSbzb().getBlbs().equals(Constants.ZB_BLBS_SJQYBL)) {
                bo.setPrintSbbh(sbzbDao.getDrsbbh(bo.getSbbh(), conn));
            }

            return bo;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ӡ�˶�֪ͨ�� ����ֻ��һ���걨���
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintHdtzs(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doPrintHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����
            bo = (SbxxBo) doGet(vo);
            // ֻ�����ͬ�������£��Ÿ���״̬
            if (bo.getState().equals(Constants.ZB_ZTBS_JS_TY)) {
                // ����״̬
                DAOFactory.getInstance().getSbzbDAO().update(
                        Constants.ZB_ZTBS_DY_HD, bo.getSbbh(), conn);
            }

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            if (bo.isBZ()) {
                hdtzsBo.setFwlxmc(bo.getVoTufwxx().getFlmc());
                hdtzsBo.setFwlxdm(bo.getVoTufwxx().getFldm());
            }

            hdtzsBo.setVoTufwxx(bo.getVoTufwxx());

            return hdtzsBo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ���ͬ��
     *
     * @param vo
     *            VOPackage
     */
    private void doConfirm(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();

        Connection conn = null;

        try {
            UserData ud = vo.getUserData();
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            // update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(
                    Constants.ZB_ZTBS_JS_TY, bo.getSbbh(), conn);

            // ����˶�֪ͨ��
            Hdtzs hdtzs = new Hdtzs();
            if (bo.isBZ()) {
                hdtzs.bzbs = Constants.BZBS_BZ;
            } else {
                hdtzs.bzbs = Constants.BZBS_JM;
            }
            hdtzs.cjr = ud.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            // modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH(ud, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            // end modified

            hdtzs.jbr = ud.yhmc;
            /**
             * ������˼��տ�ʱ����Ҫ�õ��ļ���˰�� Constants �еĶ��壺 public static final String
             * JE_CJJG = "JE_CJJG"; //�ɽ��۸� public static final String JE_JSYJ =
             * "JE_JSYJ"; //��˰���� public static final String JE_JZQS = "JE_JZQS";
             * //������˰ public static final String JE_SJYZ = "JE_SJYZ"; //ʵ��Ӧ��
             * public static final String JE_JZSE = "JE_JZSE"; //����˰�� public
             * static final String JE_QYZFBCDKE =
             * "JE_QYZFBCDKE";//�����ѹ�����ס���ı��εֿ۶� public static final String
             * JE_FWJHJG = "JE_FWJHJG"; //���ݽ����۸� public static final String
             * JE_CQJMJE = "JE_CQJMJE"; //��Ǩ������ public static final String
             * JE_PTZZJMJE = "JE_PTZZJMJE";//��ͨסլ��˰��� public static final String
             * JE_JMSZE = "JE_JMSZE";//����˰�ܽ�� public static final String JE_YNSE =
             * "JE_YNSE"; //Ӧ��˰��
             *
             * @param String
             *            �걨���
             * @return HashMap
             */
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getVoSbzb().getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); // Ӧ����˰
            // ��ȡ�ѹ�����ס����
            hdtzs.kcqyzfx = hdbo.getGyzfjmje();
            hdtzs.lrr = ud.yhmc;
            hdtzs.lrrq = now;
            // ��ȡ��ϵ�绰
            if (bo.isPerson()) {
                // hdtzs.sqr = bo.getVoZcqrxx().getNsrmc();
                hdtzs.sqr = ActionUtil.getNsrmc(bo.getNsrList(), null);
            } else {
                hdtzs.sqr = bo.getVoFgrxx().getNsrmc();
            }
            //
            hdtzs.sbbh = bo.getVoSbzb().getSbbh();
            hdtzs.spfxmmc = bo.getVoTufwxx().getFdcxmmc();
            hdtzs.zldi = bo.getVoTufwxx().getTdfwzldz();

            // ����һ���˶�֪ͨ���¼
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            // ����й���ס�������������ϸ��
            if ((bo.getGyzfList() != null) & bo.getGyzfList().size() > 0) {
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);

		//�жϷ��������ǹ���ס�����Ǿ������÷�	 modiby	by zhangyj 20090223		
		ArrayList list = DAOFactory.getInstance().getHdtzsDAO().getFwlx(bo.getSbbh(), conn);
		HashMap map = new HashMap();	
		String  lx= "";
		for(int i=0;i<list.size();i++)
		{
			map=(HashMap)list.get(i);
			lx= map.get("lx").toString();				
			if(lx.equals("1"))
			{
				hdjmmx.setZcbh(Constants.JMZC_GYZF);
				hdjmmx.setJmje(new BigDecimal(map.get("je").toString()));
			}else if(lx.equals("2"))
			{
				hdjmmx.setZcbh(Constants.JMZC_JJSYZF);
				hdjmmx.setJmje(new BigDecimal(map.get("je").toString()));
			}
			DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);					
		}
            }

            // ����в�Ǩס�������������ϸ��
            if ((bo.getCqList() != null) & bo.getCqList().size() > 0) {
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(hdbo.getCqjmje());
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_CQFW);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

            BigDecimal ptzzjmje = hdbo.getPtzzjmje();
            if (ptzzjmje.doubleValue() > 0) { // ����ͨסլ������
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(ptzzjmje);
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_PTZZ);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��˲�ͬ��
     *
     * @param vo
     *            VOPackage
     */
    private void doReject(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            DAOFactory.getInstance().getSbzbDAO().update(
                    Constants.ZB_ZTBS_JS_BTY, bo.getVoSbzb().getSbbh(), conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ɾ��
     *
     * @param vo
     *            VOPackage
     */
    private void doDelete(VOPackage vo) throws BaseException {

        ArrayList delList = (ArrayList) vo.getData();
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            int size = delList.size();
            for (int i = 0; i < size; i++) {
                String sbbh = (String) delList.get(i);
                String sql = "";
                Debug.out("delete sbbh: " + sbbh);

                // �õ�����������Ϣ
                Sbzb sbzbVo = new Sbzb();
                sbzbVo.setSbbh(sbbh);
                sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(
                        sbzbVo, conn);

                deleteSbxx(sbbh, conn);
                if ((sbzbVo.getDfsbbh() != null)
                    && (!sbzbVo.getDfsbbh().equals(""))) {
                    deleteSbxx(sbzbVo.getDfsbbh(), conn);
                }
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ʵ����SbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGetSbxxBo(String sbbh) throws BaseException {
        /**
         * ͨ��ȡ������JsblBo �����ѯ����
         *
         * 1��ʹ��SbxxBo�����걨����Vo��DAO���ؽ���걨����Vo
         *
         * 2��������ˡ��Ǹ�����ϢVo��DAO.get���ؽ�����ˡ��Ǹ�����ϢVo��ArrayList
         *
         * 3�����췿�����ػ�����Ϣconditions,DAO.query���ؽ���������ػ�����ϢVo��ArrayList
         *
         * 4�������Ǩ��Ϣconditions,DAO.query���ؽ����Ǩ��ϢVo��ArrayList
         *
         * 5�����칫��ס����Ϣconditions,DAO.query���ؽ������ס����ϢVo��ArrayList
         */

        SbxxBo Bo = new SbxxBo();

        Tufwxx voTufwxx = new Tufwxx();
        ArrayList cqList = new ArrayList();
        ArrayList gyList = new ArrayList();
        List nsrList = new ArrayList();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // �õ�����������Ϣ
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setVoSbzb(sbzbVo);

            // if (Constants.YHBS_GR.equals(bo.getNsrlxdm()))
            if (Bo.isPerson()) {
                // �õ�������Ϣ
                List l = null;
                l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                        Bo.getSbbh(), conn);

                // Debug.out("sbxxProcessor get grxx nsrmc: " +
                // grxxVo.getNsrmc());
                // Bo.setVoGrxx(grxxVo);
                Bo.setNsrList(l);
            } else {
                // �õ��Ǹ�����Ϣ
                Fgrxx fgrexxVo = null;
                fgrexxVo = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO()
                           .getBySbbh(Bo.getSbbh(), conn);

                Bo.setVoFgrxx(fgrexxVo);
            }

            // ���ط�����Ϣ
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                            .getBySbbh(sbbh, conn);

            if (tufwxx != null) {
                Bo.setVoTufwxx(tufwxx);
            } else {
                Bo.setVoTufwxx(null);
            }

            // ��Ǩ
            cqList = (ArrayList) DAOFactory.getInstance().getJsblcqDAO()
                     .queryBySbbh(sbbh, conn);
            Bo.setCqList(cqList);

            // ����ס��
            gyList = (ArrayList) DAOFactory.getInstance().getJsblgyzfDAO()
                     .getBySbbh(sbbh, conn);
            Bo.setGyzfList(gyList);

            // ��ȡ���������Ϣ
            Spjgxx spjgxx = (Spjgxx) DAOFactory.getInstance().getSpjgxxDAO()
                            .getBySbbh(sbbh, conn);
            if (spjgxx == null) {
                spjgxx = new Spjgxx();
            }
            Bo.setVoSpjgxx(spjgxx);

        } catch (Exception ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return Bo;

    }

    /**
     * ʵ����SbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGetFwjhxxBo(String sbbh) throws BaseException {
        /**
         * ͨ��ȡ������JsblBo �����ѯ����
         *
         * 1��ʹ��SbxxBo�����걨����Vo��DAO���ؽ���걨����Vo
         *
         * 2��������ˡ��Ǹ�����ϢVo��DAO.get���ؽ�����ˡ��Ǹ�����ϢVo��ArrayList
         *
         * 3�����췿�����ػ�����Ϣconditions,DAO.query���ؽ���������ػ�����ϢVo��ArrayList
         *
         * 4�������Ǩ��Ϣconditions,DAO.query���ؽ����Ǩ��ϢVo��ArrayList
         *
         * 5�����칫��ס����Ϣconditions,DAO.query���ؽ������ס����ϢVo��ArrayList
         */

        FwjhxxBo Bo = new FwjhxxBo();

        Tufwxx voTufwxx = new Tufwxx();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // �õ�����������Ϣ
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setJkfsdm(sbzbVo.jsfsdm);
            Bo.setJkfsmc(sbzbVo.jsfsmc);
            Bo.setBz(sbzbVo.getBz());
            Bo.setFcjslh(sbzbVo.getFwtdbmdm());

            Bo.setJkfsdm(sbzbVo.getJsfsdm());
            Bo.setJkfsmc(sbzbVo.getJsfsmc());
            if (sbzbVo.getYhbs().equals(Constants.YHBZ_GR)) {
                // �õ�������Ϣ
                List l = null;
                l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                        Bo.getSbbh(), conn);

                // Debug.out("sbxxProcessor get grxx nsrmc: " +
                // grxxVo.getNsrmc());
                Bo.setJhperson("0");
                Bo.setNsrList(l);

            } else {
                // �õ��Ǹ�����Ϣ
                Fgrxx fgrexxVo = null;
                fgrexxVo = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO()
                           .getBySbbh(Bo.getSbbh(), conn);
                Bo.setJhperson("1");
                Bo.setFgrxx(fgrexxVo);
            }

            // ���ط�����Ϣ
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                            .getBySbbh(sbbh, conn);

            if (tufwxx != null) {
                Bo.setTufwxx(tufwxx);
            } else {
                Bo.setTufwxx(null);
            }
        } catch (Exception ex) {
            Debug.printException(ex);
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return Bo;

    }

    /**
     * ���ݲ�ѯ�����ĸ�ҳ��,ƴ����Ӧ�Ĳ�ѯ����
     *
     * @param fromPage
     *            int 0���걨��ѯ 1��������ѯ 2����˲�ѯ 3�����˲�ѯ 4: �����걨��ѯ
     * @return String
     */
    private String getYwFilter(JsblBo bo) {
        int fromPage = bo.getFromPage();
        StringBuffer sb = new StringBuffer();
        switch (fromPage) {
        case 0:

            // sb.append("(a.bljmsbs != '" + Constants.ZB_BLJMSBS_BZ + "')");
            sb.append("(a.bljmsbs not in ( '" + Constants.ZB_BLJMSBS_BZ + "','"
                      + Constants.ZB_BLJMSBS_CXXJM + "'))");
            break;
        case 1:
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_BZ + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='')");
            break;
        case 2:
            sb
                    .append("(a.ztbs = '1') and (a.bljmsbs ='1' or (a.bljmsbs='99' and (a.dfsbbh is null or a.dfsbbh ='')))");
            break;
        case 3:
            if ((bo.getZtbs() == null) || (bo.getZtbs().equals(""))) {
                if ((bo.getFztbs() == null) || (bo.getFztbs().equals(""))) {
                    // ��ѯȫ���Ѹ��˻���Ҫ���˵��걨����
                    sb
                            .append("((a.ztbs = '1' and a.bljmsbs = '0') or (a.ztbs = '4' and a.bljmsbs = '1') or a.ztbs = '7')"); //
                } else if (bo.getFztbs().equals("4")) { // δ����
                    sb
                            .append(
                            "((a.ztbs = '1' and a.bljmsbs = '0') or (a.ztbs = '4' and a.bljmsbs = '1'))");
                } else if (bo.getFztbs().equals("7")) { // �Ѹ���
                    // ��ʱztbs������Ϊ��
                }
            }
            break;
        case 4:
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_CXXJM + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='')");
            break;
        default:
            break;
        }
        return sb.toString();

    }

    /**
     * ɾ��һ���걨��¼
     */
    private void deleteSbxx(String sbbh, Connection conn) throws Exception {
        String sql = "";
        // �Ǹ�����Ϣ
        sql = "delete from qsdb.qs_jl_fgrxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete fgrxx ");
        // ������Ϣ
        sql = "delete from qsdb.qs_jl_grxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete grxx ");
        // ��Ǩ��Ϣ
        JsblcqDAO cqdao = DAOFactory.getInstance().getJsblcqDAO();
        ArrayList cqList = (ArrayList) cqdao.queryBySbbh(sbbh, conn);
        // �ָ���Ǩ������
        DAOFactory.getInstance().getJsblcqDAO().updateBcsye(sbbh, cqList, conn);
        // ɾ���걨��Ǩ������Ϣ
        sql = "delete from qsdb.qs_jl_sbcqgl where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbcqgl ");
        // ���ÿ����Ǩ��Ϣ���Ƿ����걨����������û��ɾ����Ǩ��Ϣ
        ArrayList delcqList = new ArrayList();
        for (int j = 0; j < cqList.size(); j++) {
            Jsblcq jsblcq = (Jsblcq) cqList.get(j);
            ArrayList sbcqList = DAOFactory.getInstance().getSbcqglDAO()
                                 .queryByCqxyh(jsblcq.cqxyh, conn);
            if ((sbcqList == null) || (sbcqList.size() == 0)) {
                delcqList.add(jsblcq);
            }
        }
        cqdao.delete(delcqList, conn);
        Debug.out("after delete jsblcqxx ");
        // ����ס����Ϣ
        JsblgyzfDAO gyzfdao = DAOFactory.getInstance().getJsblgyzfDAO();
        ArrayList gyzfList = (ArrayList) gyzfdao.getBySbbh(sbbh, conn);
        // �ָ��ѹ�����ס���ĵֿ۶�
        gyzfdao.updateDksye(sbbh, gyzfList, conn);
        // �걨���ж�����Ϣ
        sql = "delete from qsdb.qs_jl_sbgyzf where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbgyzf ");
        // ���ÿ������ס����Ϣ���Ƿ����걨����������û��ɾ������ס����Ϣ
        ArrayList delgyList = new ArrayList();
        for (int k = 0; k < gyzfList.size(); k++) {
            Jsblgyzf yggyzf = (Jsblgyzf) gyzfList.get(k);
            ArrayList sbgyzfList = DAOFactory.getInstance().getSbgyzfDAO()
                                   .queryByHth(yggyzf.yggyzfqszsh, conn);
            if ((sbgyzfList == null) || (sbgyzfList.size() == 0)) {
                delgyList.add(yggyzf);
            }
        }
        gyzfdao.delete(delgyList, conn);

        Debug.out("after delete jsblgyzf ");
        // ���ݻ�����Ϣ
        TufwxxDAO fwdao = DAOFactory.getInstance().getTufwxxDAO();
        Tufwxx fw = (Tufwxx) fwdao.getBySbbh(sbbh, conn);
        // �걨������Ϣ����
        sql = "delete from qsdb.qs_jl_sbtdfwgl where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbtdfwgl ");
        if (fw != null) {
            ArrayList delfwList = new ArrayList();
            delfwList.add(fw);
            fwdao.delete(delfwList, conn);
        }
        Debug.out("after delete tufwxx ");
        // ɾ�����������Ϣ
        sql = "delete from qsdb.qs_jl_spjgxx where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        // Debug.out("after delete spjgxx ");

        // �걨������Ϣ
        sql = "delete from qsdb.qs_jl_sbzb where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbzb  ");
    }

    /**
     * ��ѯ�걨״̬
     *
     * @param vo
     *            VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuerySbzt(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        QuerySbzbBo bo = new QuerySbzbBo();
        try {
            // vo.getUserData();
            bo = (QuerySbzbBo) vo.getData();
            System.out.println("bo.getSbbh()" + bo.getSbbh());
            // ��ȡ����
            conn = QSDBUtil.getConnection();
            // ��ѯ������ȡ�걨��ţ��걨״̬�����˷Ǹ��˱�ʶ���Ƿ񽻻�
            Sbzb sbzb = new Sbzb();
            // ��bo��ȡ����ѯ�����걨��ţ�Ȼ��ֵ���걨����
            sbzb.sbbh = bo.getSbbh();
            // ����SbzbDAO��get����
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            if (sbzb != null) {
                // ����SbzbDAO�з��ص�sbzbֵ����bo
                bo.setSbbh(sbzb.sbbh);
                Debug.out("sbzb.sbbh:" + sbzb.sbbh);
                bo.setSbrq(sbzb.sbrq);
                bo.setZtbs(sbzb.ztbs);
                if (sbzb.dfsbbh == sbzb.sbbh) {
                    bo.setExistFwjh(true);
                } else {
                    bo.setExistFwjh(false);
                }
                if (Constants.ZB_YHBS_GR.equals(sbzb.yhbs)) {
                    bo.setPerson(true);
                } else {
                    bo.setPerson(false);
                }
                // ��ѯ�걨���ع������ж��Ƿ�¼�뷿����Ϣ
                Tufwxx tufwxx = new Tufwxx();
                tufwxx.sbbh = bo.getSbbh();
                Debug.out("tufwxx.sbbh 1:" + tufwxx.sbbh);
                tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO()
                         .getBySbbh(tufwxx.sbbh, conn);
                Debug.out("tufwxx:" + tufwxx);
                if (tufwxx == null) {
                    bo.setExistFtxx(false);
                } else {
                    bo.setExistFtxx(true);
                }

                // ��ѯ�걨��Ǩ�������ж��Ƿ�¼���Ǩ��Ϣ
                ArrayList jsblcq = new ArrayList();

                jsblcq = DAOFactory.getInstance().getJsblcqDAO().queryBySbbh(
                        bo.getSbbh(), conn);
                Debug.out("jsblcq.size():" + jsblcq.size());
                if (jsblcq.size() == 0) {
                    bo.setExistCqxx(false);
                } else {
                    bo.setExistCqxx(true);
                }

                // ��ѯ�걨����ס���������ж��Ƿ�¼�빫��ס����Ϣ
                ArrayList jsblgyzf = new ArrayList();
                jsblgyzf = DAOFactory.getInstance().getJsblgyzfDAO().getBySbbh(
                        bo.getSbbh(), conn);
                Debug.out("jsblgyzf:" + jsblgyzf);
                Debug.out("jsblgyzf.size():" + jsblgyzf.size());
                if (jsblgyzf.size() == 0) {
                    bo.setExistGyzf(false);
                } else {
                    bo.setExistGyzf(true);
                }
                resultList.add(bo);
            }
        }

        catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }

    /**
     * ��ѯ�˶�֪ͨ�� ����ֻ��һ���걨���
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzs(VOPackage vo) throws BaseException {
        SbxxBo bo = (SbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doQueryHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����
            bo = (SbxxBo) doGet(vo);

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            if (bo.isBZ()) {
                hdtzsBo.setFwlxmc(bo.getVoTufwxx().getFlmc());
                hdtzsBo.setFwlxdm(bo.getVoTufwxx().getFldm());
            }
            return hdtzsBo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ѯ�˶�֪ͨ�� ����ֻ��һ���걨���
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyFwhm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doQueryHdtzsbyFwhm... " + bo.getFwhm());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("��ѯ�÷�α����ĺ˶�֪ͨ���Ƿ����");
            condition.append(" where FWHM='").append(bo.getFwhm()).append("'");
            // ��ѯ�÷�α����ĺ˶�֪ͨ���Ƿ����
            hdtzsList = DAOFactory.getInstance().getHdtzsDAO().query(
                    condition.toString(), conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return hdtzsList;

    }

    /**
     * ���º˶�֪ͨ��
     *
     * @param vo
     *            VOPackage
     */
    private void doUpdateHdtzs(VOPackage vo) throws BaseException {
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doUpdateHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("���º˶�֪ͨ���������ݷ�α����");
            // ���º˶�֪ͨ���������ݷ�α����
            DAOFactory.getInstance().getHdtzsDAO().updatefwhm(bo, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }
}

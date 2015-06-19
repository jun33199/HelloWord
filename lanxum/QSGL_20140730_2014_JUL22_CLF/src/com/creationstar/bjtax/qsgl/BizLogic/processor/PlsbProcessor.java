package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.DrpcInfoDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.DrzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.FgrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.GrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsfsDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbcqglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbtdfwglDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SpjgxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbcqgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbErrBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */
public class PlsbProcessor implements Processor {


    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is PlsbProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.QUERY_DRZB:
            result = doQueryDrzb(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.INSERT:
            result = doAdd(vo);
            break;
        case ActionType.CHECK:
            result = doCheck(vo);
            break;
        case ActionType.DELETE:
            doDelete(vo);
            break;
        case ActionType.DELETE_ALLDR:
            doDeletePc(vo);
            break;

        case ActionType.RECEIVE_DRZB:
            doReceive(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ���ݲ�ѯ������ѯ����������������
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        PlsbBo bo = new PlsbBo();
        try {
            //vo.getUserData();
            bo = (PlsbBo) vo.getData();
            //String conditions = CommonUtil.getSqlQueryCondition(bo);
            String conditions = "";
            conditions += " and rownum<202 ";
            conditions += " and a.drpch =" + " b.drpch ";
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and a.drpch = '" + bo.getDrpch() + "'";
            }
            if ((bo.getXh() != null) && (!bo.getXh().equals(""))) {
                conditions += " and a.xh = '" + bo.getXh() + "'";
            }
            if ((bo.getNsrmc() != null) && (!bo.getNsrmc().equals(""))) {
                conditions += " and a.nsrmc like '" + bo.getNsrmc() + "%'";
            }
            if ((bo.getDrsj() != null) && (!bo.getDrsj().equals(""))) {
                conditions += " and to_char(a.drsj,'yyyyMMdd') = '" +
                        bo.getDrsj() + "'";
            }
            if ((bo.getTgzjsjdm() != null) && (!bo.getTgzjsjdm().equals(""))) {
                conditions += " and b.tgzjsjdm = '" + bo.getTgzjsjdm() + "'";
            }
            if ((bo.getTgzmc() != null) && (!bo.getTgzmc().equals(""))) {
                conditions += " and b.tgzmc like '%" + bo.getTgzmc() + "%'";
            }
            if ((bo.getJsfsdm() != null) && (!bo.getJsfsdm().equals(""))) {
                conditions += " and b.jsfsdm = '" + bo.getJsfsdm() + "'";
            }

            if ((bo.getSl() != null) && (!bo.getSl().equals(""))) {
                if (bo.getSl().equals("unreceived")) {
                    conditions += " and a.ztbs != '" + Constants.DRZB_ZT_RECIVE +
                            "'";
                }
                if (bo.getSl().equals("received")) {
                    conditions += " and a.ztbs = '" + Constants.DRZB_ZT_RECIVE +
                            "'";
                }

            }
            //��������Ȩ�޿���
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "QSDB", "QS_JL_DRZB");
            Debug.out("datafilter: " + datafilter);
            conditions += " and " + datafilter;
            conditions += "order by a.drsj desc";
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getDrzbDAO().queryDrxx(
                    conditions, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�PlsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }


    /**
     * ����������Ϣ��ѯ�������������������(��δ����)
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doQueryDrzb(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        PlsbBo bo = new PlsbBo();
        try {
            //UserData ud = vo.getUserData();
            //vo.getUserData();
            bo = (PlsbBo) vo.getData();
            String conditions = "";
            if ((bo.getDrpch() != null) && (!bo.getDrpch().equals(""))) {
                conditions += " and drpch = '" + bo.getDrpch() + "'";
            }
            conditions += " and ztbs = '" + Constants.DRZB_ZT_CHECKED + "'";
            //��������Ȩ�޿���
//            UserData ud = vo.getUserData();
//            String datafilter = ZKAdapter.getInstance().getDataFilterString(
//                    ud, "QSDB", "QS_JL_SBZB");
//            Debug.out("datafilter: " + datafilter);
//            conditions += " and " + datafilter;

            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getDrzbDAO().query(
                    conditions, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰��������PlsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }


    /**
     * ��õ�����Ϣ
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {

        return null;
    }

    /**
     * �����ݴ��������������ʽ�����
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doAdd(VOPackage vo) throws BaseException {

        return null;
    }

    /**
     * ���������������������ݽ����߼���֤
     * ��֤�û������Ϣ(ͨ��commonutil.handleZRR)��
     * ��֤��Ǩ��Ϣ(�ȴ���ʽ����getcqxx,û�õ�ͬ�ŵ���֤ͨ��������к�������Ϣ���бȶԣ���������Ƿ�ƥ��ʹ˴�ʹ�ö��Ƿ񳬳�)
     * ��֤����ס����Ϣ(�ȴ���ʽ����getcqxx,û�õ�ͬ�ŵ���֤ͨ��������к�������Ϣ���бȶԣ���������Ƿ�ƥ��ʹ˴�ʹ�ö��Ƿ񳬳�)��
     * ��֤����������Ϣ()
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     * TODO A ��Ҫ�޸�check�������õ����������PldrBo��У���Ƿ������⣬Ȼ����������PldrBo�ŵ�list�з��ء�
     */

    private Object doCheck(VOPackage vo) throws BaseException {
        Connection conn = null;
        conn = QSDBUtil.getConnection();
        HashMap errorMap = new HashMap();
        try {
            UserData ud = vo.getUserData();
            ArrayList checkList = new ArrayList();
            //list��ÿ������ΪpldrBo
            checkList = (ArrayList) vo.getData();

            //����pldrBo�еĶ���
            ArrayList cqxxList = new ArrayList();
            ArrayList gyzfxxList = new ArrayList();
            ArrayList fwjhxxList = new ArrayList();
            Grxx grxx = null;
            Fgrxx fgrxx = null;
            Spjgxx spjgxx = null;
            Tufwxx tufwxx = null;
            Jsblcq cq = null;
            Jsblgyzf gf = null;

            //�����������ʾ��Ϣ�ʹ�����Ϣ
            ArrayList spjeErrList = new ArrayList(); //���������Ϣ����֤����˰���ʹ˴��Ƿ����
            ArrayList spyyErrList = new ArrayList(); //��˰�������������֤����������Ƿ��Ѿ�ʹ��
            ArrayList sptsList = new ArrayList();

            //�����֤������Ϣlist����Ǩ�����������ݽ���������Ϣlist
            ArrayList grnsrxxErrList = new ArrayList(); //������Ϣ����˰�����ƺʹ˴β���
            ArrayList fgrnsrxxdmErrList = new ArrayList(); //�Ǹ�����Ϣ�м�����������
            ArrayList fgrnsrxxmcErrList = new ArrayList(); //�Ǹ�����Ϣ�и��ݴ˼������������˰��������˴β���

            ArrayList cqxxbceErrList = new ArrayList(); //��ǨЭ��Ż�ȡ��Ǩ��Ϣ����֤���б���Ĳ�Ǩ������ʹ˴�¼��Ĳ�Ǩ�������Ƿ����
            ArrayList cqxxsyeErrList = new ArrayList(); //��֤���б���Ĳ�Ǩ����ʣ�����ڴ˴β�Ǩʹ�öС�����쳣
            ArrayList gyzfcjjgErrList = new ArrayList(); //���ݹ���ס��Э��Ż�ȡ����ס����Ϣ����֤���б���Ĺ���ס���ɽ��۸�ʹ˴�¼��ĳɽ��۸��Ƿ����
            ArrayList gyzfsyeErrList = new ArrayList(); //��֤���б���Ĺ���ס��ʣ�����ڴ˴εֿ۶С�����쳣
            ArrayList fgrspErrList = new ArrayList(); //�Ǹ����걨��˰������˰������Ϊ���һ��ء���ҵ��λ������������µ�λ���ҹ���ԭ�����ڰ칫����ѧ��ҽ�ơ����л������ʩ������ģ�ϵͳӦ��ʾ����˰��Ӧ�Ȱ������˰�걨��������ٰ���˰����������������ܾ�����
            ArrayList tufwxxErrList = new ArrayList();
            ArrayList fwjhxxErrList = new ArrayList();

            //�������ݵ�arraylist
            ArrayList okList = new ArrayList();
            //ɾ�����ݵ�list
            ArrayList delList = new ArrayList();
            //״̬
            String zt = new String();

            //����dao
            SpjgxxDAO spDao = DAOFactory.getInstance().getSpjgxxDAO();
            JsblcqDAO cqDao = DAOFactory.getInstance().getJsblcqDAO();
            JsblgyzfDAO gfDao = DAOFactory.getInstance().getJsblgyzfDAO();
            DrzbDAO drzbDao = DAOFactory.getInstance().getDrzbDAO();

            //pch
            String pch = "";
            if (checkList != null && checkList.size() > 0) {
                for (int i = 0; i < checkList.size(); i++) {
                    //��Ϣ��֤״̬ 0(����) 1(�쳣)
                    zt = "0";
                    //��ȡ���ݹ����Ķ���
                    PldrBo bo = (PldrBo) checkList.get(i);
                    //������Ϣ
                    ArrayList grxxList = bo.getGrxxList();
                    //�Ǹ�����Ϣ
                    ArrayList fgrxxList = bo.getFgrxxList();
                    grxx = bo.getGrxx();
                    fgrxx = bo.getFgrxx();
                    spjgxx = bo.getSpjgxx();
                    tufwxx = bo.getTufwxx();
                    cqxxList = bo.getCqxxList();
                    gyzfxxList = bo.getGyzfxxList();
                    fwjhxxList = bo.getFwjhxxList();
                    pch = bo.getPch();

                    //������������ֵ����
                    PlsbErrBo plsbErrBo = new PlsbErrBo();

                    //����Ϣ������֤
                    //������Ϣ(��֤��˰�������͵Ǽ��Ƿ����)
                    for (int m = 0; m < grxxList.size(); m++) {
                        grxx = (Grxx) grxxList.get(m);
                        if (grxx != null && zt.equals("0")) {
                            grxx.setCjr(ud.getYhmc());
                            Timestamp now = new Timestamp(System.
                                    currentTimeMillis());
                            grxx.setCjrq(now);
                            try {
                                CommonUtil.handleZRR(grxx, ud);
                            } catch (ApplicationException ex) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                grnsrxxErrList.add(plsbErrBo);
                                zt = "1";
                            }
                        }
                    }
                    //�Ǹ�����Ϣ(��֤��˰�������͵Ǽ��Ƿ����)
                    for (int n = 0; n < fgrxxList.size(); n++) {
                        fgrxx = (Fgrxx) fgrxxList.get(n);
                        if (fgrxx != null && zt.equals("0")) {
                            try {
                                HashMap djinfo = CommonUtil.getFgrDjInfo(fgrxx.
                                        getJsjdm());
                                SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get("JBSJ");
                                if (!jbsj.getNsrmc().equals(fgrxx.getNsrmc())) {
                                    //����ļ���������ȡ����˰�������ʹ˴β���
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                    fgrnsrxxmcErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                            //����ļ�������벻���ڶ�Ӧ�ķǸ�����Ϣ
                            catch (Exception ex) {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                fgrnsrxxdmErrList.add(plsbErrBo);
                                zt = "1";
                            }
                            if (!fgrxx.nsrlxdm.equals("05") && zt.equals("0")) {
                                //��˰�����Ͳ�������
                                if (tufwxx.fldm.equals("01") ||
                                    tufwxx.fldm.equals("02") ||
                                    tufwxx.fldm.equals("03") ||
                                    tufwxx.fldm.equals("04") ||
                                    tufwxx.fldm.equals("05")) {
                                    //����ԭ�����ڰ칫����ѧ��ҽ�ơ����л������ʩ�����
                                    if (spjgxx == null) {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                        plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                        plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                        fgrspErrList.add(plsbErrBo);
                                        zt = "1";
                                    }
                                }
                            }
                        }
                    }
                    if (tufwxx != null && zt.equals("0")) {
                        Date nowTime = new Date(System.currentTimeMillis());
                        Timestamp inputTime = tufwxx.getHtqdsj();
                        String now = CommonUtil.getDatetime(nowTime, "yyyyMMdd");
                        String input = DataConvert.TimeStamp2String(inputTime);
                        if (input.compareTo(now) > 0) {
                            //���ڵ�ǰʱ��
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                            tufwxxErrList.add(plsbErrBo);
                            zt = "1";

                        }
                    }
                    //���������Ϣ
                    if (spjgxx != null && zt.equals("0")) {
                        HashMap map = CommonUtil.getZcspjg(spjgxx.getHdtzszh());

                        //�ӿڻ�ô���Ϣ�����û���д����
                        if (map != null) {
                            spjgxx.setJmlydm((String) map.get("qtjmly"));
                            if (spjgxx.getJmsje().doubleValue() !=
                                (((BigDecimal) map.get("jmse")).doubleValue())) {
                                //�ͽӿ�ȡ�������ݼ����һ��
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                spjeErrList.add(plsbErrBo);
                                zt = "1";
                            }
                        }
                        //���ݿ��д���Ϣ�Ѿ�ʹ��
                        Spjgxx spjgxx1 = (Spjgxx) spDao.get(spjgxx, conn);
                        if (spjgxx1.getHdtzszh() != null && !zt.equals("1")) {
                            //���ݿ��д�������Ϣ�Ѿ�ʹ��
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                            spyyErrList.add(plsbErrBo);
                            zt = "1";
                        }

                        //�ӿ�û�õ�������Ϣ
                        if (map == null && !zt.equals("1")) {
                            //�ӽӿ�û�л�ȡ����������,��ʾ�˶�������Ϣ
                            if (grxx != null) {
                                plsbErrBo.setNsrmc(grxx.getNsrmc());
                            } else {
                                plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                            }
                            plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                            plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                            sptsList.add(plsbErrBo);
                        }

                    }

                    //��Ǩ��Ϣ
                    if (cqxxList != null && cqxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int j = 0; j < cqxxList.size(); j++) {
                            cq = (Jsblcq) cqxxList.get(j);
                            Jsblcq cq1 = (Jsblcq) cqDao.get(
                                    cq, conn);
                            if (cq1 == null) {
                                //�µĲ�Ǩ��Ϣ
                                break;
                            }
                            if (cq1.cqbce.doubleValue() !=
                                cq.cqbce.doubleValue()) {
                                //�˴εĲ�Ǩ������Ϳ��б���Ĳ����
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                cqxxbceErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }

                            if (cq1.cqbcsye.doubleValue() <
                                cq.bcsybce.doubleValue()) {
                                //�˴εĲ�Ǩʹ�ö����ʣ��Ĳ�����
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                cqxxsyeErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }
                        }
                    }
                    //����ס����Ϣ
                    if (gyzfxxList != null && gyzfxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int k = 0; k < gyzfxxList.size(); k++) {
                            gf = (Jsblgyzf) gyzfxxList.get(k);
                            Jsblgyzf gf1 = (Jsblgyzf) gfDao.get(
                                    gf, conn);
                            if (gf1 == null) {
                                //�µĹ�����Ϣ
                                break;
                            }

                            if (gf1.cjjg.doubleValue() != gf.cjjg.doubleValue()) {
                                //�˴εĹ����ɽ��۸�Ϳ��б���Ĳ����
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }

                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                gyzfcjjgErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }

                            if (gf1.sye.doubleValue() < gf.bcdke.doubleValue()) {
                                //�˴ε�ʹ�ò��������ʣ��Ĳ�����
                                if (grxx != null) {
                                    plsbErrBo.setNsrmc(grxx.getNsrmc());
                                } else {
                                    plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                }
                                plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                gyzfsyeErrList.add(plsbErrBo);
                                zt = "1";
                                break;
                            }
                        }
                    }
                    //���ݽ�����Ϣ
                    if (fwjhxxList != null && fwjhxxList.size() > 0 &&
                        zt.equals("0")) {
                        for (int m = 0; m < fwjhxxList.size(); m++) {
                            //��ȡ���ݹ����Ķ���
                            PldrBo bo1 = (PldrBo) fwjhxxList.get(i);
                            Grxx grxx1 = bo1.getGrxx();
                            Fgrxx fgrxx1 = bo1.getFgrxx();
                            //������Ϣ(��֤��˰�������͵Ǽ��Ƿ����)
                            if (grxx1 != null && zt.equals("0")) {
                                try {
                                    CommonUtil.handleZRR(grxx1, ud);
                                } catch (ApplicationException ex) {
                                    if (grxx != null) {
                                        plsbErrBo.setNsrmc(grxx.getNsrmc());
                                    } else {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    }
                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                    fwjhxxErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                            //�Ǹ�����Ϣ(��֤��˰�������͵Ǽ��Ƿ����)
                            if (fgrxx1 != null && zt.equals("0")) {
                                try {
                                    HashMap djinfo = CommonUtil.getFgrDjInfo(
                                            fgrxx1.
                                            getJsjdm());
                                    SWDJJBSJ jbsj = (SWDJJBSJ) djinfo.get(
                                            "JBSJ");
                                    if (!jbsj.getNsrmc().equals(fgrxx1.getNsrmc())) {
                                        //����ļ���������ȡ����˰�������ʹ˴β���
                                        if (grxx != null) {
                                            plsbErrBo.setNsrmc(grxx.getNsrmc());
                                        } else {
                                            plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                        }
                                        plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                        plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());

                                        fwjhxxErrList.add(plsbErrBo);
                                        zt = "1";
                                    }
                                }
                                //����ļ�������벻���ڶ�Ӧ�ķǸ�����Ϣ
                                catch (Exception ex) {
                                    if (grxx != null) {
                                        plsbErrBo.setNsrmc(grxx.getNsrmc());
                                    } else {
                                        plsbErrBo.setNsrmc(fgrxx.getNsrmc());
                                    }

                                    plsbErrBo.setFdcxmdz(tufwxx.getTdfwzldz());
                                    plsbErrBo.setFdcxmmc(tufwxx.getFdcxmmc());
                                    fwjhxxErrList.add(plsbErrBo);
                                    zt = "1";
                                }
                            }
                        }
                    }

                    //check ��ɣ�������ʱ���������״̬
                    if (zt.equals("0")) {
                        Drzb drzb = new Drzb();
                        drzb.setDrpch(bo.getPch());
                        drzb.setXh(bo.getXh());
                        drzb.setZtbs(Constants.DRZB_ZT_CHECKED);
                        drzbDao.updateZt(drzb, conn);
                        okList.add(bo);
                    }
                    //ɾ����ʱ���е�����
                    if (zt.equals("1")) {
                        Drzb drzb = new Drzb();
                        drzb.setDrpch(bo.getPch());
                        drzb.setXh(bo.getXh());
                        delList.add(drzb);
                    }
                }
            }
            errorMap.put("nsrgr", grnsrxxErrList);
            errorMap.put("nsrfgrdm", fgrnsrxxdmErrList);
            errorMap.put("nsrfgrmc", fgrnsrxxmcErrList);
            errorMap.put("spts", sptsList);
            errorMap.put("spje", spjeErrList);
            errorMap.put("spyy", spyyErrList);
            errorMap.put("cqbce", cqxxbceErrList);
            errorMap.put("cqsye", cqxxsyeErrList);
            errorMap.put("gfcjjg", gyzfcjjgErrList);
            errorMap.put("gfsye", gyzfsyeErrList);
            errorMap.put("fgrsp", fgrspErrList);
            errorMap.put("fwjh", fwjhxxErrList);
            errorMap.put("tufwxx", tufwxxErrList);
            errorMap.put("ok", okList);
            //���ݵ������κţ�ɾ����ʱ���е�����
            /**TODO  B ����������Ҫ�޸ģ��޸�Ϊ�д���Ĳ�ɾ����������˲�ͨ���ı�־�����޸ĺ���±�־��
             * ͬʱ��okList�з������ݡ�ɾ��errorMap����ص�����
             * �������걨��Ϣ��ʱ�򣬲�ȡ��˲�ͨ����
             *
             **/
            if (delList != null && delList.size() > 0) {
                drzbDao.delete(delList, conn);
                String conditions = "";
                conditions += " and drpch = '" + pch + "'";
                ArrayList list = DAOFactory.getInstance().getDrzbDAO().query(
                        conditions, conn);
                if (list.size() == 0) {
                    Drpcinfo df = new Drpcinfo();
                    df.setDrpch(pch);
                    ArrayList pcinfoList = new ArrayList();
                    pcinfoList.add(df);
                    DAOFactory.getInstance().getDrpcInfoDAO().delete(pcinfoList,
                            conn);
                }
            }
            return errorMap;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰��������PlsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex,
                                                 "��˰��������PlsbProcessor�˶�����ʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * ɾ����������������������
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException {

        Connection conn = null;

        try {
            //vo.getUserData();
            //PlsbBo bo = (PlsbBo) vo.getData();
            ArrayList delList = new ArrayList();
            ArrayList zbList = new ArrayList();
            delList = (ArrayList) vo.getData();
            Drzb zb = new Drzb();
            UserData user = vo.getUserData();
            PlsbBo bo = new PlsbBo();
            bo = (PlsbBo) delList.get(0);
            zb = bo.getDrzb();
            String pch = zb.getDrpch();
            conn = QSDBUtil.getConnection();

            if (delList != null && delList.size() > 0) {
                for (int i = 0; i < delList.size(); i++) {
                    bo = (PlsbBo) delList.get(i);
                    zb = (Drzb) bo.getDrzb();
                    zbList.add(zb);
                }
                DAOFactory.getInstance().getDrzbDAO().delete(zbList, conn);
            }
            String conditions = "";
            conditions += " and drpch = '" + pch + "'";
            ArrayList list = DAOFactory.getInstance().getDrzbDAO().query(
                    conditions, conn);
            if (list.size() == 0) {
                Drpcinfo df = new Drpcinfo();
                df.setDrpch(pch);
                ArrayList pcinfoList = new ArrayList();
                pcinfoList.add(df);
                DAOFactory.getInstance().getDrpcInfoDAO().delete(pcinfoList,
                        conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�PlsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return null;
    }

    /**
     * ����������Ϣ
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doReceive(VOPackage vo) throws BaseException {
        Connection conn = null;
        conn = QSDBUtil.getConnection();
        try {
            UserData ud = vo.getUserData();
            ArrayList receiveList = new ArrayList();
            //list��ÿ������ΪpldrBo
            receiveList = (ArrayList) vo.getData();

            //����pldrBo�Ķ���
            ArrayList cqxxList = new ArrayList();
            ArrayList gyzfxxList = new ArrayList();
            ArrayList fwjhxxList = new ArrayList();
            ArrayList grxxList = new ArrayList();
            ArrayList fgrxxList = new ArrayList();
            Grxx grxx = null;
            Fgrxx fgrxx = null;
            Spjgxx spjgxx = null;
            Tufwxx tufwxx = null;
            Jsblcq cq = null;
            Jsblgyzf gf = null;

            PldrBo bo = null;
            Sbzb sbzb = null;

            //ϵͳ���õ��ĵ�ǰʱ��,¼���˵���Ϣ
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String sbbh = null;
            String yh = ud.yhmc;
            String nd = String.valueOf(Calendar.getInstance().get(
                    Calendar.YEAR));

            //����dao
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            GrxxDAO grxxDao = DAOFactory.getInstance().getGrxxDAO();
            FgrxxDAO fgrxxDao = DAOFactory.getInstance().getFgrxxDAO();
            SpjgxxDAO spjgxxDao = DAOFactory.getInstance().getSpjgxxDAO();
            TufwxxDAO tufwxxDao = DAOFactory.getInstance().getTufwxxDAO();
            SbtdfwglDAO sbtdfwglDao = DAOFactory.getInstance().getSbtdfwglDAO();
            JsblcqDAO cqDao = DAOFactory.getInstance().getJsblcqDAO();
            JsblgyzfDAO gfDao = DAOFactory.getInstance().getJsblgyzfDAO();
            DrzbDAO drzbDao = DAOFactory.getInstance().getDrzbDAO();
            DrpcInfoDAO drpcDao = DAOFactory.getInstance().getDrpcInfoDAO();
            SbcqglDAO sbcqglDao = DAOFactory.getInstance().getSbcqglDAO();
            SbgyzfDAO sbgyzfDao = DAOFactory.getInstance().getSbgyzfDAO();
            JsfsDAO jsfsDao = DAOFactory.getInstance().getJsfsDAO();

            String pch = new String();

            if (receiveList != null && receiveList.size() > 0) {

                for (int i = 0; i < receiveList.size(); i++) {
                    //��Ϣ��֤״̬ 0(����) 1(�쳣)
                    //��ȡ���ݹ����Ķ���
                    bo = (PldrBo) receiveList.get(i);
                    grxxList = bo.getGrxxList();
                    fgrxxList = bo.getFgrxxList();
                    grxx = bo.getGrxx();
                    fgrxx = bo.getFgrxx();
                    spjgxx = bo.getSpjgxx();
                    tufwxx = bo.getTufwxx();
                    cqxxList = bo.getCqxxList();
                    gyzfxxList = bo.getGyzfxxList();
                    fwjhxxList = bo.getFwjhxxList();
                    pch = bo.getPch();
                    //�������κŻ�ȡ��˰��ʽ���������
                    Drpcinfo drpcInfo = new Drpcinfo();
                    drpcInfo.setDrpch(pch);
                    drpcInfo = (Drpcinfo) drpcDao.get(drpcInfo, conn);
                    String jsfsdm = drpcInfo.getJsfsdm();
                    String jsfsmc = drpcInfo.getJsfmc();

                    //��ȡ�걨���
                    sbbh = CommonUtil.getPLBH(ud.getXtsbm1(), conn);
                    //�걨������Ϣ
                    //�����걨��������
                    sbzb = new Sbzb();

                    sbzb.blbs = Constants.ZB_BLBS_FBL;
                    sbzb.bljmsbs = Constants.ZB_BLJMSBS_BFHBLTJ;
                    sbzb.bz = tufwxx.getBz();
                    sbzb.cjr = yh;
                    sbzb.cjrq = now;
                    sbzb.fwtdbmdm = tufwxx.getFwtdbmdm();
                    //----add by jiq at 20061211-------------
                    if("1".equals(tufwxx.getSetz())||"5".equals(tufwxx.getSetz())||"6".equals(tufwxx.getSetz())){
                    	sbzb.setz = tufwxx.getSetz();
                    }else{
                    	sbzb.setz = bo.getSetz();
                    }
                    
                    //---------------------------------------
                    sbzb.jsfsdm = jsfsdm;
                    sbzb.jsfsmc = jsfsmc;
                    //����ҵ���ж�ȷ���ɽ��۸�
                    sbzb.jsje = new BigDecimal(CommonUtil.getCjjg(tufwxx));
                    sbzb.lrr = yh;
                    sbzb.lrrq = now;
                    if (grxx != null) {
                        sbzb.nsrlxdm = Constants.NSRLX_GR;
                    } else {
                        sbzb.nsrlxdm = fgrxx.getNsrlxdm();
                    }

                    sbzb.sbbh = sbbh;
                    sbzb.sbrq = now;

                    //----add by zhangyj at 20081030-------------
                    if ((grxx != null) && (sbzb.getSetz().equals("5"))) {
                        sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.
                                ZCWH_SCGMPTZFSL, conn));
                    } else {
                        sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.
                                ZCWH_SL, conn));
                    }
                    //---------------------------------------

                    sbzb.swjgzzjgdm = ud.getSsdwdm();
                    if (grxx != null) {
                        sbzb.yhbs = Constants.YHBZ_GR; //����
                    } else {
                        sbzb.yhbs = Constants.ZB_YHBS_FGR; ;
                    }
                    sbzb.ynse = new BigDecimal(0);
                    sbzb.zsrymc = ud.getYhmc();
                    sbzb.ztbs = Constants.ZB_ZTBS_DY;
                    sbzb.pzzhdm = ud.getXtsbm1();
                    sbzb.pzzhmc = ud.getXtsbm2();
                    sbzb.drpch = bo.getPch();
                    //insert sbzb
                    sbzb.setSjly("05");
                    sbzbDao.insert(sbzb, conn);

                    //����Ϣ���й���
                    //������Ϣ(���������Ϣ)
                    for (int m = 0; m < grxxList.size(); m++) {
                        grxx = (Grxx) grxxList.get(m);
                        if (grxx != null) {
                            grxx.cjr = yh;
                            grxx.cjrq = now;
                            grxx.fwjhbs = Constants.FFWJHBS;
                            grxx.lrr = yh;
                            grxx.lrrq = now;
                            grxx.sbbh = sbbh;
                            //��Ȩ�����ͣ�Ĭ������Ȩ��
                            //grxx.cqrlx = Constants.CQRLX_ZCQR;

                            //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                            grxx = CommonUtil.handleZRR(grxx, ud);
                            grxxDao.insert(grxx, conn);

                        }
                    }
                    //�Ǹ�����Ϣ(��֤��˰�������͵Ǽ��Ƿ����)
                    for (int k = 0; k < fgrxxList.size(); k++) {
                        fgrxx = (Fgrxx) fgrxxList.get(k);
                        if (fgrxx != null) {
                            fgrxx.cjr = yh;
                            fgrxx.cjrq = now;
                            fgrxx.fwjhbs = Constants.FFWJHBS;
                            fgrxx.lrr = yh;
                            fgrxx.lrrq = now;

                            fgrxx.sbbh = sbbh;
                            //insert fgrxx
                            fgrxxDao.insert(fgrxx, conn);
                        }
                    }
                    //���������Ϣ
                    if (spjgxx != null) {
                        spjgxx.cjr = yh;
                        spjgxx.cjrq = now;
                        //spjgxx.hdtzszh = bo.getHdtzszh();
                        if ((spjgxx.getJmlydm() == null) ||
                            (spjgxx.getJmlydm().equals(""))) {
                            spjgxx.jmlydm = "00";
                        }
                        //spjgxx.jmsje = bo.jmsje;
                        spjgxx.lrr = yh;
                        spjgxx.lrrq = now;
                        spjgxx.sbbh = sbbh;
                        spjgxxDao.insert(spjgxx, conn);
                    }

                    //���ط�����Ϣ
                    if (tufwxx != null) {
                        tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
                        tufwxx.nd = String.valueOf(Calendar.getInstance().get(
                                Calendar.YEAR));
                        tufwxx.setLrr(yh);
                        tufwxx.setCjr(yh);
                        tufwxx.setLrrq(now);
                        tufwxx.setCjrq(now);

                        //insert tufwxx
                        //System.out.println("tufwxx.rjl"+tufwxx.rjl);
                        tufwxxDao.insert(tufwxx, conn);
                        //insert sbzbftgl
                        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                        sbtdfwgl.cjr = yh;
                        sbtdfwgl.cjrq = now;
                        sbtdfwgl.lrr = yh;
                        sbtdfwgl.lrrq = now;
                        sbtdfwgl.sbbh = sbbh;
                        sbtdfwgl.tdfwid = tufwxx.tdfwid;
                        sbtdfwglDao.insert(sbtdfwgl, conn);
                    }

                    //��Ǩ��Ϣ
                    if (cqxxList != null && cqxxList.size() > 0) {
                        for (int j = 0; j < cqxxList.size(); j++) {
                            cq = (Jsblcq) cqxxList.get(j);
                            Jsblcq existJsblcq = (Jsblcq) cqDao.get(
                                    cq, conn);
                            if (existJsblcq != null) {
                                //update
                                existJsblcq.cqbcsye = new BigDecimal(
                                        existJsblcq.cqbcsye.doubleValue() -
                                        cq.bcsybce.doubleValue());
                                cqDao.update(existJsblcq, conn);
                            } else {
                                cq.cqbcsye = cq.cqbce.subtract(cq.bcsybce);
                                if (grxx != null) {
                                    cq.yhbs = Constants.YHBZ_GR; //����
                                } else {
                                    cq.yhbs = Constants.YHBZ_FGR; //�Ǹ���
                                }
                                cq.ztbs = "0";
                                cq.cjr = ud.yhmc;
                                cq.lrr = ud.yhmc;
                                cq.cjrq = now;
                                cq.lrrq = now;
                                cq.nd = nd;
                                cqDao.insert(cq, conn);
                            }

                            //construct the sbcqgl and insert
                            Sbcqgl sbcqgl = new Sbcqgl();
                            sbcqgl.cjr = yh;
                            sbcqgl.lrr = yh;
                            sbcqgl.cjrq = now;
                            sbcqgl.lrrq = now;
                            sbcqgl.cqxyh = cq.cqxyh;
                            sbcqgl.sbbh = sbbh;
                            sbcqgl.bcsybce = cq.bcsybce;
                            sbcqglDao.insert(sbcqgl, conn);

                            //��������������˰��ʶ
                            sbzbDao.updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR,
                                                sbbh, conn);
                        }
                    }
                    //����ס����Ϣ
                    if (gyzfxxList != null && gyzfxxList.size() > 0) {
                        for (int k = 0; k < gyzfxxList.size(); k++) {
                            gf = (Jsblgyzf) gyzfxxList.get(k);
                            Jsblgyzf existGyzf = (Jsblgyzf) DAOFactory.
                                                 getInstance().
                                                 getJsblgyzfDAO().get(
                                    gf, conn);

                            if (existGyzf != null) {
                                existGyzf.setSye(new BigDecimal(existGyzf.
                                        getSye().doubleValue() -
                                        gf.bcdke.doubleValue()));

                                gfDao.update(existGyzf, conn);
                            } else {
                                //construct the jsblgyzf and insert
                                gf.sye = gf.cjjg.subtract(gf.bcdke);
                                gf.ztbs = "0";
                                gf.cjr = yh;
                                gf.lrr = yh;
                                gf.cjrq = now;
                                gf.lrrq = now;
                                gf.nd = nd;

                                gfDao.insert(gf, conn);
                            }
                            //construct the sbgyzf and insert
                            Sbgyzf sbgyzf = new Sbgyzf();
                            sbgyzf.yggyzfqszsh = gf.yggyzfqszsh;
                            sbgyzf.bcdke = gf.bcdke;
                            sbgyzf.lrr = yh;
                            sbgyzf.cjr = yh;
                            sbgyzf.cjrq = now;
                            sbgyzf.lrrq = now;
                            sbgyzf.sbbh = sbbh;
                            sbgyzfDao.insert(sbgyzf, conn);

                            //��������������˰��ʶ
                            bo.setSbzb(sbzb);
                            JghdsjBo hdbo = CommonUtil.getJZSE(bo, conn);
                            //��ȡ�ѹ�����ס����
                            BigDecimal sjyz = hdbo.getSjyz(); //Ӧ����˰
                            BigDecimal kcqyzfx = hdbo.getGyzfjmje();
                            //��������������˰��ʶ,�������˰��ֿ۳��۹��������ʵ��Ӧ��˰��Ϊ0ʱ
                            if (kcqyzfx != null && kcqyzfx.doubleValue() > 0) {
                                if (sjyz != null && sjyz.doubleValue() <= 0 &&
                                    !
                                    Constants.ZB_BLJMSBS_FHBL_WLR.equals(bo.getSbzb().
                                        getBljmsbs())) {
                                    sbzbDao.updateJmsbs(Constants.
                                            ZB_BLJMSBS_FHBL_WLR, sbbh, conn);
                                }
                            }

                        }
                    }
                    //���ݽ�����Ϣ
                    if (fwjhxxList != null && fwjhxxList.size() > 0) {
                        for (int m = 0; m < fwjhxxList.size(); m++) {
                            //��ȡ���ݹ����Ķ���
                            PldrBo bo1 = (PldrBo) fwjhxxList.get(i);

                            //����һ��Sbzb��ֵ����
                            sbzb = new Sbzb();

                            sbzb.sbbh = CommonUtil.getPLBH(ud.getXtsbm1(), conn);
                            sbzb.blbs = Constants.ZB_BLBS_FBL;
                            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BZ;
                            sbzb.bz = tufwxx.getBz();
                            sbzb.cjr = yh;
                            sbzb.cjrq = now;
                            sbzb.fwtdbmdm = tufwxx.getFwtdbmdm();
                            sbzb.jsfsdm = Constants.WSZ_JSFS_XJ;
                            sbzb.jsfsmc = "�ֽ�";
                            sbzb.jsje = new BigDecimal(0);
                            sbzb.lrr = yh;
                            sbzb.lrrq = now;
                            sbzb.sbrq = now;
                            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(
                                    Constants.ZCWH_SL, conn));
                            sbzb.swjgzzjgdm = ud.getSsdwdm();
                            if (bo1.getGrxx() != null) {
                                sbzb.yhbs = Constants.YHBZ_GR; //����
                                sbzb.nsrlxdm = Constants.NSRLX_GR;
                            }
                            if (bo1.getFgrxx() != null) {
                                sbzb.yhbs = Constants.YHBZ_FGR; //�Ǹ���
                                sbzb.nsrlxdm = bo1.getFgrxx().getNsrlxdm();
                            }

                            sbzb.ynse = new BigDecimal(0);
                            sbzb.zsrymc = yh;
                            sbzb.ztbs = Constants.ZB_ZTBS_DY;
                            sbzb.pzzhdm = ud.getXtsbm1();
                            sbzb.pzzhmc = ud.getXtsbm2();
                            sbzb.setDfsbbh(sbbh);
                            DAOFactory.getInstance().getSbzbDAO().insert(sbzb,
                                    conn);

                            if (bo1.getGrxx() != null) {

                                //������Ϣ����
                                grxx = bo1.getGrxx();
                                grxx.sbbh = sbzb.getSbbh();
                                grxx.fwjhbs = Constants.FWJHBS;
                                grxx.setLrr(yh);
                                grxx.setCjr(yh);
                                grxx.setLrrq(now);
                                grxx.setCjrq(now);
                                //��Ȩ�����ͣ�Ĭ������Ȩ��
                                grxx.cqrlx = Constants.CQRLX_ZCQR;
                                //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                                grxx = CommonUtil.handleZRR(grxx, ud);
                                grxxDao.insert(grxx, conn);
                            }
                            if (bo1.getFgrxx() != null) {

                                //�Ǹ�����Ϣ����
                                fgrxx = bo1.getFgrxx();
                                fgrxx.sbbh = sbzb.getSbbh();
                                fgrxx.fwjhbs = Constants.FWJHBS;
                                fgrxx.setLrr(yh);
                                fgrxx.setCjr(yh);
                                fgrxx.setLrrq(now);
                                fgrxx.setCjrq(now);

                                fgrxxDao.insert(
                                        fgrxx, conn);

                            }

                            //�������ط�����Ϣ
                            tufwxx = bo1.getTufwxx();
                            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);

                            tufwxx.setLrr(yh);
                            tufwxx.setCjr(yh);
                            tufwxx.setLrrq(now);
                            tufwxx.setCjrq(now);
                            tufwxx.setNd(nd);

                            tufwxxDao.insert(
                                    tufwxx, conn);

                            //���롰�걨���������ط��ݹ���������Ϣ
                            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
                            sbtdfwgl.sbbh = sbzb.getSbbh();
                            sbtdfwgl.tdfwid = tufwxx.tdfwid;
                            sbtdfwgl.setLrr(yh);
                            sbtdfwgl.setCjr(yh);
                            sbtdfwgl.setLrrq(now);
                            sbtdfwgl.setCjrq(now);

                            sbtdfwglDao.insert(
                                    sbtdfwgl, conn);
                            //����ԭ�걨���еĶԷ��걨���
                            DAOFactory.getInstance().getSbzbDAO().updateDfsbbh(
                                    sbbh, sbzb.getSbbh(), conn);
                            if (bo1.getGrxx() != null) {
                                //����ԭ�걨����˵ķ��ݽ�����ʶ
                                grxxDao.
                                        updateFwjhbs(Constants.
                                        FWJHBS,
                                        sbbh, conn);
                            } else {
                                //����ԭ�걨����˵ķ��ݽ�����ʶ
                                fgrxxDao.
                                        updateFwjhbs(Constants.
                                        FWJHBS,
                                        sbbh, conn);
                            }
                        }
                    }

                    //������ɻ����걨��ź͸���״̬��ʾ
                    Drzb drzb = new Drzb();
                    drzb.setDrpch(bo.getPch());
                    drzb.setXh(bo.getXh());
                    drzb.setSbbh(sbbh);
                    drzb.setZtbs(Constants.DRZB_ZT_RECIVE);
                    drzbDao.updateAll(drzb, conn);
                    if (drpcInfo.getTjsj() == null) {
                        //update������Ϣ�е��ύʱ��
                        drpcInfo.setTjsj(now);
                        drpcDao.updateTjsj(drpcInfo, conn);
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰��������PlsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex,
                                                 "��˰��������PlsbProcessor��������ʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * ɾ����������������������
     * @param vo VOPackage
     * @return ArrayList of JsblBo
     * @throws BaseException
     */
    private Object doDeletePc(VOPackage vo) throws BaseException {

        Connection conn = null;

        try {
            String pch = (String) vo.getData();
            conn = QSDBUtil.getConnection();
            DAOFactory.getInstance().getDrzbDAO().deletePc(pch, conn);
            DAOFactory.getInstance().getDrpcInfoDAO().deletePc(pch,
                    conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�PlsbProcessor��ɾ�����ݲ�������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return null;
    }

}

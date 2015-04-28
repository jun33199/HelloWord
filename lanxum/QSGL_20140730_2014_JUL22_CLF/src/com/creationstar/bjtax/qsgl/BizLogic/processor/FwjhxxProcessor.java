package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
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
 * <p>Description: ����ά����Processor�� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author ������
 * @version 1.0
 */
public class FwjhxxProcessor extends CommonProcessor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is FtxxProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.INSERT:
            result = doAdd(vo);
            break;
        case ActionType.MODIFY:
            result = doModify(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            FwjhxxBo fwjhxxBo = (FwjhxxBo) vo.getData();
            UserData ud = vo.getUserData();
            //����һ��Sbzb��ֵ����
            Sbzb sbzb = new Sbzb();

            //�����е���Ϣ����Sbzb��ֵ����
            //��ȡ�걨���
            String sbbh = CommonUtil.getSBBH(ud.getXtsbm1(), conn);
            Debug.out("get sbbh: " + sbbh);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sbzb.sbbh = sbbh;
            sbzb.blbs = Constants.ZB_BLBS_FBL;
            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BZ;
            sbzb.bz = fwjhxxBo.getBz();
            sbzb.cjr = ud.getYhmc();
            sbzb.cjrq = now;
            sbzb.fwtdbmdm = fwjhxxBo.getFcjslh();
            sbzb.jsfsdm = fwjhxxBo.getJkfsdm();
            sbzb.jsfsmc = fwjhxxBo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.sbrq = now;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            if (fwjhxxBo.getJhperson().equals("0")) {
                sbzb.yhbs = Constants.YHBZ_GR; //����
                sbzb.nsrlxdm = Constants.NSRLX_GR;
            }
            if (fwjhxxBo.getJhperson().equals("1")) {
                sbzb.yhbs = Constants.YHBZ_FGR; //�Ǹ���
                sbzb.nsrlxdm = fwjhxxBo.getFgrxx().getNsrlxdm();
            }

            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();
            Debug.out("�Է��걨���: " + fwjhxxBo.getSbbh());
            sbzb.setDfsbbh(fwjhxxBo.getSbbh());
            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);

            if (fwjhxxBo.getJhperson().equals("0")) {
                Debug.out("����");
                //������Ϣ����
                List nsrList = fwjhxxBo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("fwjhProcessor bo nsrmc55: " + grxx.nsrmc);
                    grxx.sbbh = sbbh;
                    l.add(grxx);
                }

                //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                l = CommonUtil.handleZRR(l, ud);
                fwjhxxBo.setNsrList(l);
                //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("�Ǹ���");
                //�Ǹ�����Ϣ����
                Fgrxx fgrxx = fwjhxxBo.getFgrxx();
                fgrxx.sbbh = sbbh;
                fgrxx.fwjhbs = Constants.FWJHBS;
                fgrxx.setLrr(ud.yhmc);
                fgrxx.setCjr(ud.yhmc);
                fgrxx.setLrrq(new Timestamp(System.currentTimeMillis()));
                fgrxx.setCjrq(new Timestamp(System.currentTimeMillis()));

                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
                Debug.out("�Ǹ��� ok");
            }

            //�������ط�����Ϣ
            Tufwxx tufwxx = fwjhxxBo.getTufwxx();
            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
            Debug.out("get tdfwid: " + tufwxx.tdfwid);
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));

            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);

            //���롰�걨���������ط��ݹ���������Ϣ
            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
            sbtdfwgl.sbbh = sbbh;
            sbtdfwgl.tdfwid = tufwxx.tdfwid;
            sbtdfwgl.setLrr(ud.yhmc);
            sbtdfwgl.setCjr(ud.yhmc);
            sbtdfwgl.setLrrq(new Timestamp(System.currentTimeMillis()));
            sbtdfwgl.setCjrq(new Timestamp(System.currentTimeMillis()));

            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
            //����ԭ�걨���еĶԷ��걨���
            DAOFactory.getInstance().getSbzbDAO().updateDfsbbh(
                    fwjhxxBo.getSbbh(), sbzb.getSbbh(), conn);
            if (fwjhxxBo.getJhperson().equals("0")) {
                //����ԭ�걨����˵ķ��ݽ�����ʶ
                DAOFactory.getInstance().getGrxxDAO().updateFwjhbs(Constants.
                        FWJHBS,
                        fwjhxxBo.getSbbh(), conn);
            } else {
                //����ԭ�걨����˵ķ��ݽ�����ʶ
                DAOFactory.getInstance().getFgrxxDAO().updateFwjhbs(Constants.
                        FWJHBS,
                        fwjhxxBo.getSbbh(), conn);
            }

            return fwjhxxBo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FwjhxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doModify(VOPackage vo) throws BaseException {
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            FwjhxxBo fwjhxxBo = (FwjhxxBo) vo.getData();
            UserData ud = vo.getUserData();
            //����һ��Sbzb��ֵ����
            Sbzb sbzb = new Sbzb();
            sbzb.sbbh = fwjhxxBo.sbbh;
            Debug.out("FwjhProcessor update sbzb sbbh: " + sbzb.sbbh);
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();
            sbzb = (Sbzb) sbzbDao.get(sbzb, conn);
            //�����걨����
            Timestamp now = new Timestamp(System.currentTimeMillis());
            sbzb.fwtdbmdm = fwjhxxBo.getFcjslh();
            sbzb.jsfsdm = fwjhxxBo.getJkfsdm();
            sbzb.jsfsmc = fwjhxxBo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.sbrq = now;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            if (fwjhxxBo.getJhperson().equals("0")) {
                sbzb.yhbs = Constants.YHBZ_GR; //����
                sbzb.nsrlxdm = Constants.NSRLX_GR;
            }
            if (fwjhxxBo.getJhperson().equals("1")) {
                sbzb.yhbs = Constants.YHBZ_FGR; //�Ǹ���
                sbzb.nsrlxdm = fwjhxxBo.getFgrxx().getNsrlxdm();
            }

            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();

            sbzbDao.update(sbzb, conn);

            if (fwjhxxBo.getJhperson().equals("0")) {
                Debug.out("����");
                //�õ�������Ϣ,����зǸ�����Ϣ��Ϊ������Ϣ,�ͻ᲻��ɾ��ԭʼ��Ϣ??????
                DAOFactory.getInstance().getGrxxDAO().delete(fwjhxxBo.getSbbh(),
                        conn);

                //������Ϣ����
                List nsrList = fwjhxxBo.getNsrList();
                List l = new ArrayList();
                for (int i = 0; i < nsrList.size(); i++) {
                    Grxx grxx = (Grxx) nsrList.get(i);
                    grxx.cjr = ud.getYhmc();
                    grxx.cjrq = now;
                    grxx.fwjhbs = Constants.FFWJHBS;
                    grxx.lrr = ud.getYhmc();
                    grxx.lrrq = now;
                    Debug.out("fwjhProcessor bo nsrmc55: " + grxx.nsrmc);
                    grxx.sbbh = fwjhxxBo.getSbbh();
                    l.add(grxx);
                }

                //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                l = CommonUtil.handleZRR(l, ud);
                fwjhxxBo.setNsrList(l);
                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("�Ǹ���");
                //�Ǹ�����Ϣ����
                Fgrxx fgrxx = fwjhxxBo.getFgrxx();

                //ɾ����ǰ�걨��Ŷ�Ӧ�ķǸ�����Ϣ
                ArrayList delList = new ArrayList();
                delList.add(fgrxx);
                DAOFactory.getInstance().getFgrxxDAO().delete(delList, conn);

                fgrxx.sbbh = fwjhxxBo.sbbh;
                fgrxx.fwjhbs = Constants.FWJHBS;
                fgrxx.setLrr(ud.yhmc);
                fgrxx.setCjr(ud.yhmc);
                fgrxx.setLrrq(new Timestamp(System.currentTimeMillis()));
                fgrxx.setCjrq(new Timestamp(System.currentTimeMillis()));

                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
                Debug.out("�Ǹ��� ok");
            }

            //�������ط�����Ϣ
            Tufwxx tufwxx = fwjhxxBo.getTufwxx();
            Debug.out("get tdfwid: " + tufwxx.tdfwid);
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));

            DAOFactory.getInstance().getTufwxxDAO().update(tufwxx, conn);
            return fwjhxxBo;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FwjhxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}

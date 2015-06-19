package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.BaseDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jmsbb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qsjmlb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.OperationType;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbblBo;
import com.creationstar.bjtax.qsgl.model.bo.JmsbxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
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
 * ������˰��processor
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
public class JmsbProcessor implements Processor {
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

        Debug.out("--Debug-- Info : Here is JmsbProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doAdd(vo);

            break;

        case ActionType.QUERY:

            result = doQuery(vo);

            break;
        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.DELETE:

            doDelete(vo);

            break;
        case ActionType.PRINT_SBB:
            result = doPrintJmsbb(vo);
            break;

            // case ActionType.CONFIRM:
            // doConfirm(vo);
            // break;

            // case ActionType.REJECT:
            // doReject(vo);
            // break;
        case ActionType.PRINT_HDTZS:
            result = doPrintHdtzs(vo);
            break;
        case ActionType.Query_HDTZS:
            result = doQueryHdtzs(vo);
            break;
        case ActionType.Query_HDTZSBYFWHM:
            result = doQueryHdtzsbyFwhm(vo);
            break;
        case ActionType.UPDATE_HDTZS:
            doUpdateHdtzs(vo);
            break;
        case ActionType.CANCEL:
            doCancel(vo);
            break;
        case ActionType.Query_HDTZSBYHDTZSHM:
            result = doQueryHdtzsbyHdtzshm(vo);
            break;
        case ActionType.UPDATE_HDTZSHM_BY_HDTZSHM:
            doUpdateHdtzshmByHdtzshm(vo);
            break;
        case ActionType.ROLLBACK:
            doRollBack(vo);
            break;
        case ActionType.SAVE_CXXJMSP:
            doSavesp(vo);
            break;
        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
    private JmsbBo doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        JmsbBo bo = (JmsbBo) vo.getData();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        try {

            // vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            // ԭ�걨���
            String sbbh_old = bo.getSbbh();

            // ��ǰ̨��������bo�ڴ���֯�ɺ�̨������Ҫ�õ����ݣ��ֱ���б���
            /**
             * 1�Ȼ�ȡ����sbzb��Ҫ����Ϣ 2��ȡ������Ϣ��grxx����Ϣ 3��ȡ���ط�����Ϣ��tdfwxx
             * 4��ȡ�걨���������ء�������Ϣ��Ĺ�����SBTDFWGL��Ϣ 5ִ�в������
             */
            // �걨����
            Sbzb sbzb = getSbzb(bo, user);
            sbzb.setCjrq(now);

            // ��commonutil�л�ȡ�걨��ź͵�ǰʱ��,�赽����vo��
            // ��3�������걨���
            sbzb.setSbbh(CommonUtil.getJMSBBH2(user.getXtsbm1(), conn));

            // ������޸���
            // 1������˰�������֯�������롢������Ա���ơ�Ʊ֤�ʻ����롢Ʊ֤�ʻ����Ʋ���
            // 2����ɾ�����ٲ���ԭ�걨��ŵ��걨��
            if (bo.getYmczlxdm() == OperationType.MODIFY) {

                // 1������˰�������֯�������롢������Ա���ơ�Ʊ֤�ʻ����롢Ʊ֤�ʻ����Ʋ���
                JmsbxxBo jmsbxxBo = new JmsbxxBo();
                jmsbxxBo = (JmsbxxBo) doGetSbxxBo(sbbh_old);
                // ˰�������֯��������
                sbzb.setSwjgzzjgdm(jmsbxxBo.getVoSbzb().getSwjgzzjgdm());
                // ������Ա����
                sbzb.setZsrymc(jmsbxxBo.getVoSbzb().getZsrymc());
                // Ʊ֤�ʻ�����
                sbzb.setPzzhdm(jmsbxxBo.getVoSbzb().getPzzhdm());
                // Ʊ֤�ʻ�����
                sbzb.setPzzhmc(jmsbxxBo.getVoSbzb().getPzzhmc());

                // 2����ɾ�����ٲ���ԭ�걨��ŵ��걨��
                deleteSbxx(sbbh_old, conn);
                sbzb.setSbbh(sbbh_old);

            }

            // ������Ϣ
            List l = getGrxx(bo, user, sbzb.getSbbh(), sbzb.getCjrq());
            // ���ط�����Ϣ
            Tufwxx tufwxx = getTufwxx(bo, user);
            // �걨���ط��ݹ���
            Sbtdfwgl sbtdfwgl = getSbtdfwgl(bo, user);
            // �Ǹ�����Ϣ
            Fgrxx fgrxx = getFgrxx(bo, user);
            // ��˰�����걨��
            List al_jmsbb = getJmsbb(bo, user, sbzb);
            // Jmsbb jmsbb = getJmsbb(bo, user);

            // �����걨���
            sbtdfwgl.setSbbh(sbzb.getSbbh());
            fgrxx.setSbbh(sbzb.getSbbh());

            // ����ʱ��
            sbzb.setLrrq(sbzb.getCjrq());
            sbzb.setSbrq(bo.getSbrq());
            fgrxx.setLrrq(sbzb.getCjrq());
            fgrxx.setCjrq(sbzb.getCjrq());
            tufwxx.setCjrq(sbzb.getCjrq());
            tufwxx.setLrrq(sbzb.getCjrq());
            sbtdfwgl.setCjrq(sbzb.getCjrq());
            sbtdfwgl.setLrrq(sbzb.getCjrq());

            // ¼���� ������
            sbzb.setCjr(user.getYhmc());
            sbzb.setLrr(user.getYhmc());
            fgrxx.setCjr(user.getYhmc());
            fgrxx.setLrr(user.getYhmc());
            tufwxx.setCjr(user.getYhmc());
            tufwxx.setLrr(user.getYhmc());
            sbtdfwgl.setCjr(user.getYhmc());
            sbtdfwgl.setLrr(sbtdfwgl.getCjr());

            // ��ȡ�����걨���
            // jmsbb.setJmsbbh(CommonUtil.getJMSBBH(user.getXtsbm1(),
            // conn));//�����걨������걨������걨���һ��
            // jmsbb.setjmsbbh(jmsbb.getSbbh());

            // ��ȡ���ط���Ψһ��ʾ,�������ط�����Ϣ��tdfwxx
            // ���걨���������ء�������Ϣ��Ĺ�����SBTDFWGL
            tufwxx.setTdfwid(CommonUtil.getTDFWID(conn));
            Debug.out("tufwxx.setTdfwid in JmsbProcessor is "
                      + tufwxx.getTdfwid());
            sbtdfwgl.setTdfwid(tufwxx.getTdfwid());

            // ����ҵ���ж�ȷ���ɽ��۸�
            double cjjgrmb = CommonUtil.getCjjg(tufwxx);
            sbzb.setJsje(new BigDecimal(cjjgrmb));

            // ˰��
            sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL,
                    conn)));

            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                Debug.out("������Ϣ����");
                // ������Ϣ��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                // ���������
                // grxx = CommonUtil.handleZRR(grxx,user);
                // ��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                l = CommonUtil.handleZRR(l, user);
                bo.setNsrList(l);

                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("�Ǹ�����Ϣ����");
                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
            }

            // �������ط�����Ϣ
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);

            // ��������걨��Ϣ
            for (int i = 0; i < al_jmsbb.size(); i++) {

                // �����걨��
                Jmsbb jmsbb = (Jmsbb) al_jmsbb.get(i);

                DAOFactory.getInstance().getJmsbbDAO().insert(jmsbb, conn);

            }

            // ��viewҳ����Ҫ��ʾ����Ϣ���ص�ǰ̨
            bo.setSbbh(sbzb.getSbbh());
            bo.setCjrq(DataConvert.TimeStamp2String(sbzb.getCjrq()));

            // ���ɺ˶�֪ͨ��-start -
            // 2007-07-17������˼����Դ�ӡ�˶�֪ͨ�飬�ʽ����ɺ˶�֪ͨ��Ĳ������������걨��ʱ������

            Hdtzs hdtzs = new Hdtzs();

            String nd = DateUtil.getDate().substring(0, 4);

            // ����˶�֪ͨ��
            hdtzs.bzbs = Constants.BZBS_JM;
            hdtzs.cjr = user.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            // modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH_JMSB(user, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            // end modified

            hdtzs.jbr = user.yhmc;
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
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); // Ӧ����˰
            hdtzs.lrr = user.yhmc;
            hdtzs.lrrq = now;
            // ��ȡ��ϵ�绰
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                // hdtzs.sqr = bo.getVoZcqrxx().getNsrmc();
                hdtzs.sqr = ActionUtil.getNsrmc(bo.getNsrList(), null);
            } else {
                hdtzs.sqr = bo.getNsrmc();
            }
            //
            hdtzs.sbbh = bo.getSbbh();
            hdtzs.spfxmmc = bo.getFdcxmmc();
            hdtzs.zldi = bo.getTdfwzldz();

            // ����һ���˶�֪ͨ���¼
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            // ����˶�֪ͨ����ϸ��
            for (int i = 0; i < bo.getQsjmlbSelect().length; i++) {

                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(user.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(hdbo.getSjyz());
                hdjmmx.setLrr(user.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(bo.getQsjmlbSelect()[i]);

                // ��������������д�뱸ע
                if (Constants.CXXJM_JMXMDM_QT.equals(bo.getQsjmlbSelect()[i])) {
                    hdjmmx.setBz(bo.getQtjmlybeizhu());
                }

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

            // ���ɺ˶�֪ͨ��-end

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JmsbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }

    /**
     * ��ȡ�걨������Ϣ
     */
    private Sbzb getSbzb(JmsbBo bo, UserData user) {
        Sbzb sbzb = new Sbzb();
        // ��¼��ʶ
        sbzb.setBlbs(Constants.ZB_BLBS_FBL);
        // �������˰��ʶ
        sbzb.setBljmsbs(Constants.ZB_BLJMSBS_CXXJM);
        // ��ע
        sbzb.setBz(bo.getBeizhu());
        // �������ز��������
        sbzb.setFwtdbmdm(bo.getFwtdbmslh());
        // ��˰��ʽ
        sbzb.setJsfsdm(" ");
        // ��˰��ʽ
        sbzb.setJsfsmc(" ");
        // ��˰��� (Ĭ�Ϲ���Ϊ0)
        sbzb.setJsje(new BigDecimal(0));
        // �û���ʶ
        sbzb.setYhbs(bo.getYhbs());
        // ��˰�����ʹ���
        if (sbzb.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            sbzb.setNsrlxdm("99");
        } else {
            sbzb.setNsrlxdm(bo.getNsrlx());
        }

        // ˰��(Ĭ�Ϲ���Ϊ0)
        sbzb.setSl(new BigDecimal(0));

        // ˰�������֯��������
        sbzb.setSwjgzzjgdm(user.getSsdwdm());
        // ������Ա����
        sbzb.setZsrymc(user.getYhmc());
        // Ʊ֤�ʻ�����
        sbzb.setPzzhdm(user.getXtsbm1());
        // Ʊ֤�ʻ�����
        sbzb.setPzzhmc(user.getXtsbm2());

        // Ӧ��˰��(Ĭ�Ϲ���Ϊ0)
        sbzb.setYnse(new BigDecimal(0));
        // ״̬��ʶ
        sbzb.setZtbs(bo.getZtbs());

        return sbzb;
    }

    /**
     * ��ȡ������Ϣ����Ϣ
     */
    private List getGrxx(JmsbBo bo, UserData user, String sbbh, Timestamp now) throws
            BaseException {
        // ���������Ϣ
        List nsrList = bo.getNsrList();
        List l = new ArrayList();
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx grxx = (Grxx) nsrList.get(i);
            grxx.cjr = user.getYhmc();
            grxx.cjrq = now;

            if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
                grxx.setFwjhbs(Constants.FWJHBS);
            } else {
                grxx.setFwjhbs(Constants.FFWJHBS);
            }
            grxx.lrr = user.getYhmc();
            grxx.lrrq = now;
            Debug.out("BzProcessor bo nsrmc22: " + grxx.nsrmc);
            grxx.sbbh = sbbh;
            l.add(grxx);
        }

        // ��ϵ�绰
        // grxx.setLxdh(bo.getLxdh());
        // ��˰������
        // grxx.setNsrmc(bo.getNsrmc());
        // ֤������
        // grxx.setSfzjhm(bo.getSfzjhm());
        // ֤������
        // grxx.setSfzjlx(bo.getSfzjlx());
        // ֤����������
        // grxx.setSfzjlxmc(bo.getSfzjlxmc());
        // ��������,��������
        // grxx.setGjdm(bo.getGjdm());
        // grxx.setGjmc(bo.getGjmc());

        return l;
    }

    /**
     * �õ��Ǹ�����Ϣ��vo
     *
     * @param bo
     *            JmsbBo
     * @param user
     *            UserData
     * @return Fgrxx
     */
    private Fgrxx getFgrxx(JmsbBo bo, UserData user) {
        Fgrxx fgrxx = new Fgrxx();
        if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
            fgrxx.setFwjhbs(Constants.FWJHBS);
        } else {
            fgrxx.setFwjhbs(Constants.FFWJHBS);
        }

        fgrxx.setJsjdm(bo.getJsjdm());
        fgrxx.setKhyhdm(bo.getKhyhdm());
        fgrxx.setKhyhmc(bo.getKhyhmc());
        fgrxx.setLxrxm(bo.getLxrxm());
        fgrxx.setNsrlxdm(bo.getNsrlx());
        fgrxx.setNsrlxmc(bo.getNsrlxmc());
        fgrxx.setNsrmc(bo.getNsrmc());
        fgrxx.setYhzh(bo.getYhzh());
        fgrxx.setLxdh(bo.getLxdh());
        return fgrxx;
    }

    /**
     * ��ȡ��ȡ���ط�����Ϣ��tdfwxx
     */
    private Tufwxx getTufwxx(JmsbBo bo, UserData user) {
        Tufwxx tufwxx = new Tufwxx();
        try {
            // ��ע
            tufwxx.setBz(bo.getBeizhu());
            // ���ִ���
            tufwxx.setBzdm(bo.getBz());
            // �ɽ��۸�����ң�
            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()));
            // �ɽ��۸���ң�
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()));
            // ���ز���Ŀ����
            tufwxx.setFdcxmmc(bo.getFdcxmmc());
            // ����
            tufwxx.setFldm(bo.getFl());
            // ��������
            tufwxx.setFlmc(bo.getFlmc());
            // ���ݽ������
            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(bo.getFwjzmj()));
            // �������
            tufwxx.setFwlxdm(bo.getFwlb());
            // ����
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()));
            // ��ͬ����Լ��ǩ��ʱ��
            String date = bo.getHyqdsj();
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(date));
            // ���
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));
            // �����۸�����ң�
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()));
            // ���ء�����Ȩ��ת������
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx());
            // ���ء�����Ȩ��ת�����
            tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(bo
                    .getTdfwqszymj()));
            // ���ء����������ַ
            tufwxx.setTdfwzldz(bo.getTdfwzldz());
            Debug.out("bo.getZhyrmb()" + bo.getZhyrmb());
            // �ۺϼ۸�����ң�
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()));
            // ��������
            tufwxx.setFlmc(bo.getFlmc());
            // ���֣�����������ط���Ȩ��ת��
            tufwxx.setBzmc(bo.getBzmc());
            tufwxx.setFwlxmc(bo.getFwlbmc());
            tufwxx.setTdfwqszymc(bo.getTdfwqszylxmc());

            tufwxx.setRjl(bo.getRjl());
            tufwxx.setTdjc(bo.getTdjc());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tufwxx;

    }

    /**
     * ��ȡ�걨���������ء�������Ϣ��Ĺ�����SBTDFWGL��Ϣ
     */
    private Sbtdfwgl getSbtdfwgl(JmsbBo bo, UserData user) {
        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
        // ������
        sbtdfwgl.setCjr(user.getYhmc());
        // ¼����
        sbtdfwgl.setLrr(sbtdfwgl.getCjr());

        return sbtdfwgl;
    }

    /**
     * �õ���˰�����걨����Ϣ��vo
     *
     * @param bo
     *            JmsbBo
     * @param user
     *            UserData
     * @return Jmsbb
     */
    private List getJmsbb(JmsbBo bo, UserData user, Sbzb sbzb) {
        List al_jmsbb = new ArrayList();

        for (int i = 0; i < bo.getQsjmlbSelect().length; i++) {
            Jmsbb jmsbb = new Jmsbb();
            // ���ߴ���
            // jmsbb.setJmzcdm(bo.getQsjmlb());
            jmsbb.setJmzcdm(bo.getQsjmlbSelect()[i]);
            
            jmsbb.setJmxzdm(bo.getQsjmxzdm());
            // jmsbb.setJmzcdmSelect(bo.getQsjmlbSelect());
            // ��ӡ��ʶ
            jmsbb.setDybs(Constants.JMSBB_ZTBS_BC);
            // ״̬��ʶ
            jmsbb.setZtbs(Constants.JMSBB_ZTBS_BC);
            // ���
            jmsbb.setNd(DateUtil.getDate().substring(0, 4));
            // �����걨���
            jmsbb.setSbbh(sbzb.getSbbh());
            // ����ʱ��
            jmsbb.setCjrq(sbzb.getCjrq());
            jmsbb.setLrrq(sbzb.getCjrq());
            // ¼���� ������
            jmsbb.setCjr(user.getYhmc());
            jmsbb.setLrr(user.getYhmc());
            // ��������������д�뱸ע
            if (Constants.CXXJM_JMXMDM_QT.equals(bo.getQsjmlbSelect()[i])) {
                jmsbb.setBz(bo.getQtjmlybeizhu());
            }
            // ����list
            al_jmsbb.add(jmsbb);

        }
        return al_jmsbb;
    }

    /**
     * ��ѯ����
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        JmsbblBo bo = new JmsbblBo();
        try {
            // vo.getUserData();
            bo = (JmsbblBo) vo.getData();
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
            if ((bo.getHdtzsh() != null) && (!bo.getHdtzsh().equals(""))) {
                conditions += " and f.hdtzsh = '" + bo.getHdtzsh() + "'";
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

                resultList = DAOFactory.getInstance().getSbzbDAO().queryJmsb(
                        conditions, conn, bo.ifPersonal());
            } else { // û��ѡ����˰������
                // �Ȳ����
                resultList = DAOFactory.getInstance().getSbzbDAO().queryJmsb(
                        conditions, conn, true);
                // �ڼ��ϷǸ���
                resultList.addAll(DAOFactory.getInstance().getSbzbDAO()
                                  .queryJmsb(conditions, conn, false));
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JmsbProcessor����������",
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
     * ɾ�����������ļ�¼
     *
     * @param vo
     *            VOPackage
     * @throws BaseException
     */
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

                deleteSbxx(sbbh, conn);
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

        // ɾ���˶�֪ͨ���
        DAOFactory.getInstance().getHdtzsDAO().deleteHdtzsBySbbh(sbbh, conn);
        Debug.out("after delete hdtzs ");

        // �����걨���ɾ����˰�����걨���¼
        DAOFactory.getInstance().getJmsbbDAO().delete(sbbh, conn);
        Debug.out("after delete jmsbb ");

        // �걨������Ϣ
        sql = "delete from qsdb.qs_jl_sbzb where sbbh = '" + sbbh + "'";
        BaseDAO.execute(sql, conn);
        Debug.out("after delete sbzb  ");
    }

    /**
     * ���ݲ�ѯ�����ĸ�ҳ��,ƴ����Ӧ�Ĳ�ѯ����
     *
     * @param fromPage
     *            int 0���걨��ѯ 1��������ѯ 2����˲�ѯ 3�����˲�ѯ 4: �����걨��ѯ 5: �����걨��˲�ѯ
     *            6:�����걨������ѯ
     * @return String
     */
    private String getYwFilter(JmsbblBo bo) {
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

            // �������˰��ʶΪ3��
            sb.append("(a.bljmsbs = '" + Constants.ZB_BLJMSBS_CXXJM + "')");
            sb.append(" and (a.dfsbbh is null or a.dfsbbh ='') ");
            break;
        case 5:

            // ֻ�ܲ�ѯ�Ѵ�ӡ�걨��������������ͬ����걨���ݣ��������˰��ʶΪ3��
            sb.append("(a.ztbs = '2' and a.ztbs != '5') and (a.bljmsbs='"
                      + Constants.ZB_BLJMSBS_CXXJM
                      + "' and (a.dfsbbh is null or a.dfsbbh =''))");
            break;
        case 6:

            // ֻ�ܲ�ѯ�Ѵ�ӡ�˶�֪ͨ�顢������ͬ�⡢����ͬ����걨���ݣ��������˰��ʶΪ3��
            sb
                    .append(
                    "(a.ztbs = '4' or a.ztbs = '6' or a.ztbs = '5') and (a.bljmsbs='"
                    + Constants.ZB_BLJMSBS_CXXJM
                    + "' and (a.dfsbbh is null or a.dfsbbh =''))");
            break;
        default:
            break;
        }
        return sb.toString();

    }

    /**
     * ʵ����SbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        JmsbxxBo Bo = new JmsbxxBo();
        JmsbxxBo queryBo = (JmsbxxBo) vo.getData();
        String sbbh = queryBo.getSbbh();
        UserData ud = vo.getUserData();

        Connection conn = null;

        // ��ò��������ݽ�����Ϣ���걨bo
        Bo = (JmsbxxBo) doGetSbxxBo(sbbh);
        if (!Bo.isBZ()) { // �������ݲ�ȡ������Ϣ
            // ���Ϊ���ݽ�����Ϣ,��ȡ���Է��ķ�����Ϣ
            String dfSbbh = Bo.getVoSbzb().getDfsbbh();
            if (dfSbbh != null && !dfSbbh.equals("")) {
                Bo.setDfSbxxBo((FwjhxxBo) doGetFwjhxxBo(dfSbbh));
            }
        }

        try {
            conn = QSDBUtil.getConnection();
            // �����������������Ҫ�����������ء�����״̬list
            // д��
            ArrayList spjgList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                Swjgzzjg swjgzzjg = new Swjgzzjg();
                spjgList.add(swjgzzjg);
            }
            Swjgzzjg swjgzzjg = new Swjgzzjg();
            // ��ѯ�����־ִ���������
            swjgzzjg = CommonUtil.getSwjgzzjg(ud.getSsdwdm().substring(0, 2)
                                              + "00", conn);
            ((Swjgzzjg) spjgList.get(1))
                    .setSwjgzzjgdm(swjgzzjg.getSwjgzzjgdm());
            ((Swjgzzjg) spjgList.get(1))
                    .setSwjgzzjgmc(swjgzzjg.getSwjgzzjgmc());
            ((Swjgzzjg) spjgList.get(0)).setSwjgzzjgdm("9000");
            ((Swjgzzjg) spjgList.get(0)).setSwjgzzjgmc("�о�");
            Bo.setSpjgList(spjgList);

            ArrayList spztList = new ArrayList();
            for (int i = 0; i < 2; i++) {
                Jmsbb jmsbb_spzt = new Jmsbb();
                spztList.add(jmsbb_spzt);
            }
            ((Jmsbb) spztList.get(1)).setZtbs(Constants.ZB_ZTBS_JS_TY);
            ((Jmsbb) spztList.get(1)).setBz("ͬ��");
            ((Jmsbb) spztList.get(0)).setZtbs(Constants.ZB_ZTBS_JS_BTY);
            ((Jmsbb) spztList.get(0)).setBz("��ͬ��");
            Bo.setSpztList(spztList);
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

        JmsbxxBo Bo = new JmsbxxBo();

        Tufwxx voTufwxx = new Tufwxx();
        ArrayList cqList = new ArrayList();
        ArrayList gyList = new ArrayList();
        List nsrList = new ArrayList();

        ArrayList jmsbbList = new ArrayList();

        Connection conn = null;
        try {

            Debug.out("get sbxx sbbh: " + sbbh);
            conn = QSDBUtil.getConnection();

            // �õ�����������Ϣ
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(sbbh);
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            Debug.out("sbzbVo.getYhbs(): " + sbzbVo.getYhbs());
            Bo.setSbbh(sbzbVo.getSbbh());
            Bo.setVoSbzb(sbzbVo);
            Debug.out("Bo.getVoSbzb().getYhbs(): " + Bo.getVoSbzb().getYhbs());
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

            // ��ȡ�����걨��Ϣ
            jmsbbList = (ArrayList) DAOFactory.getInstance().getJmsbbDAO()
                        .query(" where jmsbbh = '" + sbbh + "'", conn);
            Bo.setJmsbbList(jmsbbList);

            if (jmsbbList != null && jmsbbList.size() > 0) {
                // Bo.setJmsbb((Jmsbb) jmsbbList.get(0));
                Bo.setSpjg(((Jmsbb) jmsbbList.get(0)).getSpjg());
                Bo.setSprq(((Jmsbb) jmsbbList.get(0)).getSprq());
                if (sbzbVo.getZtbs().equals(Constants.ZB_ZTBS_JS_TY)
                    || sbzbVo.getZtbs().equals(Constants.ZB_ZTBS_JS_BTY)) {
                    Bo.setSpzt(sbzbVo.getZtbs());
                }

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
     * ��ӡ�����걨��
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintJmsbb(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doPrintSbb... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            bo = (JmsbxxBo) doGet(vo);
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
                sbzbDao.update(Constants.ZB_ZTBS_DY_JMSBB, bo.getSbbh(), conn);
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
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨���գ�JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    // /**
     // * ���ͬ��
     // * 1�������걨���ʶ�������������
     // * 2�����¼����걨���е��������ء���������
     // * 3����������걨��
     // * @param vo
     // * VOPackage
     // */
    // private void doConfirm(VOPackage vo) throws BaseException {
    // JmsbxxBo bo = (JmsbxxBo) vo.getData();
    //
    // Connection conn = null;
    // Timestamp now = new Timestamp(System.currentTimeMillis());
    // Hdtzs hdtzs = new Hdtzs();
    // ArrayList jmsbbList = new ArrayList();
    //
    // try {
    // UserData ud = vo.getUserData();
    // String nd = DateUtil.getDate().substring(0, 4);
    // conn = QSDBUtil.getConnection();
    //
    // // update status of zb
    // DAOFactory.getInstance().getSbzbDAO().update(
    // Constants.ZB_ZTBS_JS_TY, bo.getSbbh(), conn);
    //			
    //			
    // String spjgdm = bo.getSpjg();
    // Timestamp sprq = bo.getSprq();
    // String sbbh = bo.getSbbh();
    // // �����걨��Ÿ��¼����걨���������
    // DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq, sbbh,
    // conn);
    //
    // } catch (Exception ex) {
    // // ����ʧ����Ϣ:����̨ �� ��־
    // Debug.printException(ex);
    // ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
    // new StackMsg2StringUtil(ex, Constants.STACK_MSG_CAP)
    // .getStackMsg(), "ʧ��");
    //
    // throw ExceptionUtil.getBaseException(ex);
    // } finally {
    // QSDBUtil.freeConnection(conn);
    // }
    // }

    // /**
     // * ��˲�ͬ��
     // * 1�������걨���ʶ�������������
     // * 2�����¼����걨���е��������ء���������
     // * 3��ɾ�������걨��
     // * @param vo
     // * VOPackage
     // */
    // private void doReject(VOPackage vo) throws BaseException {
    // JmsbxxBo bo = (JmsbxxBo) vo.getData();
    //
    // Connection conn = null;
    // ArrayList jmsbbList = new ArrayList();
    //
    // try {
    // conn = QSDBUtil.getConnection();
    //
    // // update status of zb
    // DAOFactory.getInstance().getSbzbDAO().update(
    // Constants.ZB_ZTBS_JS_BTY, bo.getVoSbzb().getSbbh(), conn);
    //			
    // String spjgdm = bo.getSpjg();
    // Timestamp sprq = bo.getSprq();
    // String sbbh = bo.getSbbh();
    // // �����걨��Ÿ��¼����걨���������
    // DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq, sbbh,
    // conn);
    //
    // } catch (Exception ex) {
    // // ����ʧ����Ϣ:����̨ �� ��־
    // Debug.printException(ex);
    // ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
    // new StackMsg2StringUtil(ex, Constants.STACK_MSG_CAP)
    // .getStackMsg(), "ʧ��");
    //
    // throw ExceptionUtil.getBaseException(ex);
    // } finally {
    // QSDBUtil.freeConnection(conn);
    // }
    // }

    /**
     * ��ӡ�˶�֪ͨ�� ����ֻ��һ���걨���
     *
     * @param vo
     *            VOPackage
     */
    private Object doPrintHdtzs(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doPrintHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����
            bo = (JmsbxxBo) doGet(vo);
            // ֻ�д�ӡ�걨�������£��Ÿ���״̬
            if (bo.getState().equals(Constants.ZB_ZTBS_DY_JMSBB)) {
                // ����״̬
                DAOFactory.getInstance().getSbzbDAO().update(
                        Constants.ZB_ZTBS_DY_HD, bo.getSbbh(), conn);
            }

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);

            hdtzsBo.setVoTufwxx(bo.getVoTufwxx());

            return hdtzsBo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ʵ����JmsbxxBo��������Ϊ��������ѯ������¼
     *
     * @return Object
     */
    private Object doGetHdtzs(VOPackage vo) throws BaseException {
        /**
         * ͨ��ȡ������JmsbxxBo �����ѯ����
         *
         * 1��ʹ��JmsbxxBo�����걨����Vo��DAO���ؽ���걨����Vo
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
        JmsbxxBo sbxxBo = (JmsbxxBo) vo.getData();

        HashMap nrMap = new HashMap();

        Connection conn = null;
        try {
            Debug.out("getHdtzs sbxx sbbh: " + sbxxBo.getSbbh());
            conn = QSDBUtil.getConnection();

            // ��ȡ�˶�֪ͨ����������
            Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                          .getBySbbh(sbxxBo.getSbbh(), conn);
            hdtzsBo.setVoHdtzs(hdtzs);

            String condition = " where HDTZSH = '" + hdtzs.getHdtzsh() + "'";
            ArrayList jmList = (ArrayList) DAOFactory.getInstance()
                               .getHdjmmxDAO().query(condition, conn);
            for (int i = 0; i < jmList.size(); i++) {
                Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                nrMap.put(hdjmmx.getZcbh(), hdjmmx);
            }
            hdtzsBo.setJmnrMap(nrMap);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
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
     * ��ѯ�˶�֪ͨ�� ����ֻ��һ���걨���
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzs(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            HdtzsBo hdtzsBo = (HdtzsBo) doGetHdtzs(vo);
            return hdtzsBo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ѯ�˶�֪ͨ�� ����ֻ��һ����α����
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyFwhm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        Hdtzs bo = (Hdtzs) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzsbyFwhm... " + bo.getFwhm());
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
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
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
        Debug.out("JmsbxxProcessor doUpdateHdtzs... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("���º˶�֪ͨ���������ݷ�α����");
            // ���º˶�֪ͨ���������ݷ�α����
            DAOFactory.getInstance().getHdtzsDAO().updatefwhm(bo, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * ����
     *
     * @param vo
     *            VOPackage
     */
    private void doCancel(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doCancel... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            // ����״̬
            sbzbDao.update(Constants.ZB_ZTBS_ZF, bo.getSbbh(), conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "�����걨ʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ѯ�˶�֪ͨ�� ����ֻ��һ���˶�֪ͨ�����
     *
     * @param vo
     *            VOPackage
     */
    private Object doQueryHdtzsbyHdtzshm(VOPackage vo) throws BaseException {
        ArrayList hdtzsList = new ArrayList();
        StringBuffer condition = new StringBuffer();
        HdtzsBo bo = (HdtzsBo) vo.getData();
        String hdtzsh_xg = bo.getHdtzsh_xg();
        UserData ud = vo.getUserData();
        Debug.out("JmsbxxProcessor doQueryHdtzsbyHdtzshm... " + hdtzsh_xg);
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("��ѯ�ú˶�֪ͨ�����ĺ˶�֪ͨ���Ƿ����");
            condition.append(" where HDTZSH='").append(hdtzsh_xg).append("'");
            // ��ѯ�÷�α����ĺ˶�֪ͨ���Ƿ����
            hdtzsList = DAOFactory.getInstance().getHdtzsDAO().query(
                    condition.toString(), conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
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
     * ���ݺ˶�֪ͨ�������º˶�֪ͨ�����
     *
     * @param vo
     *            VOPackage
     */
    private void doUpdateHdtzshmByHdtzshm(VOPackage vo) throws BaseException {
        HdtzsBo bo = (HdtzsBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("���º˶�֪ͨ��������ϸ��ĺ˶�֪ͨ�����");

            // ���º˶�֪ͨ���������ݷ�α����
            String hdtzsh = bo.getVoHdtzs().getHdtzsh(); // ԭ�˶�֪ͨ���
            String hdtzsh_xg = bo.getHdtzsh_xg(); // �޸ĺ�ĺ˶�֪ͨ���

            Debug
                    .out(
                    "JmsbxxProcessor doUpdateHdtzshmByHdtzshm...modify hdtzsh "
                    + hdtzsh_xg);

            DAOFactory.getInstance().getHdtzsDAO().updateHdtzsHm(hdtzsh,
                    hdtzsh_xg, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
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
        JmsbxxBo bo = (JmsbxxBo) vo.getData();
        UserData ud = vo.getUserData();
        Debug.out("SbxxProcessor doRollBack... " + bo.getSbbh());
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            // ��ȡ�걨����DAO
            SbzbDAO sbzbDao = DAOFactory.getInstance().getSbzbDAO();

            String state_cur = bo.getState();
            String state_rol = SbState.getCancelStateCode4Jmsb(state_cur, bo
                    .getVoSbzb().getBljmsbs());

            String state_cur_name = SbState.getStateName(state_cur);
            String state_rol_name = SbState.getStateName(state_rol);

            Debug.out("current state: " + state_cur_name);
            Debug.out("rollback state: " + state_rol_name);
            // ���ص�״̬��Ϊ�������״̬
            if (state_rol != null && !("".equals(state_rol))) {
                sbzbDao.update(state_rol, bo.getSbbh(), conn);
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
     * ��������Լ���������� 1�������걨���ʶ������������� 2�����¼����걨���е��������ء��������� 3����������걨��
     *
     * @param vo
     *            VOPackage
     */
    private void doSavesp(VOPackage vo) throws BaseException {
        JmsbxxBo bo = (JmsbxxBo) vo.getData();

        Connection conn = null;
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Hdtzs hdtzs = new Hdtzs();
        ArrayList sbjmList = new ArrayList();

        String jzbs = "000000"; // д�� ���ʱ�ʶ��ʼֵ

        try {
            UserData ud = vo.getUserData();
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            String spjgdm = bo.getSpjg();
            String spzt = bo.getSpzt();
            Timestamp sprq = bo.getSprq();
            Timestamp cjrq = bo.getVoSbzb().getCjrq();
            String sbbh = bo.getSbbh();

            // ˰��˰Ŀ����
            Szsm szsm = CommonUtil.getSZSMDM(bo.getVoTufwxx().getTdfwqszylx(),
                                             conn);

            HashMap map = new HashMap();
            // ����
            if (bo.isPerson()) {
                // ���˼��������
                String jsjdm_gr = bo.getVoZcqrxx().getJsjdm();
                map.put("jsjdm", jsjdm_gr);
            }
            // �Ǹ���
            if (!bo.isPerson()) {
                // �Ǹ��˼��������
                String jsjdm_fgr = bo.getVoFgrxx().getJsjdm();
                map.put("jsjdm", jsjdm_fgr);
            }

            map.put("szsmdm", szsm.szsmdm);

            map.put("cjrq", cjrq);

            // ��ö�Ӧ�걨���������
            sbjmList = CommonUtil.getJM(conn, map);
            Debug.out("��ö�Ӧ�걨��������ݳɹ���");
            // ������걨�����������Ѿ����ʣ��������޸ġ�ɾ��
            if (sbjmList != null && sbjmList.size() > 0) {

                Debug.out("��ö�Ӧ�걨���������sbjmList.size()=" + sbjmList.size());

                for (int index = 0; index < sbjmList.size(); index++) {
                    Jm jm = new Jm();
                    jm = (Jm) sbjmList.get(index);

                    Debug.out("��ö�Ӧ�걨���������jm.getJzbz()=" + jm.getJzbz());

                    if (!jm.getJzbz().equals(jzbs)) {
                        throw new ApplicationException("�ñʼ����걨���ݣ��ƻ��Ѽ��ʣ��������޸ģ�");
                    }
                }
            }

            Debug.out("�жϼ��ʳɹ���");

            Debug.out("����״̬spzt=" + spzt);

            // ����ͬ�⣬��Ҫ�����걨�����
            if (spzt != null && spzt.equals(Constants.ZB_ZTBS_JS_TY)) {
                Debug.out("����ͬ�⣬��Ҫ�����걨�����");
                // ��ȡ������Ŀ����
                String jmxmdm = "";
                //���Ӽ������ʴ���
                String jmxzdm = "";
                if (bo.getJmsbbList() != null || bo.getJmsbbList().size() > 0) {
                    for (int i = 0; i < bo.getJmsbbList().size(); i++) {
                        Jmsbb jmsbb = (Jmsbb) bo.getJmsbbList().get(i);
                        jmxmdm = jmsbb.getJmzcdm();
                        jmxzdm = jmsbb.getJmxzdm();
                    }
                }

                if (jmxmdm == null || "".equals(jmxmdm)) {
                    jmxmdm = Constants.JMXMDM;
                }
                
                if(jmxzdm == null || "".equals(jmxzdm)){
                    PreparedStatement ps = null;
                    ResultSet rs = null;

                    try {
                        ps = conn.prepareStatement("select jmxzdm from dmdb.sf_dm_qsjmlb where qsjmlbdm = '"+jmxmdm+"'");
                        rs = ps.executeQuery();
                        if (rs.next()) {
                        	jmxzdm = rs.getString(1);
                        }
                    } catch (Exception e) {
                    } finally {
                    	if(rs!=null){
                    		rs.close();
                    	}
                    	if(ps!=null){
                    		ps.close();
                    	}
                    }
                }
                
    

                // ��ȡ�˶�֪ͨ����������
                hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO()
                        .getBySbbh(bo.getSbbh(), conn);

                map.put("jsje", hdtzs.getJzqs());
                map.put("jmse", hdtzs.getJzqs());
                map.put("lrr", ud.getYhid());
                map.put("jmxmdm", jmxmdm); // ������Ŀ����
                map.put("jmxzdm", jmxzdm); // �������ʴ���
                map.put("skssjsrq", bo.getVoTufwxx().getHtqdsj());
                map.put("skssksrq", bo.getVoTufwxx().getHtqdsj());
                
                if (!CommonUtil.insertSBJM(map)) {
                    throw new ApplicationException("����ƻ����걨������ʱ�򱨴�");
                }
                Debug.out("��������걨�ɹ���");
            }

            // ������ͬ�⣬��Ҫɾ���걨�����
            // �Ѽ����������޸�
            if (spzt != null && spzt.equals(Constants.ZB_ZTBS_JS_BTY)) {
                Debug.out("������ͬ�⣬��Ҫɾ���걨�����");
                // ɾ���걨����������
                if (!CommonUtil.deleteSBJM(map)) {
                    throw new ApplicationException("�޷�ɾ���걨���������ݣ����ýӿڳ���");
                }
                Debug.out("���������걨�ɹ���");

            }

            // update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(spzt, sbbh, conn);

            // �����걨��Ÿ��¼����걨���������
            DAOFactory.getInstance().getJmsbbDAO().updateSpjg(spjgdm, sprq,
                    sbbh, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�����걨��JmsbxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}

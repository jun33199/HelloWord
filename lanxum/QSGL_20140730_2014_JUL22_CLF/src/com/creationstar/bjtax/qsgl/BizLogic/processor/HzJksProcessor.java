package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.HzJksBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title:��¼�������ɵĽɿ����processor </p>
 *
 * <p>Description: ��¼�������ɵĽɿ����processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class HzJksProcessor implements Processor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage�ǿ�ָ��!!!");
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = getJksInfo(vo);
            break;

        case ActionType.INSERT:
            createJks(vo);
            break;

        case ActionType.UPDATE:
            result = updateWszhz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;

    }

    /**
     * ����UserData�Զ������ɿ���������Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object getJksInfo(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Sbjkzb jkzb = new Sbjkzb();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            Yskm yskm = null; //Ԥ���Ŀ

            //�õ����ܵ�������Ա������˰����صĻ�����Ϣ
            SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(jsjdm);
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            if (swdjJbsj == null || swjgzzjg == null) {
                throw new ApplicationException("���ܵõ�����������Ա������˰����صĻ�����Ϣ��");
            }

            //Ϊ�ɿ��걨����ȫ����
            jkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //�����Ǵ���
            jkzb.setDjzclxdm(swdjJbsj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
            jkzb.setFsdm(Constants.WSZ_FSDM); //�Ǽ��걨��ʽ����
            jkzb.setGjbzhydm(swdjJbsj.getGjbzhydm()); //���ұ�׼��ҵ����

            jkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //������֯��������
            jkzb.setGkzzjgmc(swjgzzjg.skgk); //������֯��������
            jkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //�����ʶ

            jkzb.setJsjdm(jsjdm); //���������
            jkzb.setJydzlxdm(swdjJbsj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
            jkzb.setLrr(ud.getYhid()); //¼����
            jkzb.setLsgxdm(swdjJbsj.getLsgxdm()); //������ϵ����
            jkzb.setNd(DateUtil.getDate().substring(0, 4)); //���
            jkzb.setQxdm(CommonUtil.getQxdm(ud)); //���ش���

            jkzb.setSjly(Constants.JKS_SJLY_HZ); //������Դ�������ܷ�ʽ����
            jkzb.setSklxdm(Constants.JKS_SKLXDM_HZJK); //˰�����ʹ���
            jkzb.setSwjgzzjgdm(ud.getSsdwdm()); //˰�������֯��������
            jkzb.setSzdm(Constants.WSZ_QSSZDM); //˰�ִ���
            jkzb.setZsswjgzzjgdm(ud.getSsdwdm()); //���ջ��ش���
            jkzb.setYsjcdm(Constants.YSJCDM_DF); //Ԥ�㼶��
            //�õ�Ԥ���Ŀ����
            yskm = CommonUtil.getYskm(jkzb.getSzdm(), conn);
            jkzb.setYskmdm(yskm.yskmdm);

            jkzb.setSwjgzzjgmc(swdjJbsj.getSwjgzzjgmc()); //˰�������֯��������
            jkzb.setLsgxmc(swdjJbsj.getLsgxmc()); //������ϵ����
            jkzb.setDjzclxmc(swdjJbsj.getDjzclxmc()); //�Ǽ�ע����������
            jkzb.setGjbzhymc(swdjJbsj.getGjbzhymc()); //���ұ�׼��ҵ����
            jkzb.setNsrmc(swdjJbsj.getNsrmc()); //��˰������
            jkzb.setZsswjgzzjgmc(ud.ssdwmc); //����˰�������֯��������
            jkzb.setSwjgzzjgmc(ud.ssdwmc); //˰�������֯��������
            jkzb.setSklxmc(Constants.JKS_SKLXDM_HZJK_MC); //˰����������
            jkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
            jkzb.setSzmc(Constants.WSZ_QSSZMC); //˰������
            jkzb.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ����
            jkzb.setGkjhh(swjgzzjg.getGkjhh()); //���⽻����

            //�õ�������Ϣ
            ArrayList yhList = (ArrayList) CommonUtil.getYHZH(jsjdm);
            for (Iterator it = yhList.iterator(); it.hasNext(); ) {
                YHZH yhzh = (YHZH) it.next();
                if (Constants.JKS_JBZHBS.equalsIgnoreCase(yhzh.getJbzhbs())) {
                    jkzb.setYhdm(yhzh.getYhdm());
                    jkzb.setYhmc(yhzh.getKhyhmc());
                    jkzb.setZh(yhzh.getZh());
                }
            }

            bo.setSbjkzb(jkzb);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�HzJksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void createJks(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //�õ���ǰʱ��
            Timestamp now = CommonUtil.getDBtime(conn);

            Sbjkzb sbjkzb = bo.getSbjkzb();
            sbjkzb.setLrrq(now);
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 7)); //��˰�޽�����
            sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //�걨����
            sbjkzb.setZyrq(now); //��������

            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("��¼�������ɵĽɿ��飺�Ѿ���sbjkzb�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("�����걨�ɿ��������");
            }

            Sbjkmx sbjkmx = new Sbjkmx();
            //�����걨������ϸ�������
            sbjkmx.setCjrq(sbjkzb.getCjrq());
            sbjkmx.setLrrq(sbjkzb.getLrrq());
            sbjkmx.setJkpzh(sbjkzb.getJkpzh());
            sbjkmx.setJsjdm(sbjkzb.getJsjdm());
            sbjkmx.setNd(sbjkzb.getNd());
            sbjkmx.setQxdm(sbjkzb.getQxdm());
            sbjkmx.setSkssksrq(sbjkzb.getSkssksrq());
            sbjkmx.setSkssjsrq(sbjkzb.getSkssjsrq());
            sbjkmx.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());
            sbjkmx.setSwjgzzjgmc(sbjkzb.getSwjgzzjgmc());
            sbjkmx.setYsjcdm(sbjkzb.getYsjcdm());
            sbjkmx.setYsjcmc(sbjkzb.getYsjcmc());
            sbjkmx.setYskmdm(sbjkzb.getYskmdm());
            sbjkmx.setYskmmc(sbjkzb.getYskmmc());
            sbjkmx.setSbbh(sbjkzb.getSbbh()); //�걨���

            int size = bo.getMxxmdm().length;
            for (int i = 0; i < size; i++) {
                if (bo.getMxsjse()[i] != null && !bo.getMxsjse()[i].equals("")) {
                    sbjkmx.setJsje(DataConvert.String2BigDecimal(bo.getMxjsje()[
                            i]));
                    sbjkmx.setKssl(DataConvert.String2BigDecimal(bo.getMxkssl()[
                            i]));
                    sbjkmx.setSjse(DataConvert.String2BigDecimal(bo.getMxsjse()[
                            i]));
                    sbjkmx.setRkje(sbjkmx.getSjse());

                    Szsm szsm = CommonUtil.getSZSMDM(bo.getMxxmdm()[i], conn);
                    sbjkmx.setSzsmdm(szsm.getSzsmdm()); //˰��˰Ŀ����
                    sbjkmx.setSzsmmc(szsm.getSzsmmc()); //˰��˰Ŀ����

                    try {
                        DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkmx,
                                conn);
                        Debug.out("��¼�������ɵĽɿ��飺�Ѿ���sbjkmx�����ݲ��뵽���ݿ���....");
                    } catch (Exception ex) {
                        Debug.out(ex);
                        throw new ApplicationException("�����걨�ɿ���ϸ����");
                    }
                }
            }

            Qswszhz qswszhz = new Qswszhz();
            //������˰֤���ܱ������
            qswszhz.setCjr(sbjkzb.getLrr());
            qswszhz.setCjrq(sbjkzb.getCjrq());
            qswszhz.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
            qswszhz.setHzfs(bo.getHzfsdm());
            qswszhz.setHzfsmc(bo.getHzfsmc());
            qswszhz.setHzksrq(sbjkzb.getSkssksrq());
            qswszhz.setHzjsrq(sbjkzb.getSkssjsrq());
            qswszhz.setHzrq(sbjkzb.getCjrq());
            qswszhz.setJkpzh(sbjkzb.getJkpzh());
            qswszhz.setJsjdm(sbjkzb.getJsjdm());
            qswszhz.setLrr(sbjkzb.getLrr());
            qswszhz.setLrrq(sbjkzb.getLrrq());
            qswszhz.setNd(sbjkzb.getNd());
            qswszhz.setSjse(sbjkzb.getSjje());
            qswszhz.setSwjgzzjgdm(sbjkzb.getSwjgzzjgdm());

            qswszhz.setZsddm(ud.xtsbm1);
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            qswszhz.setZsdmc(zh.getZhmc());

            if (bo.getFp().equals("0")) { //���û�з�Ʊ�����
                //ȡ���걨���ܵ���
                String sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);
                qswszhz.setSbhzdh(sbhzdh);
            } else {
                qswszhz.setSbhzdh(Constants.WSZ_SBHZDH_DEFAULT);
            }

            try {
                DAOFactory.getInstance().getQswszhzDAO().insert(qswszhz, conn);
                Debug.out("��¼�������ɵĽɿ��飺�Ѿ���qswszhz�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("������˰֤���ܱ����");
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�HzJksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    private Object updateWszhz(VOPackage vo) throws BaseException {
        HzJksBo bo = (HzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String jkpzh[] = bo.getJkpzh();
            String sbhzdh = "";
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            int size = jkpzh.length;

            for (int i = 0; i < size; i++) {
                sbhzdh = DAOFactory.getInstance().getQswszhzDAO().get(jsjdm,
                        jkpzh[i], conn);
                if (!Constants.WSZ_SBHZDH_DEFAULT.equals(sbhzdh)) {
                    throw new ApplicationException(jkpzh[i] +
                            "�Žɿ���û�з�Ʊ����������Ѿ���ɷ�Ʊ!");
                }

            }

            //�����걨���ܵ���
            sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);

            //����jkpzh��Ӧ����˰֤���ܱ�����
            for (int i = 0; i < size; i++) {
                try {
                    DAOFactory.getInstance().getQswszhzDAO().update(jsjdm,
                            sbhzdh, jkpzh[i],
                            conn);
                    Debug.out("��¼�������ɵĽɿ��飺�Ѿ������ɵ�sbhzdh : " + sbhzdh +
                              "���µ����ݿ�qswszhz����....");
                } catch (Exception ex) {
                    Debug.out(ex);
                    throw new ApplicationException("������˰֤���ܱ����");
                }
            }

            return sbhzdh;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�HzJksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}

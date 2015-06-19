package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbFgrBo;
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
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

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
 * @author ����
 * @version 1.0
 */
public class SbFgrProcessor implements Processor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SbFgrProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doInsert(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ����һ���Ǹ����걨��Ϣ
     * 1.�����걨�������ݣ����Ȼ�ȡ�µ��걨��ţ�
     * 2.���ɷǸ�����Ϣ
     * 3.�������ɵ��걨���
     * @param vo VOPackage
     * @return sbbh
     */
    private String doInsert(VOPackage vo) throws BaseException {
        SbFgrBo bo = null;
        String sbbh = null;
        int oklevel = 0;
        Connection conn = null;

        try {
            bo = (SbFgrBo) vo.getData();
            Debug.out("SbFgrProcessor bo nsrmc: " + bo.nsjsjdm);
            conn = QSDBUtil.getConnection();

            UserData ud = vo.getUserData();

            //��ȡ�걨���
            sbbh = CommonUtil.getSBBH(ud.getXtsbm1(), conn);
            Debug.out("get sbbh: " + sbbh);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            //�����걨��������
            Sbzb sbzb = new Sbzb();

            if (bo.isBl() == true) {
                sbzb.blbs = Constants.ZB_BLBS_BL;
                sbzb.sbrq = bo.getSbrq();
            } else {
                sbzb.blbs = Constants.ZB_BLBS_FBL;
                sbzb.sbrq = now;
            }

            /**
             * ¼���˲�Ǩ��Ϣ��Ϊ���ϰ������������
             * �������˰��ʶ��
             * 0--�����ϰ�������
             * 1--���ϰ���������δ¼��
             */
            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BFHBLTJ;
            sbzb.bz = bo.getBz();
            sbzb.cjr = ud.getYhmc();
            sbzb.cjrq = now;
            sbzb.fwtdbmdm = bo.getFcjslh();
            sbzb.jsfsdm = bo.getJkfsdm();
            sbzb.jsfsmc = bo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();

            /**  ��bo�е�����bo��Ӧ��������Ӱ�����ԣ����ݴ�Form�е�����ֵ���� */
            sbzb.nsrlxdm = bo.getNsrlxdm();
            sbzb.sbbh = sbbh;
            //���ӽ�ίҵ���� modify by fujx,20081125
            sbzb.setJwywbh(bo.getJwywbh());
            //���Ӻ�ͬ��� modify by fujx,20081125
            sbzb.setHtbh(bo.getHtbh());
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            sbzb.yhbs = Constants.ZB_YHBS_FGR; //�Ǹ���
            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;

            //insert sbzb
            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            oklevel = 1;

            //����Ǹ�����Ϣ
            Fgrxx fgrxx = new Fgrxx();
            fgrxx.cjr = ud.getYhmc();
            fgrxx.cjrq = now;
            fgrxx.fwjhbs = Constants.FFWJHBS;
            fgrxx.lrr = ud.getYhmc();
            fgrxx.lrrq = now;
            fgrxx.lxdh = bo.lxdh;
            fgrxx.nsrmc = bo.nsrmc;
            Debug.out("SbGrProcessor bo nsrmc22: " + fgrxx.nsrmc);
            fgrxx.jsjdm = bo.getNsjsjdm();
            Debug.out("getjsjdm.... : " + fgrxx.jsjdm);
            fgrxx.sbbh = sbbh;
            fgrxx.yhzh = bo.yhzh;
            fgrxx.khyhdm = bo.khyhdm;
            fgrxx.khyhmc = bo.khyhmc;
            fgrxx.lxrxm = bo.lxrxm;
            fgrxx.nsrlxdm = bo.nsrlxdm;
            fgrxx.nsrlxmc = bo.nsrlxmc;

            //insert grxx
            DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
            oklevel = 2;

            //���������������
            if (bo.getHdtzszh() != null && !bo.getHdtzszh().equals("")) {
                Spjgxx spjgxx = new Spjgxx();
                spjgxx.cjr = ud.getYhmc();
                spjgxx.cjrq = now;
                spjgxx.hdtzszh = bo.getHdtzszh();
                if ((bo.getJmlydm() == null) || (bo.getJmlydm().equals(""))) {
                    spjgxx.jmlydm = "00";
                } else {
                    spjgxx.jmlydm = bo.jmlydm;
                }

                spjgxx.jmsje = bo.jmsje;
                spjgxx.lrr = ud.getYhmc();
                spjgxx.lrrq = now;
                spjgxx.sbbh = sbbh;

                Debug.out("jmje: " + bo.getJmsje());
                Debug.out("hdtzszh: " + bo.getHdtzszh());

                DAOFactory.getInstance().getSpjgxxDAO().insert(spjgxx, conn);
            }

        } catch (Exception ex) {
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("�����걨�������!");
                case 1:
                    throw new ApplicationException("����Ǹ�����Ϣ����!");
                case 2:
                    if (ex.getMessage().indexOf("ORA-00001") != -1) {
                        throw new ApplicationException("������������Ѿ�����!");
                    } else {
                        throw new ApplicationException("����������������Ϣ����!");
                    }
                }
            } catch (ApplicationException e) {
                // ����ʧ����Ϣ:����̨ �� ��־
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbFgrProcessor����������",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
                throw ExceptionUtil.getBaseException(e);
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return sbbh;
    }


}

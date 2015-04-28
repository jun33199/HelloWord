package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryFtxxBo;
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
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 *
 * <p>Description: �������ػ�����Ϣ��Processor�� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author ������
 * @version 1.0
 */
public class FtxxProcessor extends CommonProcessor {
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
            doAdd(vo);
            break;
        case ActionType.MODIFY:
            doUpdate(vo);
            break;
        case ActionType.QUERY_USAGE:
            return doQueryUsage(vo);
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
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            //vo.getUserData();
            Tufwxx tufwxx = (Tufwxx) vo.getData();
            UserData ud = vo.getUserData();
            tufwxx.tdfwid = CommonUtil.getTDFWID(conn);
            //����ҵ���ж�ȷ���ɽ��۸�
            double cjjgrmb = CommonUtil.getCjjg(tufwxx);
            //�����걨����ļ�˰���
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(tufwxx.sbbh);

            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            sbzb.setJsje(new BigDecimal(cjjgrmb));
            System.out.println("FtxxProcessor ���ݳɽ��۸�cjjgrmb=" + cjjgrmb);
            sbzb.setSetz(tufwxx.getSetz());
            System.out.println("FtxxProcessor ˰�����Setz=" + tufwxx.getSetz());
            //----add by zhangyj at 20081028-------------
            if (sbzb.getSetz().equals("5")) {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.
                        ZCWH_SCGMPTZFSL, conn)));
            }
            //---------------------------------------
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);

            tufwxx.nd = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
            tufwxx.setLrr(ud.yhmc);
            tufwxx.setCjr(ud.yhmc);
            tufwxx.setLrrq(new Timestamp(System.currentTimeMillis()));
            tufwxx.setCjrq(new Timestamp(System.currentTimeMillis()));

            //insert tufwxx
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            //insert sbzbftgl
            Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
            sbtdfwgl.cjr = ud.yhmc;
            sbtdfwgl.cjrq = tufwxx.cjrq;
            sbtdfwgl.lrr = ud.yhmc;
            sbtdfwgl.lrrq = tufwxx.cjrq;
            sbtdfwgl.sbbh = tufwxx.sbbh;
            sbtdfwgl.tdfwid = tufwxx.tdfwid;
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FtxxProcessor����������",
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
        Debug.out("into ftxxprocessor update....");
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the tufwxx and insert
            Tufwxx tufwxx = (Tufwxx) vo.getData();

            Debug.out("before get connection...");
            conn = QSDBUtil.getConnection();
            Debug.out("after get connection...");

            TufwxxDAO dao = DAOFactory.getInstance().getTufwxxDAO();
            Debug.out("after get dao...");

            Tufwxx existData = (Tufwxx) dao.get(tufwxx, conn);
            Debug.out("after get tufwxx...");

            //���ø��µ��ֶ�
            existData.sbbh = tufwxx.sbbh;
            existData.tdfwid = tufwxx.tdfwid;
            existData.fdcxmmc = tufwxx.fdcxmmc;
            existData.htqdsj = tufwxx.htqdsj;
            existData.fldm = tufwxx.fldm;
            existData.tdfwqszylx = tufwxx.tdfwqszylx;
            existData.fwlxdm = tufwxx.fwlxdm;
            existData.tdfwzldz = tufwxx.tdfwzldz;
            existData.tdfwqszymj = tufwxx.tdfwqszymj;
            existData.cjjgrmb = tufwxx.cjjgrmb;
            existData.pgjgrmb = tufwxx.pgjgrmb;
            existData.cjjgwb = tufwxx.cjjgwb;
            existData.bzdm = tufwxx.bzdm;
            existData.hldm = tufwxx.hldm;
            existData.zhjgrmb = tufwxx.zhjgrmb;
            existData.bz = tufwxx.bz;
            existData.bzmc = tufwxx.bzmc;
            existData.flmc = tufwxx.flmc;
            existData.fwjzmj = tufwxx.fwjzmj;
            existData.fwlxmc = tufwxx.fwlxmc;
            existData.tdfwqszymc = tufwxx.tdfwqszymc;
            existData.rjl = tufwxx.rjl;
            existData.tdjc = tufwxx.tdjc;
            existData.sfesf = tufwxx.sfesf;
            //����
            Debug.out("before update ftxx...");
            dao.update(existData, conn);
            Debug.out("after update ftxx...");

            //����ҵ���ж�ȷ���ɽ��۸�
            Debug.out("before get cjjg.");
            double cjjgrmb = CommonUtil.getCjjg(existData);
            Debug.out("after get cjjg.");

            //�����걨����ļ�˰���
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(existData.sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            sbzb.setJsje(new BigDecimal(cjjgrmb));
            sbzb.setSetz(tufwxx.getSetz());
            //----add by zhangyj at 20081029-------------
            if (sbzb.getSetz().equals("5")) {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.
                        ZCWH_SCGMPTZFSL, conn)));
            } else {
                sbzb.setSl(new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL,
                        conn)));
            }
            //---------------------------------------
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            Debug.out("after update sbzb.");

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FtxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private QueryFtxxBo doQueryUsage(VOPackage vo) throws BaseException {
        Connection conn = null;
        QueryFtxxBo bo = null;
        try {
            //construct the jsblcq and insert
            Tufwxx tufwxx = (Tufwxx) vo.getData();

            conn = QSDBUtil.getConnection();
            TufwxxDAO dao = DAOFactory.getInstance().getTufwxxDAO();
            System.out.println("--:" + tufwxx.tdfwid);

            Tufwxx existData = (Tufwxx) dao.get(tufwxx, conn);
            System.out.println("--2:" + existData);
            if (existData == null) {

                return null;
            } else {
                //����QueryFtxxBo
                bo = new QueryFtxxBo();
                bo.setTdfwid(existData.getTdfwid());
                bo.setFdcxmmx(existData.getFdcxmmc());
                bo.setHtqdsj(existData.getHtqdsj());

                bo.setFlmc(existData.getFlmc());
                bo.setFldm(existData.getFldm());

                bo.setTdfwqszylxmc(existData.getTdfwqszymc());
                bo.setTdfwqszylx(existData.getTdfwqszylx());

                bo.setFwlxmc(existData.getFwlxmc());
                bo.setFwlxdm(existData.getFwlxdm());

                bo.setTdfwzldz(existData.getTdfwzldz());
                bo.setTdfwqszymj(existData.getTdfwqszymj());
                bo.setFwjzmj(existData.getFwjzmj());
                bo.setCjjgrmb(existData.getCjjgrmb());
                bo.setPgjgrmb(existData.getPgjgrmb());
                bo.setCjjgwb(existData.getCjjgwb());

                bo.setBzmc(existData.getBzmc());
                bo.setBzdm(existData.getBzdm());

                bo.setHl(existData.getHldm());
                bo.setZhjgrmb(existData.getZhjgrmb());
                bo.setCjr(existData.getCjr());
                bo.setCjrq(existData.getCjrq());

                bo.setRjl(existData.getRjl());
                bo.setTdjc(existData.getTdjc());

                //��ȡʹ�����ط��ݵ��걨��Ϣ
                ArrayList list = (ArrayList) DAOFactory.getInstance().
                                 getSbtdfwglDAO().queryByTdfwid(tufwxx.tdfwid,
                        conn);
                if ((list != null) && (list.size() > 0)) {
                    bo.setListSbxx(list);
                }
            }

            return bo;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FtxxProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ȡ���ط���ʹ����Ϣʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}

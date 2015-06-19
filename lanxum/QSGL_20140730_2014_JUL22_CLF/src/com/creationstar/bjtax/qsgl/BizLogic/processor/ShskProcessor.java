package com.creationstar.bjtax.qsgl.BizLogic.processor;

import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import java.sql.Connection;
import com.creationstar.bjtax.qsgl.BizLogic.vo.*;
import com.ttsoft.framework.processor.Processor;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.ShskBo;
import java.math.BigDecimal;
import java.util.HashMap;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;

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
 * @author not attributable
 * @version 1.0
 */
public class ShskProcessor implements Processor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is ShskProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.GET:
            result = doGet(vo);
            break;
        case ActionType.CHECK:
            result = doCheck(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ʵ����ShskBo��������Ϊ��������ѯ������¼
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        /**
         * ͨ��ȡ������ShskBo
         * �����ѯ����
         *
         * 1��ʹ��ShskBo�����걨����Vo��DAO���ؽ���걨����Vo
         *
         * 2��������ˡ��Ǹ�����ϢVo��DAO.get���ؽ�����ˡ��Ǹ�����ϢVo��ArrayList
         *
         * 3�����췿�����ػ�����Ϣconditions,DAO.query���ؽ���������ػ�����ϢVo��ArrayList
         *
         * 4�������Ǩ��Ϣconditions,DAO.query���ؽ����Ǩ��ϢVo��ArrayList
         *
         * 5�����칫��ס����Ϣconditions,DAO.query���ؽ������ס����ϢVo��ArrayList
         */

        ShskBo bo = new ShskBo();

        ArrayList hdList = new ArrayList();

        Connection conn = null;
        try {
            bo = (ShskBo) vo.getData();

            conn = QSDBUtil.getConnection();

            //�õ�����������Ϣ
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(bo.getSbbh());
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            List l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                    sbzbVo.getSbbh(), conn);
            bo.setBz(sbzbVo.getBz());
            bo.setJsfsdm(sbzbVo.getJsfsdm());
            bo.setSl(sbzbVo.getSl());
            bo.setSbbh(sbzbVo.getSbbh());
            bo.setNsrList(l);
            if (sbzbVo.ztbs.equals(Constants.ZB_ZTBS_YFH)) {
                bo.setChecked(true);
            }

            //�õ����������Ϣ(�����Ѿ�����)��������Ǩ��˰��
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            bo.setCqjmje(hdbo.getCqjmje());
            bo.setPtzzjsje(hdbo.getPtzzjmje());
            //���Ӿ������÷���˰���
            bo.setJjsyfjsje(hdbo.getJjsyfjmje());

            bo.setJsje(hdbo.getJzse());
            bo.setJsyj(hdbo.getJsyj());
            bo.setYnse(hdbo.getYnse());

            //ȷ������������Ϣ
            bo.setCjjgrmb(hdbo.getCjjgrmb());

            //�õ��˶�֪ͨ����Ϣ
            ArrayList list = new ArrayList();
            HdtzsBo hdtzsbo = getHdtzsBo(conn, bo.getSbbh());
            if (hdtzsbo != null) {
                list.add(hdtzsbo);
            }
            bo.setHdList(list);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�ShskProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    /**
     * ����
     * @param vo VOPackage
     */
    private Object doCheck(VOPackage vo) throws BaseException {
        ShskBo bo = (ShskBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            //�����걨����ֵ���󣬸���Ӧ��˰���ֶ�
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(bo.getSbbh());
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            sbzb.setYnse(bo.getYnse());
            sbzb.setZtbs(Constants.ZB_ZTBS_YFH);
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            return null;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�ShskProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ȡ�˶�֪ͨ������
     */
    private HdtzsBo getHdtzsBo(Connection conn, String sbbh) throws Exception {
        HdtzsBo hdtzsBo = new HdtzsBo();

        HashMap nrMap = new HashMap();

        //��ȡ�˶�֪ͨ����������
        Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                      getBySbbh(sbbh, conn);
        if (hdtzs == null) {
            return null;
        } else {
            hdtzsBo.setVoHdtzs(hdtzs);

            //��ȡ�˶�������ϸ
            String condition = " where HDTZSH = '" + hdtzs.getHdtzsh() +
                               "'";
            ArrayList jmList = (ArrayList) DAOFactory.getInstance().
                               getHdjmmxDAO().query(condition, conn);
            for (int i = 0; i < jmList.size(); i++) {
                Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                nrMap.put(hdjmmx.getZcbh(), hdjmmx);
            }
            hdtzsBo.setJmnrMap(nrMap);

            //��ȡ�����������˰��
            Spjgxx spjgxx = (Spjgxx) DAOFactory.getInstance().getSpjgxxDAO().
                            getBySbbh(sbbh, conn);
            if (spjgxx != null) {
                hdtzsBo.setSpjmse(spjgxx.getJmsje());
            }

            return hdtzsBo;
        }
    }


}

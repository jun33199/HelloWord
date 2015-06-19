/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zcwh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
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
 * @author �Բ�
 * @version 1.0
 */
public class ZcwhProcessor extends CommonProcessor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is ZcwhProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQuery(vo);

            break;

        case ActionType.INSERT:
            doAdd(vo);

            break;

        case ActionType.DELETE:
            result = doDelete(vo);

            break;

            // ���ݴ�����ϼ����ش��룬�õ������¼����ص���Ϣ����
        case ActionType.MODIFY:
            result = this.doUpdate(vo);

            break;

        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * һ���ѯ����
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        try {
            //vo.getUserData();
            Zcwh zw = (Zcwh) vo.getData();
            String conditions = " where (1=1) ";
            if (zw != null && zw.zbdm != null && !zw.zbdm.equals("")) {
                conditions = conditions + " and zbdm='" + zw.zbdm + "'";
            }

            if (zw != null && zw.zbmc != null && !zw.zbmc.equals("")) {
                conditions = conditions + " and zbmc='" + zw.zbmc + "'";
            }
//            String conditions = CommonUtil.getSqlQueryConditions(zw);
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getZcwhDAO().query(conditions,
                    conn);

            /** @todo ʹ�� DataUtil ת��ֵ�����е� */
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�ZcwhProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return resultList;
    }

    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            //vo.getUserData();
            Zcwh zcwh = (Zcwh) vo.getData();
            conn = QSDBUtil.getConnection();
            DAOFactory.getInstance().getZcwhDAO().insert(zcwh, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�ZcwhProcessor����������",
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
     * @return Object
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        return new Object();
    }

    /**
     *
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doUpdate(VOPackage vo) throws BaseException {
        return new Object();
    }

}

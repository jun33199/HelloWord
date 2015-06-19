package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.model.ActionType;
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
 * <p>Description:��Ǩ�����Processor�� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾ </p>
 *
 * @author ������
 * @version 1.0
 */
public class SwjgzzjgProcessor extends CommonProcessor {
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
        case ActionType.GET:
            return doGet(vo);

        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

    }


    /**
     * 1.���ݲ�ǨЭ��Ż�ȡ��Ǩ��Ϣ
     * @param vo VOPackage
     * @throws BaseException
     */
    private Swjgzzjg doGet(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            //construct the jsblcq and insert
            UserData ud = (UserData) vo.getUserData();

            conn = QSDBUtil.getConnection();
            Swjgzzjg swjgzzjg = getSwjgzzjg(ud, conn);
            return swjgzzjg;

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SwjgzzjgProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ����˰�������֯���������ѯ�������
     * @param swjgzzjgdm ˰�������֯��������
     * @return swjgzzjg ˰�������֯������Ϣ
     * @throws java.lang.Exception �����쳣
     */
    public static Swjgzzjg getSwjgzzjg(UserData ud, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(ud.ssdwdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "��ѯ����ʧ��!");
        }
        return swjgzzjg;
    }
}

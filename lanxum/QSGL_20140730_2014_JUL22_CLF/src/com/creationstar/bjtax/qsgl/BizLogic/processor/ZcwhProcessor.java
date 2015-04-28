/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 *
 * <p>Description: 政策维护的Processor类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 赵博
 * @version 1.0
 */
public class ZcwhProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
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

            // 根据传入的上级机关代码，得到所属下级机关的信息集合
        case ActionType.MODIFY:
            result = this.doUpdate(vo);

            break;

        default:
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 一般查询方法
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

            /** @todo 使用 DataUtil 转换值对象中的 */
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－ZcwhProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－ZcwhProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

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

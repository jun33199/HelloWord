package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PlcxBo;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
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
public class PlcxProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQueryAll(vo);
            break;
        case ActionType.GET:
            result = doQueryDetail(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    public Object doQueryAll(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集

        PlcxBo bo = (PlcxBo) vo.getData();
        Connection conn = null;
        try {
            //增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "QSDB", "QS_JL_DRPCINFO");
            Debug.out("datafilter: " + datafilter);
            String conditions = " and " + datafilter;
            // 调用DAO查询数据库
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getPlcxDAO().get(bo,
                    conditions, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PlcxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    public Object doQueryDetail(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO查询返回的结果集

        PlcxMxBo bo = (PlcxMxBo) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getPlcxDAO().getDetail(bo,
                    conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－PlcxProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }
}

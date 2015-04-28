package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PzlrBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 配置录入processor
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
public class PzlrProcessor implements Processor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is PzlrProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.GET:

            result = doGetFwxx(vo);

            break;

        case ActionType.QUERY:

            result = doQuery(vo);

            break;

        case ActionType.INSERT:

            result = doSave(vo);

            break;
        case ActionType.DELETE:

            doDelete(vo);

            break;
//        case ActionType.QUERY_DRZB:
//
//         result=getFdcxmmc(vo);
//
//        break;



        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;

    }

    /**
     * 根据纳税人计算机代码查询纳税人基本信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {

        Connection conn = null;
        PzlrBo bo = (PzlrBo) vo.getData();
        try {
            conn = QSDBUtil.getConnection();
            bo = (PzlrBo) DAOFactory.getInstance().getPzlrDAO().
                 getNsrxx(bo, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "配置录入－PzlrProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    /**
     * 根据房地产项目名称查询
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGetFwxx(VOPackage vo) throws BaseException {
        List list = new ArrayList();
        Connection conn = null;
        PzlrBo bo = (PzlrBo) vo.getData();
        try {
            conn = QSDBUtil.getConnection();
            list = (List) DAOFactory.getInstance().getPzlrDAO().
                   getFwxx(bo, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "配置录入－PzlrProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return list;
    }

    /**
     *保存房屋信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException {
        Connection conn = null;
        PzlrBo bo = (PzlrBo) vo.getData();
        String result = "";
        try {
            conn = QSDBUtil.getConnection();

            result = DAOFactory.getInstance().getPzlrDAO().
                     saveFwxx(bo, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            result = "false";
            ErrorLog.makeLog(vo.getUserData(), "配置录入－PzlrProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return result;

    }

    /**
     * 删除项目信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private void doDelete(VOPackage vo) throws BaseException {
        Connection conn = null;
        PzlrBo bo = (PzlrBo) vo.getData();
        try {
            conn = QSDBUtil.getConnection();

            DAOFactory.getInstance().getPzlrDAO().
                    delFwxx(bo, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);

            ErrorLog.makeLog(vo.getUserData(), "配置录入－PzlrProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }
//    /**
//     * 根据id获取房地产项目名称
//     * @param vo VOPackage
//     * @return Object
//     * @throws BaseException
//     */
//    private Object getFdcxmmc(VOPackage vo) throws BaseException{
//        Connection conn = null;
//        PzlrBo bo = (PzlrBo) vo.getData();
//         String xmmc="";
//        try{
//            conn = QSDBUtil.getConnection();
//
//             xmmc = DAOFactory.getInstance().getPzlrDAO().
//                            getXmmc(bo, conn);
//        }catch(Exception ex){
//                // 处理失败信息:控制台 ＋ 日志
//                    Debug.printException(ex);
//
//                    ErrorLog.makeLog(vo.getUserData(), "配置录入－PzlrProcessor，操作出错",
//                                         new StackMsg2StringUtil(ex,Constants.STACK_MSG_CAP).getStackMsg(), "失败");
//
//                    throw ExceptionUtil.getBaseException(ex);
//
//                }finally {
//                        QSDBUtil.freeConnection(conn);
//                    }
//                    return xmmc;
//
//    }

}

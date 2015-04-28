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
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 *
 * <p>Description:拆迁情况的Processor类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 卫军丽
 * @version 1.0
 */
public class SwjgzzjgProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
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
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }

    }


    /**
     * 1.根据拆迁协议号获取拆迁信息
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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－SwjgzzjgProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 依照税务机关组织机构代码查询国库代码
     * @param swjgzzjgdm 税务机关组织机构代码
     * @return swjgzzjg 税务机关组织机构信息
     * @throws java.lang.Exception 操作异常
     */
    public static Swjgzzjg getSwjgzzjg(UserData ud, Connection con) throws
            Exception {
        Swjgzzjg swjgzzjg = new Swjgzzjg();
        swjgzzjg.setSwjgzzjgdm(ud.ssdwdm);

        try {
            swjgzzjg = (Swjgzzjg) DAOFactory.getInstance().getSwjgzzjgDAO().get(
                    swjgzzjg, con);
        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex, "查询国库失败!");
        }
        return swjgzzjg;
    }
}

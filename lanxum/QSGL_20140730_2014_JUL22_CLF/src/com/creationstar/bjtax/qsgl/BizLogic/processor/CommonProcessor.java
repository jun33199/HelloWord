/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import javax.naming.InitialContext;
import javax.transaction.UserTransaction;

import com.ttsoft.common.util.ResourceLocator;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 公用方法的Processor类</p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class CommonProcessor implements Processor {
    /**
     * 对不同的调用通过操作类型进行分发。
     *
     * @param vo VOPackage
     * @return Object 业务对象
     * @throws BaseException  可能抛出的异常
     */
    public Object process(VOPackage vo) throws BaseException {
        int optType = vo.getAction(); /* 操作类型 */
        Object rtnObject = null; /* 返回对象 */

        /* 根据操作类型分发操作
         */
        switch (optType) {
        //
        case 0:

//            rtnObject = this.getXJZHList(vo, false, true);

            break;

            //
        case 1:

            //          rtnObject = this.getXJZHList(vo, false, false);

            break;

        default:
            break;
        }

        return rtnObject;
    }

    /**
     * <p>得到用户事务
     * <p>流程：
     * <code>
     * <br>初始化上下文
     *  <br>InitialContext ctx = ResourceLocator.getInitialContext();
     *  <br>得到用户事务
     *  <br>UserTransaction userTransaction = UserTransaction)ctx.lookup
     * ("javax.transaction.UserTransaction");
     *  <br>return userTransaction;
     * </code>
     *
     * @return 用户事务
     * @throws Exception 查找异常
     */
    protected UserTransaction getUserTransaction() throws BaseException {
        try {
            //初始化上下文
            InitialContext ctx = ResourceLocator.getInitialContext();
            //得到用户事务
            UserTransaction userTransaction = (UserTransaction) ctx.lookup(
                    "javax.transaction.UserTransaction");
            return userTransaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex,
                    "ZcwhProcessor 中的 getUserTransction");
        }
    }
}

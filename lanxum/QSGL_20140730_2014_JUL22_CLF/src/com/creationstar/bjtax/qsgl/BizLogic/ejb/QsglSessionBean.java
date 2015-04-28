/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: QsglEJB(契税业务SessionBean) 的bean类 </p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class QsglSessionBean implements SessionBean {
    SessionContext sessionContext;

    public void ejbCreate() throws CreateException {
        /**@todo Complete this method*/
    }

    public void ejbRemove() {
        /**@todo Complete this method*/
    }

    public void ejbActivate() {
        /**@todo Complete this method*/
    }

    public void ejbPassivate() {
        /**@todo Complete this method*/
    }

    public void setSessionContext(SessionContext sessionContext) {
        this.sessionContext = sessionContext;
    }

    /**
     * 调用processor
     *
     * @param vo    the VOPackage
     * @return Object
     * @throws BaseException    抛出的异常
     */
    public Object process(VOPackage vo) throws BaseException {
        try {
            Debug.out("into QsglSessionEJB....");
            Debug.out("EJB reflection Processor is :" + vo.getProcessor());
            return ((Processor) Class.forName(vo.getProcessor()).newInstance()).
                    process(vo);
        } catch (Exception e) {
            try {
                //判断是否在事务中
                if (this.sessionContext != null) {
                    this.sessionContext.setRollbackOnly();
                }
            } catch (java.lang.IllegalStateException ex) {
                //不在事务中
            } catch (Exception ex) {
                //在事务中
                throw new EJBException(ex);
            }

            //throw ExceptionUtil.getBaseException(userData, e);
            Debug.out("现在在EJB中: " + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
    }
}

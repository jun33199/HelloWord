/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: InitialStartupEJB(契税模块初始化的SessionBean) 的bean类 </p>
 * @author 契税开发组－－赵博
 * @version 1.0
 */
public class InitialStartupBean implements SessionBean {
    SessionContext sessionContext;

    public void ejbCreate() throws CreateException {
        try {
            init_aux();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("初始化 InitialStartup EJB 出错！");
        }
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

    public static void init_aux() {
        try {
            System.out.println(
                    "\n============ Initial Startup Running OK ! ==============\n");
        } catch (Exception e) {
            System.out.println(
                    "There are some errors occur in the StartupServlet. \n\n");
            e.printStackTrace(System.out);
        }
    }
}

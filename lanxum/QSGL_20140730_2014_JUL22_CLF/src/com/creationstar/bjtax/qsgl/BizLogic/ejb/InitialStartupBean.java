/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: InitialStartupEJB(��˰ģ���ʼ����SessionBean) ��bean�� </p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class InitialStartupBean implements SessionBean {
    SessionContext sessionContext;

    public void ejbCreate() throws CreateException {
        try {
            init_aux();
        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("��ʼ�� InitialStartup EJB ����");
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

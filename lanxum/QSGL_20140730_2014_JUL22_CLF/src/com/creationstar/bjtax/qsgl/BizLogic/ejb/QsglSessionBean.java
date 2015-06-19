/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: QsglEJB(��˰ҵ��SessionBean) ��bean�� </p>
 * @author ��˰�����飭���Բ�
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
     * ����processor
     *
     * @param vo    the VOPackage
     * @return Object
     * @throws BaseException    �׳����쳣
     */
    public Object process(VOPackage vo) throws BaseException {
        try {
            Debug.out("into QsglSessionEJB....");
            Debug.out("EJB reflection Processor is :" + vo.getProcessor());
            return ((Processor) Class.forName(vo.getProcessor()).newInstance()).
                    process(vo);
        } catch (Exception e) {
            try {
                //�ж��Ƿ���������
                if (this.sessionContext != null) {
                    this.sessionContext.setRollbackOnly();
                }
            } catch (java.lang.IllegalStateException ex) {
                //����������
            } catch (Exception ex) {
                //��������
                throw new EJBException(ex);
            }

            //throw ExceptionUtil.getBaseException(userData, e);
            Debug.out("������EJB��: " + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
    }
}

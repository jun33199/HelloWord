package com.creationstar.bjtax.qsgl.BizLogic.ejb;

import java.util.ArrayList;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.creationstar.bjtax.qsgl.BizLogic.processor.InterFaceProcessor;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;


/**
 *
 * <p>Title: ������˰��������ϵͳ����Ʊ֤����</p>
 *
 * <p>Description: ��˰��˰ҵ���remote�ӿ� </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class QsglServiceSessionBean implements SessionBean {
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
    public Object getWszDataQS(String wszh, String ndzb) throws BaseException {
        try {
            Debug.out("into QsglServiceSessionEJB....");
            return InterFaceProcessor.getWszDataQS(wszh, ndzb);
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
            Debug.out("������InterfaceServiceEJB��: " + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }
    }


    public boolean jiebao(ArrayList ndzb_wszh, String jbdh, UserData ud) throws
            BaseException {
        try {
            Debug.out("into QsglServiceSessionEJB....");
            return InterFaceProcessor.Jiebao(ndzb_wszh, jbdh, ud);
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
            Debug.out("������InterfaceServiceEJB��: " + e.getMessage());
            throw ExceptionUtil.getBaseException(e);
        }

    }
}

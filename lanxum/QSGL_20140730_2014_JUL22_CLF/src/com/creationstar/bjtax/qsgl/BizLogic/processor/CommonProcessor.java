/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
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
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ���÷�����Processor��</p>
 * @author ��˰�����飭���Բ�
 * @version 1.0
 */
public class CommonProcessor implements Processor {
    /**
     * �Բ�ͬ�ĵ���ͨ���������ͽ��зַ���
     *
     * @param vo VOPackage
     * @return Object ҵ�����
     * @throws BaseException  �����׳����쳣
     */
    public Object process(VOPackage vo) throws BaseException {
        int optType = vo.getAction(); /* �������� */
        Object rtnObject = null; /* ���ض��� */

        /* ���ݲ������ͷַ�����
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
     * <p>�õ��û�����
     * <p>���̣�
     * <code>
     * <br>��ʼ��������
     *  <br>InitialContext ctx = ResourceLocator.getInitialContext();
     *  <br>�õ��û�����
     *  <br>UserTransaction userTransaction = UserTransaction)ctx.lookup
     * ("javax.transaction.UserTransaction");
     *  <br>return userTransaction;
     * </code>
     *
     * @return �û�����
     * @throws Exception �����쳣
     */
    protected UserTransaction getUserTransaction() throws BaseException {
        try {
            //��ʼ��������
            InitialContext ctx = ResourceLocator.getInitialContext();
            //�õ��û�����
            UserTransaction userTransaction = (UserTransaction) ctx.lookup(
                    "javax.transaction.UserTransaction");
            return userTransaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex,
                    "ZcwhProcessor �е� getUserTransction");
        }
    }
}

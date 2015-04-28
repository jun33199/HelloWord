package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

/**
 *
 * <p>Title: ǰ̨���ʹ�����</p>
 *
 * <p>Description: ����ǰ̨ҳ����Action��֮��Ĳ�����λ֮�ã����еĲ�����Ҫ�����
 * ������������ʶ������ֱ��д�ַ������÷�����
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾</p>
 *
 * @author ��˰�����顪���Բ�
 * @version 1.0
 */
public class OperationType implements Serializable {
    /**
     * ���
     */
    public static final int INSERT = 0;

    /**
     * ɾ��
     */
    public static final int DELETE = 1;

    /**
     * ����
     */
    public static final int MODIFY = 2;

    /**
     * ��ѯ
     */
    public static final int QUERY = 3;

    /**
     * ��ȡ���д���
     */
    public static final int LOADCODES = 4;

    /**
     * ��ȡ���д���
     */
    public static final int LOADCODESMAP = 5;

    /**
     * ��ȡһ�����ݣ�����������
     */
    public static final int GET = 6;

    /**
     * ��˰֤��Ŵ�ӡ
     */
    public static final int CHANGE = 7;

    /**
     * �ı��ӡ״̬
     */
    public static final int UPDATESTATE = 8;

}

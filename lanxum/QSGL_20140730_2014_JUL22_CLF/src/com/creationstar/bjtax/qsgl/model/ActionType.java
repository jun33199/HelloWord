package com.creationstar.bjtax.qsgl.model;

import java.io.Serializable;

/**
 *
 * <p>Title: ǰ̨���ʹ�����</p>
 *
 * <p>Description: ����ǰ̨Actionͨ��Proxy����λ��̨processor�ķ���֮�ã�
 * ���еĲ�����Ҫ�����������������ʶ������ֱ��д�ַ������÷�����
 * </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: ������Ѷ�������������޹�˾</p>
 *
 * @author ��˰�����顪���Բ�
 * @version 1.0
 */
public class ActionType implements Serializable {
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
     * ��ȡ�������ݼ�¼
     */
    public static final int GET = 6;

    /**
     * ���ͬ��
     */
    public static final int CONFIRM = 7;

    /**
     * ��˲�ͬ��
     */
    public static final int REJECT = 8;

    /**
     * ��˰��־���Ͻ�Ĵ���ҵ����ת�ʵķ�ʽ��˰�����������ɽɿ���
     */
    public static final int CREATE_JKS = 9;

    /**
     * ͨ��������˰֤���ɵĽɿ���
     */
    public static final int GENERAL_JKS = 10;

    /**
     * �����걨�����
     */
    public static final int CANCEL = 11;

    /**
     * ��ӡ�걨�����
     */
    public static final int PRINT_SBB = 12;

    /**
     * ��ӡ���֪ͨ��
     */
    public static final int PRINT_HDTZS = 13;

    /**
     * �����ϴβ������ָ�ԭ�ȵ�״̬
     */
    public static final int ROLLBACK = 14;

    /**
     * ����
     */
    public static final int CHECK = 15;

    /**
     * ������˰֤
     */
    public static final int CREATE_WSZ = 16;

    /**
     * ������˰֤
     */
    public static final int CX_WSZ = 17;

    /**
     * ������˰֤�����ɽɿ���
     */
    public static final int HZ_WSZ = 18;

    /**
     * ����������˰֤�������������ɵĽɿ���
     */
    public static final int CX_HZWSZ = 19;

    /**
     * ���½ɿ����״̬
     */
    public static final int CHANGE_JKS_STATUS = 20;

    /**
     * ������˰֤�Ĵ�ӡ״̬
     */
    public static final int CHANGE_WSZ_STATUS = 21;

    /**
     * ����ֱ�����ɵĽɿ���
     */
    public static final int CX_JKS = 22;

    /**
     * ��ȡָ�����ֵĻ���
     */
    public static final int GET_HL = 23;

    /**
     * ��ȡָ���ɿ�������������˰֤����Ϣ
     */
    public static final int JKS_QUERY_WSZ = 24;

    /**
     * ��ȡָ����˰֤�����ܵ����Žɿ����еĵ���Ϣ
     */
    public static final int WSZ_QUERY_JKS = 25;

    /**
     * ��ȡ��Ǩ������ס�������ݵ���Ϣ��ʹ�����
     */
    public static final int QUERY_USAGE = 26;
    /**
     * ����
     */
    public static final int UPDATE = 27;

    public static final int QUERY_SBZT = 28;

    /**
     * ��ѯ������������
     */
    public static final int QUERY_DRZB = 29;

    /**
     * ��������
     */
    public static final int RECEIVE_DRZB = 30;

    /**
     * ����xml�ļ�
     */
    public static final int UPLOAD_FILE = 31;

    /**
     * ɾ����������ȫ������
     */
    public static final int DELETE_ALLDR = 32;

    /**
     * ��ӡ��˰֤
     */
    public static final int PRINT_WSZ = 33;

    /**
     * ��Ŵ�ӡ
     */
    public static final int CHANGE_WSZH_PRINT = 34;

    /**
     * ��¼--��ѯ��¼�Ľɿ���(�걨�ɿ���)
     */
    public static final int BL_QUERY_JKS = 35;

    /**
     * ��¼--�����걨��Ų�ѯ��¼����˰֤��
     */
    public static final int BL_QUERY_WSZH = 36;

    /**
     * �˶Խ��
     */
    public static final int BL_CHECK_SBJKS = 37;

    /**
     * �˶Խ��
     */
    public static final int BL_CHECK_HZJKS = 38;

    /**
     * �Ͳ�¼���걨�ɿ��齨������
     */
    public static final int BL_CREATECONNECT_SBJKS = 39;

    /**
     * �Ͳ�¼�Ļ��ܽɿ��齨������
     */
    public static final int BL_CREATECONNECT_HZJKS = 40;

    /**
     * ��������
     */
    public static final int BL_REMOVECONNECT = 41;

    /**
     * ����ʣ���
     */
    public static final int UPDATE_SYE = 42;

    /**
     *
     */
    public static final int PRINT_CQQKB = 43;

    /**
     * ���º˶�֪ͨ��
     */
    public static final int UPDATE_HDTZS = 44;

    /**
     * ��ѯ�˶�֪ͨ��
     */
    public static final int Query_HDTZS = 45;

    /**
     * ���ݷ�α�����ѯ�˶�֪ͨ��
     */
    public static final int Query_HDTZSBYFWHM = 46;

    /**
     *
     */
    public static final int QUERYERR = 47;

    /**
     *
     */
    public static final int SAVEERR = 48;

    /**
     * ���ݺ˶�֪ͨ������ѯ�˶�֪ͨ��
     */
    public static final int Query_HDTZSBYHDTZSHM = 49;


    /**
     * ���ݺ˶�֪ͨ�������º˶�֪ͨ�����
     */
    public static final int UPDATE_HDTZSHM_BY_HDTZSHM = 50;

    /**
     * ��������Լ����������
     */
    public static final int SAVE_CXXJMSP = 51;

    /**
     * ������_����֤�������ѯ�ϴν���˰����Ϣ
     */
    public static final int Query_SBXXHISBYZJHM = 52;
 
    /**
     * ������_�����걨��Ų�ѯ�ϴν���˰����Ϣ
     */
    public static final int Query_SBXXHISBYSBBH = 53;
    
    /**
     * ������_˰��˰Ŀ��ʼ��
     */
    public static final int INITSZSMLIST = 54;
    
    /**
     * ������_�����걨��Ϣά��
     */
    public static final int CLF_MFSBXXWH = 55;
    
    /**
     * ������_���ݺ�ͬ��Ų�ѯ�����걨����
     */
    public static final int Query_SBXXBYHTBH = 56;
    
    /**
     * ������_�����걨����ά������ͬ��
     */
    public static final int DataSynchronism = 57;  

    /**
     * ��ӡ���ӽɿ�ר�ýɿ���
     */
    public static final int PRINT_JKS = 58; 
    /**
     * ��ѯ��˰֤
     */
    public static final int QUERY_WSZ = 59; 
    
}

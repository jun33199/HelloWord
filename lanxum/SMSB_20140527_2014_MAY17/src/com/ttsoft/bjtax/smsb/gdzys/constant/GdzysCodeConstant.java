package com.ttsoft.bjtax.smsb.gdzys.constant;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ  ����ռ��˰����
 * </p>
 * <p>
 * Description:��������
 * </p>
 * 
 * @author wangxq
 * @version 1.1
 */
public class GdzysCodeConstant {
	public GdzysCodeConstant() {
	}
    /** SMSB_  �����걨ͨ���¼����� */
    public final static int SMSB_SHOWACTION = 0;

    public final static int SMSB_SAVEACTION = 1;

    public final static int SMSB_DELETEACTION = 2;

    public final static int SMSB_QUERYACTION = 3;
    public final static int SMSB_GDZYS_CKJKSACTION = 10;
    public final static int SMSB_GDZYS_GETJKPZHCTION = 11;
    
    //����ռ��˰-�걨����-���߽ɿ���PROCESSOR
	public final static String GDZYS_SBZS_CJJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCjjksProcessor";
	
	  //����ռ��˰-�걨����-�������ɽ�ɿ���PROCESSOR
	public final static String GDZYS_SBZS_CJZNJJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCjznjjksProcessor";
	
	
    //����ռ��˰-�걨����-�����ɿ���PROCESSOR
	public final static String GDZYS_SBZS_CXJKS_PROCESSOR =
         "com.ttsoft.bjtax.smsb.gdzys.sbzs.processor.GdzysCxjksProcessor";

   //����ռ��˰-����˰����-����˰��ѯPROCESSOR
	public final static String 	GDZYS_JMSGL_JMSCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmscxProcessor";
   //����ռ��˰-����˰����-����˰֤����ӡPROCESSOR
	public final static String GDZYS_JMSGL_JMSZM_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmszmPrintProcessor";
	//����ռ��˰-����˰����-��������˰֤��PROCESSOR
	public final static String GDZYS_JMSGL_JMSCANCLE_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor.GdzysJmsCancleProcessor";
	//����ռ��˰-ҵ���ѯ-����˰֤����ѯPROCESSOR
	public final static String GDZYS_CX_JMSZMCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.cx.processor.GdzysJmszmCxProcessor";
	
	//����ռ��˰-ҵ���ѯ-�ɿ����ѯPROCESSOR
	public final static String GDZYS_YWCX_JKSCX_PROCESSOR=
			"com.ttsoft.bjtax.smsb.gdzys.cx.processor.GdzysJksCxProcessor";
	
	 //����ռ��˰-˰Դ��Ϣ¼�� action
    
    public final static int GDZYS_SYXXLRACTION_INIT = 1; //��ʼ��
    public final static int GDZYS_SYXXLRACTION_QUERY = 2; //��ѯ
    public final static int GDZYS_SYXXLRACTION_CONFIRM = 3; //ȷ��
    public final static int GDZYS_SYXXLRACTION_PRINT_SBB = 4; //��ӡ
    public final static int GDZYS_SYXXLRACTION_PRINT_JMSBB = 10; //��ӡ
    public final static int GDZYS_SYXXLRACTION_REMARK = 5; //��ע
    public final static int GDZYS_SYXXLRACTION_SAVE = 6; //����
    public final static int GDZYS_SYXXLRACTION_DJXX = 7; //��ȡ�Ǽ���Ϣ
    public final static int GDZYS_SYXXLRACTION_SYSE = 8;//������Ȼ�ȡ����˰��
    public final static int GDZYS_SYXXLRACTION_JMSYJ = 9;//����˰����
    public final static int GDZYS_SYXXLRACTION_ZRR = 11;//��Ȼ��
    public final static int GDZYS_SYXXLRACTION_QXSH = 12;//�������
    public final static int GDZYS_SYXXLRACTION_SJSH = 13;//ʫ���о����
    public final static int GDZYS_SYXXLRACTION_UPDATE = 14;//ʫ���о����
    public final static int GDZYS_SYXXLRACTION_BGQUERY = 15; //��ѯ
   //����ռ��˰-˰Դ��Ϣ¼�� action
    public final static String GDZYS_SYXXLRPROCESSOR = "com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.processor.GdzysSydjxxlrProcessor"; //�½�˰Դ�Ǽ�

    /**
     * ����ռ��˰�Ľɿ���
     */
    public final static String SMSB_SJLY_GDZYS = "38";

    
    
  //����ռ��˰˰�ִ���
	public final static String SMSB_GDZYS_SZDM = "73";
	//����ռ��˰˰������
	public final static String SMSB_GDZYS_SZMC = "����ռ��˰";
	
	//����ռ��˰˰��˰Ŀ����
	public final static String SMSB_GDZYS_SZSMDM = "730010";
	//����ռ��˰������Ŀ����
	public final static String SMSB_GDZYS_JMXMDM = "9990";
	//����ռ��˰�������ʴ���
	public final static String SMSB_GDZYS_JMXZDM = "14121301";
	
	//����ռ��˰˰��˰Ŀ����
	public final static String SMSB_GDZYS_SZSMMC = "����ռ��˰";
	
	//����ռ�����ɽ�˰˰��˰Ŀ����
	public final static String SMSB_GDZYS_ZNJ_SZSMDM = "730091";
	//����ռ�����ɽ�˰˰��˰Ŀ����
	public final static String SMSB_GDZYS_ZNJ_SZSMMC = "����ռ��˰���ɽ�";
	
	//���ص�ռ��˰�Ǽǲ�ѯ
	public static final int SMSB_GDZYS_DJQuery = 1;
	public static final int SMSB_GDZYS_DJQueryDetail = 2;
	public static final int SMSB_GDZYS_UPDATESH = 3;
	
	//����ռ��˰��� 
	public static final int SMSB_GDZYS_SYBGQUERY = 1;
	public static final int SMSB_GDZYS_SYBGQUERYDETAIL = 2;


}

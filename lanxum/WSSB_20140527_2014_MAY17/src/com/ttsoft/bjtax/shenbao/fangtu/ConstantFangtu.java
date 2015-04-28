package com.ttsoft.bjtax.shenbao.fangtu;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public interface ConstantFangtu
{
	/** Processor */
    public final static String FW_ZIYONG_PROCESSOR =
        "com.ttsoft.bjtax.shenbao.fangtu.processor.FWZiyongProcessor";
    public final static String FW_CHUZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.FWChuzuProcessor";
    public final static String FW_CHENGZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.FWChengzuProcessor";
    public final static String TD_ZIYONG_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.TDZiyongProcessor";
    public final static String TD_CHUZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.TDChuzuProcessor";
    public final static String TD_CHENGZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.TDChengzuProcessor";
	
    //�������
    public final static String ALTER_FW_ZIYONG_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterFWZiyongProcessor";
    public final static String ALTER_FW_CHUZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterFWChuzuProcessor";
    public final static String ALTER_FW_CHENGZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterFWChengzuProcessor";
    public final static String ALTER_TD_ZIYONG_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterTDZiyongProcessor";
    public final static String ALTER_TD_CHUZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterTDChuzuProcessor";
    public final static String ALTER_TD_CHENGZU_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.alter.AlterTDChengzuProcessor";
    
    //����
    public static final String JMZC_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.ZhengceProcessor";
    
    //������ʶ
    public static final int CAT_FW_ZIYONG = 1;
    public static final int CAT_FW_CHUZU = 2;
    public static final int CAT_FW_CHENGZU = 3;
    public static final int CAT_TD_ZIYONG = 4;
    public static final int CAT_TD_CHUZU= 5;
    public static final int CAT_TD_CHENGZU = 6;
    
    //����������
    public static final int CAT_FW_ZIYONG_COL  = 15;
    public static final int CAT_FW_CHUZU_COL   = 11;
    public static final int CAT_FW_CHENGZU_COL = 12;
    public static final int CAT_TD_ZIYONG_COL  = 15;
    public static final int CAT_TD_CHUZU_COL   = 14;
    public static final int CAT_TD_CHENGZU_COL = 12;

	
	
	/**Date format*/
	public static final String DATE_FORMAT = "yyyyMMdd";
	
	
	//�������,0-����,1-ɾ��,2-�޸�
	public static final String ALTER_TYPE_ADD = "0";
	public static final String ALTER_TYPE_DELETE = "1";
	public static final String ALTER_TYPE_UPDATE = "2";

	//���˱�ʶ,0-δ����,1-�Ѹ���
	public static final String AUDIT_FLAG_YES = "1";
	public static final String AUDIT_FLAG_NO = "0";
	
	//���ϵǼ������޸�����
	public static final String ADD_DATA = "1";
	public static final String UPDATE_DATA = "2";
	public static final String DELETE_DATA = "3";
	
	public static final int PAGE_SIZE_FANGTU = 5;

    public final static String FT_PRINT_PROCESSOR =
    	"com.ttsoft.bjtax.shenbao.fangtu.processor.FTPrintProcessor";
	//��ӡ����
	public static final int PRINT_DJ_ACTION = 0;
	public static final int PRINT_BG_ACTION = 1;
	
	/**
	 * �Ƿ�Ϊ������ҵ
	 */
	public static final String WZQY_FLAG_YES="1";
	public static final String WZQY_FLAG_NO="0";
	
	/**
	 * �Ƿ��������Ͷ����ҵ����ʹ�÷�
	 */
	public static final String WZQY_JN_FLAG_YES="0";
	public static final String WZQY_JN_FLAG_NO="1";
}

package com.ttsoft.bjtax.smsb.sgsswszmlr.common;
/**
 * 常量类
 * @author admin
 *
 */
public class Constant {
	//查询类型
	public static final String CONS_SGLR_QUERY_TYPE_0="0";//0--维护查询
	public static final String CONS_SGLR_QUERY_TYPE_1="1";//1--显示录入明细
	
	//凭证来源代码
	public static final String CONS_SGLR_PZLYDM_08="08";//08--手工完税证明（手工录入默认）
	
	//录入凭证类型
	public static final String CONS_SGLR_PZLXDM_0="0";//0-- 缴税凭证 （手工录入默认）
	public static final String CONS_SGLR_PZLXDM_1="1";//1-- 退税凭证
	
	//税收完税证明类型
/*	public static final String CONS_SGLR_SSWSZMLX_01="01";//01--表格式 （手工录入默认）
	public static final String CONS_SGLR_SSWSZMLX_02="02";//02--文书式
*/	
	//默认打印次数
	public static final int CONS_SGLR_DYCS_0=0;//初始为0次
	
	//打印标志
	public static final String CONS_SGLR_DYBZ_0="0";//0--未打印 
	public static final String CONS_SGLR_DYBZ_1="1";//1--已经打印
	
	//开具来源代码
	public static final String CONS_SGLR_KJLYDM_0="0";//0--税收完税证明管理 （手工录入默认）
	public static final String CONS_SGLR_KJLYDM_1="1";//1--申报换开 
	public static final String CONS_SGLR_KJLYDM_2="2";//2--保单换开
	
	//有效标志
	public static final String CONS_SGLR_YXBZ_0="0";// 0--有效 （手工录入默认）
	public static final String CONS_SGLR_YXBZ_1="1";// 1-- 注销
	
	
	//完税证主键分隔符
	public static final String CONS_SGLR_WSZMKEY_SPLIT="-";
	
	//是否重打
	public static final String CONS_SGLR_REPRINT_YES="Y";//操作为重打
	public static final String CONS_SGLR_REPRINT_NO="N";//操作为非重打（正常打印或者原号码打印）
	
	//保存是否成功
	public static final String CONS_SGLR_SAVEISSUCC_Y="Y";//保存成功
	public static final String CONS_SGLR_SAVEISSUCC_N="N";//保存失败
	
	//是否已经保存过
	public static final String CONS_SGLR_HASSAVED_Y="Y";//保存成功
	public static final String CONS_SGLR_HASSAVED_N="N";//保存失败
	
	//actionType
	public static final int QUERYONE=100;
	public static final int QUERYALL=101;
	public static final int CONS_SGLR_PRINT_SUCCESS=102;
	public static final int CONS_SGLR_PRINT_NEWPH=103;//取号重新打印
	public static final int CONS_SGLR_CANCLE=104;//作废票
	
	//票证种类代码
	public static final String CONS_SGLR_PZZLDM="1110"; //票样变更 完税证明表格式接口 tujb-20131212
	
	//完税证明是否被出具过完税证明
	public static final String CONS_SGLR_CJWSZM_BY_OTHERS_Y="1";//已经被其他程序出具过完税证明
	public static final String CONS_SGLR_CJWSZM_BY_OTHERS_N="0";//未被其他程序出具过完税证明
	
	
	
}

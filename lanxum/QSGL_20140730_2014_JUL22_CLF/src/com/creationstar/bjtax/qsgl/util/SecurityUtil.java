package com.creationstar.bjtax.qsgl.util;


import com.ttsoft.framework.exception.BaseException;

/**
 * <p>
 * Title: ��ȫ������
 * </p>
 * <p>
 * Description: ����sqlע��©��Processor
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class SecurityUtil {

	// ����Integer���Ͳ���
	public static String dealwithIntegerPara(String para) throws BaseException {
		if (para != null&&!para.equals("")) {
			Integer.parseInt(para);
		}
		return para;
	}

	// ����Long���Ͳ���
	public static String dealwithLongPara(String para) {
		if (para != null&&!para.equals("")) {
			Long.parseLong(para);
		}
		return para;
	}

	// ����Double���Ͳ���
	public static String dealwithDoublePara(String para) {
		if (para != null&&!para.equals("")) {
			Double.parseDouble(para);
		}
		return para;
	}

	// ����String���Ͳ���
	public static String dealwithStringPara(String para) {
		if (para != null&&!para.equals("")) {
			para = para.replaceAll("'", "''");
		}
		return para;
	}

}


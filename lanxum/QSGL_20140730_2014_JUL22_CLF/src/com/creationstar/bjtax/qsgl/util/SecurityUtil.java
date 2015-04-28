package com.creationstar.bjtax.qsgl.util;


import com.ttsoft.framework.exception.BaseException;

/**
 * <p>
 * Title: 安全处理类
 * </p>
 * <p>
 * Description: 处理sql注入漏洞Processor
 * </p>
 * 
 * @author wangxq
 * @version 1.0
 */
public class SecurityUtil {

	// 处理Integer类型参数
	public static String dealwithIntegerPara(String para) throws BaseException {
		if (para != null&&!para.equals("")) {
			Integer.parseInt(para);
		}
		return para;
	}

	// 处理Long类型参数
	public static String dealwithLongPara(String para) {
		if (para != null&&!para.equals("")) {
			Long.parseLong(para);
		}
		return para;
	}

	// 处理Double类型参数
	public static String dealwithDoublePara(String para) {
		if (para != null&&!para.equals("")) {
			Double.parseDouble(para);
		}
		return para;
	}

	// 处理String类型参数
	public static String dealwithStringPara(String para) {
		if (para != null&&!para.equals("")) {
			para = para.replaceAll("'", "''");
		}
		return para;
	}

}


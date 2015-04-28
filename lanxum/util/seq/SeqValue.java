package com.lscdz.util.seq;
/**
 * @author wangcy
 *
 */
public interface SeqValue {
	/**
	 * 获得表单唯一主键值
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public String getSeqValue(String seqName); //throws Exception;
}

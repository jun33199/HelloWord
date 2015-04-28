package com.lscdz.util.seq.impl;


import com.lscdz.util.seq.SeqValue;
import com.lscdz.util.seq.extdao.SeqAccessExt;

import yangjian.frame.util.FrameException;

public class SeqValueImpl implements SeqValue {
	// ���صĽ��ֵ
	public String result = null;

	/**
	 * ��ñ�Ψһ����ֵ
	 * @param seqName
	 * @return
	 * @throws Exception
	 */
	public String getSeqValue(String seqName)
	{
		try {
			result=SeqAccessExt.getSequenceVal(seqName);
		} catch (FrameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}

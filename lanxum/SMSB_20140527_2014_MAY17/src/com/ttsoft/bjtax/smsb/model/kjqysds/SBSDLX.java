package com.ttsoft.bjtax.smsb.model.kjqysds;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.*;


public class SBSDLX implements Serializable
{
    /**
     * �걨�������ʹ���
     */
    private String sbsdlxdm;

    /**
     * �걨������������
     */
    private String sbsdlxmc;

    public SBSDLX()
    {
    }

	public String getSbsdlxdm() {
		return sbsdlxdm;
	}

	public void setSbsdlxdm(String sbsdlxdm) {
		this.sbsdlxdm = sbsdlxdm;
	}

	public String getSbsdlxmc() {
		return sbsdlxmc;
	}

	public void setSbsdlxmc(String sbsdlxmc) {
		this.sbsdlxmc = sbsdlxmc;
	}

    
}

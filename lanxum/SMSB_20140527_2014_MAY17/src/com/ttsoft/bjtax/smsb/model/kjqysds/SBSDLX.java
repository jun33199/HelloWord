package com.ttsoft.bjtax.smsb.model.kjqysds;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.*;


public class SBSDLX implements Serializable
{
    /**
     * 申报所得类型代码
     */
    private String sbsdlxdm;

    /**
     * 申报所得类型名称
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

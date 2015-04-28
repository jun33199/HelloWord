package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: 北京地税综合管理系统  网上申报模块</p>
 * <p>Description: 减免税申报特定年月</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * @author tujb
 * @version 1.0 201404
 */
public class JmsSkny implements Serializable
{

	/**
     * 特定税款年月
     */
	private String skny;

	public String getSkny() {
		return skny;
	}

	public void setSkny(String skny) {
		this.skny = skny;
	}
}

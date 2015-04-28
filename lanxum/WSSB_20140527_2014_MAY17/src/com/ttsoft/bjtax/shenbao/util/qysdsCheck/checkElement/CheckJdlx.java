/**
 * @Title:       CheckJdlx.java
 * @Description: TODO
 * @date:        2014-4-19下午02:50:05
 * @version:     V1.0
 */
package com.ttsoft.bjtax.shenbao.util.qysdsCheck.checkElement;

/**
 * @Description: TODO 征管范围鉴定类型常量
 * @author: 	 Lijn
 * @time:        2014-4-19
 */
public interface CheckJdlx extends Observer{

	/**
	 * 企业所得税征管范围鉴定——独立纳税人
	 */
	public final static String QYSDSZGFWJDLX_DLNSR = "01";
	
	/**
	 * 企业所得税征管范围鉴定——跨省市总机构纳税人
	 */
	public final static String QYSDSZGFWJDLX_KSSZJGNSR = "02";
	
	/**
	 * 企业所得税征管范围鉴定——跨省市分支机构纳税人
	 */
	public final static String QYSDSZGFWJDLX_KSSFZJGNSR = "03";
	
	/**
	 * 企业所得税征管范围鉴定——总分机构均在本市的总机构纳税人
	 */
	public final static String QYSDSZGFWJDLX_ZFJGJZBSZJGNSR = "04";
	
	/**
	 * 企业所得税不由地税管辖
	 */
	public final static String QYSDSZGFWJDLX_BYDZGX = "06";
	
	/**
	 * 企业所得税不需交纳
	 */
	public final static String QYSDSZGFWJDLX_BXJN = "07"; 
	
	/**
	 * Description：不需分摊企业所得税
	 */
	public final static String QYSDSZGFWJDLX_BXFTQYSDS = "08";
}

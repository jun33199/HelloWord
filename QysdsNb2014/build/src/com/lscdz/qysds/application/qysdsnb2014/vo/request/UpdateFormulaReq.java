package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import java.io.Serializable;

/**
 * 更新审核公式
 * 项目名称：QysdsNb2014   
 * 类名称：UpdateFormulaReq   
 * 类描述：   
 * 创建人：wangcy 
 * 创建时间：2014-11-28 下午2:17:34   
 * 修改人：wangcy   
 * 修改时间：2014-11-28 下午2:17:34   
 * 修改备注：   
 * @version  1.0
 */
public class UpdateFormulaReq  implements Serializable{
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 120934719283748761L;
	/**
	 * 计算机代码
	 */
    private String jsjdm;
    /**
     * 年度
     */
    private String nd;
    /**
     * aid
     */
    private String AID;
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getAID() {
		return AID;
	}
	public void setAID(String aID) {
		AID = aID;
	}
	 
}

/**
 * @Title:       ZjgInf.java
 * @Description: TODO
 * @date:        2014-7-30上午09:10:03
 * @version:     V1.0
 */
package com.lscdz.qysds.common.service.qysdsCheck.bo;

import java.io.Serializable;

/**
 * @Description: TODO 总分机构备案对外接口model
 * @author: 	 Lijn
 * @time:        2014-7-30
 */
public class ZfjgInf implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	 * Description：备案企业类型
	 */
	private String baqylx;
	
/*----分支机构备案信息----*/
	
	/**
	 * Description：分支机构的总机构信息:上年度是否为小型微利企业
	 */
	private boolean isFzjg_zjginf_XWQY=false;
	
	/**
	 * Description：分支类型
	 */
	private String fzlx;
	
	
	/**
	 * Description：是否参与分摊企业所得税
	 */
	private boolean isCyftsk=false;

	
	
	
/*----------总机构备案信息----------------*/
	/**
	 * Description：是否分摊企业所得税
	 */
	private boolean isftsk=false;
	
	/**
	 * Description：是否是小微企业
	 */
	private boolean isXWQY=false;


	
/*------------------------------*/
	/**
	 * @description: getter-- baqylx
	 * @return the baqylx
	 */
	public String getBaqylx() {
		return baqylx;
	}

	/**
	 * @description: setter-- baqylx
	 * @param baqylx the baqylx to set
	 */
	public void setBaqylx(String baqylx) {
		this.baqylx = baqylx;
	}

	/**
	 * @description: getter-- isFzjg_zjginf_XWQY
	 * @return the isFzjg_zjginf_XWQY
	 */
	public boolean isFzjg_zjginf_XWQY() {
		return isFzjg_zjginf_XWQY;
	}

	/**
	 * @description: setter-- isFzjg_zjginf_XWQY
	 * @param isFzjg_zjginf_XWQY the isFzjg_zjginf_XWQY to set
	 */
	public void setFzjg_zjginf_XWQY(boolean isFzjg_zjginf_XWQY) {
		this.isFzjg_zjginf_XWQY = isFzjg_zjginf_XWQY;
	}

	/**
	 * @description: getter-- fzlx
	 * @return the fzlx
	 */
	public String getFzlx() {
		return fzlx;
	}

	/**
	 * @description: setter-- fzlx
	 * @param fzlx the fzlx to set
	 */
	public void setFzlx(String fzlx) {
		this.fzlx = fzlx;
	}

	/**
	 * @description: getter-- isCyftsk
	 * @return the isCyftsk
	 */
	public boolean isCyftsk() {
		return isCyftsk;
	}

	/**
	 * @description: setter-- isCyftsk
	 * @param isCyftsk the isCyftsk to set
	 */
	public void setCyftsk(boolean isCyftsk) {
		this.isCyftsk = isCyftsk;
	}

	/**
	 * @description: getter-- isftsk
	 * @return the isftsk
	 */
	public boolean isIsftsk() {
		return isftsk;
	}

	/**
	 * @description: setter-- isftsk
	 * @param isftsk the isftsk to set
	 */
	public void setIsftsk(boolean isftsk) {
		this.isftsk = isftsk;
	}

	/**
	 * @description: getter-- isXWQY
	 * @return the isXWQY
	 */
	public boolean isXWQY() {
		return isXWQY;
	}

	/**
	 * @description: setter-- isXWQY
	 * @param isXWQY the isXWQY to set
	 */
	public void setXWQY(boolean isXWQY) {
		this.isXWQY = isXWQY;
	}

		
	
	

}

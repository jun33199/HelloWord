/**
 * @Title:       ZjgInf.java
 * @Description: TODO
 * @date:        2014-7-30����09:10:03
 * @version:     V1.0
 */
package com.lscdz.qysds.common.service.qysdsCheck.bo;

import java.io.Serializable;

/**
 * @Description: TODO �ֻܷ�����������ӿ�model
 * @author: 	 Lijn
 * @time:        2014-7-30
 */
public class ZfjgInf implements Serializable{

	/** 
	* @Fields serialVersionUID : TODO 
	*/ 
	private static final long serialVersionUID = 1L;

	/**
	 * Description��������ҵ����
	 */
	private String baqylx;
	
/*----��֧����������Ϣ----*/
	
	/**
	 * Description����֧�������ܻ�����Ϣ:������Ƿ�ΪС��΢����ҵ
	 */
	private boolean isFzjg_zjginf_XWQY=false;
	
	/**
	 * Description����֧����
	 */
	private String fzlx;
	
	
	/**
	 * Description���Ƿ�����̯��ҵ����˰
	 */
	private boolean isCyftsk=false;

	
	
	
/*----------�ܻ���������Ϣ----------------*/
	/**
	 * Description���Ƿ��̯��ҵ����˰
	 */
	private boolean isftsk=false;
	
	/**
	 * Description���Ƿ���С΢��ҵ
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

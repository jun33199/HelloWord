package com.lscdz.qysds.application.qysdsnb2014.vo.request;

import java.io.Serializable;

/**
 * ������˹�ʽ
 * ��Ŀ���ƣ�QysdsNb2014   
 * �����ƣ�UpdateFormulaReq   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-11-28 ����2:17:34   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-11-28 ����2:17:34   
 * �޸ı�ע��   
 * @version  1.0
 */
public class UpdateFormulaReq  implements Serializable{
	/**
	 * ���к�
	 */
	private static final long serialVersionUID = 120934719283748761L;
	/**
	 * ���������
	 */
    private String jsjdm;
    /**
     * ���
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

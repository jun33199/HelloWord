package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:2008����������ҵ����˰����</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class CzzssdsjbForm  extends QysdsNewForm{
	
	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ���
	 * String[]
	 */
	private String[] hdjb_columns = { "hc","lje"};
    /**
     * �����ӱ������
     */
    private String[] cbsb_columns = {"cbJmxmYzhc", "cbJmxmYz","cbJmxmDmhc", "cbJmxmDm"};
    /**
     * �����ӱ������
     */
    private String[] cbsbMssrxmCol = {"cbMssrxmYzhc", "cbMssrxmYz","cbMssrxmDmhc", "cbMssrxmDm"};
    /**
     * �����ӱ������
     */
    private String[] cbsbJzmzxmCol = {"cbJzmzxmYzhc", "cbJzmzxmYz","cbJzmzxmDmhc", "cbJzmzxmDm"};
	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsjbList = new ArrayList();
	private List cbsb_qysdsjbList = new ArrayList();
	private List cbsbMssrxmList = new ArrayList();
	private List cbsbJzmzxmList = new ArrayList();
	private String SAVE_FLAG="0";//0-��ʼֵ,1-����ɹ�	
	
	private String xzqy;
	//˰Դ��ʶ
	private String sybs;
	//һ�����˰��
	private String ybjmsl;
	//��˰��ʽ
	private String nsfs;
	//�ֻܷ���
	private String zfjg;
	//�����ʸ�
	private String jmzg;
	
	//˰��������ȵ��ڿ�ҵ�Ǽ�����Y ��N
	private String sfxkh;
	//��ȡ˰�����������������һ������շ�ʽ
	private String syndZsfsdm;
	//��ȡ��һ��Ⱥ˶������걨��6����
	private String syndZbh6;
	//��ȡ��һ��Ȼ������������25����
	private String syndZbh25;
	//��ȡ��һ��Ȼ�����ɸ���5��45��46��47��У����
	private String syndFb5jyjg;
		
	//�Ƿ�ִ���˲�ѯ   �����ѯ�������µ�˰Դ��ʶ 1 �����ѯ��
	private String isQuery;
	
	//�Ƿ��¿�ҵ���¿�ҵ�Ĳ��μ�˰���̯
	private String isXky;
	//��˰������Ŀ�����
	private List mssrxmList = new ArrayList();
	private String mssrxmdm;
	//������������Ŀ�����
	private List jzmzxmList = new ArrayList();
	private String jzmzxmdm;
	//������Ŀ�����
	private List jmxmList = new ArrayList();
	private String jmxmdm;
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}

	//��ȡ���ݿ��е���˰�������ֻܷ�������ֵ
	private HashMap nsfs_fsjg = new HashMap();
	
	public String getJmzg() {
		return jmzg;
	}
	
	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
	}
	
	public String getXzqy() {
		return xzqy;
	}
	
	public void setXzqy(String xzqy) {
		this.xzqy = xzqy;
	}

	public String getYbjmsl() {
		return ybjmsl;
	}
	
	public void setYbjmsl(String ybjmsl) {
		this.xzqy = ybjmsl;
	}
	
	public String[] getColumns() {
		return hdjb_columns;
	}

	public void setColumns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public List getQysdsjbList() {
		return qysdsjbList;
	}

	public void setQysdsjbList(List qysdsjbList) {
		this.qysdsjbList = qysdsjbList;
	}

	/**
	 * @return Returns the sAVE_FLAG.
	 */
	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	/**
	 * @param save_flag The sAVE_FLAG to set.
	 */
	public void setSAVE_FLAG(String save_flag) {
		SAVE_FLAG = save_flag;
	}

	public String[] getHdjb_columns() {
		return hdjb_columns;
	}

	public void setHdjb_columns(String[] hdjb_columns) {
		this.hdjb_columns = hdjb_columns;
	}

	public String getSfxkh() {
		return sfxkh;
	}

	public void setSfxkh(String sfxkh) {
		this.sfxkh = sfxkh;
	}

	public String getSyndZsfsdm() {
		return syndZsfsdm;
	}

	public void setSyndZsfsdm(String syndZsfsdm) {
		this.syndZsfsdm = syndZsfsdm;
	}

	public String getSyndZbh6() {
		return syndZbh6;
	}

	public void setSyndZbh6(String syndZbh6) {
		this.syndZbh6 = syndZbh6;
	}

	public String getSyndZbh25() {
		return syndZbh25;
	}

	public void setSyndZbh25(String syndZbh25) {
		this.syndZbh25 = syndZbh25;
	}

	public String getSyndFb5jyjg() {
		return syndFb5jyjg;
	}

	public void setSyndFb5jyjg(String syndFb5jyjg) {
		this.syndFb5jyjg = syndFb5jyjg;
	}

	public String getSybs() {
		return sybs;
	}

	public void setSybs(String sybs) {
		this.sybs = sybs;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getNsfs() {
		return nsfs;
	}

	public void setNsfs(String nsfs) {
		this.nsfs = nsfs;
	}

	public String getZfjg() {
		return zfjg;
	}

	public void setZfjg(String zfjg) {
		this.zfjg = zfjg;
	}

	public String getIsXky() {
		return isXky;
	}

	public void setIsXky(String isXky) {
		this.isXky = isXky;
	}

	public String[] getCbsb_columns() {
		return cbsb_columns;
	}

	public void setCbsb_columns(String[] cbsbColumns) {
		cbsb_columns = cbsbColumns;
	}

	public List getCbsb_qysdsjbList() {
		return cbsb_qysdsjbList;
	}

	public void setCbsb_qysdsjbList(List cbsbQysdsjbList) {
		cbsb_qysdsjbList = cbsbQysdsjbList;
	}

	public List getJmxmList() {
		return jmxmList;
	}

	public void setJmxmList(List jmxmList) {
		this.jmxmList = jmxmList;
	}

	public String getJmxmdm() {
		return jmxmdm;
	}

	public void setJmxmdm(String jmxmdm) {
		this.jmxmdm = jmxmdm;
	}

	public List getMssrxmList() {
		return mssrxmList;
	}

	public void setMssrxmList(List mssrxmList) {
		this.mssrxmList = mssrxmList;
	}

	public String getMssrxmdm() {
		return mssrxmdm;
	}

	public void setMssrxmdm(String mssrxmdm) {
		this.mssrxmdm = mssrxmdm;
	}

	public List getJzmzxmList() {
		return jzmzxmList;
	}

	public void setJzmzxmList(List jzmzxmList) {
		this.jzmzxmList = jzmzxmList;
	}

	public String getJzmzxmdm() {
		return jzmzxmdm;
	}

	public void setJzmzxmdm(String jzmzxmdm) {
		this.jzmzxmdm = jzmzxmdm;
	}

	public String[] getCbsbMssrxmCol() {
		return cbsbMssrxmCol;
	}

	public void setCbsbMssrxmCol(String[] cbsbMssrxmCol) {
		this.cbsbMssrxmCol = cbsbMssrxmCol;
	}

	public String[] getCbsbJzmzxmCol() {
		return cbsbJzmzxmCol;
	}

	public void setCbsbJzmzxmCol(String[] cbsbJzmzxmCol) {
		this.cbsbJzmzxmCol = cbsbJzmzxmCol;
	}

	public List getCbsbMssrxmList() {
		return cbsbMssrxmList;
	}

	public void setCbsbMssrxmList(List cbsbMssrxmList) {
		this.cbsbMssrxmList = cbsbMssrxmList;
	}

	public List getCbsbJzmzxmList() {
		return cbsbJzmzxmList;
	}

	public void setCbsbJzmzxmList(List cbsbJzmzxmList) {
		this.cbsbJzmzxmList = cbsbJzmzxmList;
	}

}

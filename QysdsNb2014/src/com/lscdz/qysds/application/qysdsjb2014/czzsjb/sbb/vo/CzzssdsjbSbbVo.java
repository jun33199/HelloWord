package com.lscdz.qysds.application.qysdsjb2014.czzsjb.sbb.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.lscdz.qysds.application.qysdsjb2014.base.vo.QysdsjbBaseVo;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:2008����������ҵ����˰����</p>
 * @author Ha Zhengze
 * @version 1.1
 */
public class CzzssdsjbSbbVo  extends QysdsjbBaseVo{
		
	private String xzqy;
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
	private String cyl;
	private String dezsse;
	private String qyzslx;
	private String sysl;
	public HashMap getNsfs_fsjg() {
		return nsfs_fsjg;
	}

	public void setNsfs_fsjg(HashMap nsfsFsjg) {
		nsfs_fsjg = nsfsFsjg;
	}

	//��ȡ���ݿ��е���˰�������ֻܷ�������ֵ
	private HashMap nsfs_fsjg = new HashMap();
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
		this.ybjmsl = ybjmsl;
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

	public String getJmzg() {
		return jmzg;
	}

	public void setJmzg(String jmzg) {
		this.jmzg = jmzg;
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

	public String getIsXky() {
		return isXky;
	}

	public void setIsXky(String isXky) {
		this.isXky = isXky;
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

	public String getCyl() {
		return cyl;
	}

	public void setCyl(String cyl) {
		this.cyl = cyl;
	}

	public String getDezsse() {
		return dezsse;
	}

	public void setDezsse(String dezsse) {
		this.dezsse = dezsse;
	}

	public String getQyzslx() {
		return qyzslx;
	}

	public void setQyzslx(String qyzslx) {
		this.qyzslx = qyzslx;
	}

	public String getSysl() {
		return sysl;
	}

	public void setSysl(String sysl) {
		this.sysl = sysl;
	}




}

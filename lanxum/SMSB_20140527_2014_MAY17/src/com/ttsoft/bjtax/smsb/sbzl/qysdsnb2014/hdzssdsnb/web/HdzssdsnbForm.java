/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.hdzssdsnb.web;

import java.util.ArrayList;
import java.util.List;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;


/**
 * 2014�˶�������ҵ����˰�걨
 * ��Ŀ���ƣ���ҵ����˰
 * �����ƣ�HdzssdsnbForm   
 * ��������   
 * �����ˣ�wangcy 
 * ����ʱ�䣺2014-4-10 ����3:36:10   
 * �޸��ˣ�wangcy   
 * �޸�ʱ�䣺2014-4-10 ����3:36:10   
 * �޸ı�ע��   
 * @version  1.0
 */
public class HdzssdsnbForm extends QysdsNewForm {
	public HdzssdsnbForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ����������ۼ��� String[]
	 */
	private String[] hdnb_columns = { "hc", "lje" };

	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List qysdsnbList = new ArrayList();

	// ������ҵ
	private String xzqy;

	// һ�����˰��
	private String ybjmsl;

	// �����ʸ�
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
    private ArrayList gjbzhy;//������ҵ�����
    private String gjbzhydm;//������ҵ����	
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
		return hdnb_columns;
	}

	public void setColumns(String[] hdnb_columns) {
		this.hdnb_columns = hdnb_columns;
	}

	public List getQysdsnbList() {
		return qysdsnbList;
	}

	public void setQysdsnbList(List qysdsnbList) {
		this.qysdsnbList = qysdsnbList;
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

	public ArrayList getGjbzhy() {
		return gjbzhy;
	}

	public void setGjbzhy(ArrayList gjbzhy) {
		this.gjbzhy = gjbzhy;
	}

	public String getGjbzhydm() {
		return gjbzhydm;
	}

	public void setGjbzhydm(String gjbzhydm) {
		this.gjbzhydm = gjbzhydm;
	}

}

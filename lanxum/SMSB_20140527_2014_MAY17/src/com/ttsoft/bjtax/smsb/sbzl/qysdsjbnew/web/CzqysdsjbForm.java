/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjbnew.web;

import java.util.ArrayList;
import java.util.List;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.QysdsNewForm;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����-����ģ��
 * </p>
 * <p>
 * Description:����������ҵ����˰����
 * </p>
 * 
 * @author li wenhua
 * @version 1.1
 */

public class CzqysdsjbForm extends QysdsNewForm {
	public CzqysdsjbForm() {

	}

	/**
	 * ����˰�걨�б������Ŀ���� �дΡ��ۼƽ�� String[]
	 */
	private String[] czjb_columns = { "hc", "ljje" };

	/**
	 * ���ڴ洢��ϸ�о�����ֵ List
	 */
	private List czsdsjbList = new ArrayList();
	
	/**
	 * ������ҵ
	 */
	private String xzqy;
	
	//һ�����˰��
	private String ybjmsl;
	
	//�����ʸ�
	private String jmzg;
	
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
		this.ybjmsl = ybjmsl;
	}

	public String[] getColumns() {
		return czjb_columns;
	}

	public void setColumns(String[] hdjb_columns) {
		this.czjb_columns = hdjb_columns;
	}

	public List getQysdsjbList() {
		return czsdsjbList;
	}

	public void setQysdsjbList(List czsdsjbList) {
		this.czsdsjbList = czsdsjbList;
	}
}

package com.ttsoft.bjtax.smsb.sbzl.qyqssds2014.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * 
 * ��Ŀ���ƣ�smsb �����ƣ�QyqssdsBaseForm �������� ��ҵ����˰���㡡���ࡡ �����ˣ�wangcy ����ʱ�䣺2014-2-14
 * ����4:17:53 �޸��ˣ�wangcy �޸�ʱ�䣺2014-2-14 ����4:17:53 �޸ı�ע��
 * 
 * @version 1.0
 */
public class QyqssdsBaseForm extends BaseForm {

	public QyqssdsBaseForm() {
		try {
			jbInit();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * �걨���� String
	 */
	private String tbrq;

	/**
	 * ��˰�˼�������� String
	 */
	private String jsjdm;

	/**
	 * ��˰������ String
	 */
	private String nsrmc;

	/**
	 * �����˴��� String
	 * 
	 * �ӵ�¼������ȡ��
	 */
	private String cjr;
	/**
	 * ¼���˴��� String
	 * 
	 * �ӵ�¼������ȡ��
	 */
	private String lrr;

	/**
	 * ¼������ String
	 */

	private String lrrq;

	/**
	 * ����ʱ�� String
	 */

	private String cjsj;

	/**
	 * 
	 * ���㱸����ʼ����
	 */
	private String qsbaksrq;

	/**
	 * ˰����� String
	 * Ϊ���㱸�����ͨ�����ڵ����
	 * ��ҪΪ�˱����ܹ����棨����ҵ����˰������ɱ���һ�£�
	 */
	private String sknd;
	/**
	 * 
	 * ���㱸����������
	 */
	private String qsbajsrq;

	/**
	 * 
	 * �����걨��ʼ����
	 */
	private String qssbksrq;

	/**
	 * 
	 * �����걨��������
	 */
	private String qssbjsrq;

	/**
	 * ������-�����걨��ʼ����
	 */
	private String hid_qssbksrq;

	/**
	 * ������-�����걨��������
	 */
	private String hid_qssbjsrq;
	/**
	 * ˰�������֯�������� String
	 * 
	 * �ӵ�¼������ȡ��
	 */
	private String swjgzzjgdm;

	/**
	 * ����˰����ش��� String
	 */
	private String zgswjgzzjgdm;

	/**
	 * ����˰��������� String
	 */
	private String zgswjgzzjgmc;

	/**
	 * ˰�������� String
	 */
	private String swjsjdm;

	/**
	 * ˰�������� String
	 */
	private String swjgzzjgmc;

	/**
	 * ��ҵ����˰�걨����
	 */
	private List qyqssds = new ArrayList();

	/**
	 * �ۺ��걨��ת��
	 */
	private String iszhsb;

	/**
	 * ���ش���
	 */
	private String qxdm;

	/**
	 * У����List
	 */
	private List checkList;

	/**
	 * ������������
	 */
	private String ssjjlx;

	/**
	 * �����˻�������������Ա
	 */
	private String qsllry = "";
	/**
	 * ��ϵ�绰
	 */
	private String lxdh;

	/**
	 * ������ҵ
	 */
	private String sshy;

	/**
	 * ����������
	 */
	private String bbqlx;

	/**
	 * �ں�
	 */
	private String qh;

	/**
	 * ��˰��������д�������˰�걨��
	 */
	private List tableList = new ArrayList();

	/**
	 * ��˰��ʶ���
	 */
	private String nsrsbh;

	/**
	 * ��Ӫ��ַ
	 */
	private String jydz;

	/**
	 * ������URL HTML
	 */
	private String linkUrlHTML;

	/**
	 * ������MAP ��Ź��� 1���ؼ�����N������next����ͷ�ı�ʾ��Ӧ��valueΪ��һ�ű����ӣ��ؼ�����P������previous��
	 * ��ͷ�ı�ʾ��Ӧ��valueΪ��һ�ű����� 2��N��P֮������ֱ�ʾ��ǰ���ID
	 * 
	 * ���� key��N_2,��ʾ��ǰ���idΪ��2������Ӧ��Ϊ��ǰ�����һ�ű�����
	 */
	private Map linkMap = new HashMap();

	/**
	 * ��һ�ű�����
	 */
	private String nextTableURL;

	/**
	 * ��һ�ű�����
	 */
	private String previousTableURL;

	/**
	 * �Ƿ�Ϊ���һ�ű� �ǣ�yes ��no
	 */
	private String isLastTable;

	// ��ҵ�³̹涨�ľ�Ӫ���޽���
	private String jyqxjm = "";
	// ��ҵ�ɶ��ᡢ�ɶ��������ƻ��������ɢ
	private String gdjyjs = "";
	// ��ҵ����������Ӫҵִ�ա�����رջ��߱�����
	private String yfdxgb = "";
	// ��ҵ������Ժ�������Խ�ɢ�������Ʋ�
	private String yfxgpc = "";
	// �йط��ɡ���������涨����
	private String yfgdqs = "";
	// ��ҵ������ԭ���ɢ���������
	private String qtyy = "";

	// �������״̬��ʶ
	private String baShztbs = "";
	// �������״̬����
	private String baShztMessage = "";
	// �������ͨ������
	private String baShtgrq = "";

	// �걨���״̬��ʶ  ��д�����걨����
	private String sbShztbs = "";
	// �������״̬����
	private String sbShztMessage = "";
	// �������ͨ������
	private String sbShtgrq = "";
	
	// �Ƿ񱣴��걨����  0 û�б��� 1 �ѱ���
	private String isBcsbrq = "";

	// ��������
	private String sqlx="";
	
	//˰��������
	private String skssq;
	
	public String getSkssq() {
		return skssq;
	}
	

	public void setSkssq(String skssq) {
		this.skssq = skssq;
	}

	/**
	 * ����Form������
	 */
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("------QysdsNewForm Properties----"
				+ this.getClass().getName() + "\n");
		buffer.append("���������--" + this.jsjdm + "\n");
		buffer.append("��˰������--" + this.nsrmc + "\n");
		buffer.append("��˰��ʶ���--" + this.nsrsbh + "\n");
		buffer.append("�����--" + this.tbrq + "\n");
		buffer.append("���㿪ʼ����--" + this.qssbksrq + "\n");
		buffer.append("�����������--" + this.qssbjsrq + "\n");
		buffer.append("˰�������֯��������--" + this.swjgzzjgdm + "\n");
		buffer.append("˰���������--" + this.swjgzzjgmc + "\n");
		buffer.append("˰����������--" + this.swjsjdm + "\n");
		buffer.append("������ҵ--" + this.sshy + "\n");
		buffer.append("������������--" + this.ssjjlx + "\n");
		buffer.append("��Ӫ��ַ--" + this.jydz + "\n");
		buffer.append("�ں�--" + this.qh + "\n");
		buffer.append("����������--" + this.bbqlx + "\n");
		buffer.append("-------QysdsNewForm Properties----" + "\n");
		return buffer.toString();
	}

	
	public String getSqlx() {
		return sqlx;
	}


	public void setSqlx(String sqlx) {
		this.sqlx = sqlx;
	}


	public String getNsrsbh() {
		return nsrsbh;
	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public List getCheckList() {
		return checkList;
	}

	public void setCheckList(List checkList) {
		this.checkList = checkList;
	}

	public String getQxdm() {
		return this.qxdm;
	}

	public void setQxdm(String _qxdm) {
		this.qxdm = _qxdm;
	}

	public String getIszhsb() {
		return this.iszhsb;
	}

	public void setIszhsb(String _iszhsb) {
		this.iszhsb = _iszhsb;
	}

	
	public String getZgswjgzzjgdm() {
		return this.zgswjgzzjgdm;
	}

	public void setZgswjgzzjgdm(String _zgswjgzzjgdm) {
		this.zgswjgzzjgdm = _zgswjgzzjgdm;
	}

	public String getZgswjgzzjgmc() {
		return this.zgswjgzzjgmc;
	}

	public void setZgswjgzzjgmc(String _zgswjgzzjgmc) {
		this.zgswjgzzjgmc = _zgswjgzzjgmc;
	}

	public String getSwjsjdm() {
		return swjsjdm;
	}

	public void setSwjsjdm(String swjsjdm) {
		this.swjsjdm = swjsjdm;
	}

	public void setSwjgzzjgdm(String _swjgzzjgdm) {
		this.swjgzzjgdm = _swjgzzjgdm;
	}

	public String getSwjgzzjgmc() {
		return this.swjgzzjgmc;
	}

	public void setSwjgzzjgmc(String _swjgzzjgmc) {
		this.swjgzzjgmc = _swjgzzjgmc;
	}

	public String getJsjdm() {
		return this.jsjdm;
	}

	public void setJsjdm(String _jsjdm) {
		this.jsjdm = _jsjdm;
	}

	public String getNsrmc() {
		return this.nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	

	public List getDataList() {
		return this.qyqssds;
	}

	public void setDataList(List qyqssds) {
		this.qyqssds = qyqssds;
	}

	
	public String getLrr() {
		return lrr;
	}

	public void setLrr(String _lrr) {
		lrr = _lrr;
	}

	public String getCjrq() {
		return cjsj;
	}

	public void setCjrq(String _cjsj) {
		cjsj = _cjsj;
	}

	public String getSwjgzzjgdm() {
		return swjgzzjgdm;
	}

	public void setSwjgzzjgdm2(String _swjgzzjgdm2) {
		swjgzzjgdm = _swjgzzjgdm2;
	}

	public String getLrrq() {
		return lrrq;
	}

	public void setLrrq(String _lrrq) {
		lrrq = _lrrq;
	}

	
	
	

	/**
	 * ҳ��Ҫ�����
	 * 
	 * @param actionMapping
	 *            struts.action.ActionMapping
	 * @param httpServletRequest
	 *            HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {

		jsjdm = null;
		nsrsbh = null;
		nsrmc = null;
		lrr = null;
		lrrq = null;
		cjsj = null;
		swjgzzjgdm = null;
		zgswjgzzjgdm = null;
		zgswjgzzjgmc = null;
		swjsjdm = null;
		swjgzzjgmc = null;
		qxdm = null;
		ssjjlx = null;
		lxdh = null;
		sshy = null;
		bbqlx = null;
		qh = null;
		jydz = null;
		linkUrlHTML = null;
		this.getTableList().clear();
		// this.getDataList().clear();
	}

	private void jbInit() throws Exception {
	}

	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}
	
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getQh() {
		return qh;
	}

	public void setQh(String qh) {
		this.qh = qh;
	}

	public String getSshy() {
		return sshy;
	}

	public void setSshy(String sshy) {
		this.sshy = sshy;
	}

	public String getSsjjlx() {
		return ssjjlx;
	}

	public void setSsjjlx(String ssjjlx) {
		this.ssjjlx = ssjjlx;
	}

	public List getTableList() {
		return tableList;
	}

	public void setTableList(List tableList) {
		this.tableList = tableList;
	}


	public String getJydz() {
		return jydz;
	}

	public void setJydz(String jydz) {
		this.jydz = jydz;
	}

	public String getLinkUrlHTML() {
		return linkUrlHTML;
	}

	public void setLinkUrlHTML(String linkUrlHTML) {
		this.linkUrlHTML = linkUrlHTML;
	}

	public Map getLinkMap() {
		return linkMap;
	}

	public void setLinkMap(Map linkMap) {
		this.linkMap = linkMap;
	}

	public String getNextTableURL() {
		return nextTableURL;
	}

	public void setNextTableURL(String nextTableURL) {
		this.nextTableURL = nextTableURL;
	}

	public String getPreviousTableURL() {
		return previousTableURL;
	}

	public void setPreviousTableURL(String previousTableURL) {
		this.previousTableURL = previousTableURL;
	}

	public String getIsLastTable() {
		return isLastTable;
	}

	public void setIsLastTable(String isLastTable) {
		this.isLastTable = isLastTable;
	}

	public String getCjsj() {
		return cjsj;
	}

	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}

	public List getQyqssds() {
		return qyqssds;
	}

	public void setQyqssds(List qyqssds) {
		this.qyqssds = qyqssds;
	}

	public String getTbrq() {
		return tbrq;
	}

	public void setTbrq(String tbrq) {
		this.tbrq = tbrq;
	}

	public String getQsbaksrq() {
		return qsbaksrq;
	}

	public void setQsbaksrq(String qsbaksrq) {
		this.qsbaksrq = qsbaksrq;
	}

	public String getQsbajsrq() {
		return qsbajsrq;
	}

	public void setQsbajsrq(String qsbajsrq) {
		this.qsbajsrq = qsbajsrq;
	}

	public String getQssbksrq() {
		return qssbksrq;
	}

	public void setQssbksrq(String qssbksrq) {
		this.qssbksrq = qssbksrq;
	}

	public String getQssbjsrq() {
		return qssbjsrq;
	}

	public void setQssbjsrq(String qssbjsrq) {
		this.qssbjsrq = qssbjsrq;
	}

	public String getHid_qssbksrq() {
		return hid_qssbksrq;
	}

	public void setHid_qssbksrq(String hid_qssbksrq) {
		this.hid_qssbksrq = hid_qssbksrq;
	}

	public String getHid_qssbjsrq() {
		return hid_qssbjsrq;
	}

	public void setHid_qssbjsrq(String hid_qssbjsrq) {
		this.hid_qssbjsrq = hid_qssbjsrq;
	}

	public String getQsllry() {
		return qsllry;
	}

	public void setQsllry(String qsllry) {
		this.qsllry = qsllry;
	}

	public String getJyqxjm() {
		return jyqxjm;
	}

	public void setJyqxjm(String jyqxjm) {
		this.jyqxjm = jyqxjm;
	}

	public String getGdjyjs() {
		return gdjyjs;
	}

	public void setGdjyjs(String gdjyjs) {
		this.gdjyjs = gdjyjs;
	}

	public String getYfdxgb() {
		return yfdxgb;
	}

	public void setYfdxgb(String yfdxgb) {
		this.yfdxgb = yfdxgb;
	}

	public String getYfxgpc() {
		return yfxgpc;
	}

	public void setYfxgpc(String yfxgpc) {
		this.yfxgpc = yfxgpc;
	}

	public String getYfgdqs() {
		return yfgdqs;
	}

	public void setYfgdqs(String yfgdqs) {
		this.yfgdqs = yfgdqs;
	}

	public String getQtyy() {
		return qtyy;
	}

	public void setQtyy(String qtyy) {
		this.qtyy = qtyy;
	}

	public String getBaShztbs() {
		return baShztbs;
	}

	public void setBaShztbs(String baShztbs) {
		this.baShztbs = baShztbs;
	}

	public String getBaShztMessage() {
		return baShztMessage;
	}

	public void setBaShztMessage(String baShztMessage) {
		this.baShztMessage = baShztMessage;
	}

	public String getBaShtgrq() {
		return baShtgrq;
	}

	public void setBaShtgrq(String baShtgrq) {
		this.baShtgrq = baShtgrq;
	}

	public String getSbShztbs() {
		return sbShztbs;
	}

	public void setSbShztbs(String sbShztbs) {
		this.sbShztbs = sbShztbs;
	}

	public String getSbShztMessage() {
		return sbShztMessage;
	}

	public void setSbShztMessage(String sbShztMessage) {
		this.sbShztMessage = sbShztMessage;
	}

	public String getSbShtgrq() {
		return sbShtgrq;
	}

	public void setSbShtgrq(String sbShtgrq) {
		this.sbShtgrq = sbShtgrq;
	}

	public String getSknd() {
		return sknd;
	}

	public void setSknd(String sknd) {
		this.sknd = sknd;
	}

	public String getCjr() {
		return cjr;
	}

	public void setCjr(String cjr) {
		this.cjr = cjr;
	}

	public String getIsBcsbrq() {
		return isBcsbrq;
	}

	public void setIsBcsbrq(String isBcsbrq) {
		this.isBcsbrq = isBcsbrq;
	}
	
}

package com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰�걨ҳ�������</p>
 * @author ��־��
 * @version 1.1
 */

public class QysdsNewForm
extends BaseForm {
	public QysdsNewForm() {
		try {
			jbInit();
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//Description ���ܷ�Χ�������� addby Lijn
	private String jdlx = "";

	/**
	 * �걨���� String
	 */
	private String sbrq;
	
	/**
	 * ��˰�˼�������� String
	 */
	private String jsjdm;
	
	/**
	 * ��˰������ String
	 */
	private String nsrmc;
	
	/**
	 * ˰����� String
	 */
	private String sknd;
	
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
	 * ˰������ʱ���� String
	 *
	 * ϵͳ�����걨�����Զ�����
	 */
	
	private String skssksrq;
	
	/**
	 * ˰������ʱ��ֹ String
	 *
	 * ϵͳ�����걨�����Զ�����
	 */
	private String skssjsrq;
	
	/**
	 * ������-˰��������ʼ����
	 */
	private String hid_skssksrq;
	
	/**
	 * ������-˰��������������
	 */
	
	private String hid_skssjsrq;
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
	 * �Ǽ�ע�����ʹ��� String
	 */
	private String djzclxdm;
	
	/**
	 * ���շ�ʽ���� String
	 */
	private String zsfsdm;
	
	/**
	 * ��ҵ����˰�걨����
	 */
	private List qysdsnb = new ArrayList();
	
	/**
	 *  �ۺ��걨��ת��
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
     * ��ϵ�绰
     */
    private String lxdh;

    /**
     * ������ҵ
     */
    private String sshy;

    /**
     * ���շ�ʽ
     */
    private String zsfs;

    /**
     * �ƻ��ƶ�
     */
    private String ckzd;

    /**
     * ���ʹ�����ʽ���֡�01������02������03�������ݿ���룩
     */
    private String gzglxs;
    
    /**
     * ���ʹ������ͣ��֡���Ч���͡��ǹ�Ч���������ڱ��룩
     */
    private String gzlx;
    
    /**
     * ��������
     */
    private String jmlx;
    
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
	private List tableList=new ArrayList();
	
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
	 * ������MAP
	 * ��Ź���
	 * 1���ؼ�����N������next����ͷ�ı�ʾ��Ӧ��valueΪ��һ�ű����ӣ��ؼ�����P������previous����ͷ�ı�ʾ��Ӧ��valueΪ��һ�ű�����
	 * 2��N��P֮������ֱ�ʾ��ǰ���ID
	 * 
	 * ����
	 * key��N_2,��ʾ��ǰ���idΪ��2������Ӧ��Ϊ��ǰ�����һ�ű�����
	 */
	private Map linkMap=new HashMap();
	
	/**
	 * ��һ�ű�����
	 */
	private String nextTableURL;
	
	/**
	 * ��һ�ű�����
	 */
	private String previousTableURL;
	
	/**
	 * �Ƿ�Ϊ���һ�ű�
	 * �ǣ�yes
	 * ��no
	 */
	private String isLastTable;
	
	/**
	 * ������
	 */
	private String cyl;
	/**
	 * ��������˰��
	 */
	private String dezsse;
	/**
	 * ��ҵ��˰����
	 */
	private String qyzslx;
	/**
     * ���¼�����ҵ����˰��
     */
    private String sysl = "";
	
	/**
	 * ����Form������
	 */
    
    
	/**
	 *
	 */
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		buffer.append("------QysdsNewForm Properties----"+this.getClass().getName()+"\n");
		buffer.append("���������--"+this.jsjdm+"\n");
		buffer.append("��˰������--"+this.nsrmc+"\n");
		buffer.append("��˰��ʶ���--"+this.nsrsbh+"\n");
		buffer.append("�걨����--"+this.sbrq+"\n");
		buffer.append("˰��������ʼ����--"+this.skssksrq+"\n");
		buffer.append("˰��������������--"+this.skssjsrq+"\n");
		buffer.append("˰�������֯��������--"+this.swjgzzjgdm+"\n");
		buffer.append("˰���������--"+this.swjgzzjgmc+"\n");
		buffer.append("˰����������--"+this.swjsjdm+"\n");
		buffer.append("�ƻ��ƶ�--"+this.ckzd+"\n");
		buffer.append("��������--"+this.jmlx+"\n");
		buffer.append("���ʹ�����ʽ--"+this.gzglxs+"\n");
		buffer.append("������--"+this.cyl+"\n");
		buffer.append("��������˰��--"+this.dezsse+"\n");
		buffer.append("������ҵ--"+this.sshy+"\n");
		buffer.append("������������--"+this.ssjjlx+"\n");
		buffer.append("��Ӫ��ַ--"+this.jydz+"\n");
		buffer.append("�ں�--"+this.qh+"\n");
		buffer.append("����������--"+this.bbqlx+"\n");
		buffer.append("��Ӧ˰��--"+this.sysl+"\n");
		buffer.append("-------QysdsNewForm Properties----"+"\n");
		return buffer.toString();
	}
	

	/**
	 * @description: getter
	 * @return the jdlx
	 */
	public String getJdlx() {
		return jdlx;
	}


	/**
	 * @description: setter
	 * @param jdlx the jdlx to set
	 */
	public void setJdlx(String jdlx) {
		this.jdlx = jdlx;
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
	
	public String getDjzclxdm() {
		return this.djzclxdm;
	}
	
	public void setDjzclxdm(String _djzclxdm) {
		this.djzclxdm = _djzclxdm;
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
	
	public String getSbrq() {
		return this.sbrq;
	}
	
	public void setSbrq(String rq) {
		this.sbrq = rq;
	}
	
	public List getDataList() {
		return this.qysdsnb;
	}
	
	public void setDataList(List qysdsnb) {
		this.qysdsnb = qysdsnb;
	}
	
	public String getSknd() {
		return sknd;
	}
	
	public void setSknd(String _sknd) {
		sknd = _sknd;
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
	
	public String getSkssksrq() {
		return skssksrq;
	}
	
	public void setSkssksrq(String _skssksrq) {
		skssksrq = _skssksrq;
	}
	
	public String getSkssjsrq() {
		return skssjsrq;
	}
	
	public void setSkssjsrq(String _skssjsrq) {
		skssjsrq = _skssjsrq;
	}
	
	public String getZsfsdm() {
		//return com.ttsoft.bjtax.smsb.constant.CodeConstant.ZSFSDM_CYLZS;
		return this.zsfsdm;
	}
	
	public void setZsfsdm(String _zsfsdm) {
		this.zsfsdm = _zsfsdm;
	}
	
	/**
	 * ҳ��Ҫ�����
	 * @param actionMapping struts.action.ActionMapping
	 * @param httpServletRequest HttpServletRequest
	 */
	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		
		jsjdm=null;
		nsrsbh=null;
		nsrmc=null;
		sknd=null;
		lrr=null;
		lrrq=null;
		cjsj=null;
		swjgzzjgdm=null;
		zgswjgzzjgdm=null;
		zgswjgzzjgmc=null;
		swjsjdm=null;
		swjgzzjgmc=null;
		djzclxdm=null;
		zsfsdm=null;
		qxdm=null;
		ssjjlx=null;
		lxdh=null;
		sshy=null;
		zsfs=null;
		ckzd=null;
		gzglxs=null;
		jmlx=null;
		bbqlx=null;
		qh=null;
		jydz=null;
		linkUrlHTML=null;
		cyl=null;
		dezsse=null;
		qyzslx=null;
		sysl=null;
		jdlx="";
		this.getTableList().clear();
		//this.getDataList().clear();
	}
	
	private void jbInit() throws Exception {
	}

	public String getBbqlx() {
		return bbqlx;
	}

	public void setBbqlx(String bbqlx) {
		this.bbqlx = bbqlx;
	}

	public String getCkzd() {
		return ckzd;
	}

	public void setCkzd(String ckzd) {
		this.ckzd = ckzd;
	}

	public String getGzglxs() {
		return gzglxs;
	}

	public void setGzglxs(String gzglxs) {
		this.gzglxs = gzglxs;
	}

	public String getJmlx() {
		return jmlx;
	}

	public void setJmlx(String jmlx) {
		this.jmlx = jmlx;
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

	public String getZsfs() {
		return zsfs;
	}

	public void setZsfs(String zsfs) {
		this.zsfs = zsfs;
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

	public String getGzlx() {
		return gzlx;
	}

	public void setGzlx(String gzlx) {
		this.gzlx = gzlx;
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

	public List getQysdsnb() {
		return qysdsnb;
	}

	public void setQysdsnb(List qysdsnb) {
		this.qysdsnb = qysdsnb;
	}

	public String getSysl() {
		return sysl;
	}

	public void setSysl(String sysl) {
		this.sysl = sysl;
	}

	public String getHid_skssjsrq() {
		return hid_skssjsrq;
	}

	public void setHid_skssjsrq(String hid_skssjsrq) {
		this.hid_skssjsrq = hid_skssjsrq;
	}

	public String getHid_skssksrq() {
		return hid_skssksrq;
	}

	public void setHid_skssksrq(String hid_skssksrq) {
		this.hid_skssksrq = hid_skssksrq;
	}



}


/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web;

import java.util.*;

import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel;
import com.ttsoft.framework.form.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Form��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class GdzysSydjxxlrForm extends BaseForm {
    public GdzysSydjxxlrForm() {
    }
    
    //��ӡʱ��
    private int nian;
    private int yue;
    private int tian;
    
    private List dylist;
    /*---------------------------------------��ӱ�ע----------------------------------*/
//    private String bzms;
//    private String bzlxdm;
    /*---------------------------------------ȷ������----------------------------------*/
    private String[] sbbxlh_ary;
    /*---------------------------------------��ʼ����----------------------------------*/
  //˰Դ����list
	private List sylx_list;
	//ռ����;list
	private List zdyt_list;
	//����˰��list
	private List syse_list;
	//�������list
	private List jmlb_list;
    /*---------------------------------------�걨��Ϣ-----------------------------------*/
	//������ҵ
	private List sshy_list;
	//�Ǽ�ע������
	private List djzclx_list;
	//����˰�����
	private List nd_list;
	//����˰�����
	private String sysend;
  //�걨���к�
    private String sbbxlh;
    //˰Դ���ʹ���
    private String sylxdm;
    //��˰������
    private String nsrlx;
    //��˰������
    private String nsrmc;
    //���������
    private String jsjdm;
   //��˰��ʶ���
    private String nsrsbh;
    //��˰��������ҵ����
    private String nsrsshy;
    private String nsrsshymc;
    //���֤������
    private String sfzzlxdm;
    private String sfzzlxmc;
    //���֤�պ���
    private String sfzzhm;
  
	//��˰����ϸ��ַ
    private String nsrxxdz;
    //��ҵ�Ǽ�ע������--����
    private String qydjzclx;
    private String qydjzclxmc;
    //�������д���
    private String khyh;
    //�����ʺ�
    private String yhzh;
    //��ϵ������
    private String lxrxm;
    //��ϵ�绰
    private String lxdh;
    //�Ƿ��о�����
    private String sfsjsp;
    /*-----------------------------------������Ϣ------------------------------------*/
    //���������ַ
    private String tdzldz;
    //��׼ռ���ĺ�
    private String pzjdwh;
    //��׼ռ�����
    private String pzjdmj;
    //������Ŀ����
    private String jsxmmc;
    //ʵ��ռ�����
    private String sjzdmj;
    //ռ��ʱ��
    private String zdsj;
    //�����걨����
    private String jmsyj;
    /*-------------------------------------������Ϣ------------------------------------*/
    //����˰���ݴ���
    private String jmsyjdm;
    
    //��˰���
    private String hj_jsmj;
    //����˰��
    private String hj_jzse;
    //�������
    private String hj_jmmj;
    //����˰��
    private String hj_jmse;
    //Ӧ˰���
    private String hj_ysmj;
    //Ӧ��˰��
    private String hj_ynse;
    //������
    private String cjr;
    //����ʱ��
    private String cjsj;
    //ȷ����
    private String qrr;
    //ȷ��ʱ��
    private String qrrq;
    //ȷ��״̬
    private String qrzt;
    //ȷ����
    private String sjqrr;
    //ȷ��ʱ��
    private String sjqrrq;
    //ȷ��״̬
    private String sjqrzt;
    //¼����
    private String lrr;
    //¼��ʱ��
    private Date lrsj;
    //��ע���ʹ���
    private String bzlxdm;
    //��ע����
    private String bzms;
    
    /*------------------------------------------�걨��ϸ---------------------------------------*/
    
    private String[] zdyt;
    //����˰��
    private String[] syse;
    //��˰���
    private String[] jsmj;
    //����˰��
    private String[] jzse;
    //�������
    private String[] jmmj;
    //����˰��
    private String[] jmse;
    //����˰��
    private String[] ysmj;
    //Ӧ��˰��
    private String[] ynse;
    
    //ռ����;����
    private List zdytbean;
    private String[] zdyt_sbmx;
    //����˰��
    private String[] syse_sbmx;
    //��˰���
    private String[] jsmj_sbmx;
    //����˰��
    private String[] jzse_sbmx;
    //�������
    private String[] jmmj_sbmx;
    //����˰��
    private String[] jmse_sbmx;
    //Ӧ��˰��
    private String[] ynse_sbmx;
    
    
    /*-------------------------------------------������ϸ--------------------------------------*/
    
    private JMXXModel jmxxModel;
    //����������
    private String[] jmlbdm_jmmx;
    //�������
    private String[] jmmj_jmmx;
    //����˰��
    private String[] jmse_jmmx; 
    //����������
    private String[] fljmmj;
    //�������˰��
    private String[] fljmse;
    
    private List zdytmc;
    private List zdytdm;
    
    private List jmlbdm;
    private List jmlbmc;
    private Map sbmx_map;
	private Map jmmx_map;
    private String shzt;
    private String sjqrsj;
    private String bzdm;
    private String bznr;
    private String yhdm;
    private String errors;
    private String flag;
    private String whnd;
    private String wh;
    private List sbmxlist;
    private String bg_flag;
    private String jks_flag;
    private String jms_flag;
    private String zwbs_flag;
    private String sh_flag;

    
    
    
    
	public String getSh_flag() {
		return sh_flag;
	}
	public void setSh_flag(String sh_flag) {
		this.sh_flag = sh_flag;
	}
	public String getJks_flag() {
		return jks_flag;
	}
	public void setJks_flag(String jks_flag) {
		this.jks_flag = jks_flag;
	}
	public String getJms_flag() {
		return jms_flag;
	}
	public void setJms_flag(String jms_flag) {
		this.jms_flag = jms_flag;
	}
	public String getZwbs_flag() {
		return zwbs_flag;
	}
	public void setZwbs_flag(String zwbs_flag) {
		this.zwbs_flag = zwbs_flag;
	}
	public String getBg_flag() {
		return bg_flag;
	}
	public void setBg_flag(String bg_flag) {
		this.bg_flag = bg_flag;
	}
	public List getSbmxlist() {
		return sbmxlist;
	}
	public void setSbmxlist(List sbmxlist) {
		this.sbmxlist = sbmxlist;
	}
	public String getWhnd() {
		return whnd;
	}
	public void setWhnd(String whnd) {
		this.whnd = whnd;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getErrors() {
		return errors;
	}
	public void setErrors(String errors) {
		this.errors = errors;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public String getBzdm() {
		return bzdm;
	}
	public void setBzdm(String bzdm) {
		this.bzdm = bzdm;
	}
	public String getBznr() {
		return bznr;
	}
	public void setBznr(String bznr) {
		this.bznr = bznr;
	}
	public String getSjqrsj() {
		return sjqrsj;
	}
	public void setSjqrsj(String sjqrsj) {
		this.sjqrsj = sjqrsj;
	}
	public String getShzt() {
		return shzt;
	}
	public void setShzt(String shzt) {
		this.shzt = shzt;
	}
	public Map getJmmx_map() {
		return jmmx_map;
	}
	public void setJmmx_map(Map jmmx_map) {
		this.jmmx_map = jmmx_map;
	}
	public int getNian() {
		return nian;
	}
	public void setNian(int nian) {
		this.nian = nian;
	}
	public int getYue() {
		return yue;
	}
	public void setYue(int yue) {
		this.yue = yue;
	}
	public int getTian() {
		return tian;
	}
	public void setTian(int tian) {
		this.tian = tian;
	}
	public Map getSbmx_map() {
		return sbmx_map;
	}
	public void setSbmx_map(Map sbmx_map) {
		this.sbmx_map = sbmx_map;
	}
	public List getJmlbdm() {
		return jmlbdm;
	}
	public void setJmlbdm(List jmlbdm) {
		this.jmlbdm = jmlbdm;
	}
	public List getJmlbmc() {
		return jmlbmc;
	}
	public void setJmlbmc(List jmlbmc) {
		this.jmlbmc = jmlbmc;
	}
	public List getZdytmc() {
		return zdytmc;
	}
	public void setZdytmc(List zdytmc) {
		this.zdytmc = zdytmc;
	}
	public List getZdytdm() {
		return zdytdm;
	}
	public void setZdytdm(List zdytdm) {
		this.zdytdm = zdytdm;
	}
	public String getSfzzlxmc() {
		return sfzzlxmc;
	}
	public void setSfzzlxmc(String sfzzlxhm) {
		this.sfzzlxmc = sfzzlxhm;
	}
    public List getDylist() {
		return dylist;
	}
	public String getQydjzclxmc() {
		return qydjzclxmc;
	}
	public void setQydjzclxmc(String qydjzclxmc) {
		this.qydjzclxmc = qydjzclxmc;
	}
	public String getNsrsshymc() {
		return nsrsshymc;
	}
	public void setNsrsshymc(String nsrsshymc) {
		this.nsrsshymc = nsrsshymc;
	}
	public void setDylist(List dylist) {
		this.dylist = dylist;
	}
	public String[] getYsmj() {
		return ysmj;
	}
	public void setYsmj(String[] ysmj) {
		this.ysmj = ysmj;
	}
	public String[] getSbbxlh_ary() {
		return sbbxlh_ary;
	}
	public void setSbbxlh_ary(String[] sbbxlh_ary) {
		this.sbbxlh_ary = sbbxlh_ary;
	}
	public List getSylx_list() {
		return sylx_list;
	}
	public void setSylx_list(List sylx_list) {
		this.sylx_list = sylx_list;
	}
	public List getZdyt_list() {
		return zdyt_list;
	}
	public void setZdyt_list(List zdyt_list) {
		this.zdyt_list = zdyt_list;
	}
	public List getSyse_list() {
		return syse_list;
	}
	public void setSyse_list(List syse_list) {
		this.syse_list = syse_list;
	}
	public List getJmlb_list() {
		return jmlb_list;
	}
	public void setJmlb_list(List jmlb_list) {
		this.jmlb_list = jmlb_list;
	}
	public List getSshy_list() {
		return sshy_list;
	}
	public void setSshy_list(List sshy_list) {
		this.sshy_list = sshy_list;
	}
	public List getDjzclx_list() {
		return djzclx_list;
	}
	public void setDjzclx_list(List djzclx_list) {
		this.djzclx_list = djzclx_list;
	}
	public List getNd_list() {
		return nd_list;
	}
	public void setNd_list(List nd_list) {
		this.nd_list = nd_list;
	}
	public String getSysend() {
		return sysend;
	}
	public void setSysend(String sysend) {
		this.sysend = sysend;
	}
	public String getSbbxlh() {
		return sbbxlh;
	}
	public void setSbbxlh(String sbbxlh) {
		this.sbbxlh = sbbxlh;
	}
	public String getSylxdm() {
		return sylxdm;
	}
	public void setSylxdm(String sylxdm) {
		this.sylxdm = sylxdm;
	}
	public String getNsrlx() {
		return nsrlx;
	}
	public void setNsrlx(String nsrlx) {
		this.nsrlx = nsrlx;
	}
	public String getNsrmc() {
		return nsrmc;
	}
	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}
	public String getJsjdm() {
		return jsjdm;
	}
	public void setJsjdm(String jsjdm) {
		this.jsjdm = jsjdm;
	}
	public String getNsrsbh() {
		return nsrsbh;
	}
	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}
	public String getNsrsshy() {
		return nsrsshy;
	}
	public void setNsrsshy(String nsrsshy) {
		this.nsrsshy = nsrsshy;
	}
	public String getSfzzlxdm() {
		return sfzzlxdm;
	}
	public void setSfzzlxdm(String sfzzlxdm) {
		this.sfzzlxdm = sfzzlxdm;
	}
	public String getSfzzhm() {
		return sfzzhm;
	}
	public void setSfzzhm(String sfzzhm) {
		this.sfzzhm = sfzzhm;
	}
	public String getNsrxxdz() {
		return nsrxxdz;
	}
	public void setNsrxxdz(String nsrxxdz) {
		this.nsrxxdz = nsrxxdz;
	}
	public String getQydjzclx() {
		return qydjzclx;
	}
	public void setQydjzclx(String qydjzclx) {
		this.qydjzclx = qydjzclx;
	}
	public String getKhyh() {
		return khyh;
	}
	public void setKhyh(String khyh) {
		this.khyh = khyh;
	}
	public String getYhzh() {
		return yhzh;
	}
	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}
	public String getLxrxm() {
		return lxrxm;
	}
	public void setLxrxm(String lxrxm) {
		this.lxrxm = lxrxm;
	}
	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getSfsjsp() {
		return sfsjsp;
	}
	public void setSfsjsp(String sfsjsp) {
		this.sfsjsp = sfsjsp;
	}
	public String getTdzldz() {
		return tdzldz;
	}
	public void setTdzldz(String tdzldz) {
		this.tdzldz = tdzldz;
	}
	public String getPzjdwh() {
		return pzjdwh;
	}
	public void setPzjdwh(String pzjdwh) {
		this.pzjdwh = pzjdwh;
	}
	public String getPzjdmj() {
		return pzjdmj;
	}
	public void setPzjdmj(String pzjdmj) {
		this.pzjdmj = pzjdmj;
	}
	public String getJsxmmc() {
		return jsxmmc;
	}
	public void setJsxmmc(String jsxmmc) {
		this.jsxmmc = jsxmmc;
	}
	public String getSjzdmj() {
		return sjzdmj;
	}
	public void setSjzdmj(String sjzdmj) {
		this.sjzdmj = sjzdmj;
	}
	public String getZdsj() {
		return zdsj;
	}
	public void setZdsj(String zdsj) {
		this.zdsj = zdsj;
	}
	public String getJmsyj() {
		return jmsyj;
	}
	public void setJmsyj(String jmsyj) {
		this.jmsyj = jmsyj;
	}
	public String getJmsyjdm() {
		return jmsyjdm;
	}
	public void setJmsyjdm(String jmsyjdm) {
		this.jmsyjdm = jmsyjdm;
	}
	
	public String getHj_jsmj() {
		return hj_jsmj;
	}
	public void setHj_jsmj(String hj_jsmj) {
		this.hj_jsmj = hj_jsmj;
	}
	public String getHj_jzse() {
		return hj_jzse;
	}
	public void setHj_jzse(String hj_jzse) {
		this.hj_jzse = hj_jzse;
	}
	public String getHj_jmmj() {
		return hj_jmmj;
	}
	public void setHj_jmmj(String hj_jmmj) {
		this.hj_jmmj = hj_jmmj;
	}
	public String getHj_jmse() {
		return hj_jmse;
	}
	public void setHj_jmse(String hj_jmse) {
		this.hj_jmse = hj_jmse;
	}
	public String getHj_ysmj() {
		return hj_ysmj;
	}
	public void setHj_ysmj(String hj_ysmj) {
		this.hj_ysmj = hj_ysmj;
	}
	public String getHj_ynse() {
		return hj_ynse;
	}
	public void setHj_ynse(String hj_ynse) {
		this.hj_ynse = hj_ynse;
	}
	public String getCjr() {
		return cjr;
	}
	public void setCjr(String cjr) {
		this.cjr = cjr;
	}
	public String getCjsj() {
		return cjsj;
	}
	public void setCjsj(String cjsj) {
		this.cjsj = cjsj;
	}
	public String getQrr() {
		return qrr;
	}
	public void setQrr(String qrr) {
		this.qrr = qrr;
	}
	
	public String getQrrq() {
		return qrrq;
	}
	public void setQrrq(String qrrq) {
		this.qrrq = qrrq;
	}
	public String getSjqrr() {
		return sjqrr;
	}
	public void setSjqrr(String sjqrr) {
		this.sjqrr = sjqrr;
	}
	public String getSjqrrq() {
		return sjqrrq;
	}
	public void setSjqrrq(String sjqrrq) {
		this.sjqrrq = sjqrrq;
	}
	public String getSjqrzt() {
		return sjqrzt;
	}
	public void setSjqrzt(String sjqrzt) {
		this.sjqrzt = sjqrzt;
	}
	public String getQrzt() {
		return qrzt;
	}
	public void setQrzt(String qrzt) {
		this.qrzt = qrzt;
	}
	public String getLrr() {
		return lrr;
	}
	public void setLrr(String lrr) {
		this.lrr = lrr;
	}
	public Date getLrsj() {
		return lrsj;
	}
	public void setLrsj(Date lrsj) {
		this.lrsj = lrsj;
	}
	public String getBzlxdm() {
		return bzlxdm;
	}
	public void setBzlxdm(String bzlxdm) {
		this.bzlxdm = bzlxdm;
	}
	public String getBzms() {
		return bzms;
	}
	public void setBzms(String bzms) {
		this.bzms = bzms;
	}
	public String[] getZdyt() {
		return zdyt;
	}
	public void setZdyt(String[] zdyt) {
		this.zdyt = zdyt;
	}
	public String[] getSyse() {
		return syse;
	}
	public void setSyse(String[] syse) {
		this.syse = syse;
	}
	public String[] getJsmj() {
		return jsmj;
	}
	public void setJsmj(String[] jsmj) {
		this.jsmj = jsmj;
	}
	public String[] getJzse() {
		return jzse;
	}
	public void setJzse(String[] jzse) {
		this.jzse = jzse;
	}
	public String[] getJmmj() {
		return jmmj;
	}
	public void setJmmj(String[] jmmj) {
		this.jmmj = jmmj;
	}
	public String[] getJmse() {
		return jmse;
	}
	public void setJmse(String[] jmse) {
		this.jmse = jmse;
	}
	public String[] getYnse() {
		return ynse;
	}
	public void setYnse(String[] ynse) {
		this.ynse = ynse;
	}
	public List getZdytbean() {
		return zdytbean;
	}
	public void setZdytbean(List zdytbean) {
		this.zdytbean = zdytbean;
	}
	public String[] getZdyt_sbmx() {
		return zdyt_sbmx;
	}
	public void setZdyt_sbmx(String[] zdyt_sbmx) {
		this.zdyt_sbmx = zdyt_sbmx;
	}
	public String[] getSyse_sbmx() {
		return syse_sbmx;
	}
	public void setSyse_sbmx(String[] syse_sbmx) {
		this.syse_sbmx = syse_sbmx;
	}
	public String[] getJsmj_sbmx() {
		return jsmj_sbmx;
	}
	public void setJsmj_sbmx(String[] jsmj_sbmx) {
		this.jsmj_sbmx = jsmj_sbmx;
	}
	public String[] getJzse_sbmx() {
		return jzse_sbmx;
	}
	public void setJzse_sbmx(String[] jzse_sbmx) {
		this.jzse_sbmx = jzse_sbmx;
	}
	public String[] getJmmj_sbmx() {
		return jmmj_sbmx;
	}
	public void setJmmj_sbmx(String[] jmmj_sbmx) {
		this.jmmj_sbmx = jmmj_sbmx;
	}
	public String[] getJmse_sbmx() {
		return jmse_sbmx;
	}
	public void setJmse_sbmx(String[] jmse_sbmx) {
		this.jmse_sbmx = jmse_sbmx;
	}
	public String[] getYnse_sbmx() {
		return ynse_sbmx;
	}
	public void setYnse_sbmx(String[] ynse_sbmx) {
		this.ynse_sbmx = ynse_sbmx;
	}
	public JMXXModel getJmxxModel() {
		return jmxxModel;
	}
	public void setJmxxModel(JMXXModel jmxxModel) {
		this.jmxxModel = jmxxModel;
	}
	public String[] getJmlbdm_jmmx() {
		return jmlbdm_jmmx;
	}
	public void setJmlbdm_jmmx(String[] jmlbdm_jmmx) {
		this.jmlbdm_jmmx = jmlbdm_jmmx;
	}
	public String[] getJmmj_jmmx() {
		return jmmj_jmmx;
	}
	public void setJmmj_jmmx(String[] jmmj_jmmx) {
		this.jmmj_jmmx = jmmj_jmmx;
	}
	public String[] getJmse_jmmx() {
		return jmse_jmmx;
	}
	public void setJmse_jmmx(String[] jmse_jmmx) {
		this.jmse_jmmx = jmse_jmmx;
	}
	public String[] getFljmmj() {
		return fljmmj;
	}
	public void setFljmmj(String[] fljmmj) {
		this.fljmmj = fljmmj;
	}
	public String[] getFljmse() {
		return fljmse;
	}
	public void setFljmse(String[] fljmse) {
		this.fljmse = fljmse;
	}
    
    
  
    
    
    
   
   
   
}

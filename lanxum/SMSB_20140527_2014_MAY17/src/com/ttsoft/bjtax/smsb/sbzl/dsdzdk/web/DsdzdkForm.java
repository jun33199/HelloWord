/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.dsdzdk.web;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �����걨</p>
 * @author Shi Yanfeng
 * @version 1.1
 */


public class DsdzdkForm
    extends BaseForm
{
    /**
     * ˰�ִ��롢˰�����ơ�˰��˰Ŀ���롢˰��˰Ŀ���ơ���˰����˰��������˰�ʡ�ʵ��˰���ע���걨���ܵ��š�Ʊ֤������롢�����������ơ���˰֤��
     *
     * ��ϸ��Ϣ��ʾ String[]
     */
    private String columns[] =
                               {
                               "szdm", "szmc", "szsmdm", "szsmmc", "sl",
                               "jsje", "sjse", "pzzldm", "bdzrmc", "wszh"};

    /**
     *  �ϴ����ļ� excelFile
     */
    private FormFile excelFile;

    /**
     * �걨��� String
     */
    private String nd;

    /**
     * ˰���������� String
     *
     * ��¼��������ȡ��
     */
    private String jsjdm; //���������

    /**
     * ��˰������ String
     *
     * ���������˰�����������ѯ�õ�
     */
    private String nsrmc; //��˰��λ����

    /**
     * ����ʱ�� String
     */
    private String cjsj; //����ʱ��

    /**
     * �Ǽ��걨��ʽ���� String
     *
     * �ӵ�¼������ȡ��
     */
    private String fsdm; //�Ǽ��걨��ʽ����

    /**
     * ¼���˴��� String
     *
     * �ӵ�¼������ȡ��
     */
    private String lrr; //¼����

    /**
     * ¼������ String
     */
    private String lrrq; //¼������

    /**
     * ��ҵ��ϵ�绰 String
     *
     * ���ݼ���������ѯ�õ�
     */
    private String qylxdh; //��ҵ��ϵ�绰

    /**
     * ˰�������������� String
     *
     * ϵͳ�����걨�����Զ�����
     */
    private String skssjsrq; //¼���������

    /**
     * ˰��������ʼ���� String
     *
     * ϵͳ�����걨�����Զ�����
     */
    private String skssksrq; //¼�뿪ʼ����

    /**
     * ˰�������֯�������� String
     *
     * �ӵ�¼������ȡ��
     */
    private String swjgzzjgdm; //˰�������֯��������

    /**
     * �Ǽ�ע�����ʹ��� String
     *
     * ���ݼ���������ѯ�õ�
     */
    private String djzclxdm; //�Ǽ�ע�����ʹ���

    /**
     * ���ұ�׼��ҵ���� String
     *
     * ���ݼ���������ѯ�õ�
     */
    private String gjbzhydm; //���ұ�׼��ҵ����

    /**
     * ������ϵ���� String
     *
     * ���ݼ���������ѯ�õ�
     */
    private String lsgxdm; //������ϵ����

    /**
     * �걨��� String
     */
    private String sbbh; //�걨���

    /**
     * �걨���ܱ�� String
     */
    private String sbhzdh; //�걨���ܱ��

    /**
     * �������� String
     */
    private String hzrq; //��������

    /**
     * ���ܿ�ʼ���� String
     *
     * ϵͳ���ݻ��������Զ�����
     */
    private String hzksrq; // ���ܿ�ʼ����

    /**
     * ���ܽ������� String
     *
     * ϵͳ���ݻ��������Զ�����
     */
    private String hzjsrq; // ���ܽ�������

    /**
     * �����־���� String
     */
    private String clbjdm; // �����־����

    /**
     * ������Դ String
     */
    private String sjly; //������Դ

    /**
     * ���д��� String
     */
    private String yhdm; // ���д���

    /**
     * �������� String
     */
    private String yhmc; // ��������

    /**
     * �����ʺ� String
     */
    private String zh; // �����ʺ�

    /**
     * �걨���� String
     */
    private String sbrq; //�걨����

    /**
     * ҳ�������˿����ʾ���� String
     */
    private String scriptStr;

    /**
     * Ʊ֤������� String
     */
    private String pzzl; //Ʊ֤�������

    /**
     * ��ҳ���� String
     */
    //��ҳ��
    private int length;

    /**
     * ��ǰҳ int
     */
    private int pgNum;

    /**
     * ��ҳ int
     */
    private int pgSum;

    private String iszhsb;

    /**
     * ���ش���
     */
    private String qxdm;

    public String getQxdm ()
    {
        return this.qxdm;
    }

    public void setQxdm (String _qxdm)
    {
        this.qxdm = _qxdm;
    }

    public String getIszhsb ()
    {
        return this.iszhsb;
    }

    public void setIszhsb (String _iszhsb)
    {
        this.iszhsb = _iszhsb;
    }

    public String getSjly ()
    {
        return this.sjly;
    }

    public void setSjly (String _sjly)
    {
        this.sjly = _sjly;
    }

    /**
     * ��˰���ϼ� BigDecimal
     */
    //��˰���ϼ�
    private BigDecimal jsjehj = new BigDecimal(0.00);

    /**
     * ʵ��˰��ϼ� BigDecimal
     */
    //ʵ��˰��ϼ�
    private BigDecimal sjsehj = new BigDecimal(0.00);

    /**
     * ¼����ϸ List
     */
    //¼����ϸ
    private List dataList = new ArrayList();

    /**
     * ������ϸ List
     */
    //������ϸ
    private List sdmxDataList = new ArrayList();

    /**
     * ���ܵ� List
     */
    //���ܵ�list
    private List hzdList = new ArrayList();

    /**
     * ���ܵ��еĽɿ��� List
     */
    //���ܵ��еĽɿ���list
    private List jkpzList = new ArrayList();

    private String headJkpzh;

    private String headJsjdm;

    private String nsrzt;

    public String getNd ()
    {
        return nd;
    }

    public void setNd (String _nd)
    {
        nd = _nd;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public void setHzdDataList (List _hzdList)
    {
        this.hzdList = _hzdList;
    }

    public List getHzdDataList ()
    {
        return this.hzdList;
    }

    public List getJkpzDataList ()
    {
        return this.jkpzList;
    }

    public void setJkpzDataList (List _jkpzList)
    {
        this.jkpzList = _jkpzList;
    }

    public List getMxDataList ()
    {
        return this.sdmxDataList;
    }

    public void setMxDataList (List _mxList)
    {
        this.sdmxDataList = _mxList;
    }

    public BigDecimal getJsjehj ()
    {
        return this.jsjehj;
    }

    public void setJsjehj (BigDecimal _jsjehj)
    {
        this.jsjehj = _jsjehj;
    }

    public BigDecimal getSjsehj ()
    {
        return this.sjsehj;
    }

    public void setSjsehj (BigDecimal _sjsehj)
    {
        this.sjsehj = _sjsehj;
    }

    public FormFile getExcelFile ()
    {
        return excelFile;
    }

    public void setExcelFile (FormFile _excelFile)
    {
        this.excelFile = _excelFile;
    }

    public String getSbrq ()
    {
        return this.sbrq;
    }

    public void setSbrq (String _sbrq)
    {
        this.sbrq = _sbrq;
    }

    public String getLsgxdm ()
    {
        return this.lsgxdm;
    }

    public void setLsgxdm (String _lsgxdm)
    {
        this.lsgxdm = _lsgxdm;
    }

    public String getGjbzhydm ()
    {
        return this.gjbzhydm;
    }

    public void setGjbzhydm (String _gjbzhydm)
    {
        this.gjbzhydm = _gjbzhydm;
    }

    public String getDjzclxdm ()
    {
        return this.djzclxdm;
    }

    public void setDjzclxdm (String _djzclxdm)
    {
        this.djzclxdm = _djzclxdm;
    }

    public String getYhdm ()
    {
        return this.yhdm;
    }

    public void setYhdm (String _yhdm)
    {
        this.yhdm = _yhdm;
    }

    public String getYhmc ()
    {
        return this.yhmc;
    }

    public void setYhmc (String _yhmc)
    {
        this.yhmc = _yhmc;
    }

    public String getZh ()
    {
        return this.zh;
    }

    public void setZh (String _zh)
    {
        this.zh = _zh;
    }

    public String getSbbh ()
    {
        return this.sbbh;
    }

    public void setSbbh (String _sbbh)
    {
        this.sbbh = _sbbh;
    }

    public String getSbhzdh ()
    {
        return this.sbhzdh;
    }

    public void setSbhzdh (String _sbhzdh)
    {
        this.sbhzdh = _sbhzdh;
    }

    public String getHzrq ()
    {
        return this.hzrq;
    }

    public void setHzrq (String _hzrq)
    {
        this.hzrq = _hzrq;
    }

    public String getHzksrq ()
    {
        return getSkssksrq();
    }

    public void setHzksrq (String _hzksrq)
    {
        this.hzksrq = _hzksrq;
    }

    public String getHzjsrq ()
    {
        return getSkssjsrq();
    }

    public void setHzjsrq (String _hzjsrq)
    {
        this.hzjsrq = _hzjsrq;
    }

    public String getClbjdm ()
    {
        return this.clbjdm;
    }

    public void setClbjdm (String _clbjdm)
    {
        this.clbjdm = _clbjdm;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getCjrq ()
    {
        return cjsj;
    }

    public void setCjrq (String cjsj)
    {
        this.cjsj = cjsj;
    }

    public String getFsdm ()
    {
        return fsdm;
    }

    public void setFsdm (String fsdm)
    {
        this.fsdm = fsdm;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public String getLrr ()
    {
        return lrr;
    }

    public void setLrr (String lrr)
    {
        this.lrr = lrr;
    }

    public String getLrrq ()
    {
        return lrrq;
    }

    public void setLrrq (String lrrq)
    {
        this.lrrq = lrrq;
    }

    public String getQylxdh ()
    {
        return qylxdh;
    }

    public void setQylxdh (String qylxdh)
    {
        this.qylxdh = qylxdh;
    }

    public String getSkssjsrq ()
    {
        return skssjsrq;
    }

    public void setSkssjsrq (String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public String getSkssksrq ()
    {
        return skssksrq;
    }

    public void setSkssksrq (String skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public String getSwjgzzjgdm ()
    {
        return swjgzzjgdm;
    }

    public void setSwjgzzjgdm (String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }

    public void setScriptStr (String scriptStr)
    {
        this.scriptStr = scriptStr;
    }

    public String getScriptStr ()
    {
        return scriptStr;
    }

    public String getPzzl ()
    {
        return pzzl;
    }

    public void setPzzl (String pzzl)
    {
        this.pzzl = pzzl;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {

        return null;
    }

    /**
     * ҳ��Ҫ���������
     * @param actionMapping struts.action.ActionMapping
     * @param httpServletRequest HttpServletRequest
     */

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.dataList.clear();
        pgNum = 0;
        pgSum = 0;
        length = 0;
        this.nsrmc = null;
        this.jsjdm = null;
        this.qylxdh = null;
        this.nsrzt = CodeConstant.NSRZT_ZC;
    }

    public int getPgNum ()
    {
        return pgNum;
    }

    public int getPgSum ()
    {
        return pgSum;
    }

    public void setPgNum (int pgNum)
    {
        this.pgNum = pgNum;
    }

    public void setPgSum (int pgSum)
    {
        this.pgSum = pgSum;
    }

    public int getLength ()
    {
        return length;
    }

    public void setLength (int length)
    {
        this.length = length;
    }

    public String getHeadJkpzh ()
    {
        return headJkpzh;
    }

    public void setHeadJkpzh (String headJkpzh)
    {
        this.headJkpzh = headJkpzh;
    }

    public String getHeadJsjdm ()
    {
        return headJsjdm;
    }

    public void setHeadJsjdm (String headJsjdm)
    {
        this.headJsjdm = headJsjdm;
    }

    public String getNsrzt ()
    {
        return nsrzt;
    }

    public void setNsrzt (String nsrzt)
    {
        this.nsrzt = nsrzt;
    }

}
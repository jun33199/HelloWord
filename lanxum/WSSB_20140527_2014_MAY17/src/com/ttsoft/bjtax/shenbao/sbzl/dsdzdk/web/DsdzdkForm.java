package com.ttsoft.bjtax.shenbao.sbzl.dsdzdk.web;

import java.sql.*;
import java.util.*;

import org.apache.struts.upload.*;
import com.ttsoft.framework.form.*;
import java.math.BigDecimal;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description:�������۴���Form </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-7
 */

public class DsdzdkForm extends SbzlBaseForm
{
    public DsdzdkForm()
    {
    }

    //����jsp��ʶ
    public final static String OPTJSP_DR = "DR";
    public final static String OPTJSP_HZ = "HZ";
    public final static String OPTJSP_CX = "CX";

    public final static int NOERR     = 0;
    public final static int HASERR    = 1;

    //ÿҳ��ʾ����ϸ��Ŀ
    public final static int MAXNUMBEREACHPAGE = 10;
    //��ҳ��ĸ�ҳlist��list
    private List splitPageList = new ArrayList();
    //��ǰ��ʾ��ҳ����ϸList
    private List dsdzdkmxItemList = new ArrayList();
    //����ҳ�浱ǰҳ���� Ĭ��Ϊ0
    private int pageindex = 0;
    //����ҳ������ҳ���� Ĭ��Ϊ0
    private int maxpageindex = 0;
    //����ҳ�浱ǰҳ���� Ĭ��Ϊ0 ,����ͽ�������д��
    private int hzpageindex = 0;
    //����ҳ������ҳ���� Ĭ��Ϊ0 ,����ͽ�������д��,Ӧ�ò����кܶ��ҳ���
    private int hzmaxpageindex = 0;
    //�ϴ����ļ�
    private FormFile theFile;
    //������������
    private String bdzrmc;
    //��ע
    private String bz;
    //���ܵ���
    private String hzdh;
    //�ɿ�ƾ֤��
    private String[] jkpzh = null;
    //˰��������ʼ����
    private Timestamp skssksrq;
    //˰��������ʼ����
    private Timestamp skssjsrq;
    //���������
    private String jsjdm;
    //��ϵ�绰 �ӵǼǵõ� ����ʱ��Ҫ��д����ϸ����
    private String lxdh;
    //¼��������
    private String lrr;
    //˰�������֯�������� ��userdata�õ�����ʱ��Ҫ��д����ϸ����
    private String swjgzzjgdm;
    //��λ���� �ӵǼǵõ� ����ʱ��Ҫ��д����ϸ����
    private String dwmc;
    //��ҳ����������
    private String viewPageType;
    //��ǰҳ�Ļ�����ϢList
    private List hzInforList = new ArrayList();
    //��ҳ������л�����ϢList
    private List splitHzInforList = new ArrayList();
    //��ǰ��Ӧ��ҵ��ҳ���ʶ
    private String optJspTag = OPTJSP_DR;

    //Ҫ�����ĵĻ��ܵ�list
    private List erasehzdList =  new ArrayList();
    //���ܵ��еĽɿ���list
    private List erasejkpzhList = new ArrayList();
    //��ǰ�ڳ���ҳ��ѡ�еĻ��ܵ��ŵ�����
    private int curEraseIndex = 0;
    //�ϴ��������Ƿ��д�
    private int errTag = NOERR;
    //�������ҳ�����Ϣ
    private String resultMessage = "�������۴������ۻ������ݳɹ�";
    //�ϼ�ʵ�ɽ��
    private BigDecimal sjjehj ;
    //�ϼƼ�˰���
    private BigDecimal jsjehj;
    //��һ���걨���
    private String preSbbh;

    public List convertdsdzdkmx()
    {
        List dsdzdkmxList = new ArrayList();

        return dsdzdkmxList;
    }

    public String getHzdh()
    {
        return hzdh;
    }
    public String[] getJkpzh()
    {
        return jkpzh;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getLxdh()
    {
        return lxdh;
    }
    public FormFile getTheFile()
    {
        return theFile;
    }
    public void setTheFile(FormFile theFile)
    {
        this.theFile = theFile;
    }
    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setJkpzh(String[] jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setHzdh(String hzdh)
    {
        this.hzdh = hzdh;
    }
    public Timestamp getSkssjsrq()
    {
        return skssjsrq;
    }
    public Timestamp getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssjsrq(Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSkssksrq(Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public void setDwmc(String dwmc)
    {
        this.dwmc = dwmc;
    }
    public String getDwmc()
    {
        return dwmc;
    }
    public String getLrr()
    {
        return lrr;
    }
    public void setLrr(String lrr)
    {
        this.lrr = lrr;
    }
    public List getDsdzdkmxItemList()
    {
        return dsdzdkmxItemList;
    }
    public void setDsdzdkmxItemList(List dsdzdkmxItemList)
    {
        this.dsdzdkmxItemList = dsdzdkmxItemList;
    }
    public int getMaxpageindex()
    {
        return maxpageindex;
    }
    public void setMaxpageindex(int maxpageindex)
    {
        this.maxpageindex = maxpageindex;
    }
    public void setPageindex(int pageindex)
    {
        this.pageindex = pageindex;
    }
    public int getPageindex()
    {
        return pageindex;
    }
    public List getSplitPageList()
    {
        return splitPageList;
    }
    public void setSplitPageList(List splitPageList)
    {
        this.splitPageList = splitPageList;
    }
    public void setViewPageType(String viewPageType)
    {
        this.viewPageType = viewPageType;
    }
    public String getViewPageType()
    {
        return viewPageType;
    }
    public List getHzInforList()
    {
        return hzInforList;
    }
    public void setHzInforList(List hzInforList)
    {
        this.hzInforList = hzInforList;
    }
    public String getOptJspTag()
    {
        return optJspTag;
    }
    public void setOptJspTag(String optJspTag)
    {
        this.optJspTag = optJspTag;
    }
    public int getHzmaxpageindex()
    {
        return hzmaxpageindex;
    }
    public int getHzpageindex()
    {
        return hzpageindex;
    }
    public void setHzmaxpageindex(int hzmaxpageindex)
    {
        this.hzmaxpageindex = hzmaxpageindex;
    }
    public void setHzpageindex(int hzpageindex)
    {
        this.hzpageindex = hzpageindex;
    }
    public List getSplitHzInforList()
    {
        return splitHzInforList;
    }
    public void setSplitHzInforList(List splitHzInforList)
    {
        this.splitHzInforList = splitHzInforList;
    }
  public List getErasehzdList() {
    return erasehzdList;
  }
  public List getErasejkpzhList() {
    return erasejkpzhList;
  }
  public void setErasehzdList(List erasehzdList) {
    this.erasehzdList = erasehzdList;
  }
  public void setErasejkpzhList(List erasejkpzhList) {
    this.erasejkpzhList = erasejkpzhList;
  }
    public int getCurEraseIndex()
    {
        return curEraseIndex;
    }
    public void setCurEraseIndex(int curEraseIndex)
    {
        this.curEraseIndex = curEraseIndex;
    }
    public int getErrTag()
    {
        return errTag;
    }
    public void setErrTag(int errTag)
    {
        this.errTag = errTag;
    }
    public String getResultMessage()
    {
        return resultMessage;
    }
    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }
    public BigDecimal getJsjehj()
    {
        return jsjehj;
    }
    public void setJsjehj(BigDecimal jsjehj)
    {
        this.jsjehj = jsjehj;
    }
    public void setSjjehj(BigDecimal sjjehj)
    {
        this.sjjehj = sjjehj;
    }
    public BigDecimal getSjjehj()
    {
        return sjjehj;
    }
    public String getBdzrmc()
    {
        return bdzrmc;
    }
    public String getBz()
    {
        return bz;
    }
    public void setBdzrmc(String bdzrmc)
    {
        this.bdzrmc = bdzrmc;
    }
    public void setBz(String bz)
    {
        this.bz = bz;
    }
    public String getPreSbbh()
    {
        return preSbbh;
    }
    public void setPreSbbh(String preSbbh)
    {
        this.preSbbh = preSbbh;
    }
}
/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����ӡ��˰����������� ActionForm</p>
 * @author �������飭�������
 * @version 1.1
 */
public class YhsgmhzcxForm
    extends BaseForm
{
    /**
     *�������ܶ���
     */
    private String cxhzdx = "";

    /**
     *ӡ��˰������Ա
     */
    private String yhsxsry = "";

    /**
     *���۵�λ���������
     */
    private String dsjsjdm = "";

    /**
     *�����걨���
     */
    private String cxsbbh = "";

    /**
     *�����ɿ�ƾ֤��
     */
    private String cxjkpzh = "";
    
    /**
     *�ɿ�ƾ֤��
     */
    private String jkpzh = "";

    /**
     * �걨���
     */
    private String sbbh = "";

    /**
     *ʵ�ɽ��
     */
    private String sjje = "";

    /**
     * �ӱ����ݣ�list�����ģ�
     */
    private String columns[] =
        {
        "jkpzh", "sjje"};
    /**
     * ��ϸ��Ŀ����
     */
    private ArrayList dataList = new ArrayList();

    /**
     * ��Դ���
     */
    private boolean isFromCx;

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        cxhzdx = "0";
        yhsxsry = "";
        cxsbbh = "";
        dataList.clear();
    }

    public void setCxhzdx (String cxhzdx)
    {
        this.cxhzdx = cxhzdx;
    }

    public void setYhsxsry (String yhsxsry)
    {
        this.yhsxsry = yhsxsry;
    }

    public String getYhsxsry ()
    {
        return yhsxsry;
    }

    public String getCxhzdx ()
    {
        return cxhzdx;
    }

    public String getDsjsjdm ()
    {
        return dsjsjdm;
    }

    public void setDsjsjdm (String dsjsjdm)
    {
        this.dsjsjdm = dsjsjdm;
    }

    public String[] getColumns ()
    {
        return columns;
    }

    public ArrayList getDataList ()
    {
        return dataList;
    }

    public void setColumns (String[] columns)
    {
        this.columns = columns;
    }

    public void setDataList (ArrayList dataList)
    {
        this.dataList = dataList;
    }

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getSjje ()
    {
        return sjje;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public void setSjje (String sjje)
    {
        this.sjje = sjje;
    }

    public boolean isIsFromCx ()
    {
        return isFromCx;
    }

    public String getSbbh() {
        return sbbh;
    }

    public String getCxsbbh() {
        return cxsbbh;
    }

    public void setIsFromCx (boolean isFromCx)
    {
        this.isFromCx = isFromCx;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setCxsbbh(String cxsbbh) {
        this.cxsbbh = cxsbbh;
    }

	public String getCxjkpzh() {
		return cxjkpzh;
	}

	public void setCxjkpzh(String cxjkpzh) {
		this.cxjkpzh = cxjkpzh;
	}

}

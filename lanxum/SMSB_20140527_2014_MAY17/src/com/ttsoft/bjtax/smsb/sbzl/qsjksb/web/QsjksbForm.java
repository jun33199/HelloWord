/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.web;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.form.BaseForm;
import java.util.List;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: Ƿ˰�ɿ��걨JavaBean </p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbForm
    extends BaseForm
{
    private String mxstrings ;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * ��������
     */
    private String yhmc;

    /**
     * ���д���
     */
    private String yhdm;

    /**
     * �˺�
     */
    private String zh;

    /**
     * ��λ����
     */
    private String dwmc;

    /**
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * ˰����������
     */
    private String sklxmc;

    /**
     * ���������
     */
    private String jsjdm;

    /**
     * Ƿ˰�ϼ�
     */
    private String qshj;
    

    /**
     * �걨���ϼ�
     */
    
    private String sbhj ="0";
    

    /**
     * ���ɽ�ϼ�
     */
    private String znjhj ="0";
    
    /**
     * ϵͳ����ϼ�
     */
    private String xthj ="0";
    /**
     * ���������б�
     */
    private List dataList = new ArrayList();
    /**
     * ������Ϣ�б�
     */
    private List bankList = new ArrayList();

    /**
     * ��˰������
     */
    private String nsrmc;

    /**
     * ������Ϣjs�����ַ���
     */
    private String bankJsArray;

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ��˰��״̬
     */
    private String nsrzt;

    /**
     * �ɿ�������
     */
    private int jksType;
    
    /**
     * ˰�������֯�ṹ����
     */
    private String swjgzzjgdm;

    /**
     * �Ǽ�ע�����ʹ���
     */
   private String djzclxdm;

    /**
     * ���ұ�׼��ҵ����
     */
    private String gjbzhydm;

    /**
     * ���ش���
     */
    private String qxdm;
    
    /**
     * �걨��������б�
     */
    private List sbbhList = new ArrayList();
    
    /**
     * �޸�Ȩ��
     */
    private String xgqx;

    public void setMxstrings (String mxstrings)
    {
        this.mxstrings = mxstrings;
    }

    public String getMxstrings ()
    {
        return mxstrings;
    }

    public ActionErrors validate (ActionMapping actionMapping,
                                  HttpServletRequest httpServletRequest)
    {
        /**@todo: finish this method, this is just the skeleton.*/
        return null;
    }

    public void reset (ActionMapping actionMapping,
                       HttpServletRequest httpServletRequest)
    {
        this.actionType = "Show";
        this.dwmc = null;
        this.dataList.clear();
        this.yhmc = null;
        this.bankList.clear();
        //this.sbrq=null;
        //this.jsjdm=null;
        this.nsrmc = null;
        this.bankJsArray = "var bankInfo=new Array();";
        this.nsrzt = CodeConstant.NSRZT_ZC;
        this.mxstrings = null;
        this.xgqx = null;
        this.sbbhList.clear();
    }

    public String getSbrq ()
    {
        return sbrq;
    }

    public void setSbrq (String sbrq)
    {
        this.sbrq = sbrq;
    }

    public String getYhmc ()
    {
        return yhmc;
    }

    public void setYhmc (String yhmc)
    {
        this.yhmc = yhmc;
    }

    public String getYhdm ()
    {
        return yhdm;
    }

    public void setYhdm (String yhdm)
    {
        this.yhdm = yhdm;
    }

    public String getZh ()
    {
        return zh;
    }

    public void setZh (String zh)
    {
        this.zh = zh;
    }

    public String getDwmc ()
    {
        return dwmc;
    }

    public void setDwmc (String dwmc)
    {
        this.dwmc = dwmc;
    }

    public String getSklxdm ()
    {
        return sklxdm;
    }

    public void setSklxdm (String sklxdm)
    {
        this.sklxdm = sklxdm;
    }

    public String getSklxmc ()
    {
        return sklxmc;
    }

    public void setSklxmc (String sklxmc)
    {
        this.sklxmc = sklxmc;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }


    public String getXthj ()
    {
        return xthj;
    }

    public void setXthj (String xthj)
    {
        this.xthj = xthj;
    }

    public List getDataList ()
    {
        return dataList;
    }

    public void setDataList (List dataList)
    {
        this.dataList = dataList;
    }

    public java.util.List getBankList ()
    {
        return bankList;
    }

    public void setBankList (java.util.List bankList)
    {
        this.bankList = bankList;
    }

    public String getNsrmc ()
    {
        return nsrmc;
    }

    public void setNsrmc (String nsrmc)
    {
        this.nsrmc = nsrmc;
    }

    public String getBankJsArray ()
    {
        return bankJsArray;
    }

    public void setBankJsArray (String bankJsArray)
    {
        this.bankJsArray = bankJsArray;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

    public String getNsrzt ()
    {
        return nsrzt;
    }

  public int getJksType()
  {
    return jksType;
  }

  public void setNsrzt (String nsrzt)
    {
        this.nsrzt = nsrzt;
    }

  public void setJksType(int jksType)
  {
    this.jksType = jksType;
  }
 
  public String getSwjgzzjgdm ()
  {
      return swjgzzjgdm;
  }

  public void setSwjgzzjgdm (String swjgzzjgdm)
  {
      this.swjgzzjgdm = swjgzzjgdm;
  } 
 
  public String getDjzclxdm ()
  {
      return djzclxdm;
  }

  public void setDjzclxdm (String djzclxdm)
  {
      this.djzclxdm = djzclxdm;
  }  
  public String getGjbzhydm ()
  {
      return gjbzhydm;
  }

  public void setGjbzhydm (String gjbzhydm)
  {
      this.gjbzhydm = gjbzhydm;
  } 
  
  public String getQxdm ()
  {
      return qxdm;
  }

  public void setQxdm (String qxdm)
  {
      this.qxdm = qxdm;
  }  

  public List getSbbhList ()
  {
      return sbbhList;
  }

  public void setSbbhList (List sbbhList)
  {
      this.sbbhList = sbbhList;
  }
  
  public String getXgqx ()
  {
      return xgqx;
  }

  public void setXgqx (String xgqx)
  {
      this.xgqx = xgqx;
  }

public String getQshj() {
	return qshj;
}

public void setQshj(String qshj) {
	this.qshj = qshj;
}

public String getSbhj() {
	return sbhj;
}

public void setSbhj(String sbhj) {
	this.sbhj = sbhj;
}

public String getZnjhj() {
	return znjhj;
}

public void setZnjhj(String znjhj) {
	this.znjhj = znjhj;
}   
}


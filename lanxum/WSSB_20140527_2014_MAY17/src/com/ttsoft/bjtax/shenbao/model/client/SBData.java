package com.ttsoft.bjtax.shenbao.model.client;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import java.math.BigDecimal;

public class SBData implements java.io.Serializable
{
  /**
   * �걨���
   */
  String sbbh;

  /**
   * ���������
   */
  String jsjdm;

  /**
   * �걨����
   */
  Timestamp sbrq;

  /**
   * �ϼ�ʵ�ɽ��
   */
  BigDecimal hjsjje;

  /**
   * �ϼ������
   */
  BigDecimal hjrkje;

  /**
   * �걨����
   */
  HashMap declareInforMap = new HashMap();

  public SBData()
  {
    sbrq = null;
    sbbh = null;
    hjsjje = new BigDecimal(0);
    hjrkje = new BigDecimal(0);
  }

  public Timestamp getSbrq()
  {
    return sbrq;
  }

  public String getSbbh()
  {
    return sbbh;
  }

  public HashMap getDeclareInforMap()
  {
    return declareInforMap;
  }

  public BigDecimal getHjrkje()
  {
    return hjrkje;
  }

  public BigDecimal getHjsjje()
  {
    return hjsjje;
  }

  public String getJsjdm()
  {
    return jsjdm;
  }

  public void setSbrq(Timestamp sbrq)
  {
    this.sbrq = sbrq;
  }

  public void setSbbh(String sbbh)
  {
    this.sbbh = sbbh;
  }

  public void clearDeclareInfor()
  {
    declareInforMap.clear();
    sbrq = null;
    sbbh = null;
    hjsjje = new BigDecimal(0);
    hjrkje = new BigDecimal(0);
  }

  public boolean addSbjkzb(Sbjkzb zb)
  {
    DeclareInfor di = null;
    di = (DeclareInfor)declareInforMap.get(zb.getJkpzh());
    if (di != null)
    {
      //�걨���������Ѿ�����
      return true;
    }
    if (sbbh == null)
    {
      sbbh = zb.getSbbh();
      jsjdm = zb.getJsjdm();
      sbrq = zb.getSbrq();
    }
    else
    {
      if (!sbbh.equals(zb.getSbbh()))
      {
        return false;
      }
      if (!jsjdm.equals(zb.getJsjdm()))
      {
        return false;
      }
    }
    di = new DeclareInfor();
    declareInforMap.put(zb.getJkpzh(), di);
    di.setSbjkzb(zb);


    hjsjje = hjsjje.add(zb.getSjje());
    hjrkje = hjrkje.add(zb.getRkje());

    return true;
  }

  public boolean addSbjkmx(Sbjkmx mx)
  {
    DeclareInfor di = null;
    di = (DeclareInfor)declareInforMap.get(mx.getJkpzh());
    if (di == null)
    {
      return false;
    }
    List mxlist = di.getSbjkmxInfo();
    Sbjkmx tempmx = null;
    for (int i = 0; i < mxlist.size(); i++)
    {
      tempmx = (Sbjkmx)mxlist.get(i);
      if (tempmx.getSzsmdm().equals(mx.getSzsmdm()))
      {
        //��ϸ�����Ѿ�����
        return true;
      }
    }
    mxlist.add(mx);
    return true;
  }
  public boolean addDeclareInfor(DeclareInfor di)
  {
    DeclareInfor tempdi = (DeclareInfor)declareInforMap.get(di.getSbjkzb().getJkpzh());
    if (tempdi == null)
    {
      declareInforMap.put(di.getSbjkzb().getJkpzh(),di);

      if (sbbh == null)
      {
          sbbh = di.getSbjkzb().getSbbh();
          jsjdm = di.getSbjkzb().getJsjdm();
          sbrq = di.getSbjkzb().getSbrq();
      }


      hjsjje = hjsjje.add(di.getSbjkzb().getSjje());
      hjrkje = hjrkje.add(di.getSbjkzb().getRkje());
      return true;
    }
    return false;
  }
}

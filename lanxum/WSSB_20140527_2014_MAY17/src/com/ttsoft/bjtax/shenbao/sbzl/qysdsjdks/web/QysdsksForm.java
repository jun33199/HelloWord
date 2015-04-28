package com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

/**
 * @author He Zhiyong
 * @version 1.0
 * ��ҵ����˰�����걨ǰ̨���ݽṹForm
 */
public class QysdsksForm extends SbzlBaseForm
{

   /**
    * ���������
    */
   private String jsjdm;

   /**
    * ���
    */
   private String nd;

   /**
    * ����
    */
   private String jd;

   /**
    * ˰��������ʼ����
    */
   private String skssksrq;

   /**
    * ˰��������������
    */
   private String skssjsrq;

   /**
    * ������ҵ��ʶ
    */
   private String isXzqy;

   /**
    * ��λ����
    */
   private String dwmc;

   /**
    * ��������˰��
    */
   private String dezsse;

   /**
    * �����ܶ�
    */
   private String srze;

   /**
    * �����ܶ�(Ӧ��˰���ö
    */
   private String lrze;

   /**
    * ����˰��
    */
   private String sl;

   /**
    * Ӧ������˰��
    */
   private String ynsdse;

   /**
    * �ڳ�δ������˰��
    */
   private String qcwjsdse;

   /**
    * ��������˰��
    */
   private String jmsdse;

   /**
    * �鲹��ǰ���˰��
    */
   private String cbyqndse;

   /**
    * ʵ���ѽ���˰����˰��
    */
   private String sjyjnssdse;

   /**
    * �����걨�ӽ�����˰��
    */
   private String bqyjsdse;

   /**
    * ʵ��Ӧ��(��)����˰��
    */
   private String sjybsdse;

   /**
    * �ֲ���ǰ��ȿ���
    */
   private String mbyqndks;

   /**
    * �����������ܶ�
    */
   private String bkhlrze;

   /**
    * �����������ܶ�
    */
   private String ybjmsl;

   /**
    * ��ҵ����˰�����걨���ݱ�ORMapping ֵ�����
    */
   private Qysdsjd qysdsjd;

   /**
    * �Ǽǻ������ݶ���
    */
   private SWDJJBSJ djsj;

   /**
    * ��ҵ��������
    */
   private String qyzslx = "-1";

   /**
    * ������
    */
   private String cyl;

   /**
    * �ɿ����
    */
   private String jksh;

   /**
    * ��˰������
    * ����λ���ƣ�
    */
   private String nsrmc;

   /**
    * �����ʸ� 0�����ʸ� 1�����ʸ�
    */
   private String jmzg = "0";

   public QysdsksForm()
   {
   }

   /**
    * �ѱ�����ҵ����˰�����걨���ݱ�ORMapping ֵ�����
    * ת����ǰ̨String����
    */
   public void toStr()
   {
    // �����ҵ����˰����ֵ����Ϊnull
    if(this.qysdsjd != null)
    {
       // ����form������
       this.jsjdm = qysdsjd.getJsjdm();
       this.dwmc = this.djsj.getNsrmc();
       this.jksh = qysdsjd.getJkpzh();
       SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
       DecimalFormat ft = new DecimalFormat("0.00");

       this.setBqyjsdse(ft.format(qysdsjd.getBqyjsdse()));
       this.setCbyqndse(ft.format(qysdsjd.getCbyqndse()));
       this.setJd(qysdsjd.getJd());
       this.setJmsdse(ft.format(qysdsjd.getJmsdse()));
       this.setLrze(ft.format(qysdsjd.getLrze()));
       this.setNd(qysdsjd.getNd());
       this.setQcwjsdse(ft.format(qysdsjd.getQcwjsdse()));
       this.setSjybsdse(ft.format(qysdsjd.getSjybsdse()));
       this.setSjyjnssdse(ft.format(qysdsjd.getSjyjsdsse()));
       this.setSkssjsrq(sdf.format(qysdsjd.getSkssjsrq()));
       this.setSkssksrq(sdf.format(qysdsjd.getSkssksrq()));
       this.setSl(ft.format(qysdsjd.getSl()));
       this.setSrze(ft.format(qysdsjd.getSrze()));
       this.setYnsdse(ft.format(qysdsjd.getYnsdse()));
       this.setMbyqndks(ft.format(qysdsjd.getMbyqndks()));
      this.setBkhlrze(ft.format(qysdsjd.getBkhlrze()));
   }
   }

   /**
    * ��Form�е����ݰ�װ����ҵ����˰�����걨����ֵ����
    * @param swdjjbsj �Ǽ���Ϣ
    */
   public void toVo(SWDJJBSJ swdjjbsj)
   {
       // �����ҵ����˰����ֵ����Ϊnull
      if(this.qysdsjd != null)
      {
          if(this.ynsdse.trim().equals(""))
          {
              this.ynsdse = "0.00";
          }
          if(this.srze.trim().equals(""))
          {
              this.srze = "0.00";
          }
          if(this.sl.trim().equals(""))
          {
              this.sl = "0.00";
          }
          if(this.lrze.trim().equals(""))
          {
              this.lrze = "0.00";
          }
          if(this.qcwjsdse.trim().equals(""))
          {
              this.qcwjsdse = "0.00";
          }
          if(this.jmsdse.trim().equals(""))
          {
              this.jmsdse = "0.00";
          }
          if(this.cbyqndse.trim().equals(""))
          {
              this.cbyqndse = "0.00";
          }
          if(this.sjyjnssdse.trim().equals(""))
          {
              this.sjyjnssdse = "0.00";
          }
          if(this.bqyjsdse.trim().equals(""))
          {
              this.bqyjsdse = "0.00";
          }
          if(this.sjybsdse.trim().equals(""))
          {
              this.sjybsdse = "0.00";
          }
          if(this.mbyqndks.trim().equals(""))
          {
              this.mbyqndks = "0.00";
          }
          if(this.bkhlrze.trim().equals(""))
          {
              this.bkhlrze = "0.00";
          }
          // ��������
          qysdsjd.setYnsdse(new BigDecimal(this.ynsdse));
          qysdsjd.setBqyjsdse(new BigDecimal(this.bqyjsdse));
          qysdsjd.setCbyqndse(new BigDecimal(this.cbyqndse));
          qysdsjd.setJkpzh(this.jksh);
          qysdsjd.setJmsdse(new BigDecimal(this.jmsdse));
          qysdsjd.setLrze(new BigDecimal(this.lrze));
          qysdsjd.setQcwjsdse(new BigDecimal(this.qcwjsdse));
          qysdsjd.setSjybsdse(new BigDecimal(this.sjybsdse));
          qysdsjd.setSjyjsdsse(new BigDecimal(this.sjyjnssdse));
          qysdsjd.setSl(new BigDecimal(this.sl));
          qysdsjd.setSrze(new BigDecimal(this.srze));
          qysdsjd.setMbyqndks(new BigDecimal(this.mbyqndks));
          qysdsjd.setBkhlrze(new BigDecimal(this.bkhlrze));
          qysdsjd.setSwjgzzjgdm(swdjjbsj.getSwjgzzjgdm());
          qysdsjd.setFsdm(CodeConstant.FSDM_WSSB);

          Timestamp now = new Timestamp(System.currentTimeMillis());
          qysdsjd.setLrrq(now);
          qysdsjd.setCjrq(now);
          qysdsjd.setSbrq(TinyTools.second2Day(now));
          qysdsjd.setNd(this.getYear(now));
          qysdsjd.setQxdm(swdjjbsj.getSwjgzzjgdm().substring(0,2));
      }
   }

  public String getDwmc() {
    return dwmc;
  }
  public void setDwmc(String dwmc) {
    this.dwmc = dwmc;
  }
  public String getJsjdm() {
    return jsjdm;
  }
  public void setJsjdm(String jsjdm) {
    this.jsjdm = jsjdm;
  }
  public String getNsrmc() {
    return nsrmc;
  }
  public void setNsrmc(String nsrmc) {
    this.nsrmc = nsrmc;
  }
  public SWDJJBSJ getDjsj() {
    return djsj;
  }
  public void setDjsj(SWDJJBSJ djsj)
  {
    this.djsj = djsj;
  }
    public String getYnsdse()
    {
        return ynsdse;
    }
    public void setYnsdse(String ynsdse)
    {
        this.ynsdse = ynsdse;
    }
    public String getBqyjsdse()
    {
        return bqyjsdse;
    }
    public void setBqyjsdse(String bqyjsdse)
    {
        this.bqyjsdse = bqyjsdse;
    }
    public String getCbyqndse()
    {
        return cbyqndse;
    }
    public void setCbyqndse(String cbyqndse)
    {
        this.cbyqndse = cbyqndse;
    }
    public String getJd()
    {
        return jd;
    }
    public void setJd(String jd)
    {
        this.jd = jd;
    }
    public String getJmsdse()
    {
        return jmsdse;
    }
    public void setJmsdse(String jmsdse)
    {
        this.jmsdse = jmsdse;
    }
    public String getLrze()
    {
        return lrze;
    }
    public void setLrze(String lrze)
    {
        this.lrze = lrze;
    }
    public String getNd()
    {
        return nd;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public String getQcwjsdse()
    {
        return qcwjsdse;
    }
    public void setQcwjsdse(String qcwjsdse)
    {
        this.qcwjsdse = qcwjsdse;
    }
    public Qysdsjd getQysdsjd()
    {
        return qysdsjd;
    }
    public void setQysdsjd(Qysdsjd qysdsjd)
    {
        this.qysdsjd = qysdsjd;
    }
    public String getSjybsdse()
    {
        return sjybsdse;
    }
    public void setSjybsdse(String sjybsdse)
    {
        this.sjybsdse = sjybsdse;
    }
    public String getSjyjnssdse()
    {
        return sjyjnssdse;
    }
    public void setSjyjnssdse(String sjyjnssdse)
    {
        this.sjyjnssdse = sjyjnssdse;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getSl()
    {
        return sl;
    }
    public void setSl(String sl)
    {
        this.sl = sl;
    }
    public String getSrze()
    {
        return srze;
    }
    public void setSrze(String srze)
    {
        this.srze = srze;
    }
    public String getQyzslx()
    {
        return qyzslx;
    }
    public void setQyzslx(String qyzslx)
    {
        this.qyzslx = qyzslx;
    }
    public String getCyl()
    {
        return cyl;
    }
    public void setCyl(String cyl)
    {
        this.cyl = cyl;
    }
    public String getJksh()
    {
        return jksh;
    }
    public void setJksh(String jksh)
    {
        this.jksh = jksh;
    }
    public String getIsXzqy()
    {
        return isXzqy;
    }
    public void setIsXzqy(String isXzqy)
    {
        this.isXzqy = isXzqy;
    }
    /**
     * �õ��������ڵ���� Ϊint��
     * @param date ����������
     * @return int ���ֵ
     */
    private String getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return new Integer((calendar.get(Calendar.YEAR))).toString();
    }
    public String getJmzg()
    {
        return jmzg;
    }
    public void setJmzg(String jmzg)
    {
        this.jmzg = jmzg;
    }
    public String getBkhlrze()
    {
        return bkhlrze;
    }
    public void setBkhlrze(String bkhlrze)
    {
        this.bkhlrze = bkhlrze;
    }
    public String getMbyqndks()
    {
        return mbyqndks;
    }
    public void setMbyqndks(String mbyqndks)
    {
        this.mbyqndks = mbyqndks;
    }
    public String getYbjmsl()
    {
        return ybjmsl;
    }
    public void setYbjmsl(String ybjmsl)
    {
        this.ybjmsl = ybjmsl;
    }
    public String getDezsse()
    {
        return dezsse;
    }
    public void setDezsse(String dezsse)
    {
        this.dezsse = dezsse;
    }
}

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
 * 企业所得税亏损申报前台数据结构Form
 */
public class QysdsksForm extends SbzlBaseForm
{

   /**
    * 计算机代码
    */
   private String jsjdm;

   /**
    * 年度
    */
   private String nd;

   /**
    * 季度
    */
   private String jd;

   /**
    * 税款所属开始日期
    */
   private String skssksrq;

   /**
    * 税款所属结束日期
    */
   private String skssjsrq;

   /**
    * 乡镇企业标识
    */
   private String isXzqy;

   /**
    * 单位名称
    */
   private String dwmc;

   /**
    * 定额征收税额
    */
   private String dezsse;

   /**
    * 收入总额
    */
   private String srze;

   /**
    * 利润总额(应纳税所得额）
    */
   private String lrze;

   /**
    * 适用税率
    */
   private String sl;

   /**
    * 应纳所得税额
    */
   private String ynsdse;

   /**
    * 期初未缴所得税额
    */
   private String qcwjsdse;

   /**
    * 减免所得税额
    */
   private String jmsdse;

   /**
    * 查补以前年度税额
    */
   private String cbyqndse;

   /**
    * 实际已缴纳税所得税额
    */
   private String sjyjnssdse;

   /**
    * 本期申报延交所得税额
    */
   private String bqyjsdse;

   /**
    * 实际应补(退)所得税额
    */
   private String sjybsdse;

   /**
    * 弥补以前年度亏损
    */
   private String mbyqndks;

   /**
    * 补亏后利润总额
    */
   private String bkhlrze;

   /**
    * 补亏后利润总额
    */
   private String ybjmsl;

   /**
    * 企业所得税亏损申报数据表ORMapping 值对象的
    */
   private Qysdsjd qysdsjd;

   /**
    * 登记基本数据对象
    */
   private SWDJJBSJ djsj;

   /**
    * 企业征收类型
    */
   private String qyzslx = "-1";

   /**
    * 纯意率
    */
   private String cyl;

   /**
    * 缴款书号
    */
   private String jksh;

   /**
    * 纳税人名称
    * （单位名称）
    */
   private String nsrmc;

   /**
    * 减免资格 0：无资格 1：有资格
    */
   private String jmzg = "0";

   public QysdsksForm()
   {
   }

   /**
    * 把保存企业所得税亏损申报数据表ORMapping 值对象的
    * 转换成前台String数据
    */
   public void toStr()
   {
    // 如果企业所得税亏损值对象不为null
    if(this.qysdsjd != null)
    {
       // 设置form得属性
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
    * 将Form中的数据包装成企业所得税亏损申报数据值对象
    * @param swdjjbsj 登记信息
    */
   public void toVo(SWDJJBSJ swdjjbsj)
   {
       // 如果企业所得税亏损值对象不为null
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
          // 设置属性
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
     * 得到给定日期的年份 为int型
     * @param date 给定的日期
     * @return int 年份值
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

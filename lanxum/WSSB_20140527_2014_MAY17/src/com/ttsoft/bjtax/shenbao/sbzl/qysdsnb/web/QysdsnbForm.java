package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * 企业所得税年度申报表actionform
 */
public class QysdsnbForm extends SbzlBaseForm
{


   /**
    * 年报项目名称
    */
   private String nbxmmc[];

   /**
    * 年报行次
    */
   private String nbhc[];

   /**
    * 本期累计数
    */
   private String nbbqljs[];

   /**
    * 财务负责人
    */
   private String cwfzr;

   /**
    * 办税人员
    */
   private String bsry;

   /**
    * 《企业所得税申报表》定义列表
    */
   private List nbDefineList;

   /**
    * 事业单位序号
    */
   private String sydwxh[];

   /**
    * 事业单位项目名称
    */
   private String sydwxmmc[];

   /**
    * 事业单位行次
    */
   private String sydwhc[];

   /**
    * 是否乡镇企业
    */
   private String isXzqy;

   /**
    * 事业单位本期累计数
    */
   private String sydwbqljs[];

   /**
    * 《事业单位申报表》定义列表
    */
   private List sydwsbbDefineList;

   /**
    * 事业单位、社会团体第3行批准文号
    */
   private String sydwpzwh3;

   /**
    * 事业单位、社会团体第4行批准文号
    */
   private String sydwpzwh4;

   /**
    * 事业单位、社会团体第5行批准文号
    */
   private String sydwpzwh5;

   /**
    * 企业财务指标序号
    */
   private String qycwzbxh[];

   /**
    * 企业财务指标项目名称
    */
   private String qycwzbxmmc[];

   /**
    * 企业财务指标行次
    */
   private String qycwzbhc[];

   /**
    * 企业财务指标年初数
    */
   private String qycwzbncs[];

   /**
    * 企业财务指标年末数
    */
   private String qycwzbnms[];

   /**
    * 《企业财务指标申报表》定义列表
    */
   private List qycwzbDefineList;

   /**
    * 联营股份企业类型代码
    */
   private String lygfdjzclxdm[];

   /**
    * 联营股份企业名称
    */
   private String lygfqymc[];

   /**
    * 联营股份企业税率
    */
   private String lygfsl[];

   /**
    * 联营股份利润股息投资收入
    */
   private String lygflrgx[];

   /**
    * 联营股份应纳税所得额
    */
   private String lygfynssde[];

   /**
    * 联营股份税收扣除额
    */
   private String lygfsskce[];

   /**
    * 联营股份应纳所得税额
    */
   private String lygfynsdse[];

   /**
    * 联营股份应补所得税额
    */
   private String lygfybsdse[];

   /**
    * 企业征税的税率类型
    * “1”：高新技术企业税率为15%
    * “2”：核定税率
    * “3”：正常税率
    */
   private String qyzssllx;

   /**
    * 企业征税的税率
    * 相对于企业征税的税率类型
    * “1”：高新技术企业税率为15%
    * “2”：核定税率
    * “3”：正常税率
    */
   private String qyzssl;

   /**
    * 企业所得税税率，从税种税目代码表中定义的企业所得税税率
    */

   /**
    * 联营股份企业税率
    * 如果企业征税的税率类型
    * 为“1”：高新技术企业税率，则为qyzssl
    * 否则，为qysdssl
    */
   private String lygfqysl;

   /**
    * 财产损失扣除额 24
    */
   private java.math.BigDecimal spzg24;

   /**
    * 弥补以前年度亏损 63
    */
   private java.math.BigDecimal spzg63;

   /**
    * 免税的技术转让收益 69
    */
   private java.math.BigDecimal spzg69;

   /**
    * 研究新产品、新技术、新工艺费用抵扣额 77
    */
   private java.math.BigDecimal spzg77;

   /**
    * 减免所得税额 81
    */
   private java.math.BigDecimal spzg81;
   /**
    * 技术改造国产设备投资抵免税额 82
    */
   private java.math.BigDecimal spzg82;

   /**
    * 税款所属开始日期
    */
   private String skssksrq;
   /**
    * 税款所属开始日期
    */
   private String skssjsrq;

   /**
    * 计算机代码
    */
   private String jsjdm ;
   /**
    * 登记注册类型代码
    */
   private String djzclxdm;
   /**
    * 纳税人名称
    */
   private String qymc;
   /**
    * 企业联系电话
    */
   private String qylxdh;

   /***
    * 是否有应税收入 0:无，1：有
    */
   private String sfyyssr = "1";

   /**
    * 在纯益率方式下的是否是高新企业：0不是 1是
    */
   private String gxqy = "0";

   public QysdsnbForm()
   {
   }

   /**
    * Access method for the nbxmmc property.
    *
    * @return   the current value of the nbxmmc property
    */
   public String[] getNbxmmc()
   {
      return nbxmmc;
   }

   /**
    * Sets the value of the nbxmmc property.
    *
    * @param aNbxmmc the new value of the nbxmmc property
    */
   public void setNbxmmc(String[] aNbxmmc)
   {
      nbxmmc = aNbxmmc;
   }

   /**
    * Access method for the nbhc property.
    *
    * @return   the current value of the nbhc property
    */
   public String[] getNbhc()
   {
      return nbhc;
   }

   /**
    * Sets the value of the nbhc property.
    *
    * @param aNbhc the new value of the nbhc property
    */
   public void setNbhc(String[] aNbhc)
   {
      nbhc = aNbhc;
   }

   /**
    * Access method for the nbbqljs property.
    *
    * @return   the current value of the nbbqljs property
    */
   public String[] getNbbqljs()
   {
      return nbbqljs;
   }

   /**
    * Sets the value of the nbbqljs property.
    *
    * @param aNbbqljs the new value of the nbbqljs property
    */
   public void setNbbqljs(String[] aNbbqljs)
   {
      nbbqljs = aNbbqljs;
   }


   /**
    * Access method for the cwfzr property.
    *
    * @return   the current value of the cwfzr property
    */
   public String getCwfzr()
   {
      return cwfzr;
   }

   /**
    * Sets the value of the cwfzr property.
    *
    * @param aCwfzr the new value of the cwfzr property
    */
   public void setCwfzr(String aCwfzr)
   {
      cwfzr = aCwfzr;
   }

   /**
    * Access method for the bsry property.
    *
    * @return   the current value of the bsry property
    */
   public String getBsry()
   {
      return bsry;
   }

   /**
    * Sets the value of the bsry property.
    *
    * @param aBsry the new value of the bsry property
    */
   public void setBsry(String aBsry)
   {
      bsry = aBsry;
   }

   /**
    * Access method for the nbDefineList property.
    *
    * @return   the current value of the nbDefineList property
    */
   public List getNbDefineList()
   {
      return nbDefineList;
   }

   /**
    * Sets the value of the nbDefineList property.
    *
    * @param aNbDefineList the new value of the nbDefineList property
    */
   public void setNbDefineList(List aNbDefineList)
   {
      nbDefineList = aNbDefineList;
   }

   /**
    * Access method for the sydwxh property.
    *
    * @return   the current value of the sydwxh property
    */
   public String[] getSydwxh()
   {
      return sydwxh;
   }

   /**
    * Sets the value of the sydwxh property.
    *
    * @param aSydwxh the new value of the sydwxh property
    */
   public void setSydwxh(String[] aSydwxh)
   {
      sydwxh = aSydwxh;
   }

   /**
    * Access method for the sydwxmmc property.
    *
    * @return   the current value of the sydwxmmc property
    */
   public String[] getSydwxmmc()
   {
      return sydwxmmc;
   }

   /**
    * Sets the value of the sydwxmmc property.
    *
    * @param aSydwxmmc the new value of the sydwxmmc property
    */
   public void setSydwxmmc(String[] aSydwxmmc)
   {
      sydwxmmc = aSydwxmmc;
   }

   /**
    * Access method for the sydwhc property.
    *
    * @return   the current value of the sydwhc property
    */
   public String[] getSydwhc()
   {
      return sydwhc;
   }

   /**
    * Sets the value of the sydwhc property.
    *
    * @param aSydwhc the new value of the sydwhc property
    */
   public void setSydwhc(String[] aSydwhc)
   {
      sydwhc = aSydwhc;
   }

   /**
    * Access method for the sydwbqljs property.
    *
    * @return   the current value of the sydwbqljs property
    */
   public String[] getSydwbqljs()
   {
      return sydwbqljs;
   }

   /**
    * Sets the value of the sydwbqljs property.
    *
    * @param aSydwbqljs the new value of the sydwbqljs property
    */
   public void setSydwbqljs(String[] aSydwbqljs)
   {
      sydwbqljs = aSydwbqljs;
   }

   /**
    * Access method for the sydwsbbDefineList property.
    *
    * @return   the current value of the sydwsbbDefineList property
    */
   public List getSydwsbbDefineList()
   {
      return sydwsbbDefineList;
   }

   /**
    * Sets the value of the sydwsbbDefineList property.
    *
    * @param aSydwsbbDefineList the new value of the sydwsbbDefineList property
    */
   public void setSydwsbbDefineList(List aSydwsbbDefineList)
   {
      sydwsbbDefineList = aSydwsbbDefineList;
   }

   /**
    * Access method for the qycwzbxh property.
    *
    * @return   the current value of the qycwzbxh property
    */
   public String[] getQycwzbxh()
   {
      return qycwzbxh;
   }

   /**
    * Sets the value of the qycwzbxh property.
    *
    * @param aQycwzbxh the new value of the qycwzbxh property
    */
   public void setQycwzbxh(String[] aQycwzbxh)
   {
      qycwzbxh = aQycwzbxh;
   }

   /**
    * Access method for the qycwzbxmmc property.
    *
    * @return   the current value of the qycwzbxmmc property
    */
   public String[] getQycwzbxmmc()
   {
      return qycwzbxmmc;
   }

   /**
    * Sets the value of the qycwzbxmmc property.
    *
    * @param aQycwzbxmmc the new value of the qycwzbxmmc property
    */
   public void setQycwzbxmmc(String[] aQycwzbxmmc)
   {
      qycwzbxmmc = aQycwzbxmmc;
   }

   /**
    * Access method for the qycwzbhc property.
    *
    * @return   the current value of the qycwzbhc property
    */
   public String[] getQycwzbhc()
   {
      return qycwzbhc;
   }

   /**
    * Sets the value of the qycwzbhc property.
    *
    * @param aQycwzbhc the new value of the qycwzbhc property
    */
   public void setQycwzbhc(String[] aQycwzbhc)
   {
      qycwzbhc = aQycwzbhc;
   }

   /**
    * Access method for the qycwzbncs property.
    *
    * @return   the current value of the qycwzbncs property
    */
   public String[] getQycwzbncs()
   {
      return qycwzbncs;
   }

   /**
    * Sets the value of the qycwzbncs property.
    *
    * @param aQycwzbncs the new value of the qycwzbncs property
    */
   public void setQycwzbncs(String[] aQycwzbncs)
   {
      qycwzbncs = aQycwzbncs;
   }

   /**
    * Access method for the qycwzbnms property.
    *
    * @return   the current value of the qycwzbnms property
    */
   public String[] getQycwzbnms()
   {
      return qycwzbnms;
   }

   /**
    * Sets the value of the qycwzbnms property.
    *
    * @param aQycwzbnms the new value of the qycwzbnms property
    */
   public void setQycwzbnms(String[] aQycwzbnms)
   {
      qycwzbnms = aQycwzbnms;
   }

   /**
    * Access method for the qycwzbDefineList property.
    *
    * @return   the current value of the qycwzbDefineList property
    */
   public List getQycwzbDefineList()
   {
      return qycwzbDefineList;
   }

   /**
    * Sets the value of the qycwzbDefineList property.
    *
    * @param aQycwzbDefineList the new value of the qycwzbDefineList property
    */
   public void setQycwzbDefineList(List aQycwzbDefineList)
   {
      qycwzbDefineList = aQycwzbDefineList;
   }

   /**
    * Access method for the lygfdjzclxdm property.
    *
    * @return   the current value of the lygfdjzclxdm property
    */
   public String[] getLygfdjzclxdm()
   {
      return lygfdjzclxdm;
   }

   /**
    * Sets the value of the lygfdjzclxdm property.
    *
    * @param aLygfdjzclxdm the new value of the lygfdjzclxdm property
    */
   public void setLygfdjzclxdm(String[] aLygfdjzclxdm)
   {
      lygfdjzclxdm = aLygfdjzclxdm;
   }

   /**
    * Access method for the lygfqymc property.
    *
    * @return   the current value of the lygfqymc property
    */
   public String[] getLygfqymc()
   {
      return lygfqymc;
   }

   /**
    * Sets the value of the lygfqymc property.
    *
    * @param aLygfqymc the new value of the lygfqymc property
    */
   public void setLygfqymc(String[] aLygfqymc)
   {
      lygfqymc = aLygfqymc;
   }

   /**
    * Access method for the lygfsl property.
    *
    * @return   the current value of the lygfsl property
    */
   public String[] getLygfsl()
   {
      return lygfsl;
   }

   /**
    * Sets the value of the lygfsl property.
    *
    * @param aLygfsl the new value of the lygfsl property
    */
   public void setLygfsl(String[] aLygfsl)
   {
      lygfsl = aLygfsl;
   }

   /**
    * Access method for the lygflrgx property.
    *
    * @return   the current value of the lygflrgx property
    */
   public String[] getLygflrgx()
   {
      return lygflrgx;
   }

   /**
    * Sets the value of the lygflrgx property.
    *
    * @param aLygflrgx the new value of the lygflrgx property
    */
   public void setLygflrgx(String[] aLygflrgx)
   {
      lygflrgx = aLygflrgx;
   }

   /**
    * Access method for the lygfynssde property.
    *
    * @return   the current value of the lygfynssde property
    */
   public String[] getLygfynssde()
   {
      return lygfynssde;
   }

   /**
    * Sets the value of the lygfynssde property.
    *
    * @param aLygfynssde the new value of the lygfynssde property
    */
   public void setLygfynssde(String[] aLygfynssde)
   {
      lygfynssde = aLygfynssde;
   }

   /**
    * Access method for the lygfynsdse property.
    *
    * @return   the current value of the lygfynsdse property
    */
   public String[] getLygfynsdse()
   {
      return lygfynsdse;
   }

   /**
    * Sets the value of the lygfynsdse property.
    *
    * @param aLygfynsdse the new value of the lygfynsdse property
    */
   public void setLygfynsdse(String[] aLygfynsdse)
   {
      lygfynsdse = aLygfynsdse;
   }

   /**
    * Access method for the lygfybsdse property.
    *
    * @return   the current value of the lygfybsdse property
    */
   public String[] getLygfybsdse()
   {
      return lygfybsdse;
   }

   /**
    * Sets the value of the lygfybsdse property.
    *
    * @param aLygfybsdse the new value of the lygfybsdse property
    */
   public void setLygfybsdse(String[] aLygfybsdse)
   {
      lygfybsdse = aLygfybsdse;
   }

   /**
    * Access method for the qyzssllx property.
    *
    * @return   the current value of the qyzssllx property
    */
   public String getQyzssllx()
   {
      return qyzssllx;
   }

   /**
    * Sets the value of the qyzssllx property.
    *
    * @param aQyzssllx the new value of the qyzssllx property
    */
   public void setQyzssllx(String aQyzssllx)
   {
      qyzssllx = aQyzssllx;
   }

   /**
    * Access method for the qyzssl property.
    *
    * @return   the current value of the qyzssl property
    */
   public String getQyzssl()
   {
      return qyzssl;
   }

   /**
    * Sets the value of the qyzssl property.
    *
    * @param aQyzssl the new value of the qyzssl property
    */
   public void setQyzssl(String aQyzssl)
   {
      qyzssl = aQyzssl;
   }


   /**
    * Access method for the lygfqysl property.
    *
    * @return   the current value of the lygfqysl property
    */
   public String getLygfqysl()
   {
      return lygfqysl;
   }

   /**
    * Sets the value of the lygfqysl property.
    *
    * @param aLygfqysl the new value of the lygfqysl property
    */
   public void setLygfqysl(String aLygfqysl)
   {
      lygfqysl = aLygfqysl;
   }

   /**
    * 实现ActionForm的validate方法,对form的数据进行数据（主要是由页面提交生成的）的有?

    * 性进行检查，如果发现错误则创建一个ActionError对象，并放入道ActionErrors中，否则?

    * 回null
    *
    * 1、年报的数据不能为空
    * 2、所有的本期累计数、年初数和年末数都必须是数字或为空
    * ....
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    * @return ActonErrors，如果检查无误，则返回null
    */
   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
   {
    return null;
   }

   /**
    * 重置form的属性（不是全部属性，提交后不重新赋值的属性不需reset）
    *
    * 1、将所有的数组(xh xmmc hc bqljs ncs nms) 置 null
    * 2、将sydwpzwh3、4、5置 null
    * 3、将l联营股份所有的数组数据置 null
    *
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    */
   public void reset(ActionMapping mapping, HttpServletRequest request)
   {
       //年报
       nbxmmc = null;
       nbhc = null;
       nbbqljs = null;

       //事业单位、社会团体
       sydwxmmc = null;
       sydwhc = null;
       sydwbqljs = null;
       sydwpzwh3 = null;
       sydwpzwh4 = null;
       sydwpzwh5 = null;

       //企业财务指标
       qycwzbxmmc = null;
       qycwzbhc = null;
       qycwzbncs = null;
       qycwzbnms = null;

       //联营股份
       lygfdjzclxdm = null;
       lygfqymc = null;
       lygfsl = null;
       lygflrgx = null;
       lygfynssde = null;
       lygfynsdse = null;
       lygfybsdse = null;
       lygfsskce = null;

   }
    public String getSydwpzwh5() {
        return sydwpzwh5;
    }
    public String getSydwpzwh4() {
        return sydwpzwh4;
    }
    public String getSydwpzwh3() {
        return sydwpzwh3;
    }
    public void setSydwpzwh3(String sydwpzwh3) {
        this.sydwpzwh3 = sydwpzwh3;
    }
    public void setSydwpzwh4(String sydwpzwh4) {
        this.sydwpzwh4 = sydwpzwh4;
    }
    public void setSydwpzwh5(String sydwpzwh5) {
        this.sydwpzwh5 = sydwpzwh5;
    }
    public String[] getLygfsskce() {
        return lygfsskce;
    }
    public void setLygfsskce(String[] lygfsskce) {
        this.lygfsskce = lygfsskce;
    }
    public java.math.BigDecimal getSpzg24()
    {
        return spzg24;
    }
    public java.math.BigDecimal getSpzg63()
    {
        return spzg63;
    }
    public java.math.BigDecimal getSpzg69()
    {
        return spzg69;
    }
    public java.math.BigDecimal getSpzg77()
    {
        return spzg77;
    }
    public java.math.BigDecimal getSpzg82()
    {
        return spzg82;
    }
    public void setSpzg82(java.math.BigDecimal spzg82)
    {
        this.spzg82 = spzg82;
    }
    public void setSpzg77(java.math.BigDecimal spzg77)
    {
        this.spzg77 = spzg77;
    }
    public void setSpzg69(java.math.BigDecimal spzg69)
    {
        this.spzg69 = spzg69;
    }
    public void setSpzg63(java.math.BigDecimal spzg63)
    {
        this.spzg63 = spzg63;
    }
    public void setSpzg24(java.math.BigDecimal spzg24)
    {
        this.spzg24 = spzg24;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getDjzclxdm() {
        return djzclxdm;
    }
    public void setDjzclxdm(String djzclxdm) {
        this.djzclxdm = djzclxdm;
    }
    public String getQymc() {
        return qymc;
    }
    public void setQymc(String qymc) {
        this.qymc = qymc;
    }
    public String getQylxdh() {
        return qylxdh;
    }
    public void setQylxdh(String qylxdh) {
        this.qylxdh = qylxdh;
    }

    /**
     * 获得格式为yyyy-MM-dd的当前日期
     * @return
     */
    public String getCurtime(){
       java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-MM-dd");
       return df.format(new java.util.Date());
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public String getSfyyssr()
    {
        return sfyyssr;
    }
    public void setSfyyssr(String sfyyssr)
    {
        this.sfyyssr = sfyyssr;
    }
    public java.math.BigDecimal getSpzg81() {
        return spzg81;
    }
    public void setSpzg81(java.math.BigDecimal spzg81) {
        this.spzg81 = spzg81;
    }
    public String getIsXzqy()
    {
        return isXzqy;
    }
    public void setIsXzqy(String isXzqy)
    {
        this.isXzqy = isXzqy;
    }
    public String getGxqy()
    {
        return gxqy;
    }
    public void setGxqy(String gxqy)
    {
        this.gxqy = gxqy;
    }
}

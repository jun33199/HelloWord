package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * ��ҵ����˰����걨��actionform
 */
public class QysdsnbForm extends SbzlBaseForm
{


   /**
    * �걨��Ŀ����
    */
   private String nbxmmc[];

   /**
    * �걨�д�
    */
   private String nbhc[];

   /**
    * �����ۼ���
    */
   private String nbbqljs[];

   /**
    * ��������
    */
   private String cwfzr;

   /**
    * ��˰��Ա
    */
   private String bsry;

   /**
    * ����ҵ����˰�걨�������б�
    */
   private List nbDefineList;

   /**
    * ��ҵ��λ���
    */
   private String sydwxh[];

   /**
    * ��ҵ��λ��Ŀ����
    */
   private String sydwxmmc[];

   /**
    * ��ҵ��λ�д�
    */
   private String sydwhc[];

   /**
    * �Ƿ�������ҵ
    */
   private String isXzqy;

   /**
    * ��ҵ��λ�����ۼ���
    */
   private String sydwbqljs[];

   /**
    * ����ҵ��λ�걨�������б�
    */
   private List sydwsbbDefineList;

   /**
    * ��ҵ��λ����������3����׼�ĺ�
    */
   private String sydwpzwh3;

   /**
    * ��ҵ��λ����������4����׼�ĺ�
    */
   private String sydwpzwh4;

   /**
    * ��ҵ��λ����������5����׼�ĺ�
    */
   private String sydwpzwh5;

   /**
    * ��ҵ����ָ�����
    */
   private String qycwzbxh[];

   /**
    * ��ҵ����ָ����Ŀ����
    */
   private String qycwzbxmmc[];

   /**
    * ��ҵ����ָ���д�
    */
   private String qycwzbhc[];

   /**
    * ��ҵ����ָ�������
    */
   private String qycwzbncs[];

   /**
    * ��ҵ����ָ����ĩ��
    */
   private String qycwzbnms[];

   /**
    * ����ҵ����ָ���걨�������б�
    */
   private List qycwzbDefineList;

   /**
    * ��Ӫ�ɷ���ҵ���ʹ���
    */
   private String lygfdjzclxdm[];

   /**
    * ��Ӫ�ɷ���ҵ����
    */
   private String lygfqymc[];

   /**
    * ��Ӫ�ɷ���ҵ˰��
    */
   private String lygfsl[];

   /**
    * ��Ӫ�ɷ������ϢͶ������
    */
   private String lygflrgx[];

   /**
    * ��Ӫ�ɷ�Ӧ��˰���ö�
    */
   private String lygfynssde[];

   /**
    * ��Ӫ�ɷ�˰�տ۳���
    */
   private String lygfsskce[];

   /**
    * ��Ӫ�ɷ�Ӧ������˰��
    */
   private String lygfynsdse[];

   /**
    * ��Ӫ�ɷ�Ӧ������˰��
    */
   private String lygfybsdse[];

   /**
    * ��ҵ��˰��˰������
    * ��1�������¼�����ҵ˰��Ϊ15%
    * ��2�����˶�˰��
    * ��3��������˰��
    */
   private String qyzssllx;

   /**
    * ��ҵ��˰��˰��
    * �������ҵ��˰��˰������
    * ��1�������¼�����ҵ˰��Ϊ15%
    * ��2�����˶�˰��
    * ��3��������˰��
    */
   private String qyzssl;

   /**
    * ��ҵ����˰˰�ʣ���˰��˰Ŀ������ж������ҵ����˰˰��
    */

   /**
    * ��Ӫ�ɷ���ҵ˰��
    * �����ҵ��˰��˰������
    * Ϊ��1�������¼�����ҵ˰�ʣ���Ϊqyzssl
    * ����Ϊqysdssl
    */
   private String lygfqysl;

   /**
    * �Ʋ���ʧ�۳��� 24
    */
   private java.math.BigDecimal spzg24;

   /**
    * �ֲ���ǰ��ȿ��� 63
    */
   private java.math.BigDecimal spzg63;

   /**
    * ��˰�ļ���ת������ 69
    */
   private java.math.BigDecimal spzg69;

   /**
    * �о��²�Ʒ���¼������¹��շ��õֿ۶� 77
    */
   private java.math.BigDecimal spzg77;

   /**
    * ��������˰�� 81
    */
   private java.math.BigDecimal spzg81;
   /**
    * ������������豸Ͷ�ʵ���˰�� 82
    */
   private java.math.BigDecimal spzg82;

   /**
    * ˰��������ʼ����
    */
   private String skssksrq;
   /**
    * ˰��������ʼ����
    */
   private String skssjsrq;

   /**
    * ���������
    */
   private String jsjdm ;
   /**
    * �Ǽ�ע�����ʹ���
    */
   private String djzclxdm;
   /**
    * ��˰������
    */
   private String qymc;
   /**
    * ��ҵ��ϵ�绰
    */
   private String qylxdh;

   /***
    * �Ƿ���Ӧ˰���� 0:�ޣ�1����
    */
   private String sfyyssr = "1";

   /**
    * �ڴ����ʷ�ʽ�µ��Ƿ��Ǹ�����ҵ��0���� 1��
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
    * ʵ��ActionForm��validate����,��form�����ݽ������ݣ���Ҫ����ҳ���ύ���ɵģ�����?

    * �Խ��м�飬������ִ����򴴽�һ��ActionError���󣬲������ActionErrors�У�����?

    * ��null
    *
    * 1���걨�����ݲ���Ϊ��
    * 2�����еı����ۼ��������������ĩ�������������ֻ�Ϊ��
    * ....
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    * @return ActonErrors�������������򷵻�null
    */
   public ActionErrors validate(ActionMapping mapping, HttpServletRequest request)
   {
    return null;
   }

   /**
    * ����form�����ԣ�����ȫ�����ԣ��ύ�����¸�ֵ�����Բ���reset��
    *
    * 1�������е�����(xh xmmc hc bqljs ncs nms) �� null
    * 2����sydwpzwh3��4��5�� null
    * 3����l��Ӫ�ɷ����е����������� null
    *
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    */
   public void reset(ActionMapping mapping, HttpServletRequest request)
   {
       //�걨
       nbxmmc = null;
       nbhc = null;
       nbbqljs = null;

       //��ҵ��λ���������
       sydwxmmc = null;
       sydwhc = null;
       sydwbqljs = null;
       sydwpzwh3 = null;
       sydwpzwh4 = null;
       sydwpzwh5 = null;

       //��ҵ����ָ��
       qycwzbxmmc = null;
       qycwzbhc = null;
       qycwzbncs = null;
       qycwzbnms = null;

       //��Ӫ�ɷ�
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
     * ��ø�ʽΪyyyy-MM-dd�ĵ�ǰ����
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

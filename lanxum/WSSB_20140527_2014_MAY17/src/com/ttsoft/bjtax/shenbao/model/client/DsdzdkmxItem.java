package com.ttsoft.bjtax.shenbao.model.client;

import java.io.Serializable;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author stone
 * @version 1.0 2003-9-8
 */

public class DsdzdkmxItem implements Serializable
{
    public DsdzdkmxItem()
    {
    }
    //˰�ִ���
   String szdm;
   //��˰���
   String jsje;
   //˰��˰Ŀ����
   String szsmdm;
   //˰������
   String szmc;
   //��˰֤��
   String wszh;
   //ʵ��˰��
   String sjse;
   //˰��˰Ŀ����
   String szsmmc;
   //˰��
   String sl;
   //������������
   String bdzrmc;
   //��ע
   String bz;
   //Ʊ֤�������
   String pzzldm;

   public String getBdzrmc()
   {
       return bdzrmc;
   }

   public String getJsje()
   {
       return jsje;
   }

   public String getPzzldm()
   {
       return pzzldm;
   }

   public String getSjse()
   {
       return sjse;
   }

   public String getSl()
   {
       return sl;
   }

   public String getSzdm()
   {
       return szdm;
   }

   public String getSzmc()
   {
       return szmc;
   }

   public String getSzsmdm()
   {
       return szsmdm;
   }

   public String getSzsmmc()
   {
       return szsmmc;
   }

   public String getWszh()
   {
       return wszh;
   }

   public void setBdzrmc(String bdzrmc)
   {
       this.bdzrmc = bdzrmc;
   }

   public void setJsje(String jsje)
   {
       this.jsje = jsje;
   }

   public void setPzzldm(String pzzldm)
   {
       this.pzzldm = pzzldm;
   }

   public void setSjse(String sjse)
   {
       this.sjse = sjse;
   }

   public void setSl(String sl)
   {
       this.sl = sl;
   }

   public void setSzdm(String szdm)
   {
       this.szdm = szdm;
   }

   public void setSzmc(String szmc)
   {
       this.szmc = szmc;
   }

   public void setSzsmdm(String szsmdm)
   {
       this.szsmdm = szsmdm;
   }

   public void setSzsmmc(String szsmmc)
   {
       this.szsmmc = szsmmc;
   }

   public void setWszh(String wszh)
   {
       this.wszh = wszh;
   }
}
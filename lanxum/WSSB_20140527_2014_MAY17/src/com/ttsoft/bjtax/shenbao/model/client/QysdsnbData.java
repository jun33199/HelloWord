package com.ttsoft.bjtax.shenbao.model.client;

import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.*;

/**
 * ��ҵ����˰�걨����
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: ��ҵ����˰�걨����</p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class QysdsnbData implements java.io.Serializable
{
    NBData nbData = null;
    CwzbData cwzbData = null;
    SydwData sydwData = null;
    LygfData lygfData = null;
    Jm jm = null;

    //�Ǽǻ���������
    SWDJJBSJ djJbsj = null;

    /**
     *��ҵ����˰�걨������Ĺ��캯��
     */
    public QysdsnbData()
    {
        nbData = new NBData();
        cwzbData = new CwzbData();
        sydwData = new SydwData();
        lygfData = new LygfData();
    }

    /**
     * ���QysdsnbData.CwzbData����
     * @return QysdsnbData.CwzbData����
     */
    public CwzbData getCwzbData()
    {
        return cwzbData;
    }

    /**
     * ���QysdsnbData.LygfData����
     * @return QysdsnbData.LygfData����
     */
    public LygfData getLygfData()
    {
        return lygfData;
    }

    /**
     * ���QysdsnbData.NBData����
     * @return QysdsnbData.NBData����
     */
    public NBData getNbData()
    {
        return nbData;
    }

    /**
     * ���QysdsnbData.SydwData����
     * @return QysdsnbData.SydwData����
     */
    public SydwData getSydwData()
    {
        return sydwData;
    }

    public SWDJJBSJ getDjJbsj()
    {
        return djJbsj;
    }

    public void setDjJbsj(SWDJJBSJ djJbsj)
    {
        this.djJbsj = djJbsj;
    }

    public Jm getJm()
    {
        return jm;
    }
    public void setJm(Jm jm)
    {
        this.jm = jm;
    }

    /**
     * �걨data
     */
    public class NBData implements java.io.Serializable
    {
        private List defineList = null;
        //����걨�걨��Ķ����������
        public List getDefineList(){
            return this.defineList;
        }
        //�����걨�걨��Ķ����������
        public void setDefineList(List list){
            this.defineList = list;
        }

        private List nbData = null;
        //����걨�걨����걨����
        public List getNbData(){
            return this.nbData;
        }

        //�����걨�걨����걨����
        public void setNbData(List list){
            this.nbData = list;
        }
   }

   /**
    * ��ҵ��λdata
    */
   public class SydwData implements java.io.Serializable{
       private List defineList = null;
       //�����ҵ��λ��������걨��Ķ����������
       public List getDefineList(){
          return this.defineList;
       }
       //������ҵ��λ��������걨��Ķ����������
       public void setDefineList(List list){
         this.defineList = list;
       }

       private Sydwshttsrzb zbData = null;
       //**�����������
       public Sydwshttsrzb getSydwshttsrzb(){return this.zbData;}
       //������������
       public void setSydwshttsrzb(Sydwshttsrzb newData){
          this.zbData = newData;
       }

       private List mxList = null;
       //�����ϸ����
       public List getMxList(){
          return this.mxList;
       }
       //������ϸ����
       public void setMxList(List list){
         this.mxList = list;
       }

       private List whList = null;
       //����ĺ�����
       public List getWhList(){
          return this.whList;
       }
       //�����ĺ�����
       public void setWhList(List list){
         this.whList = list;
       }
   }

   /**
    * ��ҵ����ָ��data
    */
   public class CwzbData implements java.io.Serializable{
       private List defineList = null;
       //��ò���ָ���걨��Ķ����������
       public List getDefineList(){
          return this.defineList;
       }
       //���ò���ָ���걨��Ķ����������
       public void setDefineList(List list){
         this.defineList = list;
       }

       //��ò���ָ���걨��Ķ����������
       private List cwzbData = null;

       //��ò���ָ���걨����걨����
       public List getCwzbData(){
          return this.cwzbData;
       }
       //���ò���ָ���걨����걨����
       public void setCwzbData(List list){
         this.cwzbData = list;
       }
   }

   /**
    * ��Ӫ�ɷ�data
    */
   public class LygfData implements java.io.Serializable{
      private List lyData = null;
      //������Ӫ�ɷ���ҵ�걨����걨����
      public void setLygfData(List data){this.lyData = data;}
      //�����Ӫ�ɷ���ҵ�걨����걨����
      public List getLygfData(){return this.lyData;}
   }
}
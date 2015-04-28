package com.ttsoft.bjtax.shenbao.model.client;

import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sydwshttsrzb;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.model.domain.*;

/**
 * 企业所得税年报数据
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: 企业所得税年报数据</p>
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

    //登记基本数据数
    SWDJJBSJ djJbsj = null;

    /**
     *企业所得税年报数据类的构造函数
     */
    public QysdsnbData()
    {
        nbData = new NBData();
        cwzbData = new CwzbData();
        sydwData = new SydwData();
        lygfData = new LygfData();
    }

    /**
     * 获得QysdsnbData.CwzbData对象
     * @return QysdsnbData.CwzbData对象
     */
    public CwzbData getCwzbData()
    {
        return cwzbData;
    }

    /**
     * 获得QysdsnbData.LygfData对象
     * @return QysdsnbData.LygfData对象
     */
    public LygfData getLygfData()
    {
        return lygfData;
    }

    /**
     * 获得QysdsnbData.NBData对象
     * @return QysdsnbData.NBData对象
     */
    public NBData getNbData()
    {
        return nbData;
    }

    /**
     * 获得QysdsnbData.SydwData对象
     * @return QysdsnbData.SydwData对象
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
     * 年报data
     */
    public class NBData implements java.io.Serializable
    {
        private List defineList = null;
        //获得年报申报表的定义代码数据
        public List getDefineList(){
            return this.defineList;
        }
        //设置年报申报表的定义代码数据
        public void setDefineList(List list){
            this.defineList = list;
        }

        private List nbData = null;
        //获得年报申报表的申报数据
        public List getNbData(){
            return this.nbData;
        }

        //设置年报申报表的申报数据
        public void setNbData(List list){
            this.nbData = list;
        }
   }

   /**
    * 事业单位data
    */
   public class SydwData implements java.io.Serializable{
       private List defineList = null;
       //获得事业单位社会团体申报表的定义代码数据
       public List getDefineList(){
          return this.defineList;
       }
       //设置事业单位社会团体申报表的定义代码数据
       public void setDefineList(List list){
         this.defineList = list;
       }

       private Sydwshttsrzb zbData = null;
       //**获得主表数据
       public Sydwshttsrzb getSydwshttsrzb(){return this.zbData;}
       //设置主表数据
       public void setSydwshttsrzb(Sydwshttsrzb newData){
          this.zbData = newData;
       }

       private List mxList = null;
       //获得明细数据
       public List getMxList(){
          return this.mxList;
       }
       //设置明细数据
       public void setMxList(List list){
         this.mxList = list;
       }

       private List whList = null;
       //获得文号数据
       public List getWhList(){
          return this.whList;
       }
       //设置文号数据
       public void setWhList(List list){
         this.whList = list;
       }
   }

   /**
    * 企业财务指标data
    */
   public class CwzbData implements java.io.Serializable{
       private List defineList = null;
       //获得财务指标申报表的定义代码数据
       public List getDefineList(){
          return this.defineList;
       }
       //设置财务指标申报表的定义代码数据
       public void setDefineList(List list){
         this.defineList = list;
       }

       //获得财务指标申报表的定义代码数据
       private List cwzbData = null;

       //获得财务指标申报表的申报数据
       public List getCwzbData(){
          return this.cwzbData;
       }
       //设置财务指标申报表的申报数据
       public void setCwzbData(List list){
         this.cwzbData = list;
       }
   }

   /**
    * 联营股份data
    */
   public class LygfData implements java.io.Serializable{
      private List lyData = null;
      //设置联营股份企业申报表的申报数据
      public void setLygfData(List data){this.lyData = data;}
      //获得联营股份企业申报表的申报数据
      public List getLygfData(){return this.lyData;}
   }
}
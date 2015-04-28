//Source file: F:\\Generated Code\\com\\ttsoft\\bjtax\\shenbao\\sbzl\\qysdsnb\\web\\QycwzbForm.java

package com.ttsoft.bjtax.shenbao.sbzl.qysdsnb.web;

import com.ttsoft.framework.form.BaseForm;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.shenbao.model.client.QysdsnbData;
import com.ttsoft.bjtax.shenbao.sbzl.SbzlBaseForm;

/**
 * 企业财务指标actionform
 */
public class QycwzbForm extends SbzlBaseForm
{

   /**
    * 序号
    */
   private String qycwzbxh[];

   /**
    * 项目名称
    */
   private String qycwzbxmmc[];

   /**
    * 行次
    */
   private String qycwzbhc[];

   /**
    * 年初数
    */
   private String qycwzbncs[];

   /**
    * 年末数
    */
   private String qycwzbnms[];

   /**
    * 财务负责人
    */
   private String cwfzr;

   /**
    * 办税人员
    */
   private String bsry;


   /**
    * 计算机代码
    */
   private String jsjdm ;


   /**
    * 《企业财务指标申报表》定义列表
    */
   private List qycwzbDefineList;


   public QycwzbForm()
   {

   }

   /**
    * 实现ActionForm的validate方法,对form的数据进行数据（主要是由页面提交生成的）的有?

    * 性进行检查，如果发现错误则创建一个ActionError对象，并放入道ActionErrors中，否则?

    * 回null
    *
    * 1、序号、行次、项目名称不能为空
    * 2、本期累计数、年初数和年末数都必须是数字或为空
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
    * 将所有的数组(xh xmmc hc bqljs ncs nms) 置 null
    *
    *
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    */
   public void reset(ActionMapping mapping, HttpServletRequest request)
   {
       //企业财务指标
       qycwzbxmmc = null;
       qycwzbhc = null;
       qycwzbncs = null;
       qycwzbnms = null;

   }

    public String getBsry() {
        return bsry;
    }
    public String getCwfzr() {
        return cwfzr;
    }
    public List getQycwzbDefineList() {
        return qycwzbDefineList;
    }
    public String[] getQycwzbhc() {
        return qycwzbhc;
    }
    public String[] getQycwzbnms() {
        return qycwzbnms;
    }
    public String[] getQycwzbxh() {
        return qycwzbxh;
    }
    public String[] getQycwzbxmmc() {
        return qycwzbxmmc;
    }
    public String[] getQycwzbncs() {
        return qycwzbncs;
    }
    public void setBsry(String bsry) {
        this.bsry = bsry;
    }
    public void setCwfzr(String cwfzr) {
        this.cwfzr = cwfzr;
    }
    public void setQycwzbDefineList(List qycwzbDefineList) {
        this.qycwzbDefineList = qycwzbDefineList;
    }
    public void setQycwzbhc(String[] qycwzbhc) {
        this.qycwzbhc = qycwzbhc;
    }
    public void setQycwzbncs(String[] qycwzbncs) {
        this.qycwzbncs = qycwzbncs;
    }
    public void setQycwzbnms(String[] qycwzbnms) {
        this.qycwzbnms = qycwzbnms;
    }
    public void setQycwzbxh(String[] qycwzbxh) {
        this.qycwzbxh = qycwzbxh;
    }
    public void setQycwzbxmmc(String[] qycwzbxmmc) {
        this.qycwzbxmmc = qycwzbxmmc;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    //税款所属开始日期
    private String skssksrq;
    //税款所属结束日期
    private String skssjsrq;

    //纳税人名称
    private String qymc;
    //企业经营地址联系电话
    private String qylxdh;

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


}

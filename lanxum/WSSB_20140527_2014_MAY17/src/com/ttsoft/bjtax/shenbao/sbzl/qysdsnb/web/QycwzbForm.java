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
 * ��ҵ����ָ��actionform
 */
public class QycwzbForm extends SbzlBaseForm
{

   /**
    * ���
    */
   private String qycwzbxh[];

   /**
    * ��Ŀ����
    */
   private String qycwzbxmmc[];

   /**
    * �д�
    */
   private String qycwzbhc[];

   /**
    * �����
    */
   private String qycwzbncs[];

   /**
    * ��ĩ��
    */
   private String qycwzbnms[];

   /**
    * ��������
    */
   private String cwfzr;

   /**
    * ��˰��Ա
    */
   private String bsry;


   /**
    * ���������
    */
   private String jsjdm ;


   /**
    * ����ҵ����ָ���걨�������б�
    */
   private List qycwzbDefineList;


   public QycwzbForm()
   {

   }

   /**
    * ʵ��ActionForm��validate����,��form�����ݽ������ݣ���Ҫ����ҳ���ύ���ɵģ�����?

    * �Խ��м�飬������ִ����򴴽�һ��ActionError���󣬲������ActionErrors�У�����?

    * ��null
    *
    * 1����š��дΡ���Ŀ���Ʋ���Ϊ��
    * 2�������ۼ��������������ĩ�������������ֻ�Ϊ��
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
    * �����е�����(xh xmmc hc bqljs ncs nms) �� null
    *
    *
    *
    * @param mapping The mapping used to select this instance
    * @param request The servlet request we are processing
    */
   public void reset(ActionMapping mapping, HttpServletRequest request)
   {
       //��ҵ����ָ��
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

    //˰��������ʼ����
    private String skssksrq;
    //˰��������������
    private String skssjsrq;

    //��˰������
    private String qymc;
    //��ҵ��Ӫ��ַ��ϵ�绰
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


}

package com.creationstar.bjtax.qsgl.VisionLogic.form;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.util.Arith;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.util.Debug;
import com.creationstar.bjtax.qsgl.util.StringUtils;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ������
 * @version 1.0
 */
public class YggyzfForm extends BaseForm {
    public YggyzfForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * �Ƿ��Ǹ���
     */
    private boolean person = false;

    /**
     * �Ƿ����µ�¼��,Ҳ���ǵ�һ��ʹ�øò�Ǩ��Ϣ
     * ����ӿ��в�ѯ�����ľ���false
     */
    private boolean first = true;


    /**
     * �ѹ�����ס��Ȩ��֤���
     */
    public String yggyzfqszsh;
    //�����־ֵļ�ƣ��������յ�ļ�ƣ����������յ㣩��������������ȣ���λȫ�־���ˮ��
    private String fjmc;
    private String zsdmc;
    private String nd;
    private String lsh;
        //��or��-ס������ add by zhangyj 20090220
        private String zflx;


    /**
     * �����ַ
     */
    public String zldz;
    /**
     * ���ۺ�ͬ����Լ��ǩ��ʱ��
     */
    public String cshtqdsj;
    /**
     * �������
     */
    public String jzmj;
    /**
     * �ɽ��۸�
     */
    public String cjjg;
    /**
     * ���οɵֿ۶�
     */
    public String bckdke;
    /**
     * ���εֿ۶�
     */
    public String bcdke;
    /**
     * ʣ���
     */
    public String sye;

    /**
     *ʣ��������־
     */
    public String syeywbz = "00";
      /**
       * ����Ȩ��֤��� add by zhangyj 20090219
       */
      public String fwqszsh;      


    /**
     * ��ñ��εֿ۶�
     * @return String
     */
    public String getBcdke() {
        return bcdke;
    }

    /**
     * ��ñ��οɵֿ۶�
     * @return String
     */
    public String getBckdke() {
        return bckdke;
    }

    /**
     * ��óɽ��۸�
     * @return String
     */
    public String getCjjg() {
        return cjjg;
    }

    /**
     * ��ó��ۺ�ͬ����Լ��ǩ��ʱ��
     * @return String
     */
    public String getCshtqdsj() {
        return cshtqdsj;
    }

    /**
     * ��ý������
     * @return String
     */
    public String getJzmj() {
        return jzmj;
    }

    /**
     * ���ʣ���
     * @return String
     */
    public String getSye() {
        return sye;
    }

    /**
     * ����ѹ�����ס��Ȩ��֤���
     * @return String
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * ��������ַ
     * @return String
     */
    public String getZldz() {
        return zldz;
    }
        /**
         * ��÷���Ȩ��֤���
         * @return String
         */
        public String getFwqszsh() {
            return fwqszsh;
        }        

    public boolean isPerson() {
        return person;
    }

    public boolean isFirst() {
        return first;
    }

    public String getFjmc() {
        return fjmc;
    }

    public String getLsh() {
        return lsh;
    }

    public String getNd() {
        return nd;
    }

    public String getZsdmc() {
        return zsdmc;
    }

    public String getSyeywbz() {
        return syeywbz;
    }

    public String getZflx()
    {
        return zflx;
    }
    /**
     * ���ñ��εֿ۶�
     */
    public void setBcdke(String bcdke) {
        this.bcdke = bcdke;
    }

    /**
     * ���ñ��οɵֿ۶�
     */
    public void setBckdke(String bckdke) {
        this.bckdke = bckdke;
    }

    /**
     * ���óɽ��۸�
     * @param cjjg String
     */
    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    /**
     * ���ó��ۺ�ͬ����Լ��ǩ��ʱ��
     * @param cshtqdsj String
     */
    public void setCshtqdsj(String cshtqdsj) {
        this.cshtqdsj = cshtqdsj;
    }

    /**
     *  ���ý������
     * @param jzmj String
     */
    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    /**
     * ����ʣ���
     * @param sye String
     */
    public void setSye(String sye) {
        this.sye = sye;
    }

    /**
     * �����ѹ�����ס��Ȩ��֤���
     * @param yggyzfqszsh String
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * ���������ַ
     * @param zldz String
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }
        /**
         * ���÷���Ȩ��֤���
         * @param fwqszsh String
         */
        public void setFwqszsh(String fwqszsh) {
            this.fwqszsh = fwqszsh;
        }        

    public void setPerson(boolean person) {
        this.person = person;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public void setFjmc(String fjmc) {
        this.fjmc = fjmc;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public void setZsdmc(String zsdmc) {
        this.zsdmc = zsdmc;
    }

    public void setSyeywbz(String syeywbz) {
        this.syeywbz = syeywbz;
    }

    public void setZflx(String zflx)
    {
        this.zflx = zflx;
    }    
    /**
     * ��YggyzfGrForm�е����ݷŵ�jsblgyzf��Vo�У�Ȼ�󷵻�jsblgyzf
     * @return String
     */

    public Object getData() {
        Jsblgyzf jsblgyzf = new Jsblgyzf();
        jsblgyzf.sbbh = this.sbbh;
        jsblgyzf.yggyzfqszsh = "��" + this.fjmc + "��˰" + this.zsdmc + "��" +this.zflx+ "��" + nd +
                               "��" + lsh + "��";
        jsblgyzf.zldz = this.zldz;
        jsblgyzf.qdsj = DataConvert.String2Timestamp(this.cshtqdsj);
        //�޸��ѹ�����ס����ʾΪ��λС�� modify by fujx��20081129,start
        if(null!=StringUtils.killNull2(this.jzmj)){
             jsblgyzf.jzmj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                     jzmj,
                     3));
         }else{

         }
         //�޸��ѹ�����ס����ʾΪ��λС�� modify by fujx��20081129,end
        jsblgyzf.cjjg = DataConvert.String2BigDecimal(this.cjjg);
        jsblgyzf.sye = DataConvert.String2BigDecimal(this.sye);
        jsblgyzf.bcdke = DataConvert.String2BigDecimal(this.bcdke);
        if (this.syeywbz.equals("on")) {
            jsblgyzf.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblgyzf.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }
        Debug.out("YggyzfGrForm cjjg: " + jsblgyzf.cjjg);
           jsblgyzf.fwqszsh = this.fwqszsh;

        return jsblgyzf;
    }

    /**Ϊ���¹���ס����Ϣ��ȡס����Ϣ����
     * ��YggyzfGrForm�е����ݷŵ�jsblgyzf��Vo�У�Ȼ�󷵻�jsblgyzf
     * @return Object
     */

    public Object getDataForUpdate() {
        Jsblgyzf jsblgyzf = new Jsblgyzf();
        jsblgyzf.sbbh = this.sbbh;
        jsblgyzf.yggyzfqszsh = this.yggyzfqszsh;
        jsblgyzf.zldz = this.zldz;
        jsblgyzf.qdsj = DataConvert.String2Timestamp(this.cshtqdsj);
        jsblgyzf.jzmj = DataConvert.String2BigDecimal(this.jzmj);
        jsblgyzf.cjjg = DataConvert.String2BigDecimal(this.cjjg);
        jsblgyzf.sye = DataConvert.String2BigDecimal(this.sye);
        jsblgyzf.bcdke = DataConvert.String2BigDecimal(this.bcdke);
        if (this.syeywbz.equals("on")) {
            jsblgyzf.syeywbz = Constants.JSBL_SYEYWBZ_YONGWAN;
        } else {
            jsblgyzf.syeywbz = Constants.JSBL_SYEYWBZ_WEIYONGWAN;
        }

        Debug.out("YggyzfGrForm cjjg: " + jsblgyzf.cjjg);
              jsblgyzf.fwqszsh = this.fwqszsh;

        return jsblgyzf;
    }

    /**
     * ��jsblgyzf �е����ݷŵ�YggyzfGrForm��
     */

    public void setData(Jsblgyzf jsblgyzf) {
        this.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
        this.zldz = jsblgyzf.zldz;
        this.cshtqdsj = DataConvert.TimeStamp2String(jsblgyzf.qdsj);

        this.jzmj = DataConvert.BigDecimal2String(jsblgyzf.jzmj, 2, false);
        this.cjjg = DataConvert.BigDecimal2String(jsblgyzf.cjjg, 2, false);
        this.sye = DataConvert.BigDecimal2String(jsblgyzf.sye, 2, false);
        this.bckdke = DataConvert.BigDecimal2String(jsblgyzf.sye, 2, false);
        this.bcdke = DataConvert.BigDecimal2String(jsblgyzf.bcdke, 2, false);
        if (jsblgyzf.syeywbz.equals(Constants.JSBL_SYEYWBZ_YONGWAN)) {
            this.syeywbz = "on";
        } else {
            this.syeywbz = jsblgyzf.syeywbz;
        }
          this.fwqszsh = jsblgyzf.fwqszsh;
    }

    private void jbInit() throws Exception {
    }
}

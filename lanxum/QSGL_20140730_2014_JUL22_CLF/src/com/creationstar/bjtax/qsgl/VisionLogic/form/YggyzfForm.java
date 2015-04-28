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
 * @author 卫军丽
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
     * 是否是个人
     */
    private boolean person = false;

    /**
     * 是否是新的录入,也就是第一次使用该拆迁信息
     * 如果从库中查询查来的就是false
     */
    private boolean first = true;


    /**
     * 已购公有住房权属证书号
     */
    public String yggyzfqszsh;
    //昌－分局的简称；房－征收点的简称（房管所征收点）；２００５－年度；５位全分局流水号
    private String fjmc;
    private String zsdmc;
    private String nd;
    private String lsh;
        //公or经-住房类型 add by zhangyj 20090220
        private String zflx;


    /**
     * 座落地址
     */
    public String zldz;
    /**
     * 出售合同（契约）签订时间
     */
    public String cshtqdsj;
    /**
     * 建筑面积
     */
    public String jzmj;
    /**
     * 成交价格
     */
    public String cjjg;
    /**
     * 本次可抵扣额
     */
    public String bckdke;
    /**
     * 本次抵扣额
     */
    public String bcdke;
    /**
     * 剩余额
     */
    public String sye;

    /**
     *剩余额用完标志
     */
    public String syeywbz = "00";
      /**
       * 房屋权属证书号 add by zhangyj 20090219
       */
      public String fwqszsh;      


    /**
     * 获得本次抵扣额
     * @return String
     */
    public String getBcdke() {
        return bcdke;
    }

    /**
     * 获得本次可抵扣额
     * @return String
     */
    public String getBckdke() {
        return bckdke;
    }

    /**
     * 获得成交价格
     * @return String
     */
    public String getCjjg() {
        return cjjg;
    }

    /**
     * 获得出售合同（契约）签订时间
     * @return String
     */
    public String getCshtqdsj() {
        return cshtqdsj;
    }

    /**
     * 获得建筑面积
     * @return String
     */
    public String getJzmj() {
        return jzmj;
    }

    /**
     * 获得剩余额
     * @return String
     */
    public String getSye() {
        return sye;
    }

    /**
     * 获得已购公有住房权属证书号
     * @return String
     */
    public String getYggyzfqszsh() {
        return yggyzfqszsh;
    }

    /**
     * 获得座落地址
     * @return String
     */
    public String getZldz() {
        return zldz;
    }
        /**
         * 获得房屋权属证书号
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
     * 设置本次抵扣额
     */
    public void setBcdke(String bcdke) {
        this.bcdke = bcdke;
    }

    /**
     * 设置本次可抵扣额
     */
    public void setBckdke(String bckdke) {
        this.bckdke = bckdke;
    }

    /**
     * 设置成交价格
     * @param cjjg String
     */
    public void setCjjg(String cjjg) {
        this.cjjg = cjjg;
    }

    /**
     * 设置出售合同（契约）签订时间
     * @param cshtqdsj String
     */
    public void setCshtqdsj(String cshtqdsj) {
        this.cshtqdsj = cshtqdsj;
    }

    /**
     *  设置建筑面积
     * @param jzmj String
     */
    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    /**
     * 设置剩余额
     * @param sye String
     */
    public void setSye(String sye) {
        this.sye = sye;
    }

    /**
     * 设置已购公有住房权属证书号
     * @param yggyzfqszsh String
     */
    public void setYggyzfqszsh(String yggyzfqszsh) {
        this.yggyzfqszsh = yggyzfqszsh;
    }

    /**
     * 设置座落地址
     * @param zldz String
     */
    public void setZldz(String zldz) {
        this.zldz = zldz;
    }
        /**
         * 设置房屋权属证书号
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
     * 将YggyzfGrForm中的数据放到jsblgyzf的Vo中，然后返回jsblgyzf
     * @return String
     */

    public Object getData() {
        Jsblgyzf jsblgyzf = new Jsblgyzf();
        jsblgyzf.sbbh = this.sbbh;
        jsblgyzf.yggyzfqszsh = "京" + this.fjmc + "地税" + this.zsdmc + "契" +this.zflx+ "字" + nd +
                               "第" + lsh + "号";
        jsblgyzf.zldz = this.zldz;
        jsblgyzf.qdsj = DataConvert.String2Timestamp(this.cshtqdsj);
        //修改已购公有住房显示为三位小数 modify by fujx，20081129,start
        if(null!=StringUtils.killNull2(this.jzmj)){
             jsblgyzf.jzmj = DataConvert.String2BigDecimal(Arith.roundStr(this.
                     jzmj,
                     3));
         }else{

         }
         //修改已购公有住房显示为三位小数 modify by fujx，20081129,end
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

    /**为更新公有住房信息获取住房信息对象
     * 将YggyzfGrForm中的数据放到jsblgyzf的Vo中，然后返回jsblgyzf
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
     * 将jsblgyzf 中的数据放到YggyzfGrForm中
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

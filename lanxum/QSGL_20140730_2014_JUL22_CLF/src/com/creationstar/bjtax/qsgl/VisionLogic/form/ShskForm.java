package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.ShskBo;
import com.creationstar.bjtax.qsgl.util.DataConvert;

public class ShskForm extends BaseForm {
    public ShskForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 缴款方式代码
     */
    private String jkfsdm;

    /**
     * 计算机代码
     */
    private String jsjdm = "";

    /**
     * 计算机代码
     */
    private String xsjsjdm[];

    /**
     * 纳税人名称
     */
    private String nsrmc = "";

    /**
     * 土地房屋坐落地址
     */
    private String tdfwzldz;

    /**
     * 成交价格人民币
     */
    private String cjjgrmb;

    /**
     * 备注
     */
    private String bz;

    /**
     * 税率
     */
    private String sl;

    /**
     * 计征税额
     */
    private String jsje;

    /**
     * 计税依据
     */
    private String jsyj;

    /**
     * 应纳税额
     */
    private String ynse;

    /**
     * 拆迁减免金额
     */
    private String cqjmje;

    /**
     * 普通住宅减税金额
     */
    private String ptzzjsje;

    /**
     * 经济适用房减税金额
     */
    private String jjsyfjsje;


    /**
     * 纳税人列表
     */
    public List nsrList = new ArrayList();

    /**
     * 对应的是显示的核定通知书信息
     */
    private ArrayList hdList = new ArrayList();

    /**
     * 是否已经审核
     */
    private boolean checked = false;

    /**
     * 是否是批量导入数据
     */
    private boolean pc = false;

    public String getBz() {
        return bz;
    }

    public String getCjjgrmb() {
        return cjjgrmb;
    }

    public String getCqjmje() {
        return cqjmje;
    }

    public ArrayList getHdList() {
        return hdList;
    }

    public String getJsje() {
        return jsje;
    }

    public String getPtzzjsje() {
        return ptzzjsje;
    }

    public String getSl() {
        return sl;
    }

    public String getTdfwzldz() {
        return tdfwzldz;
    }

    public String getYnse() {
        return ynse;
    }

    public boolean isChecked() {
        return checked;
    }

    public String getJsyj() {
        return jsyj;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public boolean isPc() {
        return pc;
    }

    public void setYnse(String ynse) {
        this.ynse = ynse;
    }

    public void setTdfwzldz(String tdfwzldz) {
        this.tdfwzldz = tdfwzldz;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setPtzzjsje(String ptzzjsje) {
        this.ptzzjsje = ptzzjsje;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setHdList(ArrayList hdList) {
        this.hdList = hdList;
    }

    public void setCqjmje(String cqjmje) {
        this.cqjmje = cqjmje;
    }

    public void setCjjgrmb(String cjjgrmb) {
        this.cjjgrmb = cjjgrmb;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public void setJsyj(String jsyj) {
        this.jsyj = jsyj;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    /**
     * 获取一个ShskBo对象
     * 复核：主要获取sbbh,进行复核使用
     */
    public Object getData() {
        ShskBo shskbo = new ShskBo();
        shskbo.setBz(this.bz);
        shskbo.setCjjgrmb(DataConvert.String2BigDecimal(this.cjjgrmb));
        shskbo.setCqjmje(DataConvert.String2BigDecimal(this.cqjmje));
        shskbo.setHdList(this.hdList);
        shskbo.setJsje(DataConvert.String2BigDecimal(this.jsje));

        shskbo.setNsrmc(this.nsrmc);

        shskbo.setPtzzjsje(DataConvert.String2BigDecimal(this.ptzzjsje));
        shskbo.setSbbh(this.sbbh);
        shskbo.setSl(DataConvert.String2BigDecimal(this.sl));
        shskbo.setTdfwzldz(this.tdfwzldz);
        shskbo.setYnse(DataConvert.String2BigDecimal(this.ynse));
        shskbo.setChecked(this.checked);
        shskbo.setJsyj(DataConvert.String2BigDecimal(this.jsyj));
        shskbo.setJsfsdm(this.jkfsdm);
        shskbo.setPc(this.pc);
        shskbo.setNsrList(this.getNsrList());
        return shskbo;
    }

    /**
     * 将一个ShskBo对象保存到form中，显示时取值（包括已复核和未复核的情况）
     * 未复核：只需设置纳税人名称
     * 已复核（wsz jks）：需设置纳税人列表
     */
    public void setData(Object obj) {
        ShskBo shskbo = (ShskBo) obj;
        this.bz = shskbo.getBz();
        this.cjjgrmb = DataConvert.BigDecimal2String(shskbo.getCjjgrmb(), 2);
        this.cqjmje = DataConvert.BigDecimal2String(shskbo.getCqjmje(), 2);
        this.hdList = shskbo.getHdList();
        this.jsje = DataConvert.BigDecimal2String(shskbo.getJsje(), 2);
        this.nsrmc = shskbo.getNsrmc();
        this.ptzzjsje = DataConvert.BigDecimal2String(shskbo.getPtzzjsje(), 2);
        //增加经济适用房减税金额
        this.jjsyfjsje = DataConvert.BigDecimal2String(shskbo.getJjsyfjsje(), 2);

        this.sbbh = shskbo.getSbbh();
        this.sl = DataConvert.BigDecimal2String(shskbo.getSl(), 2);
        this.tdfwzldz = shskbo.getTdfwzldz();
        this.ynse = DataConvert.BigDecimal2String(shskbo.getYnse(), 2);
        this.jsyj = DataConvert.BigDecimal2String(shskbo.getJsyj(), 2);
        this.checked = shskbo.isChecked();
        this.jkfsdm = shskbo.getJsfsdm();
        this.pc = shskbo.isPc();
        this.nsrList = shskbo.getNsrList();
        //复核完毕时，要清空数据域
        if (this.checked && shskbo.getYnse().doubleValue() > 0) {
            this.jsjdm = "";
            this.xsjsjdm = null;
        }
    }

    /**
     * @return Returns the nsrList.
     */
    public List getNsrList() {
        return nsrList;
    }

    /**
     * @param nsrList The nsrList to set.
     */
    public void setNsrList(List nsrList) {
        this.nsrList = nsrList;
    }

    /**
     * @return Returns the jsjdm.
     */
    public String getJsjdm() {
        return jsjdm;
    }

    /**
     * @param jsjdm The jsjdm to set.
     */
    public void setJsjdm(String jsjdm) {
        this.jsjdm = jsjdm;
    }

    /**
     * @return Returns the nsrmc.
     */
    public String getNsrmc() {
        return nsrmc;
    }

    /**
     * @param nsrmc The nsrmc to set.
     */
    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }

    /**
     * @return Returns the xsjsjdm.
     */
    public String[] getXsjsjdm() {
        return xsjsjdm;
    }

    public String getJjsyfjsje() {
        return jjsyfjsje;
    }

    /**
     * @param xsjsjdm The xsjsjdm to set.
     */
    public void setXsjsjdm(String[] xsjsjdm) {
        this.xsjsjdm = xsjsjdm;
    }

    public void setJjsyfjsje(String jjsyfjsje) {
        this.jjsyfjsje = jjsyfjsje;
    }

    private void jbInit() throws Exception {
    }

}

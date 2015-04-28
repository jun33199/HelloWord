package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.FwjhxxBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;

public class SbxxForm extends BaseForm {
    public SbxxForm() {
        try {
            jbInit();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 增加条形码的输入字符串
     * author：付江霞
     */
    private String inputStr;

    /**
     * 判断是否来自补录
     */
    private boolean bl = false;

    /**
     * 申报日期
     */
    private String sbrq;

    private String cqrJs = Constants.CQRJS_INIT;

    /**
     * 批次号
     */

    private String pch;

    /**
     * 打印申报编号
     */

    private String printSbbh;
    /**
     * 页面是否提示信息
     */
    private boolean alert = false;

    /**
     * 页面提示信息
     */
    private String alertMessage = "";

    /**
     * 当前编辑的房屋信息在其arrayList中的位置
     */
    private int fwindex = -1;

    /**
     * 当前编辑的房屋信息类别
     */
    private int fwtype = 0; //1:拆迁；2:共有住房

    /**
     * 缴款方式列表
     */
    private ArrayList jkfsList;

    /**
     * 身份证件类型列表
     */
    public ArrayList sfzjlxList = new ArrayList();

    /**
     * 纳税人类型列表
     */
    public ArrayList nsrlxList = new ArrayList();

    /**
     * 开户银行列表
     */
    public ArrayList khyhList = new ArrayList();

    /**
     * 国籍列表
     */
    public ArrayList gjdqList;

    /**
     * 纳税人列表
     */
    public List nsrList = new ArrayList();

    /**
     * 是否增加了房屋基本信息
     */
    private boolean addedTufwxx = false;

    /**
     * 是否增加了房屋交换信息
     */
    private boolean addedFwjhxx = false;

    /**
     * 减免税金额
     */
    public String jmsje;

    /**
     * 申报主表数据
     */
    private Sbzb voSbzb = null;

    /**
     * 审批结果信息
     */
    private Spjgxx voSpjgxx = null;

    /**
     * 房屋基本信息
     */
    private Tufwxx voTufwxx = null;

    /**
     * 个人信息
     */
    private Grxx voGrxx = null;

    /**
     * 非个人信息
     */
    private Fgrxx voFgrxx = null;

    /**
     * 房屋交换信息
     */
    private FwjhxxBo voFwjh = null;

    /**
     * 非个人信息
     */
    private JghdsjBo voJghdxx = null;

    /**
     * 两个个ArrayList作为房屋土地基本信息、拆迁、已购公有住房
     * 每个ArrayList中，就是相对应的Vo（ArrayList中的对象）
     */
    private ArrayList cqList = new ArrayList();
    private ArrayList gyzfList = new ArrayList();
    /**
     * 更新列表中的拆迁信息
     */
    public void updateFwxx(Object obj) {
        if (this.fwtype == 1) { //拆迁信息
            this.cqList.set(fwindex, obj);
        } else if (this.fwtype == 2) { //共有住房
            this.gyzfList.set(fwindex, obj);
        }

    }

//  保存取数
    public Object getData(List l) {
        SbxxBo bo = new SbxxBo();
        bo.setSbbh(this.sbbh);
        bo.setVoSbzb(this.voSbzb);
        this.voSpjgxx.setJmsje(DataConvert.String2BigDecimal(this.jmsje));
        bo.setVoSpjgxx(this.voSpjgxx);
        bo.setVoTufwxx(this.voTufwxx);
        bo.setVoFgrxx(this.voFgrxx);
        //获得客户端的数据--
        this.setCqrJs(ActionUtil.displayMNsrDS(l));
        this.setNsrList(l);

        bo.setNsrList(l);
        bo.setVoFwjh(this.voFwjh);
        bo.setCqList(this.cqList);
        bo.setGyzfList(this.gyzfList);
        return bo;
    }


//修改显示取数
    public Object getData() {
        SbxxBo bo = new SbxxBo();
        bo.setSbbh(this.sbbh);
        bo.setVoSbzb(this.voSbzb);
        this.voSpjgxx.setJmsje(DataConvert.String2BigDecimal(this.jmsje));
        bo.setVoSpjgxx(this.voSpjgxx);
        bo.setVoTufwxx(this.voTufwxx);
        bo.setVoFgrxx(this.voFgrxx);
        bo.setNsrList(this.getNsrList());
        //获得客户端的数据--
        List l = this.getNsrList();
        this.setCqrJs(ActionUtil.displayMNsrDS(l));

        bo.setVoFwjh(this.voFwjh);
        bo.setCqList(this.cqList);
        bo.setGyzfList(this.gyzfList);
        return bo;
    }

    public ArrayList getJkfsList() {
        return jkfsList;
    }

    public ArrayList getSfzjlxList() {
        return sfzjlxList;
    }

    public ArrayList getNsrlxList() {
        return nsrlxList;
    }

    public int getFwindex() {
        return fwindex;
    }

    public int getFwtype() {
        return fwtype;
    }

    public boolean isAddedFwjhxx() {
        return addedFwjhxx;
    }

    public boolean isAddedTufwxx() {
        return addedTufwxx;
    }

    public ArrayList getCqList() {
        return cqList;
    }

    public ArrayList getGyzfList() {
        return gyzfList;
    }

    public Fgrxx getVoFgrxx() {
        return voFgrxx;
    }

    public FwjhxxBo getVoFwjh() {
        return voFwjh;
    }

    public Grxx getVoGrxx() {
        return voGrxx;
    }

    public Sbzb getVoSbzb() {
        return voSbzb;
    }

    public Tufwxx getVoTufwxx() {
        return voTufwxx;
    }

    public ArrayList getKhyhList() {
        return khyhList;
    }

    public ArrayList getGjdqList() {
        return gjdqList;
    }

    public Spjgxx getVoSpjgxx() {
        return voSpjgxx;
    }

    public String getJmsje() {
        return jmsje;
    }

    public boolean isAlert() {
        return alert;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public String getPch() {
        return pch;
    }

    public String getSbrq() {
        return sbrq;
    }

    public boolean isBl() {
        return bl;
    }

    /**
     * 将JsblShBo中单独的属性付给本Form
     */
    public void setData(SbxxBo bo) {
        this.sbrq = DataConvert.TimeStamp2String(bo.getVoSbzb().getSbrq());
        this.sbbh = bo.getVoSbzb().getSbbh();
        this.pch = bo.getVoSbzb().getDrpch();
        this.voSbzb = bo.getVoSbzb();

        if (voSbzb.blbs.equals("1") || voSbzb.blbs.equals("2")) {
            this.bl = true;
        }
        this.voSpjgxx = bo.getVoSpjgxx();
        this.setNsrList(bo.getNsrList());
        this.setVoGrxx(bo.getVoZcqrxx());
        this.voFgrxx = bo.getVoFgrxx();
        this.voTufwxx = bo.getVoTufwxx();
        this.voFwjh = bo.getVoFwjh();
        this.cqList = bo.getCqList();
        this.gyzfList = bo.getGyzfList();
        this.voJghdxx = bo.getHdbo();
        this.jmsje = DataConvert.BigDecimal2String(voSpjgxx.getJmsje());
        this.printSbbh = bo.getPrintSbbh();
        if (voSbzb.getDfsbbh() != null && !voSbzb.getDfsbbh().equals("")) {
            this.voFwjh = bo.getDfSbxxBo();
            this.addedFwjhxx = true;
        } else {
            this.addedFwjhxx = false;
        }
    }

    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setSfzjlxList(ArrayList sfzjlxList) {
        this.sfzjlxList = sfzjlxList;
    }

    public void setNsrlxList(ArrayList nsrlxList) {
        this.nsrlxList = nsrlxList;
    }

    public void setFwindex(int fwindex) {
        this.fwindex = fwindex;
    }

    public void setFwtype(int fwtype) {
        this.fwtype = fwtype;
    }

    public void setAddedTufwxx(boolean addedTufwxx) {
        this.addedTufwxx = addedTufwxx;
    }

    public void setAddedFwjhxx(boolean addedFwjhxx) {
        this.addedFwjhxx = addedFwjhxx;
    }

    public void setCqList(ArrayList cqList) {
        this.cqList = cqList;
    }

    public void setGyzfList(ArrayList gyzfList) {
        this.gyzfList = gyzfList;
    }

    public void setVoFwjh(FwjhxxBo voFwjh) {
        this.voFwjh = voFwjh;
    }

    public void setVoFgrxx(Fgrxx voFgrxx) {
        this.voFgrxx = voFgrxx;
    }

    public void setVoGrxx(Grxx voGrxx) {
        this.voGrxx = voGrxx;
    }

    public void setVoSbzb(Sbzb voSbzb) {
        this.voSbzb = voSbzb;
    }

    public void setVoTufwxx(Tufwxx voTufwxx) {
        this.voTufwxx = voTufwxx;
    }

    public void setKhyhList(ArrayList khyhList) {
        this.khyhList = khyhList;
    }

    public void setGjdqList(ArrayList gjdqList) {
        this.gjdqList = gjdqList;
    }

    public void setVoSpjgxx(Spjgxx voSpjgxx) {
        this.voSpjgxx = voSpjgxx;
    }

    public void setJmsje(String jmsje) {
        this.jmsje = jmsje;
    }

    public void setAlertMessage(String alertMessage) {
        if (alertMessage == null || alertMessage.equals("")) {
            this.alert = false;
        } else {
            this.alert = true;
        }
        this.alertMessage = alertMessage;
    }

    public void setPch(String pch) {
        this.pch = pch;
    }

    public void setSbrq(String sbrq) {
        this.sbrq = sbrq;
    }

    public void setBl(boolean bl) {
        this.bl = bl;
    }

    private void jbInit() throws Exception {
    }

    /**
     * @return Returns the voJghdxx.
     */
    public JghdsjBo getVoJghdxx() {
        return voJghdxx;
    }

    /**
     * @param voJghdxx The voJghdxx to set.
     */
    public void setVoJghdxx(JghdsjBo voJghdxx) {
        this.voJghdxx = voJghdxx;
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
     * @return Returns the cqrJs.
     */
    public String getCqrJs() {
        return cqrJs;
    }

    /**
     * @param cqrJs The cqrJs to set.
     */
    public void setCqrJs(String cqrJs) {
        this.cqrJs = cqrJs;
    }

    /**
     * @return Returns the printSbbh.
     */
    public String getPrintSbbh() {
        return printSbbh;
    }

    public String getInputStr() {
        return inputStr;
    }

    /**
     * @param printSbbh The printSbbh to set.
     */
    public void setPrintSbbh(String printSbbh) {
        this.printSbbh = printSbbh;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }
}

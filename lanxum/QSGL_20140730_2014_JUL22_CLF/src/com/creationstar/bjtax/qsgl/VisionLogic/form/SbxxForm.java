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
     * ����������������ַ���
     * author������ϼ
     */
    private String inputStr;

    /**
     * �ж��Ƿ����Բ�¼
     */
    private boolean bl = false;

    /**
     * �걨����
     */
    private String sbrq;

    private String cqrJs = Constants.CQRJS_INIT;

    /**
     * ���κ�
     */

    private String pch;

    /**
     * ��ӡ�걨���
     */

    private String printSbbh;
    /**
     * ҳ���Ƿ���ʾ��Ϣ
     */
    private boolean alert = false;

    /**
     * ҳ����ʾ��Ϣ
     */
    private String alertMessage = "";

    /**
     * ��ǰ�༭�ķ�����Ϣ����arrayList�е�λ��
     */
    private int fwindex = -1;

    /**
     * ��ǰ�༭�ķ�����Ϣ���
     */
    private int fwtype = 0; //1:��Ǩ��2:����ס��

    /**
     * �ɿʽ�б�
     */
    private ArrayList jkfsList;

    /**
     * ���֤�������б�
     */
    public ArrayList sfzjlxList = new ArrayList();

    /**
     * ��˰�������б�
     */
    public ArrayList nsrlxList = new ArrayList();

    /**
     * ���������б�
     */
    public ArrayList khyhList = new ArrayList();

    /**
     * �����б�
     */
    public ArrayList gjdqList;

    /**
     * ��˰���б�
     */
    public List nsrList = new ArrayList();

    /**
     * �Ƿ������˷��ݻ�����Ϣ
     */
    private boolean addedTufwxx = false;

    /**
     * �Ƿ������˷��ݽ�����Ϣ
     */
    private boolean addedFwjhxx = false;

    /**
     * ����˰���
     */
    public String jmsje;

    /**
     * �걨��������
     */
    private Sbzb voSbzb = null;

    /**
     * ���������Ϣ
     */
    private Spjgxx voSpjgxx = null;

    /**
     * ���ݻ�����Ϣ
     */
    private Tufwxx voTufwxx = null;

    /**
     * ������Ϣ
     */
    private Grxx voGrxx = null;

    /**
     * �Ǹ�����Ϣ
     */
    private Fgrxx voFgrxx = null;

    /**
     * ���ݽ�����Ϣ
     */
    private FwjhxxBo voFwjh = null;

    /**
     * �Ǹ�����Ϣ
     */
    private JghdsjBo voJghdxx = null;

    /**
     * ������ArrayList��Ϊ�������ػ�����Ϣ����Ǩ���ѹ�����ס��
     * ÿ��ArrayList�У��������Ӧ��Vo��ArrayList�еĶ���
     */
    private ArrayList cqList = new ArrayList();
    private ArrayList gyzfList = new ArrayList();
    /**
     * �����б��еĲ�Ǩ��Ϣ
     */
    public void updateFwxx(Object obj) {
        if (this.fwtype == 1) { //��Ǩ��Ϣ
            this.cqList.set(fwindex, obj);
        } else if (this.fwtype == 2) { //����ס��
            this.gyzfList.set(fwindex, obj);
        }

    }

//  ����ȡ��
    public Object getData(List l) {
        SbxxBo bo = new SbxxBo();
        bo.setSbbh(this.sbbh);
        bo.setVoSbzb(this.voSbzb);
        this.voSpjgxx.setJmsje(DataConvert.String2BigDecimal(this.jmsje));
        bo.setVoSpjgxx(this.voSpjgxx);
        bo.setVoTufwxx(this.voTufwxx);
        bo.setVoFgrxx(this.voFgrxx);
        //��ÿͻ��˵�����--
        this.setCqrJs(ActionUtil.displayMNsrDS(l));
        this.setNsrList(l);

        bo.setNsrList(l);
        bo.setVoFwjh(this.voFwjh);
        bo.setCqList(this.cqList);
        bo.setGyzfList(this.gyzfList);
        return bo;
    }


//�޸���ʾȡ��
    public Object getData() {
        SbxxBo bo = new SbxxBo();
        bo.setSbbh(this.sbbh);
        bo.setVoSbzb(this.voSbzb);
        this.voSpjgxx.setJmsje(DataConvert.String2BigDecimal(this.jmsje));
        bo.setVoSpjgxx(this.voSpjgxx);
        bo.setVoTufwxx(this.voTufwxx);
        bo.setVoFgrxx(this.voFgrxx);
        bo.setNsrList(this.getNsrList());
        //��ÿͻ��˵�����--
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
     * ��JsblShBo�е��������Ը�����Form
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

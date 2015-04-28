package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.util.Debug;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * ��������ĵ����ѯform
 */
public class QueryPlslForm extends QueryBaseForm {
    /**
     *��ѯ����
     */
    private String drpch;
    /**
     *��ѯ����
     */
    private String selcfdr = "";
    /**
     *��������0:�µ���1���ظ�����
     */
    private String drlx = "";

    /**
     *�����־λ
     */
    private String sl = "all";

    /**
     * �ṩ������
     */
    private String tgzmc = "";

    /**
     * �ṩ�߼��������
     */
    private String tgzjsjdm = "";

    /**
     * ��������
     */
    private String drrq = "";


    /**
     * �����Ϣ dr--�ӵ���ҳ������ѯҳ�� cx--�Ӳ�ѯ������Ϣҳ������ҳ��
     */
    private String from = "dr";

    /**
     * ��̨��ѯ����Ľ����
     */
    private ArrayList resultList = new ArrayList();

    /**
     * Ҫɾ���ĵ�������
     */
    private PlsbBo plBo;


    /**
     * �Ե�������
     */
    private ArrayList drsj;
    /**
     *  �ѵ������ݸ���
     */
    private int size = 0;
    /**
     *  �����ʶ
     */
    private String drbs = "0";


    /**
     *
     * ע��������FormFile
     * */
    private FormFile mofile;
    private String mofile_desc;
    //�Ƿ���ɹ�
    private boolean mbIsSucceed = true;
    //��ʽ���Ե����ݼ���
    private ArrayList mlErrRecords;
    /**
     * �ɿʽ
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm = "01";
    public String jkfsmc;

    //�������κ�
    private String msDrpch;
    //��¼�ĵ������κ�
    private String[] msBLDrpchs = {
            "1", "2"};
    private String msBLDrpch;

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {

    }

    public FormFile getMofile() {
        return mofile;
    }


    public String getMofile_desc() {
        return mofile_desc;
    }

    public String getSelcfdr() {
        return selcfdr;
    }

    public String getDrlx() {
        return drlx;
    }

    public boolean isMbIsSucceed() {
        return mbIsSucceed;
    }

    public ArrayList getMlErrRecords() {
        return mlErrRecords;
    }

    public String getMsDrpch() {
        return msDrpch;
    }

    public String[] getMsBLDrpchs() {
        return msBLDrpchs;
    }

    public String getMsBLDrpch() {
        return msBLDrpch;
    }


    public void setMofile(FormFile mofile) {
        this.mofile = mofile;
    }

    public void setMofile_desc(String mofile_desc) {
        this.mofile_desc = mofile_desc;
    }

    public void setMbIsSucceed(boolean mbIsSucceed) {
        this.mbIsSucceed = mbIsSucceed;
    }

    public void setMlErrRecords(ArrayList mlErrRecords) {
        this.mlErrRecords = mlErrRecords;
    }

    public void setMsDrpch(String msDrpch) {
        this.msDrpch = msDrpch;
    }

    public void setMsBLDrpchs(String[] msBLDrpchs) {
        this.msBLDrpchs = msBLDrpchs;
    }

    public void setMsBLDrpch(String msBLDrpch) {
        this.msBLDrpch = msBLDrpch;
    }

    public void setSelcfdr(String selcfdr) {
        this.selcfdr = selcfdr;
    }

    public void setDrlx(String drlx) {
        this.drlx = drlx;
    }

    public ArrayList getResultList() {
        return resultList;
    }


    public ArrayList getDrsj() {
        return drsj;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setDrsj(ArrayList drsj) {
        this.drsj = drsj;
    }

    /**
     * ����viewform
     */
    public void createViewForm() {
        //����GnForm.
        PlslForm aForm = new PlslForm();
        Drzb zb = new Drzb();
        //�Ӳ�ѯ����л�ȡbo,��bo�еõ�ѡ���¼�е���Ϣ
        if (from.equals("dr")) {
            zb = (Drzb) queryCache.getDataDetail(viewIndex);
        }
        if (from.equals("cx")) {
            PlsbBo plsbBo = (PlsbBo) queryCache.getDataDetail(viewIndex);
            this.plBo = plsbBo;
            zb = plsbBo.getDrzb();
        }
        PldrBo bo = new PldrBo();
        try {

            bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.getDrsjnr());
        } catch (Exception ex) {
            Debug.out("queryPlslForm�л�ȡviewformʱ����xml����");
            ex.printStackTrace();
        }

        aForm.setCqxxList(bo.getCqxxList());
        aForm.setFgrxx(bo.getFgrxx());
        aForm.setFwjhxxList(bo.getFwjhxxList());
        aForm.setGrxx(bo.getGrxx());
        aForm.setGyzfxxList(bo.getGyzfxxList());
        aForm.setSpjgxx(bo.getSpjgxx());
        aForm.setTufwxx(bo.getTufwxx());
        aForm.setGrxxList(bo.getGrxxList());
        aForm.setFgrxxList(bo.getFgrxxList());
        //˰���������
        aForm.setSetz(bo.getSetz());
        if (bo.getGrxx() != null) {
            //����
            aForm.setPerson("1");
        } else {
            //�Ǹ���
            aForm.setPerson("0");
        }
        if (bo.getSpjgxx() != null) {
            //������
            aForm.setSp("1");
        } else {
            //������
            aForm.setSp("0");
        }
        if (bo.getCqxxList() != null && bo.getCqxxList().size() > 0) {
            //�в�Ǩ
            aForm.setCq("1");
        } else {
            //�޲�Ǩ
            aForm.setCq("0");
        }
        if (bo.getGyzfxxList() != null && bo.getGyzfxxList().size() > 0) {
            //�й���
            aForm.setGf("1");
        } else {
            //�޹���
            aForm.setGf("0");
        }
        if (bo.getFwjhxxList() != null && bo.getFwjhxxList().size() > 0) {
            //�з���
            aForm.setFj("1");
        } else {
            //�޷���
            aForm.setFj("0");
        }
        if (bo.getTufwxx().getFwtdbmdm() != null &&
            !bo.getTufwxx().getFwtdbmdm().equals("")) {
            aForm.setTdbm("1");
        } else {
            aForm.setTdbm("0");
        }
        Debug.out("isPerson" + aForm.getPerson());
        Debug.out("IsCq" + aForm.getCq());
        Debug.out("IsFj" + aForm.getFj());
        Debug.out("IsGf" + aForm.getGf());
        Debug.out("IsSp" + aForm.getSp());
        Debug.out("IsTd" + aForm.getTdbm());

        viewForm = aForm;
    }

    public int getSize() {
        return size;
    }

    public String getDrbs() {
        return drbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getSl() {
        return sl;
    }

    public String getFrom() {
        return from;
    }

    public PlsbBo getPlBo() {
        return plBo;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public ArrayList getJkfsList() {
        return jkfsList;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getDrrq() {
        return drrq;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }


    public String getTgzmc() {
        return tgzmc;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setDrbs(String drbs) {
        this.drbs = drbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setPlBo(PlsbBo plBo) {
        this.plBo = plBo;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setDrrq(String drrq) {
        this.drrq = drrq;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setTgzmc(String tgzmc) {
        this.tgzmc = tgzmc;
    }

    /**
     * ����ú���
     */
    public void clear() {
        this.setDrpch("");
        this.setNsrmc("");
        this.setSl("all");
        this.setDrrq("");
        this.setTgzjsjdm("");
        this.setTgzmc("");
        this.setFrom("cx");
        this.setDrbs("0");
    }

    /**
     * ʵ�ָ��ඨ��ķ������� QueryForm�е����ݷ��뵽��Ӧ�� BO ���У�����ɿ��
     * @return Object
     */
    public Object getData() {
        Debug.out("into QueryPlslForm....");
        PlsbBo bo = new PlsbBo();
        bo.setDrpch(this.drpch);
        bo.setNsrmc(this.nsrmc);
        bo.setTgzjsjdm(this.tgzjsjdm);
        bo.setTgzmc(this.tgzmc);
        //��������
        bo.setDrsj(this.drrq);
        bo.setSl(this.sl);
        bo.setJsfsdm(this.jkfsdm);
        bo.setDrbs(this.drbs);
        return bo;
    }
}

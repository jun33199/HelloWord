package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

public class ImportFileForm extends BaseForm {
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
    //�������κ�
    private String msDrpch;
    //��¼�ĵ������κ�
    private String[] msBLDrpchs = {"1", "2"};
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
}

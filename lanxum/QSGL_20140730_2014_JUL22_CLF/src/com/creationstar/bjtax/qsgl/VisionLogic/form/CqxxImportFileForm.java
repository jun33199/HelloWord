package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class CqxxImportFileForm extends BaseForm {


    /**
     * 是否导入成功
     */
    private boolean importIsSucceed = true;

    /**
     * 格式不对的数据集合
     */
    private ArrayList importErrRecords;
    /**
     *  格式不对的数据集合个数
     */
    private int errSize = 0;

    /**
     *已导入数据
     */
    private ArrayList drsj;
    /**
     *  已导入数据个数
     */
    private int size = 0;


    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {

        importIsSucceed = true;
        importErrRecords = new ArrayList();
        drsj = new ArrayList();
        size = 0;
        errSize = 0;
    }

    public ArrayList getImportErrRecords() {
        return importErrRecords;
    }

    public void setImportErrRecords(ArrayList importErrRecords) {
        this.importErrRecords = importErrRecords;
    }

    public boolean isImportIsSucceed() {
        return importIsSucceed;
    }

    public void setImportIsSucceed(boolean importIsSucceed) {
        this.importIsSucceed = importIsSucceed;
    }

    public ArrayList getDrsj() {
        return drsj;
    }

    public void setDrsj(ArrayList drsj) {
        this.drsj = drsj;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getErrSize() {
        return errSize;
    }

    public void setErrSize(int errSize) {
        this.errSize = errSize;
    }


}

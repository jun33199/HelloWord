package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class PlsbErrBo implements Serializable {
    //��˰������
    private String nsrmc;

    //���ز���Ŀ����
    private String fdcxmmc;

    //���ز���Ŀ��ַ
    private String fdcxmdz;

    public String getFdcxmdz() {
        return fdcxmdz;
    }

    public String getFdcxmmc() {
        return fdcxmmc;
    }

    public String getNsrmc() {
        return nsrmc;
    }

    public void setFdcxmdz(String fdcxmdz) {
        this.fdcxmdz = fdcxmdz;
    }

    public void setFdcxmmc(String fdcxmmc) {
        this.fdcxmmc = fdcxmmc;
    }

    public void setNsrmc(String nsrmc) {
        this.nsrmc = nsrmc;
    }


}

package com.creationstar.bjtax.qsgl.model.bo;

import java.io.Serializable;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;

/**
 *
 * <p>Title:��¼�ǻ������ɵĽɿ����bo </p>
 *
 * <p>Description: ��¼�ǻ������ɵĽɿ����bo</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class FhzJksBo implements Serializable {
    private String sbbh;

    private Sbjkzb sbjkzb;

    private Sbjkmx sbjkmx;

    public String getSbbh() {
        return sbbh;
    }

    public Sbjkmx getSbjkmx() {
        return sbjkmx;
    }

    public Sbjkzb getSbjkzb() {
        return sbjkzb;
    }

    public void setSbbh(String sbbh) {
        this.sbbh = sbbh;
    }

    public void setSbjkmx(Sbjkmx sbjkmx) {
        this.sbjkmx = sbjkmx;
    }

    public void setSbjkzb(Sbjkzb sbjkzb) {
        this.sbjkzb = sbjkzb;
    }
}

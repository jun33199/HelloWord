package com.creationstar.bjtax.qsgl.BizLogic.vo;

import java.io.Serializable;

public class CqxxErr implements Serializable {

    private CqxxImportErrbvo cqxxImportErrbvo; //'²ðÇ¨ÈËÃû³Æ'

    private Cqxxb cqxxb; //'²ðÇ¨·¶Î§'

    public Cqxxb getCqxxb() {
        return cqxxb;
    }

    public void setCqxxb(Cqxxb cqxxb) {
        this.cqxxb = cqxxb;
    }

    public CqxxImportErrbvo getCqxxImportErrbvo() {
        return cqxxImportErrbvo;
    }

    public void setCqxxImportErrbvo(CqxxImportErrbvo cqxxImportErrbvo) {
        this.cqxxImportErrbvo = cqxxImportErrbvo;
    }


}

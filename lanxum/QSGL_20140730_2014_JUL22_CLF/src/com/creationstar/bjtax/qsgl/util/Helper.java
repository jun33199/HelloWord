/*
 * Created on 2006-4-11
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.creationstar.bjtax.qsgl.util;

import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;

/**
 * @author guzx
 *
 *
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Helper {

    /**
     * 获取一组共有产权人的主产权人
     */
    public static Grxx getZcqr(List nsrList) {
        if (nsrList == null || nsrList.size() == 0) {
            return null;
        }
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx g = (Grxx) nsrList.get(i);
            if (g.getCqrlx().equals(Constants.CQRLX_ZCQR)) {
                return g;
            }
        }
        return null;
    }
}

package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ekernel.db.or.*;
import java.util.*;
import java.sql.Timestamp;
import java.math.BigDecimal;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class QysdsjdTbl extends ORTable
{
    static final String tabName = "SBDB.SB_JL_QYSDSJD";
    public QysdsjdTbl(ORManager manager)
    {
        super(manager, tabName, Qysdsjd.class);

    }

}
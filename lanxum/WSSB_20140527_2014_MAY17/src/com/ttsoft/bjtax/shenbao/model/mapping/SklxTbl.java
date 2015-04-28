package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ttsoft.bjtax.shenbao.model.domain.Sklx;
import com.ekernel.db.or.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */

public class SklxTbl extends ORTable {
    static final String tabName = "DMDB.KJ_DM_SKLX";
  public SklxTbl(ORManager manager) {
         super(manager, tabName, Sklx.class);
  }

}
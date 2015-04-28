package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ttsoft.bjtax.shenbao.model.domain.Fs;
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

public class FsTbl extends ORTable{
    static final String tabName = "DMDB.GY_DM_FS";
  public FsTbl(ORManager manager)  {
      super(manager, tabName, Fs.class);
  }

}
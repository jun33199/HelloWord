package com.ttsoft.bjtax.shenbao.model.mapping;
import com.ttsoft.bjtax.shenbao.model.domain.Fs;
import com.ekernel.db.or.*;
import java.util.*;
import java.sql.Timestamp;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
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
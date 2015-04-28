package com.ttsoft.bjtax.smsb.util;

import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.framework.exception.BaseException;

public class LWHelper
{
  public LWHelper()
  {
    super();
  }

  public static boolean getLWState(HttpServletRequest request, String jsjdm) throws
      BaseException
  {
    YHZH yhzh = null;
    int i;
    String yhzhstr = "";
    String ssdwdm = "";
    UserData ud = (UserData) request.getSession().getAttribute(SessionKey.USER_DATA);
    ServiceProxy sp = new ServiceProxy();
    HashMap hm = sp.getDjInfo(jsjdm, ud);

    SWDJJBSJ jbsj = (SWDJJBSJ) hm.get("JBSJ");
    ssdwdm = jbsj.getSwjgzzjgdm();

    ArrayList dmList = (ArrayList) hm.get("YHZH");
    for (i = 0; i < dmList.size(); i++)
    {
      yhzh = (YHZH) dmList.get(i);
      if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
      {
        break;
      }
    }
    if (i < dmList.size())
    {
      yhzhstr = yhzh.getYhdm();
    }

    if (LWUtil.isLW(request.getSession().getServletContext(), ssdwdm, yhzhstr))
    {
      return true;
    }
    else
    {
      return false;
    }

  }
}

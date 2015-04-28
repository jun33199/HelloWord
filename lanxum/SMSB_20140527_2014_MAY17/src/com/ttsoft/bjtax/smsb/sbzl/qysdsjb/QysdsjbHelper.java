/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.util.Debug;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description:企业所得税季报</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbHelper
{
  /**
   * 生成默认的显示控制清单
   * @return 显示控制清单结果集
   */

  public static List getShowList()
  {
    List showList = new ArrayList();

    Map map1 = new HashMap();
    map1.put("hc", "1");
    map1.put("xmmc", "收入总额");
    showList.add(map1);

    Map map2 = new HashMap();
    map2.put("hc", "2");
    map2.put("xmmc", "利润总额（应纳税所得额）");
    showList.add(map2);

    Map map11 = new HashMap();
    map11.put("hc", "3");
    map11.put("xmmc", "减：弥补以前年度亏损");
    showList.add(map11);

    Map map12 = new HashMap();
    map12.put("hc", "4");
    map12.put("xmmc", "补亏后利润总额（应纳税所得额）4=2-3");
    showList.add(map12);

    Map map3 = new HashMap();
    map3.put("hc", "5");
    map3.put("xmmc", "适用税率");
    showList.add(map3);

    Map map4 = new HashMap();
    map4.put("hc", "6");
    map4.put("xmmc", "本期应纳所得税额 6=4×5");
    showList.add(map4);

    Map map5 = new HashMap();
    map5.put("hc", "7");
    map5.put("xmmc", "加：期初未缴所得税额");
    showList.add(map5);
    /////////////////////
    Map map6 = new HashMap();
    map6.put("hc", "8");
    map6.put("xmmc", "减：减免所得税额");
    showList.add(map6);

    Map map7 = new HashMap();
    map7.put("hc", "9");
    map7.put("xmmc", "加：查补以前年度税额");
    showList.add(map7);

    Map map8 = new HashMap();
    map8.put("hc", "10");
    map8.put("xmmc", "减：实际已缴纳所得税额");
    showList.add(map8);

    Map map9 = new HashMap();
    map9.put("hc", "11");
    map9.put("xmmc", "减：本期申报延交所得税额");
    showList.add(map9);

    Map map10 = new HashMap();
    map10.put("hc", "12");
    map10.put("xmmc", "本期实际应缴所得税额 12=6+7-8+9-10-11");
    showList.add(map10);

    Debug.out(showList);
    return showList;

  }
}
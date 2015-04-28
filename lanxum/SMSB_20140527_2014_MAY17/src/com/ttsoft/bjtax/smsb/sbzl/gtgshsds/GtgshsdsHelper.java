/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户所得税</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsHelper
{
    /**
     * 生成默认的显示控制清单
     * @return 显示控制清单结果集
     */
    public static List getShowList ()
    {
        List showList = new ArrayList();
        //1.全年［本月（次）］收入额
        Map map1 = new HashMap();
        map1.put("hc", "1");
        map1.put("xmmc", "1.全年［本月（次）］收入额");
        showList.add(map1);

        //2.成本
        Map map2 = new HashMap();
        map2.put("hc", "2");
        map2.put("xmmc", "2.成本");
        showList.add(map2);

        //3.费用
        Map map3 = new HashMap();
        map3.put("hc", "3");
        map3.put("xmmc", "3.费用");
        showList.add(map3);

        //4.损失
        Map map4 = new HashMap();
        map4.put("hc", "4");
        map4.put("xmmc", "4.损失");
        showList.add(map4);

        //5.全年（本月）应纳税所得额
        Map map5 = new HashMap();
        map5.put("hc", "5");
        map5.put("xmmc", "5.全年（本月）应纳税所得额");
        showList.add(map5);

        //6.税率
        Map map6 = new HashMap();
        map6.put("hc", "6");
        map6.put("xmmc", "6.税率");
        showList.add(map6);

        //7.速算扣除数
        Map map7 = new HashMap();
        map7.put("hc", "7");
        map7.put("xmmc", "7.速算扣除数 ");
        showList.add(map7);

        //8.应纳所得税额
        Map map8 = new HashMap();
        map8.put("hc", "8");
        map8.put("xmmc", "8.应纳所得税额");
        showList.add(map8);

        //9.减免税额
        Map map9 = new HashMap();
        map9.put("hc", "9");
        map9.put("xmmc", "9.减免税额");
        showList.add(map9);

        //10.实际应纳税额
        Map map10 = new HashMap();
        map10.put("hc", "10");
        map10.put("xmmc", "10.实际应纳税额");
        showList.add(map10);

        //11.全年预缴税额
        Map map11 = new HashMap();
        map11.put("hc", "11");
        map11.put("xmmc", "11.全年预缴税额");
        showList.add(map11);

        //12.应补（退）所得税额
        Map map12 = new HashMap();
        map12.put("hc", "12");
        map12.put("xmmc", "12.应补（退）所得税额");
        showList.add(map12);

        return showList;
    }

}
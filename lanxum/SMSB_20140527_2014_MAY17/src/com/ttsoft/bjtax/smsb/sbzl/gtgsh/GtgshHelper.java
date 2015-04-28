/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 个体工商户营业所得</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshHelper
{
    /**
     * 生成默认的显示控制清单
     * @return 显示控制清单结果集 List
     */
    public static List getShowList ()
    {
        List showList = new ArrayList();
        //①交通运输业

        Map map1 = new HashMap();
        map1.put("szsmdm", "021");
        map1.put("szsmmc", "①交通运输业");
        showList.add(map1);

        //②建筑业
        Map map2 = new HashMap();
        map2.put("szsmdm", "022");
        map2.put("szsmmc", "②建筑业");
        showList.add(map2);

        //③金融保险业
        Map map3 = new HashMap();
        map3.put("szsmdm", "023");
        map3.put("szsmmc", "③金融保险业");
        showList.add(map3);

        //④邮电通信业
        Map map4 = new HashMap();
        map4.put("szsmdm", "024");
        map4.put("szsmmc", "④邮电通信业");
        showList.add(map4);

        //⑤文化体育业
        Map map5 = new HashMap();
        map5.put("szsmdm", "025");
        map5.put("szsmmc", "⑤文化体育业");
        showList.add(map5);

        //⑥服务业: 总额
        Map map6 = new HashMap();
        map6.put("szsmdm", "0271");
        map6.put("szsmmc", "总额");
        showList.add(map6);

        //⑥服务业: 其中：饮食
        Map map7 = new HashMap();
        map7.put("szsmdm", "0272");
        map7.put("szsmmc", "其中：饮食");
        showList.add(map7);

        //⑦转让无形资产
        Map map8 = new HashMap();
        map8.put("szsmdm", "028");
        map8.put("szsmmc", "⑦转让无形资产");
        showList.add(map8);

        //⑧销售不动产
        Map map9 = new HashMap();
        map9.put("szsmdm", "029");
        map9.put("szsmmc", "⑧销售不动产");
        showList.add(map9);

        //⑨娱乐业: A、歌厅、舞厅、卡拉OK歌舞厅（包括夜总会、练歌房、恋歌房）、音乐茶座（包括酒吧）、台球、高尔夫球、保龄球、游艺（如射击、狩猎、跑马、游戏机、蹦极、卡丁车、热气球、动力伞、射箭、飞镖等）
        Map map10 = new HashMap();
        map10.put("szsmdm", "0261");
        map10.put("szsmmc", "A、歌厅、舞厅、卡拉OK歌舞厅（包括夜总会、练歌房、恋歌房）、音乐茶座（包括酒吧）、台球、高尔夫球、保龄球、游艺（如射击、狩猎、跑马、游戏机、蹦极、卡丁车、热气球、动力伞、射箭、飞镖等）");
        showList.add(map10);

        //⑨娱乐业: B、嘻水、水上滑梯、碰碰船、滑索、飞降、滑道、四轮摩托越野、滚轴滑冰、综合游乐场（园）
        Map map11 = new HashMap();
        map11.put("szsmdm", "0262");
        map11.put("szsmmc", "B、嘻水、水上滑梯、碰碰船、滑索、飞降、滑道、四轮摩托越野、滚轴滑冰、综合游乐场（园）");
        showList.add(map11);

        //⑨娱乐业: C、仅供少年儿童的游乐场或项目、其他游艺项目
        Map map12 = new HashMap();
        map12.put("szsmdm", "0263");
        map12.put("szsmmc", "C、仅供少年儿童的游乐场或项目、其他游艺项目");
        showList.add(map12);

        return showList;
    }

}
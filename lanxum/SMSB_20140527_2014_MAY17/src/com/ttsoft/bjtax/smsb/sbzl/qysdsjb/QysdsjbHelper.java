/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.util.Debug;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description:��ҵ����˰����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class QysdsjbHelper
{
  /**
   * ����Ĭ�ϵ���ʾ�����嵥
   * @return ��ʾ�����嵥�����
   */

  public static List getShowList()
  {
    List showList = new ArrayList();

    Map map1 = new HashMap();
    map1.put("hc", "1");
    map1.put("xmmc", "�����ܶ�");
    showList.add(map1);

    Map map2 = new HashMap();
    map2.put("hc", "2");
    map2.put("xmmc", "�����ܶӦ��˰���ö");
    showList.add(map2);

    Map map11 = new HashMap();
    map11.put("hc", "3");
    map11.put("xmmc", "�����ֲ���ǰ��ȿ���");
    showList.add(map11);

    Map map12 = new HashMap();
    map12.put("hc", "4");
    map12.put("xmmc", "�����������ܶӦ��˰���ö4=2-3");
    showList.add(map12);

    Map map3 = new HashMap();
    map3.put("hc", "5");
    map3.put("xmmc", "����˰��");
    showList.add(map3);

    Map map4 = new HashMap();
    map4.put("hc", "6");
    map4.put("xmmc", "����Ӧ������˰�� 6=4��5");
    showList.add(map4);

    Map map5 = new HashMap();
    map5.put("hc", "7");
    map5.put("xmmc", "�ӣ��ڳ�δ������˰��");
    showList.add(map5);
    /////////////////////
    Map map6 = new HashMap();
    map6.put("hc", "8");
    map6.put("xmmc", "������������˰��");
    showList.add(map6);

    Map map7 = new HashMap();
    map7.put("hc", "9");
    map7.put("xmmc", "�ӣ��鲹��ǰ���˰��");
    showList.add(map7);

    Map map8 = new HashMap();
    map8.put("hc", "10");
    map8.put("xmmc", "����ʵ���ѽ�������˰��");
    showList.add(map8);

    Map map9 = new HashMap();
    map9.put("hc", "11");
    map9.put("xmmc", "���������걨�ӽ�����˰��");
    showList.add(map9);

    Map map10 = new HashMap();
    map10.put("hc", "12");
    map10.put("xmmc", "����ʵ��Ӧ������˰�� 12=6+7-8+9-10-11");
    showList.add(map10);

    Debug.out(showList);
    return showList;

  }
}
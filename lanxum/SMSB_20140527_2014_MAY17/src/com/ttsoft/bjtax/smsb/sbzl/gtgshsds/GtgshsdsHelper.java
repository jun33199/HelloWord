/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgshsds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�����˰</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshsdsHelper
{
    /**
     * ����Ĭ�ϵ���ʾ�����嵥
     * @return ��ʾ�����嵥�����
     */
    public static List getShowList ()
    {
        List showList = new ArrayList();
        //1.ȫ��۱��£��Σ��������
        Map map1 = new HashMap();
        map1.put("hc", "1");
        map1.put("xmmc", "1.ȫ��۱��£��Σ��������");
        showList.add(map1);

        //2.�ɱ�
        Map map2 = new HashMap();
        map2.put("hc", "2");
        map2.put("xmmc", "2.�ɱ�");
        showList.add(map2);

        //3.����
        Map map3 = new HashMap();
        map3.put("hc", "3");
        map3.put("xmmc", "3.����");
        showList.add(map3);

        //4.��ʧ
        Map map4 = new HashMap();
        map4.put("hc", "4");
        map4.put("xmmc", "4.��ʧ");
        showList.add(map4);

        //5.ȫ�꣨���£�Ӧ��˰���ö�
        Map map5 = new HashMap();
        map5.put("hc", "5");
        map5.put("xmmc", "5.ȫ�꣨���£�Ӧ��˰���ö�");
        showList.add(map5);

        //6.˰��
        Map map6 = new HashMap();
        map6.put("hc", "6");
        map6.put("xmmc", "6.˰��");
        showList.add(map6);

        //7.����۳���
        Map map7 = new HashMap();
        map7.put("hc", "7");
        map7.put("xmmc", "7.����۳��� ");
        showList.add(map7);

        //8.Ӧ������˰��
        Map map8 = new HashMap();
        map8.put("hc", "8");
        map8.put("xmmc", "8.Ӧ������˰��");
        showList.add(map8);

        //9.����˰��
        Map map9 = new HashMap();
        map9.put("hc", "9");
        map9.put("xmmc", "9.����˰��");
        showList.add(map9);

        //10.ʵ��Ӧ��˰��
        Map map10 = new HashMap();
        map10.put("hc", "10");
        map10.put("xmmc", "10.ʵ��Ӧ��˰��");
        showList.add(map10);

        //11.ȫ��Ԥ��˰��
        Map map11 = new HashMap();
        map11.put("hc", "11");
        map11.put("xmmc", "11.ȫ��Ԥ��˰��");
        showList.add(map11);

        //12.Ӧ�����ˣ�����˰��
        Map map12 = new HashMap();
        map12.put("hc", "12");
        map12.put("xmmc", "12.Ӧ�����ˣ�����˰��");
        showList.add(map12);

        return showList;
    }

}
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.gtgsh;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ���幤�̻�Ӫҵ����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class GtgshHelper
{
    /**
     * ����Ĭ�ϵ���ʾ�����嵥
     * @return ��ʾ�����嵥����� List
     */
    public static List getShowList ()
    {
        List showList = new ArrayList();
        //�ٽ�ͨ����ҵ

        Map map1 = new HashMap();
        map1.put("szsmdm", "021");
        map1.put("szsmmc", "�ٽ�ͨ����ҵ");
        showList.add(map1);

        //�ڽ���ҵ
        Map map2 = new HashMap();
        map2.put("szsmdm", "022");
        map2.put("szsmmc", "�ڽ���ҵ");
        showList.add(map2);

        //�۽��ڱ���ҵ
        Map map3 = new HashMap();
        map3.put("szsmdm", "023");
        map3.put("szsmmc", "�۽��ڱ���ҵ");
        showList.add(map3);

        //���ʵ�ͨ��ҵ
        Map map4 = new HashMap();
        map4.put("szsmdm", "024");
        map4.put("szsmmc", "���ʵ�ͨ��ҵ");
        showList.add(map4);

        //���Ļ�����ҵ
        Map map5 = new HashMap();
        map5.put("szsmdm", "025");
        map5.put("szsmmc", "���Ļ�����ҵ");
        showList.add(map5);

        //�޷���ҵ: �ܶ�
        Map map6 = new HashMap();
        map6.put("szsmdm", "0271");
        map6.put("szsmmc", "�ܶ�");
        showList.add(map6);

        //�޷���ҵ: ���У���ʳ
        Map map7 = new HashMap();
        map7.put("szsmdm", "0272");
        map7.put("szsmmc", "���У���ʳ");
        showList.add(map7);

        //��ת�������ʲ�
        Map map8 = new HashMap();
        map8.put("szsmdm", "028");
        map8.put("szsmmc", "��ת�������ʲ�");
        showList.add(map8);

        //�����۲�����
        Map map9 = new HashMap();
        map9.put("szsmdm", "029");
        map9.put("szsmmc", "�����۲�����");
        showList.add(map9);

        //������ҵ: A������������������OK������������ҹ�ܻᡢ���跿�����跿�������ֲ����������ưɣ���̨�򡢸߶����򡢱��������գ�����������ԡ�������Ϸ�����ļ����������������򡢶���ɡ����������ڵȣ�
        Map map10 = new HashMap();
        map10.put("szsmdm", "0261");
        map10.put("szsmmc", "A������������������OK������������ҹ�ܻᡢ���跿�����跿�������ֲ����������ưɣ���̨�򡢸߶����򡢱��������գ�����������ԡ�������Ϸ�����ļ����������������򡢶���ɡ����������ڵȣ�");
        showList.add(map10);

        //������ҵ: B����ˮ��ˮ�ϻ��ݡ����������������ɽ�������������Ħ��ԽҰ�����Ử�����ۺ����ֳ���԰��
        Map map11 = new HashMap();
        map11.put("szsmdm", "0262");
        map11.put("szsmmc", "B����ˮ��ˮ�ϻ��ݡ����������������ɽ�������������Ħ��ԽҰ�����Ử�����ۺ����ֳ���԰��");
        showList.add(map11);

        //������ҵ: C�����������ͯ�����ֳ�����Ŀ������������Ŀ
        Map map12 = new HashMap();
        map12.put("szsmdm", "0263");
        map12.put("szsmmc", "C�����������ͯ�����ֳ�����Ŀ������������Ŀ");
        showList.add(map12);

        return showList;
    }

}
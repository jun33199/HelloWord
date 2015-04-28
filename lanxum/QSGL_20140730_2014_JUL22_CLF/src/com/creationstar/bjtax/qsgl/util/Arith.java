package com.creationstar.bjtax.qsgl.util;

import java.math.BigDecimal;

/**
 * <p>Title: �Զ��屨��-�߾�����ѧ���㹤��</p>
 * <p>Description: </p>
 * ����Java�ļ����Ͳ��ܹ���ȷ�ĶԸ������������㣬����������ṩ��
 * ȷ�ĸ��������㣬�����Ӽ��˳����������뼰����ָ��λ������ȡ����
 * <p>Copyright: Copyright (c) 2006</p>
 * <p>Company: </p>
 * @author Ha Zhengze
 * @version 1.0
 */
public class Arith {

    //Ĭ�ϳ������㾫��
    private static final int DEF_DIV_SCALE = 10;

    //����಻��ʵ����
    private Arith() {}

    /** */
    /**
     * �ṩ��ȷ�ļӷ����㡣
     * @param v1 ������
     * @param v2 ����
     * @return ���������ĺ�
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /** */
    /**
     * �ṩ��ȷ�ļӷ����㡣
     * @param v1 ������
     * @param v2 ����
     * @return ���������ĺ�
     */
    public static String add(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.add(b2).toString();
    }


    /** */
    /**
     * �ṩ��ȷ�ļ������㡣
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ĳ�
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }


    /** */
    /**
     * �ṩ��ȷ�ļ������㡣
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ĳ�
     */
    public static String sub(String v1, String v2) {
        BigDecimal b1 = new BigDecimal(v1);
        BigDecimal b2 = new BigDecimal(v2);
        return b1.subtract(b2).toString();
    }


    /**
     * �ṩ��ȷ�ĳ˷����㡣
     * @param v1 ������
     * @param v2 ����
     * @return ���������Ļ�
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * �ṩ����ԣ���ȷ�ĳ������㣬�����������������ʱ����ȷ��
     * С�����Ժ�10λ���Ժ�������������롣
     * @param v1 ������
     * @param v2 ����
     * @return ������������
     */
    public static double div(double v1, double v2) {
        return div(v1, v2, DEF_DIV_SCALE);
    }

    /**
     * �ṩ����ԣ���ȷ�ĳ������㡣�����������������ʱ����scale����ָ
     * �����ȣ��Ժ�������������롣
     * @param v1 ������
     * @param v2 ����
     * @param scale ��ʾ��ʾ��Ҫ��ȷ��С�����Ժ�λ��
     * @return ������������
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * �ṩ��ȷ��С��λ�������봦����
     * @param v double ��Ҫ�������������
     * @param scale int С���������λ
     * @return ���������Ľ��
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * �ṩ��ȷ��С��λ�������봦����
     * @param v String ��Ҫ�������������
     * @param scale int С���������λ
     * @return ���������Ľ��
     */
    public static double round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * �ṩ��ȷ��С��λ�������봦����
     * @param v String ��Ҫ�������������
     * @param scale int С���������λ
     * @return String ���������Ľ��
     */
    public static String roundStr(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }


    /**
     * �ṩ��ȷ��С��λ�������봦����
     * @param v String ��Ҫ�������������
     * @param scale int С���������λ
     * @return String ���������Ľ��
     */
    public static String roundStrByDouble(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }


    public static void main(String[] args) {
        System.out.println(Arith.roundStr("111111111111111111.339", 2));
    }

}
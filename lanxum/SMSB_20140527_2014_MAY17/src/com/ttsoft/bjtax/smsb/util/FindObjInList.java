/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税综合管理系统  申报模块</p>
 * <p>Description: 实现北京地税申报模块 在list中查询特定的值对象 通用查询类</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class FindObjInList
{

    //常量
    private static final String FIELD = "field";

    private static final String FIELDVALUE = "fieldvalue";

    private static final String METHOD = "method";

    private static final String METHODVALUE = "methodvalue";

    private static final String FINDONE = "findone";

    private static final String FINDALL = "findall";

    /**
     *在指定的值对象list中查找给定条件的值对象(查找1个)
     * @param source 值对象list
     * @param objClass 值对象类名
     * @param member 要匹配的成员名称list
     * @param value 跟成员名称对应值list
     * @param type  查找方式,查找1个还是查找所有
     * @param isRemove  是否需要移除找到的对象true:remove,false:unremove
     * @return Object 查找到的数据
     * @throws Exception 异常情况 比如参数不合法
     */
    public static Object find (List source, Class objClass, List member,
                               List value,
                               String type, boolean isRemove)
        throws Exception

    {
        Field tmpField = null;
        Method tmpMethod = null;

        //参数检查
        Map map = paramCheck(source, objClass, member, value);
        //分析后的Field对象list
        List fieldList = (List) map.get(FIELD);
        //分析后的Field对象对应值的list
        List fieldValueList = (List) map.get(FIELDVALUE);
        //分析后的Method对象list
        List methodList = (List) map.get(METHOD);
        //分析后的Method对象对应值的list
        List methodValueList = (List) map.get(METHODVALUE);

        Object paramobj = null; //调用field.get(obj) 或是 method.invoke(obj,obj[])得到的值
        boolean tagField = true; //标识域名调用后得到的结果是否全都匹配
        boolean tagMethod = true; //标识方法调用后得到的结果是否全都匹配
        Object voSource = null; //要确定的source中的值对象
        List resultList = null; //当要findall时要返回结果List
        for (Iterator vos = source.iterator(); vos.hasNext(); )
        {
            voSource = vos.next();
            tagField = true;
            tagMethod = true;

            //先确定field是否全都匹配
            for (int k = 0; k < fieldList.size(); k++)
            {
                try
                {
                    paramobj = ( (Field) fieldList.get(k)).get(voSource);
                    if (paramobj == null)
                    {
                        if (paramobj != fieldValueList.get(k))
                        {
                            tagField = false;
                        }
                    }
                    else
                    {
                        if (!paramobj.equals(fieldValueList.get(k)))
                        {
                            tagField = false;
                        }
                    }
                }
                catch (Exception ex)
                {
                    tagField = false;
                    break;
                }
            }

            //处理method
            if (tagField)
            {
                //循环确定调用指定method后得到是否与给定的value匹配
                for (int m = 0; m < methodList.size(); m++)
                {
                    try
                    {
                        paramobj = ( (Method) methodList.get(m)).invoke(
                            voSource, null);
                        if (paramobj == null)
                        {
                            if (null != methodValueList.get(m))
                            {
                                tagMethod = false;
                            }
                        }
                        else
                        {
                            if (!paramobj.equals(methodValueList.get(m)))
                            {
                                tagMethod = false;
                            }
                        }

                    }
                    catch (Exception ex)
                    {
                        tagMethod = false;
                        break;
                    }
                }
            }
            else
            {
                break;
            }

            //判断field 和method是否全都成功
            if (tagField && tagMethod)
            {
                //如果只是查找1条
                if (type.equals(FINDONE))
                {
                    if (isRemove)
                    {
                        vos.remove();
                    }
                    return voSource;
                }
                else //查找多条
                {
                    if (resultList == null)
                    {
                        resultList = new ArrayList();
                    }
                    resultList.add(voSource);
                    if (isRemove)
                    {
                        vos.remove();
                    }
                }
            }
        }
        return resultList; //当查找1条是,resultList为null所以就返回这个了.
    }

    /**
     *在指定的值对象list中查找给定条件的值对象(查找所有匹配的)
     * @param source 值对象list
     * @param objClass 值对象类名
     * @param member 要匹配的成员名称list
     * @param value 跟成员名称对应值list
     * @return 查找到的值对象List(查不到为null)
     * @throws Exception 异常情况 比如参数不合法
     */
    public static List findAll (List source, Class objClass, List member,
                                List value)
        throws Exception

    {
        return (List) find(source, objClass, member, value, FINDALL, false);
    }

    /**
     *在指定的值对象list中查找给定条件的值对象(查找1个匹配的)
     * @param source 值对象list
     * @param objClass 值对象类名
     * @param member 要匹配的成员名称list
     * @param value 跟成员名称对应值list
     * @return 查找到的值对象(查不到为null)
     * @throws Exception 异常情况 比如参数不合法
     */
    public static Object findOne (List source, Class objClass, List member,
                                  List value)
        throws Exception

    {
        return find(source, objClass, member, value, FINDONE, false);
    }

    /**
     * 按照指定的成员属性或方法来拆分list为多个list,请特别注意拆分完后,source的size =0
     * @param source 源List
     * @param objClass List中的类名
     * @param member 成员List
     * @return 拆分后的List
     * @throws java.lang.Exception 操作异常
     */
    public static List splitListByParam (List source, Class objClass,
                                         List member)
        throws Exception
    {

        //返回结果List,按照objClass中指定的成员拆分后的List
        //List中的每个元素还是为List(这里的list中的每个objClass实例元素,
        //中指定的成员或方法得到的数据是相同)
        List resultList = new ArrayList();

        //先构造一个假的与member相同size的valueList,这是为了使用paramCheck;
        List tmpValueList = new ArrayList(member);
        Map map = paramCheck(source, objClass, member, tmpValueList);

        List fieldList = (List) map.get(FIELD);
        List methodList = (List) map.get(METHOD);

        try
        {
            //多次循环直到拆分出所有
            while (source.size() > 0)
            {
                tmpValueList = getValueList(source.get(0), objClass, fieldList,
                                            methodList);
                //find操作会删除source中元素
                resultList.add(find(source, objClass, member, tmpValueList,
                                    FINDALL, true));
            }
            return resultList;
        }
        catch (Exception ex)
        {
            throw ex;
        }

    }

    /**
     * 得到类名的特定实例中指定成员或方法的值
     * @param obj 特定的实例
     * @param objClass 实例的类名
     * @param fieldList 属性list
     * @param methodList 方法list
     * @throws Exception 操作异常
     * @return fieldList+methodList 对应的valueList
     */
    private static List getValueList (Object obj, Class objClass,
                                      List fieldList, List methodList)
        throws Exception
    {
        List valueList = new ArrayList();
        Object tmpValue = null; //指定对象中对应field或是对应方法调用后的值
        //循环得到field的value
        for (int i = 0; i < fieldList.size(); i++)
        {
            try
            {
                tmpValue = ( (Field) fieldList.get(i)).get(obj);
            }
            catch (Exception ex)
            {
                throw new Exception("找不到指定的成员");
            }
            valueList.add(tmpValue);
        }
        //循环得到method的value
        for (int j = 0; j < methodList.size(); j++)
        {
            try
            {
                tmpValue = ( (Method) methodList.get(j)).invoke(obj, null); ;
            }
            catch (Exception ex)
            {
                //发生异常就认为null
                throw ExceptionUtil.getBaseException(new Exception("找不到指定的方法"));
            }
            valueList.add(tmpValue);
        }
        return valueList;
    }

    /**
     * 参数检查并分析出fieldList和MethodList
     * @param source 要分析的值对象List
     * @param objClass List中的元素类型
     * @param member objClass中指定的方法名或是域名
     * @param value 方法或域对应的value
     * @return Map(FieldList,methodList,FieldValueList,methodValueList等)
     * @throws java.lang.Exception 异常信息
     */
    private static Map paramCheck (List source, Class objClass, List member,
                                   List value)
        throws Exception
    {
        //临时变量
        Field tmpField = null;
        Method tmpMethod = null;
        //分析后的Field对象list
        ArrayList fieldList = new ArrayList();
        //分析后的Field对象对应值的list
        ArrayList fieldValueList = new ArrayList();
        //分析后的Method对象list
        ArrayList methodList = new ArrayList();
        //分析后的Method对象对应值的list
        ArrayList methodValueList = new ArrayList();

        //参数检查
        if (source == null || objClass == null || value == null || member == null)
        {
            throw new Exception("所有参数都不能为null");
        }

        //成员数量必须于值数量一致
        if (member.size() != value.size())
        {
            throw new Exception("(member.size:" + member.size() + " != " +
                                "value.size:" + value.size() + ")" +
                                "成员对象与值必须是一一对应的!");
        }

        //在类中分析,提取对应的成员域或是方法,同时格式化valueList
        //(这是为了能处理public field的情况)
        for (int j = 0; j < member.size(); j++)
        {
            tmpField = null;
            tmpMethod = null;
            try
            {
                //指定member是field
                tmpField = objClass.getField( (String) member.get(j));
                fieldList.add(tmpField);
                fieldValueList.add(value.get(j));
            }
            catch (Exception fex)
            {
                try
                {
                    //指定member是method
                    tmpMethod = objClass.getMethod( (String) member.get(j), null);
                    methodList.add(tmpMethod);
                    methodValueList.add(value.get(j));
                }
                catch (Exception mex)
                {
                    throw new Exception(
                        "在类" + objClass.getName()
                        + "中找不到指定的成员(或是成员不是public,没有读写权限):" +
                        (String) member.get(j));
                }
            }
        } //分析结束

        Map map = new HashMap();
        map.put(FIELD, fieldList);
        map.put(FIELDVALUE, fieldValueList);
        map.put(METHOD, methodList);
        map.put(METHODVALUE, methodValueList);
        return map;
    }

}
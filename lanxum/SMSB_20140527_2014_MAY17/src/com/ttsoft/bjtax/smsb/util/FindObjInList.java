/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨ģ��</p>
 * <p>Description: ʵ�ֱ�����˰�걨ģ�� ��list�в�ѯ�ض���ֵ���� ͨ�ò�ѯ��</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class FindObjInList
{

    //����
    private static final String FIELD = "field";

    private static final String FIELDVALUE = "fieldvalue";

    private static final String METHOD = "method";

    private static final String METHODVALUE = "methodvalue";

    private static final String FINDONE = "findone";

    private static final String FINDALL = "findall";

    /**
     *��ָ����ֵ����list�в��Ҹ���������ֵ����(����1��)
     * @param source ֵ����list
     * @param objClass ֵ��������
     * @param member Ҫƥ��ĳ�Ա����list
     * @param value ����Ա���ƶ�Ӧֵlist
     * @param type  ���ҷ�ʽ,����1�����ǲ�������
     * @param isRemove  �Ƿ���Ҫ�Ƴ��ҵ��Ķ���true:remove,false:unremove
     * @return Object ���ҵ�������
     * @throws Exception �쳣��� ����������Ϸ�
     */
    public static Object find (List source, Class objClass, List member,
                               List value,
                               String type, boolean isRemove)
        throws Exception

    {
        Field tmpField = null;
        Method tmpMethod = null;

        //�������
        Map map = paramCheck(source, objClass, member, value);
        //�������Field����list
        List fieldList = (List) map.get(FIELD);
        //�������Field�����Ӧֵ��list
        List fieldValueList = (List) map.get(FIELDVALUE);
        //�������Method����list
        List methodList = (List) map.get(METHOD);
        //�������Method�����Ӧֵ��list
        List methodValueList = (List) map.get(METHODVALUE);

        Object paramobj = null; //����field.get(obj) ���� method.invoke(obj,obj[])�õ���ֵ
        boolean tagField = true; //��ʶ�������ú�õ��Ľ���Ƿ�ȫ��ƥ��
        boolean tagMethod = true; //��ʶ�������ú�õ��Ľ���Ƿ�ȫ��ƥ��
        Object voSource = null; //Ҫȷ����source�е�ֵ����
        List resultList = null; //��ҪfindallʱҪ���ؽ��List
        for (Iterator vos = source.iterator(); vos.hasNext(); )
        {
            voSource = vos.next();
            tagField = true;
            tagMethod = true;

            //��ȷ��field�Ƿ�ȫ��ƥ��
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

            //����method
            if (tagField)
            {
                //ѭ��ȷ������ָ��method��õ��Ƿ��������valueƥ��
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

            //�ж�field ��method�Ƿ�ȫ���ɹ�
            if (tagField && tagMethod)
            {
                //���ֻ�ǲ���1��
                if (type.equals(FINDONE))
                {
                    if (isRemove)
                    {
                        vos.remove();
                    }
                    return voSource;
                }
                else //���Ҷ���
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
        return resultList; //������1����,resultListΪnull���Ծͷ��������.
    }

    /**
     *��ָ����ֵ����list�в��Ҹ���������ֵ����(��������ƥ���)
     * @param source ֵ����list
     * @param objClass ֵ��������
     * @param member Ҫƥ��ĳ�Ա����list
     * @param value ����Ա���ƶ�Ӧֵlist
     * @return ���ҵ���ֵ����List(�鲻��Ϊnull)
     * @throws Exception �쳣��� ����������Ϸ�
     */
    public static List findAll (List source, Class objClass, List member,
                                List value)
        throws Exception

    {
        return (List) find(source, objClass, member, value, FINDALL, false);
    }

    /**
     *��ָ����ֵ����list�в��Ҹ���������ֵ����(����1��ƥ���)
     * @param source ֵ����list
     * @param objClass ֵ��������
     * @param member Ҫƥ��ĳ�Ա����list
     * @param value ����Ա���ƶ�Ӧֵlist
     * @return ���ҵ���ֵ����(�鲻��Ϊnull)
     * @throws Exception �쳣��� ����������Ϸ�
     */
    public static Object findOne (List source, Class objClass, List member,
                                  List value)
        throws Exception

    {
        return find(source, objClass, member, value, FINDONE, false);
    }

    /**
     * ����ָ���ĳ�Ա���Ի򷽷������listΪ���list,���ر�ע�������,source��size =0
     * @param source ԴList
     * @param objClass List�е�����
     * @param member ��ԱList
     * @return ��ֺ��List
     * @throws java.lang.Exception �����쳣
     */
    public static List splitListByParam (List source, Class objClass,
                                         List member)
        throws Exception
    {

        //���ؽ��List,����objClass��ָ���ĳ�Ա��ֺ��List
        //List�е�ÿ��Ԫ�ػ���ΪList(�����list�е�ÿ��objClassʵ��Ԫ��,
        //��ָ���ĳ�Ա�򷽷��õ�����������ͬ)
        List resultList = new ArrayList();

        //�ȹ���һ���ٵ���member��ͬsize��valueList,����Ϊ��ʹ��paramCheck;
        List tmpValueList = new ArrayList(member);
        Map map = paramCheck(source, objClass, member, tmpValueList);

        List fieldList = (List) map.get(FIELD);
        List methodList = (List) map.get(METHOD);

        try
        {
            //���ѭ��ֱ����ֳ�����
            while (source.size() > 0)
            {
                tmpValueList = getValueList(source.get(0), objClass, fieldList,
                                            methodList);
                //find������ɾ��source��Ԫ��
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
     * �õ��������ض�ʵ����ָ����Ա�򷽷���ֵ
     * @param obj �ض���ʵ��
     * @param objClass ʵ��������
     * @param fieldList ����list
     * @param methodList ����list
     * @throws Exception �����쳣
     * @return fieldList+methodList ��Ӧ��valueList
     */
    private static List getValueList (Object obj, Class objClass,
                                      List fieldList, List methodList)
        throws Exception
    {
        List valueList = new ArrayList();
        Object tmpValue = null; //ָ�������ж�Ӧfield���Ƕ�Ӧ�������ú��ֵ
        //ѭ���õ�field��value
        for (int i = 0; i < fieldList.size(); i++)
        {
            try
            {
                tmpValue = ( (Field) fieldList.get(i)).get(obj);
            }
            catch (Exception ex)
            {
                throw new Exception("�Ҳ���ָ���ĳ�Ա");
            }
            valueList.add(tmpValue);
        }
        //ѭ���õ�method��value
        for (int j = 0; j < methodList.size(); j++)
        {
            try
            {
                tmpValue = ( (Method) methodList.get(j)).invoke(obj, null); ;
            }
            catch (Exception ex)
            {
                //�����쳣����Ϊnull
                throw ExceptionUtil.getBaseException(new Exception("�Ҳ���ָ���ķ���"));
            }
            valueList.add(tmpValue);
        }
        return valueList;
    }

    /**
     * ������鲢������fieldList��MethodList
     * @param source Ҫ������ֵ����List
     * @param objClass List�е�Ԫ������
     * @param member objClass��ָ���ķ�������������
     * @param value ���������Ӧ��value
     * @return Map(FieldList,methodList,FieldValueList,methodValueList��)
     * @throws java.lang.Exception �쳣��Ϣ
     */
    private static Map paramCheck (List source, Class objClass, List member,
                                   List value)
        throws Exception
    {
        //��ʱ����
        Field tmpField = null;
        Method tmpMethod = null;
        //�������Field����list
        ArrayList fieldList = new ArrayList();
        //�������Field�����Ӧֵ��list
        ArrayList fieldValueList = new ArrayList();
        //�������Method����list
        ArrayList methodList = new ArrayList();
        //�������Method�����Ӧֵ��list
        ArrayList methodValueList = new ArrayList();

        //�������
        if (source == null || objClass == null || value == null || member == null)
        {
            throw new Exception("���в���������Ϊnull");
        }

        //��Ա����������ֵ����һ��
        if (member.size() != value.size())
        {
            throw new Exception("(member.size:" + member.size() + " != " +
                                "value.size:" + value.size() + ")" +
                                "��Ա������ֵ������һһ��Ӧ��!");
        }

        //�����з���,��ȡ��Ӧ�ĳ�Ա����Ƿ���,ͬʱ��ʽ��valueList
        //(����Ϊ���ܴ���public field�����)
        for (int j = 0; j < member.size(); j++)
        {
            tmpField = null;
            tmpMethod = null;
            try
            {
                //ָ��member��field
                tmpField = objClass.getField( (String) member.get(j));
                fieldList.add(tmpField);
                fieldValueList.add(value.get(j));
            }
            catch (Exception fex)
            {
                try
                {
                    //ָ��member��method
                    tmpMethod = objClass.getMethod( (String) member.get(j), null);
                    methodList.add(tmpMethod);
                    methodValueList.add(value.get(j));
                }
                catch (Exception mex)
                {
                    throw new Exception(
                        "����" + objClass.getName()
                        + "���Ҳ���ָ���ĳ�Ա(���ǳ�Ա����public,û�ж�дȨ��):" +
                        (String) member.get(j));
                }
            }
        } //��������

        Map map = new HashMap();
        map.put(FIELD, fieldList);
        map.put(FIELDVALUE, fieldValueList);
        map.put(METHOD, methodList);
        map.put(METHODVALUE, methodValueList);
        return map;
    }

}
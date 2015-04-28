/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import com.ibm.xml.parsers.DOMParser;

import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ӡ��˰�����xml�ļ�����</p>
 * @author �������飭�������
 * @version 1.1
 */
public class xml4YHS
{
    /**
     *ӡ��˰������Ϣ
     */
    public ArrayList yhsList = null;

    /**
     *�ļ�����������ƾ֤�ź���Ӧ�ĺϼƽ����Ϣ
     */
    public ArrayList xsList = null;

    /**
     *���ۼƵ�λ�������
     */
    public String dsjsjdm = "";

    /**
     *����������ʱ����
     */
    private ArrayList tempList = null;

    /**
     *����������ļ�
     */
    private static Document theDocument;

    /**
     *��������ݼ�¼��
     */
    private int tableNum = 0;

    /**
     *������������
     */
    private String columns[] =
        { //������������
        "xspzh", "jsjdm", "dwmc", "zhdm", "gjdqdm",
        "zjlxdm",
        "zjhm", "spmzdm", "spmzje", "gpsl", "je"};

    /**
     *�������ݼ�����
     */
    private String checkStr = null;

    /**
     * ���캯��
     * @param theFile ����������ļ�
     * @throws java.lang.Exception
     */
    public xml4YHS (InputSource theFile)
        throws Exception
    {
        yhsList = new ArrayList();
        tempList = new ArrayList();
        xsList = new ArrayList();
        checkStr = "";
        DOMParser parser = new DOMParser();
        parser.parse(theFile);
        theDocument = parser.getDocument();
        setDsjsjdm();
        setTableNum();
        try
        {
            setXmlData();
            yhsList = (ArrayList) tempList.clone();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("������ļ��а������´���\n" + ex.getMessage());
        }
        try
        {
            setXsList(tempList);
        }
        catch (Exception ex1)
        {
            ex1.printStackTrace();
            throw new ApplicationException("�����������ʱ�������´���" + ex1.getMessage());
        }
    }

    /**
     * ȡ��xml������
     * @throws java.lang.Exception
     */
    private void setXmlData ()
        throws Exception
    {
        StringBuffer errStr = new StringBuffer("");
        String errStrTemp = "";
        for (int i = 0; i < tableNum; i++)
        {
            errStr.append(getRecord(i));
        } //end for
        if (! (errStrTemp = errStr.toString()).equals(""))
        {
            throw new Exception(errStrTemp);
        }
    }

    /**
     * ��ô��۵�λ���������
     * @throws java.lang.Exception
     */
    private void setDsjsjdm ()
        throws Exception
    {
        NodeList nodelist1 = theDocument.getElementsByTagName("info");
        dsjsjdm = ( (Element) nodelist1.item(0)).getAttribute("dsjsjdm");
        if (dsjsjdm == null)
        {
            throw new Exception("ȱ��jsjdm��");
        }
    }

    /**
     * ȡ�ü�¼����
     * @throws java.lang.Exception
     */
    private void setTableNum ()
        throws Exception
    {
        NodeList nl = theDocument.getElementsByTagName("data");
        if (nl.getLength() < 1)
        {
            throw new Exception("������ļ�û���κ����ݼ�¼��");
        }
        tableNum = nl.getLength();
    }

    /**
     * �õ�����ƾ֤�ź���Ӧ�Ľ��ϼ�
     * @param tempList ��������
     */
    private void setXsList (ArrayList tempList)
    {
        HashMap xsMap = new HashMap();
        xsMap.put("xspzh", ( (HashMap) tempList.get(0)).get("xspzh").toString());
        xsMap.put("hjje", ( (HashMap) tempList.get(0)).get("je").toString());
        xsMap.put("zhdm", ( (HashMap) tempList.get(0)).get("zhdm").toString());
        xsList.add(xsMap);

        for (int i = 1; i < tempList.size(); i++)
        {
            int flag = 1;
            for (int j = 0; j < xsList.size(); j++)
            {
                //����������ۼ�¼��������ƾ֤���Ѿ�������
                //���������ۼ�¼�����۽���ۼӵ������ۼ�¼����Ľ��ϼ���
                if ( ( (HashMap) tempList.get(i)).get("xspzh").toString().
                    equals( ( (
                    HashMap)
                             xsList.get(j)).get("xspzh").toString()))
                {
                    ( (HashMap) xsList.get(j)).put("hjje",
                        (Float.toString(Float.
                                        parseFloat( ( (HashMap) xsList.get(j)).
                        get("hjje").toString()) +
                                        Float.parseFloat( ( (HashMap) tempList.
                        get(i)).get("je").toString()))));
                    flag = 0;
                    break;
                } //end if
            } //end for
            if (flag == 1)
            { //��������µ�����ƾ֤�źͽ��
                HashMap newMap = new HashMap();
                newMap.put("xspzh", ( (HashMap) tempList.get(i)).get("xspzh"));
                newMap.put("hjje", ( (HashMap) tempList.get(i)).get("je"));
                newMap.put("zhdm", ( (HashMap) tempList.get(0)).get("zhdm"));
                xsList.add(newMap);
            } //end if
        } //end for
    }

    /**
     * ȡ�õ�n����¼������
     * @param n Ҫȡ�����ݱ��
     * @return ��n����¼������
     */
    private String getRecord (int n)
    {
        StringBuffer errStr = new StringBuffer("");
        String errStrTemp = "";
        HashMap yhsMap = new HashMap();
        NodeList nodelist1 = theDocument.getElementsByTagName("data");
        Node node1 = nodelist1.item(n);
        Element el = (Element) node1;

        //ȡ�ñ��ڵ�����ֵ
        for (int i = 0; i < columns.length; i++)
        {
            yhsMap.put(columns[i], el.getAttribute(columns[i]));
        }

        tempList.add(yhsMap);

        //��ñ���¼У����Ϣ
        if (! (errStrTemp = doCheck(yhsMap)).equals(""))
        {
            errStr.append("<BR> ��" + (n + 1) + "�����ݰ������´���:\n");
            errStr.append(errStrTemp);
        }
        return errStr.toString();
    }

    /**
     * ��֤�����ļ������ݵĺ�����
     * @param map ����֤������
     * @return ��֤���
     */
    private String doCheck (HashMap map)
    {
        StringBuffer errorStr = new StringBuffer("");

        //��֤����ƾ֤�ţ�8λ��ˮ�ţ�
        if ( (map.get("xspzh").toString().length() > 0) &&
            ! (Long.parseLong(map.get("xspzh").toString()) > 0) ||
            (map.get("xspzh").toString().length() != 8))
        {
            errorStr.append("  [����ƾ֤��]������8λ��ˮ�ţ�\n");
        }

        //��֤������λ��������롢������λ���Ƶĺ�����
        if ( (map.get("jsjdm").toString().length() > 0) &&
            (! (Long.parseLong(map.get("jsjdm").toString()) > 0) ||
             (map.get("jsjdm").toString().length() < 7) ||
             (map.get("jsjdm").toString().length() > 8)))
        {
            errorStr.append("  [������λ���������]����Ϊ�� ���� 7��8λ���ִ���\n");
        }

        //��֤���ҵ������루�� ���� 3λ����
        if ( (map.get("gjdqdm").toString().length() > 0) &&
            (map.get("gjdqdm").toString().length() != 3))
        {
            errorStr.append("  [���ҵ�������]������3λ��дӢ����ĸ����\n");
        }

        //��֤֤�����ʹ��롢֤�����ʹ���ĺ�����
        if ( (map.get("zjlxdm").toString().length() > 0) &&
            ( (map.get("zjlxdm").toString().length() != 2) ||
             (Long.parseLong(map.get("zjlxdm").toString()) > 0)))
        {
            errorStr.append("  [֤�����ʹ���]������2λ���֣�\n");
        }

        //��֤ӡ��˰Ʊ֤������롢ӡ��˰��ֵ����Ʊ���������ĺ�����
        if ( (map.get("spmzdm").toString().length() != 4) ||
            ! ( (Long.parseLong(map.get("spmzdm").toString())) > 0))
        {
            errorStr.append("  [ӡ��˰Ʊ֤�������]������4λ���ִ���\n");
        }

        //��֤ӡ��˰ƱƱ�����ʽ������
        if ( (map.get("spmzje").toString().length() < 1) ||
            ! ( (Double.parseDouble(map.get("spmzje").toString())) > 0))
        {
            errorStr.append("  [ӡ��˰ƱƱ����]��ʽ����ȷ��\n");
        }

        //��֤ӡ��˰Ʊ��Ʊ������ʽ������
        if ( (map.get("gpsl").toString().length() < 1) ||
            ! ( (Long.parseLong(map.get("gpsl").toString())) > 0))
        {
            errorStr.append("  [ӡ��˰Ʊ��Ʊ����]��ʽ����ȷ��\n");
        }

        //��֤ӡ��˰����ʽ������
        if ( (map.get("je").toString().length() < 1) ||
            ! ( (Double.parseDouble(map.get("je").toString())) > 0))
        {
            errorStr.append("  [ӡ��˰���]��ʽ����ȷ��\n");
        }

        //��֤Ʊ����������ܽ��Ĺ�ϵ
        if ( ( (Double.parseDouble(map.get("spmzje").toString())) *
              (Long.parseLong(map.get("gpsl").toString())))
            != (Double.parseDouble(map.get("je").toString())))
        {
            errorStr.append("  [ӡ��˰ƱƱ����]��[ӡ��˰Ʊ��Ʊ����]��[ӡ��˰���]��ϵ����ȷ��\n");
        }

        //��֤�Ƿ����ظ���¼
        String ckStr = map.get("xspzh").toString() +
                       map.get("spmzdm").toString();
        if (checkStr.indexOf(ckStr) >= 0)
        {
            errorStr.append("  ��ǰ����ƾ֤���´���ֵ��ӡ��˰Ʊ�����ۼ�¼�Ѵ��ڣ�\n");
        }
        else
        {
            checkStr += ckStr + "#";
        }
        return errorStr.toString();
    }
}
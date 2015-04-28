/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
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
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 印花税导入的xml文件处理</p>
 * @author 开发六组－－诸光林
 * @version 1.1
 */
public class xml4YHS
{
    /**
     *印花税销售信息
     */
    public ArrayList yhsList = null;

    /**
     *文件包含的销售凭证号和相应的合计金额信息
     */
    public ArrayList xsList = null;

    /**
     *代售计单位算机代码
     */
    public String dsjsjdm = "";

    /**
     *导入数据临时集合
     */
    private ArrayList tempList = null;

    /**
     *导入的数据文件
     */
    private static Document theDocument;

    /**
     *导入的数据记录数
     */
    private int tableNum = 0;

    /**
     *数据列名数组
     */
    private String columns[] =
        { //数据列名数组
        "xspzh", "jsjdm", "dwmc", "zhdm", "gjdqdm",
        "zjlxdm",
        "zjhm", "spmzdm", "spmzje", "gpsl", "je"};

    /**
     *导入数据检验结果
     */
    private String checkStr = null;

    /**
     * 构造函数
     * @param theFile 导入的数据文件
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
            throw new ApplicationException("导入的文件中包含以下错误：\n" + ex.getMessage());
        }
        try
        {
            setXsList(tempList);
        }
        catch (Exception ex1)
        {
            ex1.printStackTrace();
            throw new ApplicationException("获得销售数据时发生以下错误" + ex1.getMessage());
        }
    }

    /**
     * 取得xml的数据
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
     * 获得代售单位计算机代码
     * @throws java.lang.Exception
     */
    private void setDsjsjdm ()
        throws Exception
    {
        NodeList nodelist1 = theDocument.getElementsByTagName("info");
        dsjsjdm = ( (Element) nodelist1.item(0)).getAttribute("dsjsjdm");
        if (dsjsjdm == null)
        {
            throw new Exception("缺少jsjdm！");
        }
    }

    /**
     * 取得纪录个数
     * @throws java.lang.Exception
     */
    private void setTableNum ()
        throws Exception
    {
        NodeList nl = theDocument.getElementsByTagName("data");
        if (nl.getLength() < 1)
        {
            throw new Exception("导入的文件没有任何数据记录！");
        }
        tableNum = nl.getLength();
    }

    /**
     * 得到销售凭证号和相应的金额合计
     * @param tempList 销售数据
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
                //如果本条销售记录所属销售凭证号已经存在则
                //将本条销售记录的销售金额累加到本销售记录主表的金额合计中
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
            { //否则填加新的销售凭证号和金额
                HashMap newMap = new HashMap();
                newMap.put("xspzh", ( (HashMap) tempList.get(i)).get("xspzh"));
                newMap.put("hjje", ( (HashMap) tempList.get(i)).get("je"));
                newMap.put("zhdm", ( (HashMap) tempList.get(0)).get("zhdm"));
                xsList.add(newMap);
            } //end if
        } //end for
    }

    /**
     * 取得第n个纪录的数据
     * @param n 要取的数据编号
     * @return 第n个纪录的数据
     */
    private String getRecord (int n)
    {
        StringBuffer errStr = new StringBuffer("");
        String errStrTemp = "";
        HashMap yhsMap = new HashMap();
        NodeList nodelist1 = theDocument.getElementsByTagName("data");
        Node node1 = nodelist1.item(n);
        Element el = (Element) node1;

        //取得本节点属性值
        for (int i = 0; i < columns.length; i++)
        {
            yhsMap.put(columns[i], el.getAttribute(columns[i]));
        }

        tempList.add(yhsMap);

        //获得本记录校验信息
        if (! (errStrTemp = doCheck(yhsMap)).equals(""))
        {
            errStr.append("<BR> 第" + (n + 1) + "条数据包含以下错误:\n");
            errStr.append(errStrTemp);
        }
        return errStr.toString();
    }

    /**
     * 验证导入文件中数据的合理性
     * @param map 需验证的数据
     * @return 验证结果
     */
    private String doCheck (HashMap map)
    {
        StringBuffer errorStr = new StringBuffer("");

        //验证销售凭证号（8位流水号）
        if ( (map.get("xspzh").toString().length() > 0) &&
            ! (Long.parseLong(map.get("xspzh").toString()) > 0) ||
            (map.get("xspzh").toString().length() != 8))
        {
            errorStr.append("  [销售凭证号]必须是8位流水号！\n");
        }

        //验证购花单位计算机代码、购花单位名称的合理性
        if ( (map.get("jsjdm").toString().length() > 0) &&
            (! (Long.parseLong(map.get("jsjdm").toString()) > 0) ||
             (map.get("jsjdm").toString().length() < 7) ||
             (map.get("jsjdm").toString().length() > 8)))
        {
            errorStr.append("  [购花单位计算机代码]必须为空 或者 7或8位数字串！\n");
        }

        //验证国家地区代码（空 或者 3位串）
        if ( (map.get("gjdqdm").toString().length() > 0) &&
            (map.get("gjdqdm").toString().length() != 3))
        {
            errorStr.append("  [国家地区代码]必须是3位大写英文字母串！\n");
        }

        //验证证件类型代码、证件类型代码的合理性
        if ( (map.get("zjlxdm").toString().length() > 0) &&
            ( (map.get("zjlxdm").toString().length() != 2) ||
             (Long.parseLong(map.get("zjlxdm").toString()) > 0)))
        {
            errorStr.append("  [证件类型代码]必须是2位数字！\n");
        }

        //验证印花税票证种类代码、印花税面值、购票数量、金额的合理性
        if ( (map.get("spmzdm").toString().length() != 4) ||
            ! ( (Long.parseLong(map.get("spmzdm").toString())) > 0))
        {
            errorStr.append("  [印花税票证种类代码]必须是4位数字串！\n");
        }

        //验证印花税票票面金额格式合理性
        if ( (map.get("spmzje").toString().length() < 1) ||
            ! ( (Double.parseDouble(map.get("spmzje").toString())) > 0))
        {
            errorStr.append("  [印花税票票面金额]格式不正确！\n");
        }

        //验证印花税票购票数量格式合理性
        if ( (map.get("gpsl").toString().length() < 1) ||
            ! ( (Long.parseLong(map.get("gpsl").toString())) > 0))
        {
            errorStr.append("  [印花税票购票数量]格式不正确！\n");
        }

        //验证印花税金额格式合理性
        if ( (map.get("je").toString().length() < 1) ||
            ! ( (Double.parseDouble(map.get("je").toString())) > 0))
        {
            errorStr.append("  [印花税金额]格式不正确！\n");
        }

        //验证票面金额、数量和总金额的关系
        if ( ( (Double.parseDouble(map.get("spmzje").toString())) *
              (Long.parseLong(map.get("gpsl").toString())))
            != (Double.parseDouble(map.get("je").toString())))
        {
            errorStr.append("  [印花税票票面金额]、[印花税票购票数量]、[印花税金额]关系不正确！\n");
        }

        //验证是否有重复记录
        String ckStr = map.get("xspzh").toString() +
                       map.get("spmzdm").toString();
        if (checkStr.indexOf(ckStr) >= 0)
        {
            errorStr.append("  当前销售凭证号下此面值的印花税票的销售记录已存在！\n");
        }
        else
        {
            checkStr += ckStr + "#";
        }
        return errorStr.toString();
    }
}
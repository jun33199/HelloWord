package com.ttsoft.bjtax.shenbao.taglib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;


public class SbzlTag extends TagSupport
{
    public SbzlTag()
    {
    }

    private static SortedMap sbzl;
    private String frompage;

    public int doStartTag() throws JspException
    {
        try
        {
            String cur = String.valueOf(System.currentTimeMillis());
            String jsjdm =
                FriendHelper.getUserData((HttpServletRequest)pageContext.getRequest()).yhid;
            //String id = "?frompage="+frompage+"&identifyCode=" + cur + jsjdm;
            String id = "?actionType=Show&identifyCode=" + cur + jsjdm;

            List sbzl = getSbzl(id);

            StringBuffer html = new StringBuffer();

            for (int i=0; i<sbzl.size(); i++)
            {
                html.append(sbzl.get(i));
                html.append("<br>");
            }

            pageContext.getOut().println(html.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return TagSupport.SKIP_BODY;
    }

    /**
     * �õ��걨���ϵ�����
     * @return List �걨�����б�
     */
    private List getSbzl(String id)
    {
        List sbzlList = new ArrayList();

        getSbzlInfo(id);

        SortedMap tmpSbzlInfo = SbzlTag.sbzl;

//        System.out.println("tmpSbzlInfo.size() = " + tmpSbzlInfo.size());
        while(tmpSbzlInfo.size() != 0)
        {
            Integer first = (Integer)tmpSbzlInfo.firstKey();

            boolean passed = SbzlAccess.getAuthority(
                first.intValue(),
                (HttpServletRequest)this.pageContext.getRequest());

            if (passed)
            {
                sbzlList.add(tmpSbzlInfo.get(first));
            }

            tmpSbzlInfo = tmpSbzlInfo.tailMap(new Integer(first.intValue() + 1));
        }

        return sbzlList;
    }

    private static SortedMap getSbzlInfo(String id)
    {
        if (sbzl != null)
        {
            return sbzl;
        }

        Properties prop = new Properties();

        try
        {
            prop.load(com.ttsoft.bjtax.shenbao.taglib.SbzlTag.class.getClassLoader().
                      getResourceAsStream("ApplicationResources.properties"));
        }
        catch(IOException ioe)
        {
            ioe.printStackTrace();
        }

        sbzl = new TreeMap();

        sbzl.put(new Integer(SbzlAccess.CZND), decoratedc(toGBK(prop.getProperty("SbzlTag.CZND")), id));
       // sbzl.put(new Integer(SbzlAccess.CZJD), decorate(toGBK(prop.getProperty("SbzlTag.CZJD")), id));
        //sbzl.put(new Integer(SbzlAccess.SD), decorate(toGBK(prop.getProperty("SbzlTag.SD")), id));
       // sbzl.put(new Integer(SbzlAccess.GRSDS), decorate(toGBK(prop.getProperty("SbzlTag.GRSDS")), id));
       // sbzl.put(new Integer(SbzlAccess.HDYSSDL), decorate(toGBK(prop.getProperty("SbzlTag.HDYSSDL")), id));
       // sbzl.put(new Integer(SbzlAccess.HDZSL), decorate(toGBK(prop.getProperty("SbzlTag.HDZSL")), id));
        sbzl.put(new Integer(SbzlAccess.JM), decoratedc(toGBK(prop.getProperty("SbzlTag.JM")), id));
        sbzl.put(new Integer(SbzlAccess.QYKS), decoratedc(toGBK(prop.getProperty("SbzlTag.QYKS")), id));
        sbzl.put(new Integer(SbzlAccess.QYND), decorate(toGBK(prop.getProperty("SbzlTag.QYND")), id));
        sbzl.put(new Integer(SbzlAccess.WQ), decoratedc(toGBK(prop.getProperty("SbzlTag.WQ")), id));
        sbzl.put(new Integer(SbzlAccess.YHND), decoratedc(toGBK(prop.getProperty("SbzlTag.YHND")), id));
        sbzl.put(new Integer(SbzlAccess.CWZB), decoratedc(toGBK(prop.getProperty("SbzlTag.CWZB")), id));
        sbzl.put(new Integer(SbzlAccess.ZJYJMSB), decorate(toGBK(prop.getProperty("SbzlTag.ZJYJMSB")), id));//�����پ�ҵ����

//      added by tum 20080306 start ��ҵ����˰�걨��2008��
        sbzl.put(new Integer(SbzlAccess.CZZSQYJB2008), decoratedc(toGBK(prop.getProperty("SbzlTag.CZZSQYJB2008")), id)); //2008�������ռ���
        sbzl.put(new Integer(SbzlAccess.HDZSQYJB2008), decoratedc(toGBK(prop.getProperty("SbzlTag.HDZSQYJB2008")), id)); //2008�˶����ռ���
//        sbzl.put(new Integer(SbzlAccess.ZFJGQYJB2008), decoratedc(toGBK(prop.getProperty("SbzlTag.ZFJGQYJB2008")), id)); //2008������˰��֧���������
//      added by tum 20080306 end ��ҵ����˰�걨��2008��

//      added by lianglw 20061101 start �µ���ҵ����˰�걨��2006��
        /*08��ҵ����˰�����걨�ر�06�����걨��06�˶��걨�������*/
//        sbzl.put(new Integer(SbzlAccess.HDZSQYJB), decoratedc(toGBK(prop.getProperty("SbzlTag.HDZSQYJB")), id));
        sbzl.put(new Integer(SbzlAccess.HDZSQYNB), decoratedc(toGBK(prop.getProperty("SbzlTag.HDZSQYNB")), id));
//        sbzl.put(new Integer(SbzlAccess.CZZSQYJB), decoratedc(toGBK(prop.getProperty("SbzlTag.CZZSQYJB")), id));
        sbzl.put(new Integer(SbzlAccess.NSRJBXXB), decoratedc(toGBK(prop.getProperty("SbzlTag.NSRJBXXB")), id));
//      added by lianglw 20061101 end �µ���ҵ����˰�걨��

//      added by xinyy 20091215 start ���òм����걨��
        sbzl.put(new Integer(SbzlAccess.CJRJYJMSB), decoratedc(toGBK(prop.getProperty("SbzlTag.CJRJYJMSB")), id));
        System.out.println(decoratedc(toGBK(prop.getProperty("SbzlTag.CJRJYJMSB")), id));
//      added by xinyy 20091215 end ���òм����걨��   

// 		added by wangcy  20131202 end ��ҵ����˰���Ԥ����˰�걨��(A��)(2012��)
        sbzl.put(new Integer(SbzlAccess.CZZSQYNB2012), decoratedc(toGBK(prop.getProperty("SbzlTag.CZZSQYNB2012")), id));
        
// 		added by wangcy  20140507  ��ҵ��������˰������     
        sbzl.put(new Integer(SbzlAccess.QYQSSDSBA2014), decoratedc(toGBK(prop.getProperty("SbzlTag.QYQSSDSBA2014")), id));
        
        //added by lijn 20141106 ��������˰����
        //sbzl.put(new Integer(SbzlAccess.GRSDSJBXXB2014), decoratedc(toGBK(prop.getProperty("SbzlTag.GRSDSJBXXB2014")), id));
        sbzl.put(new Integer(SbzlAccess.GRSDSNDSBB2014), decoratedc(toGBK(prop.getProperty("SbzlTag.GRSDSNDSBB2014")), id));
        return sbzl;
    }

    private static String decorate(String src, String id)
    {
        StringBuffer tmp = new StringBuffer(src);
        int start = src.indexOf('.', 10);
        tmp.replace(start, start + 3, ".do" + id);

        return tmp.toString();
    }
    private static String decoratedc(String src, String id)
    {
        StringBuffer tmp = new StringBuffer(src);
        int start = src.indexOf('.', 10);
        tmp.replace(start, start + 3, ".dc" + id);

        return tmp.toString();
    }

    private static String toGBK(String iso8859)
    {
        if (iso8859==null)
            return null;

        try
        {
            return new String(iso8859.getBytes("ISO8859-1"), "GBK");
        }
        catch (java.io.UnsupportedEncodingException ex)
        {
            return iso8859;
        }
    }
    public String getFrompage()
    {
        return frompage;
    }
    public void setFrompage(String frompage)
    {
        this.frompage = frompage;
    }
}

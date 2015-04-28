/*
 * Created on 2006-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.ttsoft.bjtax.shenbao.zhsb.xmlvo;

import java.sql.*;
import java.text.*;
import java.util.*;

import com.syax.bjtax.ca.vo.*;
import com.syax.bjtax.ca.xml.util.*;
import com.syax.common.util.*;
import com.syax.common.xml.*;
import com.ttsoft.bjtax.shenbao.constant.*;

/**
 * @author guzx
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Zhsb02VO
    extends YWRootVO implements XMLVOInterface {

    /**
     * 纳税人信息 = new Nsrxx02VO()
     */
    private Nsrxx02VO nsrxx;

    /**
     * 申报信息
     */
    private Sbxx02VO sbxx;
    private Map m = new HashMap();

    /**
     * 申报数据
     */
    List sbsj = new ArrayList();

    /*
     * 核定信息= new Hdxx02VO()
     */
    //  private Hdxx02VO hdxx ;

    /**
     *
     */
    public Zhsb02VO() {
        super();
        m.put("sbsj", "com.ttsoft.bjtax.shenbao.zhsb.xmlvo.Sbsj02VO");
    }

    /* (non-Javadoc)
     * @see com.syax.common.xml.XMLVOInterface#getListTypeMap()
     */
    public Map getListTypeMap() {
        return m;
    }

    /* (non-Javadoc)
     * @see com.syax.common.xml.XMLVOInterface#toXML()
     */
    public String toXML() {
        String xmlstr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc>";

        xmlstr += toXMLHead();
        xmlstr += toXMLChilds();
        xmlstr += "</taxdoc>";
        return xmlstr;
    }

    /* (non-Javadoc)
     * @see com.syax.common.xml.XMLVOInterface#toXMLChilds()
     */
    public String toXMLChilds() {
        String xmlstr = "";
        if (nsrxx != null) {
            xmlstr += nsrxx.toXML();
        }
        //if (hdxx!=null)
        //    xmlstr += hdxx.toXML();
        if (sbxx != null) {
            xmlstr += sbxx.toXML();
        }
        if (sbsj != null) {
            for (int i = 0; i < sbsj.size(); i++) {
                xmlstr += ( (Sbsj02VO) sbsj.get(i)).toXML();
            }
        }
        return xmlstr;
    }

    /**
     * @return Returns the nsrxx.
     */
    public Nsrxx02VO getNsrxx() {
        return nsrxx;
    }

    /**
     * @param nsrxx The nsrxx to set.
     */
    public void setNsrxx(Nsrxx02VO nsrxx) {
        this.nsrxx = nsrxx;
    }

    /**
     * @return Returns the sbxx.
     */
    public Sbxx02VO getSbxx() {
        return sbxx;
    }

    /**
     * @param sbxx The sbxx to set.
     */
    public void setSbxx(Sbxx02VO sbxx) {
        this.sbxx = sbxx;
    }

    private static Zhsb02VO convert2XMLVO() {
        Zhsb02VO vo = new Zhsb02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Hdxx02VO hdxx = new Hdxx02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        // 获得系统当前日期
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // XML文档信息
        vo.setSchemaVersion("20060401");
        vo.setXsltVersion("20060401");
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_QYSDS_JB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm("06003600");
        nsrxx.setNsrmc("Nsrmc");
        nsrxx.setSwjgzzjgdm("0601");
        // 信息
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        // 企业所得税季报
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();
        List smlist = new ArrayList();
        sbsj = new Sbsj02VO();
        sbsj.setSklx("正常");
        sbsj.setSklxdm("100");
        sbsj.setSbrq(sdf.format(curDate));
        sbsj.setYhmc("Yhmc");
        sbsj.setZh("zh(123)");

        Smitem02VO sm = new Smitem02VO();
        sm.setAksslj("false");
        sm.setAsljbs("4");
        sm.setCoefficient("1");
        sm.setJsje("0.00");
        sm.setKssl("0.00");
        sm.setSjse("0.00");
        sm.setSl("0.00");
        sm.setSzmc("Szmc");
        sm.setSzsmdm("020111");
        sm.setSzsmmc("Szsmmc");
        smlist.add(sm);
        sbsj.setSmitem(smlist);
        sbsjlist.add(sbsj);
        //sbsjl.setSbsj(sbsjlist);

        vo.setSbsj(sbsjlist);

        vo.setSchemaVersion("20060401");
        vo.setXsltVersion("20060401");
        vo.setYwlx("100002");

        return vo;
    }

    private static Zhsb02VO convert2XMLZyJksVO() {
        Zhsb02VO vo = new Zhsb02VO();
        /*  ZyJks02VO sbsj = new ZyJks02VO();
           Nsrxx02VO nsrxx = new Nsrxx02VO();
           // 获得系统当前日期
           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

           // XML文档信息
           vo.setSchemaVersion("20060401");
           vo.setXsltVersion("20060401");
           vo.setYwlx(CAcodeConstants.YWDM_SB_WS_QYSDS_JB);
           vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

           // ////////////////////////////////////
           // 填充VO。
           // 纳税人信息。
           nsrxx.setJsjdm("06003600");
           nsrxx.setNsrmc("纳税人名称");
           //nsrxx.setSwjgzzjgdm("0601");
           vo.setNsrxx(nsrxx);

           Timestamp now =new Timestamp(System.currentTimeMillis());
            List nxxmlist=new ArrayList();

               sbsj.setSbrq(sdf.format(now));
         sbsj.setYhmc("Yhmc");
         sbsj.setZh("zh123");
         sbsj.setYhdm("16");
         sbsj.setSbbh("041972900600777698");
         sbsj.setGkzzjgdm("1011");
         sbsj.setSkgk("Gkzzjgmc");
         sbsj.setSwjgzzjgmc("Swjgzzjgmc");
         sbsj.setDjzclxmc("Djzclxmc");
         sbsj.setLxdh("13303153232");
         sbsj.setLsgxmc("Lsgxmc");
         sbsj.setHjsjje("0.00");
         sbsj.setHjsjjedx("壹百元");
         sbsj.setZwbs("41600000100000000112");

         Nxxm02VO sm=new Nxxm02VO();
                           sm.setNxxmmc("02 Szmc");
                           sm.setSjje("0.00");
                           nxxmlist.add(sm);

            sbsj.setNxxm(nxxmlist);
           vo.setZyjks(sbsj);
         */
        return vo;
    }

    private static Zhsb02VO convert2XMLJksVO() {
        Zhsb02VO vo = new Zhsb02VO();
        Sbxx02VO sbxx = new Sbxx02VO();
        Sbsj02VO sbsj = new Sbsj02VO();
        Hdxx02VO hdxx = new Hdxx02VO();
        Nsrxx02VO nsrxx = new Nsrxx02VO();
        // 获得系统当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // XML文档信息
        vo.setSchemaVersion("20060401");
        vo.setXsltVersion("20060401");
        vo.setYwlx(CAcodeConstants.YWDM_SB_WS_QYSDS_JB);
        vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm("06003600");
        nsrxx.setNsrmc("纳税人名称");
        nsrxx.setSwjgzzjgdm("0601");
        // 信息
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        // 企业所得税季报
        vo.setNsrxx(nsrxx);
        vo.setSbxx(sbxx);
        List sbsjlist = new ArrayList();

        Timestamp now = new Timestamp(System.currentTimeMillis());
        List smlist = new ArrayList();
        List jkslist = new ArrayList();
        sbsj = new Sbsj02VO();
        sbsj.setSklx("正常");
        sbsj.setSklxdm("100");

        sbsj.setSbrq(sdf.format(now));
        sbsj.setYhmc("Yhmc");
        sbsj.setZh("zh123");
        sbsj.setYhdm("16");
        sbsj.setSskkbz("false");
        sbsj.setLwzt("false");
        sbsj.setSbbh("041972900600777698");
        sbsj.setGkzzjgdm("1011");
        sbsj.setGkzzjgmc("Gkzzjgmc");
        sbsj.setZsswjgzzjgdm("0602");
        sbsj.setZsswjgzzjgmc("Zsswjgzzjgmc");
        sbsj.setSwjgzzjgdm("0601");
        sbsj.setSwjgzzjgmc("Swjgzzjgmc");
        sbsj.setDjzclxmc("Djzclxmc");
        sbsj.setLxdh("13303153232");
        sbsj.setLsgxmc("Lsgxmc");

        sbsj.setSfkyzf("true");
        Jks02VO ajks = new Jks02VO();
        ajks.setJkpzh("0419365006020061");
        ajks.setSzdm("02");
        ajks.setSzmc("Szmc");
        ajks.setYskmdm("0310");
        ajks.setYskmmc("Yskmmc");
        ajks.setYsjcmc("Ysjcmc");
        ajks.setSjje("0.00");
        ajks.setSkssksrq(sdf.format(now));
        ajks.setSkssjsrq(sdf.format(now));
        ajks.setXjrq(sdf.format(now));
        ajks.setBz("Bz");
        ajks.setSfkyzf("true");
        ajks.setKkzt("作废");

        jkslist.add(ajks);

        Smitem02VO sm = new Smitem02VO();
        sm.setJkpzh("0419365006020061");
        sm.setSzsmdm("020111");

        sm.setSzsmmc("Szsmmc");
        sm.setJsje("0.00");
        sm.setKssl("0.00");
        sm.setSjse("0.00");
        sm.setSl("0.00");
        smlist.add(sm);

        sbsj.setSmitem(smlist);
        sbsj.setJks(jkslist);

        sbsjlist.add(sbsj);

        vo.setSbsj(sbsjlist);
        vo.setSchemaVersion("20060401");
        vo.setXsltVersion("20060401");
        vo.setYwlx("100002");

        return vo;
    }

    public static void main(String[] args) {
        String xmlvo = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[010009]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[Nsrmc]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbxx><fsdm><![CDATA[5]]></fsdm></sbxx><sbsj><sskkbz><![CDATA[]]></sskkbz><lwzt><![CDATA[]]></lwzt><yhdm><![CDATA[]]></yhdm><yhmc><![CDATA[Yhmc]]></yhmc><zh><![CDATA[zh(123)]]></zh><sbrq><![CDATA[2006-05-12]]></sbrq><sbbh><![CDATA[]]></sbbh><sklxdm><![CDATA[100]]></sklxdm><sklx><![CDATA[正常]]></sklx><gkzzjgdm><![CDATA[]]></gkzzjgdm><gkzzjgmc><![CDATA[]]></gkzzjgmc><zsswjgzzjgdm><![CDATA[]]></zsswjgzzjgdm><zsswjgzzjgmc><![CDATA[]]></zsswjgzzjgmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm><swjgzzjgmc><![CDATA[]]></swjgzzjgmc><djzclxmc><![CDATA[]]></djzclxmc><lxdh><![CDATA[]]></lxdh><lsgxmc><![CDATA[]]></lsgxmc><sfkyzf><![CDATA[]]></sfkyzf><smitem><jkpzh><![CDATA[]]></jkpzh><szmc><![CDATA[Szmc]]></szmc><szsmdm><![CDATA[020111]]></szsmdm><szsmmc><![CDATA[Szsmmc]]></szsmmc><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[0.00]]></jsje><sjse><![CDATA[0.00]]></sjse><sl><![CDATA[0.00]]></sl><aksslj><![CDATA[false]]></aksslj><asljbs><![CDATA[4]]></asljbs><coefficient><![CDATA[1]]></coefficient></smitem></sbsj></taxdoc>"
            ;
        String xmlJks = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[010009]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[纳税人名称]]></nsrmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm></nsrxx><sbxx><fsdm><![CDATA[5]]></fsdm></sbxx><sbsj><sskkbz><![CDATA[0]]></sskkbz><lwzt><![CDATA[1]]></lwzt><yhdm><![CDATA[16]]></yhdm><yhmc><![CDATA[Yhmc]]></yhmc><zh><![CDATA[zh123]]></zh><sbrq><![CDATA[2006-05-12]]></sbrq><sbbh><![CDATA[041972900600777698]]></sbbh><sklxdm><![CDATA[100]]></sklxdm><sklx><![CDATA[正常]]></sklx><gkzzjgdm><![CDATA[1011]]></gkzzjgdm><gkzzjgmc><![CDATA[Gkzzjgmc]]></gkzzjgmc><zsswjgzzjgdm><![CDATA[0602]]></zsswjgzzjgdm><zsswjgzzjgmc><![CDATA[Zsswjgzzjgmc]]></zsswjgzzjgmc><swjgzzjgdm><![CDATA[0601]]></swjgzzjgdm><swjgzzjgmc><![CDATA[Swjgzzjgmc]]></swjgzzjgmc><djzclxmc><![CDATA[Djzclxmc]]></djzclxmc><lxdh><![CDATA[13303153232]]></lxdh><lsgxmc><![CDATA[Lsgxmc]]></lsgxmc><sfkyzf><![CDATA[5]]></sfkyzf><jks><sfkyzf><![CDATA[false]]></sfkyzf><jkpzh><![CDATA[0419365006020061]]></jkpzh><szdm><![CDATA[02]]></szdm><szmc><![CDATA[Szmc]]></szmc><yskmdm><![CDATA[0310]]></yskmdm><yskmmc><![CDATA[Yskmmc]]></yskmmc><ysjcmc><![CDATA[Ysjcmc]]></ysjcmc><kkzt><![CDATA[作废]]></kkzt><sjje><![CDATA[0.00]]></sjje><sjjedx><![CDATA[]]></sjjedx><skssksrq><![CDATA[2006-05-12]]></skssksrq><skssjsrq><![CDATA[2006-05-12]]></skssjsrq><xjrq><![CDATA[2006-05-12]]></xjrq><bz><![CDATA[Bz]]></bz></jks><smitem><jkpzh><![CDATA[0419365006020061]]></jkpzh><szmc><![CDATA[]]></szmc><szsmdm><![CDATA[020111]]></szsmdm><szsmmc><![CDATA[Szsmmc]]></szsmmc><kssl><![CDATA[0.00]]></kssl><jsje><![CDATA[0.00]]></jsje><sjse><![CDATA[0.00]]></sjse><sl><![CDATA[0.00]]></sl><aksslj><![CDATA[]]></aksslj><asljbs><![CDATA[]]></asljbs><coefficient><![CDATA[]]></coefficient></smitem></sbsj></taxdoc>"
            ;
        String xmlZy = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxdoc><xsltVersion><![CDATA[20060401]]></xsltVersion><schemaVersion><![CDATA[20060401]]></schemaVersion><ywlx><![CDATA[010009]]></ywlx><ywczlx><![CDATA[0]]></ywczlx><nsrxx><jsjdm><![CDATA[06003600]]></jsjdm><nsrmc><![CDATA[纳税人名称]]></nsrmc><swjgzzjgdm><![CDATA[]]></swjgzzjgdm></nsrxx><hdxx></hdxx><sbsj><zwbs><![CDATA[41600000100000000112]]></zwbs><yhdm><![CDATA[16]]></yhdm><yhmc><![CDATA[Yhmc]]></yhmc><zh><![CDATA[zh123]]></zh><sbrq><![CDATA[2006-05-11]]></sbrq><sbbh><![CDATA[041972900600777698]]></sbbh><skgk><![CDATA[Gkzzjgmc]]></skgk><gkzzjgdm><![CDATA[1011]]></gkzzjgdm><swjgzzjgmc><![CDATA[Swjgzzjgmc]]></swjgzzjgmc><djzclxmc><![CDATA[Djzclxmc]]></djzclxmc><lxdh><![CDATA[13303153232]]></lxdh><lsgxmc><![CDATA[Lsgxmc]]></lsgxmc><hjsjje><![CDATA[0.00]]></hjsjje><hjsjjedx><![CDATA[壹百元]]></hjsjjedx><nxxm><nxxmmc><![CDATA[02 Szmc]]></nxxmmc><sjje><![CDATA[0.00]]></sjje></nxxm></sbsj></taxdoc>";
        Zhsb02VO vo = new Zhsb02VO();
        //vo =  convert2XMLVO();
        //vo =  convert2XMLJksVO();
        //vo =  convert2XMLZyJksVO();
        try {
            XMLParseHelper.parseXMLString(vo, xmlJks,
                                          XMLParseHelper.XERCES_PARSER, true, true);
            System.out.println("aaaaa");
            vo = new Zhsb02VO();
            XMLParseHelper.parseXMLString(vo, xmlJks,
                                          XMLParseHelper.VTDXML_PARSER, false, true);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        String tmpxml = vo.toXML();
        System.out.println("====================");
        System.out.println(tmpxml);
        System.out.println("====================");

    }

    /**
     * @return Returns the sbsj.
     */
    public List getSbsj() {
        return sbsj;
    }

    /**
     * @param sbsj The sbsj to set.
     */
    public void setSbsj(List sbsj) {
        this.sbsj = sbsj;
    }
}

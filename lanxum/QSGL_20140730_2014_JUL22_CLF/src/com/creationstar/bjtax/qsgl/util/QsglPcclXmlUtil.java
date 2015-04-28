package com.creationstar.bjtax.qsgl.util;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblcq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import org.apache.crimson.jaxp.DocumentBuilderFactoryImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class QsglPcclXmlUtil {


    /**
     * 当是开发商时要读取xml的配置信息
     * @param xml String    XML字符串
     * @return ArrayList    返回的vo集合，注意0号元素为qsdb.qs_jl_drpcinfo的vo
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Object getPcclPzxx(String xml) throws
            IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException,
            ParserConfigurationException, SAXException, IOException {
        Map pzmap = new HashMap();
        try {
            //得到xml的document对象
            Document doc = XMLTool.getDoc(xml);
            //得到根节点
            Element root = XMLTool.getRootElement(doc);

            //得到配置信息节点
            Element pzxx = XMLTool.getOneElement(root, "pzxx");
            Element jsjdm = XMLTool.getOneElement(pzxx, "jsjdm");
            Element nsrmc = XMLTool.getOneElement(pzxx, "nsrmc");
            //added by gaoyh to 20141017
            Element fdcxmmc = XMLTool.getOneElement(pzxx, "fdcxmmc");

            pzmap.put("jsjdm", XMLTool.getElementText(jsjdm));
            pzmap.put("nsrmc", XMLTool.getElementText(nsrmc));
            pzmap.put("fdcxmmc", XMLTool.getElementText(fdcxmmc));
            //System.out.println("jsjdm:" + XMLTool.getElementText(jsjdm));
            //System.out.println("nsrmc:" + XMLTool.getElementText(nsrmc));
            //System.out.println("fdcxmmc:" + XMLTool.getElementText(fdcxmmc));
        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (SAXException se) {
            throw se;
        } catch (IOException ie) {
            throw ie;
        }
        return pzmap;
    }


    /**
     * 从xml中获取要插入临时表的数据
     * @param xml String    XML字符串
     * @return ArrayList    返回的vo集合，注意0号元素为qsdb.qs_jl_drpcinfo的vo
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static ArrayList getPcclMx(String xml) throws
            IOException, ClassNotFoundException, IllegalAccessException,
            InstantiationException,
            ParserConfigurationException, SAXException, IOException {

        ArrayList mxList = new ArrayList();

        try {
            //得到xml的document对象
            Document doc = XMLTool.getDoc(xml);
            //得到根节点
            Element root = XMLTool.getRootElement(doc);

            //得到存放批次处理临时表主表数据的节点
            Element drpcInfo = XMLTool.getOneElement(root, "drpcInfo");
            Element drpcRow = XMLTool.getOneElement(drpcInfo, "row");

            Drpcinfo drpcinfo = new Drpcinfo(); //临时表主表的vo
            //得到临时表主表各字段的数据，并赋值给Drpcinfo vo
            Element[] drpcField = XMLTool.getChildrenElements(drpcRow, "Field");
            //读取导入人的信息
            for (int i = 0; i < drpcField.length; i++) {
                if (drpcField[i].getAttribute("fieldName").equalsIgnoreCase(
                        "tgzlx")) {
                    drpcinfo.setTgzlx(XMLTool.getElementText(drpcField[i]));
                } else if (drpcField[i].getAttribute("fieldName").
                           equalsIgnoreCase("tgzmc")) {
                    drpcinfo.setTgzmc(XMLTool.getElementText(drpcField[i]));
                } else if (drpcField[i].getAttribute("fieldName").
                           equalsIgnoreCase("tgzgjdm")) {
                    drpcinfo.setTgzgjdm(XMLTool.getElementText(drpcField[i]));
                } else if (drpcField[i].getAttribute("fieldName").
                           equalsIgnoreCase("tgzgjmc")) {
                    drpcinfo.setTgzgjmc(XMLTool.getElementText(drpcField[i]));
                } else if (drpcField[i].getAttribute("fieldName").
                           equalsIgnoreCase("tgzsfzjlx")) {
                    drpcinfo.setTgzsfzjlx(XMLTool.getElementText(drpcField[i]));
                } else if (drpcField[i].getAttribute("fieldName").
                           equalsIgnoreCase("tgzsfzjhm")) {
                    drpcinfo.setTgzsfzjhm(XMLTool.getElementText(drpcField[i]));
                }
                ///////
                //加入建委业务编号
                //////
//                else if (drpcField[i].getAttribute("fieldName").
//                         equalsIgnoreCase("jwywbh"))
//                {
//                    drpcinfo.setJwywbh(XMLTool.getElementText(drpcField[i]));
//                }

            }
            //把导入人信息放入链表第一个节点
            mxList.add(drpcinfo);

            //得到批次处理的明细数据
            //每个record节点代表一套申报数据
            Element[] record = XMLTool.getElements(root, "record");
            int recordLength = record.length;
            for (int i = 0; i < recordLength; i++) {

                Drzb drzb = new Drzb();

                String nsrmc = "";
                String nsrjsjdm = null;
                String fdcxmmc = null;
                String fdcdz = null;
                String htqdrq = null;
                //String cjjgrmb = null;
                Tufwxx tuxx = new Tufwxx();
                String cjjg = null;
                //解析每个record下的行数
                Element[] row = XMLTool.getChildrenElements(record[i], "row");
                int rowLength = row.length;
                for (int j = 0; j < rowLength; j++) {
                    //个人信息(把所有纳税人名+证件号+电话拼串)
                    if (row[j].getAttribute("tableName").equalsIgnoreCase(
                            "qsdb.qs_jl_grxx")) {
                        Element[] field = XMLTool.getChildrenElements(row[j],
                                "Field");
                        int fieldLength = field.length;
                        for (int k = 0; k < fieldLength; k++) {
                            if (field[k].getAttribute("fieldName").
                                equalsIgnoreCase("NSRMC")) {

                                nsrmc = XMLTool.getElementText(field[k]);
                                /*
                                                                //纳税人名称
                                 if (nsrmc.length() >2 ){
                                 nsrmc = nsrmc + "," + XMLTool.getElementText(field[k]);
                                 }else {
                                 nsrmc = nsrmc + XMLTool.getElementText(field[k]);
                                 }
                                 */
                            }
                        }
                    }

                    //非个人信息（纳税人名称、计算机代码只显示主产权人的）
                    if (row[j].getAttribute("tableName").equalsIgnoreCase(
                            "qsdb.qs_jl_fgrxx")) {
                        Element[] field = XMLTool.getChildrenElements(row[j],
                                "Field");
                        if (XMLTool.getElementText(field[6]).equals("1")) {
                            nsrmc = XMLTool.getElementText(field[0]);
                            nsrjsjdm = XMLTool.getElementText(field[1]);
                        }
                        /*
                         int fieldLength = field.length;
                         for (int k = 0; k < fieldLength; k++)
                                                 {
                            if (field[k].getAttribute("fieldName").
                                equalsIgnoreCase("NSRMC"))
                            {
                                nsrmc = XMLTool.getElementText(field[k]);
                            }else if (field[k].getAttribute("fieldName").
                                equalsIgnoreCase("JSJDM"))
                            {
                                nsrjsjdm = XMLTool.getElementText(field[k]);
                            }

                                                 }
                         */
                    }
                    //土地、房屋信息
                    if (row[j].getAttribute("tableName").equalsIgnoreCase(
                            "qsdb.qs_jl_tufwxx")) {
                        Element[] field = XMLTool.getChildrenElements(row[j],
                                "Field");
                        int fieldLength = field.length;
                        for (int k = 0; k < fieldLength; k++) {
                            if (field[k].getAttribute("fieldName").
                                equalsIgnoreCase("FDCXMMC")) {
                                fdcxmmc = XMLTool.getElementText(field[k]);
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("TDFWZLDZ")) {
                                fdcdz = XMLTool.getElementText(field[k]);
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("HTQDSJ")) {
                                htqdrq = XMLTool.getElementText(field[k]);
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("CJJGRMB")) {
                                tuxx.cjjgrmb = DataConvert.String2BigDecimal(
                                        XMLTool.getElementText(field[k]));
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("CJJGWB")) {
                                tuxx.cjjgwb = DataConvert.String2BigDecimal(
                                        XMLTool.getElementText(field[k]));
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("ZHJGRMB")) {
                                tuxx.zhjgrmb = DataConvert.String2BigDecimal(
                                        XMLTool.getElementText(field[k]));
                            } else if (field[k].getAttribute("fieldName").
                                       equalsIgnoreCase("PGJGRMB")) {
                                tuxx.pgjgrmb = DataConvert.String2BigDecimal(
                                        XMLTool.getElementText(field[k]));
                            }
                        }
                    }
                }

                drzb.setDrsjnr(record[i].toString());
                drzb.setNsrmc(nsrmc);
                drzb.setNsrjsjdm(nsrjsjdm);
                drzb.setFdcxmmc(fdcxmmc);
                drzb.setFdcdz(fdcdz);
                drzb.setHtqdrq(DataConvert.String2Timestamp(htqdrq));
                drzb.setCjjg(new BigDecimal(CommonUtil.getCjjg(tuxx)));
                mxList.add(drzb);

            }

        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (SAXException se) {
            throw se;
        } catch (IOException ie) {
            throw ie;
        }

        return mxList;
    }


    /**
     * 解析临时表Drsjnr字段中存储的xml数据，赋值给相应的vo
     * @param xml String    <record></rocord>之间的字符串
     * @return Object       返回PldrBo
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Object getRecord(String xml) throws
            ParserConfigurationException, SAXException, IOException {

        PldrBo bo = new PldrBo(); ;

        //个人信息
        Grxx grxx = null;
        //非个人信息
        Fgrxx fgrxx = null;
        //土地房屋信息
        Tufwxx tufwxx = null;
        //审批结果信息
        Spjgxx spjgxx = null;
        //拆迁信息(Arryalist中的每个元素都是jsblcq对象)
        ArrayList cqxxList = new ArrayList();
        //公有住房信息(Arryalist中的每个元素都是jsblgyzf对象)
        ArrayList gyzfxxList = new ArrayList();
        //房屋交换信息(Arryalist中的每个元素都是PldrBo对象,但是没有拆迁和公房)
        ArrayList fwjhxxList = new ArrayList();
        //个人信息
        ArrayList grxxList = new ArrayList();
        //非个人信息
        ArrayList fgrxxList = new ArrayList();

        try {

            Element root = XMLTool.getRootElement(xml);
            Element[] row = XMLTool.getElements(root, "row");

            int length = row.length;

            String nsrmc = "";
            String zjhm = "";
            String lxdh = "";
            String fgrnsrmc = "";
            String fgrjsjdm = "";
            for (int i = 0; i < length; i++) {

                //如果xml数据中row节点的tableName是个人信息，则给个人信息vo赋值
                if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_grxx")) {
                    grxx = new Grxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;

                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "NSRMC")) {
                            //把个人的多个产权人拼在一起
                            if (nsrmc.length() > 2) {
                                nsrmc = nsrmc + "," +
                                        XMLTool.getElementText(field[j]);
                            } else {
                                nsrmc = nsrmc + XMLTool.getElementText(field[j]);
                            }
                            bo.setNsrmc(nsrmc);
                            grxx.setNsrmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJLX")) {
                            grxx.setSfzjlx(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJLXMC")) {
                            grxx.setSfzjlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJHM")) {
                            //把证件号码拼在一起
                            if (zjhm.length() > 2) {
                                zjhm = zjhm + "," +
                                       XMLTool.getElementText(field[j]);
                            } else {
                                zjhm = zjhm + XMLTool.getElementText(field[j]);
                            }
                            bo.setZjhm(zjhm);
                            grxx.setSfzjhm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("LXDH")) {
                            //联系电话
                            if (lxdh.length() > 2) {
                                lxdh = lxdh + "," +
                                       XMLTool.getElementText(field[j]);
                            } else {
                                lxdh = lxdh + XMLTool.getElementText(field[j]);
                            }
                            bo.setLxdh(lxdh);
                            grxx.setLxdh(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("GJDM")) {
                            grxx.setGjdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("GJMC")) {
                            grxx.setGjmc(XMLTool.getElementText(field[j]));
                        }
                        ///////
                        //加入产权人类型
                        //////
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("ZCQR")) {
                            grxx.setCqrlx(XMLTool.getElementText(field[j]));
                            if ("1".equals(grxx.cqrlx)) {
                                grxx.cqrlx = "0";
                            } else if ("0".equals(grxx.cqrlx)) {
                                grxx.cqrlx = "1";
                            } else {
                                grxx.cqrlx = "";
                            }

                        }

                    }
                    //添加个人信息
                    grxxList.add(grxx);

                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_sbzb")) {
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "SETZ")) {
                            //税额调整类型进行转换全额(01)->全额(2)
                            if (XMLTool.getElementText(field[j]).equals("01")) {
                                bo.setSetz("2");
                            }
                            //税额调整类型进行转换普通标准住宅(02)->减半征收(1)
                            if (XMLTool.getElementText(field[j]).equals("02")) {
                                bo.setSetz("1");
                            }
                            //税额调整类型进行转换经济适用房(03)->经济适用房(6)
                            if (XMLTool.getElementText(field[j]).equals("03")) {
                                bo.setSetz("6");
                            }
                            //税额调整类型进行转换购买90平方米(04)->购买90平方米(5)
                            if (XMLTool.getElementText(field[j]).equals("04")) {
                                bo.setSetz("5");
                            }
                        }
                    }

                    //如果xml数据中row节点的tableName是非个人信息，则给非个人信息vo赋值
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_fgrxx")) {
                    fgrxx = new Fgrxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    if (XMLTool.getElementText(field[6]).equals("1")) {
                        fgrnsrmc = XMLTool.getElementText(field[0]);
                        fgrjsjdm = XMLTool.getElementText(field[1]);
                        bo.setFgrnsrmc(fgrnsrmc);
                        bo.setFgrjsjdm(fgrjsjdm);
                    }
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "NSRMC")) {
                            fgrxx.setNsrmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("JSJDM")) {
                            fgrxx.setJsjdm(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("KHYHDM") ) {
                           fgrxx.setKhyhdm(XMLTool.getElementText(field[j]));
                                                 }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("KHYHMC") ) {
                           fgrxx.setKhyhmc(XMLTool.getElementText(field[j]));
                                                 }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("YHZH") ) {
                           fgrxx.setYhzh(XMLTool.getElementText(field[j]));
                                                 }*/
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("LXRXM")) {
                            fgrxx.setLxrxm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("LXDH")) {
                            fgrxx.setLxdh(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("NSRLXMC")) {
                            fgrxx.setNsrlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("NSRLXDM")) {
                            fgrxx.setNsrlxdm(XMLTool.getElementText(field[j]));
                        }
                        ///////
                        //加入产权人类型
                        //////
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("ZCQR")) {
                            fgrxx.setCqrlx(XMLTool.getElementText(field[j]));
                            if ("1".equals(fgrxx.cqrlx)) {
                                fgrxx.cqrlx = "0";
                            } else if ("0".equals(fgrxx.cqrlx)) {
                                fgrxx.cqrlx = "1";
                            } else {
                                fgrxx.cqrlx = "";
                            }

                        }
                    }
                    fgrxxList.add(fgrxx);
                    //如果xml数据中row节点的tableName是土地房屋信息，则给土地房屋信息vo赋值
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_tufwxx")) {
                    tufwxx = new Tufwxx();
                    //---add by jiq at 20061212---
                    //---设置默认值：非二手房，全额征收
                    tufwxx.setSfesf("00");
                    tufwxx.setSetz("2");
                    //-----------------------------
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "FDCXMMC")) {
                            tufwxx.setFdcxmmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HTQDSJ")) {
                            tufwxx.setHtqdsj(DataConvert.String2Timestamp(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FLDM")) {
                            tufwxx.setFldm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWZLDZ")) {
                            tufwxx.setTdfwzldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWQSZYLX")) {
                            tufwxx.setTdfwqszylx(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWLXDM")) {
                            tufwxx.setFwlxdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWQSZYMJ")) {
                            tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWJZMJ")) {
                            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJGRMB")) {
                            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJGWB")) {
                            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZDM")) {
                            tufwxx.setBzdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("ZHJGRMB")) {
                            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("PGJGRMB")) {
                            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZ")) {
                            tufwxx.setBz(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                            tufwxx.setNd(XMLTool.getElementText(field[j]) );
                                                 }*/
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("TDFWQSZYLXMC")) {
                            tufwxx.setTdfwqszymc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FLMC")) {
                            tufwxx.setFlmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWLXMC")) {
                            tufwxx.setFwlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZMC")) {
                            tufwxx.setBzmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HL")) {
                            tufwxx.setHldm(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWTDBMDM")) {
                            tufwxx.setFwtdbmdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("RJLDM")) {
                            tufwxx.setRjl(XMLTool.getElementText(field[j]));
                            //System.out.println("set tufwxx.rjl"+tufwxx.rjl);
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWJCDM")) {
                            tufwxx.setTdjc(XMLTool.getElementText(field[j]));
                        }

                        //---add by jiq at 20061211-------
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("SFESF")) {
                            tufwxx.setSfesf(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SETZ")) {
                            tufwxx.setSetz(XMLTool.getElementText(field[j]));
                        }
                        //--------------------------------


                    }

                }
                //审批结果信息
                else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_spjgxx")) {
                    spjgxx = new Spjgxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "JMSJE")) {
                            spjgxx.setJmsje(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HDTZSZH")) {
                            spjgxx.setHdtzszh(XMLTool.getElementText(field[j]));
                        }
                    }

                }
                //如果xml数据中row节点的tableName是拆迁信息，则给拆迁信息vo赋值
                //（注意：拆迁可以多条，所以放到ArrayList里面）
                else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_jsblcq")) {
                    Jsblcq jsblcq = new Jsblcq();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "ZLDZ")) {
                            jsblcq.setZldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQBCE")) {
                            jsblcq.setCqbce(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQBCSYE")) {
                            jsblcq.setBcsybce(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQXYH")) {
                            jsblcq.setCqxyh(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                          jsblcq.setNd(XMLTool.getElementText(field[j]) );
                                                }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("BZ") ) {
                          jsblcq.setBz(XMLTool.getElementText(field[j]) );
                                                }*/
                    }
                    cqxxList.add(jsblcq);

                    //如果xml数据中row节点的tableName是公有住房信息，则给公有住房信息vo赋值
                    //（注意：共有住房可以多条，所以放到ArrayList里面）
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_jsblgyzf")) {
                    Jsblgyzf jsblgyzf = new Jsblgyzf();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "YGGYZFQSZSH")) {
                            jsblgyzf.setYggyzfqszsh(XMLTool.getElementText(
                                    field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("ZLDZ")) {
                            jsblgyzf.setZldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("QDSJ")) {
                            jsblgyzf.setQdsj(DataConvert.String2Timestamp(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("JZMJ")) {
                            jsblgyzf.setJzmj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJG")) {
                            jsblgyzf.setCjjg(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BCDKE")) {
                            jsblgyzf.setBcdke(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                          jsblgyzf.setNd(XMLTool.getElementText(field[j]) );
                                                }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("BZ") ) {
                          jsblgyzf.setBz(XMLTool.getElementText(field[j]) );
                                                }*/
                    }
                    gyzfxxList.add(jsblgyzf);
                    //如果xml数据中row节点的tableName是房屋交换信息
                    //（注意：共有住房可以多条，所以放到ArrayList里面）
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_fwjhxx")) {
                    PldrBo fwjhBo = new PldrBo();
                    Element[] fwjh = XMLTool.getChildrenElements(row[i], "Fwjh");
                    for (int k = 0; k < fwjh.length; k++) {
                        if (fwjh[k].getAttribute("tableName").equalsIgnoreCase(
                                "qsdb.qs_jl_grxx")) {
                            Grxx jhgrxx = new Grxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("NSRMC")) {
                                    jhgrxx.setNsrmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJLX")) {
                                    jhgrxx.setSfzjlx(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJLXMC")) {
                                    jhgrxx.setSfzjlxmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJHM")) {
                                    jhgrxx.setSfzjhm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXDH")) {
                                    jhgrxx.setLxdh(XMLTool.getElementText(field[
                                            j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("GJDM")) {
                                    jhgrxx.setGjdm(XMLTool.getElementText(field[
                                            j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("GJMC")) {
                                    jhgrxx.setGjmc(XMLTool.getElementText(field[
                                            j]));
                                }
                            }
                            fwjhBo.setGrxx(jhgrxx);
                        } else if (fwjh[k].getAttribute("tableName").
                                   equalsIgnoreCase("qsdb.qs_jl_fgrxx")) {
                            Fgrxx jhfgrxx = new Fgrxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("NSRMC")) {
                                    jhfgrxx.setNsrmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("KHYHDM")) {
                                    jhfgrxx.setKhyhdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("KHYHMC")) {
                                    jhfgrxx.setKhyhmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("YHZH")) {
                                    jhfgrxx.setYhzh(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXRXM")) {
                                    jhfgrxx.setLxrxm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXDH")) {
                                    jhfgrxx.setLxdh(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("NSRLXMC")) {
                                    jhfgrxx.setNsrlxmc(XMLTool.getElementText(
                                            field[j]));
                                }
                            }
                            fwjhBo.setFgrxx(jhfgrxx);
                        } else if (fwjh[k].getAttribute("tableName").
                                   equalsIgnoreCase("qsdb.qs_jl_tufwxx")) {
                            Tufwxx jhTufwxx = new Tufwxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("FDCXMMC")) {
                                    jhTufwxx.setFdcxmmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("HTQDSJ")) {
                                    jhTufwxx.setHtqdsj(DataConvert.
                                            String2Timestamp(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FLDM")) {
                                    jhTufwxx.setFldm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWZLDZ")) {
                                    jhTufwxx.setTdfwzldz(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWQSZYLX")) {
                                    jhTufwxx.setTdfwqszylx(XMLTool.
                                            getElementText(field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWLXDM")) {
                                    jhTufwxx.setFwlxdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWQSZYMJ")) {
                                    jhTufwxx.setTdfwqszymj(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWJZMJ")) {
                                    jhTufwxx.setFwjzmj(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("CJJGRMB")) {
                                    jhTufwxx.setCjjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("CJJGWB")) {
                                    jhTufwxx.setCjjgwb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZDM")) {
                                    jhTufwxx.setBzdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("ZHJGRMB")) {
                                    jhTufwxx.setZhjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("PGJGRMB")) {
                                    jhTufwxx.setPgjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZ")) {
                                    jhTufwxx.setBz(XMLTool.getElementText(field[
                                            j]));
                                }
                                /*
                                 else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                                 jhTufwxx.setNd(XMLTool.getElementText(field[j]) );
                                                                 }*/
                                else if (field[j].getAttribute("fieldName").
                                         equalsIgnoreCase("TDFWQSZYLXMC")) {
                                    jhTufwxx.setTdfwqszymc(XMLTool.
                                            getElementText(field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FLMC")) {
                                    jhTufwxx.setFlmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWLXMC")) {
                                    jhTufwxx.setFwlxmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZMC")) {
                                    jhTufwxx.setBzmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("HL")) {
                                    jhTufwxx.setHldm(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                }
                            }
                            fwjhBo.setTufwxx(jhTufwxx);
                        }
                    }
                    fwjhxxList.add(fwjhBo);
                }

            }

            bo.setSpjgxx(spjgxx);
            bo.setGrxx(grxx);
            bo.setFgrxx(fgrxx);
            bo.setTufwxx(tufwxx);
            bo.setCqxxList(cqxxList);
            bo.setGyzfxxList(gyzfxxList);
            bo.setFwjhxxList(fwjhxxList);
            bo.setGrxxList(grxxList);
            bo.setFgrxxList(fgrxxList);

        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (SAXException se) {
            throw se;
        } catch (IOException ie) {
            throw ie;
        }

        return bo;
    }


    /**
     * 解析临时表Drsjnr字段中存储的xml数据，赋值给相应的vo
     * @param xml String    <record></rocord>之间的字符串
     * @return Object       返回PldrBo2
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     *
     * 说明：该方法是针对页面中"批量受理(税务人员)"模块
     */
    public static Object getRecord2(String xml) throws
            ParserConfigurationException, SAXException, IOException {

        PldrBo2 bo = null;

        //个人信息
        Grxx grxx = null;
        //非个人信息
        Fgrxx fgrxx = null;
        //土地房屋信息
        Tufwxx tufwxx = null;
        //审批结果信息
        Spjgxx spjgxx = null;
        //拆迁信息(Arryalist中的每个元素都是jsblcq对象)
        ArrayList cqxxList = new ArrayList();
        //公有住房信息(Arryalist中的每个元素都是jsblgyzf对象)
        ArrayList gyzfxxList = new ArrayList();
        //房屋交换信息(Arryalist中的每个元素都是PldrBo对象,但是没有拆迁和公房)
        ArrayList fwjhxxList = new ArrayList();
        //个人信息
        ArrayList grxxList = new ArrayList();
        //非个人信息
        ArrayList fgrxxList = new ArrayList();
        try {

            Element root = XMLTool.getRootElement(xml);
            Element[] row = XMLTool.getElements(root, "row");

            int length = row.length;

            for (int i = 0; i < length; i++) {
                //如果xml数据中row节点的tableName是个人信息，则给个人信息vo赋值
                if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_grxx")) {
                    grxx = new Grxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "NSRMC")) {
                            grxx.setNsrmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJLX")) {
                            //System.out.println("///////////////////////");
                            //System.out.println("zjlx:"+ XMLTool.getElementText(field[j]));
                            //System.out.println("///////////////////////");
                            grxx.setSfzjlx(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJLXMC")) {
                            grxx.setSfzjlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SFZJHM")) {
                            grxx.setSfzjhm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("LXDH")) {
                            grxx.setLxdh(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("GJDM")) {
                            grxx.setGjdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("GJMC")) {
                            grxx.setGjmc(XMLTool.getElementText(field[j]));
                        }
                    }

                    //添加个人信息
                    grxxList.add(grxx);
                    //如果xml数据中row节点的tableName是非个人信息，则给非个人信息vo赋值
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_fgrxx")) {
                    fgrxx = new Fgrxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "NSRMC")) {
                            fgrxx.setNsrmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("JSJDM")) {
                            fgrxx.setJsjdm(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("KHYHDM") ) {
                           fgrxx.setKhyhdm(XMLTool.getElementText(field[j]));
                                                 }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("KHYHMC") ) {
                           fgrxx.setKhyhmc(XMLTool.getElementText(field[j]));
                                                 }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("YHZH") ) {
                           fgrxx.setYhzh(XMLTool.getElementText(field[j]));
                                                 }*/
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("LXRXM")) {
                            fgrxx.setLxrxm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("LXDH")) {
                            fgrxx.setLxdh(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("NSRLXMC")) {
                            fgrxx.setNsrlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("NSRLXDM")) {
                            fgrxx.setNsrlxdm(XMLTool.getElementText(field[j]));
                        }
                    }
                    fgrxxList.add(fgrxx);
                    //如果xml数据中row节点的tableName是土地房屋信息，则给土地房屋信息vo赋值
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_tufwxx")) {
                    tufwxx = new Tufwxx();
                    //---add by jiq at 20061212---
                    //---设置默认值：非二手房，全额征收
                    tufwxx.setSfesf("00");
                    tufwxx.setSetz("2");
                    //-----------------------------
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "FDCXMMC")) {
                            tufwxx.setFdcxmmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HTQDSJ")) {
                            tufwxx.setHtqdsj(DataConvert.String2Timestamp(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FLDM")) {
                            tufwxx.setFldm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWZLDZ")) {
                            tufwxx.setTdfwzldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWQSZYLX")) {
                            tufwxx.setTdfwqszylx(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWLXDM")) {
                            tufwxx.setFwlxdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("TDFWQSZYMJ")) {
                            tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWJZMJ")) {
                            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJGRMB")) {
                            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJGWB")) {
                            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZDM")) {
                            tufwxx.setBzdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("ZHJGRMB")) {
                            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("PGJGRMB")) {
                            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZ")) {
                            tufwxx.setBz(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                            tufwxx.setNd(XMLTool.getElementText(field[j]) );
                                                 }*/
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("TDFWQSZYLXMC")) {
                            tufwxx.setTdfwqszymc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FLMC")) {
                            tufwxx.setFlmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWLXMC")) {
                            tufwxx.setFwlxmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BZMC")) {
                            tufwxx.setBzmc(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HL")) {
                            tufwxx.setHldm(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWTDBMDM")) {
                            tufwxx.setFwtdbmdm(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("RJLDM")) {
                            tufwxx.setRjl(XMLTool.getElementText(field[j]));
                            //System.out.println("set tufwxx.rjl"+tufwxx.rjl);
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("FWJCDM")) {
                            tufwxx.setTdjc(XMLTool.getElementText(field[j]));
                        }

                        //---add by jiq at 20061211-------
                        else if (field[j].getAttribute("fieldName").
                                 equalsIgnoreCase("SFESF")) {
                            tufwxx.setSfesf(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("SETZ")) {
                            tufwxx.setSetz(XMLTool.getElementText(field[j]));
                        }
                        //--------------------------------


                    }

                }
                //审批结果信息
                else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_spjgxx")) {
                    spjgxx = new Spjgxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "JMSJE")) {
                            spjgxx.setJmsje(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("HDTZSZH")) {
                            spjgxx.setHdtzszh(XMLTool.getElementText(field[j]));
                        }
                    }

                }
                //如果xml数据中row节点的tableName是拆迁信息，则给拆迁信息vo赋值
                //（注意：拆迁可以多条，所以放到ArrayList里面）
                else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_jsblcq")) {
                    Jsblcq jsblcq = new Jsblcq();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "ZLDZ")) {
                            jsblcq.setZldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQBCE")) {
                            jsblcq.setCqbce(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQBCSYE")) {
                            jsblcq.setBcsybce(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CQXYH")) {
                            jsblcq.setCqxyh(XMLTool.getElementText(field[j]));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                          jsblcq.setNd(XMLTool.getElementText(field[j]) );
                                                }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("BZ") ) {
                          jsblcq.setBz(XMLTool.getElementText(field[j]) );
                                                }*/
                    }
                    cqxxList.add(jsblcq);

                    //如果xml数据中row节点的tableName是公有住房信息，则给公有住房信息vo赋值
                    //（注意：共有住房可以多条，所以放到ArrayList里面）
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_jsblgyzf")) {
                    Jsblgyzf jsblgyzf = new Jsblgyzf();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "YGGYZFQSZSH")) {
                            jsblgyzf.setYggyzfqszsh(XMLTool.getElementText(
                                    field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("ZLDZ")) {
                            jsblgyzf.setZldz(XMLTool.getElementText(field[j]));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("QDSJ")) {
                            jsblgyzf.setQdsj(DataConvert.String2Timestamp(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("JZMJ")) {
                            jsblgyzf.setJzmj(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("CJJG")) {
                            jsblgyzf.setCjjg(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        } else if (field[j].getAttribute("fieldName").
                                   equalsIgnoreCase("BCDKE")) {
                            jsblgyzf.setBcdke(DataConvert.String2BigDecimal(
                                    XMLTool.getElementText(field[j])));
                        }
                        /*
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                          jsblgyzf.setNd(XMLTool.getElementText(field[j]) );
                                                }
                         else if(field[j].getAttribute("fieldName").equalsIgnoreCase("BZ") ) {
                          jsblgyzf.setBz(XMLTool.getElementText(field[j]) );
                                                }*/
                    }
                    gyzfxxList.add(jsblgyzf);
                    //如果xml数据中row节点的tableName是房屋交换信息
                    //（注意：共有住房可以多条，所以放到ArrayList里面）
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_fwjhxx")) {
                    PldrBo2 fwjhBo = new PldrBo2();
                    Element[] fwjh = XMLTool.getChildrenElements(row[i], "Fwjh");
                    for (int k = 0; k < fwjh.length; k++) {
                        if (fwjh[k].getAttribute("tableName").equalsIgnoreCase(
                                "qsdb.qs_jl_grxx")) {
                            Grxx jhgrxx = new Grxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("NSRMC")) {
                                    jhgrxx.setNsrmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJLX")) {
                                    System.out.println(
                                            "///////////////////////");
                                    System.out.println("zjlx:" +
                                            XMLTool.getElementText(field[j]));
                                    System.out.println(
                                            "///////////////////////");

                                    jhgrxx.setSfzjlx(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJLXMC")) {
                                    jhgrxx.setSfzjlxmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("SFZJHM")) {
                                    jhgrxx.setSfzjhm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXDH")) {
                                    jhgrxx.setLxdh(XMLTool.getElementText(field[
                                            j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("GJDM")) {
                                    jhgrxx.setGjdm(XMLTool.getElementText(field[
                                            j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("GJMC")) {
                                    jhgrxx.setGjmc(XMLTool.getElementText(field[
                                            j]));
                                }
                            }
                            fwjhBo.setGrxx(jhgrxx);
                        } else if (fwjh[k].getAttribute("tableName").
                                   equalsIgnoreCase("qsdb.qs_jl_fgrxx")) {
                            Fgrxx jhfgrxx = new Fgrxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("NSRMC")) {
                                    jhfgrxx.setNsrmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("KHYHDM")) {
                                    jhfgrxx.setKhyhdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("KHYHMC")) {
                                    jhfgrxx.setKhyhmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("YHZH")) {
                                    jhfgrxx.setYhzh(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXRXM")) {
                                    jhfgrxx.setLxrxm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("LXDH")) {
                                    jhfgrxx.setLxdh(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("NSRLXMC")) {
                                    jhfgrxx.setNsrlxmc(XMLTool.getElementText(
                                            field[j]));
                                }
                            }
                            fwjhBo.setFgrxx(jhfgrxx);
                        } else if (fwjh[k].getAttribute("tableName").
                                   equalsIgnoreCase("qsdb.qs_jl_tufwxx")) {
                            Tufwxx jhTufwxx = new Tufwxx();
                            Element[] field = XMLTool.getChildrenElements(fwjh[
                                    k], "Field");
                            int fieldLength = field.length;
                            for (int j = 0; j < fieldLength; j++) {
                                if (field[j].getAttribute("fieldName").
                                    equalsIgnoreCase("FDCXMMC")) {
                                    jhTufwxx.setFdcxmmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("HTQDSJ")) {
                                    jhTufwxx.setHtqdsj(DataConvert.
                                            String2Timestamp(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FLDM")) {
                                    jhTufwxx.setFldm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWZLDZ")) {
                                    jhTufwxx.setTdfwzldz(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWQSZYLX")) {
                                    jhTufwxx.setTdfwqszylx(XMLTool.
                                            getElementText(field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWLXDM")) {
                                    jhTufwxx.setFwlxdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("TDFWQSZYMJ")) {
                                    jhTufwxx.setTdfwqszymj(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWJZMJ")) {
                                    jhTufwxx.setFwjzmj(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("CJJGRMB")) {
                                    jhTufwxx.setCjjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("CJJGWB")) {
                                    jhTufwxx.setCjjgwb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZDM")) {
                                    jhTufwxx.setBzdm(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("ZHJGRMB")) {
                                    jhTufwxx.setZhjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("PGJGRMB")) {
                                    jhTufwxx.setPgjgrmb(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZ")) {
                                    jhTufwxx.setBz(XMLTool.getElementText(field[
                                            j]));
                                }
                                /*
                                 else if(field[j].getAttribute("fieldName").equalsIgnoreCase("ND") ) {
                                 jhTufwxx.setNd(XMLTool.getElementText(field[j]) );
                                                                 }*/
                                else if (field[j].getAttribute("fieldName").
                                         equalsIgnoreCase("TDFWQSZYLXMC")) {
                                    jhTufwxx.setTdfwqszymc(XMLTool.
                                            getElementText(field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FLMC")) {
                                    jhTufwxx.setFlmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("FWLXMC")) {
                                    jhTufwxx.setFwlxmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("BZMC")) {
                                    jhTufwxx.setBzmc(XMLTool.getElementText(
                                            field[j]));
                                } else if (field[j].getAttribute("fieldName").
                                           equalsIgnoreCase("HL")) {
                                    jhTufwxx.setHldm(DataConvert.
                                            String2BigDecimal(XMLTool.
                                            getElementText(field[j])));
                                }
                            }
                            fwjhBo.setTufwxx(jhTufwxx);
                        }
                    }
                    fwjhxxList.add(fwjhBo);
                }

            }

            bo = new PldrBo2();

            bo.setSpjgxx(spjgxx);
            bo.setGrxx(grxx);
            bo.setFgrxx(fgrxx);
            bo.setTufwxx(tufwxx);
            bo.setCqxxList(cqxxList);
            bo.setGyzfxxList(gyzfxxList);
            bo.setFwjhxxList(fwjhxxList);
            bo.setGrxxList(grxxList);
            bo.setFgrxxList(fgrxxList);

        } catch (ParserConfigurationException pce) {
            throw pce;
        } catch (SAXException se) {
            throw se;
        } catch (IOException ie) {
            throw ie;
        }

        return bo;
    }


    /**
     * 把正式表中的数据存储为xml，用于批量打印时生成excel
     * @param sbxxList ArrayList    存储的是PldrBo
     * @return String        生成的xml字符串
     * @throws ParserConfigurationException
     */
    public static String createXML(ArrayList sbxxList) throws
            ParserConfigurationException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        StringBuffer xml = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"gb2312\"?>");

        //实例化一个Document
        DocumentBuilderFactory fac = ((DocumentBuilderFactoryImpl) Class.
                                      forName(
                                              "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl").
                                      newInstance());

        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.newDocument();

        //每一个Element是对一个标记
        Element root = doc.createElement("data"); //最外层的data标记

        for (Iterator iter = sbxxList.iterator(); iter.hasNext(); ) {
            PldrBo bo = (PldrBo) iter.next();
            Element record = doc.createElement("record"); //record标记

            if (bo.getSbzb() != null) {
                Sbzb sbzb = bo.getSbzb();
                addNewSbzbData(doc, record, sbzb);
            }

            if (bo.getGrxx() != null) {
                Grxx grxx = bo.getGrxx();
                addNewGrxxData(doc, record, grxx);
            }

            if (bo.getFgrxx() != null) {
                Fgrxx fgrxx = bo.getFgrxx();
                addNewFgrxxData(doc, record, fgrxx);
            }

            if (bo.getSpjgxx() != null) {
                Spjgxx spjgxx = bo.getSpjgxx();
                addNewSpjgxxData(doc, record, spjgxx);
            }

            if (bo.getTufwxx() != null) {
                Tufwxx tufwxx = bo.getTufwxx();
                addNewTufwxxData(doc, record, tufwxx);
            }

            if (!bo.getCqxxList().isEmpty()) {
                Jsblcq cqxx[] = (Jsblcq[]) (bo.getCqxxList().toArray(new Jsblcq[
                        0]));
                addNewCqxxData(doc, record, cqxx);
            }

            if (!bo.getGyzfxxList().isEmpty()) {
                Jsblgyzf gyzf[] = (Jsblgyzf[]) (bo.getGyzfxxList().toArray(new
                        Jsblgyzf[0]));
                addNewGyzfxxData(doc, record, gyzf);
            }

            if (!bo.getFwjhxxList().isEmpty()) {
                //房屋交换只能一条目前
                PldrBo fwjhxx = (PldrBo) bo.getFwjhxxList().get(0);
                addNewFwjhxxData(doc, record, fwjhxx);
            }

            if (bo.getHdbo() != null) {
                JghdsjBo hdbo = bo.getHdbo();
                addNewJghdData(doc, record, hdbo);
            }

            root.appendChild(record);
        }

        xml.append(root.toString());
        return xml.toString();
    }


    /**
     * 把正式表中的数据存储为xml，用于批量打印时生成excel
     * @param sbxxList ArrayList    存储的是PldrBo2
     * @return String        生成的xml字符串
     * @throws ParserConfigurationException
     *
     * 说明：该方法是针对页面中"批量受理(税务人员)"模块
     */
    public static String createXML2(ArrayList sbxxList) throws
            ParserConfigurationException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        StringBuffer xml = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"gb2312\"?>");

        //实例化一个Document
        DocumentBuilderFactory fac = ((DocumentBuilderFactoryImpl) Class.
                                      forName(
                                              "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl").
                                      newInstance());

        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.newDocument();

        //每一个Element是对一个标记
        Element root = doc.createElement("data"); //最外层的data标记

        for (Iterator iter = sbxxList.iterator(); iter.hasNext(); ) {
            PldrBo2 bo = (PldrBo2) iter.next();
            Element record = doc.createElement("record"); //record标记

            if (bo.getSbzb() != null) {
                Sbzb sbzb = bo.getSbzb();
                addNewSbzbData(doc, record, sbzb);
            }

            if (bo.getGrxx() != null) {
                Grxx grxx = bo.getGrxx();
                addNewGrxxData(doc, record, grxx);
            }

            if (bo.getFgrxx() != null) {
                Fgrxx fgrxx = bo.getFgrxx();
                addNewFgrxxData(doc, record, fgrxx);
            }

            if (bo.getSpjgxx() != null) {
                Spjgxx spjgxx = bo.getSpjgxx();
                addNewSpjgxxData(doc, record, spjgxx);
            }

            if (bo.getTufwxx() != null) {
                Tufwxx tufwxx = bo.getTufwxx();
                addNewTufwxxData(doc, record, tufwxx);
            }

            if (!bo.getCqxxList().isEmpty()) {
                Jsblcq cqxx[] = (Jsblcq[]) (bo.getCqxxList().toArray(new Jsblcq[
                        0]));
                addNewCqxxData(doc, record, cqxx);
            }

            if (!bo.getGyzfxxList().isEmpty()) {
                Jsblgyzf gyzf[] = (Jsblgyzf[]) (bo.getGyzfxxList().toArray(new
                        Jsblgyzf[0]));
                addNewGyzfxxData(doc, record, gyzf);
            }

            if (!bo.getFwjhxxList().isEmpty()) {
                //房屋交换只能一条目前
                PldrBo2 fwjhxx = (PldrBo2) bo.getFwjhxxList().get(0);
                addNewFwjhxxData2(doc, record, fwjhxx);
            }

            if (bo.getHdbo() != null) {
                JghdsjBo hdbo = bo.getHdbo();
                addNewJghdData(doc, record, hdbo);
            }

            root.appendChild(record);
        }

        xml.append(root.toString());
        return xml.toString();
    }


    /**
     * 创建<record></record>节点中的个人信息子节点
     * @param doc Document         XML文件的document对象
     * @param record Element       <record></record>节点，代表一整套申报
     * @param sbzb Sbzb    申报主表
     */
    private static void addNewSbzbData(Document doc, Element record, Sbzb sbzb) {
        Element sbzbRow = doc.createElement("row"); //row标记，存放申报主表信息
        sbzbRow.setAttribute("tableName", "QSDB.QS_JL_SBZB");
        sbzbRow.setAttribute("action", "PRINT");

        //field标记--备注
        Element field0 = doc.createElement("Field");
        field0.setAttribute("fieldName", "SBBH");
        field0.setAttribute("maxLength", "20");
        field0.appendChild(doc.createTextNode(sbzb.getSbbh()));
        sbzbRow.appendChild(field0);

        //field标记--申报日期
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "SBRQ");
        field1.setAttribute("maxLength", "8");
        field1.appendChild(doc.createTextNode(DataConvert.Date2String(sbzb.
                getSbrq())));
        sbzbRow.appendChild(field1);

        //field标记--缴税方式名称
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "JSFSMC");
        field2.setAttribute("maxLength", "20");
        field2.appendChild(doc.createTextNode(sbzb.getJsfsmc()));
        sbzbRow.appendChild(field2);

        //field标记--备注
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "BZ");
        field3.setAttribute("maxLength", "100");
        field3.appendChild(doc.createTextNode(sbzb.getBz()));
        sbzbRow.appendChild(field3);

        record.appendChild(sbzbRow);
    }

    /**
     * 创建<record></record>节点中的个人信息子节点
     * @param doc Document         XML文件的document对象
     * @param record Element       <record></record>节点，代表一整套申报
     * @param grxx Grxx            个人信息
     */
    private static void addNewGrxxData(Document doc, Element record, Grxx grxx) {
        Element grxxRow = doc.createElement("row"); //row标记，存放个人信息
        grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");
        grxxRow.setAttribute("action", "PRINT");

        //field标记--纳税人名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "NSRMC");
        field1.setAttribute("maxLength", "50");
        field1.appendChild(doc.createTextNode(grxx.getNsrmc()));
        grxxRow.appendChild(field1);

        //field标记--身份证件类型（代码）
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "SFZJLX");
        field2.setAttribute("maxLength", "2");
        field2.appendChild(doc.createTextNode(grxx.getSfzjlx()));
        grxxRow.appendChild(field2);

        //field标记--身份证件类型名称
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "SFZJLXMC");
        field3.setAttribute("maxLength", "30");
        field3.appendChild(doc.createTextNode(grxx.getSfzjlxmc()));
        grxxRow.appendChild(field3);

        //field标记--身份证件号码
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "SFZJHM");
        field4.setAttribute("maxLength", "30");
        field4.appendChild(doc.createTextNode(grxx.getSfzjhm()));
        grxxRow.appendChild(field4);

        //field标记--联系电话
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "LXDH");
        field5.setAttribute("maxLength", "20");
        field5.appendChild(doc.createTextNode(grxx.getLxdh()));
        grxxRow.appendChild(field5);

        //field标记--国家代码
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "GJDM");
        field6.setAttribute("maxLength", "10");
        field6.appendChild(doc.createTextNode(grxx.getGjdm()));
        grxxRow.appendChild(field6);

        //field标记--国家名称
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "GJMC");
        field7.setAttribute("maxLength", "60");
        field7.appendChild(doc.createTextNode(grxx.getGjmc()));
        grxxRow.appendChild(field7);

        record.appendChild(grxxRow);
    }

    /**
     * 创建<record></record>节点中的非个人信息子节点
     * @param doc Document       XML文件的document对象
     * @param record Element     <record></record>节点，代表一整套申报
     * @param fgrxx Fgrxx        非个人信息
     */
    public static void addNewFgrxxData(Document doc, Element record,
                                       Fgrxx fgrxx) {

        Element fgrxxRow = doc.createElement("row"); //row标记，存放个人信息
        fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");
        fgrxxRow.setAttribute("action", "PRINT");

        //field标记--纳税人名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "NSRMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fgrxx.getNsrmc()));
        fgrxxRow.appendChild(field1);

        //field标记--纳税人计算机代码
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "JSJDM");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(fgrxx.getJsjdm()));
        fgrxxRow.appendChild(field2);

        //field标记--开户银行代码
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "KHYHDM");
        field3.setAttribute("maxLength", "100");
        field3.appendChild(doc.createTextNode(fgrxx.getKhyhdm()));
        fgrxxRow.appendChild(field3);

        //field标记--开户银行名称
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "KHYHMC");
        field4.setAttribute("maxLength", "100");
        field4.appendChild(doc.createTextNode(fgrxx.getKhyhmc()));
        fgrxxRow.appendChild(field4);

        //field标记--银行帐号
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "YHZH");
        field5.setAttribute("maxLength", "100");
        field5.appendChild(doc.createTextNode(fgrxx.getYhzh()));
        fgrxxRow.appendChild(field5);

        //field标记--联系人姓名
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "LXRXM");
        field6.setAttribute("maxLength", "50");
        field6.appendChild(doc.createTextNode(fgrxx.getLxrxm()));
        fgrxxRow.appendChild(field6);

        //field标记--联系电话
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "LXDH");
        field7.setAttribute("maxLength", "100");
        field7.appendChild(doc.createTextNode(fgrxx.getLxdh()));
        fgrxxRow.appendChild(field7);

        //field标记--纳税人类型名称
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "NSRLXMC");
        field8.setAttribute("maxLength", "50");
        field8.appendChild(doc.createTextNode(fgrxx.getNsrlxmc()));
        fgrxxRow.appendChild(field8);

        //field标记--纳税人类型代码
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "NSRLXDM");
        field9.setAttribute("maxLength", "2");
        field9.appendChild(doc.createTextNode(fgrxx.getNsrlxdm()));
        fgrxxRow.appendChild(field9);

        record.appendChild(fgrxxRow);
    }


    /**
     * 创建<record></record>节点中的审批结果子节点
     * @param doc Document         XML文件的document对象
     * @param record Element       <record></record>节点，代表一整套申报
     * @param spjgxx Spjgxx    审批结果信息
     */
    private static void addNewSpjgxxData(Document doc, Element record,
                                         Spjgxx spjgxx) {
        Element spjgRow = doc.createElement("row"); //row标记，存放审批结果信息
        spjgRow.setAttribute("tableName", "QSDB.QS_JL_SPJGXX");
        spjgRow.setAttribute("action", "PRINT");

        //field标记--减免税金额
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "JMSJE");
        field1.setAttribute("maxLength", "15");
        field1.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                spjgxx.getJmsje())));
        spjgRow.appendChild(field1);

        //field标记--核定通知书号（代码）
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HDTZSZH");
        field2.setAttribute("maxLength", "50");
        field2.appendChild(doc.createTextNode(spjgxx.getHdtzszh()));
        spjgRow.appendChild(field2);

        record.appendChild(spjgRow);
    }

    /**
     * 创建<record></record>节点中的房屋土地信息子节点
     * @param doc Document       XML文件的document对象
     * @param record Element     新建的<record></record>节点
     * @param tufwxx Tufwxx      房屋土地信息
     */
    private static void addNewTufwxxData(Document doc, Element record,
                                         Tufwxx tufwxx) {

        Element tufwxxRow = doc.createElement("row"); //row标记，存放土地房屋信息
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");
        tufwxxRow.setAttribute("action", "PRINT");

        //field标记--房地产项目名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(tufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field标记--合同签订时间
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(tufwxx.
                getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field标记--分类代码
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "FLDM");
        field3.setAttribute("maxLength", "2");
        field3.appendChild(doc.createTextNode(tufwxx.getFldm()));
        tufwxxRow.appendChild(field3);

        //field标记--土地房屋座落地址
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(tufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field标记--土地房屋权属转移类型
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(tufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field标记--房屋类型代码
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "FWLXDM");
        field6.setAttribute("maxLength", "2");
        field6.appendChild(doc.createTextNode(tufwxx.getFwlxdm()));
        tufwxxRow.appendChild(field6);

        //field标记--土地房屋权属转移面积
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field标记--房屋建筑面积
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field标记--成交价格人民币
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field标记--成交价格外币
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field标记--币种代码
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(tufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field标记--折合价格人民币
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field标记--评估价格人民币
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field标记--备注
        /*Element field14 = doc.createElement("Field");
                 field14.setAttribute("fieldName", "BZ");
                 field14.setAttribute("maxLength", "100");
                 field14.appendChild(doc.createTextNode(tufwxx.getBz()));
                 tufwxxRow.appendChild(field14);*/

        //field标记--土地房屋权属转移类型名称
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(tufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field标记--分类名称
        Element field16 = doc.createElement("Field");
        field16.setAttribute("fieldName", "FLMC");
        field16.setAttribute("maxLength", "100");
        field16.appendChild(doc.createTextNode(tufwxx.getFlmc()));
        tufwxxRow.appendChild(field16);

        //field标记--房屋类型名称
        Element field17 = doc.createElement("Field");
        field17.setAttribute("fieldName", "FWLXMC");
        field17.setAttribute("maxLength", "100");
        field17.appendChild(doc.createTextNode(tufwxx.getFwlxmc()));
        tufwxxRow.appendChild(field17);

        //field标记--币种名称
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(tufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field标记--汇率
        Element field19 = doc.createElement("Field");
        field19.setAttribute("fieldName", "HL");
        field19.setAttribute("maxLength", "15");
        field19.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getHldm())));
        tufwxxRow.appendChild(field19);

        //field标记--容积率
        Element field20 = doc.createElement("Field");
        field20.setAttribute("fieldName", "RJLDM");
        field20.setAttribute("maxLength", "2");
        field20.appendChild(doc.createTextNode(tufwxx.getRjl()));
        tufwxxRow.appendChild(field20);

        //field标记--土地级次
        Element field21 = doc.createElement("Field");
        field21.setAttribute("fieldName", "FWJCDM");
        field21.setAttribute("maxLength", "2");
        field21.appendChild(doc.createTextNode(tufwxx.getTdjc()));
        tufwxxRow.appendChild(field21);
        //field标记--土地房屋受理号
        /*Element field20 = doc.createElement("Field");
                 field20.setAttribute("fieldName", "FWTDBMDM");
                 field20.setAttribute("maxLength", "100");
                 field20.appendChild(doc.createTextNode(tufwxx.getFwtdbmdm()));
                 tufwxxRow.appendChild(field20);*/

        record.appendChild(tufwxxRow);
    }

    /**
     * 创建<record></record>节点中的房屋拆迁信息子节点(可以多条)
     * @param doc Document       XML文件的document对象
     * @param record Element     <record></record>节点，代表一整套申报
     * @param cqxx Jsblcq[]        拆迁信息数组
     */
    private static void addNewCqxxData(Document doc, Element record,
                                       Jsblcq[] cqxx) {

        int size = cqxx.length;

        for (int i = 0; i < size; i++) {

            Element cqxxRow = doc.createElement("row"); //row标记，存放拆迁信息
            cqxxRow.setAttribute("tableName", "QSDB.QS_JL_JSBLCQ");
            cqxxRow.setAttribute("action", "PRINT");

            //field标记--被拆迁房屋座落地址
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "ZLDZ");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(cqxx[i].getZldz()));
            cqxxRow.appendChild(field1);

            //field标记--拆迁补偿额
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "CQBCE");
            field2.setAttribute("maxLength", "15");
            field2.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getCqbce())));
            cqxxRow.appendChild(field2);

            //field标记--拆迁本次使用额
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "BCSYBCE");
            field3.setAttribute("maxLength", "15");
            field3.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getBcsybce())));
            cqxxRow.appendChild(field3);

            //field标记--拆迁补偿剩余额
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "CQBCSYE");
            field5.setAttribute("maxLength", "15");
            field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getCqbcsye())));
            cqxxRow.appendChild(field5);

            //field标记--拆迁协议号
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "CQXYH");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(cqxx[i].getCqxyh()));
            cqxxRow.appendChild(field4);

            record.appendChild(cqxxRow);
        }
    }

    /**
     * 创建<record></record>节点中的公有住房信息子节点(可以多条)
     * @param doc Document       XML文件的document对象
     * @param record Element     <record></record>节点，代表一整套申报
     * @param gyzfxx Gyzfxx[]    公有住房数组
     */
    private static void addNewGyzfxxData(Document doc, Element record,
                                         Jsblgyzf[] gyzfxx) {

        int size = gyzfxx.length;

        for (int i = 0; i < size; i++) {
            Element gyzfxxRow = doc.createElement("row"); //row标记，存放公有住房信息
            gyzfxxRow.setAttribute("tableName", "QSDB.QS_JL_JSBLGYZF");
            gyzfxxRow.setAttribute("action", "PRINT");

            //field标记--已购公有住房权属证书号
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "YGGYZFQSZSH");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(gyzfxx[i].getYggyzfqszsh()));
            gyzfxxRow.appendChild(field1);

            //field标记--座落地址
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "ZLDZ");
            field2.setAttribute("maxLength", "100");
            field2.appendChild(doc.createTextNode(gyzfxx[i].getZldz()));
            gyzfxxRow.appendChild(field2);

            //field标记--合同签订时间
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "QDSJ");
            field3.setAttribute("maxLength", "8");
            field3.appendChild(doc.createTextNode(DataConvert.Date2String(
                    gyzfxx[i].getQdsj())));
            gyzfxxRow.appendChild(field3);

            //field标记--建筑面积
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "JZMJ");
            field4.setAttribute("maxLength", "14");
            field4.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getJzmj())));
            gyzfxxRow.appendChild(field4);

            //field标记--成交价格
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "CJJG");
            field5.setAttribute("maxLength", "15");
            field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getCjjg())));
            gyzfxxRow.appendChild(field5);

            //field标记--本次抵扣额
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "BCDKE");
            field6.setAttribute("maxLength", "15");
            field6.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getBcdke())));
            gyzfxxRow.appendChild(field6);

            //field标记--剩余额
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "SYE");
            field7.setAttribute("maxLength", "15");
            field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getSye())));
            gyzfxxRow.appendChild(field7);

            record.appendChild(gyzfxxRow);
        }
    }

    private static void addNewFwjhxxData(Document doc, Element record,
                                         PldrBo fwjhxx) {

        Element fwjhxxRow = doc.createElement("row"); //row标记，存放房屋交换信息
        fwjhxxRow.setAttribute("tableName", "QSDB.QS_JL_FWJHXX");
        fwjhxxRow.setAttribute("action", "PRINT");

        //如果房屋交换中录入了个人信息
        if (fwjhxx.getGrxx() != null) {
            Grxx fwjhGrxx = fwjhxx.getGrxx();
            //row标记，存放房屋交换中的被交换人个人信息
            Element grxxRow = doc.createElement("Fwjh");
            grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");

            //field标记--纳税人名称
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(fwjhGrxx.getNsrmc()));
            grxxRow.appendChild(field1);

            //field标记--身份证件类型（代码）
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "SFZJLX");
            field2.setAttribute("maxLength", "2");
            field2.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlx()));
            grxxRow.appendChild(field2);

            //field标记--身份证件类型名称
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "SFZJLXMC");
            field3.setAttribute("maxLength", "30");
            field3.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlxmc()));
            grxxRow.appendChild(field3);

            //field标记--身份证件号码
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "SFZJHM");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(fwjhGrxx.getSfzjhm()));
            grxxRow.appendChild(field4);

            //field标记--联系电话
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "LXDH");
            field5.setAttribute("maxLength", "20");
            field5.appendChild(doc.createTextNode(fwjhGrxx.getLxdh()));
            grxxRow.appendChild(field5);

            //field标记--国家代码
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "GJDM");
            field6.setAttribute("maxLength", "10");
            field6.appendChild(doc.createTextNode(fwjhGrxx.getGjdm()));
            grxxRow.appendChild(field6);

            //field标记--国家名称
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "GJMC");
            field7.setAttribute("maxLength", "60");
            field7.appendChild(doc.createTextNode(fwjhGrxx.getGjmc()));
            grxxRow.appendChild(field7);

            fwjhxxRow.appendChild(grxxRow);

        } else if (fwjhxx.getFgrxx() != null) {
            Fgrxx fwjhFgrxx = fwjhxx.getFgrxx();
            //row标记，存放房屋交换中的被交换非人个人信息
            Element fgrxxRow = doc.createElement("Fwjh");
            fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");

            //field标记--纳税人名称
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(fwjhFgrxx.getNsrmc()));
            fgrxxRow.appendChild(field1);

            //field标记--纳税人计算机代码
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "JSJDM");
            field2.setAttribute("maxLength", "8");
            field2.appendChild(doc.createTextNode(fwjhFgrxx.getJsjdm()));
            fgrxxRow.appendChild(field2);

            //field标记--开户银行代码
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "KHYHDM");
            field3.setAttribute("maxLength", "100");
            field3.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhdm()));
            fgrxxRow.appendChild(field3);

            //field标记--开户银行名称
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "KHYHMC");
            field4.setAttribute("maxLength", "100");
            field4.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhmc()));
            fgrxxRow.appendChild(field4);

            //field标记--银行帐号
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "YHZH");
            field5.setAttribute("maxLength", "100");
            field5.appendChild(doc.createTextNode(fwjhFgrxx.getYhzh()));
            fgrxxRow.appendChild(field5);

            //field标记--联系人姓名
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "LXRXM");
            field6.setAttribute("maxLength", "50");
            field6.appendChild(doc.createTextNode(fwjhFgrxx.getLxrxm()));
            fgrxxRow.appendChild(field6);

            //field标记--联系电话
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "LXDH");
            field7.setAttribute("maxLength", "100");
            field7.appendChild(doc.createTextNode(fwjhFgrxx.getLxdh()));
            fgrxxRow.appendChild(field7);

            //field标记--纳税人类型名称
            Element field8 = doc.createElement("Field");
            field8.setAttribute("fieldName", "NSRLXMC");
            field8.setAttribute("maxLength", "50");
            field8.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxmc()));
            fgrxxRow.appendChild(field8);

            //field标记--纳税人类型代码
            Element field9 = doc.createElement("Field");
            field9.setAttribute("fieldName", "NSRLXDM");
            field9.setAttribute("maxLength", "2");
            field9.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxdm()));
            fgrxxRow.appendChild(field9);

            fwjhxxRow.appendChild(fgrxxRow);
        }

        //房屋交换中的土地房屋信息
        Tufwxx fwjhTufwxx = fwjhxx.getTufwxx();
        Element tufwxxRow = doc.createElement("Fwjh");
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");

        //field标记--房地产项目名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fwjhTufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field标记--合同签订时间
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(
                fwjhTufwxx.getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field标记--土地房屋座落地址
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field标记--土地房屋权属转移类型
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field标记--土地房屋权属转移面积
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field标记--房屋建筑面积
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field标记--成交价格人民币
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field标记--成交价格外币
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field标记--币种代码
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(fwjhTufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field标记--折合价格人民币
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field标记--评估价格人民币
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field标记--土地房屋权属转移类型名称
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field标记--币种名称
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(fwjhTufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field标记--汇率
        Element field19 = doc.createElement("Field");
        field19.setAttribute("fieldName", "HL");
        field19.setAttribute("maxLength", "15");
        field19.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getHldm())));
        tufwxxRow.appendChild(field19);

        fwjhxxRow.appendChild(tufwxxRow);

        record.appendChild(fwjhxxRow);
    }

    //说明：该方法是针对页面中"批量受理(税务人员)"模块
    private static void addNewFwjhxxData2(Document doc, Element record,
                                          PldrBo2 fwjhxx) {

        Element fwjhxxRow = doc.createElement("row"); //row标记，存放房屋交换信息
        fwjhxxRow.setAttribute("tableName", "QSDB.QS_JL_FWJHXX");
        fwjhxxRow.setAttribute("action", "PRINT");

        //如果房屋交换中录入了个人信息
        if (fwjhxx.getGrxx() != null) {
            Grxx fwjhGrxx = fwjhxx.getGrxx();
            //row标记，存放房屋交换中的被交换人个人信息
            Element grxxRow = doc.createElement("Fwjh");
            grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");

            //field标记--纳税人名称
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(fwjhGrxx.getNsrmc()));
            grxxRow.appendChild(field1);

            //field标记--身份证件类型（代码）
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "SFZJLX");
            field2.setAttribute("maxLength", "2");
            field2.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlx()));
            grxxRow.appendChild(field2);

            //field标记--身份证件类型名称
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "SFZJLXMC");
            field3.setAttribute("maxLength", "30");
            field3.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlxmc()));
            grxxRow.appendChild(field3);

            //field标记--身份证件号码
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "SFZJHM");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(fwjhGrxx.getSfzjhm()));
            grxxRow.appendChild(field4);

            //field标记--联系电话
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "LXDH");
            field5.setAttribute("maxLength", "20");
            field5.appendChild(doc.createTextNode(fwjhGrxx.getLxdh()));
            grxxRow.appendChild(field5);

            //field标记--国家代码
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "GJDM");
            field6.setAttribute("maxLength", "10");
            field6.appendChild(doc.createTextNode(fwjhGrxx.getGjdm()));
            grxxRow.appendChild(field6);

            //field标记--国家名称
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "GJMC");
            field7.setAttribute("maxLength", "60");
            field7.appendChild(doc.createTextNode(fwjhGrxx.getGjmc()));
            grxxRow.appendChild(field7);

            fwjhxxRow.appendChild(grxxRow);

        } else if (fwjhxx.getFgrxx() != null) {
            Fgrxx fwjhFgrxx = fwjhxx.getFgrxx();
            //row标记，存放房屋交换中的被交换非人个人信息
            Element fgrxxRow = doc.createElement("Fwjh");
            fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");

            //field标记--纳税人名称
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(fwjhFgrxx.getNsrmc()));
            fgrxxRow.appendChild(field1);

            //field标记--纳税人计算机代码
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "JSJDM");
            field2.setAttribute("maxLength", "8");
            field2.appendChild(doc.createTextNode(fwjhFgrxx.getJsjdm()));
            fgrxxRow.appendChild(field2);

            //field标记--开户银行代码
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "KHYHDM");
            field3.setAttribute("maxLength", "100");
            field3.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhdm()));
            fgrxxRow.appendChild(field3);

            //field标记--开户银行名称
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "KHYHMC");
            field4.setAttribute("maxLength", "100");
            field4.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhmc()));
            fgrxxRow.appendChild(field4);

            //field标记--银行帐号
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "YHZH");
            field5.setAttribute("maxLength", "100");
            field5.appendChild(doc.createTextNode(fwjhFgrxx.getYhzh()));
            fgrxxRow.appendChild(field5);

            //field标记--联系人姓名
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "LXRXM");
            field6.setAttribute("maxLength", "50");
            field6.appendChild(doc.createTextNode(fwjhFgrxx.getLxrxm()));
            fgrxxRow.appendChild(field6);

            //field标记--联系电话
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "LXDH");
            field7.setAttribute("maxLength", "100");
            field7.appendChild(doc.createTextNode(fwjhFgrxx.getLxdh()));
            fgrxxRow.appendChild(field7);

            //field标记--纳税人类型名称
            Element field8 = doc.createElement("Field");
            field8.setAttribute("fieldName", "NSRLXMC");
            field8.setAttribute("maxLength", "50");
            field8.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxmc()));
            fgrxxRow.appendChild(field8);

            //field标记--纳税人类型代码
            Element field9 = doc.createElement("Field");
            field9.setAttribute("fieldName", "NSRLXDM");
            field9.setAttribute("maxLength", "2");
            field9.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxdm()));
            fgrxxRow.appendChild(field9);

            fwjhxxRow.appendChild(fgrxxRow);
        }

        //房屋交换中的土地房屋信息
        Tufwxx fwjhTufwxx = fwjhxx.getTufwxx();
        Element tufwxxRow = doc.createElement("Fwjh");
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");

        //field标记--房地产项目名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fwjhTufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field标记--合同签订时间
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(
                fwjhTufwxx.getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field标记--土地房屋座落地址
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field标记--土地房屋权属转移类型
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field标记--土地房屋权属转移面积
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field标记--房屋建筑面积
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field标记--成交价格人民币
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field标记--成交价格外币
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field标记--币种代码
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(fwjhTufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field标记--折合价格人民币
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field标记--评估价格人民币
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field标记--土地房屋权属转移类型名称
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field标记--币种名称
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(fwjhTufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field标记--汇率
        Element field19 = doc.createElement("Field");
        field19.setAttribute("fieldName", "HL");
        field19.setAttribute("maxLength", "15");
        field19.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getHldm())));
        tufwxxRow.appendChild(field19);

        fwjhxxRow.appendChild(tufwxxRow);

        record.appendChild(fwjhxxRow);
    }


    /**
     * 创建<record></record>节点中的机关核定子节点
     * @param doc Document         XML文件的document对象
     * @param record Element       <record></record>节点，代表一整套申报
     * @param JghdsjBo hdbo            个人信息
     */
    private static void addNewJghdData(Document doc, Element record,
                                       JghdsjBo hdbo) {
        Element jghdRow = doc.createElement("row"); //row标记，存放个人信息
        jghdRow.setAttribute("tableName", "QSDB.QS_JL_JGHDSJ");
        jghdRow.setAttribute("action", "PRINT");

        //field标记--纳税人名称
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "CJJGRMB");
        field1.setAttribute("maxLength", "60");
        field1.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getCjjgrmb())));
        jghdRow.appendChild(field1);

        //field标记--身份证件类型（代码）
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "CQJMJE");
        field2.setAttribute("maxLength", "60");
        field2.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getCqjmje())));
        jghdRow.appendChild(field2);

        //field标记--身份证件类型名称
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "FWJHCJJG");
        field3.setAttribute("maxLength", "60");
        field3.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getFwjhCjjg())));
        jghdRow.appendChild(field3);

        //field标记--身份证件号码
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "GYZFJMJE");
        field4.setAttribute("maxLength", "60");
        field4.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getGyzfjmje())));
        jghdRow.appendChild(field4);

        //field标记--联系电话
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "JMZJE");
        field5.setAttribute("maxLength", "60");
        field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJmzje())));
        jghdRow.appendChild(field5);

        //field标记--国家代码
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "JSYJ");
        field6.setAttribute("maxLength", "60");
        field6.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJsyj())));
        jghdRow.appendChild(field6);

        //field标记--国家名称
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "JZQS");
        field7.setAttribute("maxLength", "60");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJzqs())));
        jghdRow.appendChild(field7);

        //field标记--国家名称
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "JZSE");
        field8.setAttribute("maxLength", "60");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJzse())));
        jghdRow.appendChild(field8);

        //field标记--国家名称
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "PTZZJMJE");
        field9.setAttribute("maxLength", "60");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getPtzzjmje())));
        jghdRow.appendChild(field9);

        //field标记--国家名称
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "SJYZ");
        field10.setAttribute("maxLength", "60");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getSjyz())));
        jghdRow.appendChild(field10);

        //field标记--国家名称
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "SL");
        field11.setAttribute("maxLength", "60");
        field11.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getSl())));
        jghdRow.appendChild(field11);

        //field标记--国家名称
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "YNSE");
        field12.setAttribute("maxLength", "60");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getYnse())));
        jghdRow.appendChild(field12);

        record.appendChild(jghdRow);
    }


}

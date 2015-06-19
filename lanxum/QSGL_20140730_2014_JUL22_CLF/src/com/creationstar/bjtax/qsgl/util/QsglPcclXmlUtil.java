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
     * ���ǿ�����ʱҪ��ȡxml��������Ϣ
     * @param xml String    XML�ַ���
     * @return ArrayList    ���ص�vo���ϣ�ע��0��Ԫ��Ϊqsdb.qs_jl_drpcinfo��vo
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
            //�õ�xml��document����
            Document doc = XMLTool.getDoc(xml);
            //�õ����ڵ�
            Element root = XMLTool.getRootElement(doc);

            //�õ�������Ϣ�ڵ�
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
     * ��xml�л�ȡҪ������ʱ�������
     * @param xml String    XML�ַ���
     * @return ArrayList    ���ص�vo���ϣ�ע��0��Ԫ��Ϊqsdb.qs_jl_drpcinfo��vo
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
            //�õ�xml��document����
            Document doc = XMLTool.getDoc(xml);
            //�õ����ڵ�
            Element root = XMLTool.getRootElement(doc);

            //�õ�������δ�����ʱ���������ݵĽڵ�
            Element drpcInfo = XMLTool.getOneElement(root, "drpcInfo");
            Element drpcRow = XMLTool.getOneElement(drpcInfo, "row");

            Drpcinfo drpcinfo = new Drpcinfo(); //��ʱ�������vo
            //�õ���ʱ��������ֶε����ݣ�����ֵ��Drpcinfo vo
            Element[] drpcField = XMLTool.getChildrenElements(drpcRow, "Field");
            //��ȡ�����˵���Ϣ
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
                //���뽨ίҵ����
                //////
//                else if (drpcField[i].getAttribute("fieldName").
//                         equalsIgnoreCase("jwywbh"))
//                {
//                    drpcinfo.setJwywbh(XMLTool.getElementText(drpcField[i]));
//                }

            }
            //�ѵ�������Ϣ���������һ���ڵ�
            mxList.add(drpcinfo);

            //�õ����δ������ϸ����
            //ÿ��record�ڵ����һ���걨����
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
                //����ÿ��record�µ�����
                Element[] row = XMLTool.getChildrenElements(record[i], "row");
                int rowLength = row.length;
                for (int j = 0; j < rowLength; j++) {
                    //������Ϣ(��������˰����+֤����+�绰ƴ��)
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
                                                                //��˰������
                                 if (nsrmc.length() >2 ){
                                 nsrmc = nsrmc + "," + XMLTool.getElementText(field[k]);
                                 }else {
                                 nsrmc = nsrmc + XMLTool.getElementText(field[k]);
                                 }
                                 */
                            }
                        }
                    }

                    //�Ǹ�����Ϣ����˰�����ơ����������ֻ��ʾ����Ȩ�˵ģ�
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
                    //���ء�������Ϣ
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
     * ������ʱ��Drsjnr�ֶ��д洢��xml���ݣ���ֵ����Ӧ��vo
     * @param xml String    <record></rocord>֮����ַ���
     * @return Object       ����PldrBo
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public static Object getRecord(String xml) throws
            ParserConfigurationException, SAXException, IOException {

        PldrBo bo = new PldrBo(); ;

        //������Ϣ
        Grxx grxx = null;
        //�Ǹ�����Ϣ
        Fgrxx fgrxx = null;
        //���ط�����Ϣ
        Tufwxx tufwxx = null;
        //���������Ϣ
        Spjgxx spjgxx = null;
        //��Ǩ��Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblcq����)
        ArrayList cqxxList = new ArrayList();
        //����ס����Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblgyzf����)
        ArrayList gyzfxxList = new ArrayList();
        //���ݽ�����Ϣ(Arryalist�е�ÿ��Ԫ�ض���PldrBo����,����û�в�Ǩ�͹���)
        ArrayList fwjhxxList = new ArrayList();
        //������Ϣ
        ArrayList grxxList = new ArrayList();
        //�Ǹ�����Ϣ
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

                //���xml������row�ڵ��tableName�Ǹ�����Ϣ�����������Ϣvo��ֵ
                if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_grxx")) {
                    grxx = new Grxx();
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;

                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "NSRMC")) {
                            //�Ѹ��˵Ķ����Ȩ��ƴ��һ��
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
                            //��֤������ƴ��һ��
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
                            //��ϵ�绰
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
                        //�����Ȩ������
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
                    //��Ӹ�����Ϣ
                    grxxList.add(grxx);

                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_sbzb")) {
                    Element[] field = XMLTool.getChildrenElements(row[i],
                            "Field");
                    int fieldLength = field.length;
                    for (int j = 0; j < fieldLength; j++) {
                        if (field[j].getAttribute("fieldName").equalsIgnoreCase(
                                "SETZ")) {
                            //˰��������ͽ���ת��ȫ��(01)->ȫ��(2)
                            if (XMLTool.getElementText(field[j]).equals("01")) {
                                bo.setSetz("2");
                            }
                            //˰��������ͽ���ת����ͨ��׼סլ(02)->��������(1)
                            if (XMLTool.getElementText(field[j]).equals("02")) {
                                bo.setSetz("1");
                            }
                            //˰��������ͽ���ת���������÷�(03)->�������÷�(6)
                            if (XMLTool.getElementText(field[j]).equals("03")) {
                                bo.setSetz("6");
                            }
                            //˰��������ͽ���ת������90ƽ����(04)->����90ƽ����(5)
                            if (XMLTool.getElementText(field[j]).equals("04")) {
                                bo.setSetz("5");
                            }
                        }
                    }

                    //���xml������row�ڵ��tableName�ǷǸ�����Ϣ������Ǹ�����Ϣvo��ֵ
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
                        //�����Ȩ������
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
                    //���xml������row�ڵ��tableName�����ط�����Ϣ��������ط�����Ϣvo��ֵ
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_tufwxx")) {
                    tufwxx = new Tufwxx();
                    //---add by jiq at 20061212---
                    //---����Ĭ��ֵ���Ƕ��ַ���ȫ������
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
                //���������Ϣ
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
                //���xml������row�ڵ��tableName�ǲ�Ǩ��Ϣ�������Ǩ��Ϣvo��ֵ
                //��ע�⣺��Ǩ���Զ��������Էŵ�ArrayList���棩
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

                    //���xml������row�ڵ��tableName�ǹ���ס����Ϣ���������ס����Ϣvo��ֵ
                    //��ע�⣺����ס�����Զ��������Էŵ�ArrayList���棩
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
                    //���xml������row�ڵ��tableName�Ƿ��ݽ�����Ϣ
                    //��ע�⣺����ס�����Զ��������Էŵ�ArrayList���棩
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
     * ������ʱ��Drsjnr�ֶ��д洢��xml���ݣ���ֵ����Ӧ��vo
     * @param xml String    <record></rocord>֮����ַ���
     * @return Object       ����PldrBo2
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     *
     * ˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
     */
    public static Object getRecord2(String xml) throws
            ParserConfigurationException, SAXException, IOException {

        PldrBo2 bo = null;

        //������Ϣ
        Grxx grxx = null;
        //�Ǹ�����Ϣ
        Fgrxx fgrxx = null;
        //���ط�����Ϣ
        Tufwxx tufwxx = null;
        //���������Ϣ
        Spjgxx spjgxx = null;
        //��Ǩ��Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblcq����)
        ArrayList cqxxList = new ArrayList();
        //����ס����Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblgyzf����)
        ArrayList gyzfxxList = new ArrayList();
        //���ݽ�����Ϣ(Arryalist�е�ÿ��Ԫ�ض���PldrBo����,����û�в�Ǩ�͹���)
        ArrayList fwjhxxList = new ArrayList();
        //������Ϣ
        ArrayList grxxList = new ArrayList();
        //�Ǹ�����Ϣ
        ArrayList fgrxxList = new ArrayList();
        try {

            Element root = XMLTool.getRootElement(xml);
            Element[] row = XMLTool.getElements(root, "row");

            int length = row.length;

            for (int i = 0; i < length; i++) {
                //���xml������row�ڵ��tableName�Ǹ�����Ϣ�����������Ϣvo��ֵ
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

                    //��Ӹ�����Ϣ
                    grxxList.add(grxx);
                    //���xml������row�ڵ��tableName�ǷǸ�����Ϣ������Ǹ�����Ϣvo��ֵ
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
                    //���xml������row�ڵ��tableName�����ط�����Ϣ��������ط�����Ϣvo��ֵ
                } else if (row[i].getAttribute("tableName").equalsIgnoreCase(
                        "qsdb.qs_jl_tufwxx")) {
                    tufwxx = new Tufwxx();
                    //---add by jiq at 20061212---
                    //---����Ĭ��ֵ���Ƕ��ַ���ȫ������
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
                //���������Ϣ
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
                //���xml������row�ڵ��tableName�ǲ�Ǩ��Ϣ�������Ǩ��Ϣvo��ֵ
                //��ע�⣺��Ǩ���Զ��������Էŵ�ArrayList���棩
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

                    //���xml������row�ڵ��tableName�ǹ���ס����Ϣ���������ס����Ϣvo��ֵ
                    //��ע�⣺����ס�����Զ��������Էŵ�ArrayList���棩
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
                    //���xml������row�ڵ��tableName�Ƿ��ݽ�����Ϣ
                    //��ע�⣺����ס�����Զ��������Էŵ�ArrayList���棩
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
     * ����ʽ���е����ݴ洢Ϊxml������������ӡʱ����excel
     * @param sbxxList ArrayList    �洢����PldrBo
     * @return String        ���ɵ�xml�ַ���
     * @throws ParserConfigurationException
     */
    public static String createXML(ArrayList sbxxList) throws
            ParserConfigurationException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        StringBuffer xml = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"gb2312\"?>");

        //ʵ����һ��Document
        DocumentBuilderFactory fac = ((DocumentBuilderFactoryImpl) Class.
                                      forName(
                                              "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl").
                                      newInstance());

        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.newDocument();

        //ÿһ��Element�Ƕ�һ�����
        Element root = doc.createElement("data"); //������data���

        for (Iterator iter = sbxxList.iterator(); iter.hasNext(); ) {
            PldrBo bo = (PldrBo) iter.next();
            Element record = doc.createElement("record"); //record���

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
                //���ݽ���ֻ��һ��Ŀǰ
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
     * ����ʽ���е����ݴ洢Ϊxml������������ӡʱ����excel
     * @param sbxxList ArrayList    �洢����PldrBo2
     * @return String        ���ɵ�xml�ַ���
     * @throws ParserConfigurationException
     *
     * ˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
     */
    public static String createXML2(ArrayList sbxxList) throws
            ParserConfigurationException, ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        StringBuffer xml = new StringBuffer(
                "<?xml version=\"1.0\" encoding=\"gb2312\"?>");

        //ʵ����һ��Document
        DocumentBuilderFactory fac = ((DocumentBuilderFactoryImpl) Class.
                                      forName(
                                              "org.apache.crimson.jaxp.DocumentBuilderFactoryImpl").
                                      newInstance());

        DocumentBuilder builder = fac.newDocumentBuilder();
        Document doc = builder.newDocument();

        //ÿһ��Element�Ƕ�һ�����
        Element root = doc.createElement("data"); //������data���

        for (Iterator iter = sbxxList.iterator(); iter.hasNext(); ) {
            PldrBo2 bo = (PldrBo2) iter.next();
            Element record = doc.createElement("record"); //record���

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
                //���ݽ���ֻ��һ��Ŀǰ
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
     * ����<record></record>�ڵ��еĸ�����Ϣ�ӽڵ�
     * @param doc Document         XML�ļ���document����
     * @param record Element       <record></record>�ڵ㣬����һ�����걨
     * @param sbzb Sbzb    �걨����
     */
    private static void addNewSbzbData(Document doc, Element record, Sbzb sbzb) {
        Element sbzbRow = doc.createElement("row"); //row��ǣ�����걨������Ϣ
        sbzbRow.setAttribute("tableName", "QSDB.QS_JL_SBZB");
        sbzbRow.setAttribute("action", "PRINT");

        //field���--��ע
        Element field0 = doc.createElement("Field");
        field0.setAttribute("fieldName", "SBBH");
        field0.setAttribute("maxLength", "20");
        field0.appendChild(doc.createTextNode(sbzb.getSbbh()));
        sbzbRow.appendChild(field0);

        //field���--�걨����
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "SBRQ");
        field1.setAttribute("maxLength", "8");
        field1.appendChild(doc.createTextNode(DataConvert.Date2String(sbzb.
                getSbrq())));
        sbzbRow.appendChild(field1);

        //field���--��˰��ʽ����
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "JSFSMC");
        field2.setAttribute("maxLength", "20");
        field2.appendChild(doc.createTextNode(sbzb.getJsfsmc()));
        sbzbRow.appendChild(field2);

        //field���--��ע
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "BZ");
        field3.setAttribute("maxLength", "100");
        field3.appendChild(doc.createTextNode(sbzb.getBz()));
        sbzbRow.appendChild(field3);

        record.appendChild(sbzbRow);
    }

    /**
     * ����<record></record>�ڵ��еĸ�����Ϣ�ӽڵ�
     * @param doc Document         XML�ļ���document����
     * @param record Element       <record></record>�ڵ㣬����һ�����걨
     * @param grxx Grxx            ������Ϣ
     */
    private static void addNewGrxxData(Document doc, Element record, Grxx grxx) {
        Element grxxRow = doc.createElement("row"); //row��ǣ���Ÿ�����Ϣ
        grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");
        grxxRow.setAttribute("action", "PRINT");

        //field���--��˰������
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "NSRMC");
        field1.setAttribute("maxLength", "50");
        field1.appendChild(doc.createTextNode(grxx.getNsrmc()));
        grxxRow.appendChild(field1);

        //field���--���֤�����ͣ����룩
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "SFZJLX");
        field2.setAttribute("maxLength", "2");
        field2.appendChild(doc.createTextNode(grxx.getSfzjlx()));
        grxxRow.appendChild(field2);

        //field���--���֤����������
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "SFZJLXMC");
        field3.setAttribute("maxLength", "30");
        field3.appendChild(doc.createTextNode(grxx.getSfzjlxmc()));
        grxxRow.appendChild(field3);

        //field���--���֤������
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "SFZJHM");
        field4.setAttribute("maxLength", "30");
        field4.appendChild(doc.createTextNode(grxx.getSfzjhm()));
        grxxRow.appendChild(field4);

        //field���--��ϵ�绰
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "LXDH");
        field5.setAttribute("maxLength", "20");
        field5.appendChild(doc.createTextNode(grxx.getLxdh()));
        grxxRow.appendChild(field5);

        //field���--���Ҵ���
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "GJDM");
        field6.setAttribute("maxLength", "10");
        field6.appendChild(doc.createTextNode(grxx.getGjdm()));
        grxxRow.appendChild(field6);

        //field���--��������
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "GJMC");
        field7.setAttribute("maxLength", "60");
        field7.appendChild(doc.createTextNode(grxx.getGjmc()));
        grxxRow.appendChild(field7);

        record.appendChild(grxxRow);
    }

    /**
     * ����<record></record>�ڵ��еķǸ�����Ϣ�ӽڵ�
     * @param doc Document       XML�ļ���document����
     * @param record Element     <record></record>�ڵ㣬����һ�����걨
     * @param fgrxx Fgrxx        �Ǹ�����Ϣ
     */
    public static void addNewFgrxxData(Document doc, Element record,
                                       Fgrxx fgrxx) {

        Element fgrxxRow = doc.createElement("row"); //row��ǣ���Ÿ�����Ϣ
        fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");
        fgrxxRow.setAttribute("action", "PRINT");

        //field���--��˰������
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "NSRMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fgrxx.getNsrmc()));
        fgrxxRow.appendChild(field1);

        //field���--��˰�˼��������
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "JSJDM");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(fgrxx.getJsjdm()));
        fgrxxRow.appendChild(field2);

        //field���--�������д���
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "KHYHDM");
        field3.setAttribute("maxLength", "100");
        field3.appendChild(doc.createTextNode(fgrxx.getKhyhdm()));
        fgrxxRow.appendChild(field3);

        //field���--������������
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "KHYHMC");
        field4.setAttribute("maxLength", "100");
        field4.appendChild(doc.createTextNode(fgrxx.getKhyhmc()));
        fgrxxRow.appendChild(field4);

        //field���--�����ʺ�
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "YHZH");
        field5.setAttribute("maxLength", "100");
        field5.appendChild(doc.createTextNode(fgrxx.getYhzh()));
        fgrxxRow.appendChild(field5);

        //field���--��ϵ������
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "LXRXM");
        field6.setAttribute("maxLength", "50");
        field6.appendChild(doc.createTextNode(fgrxx.getLxrxm()));
        fgrxxRow.appendChild(field6);

        //field���--��ϵ�绰
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "LXDH");
        field7.setAttribute("maxLength", "100");
        field7.appendChild(doc.createTextNode(fgrxx.getLxdh()));
        fgrxxRow.appendChild(field7);

        //field���--��˰����������
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "NSRLXMC");
        field8.setAttribute("maxLength", "50");
        field8.appendChild(doc.createTextNode(fgrxx.getNsrlxmc()));
        fgrxxRow.appendChild(field8);

        //field���--��˰�����ʹ���
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "NSRLXDM");
        field9.setAttribute("maxLength", "2");
        field9.appendChild(doc.createTextNode(fgrxx.getNsrlxdm()));
        fgrxxRow.appendChild(field9);

        record.appendChild(fgrxxRow);
    }


    /**
     * ����<record></record>�ڵ��е���������ӽڵ�
     * @param doc Document         XML�ļ���document����
     * @param record Element       <record></record>�ڵ㣬����һ�����걨
     * @param spjgxx Spjgxx    ���������Ϣ
     */
    private static void addNewSpjgxxData(Document doc, Element record,
                                         Spjgxx spjgxx) {
        Element spjgRow = doc.createElement("row"); //row��ǣ�������������Ϣ
        spjgRow.setAttribute("tableName", "QSDB.QS_JL_SPJGXX");
        spjgRow.setAttribute("action", "PRINT");

        //field���--����˰���
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "JMSJE");
        field1.setAttribute("maxLength", "15");
        field1.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                spjgxx.getJmsje())));
        spjgRow.appendChild(field1);

        //field���--�˶�֪ͨ��ţ����룩
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HDTZSZH");
        field2.setAttribute("maxLength", "50");
        field2.appendChild(doc.createTextNode(spjgxx.getHdtzszh()));
        spjgRow.appendChild(field2);

        record.appendChild(spjgRow);
    }

    /**
     * ����<record></record>�ڵ��еķ���������Ϣ�ӽڵ�
     * @param doc Document       XML�ļ���document����
     * @param record Element     �½���<record></record>�ڵ�
     * @param tufwxx Tufwxx      ����������Ϣ
     */
    private static void addNewTufwxxData(Document doc, Element record,
                                         Tufwxx tufwxx) {

        Element tufwxxRow = doc.createElement("row"); //row��ǣ�������ط�����Ϣ
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");
        tufwxxRow.setAttribute("action", "PRINT");

        //field���--���ز���Ŀ����
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(tufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field���--��ͬǩ��ʱ��
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(tufwxx.
                getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field���--�������
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "FLDM");
        field3.setAttribute("maxLength", "2");
        field3.appendChild(doc.createTextNode(tufwxx.getFldm()));
        tufwxxRow.appendChild(field3);

        //field���--���ط��������ַ
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(tufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field���--���ط���Ȩ��ת������
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(tufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field���--�������ʹ���
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "FWLXDM");
        field6.setAttribute("maxLength", "2");
        field6.appendChild(doc.createTextNode(tufwxx.getFwlxdm()));
        tufwxxRow.appendChild(field6);

        //field���--���ط���Ȩ��ת�����
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field���--���ݽ������
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field���--�ɽ��۸������
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field���--�ɽ��۸����
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field���--���ִ���
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(tufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field���--�ۺϼ۸������
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field���--�����۸������
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field���--��ע
        /*Element field14 = doc.createElement("Field");
                 field14.setAttribute("fieldName", "BZ");
                 field14.setAttribute("maxLength", "100");
                 field14.appendChild(doc.createTextNode(tufwxx.getBz()));
                 tufwxxRow.appendChild(field14);*/

        //field���--���ط���Ȩ��ת����������
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(tufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field���--��������
        Element field16 = doc.createElement("Field");
        field16.setAttribute("fieldName", "FLMC");
        field16.setAttribute("maxLength", "100");
        field16.appendChild(doc.createTextNode(tufwxx.getFlmc()));
        tufwxxRow.appendChild(field16);

        //field���--������������
        Element field17 = doc.createElement("Field");
        field17.setAttribute("fieldName", "FWLXMC");
        field17.setAttribute("maxLength", "100");
        field17.appendChild(doc.createTextNode(tufwxx.getFwlxmc()));
        tufwxxRow.appendChild(field17);

        //field���--��������
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(tufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field���--����
        Element field19 = doc.createElement("Field");
        field19.setAttribute("fieldName", "HL");
        field19.setAttribute("maxLength", "15");
        field19.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                tufwxx.getHldm())));
        tufwxxRow.appendChild(field19);

        //field���--�ݻ���
        Element field20 = doc.createElement("Field");
        field20.setAttribute("fieldName", "RJLDM");
        field20.setAttribute("maxLength", "2");
        field20.appendChild(doc.createTextNode(tufwxx.getRjl()));
        tufwxxRow.appendChild(field20);

        //field���--���ؼ���
        Element field21 = doc.createElement("Field");
        field21.setAttribute("fieldName", "FWJCDM");
        field21.setAttribute("maxLength", "2");
        field21.appendChild(doc.createTextNode(tufwxx.getTdjc()));
        tufwxxRow.appendChild(field21);
        //field���--���ط��������
        /*Element field20 = doc.createElement("Field");
                 field20.setAttribute("fieldName", "FWTDBMDM");
                 field20.setAttribute("maxLength", "100");
                 field20.appendChild(doc.createTextNode(tufwxx.getFwtdbmdm()));
                 tufwxxRow.appendChild(field20);*/

        record.appendChild(tufwxxRow);
    }

    /**
     * ����<record></record>�ڵ��еķ��ݲ�Ǩ��Ϣ�ӽڵ�(���Զ���)
     * @param doc Document       XML�ļ���document����
     * @param record Element     <record></record>�ڵ㣬����һ�����걨
     * @param cqxx Jsblcq[]        ��Ǩ��Ϣ����
     */
    private static void addNewCqxxData(Document doc, Element record,
                                       Jsblcq[] cqxx) {

        int size = cqxx.length;

        for (int i = 0; i < size; i++) {

            Element cqxxRow = doc.createElement("row"); //row��ǣ���Ų�Ǩ��Ϣ
            cqxxRow.setAttribute("tableName", "QSDB.QS_JL_JSBLCQ");
            cqxxRow.setAttribute("action", "PRINT");

            //field���--����Ǩ���������ַ
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "ZLDZ");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(cqxx[i].getZldz()));
            cqxxRow.appendChild(field1);

            //field���--��Ǩ������
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "CQBCE");
            field2.setAttribute("maxLength", "15");
            field2.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getCqbce())));
            cqxxRow.appendChild(field2);

            //field���--��Ǩ����ʹ�ö�
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "BCSYBCE");
            field3.setAttribute("maxLength", "15");
            field3.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getBcsybce())));
            cqxxRow.appendChild(field3);

            //field���--��Ǩ����ʣ���
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "CQBCSYE");
            field5.setAttribute("maxLength", "15");
            field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    cqxx[i].
                    getCqbcsye())));
            cqxxRow.appendChild(field5);

            //field���--��ǨЭ���
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "CQXYH");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(cqxx[i].getCqxyh()));
            cqxxRow.appendChild(field4);

            record.appendChild(cqxxRow);
        }
    }

    /**
     * ����<record></record>�ڵ��еĹ���ס����Ϣ�ӽڵ�(���Զ���)
     * @param doc Document       XML�ļ���document����
     * @param record Element     <record></record>�ڵ㣬����һ�����걨
     * @param gyzfxx Gyzfxx[]    ����ס������
     */
    private static void addNewGyzfxxData(Document doc, Element record,
                                         Jsblgyzf[] gyzfxx) {

        int size = gyzfxx.length;

        for (int i = 0; i < size; i++) {
            Element gyzfxxRow = doc.createElement("row"); //row��ǣ���Ź���ס����Ϣ
            gyzfxxRow.setAttribute("tableName", "QSDB.QS_JL_JSBLGYZF");
            gyzfxxRow.setAttribute("action", "PRINT");

            //field���--�ѹ�����ס��Ȩ��֤���
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "YGGYZFQSZSH");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(gyzfxx[i].getYggyzfqszsh()));
            gyzfxxRow.appendChild(field1);

            //field���--�����ַ
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "ZLDZ");
            field2.setAttribute("maxLength", "100");
            field2.appendChild(doc.createTextNode(gyzfxx[i].getZldz()));
            gyzfxxRow.appendChild(field2);

            //field���--��ͬǩ��ʱ��
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "QDSJ");
            field3.setAttribute("maxLength", "8");
            field3.appendChild(doc.createTextNode(DataConvert.Date2String(
                    gyzfxx[i].getQdsj())));
            gyzfxxRow.appendChild(field3);

            //field���--�������
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "JZMJ");
            field4.setAttribute("maxLength", "14");
            field4.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getJzmj())));
            gyzfxxRow.appendChild(field4);

            //field���--�ɽ��۸�
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "CJJG");
            field5.setAttribute("maxLength", "15");
            field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getCjjg())));
            gyzfxxRow.appendChild(field5);

            //field���--���εֿ۶�
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "BCDKE");
            field6.setAttribute("maxLength", "15");
            field6.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                    gyzfxx[i].getBcdke())));
            gyzfxxRow.appendChild(field6);

            //field���--ʣ���
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

        Element fwjhxxRow = doc.createElement("row"); //row��ǣ���ŷ��ݽ�����Ϣ
        fwjhxxRow.setAttribute("tableName", "QSDB.QS_JL_FWJHXX");
        fwjhxxRow.setAttribute("action", "PRINT");

        //������ݽ�����¼���˸�����Ϣ
        if (fwjhxx.getGrxx() != null) {
            Grxx fwjhGrxx = fwjhxx.getGrxx();
            //row��ǣ���ŷ��ݽ����еı������˸�����Ϣ
            Element grxxRow = doc.createElement("Fwjh");
            grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");

            //field���--��˰������
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(fwjhGrxx.getNsrmc()));
            grxxRow.appendChild(field1);

            //field���--���֤�����ͣ����룩
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "SFZJLX");
            field2.setAttribute("maxLength", "2");
            field2.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlx()));
            grxxRow.appendChild(field2);

            //field���--���֤����������
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "SFZJLXMC");
            field3.setAttribute("maxLength", "30");
            field3.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlxmc()));
            grxxRow.appendChild(field3);

            //field���--���֤������
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "SFZJHM");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(fwjhGrxx.getSfzjhm()));
            grxxRow.appendChild(field4);

            //field���--��ϵ�绰
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "LXDH");
            field5.setAttribute("maxLength", "20");
            field5.appendChild(doc.createTextNode(fwjhGrxx.getLxdh()));
            grxxRow.appendChild(field5);

            //field���--���Ҵ���
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "GJDM");
            field6.setAttribute("maxLength", "10");
            field6.appendChild(doc.createTextNode(fwjhGrxx.getGjdm()));
            grxxRow.appendChild(field6);

            //field���--��������
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "GJMC");
            field7.setAttribute("maxLength", "60");
            field7.appendChild(doc.createTextNode(fwjhGrxx.getGjmc()));
            grxxRow.appendChild(field7);

            fwjhxxRow.appendChild(grxxRow);

        } else if (fwjhxx.getFgrxx() != null) {
            Fgrxx fwjhFgrxx = fwjhxx.getFgrxx();
            //row��ǣ���ŷ��ݽ����еı��������˸�����Ϣ
            Element fgrxxRow = doc.createElement("Fwjh");
            fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");

            //field���--��˰������
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(fwjhFgrxx.getNsrmc()));
            fgrxxRow.appendChild(field1);

            //field���--��˰�˼��������
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "JSJDM");
            field2.setAttribute("maxLength", "8");
            field2.appendChild(doc.createTextNode(fwjhFgrxx.getJsjdm()));
            fgrxxRow.appendChild(field2);

            //field���--�������д���
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "KHYHDM");
            field3.setAttribute("maxLength", "100");
            field3.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhdm()));
            fgrxxRow.appendChild(field3);

            //field���--������������
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "KHYHMC");
            field4.setAttribute("maxLength", "100");
            field4.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhmc()));
            fgrxxRow.appendChild(field4);

            //field���--�����ʺ�
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "YHZH");
            field5.setAttribute("maxLength", "100");
            field5.appendChild(doc.createTextNode(fwjhFgrxx.getYhzh()));
            fgrxxRow.appendChild(field5);

            //field���--��ϵ������
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "LXRXM");
            field6.setAttribute("maxLength", "50");
            field6.appendChild(doc.createTextNode(fwjhFgrxx.getLxrxm()));
            fgrxxRow.appendChild(field6);

            //field���--��ϵ�绰
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "LXDH");
            field7.setAttribute("maxLength", "100");
            field7.appendChild(doc.createTextNode(fwjhFgrxx.getLxdh()));
            fgrxxRow.appendChild(field7);

            //field���--��˰����������
            Element field8 = doc.createElement("Field");
            field8.setAttribute("fieldName", "NSRLXMC");
            field8.setAttribute("maxLength", "50");
            field8.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxmc()));
            fgrxxRow.appendChild(field8);

            //field���--��˰�����ʹ���
            Element field9 = doc.createElement("Field");
            field9.setAttribute("fieldName", "NSRLXDM");
            field9.setAttribute("maxLength", "2");
            field9.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxdm()));
            fgrxxRow.appendChild(field9);

            fwjhxxRow.appendChild(fgrxxRow);
        }

        //���ݽ����е����ط�����Ϣ
        Tufwxx fwjhTufwxx = fwjhxx.getTufwxx();
        Element tufwxxRow = doc.createElement("Fwjh");
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");

        //field���--���ز���Ŀ����
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fwjhTufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field���--��ͬǩ��ʱ��
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(
                fwjhTufwxx.getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field���--���ط��������ַ
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field���--���ط���Ȩ��ת������
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field���--���ط���Ȩ��ת�����
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field���--���ݽ������
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field���--�ɽ��۸������
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field���--�ɽ��۸����
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field���--���ִ���
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(fwjhTufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field���--�ۺϼ۸������
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field���--�����۸������
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field���--���ط���Ȩ��ת����������
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field���--��������
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(fwjhTufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field���--����
        Element field19 = doc.createElement("Field");
        field19.setAttribute("fieldName", "HL");
        field19.setAttribute("maxLength", "15");
        field19.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getHldm())));
        tufwxxRow.appendChild(field19);

        fwjhxxRow.appendChild(tufwxxRow);

        record.appendChild(fwjhxxRow);
    }

    //˵�����÷��������ҳ����"��������(˰����Ա)"ģ��
    private static void addNewFwjhxxData2(Document doc, Element record,
                                          PldrBo2 fwjhxx) {

        Element fwjhxxRow = doc.createElement("row"); //row��ǣ���ŷ��ݽ�����Ϣ
        fwjhxxRow.setAttribute("tableName", "QSDB.QS_JL_FWJHXX");
        fwjhxxRow.setAttribute("action", "PRINT");

        //������ݽ�����¼���˸�����Ϣ
        if (fwjhxx.getGrxx() != null) {
            Grxx fwjhGrxx = fwjhxx.getGrxx();
            //row��ǣ���ŷ��ݽ����еı������˸�����Ϣ
            Element grxxRow = doc.createElement("Fwjh");
            grxxRow.setAttribute("tableName", "QSDB.QS_JL_GRXX");

            //field���--��˰������
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "50");
            field1.appendChild(doc.createTextNode(fwjhGrxx.getNsrmc()));
            grxxRow.appendChild(field1);

            //field���--���֤�����ͣ����룩
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "SFZJLX");
            field2.setAttribute("maxLength", "2");
            field2.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlx()));
            grxxRow.appendChild(field2);

            //field���--���֤����������
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "SFZJLXMC");
            field3.setAttribute("maxLength", "30");
            field3.appendChild(doc.createTextNode(fwjhGrxx.getSfzjlxmc()));
            grxxRow.appendChild(field3);

            //field���--���֤������
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "SFZJHM");
            field4.setAttribute("maxLength", "30");
            field4.appendChild(doc.createTextNode(fwjhGrxx.getSfzjhm()));
            grxxRow.appendChild(field4);

            //field���--��ϵ�绰
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "LXDH");
            field5.setAttribute("maxLength", "20");
            field5.appendChild(doc.createTextNode(fwjhGrxx.getLxdh()));
            grxxRow.appendChild(field5);

            //field���--���Ҵ���
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "GJDM");
            field6.setAttribute("maxLength", "10");
            field6.appendChild(doc.createTextNode(fwjhGrxx.getGjdm()));
            grxxRow.appendChild(field6);

            //field���--��������
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "GJMC");
            field7.setAttribute("maxLength", "60");
            field7.appendChild(doc.createTextNode(fwjhGrxx.getGjmc()));
            grxxRow.appendChild(field7);

            fwjhxxRow.appendChild(grxxRow);

        } else if (fwjhxx.getFgrxx() != null) {
            Fgrxx fwjhFgrxx = fwjhxx.getFgrxx();
            //row��ǣ���ŷ��ݽ����еı��������˸�����Ϣ
            Element fgrxxRow = doc.createElement("Fwjh");
            fgrxxRow.setAttribute("tableName", "QSDB.QS_JL_FGRXX");

            //field���--��˰������
            Element field1 = doc.createElement("Field");
            field1.setAttribute("fieldName", "NSRMC");
            field1.setAttribute("maxLength", "100");
            field1.appendChild(doc.createTextNode(fwjhFgrxx.getNsrmc()));
            fgrxxRow.appendChild(field1);

            //field���--��˰�˼��������
            Element field2 = doc.createElement("Field");
            field2.setAttribute("fieldName", "JSJDM");
            field2.setAttribute("maxLength", "8");
            field2.appendChild(doc.createTextNode(fwjhFgrxx.getJsjdm()));
            fgrxxRow.appendChild(field2);

            //field���--�������д���
            Element field3 = doc.createElement("Field");
            field3.setAttribute("fieldName", "KHYHDM");
            field3.setAttribute("maxLength", "100");
            field3.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhdm()));
            fgrxxRow.appendChild(field3);

            //field���--������������
            Element field4 = doc.createElement("Field");
            field4.setAttribute("fieldName", "KHYHMC");
            field4.setAttribute("maxLength", "100");
            field4.appendChild(doc.createTextNode(fwjhFgrxx.getKhyhmc()));
            fgrxxRow.appendChild(field4);

            //field���--�����ʺ�
            Element field5 = doc.createElement("Field");
            field5.setAttribute("fieldName", "YHZH");
            field5.setAttribute("maxLength", "100");
            field5.appendChild(doc.createTextNode(fwjhFgrxx.getYhzh()));
            fgrxxRow.appendChild(field5);

            //field���--��ϵ������
            Element field6 = doc.createElement("Field");
            field6.setAttribute("fieldName", "LXRXM");
            field6.setAttribute("maxLength", "50");
            field6.appendChild(doc.createTextNode(fwjhFgrxx.getLxrxm()));
            fgrxxRow.appendChild(field6);

            //field���--��ϵ�绰
            Element field7 = doc.createElement("Field");
            field7.setAttribute("fieldName", "LXDH");
            field7.setAttribute("maxLength", "100");
            field7.appendChild(doc.createTextNode(fwjhFgrxx.getLxdh()));
            fgrxxRow.appendChild(field7);

            //field���--��˰����������
            Element field8 = doc.createElement("Field");
            field8.setAttribute("fieldName", "NSRLXMC");
            field8.setAttribute("maxLength", "50");
            field8.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxmc()));
            fgrxxRow.appendChild(field8);

            //field���--��˰�����ʹ���
            Element field9 = doc.createElement("Field");
            field9.setAttribute("fieldName", "NSRLXDM");
            field9.setAttribute("maxLength", "2");
            field9.appendChild(doc.createTextNode(fwjhFgrxx.getNsrlxdm()));
            fgrxxRow.appendChild(field9);

            fwjhxxRow.appendChild(fgrxxRow);
        }

        //���ݽ����е����ط�����Ϣ
        Tufwxx fwjhTufwxx = fwjhxx.getTufwxx();
        Element tufwxxRow = doc.createElement("Fwjh");
        tufwxxRow.setAttribute("tableName", "QSDB.QS_JL_TUFWXX");

        //field���--���ز���Ŀ����
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "FDCXMMC");
        field1.setAttribute("maxLength", "100");
        field1.appendChild(doc.createTextNode(fwjhTufwxx.getFdcxmmc()));
        tufwxxRow.appendChild(field1);

        //field���--��ͬǩ��ʱ��
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "HTQDSJ");
        field2.setAttribute("maxLength", "8");
        field2.appendChild(doc.createTextNode(DataConvert.Date2String(
                fwjhTufwxx.getHtqdsj())));
        tufwxxRow.appendChild(field2);

        //field���--���ط��������ַ
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "TDFWZLDZ");
        field4.setAttribute("maxLength", "200");
        field4.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwzldz()));
        tufwxxRow.appendChild(field4);

        //field���--���ط���Ȩ��ת������
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "TDFWQSZYLX");
        field5.setAttribute("maxLength", "2");
        field5.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszylx()));
        tufwxxRow.appendChild(field5);

        //field���--���ط���Ȩ��ת�����
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "TDFWQSZYMJ");
        field7.setAttribute("maxLength", "14");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getTdfwqszymj())));
        tufwxxRow.appendChild(field7);

        //field���--���ݽ������
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "FWJZMJ");
        field8.setAttribute("maxLength", "14");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getFwjzmj())));
        tufwxxRow.appendChild(field8);

        //field���--�ɽ��۸������
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "CJJGRMB");
        field9.setAttribute("maxLength", "15");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgrmb())));
        tufwxxRow.appendChild(field9);

        //field���--�ɽ��۸����
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "CJJGWB");
        field10.setAttribute("maxLength", "15");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getCjjgwb())));
        tufwxxRow.appendChild(field10);

        //field���--���ִ���
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "BZDM");
        field11.setAttribute("maxLength", "3");
        field11.appendChild(doc.createTextNode(fwjhTufwxx.getBzdm()));
        tufwxxRow.appendChild(field11);

        //field���--�ۺϼ۸������
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "ZHJGRMB");
        field12.setAttribute("maxLength", "15");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getZhjgrmb())));
        tufwxxRow.appendChild(field12);

        //field���--�����۸������
        Element field113 = doc.createElement("Field");
        field113.setAttribute("fieldName", "PGJGRMB");
        field113.setAttribute("maxLength", "15");
        field113.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                fwjhTufwxx.getPgjgrmb())));
        tufwxxRow.appendChild(field113);

        //field���--���ط���Ȩ��ת����������
        Element field15 = doc.createElement("Field");
        field15.setAttribute("fieldName", "TDFWQSZYLXMC");
        field15.setAttribute("maxLength", "100");
        field15.appendChild(doc.createTextNode(fwjhTufwxx.getTdfwqszymc()));
        tufwxxRow.appendChild(field15);

        //field���--��������
        Element field118 = doc.createElement("Field");
        field118.setAttribute("fieldName", "BZMC");
        field118.setAttribute("maxLength", "60");
        field118.appendChild(doc.createTextNode(fwjhTufwxx.getBzmc()));
        tufwxxRow.appendChild(field118);

        //field���--����
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
     * ����<record></record>�ڵ��еĻ��غ˶��ӽڵ�
     * @param doc Document         XML�ļ���document����
     * @param record Element       <record></record>�ڵ㣬����һ�����걨
     * @param JghdsjBo hdbo            ������Ϣ
     */
    private static void addNewJghdData(Document doc, Element record,
                                       JghdsjBo hdbo) {
        Element jghdRow = doc.createElement("row"); //row��ǣ���Ÿ�����Ϣ
        jghdRow.setAttribute("tableName", "QSDB.QS_JL_JGHDSJ");
        jghdRow.setAttribute("action", "PRINT");

        //field���--��˰������
        Element field1 = doc.createElement("Field");
        field1.setAttribute("fieldName", "CJJGRMB");
        field1.setAttribute("maxLength", "60");
        field1.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getCjjgrmb())));
        jghdRow.appendChild(field1);

        //field���--���֤�����ͣ����룩
        Element field2 = doc.createElement("Field");
        field2.setAttribute("fieldName", "CQJMJE");
        field2.setAttribute("maxLength", "60");
        field2.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getCqjmje())));
        jghdRow.appendChild(field2);

        //field���--���֤����������
        Element field3 = doc.createElement("Field");
        field3.setAttribute("fieldName", "FWJHCJJG");
        field3.setAttribute("maxLength", "60");
        field3.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getFwjhCjjg())));
        jghdRow.appendChild(field3);

        //field���--���֤������
        Element field4 = doc.createElement("Field");
        field4.setAttribute("fieldName", "GYZFJMJE");
        field4.setAttribute("maxLength", "60");
        field4.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getGyzfjmje())));
        jghdRow.appendChild(field4);

        //field���--��ϵ�绰
        Element field5 = doc.createElement("Field");
        field5.setAttribute("fieldName", "JMZJE");
        field5.setAttribute("maxLength", "60");
        field5.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJmzje())));
        jghdRow.appendChild(field5);

        //field���--���Ҵ���
        Element field6 = doc.createElement("Field");
        field6.setAttribute("fieldName", "JSYJ");
        field6.setAttribute("maxLength", "60");
        field6.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJsyj())));
        jghdRow.appendChild(field6);

        //field���--��������
        Element field7 = doc.createElement("Field");
        field7.setAttribute("fieldName", "JZQS");
        field7.setAttribute("maxLength", "60");
        field7.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJzqs())));
        jghdRow.appendChild(field7);

        //field���--��������
        Element field8 = doc.createElement("Field");
        field8.setAttribute("fieldName", "JZSE");
        field8.setAttribute("maxLength", "60");
        field8.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getJzse())));
        jghdRow.appendChild(field8);

        //field���--��������
        Element field9 = doc.createElement("Field");
        field9.setAttribute("fieldName", "PTZZJMJE");
        field9.setAttribute("maxLength", "60");
        field9.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getPtzzjmje())));
        jghdRow.appendChild(field9);

        //field���--��������
        Element field10 = doc.createElement("Field");
        field10.setAttribute("fieldName", "SJYZ");
        field10.setAttribute("maxLength", "60");
        field10.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getSjyz())));
        jghdRow.appendChild(field10);

        //field���--��������
        Element field11 = doc.createElement("Field");
        field11.setAttribute("fieldName", "SL");
        field11.setAttribute("maxLength", "60");
        field11.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getSl())));
        jghdRow.appendChild(field11);

        //field���--��������
        Element field12 = doc.createElement("Field");
        field12.setAttribute("fieldName", "YNSE");
        field12.setAttribute("maxLength", "60");
        field12.appendChild(doc.createTextNode(DataConvert.BigDecimal2String(
                hdbo.getYnse())));
        jghdRow.appendChild(field12);

        record.appendChild(jghdRow);
    }


}

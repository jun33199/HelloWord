package com.ttsoft.bjtax.smsb.jkscx.web;

import java.util.*;

import javax.servlet.http.*;

import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.framework.form.*;
import org.apache.struts.action.*;
/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class JkscxForm
    extends BaseForm
{
    /**
     * ��ѯ������������
     */
    private String jsjdm;

    /**
     * ��ѯ������������
     */
    private String yhjb;

    /**
     * ��ѯ�걨������
     */
    private String sbrqq;

    /**
     * ��ѯ�걨����ֹ
     */
    private String sbrqz;

    /**
     * ˰�����ʹ���
     */
    private String sklxdm;

    /**
     * �ɿ�ƾ֤��
     */
    private String jkpzh;

    /**
     * ��ѯ������������
     */
    private String sbfsdm;

    /**
     * ˰Ʊ��
     */
    private String sphm;

    /**
     * ��ѯ��ϸ˰Ʊ����
     */
    private String mxsphm;
    /**
     * ��ѯ������������
     */
    private String yhdm;

    /**
     * ��ѯ������������
     */
    private String djzclxdm;

    /**
     * ����˰����ش���
     */
    private String swjgdm;

    /**
     * ����˰��������
     */
    private String swsdm;

    /**
     * ����˰����ش���
     */
    private String zsswjgdm;

    /**
     * ����˰��������
     */

    /**
     * ���ұ�׼��ҵ����
     */
    private String gjbzhydm;

    /**
     * Ԥ���Ŀ����
     */
    private String yskmdm;

    /**
     * Ԥ�㼶�δ���
     */
    private String ysjcdm;

    /**
     * ˰�ִ���
     */
    private String szdm;

    /**
     * ˰��˰Ŀ����
     */
    private String szsmdm;

    /**
     * ʵ�ɽ��
     */
    private String sjje;
    private String op_sjje = "=";

    /**
     * ��˰����
     */
    private String kssl;
    private String op_kssl = "=";

    /**
     * ��˰���
     */
    private String jsje;
    private String op_jsje = "=";

    /**
     * ����ʵ�ɽ��
     */
    private String zbsjje;
    private String op_zbsjje = "=";


    /**
     * ҳ����ʾ�ߴ�
     */
    private String pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);

    /**
     * ҳ��
     */
    private String nextPage = "1";

    /**
     * ҳ��
     */
    private String totalpage = "0";

    /**
     * ҳ����ʾ��Ϣ
     */
    private String message;

    /**
     * ��ǰҳ��
     */
    private String curPage;

    Map zgswjg;
    Map zgsws;
    SfHashList szList;
    SfHashList smList ;


    /**
     * �����б�����
     */
    private List dataList = new ArrayList();

    /**
     * ���β�ѯ�����нɿ�����ϸ����
     */
    private Map mxMap = new HashMap();

    /**
     * һ�Žɿ������ϸ����
     */
    private ArrayList mxList = new ArrayList();

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest httpServletRequest) {
      this.pageSize = String.valueOf(CodeConstant.GZ_PG_LENGTH);
      this.curPage = "0";
      this.nextPage = "1";
      this.totalpage = "0";
      this.message = "";
      this.op_jsje = "=";
      this.op_kssl = "=";
      this.op_sjje = "=";
      this.op_zbsjje = "=";
      this.sjje = "";
      this.jsje = "";
      this.zbsjje = "";
      this.kssl = "";
      this.dataList = new ArrayList();
      this.sbrqq = TinyTools.Date2String(new Date(),"yyyyMMdd");
      this.sbrqz = TinyTools.Date2String(new Date(),"yyyyMMdd");
      this.jkpzh = "";
      this.sphm = "";
      this.jsjdm = "";
      this.djzclxdm = "";
      this.sbfsdm = "";
      this.sklxdm = "";
      this.yhdm = "";
      this.ysjcdm = "";
      this.yskmdm = "";
      this.zsswjgdm = "";
    }

    public void makeMxDate(String sphm)
    {
        this.mxList = (ArrayList)mxMap.get(sphm);
    }

    public String getDjzclxdm()
    {
        return djzclxdm;
    }
    public String getGjbzhydm()
    {
        return gjbzhydm;
    }
    public String getJkpzh()
    {
        return jkpzh;
    }
    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getJsje()
    {
        return jsje;
    }
    public String getKssl()
    {
        return kssl;
    }
    public String getSbfsdm()
    {
        return sbfsdm;
    }
    public String getSbrqq()
    {
        return sbrqq;
    }
    public String getSbrqz()
    {
        return sbrqz;
    }
    public String getSjje()
    {
        return sjje;
    }
    public String getSklxdm()
    {
        return sklxdm;
    }
    public String getSwjgdm()
    {
        return swjgdm;
    }
    public String getSwsdm()
    {
        return swsdm;
    }
    public String getSzdm()
    {
        return szdm;
    }
    public String getSzsmdm()
    {
        return szsmdm;
    }
    public String getYhdm()
    {
        return yhdm;
    }
    public String getYsjcdm()
    {
        return ysjcdm;
    }
    public String getYskmdm()
    {
        return yskmdm;
    }
    public String getZbsjje()
    {
        return zbsjje;
    }
    public String getZsswjgdm()
    {
        return zsswjgdm;
    }
    public void setZsswjgdm(String zsswjgdm)
    {
        this.zsswjgdm = zsswjgdm;
    }
    public void setZbsjje(String zbsjje)
    {
        this.zbsjje = zbsjje;
    }
    public void setYskmdm(String yskmdm)
    {
        this.yskmdm = yskmdm;
    }
    public void setYsjcdm(String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }
    public void setYhdm(String yhdm)
    {
        this.yhdm = yhdm;
    }
    public void setSzsmdm(String szsmdm)
    {
        this.szsmdm = szsmdm;
    }
    public void setSzdm(String szdm)
    {
        this.szdm = szdm;
    }
    public void setSwsdm(String swsdm)
    {
        this.swsdm = swsdm;
    }
    public void setSwjgdm(String swjgdm)
    {
        this.swjgdm = swjgdm;
    }
    public void setSklxdm(String sklxdm)
    {
        this.sklxdm = sklxdm;
    }
    public void setSjje(String sjje)
    {
        this.sjje = sjje;
    }
    public void setSbrqz(String sbrqz)
    {
        this.sbrqz = sbrqz;
    }
    public void setSbrqq(String sbrqq)
    {
        this.sbrqq = sbrqq;
    }
    public void setSbfsdm(String sbfsdm)
    {
        this.sbfsdm = sbfsdm;
    }
    public void setKssl(String kssl)
    {
        this.kssl = kssl;
    }
    public void setJsje(String jsje)
    {
        this.jsje = jsje;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setJkpzh(String jkpzh)
    {
        this.jkpzh = jkpzh;
    }
    public void setGjbzhydm(String gjbzhydm)
    {
        this.gjbzhydm = gjbzhydm;
    }
    public void setDjzclxdm(String djzclxdm)
    {
        this.djzclxdm = djzclxdm;
    }
    public String getCurPage()
    {
        return curPage;
    }
    public void setCurPage(String curPage)
    {
        this.curPage = curPage;
    }
    public void setMessage(String message)
    {
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
    public String getNextPage()
    {
        return nextPage;
    }
    public void setNextPage(String nextPage)
    {
        this.nextPage = nextPage;
    }
    public String getTotalpage()
    {
        return totalpage;
    }
    public void setTotalpage(String totalpage)
    {
        this.totalpage = totalpage;
    }
    public Map getZgswjg()
    {
        return zgswjg;
    }
    public void setZgswjg(Map zgswjg)
    {
        this.zgswjg = zgswjg;
    }
    public void setZgsws(Map zgsws)
    {
        this.zgsws = zgsws;
    }
    public Map getZgsws()
    {
        return zgsws;
    }
    public String getYhjb()
    {
        return yhjb;
    }
    public void setYhjb(String yhjb)
    {
        this.yhjb = yhjb;
    }
    public String getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(String pageSize)
    {
        this.pageSize = pageSize;
    }
    public List getDataList()
    {
        return dataList;
    }
    public void setDataList(List dataList)
    {
        this.dataList = dataList;
    }
    public SfHashList getSmList()
    {
        return smList;
    }
    public void setSmList(SfHashList smList)
    {
        this.smList = smList;
    }
    public void setSzList(SfHashList szList)
    {
        this.szList = szList;
    }
    public SfHashList getSzList()
    {
        return szList;
    }
    public String getOp_jsje()
    {
        return op_jsje;
    }
    public void setOp_jsje(String op_jsje)
    {
        this.op_jsje = op_jsje;
    }
    public void setOp_kssl(String op_kssl)
    {
        this.op_kssl = op_kssl;
    }
    public String getOp_kssl()
    {
        return op_kssl;
    }
    public String getOp_sjje()
    {
        return op_sjje;
    }
    public void setOp_sjje(String op_sjje)
    {
        this.op_sjje = op_sjje;
    }
    public void setOp_zbsjje(String op_zbsjje)
    {
        this.op_zbsjje = op_zbsjje;
    }

    public void setSphm(String sphm) {
        this.sphm = sphm;
    }

    public void setMxMap(Map mxMap) {
        this.mxMap = mxMap;
    }

    public void setMxList(ArrayList mxList) {
        this.mxList = mxList;
    }

    public void setMxsphm(String mxsphm) {
        this.mxsphm = mxsphm;
    }

    public String getOp_zbsjje()
    {
        return op_zbsjje;
    }

    public String getSphm() {
        return sphm;
    }

    public Map getMxMap() {
        return mxMap;
    }

    public ArrayList getMxList() {
        return mxList;
    }

    public String getMxsphm() {
        return mxsphm;
    }

}

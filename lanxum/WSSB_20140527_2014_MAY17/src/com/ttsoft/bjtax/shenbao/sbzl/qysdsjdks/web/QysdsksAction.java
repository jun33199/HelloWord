package com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.web;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.ca.xml.util.XMLParseHelper;
import com.syax.bjtax.shenbao.model.common.HdxxVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.model.common.SbxxVO;
import com.syax.common.util.CAcodeConstants;
import com.syax.common.util.NumberUtils;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.syax.common.web.action.DcBaseAction;
import com.syax.common.xml.util.XMLVOUtil;
import com.syax.frame.exception.ApplicationException;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.syax.frame.util.DateTimeUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.constant.SuccessConstant;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Jm;
import com.ttsoft.bjtax.shenbao.model.domain.Qysdsjd;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksActionConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.xmlvo.QysdsjbVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.xmlvo.SbsjVO;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.LogUtil;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.bjtax.shenbao.util.Skssrq;
import com.ttsoft.bjtax.shenbao.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author He Zhiyong
 * @version 1.0 企业所得税亏损申报前台Action
 */
public class QysdsksAction extends DcBaseAction
{
    // 企业所得税税率
    private static final String QYSDS_SL = "0.33";

    private String errorMessage = "";

    public QysdsksAction()
    {
    }

    protected boolean validate(QysdsjbVO qyjb)
    {
        if (!qyjb.getYwlx().equals(CAcodeConstants.YWDM_SB_WS_QYSDS_JB))
        {
            errorMessage = "业务类型错误，不能执行业务操作。";
            return false;
        }
        if (!(qyjb.getYwczlx().equals(CAcodeConstants.YWCZLX_NEW) || 
                qyjb.getYwczlx().equals(CAcodeConstants.YWCZLX_MODIFY)))
        {
            System.out.println("业务操作类型错误" + qyjb.getYwczlx());
            errorMessage = "业务操作类型错误，不能执行业务操作。";
            return false;
        }

        Timestamp now = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if (!qyjb.getSbxx().getSbrq().startsWith(df.format(now)))
        {
            errorMessage = "申报日期错误。";
            return false;
        }
        return true;
    }

    protected int getActionID()
    {
        return com.ttsoft.bjtax.shenbao.util.SbzlAccess.QYKS;
    }

    public String doSave(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
         if (!isTokenValid(request))
         {
            return CAConstants.INVALIDTOKEN;
         }

        // System.out.println("enter doSave...........");
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        
        UserData ud = (UserData) this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        Map retmap = null;

        QysdsjbVO qyjb = new QysdsjbVO();

        try
        {
            
            if (ud.getCaflag())
            {
                try
                {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1)
                {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            //System.out.println("save xml:"+xmldata);
            try
	         {
	            // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
	           //  qyjb = new QysdsjbVO();
	             XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.VTDXML_PARSER,false,true);
	         }
	         catch (ApplicationException e)
	         {
	             throw ExceptionUtil.getBaseException(e);
	         }

            Qysdsjd qysdsjd = convert2VO(qyjb);
            dzyj.setYwdm(qyjb.getYwlx());
            dzyj.setYwczlx(qyjb.getYwczlx());
            //Debug.out("convert2VO ok ... ");
            // 取得Form
            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);

            if (qyjb.getHdxx().getJmzg().equals("1") && qyjb.getSbsj().getJmsdse() != null)
            {
                Timestamp now = new Timestamp(System.currentTimeMillis());

                Jm jm = new Jm();
                jm.setJsjdm(djJbsj.getJsjdm());
                jm.setJmlx(CodeConstant.JMLX_SP); // 审批减免
                jm.setSzsmdm(SzsmdmConstant.QYSDS_SM);
                jm.setSbrq(TinyTools.second2Day(now));
                jm.setLrrq(now);
                jm.setJsje(new BigDecimal(qyjb.getSbsj().getJmsdse()));
                jm.setKssl(null);
                jm.setJmse(jm.getJsje());
                jm.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
                jm.setLrr(djJbsj.getJsjdm());
                jm.setFsdm(CodeConstant.FSDM_WSSB);

                jm.setDjzclxdm(djJbsj.getDjzclxdm());
                jm.setGjbzhydm(djJbsj.getGjbzhydm());
                jm.setYskmdm(null); // 在processor赋值
                jm.setYsjcdm(null); // 在processor赋值
                Timestamp skssksrq = new Timestamp((new SimpleDateFormat("yyyy-MM-dd")).parse(
                        qyjb.getSbxx().getSkssksrq()).getTime());
                Timestamp skssjsrq = new Timestamp((new SimpleDateFormat("yyyy-MM-dd")).parse(
                        qyjb.getSbxx().getSkssjsrq()).getTime());
                jm.setSkssksrq(skssksrq); // 税款所属开始时间
                jm.setSkssjsrq(skssjsrq); // 税款所属结束时间
                jm.setNd((new SimpleDateFormat("yyyy")).format(now));
                jm.setCjrq(now);
                jm.setQxdm(djJbsj.getSwjgzzjgdm().substring(0, 2));

                String jmxmdm = (new ServiceProxy()).getJmsbs(djJbsj.getJsjdm(), SzsmdmConstant.QYSDS, skssksrq,
                        skssjsrq);
                jm.setJmxmdm(jmxmdm); // 减免项目代码

                pData.put("QYSDSJB_JM", jm);
            }

            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);

            // 构造VoPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.QYSDSKS_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_SAVE);
            vo.setUserData(ud);
            // 调用后台Procxy


            //设置回执下载页面的后续操作。
            retmap = (Map) ShenbaoProxy.getInstance().process(vo);
            
            if (ud.getCaflag() )
            {
                dzyj = (DzyjsjVO) retmap.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
                ArrayList hzlist = new ArrayList();
                hzlist.add(Long.toString(dzyj.getLsh()));
//                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,Long.toString(dzyj.getLsh()));
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,hzlist);
            }
            else
            {
                request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            }
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "企业所得税季度纳税申报表"+CAUtils.getTsxx(qyjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"企业所得税季度纳税申报表"+CAUtils.getTsxx(qyjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_QYJB_SAVE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税季度纳税申报表申报", qyjb.getSbxx().getSbrq(),
                    "成功!");

            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.SAVE,CAConstants.SUCCESSSB);
        }
        catch (Exception ex)
        {
			// /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qyjb.getSchemaVersion());
            ex.printStackTrace();
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税季度纳税申报表申报", qyjb.getSbxx().getSbrq(),
                    "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    private void getHdxx(String jsjdm, HdxxVO hdvo, SbsjVO sbsj, SWDJJBSJ swdjjbsj) throws BaseException
    {
        String qyzssllx = "3"; // 缺省为正常申报

        // 企业征税的税率 相对于企业征税的税率类型
        String qyzssl = QYSDS_SL;

        // 应纳所得税额
        String ynsdse = "0.00";
        // 定额征收税额
        String dezsse = "0.00";

        // 当前时间
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
        Map ssrq = Skssrq.getSksssq(jsjdm, SzsmdmConstant.QYSDS_SM, swdjjbsj.getDjzclxdm(), CodeConstant.SKLXDM_ZCJK,
                CodeConstant.ZQLXDM_QUARTER, sbrq, null, null, null);

        // 取得税款所属开始和结束日期
        Timestamp skssksrq = (Timestamp) ssrq.get(Skssrq.SKSSKSRQ);
        Timestamp skssjsrq = (Timestamp) ssrq.get(Skssrq.SKSSJSRQ);

        ServiceProxy proxy = new ServiceProxy();

        // 查询税费接口
        QysdsSet qysdsSet = null;
        try
        {
            qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, "00");
        }
        catch (com.ttsoft.framework.exception.BaseException e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        // 1、查询企业征收方式
        Zsfs zsfs = qysdsSet.getZsfs();

        Date gxqyrdrq = qysdsSet.getGxjsqy();

        // 初值
        hdvo.setCyl("0");
        hdvo.setXzqy("0");
        hdvo.setDezsse("0.00");
        hdvo.setYbjmsl("0.00");

        if (zsfs != null)
        {
            String zsfsdm = zsfs.getZsfsdm();
            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS))
            {
                if (gxqyrdrq == null)
                {
                    // 纯益率征收
                    qyzssllx = "2";
                }
                else
                {
                    // 高新技术和纯益率企业
                    qyzssllx = "5";
                    qyzssl = "0.15";
                }
                hdvo.setCyl(zsfs.getCyl());
            }
            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS))
            {
                // 定额征收
                qyzssllx = "4";
                // 此时本字段代表企业核定税额
                // ynsdse = zsfs.getDe();
                dezsse = zsfs.getDe();
                hdvo.setDezsse(dezsse);
            }
        }

        // 2、查询是否是高新技术企业
        if (gxqyrdrq != null)
        {
            if (zsfs != null && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS))
            {
                // 高新技术和纯益率企业
                qyzssllx = "5";
            }
            else
            {
                // 类型为高新技术企业
                qyzssllx = "1";
            }
            qyzssl = "0.15";
        }

        // 判断是否乡镇企业，“1”为乡镇企业，“0”为不是乡镇企业
        else if (swdjjbsj.getDjzclxdm().equals(CodeConstant.DJZXLXDM_JTQY))
        {
            if (qysdsSet.isXzqy())
            {
                hdvo.setXzqy("1");
                hdvo.setJmzg("1"); // 有减免资格
            }
        }

        if (!(hdvo.getXzqy() != null && hdvo.getXzqy().equals("1")) && qysdsSet.getYbjmsl() != null)
        {
            // 非乡镇企业的减免情况
            hdvo.setJmzg("1"); // 有减免资格
            DecimalFormat ft = new DecimalFormat("0.00");
            hdvo.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
        }
        hdvo.setQyzslx(qyzssllx);
        sbsj.setSl(qyzssl);
    }

    /**
     * 将旧的VO对象转换为XML-VO对象。
     * 
     * @param qysdsjd
     * @param djJbsj
     * @return
     * @throws BaseException
     */
    private QysdsjbVO convert2XMLVO(Qysdsjd qysdsjd, SWDJJBSJ djJbsj) throws BaseException
    {
        SbxxVO sbxx = new SbxxVO();
        SbsjVO sbsj = new SbsjVO();
        HdxxVO hdxx = new HdxxVO();
        NsrxxVO nsrxx = new NsrxxVO();
        QysdsjbVO qysdsjb = new QysdsjbVO();
        // 获得系统当前日期
        Timestamp curDate = new Timestamp(System.currentTimeMillis());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // ////////////////////////////////////
        // 填充VO。
        // 纳税人信息。
        nsrxx.setJsjdm(djJbsj.getJsjdm());
        nsrxx.setNsrmc(djJbsj.getNsrmc());
        nsrxx.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());

        qysdsjb.setYwczlx(CAcodeConstants.YWCZLX_NEW);
        if (qysdsjd == null)
        {
            // 如果后台没有查询到企业所得税亏损数据，new一个Qysdsjd值对象并设置属性
            // 取得所在的季度
            qysdsjd = new Qysdsjd();
            qysdsjb.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
            Map ssrq = Skssrq.getSksssq(djJbsj.getJsjdm(), SzsmdmConstant.QYSDS_SM, djJbsj.getDjzclxdm(),
                    CodeConstant.SKLXDM_ZCJK, CodeConstant.ZQLXDM_QUARTER, curDate, null, null, null);

            Timestamp skssksrq = (Timestamp) ssrq.get(Skssrq.SKSSKSRQ);
            Timestamp skssjsrq = (Timestamp) ssrq.get(Skssrq.SKSSJSRQ);

            String jd = Skssrq.preQuarter(curDate);
            // 取得年度

//            String nd = new SimpleDateFormat("yyyy").format(curDate);
            String nd = new SimpleDateFormat("yyyy").format(skssjsrq);


            qysdsjd.setJsjdm(djJbsj.getJsjdm());
            qysdsjd.setNd(nd);
            qysdsjd.setJd(jd);
            qysdsjd.setCjrq(curDate);
            qysdsjd.setFsdm(CodeConstant.FSDM_WSSB);
            qysdsjd.setSwjgzzjgdm(djJbsj.getSwjgzzjgdm());
            qysdsjd.setLrr(djJbsj.getJsjdm());
            qysdsjd.setSrze(null);
            qysdsjd.setLrze(null);
            qysdsjd.setSl(null);
            qysdsjd.setYnsdse(null);
            qysdsjd.setQcwjsdse(null);
            qysdsjd.setMbyqndks(null);
            qysdsjd.setBkhlrze(null);

            qysdsjd.setJmsdse(null);
            qysdsjd.setCbyqndse(null);
            qysdsjd.setSjyjsdsse(null);
            qysdsjd.setBqyjsdse(null);
            qysdsjd.setSjybsdse(null);
            qysdsjd.setQxdm(djJbsj.getSwjgzzjgdm().substring(0, 2));
            qysdsjd.setSkssksrq(skssksrq);
            qysdsjd.setSkssjsrq(skssjsrq);

        }
        // 核定信息，包含一部分申报信息
        getHdxx(djJbsj.getJsjdm(), hdxx, sbsj, djJbsj);

        // 信息
        sbxx.setFsdm(CodeConstant.FSDM_WSSB);
        sbxx.setJd(qysdsjd.getJd());
        sbxx.setNd(qysdsjd.getNd());
        sbxx.setSbrq(sdf.format(curDate));
        sbxx.setSkssjsrq(sdf.format(qysdsjd.getSkssjsrq()));
        sbxx.setSkssksrq(sdf.format(qysdsjd.getSkssksrq()));

        // 申报数据
        sbsj.setBkhlrze(StringUtils.bigDeciaml2String(qysdsjd.getBkhlrze(), "0.00"));
        sbsj.setBqyjsdse(StringUtils.bigDeciaml2String(qysdsjd.getBqyjsdse(), "0.00"));
        sbsj.setCbyqndse(StringUtils.bigDeciaml2String(qysdsjd.getCbyqndse(), "0.00"));
        sbsj.setJmsdse(StringUtils.bigDeciaml2String(qysdsjd.getJmsdse(), "0.00"));


        sbsj.setLrze(StringUtils.bigDeciaml2String(qysdsjd.getLrze(), "0.00"));
        sbsj.setMbyqndks(StringUtils.bigDeciaml2String(qysdsjd.getMbyqndks(), "0.00"));
        sbsj.setQcwjsdse(StringUtils.bigDeciaml2String(qysdsjd.getQcwjsdse(), "0.00"));
        sbsj.setSjybsdse(StringUtils.bigDeciaml2String(qysdsjd.getSjybsdse(), "0.00"));
        sbsj.setSjyjnssdse(StringUtils.bigDeciaml2String(qysdsjd.getSjyjsdsse(), "0.00"));
        if (qysdsjd.getSl() != null)
        {
            sbsj.setSl(StringUtils.bigDeciaml2String(qysdsjd.getSl(), "0.00"));
        }
        sbsj.setSrze(StringUtils.bigDeciaml2String(qysdsjd.getSrze(), "0.00"));
        sbsj.setYnsdse(StringUtils.bigDeciaml2String(qysdsjd.getYnsdse(), "0.00"));

        // 企业所得税季报
        qysdsjb.setNsrxx(nsrxx);
        qysdsjb.setSbsj(sbsj);
        qysdsjb.setSbxx(sbxx);
        qysdsjb.setHdxx(hdxx);

        // XML文档信息
        qysdsjb.setXsltVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_QYSDS_JB).substring(0,8));
        qysdsjb.setSchemaVersion(CAProxy.getInstance().getXSLTSCHEMAVersion(CAcodeConstants.YWDM_SB_WS_QYSDS_JB).substring(8));
        qysdsjb.setYwlx(CAcodeConstants.YWDM_SB_WS_QYSDS_JB);
        return qysdsjb;
    }

    /**
     * 将XML-VO对象转换为旧的VO对象。
     * 
     * @param qysdsjb
     * @return
     * @throws BaseException
     */
    private Qysdsjd convert2VO(QysdsjbVO qysdsjb) throws BaseException
    {
        XMLVOUtil.fillVODecimalFields(qysdsjb.getSbsj(), new String[]
        { "bkhlrze", "bqyjsdse", "cbyqndse", "jmsdse", "mbyqndks", "qcwjsdse", "sjybsdse", "sjyjnssdse", "sl", "srze",
                "ynsdse" });

        Qysdsjd jdvo = new Qysdsjd();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        jdvo.setBkhlrze(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getBkhlrze(), null));
        jdvo.setBqyjsdse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getBqyjsdse(), null));
        jdvo.setCbyqndse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getCbyqndse(), null));
        jdvo.setCjrq(now);
        jdvo.setFsdm(qysdsjb.getSbxx().getFsdm());
        jdvo.setJd(qysdsjb.getSbxx().getJd());
        jdvo.setJmsdse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getJmsdse(), null));
        jdvo.setJsjdm(qysdsjb.getNsrxx().getJsjdm());
        jdvo.setLrr(qysdsjb.getNsrxx().getJsjdm());

        jdvo.setLrrq(now);
        jdvo.setMbyqndks(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getMbyqndks(), null));
        jdvo.setNd(qysdsjb.getSbxx().getNd());
        jdvo.setQcwjsdse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getQcwjsdse(), null));
        jdvo.setQxdm(qysdsjb.getNsrxx().getSwjgzzjgdm().substring(0, 2));
        jdvo.setSbrq(DateTimeUtil.stringToTimestamp(qysdsjb.getSbxx().getSbrq(), "yyyy-MM-dd"));
        jdvo.setSjybsdse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getSjybsdse(), null));
        jdvo.setSjyjsdsse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getSjyjnssdse(), null));
        jdvo.setSkssjsrq(DateTimeUtil.stringToTimestamp(qysdsjb.getSbxx().getSkssjsrq(), "yyyy-MM-dd"));
        jdvo.setSkssksrq(DateTimeUtil.stringToTimestamp(qysdsjb.getSbxx().getSkssksrq(), "yyyy-MM-dd"));
        jdvo.setSl(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getSl(), null));
        jdvo.setSrze(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getSrze(), null));
        jdvo.setLrze(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getLrze(), null));
        jdvo.setSwjgzzjgdm(qysdsjb.getNsrxx().getSwjgzzjgdm());
        jdvo.setYnsdse(NumberUtils.string2BigDecimal(qysdsjb.getSbsj().getYnsdse(), null));

        return jdvo;
    }
    /**
     * 删除亏损申报数据
     * 1.取得QysdsksForm
     * 2.调用form的toVo方法转换数据
     * 3.构造VoPackage
     * 4.调用ShenBaoProxy的processor方法，传入VoPackage
     * 5.利用ActionMapping转向
     * @param mapping ActionMapping
     * @param form QysdsksForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     * @throws BaseException
     */
    public String doDelete(
                                  HttpServletRequest request,
                                  HttpServletResponse response) throws
        BaseException
    {
        // 检查token
        if (!isTokenValid(request))
        {
            return CAConstants.INVALIDTOKEN;
        }

        // 得到xml
        String xmldata = request.getParameter(CAConstants.REQ_KEY_XML_PARANAME);
        
        UserData ud = (UserData) this.getUserData(request);
        DzyjHelper dh = new DzyjHelper();
        DzyjsjVO dzyj = new DzyjsjVO();
        QysdsjbVO qyjb = new QysdsjbVO();
        Timestamp sbrq = new Timestamp(System.currentTimeMillis());
        try
        {
            if (ud.getCaflag())
            {
                try
                {
                    dzyj = dh.verifyAndSign(request, ud.getCert(), ud.getSsdwdm());
                }
                catch (ApplicationException e1)
                {
                    throw ExceptionUtil.getBaseException(e1);
                }
            }
            System.out.println("delete xml:"+xmldata);
            
            try
            {
               // XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.XERCES_PARSER,true,true);
               // qyjb = new QysdsjbVO();
                XMLParseHelper.parseXMLString(qyjb,xmldata,XMLParseHelper.VTDXML_PARSER,false,true);
            }
            catch (ApplicationException e)
            {
                throw ExceptionUtil.getBaseException(e);
            }
            dzyj.setYwczlx(qyjb.getYwczlx());
            dzyj.setYwdm(qyjb.getYwlx());

            // 取得登记基本数据
            SWDJJBSJ djJbsj = (SWDJJBSJ)FriendHelper.getSWDJJBSJ(request);
            //更新form中的数据
            Qysdsjd qysdsjd = convert2VO(qyjb);
            Map pData = new HashMap();
            pData.put(QysdsksMapConstant.VO_KEY_KSSBSJ, qysdsjd);
            pData.put(QysdsksMapConstant.STRING_KEY_JBSJ, djJbsj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            pData.put(CAcodeConstants.CA_MAPKEY_VO_XMLVO, qyjb);
            Map retmap = null;
            //构造VOPackage
            VOPackage vo = new VOPackage();
            vo.setData(pData);
            vo.setProcessor(ProcessorNames.QYSDSKS_PROCESSOR);
            vo.setAction(QysdsksActionConstant.INT_ACTION_DELETE);
            vo.setUserData(ud);
            //调用后台Proxy
            ShenbaoProxy.getInstance().process(vo);

            request.setAttribute(CAConstants.REQ_SHENBAO_HUIZHI_KEY,"");
            request.setAttribute(CAConstants.REQ_KEY_SUCCESS_MSG,"企业所得税季度纳税申报表"+CAUtils.getTsxx(qyjb.getYwczlx()));
            request.setAttribute(CAConstants.REQ_KEY_RETURN_ADDRESS,CAConstants.REQ_KEY_RETURN_QYJB_DELETE);
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "企业所得税季度纳税申报表删除", qyjb.getSbxx().getSbrq(),
                    "成功!");
            request.setAttribute(SuccessConstant.SUCCESS_MESSAGE, "企业所得税季度纳税申报表"+CAUtils.getTsxx(qyjb.getYwczlx()));
            
            return CAUtils.jumpTo(ud.getCaflag(),CAConstants.DELETE,CAConstants.SUCCESSSB);
        }
        catch(Exception ex)
        {
			// /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qyjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qyjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qyjb.getSchemaVersion());
            LogUtil.getInstance().log(FriendHelper.getUserData(request), "删除企业所得税季度纳税申报表", (new SimpleDateFormat("yyyyMMdd")).format(sbrq), "失败!");
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    public String doShow(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {

        // 登记基本数据
        SWDJJBSJ djJbsj = null;
        // 构造VOPackage
        VOPackage vo = new VOPackage();
        Map pDataMap = new HashMap();
        UserData ud = (UserData) request.getSession(false).getAttribute("UserData");
        if (ud == null)
        {
            System.out.println("session is null");
        }

        String jsjdm = ud.yhid;

        Timestamp curDate = new Timestamp(System.currentTimeMillis());

        Qysdsjd qysdsjd = null;
        QysdsjbVO qysdsjb = new QysdsjbVO(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        DecimalFormat ft = new DecimalFormat("0.00");
        try
        {
            // 取得登记基本数据
            djJbsj = (SWDJJBSJ) FriendHelper.getSWDJJBSJ(request);

            pDataMap.put(QysdsksMapConstant.STRING_KEY_JSJDM, jsjdm);
            pDataMap.put(QysdsksMapConstant.STRING_KEY_DATE, curDate);
            pDataMap.put(QysdsksMapConstant.OBJECT_KEY_DJSJ, djJbsj);
            vo.setAction(QysdsksActionConstant.INT_ACTION_QUERY);
            vo.setProcessor(ProcessorNames.QYSDSKS_PROCESSOR);
            vo.setData(pDataMap);
            vo.setUserData(ud);
            // 调用后台查询数据

            vo = (VOPackage) ShenbaoProxy.getInstance().process(vo);

            pDataMap = (Map) vo.getData();
            qysdsjd = (Qysdsjd) pDataMap.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

            qysdsjb = convert2XMLVO(qysdsjd, djJbsj);
            String tmpxml = qysdsjb.toXML();
             System.out.println("show xml:"+tmpxml);

            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, tmpxml);
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjb.getSchemaVersion());
            
            //if (true) throw new Exception("test");

            // 转向企业所得税亏损申报页面
            return CAConstants.SHOW;
        }
        catch (Exception ex)
        {
			// /3.9.设置空xml，转向失败页面
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, qysdsjb.toXML());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, qysdsjb.getXsltVersion());
            request.setAttribute(CAConstants.REQ_KEY_CA_XML_SCHEMA_VERSION, qysdsjb.getSchemaVersion());
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    protected String beforePerform(HttpServletRequest request, HttpServletResponse response)
    {
        // 检查权限
        if (!SbzlAccess.getAuthority(SbzlAccess.QYKS, request))
        {
            return CAConstants.NOPERMISSION;
        }

        return null;
    }

    public String doReturn(HttpServletRequest request, HttpServletResponse response) throws BaseException
    {
        return "Return";
    }
}
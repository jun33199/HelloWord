package com.syax.bjtax.shenbao.jmba.qygzb.web;

import com.syax.common.web.action.DcBaseAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.framework.util.VOPackage;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.ttsoft.bjtax.shenbao.proxy.ShenbaoProxy;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.framework.exception.BaseException;
import java.util.ArrayList;
import java.util.List;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import java.util.Map;
import java.util.HashMap;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;

public class QygzbAction extends DcBaseAction{
    public QygzbAction() {
    }

    /**
     * 录入备案申请 初始请求处理
     * @param request
     * @param response
     * @return
     * @throws BaseException
     */
    public String doShow(HttpServletRequest request,
            HttpServletResponse response) throws BaseException {

        // 取得userdata
        UserData userdata = (UserData) this.getUserData(request);
        // 生成VOPackage
        VOPackage voPackage = new VOPackage();
        // 设定vopackage参数
        voPackage.setProcessor(ProcessorNames.QYGZB16_PROCESSOR);
        voPackage.setAction(JmbaActionConstant.INTI_ACTION_SHOW);
        voPackage.setUserData(userdata);
        //@todo 从第二个跳转页面的request中获取
        String BASQWSH = "062008000002";
        String jsjdm = userdata.getYhid();
        //纳税人的主管税务机关
        String zgswjg = userdata.getSsdwdm();
        Map map = new HashMap();
        map.put("BASQWSH",BASQWSH);
        map.put("jsjdm",jsjdm);

        voPackage.setData(map);

        // 调用后台操作，取得返回值
        voPackage = (VOPackage) ShenbaoProxy.getInstance().process(
                voPackage);

        JmbaVO vo  = this.convertToXmlVO((Map)voPackage.getData(),userdata);
        //构造数据
        String strXml = vo.toXML();
        Debug.out(strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_DATA, strXml);
        request.setAttribute(CAConstants.REQ_KEY_CA_XML_XSLT_VERSION, vo.getXsltVersion());

        // 转向到显示页面
        return CAConstants.SHOW;
    }

    private JmbaVO convertToXmlVO(Map map,UserData ud) {
        //1 最上层VO
        JmbaVO vo = new JmbaVO();
        //2 纳税人VO 1.set 2
        NsrxxVO nsrxx = new NsrxxVO();
        nsrxx.setJsjdm(ud.getYhid());
        nsrxx.setNsrmc(ud.getYhmc());
        nsrxx.setSwjgzzjgdm(ud.getSsdwdm());
        //1.set 2
        vo.setNsrxx(nsrxx);
        //3 主表VO 1-n 备案文书号 1.list.add 3s
        JmbaZbVO zbvo = (JmbaZbVO) map.get("JmbaZbVO");
        //db query result,set zbvo data
        //zbvo.setJmbasxdm

        //SF_JL_QYSDSJMSBAJLZLQD
        List zlqdVOs = (List) map.get("JmbaZlqdVO");
        //4 明细数据VO 1-ns   3.list.add 4s
//        Jmba12VO vo13 = (Jmba13VO)map.get("Jmba12VO");
        //3.list.add 4s
//        zbvo.getQysdsjmba().add(vo12);
//        资料清单查询VO处理,功能页面暂时不调用
//        zbvo.getBajlzlqd().add(zlqdVOs);
        //1.list.add 3s
        vo.getJmsbajl().add(zbvo);
        vo.setNsrxx(nsrxx);
        vo.setXsltVersion("091216");
        return vo;
    }



    /**
     * 对请求的权限进行检查
     */
    protected String beforePerform(HttpServletRequest request,
            HttpServletResponse response) {
        // 检查权限 暂设置没有检查条件
        // System.out.println("beforePerform");
//        if (!SbzlAccess.getAuthority(SbzlAccess.JMBASX, request))
//        {
//
//            return CAConstants.NOPERMISSION;
//        }
        return null;
    }

}

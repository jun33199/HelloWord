package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: 扣缴企业所得税-备案登记表Action帮助类</p>
 *
 * <p>Description: 相关公用方法</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import javax.servlet.http.HttpServletRequest;
import com.ttsoft.framework.exception.ApplicationException;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.dj.model.dm.GJ;
import com.ttsoft.bjtax.dj.model.dm.BZ;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbListVO;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbVO;
import com.syax.bjtax.shenbao.model.kjqysds.KJYWRXX;
import com.syax.bjtax.shenbao.model.kjqysds.FJMQYXX;
import com.syax.bjtax.shenbao.model.kjqysds.BAHTXX;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo.BadjbBO;



public class ActionHelper
{
    public ActionHelper()
    {
    }

    /**
     * 初始化页面下拉信息
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request) throws ApplicationException
    {
        // 获取session
        HttpSession session = request.getSession(false);
        // 获取国家列表
        if (session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) == null) {
            List gjdqList = CodeTableUtil.getCodeTableList(CodeTableKey.GJ);
//            System.out.println("gjdqList size = " + gjdqList.size());
            // 转换之后的国家地区
            String[][] gjdq = new String[gjdqList.size()][2];
            for (int i = 0; i < gjdqList.size(); i++) {
                GJ gj = (GJ) gjdqList.get(i);
                if("CHN".equals(gj.getGjdm()))
                {
                    gjdqList.remove(i);
                    i--;
                }
                else{
                    gjdq[i][0] = gj.getGjdm();
                    gjdq[i][1] = gj.getGjmc();
                }
            }
            session.setAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ, gjdq);
        }
        // 获取币种列表
        if (session.getAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
            List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
//            System.out.println("bzList size = " + bzList.size());
            // 转换之后的币种
            String[][] bz = new String[bzList.size()][2];
            for (int i = 0; i < bzList.size(); i++) {
                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
            }
            session.setAttribute(KjqysdsConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
        }
    }

    /**
     * 将bo对象转换成VO对象供前台使用
     * @param bo BadjbBO
     * @param vo BadjbVO
     */
    public static void XMLVO2BO(BadjbVO vo, BadjbBO bo)
    {
        if(bo != null)
        {
        	// 备案登记序号
        	bo.setBadjxh(vo.getBadjbxh());
            // 填报日期
            bo.setTbrq(vo.getTbrq());
            // 展示填报日期
            bo.setTbrqShow(vo.getTbrqShow());
            // 当前日期
            bo.setCurrentPage(Integer.parseInt(vo.getCurrentPage()));
            // 修改标记
            bo.setModifyFlag(vo.getModifyFlag());
            // 扣缴义务人信息
            bo.setKjywrxx(vo.getKjywrxx() == null ? new KJYWRXX() : vo.getKjywrxx());
            // 非居民企业信息
            bo.setFjmqyxx(vo.getFjmqyxx() == null ? new FJMQYXX() : vo.getFjmqyxx());
            // 合同信息
            bo.setHtxx(vo.getHtxx() == null ? new BAHTXX() : vo.getHtxx());
        }
    }


    /**
     * 将bo对象转换成VO对象供前台使用
     * @param bo BadjbBO
     * @param vo BadjbVO
     */
    public static void BO2XMLVO(BadjbBO bo, BadjbVO vo)
    {
        if(bo != null)
        {
            // 填报日期
            vo.setTbrq(bo.getTbrq());
            // 展示填报日期
            vo.setTbrqShow(bo.getTbrqShow());
            vo.setModifyFlag(bo.getModifyFlag());
            // 扣缴义务人信息
            vo.setKjywrxx(bo.getKjywrxx() == null ? new KJYWRXX() : bo.getKjywrxx());
            // 非居民企业信息
            vo.setFjmqyxx(bo.getFjmqyxx() == null ? new FJMQYXX() : bo.getFjmqyxx());
            /**
             * 合同信息
             */
            // 处理其它资料名称
            if(bo.getHtxx() != null)
            {
            	// 其它资料名称
	            String qtzlmc = bo.getHtxx().getQtzlmc();
	            //将其它资料名称格式符去掉
	            qtzlmc = qtzlmc.replaceAll("<br/>","\r\n");
	            qtzlmc = qtzlmc.replaceAll("<br>","\r\n");
	            qtzlmc = qtzlmc.replaceAll("&nbsp;"," ");
	            
	            bo.getHtxx().setQtzlmc(qtzlmc);
            }
            vo.setHtxx(bo.getHtxx() == null ? new BAHTXX() : bo.getHtxx());
            // 备案合同信息
            BadjbListVO listVO = new BadjbListVO();
            listVO.setMxxx(bo.getRecordList());
            vo.setMxxx(listVO);

            // XML文档信息
            vo.setXsltVersion(KjqysdsConstant.CA_XSLTDM_BADJB_2009);
            vo.setSchemaVersion(KjqysdsConstant.CA_SCHEMADM_BADJB_2009);
            vo.setYwlx(KjqysdsConstant.CA_YWLXDM_BADJB_2009);
            vo.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
        }
    }
}

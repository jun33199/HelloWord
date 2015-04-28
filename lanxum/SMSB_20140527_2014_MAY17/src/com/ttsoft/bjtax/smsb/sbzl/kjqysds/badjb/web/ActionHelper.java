package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


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
import com.ttsoft.bjtax.smsb.constant.CodeConstant;

import java.text.NumberFormat;
import java.util.List;
import com.ttsoft.bjtax.dj.util.CodeTableKey;
import com.ttsoft.bjtax.dj.util.CodeTableUtil;
import com.ttsoft.bjtax.dj.model.dm.GJ;
import com.ttsoft.bjtax.dj.model.dm.BZ;



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
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ) == null) {
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
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_GJDQ, gjdq);
        }
        // 获取币种列表
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ) == null) {
            List bzList = CodeTableUtil.getCodeTableList(CodeTableKey.BZ);
//            System.out.println("bzList size = " + bzList.size());
            // 转换之后的币种
            String[][] bz = new String[bzList.size()][2];
            for (int i = 0; i < bzList.size(); i++) {
                bz[i][0] = ((BZ) bzList.get(i)).getBzdm();
                bz[i][1] = ((BZ) bzList.get(i)).getBzmc();
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BZ, bz);
        }
    }

    /**
     * 将form对象转换成BO对象供后台使用
     * @param form BadjbForm
     * @param bo BadjbBO
     */
    public static void form2Bo(BadjbForm form, BadjbBO bo)
    {
        // 备案登记表序号（主键）
        bo.setBadjxh(form.getBadjxh());
        // 计算机代码
        bo.setJsjdm(form.getJsjdm());
        // 填报日期
        bo.setTbrq(form.getTbrq());
        // 备案登记表号
        bo.setBadjbbh(form.getBadjbbh());
        // 扣缴义务人信息
        bo.setKjywrxx(form.getKjywrxx());
        // 非居民企业信息
        bo.setFjmqyxx(form.getFjmqyxx());
        // 合同信息
        bo.setHtxx(form.getHtxx());
        // 记录数
        bo.setTotalCount(form.getTotalCount());
        // 总页数
        bo.setTotalPage(form.getTotalPage());
        // 当前页
        bo.setCurrentPage(form.getCurrentPage());
        // 状态标识
        bo.setZtbz(form.getZtbz());
        // 修改标识
        bo.setModifyFlag(form.getModifyFlag());
    }

    /**
     * 将bo对象转换成form对象供前台使用
     * @param bo BadjbBO
     * @param form BadjbForm
     */
    public static void BO2form(BadjbBO bo, BadjbForm form)
    {
        if(bo != null)
        {
            // 备案登记表序号（主键）
            form.setBadjxh(bo.getBadjxh());
            // 计算机代码
            form.setJsjdm(bo.getJsjdm());
            // 填报日期
            form.setTbrq(bo.getTbrq());
            // 备案登记表号
            form.setBadjbbh(bo.getBadjbbh());
            // 扣缴义务人信息
            form.setKjywrxx(bo.getKjywrxx());
            // 非居民企业信息
            form.setFjmqyxx(bo.getFjmqyxx());
            /**
             * 合同信息
             */
            // 处理其它资料名称
            // 其它资料名称
            String qtzlmc = bo.getHtxx().getQtzlmc();
            //将其它资料名称格式符去掉
            qtzlmc = qtzlmc.replaceAll("<br/>","\r\n");
            qtzlmc = qtzlmc.replaceAll("<br>","\r\n");
            qtzlmc = qtzlmc.replaceAll("&nbsp;"," ");
            
            bo.getHtxx().setQtzlmc(qtzlmc);
            form.setHtxx(bo.getHtxx());
            // 记录数
            form.setTotalCount(bo.getTotalCount());
            // 总页数
            form.setTotalPage(bo.getTotalPage());
            // 当前页
            form.setCurrentPage(bo.getCurrentPage());
            // 查询结果
            form.setRecordList(bo.getRecordList());

        }
    }
    
    /**
     * 
     */
    public  static void splitJe(BadjbForm form)
    {	
    	if(form.getHtxx().getHtje()!=null){
    	double money=Double.parseDouble(form.getHtxx().getHtje());
    	NumberFormat nf=NumberFormat.getInstance(); 
		String ss=nf.format(money); 
		form.getHtxx().setHtje(ss);
    	}
    }
}

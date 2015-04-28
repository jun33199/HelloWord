package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.sfgl.common.model.Sfxy;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.proxy.LWUtil;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.bjtax.shenbao.util.SbzlAccess;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;


/**
 * 有三方协议的综合申报action
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZhsbWithSfxyAction extends ZhsbAction
{
    public ZhsbWithSfxyAction()
    {
    }
    //start modified code by qianchao 2006-2-13
//    protected int getActionID()
//    {
//        return SbzlAccess.ZHSBWITHSFXY;
//    }
    //end   modified code by qianchao 2006-2-13

    protected DeclareInfor setDeclareInforDetail(DeclareInfor declareInfor,
                                                 HttpServletRequest request)
    {
      Sbjkzb zb = declareInfor.getSbjkzb();
      zb.setClbjdm(CodeConstant.CLBJDM_DHK);
      declareInfor.setIsReturnPaymentInfo(true);
      /**
       * Modified by Ha Zhengze 20051008 20:59 为部分区县试运行进行一票多税的调整 Start
       */
       //declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
      UserData ud = this.getUserData(request);
      String yhdm = zb.getYhdm();
      if (yhdm == null) {
        // 用户没有登记银行信息，一票一税
        declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
      }
      else {
        Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
        Yh yh = (Yh) yhMap.get(yhdm);
        if (LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),
                        yh.getYhdm())) {
          // 区县银行已联网，一票多税
          super.log("区县银行已联网，一票多税");
          declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
        }
        else {
          super.log("银行未联网，一票一税");
          // 银行未联网，一票一税
          declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
        }
      }
      /**
       * Modified by Ha Zhengze 20051008 20:59 为部分区县试运行进行一票多税的调整 End
       */
      return declareInfor;
    }

    protected void setBankInfo(HttpServletRequest request, ZhsbForm form)
        throws BaseException
    {
        ZhsbWithSfxyForm myForm = (ZhsbWithSfxyForm)form;
        // 从税费获得三方协议
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);

        // 设置银行信息
        myForm.setJkyhmc(sfxy.getYhmc());
        myForm.setJkyhzh(sfxy.getZh());
    }

    protected BankInfo getPaymentBankInfo(HttpServletRequest request, ZhsbForm form)
        throws BaseException
    {
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);

        BankInfo bankInfo = new BankInfo();
        bankInfo.accountNumber = sfxy.getZh();
        bankInfo.bankID = sfxy.getYhdm();
        bankInfo.bankName = sfxy.getYhmc();

        return bankInfo;
    }
}
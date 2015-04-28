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
 * ������Э����ۺ��걨action
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
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
       * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� Start
       */
       //declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
      UserData ud = this.getUserData(request);
      String yhdm = zb.getYhdm();
      if (yhdm == null) {
        // �û�û�еǼ�������Ϣ��һƱһ˰
        declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
      }
      else {
        Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
        Yh yh = (Yh) yhMap.get(yhdm);
        if (LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),
                        yh.getYhdm())) {
          // ����������������һƱ��˰
          super.log("����������������һƱ��˰");
          declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
        }
        else {
          super.log("����δ������һƱһ˰");
          // ����δ������һƱһ˰
          declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
        }
      }
      /**
       * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� End
       */
      return declareInfor;
    }

    protected void setBankInfo(HttpServletRequest request, ZhsbForm form)
        throws BaseException
    {
        ZhsbWithSfxyForm myForm = (ZhsbWithSfxyForm)form;
        // ��˰�ѻ������Э��
        Sfxy sfxy = FriendHelper.getYhkkSfxy(request);

        // ����������Ϣ
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
package com.ttsoft.bjtax.shenbao.zhsb.web;

import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.shenbao.codetable.web.CodeTableUtil;
import com.ttsoft.bjtax.shenbao.constant.ApplicationConstant;
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
 * ������Э����ۺ��걨form
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����ģ��</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author not attributable
 * @version 1.0
 */
public class ZhsbWithoutSfxyAction
    extends ZhsbAction {
  public ZhsbWithoutSfxyAction() {
  }

  //start modified code by qianchao 2006-2-13
//  protected int getActionID() {
//    return SbzlAccess.ZHSBWITHOUTSFXY;
//  }
  //end   modified code by qianchao 2006-2-13

  protected DeclareInfor setDeclareInforDetail(DeclareInfor declareInfor,
                                               HttpServletRequest request) {
    declareInfor.setIsReturnPaymentInfo(true);
    UserData ud = this.getUserData(request);

    Sbjkzb zb = declareInfor.getSbjkzb();
    zb.setClbjdm(CodeConstant.CLBJDM_YSB);
    String yhdm = zb.getYhdm();
    if (yhdm == null) {
      // �û�û�еǼ�������Ϣ��һƱһ˰
      declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
    }
    else {
      Map yhMap = CodeTableUtil.getCodeTableMap(request, CodeTable.YH_MAP);
      Yh yh = (Yh) yhMap.get(yhdm);
      /**
       * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� Start
       */

//            if(yh.getLwzt() != null && yh.getLwzt().equals("T"))
//            {
//                // ������������һƱ��˰
//                declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
//            }
//            else
//            {
//                // ����δ������һƱһ˰
//                declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
//            }
      if (LWUtil.isLW(request.getSession().getServletContext(), ud.getSsdwdm(),
                      yh.getYhdm())) {
        super.log("������������һƱ��˰");
        // ������������һƱ��˰
        declareInfor.setPrintTag(CodeConstant.PRINT_YPDS_KM);
      }
      else {
        super.log("����δ������һƱһ˰");
        // ����δ������һƱһ˰
        declareInfor.setPrintTag(CodeConstant.PRINT_YPYS);
      }
      /**
       * Modified by Ha Zhengze 20051008 20:59 Ϊ�������������н���һƱ��˰�ĵ��� End
       */
    }

    return declareInfor;
  }

  protected void setBankInfo(HttpServletRequest request, ZhsbForm form) throws
      BaseException {
    Map djInfo = FriendHelper.getDjInfo(request);
    List bankInfo = (List) djInfo.get("YHZH");

    ZhsbWithoutSfxyForm myForm = (ZhsbWithoutSfxyForm) form;
    myForm.setBankInfo(bankInfo);
  }

  protected BankInfo getPaymentBankInfo(HttpServletRequest request,
                                        ZhsbForm form) throws BaseException {
    ZhsbWithoutSfxyForm myForm = (ZhsbWithoutSfxyForm) form;
    String selectedAccountNumber = myForm.getJkyhzh();

    Map djInfo = FriendHelper.getDjInfo(request);
    List bank = (List) djInfo.get("YHZH");

    BankInfo bankInfo = new BankInfo();
    for (int i = 0; i < bank.size(); i++) {
      YHZH yhzh = (YHZH) bank.get(i);
      if (yhzh.getZh().equals(selectedAccountNumber)) {
        bankInfo.accountNumber = selectedAccountNumber;
        bankInfo.bankID = yhzh.getYhdm();
        bankInfo.bankName = yhzh.getKhyhmc();

        break;
      }
    }

    return bankInfo;
  }

}
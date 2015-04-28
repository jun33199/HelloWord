package com.ttsoft.bjtax.shenbao.ejb;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.service.constant.SkhConstant;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.service.processor.PzglInterFaceProcessor;
import com.ttsoft.bjtax.shenbao.service.processor.SkhInterFaceProcessor;
import com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbResult;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.TypeChecker;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: service Ejb</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class ServiceEJB
    implements SessionBean {
  SessionContext sessionContext;
  public void ejbCreate() throws CreateException {
  }

  public void ejbRemove() {
  }

  public void ejbActivate() {
  }

  public void ejbPassivate() {
  }

  public void setSessionContext(SessionContext sessionContext) {
    this.sessionContext = sessionContext;
  }

  /**
   * 根据其它模块传入的申报数据，生成缴款书，并返回缴款书内容
   * @param sbsj 申报数据
   * @return DeclareInfor 缴款书信息
   * @throws BaseException
   */
  public DeclareInfor getJkInforBySbsj(Sbsj sbsj) throws BaseException {
    try {
      return InterFaceProcessor.getJkInforBySbsj(sbsj);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 根据其它模块传入的一批申报数据，生成缴款书，并返回缴款书内容
   * @param sbsj 申报数据
   * @return DeclareInfor 缴款书信息
   * @throws BaseException
   */
  public List getJkInforListBySbsj(Map sbsjMap) throws BaseException {
    try {
      return InterFaceProcessor.getJkInforListBySbsj(sbsjMap);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 根据其它模块传入的申报数据，生成缴款书，并返回缴款书内容
   * @param sbsj 申报数据
   * @return DeclareInfor 缴款书信息
   * @throws BaseException
   */
  public DeclareInfor getJkInforBySbsj(Sbsj sbsj, String yhdm, String yhmc,
                                       String zh) throws BaseException {
    try {
      return InterFaceProcessor.getJkInforBySbsj(sbsj, yhdm, yhmc, zh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 根据处罚模块提交的申报数据，生成缴款书，并返回缴款书数据
   * @param sbsj 申报数据
   * @return DeclareInfor 缴款书信息
   * @throws BaseException
   */
  public DeclareInfor getJksBySbsjForCF(Sbsj sbsj) throws BaseException {
    try {
      return InterFaceProcessor.getJksBySbsjForCF(sbsj);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 取入库数据
   * @param jkpzh 缴款凭证号字符数组
   * @param ywlx 业务类型
   * @return list 缴款数据列表
   * @throws BaseException
   */
  public List getJkDataByJkpzh(String[] jkpzh, int ywlx) throws BaseException {
    try {
      return InterFaceProcessor.getJkDataByJkpzh(jkpzh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 取入库数据
   * @param sphm 税票号码字符数组
   * @param ywlx 业务类型
   * @return list 缴款数据列表
   * @throws BaseException
   */
  public List getJkDataBySphm(String[] sphm, int ywlx) throws BaseException {
    try {
      return InterFaceProcessor.getJkDataBySphm(sphm);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 查询申报缴款数据
   * @param jsjdm 纳税人计算机代码
   * @param jkpzh 缴款凭证号字符数组
   * @param sjly 数据来源
   * @param fsdm 方式代码
   * @param zwbs 帐务标识判断
   * @return List 缴款数据列表
   * @throws BaseException
   */
  public List searchJkData(String jsjdm, String[] jkpzh, String sjly,
                           String fsdm, int zwbs) throws BaseException {
    try {
      return InterFaceProcessor.searchJkData(jsjdm, jkpzh, sjly, fsdm, zwbs);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 回填结报单号
   * @param wszhList 完税证列表
   * @param xspzhList 销售凭证号列表
   * @param jbdh 结报单号
   * @param userData 用户信息
   * @throws BaseException
   */
  public void jiebao(List wszhList, List xspzhList, String jbdh,
                     UserData userData) throws BaseException {
    try {
      PzglInterFaceProcessor.updatejbdhForPZ(wszhList, xspzhList, jbdh,
                                             userData);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 查询结报单对应的完税证是否已入库
   * @param jbdh 结报单号
   * @return true 已入库，false 未入库
   * @throws BaseException
   */
  public boolean cxsfrk(String jbdh) throws BaseException {
    try {
      return PzglInterFaceProcessor.cxsfrk(jbdh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 查询完税征对应的缴款书数据
   * @param wszhArray 完税证号列表
   * @return 缴款书信息列表
   * @throws BaseException
   */
  public List getJkpzhJeByWszh(String[] wszhArray) throws BaseException {
    try {
      return PzglInterFaceProcessor.getJkpzhJeByWszh(wszhArray);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 查询完税征对应的缴款书数据
   * @param wszhArray 完税证号列表
   * @return 缴款书信息列表
   * @throws BaseException
   */
  public List getJkpzhJeByWszhForLw(String[] wszhArray) throws BaseException {
    try {
      return PzglInterFaceProcessor.getJkpzhJeByWszhForLw(wszhArray);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }


  /**
   * 删除缴款凭证数据
   * @param jkpzhList 缴款凭证号列表
   * @throws BaseException
   */
  public void deleteJKS(List jkpzhList) throws BaseException {
    try {
      InterFaceProcessor.deleteJKS(jkpzhList);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 得到缴款凭证号
   * @param jsjdm 计算机代码
   * @return String jkpzh
   * @throws BaseException
   */
  public String getJkpzh(String jsjdm) throws BaseException {
    try {
      return InterFaceProcessor.getJkpzh(jsjdm);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 根据计算机代码和时间获取逾期未入库的申报数据
   * @param jsjdm 计算机代码
   * @param time 时间
   * @return list
   * @throws BaseException
   */
  public List getSbjkyqwrkData(String jsjdm, Date time) throws BaseException {
    try {
      return InterFaceProcessor.getSbjkyqwrkData(jsjdm, time);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取计算机代码对应的当前使用过的最大序号（本月内）
   * @param jsjdm 计算机代码8位
   * @param ny 年月yyyyMM 6位
   * @return String
   * @throws BaseException
   */
  public String getXh(String jsjdm, String ny) throws BaseException {
    try {
      return InterFaceProcessor.getXh(jsjdm, ny);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 根据计算机代码取某次的申报编号
   * @param jsjdm 计算机代码
   * @return String sbbh
   * @throws BaseException
   */
  public String getSbbh(String jsjdm) throws BaseException {
    try {
      return InterFaceProcessor.getSbbh(jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 根据计算机代码取某次的税票号码
   * @param jsjdm 计算机代码
   * @return String sphm
   * @throws BaseException
   */
  public String getSphm(String jsjdm) throws BaseException {
    try {
      return InterFaceProcessor.getSphm(jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取得税种税目对象
   * @param szsmdm 税种税目代码
   * @return 税种税目值对象
   * @throws BaseException
   */
  public Szsm getSzsm(String szsmdm) throws BaseException {
    try {
      return InterFaceProcessor.getSzsm(szsmdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 取得登记注册类型对象
   * @param djzclxdm 登记注册类型代码
   * @return 登记注册类型对象
   * @throws BaseException
   */
  public Djzclx getDjzclx(String djzclxdm) throws BaseException {
    try {
      return InterFaceProcessor.getDjzclx(djzclxdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 票证模块处理印花税用
   * 根据帐户代码查询印花税购买明细数据
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return ArrayList 明细数据列表
   * @throws BaseException
   */
  public ArrayList getYhssj(String zhdm, String jsjdm) throws BaseException {
    try {
      return PzglInterFaceProcessor.getYhssj(zhdm, jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 票证模块处理印花税用
   * 根据帐户代码查询印花税购买明细数据
   * @param zhdm 帐户代码
   * @param jsjdm 计算机代码
   * @return ArrayList 明细数据列表
   * @throws BaseException
   */
  public ArrayList getYhssjForLw(String zhdm, String jsjdm) throws BaseException {
    try {
      return PzglInterFaceProcessor.getYhssjForLw(zhdm, jsjdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 票证模块处理印花税用
   * 修改记帐标识
   * 将已经记录在票证帐中的印花税销售记录的记帐标识由0改为1
   * @param xspzhList 印花税销售凭证号列表
   * @param jsjdm 计算机代码
   * @param lx 数据更新类型 0：更新为未记帐，1：更新为已记帐
   * @throws BaseException
   */
  public void updateJzbs(ArrayList xspzhList, String jsjdm, int lx) throws
      BaseException {
    try {
      PzglInterFaceProcessor.updateJzbs(xspzhList, jsjdm, lx);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 提供给票证的获取入库金额的接口方法
   * @param jkpzh 缴款凭证号
   * @return rkje 入库金额
   * @throws BaseException
   */
  public BigDecimal getRkjeByJkpzhPZ(String jkpzh) throws BaseException {
    try {
      return PzglInterFaceProcessor.getRkjeByJkpzhPZ(jkpzh);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 提供给票证的获取入库金额的接口方法
   * @param jkpzh 缴款凭证号
   * @return rkje 入库金额
   * @throws BaseException
   */
  public BigDecimal getRkjeByJkpzhPZForLw(String jkpzh) throws BaseException {
    try {
      return PzglInterFaceProcessor.getRkjeByJkpzhPZForLw(jkpzh);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }


  /**
   * 获取税款所属日期
   * @param smdm 税目代码
   * @param djzclx 登记注册类型
   * @param rq  当前日期
   * @return Map
   * @throws BaseException
   */
  public Map getZqzzrq(String smdm, String djzclx, String rq) throws
      BaseException {
    try {
      return InterFaceProcessor.getZqzzrq(smdm, djzclx, rq);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * 根据年度字别和完税证号及类型代码，取对应的完税征明细数据
   * @param wszh 完税证号
   * @param nd 年度
   * @param lxdm 类型代码：1－－零散税完税证；2－－个体工商户完税证。
   * @return List
   * @throws BaseException
   */
  public List getWszData(String wszh, String nd, int lxdm) throws BaseException {
    try {
      return InterFaceProcessor.getWszData(wszh, nd, lxdm);
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
  }

  /**
   * add by Daniel 银行申报缴款接口
   * 根据银行传来的一批申报数据，生成缴款书，并返回缴款书内容
   * @throws BaseException
   */
  public List generateBankSBJKS(Map sbsjMap) throws BaseException {
    try {
      return InterFaceProcessor.generateBankSBJKS(sbsjMap);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合
   */
  public List getYpdsJks(String sbbh) throws BaseException {
    try {
      return InterFaceProcessor.getYpdsJks(sbbh);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合
   */
  public List getYpdsJksBySphm(String sphm) throws BaseException {
    try {
      return InterFaceProcessor.getYpdsJksBySphm(sphm);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param JksList 一票一税缴款书集合,该集合必须保证属于同一笔申报
   * @return 一票多税缴款书集合
   */
  public List getYpdsJks(List JksList) throws BaseException {
    try {
      return InterFaceProcessor.getYpdsJks(JksList);
    }
    catch (Exception e) {
      throw ExceptionUtil.getBaseException(e);
    }

  }

  /**
   * 无申报银行缴款根据申报明细生成一票多税缴款书
   * @param yhsbInfo 申报数据
   * @param hjzse 申报合计总应纳税额
   * @return YhsbResult
   * @throws java.lang.Exception 应用异常
   */
  public YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo, double hjzse) throws
      Exception {
    try {
      return SkhInterFaceProcessor.generateYpdsJksWithNoSbInfo(yhsbInfo, hjzse);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("ServercEJB 银行端无申报异常信息: ==== " + e.getMessage());
      return getYhsbResultBizErrorObj(yhsbInfo.getJsjdm(), e.getMessage());
      //throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * 有申报银行缴款根据申报明细生成一票多税缴款书
   * @param yhsbInfo 申报数据
   * @param hjzse 申报合计总应纳税额
   * @return YhsbResult
   * @throws java.lang.Exception 应用异常
   */
  public YhsbResult generateYpdsJksWithSbInfo(YhsbInfo yhsbInfo, double hjzse) throws
      Exception {
    try {
      return SkhInterFaceProcessor.generateYpdsJksWithSbInfo(yhsbInfo, hjzse);
    }
    catch (Exception e) {
      e.printStackTrace();
      return getYhsbResultBizErrorObj(yhsbInfo.getJsjdm(),e.getMessage());
      //throw ExceptionUtil.getBaseException(e);
    }
  }

  public boolean deleteSbjkDataBySphm(String sphm) throws Exception{
    try {
      return SkhInterFaceProcessor.deleteSbjkDataBySphm(sphm);
    }
    catch (Exception e) {
      e.printStackTrace();
      //return getYhsbResultBizErrorObj(yhsbInfo.getJsjdm());
      throw ExceptionUtil.getBaseException(e);
    }
  }


    /**
     * 无申报银行缴款扣款结果
     * @param kkFlag 扣款成功标记，true-扣款成功，false-扣款失败
     * @param ypdsJkss 一票多税缴款书列表，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     * @throws java.lang.Exception 应用异常
     */
    public YhsbResult WsbYhJkKkResult(boolean kkFlag, List ypdsJkss) throws
            Exception {
        try {
            return SkhInterFaceProcessor.WsbYhJkKkResult(kkFlag, ypdsJkss);
        } catch (Exception e) {
            e.printStackTrace();
            return getYhsbResultBizErrorObj(((YPDSJKS) ypdsJkss.get(0)).
                                            getJsjdm(), e.getMessage());
            //throw ExceptionUtil.getBaseException(e);
        }
    }

    public YhsbResult yhJkKkResult(boolean kkFlag, List ypdsJkss) throws
            Exception {
        try {
            return SkhInterFaceProcessor.yhJkKkResult(kkFlag, ypdsJkss);
        } catch (Exception e) {
            e.printStackTrace();
            return getYhsbResultBizErrorObj(((YPDSJKS) ypdsJkss.get(0)).
                                            getJsjdm(),
                                            e.getMessage());
        }
    }

    public YhsbResult yhkkBySbbh(String sbbh, String sphm, String jsjdm,
                                 String jksj) throws Exception {
        try {
            return SkhInterFaceProcessor.yhkkBySbbh(sbbh, sphm, jsjdm, jksj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

  /**
   * 有申报银行缴款根据申报明细生成一票多税缴款书,可选税种
   * @param yhsbInfo 申报数据,应当包括选择的所有税种税目信息
   * @param hjzse 申报合计总应纳税额
   * @return 一票多税缴款书，内部成员为com.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   * @throws java.lang.Exception 应用异常
   */
  public YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(YhsbInfo yhsbInfo,
      double hjzse) throws
      Exception {
    try {
      return SkhInterFaceProcessor.generateYpdsJksWithSbInfoAndSzAlterable(
          yhsbInfo, hjzse);
    }
    catch (Exception e) {
      e.printStackTrace();
      return getYhsbResultBizErrorObj(yhsbInfo.getJsjdm(), e.getMessage());
      //throw ExceptionUtil.getBaseException(e);
    }
  }

  private YhsbResult getYhsbResultBizErrorObj(String jsjdm,String message) throws Exception {
    //
    String swjgzzjgdm = "";
    String qxdm = "";
    String resultDescription = message;
    //
    
    //TypeChecker tc = new TypeChecker();
    String flag = TypeChecker.isQyyh(jsjdm);

    //
    try
    {
        if (SkhConstant.JSJDM_LX_QY.equals(flag)) { //企业用户
            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(jsjdm);
            swjgzzjgdm = jbsj.getSwjgzzjgdm();
            qxdm = jbsj.getQxdm();
          }
          else if (SkhConstant.JSJDM_LX_ZRR.equals(flag)) { //自然人用
            ZRRJBSJ zrrJbsj = FriendHelper.getZrrjbsj(jsjdm);
            swjgzzjgdm = zrrJbsj.getSwjgzzjgdm();
            qxdm = zrrJbsj.getQxdm();
          }
          else {
          	
          		  resultDescription = "该纳税人无法判定登记类型！"+jsjdm;
          }
    	
    }
    catch (Exception e)
    {
    	e.printStackTrace();
    	resultDescription = e.getMessage();
    }

    //
    YhsbResult yhsbResult = new YhsbResult();
    yhsbResult.setJsjdm(jsjdm);
    yhsbResult.setSwjgzzjgdm(swjgzzjgdm);
    yhsbResult.setQxdm(qxdm);
    yhsbResult.setResultCode(SkhConstant.RESULT_CODE_PREFIX_ERROR +
                             SkhConstant.RESULT_CODE_MID_DEFAULT +
                             SkhConstant.RESULT_CODE_POSTFIX_ERROR_EJB);
    yhsbResult.setResultDescription(resultDescription);
    return yhsbResult;
  }
  
	/**
	 * @desc 根据收入规划核算处要求，重新查询预算级次名称，预算级次显示为中央级、市级、区级
	 * @author gaoyh
	 * @date 20131219
	 * @param yskmdm
	 * @param szsmdm
	 * @param swjgzzjgdm
	 * @return String
	 * @throws BaseException
	 */
  public String getYskmFcblmc(String tmpYskmdm, String tmpSzsmdm, String tmpSwjgzzjgdm) throws BaseException {
	  
	  try {
		  return InterFaceProcessor.getYskmFcblmc(tmpYskmdm, tmpSzsmdm, tmpSwjgzzjgdm);
	  } catch (Exception e) {
		  throw ExceptionUtil.getBaseException(e);
	  }
  }


}
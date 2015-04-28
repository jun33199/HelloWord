package com.ttsoft.bjtax.shenbao.ejb;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Sbsj;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbInfo;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.shenbao.service.vo.YhsbResult;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: service 接口</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public interface Service
    extends javax.ejb.EJBObject {
  public DeclareInfor getJkInforBySbsj(Sbsj sbsj) throws BaseException,
      RemoteException;

  public List getJkInforListBySbsj(Map sbsjMap) throws BaseException,
      RemoteException;

  public DeclareInfor getJkInforBySbsj(Sbsj sbsj, String yhdm, String yhmc,
                                       String zh) throws BaseException,
      RemoteException;

  public List getJkDataByJkpzh(String[] jkpzh, int ywlx) throws BaseException,
      RemoteException;

  public List getJkDataBySphm(String[] sphm, int ywlx) throws BaseException,
      RemoteException;

  public void jiebao(List wszhList, List xspzhList, String jbdh,
                     UserData userData) throws BaseException, RemoteException;

  public boolean cxsfrk(String jbdh) throws BaseException, RemoteException;

  public List getJkpzhJeByWszh(String[] wszhArray) throws BaseException,
      RemoteException;

  public List getJkpzhJeByWszhForLw(String[] wszhArray) throws BaseException,
      RemoteException;

  public void deleteJKS(List jkpzhList) throws BaseException, RemoteException;

  public String getJkpzh(String jsjdm) throws BaseException, RemoteException;

  public List getSbjkyqwrkData(String jsjdm, Date time) throws BaseException,
      RemoteException;

  public String getXh(String jsjdm, String ny) throws BaseException,
      RemoteException;

  public String getSbbh(String jsjdm) throws BaseException, RemoteException;

  public String getSphm(String jsjdm) throws BaseException, RemoteException;

  public Szsm getSzsm(String szsmdm) throws BaseException, RemoteException;

  public Djzclx getDjzclx(String djzclxdm) throws BaseException,
      RemoteException;

  public ArrayList getYhssj(String zhdm, String jsjdm) throws BaseException,
      RemoteException;

  public ArrayList getYhssjForLw(String zhdm, String jsjdm) throws BaseException,
      RemoteException;

  public void updateJzbs(ArrayList xspzhList, String jsjdm, int lx) throws
      BaseException, RemoteException;

  public DeclareInfor getJksBySbsjForCF(Sbsj sbsj) throws BaseException,
      RemoteException;

  public BigDecimal getRkjeByJkpzhPZ(String jkpzh) throws BaseException,
      RemoteException;

  public BigDecimal getRkjeByJkpzhPZForLw(String jkpzh) throws BaseException,
      RemoteException;

  public Map getZqzzrq(String smdm, String djzclx, String rq) throws
      BaseException, RemoteException;

  public List getWszData(String wszh, String nd, int lxdm) throws BaseException,
      RemoteException;

  public List searchJkData(String jsjdm, String[] jkpzh, String sjly,
                           String fsdm, int zwbs) throws BaseException,
      RemoteException;

  public List getYpdsJks(String sbbh) throws Exception;

  public List getYpdsJksBySphm(String sphm) throws Exception;

  public List getYpdsJks(List JksList) throws Exception;

  public YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo, double hjzse) throws
      Exception;

  public YhsbResult generateYpdsJksWithSbInfo(YhsbInfo yhsbInfo, double hjzse) throws
      Exception;

  public YhsbResult WsbYhJkKkResult(boolean kkFlag, List ypdsJkss) throws Exception;

  public YhsbResult yhJkKkResult(boolean kkFlag, List ypdsJkss) throws Exception;

  public YhsbResult yhkkBySbbh(String sbbh, String sphm, String jsjdm, String jksj) throws Exception;

  public YhsbResult generateYpdsJksWithSbInfoAndSzAlterable(YhsbInfo yhsbInfo,
      double hjzse) throws Exception;

  public boolean deleteSbjkDataBySphm(String sphm) throws Exception;

  /**
   * add by Daniel 银行申报缴款接口
   * @param sbsjMap
   * @return
   * @throws BaseException
   * @throws RemoteException
   */
  public List generateBankSBJKS(Map sbsjMap) throws BaseException,
      RemoteException;
  
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
  public String getYskmFcblmc(String tmpYskmdm, String tmpSzsmdm, String tmpSwjgzzjgdm) throws BaseException, RemoteException;;

}
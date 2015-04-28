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
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: service Ejb</p>
 * <p>Copyright: (C) 2003-2004 �廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @author ����һ�飭�����
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
   * ��������ģ�鴫����걨���ݣ����ɽɿ��飬�����ؽɿ�������
   * @param sbsj �걨����
   * @return DeclareInfor �ɿ�����Ϣ
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
   * ��������ģ�鴫���һ���걨���ݣ����ɽɿ��飬�����ؽɿ�������
   * @param sbsj �걨����
   * @return DeclareInfor �ɿ�����Ϣ
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
   * ��������ģ�鴫����걨���ݣ����ɽɿ��飬�����ؽɿ�������
   * @param sbsj �걨����
   * @return DeclareInfor �ɿ�����Ϣ
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
   * ���ݴ���ģ���ύ���걨���ݣ����ɽɿ��飬�����ؽɿ�������
   * @param sbsj �걨����
   * @return DeclareInfor �ɿ�����Ϣ
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
   * ȡ�������
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @param ywlx ҵ������
   * @return list �ɿ������б�
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
   * ȡ�������
   * @param sphm ˰Ʊ�����ַ�����
   * @param ywlx ҵ������
   * @return list �ɿ������б�
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
   * ��ѯ�걨�ɿ�����
   * @param jsjdm ��˰�˼��������
   * @param jkpzh �ɿ�ƾ֤���ַ�����
   * @param sjly ������Դ
   * @param fsdm ��ʽ����
   * @param zwbs �����ʶ�ж�
   * @return List �ɿ������б�
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
   * ����ᱨ����
   * @param wszhList ��˰֤�б�
   * @param xspzhList ����ƾ֤���б�
   * @param jbdh �ᱨ����
   * @param userData �û���Ϣ
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
   * ��ѯ�ᱨ����Ӧ����˰֤�Ƿ������
   * @param jbdh �ᱨ����
   * @return true ����⣬false δ���
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
   * ��ѯ��˰����Ӧ�Ľɿ�������
   * @param wszhArray ��˰֤���б�
   * @return �ɿ�����Ϣ�б�
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
   * ��ѯ��˰����Ӧ�Ľɿ�������
   * @param wszhArray ��˰֤���б�
   * @return �ɿ�����Ϣ�б�
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
   * ɾ���ɿ�ƾ֤����
   * @param jkpzhList �ɿ�ƾ֤���б�
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
   * �õ��ɿ�ƾ֤��
   * @param jsjdm ���������
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
   * ���ݼ���������ʱ���ȡ����δ�����걨����
   * @param jsjdm ���������
   * @param time ʱ��
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
   * ȡ����������Ӧ�ĵ�ǰʹ�ù��������ţ������ڣ�
   * @param jsjdm ���������8λ
   * @param ny ����yyyyMM 6λ
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
   * ���ݼ��������ȡĳ�ε��걨���
   * @param jsjdm ���������
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
   * ���ݼ��������ȡĳ�ε�˰Ʊ����
   * @param jsjdm ���������
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
   * ȡ��˰��˰Ŀ����
   * @param szsmdm ˰��˰Ŀ����
   * @return ˰��˰Ŀֵ����
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
   * ȡ�õǼ�ע�����Ͷ���
   * @param djzclxdm �Ǽ�ע�����ʹ���
   * @return �Ǽ�ע�����Ͷ���
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
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �����ʻ������ѯӡ��˰������ϸ����
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ArrayList ��ϸ�����б�
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
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �����ʻ������ѯӡ��˰������ϸ����
   * @param zhdm �ʻ�����
   * @param jsjdm ���������
   * @return ArrayList ��ϸ�����б�
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
   * Ʊ֤ģ�鴦��ӡ��˰��
   * �޸ļ��ʱ�ʶ
   * ���Ѿ���¼��Ʊ֤���е�ӡ��˰���ۼ�¼�ļ��ʱ�ʶ��0��Ϊ1
   * @param xspzhList ӡ��˰����ƾ֤���б�
   * @param jsjdm ���������
   * @param lx ���ݸ������� 0������Ϊδ���ʣ�1������Ϊ�Ѽ���
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
   * �ṩ��Ʊ֤�Ļ�ȡ�����Ľӿڷ���
   * @param jkpzh �ɿ�ƾ֤��
   * @return rkje �����
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
   * �ṩ��Ʊ֤�Ļ�ȡ�����Ľӿڷ���
   * @param jkpzh �ɿ�ƾ֤��
   * @return rkje �����
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
   * ��ȡ˰����������
   * @param smdm ˰Ŀ����
   * @param djzclx �Ǽ�ע������
   * @param rq  ��ǰ����
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
   * ��������ֱ����˰֤�ż����ʹ��룬ȡ��Ӧ����˰����ϸ����
   * @param wszh ��˰֤��
   * @param nd ���
   * @param lxdm ���ʹ��룺1������ɢ˰��˰֤��2�������幤�̻���˰֤��
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
   * add by Daniel �����걨�ɿ�ӿ�
   * �������д�����һ���걨���ݣ����ɽɿ��飬�����ؽɿ�������
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
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯��
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
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param sbbh �걨���
   * @return һƱ��˰�ɿ��鼯��
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
   * �����걨��Ż�ȡһƱ��˰�Ľɿ���
   * @param JksList һƱһ˰�ɿ��鼯��,�ü��ϱ��뱣֤����ͬһ���걨
   * @return һƱ��˰�ɿ��鼯��
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
   * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���
   * @param yhsbInfo �걨����
   * @param hjzse �걨�ϼ���Ӧ��˰��
   * @return YhsbResult
   * @throws java.lang.Exception Ӧ���쳣
   */
  public YhsbResult generateYpdsJksWithNoSbInfo(YhsbInfo yhsbInfo, double hjzse) throws
      Exception {
    try {
      return SkhInterFaceProcessor.generateYpdsJksWithNoSbInfo(yhsbInfo, hjzse);
    }
    catch (Exception e) {
      e.printStackTrace();
      System.out.println("ServercEJB ���ж����걨�쳣��Ϣ: ==== " + e.getMessage());
      return getYhsbResultBizErrorObj(yhsbInfo.getJsjdm(), e.getMessage());
      //throw ExceptionUtil.getBaseException(e);
    }
  }

  /**
   * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���
   * @param yhsbInfo �걨����
   * @param hjzse �걨�ϼ���Ӧ��˰��
   * @return YhsbResult
   * @throws java.lang.Exception Ӧ���쳣
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
     * ���걨���нɿ�ۿ���
     * @param kkFlag �ۿ�ɹ���ǣ�true-�ۿ�ɹ���false-�ۿ�ʧ��
     * @param ypdsJkss һƱ��˰�ɿ����б��ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
     * @throws java.lang.Exception Ӧ���쳣
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
   * ���걨���нɿ�����걨��ϸ����һƱ��˰�ɿ���,��ѡ˰��
   * @param yhsbInfo �걨����,Ӧ������ѡ�������˰��˰Ŀ��Ϣ
   * @param hjzse �걨�ϼ���Ӧ��˰��
   * @return һƱ��˰�ɿ��飬�ڲ���ԱΪcom.ttsoft.bjtax.shenbao.service.vo.YPDSJKS
   * @throws java.lang.Exception Ӧ���쳣
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
        if (SkhConstant.JSJDM_LX_QY.equals(flag)) { //��ҵ�û�
            SWDJJBSJ jbsj = FriendHelper.getSWDJJBSJ(jsjdm);
            swjgzzjgdm = jbsj.getSwjgzzjgdm();
            qxdm = jbsj.getQxdm();
          }
          else if (SkhConstant.JSJDM_LX_ZRR.equals(flag)) { //��Ȼ����
            ZRRJBSJ zrrJbsj = FriendHelper.getZrrjbsj(jsjdm);
            swjgzzjgdm = zrrJbsj.getSwjgzzjgdm();
            qxdm = zrrJbsj.getQxdm();
          }
          else {
          	
          		  resultDescription = "����˰���޷��ж��Ǽ����ͣ�"+jsjdm;
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
	 * @desc ��������滮���㴦Ҫ�����²�ѯԤ�㼶�����ƣ�Ԥ�㼶����ʾΪ���뼶���м�������
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
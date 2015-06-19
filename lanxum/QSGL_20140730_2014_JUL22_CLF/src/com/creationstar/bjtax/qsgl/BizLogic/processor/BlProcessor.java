package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdjmmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Hdtzs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszhz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class BlProcessor extends CommonProcessor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.BL_QUERY_JKS:
            result = doQueryJks(vo); //��ѯ��¼���걨�ɿ���
            break;

        case ActionType.BL_CHECK_SBJKS:
            result = doCheckSbJks(vo); //ͨ���걨��ţ��ͽɿ�ƾ֤�ţ��˶����ߵ�Ӧ��˰���Ƿ�һ��
            break;

        case ActionType.BL_CHECK_HZJKS:
            result = doCheckHzJks(vo); //ͨ���걨��ţ��ͽɿ�ƾ֤�ţ��˶����ߵ�Ӧ��˰���Ƿ�һ��
            break;

        case ActionType.BL_CREATECONNECT_SBJKS:
            result = doCreateConnectSbJks(vo);
            break;

        case ActionType.BL_CREATECONNECT_HZJKS:
            result = doCreateConnectHzJks(vo);
            break;

        case ActionType.GET: //������¼ʱ�����û�����Ľɿ�ƾ֤�ŵõ��ɿ��������
            result = doGetJks(vo);
            break;

        case ActionType.BL_REMOVECONNECT: //������¼��Ҳ���ǶϿ�����
            removeConnect(vo);
            break;

        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }


    /**
     * ��ѯ��¼�Ľɿ��飬����¼�Ľɿ������ArrayList�з��ظ�ǰ̨
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryJks(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO��ѯ���صĽ����
        QueryBlJksBo qbo = (QueryBlJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            StringBuffer baseCondition = new StringBuffer("");
            baseCondition.append(" AND a.jsjdm = '").append(jsjdm).append("' ")
                    .append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM)
                    .append("' AND a.SWJGZZJGDM = '").append(ud.ssdwdm).append(
                    "' ");
            //�õ�ǰ̨ҳ��Ĳ�ѯ������jsje�����˰��sjse����ʵ��˰��
            String sjse = qbo.getSjse();
            String jsje = qbo.getJsje();

            StringBuffer condition = baseCondition;
            //���û�����Ĳ�ѯ�������뵽where�����
            if (sjse != null && !sjse.equals("")) {
                condition.append(" AND b.sjse = ").append(sjse);
            }
            if (jsje != null && !jsje.equals("")) {
                condition.append(" AND b.jsje = ").append(jsje);
            }

            //

            //���걨�ɿ������в�ѯ�ɿ���
            DAOFactory.getInstance().getBlQueryJksDAO().queryJks(
                    condition, resultList, conn);
            //�ڵ��ʵı��в�ѯ�ɿ���
            //����û�û�������ѯ����
            if ((sjse == null || sjse.equals(""))) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryDzJks(
                        baseCondition, resultList, conn);
            } else {
                DAOFactory.getInstance().getBlQueryJksDAO().queryDzJks(
                        baseCondition, sjse, resultList, conn);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨��¼��BlProcessor����ѯ��¼�ɿ����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //�����걨�ɿ�������걨�ɿ���ϸ���е�������1�Զ�������Ϊ��ʹ�÷��㣬��Ҫ������֯
        //��ѯ�����1���������ݶ�Ӧ�����ӱ����ݵ��������bo���������ݷ���һ������vo�У���Ӧ���ӱ����ݷ���ArrayList��
        //���´�����������֯��ѯ�����ݣ�����֯�õ�bo�ŵ�ArrayList���淵��

        ArrayList returnList = new ArrayList(); //������֯���ݺ󣬷��ظ�ǰ̨�Ľ����
        ArrayList tempList = new ArrayList(); //��ʱ��Ų�ѯ���bo�е���ϸvo
        QueryBlJksBo resultBo = new QueryBlJksBo(); //���շ��ظ�ǰ̨�Ĳ�ѯ������е�ÿһ����¼
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            QueryBlJksBo bo = new QueryBlJksBo();
            bo = (QueryBlJksBo) iter.next();

            if (tempList.isEmpty()) { //������ظ�ǰ̨�Ĳ�ѯ���Ϊ�գ�˵���ǵ�һ������
                resultBo = bo;
                //resultBo.setSbjkzb(bo.getSbjkzb());
                //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                if (bo.getSbjkmx().getKssl() == null) {
                    resultBo.getSbjkzb().setKssl(0);
                } else {
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                }
                //��ȫ�ɿ�������ݣ���Ҫ����Щ����
                getSbjkzbInfo(resultBo.getSbjkzb(), ud, conn);
                getSzsmmc(bo.getSbjkmx()); //�õ���Ŀ����
                tempList.add(bo.getSbjkmx()); //�ڲ�ѯ���bo�м�����Ӧ���ӱ�vo
                resultBo.setMxList(tempList); //������ѯ���bo�д洢�ӱ�����ArrayList

                returnList.add(resultBo); //��������֯�Ĳ�ѯ����ŵ����ظ�ǰ̨��ArrayList��
            } else {
                //���������ѯ����Ľɿ�ƾ֤�š������������������ͬ
                //˵������������ֻͬ����ϸ��ͬ��Ҳ����1���������ݶ�Ӧ������ϸ�����ݵ����
                //��Ѹ�������ϸ���ݷŵ���ͬ��resultBo����ϸArrayList��
                if (bo.getSbjkzb().getJkpzh().equals(resultBo.getSbjkzb().
                        getJkpzh()) &&
                    bo.getSbjkzb().getJsjdm().equals(resultBo.getSbjkzb().
                        getJsjdm())) {
                    getSzsmmc(bo.getSbjkmx()); //�õ���Ŀ����
                    tempList.add(bo.getSbjkmx());
                    //���걨�ɿ���ϸ���еĿ�˰������ӣ������걨�ɿ�����vo�������ѯҳ����ʾ��
                    int kssl = 0;
                    if (bo.getSbjkmx().getKssl() != null) {
                        kssl = bo.getSbjkmx().getKssl().intValue();
                    }
                    kssl = resultBo.getSbjkzb().getKssl() +
                           kssl;
                    resultBo.getSbjkzb().setKssl(kssl);
                    resultBo.setMxList(tempList);
                } else {
                    //��Ϊ��һ����ͬ�����ݣ��������¹���resultBo��tempList
                    resultBo = new QueryBlJksBo();
                    tempList = new ArrayList();

                    resultBo = bo;
                    //resultBo.setSbjkzb(bo.getSbjkzb());
                    //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                    if (bo.getSbjkmx().getKssl() == null) {
                        resultBo.getSbjkzb().setKssl(0);
                    } else {
                        resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                intValue());
                    }

                    //��ȫ�ɿ�������ݣ���Ҫ����Щ����
                    getSbjkzbInfo(resultBo.getSbjkzb(), ud, conn);
                    getSzsmmc(bo.getSbjkmx()); //�õ���Ŀ����
                    tempList.add(bo.getSbjkmx());
                    resultBo.setMxList(tempList);

                    returnList.add(resultBo); //��������֯�Ĳ�ѯ����ŵ����ظ�ǰ̨��ArrayList��
                }
            }
        }

        return returnList;
    }


    private Object doGetJks(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO��ѯ���صĽ����
        UserData ud = vo.getUserData();

        QueryBlJksBo queryBo = (QueryBlJksBo) vo.getData();
        String jkpzh = queryBo.getJkpzh();
        String type = queryBo.getType();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            StringBuffer condition = new StringBuffer("");
            condition.append(" AND a.jkpzh = '").append(jkpzh).append("' ")
                    .append(" AND a.jsjdm = '").append(jsjdm).append("' ")
                    .append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM)
//                .append("' ").append(" AND a.qxdm = '")   //modified by zhaobo 20050630
//                .append(CommonUtil.getQxdm(ud)).append("' ")
                    .append("' ")
                    .append(" AND a.SWJGZZJGDM = '").append(ud.ssdwdm).append(
                    "' ");
            //

            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryBlJks(
                        condition, resultList, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().queryBlDzJks(
                        condition, resultList, conn);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨��¼��BlProcessor����ѯ��¼�ɿ����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //�����걨�ɿ�������걨�ɿ���ϸ���е�������1�Զ�������Ϊ��ʹ�÷��㣬��Ҫ������֯
        //��ѯ�����1���������ݶ�Ӧ�����ӱ����ݵ��������bo���������ݷ���һ������vo�У���Ӧ���ӱ����ݷ���ArrayList��
        //���´�����������֯��ѯ�����ݣ�����֯�õ�bo�ŵ�ArrayList���淵��
        if (resultList.isEmpty()) {
            return null;
        }

        ArrayList tempList = new ArrayList(); //��ʱ��Ų�ѯ���bo�е���ϸvo
        QueryBlJksBo resultBo = new QueryBlJksBo(); //���շ��ظ�ǰ̨�Ĳ�ѯ������е�ÿһ����¼
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            QueryBlJksBo bo = new QueryBlJksBo();
            bo = (QueryBlJksBo) iter.next();

            if (tempList.isEmpty()) { //������ظ�ǰ̨�Ĳ�ѯ���Ϊ�գ�˵���ǵ�һ������
                resultBo = bo;
                //resultBo.setSbjkzb(bo.getSbjkzb());
                //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().intValue());
                tempList.add(bo.getSbjkmx()); //�ڲ�ѯ���bo�м�����Ӧ���ӱ�vo
                resultBo.setMxList(tempList); //������ѯ���bo�д洢�ӱ�����ArrayList
            } else {
                //���������ѯ����Ľɿ�ƾ֤�š������������������ͬ
                //˵������������ֻͬ����ϸ��ͬ��Ҳ����1���������ݶ�Ӧ������ϸ�����ݵ����
                //��Ѹ�������ϸ���ݷŵ���ͬ��resultBo����ϸArrayList��
                if (bo.getSbjkzb().getJkpzh().equals(resultBo.getSbjkzb().
                        getJkpzh()) &&
                    bo.getSbjkzb().getJsjdm().equals(resultBo.getSbjkzb().
                        getJsjdm())) {
                    tempList.add(bo.getSbjkmx());
                    //���걨�ɿ���ϸ���еĿ�˰������ӣ������걨�ɿ�����vo�������ѯҳ����ʾ��
                    int kssl = resultBo.getSbjkzb().getKssl() +
                               bo.getSbjkmx().getKssl().intValue();
                    resultBo.getSbjkzb().setKssl(kssl);
                    resultBo.setMxList(tempList);
                } else {
                    //��Ϊ��һ����ͬ�����ݣ��������¹���resultBo��tempList
                    resultBo = new QueryBlJksBo();
                    tempList = new ArrayList();

                    resultBo = bo;
                    //resultBo.setSbjkzb(bo.getSbjkzb());
                    //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                    tempList.add(bo.getSbjkmx());
                    resultBo.setMxList(tempList);
                }
            }
        }
        return resultBo;
    }


    /**
     * ����ǰ̨���������걨��źͽɿ�ƾ֤�ţ��ȶ����ߵ�Ӧ��˰���Ƿ�һ�£�һ�·���true
     * @param vo VOPackage
     * @return Boolean
     * @throws BaseException
     */
    private Boolean doCheckSbJks(VOPackage vo) throws BaseException {
        Boolean legal = new Boolean(false);

        QueryBlJksBo bo = (QueryBlJksBo) vo.getData();
        UserData ud = vo.getUserData();

        String jkpzh = bo.getJkpzh();
        String sbbh = bo.getSbbh();
        String type = bo.getType();
        String zbxh = bo.getZbxh();

        Connection conn = null;
        try {
            if (sbbh.charAt(0) == 'B' || sbbh.charAt(0) == 'b') {
                throw new ApplicationException("�걨��" + sbbh +
                                               "���ڲ������걨�����ܽ��в�¼������");
            }

            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //ͨ�������걨������ֵ�걨��ţ�����������걨��������
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            if (sbzb.getSbbh() == null || sbzb.getSbbh().equals("")) {
                throw new ApplicationException("��������걨��" + sbbh + "�����ݿ��в����ڣ�");
            }

            if (Constants.ZB_ZTBS_ZF.equals(sbzb.getZtbs())) {
                throw new ApplicationException("��������걨��" + sbbh + "�����ϣ�");
            }

            //һ��Ҫ�ų��Ѿ����ɹ��ɿ����
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("�ñ��걨�Ѿ���ӡ�ɿ����ˣ����ܹ��ٴ����ɽɿ��飡");
            }

            //�ȶ��걨���Ȩ��ת�����ʹ���ͽɿ����˰��˰Ŀ�����Ƿ�һ��
            ArrayList sbjkmxList = null;
            if (Constants.PT_JKS.equals(type)) {
                sbjkmxList = doGetSbjkmx(jkpzh, jsjdm, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                sbjkmxList = doGetDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
            }
            if (sbjkmxList.isEmpty()) {
                throw new ApplicationException("û���ҵ��ɿ�ƾ֤��Ϊ��" + jkpzh +
                                               "�Ľɿ��飡");
            }
            //�õ��걨��Ȩ��ת�����Ͷ�Ӧ��˰��˰Ŀ����
            //���ȵõ����ط����걨�Ļ�����Ϣ
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            if (tfxx == null) {
                throw new ApplicationException("û���ҵ��걨��" + sbbh +
                                               "����Ӧ�����ط��ݻ�����Ϣ��");
            }
            //ͨ��Ȩ��ת�����ʹ���õ�˰��˰Ŀ����
            String szsmdm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn).
                            getSzsmdm();
            //�õ��ɿ�����ϸ���б����˰��˰Ŀ����
            String jks_szsmdm = ((Sbjkmx) sbjkmxList.get(0)).getSzsmdm();
            if (!jks_szsmdm.equals(szsmdm)) {
                throw new ApplicationException("�걨��" + sbbh +
                                               "����Ӧ��˰��˰Ŀ��ɿ���" + jkpzh +
                                               "�����˰��˰Ŀ�������");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            //�����걨��������ݣ����ʵ�ɽ��
            double sb_ynse = hdbo.getYnse().doubleValue();
            //���ݿ�����ɿ����д洢��ʵ��˰��
            double jks_ynse = 0.0d;

            Sbjkzb sbjkzb = new Sbjkzb();
            sbjkzb.setJkpzh(jkpzh);
            sbjkzb.setJsjdm(jsjdm);
            //�������ͨ�ɿ��飬��ȥ�걨�ɿ����������
            if (Constants.PT_JKS.equals(type)) {
                sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                        sbjkzb, conn);
                if (sbjkzb != null) {
                    jks_ynse = ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }

                //����ǵ��ʵĽɿ��飬��ȥ���ʵ������
            } else if (Constants.DZ_JKS.equals(type)) {
                ArrayList dzList = (ArrayList) DAOFactory.getInstance().
                                   getBlQueryJksDAO().getDzJks(
                                           sbjkzb, conn);
                for (Iterator iter = dzList.iterator(); iter.hasNext(); ) {
                    sbjkzb = (Sbjkzb) iter.next();
                    jks_ynse += ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }
            }

            //���������
            if (sb_ynse == jks_ynse) {
                legal = Boolean.TRUE;
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨��¼��BlProcessor����¼�걨�ɿ����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return legal;
    }

    /**
     * ����ǰ̨�������Ĵ���걨��ŵ�ArrayList�ͽɿ�ƾ֤�ţ��ȶ����ߵ�Ӧ��˰���Ƿ�һ�£�һ�·���true
     * @param vo VOPackage
     * @return Boolean
     * @throws BaseException
     */
    private Boolean doCheckHzJks(VOPackage vo) throws BaseException {
        Boolean legal = new Boolean(false);
        UserData ud = vo.getUserData();

        ArrayList blList = (ArrayList) vo.getData();
        String jkpzh = ((BlJksBo) blList.get(0)).getJkpzh(); //ǰ̨�������Ľɿ�ƾ֤�ţ��������ɵģ�������������sbbh��Ӧ
        String type = ((BlJksBo) blList.get(0)).getType(); //�ɿ�������ͣ���ͨ�Ļ��ǵ��ʵ�

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //���ȶ��û�������걨��ź���˰֤�ŵĹ�ϵ����У��
            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                BlJksBo bo = (BlJksBo) iter.next();
                //�û��������˰֤����
                String userWszh = bo.getWszh();
                String userNdzb = bo.getNdzb();
                String userPzzldm = bo.getPzzldm();

                //�����û�������걨��ŵõ��걨�������
                String sbbh = bo.getSbbh();
                if (sbbh.charAt(0) == 'B' || sbbh.charAt(0) == 'b') {
                    throw new ApplicationException("��������걨��" + sbbh +
                            "���ڲ������걨�����ܽ��в�¼������");
                }
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(sbbh);
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);

                if (sbzb.getSbbh() == null || sbzb.getSbbh().equals("")) {
                    throw new ApplicationException("��������걨��" + sbbh +
                            "�����ݿ��в����ڣ�");
                }

                if (Constants.ZB_ZTBS_ZF.equals(sbzb.getZtbs())) {
                    throw new ApplicationException("��������걨��" + sbbh + "�����ϣ�");
                }

                //�ж��Ƿ���걨���Ƿ��Ѿ���������˰֤
                //����Ѿ���������˰֤���жϸ���˰֤�����û�¼�����˰֤���Ƿ�һ��
                if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                    StringBuffer condition = new StringBuffer(" sbbh = '");
                    condition.append(sbbh).append("' ");
                    Qswszz qswszz = (Qswszz) DAOFactory.getInstance().
                                    getBlQueryJksDAO()
                                    .getQswszz(condition.toString(), conn);
                    //����û��������˰֤�������ݿ��еĲ���
                    if (qswszz == null || !userWszh.equals(qswszz.getWszh())) {
                        throw new ApplicationException("�걨���Ϊ" + sbbh +
                                "���걨�������ݿ��ж�Ӧ����˰֤�������������˰֤��" + userWszh + "����!");
                    }
                    //�����˰֤�Ĵ����Ǵ��������걨�������Ϊ���걨��ֻ�����걨��״̬���ܻ���
                    if (Constants.WSZ_CLBJDM_YSB.equals(qswszz.getClbjdm())) {
                        StringBuffer sql = new StringBuffer(
                                "UPDATE sbdb.sb_jl_qswszz a ");
                        sql.append(" SET a.clbjdm = '").append(Constants.
                                WSZ_CLBJDM_YWS)
                                .append("' WHERE a.wszh = '").append(qswszz.
                                getWszh())
                                .append("' AND ndzb = '").append(qswszz.getNdzb())
                                .append("' AND pzzldm = '").append(qswszz.
                                getPzzldm())
                                .append("' ");
                        DAOFactory.getInstance().getQswszzDAO().update(sql.
                                toString(), conn);
                    }
                } else { //���û��������˰֤�����ж��û�¼�����˰֤���Ƿ������ݿ����Ѿ�������
                    StringBuffer condition = new StringBuffer(" wszh = '");
                    condition.append(userWszh).append("' AND ndzb = '")
                            .append(userNdzb).append("' AND pzzldm = '")
                            .append(userPzzldm).append("' ");
                    //����û��������˰֤�������ݿ����Ѵ���
                    Qswszz qswszz = (Qswszz) DAOFactory.getInstance().
                                    getBlQueryJksDAO()
                                    .getQswszz(condition.toString(), conn);
                    if (qswszz != null) {
                        throw new ApplicationException("�����������˰֤��" + userWszh +
                                "�����ݿ����Ѵ���");
                    }
                }
            }

            double total_ynse = 0.0d; //�걨���Ӧ��˰����ܺ�
            double jks_ynse = 0.0d; //���ݿ��нɿ���洢��ʵ��˰��
            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                BlJksBo bo = (BlJksBo) iter.next();
                String sbbh = bo.getSbbh();

                JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
                //�����걨��������ݣ����ʵ�ɽ��
                double sb_ynse = hdbo.getYnse().doubleValue();
                total_ynse += sb_ynse;
            }

            Sbjkzb sbjkzb = new Sbjkzb();
            sbjkzb.setJkpzh(jkpzh);
            sbjkzb.setJsjdm(jsjdm);
            //�������ͨ�ɿ���1����ȥ�걨�ɿ����������
            //����ǵ��ʽɿ���2����ȥ�걨�ɿ����������
            Debug.out("jks type==" + type + " jkpzh==" + jkpzh + " jsjdm==" +
                      jsjdm);

            if (Constants.PT_JKS.equals(type)) {
                sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                        sbjkzb, conn);
                if (sbjkzb != null) {
                    jks_ynse = ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                } else {
                    throw new ApplicationException("û���ҵ��ɿ�ƾ֤��Ϊ��" + jkpzh +
                            "�Ľɿ��飡");
                }
                //����ǵ��ʵĽɿ��飬��ȥ���ʵ������
            } else if (Constants.DZ_JKS.equals(type)) {
                ArrayList dzList = (ArrayList) DAOFactory.getInstance().
                                   getBlQueryJksDAO().getDzJks(
                                           sbjkzb, conn);
                if (dzList.isEmpty()) {
                    throw new ApplicationException("û���ҵ��ɿ�ƾ֤��Ϊ��" + jkpzh +
                            "�Ľɿ��飡");
                }
                for (Iterator iter = dzList.iterator(); iter.hasNext(); ) {
                    sbjkzb = (Sbjkzb) iter.next();
                    jks_ynse += ((BigDecimal) sbjkzb.getSjje()).doubleValue();
                }
            }

            total_ynse = DataConvert.round(total_ynse, 2);
            jks_ynse = DataConvert.round(jks_ynse, 2);
            Debug.out("�ܽ�� total_ynse" + total_ynse + "Ԫ");
            Debug.out("�ܽ�� jks_ynse" + jks_ynse + "Ԫ");

            if (total_ynse == jks_ynse) {
                legal = Boolean.TRUE;
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨��¼��BlProcessor����¼���ܽɿ����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return legal;
    }


    private ArrayList doCreateConnectSbJks(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData();

        BlJksBo bo = (BlJksBo) vo.getData();
        String jkpzh = bo.getJkpzh();
        String zbxh = bo.getZbxh();
        String sbbh = bo.getSbbh();
        String type = bo.getType();
        String zwbs = bo.getZwbs();

        ArrayList hdtzsList = new ArrayList();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            //�ж��Ƿ��в�Ǩ
            ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                   .queryBySbbh(sbzb.getSbbh(), conn);
            //����в�Ǩ��Ϣ
            if (!jsblcqList.isEmpty()) {
                //��ȡ�˶�֪ͨ����������
                Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                              getBySbbh(sbbh, conn);
                //����Ƿ������˺˶�֪ͨ�飬���û�������ɺ˶�֪ͨ�鲢insert�����ݿ���
                if (hdtzs == null) {
                    hdtzs = doCreateHdtzs(ud, sbzb, conn);
                    hdtzsList.add(hdtzs);
                }
            }

            //����¼��Ľɿ����zwbs�ĵ�11λ��0����1
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '1';
            zwbs = String.copyValueOf(zwbschr);
            //���걨��ŷ����걨�ɿ�������
            //�������ͨ�ɿ��飬������걨�ɿ�����
            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        sbbh, jsjdm, zwbs, conn);
                //����ǵ��ʵĽɿ��飬����µ��ʱ�
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        sbbh, jsjdm, zbxh, zwbs, conn);
            }
            //���²�¼���걨�����״̬Ϊ�����
            DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "��˰�걨��¼��BlProcessor����¼�걨�ɿ��齨������ʱ����",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return hdtzsList;
    }


    private ArrayList doCreateConnectHzJks(VOPackage vo) throws BaseException {
        ArrayList blList = (ArrayList) vo.getData();
        UserData ud = vo.getUserData();

        ArrayList hdtzsList = new ArrayList();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            ArrayList sbbhList = new ArrayList();
            ArrayList wszhList = new ArrayList();

            BlJksBo bo = (BlJksBo) blList.get(0);
            String jkpzh = bo.getJkpzh();
            String type = bo.getType();
            String zbxh = bo.getZbxh();
            String zwbs = bo.getZwbs();

            for (Iterator iter = blList.iterator(); iter.hasNext(); ) {
                bo = (BlJksBo) iter.next();
                String sbbh = bo.getSbbh();

                //ͨ�������걨������ֵ�걨��ţ�����������걨��������
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(sbbh);
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);

                //�ж��Ƿ��в�Ǩ
                ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                       .queryBySbbh(sbzb.getSbbh(), conn);
                //����в�Ǩ��Ϣ
                if (!jsblcqList.isEmpty()) {
                    //��ȡ�˶�֪ͨ����������
                    Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                                  getBySbbh(sbbh, conn);
                    //����Ƿ������˺˶�֪ͨ�飬���û�������ɺ˶�֪ͨ�鲢insert�����ݿ���
                    if (hdtzs == null) {
                        hdtzs = doCreateHdtzs(ud, sbzb, conn);
                        hdtzsList.add(hdtzs);
                    }
                }

                Qswszz qswszz = new Qswszz();
                qswszz.setWszh(bo.getWszh());
                qswszz.setNdzb(bo.getNdzb());
                qswszz.setPzzldm(bo.getPzzldm());
                qswszz.setSbbh(sbbh);

                //�ж��Ƿ���걨���Ƿ��Ѿ���������˰֤
                if (!Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                    doCreateQswszz(sbzb, qswszz, ud, conn);
                }

                sbbhList.add(sbbh);
                wszhList.add(bo.getWszh());
            }

            ArrayList hzMxList = doHzWsz(sbbhList, conn);
            ArrayList sbjkmxList = null;
            if (Constants.PT_JKS.equals(type)) {
                sbjkmxList = doGetSbjkmx(jkpzh, jsjdm, conn);
            } else if (Constants.DZ_JKS.equals(type)) {
                sbjkmxList = doGetDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
            }
            //�ѻ��ܳ������걨�ɿ���ϸ���ݺ��Ѿ���¼���걨�ɿ���ϸ���ݽ��бȶ�
            int size = sbjkmxList.size();
            if (size != hzMxList.size()) {
                throw new ApplicationException("���ܳ�����˰��˰Ŀ�������벹¼�Ľɿ��鲻�����");
            }
            System.out.println("size : " + size);
            for (int i = 0; i < size; i++) {
                Sbjkmx hzMx = (Sbjkmx) hzMxList.get(i);
                Sbjkmx sbjkMx = (Sbjkmx) sbjkmxList.get(i);

                if (hzMx.getSzsmdm().equals(sbjkMx.getSzsmdm())) {
                    if (hzMx.getSjse().doubleValue() !=
                        sbjkMx.getSjse().doubleValue()) {
                        System.out.println("���ܳ�����˰��˰ĿΪ" + hzMx.getSzsmdm() +
                                           "�Ľ� " + hzMx.getSjse());
                        System.out.println("�ɿ�����˰��˰ĿΪ" + sbjkMx.getSzsmdm() +
                                           "�Ľ� " + sbjkMx.getSjse());
                        throw new ApplicationException(
                                "���ܳ�����˰��˰Ŀ�е�ʵ�ɽ���벹¼�Ľɿ��鲻�����");
                    }
                } else {
                    throw new ApplicationException("���ܳ�����˰��˰Ŀ�����벹¼�Ľɿ��鲻�����");
                }
            }

            String sbhzdh = doCreateQswszhz(ud, jkpzh, hzMxList, conn);
            //������˰֤������Ǵ��롢�걨���ܵ��Ÿ���˰֤����
            StringBuffer condition = new StringBuffer("");
            for (Iterator iter = wszhList.iterator(); iter.hasNext(); ) {
                String wszh = (String) iter.next();
                condition.append("'").append(wszh).append("',");
            }
            int end = condition.length() - 1; //�ҵ����һ���ַ���λ��

            StringBuffer sql = new StringBuffer("UPDATE sbdb.sb_jl_qswszz a ");
            sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ)
                    .append("', a.sbhzdh = '")
                    .append(sbhzdh)
                    .append("' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                    .append(" a.clbjdm = '").append(Constants.WSZ_CLBJDM_YWS)
                    .append("' AND a.wszh in (").append(condition.substring(0,
                    end))
                    .append(") ");
            DAOFactory.getInstance().getQswszzDAO().update(sql.toString(), conn);

            //����¼��Ľɿ����zwbs�ĵ�11λ��0����1
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '1';
            zwbs = String.copyValueOf(zwbschr);
            //���걨��ŷ����걨�ɿ�������
            //�������ͨ�ɿ��飬������걨�ɿ�����
            //
            if (Constants.PT_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        null, jsjdm, zwbs, conn);
                //����ǵ��ʵĽɿ��飬����µ��ʱ�
            } else if (Constants.DZ_JKS.equals(type)) {
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        null, jsjdm, zbxh, zwbs, conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "��˰�걨��¼��BlProcessor����¼�걨�ɿ��齨������ʱ����",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return hdtzsList;
    }

    /**
     * ������˰֤
     * @param sbzb Sbzb    �걨����vo
     * @param wszZb Qswszz    ��˰֤����vo
     * @param ud UserData     UserData
     * @param conn Connection  ���ݿ�����
     * @throws BaseException
     */
    private void doCreateQswszz(Sbzb sbzb, Qswszz wszZb, UserData ud,
                                Connection conn) throws BaseException {
        Qswszmx wszMx = null; //������˰֤��ϸ������

        try {
            Timestamp now = CommonUtil.getDBtime(conn);

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            Debug.out("ȡ�÷�����Ϣ��������Ϣ....");
            if (tufwxx == null) {
                throw new ApplicationException("���ݡ����صĻ�����Ϣ����Ϊ�գ�");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(sbzb.getSbbh(), conn);
            //ȡ�ü���˰���
            BigDecimal jmszje = hdbo.getJmzje();
            //ȡ�úϼƼ�˰���
            BigDecimal total_je = hdbo.getYnse();

            //��ʼ������˰֤����
            wszZb.setCjr(ud.getYhmc());
            wszZb.setCjrq(now);
            wszZb.setLrr(ud.getYhid()); //Ϊ���Ժ���ܵ�ʱ��Ļ������ݣ����ո��˻��ܵľ���������ֶ�
            wszZb.setLrrq(now);
            wszZb.setNd(DateUtil.getDate().substring(0, 4));
            wszZb.setClbjdm(Constants.WSZ_CLBJDM_YWS);
            wszZb.setSwjgzzjgdm(ud.getSsdwdm());
            wszZb.setSwjgzzjgmc(ud.getSsdwmc());
            wszZb.setJsjdm(CommonUtil.getWszJsjdm(ud, conn)); //�����˰֤�����˰����صļ��������
            wszZb.setFsdm(Constants.WSZ_FSDM); //ֻ��һ�ַ�ʽ���롪����˰����ذ���ʽ
            wszZb.setSwjgzzjgmc(ud.ssdwmc); //���ջ�������
            wszZb.setSbbh(sbzb.sbbh); //����걨���

            //����������˰֤�������
            wszZb.setJsfsdm(sbzb.getJsfsdm()); //��˰��ʽ����
            wszZb.setJsfsmc(sbzb.getJsfsmc()); //��˰����
            //���Ʊ֤�ʻ���������Ϣ
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            wszZb.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
            wszZb.setZsdmc(zh.zhmc); //���յ�����
            wszZb.setFdcwz(tufwxx.getTdfwzldz()); //���ز�λ��
            wszZb.setHtclrq(tufwxx.getHtqdsj()); //���ݺ�ͬǩ��ʱ��
            wszZb.setYqts(new BigDecimal(0)); //��������
            wszZb.setZnjzje(new BigDecimal(0)); //���ɽ��ܽ��
            wszZb.setJsje(jmszje); //��ü���˰�ܶ�
            wszZb.setHjsjje(total_je); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�

            //���ָ��˻��ǷǸ��ˣ���ҵ��������˰֤
            //����Ǹ��˹���ķ���
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(grxx.getJsjdm()); //��˰�˼��������
//                    ZRRJBSJ grjb = CommonUtil.getGrJBSJ(grxx);
                wszZb.setDjzclxdm(Constants.WSZ_DJZCLX); //�Ǽ�ע�����͡����Ǽ�ע������
                wszZb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //�Ǽ�ע�����͡����Ǽ�ע������
                wszZb.setFsmc(Constants.WSZ_FSMC); //�걨����ʽ����--��������˰����ذ���
                wszZb.setZjhm(grxx.getSfzjhm()); //֤������
                wszZb.setZjlxdm(grxx.getSfzjlx()); //֤�����ʹ���
                wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //��ù��ұ�׼��ҵ����
                wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //��ù��ұ�׼��ҵ����
                wszZb.setNsrmc(grxx.getNsrmc()); //��˰������
                wszZb.setNsrdm(grxx.sfzjhm); //��˰�˴���
                wszZb.setZjlxmc(grxx.sfzjlxmc); //֤����������
                Debug.out("ȡ�ø��˵ĵǼ���Ϣ....");
            }
            //����ǷǸ�����Ϣ��ȡ�ù�����ҵ����Ȳ�ͬ
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(fgrxx.getJsjdm());
                SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(fgrxx.jsjdm);
                wszZb.setDjzclxdm(swdjJbsj.getDjzclxdm());
                wszZb.setNsrjsjdm(fgrxx.jsjdm);
                wszZb.setSwjgzzjgdm2(swdjJbsj.getSwjgzzjgdm()); //���Ǹ��˵�����˰����ش������
                wszZb.setGjbzhydm(swdjJbsj.getGjbzhydm());
                wszZb.setNsrmc(fgrxx.getNsrmc()); //��˰������
                wszZb.setNsrdm(fgrxx.jsjdm);
                Debug.out("ȡ�÷Ǹ��˵�������Ϣ....");
            }

            //������ϸ��
            //��ʼ����ϸ
            wszMx = new Qswszmx();

            //��������ֵ����ṹ�����⣬���޸ĺ����޸Ĵ˴�����Ϣ
            wszMx.setWszh(wszZb.getWszh());
            wszMx.setNdzb(wszZb.getNdzb()); //����ֱ�
            wszMx.setPzzldm(wszZb.getPzzldm());
            wszMx.setSzdm(Constants.WSZ_QSSZDM); //Ĭ��˰�ִ���Ϊ����˰74��
            wszMx.setSzmc(Constants.WSZ_QSSZMC); //Ĭ��˰�ִ���Ϊ����˰74��

            wszMx.setCjrq(now); //��������
            wszMx.setLrrq(now); //¼������
            wszMx.setLrr(ud.getYhid());
            wszMx.setCjr(ud.getYhmc());
//          wszMx.setQxdm(qxdm); //���ش���
            wszMx.setJsjdm(wszZb.getJsjdm()); //���������
            wszMx.setSwjgzzjgdm(wszZb.getSwjgzzjgdm());
            wszMx.setJzbz(Constants.WSZ_JZBZ_DEFAULT); //���˱�־��Ĭ������0
            //�������
            wszMx.setNd(wszZb.getNd());
            //���Ԥ�㼶��
            wszMx.setYsjcdm(Constants.YSJCDM_DF); //������Ԥ�㼶�δ��룬21 �ط���
            wszMx.setYsjcmc(Constants.YSJCDM_DF_MC); //������Ԥ�㼶�δ��룬21 �ط���
            wszMx.setSkssjsrq(wszZb.getHtclrq()); //˰����������
            wszMx.setSkssksrq(wszZb.getHtclrq()); //˰����������

            if (tufwxx.getTdfwqszymj() != null &&
                tufwxx.getTdfwqszymj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getTdfwqszymj()); //Ȩ��ת�����
            } else if (tufwxx.getFwjzmj() != null &&
                       tufwxx.getFwjzmj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getFwjzmj()); //Ȩ��ת�����
            } else {
                wszMx.setQszymj(new BigDecimal(0));
            }
            //end added

            wszMx.setSl(sbzb.getSl()); //˰��
            Szsm szsm = CommonUtil.getSZSMDM(tufwxx.getTdfwqszylx(), conn);
            wszMx.setSzsmdm(szsm.szsmdm); //���˰��˰Ŀ����
            wszMx.setSzsmmc(szsm.szsmmc); //���˰��˰Ŀ����
            Yskm yskm = CommonUtil.getYskm(wszMx.getSzdm(), conn);
            if (yskm == null) {
                throw new ApplicationException("û��Ԥ���Ŀ���룡");
            }
            //���Ԥ���Ŀ
            wszMx.setYskmdm(yskm.yskmdm);
            wszMx.setYskmmc(yskm.yskmmc);
            wszMx.setYjhkc(wszZb.getJsje()); //������ϸ��ֻ��һ�����ݣ������ѽɻ�۳��ֶε�������ļ�˰����ֶ�
            wszMx.setJsje(hdbo.getJsyj()); //��˰�����ڼ�˰����
            wszMx.setSjse(hdbo.getYnse()); //ʵ��˰�����Ӧ��˰����˼��տ��е��
            wszMx.setJzbz("000001");

            //������������
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("������˰֤����ɹ�....��˰֤��Ϊ��" + wszZb.getWszh());

                DAOFactory.getInstance().getQswszmxDAO().insert(wszMx, conn);
                Debug.out("������˰֤��ϸ�ɹ�....");

                //�����걨����״̬λ
                String ztbs = Constants.ZB_ZTBS_YRK;
                DAOFactory.getInstance().getSbzbDAO().update(ztbs, wszZb.sbbh,
                        conn);

            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("��¼ʱ������˰֤�����ݿ�ʧ�ܣ�");
            }

            //���ϼ�����������ģ������Ѿ������˵��걨����Ҫ���������걨��Ϣ
            //�Ա��������ͳ�ƺͼ���
//          if (Constants.ZB_BLJMSBS_FHBL_YLR.equals(sbzb.bljmsbs) &&
//              Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs))
            //���Ӽ��⴦��
            String jmxzdm = Constants.JSJM_JMXZDM_SLJM;
            if(Constants.SETZ_QEZS.equals(sbzb.getSetz())){
            	 jmxzdm = Constants.JSJM_JMXZDM_CQJM;
            }
            
            if (jmszje == null || jmszje.doubleValue() == 0) {
            	BigDecimal jbsl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            	
            	BigDecimal jmsl = jbsl.subtract(hdbo.getSl());
            	if(jmsl.doubleValue()>0.001){
            		BigDecimal jmje = jmsl.multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	}else if(Constants.SETZ_JBZS.equals(sbzb.getSetz())) {
            		BigDecimal jmje = jbsl.multiply(new BigDecimal(0.5)).multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	} 	
            }  
            
            if (jmszje != null && jmszje.doubleValue() > 0) {
                HashMap map_jmsb = new HashMap();
                map_jmsb.put("jsjdm", wszZb.jsjdm);
                map_jmsb.put("szsmdm", wszMx.szsmdm);
                map_jmsb.put("jsje", hdbo.getJzse());
                map_jmsb.put("jmse", jmszje);
                map_jmsb.put("lrr", wszZb.lrr);
                map_jmsb.put("jmxmdm", Constants.JMXMDM); //��Ŀ����
                map_jmsb.put("jmxzdm", jmxzdm); //������Ŀ����
                map_jmsb.put("cjrq", wszZb.cjrq);
                map_jmsb.put("skssjsrq", wszMx.getSkssjsrq());
                map_jmsb.put("skssksrq", wszMx.getSkssksrq());
                if (!CommonUtil.insertSBJM(map_jmsb)) {
                    throw new ApplicationException("��¼ʱ����˰�ѵļ����걨��Ϣ��ʱ�򱨴�");
                }
                Debug.out("��������걨�ɹ���");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
    }


    private ArrayList doHzWsz(ArrayList sbbhList, Connection conn) throws
            BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            StringBuffer condition = new StringBuffer("");
            for (Iterator iter = sbbhList.iterator(); iter.hasNext(); ) {
                String sbbh = (String) iter.next();
                condition.append("'").append(sbbh).append("',");
            }
            int end = condition.length() - 1; //�ҵ����һ���ַ���λ��
            //���Ĳ����н�ȡStringBuffer��Ϊ��ȥ�����һ��","
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO()
                         .HzWsz(condition.substring(0, end), conn);
        } catch (Exception ex) {
            throw new ApplicationException("���ܲ�¼����˰֤ʱ����");
        }

        return sbjkmxList;
    }


    private ArrayList doGetSbjkmx(String jkpzh, String jsjdm, Connection conn) throws
            BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO().
                         querySbjkmx(jkpzh, jsjdm, conn);
        } catch (Exception ex) {
            throw new ApplicationException("��ѯ��¼�ɿ������ݵ�ʱ�����");
        }

        return sbjkmxList;
    }


    private ArrayList doGetDzSbjkmx(String jkpzh, String jsjdm, String zbxh,
                                    Connection conn) throws BaseException {
        ArrayList sbjkmxList = new ArrayList();

        try {
            sbjkmxList = DAOFactory.getInstance().getBlQueryJksDAO().
                         queryDzSbjkmx(jkpzh, jsjdm, zbxh, conn);
        } catch (Exception ex) {
            throw new ApplicationException("��ѯ��¼�ɿ������ݵ�ʱ�����");
        }

        return sbjkmxList;
    }


    private String doCreateQswszhz(UserData ud, String jkpzh,
                                   ArrayList sbjkmxList, Connection conn) throws
            BaseException {
        Qswszhz qswszhz = new Qswszhz();

        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //ȡ��˰����ؼ��������
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //ȡ��˰�������֯��������
            String swjgzzjgDm = ud.getSsdwdm();
            //ȡ���걨���ܵ���
            String sbhzdh = CommonUtil.getSequenceOfSbhzd(conn);
            Debug.out("ȡ���걨���ܵ��� : " + sbhzdh);

            //����˰֤���ܱ�vo��������
            qswszhz.setCjrq(now);
            qswszhz.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
            qswszhz.setHzfs(Constants.WSZ_HZFS_GR);
            qswszhz.setHzjsrq(now);
            qswszhz.setHzksrq(now);
            qswszhz.setHzrq(now);
            qswszhz.setJsjdm(jsjdm);
            qswszhz.setCjr(ud.getYhid());
            qswszhz.setLrr(ud.getYhid());
            qswszhz.setLrrq(now);
            qswszhz.setNd(DateUtil.getDate().substring(0, 4));
            qswszhz.setSbhzdh(sbhzdh);
            qswszhz.setSwjgzzjgdm(swjgzzjgDm);
            qswszhz.setHzfsmc("�����˻���");
            //qswszhz.setJsfsmc(bo.getJsfsmc());
            //���Ʊ֤�ʻ���������Ϣ
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            qswszhz.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
            qswszhz.setZsdmc(zh.zhmc); //���յ�����

            //���˰��˰ĿС�ڵ���4��Ҳ����һ�Žɿ���
            double sjse = 0.0;
            for (int i = 0; i < sbjkmxList.size(); i++) {
                sjse += ((Sbjkmx) sbjkmxList.get(i)).getSjse().doubleValue();
            }
            qswszhz.setSjse(new BigDecimal(sjse)); //ʵ�ɽ��
            qswszhz.setJkpzh(jkpzh);

            DAOFactory.getInstance().getQswszhzDAO().insert(qswszhz, conn);
        } catch (Exception ex) {
            throw new ApplicationException("������˰֤���ܱ�����ʱ�������󣡣���");
        }

        return qswszhz.getSbhzdh();
    }

    private void removeConnect(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData();

        QueryBlJksBo bo = (QueryBlJksBo) vo.getData();
        Sbjkzb sbjkzb = bo.getSbjkzb();
        String type = bo.getType();
        String sjly = bo.getSjly();
        String zbxh = bo.getZbxh();
        String zwbs = sbjkzb.getZwbs();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String jkpzh = sbjkzb.getJkpzh();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //������걨�ɿ���
            if (Constants.JKS_SJLY_FHZ.equals(sjly)) {
                //�����걨����״̬λΪ�Ѹ���
                DAOFactory.getInstance().getSbzbDAO().update(Constants.
                        ZB_ZTBS_YFH, sbjkzb.getSbbh(), conn);
            }
            //����ǻ������ɵĽɿ���
            else if (Constants.JKS_SJLY_HZ.equals(sjly)) {
                ArrayList qswszhzList = DAOFactory.getInstance().getQswszhzDAO().
                                        query(jsjdm, jkpzh, conn);
                if (qswszhzList.isEmpty()) {
                    throw new ApplicationException("û���ҵ���˰֤���ܼ�¼��");
                }
                Qswszhz qswszhz = (Qswszhz) qswszhzList.get(0);
                //ȡ�����ܵĹ�����ɾ����ѯ��������˰֤���ܱ�����
                DAOFactory.getInstance().getBlQueryJksDAO().
                        removeQswszhzConnect(qswszhz, conn);

                //�õ����ܵ���˰֤��
                ArrayList queryList = DAOFactory.getInstance().getBlQueryJksDAO().
                                      queryWszh(qswszhz.getSbhzdh(), conn);
                ArrayList wszList = (ArrayList) queryList.get(0);
                StringBuffer condition = new StringBuffer("");
                for (Iterator iter = wszList.iterator(); iter.hasNext(); ) {
                    String wszh = (String) iter.next();
                    condition.append("'").append(wszh).append("',");
                    Debug.out("����ʱ��wszh BlProcessor.removeConnect is" + wszh);
                }
                int end = condition.length() - 1; //�ҵ����һ���ַ���λ��

                //ɾ����˰֤�ӱ�����
                StringBuffer sql = new StringBuffer(
                        "DELETE FROM sbdb.sb_jl_qswszmx ");
                sql.append("WHERE wszh in(").append(condition.substring(0, end))
                        .append(") ");
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                //ɾ����˰֤��������
                sql = new StringBuffer("DELETE FROM sbdb.sb_jl_qswszz ");
                sql.append("WHERE wszh in(").append(condition.substring(0, end))
                        .append(") ");
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);

                //�޸��걨�����״̬��ʶ
                //�ָ����Ϊ�Ѹ���
                ArrayList sbbhList = (ArrayList) queryList.get(1);
                for (Iterator it = sbbhList.iterator(); it.hasNext(); ) {
                    String s = (String) it.next();
                    DAOFactory.getInstance().getSbzbDAO().update(Constants.
                            ZB_ZTBS_YFH, s, conn);
                    Debug.out("�ָ���sbbh in BlProcessor.removeConnect is" + s);
                }
            }

            //�����ɿ��������ʱ�򣬽ɿ����zwbs�ĵ�11λ��1�޸�Ϊ0
            char[] zwbschr = zwbs.toCharArray();
            zwbschr[10] = '0';
            zwbs = String.copyValueOf(zwbschr);

            String sbbh = null;

            if (Constants.PT_JKS.equals(type)) { //�������ͨ�Ľɿ��飬��sbbh�ֶ���Ϊnull
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbJks(jkpzh,
                        sbbh, jsjdm, zwbs, conn);
            } else if (Constants.DZ_JKS.equals(type)) { //����ǵ��ʵĽɿ��飬��sbbh�ֶ���Ϊnull
                DAOFactory.getInstance().getBlQueryJksDAO().updateSbDzJks(jkpzh,
                        sbbh, jsjdm, zbxh, zwbs, conn);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨��¼��BlProcessor��������¼ʱ����",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private Hdtzs doCreateHdtzs(UserData ud, Sbzb sbzb, Connection conn) throws
            BaseException {
        Hdtzs hdtzs = new Hdtzs();
        try {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            String nd = DateUtil.getDate().substring(0, 4);
            conn = QSDBUtil.getConnection();

            //update status of zb
            DAOFactory.getInstance().getSbzbDAO().update(Constants.
                    ZB_ZTBS_JS_TY, sbzb.getSbbh(), conn);

            //����˶�֪ͨ��

            hdtzs.sbbh = sbzb.getSbbh();
            hdtzs.bzbs = Constants.BZBS_JM;
            hdtzs.cjr = ud.yhmc;
            hdtzs.cjrq = now;
            hdtzs.dysj = null;
            //modified by zhaobo 20041218
            HashMap hdtzshMap = CommonUtil.getHDTZSH(ud, conn);
            hdtzs.hdtzsh = (String) hdtzshMap.get("hdtzsh");
            hdtzs.ndzb = (String) hdtzshMap.get("ndzb");
            hdtzs.wsjc = (String) hdtzshMap.get("wsjc");
            hdtzs.lsh = (BigDecimal) hdtzshMap.get("lsh");
            //end modified

            hdtzs.jbr = ud.yhmc;
            /**
             * ������˼��տ�ʱ����Ҫ�õ��ļ���˰��
             * Constants �еĶ��壺
              public static final String JE_CJJG = "JE_CJJG";   //�ɽ��۸�
              public static final String JE_JSYJ = "JE_JSYJ";   //��˰����
              public static final String JE_JZQS = "JE_JZQS";   //������˰
              public static final String JE_SJYZ = "JE_SJYZ";   //ʵ��Ӧ��
              public static final String JE_JZSE = "JE_JZSE";   //����˰��
             public static final String JE_QYZFBCDKE = "JE_QYZFBCDKE";//�����ѹ�����ס���ı��εֿ۶�
              public static final String JE_FWJHJG = "JE_FWJHJG";  //���ݽ����۸�
              public static final String JE_CQJMJE = "JE_CQJMJE";  //��Ǩ������
              public static final String JE_PTZZJMJE = "JE_PTZZJMJE";//��ͨסլ��˰���
              public static final String JE_JMSZE = "JE_JMSZE";//����˰�ܽ��
              public static final String JE_YNSE = "JE_YNSE";    //Ӧ��˰��
             * @param String �걨���
             * @return HashMap
             */
            JghdsjBo hdbo = CommonUtil.getJZSE(sbzb.getSbbh(), conn);
            hdtzs.cjjg = hdbo.getCjjgrmb();
            hdtzs.jsyj = hdbo.getJsyj();
            hdtzs.jzqs = hdbo.getJzqs();
            hdtzs.sjyz = hdbo.getSjyz(); //Ӧ����˰
            //��ȡ�ѹ�����ס����
            hdtzs.kcqyzfx = hdbo.getGyzfjmje();
            hdtzs.lrr = ud.yhmc;
            hdtzs.lrrq = now;
            //�õ���˰������
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                hdtzs.sqr = grxx.getNsrmc();
            } else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbzb.sbbh, conn);
                hdtzs.sqr = fgrxx.getNsrmc();
            }

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.getSbbh(), conn);
            hdtzs.spfxmmc = tufwxx.getFdcxmmc();
            hdtzs.zldi = tufwxx.getTdfwzldz();

            //����һ���˶�֪ͨ���¼
            DAOFactory.getInstance().getHdtzsDAO().insert(hdtzs, conn);

            //����в�Ǩס�������������ϸ��
            ArrayList jsblcqList = DAOFactory.getInstance().getJsblcqDAO()
                                   .queryBySbbh(sbzb.getSbbh(), conn);
            if (!jsblcqList.isEmpty()) {
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(hdbo.getCqjmje());
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_CQFW);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }

            BigDecimal ptzzjmje = (hdbo.getPtzzjmje());
            if (ptzzjmje.doubleValue() > 0) { //����ͨסլ������
                Hdjmmx hdjmmx = new Hdjmmx();
                hdjmmx.setBz("");
                hdjmmx.setCjr(ud.getYhmc());
                hdjmmx.setCjrq(now);
                hdjmmx.setHdtzsh(hdtzs.hdtzsh);
                hdjmmx.setJmje(ptzzjmje);
                hdjmmx.setLrr(ud.getYhmc());
                hdjmmx.setLrrq(now);
                hdjmmx.setNd(nd);
                hdjmmx.setZcbh(Constants.JMZC_PTZZ);

                DAOFactory.getInstance().getHdjmmxDAO().insert(hdjmmx, conn);
            }
        } catch (Exception ex) {
            throw new ApplicationException("����˶�֪ͨ��ʱ����");
        }
        return hdtzs;
    }

    private void getSbjkzbInfo(Sbjkzb sbjkzb, UserData ud, Connection conn) {
        try {

            Yskm yskm = CommonUtil.getYskm(sbjkzb.getSzdm(), conn);
            sbjkzb.setYskmdm(yskm.yskmdm); //Ԥ���Ŀ
            sbjkzb.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ����

            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            sbjkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //������֯��������
            sbjkzb.setGkzzjgmc(swjgzzjg.skgk); //������֯��������

            //�õ���˰�˵ĵǼǻ�������
            SWDJJBSJ jbsj = null;
            ZRRJBSJ zrrJbsj = null;
            try {
                jbsj = CommonUtil.getFgrJBSJ(sbjkzb.getJsjdm());
            } catch (Exception ex1) {
                Debug.out(ex1);
            }

            if (jbsj == null) { //��ȥ����Ȼ�˵Ǽ�
                try {
                    zrrJbsj = CommonUtil.getGrJBSJ(sbjkzb.getJsjdm());
                    if (zrrJbsj != null) {
                        sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //�Ǽ�ע������
                        sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //�Ǽ�ע����������
                        sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //���ұ�׼��ҵ����
                        sbjkzb.setNsrmc(zrrJbsj.getNsrmc()); //��˰������
                    }
                } catch (Exception ex1) {
                    Debug.out(ex1);
                }
            } else {
                sbjkzb.setLsgxdm(jbsj.getLsgxdm()); //������ϵ
                sbjkzb.setLsgxmc(jbsj.getLsgxmc()); //������ϵ����
                sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //�Ǽ�ע������
                sbjkzb.setDjzclxmc(jbsj.getDjzclxmc()); //�Ǽ�ע����������
                sbjkzb.setJydzlxdm(jbsj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
                sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //���ұ�׼��ҵ����
                sbjkzb.setGjbzhymc(jbsj.getGjbzhymc()); //���ұ�׼��ҵ����
                sbjkzb.setNsrmc(jbsj.getNsrmc()); //��˰������
            }

            if (Constants.JKS_SKLXDM_XHJK.equals(sbjkzb.getSklxdm())) {
                sbjkzb.setSklxmc(Constants.JKS_SKLXDM_XHJK_MC); //˰����������
            } else if (Constants.JKS_SKLXDM_ZCJK.equals(sbjkzb.getSklxdm())) {
                sbjkzb.setSklxmc(Constants.JKS_SKLXDM_ZCJK_MC); //˰����������
            }
            sbjkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
            sbjkzb.setSzmc(Constants.WSZ_QSSZMC); //˰������
            sbjkzb.setZsswjgzzjgmc(ud.ssdwmc); //����˰�������֯��������
            sbjkzb.setSwjgzzjgmc(ud.ssdwmc); //˰�������֯��������

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void getSzsmmc(Sbjkmx sbjkmx) {
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Szsm szsm = CommonUtil.getSZSM(sbjkmx.getSzsmdm(), conn);
            sbjkmx.setSzsmmc(szsm.getSzsmmc());
        } catch (Exception ex) {
            ex.printStackTrace();
            sbjkmx.setSzsmmc("û���ҵ�����Ŀ����");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }
}

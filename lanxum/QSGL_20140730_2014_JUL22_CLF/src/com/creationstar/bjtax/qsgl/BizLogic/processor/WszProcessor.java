package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
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
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryWszBo;
import com.creationstar.bjtax.qsgl.model.bo.WszBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.Helper;
import com.creationstar.bjtax.qsgl.util.JksUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 * ��˰֤��processor����������˰֤�йصĺ�̨�߼������ݿ���������ڴ�Processor�н���
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
public class WszProcessor implements Processor {
    /**
     *
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is WszProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }
        switch (vo.getAction()) {
        //��ѯ��˰֤
        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.CREATE_WSZ:
            result = doSave(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

            //������˰֤
        case ActionType.CX_WSZ:
            cxWsz(vo);
            break;

            //������˰֤���ɽɿ���
        case ActionType.HZ_WSZ:
            result = doHzWsz(vo);
            break;

            //�������ܡ������ɿ���
        case ActionType.CX_HZWSZ:
            cxJks(vo);
            break;

        case ActionType.CHANGE_WSZ_STATUS:
            doUpdate(vo);
            break;

            //��ӡ��˰֤
        case ActionType.PRINT_WSZ:
            result = doPrint(vo);
            break;

            //��Ŵ�ӡ
        case ActionType.CHANGE_WSZH_PRINT:
            result = doChangePrint(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ��ѯ��˰֤
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList();

        QueryWszBo bo = (QueryWszBo) vo.getData();

        StringBuffer condition = new StringBuffer("");

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            condition.append(" AND a.jsjdm = '").
            	append(CommonUtil.getWszJsjdm(vo.getUserData(), conn)).
                    append("' ");

            if (bo.getNdzb() != null && !bo.getNdzb().equals("")) {
                condition.append(" AND a.ndzb = '").append(bo.getNdzb()).append(
                        "' ");
            }
            if (bo.getStartWszh() != null && !bo.getStartWszh().equals("")) {
                condition.append(" AND a.wszh >= '").append(bo.getStartWszh()).
                        append("' ");
            }
            if (bo.getEndWszh() != null && !bo.getEndWszh().equals("")) {
                condition.append(" AND a.wszh <= '").append(bo.getEndWszh()).
                        append("' ");
            }
            if (bo.getStartCjrq() != null && !bo.getStartCjrq().equals("")) {
                condition.append(" AND a.cjrq >= TO_DATE('").append(bo.
                        getStartCjrq()).
                        append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getEndCjrq() != null && !bo.getEndCjrq().equals("")) {
                condition.append(" AND a.cjrq <= TO_DATE('").append(bo.
                        getEndCjrq()).
                        append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                condition.append(" AND a.nsrmc like '%").append(bo.getNsrmc()).
                        append("%' ");
            }

            //��������Ȩ�޿���
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_QSWSZZ");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);
            resultList = DAOFactory.getInstance().getQswszzDAO().queryAll(
                    condition.toString(), conn);

            //��ѯ�ѻ��ܵ���˰֤�����ܵ����ĸ��ɿ�����
            for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
                bo = (QueryWszBo) iter.next();
                //������ѻ��ܵ���˰֤
                if (Constants.WSZ_CLBJDM_YJZ.equals(bo.getQswszzVo().getClbjdm())) {
                    //��ѯ�ѻ��ܵ���˰֤��Ӧ�Ľɿ���
                    String jkpzh = (String) DAOFactory.getInstance().
                                   getSbjkzbDAO().query(bo.getQswszzVo(),
                            bo.getMxVo(), conn);
                    //ȡ�ýɿ�ƾ֤��
                    bo.getQswszzVo().setJkpzh(jkpzh);
                }
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(),
                             "ʧ��" + condition.toString());

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //������˰֤������ӱ��е�������1�Զ�������Ϊ��ʹ�÷��㣬��Ҫ������֯
        //��ѯ�����1���������ݶ�Ӧ�����ӱ����ݵ��������bo���������ݷ���һ������vo�У���Ӧ���ӱ����ݷ���ArrayList��
        //���´�����������֯��ѯ�����ݣ�����֯�õ�bo�ŵ�ArrayList���淵��

        ArrayList returnList = new ArrayList(); //���շ��ظ�ǰ̨�Ĳ�ѯ�����
        ArrayList tempList = new ArrayList(); //��ʱ��Ų�ѯ���bo�е���ϸvo
        QueryWszBo resultBo = new QueryWszBo(); //���շ��ظ�ǰ̨�Ĳ�ѯ������е�ÿһ����¼
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            //���bo����DAO.queryAll()������ѯ���ص�ArrayList�е�ÿһ����¼������ÿ�ζ�Ҫnew
            bo = (QueryWszBo) iter.next();

            if (returnList.isEmpty()) { //������ظ�ǰ̨�Ĳ�ѯ���Ϊ�գ�˵���ǵ�һ������
                resultBo.setQswszzVo(bo.getQswszzVo());
                //resultBo.setJkpzh(bo.getJkpzh() );
                tempList.add(bo.getMxVo()); //�ڲ�ѯ���bo�м�����Ӧ���ӱ�vo
                resultBo.setResultList(tempList); //������ѯ���bo�д洢�ӱ�����ArrayList

                returnList.add(resultBo); //��������֯�Ĳ�ѯ����ŵ����ظ�ǰ̨��ArrayList��

            } else {
                //���������ѯ�������˰֤�š�����ֱ�Ʊ֤���������������ͬ
                //˵������������ֻͬ����ϸ��ͬ��Ҳ����1���������ݶ�Ӧ������ϸ�����ݵ����
                //��Ѹ�������ϸ���ݷŵ���ͬ��resultBo����ϸArrayList��
                if (bo.getQswszzVo().getWszh().equals(resultBo.getQswszzVo().
                        getWszh())
                    &&
                    bo.getQswszzVo().getNdzb().equals(resultBo.getQswszzVo().getNdzb())
                    &&
                    bo.getQswszzVo().getPzzldm().equals(resultBo.getQswszzVo().getPzzldm())) {
                    //��Ϊû�й����µ�resultBo������ArrayList�еĵ�ǰresultBo�����µ�resultBo
                    tempList.add(bo.getMxVo());
                    resultBo.setResultList(tempList);

                    //returnList.add(resultBo);
                } else {
                    //��Ϊ��һ����ͬ�����ݣ��������¹���resultBo��tempList
                    resultBo = new QueryWszBo();
                    tempList = new ArrayList();

                    resultBo.setQswszzVo(bo.getQswszzVo());
                    //resultBo.setJkpzh(bo.getJkpzh());
                    tempList.add(bo.getMxVo());
                    resultBo.setResultList(tempList);

                    returnList.add(resultBo);
                }
            }
        }

        return returnList;
    }


    /**
     * ������˰֤ʱ��ѯ��˰֤�ķ���
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {
        Qswszz wszVo = new Qswszz();
        QueryWszBo bo = (QueryWszBo) vo.getData();
        StringBuffer condition = new StringBuffer("");
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            if (bo.getStartWszh() != null && !bo.getStartWszh().equals("")) {
                condition.append(" AND a.wszh = '").append(bo.getStartWszh()).
                        append("' ");
            }
            if (bo.getNdzb() != null && !bo.getNdzb().equals("")) {
                condition.append(" AND a.ndzb = '").append(bo.getNdzb()).append(
                        "' ");
            }
            //��������Ȩ�޿���
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_QSWSZZ");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);
            wszVo = (Qswszz) DAOFactory.getInstance().getQswszzDAO().get(
                    condition, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return wszVo;
    }

    /**
     * ������˰֤
     * @param vo VOPackage
     * @throws BaseException
     */
    private ArrayList doHzWsz(VOPackage vo) throws BaseException {
        ArrayList mxList = new ArrayList(); //���ܵ����ݼ�
        ArrayList fpList = new ArrayList(); //�������ݺ����ɽɿ�����������ݼ�

        UserData ud = (UserData) vo.getUserData();

        //��ǰ̨��������bo�У�ȡ����������
        WszBo bo = new WszBo();
        bo = (WszBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //ȡ��˰����ؼ��������
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            //ȡ��˰�������֯��������
            String swjgzzjgDm = ud.getSsdwdm();

            String drpch = bo.getDrpch();

            //1�����ܷ�ʽ������
            //2���ֽ� or ˢ�� or ֧Ʊ
            //3��������ʼ���ںͽ�ֹ���ڣ����û����ʼ���ڣ�����ܵ�ʱ������Ϊ ��˰֤������� <�� ���ܽ�ֹ����
            //������ܵ�ʱ������Ϊ ������ʼ���� <�� ��˰֤������� <�� ���ܽ�ֹ����
            String hzqsrq = bo.getHzqsrq();
            String hzjzrq = bo.getHzjzrq();

            StringBuffer condition = new StringBuffer("");
            if (bo.getJsfs() != null && !bo.getJsfs().equals("")) {
                condition.append(" AND a.JSFSDM = '").append(bo.getJsfs()).
                        append("' ");
            }

            //���ݲ�ѯ���������˻�����˰֤
            if (Constants.WSZ_HZFS_GR.equals(bo.getHzfs())) {
                if (hzqsrq != null && !hzqsrq.equals("")) {
                    condition.append(" AND a.cjrq >= TO_DATE('").append(hzqsrq)
                            .append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
                }
                if (hzjzrq != null && !hzjzrq.equals("")) {
                    condition.append(" AND a.cjrq <= TO_DATE('").append(hzjzrq)
                            .append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
                }
                condition.append(" AND a.lrr = '").append(ud.getYhid()).append(
                        "' ");
                condition.append(" AND substr(a.sbbh,0,1) != 'P'");

                mxList = DAOFactory.getInstance().getQswszzDAO().HzWszGr(
                        condition.toString(), conn);
                Debug.out("�ɹ������˰֤��ϸ�Ļ�����ȡ����....");

                if (mxList.isEmpty()) {
                    throw new Exception("����ѡ����ʱ����ڵ���˰֤���ѻ���!!!");
                }
                //�������˷�ʽ���ܵ���˰֤����vo��������
                for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                    Sbjkmx mxVo = new Sbjkmx();
                    mxVo = (Sbjkmx) iter.next();

                    mxVo.setCjrq(now);
                    mxVo.setJsjdm(jsjdm);
                    mxVo.setLrrq(now);
                    mxVo.setNd(DateUtil.getDate().substring(0, 4));
                    /** @todo ���������ȡֵ�� */
                    //mxVo.setQjfc();
                    mxVo.setQxdm(CommonUtil.getQxdm(ud));
                    //mxVo.setSjfc();
                    mxVo.setSkssjsrq(DataConvert.String2Timestamp(hzjzrq));
                    mxVo.setSkssksrq(DataConvert.String2Timestamp(hzqsrq));
                    //mxVo.setSl();    ����ǲ���Ҫ�ģ��걨�ɿ���ϸ���е�˰��û���õ�
                    mxVo.setSwjgzzjgdm(swjgzzjgDm);
                    mxVo.setYsjcdm(Constants.YSJCDM_DF);

                    //�õ�˰��˰Ŀ����
                    Szsm szsm = CommonUtil.getSZSM(mxVo.getSzsmdm(), conn);
                    mxVo.setSzsmmc(szsm.getSzsmmc());
                }

                fpList = this.JksFP(mxList, vo);
                Debug.out("fpList �ĳ��ȣ�" + fpList.size());
                for (Iterator iter = fpList.iterator(); iter.hasNext(); ) {
                    //��������˰֤������insert��������˰֤����
                    DAOFactory.getInstance().getQswszhzDAO().insert((Qswszhz)
                            iter.next(), conn);
                    Debug.out("���������ݲ�����ܱ�ɹ�...");
                }

                //���ɿ�����ϸ�������ýɿ�ƾ֤��
                for (int i = 0; i < mxList.size(); i++) {
                    if (mxList.size() <= 4) {
                        ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.get(
                                0)).getJkpzh());
                    } else {
                        if (i < 4) {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(0)).getJkpzh());
                        } else {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(1)).getJkpzh());
                        }
                    }
                }

                //������˰֤������Ǵ��롢�걨���ܵ��Ÿ���˰֤����
                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).
                        append("', a.sbhzdh = '")
                        .append(((Qswszhz) fpList.get(0)).getSbhzdh())
                        .append(
                        "' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                        .append(" a.clbjdm = '").append(Constants.
                        WSZ_CLBJDM_YWS).append("' ")
                        .append(condition);

                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
                Debug.out("������˰֤������Ǵ��롢�걨���ܵ��Ÿ���˰֤����....");

            } else {
                //���ݲ�ѯ���������ֵ�λ���ܷ�ʽ���ɵ���˰֤
                if (Constants.WSZ_HZFS_SWS.equals(bo.getHzfs())) { //˰����ػ���
                    condition.append(" AND a.jsjdm = '").append(jsjdm).append(
                            "' ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().
                             HzWszSwjg(condition.toString(), conn);
                } else if (Constants.WSZ_HZFS_ZSD.equals(bo.getHzfs())) { //���յ����
                    condition.append(" AND substr(a.zsddm,0,8) = '").append(ud.
                            getXtsbm1().substring(0, 8)).append("' ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().
                             HzWszZsd(condition.toString(), conn);
                }

                //���ε������˰֤����
                if (drpch != null && !drpch.equals("")) {
                    condition.append(
                            " AND a.sbbh in (SELECT sbbh FROM QSDB.QS_JL_SBZB WHERE drpch = '").
                            append(drpch).append("') ");
                    mxList = DAOFactory.getInstance().getQswszzDAO().HzWszPl(bo,
                            conn);
                }

                if (mxList.isEmpty() && (drpch == null || drpch.equals(""))) {
                    throw new Exception("����ѡ����ʱ����ڵ���˰֤���ѻ���!!!");
                } else if (mxList.isEmpty() &&
                           (drpch != null && !drpch.equals(""))) {
                    throw new Exception("��������������κ��������˰֤���ѻ���!!!");
                }

                //�������ܵ���˰֤����vo��������
                for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                    Sbjkmx mxVo = new Sbjkmx();
                    mxVo = (Sbjkmx) iter.next();

                    mxVo.setCjrq(now);
                    mxVo.setJsjdm(jsjdm);
                    mxVo.setLrrq(now);
                    mxVo.setNd(DateUtil.getDate().substring(0, 4));
                    /** @todo ���������ȡֵ�� */
                    //mxVo.setQjfc();
                    mxVo.setQxdm(CommonUtil.getQxdm(ud));
                    //mxVo.setSjfc();
                    /** @todo ����������������ã��д�ȷ�� */
//                    mxVo.setSkssjsrq(DataConvert.String2Timestamp(hzjzrq));
//                    mxVo.setSkssksrq(DataConvert.String2Timestamp(hzqsrq));
                    mxVo.setSkssjsrq(now);
                    mxVo.setSkssksrq(now);
                    //mxVo.setSl();����ǲ���Ҫ�ģ��걨�ɿ���ϸ���е�˰��û���õ�
                    mxVo.setSwjgzzjgdm(swjgzzjgDm);
                    mxVo.setYsjcdm(Constants.YSJCDM_DF);

                    //�õ�˰��˰Ŀ����
                    Szsm szsm = CommonUtil.getSZSM(mxVo.getSzsmdm(), conn);
                    mxVo.setSzsmmc(szsm.getSzsmmc());
                }

                fpList = this.JksFP(mxList, vo);
                for (Iterator iter = fpList.iterator(); iter.hasNext(); ) {
                    //��������˰֤������insert��������˰֤����
                    DAOFactory.getInstance().getQswszhzDAO().insert((Qswszhz)
                            iter.next(), conn);
                }

                //���ɿ�����ϸ�������ýɿ�ƾ֤��
                for (int i = 0; i < mxList.size(); i++) {
                    if (mxList.size() <= 4) {
                        ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.get(
                                0)).getJkpzh());
                    } else {
                        if (i < 4) {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(0)).getJkpzh());
                        } else {
                            ((Sbjkmx) mxList.get(i)).setJkpzh(((Qswszhz) fpList.
                                    get(1)).getJkpzh());
                        }
                    }
                }

                //������˰֤������Ǵ��롢�걨���ܵ��Ÿ���˰֤����
                StringBuffer sql = new StringBuffer(
                        "UPDATE sbdb.sb_jl_qswszz a ");
                sql.append("SET a.clbjdm = '").append(Constants.WSZ_CLBJDM_YJZ).
                        append("', a.sbhzdh = '")
                        .append(((Qswszhz) fpList.get(0)).getSbhzdh())
                        .append(
                        "' WHERE (a.sbhzdh is null OR a.sbhzdh = '') AND ")
                        .append(" a.clbjdm = '").append(Constants.
                        WSZ_CLBJDM_YWS).append("' ")
                        .append(condition);
                Debug.out("�걨���ܵ��Ÿ���˰֤���� " + sql.toString());
                DAOFactory.getInstance().getQswszzDAO().update(sql.toString(),
                        conn);
            }

            return doCreateJks(vo, fpList, mxList);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }


    /**
     * ���ܷ�ʽ���ɵĽɿ���
     * @param vo VOPackage
     * @param fplist ArrayList   �ֺ�Ʊ����˰֤�������ݼ���
     * @param mxList ArrayList   �ɿ�����ϸ���ݼ���
     * @throws BaseException
     */
    private ArrayList doCreateJks(VOPackage vo, ArrayList fplist,
                                  ArrayList mxList) throws BaseException {
        UserData ud = (UserData) vo.getUserData();
        ArrayList jkzbList = new ArrayList();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);
            Yskm yskm = null; //Ԥ���Ŀ

            //�õ����ܵ�������Ա������˰����صĻ�����Ϣ
            SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(jsjdm);
            if (swdjJbsj == null) {
                throw new ApplicationException("���ܵõ�����������Ա������˰����صĻ�����Ϣ��");
            }
            //�õ���ǰʱ��
            Timestamp now = CommonUtil.getDBtime(conn);

            //added by zhaobo at 20050711
            //cause zwbs modified to jk
            WszBo bo = (WszBo) vo.getData();
            String zwbs;
            if (Constants.WSZ_JSFS_XJ.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_XJ;
            } else if (Constants.WSZ_JSFS_ZP.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_ZP;
            } else if (Constants.WSZ_JSFS_SK.equals(bo.getJsfs())) {
                zwbs = Constants.JKS_ZWBS_SK;
            } else {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }
            //added end

            //���ݷ�Ʊ�����ݣ�insert�걨�ɿ������¼
            for (Iterator iter = fplist.iterator(); iter.hasNext(); ) {
                Qswszhz fpVo = (Qswszhz) iter.next();
                Sbjkzb jkzb = new Sbjkzb();

                //Ϊ�ɿ��걨����ȫ����
                jkzb.setCjrq(now); //��������
                jkzb.setXjrq(CommonUtil.getXjrq(jkzb.getCjrq(), 7)); //��˰�޽�����
                jkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //�����Ǵ���
                jkzb.setDjzclxdm(swdjJbsj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
                jkzb.setFsdm(Constants.WSZ_FSDM); //�Ǽ��걨��ʽ����
                jkzb.setGjbzhydm(swdjJbsj.getGjbzhydm()); //���ұ�׼��ҵ����
                Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
                jkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //������֯��������
                jkzb.setGkzzjgmc(swjgzzjg.skgk); //������֯��������
                jkzb.setZwbs(zwbs); //�����ʶ

                jkzb.setJsjdm(jsjdm); //���������
                jkzb.setJydzlxdm(swdjJbsj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
                jkzb.setLrr(ud.getYhid()); //¼����
                jkzb.setLrrq(now); //¼������
                jkzb.setLsgxdm(swdjJbsj.getLsgxdm()); //������ϵ����
                jkzb.setNd(DateUtil.getDate().substring(0, 4)); //���
                jkzb.setQxdm(CommonUtil.getQxdm(ud)); //���ش���
                //ԭ��sbbh��Ϊ���걨��ţ����ڸ�Ϊ�걨��ţ�����û���걨���
                //jkzb.setSbbh(JksUtil.getSbbh(jsjdm)); //�걨���
                jkzb.setSbrq(now); //�걨����

                jkzb.setSjly(Constants.JKS_SJLY_HZ); //������Դ�������ܷ�ʽ����
                jkzb.setSklxdm(Constants.JKS_SKLXDM_HZJK); //˰�����ʹ���
                jkzb.setSwjgzzjgdm(ud.getSsdwdm()); //˰�������֯��������
                jkzb.setSzdm(Constants.WSZ_QSSZDM); //˰�ִ���
                jkzb.setZsswjgzzjgdm(ud.getSsdwdm()); //���ջ��ش���
                jkzb.setYsjcdm(Constants.YSJCDM_DF); //Ԥ�㼶��
                //�õ�Ԥ���Ŀ����
                yskm = CommonUtil.getYskm(jkzb.getSzdm(), conn);
                jkzb.setYskmdm(yskm.yskmdm);

                jkzb.setSwjgzzjgmc(swdjJbsj.getSwjgzzjgmc()); //˰�������֯��������
                jkzb.setLsgxmc(swdjJbsj.getLsgxmc()); //������ϵ����
                jkzb.setDjzclxmc(swdjJbsj.getDjzclxmc()); //�Ǽ�ע����������
                jkzb.setGjbzhymc(swdjJbsj.getGjbzhymc()); //���ұ�׼��ҵ����
                jkzb.setNsrmc(swdjJbsj.getNsrmc()); //��˰������
                jkzb.setZsswjgzzjgmc(ud.ssdwmc); //����˰�������֯��������
                jkzb.setSwjgzzjgmc(ud.ssdwmc); //˰�������֯��������
                jkzb.setSklxmc(Constants.JKS_SKLXDM_HZJK_MC); //˰����������
                jkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
                jkzb.setSzmc(Constants.WSZ_QSSZMC); //˰������
                jkzb.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ����
                jkzb.setZyrq(now); //��������

                // �������ɽɿ��鲻����������Ϣ Modifyed by wuyouzhi 2006.2.8
//                //�õ�������Ϣ
//                ArrayList yhList = (ArrayList) CommonUtil.getYHZH(jsjdm);
//                for (Iterator it = yhList.iterator(); it.hasNext(); )
//                {
//                    YHZH yhzh = (YHZH) it.next();
//                    if (Constants.JKS_JBZHBS.equalsIgnoreCase(yhzh.getJbzhbs()))
//                    {
//                        jkzb.setYhdm(yhzh.getYhdm());
//                        jkzb.setYhmc(yhzh.getKhyhmc());
//                        jkzb.setZh(yhzh.getZh());
//                    }
//                }

                jkzb.setJkpzh(fpVo.getJkpzh()); //�ɿ�ƾ֤��;
                jkzb.setSjje(fpVo.getSjse()); //ʵ�ɽ��
                jkzb.setRkje(fpVo.getSjse()); //�����
                Sbjkmx mx = (Sbjkmx) mxList.get(0);
                jkzb.setSkssjsrq(mx.getSkssjsrq()); //˰��������ֹ����
                jkzb.setSkssksrq(mx.getSkssksrq()); //˰��������ʼ����
                jkzb.setBz("��˰��ʽ��" + fpVo.getJsfsmc());
                //end modified
                jkzb.setSbbh(CommonUtil.getJksSbbh(jsjdm)); //�걨����

                DAOFactory.getInstance().getSbjkzbDAO().insert(jkzb, conn);

                //�����ɺõĽɿ�����󷵻ص�ǰ̨
                jkzbList.add(jkzb);
            }

            //����sjbkmx vo�����ݣ�insert�걨�ɿ���ϸ���¼
            for (Iterator iter = mxList.iterator(); iter.hasNext(); ) {
                Sbjkmx mxVo = new Sbjkmx();
                mxVo = (Sbjkmx) iter.next();

                //�õ�Ԥ���Ŀ���룬Ŀǰ��˰����˰��˰Ŀ��Ӧ��Ԥ���Ŀ���붼��һ����
                //�������걨�ɿ�����vo�е�˰��ȥȡ�Ϳ�����
                mxVo.setYskmdm(yskm.yskmdm);
                mxVo.setYskmmc(yskm.yskmmc);
                mxVo.setSbbh(CommonUtil.getJksSbbh(jsjdm)); //�걨����
                mxVo.setYskmfcbl(com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(yskm.yskmdm, mxVo.getSzsmdm(), mxVo.getSwjgzzjgdm()));//�ֳɱ���
                DAOFactory.getInstance().getSbjkmxDAO().insert(mxVo, conn);
            }

            int kssl = 0; //��˰֤����

            //��֯���ظ�ǰ̨������
            //���û�з�Ʊ�������Ҳ����ֻ��һ�Žɿ���
            int mxListsize = mxList.size();
            if (jkzbList.size() == 1) {
                //���ɿ���ϸ�����ݷ��뵽�걨�ɿ������vo��
                ((Sbjkzb) jkzbList.get(0)).setMxList(mxList);
                for (int i = 0; i < mxListsize; i++) {
                    //�ýɿ������˰֤����������ϸ�����еĿ�˰�����ĺ�
                    kssl += ((Sbjkmx) mxList.get(i)).getKssl().intValue();
                }
                ((Sbjkzb) jkzbList.get(0)).setKssl(kssl); //������˰֤����
            } else if (jkzbList.size() > 1) { //����з�Ʊ�����
                ArrayList tempList = new ArrayList();
                //���ڵ�ҵ����һ�Žɿ����������4��˰��˰Ŀ�������5��˰Ŀ���Ժ���ܻ��б仯
                //�ѵ�һ�Žɿ������ϸ���ݼ��뵽�걨�ɿ������vo��
                for (int i = 0; i < 4; i++) {
                    tempList.add(mxList.get(i));

                    //�ýɿ������˰֤����������ϸ�����еĿ�˰�����ĺ�
                    kssl += ((Sbjkmx) mxList.get(i)).getKssl().intValue();
                }
                ((Sbjkzb) jkzbList.get(0)).setMxList(tempList);
                ((Sbjkzb) jkzbList.get(0)).setKssl(kssl); //������˰֤����

                //�ѵڶ��Žɿ������ϸ���ݼ��뵽�걨�ɿ������vo��
                tempList = new ArrayList();
                for (int i = 1; i <= mxListsize - 4; i++) {
                    tempList.add(mxList.get(mxListsize - i));

                    //�ýɿ������˰֤����������ϸ�����еĿ�˰�����ĺ�
                    kssl = ((Sbjkmx) mxList.get(mxListsize - i)).getKssl().
                           intValue();
                }
                ((Sbjkzb) jkzbList.get(1)).setMxList(tempList);
                ((Sbjkzb) jkzbList.get(1)).setKssl(kssl); //������˰֤����
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return jkzbList;
    }

    /**
     * �������ܽɿ���
     * @param vo VOPackage
     * @throws BaseException
     */
    private void cxJks(VOPackage vo) throws BaseException {
        ArrayList fpList = new ArrayList();

        WszBo wszbo = (WszBo) vo.getData();
        String jkpzh = wszbo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);
        Sbjkzb jkzbVo = new Sbjkzb();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //��ѯ�ɿ�������
            jkzbVo.setJkpzh(jkpzh);
//            jkzbVo.setJsjdm(CommonUtil.getWszJsjdm(vo.getUserData(), conn) );
            jkzbVo.setJsjdm(jsjdm);
            wszbo.setJsjdm(jsjdm);

            jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    jkzbVo, conn);

            if (jkzbVo == null) {
                throw new Exception("�Բ���û���ҵ���ýɿ����ƥ��ļ�¼������");
            }

            //����ýɿ��������
//            if(!Constants.JKS_ZWBS_DEFAULT.equals(jkzbVo.getZwbs()) )
//            {
//                throw new Exception("�ýɿ�������⣬���ܳ���������");
//            }
            String zwbs = jkzbVo.getZwbs();
            if (zwbs == null) {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }

            if (!Constants.JKS_ZWBS_DEFAULT.substring(0,
                1).equals(zwbs.substring(0, 1))
                ||
                !Constants.JKS_ZWBS_DEFAULT.substring(19).equals(zwbs.
                    substring(19))) {
                throw new Exception("�ýɿ�������⣬���ܳ���������");
            }

            //����ǻ������ɵĽɿ��飬��Ҫ�жϻ��ܵ���˰֤��jbdh�ֶ��Ƿ�Ϊnull
            //��˰֤�����jbdh�ֶ�Ϊnull��˵����δ�ᱨ�����Գ����ɿ���
            if (jkzbVo.getSbbh() == null || jkzbVo.getSbbh().equals("")) {
                ArrayList resultList = new ArrayList(); //��ѯ��������洢�Ķ�����˰֤����vo
                resultList = DAOFactory.getInstance().getQswszzDAO().query(
                        jkzbVo,
                        conn);

                //����˵�������ɵĽɿ��鲻���ܲ�ѯ������˰֤
                //�������и����⣬���ܺ����˰֤��clbjdm��Ī������Ĵ�15��Ϊ14�����Լ�������ж�
                if (resultList.isEmpty()) {
                    throw new Exception("û���ҵ��������ɸýɿ������˰֤��¼������");
                }

                Qswszz qswszz = (Qswszz) resultList.get(0);
                Debug.out("�������ܵĽɿ��飬Ҫ�ж���˰֤�Ƿ��ѽᱨ����˰֤�ţ�" + qswszz.getWszh());
                if (qswszz.getJbdh() != null && !qswszz.getJbdh().equals("")) {
                    Debug.out("�ᱨ���ţ�" + qswszz.getJbdh());
                    throw new Exception("��˰֤�ѽᱨ�����ܳ����ɿ��飡����");
                }

            }

            StringBuffer condition = new StringBuffer("");
            condition.append("jkpzh = '").append(jkpzh)
                    .append("' AND jsjdm = '").append(jsjdm).append("' ");
            Debug.out("�Ѿ�������ɳ������ܵĲ�ѯ������");

            //ͨ�����������ͽɿ��������˰֤���ܱ��в�ѯ�Ƿ��з�Ʊ�����
            //����У�ֻҪ��һ�Žɿ��������Ͷ����ܳ���������ȫ������
            Debug.out("������������Ա�ļ�������룺" + jsjdm);
            fpList = DAOFactory.getInstance().getQswszhzDAO().query(jsjdm,
                    jkpzh, conn);

            //���ֻ��һ���ɿ�ƾ֤�ţ�˵��û�з�Ʊ
            if (fpList.size() == 0) {
                throw new ApplicationException("��������û�в�ѯ������Ҫ�����Ľɿ��飡");
            } else if (fpList.size() == 1) {
                //ɾ���ɿ��������ɿ�����ϸ�����˰֤���ܱ����ؼ�¼
                DAOFactory.getInstance().getSbjkmxDAO().delete(condition, conn);
                DAOFactory.getInstance().getSbjkzbDAO().delete(condition, conn);
                DAOFactory.getInstance().getQswszhzDAO().delete(condition, conn);
                Debug.out("���ֻ��һ���ɿ�ƾ֤�ţ�˵��û�з�Ʊ��");
            }
            //����з�Ʊ�����
            else {
                for (int i = 0; i < fpList.size(); i++) {
                    condition = new StringBuffer("");
                    condition.append("jkpzh = '")
                            .append(((Qswszhz) fpList.get(i)).getJkpzh())
                            .append("' AND jsjdm = '").append(wszbo.getJsjdm()).
                            append("' ");

                    //ɾ���ɿ��������ɿ�����ϸ�����˰֤���ܱ����ؼ�¼
                    DAOFactory.getInstance().getSbjkmxDAO().delete(condition,
                            conn);
                    DAOFactory.getInstance().getSbjkzbDAO().delete(condition,
                            conn);
                    DAOFactory.getInstance().getQswszhzDAO().delete(condition,
                            conn);
                    Debug.out("����з�Ʊ�������");
                }
            }

            Debug.out("����˰֤�����е���ؼ�¼���걨���ܵ����ֶ�updateΪnull��");
            //����˰֤�����е���ؼ�¼���걨���ܵ����ֶ�updateΪnull
            StringBuffer sql = new StringBuffer("");
            sql.append("UPDATE sbdb.sb_jl_qswszz a ")
                    .append("SET a.sbhzdh = null, a.clbjdm = '")
                    .append(Constants.WSZ_CLBJDM_YWS).append(
                    "' WHERE a.sbhzdh = '")
                    .append(((Qswszhz) fpList.get(0)).getSbhzdh()).append("' ");

            DAOFactory.getInstance().getQswszzDAO().update(sql.toString(), conn);
            Debug.out("����˰֤�����е���ؼ�¼���걨���ܵ����ֶ�updateΪnull.......�ɹ���");
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * ���ɲ�������˰֤
     * VOPackage�е�Data���ԣ���ǰ̨����������ScDyWszBo�����ݶ���
     * ��Ȼ����ֻ��һ���֣������걨�����е��걨����
     * @param vo ���ݼ����󣨰���Qswszzֵ�����UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData(); //ȡ����ǰ�û�����
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = null; //������˰֤���������
        Qswszmx wszMx = null; //������˰֤��ϸ������

        ArrayList wszList = new ArrayList();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Timestamp now = CommonUtil.getDBtime(conn);

            //1��ͨ����ѯ�걨�����������ػ�����Ϣ�õ���˰֤�Ļ�����Ϣ����
            Sbzb sbzb = new Sbzb();
            sbzb.sbbh = bo.getSbbh();
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                    conn);
            Debug.out("ȡ���걨�����������Ϣ....");
            //�ж��Ƿ��Ѿ�������˰֤�Ĵ�ӡ������
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("�걨���Ϊ" + sbzb.sbbh +
                                               "�Ѿ���ӡ����˰֤�ˣ�");
            }
            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            Debug.out("ȡ�÷�����Ϣ��������Ϣ....");
            if (tufwxx == null) {
                throw new ApplicationException("���ݡ����صĻ�����Ϣ����Ϊ�գ�");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //ȡ�ü���˰���
            //BigDecimal jmszje = (BigDecimal) map.get(Constants.JE_JMSZE);
            BigDecimal jmszje = hdbo.getJmzje();
            //ȡ�úϼƼ�˰���
            //double total_je = ((BigDecimal) map.get(Constants.JE_YNSE)).doubleValue();
            double total_je = hdbo.getYnse().doubleValue();
            //�úϼƼ�˰������ÿ����˰֤����߽��ó�Ҫ�ּ���Ʊ
            int tmp = (int) Math.ceil(total_je /
                                      Double.parseDouble(Constants.WSZ_FPJE));

            Debug.out("��˰֤���У�" + tmp + "��");

            //��ʼ��Ʊ
            for (int i = 0; i < tmp; i++) {

                wszZb = new Qswszz();

                //��ʼ������˰֤����
                wszZb.setCjr(ud.getYhmc());
                wszZb.setCjrq(now);
                wszZb.setLrr(ud.getYhid()); //Ϊ���Ժ���ܵ�ʱ��Ļ������ݣ����ո��˻��ܵľ���������ֶ�
                wszZb.setLrrq(now);
                wszZb.setNd(DateUtil.getDate().substring(0, 4));
                wszZb.setClbjdm(Constants.WSZ_CLBJDM_YSB);
                wszZb.setSwjgzzjgdm(ud.getSsdwdm());
                wszZb.setSwjgzzjgmc(ud.getSsdwmc());
                wszZb.setPzzldm(Constants.WSZ_PZZLDM);
                wszZb.setJsjdm(CommonUtil.getWszJsjdm(ud, conn)); //�����˰֤�����˰����صļ��������
                wszZb.setFsdm(Constants.WSZ_FSDM); //ֻ��һ�ַ�ʽ���롪����˰����ذ���ʽ
                wszZb.setSbbh(bo.getSbbh());
                wszZb.setSwjgzzjgmc(ud.ssdwmc); //���ջ�������

                //����������˰֤�������
                wszZb.setJsfsdm(sbzb.getJsfsdm()); //��˰��ʽ����
                wszZb.setJsfsmc(sbzb.getJsfsmc()); //��˰����
                //���Ʊ֤�ʻ���������Ϣ
                Zh zh = CommonUtil.getPzzhVo(ud, conn);
                wszZb.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
                wszZb.setZsdmc(zh.zhmc); //���յ�����
                Debug.out("WszProcessor.doSave ���յ���룺" + ud.xtsbm1 + "��");

                wszZb.setFdcwz(tufwxx.getTdfwzldz()); //���ز�λ��
                wszZb.setHtclrq(tufwxx.getHtqdsj()); //���ݺ�ͬǩ��ʱ��

                //���ָ��˻��ǷǸ��ˣ���ҵ��������˰֤
                //����Ǹ��˹���ķ���
                if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                    //��ӡ��˰֤Ҫ����ѡ���Ĳ�Ȩ�˴�ӡ����˰�����ƣ������˰�������ԡ������ָ������֤������ͼ���ƴ��밴��ѡ���Ĳ�Ȩ�˴�ӡ����������Ȩ�˰�������Ȩ�˴�ӡ
                    Grxx grxx = new Grxx();
                    if (bo.getJsjdm() == null || bo.getJsjdm().equals("")) {
                        List l = (List) DAOFactory.getInstance().getGrxxDAO().
                                 getAllBySbbh(sbzb.sbbh, conn);
                        grxx = Helper.getZcqr(l);
                        bo.setNsrmc(ActionUtil.getNsrmc(l, null));
                    } else {
                        grxx.setJsjdm(bo.getJsjdm());
                        grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                               get(grxx, conn);
                    }

                    wszZb.setNsrjsjdm(grxx.getJsjdm()); //��˰�˼��������
                    wszZb.setNsrmc(bo.getNsrmc()); //��˰������
                    wszZb.setNsrdm(grxx.sfzjhm); //��˰�˴���
                    wszZb.setZjhm(grxx.getSfzjhm()); //֤������
                    wszZb.setZjlxdm(grxx.getSfzjlx()); //֤�����ʹ���
                    wszZb.setZjlxmc(grxx.sfzjlxmc); //֤����������
                    wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //��ù��ұ�׼��ҵ����
                    wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //��ù��ұ�׼��ҵ����
                    wszZb.setDjzclxdm(Constants.WSZ_DJZCLX); //�Ǽ�ע�����͡����Ǽ�ע������
                    wszZb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //�Ǽ�ע�����͡����Ǽ�ע������
                    wszZb.setFsmc(Constants.WSZ_FSMC); //�걨����ʽ����--��������˰����ذ���
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
                wszMx.setNd(DateUtil.getDate().substring(0, 4));
                //���Ԥ�㼶��
                wszMx.setYsjcdm(Constants.YSJCDM_DF); //������Ԥ�㼶�δ��룬21 �ط���
                wszMx.setYsjcmc(Constants.YSJCDM_DF_MC); //������Ԥ�㼶�δ��룬21 �ط���
                wszMx.setSkssjsrq(wszZb.getHtclrq()); //˰����������
                wszMx.setSkssksrq(wszZb.getHtclrq()); //˰����������

                //added by zhaobo 20041228
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

                System.out.println("yskm.yskmdm()��˰����ͨ������ȡ��Ԥ���Ŀ���룺" +
                                   yskm.yskmdm);
                System.out.println("wszMx.getYskmdm()��˰����ͨ������ȡ��Ԥ���Ŀ���룺" +
                                   wszMx.getYskmdm());

                //�����ֻ��һ����˰֤
                if (tmp == 1) {
                    wszZb.setYqts(new BigDecimal(0)); //��������
                    wszZb.setZnjzje(new BigDecimal(0)); //���ɽ��ܽ��
                    wszZb.setJsje(jmszje); //��ü���˰�ܶ�
                    //  wszZb.setHjsjje((BigDecimal) map.get(Constants.JE_YNSE)); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�
                    wszZb.setHjsjje(hdbo.getYnse()); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�

                    wszMx.setYjhkc(wszZb.getJsje()); //������ϸ��ֻ��һ�����ݣ������ѽɻ�۳��ֶε�������ļ�˰����ֶ�
                    wszMx.setJsje(hdbo.getJsyj()); //��˰�����ڼ�˰����
                    wszMx.setSjse(hdbo.getYnse()); //ʵ��˰�����Ӧ��˰����˼��տ��е��

                }
                //����Ƿ�Ʊ�ĵ�һ����˰֤
                else if (i == 0 && tmp != 1) {
                    wszZb.setYqts(new BigDecimal(0)); //��������
                    wszZb.setZnjzje(new BigDecimal(0)); //���ɽ��ܽ��
                    wszZb.setJsje(jmszje); //��ü���˰�ܶ�
                    wszZb.setHjsjje(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�

                    wszMx.setYjhkc(wszZb.getJsje()); //������ϸ��ֻ��һ�����ݣ������ѽɻ�۳��ֶε�������ļ�˰����ֶ�
                    wszMx.setJsje(hdbo.getJsyj()); //��˰�����ڼ�˰����
                    wszMx.setSjse(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //ʵ��˰�����Ӧ��˰����˼��տ��е��
                }
                //����Ƿ�Ʊ�����һ����˰֤
                else if (i != 0 && i == (tmp - 1)) {
                    double x = (hdbo.getYnse()).
                               doubleValue() -
                               Double.parseDouble(Constants.WSZ_FPJE) * i;
                    wszZb.setHjsjje(new BigDecimal(x)); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�
                    wszMx.setSjse(new BigDecimal(x)); //ʵ��˰�����Ӧ��˰����˼��տ��е��
                }
                //����Ƿ�Ʊ���м伸����˰֤���Ȳ��ǵ�һ��Ҳ�������һ��
                else {
                    wszZb.setHjsjje(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�
                    wszMx.setSjse(new BigDecimal(Double.parseDouble(Constants.
                            WSZ_FPJE))); //ʵ��˰�����Ӧ��˰����˼��տ��е��
                }

                wszZb.getMxList().add(wszMx);

                wszList.add(wszZb);
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
                    throw new ApplicationException("����˰�ѵļ����걨��Ϣ��ʱ�򱨴�");
                }
                Debug.out("��������걨�ɹ���");
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return wszList;
    }


    /**
     * ������˰֤
     * @param vo VOPackage
     * @throws BaseException
     */
    private void cxWsz(VOPackage vo) throws BaseException {
        QueryWszBo bo = new QueryWszBo();
        UserData ud = vo.getUserData();
        bo = (QueryWszBo) vo.getData();

        Qswszz wszVo = new Qswszz();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //��ѯ��˰֤����
            wszVo.setWszh(bo.getStartWszh());
            wszVo.setNdzb(bo.getNdzb());
            //wszVo.setPzzldm(Constants.WSZ_PZZLDM);
            wszVo.setPzzldm(bo.getPzzldm());
            System.out.println("wszProcessor.cxWsz ���������wszh=="
                               + wszVo.getWszh() + " ndzb==" + wszVo.getNdzb() +
                               " pzzldm==" + wszVo.getPzzldm());
            System.out.println("wszProcessor.cxWsz ���������pzzhdm=="
                               + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2);

            wszVo = (Qswszz) DAOFactory.getInstance().getQswszzDAO().get(wszVo,
                    conn);

            if (wszVo == null) {
                throw new Exception("�Բ���û���ҵ������˰֤��ƥ��ļ�¼������");
            }

            //�������˰֤�ѻ���
            if (wszVo.getSbhzdh() != null && !wszVo.getSbhzdh().equals("")) {
                throw new Exception("����˰֤�ѻ��ܣ����ȳ������ܽɿ����ٳ�����˰֤������");
            }

            if (wszVo.getJbdh() != null && !wszVo.getJbdh().equals("")) {
                throw new Exception("����˰֤�ѽᱨ�����ܳ���������");
            }

            //�����ϸ��˰����
            Qswszmx mxVo = new Qswszmx();
            String conditions = "where ndzb = '" + wszVo.getNdzb() +
                                "'  and pzzldm = '" + wszVo.getPzzldm() +
                                "'  and wszh = '" + wszVo.getWszh() + "' ";
            ArrayList mxList = (ArrayList) DAOFactory.getInstance().
                               getQswszmxDAO().query(conditions, conn);
            if (mxList != null && mxList.size() > 0) {
                mxVo = (Qswszmx) mxList.get(0);
                if (!mxVo.getJzbz().equals("000000")) {
                    throw new Exception("����˰֤�Ѽ��ʣ����ܳ���������");
                }
            }

            System.out.println("wszProcessor.cxWsz ��ѯ������" + conditions);
            //��ѯ��Ʊ����˰֤������У�һ����ɾ��
            ArrayList wszList = (ArrayList) DAOFactory.getInstance().
                                getQswszzDAO().query(wszVo, conn);
            for (Iterator iter = wszList.iterator(); iter.hasNext(); ) {
                wszVo = (Qswszz) iter.next();
                //���ϳ�������˰֤��
                try {
                    System.out.println(
                            "wszProcessor.cxWsz ����setCancellation������wszh=="
                            + wszVo.getWszh() + " ndzb==" + wszVo.getNdzb() +
                            " pzzldm==" + wszVo.getPzzldm());
                    ServiceProxy.setCancellation(ud, wszVo.getPzzldm(),
                                                 wszVo.getNdzb() +
                                                 wszVo.getWszh(),
                                                 DataConvert.round(wszVo.
                            getHjsjje(), 2).doubleValue(), "1", "0", "1");
                    Debug.out("���ϳɹ�....�����ϳ�������˰֤�ţ� wszh==" + wszVo.getWszh() +
                              " ndzb==" + wszVo.getNdzb());
                } catch (Exception ex) {
                    ex.printStackTrace(System.out);
                    Debug.out(ex);
                    throw new ApplicationException("������˰֤�����ͬʱ������Ʊ֤�����еĸ�Ʊ����");
                }

                StringBuffer condition = new StringBuffer("");
                condition.append("wszh = '").append(wszVo.getWszh())
                        .append("' AND ndzb = '").append(wszVo.getNdzb())
                        .append("' AND pzzldm = '").append(wszVo.getPzzldm()).
                        append("' ");
                DAOFactory.getInstance().getQswszmxDAO().delete(condition, conn);
                Debug.out("������˰֤���Ϊ" + wszVo.wszh + "��ϸ�ɹ�");
                DAOFactory.getInstance().getQswszzDAO().delete(condition, conn);
                Debug.out("������˰֤���Ϊ" + wszVo.wszh + "����ɹ�");

            }

            //�޸��걨�����״̬��ʶ
            //�ָ����Ϊ�Ѹ���
            DAOFactory.getInstance().getSbzbDAO().update(Constants.ZB_ZTBS_YFH,
                    wszVo.getSbbh(), conn);

            //ɾ�������걨�������
            HashMap map = new HashMap();
            map.put("jsjdm", wszVo.jsjdm);
            map.put("szsmdm", mxVo.getSzsmdm());
            map.put("cjrq", wszVo.cjrq);
            //ɾ�������걨�������
            if (!CommonUtil.deleteSBJM(map)) {
                throw new ApplicationException("�޷�ɾ�������걨������ݣ����ýӿڳ���");
            }
            Debug.out("���������걨�ɹ���");
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            ex.printStackTrace();
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    /**
     * �����ܵ���˰֤��Ʊ������ʵ�ɽ���ȡ�ɿ����
     * @param list ArrayList
     * @return ArrayList
     */
    private ArrayList JksFP(ArrayList list, VOPackage vo) throws Exception {
        ArrayList fpList = new ArrayList(); //���ص��ѷֺ�Ʊ����˰��˰֤������Ϣ����

        Qswszhz fpVo = new Qswszhz(); //�ѷֺ�Ʊ����˰֤���ܱ�vo���������ɽɿ�������

        WszBo bo = new WszBo();
        bo = (WszBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

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

            if (list.size() <= 4) {
                //����˰֤���ܱ�vo�������ݣ�����Ʊ
                fpVo.setCjrq(now);
                fpVo.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
                Debug.out("bo.getHzfs() is '" + bo.getHzfs() + "'");
                fpVo.setHzfs(bo.getHzfs());
                fpVo.setHzjsrq(DataConvert.String2Timestamp(bo.getHzjzrq()));
                fpVo.setHzksrq(DataConvert.String2Timestamp(bo.getHzqsrq()));
                fpVo.setHzrq(now);
                fpVo.setJsjdm(jsjdm);
                Debug.out("jsjdm is " + jsjdm);
                fpVo.setCjr(ud.getYhid());
                fpVo.setLrr(ud.getYhid());
                fpVo.setLrrq(now);
                fpVo.setNd(DateUtil.getDate().substring(0, 4));
                fpVo.setSbhzdh(sbhzdh);
                fpVo.setSwjgzzjgdm(swjgzzjgDm);
                fpVo.setHzfsmc(bo.getHzfsmc());
                fpVo.setJsfsmc(bo.getJsfsmc());
                //���Ʊ֤�ʻ���������Ϣ
                Zh zh = CommonUtil.getPzzhVo(ud, conn);
                fpVo.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
                fpVo.setZsdmc(zh.zhmc); //���յ�����

                //���˰��˰ĿС�ڵ���4��Ҳ����һ�Žɿ���
                double sjse = 0.0;
                for (int i = 0; i < list.size(); i++) {
                    sjse += ((Sbjkmx) list.get(i)).getSjse().doubleValue();
                }
                fpVo.setSjse(new BigDecimal(sjse)); //ʵ�ɽ��
                //ȡ�ýɿ�ƾ֤��
                String jkpzh = JksUtil.getJkpzh(fpVo.getJsjdm());
                Debug.out("ȡ�ýɿ�ƾ֤�� : " + jkpzh);
                fpVo.setJkpzh(jkpzh);

                fpList.add(fpVo);
            } else { //���˰��˰Ŀ����5
                for (int j = 0; j <= 1; j++) {
                    fpVo = new Qswszhz();

                    //����˰֤���ܱ�vo�������ݣ�����Ʊ
                    fpVo.setCjrq(now);
                    fpVo.setClbjdm(Constants.WSZ_CLBJDM_YJZ);
                    fpVo.setHzfs(bo.getHzfs());
                    fpVo.setHzjsrq(DataConvert.String2Timestamp(bo.getHzjzrq()));
                    fpVo.setHzksrq(DataConvert.String2Timestamp(bo.getHzqsrq()));
                    fpVo.setHzrq(now);
                    fpVo.setJsjdm(jsjdm);
                    fpVo.setCjr(ud.getYhid());
                    fpVo.setLrr(ud.getYhid());
                    fpVo.setLrrq(now);
                    fpVo.setNd(DateUtil.getDate().substring(0, 4));
                    fpVo.setSbhzdh(sbhzdh);
                    fpVo.setSwjgzzjgdm(swjgzzjgDm);
                    fpVo.setHzfsmc(bo.getHzfsmc());
                    fpVo.setJsfsmc(bo.getJsfsmc());
                    //���Ʊ֤�ʻ���������Ϣ
                    Zh zh = CommonUtil.getPzzhVo(ud, conn);
                    fpVo.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
                    fpVo.setZsdmc(zh.zhmc); //���յ�����

                    double sjse = 0.0;
                    if (j == 0) {
                        for (int i = 0; i < 4; i++) { //��ȡǰ4�����һ�Žɿ���
                            sjse += ((Sbjkmx) list.get(i)).getSjse().
                                    doubleValue();
                        }
                    } else {
                        sjse += ((Sbjkmx) list.get(4)).getSjse().doubleValue();
                    }

                    //ʵ�ɽ��
                    fpVo.setSjse(new BigDecimal(sjse));

                    //ȡ�ýɿ�ƾ֤��
                    String jkpzh = JksUtil.getJkpzh(fpVo.getJsjdm());
                    Debug.out("�ɿ�ƾ֤�ţ�" + jkpzh);
                    fpVo.setJkpzh(jkpzh);

                    fpList.add(fpVo);
                }
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw new Exception("�ɿ����Ʊ�������󣡣���");
        }

        return fpList;
    }

    /**
     * ��˰֤��ӡ��update�����Ǵ���ķ���
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Qswszz wszVo = new Qswszz();
        wszVo = (Qswszz) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer(" WHERE ");

            condition.append("wszh = '").append(wszVo.getWszh()).append("' ")
                    .append("AND ndzb = '").append(wszVo.getNdzb()).append("' ")
                    .append(" AND pzzldm = '").append(wszVo.getPzzldm()).
                    append("' ");

            DAOFactory.getInstance().getQswszzDAO().update(condition, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

    private Object doPrint(VOPackage vo) throws BaseException {
        UserData ud = vo.getUserData(); //ȡ����ǰ�û�����
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = bo.getQswszzVo(); //������˰֤���������
        Qswszmx wszMx = bo.getMxVo(); //������˰֤��ϸ������

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            //�����˰֤��
            try {
                System.out.println("wszProcessor.doPrint ���������pzzhdm=="
                                   + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2 +
                                   " pzzldm==" + wszZb.getPzzldm());
                String retResult = ServiceProxy.getNumber(ud,
                        wszZb.getPzzldm(),
                        DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                        "1", "1");
                wszZb.setNdzb(retResult.substring(0, 4)); //����ֱ�
                wszZb.setWszh(retResult.substring(4)); //��˰֤��
                wszMx.setWszh(wszZb.getWszh());
                wszMx.setNdzb(wszZb.getNdzb()); //����ֱ�
                Debug.out("ȡ����˰��˰֤��...." + wszZb.wszh + "  " + wszZb.ndzb);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�");
            }

            //������������
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("������˰֤����ɹ�....");

                DAOFactory.getInstance().getQswszmxDAO().insert(wszMx, conn);
                Debug.out("������˰֤��ϸ�ɹ�....");

                //�����걨����״̬λ
                String ztbs = Constants.ZB_ZTBS_YRK;
                DAOFactory.getInstance().getSbzbDAO().update(ztbs, wszZb.sbbh,
                        conn);
                Debug.out("�����걨����״̬λ�ɹ������Ϊ������⡱....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("������˰֤�����ݿ�ʧ�ܣ�");
            }
            bo.setQswszzVo(wszZb);
            bo.setMxVo(wszMx);
        } catch (Exception ex) {
            //���治�ɹ��������ϸո�ȡ������˰֤�ţ�
            try {
                //ֻ�е��Ѿ�ȡ����˰֤�ź��������Ĳ������������Ҫ����ȡ������˰֤��
                if (wszZb.getWszh() != null && !wszZb.getWszh().equals("")) {
                    ServiceProxy.setCancellation(
                            ud, wszZb.getPzzldm(),
                            wszZb.getNdzb() + wszZb.getWszh(),
                            DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                            "1", "0", "1");
                    Debug.out("���治�ɹ�....������ȡ����˰֤�ţ�");

                }
            } catch (Exception ex1) {
                ex1.printStackTrace();
                throw ExceptionUtil.getBaseException(ex);
            }

            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����ӡ��������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private Object doChangePrint(VOPackage vo) throws BaseException {

        UserData ud = vo.getUserData(); //ȡ����ǰ�û�����
        QueryWszBo bo = (QueryWszBo) vo.getData();

        Qswszz wszZb = bo.getQswszzVo(); //������˰֤���������

        String old_wszh = wszZb.getWszh();
        String old_ndzb = wszZb.getNdzb();
        String old_pzzldm = wszZb.getPzzldm();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            try {
                System.out.println("wszProcessor.doChangePrint ���������old_wszh=="
                                   + old_wszh + " old_ndzb==" + old_ndzb +
                                   " pzzhdm=="
                                   + ud.xtsbm1 + " pzzhmc==" + ud.xtsbm2 +
                                   " pzzldm==" + old_pzzldm);
                //ֻ�е��Ѿ�ȡ����˰֤�ź��������Ĳ������������Ҫ����ȡ������˰֤��
                if (old_wszh != null && !old_wszh.equals("")) {
                    ServiceProxy.setCancellation(
                            ud, old_pzzldm, old_ndzb + old_wszh,
                            DataConvert.round(wszZb.getHjsjje(), 2).doubleValue(),
                            "1",
                            "0", "1");
                    Debug.out("���治�ɹ�....������ȡ����˰֤�ţ�");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("��Ŵ�ӡʱ������˰֤ʧ�ܣ�");
            }

            try {
                //�����˰֤��
                String retResult = ServiceProxy.getNumber(ud,
                        wszZb.getPzzldm(),
                        DataConvert.round(wszZb.
                                          getHjsjje(), 2).doubleValue(), "1",
                        "1");

                wszZb.setNdzb(retResult.substring(0, 4)); //����ֱ�
                wszZb.setWszh(retResult.substring(4)); //��˰֤��

                Debug.out("ȡ����˰��˰֤��...." + wszZb.wszh + "  " + wszZb.ndzb);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("��Ŵ�ӡʱ��ȡ��˰֤ʧ�ܣ�");
            }

            //����һ���µ���������
            try {
                //�����������Ҫ������˰֤������insertһ���µ����ݣ����ź����˰֤��
                DAOFactory.getInstance().getQswszzDAO().insert(wszZb, conn);
                Debug.out("��˰֤���Ų�����˰֤����ɹ�....old_wszh==" + old_wszh +
                          " new_wszh==" + wszZb.wszh);
            } catch (BaseException ex1) {
                ex1.printStackTrace();
                Debug.out(ex1);
                throw new ApplicationException("��Ŵ�ӡʱ��˰֤���Ų���������Ϣ����");
            }

            String sql = "UPDATE sbdb.sb_jl_qswszmx t SET t.wszh = '" +
                         wszZb.getWszh() + "' WHERE t.wszh = '" + old_wszh
                         + "' AND t.ndzb = '" + old_ndzb + "' AND t.pzzldm = '" +
                         old_pzzldm + "' ";
            //������ϸ������
            try {
                //Ȼ�������ϸ���е�����
                DAOFactory.getInstance().getQswszmxDAO().update(sql, conn);
                Debug.out("��˰֤���Ÿ�����˰֤��ϸ�ɹ�....");
                Debug.out(sql);

            } catch (BaseException ex6) {
                ex6.printStackTrace();
                throw new ApplicationException("��Ŵ�ӡʱ��˰֤���Ÿ�����ϸ����Ϣ����");
            }

            sql = "DELETE sbdb.sb_jl_qswszz t WHERE t.wszh = '" + old_wszh
                  + "' AND t.ndzb = '" + old_ndzb + "' AND t.pzzldm = '" +
                  old_pzzldm + "' ";
            //ɾ��������ԭ��������
            try {
                //���ɾ��������ԭ��������
                DAOFactory.getInstance().getQswszzDAO().update(sql, conn);
                Debug.out("��˰֤����ɾ����˰֤����ɹ�....");
                Debug.out(sql);
            } catch (BaseException ex1) {
                ex1.printStackTrace();
                Debug.out(ex1);
                throw new ApplicationException("��Ŵ�ӡʱ��˰֤����ɾ��������Ϣ����");
            }

        } catch (Exception ex) {
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�WszProcessor����Ŵ�ӡ��������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    public static void main(String[] args) {
        VOPackage vo = new VOPackage();
        //���Ի��ܵ�bo
        /*
                 HzWszBo bo = new HzWszBo();
                 bo.setHzfs("1");
                 bo.setJsfs("1");
                 bo.setHzqsrq("2004-12-01");
                 bo.setHzjzrq("2004-12-31");*/

        //���Գ����ɿ����bo
        /*CxJksBo bo = new CxJksBo();
                 bo.setJkpzh("069990100412018");
                 bo.setJsjdm("06999010");*/

        //���Գ�����˰֤��bo
        WszBo bo = new WszBo();
        bo.setNdzb("2004");
        bo.setWszh("22222222");

        UserData ud = new UserData();
        ud.yhid = "hanl";
        ud.ssdwdm = "0601";
        ud.yhmc = "����";
        ud.xtsbm1 = "0106010017";

        vo.setData(bo);
        vo.setUserData(ud);

        WszProcessor p = new WszProcessor();
        try {
            p.cxWsz(vo);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}

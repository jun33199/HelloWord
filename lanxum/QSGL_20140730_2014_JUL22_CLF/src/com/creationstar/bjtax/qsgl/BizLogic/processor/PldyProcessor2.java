package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.dao.FgrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.GrxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblcqDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbzbDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SpjgxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.TufwxxDAO;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo2;
import com.creationstar.bjtax.qsgl.model.bo.PldyBo2;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
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
 *
 * ˵������Processor�����ҳ����"��������(˰����Ա)"ģ��
 */
public class PldyProcessor2 extends CommonProcessor {
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;
        PldyBo2 bo = (PldyBo2) vo.getData();
        // ���κ�
        String pch = bo.getPch();
        // �ṩ������
        String sjtgzmc = bo.getTgzgjmc();
        // �ṩ�߼��������
        String sjtgzjsjdm = bo.getTgzjsjdm();
        // ����ʱ����
        String drsjBegin = bo.getDrsjBegin();
        // ����ʱ��ֹ
        String drsjEnd = bo.getDrsjEnd();
        Connection conn = null;

        String where =
                " where rownum<202 and  a.sjly='01' and c.sjly='04' and a.drpch=c.drpch";
        if (vo == null) {
            throw new ApplicationException("VOPackage�ǿ�ָ��!!!");
        }
        try {
            conn = QSDBUtil.getConnection();
            PreparedStatement ps = null;
            switch (vo.getAction()) {
            // ȡ��������Ϣ
            case ActionType.QUERY:

                // �����ṩ������
                if (sjtgzmc != null && !sjtgzmc.trim().equals("")) {
                    where += (" and a.tgzmc='" + sjtgzmc + "'");
                }

                // �ṩ�߼��������
                if (sjtgzjsjdm != null && !sjtgzjsjdm.trim().equals("")) {
                    where += (" and a.tgzjsjdm='" + sjtgzjsjdm + "'");
                }

                // ������
                if (pch != null && !pch.trim().equals("")) {
                    where += (" and a.drpch='" + pch + "'");
                }

                // ����ʱ���
                if (drsjBegin != null && !drsjBegin.trim().equals("")) {
                    where += (" and a.drsj>=TO_DATE('" + drsjBegin +
                              "','yyyy-mm-dd hh24:mi:ss')");
                }
                if (drsjEnd != null && !drsjEnd.trim().equals("")) {
                    where += (" and a.drsj<=TO_DATE('" + drsjEnd +
                              " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
                }
                try {
                    String sql =
                            "select distinct a.* from QSDB.QS_JL_DRPCINFO a,QSDB.QS_JL_SBZB c ";
                    //��������Ȩ�޿���
                    UserData ud = vo.getUserData();
                    String datafilter = ZKAdapter.getInstance().
                                        getDataFilterString(
                                                ud, "QSDB", "QS_JL_DRPCINFO");
                    Debug.out("datafilter: " + datafilter);
                    where += (" and " + datafilter);

                    ps = conn.prepareStatement(sql + where);
                    ResultSet rs = ps.executeQuery();
                    ArrayList resultList = new ArrayList();
                    while (rs.next()) {
                        Drpcinfo tmpDrpcinfo = new Drpcinfo();
                        tmpDrpcinfo.setDrpch(rs.getString("drpch"));
                        tmpDrpcinfo.setTgzmc(rs.getString("tgzmc"));
                        tmpDrpcinfo.setTgzgjmc(rs.getString("tgzgjmc"));
                        tmpDrpcinfo.setTgzjsjdm(rs.getString("tgzjsjdm"));
                        tmpDrpcinfo.setTjsj(rs.getTimestamp("tjsj"));
                        tmpDrpcinfo.setDrsj(rs.getTimestamp("drsj"));
                        tmpDrpcinfo.setJsfmc(rs.getString("jsfmc"));
                        resultList.add(tmpDrpcinfo);
                    }
                    result = resultList;
                } catch (SQLException e) {
                    throw e;
                }
                finally {
                    ps.close();
                }
                return result;
                // ȡ���걨������Ϣ
            case ActionType.GET:
                result = getPldrList(pch, conn);
                return result;
                // ��ӡ�����״̬��ʶ
            case ActionType.UPDATE:
                String sql =
                        "UPDATE qsdb.qs_jl_sbzb SET ztbs = ? WHERE drpch = ? ";
                ps = null;
                try {
                    ps = conn.prepareStatement(sql);
                    ps.setString(1, Constants.ZB_ZTBS_DY);
                    ps.setString(2, pch);
                    ps.execute();
                } catch (SQLException e) {
                    throw e;
                }
                finally {
                    ps.close();
                }
                return null;
            default:
                throw new ApplicationException(
                        "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�PldyProcessor2����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    private ArrayList getPldrList(String pch, Connection conn) throws
            SQLException, ApplicationException {
        ArrayList sbzbList = SbzbDAO.query(" where drpch='" + pch +
                                           "'", conn);
        ArrayList resultList = new ArrayList();
        for (int i = 0; i < sbzbList.size(); i++) {
            Sbzb tmpSbzb = (Sbzb) sbzbList.get(i);
            // ȡ���걨���
            String sbbh = tmpSbzb.getSbbh();
            // ������Ҫ���ص�PldrBo
            PldrBo2 tmpPldrBo = new PldrBo2();
            // ����sbzb
            tmpPldrBo.setSbzb(tmpSbzb);
            // �������κ�
            tmpPldrBo.setPch(pch);

            // �жϸ��˻�Ǹ���
            if (tmpSbzb.getYhbs().equals(Constants.YHBZ_GR)) {
                tmpPldrBo.setGrxx((Grxx) GrxxDAO.getOneBySbbh(sbbh, conn));
            } else {
                tmpPldrBo.setFgrxx((Fgrxx) FgrxxDAO.getBySbbh(sbbh, conn));
            }
            //���������Ϣ
            Spjgxx spjgxx = (Spjgxx) SpjgxxDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setSpjgxx(spjgxx);
            //���ط�����Ϣ
            Tufwxx tufwxx = (Tufwxx) TufwxxDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setTufwxx(tufwxx);
            //��Ǩ��Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblcq����)
            ArrayList cqxxList = JsblcqDAO.queryBySbbh(sbbh, conn);
            tmpPldrBo.setCqxxList(cqxxList);
            //����ס����Ϣ(Arryalist�е�ÿ��Ԫ�ض���jsblgyzf����)
            ArrayList gyzfxxList = JsblgyzfDAO.getBySbbh(sbbh, conn);
            tmpPldrBo.setGyzfxxList(gyzfxxList);
            //���ݽ�����Ϣ(Arryalist�е�ÿ��Ԫ�ض���PldrBo����,����û�в�Ǩ�͹���)
            /** @todo û�з��ݽ�����Ϣ */
            tmpPldrBo.setFwjhxxList(new ArrayList());
//          ArrayList fwjhxxList;

            tmpPldrBo.setHdbo(CommonUtil.getJZSE2(tmpPldrBo, conn));

            resultList.add(tmpPldrBo);
        }
        return resultList;
    }
}

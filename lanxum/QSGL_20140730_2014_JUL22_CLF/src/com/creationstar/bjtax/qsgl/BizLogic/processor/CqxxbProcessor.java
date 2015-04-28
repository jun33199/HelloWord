package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxErr;
import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.PlcxMxBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.RegexUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author llw
 *
 */
public class CqxxbProcessor extends CommonProcessor {

    /*
     * ���� Javadoc��
     *
     * @see com.ttsoft.framework.processor.Processor#process(com.ttsoft.framework.util.VOPackage)
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage�ǿ�ָ��!!!");
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            result = doQueryAll(vo);
            break;
        case ActionType.GET:
            result = doGet(vo);
            break;
        case ActionType.INSERT:
            doSave(vo);
            break;

        case ActionType.PRINT_CQQKB:
            result = doPrint(vo);
            break;
        case ActionType.MODIFY:
            doUpdate(vo);
            break;
        case ActionType.DELETE:

            doDelete(vo);

            break;
        case ActionType.UPLOAD_FILE:

            result = doUploadExcel(vo);

            break;
        case ActionType.QUERYERR:

            result = doQueryErr(vo);

            break;
        case ActionType.SAVEERR:

            result = doSaveErr(vo);

            break;

        default:
            throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * doQueryAll
     *
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doQueryAll(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); // DAO��ѯ���صĽ����

        Cqxxb bo = (Cqxxb) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;
        try {

            String conditions = " WHERE 1=1 ";

            if (bo.getSzqx() != null && !"".equals(bo.getSzqx().trim())
                && !"00".equals(bo.getSzqx().trim())) {

                conditions += " AND QXDM='" + bo.getSzqx() + "'";

            }

            if (bo.getBcqrlxdm() != null && !"".equals(bo.getBcqrlxdm().trim())
                && !"00".equals(bo.getBcqrlxdm().trim())) {

                conditions += " AND BCQRLXDM='" + bo.getBcqrlxdm() + "'";

            }

            if (bo.getBcqrmc() != null && !bo.getBcqrmc().trim().equals("")) {
                conditions += " AND BCQRMC='" + bo.getBcqrmc() + "'";
            }
            if (bo.getZjhm() != null && !bo.getZjhm().trim().equals("")) {
                conditions += " AND ZJHM='" + bo.getZjhm() + "'";
            }
            if (bo.getCqxxdz() != null && !bo.getCqxxdz().trim().equals("")) {
                conditions += " AND CQXXDZ='" + bo.getCqxxdz() + "'";
            }
            if (bo.getBcjeBegin() != null
                && !bo.getBcjeBegin().trim().equals("")) {
                conditions += " and BCJE>=" + bo.getBcjeBegin();
            }
            if (bo.getBcjeEnd() != null && !bo.getBcjeEnd().trim().equals("")) {
                conditions += " and bcje<=" + bo.getBcjeEnd();
            }
            if (bo.getLrrqBegin() != null
                && !bo.getLrrqBegin().trim().equals("")) {
                conditions += " and LRRQ >= TO_DATE('" + bo.getLrrqBegin()
                        + " 00:00:00', 'YYYYMMDD HH24:MI:SS')";
            }
            if (bo.getLrrqEnd() != null && !bo.getLrrqEnd().trim().equals("")) {
                conditions += " and LRRQ <= TO_DATE('" + bo.getLrrqEnd()
                        + " 00:00:00', 'YYYYMMDD HH24:MI:SS')";
            }

            // && !ud.getYhjb().equals(sjjb)
            if (bo.getSfwh().equals("1")) {
                conditions += " and lrr='" + ud.getYhid() + "'";

                if (ud.yhjb.equals(CodeConstants.JBDM_FJ)) {
                    // �־�Ȩ��
                    // �������־ֵ������в���Ȩ��
                    String fj = ud.ssdwdm;
                    fj = fj.substring(0, 2);
                    conditions += " and  swjgzzjgdm " + " like '" + fj + "%'";

                } else if (ud.yhjb.equals(CodeConstants.JBDM_SWSJ)
                           || ud.yhjb.equals(CodeConstants.JBDM_ZSDJ))

                {
                    // ˰����Ȩ�ޡ����յ㼶��
                    // ֻ���Լ�������˰���������յ�������в���Ȩ��
                    conditions += " and  swjgzzjgdm " + "='" + ud.ssdwdm + "'";

                }
            }

            if (bo.getCqxmmc() != null && !"".equals(bo.getCqxmmc().trim())) {

                conditions += " AND CQXMMC like '%" + bo.getCqxmmc() + "%'";
            }

            // ����DAO��ѯ���ݿ�
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getCqxxbDAO().query(
                    conditions, conn);

            for (int i = 0; i < resultList.size(); i++) {
                Cqxxb cqxxbTmp = (Cqxxb) resultList.get(i);
                String zgswjgmc = CommonUtil.getSwjgzzjg(
                        cqxxbTmp.getSwjgzzjgdm(), conn).getSwjgzzjgmc();
                cqxxbTmp.setSwjgzzjgmc(zgswjgmc);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * doQueryErr
     *
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doQueryErr(VOPackage vo) throws BaseException {

        ArrayList resultList = new ArrayList(); // DAO��ѯ���صĽ����

        Cqxxb bo = (Cqxxb) vo.getData();

        UserData ud = vo.getUserData();

        StringBuffer condition = new StringBuffer();

        Map lmReturnData = new HashMap(); // ���ص����ݼ���

        String message = "";

        // ��̨��ѯ�������Ľ���Ľ����(���в�Ǩ��Ϣ)
        ArrayList resultListErrIs = new ArrayList();

        // ��̨��ѯ�������Ľ���Ľ����(�޶�Ӧ��Ǩ��Ϣ)
        ArrayList resultListErrNo = new ArrayList();

        Connection conn = null;
        try {

            String conditions = " WHERE 1=1 ";

            if (bo.getSzqx() != null && !"".equals(bo.getSzqx().trim())
                && !"00".equals(bo.getSzqx().trim())) {

                conditions += " AND QXDM='" + bo.getSzqx() + "'";

            }

            if (bo.getBcqrlxdm() != null && !"".equals(bo.getBcqrlxdm().trim())
                && !"00".equals(bo.getBcqrlxdm().trim())) {

                conditions += " AND BCQRLXDM='" + bo.getBcqrlxdm() + "'";

            }

            if (bo.getBcqrmc() != null && !bo.getBcqrmc().trim().equals("")) {
                conditions += " AND BCQRMC='" + bo.getBcqrmc() + "'";
            }
            if (bo.getZjhm() != null && !bo.getZjhm().trim().equals("")) {
                conditions += " AND ZJHM='" + bo.getZjhm() + "'";
            }
            if (bo.getCqxxdz() != null && !bo.getCqxxdz().trim().equals("")) {
                conditions += " AND CQXXDZ='" + bo.getCqxxdz() + "'";
            }
            if (bo.getBcjeBegin() != null
                && !bo.getBcjeBegin().trim().equals("")) {
                conditions += " and BCJE>=" + bo.getBcjeBegin();
            }
            if (bo.getBcjeEnd() != null && !bo.getBcjeEnd().trim().equals("")) {
                conditions += " and bcje<=" + bo.getBcjeEnd();
            }
            if (bo.getLrrqBegin() != null
                && !bo.getLrrqBegin().trim().equals("")) {
                conditions += " and LRRQ >= TO_DATE('" + bo.getLrrqBegin()
                        + " 00:00:00', 'YYYYMMDD HH24:MI:SS')";
            }
            if (bo.getLrrqEnd() != null && !bo.getLrrqEnd().trim().equals("")) {
                conditions += " and LRRQ <= TO_DATE('" + bo.getLrrqEnd()
                        + " 00:00:00', 'YYYYMMDD HH24:MI:SS')";
            }

            if (bo.getCqxmmc() != null && !"".equals(bo.getCqxmmc().trim())) {

                conditions += " AND CQXMMC like '%" + bo.getCqxmmc() + "%'";
            }

            if (ud.yhjb.equals(CodeConstants.JBDM_FJ)) {
                // �־�Ȩ��
                // �������־ֵ������в���Ȩ��
                String fj = ud.ssdwdm;
                fj = fj.substring(0, 2);
                conditions += " and  swjgzzjgdm " + " like '" + fj + "%'";

            } else if (ud.yhjb.equals(CodeConstants.JBDM_SWSJ)
                       || ud.yhjb.equals(CodeConstants.JBDM_ZSDJ))

            {
                // ˰����Ȩ�ޡ����յ㼶��
                // ֻ���Լ�������˰���������յ�������в���Ȩ��
                conditions += " and  swjgzzjgdm " + "='" + ud.ssdwdm + "'";

            }

            // ����DAO��ѯ���ݿ�
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getCqxxCwbDAO().query(
                    conditions, conn);

            if (resultList.size() > Constants.XGDRCWXX_QUERYSIZE_DR) {
                message = "����ǰ�Ĳ�ѯ�������ڿ��ɣ����½��������δ��ȫ��ʾ����������С��ѯ��Χ����һ����ѯ��";
                resultList = new ArrayList(resultList.subList(0,
                        Constants.XGDRCWXX_QUERYSIZE_DR));

            } else {
                message = "��ѯ�ɹ���";
            }

            for (int i = 0; i < resultList.size(); i++) {

                CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo) resultList
                                               .get(i);

                String zgswjgmc = CommonUtil.getSwjgzzjg(
                        cqxxberrTmp.getSwjgzzjgdm(), conn).getSwjgzzjgmc();

                cqxxberrTmp.setSwjgzzjgmc(zgswjgmc);

                // ��ʽ���ݼ�������ʽ���ݽ������������
                if (Constants.CQXXCWB_CWKX_01.equals(cqxxberrTmp.getCwlx())) {

                    // Cqxxb������+���֤��+��ַ��ͬ��ȡ���ŵ�resultListCqxx��
                    condition.delete(0, condition.length());

                    condition.append(
                            " where BCQRMC = '" + cqxxberrTmp.getBcqrmc()
                            + "' ").append(
                                    " and ZJHM ='" + cqxxberrTmp.getZjhm() +
                                    "' ")
                            .append(
                                    " and CQXXDZ='" + cqxxberrTmp.getCqxxdz()
                                    + "'");

                    ArrayList arrayListCqxx1 = DAOFactory.getInstance()
                                               .getCqxxbDAO().query(condition.
                            toString(), conn);

                    // Cqxxb������+���֤��+��ַ��ͬ��ȡ���ŵ�resultListCqxx��
                    if (arrayListCqxx1.size() > 0) {

                        Cqxxb cqxxbre = (Cqxxb) arrayListCqxx1.get(0);

                        CqxxErr cqxxErr = new CqxxErr();

                        cqxxErr.setCqxxb(cqxxbre);

                        cqxxErr.setCqxxImportErrbvo(cqxxberrTmp);

                        resultListErrIs.add(cqxxErr);

                    } else {

                        condition.delete(0, condition.length());

                        condition.append(" where zjhm='"
                                         + cqxxberrTmp.getZjhm() + "'");

                        ArrayList arrayListCqxx2 = DAOFactory.getInstance()
                                .getCqxxbDAO()
                                .query(condition.toString(), conn);
                        // ���֤������ͬ��������һ������������ȡ���ŵ�resultListCqxx
                        if (arrayListCqxx2.size() > 0) {

                            Cqxxb cqxxbre = (Cqxxb) arrayListCqxx2.get(0);

                            CqxxErr cqxxErr = new CqxxErr();

                            cqxxErr.setCqxxb(cqxxbre);

                            cqxxErr.setCqxxImportErrbvo(cqxxberrTmp);

                            resultListErrIs.add(cqxxErr);

                        } else {

                            resultListErrNo.add(cqxxberrTmp);

                        }

                    }

                } else if (Constants.CQXXCWB_CWKX_02 // ���֤����ͬ��������һ��
                           .equals(cqxxberrTmp.getCwlx())) {

                    condition.delete(0, condition.length());

                    condition.append(" where zjhm='" + cqxxberrTmp.getZjhm()
                                     + "'");

                    ArrayList arrayListCqxx3 = DAOFactory.getInstance()
                                               .getCqxxbDAO().query(condition.
                            toString(), conn);
                    // ���֤������ͬ��������һ������������ȡ���ŵ�resultListCqxx
                    if (arrayListCqxx3.size() > 0) {

                        Cqxxb cqxxbre = (Cqxxb) arrayListCqxx3.get(0);

                        CqxxErr cqxxErr = new CqxxErr();

                        cqxxErr.setCqxxb(cqxxbre);

                        cqxxErr.setCqxxImportErrbvo(cqxxberrTmp);

                        resultListErrIs.add(cqxxErr);

                    } else {

                        resultListErrNo.add(cqxxberrTmp);

                    }

                }

            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        lmReturnData.put("message", message);
        lmReturnData.put("resultListErr", resultList);
        lmReturnData.put("resultListErrIs", resultListErrIs);
        lmReturnData.put("resultListErrNo", resultListErrNo);

        return lmReturnData;
    }

    /**
     * doSaveErr
     *
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doSaveErr(VOPackage vo) throws BaseException {

        Map hash = (HashMap) vo.getData();

        Map hashre = new HashMap(); // ���ص����ݼ���

        String message = "";

        // �����list
        ArrayList list = new ArrayList();

        Connection conn = null;

        try {

            // ����DAO��ѯ���ݿ�
            conn = QSDBUtil.getConnection();

            // ���� �޸���ʽ�����ݣ�ɾ����������� Is ������������ʽ��
            list = (ArrayList) hash.get("fgIs");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxErr cqxxErrtmp = (CqxxErr) list.get(i);

                    // ���ݲ�Ǩ��Ϣ��Ĳ�Ǩ��Ÿ��²�Ǩ������
                    Cqxxb cqxxbtmp = cqxxErrtmp.getCqxxb();

                    CqxxImportErrbvo cqxxberrTmp = cqxxErrtmp
                            .getCqxxImportErrbvo();

                    DAOFactory.getInstance().getCqxxbDAO().update(cqxxbtmp,
                            conn);

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            // ���� ɾ����������� Is ������������ʽ��
            list = (ArrayList) hash.get("fqIs");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxErr cqxxErrtmp = (CqxxErr) list.get(i);

                    // ���ݲ�Ǩ��Ϣ��Ĳ�Ǩ��Ÿ��²�Ǩ������
                    CqxxImportErrbvo cqxxberrTmp = cqxxErrtmp
                            .getCqxxImportErrbvo();

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            // ���� ������ʽ�����ݣ�ɾ����������� No ��������û������ʽ��
            list = (ArrayList) hash.get("fgNo");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo) list
                            .get(i);

                    Cqxxb cqxxbtmp = this.getCqxxb(cqxxberrTmp);

                    DAOFactory.getInstance().getCqxxbDAO().insert(cqxxbtmp,
                            conn);

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            // ���� ɾ����������� No ��������û������ʽ��
            list = (ArrayList) hash.get("fqNo");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo) list
                            .get(i);

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            message = "�����ɹ��� ";

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        hashre.put("message", message);

        return hashre;
    }

    /**
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doQueryDetail(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); // DAO��ѯ���صĽ����

        PlcxMxBo bo = (PlcxMxBo) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getPlcxDAO().getDetail(bo,
                    conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doGet(VOPackage vo) throws BaseException {

        String bh = (String) vo.getData();
        Cqxxb bo = null;
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            bo = DAOFactory.getInstance().getCqxxbDAO().get(bh, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰��Ǩ��Ϣ��CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    /**
     * @param vo
     * @throws BaseException
     */
    public void doSave(VOPackage vo) throws BaseException {
        Cqxxb cqxxb = (Cqxxb) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String cqxxbh = CommonUtil.getCqxxbh(conn);
            cqxxb.setCqxxbh(cqxxbh);
            DAOFactory.getInstance().getCqxxbDAO().insert(cqxxb, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * @param vo
     * @throws BaseException
     */
    public void doDelete(VOPackage vo) throws BaseException {
        ArrayList cqxxbh = (ArrayList) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            DAOFactory.getInstance().getCqxxbDAO().delete(cqxxbh, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * @param vo
     * @return
     * @throws BaseException
     */
    public Object doPrint(VOPackage vo) throws BaseException {
        Cqxxb result = new Cqxxb(); // DAO��ѯ���صĽ����

        Cqxxb bo = (Cqxxb) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            String cqxxbh = bo.getCqxxbh();
            if (cqxxbh != null && !cqxxbh.trim().equals("")) {
                String conditions = " WHERE CQXXBH='" + cqxxbh + "'";
                result = (Cqxxb) DAOFactory.getInstance().getCqxxbDAO().query(
                        conditions, conn).get(0);
                String zgswjgmc = CommonUtil.getSwjgzzjg(
                        result.getSwjgzzjgdm(), conn).getSwjgzzjgmc();
                result.setSwjgzzjgmc(zgswjgmc);
                return result;
            } else {
                throw new ApplicationException("��Ǩ��Ϣ���Ϊ�գ�");
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * @param vo
     * @throws BaseException
     */
    public void doUpdate(VOPackage vo) throws BaseException {
        Cqxxb cqxxb = (Cqxxb) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            DAOFactory.getInstance().getCqxxbDAO().update(cqxxb, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * �����ϴ���excel
     *
     *
     * @param vo
     * @throws BaseException
     */
    public Object doUploadExcel(VOPackage vo) throws BaseException {

        Map loData = (HashMap) vo.getData();

        ArrayList cqxxList = (ArrayList) loData.get("new");

        Map lmReturnData = new HashMap(); // ���ص����ݼ���
        List llErrorRecords = new ArrayList(); // ��ʽ��������ݼ���
        List llSucceedRecords = new ArrayList(); // �ѵ������ȷ������
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            for (int i = 0; i < cqxxList.size(); i++) {
                // ��ò�Ǩ��Ϣ���
                String cqxxbh = "";
                boolean cqxx = true;
                StringBuffer condition = new StringBuffer();
                CqxxImportErrbvo cqxxImportErrbvo = (CqxxImportErrbvo) cqxxList
                        .get(i);
                Cqxxb cqxxb = new Cqxxb();

                // ��ʽ���ݼ�������ʽ���ݽ�����������ַŵ����������д��ֹ�����

                String[] patternStr = new String[6];
                patternStr[0] = "[1-9]?\\d*";
                patternStr[1] = "[1-9]\\d*\\.?\\d*";
                patternStr[2] = "0\\.?\\d*";

                if ((cqxxImportErrbvo.getCqmj() == null)
                    || (cqxxImportErrbvo.getZsfwjs() == null)
                    || !(RegexUtil.isRegex(patternStr[0], cqxxImportErrbvo
                                           .getZsfwjs()))

                    || !(RegexUtil.isRegex(patternStr[1], cqxxImportErrbvo
                                           .getCqmj().toString()) ||
                         RegexUtil.isRegex(
                                 patternStr[2], cqxxImportErrbvo.getCqmj()
                                 .toString()))) {

                    cqxxImportErrbvo.setCwlx(Constants.CQXXCWB_CWKX_01);

                    cqxxImportErrbvo.setCwlxmc(Constants.CQXXCWB_CWKX_01_CH);

                    cqxxbh = CommonUtil.getCqxxbh(conn);

                    cqxxImportErrbvo.setCqxxbh(cqxxbh);

                    DAOFactory.getInstance().getCqxxCwbDAO().insert(
                            cqxxImportErrbvo, conn);
                    cqxx = false;

                    llErrorRecords.add(cqxxImportErrbvo);
                }

                // �������֤
                //���������ֱ֤�ӱ���
                if (cqxxImportErrbvo.getZjhm() != null
                    && !("".equals(cqxxImportErrbvo.getZjhm()))
                    && !("0".equals(cqxxImportErrbvo.getZjhm()))
                    && !(" ".equals(cqxxImportErrbvo.getZjhm()))) {

                    // ���֤������ͬ��������һ����ŵ����������д��ֹ�����
                    condition.delete(0, condition.length());

                    condition.append(" where zjhm='"
                                     + cqxxImportErrbvo.getZjhm() + "'");

                    ArrayList arrayListCqxx = DAOFactory.getInstance()
                                              .getCqxxbDAO().querySfzhmIstwo(
                            condition.toString(), conn);

                    if (arrayListCqxx.size() > 0) {

                        Cqxxb cqxxbre = (Cqxxb) arrayListCqxx.get(0);

                        if (!(cqxxImportErrbvo.getBcqrmc().equals(cqxxbre
                                .getBcqrmc()))) {

                            cqxxImportErrbvo.setCwlx(Constants.CQXXCWB_CWKX_02);

                            cqxxImportErrbvo
                                    .setCwlxmc(Constants.CQXXCWB_CWKX_02_CH);

                            cqxxbh = CommonUtil.getCqxxbh(conn);

                            cqxxImportErrbvo.setCqxxbh(cqxxbh);

                            DAOFactory.getInstance().getCqxxCwbDAO().insert(
                                    cqxxImportErrbvo, conn);

                            cqxx = false;

                            llErrorRecords.add(cqxxImportErrbvo);
                        }
                    }

                    // ����+���֤��+��ַ��ͬ�򸲸ǡ�
                    condition.delete(0, condition.length());

                    condition.append(
                            " where BCQRMC = '" + cqxxImportErrbvo.getBcqrmc()
                            + "' ").append(
                                    " and ZJHM ='" + cqxxImportErrbvo.getZjhm() +
                                    "' ")
                            .append(
                                    " and CQXXDZ='"
                                    + cqxxImportErrbvo.getCqxxdz()
                                    + "'");

                    if (DAOFactory.getInstance().getCqxxbDAO().query(
                            condition.toString(), conn).size() > 0) {

                        cqxxbh = CommonUtil.getCqxxbh(conn);

                        cqxxImportErrbvo.setCqxxbh(cqxxbh);

                        cqxxb = this.getCqxxb(cqxxImportErrbvo);

                        DAOFactory.getInstance().getCqxxbDAO().update(cqxxb,
                                condition.toString(), conn);

                        cqxx = false;

                        llSucceedRecords.add(cqxxb);
                    }
                }

                // ��������

                if (cqxx) {

                    cqxxbh = CommonUtil.getCqxxbh(conn);

                    cqxxImportErrbvo.setCqxxbh(cqxxbh);

                    cqxxb = this.getCqxxb(cqxxImportErrbvo);

                    DAOFactory.getInstance().getCqxxbDAO().insert(cqxxb, conn);

                    llSucceedRecords.add(cqxxb);
                }

            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CqxxbProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        lmReturnData.put("Error", llErrorRecords);
        lmReturnData.put("Succeed", llSucceedRecords);
        return lmReturnData;
    }

    /**
     * @param cqxxImportErrbvo
     * @return
     */
    public Cqxxb getCqxxb(CqxxImportErrbvo cqxxImportErrbvo) {
        Cqxxb cqxxb = new Cqxxb();
        // '��Ǩ������'
        cqxxb.setCqrmc(cqxxImportErrbvo.getCqrmc());
        // '��Ǩ��Χ'
        cqxxb.setCqfw(cqxxImportErrbvo.getCqfw());
        // '����Ǩ������'
        cqxxb.setBcqrmc(cqxxImportErrbvo.getBcqrmc());
        // '����Ǩ�����ʹ���'
        cqxxb.setBcqrlxdm(cqxxImportErrbvo.getBcqrlxdm());
        // '����Ǩ����������'
        cqxxb.setBcqrlxmc(cqxxImportErrbvo.getBcqrlxmc());
        // '֤�����ʹ���'
        cqxxb.setZjlxdm(cqxxImportErrbvo.getZjlxdm());
        // '֤����������'
        cqxxb.setZjlxmc(cqxxImportErrbvo.getZjlxmc());
        // '֤������'
        cqxxb.setZjhm(cqxxImportErrbvo.getZjhm());
        // '��Ǩ��ϸ��ַ'
        cqxxb.setCqxxdz(cqxxImportErrbvo.getCqxxdz());
        // '�������'
        cqxxb
                .setBcje(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getBcje()));
        // '�������ʹ���'
        cqxxb.setBclxdm(cqxxImportErrbvo.getBclxdm());
        // '������������'
        cqxxb.setBclxmc(cqxxImportErrbvo.getBclxmc());
        // '�������'
        cqxxb
                .setBcmj(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getBcmj()));
        // '�������ݵ�ַ'
        cqxxb.setBcfwdz(cqxxImportErrbvo.getBcfwdz());
        // '��Ǩ���֤��'
        cqxxb.setCqxkzh(cqxxImportErrbvo.getCqxkzh());
        // '���ش���'
        cqxxb.setQxdm(cqxxImportErrbvo.getQxdm());
        // '¼����'
        cqxxb.setLrr(cqxxImportErrbvo.getLrr());
        // '������'
        cqxxb.setCjr(cqxxImportErrbvo.getCjr());
        // '¼������'
        cqxxb.setLrrq(cqxxImportErrbvo.getLrrq());
        // '��������'
        cqxxb.setCjrq(cqxxImportErrbvo.getCjrq());

        // '��Ǩ��Ϣ���'
        cqxxb.setCqxxbh(cqxxImportErrbvo.getCqxxbh());

        // '������Դ'
        cqxxb.setSjly(cqxxImportErrbvo.getSjly());
        // '��������'
        cqxxb.setSzqx(cqxxImportErrbvo.getSzqx());
        // '˰�������֯��������'
        cqxxb.setSwjgzzjgdm(cqxxImportErrbvo.getSwjgzzjgdm());
        // '��Ǩ��Ŀ����'
        cqxxb.setCqxmmc(cqxxImportErrbvo.getCqxmmc());
        // '��Ǩ���'
        cqxxb
                .setCqmj(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getCqmj()));
        // ��Ǩ���֤����ʱ��
        cqxxb.setCqxkzspsj(DataConvert.String2Timestamp(cqxxImportErrbvo
                .getCqxkzspsj()));
        // ����������
        cqxxb.setGjrmc(cqxxImportErrbvo.getGjrmc());
        // ��ʽ���ݼ���
        cqxxb.setZsfwjs(cqxxImportErrbvo.getZsfwjs());
        return cqxxb;

    }

}

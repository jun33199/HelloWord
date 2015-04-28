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
     * （非 Javadoc）
     *
     * @see com.ttsoft.framework.processor.Processor#process(com.ttsoft.framework.util.VOPackage)
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage是空指针!!!");
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
            throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
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
        ArrayList resultList = new ArrayList(); // DAO查询返回的结果集

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
                    // 分局权限
                    // 对所属分局的数据有操作权限
                    String fj = ud.ssdwdm;
                    fj = fj.substring(0, 2);
                    conditions += " and  swjgzzjgdm " + " like '" + fj + "%'";

                } else if (ud.yhjb.equals(CodeConstants.JBDM_SWSJ)
                           || ud.yhjb.equals(CodeConstants.JBDM_ZSDJ))

                {
                    // 税务所权限、征收点级，
                    // 只对自己所属的税务所或征收点的数据有操作权限
                    conditions += " and  swjgzzjgdm " + "='" + ud.ssdwdm + "'";

                }
            }

            if (bo.getCqxmmc() != null && !"".equals(bo.getCqxmmc().trim())) {

                conditions += " AND CQXMMC like '%" + bo.getCqxmmc() + "%'";
            }

            // 调用DAO查询数据库
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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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

        ArrayList resultList = new ArrayList(); // DAO查询返回的结果集

        Cqxxb bo = (Cqxxb) vo.getData();

        UserData ud = vo.getUserData();

        StringBuffer condition = new StringBuffer();

        Map lmReturnData = new HashMap(); // 返回的数据集合

        String message = "";

        // 后台查询导入错误的结果的结果集(已有拆迁信息)
        ArrayList resultListErrIs = new ArrayList();

        // 后台查询导入错误的结果的结果集(无对应拆迁信息)
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
                // 分局权限
                // 对所属分局的数据有操作权限
                String fj = ud.ssdwdm;
                fj = fj.substring(0, 2);
                conditions += " and  swjgzzjgdm " + " like '" + fj + "%'";

            } else if (ud.yhjb.equals(CodeConstants.JBDM_SWSJ)
                       || ud.yhjb.equals(CodeConstants.JBDM_ZSDJ))

            {
                // 税务所权限、征收点级，
                // 只对自己所属的税务所或征收点的数据有操作权限
                conditions += " and  swjgzzjgdm " + "='" + ud.ssdwdm + "'";

            }

            // 调用DAO查询数据库
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getCqxxCwbDAO().query(
                    conditions, conn);

            if (resultList.size() > Constants.XGDRCWXX_QUERYSIZE_DR) {
                message = "您当前的查询条件过于宽松，导致结果集过大未完全显示；您可以缩小查询范围作进一步查询！";
                resultList = new ArrayList(resultList.subList(0,
                        Constants.XGDRCWXX_QUERYSIZE_DR));

            } else {
                message = "查询成功！";
            }

            for (int i = 0; i < resultList.size(); i++) {

                CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo) resultList
                                               .get(i);

                String zgswjgmc = CommonUtil.getSwjgzzjg(
                        cqxxberrTmp.getSwjgzzjgdm(), conn).getSwjgzzjgmc();

                cqxxberrTmp.setSwjgzzjgmc(zgswjgmc);

                // 正式房屋间数、正式房屋建筑面积非数字
                if (Constants.CQXXCWB_CWKX_01.equals(cqxxberrTmp.getCwlx())) {

                    // Cqxxb中姓名+身份证号+地址相同则取出放到resultListCqxx。
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

                    // Cqxxb中姓名+身份证号+地址相同则取出放到resultListCqxx。
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
                        // 身份证号且相同且姓名不一样的问题数据取出放到resultListCqxx
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

                } else if (Constants.CQXXCWB_CWKX_02 // 身份证号相同且姓名不一样
                           .equals(cqxxberrTmp.getCwlx())) {

                    condition.delete(0, condition.length());

                    condition.append(" where zjhm='" + cqxxberrTmp.getZjhm()
                                     + "'");

                    ArrayList arrayListCqxx3 = DAOFactory.getInstance()
                                               .getCqxxbDAO().query(condition.
                            toString(), conn);
                    // 身份证号且相同且姓名不一样的问题数据取出放到resultListCqxx
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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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

        Map hashre = new HashMap(); // 返回的数据集合

        String message = "";

        // 处理的list
        ArrayList list = new ArrayList();

        Connection conn = null;

        try {

            // 调用DAO查询数据库
            conn = QSDBUtil.getConnection();

            // 覆盖 修改正式表数据，删除错误表数据 Is 错误数据有正式表。
            list = (ArrayList) hash.get("fgIs");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxErr cqxxErrtmp = (CqxxErr) list.get(i);

                    // 根据拆迁信息表的拆迁编号更新拆迁表数据
                    Cqxxb cqxxbtmp = cqxxErrtmp.getCqxxb();

                    CqxxImportErrbvo cqxxberrTmp = cqxxErrtmp
                            .getCqxxImportErrbvo();

                    DAOFactory.getInstance().getCqxxbDAO().update(cqxxbtmp,
                            conn);

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            // 废弃 删除错误表数据 Is 错误数据有正式表。
            list = (ArrayList) hash.get("fqIs");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxErr cqxxErrtmp = (CqxxErr) list.get(i);

                    // 根据拆迁信息表的拆迁编号更新拆迁表数据
                    CqxxImportErrbvo cqxxberrTmp = cqxxErrtmp
                            .getCqxxImportErrbvo();

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            // 覆盖 插入正式表数据，删除错误表数据 No 错误数据没有有正式表。
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

            // 废弃 删除错误表数据 No 错误数据没有有正式表。
            list = (ArrayList) hash.get("fqNo");

            if (list != null && list.size() > 0) {

                for (int i = 0; i < list.size(); i++) {

                    CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo) list
                            .get(i);

                    DAOFactory.getInstance().getCqxxCwbDAO().delete(
                            cqxxberrTmp, conn);

                }

            }

            message = "操作成功！ ";

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
        ArrayList resultList = new ArrayList(); // DAO查询返回的结果集

        PlcxMxBo bo = (PlcxMxBo) vo.getData();
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getPlcxDAO().getDetail(bo,
                    conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税拆迁信息－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
        Cqxxb result = new Cqxxb(); // DAO查询返回的结果集

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
                throw new ApplicationException("拆迁信息编号为空！");
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");
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
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 保存上传的excel
     *
     *
     * @param vo
     * @throws BaseException
     */
    public Object doUploadExcel(VOPackage vo) throws BaseException {

        Map loData = (HashMap) vo.getData();

        ArrayList cqxxList = (ArrayList) loData.get("new");

        Map lmReturnData = new HashMap(); // 返回的数据集合
        List llErrorRecords = new ArrayList(); // 格式错误的数据集合
        List llSucceedRecords = new ArrayList(); // 已导入的正确的数据
        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            for (int i = 0; i < cqxxList.size(); i++) {
                // 获得拆迁信息编号
                String cqxxbh = "";
                boolean cqxx = true;
                StringBuffer condition = new StringBuffer();
                CqxxImportErrbvo cqxxImportErrbvo = (CqxxImportErrbvo) cqxxList
                        .get(i);
                Cqxxb cqxxb = new Cqxxb();

                // 正式房屋间数、正式房屋建筑面积非数字放到问题数据中待手工处理

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

                // 存在身份证
                //不存在身份证直接保存
                if (cqxxImportErrbvo.getZjhm() != null
                    && !("".equals(cqxxImportErrbvo.getZjhm()))
                    && !("0".equals(cqxxImportErrbvo.getZjhm()))
                    && !(" ".equals(cqxxImportErrbvo.getZjhm()))) {

                    // 身份证号且相同且姓名不一样则放到问题数据中待手工处理
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

                    // 姓名+身份证号+地址相同则覆盖。
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

                // 正常保存

                if (cqxx) {

                    cqxxbh = CommonUtil.getCqxxbh(conn);

                    cqxxImportErrbvo.setCqxxbh(cqxxbh);

                    cqxxb = this.getCqxxb(cqxxImportErrbvo);

                    DAOFactory.getInstance().getCqxxbDAO().insert(cqxxb, conn);

                    llSucceedRecords.add(cqxxb);
                }

            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CqxxbProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP)
                             .getStackMsg(), "失败");

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
        // '拆迁人名称'
        cqxxb.setCqrmc(cqxxImportErrbvo.getCqrmc());
        // '拆迁范围'
        cqxxb.setCqfw(cqxxImportErrbvo.getCqfw());
        // '被拆迁人名称'
        cqxxb.setBcqrmc(cqxxImportErrbvo.getBcqrmc());
        // '被拆迁人类型代码'
        cqxxb.setBcqrlxdm(cqxxImportErrbvo.getBcqrlxdm());
        // '被拆迁人类型名称'
        cqxxb.setBcqrlxmc(cqxxImportErrbvo.getBcqrlxmc());
        // '证件类型代码'
        cqxxb.setZjlxdm(cqxxImportErrbvo.getZjlxdm());
        // '证件类型名称'
        cqxxb.setZjlxmc(cqxxImportErrbvo.getZjlxmc());
        // '证件号码'
        cqxxb.setZjhm(cqxxImportErrbvo.getZjhm());
        // '拆迁详细地址'
        cqxxb.setCqxxdz(cqxxImportErrbvo.getCqxxdz());
        // '补偿金额'
        cqxxb
                .setBcje(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getBcje()));
        // '补偿类型代码'
        cqxxb.setBclxdm(cqxxImportErrbvo.getBclxdm());
        // '补偿类型名称'
        cqxxb.setBclxmc(cqxxImportErrbvo.getBclxmc());
        // '补偿面积'
        cqxxb
                .setBcmj(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getBcmj()));
        // '补偿房屋地址'
        cqxxb.setBcfwdz(cqxxImportErrbvo.getBcfwdz());
        // '拆迁许可证号'
        cqxxb.setCqxkzh(cqxxImportErrbvo.getCqxkzh());
        // '区县代码'
        cqxxb.setQxdm(cqxxImportErrbvo.getQxdm());
        // '录入人'
        cqxxb.setLrr(cqxxImportErrbvo.getLrr());
        // '创建人'
        cqxxb.setCjr(cqxxImportErrbvo.getCjr());
        // '录入日期'
        cqxxb.setLrrq(cqxxImportErrbvo.getLrrq());
        // '创建日期'
        cqxxb.setCjrq(cqxxImportErrbvo.getCjrq());

        // '拆迁信息编号'
        cqxxb.setCqxxbh(cqxxImportErrbvo.getCqxxbh());

        // '数据来源'
        cqxxb.setSjly(cqxxImportErrbvo.getSjly());
        // '所在区县'
        cqxxb.setSzqx(cqxxImportErrbvo.getSzqx());
        // '税务机关组织机构代码'
        cqxxb.setSwjgzzjgdm(cqxxImportErrbvo.getSwjgzzjgdm());
        // '拆迁项目名称'
        cqxxb.setCqxmmc(cqxxImportErrbvo.getCqxmmc());
        // '拆迁面积'
        cqxxb
                .setCqmj(DataConvert.String2BigDecimal(cqxxImportErrbvo
                .getCqmj()));
        // 拆迁许可证审批时间
        cqxxb.setCqxkzspsj(DataConvert.String2Timestamp(cqxxImportErrbvo
                .getCqxkzspsj()));
        // 共居人姓名
        cqxxb.setGjrmc(cqxxImportErrbvo.getGjrmc());
        // 正式房屋间数
        cqxxb.setZsfwjs(cqxxImportErrbvo.getZsfwjs());
        return cqxxb;

    }

}

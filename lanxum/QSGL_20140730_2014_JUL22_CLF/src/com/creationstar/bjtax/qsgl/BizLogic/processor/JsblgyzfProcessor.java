package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.creationstar.bjtax.qsgl.BizLogic.dao.JsblgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.SbgyzfDAO;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Jsblgyzf;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbgyzf;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.QueryYggyzfBo;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 *
 * <p>Description:拆迁情况的Processor类 </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: 北京创讯益达软件技术有限公司 </p>
 *
 * @author 蒋勇
 * @version 1.0
 */
public class JsblgyzfProcessor extends CommonProcessor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is CqqkProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.INSERT:
            doAdd(vo);
            break;
        case ActionType.MODIFY:
            doUpdate(vo);
            break;
        case ActionType.UPDATE_SYE:
            doUpdateSye(vo);
            break;
        case ActionType.GET:
            return doGet(vo);
        case ActionType.QUERY_USAGE:
            return doQueryUsage(vo);
        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 实际上增加一条公有住房抵扣额使用记录
     * 如果该公用住房是第一次使用,则增加一条公有住房信息,否则更新公有住房信息
     * 增加申报公有住房关联信息，即本次使用抵扣额
     * 1.insert/update a record of Jsblgyzf
     * 2. insert a record of Sbgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            String nd = DateUtil.getDate().substring(0, 4);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            conn = QSDBUtil.getConnection();
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            //  List l = dao.getBySbbh(jsblgyzf.getSbbh(),conn);

            Jsblgyzf existGyzf = (Jsblgyzf) dao.get(jsblgyzf, conn);
            /*          Jsblgyzf existGyzf = null;
                      double dkeHj = jsblgyzf.getBcdke().doubleValue();
                      //dkeHj+=jsblgyzf.getBcdke().doubleValue();
                      for (int i=0;i<l.size();i++){
                       Jsblgyzf gf = (Jsblgyzf) l.get(i);
                       if (gf != null && gf.getYggyzfqszsh().equalsIgnoreCase(jsblgyzf.getYggyzfqszsh())){
                        existGyzf = gf;
                       }else{
                        dkeHj+=gf.getBcdke().doubleValue();
                       }
                       }
             */
            if (existGyzf != null) {
                existGyzf.setBcdke(jsblgyzf.bcdke);

                existGyzf.setCjjg(jsblgyzf.cjjg);
                existGyzf.setJzmj(jsblgyzf.jzmj);
                existGyzf.setQdsj(jsblgyzf.qdsj);
                existGyzf.setSye(jsblgyzf.sye);
                existGyzf.setZldz(jsblgyzf.zldz);
                existGyzf.setSyeywbz(jsblgyzf.getSyeywbz());
                existGyzf.setFwqszsh(jsblgyzf.getFwqszsh());                
                dao.update(existGyzf, conn);
            } else {
                //construct the jsblgyzf and insert
                jsblgyzf.ztbs = "0";
                jsblgyzf.cjr = ud.yhmc;
                jsblgyzf.lrr = ud.yhmc;
                jsblgyzf.cjrq = now;
                jsblgyzf.lrrq = now;
                jsblgyzf.nd = nd;

                dao.insert(jsblgyzf, conn);
            }

            //construct the sbgyzf and insert
            Sbgyzf sbgyzf = new Sbgyzf();
            sbgyzf.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            sbgyzf.bcdke = jsblgyzf.bcdke;
            sbgyzf.lrr = ud.yhmc;
            sbgyzf.cjr = ud.yhmc;
            sbgyzf.cjrq = now;
            sbgyzf.lrrq = now;
            sbgyzf.sbbh = jsblgyzf.sbbh;
            Debug.out("Jsblgyzfprocessor sbbh: " + jsblgyzf.sbbh);

            DAOFactory.getInstance().getSbgyzfDAO().insert(sbgyzf, conn);

            //更新主表办理减免税标识,当其计征税额抵扣出售公房收入后实际应征税额为0时
            //DAOFactory.getInstance().getSbzbDAO().updateJmsbs(Constants.ZB_BLJMSBS_FHBL_WLR,jsblgyzf.getSbbh(),conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JsblgyzfProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.根据拆迁协议号获取拆迁信息
     * @param vo VOPackage
     * @throws BaseException
     */
    private Jsblgyzf doGet(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);

            if (existData == null) {
                Debug.out("jsblgyzf : " + jsblgyzf.getYggyzfqszsh() +
                          " not exist.");
            } else {
                Sbgyzf sbgyzf = new Sbgyzf();
                sbgyzf.sbbh = existData.sbbh;
                sbgyzf.yggyzfqszsh = existData.yggyzfqszsh;
                sbgyzf = (Sbgyzf) DAOFactory.getInstance().getSbgyzfDAO().get(
                        sbgyzf, conn);
                if (sbgyzf != null) {
                    existData.bcdke = sbgyzf.bcdke;
                }
            }

            return existData;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JsblgyzfProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "获取公有住房信息失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdate(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            SbgyzfDAO gldao = DAOFactory.getInstance().getSbgyzfDAO();

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);

            //设置更新的字段
            existData.sye = jsblgyzf.sye;
            existData.cjjg = jsblgyzf.cjjg;
            Debug.out("update cjjg: " + existData.cjjg);
            existData.jzmj = jsblgyzf.jzmj;
            existData.qdsj = jsblgyzf.qdsj;
            existData.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            existData.zldz = jsblgyzf.zldz;
            existData.syeywbz = jsblgyzf.syeywbz;
            existData.fwqszsh = jsblgyzf.fwqszsh;            

            //更新
            dao.update(existData, conn);

            //更新申报公有住房关联
            Sbgyzf sbgyzf = new Sbgyzf();
            sbgyzf.sbbh = jsblgyzf.sbbh;
            sbgyzf.yggyzfqszsh = jsblgyzf.yggyzfqszsh;
            sbgyzf = (Sbgyzf) gldao.get(sbgyzf, conn);
            sbgyzf.bcdke = jsblgyzf.bcdke;
            DAOFactory.getInstance().getSbgyzfDAO().update(sbgyzf, conn);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JsblgyzfProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    /*  private void doDelete(VOPackage vo) throws BaseException {
         Connection conn = null;
         try {
             UserData ud = vo.getUserData();

             //construct the jsblgyzf and insert
             ArrayList delList = (ArrayList)vo.getData();

             conn = QSDBUtil.getConnection();
             JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();

             dao.delete(delList,conn);


         } catch (Exception ex) {
             throw ExceptionUtil.getBaseException(ex);
         } finally {
             QSDBUtil.freeConnection(conn);
         }
      }
     */
    /**
     * 1.根据拆迁协议号获取拆迁使用信息
     * @param vo VOPackage
     * @throws BaseException
     * @return QueryCqxxBo
     *
     */
    private QueryYggyzfBo doQueryUsage(VOPackage vo) throws BaseException {
        Connection conn = null;
        QueryYggyzfBo bo = null;
        try {
            //construct the jsblcq and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            System.out.println("--:" + jsblgyzf.yggyzfqszsh);

            Jsblgyzf existData = (Jsblgyzf) dao.get(jsblgyzf, conn);
            System.out.println("--2:" + existData);
            if (existData == null) {

                return null;
            } else {
                //构造QueryCqxxBo
                bo = new QueryYggyzfBo();
                bo.setYggyzfqszsh(existData.getYggyzfqszsh());
                bo.setZldz(existData.getZldz());
                bo.setQdsj(existData.getQdsj());
                bo.setJzmj(existData.getJzmj());
                bo.setCjjg(existData.getCjjg());
                bo.setSye(existData.getSye());
                bo.setCjr(existData.getCjr());
                bo.setCjrq(existData.getCjrq());
                bo.setSyeywbz(existData.getSyeywbz());
                bo.setFwqszsh(existData.getFwqszsh());
                //获取使用此拆迁的申报信息
                ArrayList list = (ArrayList) DAOFactory.getInstance().
                                 getSbgyzfDAO().queryByHth(jsblgyzf.yggyzfqszsh,
                        conn);
                if ((list != null) && (list.size() > 0)) {
                    bo.setListSbxx(list);
                }
            }

            return bo;

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JsblgyzfProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex, "获取拆迁使用信息失败!");
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }


    /**
     * 1.update a record of Jsblgyzf
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doUpdateSye(VOPackage vo) throws BaseException {
        Connection conn = null;
        try {
            UserData ud = vo.getUserData();
            Calendar cd = Calendar.getInstance();

            //construct the jsblgyzf and insert
            Jsblgyzf jsblgyzf = (Jsblgyzf) vo.getData();

            conn = QSDBUtil.getConnection();
            JsblgyzfDAO dao = DAOFactory.getInstance().getJsblgyzfDAO();
            //更新
            dao.updateSye(jsblgyzf, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－JsblgyzfProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }
}

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import com.creationstar.bjtax.qsgl.util.Constants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.ttsoft.framework.exception.ExceptionUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import java.sql.Connection;
import com.creationstar.bjtax.qsgl.BizLogic.vo.*;
import com.ttsoft.framework.processor.Processor;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.ShskBo;
import java.math.BigDecimal;
import java.util.HashMap;
import com.creationstar.bjtax.qsgl.model.bo.HdtzsBo;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;

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
 */
public class ShskProcessor implements Processor {
    /**
     * 根据传入数据进行不同的操作.
     *
     * @param vo the VOPackage.
     * @return Object 业务对象.
     * @throws BaseException 抛出的异常.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is ShskProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.GET:
            result = doGet(vo);
            break;
        case ActionType.CHECK:
            result = doCheck(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * 实现以ShskBo的属性作为条件，查询单条记录
     * @return Object
     */
    private Object doGet(VOPackage vo) throws BaseException {
        /**
         * 通过取出来得ShskBo
         * 构造查询条件
         *
         * 1、使用ShskBo构造申报主表Vo，DAO反回结果申报主表Vo
         *
         * 2、构造个人、非个人信息Vo，DAO.get反回结果个人、非个人信息Vo的ArrayList
         *
         * 3、构造房屋土地基本信息conditions,DAO.query反回结果房屋土地基本信息Vo的ArrayList
         *
         * 4、构造拆迁信息conditions,DAO.query反回结果拆迁信息Vo的ArrayList
         *
         * 5、构造公有住房信息conditions,DAO.query反回结果公有住房信息Vo的ArrayList
         */

        ShskBo bo = new ShskBo();

        ArrayList hdList = new ArrayList();

        Connection conn = null;
        try {
            bo = (ShskBo) vo.getData();

            conn = QSDBUtil.getConnection();

            //得到主表的相关信息
            Sbzb sbzbVo = new Sbzb();
            sbzbVo.setSbbh(bo.getSbbh());
            sbzbVo = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzbVo,
                    conn);

            List l = (List) DAOFactory.getInstance().getGrxxDAO().getAllBySbbh(
                    sbzbVo.getSbbh(), conn);
            bo.setBz(sbzbVo.getBz());
            bo.setJsfsdm(sbzbVo.getJsfsdm());
            bo.setSl(sbzbVo.getSl());
            bo.setSbbh(sbzbVo.getSbbh());
            bo.setNsrList(l);
            if (sbzbVo.ztbs.equals(Constants.ZB_ZTBS_YFH)) {
                bo.setChecked(true);
            }

            //得到其他相关信息(部分已经传入)这里计算拆迁减税额
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            bo.setCqjmje(hdbo.getCqjmje());
            bo.setPtzzjsje(hdbo.getPtzzjmje());
            //增加经济适用房减税金额
            bo.setJjsyfjsje(hdbo.getJjsyfjmje());

            bo.setJsje(hdbo.getJzse());
            bo.setJsyj(hdbo.getJsyj());
            bo.setYnse(hdbo.getYnse());

            //确定房屋土地信息
            bo.setCjjgrmb(hdbo.getCjjgrmb());

            //得到核定通知书信息
            ArrayList list = new ArrayList();
            HdtzsBo hdtzsbo = getHdtzsBo(conn, bo.getSbbh());
            if (hdtzsbo != null) {
                list.add(hdtzsbo);
            }
            bo.setHdList(list);

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－ShskProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }

    /**
     * 复核
     * @param vo VOPackage
     */
    private Object doCheck(VOPackage vo) throws BaseException {
        ShskBo bo = (ShskBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            //构造申报主表值对象，更新应纳税额字段
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(bo.getSbbh());
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            sbzb.setYnse(bo.getYnse());
            sbzb.setZtbs(Constants.ZB_ZTBS_YFH);
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            return null;
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－ShskProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * 获取核定通知书数据
     */
    private HdtzsBo getHdtzsBo(Connection conn, String sbbh) throws Exception {
        HdtzsBo hdtzsBo = new HdtzsBo();

        HashMap nrMap = new HashMap();

        //获取核定通知书主表数据
        Hdtzs hdtzs = (Hdtzs) DAOFactory.getInstance().getHdtzsDAO().
                      getBySbbh(sbbh, conn);
        if (hdtzs == null) {
            return null;
        } else {
            hdtzsBo.setVoHdtzs(hdtzs);

            //获取核定减免明细
            String condition = " where HDTZSH = '" + hdtzs.getHdtzsh() +
                               "'";
            ArrayList jmList = (ArrayList) DAOFactory.getInstance().
                               getHdjmmxDAO().query(condition, conn);
            for (int i = 0; i < jmList.size(); i++) {
                Hdjmmx hdjmmx = (Hdjmmx) jmList.get(i);
                nrMap.put(hdjmmx.getZcbh(), hdjmmx);
            }
            hdtzsBo.setJmnrMap(nrMap);

            //获取审批结果减免税额
            Spjgxx spjgxx = (Spjgxx) DAOFactory.getInstance().getSpjgxxDAO().
                            getBySbbh(sbbh, conn);
            if (spjgxx != null) {
                hdtzsBo.setSpjmse(spjgxx.getJmsje());
            }

            return hdtzsBo;
        }
    }


}

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.model.ActionType;
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
import com.ttsoft.framework.processor.Processor;
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
 * 说明：该Processor是针对页面中"批量受理(税务人员)"模块
 */
public class ImportFileProcessor2 implements Processor {
    Connection conn = null;
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }
        switch (vo.getAction()) {
        case ActionType.UPLOAD_FILE:
            result = doUpload(vo);
            break;

        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.GET:
            result = doGetPlh(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }

        return result;
    }

    /**
     * doUpload
     */
    private Object doUpload(VOPackage vo) throws BaseException {
        UserData loUserData = vo.getUserData();
        List llErrorRecords = new ArrayList(); //格式错误的数据集合
        List llSucceedRecords = new ArrayList(); //已导入的正确的数据
        List llSucceedPcinfoRecords = new ArrayList(); //已导入的正确批次主表的记录
        List loRecords;
        Map lmReturnData = new HashMap(); //返回的数据集合
        String lsDrpch = null; //导入批次号
        int liXH = 1; //插入序号
        int liDrzt = 0; //导入状态：0新增1追加
        try {
            conn = QSDBUtil.getConnection();
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());

            Map loData = (HashMap) vo.getData();
            lsDrpch = (String) loData.get("pch");
            loRecords = (List) loData.get("new");
            /*
                         System.out.println("////////////////");
                         System.out.println("记录数：" + loRecords.size());
                         System.out.println("////////////////");
             */
            if (loRecords != null) {
                System.out.println("newnewnewnewnewnew");
                //先插导入批次主表
                for (int i = 0; i < loRecords.size(); i++) {
                    Object loVo = loRecords.get(i);
                    //导入人信息
                    if (loVo.getClass().equals(Drpcinfo.class)) {
                        try {
                            ((Drpcinfo) loVo).setDrpch(lsDrpch);
                            ((Drpcinfo) loVo).setDrbs(BigDecimal.valueOf(0)); //导入笔数
                            ((Drpcinfo) loVo).setDrsj(nowTime);
//                            ((Drpcinfo) loVo).setTjsj(nowTime);
                            ((Drpcinfo) loVo).setJsfmc((String) loData.get(
                                    "jsfsmc"));
                            ((Drpcinfo) loVo).setJsfsdm((String) loData.get(
                                    "jsfsdm"));

                            DAOFactory.getInstance().getDrpcInfoDAO().insert((
                                    Drpcinfo)
                                    loVo,
                                    conn,
                                    "01");
                            llSucceedPcinfoRecords.add(loVo);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("导入批次信息错误");
                            llErrorRecords.add(loVo);
                        }
                    }
                }
            } else {
                System.out.println("appendappendappendappendappend");
                loRecords = (List) loData.get("append");
                liDrzt = 1; //设置为追加状态
                liXH = Integer.parseInt((String) DAOFactory.getInstance().
                                        getDrzbDAO().getMaxXh(lsDrpch, conn)) +
                       1;
                int liDrbs = DAOFactory.getInstance().getDrzbDAO().getDrbs(
                        lsDrpch, conn);
                if (liDrbs + loRecords.size() - 1 > 200) {
                    throw new Exception("每批申报笔数不能超过200条,追加失败！");
                }
            }

            //再插子表
            for (int i = 0; i < loRecords.size(); i++) {
                Object loVo = loRecords.get(i);
                if (loVo.getClass().equals(Drzb.class)) {
                    try {
                        ((Drzb) loVo).setBz("测试");
                        ((Drzb) loVo).setDrczr(loUserData.getYhmc());
                        ((Drzb) loVo).setDrsj(nowTime);
                        ((Drzb) loVo).setDrpch(lsDrpch);
                        ((Drzb) loVo).setXh(BigDecimal.valueOf(liXH++));
                        ((Drzb) loVo).setZtbs(Constants.DRZB_ZT_XINZENG);
                        DAOFactory.getInstance().getDrzbDAO().insertSql((Drzb)
                                loVo,
                                conn,
                                "01");
                        llSucceedRecords.add(loVo);

                    } catch (Exception ex) {
                        System.out.println("插入契税导入主表错误");
                        ex.printStackTrace();
                        llErrorRecords.add(loVo);

                    }

                }
            }
            //检查子表数据是否都没插进，若是则删除主表记录
            if ((llErrorRecords.size() == loRecords.size() - 1) && liDrzt == 0) {
                System.out.println("检查子表数据是否都没插进，若是则删除主表记录");
                DAOFactory.getInstance().getDrpcInfoDAO().delete((ArrayList)
                        llSucceedPcinfoRecords, conn);
            }
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ex.printStackTrace();
            ErrorLog.makeLog(vo.getUserData(),
                             "契税xml文件上传－UploadFileProcessor2，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        lmReturnData.put("Error", llErrorRecords);
        lmReturnData.put("Succeed", llSucceedRecords);
        return lmReturnData;
    }

    private Object doQuery(VOPackage vo) throws BaseException {
        String[] llDrpchs = null;
        try {
            conn = QSDBUtil.getConnection();
            String lsCondition = "";
            //增加数据权限控制
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "QSDB", "QS_JL_DRPCINFO");
            Debug.out("datafilter: " + datafilter);
            lsCondition += " where " + datafilter;
            lsCondition += " order by drsj desc";
            ArrayList loDrpcinfos = DAOFactory.getInstance().getDrpcInfoDAO().
                                    query(lsCondition, conn);
            llDrpchs = new String[loDrpcinfos.size()];
            for (int i = 0; i < loDrpcinfos.size(); i++) {
                llDrpchs[i] = ((Drpcinfo) loDrpcinfos.get(i)).getDrpch();
            }

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "契税xml文件上传－UploadFileProcessor2，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return llDrpchs;

    }


    private Object doGetPlh(VOPackage vo) throws BaseException {
        String plh = null;
        try {
            conn = QSDBUtil.getConnection();
            UserData user = vo.getUserData();
            plh = CommonUtil.getPlh(user.getXtsbm1(), conn, "P");

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "契税xml文件上传－UploadFileProcessor2，获取批量号出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return plh;

    }
}

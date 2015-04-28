/*
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京创讯益达软件技术有限公司，版权所有. </p>
 * <p>Company: 北京创讯益达软件技术有限公司</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Gjdq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税核心征管系统－－契税管理</p>
 * <p>Description: 执行代码表数据的初始化和获取的Processor类</p>
 * @author 契税开发组－－赵博、韩雷
 * @version 1.0
 */
public class CodeTableProcessor implements Processor {

    /**
     * process method.
     *
     * @param vo VOPackage
     * @return java.lang.Object 业务对象
     * @throws BaseException 可能抛出的异常
     * @roseuid 3F4DA01C0007
     */
    public Object process(VOPackage vo) throws BaseException {
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            return doQuery(vo);
        case ActionType.GET_HL:
            return doGetHl(vo);

        default:
            throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
    }

    /**
     * load all tables
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        HashMap map = (HashMap) vo.getData();
        HashMap reMap = new HashMap();
        if (map.isEmpty()) {
            Debug.out(
                    "CodeTableProcessor: doQuery() say -- the map is empty!!!");
            return null;
        }

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Set s = map.keySet();
            Debug.out(
                    "CodeTableProcessor: doQuery() say -- the map is clear!!!");
            for (Iterator it = s.iterator(); it.hasNext(); ) {
                String tableName = (String) it.next();
                Debug.out(
                        "CodeTableProcessor: INIT   " + tableName);
                if (Constants.ZCWH.equals(tableName)) {
                    //取政策维护代码表
                    reMap.put(Constants.ZCWH,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryZCWH(conn));
                } else if (Constants.BZ.equals(tableName)) {
                    //取币种代码表

                    reMap.put(Constants.BZ,
                              DAOFactory.getInstance().
                              getCodeTablesDAO().queryBZ(conn));
                } else if (Constants.HL.equals(tableName)) {
                    //取汇率代码表
                    //由于汇率天天变，所以不能放在这里面
                } else if (Constants.FWLX.equals(tableName)) {
                    //取房屋类型代码表
                    reMap.put(Constants.FWLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFWLX(conn));
                } else if (Constants.ZJLX.equals(tableName)) {
                    //取证件类型代码表
                    List l = DAOFactory.getInstance().getCodeTablesDAO().
                             queryZJLX(conn);
                    reMap.put(Constants.ZJLX,
                              l);
                    reMap.put(Constants.ZJLXMAP, getZjMap(l));
                } else if (Constants.JSFS.equals(tableName)) {
                    //取缴税方式代码表
                    reMap.put(Constants.JSFS,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJSFS(conn));
                } else if (Constants.NSRLX.equals(tableName)) {
                    //取纳税人类型代码表
                    reMap.put(Constants.NSRLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryNSRLX(conn));
                } else if (Constants.TDFWYT.equals(tableName)) {
                    //取土地房屋用途代码表
                    reMap.put(Constants.TDFWYT,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryTDFWYT(conn));
                } else if (Constants.BZ_TDFWYT_GR.equals(tableName)) {
                    //取土地房屋用途代码表——个人
                    reMap.put(Constants.BZ_TDFWYT_GR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZTDFWYTGR(conn));
                } else if (Constants.BZ_TDFWYT_FGR.equals(tableName)) {
                    //取土地房屋用途代码表——非个人
                    reMap.put(Constants.BZ_TDFWYT_FGR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZTDFWYTFGR(conn));
                } else if (Constants.BZQK.equals(tableName)) {
                    //取不征情况代码表
                    reMap.put(Constants.BZQK,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZQK(conn));
                } else if (Constants.TDFWQSZY.equals(tableName)) {
                    //取土地房屋权属转移形式代码表
                    reMap.put(Constants.TDFWQSZY,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryQSZYXS(conn));

                //zzb 20090820 add begin
                } else if (Constants.TDFWYTJM.equals(tableName)) {
                    //取土地房屋用途代码表__减免
                    reMap.put(Constants.TDFWYTJM,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMTDFWYTFGR(conn));
                //zzb  20090820 add end

                } else if (Constants.JMZC.equals(tableName)) {
                    //取减免政策代码表－－list
                    reMap.put(Constants.JMZC,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZC(conn));
                } else if (Constants.JMZCMAP.equals(tableName)) {
                    //取减免政策代码表－－map
                    reMap.put(Constants.JMZCMAP,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCMap(conn));
                } else if (Constants.JMZC_GR.equals(tableName)) {
                    //取减免政策代码表－－个人
                    reMap.put(Constants.JMZC_GR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCGR(conn));
                } else if (Constants.JMZC_FGR.equals(tableName)) {
                    //取减免政策代码表－－非个人
                    reMap.put(Constants.JMZC_FGR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCFGR(conn));
                } else if (Constants.GJ.equals(tableName)) {
                    //取国籍代码表
                    List l = DAOFactory.getInstance().
                             getCodeTablesDAO().queryGJ(conn);
                    reMap.put(Constants.GJ,
                              l);
                    reMap.put(Constants.GJMAP, getGjMap(l));
                } else if (Constants.SWJGZZJG.equals(tableName)) {
                    //取税务机关组织机构代码表
                    reMap.put(Constants.SWJGZZJG,
                              DAOFactory.getInstance().
                              getCodeTablesDAO().querySWJGZZJG(conn));
                } else if (Constants.TDJC.equals(tableName)) {
                    //取生活用房代码表
                    reMap.put(Constants.TDJC,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryTDJC(conn));

                } else if (Constants.SZQY.equals(tableName)) {
                    //取生活用房代码表
                    reMap.put(Constants.SZQY,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              querySZQY(conn));

                }else if (Constants.FPZL.equals(tableName)) {
                    //取发票种类代码表
                    reMap.put(Constants.FPZL,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFPZL(conn));
                }else if (Constants.KPLX.equals(tableName)) {
                    //取发票种类代码表
                    reMap.put(Constants.KPLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryKplx(conn));
                }else if (Constants.FWCQZBZZFLX.equals(tableName)) {
                    //房屋产权证标注住房类
                    reMap.put(Constants.FWCQZBZZFLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFwcqzbzzflx(conn));
                }else if(Constants.FWXZ.equals(tableName)){
                    //房屋性质
                    reMap.put(Constants.FWXZ,
                              DAOFactory.getInstance().getCodeTablesDAO().queryFwxz(conn));               	
                	
                }else if (Constants.FWSZQY.equals(tableName)){
                    //房屋所载区域
                    reMap.put(Constants.FWSZQY,
                              DAOFactory.getInstance().getCodeTablesDAO().queryFWSZQY(conn));                  	
                	
                }else if (Constants.QS_JMXZ.equals(tableName)){
                    //契税减免性质
                    reMap.put(Constants.QS_JMXZ,
                              DAOFactory.getInstance().getCodeTablesDAO().queryJmxzdmList(conn));                  	
                	
                }else if (Constants.QSZYXSMX.equals(tableName)){
                    //房屋权属转移明细
                    reMap.put(Constants.QSZYXSMX,
                              DAOFactory.getInstance().getCodeTablesDAO().queryQSZYXSMX(conn));                  	
                	
                }
                

            }
            Debug.out("sucessfully put arrylist in map !!!");

        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CodeTableProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return reMap;
    }

    private Map getZjMap(List l) {
        Map m = new HashMap();
        for (int i = 0; i < l.size(); i++) {
            Zjlx vo = (Zjlx) l.get(i);
            m.put(vo.getZjlxdm(), vo);
        }
        return m;
    }

    private Map getGjMap(List l) {
        Map m = new HashMap();
        for (int i = 0; i < l.size(); i++) {
            Gjdq vo = (Gjdq) l.get(i);
            m.put(vo.getGjdqdm(), vo);
        }
        return m;
    }

    /**
     * 获取指定币种的汇率
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGetHl(VOPackage vo) throws BaseException {
        Wbhs wbhs = (Wbhs) vo.getData();
        if (wbhs == null) {
            throw new ApplicationException("获取汇率参数不能为空！");
        }

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            return DAOFactory.getInstance().getWbhsDAO().get(wbhs, conn);
        } catch (Exception ex) {
            // 处理失败信息:控制台 ＋ 日志
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "契税申报征收－CodeTableProcessor，操作出错",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "失败");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }
}

/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsCxjksForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税核心征管系统－－上门申报
 * </p>
 * <p>
 * Description: 零散征收撤销缴款书
 * </p>
 * 
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsCxjksProcessor implements Processor
{
    public LszsCxjksProcessor()
    {
    }

    /**
     * 通用处理调度模块
     * 
     * @param vo
     *            数据传递值对象
     * @return 数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        Object result = null;

        /** @todo Implement this com.ttsoft.framework.processor.Processor method */
        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            default:
                throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 页面初始化
     * 
     * @param vo
     *            数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow(VOPackage vo) throws BaseException
    {
        LszsCxjksForm pf = new LszsCxjksForm();
        try
        {
            pf = (LszsCxjksForm) doQuery(vo);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return pf;
    }

    /**
     * 查询 modifying by qianchao 2005.10.26
     * 
     * 不再使用map传输 页面数据，而使用SBData存储页面数据。
     * 
     * @param vo
     *            数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        LszsCxjksForm pf = new LszsCxjksForm();

        pf = (LszsCxjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            // 得到UserData
            ud = (UserData) vo.getUserData();
            // 设置查询条件
            Vector tempVector = new Vector();
            String qxdm = InterfaceDj.getQxdm(ud);
            String jsjdm = pf.getHeadJsjdm();

            tempVector.addElement("lrr='" + pf.getLrrdm() + "'");
            tempVector.addElement("sjly='" + CodeConstant.SMSB_SJLY_LSZSLR + "'");
            tempVector.addElement("FSDM='" + CodeConstant.FSDM_SMSB + "'");
            tempVector.addElement("zwbs like '" + CodeConstant.SMSB_ZWBS + "%" + CodeConstant.SMSB_ZWBS + "'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("jsjdm='" + jsjdm + "'");

            // 子查询

            // started modified by qianchao 2005-12-19
//            tempVector.addElement("SBBH NOT IN " + "(SELECT SBBH FROM SBDB.SB_JL_SBJKZB "
//                    + " WHERE ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '" + qxdm + "' AND JSJDM = '" + jsjdm
//                    + "'" + " AND SJLY = '" + CodeConstant.SMSB_SJLY_LSZSLR + "' AND FSDM = '" + CodeConstant.FSDM_SMSB
//                    + "' AND ((substr(zwbs, 1, 1) <> '0') OR (substr(zwbs, 20, 1) <> '0')))");

            tempVector.addElement("ZYRQ >= to_date('20050101','yyyymmdd')");
            tempVector.addElement("ND=to_char(sysdate,'yyyy')");
            // ended modified by qianchao 2005-12-19

            // tempVector.addElement("jkpzh like '" + pf.getHeadJsjdm() + "%'");
            tempVector.addElement("1=1 order by sbrq desc,sbbh,jkpzh asc ");

            // 查询
            //started added by qianchao 2005-12-19
            long t = System.currentTimeMillis();
            //ended   added by qianchao 2005-12-19
            List tempList = da.query(Sbjkzb.class, tempVector);
            //started added by qianchao 2005-12-19
            Debug.out("com.ttsoft.bjtax.smsb.lszs.processor.LszsCxjksProcessor.doQuery time cost:" + (System.currentTimeMillis() - t));
            //ended   added by qianchao 2005-12-19

            Debug.out("tempList.size()=" + tempList.size());

            HashMap sbmaps = new HashMap();
            SBData sb = null;
            for (int i = 0; i < tempList.size(); i++)
            {
                // 格式化每条纪录
                Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
                sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", sbjkzb.getSzdm())); // 税种名称
                sb = (SBData) sbmaps.get(sbjkzb.getSbbh());
                if (sb == null)
                {
                    sb = new SBData();
                    sbmaps.put(sbjkzb.getSbbh(), sb);
                }
                sb.addSbjkzb(sbjkzb);
            }

            // 将map转为list
            Iterator c = sbmaps.values().iterator();
            ArrayList datalist = new ArrayList();

            while (c.hasNext())
            {
                datalist.add(c.next());
            }
            // 把值放回form对象
            pf.setDataList(datalist);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return pf;
    }

    /**
     * 保存
     * 
     * @param vo
     *            数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException
    {
        return null;
    }

    /**
     * 删除 modified by qianchao 2005.10.27 加入对一票多税的处理
     * 
     * @param vo
     *            数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {

        Connection conn = null;

        int nret;
        UserData ud = new UserData();
        // from对象
        LszsCxjksForm pf = new LszsCxjksForm();
        pf = (LszsCxjksForm) vo.getData();
        ResultSet rs = null;
        int zbcount = 0;

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            // 得到UserData
            ud = (UserData) vo.getUserData();
            String strSql;
            if (pf.getJksType() == CodeConstant.PRINT_YPYS)
            {
                strSql = "delete from sbdb.sb_jl_sbjkmx " + " where qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                        + " and jsjdm='" + pf.getHeadJsjdm() + "'" + " and jkpzh='" + pf.getHeadJkpzh() + "'";
            }
            else
            {
                strSql = "delete from sbdb.sb_jl_sbjkmx " + " where qxdm='" + InterfaceDj.getQxdm(ud) + "'"
                        + " and jsjdm='" + pf.getHeadJsjdm() + "'" + " and sbbh='" + pf.getHeadSbbh() + "'";
            }
            Debug.out("strSql=" + strSql);

            // " and zwbs like
            // '"+CodeConstant.SMSB_ZWBS+"%"+CodeConstant.SMSB_ZWBS+"'";
            // 1、删除明细表数据
            try
            {
                nret = da.updateSQL(strSql);
                if (nret == 0)
                {
                    // 如果没有可删除数据则回滚
                    throw new ApplicationException("删除明细表数据出错！无明细数据。");
                }
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除明细表数据出错！");
            }

            // 2、查出主表数据
            try
            {
                if (pf.getJksType() == CodeConstant.PRINT_YPYS)
                {
                    strSql = " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" + " and jsjdm='" + pf.getHeadJsjdm() + "'"
                            + " and jkpzh='" + pf.getHeadJkpzh() + "'";
                }
                else
                {
                    strSql = " where qxdm='" + InterfaceDj.getQxdm(ud) + "'" + " and jsjdm='" + pf.getHeadJsjdm() + "'"
                            + " and sbbh='" + pf.getHeadSbbh() + "'";
                }
                rs = da.querySQL("select count(*) from sbdb.sb_jl_sbjkzb " + strSql);
                rs.next();
                zbcount = rs.getInt(1);

                Debug.out("zbcount=" + zbcount);

                strSql = "delete from sbdb.sb_jl_sbjkzb " + strSql + " AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%"
                        + CodeConstant.SMSB_ZWBS + "'  ";

                Debug.out("strSql=" + strSql);

                nret = da.updateSQL(strSql);

                Debug.out("nret=" + nret);

                if (nret != zbcount)
                {
                    // 可删除数据不符合，则回滚
                    throw new ApplicationException("撤销缴款书失败！主表数据已经变更。");
                }

            }
            catch (ApplicationException aex)
            {
                throw aex;
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除主表数据出错！");
            }
            pf.setHeadJkpzh("");
            pf.setHeadSbbh("");
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 更新
     * 
     * @param vo
     *            数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate(VOPackage vo) throws BaseException
    {
        return null;
    }

}
// :-)

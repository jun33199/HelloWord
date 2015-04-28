/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.shenbao.model.domain.Szzqsz;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.ZqwhHelper;
import com.ttsoft.bjtax.smsb.zqwh.web.SzzqdzForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 税种与征期类型对照关系维护</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class SzzqdzProcessor
    implements Processor
{

    /**
     * 用户信息对象
     */
    private UserData userData = null;

    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        //回传对象
        Object result = null;
        //判断VO是否为空
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //根据Action的值调用不同的process方法
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //前台默认显示
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //查询
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_LOADACTION:
                result = doImport(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 获得前台默认显示控制的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * 获得查询结果的ActionForm
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        List slist = new ArrayList();
        //得到Action传递过来ActionForm对象
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        //建立数据库连接
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();

            SfDBUtils sfDB = new SfDBUtils(conn);
            //记录查询条件
            form.setHeadDjzclx(form.getTempDjzclx());
            form.setHeadNd(form.getTempNd());
            form.setHeadSz(form.getTempSz());
            form.setHeadZqlx(form.getTempZqlx());

            //得到sql语句
            String sql = getQuerySql(form, "query");
            //检索数据
            slist = sfDB.getDataList(sql
                                     + " ORDER BY A.SZDM,A.ZQLXDM,A.DJZCLXDM");
            if (slist.size() <= 0)
            {
                throw new ApplicationException("没有找到指定的记录！");
            }

            form.setDataList(slist);
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * 删除
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        String strSql = "delete from sbdb.sb_jl_szzqsz where ";
        String allCon = " ";
        String strSql2 = "delete from sbdb.sb_jl_zqrl where ";
        String allCon2 = " ";
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            String[] deleteCheckbox = form.getDeleteCheckBox();
            if (deleteCheckbox != null)
            {

                Szzqsz szzqsz = new Szzqsz();
                Zqrl zqrl = new Zqrl();
                for (int i = 0; i < deleteCheckbox.length; i++)
                {
                    String primaryKey = deleteCheckbox[i];
                    String tempStr = "";
                    //拆分选中的纪录的各项主键值
                    String szdm = primaryKey.substring(0,
                        primaryKey.indexOf("|"));
                    tempStr = primaryKey.substring(primaryKey.indexOf("|") + 1);
                    String zqlxdm = tempStr.substring(0, tempStr.indexOf("|"));
                    String djzclxdm = tempStr.substring(tempStr.lastIndexOf("|")
                        + 1);

                    //删除两个表的数据
                    allCon = allCon + " (" +
                             " szdm = '" + szdm + "' " +
                             " and zqlxdm = '" + zqlxdm + "'" +
                             " and ND = '" + form.getTempNd() + "') or ";

                    //查询并删除
                    String wherCon = " ";
                    if (!djzclxdm.equals("*"))
                    {
                        wherCon = " and DJZCLXDM = '" + djzclxdm + "' ";
                    }

                    allCon2 = allCon2 + " (" +
                              " szsmdm like '" + szdm + "%' " +
                              " and zqlxdm = '" + zqlxdm + "'" + wherCon +
                              " and ND = '" + form.getTempNd() + "') or ";
                }
            }
            //删除对照表
            strSql = strSql + allCon.substring(0, allCon.length() - 3);
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除对照表数据出错！");
            }

            //删除征期日历
            strSql2 = strSql2 + allCon2.substring(0, allCon2.length() - 3);
            try
            {
                da.updateSQL(strSql2);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除征期日历出错！");
            }
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

        return form;
    }

    /**
     * 批量导入
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doImport (VOPackage vo)
        throws BaseException
    {
        userData = vo.getUserData();
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        Connection conn = null;
        try
        {
            //获得数据库连接
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //1.检查数据
            Vector tempVector = new Vector();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            List tempList = da.query(Szzqsz.class, tempVector);
            if (tempList.size() > 0)
            {
                throw new ApplicationException("要导入的数据在对照表已经存在！");
            }

            //2.检查征期日历数据
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            tempList = da.query(Czqrl.class, tempVector);
            if (tempList == null || tempList.size() <= 0)
            {
                throw new ApplicationException("征期日历表没有可用数据！请先生成"
                                               + form.getTempNd() +
                                               "年度的征期日历！");
            }

            //3.清空征期日历表，如果有，则清除！
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getTempNd() + "' ");
            tempList = da.query(Zqrl.class, tempVector);
            if (tempList.size() > 0)
            {
                da.delete(tempList);
            }

            //4.查出原有对照表数据
            tempVector.clear();
            tempList.clear();
            tempVector.add(" ND = '" + form.getImportNd() + "' ");
            tempList = da.query(Szzqsz.class, tempVector);
            if (tempList == null || tempList.size() <= 0)
            {
                throw new ApplicationException("根据输入的" + form.getImportNd() +
                                               "年度，对照表没有可用数据！");
            }

            //5.设置插入年份，然后插入对照表
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = new Szzqsz();
                dz = (Szzqsz) tempList.get(i);
                dz.setNd(form.getTempNd());
            }
            //保存数据到对照表信息
            try
            {
                da.insert(tempList);
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("保存失败！");
            }

            //6.创建最终使用的征期日历
            try
            {
                autoCreateZqrl(form, da);
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(ex1.getMessage());
            }
            form.setImportNd("");
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * 保存
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        userData = vo.getUserData();
        SzzqdzForm form = (SzzqdzForm) vo.getData();
        Connection conn = null;
        try
        {
            //得到数据集
            List mapData = form.getDataList();
            if (mapData == null)
            {
                return form;
            }

            //存贮待插入数据值对象
            List mxSaveData = new ArrayList();
            //获得数据库连接
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);

            //得到sql语句
            String sql = getQuerySql(form, "save");
            //检索数据，并删除
            SfDBUtils sfDB = new SfDBUtils(conn);
            List slist = sfDB.getDataList(sql);

            List deleteOfSaveList = new ArrayList();
            for (int i = 0; i < slist.size(); i++)
            {
                Map map = (Map) slist.get(i);
                Szzqsz s = new Szzqsz();
                //将数据传递值对象
                BeanUtil.populate(s, map);
                deleteOfSaveList.add(s);
            }
            da.delete(deleteOfSaveList);

            //取出税种征期（登记注册类型）对照表数据，查询原有数据，以便对比
            Vector szzqVector = new Vector();
            szzqVector.add(" ND = '" + form.getTempNd() + "'");
            List tempList = da.query(Szzqsz.class, szzqVector);

            //整理插入值
            for (int i = 0; i < mapData.size(); i++)
            {
                Map map = (Map) mapData.get(i);
                Szzqsz orMx = new Szzqsz();
                //将数据传递给明细值对象
                BeanUtil.populate(orMx, map);
                orMx.setNd(form.getTempNd()); //设置年度
                Timestamp timeNow = SfTimeUtil.getNowTimestamp();
                orMx.setCjrq(timeNow);
                orMx.setLrrq(timeNow);
                orMx.setSwjgzzjgdm(userData.getSsdwdm());
                orMx.setZqksrq(new BigDecimal("1")); //字段不能为空，给定临时值
                orMx.setZqts(new BigDecimal("10")); //字段不能为空，给定临时值
                mxSaveData.add(orMx);
            }

            //查重操作
            if (tempList.size() >= 0)
            {
                for (int i = 0; i < tempList.size(); i++)
                {
                    Szzqsz tmpSzdz1 = (Szzqsz) tempList.get(i);
                    for (int j = 0; j < mxSaveData.size(); j++)
                    {
                        Szzqsz tmpSzdz2 = (Szzqsz) mxSaveData.get(j);
                        //如果登记注册类型
                        if (tmpSzdz1.getDjzclxdm().equals("*") ||
                            tmpSzdz2.getDjzclxdm().equals("*") ||
                            tmpSzdz1.getDjzclxdm().equals(tmpSzdz2.getDjzclxdm()))
                        {
                            if (tmpSzdz1.getSzdm().startsWith(tmpSzdz2.getSzdm()) ||
                                tmpSzdz2.getSzdm().startsWith(tmpSzdz1.getSzdm()))
                            {
                                throw new ApplicationException("保存失败！税种：" +
                                    CodeUtils.getCodeBeanLabel("DM_SZSM",
                                    tmpSzdz1.getSzdm()) +
                                    " 在当前年份里已经存在！");
                            }
                        }
                    } //end of loop2
                } //end of loop1
            } //End of if

            //进行插入操作
            try
            {
                //插入数据
                da.insert(mxSaveData);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException(ex1.getMessage() + "保存失败！");
            }

            //Modified by lufeng 2004-02-19
            //生成税种征期日历对照表
            try
            {
                createSzZqrl(form, da, mxSaveData);
            }
            catch (BaseException ex2)
            {
                throw new ApplicationException("保存失败！" + ex2.getMessage());
            }
            //更新税种税目代码
            //updateSzsmdm(new SfDBUtils(conn), form.getTempNd());
        }
        catch (Exception ex)
        {
            //抛出异常
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //释放数据库连接
            SfDBResource.freeConnection(conn);
        }
        return form;
    }

    /**
     * 生成税种税目征期日历
     * @param pf 当前Form对象
     * @param da 数据访问对象
     * @param tempList 待插入的征期日历List
     * @throws BaseException
     */
    private void createSzZqrl (SzzqdzForm pf, SfDBAccess da, List tempList)
        throws
        BaseException
    {
        try
        {
            //对照表的数据集，包括拆分后的登记注册类型
            List szdzList = new ArrayList();
            //生成税种征期日历对照表
            //删除本年度所有税种征期日历
            //本年度所有的征期日历
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();
            //跟据条件删除，然后再插入
            String whereCon = "";
            if (pf.getTempDjzclx() != null && !pf.getTempDjzclx().equals("*") &&
                !pf.getTempDjzclx().equals(""))
            {
                whereCon += " AND DJZCLXDM='" + pf.getTempDjzclx() + "'";
            }
            if (pf.getTempSz() != null && !pf.getTempSz().equals("*") &&
                !pf.getTempSz().equals(""))
            {
                whereCon += " AND SZSMDM LIKE '" + pf.getTempSz() + "%'";
            }
            if (pf.getTempZqlx() != null && ! (pf.getTempZqlx()).equals("*") &&
                ! (pf.getTempZqlx()).equals(""))
            {
                whereCon += " AND ZQLXDM='" + pf.getTempZqlx() + "'";
            }

            whereCon = "delete from sbdb.sb_jl_zqrl where 1=1 " + whereCon +
                       " AND ND = '" + pf.getTempNd() + "'";
            try
            {
                da.updateSQL(whereCon);
            }
            catch (BaseException ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("删除原有征期日历失败！");
            }

            //取出税种征期（登记注册类型）对照表数据
            Vector szzqVector = new Vector();
            whereCon = "";
            if (pf.getTempDjzclx() != null && !pf.getTempDjzclx().equals("*") &&
                !pf.getTempDjzclx().equals(""))
            {
                whereCon += " AND DJZCLXDM='" + pf.getTempDjzclx() + "'";
            }
            if (pf.getTempSz() != null && !pf.getTempSz().equals("*") &&
                !pf.getTempSz().equals(""))
            {
                whereCon += " AND SZDM LIKE '" + pf.getTempSz() + "%'";
            }
            if (pf.getTempZqlx() != null && ! (pf.getTempZqlx()).equals("*") &&
                ! (pf.getTempZqlx()).equals(""))
            {
                whereCon += " AND ZQLXDM='" + pf.getTempZqlx() + "'";
            }

            ZqwhHelper helper = new ZqwhHelper();
            ZqwhHelper helper2 = new ZqwhHelper();
            //得到所有税目代码
            ArrayList smList = (ArrayList) helper.getSmList();
            ArrayList smTempList = new ArrayList();
            smTempList = (ArrayList) smList.clone();
            //得到所有税种代码
            List allSzList = helper2.getSzList();
            //得到所有登记注册类型代码
            List allDjzclxList = helper.getDjzclxList();
            //把登记注册类型列表中的*号去掉
            for (int i = 0; i < allDjzclxList.size(); i++)
            {
                LabelValueBean djzclx = (LabelValueBean) allDjzclxList.get(i);
                if (djzclx.getValue().equals("*"))
                {
                    allDjzclxList.remove(i);
                    break;
                }
            }

            //把对照表的登记注册类型为“全部”（*）的转化为单个的
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = (Szzqsz) tempList.get(i);
                if (dz.getDjzclxdm().equals("*"))
                {
                    for (int n = 0; n < allDjzclxList.size(); n++)
                    {
                        Szzqsz dzTemp = new Szzqsz();
                        dzTemp.setCjrq(dz.getCjrq());
                        dzTemp.setLrrq(dz.getLrrq());
                        dzTemp.setNd(dz.getNd());
                        dzTemp.setSwjgzzjgdm(dz.getSwjgzzjgdm());
                        dzTemp.setSzdm(dz.getSzdm());
                        dzTemp.setZqksrq(dz.getZqksrq());
                        dzTemp.setZqlxdm(dz.getZqlxdm());
                        dzTemp.setZqts(dz.getZqts());

                        LabelValueBean djzclx = (LabelValueBean) allDjzclxList.
                                                get(n);
                        dzTemp.setDjzclxdm(djzclx.getValue());
                        //把整理好的对照表信息放入List
                        szdzList.add(dzTemp);
                    }
                }
                else
                {
                    //把整理好的对照表信息放入List
                    szdzList.add(dz);
                }
            }

            //插入zqrl表，生成最终的征期日历
            try
            {
                insertZqrl(da, szdzList, smTempList, allSzList, pf.getTempNd());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException("生成最终征期日历失败！" + ex1.getMessage());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 向税种征期日历表中插入数据
     * @param da 数据操作对象
     * @param dzList 对照表数据
     * @param smList 税目总数
     * @param allSzList 税种总数
     * @param strNd 年度
     * @throws BaseException
     */
    private void insertZqrl (SfDBAccess da, List dzList, List smList,
                             List allSzList, String strNd)
        throws BaseException
    {
        Timestamp timeNow = SfTimeUtil.getNowTimestamp();
        String[] ZQRLNames =
            {
            "szsmdm", "zqlxdm", "zqlxmc", "zqssrqq", "zqssrqz",
            "zqqsrq",
            "zqzzrq"};
        try
        {
            //1.得到当前年份现有的征期日历，以便对比
            Vector tempVector = new Vector();
            tempVector.add(" ND = '" + strNd + "' ");
            List zqrlList = da.query(Zqrl.class, tempVector);

            //2.得到日历
            tempVector.clear();
            tempVector = new Vector();
            tempVector.add(" ND = '" + strNd + "' ");
            List tempList = da.query(Czqrl.class, tempVector);

            if (tempList.size() <= 0)
            {
                throw new ApplicationException("征期日历表没有可用数据！请先生成" + strNd
                                               + "年度的征期日历！");
            }

            Timestamp nowTime = new Timestamp(System.currentTimeMillis());
            //循环对照表的信息。
            List insertZqrlList = new ArrayList();
            for (int i = 0; i < dzList.size(); i++)
            {
                Szzqsz szzq = (Szzqsz) dzList.get(i);
                String strSzdm = szzq.getSzdm();
                //得到征期类型对应的中间征期日历
                List czqrlList = new ArrayList();
                for (int k = 0; k < tempList.size(); k++)
                {
                    Czqrl tempCzqrl = (Czqrl) tempList.get(k);
                    if (tempCzqrl.getZqlxdm().equals(szzq.getZqlxdm()))
                    {
                        czqrlList.add(tempCzqrl);
                    }
                } //End of czqrl

                //3.循环插入税目，即最叶子的税目代码
                for (int j = 0; j < smList.size(); j++)
                {
                    LabelValueBean smBean = (LabelValueBean) smList.get(j);
                    if (smBean.getValue().startsWith(strSzdm))
                    {
                        //循环日历
                        for (int k = 0; k < czqrlList.size(); k++)
                        {
                            Czqrl czqrl = (Czqrl) czqrlList.get(k);
                            Zqrl tmpZq = new Zqrl();
                            BeanUtil.copyBeanToBean(ZQRLNames, czqrl, tmpZq);
                            //设置征期日历各项值
                            tmpZq.setSzsmdm(smBean.getValue());
                            tmpZq.setCjrq(timeNow);
                            tmpZq.setLrrq(timeNow);
                            tmpZq.setSwjgzzjgdm(userData.getSsdwdm());
                            tmpZq.setNd(strNd);
                            tmpZq.setDjzclxdm(szzq.getDjzclxdm()); //登记注册类型
                            tmpZq.setZqksrq(szzq.getZqksrq()); //征期开始日期
                            tmpZq.setZqts(szzq.getZqts()); //征期天数

                            //把对象放入List
                            insertZqrlList.add(tmpZq);
                        }
                    } //End of if
                }
            } //End of first loop

            //插入数据
            try
            {
                if (insertZqrlList.size() > 0)
                {
                    da.insert(insertZqrlList);
                }
            }
            catch (Exception ex2)
            {
                ex2.printStackTrace();
                throw new ApplicationException("征期日历插入数据出错！");
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
    }

    /**
     * 生成税种税目征期日历
     * @param pf 当前Form对象
     * @param da 数据访问对象
     * @throws BaseException
     */
    private void autoCreateZqrl (SzzqdzForm pf, SfDBAccess da)
        throws
        BaseException
    {
        try
        {
            //对照表的数据集，包括拆分后的登记注册类型
            List szdzList = new ArrayList();
            //生成税种征期日历对照表
            //本年度所有的征期日历
            Zqrl zqrl = new Zqrl();
            Vector zqrlVector = new Vector();

            //取出税种征期（登记注册类型）对照表数据
            Vector szzqVector = new Vector();
            szzqVector.add(" ND = '" + pf.getTempNd() + "'");
            List tempList = da.query(Szzqsz.class, szzqVector);

            ZqwhHelper helper = new ZqwhHelper();
            ZqwhHelper helper2 = new ZqwhHelper();
            //得到所有税目代码
            ArrayList smList = (ArrayList) helper.getSmList();
            ArrayList smTempList = new ArrayList();
            smTempList = (ArrayList) smList.clone();
            //得到所有税种代码
            List allSzList = helper2.getSzList();
            //得到所有登记注册类型代码
            List allDjzclxList = helper.getDjzclxList();
            //把登记注册类型列表中的*号去掉
            for (int i = 0; i < allDjzclxList.size(); i++)
            {
                LabelValueBean djzclx = (LabelValueBean) allDjzclxList.get(i);
                if (djzclx.getValue().equals("*"))
                {
                    allDjzclxList.remove(i);
                    break;
                }
            }

            //把对照表的登记注册类型为“全部”（*）的转化为单个的
            for (int i = 0; i < tempList.size(); i++)
            {
                Szzqsz dz = (Szzqsz) tempList.get(i);
                if (dz.getDjzclxdm().equals("*"))
                {
                    for (int n = 0; n < allDjzclxList.size(); n++)
                    {
                        Szzqsz dzTemp = new Szzqsz();
                        dzTemp.setCjrq(dz.getCjrq());
                        dzTemp.setLrrq(dz.getLrrq());
                        dzTemp.setNd(dz.getNd());
                        dzTemp.setSwjgzzjgdm(dz.getSwjgzzjgdm());
                        dzTemp.setSzdm(dz.getSzdm());
                        dzTemp.setZqksrq(dz.getZqksrq());
                        dzTemp.setZqlxdm(dz.getZqlxdm());
                        dzTemp.setZqts(dz.getZqts());

                        LabelValueBean djzclx = (LabelValueBean) allDjzclxList.
                                                get(n);
                        dzTemp.setDjzclxdm(djzclx.getValue());
                        //把整理好的对照表信息放入List
                        szdzList.add(dzTemp);
                    }
                }
                else
                {
                    //把整理好的对照表信息放入List
                    szdzList.add(dz);
                }
            }

            //插入zqrl表，生成最终的征期日历
            try
            {
                insertZqrl(da, szdzList, smTempList, allSzList, pf.getTempNd());
            }
            catch (BaseException ex1)
            {
                throw new ApplicationException(ex1.getMessage());
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * 得到查询所用SQL语句
     * @param sf 当前页面对应的Form对象
     * @param flag 查询（query）或保存（save）标记
     * @return sql语句条件
     */
    private String getQuerySql (SzzqdzForm sf, String flag)
    {
        String sql = "";
        String whereCon = "";
        //得到查询条件
        //查询，则使用用户输入值，保存则使用临时值
        if (flag.equals("query"))
        {
            if (sf.getHeadDjzclx() != null && !sf.getHeadDjzclx().equals("*") &&
                !sf.getHeadDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getHeadDjzclx() + "'";
            }
            if (sf.getHeadSz() != null && !sf.getHeadSz().equals("*") &&
                !sf.getHeadSz().equals(""))
            {
                whereCon += " AND A.SZDM LIKE '" + sf.getHeadSz() + "%'";
            }
            if (sf.getHeadZqlx() != null && ! (sf.getHeadZqlx()).equals("*") &&
                ! (sf.getHeadZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getHeadZqlx() + "'";
            }
        }
        else if (flag.equals("save"))
        {
            if (sf.getTempDjzclx() != null && !sf.getTempDjzclx().equals("*") &&
                !sf.getTempDjzclx().equals(""))
            {
                whereCon += " AND A.DJZCLXDM='" + sf.getTempDjzclx() + "'";
            }
            if (sf.getTempSz() != null && !sf.getTempSz().equals("*") &&
                !sf.getTempSz().equals(""))
            {
                whereCon += " AND A.SZDM LIKE '" + sf.getTempSz() + "%'";
            }
            if (sf.getTempZqlx() != null && ! (sf.getTempZqlx()).equals("*") &&
                ! (sf.getTempZqlx()).equals(""))
            {
                whereCon += " AND A.ZQLXDM='" + sf.getTempZqlx() + "'";
            }
        }

        sql =
            "SELECT A.SZDM,A.ZQLXDM,A.ND,A.DJZCLXDM FROM SBDB.SB_JL_SZZQSZ A "
            + " WHERE A.ND = '" + sf.getTempNd() + "' "
            + whereCon;

        return sql;
    }

    /**
     * 将Timestamp型转换为一个String型日期 （eg.20030611）
     * @param gCalendar  日期
     * @return String型日期 （eg.20030611）
     */
    private String calendarToString (Calendar gCalendar)
    {
        int month = gCalendar.get(Calendar.MONTH) + 1;
        int date = gCalendar.get(Calendar.DATE);
        String strMonth = "" + month;
        String strDate = "" + date;
        if (month < 10)
        {
            strMonth = "0" + strMonth;
        }
        if (date < 10)
        {
            strDate = "0" + strDate;
        }
        String strDay = "" + gCalendar.get(Calendar.YEAR) + strMonth + strDate;
        return strDay;
    }

    /**
     * 更新税种税目代码表
     * @param sfDB 数据访问对象
     * @param Nd 年度
     * @throws ApplicationException
     */
    private void updateSzsmdm (SfDBUtils sfDB, String Nd)
        throws
        ApplicationException
    {
        try
        {
            String updateSql =
                " UPDATE DMDB.SB_DM_SZSM x SET x.zqlxdm=(SELECT d.jglxdm FROM "
                + " (select distinct A.SZSMDM,B.JGLXDM "
                + " from SBDB.SB_JL_ZQRL A,DMDB.SB_DM_ZQLX B "
                + " where A.ZQLXDM = B.ZQLXDM and  B.TYRQ is Null "
                + " and Length(A.SZSMDM)=6 AND A.ND ='" + Nd + "') d "
                + " WHERE x.szsmdm=d.szsmdm) "
                + " WHERE x.szsmdm IN (select distinct A.SZSMDM "
                + " from SBDB.SB_JL_ZQRL A,DMDB.SB_DM_ZQLX B "
                + " where A.ZQLXDM = B.ZQLXDM and  B.TYRQ is Null "
                + " and Length(A.SZSMDM)=6 AND A.ND ='" + Nd + "') ";
            int result = sfDB.executeSql(updateSql);
            if (result == -1)
            {
                throw new Exception();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("更新税种税目代码表时发生错误。");
        }
    }

}

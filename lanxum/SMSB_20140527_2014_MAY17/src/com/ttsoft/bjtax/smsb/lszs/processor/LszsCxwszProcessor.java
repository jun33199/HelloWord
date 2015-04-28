/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.sql.Timestamp;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszmx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsswszz;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsCxwszForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收撤销完税证</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsCxwszProcessor
    implements Processor
{
    public LszsCxwszProcessor ()
    {
    }

    /**
     * 通用处理调度模块
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        Object result = null;

            /**@todo Implement this com.ttsoft.framework.processor.Processor method*/
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
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }

    /**
     * 页面初始化
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();

        Connection conn = null;
        SfDBUtils sfDB = null;
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String ndzb = String.valueOf(nowTime).substring(0, 4);

        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        //年度字别
        pf.setTempNdzb(ndzb);

        try
        {
            //conn = SfDBResource.getConnection();
            //sfDB = new SfDBUtils(conn);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            //SfDBResource.freeConnection(conn);
        }
        return pf;
    }

    /**
     * 查询
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        String names[] =
                         {
                         "pzzldm", "wszh", "jsjdm", "swjgzzjgdm", "djzclxdm",
                         "nsrmc", "dz", "hjsjje", "clbjdm", "sbhzdh",
                         "jbdh", "zjlxdm", "zjhm", "swjgzzjgdm2", "lrr",
                         "fsdm", "gjbzhydm", "nd", "wszxh", "cjrq",
                         "lrrq", "qxdm", "ndzb", "printflag"};

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            //设置查询条件
            Vector tempVector = new Vector();
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("lrr='" + ud.getYhid() + "'");
            if (!pf.getHeadClbjdm().equals("*"))
            {
                tempVector.addElement("clbjdm='" + pf.getHeadClbjdm() + "'");
            }
            tempVector.addElement(
                "wszxh in (select b.wszxh from sbdb.sb_jl_lsswszmx b WHERE substr(b.jzbz,1,1)='"
                + CodeConstant.SMSB_JZBZ_EDIT + "')");
            tempVector.addElement("(sbhzdh='' or sbhzdh is null)");
            tempVector.addElement("(jbdh='' or jbdh is null)"); //Modified by lufeng 2004-05-11
            tempVector.addElement("ndzb='" + pf.getTempNdzb() + "'");
            tempVector.addElement("1=1 order by wszh asc ");

            //查询
            List tempList = da.query(Lsswszz.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合条件的记录！");
            }
            for (int i = 0; i < tempList.size(); i++)
            {
                //格式化每条纪录
                Lsswszz wszz = (Lsswszz) tempList.get(i);
                //将值对象的值赋给Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, wszz, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("数据转换出错！");
                }

                //map.put("hjsjje",wszz.getHjsjje().toString());
                map.put("cjrq", wszz.getCjrq().toString().substring(0, 19));
                map.put("lrrq", wszz.getLrrq().toString().substring(0, 19));
                //设置显示值
                if (map.get("clbjdm").equals("1"))
                {
                    map.put("printflag", "已打印");
                }
                else
                {
                    map.put("printflag", "未打印");
                }
                dataList.add(map);
            }
            //把值放回form对象
            pf.setDataList(dataList);

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
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
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
        List dataList = new ArrayList();
        String pzzldm = ""; //票证种类代码
        String zhdm = ""; //帐户代码
        String wszh = ""; //完税证号

        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from对象
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        dataList = (List) pf.getDataList();
        //得到帐户代码
        zhdm = pf.getHeadZhdm();

        //ormapping对象
        Lsswszz orObjz = new Lsswszz();
        Lsswszmx orObjmx = new Lsswszmx();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "delete from sbdb.sb_jl_lsswszmx " +
                            //" where pzzldm='" + CodeConstant.SMSB_PZZLDM + "'" +
                            " where qxdm='" + qxdm + "'" +
                            " and substr(jzbz,1,1)='"
                            + CodeConstant.SMSB_JZBZ_EDIT + "'" +
                            " and ndzb='" + pf.getHeadNdzb() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";

            //1、删除明细表数据
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除明细表数据出错！");
            }

            //2、查出主表数据 //Modified by lufeng 2004-05-11
            Vector tempVector = new Vector();
            //tempVector.addElement("pzzldm='" + CodeConstant.SMSB_PZZLDM + "'");
            tempVector.addElement("pzzldm='" + pf.getHeadPzzldm() + "'");
            tempVector.addElement("qxdm='" + qxdm + "'");
            tempVector.addElement("(sbhzdh='' or sbhzdh is null)");
            tempVector.addElement("(jbdh='' or jbdh is null)");
            tempVector.addElement("ndzb='" + pf.getTempNdzb() + "'");
            tempVector.addElement("wszh='" + pf.getHeadWszh() + "'");
            tempVector.addElement("wszxh='" + pf.getHeadWszxh() + "'");
            tempVector.addElement("jsjdm='" + pf.getHeadJsjdm() + "'");

            //查询
            List tempList = da.query(Lsswszz.class, tempVector);
            if (tempList.size() <= 0)
            {
                throw new ApplicationException("没有符合撤销的完税证，数据可能已被修改，请重新查询！");
            }
             else {
            	Lsswszz lsswszzsj = (Lsswszz)tempList.get(0);
            	if (lsswszzsj.getFsdm().equals(CodeConstant.SMSB_FSDM)){
                throw new ApplicationException("货运导入的完税证不能撤销！");
            	}         
            }
            try
            {
                da.delete(tempList);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("删除主表数据出错！");
            }
            //3、调用票证接口，作废当前完税证号，并不再取号
            try
            {
                String result = ServiceProxy.setCancellation(ud,
                	pf.getHeadPzzldm(),
                    pf.getHeadNdzb() +
                    pf.getHeadWszh(),
                    0, "1", "0", "1");
            }
            catch (Exception ex1)
            {
                throw new ApplicationException("撤销完税证号出错！");
            }
            pf.setHeadWszh("");
            pf.setHeadWszxh("");
            pf.setHeadNdzb("");
            pf.setHeadJsjdm("");
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
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        SfDBUtils sfDB = null;
        UserData ud = new UserData();
        //from对象
        LszsCxwszForm pf = new LszsCxwszForm();
        pf = (LszsCxwszForm) vo.getData();
        //ormapping对象
        Lsswszz orObjz = new Lsswszz();
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //得到UserData
            ud = (UserData) vo.getUserData();
            //的得到区县代码
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_lsswszz " +
                            " set clbjdm='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'" +
                            " and qxdm='" + qxdm + "'" +
                            " and jsjdm='" + pf.getHeadJsjdm() + "'" +
                            " and wszxh='" + pf.getHeadWszxh() + "'";

            //1、更新数据
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("更新数据出错！");
            }
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

}
//:-)

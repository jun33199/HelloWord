/*
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */
package com.ttsoft.bjtax.smsb.lszs.processor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lszs.web.LszsJkslrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统－－上门申报</p>
 * <p>Description: 零散征收录入缴款书</p>
 * @author 开发六组－－陆峰
 * @version 1.1
 */
public class LszsJkslrProcessor
    implements Processor
{
    public LszsJkslrProcessor ()
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
                result = doMoveToHis(vo);
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

        LszsJkslrForm pf = new LszsJkslrForm();
        pf = (LszsJkslrForm) vo.getData();
        try
        {
            //UserData对象
            UserData ud = (UserData) vo.getUserData();
            //填发日期
            pf.setSbrq(SfDateUtil.getDate());
            //从登记接口中获得信息
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }
            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //税务机关组织机构代码
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //税务机关组织机构名称

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
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

        Connection conn = null;
        SfDBUtils sfDB = null;
        LszsJkslrForm pf = new LszsJkslrForm();
        pf = (LszsJkslrForm) vo.getData();

        try
        {
            //UserData对象
            UserData ud = (UserData) vo.getUserData();

            //从登记接口中获得信息
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }
            pf.setNsrmc(dj.getNsrmc());
            pf.setGjbzhydm(dj.getGjbzhydm()); //国家标准行业代码
            pf.setDjzclxdm(dj.getDjzclxdm()); //登记注册类型代码
            pf.setDjzclxmc(dj.getDjzclxmc()); //
            pf.setDz(dj.getJydz()); //地址，经营地址
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
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
        String qxdm = ""; //区县代码
        //ormapping对象
        Sbjkzb orObj = new Sbjkzb();

        //start modifying by qianchao 2005.10.26
        HashMap hm = (HashMap)vo.getData();
        //获取form对象
        LszsJkslrForm form = (LszsJkslrForm)hm.get(CodeConstant.MAP_VO_FORM);
        int ypysORypds = ((Integer)hm.get(CodeConstant.MAP_VO_YPYSorYPDS)).intValue();
        //end modifying by qianchao 2005.10.26



        //获得UserData
        UserData ud = vo.getUserData();
        //定义与form种的columns 一样的变量。其中的名称为名细的字段名
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        try
        {
            //得到区县代码
            qxdm = InterfaceDj.getQxdm(ud);
            //通过登记接口取得纳税人相关信息
            //从登记接口中获得信息
            //SWDJJBSJ jbsj = new SWDJJBSJ();
            //补充主表信息
            HashMap mapDJ = new HashMap();
            //SWDJJBSJ jbsj = new SWDJJBSJ();
            try
            {
                mapDJ = (HashMap) InterfaceDj.getDjInfo(form.getJsjdm(), ud);
                //jbsj = InterfaceDj.getJBSJ(String.valueOf(map.get("jsjdm")), ud);
            }
            catch (Exception ex1)
            {
                ex1.printStackTrace();
                throw ExceptionUtil.getBaseException(ex1);
            }

            //Modified by lufeng 2003-12-09
            SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
            if (jbsj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }

            //补充主表信息
            //计算机代码
            orObj.setJsjdm(form.getJsjdm());

            //保存纳税人名称，证件类型，证件号码 到申报缴款的备注字段
            /**20040413 Shi Yanfeng  **/
            /***在零散的缴款书打印的时候把备注的信息都去掉不打印了，因为纳税人名称已经打印在缴款书的纳税人名称一栏中了**/
            orObj.setBz(form.getNsrmc() + " #$# " +
                        CodeUtils.getCodeBeanLabel("ZJLX", form.getZjlxdm())
                        + " " + form.getZjhm());
            //申报日期
            orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
            //登记注册类型
            orObj.setDjzclxdm(jbsj.getDjzclxdm());
            orObj.setDjzclxmc(jbsj.getDjzclxmc());
            //国家标准行业代码
            orObj.setGjbzhydm(jbsj.getGjbzhydm());
            orObj.setGjbzhymc(jbsj.getGjbzhymc());
            //税务机关组织机构
            orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
            //征收机关
            orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
            //隶属关系
            orObj.setLsgxdm(jbsj.getLsgxdm());
            orObj.setLsgxmc(jbsj.getLsgxmc());
            //经营地址联系电话
            orObj.setJydzlxdm(jbsj.getJydzlxdm());
            //录入人
            orObj.setLrr(form.getLrr());
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
            }
            //申报日期
            orObj.setSbrq(nowTime);
            orObj.setCjrq(nowTime); //创建日期
            orObj.setLrrq(nowTime); //录入日期
            orObj.setQxdm(qxdm); //区县代码

            //得到银行信息//Modified by lufeng 2003-12-09
            ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
            for (int i = 0; i < dmList.size(); i++)
            {
                YHZH yhzh = (YHZH) dmList.get(i);
                if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                {
                    orObj.setYhdm(yhzh.getYhdm()); //银行代码
                    orObj.setYhmc(yhzh.getKhyhmc()); //银行名称
                    orObj.setZh(yhzh.getZh()); //帐户
                    break;
                }
            }

            //申报方式代码
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //数据来源
            orObj.setSjly(CodeConstant.SMSB_SJLY_LSZSLR);
            //税款类型
            //orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
            //orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
          
            //税款类型 tujb 2014.03.13 增加税款类型条件
            orObj.setSklxdm(form.getSklxdm());
            orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
            //对数据进行分票保存
            JksUtil ju = new JksUtil();
            //return ju.getJkData(orObj, form.getDataList());
            try
            {

              //start modifying by qianchao 2005.10.26
              System.out.println("jsjdm:" + form.getJsjdm() +  "== 分票类型：" + ypysORypds);
              List tempList = (ArrayList) ju.getJkDataLS(orObj,form.getDataList(),ypysORypds);
              form.setDataList(tempList);
              //end modifying by qianchao 2005.10.26
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("保存数据失败！");
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
    }
    
    private Boolean doMoveToHis (VOPackage vo) throws BaseException{
        //获得UserData
        UserData ud = vo.getUserData();
        Map moveMap = (Map)vo.getData();
        String jsjdm = (String)moveMap.get("jsjdm");
        List jkpzhList = (List)moveMap.get("jkpzhList");
        try{  
        	JksUtil ju = new JksUtil();
        	ju.moveSbjkToHis(jsjdm, jkpzhList, ud.getYhid());
        }catch (Exception ex){
            //throw ExceptionUtil.getBaseException(ex, "系统错误请与管理员联系!");
            throw ExceptionUtil.getBaseException(ex);
        }
        
    	return new Boolean(true);
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
        return null;
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
        return null;
    }
    
    /**
     * 根据税款类型代码查询税款类型名称
     * @param sklx String 税款类型代码
     * @return String 税款类型名称
     */
    private String getSklxmc (String sklx)
    {

        //根据代码表返回税款类型名称
        return CodeUtils.getCodeBeanLabel("LSSB_SKLX", sklx);
    }

}
//:-)

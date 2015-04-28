/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，清华同方软件股份有限公司，版权所有. </p>
 * <p>Company: 清华同方软件股份有限公司</p>
 */

package com.ttsoft.bjtax.smsb.dzyj.processor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.dj.DjOuterConstant;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.dzyj.web.DzyjcxActionForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: 北京地税综合管理系统  申报征收-上门模块</p>
 * <p>Description: 外籍个人所得税月份申报表</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DzyjProcessor
    implements Processor
{
    /**
     * 扩展Processor接口
     * @param vo  数据容器传递对象，来自Action
     * @return  数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
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
            case CodeConstant.SMSB_QUERYACTION: //查询
                result = this.doQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * 查询数据
     * @param vo  数据传递值对象
     * @return  数据结果对象[ActionForm]
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        DzyjcxActionForm form = (DzyjcxActionForm) vo.getData();
        UserData ud = vo.getUserData();
        String nsrssdwdm = "";
        String yhjb = ud.getYhjb();
        String ssdwdm = ud.getSsdwdm();
        
        
        String jsjdm = form.getJsjdm();
        String startdate = form.getQssj();
        String enddate = form.getJzsj();
        HashMap djmap;
        
        if (ud.getYhlx().equals("02"))
        {
            throw new ApplicationException("权限不足！不允许查询原件数据");
        }
        
        if ((jsjdm.substring(7).compareTo("0") >= 0) 
                && (jsjdm.substring(7).compareTo("9") <= 0))
        {
            SWDJJBSJ dj = null;
            try
            {
                djmap = InterfaceDj.getDjInfo(jsjdm,ud);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e);
            }
            dj = (SWDJJBSJ) djmap.get("JBSJ");
            if (dj == null)
            {
                throw new ApplicationException("获取登记信息出错！");
            }
            form.setNsrmc(dj.getNsrmc()); //纳税人名称
            nsrssdwdm = dj.getSwjgzzjgdm();
        }
        else
        {
            ZRRJBSJ zrrJbsj = null;
            try
            {
                djmap = (HashMap)InterfaceDj.getZRRInfo(jsjdm,ud);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw ExceptionUtil.getBaseException(e);
            }
            zrrJbsj = (ZRRJBSJ)djmap.get(DjOuterConstant.ZRRJBSJ);
            if (zrrJbsj == null)
            {
                throw new ApplicationException("获取自然人登记信息出错！");
            }
            form.setNsrmc(zrrJbsj.getNsrmc()); //纳税人全称
            nsrssdwdm = zrrJbsj.getSwjgzzjgdm();
        }
                
        
        
        String sql = "WHERE ";
        /*
        System.out.println("yhjb:" + yhjb);
        System.out.println("nsrssdwdm:" + nsrssdwdm);
        System.out.println("ssdwdm:" + ssdwdm);
        */
        if (yhjb.equals("40"))
        {
            if (! nsrssdwdm.startsWith(ssdwdm.substring(0,2)))
            {
                throw new ApplicationException("所填计算机代码不是本分局管辖！");
            }
        }
        else if (yhjb.equals("30"))
        {
            if (! nsrssdwdm.equals(ssdwdm))
            {
                throw new ApplicationException("所填计算机代码不是本所管辖！");
            }
        }
        else if (! yhjb.equals("50"))
        {
            throw new ApplicationException("权限不足！不允许查询原件数据");
        }
        sql += "JSJDM='" + jsjdm + 
            "' AND JSSJ >= to_date('" + startdate + 
            "','yyyymmdd') AND JSSJ <= to_date('" + enddate +
            "235959','yyyymmddHH24miss') ";
        /*
        Debug.out("form.getYwlx():" + form.getYwlx());
        Debug.out("form.getCzlx():" + form.getCzlx());
        Debug.out("form.getYwuid():" + form.getYwuid());
        */
        if (! form.getYwlx().equals("0"))
        {
            sql += " AND YWDM='" + form.getYwlx() + "' ";
        }
        if (! form.getCzlx().equals("0"))
        {
            sql += " AND YWCZLX='" + form.getCzlx() + "' ";
        }
        if (form.getYwuid().length() > 0)
        {
            sql += " AND YWUID like'%" + form.getYwuid() + "%' ";
        }
        
        sql += " ORDER BY LSH";
        
        Debug.log(" DZYJ QUERY: " + sql);
        
        DzyjsjVO dzvo;
        
        ArrayList ywlxlist = CodeManager.getCodeList("DM_CA_YWLX",CodeConstants.CODE_MAP_BEANLIST).getRecords();
        List resultlist = null;
        
        List pageresult = new ArrayList(); 
        
        List dataList = new ArrayList();
        Map item;
        int istart,iend;
        
        try
        {
            resultlist = CAProxy.getInstance().queryDzyjsj(sql,true);
            
            form.setTotalRecord(resultlist.size());
            
            istart = resultlist.size()/form.getPageLength();
            if (istart * form.getPageLength() < resultlist.size())
                istart ++;
            form.setPgSum(istart);
            
            istart = form.getPageLength() * (form.getPgNum()-1);
            iend = istart + form.getPageLength();
            
            if (iend > resultlist.size())
                iend = resultlist.size();

            //暂时不用分页。
            istart = 0;
            iend = resultlist.size();
            
            
            for (;istart < iend;istart ++)
            {
                dzvo = (DzyjsjVO)resultlist.get(istart);
                
                pageresult.add(dzvo);
                
                item = new HashMap();
                item.put("lsh",Long.toString(dzvo.getLsh()));
                item.put("ywlxmc",CodeUtils.getCodeBeanLabel("DM_CA_YWLX",dzvo.getYwdm()));
                if (dzvo.getYwczlx().equals("1"))
                    item.put("ywczmc","保存");
                else if (dzvo.getYwczlx().equals("2"))
                    item.put("ywczmc","修改");
                else
                    item.put("ywczmc","删除");
                item.put("ywuid",dzvo.getYwuid());
                item.put("jssj",(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(dzvo.getJssj()));
                
                dataList.add(item);
            }
            form.setResultList(pageresult);
            
            form.setYwlxList(ywlxlist);
            form.setDataList(dataList);
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        return form;
    }
}

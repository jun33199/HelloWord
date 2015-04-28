package com.ttsoft.bjtax.smsb.gdzys.sbzs.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java_cup.internal_error;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sbzs.web.GdzysCjjksForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysDateUtil;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysMoneyUtils;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统--耕地占用税征收管理</p>
 * <p>Description: 出具缴款书AProcessor</p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCjjksProcessor  implements Processor{
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
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION:
                result = doCkJks(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType有错误，processor中找不到相应的方法.");
        }
        return result;
    }
	/**
     * 查询缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    	//获取form
    	GdzysCjjksForm pf = (GdzysCjjksForm) vo.getData();
        List dataList = new ArrayList();
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    
	    try
        {
	    
	    String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"SBDB", "SB_JL_GDZYS_SBB" );
	    
	    System.out.println("##########datafilter="+datafilter);
	    
	    StringBuffer sqlBuf = new StringBuffer();
        
	    sqlBuf.append("select * from (");
	    //sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='0' and QRZT='1' and QRRQ is not null and CJR='"+ud.yhid+"' "+" and SFJMSSB='0' ");//
	    sqlBuf.append("select k.*,'1' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='0' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and k.SFJMSSB='0' and "+datafilter+" ");//无减免
	   // sqlBuf.append(" union all ");
	    //sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='0' and SBZT='40' and SFSJSP='1' and SFJMSSB='0' and CJR='"+ud.yhid+"' ");//市局无减免
	    sqlBuf.append(" union all ");
	    sqlBuf.append("select k.*,'2' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='2' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and "+datafilter+" and k.SFJMSSB='1' and k.SBBXLH not in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) and k.SBBXLH in (select SBBXLH from sbdb.SB_JL_GDZYS_JMSZM where DYBZ='1' and ZFBZ='0' and KJRQ is not null ) ");//有减免需出减免证明
	    sqlBuf.append(" union all ");
	    sqlBuf.append("select k.*,'3' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='2' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and "+datafilter+" and k.SFJMSSB='1' and k.SBBXLH  in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) and k.SBBXLH not in (select SBBXLH from sbdb.SB_JL_GDZYS_JMSZM where DYBZ='1' and ZFBZ='0' and KJRQ is not null ) ");//有减免无需出减免证明
	   // sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='2' and QRZT='1' and QRSJ is not null and CJR='"+ud.yhid+"' "+" and SFJMSSB='1' and SBBXLH in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) ");//有减免不需出减免证明
	    sqlBuf.append(") t where 1=1 ");
	    
        if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
        	sqlBuf.append(" and t.NSRMC like ?");
        }
        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
        	sqlBuf.append(" and t.JSJDM like ?");
        }
        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
        	sqlBuf.append(" and t.PZJDWH like ?");
        }
        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
        	sqlBuf.append(" and t.SBBXLH like ?");
        }

        System.out.println("#########="+sqlBuf.toString());
      
            conn = SfDBResource.getConnection();
            ps = conn.prepareStatement(sqlBuf.toString());   
            //计算机代码
		    int i=1;
		    if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
		    	 ps.setString(i++, "%"+pf.getNsrmc()+"%");
	        }
	        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
	        	 ps.setString(i++, "%"+pf.getJsjdm()+"%");
	        }
	        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
	        	 ps.setString(i++, "%"+pf.getPzjdwh()+"%");
	        }
	        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
	        	 ps.setString(i++, "%"+pf.getSbbxlh()+"%");
	        }
            
            rst = ps.executeQuery();
            while (rst.next()) {
            	HashMap map = new HashMap();
            	//申报表序列号
            	map.put("sbbxlh", rst.getString("sbbxlh")==null?"":rst.getString("sbbxlh"));
            	//纳税人名称
            	map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
            	//计算机代码
            	map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
            	//批准占地文号
            	map.put("pzjdwh", rst.getString("pzjdwh")==null?"":rst.getString("pzjdwh"));
            	//实际占地面积
            	map.put("sjzdmj", rst.getBigDecimal("sjzdmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjzdmj").doubleValue()));
            	//计税面积
            	map.put("jsmj", rst.getBigDecimal("jsmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jsmj").doubleValue()));
            	//减免面积
            	map.put("jmmj", rst.getBigDecimal("jmmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jmmj").doubleValue()));
            	//应纳税额
            	map.put("ynse", rst.getBigDecimal("ynse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ynse").doubleValue()));
            	//确认时间
            	map.put("qrrq", rst.getString("qrrq")==null?"":(rst.getString("qrrq").substring(0, 10)));
            	//创建日期
            	map.put("cjrq", rst.getString("cjrq")==null?"":(rst.getString("cjrq").substring(0, 10)));
            	//是否市局审批
            	map.put("sfsjsp",rst.getString("sfsjsp")==null?"":rst.getString("sfsjsp"));
            	//市局确认日期
            	map.put("sjqrrq",rst.getString("sjqrrq")==null?"":(rst.getString("sjqrrq").substring(0, 10)));
            	//flag 1:申报数据无减免缴款书数据；2：申报数据有减免需出减免证明缴款书数据；3：申报数据有减免无需出减免证明缴款书数据
            	map.put("flag", rst.getString("flag"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //把值放回form对象
            pf.setDataList(dataList);
            //关闭resultset
		    rst.close();
		    //关闭preparestatement
		    ps.close();

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
     * 查看缴款书数据
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的Form对象
     * @throws BaseException
     */
    private Object doCkJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //的到当前用户对象
    	//获取form
    	GdzysCjjksForm pf = (GdzysCjjksForm) vo.getData();
        List dataList = new ArrayList();
        
        
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping对象
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //区县代码
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	    
	    sqlBuf.append("select t.*,(select jsjdm from sbdb.SB_JL_GDZYS_SBB where sbbxlh=t.sbbxlh) jsjdm from sbdb.SB_JL_GDZYS_SBBJKSGLB t where t.SPLX='1' and  t.SBBXLH=?");
        System.out.println("#########="+sqlBuf.toString());
        System.out.println("#########pf.getCxSbbxlh="+pf.getCxSbbxlh());
        try
        {   
            conn = SfDBResource.getConnection();
            
            //是否需要并生成减免申报
            dealWithJMSB(pf,ud,conn);
            
            ps = conn.prepareStatement(sqlBuf.toString());   
		    int i=1;
		    ps.setString(i++, pf.getCxSbbxlh());
            rst = ps.executeQuery();
           //有缴款书
            if(rst.next()) {
            	System.out.println("#################1");
            	HashMap tepmmap = new HashMap();
            	//申报表序列号
            	tepmmap.put("sbbxlh", rst.getString("sbbxlh"));
            	//纳税人名称
            	tepmmap.put("jkpzh", rst.getString("jkpzh"));
            	
            	//计算机代码
            	tepmmap.put("jsjdm", rst.getString("jsjdm"));
            	dataList.add(tepmmap);
            	pf.setDataList(dataList);
            //无缴款书	
            }else{
            	System.out.println("#################2");
            	sqlBuf=new StringBuffer();
            	//sqlBuf.append("select *,(select * from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02') and SBBXLH=?) from sbdb.SB_JL_GDZYS_SBB where  SBBXLH=?");
            	//sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where  SBBXLH=?");
            	
            	//申报数据无减免缴款书，有减免无需出减免证明缴款书
            	if(pf.getFlag().equals("1")||pf.getFlag().equals("3")){
            		//sqlBuf.append("select t.*,(case when t.SFSJSP = '0' then (t.qrrq+30) else (t.sjqrrq+30) end) xjrq from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            		sqlBuf.append("select t.*,(select djzclxmc from  dmdb.dj_dm_djzclx where djzclxdm=t.qydjzclx and rownum<=1) djzclxmc,(select gjbzhymc from  dmdb.gy_dm_gjbzhy where gjbzhydm=t.nsrsshy and rownum<=1) gjbzhymc,(t.qrrq+30) xjrq from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            	//申报数据有减免需出减免证明缴款书
            	}else if(pf.getFlag().equals("2")){
            		sqlBuf.append("select t.*,(select djzclxmc from  dmdb.dj_dm_djzclx where djzclxdm=t.qydjzclx and rownum<=1) djzclxmc,(select gjbzhymc from  dmdb.gy_dm_gjbzhy where gjbzhydm=t.nsrsshy and rownum<=1) gjbzhymc,(select (b.KJRQ+30) from (select (a.KJRQ) from sbdb.SB_JL_GDZYS_JMSZM a where a.DYBZ='1' and a.ZFBZ='0' and a.KJRQ is not null and a.SBBXLH=? order by a.cjrq desc ) b where rownum<=1 ) xjrq  from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            	}
                System.out.println("############sqlBuf.toString()="+sqlBuf.toString());
            	
            	ps = conn.prepareStatement(sqlBuf.toString());   
    		    i=1;
    		    if(pf.getFlag().equals("1")||pf.getFlag().equals("3")){
     	    	   ps.setString(i++, pf.getCxSbbxlh());
     		    }else if(pf.getFlag().equals("2")){
     		       ps.setString(i++, pf.getCxSbbxlh());
     	    	   ps.setString(i++, pf.getCxSbbxlh());
     		    }	
    		    
                rst = ps.executeQuery();
                HashMap map = new HashMap();
                if(rst.next()) {
                	//申报表序列号
                	map.put("sbbxlh", rst.getString("sbbxlh"));
                	//纳税人名称
                	map.put("nsrmc", rst.getString("nsrmc"));
                	//计算机代码
                	map.put("jsjdm", rst.getString("jsjdm"));
                	//批准占地文号
                	map.put("pzjdwh", rst.getString("pzjdwh")==null?"":rst.getString("pzjdwh"));
                	//计税面积
                	map.put("jsmj", rst.getBigDecimal("jsmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jsmj").doubleValue()));
                	//应税面积
                	map.put("ysmj", rst.getBigDecimal("ysmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ysmj").doubleValue()));
                	//计征税额
                	map.put("jzse", rst.getBigDecimal("jzse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jzse").doubleValue()));
                	//应纳税额
                	map.put("ynse", rst.getBigDecimal("ynse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ynse").doubleValue()));
                	//区县确认日期
                	map.put("qrrq", rst.getTimestamp("qrrq"));
                	//市局确认日期
                	map.put("sjqrrq", rst.getTimestamp("sjqrrq"));
                	//map.put("qrsj", rst.getString("qrsj")==null?"":(rst.getString("qrsj").substring(0, 10)));
                	//是否市局审批
                	map.put("sfsjsp", rst.getString("sfsjsp"));
                	//银行代码
                	map.put("khyhdm", rst.getString("khyhdm"));
                    //银行名称
                	map.put("khyhmc", rst.getString("khyhmc"));
                    //帐户
                	map.put("yhzh", rst.getString("yhzh"));
                	
                	//限缴日期
                	map.put("xjrq", rst.getTimestamp("xjrq"));
                	
                	
                	
                	//纳税人类型 0:企业纳税人 1:个人纳税人
                	map.put("nsrlx", rst.getString("nsrlx"));
                	//企业登记注册类型
                	map.put("qydjzclx", rst.getString("qydjzclx"));
                	//企业登记注册类型名称
                	map.put("djzclxmc", rst.getString("djzclxmc"));
                	
                	//纳税人所属行业
                	map.put("nsrsshy", rst.getString("nsrsshy"));
                	//纳税人所属行业名称
                	map.put("gjbzhymc", rst.getString("gjbzhymc"));
                	
                	//区县代码
                	map.put("qxdm", rst.getString("qxdm"));
                	//联系电话
                	map.put("lxdh", rst.getString("lxdh"));
                	
                }
               //企业纳税人	
              if(((String)map.get("nsrlx")).equals("0")){
	            	//得到区县代码
	                qxdm = InterfaceDj.getQxdm(ud);
	                //通过登记接口取得纳税人相关信息
	                //从登记接口中获得信息
	                SWDJJBSJ jbsj = new SWDJJBSJ();
	                //补充主表信息
	                //HashMap mapDJ = new HashMap();
	               // SWDJJBSJ jbsj = new SWDJJBSJ();
	                try
	                {
	                    //mapDJ = (HashMap) InterfaceDj.getDjInfo(map, ud);
	                   // mapDJ = InterfaceDj.getDjInfo((String)map.get("jsjdm"),ud);
	                    jbsj = InterfaceDj.getJBSJ(String.valueOf(map.get("jsjdm")), ud);
	                }
	                catch (Exception ex1)
	                {
	                    ex1.printStackTrace();
	                    throw ExceptionUtil.getBaseException(ex1);
	                }
	                ZRRJBSJ dj1 = new ZRRJBSJ();
	                //SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
	                if (jbsj == null)
	                {
	                    throw new ApplicationException("获取登记信息出错！");
	                }
	
	                //补充主表信息
	                //计算机代码
	                orObj.setJsjdm((String)map.get("jsjdm"));
	                //备注
	                orObj.setBz((String)map.get("pzjdwh"));
	                //申报日期
	                //orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
	                //登记注册类型
	                System.out.println("##########jbsj.getDjzclxdm()="+jbsj.getDjzclxdm());
	                System.out.println("##########jbsj.getGjbzhydm()="+jbsj.getGjbzhydm());
	                System.out.println("##########qxdm="+qxdm);
	                
	                orObj.setDjzclxdm(jbsj.getDjzclxdm());
	                orObj.setDjzclxmc(jbsj.getDjzclxmc());
	                //国家标准行业代码
	                orObj.setGjbzhydm(jbsj.getGjbzhydm());//
	                orObj.setGjbzhymc(jbsj.getGjbzhymc());
	                //税务机关组织机构
	                //orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
	                //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
	                orObj.setSwjgzzjgdm(ud.getSsdwdm());
	                orObj.setSwjgzzjgmc(ud.getSsdwmc());
	                
	                //征收机关
	               // orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
	                //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
	                 orObj.setZsswjgzzjgdm(ud.getSsdwdm());
	                 orObj.setZsswjgzzjgmc(ud.getSsdwmc());
	                
	                //隶属关系
	                orObj.setLsgxdm(jbsj.getLsgxdm()); 
	                orObj.setLsgxmc(jbsj.getLsgxmc());
	                //经营地址联系电话
	                orObj.setJydzlxdm(jbsj.getJydzlxdm());
	                //录入人
	                orObj.setLrr(ud.yhid);
	               // if (ud != null)
	               // {
	                //    orObj.setLrr(ud.getYhid());
	               // }
	                
	                System.out.println("1################限缴日期="+(Timestamp)map.get("xjrq"));
	                
	                //限缴日期
	                orObj.setXjrq((Timestamp)map.get("xjrq"));
	                /*
	                if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
	                	//orObj.setXjrq(GdzysUtil.getDateAfter((Date)map.get("sjqrrq"),30));
	                	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("sjqrrq")).
	                	          toString(), 30)));
	                }else{
	                	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("qrrq")).
	                	          toString(), 30)));
	                }
	                */
	                
	                //申报日期
	                //orObj.setSbrq(nowTime);
	               //是市局审批 申报日期
	                if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
	                	orObj.setSbrq((Timestamp)map.get("sjqrrq"));
	                }else{
	                	orObj.setSbrq((Timestamp)map.get("qrrq"));
	                }
	                
	                orObj.setCjrq(nowTime); //创建日期
	                orObj.setLrrq(nowTime); //录入日期
	                orObj.setQxdm(qxdm); //区县代码
	
	                /*
	                //得到银行信息//Modified by lufeng 2003-12-09
	                ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
	                for (int j = 0; j < dmList.size(); j++)
	                {
	                    YHZH yhzh = (YHZH) dmList.get(j);
	                    if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
	                    {
	                        orObj.setYhdm(yhzh.getYhdm()); //银行代码
	                        orObj.setYhmc(yhzh.getKhyhmc()); //银行名称
	                        orObj.setZh(yhzh.getZh()); //帐户
	                        break;
	                    }
	                }
	                */
	                //银行代码
	                orObj.setYhdm(map.get("khyhdm")==null?"":(String)map.get("khyhdm")); 
	                //银行名称
	                orObj.setYhmc(map.get("khyhmc")==null?"":(String)map.get("khyhmc")); 
	                //帐户
	                orObj.setZh(map.get("yhzh")==null?"":(String)map.get("yhzh")); 
	
	                //申报方式代码
	                orObj.setFsdm(CodeConstant.FSDM_SMSB);
	                //数据来源
	                orObj.setSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //38
	                //税款类型
	                orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
	                orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
	                
            	//个人纳税人
	            //自然人	    
                }else{
                	//补充主表信息
                    //计算机代码
                    orObj.setJsjdm((String)map.get("jsjdm"));
                    //备注
                    orObj.setBz((String)map.get("pzjdwh"));
                    //申报日期
                    //orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
                    //登记注册类型
                    orObj.setDjzclxdm(map.get("qydjzclx")==null?"":(String)map.get("qydjzclx"));
                    orObj.setDjzclxmc(map.get("djzclxmc")==null?"":(String)map.get("djzclxmc"));
                    
                    //国家标准行业代码
                    orObj.setGjbzhydm(map.get("nsrsshy")==null?"":(String)map.get("nsrsshy"));
                    orObj.setGjbzhymc(map.get("gjbzhymc")==null?"":(String)map.get("gjbzhymc"));
                    
                    //税务机关组织机构
                    //orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
                    //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
                    orObj.setSwjgzzjgdm(ud.getSsdwdm());
                    orObj.setSwjgzzjgmc(ud.getSsdwmc());
                    
                    //征收机关
                   // orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
                    //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
                     orObj.setZsswjgzzjgdm(ud.getSsdwdm());
                     orObj.setZsswjgzzjgmc(ud.getSsdwmc());
                    
                    //隶属关系
                    //orObj.setLsgxdm(""); 
                    //orObj.setLsgxmc("");
                    //经营地址联系电话
                    orObj.setJydzlxdm(map.get("lxdh")==null?"":(String)map.get("lxdh"));
                    //录入人
                    orObj.setLrr(ud.yhid);
                   // if (ud != null)
                   // {
                    //    orObj.setLrr(ud.getYhid());
                   // }
                    
                    System.out.println("2################限缴日期="+(Timestamp)map.get("xjrq"));
                    
                    //限缴日期
                    orObj.setXjrq((Timestamp)map.get("xjrq"));
                    /*
                    if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
                    	//orObj.setXjrq(GdzysUtil.getDateAfter((Date)map.get("sjqrrq"),30));
                    	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("sjqrrq")).
                    	          toString(), 30)));
                    }else{
                    	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("qrrq")).
                    	          toString(), 30)));
                    }
                    */
                    
                    //申报日期
                    //orObj.setSbrq(nowTime);
                   //是市局审批 申报日期
                    if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
                    	orObj.setSbrq((Timestamp)map.get("sjqrrq"));
                    }else{
                    	orObj.setSbrq((Timestamp)map.get("qrrq"));
                    }
                    
                    orObj.setCjrq(nowTime); //创建日期
                    orObj.setLrrq(nowTime); //录入日期
                    orObj.setQxdm(map.get("qxdm")==null?"":(String)map.get("qxdm")); //区县代码

                    /*
                    //得到银行信息//Modified by lufeng 2003-12-09
                    ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
                    for (int j = 0; j < dmList.size(); j++)
                    {
                        YHZH yhzh = (YHZH) dmList.get(j);
                        if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                        {
                            orObj.setYhdm(yhzh.getYhdm()); //银行代码
                            orObj.setYhmc(yhzh.getKhyhmc()); //银行名称
                            orObj.setZh(yhzh.getZh()); //帐户
                            break;
                        }
                    }
                    */
                    //银行代码
                    orObj.setYhdm(map.get("khyhdm")==null?"":(String)map.get("khyhdm")); 
                    //银行名称
                    orObj.setYhmc(map.get("khyhmc")==null?"":(String)map.get("khyhmc")); 
                    //帐户
                    orObj.setZh(map.get("yhzh")==null?"":(String)map.get("yhzh")); 

                    //申报方式代码
                    orObj.setFsdm(CodeConstant.FSDM_SMSB);
                    //数据来源
                    orObj.setSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //38
                    //税款类型
                    orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
                    orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
                }
                
                
                
                
                //对数据进行分票保存
                JksUtil ju = new JksUtil();
                try
                {   //当前日期
                String xtdqrq=GdzysDateUtil.getDate(GdzysDateUtil.getTimestampFromDB());
                
                List jksList=new ArrayList();
                HashMap jksmap = new HashMap();
                jksmap.put("szsmdm", GdzysCodeConstant.SMSB_GDZYS_SZSMDM);
                jksmap.put("szsmmc", GdzysCodeConstant.SMSB_GDZYS_SZSMMC);
                jksmap.put("kssl", map.get("ysmj"));
                jksmap.put("jsje", "0");
                jksmap.put("sjse", map.get("ynse"));
                jksmap.put("skssksrq", xtdqrq.substring(0,4)+"0101");
                jksmap.put("skssjsrq", xtdqrq.substring(0,4)+"1231");
                jksmap.put("szdm", GdzysCodeConstant.SMSB_GDZYS_SZDM);
                jksmap.put("szmc", GdzysCodeConstant.SMSB_GDZYS_SZMC);
                jksList.add(jksmap);
                	/*
                	 for (int i = 0; i < form.getDataList().size(); i++)
                	 {
                	      HashMap map = (HashMap) form.getDataList().get(i);
                	 System.out.println("####szsmdm="+map.get("szsmdm"));     
                	 System.out.println("####szsmmc="+map.get("szsmmc"));
                	 System.out.println("####kssl="+map.get("kssl"));
                	 System.out.println("####jsje="+map.get("jsje"));
                	 System.out.println("####sjse="+map.get("sjse"));
                	 System.out.println("####skssksrq="+map.get("skssksrq"));
                	 System.out.println("####skssjsrq="+map.get("skssjsrq"));
                	 System.out.println("####szdm="+map.get("szdm"));
                	 System.out.println("####szmc="+map.get("szmc"));
                	 }
                	*/
                	
                  List tempList = (ArrayList) ju.getJkDataGDZYS(orObj,
                		  jksList,CodeConstant.PRINT_YPYS);
                  if(tempList.size()>0){
                	 DeclareInfor declareInfor=(DeclareInfor)tempList.get(0);
                	 System.out.println("K#####declareInfor.getSbjkzb().getJkpzh()="+declareInfor.getSbjkzb().getJkpzh());
                	 System.out.println("K#####pf.getCxSbbxlh()="+pf.getCxSbbxlh());
   	              
	                 //生成耕地占用税申报表缴款书关联表数据
	                 sqlBuf=new StringBuffer();
	             	 sqlBuf.append("insert into sbdb.SB_JL_GDZYS_SBBJKSGLB (SBBXLH,JKPZH,SPLX) values (?,?,?) ");
	             	 ps = conn.prepareStatement(sqlBuf.toString());   
	      		     i=1;
	      		     ps.setString(i++, pf.getCxSbbxlh());
	      		     ps.setString(i++, declareInfor.getSbjkzb().getJkpzh());
	      		     ps.setString(i++, "1");
	                 int count=ps.executeUpdate();
	                 System.out.println("K##########count="+count);
	                 
	                 HashMap tepmmap = new HashMap();
	             	//申报表序列号
	                 tepmmap.put("sbbxlh", pf.getCxSbbxlh());
	             	//纳税人名称
	                 tepmmap.put("jkpzh", declareInfor.getSbjkzb().getJkpzh());
	             	
	             	//纳税人名称
	                 tepmmap.put("jsjdm", (String)map.get("jsjdm"));
	             	  dataList.add(tepmmap);
	             	  pf.setDataList(dataList);
	                 
	                 
                  }
                  
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("保存数据失败！");
                }            	
            	
            }
            
            
            
		    rst.close();
		    //关闭preparestatement
		    ps.close();

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
     * @Description: TODO 生成减免申报
     * @param gcf
     * @throws BaseException 
     */
    private void dealWithJMSB(GdzysCjjksForm gcf ,UserData ud ,Connection con) throws BaseException
    {
    	
    	try {
    		/*-------查询是否需要生成收入退还书-----------*/
    		String sbbxlh = gcf.getCxSbbxlh();
			String queryGDJMSB="select * from sbdb.SB_JL_GDZYS_SBB where sbbxlh = ?";
			PreparedStatement queryGDJMSBPs = con.prepareStatement(queryGDJMSB);
			queryGDJMSBPs.setString(1, sbbxlh);
			
			ResultSet queryGDJMSBRsRs = queryGDJMSBPs.executeQuery();

			if(!queryGDJMSBRsRs.next())
			{
				return ;
			}
			
			
			String qxdm = queryGDJMSBRsRs.getString("QXDM");
			String sysend=queryGDJMSBRsRs.getString("SYSEND");
			String jsjdm = queryGDJMSBRsRs.getString("JSJDM");			//计算机代码
			
			//减免税额
			String qxsyse = "select syse from dmdb.GD_DM_SYSE syse  where syse.qxdm=? AND syse.ND=? AND syse.syselxdm = (case when to_number(?)>2007 then '11' else '01' end)";
			PreparedStatement querySysePs = con.prepareStatement(qxsyse);
			querySysePs.setString(1, qxdm);
			querySysePs.setString(2, sysend);
			querySysePs.setString(3, sysend);
			ResultSet querySyseRs = querySysePs.executeQuery();
			if(!querySyseRs.next())
			{
				return ;
			}
			
			double syse = querySyseRs.getDouble("SYSE");		//业务上要求这个值不能从明细取
		
			String lrr = ud.yhid;			//录入人
			String jmxmdm = GdzysCodeConstant.SMSB_GDZYS_JMXMDM;			//减免项目代码
			String szsmdm =GdzysCodeConstant.SMSB_GDZYS_SZSMDM;				//税总税目代码	
			
			String xtdqrq=GdzysDateUtil.getDate(GdzysDateUtil.getTimestampFromDB());
			String skssksrq = xtdqrq.substring(0,4)+"0101";				//税款所属开始日期
			String skssjsrq = xtdqrq.substring(0,4)+"1231";				//税款所属结束日期
			
			//明细信息
			String querySql = "select * from sbdb.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
			PreparedStatement queryPs = con.prepareStatement(querySql);
			queryPs.setString(1, sbbxlh);
			ResultSet queryRs = queryPs.executeQuery();
			
			while(queryRs.next())
			{
				String zdyt = queryRs.getString("ZDYTDM");
				int checksyse = queryRs.getInt("SYSE");
				System.out.println("zdyt:"+zdyt+"checksyse:"+checksyse);
				if("00".equals(zdyt) && checksyse ==2 )
				{
					
					double jsmj = queryRs.getDouble("JSMJ");
					Timestamp cjrq = queryRs.getTimestamp("CJRQ");
					
//			
					BigDecimal jsje = queryRs.getBigDecimal("JZSE")==null?new BigDecimal(0):queryGDJMSBRsRs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP);  //计税金额
					
					BigDecimal jmse = new BigDecimal((syse-2.0)*jsmj);
				
					//减免性质代码
					String jmxzdm = GdzysCodeConstant.SMSB_GDZYS_JMXZDM;
					String sjly = GdzysCodeConstant.SMSB_SJLY_GDZYS;
					
					ServiceProxy sp = new ServiceProxy();
					sp.insertSBJM(jsjdm, szsmdm, jsje, jmse, lrr, jmxmdm, cjrq, skssjsrq, skssksrq, jmxzdm,sjly);
					System.out.println("已生成收入退还书");
					
					
					
				}
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
    }
    
}

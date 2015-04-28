/*
 * <p>Title: 北京地税核心征管系统－－网上申报--查询电子缴款专用缴款书</p>
 * <p>Copyright: (C) 2004-2005 北京市地方税务局，北京四一安信科技有限公司，版权所有. </p>
 * <p>Company: 北京四一安信科技有限公司</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.dzwsz.web.DzwszForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.StringUtil;

/**
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 查询电子缴款专用缴款书Action。</p>
 * @author 开发部 - 刘铁刚
 * @version 1.0
 */
public class DzwszProcessor implements Processor {

    public DzwszProcessor() {
    }

    //设置格式化数字
    private final DecimalFormat deFormat = new DecimalFormat("#0.00");
    class tmpQuery {
        //备注
        String bz;
        //国库组织结构代码
        String gkzzjgdm;
        //申报日期
        Timestamp sbrq;
        //实缴数额
        BigDecimal sjse;
        //税种代码
        String szdm;
        //税目代码
        String szsmdm;
        //银行帐号
        String yhzh;
        //银行名称
        String yhmc;
        //预算科目代码
        String yskmdm;
        //税务机关组织机构代码
        String swjgzzjgdm;
        //税款所属开始日期
        String skssksrq;
        //税款所属结束日期
        String skssjsrq;
        //数据来源 2013 kanght
        String sjly;
    };
    /**
     * 查询电子缴库专用缴款书
     * @param vp VOPackage
     * @return Object
     * @throws BaseException BaseException
     */
    public Object process(VOPackage vp) throws BaseException {
        switch (vp.getAction()) {
        case CodeConstant.SMSB_QUERYACTION:

            //获取税收电子转帐专用完税证
            return this.doQuery(vp);
        case CodeConstant.SMSB_PRINTACTION:
            //打印税收电子转帐专用完税证
            return this.doPrint(vp);
        case CodeConstant.SMSB_QUERYACTION1:
        	return this.new_Query(vp);
        case CodeConstant.SMSB_SFDYWSZ:
        	return this.getPrintInfo(vp);
        default:
            throw new ApplicationException(
                "ActionType有错误，processor中找不到相应的方法.");
        }
    }

    /**
     * 打印税收电子转帐专用完税证
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的bo对象
     * @throws BaseException
     */
    private Object doPrint(VOPackage vo) throws BaseException {
        //数据连接对象
        Connection conn = null;
        //获取查询结果对象
        DzwszBO bo = (DzwszBO) vo.getData();
        //删除SBDB.SB_JL_DYHKWSZRZ表中打印失败的信息dopring()方法是取号打印
        DeletePrintInfo(bo);
        
        //获取当前操作人信息
        UserData userdata = (UserData) vo.getUserData();
        //查询的税种信息
        List list = bo.getSzitem();
       
        //处理过的打印税种信息
        List rsList = new ArrayList();
        try {
            //获取数据库连接
            conn = SfDBResource.getConnection();
            if (list != null && list.size() > 0) {
                //显示的行数
                int rowNumb = 8;
                //总记录数
                int totalCount = list.size();
                //计算总页数，如果余数大于0则页数为整除数＋1，否则为整除数
                //余数
                int residual = totalCount % rowNumb;
                //总页数
                int totalPage = totalCount / rowNumb;
                for (int tl = 0; tl < totalPage; tl++) {
                    List smList = new ArrayList();
                    BigDecimal hjje = new BigDecimal("0.00");
                    //取出要显示的记录
                    for (int i = 0; i < rowNumb; i++) {
                        Map rstmap = (Map) list.get(i + 8 * tl);
                        //计算分页打印的单页合计金额
                        hjje = hjje.add(
                            new BigDecimal((String) rstmap.get("sjje")));
                        smList.add(rstmap);
                    }
                    //分页的单页打印信息
                    DzwszBO printbo = setPrintBo(bo, hjje, userdata);
                    SavePrintInfo(printbo, conn, userdata);
                    //合计金额
                    printbo.setHjjexx(deFormat.format(hjje));
                    //把合计金额转换为大写
                    printbo.setHjjedx(Currency.convert(hjje));
                    //分页的单页打印信息--税目信息
                    printbo.setSzitem(smList);
                    rsList.add(printbo);
                }
                if (residual > 0) {
                    //是最后一页
                    List smList = new ArrayList();
                    BigDecimal hjje = new BigDecimal("0.00");
                    //取出要显示的记录
                    for (int i = 0; i < residual; i++) {
                        Map rstmap = (Map) list.get(i + 8 * totalPage);
                        //计算分页打印的单页合计金额
                        hjje = hjje.add(
                            new BigDecimal((String) rstmap.get("sjje")));
                        smList.add(rstmap);
                    }
                    //分页的单页打印信息
                    DzwszBO printbo = setPrintBo(bo, hjje, userdata);
                    SavePrintInfo(printbo, conn, userdata);
                    //合计金额
                    printbo.setHjjexx(deFormat.format(hjje));
                    //把合计金额转换为大写
                    printbo.setHjjedx(Currency.convert(hjje));
                    //分页的单页打印信息--税目信息
                    printbo.setSzitem(smList);
                    rsList.add(printbo);
                }
            } else {
                throw new ApplicationException("打印税收电子转帐专用完税证时出错！");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return rsList;
    }
    
    /**
     * 20130527待修改
     * 查询税收电子转帐专用完税证
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的bo对象
     * @throws BaseException
     */
    private List new_Query(VOPackage vo) throws BaseException {
    	
    	DzwszForm yForm = (DzwszForm) vo.getData();
    	//完税证结果集列表
    	 ArrayList results = new ArrayList();
    	//依付款凭证开具完税证
    	if(!(yForm.getFkpzjsjdm().equals("") || yForm.getFkpzjsjdm() == "")){
    		System.out.println("依付款凭证开具完税证");
    		 DzwszBO bo = (DzwszBO)fkpz_Query(vo);
    		 if(!bo.equals("") && bo != null){
    			 results.add(bo);
    		 }
    		
    	//依存折开具完税证
    	}else{
    		System.out.println("依存折开具完税证");
    		results = (ArrayList) cz_Query(vo);
    		
    	}
    	
    	 return results;	
    	
    }
    /**
     * 20130527待修改
     * kanght
     * 查询税收电子转帐专用完税证---依存折开具
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的bo对象
     * @throws BaseException
     */
    private List cz_Query(VOPackage vo) throws BaseException {
    	//查询结果集
    	List list = new ArrayList();
    	
        //数据连接对象
        Connection conn = null;
        //数据执行对象
        PreparedStatement ps = null;
        //数据返回对象
        ResultSet rs = null;
        //form对象
        DzwszForm form = (DzwszForm) vo.getData();
        //查询条件bo对象
        DzwszBO bo = form.getBo();
       
        try {
            //获取数据库连接
            conn = SfDBResource.getConnection();
            //操作人信息
            UserData ud = (UserData) vo.getUserData();
            //区县代码--作为因此查询条件
            String qxdm = InterfaceDj.getQxdm(ud);
    
            //查询的sql语句，其中隐藏查询条件为SUBSTR(ZB.ZWBS, 1, 1) <> 0（已入库）
            String sql =
           "SELECT DISTINCT ZB.SPHM FROM SBDB.SB_JL_SBJKZB ZB, DJDB.DJ_JL_JBSJ T " +
           "WHERE ZB.JSJDM = T.JSJDM " +
           "AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 " +
           //20131011 kanght 设查询数据来源为123 和zwbs为092
           //"AND SUBSTR(ZB.ZWBS, 18, 3) = '092' " +
           "AND ZB.SJLY= '23' " +
           
           "AND SUBSTR(T.SWJGZZJGDM, 1, 2) = ? " +
           "AND ZB.JSJDM = ? " +
           "AND LENGTH(ZB.SPHM) = 18 " +
           "AND ZB.ZYRQ >= TO_DATE(?, 'YYYYMMDD') " +
           "AND ZB.ZYRQ <= TO_DATE(?, 'YYYYMMDD')";
            ps = conn.prepareStatement(sql);
            //传入查询参数，区县代码
            ps.setString(1, qxdm);
            //传入查询参数，计算机代码
            ps.setString(2, bo.getJsjdm());
            //查询起始日期
            ps.setString(3, form.getCzcxqrq());
            //查询终止日期
            ps.setString(4, form.getCzcxzrq());
            Debug.out("com in DzwszProcessor 存折开具  sql == " + sql);
            //执行数据库查询
            rs = ps.executeQuery();
//            if(rs.equals(null)){
//            	throw new ApplicationException("为查询到符合条件的数据！");
//            }
            if(!rs.next()){
            	throw new ApplicationException("未查询到符合条件的数据！");
            }else{
            	do{
            	    bo.setSphm(rs.getString("SPHM"));
                    //重新设置参数
                    DzwszBO bo_cx = new DzwszBO();
                    bo_cx.setJsjdm(bo.getJsjdm());
                    bo_cx.setSphm(rs.getString("SPHM"));
                    //设置bo参数
                    form.setBo(bo_cx);
                    //获取完税证信息
                    DzwszBO bo2 = (DzwszBO) fkpz_Query(vo);
                    //完税证信息放置到list中
                    list.add(bo2);
            	}while(rs.next());
            }
            
            
        	/*while (rs.next()) {
            	//税票号码
              bo.setSphm(rs.getString("SPHM"));
              //重新设置参数
              DzwszBO bo_cx = new DzwszBO();
              bo_cx.setJsjdm(bo.getJsjdm());
              bo_cx.setSphm(rs.getString("SPHM"));
              //设置bo参数
              form.setBo(bo_cx);
              //获取完税证信息
              DzwszBO bo2 = (DzwszBO) fkpz_Query(vo);
              //完税证信息放置到list中
              list.add(bo2);
            }*/
            rs.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
        	 SfDBResource.freeConnection(conn);
        }
        return list;
    }
    
    /**
     * 20130626
     * kanght
     * 查询税收电子转帐专用完税证----依付款凭证开具
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的bo对象
     * @throws BaseException
     */
    private Object fkpz_Query(VOPackage vo) throws BaseException {
        //一票多税信息
        List dataList = new ArrayList();
        //数据连接对象
        Connection conn = null;
        //数据执行对象
        PreparedStatement ps = null;
        //数据返回对象
        ResultSet rs = null;
        //合计金额
        BigDecimal hjje = new BigDecimal("0.00");
        //分税种的实缴金额
        BigDecimal tmpdd;
        //form对象
        DzwszForm form = (DzwszForm) vo.getData();
        //查询条件bo对象
        DzwszBO bo = form.getBo();
       

        //待处理的查询结果
        ArrayList results = new ArrayList();
        //税目名称
        String tempstr;

        try {
            //获取数据库连接
            conn = SfDBResource.getConnection();
            //操作人信息
            UserData ud = (UserData) vo.getUserData();
            //区县代码--作为因此查询条件
            String qxdm = InterfaceDj.getQxdm(ud);
           /* HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
            //获取纳税人名称
            bo.setNsrmc((String) djmap.get("nsrmc"));
            //获取纳税人名称
            bo.setSwdjzh((String) djmap.get("swdjzh"));
            */
            //税票号码
            String sphm = bo.getSphm();
            //获取交易流水号
             bo.setJylsh(getJylsh(bo, conn));
             if (bo.getJylsh() == null || bo.getJylsh().equals("")) {
            	 form.setWszList(new ArrayList());
                 throw new ApplicationException("根据税票号码未找到交易流水号！");
                 
             }
            //查询的sql语句，其中隐藏查询条件为SUBSTR(ZB.ZWBS, 1, 1) <> 0（已入库）
            //LENGTH(ZB.SPHM) = 18 为时时扣款数据
            String sql =
                "SELECT ZB.SZDM , MX.SZSMDM , MX.SJSE , ZB.SWJGZZJGDM , ZB.SBRQ , ZB.SJLY ,ZB.BZ,"
                + "ZB.YHMC , ZB.ZH , MX.YSKMDM , ZB.GKZZJGDM , "
                + "TO_CHAR(ZB.SKSSKSRQ,'YYYYMMDD') SKSSKSRQ, "
                + "TO_CHAR(ZB.SKSSJSRQ,'YYYYMMDD') SKSSJSRQ "
                + "FROM SBDB.SB_JL_SBJKMX MX,SBDB.SB_JL_SBJKZB ZB,DJDB.DJ_JL_JBSJ T "
                + "WHERE MX.JKPZH=ZB.JKPZH And ZB.JSJDM = T.JSJDM AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 "
                //20131011 kanght 设查询数据来源为123 和zwbs为092
//                + "AND SUBSTR(ZB.ZWBS, 18, 3) = '092' " 
                + "AND ZB.SJLY= '23' "
                
                + "AND  SUBSTR(T.SWJGZZJGDM, 1, 2) = ? AND ZB.JSJDM=? "
                + "AND LENGTH(ZB.SPHM) = 18 AND ZB.SPHM=? "
                + "ORDER BY MX.SZSMDM,MX.YSKMDM";
            //传入sql
            ps = conn.prepareStatement(sql);
            //传入查询参数，区县代码
            ps.setString(1, qxdm);
            //传入查询参数，计算机代码
            ps.setString(2, bo.getJsjdm());
            //传入查询参数，税票号码
            ps.setString(3, sphm);
            
            Debug.out("com in DzwszProcessor doQuery  sql == " + sql);
            //执行数据库查询
            rs = ps.executeQuery();
            //临时处理查询结果的类，将查询结果转换为按预算科目进行分类
            tmpQuery tq = null;
            //将查询结果放到数组中，便于处理
            while (rs.next()) {
                tq = new tmpQuery();
                tq.gkzzjgdm = rs.getString("GKZZJGDM");
                tq.sbrq = rs.getTimestamp("SBRQ");
                tq.sjse = rs.getBigDecimal("SJSE");
                tq.swjgzzjgdm = rs.getString("SWJGZZJGDM");
                tq.szdm = rs.getString("SZDM");
                tq.szsmdm = rs.getString("SZSMDM");
                tq.yhmc = rs.getString("YHMC");
                tq.yhzh = rs.getString("ZH");
                tq.yskmdm = rs.getString("YSKMDM");
                tq.skssksrq = rs.getString("SKSSKSRQ");
                tq.skssjsrq = rs.getString("SKSSJSRQ");
                tq.sjly = rs.getString("sjly");
                tq.bz = rs.getString("BZ");
                results.add(tq);
            }
            if (results.size() == 0) {
                throw new ApplicationException("找不到申报数据！");
            }
            //获取jsjdm最后一位
            char ch = bo.getJsjdm().charAt(bo.getJsjdm().length()-1);
            
            //判断是不是自然人
            if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z')){
            	//调用登记接口获取自然人登记信息
            	Map tmp = getZRRInfo(bo.getJsjdm(),conn);
            	//获取纳税人名称
                bo.setNsrmc((String) tmp.get("nsrmc"));
                //获取纳税人名称
                bo.setSwdjzh("");
                
                System.out.println("自然人名称："+tmp.get("nsrmc"));
                
                
                /*如果数据来源是17开头 零散纳税人*/
            }else if(tq.sjly.substring(0, 2).equals("17")){
            	
                //获取纳税人名称
            	String nsrmc = tq.bz;
                bo.setNsrmc(nsrmc.substring(0, nsrmc.indexOf("#$#")).trim());
                
                HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
                //获取税务登记证号
                bo.setSwdjzh((String) djmap.get("swdjzh"));
                
                System.out.println("零散纳税人名称："+bo.getNsrmc());
                
              //非零散纳税人,非自然人
            }else{
            	
            	HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
                //获取纳税人名称
                bo.setNsrmc((String) djmap.get("nsrmc"));
                //获取纳税人名称
                bo.setSwdjzh((String) djmap.get("swdjzh"));
                
                System.out.println("纳税人名称："+djmap.get("nsrmc"));
            }
            
            //申报日期
            bo.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            //收款银行名称
            bo.setYhmc(tq.yhmc);
            //收款银行帐号
            bo.setZh(tq.yhzh);
            // 征收机关名称（税务所的名称），需要根据代码查名称
            bo.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                tq.swjgzzjgdm));
        
            // 国库代码
            bo.setGkzzjgdm(tq.gkzzjgdm);
            //国库名称
            bo.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", tq.gkzzjgdm));
            //待比较的查询结果
            Map preZB = null;
            //当前的查询结果
            Map curZB = null;
            //比较的游标
            int isz;
            //将查询结果转换为按预算科目进行分类
            for (int i = 0; i < results.size(); i++) {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for (isz = 0; isz < dataList.size(); isz++) {
                    preZB = (Map) dataList.get(isz);
                    if (((String) preZB.get("szdm")).equals(tq.szdm)
                        && ((String) preZB.get("yskmdm")).equals(tq.yskmdm)
                        && ((String) preZB.get("skssksrq")).equals(tq.skssksrq)
                        && ((String) preZB.get("skssjsrq")).equals(tq.skssjsrq)) {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size())) {
                    curZB = new HashMap();
                    curZB.put("szdm", tq.szdm);
                    curZB.put("sjje", tq.sjse);
                    curZB.put("yskmdm", tq.yskmdm);
                    curZB.put("skssksrq", tq.skssksrq);
                    curZB.put("skssjsrq", tq.skssjsrq);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM", tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92")) {
                        tempstr += "(滞纳金、罚款)";
                    }
                    curZB.put("szmc", tempstr);

                    dataList.add(curZB);
                    
                    System.out.println("curZB:"+curZB);
                    
                } else {
                    tmpdd = (BigDecimal) preZB.get("sjje");
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje", tmpdd);
                }
                hjje = hjje.add(tq.sjse);
            }
            for (int i = 0; i < dataList.size(); i++) {
                curZB = (HashMap) dataList.get(i);
                tmpdd = (BigDecimal) curZB.get("sjje");
                curZB.put("sjje", deFormat.format(tmpdd));
            }
            //合计金额
            bo.setHjjexx(deFormat.format(hjje));
            //返回按预算科目进行分类的查询结果转换
            bo.setSzitem(dataList);
            //把合计金额转换为大写
            bo.setHjjedx(Currency.convert(hjje));
            //获取打印信息
            //HashMap rstMap=getPrintInfo(bo, conn);
            //是否已经打印标志
            //bo.setSfdybz((String)rstMap.get("jsjdm"));
            //打印日期
            //bo.setDyrq((String)rstMap.get("dyrq"));
            //将计算机代码、税票号码、交易流水号在备注栏打印
            bo.setBz1("计算机代码：" + bo.getJsjdm());
            bo.setBz2("税票号码：" + bo.getSphm());
            bo.setBz3("交易流水号：" + bo.getJylsh());
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return bo;
    }
    /**
     * 查询税收电子转帐专用完税证
     * @param vo 数据集对象（包括Form和UserData对象）
     * @return 当前页面对应的bo对象
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        //一票多税信息
        List dataList = new ArrayList();
        //数据连接对象
        Connection conn = null;
        //数据执行对象
        PreparedStatement ps = null;
        //数据返回对象
        ResultSet rs = null;
        //合计金额
        BigDecimal hjje = new BigDecimal("0.00");
        //分税种的实缴金额
        BigDecimal tmpdd;
        //查询条件bo对象
        DzwszBO bo = (DzwszBO) vo.getData();

        //待处理的查询结果
        ArrayList results = new ArrayList();
        //税目名称
        String tempstr;

        try {
            //获取数据库连接
            conn = SfDBResource.getConnection();
            //操作人信息
            UserData ud = (UserData) vo.getUserData();
            //区县代码--作为因此查询条件
            String qxdm = InterfaceDj.getQxdm(ud);
            HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
            //获取纳税人名称
            bo.setNsrmc((String) djmap.get("nsrmc"));
            //获取纳税人名称
            bo.setSwdjzh((String) djmap.get("swdjzh"));
            //税票号码
            String sphm = bo.getSphm();
            //交易流水号
            String jylsh = bo.getJylsh();
            //只输入交易流水号时，获取税票号码
            if (sphm == null || sphm.equals("")) {
				        if(jylsh == null||jylsh.equals("") || jylsh.length() != 16)
				        {
				        		throw new ApplicationException("交易流水号错误！找不到申报数据。");
				        }            	
                sphm = getSphm(bo, conn);
                bo.setSphm(sphm);
            }
            //只输入税票号码时，获取税票号码
            if (jylsh == null || jylsh.equals("")) {
                bo.setJylsh(getJylsh(bo, conn));
            }
            //若数据库中不存在税票号码信息，抛出ApplicationException("错误的申报编号！找不到申报数据。")
            if (sphm == null || sphm.equals("")) {
                throw new ApplicationException("申报编号错误！找不到申报数据。");
            }
            //若数据库中不存在税票号码信息，抛出ApplicationException("错误的申报编号！找不到申报数据。")
            if (bo.getJylsh() == null || bo.getJylsh().equals("")) {
                throw new ApplicationException("交易流水号错误！找不到申报数据。");
            }
            //查询的sql语句，其中隐藏查询条件为SUBSTR(ZB.ZWBS, 1, 1) <> 0（已入库）
            //LENGTH(ZB.SPHM) = 18 为时时扣款数据
            String sql =
                "SELECT ZB.SZDM , MX.SZSMDM , MX.SJSE , ZB.SWJGZZJGDM , ZB.SBRQ , "
                + "ZB.YHMC , ZB.ZH , MX.YSKMDM , ZB.GKZZJGDM , "
                + "TO_CHAR(ZB.SKSSKSRQ,'YYYYMMDD') SKSSKSRQ, "
                + "TO_CHAR(ZB.SKSSJSRQ,'YYYYMMDD') SKSSJSRQ "
                + "FROM SBDB.SB_JL_SBJKMX MX,SBDB.SB_JL_SBJKZB ZB "
                + "WHERE MX.JKPZH=ZB.JKPZH AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 "
                + "AND ZB.QXDM=? AND ZB.JSJDM=? "
                + "AND LENGTH(ZB.SPHM) = 18 AND ZB.SPHM=? "
                + "ORDER BY MX.SZSMDM,MX.YSKMDM";
            //传入sql
            ps = conn.prepareStatement(sql);
            //传入查询参数，区县代码
            ps.setString(1, qxdm);
            //传入查询参数，计算机代码
            ps.setString(2, bo.getJsjdm());
            //传入查询参数，税票号码
            ps.setString(3, sphm);
            Debug.out("com in DzwszProcessor doQuery  sql == " + sql);
            //执行数据库查询
            rs = ps.executeQuery();
            //临时处理查询结果的类，将查询结果转换为按预算科目进行分类
            tmpQuery tq = null;
            //将查询结果放到数组中，便于处理
            while (rs.next()) {
                tq = new tmpQuery();
                tq.gkzzjgdm = rs.getString("GKZZJGDM");
                tq.sbrq = rs.getTimestamp("SBRQ");
                tq.sjse = rs.getBigDecimal("SJSE");
                tq.swjgzzjgdm = rs.getString("SWJGZZJGDM");
                tq.szdm = rs.getString("SZDM");
                tq.szsmdm = rs.getString("SZSMDM");
                tq.yhmc = rs.getString("YHMC");
                tq.yhzh = rs.getString("ZH");
                tq.yskmdm = rs.getString("YSKMDM");
                tq.skssksrq = rs.getString("SKSSKSRQ");
                tq.skssjsrq = rs.getString("SKSSJSRQ");
                results.add(tq);
            }
            if (results.size() == 0) {
                throw new ApplicationException("错误的申报编号！找不到申报数据。");
            }
            //申报日期
            bo.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            //收款银行名称
            bo.setYhmc(tq.yhmc);
            //收款银行帐号
            bo.setZh(tq.yhzh);
            // 征收机关名称（税务所的名称），需要根据代码查名称
            bo.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                tq.swjgzzjgdm));
            // 国库代码
            bo.setGkzzjgdm(tq.gkzzjgdm);
            //国库名称
            bo.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", tq.gkzzjgdm));
            //待比较的查询结果
            Map preZB = null;
            //当前的查询结果
            Map curZB = null;
            //比较的游标
            int isz;
            //将查询结果转换为按预算科目进行分类
            for (int i = 0; i < results.size(); i++) {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for (isz = 0; isz < dataList.size(); isz++) {
                    preZB = (Map) dataList.get(isz);
                    if (((String) preZB.get("szdm")).equals(tq.szdm)
                        && ((String) preZB.get("yskmdm")).equals(tq.yskmdm)
                        && ((String) preZB.get("skssksrq")).equals(tq.skssksrq)
                        && ((String) preZB.get("skssjsrq")).equals(tq.skssjsrq)) {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size())) {
                    curZB = new HashMap();
                    curZB.put("szdm", tq.szdm);
                    curZB.put("sjje", tq.sjse);
                    curZB.put("yskmdm", tq.yskmdm);
                    curZB.put("skssksrq", tq.skssksrq);
                    curZB.put("skssjsrq", tq.skssjsrq);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM", tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92")) {
                        tempstr += "(滞纳金、罚款)";
                    }
                    curZB.put("szmc", tempstr);

                    dataList.add(curZB);
                } else {
                    tmpdd = (BigDecimal) preZB.get("sjje");
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje", tmpdd);
                }
                hjje = hjje.add(tq.sjse);
            }
            for (int i = 0; i < dataList.size(); i++) {
                curZB = (HashMap) dataList.get(i);
                tmpdd = (BigDecimal) curZB.get("sjje");
                curZB.put("sjje", deFormat.format(tmpdd));
            }
            //合计金额
            bo.setHjjexx(deFormat.format(hjje));
            //返回按预算科目进行分类的查询结果转换
            bo.setSzitem(dataList);
            //把合计金额转换为大写
            bo.setHjjedx(Currency.convert(hjje));
            //获取打印信息
            HashMap rstMap=getPrintInfo(bo, conn);
            //是否已经打印标志
            bo.setSfdybz((String)rstMap.get("jsjdm"));
            //打印日期
            bo.setDyrq((String)rstMap.get("dyrq"));
            //将计算机代码、税票号码、交易流水号在备注栏打印
            bo.setBz1("计算机代码：" + bo.getJsjdm());
            bo.setBz2("税票号码：" + bo.getSphm());
            bo.setBz3("交易流水号：" + bo.getJylsh());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return bo;
    }


    /**
     * 获取税票号码
     * @param bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getSphm(DzwszBO bo, Connection conn) throws BaseException {
        //构造根据计算机代码、委托日期、交易流水号查询税票号码sql
        String strSql =
            "SELECT PZHM FROM SKHDB.SKH_JL_GKRZDZJYMX "
            + "WHERE PZZL='00' AND PJZT = '20' AND WTRQ=? AND JYLSH=?";
        Debug.out("com in DzwszProcessor doQuery getSphm sql == " + strSql);
        //定义PZHM
        String pzhm = "";
        String ymjylsh=bo.getJylsh();
        try {
            //构造PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //查询条件委托日期
            pst.setString(1, ymjylsh.substring(0, 8));
            //查询条件交易流水号
            pst.setString(2, ymjylsh.substring(8));
            //执行查询sql
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取pzhm
                pzhm = rs.getString("PZHM");
            }
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("查询税票号码错误！");
        }
        //返回pzhm
        return pzhm;
    }

    /**
     * 获取交易流水号
     * @param bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getJylsh(DzwszBO bo, Connection conn) throws BaseException {
        //构造根据税票号码查询交易流水号sql
        String strSql =
            "SELECT CONCAT(WTRQ,JYLSH) JYLSH FROM SKHDB.SKH_JL_GKRZDZJYMX "
            + "WHERE PZZL='00' AND PJZT = '20' AND PZHM=?";
        Debug.out("com in DzwszProcessor doQuery getJylsh sql == " + strSql);
        //定义交易流水号
        String pzhm = "";
        try {
            //构造PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //查询税票号码
            pst.setString(1, bo.getSphm());
            //执行查询sql
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取交易流水号
                pzhm = rs.getString("JYLSH");
            }
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("查询交易流水号错误！");
        }
        //返回pzhm
        return pzhm;
    }


    /**
     * 查询纳税人名称信息
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getNsrmc(String jsjdm, Connection conn) throws
        BaseException {
        String strSql =
            "SELECT NSRMC,SWDJZH FROM DJDB.DJ_JL_JBSJ WHERE JSJDM=?";
        String nsrmc = "";
        String swdjzh = "";
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, jsjdm);
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取纳税人名称
                nsrmc = rs.getString("NSRMC");
                swdjzh = rs.getString("SWDJZH");
            }
            rstmap.put("nsrmc", nsrmc);
            rstmap.put("swdjzh", swdjzh);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取纳税人名称错误！");
        }
        return rstmap;
    }

    /**
     * 获取保存打印信息的序号
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getPrintSaveXh(Connection conn) throws BaseException {
        String strSql = "SELECT SBDB.SEQ_SB_DYDZWSZXH.NEXTVAL FROM DUAL";
        String nsrmc = "";
        try {
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取纳税人名称
                nsrmc = rs.getString("NEXTVAL");
            }
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取保存打印信息的序号错误！");
        }
        return nsrmc;
    }

    /**
     * 查询是否已经打印过完税证
     * @param  bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getPrintInfo(DzwszBO bo, Connection conn) throws
        BaseException {
        String strSql =
            "SELECT JSJDM,TO_CHAR(DYRQ,'YYYYMMDD') DYRQ "
            +"FROM SBDB.SB_JL_DYHKWSZRZ WHERE JSJDM=? AND SPHM=?";
        String jsjdm = "";
        String dyrq = "";
        HashMap rstMap=new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, bo.getJsjdm());
            pst.setString(2, bo.getSphm());
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取纳税人名称
                jsjdm = rs.getString("JSJDM");
                dyrq = rs.getString("DYRQ");
            }
            if(dyrq==null||dyrq.equals("")){
            	dyrq=TinyTools.Date2String(new Timestamp(System.currentTimeMillis()),"yyyyMMdd");
            }
            rstMap.put("dyrq",dyrq);
            rstMap.put("jsjdm",jsjdm);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取换开电子转帐专用完税证日志错误！");
        }
        return rstMap;
    }

    
    
    /**
     * 查询是否已经打印过完税证
     * 20131028
     * kanght
     * @param  bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getPrintInfo(VOPackage vo) throws
        BaseException {
    	 //获取数据库连接
        Connection conn = SfDBResource.getConnection();
        DzwszBO bo = (DzwszBO) vo.getData();
        String strSql =
            "SELECT JSJDM,TO_CHAR(DYRQ,'YYYYMMDD') DYRQ "
            +"FROM SBDB.SB_JL_DYHKWSZRZ WHERE JSJDM=? AND SPHM=?";
        String jsjdm = "";
        String dyrq = "";
        HashMap rstMap=new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, bo.getJsjdm());
            pst.setString(2, bo.getSphm());
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取纳税人名称
                jsjdm = rs.getString("JSJDM");
                dyrq = rs.getString("DYRQ");
            }
            if(dyrq==null||dyrq.equals("")){
            	dyrq=TinyTools.Date2String(new Timestamp(System.currentTimeMillis()),"yyyyMMdd");
            }
            rstMap.put("dyrq",dyrq);
            rstMap.put("jsjdm",jsjdm);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取换开电子转帐专用完税证日志错误！");
        }
        return rstMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * 设置打印项的数据
     * @param  bo DzwszBO
     * @param userdata UserData
     * @param hjje BigDecimal
     * @return String
     * @throws BaseException
     */
    private DzwszBO setPrintBo(DzwszBO bo, BigDecimal hjje, UserData userdata) throws
        BaseException {
        //设置打印项的数据
        DzwszBO printbo = new DzwszBO();
        //计算机代码
        printbo.setJsjdm(bo.getJsjdm());
        //税票号码
        printbo.setSphm(bo.getSphm());
        //交易流水号
        printbo.setJylsh(bo.getJylsh());
        //申报日期
        printbo.setSbrq(bo.getSbrq());
        //征收机关
        printbo.setSwjgzzjgmc(bo.getSwjgzzjgmc());
        //纳税人名称
        printbo.setNsrmc(bo.getNsrmc());
        //税务登记证号
        printbo.setSwdjzh(bo.getSwdjzh());
        //收款银行
        printbo.setYhmc(bo.getYhmc());
        //备注
        printbo.setBz1(bo.getBz1());
        printbo.setBz2(bo.getBz2());
        printbo.setBz3(bo.getBz3());
        String ndzb = "";
        String wszh = "";
        //获得完税证号
        try {
            String retResult = ServiceProxy.getNumber(userdata, CodeConstant.SMSB_SWWSZM_PZZLDM,
                StringUtil.getDouble(deFormat.format(hjje), 0), "1", "1");
            ndzb = retResult.substring(0, 4); //年度字别
            wszh = retResult.substring(4); //完税证号
            
            System.out.println("年度字别"+ndzb);
            System.out.println("完税证号"+wszh);
            
            //停止1秒钟，防止下一次调用时报错
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("获取完税证失败！");
        }
        //完税证号
        printbo.setWszh(wszh);
        //年度字别
        printbo.setNdzb(ndzb);
        return printbo;
    }

    /**
     * 查询纳税人名称信息
     * @param  bo DzwszBO
     * @param conn Connection
     * @param userdata UserData
     * @throws BaseException
     */
    private void SavePrintInfo(DzwszBO bo, Connection conn, UserData userdata) throws
        BaseException {
        try {
            //获取PreparedStatement
            String xh = getPrintSaveXh(conn);
            String sql = "INSERT INTO SBDB.SB_JL_DYHKWSZRZ "
                         + "(JSJDM, SPHM, JYLSH,DYRY, WSZH, NDZB, XH) "
                         + "VALUES ('" + bo.getJsjdm() + "', '" + bo.getSphm()
                         + "', '" + bo.getJylsh() + "', '" + userdata.getYhmc()
                         + "', '" + bo.getWszh() + "', '" + bo.getNdzb()
                         + "', " + xh + ")";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);
            //关闭pst
            stmt.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取换开电子转帐专用完税证日志错误！");
        }
    }
    
    /**
     *自然人登记信息 
     * 201307
     * kanght
     */
    private HashMap getZRRInfo(String jsjdm, Connection conn) throws
    BaseException {
    	String strSql =
            "SELECT NSRMC FROM DJDB.DJ_JL_ZRRJBSJ WHERE JSJDM=?";
        String nsrmc = "";
        HashMap rstmap = new HashMap();
        try {
            //获取PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //设置查询条件Sphm
            pst.setString(1, jsjdm);
            //设置查询条件计算机代码
            ResultSet rs = pst.executeQuery();
            //循环取出查询结果
            if (rs.next()) {
                //获取纳税人名称
                nsrmc = rs.getString("NSRMC");
            }
            rstmap.put("nsrmc", nsrmc);
            //关闭rs
            rs.close();
            //关闭pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //抛出应用异常
            throw new ApplicationException("获取自然人名称错误！");
        }
        return rstmap;
}
    //删除上次打印失败信息
    //2013 kanght
    public void DeletePrintInfo(DzwszBO bo){
    	try {
    		System.out.println("删除上次打印失败的数据");
			Connection conn = SfDBResource.getConnection();
			String sql = "delete from SBDB.SB_JL_DYHKWSZRZ where jsjdm = ? and sphm = ? and jylsh = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bo.getJsjdm());
			ps.setString(2, bo.getSphm());
			ps.setString(3, bo.getJylsh());
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
}

package com.ttsoft.bjtax.smsb.gdzys.cx.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.cx.web.GdzysJksCxForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysDateUtil;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysMoneyUtils;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysUtil;
import com.ttsoft.bjtax.smsb.model.client.swdwmodel;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: 北京地税核心征管系统--耕地占用税查询管理</p>
 * <p>Description: 缴款书查询Processor</p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysJksCxProcessor implements Processor{
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
        	//获取分局数据
	      case CodeConstant.SMSB_DQJSLIST:
	        result =dogetdqjslist(vo);
	        break;
	      //获取税务所数据
	      case CodeConstant.SMSB_CXDQJSLIST:
				result = dogetcxdqjslist(vo);
		   break;
		   //查询
	      case CodeConstant.SMSB_QUERYACTION:
	        result = doQuery(vo);
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
    	GdzysJksCxForm form = (GdzysJksCxForm) vo.getData();
       
         //连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        //结果集 
	    //ResultSet rst = null;
       
        try
        {
        	 int count=0;//查询总记录条数
	    	 int currentPage = form.getCurrentPage();
		     int startNum = (currentPage-1)*(form.getPageSize())+1;
		     int endNum = currentPage*(form.getPageSize());
		     System.out.println("################currentPage="+currentPage);
		     System.out.println("################startNum="+startNum);
			 System.out.println("################endNum="+endNum);
		     
        	
            conn = SfDBResource.getConnection();
            StringBuffer sqlBuf = new StringBuffer();
            sqlBuf.append("select count(bb.num) from (select rownum num,aa.* from (");
            sqlBuf.append("select (select sbbxlh from sbdb.SB_JL_GDZYS_SBBJKSGLB where jkpzh=t.jkpzh and rownum<=1) sbbxlh,t.jsjdm,");
            sqlBuf.append("nvl((select nsrmc from djdb.dj_jl_jbsj  where jsjdm=t.jsjdm),(select nsrmc from djdb.Dj_Jl_Zrrjbsj  where jsjdm=t.jsjdm)) nsrmc,");
            sqlBuf.append("t.jkpzh,(select decode(splx,'1','正税税票','2','滞纳金税票') from sbdb.SB_JL_GDZYS_SBBJKSGLB where jkpzh=t.jkpzh and rownum<=1) splx,");
            sqlBuf.append("(select kssl from sbdb.sb_jl_sbjkmx where jsjdm=t.jsjdm and jkpzh=t.jkpzh and sbbh=t.sbbh and rownum<=1) kssl,t.sjje,t.sbrq,(case when substr(t.zwbs,2,1) = '1' then '已入库' else '未入库' end) jkszt,(case when substr(t.zwbs,2,1) = '1' then t.zyrq else null end) zyrq,");
            sqlBuf.append("(select SWJGZZJGMC from dmdb.gy_dm_swjgzzjg where SWJGZZJGDM=t.ZSSWJGZZJGDM) zsjg,t.bz,t.cjrq ");
            sqlBuf.append(" from sbdb.sb_jl_sbjkzb  t");
            sqlBuf.append(" where t.sjly='"+GdzysCodeConstant.SMSB_SJLY_GDZYS+"' and t.FSDM='"+CodeConstant.FSDM_SMSB+"' ");
            
            if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
            	sqlBuf.append(" and t.qxdm = ? ");
		    }
            if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
            	sqlBuf.append(" and t.ZSSWJGZZJGDM = ? ");
		    }
           
            if(form.getJsjdm()!=null&&form.getJsjdm().trim().length()>0){
            	sqlBuf.append(" and t.jsjdm = ? ");
		    }
            
            if(form.getJkszt()!=null&&form.getJkszt().trim().length()>0){
            	if(form.getJkszt().equals("1")){
            		sqlBuf.append(" and substr(t.zwbs,2,1) = ? ");
            	}else{
            		sqlBuf.append(" and substr(t.zwbs,2,1) <> ? ");
            	}
            	
		    }
            
            if(form.getKsrq()!=null&&form.getKsrq().trim().length()>0){
            	sqlBuf.append(" and t.sbrq >=to_date(?,'yyyymmdd') ");
		    }
            
            if(form.getJsrq()!=null&&form.getJsrq().trim().length()>0){
            	sqlBuf.append(" and t.sbrq <to_date(?,'yyyymmdd') ");
		    }
            
            if(form.getPzzdwh()!=null&&form.getPzzdwh().trim().length()>0){
            	sqlBuf.append(" and t.bz like ? ");
		    }
            
            
            sqlBuf.append(") aa ) bb ");
            
            System.out.println("1###########sqlBuf.toString()="+sqlBuf.toString());
            ps = conn.prepareStatement(sqlBuf.toString());   
            
		    int i=1;
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i++, form.getDqjs().trim().substring(0,2));
		    }
            if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
            	ps.setString(i++, form.getCxdqjs().trim());
		    }
            if(form.getJsjdm()!=null&&form.getJsjdm().trim().length()>0){
            	ps.setString(i++, form.getJsjdm().trim());
		    }
            
            if(form.getJkszt()!=null&&form.getJkszt().trim().length()>0){
            	ps.setString(i++, "1");
		    }
            
            if(form.getKsrq()!=null&&form.getKsrq().trim().length()>0){
            	ps.setString(i++, form.getKsrq().trim());
		    }
            
            if(form.getJsrq()!=null&&form.getJsrq().trim().length()>0){
            	String nextDate=GdzysDateUtil.getDate(GdzysUtil.getDateAfter(GdzysDateUtil.parsePlainDate(form.getJsrq().trim()),1));
            	ps.setString(i++, nextDate);
		    }
            
            if(form.getPzzdwh()!=null&&form.getPzzdwh().trim().length()>0){
            	 ps.setString(i++, "%"+form.getPzzdwh()+"%");
		    }
            
            ResultSet rs = ps.executeQuery();
		    if(rs.next())
			{
				count=rs.getInt(1);
			}
		    System.out.println("###########count="+count);
		    form.setTotalRowCount(count);
		    rs.close();
            
		    
		    sqlBuf = new StringBuffer();
            sqlBuf.append("select bb.* from (select rownum num,aa.* from (");
            sqlBuf.append("select (select sbbxlh from sbdb.SB_JL_GDZYS_SBBJKSGLB where jkpzh=t.jkpzh and rownum<=1) sbbxlh,t.jsjdm,");
            sqlBuf.append("nvl((select nsrmc from djdb.dj_jl_jbsj  where jsjdm=t.jsjdm),(select nsrmc from djdb.Dj_Jl_Zrrjbsj  where jsjdm=t.jsjdm)) nsrmc,");
            sqlBuf.append("t.jkpzh,(select decode(splx,'1','正税税票','2','滞纳金税票') from sbdb.SB_JL_GDZYS_SBBJKSGLB where jkpzh=t.jkpzh and rownum<=1) splx,");
            sqlBuf.append("(select kssl from sbdb.sb_jl_sbjkmx where jsjdm=t.jsjdm and jkpzh=t.jkpzh and sbbh=t.sbbh and rownum<=1) kssl,t.sjje,t.sbrq,(case when substr(t.zwbs,2,1) = '1' then '已入库' else '未入库' end) jkszt,(case when substr(t.zwbs,2,1) = '1' then t.zyrq else null end) zyrq,");
            sqlBuf.append("(select SWJGZZJGMC from dmdb.gy_dm_swjgzzjg where SWJGZZJGDM=t.ZSSWJGZZJGDM) zsjg,t.bz,t.cjrq ");
            sqlBuf.append(" from sbdb.sb_jl_sbjkzb  t");
            sqlBuf.append(" where t.sjly='"+GdzysCodeConstant.SMSB_SJLY_GDZYS+"' and t.FSDM='"+CodeConstant.FSDM_SMSB+"' ");
            
            if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
            	sqlBuf.append(" and t.qxdm = ? ");
		    }
            if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
            	sqlBuf.append(" and t.ZSSWJGZZJGDM = ? ");
		    }
           
            if(form.getJsjdm()!=null&&form.getJsjdm().trim().length()>0){
            	sqlBuf.append(" and t.jsjdm = ? ");
		    }
            
            if(form.getJkszt()!=null&&form.getJkszt().trim().length()>0){
            	if(form.getJkszt().equals("1")){
            		sqlBuf.append(" and substr(t.zwbs,2,1) = ? ");
            	}else{
            		sqlBuf.append(" and substr(t.zwbs,2,1) <> ? ");
            	}
            	
		    }
            
            if(form.getKsrq()!=null&&form.getKsrq().trim().length()>0){
            	sqlBuf.append(" and t.sbrq >=to_date(?,'yyyymmdd') ");
		    }
            
            if(form.getJsrq()!=null&&form.getJsrq().trim().length()>0){
            	sqlBuf.append(" and t.sbrq <to_date(?,'yyyymmdd') ");
		    }
            
            if(form.getPzzdwh()!=null&&form.getPzzdwh().trim().length()>0){
            	sqlBuf.append(" and t.bz like ? ");
		    }
            
            sqlBuf.append(") aa order by aa.sbrq asc  ) bb ");
		    
            sqlBuf.append(" where bb.num >= ? and bb.num <= ?");
            System.out.println("2###########sqlBuf.toString()="+sqlBuf.toString());
            
            ps = conn.prepareStatement(sqlBuf.toString());
            i=1;
		    if(form.getDqjs()!=null&&form.getDqjs().trim().length()>0){
		    	ps.setString(i++, form.getDqjs().trim().substring(0,2));
		    }
            if(form.getCxdqjs()!=null&&form.getCxdqjs().trim().length()>0){
            	ps.setString(i++, form.getCxdqjs().trim());
		    }
            if(form.getJsjdm()!=null&&form.getJsjdm().trim().length()>0){
            	ps.setString(i++, form.getJsjdm().trim());
		    }
            
            if(form.getJkszt()!=null&&form.getJkszt().trim().length()>0){
            	ps.setString(i++, "1");
		    }
            
            if(form.getKsrq()!=null&&form.getKsrq().trim().length()>0){
            	ps.setString(i++, form.getKsrq().trim());
		    }
            
            if(form.getJsrq()!=null&&form.getJsrq().trim().length()>0){
            	String nextDate=GdzysDateUtil.getDate(GdzysUtil.getDateAfter(GdzysDateUtil.parsePlainDate(form.getJsrq().trim()),1));
            	ps.setString(i++, nextDate);
		    }
            
            if(form.getPzzdwh()!=null&&form.getPzzdwh().trim().length()>0){
           	 ps.setString(i++, "%"+form.getPzzdwh()+"%");
		    }
            
            ps.setInt(i++, startNum);
	    	ps.setInt(i++, endNum);
	    	ResultSet rst = ps.executeQuery();
	        List dataList = new ArrayList();
	        while(rst.next()){
	        	HashMap map = new HashMap();
	        	//申报表序列号
            	map.put("sbbxlh", rst.getString("sbbxlh")==null?"":rst.getString("sbbxlh"));
            	//计算机代码
            	map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
            	//纳税人名称
            	map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
            	//缴款凭证号
            	map.put("jkpzh", rst.getString("jkpzh")==null?"":rst.getString("jkpzh"));
            	//缴款书类别
            	map.put("splx", rst.getString("splx")==null?"":rst.getString("splx"));
            	//课税数量
            	map.put("kssl", rst.getBigDecimal("kssl")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("kssl").doubleValue()));
            	//实缴金额
            	map.put("sjje", rst.getBigDecimal("sjje")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjje").doubleValue()));
            	//申报日期
            	map.put("sbrq", rst.getDate("sbrq")==null?"":GdzysDateUtil.getHyphenDate(rst.getDate("sbrq")));
            	//状态
            	map.put("jkszt", rst.getString("jkszt")==null?"":rst.getString("jkszt"));
            	//入库日期
            	map.put("zyrq", rst.getDate("zyrq")==null?"":GdzysDateUtil.getHyphenDate(rst.getDate("zyrq")));
            	//征收机关
            	map.put("zsjg", rst.getString("zsjg")==null?"":rst.getString("zsjg"));
            	//备注
            	map.put("bz", rst.getString("bz")==null?"":rst.getString("bz"));
            	dataList.add(map);
	    	}
		    form.setDataList(dataList);

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
	 * 获取税务所信息list
	 */
	private Object dogetcxdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJksCxForm theForm = (GdzysJksCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
                UserData userdata = (UserData) vo.getUserData();
		try {

			swdwmodel model1 = null;

			List list1 = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = theForm.getDqjs();
			// 根据登录用户的信息获取登陆的居所的信息
			if ("30".equals(userdata.yhjb)) {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where (b.ZXBS ='0' or b.ZXBS is null) and "
						+ "  b.swjgzzjgdm='" + userdata.getSsdwdm()+ "' order by b.swjgzzjgdm ";
			} else {
				// 根据分局查税务所
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where (b.ZXBS ='0' or b.ZXBS is null) and b.swjgzzjgdm like '"
						+ gxswjgzzjgdm.substring(0, 2)
						+ "%' "
						+ " and b.swjgzzjgdm!='" + gxswjgzzjgdm + "' order by b.swjgzzjgdm ";
			}
			
			System.out.println("###########sql="+sql);
			
			cstmt = con.prepareStatement(sql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				model1 = new swdwmodel();
				model1.setSwdwid(rs.getString(1));
				model1.setSwdwmc(rs.getString(2));
				list1.add(model1);
			}
			theForm.setCxswdwlist(list1);
			if (rs != null) {
				rs.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}

			return theForm;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}
    
    
    
	/**
	 * 获取分局数据list
	 */
	private Object dogetdqjslist(VOPackage vo) throws BaseException {
		Connection conn = null;
		GdzysJksCxForm theForm = (GdzysJksCxForm) vo.getData();
		Connection con = null;
		ResultSet rs = null;
		String sql = "";
		PreparedStatement cstmt = null;
		UserData userdata = (UserData) vo.getUserData();
		try {
			swdwmodel model = null;
			swdwmodel model1 = null;
			List list = new ArrayList();
			con = SfDBResource.getConnection();
			String gxswjgzzjgdm = userdata.getSsdwdm();
			// 根据登录用户的信息获取登陆的居所的信息
			//gxswjgzzjgdm="0604";
			if ("90".equals(gxswjgzzjgdm.substring(0, 2))) {
				// 查的是所有分局
				sql = "select b.qxdm||'00',b.QXFJMC from dmdb.gd_dm_qxfjdm b where  b.qxdm!='90' order by b.qxdm ";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			} else {
				sql = "select b.swjgzzjgdm,b.swjgzzjgmc from dmdb.gy_dm_swjgzzjg b where (b.ZXBS ='0' or b.ZXBS is null) and b.swjgzzjgdm='"
						+ gxswjgzzjgdm.substring(0, 2) + "00' order by b.swjgzzjgdm ";
				cstmt = con.prepareStatement(sql);
				rs = cstmt.executeQuery();
				//System.out.println(sql + "-------------查登陆税务机关的sql");
				while (rs.next()) {
					model = new swdwmodel();
					model.setSwdwid(rs.getString(1));
					model.setSwdwmc(rs.getString(2));
					list.add(model);
				}
				theForm.setSwdwlist(list);
				return theForm;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (cstmt != null) {
					cstmt.close();
				}
			} catch (Exception exx) {
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (Exception exx) {
			}
		}

	}
}

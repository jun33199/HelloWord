/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税报告表</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信科技有限公司，版权所有. </p>
 * <p>Company: 四一安信科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.processor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.kjqysds.*;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web.KjqysdsbgbBO;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 北京地税综合管理系统 扣缴企业所得税报告表
 * </p>
 * <p>
 * Description:扣缴企业所得税报告表
 * </p>
 *
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbProcessor implements Processor {


	/**
	 * 实现Processor接口
	 *
	 * @param vo
	 *            业务参数
	 * @return Object VOPackage型数据
	 * @throws BaseException
	 *             业务异常 1 当传过来的操作类型不对时抛出 2 当调用的业务方法抛出业务异常时向上传递抛出
	 *             其他异常抛出由EJB的process方法处理。
	 */

	public Object process(VOPackage vo) throws BaseException {
		System.out.println("------------kjqysdsbgbProcessor-----------------");
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
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
		case CodeConstant.SMSB_QUERYACTION1:
			result=doQuery1(vo);
			break;
		// case CodeConstant.SMSB_CHECKACTION:
		// result = doCheck(vo);
		// break;
		default:
			throw new ApplicationException("用户执行了系统不支持的方法或功能.");
		}

		return result;
	}

	/**
	 * doShow初始化对象页面信息要素
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		// 得到Action传递过来KjqysdsbgbBO对象
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		// 得到当前时间的所属月
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// 税款所属开始日期
		bo.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// 税款所属结束日期
		bo.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// 税款申报日期
		bo.setSbrq(SfDateUtil.getDate());
		return bo;
	}
	/**
	 * doQuery1扣缴企业所得税报表告查询
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doQuery1(VOPackage vo) throws BaseException {
		// 得到Action传递过来KjqysdsbgbBO对象
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql= new StringBuffer();
		PreparedStatement psCount = null;
		ResultSet rsCount = null;
		List list = new ArrayList();
		
		if(bo.getDqzt().equals("01")){//已备案未填写报告表
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje, rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b where a.BADJXH=b.BADJXH and b.jsjdm='");
			sql.append(bo.getJsjdm());
			sql.append("' and b.badjxh not in(select badjxh from SBDB.SB_JL_KJQYSDS_KJBGB where jsjdm='");
			sql.append(bo.getJsjdm()).append("') and b.ztbz='1'");
		}
		else if(bo.getDqzt().equals("02")){//已填写报告表未开拒缴款书
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje,c.bgbxh,rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b,SBDB.SB_JL_KJQYSDS_KJBGB c where a.BADJXH=b.BADJXH  and sphm is null and b.jsjdm='");
			sql.append(bo.getJsjdm());
			sql.append("' and b.badjxh=c.badjxh and c.scbs='1' and b.ztbz='1'");
		}
		else if(bo.getDqzt().equals("03")){//已开缴款书
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje,c.bgbxh,rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b,SBDB.SB_JL_KJQYSDS_KJBGB c where a.BADJXH=b.BADJXH and b.ztbz='1' and b.badjxh=c.badjxh and c.sphm is not null and c.scbs='1' and b.jsjdm='");
			sql.append(bo.getJsjdm()).append("'");
		}
		try{
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			//查询总条数
			StringBuffer sqlCount=new StringBuffer();
			sqlCount.append("select count(*) from (");
			sqlCount.append(sql.toString()).append(")");
			psCount=conn.prepareCall(sqlCount.toString());
			rsCount=psCount.executeQuery();
			int zts=0;
			if(rsCount.next()){
			zts = rsCount.getInt(1);
			}
            bo.setZts(String.valueOf(zts));
            sqlCount.delete(0,sqlCount.length());
            rsCount.close();
            psCount.close();
            
          //如果总条数不为0，则计算总页数，查询明细
            if(zts > 0){
			//计算总页数
			int zys = 0;
            //每页条数
            int myts = Integer.parseInt(CodeConstant.PAGE_MYTS);
			if (zts % myts == 0) {
				zys = zts / myts;
			}
			else if (zts % myts > 0) {
				zys = zts / myts + 1;
			}
			bo.setZys(String.valueOf(zys));
			System.out.println("总页数::" + bo.getZys());

            //获取当前页，如果为查询操作，当前页置为第一页
            if(bo.getDqy() != null)
            {
                System.out.println("bo_dqy = " + bo.getDqy());
            }
            System.out.println("bo_dqy ============ " + bo.getDqy());
            int dqy = (bo.getDqy()==null||bo.getDqy().equals("")||bo.getDqy().equals("0") ? 1 : Integer.parseInt(bo.getDqy()));
            bo.setDqy(String.valueOf(dqy));
            System.out.println("dqy = " + dqy + "\nmyts = " + myts);
            StringBuffer sql1=new StringBuffer();
			sql1.append("select * from (");
			sql1.append(sql.toString()).append(") where num >= ");
            sql1.append((dqy - 1)*myts + 1);
            sql1.append(" and num <= ");
            sql1.append(dqy*myts);
            System.out.println("sql1------"+sql1.toString());

			ps=conn.prepareCall(sql1.toString());
			rs=ps.executeQuery();
			while(rs.next()){
				Map map= new HashMap();
				map.put("badjxh", rs.getString("badjxh"));
				if(!bo.getDqzt().equals("01")){
					map.put("bgbxh",rs.getString("bgbxh"));
				}
				BAHTXX bahtxx = new BAHTXX();
				bahtxx.setHtmc(rs.getString("htmc"));
				bahtxx.setHtbh(rs.getString("htbh"));
				bahtxx.setHtje(rs.getString("htje"));
				map.put("bahtxx", bahtxx);
				list.add(map);
			}
			bo.setQysdsbgbList(list);
			rs.close();
			ps.close();
		}
            else{
            	//封装返回值
                bo.setZys("0");
                bo.setDqy("0");
                bo.setQysdsbgbList(list);
    		}
		}
		
		 catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		return bo;
	}

	/**
	 * doQuery 用于返回页面索要查询的详尽信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]：
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 *
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		// 得到Action传递过来KjqysdsbgbBO对象
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KJYWRXX kjywrxx=new KJYWRXX();
		StringBuffer sql=new StringBuffer();
		
		
		// 得到当前时间的所属月
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// 税款所属开始日期
		bo.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// 税款所属结束日期
		bo.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			/**
			 *判断该记录是否已确认且已生成代扣代缴信息 
			 */
			sql.append("select count(*) from sbdb.sb_jl_kjqysds_htbadjb a where a.jsjdm='");
			sql.append(bo.getJsjdm()).append("' and a.badjxh='");
			sql.append(bo.getBadjxh()).append("' and a.ztbz='1'");
			ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery(sql.toString());

            rs.next();
            int count1 = rs.getInt(1);
            sql.delete(0, sql.length());
            rs.close();
            ps.close();
            if(count1==0){
            	bo.setFlag(false);
            }
            else{
				sql.append("select a.fjmgjdq from sbdb.sb_jl_kjqysds_fjmqyxx a where a.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				ps = conn.prepareStatement(sql.toString());
	            rs = ps.executeQuery(sql.toString());
				rs.next();
				String fjmgjdg=rs.getString("fjmgjdq");
				// 税种税目代码
	            String szsmdm = new String();
	            if ("01".equals(fjmgjdg)) {
	                szsmdm = "300021";
	            }
	            else if ("02".equals(fjmgjdg)) {
	                szsmdm = "300022";
	            }
	         // 判断是否已存在对应税种税目的代扣代缴认定信息
	            sql.delete(0, sql.length());
	            sql.append("select count(*) from sfdb.sf_jl_wtdwmx where ");
	            sql.append("jsjdm = '").append(bo.getJsjdm()).append("' ");
	            sql.append("and szsmdm = '").append(szsmdm).append("'");
	
	            ps = conn.prepareStatement(sql.toString());
	            rs = ps.executeQuery(sql.toString());
	
	            rs.next();
	            sql.delete(0, sql.length());
	            int count = rs.getInt(1);
	            rs.close();
	            ps.close();
	            
	
	            // 不存在对应税种税目的代扣代缴认定记录
	            if (count == 0)
	            {
	            	bo.setFlag(false);
	            }
	            else{
					/**
					 * 从登记接口取扣缴义务人信息
					 */
					SWDJJBSJ djxx = null;
					UserData ud = (UserData) vo.getUserData();
					try {
		
						djxx = InterfaceDj.getJBSJ_New(
								bo.getJsjdm(), ud);
		
		
					} catch (Exception ex1) {
						throw ExceptionUtil.getBaseException(ex1);
					}
					kjywrxx.setKjrnssbh(djxx.getSwdjzh());//纳税人识别号
					kjywrxx.setKjrdz_cn(djxx.getJydz());//中文地址
					kjywrxx.setKjryzbm(djxx.getJydzyb());//邮政编码
					kjywrxx.setKjrmc_cn(djxx.getNsrmc());//中文名称
					kjywrxx.setKjrjjlxdm(djxx.getDjzclxdm());//经济类型代码
					kjywrxx.setKjrjjlxmc(djxx.getDjzclxmc());//经济类型名称
					kjywrxx.setKjrjjxlfldm(djxx.getGjbzhydm());//经济行业分类代码
					kjywrxx.setKjrjjxlflmc(djxx.getGjbzhymc());//经济行业分类名称
					/**
					 * 从扣缴义务人表中取扣缴义务人其它信息
					 */
					sql.append("select * from SBDB.SB_JL_KJQYSDS_KJYWR t where t.badjxh='");
					sql.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					while(rs.next()){
						kjywrxx.setKjrmc_en(rs.getString("kjrmc_en"));//英文名称
						kjywrxx.setKjrdz_en(rs.getString("kjrdz_en"));//英文地址
						kjywrxx.setKjrlxr(rs.getString("kjrlxr"));//联系人
						kjywrxx.setKjrlxdh(rs.getString("kjrlxdh"));//联系电话
					}
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
					bo.setKjywrxx(kjywrxx);
					/**
					 * 从扣缴企业所得税非居民企业信息表中取非居民信息
					 */
					sql.append("select t.fjmjmgnssbh,t.fjmgjdq,t.fjmgb,a.gjdqmc,t.fjmmc_cn,t.fjmmc_en,t.fjmjmgmc_cn,t.fjmjmgmc_en,t.fjmdz_cn,t.fjmdz_en from SBDB.SB_JL_KJQYSDS_FJMQYXX t,dmdb.gy_dm_gjdq a where t.badjxh='");
					sql.append(bo.getBadjxh()).append("' and a.gjdqdm=t.fjmgb");
					System.out.println("fjmqyxx-----sql----"+sql.toString());
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					FJMQYXX fjmqyxx = new FJMQYXX();
					while(rs.next()){
						fjmqyxx.setFjmjmgnssbh(rs.getString("fjmjmgnssbh"));//在其居民国纳税识别号
						fjmqyxx.setFjmgjdq(rs.getString("fjmgjdq"));//国家或地区
						String gb=rs.getString("fjmgb")+"|"+rs.getString("gjdqmc");
						fjmqyxx.setFjmgb(gb);//居民国（地区）名称及代码
						fjmqyxx.setFjmmc_cn(rs.getString("fjmmc_cn"));//在中国境内的中文名称
						fjmqyxx.setFjmmc_en(rs.getString("fjmmc_en"));//在中国境内的英文名称
						fjmqyxx.setFjmjmgmc_cn(rs.getString("fjmjmgmc_cn"));//在其居民国中文名称
						fjmqyxx.setFjmjmgmc_en(rs.getString("fjmjmgmc_en"));//在其居民国英文名称
						fjmqyxx.setFjmdz_cn(rs.getString("fjmdz_cn"));//在其居民国中文地址
						fjmqyxx.setFjmdz_en(rs.getString("fjmdz_en"));//在其居民国英文地址	
					}
					bo.setFjmqyxx(fjmqyxx);
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
		
					
					/**
					 * 从扣缴企业所得税备案合同明细表中取得合同信息
					 */
					sql.append("select t.htmc,t.htbh,to_char(t.htzxqsrq,'yyyymmdd') htzxqsrq,to_char(t.htzxzzrq,'yyyymmdd') htzxzzrq,t.htje from SBDB.SB_JL_KJQYSDS_BAHTMX t where t.badjxh='");
					sql.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					BAHTXX bahtxx = new BAHTXX();
					while(rs.next()){
						bahtxx.setHtmc(rs.getString("htmc"));
						bahtxx.setHtbh(rs.getString("htbh"));
						bahtxx.setHtzxqsrq(rs.getString("htzxqsrq"));
						bahtxx.setHtzxzzrq(rs.getString("htzxzzrq"));
						bahtxx.setHtje(rs.getString("htje"));
					}
					bo.setBahtxx(bahtxx);
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
					
					/**
					 * 从扣缴企业所得税扣缴报告表明细数据表中取出报告明细信息
					 */
					System.out.println("bo.getBgbxh----"+bo.getBgbxh());
					try{
						if(!"".equals(bo.getBgbxh())){
							sql.append("select * from SBDB.SB_JL_KJQYSDS_KJBGMX t where t.badjxh='");
							sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
							sql.append(bo.getBgbxh()).append("'");
							ps=conn.prepareStatement(sql.toString());
							rs=ps.executeQuery();
							List list=new ArrayList();
							while(rs.next()){
								Map map=new HashMap();
								map.put("hc",rs.getString("hc"));
								map.put("sj", rs.getString("yz"));
								list.add(map);
							}
							bo.setQysdsbgbList(list);
							sql.delete(0,sql.length());
							rs.close();
							ps.close();
							
							/**
							 * 从扣缴企业所得税扣缴报告表中取得申报所得类型代码,申报所得取得日期,税款所属日期
							 */
							sql.append("select t.SBSDLXDM,to_char(t.SBSDQDRQ,'yyyymmdd') sbsdqdrq,to_char(t.skssksrq,'yyyymmdd') skssksrq,to_char(t.skssjsrq,'yyyymmdd') skssjsrq from SBDB.SB_JL_KJQYSDS_KJBGB t where t.badjxh='");
							sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
							sql.append(bo.getBgbxh()).append("'");
							ps=conn.prepareStatement(sql.toString());
							rs=ps.executeQuery();
							while(rs.next()){
								bo.setSbsdlxdm(rs.getString("sbsdlxdm"));
								bo.setSbsdqdrq(rs.getString("sbsdqdrq"));
								bo.setSkssksrq(rs.getString("skssksrq"));
								bo.setSkssjsrq(rs.getString("skssjsrq"));
							}
						}
						rs.close();
						ps.close();
						sql.delete(0,sql.length());
					} catch (Exception ex1) {
						throw ExceptionUtil.getBaseException(ex1);
					}
					/**
				     * 获取扣缴企业所得税申报信息
				     */
					List sbsdlxList = new ArrayList();
			        sql.append("select sbsdlxdm,sbsdlxdm||'|'||sbsdlxmc sbsdlxmc from DMDB.SB_DM_FJMSBSDLXDM where zxbs='0' order by xssx");
		
			        try {
			            ps = conn.prepareStatement(sql.toString());
			            rs = ps.executeQuery(sql.toString());
			            while (rs.next()) {
			            	SBSDLX sbsdlx = new SBSDLX();
			                //申报所得类型代码
			                sbsdlx.setSbsdlxdm(rs.getString("sbsdlxdm"));
			                //申报所得类型名称
			                sbsdlx.setSbsdlxmc(rs.getString("sbsdlxmc"));
		
			                sbsdlxList.add(sbsdlx);
			            }
		
			            //关闭数据库对象
			            rs.close();
			            ps.close();
			        }
			        catch (Exception ex) {
			            ex.printStackTrace();
			            throw new ApplicationException("获取扣缴企业所得税申报信息错误!");
			        }
			        bo.setSbsdlxList(sbsdlxList);
					bo.setFlag(true);
	            }
            }
		} catch (Exception e) {
			// 抛出异常
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 查询成功返回bo
		return bo;
	}

	/**
	 * doSave 用于存储页面提交的详尽处理信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// 得到Action传递过来KjqysdsbgbBO对象

		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		try {

			// 创建数据库连接
			conn = SfDBResource.getConnection();
			/**
			 * 保存扣缴义务人信息
			 */
			try{
				sql.append("update SBDB.SB_JL_KJQYSDS_KJYWR set KJRDZ_EN='");
				sql.append(bo.getKjywrxx().getKjrdz_en()).append("' where badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("扣缴义务人信息----"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				int flag=ps.executeUpdate(sql.toString());
				ps.close();
				sql.delete(0, sql.length());
			}
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            throw new ApplicationException("保存扣缴义务人表-扣缴义务人信息失败!");
	        }

			
			/**
			 * 保存非居民信息
			 */
			sql.append("update SBDB.SB_JL_KJQYSDS_FJMQYXX set FJMJMGNSSBH='");
			sql.append(bo.getFjmqyxx().getFjmjmgnssbh());
			sql.append("',FJMJMGMC_CN='");
			sql.append(bo.getFjmqyxx().getFjmjmgmc_cn());
			sql.append("',FJMJMGMC_EN='");
			sql.append(bo.getFjmqyxx().getFjmjmgmc_en());
			sql.append("' where badjxh='");
			sql.append(bo.getBadjxh());
			sql.append("'");
			System.out.println("保存非居民信息----"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeUpdate(sql.toString());
			ps.close();
			sql.delete(0, sql.length());
			/**
			 * 保存合同信息
			 */
			sql.append("update SBDB.SB_JL_KJQYSDS_BAHTMX set HTZXQSRQ=to_date('");
			sql.append(bo.getBahtxx().getHtzxqsrq());
			sql.append("','yyyy-mm-dd'),HTZXZZRQ=to_date('");
			sql.append(bo.getBahtxx().getHtzxzzrq());
			sql.append("','yyyy-mm-dd') where badjxh='");
			sql.append(bo.getBadjxh());
			sql.append("'");
			System.out.println("合同----sql----"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeUpdate(sql.toString());
			ps.close();
			sql.delete(0, sql.length());
			
			/**
			 * 保存扣缴报告主表
			 */
			StringBuffer sql1=new StringBuffer();
			String bgbxh="";
			try{
				System.out.println("bo.getBgbxh----"+bo.getBgbxh());
				if("".equals(bo.getBgbxh())||bo.getBgbxh()==null ||"null".equals(bo.getBgbxh())){
					System.out.println("1111111111");
				}
				if(!("".equals(bo.getBgbxh()) || bo.getBgbxh()==null || "null".equals(bo.getBgbxh()))){
	
					sql1.append("update SBDB.SB_JL_KJQYSDS_KJBGB set sbsdlxdm='");
					sql1.append(bo.getSbsdlxdm());
					sql1.append("',sbsdqdrq=to_date('");
					sql1.append(bo.getSbsdqdrq());
					sql1.append("','yyyy-mm-dd') where badjxh='");
					sql1.append(bo.getBadjxh());
					sql1.append("' and bgbxh='");
					sql1.append(bo.getBgbxh()).append("'");
				}
				else{
					bgbxh=createBgbxh(conn);
					sql1.append("insert into SBDB.SB_JL_KJQYSDS_KJBGB(bgbxh,badjxh,jsjdm,htbh,sbsdlxdm,sbsdqdrq,lrry,lrrq,cjry,cjrq,skssksrq,skssjsrq) values('");
					sql1.append(bgbxh).append("','");
					sql1.append(bo.getBadjxh()).append("','");
					sql1.append(bo.getJsjdm()).append("','");
					sql1.append(bo.getBahtxx().getHtbh()).append("','");
					sql1.append(bo.getSbsdlxdm()).append("',to_date('");
					sql1.append(bo.getSbsdqdrq()).append("','yyyy-mm-dd'),'");
					sql1.append(bo.getLrr()).append("',sysdate,'");
					sql1.append(bo.getLrr()).append("',sysdate,to_date('");
					sql1.append(bo.getSkssksrq()).append("','yyyy-mm-dd'),to_date('");
					sql1.append(bo.getSkssjsrq()).append("','yyyy-mm-dd'))");
				}
				System.out.println("主表----sql---"+sql1.toString());
				ps=conn.prepareStatement(sql1.toString());
				ps.execute(sql1.toString());
				sql1.delete(0,sql1.length());
				ps.close();
			
			}
			catch(Exception e)
			{
			    e.printStackTrace();
			    throw new ApplicationException("保存扣缴报告主表信息失败!");
			}
			
			/**
			 * 保存扣缴报告表明细表
			 */
			try{
				if(!("".equals(bo.getBgbxh()) || bo.getBgbxh()==null || "null".equals(bo.getBgbxh()))){
					bgbxh=bo.getBgbxh();
					sql1.append("delete SBDB.SB_JL_KJQYSDS_KJBGMX where bgbxh='");
					sql1.append(bgbxh).append("' and badjxh='");
					sql1.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql1.toString());
					ps.execute(sql1.toString());
					sql1.delete(0,sql1.length());
					ps.close();
				}
				//插入明细表数据
				sql.append("insert into SBDB.SB_JL_KJQYSDS_KJBGMX(bgbxh,badjxh,jsjdm,htbh,hc,yz,lrry,lrrq,cjry,cjrq) values('");
				sql.append(bgbxh).append("','");
				sql.append(bo.getBadjxh()).append("','");
				sql.append(bo.getJsjdm()).append("','");
				sql.append(bo.getBahtxx().getHtbh()).append("',?,?,'");
				sql.append(bo.getLrr()).append("',sysdate,'");
				sql.append(bo.getLrr()).append("',sysdate)");
				System.out.println("明细---sql----"+sql.toString());
				List list = bo.getQysdsbgbList();
				int sp = 0; // 开始执行point
	            int ep = 0; // 结束执行point
	            int maxlength = list.size();
	            ps=conn.prepareStatement(sql.toString());
	            while (ep < maxlength) {
	                // 每次循环提交50笔
	                if ((sp + 50) < maxlength) {
	                    ep = sp + 50;
	                } else {
	                    ep = maxlength;
	                }
	                for (int j = sp; j < ep; j++) {
	                	HashMap map = (HashMap) list.get(j);
	    				String hc = (String) map.get("hc");
	    				String sj = (String) map.get("sj");
	    				System.out.println("hc1----------"+hc);
	    				if(!"".equals(sj)){
		                    ps.setString(1, hc);
		                    System.out.println("hc2----------"+hc);
		                    ps.setString(2, sj);
		                    ps.addBatch();
	    				}
	                    
	                }
	                ps.executeBatch();
	                sp = ep;             
	            }
	            sql.delete(0,sql.length());
	            ps.close();
	            bo.setBgbxh(bgbxh);
	            System.out.println("----end------"+bo.getBgbxh());
			}
		catch(Exception e)
		{
		    e.printStackTrace();
		    throw new ApplicationException("保存扣缴报告表明细表信息失败!");
		}
		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 保存成功返回bo
		return bo;
	}

	/**
	 * 计算季报类型的税款所属日期
	 *
	 * @param curDate
	 *            日期
	 * @return Map 使用Key ＝ Skssrq.SKSSKSRQ 得到 税款所属开始日期Timestamp 使用Key ＝
	 *         Skssrq.SKSSJSRQ 得到 税款所属结束日期Timestamp 
	 */
	public Map quarterSkssrq(Date curDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				jd * 3 + 2, new GregorianCalendar(year, jd * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
		Map retMap = new HashMap();
		retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
		retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
		retMap.put(Skssrq.SKSSRQ_ND, nd);
		return retMap;
	}

	/**
	 * doDelete 用于删除页面提交的详尽处理信息
	 *
	 * @param vo
	 *            业务参数
	 * @return 数据对象[如果不是null(即Action不需要返回值)，如果需要返回值，必须是ActionForm]
	 * @throws BaseException
	 *             当其他情况都会抛出异常信息
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		// 得到Action传递过来KjqysdsbgbBO对象
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		try {
			// 创建数据库连接
			conn = SfDBResource.getConnection();
			//标注报告表删除
			sql.append("update SBDB.SB_JL_KJQYSDS_KJBGB set scbs='2' where badjxh='");
			sql.append(bo.getBadjxh()).append("' and bgbxh='");
			sql.append(bo.getBgbxh()).append("'");
			System.out.println("删除----sql---"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeQuery(sql.toString());

		} catch (Exception ex) {
			// 抛出异常
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		// 删除成功返回bo
		return bo;
	}
	
    /**
     * 生成报告表序号
     *    生成规则：8为日期 + 6位当天总数量递增1后的序号
     * @param conn Connection 数据库连接
     * @return String 返回生成的14位编码
     * @throws BaseException
     */
    private String createBgbxh(Connection conn) throws BaseException
    {
        // 备案登记号
        StringBuffer badjxh = new StringBuffer();
        // 查询系统当前时间sql
        StringBuffer sql = new StringBuffer("select to_char(sysdate,'yyyymmdd') xtsj from dual");

        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try
        {
            // 查询当前8为系统时间
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(rs.getString("xtsj"));

            // 查询当前用户当天合同总数
            sql.delete(0, sql.length());
            sql.append("select count(*) + 1 from SBDB.SB_JL_KJQYSDS_KJBGB where ");
            sql.append("to_char(cjrq, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(SBStringUtils.LPAD(String.valueOf(rs.getInt(1)), 6, "0"));
            System.out.println("备案登记表序号：" + badjxh.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("创建备案登记序号错误！");
        }
        finally
        {
            try
            {
                // 关闭数据库对象
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch(Exception ex)
            {
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }

        return badjxh.toString();
    }
}

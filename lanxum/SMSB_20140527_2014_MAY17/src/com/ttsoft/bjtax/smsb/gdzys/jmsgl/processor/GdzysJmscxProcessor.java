package com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmscxForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: 耕地资源占用税-减免税查询
 * </p>
 * <p>
 * Description: 查询减免税Processor。
 * </p>
 * 
 * @author 开发部 - 霍洪波
 * @version 1.0
 */
public class GdzysJmscxProcessor implements Processor {

	public GdzysJmscxProcessor() {

	}

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		/** @todo Implement this com.ttsoft.framework.processor.Processor method */
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case GdzysCodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case GdzysCodeConstant.SMSB_SAVEACTION:
			result= doShow(vo);
			break;
		case GdzysCodeConstant.SMSB_SHOWACTION:
			result=doPrintQuery(vo);
			break;
		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}
		return result;
	}
	//初始页
	public Object doShow(VOPackage vo) throws BaseException{
		GdzysJmscxForm kf = (GdzysJmscxForm) vo.getData();
	        Connection conn = null;
	        try {
	            conn = SfDBResource.getConnection();
	            SfDBAccess db = new SfDBAccess(conn);
	            UserData userData = vo.getUserData();
	            //取得所有税务机关的代码
	            kf.setJgList(getSwjsList(db, userData));
	            return kf;
	        }
	        catch (SystemException ex) {
	            throw ExceptionUtil.getBaseException(ex);
	        }
	        finally {
	            SfDBResource.freeConnection(conn);
	        }
	}
    /**
     * 获取税务局列表
     * @param db
     * @param userData
     * @return
     * @throws BaseException
     */
    private ArrayList getSwjsList(SfDBAccess db, UserData userData) throws BaseException {
        ArrayList list = new ArrayList();
        try {
            //税务局
            String iQxdm = InterfaceDj.getQxdm(userData); //2位区县代码
            StringBuffer sb  = new StringBuffer();
           sb.append(" select b.swjgzzjgdm VALUE ,b.swjgzzjgmc descr from dmdb.gy_dm_swjgzzjg b ");
            LabelValueBean label = new LabelValueBean("*所有税务所", "0");
            list.add(label);
            ResultSet rs  = db.querySQL(sb.toString());
            while(rs.next()){
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue((String)rs.getString("value"));
                bean.setLabel((String)rs.getString("descr"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }
    //减免税查询
	public Object doQuery(VOPackage vo) throws BaseException {

		Connection conn = null;
		List list = new ArrayList();

		GdzysJmscxForm myForm = (GdzysJmscxForm) vo.getData();
		//取得ud
		UserData ud =vo.getUserData();
	
		try {
			conn = SfDBResource.getConnection();
			myForm.setJgList(getSwjsList(new SfDBAccess(conn),vo.getUserData()));
			StringBuffer result = new StringBuffer("");
			
			//权限
			  String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"SBDB", "SB_JL_GDZYS_SBB" );
			  System.out.println("权限结果#######"+datafilter);
			//查询减免税sql
			result.append("select a.sylxdm,b.zfbz,a.SBBXLH,a.NSRMC,a.JSJDM,a.jsmj,a.jmse,a.CJRQ ,a.jmmj, a.cjrq,b.jmszmbh from SBDB.SB_JL_GDZYS_SBB a, SBDB.SB_JL_GDZYS_JMSZM b where a.SBBXLH=b.SBBXLH ");
			//将权限加到sql中
			result.append("and "+datafilter);

			if (myForm.getJsjdm() != null && !myForm.getJsjdm().equals("")) {
				result.append(" and a.JSJDM=?");
			}
			if (myForm.getSbxlh() != null && !myForm.getSbxlh().equals("")) {
				result.append(" and a.SBBXLH=?");
			}
			if (myForm.getNsrmc() != null && !myForm.getNsrmc().equals("")) {
				result.append(" and a.NSRMC=?");
			}
			if (myForm.getPzzdwh() != null && !myForm.getPzzdwh().equals("")) {
				result.append(" and a.PZJDWH=?");
			}
			if (myForm.getStatus() != null && !myForm.getStatus().equals("")&&!myForm.getStatus().equals("全部")) {
				result.append(" and b.ZFBZ=?");
			}
			String sql = result.toString();
			System.out.println(sql + "###############");
			PreparedStatement ps = conn.prepareStatement(sql);
			//查询税源基本信息的sql
			String baseInfoSql = "SELECT (select djzclxmc from  dmdb.dj_dm_djzclx where djzclxdm=a.qydjzclx and rownum<=1) djzclxmc,(select gjbzhymc from  dmdb.gy_dm_gjbzhy where gjbzhydm=a.nsrsshy and rownum<=1) gjbzhymc," +
					"a.sylxdm,a.jsjdm,a.sbbxlh,a.nsrmc,a.nsrlx,a.sfsjsp,b.sylxmc,a.swdjzh,a.zzjgdm,a.nsrsshy,a.nsrxxdz,a.qydjzclx,a.khyhmc,a.yhzh,a.sbjmly,a.bzms,"
					+ "a.jsmj,a.jzse,a.jmmj,a.jmse,a.ynse,a.lxrxm,a.lxdh,a.tdzldz,a.pzjdwh,a.pzjdmj,a.jsxmmc,a.sjzdmj,a.zdsj,a.cjr,a.cjrq,a.qrr,a.qrrq,a.qrzt,a.sjqrr,a.sjqrrq,a.sjqrzt FROM SBDB.SB_JL_GDZYS_SBB a , DMDB.GD_DM_SYLX b"
					+ " where a.sylxdm=b.sylxdm and a.sbbxlh=?";
			//查询税源中减免税类别的sql
			String styleInfoSql = "SELECT b.jmslbmc,a.jmmj,a.jmse FROM SBDB.SB_JL_GDZYS_SBBJMMX a , DMDB.GD_DM_JMSLB b where a.jmslbdm=b.jmslbdm and a.sbbxlh=?";
			//查询占地用途sql
			String landSql = "SELECT b.zdytmc,a.syse,a.jsmj,a.jzse,a.jmmj,a.jmse,a.ynse FROM SBDB.SB_JL_GDZYS_SBBSBMX a , DMDB.GD_DM_ZDYT b where a.zdytdm=b.zdytdm and a.sbbxlh=? ";
			PreparedStatement ps1 = null;
			//设置权限
			int i = 1;
			if (myForm.getJsjdm() != null && !myForm.getJsjdm().equals("")) {
				ps.setString(i++, myForm.getJsjdm());
			}
			if (myForm.getSbxlh() != null && !myForm.getSbxlh().equals("")) {
				ps.setString(i++, myForm.getSbxlh());
			}
			if (myForm.getNsrmc() != null && !myForm.getNsrmc().equals("")) {
				ps.setString(i++, myForm.getNsrmc());
			}
			if (myForm.getPzzdwh() != null && !myForm.getPzzdwh().equals("")) {
				ps.setString(i++, myForm.getPzzdwh());
			}
			if (myForm.getStatus() != null && !myForm.getStatus().equals("")&&!myForm.getStatus().equals("全部")) {
				ps.setString(i++, myForm.getStatus());
			}
			
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = null;

			while (rs.next()) {
				//存放土地信息的集合
				List landInfoList = new ArrayList();
				//存放减免税类别的集合
				List styleList = new ArrayList();
				Map map = new HashMap();
				if(rs.getString("sylxdm").equals("0")){
					continue;
				}
				
				// 计算机代码
				map.put("jsjdm", rs.getString("JSJDM")==null ? "":rs.getString("jsjdm"));
				// 申报序列号
				map.put("sbbxlh", rs.getString("SBBXLH")==null?"":rs.getString("sbbxlh"));
				// 纳税人名称
				map.put("nsrmc", rs.getString("NSRMC")==null?"":rs.getString("nsrmc"));
				//计税面积
				map.put("jsmj", rs.getBigDecimal("jsmj")==null?"":rs.getBigDecimal("jsmj").toString());
				// 减免面积
				map.put("jmmj", rs.getBigDecimal("jmmj")==null?"":rs.getBigDecimal("jmmj").toString());
				// 减免税额
				map.put("jmse", rs.getBigDecimal("jmse")==null?"":rs.getBigDecimal("jmse").toString());
				// 申报日期
				map.put("cjrq",
						rs.getString("CJRQ") == null ? "" : (rs
								.getString("CJRQ").substring(0, 10)));
				//作废标识
				map.put("zfbz", rs.getString("zfbz")==null?"":rs.getString("zfbz"));
				//减免税证明编号
				map.put("jmszmbh", rs.getString("jmszmbh")==null?"":rs.getString("jmszmbh"));

				ps1 = conn.prepareStatement(baseInfoSql);
				ps1.setString(1, rs.getString("sbbxlh"));
				System.out.println(baseInfoSql+"####################");
				rs1 = ps1.executeQuery();
				while (rs1.next()) {
				
					// 纳税人类型
					map.put("nsrlx", rs1.getString("nsrlx")==null?"":(rs1.getString("nsrlx").equals("0")?"企业纳税人":"个人纳税人"));
					// 是否市局审批
					map.put("sfsjsp", Integer.parseInt((rs1.getString("sfsjsp")==null?"":rs1.getString("sfsjsp")))==0?"否":"是");
					// 税源类型名称
					map.put("sylxmc", rs1.getString("sylxmc")==null?"":rs1.getString("sylxmc"));
					// 税务登记账号
					map.put("swdjzh", rs1.getString("swdjzh")==null?(rs1.getString("zzjgdm")==null?"":rs1.getString("zzjgdm")):rs1.getString("swdjzh"));
					// 纳税人所属行业
					map.put("nsrsshy", rs1.getString("gjbzhymc")==null?"":rs1.getString("gjbzhymc"));
					// 纳税人详细地址
					map.put("nsrxxdz", rs1.getString("nsrxxdz")==null?"":rs1.getString("nsrxxdz"));
					// 企业登记注册类型
					map.put("qydjzclx", rs1.getString("djzclxmc")==null?"":rs1.getString("djzclxmc"));
					// 开户银行名称
					map.put("khyhmc", rs1.getString("khyhmc")==null?"":rs1.getString("khyhmc"));
					// 银行账号
					map.put("yhzh", rs1.getString("yhzh")==null?"":rs1.getString("yhzh"));
					// 联系人姓名
					map.put("lxrxm", rs1.getString("lxrxm")==null?"":rs1.getString("lxrxm"));
					// 联系电话
					map.put("lxdh", rs1.getString("lxdh")==null?"":rs1.getString("lxdh"));
					// 土地坐落地址
					map.put("tdzldz", rs1.getString("tdzldz")==null?"":rs1.getString("tdzldz"));
					//批准占地文号
					map.put("pzzdwh", rs1.getString("pzjdwh")==null?"":rs1.getString("pzjdwh"));
					//实际占地面积
					map.put("sjzdmj", rs1.getBigDecimal("sjzdmj")==null?"":rs1.getBigDecimal("sjzdmj").toString());
					// 批准占地面积
					map.put("pzjdmj", rs1.getBigDecimal("pzjdmj")==null?"":rs1.getBigDecimal("pzjdmj").toString());
					// 建设项目名称
					map.put("jsxmmc", rs1.getString("jsxmmc")==null?"":rs1.getString("jsxmmc"));
					// 占地时间
					map.put("zdsj", rs1.getString("zdsj")==null?"":(rs1
							.getString("zdsj").substring(0, 10)));
					// 创建人
					map.put("cjr", rs1.getString("cjr")==null?"":rs1.getString("cjr"));
					// 创建日期
					map.put("cjrq", rs1.getString("cjrq")==null?"":(rs1
							.getString("cjrq").substring(0, 10)));
					// 确认人
					map.put("qrr", rs1.getString("qrr")==null?"":rs1.getString("qrr"));
					// 确认日期
					map.put("qrsj", rs1.getString("qrrq")==null?"":(rs1
							.getString("qrrq").substring(0, 10)));
					//申报减免理由
					map.put("sbjmly", rs1.getString("sbjmly")==null?"":rs1.getString("sbjmly"));
					//备注
					map.put("bzms", rs1.getString("bzms")==null?"":rs1.getString("bzms"));
					// 确认状态
					map.put("qrzt", rs1.getString("qrzt")==null?"":(rs1.getString("qrzt").equals("1")?"已确认":"未确认"));
					//市局确认人
					map.put("sjqrr", rs1.getString("sjqrr")==null?"":(rs1.getString("sjqrr")));
					//市局确认日期
					map.put("sjqrrq", rs1.getString("sjqrrq")==null?"":(rs1.getString("sjqrrq")).substring(0, 10));
					//市局确认状态
					map.put("sjqrzt", rs1.getString("sjqrzt")==null?"":(rs1.getString("sjqrzt").equals("1")?"已确认":"未确认"));
					//计税面积合计
					map.put("jsmj", rs1.getBigDecimal("jsmj")==null?"":(rs1.getBigDecimal("jsmj").toString()));
					//计征税额合计
					map.put("jzse", rs1.getBigDecimal("jzse")==null?"":(rs1.getBigDecimal("jzse").toString()));
					//减免面积合计
					map.put("jmmj", rs1.getBigDecimal("jmmj")==null?"":(rs1.getBigDecimal("jmmj").toString()));
					//减免税额合计
					map.put("jmse", rs1.getBigDecimal("jmse")==null?"":(rs1.getBigDecimal("jmse").toString()));
					//应纳税额合计
					map.put("ynse", rs1.getBigDecimal("ynse")==null?"":(rs1.getBigDecimal("ynse").toString()));

				}
				ps1 = conn.prepareStatement(landSql);
				ps1.setString(1, rs.getString("sbbxlh"));
				rs1 = ps1.executeQuery();
				while (rs1.next()) {

					Map map2 = new HashMap();
					// 占地用途名称
					map2.put("zdytmc", rs1.getString("zdytmc")==null?"":rs1.getString("zdytmc"));
					// 适用税额
					map2.put("syse",  rs1.getBigDecimal("syse")==null?"":rs1.getBigDecimal("syse").toString());
					// 计税面积
					map2.put("jsmj", rs1.getBigDecimal("jsmj")==null?"":rs1.getBigDecimal("jsmj").toString());
					// 计征税额
					map2.put("jzse", rs1.getBigDecimal("jzse")==null?"":rs1.getBigDecimal("jzse").toString());
					// 减免面积
					map2.put("jmmj", rs1.getBigDecimal("jmmj")==null?"":rs1.getBigDecimal("jmmj").toString());
					// 减免税额
					map2.put("jmse", rs1.getBigDecimal("jmse")==null?"":rs1.getBigDecimal("jmse").toString());
					// 应纳税额
					map2.put("ynse", rs1.getBigDecimal("ynse")==null?"":rs1.getBigDecimal("ynse").toString());
					landInfoList.add(map2);
				}
				ps1 = conn.prepareStatement(styleInfoSql);
				ps1.setString(1, rs.getString("sbbxlh"));
				rs1 = ps1.executeQuery();
				while (rs1.next()) {
					Map map1 = new HashMap();
					// 减免税类别名称
					map1.put("jmslbmc", rs1.getString("jmslbmc")==null?"":rs1.getString("jmslbmc"));
					// 减免面积
					map1.put("jmmj", rs1.getBigDecimal("jmmj")==null?"":rs1.getBigDecimal("jmmj").toString());
					// 减免税额
					map1.put("jmse", rs1.getBigDecimal("jmse")==null?"":rs1.getBigDecimal("jmse").toString());
					styleList.add(map1);
				}
				//map.put("", value)
				map.put("landInfoList", landInfoList);
				map.put("styleList", styleList);
				list.add(map);
			}
			myForm.setDataList(list);
			rs.close();
			ps.close();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return myForm;
	}
	//打印减免税证明查询
	public Object doPrintQuery(VOPackage vo) throws BaseException {
		Connection conn = null;
		List list = new ArrayList();

		GdzysJmscxForm myForm = (GdzysJmscxForm) vo.getData();
		String sbbxlh=myForm.getSbxlh();
		String jmszmbh=myForm.getJmszmbh();
		try {
			//获得数据库连接
			conn = SfDBResource.getConnection();
			//减免税证明信息的查询sql
			StringBuffer result = new StringBuffer(" SELECT a.cjr,a.cjrq,a.kjrq,b.sjqrrq,a.jmszmbh,c.qxfjmc,c.qxfjsx,b.pzjdwh,b.nsrmc,b.jsjdm,b.jmse,b.sfsjsp," +
					"b.jmmj,b.jzse,b.jsmj FROM SBDB.SB_JL_GDZYS_JMSZM a, " +
					" SBDB.SB_JL_GDZYS_SBB b, DMDB.GD_DM_QXFJDM c where a.sbbxlh=b.sbbxlh  " +
					" and b.qxdm=c.qxdm " +
					" and b.sbbxlh=? and a.zfbz='0' and a.jmszmbh=?");
			//减免税依据查询sql
			String jmyjsql="select jmsyjmc from DMDB.GD_DM_JMSYJ where jmsyjdm in(select jmsyjdm from DMDB.GD_DM_JMSLB " +
					"where jmslbdm in (select jmslbdm from SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh=?))";
			String sql=result.toString();
			List jmyjlist=new ArrayList();
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			ps.setString(2, jmszmbh);
			PreparedStatement ps1=null;
			PreparedStatement ps2=null;
			ResultSet rs1=null;
			ResultSet rs2=null;
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				Map map = new HashMap();
				//简称
				map.put("wsjc", rs.getString("qxfjsx")==null?"":rs.getString("qxfjsx"));
				//批准占地文号
				map.put("pzjdwh", rs.getString("pzjdwh")==null?"":rs.getString("pzjdwh"));
				//减免税证明编号
				map.put("jmszmbh", rs.getString("jmszmbh")==null?"":rs.getString("jmszmbh"));
				jmszmbh=rs.getString("jmszmbh")==null?"":rs.getString("jmszmbh");
				//纳税人名称
				map.put("nsrmc", rs.getString("nsrmc")==null?"":rs.getString("nsrmc"));
				//计算机代码
				map.put("jsjdm", rs.getString("jsjdm")==null?"":rs.getString("jsjdm"));
				//减免税额
				map.put("jmse", rs.getBigDecimal("jmse")==null?"":rs.getBigDecimal("jmse").toString());
				//减免面积
				map.put("jmmj", rs.getBigDecimal("jmmj")==null?"":rs.getBigDecimal("jmmj").toString());
				//计征税额
				map.put("jzse", rs.getBigDecimal("jzse")==null?"":rs.getBigDecimal("jzse").toString());
				//计税面积
				map.put("jsmj", rs.getBigDecimal("jsmj")==null?"":rs.getBigDecimal("jsmj").toString());
				ps1=conn.prepareStatement(jmyjsql);
				ps1.setString(1, sbbxlh);
				rs1=ps1.executeQuery();
				while(rs1.next()){
					//获得所有减免税依据
					jmyjlist.add(rs1.getString("jmsyjmc")==null?"":rs1.getString("jmsyjmc"));
				}
				map.put("jmyjlist", jmyjlist);
				//不是市局审批，时间取区县审核时间
				if(rs.getString("sfsjsp").equals("0")){
					//税务机关名称
					map.put("swjgmc", rs.getString("qxfjmc")==null?"":rs.getString("qxfjmc"));
					String kjrq=rs.getString("kjrq")==null?"":rs.getString("kjrq").substring(0,10);
					String strs[]=new String[20];
					strs=kjrq.split("-");
					//System.out.println(rs.getString("kjrq"));
					map.put("year", strs[0]);
					map.put("month",strs[1]);
					map.put("day", strs[2]);
				}else{
					//税务机关名称
					ps2=conn.prepareStatement("select qxfjmc from DMDB.GD_DM_QXFJDM where qxdm='90'");
					rs2=ps2.executeQuery();
					String sjmc="";
					while(rs2.next()){
						sjmc=rs2.getString("qxfjmc");
					}
					map.put("swjgmc", sjmc);
					//是市局审核的，从数据库中取市局审核的时间
					String sjqrrq=rs.getString("sjqrrq")==null?"":rs.getString("sjqrrq").substring(0, 10);
					//市局审核时间没填的先置空
					if(sjqrrq.equals("")){
						map.put("year","        ");
						map.put("month", "        ");
						map.put("day", "        ");
					}else{
						String strs[]=new String[3];
						strs=sjqrrq.split("-");
						map.put("year", strs[0]);
						map.put("month", strs[1]);
						map.put("day",strs[2]);
					}
				}
				list.add(map);
			}
			myForm.setSbxlh(sbbxlh);
			myForm.setJmszmbh(jmszmbh);
			myForm.setJmszmList(list);
		

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			SfDBResource.freeConnection(conn);
		}
		return myForm;
	}

}

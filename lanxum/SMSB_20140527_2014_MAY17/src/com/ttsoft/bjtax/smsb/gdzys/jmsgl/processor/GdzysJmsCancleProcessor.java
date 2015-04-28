package com.ttsoft.bjtax.smsb.gdzys.jmsgl.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmsCancleForm;
import com.ttsoft.bjtax.smsb.gdzys.jmsgl.web.GdzysJmscxForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysDateUtil;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.exceptions.TtsoftException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class GdzysJmsCancleProcessor implements Processor{

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		/** @todo Implement this com.ttsoft.framework.processor.Processor method */
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case GdzysCodeConstant.SMSB_SHOWACTION:
			result=doShow(vo);
			break;
		case GdzysCodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case GdzysCodeConstant.SMSB_SAVEACTION:
			result=doView(vo);
			break;
		case GdzysCodeConstant.SMSB_DELETEACTION:
			result=doCancle(vo);
			break;

		default:
			throw new ApplicationException("ActionType有错误，processor中找不到相应的方法.");
		}
		return result;
	}
	//显示减免税查询页面
	public Object doShow(VOPackage vo) throws BaseException{
		GdzysJmsCancleForm kf = (GdzysJmsCancleForm) vo.getData();
        Connection conn = null;
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
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
    //预览减免税证明
	public Object doView(VOPackage vo) throws BaseException{
		Connection conn = null;
		List list = new ArrayList();

		GdzysJmsCancleForm myForm = (GdzysJmsCancleForm) vo.getData();
		String sbbxlh=myForm.getSbbxlh();
		String jmszmbh=myForm.getJmszmbh();
		try {
			//获取数据库连接
			conn = SfDBResource.getConnection();
			//查询减免税证明信息sql
			StringBuffer result = new StringBuffer(" SELECT a.cjr,a.cjrq,a.kjrq,b.sjqrrq,a.jmszmbh,c.qxfjmc,c.qxfjsx,b.pzjdwh,b.nsrmc,b.jsjdm,b.jmse,b.sfsjsp," +
					"b.jmmj,b.jzse,b.jsmj FROM SBDB.SB_JL_GDZYS_JMSZM a  " +
					", SBDB.SB_JL_GDZYS_SBB b  " +
					", DMDB.GD_DM_QXFJDM c" +
					" where  a.sbbxlh=b.sbbxlh and b.qxdm=c.qxdm and b.sbbxlh=?  and a.jmszmbh=?");
			//查询减免税证明依据信息sql
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
					//取得所有减免税证明依据，存放到集合中
					jmyjlist.add(rs1.getString("jmsyjmc")==null?"":rs1.getString("jmsyjmc"));
				}
				map.put("jmyjlist", jmyjlist);
				//不是市局审批的取区县审核时间
				if(rs.getString("sfsjsp").equals("0")){
					//税务机关名称
					map.put("swjgmc", rs.getString("qxfjmc")==null?"":rs.getString("qxfjmc"));
					String kjrq=rs.getString("kjrq")==null?"":rs.getString("kjrq").substring(0,10);
					String strs[]=new String[20];
					strs=kjrq.split("-");
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
					//是市局审核的，从数据库中取时间，如果时间为空的话，就置空
					String sjqrrq=rs.getString("sjqrrq")==null?"":rs.getString("sjqrrq").substring(0, 10);
					if(sjqrrq.equals("")){
						map.put("year", "");
						map.put("month", "");
						map.put("day", "");
					}else{
						String strs[]=new String[20];
						strs=sjqrrq.split("-");
						map.put("year", strs[0]);
						map.put("month", strs[1]);
						map.put("day",strs[2]);
					}
				}
				list.add(map);
			}
			myForm.setSbbxlh(sbbxlh);
			myForm.setJmszmbh(jmszmbh);
			myForm.setJmszmList(list);
		

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭数据库连接
			SfDBResource.freeConnection(conn);
		}
		return myForm;
	}
	//查询减免税证明
	public Object doQuery(VOPackage vo) throws BaseException {
		Connection conn = null;
		List list = new ArrayList();

		GdzysJmsCancleForm myForm = (GdzysJmsCancleForm) vo.getData();
		UserData ud =vo.getUserData();
		
		try {
			conn = SfDBResource.getConnection();
			myForm.setJgList(getSwjsList(new SfDBAccess(conn),vo.getUserData()));
			String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"SBDB", "SB_JL_GDZYS_SBB" );
			System.out.println("撤销减免税证明权限结果#######"+datafilter);
			//减免税证明查询sql（本人只能查询自己出具的减免税证明）（所长可以看本所的）（分局可以看自己下面所有所的）
			StringBuffer result = new StringBuffer("SELECT b.sylxdm,a.sbbxlh,a.jmszmbh,a.jzbz,b.nsrmc,b.jsjdm,b.jsmj,b.jmmj,b.jmse,a.kjrq FROM SBDB.SB_JL_GDZYS_JMSZM a,SBDB.SB_JL_GDZYS_SBB b " +
					"WHERE a.sbbxlh=b.sbbxlh ");
			//将权限添加到sql中
			result.append(" and "+datafilter);
			if (myForm.getJsjdm() != null && !myForm.getJsjdm().equals("")) {
				result.append(" and b.JSJDM=?");
			}
			if (myForm.getSbbxlh() != null && !myForm.getSbbxlh().equals("")) {
				result.append(" and b.SBBXLH=?");
			}
			if (myForm.getNsrmc() != null && !myForm.getNsrmc().equals("")) {
				result.append(" and b.NSRMC=?");
			}
			if (myForm.getPzzdwh() != null && !myForm.getPzzdwh().equals("")) {
				result.append(" and b.PZJDWH=?");
			}
			if (myForm.getStatus() != null && !myForm.getStatus().equals("")&&!myForm.getStatus().equals("全部")) {
				result.append(" and a.zfbz=?");
			}
			String sql = result.toString();
			System.out.println(sql + "###############");
			PreparedStatement ps = conn.prepareStatement(sql);
			int i = 1;
			if (myForm.getJsjdm() != null && !myForm.getJsjdm().equals("")) {
				ps.setString(i++, myForm.getJsjdm());
			}
			if (myForm.getSbbxlh() != null && !myForm.getSbbxlh().equals("")) {
				ps.setString(i++, myForm.getSbbxlh());
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
			while (rs.next()) {
				Map map = new HashMap();
				if(rs.getString("sylxdm").equals("0")){
					continue;
				}
				//申报表序列号
				map.put("sbbxlh", rs.getString("sbbxlh")==null?"":rs.getString("sbbxlh"));
				// 计算机代码
				map.put("jsjdm", rs.getString("JSJDM")==null ? "":rs.getString("jsjdm"));
				// 减免税证明编号
				map.put("jmszmbh", rs.getString("jmszmbh")==null?"":rs.getString("jmszmbh"));
				// 纳税人名称
				map.put("nsrmc", rs.getString("NSRMC")==null?"":rs.getString("nsrmc"));
				//计税面积
				map.put("jsmj", rs.getBigDecimal("jsmj")==null?"":rs.getBigDecimal("jsmj").toString());
				// 减免面积
				map.put("jmmj", rs.getBigDecimal("jmmj")==null?"":rs.getBigDecimal("jmmj").toString());
				// 减免税额
				map.put("jmse", rs.getBigDecimal("jmse")==null?"":rs.getBigDecimal("jmse").toString());
				//记账标识
				map.put("jzbz", rs.getString("jzbz")==null?"":rs.getString("jzbz"));
				// 申报日期
				map.put("kjrq",
						rs.getString("KJRQ") == null ? "" : (rs
								.getString("KJRQ").substring(0, 10)));
				
				list.add(map);
			}
			myForm.setDataList(list);
			rs.close();
			ps.close();
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (TtsoftException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return myForm;
	}
	
	//撤销减免税证明
	public Object doCancle(VOPackage vo) throws BaseException {
		Connection conn = null;
		List list = new ArrayList();

		GdzysJmsCancleForm myForm = (GdzysJmsCancleForm) vo.getData();
		String sbbxlh=myForm.getSbbxlh();
		String jmszmbh=myForm.getJmszmbh();
	
		try {
			conn = SfDBResource.getConnection();
			//从数据库中取出当前的日期
			long mis=GdzysDateUtil.getTimestamp().getTime();
			Date date = new Date(mis);
			DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String str=df.format(date);
			myForm.setJgList(getSwjsList(new SfDBAccess(conn),vo.getUserData()));
			//从减免税证明表中查出作废标识
			String csql="select zfbz from SBDB.SB_JL_GDZYS_JMSZM where sbbxlh=? and jmszmbh=?";
			PreparedStatement ps1 = conn.prepareStatement(csql);
			ps1.setString(1, sbbxlh);
			ps1.setString(2, jmszmbh);
			ResultSet rs = ps1.executeQuery();
			String zfbz="";
			while(rs.next()){
				zfbz=rs.getString("zfbz");
				System.out.println("作废标志有结果#########");
			}
			//判断作废标识（1:已作废 ,0:未作废）
			if(zfbz.equals("1")){
				myForm.setFlag("have");
			}else{
			//更新减免税证明表中作废标识为（已作废），更新作废人，作废日期
			StringBuffer result = new StringBuffer("update SBDB.SB_JL_GDZYS_JMSZM set zfbz='1',zfr=?,zfrq=to_date(?,'yyyy-MM-dd hh24:mi:ss')  where sbbxlh=? and jmszmbh=?");
			String sql = result.toString();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, myForm.getLocalUser());
			ps.setString(2, str);
			ps.setString(3, sbbxlh);
			ps.setString(4, jmszmbh);
			int rnum=ps.executeUpdate();
		    if(rnum!=0){
		    	myForm.setFlag("success");
		    }else{
		    	myForm.setFlag("faile");
		    }
			ps.close();
			}
		} catch (SystemException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
			//throw new ApplicationException("保存数据失败！");
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return myForm;
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

}

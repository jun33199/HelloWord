package com.ttsoft.bjtax.smsb.lwpk.processor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.lwpk.model.PKTaskModel;
import com.ttsoft.bjtax.smsb.lwpk.web.PKTaskForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class PKTaskProcessor implements Processor{

	
	public Object process(VOPackage vo) throws BaseException {
		
		 Object result = null;
	        if (vo == null)
	        {
	            throw new NullPointerException(" VOpackage is null ");
	        }
	        switch (vo.getAction())
	        {
	        	//初始化计划表
	        	case CodeConstant.SMSB_INIT:
                result = doInit(vo);
                break;
	        	//查询计划表
	            case CodeConstant.SMSB_QUERYACTION:
	                result = doQuery(vo);
	                break;
	                //删除计划表
	            case CodeConstant.SMSB_DELETEACTION:
	                result = doDelete(vo);
	                break;
	                //保存修改后的计划表
	            case CodeConstant.SMSB_UPDATEACTION:
	                result = doSaveModify(vo);
	                break;
	                //保存新增加的计划表
	            case CodeConstant.SMSB_SAVEACTION:
	                result = doSaveAdd(vo);
	                break;
	                //获取未执行的任务
	            case CodeConstant.SMSB_WZXTASKACTION:  
	                result = getWzxTask(vo);
	                break;
	            case CodeConstant.SMSB_UPDATESTATE:  
	                result = updateState(vo);
	                break;
	                //获取打印数据
	            case CodeConstant.SMSB_PRINTDATA:  
	                result = getPrintData(vo);
	                break;
	            default:
	                throw new ApplicationException(
	                    "ActionType有错误，processor中找不到相应的方法.");
	        }
	        return result;

	}
	
	
	 
	  /**
	   * 查询页面
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object doQuery(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】查询时间计划任务");
		 PKTaskForm pktaskform = (PKTaskForm) vo.getData();
		 //月度
		 String yd = pktaskform.getYd();
		 //年度
		 String nd = pktaskform.getNd();
		 String zxsj2 = nd ;
		 //判断月度是某一个月份还是全部月份
		 if(yd.equals("00")){
			
		 }else{
			zxsj2 = zxsj2+yd;
		 }
		 
		 //任务类型
		 String rwlx = pktaskform.getCxrwlx();
		 //查询语句
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE zxsj like '"+zxsj2+"%' and ");
		
		 //判断任务类型
		 if(rwlx.equals("00")){
			 buffer.append(" 1=1");
		 }else{
			 buffer.append(" rwlx='"+rwlx+"'");
		 }
		 buffer.append(" ORDER BY ZXSJ");
		 
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
			//存放结果列表
			List pkTaskList = new ArrayList();
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
		
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				String zxsj = rs.getString("zxsj");
				//年度
				String nd2 = zxsj.substring(0,4);
				//月度
				String yd2 = zxsj.substring(4,6);
				//天
				String ri = zxsj.substring(6,8);
				//小时
				String xs2 = zxsj.substring(8,10);
				//分钟
				String fz2 = zxsj.substring(10);
				//执行时间
				model.setZxsj(zxsj);
				//执行日期
				model.setZxrq(nd2+"-"+yd2+"-"+ri);
				//执行时间
				model.setSj(xs2+":"+fz2);
				
				//判断任务类型名称
				if(rs.getString("rwlx").trim().equals("01")){
					model.setRwlxmc("生成待扣信息时间");
				}else if(rs.getString("rwlx").trim().equals("02")){
					model.setRwlxmc("发送扣款信息时间");
				}else{
					model.setRwlxmc("全部");
				}
			
				//执行状态
				model.setZxzt(rs.getString("zxzt"));
				//执行结果
				model.setZxjg(rs.getString("zxjg"));
				//任务类型
				model.setRwlx(rs.getString("rwlx"));
				//创建人
				model.setCjr(rs.getString("cjr"));
				//创建日期
				model.setCjrq(rs.getDate("cjrq"));
				//录入人
				model.setLrr(rs.getString("lrr"));
				//录入日期
				model.setLrrq(rs.getDate("lrrq"));
				//序号
				model.setXh(rs.getString("xh"));
				
				pkTaskList.add(model);
				
			}
//			if(pkTaskList.size()<=0){
//				 throw new ApplicationException("未查询到符合条件的数据！");
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		return pkTaskList;
	 }

	 /**
	   * 保存修改后的数据
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object doSaveModify(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】保存修改后的时间计划任务");
		 //返回值
		 String rows="";
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 UserData userData = vo.getUserData();
		//录入人id
		 String lrr = userData.getYhid(); 
		 //录入日期
		// String lrrq = SfTimeUtil.getNowTimestamp().toString().substring(0, 10);
		 //执行日期
		 String zxrq = pkTaskForm.getZxrq();
		 
		 if("".equals(zxrq) || zxrq == null){
			 throw new ApplicationException("日期不能为空！");
		 }
		 //执行时间
		 String sj = pkTaskForm.getZxsj();
		 //年度
		 String nd = zxrq.substring(0,4);
		 //月份
		 String yd = zxrq.substring(5,7);
		 //日
		 String ri = zxrq.substring(8);
		 //小时
		 String xs = sj.substring(0,2);
		 //分钟
		 String fz = sj.substring(3);
		 //执行时间
		 String zxsj = nd+yd+ri+xs+fz;
		 //任务类型
		 String rwlx = pkTaskForm.getZxrwlx();
		 //序号
		 String xh = pkTaskForm.getXh();
		 
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //查询语句
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB  SET ");
		 buffer.append(" zxsj = '"+zxsj);
		 buffer.append("',rwlx = '"+rwlx);
		 buffer.append("',lrr = '"+lrr);
		 buffer.append("',lrrq = SYSDATE");
		 buffer.append(" WHERE xh = '"+xh+"'");
		 
		 try {
			ps = conn.prepareStatement(buffer.toString());
			//受影响的行数
			int row = ps.executeUpdate();
			if(row>0){
				rows="1";
			}else{
				rows="0";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		
		 return rows;
	 }
	 
	 
	 /**
	   * 删除选中的数据
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object doDelete(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】删除时间计划任务");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 String xh = pkTaskForm.getXh();
		 String zxrq = pkTaskForm.getZxrq();
		 //存放结果对象列表
		 List list = null;
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("DELETE FROM SBDB.SB_JL_PLKKSJJHB WHERE XH = '"+xh+"'");
		 try {
			 
			ps = conn.prepareStatement(buffer.toString());
			ps.executeUpdate();
			//删除后的本年度
			list = toQuery(conn,zxrq);
			
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
	
		 return list;
	 }
	 
	 
	 /**
	   * 保存新增加的数据
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object doSaveAdd(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】保存新增加的时间计划任务");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 //执行日期
		 String zxrq = pkTaskForm.getZxrq();
		 if("".equals(zxrq) || zxrq == null){
			 throw new ApplicationException("日期不能为空！");
		 }
		 //执行时间
		 String sj = pkTaskForm.getZxsj();
		 //年度
		 String nd = zxrq.substring(0,4);
		 //月份
		 String yd = zxrq.substring(5,7);
		 //日
		 String ri = zxrq.substring(8);
		 //小时
		 String xs = sj.substring(0,2);
		 //分钟
		 String fz = sj.substring(3);
		 //执行时间
		 String zxsj = nd+yd+ri+xs+fz;
		 
		 //任务类型
		 String rwlx = pkTaskForm.getZxrwlx();
		 UserData userData = vo.getUserData();
		 //录入人id
		 String lrr = userData.getYhid();
		 
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("INSERT INTO SBDB.SB_JL_PLKKSJJHB(XH,ZXSJ,ZXZT,ZXJG,RWLX,CJR,CJRQ,LRR,LRRQ) VALUES(sbdb.seq_sb_lwpk.nextval,'");
		 buffer.append(zxsj+"','00','未执行','"+rwlx+"','"+lrr+"',SYSDATE,'"+lrr+"',SYSDATE)");
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		 return null;
	 }
	 
	 /**
	   * 轮询查找要执行的任务
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private List getWzxTask(VOPackage vo) throws BaseException {
		 System.out.println("获取未执行任务开始时间"+new Date());
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 List list = new ArrayList();
			
		//查询改时间段是否有要执行的任务
		 String sql = "select a.xh, a.rwlx, a.zxsj, a.zxzt from sbdb.sb_jl_plkksjjhb a where zxzt = '00' and sysdate >= to_date(a.zxsj,'yyyymmddHH24mi') and sysdate < (to_date(a.zxsj,'yyyymmddHH24mi')+30/60/24)";
		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				model.setXh(rs.getString("xh"));
				model.setRwlx(rs.getString("rwlx"));
				model.setZxsj(rs.getString("zxsj"));
				model.setZxzt(rs.getString("zxzt"));
				list.add(model);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
            SfDBResource.freeConnection(conn);
        }
		 System.out.println("获取未执行任务结束时间时间"+new Date());
		 return list;
	 }
	 
	 /**
	   * 修改执行状态
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object updateState(VOPackage vo) throws BaseException {
		 
		 System.out.println("修改时间计划表状态开始时间"+new Date());
		 //修改的行数
		 int rows = 0;
		 //获取时间计划表对象
		 PKTaskModel pkTaskModel = (PKTaskModel) vo.getData();
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //构造sql语句
		 StringBuffer buffer = new StringBuffer();
		 //判断是开始执行还是结束执行
		 if(pkTaskModel.getZxzt().equals("01")){
			 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB SET ZXZT=?,ZXJG=?,SJZXKSSJ=SYSDATE WHERE XH = ? AND ZXZT = '00'");
		 }else{
			 buffer.append("UPDATE SBDB.SB_JL_PLKKSJJHB SET ZXZT=?,ZXJG=?,SJZXJSSJ=SYSDATE WHERE XH = ? AND ZXZT = '01'");
		 }
		try {
			ps = conn.prepareStatement(buffer.toString());
			//执行状态
			ps.setString(1, pkTaskModel.getZxzt());
			//执行结果
			ps.setString(2, pkTaskModel.getZxjg());
			//序号
			ps.setString(3, pkTaskModel.getXh());
			//执行
			rows = ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		 System.out.println(new Date()+"修改数据库中数据的行数："+rows);
		System.out.println("修改时间计划表状态结束时间"+new Date());
		 return rows+"";
	 }
	 
	 /**
	   * 打印时间计划表
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object getPrintData(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】打印时间计划表");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 PKTaskForm pkTaskForm = (PKTaskForm) vo.getData();
		 //获取年度
		 String nd = pkTaskForm.getNd();
		 String yd = pkTaskForm.getYd();
		 //查询sql
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '");
		 //判断月度是某一个月份还是全部月份
		 if(yd.equals("00")){
			buffer.append(nd+"%' and ");
		 }else{
			 buffer.append(nd+yd+"%' and ");
		 }
		 
		 //任务类型
		 String rwlx = pkTaskForm.getCxrwlx();
		 //判断任务类型
		 if(rwlx.equals("00")){
			 buffer.append(" 1=1");
		 }else{
			 buffer.append(" rwlx='"+rwlx+"'");
		 }
		 
		 List list = new ArrayList();
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				//任务类型
				model.setRwlx(rs.getString("rwlx"));
				if(model.getRwlx().trim().equals("01")){
					model.setRwlxmc("生成待扣信息时间");
				}else{
					model.setRwlxmc("发送扣款信息时间");
				}

				//执行时间
				String zxsj = rs.getString("zxsj");
				String nd2 = zxsj.substring(0,4);
				String yd2 = zxsj.substring(4,6);
				String ri2 = zxsj.substring(6,8);
				//日期
				String zxrq = nd2+"-"+yd2+"-"+ri2;
				
				String xs2 = zxsj.substring(8,10);
				String fz2 = zxsj.substring(10,12);
				//时间
				String sj = xs2+":"+fz2;
				//执行时间
				model.setZxsj(zxsj);
				//日期
				model.setZxrq(zxrq);
				//时间
				model.setSj(sj);
				
				list.add(model);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
           SfDBResource.freeConnection(conn);
       }
	
		 return list;
	 }

	 /**
	   * 初始化时间计划表
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	   * @throws BaseException
	   */
	 private Object doInit(VOPackage vo) throws BaseException {
		 System.out.println("【PKTaskProcessor】初始化时间计划表");
		 Connection conn ;
		 PreparedStatement ps;
		 conn = SfDBResource.getConnection();
		 //获取初始化年度参数
		 PKTaskForm yForm = (PKTaskForm) vo.getData();
		 //获取初始化年度
		 String nd = yForm.getNd();
		 //判断是否已经初始化
		 boolean bl = isInit(conn,nd);
		 if(!bl){
			 return "fasle";
		 }
		 //获取用户信息
		 UserData userdata = vo.getUserData();
		 //用户id
		 String lrr = userdata.getYhid();
		 
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("INSERT INTO SBDB.SB_JL_PLKKSJJHB(XH,ZXSJ,ZXZT,ZXJG,RWLX,CJR,CJRQ,LRR,LRRQ)");
		 buffer.append("VALUES(sbdb.seq_sb_lwpk.nextval,?,?,'未执行',?,?,SYSDATE,?,SYSDATE)");
		 try {
			 ps = conn.prepareStatement(buffer.toString());
			 int k=1;
			//2013081618
			 if(nd.equals("2013")){
				 k=9;
			 }
			 for(int i=k;i<=12;i++){
				 
				 //如果为一月份
				 if( i==1){
					 //生成一月份的时间计划
					 for(int j=0;j<4;j++){
						
							 int day = j*5;
							 //执行时间sbdb.seq_sb_lwpk
							 ps.setString(1, j==0?nd+"0"+i+"020100":day<10?nd+"0"+i+"0"+day+"0600":nd+"0"+i+day+"0600");
							 //执行状态
							 ps.setString(2, "00");
							 //任务类型
							 ps.setString(3, j==0?"01":"02");
							 //创建人
							 ps.setString(4, lrr);
							 //录入人
							 ps.setString(5, lrr);
							 
							 ps.addBatch();
						 }
					
				 }else{
					 //除一月的其他月份
					 
					 //月份小于10
					 if(i<10){
						 for(int j=0;j<4;j++){
								
							 int day = j*5;
							 //执行时间
							 ps.setString(1, j==0?nd+"0"+i+"010100":day<10?nd+"0"+i+"0"+day+"0600":nd+"0"+i+day+"0600");
							 ps.setString(2, "00");
							 //执行状态
							 ps.setString(3, j==0?"01":"02");
							 //创建人
							 ps.setString(4, lrr);
							 //录入人
							 ps.setString(5, lrr);
							 
							 ps.addBatch();
						 }
					 }else{
						 //月份大于10
						 for(int j=0;j<4;j++){
								
							 int day = j*5;
							 //执行时间
							 ps.setString(1, j==0?nd+i+"010100":day<10?nd+i+"0"+day+"0600":nd+i+day+"0600");
							 //执行状态
							 ps.setString(2, "00");
							 //任务类型
							 ps.setString(3, j==0?"01":"02");
							 //创建人
							 ps.setString(4, lrr);
							 //录入人
							 ps.setString(5, lrr);
								 
							 ps.addBatch();
							 }
						 }
					 }
					 
				 }//for循环结束
			 //批量执行插入数据库
		ps.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
	           SfDBResource.freeConnection(conn);
	       }
		 
		 
		return "true";
	 }
	 
	 /**
	   * 删除后再次查询
	   * @return object
	 * @throws Exception 
	   * @throws BaseException
	   */
	 private List toQuery(Connection conn,String zxrq) throws BaseException{
		 System.out.println("【PKTaskProcessor】查询时间计划表");
		 PreparedStatement ps;
		 String nd  = zxrq.substring(0,4);
		 StringBuffer buffer = new StringBuffer();
		 buffer.append("SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '"+nd+"%'");
		 List list = new ArrayList();
		 
		 System.out.println(buffer.toString()+"==========");
		 
		 try {
			ps = conn.prepareStatement(buffer.toString());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				PKTaskModel model = new PKTaskModel();
				String zxsj = rs.getString("zxsj");
				//年度
				String nd2 = zxsj.substring(0,4);
				//月度
				String yd2 = zxsj.substring(4,6);
				//天
				String ri = zxsj.substring(6,8);
				//小时
				String xs2 = zxsj.substring(8,10);
				//分钟
				String fz2 = zxsj.substring(10);
				//执行时间
				model.setZxsj(rs.getString("zxsj"));
				//执行日期
				model.setZxrq(nd2+"-"+yd2+"-"+ri);
				//执行时间
				model.setSj(xs2+":"+fz2);
				//执行状态
				model.setZxzt(rs.getString("zxzt"));
				//执行结果
				model.setZxjg(rs.getString("zxjg"));
				//任务类型
				model.setRwlx(rs.getString("rwlx"));
				//创建人
				model.setCjr(rs.getString("cjr"));
				//创建日期
				model.setCjrq(rs.getDate("cjrq"));
				//录入人
				model.setLrr(rs.getString("lrr"));
				//录入日期
				model.setLrrq(rs.getDate("lrrq"));
				//序号
				model.setXh(rs.getString("xh"));
				
				list.add(model);
			}
			ps.close();
			rs.close();
			 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		
		return list; 
	 }
	 
	 /**
	   * 判断初始化是否已存在
	   * @param vo 数据集对象（包括Form和UserData对象）
	   * @return object
	 * @throws Exception 
	   * @throws BaseException
	   */
	 private boolean isInit(Connection conn,String nd) throws BaseException{
		 System.out.println("【PKTaskProcessor】判断是否初始化");
		 PreparedStatement ps;
		 String sql="SELECT * FROM SBDB.SB_JL_PLKKSJJHB WHERE ZXSJ LIKE '"+nd+"%'";
		 System.out.println(sql);
		 boolean bl = true;
		 //当前时间
		 Date datetime = SfTimeUtil.getNowTimestamp();
		 //当前年
		 int year = datetime.getYear();
		 //需要初始化的年份
		 int y = Integer.parseInt(nd);
		 //如果需要初始化的年份小于但前年，不能初始化
		 if(y<year){
			 return false;
		 }
		 try {
			ps = conn.prepareStatement(sql);
			 ResultSet rs = ps.executeQuery();
			 if(rs.next()){
				 bl = false;
			 }	 
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
		      SfDBResource.freeConnection(conn);
	    }
		
		return bl; 
	 }
	
	 
}

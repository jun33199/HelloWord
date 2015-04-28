package com.ttsoft.bjtax.smsb.jccswh.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttsoft.bjtax.dj.model.dm.SZSM;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jccswh.vo.JmxzVo;
import com.ttsoft.bjtax.smsb.jccswh.vo.ZjjmsdmVo;
import com.ttsoft.bjtax.smsb.jccswh.web.ZjjmsdmwhForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * 总局减免税代码维护 该功能是对总局减免税项目进行维护
 * 
 * @author tangchangfu 2014-04-22
 * 
 */
public class ZjjmsdmwhProcessor implements Processor {

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
		// 回传对象
		Object result = null;
		// 判断VO是否为空
		if (vo == null) {
			throw new NullPointerException();
		}
		// 根据Action的值调用不同的process方法
		switch (vo.getAction()) {
				case CodeConstant.SMSB_INIT:
					result = doInit(vo);
					break;
				case CodeConstant.SMSB_QUERYACTION: // 查询
					result = doGetAllData(vo);
					break;
			    case CodeConstant.QUERYONE://获得单条信息用于修改操作
			    	  result = doGetOne(vo);
			    	  break;
				case CodeConstant.SMSB_SAVEACTION: // 保存
					doSave(vo);
					break;
				case CodeConstant.SMSB_UPDATEACTION: // 更新
					doUpdate(vo);
					break;
				default:
					throw new UnsupportedOperationException(
							"Method process() not yet implemented.");
		}
		return result;
	}

	/**
	 * 页面初始化
	 * 
	 * @param vo
	 * @return
	 */
	private Object doInit(VOPackage vo) throws BaseException {
		Map resMap = new HashMap();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
			
			//
			ArrayList szList = new ArrayList();
			ArrayList jmxzdlList = new ArrayList();
			ArrayList jmxzxlList = new ArrayList();
			
			//==== 1.初始化税种
			String querySql = "select szsmdm,  t.szsmdm||'-'||t.szsmmc  ms from dmdb.sb_dm_szsm t where t.ccbs='0' order by t.szsmdm";
			ps = conn.prepareStatement(querySql);
			ResultSet rs = ps.executeQuery();

			SZSM szFirst = new SZSM();
			szFirst.setSzsmdm("");
			szFirst.setSzsmmc("");
			szList.add(szFirst);

			while (rs.next()) {
				SZSM sz = new SZSM();
				sz.setSzsmdm(rs.getString("szsmdm"));
				sz.setSzsmmc(rs.getString("ms"));
				szList.add(sz);
			}
			
			//==== 2.初始化减免性质大类
			rs.close();
			ps.close();
			querySql = "select t.jmxzdm,t.jmxzmc ms from DMDB.KJ_DM_JMXZ t where t.xsbs='1' and length(t.jmxzdm)=4";
			ps = conn.prepareStatement(querySql);
			rs = ps.executeQuery();
			
			JmxzVo dlvo = new JmxzVo();
			dlvo.setJmxzdm("");
			dlvo.setJmxzmc("");
			jmxzdlList.add(dlvo);
			
			while (rs.next()) {
				JmxzVo dlvo1 = new JmxzVo();
				dlvo1.setJmxzdm(rs.getString("jmxzdm"));
				dlvo1.setJmxzmc(rs.getString("ms"));
				jmxzdlList.add(dlvo1);
			}
			

			//==== 3.初始化减免性质小类
			rs.close();
			ps.close();
			querySql = "select t.jmxzdm,t.jmxzmc ms from DMDB.KJ_DM_JMXZ t where t.xsbs='1' and length(t.jmxzdm)!=4";
			ps = conn.prepareStatement(querySql);
			rs = ps.executeQuery();
			
			JmxzVo xlvo = new JmxzVo();
			xlvo.setJmxzdm("");
			xlvo.setJmxzmc("");
			jmxzxlList.add(xlvo);
			
			while (rs.next()) {
				JmxzVo xlvo1 = new JmxzVo();
				xlvo1.setJmxzdm(rs.getString("jmxzdm"));
				xlvo1.setJmxzmc(rs.getString("ms"));
				jmxzxlList.add(xlvo1);
			}
			
			//==== 4.把查询结果存入Map
			resMap.put(CodeConstant.SMSB_MAP_KEY_SZ, szList);
			resMap.put(CodeConstant.SMSB_MAP_KEY_JMXZDL, jmxzdlList);
			resMap.put(CodeConstant.SMSB_MAP_KEY_JMXZXL, jmxzxlList);
			
			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
			// 如果是特定抛出的异常
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("页面初始化失败，请退出重试！");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return resMap;
	}
	
	
	/**
	 * 执行查询，获得分页数据
	 * @param vo
	 * @return
	 */
	private Object doGetAllData(VOPackage vo)throws BaseException{
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
		try{
			//依据查询条件获得查询结果
			ArrayList  allDataList = this.doQuery(form);
			// 获取总页数数据
			form.setTotalpage(this.getPageTotalCount(allDataList.size()));
			//执行分页操作
			// 分页当前显示的数据集
			ArrayList showDataList = new ArrayList();
			// 整理查询结果
			showDataList = this.getPageDataList(allDataList, form);
			
			form.setQueryList_onePage(showDataList);
			
			System.out.println("显示结果长度+++++++++++++++++++showDataList###"+showDataList.size());
			
		}catch(Exception e){
			e.printStackTrace();
			// 如果是特定抛出的异常
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("执行查询失败，请退出重试！");
		}
		return form;
	}
	
	/**
	 * 执行查询，获得某条指定信息
	 * @param vo
	 * @return
	 */
	private Object doGetOne(VOPackage vo)throws BaseException{
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
		try{
			//依据查询条件获得查询结果
			ArrayList  allDataList = this.doQuery(form);
			ZjjmsdmVo resVo = (ZjjmsdmVo)allDataList.get(0);
			//form.setVo(resVo);
			form.setAllNewAddInfo(resVo.getReShowStr());
		}catch(Exception e){
			e.printStackTrace();
			// 如果是特定抛出的异常
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取指定减免信息失败，请退出重试！");
		}
		return form;
	}

	/**
	 * 根据前台输入条件，查询相关总局减免税代码维护信息
	 * 
	 * @param vo
	 * @return
	 */
	private ArrayList doQuery(ZjjmsdmwhForm form)throws BaseException {
		Connection conn = null;// 建立数据库连接
		ArrayList resList = new ArrayList();
		try {
			conn = SfDBResource.getConnection();

			String querySQL = "select * from dmdb.sb_dm_zjqyjmslx where 1=1 "+ this.getQueryConditions(form)+" order by szdm,jmslxdldm,jmslxxldm,jmslxdm";
			System.out.println("查询SQL+++++++++++++++++++querySQL###"+querySQL);
			PreparedStatement ps = conn.prepareStatement(querySQL);
			ResultSet rs = ps.executeQuery();// 执行查询
			int indx =1;
			while (rs.next()) {
				ZjjmsdmVo dmVo = new ZjjmsdmVo();
				dmVo.setIndexId(indx++);
				dmVo.setJmslxdm(replaceEnterKey2Blank(rs.getString("jmslxdm")));
				dmVo.setWh(replaceEnterKey2Blank(rs.getString("wh")));
				dmVo.setJmslxmc(replaceEnterKey2Blank(rs.getString("jmslxmc")));
				dmVo.setSz(rs.getString("sz"));
				dmVo.setZxbz(rs.getString("zxbz"));
				dmVo.setLrr(rs.getString("lrr"));
				dmVo.setLrrq(rs.getDate("lrrq"));
				dmVo.setJmszcqsnd(replaceEnterKey2Blank(rs.getString("jmszcqsnd")));
				dmVo.setSzdm(rs.getString("szdm"));
				dmVo.setJmslxdldm(rs.getString("JMSLXDLDM"));//减免税类型大类代码
				dmVo.setJmslxxldm(rs.getString("JMSLXXLDM"));//减免税类型小类代码
				dmVo.setYxbs(rs.getString("YXBS"));////有效标识（0-有效；9-无效）
				dmVo.setBz(replaceEnterKey2Blank(rs.getString("BZ")));//备注
				dmVo.setLsbh(rs.getString("LSBH"));//历史编号（初始值为0，每次变更自动加一）

				//获得描述
				this.getDescAll(dmVo,conn);
				//
				//获得拼接信息
				this.getMx2ReShow(dmVo);
				
				resList.add(dmVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// 如果是特定抛出的异常
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("执行查询失败，请退出重试！");

		} finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		System.out.println("查询结果长度+++++++++++++++++++resList###"+resList.size());
		return resList;
	}

	/**
	 * 获得前台查询条件
	 * 
	 * @param form
	 * @return
	 */
	private String getQueryConditions(ZjjmsdmwhForm form) throws BaseException{
		StringBuffer whereConditions = new StringBuffer();
		try {
			//减免税类型代码
			if(form.getQuery_jmslxdm()!= null && !"".equals(form.getQuery_jmslxdm())){
				whereConditions.append(" and jmslxdm='"+form.getQuery_jmslxdm()+"'");
			}
			//文号
			if(form.getQuery_wh() != null && !"".equals(form.getQuery_wh())){
				whereConditions.append(" and wh like '%"+form.getQuery_wh()+"%'");
				
			}
			//税种代码
			if(form.getQuery_szdm() != null && !"".equals(form.getQuery_szdm())){
				whereConditions.append(" and szdm='"+form.getQuery_szdm()+"'");
				
			}
			//减免税政策起始年度
			if(form.getQuery_jmszcqsnd() !=null && !"".equals(form.getQuery_jmszcqsnd())){
				whereConditions.append(" and jmszcqsnd='"+form.getQuery_jmszcqsnd()+"'");
			}
			
			//减免税类型大类代码
			if(form.getQuery_jmslxdldm() != null && !"".equals(form.getQuery_jmslxdldm())){
				whereConditions.append(" and jmslxdldm='"+form.getQuery_jmslxdldm()+"'");
			}
			//减免税类型小类代码
			if(form.getQuery_jmslxxldm() != null && !"".equals(form.getQuery_jmslxxldm())){
				whereConditions.append(" and jmslxxldm='"+form.getQuery_jmslxxldm()+"'");
			}
			
			
			//录入开始日期
			if(form.getQuery_lrrqKS() != null && !"".equals(form.getQuery_lrrqKS())){
				whereConditions.append(" and lrrq >=to_date('" + form.getQuery_lrrqKS() + " 00:00:00','yyyymmdd hh24:mi:ss') ");
			}
			
			
			//录入结束日期
			if(form.getQuery_lrrqJS() != null && !"".equals(form.getQuery_lrrqJS())){
				whereConditions.append(" and lrrq <=to_date('" + form.getQuery_lrrqJS() + " 23:59:59','yyyymmdd hh24:mi:ss')");
			}
			
			
			
			
			//有效标识（0-有效；9-无效）
			if(form.getQuery_yxbs()!= null && !"".equals(form.getQuery_yxbs())){
				//如果选择有效，则把为空的也一起查询出来
				if("0".equals(form.getQuery_yxbs())){
					whereConditions.append(" and (yxbs='"+form.getQuery_yxbs()+"' or yxbs is null)");
				}else{
					whereConditions.append(" and yxbs='"+form.getQuery_yxbs()+"'");
				}
			}
			
			//如果执行修改
			if(form.getActionType() != null && "ShowOne".equals(form.getActionType())){
				if(form.getModifyKey_jmslxdm()!= null && !"".equals(form.getModifyKey_jmslxdm())){
					whereConditions.append(" and jmslxdm='"+form.getModifyKey_jmslxdm()+"'");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			// 如果是特定抛出的异常
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("执行失败，组合查询条件失败，请退出重试！");
		}

		return whereConditions.toString();
	}
	
	/**
	 * 获得明细信息（回显  修改 用）
	 * @param zbVo
	 * @param mxrs
	 */
	private void getMx2ReShow(ZjjmsdmVo resVo)throws BaseException{
		StringBuffer reShowBF = new StringBuffer();//回显明细（如保存成功回显 或者修改信息）
		try{
			if(resVo != null){
				reShowBF.append(replaceNull2Blank(resVo.getSzdm()));//税种
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxdldm()));//减免税类型大类代码
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxxldm()));//减免税类型小类代码
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxdm()));//减免税类型代码
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxmc()));//减免税类型名称
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmszcqsnd()));//减免开始年度
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getWh()));//文号
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getBz()));//备注
				reShowBF.append("^");
			}
			//System.out.println("打印信息(1)###############"+reShowBF.toString());
			if(reShowBF.length() >0){
				reShowBF = reShowBF.replace(reShowBF.length()-1, reShowBF.length(), "");
			}
			//System.out.println("打印信息(2)###############"+reShowBF.toString());
			resVo.setReShowStr(reShowBF.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("查询出错，获得完税信息明细出错，请重试！");
		}
	}
	
	
	/**
	 * 保存录入信息
	 * @param vo
	 */
	private void doSave(VOPackage vo)throws BaseException{
		UserData ud = vo.getUserData();
		Connection conn = null;// 建立数据库连接
		try{
			conn = SfDBResource.getConnection();
			
			String allNewAddInfo="";
			ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
			allNewAddInfo = form.getAllNewAddInfo();
			if(allNewAddInfo != null && !"".equals(allNewAddInfo)){
				List newAddInfoList = this.constructMx2SaveOrUpdate(ud, allNewAddInfo, conn);
				String existsJmslxdms = this.getAllExistsJmslxdms(newAddInfoList, conn);
				if(existsJmslxdms != null && !"".equals(existsJmslxdms)){
					throw new ApplicationException("新增失败，含已存在减免税类型代码："+existsJmslxdms);
				}
				
				
				if(newAddInfoList != null && newAddInfoList.size()>0){
					String saveSQL = " insert into dmdb.sb_dm_zjqyjmslx " +
							" (JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR,    LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH) values " +
							" (?      , ? , ?      , ? , '0' , ?  , sysdate, ?        , ?   , ?        , ?        , ?   , ? , ?   )";
					PreparedStatement ps = conn.prepareStatement(saveSQL);
					
					for(int index =0; index < newAddInfoList.size(); index ++){
						ZjjmsdmVo temp = (ZjjmsdmVo)newAddInfoList.get(index);
						int tempIndex =1;
						//减免税类型代码
						ps.setString(tempIndex++, temp.getJmslxdm());
						//文号
						ps.setString(tempIndex++, temp.getWh());
						//减免税类型名称
						ps.setString(tempIndex++, temp.getJmslxmc());
						//税种
						ps.setString(tempIndex++, temp.getSz());
						//注销标识
						//录入人
						ps.setString(tempIndex++, temp.getLrr());
						//录入日期
						//减免税政策起始年度
						ps.setString(tempIndex++, temp.getJmszcqsnd());
						//税种代码
						ps.setString(tempIndex++, temp.getSzdm());
						//减免税类型大类代码
						ps.setString(tempIndex++, temp.getJmslxdldm());
						//减免税类型小类代码
						ps.setString(tempIndex++, temp.getJmslxxldm());
						//有效标识（0-有效；9-无效）
						ps.setString(tempIndex++, "0");
						//备注
						ps.setString(tempIndex++, temp.getBz());
						//历史编号（初始值为1，每次变更自动加一）
						ps.setString(tempIndex++, "1");
						
						ps.addBatch();
					}
					ps.executeBatch();
				}else{
					throw new ApplicationException("保存录入信息失败,未获得前台提交构造信息，请重试！");
				}
			}else{
				throw new ApplicationException("保存录入信息失败,未获得提交信息，请重试！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("保存录入信息失败，请重试！");
			
		}finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
	}
	
	/**
	 * 新增时判断Jmslxdm是否存在，如果存在，则不允许新增
	 * @param newAddInfoList
	 * @return
	 */
	private String getAllExistsJmslxdms(List newAddInfoList,Connection conn)throws BaseException{
		String jmslxdms =null;
		
		if(newAddInfoList == null || newAddInfoList.size() == 0 || conn == null){
			throw new ApplicationException("验证新增信息失败，请重试！");
		}
		try{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String querySQL="SELECT 1 FROM DMDB.SB_DM_ZJQYJMSLX WHERE JMSLXDM=? ";
			ps = conn.prepareStatement(querySQL);
			for(int index =0; index < newAddInfoList.size(); index ++){
				ZjjmsdmVo temp = (ZjjmsdmVo)newAddInfoList.get(index);
				if(temp != null){
					ps.setString(1, temp.getJmslxdm());
					rs = ps.executeQuery();
					if(rs.next()){
						if(jmslxdms == null){
							jmslxdms = temp.getJmslxdm();
						}else{
							jmslxdms +=","+ temp.getJmslxdm();
						}
					}
				}
			}
			
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("验证新增信息失败，请重试！");
		}
		return jmslxdms;
	}
	
	/**
	 * 执行更新
	 * @param vo
	 * @throws BaseException
	 */
	private void doUpdate(VOPackage vo)throws BaseException{
		Connection conn = null;// 建立数据库连接
		try{
			//初始化数据库连接
			conn = SfDBResource.getConnection();
			//获得前台相关信息
			UserData ud = vo.getUserData();
			ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
			String updateType = form.getUpdateType();
			//拼接前台修改信息
			String allNewAddInfo = form.getAllNewAddInfo();
			ArrayList updateList = new ArrayList();
			ZjjmsdmVo temp=null;
			
			if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
				updateList = this.constructMx2SaveOrUpdate(ud, allNewAddInfo, conn);
				
				if(updateList.size() >0){
					temp = (ZjjmsdmVo)updateList.get(0);
				}
			}
			
			//修改前保存历史
			String modifyKey_jmslxdm = form.getModifyKey_jmslxdm();
			this.save2His(ud, modifyKey_jmslxdm, conn);
			//执行全部更新
			System.out.println("updateType ===="+updateType);
			System.out.println("temp===="+temp);
			System.out.println("++++++++++++++(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)):"+(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)));
			System.out.println();
			System.out.println();
			System.out.println();
			
			
			
			
			if(updateType != null && !"".equals(updateType) &&
					(
						(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType))||
						(temp == null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType))
					)
				)
			{
				StringBuffer updateSQL = new StringBuffer();
				updateSQL.append("UPDATE DMDB.SB_DM_ZJQYJMSLX SET " );
				
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
					//"JMSLXDM=? "+//减免税类型代码
					updateSQL.append("WH=?,  " );//文号
					updateSQL.append("JMSLXMC=?, " );//减免税类型名称
					updateSQL.append("SZ=?, " );//税种
					//"ZXBZ=? "+//注销标识
					updateSQL.append("JMSZCQSND=?, " );//减免税政策起始年度
					updateSQL.append("SZDM=?, " );//税种代码
					updateSQL.append("JMSLXDLDM=?, " );//减免税类型大类代码
					updateSQL.append("JMSLXXLDM=?, " );//减免税类型小类代码
					updateSQL.append("BZ=?, " );//备注
				}
				//只更新有效标识
				/**
				 * 更新标识： 如果为空，null 则默认为有效”0“
				 * 如果现在为有效，则变成无效；
				 * 如果为无效，则变为有效
				 */
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
					updateSQL.append(" YXBS=decode(nvl(yxbs,0),'0','9','9','0'), " );//有效标识（0-有效；9-无效）
				}
				updateSQL.append("LRR=?, " );//录入人
				updateSQL.append("LRRQ=sysdate, " );//录入日期
				updateSQL.append(" LSBH=LSBH+1 " );
				updateSQL.append(" where JMSLXDM=? " );//历史编号（初始值为0，每次变更自动加一）
				
				System.out.println("更新SQL::::++++++++"+updateSQL.toString());
				PreparedStatement ps = conn.prepareStatement(updateSQL.toString());
				int index=1;
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
					//ps.setString(index++, temp.getJmslxdm());//减免税类型代码
					ps.setString(index++, temp.getWh());//文号
					ps.setString(index++, temp.getJmslxmc());//减免税类型名称
					ps.setString(index++, temp.getSz());//税种
					//ps.setString(index++, temp.getZxbz());//注销标识
					//ps.setString(index++, x);//录入日期
					ps.setString(index++, temp.getJmszcqsnd());//减免税政策起始年度
					ps.setString(index++, temp.getSzdm());//税种代码
					ps.setString(index++, temp.getJmslxdldm());//减免税类型大类代码
					ps.setString(index++, temp.getJmslxxldm());//减免税类型小类代码
					ps.setString(index++, temp.getBz());//备注
					//ps.setString(index++, x);//历史编号（初始值为0，每次变更自动加一）
				}
				
				ps.setString(index++, ud.getYhmc());//录入人
				//更新主键
				ps.setString(index++, modifyKey_jmslxdm);
				//执行更新
				ps.execute();
			}else{
				throw new ApplicationException("更新录入信息失败,无法获取前台更新数据，请重试！");
			}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("更新录入信息失败，请重试！");
		}finally {
			// 释放数据库连接
			SfDBResource.freeConnection(conn);
		}
		
	}
	
	/**
	 * 构造提交信息
	 * @param ud
	 * @param allNewAddInfo
	 * @return
	 * @throws BaseException
	 */
	private ArrayList constructMx2SaveOrUpdate(UserData ud, String allNewAddInfo,Connection conn)throws BaseException {
		ArrayList SaveInfoList = new ArrayList();
		try{
		String regEx ="[\\^]";//定义多组用^分开
		String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

		for (int index = 0; index < splitAllNewAddInfo.length; index++) {

			String[] oneRowInfoArr = new String[] {};
			if (splitAllNewAddInfo[index] != null) {
				System.out.println("每行手工录入信息################################"+splitAllNewAddInfo[index]);

				oneRowInfoArr = splitAllNewAddInfo[index].split("~");
				if (oneRowInfoArr.length != 0) {
					ZjjmsdmVo temp = new ZjjmsdmVo();
					//税种
					temp.setSzdm(oneRowInfoArr[0]);
					//减免性质大类
					temp.setJmslxdldm(oneRowInfoArr[1]);
					//减免性质小类
					temp.setJmslxxldm(oneRowInfoArr[2]);
					//减免税类型代码
					temp.setJmslxdm(replaceEnterKey2Blank(oneRowInfoArr[3]));
					//减免税类型名称
					temp.setJmslxmc(replaceEnterKey2Blank(oneRowInfoArr[4]));
					//起始年度
					temp.setJmszcqsnd(replaceEnterKey2Blank(oneRowInfoArr[5]));
					//文号
					temp.setWh(replaceEnterKey2Blank(oneRowInfoArr[6]));
					//备注
					if(oneRowInfoArr.length ==8){
						temp.setBz(replaceEnterKey2Blank(oneRowInfoArr[7]));
					}
					temp.setLrr(ud.getYhmc());

					
					//获得各类描述
					this.getDescAll(temp, conn);
					
					//检查是否已经存在
					
					SaveInfoList.add(temp);
					
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取保存信息失败，请重试！");
		}
		return SaveInfoList;
	}
	
	/**
	 * 获得各类描述
	 * @param temp
	 */
	private void getDescAll(ZjjmsdmVo temp,Connection conn)throws BaseException{
		if(conn == null || temp == null){
			throw new ApplicationException("获取各类描述信息失败，请重试！");
		}
		
		try{
			//加载税种名称
			String qySzmcSQL ="select szsmmc from dmdb.sb_dm_szsm where szsmdm=?";
			PreparedStatement ps = conn.prepareStatement(qySzmcSQL);
			ps.setString(1, temp.getSzdm());
			ResultSet rs = ps.executeQuery();// 执行查询
			while(rs.next()){
				temp.setSz(rs.getString("szsmmc"));
			}
			
			//加载减免性质（大类）代码名称
			rs.close();
			ps.close();
			String qyJmxzDLmcSQL ="select jmxzmc from DMDB.KJ_DM_JMXZ  where xsbs='1' and length(jmxzdm)=4 and jmxzdm=?";
			ps = conn.prepareStatement(qyJmxzDLmcSQL);
			ps.setString(1, temp.getJmslxdldm());
			rs = ps.executeQuery();// 执行查询
			while(rs.next()){
				temp.setJmslxdldmmc(rs.getString("jmxzmc"));
			}
			
			//加载减免性质（小类）代码名称
			rs.close();
			ps.close();
			String qyJmxzXLmcSQL ="select jmxzmc  from DMDB.KJ_DM_JMXZ  where xsbs='1' and length(jmxzdm)!=4 and jmxzdm=?";
			ps = conn.prepareStatement(qyJmxzXLmcSQL);
			ps.setString(1, temp.getJmslxxldm());
			rs = ps.executeQuery();// 执行查询
			while(rs.next()){
				temp.setJmslxxldmmc(rs.getString("jmxzmc"));
			}
			
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("获取各类描述信息失败，请重试！");
		}
		
	}
	
	/**
	 * 保存修改信息到历史
	 * @param jmslxdm
	 * @return
	 */
	private boolean save2His(UserData ud,String jmslxdm,Connection conn)throws BaseException{
		boolean isSucc = false;
		try{
			if(ud == null || jmslxdm == null || "".equals(jmslxdm) || conn == null){
				throw new ApplicationException("保存更新信息到历史表,初始化相关信息失败，请重试！");
			}
			String hisSQL="insert into dmdb.sb_dm_zjqyjmslx_his (JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR, LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH,bgr,bgrq) "+
                                " select JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR, LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH lsbh,?,sysdate "+
                                " from dmdb.sb_dm_zjqyjmslx where jmslxdm=? ";
			
			PreparedStatement ps = conn.prepareStatement(hisSQL);
			ps.setString(1, ud.yhmc);
			ps.setString(2, jmslxdm);
			ps.executeUpdate();// 执行查询
			
			//执行成功
			isSucc = true;
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			//如果是特定抛出的异常
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("保存更新信息到历史表失败，请重试！");
		}
		return isSucc;
	}
	
	
	/**
	 * 获取页数
	 * 
	 * @param rsCount
	 *            查询结果集build
	 * @return 页数
	 */
	private String getPageTotalCount(int rsCount) {
		// 句柄申明
		String countTotal = "0";
		// 开始业务
		int pageCount = 0;
		if ((rsCount % CodeConstant.JKS_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// 返回值
		return countTotal;
	}
	
	/**
	 * 获取当前分页数据集
	 * 
	 * @param tmpList
	 *            完整数据集
	 * @param pf
	 *            页面对象
	 * @return 当前分页数据集
	 */
	private ArrayList getPageDataList(List tmpList, ZjjmsdmwhForm pf) {
		// 1.申明句柄
		ArrayList dataList = new ArrayList();
		// 2.初始化参数值
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.开始业务
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.返回值
		return dataList;
	}
	
	/**
	 * 获取当前页开始index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 开始index
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.开始业务
		start = (iNextPage - 1) * CodeConstant.JKS_PG_LENGTH;
		// 99.返回值
		return start;
	}
	
	/**
	 * 获取当前页结束index
	 * 
	 * @param nextPage
	 *            下一页
	 * @param countTotal
	 *            总页数
	 * @return 结束index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.句柄申明
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.开始业务
		end = iNextPage * CodeConstant.JKS_PG_LENGTH;
		// 99.返回值
		return end;
	}
	
	/**
	 * 把null值转换成空串
	 * @param str
	 * @return
	 */
	private  String replaceNull2Blank(String str){
		if(str == null){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * 把文本换行替换为空
	 * @param str
	 * @return
	 */
	private String replaceEnterKey2Blank(String str){
        String dest = "";

        if (str!=null) {

            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
		
	}

}

package com.lscdz.util.dao;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import yangjian.frame.util.FrameException;
import yangjian.frame.util.ResourceManager;

public class CodeTableAccessExt {
	// 用于转换时间格式
	private static final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	/**
	 * 查询代码表并缓存
	 * @param sqlWhere
	 * @return
	 * @throws FrameException
	 */
	public static List readRecords(Object entity,String tableName,String sqlWhere,String orders) throws FrameException
	{
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String buf = null;
		List ar = new ArrayList();

		try
		{
			if(null!=sqlWhere && !"".equals(sqlWhere) && sqlWhere.toUpperCase().contains("WHERE")){
				buf = "SELECT * FROM " + tableName +" "+sqlWhere+" "+ orders;
			}else{
				buf = "SELECT * FROM " + tableName +" "+ orders;
			}
			con = ResourceManager.getConnection();
			st = con.createStatement();
			
			System.out.println(buf);
			rs = st.executeQuery(buf);
			Method methodArr[] = entity.getClass().getMethods();
			while(rs.next())
			{
				Object vo=Class.forName(entity.getClass().getName()).newInstance();
				ResultSetMetaData rsmd = rs.getMetaData() ;
				for(int i = 1; i <= rsmd.getColumnCount(); i++)
				{
					String colname = rsmd.getColumnName(i);
					String methodName = "set" + colname;
					for(int j=0;j<methodArr.length;j++){
						Method m=(Method)methodArr[j];
						if (m.getName().equalsIgnoreCase(methodName)) {
							fillObjectMethodValue(m, vo, rs.getString(colname));
							break;
						}
					}
				}
				ar.add(vo);
			}
			return ar;
		}
		catch(FrameException e)
		{
			throw e;
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			throw new FrameException("内部错误，请与管理员联系，访问表: "+tableName);
		}
		finally
		{
			try { if (rs != null) rs.close(); } catch(Exception exx) {}
			try { if (st != null) st.close(); } catch(Exception exx) {}
			try { if (con != null) con.close(); } catch(Exception exx) {}
		}
	}

		
	/**
	 * 为 vo 的特定 method 赋值，
	 * 
	 * @param m
	 * @param vo
	 * @param value
	 * @throws Exception
	 */
	private static void fillObjectMethodValue(Method m, Object vo, String value) throws Exception {
		// 获取方法的参数类型
		String paraType = m.getParameterTypes()[0].getName();
		if (paraType.equals("java.lang.String")) {
			// 字符串类型，直接调用赋值
			Object[] obj = { value };
			m.invoke(vo, obj);
		} else if (paraType.equals("java.sql.Timestamp")) {
			// 时间类型 2013/03/13 16:02:33
			Timestamp time = null;
			if (value != null && value.length() == 19) {
				try {
					time = new Timestamp(timeFormat.parse(value).getTime());
				} catch (Exception e) {
					throw new FrameException("Unsupport parseTime(" + value+ ") at " + vo.getClass().getName() + "."+ m.getName() + "(" + paraType + ")");
				}
			}
			Object[] obj = { time };
			m.invoke(vo, obj);
		} else if (paraType.equals("java.math.BigDecimal")) {
			// 金额保留两位小数
			BigDecimal big = null;
			if (value != null) {
				try {
					big = new BigDecimal(value).setScale(2,BigDecimal.ROUND_HALF_UP);
				} catch (Exception e) {
					throw new FrameException("Unsupport BigDecimal("+ value + ") at " + vo.getClass().getName()+ "." + m.getName() + "(" + paraType + ")");
				}
			}
			Object[] obj = { big };
			m.invoke(vo, obj);
		}else if(m.getParameterTypes()[0].isPrimitive()){ 
			if(paraType.equals("long")){
				Long lo=new Long(value);
				Object[] obj = { lo };
				m.invoke(vo, obj);
			}else if(paraType.equals("int")){
				Integer integer=new Integer(value);
				Object[] obj = { integer };
				m.invoke(vo, obj);
			}else{
				throw new FrameException("Unsupport Primitive type of "+ vo.getClass().getName() + "." + m.getName() + "("+ paraType + ")");
			} 
		}else {
			// 暂不支持的类型，直接抛异常
			throw new FrameException("Unsupport para type of "+ vo.getClass().getName() + "." + m.getName() + "("+ paraType + ")");
		}
	}
	
}

package com.ttsoft.bjtax.shenbao.jiekou.access;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;

/**
 * 为纳税评估单独提供的接口代码，添加申报缴款明细数据
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class Sb_jl_sbjkmxAccess
{
	public static void insertRecord(Connection con, Sbjkmx vo) throws Exception
	{
		PreparedStatement st = null;
		String buf = "INSERT INTO SBDB.SB_JL_SBJKMX(JKPZH, JSJDM, SZSMDM, YSJCDM, YSKMDM, JSJE, KSSL, SJSE, SKSSJSRQ, SKSSKSRQ, RKJE, SL, SJFC, QJFC, SWJGZZJGDM, ND, SBBH, CJRQ, LRRQ, QXDM) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try
		{
                    st = con.prepareStatement(buf);
                    st.setString(1, vo.getJkpzh());
                    st.setString(2, vo.getJsjdm());
                    st.setString(3, vo.getSzsmdm());
                    st.setString(4, vo.getYsjcdm());
                    st.setString(5, vo.getYskmdm());
                    st.setBigDecimal(6, vo.getJsje());
                    st.setBigDecimal(7, vo.getKssl());
                    st.setBigDecimal(8, vo.getSjse());
                    st.setTimestamp(9, vo.getSkssjsrq());
                    st.setTimestamp(10, vo.getSkssksrq());
                    st.setBigDecimal(11, vo.getRkje());
                    st.setBigDecimal(12, vo.getSl());
                    st.setBigDecimal(13, vo.getSjfc());
                    st.setBigDecimal(14, vo.getQjfc());
                    st.setString(15, vo.getSwjgzzjgdm());
                    st.setString(16, vo.getNd());
                    st.setString(17, vo.getSbbh());
                    st.setTimestamp(18, vo.getCjrq());
                    st.setTimestamp(19, vo.getLrrq());
                    st.setString(20, vo.getQxdm());

                    st.execute();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			throw new Exception("Database record access errors, Table: SBDB.SB_JL_SBJKMX");
		}
		finally
		{
			try
			{
				if (st != null) st.close();
			}
			catch(Exception ex)
			{
			}
		}
	}
}

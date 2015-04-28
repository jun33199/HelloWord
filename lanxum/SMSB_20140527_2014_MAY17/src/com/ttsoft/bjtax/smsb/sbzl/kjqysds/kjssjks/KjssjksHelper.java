/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Copyright: (C) 2003-2004 北京市地方税务局，四一安信息科技有限公司，版权所有. </p>
 * <p>Company: 四一安信息科技有限公司</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;

import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.exception.ExceptionUtil;

/**
 * <p>Title: 北京地税综合管理系统  扣缴企业所得税生成税收缴款书</p>
 * <p>Description: 扣缴企业所得税生成税收缴款书</p>
 * @author wangxm
 * @version 1.1
 */

public class KjssjksHelper
{
    /**
     * 生成默认的显示控制清单
     * @return 显示控制清单结果集 List
     */
    public static Yskm getYskm (String yskmdm,Timestamp sbrq)throws Exception
    {
        Yskm yskm=new Yskm();
        Connection conn = null;
        StringBuffer sql=new StringBuffer();
        PreparedStatement ps = null;
		ResultSet rs = null;
        try
        {
          //得到连接
          conn = SfDBResource.getConnection();
          sql.append("select t.qxfcbl,t.sjfcbl from dmdb.kj_dm_yskm t where t.yskmdm='");
          sql.append(yskmdm).append("' and t.nd='");
          sql.append(String.valueOf(TinyTools.getYear(sbrq))).append("'");
          ps=conn.prepareStatement(sql.toString());
		  rs=ps.executeQuery();
			
			while(rs.next()){
				yskm.setQxfcbl(rs.getBigDecimal("qxfcbl"));
				yskm.setSjfcbl(rs.getBigDecimal("sjfcbl"));
			}
			rs.close();
			ps.close();
			sql.delete(0,sql.length());
          
        }
        catch (Exception ex)
        {
          throw ExceptionUtil.getBaseException(ex, "查询预算科目失败！");
        }
        finally
        {
          //释放连接
          SfDBResource.freeConnection(conn);
        }
        return yskm;
    }

}
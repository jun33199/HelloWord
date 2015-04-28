/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ����Ϣ�Ƽ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ����Ϣ�Ƽ����޹�˾</p>
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
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Description: �۽���ҵ����˰����˰�սɿ���</p>
 * @author wangxm
 * @version 1.1
 */

public class KjssjksHelper
{
    /**
     * ����Ĭ�ϵ���ʾ�����嵥
     * @return ��ʾ�����嵥����� List
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
          //�õ�����
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
          throw ExceptionUtil.getBaseException(ex, "��ѯԤ���Ŀʧ�ܣ�");
        }
        finally
        {
          //�ͷ�����
          SfDBResource.freeConnection(conn);
        }
        return yskm;
    }

}
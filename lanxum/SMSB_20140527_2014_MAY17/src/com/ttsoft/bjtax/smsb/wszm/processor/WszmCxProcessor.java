package com.ttsoft.bjtax.smsb.wszm.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.Wszm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.wszm.web.WszmCxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

public class WszmCxProcessor implements Processor
{
	public WszmCxProcessor ()
    {
    }
	
	/**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
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
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

	private Object doSave(VOPackage vo) {
		// TODO Auto-generated method stub
		return null;
	}
	

    /**
     * ��ѯ
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	ArrayList dataList = new ArrayList();
    	ArrayList wszmList = new ArrayList();
        String names[] =
        {
        		"pzzldm", "wszh", "swjgzzjgdm","nsrsbh", "nsrmc", "hjje", "ndzb", "printflag", "cjrq", "lrrq", "dybz", "yxbz", "yxflag"
        };

        Connection conn = null;
        UserData ud = new UserData();
        WszmCxForm pf = (WszmCxForm) vo.getData();
        
        String wszhCon = "";
		String ndzbCon = "";
		String dybzCon = "";
		String whereCon = "";

        try
        {
        	Map dataMap = (Map) pf.getData();
        	String tempWszh = (String) dataMap.get("tempWszh");
        	String tempNdzb = (String) dataMap.get("tempNdzb");
        	String query_qsrq = (String) dataMap.get("query_qsrq");
        	String query_jzrq = (String) dataMap.get("query_jzrq");
        	String tempClbjdm = (String) dataMap.get("tempClbjdm");
        	
        	conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�õ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            
            //��˰֤��
            if ((tempWszh != null) && !tempWszh.equals(""))
			 {
            	wszhCon = wszhCon + " and wszh = '" + tempWszh + "'";
			 }
			
			 //����ֱ�
			 if ((tempNdzb != null) && !tempNdzb.equals(""))
			 {
				 ndzbCon = ndzbCon + " and ndzb = '" + tempNdzb + "'";
			 }
			 
			 //��ӡ��־
			 if ((tempClbjdm != null) && !tempClbjdm.equals("") && !pf.getTempClbjdm().equals("*"))
			 {
				 dybzCon = dybzCon + " and dybz = '" + tempClbjdm + "'";
			 }
			 
			 //���ʱ��
			 if ((query_qsrq != null) && !query_qsrq.equals(""))
			 {
				 whereCon = whereCon + " and cjrq>=to_date('" + query_qsrq +
				    " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
			 }
			 if ((query_jzrq != null) && !query_jzrq.equals(""))
			 {
				 whereCon = whereCon + " and cjrq<=to_date('" + query_jzrq +
		            " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
			 }
            
            //���ò�ѯ����
            StringBuffer querySql = new StringBuffer();
			querySql.append("select * from SBDB.SB_JL_KJSSWSZM ");
			querySql.append("where yxbz in ('"+CodeConstant.WSZM_YXBZ_0+"','"+CodeConstant.WSZM_YXBZ_1+"')");
			querySql.append(wszhCon);
			querySql.append(ndzbCon);
			querySql.append(dybzCon);
			querySql.append(whereCon);
			querySql.append(" and  zhdm = '"+pf.getHeadZhdm()+"' ");
			querySql.append("order by wszh,ndzb,pzzldm");
					
			Debug.out("WszmQuerySql=== wszm ==" + querySql);
			//��ѯ
			ResultSet rs = da.querySQL(querySql.toString());
			while (rs.next()) 
			{
				Wszm wszm = new Wszm();
	            
				wszm.setWszh(rs.getString("wszh"));
				wszm.setPzzldm(rs.getString("pzzldm"));
				wszm.setNdzb(rs.getString("ndzb"));
				wszm.setHjje(rs.getBigDecimal("HJJE"));
	            wszm.setPzlydm(rs.getString("pzlydm"));
	            wszm.setNsrsbh(rs.getString("nsrsbh"));
	            wszm.setNsrmc(rs.getString("nsrmc"));
				wszm.setYpzh(rs.getString("ypzh"));
				wszm.setYwszh(rs.getString("ywszh"));
				wszm.setYpzzldm(rs.getString("ypzzldm"));
				wszm.setYndzb(rs.getString("yndzb"));
				wszm.setDybz(rs.getString("dybz"));
				wszm.setDycs(rs.getString("dycs"));
				wszm.setYxbz(rs.getString("yxbz"));
				wszm.setCjrq(rs.getTimestamp("cjrq"));
				wszm.setLrrq(rs.getTimestamp("lrrq"));
				 
				dataList.add(wszm);
			}
            if (dataList.size() <= 0)
            {
                throw new ApplicationException("û�з��������ļ�¼��");
            }
            for (int i = 0; i < dataList.size(); i++)
            {
                //��ʽ��ÿ����¼
            	Wszm tmpWszm = (Wszm) dataList.get(i);
                //��ֵ�����ֵ����Map
                HashMap map = new HashMap();
                try
                {
                    BeanUtil.copyBeanToMap(names, tmpWszm, map);
                }
                catch (Exception ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("����ת������");
                }

                //map.put("hjsjje",wszz.getHjsjje().toString());
                map.put("cjrq", tmpWszm.getCjrq().toString().substring(0, 19));
                map.put("lrrq", tmpWszm.getLrrq().toString().substring(0, 19));
                //������ʾֵ
                if (map.get("dybz").equals("1"))
                {
                    map.put("printflag", "�Ѵ�ӡ");
                }
                else
                {
                    map.put("printflag", "δ��ӡ");
                }
                
                //������ʾֵ
                if (map.get("yxbz").equals("1"))
                {
                    map.put("yxflag", "������");
                }
                else
                {
                    map.put("yxflag", "δ����");
                }
                wszmList.add(map);
            }
            //��ֵ�Ż�form����
            pf.setDataList(wszmList);

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
        return pf;
    }
    
    
    /**
     * ������Ч��־
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        //from����
        WszmCxForm pf = new WszmCxForm();
        pf = (WszmCxForm) vo.getData();
        
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�ĵõ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_kjsswszm " +
                            " set yxbz='" + CodeConstant.WSZM_YXBZ_1 + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'"+
                            " and zhdm='" + pf.getHeadZhdm() + "'";

            //1����������
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�������ݳ���");
            }
            
            //2������Ʊ֤�ӿڣ����ϵ�ǰ��˰֤�ţ�������ȡ��
            try
            {
                String result = ServiceProxy.setCancellation(ud,
                	pf.getHeadPzzldm(),
                    pf.getHeadNdzb() +
                    pf.getHeadWszh(),
                    0, "1", "0", "0");
            }
            catch (Exception ex1)
            {
                throw new ApplicationException("������˰֤�ų���");
            }
            pf.setHeadWszh("");
            pf.setHeadNdzb("");
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }
    
    
    /**
     * ���´�ӡ��־
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        UserData ud = new UserData();
        //from����
        WszmCxForm pf = new WszmCxForm();
        pf = (WszmCxForm) vo.getData();
        
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //�õ�UserData
            ud = (UserData) vo.getUserData();
            //�ĵõ����ش���
            String qxdm = InterfaceDj.getQxdm(ud);
            String strSql = "update sbdb.sb_jl_kjsswszm " +
                            " set dybz='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                            " where ndzb='" + pf.getHeadNdzb() + "'" +
                            " and pzzldm='" + pf.getHeadPzzldm() + "'" +
                            " and wszh='" + pf.getHeadWszh() + "'";

            //1����������
            try
            {
                da.updateSQL(strSql);
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("�������ݳ���");
            }
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
        return pf;
    }

}

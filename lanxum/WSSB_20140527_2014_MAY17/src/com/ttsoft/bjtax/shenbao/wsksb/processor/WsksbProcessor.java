package com.ttsoft.bjtax.shenbao.wsksb.processor;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 ��һ���Źɷ����޹�˾����Ȩ����.</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
 * @author  guzhixian
 * @version 1.1
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORContext;
import com.ekernel.db.or.ORManager;
import com.syax.bjtax.ca.proxy.CAProxy;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.shenbao.model.domain.Wynskszsm;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.CAUtils;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.wsksb.SessionKey;
import com.ttsoft.bjtax.shenbao.wsksb.xmlvo.WsksbVO;
import com.ttsoft.bjtax.shenbao.zhsb.ZhsbMapConstant;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


public class WsksbProcessor implements Processor
{
    public WsksbProcessor()
    {
    }

    /**
     * SessionID����
     */
    private final long SESSION_ID = 0;

    //ʵ��Processor�ӿ�
    public Object process(VOPackage vOPackage) throws BaseException
    {
        switch(vOPackage.getAction())
        {
            case SessionKey.INT_ACTION_SAVE:
                try{ doSave(vOPackage);
                return vOPackage;}
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "������˰���걨������ʧ��!");
                }
            case SessionKey.INT_ACTION_QUERYJKSJL :
                try{ return doQueryJks(vOPackage, CodeConstant.SJLY_SB_SBLR); }
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "��ѯ�ɿ��걨������ʧ�ܣ�");
                }
           case SessionKey.INT_ACTION_QUERYWSKJL :
                    try{ return doQueryWsk(vOPackage); }
                    catch(Exception ex) {
                        throw ExceptionUtil.getBaseException(ex, "��ѯ��˰���걨������ʧ�ܣ�");
                    }
           case SessionKey.INT_ACTION_QUERYGZSX :
               try{ return doQueryGzsx(vOPackage); }
               catch(Exception ex) {
                   throw ExceptionUtil.getBaseException(ex, "��ѯ��֪��������ʧ�ܣ�");
               }    
                    
            case SessionKey.INT_ACTION_DELETE:
                try { doDel(vOPackage);
                    return null; }
                catch(Exception ex) {
                    throw ExceptionUtil.getBaseException(ex, "ɾ����˰���걨������ʧ�ܣ�");
                }
                default:
                    throw ExceptionUtil.getBaseException(new Exception("ϵͳ����,�������Ա��ϵ!"));
            }
        }


    /**
     * ɾ����˰�˱�����˰���걨����
     * @param voPackage object
     * @return map
     * @throws java.lang.Exception
     */
    private void doDel(VOPackage vOPackage) throws BaseException
    {
    	HashMap hm=(HashMap)vOPackage.getData();
        UserData ud = vOPackage.getUserData();
        DzyjHelper dh = new DzyjHelper();        
        Wynsksb wynsksb =(Wynsksb)hm.get(ZhsbMapConstant.SBSJ);
        WsksbVO wsksbvo = (WsksbVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);  
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        Connection conn = null;
        ORManager orManager = null;
        try
        {
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);
            Vector criteria = null; //��ѯ����
            ORContext orCtx = null; //��ѯ��ORContext����
            //ɾ����ϸ����������
            StringBuffer sqlBuffer = new StringBuffer();
            sqlBuffer.append("(SBBH = '" + wynsksb.getSbbh() + "'");
            sqlBuffer.append(" AND FSDM = '5' AND JSJDM = '"+wynsksb.getJsjdm()+"')");
            String wheresql = sqlBuffer.toString(); //ɾ������
    		// ����ǩ��Ԫ��
            //System.out.println("ud.getCaflag():"+ud.getCaflag());
			//dzyj.setYwczlx(wsksbvo.getYwczlx());
			//dzyj.setYwdm(wsksbvo.getYwlx());
    		/*if (ud.getCaflag()) {
    			try {
			dzyj.setYwuid(wynsksb.getSbbh());
    				dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", wynsksb.getSbbh());
    			} catch (com.syax.frame.exception.ApplicationException e) {
    				throw ExceptionUtil.getBaseException(e);
    			} catch (Exception ex) {
    				ex.printStackTrace();
    				throw ExceptionUtil.getBaseException(ex);
    			}
      			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
    			//System.out.println("============����ǩ�����ݽ���==============");
    		}*/
            
    		try
	        {
	       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", wynsksb.getSbbh());
	       }catch (Exception ex)
	        {
	            ex.printStackTrace();
	            throw ExceptionUtil.getBaseException(ex);
	        }
	       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

    		String sqlZb = wheresql; //��������
            //ɾ������
            orManager.deleteObject(SESSION_ID, conn, sqlZb, new Wynsksb());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "ɾ���걨��Ŷ�Ӧ����Ӧ��˰������ʧ��!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * ������˰�˱�����˰���걨����
     * @param voPackage object
     * @return map
     * @throws java.lang.Exception
     */
    private void doSave(VOPackage vOPackage) throws BaseException
    {
        //ORʵ��
        ORManager orManager = null;

        Connection conn = null;

        HashMap hm=(HashMap)vOPackage.getData();
        Wynsksb wynsksb =(Wynsksb)hm.get(ZhsbMapConstant.SBSJ);
        UserData ud = vOPackage.getUserData();
        DzyjHelper dh = new DzyjHelper();
        WsksbVO wsksbvo = (WsksbVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		
        //ѭ�������������ϸ����
        try
        {

          String sbbh = InterFaceProcessor.getSbbh(wynsksb.getJsjdm());  //��׼ʱ�䣬1970��1��1��0�������ĺ�����
          wynsksb.setSbbh(sbbh);
          hm.put(ZhsbMapConstant.SBSJ,wynsksb);
  		// ����ǩ��Ԫ��
			//dzyj.setYwczlx(wsksbvo.getYwczlx());
  			//dzyj.setYwdm(wsksbvo.getYwlx());
  		/*if (ud.getCaflag()) {
  			try {
  			dzyj.setYwuid(wynsksb.getSbbh());
  				dzyj = dh.saveDzyjsj(dzyj, "0", "0", "0", wynsksb.getSbbh());
  			} catch (com.syax.frame.exception.ApplicationException e) {
  				throw ExceptionUtil.getBaseException(e);
  			} catch (Exception ex) {
  				ex.printStackTrace();
  				throw ExceptionUtil.getBaseException(ex);
  			}
  			hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ,dzyj);
  			//System.out.println("============����ǩ�����ݽ���==============");
  		}*/
        try
        {
       	 dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0", wynsksb.getSbbh());
       }catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
       hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);

            // ��� ORManager
            orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            Vector cri = new Vector();
            cri.add("nd='" + String.valueOf(new SimpleDateFormat("yyyy").format(wynsksb.getSbrq())) + "'");
            cri.add("qxdm='" + wynsksb.getQxdm() + "'");
            cri.add("jsjdm='" + wynsksb.getJsjdm() + "'");
            cri.add("to_char(skssksrq,'yyyyMMdd')='" + new SimpleDateFormat("yyyyMMdd").format(wynsksb.getSkssksrq()) + "'");
            cri.add("to_char(skssjsrq,'yyyyMMdd')='" + new SimpleDateFormat("yyyyMMdd").format(wynsksb.getSkssjsrq()) + "'");
            ORContext orCtx = new ORContext(Wynsksb.class.getName(), cri);

            ArrayList list = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //��ѯ��˰���¼

            if (list.size() > 0) {

                throw ExceptionUtil.getBaseException(new Exception("��˰�걨�����Ѿ����ڣ������ظ����棡"));
            }


            //������������
            orManager.makePersistent(SESSION_ID, conn, wynsksb);

            /*if (vOPackage.getUserData().getCaflag()) {
              //����ǩ�����ݱ��е�˰Ʊ���
              dzyj.setYwuid(wynsksb.getSbbh());
              CAProxy.getInstance().updateSignedData(dzyj);
            }*/

        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex, "�������ݴ���!");
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * �걨����ά����ڽ��룬��ѯ��˰�˱��������걨����
     * @param voPackage object
     * @param sjly   ������Դ
     * @return map
     * @throws java.lang.Exception
     */
    public List doQueryJks(VOPackage voPackage ,String sjly) throws Exception
    {
        HashMap dataMap = new HashMap(); //���ص�map����
        //�������ݿ�����
        Connection conn = null;
        try
        {
            Map tempData = (HashMap)voPackage.getData(); //��ǰ�˻��map����
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM");  //˰�������֯��������
            String jsjdm = (String)tempData.get(SessionKey.JSJDM); //��ȡ�û��ļ��������
            //ע�����ڸ�ʽ������Ϊ:(YYYY-MM-DD)
            String whrq = (String)tempData.get(SessionKey.WHRQ); //��ȡ�û�ά���걨��������

            String qxdm = swjgzzjgdm.substring(0,2);  //���ش���

            //��ѯ�걨�ɿ�����Ĳ�ѯ���
            ArrayList zbResult = new ArrayList();

            //���˳��Ѿ��нɿ��¼���걨��ţ���ҳ���ڻ���(�����ʶ�ĵ�1λ<>0or��20λ<>0)
            //����ͬһ�������Ƿ��нɿ��¼��ֻҪû�нɿ�������޸�
            String sqlWhere = "(ZYRQ >= to_date('20050101','yyyymmdd') AND QXDM = '"
                + qxdm + "' AND JSJDM = '" + jsjdm + "'"
                + " AND substr(to_char(SBRQ,'yyyy-mm-dd'),0,7) = substr('"
                + whrq + "', 0, 7) ) "; //AND SJLY = '" + sjly
            Vector criteria = new Vector();
            criteria.add(sqlWhere);

            ORContext orCtx = new ORContext(Sbjkzb.class.getName(), criteria);

            //��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //��ѯ�걨�ɿ�����

            return zbResult; //���ؽ������
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }

    /**
     * �걨����ά����ڽ��룬��ѯ��˰�˱��������걨����
     * @param voPackage object
     * @param sjly   ������Դ
     * @return map
     * @throws java.lang.Exception
     */
    public HashMap doQueryWsk(VOPackage voPackage) throws Exception
    {
        HashMap dataMap = new HashMap(); //���ص�map����
        //�������ݿ�����
        Connection conn = null;
        try
        {
            Map tempData = (HashMap)voPackage.getData(); //��ǰ�˻��map����
            String swjgzzjgdm = (String)tempData.get("WSSB_SWJGZZJGDM");  //˰�������֯��������
            String skssksrq = (String)tempData.get(SessionKey.SKSSKSRQ);  //
            String skssjsrq = (String)tempData.get(SessionKey.SKSSJSRQ);  //
            String jsjdm = (String)tempData.get(SessionKey.JSJDM); //��ȡ�û��ļ��������
            //ע�����ڸ�ʽ������Ϊ:(YYYY-MM-DD)
            String whrq = (String)tempData.get(SessionKey.WHRQ); //��ȡ�û�ά���걨��������

            String qxdm = swjgzzjgdm.substring(0,2);  //���ش���

            //��ѯ�걨����Ĳ�ѯ���
            Wynsksb wynsksb = null;
        //    Zqrl zqrl = null;
            //��ѯ��Ӧ��˰��˰��˰Ŀ�Ĳ�ѯ���
            ArrayList szsmResult = new ArrayList();

            StringBuffer sqlStrBuf = new StringBuffer();

            sqlStrBuf.append( "(nd = substr('" + whrq + "', 0, 4) AND QXDM = '")
                .append( qxdm + "' AND JSJDM = '" + jsjdm + "'")
                .append( " AND to_char(SKSSKSRQ,'yyyy-mm-dd') = '")
                .append( skssksrq + "'  ")
                .append( " AND to_char(SKSSJSRQ,'yyyy-mm-dd') = '")
                .append( skssjsrq + "' ) ");
            System.out.println("-----Wsksb Query Sql:::"+sqlStrBuf);

            String sqlString = sqlStrBuf.toString();
            Vector criteria = new Vector();
            criteria.add(sqlString);

            ORContext orCtx = new ORContext(Wynsksb.class.getName(), criteria);

            //��� ORManager
            ORManager orManager = DBResource.getORManager(DBResource.OR_SHENBAO);
            //������ݿ�����
            conn = DBResource.getConnection(DBResource.DB_SHENBAO);

            ArrayList zbResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtx); //��ѯ��˰���¼
            if (zbResult.size()>0){
                wynsksb = (Wynsksb)zbResult.get(0);
            }

            sqlStrBuf = new StringBuffer();
            sqlStrBuf.append("(ZXBS = '0'").append(" AND ZTBS = '0'");
            sqlStrBuf.append(")").append(" ORDER BY SZSMDM ASC");
            sqlString = sqlStrBuf.toString();
            Vector criteriaMx = new Vector();
            criteriaMx.add(sqlString);

            ORContext orCtxMx = new ORContext(Wynskszsm.class.getName(), criteriaMx);
            szsmResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxMx); //��ѯ��˰��˰��˰Ŀ��

            //ȡӪҵ˰����
       /*     sqlStrBuf = new StringBuffer();
            sqlStrBuf.append( "(ZQLXDM >= '0' AND ZQQSRQ <= to_date('")
                .append( whrq + "','yyyy-mm-dd') AND SZSMDM = '" + SzsmdmConstant.YYS_TLYS)
                .append( "' AND DJZCLXDM = '" + djzclxdm)
                .append( "' AND ZQZZRQ >= to_date('" + whrq)
                .append( "','yyyy-mm-dd')) ORDER BY ZQQSRQ");
            sqlString = sqlStrBuf.toString();
            Vector criteriaZq = new Vector();
            criteriaZq.add(sqlString);

            ORContext orCtxZq = new ORContext(Zqrl.class.getName(), criteriaZq);
            ArrayList zqResult = (ArrayList)orManager.query(SESSION_ID, conn, orCtxZq); //��ѯ��˰��˰��˰Ŀ��
            if (zqResult.size()>0){
                zqrl = (Zqrl)zqResult.get(0);
            }
*/
            
            
            
            
            
            
            DBResource.destroyConnection(conn);  //�ر����ݿ�����
            conn = null;

            dataMap.put(SessionKey.WSKJKS, wynsksb);
            dataMap.put(SessionKey.WSKSZSM, szsmResult);
         //   dataMap.put(SessionKey.ZQRL, zqrl);

            return dataMap; //���ؽ������
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            DBResource.destroyConnection(conn);
        }
    }
    	
    /**
     * ��ѯ��֪����֪ͨ
     * @param voPackage
     * @return
     * @throws Exception
     */
    public String doQueryGzsx(VOPackage vo) throws Exception
    {
    	Connection conn = DBResource.getConnection(DBResource.DB_SHENBAO);
    	try{
	    	String result = "";
	    	
	    	
	    	/*--------��Ӹ�֪����  add by lijn 20140228 start------*/
	        String sql = "select gzsxnr  from SBDB.SB_JL_GZSX where jsjdm = ? and ph like 'lxwynssb%'  and GZSXKSRQ <= sysdate  and GZSXJSRQ >= sysdate";
	        
	        PreparedStatement ps = conn.prepareStatement(sql);
	        ps.setString(1, vo.getUserData().getYhid());
	        
	        ResultSet rs = ps.executeQuery();
	        if(rs.next()){
	        	result = rs.getString("gzsxnr");	
	        }
	        /*-----------------end--------------------------*/
	    	return result;
	    }
	    catch(Exception e)
	    {
	        e.printStackTrace();
	        throw ExceptionUtil.getBaseException(e);
	    }
	    finally
	    {
	        DBResource.destroyConnection(conn);
	    }
    }

}
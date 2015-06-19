/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.gtgsh.processor;

import java.math.*;
import java.sql.*;
import java.util.*;

import com.ttsoft.bjtax.dj.model.*;
import com.ttsoft.bjtax.sfgl.common.db.util.*;
import com.ttsoft.bjtax.sfgl.common.util.*;
import com.ttsoft.bjtax.shenbao.model.domain.*;
import com.ttsoft.bjtax.smsb.constant.*;
import com.ttsoft.bjtax.smsb.gtgsh.web.*;
import com.ttsoft.bjtax.smsb.lszs.web.LszsHzjksForm;
import com.ttsoft.bjtax.smsb.model.client.*;
import com.ttsoft.bjtax.smsb.util.*;
import com.ttsoft.common.model.*;
import com.ttsoft.framework.exception.*;
import com.ttsoft.framework.processor.*;
import com.ttsoft.framework.util.*;

/**
 * ����˰֤���ܳɽɿ��顣��һ˰һƱ��һ˰��Ʊ����
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��˰֤���ܽɿ���</p>
 * @author �������飭��½��
 * @version 1.1
 */

public class GtgshHzjksProcessor
    implements Processor
{
	//ÿ��Ʊ�е����Ԥ���Ŀ
    private static final int EVE_PAGE_YSKM = 14;
    
    public GtgshHzjksProcessor ()
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
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result =doFp(vo);;
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            case CodeConstant.SMSB_HZSBJKDACTION:

                //�����걨�ɿ
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_HZJKSACTION:

                //���ܽɿ���
                result = doQuery(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
        List dataList = new ArrayList();
        GtgshHzjksForm pf = new GtgshHzjksForm();
        pf = (GtgshHzjksForm) vo.getData();
        //�������ڷ�Χ��ȱʡ�ǻ��ܵ������Ϣ
        pf.setHzksrq(SfDateUtil.getDate());
        pf.setHzjsrq(SfDateUtil.getDate());

        try
        {
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //˰�������֯��������
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //˰�������֯��������
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
        }
        return pf;
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
        Connection conn = null;
        double hjje = 0;
        GtgshHzjksForm pf = new GtgshHzjksForm();
        pf = (GtgshHzjksForm) vo.getData();

        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�ӵǼǽӿ��л����Ϣ
            SWDJJBSJ dj = new SWDJJBSJ();
            try
            {
                dj = (SWDJJBSJ) InterfaceDj.getJBSJ(pf.getJsjdm(), ud);
            }
            catch (Exception ex5)
            {
                ex5.printStackTrace();
                throw ExceptionUtil.getBaseException(ex5);
            }

            pf.setSwjgzzjgdm(dj.getSwjgzzjgdm()); //˰�������֯��������
            pf.setSwjgzzjgmc(dj.getSwjgzzjgmc()); //˰�������֯��������

            Vector tempVector = new Vector();
            tempVector.addElement("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
            tempVector.addElement("hzksrq>=to_date('" + pf.getHzksrq()
                                  + " 00:00:00','yyyy-mm-dd hh24:mi:ss')");
            tempVector.addElement("hzjsrq<=to_date('" + pf.getHzjsrq()
                                  + " 23:59:59','yyyy-mm-dd hh24:mi:ss')");
            tempVector.addElement("jsjdm='" + pf.getJsjdm() + "'"); //Modified by lufeng 2003-12-24
            tempVector.addElement("jkpzh like '" + pf.getJsjdm() + "%'"); //Modified by lufeng 2003-12-24
            tempVector.addElement("sbhzdh='" + pf.getSbhzdh() + "'");
            tempVector.addElement("1=1 order by jkpzh asc");

            List tempList = da.query(Gtgshwszsbhz.class, tempVector);

            for (int i = 0; i < tempList.size(); i++)
            {
                Gtgshwszsbhz hz = (Gtgshwszsbhz) tempList.get(i);
                HashMap map = new HashMap();
                //�ж�һƱһ˰ / һƱ��˰ ��ĩλΪ0��ΪһƱһ˰������ΪһƱ��˰��
                String strJkpzh = hz.getJkpzh();
                map.put("jkpzh_ypds",
                        strJkpzh.substring(0, strJkpzh.length() - 1));
                //��ʾ�Ľɿ�ƾ֤�ţ�ֻ��ʾǰ17λ
                strJkpzh = strJkpzh.substring(strJkpzh.length() - 1,
                                              strJkpzh.length());
                if (strJkpzh.equals("0"))
                {
                    map.put("jkpzh", hz.getJkpzh());
                }
                else
                {
                    //����һƱ��˰�����һλ����ʾ
                    map.put("jkpzh", map.get("jkpzh_ypds"));
                }
                map.put("sjse", String.valueOf(hz.getSjse()));
                hjje = hjje
                       + StringUtil.getDouble( (hz.getSjse()).toString(), 0);
                dataList.add(map);
            }
            //����һƱ��˰���б��Ա���ʾ
            dataList = (ArrayList) JksUtil.getYpdsList(dataList);
            pf.setDataList(dataList);
            pf.setHjzs(String.valueOf(dataList.size()));
            pf.setHjje(StringUtil.getCurrency(String.valueOf(hjje)));
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
     * ����Oracle SQL in����1000�ķ���
     * @param ids ����id
     * @param ÿ��id������
     * @param in or not in
     * @return �����ĺ�in sql
     */
    private  String  getOracleSQLIn(List ids, int count, String field,String type) {  

	    count = Math.min(count, 1000);  

	    int len = ids.size();  

	    int size = len % count;  

	    if (size == 0) {  

	        size = len / count;  

	    } else {   

	        size = (len / count) + 1;  

	    }  

	    StringBuffer builder = new StringBuffer();  

	    for (int i = 0; i < size; i++) {  

	        int fromIndex = i * count;  

	        int toIndex = Math.min(fromIndex + count, len);  

	        //System.out.println(ids.subList(fromIndex, toIndex));  

	       // String productId = "";//StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), ""); 
	        List tempList=ids.subList(fromIndex, toIndex);
	        
	        StringBuffer buf = new StringBuffer();
	        for(int k=0;k<tempList.size();k++){
	        	//HashMap map=(HashMap)tempList.get(k);
	        	if(k!=0){
	        		buf.append(",");
	        	}
	        	
	        	buf.append("'").append((String)tempList.get(k)).append("'");
	        }

	        if (i != 0) {  

	            builder.append(" or ");  

	        }  

	        builder.append(field).append(" "+type+" (").append(buf.toString()).append(")");  

	    }  
	    return builder.toString();
	}  
    
    
    
    /**
     * ��Ʊ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doFp (VOPackage vo)
        throws BaseException
    {
    	
    	try{
    		
    	   //��˰֤��Ԥ���Ŀ����List
    	   List list=getWszhAndYskmdms(vo);
    	   System.out.println("GtgshHzjksProcessor.doFp##################list.size()="+list.size());
    	   if(list.size()==0){
    	     throw new ApplicationException("û�з�����������˰֤���Ի��ܣ�");
    	   }else{
    	    recursionFp(list,vo);
    	   }
    	}catch(Exception ex){
    		 ex.printStackTrace();
             throw ExceptionUtil.getBaseException(ex);
    	}
    	GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
    	return pf;
    }
    
    /**
     * ��Ʊ����
     * @param list Ԥ���Ŀ���ݼ�����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
	private void recursionFp(List list,VOPackage vo)  throws BaseException{
		try{
		
			System.out.println("GtgshHzjksProcessor.recursionFp##################list.size()="+list.size());
			//����Ҫ���ܵ���˰֤
			if(list.size()>0){
				    List xhzWszxhList=getXhzWszxhList(list);
				    System.out.println("GtgshHzjksProcessor.recursionFp##################xhzWszxhListt.size()="+xhzWszxhList.size());
                	//ȡ�ñ�����Ҫ���ܵ���˰֤�ż��� sql
                	String hzsql=getOracleSQLIn(xhzWszxhList,1000,"a.wszxh"," in");
                	System.out.println("GtgshHzjksProcessor.recursionFp##################hzsql="+hzsql);
                	//���л��ܲ���
                	doSave(vo,hzsql);
                	//ȡ����һ����˰֤��Ԥ���Ŀ����List
                	List nextList=getWszhAndYskmdms(vo);
                	System.out.println("GtgshHzjksProcessor.recursionFp##################nextlist()="+nextList.size());
                	//������һ�η�Ʊ�������ݹ���ã�
                	if(nextList.size()>0){
                		recursionFp(nextList,vo);
                	}
			}
		}catch(Exception e){
			 e.printStackTrace();
			throw new ApplicationException("��˰֤���ܷ�Ʊ�������");
		}
	}
    
	  /**
     * ��ȡ��Ҫ���ܵ���˰֤��� ���Ϸ���
     * @param list ���ݼ�������˰֤��ź����Ӧ��Ԥ���Ŀ���뼯�ϣ�
     * @return ��˰֤���List����
     * @throws BaseException
     */
	 private List getXhzWszxhList(List list) throws BaseException{
		 ArrayList reList=new ArrayList();
		 HashMap yskmdmMap = new HashMap();
		 for(int i=0;i<list.size();i++){
			  //��˰֤���
			  String wszxh=(String)((HashMap)list.get(i)).get("wszxh");
			  //System.out.println("GtgshHzjksProcessor.getXhzWszxhList##################wszxh="+wszxh);
			  //Ԥ���Ŀ����List
			  List yskmdmList=(List)((HashMap)list.get(i)).get(wszxh);
			  //System.out.println("GtgshHzjksProcessor.getXhzWszxhList##################yskmdmList.size()="+yskmdmList.size());
			  HashMap  tempYskmdmMap=(HashMap)yskmdmMap.clone();
			  for(int j=0;j<yskmdmList.size();j++){
				  tempYskmdmMap.put((String)yskmdmList.get(j), (String)yskmdmList.get(j));
			  }
			  if(tempYskmdmMap.size()<=EVE_PAGE_YSKM){	
				  reList.add(wszxh);
				  yskmdmMap=tempYskmdmMap;
			  }
		 }
		 return reList;
	 }	 
	
   /**
     * ��ȡ��˰֤ �Ͷ�ӦԤ���Ŀ�� ���Ϸ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��˰֤List����
     * @throws BaseException
     */
    private List getWszhAndYskmdms(VOPackage vo) throws BaseException{
    	GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
    	Connection conn = null;
    	ResultSet rs = null;
    	ResultSet rs1 = null;
    	ArrayList reList=new ArrayList();
    	//StringBuffer sqlBuf=new StringBuffer();
    	String strSql="";
    	String timeCon = ""; //ʱ������
        String whereCon = ""; //where ����
        String qxdm = ""; //���ش���
       
        String hzksrq = pf.getHzksrq();
        String hzjsrq = pf.getHzjsrq();
        String hzlx = pf.getHzlx();
    	try
         {
    		UserData ud = (UserData) vo.getUserData();
		    qxdm = InterfaceDj.getQxdm(ud);
		   //ʱ������
	        if (hzksrq != null && !hzksrq.equals(""))
	        {
	            timeCon = timeCon + " and a.cjrq>=to_date('" + hzksrq +
	                      " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
	        }
	        if (hzjsrq != null && !hzjsrq.equals(""))
	        {
	            timeCon = timeCon + " and a.cjrq<=to_date('" + hzjsrq +
	                      " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
	        }

	        //���˻��� 1 / ����λ���� 0
	        if (hzlx.equals(CodeConstant.HZFS_DW))
	        {
	            whereCon = " and a.jsjdm='" + pf.getJsjdm() + "'";
	        }
	        if (hzlx.equals(CodeConstant.HZFS_GR))
	        {
	            whereCon = " and a.lrr='" + pf.getLrr() + "'";

	        }
           
            
	        strSql = "select a.wszxh,a.nsrjsjdm" +
            " from sbdb.sb_jl_gtgshwszz a" +
            " where " +
            " a.qxdm='" + qxdm + "' " + timeCon +
            " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //�Ѵ�ӡ
            " and (a.sbhzdh = '' or a.sbhzdh is null) " + whereCon ;
    		
	  
            System.out.println("GtgshHzjksProcessor.getWszhAndYskmdms##########sql="+strSql); 		
            
    		 conn = SfDBResource.getConnection();
             SfDBAccess da = new SfDBAccess(conn);
             rs = da.querySQL(strSql);
             while (rs.next())
             { 
            	 
                 HashMap remap = new HashMap();
                 strSql = "select distinct(b.yskmdm) yskmdm" +
                 " from sbdb.sb_jl_gtgshwszmx b " +
                 " where " +
                 " b.nsrjsjdm='"+ rs.getString("nsrjsjdm")+"' and b.wszxh='"+rs.getString("wszxh")+"'"; 
                 //System.out.println(reList.size()+"GtgshHzjksProcessor.getWszhAndYskmdms##########sql="+strSql);
                 rs1 = da.querySQL(strSql);
                 ArrayList yskmList=new ArrayList();
                 while (rs1.next())
                 {
                	 yskmList.add(rs1.getString("yskmdm"));
                 }
                 //��˰֤���
                 remap.put("wszxh", rs.getString("wszxh"));
                 //��˰֤��Ŷ�Ӧ��Ԥ���Ŀ
                 remap.put(rs.getString("wszxh"), yskmList);
                 reList.add(remap);                             
             }
             if(rs1!=null){
                 rs1.close();
              }
             rs.close();
         }
    	catch (Exception ex)
        {
    		 throw new ApplicationException("��ѯԤ���Ŀ����");
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }
   return reList;
	}
    
    /**
     * ��ѯ������������ѯ����û�л��ܵ���˰֤�ţ�Ȼ����л���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo,String insql)
        throws BaseException
    {
        Connection conn = null;
        double hjje = 0; //�ϼƽ��
        int intCount = 0; //����
        String hzff = "";
        String strSql = ""; //ƴ��sql
        String tableName = "";  //������
        String timeCon = ""; //ʱ������
        String whereCon = ""; //where ����
        String qxdm = ""; //���ش���

        List dataList = new ArrayList();
        List hzList = new ArrayList();
        List sbjkzbList = new ArrayList();
        List sbjkmxList = new ArrayList();

        GtgshHzjksForm pf = (GtgshHzjksForm) vo.getData();
        Timestamp nowTime = new Timestamp(System.currentTimeMillis());
        String hzlx = pf.getHzlx();
        String hzksrq = pf.getHzksrq();
        String hzjsrq = pf.getHzjsrq();
        int ypys = StringUtil.getInt(pf.getYpys(), 1); //�õ�һ˰һƱ/һ˰��Ʊ��ʶ
        //����Ormap
        Gtgshwszmx wszmx = new Gtgshwszmx();
        Gtgshwszz wszz = new Gtgshwszz();
        Sbjkzb sbjkzb = new Sbjkzb();
        Sbjkmx sbjkmx = new Sbjkmx();

        //ʱ������
        if (hzksrq != null && !hzksrq.equals(""))
        {
            timeCon = timeCon + " and a.cjrq>=to_date('" + hzksrq +
                      " 00:00:00','yyyy-mm-dd hh24:mi:ss')";
        }
        if (hzjsrq != null && !hzjsrq.equals(""))
        {
            timeCon = timeCon + " and a.cjrq<=to_date('" + hzjsrq +
                      " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
        }

        //���˻��� 1 / ����λ���� 0
        if (hzlx.equals(CodeConstant.HZFS_DW))
        {
            whereCon = " and a.jsjdm='" + pf.getJsjdm() + "'";
        }
        if (hzlx.equals(CodeConstant.HZFS_GR))
        {
            whereCon = " and a.lrr='" + pf.getLrr() + "'";

        }
        if(insql.length()>0){
        	insql=" and ("+insql+")";
        }
        try
        {
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            //UserData����
            UserData ud = (UserData) vo.getUserData();
            //�õ����ش���
            qxdm = InterfaceDj.getQxdm(ud);
            
            
            /*
            strSql = "select distinct b.szsmdm,b.jsjdm, " +
                     " to_char(b.skssksrq,'yyyymmdd') skssksrq, " +
                     " to_char(b.skssjsrq,'yyyymmdd') skssjsrq, b.szdm," +
                     " sum(b.sjse) sjse,sum(b.kssl) kssl,sum(b.jsje) jsje " +
                     " from sbdb.sb_jl_gtgshwszz a, sbdb.sb_jl_gtgshwszmx b " +
                     " where a.wszh = b.wszh and a.ndzb=b.ndzb " +
                     " and a.qxdm='" + qxdm + "' " + whereCon + timeCon +
                     " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //�Ѵ�ӡ
                     " and (a.sbhzdh = '' or a.sbhzdh is null) " +
                     " and a.nsrjsjdm = b.nsrjsjdm and a.wszxh = b.wszxh " +
                     " group by b.szsmdm, b.jsjdm, b.skssksrq, b.skssjsrq, b.szdm " +
                     " order by b.szsmdm asc";
            */
            
            
            strSql = "with t as (select b.szsmdm,b.jsjdm," +
	   		 " min(b.skssksrq) over(partition by b.szdm,b.yskmdm) skssksrq, " +
	   		 " max(b.skssjsrq) over(partition by b.szdm,b.yskmdm) skssjsrq, b.szdm," +
	   		 " b.sjse,b.kssl,b.jsje" +
	   		 " from sbdb.sb_jl_gtgshwszz a, sbdb.sb_jl_gtgshwszmx b " +
	   		 " where a.wszh = b.wszh and a.ndzb = b.ndzb " +
	   		 " and a.qxdm='" + qxdm + "' " + timeCon +
	   		 " and a.clbjdm = '" + CodeConstant.SMSB_WSZ_PRINT + "'" + //�Ѵ�ӡ
	   		 " and (a.sbhzdh = '' or a.sbhzdh is null) " + whereCon +
	   		 " and a.nsrjsjdm=b.nsrjsjdm and a.wszxh=b.wszxh"+insql+" ) " +
	   		 " select szsmdm,jsjdm, to_char(skssksrq,'yyyymmdd') skssksrq, to_char(skssjsrq,'yyyymmdd') skssjsrq, szdm, " +
	   		 " sum(sjse) sjse, sum(kssl) kssl, sum(jsje) jsje " +
	   		 " from t" +
	      	 " group by t.szsmdm, t.jsjdm, t.skssksrq, t.skssjsrq,t.szdm " +
	   	     " order by t.szsmdm asc";
            
      System.out.println("GtgshHzjksProcessor.doSave##########sql="+strSql); 		
            
            
            
            
            System.out.println("��ѯ�ɻ������ݣ���������������������"+strSql);
            try
            {
                ResultSet rs = da.querySQL(strSql);
                double sjse = 0;
                while (rs.next())
                {
                    HashMap map = new HashMap();
                    //JksUtil.getJkpzh(hzlx,java.sql.Date.valueOf(hzjsrq))
                    map.put("jsjdm", rs.getString("jsjdm"));
                    map.put("szsmdm", rs.getString("szsmdm"));
                    map.put("szdm", rs.getString("szdm"));
                    map.put("skssksrq", rs.getString("skssksrq"));
                    map.put("skssjsrq", rs.getString("skssjsrq"));
                    map.put("sjse", String.valueOf(rs.getBigDecimal("sjse")));
                    map.put("kssl", String.valueOf(rs.getBigDecimal("kssl")));
                    map.put("jsje", String.valueOf(rs.getBigDecimal("jsje")));
                    sjse = sjse + rs.getDouble("sjse");
                    sbjkmxList.add(map);
                }
                rs.close();
                //��д�걨�ɿ�����
                if (sbjkmxList.size() <= 0)
                {
                    pf.setDataList(dataList);
                    pf.setSbhzdh("");
                    pf.setHjje("0");
                    pf.setHjzs("0");
                    throw new ApplicationException("û�з�����������˰֤���Ի��ܣ�");
                }
                else
                {
                    HashMap map = (HashMap) sbjkmxList.get(0);
                    //sbjkzb.setYhdm("");��ʱһ˰һƱ��ʶ
                    sbjkzb.setJsjdm(String.valueOf(map.get("jsjdm")));
                    sbjkzb.setSjje(new BigDecimal(String.valueOf(sjse)));
                    //����������Ϣ
                    HashMap mapDJ = new HashMap();
                    try
                    {
                        mapDJ = (HashMap) InterfaceDj.getDjInfo(sbjkzb.getJsjdm(),
                            ud);
                    }
                    catch (Exception ex1)
                    {
                        ex1.printStackTrace();
                        throw ExceptionUtil.getBaseException(ex1);
                    }
                    //Modified by lufeng 2003-12-09
                    SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
                    if (jbsj == null)
                    {
                        throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
                    }

                    //�Ǽ�ע�����ͣ������˰������ʹ��Ĭ��ֵ
                    if (hzlx.equals(CodeConstant.HZFS_DW))
                    {
                        sbjkzb.setDjzclxdm(CodeConstant.ZRR_JKS_DJZCLXDM); //Ĭ��410
                        sbjkzb.setDjzclxmc(jbsj.getDjzclxmc());
                        //���ұ�׼��ҵ����
                        sbjkzb.setGjbzhydm(CodeConstant.ZRR_JKS_GJBZHYDM); //Ĭ��8190
                        sbjkzb.setGjbzhymc(jbsj.getGjbzhymc());
                    }
                    else
                    { //���˻���ʹ��
                        sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //��Ĭ��
                        sbjkzb.setDjzclxmc(jbsj.getDjzclxmc());
                        //���ұ�׼��ҵ����
                        sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //��Ĭ��
                        sbjkzb.setGjbzhymc(jbsj.getGjbzhymc());
                    }
                    //˰�������֯����
                    sbjkzb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
                    sbjkzb.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
                    //���ջ���
                    sbjkzb.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
                    sbjkzb.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
                    //������ϵ
                    sbjkzb.setLsgxdm(jbsj.getLsgxdm());
                    sbjkzb.setLsgxmc(jbsj.getLsgxmc());
                    //��Ӫ��ַ��ϵ�绰
                    sbjkzb.setJydzlxdm(jbsj.getJydzlxdm());
                    //�������ɽɿ��鲻����������Ϣ Modifyed by wuyouzhi 2006.2.7
//                    //�õ�������Ϣ//Modified by lufeng 2003-12-09
//                    ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
//                    for (int i = 0; i < dmList.size(); i++)
//                    {
//                        YHZH yhzh = (YHZH) dmList.get(i);
//                        if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
//                        {
//                            sbjkzb.setYhdm(yhzh.getYhdm()); //���д���
//                            sbjkzb.setYhmc(yhzh.getKhyhmc()); //��������
//                            sbjkzb.setZh(yhzh.getZh()); //�ʻ�
//                            break;
//                        }
//                    }

                    //¼����
                    sbjkzb.setLrr(pf.getLrr());
                    //�걨����
                    sbjkzb.setSbrq(nowTime);
                    //��������
                    sbjkzb.setCjrq(nowTime); //��������
                    sbjkzb.setLrrq(nowTime); //¼������
                    sbjkzb.setQxdm(qxdm); //���ش���
                    //������Դ
                    sbjkzb.setSjly(CodeConstant.SMSB_SJLY_GTGSHHZ);
                    //˰�����ʹ���
                    sbjkzb.setSklxdm(CodeConstant.SKLXDM_GTGSH); //Ĭ��Ϊ120

                    sbjkzb.setRkje(new BigDecimal("0"));
                    sbjkzb.setLrr(pf.getLrr());
                    sbjkzb.setClbjdm(CodeConstant.CLBJDM_WCL); //�걨 �����Ǵ���

                    //�걨��ʽ����
                    sbjkzb.setFsdm(CodeConstant.FSDM_SMSB);
                    //�����ʶ,���� zwbs��18��19λ����01���ֽ�ɿ�,
                    sbjkzb.setZwbs("00000000000000000010");
                } //End of if
            }
            catch (Exception sqlEx)
            {
                sqlEx.printStackTrace();
                throw ExceptionUtil.getBaseException(sqlEx);
            }
            finally
            {
            } //End of try

            if (sbjkmxList.size() > 0)
            {
                //���ú������в������
                //ѭ�����ɽɿ���Ϣ���������걨�ɿ���������ݸ���,���ؽɿ���Ϣ
                JksUtil jksUtil = new JksUtil();
                //һ˰һƱ /һ˰��Ʊ�������Ŀ
                try
                {
                    if (ypys == CodeConstant.PRINT_YPYS)
                    {
                        sbjkzbList = jksUtil.getJkDataLS(sbjkzb, sbjkmxList,
                            CodeConstant.PRINT_YPYS);
                    }
                    else
                    {
                        sbjkzbList = jksUtil.getJkDataLS(sbjkzb, sbjkmxList,
                            CodeConstant.PRINT_YPDS_KM);
                    }
                }
                catch (Exception ex2)
                {
                    ex2.printStackTrace();
                    throw new ApplicationException("�����걨�ɿ���Ϣ����");
                }

                String sbhzdh = jksUtil.getSequenceOfSbhzd(conn); //��ȡ�걨���ܵ���
                //�ѻ�������д����˰֤���ܱ�
                DeclareInfor dclInfo = new DeclareInfor();
                if (ypys == CodeConstant.PRINT_YPYS)
                { //һƱһ˰
                    for (int i = 0; i < sbjkzbList.size(); i++)
                    {
                        List hzTmpList = new ArrayList();
                        dclInfo = (DeclareInfor) sbjkzbList.get(i);
                        sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
                        hjje = hjje
                               + StringUtil.getDouble( (sbjkzb.getSjje()).toString(),
                            0);
                        //д����ܱ�
                        try
                        {
                            hzTmpList = (List) updateHZ(da, sbjkzb, sbhzdh,
                                nowTime, pf);
                        }
                        catch (Exception ex)
                        {
                            throw ExceptionUtil.getBaseException(ex);
                        }

                        dataList = (List) ListUtils.union(dataList, hzTmpList);
                    }
                }
                else if (ypys == CodeConstant.PRINT_YPDS_KM)
                { //һƱ��˰

                    for (int i = 0; i < sbjkzbList.size(); i++)
                    {
                        List onePageList = (List) sbjkzbList.get(i);
                        List tmpList = new ArrayList();
                        for (int j = 0; j < onePageList.size(); j++)
                        {
                            List hzTmpList = new ArrayList();
                            dclInfo = (DeclareInfor) onePageList.get(j);
                            sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
                            hjje = hjje
                                   + StringUtil.getDouble( (sbjkzb.getSjje()).toString(),
                                0);
                            try
                            {
                                hzTmpList = (List) updateHZ(da, sbjkzb, sbhzdh,
                                    nowTime, pf);
                            }
                            catch (BaseException ex4)
                            {
                                throw ExceptionUtil.getBaseException(ex4);
                            }
                            tmpList = (List) ListUtils.union(tmpList, hzTmpList);
                        } //End of inside loop
                        //����ÿ�����ѭ����������Ϣ
                        dataList = (List) ListUtils.union(dataList, tmpList);
                    } //End of outside loop
                } //End of if

                //������ܵ��ŵ�
                strSql = "update sbdb.sb_jl_gtgshwszz a set a.sbhzdh='"
                         + sbhzdh + "'," +
                         " a.lrrq=sysdate " +
                         " where a.qxdm='" + qxdm + "' " + timeCon + whereCon +
                         " and a.clbjdm='" + CodeConstant.SMSB_WSZ_PRINT + "'" +
                         " and (a.sbhzdh = '' or a.sbhzdh is null) "+insql;
                try
                {
                    da.updateSQL(strSql);
                }
                catch (BaseException ex3)
                {
                    ex3.printStackTrace();
                    throw new ApplicationException("������ܵ��ų���");
                }

                //����һƱ��˰���б��Ա���ʾ
                ArrayList detailList = (ArrayList) dataList;
                detailList = (ArrayList) JksUtil.getYpdsList(detailList);
                //�ش�dataList
                pf.setDataList(detailList);
                pf.setSbhzdh(sbhzdh);
                
                pf.setHjje(StringUtil.getCurrency(String.valueOf(hjje)));
                pf.setHjzs(String.valueOf(dataList.size()));
                System.out.println("GtgshHzjksProcessor.doSave##########sbjkzb.getSbbh()="+sbjkzb.getSbbh()); 
                pf.setSbbh(sbjkzb.getSbbh());
                
                //�����걨��� �� �걨���ܵ���
                HashMap bhMap = new HashMap();
                bhMap.put("sbbh", sbjkzb.getSbbh());
                bhMap.put("sbhzdh", sbhzdh);
                bhMap.put("hjje", pf.getHjje());
               // bhMap.put("hjzs", pf.getHjzs());
                bhMap.put("dataList", pf.getDataList());
                pf.getBhList().add(bhMap);
                
            } //End of if

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
     * ɾ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doUpdate (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ���»��ܱ���Ϣ�������ܳɹ�����Ҫ��д���ܱ�
     * @param sfda ���ݿ��������
     * @param sbjkzb �걨�ɿ��������
     * @param sbhzdh �걨���ܵ���
     * @param nowTime ��ǰʱ��
     * @param pf ��ǰҳ���Form����
     * @return ���ܺ����ɵĽɿ����б�
     * @throws BaseException
     */
    private List updateHZ (SfDBAccess sfda, Sbjkzb sbjkzb, String sbhzdh,
                           Timestamp nowTime, GtgshHzjksForm pf)
        throws
        BaseException
    {

        List tempList = new ArrayList();
        Gtgshwszsbhz hz = new Gtgshwszsbhz();
        try
        {
            //sbjkzb = (Sbjkzb) dclInfo.getSbjkzb();
            hz.setSbhzdh(sbhzdh);
            hz.setJsjdm(sbjkzb.getJsjdm());
            hz.setJkpzh(sbjkzb.getJkpzh());
            hz.setHzrq(nowTime); //��������
            hz.setCjrq(nowTime); //��������
            hz.setLrrq(nowTime); //¼������
            hz.setQxdm(sbjkzb.getQxdm()); //���ش���
            hz.setSjse(sbjkzb.getSjje());
            hz.setHzksrq(Timestamp.valueOf(setFormatDate(pf.getHzksrq()) +
                                           " 00:00:00"));
            hz.setHzjsrq(Timestamp.valueOf(setFormatDate(pf.getHzjsrq()) +
                                           " 23:59:59"));
            hz.setClbjdm(CodeConstant.CLBJDM_WCL); //�걨 �����Ǵ���
            hz.setSwjgzzjgdm(pf.getSwjgzzjgdm());
            hz.setLrr(pf.getLrr());
            //�������
            hz.setNd(String.valueOf(SfDateUtil.getYear(SfDateUtil.getDate())));

            //���˻��� / ����λ����
            if ( (pf.getHzlx()).equals(CodeConstant.HZFS_DW))
            {
                hz.setHzfs(CodeConstant.HZFS_DW);
            }
            if ( (pf.getHzlx()).equals(CodeConstant.HZFS_GR))
            {
                hz.setHzfs(CodeConstant.HZFS_GR);
            }
            //д����
            sfda.insert(hz);
            HashMap map = new HashMap();
            String strJkpzh = sbjkzb.getJkpzh();
            map.put("jkpzh_ypds", strJkpzh.substring(0, strJkpzh.length() - 1));
            //һƱһ˰��һƱ��˰
            int ypys = StringUtil.getInt(pf.getYpys(), 1);
            if (ypys == CodeConstant.PRINT_YPYS)
            {
                map.put("jkpzh", sbjkzb.getJkpzh());
            }
            else
            {
                map.put("jkpzh", map.get("jkpzh_ypds"));
            }
            map.put("sjse", String.valueOf(sbjkzb.getSjje()));
            tempList.add(map);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("������˰֤������Ϣ����");
        }
        return tempList;
    } //End of updateHZ

    /**
     * ʱ��ת��������getFormatDate������������
     * �磺20080808 00:00:00 ת��Ϊ2008-08-08 00:00:00
     * ���ߣ�20080808 ת��Ϊ2008-08-08
     * @param inTime ���ڻ�����ʱ���ַ���
     * @return ��ʽ����������ַ���
     * @throws BaseException
     */
    private static String setFormatDate (String inTime)
        throws BaseException
    {
        if (inTime == null || inTime.equals(""))
        {
            return inTime;
        }
        String result = "";
        try
        {
            String tempStr = inTime.substring(0, 4);
            String defStr = inTime.substring(4, 6);
            result = tempStr + "-" + defStr + "-" + inTime.substring(6);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return result;
    } //End of setFormatDate

}

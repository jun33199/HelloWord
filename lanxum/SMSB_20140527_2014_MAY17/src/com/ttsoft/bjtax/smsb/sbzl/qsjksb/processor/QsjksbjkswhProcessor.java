/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.qsjksb.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.shenbao.model.client.SBData;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.qsjksb.web.QsjksbjkswhActionForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjkswhActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import java.util.Vector;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ��Ƿ˰�ɿ��걨���ܣ������ɿ���¼�룬ά����</p>
 * @author Zhang Yijun
 * @version 1.1
 */

public class QsjksbjkswhProcessor
    implements Processor
{
  public QsjksbjkswhProcessor()
  {
  }

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException
  {
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      case CodeConstant.SMSB_QUERYACTION1:
          return doQueryCx(vo);
      case CodeConstant.SMSB_UPDATEACTION:
        return doCx(vo);

      default:
        return null;
    }

  }

  /**
   * ��ʾ�ü���������Ӧ������δ���ɿ���
   *
   * @param     vo ҵ�����
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm������
   * @throws BaseException ҵ���쳣
   */
  private QsjksbjkswhActionForm doQuery(VOPackage vo) throws BaseException
  {

    //�������ݿ�����
    Connection conn = null;

    try
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData(); //��ǰ�˻��map����
      //�õ�userdata
      UserData userData = vo.getUserData();
      //�õ����ش���
      String qxdm = InterfaceDj.getQxdm(userData);
      String jsjdm = form.getJsjdm(); //��ȡ�û��ļ��������
      //������Դ
      String sjly = form.getSjly();
      if (sjly == null || sjly.equals(""))
      {
        //δָ�������������Դ������ȱʡ��Ϊ����Ƿ˰�γɵ��걨¼��
        sjly = CodeConstant.SMSB_SJLY_BJQS;
        
      }
      //������ݿ�����
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      //��� ORManager


      //���ò�ѯ����(����ǻ���һ�����û�,�����ش����ѯ����)
      Vector tempSql = new Vector();
      tempSql.add("LRR='" + userData.getYhid() + "'");
      
      //���ӻ������ľ�Ȩ�޹������� modified by zhangyj 20140723     
      if(!(qxdm.endsWith("25")||qxdm.endsWith("41")||qxdm.endsWith("24")||qxdm.endsWith("45"))){
      tempSql.add("QXDM='" + qxdm + "'");
      }
      tempSql.add("JSJDM='" + jsjdm + "'");
      tempSql.add("SJLY='" + sjly + "'");
      tempSql.add("FSDM='" + CodeConstant.FSDM_SMSB + "'");
      tempSql.add("ZWBS LIKE '0%0'");
       //����걨��Ϊ�գ���ֻ��ʾ���걨��ŵĽɿ����б�
      
      if(form.getSbbhList()!=null && form.getPresbbh()!= null && form.getPresbbh().equals("1"))
      {
    	  String sbbhStr="";
    	  ArrayList list=(ArrayList) form.getSbbhList();
    	  for(int i=0;i<list.size();i++){
    		  sbbhStr=sbbhStr+"'"+list.get(i)+"',";
    	  }
    	  sbbhStr = sbbhStr.substring(0, sbbhStr.length()-1);
    	  tempSql.add("SBBH in(" + sbbhStr + ")");    	  
      }    
      tempSql.add("ZYRQ >= to_date('20050101','yyyymmdd')");
      tempSql.add("ND=to_char(sysdate,'yyyy')");
      tempSql.addElement("1=1 ORDER BY SBRQ DESC,SBBH,JKPZH ASC");
      //long t = System.currentTimeMillis();
      //Debug.out("tempSql******************"+tempSql);
      
      List tempList = da.query(Sbjkzb.class, tempSql);

      //Debug.out("doQuery time cost:" + (System.currentTimeMillis() - t));

      
      //���˰�����ƺ������б�
      HashMap sbmaps = new HashMap();
      SBData sb = null;
      for (int i = 0; i < tempList.size(); i++)
      {
        //��ʽ��ÿ����¼
        Sbjkzb sbjkzb = (Sbjkzb) tempList.get(i);
        sbjkzb.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM",
                                                  sbjkzb.getSzdm())); //˰������
        sb = (SBData)sbmaps.get(sbjkzb.getSbbh());
        if (sb == null)
        {
          sb = new SBData();
          sbmaps.put(sbjkzb.getSbbh(),sb);
        }
        sb.addSbjkzb(sbjkzb);
      }

      //��mapתΪlist
      Iterator c = sbmaps.values().iterator();
      ArrayList datalist = new ArrayList();

      while (c.hasNext())
      {
        datalist.add(c.next());
      }
      //��ֵ�Ż�form����

      form.setDataList(datalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }


  /**
   * ���ظü���������Ӧ������δ���ɿ���ĳ�������
   *
   * @param     vo ҵ�����
   * @return ZhsbjkswhActionForm ZhsbjkswhActionForm������
   * @throws BaseException ҵ���쳣
   */
  private QsjksbjkswhActionForm doQueryCx(VOPackage vo) throws BaseException
  {

    //�������ݿ�����
    Connection conn = null;
    ResultSet rs=null;

    //������ݿ�����
    conn = SfDBResource.getConnection();
    SfDBAccess da = new SfDBAccess(conn);
    try
    {
    	QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData();
    	List datalist=form.getDataList();
    	List nlwdatalist=form.getNlwDataList();
    	HashMap map = null;
    	String sql = null ;
    	String firstchar=null;
    	String lastchar=null;
    	
    	if(datalist.size()!=0){
    		for(int i=0;i<datalist.size();i++){
    		map=(HashMap)datalist.get(i);
    		String sbbh=String.valueOf(map.get("sbbh"));
    		sql="select * from sbdb.sb_jl_sbjkzb where sbbh='"+sbbh+"'and zwbs not like '0%0'";
    		rs=da.querySQL(sql);
    		if(rs.next()){
    			map.put("cxbs","1");    			
    		}else{
    			map.put("cxbs","0");
    		}
    		}
    	}
    	if(nlwdatalist.size()!=0){
    		for(int j=0;j<nlwdatalist.size();j++){
    			map=(HashMap)nlwdatalist.get(j);
        		String jkpzh=String.valueOf(map.get("jkpzh"));
        		sql="select t.jkpzh,p.znjjkpzh dzjkpzh,(select zwbs from sbdb.sb_jl_sbjkzb where jkpzh=p.znjjkpzh) dzzwbs,p.jkpzh pzh,p.qs_xh qsxh,p.sbje" 
        			+" from sbdb.sb_jl_sbjkzb t,jkdb.kj_jl_sb_qs_dz p where t.jkpzh=p.jkpzh and t.jkpzh='"+jkpzh+"' union " 
        			+"select t.jkpzh,p.jkpzh dzjkpzh,(select zwbs from sbdb.sb_jl_sbjkzb where jkpzh=p.jkpzh) dzzwbs,p.jkpzh pzh,p.qs_xh qsxh,p.sbje"  
        				+" from sbdb.sb_jl_sbjkzb t,jkdb.kj_jl_sb_qs_dz p where t.jkpzh=p.znjjkpzh and t.jkpzh='"+jkpzh+"'";
        		rs=da.querySQL(sql);
        		while(rs.next()){
        			if(rs.getString("dzjkpzh")==null){
        				map.put("dzjkpzh","");
        			}else{
            			map.put("dzjkpzh",rs.getString("dzjkpzh"));       				
        			}
        			if(rs.getString("dzzwbs")==null){
        				map.put("cxbs","0");
        			}else{
        				firstchar=rs.getString("dzzwbs").toString().substring(0, 1);
        				lastchar=rs.getString("dzzwbs").toString().substring(19, 20);
        				if(firstchar.equals("0")&&lastchar.equals("0")){
        					map.put("cxbs","0");
        				}else{
        					map.put("cxbs","1");
        				}
        			}       			
        			map.put("pzh",rs.getString("pzh"));
        			map.put("qsxh",rs.getString("qsxh"));
        			map.put("sbje",rs.getString("sbje"));
        		}    			
    		}
    	}
    	form.setDataList(datalist);   	
    	form.setNlwDataList(nlwdatalist);
      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }  
  
  
  /**
   * ����ָ���ɿ��� ���걨
   *
   * @param     vo ҵ�����
   * @return ArrayList ���˰�����Ƶ��걨�ɿ������б�
   * @throws BaseException ҵ���쳣
   */
  
  private QsjksbjkswhActionForm doCx(VOPackage vo) throws BaseException
  {
	  QsjksbjkswhActionForm form = (QsjksbjkswhActionForm) vo.getData(); //��ǰ�˻��map����
    //�õ�userdata
    UserData userData = vo.getUserData();
    
    /**
     * �����־
     */
    
    TinyTools.makeLog4ZhsbCx(userData, form.getJkpzhStr());
    Connection conn = null;
    String sql=null;

    
    //������ݿ�����
    conn = SfDBResource.getConnection();
    SfDBAccess da = new SfDBAccess(conn);
    try
    {
      if (form.getJksType() == CodeConstant.PRINT_YPYS)
      { 
    	  
          //�޸�Ƿ˰��ϸ����
          sql="update jkdb.kj_jl_qsmx set qjje=qjje+'"+form.getSbje()+"' ,sbje=sbje-'"+form.getSbje()+"'where xh='"+form.getQsxh()+"'";
          int nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //���û�п��޸�������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ��Ƿ˰��ϸ���ݣ�");
          }
          
          //ɾ�����ձ�����
          sql="delete from jkdb.kj_jl_sb_qs_dz where jkpzh='"+form.getDzjkpzh()+"' and qs_xh='"+form.getQsxh()+"'";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //���û�п�ɾ��������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ��Ƿ˰�걨��������");
          }    	  

        //ɾ������ִ�м�¼��ϸ������
        sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh='"+form.getJkpzhStr()+"'";
        da.updateSQL(sql);          
        //ɾ����ϸ������
        sql="delete from sbdb.sb_jl_sbjkmx where jkpzh='"+form.getJkpzhStr()+"'";
        da.updateSQL(sql);
        //ɾ����������
        sql="delete from sbdb.sb_jl_sbjkzb where jkpzh='"+form.getJkpzhStr()+"' and zwbs like '" +CodeConstant.SMSB_ZWBS + "%" +
                                CodeConstant.SMSB_ZWBS + "'";
        nret = da.updateSQL(sql);
        if (nret == 0)
        {
          //���û�п�ɾ��������ع�
          throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ���걨�ɿ��������ݣ�");
        }

        //ɾ�������ɿ�������
        if(!form.getGljkpzh().equals("")){
            //ɾ������ִ�м�¼��ϸ������
            sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh='"+form.getGljkpzh()+"'";
            da.updateSQL(sql);         	
            //ɾ����ϸ������
            sql="delete from sbdb.sb_jl_sbjkmx where jkpzh='"+form.getGljkpzh()+"'";
            da.updateSQL(sql);
            //ɾ����������
            sql="delete from sbdb.sb_jl_sbjkzb where jkpzh='"+form.getGljkpzh()+"' and zwbs like '" +CodeConstant.SMSB_ZWBS + "%" +
                                    CodeConstant.SMSB_ZWBS + "'";
            nret = da.updateSQL(sql);
            if (nret == 0)
            {
              //���û�п�ɾ��������ع�
              throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ���걨�ɿ������������");
            }        	
        }

      }
      else
      {
          //�޸�Ƿ˰��ϸ����
          sql="update jkdb.kj_jl_qsmx a set a.qjje = a.qjje + (select t.sbje from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"'),"
          +"a.sbje = a.sbje - (select t.sbje from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"')"
          +"where a.xh = (select t.qs_xh from jkdb.kj_jl_sb_qs_dz t where a.xh = t.qs_xh and t.sbbh = '"+form.getSbbh()+"')";
          int nret=da.updateSQL(sql); 
          if (nret == 0)
          {
            //���û�п��޸�������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ��Ƿ˰��ϸ���ݣ�");
          }
          
          //ɾ�����ձ�����
          sql="delete from jkdb.kj_jl_sb_qs_dz where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //���û�п��޸�������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ��Ƿ˰�걨�������ݣ�");
          }
    	  
          //ɾ������ִ�м�¼��ϸ������
          sql="delete from jcdb.jc_jl_ajzxjlmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          da.updateSQL(sql);                
          
          //ɾ����ϸ������
    	  sql="delete from sbdb.sb_jl_sbjkmx where jkpzh in (select jkpzh from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "' and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "')";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //���û�п��޸�������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ���걨�ɿ���ϸ������");
          }
          
          //ɾ����������
          sql="delete from sbdb.sb_jl_sbjkzb where jsjdm='"
           + form.getJsjdm() + "'  and sbbh='" + form.getSbbh() +
          "'  AND zwbs like '" + CodeConstant.SMSB_ZWBS + "%" +
          CodeConstant.SMSB_ZWBS + "'  ";
          nret=da.updateSQL(sql);
          if (nret == 0)
          {
            //���û�п��޸�������ع�
            throw new ApplicationException("�����ɿ���ʧ�ܣ��޶�Ӧ���걨�ɿ���������");
          }

      }

    }
    catch (Exception ex)
    {
      throw ExceptionUtil.getBaseException(ex, "�����ɿ���ʧ��!");
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }
    return form;
  }

}

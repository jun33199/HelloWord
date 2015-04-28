package com.ttsoft.bjtax.smsb.gdzys.sbzs.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java_cup.internal_error;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sbzs.web.GdzysCjjksForm;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysDateUtil;
import com.ttsoft.bjtax.smsb.gdzys.util.GdzysMoneyUtils;
import com.ttsoft.bjtax.smsb.model.client.DeclareInfor;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ--����ռ��˰���չ���</p>
 * <p>Description: ���߽ɿ���AProcessor</p>
 * @author wangxq
 * @version 1.0
 */
public class GdzysCjjksProcessor  implements Processor{
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
            case GdzysCodeConstant.SMSB_GDZYS_CKJKSACTION:
                result = doCkJks(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }
	/**
     * ��ѯ�ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    	//��ȡform
    	GdzysCjjksForm pf = (GdzysCjjksForm) vo.getData();
        List dataList = new ArrayList();
         //�������ݿ�
        Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    
	    try
        {
	    
	    String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"SBDB", "SB_JL_GDZYS_SBB" );
	    
	    System.out.println("##########datafilter="+datafilter);
	    
	    StringBuffer sqlBuf = new StringBuffer();
        
	    sqlBuf.append("select * from (");
	    //sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='0' and QRZT='1' and QRRQ is not null and CJR='"+ud.yhid+"' "+" and SFJMSSB='0' ");//
	    sqlBuf.append("select k.*,'1' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='0' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and k.SFJMSSB='0' and "+datafilter+" ");//�޼���
	   // sqlBuf.append(" union all ");
	    //sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='0' and SBZT='40' and SFSJSP='1' and SFJMSSB='0' and CJR='"+ud.yhid+"' ");//�о��޼���
	    sqlBuf.append(" union all ");
	    sqlBuf.append("select k.*,'2' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='2' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and "+datafilter+" and k.SFJMSSB='1' and k.SBBXLH not in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) and k.SBBXLH in (select SBBXLH from sbdb.SB_JL_GDZYS_JMSZM where DYBZ='1' and ZFBZ='0' and KJRQ is not null ) ");//�м����������֤��
	    sqlBuf.append(" union all ");
	    sqlBuf.append("select k.*,'3' flag from sbdb.SB_JL_GDZYS_SBB k where k.SYLXDM='2' and ((k.SBZT='30' and k.SFSJSP='0' and k.QRRQ is not null ) or (k.SBZT='40' and k.SFSJSP='1' and k.SJQRRQ is not null )) and "+datafilter+" and k.SFJMSSB='1' and k.SBBXLH  in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) and k.SBBXLH not in (select SBBXLH from sbdb.SB_JL_GDZYS_JMSZM where DYBZ='1' and ZFBZ='0' and KJRQ is not null ) ");//�м������������֤��
	   // sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where SYLXDM='2' and QRZT='1' and QRSJ is not null and CJR='"+ud.yhid+"' "+" and SFJMSSB='1' and SBBXLH in (select SBBXLH from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02')) ");//�м��ⲻ�������֤��
	    sqlBuf.append(") t where 1=1 ");
	    
        if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
        	sqlBuf.append(" and t.NSRMC like ?");
        }
        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
        	sqlBuf.append(" and t.JSJDM like ?");
        }
        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
        	sqlBuf.append(" and t.PZJDWH like ?");
        }
        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
        	sqlBuf.append(" and t.SBBXLH like ?");
        }

        System.out.println("#########="+sqlBuf.toString());
      
            conn = SfDBResource.getConnection();
            ps = conn.prepareStatement(sqlBuf.toString());   
            //���������
		    int i=1;
		    if(pf.getNsrmc()!=null&&!pf.getNsrmc().equals("")){
		    	 ps.setString(i++, "%"+pf.getNsrmc()+"%");
	        }
	        if(pf.getJsjdm()!=null&&!pf.getJsjdm().equals("")){
	        	 ps.setString(i++, "%"+pf.getJsjdm()+"%");
	        }
	        if(pf.getPzjdwh()!=null&&!pf.getPzjdwh().equals("")){
	        	 ps.setString(i++, "%"+pf.getPzjdwh()+"%");
	        }
	        if(pf.getSbbxlh()!=null&&!pf.getSbbxlh().equals("")){
	        	 ps.setString(i++, "%"+pf.getSbbxlh()+"%");
	        }
            
            rst = ps.executeQuery();
            while (rst.next()) {
            	HashMap map = new HashMap();
            	//�걨�����к�
            	map.put("sbbxlh", rst.getString("sbbxlh")==null?"":rst.getString("sbbxlh"));
            	//��˰������
            	map.put("nsrmc", rst.getString("nsrmc")==null?"":rst.getString("nsrmc"));
            	//���������
            	map.put("jsjdm", rst.getString("jsjdm")==null?"":rst.getString("jsjdm"));
            	//��׼ռ���ĺ�
            	map.put("pzjdwh", rst.getString("pzjdwh")==null?"":rst.getString("pzjdwh"));
            	//ʵ��ռ�����
            	map.put("sjzdmj", rst.getBigDecimal("sjzdmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("sjzdmj").doubleValue()));
            	//��˰���
            	map.put("jsmj", rst.getBigDecimal("jsmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jsmj").doubleValue()));
            	//�������
            	map.put("jmmj", rst.getBigDecimal("jmmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jmmj").doubleValue()));
            	//Ӧ��˰��
            	map.put("ynse", rst.getBigDecimal("ynse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ynse").doubleValue()));
            	//ȷ��ʱ��
            	map.put("qrrq", rst.getString("qrrq")==null?"":(rst.getString("qrrq").substring(0, 10)));
            	//��������
            	map.put("cjrq", rst.getString("cjrq")==null?"":(rst.getString("cjrq").substring(0, 10)));
            	//�Ƿ��о�����
            	map.put("sfsjsp",rst.getString("sfsjsp")==null?"":rst.getString("sfsjsp"));
            	//�о�ȷ������
            	map.put("sjqrrq",rst.getString("sjqrrq")==null?"":(rst.getString("sjqrrq").substring(0, 10)));
            	//flag 1:�걨�����޼���ɿ������ݣ�2���걨�����м����������֤���ɿ������ݣ�3���걨�����м������������֤���ɿ�������
            	map.put("flag", rst.getString("flag"));
            	dataList.add(map);
            }
            
            System.out.println("########dataList.size()="+dataList.size());
            
            //��ֵ�Ż�form����
            pf.setDataList(dataList);
            //�ر�resultset
		    rst.close();
		    //�ر�preparestatement
		    ps.close();

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
     * �鿴�ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doCkJks (VOPackage vo)
        throws BaseException
    {
    	UserData ud = (UserData) vo.getUserData(); //�ĵ���ǰ�û�����
    	//��ȡform
    	GdzysCjjksForm pf = (GdzysCjjksForm) vo.getData();
        List dataList = new ArrayList();
        
        
         //�������ݿ�
        Connection conn = null;
        PreparedStatement ps = null;
        //����� 
	    ResultSet rst = null;
        //UserData ud = new UserData();
	    StringBuffer sqlBuf = new StringBuffer();
	    //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        String qxdm = ""; //���ش���
	    Timestamp nowTime = new Timestamp(System.currentTimeMillis());
	    
	    sqlBuf.append("select t.*,(select jsjdm from sbdb.SB_JL_GDZYS_SBB where sbbxlh=t.sbbxlh) jsjdm from sbdb.SB_JL_GDZYS_SBBJKSGLB t where t.SPLX='1' and  t.SBBXLH=?");
        System.out.println("#########="+sqlBuf.toString());
        System.out.println("#########pf.getCxSbbxlh="+pf.getCxSbbxlh());
        try
        {   
            conn = SfDBResource.getConnection();
            
            //�Ƿ���Ҫ�����ɼ����걨
            dealWithJMSB(pf,ud,conn);
            
            ps = conn.prepareStatement(sqlBuf.toString());   
		    int i=1;
		    ps.setString(i++, pf.getCxSbbxlh());
            rst = ps.executeQuery();
           //�нɿ���
            if(rst.next()) {
            	System.out.println("#################1");
            	HashMap tepmmap = new HashMap();
            	//�걨�����к�
            	tepmmap.put("sbbxlh", rst.getString("sbbxlh"));
            	//��˰������
            	tepmmap.put("jkpzh", rst.getString("jkpzh"));
            	
            	//���������
            	tepmmap.put("jsjdm", rst.getString("jsjdm"));
            	dataList.add(tepmmap);
            	pf.setDataList(dataList);
            //�޽ɿ���	
            }else{
            	System.out.println("#################2");
            	sqlBuf=new StringBuffer();
            	//sqlBuf.append("select *,(select * from sbdb.SB_JL_GDZYS_SBBJMMX where JMSLBDM in ('01','02') and SBBXLH=?) from sbdb.SB_JL_GDZYS_SBB where  SBBXLH=?");
            	//sqlBuf.append("select * from sbdb.SB_JL_GDZYS_SBB where  SBBXLH=?");
            	
            	//�걨�����޼���ɿ��飬�м������������֤���ɿ���
            	if(pf.getFlag().equals("1")||pf.getFlag().equals("3")){
            		//sqlBuf.append("select t.*,(case when t.SFSJSP = '0' then (t.qrrq+30) else (t.sjqrrq+30) end) xjrq from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            		sqlBuf.append("select t.*,(select djzclxmc from  dmdb.dj_dm_djzclx where djzclxdm=t.qydjzclx and rownum<=1) djzclxmc,(select gjbzhymc from  dmdb.gy_dm_gjbzhy where gjbzhydm=t.nsrsshy and rownum<=1) gjbzhymc,(t.qrrq+30) xjrq from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            	//�걨�����м����������֤���ɿ���
            	}else if(pf.getFlag().equals("2")){
            		sqlBuf.append("select t.*,(select djzclxmc from  dmdb.dj_dm_djzclx where djzclxdm=t.qydjzclx and rownum<=1) djzclxmc,(select gjbzhymc from  dmdb.gy_dm_gjbzhy where gjbzhydm=t.nsrsshy and rownum<=1) gjbzhymc,(select (b.KJRQ+30) from (select (a.KJRQ) from sbdb.SB_JL_GDZYS_JMSZM a where a.DYBZ='1' and a.ZFBZ='0' and a.KJRQ is not null and a.SBBXLH=? order by a.cjrq desc ) b where rownum<=1 ) xjrq  from sbdb.SB_JL_GDZYS_SBB t where  t.SBBXLH=?");
            	}
                System.out.println("############sqlBuf.toString()="+sqlBuf.toString());
            	
            	ps = conn.prepareStatement(sqlBuf.toString());   
    		    i=1;
    		    if(pf.getFlag().equals("1")||pf.getFlag().equals("3")){
     	    	   ps.setString(i++, pf.getCxSbbxlh());
     		    }else if(pf.getFlag().equals("2")){
     		       ps.setString(i++, pf.getCxSbbxlh());
     	    	   ps.setString(i++, pf.getCxSbbxlh());
     		    }	
    		    
                rst = ps.executeQuery();
                HashMap map = new HashMap();
                if(rst.next()) {
                	//�걨�����к�
                	map.put("sbbxlh", rst.getString("sbbxlh"));
                	//��˰������
                	map.put("nsrmc", rst.getString("nsrmc"));
                	//���������
                	map.put("jsjdm", rst.getString("jsjdm"));
                	//��׼ռ���ĺ�
                	map.put("pzjdwh", rst.getString("pzjdwh")==null?"":rst.getString("pzjdwh"));
                	//��˰���
                	map.put("jsmj", rst.getBigDecimal("jsmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jsmj").doubleValue()));
                	//Ӧ˰���
                	map.put("ysmj", rst.getBigDecimal("ysmj")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ysmj").doubleValue()));
                	//����˰��
                	map.put("jzse", rst.getBigDecimal("jzse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("jzse").doubleValue()));
                	//Ӧ��˰��
                	map.put("ynse", rst.getBigDecimal("ynse")==null?"0":GdzysMoneyUtils.format(rst.getBigDecimal("ynse").doubleValue()));
                	//����ȷ������
                	map.put("qrrq", rst.getTimestamp("qrrq"));
                	//�о�ȷ������
                	map.put("sjqrrq", rst.getTimestamp("sjqrrq"));
                	//map.put("qrsj", rst.getString("qrsj")==null?"":(rst.getString("qrsj").substring(0, 10)));
                	//�Ƿ��о�����
                	map.put("sfsjsp", rst.getString("sfsjsp"));
                	//���д���
                	map.put("khyhdm", rst.getString("khyhdm"));
                    //��������
                	map.put("khyhmc", rst.getString("khyhmc"));
                    //�ʻ�
                	map.put("yhzh", rst.getString("yhzh"));
                	
                	//�޽�����
                	map.put("xjrq", rst.getTimestamp("xjrq"));
                	
                	
                	
                	//��˰������ 0:��ҵ��˰�� 1:������˰��
                	map.put("nsrlx", rst.getString("nsrlx"));
                	//��ҵ�Ǽ�ע������
                	map.put("qydjzclx", rst.getString("qydjzclx"));
                	//��ҵ�Ǽ�ע����������
                	map.put("djzclxmc", rst.getString("djzclxmc"));
                	
                	//��˰��������ҵ
                	map.put("nsrsshy", rst.getString("nsrsshy"));
                	//��˰��������ҵ����
                	map.put("gjbzhymc", rst.getString("gjbzhymc"));
                	
                	//���ش���
                	map.put("qxdm", rst.getString("qxdm"));
                	//��ϵ�绰
                	map.put("lxdh", rst.getString("lxdh"));
                	
                }
               //��ҵ��˰��	
              if(((String)map.get("nsrlx")).equals("0")){
	            	//�õ����ش���
	                qxdm = InterfaceDj.getQxdm(ud);
	                //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
	                //�ӵǼǽӿ��л����Ϣ
	                SWDJJBSJ jbsj = new SWDJJBSJ();
	                //����������Ϣ
	                //HashMap mapDJ = new HashMap();
	               // SWDJJBSJ jbsj = new SWDJJBSJ();
	                try
	                {
	                    //mapDJ = (HashMap) InterfaceDj.getDjInfo(map, ud);
	                   // mapDJ = InterfaceDj.getDjInfo((String)map.get("jsjdm"),ud);
	                    jbsj = InterfaceDj.getJBSJ(String.valueOf(map.get("jsjdm")), ud);
	                }
	                catch (Exception ex1)
	                {
	                    ex1.printStackTrace();
	                    throw ExceptionUtil.getBaseException(ex1);
	                }
	                ZRRJBSJ dj1 = new ZRRJBSJ();
	                //SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");
	                if (jbsj == null)
	                {
	                    throw new ApplicationException("��ȡ�Ǽ���Ϣ����");
	                }
	
	                //����������Ϣ
	                //���������
	                orObj.setJsjdm((String)map.get("jsjdm"));
	                //��ע
	                orObj.setBz((String)map.get("pzjdwh"));
	                //�걨����
	                //orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
	                //�Ǽ�ע������
	                System.out.println("##########jbsj.getDjzclxdm()="+jbsj.getDjzclxdm());
	                System.out.println("##########jbsj.getGjbzhydm()="+jbsj.getGjbzhydm());
	                System.out.println("##########qxdm="+qxdm);
	                
	                orObj.setDjzclxdm(jbsj.getDjzclxdm());
	                orObj.setDjzclxmc(jbsj.getDjzclxmc());
	                //���ұ�׼��ҵ����
	                orObj.setGjbzhydm(jbsj.getGjbzhydm());//
	                orObj.setGjbzhymc(jbsj.getGjbzhymc());
	                //˰�������֯����
	                //orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
	                //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
	                orObj.setSwjgzzjgdm(ud.getSsdwdm());
	                orObj.setSwjgzzjgmc(ud.getSsdwmc());
	                
	                //���ջ���
	               // orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
	                //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
	                 orObj.setZsswjgzzjgdm(ud.getSsdwdm());
	                 orObj.setZsswjgzzjgmc(ud.getSsdwmc());
	                
	                //������ϵ
	                orObj.setLsgxdm(jbsj.getLsgxdm()); 
	                orObj.setLsgxmc(jbsj.getLsgxmc());
	                //��Ӫ��ַ��ϵ�绰
	                orObj.setJydzlxdm(jbsj.getJydzlxdm());
	                //¼����
	                orObj.setLrr(ud.yhid);
	               // if (ud != null)
	               // {
	                //    orObj.setLrr(ud.getYhid());
	               // }
	                
	                System.out.println("1################�޽�����="+(Timestamp)map.get("xjrq"));
	                
	                //�޽�����
	                orObj.setXjrq((Timestamp)map.get("xjrq"));
	                /*
	                if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
	                	//orObj.setXjrq(GdzysUtil.getDateAfter((Date)map.get("sjqrrq"),30));
	                	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("sjqrrq")).
	                	          toString(), 30)));
	                }else{
	                	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("qrrq")).
	                	          toString(), 30)));
	                }
	                */
	                
	                //�걨����
	                //orObj.setSbrq(nowTime);
	               //���о����� �걨����
	                if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
	                	orObj.setSbrq((Timestamp)map.get("sjqrrq"));
	                }else{
	                	orObj.setSbrq((Timestamp)map.get("qrrq"));
	                }
	                
	                orObj.setCjrq(nowTime); //��������
	                orObj.setLrrq(nowTime); //¼������
	                orObj.setQxdm(qxdm); //���ش���
	
	                /*
	                //�õ�������Ϣ//Modified by lufeng 2003-12-09
	                ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
	                for (int j = 0; j < dmList.size(); j++)
	                {
	                    YHZH yhzh = (YHZH) dmList.get(j);
	                    if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
	                    {
	                        orObj.setYhdm(yhzh.getYhdm()); //���д���
	                        orObj.setYhmc(yhzh.getKhyhmc()); //��������
	                        orObj.setZh(yhzh.getZh()); //�ʻ�
	                        break;
	                    }
	                }
	                */
	                //���д���
	                orObj.setYhdm(map.get("khyhdm")==null?"":(String)map.get("khyhdm")); 
	                //��������
	                orObj.setYhmc(map.get("khyhmc")==null?"":(String)map.get("khyhmc")); 
	                //�ʻ�
	                orObj.setZh(map.get("yhzh")==null?"":(String)map.get("yhzh")); 
	
	                //�걨��ʽ����
	                orObj.setFsdm(CodeConstant.FSDM_SMSB);
	                //������Դ
	                orObj.setSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //38
	                //˰������
	                orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
	                orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
	                
            	//������˰��
	            //��Ȼ��	    
                }else{
                	//����������Ϣ
                    //���������
                    orObj.setJsjdm((String)map.get("jsjdm"));
                    //��ע
                    orObj.setBz((String)map.get("pzjdwh"));
                    //�걨����
                    //orObj.setSbrq(SfTimeUtil.getTimestamp(form.getSbrq()));
                    //�Ǽ�ע������
                    orObj.setDjzclxdm(map.get("qydjzclx")==null?"":(String)map.get("qydjzclx"));
                    orObj.setDjzclxmc(map.get("djzclxmc")==null?"":(String)map.get("djzclxmc"));
                    
                    //���ұ�׼��ҵ����
                    orObj.setGjbzhydm(map.get("nsrsshy")==null?"":(String)map.get("nsrsshy"));
                    orObj.setGjbzhymc(map.get("gjbzhymc")==null?"":(String)map.get("gjbzhymc"));
                    
                    //˰�������֯����
                    //orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
                    //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
                    orObj.setSwjgzzjgdm(ud.getSsdwdm());
                    orObj.setSwjgzzjgmc(ud.getSsdwmc());
                    
                    //���ջ���
                   // orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
                    //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
                     orObj.setZsswjgzzjgdm(ud.getSsdwdm());
                     orObj.setZsswjgzzjgmc(ud.getSsdwmc());
                    
                    //������ϵ
                    //orObj.setLsgxdm(""); 
                    //orObj.setLsgxmc("");
                    //��Ӫ��ַ��ϵ�绰
                    orObj.setJydzlxdm(map.get("lxdh")==null?"":(String)map.get("lxdh"));
                    //¼����
                    orObj.setLrr(ud.yhid);
                   // if (ud != null)
                   // {
                    //    orObj.setLrr(ud.getYhid());
                   // }
                    
                    System.out.println("2################�޽�����="+(Timestamp)map.get("xjrq"));
                    
                    //�޽�����
                    orObj.setXjrq((Timestamp)map.get("xjrq"));
                    /*
                    if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
                    	//orObj.setXjrq(GdzysUtil.getDateAfter((Date)map.get("sjqrrq"),30));
                    	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("sjqrrq")).
                    	          toString(), 30)));
                    }else{
                    	orObj.setXjrq(Timestamp.valueOf(DateUtil.addDatetimeByDay(((Timestamp)map.get("qrrq")).
                    	          toString(), 30)));
                    }
                    */
                    
                    //�걨����
                    //orObj.setSbrq(nowTime);
                   //���о����� �걨����
                    if(map.get("sfsjsp")!=null&&((String)map.get("sfsjsp")).equals("1")){
                    	orObj.setSbrq((Timestamp)map.get("sjqrrq"));
                    }else{
                    	orObj.setSbrq((Timestamp)map.get("qrrq"));
                    }
                    
                    orObj.setCjrq(nowTime); //��������
                    orObj.setLrrq(nowTime); //¼������
                    orObj.setQxdm(map.get("qxdm")==null?"":(String)map.get("qxdm")); //���ش���

                    /*
                    //�õ�������Ϣ//Modified by lufeng 2003-12-09
                    ArrayList dmList = (ArrayList) mapDJ.get("YHZH");
                    for (int j = 0; j < dmList.size(); j++)
                    {
                        YHZH yhzh = (YHZH) dmList.get(j);
                        if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
                        {
                            orObj.setYhdm(yhzh.getYhdm()); //���д���
                            orObj.setYhmc(yhzh.getKhyhmc()); //��������
                            orObj.setZh(yhzh.getZh()); //�ʻ�
                            break;
                        }
                    }
                    */
                    //���д���
                    orObj.setYhdm(map.get("khyhdm")==null?"":(String)map.get("khyhdm")); 
                    //��������
                    orObj.setYhmc(map.get("khyhmc")==null?"":(String)map.get("khyhmc")); 
                    //�ʻ�
                    orObj.setZh(map.get("yhzh")==null?"":(String)map.get("yhzh")); 

                    //�걨��ʽ����
                    orObj.setFsdm(CodeConstant.FSDM_SMSB);
                    //������Դ
                    orObj.setSjly(GdzysCodeConstant.SMSB_SJLY_GDZYS); //38
                    //˰������
                    orObj.setSklxdm(CodeConstant.SKLXDM_ZCJK);
                    orObj.setSklxmc(CodeConstant.SKLXMC_ZCJK);
                }
                
                
                
                
                //�����ݽ��з�Ʊ����
                JksUtil ju = new JksUtil();
                try
                {   //��ǰ����
                String xtdqrq=GdzysDateUtil.getDate(GdzysDateUtil.getTimestampFromDB());
                
                List jksList=new ArrayList();
                HashMap jksmap = new HashMap();
                jksmap.put("szsmdm", GdzysCodeConstant.SMSB_GDZYS_SZSMDM);
                jksmap.put("szsmmc", GdzysCodeConstant.SMSB_GDZYS_SZSMMC);
                jksmap.put("kssl", map.get("ysmj"));
                jksmap.put("jsje", "0");
                jksmap.put("sjse", map.get("ynse"));
                jksmap.put("skssksrq", xtdqrq.substring(0,4)+"0101");
                jksmap.put("skssjsrq", xtdqrq.substring(0,4)+"1231");
                jksmap.put("szdm", GdzysCodeConstant.SMSB_GDZYS_SZDM);
                jksmap.put("szmc", GdzysCodeConstant.SMSB_GDZYS_SZMC);
                jksList.add(jksmap);
                	/*
                	 for (int i = 0; i < form.getDataList().size(); i++)
                	 {
                	      HashMap map = (HashMap) form.getDataList().get(i);
                	 System.out.println("####szsmdm="+map.get("szsmdm"));     
                	 System.out.println("####szsmmc="+map.get("szsmmc"));
                	 System.out.println("####kssl="+map.get("kssl"));
                	 System.out.println("####jsje="+map.get("jsje"));
                	 System.out.println("####sjse="+map.get("sjse"));
                	 System.out.println("####skssksrq="+map.get("skssksrq"));
                	 System.out.println("####skssjsrq="+map.get("skssjsrq"));
                	 System.out.println("####szdm="+map.get("szdm"));
                	 System.out.println("####szmc="+map.get("szmc"));
                	 }
                	*/
                	
                  List tempList = (ArrayList) ju.getJkDataGDZYS(orObj,
                		  jksList,CodeConstant.PRINT_YPYS);
                  if(tempList.size()>0){
                	 DeclareInfor declareInfor=(DeclareInfor)tempList.get(0);
                	 System.out.println("K#####declareInfor.getSbjkzb().getJkpzh()="+declareInfor.getSbjkzb().getJkpzh());
                	 System.out.println("K#####pf.getCxSbbxlh()="+pf.getCxSbbxlh());
   	              
	                 //���ɸ���ռ��˰�걨��ɿ������������
	                 sqlBuf=new StringBuffer();
	             	 sqlBuf.append("insert into sbdb.SB_JL_GDZYS_SBBJKSGLB (SBBXLH,JKPZH,SPLX) values (?,?,?) ");
	             	 ps = conn.prepareStatement(sqlBuf.toString());   
	      		     i=1;
	      		     ps.setString(i++, pf.getCxSbbxlh());
	      		     ps.setString(i++, declareInfor.getSbjkzb().getJkpzh());
	      		     ps.setString(i++, "1");
	                 int count=ps.executeUpdate();
	                 System.out.println("K##########count="+count);
	                 
	                 HashMap tepmmap = new HashMap();
	             	//�걨�����к�
	                 tepmmap.put("sbbxlh", pf.getCxSbbxlh());
	             	//��˰������
	                 tepmmap.put("jkpzh", declareInfor.getSbjkzb().getJkpzh());
	             	
	             	//��˰������
	                 tepmmap.put("jsjdm", (String)map.get("jsjdm"));
	             	  dataList.add(tepmmap);
	             	  pf.setDataList(dataList);
	                 
	                 
                  }
                  
                }
                catch (BaseException ex1)
                {
                    ex1.printStackTrace();
                    throw new ApplicationException("��������ʧ�ܣ�");
                }            	
            	
            }
            
            
            
		    rst.close();
		    //�ر�preparestatement
		    ps.close();

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
     * @Description: TODO ���ɼ����걨
     * @param gcf
     * @throws BaseException 
     */
    private void dealWithJMSB(GdzysCjjksForm gcf ,UserData ud ,Connection con) throws BaseException
    {
    	
    	try {
    		/*-------��ѯ�Ƿ���Ҫ���������˻���-----------*/
    		String sbbxlh = gcf.getCxSbbxlh();
			String queryGDJMSB="select * from sbdb.SB_JL_GDZYS_SBB where sbbxlh = ?";
			PreparedStatement queryGDJMSBPs = con.prepareStatement(queryGDJMSB);
			queryGDJMSBPs.setString(1, sbbxlh);
			
			ResultSet queryGDJMSBRsRs = queryGDJMSBPs.executeQuery();

			if(!queryGDJMSBRsRs.next())
			{
				return ;
			}
			
			
			String qxdm = queryGDJMSBRsRs.getString("QXDM");
			String sysend=queryGDJMSBRsRs.getString("SYSEND");
			String jsjdm = queryGDJMSBRsRs.getString("JSJDM");			//���������
			
			//����˰��
			String qxsyse = "select syse from dmdb.GD_DM_SYSE syse  where syse.qxdm=? AND syse.ND=? AND syse.syselxdm = (case when to_number(?)>2007 then '11' else '01' end)";
			PreparedStatement querySysePs = con.prepareStatement(qxsyse);
			querySysePs.setString(1, qxdm);
			querySysePs.setString(2, sysend);
			querySysePs.setString(3, sysend);
			ResultSet querySyseRs = querySysePs.executeQuery();
			if(!querySyseRs.next())
			{
				return ;
			}
			
			double syse = querySyseRs.getDouble("SYSE");		//ҵ����Ҫ�����ֵ���ܴ���ϸȡ
		
			String lrr = ud.yhid;			//¼����
			String jmxmdm = GdzysCodeConstant.SMSB_GDZYS_JMXMDM;			//������Ŀ����
			String szsmdm =GdzysCodeConstant.SMSB_GDZYS_SZSMDM;				//˰��˰Ŀ����	
			
			String xtdqrq=GdzysDateUtil.getDate(GdzysDateUtil.getTimestampFromDB());
			String skssksrq = xtdqrq.substring(0,4)+"0101";				//˰��������ʼ����
			String skssjsrq = xtdqrq.substring(0,4)+"1231";				//˰��������������
			
			//��ϸ��Ϣ
			String querySql = "select * from sbdb.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
			PreparedStatement queryPs = con.prepareStatement(querySql);
			queryPs.setString(1, sbbxlh);
			ResultSet queryRs = queryPs.executeQuery();
			
			while(queryRs.next())
			{
				String zdyt = queryRs.getString("ZDYTDM");
				int checksyse = queryRs.getInt("SYSE");
				System.out.println("zdyt:"+zdyt+"checksyse:"+checksyse);
				if("00".equals(zdyt) && checksyse ==2 )
				{
					
					double jsmj = queryRs.getDouble("JSMJ");
					Timestamp cjrq = queryRs.getTimestamp("CJRQ");
					
//			
					BigDecimal jsje = queryRs.getBigDecimal("JZSE")==null?new BigDecimal(0):queryGDJMSBRsRs.getBigDecimal("JZSE").setScale(2,BigDecimal.ROUND_HALF_UP);  //��˰���
					
					BigDecimal jmse = new BigDecimal((syse-2.0)*jsmj);
				
					//�������ʴ���
					String jmxzdm = GdzysCodeConstant.SMSB_GDZYS_JMXZDM;
					String sjly = GdzysCodeConstant.SMSB_SJLY_GDZYS;
					
					ServiceProxy sp = new ServiceProxy();
					sp.insertSBJM(jsjdm, szsmdm, jsje, jmse, lrr, jmxmdm, cjrq, skssjsrq, skssksrq, jmxzdm,sjly);
					System.out.println("�����������˻���");
					
					
					
				}
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}
    }
    
}

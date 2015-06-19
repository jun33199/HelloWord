/*
 * <p>Title: ������˰��������ϵͳ��������ռ��˰--˰Դ�Ǽ�</p>
 * <p>Copyright: (C) 2013 �����еط�˰��֣���˼�����ӿƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��˼�����ӿƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.YHZH;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.common.GdzysGy;
import com.ttsoft.bjtax.smsb.gdzys.constant.GdzysCodeConstant;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.DJXX;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.JMXXModel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.model.SBMXModel;
import com.ttsoft.bjtax.smsb.gdzys.sydj.sydjxxlr.web.GdzysSydjxxlrForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ����ռ��˰</p>
 * <p>Description: �½�˰Դ�Ǽ�Action��</p>
 * @author kanght
 * @version 1.0
 */
public class GdzysSydjxxlrProcessor implements Processor {

    public GdzysSydjxxlrProcessor() {
    }
    /**
     *˰Դ�Ǽ�
     * @param vp VOPackage
     * @return Object
     * @throws BaseException BaseException
     */
    public Object process(VOPackage vp) throws BaseException {
        switch (vp.getAction()) {
       
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_INIT:
            return this.doInit(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_QUERY: //��ʼ��
            return this.doQuery(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_CONFIRM: //ȷ��
        	return this.doConfirm(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_PRINT_SBB: //��ӡ��Ϣ
        	return this.getPrintSBB(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_REMARK://��ע
        	return this.doRemark(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SAVE://����
        	return this.doSave(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_DJXX://����
        	return this.getJBSJ(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SYSE://AJAX��ȡ����˰��
        	return this.getSYSE(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_JMSYJ:
        	return this.getJMSYJ(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_ZRR:
        	return this.getZRR(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_QXSH:
        	return this.doQxsh(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_SJSH:
        	return this.doSjsh(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_UPDATE:
        	return this.doUpdate(vp);
        case GdzysCodeConstant.GDZYS_SYXXLRACTION_BGQUERY:
        	return this.doBgcx(vp);
        default:
            throw new ApplicationException(
                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
    }
  //�����ѯ
    private Object doBgcx(VOPackage vp) throws BaseException  {
    
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	UserData ud= vp.getUserData();
    	
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	ResultSet rs = null;
	  	String sql = " select * from  sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				
				form.setSbbxlh(rs.getString("SBBXLH"));//�걨���к�
				form.setSylxdm(rs.getString("sylxdm"));//˰Դ���ʹ���
				form.setNsrlx(rs.getString("nsrlx"));//��˰������
				form.setNsrmc(rs.getString("nsrmc"));//��˰������
				form.setJsjdm(rs.getString("JSJDM"));//���������
				//˰��Ǽ�֤��
				//��֯��������
				form.setNsrsshy(rs.getString("nsrsshy"));//��˰��������ҵ
				form.setSfzzlxdm(rs.getString("sfzzlx"));//���֤������
				form.setSfzzhm(rs.getString("SFZZHM"));//���֤�պ���
				form.setNsrxxdz(rs.getString("nsrxxdz"));//��˰����ϸ��ַ
				form.setQydjzclx(rs.getString("qydjzclx"));//��ҵ�Ǽ�ע������
				form.setKhyh(rs.getString("KHYHMC"));//������������
				form.setYhzh(rs.getString("YHZH"));//�����ʺ�
				form.setLxrxm(rs.getString("LXRXM"));//��ϵ������
				form.setLxdh(rs.getString("LXDH"));//��ϵ�绰
				form.setSfsjsp(rs.getString("sfsjsp"));//�Ƿ��о�����
				form.setTdzldz(rs.getString("TDZLDZ"));//���������ַ
				form.setPzjdwh(rs.getString("PZJDWH"));//��׼ռ���ĺ�
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//��׼ռ�����
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//��׼ռ�����
				}
				//form.setPzjdmj(rs.getString("PZJDMJ"));//��׼ռ�����
				form.setJsxmmc(rs.getString("JSXMMC"));//������Ŀ����
				//form.setSjzdmj(rs.getString("SJZDMJ"));//ʵ��ռ�����
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//ʵ��ռ�����
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				
				form.setZdsj(df.format(rs.getDate("ZDSJ")));//ռ��ʱ��
				form.setJmsyj(rs.getString("sbjmly"));//�걨��������
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//��˰���
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//�������
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_ysmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//Ӧ˰���
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//Ӧ��˰��
				form.setBzlxdm(rs.getString("bzlxdm"));//��ע���ʹ���
				form.setBzms(rs.getString("bzms"));//��ע����
				form.setSysend(rs.getString("sysend"));//����˰�����
				form.setYhdm(rs.getString("khyhdm"));//�������д���
				
				if(form.getNsrlx().equals("0")){
					form.setNsrsbh(rs.getString("SWDJZH")+"-"+rs.getString("ZZJGDM"));//��˰��ʶ���
					DJXX djxx = (DJXX) getJBSJ(vp);
					form.setNsrsshymc(djxx.getNsrsshymc());
					form.setQydjzclxmc(djxx.getQydjzclxmc());
				}else{
					form.setNsrsbh("");//��˰��ʶ���
				}
				
			}
			if("".equals(form.getPzjdwh()) || form.getPzjdwh() == null || form.getPzjdwh() == ""){
				form.setWhnd("");
				form.setWh("");
			}else{
				String whnd = form.getPzjdwh().substring(5,9);
				String wh = form.getPzjdwh().substring(10,form.getPzjdwh().length()-1);
				System.out.println(whnd+"=nd="+wh+"=wh=");
				form.setWhnd(whnd);
				form.setWh(wh);
			}
			
			//��ʼ��
	    	getBGXX(form,ud);
			//ռ����;
			//Map map = getSBMX(form,conn);	
			//form.setSbmx_map(map);
			form.setSbmxlist(getSBMX_list(form,conn));
			//�������
			Map map2 = getJMMX_CX(form.getSbbxlh(),form,conn);
			form.setJmmx_map(map2);
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("�����ѯ�쳣");
		}finally{
			SfDBResource.freeConnection(conn);
		}

		return form;
	}
    //��ȡ������Ϣ
    public Object getBGXX(GdzysSydjxxlrForm form,UserData ud ) throws BaseException {
		//���ش���
		String qxdm = InterfaceDj.getQxdm(ud);;
		//˰Դ����list
		List sylx_list = new ArrayList();
		//ռ����;list
		List zdyt_list = new ArrayList();
		//����˰��list
		List syse_list = new ArrayList();
		//�������list
		List jmlb_list = new ArrayList();
	     Connection conn = null;
		try{
			conn = SfDBResource.getConnection();
		//������˰������
		//˰Դ����
			sylx_list = getSYLX(conn);
		//��˰��������ҵ--��ҵ�Ǽ�ע������ 20131202
	     //getJBSJ(vp);
		//ռ����;
	     zdyt_list = getZDYT(conn);
		//����˰��
	     syse_list = getSYSE_CX(conn,qxdm,form);
		//�������
	     jmlb_list =  getJMLB(conn);
	    
	     form.setSylx_list(sylx_list);
	     form.setZdyt_list(zdyt_list);
	     form.setSyse_list(syse_list);
	     form.setJmlb_list(jmlb_list);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	       }
	   //��ע
		
		
		return null;
    	
    }

    
  //�޸�
    private Object doUpdate(VOPackage vp) throws BaseException  {
    	System.out.println("˰Դ�ǼǱ��");
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	UserData ud= vp.getUserData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		String flag="";
		try {
    
    	 //��˰��ʶ���
		 String swdjzh ="";
		 String zzjgdm ="";
		 String qxdm = InterfaceDj.getQxdm(ud);;
		 if(form.getNsrlx().equals("0")){
			 String nsrsbh =  form.getNsrsbh();
			 int m = nsrsbh.indexOf("-");
			 int n = m+1;
			 swdjzh = nsrsbh.substring(0,m);
			 zzjgdm = nsrsbh.substring(n);
		 }else{
			 DJXX djxx = (DJXX)getZRR(vp);
			 form.setNsrsbh("");
			/* form.setKhyh(djxx.getYhdm());
			 form.setYhzh(djxx.getYhzh());*/
			 form.setKhyh(djxx.getYhmc());
			 form.setYhzh(djxx.getYhzh());
			 form.setYhdm(djxx.getYhdm());
			// zzjgdm = djxx.getSwjgzzjgdm();
			 form.setNsrsshy("8190");//����δ�����ķ���
			 form.setQydjzclx("410");//���幤�̻�
		 }
		 
		 //���֤����������
		 Map map = getSFZZLXMC();
		 if(form.getNsrlx().equals("1")){
			 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
		 }
		 //��ȡռ����;����
		 Map zdyt = getZDYTMC();
		 form.setZdytmc((List)zdyt.get("zdytmc"));
		 form.setZdytdm((List)zdyt.get("zdytdm")); 
		 //�������
		 Map jmlbmap = getJMLBMC();
		 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
		 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
		 //�û���
		 String yhid = ud.getYhid();
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='10',sjqrr= '',sjqrrq=to_date('','yyyymmdd'),sjqrzt='0',qrr='',qrrq=to_date('','yyyymmdd'),qrzt='0'," +
	  			"sylxdm=?,nsrlx=?,nsrmc=?,jsjdm=?,swdjzh=?,zzjgdm=?,nsrsshy=?,sfzzlx=?,sfzzhm=?,nsrxxdz=?,qydjzclx=?," +
	  			"khyhmc=?,yhzh=?,lxrxm=?,lxdh=?,sfsjsp=?,tdzldz=?,pzjdwh=?,pzjdmj=?,jsxmmc=?,sjzdmj=?,zdsj=to_date(?,'yyyymmdd')," +
	  			"sfjmssb=?,sbjmly=?,jsmj=?,jzse=?,jmmj=?,jmse=?,ysmj=?,ynse=?,zsjgdm=?,lrr=?,lrrq=sysdate,bzms=?,sysend=?,qxdm=?,khyhdm=?" +
	  			" where sbbxlh=?";
	  
			conn = SfDBResource.getConnection();
			
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSylxdm());//˰Դ���ʹ���
			ps.setString(2, form.getNsrlx());//��˰������
			ps.setString(3, form.getNsrmc());//��˰������
			ps.setString(4, form.getJsjdm());//���������
			ps.setString(5, swdjzh);//˰��Ǽ�֤��
			ps.setString(6, zzjgdm);//��֯��������
			ps.setString(7, form.getNsrsshy());//��˰��������ҵ
			ps.setString(8, form.getSfzzlxdm());//���֤������
			ps.setString(9, form.getSfzzhm());//���֤�պ���
			ps.setString(10, form.getNsrxxdz());//��˰����ϸ��ַ
			ps.setString(11, form.getQydjzclx());//��ҵ�Ǽ�ע������
			ps.setString(12, form.getKhyh());//������������
			ps.setString(13, form.getYhzh());//�����ʺ�
			ps.setString(14, form.getLxrxm());//��ϵ������
			ps.setString(15, form.getLxdh());//��ϵ�绰
			ps.setString(16, form.getSfsjsp());//�Ƿ��о�����
			ps.setString(17, form.getTdzldz());//���������ַ
			ps.setString(18, form.getPzjdwh());//��׼ռ���ĺ�
			ps.setString(19, form.getPzjdmj());//��׼ռ�����
			ps.setString(20, form.getJsxmmc());//������Ŀ����
			ps.setString(21, form.getSjzdmj());//ʵ��ռ�����
			ps.setString(22, form.getZdsj());//ռ��ʱ��
			if(form.getSylxdm().equals("0")){
				/*if("".equals(form.getYhdm().trim())){
					ps.setString(23, "0");//�Ƿ�����걨
				}else{
					ps.setString(23, form.getYhdm());//�Ƿ�����걨
				}*/
				ps.setString(23, "0");//�Ƿ�����걨
			}else{
				ps.setString(23, "1");//�Ƿ�������걨
			}
			ps.setString(24, form.getJmsyj());//�걨��������
			ps.setString(25, form.getHj_jsmj());//��˰���
			ps.setString(26, form.getHj_jzse());//����˰��
			ps.setString(27, form.getHj_jmmj());//�������
			ps.setString(28, form.getHj_jmse());//����˰��
			ps.setString(29, form.getHj_ysmj());//Ӧ˰���
			ps.setString(30, form.getHj_ynse());//Ӧ��˰��
			ps.setString(31, ud.getSsdwdm());//���ջ��ش���			
			ps.setString(32, yhid);//¼����
			ps.setString(33, form.getBzms());//¼����
			ps.setString(34, form.getSysend());//ʹ��˰�����
			ps.setString(35, qxdm);//���ش���
			if("0".equals(form.getYhdm())){
				ps.setString(36, "");//���д���
			}else{
				ps.setString(36, form.getYhdm());//���д���
			}
			ps.setString(37, form.getSbbxlh());//�걨�����к�
			ps.executeUpdate();
			ps.close();
			
			//ɾ���걨��ϸ
	    	//deleteSBMX(vp);
			String sql2 = "delete from  SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
			ps= conn.prepareStatement(sql2);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
			ps.close();
				
				
	    	//ɾ��������ϸ
//	    	deleteJMMX(vp);
			String sql3 = "delete from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ?";
			ps= conn.prepareStatement(sql3);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
			ps.close();
	    	//�����걨��ϸ
//	    	saveSBMX(vp);
			 //ռ����;
			 String[] zdyt_sbmx = form.getZdyt();
			//����˰��
		    String[] syse_sbmx = form.getSyse();
		    //��˰���
		    String[] jsmj_sbmx = form.getJsmj();
		    //����˰��
		    String[] jzse_sbmx = form.getJzse();
		    //�������
		    String[] jmmj_sbmx = form.getJmmj();
		    //����˰��
		    String[] jmse_sbmx = form.getJmse();
		    //Ӧ˰���
		    String[] ysmj_sbmx = form.getYsmj();
		    //Ӧ��˰��
		    String[] ynse_sbmx = form.getYnse();
			 
		     //��ȡ��������sql
		     String sql4 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBSBMX(SBBXLH,ZDYTDM,SYSE,JSMJ,JZSE,JMMJ,JMSE,YSMJ,YNSE,CJR,CJRQ,LRR,LRRQ,xh,syselxdm) " +
		     		"VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?)";
		    
		    	 //��ȡ����
				ps = conn.prepareStatement(sql4);
				for(int i = 0;i<jsmj_sbmx.length;i++){
					int j = syse_sbmx[i].indexOf("-")+1;
					ps.setString(1, form.getSbbxlh());//�걨�����к�
					ps.setString(2, zdyt_sbmx[i]);//ռ����;
					ps.setString(3, syse_sbmx[i].substring(j));//����˰��
					ps.setBigDecimal(4, str2Bigdecimal(jsmj_sbmx[i]));//��˰���
					ps.setBigDecimal(5, str2Bigdecimal(jzse_sbmx[i]));//����˰��
					ps.setBigDecimal(6, str2Bigdecimal(jmmj_sbmx[i]));//�������
					ps.setBigDecimal(7, str2Bigdecimal(jmse_sbmx[i]));//����˰��
					ps.setBigDecimal(8, str2Bigdecimal(ysmj_sbmx[i]));//Ӧ˰���
					ps.setBigDecimal(9, str2Bigdecimal(ynse_sbmx[i]));//Ӧ��˰��
					ps.setString(10, yhid);//������
					ps.setString(11, yhid);//¼����
					ps.setInt(12, i+1);//¼����
					ps.setString(13, syse_sbmx[i].substring(0,j-1));//¼����
			
					ps.addBatch();    
				}
			
			    //����
				ps.executeBatch();
			   //�ر�preparestatement
			    ps.close();
	    	//���������ϸ
	    	if(form.getFljmse()==null || "".equals(form.getFljmse())){
	    	}else{
	    		//����������
	    	    String[] jmlbdm_jmmx = form.getJmlbdm_jmmx();
	    	    //�������
	    	    String[] jmmj_jmmx = form.getFljmmj();
	    	    //����˰��
	    	    String[] jmse_jmmx = form.getFljmse();
	    	     //����sql
	    	     String sql5 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBJMMX(SBBXLH,JMSLBDM,JMMJ,JMSE,CJR,CJRQ,LRR,LRRQ) " +
	    	     		"VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)";
	    	    	 //��ȡ����
	    			conn = SfDBResource.getConnection();
	    			ps = conn.prepareStatement(sql5);
	    			
	    			for(int i = 0;i<jmmj_jmmx.length;i++){
	    				ps.setString(1, form.getSbbxlh());//�걨�����к�
	    				if("".equals(jmlbdm_jmmx[i]) || jmlbdm_jmmx[i]==""){
	    					return null;
	    				}
	    				ps.setString(2, jmlbdm_jmmx[i]);//����������
	    				ps.setBigDecimal(3, str2Bigdecimal(jmmj_jmmx[i]));//�������
	    				ps.setBigDecimal(4, str2Bigdecimal(jmse_jmmx[i]));//����˰��
	    				ps.setString(5, yhid);//������
	    				ps.setString(6, yhid);//¼����
	    				ps.addBatch();
	    			}
	    		    ps.executeBatch();
	    		    //�ر�preparestatement
	    		    ps.close();
	    	}
	    	
	    	String sql6 = "select cjr,cjrq from sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
	    	ps = conn.prepareStatement(sql6);
	    	ps.setString(1, form.getSbbxlh());
	    	ResultSet rs = ps.executeQuery();
	    	if(rs.next()){
	    		//������
	    		form.setCjr(rs.getString("cjr"));
	    		//����ʱ��
	    		if(null == rs.getString("cjrq") || "".equals(rs.getString("cjrq"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("cjrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
	    	}
	    	rs.close();
	    	ps.close();
	    	
	    	form.setShzt("10");
	    	
		} catch (Exception e) {
			
			e.printStackTrace();
			flag="error";
			form.setFlag(flag);
		}finally{
			SfDBResource.freeConnection(conn);
		}
    	
    	return form;
	}
    //ɾ���걨��ϸ
    public Object deleteSBMX(VOPackage vo) throws BaseException{
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
    	Connection conn = null;
    	PreparedStatement ps = null;
    	conn = SfDBResource.getConnection();
    	String sql = "delete from  SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh = ?";
    	try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	return null;
    }
    //ɾ��������ϸ
  public Object deleteJMMX(VOPackage vo) throws BaseException{
	  GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
  	Connection conn = null;
  	PreparedStatement ps = null;
  	conn = SfDBResource.getConnection();
  	String sql = "delete from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ?";
  	try {
			ps= conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
  	return null;
    }
    
    //�о����
    private Object doSjsh(VOPackage vp) throws BaseException  {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	String flag = "sjsh_success";
    	UserData ud= vp.getUserData();
    	String yhid = ud.getYhid();
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='40',sjqrr= ?,sjqrrq=to_date(?,'yyyymmdd HH24:MI:SS'),sjqrzt='1' where sbbxlh=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, yhid);
			ps.setString(2, form.getSjqrsj()+" 00:00:00");
			ps.setString(3, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			
			
			String sql2 = "update sbdb.SB_JL_GDZYS_JMSZM set kjrq=to_date(?,'yyyymmdd HH24:MI:SS') where sbbxlh=?";
			
			ps =  conn.prepareStatement(sql2);
			ps.setString(1, form.getSjqrsj()+" 00:00:00");
			ps.setString(2, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			System.out.println("�о���˽�����");
			
			form.setShzt("40");
	    	form.setSjqrrq(form.getSjqrsj()+" 00:00:00");
	    	form.setSjqrr(yhid);
	    	form.setFlag(flag);
	    	form.setJms_flag("jms_flag");
		} catch (Exception e) {
			form.setShzt("30");
			flag="sjsh_false";
			form.setFlag(flag);
			e.printStackTrace();
			
		}finally{
			SfDBResource.freeConnection(conn);
		}
    	
    	return form;
	}
    
    
    //�������
    private Object doQxsh(VOPackage vp) throws BaseException  {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	String flag = "qxsh_success";
    	String jms_flag = "jms_flag";
    	UserData ud= vp.getUserData();
    	String yhid = ud.getYhid();
    	String ssdwdm  = ud.getSsdwdm();
    	System.out.println(ud.getSsdwmc()+"===============ssdwmc=============");
	  	Connection conn = null;
		PreparedStatement ps = null;
	  	
	  	String sql = " update sbdb.SB_JL_GDZYS_SBB set sbzt='30',qrr= ?,qrrq=sysdate,qrzt='1' where sbbxlh=?";
		 try {
			
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, yhid);
			ps.setString(2, form.getSbbxlh());
			ps.executeUpdate();
			ps.close();
			System.out.println("������˽�����");
			//��������о���ˣ����ɼ�����Ϣ
			
			
			boolean bl= true;
			if("0".equals(form.getSylxdm())){
				jms_flag = "";
				bl = false;
			}
			String[] jmlb = form.getJmlbdm_jmmx();
			if( "".equals(jmlb) || jmlb==null){
				bl=false;
			}else{
				for(int i=0;i<jmlb.length;i++){
					if("01".equals(jmlb[i]) || "02".equals(jmlb[i])){
						jms_flag="";
						bl=false;
					}
				}
			}
			//ռ������Ϊ�ղ������ɼ�������
			if( "".equals(form.getPzjdwh()) || form.getPzjdwh() =="" || form.getPzjdwh()==null){
				bl =false;
			}
			
			if(form.getSfsjsp().equals("0")){
				
				if(bl){
				int nd = new Date(System.currentTimeMillis()).getYear()+1900;
				String qxdm = InterfaceDj.getQxdm(ud);
				String jmszmbh = getJMSZMBH(qxdm,nd,conn);
				System.out.println("����˰���"+jmszmbh);
				String sql2 = "insert into sbdb.SB_JL_GDZYS_JMSZM (sbbxlh,jmszmbh,qxdm,dybz,zfbz,cjr,cjrq,jzbz,KJRQ) values(?,?,?,'0','0',?,sysdate,'000000',SYSDATE)";
				ps = conn.prepareStatement(sql2);
				ps.setString(1, form.getSbbxlh());
				ps.setString(2, jmszmbh);
				ps.setString(3, ssdwdm);
				ps.setString(4, yhid);
				ps.executeUpdate();
				ps.close();
				}
				//���о����--����д��������
			}else{
				if(bl){
				int nd = new Date(System.currentTimeMillis()).getYear()+1900;
				String qxdm = InterfaceDj.getQxdm(ud);
				String jmszmbh = getJMSZMBH_SJ(qxdm,nd,conn);
				System.out.println("����˰���"+jmszmbh);
				String sql2 = "insert into sbdb.SB_JL_GDZYS_JMSZM (sbbxlh,jmszmbh,qxdm,dybz,zfbz,cjr,cjrq,jzbz) values(?,?,?,'0','0',?,sysdate,'000000')";
				ps = conn.prepareStatement(sql2);
				ps.setString(1, form.getSbbxlh());
				ps.setString(2, jmszmbh);
				ps.setString(3, ssdwdm);
				ps.setString(4, yhid);
				ps.executeUpdate();
				ps.close();
				}
			}
			//����˰�ѿ���
		  	form.setJms_flag(jms_flag);
			
			String qrrq =null;
			String sql3 = " select * from  sbdb.SB_JL_GDZYS_SBB where sbbxlh=?";
				ps =  conn.prepareStatement(sql3);
				ps.setString(1, form.getSbbxlh());
				ResultSet rs = ps.executeQuery();
				if(rs.next()){
					qrrq = rs.getString("qrrq");
				}
				qrrq = qrrq.substring(0, qrrq.indexOf("."));
				String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10);
			form.setQrrq(rq);
			form.setShzt("30");
			  
	    	form.setQrr(yhid);
	    	form.setFlag(flag);
			
		} catch (Exception e) {
			flag = "qxsh_false";
			form.setFlag(flag);
			form.setShzt("10");
			e.printStackTrace();
		
		}finally{
			SfDBResource.freeConnection(conn);
		}
		
    
    	return form;
	}
    //��ȡ����˰֤�����
    public String getJMSZMBH(String qxdm,int nd,Connection conn) throws BaseException{
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	StringBuffer buffer = new StringBuffer();
    	String qxfjsx = "";
    	int bh = 1;
    	String sys_nd="";
    	String sql = "select qxfjsx from dmdb.GD_DM_QXFJDM where qxdm = ?";
    	String sql_nd = "select extract(year from sysdate) nd��from dual";
    	try {
    		
    		ps = conn.prepareStatement(sql_nd);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			sys_nd = rs.getString("nd");
    		}
    		
    		rs.close();
    		ps.close();
    		
			ps = conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			rs = ps.executeQuery();
			if(rs.next()){
				qxfjsx = rs.getString("qxfjsx");
			}
			rs.close();
			ps.close();
			String sql2 = " select bh from sbdb.SB_JL_GDZYS_JMSZMBH where qxdm =? and nd = ?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, qxdm);
			ps.setString(2, sys_nd);
			rs = ps.executeQuery();
			
				//�в�ѯ
			if(rs.next()){
				bh = rs.getInt("bh");
				//�޸�״̬
				String sql4 = "update sbdb.SB_JL_GDZYS_JMSZMBH set bh = ?  where qxdm =? and nd= ?  and bh=?";
				try {
					ps.close();
					ps = conn.prepareStatement(sql4);
					ps.setInt(1, bh+1);
					ps.setString(2, qxdm);
					ps.setString(3, sys_nd);
					ps.setInt(4, bh);
					int num = ps.executeUpdate();
					if(num<0){
						throw new ApplicationException("��ȡ����˰֤�����ʱ�����쳣�������±��棡");
					}
				}catch (Exception ex) {
						ex.printStackTrace();
						throw new ApplicationException("��ȡ����˰֤�����ʱ�����쳣�������±��棡");
					}		
				
				//û�в���
			}else{
				ps.close();
				String sql3 = "insert into sbdb.SB_JL_GDZYS_JMSZMBH (qxdm,nd,bh,szt) values(?,?,'2','0')";
				ps = conn.prepareStatement(sql3);
				ps.setString(1, qxdm);
				ps.setString(2, sys_nd);
				ps.executeUpdate();
				ps.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	
    	buffer.append(qxfjsx);
    	buffer.append("��˰���⣨");
    	buffer.append(sys_nd);
    	buffer.append("��");
    	buffer.append(bh);
    	
    	return buffer.toString();
    }
    
  //��ȡ����˰֤�����
    public String getJMSZMBH_SJ(String qxdm,int nd,Connection conn) throws BaseException{
    	PreparedStatement ps= null;
    	ResultSet rs = null;
    	StringBuffer buffer = new StringBuffer();
    	String qxfjsx = "";
    	int bh = 1;
    	String sys_nd="";
    	String sql = "select qxfjsx from dmdb.GD_DM_QXFJDM where qxdm = '90'";
    	String sql_nd = "select extract(year from sysdate) nd��from dual";
    	try {
    		
    		ps = conn.prepareStatement(sql_nd);
    		rs = ps.executeQuery();
    		if(rs.next()){
    			sys_nd = rs.getString("nd");
    		}
    		
    		rs.close();
    		ps.close();
    		
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			if(rs.next()){
				qxfjsx = rs.getString("qxfjsx");
			}
			rs.close();
			ps.close();
			String sql2 = " select bh from sbdb.SB_JL_GDZYS_JMSZMBH where qxdm =? and nd = ?";
			ps = conn.prepareStatement(sql2);
			//ps.setString(1, qxdm);
			ps.setString(1, "90");
			ps.setString(2, sys_nd);
			rs = ps.executeQuery();
			
				//�в�ѯ
			if(rs.next()){
				bh = rs.getInt("bh");
				//�޸�״̬
				String sql4 = "update sbdb.SB_JL_GDZYS_JMSZMBH set bh = ?  where qxdm =? and nd= ?  and bh=?";
				try {
					ps.close();
					ps = conn.prepareStatement(sql4);
					ps.setInt(1, bh+1);
					//ps.setString(2, qxdm);
					ps.setString(2, "90");
					ps.setString(3, sys_nd);
					ps.setInt(4, bh);
					int num = ps.executeUpdate();
					if(num<0){
						throw new ApplicationException("��ȡ����˰֤�����ʱ�����쳣�������±��棡");
					}
				}catch (Exception ex) {
						ex.printStackTrace();
						throw new ApplicationException("��ȡ����˰֤�����ʱ�����쳣�������±��棡");
					}		
				
				//û�в���
			}else{
				ps.close();
				String sql3 = "insert into sbdb.SB_JL_GDZYS_JMSZMBH (qxdm,nd,bh,szt) values(?,?,'2','0')";
				ps = conn.prepareStatement(sql3);
				//ps.setString(1, qxdm);
				ps.setString(1, "90");
				ps.setString(2, sys_nd);
				ps.executeUpdate();
				ps.close();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	
    	buffer.append(qxfjsx);
    	buffer.append("��˰���⣨");
    	buffer.append(sys_nd);
    	buffer.append("��");
    	buffer.append(bh);
    	
    	return buffer.toString();
    }
    
    
	private Object getZRR(VOPackage vp) throws BaseException {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	  	
	  	DJXX djxx = new DJXX();
	  	djxx.setDm("0");
	  	String sql = " select * from djdb.dj_jl_zrrjbsj WHERE zjlxdm =? and zjhm = ? ";
	  	String sql2 = "select * from djdb.dj_jl_yhzh where jsjdm=?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			ps.setString(1, form.getSfzzlxdm());
			ps.setString(2, form.getSfzzhm());
			rs = ps.executeQuery();
			if(rs.next()){
				djxx.setDm("1");
				djxx.setNsrmc(rs.getString("nsrmc"));
				djxx.setNsrxxdz(rs.getString("txdz"));
				djxx.setJsjdm(rs.getString("jsjdm"));
				//djxx.setSwjgzzjgdm(rs.getString("swjgzzjgdm"));
			}	
			rs.close();
			ps.close();
			
			ps=conn.prepareStatement(sql2);
			ps.setString(1, djxx.getJsjdm());
			rs = ps.executeQuery();
			while(rs.next()){
	             if (rs.getString("jbzhbs").equals(CodeConstant.SMSB_JBZHBS))
	             {
            		djxx.setYhdm(rs.getString("yhdm"));
    				djxx.setYhmc(rs.getString("khyhmc"));
    				djxx.setYhzh(rs.getString("zh"));
	                 break;
	             }
			
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
    	return djxx;
	}

    
    
    //����˰����
  private Object getJMSYJ(VOPackage vp) throws BaseException {
	  	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
	  	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
	  	String dm ="";
	  	StringBuffer yj =  new StringBuffer();
	  	for(int i = 0;i<form.getJmlbdm_jmmx().length;i++){
	  		if(i == form.getJmlbdm_jmmx().length-1){
	  			dm +=form.getJmlbdm_jmmx()[i];
	  		}else{
	  			dm +=form.getJmlbdm_jmmx()[i]+",";
	  		}
	  	}
	  	System.out.println("����˰������"+dm);
	  	if("".equals(dm) || dm == "" || dm == null){
	  		yj.append("");
	  	}else{
		String sql = "select * from dmdb.GD_DM_JMSYJ where jmsyjdm in( select distinct jmsyjdm  from dmdb.GD_DM_JMSLB a where jmslbdm in("+dm+"))";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				yj.append(rs.getString("jmsyjmc"));
				yj.append(" ");
			}	
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
	  }
	  return yj;
	}
	//AJAX��ȡ����˰��
    private Object getSYSE(VOPackage vp) throws BaseException {
    	GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
    	List list = new ArrayList();
    	UserData ud = vp.getUserData();
    	String qxdm = InterfaceDj.getQxdm(ud);;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
 		" AND SE.QXDM = ?" +
 		"AND SE.ND = ?";
		 try {
			conn = SfDBResource.getConnection();
			ps =  conn.prepareStatement(sql);
			
			ps.setString(1, qxdm);
			ps.setString(2, form.getSysend());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
   				list.add(bean);
			}	
			rs.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	//����˰Դ�Ǽ���Ϣ
	private Object doSave(VOPackage vp)throws BaseException {
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 UserData ud = vp.getUserData();
		 //��˰��ʶ���
		 String swdjzh ="";
		 String zzjgdm ="";
		 PreparedStatement ps = null;
	     Connection conn = null;
		 try {
		 String qxdm = InterfaceDj.getQxdm(ud);;
		 if(form.getNsrlx().equals("0")){
			 String nsrsbh =  form.getNsrsbh();
			 int m = nsrsbh.indexOf("-");
			 int n = m+1;
			 swdjzh = nsrsbh.substring(0,m);
			 zzjgdm = nsrsbh.substring(n);
			 form.setSfzzlxdm("");
			 form.setSfzzhm("");
		 }else{
			 DJXX djxx = (DJXX)getZRR(vp);
			 form.setNsrsbh("");
			/* form.setKhyh(djxx.getYhdm());
			 form.setYhzh(djxx.getYhzh());*/
			 form.setKhyh(djxx.getYhmc());
			 form.setYhzh(djxx.getYhzh());
			 form.setYhdm(djxx.getYhdm());
			 zzjgdm = djxx.getSwjgzzjgdm();
			 form.setNsrsshy("8190");//����δ�����ķ���
			 form.setQydjzclx("410");//���幤�̻�
		 }
		 
		 //���֤����������
		 Map map = getSFZZLXMC();
		 if(form.getNsrlx().equals("1")){
			 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
		 }
		 //��ȡռ����;����
		 Map zdyt = getZDYTMC();
		 form.setZdytmc((List)zdyt.get("zdytmc"));
		 form.setZdytdm((List)zdyt.get("zdytdm")); 
		 //�������
		 Map jmlbmap = getJMLBMC();
		 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
		 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
		 //�û���
		 String yhid = ud.getYhid();
		 form.setCjr(yhid);
	     //����sql
	     String sql = "INSERT INTO SBDB.SB_JL_GDZYS_SBB(SBBXLH,SYLXDM,NSRLX,NSRMC,JSJDM," +
	     		"SWDJZH," +
	     		"ZZJGDM," +
	     		"NSRSSHY,SFZZLX,SFZZHM,NSRXXDZ,QYDJZCLX,KHYHMC,YHZH,LXRXM," +
	     		"LXDH," +
	     		"SFSJSP," +
	     		"TDZLDZ,PZJDWH,PZJDMJ,JSXMMC," +
	     		"SJZDMJ," +
	     		"ZDSJ," +
	     		"SBJMLY,JSMJ,JZSE," +
	     		"JMMJ,JMSE,YNSE," +
	     		"CJR,CJRQ,LRR,LRRQ,QRZT,SBZT,qxdm,ZSJGDM,sysend,sfjmssb,ysmj,khyhdm) " +
	     		"VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,to_date(?,'yyyymmdd'),?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,'0','10',?,?,?,?,?,?)";
	   
	    	 //��ȡ����
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, form.getSbbxlh());//�걨�����к�
			ps.setString(2, form.getSylxdm());//˰Դ���ʹ���
			ps.setString(3, form.getNsrlx());//��˰������
			ps.setString(4, form.getNsrmc());//��˰������
			ps.setString(5, form.getJsjdm());//���������
			ps.setString(6, swdjzh);//˰��Ǽ�֤��
			ps.setString(7, zzjgdm);//��֯��������
			ps.setString(8, form.getNsrsshy());//��˰��������ҵ
			ps.setString(9, form.getSfzzlxdm());//���֤������
			ps.setString(10, form.getSfzzhm());//���֤�պ���
			ps.setString(11, form.getNsrxxdz());//��˰����ϸ��ַ
			ps.setString(12, form.getQydjzclx());//��ҵ�Ǽ�ע������
			ps.setString(13, form.getKhyh());//������������
			ps.setString(14, form.getYhzh());//�����ʺ�
			ps.setString(15, form.getLxrxm());//��ϵ������
			ps.setString(16, form.getLxdh());//��ϵ�绰
			ps.setString(17, form.getSfsjsp());//�Ƿ��о�����
			ps.setString(18, form.getTdzldz());//���������ַ
			ps.setString(19, form.getPzjdwh());//��׼ռ���ĺ�
			ps.setString(20, form.getPzjdmj());//��׼ռ�����
			ps.setString(21, form.getJsxmmc());//������Ŀ����
			ps.setString(22, form.getSjzdmj());//ʵ��ռ�����
			ps.setString(23, form.getZdsj());//ռ��ʱ��
			ps.setString(24, form.getJmsyj());//�걨��������
			ps.setString(25, form.getHj_jsmj());//��˰���
			ps.setString(26, form.getHj_jzse());//����˰��
			ps.setString(27, form.getHj_jmmj());//�������
			ps.setString(28, form.getHj_jmse());//����˰��
			ps.setString(29, form.getHj_ynse());//Ӧ��˰��
			ps.setString(30, yhid);//������
//			ps.setString(31, form.getBzlxdm());//��ע���ʹ���
//			ps.setString(32, form.getBzms());//��ע����
			ps.setString(31, yhid);//¼����
			ps.setString(32, qxdm);//���ش���
			ps.setString(33, ud.getSsdwdm());//���ջ��ش���
			ps.setString(34, form.getSysend());//ʹ��˰�����
			if(form.getSylxdm().equals("0")){
				ps.setString(35, "0");//�Ƿ�����걨
			}else{
				ps.setString(35, "1");//�Ƿ�����걨
			}
			ps.setString(36, form.getHj_ysmj());//Ӧ˰���
			if("0".equals(form.getYhdm())){
				ps.setString(37, "");//���д���
			}else{
				ps.setString(37, form.getYhdm());//���д���
			}
		    //����
		     ps.execute();
		    //�ر�preparestatement
		    ps.close();
			System.out.println("�����걨����");
			
			//�����걨��ϸ
			String[] str_zdyt = form.getZdyt();
			if(str_zdyt.length<1){
				
			}else{
				 //ռ����;
				 String[] zdyt_sbmx = form.getZdyt();
				//����˰��
			    String[] syse_sbmx = form.getSyse();
			    //��˰���
			    String[] jsmj_sbmx = form.getJsmj();
			    //����˰��
			    String[] jzse_sbmx = form.getJzse();
			    //�������
			    String[] jmmj_sbmx = form.getJmmj();
			    //����˰��
			    String[] jmse_sbmx = form.getJmse();
			    //Ӧ˰���
			    String[] ysmj_sbmx = form.getYsmj();
			    //Ӧ��˰��
			    String[] ynse_sbmx = form.getYnse();
				  
			     String sql2 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBSBMX(SBBXLH,ZDYTDM,SYSE,JSMJ,JZSE,JMMJ,JMSE,YSMJ,YNSE,CJR,CJRQ,LRR,LRRQ,xh,syselxdm) " +
			     		"VALUES(?,?,?,?,?,?,?,?,?,?,SYSDATE,?,SYSDATE,?,?)";
			    
			    	 //��ȡ����
					ps = conn.prepareStatement(sql2);
			
					for(int i = 0;i<jsmj_sbmx.length;i++){
						int j = syse_sbmx[i].indexOf("-")+1;
						ps.setString(1, form.getSbbxlh());//�걨�����к�
						ps.setString(2, zdyt_sbmx[i]);//ռ����;
						ps.setString(3, syse_sbmx[i].substring(j));//����˰��
						ps.setBigDecimal(4, str2Bigdecimal(jsmj_sbmx[i]));//��˰���
						ps.setBigDecimal(5, str2Bigdecimal(jzse_sbmx[i]));//����˰��
						ps.setBigDecimal(6, str2Bigdecimal(jmmj_sbmx[i]));//�������
						ps.setBigDecimal(7, str2Bigdecimal(jmse_sbmx[i]));//����˰��
						ps.setBigDecimal(8, str2Bigdecimal(ysmj_sbmx[i]));//Ӧ˰���
						ps.setBigDecimal(9, str2Bigdecimal(ynse_sbmx[i]));//Ӧ��˰��
						ps.setString(10, yhid);//������
						ps.setString(11, yhid);//¼����
						ps.setInt(12, i+1);//¼����
						ps.setString(13, syse_sbmx[i].substring(0,j-1));//¼����
						
						ps.addBatch();    
					}
				
				    //����
					ps.executeBatch();
				   //�ر�preparestatement
				    ps.close();
				
			}
			//���������ϸ
			String[] fljmmj = form.getFljmmj();
			if("".equals(fljmmj) || fljmmj==null || fljmmj.length<1){
			}else{
				//����������
			    String[] jmlbdm_jmmx = form.getJmlbdm_jmmx();
			    //�������
			    String[] jmmj_jmmx = form.getFljmmj();
			    //����˰��
			    String[] jmse_jmmx = form.getFljmse();
			     //����sql
			     String sql3 = "INSERT INTO SBDB.SB_JL_GDZYS_SBBJMMX(SBBXLH,JMSLBDM,JMMJ,JMSE,CJR,CJRQ,LRR,LRRQ) " +
			     		"VALUES(?,?,?,?,?,SYSDATE,?,SYSDATE)";
			    	 //��ȡ����
					ps = conn.prepareStatement(sql3);
					for(int i = 0;i<jmmj_jmmx.length;i++){
						ps.setString(1, form.getSbbxlh());//�걨�����к�
						ps.setString(2, jmlbdm_jmmx[i]);//����������
						ps.setBigDecimal(3, str2Bigdecimal(jmmj_jmmx[i]));//�������
						ps.setBigDecimal(4, str2Bigdecimal(jmse_jmmx[i]));//����˰��
						ps.setString(5, yhid);//������
						ps.setString(6, yhid);//¼����
						ps.addBatch();
					}
				    ps.executeBatch();
				    //�ر�preparestatement
				    ps.close();
			}
			String sql4 = "select sysdate from dual";
			ps = conn.prepareStatement(sql4);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				if(null == rs.getString("sysdate") || "".equals(rs.getString("sysdate"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("sysdate");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
				
			}
			
			
		} catch (Exception e) {
			
			form.setErrors("error");
			e.printStackTrace();
			System.out.println("�����걨�����쳣��Ϣ");
			
			
		}finally {
   	     	SfDBResource.freeConnection(conn);
   	     	
	       }
	
	
		form.setShzt("10");
		form.setQrrq(null);
		form.setSjqrrq(null);
		return form;
	}
	
	//���汸ע��Ϣ
	private Object doRemark(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		//�걨���к�
		String sbbxlh = form.getSbbxlh();
		//��ע���ʹ���
		String bzlxdm = form.getBzlxdm();
		//��ע����
		String bzms = form.getBzms();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer();
		conn = SfDBResource.getConnection();
		
		String sql2 = "select bzms from SBDB.SB_JL_GDZYS_SBB where sbbxlh = ?";
		String sql = "UPDATE SBDB.SB_JL_GDZYS_SBB SET BZLXDM = ?,BZMS = ? WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql2);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			if(rs.next()){
				if(rs.getString("bzms") ==null || "".equals(rs.getString("bzms")) || rs.getString("bzms") ==""){
					buffer.append("");
				}else{
					if(rs.getString("bzms").trim().length()>1){
						buffer.append(rs.getString("bzms").trim());
						buffer.append(";");
					}
					
				}
			}
			ps.close();
			//���ݱ�ע���ʹ����ȡ��ע��������
			String bzlxmc = getBZLXMC(bzlxdm);
			buffer.append(bzlxmc);
			buffer.append(":");
			ps = conn.prepareStatement(sql);
			ps.setString(1, bzlxdm);
			ps.setString(2, buffer.append(bzms).toString());
			ps.setString(3, sbbxlh);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		form.setBzms(buffer.toString());
		return form;
	}
	//��ȡ��ע��������
	private String getBZLXMC(String bzlxdm)throws BaseException {
		//��ע����
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String bzlxmc = "";
		conn = SfDBResource.getConnection();
		String sql = "select bzlxmc from DMDB.GD_DM_BZLX where bzlxdm = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, bzlxdm);
			rs = ps.executeQuery();
			if(rs.next()){
				bzlxmc= rs.getString("bzlxmc");
			}
			rs.close();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		return bzlxmc;
	}
	

	
	//��ȡ��ӡ��Ϣ
	private Object getPrintSBB(VOPackage vp)throws BaseException {
		
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		//ȷ�����к�
		String sbbxlh = form.getSbbxlh();
		//�Ǽ���Ϣ
		DJXX djxx = new DJXX();
		if(form.getNsrlx().equals("0")){
			djxx = (DJXX) getJBSJ(vp);
			form.setNsrmc(djxx.getNsrmc());
			form.setNsrxxdz(djxx.getNsrxxdz());
			form.setQydjzclxmc(djxx.getQydjzclxmc());
			form.setNsrsshymc(djxx.getNsrsshymc());
			form.setSfzzlxmc("------------------");
			form.setSfzzhm("-----------------");
		}else{
			djxx = (DJXX) getZRR(vp);
			form.setNsrmc(djxx.getNsrmc());
			form.setNsrxxdz(djxx.getNsrxxdz());
			form.setKhyh(djxx.getYhmc());
			form.setYhzh(djxx.getYhzh());
			form.setYhdm(djxx.getYhdm());
			form.setQydjzclxmc("���幤�̻�");
			form.setNsrsshymc("����δ��������");
		
		}
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = SfDBResource.getConnection();
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBB WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			if(rs.next()){
				form.setSbbxlh(rs.getString("SBBXLH"));//�걨���к�
				//form.setSfzzhm(rs.getString("SFZZHM"));//���֤�պ���
				form.setSylxdm(rs.getString("sylxdm"));//˰Դ���ʹ���
				if(form.getNsrlx().equals("0")){
					form.setJsjdm(rs.getString("JSJDM"));//���������
					form.setNsrsbh(rs.getString("SWDJZH")+"-"+rs.getString("ZZJGDM"));//��˰��ʶ���
				}else{
					form.setNsrsbh("-");//��˰��ʶ���
				}
				
				form.setKhyh(rs.getString("KHYHMC"));//������������
				form.setYhzh(rs.getString("YHZH"));//�����ʺ�
				form.setLxrxm(rs.getString("LXRXM"));//��ϵ������
				form.setLxdh(rs.getString("LXDH"));//��ϵ�绰
				form.setPzjdwh(rs.getString("PZJDWH"));//��׼ռ���ĺ�
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				form.setZdsj(df.format(rs.getDate("ZDSJ")));//ռ��ʱ��
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//��׼ռ�����
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//��׼ռ�����
				}
				
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("SJZDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//ʵ��ռ�����
				
				
				
				form.setTdzldz(rs.getString("TDZLDZ"));//���������ַ
				form.setJsxmmc(rs.getString("JSXMMC"));//������Ŀ����
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//��˰���
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//�������
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//Ӧ��˰��
				Date date = rs.getDate("cjrq");//��������
				int nian = date.getYear()+1900;
				int yue = date.getMonth()+1;
				int tian = date.getDate();
				System.out.println("date"+date+"nian"+nian+"yue"+yue+"tian"+tian);
				form.setNian(nian);
				form.setYue(yue);
				form.setTian(tian);
				
				if(form.getNsrlx().equals("1")){
					Map sfzzlxmc = getSFZZLXMC();
					form.setSfzzlxmc(sfzzlxmc.get(rs.getString("sfzzlx")).toString());//���֤������
					form.setSfzzhm(rs.getString("SFZZHM"));//���֤�պ���
				}
				
				
			}
			//ռ����;
			Map map = getSBMX(form,conn);	
			form.setSbmx_map(map);
			//�������
			Map map2 = getJMMX(sbbxlh,form,conn);
			form.setJmmx_map(map2);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		return form;
	}
/* 
 * ��ʼ
 * */
	public List getSBMX_list(GdzysSydjxxlrForm form,Connection conn) throws BaseException  {
		String sbbxlh = form.getSbbxlh();
		
		List list = new ArrayList();
	
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by xh";	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			while(rs.next()){
				SBMXModel model = new SBMXModel();
				model.setSyse(rs.getString("syse"));
				model.setZdyt(rs.getString("zdytdm"));
				model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
				model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
				list.add(model);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		return list;
	}
	
/*����*/
	
	//��ȡ��ӡ�걨��ϸ
	
	public Map getSBMX(GdzysSydjxxlrForm form,Connection conn) throws BaseException  {
		String sbbxlh = form.getSbbxlh();
		
		Map map = new HashMap();
		//��ͨ
		List jt = new ArrayList();
		//��ҵ
		List gy = new ArrayList();
		//��ҵ
		List sy = new ArrayList();
		//סլ
		List zz = new ArrayList();
		//����ס��
		List jm = new ArrayList();
		//����
		List qt = new ArrayList();
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by zdytdm";	
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			while(rs.next()){
				SBMXModel model = new SBMXModel();
				if("00".equals(rs.getString("zdytdm"))){
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					jt.add(model);
				}else if("01".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					gy.add(model);
				}else if("02".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					sy.add(model);
				}else if("03".equals(rs.getString("zdytdm"))){
					//ѧУ
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					zz.add(model);
				}else if("04".equals(rs.getString("zdytdm"))){
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					jm.add(model);
				}else if("05".equals(rs.getString("zdytdm"))){
					//
					model.setSyse(rs.getString("syse"));
					model.setJsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setJmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYsmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					model.setYnse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					qt.add(model);
				}else{
					System.out.println("�������������");
				}
			}
			map.put("00", jt);
			map.put("01",gy);
			map.put("02", sy);
			map.put("03", zz);
			map.put("04", jm);
			map.put("05", qt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		return map;
	}
	
	

	//ȷ��˰Դ�Ǽ���Ϣ
	private Object doConfirm(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		UserData ud = vp.getUserData();
		//ȷ����
		String yhid = ud.getYhid();
		//ȷ�����к�
		String[] sbbxlh = form.getSbbxlh_ary();
		Connection conn = null;
		PreparedStatement ps = null;
		conn = SfDBResource.getConnection();
		String sql = "UPDATE SBDB.SB_JL_GDZYS_SBB SET QRR = ?,QRSJ = SYSDATE,QRZT = '1' WHERE SBBXLH = ?";
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<sbbxlh.length;i++){
				ps.setString(1, yhid);
				ps.setString(2, sbbxlh[i]);
				ps.addBatch();
			}
			ps.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	    }
		
		return null;
	}
	//��ѯ˰Դ�Ǽ���Ϣ
	private Object doQuery(VOPackage vp)throws BaseException {
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 String sbbxlh = form.getSbbxlh();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	     String sql1 = "select * from SBDB.SB_JL_GDZYS_SBB where sbbxlh=?";
	     try {
	    	conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				form.setSylxdm(rs.getString("sylxdm"));//˰Դ���ʹ���
				form.setNsrlx(rs.getString("nsrlx"));//��˰������
				form.setShzt(rs.getString("sbzt"));//�걨״̬
				form.setNsrmc(rs.getString("nsrmc"));//��˰������
				form.setJsjdm(rs.getString("jsjdm"));//���������
				form.setNsrsbh(rs.getString("swdjzh")+"-"+rs.getString("zzjgdm"));//��˰��ʶ���
				form.setNsrsshy(rs.getString("nsrsshy"));//��˰��������ҵ
				form.setQydjzclx(rs.getString("qydjzclx"));
				form.setNsrxxdz(rs.getString("nsrxxdz"));//��˰����ϸ��ַ
				form.setKhyh(rs.getString("khyhmc"));//������������
				form.setYhzh(rs.getString("yhzh"));//�����ʺ�
				form.setLxrxm(rs.getString("lxrxm"));//��ϵ������
				form.setLxdh(rs.getString("lxdh"));//��ϵ�绰
				form.setSfsjsp(rs.getString("sfsjsp"));//�Ƿ��о�����
				form.setTdzldz(rs.getString("tdzldz"));//���������ַ
				form.setPzjdwh(rs.getString("pzjdwh"));//��׼ռ���ĺ�
			
				if(rs.getString("PZJDMJ") == null || "".equals(rs.getString("PZJDMJ"))){
					form.setPzjdmj("");//��׼ռ�����
				}else{
					form.setPzjdmj(String.valueOf(rs.getBigDecimal("PZJDMJ").setScale(2,BigDecimal.ROUND_HALF_UP)));//��׼ռ�����
				}
				form.setJsxmmc(rs.getString("jsxmmc"));//������Ŀ����
				
				form.setSjzdmj(String.valueOf(rs.getBigDecimal("sjzdmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//ʵ��ռ�����
				SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
				form.setZdsj(df.format(rs.getDate("zdsj")));//ռ��ʱ��
				form.setJmsyj(rs.getString("sbjmly"));//�걨��������
				form.setHj_jsmj(String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//��˰���
				form.setHj_jzse(String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_jmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//�������
				form.setHj_jmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));//����˰��
				form.setHj_ysmj(String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP)));//Ӧ˰���
				form.setHj_ynse(String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP)));//Ӧ��˰��
				if("".equals(rs.getString("qrrq")) || rs.getString("qrrq") ==null){
					form.setQrrq(rs.getString("qrrq"));//ȷ������
				}else{
					String qrrq = rs.getString("qrrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setQrrq(rq);//ȷ������
				}
				form.setQrr(rs.getString("qrr"));//ȷ����
				
				form.setSjqrr(rs.getString("sjqrr"));//�о�ȷ����
				if("".equals(rs.getString("sjqrrq")) || rs.getString("sjqrrq") ==null){
					form.setSjqrrq(rs.getString("sjqrrq"));//�о�ȷ������
				}else{
					String qrrq = rs.getString("sjqrrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10);
					form.setSjqrrq(rq+" 00:00:00");//�о�ȷ������
				}
				
				form.setCjr(rs.getString("cjr"));//������
				
				if(null == rs.getString("cjrq") || "".equals(rs.getString("cjrq"))){
					form.setCjsj("");	
				}else{
					String qrrq = rs.getString("cjrq");
					String rq = qrrq.substring(0,4)+qrrq.substring(5,7)+qrrq.substring(8,10)+qrrq.substring(10,qrrq.indexOf("."));
					form.setCjsj(rq);	
				}
				
				form.setSysend(rs.getString("sysend"));//����˰�����
				form.setBzms(rs.getString("bzms"));//��ע����
				form.setSfzzlxdm(rs.getString("sfzzlx"));//���֤������
				form.setSfzzhm(rs.getString("sfzzhm"));//���֤�պ���
			}
			rs.close();
			ps.close();

			//���֤����������
			 Map map = getSFZZLXMC();
			 if(form.getNsrlx().equals("1")){
				 form.setSfzzlxmc(map.get(form.getSfzzlxdm().toString()).toString());	 
			 }else{
				 DJXX djxx = (DJXX) getJBSJ(vp);
				 form.setNsrsshymc(djxx.getNsrsshymc());
				 form.setQydjzclxmc(djxx.getQydjzclxmc());
			 }
			 //��ȡռ����;����
			 Map zdyt = getZDYTMC();
			 form.setZdytmc((List)zdyt.get("zdytmc"));
			 form.setZdytdm((List)zdyt.get("zdytdm")); 
			 //�������
			 Map jmlbmap = getJMLBMC();
			 form.setJmlbdm((List)jmlbmap.get("jmslbdm"));
			 form.setJmlbmc((List)jmlbmap.get("jmslbmc"));
			//ռ����;
			Map map2 = getSBMX(form,conn);	
			form.setSbmx_map(map2);
			//�������
			Map map3 = getJMMX_CX(sbbxlh,form,conn);
			form.setJmmx_map(map3);
			//ռ����;
			getzdyt(vp);
			//������ϸ
			getjmxx(vp);
			//��ѯ�Ƿ���Խ��б��
			//sql2��ѯ����˰֤���Ƿ������ɺ��Ƿ������
			//sql3��ѯ�ɿ����Ƿ�������
			String sql2 = "select * from sbdb.sb_jl_gdzys_jmszm where zfbz <> '1' and sbbxlh = ?";
			String sql3 = "select * from SBDB.SB_JL_GDZYS_SBBJKSGLB where sbbxlh=?";
			ps = conn.prepareStatement(sql2);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			//�Ƿ����ɼ���˰֤��
			if(rs.next()){
				form.setJms_flag("jms_flag");
				//�ж��Ƿ��Ѽ���
				String jzbz = rs.getString("jzbz");
				if(jzbz.charAt(1) == '1'){
					form.setZwbs_flag("zwbs_flag");
				}else{
					form.setZwbs_flag("");
				}
				
			}else{
				form.setJms_flag("");
			}
			rs.close();
			ps.close();
			//�Ƿ����ɽɿ���
			ps = conn.prepareStatement(sql3);
			ps.setString(1, form.getSbbxlh());
			rs = ps.executeQuery();
			if(rs.next()){
				form.setJks_flag("jks_flag");
			}else{
				form.setJks_flag("");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}

		return form;
	}
	public String[] getjmxx(VOPackage vo){
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		//����������
	    String[] jmlbdm_jmmx = null;
	    //�������
	    String[] jmmj_jmmx = null;
	    //����˰��
	    String[] jmse_jmmx = null;
		int len = 0;
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	     //����sql
	     String sql1 = " select count(*) num from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh=?";
	     String sql = " select * from  SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh=?";
	     try {
	    	 //��ȡ����
			conn = SfDBResource.getConnection();
			ps = conn.prepareStatement(sql1);
			ps.setString(1, form.getSbbxlh());
			rs =ps.executeQuery();
			if(rs.next()){
				len = rs.getInt("num");
			}
			rs.close();
			ps.close();
			  jmlbdm_jmmx =new String[len];
			    //�������
			  jmmj_jmmx = new String[len];
			    //����˰��
			  jmse_jmmx = new String[len];
			  int i=0;
			  ps = conn.prepareStatement(sql);
			  ps.setString(1, form.getSbbxlh());
			  rs =ps.executeQuery();
			  while(rs.next()){
				  jmlbdm_jmmx[i]=rs.getString("jmslbdm");
				  jmmj_jmmx[i]=String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmmj");
				  jmse_jmmx[i]=String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmse");
				  i++;
			  }
			form.setJmlbdm_jmmx(jmlbdm_jmmx);
			form.setFljmmj(jmmj_jmmx);
			form.setFljmse(jmse_jmmx);
		    //�ر�preparestatement
		    ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return null;
	}
	
	
	
	//�걨��Ϣ
	public String[] getzdyt(VOPackage vp){
		 GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		 PreparedStatement ps = null;
	     Connection conn = null;
	     ResultSet rs = null;
	   //ռ����;
		 String[] zdyt_sbmx = null;
		//����˰��
	    String[] syse_sbmx =null;
	    //��˰���
	    String[] jsmj_sbmx = null;
	    //����˰��
	    String[] jzse_sbmx = null;
	    //�������
	    String[] jmmj_sbmx = null;
	    //����˰��
	    String[] jmse_sbmx =null;
	    //Ӧ˰���
	    String[] ysmj_sbmx = null;
	    //Ӧ��˰��
	    String[] ynse_sbmx = null;
		 
	     int len =0;
	     String sql = "select count(*) num from SBDB.SB_JL_GDZYS_SBBSBMX where sbbxlh=?";
	     String sql1 = "SELECT * FROM SBDB.SB_JL_GDZYS_SBBSBMX WHERE SBBXLH = ? order by zdytdm";
	    	try {
				conn = SfDBResource.getConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, form.getSbbxlh());
				rs = ps.executeQuery();
				if(rs.next()){
					len=rs.getInt("num");
				}
				zdyt_sbmx = new String[len] ;
				syse_sbmx = new String[len] ;
				jsmj_sbmx = new String[len] ;
				jzse_sbmx = new String[len] ;
				jmmj_sbmx = new String[len] ;
				jmse_sbmx = new String[len] ;
				ysmj_sbmx = new String[len] ;
				ynse_sbmx = new String[len] ;
				int i=0;
				rs.close();
				ps.close();
				ps = conn.prepareStatement(sql1);
				ps.setString(1, form.getSbbxlh());
				rs = ps.executeQuery();
				while(rs.next()){
					zdyt_sbmx[i] = rs.getString("zdytdm");
					syse_sbmx[i] = rs.getString("syse");//rs.getString("syse");
					jsmj_sbmx[i] = String.valueOf(rs.getBigDecimal("jsmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jsmj");
					jzse_sbmx[i] = String.valueOf(rs.getBigDecimal("jzse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jzse");
					jmmj_sbmx[i] = String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmmj");
					jmse_sbmx[i] = String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("jmse");
					ysmj_sbmx[i] = String.valueOf(rs.getBigDecimal("ysmj").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("ysmj");
					ynse_sbmx[i] = String.valueOf(rs.getBigDecimal("ynse").setScale(2,BigDecimal.ROUND_HALF_UP));//rs.getString("ynse");
					i++;
				}
				form.setZdyt(zdyt_sbmx);
				form.setSyse(syse_sbmx);
				form.setJsmj(jsmj_sbmx);
				form.setJzse(jzse_sbmx);
				form.setJmmj(jmmj_sbmx);
				form.setJmse(jmse_sbmx);
				form.setYsmj(ysmj_sbmx);
				form.setYnse(ynse_sbmx);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return null;
	}
	
	
	
	
	
	
	//��ʼ��˰Դ�Ǽ�ҳ��
	private Object doInit(VOPackage vp)throws BaseException {
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vp.getData();
		UserData ud = vp.getUserData();
		//���ش���
		String qxdm = InterfaceDj.getQxdm(ud);;
		//˰Դ����list
		List sylx_list = new ArrayList();
		//ռ����;list
		List zdyt_list = new ArrayList();
		//����˰��list
		List syse_list = new ArrayList();
		//�������list
		List jmlb_list = new ArrayList();
	     Connection conn = null;
		try{
			conn = SfDBResource.getConnection();
		//������˰������
		//˰Դ����
			sylx_list = getSYLX(conn);
		//��˰��������ҵ--��ҵ�Ǽ�ע������ 20131202
	     //getJBSJ(vp);
		//ռ����;
	     zdyt_list = getZDYT(conn);
		//����˰��
	     syse_list = getSYSE(conn,qxdm,form);
		//�������
	     jmlb_list =  getJMLB(conn);
	    
	     form.setSylx_list(sylx_list);
	     form.setZdyt_list(zdyt_list);
	     form.setSyse_list(syse_list);
	     form.setJmlb_list(jmlb_list);
	     String sbbxlh = GdzysGy.getSBXLH();
	     form.setSbbxlh(sbbxlh);
		} catch (Exception e) {
			e.printStackTrace();
			 throw new ApplicationException(e.getMessage());
		}finally {
			SfDBResource.freeConnection(conn);
	       }
		return form;
	}
    
	public BigDecimal str2Bigdecimal(String str){
		BigDecimal bd=new BigDecimal(str); 
		bd=bd.setScale(4, BigDecimal.ROUND_HALF_UP);
		return bd;
	}
	
	//������˰������
	//˰Դ����
	private List getSYLX(Connection conn) throws BaseException{
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_SYLX WHERE ZXBZ = '0'";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYLXMC"),rs.getString("SYLXDM"));
				list.add(bean);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	public Object getZRRJBSJ(VOPackage vo) throws BaseException{
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		UserData ud = vo.getUserData();
		ZRRJBSJ dj = new ZRRJBSJ();
		List list = new ArrayList();
		try {
			dj =  InterfaceDj.getZRRJBSJ(form.getJsjdm(), ud);
			//list = InterfaceDj.getYHZH(form.getJsjdm());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		DJXX djxx = new DJXX();
		djxx.setNsrmc(dj.getNsrmc());//��˰������
		djxx.setNsrxxdz(dj.getTxdz());//��д��ַ
		/*for (int j = 0; j < list.size(); j++){
            YHZH yhzh = (YHZH) list.get(j);
            if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
            {
                djxx.setYhdm(yhzh.getYhdm()); //���д���
                djxx.setYhmc(yhzh.getKhyhmc()); //��������
                djxx.setYhzh(yhzh.getZh()); //�ʻ�
                break;
            }
        }*/
			
		return djxx;
	}
	
	//��˰��������ҵ-----��ҵ�Ǽ�ע������
	public Object getJBSJ(VOPackage vo) throws BaseException{
		GdzysSydjxxlrForm form = (GdzysSydjxxlrForm) vo.getData();
		UserData ud = vo.getUserData();
		SWDJJBSJ dj = new SWDJJBSJ();
		List list = new ArrayList();
		try {
			dj = (SWDJJBSJ) InterfaceDj.getJBSJ(form.getJsjdm(), ud);
		    list = InterfaceDj.getYHZH(form.getJsjdm(), ud);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		DJXX djxx = new DJXX();
	
			djxx.setNsrmc(dj.getNsrmc());//��˰������
			djxx.setNsrsshy(dj.getGjbzhydm()); //���ұ�׼��ҵ����
			djxx.setNsrsshymc(dj.getGjbzhymc());//���ұ�׼��ҵ����
			djxx.setQydjzclxdm(dj.getDjzclxdm()); //�Ǽ�ע�����ʹ���
			djxx.setQydjzclxmc(dj.getDjzclxmc()); //�Ǽ�ע����������
			djxx.setNsrxxdz(dj.getZcdz()); //ע���ַ
			djxx.setNsrsbh(dj.getSwdjzh()+"-"+dj.getZzjgdm());
		
			String[] yhdm_list = new String[list.size()+1];
			String[] yhmc_list = new String[list.size()+1];
			String[] yhzh_list = new String[list.size()+1];
			yhdm_list[0]="0";
			yhmc_list[0]="��¼����������";
			yhzh_list[0]="��¼�������˺�";
			for (int j = 0; j < list.size(); j++)
	         {
	             YHZH yhzh = (YHZH) list.get(j);
	             if (yhzh.getJbzhbs().equals(CodeConstant.SMSB_JBZHBS))
	             {
	                 djxx.setYhdm(yhzh.getYhdm()); //���д���
	                 djxx.setYhmc(yhzh.getKhyhmc()); //��������
	                 djxx.setYhzh(yhzh.getZh()); //�ʻ�
	                 yhdm_list[j+1] = yhzh.getYhdm();
	                 yhmc_list[j+1] = yhzh.getKhyhmc();
	                 yhzh_list[j+1] = yhzh.getZh();
	                 
	             }else{
	            	 yhdm_list[j+1] = yhzh.getYhdm();
	                 yhmc_list[j+1] = yhzh.getKhyhmc();
	                 yhzh_list[j+1] = yhzh.getZh();
	                 
	             }
	         }
			
		djxx.setYhdm_list(yhdm_list);
		djxx.setYhmc_list(yhmc_list);
		djxx.setYhzh_list(yhzh_list);
		return djxx;
	}
	//��ע
	//ռ����;
	
	public List getZDYT(Connection conn) throws BaseException{
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_ZDYT WHERE ZXBZ = '0'";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("ZDYTMC"),rs.getString("ZDYTDM"));
				list.add(bean);
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	//����˰��
	public List getSYSE(Connection conn,String qxdm, GdzysSydjxxlrForm form)throws BaseException{
		List nd_list = new ArrayList();
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql2 = "select a.nd from DMDB.GD_DM_SYSE a group by a.nd order by a.nd desc";
		 try {
			ps =  conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				nd_list.add(rs.getString("nd"));
			}
			rs.close();
			ps.close();
			//�ŵ�form�� 
			form.setNd_list(nd_list);
			
			String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
		 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
		 		" AND SE.QXDM = ?" +
		 		"AND SE.ND = ?";
		
			ps =  conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			ps.setString(2, nd_list.get(0).toString());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
				list.add(bean);
			}	
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	
	//�������
	public List getJMLB(Connection conn) throws BaseException{
		List list = new ArrayList();
		LabelValueBean b = new LabelValueBean("","");
		list.add(b);
		LabelValueBean ncjm = null;
		LabelValueBean nckn = null;
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql = "SELECT * FROM DMDB.GD_DM_JMSLB WHERE ZXBZ = '0' and jmslbdm <> '06' order by JMSLBDM";
		 try {
			ps =  conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				if("01".equals(rs.getString("JMSLBDM"))){
					ncjm = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));;
				} else if("02".equals(rs.getString("JMSLBDM"))){
					nckn = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));
				}else{
				LabelValueBean bean = new LabelValueBean(rs.getString("JMSLBMC"),rs.getString("JMSLBDM"));
				list.add(bean);
				}
			}
			list.add(ncjm);
			list.add(nckn);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	//��ȡ������ϸ��Ϣ
	private Map getJMMX_CX(String sbbxlh, GdzysSydjxxlrForm form, Connection conn) throws BaseException{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Map map = new HashMap();
		 //����00
			JMXXModel js = new JMXXModel();
		 //ũ��01
			JMXXModel nc = new JMXXModel();
		 //����02
			JMXXModel kn = new JMXXModel();
		 //����Ժ03
			JMXXModel yly = new JMXXModel();
		 //ҽԺ04
			JMXXModel yy = new JMXXModel();
		 //�׶�԰05
			JMXXModel yey = new JMXXModel();
		 //ѧУ0601--һ��
			JMXXModel xx1 = new JMXXModel();
			//ѧУ0602--����
			JMXXModel xx2 = new JMXXModel();
		 //����07
			JMXXModel qt = new JMXXModel();
		
		 String sql = " select * from SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ? ";
		 try {
			ps =  conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if("00".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						js.setFljmmj("0");
					}else{
						js.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						js.setFljmse("0");
					}else{
						js.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("01".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						nc.setFljmmj("0");
					}else{
						nc.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						nc.setFljmse("0");
					}else{
						nc.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("02".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						kn.setFljmmj("0");
					}else{
						kn.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						kn.setFljmse("0");
					}else{
						kn.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("03".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yly.setFljmmj("0");
					}else{
						yly.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yly.setFljmse("0");
					}else{
						yly.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}else if("04".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yy.setFljmmj("0");
					}else{
						yy.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yy.setFljmse("0");
					}else{
						yy.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("05".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yey.setFljmmj("0");
					}else{
						yey.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yey.setFljmse("0");
					}else{
						yey.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("0601".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						xx1.setFljmmj("0");
					}else{
						xx1.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						xx1.setFljmse("0");
					}else{
						xx1.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("0602".equals(rs.getString("jmslbdm"))){
						
						if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
							xx2.setFljmmj("0");
						}else{
							xx2.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
						}
						if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
							xx2.setFljmse("0");
						}else{
							xx2.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
						}
				
				}else if("07".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						qt.setFljmmj("0");
					}else{
						qt.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						qt.setFljmse("0");
					}else{
						qt.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}
	
			}
			map.put("00", js);
			map.put("01", nc);
			map.put("02", kn);
			map.put("03", yly);
			map.put("04", yy);
			map.put("05", yey);
			map.put("0601", xx1);
			map.put("0602", xx2);
			map.put("07", qt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return map;
	}
	
	
	//��ȡ������ϸ��Ϣ
	private Map getJMMX(String sbbxlh, GdzysSydjxxlrForm form, Connection conn) throws BaseException{
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 Map map = new HashMap();
		 //����00
			JMXXModel js = new JMXXModel();
		 //ũ��01
			JMXXModel nc = new JMXXModel();
		 //����02
			JMXXModel kn = new JMXXModel();
		 //����Ժ03
			JMXXModel yly = new JMXXModel();
		 //ҽԺ04
			JMXXModel yy = new JMXXModel();
		 //�׶�԰05
			JMXXModel yey = new JMXXModel();
		 //ѧУ06
			JMXXModel xx = new JMXXModel();
		 //����07
			JMXXModel qt = new JMXXModel();
		 BigDecimal jmmj = new BigDecimal("0.0000");
		 BigDecimal jmse = new BigDecimal("0.0000");
		 //str2Bigdecimal
		 String sql = " select * from SBDB.SB_JL_GDZYS_SBBJMMX where sbbxlh = ? ";
		 try {
			ps =  conn.prepareStatement(sql);
			ps.setString(1, sbbxlh);
			rs = ps.executeQuery();
			
			while(rs.next()){
				if("00".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						js.setFljmmj("0");
					}else{
						js.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						js.setFljmse("0");
					}else{
						js.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("01".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						nc.setFljmmj("0");
					}else{
						nc.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						nc.setFljmse("0");
					}else{
						nc.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("02".equals(rs.getString("jmslbdm"))){
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						kn.setFljmmj("0");
					}else{
						kn.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						kn.setFljmse("0");
					}else{
						kn.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("03".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yly.setFljmmj("0");
					}else{
						yly.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yly.setFljmse("0");
					}else{
						yly.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}else if("04".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yy.setFljmmj("0");
					}else{
						yy.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yy.setFljmse("0");
					}else{
						yy.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if("05".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						yey.setFljmmj("0");
					}else{
						yey.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						yey.setFljmse("0");
					}else{
						yey.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
				}else if(rs.getString("jmslbdm").indexOf("06")!=-1){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						jmmj = jmmj.add(new BigDecimal("0.00"));
					}else{
						jmmj = jmmj.add(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						jmse = jmse.add(new BigDecimal("0.00"));
					}else{
						jmse = jmse.add(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP));					
					}
					
					xx.setFljmmj(String.valueOf(jmmj.setScale(2,BigDecimal.ROUND_HALF_UP)));
					xx.setFljmse(String.valueOf(jmse.setScale(2,BigDecimal.ROUND_HALF_UP)));
				}else if("07".equals(rs.getString("jmslbdm"))){
					
					if("".equals(rs.getString("jmmj")) || rs.getString("jmmj") == null){
						qt.setFljmmj("0");
					}else{
						qt.setFljmmj(String.valueOf(rs.getBigDecimal("jmmj").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					if("".equals(rs.getString("jmse")) || rs.getString("jmmj") == null){
						qt.setFljmse("0");
					}else{
						qt.setFljmse(String.valueOf(rs.getBigDecimal("jmse").setScale(2,BigDecimal.ROUND_HALF_UP)));
					}
					
					
				}
	
			}
			map.put("00", js);
			map.put("01", nc);
			map.put("02", kn);
			map.put("03", yly);
			map.put("04", yy);
			map.put("05", yey);
			map.put("06", xx);
			map.put("07", qt);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return map;
	}
    
	public Map getSFZZLXMC(){
		
		Map map = new HashMap();
		map.put("02", "���֤");
		map.put("03", "����֤��");
		map.put("04", "����");
		map.put("05", "�۰�ͬ������֤");
		map.put("90", "����");
		return map;
		
	}
	//ռ����;����
	private Map getZDYTMC() throws BaseException{
		Map map = new HashMap();
		List zdytmc = new ArrayList();
		List zdytdm = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from dmdb.GD_DM_ZDYT order by zdytdm";
		conn = SfDBResource.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				zdytdm.add(rs.getString("zdytdm"));
				zdytmc.add(rs.getString("zdytmc"));
			}
			map.put("zdytmc", zdytmc);
			map.put("zdytdm", zdytdm);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
   	     	SfDBResource.freeConnection(conn);
	       }
		
		return map;
	}
	//�����������
	private Map getJMLBMC() throws BaseException{
		Map map = new HashMap();
		List jmslbmc = new ArrayList();
		List jmslbdm = new ArrayList();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select * from dmdb.GD_DM_JMSLB order by JMSLBDM";
		conn = SfDBResource.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				jmslbdm.add(rs.getString("JMSLBDM"));
				jmslbmc.add(rs.getString("JMSLBMC"));
			}
			map.put("jmslbmc", jmslbmc);
			map.put("jmslbdm", jmslbdm);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}finally {
   	     	SfDBResource.freeConnection(conn);
	       }
		
		return map;
	}
	
	//����˰��
	public List getSYSE_CX(Connection conn,String qxdm, GdzysSydjxxlrForm form)throws BaseException{
		List nd_list = new ArrayList();
		List list = new ArrayList();
		 PreparedStatement ps = null;
		 ResultSet rs = null;
		 String sql2 = "select a.nd from DMDB.GD_DM_SYSE a group by a.nd order by a.nd desc";
		 try {
			ps =  conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			while(rs.next()){
				nd_list.add(rs.getString("nd"));
			}
			rs.close();
			ps.close();
			//�ŵ�form�� 
			form.setNd_list(nd_list);
			System.out.println("nd==========="+nd_list.get(0).toString());
			
			String sql = "SELECT LX.SYSELXMC,LX.SYSELXDM,SE.SYSE,SE.ND FROM DMDB.GD_DM_SYSELX LX,DMDB.GD_DM_SYSE SE " +
		 		"WHERE LX.SYSELXDM = SE.SYSELXDM AND LX.ZXBZ = '0' AND SE.ZXBZ = '0' " +
		 		" AND SE.QXDM = ?" +
		 		"AND SE.ND = ?";
		
			ps =  conn.prepareStatement(sql);
			ps.setString(1, qxdm);
			ps.setString(2,form.getSysend());
			rs = ps.executeQuery();
			while(rs.next()){
				LabelValueBean bean = new LabelValueBean(rs.getString("SYSE"),rs.getString("SYSELXDM")+"-"+rs.getString("SYSE"));
				list.add(bean);
			}	
			rs.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException(e.getMessage());
		}
		
		return list;
	}
	
	
	
}

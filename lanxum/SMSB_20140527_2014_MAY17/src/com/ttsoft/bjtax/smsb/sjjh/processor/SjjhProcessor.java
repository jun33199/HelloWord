package com.ttsoft.bjtax.smsb.sjjh.processor;

/**
 * <p>Title: 扣缴企业所得税-扣缴企业所得税管理台帐Processer</p>
 *
 * <p>Description: 查询指定纳税人的扣缴企业所得税相关台帐信息</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author liuc
 * @version 1.0
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import oracle.sql.BLOB;

import org.apache.velocity.texen.util.FileUtil;

import weblogic.jdbc.vendor.oracle.OracleThinBlob;




import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;


import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web.DztzBO;
import com.ttsoft.bjtax.smsb.sjjh.web.SjjhBO;
import com.ttsoft.bjtax.smsb.sjjh.web.SjjhForm;
import com.ttsoft.bjtax.smsb.sjjh.web.SjjhHelper;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


public class SjjhProcessor implements Processor{
	
    public SjjhProcessor(){
    }

    /**
     * 应用处理转发器
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException{ 
    	  Object result = null;

          if (vo == null) {
              throw new NullPointerException();
          }


          switch (vo.getAction())
          {
              case CodeConstant.SJJH_QUERYFJJG:
                  result = doQueryDeptList(vo);
                  break;
              case CodeConstant.SJJH_BWBC:
                  doSave(vo);
                  break;
              case CodeConstant.SJJH_BUILDXML:
               	 SjjhForm form=(SjjhForm)vo.getData();
              	 String bwlx=form.getBwlx();
            	  if(bwlx.equals(SjjhHelper.FZJGFPBYWBW))
            		  result = fzjgfpbywbw(vo);
            	  if(bwlx.equals(SjjhHelper.HZSBSWDJXX))
                      result = hzsbswdjxx(vo);
            	  if(bwlx.equals(SjjhHelper.JDALYWBW))
                      result = jdalywbw(vo);
            	  if(bwlx.equals(SjjhHelper.SBZSXX))
                      result = sbzsxx(vo);
                  break;

              default:
                  throw new ApplicationException("用户执行了系统不支持的方法或功能.");
          }

          return result;      
    }    
    
    /**
     * 查询扣缴义务人信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private void doSave(VOPackage vo) throws BaseException{
        Connection con = null;
        PreparedStatement pstmt = null;
        SjjhForm sjjhForm=(SjjhForm)vo.getData();
        String kssj=sjjhForm.getSkssksrq();
        String jssj=sjjhForm.getSkssjsrq();
        String fj=sjjhForm.getDept();
        String blx=sjjhForm.getBwlx();
        String cjry=vo.getUserData().getYhid();
        String context=sjjhForm.getContext();
        ResultSet  rs = null;
        try{
            // 获取数据库连接
            con = SfDBResource.getConnection();
//            con.setAutoCommit(false);
            
            //删除
            String sqlDelete=" delete sbdb.sb_jl_qysdshzbf ";
            String sqlWhere=" where kssj=to_date('"+kssj+"','yyyymmdd') and jssj=to_date('"+jssj+"','yyyymmdd') " +
            				" and fj='"+fj+"' and blx='"+blx+"' and cjry='"+cjry+"'";
            pstmt = con.prepareStatement(sqlDelete+sqlWhere);   
            pstmt.executeUpdate();
            //插入
            String sql="insert into sbdb.sb_jl_qysdshzbf(kssj,jssj ,fj  ,blx ,context ,cjrq ,cjry) values " +
    		" (to_date('"+kssj+"','yyyymmdd'),to_date('"+jssj+"','yyyymmdd'),'"+fj+"','"+blx+"',EMPTY_BLOB(),sysdate,'"+cjry+"')";
            pstmt = con.prepareStatement(sql);   
            pstmt.executeUpdate();
            //更新bolb 
            
            
            
            pstmt = con.prepareStatement("SELECT context FROM sbdb.sb_jl_qysdshzbf "+sqlWhere+ " and ROWNUM = 1 ");
              rs = pstmt.executeQuery();
              Blob blob = null;
            rs.next();
            	blob =  rs.getBlob(1);
            
            
            OracleThinBlob  oblob=(OracleThinBlob)blob;
            OutputStream os = oblob.getBinaryOutputStream(); 
            BufferedOutputStream output = new BufferedOutputStream(os);
            BufferedInputStream input = new BufferedInputStream(new File(context).toURL().openStream()); 
    		  byte[] buff = new byte[oblob.getBufferSize()]; 
    		  //用做文件写入的缓冲 
    		  int bytesRead; 
    		  while(-1 != (bytesRead = input.read(buff, 0, buff.length))){ 
    		   output.write(buff, 0, bytesRead); 
    		  } 
    		os.close();
			output.close();
			input.close();
			
//			con.commit();
     

         
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rs!=null){
                	rs.close();
                }
                if(pstmt != null){pstmt.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }  
    }
    
   
    /**
     * 查询扣缴义务人信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryDeptList(VOPackage vo) throws BaseException{
        List list=new ArrayList();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;        
        String sql="select substr(swjgzzjgdm,0,2) swjgzzjgdm,swjgzzjgmc from dmdb.gy_dm_swjgzzjg where zxbs='0' and ccbs='1' order by swjgzzjgdm ";
        try{
            // 获取数据库连接
            con = SfDBResource.getConnection();
            pstmt = con.prepareStatement(sql);            
            rs = pstmt.executeQuery(sql);            
            while(rs.next()){            	
            	Map map=new HashMap();
            	map.put("CODE", nullToSpace(rs.getString(1)));
            	map.put("NAME", nullToSpace(rs.getString(2)));
            	list.add(map);
            }           
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null){pstmt.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }        
        return list;
    }
    
    
  
	 private Object fzjgfpbywbw(VOPackage vo) throws BaseException{
    	SjjhForm form=(SjjhForm)vo.getData();
    	String ksrq=form.getSkssksrq();
    	String jsrq=form.getSkssjsrq();
    	String dept=form.getDept();
    	
        SjjhBO bo=new SjjhBO();
        Connection con = null;
        PreparedStatement pstmtMain = null;
        PreparedStatement pstmtSub = null;
        ResultSet rsMain = null;
        ResultSet rsSub = null;
        
        

        String sqlMain=
        	" select b.nsrsbh col_1,a.nsrmc col_2,a.sbrq col_3 ,a.sksssqq col_4,a.sksssqz col_5,a.hc1 col_6,a.hc2 col_7,                 "+
        	"        (select c.xm from djdb.dj_jl_qyry c where a.nsrjsjdm=c.jsjdm and c.zwdm='04') col_8,a.swjgzzjgdm col_9 ,            "+
        	"        (select d.swjgzzjgmc from dmdb.gy_dm_swjgzzjg d where a.swjgzzjgdm=d.swjgzzjgdm) col_10,a.lrr col_11,               "+
        	"        a.lrr col_12,a.sbrq col_13,'' col_14 ,'' col_15, b.swjgzzjgdm col_16,a.cjsj col_17, a.lrsj col_18,                  "+
        	"        '' col_19, a.hc3 col_20,a.hc4 col_21, a.hc5 col_22 ,a.hc6 col_23 ,a.hc7 col_24 ,a.hc8 col_25,                       "+
        	"        a.hc9 col_26,a.nsrjsjdm||a.sknd||a.qh||a.bbqlx||a.sbdm pk                                                           "+
        	" from (select nsrmc,to_char(sbrq,'yyyymmdd') sbrq,to_char(sksssqq,'yyyymmdd') sksssqq,to_char(sksssqz,'yyyymmdd') sksssqz,  "+
        	"              swjgzzjgdm,lrr,to_char(cjsj,'yyyymmdd')cjsj,to_char(lrsj,'yyyymmdd')lrsj,nsrjsjdm,sknd,qh,bbqlx,sbdm,         "+
        	"              max(decode(hc,'1',yz)) hc1,max(decode(hc,'2',yz)) hc2,max(decode(hc,'3',yz)) hc3,                             "+
        	"              max(decode(hc,'4',yz)) hc4,max(decode(hc,'5',yz)) hc5,max(decode(hc,'6',yz)) hc6,                             "+
        	"              max(decode(hc,'7',yz)) hc7,max(decode(hc,'8',yz)) hc8,max(decode(hc,'9',yz)) hc9                              "+
        	"        from sbdb.sb_jl_qysdssbb_zb_jd where sbdm='30' and (yz = '1' or yz='2') CONDITION                                                             "+
        	"        group by nsrmc,sbrq,sksssqq,sksssqz,swjgzzjgdm,lrr,cjsj,lrsj,nsrjsjdm,sknd,qh,bbqlx,sbdm) a,                        "+
        	"      sbdb.sb_jl_qysds_nsrjbxxb b                                                                                           "+
        	" where a.nsrjsjdm=b.nsrjsjdm(+) and a.sknd=b.nd(+)                                                                          ";

 	
        
        String condition="";
        if(ksrq != null && ksrq.trim().length()>0){
        	condition+=" and sksssqq>= to_date('"+ksrq+"','yyyymmdd')" ;        	
        }
        if(jsrq != null && jsrq.trim().length()>0){
        	condition+=" and sksssqz<= to_date('"+jsrq+"','yyyymmdd')" ;
        }        	
   		if(dept != null && dept.trim().length()>0){
   			condition+=" and substr(swjgzzjgdm,0,2)='"+dept+"'";
   		}   		
   		sqlMain=sqlMain.replaceAll("CONDITION", condition);
   		
   		
   		
   		String sqlSub=
		" select max(decode(hc,'10',yz)) col_1,max(decode(hc,'11',yz)) col_2,max(decode(hc,'12',yz)) col_3,     "+
		"        max(decode(hc,'13',yz)) col_4,max(decode(hc,'14',yz)) col_5,max(decode(hc,'15',yz)) col_6,     "+
		"        max(decode(hc,'16',yz)) col_7,max(decode(hc,'17',yz)) col_8,nsrjsjdm||sknd||qh||bbqlx||sbdm fk "+
		" from sbdb.sb_jl_qysdssbb_cb_jd where sbdm='30'                                                        "+
		" group by nsrjsjdm,sknd,qh,bbqlx,sbdm                                                                  ";
   		
        try{
            // 获取数据库连接
            con = SfDBResource.getConnection();
            
            // 主数据取数
            pstmtMain = con.prepareStatement(sqlMain);            
            rsMain = pstmtMain.executeQuery(sqlMain);       
            ResultSetMetaData metaMain=rsMain.getMetaData();   
            
            Map mainMap=new HashMap();            
            while (rsMain.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaMain.getColumnCount();i++){
            		 map.put(metaMain.getColumnName(i).toUpperCase(), rsMain.getString(i));
                 }
            	 map.put("FZJG_MAP_LIST", new ArrayList());
                mainMap.put(rsMain.getString(metaMain.getColumnCount()), map);
                
            }      
           
            // 子数据取数
            pstmtSub = con.prepareStatement(sqlSub);           
            rsSub = pstmtSub.executeQuery(sqlSub);  
            ResultSetMetaData metaSub=rsSub.getMetaData(); 
            
            while (rsSub.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaSub.getColumnCount();i++){
            		 map.put(metaSub.getColumnName(i).toUpperCase(), rsSub.getString(i));
                 }
            	 if(mainMap.get(rsSub.getString(metaSub.getColumnCount()))!=null){
            		  Map mainMapChild=(Map)mainMap.get(rsSub.getString(metaSub.getColumnCount()));
            		  List fzjgMapList=(List)mainMapChild.get("FZJG_MAP_LIST");
            		  fzjgMapList.add(map);            		 
            		  mainMapChild.put("FZJG_MAP_LIST", fzjgMapList);
            	 }                
            }   
            List listMain=new ArrayList();            
            for(Iterator it=mainMap.keySet().iterator();it.hasNext();){
            	Map map=(Map)mainMap.get(it.next());
            	listMain.add(map);
            }
            bo.setResultList(listMain);
            
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rsMain != null) {rsMain.close();}
                if(pstmtMain != null){pstmtMain.close();}
                if(rsSub != null) {rsSub.close();}
                if(pstmtSub != null){pstmtSub.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }        
        return bo;
    }
    
    private Object hzsbswdjxx(VOPackage vo) throws BaseException{
    	SjjhForm form=(SjjhForm)vo.getData();
    	String ksrq=form.getSkssksrq();
    	String jsrq=form.getSkssjsrq();
    	String dept=form.getDept();
        String sqlMain=
        	" select  a.swdjzh col_1, a.swjgzzjgdm col_2, to_char(a.cjrq,'yyyymmdd') col_3,                                                                       "+
        	"          (select to_char(max(b.bgrq),'yyyymmdd') from  djdb.dj_jl_bgsj_ls b where a.jsjdm=b.jsjdm ) col_4 ,                                         "+
        	"          a.nsrmc col_5, j.col_6 col_6,j.col_7 col_7,j.col_8 col_8, a.jydz col_9 ,j.col_10 col_10,j.col_11 col_11,                                   "+
        	"          (select dz1.zjlsgxdm from dmdb.sb_dm_lsgxdz dz1 where a.lsgxdm=dz1.dslsgxdm) col_12,                                                       "+
        	"          (select dz2.zjhydm from dmdb.sb_dm_hydmdz dz2 where a.gjbzhydm=dz2.dshydm) col_13,                                                         "+
        	"          (select dz3.zjdjzclxdm from dmdb.sb_dm_djzclxdz dz3 where a.djzclxdm=dz3.dsdjzclxdm) col_14,                                               "+
        	"          to_char(a.qrrq,'yyyymmdd') col_15,                                                                                                         "+
        	"          (select dz4.zjkjzddm from dmdb.sb_dm_qykjzddz dz4 where a.kjzddm=dz4.dskjzddm) col_16,                                                     "+
        	"          '' col_17,'' col_18, a.nsrzt col_19,'' col_20, a.scjxdm col_21, '' col_22, '' col_23,                                                      "+
        	"          (select min(g.xydj) from  sfdb.sf_jl_xydj g  where  a.jsjdm=g.jsjdm ) col_24, '' col_25,                                                   "+
        	"          (select c.cyfldm from  dmdb.gy_dm_gjbzhy c where c.gjbzhydm=a.gjbzhydm) col_26,                                                            "+
        	"          '' col_27, '' col_28, '' col_29, '' col_30, '' col_31,                                                                                     "+
        	"          (select dz5.zjdwxzdm from dmdb.sb_dm_dwxzdz dz5 where h.dwxz=dz5.dsdwxzdm) col_32,                                                         "+
        	"          a.gdsgghbs col_33, '' col_34,decode(a.djzclxdm,'410','Y','420','Y','N') col_35, h.zjmc col_36,                                             "+
        	"          a.zcdz col_37,a.zcdzyb col_38,a.jydzyb col_39, '' col_40, to_char(a.kydjrq,'yyyymmdd') col_41,                                             "+
        	"          j.col_42 col_42,j.col_43 col_43,a.zzjgdm col_44,                                                                                           "+
        	"          h.pzcljgdm col_45, h.zjmc col_46, a.yyzzh col_47,to_char(h.xcjyqx_begin,'yyyymmdd') col_48,                                                "+
        	"          to_char(h.xcjyqx_end ,'yyyymmdd')col_49,                                                                                                   "+
        	"          (select dz6.zjhydm from dmdb.sb_dm_hydmdz dz6 where a.gjbzhydm=dz6.dshydm) col_50,                                                         "+
        	"          a.jyfw col_51, '' col_52,to_char(h.xcjyqx_begin,'yyyymmdd') col_53,to_char(h.xcjyqx_end,'yyyymmdd') col_54,                                "+
        	"          to_char(h.fzrq,'yyyymmdd')  col_55, a.zczbje col_56,a.zczbbzdm col_57,a.zczbje col_58,                                                     "+
        	"            (select dz7.zjbzdm from dmdb.sb_dm_bzdz dz7 where a.zczbbzdm=dz7.dsbzdm)  col_59,                                                        "+
        	"          h.cyrs col_60,h.wjrs col_61,a.jydz col_62,a.zgbmdm col_63, '' col_64, '' col_65, h.pzcljgdm col_66,                                        "+
        	"          a.yyzzh col_67,to_char(a.kydjrq,'yyyymmdd') col_68, '' col_69, '' col_70, '' col_71, '' col_72,                                            "+
        	"          '' col_73,j.col_74 col_74,'' col_75, '' col_76, '' col_77, '' col_78, '' col_79, '' col_80, '' col_81, '' col_82,                          "+
        	"          to_char(a.qrrq,'yyyymmdd') col_83,a.jydzlxdm col_84,a.hsxsdm col_85,                                                                       "+
        	"           (select dz8.zjsykjzddm from dmdb.sb_dm_sykjzddz dz8 where a.kjzddm=dz8.dssykjzddm) col_86,a.qyzy col_87,                                  "+
        	"          j.col_88 col_88,j.col_89 col_89,j.col_90 col_90 ,j.col_91 col_91, j.col_92 col_92,j.col_93 col_93,                                         "+
        	"          j.col_94 col_94 ,j.col_95 col_95,j.col_96 col_96 ,j.col_97 col_97 ,j.col_98 col_98,                                                        "+
        	"          i.mc col_99, i.swdjzh col_100,i.gddh col_101, i.dzyx col_102,substr(a.swjgzzjgdm,0,2) col_103,                                             "+
        	"          a.swjgzzjgdm col_104,h.hhr col_105,h.ggrs col_106,a.zrrtzblhj col_107, a.wzztzblhj col_108,                                                "+
        	"          h.gytzbl col_109,'' col_110,h.gjbzhydm_one col_111,h.gjbzhydm_two col_112,h.gjbzhydm_three col_113,                                        "+
        	"          a.jyfw col_114,to_char(h.kyslrq,'yyyymmdd') col_115,'' col_116,'' col_117,'' col_118,'' col_119,                                           "+
        	"          h.gjbzhydm_one col_120,h.gjbzhydm_two col_121,h.gjbzhydm_three col_122,d.zjgswdjzh col_123 ,                                               "+
        	" 		     d.nsrmc col_124,d.scjydz col_125,d.zjgjyfw col_126,d.frdbxm col_127,'' col_128, '' col_129, '' col_130,                                    "+
        	" 		     d.scjydzlxdh col_131,a.jsjdm pk                                                                                                            "+
        	"          from djdb.dj_jl_jbsj a, djdb.dj_jl_zjg d ,djdb.dj_jl_add h ,djdb.dj_jl_swdl i,                                                             "+
        	"          (select frxm col_6,(select bb.zjfrzjlxdm from dmdb.sb_dm_frzjlxdz bb where aa.frzjlx=bb.dsfrzjlxdm ) col_7,                                "+
        	"          frzjhm col_8,bsrxm col_10,bsrgddh col_11,                                                                                                  "+
        	"          (select bb.zjfrzjlxdm from dmdb.sb_dm_frzjlxdz bb where aa.bsrzjlx=bb.dsfrzjlxdm ) col_42,bsrzjhm col_43,                                  "+
        	"         cwxm col_74,frgddh col_88,fryddh col_89,frdzyx col_90,                                                                                      "+
        	"         (select bb.zjfrzjlxdm from dmdb.sb_dm_frzjlxdz bb where aa.cwzjlx=bb.dsfrzjlxdm ) col_91,cwzjhm col_92,cwgddh col_93,                       "+
        	"         cwyddh col_94,cwdzyx col_95,bsrgddh col_96,bsryddh col_97,bsrdzyx col_98,jsjdm                                                              "+
        	"    from (                                                                                                                                           "+
        	"         select a.jsjdm, max(decode(a.zwdm,'01',a.xm)) frxm , max(decode(a.zwdm,'04',a.xm)) cwxm , max(decode(a.zwdm,'05',a.xm)) bsrxm               "+
        	"          ,max(decode(a.zwdm,'01',a.zjlxdm)) frzjlx , max(decode(a.zwdm,'04',a.zjlxdm)) cwzjlx , max(decode(a.zwdm,'05',a.zjlxdm)) bsrzjlx           "+
        	"          ,max(decode(a.zwdm,'01',a.zjhm)) frzjhm , max(decode(a.zwdm,'04',a.zjhm)) cwzjhm , max(decode(a.zwdm,'05',a.zjhm)) bsrzjhm                 "+
        	"          ,max(decode(a.zwdm,'01',a.gddh)) frgddh , max(decode(a.zwdm,'04',a.gddh)) cwgddh , max(decode(a.zwdm,'05',a.gddh)) bsrgddh                 "+
        	"          ,max(decode(a.zwdm,'01',a.yddh)) fryddh , max(decode(a.zwdm,'04',a.yddh)) cwyddh , max(decode(a.zwdm,'05',a.yddh)) bsryddh                 "+
        	"          ,max(decode(a.zwdm,'01',a.dzyx)) frdzyx , max(decode(a.zwdm,'04',a.zjhm)) cwdzyx , max(decode(a.zwdm,'05',a.dzyx)) bsrdzyx                 "+
        	"           from djdb.dj_jl_qyry a where                                                                                                              "+
        	"           exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd f where (yz = '1' or yz='2') and a.jsjdm=f.nsrjsjdm CONDITION)                                     "+
        	"           group by a.jsjdm) aa) j                                                                                                                   "+
        	"  where a.jsjdm=d.jsjdm(+) and a.jsjdm=h.jsjdm(+) and a.jsjdm=i.jsjdm(+) and a.swdjzh=i.swdjzh(+)  and a.jsjdm=j.jsjdm                               "+
        	"  and exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd f where (yz = '1' or yz='2') and a.jsjdm=f.nsrjsjdm CONDITION)                                      ";      
        String sqlSubZczb=
        	" select '1' col_1,                                                                                             "+
        	"        (select dz1.zjbzdm from dmdb.sb_dm_bzdz dz1 where a.zczbbzdm=dz1.dsbzdm  ) col_2 ,                     "+
        	"          a.zczbje col_3,a.jsjdm fk                                                                            "+
        	" from   djdb.dj_jl_jbsj a where exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd f where (yz = '1' or yz='2')   "+
        	" 		 and a.jsjdm=f.nsrjsjdm CONDITION)                                                                                 ";
        
        String sqlSubTzf=
        	" select row_number() over (partition by a.jsjdm order by a.tzbl desc) col_1,'' col_2, a.tzfmc col_3,            "+       
        	"        (select dz1.zjgjdm from dmdb.sb_dm_gjdmdz dz1 where a.gjdz=dz1.dsgjdm) col_4,                           "+
        	"        a.tzje col_5,''col_6 ,a.tzbl col_7,a.fpbl col_8,'' col_9,'' col_10,                                     "+
        	"        (select dz2.zjtzfxzdm from dmdb.sb_dm_tzfxzdz dz2 where a.tzfjjxz=dz2.dstzfxzdm)  col_11,               "+
        	"        (select dz3.zjfrzjlxdm from dmdb.sb_dm_frzjlxdz dz3 where a.zjlxdm=dz3.dsfrzjlxdm)  col_12,             "+
        	"        a.zjhm col_13,'' col_14,a.jsjdm fk                                                                      "+
        	" from   djdb.dj_jl_tzf a where exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd f where (yz = '1' or yz='2')     "+
        	"        and a.jsjdm=f.nsrjsjdm CONDITION)                                                                                ";
        
        String sqlSubFzjg=
        	" select row_number() over (partition by a.jsjdm order by a.cjrq) col_1,a.fzjgswdjzh col_2,a.nsrmc col_3,                "+
        	"        a.fzdh col_4,a.zcdz col_5,a.jsjdm fk                                                                            "+
        	" from   djdb.dj_jl_fzjg a where exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd f where (yz='1' or yz='2')              "+
        	"        and a.jsjdm=f.nsrjsjdm CONDITION)                                                                               ";
        
        
        String condition="";
	        if(ksrq != null && ksrq.trim().length()>0){
	        	condition+=" and f.sksssqq>= to_date('"+ksrq+"','yyyymmdd')" ;        	
	        }
	        if(jsrq != null && jsrq.trim().length()>0){
	        	condition+=" and f.sksssqz<= to_date('"+jsrq+"','yyyymmdd')" ;
	        }        	
	   		if(dept != null && dept.trim().length()>0){
	   			condition+=" and substr(f.swjgzzjgdm,0,2)='"+dept+"'";
	   		}   			   	
	   	sqlMain=sqlMain.replaceAll("CONDITION", condition);
	   	sqlSubZczb=sqlSubZczb.replaceAll("CONDITION", condition);
	   	sqlSubTzf=sqlSubTzf.replaceAll("CONDITION", condition);
	   	sqlSubFzjg=sqlSubFzjg.replaceAll("CONDITION", condition);
	   	
	   	
	   	
	    SjjhBO bo=new SjjhBO();
        Connection con = null;
        PreparedStatement pstmtMain = null;
        ResultSet rsMain = null;
        
        PreparedStatement pstmtSubZczb = null;
        ResultSet rsSubZczb = null;
        
        PreparedStatement pstmtSubTzf = null;
        ResultSet rsSubTzf = null;
        
        PreparedStatement pstmtSubFzjg = null;
        ResultSet rsSubFzjg = null;
        
        try{
        	 // 获取数据库连接
            con = SfDBResource.getConnection();
            
            // 主数据取数
            pstmtMain = con.prepareStatement(sqlMain);            
            rsMain = pstmtMain.executeQuery(sqlMain);       
            ResultSetMetaData metaMain=rsMain.getMetaData();   
            
            Map mainMap=new HashMap();            
            while (rsMain.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaMain.getColumnCount();i++){
            		 map.put(metaMain.getColumnName(i).toUpperCase(), rsMain.getString(i));
                 }
            	 map.put("ZCZBTZZE_MAP_LIST", new ArrayList());
            	 map.put("TZFXX_MAP_LIST", new ArrayList());
            	 map.put("FZJGXX_MAP_LIST", new ArrayList());
                 mainMap.put(rsMain.getString(metaMain.getColumnCount()), map);
                
            }      
           
            // 子数据取数
            pstmtSubZczb = con.prepareStatement(sqlSubZczb);           
            rsSubZczb = pstmtSubZczb.executeQuery(sqlSubZczb);  
            ResultSetMetaData metaSubZczb=rsSubZczb.getMetaData(); 
            
            while (rsSubZczb.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaSubZczb.getColumnCount();i++){
            		 map.put(metaSubZczb.getColumnName(i).toUpperCase(), rsSubZczb.getString(i));
                 }
            	 if(mainMap.get(rsSubZczb.getString(metaSubZczb.getColumnCount()))!=null){
            		  Map mainMapChild=(Map)mainMap.get(rsSubZczb.getString(metaSubZczb.getColumnCount()));
            		  List zczbtzzeMapList=(List)mainMapChild.get("ZCZBTZZE_MAP_LIST");
            		  zczbtzzeMapList.add(map);            		 
            		  mainMapChild.put("ZCZBTZZE_MAP_LIST", zczbtzzeMapList);
            	 }                
            } 
            
            // 子数据取数
            pstmtSubTzf = con.prepareStatement(sqlSubTzf);           
            rsSubTzf = pstmtSubTzf.executeQuery(sqlSubTzf);  
            ResultSetMetaData metaSubTzf=rsSubTzf.getMetaData(); 
            
            while (rsSubTzf.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaSubTzf.getColumnCount();i++){
            		 map.put(metaSubTzf.getColumnName(i).toUpperCase(), rsSubTzf.getString(i));
                 }
            	 if(mainMap.get(rsSubTzf.getString(metaSubTzf.getColumnCount()))!=null){
            		  Map mainMapChild=(Map)mainMap.get(rsSubTzf.getString(metaSubTzf.getColumnCount()));
            		  List tzfxxMapList=(List)mainMapChild.get("TZFXX_MAP_LIST");
            		  tzfxxMapList.add(map);            		 
            		  mainMapChild.put("TZFXX_MAP_LIST", tzfxxMapList);
            	 }                
            } 
            
            
            
            // 子数据取数
            pstmtSubFzjg = con.prepareStatement(sqlSubFzjg);           
            rsSubFzjg = pstmtSubFzjg.executeQuery(sqlSubFzjg);  
            ResultSetMetaData metaSubFzjg=rsSubFzjg.getMetaData(); 
            
            while (rsSubFzjg.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=metaSubFzjg.getColumnCount();i++){
            		 map.put(metaSubFzjg.getColumnName(i).toUpperCase(), rsSubFzjg.getString(i));
                 }
            	 if(mainMap.get(rsSubFzjg.getString(metaSubFzjg.getColumnCount()))!=null){
            		  Map mainMapChild=(Map)mainMap.get(rsSubFzjg.getString(metaSubFzjg.getColumnCount()));
            		  List fzjgxxMapList=(List)mainMapChild.get("FZJGXX_MAP_LIST");
            		  fzjgxxMapList.add(map);            		 
            		  mainMapChild.put("FZJGXX_MAP_LIST", fzjgxxMapList);
            	 }                
            } 
            
            List listMain=new ArrayList();            
            for(Iterator it=mainMap.keySet().iterator();it.hasNext();){
            	Map map=(Map)mainMap.get(it.next());
            	listMain.add(map);
            }
            bo.setResultList(listMain);
            
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rsMain != null) {rsMain.close();}
                if(rsSubZczb != null) {rsSubZczb.close();}
                if(rsSubTzf != null) {rsSubTzf.close();}
                if(rsSubFzjg != null) {rsSubFzjg.close();}
                if(pstmtMain != null){pstmtMain.close();}
                if(pstmtSubZczb != null){pstmtSubZczb.close();}
                if(pstmtSubTzf != null){pstmtSubTzf.close();}
                if(pstmtSubFzjg != null){pstmtSubFzjg.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }
        
        return bo;
    }
    
    
    /**
     * 查询扣缴义务人信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object jdalywbw(VOPackage vo) throws BaseException{
    	SjjhForm form=(SjjhForm)vo.getData();
    	String ksrq=form.getSkssksrq();
    	String jsrq=form.getSkssjsrq();
    	String dept=form.getDept();
    	String bwlx=form.getBwlx();
    	 
    	System.out.println("dept is "+ dept);
    	System.out.println("bwlx is "+ bwlx);

    	
        SjjhBO bo=new SjjhBO();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        

        String sql=
        	" select aa.nsrsbh col_1,aa.nsrmc col_2,aa.sbrq col_3,aa.sksssqq col_4, aa.sksssqz col_5, '' col_6,'' col_7,                 "+
        	"        (select bb.xm from djdb.dj_jl_qyry bb where aa.nsrjsjdm=bb.jsjdm and bb.zwdm='04') col_8,                           "+
        	"        '' col_9, '' col_10,'' col_11,'' col_12,aa.swjgzzjgdm col_13,                                                       "+
        	"        (select cc.swjgzzjgmc  from dmdb.gy_dm_swjgzzjg cc where aa.swjgzzjgdm=cc.swjgzzjgdm) col_14,                       "+
        	"        aa.lrr col_15,aa.lrr col_16,aa.cjsj col_17,'' col_18,'' col_19,aa.swjgzzjgdm col_20,aa.cjsj col_21,                 "+
        	"        aa.lrsj col_22,'' col_23,aa.HC3 col_24,aa.HC4 col_25,aa.HC5 col_26,aa.HC7 col_27,aa.HC8 col_28,                     "+
        	"        aa.HC3_SUM col_29,aa.HC4_SUM col_30,aa.HC5_SUM col_31,aa.HC7_SUM col_32,aa.HC8_SUM col_33,                          "+
        	"        aa.HC9_SUM col_34,aa.HC10_SUM col_35,aa.HC12 col_36,aa.HC14 col_37,aa.HC11_SUM col_38,                              "+
        	"        aa.HC12_SUM col_39,aa.HC14_SUM col_40,aa.HC15 col_41,aa.HC15_SUM col_42,aa.HC16 col_43,                             "+
        	"        aa.HC17 col_44,aa.HC18 col_45,aa.HC19 col_46,aa.HC20 col_47,aa.HC16_SUM col_48,aa.HC17_SUM col_49,                  "+
        	"        aa.HC18_SUM col_50,aa.HC19_SUM col_51,aa.HC20_SUM col_52                                                            "+
        	" from                                                                                                                       "+
        	"  (select a.nsrjsjdm,c.nsrsbh  nsrsbh,c.nsrmc nsrmc,to_char(b.sbrq,'yyyymmdd') sbrq,to_char(b.sksssqq,'yyyymmdd') sksssqq,  "+
        	"  to_char(b.sksssqz,'yyyymmdd') sksssqz, b.swjgzzjgdm swjgzzjgdm,                                                           "+
        	"  b.lrr,to_char(b.cjsj,'yyyymmdd') cjsj ,to_char(b.lrsj,'yyyymmdd') lrsj,                                                   "+
        	"   max(decode(a.hc,'3',a.yz)) hc3,max(decode(a.hc,'4',a.yz)) hc4,max(decode(a.hc,'5',a.yz)) hc5,                            "+
        	"   max(decode(a.hc,'7',a.yz)) hc7,max(decode(a.hc,'8',a.yz)) hc8,sum(decode(a.hc,'3',a.yz)) hc3_sum,                        "+
        	"   sum(decode(a.hc,'4',a.yz)) hc4_sum,sum(decode(a.hc,'5',a.yz)) hc5_sum,sum(decode(a.hc,'7',a.yz)) hc7_sum,                "+
        	"   sum(decode(a.hc,'8',a.yz)) hc8_sum,sum(decode(a.hc,'9',a.yz)) hc9_sum,max(decode(a.hc,'10',a.yz)) hc10_sum,              "+
        	"   max(decode(a.hc,'12',a.yz)) hc12,max(decode(a.hc,'14',a.yz)) hc14,max(decode(a.hc,'11',a.yz)) hc11_sum,                  "+
        	"   sum(decode(a.hc,'12',a.yz)) hc12_sum,sum(decode(a.hc,'14',a.yz)) hc14_sum,max(decode(a.hc,'15',a.yz)) hc15,              "+
        	"   sum(decode(a.hc,'15',a.yz)) hc15_sum,max(decode(a.hc,'16',a.yz)) hc16,max(decode(a.hc,'17',a.yz)) hc17,                  "+
        	"   max(decode(a.hc,'18',a.yz)) hc18,max(decode(a.hc,'19',a.yz)) hc19,max(decode(a.hc,'20',a.yz)) hc20,                      "+
        	"   sum(decode(a.hc,'16',a.yz)) hc16_sum,sum(decode(a.hc,'17',a.yz)) hc17_sum,sum(decode(a.hc,'18',a.yz)) hc18_sum,          "+
        	"   sum(decode(a.hc,'19',a.yz)) hc19_sum,sum(decode(a.hc,'20',a.yz)) hc20_sum                                                "+
        	"   from sbdb.sb_jl_qysdssbb_cb_jd a,sbdb.sb_jl_qysdssbb_zb_jd b,sbdb.sb_jl_qysds_nsrjbxxb c                                 "+
        	"   where a.nsrjsjdm=b.nsrjsjdm(+) and a.BBQLX=b.BBQLX(+) and a.QH=b.QH(+)                                                   "+
        	"   and a.SKND=b.SKND(+) and a.SBDM=b.SBDM(+) and a.HC=b.HC(+) and a.nsrjsjdm=c.nsrjsjdm(+)                                  "+
        	"   and b.sbbm='28' and (b.yz = '1' or b.yz='2')  " +
        	"   CONDITION                                                                          "+
        	" group by a.nsrjsjdm,c.nsrsbh,c.nsrmc,b.sbrq,b.sksssqq,b.sksssqz,b.swjgzzjgdm,b.lrr,b.cjsj,b.lrsj)aa                        ";
        
        
        String condition="";
        if(ksrq != null && ksrq.trim().length()>0){
        	condition+=" and b.sksssqq>= to_date('"+ksrq+"','yyyymmdd')" ;        	
        }
        if(jsrq != null && jsrq.trim().length()>0){
        	condition+=" and b.sksssqz<= to_date('"+jsrq+"','yyyymmdd')" ;
        }        	
   		if(dept != null && dept.trim().length()>0){
   			condition+=" and substr(b.swjgzzjgdm,0,2)='"+dept+"'";
   		}   			   	

   		sql=sql.replaceAll("CONDITION", condition);
        try{
        	// 获取数据库连接
            con = SfDBResource.getConnection();
            pstmt = con.prepareStatement(sql);            
            rs = pstmt.executeQuery(sql);            
            List list=new ArrayList();
            ResultSetMetaData meta=rs.getMetaData();    
            
            while (rs.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=meta.getColumnCount();i++){
            		 map.put(meta.getColumnName(i).toUpperCase(), rs.getString(i));
                 }
                list.add(map);
            }      
            bo.setResultList(list);
            
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null){pstmt.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }
        
        return bo;
    }
    
    
    /**
     * 查询扣缴义务人信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object sbzsxx(VOPackage vo) throws BaseException{
    	SjjhForm form=(SjjhForm)vo.getData();
    	String ksrq=form.getSkssksrq();
    	String jsrq=form.getSkssjsrq();
    	String dept=form.getDept();   	
        SjjhBO bo=new SjjhBO();
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        

        String sql=

	        " select a.jkpzh col_1,a.jkpzh col_2, b.swdjzh col_3,to_char(a.skssksrq,'yyyymmdd') col_4,                           "+  
	        " to_char(a.skssjsrq,'yyyymmdd') col_5,b.swjgzzjgdm col_6, to_char(a.cjrq,'yyyymmdd') col_7,                         "+  
	        " to_char(a.lrrq,'yyyymmdd') col_8,'' col_9 ,'20090101' col_10,a.rkje col_11,to_char(a.sbrq,'yyyymmdd') col_12,      "+  
	        " to_char(a.xjrq,'yyyymmdd') col_13 ,to_char(a.cjrq,'yyyymmdd') col_14 ,to_char(a.zyrq,'yyyymmdd') col_15,           "+  
	        " to_char(a.hxrq,'yyyymmdd') col_16,                                                                                 "+
	        " (select dz1.zjyskmdm from dmdb.sb_dm_yskmdz dz1 where a.yskmdm=dz1.dsyskmdm) col_17,                               "+
	        " c.zyfcbl col_18, c.sjfcbl col_19,c.qxfcbl col_20,                                                                  "+
	        " '0' col_21, '0' col_22,'0' col_23, a.swjgzzjgdm col_24                                                             "+  
	        " from sbdb.sb_jl_sbjkzb a,djdb.dj_jl_jbsj b,dmdb.kj_dm_yskm c                                                       "+  
	        " where a.jsjdm=b.jsjdm and a.yskmdm=c.yskmdm and a.szdm='30' CONDITION_1 and                                        "+
	        " exists (select 1 from sbdb.sb_jl_qysdssbb_zb_jd d where a.jsjdm=d.nsrjsjdm and (d.yz='1' or d.yz='2') CONDITION_2 )";
        
        String condition_1="";
        String condition_2="";
        if(ksrq != null && ksrq.trim().length()>0){
        	condition_1+=" and a.skssksrq>= to_date('"+ksrq+"','yyyymmdd')" ;
        	condition_2+=" and d.sksssqq>= to_date('"+ksrq+"','yyyymmdd')" ;        	
        }
        if(jsrq != null && jsrq.trim().length()>0){
        	condition_1+=" and a.skssjsrq<= to_date('"+jsrq+"','yyyymmdd')" ;
   		   	condition_2+=" and d.sksssqz<= to_date('"+jsrq+"','yyyymmdd')" ;
        }        	
   		if(dept != null && dept.trim().length()>0){
   			condition_2+=" and substr(d.swjgzzjgdm,0,2)='"+dept+"'";
   		}   		
   		sql=sql.replaceAll("CONDITION_1", condition_1).replaceAll("CONDITION_2", condition_2);
        try{
            // 获取数据库连接
            con = SfDBResource.getConnection();
            pstmt = con.prepareStatement(sql);            
            rs = pstmt.executeQuery(sql);            
            List list=new ArrayList();
            ResultSetMetaData meta=rs.getMetaData();    
            
            while (rs.next()) {
            	Map map=new HashMap();
            	 for(int i=1;i<=meta.getColumnCount();i++){
            		 map.put(meta.getColumnName(i).toUpperCase(), rs.getString(i));
                 }
                list.add(map);
            }      
            bo.setResultList(list);
            
        }catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }finally{
            // 关闭数据库对象
            try{
                if(rs != null) {rs.close();}
                if(pstmt != null){pstmt.close();}
                SfDBResource.freeConnection(con);
            }catch(Exception e){
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }
        
        return bo;
    }
    
    private String nullToSpace(String value){
    	if(value == null || value.length()<=0)
    	return "";
    	else 
    	return value;
    }
    


      
}

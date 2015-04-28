package com.ttsoft.bjtax.shenbao.codetable.processor;

import java.beans.Beans;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ekernel.db.or.ORManager;
import com.syax.bjtax.shenbao.model.dm.DmConfig;
import com.syax.bjtax.shenbao.model.dm.Zlqddm;
import com.syax.bjtax.shenbao.util.DmBeanUtils;
import com.ttsoft.bjtax.shenbao.constant.CodeConstant;
import com.ttsoft.bjtax.shenbao.constant.CodeTable;
import com.ttsoft.bjtax.shenbao.constant.SzsmdmConstant;
import com.ttsoft.bjtax.shenbao.model.domain.Djzclx;
import com.ttsoft.bjtax.shenbao.model.domain.Gj;
import com.ttsoft.bjtax.shenbao.model.domain.Gjbzhy;
import com.ttsoft.bjtax.shenbao.model.domain.Jmfl;
import com.ttsoft.bjtax.shenbao.model.domain.Jnjpjsgzlx;
import com.ttsoft.bjtax.shenbao.model.domain.Lsgx;
import com.ttsoft.bjtax.shenbao.model.domain.Skfdqk;
import com.ttsoft.bjtax.shenbao.model.domain.Sklx;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Szsmyfjs;
import com.ttsoft.bjtax.shenbao.model.domain.Wbhs;
import com.ttsoft.bjtax.shenbao.model.domain.Wssbyy;
import com.ttsoft.bjtax.shenbao.model.domain.Yh;
import com.ttsoft.bjtax.shenbao.model.domain.Ysjc;
import com.ttsoft.bjtax.shenbao.model.domain.Yskm;
import com.ttsoft.bjtax.shenbao.model.domain.Zjlx;
import com.ttsoft.bjtax.shenbao.model.domain.Zjqyjmslx;
import com.ttsoft.bjtax.shenbao.model.domain.Zy;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DBAccess;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: 北京地税核心征管系统－－网上申报</p>
 * <p>Description: 取代码表</p>
 * <p>Copyright: (C) 2003-2004 清华同方软件股份有限公司，版权所有.</p>
 * <p>Company: 清华同方软件股份有限公司</p>
 * @author 开发一组－－张昕
 * @version 1.1
 */
public class CodeTableProcessor implements Processor
{
    public CodeTableProcessor()
    {
    }

    public Object process(VOPackage vo) throws BaseException
    {
        Connection con = null;
        try
        {
            con = DBResource.getConnection();
            ORManager orMgr = DBResource.getORManager();
            DBAccess db = new DBAccess(con, orMgr);

            int size = 0;
            Map ret = new HashMap();

            // 查询税种税目
            Vector cri = new Vector(1);
            cri.add("1=1 order by szsmdm");
            List szsmList = db.query(Szsm.class, cri);
            List szsmList_available = new ArrayList();
            size = szsmList.size();
            Map szsmMap = new HashMap(size);
            Map szsmMap_available = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Szsm s = (Szsm)szsmList.get(i);
                szsmMap.put(s.getSzsmdm(), s);

                if (s.getZxbs() != null && s.getZxbs().equals("0"))
                {
                    szsmMap_available.put(s.getSzsmdm(), s);
                    szsmList_available.add(s);
                }
            }
            ret.put(CodeTable.SZSM_MAP, szsmMap);
            ret.put(CodeTable.SZSM_LIST, szsmList);

            ret.put(CodeTable.SZSM_MAP_AVAILABLE, szsmMap_available);
            ret.put(CodeTable.SZSM_LIST_AVAILABLE, szsmList_available);
            
            // 税种税目附加税
            List fjsList = db.query(Szsmyfjs.class, null);
            
//            List wqFjsList = new ArrayList();  // 外企附加税List
//            for (int i=0; i<fjsList.size(); i++)
//            {
//                Szsmyfjs fjs = (Szsmyfjs)fjsList.get(i);
//                if (!(fjs.getFjsszsmdm().equals(SzsmdmConstant.CSWHJSS_YYS) ||
//                      fjs.getFjsszsmdm().equals(SzsmdmConstant.JYFFJ_YYS)))
//                {
//                    wqFjsList.add(fjs);
//                }
//            }
//            ret.put(CodeTable.SZSMFJS_LIST, fjsList);
//            ret.put(CodeTable.SZSMFJS_WQ_LIST, wqFjsList);
            
            //修改 时间 2010-12-2  @author 郭晓静
            //是外资带出相应附加税 （包括城市维护建设税和教育费附税）
            ret.put(CodeTable.SZSMFJS_LIST, fjsList);
            ret.put(CodeTable.SZSMFJS_WQ_LIST, fjsList);

            // 国籍
            List gjList = db.query(Gj.class, null);
            size = gjList.size();
            Map gjMap = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Gj gj = (Gj)gjList.get(i);
                gjMap.put(gj.getGjdqdm(), gj);
            }
            ret.put(CodeTable.GJ_LIST, gjList);
            ret.put(CodeTable.GJ_MAP, gjMap);

            // 证件类型
            List zjlxList = db.query(Zjlx.class, null);
            size = zjlxList.size();
            Map zjlxMap = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Zjlx zjlx = (Zjlx)zjlxList.get(i);
                zjlxMap.put(zjlx.getZjlxdm(), zjlx);
            }
            ret.put(CodeTable.ZJLX_LIST, zjlxList);
            ret.put(CodeTable.ZJLX_MAP, zjlxMap);

            // 职业
            List zyList = db.query(Zy.class, null);
            size = zyList.size();
            Map zyMap = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Zy zy = (Zy)zyList.get(i);
                zyMap.put(zy.getZydm(), zy);
            }
            ret.put(CodeTable.ZY_LIST, zyList);
            ret.put(CodeTable.ZY_MAP, zyMap);

            // 银行
            List yhList = db.query(Yh.class, null);
            size = yhList.size();
            Map yhMap = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Yh yh = (Yh)yhList.get(i);
                yhMap.put(yh.getYhdm(), yh);
            }
            ret.put(CodeTable.YH_LIST, yhList);
            ret.put(CodeTable.YH_MAP, yhMap);

            // 隶属关系
            List lsgxList = db.query(Lsgx.class, null);
            size = lsgxList.size();
            Map lsgxMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Lsgx lsgx = (Lsgx)lsgxList.get(i);
                lsgxMap.put(lsgx.getLsgxdm(), lsgx);
            }
            ret.put(CodeTable.LSGX_LIST, lsgxList);
            ret.put(CodeTable.LSGX_MAP, lsgxMap);

            // 登记注册类型
            List djzclxList = db.query(Djzclx.class, null);
            size = djzclxList.size();
            Map djzclxMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Djzclx djczlx = (Djzclx)djzclxList.get(i);
                djzclxMap.put(djczlx.getDjzclxdm(), djczlx);
            }
            ret.put(CodeTable.DJZCLX_LIST , djzclxList);
            ret.put(CodeTable.DJZCLX_MAP , djzclxMap);

            // 国家标准行业
            List gjbzhyList = db.query(Gjbzhy.class, null);
            size = gjbzhyList.size();
            Map gjbzhyMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Gjbzhy gjbzhy = (Gjbzhy)gjbzhyList.get(i);
                gjbzhyMap.put(gjbzhy.getGjbzhydm(), gjbzhy);
            }
            ret.put(CodeTable.GJBZHY_LIST  , gjbzhyList);
            ret.put(CodeTable.GJBZHY_MAP , gjbzhyMap);

            // 预算科目
            List yskmList = db.query(Yskm.class, null);
            size = yskmList.size();
            Map yskmMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Yskm yskm = (Yskm)yskmList.get(i);
                yskmMap.put(yskm.getYskmdm(), yskm);
            }
            ret.put(CodeTable.YSKM_LIST, yskmList);
            ret.put(CodeTable.YSKM_MAP, yskmMap);

            // 预算级次
            List ysjcList = db.query(Ysjc.class, null);
            size = ysjcList.size();
            Map ysjcMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Ysjc ysjc = (Ysjc)ysjcList.get(i);
                ysjcMap.put(ysjc.getYsjcdm(), ysjc);
            }
            ret.put(CodeTable.YSJC_LIST, ysjcList);
            ret.put(CodeTable.YSJC_MAP, ysjcMap);

            // 税务机关组织机构
            List swjgzzjgList = db.query(Swjgzzjg.class, null);
            size = swjgzzjgList.size();
            Map swjgzzjgMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Swjgzzjg swjgzzjg = (Swjgzzjg)swjgzzjgList.get(i);
                swjgzzjgMap.put(swjgzzjg.getSwjgzzjgdm(), swjgzzjg);
            }
            ret.put(CodeTable.SWJJZZJG_LIST, swjgzzjgList);
            ret.put(CodeTable.SWJJZZJG_MAP, swjgzzjgMap);

            // 税款类型
            List sklxList = db.query(Sklx.class, null);
            size = sklxList.size();
            Map sklxMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Sklx sklx = (Sklx)sklxList.get(i);
                sklxMap.put(sklx.getSklxdm(), sklx);
            }
            ret.put(CodeTable.SKLX_LIST, sklxList);
            ret.put(CodeTable.SKLX_MAP, sklxMap);

            // 减免分类
            List jmflList = db.query(Jmfl.class, null);
            size = jmflList.size();
            Map jmflMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Jmfl jmfl = (Jmfl)jmflList.get(i);
                jmflMap.put(jmfl.getJmfldm(), jmfl);
            }
            ret.put(CodeTable.JMFL_LIST, jmflList);
            ret.put(CodeTable.JMFL_MAP, jmflMap);
         
            //无税减免原因List  减免税项目  tujb 200404
            Vector criWssb = new Vector();
            criWssb.add("zxbs = '" + CodeConstant.WSSBYY_ZXBS_0 + "' order by wssbyydm");
            
            List wsyyList = db.query(Wssbyy.class, criWssb);
            size = wsyyList.size();
            Map wsyyMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
            	Wssbyy wsyy = (Wssbyy)wsyyList.get(i);
                wsyyMap.put(wsyy.getWssbyydm(), wsyy);
            }
            ret.put(CodeTable.WSYY_BASX_LIST, wsyyList);
            ret.put(CodeTable.WSYY_BASX_MAP, wsyyMap);
          
            //减免项目List 减免税项目  tujb 200404
            Vector criJmxm = new Vector();
            criJmxm.add("zxbz = '" + CodeConstant.JMXM_ZXBS_0 + "' and yxbs = '" + CodeConstant.JMXM_YXBS_0 + "' order by jmslxdm");
            
            List jmxmList = db.query(Zjqyjmslx.class, criJmxm);
            size = jmxmList.size();
            Map jmxmMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
            	Zjqyjmslx jmxm = (Zjqyjmslx)jmxmList.get(i);
            	jmxmMap.put(jmxm.getJmslxdm(), jmxm);
            }
            ret.put(CodeTable.JMXM_BASX_LIST, jmxmList);
            ret.put(CodeTable.JMXM_BASX_MAP, jmxmMap);
         
            // 查询具有减免条件的税种税目 tujb 200404
            Vector criSzsm = new Vector(1);
            criSzsm.add(" substr(szsmdm,1,2) in (select szdm from DMDB.Sb_Dm_Zjqyjmslx) order by szsmdm ");
            List szsmNewList = db.query(Szsm.class, criSzsm);
            List szsmList_New_available = new ArrayList();
            size = szsmNewList.size();
            Map szsmNewMap = new HashMap(size);
            Map szsmMap_New_available = new HashMap(size);
            for (int i=0; i<size; i++)
            {
                Szsm s = (Szsm)szsmNewList.get(i);
                szsmNewMap.put(s.getSzsmdm(), s);
                if (s.getZxbs() != null && s.getZxbs().equals("0"))
                {
                	szsmMap_New_available.put(s.getSzsmdm(), s);
                	szsmList_New_available.add(s);
                }
            }
            ret.put(CodeTable.SZSM_NEW_MAP, szsmNewMap);
            ret.put(CodeTable.SZSM_NEW_LIST, szsmNewList);

            ret.put(CodeTable.SZSM_MAP_NEW_AVAILABLE, szsmMap_New_available);
            ret.put(CodeTable.SZSM_LIST_NEW_AVAILABLE, szsmList_New_available);

            // 外币换算
            Calendar time = Calendar.getInstance();
            time.add(Calendar.MONTH, -1);

            Vector criWbhs = new Vector();
            criWbhs.add("ND = '" + String.valueOf(time.get(Calendar.YEAR)) + "'");
            criWbhs.add("YF = '" + String.valueOf(time.get(Calendar.MONTH) + 1) + "' order by bzdm");

            List wbhsList = db.query(Wbhs.class, criWbhs);
            size = wbhsList.size();
            Map wbhsMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Wbhs wbhs = (Wbhs)wbhsList.get(i);
                wbhsMap.put(wbhs.getBzdm(), wbhs);
            }
            ret.put(CodeTable.WBHS_LIST, wbhsList);
            ret.put(CodeTable.WBHS_MAP, wbhsMap);

            // 税款负担情况
            List skfdqkList = db.query(Skfdqk.class, null);
            size = skfdqkList.size();
            Map skfdqkMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
                Skfdqk skfdqk = (Skfdqk)skfdqkList.get(i);
                skfdqkMap.put(skfdqk.getSkfdqkdm(), skfdqk);
            }
            ret.put(CodeTable.SKFDQK_LIST, skfdqkList);
            ret.put(CodeTable.SKFDQK_MAP, skfdqkMap);
//获取减免备案代码数据
            System.out.println("--------getJmbaDm---------");
            
            
            //获取减免税备案 节能减排技术改造项目 
            Vector jnjpLxCri = new Vector(1);
            jnjpLxCri.add("zfbs='0' order by jnjpjsgzxmdm");
            List jnjpLxList = db.query(Jnjpjsgzlx.class, jnjpLxCri);
            size = jnjpLxList.size();
            Map jnjpLxMap = new HashMap(size);
            for(int i=0; i<size; i++)
            {
            	Jnjpjsgzlx jnjpjsgzlx = (Jnjpjsgzlx)jnjpLxList.get(i);
            	jnjpLxMap.put(jnjpjsgzlx.getJnjpjsgzxmdm(), jnjpjsgzlx);
            }
            ret.put(CodeTable.JMBA_JNJPJSGZXM_LIST, jnjpLxList);
            ret.put(CodeTable.JMBA_JNJPJSGZXM_MAP, jnjpLxMap);
            //获取减免备案代码数据
            System.out.println("--------getJnjpjsgzxmdm---------");
            
            getJmbaDms(ret);

            return ret;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "初始化代码表失败");
        }
        finally
        {
            DBResource.destroyConnection(con);
        }
    }

    public void getJmbaDms(Map ret) throws Exception{
        Connection con = null;
        try
        {
            con = DBResource.getConnection();
            PreparedStatement ps =null;
            ResultSet rs = null;
            for (int i=0;i<DmConfig.dmConfigs.length;i++){
            	List reList = new ArrayList();
            	Map reMap = new HashMap();
            	DmConfig df = DmConfig.dmConfigs[i];
            	PropertyDescriptor[] pds = DmBeanUtils.getProperties(df.getBeanClassName());
            	StringBuffer sqlbuffer = new StringBuffer();
            	sqlbuffer.append("select * from "+df.getTableName()+" WHERE ZFBS='0'");
            	ps =con.prepareStatement(sqlbuffer.toString());
            	rs = ps.executeQuery();
//                System.out.println("df.getBeanName() = "+df.getBeanClassName());
            	while(rs.next()){
            		Object bean = Beans.instantiate(DmBeanUtils.class.getClassLoader(),df.getBeanClassName());
            		String dmValue="";
            		for (int j=0;j<pds.length;j++){
            			PropertyDescriptor pd = pds[j];
            			
            			//System.out.println("col name="+pd.getName());
            			if (pd.getName().equals("class")){
            				continue;
            			}
        			    pd.getWriteMethod().invoke(bean,new Object[]{rs.getString(pd.getName())});
            			if (pd.getName().endsWith("DM") ){
//            			    pd.getWriteMethod().invoke(bean,new Object[]{rs.getString(pd.getName())});
            				dmValue = rs.getString(pd.getName());
            			}

            		}
            		reList.add(bean);
            		reMap.put(dmValue,bean);
            	}
                System.out.println("--------getJmbaDm "+df.getBeanName()+" size ="+reList.size());

            	ret.put(DmConfig.DM_PREFIX+df.getBeanName()+"_LIST",reList);
            	ret.put(DmConfig.DM_PREFIX+df.getBeanName()+"_MAP",reMap);

            }
            
            //获取资料清单代码表
			String sql = "select jmbasxdm,zlqdmc,zlqddm,sfkysc,synd  from dmdb.sf_dm_bazlqddm where zfbs='0' order by jmbasxdm,sfkysc,zlqddm";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
        	List qdList = new ArrayList();
        	List tmpList = new ArrayList();
        	Map qdMap = new HashMap();
        	Map baMap = new HashMap();
			while (rs.next()) {
				Zlqddm obj = new Zlqddm();
				obj.setJmbasxdm(rs.getString("jmbasxdm"));
				obj.setZlqddm(rs.getString("zlqddm"));
				obj.setZlqdmc(rs.getString("zlqdmc"));
				obj.setSfkysc(rs.getString("sfkysc"));
				obj.setSynd(rs.getString("synd"));
				if (baMap.containsKey(obj.getJmbasxdm())){
					tmpList.add(obj);
				}else{
					tmpList =new ArrayList();
					tmpList.add(obj);
					//System.out.println("Jmba-zlqd map key="+obj.getJmbasxdm());
					baMap.put(obj.getJmbasxdm(),tmpList);					
				}
				qdList.add(obj);
				qdMap.put(obj.getJmbasxdm()+obj.getZlqddm(),obj);
			}
			//System.out.println("codePRocessor  zlqdmapSize="+baMap.size());
            ret.put(CodeTable.JMBA_ZLQD_LIST, qdList);
            ret.put(CodeTable.JMBA_ZLQD_MAP, qdMap);
            ret.put(CodeTable.JMBA_JMBAZLQD_MAP, baMap);
            
            
        	rs.close();
        	ps.close();
        }
        catch (Exception ex)
        {
            throw ex;
        }
        finally
        {
            DBResource.destroyConnection(con);
        }

    }
}

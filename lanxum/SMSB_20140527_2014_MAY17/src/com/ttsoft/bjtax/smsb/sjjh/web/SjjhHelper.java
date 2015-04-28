package com.ttsoft.bjtax.smsb.sjjh.web;

/**
 * <p>Title: 扣缴企业所得税-备案登记表Action帮助类</p>
 *
 * <p>Description: 相关公用方法</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */



import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;








public class SjjhHelper
{	
	public final static String  HZSBSWDJXX= "1";
	public final static String  SBZSXX= "2";
	public final static String  JDALYWBW= "3";
	public final static String  FZJGFPBYWBW= "4";	
	public static final String _L_0_1="L:0,1";                                            
	public static final String _L_0_2="L:0,2";                                              
	public static final String _L_0_3="L:0,3";                                              
	public static final String _L_0_4="L:0,4";                                              
	public static final String _L_0_6="L:0,6";
	public static final String _L_0_9="L:0,9";  
	public static final String _L_0_10="L:0,10";                                              
	public static final String _L_0_11="L:0,11";                                              
	public static final String _L_0_20="L:0,20";                                        
	public static final String _L_0_30="L:0,30";                                           
	public static final String _L_0_50="L:0,50";
	public static final String _L_0_60="L:0,60";                                           
	public static final String _L_0_80="L:0,80";
	public static final String _L_0_100="L:0,100";
	public static final String _L_0_120="L:0,120";
	public static final String _L_0_1000="L:0,1000";  
	public static final String _L_0_4000="L:0,4000";
	public static final String _L_1_2="L:1,2";                         
	public static final String _L_6_9="L:6,9";     
	public static final String _F_1_6="F:1,6";                      
	public static final String _F_4_6="F:4,6";                      
	public static final String _F_14_2="F:14,2";                      
	public static final String _D="D";                      
	public static final String _I="I";
	
	
	
	
	

    /**
     * 初始化页面下拉信息
     * @param request HttpServletRequest
     * @throws ApplicationException
     */
    public static void initialPageSelectItem(HttpServletRequest request,List fjjgList) throws ApplicationException
    {
        // 获取session
        HttpSession session = request.getSession(false);
        // 获取国家列表
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_DEPT) == null) {
            String[][] dept = new String[fjjgList.size()][2];
            for (int i = 0; i < fjjgList.size(); i++) {
            	Map map=(Map)fjjgList.get(i);
            	dept[i][0] = (String)map.get("CODE");
            	dept[i][1] = (String)map.get("NAME");
            }
            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_DEPT, dept);
        }    
        if (session.getAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BWLX) == null) {
            String[][] bwlx = new String[4][2];
            
            bwlx[0][0] =HZSBSWDJXX;
        	bwlx[0][1] ="汇总申报税务登记信息业务报文";
        	
            bwlx[1][0] =SBZSXX;
        	bwlx[1][1] ="申报征收信息业务报文";
            
        	bwlx[2][0] =JDALYWBW;
        	bwlx[2][1] ="2008版企业所得税月(季)度申报表A类业务报文";
            
        	bwlx[3][0] =FZJGFPBYWBW;
        	bwlx[3][1] ="2008版企业所得税汇总纳税分支机构分配表业务报文";

            session.setAttribute(CodeConstant.PAGE_SELECT_ITEMS_SESSION_KEY_BWLX, bwlx);
        } 
    }

    /**
     * 报文业务分派
     * @param list processor返回的报文数据
     * @param bwlx 报文类型
     * @return 报文对应javaBean 
     */
	public static Object rsToBean(List list, String bwlx){
		Object obj=new Object();
		
		if(bwlx.equals(SBZSXX)){//申报征收信息业务报文
			obj=sbzsxx(list);
		}
		if(bwlx.equals(HZSBSWDJXX)){//
			obj=hzsbswdjxx(list);
		}
		if(bwlx.equals(JDALYWBW)){
			obj=jdalywbw(list);
		}
		if(bwlx.equals(FZJGFPBYWBW)){
			obj=fzjgfpbywbw(list);
		}
		return obj;
	}
	
	/**
	 * 申报征收信息业务报文
	 * @param list processor返回的报文数据
	 * @return 报文对应javaBean
	 */
	private static Object sbzsxx(List list){
		com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxML tml=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.TaxML();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			
			//vSbZsxxYwbwItem
			com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem vSbZsxxYwbwItem=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxxYwbwItem();
				//sbZsxx
				com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxx sbZsxx=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.SbZsxx();
					//Head/////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Head head=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Head();			
						head.setZsxh(dataClean(map.get("COL_1"),_L_0_20,false));
						head.setYzpzxh(dataClean(map.get("COL_2"),_L_0_20,false));
						head.setNsrsbh(dataClean(map.get("COL_3"),_L_0_20,false));	
						//sssq
						com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Sssq sssq=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Sssq();
							sssq.setRqQ(dataClean(map.get("COL_4"),_D,false));
							sssq.setRqZ(dataClean(map.get("COL_5"),_D,false));			
						head.setSssq(sssq);		
						head.setNsrSwjgDm(dataClean(map.get("COL_6"),_L_0_11,false));
						head.setLrrq(dataClean(map.get("COL_7"),_D,false));		
						head.setXgrq(dataClean(map.get("COL_8"),_D,false));		
					//Head/////////////////////////////////////////////////////////////////////////////////////////////////////
						
				sbZsxx.setHead(head);
					
					//Body/////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Body body=new com.ttsoft.bjtax.smsb.sjjh.dao.sbzsxx.Body();			
						body.setTzlxDm(dataClean(map.get("COL_9"),_L_0_1,false));
						body.setTzrq(dataClean(map.get("COL_10"),_D,false));
						body.setSe(dataClean(map.get("COL_11"),_F_14_2,false));
						body.setYzfsrq(dataClean(map.get("COL_12"),_D,false));
						body.setJkqx(dataClean(map.get("COL_13"),_D,false));
						body.setKprq(dataClean(map.get("COL_14"),_D,false));
						body.setRkrq(dataClean(map.get("COL_15"),_D,false));
						body.setRkxhrq(dataClean(map.get("COL_16"),_D,false));
						body.setYskm_dm(dataClean(map.get("COL_17"),_L_6_9,false));
						body.setYsfpblZy(dataClean(map.get("COL_18"),_F_1_6,false));
						body.setYsfpblSs(dataClean(map.get("COL_19"),_F_1_6,false));
						body.setYsfpblDs(dataClean(map.get("COL_20"),_F_1_6,false));
						body.setYsfpblXq(dataClean(map.get("COL_21"),_F_1_6,false));
						body.setYsfpblXz(dataClean(map.get("COL_22"),_F_1_6,false));
						body.setYsfpblXc(dataClean(map.get("COL_23"),_F_1_6,false));
						body.setZsjgDm(dataClean(map.get("COL_24"),_L_0_11,false));	
					//Body/////////////////////////////////////////////////////////////////////////////////////////////////////
						
				sbZsxx.setBody(body);	
			
			vSbZsxxYwbwItem.setSbZsxx(sbZsxx);
			tml.addSbZsxxYwbwItem(vSbZsxxYwbwItem);
		}		
		tml.setName("taxMLsbZsxx_hzsds");
		tml.setCnName("申报征收信息业务报文");
		tml.setVersion("1.0");
		
		return tml;
	}
	
	/**
	 * 汇总申报税务登记信息业务报文
	 * @param list processor返回的报文数据
	 * @return 报文对应javaBean
	 */
	private static Object hzsbswdjxx(List list){
		com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxML tml=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.TaxML();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			//vHzsbDwnsrYwbwItem
			com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem vHzsbDwnsrYwbwItem=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsrYwbwItem();
				//hzsbDwnsr
				com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsr hzsbDwnsr=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbDwnsr();
					hzsbDwnsr.setNsrsbh(dataClean(map.get("COL_1"),_L_0_20,false));
					hzsbDwnsr.setSwjgDm(dataClean(map.get("COL_2"),_L_0_11,false));
					hzsbDwnsr.setLrrq(dataClean(map.get("COL_3"),_D,false));
					hzsbDwnsr.setXgrq(dataClean(map.get("COL_4"),_D,false));
					//nsrxx
					com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrxx nsrxx=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrxx();
						nsrxx.setNsrmc(dataClean(map.get("COL_5"),_L_0_80,false));
						nsrxx.setFddbrmc(dataClean(map.get("COL_6"),_L_0_80,false));
						nsrxx.setFrzjlxDm(dataClean(map.get("COL_7"),_L_0_2,false));
						nsrxx.setZjhm(dataClean(map.get("COL_8"),_L_0_20,false));
						nsrxx.setScjydz(dataClean(map.get("COL_9"),_L_0_80,false));
						nsrxx.setBsrmc(dataClean(map.get("COL_10"),_L_0_80,false));
						nsrxx.setDhhm(dataClean(map.get("COL_11"),_L_0_60,false));
						nsrxx.setLsgxDm(dataClean(map.get("COL_12"),_L_0_2,false));
						nsrxx.setHyDm(dataClean(map.get("COL_13"),_L_0_6,false));
						nsrxx.setDjzclxDm(dataClean(map.get("COL_14"),_L_0_3,false));
						nsrxx.setHzdjrq(dataClean(map.get("COL_15"),_D,false));
						nsrxx.setQykjzdDm(dataClean(map.get("COL_16"),_L_0_2,false));
						nsrxx.setSwdjblxDm(dataClean(map.get("COL_17"),_L_1_2,true));
						nsrxx.setSwdjzlxDm(dataClean(map.get("COL_18"),null,true));
						nsrxx.setNsrztDm(dataClean(map.get("COL_19"),_L_0_2,false));
						nsrxx.setZgswryDm(dataClean(map.get("COL_20"),_L_0_11,true));
						nsrxx.setJdxzDm(dataClean(map.get("COL_21"),_L_0_10,true));
						nsrxx.setZzsnsrlxDm(dataClean(map.get("COL_22"),null,true));
						nsrxx.setGsylbDm(dataClean(map.get("COL_23"),null,true));
						nsrxx.setNsrxydjDm(dataClean(map.get("COL_24"),null,true));
						nsrxx.setQygmDm(dataClean(map.get("COL_25"),null,true));
						nsrxx.setCylxDm(dataClean(map.get("COL_26"),null,true));
						nsrxx.setXzqhYsfpblDm(dataClean(map.get("COL_27"),_L_0_4,true));
						nsrxx.setHgdm(dataClean(map.get("COL_28"),_L_0_30,true));
						nsrxx.setZjgBz(dataClean(map.get("COL_29"),_L_0_1,true));
						nsrxx.setGszgswjgJDm(dataClean(map.get("COL_30"),_L_0_11,true));
						nsrxx.setLsswdjlxDm(dataClean(map.get("COL_31"),null,true));
						nsrxx.setDwxzDm(dataClean(map.get("COL_32"),_L_0_2,false));
						nsrxx.setGghbz(dataClean(map.get("COL_33"),_L_0_1,true));
					hzsbDwnsr.setNsrxx(nsrxx);
					//nsrkzxx
					com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrkzxx nsrkzxx=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Nsrkzxx();
						nsrkzxx.setNsrmcYw(dataClean(map.get("COL_34"),_L_0_80,false));
						nsrkzxx.setGshbz(dataClean(map.get("COL_35"),_L_0_1,true));
						nsrkzxx.setFrbz(dataClean(map.get("COL_36"),_L_0_1,true));
						nsrkzxx.setZcdz(dataClean(map.get("COL_37"),_L_0_80,false));
						nsrkzxx.setZcdYb(dataClean(map.get("COL_38"),_L_0_6,false));
						nsrkzxx.setScjydYb(dataClean(map.get("COL_39"),_L_0_6,false));
						nsrkzxx.setDydz(dataClean(map.get("COL_40"),_L_0_80,true));
						nsrkzxx.setGskyrq(dataClean(map.get("COL_41"),_D,false));
						nsrkzxx.setBsrZjlDm(dataClean(map.get("COL_42"),_L_0_2,false));
						nsrkzxx.setBsrZjhm(dataClean(map.get("COL_43"),_L_0_20,false));
						nsrkzxx.setZzjgdm(dataClean(map.get("COL_44"),_L_0_9,true));
						nsrkzxx.setGsdjjgMc(dataClean(map.get("COL_45"),_L_0_80,false));
						nsrkzxx.setZzlbDm(dataClean(map.get("COL_46"),_L_0_2,false));
						nsrkzxx.setGsdjzzh(dataClean(map.get("COL_47"),_L_0_50,false));
						nsrkzxx.setYxqQ(dataClean(map.get("COL_48"),_D,false));
						nsrkzxx.setYxqZ(dataClean(map.get("COL_49"),_D,false));
						nsrkzxx.setZy(dataClean(map.get("COL_50"),_L_0_4000,false));
						nsrkzxx.setJy(dataClean(map.get("COL_51"),_L_0_4000,false));
						nsrkzxx.setJyfs(dataClean(map.get("COL_52"),_L_0_100,false));
						nsrkzxx.setScjyqxQ(dataClean(map.get("COL_53"),_D,false));
						nsrkzxx.setScjyqxZ(dataClean(map.get("COL_54"),_D,false));
						nsrkzxx.setGsfzrq(dataClean(map.get("COL_55"),_D,false));
						nsrkzxx.setZczb(dataClean(map.get("COL_56"),_F_14_2,false));
						nsrkzxx.setZcbzDm(dataClean(map.get("COL_57"),_L_0_3,false));
						nsrkzxx.setTzze(dataClean(map.get("COL_58"),_F_14_2,false));
						nsrkzxx.setTzbzDm(dataClean(map.get("COL_59"),_L_0_3,false));
						nsrkzxx.setCyrs(dataClean(map.get("COL_60"),_I,false));
						nsrkzxx.setWjrs(dataClean(map.get("COL_61"),_I,false));
						nsrkzxx.setYzzz(dataClean(map.get("COL_62"),_L_0_80,true));
						nsrkzxx.setZgdw(dataClean(map.get("COL_63"),_L_0_80,true));
						nsrkzxx.setJyfwdw(dataClean(map.get("COL_64"),_L_0_80,true));
						nsrkzxx.setZjgyb(dataClean(map.get("COL_65"),_L_0_30,true));
						nsrkzxx.setPzjg(dataClean(map.get("COL_66"),_L_0_80,false));
						nsrkzxx.setPzwh(dataClean(map.get("COL_67"),_L_0_50,false));
						nsrkzxx.setPzrq(dataClean(map.get("COL_68"),_D,false));
						nsrkzxx.setZhzcqk(dataClean(map.get("COL_69"),_L_0_1000,true));
						nsrkzxx.setCjqQ(dataClean(map.get("COL_70"),_D,true));
						nsrkzxx.setCjqZ(dataClean(map.get("COL_71"),_D,true));
						nsrkzxx.setHwcfc(dataClean(map.get("COL_72"),_L_0_80,true));
						nsrkzxx.setHwcfdmj(dataClean(map.get("COL_73"),_L_0_80,true));
						nsrkzxx.setCwfzrmc(dataClean(map.get("COL_74"),_L_0_80,false));
						nsrkzxx.setCwbbzl(dataClean(map.get("COL_75"),_L_0_120,false));
						nsrkzxx.setGdzczjfs(dataClean(map.get("COL_76"),_L_0_80,true));
						nsrkzxx.setYhptxff(dataClean(map.get("COL_77"),_L_0_80,true));
						nsrkzxx.setKjnd(dataClean(map.get("COL_78"),_L_0_30,true));
						nsrkzxx.setJzbwbDm(dataClean(map.get("COL_79"),_L_0_3,true));
						nsrkzxx.setJzlb(dataClean(map.get("COL_80"),_L_0_1,true));
						nsrkzxx.setJzfs(dataClean(map.get("COL_81"),_L_0_1,true));
						nsrkzxx.setJzrq(dataClean(map.get("COL_82"),_I,true));
						nsrkzxx.setFzrq(dataClean(map.get("COL_83"),_D,true));
						nsrkzxx.setZcdDhhm(dataClean(map.get("COL_84"),_L_0_60,false));
						nsrkzxx.setHsfsDm(dataClean(map.get("COL_85"),_L_0_2,false));
						nsrkzxx.setSykjzdDm(dataClean(map.get("COL_86"),_L_0_2,false));
						nsrkzxx.setWzwz(dataClean(map.get("COL_87"),_L_0_80,true));
						nsrkzxx.setFrDhhm(dataClean(map.get("COL_88"),_L_0_60,false));
						nsrkzxx.setFrYddhhm(dataClean(map.get("COL_89"),_L_0_60,false));
						nsrkzxx.setFrEmail(dataClean(map.get("COL_90"),_L_0_80,false));
						nsrkzxx.setCwfzrZjlxDm(dataClean(map.get("COL_91"),_L_0_2,false));
						nsrkzxx.setCwfzrZjhm(dataClean(map.get("COL_92"),_L_0_20,false));
						nsrkzxx.setCwfzrDhhm(dataClean(map.get("COL_93"),_L_0_60,false));
						nsrkzxx.setCwfzrYddhhm(dataClean(map.get("COL_94"),_L_0_60,false));
						nsrkzxx.setCwfzrEmail(dataClean(map.get("COL_95"),_L_0_80,false));
						nsrkzxx.setBsrDhhm(dataClean(map.get("COL_96"),_L_0_60,false));
						nsrkzxx.setBsrYddhhm(dataClean(map.get("COL_97"),_L_0_60,false));
						nsrkzxx.setBsrEmail(dataClean(map.get("COL_98"),_L_0_80,false));
						nsrkzxx.setSwdlrmc(dataClean(map.get("COL_99"),_L_0_20,false));
						nsrkzxx.setSwdlrNsrsbh(dataClean(map.get("COL_100"),_L_0_20,false));
						nsrkzxx.setSwdlrDhhm(dataClean(map.get("COL_101"),_L_0_60,false));
						nsrkzxx.setSwdlrEmail(dataClean(map.get("COL_102"),_L_0_80,false));
						nsrkzxx.setDszgswjgJDm(dataClean(map.get("COL_103"),_L_0_20,true));
						nsrkzxx.setDszgswjgDm(dataClean(map.get("COL_104"),_L_0_11,true));
						nsrkzxx.setHhrs(dataClean(map.get("COL_105"),_I,true));
						nsrkzxx.setGdrs(dataClean(map.get("COL_106"),_I,true));
						nsrkzxx.setTzblZrr(dataClean(map.get("COL_107"),_F_4_6,false));
						nsrkzxx.setTzblWz(dataClean(map.get("COL_108"),_F_4_6,false));
						nsrkzxx.setTzblGy(dataClean(map.get("COL_109"),_F_4_6,false));
						nsrkzxx.setFbzl(dataClean(map.get("COL_110"),null,true));
						nsrkzxx.setFshy1Dm(dataClean(map.get("COL_111"),_L_0_6,true));
						nsrkzxx.setFshy2Dm(dataClean(map.get("COL_112"),_L_0_6,true));
						nsrkzxx.setFshy3Dm(dataClean(map.get("COL_113"),_L_0_6,true));
						nsrkzxx.setJyfw(dataClean(map.get("COL_114"),_L_0_4000,false));
						nsrkzxx.setKyslrq(dataClean(map.get("COL_115"),_D,false));
						nsrkzxx.setNsywfsrq(dataClean(map.get("COL_116"),_D,true));
						nsrkzxx.setLghbz(dataClean(map.get("COL_117"),_L_0_1,true));
						nsrkzxx.setXysbz(dataClean(map.get("COL_118"),_L_0_1,true));
						nsrkzxx.setZszzsydwbz(dataClean(map.get("COL_119"),_L_0_1,true));
						nsrkzxx.setFshy1Hymxdm(dataClean(map.get("COL_120"),_L_0_4,true));
						nsrkzxx.setFshy2Hymxdm(dataClean(map.get("COL_121"),_L_0_4,true));
						nsrkzxx.setFshy3Hymxdm(dataClean(map.get("COL_122"),_L_0_4,true));
					hzsbDwnsr.setNsrkzxx(nsrkzxx);
					
					//zjgxx
					com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zjgxx zjgxx=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zjgxx();
						zjgxx.setZjgNsrsbh(dataClean(map.get("COL_123"),_L_0_20,false));
						zjgxx.setZjgmc(dataClean(map.get("COL_124"),_L_0_80,false));
						zjgxx.setZjgZcdz(dataClean(map.get("COL_125"),_L_0_80,false));
						zjgxx.setJyfw(dataClean(map.get("COL_126"),_L_0_1000,false));
						zjgxx.setZjgFddbrmc(dataClean(map.get("COL_127"),_L_0_80,false));
						zjgxx.setZgswjgDm(dataClean(map.get("COL_128"),_L_0_11,false));
						zjgxx.setZgswjgMc(dataClean(map.get("COL_129"),_L_0_80,false));
						zjgxx.setYb(dataClean(map.get("COL_130"),_L_0_6,false));
						zjgxx.setDhhm(dataClean(map.get("COL_131"),_L_0_60,false));
					hzsbDwnsr.setZjgxx(zjgxx);
					
					List zczbtzzeMapList=(List)map.get("ZCZBTZZE_MAP_LIST");
					int zczbtzzeMapListSize=(zczbtzzeMapList==null)?0:zczbtzzeMapList.size();
					for(int j=0;j<zczbtzzeMapListSize;j++){
						Map zczbtzzeMap=(Map)zczbtzzeMapList.get(j);
						//zczbtzze
						com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze vZczbtzze=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Zczbtzze();
							vZczbtzze.setXh(dataClean(zczbtzzeMap.get("COL_1"),_I,false));
							vZczbtzze.setZcbzDm(dataClean(zczbtzzeMap.get("COL_2"),_L_0_3,false));
							vZczbtzze.setZczbJe(dataClean(zczbtzzeMap.get("COL_3"),_F_14_2,false));
						hzsbDwnsr.addZczbtzze(vZczbtzze);
					}					
					
					List tzfxxMapList=(List)map.get("TZFXX_MAP_LIST");
					int tzfxxMapListSize=(tzfxxMapList==null)?0:tzfxxMapList.size();
					for(int j=0;j<tzfxxMapListSize;j++){
						Map tzfxxMap=(Map)tzfxxMapList.get(j);
						//tzfxx
						com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx vTzfxx=new com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Tzfxx();
							vTzfxx.setXh(dataClean(tzfxxMap.get("COL_1"),_I,false));
							vTzfxx.setTzfNsrsbh(dataClean(tzfxxMap.get("COL_2"),_L_0_20,false));
							vTzfxx.setTzfmc(dataClean(tzfxxMap.get("COL_3"),_L_0_80,false));
							vTzfxx.setGjDm(dataClean(tzfxxMap.get("COL_4"),_L_0_3,false));
							vTzfxx.setTzje(dataClean(tzfxxMap.get("COL_5"),_F_14_2,false));
							vTzfxx.setBzDm(dataClean(tzfxxMap.get("COL_6"),_L_0_3,false));
							vTzfxx.setTzbl(dataClean(tzfxxMap.get("COL_7"),_F_4_6,false));
							vTzfxx.setFpbl(dataClean(tzfxxMap.get("COL_8"),_F_4_6,true));
							vTzfxx.setXyczrq(dataClean(tzfxxMap.get("COL_9"),_D,true));
							vTzfxx.setHlbj(dataClean(tzfxxMap.get("COL_10"),_F_4_6,true));
							vTzfxx.setTzfxz(dataClean(tzfxxMap.get("COL_11"),_L_0_3,false));
							vTzfxx.setTzfZjzlDm(dataClean(tzfxxMap.get("COL_12"),_L_0_2,false));
							vTzfxx.setTzfZjhm(dataClean(tzfxxMap.get("COL_13"),_L_0_20,false));
							vTzfxx.setTzfDz(dataClean(tzfxxMap.get("COL_14"),_L_0_80,true));							
						hzsbDwnsr.addTzfxx(vTzfxx);					
					}
					
					List fzjgxxMapList=(List)map.get("FZJGXX_MAP_LIST");
					int fzjgxxMapListSize=(fzjgxxMapList==null)?0:fzjgxxMapList.size();
					for(int j=0;j<fzjgxxMapListSize;j++){
						Map fzjgxxMap=(Map)fzjgxxMapList.get(j);
						//fzjgxx
						com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx vFzjgxx=new  com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.Fzjgxx();
							vFzjgxx.setXh(dataClean(fzjgxxMap.get("COL_1"),_I,false));
							vFzjgxx.setFzjgNsrsbh(dataClean(fzjgxxMap.get("COL_2"),_L_0_20,false));
							vFzjgxx.setFzjgmc(dataClean(fzjgxxMap.get("COL_3"),_L_0_80,false));
							vFzjgxx.setDhhm(dataClean(fzjgxxMap.get("COL_4"),_L_0_60,true));
							vFzjgxx.setDz(dataClean(fzjgxxMap.get("COL_5"),_L_0_80,false));
						hzsbDwnsr.addFzjgxx(vFzjgxx);
					}
			
			vHzsbDwnsrYwbwItem.setHzsbDwnsr(hzsbDwnsr);
			tml.addHzsbDwnsrYwbwItem(vHzsbDwnsrYwbwItem);
		}		
		tml.setName("taxMLdwnsr_hzsds");
		tml.setCnName("汇总申报税务登记信息业务报文");
		tml.setVersion("1.0");		
		return tml;
	}
	
	/**
	 * 2008版企业所得税月(季)度申报表A类业务报文
	 * @param list processor返回的报文数据
	 * @return 报文对应javaBean
	 */
	private static Object jdalywbw(List list){
		com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxML tml=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.TaxML();
		for(int i=0;i<list.size();i++){
			Map map=(Map)list.get(i);
			//vSbbQysds2008JdAYwbwItem
			com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem vSbbQysds2008JdAYwbwItem=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdAYwbwItem();
				//sbbQysds2008JdA
				com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdA sbbQysds2008JdA=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SbbQysds2008JdA();
					//Head////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Head head=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Head();
						//publicHead
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.PublicHead publicHead=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.PublicHead();
							publicHead.setNsrsbh(dataClean(map.get("COL_1"),_L_0_20,false));
							publicHead.setNsrmc(dataClean(map.get("COL_2"),_L_0_80,false));
							publicHead.setTbrq(dataClean(map.get("COL_3"),_D,false));
							//sssq
							com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Sssq sssq=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Sssq();
								sssq.setRqQ(dataClean(map.get("COL_4"),_D,false));
								sssq.setRqZ(dataClean(map.get("COL_5"),_D,false));
							publicHead.setSssq(sssq);						
						head.setPublicHead(publicHead);
						//privateHead
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.PrivateHead privateHead=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.PrivateHead();
							privateHead.setFddbr(dataClean(map.get("COL_6"),_L_0_80,true));
							privateHead.setFddbrQzrq(dataClean(map.get("COL_7"),_D,true));
							privateHead.setKjzg(dataClean(map.get("COL_8"),_L_0_80,true));
							privateHead.setDlsbZjjg(dataClean(map.get("COL_9"),_L_0_80,true));
							privateHead.setDlsbZjjgJbr(dataClean(map.get("COL_10"),_L_0_80,true));
							privateHead.setDlsbZjjgJbrZyzjhm(dataClean(map.get("COL_11"),_L_0_20,true));
							privateHead.setDlSbrq(dataClean(map.get("COL_12"),_D,true));
							privateHead.setSlSwjgDm(dataClean(map.get("COL_13"),_L_0_11,true));
							privateHead.setSlSwjgMc(dataClean(map.get("COL_14"),_L_0_80,true));
							privateHead.setSlSwjgSlrDm(dataClean(map.get("COL_15"),_L_0_11,true));
							privateHead.setSlSwjgSlrMc(dataClean(map.get("COL_16"),_L_0_80,true));
							privateHead.setSlrq(dataClean(map.get("COL_17"),_D,true));
						head.setPrivateHead(privateHead);
					//Head////////////////////////////////////////////////////////////////////////////////////////////////////
					
				sbbQysds2008JdA.setHead(head);
				
					//jbxxHead////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.jbal.JbxxHead jbxxHead=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.JbxxHead();
						jbxxHead.setPzxh(dataClean(map.get("COL_18"),_L_0_20,false));
						jbxxHead.setYxbz(dataClean(map.get("COL_19"),_L_0_1,false));
						jbxxHead.setNsrSwjgDm(dataClean(map.get("COL_20"),_L_0_11,false));
						jbxxHead.setLrrq(dataClean(map.get("COL_21"),_D,false));
						jbxxHead.setXgrq(dataClean(map.get("COL_22"),_D,false));
						jbxxHead.setBcsblx(dataClean(map.get("COL_23"),_L_0_1,true));
					//jbxxHead////////////////////////////////////////////////////////////////////////////////////////////////		
					
				sbbQysds2008JdA.setJbxxHead(jbxxHead);
				
					//body////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Body body=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Body();						
						//nsrJsYj
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj nsrJsYj=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.NsrJsYj();
							//bqjeNsrJsYj
							com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeNsrJsYj bqjeNsrJsYj=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeNsrJsYj();
								bqjeNsrJsYj.setYysr(dataClean(map.get("COL_24"),_F_14_2,false));
								bqjeNsrJsYj.setYycb(dataClean(map.get("COL_25"),_F_14_2,false));
								bqjeNsrJsYj.setLrze(dataClean(map.get("COL_26"),_F_14_2,false));
								bqjeNsrJsYj.setYnsdse(dataClean(map.get("COL_27"),_F_14_2,false));
								bqjeNsrJsYj.setJmsdse(dataClean(map.get("COL_28"),_F_14_2,false));
							nsrJsYj.setBqjeNsrJsYj(bqjeNsrJsYj);
							//ljjeNsrJsY;
							com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeNsrJsYj ljjeNsrJsYj=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeNsrJsYj();
								ljjeNsrJsYj.setYysr(dataClean(map.get("COL_29"),_F_14_2,false));
								ljjeNsrJsYj.setYycb(dataClean(map.get("COL_30"),_F_14_2,false));
								ljjeNsrJsYj.setLrze(dataClean(map.get("COL_31"),_F_14_2,false));
								ljjeNsrJsYj.setYnsdse(dataClean(map.get("COL_32"),_F_14_2,false));
								ljjeNsrJsYj.setJmsdse(dataClean(map.get("COL_33"),_F_14_2,false));
								ljjeNsrJsYj.setSjyjSdse(dataClean(map.get("COL_34"),_F_14_2,false));
								ljjeNsrJsYj.setYbtSdse(dataClean(map.get("COL_35"),_F_14_2,false));
							nsrJsYj.setLjjeNsrJsYj(ljjeNsrJsYj);
						body.setNsrJsYj(nsrJsYj);
						//sndPjeYj
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj sndPjeYj=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SndPjeYj();
							sndPjeYj.setBqjeBjYnssde(dataClean(map.get("COL_36"),_F_14_2,false));
							sndPjeYj.setBqjeBjYnsdse(dataClean(map.get("COL_37"),_F_14_2,false));
							sndPjeYj.setLjjeSndYnssde(dataClean(map.get("COL_38"),_F_14_2,false));
							sndPjeYj.setLjjeBjYnssde(dataClean(map.get("COL_39"),_F_14_2,false));
							sndPjeYj.setLjjeBjYnsdse(dataClean(map.get("COL_40"),_F_14_2,false));
						body.setSndPjeYj(sndPjeYj);
						//swjgQdfsYj
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj swjgQdfsYj=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.SwjgQdfsYj();
							swjgQdfsYj.setBqjeBjqdYjse(dataClean(map.get("COL_41"),_F_14_2,false));
							swjgQdfsYj.setLjjeBjqdYjse(dataClean(map.get("COL_42"),_F_14_2,false));
						body.setSwjgQdfsYj(swjgQdfsYj);
						//zfzjg
						com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg zfzjg=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.Zfzjg();
							//bqjeZfzjgxx
							com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeZfzjgxx bqjeZfzjgxx=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.BqjeZfzjgxx();
								bqjeZfzjgxx.setZjgYftSdse(dataClean(map.get("COL_43"),_F_14_2,false));
								bqjeZfzjgxx.setZjgZyjzfpSdse(dataClean(map.get("COL_44"),_F_14_2,false));
								bqjeZfzjgxx.setZjgFzjgftSdse(dataClean(map.get("COL_45"),_F_14_2,false));
								bqjeZfzjgxx.setFzjgFpbl(dataClean(map.get("COL_46"),_F_4_6,false));
								bqjeZfzjgxx.setFzjgFpSdse(dataClean(map.get("COL_47"),_F_14_2,false));
							zfzjg.setBqjeZfzjgxx(bqjeZfzjgxx);								
							//ljjeZfzjgxx
							com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeZfzjgxx ljjeZfzjgxx=new com.ttsoft.bjtax.smsb.sjjh.dao.jbal.LjjeZfzjgxx();
								ljjeZfzjgxx.setZjgYftSdse(dataClean(map.get("COL_48"),_F_14_2,false));
								ljjeZfzjgxx.setZjgZyjzfpSdse(dataClean(map.get("COL_49"),_F_14_2,false));
								ljjeZfzjgxx.setZjgFzjgftSdse(dataClean(map.get("COL_50"),_F_14_2,false));
								ljjeZfzjgxx.setFzjgFpbl(dataClean(map.get("COL_51"),_F_4_6,false));
								ljjeZfzjgxx.setFzjgFpSdse(dataClean(map.get("COL_52"),_F_14_2,false));
							zfzjg.setLjjeZfzjgxx(ljjeZfzjgxx);
						body.setZfzjg(zfzjg);
					//body////////////////////////////////////////////////////////////////////////////////////////////////////				
				
				sbbQysds2008JdA.setBody(body);
			vSbbQysds2008JdAYwbwItem.setSbbQysds2008JdA(sbbQysds2008JdA);
			tml.addSbbQysds2008JdAYwbwItem(vSbbQysds2008JdAYwbwItem);
		}		
		tml.setName("taxMLsbbQysds2008JdA_hzsds");
		tml.setCnName("2008版企业所得税月(季)度申报表A类业务报文");
		tml.setVersion("1.0");		
		return tml;
	}
	
	/**
	 * 2008版企业所得税汇总纳税分支机构分配表业务报文
	 * @param list processor返回的报文数据
	 * @return 报文对应javaBean
	 */
			
	private static Object fzjgfpbywbw(List list){
		com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.TaxML tml=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.TaxML();
		for(int i=0;i<list.size();i++){			
			Map map=(Map)list.get(i);				
			//vSbbQysds2008HznsFzjgFpbYwbwItem
			com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem vSbbQysds2008HznsFzjgFpbYwbwItem=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpbYwbwItem();
				//sbbQysds2008HznsFzjgFpb
				com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpb sbbQysds2008HznsFzjgFpb=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.SbbQysds2008HznsFzjgFpb();	
					
					//Head////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Head head=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Head();			
						//publicHead
						com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PublicHead publicHead=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PublicHead();
							publicHead.setNsrsbh(dataClean(map.get("COL_1"),_L_0_20,false));
							publicHead.setNsrmc(dataClean(map.get("COL_2"),_L_0_80,false));
							publicHead.setTbrq(dataClean(map.get("COL_3"),_D,false));
								com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Sssq sssq=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Sssq();
								sssq.setRqQ(dataClean(map.get("COL_4"),_D,false));
								sssq.setRqZ(dataClean(map.get("COL_5"),_D,false));
							publicHead.setSssq(sssq);	
						head.setPublicHead(publicHead);		
						//fpblYxq	
						com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.FpblYxq fpblYxq=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.FpblYxq();	
							fpblYxq.setRqQ(dataClean(map.get("COL_6"),_D,false));
							fpblYxq.setRqZ(dataClean(map.get("COL_7"),_D,false));
						head.setFpblYxq(fpblYxq);	
						//privateHead optional
						com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PrivateHead privateHead=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.PrivateHead();		
							privateHead.setKjzg(dataClean(map.get("COL_8"),_L_0_80,true));
							privateHead.setSlSwjgDm(dataClean(map.get("COL_9"),_L_0_11,true));
							privateHead.setSlSwjgMc(dataClean(map.get("COL_10"),_L_0_80,true));
							privateHead.setSlSwjgSlrDm(dataClean(map.get("COL_11"),_L_0_11,true));
							privateHead.setSlSwjgSlrMc(dataClean(map.get("COL_12"),_L_0_80,true));
							privateHead.setSlrq(dataClean(map.get("COL_13"),_D,true));
						head.setPrivateHead(privateHead);
					
					//Head////////////////////////////////////////////////////////////////////////////////////////////////////
						
				sbbQysds2008HznsFzjgFpb.setHead(head);
				
					//jbxxHead////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.JbxxHead jbxxHead=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.JbxxHead();			
						jbxxHead.setPzxh(dataClean(map.get("COL_14"),_L_0_20,false));
						jbxxHead.setYxbz(dataClean(map.get("COL_15"),_L_0_1,false));
						jbxxHead.setNsrSwjgDm(dataClean(map.get("COL_16"),_L_0_11,false));
						jbxxHead.setLrrq(dataClean(map.get("COL_17"),_D,false));
						jbxxHead.setXgrq(dataClean(map.get("COL_18"),_D,false));
						jbxxHead.setBcsblx(dataClean(map.get("COL_19"),_L_0_1,true));
					
					//jbxxHead////////////////////////////////////////////////////////////////////////////////////////////////
						
				sbbQysds2008HznsFzjgFpb.setJbxxHead(jbxxHead);
				
					//body////////////////////////////////////////////////////////////////////////////////////////////////////
					com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Body body=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Body();						
						//zjgxx
						com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Zjgxx zjgxx= new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Zjgxx();
							zjgxx.setZjgNsrsbh(dataClean(map.get("COL_20"),_L_0_20,false));
							zjgxx.setZjgMc(dataClean(map.get("COL_21"),_L_0_80,false));
							zjgxx.setSxysSrze(dataClean(map.get("COL_22"),_F_14_2,false));
							zjgxx.setSxysGzze(dataClean(map.get("COL_23"),_F_14_2,false));
							zjgxx.setSxysZcze(dataClean(map.get("COL_24"),_F_14_2,false));
							zjgxx.setSxysHj(dataClean(map.get("COL_25"),_F_14_2,false));
							zjgxx.setFzjgFtSdse(dataClean(map.get("COL_26"),_F_14_2,false));
					body.setZjgxx(zjgxx);						
						//fzjgxx
						List fzjgList=(List)map.get("FZJG_MAP_LIST");
						int fzjgListSize= (fzjgList==null) ? 0: fzjgList.size();
						for(int j=0;j<fzjgListSize;j++){
							Map fzjgMap=(Map)fzjgList.get(j);							
							com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx fzjgxx=new com.ttsoft.bjtax.smsb.sjjh.dao.fzjgfpb.Fzjgxx();
							fzjgxx.setFzjgNsrsbh(dataClean(fzjgMap.get("COL_1"),_L_0_20,false));
							fzjgxx.setFzjgMc(dataClean(fzjgMap.get("COL_2"),_L_0_80,false));
							fzjgxx.setSxysSrze(dataClean(fzjgMap.get("COL_3"),_F_14_2,false));
							fzjgxx.setSxysGzze(dataClean(fzjgMap.get("COL_4"),_F_14_2,false));
							fzjgxx.setSxysZcze(dataClean(fzjgMap.get("COL_5"),_F_14_2,false));
							fzjgxx.setSxysHj(dataClean(fzjgMap.get("COL_6"),_F_14_2,false));
							fzjgxx.setFpbl(dataClean(fzjgMap.get("COL_7"),_F_4_6,false));
							fzjgxx.setFpse(dataClean(fzjgMap.get("COL_8"),_F_14_2,false));
							body.addFzjgxx(fzjgxx);	
						}
					
					//body////////////////////////////////////////////////////////////////////////////////////////////////////
					
				sbbQysds2008HznsFzjgFpb.setBody(body);
				
			vSbbQysds2008HznsFzjgFpbYwbwItem.setSbbQysds2008HznsFzjgFpb(sbbQysds2008HznsFzjgFpb);	
			tml.addSbbQysds2008HznsFzjgFpbYwbwItem(vSbbQysds2008HznsFzjgFpbYwbwItem);
			
		}
		
		tml.setName("taxMLsbbQysds2008HznsFzjgFpb_hzsds");
		tml.setCnName("2008版企业所得税汇总纳税分支机构分配表业务报文");
		tml.setVersion("1.0");
		
		return tml;
	}
	
	/**
	 * 根据XSD里定义的格式约束进行格式转换
	 * @param valueObj 原始数据
	 * @param type 数据格式类型
	 * @param nullable 数据项是否可以为空
	 * @return 符合xml数据项格式的数据
	 */
	private static String dataClean(Object valueObj,String type,boolean  nullable){
		String value=(String)valueObj;
		value=(value==null)?"":value;
		boolean valid=false;
		if(type ==null ){
			valid=true;
			return value;
		}
		if(type.indexOf("L")>=0){
			String lengthRestrict=type.split(":")[1];
			int min=Integer.parseInt(lengthRestrict.split(",")[0]);
			int max=Integer.parseInt(lengthRestrict.split(",")[1]);
			if(value.length()>=min && value.length()<=max){
				valid=true;
			}else{
				StringBuffer sb=new StringBuffer();
				
				for(int i=0;i<min;i++){
					sb.append(" ");
				}
				value=sb.toString();
				valid=false;
			}
		}
		if(type.indexOf("F")>=0){
			try{
				DecimalFormat format = new DecimalFormat("0.000000");
				value = format.format(value);
				value=formateFloat(value);
				String beforComma=value.split("\\.")[0];
				String afterComma=value.split("\\.")[1];				
				String digitRestrict=type.split(":")[1];
				int befor=Integer.parseInt(digitRestrict.split(",")[0]);
				int after=Integer.parseInt(digitRestrict.split(",")[1]);				
				int beforCommaLength=(beforComma.indexOf("-")==0)?beforComma.length()-1:beforComma.length();
				int afterCommaLength=afterComma.length();				
				if(beforCommaLength<=befor && afterCommaLength<=after){
					valid=true;
				}else{
					value="0";
					valid=false;
				}
			}catch (Exception e){
				value="0";
				valid=false;
			}			
		}
		if(type.indexOf("D")>=0){
			try{
				int date=Integer.parseInt(value);
				if(date>=10000000 && date <=99999999){
					valid=true;
				}else{
					value="00000000";
					valid=false;
				}
			}catch (NumberFormatException e){
				value="00000000";
				valid=false;
			}
		}
		if(type.indexOf("I")>=0){
			try{
				Integer.parseInt(value);	
				valid=true;
			}catch (NumberFormatException e){
				value="0";
				valid=false;
			}
		}
		if(nullable&&!valid) value=null;
		return value;
	}
	
	/**
	 * 对浮点类型绝对值小于1的数据进行补零处理
	 * @param value 原始数据 
	 * @return 经处理的数据
	 */
	
	private static String formateFloat(String value){
	    	if(value !=null && value.length()>0){
	    		if(value.indexOf(".")==0){
	    			value="0"+value;
	    		}
	    		if(value.indexOf("-.")==0){
	    			value="-0"+value.substring(1);
	    		}
	    	}
	    	return value;
	    }

	
	
    


}

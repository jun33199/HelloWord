package com.lscdz.qysds.common.service.qysds.util;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import yangjian.frame.dao.FrameCommonAccess;
import yangjian.frame.util.FrameException;
import com.lscdz.hlwdsj.hxzgframe.Yh;
import com.lscdz.qysds.application.nsrjbxx2014.vo.response.QyjbxxVo;
import com.lscdz.qysds.common.QysdsNbConstant;
import com.lscdz.qysds.common.codetable.CodeTableKey;
import com.lscdz.qysds.common.service.ServiceManager;
import com.lscdz.qysds.common.service.QysdsInfo.bo.QysdsSet;
import com.lscdz.qysds.common.service.QysdsInfo.bo.Zsfs;
import com.lscdz.qysds.common.service.djjbsj.bo.Djjbsj;
import com.lscdz.qysds.common.service.qysds.Constants;
import com.lscdz.qysds.common.service.qysdsCheck.bo.CheckBean;
import com.lscdz.qysds.common.service.qysdsCheck.bo.Qysdszgfwjd;
import com.lscdz.qysds.common.service.qysdsCheck.bo.ZfjgInf;
import com.lscdz.qysds.common.util.TinyTools;
import com.lscdz.qysds.common.vo.sb_jl_qysds_dwtz;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014;
import com.lscdz.qysds.common.vo.sb_jl_qysds_nsrjbxxb_2014_his;
import com.lscdz.qysds.common.vo.sb_jl_qysds_qyzygd;
import com.lscdz.util.codetable.CodeTableManager;

public class Qyjbxx {
	public static QyjbxxVo getQyjbxxVo(Djjbsj djjbsjVo,String startTime,String endTime,CheckBean checkbean) throws FrameException{
		QyjbxxVo qyjbxxVo=new QyjbxxVo();
		Timestamp skqsrq=DateUtils.getDateTime(startTime);
		Timestamp skjzrq=DateUtils.getDateTime(endTime);
		//主管税务机关
		String zgswjgdm=djjbsjVo.getSwjgzzjgdm();
		String zgswjgmc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_SWJGZZJG, zgswjgdm);
		//主管分局
		String zgfjdm=getZgfjdm(zgswjgdm);
		String zgfjmc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_SWJGZZJG, zgfjdm);
		//纳税人状态
		String nsrzt=djjbsjVo.getNsrzt();
		String nsrztmc=CodeTableManager.getNameByCode(CodeTableKey.DJ_DM_NSRZT,nsrzt);
		//获得鉴定范围信息
		String syjdlxdm=checkbean.getJdlx();
		String syjdlxmc=CodeTableManager.getNameByCode(CodeTableKey.DJ_DM_QYSDSZGFWJDLX,syjdlxdm);
		//所属行业
		String sshydm=djjbsjVo.getGjbzhydm();
		String sshymc=CodeTableManager.getNameByCode(CodeTableKey.GY_DM_GJBZHY,sshydm);
		ZfjgInf zfjgInfVo=new ZfjgInf();
		zfjgInfVo=checkbean.getZfjgInf();
		Qysdszgfwjd qysdszgfwjd=new Qysdszgfwjd();
		qysdszgfwjd=checkbean.getQysdszgfwjd();
		if(zfjgInfVo!=null){
			//上年度是否为小型微利企业
			qyjbxxVo.setSfwxxwlqy(zfjgInfVo.isXWQY());
			//分支机构是否分摊企业所得税
			qyjbxxVo.setFzjgsfftqysds(zfjgInfVo.isIsftsk());
		}
		if(qysdszgfwjd!=null){
			qyjbxxVo.setSyjdqsrq(qysdszgfwjd.getJdqsrq());
			qyjbxxVo.setSyjdjzrq(qysdszgfwjd.getJdjzrq());
		}
		qyjbxxVo.setSyjdlxdm(syjdlxdm);
		qyjbxxVo.setSyjdlxmc(syjdlxmc);
		qyjbxxVo.setSfjrqsq(checkbean.isInQsq());
		qyjbxxVo.setSfjsqs(checkbean.isFinishQs());
		qyjbxxVo.setZgswjgdm(zgswjgdm);
		qyjbxxVo.setZgswjgmc(zgswjgmc);
		qyjbxxVo.setZgfjdm(zgfjdm);
		qyjbxxVo.setZgfjmc(zgfjmc);
		qyjbxxVo.setNsrmc(djjbsjVo.getNsrmc());
		qyjbxxVo.setNsrzt(djjbsjVo.getNsrzt());
		qyjbxxVo.setNsrztmc(nsrztmc);
		qyjbxxVo.setSshydm(sshydm);
		qyjbxxVo.setSshymc(sshymc);
		qyjbxxVo.setNsrsbh(djjbsjVo.getSwdjzh());
		qyjbxxVo.setJsjdm(djjbsjVo.getJsjdm());
		qyjbxxVo.setSkjsrq(skjzrq);
		qyjbxxVo.setSkksrq(skqsrq);
		return qyjbxxVo;
	}
	/**
	 * 获得主管分局
	 * @param zgswjgdm
	 * @return
	 */
	public static String getZgfjdm(String zgswjgdm){
		//主管分局
		String zgfjdm="";
		if(zgswjgdm!=null&&!("".equals(zgswjgdm))){
			zgfjdm=zgswjgdm.substring(0, 2);
			zgfjdm=zgfjdm+"00";
		}
		return zgfjdm;
	}
	/**
	 * 获得增收方式代码
	 * @param conn
	 * @param jsjdm
	 * @param startTime
	 * @param endTime
	 * @return
	 * @throws FrameException
	 */
	public static Zsfs getZsfs(Connection conn,String jsjdm,String startTime,String endTime) throws FrameException{
		QysdsSet qysdsSetVo=new QysdsSet();
		Zsfs zsfs=new Zsfs();
		Date rq=DateUtils.getDateTime(startTime);
		Date qsrq=DateUtils.getDateTime(startTime);
		Date jzrq=DateUtils.getDateTime(endTime);
		qysdsSetVo =ServiceManager.getInstance().getQysdsInfoServer().getQysdsInfo(conn, jsjdm, rq, qsrq, jzrq, Constants.QYSDS_NB_BBFS);
		zsfs=qysdsSetVo.getZsfs();
		
		return zsfs;
	}
	/**
	 * 获取历史表vo
	 * @param nsrjbxx_Vo2014
	 * @param nsrjbxxb_2014_hisVo
	 * @return  nsrjbxxb_2014_hisVo
	 */
	public static sb_jl_qysds_nsrjbxxb_2014_his getNsrjbxxb_2014_hisVo(Connection conn,sb_jl_qysds_nsrjbxxb_2014 nsrjbxx_Vo2014,
			sb_jl_qysds_nsrjbxxb_2014_his nsrjbxxb_2014_hisVo){
		    String jsjdm=nsrjbxx_Vo2014.getJsjdm(); 
			nsrjbxxb_2014_hisVo.setId(TinyTools.getXh(jsjdm));
			nsrjbxxb_2014_hisVo.setBbmsf(nsrjbxx_Vo2014.getBbmsf());
			nsrjbxxb_2014_hisVo.setChcbjjff(nsrjbxx_Vo2014.getChcbjjff());
			nsrjbxxb_2014_hisVo.setCjr(nsrjbxx_Vo2014.getCjr());
			nsrjbxxb_2014_hisVo.setCjrq(nsrjbxx_Vo2014.getCjrq());
			nsrjbxxb_2014_hisVo.setCsgjfxzhjzhy(nsrjbxx_Vo2014.getCsgjfxzhjzhy());
			nsrjbxxb_2014_hisVo.setCyrs(nsrjbxx_Vo2014.getCyrs());
			nsrjbxxb_2014_hisVo.setCzjwgljy(nsrjbxx_Vo2014.getCzjwgljy());
			nsrjbxxb_2014_hisVo.setBbmsf(nsrjbxx_Vo2014.getDwtzqyid());
			nsrjbxxb_2014_hisVo.setFzjgsfftqysds(nsrjbxx_Vo2014.getFzjgsfftqysds());
			nsrjbxxb_2014_hisVo.setGdzczjff(nsrjbxx_Vo2014.getGdzczjff());
			nsrjbxxb_2014_hisVo.setHznsqy(nsrjbxx_Vo2014.getHznsqy());
			nsrjbxxb_2014_hisVo.setHznsqylx(nsrjbxx_Vo2014.getHznsqylx());
			nsrjbxxb_2014_hisVo.setHzxshsff(nsrjbxx_Vo2014.getHzxshsff());
			nsrjbxxb_2014_hisVo.setJsjdm(jsjdm);
			nsrjbxxb_2014_hisVo.setJwzzkgjmqy(nsrjbxx_Vo2014.getJwzzkgjmqy());
			nsrjbxxb_2014_hisVo.setJzbwb(nsrjbxx_Vo2014.getJzbwb());
			nsrjbxxb_2014_hisVo.setKjdacfd(nsrjbxx_Vo2014.getKjdacfd());
			nsrjbxxb_2014_hisVo.setKjhsrj(nsrjbxx_Vo2014.getKjhsrj());
			nsrjbxxb_2014_hisVo.setKjzchgjsffsbh(nsrjbxx_Vo2014.getKjzchgjsffsbh());
			nsrjbxxb_2014_hisVo.setLrr(nsrjbxx_Vo2014.getLrr());
			nsrjbxxb_2014_hisVo.setLrrq(nsrjbxx_Vo2014.getLrrq());
			nsrjbxxb_2014_hisVo.setNd(nsrjbxx_Vo2014.getNd());
			nsrjbxxb_2014_hisVo.setNsrmc(nsrjbxx_Vo2014.getNsrmc());
			nsrjbxxb_2014_hisVo.setNsrsbh(nsrjbxx_Vo2014.getNsrsbh());
			nsrjbxxb_2014_hisVo.setNsrzt(nsrjbxx_Vo2014.getNsrzt());
			nsrjbxxb_2014_hisVo.setQtsykjzzhkjzz(nsrjbxx_Vo2014.getQtsykjzzhkjzz());
			nsrjbxxb_2014_hisVo.setQyzygdid(nsrjbxx_Vo2014.getQyzygdid());
			nsrjbxxb_2014_hisVo.setSblx(nsrjbxx_Vo2014.getSblx());
			nsrjbxxb_2014_hisVo.setSbnd(nsrjbxx_Vo2014.getSbnd());
			nsrjbxxb_2014_hisVo.setSdsjsff(nsrjbxx_Vo2014.getSdsjsff());
			nsrjbxxb_2014_hisVo.setSfjrqsq(nsrjbxx_Vo2014.getSfjrqsq());
			nsrjbxxb_2014_hisVo.setSfwcqs(nsrjbxx_Vo2014.getSfwcqs());
			nsrjbxxb_2014_hisVo.setSksssqq(nsrjbxx_Vo2014.getSksssqq());
			nsrjbxxb_2014_hisVo.setSksssqz(nsrjbxx_Vo2014.getSksssqz());
			nsrjbxxb_2014_hisVo.setSndsfxxwlqy(nsrjbxx_Vo2014.getSndsfxxwlqy());
			nsrjbxxb_2014_hisVo.setSsgs(nsrjbxx_Vo2014.getSsgs());
			nsrjbxxb_2014_hisVo.setSsgslx(nsrjbxx_Vo2014.getSsgslx());
			nsrjbxxb_2014_hisVo.setSshy(nsrjbxx_Vo2014.getSshy());
			nsrjbxxb_2014_hisVo.setSshymc(nsrjbxx_Vo2014.getSshymc());
			nsrjbxxb_2014_hisVo.setSwjgzzjgdm(nsrjbxx_Vo2014.getSwjgzzjgdm());
			nsrjbxxb_2014_hisVo.setSwjgzzjgmc(nsrjbxx_Vo2014.getSwjgzzjgmc());
			nsrjbxxb_2014_hisVo.setSyjdlx(nsrjbxx_Vo2014.getSyjdlx());
			nsrjbxxb_2014_hisVo.setSykjzzhkjzz(nsrjbxx_Vo2014.getSykjzzhkjzz());
			nsrjbxxb_2014_hisVo.setSyqdjzrq(nsrjbxx_Vo2014.getSyqdjzrq());
			nsrjbxxb_2014_hisVo.setSyqdqsrq(nsrjbxx_Vo2014.getSyqdqsrq());
			nsrjbxxb_2014_hisVo.setVersion(nsrjbxx_Vo2014.getVersion());
			nsrjbxxb_2014_hisVo.setZczbje(nsrjbxx_Vo2014.getZczbje());
			nsrjbxxb_2014_hisVo.setZcze(nsrjbxx_Vo2014.getZcze());
			nsrjbxxb_2014_hisVo.setSqlx(nsrjbxx_Vo2014.getSqlx());
			return nsrjbxxb_2014_hisVo;
		
	}
	/**
	 * 给对外投资的list排序，选前五个
	 * @param qyzygdList
	 * @return
	 */
	public static List<sb_jl_qysds_dwtz> sortDwtzList(List<sb_jl_qysds_dwtz> dwtzList) {
			try {
				if (dwtzList != null && dwtzList.size() > 0) {
					Comparator<sb_jl_qysds_dwtz> comparator = new Comparator<sb_jl_qysds_dwtz>() {
						public int compare(sb_jl_qysds_dwtz s1, sb_jl_qysds_dwtz s2) {
							return s2.getTzbl().compareTo(s1.getTzbl());
						}
					};
					Collections.sort(dwtzList, comparator);
				}

				// 获取前五个个对象
				List<sb_jl_qysds_dwtz> sortDwtzList = new ArrayList<sb_jl_qysds_dwtz>();
				if (dwtzList.size() > 5) {
					for (int i = 0; i < 5; i++) {
						sortDwtzList.add(dwtzList.get(i));
					}
					return sortDwtzList;
				} else {
					return dwtzList;
				}
			} catch (Exception e) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				e.printStackTrace();
				System.out.print("=================");
			}

			return dwtzList;
		}
	/**
	 * 企业主要股东list排序
	 * @param qyzygdList
	 * @return
	 */
	public static List<sb_jl_qysds_qyzygd> sortList(List<sb_jl_qysds_qyzygd> qyzygdList) {
		try {
			if (qyzygdList != null && qyzygdList.size() > 0) {
				Comparator<sb_jl_qysds_qyzygd> comparator = new Comparator<sb_jl_qysds_qyzygd>() {
					public int compare(sb_jl_qysds_qyzygd s1, sb_jl_qysds_qyzygd s2) {
						return s2.getTzbl().compareTo(s1.getTzbl());
					}
				};
				Collections.sort(qyzygdList, comparator);
			}

			// 获取前五个个对象
			List<sb_jl_qysds_qyzygd> sortQyzygdList = new ArrayList<sb_jl_qysds_qyzygd>();
			if (qyzygdList.size() > 5) {
				for (int i = 0; i < 5; i++) {
					sortQyzygdList.add(qyzygdList.get(i));
				}
				return sortQyzygdList;
			} else {
				return qyzygdList;
			}
		} catch (Exception e) {
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			e.printStackTrace();
			System.out.print("=================");
		}

		return qyzygdList;
	}
	/**
	 * 获取保存的纳税人基本信息vo
	 * @param userData
	 * @param nsrjbxx_Vo2014
	 * @param sbnd
	 * @param zygdid
	 * @return
	 * @throws FrameException
	 */
	public static sb_jl_qysds_nsrjbxxb_2014 getNsrjbxxb2014Vo(Yh userData,sb_jl_qysds_nsrjbxxb_2014   nsrjbxx_Vo2014,String sbnd,String zygdid) throws FrameException{
		nsrjbxx_Vo2014.setSbnd(sbnd);
		nsrjbxx_Vo2014.setCjr(userData.getYhid());
		if(nsrjbxx_Vo2014.getCjrq()==null||"".equals(nsrjbxx_Vo2014.getCjrq())){
			nsrjbxx_Vo2014.setCjrq(FrameCommonAccess.getDBDate());
		}
		nsrjbxx_Vo2014.setLrr(userData.getYhid());
		nsrjbxx_Vo2014.setLrrq(FrameCommonAccess.getDBDate());
		nsrjbxx_Vo2014.setVersion(QysdsNbConstant.REPORT_VERSION_2014);
		nsrjbxx_Vo2014.setQyzygdid(zygdid);
		nsrjbxx_Vo2014.setDwtzqyid(zygdid);
		return nsrjbxx_Vo2014;
		
	}
	/**
	 * 获取主要股东vo
	 * @param userData
	 * @param qyzygdVo
	 * @param jsjdm
	 * @param qyzygdid
	 * @return
	 * @throws FrameException 
	 */
	public static sb_jl_qysds_qyzygd  getQyzygdVo(Yh userData,sb_jl_qysds_qyzygd qyzygdVo,String jsjdm,String qyzygdid) throws FrameException{
			String id=TinyTools.getXh(jsjdm);
			qyzygdVo.setCjr(userData.getYhid());
			if(qyzygdVo.getCjrq()==null||"".equals(qyzygdVo.getCjrq())){
				qyzygdVo.setCjrq(FrameCommonAccess.getDBDate());
			}
			qyzygdVo.setLrr(userData.getYhid());
			qyzygdVo.setLrrq(FrameCommonAccess.getDBDate());
			qyzygdVo.setSwjgzzjgdm(userData.getSsdwdm());
			qyzygdVo.setSwjgzzjgmc(userData.getSsdwmc());
			qyzygdVo.setId(id);
			qyzygdVo.setQyzygdid(qyzygdid);
		    return qyzygdVo;
  }
	
	public static sb_jl_qysds_dwtz getDwtzVo(Yh userData,sb_jl_qysds_dwtz dwtzVo,String jsjdm,String dwtzid) throws FrameException{
		String tzid=TinyTools.getXh(jsjdm);
		dwtzVo.setCjr(userData.getYhid());
		if(dwtzVo.getCjrq()==null||"".equals(dwtzVo.getCjrq())){
			dwtzVo.setCjrq(FrameCommonAccess.getDBDate());
		}
		dwtzVo.setLrr(userData.getYhid());
		dwtzVo.setLrrq(FrameCommonAccess.getDBDate());
		dwtzVo.setSwjgzzjgdm(userData.getSsdwdm());
		dwtzVo.setSwjgzzjgmc(userData.getSsdwmc());
		dwtzVo.setId(tzid);
		dwtzVo.setDwtzqyid(dwtzid);
		return dwtzVo;
	}
	
}

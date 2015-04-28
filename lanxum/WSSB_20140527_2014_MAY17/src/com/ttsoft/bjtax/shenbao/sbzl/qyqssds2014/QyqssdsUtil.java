package com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.syax.common.util.CAcodeConstants;
import com.syax.frame.exception.BaseException;
import com.syax.frame.exception.ExceptionUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.shenbao.constant.CAConstants;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.bo.NsrJbxxBo;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo.NsrJbxxVo;
import com.ttsoft.bjtax.shenbao.sbzl.qyqssds2014.xmlvo.QyqssdsNsrJbxxVo;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.bo.NsrJbxxBO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsnew.xmlvo.NsrJbxxdjbVO;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.FriendHelper;


public class QyqssdsUtil {

	/**
	 * 基本信息表 将BO对象转换为XML-VO对象。
	 *
	 * @param NsrJbxxBo
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public QyqssdsNsrJbxxVo NsrJbxxconvert2XMLVO(NsrJbxxBo nsrJbxxBO,
			SWDJJBSJ djJbsj) throws BaseException {

		QyqssdsNsrJbxxVo nsrJbxxdjbVO = new QyqssdsNsrJbxxVo();
		NsrJbxxVo nsrjbxx = new NsrJbxxVo();

		// //////////////////////////////////
		// 填充VO。

		nsrjbxx.setBbmsf(nsrJbxxBO.getBbmsf());
		nsrjbxx.setCjr(nsrJbxxBO.getCjr());
		nsrjbxx.setCjrq(nsrJbxxBO.getCjrq());
		nsrjbxx.setJsjdm(nsrJbxxBO.getJsjdm());
		nsrjbxx.setJydz(nsrJbxxBO.getJydz());
		nsrjbxx.setLrr(nsrJbxxBO.getLrr());
		nsrjbxx.setLrrq(nsrJbxxBO.getLrrq());
		nsrjbxx.setQsllry(nsrJbxxBO.getQsllry());
		nsrjbxx.setLxdh(nsrJbxxBO.getLxdh());
		nsrjbxx.setNsrmc(nsrJbxxBO.getNsrmc());
		nsrjbxx.setNsrsbh(nsrJbxxBO.getNsrsbh());
		nsrjbxx.setTbrq(nsrJbxxBO.getTbrq());
		nsrjbxx.setQsbaksrq(nsrJbxxBO.getQsbaksrq());
		nsrjbxx.setQsbajsrq(nsrJbxxBO.getQsbajsrq());
		nsrjbxx.setQssbksrq(nsrJbxxBO.getQssbksrq());
		nsrjbxx.setQssbjsrq(nsrJbxxBO.getQssbjsrq());
		nsrjbxx.setSqspbh(nsrJbxxBO.getSqspbh());
		nsrjbxx.setSshydm(nsrJbxxBO.getSshydm());
		nsrjbxx.setSshymc(nsrJbxxBO.getSshymc());
		nsrjbxx.setSsjjlxdm(nsrJbxxBO.getSsjjlxdm());
		nsrjbxx.setSsjjlxmc(nsrJbxxBO.getSsjjlxmc());
		nsrjbxx.setSwjgzzjgdm(nsrJbxxBO.getSwjgzzjgdm());
		nsrjbxx.setSwjgzzjgmc(nsrJbxxBO.getSwjgzzjgmc());
		nsrjbxx.setVersion(nsrJbxxBO.getVersion());
		nsrjbxx.setXtjb(nsrJbxxBO.getXtjb());
		nsrjbxx.setBaShztbs(nsrJbxxBO.getBaShztbs());
		nsrjbxx.setBaShztMessage(nsrJbxxBO.getBaShztMessage());
		nsrjbxx.setJyqxjm(nsrJbxxBO.getJyqxjm());
		nsrjbxx.setGdjyjs(nsrJbxxBO.getGdjyjs());
		nsrjbxx.setYfdxgb(nsrJbxxBO.getYfdxgb());
		nsrjbxx.setYfxgpc(nsrJbxxBO.getYfxgpc());
		nsrjbxx.setYfgdqs(nsrJbxxBO.getYfgdqs());
		nsrjbxx.setQtyy(nsrJbxxBO.getQtyy());
		//added by zhangj,是否无需进行备案
		nsrjbxx.setSfwxjxba(getSfwxjsba(nsrJbxxBO.getJsjdm()).get(CAConstants.SMSB_QYQSSDS2014_SFWXJXBA).toString());
		
		// 纳税人基本信息
		nsrJbxxdjbVO.setNsrjbxx(nsrjbxx);

		// XML文档信息
		nsrJbxxdjbVO.setXsltVersion(QyqssdsConstant.CA_XSLTDM_QYQSSDS);
		nsrJbxxdjbVO.setSchemaVersion(QyqssdsConstant.CA_SCHEMADM_QYQSSDS);
		nsrJbxxdjbVO.setYwlx(QyqssdsConstant.CA_YWLXDM_QYQSSDS);
		nsrJbxxdjbVO.setYwczlx(CAcodeConstants.YWCZLX_SHOW);
		
		
		

		return nsrJbxxdjbVO;
	}
	
	/**
	 * 基本信息表 将XML-VO对象转换为BO对象。
	 *
	 * @param qysdsjd
	 * @param djJbsj
	 * @return
	 * @throws BaseException
	 */
	public NsrJbxxBo NsrJbxxconvert2VO(QyqssdsNsrJbxxVo nsrJbxxdjbVO)
			throws BaseException {

		NsrJbxxBo nsrJbxxBO = new NsrJbxxBo();
		NsrJbxxVo nsrjbxx = new NsrJbxxVo();
		nsrjbxx = nsrJbxxdjbVO.getNsrjbxx();

		// //////////////////////////////////
		// 填充VO。

		nsrJbxxBO.setBbmsf(nsrjbxx.getBbmsf());
		nsrJbxxBO.setCjr(nsrjbxx.getCjr());
		nsrJbxxBO.setCjrq(nsrjbxx.getCjrq());
		nsrJbxxBO.setJsjdm(nsrjbxx.getJsjdm());
		nsrJbxxBO.setJydz(nsrjbxx.getJydz());
		nsrJbxxBO.setLrr(nsrjbxx.getLrr());
		nsrJbxxBO.setLrrq(nsrjbxx.getLrrq());
		nsrJbxxBO.setLxdh(nsrjbxx.getLxdh());
		nsrJbxxBO.setNsrmc(nsrjbxx.getNsrmc());
		nsrJbxxBO.setNsrsbh(nsrjbxx.getNsrsbh());
		nsrJbxxBO.setSqspbh(nsrjbxx.getSqspbh());
		nsrJbxxBO.setSshydm(nsrjbxx.getSshydm());
		nsrJbxxBO.setSshymc(nsrjbxx.getSshymc());
		nsrJbxxBO.setSsjjlxdm(nsrjbxx.getSsjjlxdm());
		nsrJbxxBO.setSsjjlxmc(nsrjbxx.getSsjjlxmc());
		nsrJbxxBO.setSwjgzzjgdm(nsrjbxx.getSwjgzzjgdm());
		nsrJbxxBO.setSwjgzzjgmc(nsrjbxx.getSwjgzzjgmc());
		nsrJbxxBO.setVersion(nsrjbxx.getVersion());
		nsrJbxxBO.setXtjb(nsrjbxx.getXtjb());
		nsrJbxxBO.setBaShtgrq(nsrjbxx.getBaShtgrq());
		nsrJbxxBO.setBaShztbs(nsrjbxx.getBaShztbs());
		nsrJbxxBO.setGdjyjs(nsrjbxx.getGdjyjs());
		nsrJbxxBO.setBaShztMessage(nsrjbxx.getBaShztMessage());
		nsrJbxxBO.setJydz(nsrjbxx.getJydz());
		nsrJbxxBO.setJyqxjm(nsrjbxx.getJyqxjm());
		nsrJbxxBO.setQsbajsrq(nsrjbxx.getQsbajsrq());
		nsrJbxxBO.setQsbaksrq(nsrjbxx.getQsbaksrq());
		nsrJbxxBO.setQsllry(nsrjbxx.getQsllry());
		nsrJbxxBO.setQssbjsrq(nsrjbxx.getQssbjsrq());
		nsrJbxxBO.setQssbksrq(nsrjbxx.getQssbksrq());
		nsrJbxxBO.setQtyy(nsrjbxx.getQtyy());
		nsrJbxxBO.setSbShtgrq(nsrjbxx.getSbShtgrq());
		nsrJbxxBO.setSbShztbs(nsrjbxx.getSbShztbs());
		nsrJbxxBO.setSbShztMessage(nsrjbxx.getSbShztMessage());
		nsrJbxxBO.setSqspbh(nsrjbxx.getSqspbh());
		nsrJbxxBO.setSqlxdm(nsrjbxx.getSqlxdm());
		nsrJbxxBO.setYfdxgb(nsrjbxx.getYfdxgb());
		nsrJbxxBO.setYfgdqs(nsrjbxx.getYfgdqs());
		nsrJbxxBO.setYfxgpc(nsrjbxx.getYfxgpc());
		nsrJbxxBO.setTbrq(nsrjbxx.getTbrq());
		
		return nsrJbxxBO;
	}
	
	/**
	 * 获取审核状态标识
	 * @param nsrJbxxBO
	 * @throws BaseException
	 */
	public void getBaShztBs(NsrJbxxBo nsrJbxxBO) throws BaseException {
		// 数据库连接对象
		Connection conn = null;
		// 获得数据库连接
		try {
			conn = DBResource.getConnection();
			PreparedStatement ps;
			ResultSet rs;
			StringBuffer bf = new StringBuffer();
			// select
			bf.append(" select ")
					// 查询字段
					.append(" NSRJSJDM,NSRSBH,NSRMC,VERSION,SSJJLX, ")
					// 子查询-根据登记注册类型代码表查询登记注册类型名称
					// 所属经济类型-登记注册类型
					.append(" (SELECT DJZCLXMC FROM DMDB.DJ_DM_DJZCLX T2 WHERE T2.DJZCLXDM=T1.SSJJLX) AS SSJJLXMC, ")
					.append(" LXDH,JYDZ,SSHY, ")
					// 子查询-根据所属行业代码表查询所属行业名称
					.append(" (SELECT GJBZHYMC FROM DMDB.GY_DM_GJBZHY T3 WHERE T3.GJBZHYDM=T1.SSHY) AS SSHYMC,")
					.append(" SWJGZZJGDM, ")
					// 子查询-根据税务机关组织代码查询税务机关组织机构名称
					.append(" (SELECT SWJGZZJGMC FROM DMDB.GY_DM_SWJGZZJG T5 WHERE T5.SWJGZZJGDM=T1.SWJGZZJGDM) AS SWJGZZJGMC, ")
					.append(" CJR,CJSJ,LRR,LRSJ,XTJB,BBMSF,REMARK1,REMARK2,QSLLRY,TBRQ,QSBAKSRQ,QSBAJSRQ,BASHZTBS,BASHTGRQ,SBSHZTBS,SBSHTGRQ,JYQXJM,GDJYJS,YFDXGB,YFXGPC,YFGDQS,QTYY,SQLXDM,QSSBKSRQ,QSSBJSRQ ")
					// from
					.append(" from SBDB.SB_JL_QYQSSDSBA_NSRJBXXB t1 ")
					// where
					.append(" where  t1.nsrjsjdm = ? ");

			System.out.println("企业清算所得税-基本信息查询SQL");
			System.out.println(bf.toString());

			ps = conn.prepareStatement(bf.toString());
			ps.setString(1, nsrJbxxBO.getJsjdm());
			rs = ps.executeQuery();

			if (rs.next()) {
				nsrJbxxBO.setBaShztbs(rs.getString("BASHZTBS"));
			}

			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
		} catch (Exception ex) {
			throw ExceptionUtil.getBaseException(ex, "查询企业清算所得税备案纳税人基本信息表失败");
		} finally {
			// 关闭数据库连接
			DBResource.destroyConnection(conn);
		}

	}
	 /**
	    * 获取是否无需备案状态
	    * @author zhangj
	    * @param jsjdm
	    * @throws BaseException
	    */
	   
	   public  Map getSfwxjsba(String jsjdm) throws BaseException{
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			HashMap map=new HashMap();
			String sfwxjxba="";
			try {
				conn = DBResource.getConnection();
				String showSql="select JSJDM,SFWXJXBA from SBDB.SB_JL_QYQSSDS_WXBADJB  where jsjdm=?";
				ps = conn.prepareStatement(showSql);
				ps.setString(1, jsjdm);
				rs = ps.executeQuery();
				if(rs.next()){

					sfwxjxba=rs.getString("SFWXJXBA")==null ?"":rs.getString("SFWXJXBA");
					//map.put(CodeConstant.SMSB_QYQSSDS2014_SBSHZTBS,"");
				}
				map.put(CAConstants.SMSB_QYQSSDS2014_SFWXJXBA,sfwxjxba);
			} catch (Exception ex) {
				// 抛出异常
				System.out.println("catch exception  .............."+ex);
				ex.printStackTrace();
				throw ExceptionUtil.getBaseException(ex);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (ps != null) {
						ps.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				DBResource.destroyConnection(conn);
			}
			return map;
	   }   
}

package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.processor;


/**
 * <p>Title: 扣缴企业所得税-备案登记表Processer</p>
 *
 * <p>Description: 处理备案登记表业务后台数据处理</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
 *
 * @author tum
 * @version 1.0
 */

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ApplicationException;
import java.sql.Connection;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.ttsoft.bjtax.shenbao.constant.ActionConstant;
import com.ttsoft.bjtax.shenbao.util.StringUtils;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;
import com.syax.bjtax.shenbao.model.kjqysds.BAHTXX;
import com.syax.bjtax.shenbao.model.kjqysds.FJMQYXX;
import com.syax.bjtax.shenbao.model.kjqysds.KJYWRXX;
import com.syax.bjtax.shenbao.model.kjqysds.MXXXVO;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.KjqysdsConstant;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo.BadjbBO;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.dj.util.DjStringUtil;
import com.ttsoft.bjtax.dj.CAConstants;
import com.syax.common.util.CAcodeConstants;
import com.syax.bjtax.ca.util.DzyjHelper;
import com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.xmlvo.BadjbVO;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.ttsoft.bjtax.shenbao.sbzl.qysdsjdks.QysdsksMapConstant;


public class BadjbProcessor implements Processor
{
    public BadjbProcessor()
    {
    }

    /**
     * 应用处理转发器
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object process(VOPackage vo) throws BaseException
    {
        Object result = null;

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case ActionConstant.INT_ACTION_QUERY:
                result = doQueryKjrInfo(vo);
                break;
            case ActionConstant.INT_ACTION_SAVE:
                result = doSave(vo);
                break;
            case ActionConstant.INT_ACTION_QUERY_MXXX:
                result = doQueryKjrRecords(vo);
                break;
            case ActionConstant.INT_ACTION_VIEWMX:
                result = doViewMX(vo);
                break;
            case ActionConstant.INT_ACTION_DELETE:
                result = doDelete(vo);
                break;
            default:
                throw new ApplicationException("用户执行了系统不支持的方法或功能.");
        }

        return result;
    }

    /**
     * 查询扣缴义务人信息
     * @param vo VOPackage19830411cc19830
     * @return Object
     * @throws BaseException
     */
    private Object doQueryKjrInfo(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doQueryKjrInfo ==========");
        // 获取UserData
        UserData ud = vo.getUserData();
        // 获取bo
        BadjbBO bo = (BadjbBO) vo.getData();

        try
        {
            // 扣缴人信息
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
            this.getKjywrxxByDjInfo(bo, ud);
            // 申报日期
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            bo.setTbrq(sdf.format(curDate));

            sdf = new SimpleDateFormat("yyyy年MM月dd日");
            bo.setTbrqShow(sdf.format(curDate));
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        return bo;
    }




    /**
     * 保存备案登记表信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doSave ==========");
        DzyjHelper dh = new DzyjHelper();
        Map retData = new HashMap();
        Map data = (Map) vo.getData();
        // 获取UserData
        UserData ud = vo.getUserData();

        // BadjbVO
        BadjbVO badjbVO = (BadjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        // BadjbBO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        // BadjbBO
        BadjbBO bo = (BadjbBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

        // 数据库连接
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // 获取数据库连接
            conn = DBResource.getConnection();
            // sqlBuffer
            StringBuffer sql = new StringBuffer();
            bo.setJsjdm(bo.getKjywrxx().getKjrjsjdm());
            // 备案登记序号
            String badjxh = new String();
            
            // 如果是修改后保存，则需要先删后插入数据
            if("1".equals(bo.getModifyFlag()))
            {
            	badjxh = bo.getBadjxh();
            	
            	System.out.println("========开始删除已保存的数据=======");
            	System.out.println("badjxh = " + badjxh);
            	try
            	{
            		// 删除sbdb.sb_jl_kjqysds_htbadjb
            		sql.append("delete from sbdb.sb_jl_kjqysds_htbadjb where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// 删除sbdb.SB_JL_KJQYSDS_KJYWR
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_KJYWR where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// 删除sbdb.SB_JL_KJQYSDS_FJMQYXX
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_FJMQYXX where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// 删除sbdb.SB_JL_KJQYSDS_BAHTMX
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_BAHTMX where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            	}
            	catch(Exception ex)
            	{
            		ex.printStackTrace();
                    throw new ApplicationException("保存备案登记表-删除已保存的数据失败!");
            	}
            }
            else
            {
            	badjxh = this.createBadjxh(conn, bo.getJsjdm());
            }
            
            // 判断合同编号
            try
            {
            	sql.delete(0, sql.length());
            	sql.append("select count(*) from sbdb.sb_jl_kjqysds_htbadjb a, sbdb.sb_jl_kjqysds_bahtmx b ");
            	sql.append("where a.jsjdm = '").append(bo.getJsjdm()).append("' ");
            	sql.append("and a.badjxh = b.badjxh ");
            	sql.append("and b.htmc = '").append(bo.getHtxx().getHtmc()).append("' ");
            	sql.append("and b.htbh = '").append(bo.getHtxx().getHtbh()).append("' ");
            	
            	pstmt = conn.prepareStatement(sql.toString());
            	rs = pstmt.executeQuery(sql.toString());
            	
            	rs.next();
            	
            	int htbh_count = rs.getInt(1);
            	
            	
            	if(htbh_count > 0)
            	{
            		System.out.println("=======该合同编号或合同名称已存在========");
            		// 扣缴人信息
//                    bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
            		this.getKjywrxxByDjInfo(bo, ud);
                    
            		bo.setZtbz("3");
            		bo.setMessage("该合同编号或合同名称已存在，请核对、修改！");
            		retData.put(KjqysdsConstant.RETURN_OBJECT, bo);
            		
            		// 关闭数据库对象
            		rs.close();
            		pstmt.close();
            		conn.close();
            		
            		return retData;
            	}            	
            }
            catch(Exception ex)
            {
            	ex.printStackTrace();
                throw new ApplicationException("保存备案登记表-判断备案登记信息失败!");
            }
            
            // 插入备案登记表信息
            try
            {
            	sql.delete(0, sql.length());
                sql.append("insert into sbdb.sb_jl_kjqysds_htbadjb (badjxh, jsjdm, htbh, tbrq, badjbbh, swjgzzjgdm, ztbz, lrry, lrrq, cjry, cjrq)");
                sql.append("values (");
                sql.append("'").append(badjxh).append("', "); // badjxh
                sql.append("'").append(bo.getJsjdm()).append("', "); // jsjdm
                sql.append("'").append(bo.getHtxx().getHtbh()).append("', "); // htbh
                sql.append("to_date('").append(bo.getTbrq()).append("', 'yyyymmdd'), "); // tbrq
                sql.append("null, "); // badjbbh
                sql.append("'").append(ud.getSsdwdm()).append("', "); // swjgzzjgdm
                sql.append("'0', "); // ztbz
                sql.append("'").append(ud.getYhid()).append("', "); // lrry
                sql.append("sysdate, "); // lrrq
                sql.append("'").append(ud.getYhid()).append("', "); // cjry
                sql.append("sysdate "); // cjrq
                sql.append(")");

                System.out.println("插入备案登记表sql：\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("保存备案登记表-备案登记信息失败!");
            }

            // 插入扣缴义务人信息
            try
            {
                sql.delete(0, sql.length());
                sql.append("insert into SBDB.SB_JL_KJQYSDS_KJYWR (BADJXH, JSJDM, KJRMC_EN, KJRCWFZR, KJRLXR, KJRLXDH, KJRCZHM, KJRDZ_EN, LRRY, LRRQ, CJRY, CJRQ)");
                sql.append("values (");
                sql.append("'").append(badjxh).append("', "); // BADJXH
                sql.append("'").append(bo.getJsjdm()).append("', "); // JSJDM
                sql.append("'").append(bo.getKjywrxx().getKjrmc_en()).append("', "); // KJRMC_EN
                sql.append("'").append(bo.getKjywrxx().getKjrcwfzr()).append("', "); // KJRCWFZR
                sql.append("'").append(bo.getKjywrxx().getKjrlxr()).append("', "); // KJRLXR
                sql.append("'").append(bo.getKjywrxx().getKjrlxdh()).append("', "); // KJRLXDH
                sql.append("'").append(bo.getKjywrxx().getKjrczhm()).append("', "); // KJRCZHM
                sql.append("null, "); // KJRDZ_EN
                sql.append("'").append(ud.getYhid()).append("', "); // lrry
                sql.append("sysdate, "); // lrrq
                sql.append("'").append(ud.getYhid()).append("', "); // cjry
                sql.append("sysdate "); // cjrq
                sql.append(")");

                System.out.println("插入扣缴义务人信息sql：\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("保存备案登记表-插入扣缴义务人信息失败!");
            }

            // 插入非居民企业信息
            try
            {
                sql.delete(0, sql.length());
                sql.append("insert into SBDB.SB_JL_KJQYSDS_FJMQYXX (BADJXH, FJMGB, FJMGJDQ, FJMJMGNSSBH, FJMMC_CN, FJMMC_EN, ");
                sql.append("FJMJMGMC_CN, FJMJMGMC_EN, FJMDZ_CN, FJMDZ_EN, FJMCWFZR, FJMLXR, FJMLXDH, FJMCZHM, LRRY, LRRQ, CJRY, CJRQ)");
                sql.append("values (");
                sql.append("'").append(badjxh).append("', "); // BADJXH
                sql.append("'").append(bo.getFjmqyxx().getFjmgb()).append("', "); // FJMGB
                sql.append("'").append(bo.getFjmqyxx().getFjmgjdq()).append("', "); // FJMGJDQ
                sql.append("null, "); // FJMJMGNSSBH
                sql.append("'").append(bo.getFjmqyxx().getFjmmc_cn()).append("', "); // FJMMC_CN
                sql.append("'").append(bo.getFjmqyxx().getFjmmc_en()).append("', "); // FJMMC_EN
                sql.append("null, "); // FJMJMGMC_CN
                sql.append("null, "); // FJMJMGMC_EN
                sql.append("'").append(bo.getFjmqyxx().getFjmdz_cn()).append("', "); // FJMDZ_CN
                sql.append("'").append(bo.getFjmqyxx().getFjmdz_en()).append("', "); // FJMDZ_EN
                sql.append("'").append(bo.getFjmqyxx().getFjmcwfzr()).append("', "); // FJMCWFZR
                sql.append("'").append(bo.getFjmqyxx().getFjmlxr()).append("', "); // FJMLXR
                sql.append("'").append(bo.getFjmqyxx().getFjmlxdh()).append("', "); // FJMLXDH
                sql.append("'").append(bo.getFjmqyxx().getFjmczhm()).append("', "); // FJMCZHM
                sql.append("'").append(ud.getYhid()).append("', "); // lrry
                sql.append("sysdate, "); // lrrq
                sql.append("'").append(ud.getYhid()).append("', "); // cjry
                sql.append("sysdate "); // cjrq
                sql.append(")");

                System.out.println("插入非居民企业信息sql：\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("保存备案登记表-插入非居民企业信息失败!");
            }

            // 插入合同信息
            try
            {
                sql.delete(0, sql.length());
                sql.append("insert into SBDB.SB_JL_KJQYSDS_BAHTMX (BADJXH, HTBH, HTMC, HTQYRQ, HTZXQSRQ, HTZXZZRQ, HTYXQ, HTJE, ");
                sql.append("BZ, ZFXM, FKCS, QTZLMC, LRRY, LRRQ, CJRY, CJRQ)");
                sql.append("values (");
                sql.append("'").append(badjxh).append("', "); // BADJXH
                sql.append("'").append(bo.getHtxx().getHtbh()).append("', "); // HTBH
                sql.append("'").append(bo.getHtxx().getHtmc()).append("', "); // HTMC
                sql.append("to_date('").append(bo.getHtxx().getQyrq()).append("', 'yyyymmdd'), "); // HTQYRQ
                sql.append("null, "); // HTZXQSRQ
                sql.append("null, "); // HTZXZZRQ
                sql.append("to_date('").append(bo.getHtxx().getHtyxq()).append("', 'yyyymmdd'), "); // HTYXQ
                sql.append(bo.getHtxx().getHtje()).append(", "); // HTJE
                sql.append("'").append(bo.getHtxx().getBz()).append("', "); // BZ
                sql.append("'").append(bo.getHtxx().getZfxm()).append("', "); // ZFXM
                sql.append(bo.getHtxx().getFkcs()).append(", "); // FKCS
                sql.append("'").append(bo.getHtxx().getQtzlmc()).append("', "); // QTZLMC
                sql.append("'").append(ud.getYhid()).append("', "); // lrry
                sql.append("sysdate, "); // lrrq
                sql.append("'").append(ud.getYhid()).append("', "); // cjry
                sql.append("sysdate "); // cjrq
                sql.append(")");

                System.out.println("插入合同信息sql：\n" + sql.toString());

                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("保存备案登记表-插入合同信息失败!");
            }

            if(ud.getCaflag()) {
                System.out.println("===========签名开始==========");
                try {
                    String ywid = bo.getJsjdm() + "+"
                                  + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                    System.out.println("======ywid:" + ywid);
                    dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                    System.out.println("===========签名结束==========");
                }
                catch (Exception ex) {
                    System.out.println("===========签名失败==========");
                    throw ExceptionUtil.getBaseException(ex);

                }

                retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            }
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // 关闭数据库对象
            try
            {
            	if(rs != null)
            	{
            		rs.close();
            	}
                if(pstmt != null)
                {
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }

        return retData;
    }
    
    /**
     * 查询扣缴义务人本案登记信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryKjrRecords(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doQueryKjrRecords ==========");
        // 获取UserData
        UserData ud = vo.getUserData();
        // 获取BadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // 数据库对象
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // 扣缴人信息
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // 组织查询sql语句
            StringBuffer sql = new StringBuffer();
            sql.append("select count(*) from sbdb.sb_jl_kjqysds_htbadjb where jsjdm = ");
            sql.append("'").append(bo.getJsjdm()).append("' ");
            sql.append("and ztbz in ('0', '2')"); // 修改备案登记表操作允许操作非审核通过的数据


            // 获取数据库连接
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            // 总记录数
            int totalCount = rs.getInt(1);
            /**
             * 根据总条数，当前用户每页条数计算页数，翻页操作
             */
            if(totalCount > 0)
            {
                bo.setTotalCount(totalCount);
                // 获取当前用户每页条数
//                int myts = ud.getMyxszds();
                int myts = 10;
                // 总页数
                int totalPage = 0;
                if (totalCount % myts == 0) {
                	totalPage = totalCount / myts;
				}
				else if (totalCount % myts  > 0) {
					totalPage  = totalCount / myts + 1;
				}
                bo.setTotalPage(totalPage);
                // 当前页
                int curPage = bo.getCurrentPage() == 0 ? 1 : bo.getCurrentPage();
                if(curPage > totalPage)
                {
                	curPage = totalPage;
                }
                bo.setCurrentPage(curPage);
                // 根据当前页计算每页记录的起止索引
                int startIndex = (curPage - 1) * myts + 1;
                int endIndex = curPage * myts;

                // 组织查询sql语句
                sql.delete(0, sql.length());
                sql.append("select badjxh, htbh, htmc, htqyrq, htyxq, htje, tbrq, shzt, rowIndex from (");
                sql.append("select a.badjxh, b.htbh, b.htmc, to_char(b.htqyrq, 'yyyy-mm-dd') htqyrq, ");
                sql.append("to_char(b.htyxq, 'yyyy-mm-dd') htyxq, b.htje, to_char(a.tbrq, 'yyyy-mm-dd') tbrq, ");
                sql.append("decode(a.ztbz, '0', '待审核', '2', '备案不通过', a.ztbz) shzt, rownum rowIndex ");
                sql.append("from sbdb.sb_jl_kjqysds_htbadjb a, sbdb.sb_jl_kjqysds_bahtmx b ");
                sql.append("where a.badjxh = b.badjxh ");
                sql.append("and a.htbh = b.htbh ");
                sql.append("and a.jsjdm = '").append(bo.getJsjdm()).append("' ");
                sql.append("and a.ztbz in ('0', '2')"); // 修改备案登记表操作允许操作非审核通过的数据
                sql.append("order by a.cjrq, a.badjxh) ");
                sql.append("where rowIndex >= ").append(startIndex);
                sql.append(" and rowIndex <= ").append(endIndex);
                System.out.println("查询备案登记记录sql：\n" + sql.toString());

                pstmt = con.prepareStatement(sql.toString());
                rs = pstmt.executeQuery(sql.toString());
                // 封装返回数据
                List reList = new ArrayList();
                while(rs.next())
                {
                	MXXXVO mxxx = new MXXXVO();
                	/**
                	 * 返回处理结果
                	 */
                    // 备案登记序号（记录主键）
                	mxxx.setBadjxh(rs.getString("badjxh"));
                    // 合同编号
                    mxxx.setHtbh(rs.getString("htbh"));
                    // 合同名称
                	mxxx.setHtmc(rs.getString("htmc"));
                    // 合同签约日期
                	mxxx.setHtqyrq(rs.getString("htqyrq"));
                    // 合同有效期
                	mxxx.setHtyxq(rs.getString("htyxq"));
                    // 合同金额
                	mxxx.setHtje(rs.getString("htje"));
                    // 填报日期
                	mxxx.setTbrq(rs.getString("tbrq"));
                    // 审核状态
                	mxxx.setShzt(rs.getString("shzt"));

                    reList.add(mxxx);
                }
                bo.setRecordList(reList);
            }
            else if(totalCount == 0)
            {
                // 总条数
                bo.setTotalCount(0);
                // 总页数
                bo.setTotalPage(0);
                // 当前页
                bo.setCurrentPage(0);
            }
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // 关闭数据库对象
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                if(pstmt != null)
                {
                    pstmt.close();
                }
                if(con != null)
                {
                	con.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }


        return bo;
    }
    
    /**
     * 删除纳税人备案登记信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doDelete ==========");
        // 获取UserData
        UserData ud = vo.getUserData();
        // 获取BadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // 数据库对象
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            // 扣缴人信息
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // 组织查询sql语句
            StringBuffer sql = new StringBuffer();
            sql.append("update sbdb.sb_jl_kjqysds_htbadjb set ztbz = '9' ");
            sql.append("where badjxh = '").append(bo.getBadjxh()).append("' ");
            sql.append("and ztbz in ('0', '2')"); // 修改备案登记表操作允许操作非审核通过的数据
            System.out.println("delete sql:\n" + sql.toString());

            // 获取数据库连接
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.execute(sql.toString());
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // 关闭数据库对象
            try
            {
                if(pstmt != null)
                {
                    pstmt.close();
                }
                if(con != null)
                {
                	con.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }


        return null;
    }
    
    /**
     * 查询待审核备案登记表明细信息
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doViewMX(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doViewMX ==========");
        // 获取UserData
        UserData ud = vo.getUserData();
        // 获取BadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // 数据库对象
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // 通过登记接口获取扣缴义务人部分信息
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // 组织查询sql语句
            StringBuffer sql = new StringBuffer();
            sql.append("select a.badjxh, a.jsjdm, to_char(a.tbrq, 'yyyy-mm-dd') tbrq, b.kjrmc_en, b.kjrcwfzr, b.kjrlxr, ");
            sql.append("b.kjrlxdh, b.kjrczhm, c.fjmmc_cn, c.fjmmc_en, c.fjmgb, e.gjdqmc, c.fjmgjdq, c.fjmdz_cn, c.fjmdz_en, ");
            sql.append("c.fjmcwfzr, c.fjmlxr, c.fjmlxdh, c.fjmczhm, d.htbh, d.htmc, to_char(d.htqyrq, 'yyyymmdd') htqyrq, ");
            sql.append("to_char(d.htyxq, 'yyyymmdd') htyxq, d.htje, d.bz, f.bzmc, d.zfxm, d.fkcs, d.qtzlmc ");
            sql.append("from sbdb.sb_jl_kjqysds_htbadjb a, sbdb.sb_jl_kjqysds_kjywr b, sbdb.sb_jl_kjqysds_fjmqyxx c, ");
            sql.append("sbdb.sb_jl_kjqysds_bahtmx d, dmdb.gy_dm_gjdq e, dmdb.gy_dm_bz f ");
            sql.append("where a.badjxh = b.badjxh and a.badjxh = c.badjxh and a.badjxh = d.badjxh ");
            sql.append("and a.jsjdm = b.jsjdm and a.htbh = d.htbh and c.fjmgb = e.gjdqdm and d.bz = f.bzdm ");
            sql.append("and a.badjxh = '").append(bo.getBadjxh()).append("' ");
            sql.append("and a.jsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and a.ztbz <> '1' "); // 修改备案登记表操作
            System.out.println("查询备案登记详细信息sql：\n" + sql.toString());


            // 获取数据库连接
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();

            // 申报日期
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            bo.setTbrq(sdf.format(curDate));

            sdf = new SimpleDateFormat("yyyy年MM月dd日");
            bo.setTbrqShow(sdf.format(curDate));
            /**
             * 扣缴义务人信息
             */
            KJYWRXX kjywrxx = bo.getKjywrxx();
            // 扣缴人英文名称
            kjywrxx.setKjrmc_en(rs.getString("kjrmc_en"));
            // 扣缴人财务负责人
            kjywrxx.setKjrcwfzr(rs.getString("kjrcwfzr"));
            // 扣缴人联系人
            kjywrxx.setKjrlxr(rs.getString("kjrlxr"));
            // 扣缴人联系电话
            kjywrxx.setKjrlxdh(rs.getString("kjrlxdh"));
            // 扣缴人传真
            kjywrxx.setKjrczhm(rs.getString("kjrczhm"));
            bo.setKjywrxx(kjywrxx);

            /**
             * 非居民企业信息
             */
            FJMQYXX fjmqyxx = new FJMQYXX();
            // 非居民企业中文名称
            fjmqyxx.setFjmmc_cn(rs.getString("fjmmc_cn"));
            // 非居民企业英文名称
            fjmqyxx.setFjmmc_en(rs.getString("fjmmc_en"));
            // 非居民企业国别
            fjmqyxx.setFjmgb(rs.getString("fjmgb"));
            // 非居民企业国别名称
            fjmqyxx.setFjmgbmc(rs.getString("gjdqmc"));
            // 非居民企业国家或地区
            fjmqyxx.setFjmgjdq(rs.getString("fjmgjdq"));
            if("01".equals(fjmqyxx.getFjmgjdq())){
                fjmqyxx.setFjmgjdqmc("港澳台");
            }
            else if("02".equals(fjmqyxx.getFjmgjdq()))
            {
                fjmqyxx.setFjmgjdqmc("外国");
            }
            // 非居民企业国家其居民国地址（中文）
            fjmqyxx.setFjmdz_cn(rs.getString("fjmdz_cn"));
            // 非居民企业国家其居民国地址（英文）
            fjmqyxx.setFjmdz_en(rs.getString("fjmdz_en"));
            // 非居民企业国家财务负责人
            fjmqyxx.setFjmcwfzr(rs.getString("fjmcwfzr"));
            // 非居民企业国家联系人
            fjmqyxx.setFjmlxr(rs.getString("fjmlxr"));
            // 非居民企业国家联系电话
            fjmqyxx.setFjmlxdh(rs.getString("fjmlxdh"));
            // 非居民企业国家传真
            fjmqyxx.setFjmczhm(rs.getString("fjmczhm"));
            bo.setFjmqyxx(fjmqyxx);

            /**
             * 合同信息
             */
            BAHTXX htxx = new BAHTXX();
            // 合同或协议名称
            htxx.setHtmc(rs.getString("htmc"));
            // 合同编号
            htxx.setHtbh(rs.getString("htbh"));
            // 合同签约日期
            htxx.setQyrq(rs.getString("htqyrq"));
            // 合同有效期
            htxx.setHtyxq(rs.getString("htyxq"));
            // 合同金额
            System.out.println("htje = " + rs.getString("htje"));
            htxx.setHtje(rs.getString("htje"));
            // 币种
            htxx.setBz(rs.getString("bz"));
            // 币种名称
            htxx.setBzmc(rs.getString("bzmc")); 
            // 支付项目
            htxx.setZfxm(rs.getString("zfxm"));
            // 付款次数
            htxx.setFkcs(rs.getString("fkcs"));
            // 其它资料名称
            htxx.setQtzlmc(rs.getString("qtzlmc"));
            bo.setHtxx(htxx);
        }
        catch (Exception e) {
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // 关闭数据库对象
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                if(pstmt != null)
                {
                    pstmt.close();
                }
                if(con != null)
                {
                	con.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }

        return bo;
    }

    /**
     * 生成备案登记表序号
     *    生成规则：8位计算机代码 + 8为日期 + 4位当天总数量递增1后的序号
     * @param conn Connection 数据库连接
     * @param jsjdm String 计算机代码
     * @return String 返回生成的20位编码
     * @throws BaseException
     */
    private String createBadjxh(Connection conn, String jsjdm) throws BaseException
    {
        // 备案登记号
        StringBuffer badjxh = new StringBuffer();
        // 查询系统当前时间sql
        StringBuffer sql = new StringBuffer("select to_char(sysdate,'yyyymmdd') xtsj from dual");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        badjxh.append(jsjdm);

        try
        {
            // 查询当前8为系统时间
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(rs.getString("xtsj"));

            // 查询当前用户当天合同总数
            sql.delete(0, sql.length());
            sql.append("select count(*) + 1 from sbdb.sb_jl_kjqysds_htbadjb where jsjdm = '");
            sql.append(jsjdm);
            sql.append("' and to_char(cjrq, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(StringUtils.LPAD(String.valueOf(rs.getInt(1)), 4, "0"));
            System.out.println("备案登记表序号：" + badjxh.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("创建备案登记序号错误！");
        }
        finally
        {
            try
            {
                // 关闭数据库对象
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch(Exception ex)
            {
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }

        return badjxh.toString();
    }


    /**
     * 通过登记接口获取扣缴义务人相关信息
     * @param bo BadjbBO
     * @param ud UserData
     * @throws BaseException
     */
    private void getKjywrxxByDjInfo(BadjbBO bo, UserData ud) throws BaseException
    {
        // 数据库对象
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        KJYWRXX kjywrxx = bo.getKjywrxx() == null ? new KJYWRXX(): bo.getKjywrxx();
        try
        {
            Map djMap = null;
            // 通过登记接口获取登记信息
            ServiceProxy proxy = new ServiceProxy();
            try {
                djMap = proxy.getDjInfo(bo.getJsjdm());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            SWDJJBSJ djxx = (SWDJJBSJ) djMap.get("JBSJ");

            /**
             * 补充登记信息
             */
            // 扣缴人计算机代码
            kjywrxx.setKjrjsjdm(bo.getJsjdm());
            // 扣缴人中文名称
            kjywrxx.setKjrmc_cn(djxx.getNsrmc());
            // 扣缴人纳税识别号
            kjywrxx.setKjrnssbh(djxx.getSwdjzh());
            // 扣缴人地址
            kjywrxx.setKjrdz_cn(djxx.getJydz());
            // 扣缴人邮编
            kjywrxx.setKjryzbm(djxx.getJydzyb());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("调用登记接口失败!");
        }

        try
        {
            // 查询sql语句
            StringBuffer sql = new StringBuffer("select xm from djdb.dj_jl_qyry ");
            sql.append("where jsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and zwdm='04'");
            // 获取数据库连接
            conn = DBResource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            if(rs.next()){
            // 扣缴义务人财务负责人
           		kjywrxx.setKjrcwfzr(rs.getString(1));
          	}
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("查询企业人员信息错误!");
        }
        finally
        {
            // 关闭数据库对象
            try
            {
                if(rs != null)
                {
                    rs.close();
                }
                if(pstmt != null)
                {
                    pstmt.close();
                }
                if(conn != null){
                    conn.close();
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("关闭数据库对象错误！");
            }
        }
        
        bo.setKjywrxx(kjywrxx);
    }
}

package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.processor;


/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Processer</p>
 *
 * <p>Description: �������ǼǱ�ҵ���̨���ݴ���</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
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
     * Ӧ�ô���ת����
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
                throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    /**
     * ��ѯ�۽���������Ϣ
     * @param vo VOPackage19830411cc19830
     * @return Object
     * @throws BaseException
     */
    private Object doQueryKjrInfo(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doQueryKjrInfo ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡbo
        BadjbBO bo = (BadjbBO) vo.getData();

        try
        {
            // �۽�����Ϣ
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
            this.getKjywrxxByDjInfo(bo, ud);
            // �걨����
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            bo.setTbrq(sdf.format(curDate));

            sdf = new SimpleDateFormat("yyyy��MM��dd��");
            bo.setTbrqShow(sdf.format(curDate));
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        return bo;
    }




    /**
     * ���汸���ǼǱ���Ϣ
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
        // ��ȡUserData
        UserData ud = vo.getUserData();

        // BadjbVO
        BadjbVO badjbVO = (BadjbVO) data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        // BadjbBO
        DzyjsjVO dzyj = (DzyjsjVO)data.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        // BadjbBO
        BadjbBO bo = (BadjbBO) data.get(QysdsksMapConstant.VO_KEY_KSSBSJ);

        // ���ݿ�����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // ��ȡ���ݿ�����
            conn = DBResource.getConnection();
            // sqlBuffer
            StringBuffer sql = new StringBuffer();
            bo.setJsjdm(bo.getKjywrxx().getKjrjsjdm());
            // �����Ǽ����
            String badjxh = new String();
            
            // ������޸ĺ󱣴棬����Ҫ��ɾ���������
            if("1".equals(bo.getModifyFlag()))
            {
            	badjxh = bo.getBadjxh();
            	
            	System.out.println("========��ʼɾ���ѱ��������=======");
            	System.out.println("badjxh = " + badjxh);
            	try
            	{
            		// ɾ��sbdb.sb_jl_kjqysds_htbadjb
            		sql.append("delete from sbdb.sb_jl_kjqysds_htbadjb where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// ɾ��sbdb.SB_JL_KJQYSDS_KJYWR
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_KJYWR where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// ɾ��sbdb.SB_JL_KJQYSDS_FJMQYXX
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_FJMQYXX where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            		
            		// ɾ��sbdb.SB_JL_KJQYSDS_BAHTMX
            		sql.delete(0, sql.length());
            		sql.append("delete from sbdb.SB_JL_KJQYSDS_BAHTMX where badjxh = '").append(badjxh).append("'");
            		pstmt = conn.prepareStatement(sql.toString());
            		pstmt.execute();
            	}
            	catch(Exception ex)
            	{
            		ex.printStackTrace();
                    throw new ApplicationException("���汸���ǼǱ�-ɾ���ѱ��������ʧ��!");
            	}
            }
            else
            {
            	badjxh = this.createBadjxh(conn, bo.getJsjdm());
            }
            
            // �жϺ�ͬ���
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
            		System.out.println("=======�ú�ͬ��Ż��ͬ�����Ѵ���========");
            		// �۽�����Ϣ
//                    bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
            		this.getKjywrxxByDjInfo(bo, ud);
                    
            		bo.setZtbz("3");
            		bo.setMessage("�ú�ͬ��Ż��ͬ�����Ѵ��ڣ���˶ԡ��޸ģ�");
            		retData.put(KjqysdsConstant.RETURN_OBJECT, bo);
            		
            		// �ر����ݿ����
            		rs.close();
            		pstmt.close();
            		conn.close();
            		
            		return retData;
            	}            	
            }
            catch(Exception ex)
            {
            	ex.printStackTrace();
                throw new ApplicationException("���汸���ǼǱ�-�жϱ����Ǽ���Ϣʧ��!");
            }
            
            // ���뱸���ǼǱ���Ϣ
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

                System.out.println("���뱸���ǼǱ�sql��\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���汸���ǼǱ�-�����Ǽ���Ϣʧ��!");
            }

            // ����۽���������Ϣ
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

                System.out.println("����۽���������Ϣsql��\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���汸���ǼǱ�-����۽���������Ϣʧ��!");
            }

            // ����Ǿ�����ҵ��Ϣ
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

                System.out.println("����Ǿ�����ҵ��Ϣsql��\n" + sql.toString());
                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���汸���ǼǱ�-����Ǿ�����ҵ��Ϣʧ��!");
            }

            // �����ͬ��Ϣ
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

                System.out.println("�����ͬ��Ϣsql��\n" + sql.toString());

                pstmt = conn.prepareStatement(sql.toString());
                pstmt.executeUpdate(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���汸���ǼǱ�-�����ͬ��Ϣʧ��!");
            }

            if(ud.getCaflag()) {
                System.out.println("===========ǩ����ʼ==========");
                try {
                    String ywid = bo.getJsjdm() + "+"
                                  + DjStringUtil.getCurrentDateStr(DjStringUtil.DATE_FORMAT5);
                    System.out.println("======ywid:" + ywid);
                    dzyj = dh.saveDzyjsj(dzyj, ywid, CAConstants.DADM_WHFZJG);
                    System.out.println("===========ǩ������==========");
                }
                catch (Exception ex) {
                    System.out.println("===========ǩ��ʧ��==========");
                    throw ExceptionUtil.getBaseException(ex);

                }

                retData.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
            }
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // �ر����ݿ����
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
                throw new ApplicationException("�ر����ݿ�������");
            }
        }

        return retData;
    }
    
    /**
     * ��ѯ�۽������˱����Ǽ���Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryKjrRecords(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doQueryKjrRecords ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // ���ݿ����
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // �۽�����Ϣ
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // ��֯��ѯsql���
            StringBuffer sql = new StringBuffer();
            sql.append("select count(*) from sbdb.sb_jl_kjqysds_htbadjb where jsjdm = ");
            sql.append("'").append(bo.getJsjdm()).append("' ");
            sql.append("and ztbz in ('0', '2')"); // �޸ı����ǼǱ����������������ͨ��������


            // ��ȡ���ݿ�����
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            // �ܼ�¼��
            int totalCount = rs.getInt(1);
            /**
             * ��������������ǰ�û�ÿҳ��������ҳ������ҳ����
             */
            if(totalCount > 0)
            {
                bo.setTotalCount(totalCount);
                // ��ȡ��ǰ�û�ÿҳ����
//                int myts = ud.getMyxszds();
                int myts = 10;
                // ��ҳ��
                int totalPage = 0;
                if (totalCount % myts == 0) {
                	totalPage = totalCount / myts;
				}
				else if (totalCount % myts  > 0) {
					totalPage  = totalCount / myts + 1;
				}
                bo.setTotalPage(totalPage);
                // ��ǰҳ
                int curPage = bo.getCurrentPage() == 0 ? 1 : bo.getCurrentPage();
                if(curPage > totalPage)
                {
                	curPage = totalPage;
                }
                bo.setCurrentPage(curPage);
                // ���ݵ�ǰҳ����ÿҳ��¼����ֹ����
                int startIndex = (curPage - 1) * myts + 1;
                int endIndex = curPage * myts;

                // ��֯��ѯsql���
                sql.delete(0, sql.length());
                sql.append("select badjxh, htbh, htmc, htqyrq, htyxq, htje, tbrq, shzt, rowIndex from (");
                sql.append("select a.badjxh, b.htbh, b.htmc, to_char(b.htqyrq, 'yyyy-mm-dd') htqyrq, ");
                sql.append("to_char(b.htyxq, 'yyyy-mm-dd') htyxq, b.htje, to_char(a.tbrq, 'yyyy-mm-dd') tbrq, ");
                sql.append("decode(a.ztbz, '0', '�����', '2', '������ͨ��', a.ztbz) shzt, rownum rowIndex ");
                sql.append("from sbdb.sb_jl_kjqysds_htbadjb a, sbdb.sb_jl_kjqysds_bahtmx b ");
                sql.append("where a.badjxh = b.badjxh ");
                sql.append("and a.htbh = b.htbh ");
                sql.append("and a.jsjdm = '").append(bo.getJsjdm()).append("' ");
                sql.append("and a.ztbz in ('0', '2')"); // �޸ı����ǼǱ����������������ͨ��������
                sql.append("order by a.cjrq, a.badjxh) ");
                sql.append("where rowIndex >= ").append(startIndex);
                sql.append(" and rowIndex <= ").append(endIndex);
                System.out.println("��ѯ�����ǼǼ�¼sql��\n" + sql.toString());

                pstmt = con.prepareStatement(sql.toString());
                rs = pstmt.executeQuery(sql.toString());
                // ��װ��������
                List reList = new ArrayList();
                while(rs.next())
                {
                	MXXXVO mxxx = new MXXXVO();
                	/**
                	 * ���ش�����
                	 */
                    // �����Ǽ���ţ���¼������
                	mxxx.setBadjxh(rs.getString("badjxh"));
                    // ��ͬ���
                    mxxx.setHtbh(rs.getString("htbh"));
                    // ��ͬ����
                	mxxx.setHtmc(rs.getString("htmc"));
                    // ��ͬǩԼ����
                	mxxx.setHtqyrq(rs.getString("htqyrq"));
                    // ��ͬ��Ч��
                	mxxx.setHtyxq(rs.getString("htyxq"));
                    // ��ͬ���
                	mxxx.setHtje(rs.getString("htje"));
                    // �����
                	mxxx.setTbrq(rs.getString("tbrq"));
                    // ���״̬
                	mxxx.setShzt(rs.getString("shzt"));

                    reList.add(mxxx);
                }
                bo.setRecordList(reList);
            }
            else if(totalCount == 0)
            {
                // ������
                bo.setTotalCount(0);
                // ��ҳ��
                bo.setTotalPage(0);
                // ��ǰҳ
                bo.setCurrentPage(0);
            }
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // �ر����ݿ����
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
                throw new ApplicationException("�ر����ݿ�������");
            }
        }


        return bo;
    }
    
    /**
     * ɾ����˰�˱����Ǽ���Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doDelete ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // ���ݿ����
        Connection con = null;
        PreparedStatement pstmt = null;

        try
        {
            // �۽�����Ϣ
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // ��֯��ѯsql���
            StringBuffer sql = new StringBuffer();
            sql.append("update sbdb.sb_jl_kjqysds_htbadjb set ztbz = '9' ");
            sql.append("where badjxh = '").append(bo.getBadjxh()).append("' ");
            sql.append("and ztbz in ('0', '2')"); // �޸ı����ǼǱ����������������ͨ��������
            System.out.println("delete sql:\n" + sql.toString());

            // ��ȡ���ݿ�����
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.execute(sql.toString());
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // �ر����ݿ����
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
                throw new ApplicationException("�ر����ݿ�������");
            }
        }


        return null;
    }
    
    /**
     * ��ѯ����˱����ǼǱ���ϸ��Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doViewMX(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doViewMX ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // ���ݿ����
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // ͨ���Ǽǽӿڻ�ȡ�۽������˲�����Ϣ
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);

            // ��֯��ѯsql���
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
            sql.append("and a.ztbz <> '1' "); // �޸ı����ǼǱ����
            System.out.println("��ѯ�����Ǽ���ϸ��Ϣsql��\n" + sql.toString());


            // ��ȡ���ݿ�����
            con = DBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();

            // �걨����
            Timestamp curDate = new Timestamp(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            bo.setTbrq(sdf.format(curDate));

            sdf = new SimpleDateFormat("yyyy��MM��dd��");
            bo.setTbrqShow(sdf.format(curDate));
            /**
             * �۽���������Ϣ
             */
            KJYWRXX kjywrxx = bo.getKjywrxx();
            // �۽���Ӣ������
            kjywrxx.setKjrmc_en(rs.getString("kjrmc_en"));
            // �۽��˲�������
            kjywrxx.setKjrcwfzr(rs.getString("kjrcwfzr"));
            // �۽�����ϵ��
            kjywrxx.setKjrlxr(rs.getString("kjrlxr"));
            // �۽�����ϵ�绰
            kjywrxx.setKjrlxdh(rs.getString("kjrlxdh"));
            // �۽��˴���
            kjywrxx.setKjrczhm(rs.getString("kjrczhm"));
            bo.setKjywrxx(kjywrxx);

            /**
             * �Ǿ�����ҵ��Ϣ
             */
            FJMQYXX fjmqyxx = new FJMQYXX();
            // �Ǿ�����ҵ��������
            fjmqyxx.setFjmmc_cn(rs.getString("fjmmc_cn"));
            // �Ǿ�����ҵӢ������
            fjmqyxx.setFjmmc_en(rs.getString("fjmmc_en"));
            // �Ǿ�����ҵ����
            fjmqyxx.setFjmgb(rs.getString("fjmgb"));
            // �Ǿ�����ҵ��������
            fjmqyxx.setFjmgbmc(rs.getString("gjdqmc"));
            // �Ǿ�����ҵ���һ����
            fjmqyxx.setFjmgjdq(rs.getString("fjmgjdq"));
            if("01".equals(fjmqyxx.getFjmgjdq())){
                fjmqyxx.setFjmgjdqmc("�۰�̨");
            }
            else if("02".equals(fjmqyxx.getFjmgjdq()))
            {
                fjmqyxx.setFjmgjdqmc("���");
            }
            // �Ǿ�����ҵ������������ַ�����ģ�
            fjmqyxx.setFjmdz_cn(rs.getString("fjmdz_cn"));
            // �Ǿ�����ҵ������������ַ��Ӣ�ģ�
            fjmqyxx.setFjmdz_en(rs.getString("fjmdz_en"));
            // �Ǿ�����ҵ���Ҳ�������
            fjmqyxx.setFjmcwfzr(rs.getString("fjmcwfzr"));
            // �Ǿ�����ҵ������ϵ��
            fjmqyxx.setFjmlxr(rs.getString("fjmlxr"));
            // �Ǿ�����ҵ������ϵ�绰
            fjmqyxx.setFjmlxdh(rs.getString("fjmlxdh"));
            // �Ǿ�����ҵ���Ҵ���
            fjmqyxx.setFjmczhm(rs.getString("fjmczhm"));
            bo.setFjmqyxx(fjmqyxx);

            /**
             * ��ͬ��Ϣ
             */
            BAHTXX htxx = new BAHTXX();
            // ��ͬ��Э������
            htxx.setHtmc(rs.getString("htmc"));
            // ��ͬ���
            htxx.setHtbh(rs.getString("htbh"));
            // ��ͬǩԼ����
            htxx.setQyrq(rs.getString("htqyrq"));
            // ��ͬ��Ч��
            htxx.setHtyxq(rs.getString("htyxq"));
            // ��ͬ���
            System.out.println("htje = " + rs.getString("htje"));
            htxx.setHtje(rs.getString("htje"));
            // ����
            htxx.setBz(rs.getString("bz"));
            // ��������
            htxx.setBzmc(rs.getString("bzmc")); 
            // ֧����Ŀ
            htxx.setZfxm(rs.getString("zfxm"));
            // �������
            htxx.setFkcs(rs.getString("fkcs"));
            // ������������
            htxx.setQtzlmc(rs.getString("qtzlmc"));
            bo.setHtxx(htxx);
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally
        {
            // �ر����ݿ����
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
                throw new ApplicationException("�ر����ݿ�������");
            }
        }

        return bo;
    }

    /**
     * ���ɱ����ǼǱ����
     *    ���ɹ���8λ��������� + 8Ϊ���� + 4λ��������������1������
     * @param conn Connection ���ݿ�����
     * @param jsjdm String ���������
     * @return String �������ɵ�20λ����
     * @throws BaseException
     */
    private String createBadjxh(Connection conn, String jsjdm) throws BaseException
    {
        // �����ǼǺ�
        StringBuffer badjxh = new StringBuffer();
        // ��ѯϵͳ��ǰʱ��sql
        StringBuffer sql = new StringBuffer("select to_char(sysdate,'yyyymmdd') xtsj from dual");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        badjxh.append(jsjdm);

        try
        {
            // ��ѯ��ǰ8Ϊϵͳʱ��
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(rs.getString("xtsj"));

            // ��ѯ��ǰ�û������ͬ����
            sql.delete(0, sql.length());
            sql.append("select count(*) + 1 from sbdb.sb_jl_kjqysds_htbadjb where jsjdm = '");
            sql.append(jsjdm);
            sql.append("' and to_char(cjrq, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(StringUtils.LPAD(String.valueOf(rs.getInt(1)), 4, "0"));
            System.out.println("�����ǼǱ���ţ�" + badjxh.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("���������Ǽ���Ŵ���");
        }
        finally
        {
            try
            {
                // �ر����ݿ����
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            }
            catch(Exception ex)
            {
                throw new ApplicationException("�ر����ݿ�������");
            }
        }

        return badjxh.toString();
    }


    /**
     * ͨ���Ǽǽӿڻ�ȡ�۽������������Ϣ
     * @param bo BadjbBO
     * @param ud UserData
     * @throws BaseException
     */
    private void getKjywrxxByDjInfo(BadjbBO bo, UserData ud) throws BaseException
    {
        // ���ݿ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        KJYWRXX kjywrxx = bo.getKjywrxx() == null ? new KJYWRXX(): bo.getKjywrxx();
        try
        {
            Map djMap = null;
            // ͨ���Ǽǽӿڻ�ȡ�Ǽ���Ϣ
            ServiceProxy proxy = new ServiceProxy();
            try {
                djMap = proxy.getDjInfo(bo.getJsjdm());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }

            SWDJJBSJ djxx = (SWDJJBSJ) djMap.get("JBSJ");

            /**
             * ����Ǽ���Ϣ
             */
            // �۽��˼��������
            kjywrxx.setKjrjsjdm(bo.getJsjdm());
            // �۽�����������
            kjywrxx.setKjrmc_cn(djxx.getNsrmc());
            // �۽�����˰ʶ���
            kjywrxx.setKjrnssbh(djxx.getSwdjzh());
            // �۽��˵�ַ
            kjywrxx.setKjrdz_cn(djxx.getJydz());
            // �۽����ʱ�
            kjywrxx.setKjryzbm(djxx.getJydzyb());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("���õǼǽӿ�ʧ��!");
        }

        try
        {
            // ��ѯsql���
            StringBuffer sql = new StringBuffer("select xm from djdb.dj_jl_qyry ");
            sql.append("where jsjdm = '").append(bo.getJsjdm()).append("' ");
            sql.append("and zwdm='04'");
            // ��ȡ���ݿ�����
            conn = DBResource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            if(rs.next()){
            // �۽������˲�������
           		kjywrxx.setKjrcwfzr(rs.getString(1));
          	}
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("��ѯ��ҵ��Ա��Ϣ����!");
        }
        finally
        {
            // �ر����ݿ����
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
                throw new ApplicationException("�ر����ݿ�������");
            }
        }
        
        bo.setKjywrxx(kjywrxx);
    }
}

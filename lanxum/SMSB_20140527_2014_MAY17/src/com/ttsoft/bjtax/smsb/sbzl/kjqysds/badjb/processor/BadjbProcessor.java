package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.processor;


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
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbBO;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.proxy.ServiceProxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import com.ttsoft.bjtax.smsb.model.kjqysds.FJMQYXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX;


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
            case CodeConstant.SMSB_QUERYACTION1:
                result = doQueryKjrInfo(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION2:
                result = doQueryKjrRecords(vo);
                break;
            case CodeConstant.SMSB_SAVEACTION:
                result = doSave(vo);
                break;
            case CodeConstant.SMSB_VIEWMX:
                result = doViewMX(vo);
                break;
            case CodeConstant.SMSB_SHENHE:
                result = doShenhe(vo);
                break;
            case CodeConstant.SMSB_DELETEACTION:
                result = doDelete(vo);
                break;
            default:
                throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    /**
     * ��ѯ�۽���������Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryKjrInfo(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doQueryKjrInfo ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();

        try
        {
            // �۽�����Ϣ
//            bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
        	this.getKjywrxxByDjInfo(bo, ud);
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        return bo;
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
            if(bo.getModifyFlag())
            {
            	// �޸ı����ǼǱ����������������ͨ��������
            	sql.append("and (ztbz <> '1' and ztbz<>'9')");
            }
            else{
            	// ���޸ı����ǼǱ����
            	sql.append("and ztbz = '0'");
            }

            // ��ȡ���ݿ�����
            con = SfDBResource.getConnection();
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
                int myts = ud.getMyxszds();
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
                if(bo.getModifyFlag())
                {
                	// �޸ı����ǼǱ����������������ͨ��������
                	sql.append("and (a.ztbz <> '1' and a.ztbz<>'9')");
                }
                else{
                	// ���޸ı����ǼǱ����
                	sql.append("and a.ztbz = '0'");
                }
//                sql.append("and a.ztbz = '0' "); // ����˱����Ǽ���Ϣ
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
                	
                	/**
                	 * ���ش�����
                	 */
                    Map recordMap = new HashMap();
                    // �����Ǽ���ţ���¼������
                    recordMap.put("badjxh", rs.getString("badjxh"));
                    // ��ͬ���
                    recordMap.put("htbh", rs.getString("htbh"));
                    // ��ͬ����
                    recordMap.put("htmc", rs.getString("htmc"));
                    // ��ͬǩԼ����
                    recordMap.put("htqyrq", rs.getString("htqyrq"));
                    // ��ͬ��Ч��
                    recordMap.put("htyxq", rs.getString("htyxq"));
                    // ��ͬ���
                    recordMap.put("htje", rs.getString("htje"));
                    // �����
                    recordMap.put("tbrq", rs.getString("tbrq"));
                    // ���״̬
                    recordMap.put("shzt", rs.getString("shzt"));

                    reList.add(recordMap);
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
                SfDBResource.freeConnection(con);
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
     * ���汸���ǼǱ���Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doSave(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doSave ==========");
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();
        // ���ݿ�����
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            // ��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            // sqlBuffer
            StringBuffer sql = new StringBuffer();
            // �����Ǽ����
            String badjxh = new String();
            
            // ������޸ĺ󱣴棬����Ҫ��ɾ���������
            if(bo.getModifyFlag())
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
            		// ͨ���Ǽǽӿڻ�ȡ�۽������˲�����Ϣ
//                    bo.setKjywrxx(this.getKjywrxxByDjInfo(bo.getJsjdm(), ud));
            		this.getKjywrxxByDjInfo(bo, ud);
                    
            		bo.setZtbz("3");
            		bo.setMessage("�ú�ͬ��Ż��ͬ�����Ѵ��ڣ���˶ԡ��޸ģ�");
            		
            		// �ر����ݿ����
            		rs.close();
            		pstmt.close();
            		conn.close();
            		
            		return bo;
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
                sql.append("to_date('").append((bo.getHtxx().getQyrq()).replaceAll("-", "")).append("', 'yyyymmdd'), "); // HTQYRQ
                sql.append("null, "); // HTZXQSRQ
                sql.append("null, "); // HTZXZZRQ
                sql.append("to_date('").append((bo.getHtxx().getHtyxq()).replaceAll("-", "")).append("', 'yyyymmdd'), "); // HTYXQ
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
                SfDBResource.freeConnection(conn);
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
            sql.append("c.fjmcwfzr, c.fjmlxr, c.fjmlxdh, c.fjmczhm, d.htbh, d.htmc, to_char(d.htqyrq, 'yyyy-mm-dd') htqyrq, ");
            sql.append("to_char(d.htyxq, 'yyyy-mm-dd') htyxq, d.htje, d.bz, f.bzmc, d.zfxm, d.fkcs, d.qtzlmc ");
            sql.append("from sbdb.sb_jl_kjqysds_htbadjb a, sbdb.sb_jl_kjqysds_kjywr b, sbdb.sb_jl_kjqysds_fjmqyxx c, ");
            sql.append("sbdb.sb_jl_kjqysds_bahtmx d, dmdb.gy_dm_gjdq e, dmdb.gy_dm_bz f ");
            sql.append("where a.badjxh = b.badjxh and a.badjxh = c.badjxh and a.badjxh = d.badjxh ");
            sql.append("and a.jsjdm = b.jsjdm and a.htbh = d.htbh and c.fjmgb = e.gjdqdm and d.bz = f.bzdm ");
            sql.append("and a.badjxh = '").append(bo.getBadjxh()).append("' ");
            sql.append("and a.jsjdm = '").append(bo.getJsjdm()).append("' ");
            if(bo.getModifyFlag())
            {
            	// �޸ı����ǼǱ����
            	sql.append("and (a.ztbz <> '1' and a.ztbz<>'9')");
            }
            else
            {
            	sql.append("and a.ztbz = '0' "); // ����˱����Ǽ���Ϣ
            }
            System.out.println("��ѯ�����Ǽ���ϸ��Ϣsql��\n" + sql.toString());


            // ��ȡ���ݿ�����
            con = SfDBResource.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();

            bo.setTbrq(rs.getString("tbrq"));
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
                SfDBResource.freeConnection(con);
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
     * ��˱����Ǽ���Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doShenhe(VOPackage vo) throws BaseException
    {
        System.out.println("========== badjProcessor_doShenhe ==========");
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
            // ��ȡ���ݿ�����
            con = SfDBResource.getConnection();

            // ״̬��ʶ
            String ztbz = bo.getZtbz();

            // ��֯��ѯsql���
            StringBuffer sql = new StringBuffer();
            try
            {
                /**
                 * ���±����ǼǼ�¼���״̬
                 */
                sql.append("update sbdb.sb_jl_kjqysds_htbadjb set ");
                sql.append("ztbz = '").append(ztbz).append("', ");

                if ("1".equals(ztbz)) {
                    // ���ͨ�������ɱ����ǼǱ���
                    String badjbbh = this.createBadjbbh(con, bo.getJsjdm());
                    bo.setBadjbbh(badjbbh);
                    sql.append("badjbbh = '").append(badjbbh).append("', ");
                }
                sql.append("lrry = '").append(ud.getYhid()).append("', ");
                sql.append("lrrq = sysdate ");
                sql.append("where badjxh = '").append(bo.getBadjxh()).append("'");

                System.out.println("��˱����ǼǱ�sql��\n" + sql.toString());

                pstmt = con.prepareStatement(sql.toString());
                pstmt.execute(sql.toString());
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���±����ǼǼ�¼���״̬ʧ�ܣ�");
            }

            try
            {
                /**
                 * ���ݱ����Ǽ���Ϣ���ɴ��۴�����Ϣ
                 *    1�����ͨ�������ɴ��۴�����Ϣ
                 *    2���жϵ�ǰ�û���Ӧ��˰Ŀ�Ƿ��Ѿ����ڴ��۴�����Ϣ��������ֱ�ӽ�����
                 *    3���粻���ڣ����յ�ǰ�û������Ǽ��еĹ��һ������Ϣ�Զ�Ӧ��˰��˰Ŀ������۴����϶���¼
                 *       ���У��۰�̨-300021 ԴȪ�۽���ҵ����˰���۰�̨�������-300022 ԴȪ�۽���ҵ����˰�������
                 */
                // ���ͨ��
                if ("1".equals(ztbz))
                {
                    System.out.println("==============���ͨ������ʼ���ɴ��۴�����Ϣ��================");
                    // ˰��˰Ŀ����
                    String szsmdm = new String();
                    if ("01".equals(bo.getFjmqyxx().getFjmgjdq())) {
                        szsmdm = "300021";
                    }
                    else if ("02".equals(bo.getFjmqyxx().getFjmgjdq())) {
                        szsmdm = "300022";
                    }
                    System.out.println("szsmdm = " + szsmdm);

                    // ͨ���Ǽǽӿڻ�ȡ�Ǽ���Ϣ
                    InterfaceDj dj = new InterfaceDj();
                    SWDJJBSJ djxx = dj.getJBSJ_New(bo.getJsjdm(), ud);
                    try
                    {
                        /**
                         * ����sfdb.sf_jl_wtdw����
                         */
                        sql.delete(0, sql.length());
                        sql.append("select count(*) from sfdb.sf_jl_wtdw where jsjdm = '").append(bo.getJsjdm()).append("'");
                        pstmt = con.prepareStatement(sql.toString());
                        rs = pstmt.executeQuery(sql.toString());

                        rs.next();
                        int zb_count = rs.getInt(1);
                        // sfdb.sf_jl_wtdwû���϶���¼�����һ����¼
                        if (zb_count == 0) {
                            sql.delete(0, sql.length());
                            sql.append("insert into sfdb.sf_jl_wtdw (jsjdm, lrr, lrrq, wtzsh, cjrq, swjgzzjgdm) values (");
                            sql.append("'").append(bo.getJsjdm()).append("', ");
                            sql.append("'").append(ud.getYhid()).append("', ");
                            sql.append("sysdate, null, sysdate, ");
                            sql.append("'").append(djxx.getSwjgzzjgdm()).append("'");
                            sql.append(")");
                        }
                        // �����϶���¼��������м�¼
                        else if (zb_count > 0) {
                            sql.delete(0, sql.length());
                            sql.append("update sfdb.sf_jl_wtdw set ");
                            sql.append("wtzsh = null, ");
                            sql.append("lrr = '").append(ud.getYhid()).append("', ");
                            sql.append("lrrq = sysdate ");
                            sql.append("where jsjdm = '").append(bo.getJsjdm()).append("'");
                        }
                        System.out.println("sfdb.sf_jl_wtdw����sql��\n" +
                                           sql.toString());
                        pstmt = con.prepareStatement(sql.toString());
                        pstmt.execute(sql.toString());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                        throw new ApplicationException("����sfdb.sf_jl_wtdw����ʧ�ܣ�");
                    }

                    // �ж��Ƿ��Ѵ��ڶ�Ӧ˰��˰Ŀ�Ĵ��۴����϶���Ϣ
                    sql.delete(0, sql.length());
                    sql.append("select count(*) from sfdb.sf_jl_wtdwmx where ");
                    sql.append("jsjdm = '").append(bo.getJsjdm()).append("' ");
                    sql.append("and szsmdm = '").append(szsmdm).append("'");

                    pstmt = con.prepareStatement(sql.toString());
                    rs = pstmt.executeQuery(sql.toString());

                    rs.next();

                    int count = rs.getInt(1);

                    // �����ڶ�Ӧ˰��˰Ŀ�Ĵ��۴����϶���¼
                    if (count == 0)
                    {
                        try
                        {
                            /**
                             * ����sfdb.sf_jl_wtdwmx����
                             */
                            sql.delete(0, sql.length());
                            sql.append("select count(*) from sfdb.sf_jl_wtdwmx where ");
                            sql.append("jsjdm = '").append(bo.getJsjdm()).append("' ");
                            sql.append("and szsmdm = '").append(szsmdm).append("'");

                            pstmt = con.prepareStatement(sql.toString());
                            rs = pstmt.executeQuery(sql.toString());

                            rs.next();
                            int mx_count = rs.getInt(1);
                            // sfdb.sf_jl_wtdwmxû���϶���¼�����һ����¼����������
                            if (mx_count == 0) {
                                sql.delete(0, sql.length());
                                sql.append("select sfdb.SEQ_JL_SFDB.nextval from dual");
                                pstmt = con.prepareStatement(sql.toString());
                                rs = pstmt.executeQuery(sql.toString());

                                rs.next();
                                int mxid = rs.getInt(1);

                                sql.delete(0, sql.length());
                                sql.append("insert into sfdb.sf_jl_wtdwmx (jsjdm, wtxmdm, szsmdm, fhsxfbl, rdrq, mxid, cjrq, lrrq, swjgzzjgdm) values (");
                                sql.append("'").append(bo.getJsjdm()).append("', ");
                                sql.append("'11', "); // ����
                                sql.append("'").append(szsmdm).append("', ");
                                sql.append("2, to_date(to_char(sysdate, 'yyyymmdd'), 'yyyymmdd'), ");
                                sql.append(mxid).append(", "); // mxid
                                sql.append("sysdate, sysdate, ");
                                sql.append("'").append(djxx.getSwjgzzjgdm()).append("'");
                                sql.append(")");
                            }

                            System.out.println("sfdb.sf_jl_wtdwmx����sql��\n" + sql.toString());
                            pstmt = con.prepareStatement(sql.toString());
                            pstmt.execute(sql.toString());
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                            throw new ApplicationException("����sfdb.sf_jl_wtdwmx����ʧ�ܣ�");
                        }
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException("���ݱ����Ǽ���Ϣ���ɴ��۴�����Ϣʧ�ܣ�");
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
                if(pstmt != null)
                {
                    pstmt.close();
                }
                SfDBResource.freeConnection(con);
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
            badjxh.append(SBStringUtils.LPAD(String.valueOf(rs.getInt(1)), 4, "0"));
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
     * ���ɱ����ǼǱ���
     *    ����������������Ϣ��ȷ��ʱ���ɣ���ӡʱʹ��
     *    ���ɹ���8λ��������� + [ + 4λ��� + ] + 4λ��������������1������
     * @param conn Connection ���ݿ�����
     * @param jsjdm String ���������
     * @return String �������ɵ�18λ����
     * @throws BaseException
     */
    private String createBadjbbh(Connection conn, String jsjdm) throws BaseException
    {
        // �����ǼǺ�
        StringBuffer badjbbh = new StringBuffer();
        // ��ѯϵͳ��ǰʱ��sql
        StringBuffer sql = new StringBuffer("select to_char(sysdate,'yyyy') xtsj from dual");

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        badjbbh.append(jsjdm).append("[");

        try
        {
            // ��ѯ��ǰ8Ϊϵͳʱ��
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            String dqnd = rs.getString("xtsj");
            badjbbh.append(dqnd).append("]");

            // ��ѯ��ǰ�û����걸���Ǽ�����
            sql.delete(0, sql.length());
            sql.append("select count(*) + 1 from sbdb.sb_jl_kjqysds_htbadjb where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and ztbz = '1' ");
            sql.append("and (badjbbh is not null or badjbbh <> '') ");
            sql.append("and to_char(cjrq, 'yyyy') = '").append(dqnd).append("'");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjbbh.append(SBStringUtils.LPAD(String.valueOf(rs.getInt(1)), 4, "0"));
            System.out.println("�����ǼǱ���ţ�" + badjbbh.toString());
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("���������ǼǱ���Ŵ���");
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

        return badjbbh.toString();
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
            conn = SfDBResource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            // �۽������˲�������
            kjywrxx.setKjrcwfzr(rs.getString(1));
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
    
    /**
     * ɾ��ָ���ı����ǼǱ�
     * @param bo BadjbBO
     * @param ud UserData
     * @throws BaseException
     */
    private Object doDelete(VOPackage vo) throws BaseException
    {
        // ���ݿ����
        Connection conn = null;
        PreparedStatement pstmt = null;
        // ��ȡUserData
        UserData ud = vo.getUserData();
        // ��ȡBadjbBO
        BadjbBO bo = (BadjbBO) vo.getData();

        try
        {
            // ��ѯsql���
            StringBuffer sql = new StringBuffer("update sbdb.sb_jl_kjqysds_htbadjb set ztbz='9' where badjxh='");
            sql.append(bo.getBadjxh()).append("' and ztbz in ('0', '2')");
            // ��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            pstmt = conn.prepareStatement(sql.toString());
            pstmt.executeUpdate(sql.toString());
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException("ɾ�������ǼǱ���Ϣ����!");
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
        return bo;
    }
}

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰�����</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ���ſƼ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.processor;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.kjqysds.*;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjqysdsbgb.web.KjqysdsbgbBO;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.SBStringUtils;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �۽���ҵ����˰�����
 * </p>
 * <p>
 * Description:�۽���ҵ����˰�����
 * </p>
 *
 * @author wang xiaomin
 * @version 1.1
 */

public class KjqysdsbgbProcessor implements Processor {


	/**
	 * ʵ��Processor�ӿ�
	 *
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

	public Object process(VOPackage vo) throws BaseException {
		System.out.println("------------kjqysdsbgbProcessor-----------------");
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_SHOWACTION:
			result = doShow(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION:
			result = doQuery(vo);
			break;
		case CodeConstant.SMSB_SAVEACTION:
			result = doSave(vo);
			break;
		case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;
		case CodeConstant.SMSB_QUERYACTION1:
			result=doQuery1(vo);
			break;
		// case CodeConstant.SMSB_CHECKACTION:
		// result = doCheck(vo);
		// break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

	/**
	 * doShow��ʼ������ҳ����ϢҪ��
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doShow(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���KjqysdsbgbBO����
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		// �õ���ǰʱ���������
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// ˰��������ʼ����
		bo.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// ˰��������������
		bo.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));
		// ˰���걨����
		bo.setSbrq(SfDateUtil.getDate());
		return bo;
	}
	/**
	 * doQuery1�۽���ҵ����˰������ѯ
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doQuery1(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���KjqysdsbgbBO����
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql= new StringBuffer();
		PreparedStatement psCount = null;
		ResultSet rsCount = null;
		List list = new ArrayList();
		
		if(bo.getDqzt().equals("01")){//�ѱ���δ��д�����
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje, rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b where a.BADJXH=b.BADJXH and b.jsjdm='");
			sql.append(bo.getJsjdm());
			sql.append("' and b.badjxh not in(select badjxh from SBDB.SB_JL_KJQYSDS_KJBGB where jsjdm='");
			sql.append(bo.getJsjdm()).append("') and b.ztbz='1'");
		}
		else if(bo.getDqzt().equals("02")){//����д�����δ���ܽɿ���
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje,c.bgbxh,rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b,SBDB.SB_JL_KJQYSDS_KJBGB c where a.BADJXH=b.BADJXH  and sphm is null and b.jsjdm='");
			sql.append(bo.getJsjdm());
			sql.append("' and b.badjxh=c.badjxh and c.scbs='1' and b.ztbz='1'");
		}
		else if(bo.getDqzt().equals("03")){//�ѿ��ɿ���
			sql.append("select a.badjxh,a.htmc,a.htbh,a.htje,c.bgbxh,rownum num from SBDB.SB_JL_KJQYSDS_BAHTMX a,SBDB.SB_JL_KJQYSDS_HTBADJB b,SBDB.SB_JL_KJQYSDS_KJBGB c where a.BADJXH=b.BADJXH and b.ztbz='1' and b.badjxh=c.badjxh and c.sphm is not null and c.scbs='1' and b.jsjdm='");
			sql.append(bo.getJsjdm()).append("'");
		}
		try{
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			//��ѯ������
			StringBuffer sqlCount=new StringBuffer();
			sqlCount.append("select count(*) from (");
			sqlCount.append(sql.toString()).append(")");
			psCount=conn.prepareCall(sqlCount.toString());
			rsCount=psCount.executeQuery();
			int zts=0;
			if(rsCount.next()){
			zts = rsCount.getInt(1);
			}
            bo.setZts(String.valueOf(zts));
            sqlCount.delete(0,sqlCount.length());
            rsCount.close();
            psCount.close();
            
          //�����������Ϊ0���������ҳ������ѯ��ϸ
            if(zts > 0){
			//������ҳ��
			int zys = 0;
            //ÿҳ����
            int myts = Integer.parseInt(CodeConstant.PAGE_MYTS);
			if (zts % myts == 0) {
				zys = zts / myts;
			}
			else if (zts % myts > 0) {
				zys = zts / myts + 1;
			}
			bo.setZys(String.valueOf(zys));
			System.out.println("��ҳ��::" + bo.getZys());

            //��ȡ��ǰҳ�����Ϊ��ѯ��������ǰҳ��Ϊ��һҳ
            if(bo.getDqy() != null)
            {
                System.out.println("bo_dqy = " + bo.getDqy());
            }
            System.out.println("bo_dqy ============ " + bo.getDqy());
            int dqy = (bo.getDqy()==null||bo.getDqy().equals("")||bo.getDqy().equals("0") ? 1 : Integer.parseInt(bo.getDqy()));
            bo.setDqy(String.valueOf(dqy));
            System.out.println("dqy = " + dqy + "\nmyts = " + myts);
            StringBuffer sql1=new StringBuffer();
			sql1.append("select * from (");
			sql1.append(sql.toString()).append(") where num >= ");
            sql1.append((dqy - 1)*myts + 1);
            sql1.append(" and num <= ");
            sql1.append(dqy*myts);
            System.out.println("sql1------"+sql1.toString());

			ps=conn.prepareCall(sql1.toString());
			rs=ps.executeQuery();
			while(rs.next()){
				Map map= new HashMap();
				map.put("badjxh", rs.getString("badjxh"));
				if(!bo.getDqzt().equals("01")){
					map.put("bgbxh",rs.getString("bgbxh"));
				}
				BAHTXX bahtxx = new BAHTXX();
				bahtxx.setHtmc(rs.getString("htmc"));
				bahtxx.setHtbh(rs.getString("htbh"));
				bahtxx.setHtje(rs.getString("htje"));
				map.put("bahtxx", bahtxx);
				list.add(map);
			}
			bo.setQysdsbgbList(list);
			rs.close();
			ps.close();
		}
            else{
            	//��װ����ֵ
                bo.setZys("0");
                bo.setDqy("0");
                bo.setQysdsbgbList(list);
    		}
		}
		
		 catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		return bo;
	}

	/**
	 * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 *
	 */

	private Object doQuery(VOPackage vo) throws BaseException {

		// �õ�Action���ݹ���KjqysdsbgbBO����
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		KJYWRXX kjywrxx=new KJYWRXX();
		StringBuffer sql=new StringBuffer();
		
		
		// �õ���ǰʱ���������
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		Timestamp curTime = new Timestamp(System.currentTimeMillis());
		Map getsbjd = this.quarterSkssrq(curTime);
		Timestamp skssksrq = (Timestamp) getsbjd.get(Skssrq.SKSSKSRQ);
		Timestamp skssjsrq = (Timestamp) getsbjd.get(Skssrq.SKSSJSRQ);
		// ˰��������ʼ����
		bo.setSkssksrq(SfTimeUtil.getDateFromDateTime(skssksrq));
		// ˰��������������
		bo.setSkssjsrq(SfTimeUtil.getDateFromDateTime(skssjsrq));

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			/**
			 *�жϸü�¼�Ƿ���ȷ���������ɴ��۴�����Ϣ 
			 */
			sql.append("select count(*) from sbdb.sb_jl_kjqysds_htbadjb a where a.jsjdm='");
			sql.append(bo.getJsjdm()).append("' and a.badjxh='");
			sql.append(bo.getBadjxh()).append("' and a.ztbz='1'");
			ps = conn.prepareStatement(sql.toString());
            rs = ps.executeQuery(sql.toString());

            rs.next();
            int count1 = rs.getInt(1);
            sql.delete(0, sql.length());
            rs.close();
            ps.close();
            if(count1==0){
            	bo.setFlag(false);
            }
            else{
				sql.append("select a.fjmgjdq from sbdb.sb_jl_kjqysds_fjmqyxx a where a.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				ps = conn.prepareStatement(sql.toString());
	            rs = ps.executeQuery(sql.toString());
				rs.next();
				String fjmgjdg=rs.getString("fjmgjdq");
				// ˰��˰Ŀ����
	            String szsmdm = new String();
	            if ("01".equals(fjmgjdg)) {
	                szsmdm = "300021";
	            }
	            else if ("02".equals(fjmgjdg)) {
	                szsmdm = "300022";
	            }
	         // �ж��Ƿ��Ѵ��ڶ�Ӧ˰��˰Ŀ�Ĵ��۴����϶���Ϣ
	            sql.delete(0, sql.length());
	            sql.append("select count(*) from sfdb.sf_jl_wtdwmx where ");
	            sql.append("jsjdm = '").append(bo.getJsjdm()).append("' ");
	            sql.append("and szsmdm = '").append(szsmdm).append("'");
	
	            ps = conn.prepareStatement(sql.toString());
	            rs = ps.executeQuery(sql.toString());
	
	            rs.next();
	            sql.delete(0, sql.length());
	            int count = rs.getInt(1);
	            rs.close();
	            ps.close();
	            
	
	            // �����ڶ�Ӧ˰��˰Ŀ�Ĵ��۴����϶���¼
	            if (count == 0)
	            {
	            	bo.setFlag(false);
	            }
	            else{
					/**
					 * �ӵǼǽӿ�ȡ�۽���������Ϣ
					 */
					SWDJJBSJ djxx = null;
					UserData ud = (UserData) vo.getUserData();
					try {
		
						djxx = InterfaceDj.getJBSJ_New(
								bo.getJsjdm(), ud);
		
		
					} catch (Exception ex1) {
						throw ExceptionUtil.getBaseException(ex1);
					}
					kjywrxx.setKjrnssbh(djxx.getSwdjzh());//��˰��ʶ���
					kjywrxx.setKjrdz_cn(djxx.getJydz());//���ĵ�ַ
					kjywrxx.setKjryzbm(djxx.getJydzyb());//��������
					kjywrxx.setKjrmc_cn(djxx.getNsrmc());//��������
					kjywrxx.setKjrjjlxdm(djxx.getDjzclxdm());//�������ʹ���
					kjywrxx.setKjrjjlxmc(djxx.getDjzclxmc());//������������
					kjywrxx.setKjrjjxlfldm(djxx.getGjbzhydm());//������ҵ�������
					kjywrxx.setKjrjjxlflmc(djxx.getGjbzhymc());//������ҵ��������
					/**
					 * �ӿ۽������˱���ȡ�۽�������������Ϣ
					 */
					sql.append("select * from SBDB.SB_JL_KJQYSDS_KJYWR t where t.badjxh='");
					sql.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					while(rs.next()){
						kjywrxx.setKjrmc_en(rs.getString("kjrmc_en"));//Ӣ������
						kjywrxx.setKjrdz_en(rs.getString("kjrdz_en"));//Ӣ�ĵ�ַ
						kjywrxx.setKjrlxr(rs.getString("kjrlxr"));//��ϵ��
						kjywrxx.setKjrlxdh(rs.getString("kjrlxdh"));//��ϵ�绰
					}
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
					bo.setKjywrxx(kjywrxx);
					/**
					 * �ӿ۽���ҵ����˰�Ǿ�����ҵ��Ϣ����ȡ�Ǿ�����Ϣ
					 */
					sql.append("select t.fjmjmgnssbh,t.fjmgjdq,t.fjmgb,a.gjdqmc,t.fjmmc_cn,t.fjmmc_en,t.fjmjmgmc_cn,t.fjmjmgmc_en,t.fjmdz_cn,t.fjmdz_en from SBDB.SB_JL_KJQYSDS_FJMQYXX t,dmdb.gy_dm_gjdq a where t.badjxh='");
					sql.append(bo.getBadjxh()).append("' and a.gjdqdm=t.fjmgb");
					System.out.println("fjmqyxx-----sql----"+sql.toString());
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					FJMQYXX fjmqyxx = new FJMQYXX();
					while(rs.next()){
						fjmqyxx.setFjmjmgnssbh(rs.getString("fjmjmgnssbh"));//����������˰ʶ���
						fjmqyxx.setFjmgjdq(rs.getString("fjmgjdq"));//���һ����
						String gb=rs.getString("fjmgb")+"|"+rs.getString("gjdqmc");
						fjmqyxx.setFjmgb(gb);//����������������Ƽ�����
						fjmqyxx.setFjmmc_cn(rs.getString("fjmmc_cn"));//���й����ڵ���������
						fjmqyxx.setFjmmc_en(rs.getString("fjmmc_en"));//���й����ڵ�Ӣ������
						fjmqyxx.setFjmjmgmc_cn(rs.getString("fjmjmgmc_cn"));//����������������
						fjmqyxx.setFjmjmgmc_en(rs.getString("fjmjmgmc_en"));//��������Ӣ������
						fjmqyxx.setFjmdz_cn(rs.getString("fjmdz_cn"));//�����������ĵ�ַ
						fjmqyxx.setFjmdz_en(rs.getString("fjmdz_en"));//��������Ӣ�ĵ�ַ	
					}
					bo.setFjmqyxx(fjmqyxx);
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
		
					
					/**
					 * �ӿ۽���ҵ����˰������ͬ��ϸ����ȡ�ú�ͬ��Ϣ
					 */
					sql.append("select t.htmc,t.htbh,to_char(t.htzxqsrq,'yyyymmdd') htzxqsrq,to_char(t.htzxzzrq,'yyyymmdd') htzxzzrq,t.htje from SBDB.SB_JL_KJQYSDS_BAHTMX t where t.badjxh='");
					sql.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql.toString());
					rs=ps.executeQuery();
					BAHTXX bahtxx = new BAHTXX();
					while(rs.next()){
						bahtxx.setHtmc(rs.getString("htmc"));
						bahtxx.setHtbh(rs.getString("htbh"));
						bahtxx.setHtzxqsrq(rs.getString("htzxqsrq"));
						bahtxx.setHtzxzzrq(rs.getString("htzxzzrq"));
						bahtxx.setHtje(rs.getString("htje"));
					}
					bo.setBahtxx(bahtxx);
					sql.delete(0,sql.length());
					rs.close();
					ps.close();
					
					/**
					 * �ӿ۽���ҵ����˰�۽ɱ������ϸ���ݱ���ȡ��������ϸ��Ϣ
					 */
					System.out.println("bo.getBgbxh----"+bo.getBgbxh());
					try{
						if(!"".equals(bo.getBgbxh())){
							sql.append("select * from SBDB.SB_JL_KJQYSDS_KJBGMX t where t.badjxh='");
							sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
							sql.append(bo.getBgbxh()).append("'");
							ps=conn.prepareStatement(sql.toString());
							rs=ps.executeQuery();
							List list=new ArrayList();
							while(rs.next()){
								Map map=new HashMap();
								map.put("hc",rs.getString("hc"));
								map.put("sj", rs.getString("yz"));
								list.add(map);
							}
							bo.setQysdsbgbList(list);
							sql.delete(0,sql.length());
							rs.close();
							ps.close();
							
							/**
							 * �ӿ۽���ҵ����˰�۽ɱ������ȡ���걨�������ʹ���,�걨����ȡ������,˰����������
							 */
							sql.append("select t.SBSDLXDM,to_char(t.SBSDQDRQ,'yyyymmdd') sbsdqdrq,to_char(t.skssksrq,'yyyymmdd') skssksrq,to_char(t.skssjsrq,'yyyymmdd') skssjsrq from SBDB.SB_JL_KJQYSDS_KJBGB t where t.badjxh='");
							sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
							sql.append(bo.getBgbxh()).append("'");
							ps=conn.prepareStatement(sql.toString());
							rs=ps.executeQuery();
							while(rs.next()){
								bo.setSbsdlxdm(rs.getString("sbsdlxdm"));
								bo.setSbsdqdrq(rs.getString("sbsdqdrq"));
								bo.setSkssksrq(rs.getString("skssksrq"));
								bo.setSkssjsrq(rs.getString("skssjsrq"));
							}
						}
						rs.close();
						ps.close();
						sql.delete(0,sql.length());
					} catch (Exception ex1) {
						throw ExceptionUtil.getBaseException(ex1);
					}
					/**
				     * ��ȡ�۽���ҵ����˰�걨��Ϣ
				     */
					List sbsdlxList = new ArrayList();
			        sql.append("select sbsdlxdm,sbsdlxdm||'|'||sbsdlxmc sbsdlxmc from DMDB.SB_DM_FJMSBSDLXDM where zxbs='0' order by xssx");
		
			        try {
			            ps = conn.prepareStatement(sql.toString());
			            rs = ps.executeQuery(sql.toString());
			            while (rs.next()) {
			            	SBSDLX sbsdlx = new SBSDLX();
			                //�걨�������ʹ���
			                sbsdlx.setSbsdlxdm(rs.getString("sbsdlxdm"));
			                //�걨������������
			                sbsdlx.setSbsdlxmc(rs.getString("sbsdlxmc"));
		
			                sbsdlxList.add(sbsdlx);
			            }
		
			            //�ر����ݿ����
			            rs.close();
			            ps.close();
			        }
			        catch (Exception ex) {
			            ex.printStackTrace();
			            throw new ApplicationException("��ȡ�۽���ҵ����˰�걨��Ϣ����!");
			        }
			        bo.setSbsdlxList(sbsdlxList);
					bo.setFlag(true);
	            }
            }
		} catch (Exception e) {
			// �׳��쳣
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ��ѯ�ɹ�����bo
		return bo;
	}

	/**
	 * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doSave(VOPackage vo) throws BaseException {
		// �õ�Action���ݹ���KjqysdsbgbBO����

		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		try {

			// �������ݿ�����
			conn = SfDBResource.getConnection();
			/**
			 * ����۽���������Ϣ
			 */
			try{
				sql.append("update SBDB.SB_JL_KJQYSDS_KJYWR set KJRDZ_EN='");
				sql.append(bo.getKjywrxx().getKjrdz_en()).append("' where badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("�۽���������Ϣ----"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				int flag=ps.executeUpdate(sql.toString());
				ps.close();
				sql.delete(0, sql.length());
			}
	        catch(Exception e)
	        {
	            e.printStackTrace();
	            throw new ApplicationException("����۽������˱�-�۽���������Ϣʧ��!");
	        }

			
			/**
			 * ����Ǿ�����Ϣ
			 */
			sql.append("update SBDB.SB_JL_KJQYSDS_FJMQYXX set FJMJMGNSSBH='");
			sql.append(bo.getFjmqyxx().getFjmjmgnssbh());
			sql.append("',FJMJMGMC_CN='");
			sql.append(bo.getFjmqyxx().getFjmjmgmc_cn());
			sql.append("',FJMJMGMC_EN='");
			sql.append(bo.getFjmqyxx().getFjmjmgmc_en());
			sql.append("' where badjxh='");
			sql.append(bo.getBadjxh());
			sql.append("'");
			System.out.println("����Ǿ�����Ϣ----"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeUpdate(sql.toString());
			ps.close();
			sql.delete(0, sql.length());
			/**
			 * �����ͬ��Ϣ
			 */
			sql.append("update SBDB.SB_JL_KJQYSDS_BAHTMX set HTZXQSRQ=to_date('");
			sql.append(bo.getBahtxx().getHtzxqsrq());
			sql.append("','yyyy-mm-dd'),HTZXZZRQ=to_date('");
			sql.append(bo.getBahtxx().getHtzxzzrq());
			sql.append("','yyyy-mm-dd') where badjxh='");
			sql.append(bo.getBadjxh());
			sql.append("'");
			System.out.println("��ͬ----sql----"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeUpdate(sql.toString());
			ps.close();
			sql.delete(0, sql.length());
			
			/**
			 * ����۽ɱ�������
			 */
			StringBuffer sql1=new StringBuffer();
			String bgbxh="";
			try{
				System.out.println("bo.getBgbxh----"+bo.getBgbxh());
				if("".equals(bo.getBgbxh())||bo.getBgbxh()==null ||"null".equals(bo.getBgbxh())){
					System.out.println("1111111111");
				}
				if(!("".equals(bo.getBgbxh()) || bo.getBgbxh()==null || "null".equals(bo.getBgbxh()))){
	
					sql1.append("update SBDB.SB_JL_KJQYSDS_KJBGB set sbsdlxdm='");
					sql1.append(bo.getSbsdlxdm());
					sql1.append("',sbsdqdrq=to_date('");
					sql1.append(bo.getSbsdqdrq());
					sql1.append("','yyyy-mm-dd') where badjxh='");
					sql1.append(bo.getBadjxh());
					sql1.append("' and bgbxh='");
					sql1.append(bo.getBgbxh()).append("'");
				}
				else{
					bgbxh=createBgbxh(conn);
					sql1.append("insert into SBDB.SB_JL_KJQYSDS_KJBGB(bgbxh,badjxh,jsjdm,htbh,sbsdlxdm,sbsdqdrq,lrry,lrrq,cjry,cjrq,skssksrq,skssjsrq) values('");
					sql1.append(bgbxh).append("','");
					sql1.append(bo.getBadjxh()).append("','");
					sql1.append(bo.getJsjdm()).append("','");
					sql1.append(bo.getBahtxx().getHtbh()).append("','");
					sql1.append(bo.getSbsdlxdm()).append("',to_date('");
					sql1.append(bo.getSbsdqdrq()).append("','yyyy-mm-dd'),'");
					sql1.append(bo.getLrr()).append("',sysdate,'");
					sql1.append(bo.getLrr()).append("',sysdate,to_date('");
					sql1.append(bo.getSkssksrq()).append("','yyyy-mm-dd'),to_date('");
					sql1.append(bo.getSkssjsrq()).append("','yyyy-mm-dd'))");
				}
				System.out.println("����----sql---"+sql1.toString());
				ps=conn.prepareStatement(sql1.toString());
				ps.execute(sql1.toString());
				sql1.delete(0,sql1.length());
				ps.close();
			
			}
			catch(Exception e)
			{
			    e.printStackTrace();
			    throw new ApplicationException("����۽ɱ���������Ϣʧ��!");
			}
			
			/**
			 * ����۽ɱ������ϸ��
			 */
			try{
				if(!("".equals(bo.getBgbxh()) || bo.getBgbxh()==null || "null".equals(bo.getBgbxh()))){
					bgbxh=bo.getBgbxh();
					sql1.append("delete SBDB.SB_JL_KJQYSDS_KJBGMX where bgbxh='");
					sql1.append(bgbxh).append("' and badjxh='");
					sql1.append(bo.getBadjxh()).append("'");
					ps=conn.prepareStatement(sql1.toString());
					ps.execute(sql1.toString());
					sql1.delete(0,sql1.length());
					ps.close();
				}
				//������ϸ������
				sql.append("insert into SBDB.SB_JL_KJQYSDS_KJBGMX(bgbxh,badjxh,jsjdm,htbh,hc,yz,lrry,lrrq,cjry,cjrq) values('");
				sql.append(bgbxh).append("','");
				sql.append(bo.getBadjxh()).append("','");
				sql.append(bo.getJsjdm()).append("','");
				sql.append(bo.getBahtxx().getHtbh()).append("',?,?,'");
				sql.append(bo.getLrr()).append("',sysdate,'");
				sql.append(bo.getLrr()).append("',sysdate)");
				System.out.println("��ϸ---sql----"+sql.toString());
				List list = bo.getQysdsbgbList();
				int sp = 0; // ��ʼִ��point
	            int ep = 0; // ����ִ��point
	            int maxlength = list.size();
	            ps=conn.prepareStatement(sql.toString());
	            while (ep < maxlength) {
	                // ÿ��ѭ���ύ50��
	                if ((sp + 50) < maxlength) {
	                    ep = sp + 50;
	                } else {
	                    ep = maxlength;
	                }
	                for (int j = sp; j < ep; j++) {
	                	HashMap map = (HashMap) list.get(j);
	    				String hc = (String) map.get("hc");
	    				String sj = (String) map.get("sj");
	    				System.out.println("hc1----------"+hc);
	    				if(!"".equals(sj)){
		                    ps.setString(1, hc);
		                    System.out.println("hc2----------"+hc);
		                    ps.setString(2, sj);
		                    ps.addBatch();
	    				}
	                    
	                }
	                ps.executeBatch();
	                sp = ep;             
	            }
	            sql.delete(0,sql.length());
	            ps.close();
	            bo.setBgbxh(bgbxh);
	            System.out.println("----end------"+bo.getBgbxh());
			}
		catch(Exception e)
		{
		    e.printStackTrace();
		    throw new ApplicationException("����۽ɱ������ϸ����Ϣʧ��!");
		}
		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ����ɹ�����bo
		return bo;
	}

	/**
	 * ���㼾�����͵�˰����������
	 *
	 * @param curDate
	 *            ����
	 * @return Map ʹ��Key �� Skssrq.SKSSKSRQ �õ� ˰��������ʼ����Timestamp ʹ��Key ��
	 *         Skssrq.SKSSJSRQ �õ� ˰��������������Timestamp 
	 */
	public Map quarterSkssrq(Date curDate) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(curDate);
		int month = calendar.get(calendar.MONTH);
		int year = calendar.get(calendar.YEAR);

		int jd = month / 3;
		if (jd == 0) {
			year--;
			jd = 4;
		}
		String nd = new Integer(year).toString();
		Timestamp skssksrqDate = new Timestamp(
				new GregorianCalendar(year, 0, 1).getTime().getTime());
		Timestamp skssjsrqDate = new Timestamp(new GregorianCalendar(year,
				jd * 3 + 2, new GregorianCalendar(year, jd * 3 + 2,
						1).getActualMaximum(calendar.DAY_OF_MONTH)).getTime()
				.getTime());
		Map retMap = new HashMap();
		retMap.put(Skssrq.SKSSKSRQ, skssksrqDate);
		retMap.put(Skssrq.SKSSJSRQ, skssjsrqDate);
		retMap.put(Skssrq.SKSSRQ_ND, nd);
		return retMap;
	}

	/**
	 * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
	 *
	 * @param vo
	 *            ҵ�����
	 * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
	 * @throws BaseException
	 *             ��������������׳��쳣��Ϣ
	 */

	private Object doDelete(VOPackage vo) throws BaseException {

		// �õ�Action���ݹ���KjqysdsbgbBO����
		KjqysdsbgbBO bo = (KjqysdsbgbBO) vo.getData();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();

		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			//��ע�����ɾ��
			sql.append("update SBDB.SB_JL_KJQYSDS_KJBGB set scbs='2' where badjxh='");
			sql.append(bo.getBadjxh()).append("' and bgbxh='");
			sql.append(bo.getBgbxh()).append("'");
			System.out.println("ɾ��----sql---"+sql.toString());
			ps=conn.prepareStatement(sql.toString());
			ps.executeQuery(sql.toString());

		} catch (Exception ex) {
			// �׳��쳣
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		// ɾ���ɹ�����bo
		return bo;
	}
	
    /**
     * ���ɱ�������
     *    ���ɹ���8Ϊ���� + 6λ��������������1������
     * @param conn Connection ���ݿ�����
     * @return String �������ɵ�14λ����
     * @throws BaseException
     */
    private String createBgbxh(Connection conn) throws BaseException
    {
        // �����ǼǺ�
        StringBuffer badjxh = new StringBuffer();
        // ��ѯϵͳ��ǰʱ��sql
        StringBuffer sql = new StringBuffer("select to_char(sysdate,'yyyymmdd') xtsj from dual");

        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try
        {
            // ��ѯ��ǰ8Ϊϵͳʱ��
            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(rs.getString("xtsj"));

            // ��ѯ��ǰ�û������ͬ����
            sql.delete(0, sql.length());
            sql.append("select count(*) + 1 from SBDB.SB_JL_KJQYSDS_KJBGB where ");
            sql.append("to_char(cjrq, 'yyyymmdd') = to_char(sysdate, 'yyyymmdd')");

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            badjxh.append(SBStringUtils.LPAD(String.valueOf(rs.getInt(1)), 6, "0"));
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
}

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣���һ����Ϣ�Ƽ����޹�˾����Ȩ����. </p>
 * <p>Company: ��һ����Ϣ�Ƽ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.Constant;
import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.model.Cftsyhd;
import com.ttsoft.bjtax.sfgl.common.model.Grtszygsde;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.model.orobj.Dqdedlmx1;
import com.ttsoft.bjtax.sfgl.model.orobj.Tszslmx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Szsm;
import com.ttsoft.bjtax.shenbao.model.domain.Zqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.model.client.SbjkmxDis;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.kjssjks.web.KjssjksBO;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.util.Skssrq;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbGzsxActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ   �۽���ҵ����˰����˰�սɿ���</p>
 * <p>Description:  �۽���ҵ����˰����˰�սɿ���</p>
 * @author wangxm
 * @version 1.1
 */

public class KjssjksProcessor
    implements Processor
{
    private Map CDF = null;

    public KjssjksProcessor ()
    {
    }

    /**
     * ʵ��Processor�ӿ�
     * @param vo ҵ�����
     * @return Object VOPackage������
     * @throws BaseException ҵ���쳣
     */

    public Object process(VOPackage vo) throws BaseException {
		System.out.println("------------kjqysdsbgbProcessor-----------------");
		Object result = null;
		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case CodeConstant.SMSB_QUERYACTION:
        	result = doQuery(vo);
        	break;

        case CodeConstant.SMSB_SAVEACTION:
        	result = doSave(vo);
            break;
        case CodeConstant.SMSB_ZHSB_INITLIST:
        	result = getInitList(vo);
            break;
        case CodeConstant.SMSB_ZHSB_GZSX:
        	result = this.getGzsxInfo(vo);
            break;
		default:
			throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
		}

		return result;
	}

    private Object doQuery (VOPackage vo)
        throws BaseException
    {
    	// �õ�Action���ݹ���KjqysdsbgbBO����
		KjssjksBO bo = (KjssjksBO) vo.getData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql=new StringBuffer();
		
		
		try {
			// �������ݿ�����
			conn = SfDBResource.getConnection();
			/**
			 * ��ѯ�ñ�����Ƿ������ɽɿ���
			 */
			sql.append("select t.sphm,a.sbbh from sbdb.sb_jl_kjqysds_kjbgb t,sbdb.sb_jl_sbjkzb a where t.badjxh='");
			sql.append(bo.getBadjxh()).append("' and t.bgbxh='");
			sql.append(bo.getBgbxh()).append("' and a.jkpzh=t.sphm");
			ps=conn.prepareStatement(sql.toString());
			rs=ps.executeQuery();
			String sbbh="";
			while(rs.next()){
				sbbh=rs.getString("sbbh");
				bo.setSbbh(sbbh);
			}
			sql.delete(0,sql.length());
			rs.close();
			ps.close();
			if("".equals(sbbh)){	//û�����ɽɿ���,���ѯ�������Ϣ
				/**
				 * ��ѯ�۰�̨or�����ȷ��˰��˰Ŀ����
				 */
				sql.append("select t.fjmgjdq from SBDB.SB_JL_KJQYSDS_FJMQYXX t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				rs.next();
				String fjmgjdq =rs.getString("fjmgjdq");//���һ����
				sql.delete(0,sql.length());
				rs.close();
				ps.close();
				/**
				 * ��ѯ˰��˰Ŀ����
				 */
				if(fjmgjdq.equals(CodeConstant.PAGE_FJMQYGB_GAT)){
					bo.setSzsmdm("300021");
				}
				else{
					bo.setSzsmdm("300022");
				}
				sql.append("select a.szsmdm szsmdm,a.szsmmc szsmmc,b.szsmdm szdm,b.szsmmc szmc from dmdb.sb_dm_szsm a, dmdb.sb_dm_szsm b where a.fjddm=b.szsmdm and a.szsmdm='");
				sql.append(bo.getSzsmdm()).append("'");
				System.out.println("szsmsql----------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					bo.setSzmc(rs.getString("szmc"));
					bo.setSzsmdm(rs.getString("szsmdm"));
					bo.setSzsmmc(rs.getString("szsmmc"));
					bo.setSzdm(rs.getString("szdm"));	
					System.out.println("szdm---"+bo.getSzdm());
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * ��ѯ˰����������
				 */
				sql.append("select to_char(t.skssksrq,'yyyymmdd') skssksrq,to_char(t.skssjsrq,'yyyymmdd') skssjsrq from  SBDB.SB_JL_KJQYSDS_KJBGB t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("˰����������------------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				while(rs.next()){
					bo.setSkssksrq(rs.getString("skssksrq"));
					bo.setSkssjsrq(rs.getString("skssjsrq"));			
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * ��ѯ��ͬ��Ϣ
				 */
				sql.append("select t.htmc,t.htbh from sbdb.sb_jl_kjqysds_bahtmx t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("'");
				System.out.println("��ѯ��ͬ��Ϣ-----------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				
				while(rs.next()){
					bo.setHtmc(rs.getString("htmc"));
					bo.setHtbh(rs.getString("htbh"));
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
				/**
				 * ��ѯʵ��Ӧ���ɵ�����˰��
				 */
				sql.append("select t.bgbxh,t.hc,t.yz from SBDB.SB_JL_KJQYSDS_KJBGMX t where t.badjxh='");
				sql.append(bo.getBadjxh()).append("' and t.jsjdm='");
				sql.append(bo.getJsjdm()).append("' and t.bgbxh='");
				sql.append(bo.getBgbxh()).append("' and (t.hc='12' or t.hc='8' or t.hc='11')");
				System.out.println("ʵ��Ӧ���ɵ�����˰��------------"+sql.toString());
				ps=conn.prepareStatement(sql.toString());
				rs=ps.executeQuery();
				bo.setSjse("0.00");
				bo.setJsje("0.00");
				while(rs.next()){
					if(rs.getString("hc").equals("12")){
						bo.setSjse(rs.getString("yz"));	
					}
					if(rs.getString("hc").equals("11")){
						bo.setSl(rs.getString("yz"));
					}
					if(rs.getString("hc").equals("8")){
						bo.setJsje(rs.getString("yz"));
					}
				}
				rs.close();
				ps.close();
				sql.delete(0,sql.length());
			}

		} catch (Exception e) {
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
     * doSave     ����¼������
     * @param     vo ҵ�����
     * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException    ��������������׳��쳣��Ϣ
     */

    private Object doSave (VOPackage vo)
        throws BaseException
    {
    	Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		StringBuffer sql=new StringBuffer();

        //ormapping����
        Sbjkzb orObj = new Sbjkzb();
        
     // �õ�Action���ݹ���KjqysdsbgbBO����
		KjssjksBO bo = (KjssjksBO) vo.getData();

        //���UserData
        UserData ud = vo.getUserData();
        //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
        String names[] =
            {
            "jsjdm", "nsrmc", "yhmc", "yhdm", "zh", "sklxdm", "sklxmc", "sbrq"};
        Timestamp now = new Timestamp( (new java.util.Date()).getTime());
        try
        {

            //��bo�ж�Ӧ������Ϣ���浽ֵ����
            BeanUtil.copyBeanToBean(names, bo, orObj);
            //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ
            /* start added by huxiaofeng 2005.8.1*/
            //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New(bo.getJsjdm(), ud);
            /* end added by huxiaofeng 2005.8.1*/

            //����������Ϣ
            //�Ǽ�ע������
            if(bo.getSzsmdm().equals("300021")){
            	orObj.setDjzclxdm("290");
            }
            else{
            	orObj.setDjzclxdm("390");
            }
            //orObj.setDjzclxmc(jbsj.getDjzclxmc());
            //���ұ�׼��ҵ����
            orObj.setGjbzhydm(jbsj.getGjbzhydm());
            //orObj.setGjbzhymc(jbsj.getGjbzhymc());
            //˰�������֯����
            orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());
            //orObj.setSwjgzzjgmc(jbsj.getSwjgzzjgmc());
            //���ջ���
            //orObj.setZsswjgzzjgdm(jbsj.getSwjgzzjgdm());
            orObj.setZsswjgzzjgdm(ud.getSsdwdm());
            //orObj.setZsswjgzzjgmc(jbsj.getSwjgzzjgmc());
            //������ϵ
            orObj.setLsgxdm(jbsj.getLsgxdm());
            //orObj.setLsgxmc(jbsj.getLsgxmc());
            //��Ӫ��ַ��ϵ�绰
            orObj.setJydzlxdm(jbsj.getJydzlxdm());
            //¼����
            orObj.setLrr("smsb");
            if (ud != null)
            {
                orObj.setLrr(ud.getYhid());
                //¼������
            }
            orObj.setLrrq(now);
            //��������
            //orObj.setCjsj(now);
            orObj.setCjrq(now);
            //˰������
            orObj.setSklxdm(bo.getSklxdm());
            //orObj.setSklxmc(this.getSklxmc(form.getSklxdm()));
            //�걨��ʽ����
            orObj.setFsdm(CodeConstant.FSDM_SMSB);
            //������Դ
            orObj.setSjly(CodeConstant.SMSB_SJLY_YQKJ);
            //���ش���
            orObj.setQxdm(InterfaceDj.getQxdm(ud));
            //�����ݽ��з�Ʊ����
            JksUtil ju = new JksUtil();
//            return ju.getJkDataZhsb(orObj, form.getDataList());
            try
            {

              //start modifying by qianchao 2005.10.26
              System.out.println("jsjdm:" + bo.getJsjdm() +  "== ��Ʊ���ͣ�" + bo.getJksType());
              Map retmap = (Map) ju.getJkDataKjqysds(orObj,bo.getDataList(),bo.getJksType());
              bo.setSbbh(ju.getSbbh());
              bo.setDataList((List)retmap.get(CodeConstant.ZHSB_JKS_LIST));
              //��sphm����sbdb.sb_jl_kjqysds_kjbgb��
              // �������ݿ�����
  				conn = SfDBResource.getConnection();
  				sql.append("select t.sphm from sbdb.sb_jl_sbjkzb t where t.sbbh='");
  				sql.append(bo.getSbbh()).append("'");
  				ps=conn.prepareStatement(sql.toString());
  				rs=ps.executeQuery();
  				String sphm=new String();
  				while(rs.next()){
  					sphm=rs.getString("sphm");
  				}
  				rs.close();
  				ps.close();
  				sql.delete(0,sql.length());
  				
  				sql.append("update sbdb.sb_jl_kjqysds_kjbgb set sphm='");
  				sql.append(sphm).append("' where badjxh='");
  				sql.append(bo.getBadjxh()).append("' and bgbxh='");
  				sql.append(bo.getBgbxh()).append("'");
  				ps=conn.prepareStatement(sql.toString());
  				ps.execute();
  				ps.close();
  				sql.delete(0,sql.length());
  				
              return bo;
              //end modifying by qianchao 2005.10.26
            }
            catch (BaseException ex1)
            {
                ex1.printStackTrace();
                throw new ApplicationException("��������ʧ�ܣ�");
            }

        }
        catch (Exception ex)
        {
            //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }

    }

    /**
     * �õ���ʼ��list����˰��˰Ŀlist,����˰list
     * @param     vo ҵ�����
     * @return List
     * @throws BaseException
     */
    private Object getInitList (VOPackage vo)
        throws BaseException
    {
        //��ȡform����
        ZhsbActionForm form = (ZhsbActionForm) vo.getData();
        //�õ��ܿ��û������ش���
        String qxdm = InterfaceDj.getQxdm(vo.getUserData());
        List ret = new ArrayList();
        String code="ORSZSM";
        Connection con = null;
        try
        {
//      System.out.println("time == "+new Date());
            con = SfDBResource.getConnection();
            //�õ�˰��˰Ŀ�����б��б�͸���˰�б�
            EArray jsArray = new EArray();
            String tempJsStr = jsArray.getArrayByCode("szsmlist_add",
                "ZHSB_SZSMADD");

            /**
             * ���˰������Ϊ�Բ鲹˰,������ע����11\88˰��,���򲻴���
             */
            if("400".equals(form.getSklxdm())){
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM_ZCBS",new ArrayList());
            	code="ORSZSM_ZCBS";
            }else{
            	tempJsStr +=jsArray.getMsgsByCode("szsmdm", "ZHSB_SZSM",new ArrayList());
            	code="ORSZSM";
            }
            
            //����˰�ѽӿڴ����ڶ�����ʺ͸���˰
            List mxList = this.dealWithSfgl(form.getJsjdm(),
                                            this.getSzsmList(con,code),
                                            SfDateUtil.getDate(form.getSbrq()));
            //�����Ѿ��õ�����������mapΪ��ϸ�������˰����������
            //�����걨���ڵõ�˰����������
            Date date = SfDateUtil.getDate(form.getSbrq());
            if (date == null)
            {
                date = new Date();
            }
//      System.out.println("time == "+new Date());
            this.addSkssrqByMap(form.getJsjdm(), mxList, date, con);
//      System.out.println("time == "+new Date());
            //����˰���������ڵļ���
//      this.fixSpeSkssrq(form.getJsjdm(), date, mxList);
//
//      this.fixSpeSkssrq2(form.getJsjdm(), date, mxList);
            form.setInitMxList(mxList);
            //������ϸ���ݵ�js����
            tempJsStr += this.getMxJsArray(mxList);
            tempJsStr += "\nvar szsmlist_fields = [\"szsmdm\",\"szmc\",\"szsmmc\",\"skssksrq\",\"skssjsrq\",\"kssl\",\"jsje\",\"sjse\",\"szdm\",\"sffjs\",\"szsmdm_old\",\"asljbs\",\"sl\",\"jsjs\"];";
            form.setScriptStr(tempJsStr);
            //���ø�֪�����б�
            form.setGzsxList(this.getGzsxList(form.getJsjdm(), qxdm, new Date(),
                                              con));
            return form;

        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ�˰��˰Ŀ
     * @param     con ���ݿ�����
     * @return List ˰��˰Ŀlist
     * @throws BaseException
     */
    private List getSzsmList (Connection con,String code)
        throws BaseException
    {
        List ret = new ArrayList();
        ret = CodeManager.getCodeList(code, CodeConstants.CODE_MAP_ORLIST).
              getRecords();
        return ret;

    }

    /**
     * �������������õ�����˰��˰Ŀ��˰������ʱ��<br>
     * ��ǰ���ڵ��µ���������ʼ���ֹ���ڵ���
     * ���ڽ�ֹ���ڣ�1��=�޽�����<br>
     * @param  rq �걨ʱ��
     * @param  djzclxdm �Ǽ�ע������
     * @param     conn ���ݿ�����
     * @throws Exception
     * @return Map
     */
    private Map getSksssqMap (String djzclxdm, Date rq, Connection conn)
        throws
        Exception
    {
        List ret = new ArrayList();
        //Connection conn = null;
        try
        {
            //�õ�����
            //conn = SfDBResource.getConnection();

            String dateStr = TinyTools.Date2String(rq,"yyyyMM");
            Vector criteria = new Vector(); //��ѯ����

            criteria.add("djzclxdm='" + djzclxdm + "'");
            criteria.add("ZQQSRQ<=to_date('" + dateStr + "','yyyyMM')");
            criteria.add("ZQZZRQ>=to_date('" + dateStr + "','yyyyMM')");

            SfDBAccess db = new SfDBAccess(conn);
            ret = db.query(new Zqrl().getClass(), criteria);
            Map zqrlMap = new HashMap();
            for (int i = 0; i < ret.size(); i++)
            {
                Zqrl zqrl = (Zqrl) ret.get(i);
                zqrlMap.put(zqrl.getSzsmdm(), zqrl);
            }
            return zqrlMap;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "��ѯ��������ʧ��!");
        }
        finally
        {
            //�ͷ�����
            ///SfDBResource.freeConnection(conn);
        }

    }

    /**
     * ������ϸ�б��е�˰��˰Ŀ���걨���ڡ����˰����������
     * @param     jsjdm ���������
     * @param mxList ǰ̨��ʾ��List
     * @param rq �걨����
     * @param     conn ���ݿ�����
     * @return list
     * @throws BaseException
     *
     */
    private List addSkssrqByMap (String jsjdm, List mxList, Date rq,
                                 Connection conn)
        throws
        BaseException
    {
        List ret = new ArrayList();
        try
        {
            //ͨ���Ǽǽӿڵõ���˰�˻�����Ϣ
            /* start added by huxiaofeng 2005.8.16*/
            //SWDJJBSJ jbsj = InterfaceDj.getJBSJ(jsjdm);
            SWDJJBSJ jbsj = InterfaceDj.getJBSJ_New2(jsjdm);
            /* end added by huxiaofeng 2005.8.16*/


            //�õ��������ڵ�����˰��˰Ŀ˰����������
            Map zqrlMap = this.getSksssqMap(jbsj.getDjzclxdm(), rq, conn);
            for (int i = 0; i < mxList.size(); i++)
            {
                //Ϊÿ����ϸ���˰����������
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //����˰����������mapΪ��ϸ�������˰����������
                Zqrl zqrl = (Zqrl) zqrlMap.get(mxData.getSzsmdm());

                //Modified by lufeng 20031105
                if (zqrl != null)
                {
                    mxData.setSkssjsrq(zqrl.getZqssrqz());
                    mxData.setSkssksrq(zqrl.getZqssrqq());

                }
                else
                {
                    Map map = (Map) Skssrq.getSksssq(jsjdm, mxData.getSzsmdm(),
                        CodeConstant.SKLXDM_ZCJK,
                        mxData.getZqlxdm(),
                        rq, mxData.getSjse(),
                        mxData.getKssl(), mxData.getJsje(),
                        mxData.getJsje());
                    mxData.setSkssjsrq( (Timestamp) map.get("SKSSJSRQ")); //��ʼ����
                    mxData.setSkssksrq( (Timestamp) map.get("SKSSKSRQ")); //��������

                }
                //���ݽ��ɴ����޸ĳ������޽�����

                this.modifyCft(mxData, rq, (Map) CDF.get("JNCS"));
                ret.add(mxData);
            }
            return ret;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
    }

    /**
     * ����˰�ѽӿڵõ���������Ϣ<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * @param     jsjdm ���������
     * @param     date �걨����
     * @param     qsrq �걨����
     * @param     jzrq �걨����
     * @return Map
     */
    private Map getCDFSet (String jsjdm, Date date, Date qsrq, Date jzrq)
    {

        Map map = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            map = proxy.getCDFSet(jsjdm, date, qsrq, jzrq);
        }
        catch (Exception ex)
        {
            return null;
        }
        return map;
    }

    /**
     * ����˰�ѽӿڵõ���������Ϣ<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * <br>�÷���ͨ�����������õ����еĳ���������
     * @param     jsjdm ���������
     * @param     date �걨����
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getCftInfoByJsjdm (String jsjdm, Date date)
    {

        Map cftMap = new HashMap();
        try
        {

            com.ttsoft.bjtax.sfgl.proxy.ServiceProxy proxy =
                new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
            List cftList = proxy.getCftsyhdInfo(jsjdm, date);
            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            //ex.printStackTrace();
            return null;
        }
        return cftMap;
    }
*/
    /**
     * ����˰�ѽӿڵõ�������List�õ�map<br>
     * ��Ϊ����˰�ѽӿ�ֻ����ͨ������������˰��˰Ŀ��һȷ��<br>
     * ����Ӧ��Ϊ����������µ�˰�ѽӿڣ�ͨ�����������õ����еĳ������˶�
     * <br>�÷���ͨ�����������õ����еĳ�������
     * @param cftList �϶��б�
     * @return Map
     */
    private Map getCftMap (List cftList)
    {

        Map cftMap = new HashMap();
        try
        {

            for (int i = 0; i < cftList.size(); i++)
            {
                Cftsyhd temp = (Cftsyhd) cftList.get(i);
                cftMap.put(temp.getSzsmdm(), temp);
            }
        }
        catch (Exception ex)
        {
            return null;
        }
        return cftMap;
    }

    /**
     * ���ݽ��ɴ�������������˰����������
     * @param mxData ��ϸ����
     * @param  sbrq �걨����
     * @param  jncs ���ɴ���
     */
    private void modifyCft (SbjkmxDis mxData, Date sbrq, Map jncs)
    {

        String szdm = mxData.getSzsmdm().substring(0, 2);
        int ijncs = 0;
        if (jncs != null && (String) jncs.get(szdm) != null)
        {
            ijncs = Integer.parseInt( (String) jncs.get(szdm));
            /**
             * ��������ʹ��˰2007��10��������������
             * ��2007���ϰ���δ��������˰,�°�������������ȫ��˰��
             * ���������¿���:
             * 2007���������ʹ��˰���ɴ���ͳһΪȫ������,�����ɴ���Ϊ0
             * 2007.10���ڹ���,��˰���϶����ɴ�������
             * 
             * ��־�� 2007-8-15�ձ�ע
             */
            if(szdm.equals("15")&&TinyTools.Date2String(sbrq,"yyyyMMdd").substring(0,4).equals("2007")){
            	ijncs=0;
            }
        }
        if (Skssrq.SZDM_CFT.indexOf(szdm) > 0)
        {
            //  ���ڳ�����˰��
            Map temp = Skssrq.getCftSkssrq(sbrq, ijncs);
            mxData.setSkssksrq( (Timestamp) temp.get(Skssrq.SKSSKSRQ));
            mxData.setSkssjsrq( (Timestamp) temp.get(Skssrq.SKSSJSRQ));
			//modified by Guoxh,2007-09-12��11��88ֻ������20061231ǰ��˰�
			if(szdm.equals("11") || szdm.equals("88")){
				mxData.setSkssksrq( Skssrq.getTimestampMinDay(2006,0));
				mxData.setSkssjsrq( Skssrq.getTimestampMaxDay(2006,11));
			}
        }
    }

    /**
     * ����˰�ѹ����϶����������������Ϊ���˰��˰Ŀ����<br>
     * �������ʹ��룱�����ꡢ��������ꡢ�������ȡ�12�����£�
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @param jsjdm ���������
     * @param szsmList ˰���б�
     * @param sbrq �걨����
     * @throws Exception
     * @return Map
     */
    private List dealWithSfgl (String jsjdm, List szsmList, Date sbrq)
        throws
        Exception
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();

        try
        {
            //�õ����������µ�˰����������
            Map semiY = Skssrq.monthSkssrq(sbrq);
            Date skssjsrq = (Date) semiY.get(Skssrq.SKSSJSRQ);
            Date skssksrq = (Date) semiY.get(Skssrq.SKSSKSRQ);
            /**
             * ͨ�������������ڶ���͸���˰��һ�Ľӿڵõ��������
             *
             */
            Map cdfMap = this.getCDFSet(jsjdm, sbrq, skssksrq, skssjsrq);
            CDF = cdfMap;
            //���ڶ�����Ϣ
            Map dqdeInfo = this.getDqdeMap( (List) cdfMap.get(Constant.
                SFGL_SB_DQDE));
            //Ӫҵ˰����˰��Ϣ
            Map fjsInfo = this.getFjsMap( (List) cdfMap.get(Constant.
                SFGL_SB_FJS));
            //����˰�ѽӿڵõ���������Ϣ
            Map cftInfo = this.getCftMap( (List) cdfMap.get(Constant.
                SFGL_SB_CFT));
            mxList = this.creatMxList(szsmList);
            //���ݸ�˰����ӿڵõ����и��˵Ķ���ϼ�
            List gsList = (List) cdfMap.get(Constant.SFGL_SB_GSDE);
            //������˰�����ڶ�����������
            for (int i = 0; i < mxList.size(); i++)
            {
                SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
                //  if (mxData.getZqlxdm().equals(CodeConstant.ZQLXDM_YEAR)) {
                //����������Ϊ���ʱ�����˰�ѽӿڴ�����Ӧ������

                String szsmdm = mxData.getSzsmdm();

                // ����˰�ѹ����еĶ��ڶ���
                if (dqdeInfo != null)
                {
                    Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(szsmdm);
                    if (dqde != null)
                    {
                        //
                        if (dqde.getZsfsdm().equals(CodeConstant.ZHSB_ZSFS_DE))
                        {
                            //���շ�ʽΪ����
                            //�����걨�Ľӿ��У���˰���϶�����Ӧ�걨�ġ���˰������Ӧ��˰���Ӧ�걨�ġ�ʵ�ʽ�˰���
                            mxData.setSjse(dqde.getYnsrd());
                            //mxData.setSjse(dqde.getSjrd());
                            mxData.setJsje(dqde.getSjrd());
                            mxData.setFromDqde(true);
                        }
                        else if (dqde.getZsfsdm().equals(CodeConstant.
                            ZHSB_ZSFS_DL))
                        {
                            //���շ�ʽΪ����
                            mxData.setSl(dqde.getZsl());
                        }

                    }
                }
                // ����˰�ѹ����еĸ���˰˰��
                //        if (mxData.getSffjs() != null && mxData.getSffjs().equals("2") &&
//            fjsInfo != null) {
                //�걨����ʱ��˰���ǳ���ά������˰��10%���ͽ����Ѹ��ӣ�51%��ȡ˰�����������ʣ�����˰��ȡ˰�Ѷ��ڶ���ɡ�
                String smdm = szsmdm.substring(0, 2);
                if (fjsInfo != null && (smdm.equals("10") || smdm.equals("51")))
                {
                    //˰Ŀ��Ӫҵ˰����˰��������˰��ȡ�ú˶���Ϣ
                    Tszslmx tszslmx = (Tszslmx) fjsInfo.get(smdm);
                    if (tszslmx != null)
                    {
                        //
                        mxData.setSl(tszslmx.getSl());
                    }
                }
                //����н����050130
                if (szsmdm.equals(CodeConstant.ZRR_GXSD_SZSMDM))
                {
                    //��˰����ϼ�
                    BigDecimal hj = new BigDecimal(0);
                    //�ϼ����еĸ�˰����
                    for (int ig = 0; ig < gsList.size(); ig++)
                    {
                        Grtszygsde temp = (Grtszygsde) gsList.get(ig);
                        hj = hj.add(temp.getHdske());
                    }

                    if (hj.longValue() != 0)
                    {
                        mxData.setSjse(hj);
                    }
                }

                //}
            }
            return mxList;
        }
        catch (BaseException ex)
        {
            throw ExceptionUtil.getBaseException(ex, "����˰��˰Ŀ�б�ʧ��");
        }

    }

    /**
     * ����˰�ѹ����϶��������˰��˰Ŀ����<br>
     * ���ݶ��ڶ������ݺ�Ӫҵ˰����˰���ݴ���˰��˰Ŀlist
     * ���ش����ĸ���˰��˰Ŀ��Ӧ����ϸlist
     * @param szsmList ˰���б�
     * @return Map
     */
    private List creatMxList (List szsmList)
    {
        //��Ӵ�������ݵ���ϸlist
        List mxList = new ArrayList();
        String szdm = "";
        String szmc = "";
        for (int i = 0; i < szsmList.size(); i++)
        {
            //ǰ̨��ʾ���걨�ɿ���ϸ
            SbjkmxDis temp = new SbjkmxDis();
            Szsm szsm = (Szsm) szsmList.get(i);
            //��Ϊ����˰��˰Ŀ�����������Կ�����ȡ��˰��

            if (szsm.getSzsmdm().length() == 2)
            {
                //����Ϊ2����˰�ִ���
                szdm = szsm.getSzsmdm();
                szmc = szsm.getSzsmmc();
            }
            //������ϸ��˰��˰Ŀ
            if (szsm.getSzsmdm().length() == 6)
            {
                //����Ϊ6Ϊ˰Ŀ
                temp.setSzsmdm(szsm.getSzsmdm());
                temp.setSzsmmc(szsm.getSzsmmc());
                //�����Ƿ񸽼�˰��ʾ
                temp.setSffjs(szsm.getSffjs());
                //����˰��
                temp.setSzdm(szdm);
                temp.setSzmc(szmc);
                //���ð������Ʊ�ʾ
                temp.setAsljbs(szsm.getAsljbs());
                //����˰��
                temp.setSl(szsm.getSl());
                //�����������ʹ���
                temp.setZqlxdm(szsm.getZqlxdm());
                //���ü�˰����
                temp.setJsjs(szsm.getJsjs());
                mxList.add(temp);
            }

        }
        return mxList;
    }

    /**
     * ����˰�ѽӿڵõ�����˰�˶�
     * @param jsjdm ���������
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getFjsInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List fjsInfo = sfglProxy.getYyfjsslInfo(jsjdm, new Date());

        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }
*/
    /**
     * ����˰�ѽӿڵõ�����˰�˶�
     * @param fjsInfo ��ϸ�����б�
     * @throws BaseException
     * @return Map
     */
    private Map getFjsMap (List fjsInfo)
        throws BaseException
    {

        Map fjsMap = new HashMap();
        for (int i = 0; i < fjsInfo.size(); i++)
        {
            Tszslmx tszslmx = (Tszslmx) fjsInfo.get(i);
            fjsMap.put(tszslmx.getSzsmdm(), tszslmx);
        }

        return fjsMap;
    }

    /**
     * ����˰�ѽӿ�ȡ�ö��ڶ���˶�
     * @param jsjdm ���������
     * @throws BaseException
     * @return Map
     */
    /* deleted by qianchao 2005.11.2
    private Map getDqdeInfo (String jsjdm)
        throws BaseException
    {
        com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfglProxy =
            new com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();

        List dqdeInfo = sfglProxy.getYnsje(jsjdm, new Date(), new Date());

        Map dqdeMap = new HashMap();
        if (dqdeInfo != null)
        {
            for (int i = 0; i < dqdeInfo.size(); i++)
            {
                Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
                dqdeMap.put(dqde.getSzsmdm(), dqde);
            }
        }

        return dqdeMap;
    }
*/
    /**
     * ����˰�ѽӿ�ȡ�ö��ڶ���˶�
     * ͨ�����ڶ���list�õ����ڶ���map
     * @param dqdeInfo ��ϸ�����б�
     * @throws BaseException
     * @return Map
     */
    private Map getDqdeMap (List dqdeInfo)
        throws BaseException
    {
        Map dqdeMap = new HashMap();
        if (dqdeInfo != null)
        {
            for (int i = 0; i < dqdeInfo.size(); i++)
            {
                Dqdedlmx1 dqde = (Dqdedlmx1) dqdeInfo.get(i);
                dqdeMap.put(dqde.getSzsmdm(), dqde);
            }
        }

        return dqdeMap;
    }

    /**
     * ������ϸ�����б�����js2ά����<br>
     * @param mxList ��ϸ�����б�
     * @return String js2ά����
     **/
    private String getMxJsArray (List mxList)
    {
        StringBuffer ret = new StringBuffer();
        //ret.append("[");
        for (int i = 0; i < mxList.size(); i++)
        {
            SbjkmxDis mxData = (SbjkmxDis) mxList.get(i);
            ret.append("[");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //˰������
            ret.append("\"" + mxData.getSzmc() + "\",");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmmc() + "\",");
            if (mxData.getSkssksrq() != null)
            {
                //˰����������
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssksrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssksrq() + "\",");
            }
            if (mxData.getSkssjsrq() != null)
            {
                ret.append("\"" + SfDateUtil.getDate(mxData.getSkssjsrq())
                           + "\",");
            }
            else
            {
                ret.append("\"" + mxData.getSkssjsrq() + "\",");
            }
            //��˰����
            ret.append("\"" + mxData.getKssl() + "\",");
            //��˰���
            ret.append("\"" + mxData.getJsje() + "\",");
            //ʵ��˰��
            ret.append("\"" + mxData.getSjse() + "\",");
            //˰�ִ���
            ret.append("\"" + mxData.getSzdm() + "\",");
            //�Ƿ񸽼�˰
            ret.append("\"" + mxData.getSffjs() + "\",");
            //˰��˰Ŀ����
            ret.append("\"" + mxData.getSzsmdm() + "\",");
            //�������Ʊ�ʾ
            ret.append("\"" + mxData.getAsljbs() + "\",");
            //˰��
            ret.append("\"" + mxData.getSl() + "\",");
            //��˰����
            //ret.append("\"" + mxData.getJsjs() + "\"");
            ret.append("\"" + mxData.getSl() + "\"");
            ret.append("],");
        }
        if (ret.length() > 0)
        {
            //��������ݣ���ɾ�������ӵĶ���
            ret.delete(ret.length() - 1, ret.length());
        }
        else
        {
            return "var szsmlist = new Array();";
        }
        ret.append("];");
        ret = SfStringUtils.replaceAll(ret, "null", "");
        return "var szsmlist = [" + ret.toString();

    }

    /**
     * �õ�˰��˰Ŀ
     * @param jsjdm ���������
     * @param rq ����
     * @return List ˰��˰Ŀlist
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, Date rq)
        throws BaseException
    {

        List ret = new ArrayList();
        Connection con = null;
        try
        {
            con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            //ֻ�з�������֪�����ʱ���ת�Ƶ���֪����ҳ��
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ���֪�б�
     * @param jsjdm ���������
     * @param rq ����
     * @param qxdm ���ش���
     * @param con ���ݿ�����
     * @return List ��֪list
     * @throws BaseException
     */
    private List getGzsxList (String jsjdm, String qxdm, Date rq,
                              Connection con)
        throws
        BaseException
    {

        List ret = new ArrayList();
        //Connection con = null;
        try
        {
            //con = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(con);
            Vector v = new Vector();
            //v.add("jsjdm='" + jsjdm + "'");
            v.add("gzsxksrq<=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("gzsxjsrq>=to_date('" + DateUtil.getDate(rq)
                  + "','yyyy-MM-dd')");
            v.add("qxdm='" + qxdm + "'");
            //ֻ�з�������֪�����ʱ���ת�Ƶ���֪����ҳ��
            //v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' order by GZSXKSRQ");
            v.add("fzcbs='" + CodeConstant.ZHSB_FZCBS + "' ");
            v.add("jsjdm='" + jsjdm + "'  order by cjrq desc");
            List ret1 = db.query(new Gzsx().getClass(), v);
            return ret1;
        }
        catch (Exception ex)
        {
            throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
        }
        finally
        {
            //SfDBResource.freeConnection(con);
        }

    }

    private List getGzsxInfo (VOPackage vo)
        throws BaseException
    {
        ZhsbGzsxActionForm form = (ZhsbGzsxActionForm) vo.getData();
        return this.getGzsxList(form.getJsjdm(), new Date());
    }

}

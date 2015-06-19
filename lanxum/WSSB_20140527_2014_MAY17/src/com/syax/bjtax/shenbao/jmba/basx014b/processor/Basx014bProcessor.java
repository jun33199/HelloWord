/*
 * Created on 2010-1-5
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.syax.bjtax.shenbao.jmba.basx014b.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.syax.bjtax.ca.util.DzyjHelper;
import com.syax.bjtax.ca.vo.DzyjsjVO;
import com.syax.bjtax.shenbao.jmba.dao.PublicAccess;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbaActionConstant;
import com.syax.bjtax.shenbao.jmba.jmbaz.JmbamxBo;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14AVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba14BVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba18VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba20VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.VOConstants;
import com.syax.bjtax.shenbao.model.dm.GROUPZYSBLX;
import com.syax.bjtax.shenbao.model.dm.ZYSBLX;
import com.syax.common.util.CAcodeConstants;
import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.DateUtilPro;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * @author MI_Viewer
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class Basx014bProcessor implements Processor
{
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

    public Object process(VOPackage vo) throws BaseException
    {

        Object result = null;
        if(vo == null) {
            throw new NullPointerException();
        }

        switch(vo.getAction()) {

            case JmbaActionConstant.INTI_ACTION_QUERY:
                result = doQuery(vo);
                break;
            case JmbaActionConstant.INTI_ACTION_QUERYDETAIL:
                result = doAjaxQuery(vo);
                break;
            case JmbaActionConstant.INTI_ACTION_SAVE:
                result = doSave(vo); // ����Ϊ1
                break;
            case JmbaActionConstant.INTI_ACTION_DELETE:
                result = doDelete(vo);
                break;
            case JmbaActionConstant.INTI_ACTION_COMMIT:

                // result = doCommit(vo, "3"); // �ύΪ2
                break;

            default:
                throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    /**
     * doSave�������ҳ����ϢҪ��
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */

    private Object doSave(VOPackage vo) throws BaseException
    {
        // sqzt ������1���ύ��2
        List list = new ArrayList();
        DzyjHelper dh = new DzyjHelper();
        Map hm = (Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        DzyjsjVO dzyj = (DzyjsjVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
        System.out.println(vo1.getQysdsjmba().get(0).getClass());
        Jmba14BVO mxvo = (Jmba14BVO)vo1.getQysdsjmba().get(0);

        //Jmba14BVO mxvo = (Jmba14BVO) vo1.getQysdsjmba().get(0);
        UserData ud = (UserData)vo.getUserData();
        Connection conn = null;
        PreparedStatement ps = null;
        com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
        int band = (Integer.parseInt(DateUtilPro.getCurYearStr4()) - 1);
        String sql = "";
        try {
            // ����ԭ���ݲ�ʵ��
            /*
             * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
             * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
             * ex) { ex.printStackTrace(); throw
             * ExceptionUtil.getBaseException(ex); }
             * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
             */

            conn = DBResource.getConnection();
            JmbaZbVO vo2 = PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
            // conn.setHoldability(1);
            String dmnd = mxvo.getDmnd();
            // ����
            if(vo2.getBasqwsh() == null || "".equals(vo2.getBasqwsh())) {
                PublicAccess.saveZb(conn, bavo, ud);

                // if (mxvo.getXh() != null && !mxvo.getXh().equals("")) {
                // sql = "UPDATE sfdb.sf_jl_qysdsjmsba_14B SET ZYSBLXDM='"
                // + mxvo.getZysblxdm() + "',ZYSBMC='" + mxvo.getZysbmc()
                // + "',TZEZS='" + mxvo.getTzezs() + "',DMND='"
                // + mxvo.getDmnd() + "',DMYNSE='" + mxvo.getDmynse()
                // + "',DNKDMSE='" + mxvo.getDnkdmse() + "',JZE='"
                // + mxvo.getJze() + "',LRR='" + ud.getYhid()
                // + "',LRRQ=sysdate where xh ='" + mxvo.getXh() + "'";

                // ��ԭ������ɾ��
                sql = "delete sfdb.sf_jl_qysdsjmsba_14b t where t.BASQWSH='"
                    + mxvo.getBasqwsh() + "' and t.JSJDM='"
                    + mxvo.getJsjdm() + "'";
                ps = conn.prepareStatement(sql);
                System.out.println("��ԭ������ɾ��" + sql);
                ps.execute();
                if(ps != null) {
                    ps.close();
                }

                // �����µ�����
                sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_14B (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM"
                    + ",ZYSBLXDM,ZYSBMC,DMND,tznd,TZEZS,DNKDMSE,DMYNSE,JZE"
                    + ",CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
                    + vo1.getBasqwsh()
                    + "','"
                    + bavo.getNsrxx().getJsjdm()
                    + "','"
                    + vo1.getBand()
                    + "','"
                    + bavo.getNsrxx().getSwjgzzjgdm()
                    + "','"
                    + mxvo.getZysblxdm()
                    + "','"
                    + mxvo.getZysbmc()
                    + "',?,?,?,?,?,?,'"
                    + ud.getYhid()
                    + "',sysdate,'"
                    + ud.getYhid() + "',sysdate)";
                //
                System.out.println("14BProcessor=����==doSave===sql=" + sql);
                ps = conn.prepareStatement(sql);
                int count = vo1.getQysdsjmba().size();
                for(int i = 0; i <count; i++) {
                    System.out.println("14bProcessor===doSave===sql="
                                       + qysdsUtil.getSequence() + "----" + mxvo.getDmnd()
                                       + "--" + ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getTzezs() + "--" +
                                       ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDnkdmse()
                                       + "--" + ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmynse() + "--" +
                                       ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getJze()
                                       + "--");
                    ps.setString(1, qysdsUtil.getSequence());
                    ps.setString(2, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmnd());
                    ps.setString(3,  dmnd);
                    ps.setString(4, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getTzezs());
                    ps.setString(5, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDnkdmse());
                    ps.setString(6, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmynse());
                    ps.setString(7, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getJze());
                    ps.addBatch();
                }
                ps.executeBatch();
                if(ps != null) {
                    ps.close();
                }

            }
            else {

                // ��ԭ������ɾ��
                sql = "delete sfdb.sf_jl_qysdsjmsba_14b t where t.BASQWSH='"
                    + vo1.getBasqwsh() + "'";
                ps = conn.prepareStatement(sql);
                ps.execute();
                if(ps != null) {
                    ps.close();
                }
                // ����һ����
                sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_14B (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM"
                    + ",ZYSBLXDM,ZYSBMC,DMND,tznd,TZEZS,DNKDMSE,DMYNSE,JZE"
                    + ",CJR,CJRQ,LRR,LRRQ) VALUES(?,'"
                    + vo1.getBasqwsh()
                    + "','"
                    + bavo.getNsrxx().getJsjdm()
                    + "','"
                    + vo1.getBand()
                    + "','"
                    + bavo.getNsrxx().getSwjgzzjgdm()
                    + "','"
                    + mxvo.getZysblxdm()
                    + "','"
                    + mxvo.getZysbmc()
                    + "',?,?,?,?,?,?,'"
                    + ud.getYhid()
                    + "',sysdate,'"
                    + ud.getYhid() + "',sysdate)";
                //
                System.out.println("14BProcessor=����==doSave===sql=" + sql);
                ps = conn.prepareStatement(sql);
                int count = vo1.getQysdsjmba().size();
                for(int i = 0; i <count; i++) {
                    System.out.println("14bProcessor===doSave===sql="
                                       + ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmnd() + "--" +
                                       ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getTzezs() + "--"
                                       + ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDnkdmse() + "--" +
                                       ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmynse() + "--"
                                       + ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getJze() + "--");

                    ps.setString(1, qysdsUtil.getSequence());
//                    ps.setString(2, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmnd());
//                    ps.setString(3, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getTzezs());
//                    ps.setString(4, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDnkdmse());
//                    ps.setString(5, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmynse());
//                    ps.setString(6, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getJze());

                    ps.setString(2, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmnd());
                    ps.setString(3,  dmnd);
                    ps.setString(4, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getTzezs());
                    ps.setString(5, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDnkdmse());
                    ps.setString(6, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getDmynse());
                    ps.setString(7, ( (Jmba14BVO)vo1.getQysdsjmba().get(i)).getJze());
ps.addBatch();
                }
                ps.executeBatch();
                System.out.println("14BProcessor===doSave===sql=" + sql);
                if(ps != null) {
                    ps.close();
                }
            }
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            DBResource.destroyConnection(conn);
        }
        return list;

    }

    /**
     * doDeleteɾ������ҳ����ϢҪ��
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */

    private Object doDelete(VOPackage vo) throws BaseException
    {

        DzyjHelper dh = new DzyjHelper();
        Map hm = (Map)vo.getData();
        JmbamxBo bo = (JmbamxBo)hm.get(VOConstants.KEY_JMBA_MX_BO);
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        DzyjsjVO dzyj = (DzyjsjVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
        JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);
        // Jmba01VO mxvo=(Jmba01VO)vo1.getQysdsjmba().get(0);
        UserData ud = (UserData)vo.getUserData();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // ����ԭ���ݲ�ʵ��
            /*
             * try { dzyj = CAUtils.saveDzyjsj(ud,dzyj, "0", "0", "0",
             * vo1.getBasqwsh()+VOConstants.QYSDSJMBA_SXDM01); }catch (Exception
             * ex) { ex.printStackTrace(); throw
             * ExceptionUtil.getBaseException(ex); }
             * hm.put(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ, dzyj);
             */
            conn = DBResource.getConnection();
            String delSQL = "DELETE FROM sfdb.sf_jl_qysdsjmsba_14B t WHERE t.xh = '"
                + bo.getXh() + "'";
            System.out.println("delSQL===" + delSQL);
            ps = conn.prepareStatement(delSQL);
            ps.executeUpdate();
            if(ps != null) {
                ps.close();
            }

        }
        catch(Exception ex) {
            throw new ApplicationException("���ݿ���¼�¼ʧ�ܣ�" + ud.getYhid() + ":"
                                           + ex.getMessage());
        }
        finally {
            DBResource.destroyConnection(conn);
        }
        return null;
    }

    private Object doAjaxQuery(VOPackage vo) throws BaseException
    {

        JmbaZbVO vo1 = null;
        UserData ud = vo.getUserData();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap map1=new HashMap();
        Map map = (Map)vo.getData();
        String wnbasqwsh = (String)map.get("wnbasqwsh");
        String querydmnd = (String)map.get("querydmnd");
        String jsjdm = ud.getYhid();
        JmbamxBo bo = (JmbamxBo)map.get("bo");
        vo1 = PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
        try {
            conn = DBResource.getConnection();

            StringBuffer sb = new StringBuffer();
            List list = new ArrayList();

            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String tjsj = df.format(date);
            List sblxlist = new ArrayList();
			sb = new StringBuffer();
			sb.append("SELECT zysblxdm,LPAD('  ',2*(LEVEL - 1)) || zysblxmc zysblxmc, level " +
					"FROM dmdb.sf_dm_zysblx START WITH fjddm IS NULL CONNECT BY PRIOR zysblxdm = fjddm"
);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				GROUPZYSBLX sblx = getSblxvo(rs);
				sblxlist.add(sblx);
			}
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			map1.put("sblx",sblxlist);
            // ����
            if(vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
                vo1 = new JmbaZbVO();
                vo1.setBand(bo.getBand());
                vo1.setBasqbh(bo.getBasqbh());
                vo1.setBasqwsh(bo.getBasqwsh());
                vo1.setJmbasxdm(bo.getJmbasxdm());
                vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
                            .format(new Date()));
            }
            sb = new StringBuffer();
            sb.append("select t.xh,t.ZYSBLXDM,t.ZYSBMC,t.TZEZS,t.DMND,T.TZND,t.DMYNSE,t.YWCBABS,t.DNKDMSE,t.JZE ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_14B t ");

            if(wnbasqwsh!=null && !"".equals(wnbasqwsh)){
                sb.append(" ,SFDB.SF_JL_QYSDSJMSBAJL T2  where 1=1 ");
                sb.append(" and t.basqwsh = t2.basqwsh and t2.basqbh = '"+wnbasqwsh+"' ");
            }else if(querydmnd!=null && !"".equals(querydmnd)){
                sb.append(" where 1=1 and t.jsjdm ='"+jsjdm+"' ");
                sb.append(" and t.dmnd >= '"+querydmnd+"' ");
            }
            System.out.println("===query sql==" + sb.toString());

            ps = conn.prepareStatement(sb.toString());
            rs = ps.executeQuery();

            while(rs.next()) {
                Jmba14BVO mxvo = getBamxvo(rs);

                list.add(mxvo);

            }
            if(list!=null && !list.isEmpty()){
            	Jmba14BVO mxvo = null;

            	 mxvo =  (Jmba14BVO) list.get(list.size()-1);
            	 int tznd = Integer.parseInt(mxvo.getDmnd())+1;
            	 System.out.println("tznd = "+tznd);
            	 int band = Integer.parseInt(DateUtilPro.getCurYearStr4());
            	 System.out.println("band = "+band);
            	 for(;tznd<band;tznd++){
		             mxvo = new Jmba14BVO();
		            mxvo.setXh("");
		            mxvo.setZysblxdm("");
		            mxvo.setZysbmc("");
		            mxvo.setTzezs("");
		            mxvo.setDmnd(tznd+"");
		            System.out.println("i = "+tznd);
		            mxvo.setDmynse("");
		            mxvo.setYwcbabs("");
		            mxvo.setDnkdmse("");
		            mxvo.setJze("");
		            list.add(mxvo);
            	 }
            }
            vo1.setQysdsjmba(list);
            System.out.println("query mx done");
            if(rs != null) {
                rs.close();
            }
            if(ps != null) {
                ps.close();
            }
            map1.put("jmbavo",vo1);
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            DBResource.destroyConnection(conn);
        }
        return map1;
    }

    /**
     *
     *

     * doShow��ʼ������ҳ����ϢҪ��
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private GROUPZYSBLX getSblxvo(ResultSet rs) throws Exception {
    	GROUPZYSBLX sblx = new GROUPZYSBLX();
		// String tbblx = null;
		try {
			sblx.setZYSBLXDM(rs.getString("ZYSBLXDM"));
			sblx.setZYSBLXMC(rs.getString("ZYSBLXMC"));
			sblx.setLEVEL(rs.getString("LEVEL"));
			return sblx;
		} catch (Exception e) {
			throw e;
		}
	}
    private Object doQuery(VOPackage vo) throws BaseException
    {

        JmbamxBo bo = (JmbamxBo)vo.getData();
        JmbaZbVO vo1 = null;
        UserData ud = vo.getUserData();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap map=new HashMap();
        try {
            conn = DBResource.getConnection();
            vo1 = PublicAccess.getJmbaZbVO(conn, bo.getBasqwsh());
            Date date = new Date();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String tjsj = df.format(date);
            StringBuffer sb = new StringBuffer();
            List list = new ArrayList();
            List sblxlist = new ArrayList();
			sb = new StringBuffer();
			sb.append("SELECT zysblxdm,LPAD('  ',2*(LEVEL - 1)) || zysblxmc zysblxmc, level " +
					"FROM dmdb.sf_dm_zysblx START WITH fjddm IS NULL CONNECT BY PRIOR zysblxdm = fjddm"
);
			ps = conn.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			while (rs.next()) {
				GROUPZYSBLX sblx = getSblxvo(rs);
				sblxlist.add(sblx);
			}
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			map.put("sblx",sblxlist);
            // ����
            if(vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
                vo1 = new JmbaZbVO();
                vo1.setBand(bo.getBand());
                vo1.setBasqbh(bo.getBasqbh());
                vo1.setBasqwsh(bo.getBasqwsh());
                vo1.setJmbasxdm(bo.getJmbasxdm());
                vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
                            .format(new Date()));
                // vo1.setJmbasxmc("");//��action�����

                // if (list.size() ==0){//��ѯ�����
                sb = new StringBuffer();

                sb
                    .append("select t.xh,t.ZYSBLXDM,t.ZYSBMC,t.TZEZS,t.DMND,t.tznd,t.DMYNSE,t.YWCBABS,t.DNKDMSE,t.JZE");

                sb
                    .append(" from sfdb.sf_jl_qysdsjmsba_14b t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
                            + (Integer.parseInt(bo.getBand()) - 1) + "' ");
                sb
                    .append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
                            + ud.getYhid() + "' and rownum=1 ");
                System.out.println("===query wn sql==" + sb.toString());
                ps = conn.prepareStatement(sb.toString());
                rs = ps.executeQuery();

                while(rs.next()) {
                    Jmba14BVO mxvo = getBamxvo(rs);
                    mxvo.setXh("");

                    list.add(mxvo);
                }

                if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }


                // }
               // vo1.setQysdsjmba(list);
                map.put("jmbavo",vo1);
				return map;
               // return vo1;
            }

            sb = new StringBuffer();
            sb
                .append("select t.xh,t.ZYSBLXDM,t.ZYSBMC,t.TZEZS,t.DMND,T.TZND,t.DMYNSE,t.YWCBABS,t.DNKDMSE,t.JZE");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_14B t where  basqwsh='"
                      + bo.getBasqwsh() + "'");

            if(bo.getXh() != null && !bo.getXh().equals("")) {
                sb.append(" and a.xh=" + bo.getXh() + " ");
            }

            System.out.println("===query sql==" + sb.toString());

            ps = conn.prepareStatement(sb.toString());
            rs = ps.executeQuery();
            int i = 0;
            while(rs.next()) {
                Jmba14BVO mxvo = getBamxvo(rs);

                list.add(mxvo);
            }
            vo1.setQysdsjmba(list);
            System.out.println("query mx done");
            if(rs != null) {
                rs.close();
            }
            if(ps != null) {
                ps.close();
            }
            map.put("jmbavo",vo1);
        }
        catch(Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            DBResource.destroyConnection(conn);
        }
    	return map;
    }

    private Jmba14BVO getBamxvo(ResultSet rs) throws Exception
    {
        Jmba14BVO mxvo = new Jmba14BVO();
        // String tbblx = null;
        try {
            mxvo.setXh(rs.getString("xh"));
            mxvo.setZysblxdm(rs.getString("ZYSBLXDM"));
            mxvo.setZysbmc(rs.getString("ZYSBMC"));
            mxvo.setTzezs(MoneyUtils.format(rs.getString("TZEZS")));
            mxvo.setDmnd(rs.getString("DMND"));
            mxvo.setDmynse(MoneyUtils.format(rs.getString("DMYNSE")));
            mxvo.setYwcbabs(rs.getString("YWCBABS"));
            mxvo.setDnkdmse(MoneyUtils.format(rs.getString("DNKDMSE")));
            mxvo.setJze(MoneyUtils.format(rs.getString("JZE")));

            return mxvo;
        }
        catch(Exception e) {
            throw e;
        }
    }

}

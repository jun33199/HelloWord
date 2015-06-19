package com.syax.bjtax.shenbao.jmba.jyxwhsy19.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
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
import com.syax.bjtax.shenbao.jmba.xmlvo.Jmba19VO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaVO;
import com.syax.bjtax.shenbao.jmba.xmlvo.JmbaZbVO;
import com.syax.bjtax.shenbao.model.common.NsrxxVO;
import com.syax.bjtax.shenbao.util.QysdsUtil;
import com.syax.common.util.CAcodeConstants;

import com.ttsoft.bjtax.shenbao.util.DBResource;
import com.ttsoft.bjtax.shenbao.util.Debug;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.util.MoneyUtils;


/**
 *
 * <p>
 * Title: ������˰�ۺϹ���ϵͳ �걨����ģ��
 * </p>
 * <p>
 * Description: ¼����˰��λ��������ҵ����˰��������Processor
 * </p>
 * <p>
 * Copyright: Copyright (c) 2003
 * </p>
 * <p>
 * Company: syax
 * </p>
 * @version 1.0
 */
public class LrjyxwhsyProcessor implements Processor{

	/**
	 * �ܿ�����
	 */
	private UserData userData;

	   /**
     * ��������
     */
    public Object process(VOPackage vo) throws BaseException {
        this.userData = vo.getUserData();
        Object result = null;
        // ����ҵ���������ֵ����ҵ�����
        try {
            switch(vo.getAction()) {
                // ��ѯ
                case JmbaActionConstant.INTI_ACTION_QUERY:
                {
                    vo.setData(this.doQuery((Map)vo.getData()));
                    return vo;
                }
                case JmbaActionConstant.INTI_ACTION_SHOW:
                {
                	Debug.out("processor111111111111");
    				result = doShow(vo);

    				return result;
                }
                case JmbaActionConstant.INTI_ACTION_SAVE:{
                	result = doSave(vo);
    				return result;
                }
                case JmbaActionConstant.INTI_ACTION_COMMIT:{
                    vo.setData(this.doCommit((Map)vo.getData()));
                    return vo;
                }
                // û�пɵ��õķ���
                default:
                    throw new SystemException("no such mothod");
            }
        }
        catch(Exception e) {
            throw ExceptionUtil.getBaseException(e);
        }
    }


	/**
	 * ʹ��keyΪJmsMapConstant.SBRQȡ�걨���� ͨ�����������͵�ǰ����ȡ�ñ������걨���ݣ����£�
	 * ��keyΪJmsMapConstant.LIST_JMYSB�ű������걨����
	 *
	 * @param data
	 * ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
    private Map doQuery(Map data) throws BaseException {
        Map map = new HashMap();
        String BASQWSH = (String)data.get("BASQWSH");
        //System.out.println("BASQWSH = "+BASQWSH);
        Jmba19VO vo = new Jmba19VO();
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        boolean flag = false;
        try {
            String sql = "select XH,BASQWSH,JSJDM,BAND,WHSYDWLXDM,SFYQYMD,SFYZZFAPFH,SFBLGSYYZZ,SFYZXSYDWZM,SFCJSHBX,SFYBGZBJG,JMSE,QTZL,CJR,to_char(CJRQ,'yyyymmdd') CJRQ,LRR,to_char(LRRQ,'yyyymmdd') LRRQ  from sfdb.sf_jl_qysdsjmsba_19 t where t.basqwsh = '"+BASQWSH+"'";
            con = DBResource.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
            	flag = true;
                vo.setXH(rs.getString("XH"));
                vo.setBASQWSH(rs.getString("BASQWSH"));
                vo.setJSJDM(rs.getString("JSJDM"));
                vo.setBAND(rs.getString("BAND"));
                vo.setWHSYDWLXDM(rs.getString("WHSYDWLXDM"));
                vo.setSFYQYMD(rs.getString("SFYQYMD"));
                vo.setSFYZZFAPFH(rs.getString("SFYZZFAPFH"));
                vo.setSFBLGSYYZZ(rs.getString("SFBLGSYYZZ"));
                vo.setSFYZXSYDWZM(rs.getString("SFYZXSYDWZM"));
                vo.setSFCJSHBX(rs.getString("SFCJSHBX"));
                vo.setSFYBGZBJG(rs.getString("SFYBGZBJG"));
                vo.setJMSE(rs.getString("JMSE"));
                vo.setQTZL(rs.getString("QTZL"));
                vo.setCJR(rs.getString("CJR"));
                vo.setCJRQ(rs.getString("CJRQ"));
                vo.setLRR(rs.getString("LRR"));
                vo.setLRRQ(rs.getString("LRRQ"));
                //System.out.println("¼�����ڣ�" + rs.getString("LRRQ"));
            }
            //���������ʱ����ʱ����û�ж�Ӧ���ݣ�ȡϵͳ��
            if (!flag){
            	//¼������
            	vo.setLRRQ(getSysTimeNow());
            	//�������
            	vo.setBAND(getBand());
            	//¼����
            	vo.setLRR((String)data.get("jsjdm"));
            }
            map.put("Jmba19VO",vo);
            map.put("JmbaZbVO",PublicAccess.getJmbaZbVO(con,BASQWSH));
            map.put("JmbaZlqdVO",PublicAccess.getJmbaZlqdVO(con,BASQWSH));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception exx) {
            }
        }

        return map;
    }
    private Jmba19VO getBamxvo(ResultSet rs) throws Exception {
		Jmba19VO vo = new Jmba19VO();
		try {
			 vo.setXH(rs.getString("XH"));
             vo.setBASQWSH(rs.getString("BASQWSH"));
             vo.setJSJDM(rs.getString("JSJDM"));
             vo.setBAND(rs.getString("BAND"));
             vo.setWHSYDWLXDM(rs.getString("WHSYDWLXDM"));
             vo.setJMSE(MoneyUtils.format(rs.getString("JMSE")));
             vo.setLRR(rs.getString("LRR"));
             vo.setLRRQ(rs.getString("LRRQ"));
			// mxvo.setZfgz( MoneyUtils.format(rs.getString("zfgz")));
			// mxvo.setJjkcje( MoneyUtils.format(rs.getString("jjkcje")));
			return vo;
		} catch (Exception e) {
			throw e;
		}
	}
	/**
	 * ʹ��keyΪJmsMapConstant.SBRQȡ�걨���� ͨ�����������͵�ǰ����ȡ�ñ������걨���ݣ����£�
	 * ��keyΪJmsMapConstant.LIST_JMYSB�ű������걨����
	 *
	 * @param data
	 * ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
    private Object doShow(VOPackage vo) throws BaseException {
    	JmbamxBo bo = (JmbamxBo) vo.getData();
		JmbaZbVO vo1 = null;
		UserData ud = vo.getUserData();
		Connection con = null;
		ResultSet rs = null;
		Statement st = null;
		PreparedStatement ps = null;
        try {
        	con = DBResource.getConnection();
			vo1 = PublicAccess.getJmbaZbVO(con, bo.getBasqwsh());
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String tjsj = df.format(date);
			StringBuffer sb = new StringBuffer();
			List list = new ArrayList();
			if (vo1.getBasqwsh() == null || "".equals(vo1.getBasqwsh())) {
				vo1 = new JmbaZbVO();
				vo1.setBand(bo.getBand());
				vo1.setBasqbh(bo.getBasqbh());
				vo1.setBasqwsh(bo.getBasqwsh());
				vo1.setJmbasxdm(bo.getJmbasxdm());
				vo1.setLrrq(new SimpleDateFormat("yyyy-MM-dd")
						.format(new Date()));
				sb = new StringBuffer();

				sb.append("select * ");
				sb
						.append(" from sfdb.sf_jl_qysdsjmsba_19 t,sfdb.sf_jl_qysdsjmsbajl c where  t.band='"
								+ (Integer.parseInt(bo.getBand()) - 1) + "' ");
				sb
						.append(" and t.basqwsh=c.basqwsh and c.sqzt = '4'  and  t.jsjdm='"
								+ ud.getYhid() + "' and rownum=1 ");
				System.out.println("===query wn sql==" + sb.toString());
				ps = con.prepareStatement(sb.toString());
				rs = ps.executeQuery();

				while (rs.next()) {
					Jmba19VO mxvo = getBamxvo(rs);
					mxvo.setXH("");
					list.add(mxvo);
				}

                if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }

				vo1.setQysdsjmba(list);
				return vo1;
			}
			sb=new StringBuffer();
			sb.append("select * " );//t.SFYCJRMC,t.SFYSHBX,t.SFYLDHT,t.SFYCJRZM,t.SFYGZZM,
//					sb.append(" decode(t.SFYCJRMC,'0','��','1','��','') SFYCJRMCmc ,t.QTZL, ");
//            sb.append(" decode(t.SFYSHBX,'0','��','1','��','') SFYSHBXmc , ");
//            sb.append(" decode(t.SFYLDHT,'0','��','1','��','') SFYLDHTmc , ");
//            sb.append(" decode(t.SFYCJRZM,'0','��','1','��','') SFYCJRZMmc , ");
//            sb.append(" decode(t.SFYGZZM,'0','��','1','��','') SFYGZZMmc  ");

            sb.append(" from sfdb.sf_jl_qysdsjmsba_19 t where  basqwsh='"+bo.getBasqwsh()+"'");


			if (bo.getXh()!=null && !bo.getXh().equals("")){
				sb.append(" and a.xh="+bo.getXh()+" ");
			}

			System.out.println("===query sql=="+sb.toString());
			ps = con.prepareStatement(sb.toString());
			rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {
				 Jmba19VO mxvo=getBamxvo(rs);
	             list.add(mxvo);
			}
			vo1.setQysdsjmba(list);
			System.out.println("query mx done");
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
        } catch (Exception ex) {
        	throw ExceptionUtil.getBaseException(ex);
        } finally {
        	DBResource.destroyConnection(con);
        }

        return vo1;
    }

	/**
	 * ʹ��keyΪJmsMapConstant.SBRQȡ�걨���� ͨ�����������͵�ǰ����ȡ�ñ������걨���ݣ����£�
	 * ��keyΪJmsMapConstant.LIST_JMYSB�ű������걨����
	 *
	 * @param data
	 * ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
    private Object doSave(VOPackage vo) throws BaseException {
    	//System.out.println("////////////doSave");
    	List list=new ArrayList();
        DzyjHelper dh = new DzyjHelper();
		Map hm=(Map)vo.getData();
        JmbaVO bavo = (JmbaVO)hm.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
		DzyjsjVO dzyj = (DzyjsjVO) hm.get(CAcodeConstants.CA_MAPKEY_VO_DZYJSJ);
		JmbaZbVO vo1 = (JmbaZbVO)bavo.getJmsbajl().get(0);

		Jmba19VO mxvo=(Jmba19VO)vo1.getQysdsjmba().get(0);
		UserData ud = (UserData)vo.getUserData();
		Connection conn = null;
		PreparedStatement ps = null;
		com.syax.bjtax.shenbao.util.QysdsUtil qysdsUtil = new com.syax.bjtax.shenbao.util.QysdsUtil();
		System.out.println("into Processor save");
        try {
        	conn = DBResource.getConnection();
			JmbaZbVO vo2=PublicAccess.getJmbaZbVO(conn, vo1.getBasqwsh());
			//conn.setHoldability(1);
			//����
			if (vo2.getBasqwsh()==null || "".equals(vo2.getBasqwsh())){
				PublicAccess.saveZb(conn,bavo,ud);
			}
			String sql = "";
			if(mxvo.getXH()!=null && !mxvo.getXH().equals("")){
				sql = "UPDATE sfdb.sf_jl_qysdsjmsba_19 SET "+
				" WHSYDWLXDM='"+mxvo.getWHSYDWLXDM()+"',JMSE='"+mxvo.getJMSE()+

				"',LRR='"+ud.getYhid()+"',LRRQ=sysdate where xh ='"+mxvo.getXH()+"'";
				}else{
					String xh = qysdsUtil.getSequence();
				sql = "INSERT INTO sfdb.sf_jl_qysdsjmsba_19 (XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,WHSYDWLXDM,JMSE,CJR,CJRQ,LRR,LRRQ) VALUES('"+
				xh+"','" +vo1.getBasqwsh()+"','"+
				bavo.getNsrxx().getJsjdm()+"','"+
				vo1.getBand()+"','"+
				bavo.getNsrxx().getSwjgzzjgdm()+"','"+
				mxvo.getWHSYDWLXDM()+"','"+
				mxvo.getJMSE()+"','"+ud.getYhid()+"',sysdate,'"+ud.getYhid()+"',sysdate)";
			}
			System.out.println("02Processor===doSave===sql="+sql);
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			if (ps != null) {
				ps.close();
			}


        } catch (Exception ex) {
        	throw ExceptionUtil.getBaseException(ex);
        } finally {
        	DBResource.destroyConnection(conn);
        }
    	return list;
    }


	/**
	 * ʹ��keyΪJmsMapConstant.SBRQȡ�걨���� ͨ�����������͵�ǰ����ȡ�ñ������걨���ݣ����£�
	 * ��keyΪJmsMapConstant.LIST_JMYSB�ű������걨����
	 *
	 * @param data
	 * ҵ�����
	 * @return Map
	 * @throws com.ttsoft.framework.exception.BaseException
	 */
    private Map doCommit(Map data) throws BaseException {
    	System.out.println("////////////doSave");
        Map map = new HashMap();
        //ȡ���ݹ��������ݶ���
        String BASQWSH = "";
        Jmba19VO vo = null;
        NsrxxVO nsrvo = null;
        String BAND = "";
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        boolean flag = false;
        try {

        	JmbaVO jmbavo = (JmbaVO)data.get(CAcodeConstants.CA_MAPKEY_VO_XMLVO);
        	JmbaZbVO zbvo = (JmbaZbVO)jmbavo.getJmsbajl().get(0);
        	BASQWSH = zbvo.getBasqwsh();
        	BAND = zbvo.getBand();
            //System.out.println("BASQWSH = "+BASQWSH);
        	vo = (Jmba19VO)((JmbaZbVO)jmbavo.getJmsbajl().get(0)).getQysdsjmba().get(0);
        	nsrvo = jmbavo.getNsrxx();
        	//������BASQWSH��ѯ����������ݣ�����¼�¼�����û�м�¼�������һ��������
            String sql = "select * from SFDB.SF_JL_QYSDSJMSBA_19 where basqwsh = '"+BASQWSH+"'";
            con = DBResource.getConnection();
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while(rs.next()) {
            	flag = true;
            	System.out.println("��ѯ����ִ�гɹ�������ȡ������!!");
            }
            //û�м�¼����£�����һ������
            if (!flag){
            	//1 ȡ���
            	QysdsUtil obj = new QysdsUtil();
            	String xh = obj.getSequence();
            	//System.out.println("����һ������");
            	sql = "insert into SFDB.SF_JL_QYSDSJMSBA_19(XH,BASQWSH,JSJDM,BAND,SWJGZZJGDM,WHSYDWLXDM,SFYQYMD,SFYZZFAPFH,SFBLGSYYZZ,SFYZXSYDWZM,SFCJSHBX,SFYBGZBJG,JMSE,QTZL,CJR,CJRQ,LRR,LRRQ) " +
            			" values('" + xh + "'" +
            			",'" + BASQWSH + "'" +
            			",'" + nsrvo.getJsjdm() + "'" +
            			",'" + BAND + "'" +
            			",'" + nsrvo.getSwjgzzjgdm() + "'" +
            			",'" + vo.getWHSYDWLXDM() + "'" +
            			",'" + vo.getSFYQYMD() + "'" +
            			",'" + vo.getSFYZZFAPFH() + "'" +
            			",'" + vo.getSFBLGSYYZZ() + "'" +
            			",'" + vo.getSFYZXSYDWZM() + "'" +
             			",'" + vo.getSFCJSHBX() + "'" +
             			",'" + vo.getSFYBGZBJG() + "'" +
             			"," + vo.getJMSE() +
             			",'" + vo.getQTZL() + "'" +
             			",'" + nsrvo.getJsjdm() + "'" +
             			",sysdate" +
             			",'" + nsrvo.getJsjdm() + "'" +
             			",sysdate)";
            	//System.out.println(sql);
            	st.execute(sql);
            	//System.out.println("�������ִ�гɹ�!!");
            }else{
            	//System.out.println("ִ�и��²���");
            	//ִ�и��²���
            	sql="update  sfdb.sf_jl_qysdsjmsba_19 " +
            			" set WHSYDWLXDM='" + vo.getWHSYDWLXDM()+"'" +
            			" ,SFYQYMD='" + vo.getSFYQYMD() + "'" +
            			" ,SFYZZFAPFH='" + vo.getSFYZZFAPFH() + "'" +
            			" ,SFBLGSYYZZ='" + vo.getSFBLGSYYZZ() + "'" +
            			" ,SFYZXSYDWZM='" + vo.getSFYZXSYDWZM() + "'" +
            			" ,SFCJSHBX='" + vo.getSFCJSHBX() + "'" +
            			" ,SFYBGZBJG='" + vo.getSFYBGZBJG() + "'" +
            			" ,JMSE=" + vo.getJMSE() +
            			" ,QTZL='" + vo.getQTZL() + "'" +
            			" ,LRR='" + nsrvo.getJsjdm() + "'" +
            			" ,LRRQ=sysdate where BASQWSH='" + BASQWSH + "'";
            	//System.out.println(sql);
            	st.execute(sql);
            	//System.out.println("���²���ִ�гɹ�!!");
            }
            //����������Ϣ
            QysdsUtil obj_update = new QysdsUtil();
            obj_update.updateSqzt(BASQWSH, "3", nsrvo.getJsjdm());
            map.put("Jmba19VO",vo);
            map.put("JmbaZbVO",PublicAccess.getJmbaZbVO(con,BASQWSH));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (Exception exx) {
            }
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception exx) {
            }
        }
        return map;
    }


    /*
     * ȡϵͳʱ��
     */
    public static String getSysTimeNow() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        return sdf.format(date);
    }
    /*
     * ȡϵͳʱ��
     */
    public static String getBand() {
    	String date_str = "";
    	int year = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        date_str = sdf.format(date);
        date_str = date_str.substring(0,4);
        year = Integer.parseInt(date_str);
        return String.valueOf(year-1);
    }
}

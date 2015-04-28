/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.gzwh.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsx;
import com.ttsoft.bjtax.shenbao.model.domain.Gzsxls;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.gzwh.web.GzsxwhForm;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.smsb.util.TinyTools;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��֪����ά��</p>
 * @author �������飭��ʯ�ҷ�
 * @version 1.1
 */
public class GzsxwhProcessor
    implements Processor
{

    /**
     * ҳ��Ԫ���б�����
     */
    private String names[] =
        {
        "jsjdm", "gzsxfcrq", "nsrmc", "gzsxksrq",
        "gzsxjsrq", "gzsxnr",
        "gzsxfcdw", "gzsxsdbz", "fzcbs", "bz", "lrr",
        "lrrdm", "swjgzzjgdm",
        "swjgzzjgdm2", "djzclxdm", "gjbzhydm", "cjrq",
        "lrrq", "qxdm"};

    /**
     * Ĭ�Ϲ��캯��
     */
    public GzsxwhProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
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
            case CodeConstant.SMSB_UPDATEACTION:
                result = doUpdate(vo);
                break;
            case CodeConstant.SMSB_READERACTION:
                result = doGetNsrmc(vo);
                break;
            case CodeConstant.SMSB_DELETEALLACTION:
                result = doDeleteAll(vo);
                break;
            default:
                throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
        return result;
    }

    /**
     * ҳ���ʼ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doShow (VOPackage vo)
        throws BaseException
    {
    	System.out.println("GzsxwhProcessor.doShow()");
    	
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        Connection conn = null;
        try
        {
        	
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            List list = this.getPh(vo, db);
            //���õ��������б�
            gf.setPhList( (ArrayList) list);

            return gf;
        }
        catch (SystemException ex)
        {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * �õ��ò���Ա���еĵ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @param db ���ݿ���ʶ���
     * @return ��ǰ����Ա�����е�������list
     */
    private List getPh (VOPackage vo, SfDBAccess db)
    {
        UserData ud = vo.getUserData();
        List ret = new ArrayList();
        try
        {
        	System.out.println(InterfaceDj.getQxdm(ud)+"##############");
        	
           /* ResultSet rs = db.querySQL(
                "select distinct ph from sbdb.SB_JL_GZSX where qxdm='" +
                InterfaceDj.getQxdm(ud) + "' and ph is not null");*/
            
        	//�ڸ�֪����ά��ģ����,Ϊ'��������뵼�뷽ʽ'��ѡ����������˰���lrrq��������(2014��7��14��)
        	ResultSet rs = db.querySQL(
                    "select distinct ph,lrrq from sbdb.SB_JL_GZSX where qxdm='" +
                    InterfaceDj.getQxdm(ud) + "' and ph is not null order by lrrq desc");
            
            while (rs.next()) {
                Gzsx temp = new Gzsx();
                temp.setPh(rs.getString("ph"));
                ret.add(temp);
            }
            return ret;
        }
        catch (Exception ex) {
            return ret;
        }

    }

    /**
     * ȡ���ƺ�̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��˰������
     * @throws BaseException
     */
	private Object doGetNsrmc (VOPackage vo)
        throws BaseException
    {

        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        //���Ǽǽӿ�
        try
        {
            if (gf.getJsjdm() != null && (gf.getJsjdm().trim()).length() != 0)
            {
                /****�õ�����***/
                gf = (GzsxwhForm)this.doShow(vo);
                com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = new com.ttsoft.
                    bjtax.
                    dj.proxy.ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(gf.getJsjdm());
                SWDJJBSJ swdjjbsj = (SWDJJBSJ) ghdwMap.get("JBSJ");
                gf.setNsrmc(swdjjbsj.getNsrmc());
            }
        }
        catch (Exception e) {
            gf.setNsrmc("");
            e.printStackTrace();
            throw new ApplicationException("û�и���˰�˵ĵǼ���Ϣ!");
        }
        try
        {
            if (gf.getJsjdm() != null && (gf.getJsjdm().trim()).length() != 0) {
                com.ttsoft.bjtax.dj.proxy.ServiceProxy djProxy = 
                							new com.ttsoft.bjtax.dj.proxy.ServiceProxy();
                HashMap ghdwMap = djProxy.getDjInfo(gf.getJsjdm(),
                    vo.getUserData());
            }
        }
        catch (Exception e) {
            gf.setNsrmc("");
            e.printStackTrace();
            throw new ApplicationException("��û���㹻��Ȩ�޴���˼�������룡");
        }

        return gf;
    }

    /**
     * ��ѯ��̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        Connection conn = null;
        Gzsx g = new Gzsx(); //��֪�����ORMappingֵ����
        List qlist = null; //List of ��֪�����HashMap�������
        String sql = ""; //Case2��SQL���
        StringBuffer sqlBuffer = new StringBuffer();
        UserData userdata = vo.getUserData();

        gf.setLength(CodeConstant.GZ_PG_LENGTH); //����ҳ��
        //��ѯ����
        Vector v = new Vector();

        //*******Case1****** ѡ�񵥷���ѯģʽ����������������
		if (gf.getChooseTypeRadio().equals("1") 
				&& gf.getJsjdm() != null
				&& !gf.getJsjdm().equals("")) {

            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                             + " FROM SBDB.SB_JL_GZSX WHERE ");
            //��֪������������
            sqlBuffer.append(" FZCBS = '" + gf.getGzlx() + "' ");

            //�����������������
            //���������������� == *
            gf.setJsjdm(gf.getJsjdm().trim());
            if (gf.getJsjdm() != null && (gf.getJsjdm()).equals("*"))
            {
                //doNothing
            }
            else
            {
                sqlBuffer.append(" AND JSJDM= '" + gf.getJsjdm() + "' ");
            }

            //*******Case2******* ѡ��Ⱥ����ѯģʽ
        }
        else if (gf.getChooseTypeRadio().equals("2"))
        {
            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq  "
                             +
                             " FROM SBDB.SB_JL_GZSX WHERE JSJDM= '*' ");
            //��֪������������
            sqlBuffer.append(" AND FZCBS = '" + gf.getGzlx() + "' ");

            //Ⱥ����������
            String multiSendString = getMultiSendString(gf);
            sqlBuffer.append(multiSendString);

        }
        else if (gf.getChooseTypeRadio().equals("3"))
        {
            sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                             + " FROM SBDB.SB_JL_GZSX WHERE ");
            sqlBuffer.append("ph='" + gf.getPh() + "' ");
        }
        else
        {
            return gf;
        }
        
        //��������֪��ʼ����
        if (gf.getGzqsrq() != null && !gf.getGzqsrq().equals("")) {
            gf.setGzqsrq(gf.getGzqsrq().trim());
           
            sqlBuffer.append(" AND GZSXKSRQ >= TO_DATE('" + gf.getGzqsrq() +
                             "','yyyyMMdd') ");            
        }
        //��������֪��������
        if (gf.getGzjzrq() != null && !gf.getGzjzrq().equals(""))
        {
            gf.setGzjzrq(gf.getGzjzrq().trim());
            sqlBuffer.append(" AND GZSXJSRQ <= TO_DATE('" + gf.getGzjzrq() +
                             "','yyyyMMdd') ");
        }
        
        if (!gf.getChooseTypeRadio().equals("3"))
        {
            //��������ļ������������Ȩ����֤
            //�����������Ȩ�޿���
            sqlBuffer.append(getDqjsCondition(userdata.getSsdwdm(), "SELECT"));
        }
        //sqlBuffer.append("ORDER BY GZSXKSRQ ");
        /*******************************************/
        /**              Shi Yanfeng              **/
        /*******************************************/
        sqlBuffer.append("order by cjrq desc ");

        sql = sqlBuffer.toString();
        if (!sql.equals(""))
        {
            SfHashList sfHashList = null;
            try
            {
                conn = SfDBResource.getConnection();
                
                /**20040311 Shi Yanfeng ��ȡ�����б�**/
                SfDBAccess db = new SfDBAccess(conn);
                List list = this.getPh(vo, db);
                
                //���õ��������б�
                gf.setPhList( (ArrayList) list);

                SfDBUtils sfDB = new SfDBUtils(conn);
                sfHashList = sfDB.getData(sql, gf.getLength(), gf.getPgNum());
                qlist = sfHashList.getRecords();
                //��ɾ�����һҳ���м�¼�����˵���һҳ������ǰ���һҳ
                if (gf.getPgNum() > 1 && qlist.size() < 1)
                {
                    gf.setPgNum(gf.getPgNum() - 1);
                    sfHashList = sfDB.getData(sql, gf.getLength(), gf.getPgNum());
                    qlist = sfHashList.getRecords();
                }
                //����һЩ��Ҫ�ĸ�ʽת��
                if (qlist.size() > 0)
                {
                    ArrayList dataList = new ArrayList();
                    for (int i = 0; i < qlist.size(); i++)
                    {
                        HashMap map = (HashMap) qlist.get(i);
                        //��ʱ��תΪ��׼��ʽ
                        map.put("gzsxksrq",
                                stringToStandTimeString( (String) map.get(
                            "gzsxksrq")));
                        map.put("gzsxjsrq",
                                stringToStandTimeString( (String) map.get(
                            "gzsxjsrq")));
                        //����������ʾ
                        String modifyIndex = null;
                        if (gf.getChooseTypeRadio().equals("1"))
                        { 
                        	//������ʽ
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }
                        else if (gf.getChooseTypeRadio().equals("2"))
                        {
                        	//Ⱥ����ʽ
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }
                        else if (gf.getChooseTypeRadio().equals("3"))
                        { 
                        	//���뷽ʽ
                            modifyIndex = (String) map.get("jsjdm") + "|"
                                          + (String) map.get("gzsxfcrq");
                        }

                        //������תΪ����
                        map.put("modifyIndex", modifyIndex);
                        if ( ( (String) map.get("fzcbs")).equals("0"))
                        {
                            map.put("fzcbs", "����");
                        }
                        else if ( ( (String) map.get("fzcbs")).equals("1"))
                        {
                            map.put("fzcbs", "�쳣");
                        }
                        dataList.add(map);
                    }
                    gf.setDataList(dataList);

                    //ȷ�����ҳ��
                    gf.setPgSum(sfDB.getMaxResultNum() % gf.getLength() == 0 ?
                                sfDB.getMaxResultNum() / gf.getLength() :
                                sfDB.getMaxResultNum() / gf.getLength() + 1);
                }
                else
                {
                    gf.setDataList(new ArrayList());
                    gf.setPgNum(0);
                    gf.setPgSum(0);
                    throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
                }

            }
            catch (Exception sqlex)
            {
                sqlex.printStackTrace();
                throw ExceptionUtil.getBaseException(sqlex);
            }
            finally
            {
                SfDBResource.freeConnection(conn);
            }

        }

        return gf;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSave (VOPackage vo)
        throws BaseException
    {
        return null;
    }

    /**
     * ɾ���ļ�����ĸ�֪����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
	private Object deleteAllDr(VOPackage vo) throws BaseException {
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		Connection conn = null;
		try {
			conn = SfDBResource.getConnection();			
			SfDBAccess db = new SfDBAccess(conn);			
			db.querySQL("delete from sbdb.sb_jl_gzsx where qxdm='"
					+ InterfaceDj.getQxdm(vo.getUserData()) + "' and ph='"
					+ gf.getPh() + "'");
			
			return gf;
		} catch (SystemException ex) {
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

	}

    /**
     * ɾ��ȫ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDeleteAll (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        List qlist = null; //List of ��֪�����HashMap�������
        String sql = ""; //Case2��SQL���
        StringBuffer sqlBuffer = new StringBuffer();
        UserData userdata = vo.getUserData();

        try
        {

            //*******Case1****** ѡ�񵥷���ѯģʽ����������������
             if (gf.getChooseTypeRadio().equals("1")
                 && gf.getJsjdm() != null
                 && !gf.getJsjdm().equals(""))
             {

                 sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq "
                                  + " FROM SBDB.SB_JL_GZSX WHERE ");
                 //��֪������������
                 sqlBuffer.append(" FZCBS = '" + gf.getGzlx() + "' ");

                 //�����������������
                 //���������������� == *
                 gf.setJsjdm(gf.getJsjdm().trim());
                 if (gf.getJsjdm() != null && (gf.getJsjdm()).equals("*"))
                 {
                     //doNothing
                 }
                 else
                 {
                     sqlBuffer.append(" AND JSJDM= '" + gf.getJsjdm() + "' ");
                 }

                 //*******Case2******* ѡ��Ⱥ����ѯģʽ
             }
             else if (gf.getChooseTypeRadio().equals("2"))
             {

                 sqlBuffer.append("SELECT DISTINCT JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,cjrq  "
                                  +
                                  " FROM SBDB.SB_JL_GZSX WHERE JSJDM= '*' ");
                 //��֪������������
                 sqlBuffer.append(" AND FZCBS = '" + gf.getGzlx() + "' ");

                 //Ⱥ����������
                 String multiSendString = getMultiSendString(gf);
                 sqlBuffer.append(multiSendString);

             }
             else if (gf.getChooseTypeRadio().equals("3"))
             {
                 //ɾ�����е����֪
                 this.deleteAllDr(vo);
                 return this.doShow(vo);
             }
             else
             {
                 return gf;
             }

            //��������֪��ʼ����
            if (gf.getGzqsrq() != null && !gf.getGzqsrq().equals(""))
            {
                gf.setGzqsrq(gf.getGzqsrq().trim());
                sqlBuffer.append(" AND GZSXKSRQ >= TO_DATE('" + gf.getGzqsrq() +
                                 "','yyyyMMdd') ");
            }
            //��������֪��������
            if (gf.getGzjzrq() != null && !gf.getGzjzrq().equals(""))
            {
                gf.setGzjzrq(gf.getGzjzrq().trim());
                sqlBuffer.append(" AND GZSXJSRQ <= TO_DATE('" + gf.getGzjzrq() +
                                 "','yyyyMMdd') ");
            }
            if (!gf.getChooseTypeRadio().equals("3"))
            {
                //��������ļ������������Ȩ����֤
                //�����������Ȩ�޿���
                sqlBuffer.append(getDqjsCondition(userdata.getSsdwdm(),
                                                  "SELECT"));
            }
            sql = sqlBuffer.toString();
            if (!sql.equals(""))
            {
                SfHashList sfHashList = null;

                conn = SfDBResource.getConnection();
                SfDBUtils sfDB = new SfDBUtils(conn);
                sfHashList = sfDB.getData(sql);
                qlist = sfHashList.getRecords();
            }

            SfDBAccess da = new SfDBAccess(conn);
            /***�õ���������***/
            gf.setPhList( (ArrayList)this.getPh(vo, da));
            Calendar nowTime = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            //����ʱ��
            now.set(nowTime.get(Calendar.YEAR), nowTime.get(Calendar.MONTH),
                    nowTime.get(Calendar.DATE));
            for (int i = 0; i < qlist.size(); i++)
            {
                String strJsjdm = (String) ( (Map) qlist.get(i)).get("jsjdm");
                String strGzsxfcrq = (String) ( (Map) qlist.get(i)).get(
                    "gzsxfcrq");
                //ȥ������
                strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

                Vector gzV = new Vector();
                gzV.add(" JSJDM = '" + strJsjdm + "' ");
                gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq +
                        "','YYYY-mm-dd hh24:mi:ss') ");

                List sourceGzsxList = da.query(Gzsx.class, gzV);

                Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
                Calendar gzksrq = Calendar.getInstance();
                gzksrq.set(TinyTools.getYear(sourceGzsx.getGzsxksrq()),
                           TinyTools.getMonth(sourceGzsx.getGzsxksrq()),
                           TinyTools.getDay(sourceGzsx.getGzsxksrq()), 0, 0, 0);

                //����û���������֪��ʼ�����ڵ�ǰ����֮ǰ��������ʷ��
                //����ֱ��ɾ��
                if (gzksrq.after(now))
                {
                    //da.delete(sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }
                else
                {
                    //������ʷ��
                    List insertGzsxlsList = new ArrayList();
                    for (int j = 0; j < sourceGzsxList.size(); j++)
                    {
                        sourceGzsx = (Gzsx) sourceGzsxList.get(j);
                        Gzsxls gls = new Gzsxls();
                        BeanUtil.copyBeanToBean(names, sourceGzsx, gls);
                        insertGzsxlsList.add(gls);
                    }
                   //da.insert(insertGzsxlsList);
                   // da.delete(sourceGzsxList);
                    insertgzsx(conn,sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return gf;
    }

    /**
     * ɾ������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doDelete (VOPackage vo)
        throws BaseException
    {
        Connection conn = null;
        GzsxwhForm gf = (GzsxwhForm) vo.getData();
        String[] deleteCheckbox = gf.getDeleteCheckbox();
        try
        {
            Gzsx g = new Gzsx(); //��֪�����ORMappingֵ����
            ArrayList qlist = gf.getDataList(); //List of ��֪�����ORMappingֵ����
            conn = SfDBResource.getConnection();
            SfDBAccess da = new SfDBAccess(conn);
            /***�õ���������***/
            gf.setPhList( (ArrayList)this.getPh(vo, da));
            Calendar nowTime = Calendar.getInstance();
            Calendar now = Calendar.getInstance();
            //����ʱ��
            now.set(nowTime.get(Calendar.YEAR), nowTime.get(Calendar.MONTH),
                    nowTime.get(Calendar.DATE));
            for (int i = 0; i < deleteCheckbox.length; i++)
            {
                g = new Gzsx();
                String primaryKey = deleteCheckbox[i];
                String strJsjdm = primaryKey.substring(0,
                    primaryKey.indexOf("|"));
                String strGzsxfcrq = primaryKey.substring(primaryKey.indexOf(
                    "|") + 1,
                    primaryKey.length());
                //ȥ������
                strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

                Vector gzV = new Vector();
                gzV.add(" JSJDM = '" + strJsjdm + "' ");
                gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq +
                        "','YYYY-mm-dd hh24:mi:ss') ");

                List sourceGzsxList = da.query(Gzsx.class, gzV);

                Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);
                Calendar gzksrq = Calendar.getInstance();
                gzksrq.set(TinyTools.getYear(sourceGzsx.getGzsxksrq()),
                           TinyTools.getMonth(sourceGzsx.getGzsxksrq()),
                           TinyTools.getDay(sourceGzsx.getGzsxksrq()), 0, 0, 0);

//        gzksrq.set(sourceGzsx.getGzsxksrq().getYear() + 1900,
//                   sourceGzsx.getGzsxksrq().getMonth(),
//                   sourceGzsx.getGzsxksrq().getDate(), 0, 0, 0);

                //����û���������֪��ʼ�����ڵ�ǰ����֮ǰ��������ʷ��
                //����ֱ��ɾ��
                if (gzksrq.after(now))
                {
                    //da.delete(sourceGzsxList);
                	deletegzsx(conn,sourceGzsxList);
                }
                else
                {
                    //������ʷ��
                    List insertGzsxlsList = new ArrayList();
                    for (int j = 0; j < sourceGzsxList.size(); j++)
                    {
                        sourceGzsx = (Gzsx) sourceGzsxList.get(j);
                        Gzsxls gls = new Gzsxls();
                        BeanUtil.copyBeanToBean(names, sourceGzsx, gls);
                        insertGzsxlsList.add(gls);
                    }
                    //da.insert(insertGzsxlsList);
                    insertgzsx(conn,sourceGzsxList);
                    deletegzsx(conn,sourceGzsxList);
                }

            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            SfDBResource.freeConnection(conn);
        }

        return gf;

    }
    
    
    private void insertgzsx (Connection conn, List list)
    throws BaseException{
    	Statement sttest=null;
    	try {
    		for(int i=0;i<list.size();i++){
				Gzsx g=(Gzsx)list.get(i);
				sttest = conn.createStatement();
				
				/*��������*/
				String fcrq=TinyTools.Date2String(new Date(g.getGzsxfcrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*��������*/
				String cjrq=TinyTools.Date2String(new Date(g.getCjrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*¼������*/
				String lrrq=TinyTools.Date2String(new Date(g.getLrrq().getTime()),"yyyy-MM-dd HH:mm:ss");
				/*��ʼ����*/
				String ksrq=TinyTools.Date2String(new Date(g.getGzsxksrq().getTime()));
				/*��������*/
				String jsrq=TinyTools.Date2String(new Date(g.getGzsxjsrq().getTime()));
				
				String sql = "insert into sbdb.sb_jl_gzsx_ls(JSJDM,GZSXFCRQ,NSRMC,GZSXKSRQ,GZSXJSRQ,GZSXNR,GZSXFCDW,GZSXSDBZ,FZCBS,BZ,LRR,LRRDM,SWJGZZJGDM,DJZCLXDM,GJBZHYDM,SWJGZZJGDM2,CJRQ,LRRQ,QXDM,PH,GZSX_ID,GZSXNRBT) " +
				"values('"+g.getJsjdm()+"',to_date('"+fcrq+"','yyyy-MM-dd HH24:mi:ss'),'"+g.getNsrmc()+"',"+
				"to_date('"+ksrq+"','yyyy-MM-dd')"+","+
				"to_date('"+jsrq+"','yyyy-MM-dd')"+",'"+
				g.getGzsxnr()+"','"+
				g.getGzsxfcdw()+"','"+
				(g.getGzsxsdbz()==null?"":g.getGzsxsdbz())+"','"+
				g.getFzcbs()+"','"+
				g.getBz()+"','"+
				g.getLrr()+"','"+
				g.getLrrdm()+"','"+
				g.getSwjgzzjgdm()+"','"+
				g.getDjzclxdm()+"','"+
				g.getGjbzhydm()+"','"+
				g.getSwjgzzjgdm2()+"',to_date('"+cjrq+"','yyyy-MM-dd HH24:mi:ss'),to_date('"+lrrq+"','yyyy-MM-dd HH24:mi:ss')"+
				",'"+
				g.getQxdm()+"','"+
				(g.getPh()==null?"":g.getPh())+"','"+
				g.getGzsx_id()+"','"+
				g.getGzsxnrbt()+"')";
				//System.out.println("------sql"+sql);
				sttest.execute(sql);
                if(sttest!=null){
					try {
						sttest.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
			     }
			}			
    	   } catch (SQLException e1) {
			e1.printStackTrace();
			throw new ApplicationException("��֪���������ʷ��ʧ�ܣ�����ϵ����Ա\n��ϸ��Ϣ��"+e1.getMessage());
		}finally{
			if(sttest!=null){
				try {
					sttest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
    }
    
    
    /**
     * ɾ����֪����
     * @param conn
     * @param list
     * @throws BaseException
     */
    private void deletegzsx (Connection conn, List list)
 throws BaseException {
		Statement sttest = null;
		try {
			for (int i = 0; i < list.size(); i++) {
				Gzsx g = (Gzsx) list.get(i);
				sttest = conn.createStatement();
				String sql = "delete from sbdb.sb_jl_gzsx t where t.jsjdm='"
						+ g.getJsjdm()
						+ "' and t.gzsxfcrq=to_date('"
						+ g.getGzsxfcrq().toString().substring(0,g.getGzsxfcrq().toString().length() - 2)
						+ "','YYYY-mm-dd hh24:mi:ss')";
				
				sttest.execute(sql);
				if (sttest != null) {
					try {
						sttest.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			throw new ApplicationException("��֪����ɾ��ʧ�ܣ�����ϵ����Ա\n��ϸ��Ϣ��"
					+ e1.getMessage());
		} finally {
			if (sttest != null) {
				try {
					sttest.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    /**
     * �޸Ĵ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
	private Object doUpdate (VOPackage vo)  throws BaseException {
		Connection conn = null;
		GzsxwhForm gf = (GzsxwhForm) vo.getData();
		try {
			conn = SfDBResource.getConnection();
			SfDBAccess da = new SfDBAccess(conn);
			/*** �õ��������� ***/
			gf.setPhList((ArrayList) getPh(vo, da));
			String primaryKey = gf.getModifyIndex();
			String strJsjdm = primaryKey.substring(0, primaryKey.indexOf("|"));
			String strGzsxfcrq = primaryKey.substring(primaryKey.indexOf("|") + 1, primaryKey.length());
			// ȥ������
			strGzsxfcrq = strGzsxfcrq.substring(0, strGzsxfcrq.length() - 2);

			Vector gzV = new Vector();
			gzV.add(" JSJDM = '" + strJsjdm + "' ");
			gzV.add(" GZSXFCRQ = to_date('" + strGzsxfcrq
					+ "','YYYY-mm-dd hh24:mi:ss') ");
			List sourceGzsxList = da.query(Gzsx.class, gzV);
			Gzsx sourceGzsx = (Gzsx) sourceGzsxList.get(0);

			// ���ݳ�ʼ�����޸ģ�
			gf.setMxJsjdm(sourceGzsx.getJsjdm());
			gf.setMxNsrmc(sourceGzsx.getNsrmc());
			gf.setMxGzlx(sourceGzsx.getFzcbs());
			gf.setMxGzqsrq(stringToStandTimeString(sourceGzsx.getGzsxksrq()
					.toString()));
			gf.setMxGzjzrq(stringToStandTimeString(sourceGzsx.getGzsxjsrq()
					.toString()));
			gf.setMxGzsxxxxx(sourceGzsx.getGzsxnr());
			if (!sourceGzsx.getJsjdm().equals("*")) {
				gf.setMxChooseTypeRadio("1");
			} else if (sourceGzsx.getJsjdm().equals("*")) {
				gf.setMxChooseTypeRadio("2");
				if (sourceGzsxList.size() == 1) {
					gf.setMxJhfs("0"); // ��Ϸ�ʽ���룩
					gf.setMxQylx(sourceGzsx.getDjzclxdm());
					gf.setMxHylb(sourceGzsx.getGjbzhydm());
					gf.setMxDqjs(sourceGzsx.getSwjgzzjgdm2());
				} else if (sourceGzsxList.size() > 1) {
					gf.setMxJhfs("1"); // ��Ϸ�ʽ����
					for (int i = 0; i < sourceGzsxList.size(); i++) {
						sourceGzsx = (Gzsx) sourceGzsxList.get(i);
						if (!sourceGzsx.getDjzclxdm().equals("0")) {
							gf.setMxQylx(sourceGzsx.getDjzclxdm());
						}
						if (!sourceGzsx.getGjbzhydm().equals("0")) {
							gf.setMxHylb(sourceGzsx.getGjbzhydm());
						}
						if (!sourceGzsx.getSwjgzzjgdm2().equals("0")) {
							gf.setMxDqjs(sourceGzsx.getSwjgzzjgdm2());
						}
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw ExceptionUtil.getBaseException(ex);
		} finally {
			SfDBResource.freeConnection(conn);
		}

		return gf;

	}

    /**
     * �������ִ�(format:yyyyMMdd eg. 20030611)ת��ΪCalendar������
     * @param strDate �����ַ���
     * @return Calendar������
     */
    private Calendar stringToCalendar (String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(4, 6)) - 1;
        int date = Integer.parseInt(strDate.substring(6, 8));
        Calendar c = Calendar.getInstance();
        c.set(year, month, date);
        
        return c;
    }

    /**
     * �������ִ�(format:yyyy-MM-dd hh:MI:ss.ms  eg.2003-10-20 11:11:01.0)
     * ת��Ϊ��׼�����ִ�(format:yyyyMMdd eg. 20030611)
     * @param strDate �����ַ���
     * @return �ַ�������
     */
    private String stringToStandTimeString (String strDate)
    {
        int year = Integer.parseInt(strDate.substring(0, 4));
        int month = Integer.parseInt(strDate.substring(5, 7));
        int date = Integer.parseInt(strDate.substring(8, 10));
        String strMonth = "";
        String strDay = "";
        if (month < 10)
        {
            strMonth = "0" + month;
        }
        else
        {
            strMonth = "" + month;
        }
        if (date < 10)
        {
            strDay = "0" + date;
        }
        else
        {
            strDay = "" + date;
        }
        return "" + year + strMonth + strDay;
    }

    /**
     * �õ�Ⱥ������sql
     * @param gf ��ǰForm����
     * @return String Ⱥ������sql
     */
    private String getMultiSendString (GzsxwhForm gf)
    {
        StringBuffer sqlBuffer = new StringBuffer();
        //Ⱥ����������
        if (gf.getJhfs().equals("0"))
        { //��Ϸ�ʽ:"��"
            if (gf.getQylx().equals("0") &&
                gf.getHylb().equals("0") &&
                gf.getDqjs().equals("0"))
            {
                //������
            }
            else
            {
                if (!gf.getQylx().equals("0"))
                {
                    sqlBuffer.append(" AND DJZCLXDM = '" + gf.getQylx() + "' ");
                }
                if (!gf.getHylb().equals("0"))
                {
                    sqlBuffer.append(" AND GJBZHYDM = '" + gf.getHylb() + "' ");
                }
                if (!gf.getDqjs().equals("0"))
                {
                    sqlBuffer.append(" AND SWJGZZJGDM2 = '" + gf.getDqjs()
                                     + "' ");
                }
            }
        }
        else if (gf.getJhfs().equals("1"))
        { //��Ϸ�ʽ:"��"
            if (gf.getQylx().equals("0") &&
                gf.getHylb().equals("0") &&
                gf.getDqjs().equals("0"))
            {
                //������
            }
            else
            {
                sqlBuffer.append(" AND ( ");
                String tempSql = new String();
                if (!gf.getQylx().equals("0"))
                {
                    tempSql = tempSql + " DJZCLXDM = '" + gf.getQylx() +
                              //"' AND GJBZHYDM = '0' AND SWJGZZJGDM2='0' OR ";
                              "'  OR ";
                }
                if (!gf.getHylb().equals("0"))
                {
                    tempSql = tempSql + " GJBZHYDM = '" + gf.getHylb() +
                              //"' AND DJZCLXDM = '0' AND SWJGZZJGDM2='0' OR ";
                              "'  OR ";
                }
                if (!gf.getDqjs().equals("0"))
                {
                    tempSql = tempSql + " SWJGZZJGDM2 = '" + gf.getDqjs() +
                              //"' AND DJZCLXDM = '0' AND GJBZHYDM='0' OR ";
                              "'  OR ";
                }
                tempSql = tempSql.substring(0, tempSql.length() - 3);
                sqlBuffer.append(tempSql + ") ");
            }
        }
        return sqlBuffer.toString();
    }

    /**
     * �õ���ͬ����ĵ�����������
     * @param userSsdwdm �û�������λ����
     * @param type �������
     * @return ��������
     * @throws BaseException
     */
    public String getDqjsCondition (String userSsdwdm, String type)
        throws
        BaseException
    {

        String selectSql = null;
        String deleteSql = null;
        try
        {
            if (userSsdwdm.startsWith("90"))
            {
                //�о֣��޸�������
                selectSql = "";
                deleteSql = "";
            }
            else if (userSsdwdm.endsWith("00"))
            {
                //�־֣��ɹ�Ͻ���־ֺ����ڱ��־����е���
                selectSql = " AND SUBSTR(SWJGZZJGDM,1,2)='"
                            + userSsdwdm.substring(0, 2) +
                            "' ";
                deleteSql = " AND SUBSTR(A.SWJGZZJGDM,1,2)='" +
                            userSsdwdm.substring(0, 2) + "' "; ;
            }
            else
            {
                //����ֻ�ܹ�Ͻ����
                selectSql = " AND SWJGZZJGDM='" + userSsdwdm + "' ";
                deleteSql = " AND A.SWJGZZJGDM='" + userSsdwdm + "' ";
            }
            if (type.equals("SELECT"))
            {
                return selectSql;
            }
            else
            {
                return deleteSql;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
    }

}

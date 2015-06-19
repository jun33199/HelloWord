package com.ttsoft.bjtax.smsb.gghsb.processor;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003-2004�����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����.</p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 * @version 1.0
 */

import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.gghsb.web.KkxxcxForm;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.common.model.UserData;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;

import java.util.*;

/**
 * <p>Title: ������˰��������ϵͳ--���幤�̻�˰�����չ���</p>
 * <p>Description: �ۿ���Ϣ��ѯProcessor</p>
 * @author not attributable
 * @version 1.0
 */
public class KkxxcxProcessor
    implements Processor {

    private static String KEY_QUERY_SQL="querySql";
    private static String KEY_QUERY_TITLE="queryTitle";
    private static String KEY_QUERY_KEY="queryKey";
    private static String KEY_SUM_SQL="sumSql";
    private static String KEY_SUM_TITLE ="sumTitle";
    private static String KEY_SUM_KEY="sumKey";

    public KkxxcxProcessor() {
    }

    /**
     * ҳ��Ԫ���б�����
     */


    public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
        BaseException {
        Object result = null;
        if (vo == null) {
            throw new NullPointerException(" VOpackage is null ");
        }

        switch (vo.getAction()) {
            case CodeConstant.SMSB_SHOWACTION:
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            case CodeConstant.SMSB_TOEXCELACTION:
                result = doSaveExcel(vo);
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
    private Object doShow(VOPackage vo) throws BaseException {
        KkxxcxForm kf = (KkxxcxForm) vo.getData();
        Connection conn = null;
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            kf.setSwjsList(getSwjsList(db, userData));
            kf.setJxList(getJxList(db,userData));
            kf.setQxList(this.getQxList(db,userData));
            if(kf.getNd() == null || kf.getNd().equals("")){
                int year = TinyTools.getYear(new java.util.Date(System.currentTimeMillis()));
                kf.setNd(String.valueOf(year));
            }
            return kf;
        }
        catch (SystemException ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            SfDBResource.freeConnection(conn);
        }

    }

    /**
     * ��ȡ˰����б�
     * @param db
     * @param userData
     * @return
     * @throws BaseException
     */
    private ArrayList getSwjsList(SfDBAccess db, UserData userData) throws BaseException {
        ArrayList list = new ArrayList();
        try {
            //˰���
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
            StringBuffer sb  = new StringBuffer();
            sb.append(" SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG " );
            sb.append(" where substr(SWJGZZJGDM,3,2) <> '00' AND SWJGZZJGDM not like '90%' ");
            if(!"90".equals(iQxdm)){
                sb.append(" and SWJGZZJGDM like '"+iQxdm+"%' ");
            }
            sb.append(" order by SWJGZZJGDM ");
            LabelValueBean label = new LabelValueBean("*����˰����", "0");
            list.add(label);
            ResultSet rs  = db.querySQL(sb.toString());
            while(rs.next()){
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue((String)rs.getString("value"));
                bean.setLabel((String)rs.getString("descr"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }


    /**
     * ��ȡ�����б�
     * @param db
     * @param userData
     * @return
     * @throws BaseException
     */
    private ArrayList getQxList(SfDBAccess db, UserData userData) throws BaseException {
        ArrayList list = new ArrayList();
        try {
            //˰���
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
            StringBuffer sb  = new StringBuffer();
            sb.append(" SELECT SWJGZZJGDM value,SWJGZZJGMC descr FROM DMDB.GY_DM_SWJGZZJG " );
            if(!"90".equals(iQxdm)){
                sb.append(" where SWJGZZJGDM  = '"+iQxdm+"00' ");
            }else{
                sb.append(" where substr(SWJGZZJGDM,3,2) = '00' ");
            }
            sb.append(" order by SWJGZZJGDM ");
            ResultSet rs  = db.querySQL(sb.toString());
            while(rs.next()){
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue((String)rs.getString("value"));
                bean.setLabel((String)rs.getString("descr"));
                if("9000".equals((String)rs.getString("value"))){
                    list.add(0,bean);
                }else{
                    list.add(bean);
                }
            }
        } catch (SQLException e) {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }
    /**
     * ��ȡ˰����б�
     * @param db
     * @return
     * @throws BaseException
     */
    private ArrayList getJxList(SfDBAccess db,UserData userData) throws BaseException {
        ArrayList list = new ArrayList();
        try {
            //˰���
            StringBuffer sb  = new StringBuffer();
            String iQxdm = InterfaceDj.getQxdm(userData); //2λ���ش���
            sb.append(" select scjxdm as value, scjxmc as descr from dmdb.dj_dm_scjx " );
            if(!"90".equals(iQxdm)){
                sb.append(" where scjxdm like '"+iQxdm+"%'");
            }
            sb.append( " order by scjxdm ");
            LabelValueBean label = new LabelValueBean("*���н���", "0");
            list.add(label);
            ResultSet rs  = db.querySQL(sb.toString());
            while(rs.next()){
                LabelValueBean bean = new LabelValueBean("", "");
                bean.setValue((String)rs.getString("value"));
                bean.setLabel((String)rs.getString("descr"));
                list.add(bean);
            }
        } catch (SQLException e) {
            throw ExceptionUtil.getBaseException(e);
        }
        return list;
    }

    /**
     * ���ݵ�ǰ��ѯ�������Excel�ļ�
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doSaveExcel(VOPackage vo) throws BaseException {
        KkxxcxForm kf = (KkxxcxForm) vo.getData();
        Connection conn = null;
        try {
            Map sqlMap = getSql(kf);
            String querySql = (String)sqlMap.get(KEY_QUERY_SQL);
            String sumSql = (String)sqlMap.get(KEY_SUM_SQL);
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            SfHashList sfHashList = sfDB.getData(querySql);
            //�ۿ���Ϣ��HashMap�������
            ArrayList dataList = sfHashList.getRecords();
            //����һЩ��Ҫ�ĸ�ʽת��
            if (dataList.size() > 0) {
                kf.setDataListTitle((String[])sqlMap.get(KEY_QUERY_TITLE));
                kf.setDataListKey((String[])sqlMap.get(KEY_QUERY_KEY));
                kf.setDataList(DataListFormat(dataList));
            }else{
                kf.setDataList(new ArrayList());
            }
            //��ȡ����ֵ
            SfHashList sumHashList = sfDB.getData(sumSql);
            ArrayList sumList = sumHashList.getRecords();
            if(sumList.size()>0){
                kf.setSumList(sumList);
                kf.setSumListKey((String[])sqlMap.get(KkxxcxProcessor.KEY_SUM_KEY));
                kf.setSumListTitle((String[])sqlMap.get(KkxxcxProcessor.KEY_SUM_TITLE));
                Map map = (Map)sumList.get(0);
                kf.setYnjehj((String)map.get("ynjehj"));
                kf.setKkjehj((String)map.get("kkjehj"));
                kf.setJls((String)map.get("jls"));
                kf.setHshj((String)map.get("hshj"));
            }
            //��ȡ��������
            if("0".equals(kf.getJx())){
               kf.setJxmc("���н���");
            }else{
                kf.setJxmc(getJxMc(kf.getJx()));
            }
        } catch (SystemException e) {
            throw ExceptionUtil.getBaseException(e,e.getMessage());
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return kf;
    }

    /**
     * ��ѯ��̨����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        KkxxcxForm kf = (KkxxcxForm) vo.getData();
        Connection conn = null;
        List qlist = null; //List of �ۿ���Ϣ��HashMap�������
        Map sqlMap = getSql(kf);
        String querySql = (String)sqlMap.get(KEY_QUERY_SQL);
        String sumSql = (String)sqlMap.get(KEY_SUM_SQL);
        System.out.println("--Print--"+querySql);
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            UserData userData = vo.getUserData();
            kf.setSwjsList(getSwjsList(db, userData));
            kf.setJxList(getJxList(db,userData));
            kf.setQxList(getQxList(db,userData));
            if (querySql != null && !querySql.equals("")) {
                SfDBUtils sfDB = new SfDBUtils(conn);
                System.out.println("--print--:pgNum="+kf.getPgNum()+",length="+kf.getLength());
                SfHashList sfHashList = sfDB.getData(querySql, kf.getLength(), kf.getPgNum());
                qlist = sfHashList.getRecords();
                //��ɾ�����һҳ���м�¼�����˵���һҳ������ǰ���һҳ
                if (kf.getPgNum() > 1 && qlist.size() < 1) {
                    kf.setPgNum(kf.getPgNum() - 1);
                    System.out.println("--print--:pgNum="+kf.getPgNum()+",length="+kf.getLength());
                    sfHashList = sfDB.getData(querySql, kf.getLength(), kf.getPgNum());
                    qlist = sfHashList.getRecords();
                }
                //����һЩ��Ҫ�ĸ�ʽת��
                if (qlist.size() > 0) {
                    kf.setDataList(DataListFormat(qlist));
                    //ȷ�����ҳ��
                    kf.setPgSum(sfDB.getMaxResultNum() % kf.getLength() == 0 ?
                                sfDB.getMaxResultNum() / kf.getLength() :
                                sfDB.getMaxResultNum() / kf.getLength() + 1);
                    //����
                    SfHashList hashlist = sfDB.getData(sumSql);
                    List list = hashlist.getRecords();
                    if(list != null && list.size()>0){
                        Map map = (Map)list.get(0);
                        System.out.println("-------------hshj:"+(String)map.get("hshj"));
                        kf.setYnjehj((String)map.get("ynjehj"));
                        kf.setKkjehj((String)map.get("kkjehj"));
                        kf.setJls((String)map.get("jls"));
                        kf.setHshj((String)map.get("hshj"));
                    }
                }else {
                    kf.setDataList(new ArrayList());
                    kf.setPgNum(0);
                    kf.setPgSum(0);
//                    throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
                    return kf;
                }
             }
        }catch (Exception sqlex) {
            throw ExceptionUtil.getBaseException(sqlex);
        }finally {
            SfDBResource.freeConnection(conn);
        }
        return kf;
    }

    /**
     * ��ȡ�������Ʋ�ת�����ݸ�ʽ
     * @param qlist
     * @return
     */
    private ArrayList DataListFormat(List qlist) {
        ArrayList resultList = new ArrayList();
        for(int i=0;i<qlist.size();i++){
            Map map = (Map)qlist.get(i);
            map.put("skssksrq",convertDateFormat((String)map.get("skssksrq")));
            map.put("skssjsrq",convertDateFormat((String)map.get("skssjsrq")));
            map.put("kkrq",convertDateFormat((String)map.get("kkrq")));
            if(map.get("swjgzzjgdm") != null){
                String jc = CodeUtils.getCodeMapValue("ZHSB_SWJGZZJG", "swjgzzjgdm", (String)map.get("swjgzzjgdm"), "jc");
                map.put("wsjc",jc);
            }
            if(map.get("kkbz") != null){
                String kkbzmc = CodeUtils.getCodeBeanLabel("GTGSH_KKBZ", (String)map.get("kkbz"));
                map.put("kkbzmc",kkbzmc);
            }
            if(map.get("bcgyydm") != null){
                String bcggyymc = CodeUtils.getCodeBeanLabel("GTGSH_KKSBYY", (String)map.get("bcgyydm"));
                map.put("bcgyymc",bcggyymc);
            }
            if(map.get("yhdm") != null){
                String yhmc = CodeUtils.getCodeBeanLabel("DM_YH", (String)map.get("yhdm"));
                map.put("yhmc",yhmc);
            }
            if(map.get("yskmbm") != null){
                String yskmdm = CodeUtils.getCodeBeanLabel("DM_YSKM", (String)map.get("yskmbm"));
                map.put("yskmdm",yskmdm);
            }
            resultList.add(map);
        }
        return resultList;
    }

    /**
     * ת�����ڸ�ʽ
     * @param strDate
     * @return
     */
    private String convertDateFormat(String strDate) {
        if(strDate != null && !"".equals(strDate)){
            String dateStr = TinyTools.Date2String(TinyTools.stringToDate(strDate));
            return dateStr;
        }else{
            return "";
        }
    }


    /**
     * ���ɲ�ѯ��SQL���
     * @param kf
     * @return   key  =sumSql   ,value String ;key=sumTitle ,value String[] ,key = sumKey ,value String[]
     *           key = querySql ,value String ;key=queryTitle,value String[] ; key=queryKey ,value String[]
     */
    private Map getSql(KkxxcxForm kf){
        //��ѯ����
        StringBuffer sqlBuffer = new StringBuffer();
        String sumSql = null;
        StringBuffer whereBuffer = new StringBuffer();
        //���
        whereBuffer.append(" where nd = '"+kf.getNd()+"'");
        //�¶�
        whereBuffer.append(" and yd = '"+kf.getZq()+"'");

        //����
        if(!"9000".equals(kf.getQx())){
            whereBuffer.append(" and SWJGZZJGDM like '"+kf.getQx().substring(0,2)+"%'");
        }
        //˰����
        if(!"0".equals(kf.getSwjs())){
            whereBuffer.append(" and SWJGZZJGDM ='"+kf.getSwjs()+"'");
        }
        //����
        if(!"0".equals(kf.getJx())){
            whereBuffer.append(" and SCJXDM ='"+kf.getJx()+"'");
        }
        //���������
        if((!"".equals(kf.getJsjdm()) && (kf.getJsjdm()!=null))){
            whereBuffer.append(" and jsjdm ='"+kf.getJsjdm()+"'");
        }

        //UserData userdata = vo.getUserData();
        kf.setLength(CodeConstant.GZ_PG_LENGTH); //����ҳ��
        if("0".equals(kf.getKkbz())){//��ѯ�ۿ���Ϣ
            sqlBuffer.append(" select jsjdm,nsrmc,null as kkrq,'' as bcgyymc,szsmmc,sjje,'' as rkje,zh,skssksrq, skssjsrq,ysjcmc,jkpzh,swjgzzjgdm,yhdm,yskmdm as yskmbm,szsmdm,");
            //˰������
            sqlBuffer.append("(select t.szsmmc from DMDB.SB_DM_SZSM t where t.szsmdm = substr(t2.szsmdm,1,2)) as szmc,");
            // ��˰����ϵ�绰
            //sqlBuffer.append(" (select concat(ZCDZLXDH,concat(',',JYDZLXDM))  from DJDB.V_DJSB_JBSJ t1 where t2.jsjdm = t1.jsjdm ) as nsrlxdh,");
            sqlBuffer.append(" JYDZLXDM as nsrlxdh,");
            //˰�������
            //sqlBuffer.append(" (select WSJC from DMDB.GY_DM_SWJGZZJG t1 where t1.swjgzzjgdm =t2.SWJGZZJGDM )as wsjc,");
            sqlBuffer.append(" '' as wsjc,");
            //�ۿ��־
            //sqlBuffer.append(" (select KKBZMC from DMDB.SF_DM_GTGSH_KKBZ t1 where t1.KKBZDM = '00') as kkbzmc,");
            sqlBuffer.append(" '' as kkbzmc,");
            //��������
            //sqlBuffer.append(" (select yhmc from DMDB.GY_DM_YH t1 where t1.yhdm = t2.yhdm) as yhmc,");
            sqlBuffer.append(" '' as yhmc,");
            //Ԥ���Ŀ����
            //sqlBuffer.append(" (select yskmmc from DMDB.KJ_DM_YSKM t1 where t1.yskmdm = t2.yskmdm and t1.nd = t2.nd ) as  yskmdm,");
            sqlBuffer.append(" '' as  yskmdm,");
            //�ۿλ����
            sqlBuffer.append(" '' as kkdwmc");
            sqlBuffer.append(" from SFDB.SF_JL_GTGSH_YHKKXX t2 ");
            sumSql =" select '' as kkjehj, sum(sjje) as ynjehj,count(*) as jls, count(distinct jsjdm) as hshj from SFDB.SF_JL_GTGSH_YHKKXX ";
        }else{                      //��ѯ�ۿ���
            sqlBuffer.append(" select jsjdm,nsrmc,kkrq,szsmmc,sjje,rkje,zh,skssksrq, skssjsrq,ysjcmc,jkpzh ,swjgzzjgdm,kkbz,bcgyydm,yhdm,yskmdm as yskmbm,szsmdm,");
            //˰������
            sqlBuffer.append("(select t.szsmmc from DMDB.SB_DM_SZSM t where t.szsmdm = substr(t2.szsmdm,1,2)) as szmc,");
            // ��˰����ϵ�绰
            //sqlBuffer.append(" (select concat(ZCDZLXDH,concat(',',JYDZLXDM))  from DJDB.V_DJSB_JBSJ t1 where t2.jsjdm = t1.jsjdm ) as nsrlxdh,");
            sqlBuffer.append(" JYDZLXDM as nsrlxdh,");
            //˰�������
            //sqlBuffer.append(" (select WSJC from DMDB.GY_DM_SWJGZZJG t1 where t1.swjgzzjgdm =t2.SWJGZZJGDM )as wsjc,");
            sqlBuffer.append(" '' as wsjc,");
            //�ۿ��־   ����
            //sqlBuffer.append(" (select KKBZMC from DMDB.SF_DM_GTGSH_KKBZ t1 where t1.KKBZDM = t2.KKBZ) as kkbzmc,");
            sqlBuffer.append(" '' as kkbzmc,");
            //�ۿ�ɹ�ԭ��
            //sqlBuffer.append(" (select KKSBYYMC from  DMDB.SF_DM_GTGSH_KKSBYY t1 where t1.KKSBYYDM= t2.BCGYYDM) as bcgyymc,");
            sqlBuffer.append(" '' as bcgyymc,");
            //��������
            //sqlBuffer.append(" (select yhmc from DMDB.GY_DM_YH t1 where t1.yhdm = t2.yhdm) as yhmc,");
            sqlBuffer.append(" '' as yhmc,");
            //Ԥ���Ŀ����
            //sqlBuffer.append(" (select yskmmc from DMDB.KJ_DM_YSKM t1 where t1.yskmdm = t2.yskmdm and t1.nd = t2.nd ) as  yskmdm,");
            sqlBuffer.append(" '' as  yskmdm,");
            //�ۿλ����
            sqlBuffer.append(" (select NSRMC from DJDB.V_DJSB_JBSJ t1 where t1.JSJDM = t2.KKDWDM ) as kkdwmc");
            sqlBuffer.append(" from SFDB.SF_JL_GTGSH_YHKKHZXX t2 ");
            //�ۿ��ʶ
            if("2".equals(kf.getKkbz())){//�ۿ�ɹ���Ϣ
                whereBuffer.append(" and ( KKBZ='10' or KKBZ='20' )");
            }else if("3".equals(kf.getKkbz())){//�ۿ�ɹ���Ϣ
                whereBuffer.append(" and ( KKBZ='11' or KKBZ='21' )");
            }
            //�ۿ�ɹ�ԭ�����
            if(kf.getKkbcgyy() != null && !"0".equals(kf.getKkbcgyy())){
                whereBuffer.append(" and BCGYYDM='"+kf.getKkbcgyy()+"'");
            }
            sumSql =" select sum(rkje) as kkjehj, sum(sjje) as ynjehj,count(*) as jls, count(distinct jsjdm) as hshj from SFDB.SF_JL_GTGSH_YHKKHZXX ";
        }
        String sql = sqlBuffer.append(whereBuffer.toString()).toString();
        System.out.println("++++++++++++++++++++sql-------%%%%%%%%%%%%%%%%%%555---------:");
        System.out.println(sql);
        sumSql = sumSql.concat(whereBuffer.toString());
        String queryTitle[] = {"���������" ,"��˰������","��˰����ϵ�绰","˰�������","�ۿ��־","��˰����","�ۿ�ɹ�ԭ��","˰��˰Ŀ����","˰������","˰Ŀ����","��Ӧ��˰��","���пۿ���","��������","�����ʺ�","˰��������ʼ����","˰��������������","Ԥ���Ŀ����","Ԥ�㼶������","�ۿλ����","�ɿ�ƾ֤��"};
        String queryKey[] = {"jsjdm"     ,"nsrmc"     ,"nsrlxdh"   ,"wsjc",   "kkbzmc"   , "kkrq"  , "bcgyymc" ,     "szsmdm",    "szmc",   "szsmmc"    ,"sjje"      ,"rkje"  ,"yhmc" , "zh"     ,"skssksrq"  ,     "skssjsrq"  ,    "yskmdm"      ,"ysjcmc"    ,"kkdwmc"    ,  "jkpzh"};
        String sumTitle[] = {"��¼��","�ۿ���ϼ�","��Ӧ��˰��ϼ�","�����ϼ�"};
        String sumKey[] = {"jls","kkjehj","ynjehj","hshj"};
        Map sqlMap = new HashMap();
        sqlMap.put(KEY_QUERY_SQL,sql);
        sqlMap.put(KEY_QUERY_TITLE,queryTitle);
        sqlMap.put(KEY_QUERY_KEY,queryKey);
        sqlMap.put(KEY_SUM_SQL,sumSql);
        sqlMap.put(KEY_SUM_TITLE,sumTitle);
        sqlMap.put(KEY_SUM_KEY,sumKey);
        return sqlMap;
    }

    /**
     * ��ȡ�����б�
     * @param db
     * @return
     * @throws BaseException
     */
    private  String getJxMc(String jxdm) throws BaseException {
        String jxmc = null;
        Connection conn = null;
        try {
            conn = SfDBResource.getConnection();
            SfDBAccess db = new SfDBAccess(conn);
            //˰���
            StringBuffer sb  = new StringBuffer();
            sb.append(" select  scjxmc  from dmdb.dj_dm_scjx where scjxdm = '"+jxdm+"' ");
            ResultSet rs  = db.querySQL(sb.toString());
            if(rs.next()){
                jxmc = rs.getString("scjxmc");
            }
        } catch (SQLException e) {
            throw ExceptionUtil.getBaseException(e);
        }finally{
            SfDBResource.freeConnection(conn);
        }
        if(jxmc == null) jxmc ="";
        return jxmc;
    }

}
package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.processor;


/**
 * <p>Title: �۽���ҵ����˰-�۽���ҵ����˰����̨��Processer</p>
 *
 * <p>Description: ��ѯָ����˰�˵Ŀ۽���ҵ����˰���̨����Ϣ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author liuc
 * @version 1.0
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web.BadjbBO;
import com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.web.DztzBO;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


public class DztzProcessor implements Processor
{

    public DztzProcessor()
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
        if (vo == null) {
            throw new NullPointerException();
        }
        return doQueryDztz(vo);
    }

    /**
     * ��ѯ�۽���������Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQueryDztz(VOPackage vo) throws BaseException
    {
        System.out.println("========== dztzProcessor_doQueryDztz ==========");
        String jsjdm = (String) vo.getData();
        DztzBO bo = new DztzBO();
        getDztzxxByJsjdm(jsjdm, bo);
        if (bo.zscxjg()) {
            UserData ud = vo.getUserData();
            getDjxxByJsjdm(jsjdm, ud, bo);
        }
        return bo;
    }


    private void getDjxxByJsjdm(String jsjdm, UserData ud, DztzBO bo) throws
        BaseException
    {
        try
        {
            // ͨ���Ǽǽӿڻ�ȡ�Ǽ���Ϣ
            InterfaceDj dj = new InterfaceDj();
            SWDJJBSJ djxx = dj.getJBSJ_New(jsjdm, ud);
            // �۽�����������
            bo.setKjywrmc(djxx.getNsrmc());
            // �۽�����˰ʶ���
            bo.setKjywrnssbh(djxx.getSwdjzh());
            // �۽��˵�ַ
            bo.setDz(djxx.getJydz());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("���õǼǽӿ�ʧ��!");
        }
    }


    /**
     * ��ȡ̨�ʻ��ܽ��
     * @param vo
     * @throws BaseException
     */
    private void getDztzxxByJsjdm(String jsjdm, DztzBO bo) throws BaseException
    {
        System.out.println("========== dztzProcessor_getTzhzjgList ==========");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        /*  XH ���;HTMC ��ͬ��Ŀ����;HTBH ��ͬ��;HTJE ��ͬ�ܼۿ�;HTQYRQ ��ͬǩ������;HTYXQ ��ִͬ������;
         *  FKCS ��֧ͬ������;BZ ����;WBJE ��ҽ��;RMBJE �ۺ������;SDLX ��������;YJSE Ӧ��˰��;JMSE ����˰��;
         *  SBKSRQ �걨��˰����;SKRKRQ ˰���������;SPHM ��˰֤��������˰���ĺ�;BZXX ��ע; */
        String listSql =
            " SELECT XH,E.GJDQMC, E.fjmmc_cn FJMMC, B.HTMC HTMC, B.HTBH HTBH," +
            //��ͬ����л��ܼ���
            "       CASE WHEN A.BADJXH IS NULL AND A.BGBXH IS NULL" +
            "       THEN (SELECT SUM(E.HTJE)" +
            "             FROM SBDB.SB_JL_KJQYSDS_BAHTMX E" +
            "             WHERE E.BADJXH IN (SELECT AA.BADJXH FROM SBDB.SB_JL_KJQYSDS_KJYWR AA  WHERE AA.JSJDM='" +
            jsjdm + "'" +
            "             AND EXISTS (SELECT 1 FROM SBDB.SB_JL_KJQYSDS_KJBGB BB WHERE BB.BADJXH=AA.BADJXH AND BB.SCBS='1' )" +
            "             AND EXISTS (SELECT 1 FROM SBDB.SB_JL_KJQYSDS_HTBADJB CC WHERE CC.BADJXH=AA.BADJXH AND CC.ZTBZ='1' )))" +
            "       ELSE B.HTJE END HTJE," +
            ////////////////////////////////////////////
            "       TO_CHAR(B.HTQYRQ,'YYYY-MM-DD') HTQYRQ, TO_CHAR(B.HTYXQ,'YYYY-MM-DD') HTYXQ, B.FKCS FKCS," +
            "       NVL2(A.BGBXH,(SELECT G.BZMC FROM DMDB.GY_DM_BZ G WHERE G.BZDM=B.BZ),NULL) BZ," +
            "       A.WBJE WBJE,A.RMBJE RMBJE,  (SELECT SBSDLXMC FROM DMDB.SB_DM_FJMSBSDLXDM F WHERE F.SBSDLXDM=C.SBSDLXDM ) SDLX," +
            "       A.YJSE YJSE,A.JMSE JMSE," +
            //����˰Ʊ������걨�ɿ������л�ȡ�걨���ڼ���ҳ����
            "       TO_CHAR(D.SBRQ,'YYYY-MM-DD') SBKSRQ,DECODE(SUBSTR(D.ZWBS,2,1),'1',TO_CHAR(D.ZYRQ,'YYYY-MM-DD'),NULL) SKRKRQ," +
            ////////////////////////////////////////////
            "       C.SPHM, '' BZXX" +
            ////////////////////////////////////////////
            " FROM   " +
            //�ѿ۽ɱ�����ϸ����ת�к���з�����ܲ�����grouping_id���з���Ŀ��ţ����������Ϊ��������
            "       (SELECT CASE WHEN GROUPING_ID(BADJXH,BGBXH) =0 THEN TO_CHAR(ROW_NUMBER() OVER (PARTITION BY BADJXH ORDER BY BADJXH))" +
            "                    WHEN GROUPING_ID(BADJXH,BGBXH) =1 THEN '��Ŀ�ϼ�'" +
            "                    WHEN GROUPING_ID(BADJXH,BGBXH) =3 THEN '��Ⱥϼ�'" +
            "                    ELSE NULL END XH," +
            "               JSJDM,BADJXH,BGBXH,NVL2(BGBXH,SUM(DECODE(HC,'3',YZ,0)),NULL)WBJE," +
            "               SUM(DECODE(HC,'5',YZ,0))RMBJE,SUM(DECODE(HC,'10',YZ,0))YJSE,SUM(DECODE(HC,'13',YZ,0))JMSE" +
            "        FROM SBDB.SB_JL_KJQYSDS_KJBGMX AA" +
            "        WHERE JSJDM='" + jsjdm + "'" +
            "		 AND EXISTS (SELECT 1 FROM SBDB.SB_JL_KJQYSDS_KJBGB BB WHERE BB.BGBXH=AA.BGBXH AND BB.SCBS='1' )" +
            "		 AND EXISTS (SELECT 1 FROM SBDB.SB_JL_KJQYSDS_HTBADJB CC WHERE CC.BADJXH=AA.BADJXH AND CC.ZTBZ='1' )" +
            "        GROUP BY JSJDM, ROLLUP (BADJXH,BGBXH)) A," +
            ////////////////////////////////////////////
            "        SBDB.SB_JL_KJQYSDS_BAHTMX B," +
            "        SBDB.SB_JL_KJQYSDS_KJBGB C," +
            ////////////////////////////////////////////
            "		 (SELECT SPHM,ZWBS,SBRQ,ZYRQ FROM SBDB.SB_JL_SBJKZB WHERE JSJDM='" + jsjdm + "') D, " +
            "(select E1.badjxh, E1.fjmgb, E2.gjdqmc, E1.fjmmc_cn from sbdb.sb_jl_kjqysds_fjmqyxx E1, dmdb.gy_dm_gjdq E2 where E1.fjmgb = E2.gjdqdm) E " + 
            " WHERE   A.BADJXH=B.BADJXH(+)  AND A.BGBXH=C.BGBXH(+) AND A.BADJXH = E.BADJXH(+) AND C.SPHM=D.SPHM(+)" +
            " ORDER BY A.BADJXH,A.BGBXH";

        // KJRLXR �۽�����ϵ��; KJRLXDH �۽�����ϵ�绰; FJMMC_CN �Ǿ�����ҵ��������; �Ǿ�����ҵ����
        String kjrxxSql =
            " SELECT KJRLXR, KJRLXDH, FJMMC_CN, FJMGB" +
            " FROM (SELECT A.KJRLXR, A.KJRLXDH, B.FJMMC_CN, (SELECT C.GJDQMC FROM DMDB.GY_DM_GJDQ C WHERE  C.GJDQDM=B.FJMGB) FJMGB" +
            "      FROM SBDB.SB_JL_KJQYSDS_KJYWR A, SBDB.SB_JL_KJQYSDS_FJMQYXX B" +
            "      WHERE A.BADJXH = B.BADJXH" +
            "      AND A.JSJDM = '" + jsjdm + "'" +
            "      ORDER BY A.LRRQ DESC)" +
            " WHERE ROWNUM = 1";

        try {
            // ��ȡ���ݿ�����
            con = SfDBResource.getConnection();
            pstmt = con.prepareStatement(listSql);
            rs = pstmt.executeQuery(listSql);

            System.out.println("listSql is:: \n" + listSql);

            List list = new ArrayList();
            while (rs.next()) {
                Map map = new HashMap();
                map.put("COL_1", rs.getString("XH"));
                map.put("COL_2", rs.getString("GJDQMC"));
                map.put("COL_3", rs.getString("FJMMC"));
                map.put("COL_4", rs.getString("HTMC"));
                map.put("COL_5", rs.getString("HTBH"));
                map.put("COL_6", formateNumber(rs.getString("HTJE")));
                map.put("COL_7", rs.getString("HTQYRQ"));
                map.put("COL_8", rs.getString("HTYXQ"));
                map.put("COL_9", rs.getString("FKCS"));
                map.put("COL_10", rs.getString("BZ"));
                map.put("COL_11", formateNumber(rs.getString("WBJE")));
                map.put("COL_12", formateNumber(rs.getString("RMBJE")));
                map.put("COL_13", rs.getString("SDLX"));
                map.put("COL_14", formateNumber(rs.getString("YJSE")));
                map.put("COL_15", formateNumber(rs.getString("JMSE")));
                map.put("COL_16", rs.getString("SBKSRQ"));
                map.put("COL_17", rs.getString("SKRKRQ"));
                map.put("COL_18", rs.getString("SPHM"));
                map.put("COL_19", rs.getString("BZXX"));
                list.add(map);
            }
            bo.setTzsjxx(list);
            if (bo.zscxjg()) {
                pstmt = con.prepareStatement(kjrxxSql);
                rs = pstmt.executeQuery(kjrxxSql);
                if (rs.next()) {
                    bo.setLxr(rs.getString("KJRLXR"));
                    bo.setDh(rs.getString("KJRLXDH"));
//                    bo.setFjmqymc(rs.getString("FJMMC_CN"));
//                    bo.setGbdq(rs.getString("FJMGB"));
                }
            }

        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // �ر����ݿ����
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                SfDBResource.freeConnection(con);
            }
            catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException("�ر����ݿ�������");
            }
        }
    }

    private String formateNumber(String value)
    {
        if (value != null && value.length() > 0) {
            if (value.indexOf(".") == 0) {
                value = "0" + value;
            }
            if (value.indexOf("-.") == 0) {
                value = "-0" + value.substring(1);
            }
        }
        return value;
    }

}

package com.ttsoft.bjtax.smsb.sbzl.kjqysds.dztz.processor;


/**
 * <p>Title: 扣缴企业所得税-扣缴企业所得税管理台帐Processer</p>
 *
 * <p>Description: 查询指定纳税人的扣缴企业所得税相关台帐信息</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: 北京四一安信科技有限公司</p>
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
     * 应用处理转发器
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
     * 查询扣缴义务人信息
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
            // 通过登记接口获取登记信息
            InterfaceDj dj = new InterfaceDj();
            SWDJJBSJ djxx = dj.getJBSJ_New(jsjdm, ud);
            // 扣缴人中文名称
            bo.setKjywrmc(djxx.getNsrmc());
            // 扣缴人纳税识别号
            bo.setKjywrnssbh(djxx.getSwdjzh());
            // 扣缴人地址
            bo.setDz(djxx.getJydz());
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException("调用登记接口失败!");
        }
    }


    /**
     * 获取台帐汇总结果
     * @param vo
     * @throws BaseException
     */
    private void getDztzxxByJsjdm(String jsjdm, DztzBO bo) throws BaseException
    {
        System.out.println("========== dztzProcessor_getTzhzjgList ==========");
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        /*  XH 序号;HTMC 合同项目名称;HTBH 合同号;HTJE 合同总价款;HTQYRQ 合同签订日期;HTYXQ 合同执行期限;
         *  FKCS 合同支付次数;BZ 币种;WBJE 外币金额;RMBJE 折合人民币;SDLX 所得类型;YJSE 应纳税额;JMSE 减免税额;
         *  SBKSRQ 申报扣税日期;SKRKRQ 税款入库日期;SPHM 完税证号码或减免税批文号;BZXX 备注; */
        String listSql =
            " SELECT XH,E.GJDQMC, E.fjmmc_cn FJMMC, B.HTMC HTMC, B.HTBH HTBH," +
            //合同金额列汇总计算
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
            //根据税票号码从申报缴款主表中获取申报日期及帐页日期
            "       TO_CHAR(D.SBRQ,'YYYY-MM-DD') SBKSRQ,DECODE(SUBSTR(D.ZWBS,2,1),'1',TO_CHAR(D.ZYRQ,'YYYY-MM-DD'),NULL) SKRKRQ," +
            ////////////////////////////////////////////
            "       C.SPHM, '' BZXX" +
            ////////////////////////////////////////////
            " FROM   " +
            //把扣缴报告明细表行转列后进行分组汇总并根据grouping_id进行分项目编号，将结果集作为主驱动表
            "       (SELECT CASE WHEN GROUPING_ID(BADJXH,BGBXH) =0 THEN TO_CHAR(ROW_NUMBER() OVER (PARTITION BY BADJXH ORDER BY BADJXH))" +
            "                    WHEN GROUPING_ID(BADJXH,BGBXH) =1 THEN '项目合计'" +
            "                    WHEN GROUPING_ID(BADJXH,BGBXH) =3 THEN '年度合计'" +
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

        // KJRLXR 扣缴人联系人; KJRLXDH 扣缴人联系电话; FJMMC_CN 非居民企业中文名称; 非居民企业国别
        String kjrxxSql =
            " SELECT KJRLXR, KJRLXDH, FJMMC_CN, FJMGB" +
            " FROM (SELECT A.KJRLXR, A.KJRLXDH, B.FJMMC_CN, (SELECT C.GJDQMC FROM DMDB.GY_DM_GJDQ C WHERE  C.GJDQDM=B.FJMGB) FJMGB" +
            "      FROM SBDB.SB_JL_KJQYSDS_KJYWR A, SBDB.SB_JL_KJQYSDS_FJMQYXX B" +
            "      WHERE A.BADJXH = B.BADJXH" +
            "      AND A.JSJDM = '" + jsjdm + "'" +
            "      ORDER BY A.LRRQ DESC)" +
            " WHERE ROWNUM = 1";

        try {
            // 获取数据库连接
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
            // 抛出异常
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // 关闭数据库对象
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
                throw new ApplicationException("关闭数据库对象错误！");
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

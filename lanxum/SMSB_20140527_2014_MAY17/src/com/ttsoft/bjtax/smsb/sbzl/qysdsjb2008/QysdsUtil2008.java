package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import com.ttsoft.bjtax.sfgl.model.orobj.QysdsZsfs;
import com.ttsoft.framework.exception.BaseException;
import java.util.Date;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.GregorianCalendar;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import java.math.BigDecimal;
import com.ttsoft.framework.exception.ExceptionUtil;


public class QysdsUtil2008
{
    public QysdsUtil2008()
    {
    }

    /**
     * ��ȡ���շ�ʽ
     * @param jsjdm String
     * @param rq Date
     * @return Zsfs
     * @throws BaseException
     */
    public Zsfs getZsfsInfo(String jsjdm, Date rq) throws BaseException
    {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        Zsfs ret = new Zsfs();
        QysdsZsfs q = new QysdsZsfs();
        StringBuffer sql = new StringBuffer();
        int rdnd = getYear(rq);
        try {
            con = SfDBResource.getConnection();
//            SfDBAccess db = new SfDBAccess(con);
//            Vector v = new Vector();
            sql.append("select * from sfdb.sf_jl_qysdszsfs where ");
            sql.append("jsjdm = '").append(jsjdm).append("' ");
            sql.append("and to_number(rdnd) <= ").append(rdnd);
            sql.append(" order by rdnd desc, cjrq desc");
            System.out.println("zsfs query sql:\n" + sql.toString());

            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());
            //ȡ���˶���һ����¼
            if(rs.next())
            {
                q.setZsfsdm(rs.getString("ZSFSDM"));
                q.setDl(new BigDecimal(rs.getDouble("DL")));
                q.setCyl(new BigDecimal(rs.getDouble("CYL")));
                q.setDe(new BigDecimal(rs.getDouble("DE")));
                //ret.setZsfsdm(rs.getString("ZSFSDM"));
                //ret.setSl(String.valueOf(rs.getDouble("DL")));
                //ret.setZsfsmc(this.getZsfsmc(rs.getString("ZSFSDM"), con));
            }
            else
            {
                return null;
            }

            ret.setZsfsdm(q.getZsfsdm());
            ret.setSl(String.valueOf(q.getDl()));
            ret.setZsfsmc(this.getZsfsmc(q.getZsfsdm(), con));

            if (q.getCyl() != null) {
                ret.setCyl(String.valueOf(q.getCyl().divide(new BigDecimal(100),
                    4,
                    BigDecimal.ROUND_HALF_UP)));
            }
            else {
                ret.setCyl(String.valueOf(q.getCyl()));
            }

            ret.setDe(String.valueOf(q.getDe()));

            //�ر����ݿ����
            rs.close();
            pstmt.close();
            return ret;
        }
        catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }
        finally {
            SfDBResource.freeConnection(con);
        }

    }

    /**
     * �õ��������ڵ���� Ϊint��
     * @param date ����������
     * @return int ���ֵ
     */
    public static int getYear(Date date)
    {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return (calendar.get(Calendar.YEAR));
    }

    public java.lang.String getZsfsmc(String zsfsdm, Connection con)
    {
        PreparedStatement pstmt = null;
//        ResultSet rs = null;
        String ret = "";
        try
        {
            String sql = "select * from dmdb.sf_dm_zsfs where zsfsdm='"
                + zsfsdm +
                "'";
            SfDBAccess db = new SfDBAccess(con);
            ResultSet rs = db.querySQL(sql);
            if (rs.next())
            {
                //rs.first();
                ret = rs.getString("zsfsmc");
            }
            rs.close();
            return ret;
        }
        catch (Exception ex)
        {
            return "";
        }
    }

}

/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.zqwh.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.Debug;
import com.ttsoft.bjtax.shenbao.model.domain.Czqrl;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.zqwh.web.ZqrlcxForm;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ����������ѯ</p>
 * @author �������飭��½��
 * @version 1.1
 */
public class ZqrlcxProcessor
    implements Processor
{

    /**
     * ͨ�ô������ģ��
     * @param vo  ���ݴ���ֵ����
     * @return  ���ݽ������[ActionForm]
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws BaseException
    {

        //�ش�����
        Object result = null;
        //�ж�VO�Ƿ�Ϊ��
        if (vo == null)
        {
            throw new NullPointerException();
        }
        //����Action��ֵ���ò�ͬ��process����
        switch (vo.getAction())
        {
            case CodeConstant.SMSB_SHOWACTION: //ǰ̨Ĭ����ʾ
                result = doShow(vo);
                break;
            case CodeConstant.SMSB_QUERYACTION: //��ѯ
                result = doQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ���ǰ̨Ĭ����ʾ���Ƶ�ActionForm
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    public Object doShow (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        return null;
    }

    /**
     * ��ò�ѯ�����ActionForm
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    public Object doQuery (VOPackage vo)
        throws BaseException
    {
        //�õ�Action���ݹ���ActionForm����
        ZqrlcxForm zf = (ZqrlcxForm) vo.getData();
        //�������ݿ�����
        Connection conn = null;
        try
        {
            conn = SfDBResource.getConnection();
            //ʹ��OrMap�������ݿ�ķ�װ��
            SfDBAccess da = new SfDBAccess(conn);

            //��ѯ��ϸ����
            //��ϸ��ѯ������װ
            Vector vMx = new Vector();
            if (zf.getHeadZqlx() != null && !zf.getHeadZqlx().equals("*"))
            {
                vMx.add(" ZQLXDM = '" + zf.getHeadZqlx() + "' ");
            }
            String whnf = zf.getWhnf();
            if (zf.getSelectTypeRadio().equals("1"))
            {
                vMx.add(" ND = '" + whnf + "' ");
            }
            vMx.add(" 1=1 ORDER BY ZQLXDM ");
            //��ϸ��ѯ���ݽ��
            List mxDataList = da.query(Czqrl.class, vMx);
            if (mxDataList == null || mxDataList.size() == 0)
            {
                throw new ApplicationException("û���ҵ�ָ���ļ�¼��");
            }
            //��ϸOrMappingֵ�����ÿ������
            String mxNames[] =
                {
                "zqlxdm", "zqlxmc", "zqssrqq", "zqssrqz",
                "zqqsrq", "zqzzrq"};
            //��OrMappingֵ����ת��Map���Ա�ActionForm�ܹ�ʹ��
            List mxMapDataList = new ArrayList();
            for (int i = 0; i < mxDataList.size(); i++)
            {
                //��ô�����ϸֵ
                Czqrl orMx = (Czqrl) mxDataList.get(i);
                Map map = new HashMap();
                //��ֵ�����ֵ����Map
                BeanUtil.copyBeanToMap(mxNames, orMx, map);
                mxMapDataList.add(map);
            }
            zf.setDataList(mxMapDataList);
            return zf;
        }
        catch (Exception ex)
        {
            //�׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            //�ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
    }

}
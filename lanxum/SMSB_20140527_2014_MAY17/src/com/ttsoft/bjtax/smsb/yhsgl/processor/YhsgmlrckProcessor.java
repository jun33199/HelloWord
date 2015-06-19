/*
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.yhsgl.processor;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ttsoft.bjtax.sfgl.common.code.CodeManager;
import com.ttsoft.bjtax.sfgl.common.constant.CodeConstants;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.LabelValueBean;
import com.ttsoft.bjtax.sfgl.common.util.SfHashList;
import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.yhsgl.web.YhsgmlrcxForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ¼��ӡ��˰��������鿴��̨����</p>
 * @author �������飭�������
 * @version 1.1
 */

public class YhsgmlrckProcessor
    implements Processor
{
    /**
     * Ĭ�Ϲ��캯��
     */
    public YhsgmlrckProcessor ()
    {
    }

    /**
     * ͨ�ô������ģ��
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return object
     * @throws com.ttsoft.framework.exception.BaseException
     */
    public Object process (VOPackage vo)
        throws com.ttsoft.framework.exception.
        BaseException
    {
        Object result = null;

        if (vo == null)
        {
            throw new NullPointerException();
        }

        switch (vo.getAction())
        {
            case CodeConstant.SMSB_QUERYACTION:
                result = doQuery(vo);
                break;
            default:
                throw new UnsupportedOperationException(
                    "Method process() not yet implemented.");
        }
        return result;
    }

    /**
     * ����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return Form
     * @throws BaseException
     */
    private Object doQuery (VOPackage vo)
        throws BaseException
    {
        YhsgmlrcxForm yForm = (YhsgmlrcxForm) vo.getData();
        Connection conn = null;
        UserData userdata = vo.getUserData();

        Map map = SfStringUtils.splitString(yForm.getViewId());
        String xspzh = (String) map.get("xspzh");
        String dsjsjdm = (String) map.get("dsjsjdm");
        String nd = (String) map.get("nd");
        String cjrq = (String) map.get("cjrq");
        String hjje = (String) map.get("hjje");
        String dwdm = (String) map.get("dwdm");
        String dwmc = (String) map.get("dwmc");
        yForm.setMxCjrq(cjrq);
        yForm.setMxHjje(hjje);
        yForm.setMxDwdm(dwdm);
        yForm.setMxDwmc(dwmc);
        //��ѯ��sql
        StringBuffer sqlBuffer = new StringBuffer();
        sqlBuffer.append(
            "select t.spmzdm,t.gpsl,t.je from sbdb.sb_jl_yhsgmmx t where ")
            .append(" t.qxdm = '" + InterfaceDj.getQxdm(vo.getUserData())
                    + "' ")
            .append(" and t.xspzh = '" + xspzh + "' ")
            .append(" and t.dsjsjdm = '" + dsjsjdm + "' ")
            .append(" and t.nd = '" + nd + "' ");
        sqlBuffer.append(" order by t.spmzdm ");

        String sql = new String();
        sql = sqlBuffer.toString();
        SfHashList sfHashList = null;
        try
        {
            conn = SfDBResource.getConnection();
            SfDBUtils sfDB = new SfDBUtils(conn);
            sfHashList = sfDB.getData(sql);
            List recordList = sfHashList.getRecords();
            //�����ѯ���
            if (recordList.size() > 0)
            {
                List codeList = CodeManager.getCodeList("YHS_PZZL",
                    CodeConstants.CODE_MAP_BEANLIST).
                                getRecords();
                List mxDataList = new ArrayList();
                for (int i = 0; i < recordList.size(); i++)
                {
                    Map temMap = (Map) recordList.get(i);
                    String spmzdm = (String) temMap.get("spmzdm");
                    for (int j = 0; j < codeList.size(); j++)
                    {
                        LabelValueBean labelValueBean = (LabelValueBean)
                            codeList.get(j);
                        if (spmzdm.equals(labelValueBean.getValue()))
                        {
                            temMap.put("spmzmc", labelValueBean.getLabel());
                            mxDataList.add(temMap);
                            break;
                        }
                    }
                }

                yForm.setMxDataList(mxDataList);
            }
            else
            {
                yForm.setMxDataList(new ArrayList());
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

        return yForm;

    }

}

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2008.web;


/**
 * <p>Title: ��ҵ����˰����2008��-������˰��֧���������Form</p>
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
import java.util.*;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.*;


public class ZfjgqysdsjbForm extends QysdsNewForm
{
    /**
     * ����˰�걨�б��֧����������Ŀ���� ��˰��ʶ��š���֧�������ơ������ܶ�����ܶ�ʲ��ܶ�ϼơ��������������˰�� String[]
     */
    private String[] fzjg_columns = { "fzjgnsrsbh", "fzjgmc", "fzjgsrze", "fzjggzze", "fzjgzcze", "fzjghj", "fzjgfpbl", "fzjgfpse" };

    /**
     * ����˰��������
     */
    private List qysdsjbList = new ArrayList();

    /**
     * ���������Ч����
     */
    private String fpblyxqq;

    /**
     * ���������Ч��ֹ
     */
    private String fpblyxqz;

    /**
     * �ܻ��������ܶ�
     */
    private String srze;

    /**
     * �ܻ��������ܶ�
     */
    private String gzze;

    /**
     * �ܻ����ʲ��ܶ�
     */
    private String zcze;

    /**
     * �ܻ����ϼ�
     */
    private String hj;

    /**
     * ��֧������̯������˰��
     */
    private String ftse;

    /**
     * ��ϸ���ݾ����index
     */
    private int maxIndex;

    /**
     * �����ʸ�
     */
    private String jmzg;

    /**
     * һ�����˰��
     */
    private String ybjmsl;

    /**
     * ������ҵ
     */
    private String xzqy;


    public ZfjgqysdsjbForm()
    {
    }

    public void setFzjgColumns(String[] column)
    {
        this.fzjg_columns = column;
    }

    public String[] getFzjgColumns()
    {
        return this.fzjg_columns;
    }

    public String getFpblyxqz()
    {
        return fpblyxqz;
    }

    public String getFpblyxqq()
    {
        return fpblyxqq;
    }

    public List getQysdsjbList()
    {
        return qysdsjbList;
    }

    public String getSrze()
    {
        return srze;
    }

    public String getZcze()
    {
        return zcze;
    }

    public String getGzze()
    {
        return gzze;
    }

    public String getHj()
    {
        return hj;
    }

    public String getFtse()
    {
        return ftse;
    }

    public int getMaxIndex()
    {
        return maxIndex;
    }

    public String getXzqy()
    {
        return xzqy;
    }

    public String getYbjmsl()
    {
        return ybjmsl;
    }

    public String getJmzg()
    {
        return jmzg;
    }

    public void setFpblyxqz(String fpblyxqz)
    {
        this.fpblyxqz = fpblyxqz;
    }

    public void setFpblyxqq(String fpblyxqq)
    {
        this.fpblyxqq = fpblyxqq;
    }

    public void setQysdsjbList(List qysdsjbList)
    {
        this.qysdsjbList = qysdsjbList;
    }

    public void setSrze(String srze)
    {
        this.srze = srze;
    }

    public void setZcze(String zcze)
    {
        this.zcze = zcze;
    }

    public void setGzze(String gzze)
    {
        this.gzze = gzze;
    }

    public void setHj(String hj)
    {
        this.hj = hj;
    }

    public void setFtse(String ftse)
    {
        this.ftse = ftse;
    }

    public void setMaxIndex(int maxIndex)
    {
        this.maxIndex = maxIndex;
    }

    public void setYbjmsl(String ybjmsl)
    {
        this.ybjmsl = ybjmsl;
    }

    public void setXzqy(String xzqy)
    {
        this.xzqy = xzqy;
    }

    public void setJmzg(String jmzg)
    {
        this.jmzg = jmzg;
    }
}

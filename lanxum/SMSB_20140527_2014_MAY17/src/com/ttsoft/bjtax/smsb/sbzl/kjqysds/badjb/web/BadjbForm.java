package com.ttsoft.bjtax.smsb.sbzl.kjqysds.badjb.web;


/**
 * <p>Title: �۽���ҵ����˰-�����ǼǱ�Form</p>
 *
 * <p>Description: ��¼�����ǼǱ�ҳ����ֵ</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */

import java.util.List;

import com.ttsoft.bjtax.smsb.model.kjqysds.BAHTXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.FJMQYXX;
import com.ttsoft.bjtax.smsb.model.kjqysds.KJYWRXX;
import com.ttsoft.framework.form.BaseForm;

public class BadjbForm extends BaseForm
{
    /**
     * �����Ǽ���ţ�������
     */
    private String badjxh;

    /**
     * �����ǼǱ���
     */
    private String badjbbh;

    /**
     * �����
     */
    private String tbrq;

    /**
     * �۽��˼��������
     */
    private String jsjdm;

    /**
     * �۽�����Ϣ
     */
    private KJYWRXX kjywrxx = new KJYWRXX();

    /**
     * �Ǿ�����ҵ��Ϣ
     */
    private FJMQYXX fjmqyxx = new FJMQYXX();

    /**
     * ��ͬ��Ϣ
     */
    private BAHTXX htxx = new BAHTXX();

    /**
     * ��¼��
     */
    private int totalCount;

    /**
     * ��ҳ��
     */
    private int totalPage;

    /**
     * ��ǰҳ
     */
    private int currentPage;

    /**
     * ��ѯ��¼
     */
    private List recordList;

    /**
     * ���״̬��ʶ
     *     ״̬��ʶ:0-��ʼ״̬|1-ȷ�ϱ���ͨ��|2-ȷ�ϱ�����ͨ��
     */
    private String ztbz;
    
    /**
     * �޸ı�ʶ
     *    true--�޸ı����ǼǱ����
     *    false--���޸ı����ǼǱ����
     */
    private boolean modifyFlag = false;


    public BadjbForm()
    {
    }

    public String getBadjbbh()
    {
        return badjbbh;
    }

    public BAHTXX getHtxx()
    {
        return htxx;
    }

    public String getTbrq()
    {
        return tbrq;
    }

    public FJMQYXX getFjmqyxx()
    {
        return fjmqyxx;
    }

    public KJYWRXX getKjywrxx()
    {
        return kjywrxx;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

    public int getTotalPage()
    {
        return totalPage;
    }

    public int getTotalCount()
    {
        return totalCount;
    }

    public List getRecordList()
    {
        return recordList;
    }

    public String getBadjxh()
    {
        return badjxh;
    }

    public String getZtbz()
    {
        return ztbz;
    }

    public void setTbrq(String tbrq)
    {
        this.tbrq = tbrq;
    }

    public void setKjywrxx(KJYWRXX kjywrxx)
    {
        this.kjywrxx = kjywrxx;
    }

    public void setHtxx(BAHTXX htxx)
    {
        this.htxx = htxx;
    }

    public void setFjmqyxx(FJMQYXX fjmqyxx)
    {
        this.fjmqyxx = fjmqyxx;
    }

    public void setBadjbbh(String badjbbh)
    {
        this.badjbbh = badjbbh;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setCurrentPage(int currentPage)
    {
        this.currentPage = currentPage;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public void setRecordList(List recordList)
    {
        this.recordList = recordList;
    }

    public void setBadjxh(String badjxh)
    {
        this.badjxh = badjxh;
    }

    public void setZtbz(String ztbz)
    {
        this.ztbz = ztbz;
    }

	public boolean getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(boolean modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

}

package com.ttsoft.bjtax.shenbao.sbzl.kjqysds.badjb.bo;

/**
 * <p>Title: �����ǼǱ�BO</p>
 *
 * <p>Description: ��¼�����ǼǱ����Ӧ�õļ�ֵ�����ں�̨����</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author tum
 * @version 1.0
 */
import java.io.Serializable;
import java.util.List;

import com.syax.bjtax.shenbao.model.kjqysds.*;


public class BadjbBO implements Serializable
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
     * չʾ�����
     *     ��ʽΪyyyy��mm��dd��
     */
    private String tbrqShow;

    /**
     * �۽��˼��������
     */
    private String jsjdm;
    
    /**
     * �޸ı��
     *    0-δ�޸Ĳ���|1-�޸Ĳ���
     */
    private String modifyFlag;

    /**
     * �۽�����Ϣ
     */
    private KJYWRXX kjywrxx;

    /**
     * �Ǿ�����ҵ��Ϣ
     */
    private FJMQYXX fjmqyxx;

    /**
     * ��ͬ��Ϣ
     */
    private BAHTXX htxx;
    

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
     *     ״̬��ʶ:0-��ʼ״̬|1-ȷ�ϱ���ͨ��|2-ȷ�ϱ�����ͨ��|3-ͬһ�û���ͬ����ظ�
     */
    private String ztbz;
    
    /**
     * ��ʾ��Ϣ
     */
    private String message;


    public BadjbBO()
    {
    }

    public String getBadjbbh()
    {
        return badjbbh;
    }

    public FJMQYXX getFjmqyxx()
    {
        return fjmqyxx;
    }

    public BAHTXX getHtxx()
    {
        return htxx;
    }

    public String getJsjdm()
    {
        return jsjdm;
    }

    public KJYWRXX getKjywrxx()
    {
        return kjywrxx;
    }

    public String getTbrq()
    {
        return tbrq;
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

    public String getBadjxh()
    {
        return badjxh;
    }

    public List getRecordList()
    {
        return recordList;
    }

    public String getZtbz()
    {
        return ztbz;
    }

    public String getTbrqShow()
    {
        return tbrqShow;
    }

    public void setTbrq(String tbrq)
    {
        this.tbrq = tbrq;
    }

    public void setKjywrxx(KJYWRXX kjywrxx)
    {
        this.kjywrxx = kjywrxx;
    }

    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
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

    public void setBadjxh(String badjxh)
    {
        this.badjxh = badjxh;
    }

    public void setRecordList(List recordList)
    {
        this.recordList = recordList;
    }

    public void setZtbz(String ztbz)
    {
        this.ztbz = ztbz;
    }

    public void setTbrqShow(String tbrqShow)
    {
        this.tbrqShow = tbrqShow;
    }

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

package com.ttsoft.bjtax.smsb.sbzl.qysdsjb2014.web;


/**
 * <p>Title: ��ҵ����˰����2014��-������˰��֧���������Form</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2008</p>
 *
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 *
 * @author zhangyj
 * @version 1.0
 */
import java.util.*;

import com.ttsoft.bjtax.smsb.sbzl.qysdsnbnew.base.*;


public class ZfjgqysdsjbForm extends QysdsNewForm
{
    /**
     * ����˰�걨�б��֧����������Ŀ���� ��˰��ʶ��š���֧�������ơ������ܶ�����ܶ�ʲ��ܶ�������������˰�� String[]
     */
    private String[] fzjg_columns = { "fzjgnsrsbh", "fzjgmc", "fzjgsrze", "fzjggzze", "fzjgzcze", "fzjgfpbl", "fzjgfpse" };

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

    /**
     * ��������
     */
    private String jglx;

	/**
     * Ӧ������˰��
     */
    private String ynsdse;    
    
    /**
     * �ܻ�����̯����˰��
     */
    private String zjgftse;       
    
    /**
     * �ܻ����������з�������˰��
     */
    private String zjgfpse;   
    
    /**
     * ��֧������̯����˰��
     */
    private String fzjgftse;   
    
    /**
     * ��֧�����������
     */
    private String fzjgfpbl;  
 
    /**
     * ��֧��������˰��
     */
    private String fzjgfpse; 
    
    /**
     * �ܻ�������
     */
    private String zjgmc;  

	/**
     * �����ϼ�
     */
    private String srehj;
    
    /**
     * ���ʶ�ϼ�
     */
    private String gzehj;
    
    /**
     * �ʲ���ϼ�
     */
    private String zcehj;
    
    /**
     * ��������ϼ�
     */
    private String fpblhj;
    
    /**
     * ����˰��ϼ�
     */
    private String fpsehj; 

	/**
     * ��֧������˰��ʶ���
     */
    private String fzjgnsrsbh;
    
    /**
     * ��֧������˰������
     */
    private String fzjgnsrmc;
    //�����ʶ��0Ϊ����ʧ�ܣ�1Ϊ����ɹ���added by zhangj 2014.11.28
    private String SAVE_FLAG="0";
    public String getZjgmc() {
		return zjgmc;
	}

	public void setZjgmc(String zjgmc) {
		this.zjgmc = zjgmc;
	}

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
    
    public String getJglx()
    {
        return jglx;
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

    public void setJglx(String jglx)
    {
        this.jglx = jglx;
    }

    public String getYnsdse() {
		return ynsdse;
	}

	public void setYnsdse(String ynsdse) {
		this.ynsdse = ynsdse;
	}

	public String getZjgftse() {
		return zjgftse;
	}

	public void setZjgftse(String zjgftse) {
		this.zjgftse = zjgftse;
	}

	public String getZjgfpse() {
		return zjgfpse;
	}

	public void setZjgfpse(String zjgfpse) {
		this.zjgfpse = zjgfpse;
	}

	public String getFzjgftse() {
		return fzjgftse;
	}

	public void setFzjgftse(String fzjgftse) {
		this.fzjgftse = fzjgftse;
	}

	public String getFzjgfpbl() {
		return fzjgfpbl;
	}

	public void setFzjgfpbl(String fzjgfpbl) {
		this.fzjgfpbl = fzjgfpbl;
	}

	public String getFzjgfpse() {
		return fzjgfpse;
	}

	public void setFzjgfpse(String fzjgfpse) {
		this.fzjgfpse = fzjgfpse;
	}
	
	
    public String getSrehj() {
		return srehj;
	}

	public void setSrehj(String srehj) {
		this.srehj = srehj;
	}

	public String getGzehj() {
		return gzehj;
	}

	public void setGzehj(String gzehj) {
		this.gzehj = gzehj;
	}

	public String getZcehj() {
		return zcehj;
	}

	public void setZcehj(String zcehj) {
		this.zcehj = zcehj;
	}

	public String getFpblhj() {
		return fpblhj;
	}

	public void setFpblhj(String fpblhj) {
		this.fpblhj = fpblhj;
	}

	public String getFpsehj() {
		return fpsehj;
	}

	public void setFpsehj(String fpsehj) {
		this.fpsehj = fpsehj;
	}	
    
    public String getFzjgnsrsbh() {
		return fzjgnsrsbh;
	}

	public void setFzjgnsrsbh(String fzjgnsrsbh) {
		this.fzjgnsrsbh = fzjgnsrsbh;
	}

	public String getFzjgnsrmc() {
		return fzjgnsrmc;
	}

	public void setFzjgnsrmc(String fzjgnsrmc) {
		this.fzjgnsrmc = fzjgnsrmc;
	}

	public String getSAVE_FLAG() {
		return SAVE_FLAG;
	}

	public void setSAVE_FLAG(String sAVEFLAG) {
		SAVE_FLAG = sAVEFLAG;
	}
	
}

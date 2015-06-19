package com.ttsoft.bjtax.shenbao.wsksb.web;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: </p>
 * <p>Copyright: (C) 2004-2005 ��һ���Źɷ����޹�˾����Ȩ����.</p>
 * <p>Company: ��һ���Źɷ����޹�˾</p>
 * @author  guzhixian
 * @version 1.1
 */

import com.ttsoft.framework.form.BaseForm;
import com.ttsoft.common.model.UserData;
import java.util.Map;
import java.util.HashMap;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.bjtax.shenbao.constant.ProcessorNames;
import com.ttsoft.bjtax.shenbao.wsksb.SessionKey;
import java.text.SimpleDateFormat;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import java.util.List;

public class WsksbForm extends BaseForm
{
    public WsksbForm()
    {
    }

    /**
     * ˰����������
     */
    private String jsjdm;

    /**
     * �걨����
     */
    private String sbrq;

    /**
     * �걨���
     */
    private String sbbh;

    /**
     * ˰��������ʼ��������
     */
    private String skssksrq;

    /**
     * ˰��������������
     */
    private String skssjsrq;

    /**
     * ��ϵ�绰
     */
    private String lxdh;

    /**
     * ���
     */
    private String nd;

    /**
     * ���
     */
    private String bz;

    /**
     *  ��˰������
     */
    private String nsrmc;

    /**
     *  ˰�������֯��������
     */
    private String swjgzzjgdm;

    /**
     *  ��ʾ��Ϣ
     */
    private List msg;
//    private String msg;


    /**
     *  �Ƿ���Ա���
     */
    private int canSave;

    /**
     *  �Ƿ����ɾ��
     */
    private int canDel;

    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getLxdh()
    {
        return lxdh;
    }
    public String getNd()
    {
        return nd;
    }
    public String getNsrmc()
    {
        return nsrmc;
    }
    public String getSbrq()
    {
        return sbrq;
    }
    public String getSkssjsrq()
    {
        return skssjsrq;
    }
    public String getSkssksrq()
    {
        return skssksrq;
    }
    public String getSwjgzzjgdm()
    {
        return swjgzzjgdm;
    }
    public void setSwjgzzjgdm(String swjgzzjgdm)
    {
        this.swjgzzjgdm = swjgzzjgdm;
    }
    public void setSkssksrq(String skssksrq)
    {
        this.skssksrq = skssksrq;
    }
    public void setSkssjsrq(String skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }
    public void setSbrq(String sbrq)
    {
        this.sbrq = sbrq;
    }
    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public void setLxdh(String lxdh)
    {
        this.lxdh = lxdh;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    /**
     * ʹ��keyΪSessionKey.JSJDM�����������룬����Map��
     * ����VOPackage������action= YhsActionConstant.INT_ACTION_QUERY���ݸ�processor
     * @param userData UserData
     * @param djMap Map
     * @return VOPackage
     */
    public VOPackage getQueryData(UserData userData, Map data, int actionId)
    {
        VOPackage voPackage = new VOPackage();
        voPackage.setData(data);
        voPackage.setAction(actionId);
        voPackage.setUserData(userData);
        voPackage.setProcessor(ProcessorNames.WSKSB_PROCESSOR);
        return voPackage;
    }

    public int getCanSave()
    {
        return canSave;
    }
    public int getCanDel()
    {
        return canDel;
    }
    public void setCanDel(int canDel)
    {
        this.canDel = canDel;
    }
    public void setCanSave(int canSave)
    {
        this.canSave = canSave;
    }
    public String getBz()
    {
        return bz;
    }
    public void setBz(String bz)
    {
        this.bz = bz;
    }
    public String getSbbh()
    {
        return sbbh;
    }
    public void setSbbh(String sbbh)
    {
        this.sbbh = sbbh;
    }
    public List getMsg()
    {
        return msg;
    }
    public void setMsg(List msg)
    {
        this.msg = msg;
    }


}
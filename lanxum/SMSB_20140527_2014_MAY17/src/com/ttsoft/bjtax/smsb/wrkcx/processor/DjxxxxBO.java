package com.ttsoft.bjtax.smsb.wrkcx.processor;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

import java.io.Serializable;
import java.util.List;
import com.ttsoft.bjtax.dj.model.SWDL;
import com.ttsoft.bjtax.dj.model.ZJG;
import com.ttsoft.bjtax.dj.model.JBSJ;

/**
 * <p>Title: ������˰��������ϵͳ����˰��Ǽ�</p>
 * <p>Description: ˰��Ǽ���ϸ��Ϣ���ݶ���</p>
 * @author �������飭��������
 * @version 1.1
 */
public class DjxxxxBO
    implements Serializable
{
    /**
     * Ĭ�Ϲ��캯��
     */
    public DjxxxxBO()
    {
    }

    //˰��Ǽ�
    /**
     * ˰��Ǽǡ�˰�������Ϣ
     */
    SWDL swdl;

    /**
     * ˰��Ǽǡ��ܻ���
     */

    ZJG zjg;

    /**
     * ˰��Ǽǡ���������
     */

    JBSJ jbsj;

    /**
     * ˰��Ǽǡ���֧����
     */

    List fzjgList;

    /**
     * ˰��Ǽǡ�������Ϣ
     */

    List yhzhList;

    /**
     * ˰��Ǽǡ������Ϣ
     */

    List bgxxList;

    /**˰��Ǽǡ�ͣҵ�Ǽ�*/

    List tydjList;

    /**
     * ˰��Ǽǡ������Ǽ�
     */

    List dxdjList;

    /**
     * ˰��Ǽǡ�ע���Ǽ�
     */

    List zxdjList;

    /**
     * ˰��Ǽǡ�ͣҵ�Ǽǡ�����ʷ
     */

    List tydjList_ls;

    /**
     * ˰��Ǽǡ������Ǽǡ�����ʷ
     */

    List dxdjList_ls;

    /**
     * ˰��Ǽǡ�ע���Ǽǡ�����ʷ
     */

    List zxdjList_ls;

    /**
     * ˰��Ǽǡ�˰��Ǽǡ�����ϵ��
     */

    List lxrList;

    /**
     * ˰��Ǽǡ�Ͷ�ʷ��Ǽ���Ϣ
     */

    List tzfList;

    public List getBgxxList()
    {
        return bgxxList;
    }

    public List getDxdjList()
    {
        return dxdjList;
    }

    public List getDxdjList_ls()
    {
        return dxdjList_ls;
    }

    public List getFzjgList()
    {
        return fzjgList;
    }

    public JBSJ getJbsj()
    {
        return jbsj;
    }

    public List getTydjList()
    {
        return tydjList;
    }

    public List getTydjList_ls()
    {
        return tydjList_ls;
    }

    public List getYhzhList()
    {
        return yhzhList;
    }

    public ZJG getZjg()
    {
        return zjg;
    }

    public List getZxdjList()
    {
        return zxdjList;
    }

    public List getZxdjList_ls()
    {
        return zxdjList_ls;
    }

    public void setBgxxList(List bgxxList)
    {
        this.bgxxList = bgxxList;
    }

    public void setDxdjList(List dxdjList)
    {
        this.dxdjList = dxdjList;
    }

    public void setDxdjList_ls(List dxdjList_ls)
    {
        this.dxdjList_ls = dxdjList_ls;
    }

    public void setFzjgList(List fzjgList)
    {
        this.fzjgList = fzjgList;
    }

    public void setJbsj(JBSJ jbsj)
    {
        this.jbsj = jbsj;
    }

    public void setTydjList(List tydjList)
    {
        this.tydjList = tydjList;
    }

    public void setTydjList_ls(List tydjList_ls)
    {
        this.tydjList_ls = tydjList_ls;
    }

    public void setYhzhList(List yhzhList)
    {
        this.yhzhList = yhzhList;
    }

    public void setZjg(ZJG zjg)
    {
        this.zjg = zjg;
    }

    public void setZxdjList(List zxdjList)
    {
        this.zxdjList = zxdjList;
    }

    public void setZxdjList_ls(List zxdjList_ls)
    {
        this.zxdjList_ls = zxdjList_ls;
    }

    public List getLxrList()
    {
        return lxrList;
    }

    public SWDL getSwdl()
    {
        return swdl;
    }

    public List getTzfList()
    {
        return tzfList;
    }

    public void setTzfList(List tzfList)
    {
        this.tzfList = tzfList;
    }

    public void setSwdl(SWDL swdl)
    {
        this.swdl = swdl;
    }

    public void setLxrList(List lxrList)
    {
        this.lxrList = lxrList;
    }

}



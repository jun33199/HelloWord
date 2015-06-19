/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.Serializable;
import java.util.List;

import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: �ۺ��걨��ǰ̨����̨�����걨����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class DeclareInfor
    implements Serializable
{

    /**
     * �걨�ɿ�������Ϣ
     */
    private Sbjkzb sbjkzb;

    /**
     * �걨����ϸ��Ϣ
     */
    private List sbjkmxInfo;

    /**
     * ��ӡ��ʶ:1.һƱһ˰;2.һƱ��˰(��Ŀ);3.һƱ��˰(˰Ŀ)
     */
    private int printTag;

    /**
     * �Ƿ���Ҫ���ؽɿ����ݱ�ʶ
     */
    private boolean isReturnPaymentInfo;

    public DeclareInfor ()
    {
    }

    //���캯��ͬʱ����� sbjkzb(������Ϣ)��sbjkmxInfo(������ϸ��Ϣ)
    public DeclareInfor (Sbjkzb sbjkzb, List sbjkmxInfo)
    {
        this.sbjkzb = sbjkzb;
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public Sbjkzb getSbjkzb ()
    {
        return sbjkzb;
    }

    public void setSbjkzb (Sbjkzb sbjkzb)
    {
        this.sbjkzb = sbjkzb;
    }

    public void setSbjkmxInfo (List sbjkmxInfo)
    {
        this.sbjkmxInfo = sbjkmxInfo;
    }

    public List getSbjkmxInfo ()
    {
        return sbjkmxInfo;
    }

    public boolean isIsReturnPaymentInfo ()
    {
        return isReturnPaymentInfo;
    }

    public void setIsReturnPaymentInfo (boolean isReturnPaymentInfo)
    {
        this.isReturnPaymentInfo = isReturnPaymentInfo;
    }

    public int getPrintTag ()
    {
        return printTag;
    }

    public void setPrintTag (int printTag)
    {
        this.printTag = printTag;
    }
}
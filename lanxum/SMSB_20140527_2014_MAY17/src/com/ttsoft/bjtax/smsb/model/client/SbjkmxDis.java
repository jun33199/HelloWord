/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.model.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ��ԭSbjkmxֵ����Ļ��������ǰ̨��ʾ��Ҫʹ�õ�����</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class SbjkmxDis
    implements Serializable
{
//

    /**
     * ˰��˰Ŀ����	SZSMDM	VARCHAR2(9)	TRUE	FALSE	TRUE
     */
    String szsmdm;
    /**
     * �ɿ�ƾ֤��	JKPZH	VARCHAR2(20)	TRUE	TRUE	TRUE
     */

    String jkpzh;
    /**
     * ���������	JSJDM	VARCHAR2(8)	TRUE	TRUE	TRUE
     */

    String jsjdm;
    /**
     * Ԥ���Ŀ����	YSKMDM	VARCHAR2(10)	FALSE	FALSE	TRUE
     */

    String yskmdm;
    /**
     * Ԥ���Ŀ����	YSKMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
     */

    String yskmmc;
    /**
     * Ԥ�㼶�δ���	YSJCDM	VARCHAR2(4)	FALSE	FALSE	TRUE
     */

    String ysjcdm;
    /**
     * Ԥ�㼶������	YSJCMC	VARCHAR2(60)	FALSE	FALSE	FALSE
     */

    String ysjcmc;
    /**
     * ˰��˰Ŀ����	SZSMMC	VARCHAR2(60)	FALSE	FALSE	FALSE
     */

    String szsmmc;
    /**
     * ��˰����	KSSL	NUMBER(15,2)	FALSE	FALSE	FALSE
     */

    BigDecimal kssl;
    /**
     * ��˰���	JSJE	NUMBER(15,2)	FALSE	FALSE	FALSE
     */

    BigDecimal jsje;
    /**
     * ʵ��˰��	SJSE	NUMBER(15,2)	FALSE	FALSE	FALSE
     */

    BigDecimal sjse;
    /**
     * ˰��������ʼ����	SKSSKSRQ	DATE	FALSE	FALSE	FALSE
     */

    Timestamp skssksrq;
    /**
     * ˰��������������	SKSSJSRQ	DATE	FALSE	FALSE	FALSE
     */

    Timestamp skssjsrq;
    /**
     * ˰�ִ���        SZDM    VARCHAR2(6)     �ⲻ�Ǳ��е��ֶ�
     */

    String szdm;
    /**
     * ˰������        SZMC    VARCHAR2(6)     �ⲻ�Ǳ��е��ֶ�
     */

    String szmc;
    /**
     * �������ʹ���
     * �ⲻ�����ݿ��е��ֶ�,Ϊ�˺�̨��ȡ�õ�˰������ʱ�ڱ����������Ϣ
     */

    String zqlxdm;
    /**
     * ��������
     * �ⲻ�����ݿ��е��ֶ�,Ϊ�˺�̨��ȡ�õ�˰������ʱ�ڱ����������Ϣ
     */

    String jnqx;
    /**
     * �����	SJJE	NUMBER(15,2)	FALSE	FALSE	TRUE
     */

    BigDecimal rkje;
    /**
     * �걨���    SBBH	VARCHAR2(20)
     */

    String sbbh;
    /**
     * �оּ��ֳ�(����4ΪС��)
     */

    BigDecimal sjfc;
    /**
     * ���ؼ��ֳ�(����4ΪС��)
     */

    BigDecimal qjfc;
    /**
     * �Ƿ񸽼�˰��ʾ
     */

    private String sffjs;

    /**
     * ˰��
     */
    private java.math.BigDecimal sl;

    /**
     * ����˰�ѽӿ�
     */
    private boolean fromDqde;

    /**
     * �������Ʊ�ʾ
     */
    private String asljbs;

    /**
     * ��������
     */
    private java.math.BigDecimal jsjs;

    public String getJkpzh ()
    {
        return jkpzh;
    }

    public String getJsjdm ()
    {
        return jsjdm;
    }

    public BigDecimal getJsje ()
    {
        return jsje;
    }

    public BigDecimal getKssl ()
    {
        return kssl;
    }

    public BigDecimal getSjse ()
    {
        return sjse;
    }

    public Timestamp getSkssjsrq ()
    {
        return skssjsrq;
    }

    public Timestamp getSkssksrq ()
    {
        return skssksrq;
    }

    public String getSzdm ()
    {
        return szdm;
    }

    public String getSzmc ()
    {
        return szmc;
    }

    public String getSzsmdm ()
    {
        return szsmdm;
    }

    public String getSzsmmc ()
    {
        return szsmmc;
    }

    public String getYsjcdm ()
    {
        return ysjcdm;
    }

    public String getYsjcmc ()
    {
        return ysjcmc;
    }

    public String getYskmdm ()
    {
        return yskmdm;
    }

    public String getYskmmc ()
    {
        return yskmmc;
    }

    public void setYskmmc (String yskmmc)
    {
        this.yskmmc = yskmmc;
    }

    public void setYskmdm (String yskmdm)
    {
        this.yskmdm = yskmdm;
    }

    public void setYsjcmc (String ysjcmc)
    {
        this.ysjcmc = ysjcmc;
    }

    public void setYsjcdm (String ysjcdm)
    {
        this.ysjcdm = ysjcdm;
    }

    public void setSzsmmc (String szsmmc)
    {
        this.szsmmc = szsmmc;
    }

    public void setSzsmdm (String szsmdm)
    {
        this.szsmdm = szsmdm;
    }

    public void setSzmc (String szmc)
    {
        this.szmc = szmc;
    }

    public void setSzdm (String szdm)
    {
        this.szdm = szdm;
    }

    public void setSkssksrq (Timestamp skssksrq)
    {
        this.skssksrq = skssksrq;
    }

    public void setSkssjsrq (Timestamp skssjsrq)
    {
        this.skssjsrq = skssjsrq;
    }

    public void setSjse (BigDecimal sjse)
    {
        this.sjse = sjse;
    }

    public void setKssl (BigDecimal kssl)
    {
        this.kssl = kssl;
    }

    public void setJsje (BigDecimal jsje)
    {
        this.jsje = jsje;
    }

    public void setJsjdm (String jsjdm)
    {
        this.jsjdm = jsjdm;
    }

    public void setJkpzh (String jkpzh)
    {
        this.jkpzh = jkpzh;
    }

    public String getZqlxdm ()
    {
        return zqlxdm;
    }

    public void setZqlxdm (String zqlxdm)
    {
        this.zqlxdm = zqlxdm;
    }

    public String getJnqx ()
    {
        return jnqx;
    }

    public void setJnqx (String jnqx)
    {
        this.jnqx = jnqx;
    }

    public BigDecimal getRkje ()
    {
        return rkje;
    }

    public void setRkje (BigDecimal rkje)
    {
        this.rkje = rkje;
    }

    public String getSbbh ()
    {
        return sbbh;
    }

    public void setSbbh (String sbbh)
    {
        this.sbbh = sbbh;
    }

    public BigDecimal getQjfc ()
    {
        return qjfc;
    }

    public void setQjfc (BigDecimal qjfc)
    {
        this.qjfc = qjfc;
    }

    public BigDecimal getSjfc ()
    {
        return sjfc;
    }

    public void setSjfc (BigDecimal sjfc)
    {
        this.sjfc = sjfc;
    }

    private void writeObject (ObjectOutputStream oos)
        throws IOException
    {
        oos.defaultWriteObject();
    }

    private void readObject (ObjectInputStream ois)
        throws ClassNotFoundException,
        IOException
    {
        ois.defaultReadObject();
    }

    public String getSffjs ()
    {
        return sffjs;
    }

    public void setSffjs (String sffjs)
    {
        this.sffjs = sffjs;
    }

    public java.math.BigDecimal getSl ()
    {
        return sl;
    }

    public void setSl (java.math.BigDecimal sl)
    {
        this.sl = sl;
    }

    public boolean isFromDqde ()
    {
        return fromDqde;
    }

    public void setFromDqde (boolean fromDqde)
    {
        this.fromDqde = fromDqde;
    }

    public String getAsljbs ()
    {
        return asljbs;
    }

    public void setAsljbs (String asljbs)
    {
        this.asljbs = asljbs;
    }

    public java.math.BigDecimal getJsjs ()
    {
        return jsjs;
    }

    public void setJsjs (java.math.BigDecimal jsjs)
    {
        this.jsjs = jsjs;
    }
}
/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪ͬ������ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪ͬ������ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.zhsb.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.model.domain.Swjgzzjg;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.EArray;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.JksUtil;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypdsActionForm;
import com.ttsoft.bjtax.smsb.zhsb.web.ZhsbjksypysActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ��һƱ��˰�ɿ�������ӡ��޸ġ�ɾ������</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class ZhsbjksypdsProcessor
    implements Processor
{
  public ZhsbjksypdsProcessor()
  {
  }

  /**
   * һƱһ˰��������
   */
  //private static final String YPYS = "ZhsbjksypysAction.do";

  /**
   * һƱ��˰��������
   */
  //private static final String YPDS = "ZhsbjksypdsAction.do";

  /**
   * ʵ��Processor�ӿ�
   * @param vo ҵ�����
   * @return Object VOPackage������
   * @throws BaseException ҵ���쳣
   */

  public Object process(VOPackage vo) throws com.ttsoft.framework.exception.
      BaseException
  {
    switch (vo.getAction())
    {
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      case CodeConstant.SMSB_UPDATEACTION:
        return doUpdate(vo);
      default:
        return null;
    }

  }

  /**
   * �õ����͹����Ľ������ ����ѡ�����е�������������ϸ
   * @param vo ҵ�����
   * @return ��form ��Ҫ��ϸ��Ϣ�����DataList
   * @throws BaseException
   */

  private Object doQuery(VOPackage vo) throws BaseException
  {
    if (1==1)
    {
      throw new ApplicationException("�����ܲ����ã������Ǵ���ģ�δ�޸����");
    }

    //HashMap dataMap = new HashMap(); //���ص�map����
    ZhsbjksypdsActionForm form = (ZhsbjksypdsActionForm) vo.getData();
    //�������ݿ�����

    Connection conn = null;
    SfDBAccess sbDB = null;
    SfDBUtils sbDBU = null;

    try
    {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      //UserData����
      UserData ud = (UserData) vo.getUserData();
      //�õ����ش���
      String qxdm = InterfaceDj.getQxdm(ud);
      String sql = "";
      sql = "";
      Sbjkzb orObj = new Sbjkzb();
      Sbjkmx orMx = new Sbjkmx();
      Vector condition = new Vector();

      //������ش���
      condition.add("qxdm='" + qxdm + "'");
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      //condition.add("substr(jkpzh,1,length(jkpzh)-1)='" + form.getJkpzh() + "'");
      condition.add("sbbh='" + form.getSbbh() + "'");
      List list = sbDB.query(Sbjkzb.class, condition);
      ArrayList ysFormList = new ArrayList();

      String[] names =
          {
          "jsjdm", "lrrq", "yhmc", "sjly"};
      EArray jsArray = new EArray();
      String tempJsStr = "";
      String varName = "";
      String jsSql = "";
      if (list.size() > 0)
      {
        orObj = (Sbjkzb) list.get(0);
        BeanUtil.copyBeanToBean(names, orObj, form);
        form.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
            orObj.getSwjgzzjgdm()));
        /**  ͨ���Ǽǽӿڵõ���˰������   Shi Yanfeng 20031028 **/
        SWDJJBSJ jbsj = InterfaceDj.getJBSJ(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.16*/
        form.setNsrmc(jbsj.getNsrmc());
      }

      for (int i = 0; i < list.size(); i++)
      {
        orObj = (Sbjkzb) list.get(i);
        orObj.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG", orObj.getSwjgzzjgdm()));
        orObj.setNsrmc(form.getNsrmc());
        orObj.setYsjcmc(CodeUtils.getCodeBeanLabel("DM_YSJC", orObj.getYsjcdm()));
        orObj.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", orObj.getGkzzjgdm()));
        orObj.setSzmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orObj.getSzdm()));

        condition.clear();
        condition.add("qxdm='" + qxdm + "'");
        condition.add("sbbh='" + form.getSbbh() + "'");

        List orMxList = sbDB.query(Sbjkmx.class, condition);

        for (int j = 0; j < orMxList.size(); j++)
        {
          orMx = ( (Sbjkmx) orMxList.get(j));
          orMx.setSzsmmc(CodeUtils.getCodeBeanLabel("DM_SZSM", orMx.getSzsmdm()));
        }

        varName = "d" + orObj.getJkpzh();
        String szsmdm = orMx.getSzsmdm();
        if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM023) == 0)
        { //��˰��˰Ŀ����ǰ��λ��023ʱ �ǽ��� ���⴦��
          jsSql = "JKSWH_SZSMDM02023";
        }
        else
        {
          if (szsmdm.indexOf(CodeConstant.ZHSB_JKSMX_SZSMDM02)
              == 0)
          { //��˰��˰Ŀ����ǰ��λ��02��ǰ��λ����023ʱ �ǽ��� ���⴦��
            jsSql = "JKSWH_SZSMDM02";
          }
          else
          {
            jsSql = "JKSWH_SZSMDM" + orObj.getSzdm();
          }
        }
      }
      //�����ж��һƱһ˰���List��ΪһƱ��˰��List
      form.setDataList(ysFormList);

      tempJsStr += jsArray.getArrayByCode("szsmlist", "JKSWH_SZSMDMLIST");
      form.setScriptStr(tempJsStr);

      return form;
    }

    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * ���½ɿ�����Ϣ
   * @param vo ҵ�����
   * @return ��form ��Ҫ��ϸ��Ϣ�����DataList
   * @throws BaseException
   */

  private Object doUpdate(VOPackage vo) throws BaseException
  {
    if (1==1)
    {
      throw new ApplicationException("�����ܲ����ã������Ǵ���ģ�δ�޸����");
    }
    //HashMap dataMap = new HashMap(); //���ص�map����
    ZhsbjksypdsActionForm form = (ZhsbjksypdsActionForm) vo.getData();
    Swjgzzjg swjgzzjg = null;
    //�������ݿ�����
    Connection conn = null;
    SfDBAccess sbDB = null;
    SfDBUtils sbDBU = null;
    //UserData����
    UserData ud = (UserData) vo.getUserData();
    //�õ����ش���
    String qxdm1 = InterfaceDj.getQxdm(ud);

    try
    {
      conn = SfDBResource.getConnection();
      sbDB = new SfDBAccess(conn);
      sbDBU = new SfDBUtils(conn);
      Sbjkzb orObj = new Sbjkzb();
      Sbjkmx orMx = new Sbjkmx();
      Sbjkmx orMxTmp = new Sbjkmx();
      Vector condition = new Vector();
      //������ش���
      condition.add("qxdm='" + qxdm1 + "'");
      condition.add("jsjdm='" + form.getJsjdm() + "'");
      condition.add("jkpzh like '" + form.getJkpzh().substring(0, 15)
                    + "%'");
      List orObjList = sbDB.query(Sbjkzb.class, condition);
      //˰�������֯��������
      String swjgzzjgdm = "";
      //���
      String nd = "";
      //��������
      Timestamp cjrq = new Timestamp( (new java.util.Date()).getTime());
      Timestamp now = new Timestamp( (new java.util.Date()).getTime());
      //���ش���
      String qxdm = "";
      String szdm = "";
      String ysjc = "";
      String yskm = "";
      String gjbzhydm = "";
      String djzclxdm = "";
      if (orObjList.size() > 0)
      {
        //ͨ���������ݵõ�˰�������֯������������
        Sbjkzb temp = (Sbjkzb) orObjList.get(0);
        swjgzzjgdm = temp.getSwjgzzjgdm();
        szdm = temp.getSzdm();
        ysjc = temp.getYsjcdm();
        yskm = temp.getYskmdm();
        gjbzhydm = temp.getGjbzhydm();
        djzclxdm = temp.getDjzclxdm();
        nd = temp.getNd();
        //��������
        cjrq = temp.getCjrq();
        //���ش���
        qxdm = temp.getQxdm();
      }
      ArrayList orMxList = form.getDataList();
      ArrayList orList = new ArrayList();
      //��ҳ����ȡ�������ݷ�װ��ormappingֵ������
      JksUtil ju = new JksUtil();
      ResultSet rs = null;
      for (int i = 0; i < orMxList.size(); i++)
      {
        HashMap map = (HashMap) orMxList.get(i);
        orMx = new Sbjkmx();
        BeanUtil.populate(orMx, map);
        orMx.setSwjgzzjgdm(swjgzzjgdm);
        orMx.setNd(nd);
        //���ô�������
        orMx.setCjrq(cjrq);
        //¼������
        orMx.setLrrq(now);
        //���ش���
        orMx.setQxdm(qxdm);
        //
        orMx.setRkje(orMx.getSjse());
        //--//Modified By Ha Zhengze 20040314 Start/////////////////////
        //--����˰��
        rs = sbDB.querySQL("select sl from dmdb.sb_dm_szsm where szsmdm='" +
                           orMx.getSzsmdm() + "'");
        if (rs.next())
        {
          orMx.setSl(rs.getBigDecimal(1));
        }
        rs.close();
        //--�����м��ֳɼ������ֳ�
        Yskm yskmObj = JKAdapter.getInstance().getYskm(orMx.getSzsmdm(),
            djzclxdm,
            gjbzhydm,
            ysjc);
        if (CodeConstant.YSJC_DIFANGJI.equals(ysjc))
        {
          orMx.setSjfc(ju.getFc(orMx.getSjse(), yskmObj.getSjfcbl())); //�����оּ��ֳɽ��
          orMx.setQjfc(ju.getFc(orMx.getSjse(), yskmObj.getQxfcbl())); //�������ؼ��ֳɽ��
        }
        StringBuffer sb = new StringBuffer();
        sb.append(SfTimeUtil.getNowTimestamp() + "�ɿ���ά������-->");
        sb.append("sl:");
        sb.append(orMx.getSl());
        sb.append("|");
        sb.append("Sjfc:");
        sb.append(orMx.getSjfc());
        sb.append("|");
        sb.append("qjfc:");
        sb.append(orMx.getQjfc());
        sb.append("|");
        System.out.println(sb.toString());
        //--//Modified By Ha Zhengze 20040314 end///////////////////////
        orList.add(orMx);
      }
      //ɾ����ϸ���� �������� �������޸ĺ����������
      try
      {
//        sbDB.delete("substr(jkpzh,0,15)='" + form.getJkpzh().substring(0, 15) +
//                    "'", orMx);
        sbDB.delete("jkpzh like '" + form.getJkpzh().substring(0, 15) +
                    "%'", orMx);
        sbDB.insert(orList);
      }
      catch (BaseException ex)
      {
        throw ExceptionUtil.getBaseException(ex);
      }
      //�����޸ĺ�ĺϼ�ֵ ����������
      String tempJkpzh = "";
      BigDecimal tempSjje = new BigDecimal(0);
      //haveMX�ж�һ���ɿ�ƾ֤�� �Ƿ�����ϸ���ݵĴ���
      boolean noneMx = true;
      for (int i = 0; i < orObjList.size(); i++)
      {
        noneMx = true;
        tempSjje = new BigDecimal(0);
        orObj = new Sbjkzb();
        orObj = (Sbjkzb) orObjList.get(i);
        tempJkpzh = orObj.getJkpzh();
        for (int j = 0; j < orList.size(); j++)
        {
          orMx = new Sbjkmx();
          orMx = (Sbjkmx) orList.get(j);

          if (tempJkpzh.equals(orMx.getJkpzh()))
          {
            noneMx = false;
            tempSjje = tempSjje.add(orMx.getSjse());
          } //end if
        } //end for ��ϸѭ��
        orObj.setSjje(tempSjje);
        //��������
        orObj.setRkje(orObj.getSjje());
        try
        {
          //����SJJE
          sbDB.update(orObj);
          //�������������ϸ����ɾ����ɾ�����˵� shit ��ʵ֤������ɾ�� �����ݿ����
          if (noneMx)
          {

            sbDB.delete(orObj);
          }
        } //end try
        catch (BaseException ex1)
        {

          throw ExceptionUtil.getBaseException(ex1);
        } // end catch
      } //end for

      return form;
    }
    catch (Exception e)
    {
      throw ExceptionUtil.getBaseException(e);
    }
    finally
    {
      SfDBResource.freeConnection(conn);
    }

  }

}

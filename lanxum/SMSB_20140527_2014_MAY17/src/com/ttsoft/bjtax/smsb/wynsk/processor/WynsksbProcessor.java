/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Copyright: (C) 2003-2004 �����еط�˰��֣��廪�Ϲ�ɷ����޹�˾����Ȩ����. </p>
 * <p>Company: �廪�Ϲ�ɷ����޹�˾</p>
 */

package com.ttsoft.bjtax.smsb.wynsk.processor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.HashMap;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.sfgl.common.db.util.BeanUtil;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBAccess;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.sfgl.common.util.SfDateUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Wynsksb;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.bjtax.smsb.wynsk.web.WynsksbActionForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * <p>Title: ������˰�ۺϹ���ϵͳ  �걨����-����ģ��</p>
 * <p>Description: ʵ����Ӧ��˰�ѿ��걨ģ�顣</p>
 * @author Shi Yanfeng
 * @version 1.1
 */

public class WynsksbProcessor
    implements Processor {
  public Object process(VOPackage vo) throws BaseException {
    switch (vo.getAction()) {

      case CodeConstant.SMSB_SAVEACTION:
        return doSave(vo);
      case CodeConstant.SMSB_DELETEACTION:
        return doDelete(vo);
      case CodeConstant.SMSB_QUERYACTION:
        return doQuery(vo);
      default:
        return null;
    }
  }

  /**
   * doSave     ����¼������
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   */

  private Object doSave(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    //������form�ֵ�columns һ���ı��������е�����Ϊ��ϸ���ֶ���
    //modified by tangchangfu 2014-04-08 ��˰����˰��Ŀ  �����ֶ� "wssbyydm","wssbyynr"
    String names[] = {
        "jsjdm", "sbrq", "skssksrq", "skssjsrq", "swjgzzjgdm","wssbyydm","wssbyynr"};
    //���UserData
    UserData ud = vo.getUserData();
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    Connection conn = null;
    try {
      //�������ݿ�����
      conn = SfDBResource.getConnection();
      if (this.haveSb(vo, conn)) {
        //����˰���Ѿ����ڱ�����Ӧ��˰���ѣ����걨��¼��
        throw new ApplicationException("����˰���Ѿ����ڱ�����Ӧ��˰���ѣ����걨��¼��");
      }
      Wynsksb orObj = new Wynsksb();
      //��form�ж�Ӧ������Ϣ���浽ֵ����
      BeanUtil.copyBeanToBean(names, form, orObj);
      //�ӵǼǽӿ��л����Ϣ
      HashMap mapDJ = new HashMap();
      try {
        /* start added by huxiaofeng 2005.8.16*/
        //mapDJ = (HashMap) InterfaceDj.getDjInfo(form.getJsjdm(), ud);
        mapDJ = (HashMap) InterfaceDj.getDjInfo_New(form.getJsjdm(), ud);
        /* end added by huxiaofeng 2005.8.16*/
      }
      catch (Exception ex5) {
        ex5.printStackTrace();
        throw ExceptionUtil.getBaseException(ex5);
      }
      //ͨ���Ǽǽӿ�ȡ����˰�������Ϣ

      SWDJJBSJ jbsj = (SWDJJBSJ) mapDJ.get("JBSJ");

      orObj.setJsjdm(form.getJsjdm());
      //orObj.setSbrq();
      //orObj.setSkssksrq();
      //orObj.setSkssjsrq();
      //
      orObj.setSwjgzzjgdm(jbsj.getSwjgzzjgdm());

      //�õ��걨���
      String sbbh = getSbbh(form.getJsjdm());
      orObj.setSbbh(sbbh);
      //¼������
      orObj.setLrrq(now);
      //��������
      orObj.setCjrq(now);
      //¼����
      orObj.setLrr(ud.getYhid());
      orObj.setLrrmc(ud.getYhmc());//add by tangchangfu ��˰����˰��Ŀ 2014-05-13
      //�걨��ʽ����
      orObj.setFsdm(CodeConstant.FSDM_SMSB);
      //������Դ
      orObj.setSjly(CodeConstant.SMSB_SJLY_SBLR);
      orObj.setZsswjgzzjgdm(ud.getSsdwdm());
      //���ش���
      orObj.setQxdm(InterfaceDj.getQxdm(ud));
      //���
      orObj.setNd(String.valueOf(TinyTools.getYear(orObj.getCjrq())));

      SfDBAccess da = new SfDBAccess(conn);
      //add by tangchanhgfu 2014-04-08 ��˰����˰��Ŀ start
      try{
    	  //��˰�걨ԭ��Ϊ����������˰�걨���ݵ��ڱ�ע��Ϣ
    	  if(CodeConstant.SMSB_WYNSKSB_WSSBYY_QT.equals(form.getWssbyydm())){
    		  orObj.setWssbyynr(form.getBz());
    	  }else{
        	  getWssbyyMC(form.getWssbyydm(), orObj, da);
    	  }
    	    System.out.println("##############����˰�걨ԭ����Ϣ����"+orObj.getWssbyydm()+"--"+orObj.getWssbyynr());
      }catch(Exception ex){
    	  ex.printStackTrace();
    	  throw ExceptionUtil.getBaseException(ex, "�����˰�걨��Ϣ����!");
      }
      
      da.insert(orObj);
      
      return form;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
    	ex.printStackTrace();
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

 /**
  * �����˰�걨ԭ��
  * @param form
  * @param orObj
  * @param da
  * @throws BaseException
  * @throws SQLException
  */
private void getWssbyyMC(String wssbyydm, Wynsksb orObj, SfDBAccess da)
		throws BaseException, SQLException {
	
	try{
		
		if(wssbyydm == null || "".equals(wssbyydm)){
			throw new ApplicationException("�����˰�걨ԭ�����");
			
		}
		
		//�����˰�걨ԭ��
		String queryWssbyy="select wssbyydm,wssbyymc, zchbsdm from DMDB.SB_DM_WSSBYY where wssbyydm='"+wssbyydm+"' and wssbyydm !='99'";
		ResultSet rs = da.querySQL(queryWssbyy);
		while(rs.next()){
			orObj.setWssbyynr(rs.getString("wssbyymc"));
		}
		
	}catch(Exception e){
		e.printStackTrace();
		throw new ApplicationException("�����˰�걨ԭ�����");
	}
}

  /**
   * doSave     ɾ��ָ������
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   */

  private Object doDelete(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    Connection conn = null;
    //���UserData
    UserData ud = vo.getUserData();
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      StringBuffer sql = new StringBuffer();
      sql.append("delete from SBDB.SB_JL_WYNSKSB where ");
      sql.append("qxdm='" + InterfaceDj.getQxdm(ud)).append("'");
      sql.append(" and jsjdm='").append(form.getJsjdm()).append("'");
      sql.append(" and sbbh='").append(form.getSbbh()).append("'");
      da.updateSQL(sql.toString());
      
      return form;
    }
    catch (Exception ex) {
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * doSave     ��ѯ¼������
   * @param     vo ҵ�����
   * @return    ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
   * @throws BaseException    ��������������׳��쳣��Ϣ
   */

  private Object doQuery(VOPackage vo) throws BaseException {
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    Connection conn = null;
    //���UserData
    UserData ud = vo.getUserData();
    Timestamp now = new Timestamp( (new java.util.Date()).getTime());
    try {
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      Vector cri = new Vector();
      cri.add("nd='" + String.valueOf(TinyTools.getYear(now)) + "'");
      cri.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      cri.add("jsjdm='" + form.getJsjdm() + "'");
      cri.add("to_char(sbrq,'yyyyMM')='" +
              SfDateUtil.getDate(now).substring(0, 6) + "' order by cjrq desc");
      List ret = da.query(new Wynsksb().getClass(), cri);
      form.setDataList( (ArrayList) ret);
      
      //add by tangchangfu ��˰����˰��Ŀ �����˰�걨ԭ��,������99--����
      List tempList = form.getDataList();
      for(int index =0; index < tempList.size(); index ++){
    	  Wynsksb tmpOrObj = (Wynsksb)tempList.get(index);
    	  if(tmpOrObj != null ){
    		  String wssbyydm = tmpOrObj.getWssbyydm();
    		  String wssbyymc = tmpOrObj.getWssbyynr();
    		  if(wssbyydm != null && !"".equals(wssbyydm) && (wssbyymc == null || "".equals(wssbyymc)))
    		  {
    			  this.getWssbyyMC(wssbyydm, tmpOrObj, da);
    		  }
    		  //���û�л�ȡ���걨ԭ��
    		  wssbyymc = tmpOrObj.getWssbyynr();
    		  if(wssbyymc == null || "".equals(wssbyymc)){
    			  tmpOrObj.setWssbyynr(" ");
    			  
    		  }
    	  }
      }
      //add end
      return form;
    }
    catch (Exception ex) {
      //throw ExceptionUtil.getBaseException(ex, "ϵͳ�����������Ա��ϵ!");
      throw ExceptionUtil.getBaseException(ex);
    }
    finally {
      SfDBResource.freeConnection(conn);
    }

  }

  /**
   * �걨��ŵ�����,�걨��ŵ����ɹ���Ϊ�������������Ϸ������ĵ�ǰʱ���ʮ��λ�ַ���
   * @param jsjdm ���������
   * @return sbbh
   */
  private String getSbbh(String jsjdm) {
    //�õ���ǰʱ��
    long currentTime = System.currentTimeMillis();
    //�ο�ʱ��
    long targetTime = Long.parseLong("1064700000000");
    //�����걨���
    String sbbh = jsjdm + Long.toHexString(currentTime - targetTime);
    return sbbh;
  }

  /**
   * ����Ƿ�������Ӧ��˰���걨��¼
   * @param vo VOPackage
   * @param conn ���ݿ�����
   * @return �Ƿ�������Ӧ��˰���걨��¼
   */
  private boolean haveSb(VOPackage vo, Connection conn) {
    boolean ret = false;
    WynsksbActionForm form = (WynsksbActionForm) vo.getData();
    try {
      //���UserData
      UserData ud = vo.getUserData();
      conn = SfDBResource.getConnection();
      SfDBAccess da = new SfDBAccess(conn);
      Vector cri = new Vector();
      cri.add("nd='" + String.valueOf(form.getSbrq().substring(0, 4)) + "'");
      cri.add("qxdm='" + InterfaceDj.getQxdm(ud) + "'");
      cri.add("jsjdm='" + form.getJsjdm() + "'");
      cri.add("to_char(skssksrq,'yyyyMMdd')='" + form.getSkssksrq() + "'");
      cri.add("to_char(skssjsrq,'yyyyMMdd')='" + form.getSkssjsrq() + "'");
      List list = da.query(new Wynsksb().getClass(), cri);
      if (list.size() > 0) {
        return true;
      }
      else {
        return false;
      }
    }
    catch (Exception ex) {
      return true;
    }
  }
}

package com.ttsoft.bjtax.smsb.jccswh.processor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ttsoft.bjtax.dj.model.dm.SZSM;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.jccswh.vo.JmxzVo;
import com.ttsoft.bjtax.smsb.jccswh.vo.ZjjmsdmVo;
import com.ttsoft.bjtax.smsb.jccswh.web.ZjjmsdmwhForm;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 * �ּܾ���˰����ά�� �ù����Ƕ��ּܾ���˰��Ŀ����ά��
 * 
 * @author tangchangfu 2014-04-22
 * 
 */
public class ZjjmsdmwhProcessor implements Processor {

	/**
	 * ʵ��Processor�ӿ�
	 * 
	 * @param vo
	 *            ҵ�����
	 * @return Object VOPackage������
	 * @throws BaseException
	 *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
	 *             �����쳣�׳���EJB��process��������
	 */

	public Object process(VOPackage vo) throws BaseException {
		// �ش�����
		Object result = null;
		// �ж�VO�Ƿ�Ϊ��
		if (vo == null) {
			throw new NullPointerException();
		}
		// ����Action��ֵ���ò�ͬ��process����
		switch (vo.getAction()) {
				case CodeConstant.SMSB_INIT:
					result = doInit(vo);
					break;
				case CodeConstant.SMSB_QUERYACTION: // ��ѯ
					result = doGetAllData(vo);
					break;
			    case CodeConstant.QUERYONE://��õ�����Ϣ�����޸Ĳ���
			    	  result = doGetOne(vo);
			    	  break;
				case CodeConstant.SMSB_SAVEACTION: // ����
					doSave(vo);
					break;
				case CodeConstant.SMSB_UPDATEACTION: // ����
					doUpdate(vo);
					break;
				default:
					throw new UnsupportedOperationException(
							"Method process() not yet implemented.");
		}
		return result;
	}

	/**
	 * ҳ���ʼ��
	 * 
	 * @param vo
	 * @return
	 */
	private Object doInit(VOPackage vo) throws BaseException {
		Map resMap = new HashMap();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = SfDBResource.getConnection();
			
			//
			ArrayList szList = new ArrayList();
			ArrayList jmxzdlList = new ArrayList();
			ArrayList jmxzxlList = new ArrayList();
			
			//==== 1.��ʼ��˰��
			String querySql = "select szsmdm,  t.szsmdm||'-'||t.szsmmc  ms from dmdb.sb_dm_szsm t where t.ccbs='0' order by t.szsmdm";
			ps = conn.prepareStatement(querySql);
			ResultSet rs = ps.executeQuery();

			SZSM szFirst = new SZSM();
			szFirst.setSzsmdm("");
			szFirst.setSzsmmc("");
			szList.add(szFirst);

			while (rs.next()) {
				SZSM sz = new SZSM();
				sz.setSzsmdm(rs.getString("szsmdm"));
				sz.setSzsmmc(rs.getString("ms"));
				szList.add(sz);
			}
			
			//==== 2.��ʼ���������ʴ���
			rs.close();
			ps.close();
			querySql = "select t.jmxzdm,t.jmxzmc ms from DMDB.KJ_DM_JMXZ t where t.xsbs='1' and length(t.jmxzdm)=4";
			ps = conn.prepareStatement(querySql);
			rs = ps.executeQuery();
			
			JmxzVo dlvo = new JmxzVo();
			dlvo.setJmxzdm("");
			dlvo.setJmxzmc("");
			jmxzdlList.add(dlvo);
			
			while (rs.next()) {
				JmxzVo dlvo1 = new JmxzVo();
				dlvo1.setJmxzdm(rs.getString("jmxzdm"));
				dlvo1.setJmxzmc(rs.getString("ms"));
				jmxzdlList.add(dlvo1);
			}
			

			//==== 3.��ʼ����������С��
			rs.close();
			ps.close();
			querySql = "select t.jmxzdm,t.jmxzmc ms from DMDB.KJ_DM_JMXZ t where t.xsbs='1' and length(t.jmxzdm)!=4";
			ps = conn.prepareStatement(querySql);
			rs = ps.executeQuery();
			
			JmxzVo xlvo = new JmxzVo();
			xlvo.setJmxzdm("");
			xlvo.setJmxzmc("");
			jmxzxlList.add(xlvo);
			
			while (rs.next()) {
				JmxzVo xlvo1 = new JmxzVo();
				xlvo1.setJmxzdm(rs.getString("jmxzdm"));
				xlvo1.setJmxzmc(rs.getString("ms"));
				jmxzxlList.add(xlvo1);
			}
			
			//==== 4.�Ѳ�ѯ�������Map
			resMap.put(CodeConstant.SMSB_MAP_KEY_SZ, szList);
			resMap.put(CodeConstant.SMSB_MAP_KEY_JMXZDL, jmxzdlList);
			resMap.put(CodeConstant.SMSB_MAP_KEY_JMXZXL, jmxzxlList);
			
			rs.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
			// ������ض��׳����쳣
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("ҳ���ʼ��ʧ�ܣ����˳����ԣ�");
		} finally {
			SfDBResource.freeConnection(conn);
		}
		return resMap;
	}
	
	
	/**
	 * ִ�в�ѯ����÷�ҳ����
	 * @param vo
	 * @return
	 */
	private Object doGetAllData(VOPackage vo)throws BaseException{
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
		try{
			//���ݲ�ѯ������ò�ѯ���
			ArrayList  allDataList = this.doQuery(form);
			// ��ȡ��ҳ������
			form.setTotalpage(this.getPageTotalCount(allDataList.size()));
			//ִ�з�ҳ����
			// ��ҳ��ǰ��ʾ�����ݼ�
			ArrayList showDataList = new ArrayList();
			// �����ѯ���
			showDataList = this.getPageDataList(allDataList, form);
			
			form.setQueryList_onePage(showDataList);
			
			System.out.println("��ʾ�������+++++++++++++++++++showDataList###"+showDataList.size());
			
		}catch(Exception e){
			e.printStackTrace();
			// ������ض��׳����쳣
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("ִ�в�ѯʧ�ܣ����˳����ԣ�");
		}
		return form;
	}
	
	/**
	 * ִ�в�ѯ�����ĳ��ָ����Ϣ
	 * @param vo
	 * @return
	 */
	private Object doGetOne(VOPackage vo)throws BaseException{
		ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
		try{
			//���ݲ�ѯ������ò�ѯ���
			ArrayList  allDataList = this.doQuery(form);
			ZjjmsdmVo resVo = (ZjjmsdmVo)allDataList.get(0);
			//form.setVo(resVo);
			form.setAllNewAddInfo(resVo.getReShowStr());
		}catch(Exception e){
			e.printStackTrace();
			// ������ض��׳����쳣
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡָ��������Ϣʧ�ܣ����˳����ԣ�");
		}
		return form;
	}

	/**
	 * ����ǰ̨������������ѯ����ּܾ���˰����ά����Ϣ
	 * 
	 * @param vo
	 * @return
	 */
	private ArrayList doQuery(ZjjmsdmwhForm form)throws BaseException {
		Connection conn = null;// �������ݿ�����
		ArrayList resList = new ArrayList();
		try {
			conn = SfDBResource.getConnection();

			String querySQL = "select * from dmdb.sb_dm_zjqyjmslx where 1=1 "+ this.getQueryConditions(form)+" order by szdm,jmslxdldm,jmslxxldm,jmslxdm";
			System.out.println("��ѯSQL+++++++++++++++++++querySQL###"+querySQL);
			PreparedStatement ps = conn.prepareStatement(querySQL);
			ResultSet rs = ps.executeQuery();// ִ�в�ѯ
			int indx =1;
			while (rs.next()) {
				ZjjmsdmVo dmVo = new ZjjmsdmVo();
				dmVo.setIndexId(indx++);
				dmVo.setJmslxdm(replaceEnterKey2Blank(rs.getString("jmslxdm")));
				dmVo.setWh(replaceEnterKey2Blank(rs.getString("wh")));
				dmVo.setJmslxmc(replaceEnterKey2Blank(rs.getString("jmslxmc")));
				dmVo.setSz(rs.getString("sz"));
				dmVo.setZxbz(rs.getString("zxbz"));
				dmVo.setLrr(rs.getString("lrr"));
				dmVo.setLrrq(rs.getDate("lrrq"));
				dmVo.setJmszcqsnd(replaceEnterKey2Blank(rs.getString("jmszcqsnd")));
				dmVo.setSzdm(rs.getString("szdm"));
				dmVo.setJmslxdldm(rs.getString("JMSLXDLDM"));//����˰���ʹ������
				dmVo.setJmslxxldm(rs.getString("JMSLXXLDM"));//����˰����С�����
				dmVo.setYxbs(rs.getString("YXBS"));////��Ч��ʶ��0-��Ч��9-��Ч��
				dmVo.setBz(replaceEnterKey2Blank(rs.getString("BZ")));//��ע
				dmVo.setLsbh(rs.getString("LSBH"));//��ʷ��ţ���ʼֵΪ0��ÿ�α���Զ���һ��

				//�������
				this.getDescAll(dmVo,conn);
				//
				//���ƴ����Ϣ
				this.getMx2ReShow(dmVo);
				
				resList.add(dmVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ������ض��׳����쳣
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("ִ�в�ѯʧ�ܣ����˳����ԣ�");

		} finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		System.out.println("��ѯ�������+++++++++++++++++++resList###"+resList.size());
		return resList;
	}

	/**
	 * ���ǰ̨��ѯ����
	 * 
	 * @param form
	 * @return
	 */
	private String getQueryConditions(ZjjmsdmwhForm form) throws BaseException{
		StringBuffer whereConditions = new StringBuffer();
		try {
			//����˰���ʹ���
			if(form.getQuery_jmslxdm()!= null && !"".equals(form.getQuery_jmslxdm())){
				whereConditions.append(" and jmslxdm='"+form.getQuery_jmslxdm()+"'");
			}
			//�ĺ�
			if(form.getQuery_wh() != null && !"".equals(form.getQuery_wh())){
				whereConditions.append(" and wh like '%"+form.getQuery_wh()+"%'");
				
			}
			//˰�ִ���
			if(form.getQuery_szdm() != null && !"".equals(form.getQuery_szdm())){
				whereConditions.append(" and szdm='"+form.getQuery_szdm()+"'");
				
			}
			//����˰������ʼ���
			if(form.getQuery_jmszcqsnd() !=null && !"".equals(form.getQuery_jmszcqsnd())){
				whereConditions.append(" and jmszcqsnd='"+form.getQuery_jmszcqsnd()+"'");
			}
			
			//����˰���ʹ������
			if(form.getQuery_jmslxdldm() != null && !"".equals(form.getQuery_jmslxdldm())){
				whereConditions.append(" and jmslxdldm='"+form.getQuery_jmslxdldm()+"'");
			}
			//����˰����С�����
			if(form.getQuery_jmslxxldm() != null && !"".equals(form.getQuery_jmslxxldm())){
				whereConditions.append(" and jmslxxldm='"+form.getQuery_jmslxxldm()+"'");
			}
			
			
			//¼�뿪ʼ����
			if(form.getQuery_lrrqKS() != null && !"".equals(form.getQuery_lrrqKS())){
				whereConditions.append(" and lrrq >=to_date('" + form.getQuery_lrrqKS() + " 00:00:00','yyyymmdd hh24:mi:ss') ");
			}
			
			
			//¼���������
			if(form.getQuery_lrrqJS() != null && !"".equals(form.getQuery_lrrqJS())){
				whereConditions.append(" and lrrq <=to_date('" + form.getQuery_lrrqJS() + " 23:59:59','yyyymmdd hh24:mi:ss')");
			}
			
			
			
			
			//��Ч��ʶ��0-��Ч��9-��Ч��
			if(form.getQuery_yxbs()!= null && !"".equals(form.getQuery_yxbs())){
				//���ѡ����Ч�����Ϊ�յ�Ҳһ���ѯ����
				if("0".equals(form.getQuery_yxbs())){
					whereConditions.append(" and (yxbs='"+form.getQuery_yxbs()+"' or yxbs is null)");
				}else{
					whereConditions.append(" and yxbs='"+form.getQuery_yxbs()+"'");
				}
			}
			
			//���ִ���޸�
			if(form.getActionType() != null && "ShowOne".equals(form.getActionType())){
				if(form.getModifyKey_jmslxdm()!= null && !"".equals(form.getModifyKey_jmslxdm())){
					whereConditions.append(" and jmslxdm='"+form.getModifyKey_jmslxdm()+"'");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			
			// ������ض��׳����쳣
			if (e instanceof BaseException) {
				throw new ApplicationException(e.getMessage());
			}

			throw new ApplicationException("ִ��ʧ�ܣ���ϲ�ѯ����ʧ�ܣ����˳����ԣ�");
		}

		return whereConditions.toString();
	}
	
	/**
	 * �����ϸ��Ϣ������  �޸� �ã�
	 * @param zbVo
	 * @param mxrs
	 */
	private void getMx2ReShow(ZjjmsdmVo resVo)throws BaseException{
		StringBuffer reShowBF = new StringBuffer();//������ϸ���籣��ɹ����� �����޸���Ϣ��
		try{
			if(resVo != null){
				reShowBF.append(replaceNull2Blank(resVo.getSzdm()));//˰��
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxdldm()));//����˰���ʹ������
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxxldm()));//����˰����С�����
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxdm()));//����˰���ʹ���
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmslxmc()));//����˰��������
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getJmszcqsnd()));//���⿪ʼ���
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getWh()));//�ĺ�
				reShowBF.append("~");
				reShowBF.append(replaceNull2Blank(resVo.getBz()));//��ע
				reShowBF.append("^");
			}
			//System.out.println("��ӡ��Ϣ(1)###############"+reShowBF.toString());
			if(reShowBF.length() >0){
				reShowBF = reShowBF.replace(reShowBF.length()-1, reShowBF.length(), "");
			}
			//System.out.println("��ӡ��Ϣ(2)###############"+reShowBF.toString());
			resVo.setReShowStr(reShowBF.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("��ѯ���������˰��Ϣ��ϸ���������ԣ�");
		}
	}
	
	
	/**
	 * ����¼����Ϣ
	 * @param vo
	 */
	private void doSave(VOPackage vo)throws BaseException{
		UserData ud = vo.getUserData();
		Connection conn = null;// �������ݿ�����
		try{
			conn = SfDBResource.getConnection();
			
			String allNewAddInfo="";
			ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
			allNewAddInfo = form.getAllNewAddInfo();
			if(allNewAddInfo != null && !"".equals(allNewAddInfo)){
				List newAddInfoList = this.constructMx2SaveOrUpdate(ud, allNewAddInfo, conn);
				String existsJmslxdms = this.getAllExistsJmslxdms(newAddInfoList, conn);
				if(existsJmslxdms != null && !"".equals(existsJmslxdms)){
					throw new ApplicationException("����ʧ�ܣ����Ѵ��ڼ���˰���ʹ��룺"+existsJmslxdms);
				}
				
				
				if(newAddInfoList != null && newAddInfoList.size()>0){
					String saveSQL = " insert into dmdb.sb_dm_zjqyjmslx " +
							" (JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR,    LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH) values " +
							" (?      , ? , ?      , ? , '0' , ?  , sysdate, ?        , ?   , ?        , ?        , ?   , ? , ?   )";
					PreparedStatement ps = conn.prepareStatement(saveSQL);
					
					for(int index =0; index < newAddInfoList.size(); index ++){
						ZjjmsdmVo temp = (ZjjmsdmVo)newAddInfoList.get(index);
						int tempIndex =1;
						//����˰���ʹ���
						ps.setString(tempIndex++, temp.getJmslxdm());
						//�ĺ�
						ps.setString(tempIndex++, temp.getWh());
						//����˰��������
						ps.setString(tempIndex++, temp.getJmslxmc());
						//˰��
						ps.setString(tempIndex++, temp.getSz());
						//ע����ʶ
						//¼����
						ps.setString(tempIndex++, temp.getLrr());
						//¼������
						//����˰������ʼ���
						ps.setString(tempIndex++, temp.getJmszcqsnd());
						//˰�ִ���
						ps.setString(tempIndex++, temp.getSzdm());
						//����˰���ʹ������
						ps.setString(tempIndex++, temp.getJmslxdldm());
						//����˰����С�����
						ps.setString(tempIndex++, temp.getJmslxxldm());
						//��Ч��ʶ��0-��Ч��9-��Ч��
						ps.setString(tempIndex++, "0");
						//��ע
						ps.setString(tempIndex++, temp.getBz());
						//��ʷ��ţ���ʼֵΪ1��ÿ�α���Զ���һ��
						ps.setString(tempIndex++, "1");
						
						ps.addBatch();
					}
					ps.executeBatch();
				}else{
					throw new ApplicationException("����¼����Ϣʧ��,δ���ǰ̨�ύ������Ϣ�������ԣ�");
				}
			}else{
				throw new ApplicationException("����¼����Ϣʧ��,δ����ύ��Ϣ�������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("����¼����Ϣʧ�ܣ������ԣ�");
			
		}finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
	}
	
	/**
	 * ����ʱ�ж�Jmslxdm�Ƿ���ڣ�������ڣ�����������
	 * @param newAddInfoList
	 * @return
	 */
	private String getAllExistsJmslxdms(List newAddInfoList,Connection conn)throws BaseException{
		String jmslxdms =null;
		
		if(newAddInfoList == null || newAddInfoList.size() == 0 || conn == null){
			throw new ApplicationException("��֤������Ϣʧ�ܣ������ԣ�");
		}
		try{
			PreparedStatement ps = null;
			ResultSet rs = null;
			String querySQL="SELECT 1 FROM DMDB.SB_DM_ZJQYJMSLX WHERE JMSLXDM=? ";
			ps = conn.prepareStatement(querySQL);
			for(int index =0; index < newAddInfoList.size(); index ++){
				ZjjmsdmVo temp = (ZjjmsdmVo)newAddInfoList.get(index);
				if(temp != null){
					ps.setString(1, temp.getJmslxdm());
					rs = ps.executeQuery();
					if(rs.next()){
						if(jmslxdms == null){
							jmslxdms = temp.getJmslxdm();
						}else{
							jmslxdms +=","+ temp.getJmslxdm();
						}
					}
				}
			}
			
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			throw new ApplicationException("��֤������Ϣʧ�ܣ������ԣ�");
		}
		return jmslxdms;
	}
	
	/**
	 * ִ�и���
	 * @param vo
	 * @throws BaseException
	 */
	private void doUpdate(VOPackage vo)throws BaseException{
		Connection conn = null;// �������ݿ�����
		try{
			//��ʼ�����ݿ�����
			conn = SfDBResource.getConnection();
			//���ǰ̨�����Ϣ
			UserData ud = vo.getUserData();
			ZjjmsdmwhForm form = (ZjjmsdmwhForm) vo.getData();
			String updateType = form.getUpdateType();
			//ƴ��ǰ̨�޸���Ϣ
			String allNewAddInfo = form.getAllNewAddInfo();
			ArrayList updateList = new ArrayList();
			ZjjmsdmVo temp=null;
			
			if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
				updateList = this.constructMx2SaveOrUpdate(ud, allNewAddInfo, conn);
				
				if(updateList.size() >0){
					temp = (ZjjmsdmVo)updateList.get(0);
				}
			}
			
			//�޸�ǰ������ʷ
			String modifyKey_jmslxdm = form.getModifyKey_jmslxdm();
			this.save2His(ud, modifyKey_jmslxdm, conn);
			//ִ��ȫ������
			System.out.println("updateType ===="+updateType);
			System.out.println("temp===="+temp);
			System.out.println("++++++++++++++(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)):"+(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)));
			System.out.println();
			System.out.println();
			System.out.println();
			
			
			
			
			if(updateType != null && !"".equals(updateType) &&
					(
						(temp != null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType))||
						(temp == null && CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType))
					)
				)
			{
				StringBuffer updateSQL = new StringBuffer();
				updateSQL.append("UPDATE DMDB.SB_DM_ZJQYJMSLX SET " );
				
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
					//"JMSLXDM=? "+//����˰���ʹ���
					updateSQL.append("WH=?,  " );//�ĺ�
					updateSQL.append("JMSLXMC=?, " );//����˰��������
					updateSQL.append("SZ=?, " );//˰��
					//"ZXBZ=? "+//ע����ʶ
					updateSQL.append("JMSZCQSND=?, " );//����˰������ʼ���
					updateSQL.append("SZDM=?, " );//˰�ִ���
					updateSQL.append("JMSLXDLDM=?, " );//����˰���ʹ������
					updateSQL.append("JMSLXXLDM=?, " );//����˰����С�����
					updateSQL.append("BZ=?, " );//��ע
				}
				//ֻ������Ч��ʶ
				/**
				 * ���±�ʶ�� ���Ϊ�գ�null ��Ĭ��Ϊ��Ч��0��
				 * �������Ϊ��Ч��������Ч��
				 * ���Ϊ��Ч�����Ϊ��Ч
				 */
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_YXBS.equals(updateType)){
					updateSQL.append(" YXBS=decode(nvl(yxbs,0),'0','9','9','0'), " );//��Ч��ʶ��0-��Ч��9-��Ч��
				}
				updateSQL.append("LRR=?, " );//¼����
				updateSQL.append("LRRQ=sysdate, " );//¼������
				updateSQL.append(" LSBH=LSBH+1 " );
				updateSQL.append(" where JMSLXDM=? " );//��ʷ��ţ���ʼֵΪ0��ÿ�α���Զ���һ��
				
				System.out.println("����SQL::::++++++++"+updateSQL.toString());
				PreparedStatement ps = conn.prepareStatement(updateSQL.toString());
				int index=1;
				if(CodeConstant.SMSB_JCXXWH_ZJJMSLXWH_UPDATE_ALL.equals(updateType)){
					//ps.setString(index++, temp.getJmslxdm());//����˰���ʹ���
					ps.setString(index++, temp.getWh());//�ĺ�
					ps.setString(index++, temp.getJmslxmc());//����˰��������
					ps.setString(index++, temp.getSz());//˰��
					//ps.setString(index++, temp.getZxbz());//ע����ʶ
					//ps.setString(index++, x);//¼������
					ps.setString(index++, temp.getJmszcqsnd());//����˰������ʼ���
					ps.setString(index++, temp.getSzdm());//˰�ִ���
					ps.setString(index++, temp.getJmslxdldm());//����˰���ʹ������
					ps.setString(index++, temp.getJmslxxldm());//����˰����С�����
					ps.setString(index++, temp.getBz());//��ע
					//ps.setString(index++, x);//��ʷ��ţ���ʼֵΪ0��ÿ�α���Զ���һ��
				}
				
				ps.setString(index++, ud.getYhmc());//¼����
				//��������
				ps.setString(index++, modifyKey_jmslxdm);
				//ִ�и���
				ps.execute();
			}else{
				throw new ApplicationException("����¼����Ϣʧ��,�޷���ȡǰ̨�������ݣ������ԣ�");
			}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("����¼����Ϣʧ�ܣ������ԣ�");
		}finally {
			// �ͷ����ݿ�����
			SfDBResource.freeConnection(conn);
		}
		
	}
	
	/**
	 * �����ύ��Ϣ
	 * @param ud
	 * @param allNewAddInfo
	 * @return
	 * @throws BaseException
	 */
	private ArrayList constructMx2SaveOrUpdate(UserData ud, String allNewAddInfo,Connection conn)throws BaseException {
		ArrayList SaveInfoList = new ArrayList();
		try{
		String regEx ="[\\^]";//���������^�ֿ�
		String[] splitAllNewAddInfo = allNewAddInfo.split(regEx);

		for (int index = 0; index < splitAllNewAddInfo.length; index++) {

			String[] oneRowInfoArr = new String[] {};
			if (splitAllNewAddInfo[index] != null) {
				System.out.println("ÿ���ֹ�¼����Ϣ################################"+splitAllNewAddInfo[index]);

				oneRowInfoArr = splitAllNewAddInfo[index].split("~");
				if (oneRowInfoArr.length != 0) {
					ZjjmsdmVo temp = new ZjjmsdmVo();
					//˰��
					temp.setSzdm(oneRowInfoArr[0]);
					//�������ʴ���
					temp.setJmslxdldm(oneRowInfoArr[1]);
					//��������С��
					temp.setJmslxxldm(oneRowInfoArr[2]);
					//����˰���ʹ���
					temp.setJmslxdm(replaceEnterKey2Blank(oneRowInfoArr[3]));
					//����˰��������
					temp.setJmslxmc(replaceEnterKey2Blank(oneRowInfoArr[4]));
					//��ʼ���
					temp.setJmszcqsnd(replaceEnterKey2Blank(oneRowInfoArr[5]));
					//�ĺ�
					temp.setWh(replaceEnterKey2Blank(oneRowInfoArr[6]));
					//��ע
					if(oneRowInfoArr.length ==8){
						temp.setBz(replaceEnterKey2Blank(oneRowInfoArr[7]));
					}
					temp.setLrr(ud.getYhmc());

					
					//��ø�������
					this.getDescAll(temp, conn);
					
					//����Ƿ��Ѿ�����
					
					SaveInfoList.add(temp);
					
				}
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡ������Ϣʧ�ܣ������ԣ�");
		}
		return SaveInfoList;
	}
	
	/**
	 * ��ø�������
	 * @param temp
	 */
	private void getDescAll(ZjjmsdmVo temp,Connection conn)throws BaseException{
		if(conn == null || temp == null){
			throw new ApplicationException("��ȡ����������Ϣʧ�ܣ������ԣ�");
		}
		
		try{
			//����˰������
			String qySzmcSQL ="select szsmmc from dmdb.sb_dm_szsm where szsmdm=?";
			PreparedStatement ps = conn.prepareStatement(qySzmcSQL);
			ps.setString(1, temp.getSzdm());
			ResultSet rs = ps.executeQuery();// ִ�в�ѯ
			while(rs.next()){
				temp.setSz(rs.getString("szsmmc"));
			}
			
			//���ؼ������ʣ����ࣩ��������
			rs.close();
			ps.close();
			String qyJmxzDLmcSQL ="select jmxzmc from DMDB.KJ_DM_JMXZ  where xsbs='1' and length(jmxzdm)=4 and jmxzdm=?";
			ps = conn.prepareStatement(qyJmxzDLmcSQL);
			ps.setString(1, temp.getJmslxdldm());
			rs = ps.executeQuery();// ִ�в�ѯ
			while(rs.next()){
				temp.setJmslxdldmmc(rs.getString("jmxzmc"));
			}
			
			//���ؼ������ʣ�С�ࣩ��������
			rs.close();
			ps.close();
			String qyJmxzXLmcSQL ="select jmxzmc  from DMDB.KJ_DM_JMXZ  where xsbs='1' and length(jmxzdm)!=4 and jmxzdm=?";
			ps = conn.prepareStatement(qyJmxzXLmcSQL);
			ps.setString(1, temp.getJmslxxldm());
			rs = ps.executeQuery();// ִ�в�ѯ
			while(rs.next()){
				temp.setJmslxxldmmc(rs.getString("jmxzmc"));
			}
			
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("��ȡ����������Ϣʧ�ܣ������ԣ�");
		}
		
	}
	
	/**
	 * �����޸���Ϣ����ʷ
	 * @param jmslxdm
	 * @return
	 */
	private boolean save2His(UserData ud,String jmslxdm,Connection conn)throws BaseException{
		boolean isSucc = false;
		try{
			if(ud == null || jmslxdm == null || "".equals(jmslxdm) || conn == null){
				throw new ApplicationException("���������Ϣ����ʷ��,��ʼ�������Ϣʧ�ܣ������ԣ�");
			}
			String hisSQL="insert into dmdb.sb_dm_zjqyjmslx_his (JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR, LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH,bgr,bgrq) "+
                                " select JMSLXDM, WH, JMSLXMC, SZ, ZXBZ, LRR, LRRQ, JMSZCQSND, SZDM, JMSLXDLDM, JMSLXXLDM, YXBS, BZ, LSBH lsbh,?,sysdate "+
                                " from dmdb.sb_dm_zjqyjmslx where jmslxdm=? ";
			
			PreparedStatement ps = conn.prepareStatement(hisSQL);
			ps.setString(1, ud.yhmc);
			ps.setString(2, jmslxdm);
			ps.executeUpdate();// ִ�в�ѯ
			
			//ִ�гɹ�
			isSucc = true;
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}
			throw new ApplicationException("���������Ϣ����ʷ��ʧ�ܣ������ԣ�");
		}
		return isSucc;
	}
	
	
	/**
	 * ��ȡҳ��
	 * 
	 * @param rsCount
	 *            ��ѯ�����build
	 * @return ҳ��
	 */
	private String getPageTotalCount(int rsCount) {
		// �������
		String countTotal = "0";
		// ��ʼҵ��
		int pageCount = 0;
		if ((rsCount % CodeConstant.JKS_PG_LENGTH) == 0) {
			pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH);
		} else {
			pageCount = (rsCount / CodeConstant.JKS_PG_LENGTH) + 1;
		}
		countTotal = String.valueOf(pageCount);
		// ����ֵ
		return countTotal;
	}
	
	/**
	 * ��ȡ��ǰ��ҳ���ݼ�
	 * 
	 * @param tmpList
	 *            �������ݼ�
	 * @param pf
	 *            ҳ�����
	 * @return ��ǰ��ҳ���ݼ�
	 */
	private ArrayList getPageDataList(List tmpList, ZjjmsdmwhForm pf) {
		// 1.�������
		ArrayList dataList = new ArrayList();
		// 2.��ʼ������ֵ
		int startIndex = this.getPageStartIndex(pf.getNextPage(), pf
				.getTotalpage());
		int endIndex = this
				.getPageEndIndex(pf.getNextPage(), pf.getTotalpage());
		// 3.��ʼҵ��
		for (int i = startIndex; i < endIndex; i++) {
			if (i < tmpList.size()) {
				dataList.add(tmpList.get(i));
			}
		}
		tmpList = null;
		// 99.����ֵ
		return dataList;
	}
	
	/**
	 * ��ȡ��ǰҳ��ʼindex
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ��ʼindex
	 */
	private int getPageStartIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int start = -1;
		// 2.��ʼҵ��
		start = (iNextPage - 1) * CodeConstant.JKS_PG_LENGTH;
		// 99.����ֵ
		return start;
	}
	
	/**
	 * ��ȡ��ǰҳ����index
	 * 
	 * @param nextPage
	 *            ��һҳ
	 * @param countTotal
	 *            ��ҳ��
	 * @return ����index
	 */
	private int getPageEndIndex(String nextPage, String countTotal) {
		// 1.�������
		int iNextPage = Integer.parseInt(nextPage);
		int iCountTotal = Integer.parseInt(countTotal);
		int end = -1;
		// 2.��ʼҵ��
		end = iNextPage * CodeConstant.JKS_PG_LENGTH;
		// 99.����ֵ
		return end;
	}
	
	/**
	 * ��nullֵת���ɿմ�
	 * @param str
	 * @return
	 */
	private  String replaceNull2Blank(String str){
		if(str == null){
			return "";
		}else{
			return str;
		}
	}
	
	/**
	 * ���ı������滻Ϊ��
	 * @param str
	 * @return
	 */
	private String replaceEnterKey2Blank(String str){
        String dest = "";

        if (str!=null) {

            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
		
	}

}

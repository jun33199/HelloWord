package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.DateUtils;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.SecurityUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * ������˰����غ˶���Ϣ¼�� Processor
 * 
 * @author
 * 
 */
public class ClfswjghdxxlrProcessor extends CommonProcessor {

	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {

		case ActionType.QUERY:
			result = doQuery(vo); // ��ѯ��������Ϣ
			break;

		case ActionType.INSERT:
			result = doInsert(vo); // ����˰����Աȷ����Ϣ
			break;
			
		case ActionType.DELETE:
			doDelete(vo); // ɾ��˰����Աȷ����Ϣ
			break;
			
		default:
			throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}

		return result;
	}
	
	
	private void doDelete(VOPackage vo)throws BaseException{
		ClfswjghdxxlrBo bvo = (ClfswjghdxxlrBo) vo.getData();
		UserData ud = vo.getUserData();
		String sbbh = SecurityUtil.dealwithStringPara(bvo.getSbbh());
		Connection conn = null;
		int oklevel = 0;
		
		try{
			System.out.println("in ClfswjghdxxlrProcessor delete ###########ִ��ɾ��������ɾ���걨���Ϊ��"+sbbh);
			if(sbbh == null || "".equals(sbbh)){
				throw new ApplicationException("ɾ���˶���Ϣ�������걨��ţ�");
				
			}
			
			//����˶���Ϣ
			oklevel = 1;
			Fwhdxx fwhdxx = getFwhdxx(bvo, ud);
			if(fwhdxx == null ){
				throw new ApplicationException("ɾ���˶���Ϣ�����޷�����˶���Ϣ��");
			}
			
			//������ݿ�����
			oklevel = 2;
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("ɾ���˶���Ϣ�����޷�������ݿ����ӣ�");
			}
			
			//ִ��ɾ��
			oklevel = 3;
			DAOFactory.getInstance().getFwhdxxbDAO().delete(fwhdxx, conn);
		}catch(Exception e){
			e.printStackTrace();
			// ����ʧ����Ϣ:����̨ �� ��־
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(),"˰����Ա�˶���Ϣɾ����ClfswjghdxxlrProcessor����������",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
			
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}else{
				switch (oklevel) {						
				case 1:
					throw new ApplicationException("ɾ���˶���Ϣ�����޷�����˶���Ϣ��");				
				case 2:
					throw new ApplicationException("ɾ���˶���Ϣ�����޷�������ݿ����ӣ�");
				case 3:
					throw new ApplicationException("ɾ���˶���Ϣ����");							
				}				
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}
	}
	
	
	

	private Object doInsert(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo bvo = (ClfswjghdxxlrBo) vo.getData();
		UserData ud = vo.getUserData();
		Connection conn = null;
		int oklevel = 0;
		
		String yhid = SecurityUtil.dealwithStringPara(vo.getUserData().getYhid());
		try {
				// �жϱ�����
				oklevel = 1; 
				String[] checkDadaRes = checkData(bvo);
				if (!Boolean.valueOf(checkDadaRes[0]).booleanValue()) {
					throw new ApplicationException("����˰����Ա�˶���Ϣ����!����֤��Ϣ����:"+ checkDadaRes[1]);
				}
			
				//������ݿ�����
				oklevel = 2;
				conn = QSDBUtil.getConnection();
				if(conn == null){
					throw new ApplicationException("����˶���Ϣ�����޷�������ݿ����ӣ�");
				}				
				//���췿�ݺ˶���Ϣ
				oklevel = 3;
				Fwhdxx hdxxVo = getFwhdxx(bvo, ud);
				
				//ִ�б������
				oklevel = 4;
				DAOFactory.getInstance().getFwhdxxbDAO().insert(hdxxVo, conn);

				
				//����޸ĺ�ɾ��Ȩ��
				oklevel = 5;
				boolean hasAuth = this.getAuth(conn," and dyz='"+yhid+"' ");
				bvo.setHasMAuthorise(hasAuth);
				
				// ���ñ���ɹ���־
				bvo.setIsSaved("true");				
				//��ȡ����¥�����¥���ֵ
				ClfswjghdxxlrBo cbo=this.getCjxx(vo);
				bvo.setSzlc_show(cbo.getSzlc_show());
				bvo.setZlc_show(cbo.getZlc_show());
			} catch (Exception e) {
				e.printStackTrace();
				// ����ʧ����Ϣ:����̨ �� ��־
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),"����˶���Ϣ��ClfswjghdxxlrProcessor����������",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");				
				
				//������ض��׳����쳣
				if(e instanceof BaseException){
					throw new ApplicationException(e.getMessage());
				}else{
					switch (oklevel) {						
					case 1:
						throw new ApplicationException("����˶���Ϣ������˱������ݲ�ͨ�����������������Ƿ�����");				
					case 2:
						throw new ApplicationException("����˶���Ϣ�����޷�������ݿ����ӣ����˳����µ�½�����ߺ͹���Ա��ϵ��");
					case 3:
						throw new ApplicationException("����˶���Ϣ��������˶���Ϣ�����������������Ƿ�����");	
					case 4:
						throw new ApplicationException("����˶���Ϣ��������˶���Ϣ�����������������Ƿ�����");		
					case 5:
						throw new ApplicationException("����˶���Ϣ������ò�����Ա�޸�ɾ��Ȩ�޳���");						
					}
				}	
			} finally {
				QSDBUtil.freeConnection(conn);
			}
		return bvo;
	}

	private String[] checkData(ClfswjghdxxlrBo bvo) {

		String isSuccess = "false";

		String[] checkDadaRes = new String[2];
		checkDadaRes[0] = isSuccess;

		// �걨��Ų���Ϊ��
		if (bvo.getSbbh() == null || "".equals(bvo.getSbbh())) {
			checkDadaRes[1] = "�걨��Ų���Ϊ��";
			return checkDadaRes;
		}

		// ��ͬ��Ų���Ϊ��
		if (bvo.getHtbh() == null || "".equals(bvo.getHtbh())) {
			checkDadaRes[1] = "��ͬ��Ų���Ϊ��";
			return checkDadaRes;
		}

		// �����ݻ���([0]��1.0����;[1]: 1.0(��)����)
		if (bvo.getFwrjl() == null || "".equals(bvo.getFwrjl())) {
			checkDadaRes[1] = "�����ݻ��ʲ���Ϊ��";
			return checkDadaRes;

		} else if (!"0".equals(bvo.getFwrjl()) && !"1".equals(bvo.getFwrjl())) {
			checkDadaRes[1] = "�����ݻ���ѡ�����ӦΪ��1.0���� �� 1.0����";
			return checkDadaRes;

		}

		// ס��ʹ��ʱ����ࣨ[2]��3�꣨��������;[1]:5������3������;[0]:5�꣨�������ϣ�

		if (bvo.getZfsjsjfl() == null || "".equals(bvo.getZfsjsjfl())) {
			checkDadaRes[1] = "ס��ʹ��ʱ����಻��Ϊ��!";
			return checkDadaRes;
		} else if (!"0".equals(bvo.getZfsjsjfl())
				&& !"1".equals(bvo.getZfsjsjfl())
				&& !"2".equals(bvo.getZfsjsjfl())) {
			checkDadaRes[1] = "ס��ʹ��ʱ�������д����ȷ��ӦΪ��3�꣨��������   ��  5������3������  �� 5�꣨�������ϣ�!";
			return checkDadaRes;

		}

		// Ӫҵ˰���շ�ʽ(0:����Ӫҵ˰;1:ȫ������Ӫҵ˰:2:˰�������Ӫҵ

		if (bvo.getYyszsfsSubmit() == null || "".equals(bvo.getYyszsfsSubmit())) {
			checkDadaRes[1] = "סӪҵ˰���շ�ʽ����Ϊ��!";
			return checkDadaRes;

		} else if (!"0".equals(bvo.getYyszsfsSubmit())
				&& !"1".equals(bvo.getYyszsfsSubmit())
				&& !"2".equals(bvo.getYyszsfsSubmit())) {
			checkDadaRes[1] = "סӪҵ˰���շ�ʽ��д����ȷ��ӦΪ������Ӫҵ˰  �� ȫ������Ӫҵ˰  ��  ˰�������Ӫҵ!";
			return checkDadaRes;
		}

		// ������ֵ˰���շ�ʽ(0:������������ֵ˰;1:����������ֵ˰;2:ȫ������������ֵ˰)
		bvo.setTdzsszsfs(bvo.getTdzsszsfsSubmit());
		  if(bvo.getTdzsszsfs() == null || "".equals(bvo.getTdzsszsfs())){
			  checkDadaRes[1]="������ֵ˰���շ�ʽ����Ϊ��!"; 
			  return checkDadaRes; 
		  }else if(!"0".equals(bvo.getTdzsszsfs()) && !"1".equals(bvo.getTdzsszsfs())&&!"2".equals(bvo.getTdzsszsfs())
				 &&!"4".equals(bvo.getTdzsszsfs())&&!"5".equals(bvo.getTdzsszsfs())&&!"6".equals(bvo.getTdzsszsfs())){
			  checkDadaRes[1]="������ֵ˰���շ�ʽ��д����ȷ,ӦΪ:������������ֵ˰  ��  ����������ֵ˰ �� ȫ������������ֵ˰ �� �ṩ������Ʊ����������ֵ˰ �� �˶�����������ֵ˰ �� �ṩ������������������ֵ˰"; 
			  return checkDadaRes; 
		  }
		 

		// ��˰����ȷ�Ϸ�ʽ��0:��ͬ�۸�;1:�˶���˰�۸�

		if (bvo.getJssrqrfs() == null || "".equals(bvo.getJssrqrfs())) {
			checkDadaRes[1] = "��˰����ȷ�Ϸ�ʽ����Ϊ��!";
			return checkDadaRes;
		} else if (!"0".equals(bvo.getJssrqrfs())
				&& !"1".equals(bvo.getJssrqrfs())) {
			checkDadaRes[1] = "��˰����ȷ�Ϸ�ʽ��д����ȷ,ӦΪ����ͬ�۸�  �� ��ͬ�۸�";
			return checkDadaRes;
		}

		// ��֤��ȷ
		checkDadaRes[0] = "true";
		checkDadaRes[1] = "";

		return checkDadaRes;

	}

	/**
	 * ִ�в�ѯ����
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfswjghdxxlrBo doQuery(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo resBo = null;
		Connection conn = null;
		
		int oklevel =0;
		
		try {
			//������ݿ�����
			oklevel =1;
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("��ѯ��Ϣ�����޷�������ݿ����ӣ����˳����µ�½�����ߺ�ϵͳ����Ա��ϵ��");
			}			
			// ��ȡ�������ɼ���Ϣ
			oklevel =2;
			resBo = this.getCjxx(vo);
			if(resBo == null ){
				throw new ApplicationException("��ѯ����ͬ�ɼ���Ϣ�������ͬ����Ƿ���ȷ��");
			}
			
			
			String sbbh = SecurityUtil.dealwithStringPara(resBo.getSbbh());
			String yhid = SecurityUtil.dealwithStringPara(vo.getUserData().getYhid());
			
			
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("��ѯ����ͬ�ɼ���Ϣ�������ͬ����Ƿ���ȷ��");
			}

			//��ѯ����
			String condition = "";
			if (sbbh != null && !"".equals(sbbh)) {
				condition = " where sbbh='" + sbbh + "'";
			}
			// ���˰����Ա�˶���Ϣ
			oklevel = 3;
			ArrayList hdxxList = this.getSwryhdxx(conn,condition);
			
			//�������˰��������Ϣ
			oklevel = 4;
			ArrayList mfskxxList = this.getMfskxx(conn,condition);
			//��÷�Ʊ������Ϣ
			oklevel = 5;
			ArrayList mffpdkxxList = this.getMfFpdkxx(conn,condition);
			//���Ȩ����Ϣ
			oklevel = 6;
			boolean hasAuth = this.getAuth(conn," and dyz='"+yhid+"' ");
			//��������걨��ϸ��Ϣ ����ӡ�ã�
			oklevel = 7;
			ArrayList mfsbxxList = this.getMfsbxx(conn,sbbh);
			//���ʵ�ɽ��ϼ�
			oklevel = 8;
			String sjhjje = this.getSjhjje(conn, sbbh);

			// �����Ϣֻ�д���һ�����򱨴�
			if (hdxxList != null && hdxxList.size() > 1) {
				throw new ApplicationException("��ѯ˰����Ա�˶���Ϣ����!��ͬһ�걨��Ŵ��ں˶����д��ڶ�����Ϣ���걨���Ϊ��"+ resBo.getSbbh());
			}

			//���ؽ��
			oklevel = 9;
			if (hdxxList != null && hdxxList.size() == 1) {
				Fwhdxx hdxxVo = (Fwhdxx) hdxxList.get(0);

				//@@1 ���ú˶���Ϣ
				if (hdxxVo != null && resBo != null) {
					this.getClfswjghdxxlrBoByHdxxVo(hdxxVo, resBo);
					resBo.setIsSaved("true");// �úϺ�ͬ��Ϣ�Ѿ������ں�ͬ���
				}
				
				//@@2 ��������˰�����ձ�־
				if(mfskxxList != null && mfskxxList.size() > 0){
					resBo.setHasMfSkzsxx("true");
				}
				
				//@@2 ����������Ʊ������־
				if(mffpdkxxList != null && mffpdkxxList.size() > 0){
					resBo.setHasMfFpdkxx("true");
				}
				
				//@@3 �����޸�Ȩ��
				resBo.setHasMAuthorise(hasAuth);
				
				//@@4 ��������걨��Ϣ
				if(mfsbxxList != null && mfsbxxList.size() != 0){
					resBo.setMfsbxxList(mfsbxxList);
					
				//@@5 ��úϼƽ��
					resBo.setSjhjje(sjhjje);
				}
			} else {
				// ����֤������ ��ʼֵΪ ��������Ȩ֤�����
				if (resBo.getGfzmrq() == null || "".equals(resBo.getGfzmrq())) {
					resBo.setGfzmrq(resBo.getFwsyqztfrq());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			// ����ʧ����Ϣ:����̨ �� ��־
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(),"˰����Ա�˶���Ϣɾ����ClfswjghdxxlrProcessor����������",new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
			
			//������ض��׳����쳣
			if(e instanceof BaseException){
				throw new ApplicationException(e.getMessage());
			}else{
				switch (oklevel) {						
				case 1:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷�������ݿ����ӣ�");				
				case 2:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷���ȡ�������ɼ���Ϣ��");
				case 3:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷����˰����Ա�˶���Ϣ��");			
				case 4:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷��������˰��������Ϣ��");	
				case 5:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷���÷�Ʊ������Ϣ��");	
				case 6:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷����Ȩ����Ϣ��");	
				case 7:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷���������걨��ϸ��Ϣ ��");	
				case 8:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷����ʵ�ɽ��ϼƣ�");	
				case 9:
					throw new ApplicationException("��ѯ�˶���Ϣ�����޷�������ʾ��Ϣ��");						
				}				
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}
		return resBo;
	}

	/**
	 * ���˰����Ա�˶���Ϣ
	 * 
	 * @param condition
	 * @return
	 * @throws BaseException
	 */
	public ArrayList getSwryhdxx(Connection conn,String condition) throws BaseException {
		System.out.println("+++++++++++���˰����Ա�˶���ϢgetSwryhdxx ++++++++++++");
		ArrayList hdxxList = new ArrayList();
		// �жϱ�����
		if (condition != null && !"".equals(condition)) {
			try {
				hdxxList = DAOFactory.getInstance().getFwhdxxbDAO().query(conn, condition);
			} catch (Exception e) {
				e.printStackTrace();
				try {
					throw new ApplicationException("��ȡ˰����Ա�˶���Ϣ����");
				} catch (ApplicationException e1) {
					e1.printStackTrace();
					// ����ʧ����Ϣ:����̨ �� ��־
					Debug.printException(e1);
					throw ExceptionUtil.getBaseException(e1);

				}
			}
		} else {
			throw new ApplicationException("�޷���ȡ˰����Ա�˶���Ϣ���������ѯ������");
		}
		return hdxxList;
	}
	
	/**
	 * �������˰����Ϣ
	 * @return
	 */
	private  ArrayList getMfskxx(Connection conn,String condition)throws BaseException{
		ArrayList skxxList = new ArrayList();
		System.out.println("+++++++++++�������˰����ϢgetMfskxx ++++++++++++");
		// �жϱ�����
		if (condition != null && !"".equals(condition)) {
			try {
				skxxList = DAOFactory.getInstance().getMfsbxxzbSellerDAO().queryMfsbxxzbAllList(conn, condition);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("��ȡ����˰����Ϣ����");
			}
		} else {
			throw new ApplicationException("�޷���ȡ����˰����Ϣ���������ѯ������");
		}
		return skxxList;
	}
	
	
	/**
	 * ��÷�Ʊ������Ϣ
	 * @return
	 */
	private  ArrayList getMfFpdkxx(Connection conn,String condition)throws BaseException{
		ArrayList fpdkxxList = new ArrayList();
		System.out.println("+++++++++++��÷�Ʊ������ϢgetMfFpdkxx ++++++++++++");
		// �жϱ�����
		if (condition != null && !"".equals(condition)) {
			try {
				fpdkxxList = DAOFactory.getInstance().getHtypzdzgxbDAO().query(condition, conn);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("��ȡ��Ʊ������Ϣ����");
			}
		} else {
			throw new ApplicationException("�޷���ȡ��Ʊ������Ϣ���������ѯ������");
		}		
		return fpdkxxList;
	}
	
	
	/**
	 * ����޸ĺ�ɾ��Ȩ��
	 * @return
	 */
	private  boolean getAuth(Connection conn,String condition)throws BaseException{
		System.out.println("+++++++++++����޸ĺ�ɾ��Ȩ��getAuth ++++++++++++");
		boolean hasAuth = false;
		// �жϱ�����
		if (condition != null && !"".equals(condition)) {
			try {
				hasAuth = DAOFactory.getInstance().getFwxxDAO().queryAuth2UpdateAndDelete(conn, condition);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("��ȡ�޸�ɾ��Ȩ��ʧ�ܣ�");
			}
		} else {
			throw new ApplicationException("�޷���ȡ�޸�ɾ��Ȩ�ޣ�");
		}		
		return hasAuth;
	}
	
	/**
	 * �����걨��Ϣ����ӡ��ʾ��
	 * @return
	 */
	private ArrayList getMfsbxx(Connection conn,String sbbh)throws BaseException{
		ArrayList mfsbxxList = new ArrayList();
		// �жϱ�����
		if (sbbh != null && !"".equals(sbbh)) {
			try {
				mfsbxxList = DAOFactory.getInstance().getFwhdxxbDAO().queryMfsbxx(conn, sbbh);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("��ȡ�����걨��Ϣʧ�ܣ�");
			}
		} else {
			throw new ApplicationException("�޷���ȡ�����걨��Ϣ��");
		}		
		return mfsbxxList;
	}
	
	
	/**
	 * �����걨��Ϣ��ʵ�ɽ��ϼ�
	 * @return
	 */
	private String getSjhjje(Connection conn,String sbbh)throws BaseException{
		String sjhjje = "";
		// �жϱ�����
		if (sbbh != null && !"".equals(sbbh)) {
			try {
				sjhjje = DAOFactory.getInstance().getFwhdxxbDAO().queryMfsbSJHJJE(conn, sbbh);

			} catch (Exception e) {
				e.printStackTrace();
					throw new ApplicationException("��ȡ�����걨��Ϣʧ�ܣ�");
			}
		} else {
			throw new ApplicationException("�޷���ȡ�����걨��Ϣ��");
		}		
		return sjhjje;
	}

	/**
	 * ��ô������ɼ���Ϣ
	 * 
	 * @methodName:getCjxx
	 * @function:
	 * 
	 * @param sbbh
	 *            �걨���
	 * @param htbh
	 *            ��ͬ���
	 * @return
	 * @author:�Ʋ���
	 * @create date��2013-5-16 ����03:18:28
	 * @version 1.1
	 * 
	 * 
	 */
	public ClfswjghdxxlrBo getCjxx(VOPackage vo) throws BaseException {
		ClfswjghdxxlrBo data = (ClfswjghdxxlrBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		ClfswjghdxxlrBo resBo = null;
		System.out.println("sbbh................."+sbbh);
		System.out.println("htbh................."+htbh);
		if ((sbbh == null || "".equals(sbbh))
				&& (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("��ȡ�ɼ������޲�ѯ����!");
		}

		// ƴ�Ӳ�ѯSQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");

		if (sbbh != null && !"".equals(sbbh)) {
			sqlBuff.append("and sbbh ='" + sbbh + "'");
		}

		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			
			// ��������Ȩ�޿���
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(vo.getUserData(),
	                "QSDB", "QS_JL_FWXXB" );
	        Debug.out("datafilter: " + datafilter);
	        
			// ��÷�����Ϣ
			List fwxxList = DAOFactory.getInstance().getFwxxDAO()
					.queryFwList(conn, sqlBuff.toString() +" and "+datafilter);

			if (fwxxList.size() > 0) {
				resBo = new ClfswjghdxxlrBo();
			} else {
				throw new ApplicationException("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
			}

			for (int index = 0; index < fwxxList.size(); index++) {
				Fwxx fwxx = (Fwxx) fwxxList.get(index);

				resBo.sbbh = fwxx.sbbh;
				resBo.UNEpiccode = fwxx.ewmsj;
				resBo.bbbs = fwxx.bbbs;
				resBo.htbh = fwxx.htbh;
				resBo.fwcqzh = fwxx.fwcqzh;
				resBo.fwsyqztfrq = DataConvert.TimeStamp2String(fwxx.fwsyqztfrq);
				resBo.fwzlqx = fwxx.fwzlqx;
				resBo.fwzldz = fwxx.fwzldz;
				resBo.fwjzmj = fwxx.fwjzmj + "";
				resBo.jzjgdm = fwxx.jzjgdm;
				resBo.ghyt = fwxx.ghyt;
				resBo.fwqszylx = fwxx.fwqszylx;
				resBo.fwqszylxmc = ActionUtil.getFwqszylxmc(fwxx.fwqszylx);
				resBo.szlc = fwxx.szlc + "/" + fwxx.zcs;
				resBo.szlc_show = fwxx.szlc + "";
				resBo.zlc_show = fwxx.zcs + "";
				resBo.htwsqyrq = DataConvert.TimeStamp2String(fwxx.htwsqyrq);
				resBo.sfwscsssggf = fwxx.sfwscsssggf;
				resBo.htzj = fwxx.htzj + "";
				resBo.bzdm = fwxx.bzdm;
				resBo.bzmc = fwxx.bzmc;
				resBo.hl = fwxx.hl + "";
				resBo.wbje = fwxx.wbje + "";
				resBo.fdczjjgmc = fwxx.fdczjjgmc;
			}
			
			// ���������Ϣ
			List seller_List = DAOFactory.getInstance().getMfgrxxSellerDAO()
					.queryMfgrxxSellerList(conn, sqlBuff.toString());
			StringBuffer sellerBuf = new StringBuffer();
			StringBuffer allSellerNames4jyxxcxBuf = new StringBuffer();
			for (int index = 0; index < seller_List.size(); index++) {
				MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
				mfgrxxSellerItem = (MfgrxxSeller) seller_List.get(index);
				if (index > 0) {
					sellerBuf.append("^");
				}
				sellerBuf.append(mfgrxxSellerItem.getNsrmc());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getLb());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getZjlxdm());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getZjhm());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getQlrfe());
				sellerBuf.append("~");
				sellerBuf.append(mfgrxxSellerItem.getLxdh());

				// ������������������á�/����������������������Ϣ��ѯ��
				if (index == 0) {
					allSellerNames4jyxxcxBuf
							.append(mfgrxxSellerItem.getNsrmc());
				} else {
					allSellerNames4jyxxcxBuf.append("/"
							+ mfgrxxSellerItem.getNsrmc());

				}

			}
			System.out.println("������Ϣ:::::" + sellerBuf.toString());
			resBo.setAll_sellerInfo(sellerBuf.toString());
			resBo.setSellerList(seller_List);
			resBo.setAllSellerNames4jyxxcx(allSellerNames4jyxxcxBuf.toString());

			// �������Ϣ
			List buyer_list = DAOFactory.getInstance().getMfgrxxBuyerDAO()
					.queryMfgrxxBuyerList(conn, sqlBuff.toString());
			StringBuffer buyerBuf = new StringBuffer();
			StringBuffer allBuyerNames4jyxxcxBuf = new StringBuffer();
			for (int index = 0; index < buyer_list.size(); index++) {
				MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
				mfgrxxBuyerItem = (MfgrxxBuyer) buyer_list.get(index);
				if (index > 0) {
					buyerBuf.append("^");
				}
				buyerBuf.append(mfgrxxBuyerItem.getNsrmc());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLb());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjlxdm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjhm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getQlrfe());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLxdh());

				// ������������������á�/����������������������Ϣ��ѯ��
				if (index == 0) {
					allBuyerNames4jyxxcxBuf.append(mfgrxxBuyerItem.getNsrmc());
				} else {
					allBuyerNames4jyxxcxBuf.append("/"
							+ mfgrxxBuyerItem.getNsrmc());

				}
			}
			System.out.println("����Ϣ��������" + buyerBuf.toString());
			resBo.setAll_buyerInfo(buyerBuf.toString());
			resBo.setBuyerList(buyer_list);
			resBo.setAllBuyerNames4jyxxcx(allBuyerNames4jyxxcxBuf.toString());

		} catch (Exception e) {
			e.printStackTrace();
			// ����ʧ����Ϣ:����̨ �� ��־
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "��������Ϣ�ɼ���ClfxxcjProcessor����������",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "ʧ��");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}

	private Fwhdxx getFwhdxx(ClfswjghdxxlrBo bvo, UserData ud) {
		Fwhdxx hdxxVo = new Fwhdxx();
		hdxxVo.setSbbh(bvo.getSbbh());
		hdxxVo.setHtbh(bvo.getHtbh());
		hdxxVo.setSqrxzdz(bvo.getSqrxzdz());
		hdxxVo.setJtwyshyhbz(bvo.getJtwyshyhbz());
		hdxxVo.setFwlxdm(bvo.getFwlxdm());
		hdxxVo.setJcnd(bvo.getJcnd());
		// hdxxVo.setZlc(bvo.getZlc());
		// fwjzmj
		System.out.println("���ݽ������++++++++++" + bvo.getFwjzmj());
		System.out.println("�������++++++++++" + bvo.getHlfy());
		hdxxVo.setFwjzmj(string2BigDecimal(bvo.getFwjzmj()));
		hdxxVo.setYgffpje(string2BigDecimal(bvo.getYgffpje()));
		hdxxVo.setGfzmrq(DataConvert.String2Timestamp(bvo.getGfzmrq()));
		hdxxVo.setTdzzssbfs(bvo.getTdzzssbfs());
		hdxxVo.setQdfcqsje(string2BigDecimal(bvo.getQdfcqsje()));
		hdxxVo.setQdfcyhsje(string2BigDecimal(bvo.getQdfcyhsje()));
		hdxxVo.setQdtdsyqzfje(string2BigDecimal(bvo.getQdtdsyqzfje()));
		hdxxVo.setJfpgjg(string2BigDecimal(bvo.getJfpgjg()));
		hdxxVo.setJgpgfy(string2BigDecimal(bvo.getJgpgfy()));
		hdxxVo.setFdczjjsjdm(bvo.getFdczjjsjdm());
		hdxxVo.setFdczjswdjzh(bvo.getFdczjswdjzh());
		hdxxVo.setFdczjlxdh(bvo.getFdczjlxdh());
		hdxxVo.setFdczjjjr(bvo.getFdczjjjr());
		hdxxVo.setFdczjjjrlxdh(bvo.getFdczjjjrlxdh());
		hdxxVo.setFdczjjjrzjhm(bvo.getFdczjjjrzjhm());
		hdxxVo.setFdczjjjrzgzsh(bvo.getFdczjjjrzgzsh());
		hdxxVo.setCqzbzjzmjfl(bvo.getCqzbzjzmjflSubmit());
		hdxxVo.setMpmjydj(string2BigDecimal(bvo.getMpmjydj()));
		hdxxVo.setPtzfzgxj(string2BigDecimal(bvo.getPtzfzgxj()));
		hdxxVo.setFwrjl(bvo.getFwrjl());
		hdxxVo.setHfbz(bvo.getHfbzSubmit());
		hdxxVo.setZfsjsjfl(bvo.getZfsjsjfl());
		hdxxVo.setYyszsfs(bvo.getYyszsfsSubmit());
		hdxxVo.setGrsdszsfs(bvo.getGrsdszsfsSubmit());
		hdxxVo.setTdzsszsfs(bvo.getTdzsszsfsSubmit());
		hdxxVo.setJssrqrfs(bvo.getJssrqrfs());
		hdxxVo.setJsje(string2BigDecimal(bvo.getJsjeSubmit()));
		hdxxVo.setZfpgjg(string2BigDecimal(bvo.getZfpgjg()));
		hdxxVo.setZfzxfy(string2BigDecimal(bvo.getZfzxfy()));
		hdxxVo.setZfdklx(string2BigDecimal(bvo.getZfdklx()));
		hdxxVo.setSxf(string2BigDecimal(bvo.getSxf()));
		hdxxVo.setGzf(string2BigDecimal(bvo.getGzf()));
		hdxxVo.setHlfy(string2BigDecimal(bvo.getHlfy()));
		hdxxVo.setTdjcdm(bvo.getTdjcdm());
		// hdxxVo.setTdjcmc(string2BigDecimal(bvo.getTdjcmc()));
		hdxxVo.setFwcqzbzzflxdm(bvo.getFwcqzbzzflxdm());
		// hdxxVo.setFwcqzbzzflxmc(bvo.getFwcqzbzzflxmc());
		// hdxxVo.setCqzbzjzmjflSubmit(bvo.getCqzbzjzmjflSubmit());
		// hdxxVo.setHfbzSubmit(bvo.getHfbzSubmit());
		// hdxxVo.setGrsdszsfsSubmit(bvo.getGrsdszsfsSubmit());
		// hdxxVo.setYyszsfsSubmit(bvo.getYyszsfsSubmit());
		// hdxxVo.setJsjeSubmit(bvo.getJsjeSubmit());
		hdxxVo.setLrr(ud.getYhid());
		hdxxVo.setCjr(ud.getYhid());

		hdxxVo.setMpmhdjg(string2BigDecimal(bvo.getMpmhdjg()));// ÿƽ�׺˶��۸�
		hdxxVo.setTdcrj(string2BigDecimal(bvo.getTdcrj()));// ���س��ý�
		// hdxxVo.setJwcxfwyz();//��ί��ѯ����ԭֵ
		hdxxVo.setCjssl(string2BigDecimal(bvo.getCjssl()));// �ǽ�˰˰��
		hdxxVo.setFpszrq(DataConvert.String2Timestamp(bvo.getFpszrq()));// ��Ʊ��������
		hdxxVo.setAnjjse(string2BigDecimal(bvo.getAnjjse()));// ����Ӽ�����

		hdxxVo.setFwszqydm(bvo.getFwszqydm());// ���������������
		hdxxVo.setFwhdlxdm(bvo.getFwhdlxdm());// ���ݺ˶����ʹ���
		hdxxVo.setQszyxsmxdm(bvo.getQszyxsmxdm());// 
		hdxxVo.setYqspfwjsjg(string2BigDecimal(bvo.getYqspfwjsjg()));// 
		hdxxVo.setYhszsfs(bvo.getYhszsfsSubmit());// 
		hdxxVo.setFwszqyje(string2BigDecimal(bvo.getFwszqyje()));//�������������added by zhangj,2014.10.15

		return hdxxVo;
	}

	/**
	 * ͨ�� Fwhdxx ��� ClfswjghdxxlrBo
	 * 
	 * @param hdxxVo
	 * @param bo
	 * @return
	 */
	private ClfswjghdxxlrBo getClfswjghdxxlrBoByHdxxVo(Fwhdxx hdxxVo,
			ClfswjghdxxlrBo bo) {
		DecimalFormat deFormat_00 = new DecimalFormat("#0.00");// �ϼƽ���ʽ
		DecimalFormat deFormat_0000 = new DecimalFormat("#0.0000");// �ϼƽ���ʽ

		bo.setSbbh(hdxxVo.getSbbh());
		bo.setHtbh(hdxxVo.getHtbh());
		bo.setSqrxzdz(hdxxVo.getSqrxzdz());
		bo.setJtwyshyhbz(hdxxVo.getJtwyshyhbz());
		bo.setFwlxdm(hdxxVo.getFwlxdm());
		bo.setJcnd(hdxxVo.getJcnd());
		// bo.setZlc(hdxxVo.getZlc());
		// fwjzmj
		System.out.println("���ݽ������++++++++++" + hdxxVo.getFwjzmj());
		System.out.println("�������++++++++++" + hdxxVo.getHlfy());
		bo.setFwjzmj(deFormat_0000.format(hdxxVo.getFwjzmj()));
		bo.setYgffpje(deFormat_00.format(hdxxVo.getYgffpje()));

		if (hdxxVo.getGfzmrq() != null || !"".equals(hdxxVo.getGfzmrq())) {
			bo.setGfzmrq(DateUtils.getDate(hdxxVo.getGfzmrq()));
		}

		bo.setTdzzssbfs(hdxxVo.getTdzzssbfs());
		bo.setQdfcqsje(deFormat_00.format(hdxxVo.getQdfcqsje()));
		bo.setQdfcyhsje(deFormat_00.format(hdxxVo.getQdfcyhsje()));
		bo.setQdtdsyqzfje(deFormat_00.format(hdxxVo.getQdtdsyqzfje()));
		bo.setJfpgjg(deFormat_00.format(hdxxVo.getJfpgjg()));
		bo.setJgpgfy(deFormat_00.format(hdxxVo.getJgpgfy()));
		bo.setFdczjjsjdm(hdxxVo.getFdczjjsjdm());
		bo.setFdczjswdjzh(hdxxVo.getFdczjswdjzh());
		bo.setFdczjlxdh(hdxxVo.getFdczjlxdh());
		bo.setFdczjjjr(hdxxVo.getFdczjjjr());
		bo.setFdczjjjrlxdh(hdxxVo.getFdczjjjrlxdh());
		bo.setFdczjjjrzjhm(hdxxVo.getFdczjjjrzjhm());
		bo.setFdczjjjrzgzsh(hdxxVo.getFdczjjjrzgzsh());
		bo.setCqzbzjzmjfl(hdxxVo.getCqzbzjzmjfl());
		bo.setMpmjydj(deFormat_00.format(hdxxVo.getMpmjydj()));
		bo.setPtzfzgxj(deFormat_00.format(hdxxVo.getPtzfzgxj()));
		bo.setFwrjl(hdxxVo.getFwrjl());
		bo.setHfbz(hdxxVo.getHfbz());
		bo.setZfsjsjfl(hdxxVo.getZfsjsjfl());
		bo.setYyszsfs(hdxxVo.getYyszsfs());
		bo.setGrsdszsfs(hdxxVo.getGrsdszsfs());
		bo.setTdzsszsfs(hdxxVo.getTdzsszsfs());
		bo.setYhszsfs(hdxxVo.getYhszsfs());
		bo.setJssrqrfs(hdxxVo.getJssrqrfs());
		bo.setJsje(deFormat_00.format(hdxxVo.getJsje()));
		bo.setZfpgjg(deFormat_00.format(hdxxVo.getZfpgjg()));
		bo.setZfzxfy(deFormat_00.format(hdxxVo.getZfzxfy()));
		bo.setZfdklx(deFormat_00.format(hdxxVo.getZfdklx()));
		bo.setSxf(deFormat_00.format(hdxxVo.getSxf()));
		bo.setGzf(deFormat_00.format(hdxxVo.getGzf()));
		bo.setHlfy(deFormat_00.format(hdxxVo.getHlfy()));
		bo.setTdjcdm(hdxxVo.getTdjcdm());
		// bo.setTdjcmc(string2BigDecimal(hdxxVo.getTdjcmc()));
		bo.setFwcqzbzzflxdm(hdxxVo.getFwcqzbzzflxdm());
		// bo.setFwcqzbzzflxmc(hdxxVo.getFwcqzbzzflxmc());
		// bo.setCqzbzjzmjflSubmit(hdxxVo.getCqzbzjzmjflSubmit());
		// bo.setHfbzSubmit(hdxxVo.getHfbzSubmit());
		// bo.setGrsdszsfsSubmit(hdxxVo.getGrsdszsfsSubmit());
		// bo.setYyszsfsSubmit(hdxxVo.getYyszsfsSubmit());
		// bo.setJsjeSubmit(hdxxVo.getJsjeSubmit());

		bo.setMpmhdjg(deFormat_00.format(hdxxVo.mpmhdjg));// ÿƽ�׺˶��۸�
		bo.setTdcrj(deFormat_00.format(hdxxVo.tdcrj));// ���س��ý�
		// bo.setJwcxfwyz(deFormat_00.format(hdxxVo.jwcxfwyz);//��ί��ѯ����ԭֵ
		bo.setCjssl(deFormat_00.format(hdxxVo.cjssl));// �ǽ�˰˰��

		/**
		 * ��Ʊ��������
		 */
		if (hdxxVo.fpszrq != null && !"".equals(hdxxVo.fpszrq)) {
			bo.setFpszrq(DateUtils.getDate(hdxxVo.fpszrq));
		}
		bo.setAnjjse(deFormat_00.format(hdxxVo.anjjse));// ����Ӽ�����
		bo.setFwszqydm(hdxxVo.fwszqydm);// ���������������

		bo.setFwhdlxdm(hdxxVo.getFwhdlxdm());// ���ݺ˶����ʹ���
		bo.setQszyxsmxdm(hdxxVo.getQszyxsmxdm());// 
		bo.setYqspfwjsjg(deFormat_00.format(hdxxVo.getYqspfwjsjg()));// 
		bo.setFwszqyje(deFormat_00.format(hdxxVo.getFwszqyje()));//ADDED BY ZHANGJ,2014.10.15
		
		return bo;
	}

	public BigDecimal string2BigDecimal(String StrJe) {

		if (StrJe == null || "".equals(StrJe)) {
			StrJe = "0.00";

		}
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ

		BigDecimal zje = new BigDecimal(StrJe);// �������ǰ�ĺϼƽ����Ϣ

		return zje;
	}
}

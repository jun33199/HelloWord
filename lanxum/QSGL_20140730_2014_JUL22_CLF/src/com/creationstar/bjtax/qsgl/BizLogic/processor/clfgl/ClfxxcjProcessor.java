package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
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
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: XXXXX Processor
 * </p>
 * 
 * @author �Ʋ���
 * @version 1.1
 */
public class ClfxxcjProcessor extends CommonProcessor {
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case ActionType.INSERT:
			result = doInsertCjxx(vo); // ����ɼ���Ϣ
			break;
		case ActionType.QUERY:
			result = getCjxx(vo); // ��òɼ���Ϣ
			break;	
		case ActionType.UPDATE:
			result = updateCjxx(vo); // �޸Ĳɼ���Ϣ
			break;	
		case ActionType.DELETE:
			deleteCjxx(vo); // ɾ���ɼ���Ϣ	
			break;	
		case ActionType.LOADCODES:
			result = doGetAuth(vo); // ����޸ĺ�ɾ��Ȩ��	
			break;			
		default:
			throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}

		return result;
	}
	
	/**
	 * ����½�û��Ƿ����޸ĺ�ɾ��Ȩ��
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public Object doGetAuth(VOPackage vo) throws BaseException {
		Connection conn = null;
		// ���ǰ̨���ݹ���������
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		UserData ud = vo.getUserData();	
		
		try{
			conn = QSDBUtil.getConnection();
			
			if(conn == null ){
				throw new ApplicationException("��ѯ�޸�Ȩ��ʱ��������ݿ�����ʧ��!");
			}
			String condition =" and dyz='"+SecurityUtil.dealwithStringPara(ud.getYhid())+"' ";
			
			boolean hasAuth = DAOFactory.getInstance().getFwxxDAO().queryAuth2UpdateAndDelete(conn, condition);
			
			System.out.println("�Ƿ����޸�Ȩ��+++++++++1111111111"+hasAuth);
		    data.setHasMAuthorise(hasAuth);
		}catch(Exception e){
			throw new ApplicationException(e.getMessage());
		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		return data;
		
	}
	

	/**
	 * ����ɼ���Ϣ������������Ϣ����������Ϣ��
	 * @methodName:doInsertCjxx
	 * @function:
	 * 
	 * @param vo
	 * @return
	 * @throws BaseException
	 * @author:�Ʋ���
	 * @create date��2013-5-14 ����02:45:43
	 * @version 1.1
	 * 
	 * 
	 */

	private Object doInsertCjxx(VOPackage vo) throws BaseException {
		System.out.println("��ά������1111111+++++++++++++++++");
		Connection conn = null;
		int oklevel = 0;

		// ���ǰ̨���ݹ���������
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		HashMap zjMap = data.getZjMap();
		UserData ud = vo.getUserData();

		try {
			conn = QSDBUtil.getConnection();
			String sbbh = getSbbh(ud, conn);
			String htbh = SecurityUtil.dealwithStringPara(data.htbh);

			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("��������޷�����걨���!");
			}

			if (htbh == null || "".equals(htbh)) {
				throw new ApplicationException("���������ͬ���Ϊ��!");
			}
			
			//�����ͬ����Ѿ����ڣ����׳���ʾ�����ܱ���
			List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, "where htbh ='"+htbh+"'");
			if(fwxxList.size()!=0){
				throw new ApplicationException("��ͬ����Ѵ���!");
			}		
			
			//�����ͬ����Ѿ����ڣ����׳���ʾ�����ܱ���
			fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwList(conn, "where sbbh ='"+SecurityUtil.dealwithStringPara(sbbh)+"'");
			if(fwxxList.size()!=0){
				throw new ApplicationException("�걨����Ѵ���!");
			}			

			//��ò����췿����Ϣ
			oklevel = 1;
			Fwxx fwxx = createFwxx(data, ud, sbbh);
			System.out.println("��ά������1111111+++++++++++++++++"+fwxx.ewmsj);

			// @@2����������Ϣ
			oklevel = 2;
			List sellerList = createSellersInfo(data, zjMap, ud, sbbh);

			// @@3��������Ϣ
			oklevel = 3;
			List buyyerList = createBuyersInfo(data, zjMap, ud, sbbh);

			// ����DAO�㣬д�����ݿ�
			// @@1д�뷿����Ϣ��
			oklevel = 4;
			DAOFactory.getInstance().getFwxxDAO().insert(fwxx, conn);
			// @@2д��������Ϣ��
			oklevel = 5;
			DAOFactory.getInstance().getMfgrxxSellerDAO()
					.InsertMfgrxxSellerList(conn, sellerList);
			// @@3д������Ϣ��
			oklevel = 6;
			DAOFactory.getInstance().getMfgrxxBuyerDAO()
					.InsertMfgrxxBuyerList(conn, buyyerList);
			
			//�����걨��ţ���ѯ��
			data.setSbbh(sbbh);
			vo.setData(data);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
				case 0:
					throw new ApplicationException(ex.getMessage());					
				case 1:
					throw new ApplicationException("����ʧ�ܣ����췿����Ϣ����");				
				case 2:
					throw new ApplicationException("����ʧ�ܣ�����������Ϣ����");
				case 3:
					throw new ApplicationException("����ʧ�ܣ���������Ϣ����");							
				case 4:
					throw new ApplicationException("����ʧ�ܣ����뷿����Ϣ�����!");
				case 5:
					throw new ApplicationException("����ʧ�ܣ��������������!");
				case 6:
						throw new ApplicationException("����ʧ�ܣ���������Ϣ�����!");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// ����ʧ����Ϣ:����̨ �� ��־
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),
						"��������Ϣ�ɼ���ClfxxcjProcessor����������", new StackMsg2StringUtil(
								e, Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
				throw ExceptionUtil.getBaseException(e);
			}

		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		//��ȡ������ʾ��Ϣ
		ClfxxcjBo res = getCjxx(vo);
		res.setSaveIsSuccess("1");//����ɹ���־ 0--ʧ��  1--�ɹ�
		System.out.println("��ά������2222222222+++++++++++++++++");
		return res;
	}
	/**
	 * @@1��ò����췿����Ϣ
	 * @param data
	 * @param ud
	 * @param sbbh
	 * @return
	 */
	private Fwxx createFwxx(ClfxxcjBo data, UserData ud, String sbbh) {
		// @@1��ò����췿����Ϣ
		Fwxx fwxx = new Fwxx();

		fwxx.sbbh = sbbh;
		fwxx.htbh = data.htbh;
		fwxx.fwcqzh = data.fwcqzh;
		fwxx.fwsyqztfrq = DataConvert.String2Timestamp(data.fwsyqztfrq);

		if (data.fwzlqx != null && !"".equals(data.fwzlqx)) {
			fwxx.fwzlqx = data.fwzlqx;
			if (data.fwzlqx.indexOf("undefined") > 0) {
				fwxx.fwzlqx = data.fwzlqx.replaceAll("undefined", "");
			}
		}

		fwxx.fwzldz = data.fwzldz;
		fwxx.fwjzmj = string2BigDecimal(data.fwjzmj);
		fwxx.jzjgdm = data.jzjgdm;
		fwxx.ghyt = data.ghyt;
		fwxx.fwqszylx = data.fwqszylx;

		if (data != null && data.szlc != null && !"".equals(data.szlc)) {
			String[] tempLcArr = new String[] {};
			tempLcArr = data.szlc.split("/");
			//fwxx.szlc = string2BigDecimal(tempLcArr[0]);// ����¥��
			fwxx.szlc = tempLcArr[0];// ����¥��
			fwxx.zcs = string2BigDecimal(tempLcArr[1]);// �ܲ���
		}

		fwxx.htwsqyrq = DataConvert.String2Timestamp(data.htwsqyrq);
		fwxx.sfwscsssggf = data.sfwscsssggf;
		fwxx.htzj = string2BigDecimal(data.htzj);
		fwxx.bzdm = data.bzdm;
		fwxx.bzmc = data.bzmc;
		fwxx.hl = string2BigDecimal(data.hl);
		fwxx.wbje = string2BigDecimal(data.wbje);
		fwxx.fdczjjgmc = data.fdczjjgmc;
		fwxx.bbbs = data.bbbs;
		fwxx.swjgzzjgdm = ud.getSsdwdm();
		if (fwxx.getBbbs() != null && !"".equals(fwxx.getBbbs())) {
			fwxx.sfesf = fwxx.getBbbs().substring(
					fwxx.getBbbs().lastIndexOf("_") - 2,
					fwxx.getBbbs().lastIndexOf("_"));
		}else{
			//Ĭ��Ϊ���ַ�
			fwxx.sfesf = "02";
		}
		fwxx.cjr = ud.getYhid();
		fwxx.lrr = ud.getYhid();
		fwxx.ewmsj = data.UNEpiccode;
		fwxx.fwxzdm = data.fwxzdm;
		fwxx.xxly = data.xxly;
		return fwxx;
	}

	/**
	 * @@2����������Ϣ
	 * @param data
	 * @param zjMap
	 * @param ud
	 * @param sbbh
	 * @return
	 * @throws ApplicationException
	 */
	private List createSellersInfo(ClfxxcjBo data, HashMap zjMap, UserData ud,
			String sbbh) throws ApplicationException {
		//@@2����������Ϣ
		String regEx ="[\\^]";//���������^�ֿ�
		String allSellerInfo = data.getAll_sellerInfo();
		if (allSellerInfo == null || "".equals(allSellerInfo)) {
			throw new ApplicationException("������ϢΪ�գ�����ʧ��!");
		}
		List sellerList = new ArrayList();

		System.out.println("����������Ϣ����"+allSellerInfo);
		String[] splitAllSellerInfoArr = allSellerInfo.split(regEx);

		for (int index = 0; index < splitAllSellerInfoArr.length; index++) {

			String[] oneSellerInfoArr = new String[] {};
			if (splitAllSellerInfoArr[index] != null) {
				System.out.println("����������Ϣ����"+splitAllSellerInfoArr[index]);

				oneSellerInfoArr = splitAllSellerInfoArr[index].split("~");
				if (oneSellerInfoArr.length != 0) {
					MfgrxxSeller oneSellerInfo = new MfgrxxSeller();

					oneSellerInfo.sbbh = sbbh;
					oneSellerInfo.htbh = data.htbh;
					oneSellerInfo.sxh = String.valueOf(index + 1);
					oneSellerInfo.nsrmc = oneSellerInfoArr[0];
					oneSellerInfo.lb = oneSellerInfoArr[1];
					oneSellerInfo.zjlxdm = oneSellerInfoArr[2];
					oneSellerInfo.zjhm = oneSellerInfoArr[3];
					oneSellerInfo.qlrfe = oneSellerInfoArr[4];
					oneSellerInfo.lxdh = oneSellerInfoArr[5];
					
					oneSellerInfo.zjlxmc =((Zjlx)zjMap.get(oneSellerInfo.zjlxdm)).getZjlxmc();
					oneSellerInfo.cjr = ud.getYhid();
					oneSellerInfo.lrr = ud.getYhid();
					oneSellerInfo.setSwjgzzjgdm(ud.getSsdwdm());

					sellerList.add(oneSellerInfo);
				}
			}
		}
		return sellerList;
	}
	
	/**
	 * @@3��������Ϣ
	 * @param data
	 * @param zjMap
	 * @param ud
	 * @param sbbh
	 * @return
	 * @throws ApplicationException
	 */

	private List createBuyersInfo(ClfxxcjBo data, HashMap zjMap, UserData ud,
			String sbbh) throws ApplicationException {
		//@@3��������Ϣ
		String regEx ="[\\^]";//���������^�ֿ�
		String allBuyerInfo = data.getAll_buyerInfo();
		List buyyerList = new ArrayList();
		if (allBuyerInfo == null || "".equals(allBuyerInfo)) {
			throw new ApplicationException("����ϢΪ�գ�����ʧ��!");
		}

		System.out.println("��������Ϣ"+allBuyerInfo);
		String[] splitAllBuyerInfoArr = allBuyerInfo.split(regEx);

		for (int index = 0; index < splitAllBuyerInfoArr.length; index++) {

			String[] oneBuyerInfoArr = new String[] {};
			if (splitAllBuyerInfoArr[index] != null) {
				System.out.println("��������Ϣ"+index+"-->"+splitAllBuyerInfoArr[index]);

				oneBuyerInfoArr = splitAllBuyerInfoArr[index].split("~");
				if (oneBuyerInfoArr.length != 0) {
					MfgrxxBuyer oneBuyerInfo = new MfgrxxBuyer();

					oneBuyerInfo.sbbh = sbbh;
					oneBuyerInfo.htbh = data.htbh;
					oneBuyerInfo.sxh = String.valueOf(index + 1);
					oneBuyerInfo.nsrmc = oneBuyerInfoArr[0];
					oneBuyerInfo.lb = oneBuyerInfoArr[1];
					oneBuyerInfo.zjlxdm = oneBuyerInfoArr[2];
					oneBuyerInfo.zjhm = oneBuyerInfoArr[3];
					oneBuyerInfo.qlrfe = oneBuyerInfoArr[4];
					oneBuyerInfo.lxdh = oneBuyerInfoArr[5];
					oneBuyerInfo.zjlxmc = ((Zjlx)zjMap.get(oneBuyerInfo.zjlxdm)).getZjlxmc();
					oneBuyerInfo.cjr = ud.getYhid();
					oneBuyerInfo.lrr = ud.getYhid();
					oneBuyerInfo.setSwjgzzjgdm(ud.getSsdwdm());
					buyyerList.add(oneBuyerInfo);
				}
			}
		}
		return buyyerList;
	}


	public BigDecimal string2BigDecimal(String StrJe) {

		if (StrJe == null || "".equals(StrJe)) {
			StrJe = "0.00";

		}
		// DecimalFormat deFormat = new DecimalFormat("#0.00");// �ϼƽ���ʽ

		BigDecimal zje = new BigDecimal(StrJe);// �������ǰ�ĺϼƽ����Ϣ

		return zje;
	}

	/**
	 * ��òɼ���Ϣ
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
	public ClfxxcjBo getCjxx(VOPackage vo) throws BaseException {
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		ClfxxcjBo resBo = new ClfxxcjBo();
		UserData ud = vo.getUserData();
		
		if ((sbbh == null || "".equals(sbbh)) && (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("��ȡ�ɼ������޲�ѯ����!");
		}
		
		//ƴ�Ӳ�ѯSQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");
		
		if (sbbh != null && !"".equals(sbbh)){
			sqlBuff.append("and sbbh ='" + sbbh + "'");
		}

		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}

		Connection conn = null;
		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			//����޷���ȡʵ��
			if(dao == null){
				throw new ApplicationException("��òɼ���Ϣ�����޷�����DAOFactoryʵ��!");
				
			}	
			
			//System.out.println("++++++++++++������λ����++++++++++"+vo.getUserData().getSsdwdm());
			// ��������Ȩ�޿���
	        String datafilter = ZKAdapter.getInstance().getDataFilterString(ud,"QSDB", "QS_JL_FWXXB" );
	        //Debug.out("datafilter: " + datafilter);
	        
			
			// ��÷�����Ϣ
			List fwxxList = dao.getFwxxDAO().queryFwList(conn, sqlBuff.toString()+" and "+datafilter);
			
			
			if(fwxxList == null || fwxxList.size() == 0){
				throw new ApplicationException("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ������û��Ȩ�޲鿴�òɼ���Ϣ!");
			}
			
			if(fwxxList.size() > 1){
				throw new ApplicationException("��ѯ����ͬʱ���ڶ�����ͬ��ͬ��Ż����걨��ŵķ�����Ϣ!");
			}
			
			//��÷�����Ϣ
				Fwxx fwxx = (Fwxx) fwxxList.get(0);
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
				resBo.fwqszylxmc=ActionUtil.getFwqszylxmc(fwxx.fwqszylx);
				resBo.szlc = fwxx.szlc + "/" + fwxx.zcs;
				resBo.htwsqyrq = DataConvert.TimeStamp2String(fwxx.htwsqyrq);
				resBo.sfwscsssggf = fwxx.sfwscsssggf;
				resBo.htzj = fwxx.htzj + "";
				resBo.bzdm = fwxx.bzdm;
				resBo.bzmc = fwxx.bzmc;
				resBo.hl = fwxx.hl + "";
				resBo.wbje = fwxx.wbje + "";
				resBo.fdczjjgmc = fwxx.fdczjjgmc;
				resBo.fwxzdm = fwxx.fwxzdm;
				resBo.xxly = fwxx.xxly;


			// ���������Ϣ
			List seller_List = dao.getMfgrxxSellerDAO().queryMfgrxxSellerList(conn, sqlBuff.toString());
			StringBuffer sellerBuf = new StringBuffer();
			StringBuffer allSellerNames4jyxxcxBuf = new StringBuffer();
			for(int index =0; index < seller_List.size();index ++){
				MfgrxxSeller mfgrxxSellerItem = new MfgrxxSeller();
				mfgrxxSellerItem = (MfgrxxSeller)seller_List.get(index);
				if(index >0){
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
				
				//������������������á�/����������������������Ϣ��ѯ��
				if(index == 0){
					allSellerNames4jyxxcxBuf.append(mfgrxxSellerItem.getNsrmc());
				}else{
					allSellerNames4jyxxcxBuf.append("/"+mfgrxxSellerItem.getNsrmc());
					
				}
				
			}
			//System.out.println("������Ϣ:::::"+sellerBuf.toString());
			resBo.setAll_sellerInfo(sellerBuf.toString());
			resBo.setSellerList(seller_List);
			resBo.setAllSellerNames4jyxxcx(allSellerNames4jyxxcxBuf.toString());
			
			// �������Ϣ
			List buyer_list = dao.getMfgrxxBuyerDAO().queryMfgrxxBuyerList(conn, sqlBuff.toString());
			StringBuffer buyerBuf = new StringBuffer();
			StringBuffer allBuyerNames4jyxxcxBuf = new StringBuffer();
			for(int index =0;index < buyer_list.size();index ++){
				MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
				mfgrxxBuyerItem = (MfgrxxBuyer)buyer_list.get(index);
				if(index >0){
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
				
				//������������������á�/����������������������Ϣ��ѯ��
				if(index == 0){
					allBuyerNames4jyxxcxBuf.append(mfgrxxBuyerItem.getNsrmc());
				}else{
					allBuyerNames4jyxxcxBuf.append("/"+mfgrxxBuyerItem.getNsrmc());
					
				}
			}
			//System.out.println("����Ϣ��������"+buyerBuf.toString());
			resBo.setAll_buyerInfo(buyerBuf.toString());
			resBo.setBuyerList(buyer_list);
			resBo.setAllBuyerNames4jyxxcx(allBuyerNames4jyxxcxBuf.toString());
			
			//�Ƿ���˰����Ա�˶���Ϣ
			if(dao.getFwhdxxbDAO().hasExists(fwxx.sbbh, conn)){
				resBo.setHasHdxx("Y");
				//System.out.println("######@@@@@@@@@@@@@@@@@@@@@@@@@�к˶���Ϣ");
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
			// ����ʧ����Ϣ:����̨ �� ��־
			Debug.printException(e);
			ErrorLog.makeLog(vo.getUserData(), "��������Ϣ��ѯ��ClfxxcjProcessor����������",
					new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
							.getStackMsg(), "ʧ��");
			throw ExceptionUtil.getBaseException(e);
		} finally {
			QSDBUtil.freeConnection(conn);
		}

		return resBo;

	}
	

	
	/**
	 * �޸�
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfxxcjBo updateCjxx(VOPackage vo)throws BaseException{
		Connection conn = null;
		int oklevel = 0;

		// ���ǰ̨���ݹ���������
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		HashMap zjMap = data.getZjMap();
		UserData ud = vo.getUserData();

		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			String sbbh = SecurityUtil.dealwithStringPara(data.sbbh);
			String htbh = SecurityUtil.dealwithStringPara(data.htbh);

			//����޷���ȡʵ��
			if(dao == null){
				throw new ApplicationException("ɾ���ɼ���Ϣ�����޷�����DAOFactoryʵ��!");
				
			}
			
			//��ͬ���Ϊ��
			if (htbh == null || "".equals(htbh)) {
				throw new ApplicationException("�޸ĳ�����ͬ���Ϊ��!");
			}
			
			//�걨���Ϊ��
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("�޸ĳ����걨���Ϊ��!");
			}			
			
			//�޸�ǰ��ȡԭʼ��Ϣ
			List fwxxList = dao.getFwxxDAO().queryFwList(conn, "where sbbh ='"+sbbh+"'");
			if(fwxxList != null && fwxxList.size()!=0){
				if(fwxxList.size() == 1){
					//������걨���ֻ��ѯ��һ����¼�����������ͬ��Ž��в�ѯ
					fwxxList = dao.getFwxxDAO().queryFwList(conn, "where htbh ='"+htbh+"'");
					//ֻ��һ��
					if(fwxxList != null && fwxxList.size() ==1){
						Fwxx fwxx = new Fwxx();
						fwxx = (Fwxx)fwxxList.get(0);
						
						//��ü�¼���걨���
						String tempSbbh = fwxx.getSbbh();
						
						//���tempSbbh ��  data.sbbh ��һ�£��򱨴�
						if(tempSbbh != null && !"".equals(tempSbbh) && !tempSbbh.equals(sbbh)){
							throw new ApplicationException("�޸�ʧ�ܣ��ú�ͬ�����ϵͳ���Ѿ����ڣ�����!");
						}
					}else if(fwxxList.size() > 1){
						throw new ApplicationException("�޸�ǰ��ȡԭʼ�ɼ���Ϣ������ͬ����ظ�,�ظ���ͬ���Ϊ��"+data.htbh+"!");
					}
				}else{
					throw new ApplicationException("�޸�ǰ��ȡԭʼ�ɼ���Ϣ�����걨����ظ����ظ��걨���Ϊ:"+data.sbbh+"!");
				}
			}else{
				throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷����ԭʼ�ɼ���Ϣ!");
				
			}
			
			//�鿴�Ƿ��к˶���Ϣ�����������ʾ�������޸�
			if(dao.getFwhdxxbDAO().hasExists(sbbh, conn)){
				throw new ApplicationException("�Ѿ����ں˶���Ϣ�����ܽ����޸�!");
			}
			
			

			//@@1.��ò����췿����Ϣ
			oklevel = 1;
			Fwxx fwxx = createFwxx(data, ud, sbbh);

			//@@2.����������Ϣ
			oklevel = 2;
			List sellerList = createSellersInfo(data, zjMap, ud, sbbh);

			//@@3.��������Ϣ
			oklevel = 3;
			List buyyerList = createBuyersInfo(data, zjMap, ud, sbbh);
			
			//@@4.����ǰ���޸���Ϣ���浽��ʷ��
			oklevel = 4;
			saveCjxx2his(sbbh, "", dao, conn);
			
			//@@5.���·�����Ϣ��
			oklevel = 5;
			dao.getFwxxDAO().update(fwxx, conn);
			
			//@@6.ɾ������Ϣ
			oklevel = 6;
			dao.getMfgrxxBuyerDAO().delBuyyerInfo(sbbh, conn);
			//@@7.ɾ��������Ϣ
			oklevel = 7;
			dao.getMfgrxxSellerDAO().delSellerInfo(sbbh, conn);
			
			
			//@@8.д��������Ϣ��
			oklevel = 8;
			dao.getMfgrxxSellerDAO().InsertMfgrxxSellerList(conn, sellerList);
			// @@9д������Ϣ��
			oklevel = 9;
			dao.getMfgrxxBuyerDAO().InsertMfgrxxBuyerList(conn, buyyerList);
			
			//�����걨��ţ���ѯ��
			data.setSbbh(sbbh);
			vo.setData(data);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
				case 0:
					throw new ApplicationException(ex.getMessage());	
				case 1:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷����췿����Ϣ!");					
				case 2:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷�����������Ϣ!");
				case 3:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷���������Ϣ");
				case 4:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷������޸���Ϣ����ʷ��!");					
				case 5:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷��޸ķ�����Ϣ!");
				case 6:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷��޸�����Ϣ!");
				case 7:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷��޸�������Ϣ!");					
				case 8:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷��޸�������Ϣ!");
				case 9:
					throw new ApplicationException("�޸Ĳɼ���Ϣ�����޷��޸�����Ϣ!");	
				default:
					throw new ApplicationException(ex.getMessage());
						
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// ����ʧ����Ϣ:����̨ �� ��־
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(),
						"��������Ϣ�޸ģ�ClfxxcjProcessor����������", new StackMsg2StringUtil(
								e, Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
				throw ExceptionUtil.getBaseException(e);
			}

		} finally {
			QSDBUtil.freeConnection(conn);
		}
		
		//��ȡ������ʾ��Ϣ
		ClfxxcjBo res = getCjxx(vo);
		res.setSaveIsSuccess("1");//����ɹ���־ 0--ʧ��  1--�ɹ�
		return res;
	}
	
	
	/**
	 * ɾ���ɼ���Ϣ
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public void deleteCjxx(VOPackage vo)throws BaseException{
		ClfxxcjBo data = (ClfxxcjBo) vo.getData();
		String sbbh = SecurityUtil.dealwithStringPara(data.getSbbh());
		String htbh = SecurityUtil.dealwithStringPara(data.getHtbh());
		
		if ((sbbh == null || "".equals(sbbh)) && (htbh == null || "".equals(htbh))) {
			throw new ApplicationException("ɾ��ǰ��ȡ�ɼ������޲�ѯ����!");
		}
		
		Connection conn = null;
		int oklevel = 0;
		try {
			conn = QSDBUtil.getConnection();
			DAOFactory dao = DAOFactory.getInstance();
			//����޷���ȡʵ��
			if(dao == null){
				throw new ApplicationException("ɾ���ɼ���Ϣ�����޷�����DAOFactoryʵ��!");
				
			}
			
			//�鿴�Ƿ��к˶���Ϣ�����������ʾ������ɾ��
			if(dao.getFwhdxxbDAO().hasExists(sbbh, conn)){
				throw new ApplicationException("�Ѿ����ں˶���Ϣ�����ܽ���ɾ��!");
			}
			
			
			//������Ϣ����ʷ��
			saveCjxx2his(sbbh,vo.getUserData().getYhid(), dao, conn);
			
			//ִ��ɾ��
			//@@1.ɾ������Ϣ
			oklevel = 1;
			dao.getMfgrxxBuyerDAO().delBuyyerInfo(sbbh, conn);
			
			//@@2.ɾ��������Ϣ
			oklevel = 2;
			dao.getMfgrxxSellerDAO().delSellerInfo(sbbh, conn);
			
			//@@3.ɾ��������Ϣ
			oklevel = 3;
			dao.getFwxxDAO().delFwxx(sbbh, conn);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			try {
				switch (oklevel) {	
					case 0:
						throw new ApplicationException(ex.getMessage());				
					case 1:
						throw new ApplicationException("ɾ������Ϣ����!");
					case 2:
						throw new ApplicationException("ɾ��������Ϣ����!");
					case 3:
						throw new ApplicationException("ɾ��������Ϣ����!");						
				}
			} catch (ApplicationException e) {
				
				e.printStackTrace();
				// ����ʧ����Ϣ:����̨ �� ��־
				Debug.printException(e);
				ErrorLog.makeLog(vo.getUserData(), "��������Ϣɾ����ClfxxcjProcessor����������",
						new StackMsg2StringUtil(e, Constants.STACK_MSG_CAP)
				.getStackMsg(), "ʧ��");
				throw ExceptionUtil.getBaseException(e);
			}
		} finally {
			QSDBUtil.freeConnection(conn);
		}			
	}
	
	/**
	 * �Ѳɼ���Ϣ��ŵ���ʷ������������Ϣ����������Ϣ��
	 * @param sbbh
	 * @param htbh
	 * @param conn
	 * @param scrydm ɾ����Ա����
	 * @return
	 */
	private boolean saveCjxx2his(String sbbh,String scrydm,DAOFactory dao, Connection conn)throws BaseException{
		boolean isSuccess = false;
		int oklevel = 0;
		try {
			//��ֹSQLע��
			sbbh = SecurityUtil.dealwithStringPara(sbbh);
			scrydm = SecurityUtil.dealwithStringPara(scrydm);
		
		//@@1.��������Ϣ����ʷ��
			dao.getMfgrxxBuyerDAO().saveBuyyerInfo2his(sbbh, scrydm, conn);
		
		//@@2.����������Ϣ����ʷ��
			oklevel = 1;
			dao.getMfgrxxSellerDAO().saveSellerInfo2his(sbbh, scrydm, conn);
		
		//@@3.���淿����Ϣ����ʷ��
			oklevel = 2;
			dao.getFwxxDAO().saveFwxx2his(sbbh, scrydm, conn);
			
			isSuccess = true;	
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				switch (oklevel) {	
					case 0:
						throw new ApplicationException("��������Ϣ����ʷ�����!");				
					case 1:
						throw new ApplicationException("����������Ϣ����ʷ�����!");
					case 2:
						throw new ApplicationException("���淿����Ϣ����ʷ�����!");
				}
			} catch (ApplicationException e) {
				e.printStackTrace();
				// ����ʧ����Ϣ:����̨ �� ��־
				Debug.printException(e);
				throw ExceptionUtil.getBaseException(e);
			}
		}
		return isSuccess;
	}
	
	

	/*
	 * public BigDecimal string2BigDecimal4MJ(String StrMj) {
	 * 
	 * if (StrMj == null || "".equals(StrMj)) { StrMj = "0.000";
	 * 
	 * } DecimalFormat deFormat = new DecimalFormat("#0.000");// �ϼƽ���ʽ
	 * 
	 * BigDecimal zje = new BigDecimal(deFormat.format(StrMj));// �������ǰ�ĺϼƽ����Ϣ
	 * 
	 * return zje; }
	 */

	private String getSbbh(UserData ud, Connection conn) throws Exception {
		if (ud == null || conn == null) {
			throw new ApplicationException("��ȡ�걨��ų���!");
		}

		// ��ü���˰����صļ��������
		String swjgzzjgdm = SecurityUtil.dealwithStringPara(ud.getSsdwdm());
		String swjgJsjdm = null;

		PreparedStatement ps = null;
		CallableStatement proc = null;

		String sbbh = null;

		try {
			// ��ȡ���������
			String sql = "select jsjdm from DMDB.GY_DM_SWJGZZJG where (zxbs is null or zxbs ='0') and SWJGZZJGDM ='"
					+ swjgzzjgdm + "'";
			Debug.out("��ѯ��λ���������SQL:" + sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				swjgJsjdm = rs.getString("jsjdm");
			}
			rs = null;

			// �����ѯ�������������
			if (swjgJsjdm == null) {
				throw new ApplicationException("��ȡ��λ�������������õ�λδ�Ǽǻ����Ѿ�ע��!");
			}

			/*
			 * //��ȡ�걨��� proc =
			 * conn.prepareCall("{?=call jkdb.SB_PKG_TOOLS.getSBBH(?) }");
			 * proc.registerOutParameter(1, Types.VARCHAR); proc.setString(2,
			 * swjgJsjdm);
			 * 
			 * //ִ�� proc.execute();
			 * 
			 * //rs = proc.getResultSet();
			 * System.out.println("++++++++++++++++++++++++"+proc.getString(1));
			 */

			String sbbhSql = "select jkdb.SB_PKG_TOOLS.getSBBH('" + swjgJsjdm
					+ "') from dual";

			ps = conn.prepareStatement(sbbhSql);
			rs = ps.executeQuery();

			while (rs.next()) {
				sbbh = rs.getString(1);
				//System.out.println("++�����걨���Ϊ+++" + sbbh);

			}

			// sbbh = proc.getString("");

		} catch (Exception exp) {
			exp.printStackTrace();
			throw new ApplicationException("��ȡ�걨��ų���!");
		} finally {
			// proc.close();
			ps.close();
		}

		return sbbh;

	}

}

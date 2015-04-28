package com.creationstar.bjtax.qsgl.BizLogic.processor.clfgl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.processor.CommonProcessor;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.ClfjyxxCX;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwhdxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfsbxxzbSeller;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfjyxxCXBO;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfxxcjBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
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
 * ������������Ϣ��ѯ
 * @author admin
 *
 */
public class ClfjyxxCXProcessor extends CommonProcessor  {
	public Object process(VOPackage vo) throws BaseException {
		Object result = null;

		if (vo == null) {
			throw new NullPointerException();
		}

		switch (vo.getAction()) {
		case ActionType.QUERY:
			result = doQuery(vo); // ��ѯ������������Ϣ
			break;
		default:
			throw new ApplicationException("ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
		}

		return result;
	}

	
	
	/**
	 * ������������Ϣ��ѯ
	 * @param vo
	 * @return
	 */
	private Object doQuery(VOPackage vo)throws BaseException {
		//���ǰ̨����
		ClfjyxxCXBO bo = (ClfjyxxCXBO)vo.getData();
		UserData ud = vo.getUserData();
		//��ѯ�����
		ArrayList resList = new ArrayList();
		
		
		System.out.println("bo��ѯ����--��ʼ����-->"+bo.getQuery_qsrq());
		System.out.println("bo��ѯ����--��ֹ����-->"+bo.getQuery_jzrq());
		System.out.println("bo��ѯ����--��ͬ���-->"+bo.getQuery_htbh());
		
		
		
		
		//ƴ�Ӳ�ѯ����
		String Query_qsrq = SecurityUtil.dealwithStringPara(bo.getQuery_qsrq());//��ѯ���� --��ʼ����
		String Query_jzrq =	SecurityUtil.dealwithStringPara(bo.getQuery_jzrq());	//��ѯ���� -- ��ֹ����
		String Query_htbh = SecurityUtil.dealwithStringPara(bo.getQuery_htbh());//��ѯ���� -- ��ͬ���
		String Query_sellerN = SecurityUtil.dealwithStringPara(bo.getQuery_sellerN());//��������
		String Query_buyerN = SecurityUtil.dealwithStringPara(bo.getQuery_buyerN());//������
		String Query_fwqszylx = SecurityUtil.dealwithStringPara(bo.getQuery_fwqszylx());//����Ȩ��ת������
		String Query_minJzmj = SecurityUtil.dealwithStringPara(bo.getQuery_minJzmj());//����Ȩ��ת������
		String Query_maxJzmj = SecurityUtil.dealwithStringPara(bo.getQuery_maxJzmj());//����Ȩ��ת������
		// ��������Ȩ�޿���
        String datafilter;
		try {
			datafilter = ZKAdapter.getInstance().getDataFilterString(ud,
					"QSDB", "QS_JL_MFSBXXZB_SELLER");
		} catch (Exception e) {
			throw new ApplicationException("��������Ȩ��ʧ�ܣ���ѯ��������ϵͳ����Ա��ϵ.");
		}
        
		//ƴ��SQL
		
		StringBuffer buff = new StringBuffer();
		buff.append(" where 1=1 and a.HTBH=b.HTBH and a.HTBH=c.HTBH");
		if(Query_qsrq != null && !"".equals(Query_qsrq)){
			buff.append(" and a.cjrq >= to_date('"+Query_qsrq+"','yyyymmdd') ");
		}

		if(Query_jzrq != null && !"".equals(Query_jzrq)){
			buff.append(" and a.cjrq < to_date('"+Query_jzrq+"','yyyymmdd')+1 ");
		}
		
		if(Query_htbh != null && !"".equals(Query_htbh)){
			buff.append(" and a.htbh='"+Query_htbh+"'");
		}
		if(Query_sellerN != null && !"".equals(Query_sellerN)){
			buff.append(" and a.nsrmc like '%"+Query_sellerN+"%'");
		}
		if(Query_buyerN != null && !"".equals(Query_buyerN)){
			buff.append(" and b.nsrmc like '%"+Query_buyerN+"%'");
		}
		if(Query_fwqszylx != null && !"".equals(Query_fwqszylx)){
			buff.append(" and c.fwqszylx like '%"+Query_fwqszylx+"%'");
		}
		if(Query_minJzmj != null && !"".equals(Query_minJzmj)){
			buff.append(" and c.fwjzmj >= "+Double.parseDouble(Query_minJzmj));
		}
		if(Query_maxJzmj != null && !"".equals(Query_maxJzmj)){
			buff.append(" and c.fwjzmj <= "+Double.parseDouble(Query_maxJzmj));
		}
		buff.append(" and "+datafilter);
		
		
		Connection conn = null;

		try {
			conn = QSDBUtil.getConnection();
			
	    	//�����ձ��в�ѯ������������������Ϣ
	    	List mfsbxxzbSellerList = new ArrayList();
	    	try{
	    	mfsbxxzbSellerList = DAOFactory.getInstance().getClfjyxxCXDAO().query(conn, buff.toString());
	    	}catch(Exception e){
	    		
	    		throw new ApplicationException("��ѯ������Ϣʱ�����������Ա��ϵ");
	    	}
	    	
	    	if(mfsbxxzbSellerList == null || mfsbxxzbSellerList.size() == 0){
	    		//��ѯ�޽��������
	    		throw new ApplicationException("û�����������Ľ��");
	    		
	    	}
	    	
	    	
	    	//�����ѯ�н������ʼѭ��szxxList���ÿ��sbbh�ͺ�ͬ���,���ÿ���걨��Ż��ߺ�ͬ����£���ά��ɼ���Ϣ�Լ�������Ϣ
	    	for(int index =0; index < mfsbxxzbSellerList.size();index ++){
	    		MfsbxxzbSeller mfsbxxzbSeller = (MfsbxxzbSeller) mfsbxxzbSellerList.get(index);
	    		//������ÿ��sbbh �� htbh
	    		String sbbh = mfsbxxzbSeller.getSbbh();
	    		String htbh = mfsbxxzbSeller.getHtbh();
	    		//System.out.println("++++++++++sbbh::"+sbbh  +"::htbh"+htbh);
	    		
	    		//��sbbh ��  htbh ��ô������ɼ���Ϣ
	    		VOPackage vo1 = vo;
	    		ClfxxcjBo data = new ClfxxcjBo();
	    		data.setSbbh(sbbh);
	    		data.setHtbh(htbh);
	    		vo1.setData(data);
	    		
	    		ClfxxcjBo resBo = null;
				try {
					ClfxxcjProcessor cjpro = new ClfxxcjProcessor();
                    resBo =  cjpro.getCjxx(vo1);
				} catch (Exception e) {
					throw new ApplicationException("��ѯ�������ɼ���Ϣ�����������Ա��ϵ");
				}
				
/*				if(resBo == null ){
					throw new ApplicationException("���������ɼ���Ϣ��Ϣ�������ѯ����.");
				}*/
					    		
	    		//���˰��������Ϣ
	    		BigDecimal ynsehj = new BigDecimal("0.00");
	    		BigDecimal ynsehj_qs = new BigDecimal("0.00");// add by tangchangfu 2014-06-16  Ϊ��˰������˰��ѯ�����Ϣ
	    		List resjyxxList = new ArrayList();
	    		List jyxxList = null;
	    		BigDecimal sjjehj = new BigDecimal("0.00");
				try {
					jyxxList = DAOFactory.getInstance().getClfjyxxCXDAO()
							.queryJyxxList(conn, sbbh,htbh);
				} catch (Exception e) {
					e.printStackTrace();
					throw new ApplicationException("��ѯ������ϸ��Ϣ�����������Ա��ϵ");
				}
	    		
	    		if(jyxxList == null || jyxxList.size() == 0){
	    			throw new ApplicationException("���걨��Ϣ�������ѯ����.");
	    		}
	    		
	    		
	    		
	    		for(int jyxxIndex =0; jyxxIndex < jyxxList.size(); jyxxIndex ++){
	    			
	    			ClfjyxxCX resjyxxVo = (ClfjyxxCX)jyxxList.get(jyxxIndex);
	    			
	    			ClfjyxxCXBO.clfjyxx oneItemJyxx = new ClfjyxxCXBO().new clfjyxx(resjyxxVo);
	    			resjyxxList.add(oneItemJyxx);
	    			
	    			//Ӧ��˰��ϼ�
	    			ynsehj = oneItemJyxx.getAll_ynse_hj();
	    			sjjehj=oneItemJyxx.getAll_sjje_hj();
	    			
	    			ynsehj_qs = oneItemJyxx.getQs_jsje_hj();// add by tangchangfu 2014-06-16  Ϊ��˰������˰��ѯ�����Ϣ
	    			//System.out.println("ѭ������+++++++++"+jyxxIndex);
	    		}
	    		
	    		
	    		
	    		
	    		//������Ϣ�����
	    		//System.out.println(sbbh+"������Ϣ����+++++++++"+resjyxxList.size());
	    		//System.out.println(sbbh+"htbh*************"+resBo.getHtbh());
	    		ClfjyxxCXBO.resInfo oneResItem = new ClfjyxxCXBO().new resInfo(resBo,this.getFwhdxx(sbbh),resjyxxList,ynsehj,ynsehj_qs,sjjehj);
	    		
	    		//�ѽ�����ŵ����ؽ����
	    		resList.add(oneResItem);
	    	}
	    	
	    	//System.out.println("���ؽ������Ϊ-----"+resList.size());
	    	
	    	
		}catch(Exception e){
			e.printStackTrace();
			throw ExceptionUtil.getBaseException(e);
		}finally{
			QSDBUtil.freeConnection(conn);
		}
		return resList;
	}
/**
 * ��÷��ݺ˶���Ϣ
 */

	/**
	 * ִ�в�ѯ����
	 * @param vo
	 * @return
	 * @throws BaseException
	 */
	public ClfswjghdxxlrBo getFwhdxx(String sbbh) throws BaseException {
		ClfswjghdxxlrBo resBo = null;
		Connection conn = null;
		
		
		try {
			//������ݿ�����
			conn = QSDBUtil.getConnection();
			if(conn == null){
				throw new ApplicationException("��ѯ��Ϣ�����޷�������ݿ����ӣ����˳����µ�½�����ߺ�ϵͳ����Ա��ϵ��");
			}			

			
			
			if (sbbh == null || "".equals(sbbh)) {
				throw new ApplicationException("��ѯ����ͬ�ɼ���Ϣ�������ͬ����Ƿ���ȷ��");
			}

			//��ѯ����
			String condition = "";
			if (sbbh != null && !"".equals(sbbh)) {
				condition = " where sbbh='" + sbbh + "'";
			}
			// ���˰����Ա�˶���Ϣ
			ArrayList hdxxList = this.getSwryhdxx(conn,condition);
			// �����Ϣֻ�д���һ�����򱨴�
			if (hdxxList != null && hdxxList.size() > 1) {
				throw new ApplicationException("��ѯ˰����Ա�˶���Ϣ����!��ͬһ�걨��Ŵ��ں˶����д��ڶ�����Ϣ���걨���Ϊ��"+ resBo.getSbbh());
			}
		
			//���ؽ��
			if (hdxxList != null && hdxxList.size() == 1) {
				Fwhdxx hdxxVo = (Fwhdxx) hdxxList.get(0);

				//@@1 ���ú˶���Ϣ
				if (hdxxVo != null) {
					resBo=this.getClfswjghdxxlrBoByHdxxVo(hdxxVo);
					resBo.setIsSaved("true");// �úϺ�ͬ��Ϣ�Ѿ������ں�ͬ���
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
	 * ͨ�� Fwhdxx ��� ClfswjghdxxlrBo
	 * 
	 * @param hdxxVo
	 * @param bo
	 * @return
	 */
	private ClfswjghdxxlrBo getClfswjghdxxlrBoByHdxxVo(Fwhdxx hdxxVo) {
		DecimalFormat deFormat_00 = new DecimalFormat("#0.00");// �ϼƽ���ʽ
		DecimalFormat deFormat_0000 = new DecimalFormat("#0.0000");// �ϼƽ���ʽ
		ClfswjghdxxlrBo bo=new ClfswjghdxxlrBo();
		bo.setSbbh(hdxxVo.getSbbh());
		bo.setHtbh(hdxxVo.getHtbh());
		bo.setSqrxzdz(hdxxVo.getSqrxzdz());
		bo.setJtwyshyhbz(hdxxVo.getJtwyshyhbz());
		bo.setFwlxdm(hdxxVo.getFwlxdm());
		bo.setJcnd(hdxxVo.getJcnd());
		// bo.setZlc(hdxxVo.getZlc());
		// fwjzmj
		//System.out.println("���ݽ������++++++++++" + hdxxVo.getFwjzmj());
		//System.out.println("�������++++++++++" + hdxxVo.getHlfy());
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
		bo.setFwcqzbzzflxdm(hdxxVo.getFwcqzbzzflxdm());
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
		bo.setYqspfwjsjg(deFormat_00.format(hdxxVo.getYqspfwjsjg()));
		bo.setLrrq(DateUtils.getDate(hdxxVo.getLrrq()));
		bo.setMpmhdjg(deFormat_00.format(hdxxVo.getMpmhdjg()));		
		bo.setFwszqydm(hdxxVo.getFwszqydm());
		return bo;
	}	
	
}

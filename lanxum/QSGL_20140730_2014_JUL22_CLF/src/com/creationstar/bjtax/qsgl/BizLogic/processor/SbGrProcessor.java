package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.Fwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxBuyer;
import com.creationstar.bjtax.qsgl.BizLogic.vo.clfgl.MfgrxxSeller;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbGrForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbGrBo;
import com.creationstar.bjtax.qsgl.model.bo.clfgl.ClfswjghdxxlrBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;

/**
 *
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
public class SbGrProcessor implements Processor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is SbgrProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doInsert(vo);

            break;
        case ActionType.QUERY:
            result = doQuery(vo);
            break;
        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ����һ�������걨��Ϣ
     * 1.�����걨�������ݣ����Ȼ�ȡ�µ��걨��ţ�
     * 2.���ɸ�����Ϣ
     * 3.�������ɵ��걨���
     * @param vo VOPackage
     * @return sbbh
     * @throws BaseException
     */
    private String doInsert(VOPackage vo) throws BaseException {
        SbGrBo bo = null;
        int oklevel = 0;
        String sbbh = null;
        Connection conn = null;

        try {
            bo = (SbGrBo) vo.getData();
            Debug.out("SbGrProcessor bo nsrmc: " + bo.nsrmc);
            conn = QSDBUtil.getConnection();

            UserData ud = vo.getUserData();

            //��ȡ�걨���
            sbbh = CommonUtil.getSBBH(ud.getXtsbm1(), conn);
            Debug.out("get sbbh: " + sbbh);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            //�����걨��������
            Sbzb sbzb = new Sbzb();
            if (bo.isBl() == true) {
                sbzb.blbs = Constants.ZB_BLBS_BL;
                sbzb.sbrq = bo.getSbrq();
            } else {
                sbzb.blbs = Constants.ZB_BLBS_FBL;
                sbzb.sbrq = now;
            }
            sbzb.bljmsbs = Constants.ZB_BLJMSBS_BFHBLTJ;
            sbzb.bz = bo.getBz();
            //���ӽ�ίҵ���� modify by fujx,20081125
            sbzb.setJwywbh(bo.getJwywbh());
            //���Ӻ�ͬ��� modify by fujx,20081125
            sbzb.setHtbh(bo.getHtbh());

            sbzb.cjr = ud.getYhmc();
            sbzb.cjrq = now;
            sbzb.fwtdbmdm = bo.getFcjslh();
            sbzb.jsfsdm = bo.getJkfsdm();
            sbzb.jsfsmc = bo.getJkfsmc();
            sbzb.jsje = new BigDecimal(0);
            sbzb.lrr = ud.getYhmc();
            sbzb.lrrq = now;
            sbzb.nsrlxdm = Constants.NSRLX_GR;
            sbzb.sbbh = sbbh;
            sbzb.sl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            sbzb.swjgzzjgdm = ud.getSsdwdm();
            sbzb.yhbs = Constants.YHBZ_GR; //����
            sbzb.ynse = new BigDecimal(0);
            sbzb.zsrymc = ud.getYhmc();
            sbzb.ztbs = Constants.ZB_ZTBS_BC;
            sbzb.pzzhdm = ud.getXtsbm1();
            sbzb.pzzhmc = ud.getXtsbm2();

            //insert sbzb
            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            oklevel = 1;
            //���������Ϣ
            List nsrList = bo.getNsrList();
            List l = new ArrayList();
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                grxx.cjr = ud.getYhmc();
                grxx.cjrq = now;
                grxx.fwjhbs = Constants.FFWJHBS;
                grxx.lrr = ud.getYhmc();
                grxx.lrrq = now;
                Debug.out("SbGrProcessor bo nsrmc22: " + grxx.nsrmc);
                grxx.sbbh = sbbh;
                l.add(grxx);
            }

            //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
            Debug.out("��ȡ��˰����Ϣ��ʼ");
            l = CommonUtil.handleZRR(l, ud);
            Debug.out("S��ȡ��˰����Ϣ����....");
            bo.setNsrList(l);
            oklevel = 2;
            //insert grxx
            DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            oklevel = 3;
            //���������������
            if (bo.getHdtzszh() != null && !bo.getHdtzszh().equals("")) {
                Spjgxx spjgxx = new Spjgxx();
                spjgxx.cjr = ud.getYhmc();
                spjgxx.cjrq = now;
                spjgxx.hdtzszh = bo.getHdtzszh();
                if ((bo.getJmlydm() == null) || (bo.getJmlydm().equals(""))) {
                    spjgxx.jmlydm = "00";
                } else {
                    spjgxx.jmlydm = bo.jmlydm;
                }

                spjgxx.jmsje = bo.jmsje;
                spjgxx.lrr = ud.getYhmc();
                spjgxx.lrrq = now;
                spjgxx.sbbh = sbbh;

                Debug.out("jmje: " + bo.getJmsje());
                Debug.out("hdtzszh: " + bo.getHdtzszh());

                DAOFactory.getInstance().getSpjgxxDAO().insert(spjgxx, conn);
            }
        } catch (Exception ex) {
            Debug.out("OKLevel: " + oklevel);
            ex.printStackTrace();
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("�����걨�������!");
                case 1:
                    throw new ApplicationException(
                            "��ȡ���˼���������������¼�����˰����������Ȼ�˵Ǽǵ���˰�����Ʋ�ƥ��!");
                case 2:
                    throw new ApplicationException("���������Ϣ����!");
                case 3:
                    if (ex.getMessage().indexOf("ORA-00001") != -1) {
                        throw new ApplicationException("������������Ѿ�����!");
                    } else {
                        throw new ApplicationException("����������������Ϣ����!");
                    }
                }

            } catch (ApplicationException e) {
                // ����ʧ����Ϣ:����̨ �� ��־
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbGRProcessor����������",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
                throw ExceptionUtil.getBaseException(e);
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return sbbh;
    }
    
    
    /**
     * ���ݺ�ͬ��Ų�ѯ����Ϣ
     * @param vo VOPackage
     * @return SbGrBo
     * @throws BaseException
     */
    private SbGrBo doQuery(VOPackage vo) throws BaseException {
    	SbGrBo bo = null;
    	bo = (SbGrBo) vo.getData();
        int oklevel = 0;
        String htbh=bo.getHtbh();
        Connection conn = null;
    	if (htbh == null || "".equals(htbh)) {
			throw new ApplicationException("��ȡ��Ӧ���ݲɼ���Ϣ�����޲�ѯ����!");
		}

		// ƴ�Ӳ�ѯSQL
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" where 1= 1");
		
		if (htbh != null && !"".equals(htbh)) {
			sqlBuff.append("and htbh ='" + htbh + "'");
		}
        try {
          
            Debug.out("SbGrProcessor bo htbh: " + bo.getHtbh());
            conn = QSDBUtil.getConnection();
            oklevel = 1;
            // ��÷�����Ϣ
            List fwxxList = DAOFactory.getInstance().getFwxxDAO().queryFwListForQs(conn, sqlBuff.toString());
         	if (fwxxList.size() <= 0) {
         		bo.setHtbh("");
         		bo.setTime("");
         		bo.setAddress("");
         		bo.setDivertType("");
         		bo.setArea("");
         		bo.setRmbPrice("");
         		bo.setAll_buyerInfo("");
         		throw new ApplicationException("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ!");
         	}
         			
         	for (int index = 0; index < fwxxList.size(); index++) {
         		Fwxx fwxx = (Fwxx) fwxxList.get(index);
         		
         		bo.setHtbh(fwxx.htbh);
         		bo.setTime(DataConvert.TimeStamp2String(fwxx.getHtwsqyrq()));
         		bo.setAddress(fwxx.fwzldz);
         		bo.setDivertType(fwxx.fwqszylx);
         		bo.setArea(fwxx.fwjzmj.toString());
         		bo.setRmbPrice(fwxx.htzj.toString());
         	}
            // �������Ϣ
         	List buyer_list = DAOFactory.getInstance().getMfgrxxBuyerDAO().queryMfgrxxBuyerListForQs(conn, sqlBuff.toString());
         	StringBuffer buyerBuf = new StringBuffer();
         
         	for (int index = 0; index < buyer_list.size(); index++) {
         		MfgrxxBuyer mfgrxxBuyerItem = new MfgrxxBuyer();
         		mfgrxxBuyerItem = (MfgrxxBuyer) buyer_list.get(index);
         		if (index > 0) {
         			buyerBuf.append("^");
         		}
         		buyerBuf.append(mfgrxxBuyerItem.getNsrmc());
         		buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLxdh());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjlxdm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getZjhm());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getQlrfe());
				buyerBuf.append("~");
				buyerBuf.append(mfgrxxBuyerItem.getLb());
				
         	}
         	System.out.println("����Ϣ��������" + buyerBuf.toString());
         	bo.setAll_buyerInfo(buyerBuf.toString());
        } catch (Exception ex) {
            Debug.out("OKLevel: " + oklevel);
            ex.printStackTrace();
            try {
                switch (oklevel) {
                case 0:
                    throw new ApplicationException("���ݺ�ͬ��Ų�ѯ����Ϣ����!");
                case 1:
                    throw new ApplicationException("�޲�ѯ������޶�Ӧ���ݲɼ���Ϣ!");
                }

            } catch (ApplicationException e) {
                // ����ʧ����Ϣ:����̨ �� ��־
                Debug.printException(e);
                ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�SbGRProcessor����������",
                                 new StackMsg2StringUtil(e,
                        Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");
                throw ExceptionUtil.getBaseException(e);
            }

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }
}

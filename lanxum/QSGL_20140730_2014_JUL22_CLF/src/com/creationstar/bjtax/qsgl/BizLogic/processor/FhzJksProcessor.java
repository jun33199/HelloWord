package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Swjgzzjg;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.FhzJksBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.JksUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;


/**
 *
 * <p>Title:��¼�ǻ������ɵĽɿ����processor </p>
 *
 * <p>Description: ��¼�ǻ������ɵĽɿ����processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class FhzJksProcessor implements Processor {

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage�ǿ�ָ��!!!");
        }

        switch (vo.getAction()) {
        case ActionType.GET:
            result = createJks(vo);
            break;

        case ActionType.INSERT:
            saveJks(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;

    }

    /**
     * �����걨����Զ������ɿ���������Ϣ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */

    private Object createJks(VOPackage vo) throws BaseException {
        FhzJksBo bo = (FhzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String sbbh = bo.getSbbh();
            Sbjkzb sbjkzb = new Sbjkzb();

            Timestamp nowTime = CommonUtil.getDBtime(conn);

            //��˰�˼��������
            String nsrjsjdm = "";
            //ͨ�������걨������ֵ�걨��ţ�����������걨��������
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);

            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //�������걨�����еĲ�¼��ʶ��blbs��Ϊ1����״̬��ʶΪ7�Ĳ��ܲ�¼�ǻ��ܽɿ���
            if (sbzb == null) {
                throw new ApplicationException("δ�ҵ���ƥ����걨��¼�����Ȳ�¼�걨��Ϣ���ٲ�¼�ɿ��飡");
            } else if (Constants.ZB_BLBS_FBL.equals(sbzb.getBlbs())) {
                throw new ApplicationException("�ñ��걨���ǲ�¼�ģ����ܲ�¼�ɿ��飡");
            } else if (Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs())) {
                throw new ApplicationException("�ñ��걨����⣬�����ٴβ�¼�ɿ��飡");
            } else if (!Constants.ZB_ZTBS_YFH.equals(sbzb.getZtbs())) {
                throw new ApplicationException("������ɸ��ϼ��տ�������ٲ�¼�ɿ��飡");
            }

            //�õ����ط����걨�Ļ�����Ϣ
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            Debug.out("��¼�ǻ������ɽɿ��飺�Ѿ�ȡ���˷��ݻ�����Ϣ����������....");

            //��λSwjgzzjg����
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);

            //������걨��Ϊ�����걨
            Debug.out("��¼�ǻ������ɽɿ��飺�жϸ��˻��ǷǸ���....");
            if (Constants.YHBZ_GR.equals(sbzb.getYhbs())) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbbh, conn);
                nsrjsjdm = grxx.getJsjdm();
                //�õ���˰�˵ĵǼǻ�������
                sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //�Ǽ�ע������
                sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //�Ǽ�ע����������
                sbjkzb.setJydzlxdm(grxx.getLxdh()); //��ϵ�绰
                sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //���ұ�׼��ҵ����
                sbjkzb.setNsrmc(grxx.getNsrmc()); //��˰������
                sbjkzb.setJsjdm(nsrjsjdm);
                sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //�õ��ɿ�ƾ֤��
                Debug.out("��¼�ǻ������ɽɿ��飺���˷�ʽ�������Ѿ������˷�ʽ������ֵ����Sbjkzb��....");
            }
            //���Ϊ�Ǹ����걨
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(
                                      sbbh, conn);
                sbjkzb.setYhdm(fgrxx.getKhyhdm()); //���д���
                sbjkzb.setYhmc(fgrxx.getKhyhmc()); //��������
                sbjkzb.setZh(fgrxx.getYhzh()); //�����ʺ�
                sbjkzb.setJydzlxdm(fgrxx.getLxdh()); //��ϵ�绰
                nsrjsjdm = fgrxx.getJsjdm();
                //�õ���˰�˵ĵǼǻ�������
                SWDJJBSJ jbsj = null;
                try {
                    jbsj = CommonUtil.getFgrJBSJ(nsrjsjdm);
                } catch (Exception ex1) {
                    Debug.out(ex1);
                    throw ExceptionUtil.getBaseException(ex1, "��ȡ���˵ĵǼ���Ϣ����");
                }
                if (jbsj == null) {
                    throw new ApplicationException("��ȡ���˵Ǽ���Ϣ����");
                }
                sbjkzb.setSwjgzzjgdm(jbsj.getSwjgzzjgdm()); //˰�������֯��������
                sbjkzb.setSwjgzzjgmc(jbsj.getSwjgzzjgmc()); //˰�������֯��������
                sbjkzb.setLsgxdm(jbsj.getLsgxdm()); //������ϵ
                sbjkzb.setLsgxmc(jbsj.getLsgxmc()); //������ϵ����
                sbjkzb.setDjzclxdm(jbsj.getSwdjlx()); //�Ǽ�ע������
                sbjkzb.setDjzclxmc(jbsj.getDjzclxmc()); //�Ǽ�ע����������
                sbjkzb.setJydzlxdm(jbsj.getJydzlxdm()); //��Ӫ��ַ��ϵ�绰
                sbjkzb.setGjbzhydm(jbsj.getGjbzhydm()); //���ұ�׼��ҵ����
                sbjkzb.setGjbzhymc(jbsj.getGjbzhymc()); //���ұ�׼��ҵ����
                sbjkzb.setNsrmc(jbsj.getNsrmc()); //��˰������
                //�ж��Ƿ�Ǳ��ֵ���ҵ�û�
                String nsrSsQxdm = jbsj.getSwjgzzjgdm().substring(0, 2);
                Debug.out("�ж��Ƿ�Ǳ��ֵ���ҵ�û� is " + nsrSsQxdm);
                if (swjgzzjg.qxfjdm != null &&
                    swjgzzjg.qxfjdm.substring(0, 2).equals(nsrSsQxdm)) {
                    //���ڷǸ�����˵�����ֵ���Ͻ��ҵ�Ľɿ���ļ�������������˰�˵ļ��������
                    sbjkzb.setJsjdm(nsrjsjdm);
                    sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //�õ��ɿ�ƾ֤��
                } else { //�Ǳ�����Ͻ��ҵ�ģ��ɿ���ļ����������ǵ�ǰ˰�����ļ��������
                    sbjkzb.setJsjdm(swjgzzjg.jsjdm);
                    sbjkzb.setJkpzh(JksUtil.getJkpzh(swjgzzjg.jsjdm)); //�õ��ɿ�ƾ֤��
                }
                Debug.out("����˰�������֯�������� in FhzJksProcessor is " +
                          sbjkzb.getZsswjgzzjgmc());
                Debug.out("��¼�ǻ������ɽɿ��飺�Ǹ��˷�ʽ�������Ѿ����Ǹ��˵�����ֵ����Sbjkzb��....");
            }

            sbjkzb.setGkzzjgdm(swjgzzjg.gkzzjgdm); //������֯��������
            sbjkzb.setGkzzjgmc(swjgzzjg.skgk); //������֯��������
            sbjkzb.setZsswjgzzjgdm(ud.ssdwdm); //����˰�������֯��������
            sbjkzb.setZsswjgzzjgmc(ud.ssdwmc); //����˰�������֯��������
            sbjkzb.setSwjgzzjgdm(ud.ssdwdm); //˰�������֯��������
            sbjkzb.setSwjgzzjgmc(ud.ssdwmc); //˰�������֯��������
            sbjkzb.setSklxdm(Constants.JKS_SKLXDM_ZCJK); //˰������
            sbjkzb.setSklxmc(Constants.JKS_SKLXDM_ZCJK_MC); //˰����������
            sbjkzb.setFsdm(Constants.WSZ_FSDM); //�걨��ʽ���롪����Ϊ˰��������ŷ�ʽ
            sbjkzb.setSbbh(sbbh); //�걨����
            sbjkzb.setYsjcdm(Constants.YSJCDM_DF); //Ԥ�㼶��
            sbjkzb.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            sbjkzb.setSjje(hdbo.getYnse()); //ʵ�ɽ��
            sbjkzb.setRkje(hdbo.getYnse()); //�����
            //�������˰�ܽ��걨�ɿ�ı�ע�ֶ�
            BigDecimal jmszje = hdbo.getJmzje();
            sbjkzb.setBz("����˰�ܽ�" + DataConvert.BigDecimal2String(jmszje, 2) +
                         "  ��˰��ʽ��" + sbzb.getJsfsmc());
            sbjkzb.setZsswjgzzjgdm(ud.ssdwdm); //���ջ��ش���
            sbjkzb.setSzdm(Constants.WSZ_QSSZDM); //˰�ִ���
            sbjkzb.setSzmc(Constants.WSZ_QSSZMC); //˰������
            Yskm yskm = CommonUtil.getYskm(sbjkzb.getSzdm(), conn);
            sbjkzb.setYskmdm(yskm.yskmdm); //Ԥ���Ŀ
            sbjkzb.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ����
            sbjkzb.setClbjdm(Constants.WSZ_CLBJDM_YSB); //�����Ǵ���
            sbjkzb.setLrr(ud.getYhid()); //¼���˴���
            sbjkzb.setSbrq(nowTime); //�걨����
            sbjkzb.setCjrq(nowTime); //��������
            sbjkzb.setLrrq(nowTime); //¼������
            sbjkzb.setQxdm(CommonUtil.getQxdm(ud)); //���ش���
            sbjkzb.setSkssksrq(tfxx.getHtqdsj()); //˰��������ʼ����
            sbjkzb.setSkssjsrq(tfxx.getHtqdsj()); //˰��������������
            sbjkzb.setFdcwz(tfxx.getTdfwzldz()); //���ز�λ��
            sbjkzb.setNd(DateUtil.getDate().substring(0, 4)); //���
            sbjkzb.setSjly(Constants.JKS_SJLY_FHZ); //������Դ�����ǻ��ܷ�ʽ
            sbjkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //�����ʶ
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //��˰�޽�����
            //sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //�걨����
            sbjkzb.setZyrq(nowTime); //��������

            //��Ϊ���������Ե�����֧Ʊ��˰����˰�ˣ��������ǵ�˰Ŀֻ����һ��
            //��˲��ط�Ʊ
            //�����걨�ɿ���ϸ����
            Sbjkmx sbjkMx = new Sbjkmx();
            Szsm szsm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn);
            sbjkMx.setSzsmdm(szsm.szsmdm); //˰��˰Ŀ����
            Debug.out("�ɿ�����ϸ�е�˰��˰Ŀ���ƣ�" + szsm.szsmmc);
            sbjkMx.setSzsmmc(szsm.szsmmc); //˰��˰Ŀ����
            sbjkMx.setJkpzh(sbjkzb.getJkpzh()); //�ɿ�ƾ֤��
            sbjkMx.setJsjdm(sbjkzb.getJsjdm()); //���������
            sbjkMx.setYsjcdm(Constants.YSJCDM_DF); //Ԥ�㼶�δ���
            sbjkMx.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
            sbjkMx.setYskmdm(yskm.yskmdm); //Ԥ���Ŀ
            sbjkMx.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ
            sbjkMx.setKssl(new BigDecimal(1)); //��˰����

            sbjkMx.setJsje(hdbo.getJsyj()); //��˰���
            sbjkMx.setSjse(hdbo.getYnse()); //ʵ��˰��
            sbjkMx.setSkssksrq(nowTime); //˰��������ʼ����
            sbjkMx.setSkssjsrq(nowTime); //˰��������������
            sbjkMx.setRkje(hdbo.getYnse()); //�����
            sbjkMx.setSbbh(sbjkzb.getSbbh()); //�걨���
            /** @todo �ɿ���ֳɵ� */
            //�м��ֳ�
            //�����ֳ�
            sbjkMx.setSwjgzzjgdm(ud.ssdwdm); //˰�������֯��������
            sbjkMx.setNd(sbjkzb.getNd()); //���
            sbjkMx.setSl(sbzb.getSl()); //˰��
            sbjkMx.setCjrq(nowTime); //����ʱ��
            sbjkMx.setLrrq(nowTime); //¼��ʱ��
            sbjkMx.setQxdm(sbjkzb.getQxdm()); //���ش���

            bo.setSbjkzb(sbjkzb);
            bo.setSbjkmx(sbjkMx);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FhzJksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void saveJks(VOPackage vo) throws BaseException {
        FhzJksBo bo = (FhzJksBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            String sbbh = bo.getSbbh();

            Sbjkzb sbjkzb = bo.getSbjkzb();
            Sbjkmx sbjkmx = bo.getSbjkmx();

            //��Ϊ�������ǰ̨�����޸ģ�������Ҫ���¼���һ����˰�޽�����
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //��˰�޽�����

            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("��¼�ǻ������ɽɿ��飺�Ѿ���sbjkzb�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("�����걨�ɿ��������");
            }

            try {
                DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkmx, conn);
                Debug.out("��¼�ǻ������ɽɿ��飺�Ѿ���sbjkMx�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("�����걨�ɿ���ϸ����");
            }

            //�޸��걨�����״̬��ʶ
            //���Ϊ�����
            try {
                DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("�����걨����״̬����");
            }

            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);
            JghdsjBo hdbo = CommonUtil.getJZSE(sbbh, conn);
            //�������˰�ܽ��걨�ɿ�ı�ע�ֶ�
            BigDecimal jmszje = hdbo.getJmzje();

            //���ϼ�����������ģ������Ѿ������˵��걨����Ҫ���������걨��Ϣ
            //�Ա��������ͳ�ƺͼ���
            Debug.out("�걨�����еİ������˰��ʶ��" + sbzb.bljmsbs);
            Debug.out("�걨�����е�״̬��" + sbzb.ztbs);
//             if (Constants.ZB_BLJMSBS_FHBL_YLR.equals(sbzb.bljmsbs) &&
//                 Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs))
            
            //���Ӽ��⴦��
            String jmxzdm = Constants.JSJM_JMXZDM_SLJM;
            if(Constants.SETZ_QEZS.equals(sbzb.getSetz())){
            	 jmxzdm = Constants.JSJM_JMXZDM_CQJM;
            }
            
            if (jmszje == null || jmszje.doubleValue() == 0) {
            	BigDecimal jbsl = new BigDecimal(CommonUtil.getZcsj(Constants.ZCWH_SL, conn));
            	
            	BigDecimal jmsl = jbsl.subtract(hdbo.getSl());
            	if(jmsl.doubleValue()>0.001){
            		BigDecimal jmje = jmsl.multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	}else if(Constants.SETZ_JBZS.equals(sbzb.getSetz())) {
            		BigDecimal jmje = jbsl.multiply(new BigDecimal(0.5)).multiply(hdbo.getJzse());
            		jmszje = jmje.setScale(2,BigDecimal.ROUND_HALF_UP);
            	} 	
            } 
            
            if (jmszje != null && jmszje.doubleValue() > 0) {
                HashMap map = new HashMap();
                map.put("jsjdm", swjgzzjg.jsjdm);
                map.put("szsmdm", sbjkmx.szsmdm);
                map.put("jsje", hdbo.getJzse());
                map.put("jmse", jmszje);
                map.put("lrr", sbjkzb.lrr);
                map.put("jmxmdm", Constants.JMXMDM); //������Ŀ����
                map.put("jmxzdm", jmxzdm); //������Ŀ����                
                map.put("cjrq", sbjkzb.cjrq);
                map.put("skssjsrq", sbjkzb.skssjsrq);
                map.put("skssksrq", sbjkzb.skssksrq);
                if (!CommonUtil.insertSBJM(map)) {
                    throw new ApplicationException("����˰�ѵļ����걨��Ϣ��ʱ�򱨴�");
                }
                Debug.out("��������걨�ɹ���");
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�FhzJksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }

}

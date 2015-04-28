package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

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
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.util.ActionUtil;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.Helper;
import com.creationstar.bjtax.qsgl.util.JksUtil;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.util.DateUtil;
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
 * @author �Բ�
 * @version 1.0
 */
public class JksProcessor extends CommonProcessor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is JksProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        //ͨ���ɿ���Ľ�˰��ʽ���ɵĽɿ���
        case ActionType.CREATE_JKS:
            result = doCBPJks(vo);
            break;

        case ActionType.CX_JKS:
            doCxJks(vo);
            break;

        case ActionType.GET:
            result = doGet(vo);
            break;

        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.JKS_QUERY_WSZ:
            result = doQueryWsz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * ����ǰ̨�������걨��Ŵ����ɿ�������
     * ������ɿ�������
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��Form����
     * @throws BaseException
     */
    private Sbjkzb doCBPJks(VOPackage vo) throws BaseException {
        //��ȡǰ̨�������걨���
        JksBo bo = (JksBo) vo.getData();
        String sbbh = bo.getSbbh();
        //���UserData
        UserData ud = vo.getUserData();
        //����ɿ�����������
        Sbjkzb sbjkzb = new Sbjkzb();
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Timestamp nowTime = CommonUtil.getDBtime(conn);
            //˰����صļ��������
            //�������������Ϊ˰��־���Ͻ����ҵͨ������ת�ʵķ�ʽ��˰��
            //�����ɽɿ���ġ�����������Ӧ���Ǹ���Ͻ��ҵ�ļ��������
            //���������Ͻ����ҵ����Ӧ��ͨ��������Ա�ֹ��ķ�ʽ��������ɢ���յķ�ʽ¼�����ɽɿ���
            //�Ͳ�Ӧ������������ˣ�
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //��˰�˼��������
            String nsrjsjdm = "";
            //ͨ�������걨������ֵ�걨��ţ�����������걨��������
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
            Debug.out("ֱ�����ɽɿ��鷽ʽ���Ѿ�ȡ�����걨�������������.... sbbh " + sbzb.sbbh);

            //һ��Ҫ�ų��Ѿ����ɹ��ɿ����
            if (Constants.ZB_ZTBS_YRK.equals(sbzb.ztbs)) {
                throw new ApplicationException("�ñ��걨�Ѿ���ӡ�ɿ����ˣ����ܹ��ٴ����ɽɿ��飡");
            }

            //�õ����ط����걨�Ļ�����Ϣ
            Tufwxx tfxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                          getBySbbh(sbbh, conn);
            Debug.out("ֱ�����ɽɿ��鷽ʽ���Ѿ�ȡ���˷��ݻ�����Ϣ����������....");

            //��λSwjgzzjg����
            Swjgzzjg swjgzzjg = CommonUtil.getSwjgzzjg(ud, conn);

            //������걨��Ϊ�����걨
            Debug.out("ֱ�����ɽɿ��鷽ʽ���жϸ��˻��ǷǸ���....");
            if (Constants.YHBZ_GR.equals(sbzb.getYhbs())) {
                Grxx grxx = new Grxx();
                if (bo.getJsjdm() == null || bo.getJsjdm().equals("")) {
                    List l = (List) DAOFactory.getInstance().getGrxxDAO().
                             getAllBySbbh(sbzb.sbbh, conn);
                    grxx = Helper.getZcqr(l);
                    bo.setNsrmc(ActionUtil.getNsrmc(l, null));
                } else {
                    grxx.setJsjdm(bo.getJsjdm());
                    grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                           get(grxx, conn);
                }

                nsrjsjdm = grxx.getJsjdm();
                //�õ���˰�˵ĵǼǻ�������
                sbjkzb.setDjzclxdm(Constants.WSZ_DJZCLX); //�Ǽ�ע������
                sbjkzb.setDjzclxmc(Constants.WSZ_DJZCLX_MC); //�Ǽ�ע����������
                sbjkzb.setJydzlxdm(grxx.getLxdh()); //��ϵ�绰
                sbjkzb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //���ұ�׼��ҵ����
                sbjkzb.setNsrmc(bo.getNsrmc()); //��˰������
                sbjkzb.setJsjdm(nsrjsjdm);
                sbjkzb.setJkpzh(JksUtil.getJkpzh(nsrjsjdm)); //�õ��ɿ�ƾ֤��
                Debug.out("ֱ�����ɽɿ��鷽ʽ�����˷�ʽ�������Ѿ������˷�ʽ������ֵ����Sbjkzb��....");
            }
            //���Ϊ�Ǹ����걨
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbbh, conn);
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
                sbjkzb.setDjzclxdm(jbsj.getDjzclxdm()); //�Ǽ�ע������
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
                Debug.out("����˰�������֯�������� in JksProcessor is " +
                          sbjkzb.getZsswjgzzjgmc());
                Debug.out("ֱ�����ɽɿ��鷽ʽ���Ǹ��˷�ʽ�������Ѿ����Ǹ��˵�����ֵ����Sbjkzb��....");
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
            sbjkzb.setSbrq(sbzb.getSbrq()); //�걨����
            sbjkzb.setCjrq(nowTime); //��������
            sbjkzb.setLrrq(nowTime); //¼������
            sbjkzb.setZyrq(nowTime); //��������
            sbjkzb.setQxdm(CommonUtil.getQxdm(ud)); //���ش���
            sbjkzb.setSkssksrq(tfxx.getHtqdsj()); //˰��������ʼ����
            sbjkzb.setSkssjsrq(tfxx.getHtqdsj()); //˰��������������
            sbjkzb.setFdcwz(tfxx.getTdfwzldz()); //���ز�λ��
            sbjkzb.setNd(DateUtil.getDate().substring(0, 4)); //���
            sbjkzb.setSjly(Constants.JKS_SJLY_FHZ); //������Դ�����ǻ��ܷ�ʽ
            sbjkzb.setZwbs(Constants.JKS_ZWBS_DEFAULT); //�����ʶ
            sbjkzb.setXjrq(CommonUtil.getXjrq(sbjkzb.getCjrq(), 30)); //��˰�޽�����
//            sbjkzb.setSbbh(CommonUtil.getJksSbbh(sbjkzb.getJsjdm())); //�걨����


            try {
                DAOFactory.getInstance().getSbjkzbDAO().insert(sbjkzb, conn);
                Debug.out("ֱ�����ɽɿ��鷽ʽ���Ѿ���sbjkzb�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("�����걨�ɿ��������");
            }

            //�����ݽ��з�Ʊ����
            //��Ϊ���������Ե�����֧Ʊ��˰����˰�ˣ��������ǵ�˰Ŀֻ����һ��
            //��˲��ط�Ʊ
            //�����걨�ɿ���ϸ����
            Sbjkmx sbjkMx = new Sbjkmx();
            Szsm szsm = CommonUtil.getSZSMDM(tfxx.getTdfwqszylx(), conn);
            sbjkMx.setSzsmdm(szsm.szsmdm); //˰��˰Ŀ����
            Debug.out("�ɿ�����ϸ�е�˰��˰Ŀ���ƣ�" + szsm.szsmmc);
            sbjkMx.setSzsmmc(szsm.szsmmc); //˰��˰Ŀ����
            sbjkMx.setJkpzh(sbjkzb.getJkpzh()); //�ɿ�ƾ֤��
            Debug.out("�ɿ�ƾ֤�� : " + sbjkMx.getJkpzh());
            sbjkMx.setJsjdm(sbjkzb.getJsjdm()); //���������
            sbjkMx.setYsjcdm(Constants.YSJCDM_DF); //Ԥ�㼶�δ���
            sbjkMx.setYsjcmc(Constants.YSJCDM_DF_MC); //Ԥ�㼶������
            sbjkMx.setYskmdm(yskm.yskmdm); //Ԥ���Ŀ
            sbjkMx.setYskmmc(yskm.yskmmc); //Ԥ���Ŀ
            sbjkMx.setKssl(new BigDecimal(1)); //��˰����
            sbjkMx.setYskmfcbl(com.ttsoft.bjtax.shenbao.proxy.ServiceProxy.getYskmFcblmc(yskm.yskmdm, szsm.szsmdm, ud.ssdwdm));//�ֳɱ���
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

            try {
                DAOFactory.getInstance().getSbjkmxDAO().insert(sbjkMx, conn);
                Debug.out("ֱ�����ɽɿ��鷽ʽ���Ѿ���sbjkMx�����ݲ��뵽���ݿ���....");
            } catch (Exception ex) {
                Debug.out(ex);
                throw new ApplicationException("�����걨�ɿ���ϸ����");
            }

            //����ϸ���ݷ��뵽����VO��
            sbjkzb.getMxList().add(sbjkMx);
            //�޸��걨�����״̬��ʶ
            //���Ϊ�����
            sbzb.setZtbs(Constants.ZB_ZTBS_YRK);
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);

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
                map.put("szsmdm", sbjkMx.szsmdm);
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
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        Debug.out("ֱ�����ɽɿ��鷽ʽ���ɿ������ɲ������ݲ��뵽���ݿ���....�������ɹ���");
        Debug.out("����˰�������֯�������� in JksProcessor is " + sbjkzb.getZsswjgzzjgmc());

        return sbjkzb;
    }

    /**
     * ������˰֤
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doCxJks(VOPackage vo) throws BaseException {
        /**
         * ǰ̨������������
         */
        JksBo bo = (JksBo) vo.getData();
        Sbjkzb jkzbVo = new Sbjkzb();
        String jkpzh = bo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);
        Debug.out("ȷ����jsjdmΪ " + jsjdm);
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            //��ѯ�ɿ�������
            jkzbVo.setJkpzh(jkpzh);
            jkzbVo.setJsjdm(jsjdm);
            bo.setJsjdm(jsjdm);
            jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    jkzbVo, conn);

            //��ýɿ���ϸ����
            Sbjkmx mxVo = new Sbjkmx();
            mxVo.setJsjdm(jsjdm);
            mxVo.setJkpzh(jkpzh);
            mxVo = (Sbjkmx) DAOFactory.getInstance().getSbjkmxDAO().get(mxVo,
                    conn);

            if (jkzbVo == null) {
                throw new Exception("�Բ���û���ҵ���ýɿ����ƥ��ļ�¼������");
            }

            //����ýɿ��������
//          if(!Constants.JKS_ZWBS_DEFAULT.equals(jkzbVo.getZwbs()) )
//          {
//              throw new Exception("�ýɿ�������⣬���ܳ���������");
//          }
            String zwbs = jkzbVo.getZwbs();
            if (zwbs == null) {
                zwbs = Constants.JKS_ZWBS_DEFAULT;
            }

            // ���zwbs��һλ��ڶ�λ��Ϊ0���ܳ���  modify by wuyz
            if (!Constants.JKS_ZWBS_DEFAULT.substring(0, 1).equals(
                    zwbs.substring(0, 1))
                || !Constants.JKS_ZWBS_DEFAULT.substring(19).equals(
                        zwbs.substring(19))) {
                throw new Exception("�ýɿ�������⣬���ܳ���������");
            }

            StringBuffer condition = new StringBuffer("");
            condition.append("jkpzh = '").append(bo.getJkpzh())
                    .append("' AND jsjdm = '").append(bo.getJsjdm()).append(
                    "' ");

            //ɾ���ɿ��������ɿ�����ϸ�����˰֤���ܱ����ؼ�¼
            DAOFactory.getInstance().getSbjkmxDAO().delete(condition, conn);
            DAOFactory.getInstance().getSbjkzbDAO().delete(condition, conn);
            //�޸��걨�����״̬��ʶ
            //�ָ����Ϊ�Ѹ���
            DAOFactory.getInstance().getSbzbDAO().update(Constants.ZB_ZTBS_YFH,
                    jkzbVo.getSbbh(), conn);

            //ɾ�������걨�������
            HashMap map = new HashMap();
            map.put("jsjdm", jkzbVo.jsjdm);
            map.put("szsmdm", mxVo.getSzsmdm());
            map.put("cjrq", jkzbVo.cjrq);

            //ɾ�������걨�������
            if (!CommonUtil.deleteSBJM(map)) {
                throw new ApplicationException("�޷�ɾ�������걨������ݣ����ýӿڳ���");
            }
            Debug.out("���������걨�ɹ���");
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��ѯ�ɿ���ķ������������ܷ�ʽ�ͷǻ��ܷ�ʽ
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGet(VOPackage vo) throws BaseException {
        JksBo bo = (JksBo) vo.getData();
        UserData ud = vo.getUserData();
        String jkpzh = bo.getJkpzh();
        String jsjdm = jkpzh.substring(0, 8);

        StringBuffer condition = new StringBuffer("");
        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();
            Debug.out("��ѯ��Ҫ�����Ľɿ���ļ�������룺" + jsjdm);

            if (jkpzh != null && !jkpzh.equals("")) {
                condition.append(" AND a.jkpzh = '").append(jkpzh).append("' ");
            } else {
                throw new ApplicationException("�ɿ�ƾ֤�Ų���Ϊ�գ�");
            }

            if (jsjdm != null && !jsjdm.equals("")) {
                condition.append(" AND a.jsjdm = '").append(jsjdm).append("' ");
            } else {
                throw new ApplicationException("��ѯ�ļ�������벻��Ϊ�գ�");
            }

            //��������Ȩ�޿���
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_SBJKZB");
            Debug.out("datafilter: " + datafilter);
            condition.append(" AND ").append(datafilter);

            //���ܷ�ʽ���ǻ��ܷ�ʽ��������Դ���ֶ�
            condition.append(" AND a.sjly = '").append(bo.getHzfs()).append(
                    "' ");
            Sbjkzb jkzbVo = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().
                            get(condition, conn);

            return jkzbVo;
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * �ɿ����ӡ�ɹ���update�ɿ����״̬
     * @param vo VOPackage
     * @throws BaseException
     */
    public void doUpdate(VOPackage vo) throws BaseException {
        Sbjkzb jkzb = new Sbjkzb();

        jkzb = (Sbjkzb) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer(" WHERE 1 = 1 ");
            if (jkzb.getJkpzh() != null && !jkzb.getJkpzh().equals("")) {
                condition.append("AND jkpzh = '").append(jkzb.getJkpzh()).
                        append("' ");
            }
            if (jkzb.getJsjdm() != null && !jkzb.getJsjdm().equals("")) {
                condition.append("AND jsjdm = '").append(jkzb.getJsjdm()).
                        append("' ");
            }

            DAOFactory.getInstance().getSbjkzbDAO().update(condition, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ���ݽɿ��������,��ѯ�ýɿ����б����ܵ���˰֤�����
     * ����һ��ArrayList
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object doQueryWsz(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO��ѯ���صĽ����

        JksBo bo = (JksBo) vo.getData();
        Sbjkzb sbjkzb = bo.getSbjkzb();

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            resultList = DAOFactory.getInstance().getQswszzDAO().query(sbjkzb,
                    conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * ��ѯ�ɿ���
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    public Object doQuery(VOPackage vo) throws BaseException {
        ArrayList resultList = new ArrayList(); //DAO��ѯ���صĽ����

        JksBo bo = (JksBo) vo.getData();
        String jkpzh = bo.getJkpzh();
        String jsjdm = "";

        Connection conn = null;
        UserData ud = vo.getUserData();

        try {
            conn = QSDBUtil.getConnection();

            StringBuffer condition = new StringBuffer("");

            condition.append(" AND a.szdm = '").append(Constants.WSZ_QSSZDM).
                    append("' ")
                    .append(" AND a.qxdm = '")
                    .append(CommonUtil.getQxdm(ud)).append("' ")
                    .append(" AND a.SWJGZZJGDM = '")
                    .append(ud.ssdwdm).append("' ");

            if (bo.getJkpzh() != null && !bo.getJkpzh().equals("")) {
                condition.append(" AND a.jkpzh = '").append(bo.getJkpzh()).
                        append("' ");
            }
            if (bo.getSbqsrq() != null && !bo.getSbqsrq().equals("")) {
                condition.append(" AND a.sbrq >= TO_DATE('").append(bo.
                        getSbqsrq()).
                        append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
            }
            if (bo.getSbjzrq() != null && !bo.getSbjzrq().equals("")) {
                condition.append(" AND a.sbrq <= TO_DATE('").append(bo.
                        getSbjzrq()).
                        append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
            }

            condition.append(" AND a.sjly='").append(bo.hzfs).append("'"); //������Դ
            //��������Ȩ�޿���
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "SBDB", "SB_JL_SBJKZB");
            Debug.out("datafilter: " + datafilter);
            condition.append(" and ").append(datafilter);

            //����ǻ��ܷ�ʽ���ɵĽɿ���
            if (Constants.JKS_SJLY_HZ.equals(bo.hzfs) ||
                Constants.JKS_SJLY_XH.equals(bo.hzfs)) {
                jsjdm = CommonUtil.getWszJsjdm(ud, conn);
                condition.append(" AND a.jsjdm='").append(jsjdm).append("'");
                resultList = DAOFactory.getInstance().getSbjkzbDAO().queryAll(
                        condition, conn);
            }
            //�����ֱ�����ɵĽɿ���
            else {
                //����û�¼���˽ɿ������Ϊ��ѯ��������ôȡǰ8λ��Ϊ���������
                if (jkpzh != null && !jkpzh.equals("")) {
                    jsjdm = jkpzh.substring(0, 8);
                    condition.append(" AND (a.jsjdm = t1.jsjdm or a.jsjdm = '").
                            append(jsjdm).append("')");
                } else {
                    jsjdm = CommonUtil.getWszJsjdm(ud, conn);
                    condition.append(" AND (a.jsjdm = t1.jsjdm or a.jsjdm = '").
                            append(jsjdm).append("')");
                }

                //��ѯ�걨����������Ϣ���Ǹ�����Ϣ�õ�jsjdm��where����
                StringBuffer sbzb_condition = new StringBuffer("");
                sbzb_condition.append(" AND t.SWJGZZJGDM = '").append(ud.ssdwdm)
                        .append("' ").append("");
                if (bo.getSbqsrq() != null && !bo.getSbqsrq().equals("")) {
                    sbzb_condition.append(" AND t.sbrq >= TO_DATE('").append(bo.
                            getSbqsrq()).
                            append(" 00:00:00', 'YYYYMMDD HH24:MI:SS' )");
                }
                if (bo.getSbjzrq() != null && !bo.getSbjzrq().equals("")) {
                    sbzb_condition.append(" AND t.sbrq <= TO_DATE('").append(bo.
                            getSbjzrq()).
                            append(" 23:59:59', 'YYYYMMDD HH24:MI:SS' )");
                }

                resultList = DAOFactory.getInstance().getSbjkzbDAO().queryAll(
                        condition, sbzb_condition, conn);
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�JksProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        //�����걨�ɿ�������걨�ɿ���ϸ���е�������1�Զ�������Ϊ��ʹ�÷��㣬��Ҫ������֯
        //��ѯ�����1���������ݶ�Ӧ�����ӱ����ݵ��������bo���������ݷ���һ������vo�У���Ӧ���ӱ����ݷ���ArrayList��
        //���´�����������֯��ѯ�����ݣ�����֯�õ�bo�ŵ�ArrayList���淵��

        ArrayList returnList = new ArrayList(); //������֯���ݺ󣬷��ظ�ǰ̨�Ľ����
        ArrayList tempList = new ArrayList(); //��ʱ��Ų�ѯ���bo�е���ϸvo
        JksBo resultBo = new JksBo(); //���շ��ظ�ǰ̨�Ĳ�ѯ������е�ÿһ����¼
        for (Iterator iter = resultList.iterator(); iter.hasNext(); ) {
            bo = new JksBo();
            bo = (JksBo) iter.next();

            if (tempList.isEmpty()) { //������ظ�ǰ̨�Ĳ�ѯ���Ϊ�գ�˵���ǵ�һ������
                resultBo.setSbjkzb(bo.getSbjkzb());
                //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().intValue());
                tempList.add(bo.getSbjkmx()); //�ڲ�ѯ���bo�м�����Ӧ���ӱ�vo
                resultBo.setResultList(tempList); //������ѯ���bo�д洢�ӱ�����ArrayList

                returnList.add(resultBo); //��������֯�Ĳ�ѯ����ŵ����ظ�ǰ̨��ArrayList��
            } else {
                //���������ѯ����Ľɿ�ƾ֤�š������������������ͬ
                //˵������������ֻͬ����ϸ��ͬ��Ҳ����1���������ݶ�Ӧ������ϸ�����ݵ����
                //��Ѹ�������ϸ���ݷŵ���ͬ��resultBo����ϸArrayList��
                if (bo.getSbjkzb().getJkpzh().equals(resultBo.getSbjkzb().
                        getJkpzh()) &&
                    bo.getSbjkzb().getJsjdm().equals(resultBo.getSbjkzb().
                        getJsjdm())) {
                    tempList.add(bo.getSbjkmx());
                    //���걨�ɿ���ϸ���еĿ�˰������ӣ������걨�ɿ�����vo�������ѯҳ����ʾ��
                    int kssl = resultBo.getSbjkzb().getKssl() +
                               bo.getSbjkmx().getKssl().intValue();
                    resultBo.getSbjkzb().setKssl(kssl);
                    resultBo.setResultList(tempList);
                } else {
                    //��Ϊ��һ����ͬ�����ݣ��������¹���resultBo��tempList
                    resultBo = new JksBo();
                    tempList = new ArrayList();

                    resultBo.setSbjkzb(bo.getSbjkzb());
                    //���걨�ɿ���ϸ���еĿ�˰���������걨�ɿ�����vo�������ѯҳ����ʾ��
                    resultBo.getSbjkzb().setKssl(bo.getSbjkmx().getKssl().
                                                 intValue());
                    tempList.add(bo.getSbjkmx());
                    resultBo.setResultList(tempList);

                    returnList.add(resultBo); //��������֯�Ĳ�ѯ����ŵ����ظ�ǰ̨��ArrayList��
                }
            }
        }

        return returnList;
    }

}

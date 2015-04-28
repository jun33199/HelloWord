package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszmx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Qswszz;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbjkzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Szsm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Yskm;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zh;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BlWszBo;
import com.creationstar.bjtax.qsgl.model.bo.JghdsjBo;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.bjtax.dj.model.SWDJJBSJ;
import com.ttsoft.bjtax.dj.model.ZRRJBSJ;
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
 * <p>Title: ��¼��˰��˰֤��processor</p>
 *
 * <p>Description: ��¼��˰��˰֤��processor</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: </p>
 *
 * @author ����
 * @version 1.0
 */
public class BlWszProcessor implements Processor {

    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        if (vo == null) {
            throw new ApplicationException("VOPackage�ǿ�ָ��!!!");
        }

        switch (vo.getAction()) {
        case ActionType.GET:
            result = createWsz(vo);
            break;

        case ActionType.INSERT:
            saveWsz(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;

    }

    private Object createWsz(VOPackage vo) throws BaseException {
        BlWszBo bo = (BlWszBo) vo.getData();
        UserData ud = vo.getUserData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = bo.getQswszz();
            String jsjdm = CommonUtil.getWszJsjdm(ud, conn);

            //�����û������������ѯ�걨����������Ϣ��������ϲ�¼�������򷵻�һ���걨�����vo
            Sbzb sbzb = checkSbzb(bo, conn);
            //�����û��������������ѯ��˰֤�Ƿ��Ѵ���
            Qswszz haveOne = (Qswszz) DAOFactory.getInstance().getQswszzDAO().
                             get(qswszz, conn);
            if (haveOne != null) {
                throw new ApplicationException("����˰֤�Ѵ���");
            }
            //����û�ѡ���˲�¼�ѻ��ܵ���˰֤����Ҫ����걨�ɿ������Լ���˰֤���ܱ�������Ϣ
            if (Constants.WSZ_CLBJDM_YJZ.equals(qswszz.getClbjdm())) {
                String sbhzdh = checkHzWsz(bo, jsjdm, conn);
                bo.setSbhzdh(sbhzdh);
            }

            bo = getWszInfo(bo, ud, sbzb, conn);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BlWszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return bo;
    }


    private void saveWsz(VOPackage vo) throws BaseException {
        BlWszBo bo = (BlWszBo) vo.getData();

        Connection conn = null;

        try {
            conn = QSDBUtil.getConnection();

            Qswszz qswszz = bo.getQswszz();
            Qswszmx qswsmx = bo.getQswszmx();

            //������˰֤����
            try {
                DAOFactory.getInstance().getQswszzDAO().insert(qswszz, conn);
                Debug.out("��¼��˰֤��������˰֤����ɹ�....");
            } catch (BaseException ex) {
                ex.printStackTrace();
                throw new ApplicationException("��¼��˰֤��������˰֤������Ϣ����");
            }

            //������˰֤��ϸ��
            try {
                DAOFactory.getInstance().getQswszmxDAO().insert(qswsmx, conn);
                Debug.out("��¼��˰֤��������˰֤��ϸ�ɹ�....");
            } catch (BaseException ex) {
                ex.printStackTrace();
                throw new ApplicationException("��¼��˰֤��������ϸ����Ϣ����");
            }

            //�޸��걨�����״̬��ʶ
            //���Ϊ�����
            String sbbh = bo.getSbbh();
            try {
                DAOFactory.getInstance().getSbzbDAO().update(sbbh, conn);
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException("��¼��˰֤�������걨����״̬����");
            }

            //��������걨
            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //ȡ�ü���˰���
            BigDecimal jmszje = hdbo.getJmzje();
            
            Sbzb sbzb = new Sbzb();
            sbzb.setSbbh(sbbh);
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);
                
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
                HashMap map_jmsb = new HashMap();
                map_jmsb.put("jsjdm", qswszz.jsjdm);
                map_jmsb.put("szsmdm", qswsmx.szsmdm);
                map_jmsb.put("jsje", hdbo.getJzse());
                map_jmsb.put("jmse", jmszje);
                map_jmsb.put("lrr", qswszz.lrr);
                /** @todo jmxmdm �ṹ�������⣡ */
                map_jmsb.put("jmxmdm", Constants.JMXMDM); //��Ŀ����
                map_jmsb.put("jmxzdm", jmxzdm); //������Ŀ����
                map_jmsb.put("cjrq", qswszz.cjrq);
                map_jmsb.put("skssjsrq", qswsmx.getSkssjsrq());
                map_jmsb.put("skssksrq", qswsmx.getSkssksrq());
                if (!CommonUtil.insertSBJM(map_jmsb)) {
                    throw new ApplicationException("����˰�ѵļ����걨��Ϣ��ʱ�򱨴�");
                }
                Debug.out("��������걨�ɹ���");
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BlWszProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

    /**
     * ��¼��˰��˰֤�������û�¼����걨��ż���걨�����������ݣ����ͨ������򷵻��걨����vo
     * @param bo BlWszBo
     * @param conn Connection
     * @return Sbzb
     * @throws BaseException
     */
    private Sbzb checkSbzb(BlWszBo bo, Connection conn) throws BaseException {
        //ͨ�������걨������ֵ�걨��ţ�����������걨��������
        Sbzb sbzb = new Sbzb();
        sbzb.setSbbh(bo.getSbbh());

        try {
            sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //�������걨�����еĲ�¼��ʶ��blbs��Ϊ1����״̬��ʶΪ7�Ĳ��ܲ�¼�ǻ��ܽɿ���
            if (sbzb == null) {
                throw new ApplicationException("δ�ҵ���ƥ����걨��¼�����Ȳ�¼�걨��Ϣ���ٲ�¼�ɿ��飡");
            } else if (Constants.ZB_BLBS_FBL.equals(sbzb.getBlbs())) {
                throw new ApplicationException("�ñ��걨���ǲ�¼�ģ����ܲ�¼�ɿ��飡");
            }
            //ֻ���Ƿ�Ʊ����������ѻ��ܵ��������Ϊ��¼�Ľɿ����Ѿ��ı����걨�����״̬��
            //�������걨״̬Ϊ�����(ztbsΪ99)������²�¼��˰֤
            else if (Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs()) &&
                     (bo.getFp().equals("0") ||
                      bo.getClbjdm().equals(Constants.WSZ_CLBJDM_YJK))) {
                throw new ApplicationException("�ñ��걨����⣬�����ٴβ�¼�ɿ��飡");
            } else if (!Constants.ZB_ZTBS_YFH.equals(sbzb.getZtbs())
                       && !Constants.ZB_ZTBS_YRK.equals(sbzb.getZtbs())) {
                throw new ApplicationException("������ɸ��ϼ��տ�������ٲ�¼�ɿ��飡");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return sbzb;
    }

    /**
     * ����û�ѡ���˲�¼�ѻ��ܵ���˰֤���������걨�ɿ������Լ���˰֤���ܱ������
     * @param bo BlWszBo
     * @param jsjdm String
     * @param conn Connection
     * @return String   �����걨���ܵ���
     * @throws BaseException
     */
    private String checkHzWsz(BlWszBo bo, String jsjdm, Connection conn) throws
            BaseException {
        Sbjkzb sbjkzb = new Sbjkzb();

        sbjkzb.setJkpzh(bo.getJkpzh());
        sbjkzb.setJsjdm(jsjdm);

        String sbhzdh = null;

        try {
            sbjkzb = (Sbjkzb) DAOFactory.getInstance().getSbjkzbDAO().get(
                    sbjkzb, conn);

            //�������걨�����еĲ�¼��ʶ��blbs��Ϊ1����״̬��ʶΪ7�Ĳ��ܲ�¼�ǻ��ܽɿ���
            if (sbjkzb == null) {
                throw new ApplicationException(
                        "δ�ҵ���ƥ��Ľɿ��飬���Ȳ�¼���ܷ�ʽ���ɵĽɿ��飬�ٲ�¼��˰֤��");
            }
            //����ɿ��鲻�ǻ��ܷ�ʽ���ɵ�
            else if (!sbjkzb.getSjly().equals(Constants.JKS_SJLY_HZ)
                     || sbjkzb.getSbbh() != null) {
                throw new ApplicationException("�ýɿ��鲻�ǻ��ܷ�ʽ���ɵģ�");
            }
            //���˰�ֲ�����˰
            else if (!sbjkzb.getSzdm().equals(Constants.WSZ_QSSZDM)) {
                throw new ApplicationException("�ýɿ��鲻����˰�ɿ��飡");
            }

            //�����˰֤���ܱ��ɿ����Ƿ�����˷�Ʊ���û�������걨���ܵ����Ƿ����
            sbhzdh = DAOFactory.getInstance().getQswszhzDAO().get(jsjdm,
                    bo.getJkpzh(), conn);
            if (Constants.WSZ_SBHZDH_DEFAULT.equals(sbhzdh)) {
                throw new ApplicationException("��������ɽɿ���ķ�Ʊ!");
            } else if (sbhzdh == null || sbhzdh.equals("")) {
                throw new ApplicationException("û������˰֤���ܱ����ҵ���ƥ��ļ�¼!");
            }

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return sbhzdh;
    }


    /**
     * ������˰֤�������ϸ������ݣ�����vo����bo����
     * @param bo BlWszBo
     * @param ud UserData
     * @param sbzb Sbzb
     * @param conn Connection
     * @return BlWszBo
     * @throws BaseException
     */
    private BlWszBo getWszInfo(BlWszBo bo, UserData ud, Sbzb sbzb,
                               Connection conn) throws BaseException {
        Qswszz wszZb = null; //������˰֤���������
        Qswszmx wszMx = null; //������˰֤��ϸ������

        try {
            Timestamp now = CommonUtil.getDBtime(conn);

            Tufwxx tufwxx = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.sbbh, conn);
            if (tufwxx == null) {
                throw new ApplicationException("���ݡ����صĻ�����Ϣ����Ϊ�գ�");
            }

            JghdsjBo hdbo = CommonUtil.getJZSE(bo.getSbbh(), conn);
            //ȡ�ü���˰���
            BigDecimal jmszje = hdbo.getJmzje();
            //ȡ�úϼƼ�˰���
            double total_je = hdbo.getYnse().doubleValue();

            wszZb = new Qswszz();

            //��ʼ������˰֤����
            wszZb.setWszh(bo.getWszh());
            wszZb.setNdzb(bo.getNdzb());
            wszZb.setPzzldm(bo.getPzzldm());
            wszZb.setClbjdm(bo.getClbjdm());
            wszZb.setNd(DateUtil.getDate().substring(0, 4));
            wszZb.setCjr(ud.getYhmc());
            wszZb.setCjrq(now);
            wszZb.setLrr(ud.getYhid()); //Ϊ���Ժ���ܵ�ʱ��Ļ������ݣ����ո��˻��ܵľ���������ֶ�
            wszZb.setLrrq(now);
            wszZb.setSwjgzzjgdm(ud.getSsdwdm());
            wszZb.setSwjgzzjgmc(ud.getSsdwmc());
            wszZb.setJsjdm(CommonUtil.getWszJsjdm(ud, conn)); //�����˰֤�����˰����صļ��������
            wszZb.setFsdm(Constants.WSZ_FSDM); //ֻ��һ�ַ�ʽ���롪����˰����ذ���ʽ
            wszZb.setSbbh(bo.getSbbh());
            wszZb.setSwjgzzjgmc(ud.ssdwmc); //���ջ�������

            //����������˰֤�������
            wszZb.setJsfsdm(sbzb.getJsfsdm()); //��˰��ʽ����
            wszZb.setJsfsmc(sbzb.getJsfsmc()); //��˰����
            //���Ʊ֤�ʻ���������Ϣ
            Zh zh = CommonUtil.getPzzhVo(ud, conn);
            wszZb.setZsddm(ud.xtsbm1); //Ʊ֤�ʻ�����
            wszZb.setZsdmc(zh.zhmc); //���յ�����
            wszZb.setFdcwz(tufwxx.getTdfwzldz()); //���ز�λ��
            wszZb.setHtclrq(tufwxx.getHtqdsj()); //���ݺ�ͬǩ��ʱ��

            //���ָ��˻��ǷǸ��ˣ���ҵ��������˰֤
            //����Ǹ��˹���ķ���
            if (Constants.YHBZ_GR.equals(sbzb.yhbs)) {
                Grxx grxx = (Grxx) DAOFactory.getInstance().getGrxxDAO().
                            getOneBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(grxx.getJsjdm()); //��˰�˼��������
                ZRRJBSJ grjb = CommonUtil.getGrJBSJ(grxx);
                wszZb.setDjzclxdm(grjb.getSwdjlx()); //�Ǽ�ע�����͡���˰��Ǽ�����
                wszZb.setDjzclxmc(grjb.getSwdjlx()); //�Ǽ�ע�����͡���˰��Ǽ�����
                wszZb.setFsmc(Constants.WSZ_DJZCLX_MC);
                wszZb.setZjhm(grxx.getSfzjhm()); //֤������
                wszZb.setZjlxdm(grxx.getSfzjlx()); //֤�����ʹ���
                wszZb.setGjbzhydm(Constants.WSZ_GJBZHYDM); //��ù��ұ�׼��ҵ����
                wszZb.setGjbzhymc(Constants.WSZ_GJBZHYMC); //��ù��ұ�׼��ҵ����
                wszZb.setNsrmc(grxx.getNsrmc()); //��˰������
                wszZb.setNsrdm(grxx.sfzjhm); //��˰�˴���
                wszZb.setZjlxmc(grxx.sfzjlxmc); //֤����������
                Debug.out("��¼��˰��˰֤��ȡ�ø��˵ĵǼ���Ϣ....");
            }
            //����ǷǸ�����Ϣ��ȡ�ù�����ҵ����Ȳ�ͬ
            else {
                Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                              getBySbbh(sbzb.sbbh, conn);
                wszZb.setNsrjsjdm(fgrxx.getJsjdm());
                SWDJJBSJ swdjJbsj = CommonUtil.getFgrJBSJ(fgrxx.jsjdm);
                wszZb.setDjzclxdm(swdjJbsj.getDjzclxdm());
                wszZb.setNsrjsjdm(fgrxx.jsjdm);
                wszZb.setSwjgzzjgdm2(swdjJbsj.getSwjgzzjgdm()); //���Ǹ��˵�����˰����ش������
                wszZb.setGjbzhydm(swdjJbsj.getGjbzhydm());
                wszZb.setNsrmc(fgrxx.getNsrmc()); //��˰������
                wszZb.setNsrdm(fgrxx.jsjdm);
                Debug.out("��¼��˰��˰֤��ȡ�÷Ǹ��˵�������Ϣ....");
            }

            //������ϸ��
            //��ʼ����ϸ
            wszMx = new Qswszmx();

            //��������ֵ����ṹ�����⣬���޸ĺ����޸Ĵ˴�����Ϣ
            wszMx.setWszh(wszZb.getWszh());
            wszMx.setNdzb(wszZb.getNdzb());
            wszMx.setPzzldm(wszZb.getPzzldm());
            wszMx.setSzdm(Constants.WSZ_QSSZDM); //Ĭ��˰�ִ���Ϊ����˰74��
            wszMx.setSzmc(Constants.WSZ_QSSZMC); //Ĭ��˰�ִ���Ϊ����˰74��

            wszMx.setCjrq(now); //��������
            wszMx.setLrrq(now); //¼������
            wszMx.setLrr(ud.getYhid());
            wszMx.setCjr(ud.getYhmc());
            //wszMx.setQxdm(qxdm); //���ش���
            wszMx.setJsjdm(wszZb.getJsjdm()); //���������
            wszMx.setSwjgzzjgdm(wszZb.getSwjgzzjgdm());
            wszMx.setJzbz(Constants.WSZ_JZBZ_BL); //���˱�־��Ĭ������0
            //�������
            wszMx.setNd(DateUtil.getDate().substring(0, 4));
            //���Ԥ�㼶��
            wszMx.setYsjcdm(Constants.YSJCDM_DF); //������Ԥ�㼶�δ��룬21 �ط���
            wszMx.setYsjcmc(Constants.YSJCDM_DF_MC); //������Ԥ�㼶�δ��룬21 �ط���
            wszMx.setSkssjsrq(wszZb.getHtclrq()); //˰����������
            wszMx.setSkssksrq(wszZb.getHtclrq()); //˰����������

            if (tufwxx.getTdfwqszymj() != null &&
                tufwxx.getTdfwqszymj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getTdfwqszymj()); //Ȩ��ת�����
            } else if (tufwxx.getFwjzmj() != null &&
                       tufwxx.getFwjzmj().doubleValue() > 0) {
                wszMx.setQszymj(tufwxx.getFwjzmj()); //Ȩ��ת�����
            } else {
                wszMx.setQszymj(new BigDecimal(0));
            }

            wszMx.setSl(sbzb.getSl()); //˰��
            Szsm szsm = CommonUtil.getSZSMDM(tufwxx.getTdfwqszylx(), conn);
            wszMx.setSzsmdm(szsm.szsmdm); //���˰��˰Ŀ����
            wszMx.setSzsmmc(szsm.szsmmc); //���˰��˰Ŀ����
            Yskm yskm = CommonUtil.getYskm(wszMx.getSzdm(), conn);
            if (yskm == null) {
                throw new ApplicationException("û��Ԥ���Ŀ���룡");
            }
            //���Ԥ���Ŀ
            wszMx.setYskmdm(yskm.yskmdm);
            wszMx.setYskmmc(yskm.yskmmc);

            wszZb.setYqts(new BigDecimal(0)); //��������
            wszZb.setZnjzje(new BigDecimal(0)); //���ɽ��ܽ��
            wszZb.setJsje(jmszje); //��ü���˰�ܶ�
            wszZb.setHjsjje(hdbo.getYnse()); //��˰֤�����е�"�ϼ�ʵ��˰��"�����︳���ǡ�Ӧ��˰�

            wszMx.setYjhkc(wszZb.getJsje()); //������ϸ��ֻ��һ�����ݣ������ѽɻ�۳��ֶε�������ļ�˰����ֶ�
            wszMx.setJsje(hdbo.getJsyj()); //��˰�����ڼ�˰����
            wszMx.setSjse(hdbo.getYnse()); //ʵ��˰�����Ӧ��˰����˼��տ��е��

            //����û���¼�����ѻ��ܵ���˰֤������걨���ܵ��Ÿ�ֵ
            if (bo.getClbjdm().equals(Constants.WSZ_CLBJDM_YJZ)) {
                wszZb.setSbhzdh(bo.getSbhzdh());
            }

            bo.setQswszz(wszZb);
            bo.setQswszmx(wszMx);

        } catch (Exception ex) {
            throw ExceptionUtil.getBaseException(ex);
        }

        return bo;
    }

}

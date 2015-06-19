package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbtdfwgl;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.BzqsBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBzqsBo;
import com.creationstar.bjtax.qsgl.util.Arith;
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
import com.ttsoft.framework.util.DateUtil;
import com.ttsoft.framework.util.VOPackage;

/**
 * ������˰��processor
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
public class BzqsProcessor implements Processor {
    /**
     * ���ݴ������ݽ��в�ͬ�Ĳ���.
     *
     * @param vo the VOPackage.
     * @return Object ҵ�����.
     * @throws BaseException �׳����쳣.
     */
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;

        Debug.out("--Debug-- Info : Here is BzqsProcessor.process(vo)");

        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {

        case ActionType.INSERT:
            result = doAdd(vo);

            break;

        case ActionType.QUERY:

            result = doQuery(vo);

            break;

        case ActionType.DELETE:

            doDelete(vo);

            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }


    /**
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private BzqsBo doAdd(VOPackage vo) throws BaseException {
        Connection conn = null;
        BzqsBo bo = (BzqsBo) vo.getData();
        try {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();
            //��ǰ̨��������bo�ڴ���֯�ɺ�̨������Ҫ�õ����ݣ��ֱ���б���
            /**
             * 1�Ȼ�ȡ����sbzb��Ҫ����Ϣ
             * 2��ȡ������Ϣ��grxx����Ϣ
             * 3��ȡ���ط�����Ϣ��tdfwxx
             * 4��ȡ�걨���������ء�������Ϣ��Ĺ�����SBTDFWGL��Ϣ
             * 5ִ�в������
             */
            Sbzb sbzb = getSbzb(bo, user);
            sbzb.setCjrq(new Timestamp(System.currentTimeMillis()));
            //��commonutil�л�ȡ�걨��ź͵�ǰʱ��,�赽����vo��
            //��3�������걨���
            sbzb.setSbbh(CommonUtil.getBZBH(user.getXtsbm1(), conn));
            List l = getGrxx(bo, user, sbzb.getSbbh(), sbzb.getCjrq());
            Tufwxx tufwxx = getTufwxx(bo, user);
            Sbtdfwgl sbtdfwgl = getSbtdfwgl(bo, user);
            Fgrxx fgrxx = getFgrxx(bo, user);
            sbtdfwgl.setSbbh(sbzb.getSbbh());
            fgrxx.setSbbh(sbzb.getSbbh());
            //����ʱ��
            sbzb.setLrrq(sbzb.getCjrq());
            sbzb.setSbrq(sbzb.getCjrq());
            fgrxx.setLrrq(sbzb.getCjrq());
            fgrxx.setCjrq(sbzb.getCjrq());
            tufwxx.setCjrq(sbzb.getCjrq());
            tufwxx.setLrrq(sbzb.getCjrq());
            sbtdfwgl.setCjrq(sbzb.getCjrq());
            sbtdfwgl.setLrrq(sbzb.getCjrq());
            //¼���� ������
            sbzb.setCjr(user.getYhmc());
            sbzb.setLrr(user.getYhmc());
            fgrxx.setLrr(user.getYhmc());
            fgrxx.setCjr(user.getYhmc());
            tufwxx.setCjr(user.getYhmc());
            tufwxx.setLrr(user.getYhmc());
            sbtdfwgl.setCjr(user.getYhmc());
            sbtdfwgl.setLrr(sbtdfwgl.getCjr());

            //��ȡ���ط���Ψһ��ʾ,�������ط�����Ϣ��tdfwxx
            //���걨���������ء�������Ϣ��Ĺ�����SBTDFWGL
            tufwxx.setTdfwid(CommonUtil.getTDFWID(conn));
            Debug.out("tufwxx.setTdfwid in BzqsProcessor is " +
                      tufwxx.getTdfwid());
            sbtdfwgl.setTdfwid(tufwxx.getTdfwid());

            DAOFactory.getInstance().getSbzbDAO().insert(sbzb, conn);
            DAOFactory.getInstance().getTufwxxDAO().insert(tufwxx, conn);
            if (bo.getYhbs().equals(Constants.ZB_YHBS_GR)) {
                Debug.out("������Ϣ����");
                //������Ϣ��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                //���������
                //grxx = CommonUtil.handleZRR(grxx,user);
                //��ȡ�Ǽ���Ϣ,������ڵǼ���Ϣ,���滻,������¼��ı���
                l = CommonUtil.handleZRR(l, user);
                bo.setNsrList(l);

                DAOFactory.getInstance().getGrxxDAO().insert(l, conn);
            } else {
                Debug.out("�Ǹ�����Ϣ����");
                DAOFactory.getInstance().getFgrxxDAO().insert(fgrxx, conn);
            }
            DAOFactory.getInstance().getSbtdfwglDAO().insert(sbtdfwgl, conn);
            //��viewҳ����Ҫ��ʾ����Ϣ���ص�ǰ̨
            bo.setSbbh(sbzb.getSbbh());
            bo.setCjrq(DataConvert.TimeStamp2String(sbzb.getCjrq()));

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BzqsProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return bo;
    }


    /**
     * ��ȡ�걨������Ϣ
     */
    public Sbzb getSbzb(BzqsBo bo, UserData user) {
        Sbzb sbzb = new Sbzb();
        //���ý�ίҵ���ţ�modify by fujx��20081126
        sbzb.setJwywbh(bo.getJwywbh());
        //���ú�ͬ��ţ�modify by fujx��20081126
        sbzb.setHtbh(bo.getHtbh());
        //��¼��ʶ
        sbzb.setBlbs(Constants.ZB_BLBS_FBL);
        //�������˰��ʶ
        sbzb.setBljmsbs(Constants.ZB_BLJMSBS_BZ);
        //��ע
        sbzb.setBz(bo.getBeizhu());
        //�������ز��������
        sbzb.setFwtdbmdm(bo.getFwtdbmslh());
        //��˰��ʽ
        sbzb.setJsfsdm(" ");
        //��˰��ʽ
        sbzb.setJsfsmc(" ");
        //��˰��� (����Ϊ0)
        sbzb.setJsje(new BigDecimal(0));
        //�û���ʶ
        sbzb.setYhbs(bo.getYhbs());
        //��˰�����ʹ���
        if (sbzb.getYhbs().equals(Constants.ZB_YHBS_GR)) {
            sbzb.setNsrlxdm("99");
        } else {
            sbzb.setNsrlxdm(bo.getNsrlx());
        }

        //˰��(����Ϊ0)
        sbzb.setSl(new BigDecimal(0));
        //˰�������֯��������
        sbzb.setSwjgzzjgdm(user.getSsdwdm());

        //Ӧ��˰��(����Ϊ0)
        sbzb.setYnse(new BigDecimal(0));
        //������Ա����
        sbzb.setZsrymc(user.getYhmc());
        //״̬��ʶ
        sbzb.setZtbs(bo.getZtbs());
        //Ʊ֤�ʻ�����
        sbzb.setPzzhdm(user.getXtsbm1());
        //Ʊ֤�ʻ�����
        sbzb.setPzzhmc(user.getXtsbm2());
        return sbzb;
    }


    /**
     * ��ȡ������Ϣ����Ϣ
     */
    public List getGrxx(BzqsBo bo, UserData user, String sbbh, Timestamp now) throws
            BaseException {
        //���������Ϣ
        List nsrList = bo.getNsrList();
        List l = new ArrayList();
        for (int i = 0; i < nsrList.size(); i++) {
            Grxx grxx = (Grxx) nsrList.get(i);
            grxx.cjr = user.getYhmc();
            grxx.cjrq = now;

            if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
                grxx.setFwjhbs(Constants.FWJHBS);
            } else {
                grxx.setFwjhbs(Constants.FFWJHBS);
            }
            grxx.lrr = user.getYhmc();
            grxx.lrrq = now;
            Debug.out("BzProcessor bo nsrmc22: " + grxx.nsrmc);
            grxx.sbbh = sbbh;
            l.add(grxx);
        }

        //��ϵ�绰
        //grxx.setLxdh(bo.getLxdh());
        //��˰������
        //grxx.setNsrmc(bo.getNsrmc());
        //֤������
        //grxx.setSfzjhm(bo.getSfzjhm());
        //֤������
        //grxx.setSfzjlx(bo.getSfzjlx());
        //֤����������
        //grxx.setSfzjlxmc(bo.getSfzjlxmc());
        //��������,��������
        //grxx.setGjdm(bo.getGjdm());
        //grxx.setGjmc(bo.getGjmc());


        return l;
    }

    /**
     * �õ��Ǹ�����Ϣ��vo
     * @param bo BzqsBo
     * @param user UserData
     * @return Fgrxx
     */
    public Fgrxx getFgrxx(BzqsBo bo, UserData user) {
        Fgrxx fgrxx = new Fgrxx();
        if (bo.getTdfwqszylx().equals(Constants.TDFWQSZY_JH)) {
            fgrxx.setFwjhbs(Constants.FWJHBS);
        } else {
            fgrxx.setFwjhbs(Constants.FFWJHBS);
        }

        fgrxx.setJsjdm(bo.getJsjdm());
        fgrxx.setKhyhdm(bo.getKhyhdm());
        fgrxx.setKhyhmc(bo.getKhyhmc());
        fgrxx.setLxrxm(bo.getLxrxm());
        fgrxx.setNsrlxdm(bo.getNsrlx());
        fgrxx.setNsrlxmc(bo.getNsrlxmc());
        fgrxx.setNsrmc(bo.getNsrmc());
        fgrxx.setYhzh(bo.getYhzh());
        fgrxx.setLxdh(bo.getLxdh());
        return fgrxx;
    }

    /**
     * ��ȡ��ȡ���ط�����Ϣ��tdfwxx
     */
    public Tufwxx getTufwxx(BzqsBo bo, UserData user) {
        Tufwxx tufwxx = new Tufwxx();
        try {
            //��ע
            tufwxx.setBz(bo.getBeizhu());
            //���ִ���
            tufwxx.setBzdm(bo.getBz());
            //�ɽ��۸�����ң�
            tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()));
            //�ɽ��۸���ң�
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()));
            //���ز���Ŀ����
            tufwxx.setFdcxmmc(bo.getFdcxmmc());
            //����
            tufwxx.setFldm(bo.getFl());
            //��������
            tufwxx.setFlmc(bo.getFlmc());
            //���ݽ������
            //�޸�ΪС������λmodify by fujx��20081126
            if (null == bo.getFwjzmj() || "".equals(bo.getFwjzmj())) {
                tufwxx.setFwjzmj(DataConvert.String2BigDecimal(Arith.roundStr(
                        "0.000", 3)));
            } else {
                tufwxx.setFwjzmj(DataConvert.String2BigDecimal(Arith.roundStr(
                        bo.getFwjzmj(), 3)));
            }

            //�������
            tufwxx.setFwlxdm(bo.getFwlb());
            //����
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()));
            //��ͬ����Լ��ǩ��ʱ��
            String date = bo.getHyqdsj();
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(date));
            //���
            tufwxx.setNd(DateUtil.getDate().substring(0, 4));
            //�����۸�����ң�
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()));
            //���ء�����Ȩ��ת������
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx());
            //���ء�����Ȩ��ת�����
            //�޸�ΪС������λmodify by fujx��20081126
            if (null == bo.getTdfwqszymj() || "".equals(bo.getTdfwqszymj())) {
                tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(Arith.
                        roundStr("0.000", 3)));
            } else {
                tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(Arith.
                        roundStr(bo.getTdfwqszymj(), 3)));
            }
            //���ء����������ַ
            tufwxx.setTdfwzldz(bo.getTdfwzldz());
            Debug.out("bo.getZhyrmb()" + bo.getZhyrmb());
            //�ۺϼ۸�����ң�
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()));
            //��������
            tufwxx.setFlmc(bo.getFlmc());
            //���֣�����������ط���Ȩ��ת��
            tufwxx.setBzmc(bo.getBzmc());
            tufwxx.setFwlxmc(bo.getFwlbmc());
            tufwxx.setTdfwqszymc(bo.getTdfwqszylxmc());

            tufwxx.setRjl(bo.getRjl());
            tufwxx.setTdjc(bo.getTdjc());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return tufwxx;

    }

    /**
     * ��ȡ�걨���������ء�������Ϣ��Ĺ�����SBTDFWGL��Ϣ
     */
    public Sbtdfwgl getSbtdfwgl(BzqsBo bo, UserData user) {
        Sbtdfwgl sbtdfwgl = new Sbtdfwgl();
        //������
        sbtdfwgl.setCjr(user.getYhmc());
        //¼����
        sbtdfwgl.setLrr(sbtdfwgl.getCjr());

        return sbtdfwgl;
    }


    /**
     * ����: �걨���\��˰������\��˰������\���ز���Ŀ����\���֤�����ʹ���\���֤������
     * 1������걨����У���
     *    ���ȸ����걨��Ų�ѯ�걨�����Ӷ�������ѯ���ˡ��Ǹ�����Ϣ�ͷ������ص���Ϣ
     *
     * 2������걨��Ų�ѯ����Ϊ�գ�״̬��ʶΪ���ˣ���
     *    ������˰�����͡���˰�����ơ����֤�����͡����֤�����롢���ز���Ŀ���� 5��������ѯ���Ӷ���������
     *
     * 3������걨���Ϊ�գ�״̬��ʶΪ�Ǹ���
     *    ���հ�����˰�����͡���˰�����ơ����ز���Ŀ���ơ��ڷǸ��˵ı��н��в�ѯ���Ӷ���������
     *
     * 4������걨���Ϊ�գ�״̬��ʶΪ�գ���
     *    ����\��˰������\��˰������\���ز���Ŀ����\���֤�����ʹ���\���֤������
     *
     * ����Ժ��������Ĳ�ѯ������ֻҪ�����ֲ�ͬ������м������ش���Ϳ�����
     *
     *
     * @param vo VOPackage
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        /** @todo ��Ҫ����һ����ѯ����--�����е����ϱ�ʶ */
        ArrayList resultList = new ArrayList();
        Connection conn = null;
        QueryBzqsBo bo = (QueryBzqsBo) vo.getData();
        try {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();
            //��ǰ̨��������bo�ڴ���֯�ɺ�̨������Ҫ�õ����ݣ��ֱ���б���

            //����bo�е�sbbh��ѯ
            if (bo.getSbbh() != null && !bo.getSbbh().equals("")) {
                Sbzb sbzb = new Sbzb();
                sbzb.setSbbh(bo.getSbbh());
                sbzb = (Sbzb) DAOFactory.getInstance().getSbzbDAO().get(sbzb,
                        conn);
                bo.setSbzb(sbzb);

                //��������е�״̬���ǲ�����˰���򷵻�һ���յ�arraylist
                if (!(sbzb.getBljmsbs().equalsIgnoreCase(Constants.
                        ZB_BLJMSBS_BZ))) {
                    return resultList;
                }

                //��ѯ������Ϣ
                if (Constants.ZB_NSRLX_GR.equals(sbzb.getNsrlxdm())) {
                    List l = (List) DAOFactory.getInstance().getGrxxDAO().
                             getAllBySbbh(sbzb.getSbbh(), conn);
                    bo.setNsrList(l);
                }
                //��ѯ�Ǹ�����Ϣ
                else {
                    Fgrxx fgrxx = (Fgrxx) DAOFactory.getInstance().getFgrxxDAO().
                                  getBySbbh(sbzb.getSbbh(), conn);
                    bo.setFgrxx(fgrxx);
                }

                //��ѯ���ء�������Ϣ
                Tufwxx tf = (Tufwxx) DAOFactory.getInstance().getTufwxxDAO().
                            getBySbbh(sbzb.getSbbh(), conn);
                bo.setTufwxx(tf);
                resultList.add(bo);
            }
            //����걨��Ų�ѯ����Ϊ�գ���
            else {
                //��ѯ����
                StringBuffer condition = new StringBuffer("");
                condition.append(" AND a.bljmsbs = '").append(Constants.
                        ZB_BLJMSBS_BZ)
                        .append("' "); //��������˰
                Debug.out("nsrlx=" + bo.getNsrlx());
                //��˰������Ϊ���ˣ�
                //������˰�����ơ����֤�����͡����֤�����롢��������롢���ز���Ŀ���Ʋ�ѯ
                if (Constants.ZB_NSRLX_GR.equals(bo.getNsrlx())) {

                    if (bo.getJsjdm() != null && !bo.getJsjdm().equals("")) {
                        condition.append(" AND b.jsjdm = '").append(bo.getJsjdm()).
                                append("' ");
                    }
                    if (bo.getZjlxdm() != null && !bo.getZjlxdm().equals("")) {
                        condition.append(" AND b.sfzjlx = '").append(bo.
                                getZjlxdm())
                                .append("' ");
                    }
                    if (bo.getSfzjhm() != null && !bo.getSfzjhm().equals("")) {
                        condition.append(" AND b.sfzjhm = '").append(bo.
                                getSfzjhm())
                                .append("' ");
                    }
                    if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                        condition.append(" AND b.nsrmc LIKE '%").append(bo.
                                getNsrmc())
                                .append("%' ");
                    }
                    if (bo.getFdcxmmc() != null && !bo.getFdcxmmc().equals("")) {
                        condition.append(" AND d.fdcxmmc LIKE '%").append(bo.
                                getFdcxmmc())
                                .append("%' ");
                    }
                    resultList = (ArrayList) DAOFactory.getInstance().
                                 getSbzbDAO()
                                 .queryBzqs(condition.toString(), conn, true);

                }
                //��˰������Ϊ�Ǹ��ˣ���
                else {
                    //������˰�����ơ���˰�����͡���������롢���ز���Ŀ���Ʋ�ѯ
                    if (bo.getJsjdm() != null && !bo.getJsjdm().equals("")) {
                        condition.append(" AND b.jsjdm = '").append(bo.getJsjdm()).
                                append("' ");
                    }
                    if (bo.getNsrlx() != null && !bo.getNsrlx().equals("")) {
                        condition.append(" AND b.nsrlxdm = '").append(bo.
                                getNsrlx()).
                                append("' ");
                    }
                    if (bo.getNsrmc() != null && !bo.getNsrmc().equals("")) {
                        condition.append(" AND b.nsrmc LIKE '%").append(bo.
                                getNsrmc())
                                .append("%' ");
                    }
                    if (bo.getFdcxmmc() != null && !bo.getFdcxmmc().equals("")) {
                        condition.append(" AND d.fdcxmmc LIKE '%").append(bo.
                                getFdcxmmc())
                                .append("%' ");
                    }
                    resultList = (ArrayList) DAOFactory.getInstance().
                                 getSbzbDAO()
                                 .queryBzqs(condition.toString(), conn, false);

                }
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BzqsProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return resultList;
    }

    /**
     * ��������˰modify�ķ���
     * @param vo VOPackage
     */
    /* public void doModify(VOPackage vo) throws BaseException
         {
        ArrayList list = new ArrayList();

        BzqsBo bo = (BzqsBo)vo.getData();

        Sbzb sbzb = new Sbzb();
        Grxx grxx = new Grxx();
        List l=new ArrayList();
        Fgrxx fgrxx = new Fgrxx();
        Tufwxx tufwxx = new Tufwxx();

        Connection conn = null;

        try
        {
            //vo.getUserData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            //�����걨���ȡ����Ӧ������vo
            sbzb.setSbbh(bo.getSbbh());
            sbzb = (Sbzb)DAOFactory.getInstance().getSbzbDAO().get(sbzb, conn);

            //�����걨���ȡ����Ӧ�����ط�����Ϣvo
            tufwxx = (Tufwxx)DAOFactory.getInstance().getTufwxxDAO().getBySbbh(bo.getSbbh(), conn);

            //�����޸�ҳ�洫������bo����������vo�����ط�����Ϣ���е�ֵ��update���ݿ�
            sbzb.setFwtdbmdm(bo.getFwtdbmslh() );
            sbzb.setBz(bo.getBeizhu() );

            tufwxx.setFdcxmmc(bo.getFdcxmmc() );
            tufwxx.setHtqdsj(DataConvert.String2Timestamp(bo.getHyqdsj()) );
            tufwxx.setFldm(bo.getFl() );
            tufwxx.setTdfwqszylx(bo.getTdfwqszylx() );
            tufwxx.setFwlxdm(bo.getFwlb() );
            tufwxx.setTdfwzldz(bo.getTdfwzldz() );
     tufwxx.setTdfwqszymj(DataConvert.String2BigDecimal(bo.getTdfwqszymj()) );
            tufwxx.setFwjzmj(DataConvert.String2BigDecimal(bo.getFwjzmj()) );
     tufwxx.setCjjgrmb(DataConvert.String2BigDecimal(bo.getCjjgyrmb()) );
            tufwxx.setCjjgwb(DataConvert.String2BigDecimal(bo.getCjjgywb()) );
            tufwxx.setPgjgrmb(DataConvert.String2BigDecimal(bo.getPgjg()) );
            tufwxx.setBzdm(bo.getBz() );
            tufwxx.setHldm(DataConvert.String2BigDecimal(bo.getHn()) );
            tufwxx.setZhjgrmb(DataConvert.String2BigDecimal(bo.getZhyrmb()) );

            if (Constants.ZB_NSRLX_GR.equals(bo.getNsrlx()))
            {
                //�����걨��ţ�ȡ�ø�����Ϣ���vo
     l = (List)DAOFactory.getInstance().getGrxxDAO().getBySbbh(bo.getSbbh(), conn);
                //�����޸�ҳ�洫������bo������vo�е�ֵ��update���ݿ�
                grxx.setNsrmc(bo.getNsrmc());
                grxx.setLxdh(bo.getLxdh() );
                grxx.setSfzjlx(bo.getSfzjlx() );
                grxx.setSfzjhm(bo.getSfzjhm() );
            }
            else
            {
                fgrxx = (Fgrxx)DAOFactory.getInstance().getFgrxxDAO().getBySbbh(bo.getSbbh(), conn);

            }

            //��������ļ�¼
            DAOFactory.getInstance().getSbzbDAO().update(sbzb, conn);
            //�������ط�����Ϣ��ļ�¼
            DAOFactory.getInstance().getTufwxxDAO().update(tufwxx, conn);

            if(grxx != null )
            {
                DAOFactory.getInstance().getGrxxDAO().update(grxx, conn);
            }
            else if(fgrxx != null )
            {
                DAOFactory.getInstance().getFgrxxDAO().update(fgrxx, conn);
            }
        }
        catch (Exception ex)
        {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BzqsProcessor����������",
     new StackMsg2StringUtil(ex,Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        }
        finally
        {
            QSDBUtil.freeConnection(conn);
        }
         }
     */

    /**
     *ɾ�����������ļ�¼
     * @param vo VOPackage
     * @throws BaseException
     */
    private void doDelete(VOPackage vo) throws BaseException {
        QueryBzqsBo bo = new QueryBzqsBo();
        Connection conn = null;

        try {
            //vo.getUserData();
            ArrayList list = (ArrayList) vo.getData();
            UserData user = vo.getUserData();
            conn = QSDBUtil.getConnection();

            DAOFactory.getInstance().getSbzbDAO().deleteBZQS(list, conn,
                    Constants.ZB_BLJMSBS_BZ);

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�BzqsProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
    }

}

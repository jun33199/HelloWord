package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drpcinfo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.util.CommonUtil;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.ZKAdapter;
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
 *
 * ˵������Processor�����ҳ����"��������(˰����Ա)"ģ��
 */
public class ImportFileProcessor2 implements Processor {
    Connection conn = null;
    public Object process(VOPackage vo) throws BaseException {
        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }
        switch (vo.getAction()) {
        case ActionType.UPLOAD_FILE:
            result = doUpload(vo);
            break;

        case ActionType.QUERY:
            result = doQuery(vo);
            break;

        case ActionType.GET:
            result = doGetPlh(vo);
            break;

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }

        return result;
    }

    /**
     * doUpload
     */
    private Object doUpload(VOPackage vo) throws BaseException {
        UserData loUserData = vo.getUserData();
        List llErrorRecords = new ArrayList(); //��ʽ��������ݼ���
        List llSucceedRecords = new ArrayList(); //�ѵ������ȷ������
        List llSucceedPcinfoRecords = new ArrayList(); //�ѵ������ȷ��������ļ�¼
        List loRecords;
        Map lmReturnData = new HashMap(); //���ص����ݼ���
        String lsDrpch = null; //�������κ�
        int liXH = 1; //�������
        int liDrzt = 0; //����״̬��0����1׷��
        try {
            conn = QSDBUtil.getConnection();
            Timestamp nowTime = new Timestamp(System.currentTimeMillis());

            Map loData = (HashMap) vo.getData();
            lsDrpch = (String) loData.get("pch");
            loRecords = (List) loData.get("new");
            /*
                         System.out.println("////////////////");
                         System.out.println("��¼����" + loRecords.size());
                         System.out.println("////////////////");
             */
            if (loRecords != null) {
                System.out.println("newnewnewnewnewnew");
                //�Ȳ嵼����������
                for (int i = 0; i < loRecords.size(); i++) {
                    Object loVo = loRecords.get(i);
                    //��������Ϣ
                    if (loVo.getClass().equals(Drpcinfo.class)) {
                        try {
                            ((Drpcinfo) loVo).setDrpch(lsDrpch);
                            ((Drpcinfo) loVo).setDrbs(BigDecimal.valueOf(0)); //�������
                            ((Drpcinfo) loVo).setDrsj(nowTime);
//                            ((Drpcinfo) loVo).setTjsj(nowTime);
                            ((Drpcinfo) loVo).setJsfmc((String) loData.get(
                                    "jsfsmc"));
                            ((Drpcinfo) loVo).setJsfsdm((String) loData.get(
                                    "jsfsdm"));

                            DAOFactory.getInstance().getDrpcInfoDAO().insert((
                                    Drpcinfo)
                                    loVo,
                                    conn,
                                    "01");
                            llSucceedPcinfoRecords.add(loVo);

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println("����������Ϣ����");
                            llErrorRecords.add(loVo);
                        }
                    }
                }
            } else {
                System.out.println("appendappendappendappendappend");
                loRecords = (List) loData.get("append");
                liDrzt = 1; //����Ϊ׷��״̬
                liXH = Integer.parseInt((String) DAOFactory.getInstance().
                                        getDrzbDAO().getMaxXh(lsDrpch, conn)) +
                       1;
                int liDrbs = DAOFactory.getInstance().getDrzbDAO().getDrbs(
                        lsDrpch, conn);
                if (liDrbs + loRecords.size() - 1 > 200) {
                    throw new Exception("ÿ���걨�������ܳ���200��,׷��ʧ�ܣ�");
                }
            }

            //�ٲ��ӱ�
            for (int i = 0; i < loRecords.size(); i++) {
                Object loVo = loRecords.get(i);
                if (loVo.getClass().equals(Drzb.class)) {
                    try {
                        ((Drzb) loVo).setBz("����");
                        ((Drzb) loVo).setDrczr(loUserData.getYhmc());
                        ((Drzb) loVo).setDrsj(nowTime);
                        ((Drzb) loVo).setDrpch(lsDrpch);
                        ((Drzb) loVo).setXh(BigDecimal.valueOf(liXH++));
                        ((Drzb) loVo).setZtbs(Constants.DRZB_ZT_XINZENG);
                        DAOFactory.getInstance().getDrzbDAO().insertSql((Drzb)
                                loVo,
                                conn,
                                "01");
                        llSucceedRecords.add(loVo);

                    } catch (Exception ex) {
                        System.out.println("������˰�����������");
                        ex.printStackTrace();
                        llErrorRecords.add(loVo);

                    }

                }
            }
            //����ӱ������Ƿ�û�����������ɾ�������¼
            if ((llErrorRecords.size() == loRecords.size() - 1) && liDrzt == 0) {
                System.out.println("����ӱ������Ƿ�û�����������ɾ�������¼");
                DAOFactory.getInstance().getDrpcInfoDAO().delete((ArrayList)
                        llSucceedPcinfoRecords, conn);
            }
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ex.printStackTrace();
            ErrorLog.makeLog(vo.getUserData(),
                             "��˰xml�ļ��ϴ���UploadFileProcessor2����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        lmReturnData.put("Error", llErrorRecords);
        lmReturnData.put("Succeed", llSucceedRecords);
        return lmReturnData;
    }

    private Object doQuery(VOPackage vo) throws BaseException {
        String[] llDrpchs = null;
        try {
            conn = QSDBUtil.getConnection();
            String lsCondition = "";
            //��������Ȩ�޿���
            UserData ud = vo.getUserData();
            String datafilter = ZKAdapter.getInstance().getDataFilterString(
                    ud, "QSDB", "QS_JL_DRPCINFO");
            Debug.out("datafilter: " + datafilter);
            lsCondition += " where " + datafilter;
            lsCondition += " order by drsj desc";
            ArrayList loDrpcinfos = DAOFactory.getInstance().getDrpcInfoDAO().
                                    query(lsCondition, conn);
            llDrpchs = new String[loDrpcinfos.size()];
            for (int i = 0; i < loDrpcinfos.size(); i++) {
                llDrpchs[i] = ((Drpcinfo) loDrpcinfos.get(i)).getDrpch();
            }

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "��˰xml�ļ��ϴ���UploadFileProcessor2����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return llDrpchs;

    }


    private Object doGetPlh(VOPackage vo) throws BaseException {
        String plh = null;
        try {
            conn = QSDBUtil.getConnection();
            UserData user = vo.getUserData();
            plh = CommonUtil.getPlh(user.getXtsbm1(), conn, "P");

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(),
                             "��˰xml�ļ��ϴ���UploadFileProcessor2����ȡ�����ų���",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);

        } finally {
            QSDBUtil.freeConnection(conn);
        }
        return plh;

    }
}

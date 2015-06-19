/*
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������Ѷ�������������޹�˾����Ȩ����. </p>
 * <p>Company: ������Ѷ�������������޹�˾</p>
 */

package com.creationstar.bjtax.qsgl.BizLogic.processor;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.creationstar.bjtax.qsgl.BizLogic.dao.Base.DAOFactory;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Gjdq;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Wbhs;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Zjlx;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.ErrorLog;
import com.creationstar.bjtax.qsgl.util.QSDBUtil;
import com.creationstar.bjtax.qsgl.util.StackMsg2StringUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.ExceptionUtil;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.VOPackage;


/**
 * <p>Title: ������˰��������ϵͳ������˰����</p>
 * <p>Description: ִ�д�������ݵĳ�ʼ���ͻ�ȡ��Processor��</p>
 * @author ��˰�����飭���Բ�������
 * @version 1.0
 */
public class CodeTableProcessor implements Processor {

    /**
     * process method.
     *
     * @param vo VOPackage
     * @return java.lang.Object ҵ�����
     * @throws BaseException �����׳����쳣
     * @roseuid 3F4DA01C0007
     */
    public Object process(VOPackage vo) throws BaseException {
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case ActionType.QUERY:
            return doQuery(vo);
        case ActionType.GET_HL:
            return doGetHl(vo);

        default:
            throw new ApplicationException(
                    "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
    }

    /**
     * load all tables
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        HashMap map = (HashMap) vo.getData();
        HashMap reMap = new HashMap();
        if (map.isEmpty()) {
            Debug.out(
                    "CodeTableProcessor: doQuery() say -- the map is empty!!!");
            return null;
        }

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            Set s = map.keySet();
            Debug.out(
                    "CodeTableProcessor: doQuery() say -- the map is clear!!!");
            for (Iterator it = s.iterator(); it.hasNext(); ) {
                String tableName = (String) it.next();
                Debug.out(
                        "CodeTableProcessor: INIT   " + tableName);
                if (Constants.ZCWH.equals(tableName)) {
                    //ȡ����ά�������
                    reMap.put(Constants.ZCWH,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryZCWH(conn));
                } else if (Constants.BZ.equals(tableName)) {
                    //ȡ���ִ����

                    reMap.put(Constants.BZ,
                              DAOFactory.getInstance().
                              getCodeTablesDAO().queryBZ(conn));
                } else if (Constants.HL.equals(tableName)) {
                    //ȡ���ʴ����
                    //���ڻ�������䣬���Բ��ܷ���������
                } else if (Constants.FWLX.equals(tableName)) {
                    //ȡ�������ʹ����
                    reMap.put(Constants.FWLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFWLX(conn));
                } else if (Constants.ZJLX.equals(tableName)) {
                    //ȡ֤�����ʹ����
                    List l = DAOFactory.getInstance().getCodeTablesDAO().
                             queryZJLX(conn);
                    reMap.put(Constants.ZJLX,
                              l);
                    reMap.put(Constants.ZJLXMAP, getZjMap(l));
                } else if (Constants.JSFS.equals(tableName)) {
                    //ȡ��˰��ʽ�����
                    reMap.put(Constants.JSFS,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJSFS(conn));
                } else if (Constants.NSRLX.equals(tableName)) {
                    //ȡ��˰�����ʹ����
                    reMap.put(Constants.NSRLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryNSRLX(conn));
                } else if (Constants.TDFWYT.equals(tableName)) {
                    //ȡ���ط�����;�����
                    reMap.put(Constants.TDFWYT,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryTDFWYT(conn));
                } else if (Constants.BZ_TDFWYT_GR.equals(tableName)) {
                    //ȡ���ط�����;�����������
                    reMap.put(Constants.BZ_TDFWYT_GR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZTDFWYTGR(conn));
                } else if (Constants.BZ_TDFWYT_FGR.equals(tableName)) {
                    //ȡ���ط�����;��������Ǹ���
                    reMap.put(Constants.BZ_TDFWYT_FGR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZTDFWYTFGR(conn));
                } else if (Constants.BZQK.equals(tableName)) {
                    //ȡ������������
                    reMap.put(Constants.BZQK,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryBZQK(conn));
                } else if (Constants.TDFWQSZY.equals(tableName)) {
                    //ȡ���ط���Ȩ��ת����ʽ�����
                    reMap.put(Constants.TDFWQSZY,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryQSZYXS(conn));

                //zzb 20090820 add begin
                } else if (Constants.TDFWYTJM.equals(tableName)) {
                    //ȡ���ط�����;�����__����
                    reMap.put(Constants.TDFWYTJM,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMTDFWYTFGR(conn));
                //zzb  20090820 add end

                } else if (Constants.JMZC.equals(tableName)) {
                    //ȡ�������ߴ������list
                    reMap.put(Constants.JMZC,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZC(conn));
                } else if (Constants.JMZCMAP.equals(tableName)) {
                    //ȡ�������ߴ������map
                    reMap.put(Constants.JMZCMAP,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCMap(conn));
                } else if (Constants.JMZC_GR.equals(tableName)) {
                    //ȡ�������ߴ����������
                    reMap.put(Constants.JMZC_GR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCGR(conn));
                } else if (Constants.JMZC_FGR.equals(tableName)) {
                    //ȡ�������ߴ�������Ǹ���
                    reMap.put(Constants.JMZC_FGR,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryJMZCFGR(conn));
                } else if (Constants.GJ.equals(tableName)) {
                    //ȡ���������
                    List l = DAOFactory.getInstance().
                             getCodeTablesDAO().queryGJ(conn);
                    reMap.put(Constants.GJ,
                              l);
                    reMap.put(Constants.GJMAP, getGjMap(l));
                } else if (Constants.SWJGZZJG.equals(tableName)) {
                    //ȡ˰�������֯���������
                    reMap.put(Constants.SWJGZZJG,
                              DAOFactory.getInstance().
                              getCodeTablesDAO().querySWJGZZJG(conn));
                } else if (Constants.TDJC.equals(tableName)) {
                    //ȡ�����÷������
                    reMap.put(Constants.TDJC,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryTDJC(conn));

                } else if (Constants.SZQY.equals(tableName)) {
                    //ȡ�����÷������
                    reMap.put(Constants.SZQY,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              querySZQY(conn));

                }else if (Constants.FPZL.equals(tableName)) {
                    //ȡ��Ʊ��������
                    reMap.put(Constants.FPZL,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFPZL(conn));
                }else if (Constants.KPLX.equals(tableName)) {
                    //ȡ��Ʊ��������
                    reMap.put(Constants.KPLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryKplx(conn));
                }else if (Constants.FWCQZBZZFLX.equals(tableName)) {
                    //���ݲ�Ȩ֤��עס����
                    reMap.put(Constants.FWCQZBZZFLX,
                              DAOFactory.getInstance().getCodeTablesDAO().
                              queryFwcqzbzzflx(conn));
                }else if(Constants.FWXZ.equals(tableName)){
                    //��������
                    reMap.put(Constants.FWXZ,
                              DAOFactory.getInstance().getCodeTablesDAO().queryFwxz(conn));               	
                	
                }else if (Constants.FWSZQY.equals(tableName)){
                    //������������
                    reMap.put(Constants.FWSZQY,
                              DAOFactory.getInstance().getCodeTablesDAO().queryFWSZQY(conn));                  	
                	
                }else if (Constants.QS_JMXZ.equals(tableName)){
                    //��˰��������
                    reMap.put(Constants.QS_JMXZ,
                              DAOFactory.getInstance().getCodeTablesDAO().queryJmxzdmList(conn));                  	
                	
                }else if (Constants.QSZYXSMX.equals(tableName)){
                    //����Ȩ��ת����ϸ
                    reMap.put(Constants.QSZYXSMX,
                              DAOFactory.getInstance().getCodeTablesDAO().queryQSZYXSMX(conn));                  	
                	
                }
                

            }
            Debug.out("sucessfully put arrylist in map !!!");

        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CodeTableProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

        return reMap;
    }

    private Map getZjMap(List l) {
        Map m = new HashMap();
        for (int i = 0; i < l.size(); i++) {
            Zjlx vo = (Zjlx) l.get(i);
            m.put(vo.getZjlxdm(), vo);
        }
        return m;
    }

    private Map getGjMap(List l) {
        Map m = new HashMap();
        for (int i = 0; i < l.size(); i++) {
            Gjdq vo = (Gjdq) l.get(i);
            m.put(vo.getGjdqdm(), vo);
        }
        return m;
    }

    /**
     * ��ȡָ�����ֵĻ���
     * @param vo VOPackage
     * @return Object
     * @throws BaseException
     */
    private Object doGetHl(VOPackage vo) throws BaseException {
        Wbhs wbhs = (Wbhs) vo.getData();
        if (wbhs == null) {
            throw new ApplicationException("��ȡ���ʲ�������Ϊ�գ�");
        }

        Connection conn = null;
        try {
            conn = QSDBUtil.getConnection();
            return DAOFactory.getInstance().getWbhsDAO().get(wbhs, conn);
        } catch (Exception ex) {
            // ����ʧ����Ϣ:����̨ �� ��־
            Debug.printException(ex);
            ErrorLog.makeLog(vo.getUserData(), "��˰�걨���գ�CodeTableProcessor����������",
                             new StackMsg2StringUtil(ex,
                    Constants.STACK_MSG_CAP).getStackMsg(), "ʧ��");

            throw ExceptionUtil.getBaseException(ex);
        } finally {
            QSDBUtil.freeConnection(conn);
        }

    }
}

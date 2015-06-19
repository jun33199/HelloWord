package com.creationstar.bjtax.qsgl.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.QsglPropertiesUtil;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.util.VOPackage;


public class StartupServlet extends HttpServlet {
    /**
     * ��ʼ��ȫ�ֱ���.
     *
     * @throws  ServletException    �׳���servlet�쳣
     */
    public void init() throws ServletException {
        //��processor-map.properties���뵽 ServletContext �У�
        //�Ա� ����ҵ��� Action ���ã���λprocessor֮�ã�
        Properties prop = QsglPropertiesUtil.getProperties(Constants.
                PROCESSOR_MAP_FILE_NAME);
        this.getServletContext().setAttribute(Constants.PROCESSOR_MAP_FILE_NAME,
                                              prop);

        Debug.out(" ���²�������˰�����WEB MODEL ");

        /* ��ʼ�������application�� */
        try {
            //����VoPackage
            VOPackage vo = new VOPackage();
            //������Ҫ��Ŵ�����Vo��ArrayList
            HashMap codesMap = new HashMap();
            ArrayList zcwhList = new ArrayList();
            ArrayList bzList = new ArrayList();
            ArrayList fwlxList = new ArrayList();
            ArrayList jsfsList = new ArrayList();
            ArrayList nsrlxList = new ArrayList();
            ArrayList zjlxList = new ArrayList();
            ArrayList fwytList = new ArrayList();
            ArrayList bzgrfwytList = new ArrayList();
            ArrayList bzfgrfwytList = new ArrayList();
            ArrayList qszyList = new ArrayList();
            ArrayList jmzcList = new ArrayList();
            ArrayList gjList = new ArrayList();
            ArrayList swjgzzjgList = new ArrayList();

            ArrayList tdfwytjmList = new ArrayList();//zzb add  090820 �������ͼ������
            
            //��Ʊ������ Begin add tutu 2013-05-07
            ArrayList fpzlList = new ArrayList();//��Ʊ�������List
            ArrayList kplxList = new ArrayList();//��Ʊ���ʹ���List
            //��Ʊ������ End
            
            //���ݲ�Ȩ֤��עס�����ʹ���� Begin add tutu 2013-08-14
            ArrayList fwcqzbzzflxList = new ArrayList();
            
            ArrayList fwxzList = new ArrayList();//�Ʋ���  add 2013-10-13 ��������������
            ArrayList fwszqyList = new ArrayList();//�Ʋ���  add 2013-10-13 ������������
            ArrayList qszyxsmxList = new ArrayList();//����Ȩ��ת����ϸ,added by zhangj
            
            //���ݲ�Ȩ֤��עס�����ʹ���� End

            HashMap BzMap = new HashMap(); //������������
            HashMap jmzcMap = new HashMap(); //�������ߴ����map
            ArrayList jmzcGrList = new ArrayList(); //�������ߴ����������
            ArrayList jmzcFgrList = new ArrayList(); //�������ߴ����map�����Ǹ���
            ArrayList tdjcList = new ArrayList(); //���ؼ��δ����
            ArrayList szqyList = new ArrayList(); //���ؼ��δ����
            codesMap.put(Constants.ZCWH, zcwhList);
            codesMap.put(Constants.BZ, bzList);
            codesMap.put(Constants.FWLX, fwlxList);
            codesMap.put(Constants.JSFS, jsfsList);
            codesMap.put(Constants.NSRLX, nsrlxList);
            codesMap.put(Constants.BZQK, BzMap); //������������
            codesMap.put(Constants.ZJLX, zjlxList);
            codesMap.put(Constants.TDFWYT, fwytList);
            codesMap.put(Constants.BZ_TDFWYT_GR, bzgrfwytList);
            codesMap.put(Constants.BZ_TDFWYT_FGR, bzfgrfwytList);
            codesMap.put(Constants.TDFWQSZY, qszyList);
            codesMap.put(Constants.JMZC, jmzcList);
            codesMap.put(Constants.JMZCMAP, jmzcMap);
            codesMap.put(Constants.JMZC_GR, jmzcGrList);
            codesMap.put(Constants.JMZC_FGR, jmzcFgrList);
            codesMap.put(Constants.GJ, gjList);
            codesMap.put(Constants.SWJGZZJG, swjgzzjgList);
            codesMap.put(Constants.TDJC, tdjcList);
            codesMap.put(Constants.SZQY, szqyList);

            codesMap.put(Constants.TDFWYTJM, tdfwytjmList);//zzb add  090820 �������ͼ������
            
            //��Ʊ������ Begin add tutu 2013-05-07
            codesMap.put(Constants.FPZL, fpzlList);//��Ʊ�������List
            codesMap.put(Constants.KPLX, kplxList);//��Ʊ���ʹ���List
            //��Ʊ������ End
            
            //���ݲ�Ȩ֤��עס�����ʹ���� Begin add tutu 2013-08-14
            codesMap.put(Constants.FWCQZBZZFLX, fwcqzbzzflxList);//���ݲ�Ȩ֤��עס�����ʹ���List
            
            codesMap.put(Constants.FWXZ, fwxzList);//�Ʋ���  add 2013-10-13 ��������������
            codesMap.put(Constants.FWSZQY, fwszqyList);//�Ʋ���  add 2013-10-13 ������������
			codesMap.put(Constants.QS_JMXZ, new ArrayList());//��˰��������            
			codesMap.put(Constants.QSZYXSMX, qszyxsmxList);//����Ȩ��ת����ϸ,added by zhangj
			
            //���ݲ�Ȩ֤��עס�����ʹ���� End

            //�������̨�����Data�����뵽VoPackage.setData()��
            vo.setData(codesMap);
            //VoPackage.setUserData()����null
            vo.setUserData(null);
            vo.setAction(ActionType.QUERY);

            //VoPackage.setProcessor()�У�
            //�������CodeTableProcessor���ַ���Constants.Load_CodeTables;
            vo.setProcessor(prop.getProperty(Constants.Load_CodeTables));
            codesMap = (HashMap) QsglProxy.getInstance().process(vo);

            this.getServletContext().setAttribute(Constants.CodeTables,
                                                  codesMap);
        } catch (Exception ex) {

        }
    }

    /**
     * doGet   ����get����.
     *
     * @param request   the HttpServletRequest
     * @param response  the HttpServletResponse
     * @throws ServletException �׳���servlet�쳣
     * @throws IOException      �׳���I/O�쳣
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        doPost(request, response);
    }

    /**
     * doPost   ����post����.
     *
     * @param request   the HttpServletRequest
     * @param response  the HttpServletResponse
     * @throws ServletException �׳���servlet�쳣
     * @throws IOException      �׳���I/O�쳣
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        Debug.out("int StartupServlet Service Reload ...");

        try {
            /* ��ʼ�������application�� */

        } catch (Exception ex) {
            Debug.printException(ex);
        }
    }
}

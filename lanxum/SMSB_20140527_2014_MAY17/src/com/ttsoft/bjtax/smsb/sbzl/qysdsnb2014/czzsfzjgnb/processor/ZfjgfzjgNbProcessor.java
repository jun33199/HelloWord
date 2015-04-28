package com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.processor;


/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2012</p>
 *
 *
 * @author wangcy 
 * @version 1.0
 */
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.exception.BaseException;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.sql.Connection;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.util.QysdsNewUtil;
import com.syax.creports.bo.qysds.QysdsReportsDeclare;
import com.syax.creports.bo.qysds.QysdsReportsTableDeclare;
import com.syax.creports.persistent.access.IAppAccess;
import com.syax.creports.persistent.AppAccessFactory;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.syax.creports.bo.qysds.QysdsReportsItemDeclare;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.ttsoft.framework.exception.ExceptionUtil;
import com.syax.creports.Constants;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.ttsoft.bjtax.sfgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.model.QysdsSet;
import com.ttsoft.bjtax.sfgl.common.model.Zsfs;
import java.text.DecimalFormat;
import com.syax.creports.util.Arith;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.util.QysdsCzzsNb2014Util;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web.CzzsfzjgNbForm;
import com.ttsoft.bjtax.smsb.sbzl.qysdsnb2014.czzsfzjgnb.web.ZfjgfzjgNbForm;


public class ZfjgfzjgNbProcessor implements Processor
{
    // ��ҵ����˰˰��
	private static final String QYSDS_SL = "0.23";
    private QysdsCzzsNb2014Util util = new QysdsCzzsNb2014Util();

    public ZfjgfzjgNbProcessor()
    {
    }

    /**
     * ʵ��Processor�ӿ�
     *
     * @param vo
     *            ҵ�����
     * @return Object VOPackage������
     * @throws BaseException
     *             ҵ���쳣 1 ���������Ĳ������Ͳ���ʱ�׳� 2 �����õ�ҵ�񷽷��׳�ҵ���쳣ʱ���ϴ����׳�
     *             �����쳣�׳���EJB��process��������
     */

    public Object process(VOPackage vo) throws BaseException
    {

        Object result = null;
        if (vo == null) {
            throw new NullPointerException();
        }

        switch (vo.getAction()) {
        case CodeConstant.SMSB_SHOWACTION:
            result = doShow(vo);
            break;
        case CodeConstant.SMSB_QUERYACTION:
            result = doQuery(vo);
            break;
        case CodeConstant.SMSB_SAVEACTION:
            result = doSave(vo);
            break;
        case CodeConstant.SMSB_DELETEACTION:
			result = doDelete(vo);
			break;

        default:
            throw new ApplicationException("�û�ִ����ϵͳ��֧�ֵķ�������.");
        }

        return result;
    }

    
    /**
     * doShow ��һ��¼�����ݴ����������ƴװ�ӱ���������
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     *
     */
    private Object doShow(VOPackage vo) throws BaseException
    {
        // �õ�Action���ݹ���ZfjgfzjgNbForm����
    	Map dataMap=(Map) vo.getData();
    	
    	CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) dataMap.get("CzzsfzjgNbForm");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) dataMap.get("ZfjgfzjgNbForm");
        Connection conn = null;
        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // �걨�ں�Ϊ1
            String qh = "1";
            
            System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.getSbrq()��" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "��qh��" + qh);
           

            // ��ҳ����ȡ��˰�������ں����
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // ���ü���
            zfjgForm.setQh(qh);
            // �������
            zfjgForm.setSknd(nd);
            // ����form��������������
            zfjgForm = (ZfjgfzjgNbForm) QysdsCzzsNb2014Util.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            //��������Ϊ��֧����
            zfjgForm.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG);
            // ˰�Ѻ˶���Ϣ
			this.getHdxx(zfjgForm);
            /* ���շ�ʽ */
            String zsfs = zfjgForm.getZsfs();


            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException("����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
            }
            //��ѯ�������ձ�������������
            this.getFtseFormCzzs(conn, zfjgForm, czzsfzjgNbForm);
            zfjgForm.setJsjdm(zfjgForm.getJsjdm());
            zfjgForm.setSbrq(czzsfzjgNbForm.getSbrq());
            zfjgForm.setSkssksrq(czzsfzjgNbForm.getSkssksrq());
            zfjgForm.setSkssjsrq(czzsfzjgNbForm.getSkssjsrq());
           // zfjgForm.setSkssjsrq(czzsfzjgNbForm.getZjgmc());

            zfjgForm.setQysdsNbList(this.CzzsForm2Page(zfjgForm));
            zfjgForm.setMaxIndex(1);
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ��ѯ�ɹ�����czqysdsjbForm
        return zfjgForm;
    }
    
    
    /**
     * doQuery ���ڷ���ҳ����Ҫ��ѯ���꾡��Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]��
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     *
     */
    private Object doQuery(VOPackage vo) throws BaseException
    {
        // �õ�Action���ݹ���ZfjgfzjgNbForm����
    	Map dataMap=(Map) vo.getData();
    	CzzsfzjgNbForm czzsfzjgNbForm = (CzzsfzjgNbForm) dataMap.get("CzzsfzjgNbForm");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) dataMap.get("ZfjgfzjgNbForm");
        Connection conn = null;
        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // �걨�ں�Ϊ1
            String qh = "1";
            
            System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.getSbrq()��" + zfjgForm.getSbrq());
            System.out.println(zfjgForm.getJsjdm() + "��qh��" + qh);
           

            // ��ҳ����ȡ��˰�������ں����
            String nd = zfjgForm.getSkssksrq().substring(0, 4);

            // ���ü���
            zfjgForm.setQh(qh);
            // �������
            zfjgForm.setSknd(nd);
            System.out.println(zfjgForm.getJsjdm() + "��zfjgForm.setSknd��" + zfjgForm.getSknd());

            // ����form��������������
            zfjgForm = (ZfjgfzjgNbForm) QysdsCzzsNb2014Util.queryDjxxByInterfaceDJ(conn, zfjgForm, vo.getUserData());
            
            
            System.out.println(zfjgForm.getJsjdm() + "����˰�����ƣ�" + zfjgForm.getNsrmc());
            System.out.println(zfjgForm.getJsjdm() + "����˰��ʶ��ţ�" + zfjgForm.getNsrsbh());
            
            // ˰�Ѻ˶���Ϣ
			this.getHdxx(zfjgForm);
            /* ���շ�ʽ */
            String zsfs = zfjgForm.getZsfs();

            System.out.println(zfjgForm.getJsjdm() + "�����շ�ʽ���룺" + zsfs);

            if (zsfs == null || (zsfs != null && zsfs.equals(""))) {
                throw new ApplicationException("û�в�ѯ������ҵ�����շ�ʽ�϶���Ϣ�����϶����ٽ����걨¼�룡");
            }
            if (!CodeConstant.ZSFSDM_CZZS.equals(zsfs)) {
                throw new ApplicationException("����ҵ���϶�Ϊ�˶����ջ��������ڴ�¼�룬��¼��˶����ռ����걨��");
            }

            //��ȡ�������ջ�����˰���������ж��Ƿ�Ϊ����д�����û����ܻ�������֧������д��
            int result = this.checkCzzsNsff(conn, zfjgForm);
            switch(result)
            {
                case CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA:
                    throw new ApplicationException("����ҵ��δ���ҵ����˰��֧���������˰�걨�������ڴ�¼�룬����¼����ҵ����˰��֧���������˰�걨��");
                    //break;
                case CodeConstant.CHECK_HZNSFF_TYPE_DLNS:
                    throw new ApplicationException(CodeConstant.CHECK_HZNSFF_MESSAGE_DLNS);
                    //break;
            }
            
            System.out.println("-------zfjgForm.getJglx()-------" + zfjgForm.getJglx());

            //��ѯ�������ձ�������������
            this.getCzzsFtse(conn, zfjgForm);

            //����ȡ��������ݽ��и���
            this.getFtseFormCzzs(conn,zfjgForm,czzsfzjgNbForm);
            
            
            // ����QysdsReportsDeclare����
            QysdsReportsDeclare report = new QysdsReportsDeclare();
            // ��form�еĻ�����Ϣת��QysdsReportsDeclare report ��
            QysdsNewUtil.setQysdsReport(report, zfjgForm);

            System.out.println("--------------" + report.getSbrq());
            System.out.println("--------------" + report.getSkssksrq());
            System.out.println("--------------" + report.getSkssjsrq());

            // ����QysdsReportsTableDeclare�Ļ�����Ϣ
            QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
            table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            table.setTableName(CodeConstant.TABLE_NAME_ZFJGSDSNB_2012);
            table.setTbblx(zfjgForm.getBbqlx()); //������code = "1"��
            // ��QysdsReportsTableDeclare�Ļ�����Ϣ����QysdsReportsDeclare
            report.getTableContentList().put(table.getTableId(), table);

            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ���ò�ѯ�������в�ѯ
            iApp.querySingleTable(report);
            // ��ȡ��ѯ���ľ�������
            table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);

            //���ݲ�ѯ���Ĺ������ݹ�����Ӧ��QysdsReportsItemDeclare
            QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
            
            if (table.getCellContentList().size() > 0) {
            	
            	System.out.println("---------------������--------------------");
            	zfjgForm.setJsjdm(zfjgForm.getJsjdm());
                zfjgForm.setSbrq(TinyTools.Date2String(report.getSbrq(), "yyyyMMdd"));
                zfjgForm.setSkssksrq(TinyTools.Date2String(report.getSkssksrq(), "yyyyMMdd"));
                zfjgForm.setSkssjsrq(TinyTools.Date2String(report.getSkssjsrq(), "yyyyMMdd"));
            }else{
              if(zfjgForm.getJglx().equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_ZJG)){
            	zfjgForm.setZjgmc(zfjgForm.getNsrmc());
              }else{
              	zfjgForm.setNsrsbh("");      
              }
          		zfjgForm.setSrehj("0.00");
          		zfjgForm.setGzehj("0.00");
          		zfjgForm.setZcehj("0.00");
          		zfjgForm.setFpblhj("0");
          		zfjgForm.setFpsehj("0.00");
            	System.out.println("---------------������--------------------");
            }

            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report, zfjgForm));
        }
        catch (Exception e) {
            // �׳��쳣
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }
        finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ��ѯ�ɹ�����czqysdsjbForm
        return zfjgForm;
    }
    
    
    /**
     * ȡ��һ�ʺ���ҵ��˰����,����ҳ��У��
     *
     * @param form
     * @throws BaseException
     */
    private void getHdxx(ZfjgfzjgNbForm form) throws BaseException
    {
        String qyzssllx = "3"; // ȱʡΪ�����걨

        // ��ҵ��˰��˰�� �������ҵ��˰��˰������
        String qyzssl = QYSDS_SL;

        // ��������˰��
        String dezsse = "0.00";

        // ��ǰʱ��
        // ���걨ҳ��ȡ���걨���ں�˰����������
        Timestamp sbrq = QysdsCzzsNb2014Util.getNxetTimestamp(form.getSkssjsrq());

        // Map getsbjd = this.quarterSkssrq(sbrq);
        Timestamp skssksrq = QysdsCzzsNb2014Util.getTimestamp(form.getSkssksrq());
        Timestamp skssjsrq = QysdsCzzsNb2014Util.getTimestamp(form.getSkssjsrq());

        System.out.println(form.getJsjdm() + "sbrq��" + sbrq);
        System.out.println(form.getJsjdm() + "skssksrq��" + skssksrq);
        System.out.println(form.getJsjdm() + "skssjsrq��" + skssjsrq);
        System.out.println(form.getJsjdm() + "qh��" + form.getQh());

        ServiceProxy proxy = new ServiceProxy();

        String bblx = form.getBbqlx();
        String jsjdm = form.getJsjdm();

        // ��ѯ˰�ѽӿ�
        QysdsSet qysdsSet = null;

        // �����ʸ��ʶ
        boolean jm_type = false;
        form.setJmzg("0"); // �м����ʸ�

        try {
            if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_YEAR))
            {
                //�걨
                qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
            }
            else if (bblx.equals(Constants.CREPORTS_IBBQLX_TYPE_QUARTOR))
            {
                //����
                if (form.getQh() == null || (form.getQh() != null && form.getQh().trim().equals("")))
                {
                    /* �ںŲ���Ϊ�գ����Ϊ���׳��쳣 */
                    throw new ApplicationException("ϵͳ�����쳣���ں�Ϊ�գ�����ϵͳ����Ա��ϵ��");
                }
                System.out.println("form.getQh()::" + form.getQh());

                if ("4".equals(form.getQh()))
                {
                    /* ���Ϊ���ļ��ȣ���ȡ��ҵ����˰�϶���Ϣʱ���걨����ȡ */
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_NB);
                }
                else
                {
                    qysdsSet = proxy.getQysdsInfo(jsjdm, sbrq, skssksrq, skssjsrq, CodeConstant.SFGL_QYSDS_BBFS_JB);
                    //����Zsfs
                    Zsfs zsfs = util.getZsfsInfo(jsjdm, skssjsrq);
                    qysdsSet.setZsfs(zsfs);
                }
            }
        }
        catch (com.ttsoft.framework.exception.BaseException e) {
            e.printStackTrace();
            throw ExceptionUtil.getBaseException(e);
        }

        // 1����ѯ��ҵ���շ�ʽ
        Zsfs zsfs = qysdsSet.getZsfs();
        if (zsfs != null) {
            form.setZsfs(zsfs.getZsfsdm() == null ? CodeConstant.ZSFSDM_CZZS : zsfs.getZsfsdm());
        }
        else
        {
            // 20070208���շ�ʽ���ȡ��Ϊ������Ϊ�ǲ���������ҵ�ġ�
            form.setZsfs(CodeConstant.ZSFSDM_CZZS);
        }

        /* ���¼�����ҵ�϶����� */
        Date gxqyrdrq = qysdsSet.getGxjsqy();

        // ��ֵ
        form.setCyl("0");
        form.setXzqy("0");
        form.setDezsse("0.00");
        form.setYbjmsl("0.00");

        if (zsfs != null)
        {
            String zsfsdm = zsfs.getZsfsdm();
            if (zsfsdm.equals(CodeConstant.ZSFSDM_CYLZS))
            {
                if (gxqyrdrq == null)
                {
                    // ����������
                    qyzssllx = "2";
                }
                else
                {
                    // ���¼����ʹ�������ҵ
                    qyzssllx = "5";
                    qyzssl = "0.15";
                    form.setJmzg("1"); // �м����ʸ�
                }
                form.setCyl(zsfs.getCyl());
            }
            else if (zsfsdm.equals(CodeConstant.ZSFSDM_DEZS))
            {
                // ��������
                qyzssllx = "4";
                // ��ʱ���ֶδ�����ҵ�˶�˰��
                // ynsdse = zsfs.getDe();
                dezsse = zsfs.getDe();
                form.setDezsse(dezsse);
            }
        }

        // 2����ѯ�Ƿ��Ǹ��¼�����ҵ
        if (gxqyrdrq != null) {
            if (zsfs != null
                && zsfs.getZsfsdm().equals(CodeConstant.ZSFSDM_CYLZS)) {
                // ���¼����ʹ�������ҵ
                qyzssllx = "5";
            }
            else {
                // ����Ϊ���¼�����ҵ
                qyzssllx = "1";
            }
            qyzssl = "0.15";
            form.setJmzg("1"); // �м����ʸ�

        }
        else if (form.getSsjjlx().equals(CodeConstant.JITIQIYE_CODE)) {
            // �ж��Ƿ�������ҵ����1��Ϊ������ҵ����0��Ϊ����������ҵ
            if (qysdsSet.isXzqy()) {
                form.setXzqy("1");
                form.setJmzg("1"); // �м����ʸ�
            }
        }

        if (!(form.getXzqy() != null && form.getXzqy().equals("1"))
            && qysdsSet.getYbjmsl() != null) {
            // ��������ҵ�ļ������
            form.setJmzg("1"); // �м����ʸ�
            DecimalFormat ft = new DecimalFormat("0.00");
            form.setYbjmsl(ft.format(qysdsSet.getYbjmsl()));
        }
        form.setQyzslx(qyzssllx);
        form.setSysl(qyzssl);

        /* �˶���Ϣ��� */
        System.out.println("-------------�˶���Ϣ--------------");
        System.out.println("��ҵ����˰������-" + qyzssllx);
        System.out.println("�����ʸ�-" + form.getJmzg());
        System.out.println("һ�����˰��-" + form.getYbjmsl());
        System.out.println("���շ�ʽ-" + form.getZsfs());
        System.out.println("������-" + form.getCyl());
        System.out.println("����-" + form.getDezsse());
        System.out.println("����˰��-" + form.getSysl());
        System.out.println("-------------�˶���Ϣ--------------");
    }

    /**
     * �жϵ�ǰ��˰�˲��������걨��ʽ
     * @param conn Connection
     * @param form ZfjgfzjgNbForm
     * @return int
     * @throws BaseException
     */
    private int checkCzzsNsff(Connection conn, ZfjgfzjgNbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        //������˰-�ܻ���
        int resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_ZJG;

        HashMap result = new HashMap();
        try
        {
            sql.append("select hc, yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZZSSDSNB_2012).append("' ");
            sql.append("and to_number(hc) < 3 order by to_number(hc) ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            while(rs.next())
            {
                result.put(rs.getString("hc"), rs.getString("yz"));
            }
            if(result.size() == 0)
            {
                //û������
                resultType = CodeConstant.CHECK_HZNSFF_TYPE_NO_DATA;
            }
            else
            {
                String hzff = (String) result.get("1");
                if(hzff.equals(CodeConstant.HZNSFF_QYSDSNB2012_CZZSSDS_HZNS))
                {
                    String hzfs = (String) result.get("2") == null ? "" : (String) result.get("2");
                    if(hzfs.equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG))
                    {
                        //������˰-��֧����
                        resultType = CodeConstant.CHECK_HZNSFF_TYPE_HZNS_FZJG;
                        form.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG);
                    }else
                    {
                    	form.setJglx(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_ZJG);
                    }
                }
                else if(hzff.equals(CodeConstant.HZNSFF_QYSDSNB2012_CZZSSDS_DLNS))
                {
                    //������˰
                    resultType = CodeConstant.CHECK_HZNSFF_TYPE_DLNS;
                }
            }
            
            System.out.println("******************************"+resultType);
            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ���˰��������");
        }
        return resultType;
    }
   
    /**
     * ��ѯ�������ձ�������������
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getCzzsFtse(Connection conn, ZfjgfzjgNbForm form) throws BaseException
    {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        StringBuffer sql = new StringBuffer();
        try
        {
            sql.append("select hc,yz from sbdb.sb_jl_qysdssbb_zb_jd where ");
            sql.append("nsrjsjdm = '").append(form.getJsjdm()).append("' ");
            sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
            sql.append("and qh = '").append(form.getQh()).append("' ");
            sql.append("and sknd = '").append(form.getSknd()).append("' ");
            sql.append("and sbdm = '").append(CodeConstant.TABLE_ID_CZZSSDSNB_2012).append("' ");
            sql.append("and hc in('12','24','25','26','29','30') ");
            System.out.println("sql:\n" + sql.toString());

            pstmt = conn.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            String ynsdse="0.00";
            String zjgftse="0.00";
            String zjgfpse="0.00";
            String fzjgftse = "0.00";
            String fzjgfpbl = "0.00";
            String fzjgfpse = "0.00";
            while(rs.next())
            {
            	if(rs.getString("hc").equals("12")){
            		ynsdse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("24")){
            		zjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("25")){
            		zjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("26")){
            		fzjgftse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("29")){
            		fzjgfpbl=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            	if(rs.getString("hc").equals("30")){
            		fzjgfpse=rs.getString("yz") == null ? "0.00" : rs.getString("yz");
            	}
            }
            System.out.println("query ynsdse = " + ynsdse);
            System.out.println("query zjgftse = " + zjgftse);
            System.out.println("query zjgfpse = " + zjgfpse);
            System.out.println("query fzjgftse = " + fzjgftse);
            System.out.println("query fzjgfpbl = " + fzjgfpbl);
            System.out.println("query fzjgfpse = " + fzjgfpse);
            
           if(form.getJglx().equals(CodeConstant.HZNSFS_QYSDSNB2012_CZZSSDS_FZJG))
           {
        	   form.setFzjgftse(fzjgftse);
        	   form.setFzjgfpbl(fzjgfpbl);
        	   form.setFzjgfpse(fzjgfpse);
        	   form.setFzjgnsrsbh(form.getNsrsbh());
        	   form.setFzjgnsrmc(form.getNsrmc());
           }else{
        	   form.setYnsdse(ynsdse);
        	   form.setZjgftse(zjgftse);
        	   form.setZjgfpse(zjgfpse);
        	   form.setFzjgftse(fzjgftse);        	   
           }

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            throw new ApplicationException("��ѯ�������ձ��̯˰�����");
        }
    }
    
    /**
     * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
     *
     * @param QysdsReportsTableDeclare
     * @return ҳ������ݵ�List����
     */
    private List translate2Page(QysdsReportsTableDeclare table,ZfjgfzjgNbForm zfjgForm)
    {
        // ����List�����������ҳ�������
        ArrayList pagelist = new ArrayList();
        // �Բ���*�ŵ��е����ݽ��з���
        Iterator it = table.getCellContentList().keySet().iterator();
        Iterator it_temp = table.getCellContentList().keySet().iterator();
        //���ݿ��Ѿ�����ķ�֧�����������
		String fzjgFpbl_old="0.00";
		//��������д�ķ�֧�����������
		String fzjgFpbl_new=((String) zfjgForm.getFzjgfpbl()) == null ? "0.00" : (String) zfjgForm.getFzjgfpbl();
		
		//���ݿ��Ѿ�����ķ�֧�����������
		String fzjgFpsdse_old="0.00";
		//��������д�ķ�֧�����������
		String fzjgFpsdse_new=((String) zfjgForm.getFzjgfpse()) == null ? "0.00" : (String) zfjgForm.getFzjgfpse();
        //����ȡ�������е����� ��������ĵ�whileѭ���������йؿ��ܳ���
        while (it_temp.hasNext()) {
        	String key = (String) it_temp.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //���÷������Ϊ�ٷ�����ʽ
            if(key.indexOf(".") > 0)
            {
                String head = key.substring(0, key.indexOf("."));
                if(Integer.parseInt(head) == 17)
                {
                    String value = item.getItemValue();
                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
                }
            }
        	if(item.getItemID().equals("17.1")){
    			fzjgFpbl_old=item.getItemValue();
    		}
    		if(item.getItemID().equals("18.1")){
    			fzjgFpsdse_old=item.getItemValue();
    		}
        }
        
        while (it.hasNext()) {
            String key = (String) it.next();
            QysdsReportsItemDeclare item = (QysdsReportsItemDeclare) table.getCellContentList().get(key);
            //���÷������Ϊ�ٷ�����ʽ
//            if(key.indexOf(".") > 0)
//            {
//                String head = key.substring(0, key.indexOf("."));
//                if(Integer.parseInt(head) == 17)
//                {
//                    String value = item.getItemValue();
//                    item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(value) * 100, 2)) + "%");
//                }
//            }
            HashMap map = new HashMap();
           
            if(item.getItemID().equals("1")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }
            if(item.getItemID().equals("2")){
            	if("".equals(item.getItemValue()) || (item.getItemValue() == null)){
            		item.setItemValue("");
            	}
            }
    		//���¸�ֵ  Ӧ�ͻ�Ҫ���ڽ������¸�ֵ
//    		if(item.getItemID().equals("6")){
//    			item.setItemValue(zfjgForm.getFzjgftse());
//    		}
//    		if(item.getItemID().equals("17.1")){
//    			item.setItemValue(fzjgFpbl_new+"%");
//    		}
//    		if(item.getItemID().equals("18.1")){
//    			item.setItemValue(fzjgFpsdse_new);
//    		}
//    		
//    		//���¼����֧������������ͷ�֧��������˰��
//
//    		DecimalFormat deFormat = new DecimalFormat("#0.00");
//    		
//    		if(item.getItemID().equals("10")){
//	    		BigDecimal fpblhj_old=new BigDecimal(item.getItemValue().replaceAll("%", ""));
//	    		BigDecimal fpblhj_new=fpblhj_old.add(new BigDecimal(fzjgFpbl_new)).subtract(new BigDecimal(fzjgFpbl_old.replaceAll("%",""))) ;
//	    		item.setItemValue(deFormat.format(fpblhj_new)+"%");
//    		}
//    		if(item.getItemID().equals("11")){
//	    		BigDecimal fpsehj_old=new BigDecimal(item.getItemValue());       
//	            BigDecimal fpsehj_new=fpsehj_old.add(new BigDecimal(fzjgFpsdse_new)).subtract(new BigDecimal(fzjgFpsdse_old)) ;
//	            item.setItemValue(deFormat.format(fpsehj_new));
//            }
            
            System.out.println("item.getItemID() :: "+item.getItemID());
            System.out.println("item.getItemValue() :: "+item.getItemValue());
            System.out.println("");
            
            map.put("hc", item.getItemID());
            map.put("value", item.getItemValue());
            pagelist.add(map);
        }
        // ����List����
        return pagelist;
    }
    
    /**
     * ��ѯ��ϸ�������index
     *    �������JSJDM�����ID��ȡ��Ӧ��ϸ���ݵ����index
     * @param con Connection
     * @param report QysdsReportsDeclare
     * @return int
     * @throws BaseException
     */
    private int getMxDateMaxIndex(Connection con, QysdsReportsDeclare report, ZfjgfzjgNbForm form) throws BaseException
    {
        int maxIndex = 0;
        //��ȡQysdsReportsTableDeclare����
        QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        //sql���
        StringBuffer sql = new StringBuffer();
        sql.append("select max(to_number(zhs)) from sbdb.sb_jl_qysdssbb_cb_jd ");
        sql.append("where nsrjsjdm = '").append(report.getNsrjsjdm()).append("' ");
        sql.append("and sbdm = '").append(table.getTableId()).append("' ");
        sql.append("and bbqlx = '").append(form.getBbqlx()).append("' ");
        sql.append("and qh = '").append(form.getQh()).append("' ");
        sql.append("and sknd = '").append(form.getSknd()).append("' ");

        System.out.println("sql:\n" + sql.toString());
        
        try
        {
            pstmt = con.prepareStatement(sql.toString());
            rs = pstmt.executeQuery(sql.toString());

            rs.next();
            maxIndex = rs.getInt(1);

            //�ر����ݿ����
            rs.close();
            pstmt.close();
        }
        catch(Exception ex)
        {
            throw new ApplicationException("��ѯ��ϸ�������indexʧ�ܣ�");
        }

        return maxIndex;
    }
    
    
    /**
     * doSave ���ڴ洢ҳ���ύ���꾡������Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private Object doSave(VOPackage vo) throws BaseException {
        // �õ�Action���ݹ���ZfjgqysdsjbForm����
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) vo.getData();

        Connection conn = null;

        // ����ں�Ϊ1
        String qh = "1";

        // ��ҳ����ȡ��˰�������ں����
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // ���ü���
        zfjgForm.setQh(qh);
        // �������
        zfjgForm.setSknd(nd);
        try
        {
            // �������ݿ�����
            conn = SfDBResource.getConnection();

            // ��zfjgForm�е����ݽṹת�����������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);
            report.setVersion(CodeConstant.SBZL_QYSDSNB_VERSION_2012);
            // ��ȡ���ݿ�Ӧ�ýӿ�
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            // ����saveSingleTable�����������ݱ���
            iApp.saveSingleTable(report);

            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            table.getCellContentList().clear();

            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            //vo.setData(zfjgForm);

            //zfjgForm = (ZfjgfzjgNbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ����ɹ�����czqysdsjbForm
        return zfjgForm;
    }
    
    /**
     * ��ActionForm�е����ݽṹת���������ݿ�ӿڲ���Ҫ������ݽṹ ҳ�����ݽṹ-->�ӿ����ݽṹ
     *
     * @param form
     * @return ��ҵ����˰������������
     */
    private QysdsReportsDeclare translate2Interface(ZfjgfzjgNbForm form) {

        // ����QysdsReportsDeclare����
        QysdsReportsDeclare report = new QysdsReportsDeclare();
        // ��form�еĻ�����Ϣת��QysdsReportsDeclare������
        QysdsNewUtil.setQysdsReport(report, form);

        // ������ҵ����˰�����ڵ����������󣬲����������Ϣ
        QysdsReportsTableDeclare table = new QysdsReportsTableDeclare();
        //�ֻܷ�����
        table.setTableId(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
        table.setTableName(CodeConstant.TABLE_NAME_ZFJGSDSNB_2012);
        table.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);

        // ����ҳ���ܻ�������
        this.translateZjgDate2Interface(form, table);
        // ����ҳ���֧������ϸ����
        this.translateFzjgmxDate2Interface(form, table);
        // ��Ԫ���ֵ����
        report.getTableContentList().put(table.getTableId(), QysdsNewUtil.cleanSpace(table));

        return report;
    }
    
    
    /**
     * ����ҳ���ܻ�������
     *    �����ݴ�ZfjgqysdsjbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form ZfjgqysdsjbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateZjgDate2Interface(ZfjgfzjgNbForm form, QysdsReportsTableDeclare table)
    {
        QysdsReportsItemDeclare item;
        /**
         * �ܻ�������
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("1");
        item.setItemValue(form.getZjgmc());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����˰��ʶ���
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("2");
        item.setItemValue(form.getNsrsbh());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * Ӧ������˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("3");
        item.setItemValue(form.getYnsdse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����̯����˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("4");
        item.setItemValue(form.getZjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ܻ�����������������˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("5");
        item.setItemValue(form.getZjgfpse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * ��֧������̯����˰��
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("6");
        item.setItemValue(form.getFzjgftse());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �����ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("7");
        item.setItemValue(form.getSrehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * ���ʶ�ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("8");
        item.setItemValue(form.getGzehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ʲ���ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("9");
        item.setItemValue(form.getZcehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);
        
        /**
         * ���ʶ�ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("10");
        item.setItemValue(form.getFpblhj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);

        /**
         * �ʲ���ϼ�
         */
        item = new QysdsReportsItemDeclare();
        item.setItemID("11");
        item.setItemValue(form.getFpsehj());
        item.setItemType("11");
        table.getCellContentList().put(item.getItemID(), item);        
    }

    /**
     * ����ҳ���֧������ϸ����
     *     �����ݴ�ZfjgqysdsjbForm��ȡ����䵽QysdsReportsTableDeclare����
     * @param form ZfjgfzjgNbForm
     * @param table QysdsReportsTableDeclare
     */
    private void translateFzjgmxDate2Interface(ZfjgfzjgNbForm form, QysdsReportsTableDeclare table)
    {
        // ��ҳ���֧������ϸ���ݷ�������ݿ�ӿڵ����ݸ�ʽ
        List list = form.getQysdsNbList();

        for (int i = 0; i < list.size(); i++)
        {
            HashMap map = (HashMap) list.get(i);

            boolean flag =  true;
            for(int j = 12; j < 19; j++)
            {
                QysdsReportsItemDeclare item = new QysdsReportsItemDeclare();
                String id = String.valueOf(j) + "." + String.valueOf(i + 1);
                item.setItemID(id);
                switch(j)
                {
                    case 12:
                        //��֧������˰��ʶ���
                        if(map.get("fzjgnsrsbh") == null || map.get("fzjgnsrsbh").equals(""))
                        {
                            flag = false;
                            item.setItemValue("");
                        }
                        else
                        {
                            item.setItemValue((String) map.get("fzjgnsrsbh"));
                            item.setItemType("11");
                        }
                        break;
                    case 13:
                        if(flag)
                        {
                            //��֧��������
                            item.setItemValue((String) map.get("fzjgmc"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 14:
                        if(flag)
                        {
                            //��֧���������ܶ�
                            item.setItemValue((String) map.get("fzjgsrze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 15:
                        if(flag)
                        {
                            //��֧���������ܶ�
                            item.setItemValue((String) map.get("fzjggzze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 16:
                        if(flag)
                        {
                            //��֧�����ʲ��ܶ�
                            item.setItemValue((String) map.get("fzjgzcze"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 17:
                        if(flag)
                        {
                            //��֧�����������
                            String temp = (String) map.get("fzjgfpbl");
                            if(!temp.equals("0")){
                            	temp = temp.substring(0, (temp.length() - 1));
                            }
                            item.setItemValue(String.valueOf(Arith.round(Double.parseDouble(temp)/100, 4)));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                    case 18:
                        if(flag)
                        {
                            //��֧��������˰��
                            item.setItemValue((String) map.get("fzjgfpse"));
                            item.setItemType("11");
                        }
                        else
                        {
                            item.setItemValue("");
                        }
                        break;
                }
                table.getCellContentList().put(item.getItemID(), item);
            }
        }
    }
    
    /**
     * doDelete ����ɾ��ҳ���ύ���꾡������Ϣ
     *
     * @param vo
     *            ҵ�����
     * @return ���ݶ���[�������null(��Action����Ҫ����ֵ)�������Ҫ����ֵ��������ActionForm]
     * @throws BaseException
     *             ��������������׳��쳣��Ϣ
     */
    private Object doDelete(VOPackage vo) throws BaseException {
        System.out.println("---------doDelete");
        ZfjgfzjgNbForm zfjgForm = (ZfjgfzjgNbForm) vo.getData();

        Connection conn = null;
        // �걨�ں�Ϊ1
        String qh ="1"; 

        // ��ҳ����ȡ��˰�������ں����
        String nd = zfjgForm.getSkssksrq().substring(0, 4);
        // ���ü���
        zfjgForm.setQh(qh);
        // �������
        zfjgForm.setSknd(nd);

        try {
            // �������ݿ�����
            conn = SfDBResource.getConnection();
            // ��czqysdsjbForm�е����ݽṹת��,�������ݿ�ӿڲ���Ҫ������ݽṹ
            QysdsReportsDeclare report = this.translate2Interface(zfjgForm);

            // ��ȡ���ݿ�Ӧ�ýӿ�,����deleteSingleTable����ɾ������
            IAppAccess iApp = AppAccessFactory.getAInstance(conn, AppAccessFactory.ACCESS_MODEL_APP_QYSDS);
            iApp.deleteSingleTable(report);

            //��ҵ����˰�����ڵ�����������
			QysdsReportsTableDeclare table32 = new QysdsReportsTableDeclare();
			table32.setTableId(CodeConstant.TABLE_ID_CZZSSDSNB_2012);
			table32.setTbblx(Constants.CREPORTS_IBBQLX_TYPE_YEAR);
			// set table
			report.getTableContentList().put(table32.getTableId(), table32);
			// ����delete������������ɾ��
			iApp.deleteSingleTable(report);
            
            // ��ȡһ�����п�ֵ��QysdsReportsTableDeclare����
            QysdsReportsTableDeclare table = (QysdsReportsTableDeclare) report.getTableContentList().get(CodeConstant.TABLE_ID_ZFJGSDSNB_2012);
            System.out.println("11111table.getCellContentList() = " + table.getCellContentList().size());
            table.getCellContentList().clear();
            System.out.println("table.getCellContentList() = " + table.getCellContentList().size());
            // �����ݿ��е����ݷ����ҳ���������ݸ�ʽ
//            int[] arrs = { 1, 11 };
//            zfjgForm.setQysdsjbList(this.translate2Page(putSpace(table, arrs)));
            zfjgForm.setQysdsNbList(this.translate2Page(table,zfjgForm));
            zfjgForm.setMaxIndex(this.getMxDateMaxIndex(conn, report,zfjgForm));
            
            //vo.setData(zfjgForm);            
            //zfjgForm = (ZfjgqysdsjbForm) this.doQuery(vo);

        } catch (Exception ex) {
            // �׳��쳣
            ex.printStackTrace();
            throw ExceptionUtil.getBaseException(ex);
        } finally {
            // �ͷ����ݿ�����
            SfDBResource.freeConnection(conn);
        }
        // ɾ���ɹ�����hdzssdsnbForm
        return zfjgForm;
    }
        
    
    /**
     * ��ѯ�������ձ������������� 
     * ��һ��¼��ͨ��CzzsfzjgNbFormƴװҳ������Ҫ����
     * @param conn Connection
     * @param form ZfjgqysdsjbForm
     * @throws BaseException
     */
    private void getFtseFormCzzs(Connection conn, ZfjgfzjgNbForm form,CzzsfzjgNbForm czzsfzjgNbForm) throws BaseException
    {

        String fzjgftse = "0.00";
        String fzjgfpbl = "0.00";
        String fzjgfpse = "0.00";  
        
        String ynsdse="0.00"; 
        String zjgftse="0.00"; 
        String zjgfpse="0.00"; 
        List list = czzsfzjgNbForm.getQysdsnbList();
    	for (int i = 0; i < list.size(); i++) {
    		HashMap map = (HashMap) list.get(i);
    		String hc = (String) map.get("hc");
    		if(hc.equals("32")){
    			double fzjgftseCzzs=Double.parseDouble((String)map.get("lje"));
    			ynsdse=fzjgftseCzzs*2+"";
    			zjgftse=fzjgftseCzzs/2+"";
    			zjgfpse=fzjgftseCzzs/2+"";
    			fzjgftse=fzjgftseCzzs+"";
    			
    		}
    		if(hc.equals("34")){
    			fzjgfpbl=(String)map.get("lje");
    		}
    		if(hc.equals("35")){
    			fzjgfpse=(String)map.get("lje");
    		}
    	} 
    	form.setYnsdse(ynsdse);
    	form.setZjgftse(zjgftse);
    	form.setZjgfpse(zjgfpse);
    	
        form.setFzjgftse(fzjgftse);
        form.setFzjgfpbl(fzjgfpbl);
        form.setFzjgfpse(fzjgfpse);
        form.setFzjgnsrsbh(form.getNsrsbh());
        form.setFzjgnsrmc(form.getNsrmc());
          
    }
    
    /**
     * ���ӿ����ݽṹ�е�����ת������ҳ��Ҫ������ݽṹ �ӿ����ݽṹ-->ҳ�����ݽṹ
     * ��һ��¼��ͨ��CzzsfzjgNbFormƴװҳ������Ҫ����
     * @param QysdsReportsTableDeclare
     * @return ҳ������ݵ�List����
     */
    private List CzzsForm2Page(ZfjgfzjgNbForm form)
    {
        // ����List�����������ҳ�������
        ArrayList pagelist = new ArrayList();
     
       //��˰��ʶ���
       Map map1=new HashMap();     
       map1.put("hc", "1");
       map1.put("value", "");
       pagelist.add(map1);
        
       //��˰������
       Map map2=new HashMap();     
       map2.put("hc", "2");
       map2.put("value", "");
       pagelist.add(map2);
       
       //Ӧ������˰��
       Map map3=new HashMap();     
       map3.put("hc", "3");
       map3.put("value", "0.00");
       pagelist.add(map3);
       
       //�ܻ�����̯����˰��
       Map map4=new HashMap();     
       map4.put("hc", "4");
       map4.put("value", "0.00");
       pagelist.add(map4);
       
       //�ܻ����������з�������˰��
       Map map5=new HashMap();     
       map5.put("hc", "5");
       map5.put("value", "0.00");
       pagelist.add(map5);
       
       //��֧������̯����˰��
       Map map6=new HashMap();     
       map6.put("hc", "6");
       //map6.put("value", form.getFzjgftse());
       map6.put("value", "0.00");
       pagelist.add(map6);
       
       Map map10=new HashMap();     
       map10.put("hc", "10");
       //map10.put("value",form.getFzjgfpbl()+"%");
       map10.put("value","0.00");
       pagelist.add(map10);
       
       Map map11=new HashMap();     
       map11.put("hc", "11");
       //map11.put("value", form.getFzjgfpse());
       map11.put("value", "0.00");
       pagelist.add(map11);
       
       Map map12=new HashMap();     
       map12.put("hc", "12.1");
       map12.put("value", form.getNsrsbh());
       pagelist.add(map12);
       
       Map map13=new HashMap();     
       map13.put("hc", "13.1");
       map13.put("value", form.getNsrmc());
       pagelist.add(map13);
       
       Map map14=new HashMap();     
       map14.put("hc", "14.1");
       map14.put("value", "0.00");
       pagelist.add(map14);
       
       Map map15=new HashMap();     
       map15.put("hc", "15.1");
       map15.put("value", "0.00");
       pagelist.add(map15);
       
       
       Map map16=new HashMap();     
       map16.put("hc", "16.1");
       map16.put("value", "0.00");
       pagelist.add(map16);
       
       Map map17=new HashMap();     
       map17.put("hc", "17.1");
       //map17.put("value", form.getFzjgfpbl()+"%");
       map17.put("value", "0.00");
       pagelist.add(map17);
       
       Map map18=new HashMap();     
       map18.put("hc", "18.1");
       //map18.put("value", form.getFzjgfpse());
       map18.put("value", "0.00");
       pagelist.add(map18);
       
        // ����List����
        return pagelist;
    }
}

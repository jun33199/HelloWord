/*
 * <p>Title: ������˰��������ϵͳ���������걨--��ѯ���ӽɿ�ר�ýɿ���</p>
 * <p>Copyright: (C) 2004-2005 �����еط�˰��֣�������һ���ſƼ����޹�˾����Ȩ����. </p>
 * <p>Company: ������һ���ſƼ����޹�˾</p>
 */
package com.ttsoft.bjtax.smsb.dzwsz.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Statement;

import com.ttsoft.bjtax.pzgl.proxy.ServiceProxy;
import com.ttsoft.bjtax.sfgl.common.code.CodeUtils;
import com.ttsoft.bjtax.sfgl.common.db.util.SfDBResource;
import com.ttsoft.bjtax.smsb.constant.CodeConstant;
import com.ttsoft.bjtax.smsb.dzwsz.web.DzwszForm;
import com.ttsoft.bjtax.smsb.util.Debug;
import com.ttsoft.bjtax.smsb.util.InterfaceDj;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.common.model.UserData;
import com.ttsoft.framework.exception.ApplicationException;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.exception.SystemException;
import com.ttsoft.framework.processor.Processor;
import com.ttsoft.framework.util.Currency;
import com.ttsoft.framework.util.VOPackage;
import com.ttsoft.framework.util.StringUtil;

/**
 * <p>Title: ������˰��������ϵͳ���������걨</p>
 * <p>Description: ��ѯ���ӽɿ�ר�ýɿ���Action��</p>
 * @author ������ - ������
 * @version 1.0
 */
public class DzwszProcessor implements Processor {

    public DzwszProcessor() {
    }

    //���ø�ʽ������
    private final DecimalFormat deFormat = new DecimalFormat("#0.00");
    class tmpQuery {
        //��ע
        String bz;
        //������֯�ṹ����
        String gkzzjgdm;
        //�걨����
        Timestamp sbrq;
        //ʵ������
        BigDecimal sjse;
        //˰�ִ���
        String szdm;
        //˰Ŀ����
        String szsmdm;
        //�����ʺ�
        String yhzh;
        //��������
        String yhmc;
        //Ԥ���Ŀ����
        String yskmdm;
        //˰�������֯��������
        String swjgzzjgdm;
        //˰��������ʼ����
        String skssksrq;
        //˰��������������
        String skssjsrq;
        //������Դ 2013 kanght
        String sjly;
    };
    /**
     * ��ѯ���ӽɿ�ר�ýɿ���
     * @param vp VOPackage
     * @return Object
     * @throws BaseException BaseException
     */
    public Object process(VOPackage vp) throws BaseException {
        switch (vp.getAction()) {
        case CodeConstant.SMSB_QUERYACTION:

            //��ȡ˰�յ���ת��ר����˰֤
            return this.doQuery(vp);
        case CodeConstant.SMSB_PRINTACTION:
            //��ӡ˰�յ���ת��ר����˰֤
            return this.doPrint(vp);
        case CodeConstant.SMSB_QUERYACTION1:
        	return this.new_Query(vp);
        case CodeConstant.SMSB_SFDYWSZ:
        	return this.getPrintInfo(vp);
        default:
            throw new ApplicationException(
                "ActionType�д���processor���Ҳ�����Ӧ�ķ���.");
        }
    }

    /**
     * ��ӡ˰�յ���ת��ר����˰֤
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��bo����
     * @throws BaseException
     */
    private Object doPrint(VOPackage vo) throws BaseException {
        //�������Ӷ���
        Connection conn = null;
        //��ȡ��ѯ�������
        DzwszBO bo = (DzwszBO) vo.getData();
        //ɾ��SBDB.SB_JL_DYHKWSZRZ���д�ӡʧ�ܵ���Ϣdopring()������ȡ�Ŵ�ӡ
        DeletePrintInfo(bo);
        
        //��ȡ��ǰ��������Ϣ
        UserData userdata = (UserData) vo.getUserData();
        //��ѯ��˰����Ϣ
        List list = bo.getSzitem();
       
        //������Ĵ�ӡ˰����Ϣ
        List rsList = new ArrayList();
        try {
            //��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            if (list != null && list.size() > 0) {
                //��ʾ������
                int rowNumb = 8;
                //�ܼ�¼��
                int totalCount = list.size();
                //������ҳ���������������0��ҳ��Ϊ��������1������Ϊ������
                //����
                int residual = totalCount % rowNumb;
                //��ҳ��
                int totalPage = totalCount / rowNumb;
                for (int tl = 0; tl < totalPage; tl++) {
                    List smList = new ArrayList();
                    BigDecimal hjje = new BigDecimal("0.00");
                    //ȡ��Ҫ��ʾ�ļ�¼
                    for (int i = 0; i < rowNumb; i++) {
                        Map rstmap = (Map) list.get(i + 8 * tl);
                        //�����ҳ��ӡ�ĵ�ҳ�ϼƽ��
                        hjje = hjje.add(
                            new BigDecimal((String) rstmap.get("sjje")));
                        smList.add(rstmap);
                    }
                    //��ҳ�ĵ�ҳ��ӡ��Ϣ
                    DzwszBO printbo = setPrintBo(bo, hjje, userdata);
                    SavePrintInfo(printbo, conn, userdata);
                    //�ϼƽ��
                    printbo.setHjjexx(deFormat.format(hjje));
                    //�Ѻϼƽ��ת��Ϊ��д
                    printbo.setHjjedx(Currency.convert(hjje));
                    //��ҳ�ĵ�ҳ��ӡ��Ϣ--˰Ŀ��Ϣ
                    printbo.setSzitem(smList);
                    rsList.add(printbo);
                }
                if (residual > 0) {
                    //�����һҳ
                    List smList = new ArrayList();
                    BigDecimal hjje = new BigDecimal("0.00");
                    //ȡ��Ҫ��ʾ�ļ�¼
                    for (int i = 0; i < residual; i++) {
                        Map rstmap = (Map) list.get(i + 8 * totalPage);
                        //�����ҳ��ӡ�ĵ�ҳ�ϼƽ��
                        hjje = hjje.add(
                            new BigDecimal((String) rstmap.get("sjje")));
                        smList.add(rstmap);
                    }
                    //��ҳ�ĵ�ҳ��ӡ��Ϣ
                    DzwszBO printbo = setPrintBo(bo, hjje, userdata);
                    SavePrintInfo(printbo, conn, userdata);
                    //�ϼƽ��
                    printbo.setHjjexx(deFormat.format(hjje));
                    //�Ѻϼƽ��ת��Ϊ��д
                    printbo.setHjjedx(Currency.convert(hjje));
                    //��ҳ�ĵ�ҳ��ӡ��Ϣ--˰Ŀ��Ϣ
                    printbo.setSzitem(smList);
                    rsList.add(printbo);
                }
            } else {
                throw new ApplicationException("��ӡ˰�յ���ת��ר����˰֤ʱ����");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            SfDBResource.freeConnection(conn);
        }
        return rsList;
    }
    
    /**
     * 20130527���޸�
     * ��ѯ˰�յ���ת��ר����˰֤
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��bo����
     * @throws BaseException
     */
    private List new_Query(VOPackage vo) throws BaseException {
    	
    	DzwszForm yForm = (DzwszForm) vo.getData();
    	//��˰֤������б�
    	 ArrayList results = new ArrayList();
    	//������ƾ֤������˰֤
    	if(!(yForm.getFkpzjsjdm().equals("") || yForm.getFkpzjsjdm() == "")){
    		System.out.println("������ƾ֤������˰֤");
    		 DzwszBO bo = (DzwszBO)fkpz_Query(vo);
    		 if(!bo.equals("") && bo != null){
    			 results.add(bo);
    		 }
    		
    	//�����ۿ�����˰֤
    	}else{
    		System.out.println("�����ۿ�����˰֤");
    		results = (ArrayList) cz_Query(vo);
    		
    	}
    	
    	 return results;	
    	
    }
    /**
     * 20130527���޸�
     * kanght
     * ��ѯ˰�յ���ת��ר����˰֤---�����ۿ���
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��bo����
     * @throws BaseException
     */
    private List cz_Query(VOPackage vo) throws BaseException {
    	//��ѯ�����
    	List list = new ArrayList();
    	
        //�������Ӷ���
        Connection conn = null;
        //����ִ�ж���
        PreparedStatement ps = null;
        //���ݷ��ض���
        ResultSet rs = null;
        //form����
        DzwszForm form = (DzwszForm) vo.getData();
        //��ѯ����bo����
        DzwszBO bo = form.getBo();
       
        try {
            //��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            //��������Ϣ
            UserData ud = (UserData) vo.getUserData();
            //���ش���--��Ϊ��˲�ѯ����
            String qxdm = InterfaceDj.getQxdm(ud);
    
            //��ѯ��sql��䣬�������ز�ѯ����ΪSUBSTR(ZB.ZWBS, 1, 1) <> 0������⣩
            String sql =
           "SELECT DISTINCT ZB.SPHM FROM SBDB.SB_JL_SBJKZB ZB, DJDB.DJ_JL_JBSJ T " +
           "WHERE ZB.JSJDM = T.JSJDM " +
           "AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 " +
           //20131011 kanght ���ѯ������ԴΪ123 ��zwbsΪ092
           //"AND SUBSTR(ZB.ZWBS, 18, 3) = '092' " +
           "AND ZB.SJLY= '23' " +
           
           "AND SUBSTR(T.SWJGZZJGDM, 1, 2) = ? " +
           "AND ZB.JSJDM = ? " +
           "AND LENGTH(ZB.SPHM) = 18 " +
           "AND ZB.ZYRQ >= TO_DATE(?, 'YYYYMMDD') " +
           "AND ZB.ZYRQ <= TO_DATE(?, 'YYYYMMDD')";
            ps = conn.prepareStatement(sql);
            //�����ѯ���������ش���
            ps.setString(1, qxdm);
            //�����ѯ���������������
            ps.setString(2, bo.getJsjdm());
            //��ѯ��ʼ����
            ps.setString(3, form.getCzcxqrq());
            //��ѯ��ֹ����
            ps.setString(4, form.getCzcxzrq());
            Debug.out("com in DzwszProcessor ���ۿ���  sql == " + sql);
            //ִ�����ݿ��ѯ
            rs = ps.executeQuery();
//            if(rs.equals(null)){
//            	throw new ApplicationException("Ϊ��ѯ���������������ݣ�");
//            }
            if(!rs.next()){
            	throw new ApplicationException("δ��ѯ���������������ݣ�");
            }else{
            	do{
            	    bo.setSphm(rs.getString("SPHM"));
                    //�������ò���
                    DzwszBO bo_cx = new DzwszBO();
                    bo_cx.setJsjdm(bo.getJsjdm());
                    bo_cx.setSphm(rs.getString("SPHM"));
                    //����bo����
                    form.setBo(bo_cx);
                    //��ȡ��˰֤��Ϣ
                    DzwszBO bo2 = (DzwszBO) fkpz_Query(vo);
                    //��˰֤��Ϣ���õ�list��
                    list.add(bo2);
            	}while(rs.next());
            }
            
            
        	/*while (rs.next()) {
            	//˰Ʊ����
              bo.setSphm(rs.getString("SPHM"));
              //�������ò���
              DzwszBO bo_cx = new DzwszBO();
              bo_cx.setJsjdm(bo.getJsjdm());
              bo_cx.setSphm(rs.getString("SPHM"));
              //����bo����
              form.setBo(bo_cx);
              //��ȡ��˰֤��Ϣ
              DzwszBO bo2 = (DzwszBO) fkpz_Query(vo);
              //��˰֤��Ϣ���õ�list��
              list.add(bo2);
            }*/
            rs.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
        	 SfDBResource.freeConnection(conn);
        }
        return list;
    }
    
    /**
     * 20130626
     * kanght
     * ��ѯ˰�յ���ת��ר����˰֤----������ƾ֤����
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��bo����
     * @throws BaseException
     */
    private Object fkpz_Query(VOPackage vo) throws BaseException {
        //һƱ��˰��Ϣ
        List dataList = new ArrayList();
        //�������Ӷ���
        Connection conn = null;
        //����ִ�ж���
        PreparedStatement ps = null;
        //���ݷ��ض���
        ResultSet rs = null;
        //�ϼƽ��
        BigDecimal hjje = new BigDecimal("0.00");
        //��˰�ֵ�ʵ�ɽ��
        BigDecimal tmpdd;
        //form����
        DzwszForm form = (DzwszForm) vo.getData();
        //��ѯ����bo����
        DzwszBO bo = form.getBo();
       

        //������Ĳ�ѯ���
        ArrayList results = new ArrayList();
        //˰Ŀ����
        String tempstr;

        try {
            //��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            //��������Ϣ
            UserData ud = (UserData) vo.getUserData();
            //���ش���--��Ϊ��˲�ѯ����
            String qxdm = InterfaceDj.getQxdm(ud);
           /* HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
            //��ȡ��˰������
            bo.setNsrmc((String) djmap.get("nsrmc"));
            //��ȡ��˰������
            bo.setSwdjzh((String) djmap.get("swdjzh"));
            */
            //˰Ʊ����
            String sphm = bo.getSphm();
            //��ȡ������ˮ��
             bo.setJylsh(getJylsh(bo, conn));
             if (bo.getJylsh() == null || bo.getJylsh().equals("")) {
            	 form.setWszList(new ArrayList());
                 throw new ApplicationException("����˰Ʊ����δ�ҵ�������ˮ�ţ�");
                 
             }
            //��ѯ��sql��䣬�������ز�ѯ����ΪSUBSTR(ZB.ZWBS, 1, 1) <> 0������⣩
            //LENGTH(ZB.SPHM) = 18 Ϊʱʱ�ۿ�����
            String sql =
                "SELECT ZB.SZDM , MX.SZSMDM , MX.SJSE , ZB.SWJGZZJGDM , ZB.SBRQ , ZB.SJLY ,ZB.BZ,"
                + "ZB.YHMC , ZB.ZH , MX.YSKMDM , ZB.GKZZJGDM , "
                + "TO_CHAR(ZB.SKSSKSRQ,'YYYYMMDD') SKSSKSRQ, "
                + "TO_CHAR(ZB.SKSSJSRQ,'YYYYMMDD') SKSSJSRQ "
                + "FROM SBDB.SB_JL_SBJKMX MX,SBDB.SB_JL_SBJKZB ZB,DJDB.DJ_JL_JBSJ T "
                + "WHERE MX.JKPZH=ZB.JKPZH And ZB.JSJDM = T.JSJDM AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 "
                //20131011 kanght ���ѯ������ԴΪ123 ��zwbsΪ092
//                + "AND SUBSTR(ZB.ZWBS, 18, 3) = '092' " 
                + "AND ZB.SJLY= '23' "
                
                + "AND  SUBSTR(T.SWJGZZJGDM, 1, 2) = ? AND ZB.JSJDM=? "
                + "AND LENGTH(ZB.SPHM) = 18 AND ZB.SPHM=? "
                + "ORDER BY MX.SZSMDM,MX.YSKMDM";
            //����sql
            ps = conn.prepareStatement(sql);
            //�����ѯ���������ش���
            ps.setString(1, qxdm);
            //�����ѯ���������������
            ps.setString(2, bo.getJsjdm());
            //�����ѯ������˰Ʊ����
            ps.setString(3, sphm);
            
            Debug.out("com in DzwszProcessor doQuery  sql == " + sql);
            //ִ�����ݿ��ѯ
            rs = ps.executeQuery();
            //��ʱ�����ѯ������࣬����ѯ���ת��Ϊ��Ԥ���Ŀ���з���
            tmpQuery tq = null;
            //����ѯ����ŵ������У����ڴ���
            while (rs.next()) {
                tq = new tmpQuery();
                tq.gkzzjgdm = rs.getString("GKZZJGDM");
                tq.sbrq = rs.getTimestamp("SBRQ");
                tq.sjse = rs.getBigDecimal("SJSE");
                tq.swjgzzjgdm = rs.getString("SWJGZZJGDM");
                tq.szdm = rs.getString("SZDM");
                tq.szsmdm = rs.getString("SZSMDM");
                tq.yhmc = rs.getString("YHMC");
                tq.yhzh = rs.getString("ZH");
                tq.yskmdm = rs.getString("YSKMDM");
                tq.skssksrq = rs.getString("SKSSKSRQ");
                tq.skssjsrq = rs.getString("SKSSJSRQ");
                tq.sjly = rs.getString("sjly");
                tq.bz = rs.getString("BZ");
                results.add(tq);
            }
            if (results.size() == 0) {
                throw new ApplicationException("�Ҳ����걨���ݣ�");
            }
            //��ȡjsjdm���һλ
            char ch = bo.getJsjdm().charAt(bo.getJsjdm().length()-1);
            
            //�ж��ǲ�����Ȼ��
            if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z')){
            	//���õǼǽӿڻ�ȡ��Ȼ�˵Ǽ���Ϣ
            	Map tmp = getZRRInfo(bo.getJsjdm(),conn);
            	//��ȡ��˰������
                bo.setNsrmc((String) tmp.get("nsrmc"));
                //��ȡ��˰������
                bo.setSwdjzh("");
                
                System.out.println("��Ȼ�����ƣ�"+tmp.get("nsrmc"));
                
                
                /*���������Դ��17��ͷ ��ɢ��˰��*/
            }else if(tq.sjly.substring(0, 2).equals("17")){
            	
                //��ȡ��˰������
            	String nsrmc = tq.bz;
                bo.setNsrmc(nsrmc.substring(0, nsrmc.indexOf("#$#")).trim());
                
                HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
                //��ȡ˰��Ǽ�֤��
                bo.setSwdjzh((String) djmap.get("swdjzh"));
                
                System.out.println("��ɢ��˰�����ƣ�"+bo.getNsrmc());
                
              //����ɢ��˰��,����Ȼ��
            }else{
            	
            	HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
                //��ȡ��˰������
                bo.setNsrmc((String) djmap.get("nsrmc"));
                //��ȡ��˰������
                bo.setSwdjzh((String) djmap.get("swdjzh"));
                
                System.out.println("��˰�����ƣ�"+djmap.get("nsrmc"));
            }
            
            //�걨����
            bo.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            //�տ���������
            bo.setYhmc(tq.yhmc);
            //�տ������ʺ�
            bo.setZh(tq.yhzh);
            // ���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
            bo.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                tq.swjgzzjgdm));
        
            // �������
            bo.setGkzzjgdm(tq.gkzzjgdm);
            //��������
            bo.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", tq.gkzzjgdm));
            //���ȽϵĲ�ѯ���
            Map preZB = null;
            //��ǰ�Ĳ�ѯ���
            Map curZB = null;
            //�Ƚϵ��α�
            int isz;
            //����ѯ���ת��Ϊ��Ԥ���Ŀ���з���
            for (int i = 0; i < results.size(); i++) {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for (isz = 0; isz < dataList.size(); isz++) {
                    preZB = (Map) dataList.get(isz);
                    if (((String) preZB.get("szdm")).equals(tq.szdm)
                        && ((String) preZB.get("yskmdm")).equals(tq.yskmdm)
                        && ((String) preZB.get("skssksrq")).equals(tq.skssksrq)
                        && ((String) preZB.get("skssjsrq")).equals(tq.skssjsrq)) {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size())) {
                    curZB = new HashMap();
                    curZB.put("szdm", tq.szdm);
                    curZB.put("sjje", tq.sjse);
                    curZB.put("yskmdm", tq.yskmdm);
                    curZB.put("skssksrq", tq.skssksrq);
                    curZB.put("skssjsrq", tq.skssjsrq);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM", tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92")) {
                        tempstr += "(���ɽ𡢷���)";
                    }
                    curZB.put("szmc", tempstr);

                    dataList.add(curZB);
                    
                    System.out.println("curZB:"+curZB);
                    
                } else {
                    tmpdd = (BigDecimal) preZB.get("sjje");
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje", tmpdd);
                }
                hjje = hjje.add(tq.sjse);
            }
            for (int i = 0; i < dataList.size(); i++) {
                curZB = (HashMap) dataList.get(i);
                tmpdd = (BigDecimal) curZB.get("sjje");
                curZB.put("sjje", deFormat.format(tmpdd));
            }
            //�ϼƽ��
            bo.setHjjexx(deFormat.format(hjje));
            //���ذ�Ԥ���Ŀ���з���Ĳ�ѯ���ת��
            bo.setSzitem(dataList);
            //�Ѻϼƽ��ת��Ϊ��д
            bo.setHjjedx(Currency.convert(hjje));
            //��ȡ��ӡ��Ϣ
            //HashMap rstMap=getPrintInfo(bo, conn);
            //�Ƿ��Ѿ���ӡ��־
            //bo.setSfdybz((String)rstMap.get("jsjdm"));
            //��ӡ����
            //bo.setDyrq((String)rstMap.get("dyrq"));
            //����������롢˰Ʊ���롢������ˮ���ڱ�ע����ӡ
            bo.setBz1("��������룺" + bo.getJsjdm());
            bo.setBz2("˰Ʊ���룺" + bo.getSphm());
            bo.setBz3("������ˮ�ţ�" + bo.getJylsh());
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return bo;
    }
    /**
     * ��ѯ˰�յ���ת��ר����˰֤
     * @param vo ���ݼ����󣨰���Form��UserData����
     * @return ��ǰҳ���Ӧ��bo����
     * @throws BaseException
     */
    private Object doQuery(VOPackage vo) throws BaseException {
        //һƱ��˰��Ϣ
        List dataList = new ArrayList();
        //�������Ӷ���
        Connection conn = null;
        //����ִ�ж���
        PreparedStatement ps = null;
        //���ݷ��ض���
        ResultSet rs = null;
        //�ϼƽ��
        BigDecimal hjje = new BigDecimal("0.00");
        //��˰�ֵ�ʵ�ɽ��
        BigDecimal tmpdd;
        //��ѯ����bo����
        DzwszBO bo = (DzwszBO) vo.getData();

        //������Ĳ�ѯ���
        ArrayList results = new ArrayList();
        //˰Ŀ����
        String tempstr;

        try {
            //��ȡ���ݿ�����
            conn = SfDBResource.getConnection();
            //��������Ϣ
            UserData ud = (UserData) vo.getUserData();
            //���ش���--��Ϊ��˲�ѯ����
            String qxdm = InterfaceDj.getQxdm(ud);
            HashMap djmap = getNsrmc(bo.getJsjdm(), conn);
            //��ȡ��˰������
            bo.setNsrmc((String) djmap.get("nsrmc"));
            //��ȡ��˰������
            bo.setSwdjzh((String) djmap.get("swdjzh"));
            //˰Ʊ����
            String sphm = bo.getSphm();
            //������ˮ��
            String jylsh = bo.getJylsh();
            //ֻ���뽻����ˮ��ʱ����ȡ˰Ʊ����
            if (sphm == null || sphm.equals("")) {
				        if(jylsh == null||jylsh.equals("") || jylsh.length() != 16)
				        {
				        		throw new ApplicationException("������ˮ�Ŵ����Ҳ����걨���ݡ�");
				        }            	
                sphm = getSphm(bo, conn);
                bo.setSphm(sphm);
            }
            //ֻ����˰Ʊ����ʱ����ȡ˰Ʊ����
            if (jylsh == null || jylsh.equals("")) {
                bo.setJylsh(getJylsh(bo, conn));
            }
            //�����ݿ��в�����˰Ʊ������Ϣ���׳�ApplicationException("������걨��ţ��Ҳ����걨���ݡ�")
            if (sphm == null || sphm.equals("")) {
                throw new ApplicationException("�걨��Ŵ����Ҳ����걨���ݡ�");
            }
            //�����ݿ��в�����˰Ʊ������Ϣ���׳�ApplicationException("������걨��ţ��Ҳ����걨���ݡ�")
            if (bo.getJylsh() == null || bo.getJylsh().equals("")) {
                throw new ApplicationException("������ˮ�Ŵ����Ҳ����걨���ݡ�");
            }
            //��ѯ��sql��䣬�������ز�ѯ����ΪSUBSTR(ZB.ZWBS, 1, 1) <> 0������⣩
            //LENGTH(ZB.SPHM) = 18 Ϊʱʱ�ۿ�����
            String sql =
                "SELECT ZB.SZDM , MX.SZSMDM , MX.SJSE , ZB.SWJGZZJGDM , ZB.SBRQ , "
                + "ZB.YHMC , ZB.ZH , MX.YSKMDM , ZB.GKZZJGDM , "
                + "TO_CHAR(ZB.SKSSKSRQ,'YYYYMMDD') SKSSKSRQ, "
                + "TO_CHAR(ZB.SKSSJSRQ,'YYYYMMDD') SKSSJSRQ "
                + "FROM SBDB.SB_JL_SBJKMX MX,SBDB.SB_JL_SBJKZB ZB "
                + "WHERE MX.JKPZH=ZB.JKPZH AND SUBSTR(ZB.ZWBS, 1, 1) <> 0 "
                + "AND ZB.QXDM=? AND ZB.JSJDM=? "
                + "AND LENGTH(ZB.SPHM) = 18 AND ZB.SPHM=? "
                + "ORDER BY MX.SZSMDM,MX.YSKMDM";
            //����sql
            ps = conn.prepareStatement(sql);
            //�����ѯ���������ش���
            ps.setString(1, qxdm);
            //�����ѯ���������������
            ps.setString(2, bo.getJsjdm());
            //�����ѯ������˰Ʊ����
            ps.setString(3, sphm);
            Debug.out("com in DzwszProcessor doQuery  sql == " + sql);
            //ִ�����ݿ��ѯ
            rs = ps.executeQuery();
            //��ʱ�����ѯ������࣬����ѯ���ת��Ϊ��Ԥ���Ŀ���з���
            tmpQuery tq = null;
            //����ѯ����ŵ������У����ڴ���
            while (rs.next()) {
                tq = new tmpQuery();
                tq.gkzzjgdm = rs.getString("GKZZJGDM");
                tq.sbrq = rs.getTimestamp("SBRQ");
                tq.sjse = rs.getBigDecimal("SJSE");
                tq.swjgzzjgdm = rs.getString("SWJGZZJGDM");
                tq.szdm = rs.getString("SZDM");
                tq.szsmdm = rs.getString("SZSMDM");
                tq.yhmc = rs.getString("YHMC");
                tq.yhzh = rs.getString("ZH");
                tq.yskmdm = rs.getString("YSKMDM");
                tq.skssksrq = rs.getString("SKSSKSRQ");
                tq.skssjsrq = rs.getString("SKSSJSRQ");
                results.add(tq);
            }
            if (results.size() == 0) {
                throw new ApplicationException("������걨��ţ��Ҳ����걨���ݡ�");
            }
            //�걨����
            bo.setSbrq(new SimpleDateFormat("yyyyMMdd").format(tq.sbrq));
            //�տ���������
            bo.setYhmc(tq.yhmc);
            //�տ������ʺ�
            bo.setZh(tq.yhzh);
            // ���ջ������ƣ�˰���������ƣ�����Ҫ���ݴ��������
            bo.setSwjgzzjgmc(CodeUtils.getCodeBeanLabel("DM_SWJGZZJG",
                tq.swjgzzjgdm));
            // �������
            bo.setGkzzjgdm(tq.gkzzjgdm);
            //��������
            bo.setGkzzjgmc(CodeUtils.getCodeBeanLabel("DM_GKZZJG", tq.gkzzjgdm));
            //���ȽϵĲ�ѯ���
            Map preZB = null;
            //��ǰ�Ĳ�ѯ���
            Map curZB = null;
            //�Ƚϵ��α�
            int isz;
            //����ѯ���ת��Ϊ��Ԥ���Ŀ���з���
            for (int i = 0; i < results.size(); i++) {
                tq = (tmpQuery) results.get(i);
                preZB = null;
                for (isz = 0; isz < dataList.size(); isz++) {
                    preZB = (Map) dataList.get(isz);
                    if (((String) preZB.get("szdm")).equals(tq.szdm)
                        && ((String) preZB.get("yskmdm")).equals(tq.yskmdm)
                        && ((String) preZB.get("skssksrq")).equals(tq.skssksrq)
                        && ((String) preZB.get("skssjsrq")).equals(tq.skssjsrq)) {
                        break;
                    }
                }
                if ((preZB == null) || (isz >= dataList.size())) {
                    curZB = new HashMap();
                    curZB.put("szdm", tq.szdm);
                    curZB.put("sjje", tq.sjse);
                    curZB.put("yskmdm", tq.yskmdm);
                    curZB.put("skssksrq", tq.skssksrq);
                    curZB.put("skssjsrq", tq.skssjsrq);
                    tempstr = CodeUtils.getCodeBeanLabel("DM_SZSM", tq.szdm);
                    if (tq.szsmdm.endsWith("91") || tq.szsmdm.endsWith("92")) {
                        tempstr += "(���ɽ𡢷���)";
                    }
                    curZB.put("szmc", tempstr);

                    dataList.add(curZB);
                } else {
                    tmpdd = (BigDecimal) preZB.get("sjje");
                    tmpdd = tmpdd.add(tq.sjse);
                    preZB.put("sjje", tmpdd);
                }
                hjje = hjje.add(tq.sjse);
            }
            for (int i = 0; i < dataList.size(); i++) {
                curZB = (HashMap) dataList.get(i);
                tmpdd = (BigDecimal) curZB.get("sjje");
                curZB.put("sjje", deFormat.format(tmpdd));
            }
            //�ϼƽ��
            bo.setHjjexx(deFormat.format(hjje));
            //���ذ�Ԥ���Ŀ���з���Ĳ�ѯ���ת��
            bo.setSzitem(dataList);
            //�Ѻϼƽ��ת��Ϊ��д
            bo.setHjjedx(Currency.convert(hjje));
            //��ȡ��ӡ��Ϣ
            HashMap rstMap=getPrintInfo(bo, conn);
            //�Ƿ��Ѿ���ӡ��־
            bo.setSfdybz((String)rstMap.get("jsjdm"));
            //��ӡ����
            bo.setDyrq((String)rstMap.get("dyrq"));
            //����������롢˰Ʊ���롢������ˮ���ڱ�ע����ӡ
            bo.setBz1("��������룺" + bo.getJsjdm());
            bo.setBz2("˰Ʊ���룺" + bo.getSphm());
            bo.setBz3("������ˮ�ţ�" + bo.getJylsh());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException(ex.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                rs = null;
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ApplicationException(e.getMessage());
                }
                ps = null;
            }
            SfDBResource.freeConnection(conn);
        }
        return bo;
    }


    /**
     * ��ȡ˰Ʊ����
     * @param bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getSphm(DzwszBO bo, Connection conn) throws BaseException {
        //������ݼ�������롢ί�����ڡ�������ˮ�Ų�ѯ˰Ʊ����sql
        String strSql =
            "SELECT PZHM FROM SKHDB.SKH_JL_GKRZDZJYMX "
            + "WHERE PZZL='00' AND PJZT = '20' AND WTRQ=? AND JYLSH=?";
        Debug.out("com in DzwszProcessor doQuery getSphm sql == " + strSql);
        //����PZHM
        String pzhm = "";
        String ymjylsh=bo.getJylsh();
        try {
            //����PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //��ѯ����ί������
            pst.setString(1, ymjylsh.substring(0, 8));
            //��ѯ����������ˮ��
            pst.setString(2, ymjylsh.substring(8));
            //ִ�в�ѯsql
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡpzhm
                pzhm = rs.getString("PZHM");
            }
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ѯ˰Ʊ�������");
        }
        //����pzhm
        return pzhm;
    }

    /**
     * ��ȡ������ˮ��
     * @param bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getJylsh(DzwszBO bo, Connection conn) throws BaseException {
        //�������˰Ʊ�����ѯ������ˮ��sql
        String strSql =
            "SELECT CONCAT(WTRQ,JYLSH) JYLSH FROM SKHDB.SKH_JL_GKRZDZJYMX "
            + "WHERE PZZL='00' AND PJZT = '20' AND PZHM=?";
        Debug.out("com in DzwszProcessor doQuery getJylsh sql == " + strSql);
        //���彻����ˮ��
        String pzhm = "";
        try {
            //����PreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //��ѯ˰Ʊ����
            pst.setString(1, bo.getSphm());
            //ִ�в�ѯsql
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ������ˮ��
                pzhm = rs.getString("JYLSH");
            }
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ѯ������ˮ�Ŵ���");
        }
        //����pzhm
        return pzhm;
    }


    /**
     * ��ѯ��˰��������Ϣ
     * @param jsjdm String
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getNsrmc(String jsjdm, Connection conn) throws
        BaseException {
        String strSql =
            "SELECT NSRMC,SWDJZH FROM DJDB.DJ_JL_JBSJ WHERE JSJDM=?";
        String nsrmc = "";
        String swdjzh = "";
        HashMap rstmap = new HashMap();
        try {
            //��ȡPreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //���ò�ѯ����Sphm
            pst.setString(1, jsjdm);
            //���ò�ѯ�������������
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ��˰������
                nsrmc = rs.getString("NSRMC");
                swdjzh = rs.getString("SWDJZH");
            }
            rstmap.put("nsrmc", nsrmc);
            rstmap.put("swdjzh", swdjzh);
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ��˰�����ƴ���");
        }
        return rstmap;
    }

    /**
     * ��ȡ�����ӡ��Ϣ�����
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private String getPrintSaveXh(Connection conn) throws BaseException {
        String strSql = "SELECT SBDB.SEQ_SB_DYDZWSZXH.NEXTVAL FROM DUAL";
        String nsrmc = "";
        try {
            //��ȡPreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //���ò�ѯ�������������
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ��˰������
                nsrmc = rs.getString("NEXTVAL");
            }
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ�����ӡ��Ϣ����Ŵ���");
        }
        return nsrmc;
    }

    /**
     * ��ѯ�Ƿ��Ѿ���ӡ����˰֤
     * @param  bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getPrintInfo(DzwszBO bo, Connection conn) throws
        BaseException {
        String strSql =
            "SELECT JSJDM,TO_CHAR(DYRQ,'YYYYMMDD') DYRQ "
            +"FROM SBDB.SB_JL_DYHKWSZRZ WHERE JSJDM=? AND SPHM=?";
        String jsjdm = "";
        String dyrq = "";
        HashMap rstMap=new HashMap();
        try {
            //��ȡPreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //���ò�ѯ����Sphm
            pst.setString(1, bo.getJsjdm());
            pst.setString(2, bo.getSphm());
            //���ò�ѯ�������������
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ��˰������
                jsjdm = rs.getString("JSJDM");
                dyrq = rs.getString("DYRQ");
            }
            if(dyrq==null||dyrq.equals("")){
            	dyrq=TinyTools.Date2String(new Timestamp(System.currentTimeMillis()),"yyyyMMdd");
            }
            rstMap.put("dyrq",dyrq);
            rstMap.put("jsjdm",jsjdm);
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ��������ת��ר����˰֤��־����");
        }
        return rstMap;
    }

    
    
    /**
     * ��ѯ�Ƿ��Ѿ���ӡ����˰֤
     * 20131028
     * kanght
     * @param  bo DzwszBO
     * @param conn Connection
     * @return String
     * @throws BaseException
     */
    private HashMap getPrintInfo(VOPackage vo) throws
        BaseException {
    	 //��ȡ���ݿ�����
        Connection conn = SfDBResource.getConnection();
        DzwszBO bo = (DzwszBO) vo.getData();
        String strSql =
            "SELECT JSJDM,TO_CHAR(DYRQ,'YYYYMMDD') DYRQ "
            +"FROM SBDB.SB_JL_DYHKWSZRZ WHERE JSJDM=? AND SPHM=?";
        String jsjdm = "";
        String dyrq = "";
        HashMap rstMap=new HashMap();
        try {
            //��ȡPreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //���ò�ѯ����Sphm
            pst.setString(1, bo.getJsjdm());
            pst.setString(2, bo.getSphm());
            //���ò�ѯ�������������
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ��˰������
                jsjdm = rs.getString("JSJDM");
                dyrq = rs.getString("DYRQ");
            }
            if(dyrq==null||dyrq.equals("")){
            	dyrq=TinyTools.Date2String(new Timestamp(System.currentTimeMillis()),"yyyyMMdd");
            }
            rstMap.put("dyrq",dyrq);
            rstMap.put("jsjdm",jsjdm);
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ��������ת��ר����˰֤��־����");
        }
        return rstMap;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    /**
     * ���ô�ӡ�������
     * @param  bo DzwszBO
     * @param userdata UserData
     * @param hjje BigDecimal
     * @return String
     * @throws BaseException
     */
    private DzwszBO setPrintBo(DzwszBO bo, BigDecimal hjje, UserData userdata) throws
        BaseException {
        //���ô�ӡ�������
        DzwszBO printbo = new DzwszBO();
        //���������
        printbo.setJsjdm(bo.getJsjdm());
        //˰Ʊ����
        printbo.setSphm(bo.getSphm());
        //������ˮ��
        printbo.setJylsh(bo.getJylsh());
        //�걨����
        printbo.setSbrq(bo.getSbrq());
        //���ջ���
        printbo.setSwjgzzjgmc(bo.getSwjgzzjgmc());
        //��˰������
        printbo.setNsrmc(bo.getNsrmc());
        //˰��Ǽ�֤��
        printbo.setSwdjzh(bo.getSwdjzh());
        //�տ�����
        printbo.setYhmc(bo.getYhmc());
        //��ע
        printbo.setBz1(bo.getBz1());
        printbo.setBz2(bo.getBz2());
        printbo.setBz3(bo.getBz3());
        String ndzb = "";
        String wszh = "";
        //�����˰֤��
        try {
            String retResult = ServiceProxy.getNumber(userdata, CodeConstant.SMSB_SWWSZM_PZZLDM,
                StringUtil.getDouble(deFormat.format(hjje), 0), "1", "1");
            ndzb = retResult.substring(0, 4); //����ֱ�
            wszh = retResult.substring(4); //��˰֤��
            
            System.out.println("����ֱ�"+ndzb);
            System.out.println("��˰֤��"+wszh);
            
            //ֹͣ1���ӣ���ֹ��һ�ε���ʱ����
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new ApplicationException("��ȡ��˰֤ʧ�ܣ�");
        }
        //��˰֤��
        printbo.setWszh(wszh);
        //����ֱ�
        printbo.setNdzb(ndzb);
        return printbo;
    }

    /**
     * ��ѯ��˰��������Ϣ
     * @param  bo DzwszBO
     * @param conn Connection
     * @param userdata UserData
     * @throws BaseException
     */
    private void SavePrintInfo(DzwszBO bo, Connection conn, UserData userdata) throws
        BaseException {
        try {
            //��ȡPreparedStatement
            String xh = getPrintSaveXh(conn);
            String sql = "INSERT INTO SBDB.SB_JL_DYHKWSZRZ "
                         + "(JSJDM, SPHM, JYLSH,DYRY, WSZH, NDZB, XH) "
                         + "VALUES ('" + bo.getJsjdm() + "', '" + bo.getSphm()
                         + "', '" + bo.getJylsh() + "', '" + userdata.getYhmc()
                         + "', '" + bo.getWszh() + "', '" + bo.getNdzb()
                         + "', " + xh + ")";
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);
            //�ر�pst
            stmt.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ��������ת��ר����˰֤��־����");
        }
    }
    
    /**
     *��Ȼ�˵Ǽ���Ϣ 
     * 201307
     * kanght
     */
    private HashMap getZRRInfo(String jsjdm, Connection conn) throws
    BaseException {
    	String strSql =
            "SELECT NSRMC FROM DJDB.DJ_JL_ZRRJBSJ WHERE JSJDM=?";
        String nsrmc = "";
        HashMap rstmap = new HashMap();
        try {
            //��ȡPreparedStatement
            PreparedStatement pst = conn.prepareStatement(strSql);
            //���ò�ѯ����Sphm
            pst.setString(1, jsjdm);
            //���ò�ѯ�������������
            ResultSet rs = pst.executeQuery();
            //ѭ��ȡ����ѯ���
            if (rs.next()) {
                //��ȡ��˰������
                nsrmc = rs.getString("NSRMC");
            }
            rstmap.put("nsrmc", nsrmc);
            //�ر�rs
            rs.close();
            //�ر�pst
            pst.close();
        } catch (SQLException sqlep) {
            sqlep.printStackTrace();
            //�׳�Ӧ���쳣
            throw new ApplicationException("��ȡ��Ȼ�����ƴ���");
        }
        return rstmap;
}
    //ɾ���ϴδ�ӡʧ����Ϣ
    //2013 kanght
    public void DeletePrintInfo(DzwszBO bo){
    	try {
    		System.out.println("ɾ���ϴδ�ӡʧ�ܵ�����");
			Connection conn = SfDBResource.getConnection();
			String sql = "delete from SBDB.SB_JL_DYHKWSZRZ where jsjdm = ? and sphm = ? and jylsh = ? ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, bo.getJsjdm());
			ps.setString(2, bo.getSphm());
			ps.setString(3, bo.getJylsh());
			ps.execute();
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    }
    
    
    
}

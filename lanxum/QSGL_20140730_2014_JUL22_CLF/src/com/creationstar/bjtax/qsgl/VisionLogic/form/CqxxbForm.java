package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;
import java.util.HashMap;

import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxErr;
import com.creationstar.bjtax.qsgl.BizLogic.vo.CqxxImportErrbvo;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Cqxxb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;

public class CqxxbForm extends QueryBaseForm {
    /**
     * ��������ѡ�� ����Ǩ�����ơ� ����Ǩ��֤�����롢 ����Ǩ��֤�����ͣ�ѡ�񣩡� ����Ǩ��ϸ��ַ�� ������Χ��
     * ¼��ʱ�䷶Χ��ʱ���ʽYYYYMMDD���� �����෵��1000��
     */
    private HashMap szqxMap = new HashMap(); // '��������'

    private String szqx; // '��������'

    private String bcqrmc; // '����Ǩ������'

    private String zjhm; // '֤������'

    private String bcqrlxdm; // '����Ǩ�����ʹ���'

    private String cqxxdz; // '��Ǩ��ϸ��ַ'

    private String bcjeq; // '���������'

    private String bcjez; // '�������ֹ'

    private String lrrqq; // '¼��������'

    private String lrrqz; // '¼������ֹ'

    private String cqxmmc; // ��Ǩ��Ŀ����

    private String sfwh; // '�Ƿ�ά����ѯ 0��ѯ1ά��' 00��������ά��Ȩ��

    private String cqxxbh; // ��Ǩ��Ϣ���

    private Cqxxb cqxx; // ��ӡʱ�����ӡ��һ����Ǩ����

    /**
     * ��ѡ�е������ڵ�ǰҳ�е�λ���������� ������������ʽ��
     */
    private Object testIs;

    /**
     * ��ѡ�е������ڵ�ǰҳ�е�λ���������� ��������û����ʽ��
     */

    private Object testNo;

    /**
     * ��ѡ�е������ڵ�ǰҳ�е�λ���������� ������������ʽ��
     */
    private int selectedIndexIs[];

    /**
     * ��ѡ�е������ڵ�ǰҳ�е�λ���������� ��������û����ʽ��
     */
    private int selectedIndexNo[];

    // ----------���ǻ����

    private String[] useIs;

    private String[] useNo;

    // ---------selectedIndexIs

    private String cqrmc_a[];

    private String cqxmmc_a[];

    private String cqxkzh_a[];

    private String cqfw_a[];

    private String bcqrmc_a[];

    private String zjhm_a[];

    private String cqxxdz_a[];

    private String zsfwjs_a[];

    private String cqmj_a[];

    private String gjrmc_a[];

    // ---------selectedIndexNo

    private String cqrmc_b[];

    private String cqxmmc_b[];

    private String cqxkzh_b[];

    private String cqfw_b[];

    private String bcqrmc_b[];

    private String zjhm_b[];

    private String cqxxdz_b[];

    private String zsfwjs_b[];

    private String cqmj_b[];

    private String gjrmc_b[];

    // ---------

    /**
     * ���ݸ���
     */
    private int size = 0; // ��ʱresultListErrIs��resultListErrNo����ʹ��

    /**
     * ���ݸ���
     */
    private int sizeErrIs = 0; // ��ʱresultListErrIs��resultListErrNo����ʹ��

    /**
     * ���ݸ���
     */
    private int sizeErrNo = 0; // ��ʱresultListErrIs��resultListErrNo����ʹ��

    /**
     * ��̨��ѯ�������Ľ���Ľ����(���е�)
     */
    private ArrayList resultListErr = new ArrayList();

    /**
     * ��̨��ѯ�������Ľ���Ľ����(���в�Ǩ��Ϣ) ������Ӧ����ȷ�Ľ���Ľ����
     */
    private ArrayList resultListErrIs = new ArrayList();

    /**
     * ��̨��ѯ�������Ľ���Ľ����(�޶�Ӧ��Ǩ��Ϣ)
     */
    private ArrayList resultListErrNo = new ArrayList();

    public CqxxbForm() {
        szqxMap.put("01", "����");
        szqxMap.put("02", "����");
        szqxMap.put("03", "����");
        szqxMap.put("04", "����");
        szqxMap.put("05", "����");
        szqxMap.put("06", "����");
        szqxMap.put("07", "��̨");
        szqxMap.put("08", "ʯ��ɽ");
        szqxMap.put("09", "��ͷ��");
        szqxMap.put("10", "��ɽ");
        szqxMap.put("11", "��ƽ");
        szqxMap.put("12", "ͨ��");
        szqxMap.put("13", "˳��");
        szqxMap.put("14", "����");
        szqxMap.put("15", "��ɽ");
        szqxMap.put("16", "����");
        szqxMap.put("17", "����");
        szqxMap.put("18", "ƽ��");
        szqxMap.put("19", "����");
        szqxMap.put("20", "������");
        szqxMap.put("21", "��վ");
        szqxMap.put("22", "����");
    }

    public Object getData() {
        Cqxxb cqxxb = new Cqxxb();
        cqxxb.setSzqx(this.szqx);
        cqxxb.setBcqrmc(this.bcqrmc);
        cqxxb.setZjhm(this.zjhm);
        cqxxb.setBcqrlxdm(this.bcqrlxdm);
        cqxxb.setCqxxdz(this.cqxxdz);
        cqxxb.setBcjeBegin(bcjeq);
        cqxxb.setBcjeEnd(this.bcjez);
        cqxxb.setLrrqBegin(this.lrrqq);
        cqxxb.setLrrqEnd(this.lrrqz);
        cqxxb.setSfwh(this.sfwh);
        cqxxb.setCqxmmc(this.getCqxmmc());
        return cqxxb;
    }

    public String getBcjeq() {
        return bcjeq;
    }

    public String getBcjez() {
        return bcjez;
    }

    public String getBcqrlxdm() {
        return bcqrlxdm;
    }

    public String getBcqrmc() {
        return bcqrmc;
    }

    public String getCqxxdz() {
        return cqxxdz;
    }

    public String getLrrqq() {
        return lrrqq;
    }

    public String getLrrqz() {
        return lrrqz;
    }

    public String getSzqx() {
        return szqx;
    }

    public String getZjhm() {
        return zjhm;
    }

    public HashMap getSzqxMap() {
        return szqxMap;
    }

    public String getCqxxbh() {
        return cqxxbh;
    }

    public Cqxxb getCqxx() {
        return cqxx;
    }

    public void setBcjeq(String bcjeq) {
        this.bcjeq = bcjeq;
    }

    public void setBcjez(String bcjez) {
        this.bcjez = bcjez;
    }

    public void setBcqrlxdm(String bcqrlxdm) {
        this.bcqrlxdm = bcqrlxdm;
    }

    public void setBcqrmc(String bcqrmc) {
        this.bcqrmc = bcqrmc;
    }

    public void setCqxxdz(String cqxxdz) {
        this.cqxxdz = cqxxdz;
    }

    public void setLrrqq(String lrrqq) {
        this.lrrqq = lrrqq;
    }

    public void setLrrqz(String lrrqz) {
        this.lrrqz = lrrqz;
    }

    public void setSzqx(String szqx) {
        this.szqx = szqx;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public void setSzqxMap(HashMap szqxMap) {
        this.szqxMap = szqxMap;
    }

    public void setCqxxbh(String cqxxbh) {
        this.cqxxbh = cqxxbh;
    }

    public void setCqxx(Cqxxb cqxx) {
        this.cqxx = cqxx;
    }

    /**
     * ����viewform
     */
    public void createViewForm() {

    }

    /**
     * @return Returns the sfwh.
     */
    public String getSfwh() {
        return sfwh;
    }

    /**
     * @param sfwh
     *            The sfwh to set.
     */
    public void setSfwh(String sfwh) {
        this.sfwh = sfwh;
    }

    public String getCqxmmc() {
        return cqxmmc;
    }

    public void setCqxmmc(String cqxmmc) {
        this.cqxmmc = cqxmmc;
    }

    /*
     * ���� Javadoc��
     *
     * @see com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm#clear()
     */
    public void clear() {
        super.clear();

        this.setSzqx(""); // '��������'

        this.setBcqrmc(""); // '����Ǩ������'

        this.setZjhm(""); // '֤������'

        this.setBcqrlxdm(""); // '����Ǩ�����ʹ���'

        this.setCqxxdz(""); // '��Ǩ��ϸ��ַ'

        this.setBcjeq(""); // '���������'

        this.setBcjez(""); // '�������ֹ'

        this.setLrrqq(""); // '¼��������'

        this.setLrrqz(""); // '¼������ֹ'

        this.setCqxmmc(""); // ��Ǩ��Ŀ����

        this.setSfwh(""); // '�Ƿ�ά����ѯ 0��ѯ1ά��'

        this.setCqxxbh(""); // ��Ǩ��Ϣ���

        this.setStatus("Query"); // Form��ǰ������״̬ ��û��ִ�в�ѯ��

        this.removeAll();
    }

    public ArrayList getResultListErrIs() {
        return resultListErrIs;
    }

    public void setResultListErrIs(ArrayList resultListErrIs) {
        this.resultListErrIs = resultListErrIs;
    }

    public ArrayList getResultListErrNo() {
        return resultListErrNo;
    }

    public void setResultListErrNo(ArrayList resultListErrNo) {
        this.resultListErrNo = resultListErrNo;
    }

    public ArrayList getResultListErr() {
        return resultListErr;
    }

    public void setResultListErr(ArrayList resultListErr) {
        this.resultListErr = resultListErr;
    }

    public int getSizeErrIs() {
        return sizeErrIs;
    }

    public void setSizeErrIs(int sizeErrIs) {
        this.sizeErrIs = sizeErrIs;
    }

    public int getSizeErrNo() {
        return sizeErrNo;
    }

    public void setSizeErrNo(int sizeErrNo) {
        this.sizeErrNo = sizeErrNo;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String[] getBcqrmc_a() {
        return bcqrmc_a;
    }

    public void setBcqrmc_a(String[] bcqrmc_a) {
        this.bcqrmc_a = bcqrmc_a;
    }

    public String[] getBcqrmc_b() {
        return bcqrmc_b;
    }

    public void setBcqrmc_b(String[] bcqrmc_b) {
        this.bcqrmc_b = bcqrmc_b;
    }

    public String[] getCqfw_a() {
        return cqfw_a;
    }

    public void setCqfw_a(String[] cqfw_a) {
        this.cqfw_a = cqfw_a;
    }

    public String[] getCqfw_b() {
        return cqfw_b;
    }

    public void setCqfw_b(String[] cqfw_b) {
        this.cqfw_b = cqfw_b;
    }

    public String[] getCqmj_a() {
        return cqmj_a;
    }

    public void setCqmj_a(String[] cqmj_a) {
        this.cqmj_a = cqmj_a;
    }

    public String[] getCqmj_b() {
        return cqmj_b;
    }

    public void setCqmj_b(String[] cqmj_b) {
        this.cqmj_b = cqmj_b;
    }

    public String[] getCqrmc_a() {
        return cqrmc_a;
    }

    public void setCqrmc_a(String[] cqrmc_a) {
        this.cqrmc_a = cqrmc_a;
    }

    public String[] getCqrmc_b() {
        return cqrmc_b;
    }

    public void setCqrmc_b(String[] cqrmc_b) {
        this.cqrmc_b = cqrmc_b;
    }

    public String[] getCqxkzh_a() {
        return cqxkzh_a;
    }

    public void setCqxkzh_a(String[] cqxkzh_a) {
        this.cqxkzh_a = cqxkzh_a;
    }

    public String[] getCqxkzh_b() {
        return cqxkzh_b;
    }

    public void setCqxkzh_b(String[] cqxkzh_b) {
        this.cqxkzh_b = cqxkzh_b;
    }

    public String[] getCqxmmc_a() {
        return cqxmmc_a;
    }

    public void setCqxmmc_a(String[] cqxmmc_a) {
        this.cqxmmc_a = cqxmmc_a;
    }

    public String[] getCqxmmc_b() {
        return cqxmmc_b;
    }

    public void setCqxmmc_b(String[] cqxmmc_b) {
        this.cqxmmc_b = cqxmmc_b;
    }

    public String[] getCqxxdz_a() {
        return cqxxdz_a;
    }

    public void setCqxxdz_a(String[] cqxxdz_a) {
        this.cqxxdz_a = cqxxdz_a;
    }

    public String[] getCqxxdz_b() {
        return cqxxdz_b;
    }

    public void setCqxxdz_b(String[] cqxxdz_b) {
        this.cqxxdz_b = cqxxdz_b;
    }

    public String[] getGjrmc_a() {
        return gjrmc_a;
    }

    public void setGjrmc_a(String[] gjrmc_a) {
        this.gjrmc_a = gjrmc_a;
    }

    public String[] getGjrmc_b() {
        return gjrmc_b;
    }

    public void setGjrmc_b(String[] gjrmc_b) {
        this.gjrmc_b = gjrmc_b;
    }

    public int[] getSelectedIndexIs() {
        return selectedIndexIs;
    }

    public void setSelectedIndexIs(int[] selectedIndexIs) {
        this.selectedIndexIs = selectedIndexIs;
    }

    public int[] getSelectedIndexNo() {
        return selectedIndexNo;
    }

    public void setSelectedIndexNo(int[] selectedIndexNo) {
        this.selectedIndexNo = selectedIndexNo;
    }

    public String[] getZjhm_a() {
        return zjhm_a;
    }

    public Object getTestIs() {
        return testIs;
    }

    public void setTestIs(Object testIs) {
        this.testIs = testIs;
    }

    public Object getTestNo() {
        return testNo;
    }

    public void setTestNo(Object testNo) {
        this.testNo = testNo;
    }

    public void setZjhm_a(String[] zjhm_a) {
        this.zjhm_a = zjhm_a;
    }

    public String[] getZjhm_b() {
        return zjhm_b;
    }

    public void setZjhm_b(String[] zjhm_b) {
        this.zjhm_b = zjhm_b;
    }

    public String[] getZsfwjs_a() {
        return zsfwjs_a;
    }

    public void setZsfwjs_a(String[] zsfwjs_a) {
        this.zsfwjs_a = zsfwjs_a;
    }

    public String[] getZsfwjs_b() {
        return zsfwjs_b;
    }

    public void setZsfwjs_b(String[] zsfwjs_b) {
        this.zsfwjs_b = zsfwjs_b;
    }

    public String[] getUseIs() {
        return useIs;
    }

    public void setUseIs(String[] useIs) {
        this.useIs = useIs;
    }

    public String[] getUseNo() {
        return useNo;
    }

    public void setUseNo(String[] useNo) {
        this.useNo = useNo;
    }

    /**
     * ��ȡѡ�е����� Is
     *
     *
     * @return ѡ�е����ݼ���
     */
    public HashMap getSelectedDataIs() {

        HashMap hash = new HashMap();

        // ��ȡѡ�е�����
        ArrayList currentPageData = this.getResultListErrIs();

        if (selectedIndexIs != null) {

            ArrayList listfg = new ArrayList();
            ArrayList listfq = new ArrayList();

            int size = selectedIndexIs.length;

            // ������Ǻ�ѡ��������Ҫһ��
            if (useIs.length == size) {

                for (int i = 0; i < size; i++) {
                    // ����
                    if (Constants.CQXXCWB_CWLX_02.equals(useIs[i])) {

                        // ȡ��Ԥ������ݣ����޸ĵ����ݽ�������
                        CqxxErr cqxxErrtmp = (CqxxErr) currentPageData
                                             .get(selectedIndexIs[i]);

                        Cqxxb cqxxbtmp = cqxxErrtmp.getCqxxb();

                        cqxxbtmp.setCqrmc(cqrmc_a[i]);

                        cqxxbtmp.setCqxmmc(cqxmmc_a[i]);

                        cqxxbtmp.setCqxkzh(cqxkzh_a[i]);

                        cqxxbtmp.setCqfw(cqfw_a[i]);

                        cqxxbtmp.setBcqrmc(bcqrmc_a[i]);

                        cqxxbtmp.setZjhm(zjhm_a[i]);

                        cqxxbtmp.setCqxxdz(cqxxdz_a[i]);

                        cqxxbtmp.setZsfwjs(zsfwjs_a[i]);

                        cqxxbtmp.setCqmj(DataConvert
                                         .String2BigDecimal(cqmj_a[i]));

                        cqxxbtmp.setGjrmc(gjrmc_a[i]);

                        cqxxErrtmp.setCqxxb(cqxxbtmp);

                        listfg.add(cqxxErrtmp);

                    }
                    // ����
                    else if (Constants.CQXXCWB_CWLX_01.equals(useIs[i])) {

                        listfq.add(currentPageData.get(selectedIndexIs[i]));

                    }

                }

                hash.put("fgIs", listfg);
                hash.put("fqIs", listfq);

                return hash;
            }

        }
        return null;
    }

    /**
     * ��ȡѡ�е����� No
     *
     *
     * @return ѡ�е����ݼ���
     */
    public HashMap getSelectedDataNo() {

        HashMap hash = new HashMap();

        // ��ȡѡ�е�����
        ArrayList currentPageData = this.getResultListErrNo();

        if (selectedIndexNo != null) {

            ArrayList listfg = new ArrayList();
            ArrayList listfq = new ArrayList();

            int size = selectedIndexNo.length;

            // ������Ǻ�ѡ��������Ҫһ��
            if (useNo.length == size) {

                for (int i = 0; i < size; i++) {
                    // ����
                    if (Constants.CQXXCWB_CWLX_02.equals(useNo[i])) {

                        // ȡ��Ԥ������ݣ����޸ĵ����ݽ�������
                        CqxxImportErrbvo cqxxberrTmp = (CqxxImportErrbvo)
                                currentPageData
                                .get(selectedIndexNo[i]);

                        cqxxberrTmp.setCqrmc(cqrmc_b[i]);

                        cqxxberrTmp.setCqxmmc(cqxmmc_b[i]);

                        cqxxberrTmp.setCqxkzh(cqxkzh_b[i]);

                        cqxxberrTmp.setCqfw(cqfw_b[i]);

                        cqxxberrTmp.setBcqrmc(bcqrmc_b[i]);

                        cqxxberrTmp.setZjhm(zjhm_b[i]);

                        cqxxberrTmp.setCqxxdz(cqxxdz_b[i]);

                        cqxxberrTmp.setZsfwjs(zsfwjs_b[i]);

                        cqxxberrTmp.setCqmj(cqmj_b[i]);

                        cqxxberrTmp.setGjrmc(gjrmc_b[i]);

                        listfg.add(cqxxberrTmp);

                    }
                    // ����
                    else if (Constants.CQXXCWB_CWLX_01.equals(useNo[i])) {

                        listfq.add(currentPageData.get(selectedIndexNo[i]));

                    }

                }

                hash.put("fgNo", listfg);
                hash.put("fqNo", listfq);

                return hash;
            }

        }
        return null;
    }

    public void deleteIs(ArrayList list) {

        if (resultListErrIs != null && list.size() > 0) {

            resultListErrIs.removeAll(list);
            resultListErr.removeAll(list);

            this.setSize(0);
            if (this.getResultListErr().size() > 0) {

                this.setSize(this.getResultListErr().size());

            }

            this.setSizeErrIs(0);
            if (this.getResultListErrIs().size() > 0) {

                this.setSizeErrIs(this.getResultListErrIs().size());

            }

            this.setSizeErrNo(0);
            if (this.getResultListErrNo().size() > 0) {

                this.setSizeErrNo(this.getResultListErrNo().size());

            }

        }

    }

    public void deleteNo(ArrayList list) {

        if (resultListErrNo != null && list.size() > 0) {

            resultListErrNo.removeAll(list);
            resultListErr.removeAll(list);

            this.setSize(0);
            if (this.getResultListErr().size() > 0) {

                this.setSize(this.getResultListErr().size());

            }

            this.setSizeErrIs(0);
            if (this.getResultListErrIs().size() > 0) {

                this.setSizeErrIs(this.getResultListErrIs().size());

            }

            this.setSizeErrNo(0);
            if (this.getResultListErrNo().size() > 0) {

                this.setSizeErrNo(this.getResultListErrNo().size());

            }

        }

    }

}


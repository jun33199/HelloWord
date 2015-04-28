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
     * 所在区县选择、 被拆迁人名称、 被拆迁人证件号码、 被拆迁人证件类型（选择）、 被拆迁详细地址、 补偿金额范围、
     * 录入时间范围（时间格式YYYYMMDD）。 结果最多返回1000条
     */
    private HashMap szqxMap = new HashMap(); // '所在区县'

    private String szqx; // '所在区县'

    private String bcqrmc; // '被拆迁人名称'

    private String zjhm; // '证件号码'

    private String bcqrlxdm; // '被拆迁人类型代码'

    private String cqxxdz; // '拆迁详细地址'

    private String bcjeq; // '补偿金额起'

    private String bcjez; // '补偿金额止'

    private String lrrqq; // '录入日期起'

    private String lrrqz; // '录入日期止'

    private String cqxmmc; // 拆迁项目名称

    private String sfwh; // '是否维护查询 0查询1维护' 00所有数据维护权限

    private String cqxxbh; // 拆迁信息编号

    private Cqxxb cqxx; // 打印时保存打印的一条拆迁数据

    /**
     * 被选中的数据在当前页中的位置索引数组 错误数据有正式表。
     */
    private Object testIs;

    /**
     * 被选中的数据在当前页中的位置索引数组 错误数据没有正式表。
     */

    private Object testNo;

    /**
     * 被选中的数据在当前页中的位置索引数组 错误数据有正式表。
     */
    private int selectedIndexIs[];

    /**
     * 被选中的数据在当前页中的位置索引数组 错误数据没有正式表。
     */
    private int selectedIndexNo[];

    // ----------覆盖或废弃

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
     * 数据个数
     */
    private int size = 0; // 暂时resultListErrIs和resultListErrNo个数使用

    /**
     * 数据个数
     */
    private int sizeErrIs = 0; // 暂时resultListErrIs和resultListErrNo个数使用

    /**
     * 数据个数
     */
    private int sizeErrNo = 0; // 暂时resultListErrIs和resultListErrNo个数使用

    /**
     * 后台查询导入错误的结果的结果集(所有的)
     */
    private ArrayList resultListErr = new ArrayList();

    /**
     * 后台查询导入错误的结果的结果集(已有拆迁信息) 包含对应的正确的结果的结果集
     */
    private ArrayList resultListErrIs = new ArrayList();

    /**
     * 后台查询导入错误的结果的结果集(无对应拆迁信息)
     */
    private ArrayList resultListErrNo = new ArrayList();

    public CqxxbForm() {
        szqxMap.put("01", "东城");
        szqxMap.put("02", "西城");
        szqxMap.put("03", "崇文");
        szqxMap.put("04", "宣武");
        szqxMap.put("05", "朝阳");
        szqxMap.put("06", "海淀");
        szqxMap.put("07", "丰台");
        szqxMap.put("08", "石景山");
        szqxMap.put("09", "门头沟");
        szqxMap.put("10", "燕山");
        szqxMap.put("11", "昌平");
        szqxMap.put("12", "通州");
        szqxMap.put("13", "顺义");
        szqxMap.put("14", "大兴");
        szqxMap.put("15", "房山");
        szqxMap.put("16", "怀柔");
        szqxMap.put("17", "密云");
        szqxMap.put("18", "平谷");
        szqxMap.put("19", "延庆");
        szqxMap.put("20", "开发区");
        szqxMap.put("21", "西站");
        szqxMap.put("22", "涉外");
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
     * 生成viewform
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
     * （非 Javadoc）
     *
     * @see com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm#clear()
     */
    public void clear() {
        super.clear();

        this.setSzqx(""); // '所在区县'

        this.setBcqrmc(""); // '被拆迁人名称'

        this.setZjhm(""); // '证件号码'

        this.setBcqrlxdm(""); // '被拆迁人类型代码'

        this.setCqxxdz(""); // '拆迁详细地址'

        this.setBcjeq(""); // '补偿金额起'

        this.setBcjez(""); // '补偿金额止'

        this.setLrrqq(""); // '录入日期起'

        this.setLrrqz(""); // '录入日期止'

        this.setCqxmmc(""); // 拆迁项目名称

        this.setSfwh(""); // '是否维护查询 0查询1维护'

        this.setCqxxbh(""); // 拆迁信息编号

        this.setStatus("Query"); // Form当前所处的状态 还没有执行查询。

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
     * 获取选中的数据 Is
     *
     *
     * @return 选中的数据集合
     */
    public HashMap getSelectedDataIs() {

        HashMap hash = new HashMap();

        // 获取选中的数据
        ArrayList currentPageData = this.getResultListErrIs();

        if (selectedIndexIs != null) {

            ArrayList listfg = new ArrayList();
            ArrayList listfq = new ArrayList();

            int size = selectedIndexIs.length;

            // 操作标记和选择标记数量要一致
            if (useIs.length == size) {

                for (int i = 0; i < size; i++) {
                    // 覆盖
                    if (Constants.CQXXCWB_CWLX_02.equals(useIs[i])) {

                        // 取出预存的数据，用修改的数据进行修正
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
                    // 废弃
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
     * 获取选中的数据 No
     *
     *
     * @return 选中的数据集合
     */
    public HashMap getSelectedDataNo() {

        HashMap hash = new HashMap();

        // 获取选中的数据
        ArrayList currentPageData = this.getResultListErrNo();

        if (selectedIndexNo != null) {

            ArrayList listfg = new ArrayList();
            ArrayList listfq = new ArrayList();

            int size = selectedIndexNo.length;

            // 操作标记和选择标记数量要一致
            if (useNo.length == size) {

                for (int i = 0; i < size; i++) {
                    // 覆盖
                    if (Constants.CQXXCWB_CWLX_02.equals(useNo[i])) {

                        // 取出预存的数据，用修改的数据进行修正
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
                    // 废弃
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


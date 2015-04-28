package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Drzb;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;
import com.creationstar.bjtax.qsgl.model.bo.PlsbBo;
import com.creationstar.bjtax.qsgl.util.QsglPcclXmlUtil;
import com.ttsoft.common.util.Debug;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * 批量受理的导入查询form
 */
public class QueryPlslForm extends QueryBaseForm {
    /**
     *查询条件
     */
    private String drpch;
    /**
     *查询条件
     */
    private String selcfdr = "";
    /**
     *导入类型0:新导入1：重复导入
     */
    private String drlx = "";

    /**
     *受理标志位
     */
    private String sl = "all";

    /**
     * 提供者名称
     */
    private String tgzmc = "";

    /**
     * 提供者计算机代码
     */
    private String tgzjsjdm = "";

    /**
     * 导入日期
     */
    private String drrq = "";


    /**
     * 入口信息 dr--从导入页面进入查询页面 cx--从查询批量信息页面进入此页面
     */
    private String from = "dr";

    /**
     * 后台查询结果的结果集
     */
    private ArrayList resultList = new ArrayList();

    /**
     * 要删除的单条数据
     */
    private PlsbBo plBo;


    /**
     * 以导入数据
     */
    private ArrayList drsj;
    /**
     *  已导入数据个数
     */
    private int size = 0;
    /**
     *  导入标识
     */
    private String drbs = "0";


    /**
     *
     * 注意类型是FormFile
     * */
    private FormFile mofile;
    private String mofile_desc;
    //是否导入成功
    private boolean mbIsSucceed = true;
    //格式不对的数据集合
    private ArrayList mlErrRecords;
    /**
     * 缴款方式
     */
    public ArrayList jkfsList = new ArrayList();
    public String jkfsdm = "01";
    public String jkfsmc;

    //导入批次号
    private String msDrpch;
    //补录的导入批次号
    private String[] msBLDrpchs = {
            "1", "2"};
    private String msBLDrpch;

    public ActionErrors validate(ActionMapping actionMapping,
                                 HttpServletRequest httpServletRequest) {

        return null;
    }

    public void reset(ActionMapping actionMapping,
                      HttpServletRequest servletRequest) {

    }

    public FormFile getMofile() {
        return mofile;
    }


    public String getMofile_desc() {
        return mofile_desc;
    }

    public String getSelcfdr() {
        return selcfdr;
    }

    public String getDrlx() {
        return drlx;
    }

    public boolean isMbIsSucceed() {
        return mbIsSucceed;
    }

    public ArrayList getMlErrRecords() {
        return mlErrRecords;
    }

    public String getMsDrpch() {
        return msDrpch;
    }

    public String[] getMsBLDrpchs() {
        return msBLDrpchs;
    }

    public String getMsBLDrpch() {
        return msBLDrpch;
    }


    public void setMofile(FormFile mofile) {
        this.mofile = mofile;
    }

    public void setMofile_desc(String mofile_desc) {
        this.mofile_desc = mofile_desc;
    }

    public void setMbIsSucceed(boolean mbIsSucceed) {
        this.mbIsSucceed = mbIsSucceed;
    }

    public void setMlErrRecords(ArrayList mlErrRecords) {
        this.mlErrRecords = mlErrRecords;
    }

    public void setMsDrpch(String msDrpch) {
        this.msDrpch = msDrpch;
    }

    public void setMsBLDrpchs(String[] msBLDrpchs) {
        this.msBLDrpchs = msBLDrpchs;
    }

    public void setMsBLDrpch(String msBLDrpch) {
        this.msBLDrpch = msBLDrpch;
    }

    public void setSelcfdr(String selcfdr) {
        this.selcfdr = selcfdr;
    }

    public void setDrlx(String drlx) {
        this.drlx = drlx;
    }

    public ArrayList getResultList() {
        return resultList;
    }


    public ArrayList getDrsj() {
        return drsj;
    }

    public void setResultList(ArrayList resultList) {
        this.resultList = resultList;
    }

    public void setDrsj(ArrayList drsj) {
        this.drsj = drsj;
    }

    /**
     * 生成viewform
     */
    public void createViewForm() {
        //生成GnForm.
        PlslForm aForm = new PlslForm();
        Drzb zb = new Drzb();
        //从查询结果中获取bo,从bo中得到选择记录中的信息
        if (from.equals("dr")) {
            zb = (Drzb) queryCache.getDataDetail(viewIndex);
        }
        if (from.equals("cx")) {
            PlsbBo plsbBo = (PlsbBo) queryCache.getDataDetail(viewIndex);
            this.plBo = plsbBo;
            zb = plsbBo.getDrzb();
        }
        PldrBo bo = new PldrBo();
        try {

            bo = (PldrBo) QsglPcclXmlUtil.getRecord(zb.getDrsjnr());
        } catch (Exception ex) {
            Debug.out("queryPlslForm中获取viewform时解析xml出错");
            ex.printStackTrace();
        }

        aForm.setCqxxList(bo.getCqxxList());
        aForm.setFgrxx(bo.getFgrxx());
        aForm.setFwjhxxList(bo.getFwjhxxList());
        aForm.setGrxx(bo.getGrxx());
        aForm.setGyzfxxList(bo.getGyzfxxList());
        aForm.setSpjgxx(bo.getSpjgxx());
        aForm.setTufwxx(bo.getTufwxx());
        aForm.setGrxxList(bo.getGrxxList());
        aForm.setFgrxxList(bo.getFgrxxList());
        //税额调整类型
        aForm.setSetz(bo.getSetz());
        if (bo.getGrxx() != null) {
            //个人
            aForm.setPerson("1");
        } else {
            //非个人
            aForm.setPerson("0");
        }
        if (bo.getSpjgxx() != null) {
            //有审批
            aForm.setSp("1");
        } else {
            //无审批
            aForm.setSp("0");
        }
        if (bo.getCqxxList() != null && bo.getCqxxList().size() > 0) {
            //有拆迁
            aForm.setCq("1");
        } else {
            //无拆迁
            aForm.setCq("0");
        }
        if (bo.getGyzfxxList() != null && bo.getGyzfxxList().size() > 0) {
            //有公房
            aForm.setGf("1");
        } else {
            //无公房
            aForm.setGf("0");
        }
        if (bo.getFwjhxxList() != null && bo.getFwjhxxList().size() > 0) {
            //有房交
            aForm.setFj("1");
        } else {
            //无房交
            aForm.setFj("0");
        }
        if (bo.getTufwxx().getFwtdbmdm() != null &&
            !bo.getTufwxx().getFwtdbmdm().equals("")) {
            aForm.setTdbm("1");
        } else {
            aForm.setTdbm("0");
        }
        Debug.out("isPerson" + aForm.getPerson());
        Debug.out("IsCq" + aForm.getCq());
        Debug.out("IsFj" + aForm.getFj());
        Debug.out("IsGf" + aForm.getGf());
        Debug.out("IsSp" + aForm.getSp());
        Debug.out("IsTd" + aForm.getTdbm());

        viewForm = aForm;
    }

    public int getSize() {
        return size;
    }

    public String getDrbs() {
        return drbs;
    }

    public String getDrpch() {
        return drpch;
    }

    public String getSl() {
        return sl;
    }

    public String getFrom() {
        return from;
    }

    public PlsbBo getPlBo() {
        return plBo;
    }

    public String getJkfsdm() {
        return jkfsdm;
    }

    public ArrayList getJkfsList() {
        return jkfsList;
    }

    public String getJkfsmc() {
        return jkfsmc;
    }

    public String getDrrq() {
        return drrq;
    }

    public String getTgzjsjdm() {
        return tgzjsjdm;
    }


    public String getTgzmc() {
        return tgzmc;
    }


    public void setSize(int size) {
        this.size = size;
    }

    public void setDrbs(String drbs) {
        this.drbs = drbs;
    }

    public void setDrpch(String drpch) {
        this.drpch = drpch;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setPlBo(PlsbBo plBo) {
        this.plBo = plBo;
    }

    public void setJkfsdm(String jkfsdm) {
        this.jkfsdm = jkfsdm;
    }

    public void setJkfsList(ArrayList jkfsList) {
        this.jkfsList = jkfsList;
    }

    public void setJkfsmc(String jkfsmc) {
        this.jkfsmc = jkfsmc;
    }

    public void setDrrq(String drrq) {
        this.drrq = drrq;
    }

    public void setTgzjsjdm(String tgzjsjdm) {
        this.tgzjsjdm = tgzjsjdm;
    }

    public void setTgzmc(String tgzmc) {
        this.tgzmc = tgzmc;
    }

    /**
     * 清空用函数
     */
    public void clear() {
        this.setDrpch("");
        this.setNsrmc("");
        this.setSl("all");
        this.setDrrq("");
        this.setTgzjsjdm("");
        this.setTgzmc("");
        this.setFrom("cx");
        this.setDrbs("0");
    }

    /**
     * 实现父类定义的方法，将 QueryForm中的数据放入到相应的 BO 类中，以完成框架
     * @return Object
     */
    public Object getData() {
        Debug.out("into QueryPlslForm....");
        PlsbBo bo = new PlsbBo();
        bo.setDrpch(this.drpch);
        bo.setNsrmc(this.nsrmc);
        bo.setTgzjsjdm(this.tgzjsjdm);
        bo.setTgzmc(this.tgzmc);
        //导入日期
        bo.setDrsj(this.drrq);
        bo.setSl(this.sl);
        bo.setJsfsdm(this.jkfsdm);
        bo.setDrbs(this.drbs);
        return bo;
    }
}

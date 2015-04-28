package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.QueryBaseForm;
import com.creationstar.bjtax.qsgl.model.bo.JksBo;
import com.creationstar.bjtax.qsgl.model.bo.QueryBlJksBo;

/**
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
public class BlJksForm extends QueryBaseForm {
    //查询需要补录的缴款书的查询条件
    private String jsje;
    private String sjse;

    //需要补录的缴款书ARRAYLIST(每个元素为QueryBlJksBo)
    private ArrayList jksSbList;
    private QueryBlJksBo removeBo;

    //view缴款书页面每条要显示的内容bo
    public JksBo jksBo;
    public String type; //此缴款书的类型，1代表普通，2代表调帐的
    public String zbxh; //主表序号，调帐的缴款书要用到
    public String sklxdm; //税款类型代码

    //以下为撤消缴款书所用的查询条件,type也需要使用
    public String scfs; //缴款书生成方式
    public String jkpzh; //缴款凭证号
    public String re_jkpzh; //确认缴款凭证号
    //查询结果bo
    public QueryBlJksBo resultBo;

    //获取查询条件
    public QueryBlJksBo getBo() {
        QueryBlJksBo bo = new QueryBlJksBo();
        bo.setJkpzh(this.jkpzh);
        bo.setSjly(this.scfs);
        bo.setType(this.getType());
        return bo;
    }


    public ArrayList getJksSbList() {
        return jksSbList;
    }

    public JksBo getJksBo() {
        return jksBo;
    }

    public String getType() {
        return type;
    }

    public String getJkpzh() {
        return jkpzh;
    }

    public String getRe_jkpzh() {
        return re_jkpzh;
    }

    public String getScfs() {
        return scfs;
    }

    public String getZbxh() {
        return zbxh;
    }

    public QueryBlJksBo getResultBo() {
        return resultBo;
    }

    public String getSklxdm() {
        return sklxdm;
    }

    public String getJsje() {
        return jsje;
    }

    public String getSjse() {
        return sjse;
    }

    public QueryBlJksBo getRemoveBo() {
        return removeBo;
    }

    public void setJksSbList(ArrayList jksSbList) {
        this.jksSbList = jksSbList;
    }

    public void setJksBo(JksBo jksBo) {
        this.jksBo = jksBo;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setJkpzh(String jkpzh) {
        this.jkpzh = jkpzh;
    }

    public void setRe_jkpzh(String re_jkpzh) {
        this.re_jkpzh = re_jkpzh;
    }

    public void setScfs(String scfs) {
        this.scfs = scfs;
    }

    public void setZbxh(String zbxh) {
        this.zbxh = zbxh;
    }

    public void setResultBo(QueryBlJksBo resultBo) {
        this.resultBo = resultBo;
    }

    public void setSklxdm(String sklxdm) {
        this.sklxdm = sklxdm;
    }

    public void setJsje(String jsje) {
        this.jsje = jsje;
    }

    public void setSjse(String sjse) {
        this.sjse = sjse;
    }

    public void setRemoveBo(QueryBlJksBo removeBo) {
        this.removeBo = removeBo;
    }

}

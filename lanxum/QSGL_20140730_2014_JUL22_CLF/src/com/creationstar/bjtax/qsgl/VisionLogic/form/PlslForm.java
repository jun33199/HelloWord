package com.creationstar.bjtax.qsgl.VisionLogic.form;

import java.util.ArrayList;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Spjgxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.form.Base.BaseForm;
import com.creationstar.bjtax.qsgl.model.bo.PldrBo;

/**
 * 显示某条倒入的数据详细信息
 *
 * @author not attributable
 * @version 1.0
 */
public class PlslForm extends BaseForm {
    //显示某条申报的detail信息
    //plsbBO对象
    private PldrBo bo;
    //个人信息
    private Grxx grxx;
    //非个人信息
    private Fgrxx fgrxx;
    //审批结果信息
    private Spjgxx spjgxx;
    //土地房屋信息
    private Tufwxx tufwxx;
    //拆迁信息(Arryalist中的每个元素都是jsblcq对象)
    private ArrayList cqxxList;
    //公有住房信息(Arryalist中的每个元素都是jsblgyzf对象)
    private ArrayList gyzfxxList;
    //房屋交换信息(Arryalist中的每个元素都是PldrBo对象,但是没有拆迁和公房)
    private ArrayList fwjhxxList;
    //个人非个人(0,1)
    private String person;
    //有无拆迁
    private String cq;
    //有无共有住房
    private String gf;
    //有无房屋交换
    private String fj;
    //有无审批减免
    private String sp;
    //是否有土地房屋部门受理号
    private String tdbm;
    //是否允许删除此条记录 0--不许 1--允许
    private String del = "0";
    //察看明细页面返回那个页面的标示
    private String back = "dr";
    //税额调整
    private String setz;

    //个人的多产权人
    public ArrayList grxxList = new ArrayList();
    //非个人的多产权人
    public ArrayList fgrxxList = new ArrayList();


    public void setGrxxList(ArrayList grxxList) {
        this.grxxList = grxxList;
    }

    public ArrayList getGrxxList() {
        return grxxList;
    }

    public void setFgrxxList(ArrayList fgrxxList) {
        this.fgrxxList = fgrxxList;
    }

    public ArrayList getFgrxxList() {
        return fgrxxList;
    }

    public String getSetz() {
        return setz;
    }

    public void setSetz(String setz) {
        this.setz = setz;
    }

    public PldrBo getBo() {
        return bo;
    }


    public ArrayList getCqxxList() {
        return cqxxList;
    }

    public Fgrxx getFgrxx() {
        return fgrxx;
    }

    public ArrayList getFwjhxxList() {
        return fwjhxxList;
    }

    public Grxx getGrxx() {
        return grxx;
    }

    public ArrayList getGyzfxxList() {
        return gyzfxxList;
    }


    public Spjgxx getSpjgxx() {
        return spjgxx;
    }

    public Tufwxx getTufwxx() {
        return tufwxx;
    }

    public String getCq() {
        return cq;
    }

    public String getFj() {
        return fj;
    }

    public String getGf() {
        return gf;
    }

    public String getSp() {
        return sp;
    }

    public String getPerson() {
        return person;
    }

    public String getTdbm() {
        return tdbm;
    }


    public String getDel() {
        return del;
    }

    public String getBack() {
        return back;
    }


    public void setBo(PldrBo bo) {
        this.bo = bo;
    }


    public void setCqxxList(ArrayList cqxxList) {
        this.cqxxList = cqxxList;
    }

    public void setFgrxx(Fgrxx fgrxx) {
        this.fgrxx = fgrxx;
    }

    public void setFwjhxxList(ArrayList fwjhxxList) {
        this.fwjhxxList = fwjhxxList;
    }

    public void setGrxx(Grxx grxx) {
        this.grxx = grxx;
    }

    public void setGyzfxxList(ArrayList gyzfxxList) {
        this.gyzfxxList = gyzfxxList;
    }


    public void setSpjgxx(Spjgxx spjgxx) {
        this.spjgxx = spjgxx;
    }

    public void setTufwxx(Tufwxx tufwxx) {
        this.tufwxx = tufwxx;
    }

    public void setCq(String cq) {
        this.cq = cq;
    }

    public void setFj(String fj) {
        this.fj = fj;
    }

    public void setGf(String gf) {
        this.gf = gf;
    }

    public void setSp(String sp) {
        this.sp = sp;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public void setTdbm(String tdbm) {
        this.tdbm = tdbm;
    }


    public void setDel(String del) {
        this.del = del;
    }

    public void setBack(String back) {
        this.back = back;
    }
}

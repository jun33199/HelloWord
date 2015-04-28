/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.2</a>, using an XML
 * Schema.
 * $Id$
 */

package com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;

/**
 * 纳税人扩展信息
 * 
 * @version $Revision$ $Date$
 */
public class HzsbdwnsrKzxx implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * 纳税人名称英文
     */
    private java.lang.String _nsrmcYw;

    /**
     * 工商户标志Y：工商户。N：非工商户
     */
    private java.lang.String _gshbz;

    /**
     * 法人标志Y：法人。N：非法人
     */
    private java.lang.String _frbz;

    /**
     * 注册地址
     */
    private java.lang.String _zcdz;

    /**
     * 注册地址邮编
     */
    private java.lang.String _zcdYb;

    /**
     * 生产经营地邮编
     */
    private java.lang.String _scjydYb;

    /**
     * 电子邮件地址
     */
    private java.lang.String _dydz;

    /**
     * 工商开业日期
     */
    private java.lang.String _gskyrq;

    /**
     * 办税人证件类型
     */
    private java.lang.String _bsrZjlDm;

    /**
     * 办税人证件号码
     */
    private java.lang.String _bsrZjhm;

    /**
     * 技术监督局代码(组织机构代码)
     */
    private java.lang.String _zzjgdm;

    /**
     * 工商登记机关名称
     */
    private java.lang.String _gsdjjgMc;

    /**
     * 执照类别代码
     */
    private java.lang.String _zzlbDm;

    /**
     * 工商登记执照号
     */
    private java.lang.String _gsdjzzh;

    /**
     * 有效期起
     */
    private java.lang.String _yxqQ;

    /**
     * 有效期止
     */
    private java.lang.String _yxqZ;

    /**
     * 主营
     */
    private java.lang.String _zy;

    /**
     * 兼营
     */
    private java.lang.String _jy;

    /**
     * 经营方式
     */
    private java.lang.String _jyfs;

    /**
     * 生产经营期限起
     */
    private java.lang.String _scjyqxQ;

    /**
     * 生产经营期限止
     */
    private java.lang.String _scjyqxZ;

    /**
     * 工商发照日期
     */
    private java.lang.String _gsfzrq;

    /**
     * 注册资本
     */
    private java.lang.String _zczb = "0.00";

    /**
     * 注册资本币种
     */
    private java.lang.String _zcbzDm;

    /**
     * 投资总额
     */
    private java.lang.String _tzze = "0.00";

    /**
     * 投资币种代码
     */
    private java.lang.String _tzbzDm;

    /**
     * 从业人员
     */
    private java.lang.String _cyrs = "0";

    /**
     * 外籍人员人数
     */
    private java.lang.String _wjrs = "0";

    /**
     * 业主住址
     */
    private java.lang.String _yzzz;

    /**
     * 主管单位
     */
    private java.lang.String _zgdw;

    /**
     * 经营服务单位
     */
    private java.lang.String _jyfwdw;

    /**
     * 总机构业别
     */
    private java.lang.String _zjgyb;

    /**
     * 批准机关
     */
    private java.lang.String _pzjg;

    /**
     * 批准文号
     */
    private java.lang.String _pzwh;

    /**
     * 批准日期
     */
    private java.lang.String _pzrq;

    /**
     * 在华资产情况
     */
    private java.lang.String _zhzcqk;

    /**
     * 筹建期起
     */
    private java.lang.String _cjqQ;

    /**
     * 筹建期止
     */
    private java.lang.String _cjqZ;

    /**
     * 货物存放处
     */
    private java.lang.String _hwcfc;

    /**
     * 货物存放面积
     */
    private java.lang.String _hwcfdmj;

    /**
     * 财务负责人名称
     */
    private java.lang.String _cwfzrmc;

    /**
     * 财务报表种类
     */
    private java.lang.String _cwbbzl;

    /**
     * 固定资产折旧方式
     */
    private java.lang.String _gdzczjfs;

    /**
     * 低值易耗品摊销方法
     */
    private java.lang.String _yhptxff;

    /**
     * 会计年度
     */
    private java.lang.String _kjnd;

    /**
     * 记帐本位币
     */
    private java.lang.String _jzbwbDm;

    /**
     * 建帐类别
     */
    private java.lang.String _jzlb;

    /**
     * 记帐方式
     */
    private java.lang.String _jzfs;

    /**
     * 企业结账日期
     */
    private java.lang.String _jzrq = "0";

    /**
     * 税务登记证发证日期
     */
    private java.lang.String _fzrq;

    /**
     * 电话号码
     */
    private java.lang.String _zcdDhhm;

    /**
     * 核算方式代码
     */
    private java.lang.String _hsfsDm;

    /**
     * 适用会计制度代码
     */
    private java.lang.String _sykjzdDm;

    /**
     * 网站网址
     */
    private java.lang.String _wzwz;

    /**
     * 法人电话号
     */
    private java.lang.String _frDhhm;

    /**
     * 法人移动电话号码
     */
    private java.lang.String _frYddhhm;

    /**
     * 法人电子邮箱地址
     */
    private java.lang.String _frEmail;

    /**
     * 财务负责人证件类型代码
     */
    private java.lang.String _cwfzrZjlxDm;

    /**
     * 财务负责人证件号码
     */
    private java.lang.String _cwfzrZjhm;

    /**
     * 财务负责人电话号
     */
    private java.lang.String _cwfzrDhhm;

    /**
     * 财务负责人移动电话号码
     */
    private java.lang.String _cwfzrYddhhm;

    /**
     * 财务负责人电子邮箱地址
     */
    private java.lang.String _cwfzrEmail;

    /**
     * 办税人电话号
     */
    private java.lang.String _bsrDhhm;

    /**
     * 办税人移动电话号码
     */
    private java.lang.String _bsrYddhhm;

    /**
     * 办税人电子邮箱地址
     */
    private java.lang.String _bsrEmail;

    /**
     * 财务负责人证件号码
     */
    private java.lang.String _swdlrmc;

    /**
     * 税务代理人纳税人识别号
     */
    private java.lang.String _swdlrNsrsbh;

    /**
     * 税务代理人电话号码
     */
    private java.lang.String _swdlrDhhm;

    /**
     * 税务代理人电子邮箱地址
     */
    private java.lang.String _swdlrEmail;

    /**
     * 地税主管税务机关（局）
     */
    private java.lang.String _dszgswjgJDm;

    /**
     * 地税主管税务机关代码
     */
    private java.lang.String _dszgswjgDm;

    /**
     * 合伙人数
     */
    private java.lang.String _hhrs;

    /**
     * 固定人数
     */
    private java.lang.String _gdrs;

    /**
     * 自然人投资比例
     */
    private java.lang.String _tzblZrr;

    /**
     * 外资投资比例
     */
    private java.lang.String _tzblWz;

    /**
     * 国有投资比例
     */
    private java.lang.String _tzblGy;

    /**
     * 附报资料
     */
    private java.lang.Object _fbzl;

    /**
     * 附属行业1代码
     */
    private java.lang.String _fshy1Dm;

    /**
     * 附属行业2代码
     */
    private java.lang.String _fshy2Dm;

    /**
     * 附属行业3代码
     */
    private java.lang.String _fshy3Dm;

    /**
     * 经营范围
     */
    private java.lang.String _jyfw;

    /**
     * 开业设立日期
     */
    private java.lang.String _kyslrq;

    /**
     * 纳税义务发生日期
     */
    private java.lang.String _nsywfsrq;

    /**
     * 漏管户标志
     */
    private java.lang.String _lghbz;

    /**
     * 信用社标志
     */
    private java.lang.String _xysbz;

    /**
     * 自收自支事业单位标志
     */
    private java.lang.String _zszzsydwbz;

    /**
     * 附属行业1明细代码
     */
    private java.lang.String _fshy1Hymxdm;

    /**
     * 附属行业2明细代码
     */
    private java.lang.String _fshy2Hymxdm;

    /**
     * 附属行业3明细代码
     */
    private java.lang.String _fshy3Hymxdm;


      //----------------/
     //- Constructors -/
    //----------------/

    public HzsbdwnsrKzxx() {
        super();
        setZczb("0.00");
        setTzze("0.00");
        setCyrs("0");
        setWjrs("0");
        setJzrq("0");
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'bsrDhhm'. The field 'bsrDhhm'
     * has the following description: 办税人电话号
     * 
     * @return the value of field 'BsrDhhm'.
     */
    public java.lang.String getBsrDhhm(
    ) {
        return this._bsrDhhm;
    }

    /**
     * Returns the value of field 'bsrEmail'. The field 'bsrEmail'
     * has the following description: 办税人电子邮箱地址
     * 
     * @return the value of field 'BsrEmail'.
     */
    public java.lang.String getBsrEmail(
    ) {
        return this._bsrEmail;
    }

    /**
     * Returns the value of field 'bsrYddhhm'. The field
     * 'bsrYddhhm' has the following description: 办税人移动电话号码
     * 
     * @return the value of field 'BsrYddhhm'.
     */
    public java.lang.String getBsrYddhhm(
    ) {
        return this._bsrYddhhm;
    }

    /**
     * Returns the value of field 'bsrZjhm'. The field 'bsrZjhm'
     * has the following description: 办税人证件号码
     * 
     * @return the value of field 'BsrZjhm'.
     */
    public java.lang.String getBsrZjhm(
    ) {
        return this._bsrZjhm;
    }

    /**
     * Returns the value of field 'bsrZjlDm'. The field 'bsrZjlDm'
     * has the following description: 办税人证件类型
     * 
     * @return the value of field 'BsrZjlDm'.
     */
    public java.lang.String getBsrZjlDm(
    ) {
        return this._bsrZjlDm;
    }

    /**
     * Returns the value of field 'cjqQ'. The field 'cjqQ' has the
     * following description: 筹建期起
     * 
     * @return the value of field 'CjqQ'.
     */
    public java.lang.String getCjqQ(
    ) {
        return this._cjqQ;
    }

    /**
     * Returns the value of field 'cjqZ'. The field 'cjqZ' has the
     * following description: 筹建期止
     * 
     * @return the value of field 'CjqZ'.
     */
    public java.lang.String getCjqZ(
    ) {
        return this._cjqZ;
    }

    /**
     * Returns the value of field 'cwbbzl'. The field 'cwbbzl' has
     * the following description: 财务报表种类
     * 
     * @return the value of field 'Cwbbzl'.
     */
    public java.lang.String getCwbbzl(
    ) {
        return this._cwbbzl;
    }

    /**
     * Returns the value of field 'cwfzrDhhm'. The field
     * 'cwfzrDhhm' has the following description: 财务负责人电话号
     * 
     * @return the value of field 'CwfzrDhhm'.
     */
    public java.lang.String getCwfzrDhhm(
    ) {
        return this._cwfzrDhhm;
    }

    /**
     * Returns the value of field 'cwfzrEmail'. The field
     * 'cwfzrEmail' has the following description: 财务负责人电子邮箱地址
     * 
     * @return the value of field 'CwfzrEmail'.
     */
    public java.lang.String getCwfzrEmail(
    ) {
        return this._cwfzrEmail;
    }

    /**
     * Returns the value of field 'cwfzrYddhhm'. The field
     * 'cwfzrYddhhm' has the following description: 财务负责人移动电话号码
     * 
     * @return the value of field 'CwfzrYddhhm'.
     */
    public java.lang.String getCwfzrYddhhm(
    ) {
        return this._cwfzrYddhhm;
    }

    /**
     * Returns the value of field 'cwfzrZjhm'. The field
     * 'cwfzrZjhm' has the following description: 财务负责人证件号码
     * 
     * @return the value of field 'CwfzrZjhm'.
     */
    public java.lang.String getCwfzrZjhm(
    ) {
        return this._cwfzrZjhm;
    }

    /**
     * Returns the value of field 'cwfzrZjlxDm'. The field
     * 'cwfzrZjlxDm' has the following description: 财务负责人证件类型代码
     * 
     * @return the value of field 'CwfzrZjlxDm'.
     */
    public java.lang.String getCwfzrZjlxDm(
    ) {
        return this._cwfzrZjlxDm;
    }

    /**
     * Returns the value of field 'cwfzrmc'. The field 'cwfzrmc'
     * has the following description: 财务负责人名称
     * 
     * @return the value of field 'Cwfzrmc'.
     */
    public java.lang.String getCwfzrmc(
    ) {
        return this._cwfzrmc;
    }

    /**
     * Returns the value of field 'cyrs'. The field 'cyrs' has the
     * following description: 从业人员
     * 
     * @return the value of field 'Cyrs'.
     */
    public java.lang.String getCyrs(
    ) {
        return this._cyrs;
    }

    /**
     * Returns the value of field 'dszgswjgDm'. The field
     * 'dszgswjgDm' has the following description: 地税主管税务机关代码
     * 
     * @return the value of field 'DszgswjgDm'.
     */
    public java.lang.String getDszgswjgDm(
    ) {
        return this._dszgswjgDm;
    }

    /**
     * Returns the value of field 'dszgswjgJDm'. The field
     * 'dszgswjgJDm' has the following description: 地税主管税务机关（局）
     * 
     * @return the value of field 'DszgswjgJDm'.
     */
    public java.lang.String getDszgswjgJDm(
    ) {
        return this._dszgswjgJDm;
    }

    /**
     * Returns the value of field 'dydz'. The field 'dydz' has the
     * following description: 电子邮件地址
     * 
     * @return the value of field 'Dydz'.
     */
    public java.lang.String getDydz(
    ) {
        return this._dydz;
    }

    /**
     * Returns the value of field 'fbzl'. The field 'fbzl' has the
     * following description: 附报资料
     * 
     * @return the value of field 'Fbzl'.
     */
    public java.lang.Object getFbzl(
    ) {
        return this._fbzl;
    }

    /**
     * Returns the value of field 'frDhhm'. The field 'frDhhm' has
     * the following description: 法人电话号
     * 
     * @return the value of field 'FrDhhm'.
     */
    public java.lang.String getFrDhhm(
    ) {
        return this._frDhhm;
    }

    /**
     * Returns the value of field 'frEmail'. The field 'frEmail'
     * has the following description: 法人电子邮箱地址
     * 
     * @return the value of field 'FrEmail'.
     */
    public java.lang.String getFrEmail(
    ) {
        return this._frEmail;
    }

    /**
     * Returns the value of field 'frYddhhm'. The field 'frYddhhm'
     * has the following description: 法人移动电话号码
     * 
     * @return the value of field 'FrYddhhm'.
     */
    public java.lang.String getFrYddhhm(
    ) {
        return this._frYddhhm;
    }

    /**
     * Returns the value of field 'frbz'. The field 'frbz' has the
     * following description: 法人标志Y：法人。N：非法人
     * 
     * @return the value of field 'Frbz'.
     */
    public java.lang.String getFrbz(
    ) {
        return this._frbz;
    }

    /**
     * Returns the value of field 'fshy1Dm'. The field 'fshy1Dm'
     * has the following description: 附属行业1代码
     * 
     * @return the value of field 'Fshy1Dm'.
     */
    public java.lang.String getFshy1Dm(
    ) {
        return this._fshy1Dm;
    }

    /**
     * Returns the value of field 'fshy1Hymxdm'. The field
     * 'fshy1Hymxdm' has the following description: 附属行业1明细代码
     * 
     * @return the value of field 'Fshy1Hymxdm'.
     */
    public java.lang.String getFshy1Hymxdm(
    ) {
        return this._fshy1Hymxdm;
    }

    /**
     * Returns the value of field 'fshy2Dm'. The field 'fshy2Dm'
     * has the following description: 附属行业2代码
     * 
     * @return the value of field 'Fshy2Dm'.
     */
    public java.lang.String getFshy2Dm(
    ) {
        return this._fshy2Dm;
    }

    /**
     * Returns the value of field 'fshy2Hymxdm'. The field
     * 'fshy2Hymxdm' has the following description: 附属行业2明细代码
     * 
     * @return the value of field 'Fshy2Hymxdm'.
     */
    public java.lang.String getFshy2Hymxdm(
    ) {
        return this._fshy2Hymxdm;
    }

    /**
     * Returns the value of field 'fshy3Dm'. The field 'fshy3Dm'
     * has the following description: 附属行业3代码
     * 
     * @return the value of field 'Fshy3Dm'.
     */
    public java.lang.String getFshy3Dm(
    ) {
        return this._fshy3Dm;
    }

    /**
     * Returns the value of field 'fshy3Hymxdm'. The field
     * 'fshy3Hymxdm' has the following description: 附属行业3明细代码
     * 
     * @return the value of field 'Fshy3Hymxdm'.
     */
    public java.lang.String getFshy3Hymxdm(
    ) {
        return this._fshy3Hymxdm;
    }

    /**
     * Returns the value of field 'fzrq'. The field 'fzrq' has the
     * following description: 税务登记证发证日期
     * 
     * @return the value of field 'Fzrq'.
     */
    public java.lang.String getFzrq(
    ) {
        return this._fzrq;
    }

    /**
     * Returns the value of field 'gdrs'. The field 'gdrs' has the
     * following description: 固定人数
     * 
     * @return the value of field 'Gdrs'.
     */
    public java.lang.String getGdrs(
    ) {
        return this._gdrs;
    }

    /**
     * Returns the value of field 'gdzczjfs'. The field 'gdzczjfs'
     * has the following description: 固定资产折旧方式
     * 
     * @return the value of field 'Gdzczjfs'.
     */
    public java.lang.String getGdzczjfs(
    ) {
        return this._gdzczjfs;
    }

    /**
     * Returns the value of field 'gsdjjgMc'. The field 'gsdjjgMc'
     * has the following description: 工商登记机关名称
     * 
     * @return the value of field 'GsdjjgMc'.
     */
    public java.lang.String getGsdjjgMc(
    ) {
        return this._gsdjjgMc;
    }

    /**
     * Returns the value of field 'gsdjzzh'. The field 'gsdjzzh'
     * has the following description: 工商登记执照号
     * 
     * @return the value of field 'Gsdjzzh'.
     */
    public java.lang.String getGsdjzzh(
    ) {
        return this._gsdjzzh;
    }

    /**
     * Returns the value of field 'gsfzrq'. The field 'gsfzrq' has
     * the following description: 工商发照日期
     * 
     * @return the value of field 'Gsfzrq'.
     */
    public java.lang.String getGsfzrq(
    ) {
        return this._gsfzrq;
    }

    /**
     * Returns the value of field 'gshbz'. The field 'gshbz' has
     * the following description: 工商户标志Y：工商户。N：非工商户
     * 
     * @return the value of field 'Gshbz'.
     */
    public java.lang.String getGshbz(
    ) {
        return this._gshbz;
    }

    /**
     * Returns the value of field 'gskyrq'. The field 'gskyrq' has
     * the following description: 工商开业日期
     * 
     * @return the value of field 'Gskyrq'.
     */
    public java.lang.String getGskyrq(
    ) {
        return this._gskyrq;
    }

    /**
     * Returns the value of field 'hhrs'. The field 'hhrs' has the
     * following description: 合伙人数
     * 
     * @return the value of field 'Hhrs'.
     */
    public java.lang.String getHhrs(
    ) {
        return this._hhrs;
    }

    /**
     * Returns the value of field 'hsfsDm'. The field 'hsfsDm' has
     * the following description: 核算方式代码
     * 
     * @return the value of field 'HsfsDm'.
     */
    public java.lang.String getHsfsDm(
    ) {
        return this._hsfsDm;
    }

    /**
     * Returns the value of field 'hwcfc'. The field 'hwcfc' has
     * the following description: 货物存放处
     * 
     * @return the value of field 'Hwcfc'.
     */
    public java.lang.String getHwcfc(
    ) {
        return this._hwcfc;
    }

    /**
     * Returns the value of field 'hwcfdmj'. The field 'hwcfdmj'
     * has the following description: 货物存放面积
     * 
     * @return the value of field 'Hwcfdmj'.
     */
    public java.lang.String getHwcfdmj(
    ) {
        return this._hwcfdmj;
    }

    /**
     * Returns the value of field 'jy'. The field 'jy' has the
     * following description: 兼营
     * 
     * @return the value of field 'Jy'.
     */
    public java.lang.String getJy(
    ) {
        return this._jy;
    }

    /**
     * Returns the value of field 'jyfs'. The field 'jyfs' has the
     * following description: 经营方式
     * 
     * @return the value of field 'Jyfs'.
     */
    public java.lang.String getJyfs(
    ) {
        return this._jyfs;
    }

    /**
     * Returns the value of field 'jyfw'. The field 'jyfw' has the
     * following description: 经营范围
     * 
     * @return the value of field 'Jyfw'.
     */
    public java.lang.String getJyfw(
    ) {
        return this._jyfw;
    }

    /**
     * Returns the value of field 'jyfwdw'. The field 'jyfwdw' has
     * the following description: 经营服务单位
     * 
     * @return the value of field 'Jyfwdw'.
     */
    public java.lang.String getJyfwdw(
    ) {
        return this._jyfwdw;
    }

    /**
     * Returns the value of field 'jzbwbDm'. The field 'jzbwbDm'
     * has the following description: 记帐本位币
     * 
     * @return the value of field 'JzbwbDm'.
     */
    public java.lang.String getJzbwbDm(
    ) {
        return this._jzbwbDm;
    }

    /**
     * Returns the value of field 'jzfs'. The field 'jzfs' has the
     * following description: 记帐方式
     * 
     * @return the value of field 'Jzfs'.
     */
    public java.lang.String getJzfs(
    ) {
        return this._jzfs;
    }

    /**
     * Returns the value of field 'jzlb'. The field 'jzlb' has the
     * following description: 建帐类别
     * 
     * @return the value of field 'Jzlb'.
     */
    public java.lang.String getJzlb(
    ) {
        return this._jzlb;
    }

    /**
     * Returns the value of field 'jzrq'. The field 'jzrq' has the
     * following description: 企业结账日期
     * 
     * @return the value of field 'Jzrq'.
     */
    public java.lang.String getJzrq(
    ) {
        return this._jzrq;
    }

    /**
     * Returns the value of field 'kjnd'. The field 'kjnd' has the
     * following description: 会计年度
     * 
     * @return the value of field 'Kjnd'.
     */
    public java.lang.String getKjnd(
    ) {
        return this._kjnd;
    }

    /**
     * Returns the value of field 'kyslrq'. The field 'kyslrq' has
     * the following description: 开业设立日期
     * 
     * @return the value of field 'Kyslrq'.
     */
    public java.lang.String getKyslrq(
    ) {
        return this._kyslrq;
    }

    /**
     * Returns the value of field 'lghbz'. The field 'lghbz' has
     * the following description: 漏管户标志
     * 
     * @return the value of field 'Lghbz'.
     */
    public java.lang.String getLghbz(
    ) {
        return this._lghbz;
    }

    /**
     * Returns the value of field 'nsrmcYw'. The field 'nsrmcYw'
     * has the following description: 纳税人名称英文
     * 
     * @return the value of field 'NsrmcYw'.
     */
    public java.lang.String getNsrmcYw(
    ) {
        return this._nsrmcYw;
    }

    /**
     * Returns the value of field 'nsywfsrq'. The field 'nsywfsrq'
     * has the following description: 纳税义务发生日期
     * 
     * @return the value of field 'Nsywfsrq'.
     */
    public java.lang.String getNsywfsrq(
    ) {
        return this._nsywfsrq;
    }

    /**
     * Returns the value of field 'pzjg'. The field 'pzjg' has the
     * following description: 批准机关
     * 
     * @return the value of field 'Pzjg'.
     */
    public java.lang.String getPzjg(
    ) {
        return this._pzjg;
    }

    /**
     * Returns the value of field 'pzrq'. The field 'pzrq' has the
     * following description: 批准日期
     * 
     * @return the value of field 'Pzrq'.
     */
    public java.lang.String getPzrq(
    ) {
        return this._pzrq;
    }

    /**
     * Returns the value of field 'pzwh'. The field 'pzwh' has the
     * following description: 批准文号
     * 
     * @return the value of field 'Pzwh'.
     */
    public java.lang.String getPzwh(
    ) {
        return this._pzwh;
    }

    /**
     * Returns the value of field 'scjydYb'. The field 'scjydYb'
     * has the following description: 生产经营地邮编
     * 
     * @return the value of field 'ScjydYb'.
     */
    public java.lang.String getScjydYb(
    ) {
        return this._scjydYb;
    }

    /**
     * Returns the value of field 'scjyqxQ'. The field 'scjyqxQ'
     * has the following description: 生产经营期限起
     * 
     * @return the value of field 'ScjyqxQ'.
     */
    public java.lang.String getScjyqxQ(
    ) {
        return this._scjyqxQ;
    }

    /**
     * Returns the value of field 'scjyqxZ'. The field 'scjyqxZ'
     * has the following description: 生产经营期限止
     * 
     * @return the value of field 'ScjyqxZ'.
     */
    public java.lang.String getScjyqxZ(
    ) {
        return this._scjyqxZ;
    }

    /**
     * Returns the value of field 'swdlrDhhm'. The field
     * 'swdlrDhhm' has the following description: 税务代理人电话号码
     * 
     * @return the value of field 'SwdlrDhhm'.
     */
    public java.lang.String getSwdlrDhhm(
    ) {
        return this._swdlrDhhm;
    }

    /**
     * Returns the value of field 'swdlrEmail'. The field
     * 'swdlrEmail' has the following description: 税务代理人电子邮箱地址
     * 
     * @return the value of field 'SwdlrEmail'.
     */
    public java.lang.String getSwdlrEmail(
    ) {
        return this._swdlrEmail;
    }

    /**
     * Returns the value of field 'swdlrNsrsbh'. The field
     * 'swdlrNsrsbh' has the following description: 税务代理人纳税人识别号
     * 
     * @return the value of field 'SwdlrNsrsbh'.
     */
    public java.lang.String getSwdlrNsrsbh(
    ) {
        return this._swdlrNsrsbh;
    }

    /**
     * Returns the value of field 'swdlrmc'. The field 'swdlrmc'
     * has the following description: 财务负责人证件号码
     * 
     * @return the value of field 'Swdlrmc'.
     */
    public java.lang.String getSwdlrmc(
    ) {
        return this._swdlrmc;
    }

    /**
     * Returns the value of field 'sykjzdDm'. The field 'sykjzdDm'
     * has the following description: 适用会计制度代码
     * 
     * @return the value of field 'SykjzdDm'.
     */
    public java.lang.String getSykjzdDm(
    ) {
        return this._sykjzdDm;
    }

    /**
     * Returns the value of field 'tzblGy'. The field 'tzblGy' has
     * the following description: 国有投资比例
     * 
     * @return the value of field 'TzblGy'.
     */
    public java.lang.String getTzblGy(
    ) {
        return this._tzblGy;
    }

    /**
     * Returns the value of field 'tzblWz'. The field 'tzblWz' has
     * the following description: 外资投资比例
     * 
     * @return the value of field 'TzblWz'.
     */
    public java.lang.String getTzblWz(
    ) {
        return this._tzblWz;
    }

    /**
     * Returns the value of field 'tzblZrr'. The field 'tzblZrr'
     * has the following description: 自然人投资比例
     * 
     * @return the value of field 'TzblZrr'.
     */
    public java.lang.String getTzblZrr(
    ) {
        return this._tzblZrr;
    }

    /**
     * Returns the value of field 'tzbzDm'. The field 'tzbzDm' has
     * the following description: 投资币种代码
     * 
     * @return the value of field 'TzbzDm'.
     */
    public java.lang.String getTzbzDm(
    ) {
        return this._tzbzDm;
    }

    /**
     * Returns the value of field 'tzze'. The field 'tzze' has the
     * following description: 投资总额
     * 
     * @return the value of field 'Tzze'.
     */
    public java.lang.String getTzze(
    ) {
        return this._tzze;
    }

    /**
     * Returns the value of field 'wjrs'. The field 'wjrs' has the
     * following description: 外籍人员人数
     * 
     * @return the value of field 'Wjrs'.
     */
    public java.lang.String getWjrs(
    ) {
        return this._wjrs;
    }

    /**
     * Returns the value of field 'wzwz'. The field 'wzwz' has the
     * following description: 网站网址
     * 
     * @return the value of field 'Wzwz'.
     */
    public java.lang.String getWzwz(
    ) {
        return this._wzwz;
    }

    /**
     * Returns the value of field 'xysbz'. The field 'xysbz' has
     * the following description: 信用社标志
     * 
     * @return the value of field 'Xysbz'.
     */
    public java.lang.String getXysbz(
    ) {
        return this._xysbz;
    }

    /**
     * Returns the value of field 'yhptxff'. The field 'yhptxff'
     * has the following description: 低值易耗品摊销方法
     * 
     * @return the value of field 'Yhptxff'.
     */
    public java.lang.String getYhptxff(
    ) {
        return this._yhptxff;
    }

    /**
     * Returns the value of field 'yxqQ'. The field 'yxqQ' has the
     * following description: 有效期起
     * 
     * @return the value of field 'YxqQ'.
     */
    public java.lang.String getYxqQ(
    ) {
        return this._yxqQ;
    }

    /**
     * Returns the value of field 'yxqZ'. The field 'yxqZ' has the
     * following description: 有效期止
     * 
     * @return the value of field 'YxqZ'.
     */
    public java.lang.String getYxqZ(
    ) {
        return this._yxqZ;
    }

    /**
     * Returns the value of field 'yzzz'. The field 'yzzz' has the
     * following description: 业主住址
     * 
     * @return the value of field 'Yzzz'.
     */
    public java.lang.String getYzzz(
    ) {
        return this._yzzz;
    }

    /**
     * Returns the value of field 'zcbzDm'. The field 'zcbzDm' has
     * the following description: 注册资本币种
     * 
     * @return the value of field 'ZcbzDm'.
     */
    public java.lang.String getZcbzDm(
    ) {
        return this._zcbzDm;
    }

    /**
     * Returns the value of field 'zcdDhhm'. The field 'zcdDhhm'
     * has the following description: 电话号码
     * 
     * @return the value of field 'ZcdDhhm'.
     */
    public java.lang.String getZcdDhhm(
    ) {
        return this._zcdDhhm;
    }

    /**
     * Returns the value of field 'zcdYb'. The field 'zcdYb' has
     * the following description: 注册地址邮编
     * 
     * @return the value of field 'ZcdYb'.
     */
    public java.lang.String getZcdYb(
    ) {
        return this._zcdYb;
    }

    /**
     * Returns the value of field 'zcdz'. The field 'zcdz' has the
     * following description: 注册地址
     * 
     * @return the value of field 'Zcdz'.
     */
    public java.lang.String getZcdz(
    ) {
        return this._zcdz;
    }

    /**
     * Returns the value of field 'zczb'. The field 'zczb' has the
     * following description: 注册资本
     * 
     * @return the value of field 'Zczb'.
     */
    public java.lang.String getZczb(
    ) {
        return this._zczb;
    }

    /**
     * Returns the value of field 'zgdw'. The field 'zgdw' has the
     * following description: 主管单位
     * 
     * @return the value of field 'Zgdw'.
     */
    public java.lang.String getZgdw(
    ) {
        return this._zgdw;
    }

    /**
     * Returns the value of field 'zhzcqk'. The field 'zhzcqk' has
     * the following description: 在华资产情况
     * 
     * @return the value of field 'Zhzcqk'.
     */
    public java.lang.String getZhzcqk(
    ) {
        return this._zhzcqk;
    }

    /**
     * Returns the value of field 'zjgyb'. The field 'zjgyb' has
     * the following description: 总机构业别
     * 
     * @return the value of field 'Zjgyb'.
     */
    public java.lang.String getZjgyb(
    ) {
        return this._zjgyb;
    }

    /**
     * Returns the value of field 'zszzsydwbz'. The field
     * 'zszzsydwbz' has the following description: 自收自支事业单位标志
     * 
     * @return the value of field 'Zszzsydwbz'.
     */
    public java.lang.String getZszzsydwbz(
    ) {
        return this._zszzsydwbz;
    }

    /**
     * Returns the value of field 'zy'. The field 'zy' has the
     * following description: 主营
     * 
     * @return the value of field 'Zy'.
     */
    public java.lang.String getZy(
    ) {
        return this._zy;
    }

    /**
     * Returns the value of field 'zzjgdm'. The field 'zzjgdm' has
     * the following description: 技术监督局代码(组织机构代码)
     * 
     * @return the value of field 'Zzjgdm'.
     */
    public java.lang.String getZzjgdm(
    ) {
        return this._zzjgdm;
    }

    /**
     * Returns the value of field 'zzlbDm'. The field 'zzlbDm' has
     * the following description: 执照类别代码
     * 
     * @return the value of field 'ZzlbDm'.
     */
    public java.lang.String getZzlbDm(
    ) {
        return this._zzlbDm;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'bsrDhhm'. The field 'bsrDhhm' has
     * the following description: 办税人电话号
     * 
     * @param bsrDhhm the value of field 'bsrDhhm'.
     */
    public void setBsrDhhm(
            final java.lang.String bsrDhhm) {
        this._bsrDhhm = bsrDhhm;
    }

    /**
     * Sets the value of field 'bsrEmail'. The field 'bsrEmail' has
     * the following description: 办税人电子邮箱地址
     * 
     * @param bsrEmail the value of field 'bsrEmail'.
     */
    public void setBsrEmail(
            final java.lang.String bsrEmail) {
        this._bsrEmail = bsrEmail;
    }

    /**
     * Sets the value of field 'bsrYddhhm'. The field 'bsrYddhhm'
     * has the following description: 办税人移动电话号码
     * 
     * @param bsrYddhhm the value of field 'bsrYddhhm'.
     */
    public void setBsrYddhhm(
            final java.lang.String bsrYddhhm) {
        this._bsrYddhhm = bsrYddhhm;
    }

    /**
     * Sets the value of field 'bsrZjhm'. The field 'bsrZjhm' has
     * the following description: 办税人证件号码
     * 
     * @param bsrZjhm the value of field 'bsrZjhm'.
     */
    public void setBsrZjhm(
            final java.lang.String bsrZjhm) {
        this._bsrZjhm = bsrZjhm;
    }

    /**
     * Sets the value of field 'bsrZjlDm'. The field 'bsrZjlDm' has
     * the following description: 办税人证件类型
     * 
     * @param bsrZjlDm the value of field 'bsrZjlDm'.
     */
    public void setBsrZjlDm(
            final java.lang.String bsrZjlDm) {
        this._bsrZjlDm = bsrZjlDm;
    }

    /**
     * Sets the value of field 'cjqQ'. The field 'cjqQ' has the
     * following description: 筹建期起
     * 
     * @param cjqQ the value of field 'cjqQ'.
     */
    public void setCjqQ(
            final java.lang.String cjqQ) {
        this._cjqQ = cjqQ;
    }

    /**
     * Sets the value of field 'cjqZ'. The field 'cjqZ' has the
     * following description: 筹建期止
     * 
     * @param cjqZ the value of field 'cjqZ'.
     */
    public void setCjqZ(
            final java.lang.String cjqZ) {
        this._cjqZ = cjqZ;
    }

    /**
     * Sets the value of field 'cwbbzl'. The field 'cwbbzl' has the
     * following description: 财务报表种类
     * 
     * @param cwbbzl the value of field 'cwbbzl'.
     */
    public void setCwbbzl(
            final java.lang.String cwbbzl) {
        this._cwbbzl = cwbbzl;
    }

    /**
     * Sets the value of field 'cwfzrDhhm'. The field 'cwfzrDhhm'
     * has the following description: 财务负责人电话号
     * 
     * @param cwfzrDhhm the value of field 'cwfzrDhhm'.
     */
    public void setCwfzrDhhm(
            final java.lang.String cwfzrDhhm) {
        this._cwfzrDhhm = cwfzrDhhm;
    }

    /**
     * Sets the value of field 'cwfzrEmail'. The field 'cwfzrEmail'
     * has the following description: 财务负责人电子邮箱地址
     * 
     * @param cwfzrEmail the value of field 'cwfzrEmail'.
     */
    public void setCwfzrEmail(
            final java.lang.String cwfzrEmail) {
        this._cwfzrEmail = cwfzrEmail;
    }

    /**
     * Sets the value of field 'cwfzrYddhhm'. The field
     * 'cwfzrYddhhm' has the following description: 财务负责人移动电话号码
     * 
     * @param cwfzrYddhhm the value of field 'cwfzrYddhhm'.
     */
    public void setCwfzrYddhhm(
            final java.lang.String cwfzrYddhhm) {
        this._cwfzrYddhhm = cwfzrYddhhm;
    }

    /**
     * Sets the value of field 'cwfzrZjhm'. The field 'cwfzrZjhm'
     * has the following description: 财务负责人证件号码
     * 
     * @param cwfzrZjhm the value of field 'cwfzrZjhm'.
     */
    public void setCwfzrZjhm(
            final java.lang.String cwfzrZjhm) {
        this._cwfzrZjhm = cwfzrZjhm;
    }

    /**
     * Sets the value of field 'cwfzrZjlxDm'. The field
     * 'cwfzrZjlxDm' has the following description: 财务负责人证件类型代码
     * 
     * @param cwfzrZjlxDm the value of field 'cwfzrZjlxDm'.
     */
    public void setCwfzrZjlxDm(
            final java.lang.String cwfzrZjlxDm) {
        this._cwfzrZjlxDm = cwfzrZjlxDm;
    }

    /**
     * Sets the value of field 'cwfzrmc'. The field 'cwfzrmc' has
     * the following description: 财务负责人名称
     * 
     * @param cwfzrmc the value of field 'cwfzrmc'.
     */
    public void setCwfzrmc(
            final java.lang.String cwfzrmc) {
        this._cwfzrmc = cwfzrmc;
    }

    /**
     * Sets the value of field 'cyrs'. The field 'cyrs' has the
     * following description: 从业人员
     * 
     * @param cyrs the value of field 'cyrs'.
     */
    public void setCyrs(
            final java.lang.String cyrs) {
        this._cyrs = cyrs;
    }

    /**
     * Sets the value of field 'dszgswjgDm'. The field 'dszgswjgDm'
     * has the following description: 地税主管税务机关代码
     * 
     * @param dszgswjgDm the value of field 'dszgswjgDm'.
     */
    public void setDszgswjgDm(
            final java.lang.String dszgswjgDm) {
        this._dszgswjgDm = dszgswjgDm;
    }

    /**
     * Sets the value of field 'dszgswjgJDm'. The field
     * 'dszgswjgJDm' has the following description: 地税主管税务机关（局）
     * 
     * @param dszgswjgJDm the value of field 'dszgswjgJDm'.
     */
    public void setDszgswjgJDm(
            final java.lang.String dszgswjgJDm) {
        this._dszgswjgJDm = dszgswjgJDm;
    }

    /**
     * Sets the value of field 'dydz'. The field 'dydz' has the
     * following description: 电子邮件地址
     * 
     * @param dydz the value of field 'dydz'.
     */
    public void setDydz(
            final java.lang.String dydz) {
        this._dydz = dydz;
    }

    /**
     * Sets the value of field 'fbzl'. The field 'fbzl' has the
     * following description: 附报资料
     * 
     * @param fbzl the value of field 'fbzl'.
     */
    public void setFbzl(
            final java.lang.Object fbzl) {
        this._fbzl = fbzl;
    }

    /**
     * Sets the value of field 'frDhhm'. The field 'frDhhm' has the
     * following description: 法人电话号
     * 
     * @param frDhhm the value of field 'frDhhm'.
     */
    public void setFrDhhm(
            final java.lang.String frDhhm) {
        this._frDhhm = frDhhm;
    }

    /**
     * Sets the value of field 'frEmail'. The field 'frEmail' has
     * the following description: 法人电子邮箱地址
     * 
     * @param frEmail the value of field 'frEmail'.
     */
    public void setFrEmail(
            final java.lang.String frEmail) {
        this._frEmail = frEmail;
    }

    /**
     * Sets the value of field 'frYddhhm'. The field 'frYddhhm' has
     * the following description: 法人移动电话号码
     * 
     * @param frYddhhm the value of field 'frYddhhm'.
     */
    public void setFrYddhhm(
            final java.lang.String frYddhhm) {
        this._frYddhhm = frYddhhm;
    }

    /**
     * Sets the value of field 'frbz'. The field 'frbz' has the
     * following description: 法人标志Y：法人。N：非法人
     * 
     * @param frbz the value of field 'frbz'.
     */
    public void setFrbz(
            final java.lang.String frbz) {
        this._frbz = frbz;
    }

    /**
     * Sets the value of field 'fshy1Dm'. The field 'fshy1Dm' has
     * the following description: 附属行业1代码
     * 
     * @param fshy1Dm the value of field 'fshy1Dm'.
     */
    public void setFshy1Dm(
            final java.lang.String fshy1Dm) {
        this._fshy1Dm = fshy1Dm;
    }

    /**
     * Sets the value of field 'fshy1Hymxdm'. The field
     * 'fshy1Hymxdm' has the following description: 附属行业1明细代码
     * 
     * @param fshy1Hymxdm the value of field 'fshy1Hymxdm'.
     */
    public void setFshy1Hymxdm(
            final java.lang.String fshy1Hymxdm) {
        this._fshy1Hymxdm = fshy1Hymxdm;
    }

    /**
     * Sets the value of field 'fshy2Dm'. The field 'fshy2Dm' has
     * the following description: 附属行业2代码
     * 
     * @param fshy2Dm the value of field 'fshy2Dm'.
     */
    public void setFshy2Dm(
            final java.lang.String fshy2Dm) {
        this._fshy2Dm = fshy2Dm;
    }

    /**
     * Sets the value of field 'fshy2Hymxdm'. The field
     * 'fshy2Hymxdm' has the following description: 附属行业2明细代码
     * 
     * @param fshy2Hymxdm the value of field 'fshy2Hymxdm'.
     */
    public void setFshy2Hymxdm(
            final java.lang.String fshy2Hymxdm) {
        this._fshy2Hymxdm = fshy2Hymxdm;
    }

    /**
     * Sets the value of field 'fshy3Dm'. The field 'fshy3Dm' has
     * the following description: 附属行业3代码
     * 
     * @param fshy3Dm the value of field 'fshy3Dm'.
     */
    public void setFshy3Dm(
            final java.lang.String fshy3Dm) {
        this._fshy3Dm = fshy3Dm;
    }

    /**
     * Sets the value of field 'fshy3Hymxdm'. The field
     * 'fshy3Hymxdm' has the following description: 附属行业3明细代码
     * 
     * @param fshy3Hymxdm the value of field 'fshy3Hymxdm'.
     */
    public void setFshy3Hymxdm(
            final java.lang.String fshy3Hymxdm) {
        this._fshy3Hymxdm = fshy3Hymxdm;
    }

    /**
     * Sets the value of field 'fzrq'. The field 'fzrq' has the
     * following description: 税务登记证发证日期
     * 
     * @param fzrq the value of field 'fzrq'.
     */
    public void setFzrq(
            final java.lang.String fzrq) {
        this._fzrq = fzrq;
    }

    /**
     * Sets the value of field 'gdrs'. The field 'gdrs' has the
     * following description: 固定人数
     * 
     * @param gdrs the value of field 'gdrs'.
     */
    public void setGdrs(
            final java.lang.String gdrs) {
        this._gdrs = gdrs;
    }

    /**
     * Sets the value of field 'gdzczjfs'. The field 'gdzczjfs' has
     * the following description: 固定资产折旧方式
     * 
     * @param gdzczjfs the value of field 'gdzczjfs'.
     */
    public void setGdzczjfs(
            final java.lang.String gdzczjfs) {
        this._gdzczjfs = gdzczjfs;
    }

    /**
     * Sets the value of field 'gsdjjgMc'. The field 'gsdjjgMc' has
     * the following description: 工商登记机关名称
     * 
     * @param gsdjjgMc the value of field 'gsdjjgMc'.
     */
    public void setGsdjjgMc(
            final java.lang.String gsdjjgMc) {
        this._gsdjjgMc = gsdjjgMc;
    }

    /**
     * Sets the value of field 'gsdjzzh'. The field 'gsdjzzh' has
     * the following description: 工商登记执照号
     * 
     * @param gsdjzzh the value of field 'gsdjzzh'.
     */
    public void setGsdjzzh(
            final java.lang.String gsdjzzh) {
        this._gsdjzzh = gsdjzzh;
    }

    /**
     * Sets the value of field 'gsfzrq'. The field 'gsfzrq' has the
     * following description: 工商发照日期
     * 
     * @param gsfzrq the value of field 'gsfzrq'.
     */
    public void setGsfzrq(
            final java.lang.String gsfzrq) {
        this._gsfzrq = gsfzrq;
    }

    /**
     * Sets the value of field 'gshbz'. The field 'gshbz' has the
     * following description: 工商户标志Y：工商户。N：非工商户
     * 
     * @param gshbz the value of field 'gshbz'.
     */
    public void setGshbz(
            final java.lang.String gshbz) {
        this._gshbz = gshbz;
    }

    /**
     * Sets the value of field 'gskyrq'. The field 'gskyrq' has the
     * following description: 工商开业日期
     * 
     * @param gskyrq the value of field 'gskyrq'.
     */
    public void setGskyrq(
            final java.lang.String gskyrq) {
        this._gskyrq = gskyrq;
    }

    /**
     * Sets the value of field 'hhrs'. The field 'hhrs' has the
     * following description: 合伙人数
     * 
     * @param hhrs the value of field 'hhrs'.
     */
    public void setHhrs(
            final java.lang.String hhrs) {
        this._hhrs = hhrs;
    }

    /**
     * Sets the value of field 'hsfsDm'. The field 'hsfsDm' has the
     * following description: 核算方式代码
     * 
     * @param hsfsDm the value of field 'hsfsDm'.
     */
    public void setHsfsDm(
            final java.lang.String hsfsDm) {
        this._hsfsDm = hsfsDm;
    }

    /**
     * Sets the value of field 'hwcfc'. The field 'hwcfc' has the
     * following description: 货物存放处
     * 
     * @param hwcfc the value of field 'hwcfc'.
     */
    public void setHwcfc(
            final java.lang.String hwcfc) {
        this._hwcfc = hwcfc;
    }

    /**
     * Sets the value of field 'hwcfdmj'. The field 'hwcfdmj' has
     * the following description: 货物存放面积
     * 
     * @param hwcfdmj the value of field 'hwcfdmj'.
     */
    public void setHwcfdmj(
            final java.lang.String hwcfdmj) {
        this._hwcfdmj = hwcfdmj;
    }

    /**
     * Sets the value of field 'jy'. The field 'jy' has the
     * following description: 兼营
     * 
     * @param jy the value of field 'jy'.
     */
    public void setJy(
            final java.lang.String jy) {
        this._jy = jy;
    }

    /**
     * Sets the value of field 'jyfs'. The field 'jyfs' has the
     * following description: 经营方式
     * 
     * @param jyfs the value of field 'jyfs'.
     */
    public void setJyfs(
            final java.lang.String jyfs) {
        this._jyfs = jyfs;
    }

    /**
     * Sets the value of field 'jyfw'. The field 'jyfw' has the
     * following description: 经营范围
     * 
     * @param jyfw the value of field 'jyfw'.
     */
    public void setJyfw(
            final java.lang.String jyfw) {
        this._jyfw = jyfw;
    }

    /**
     * Sets the value of field 'jyfwdw'. The field 'jyfwdw' has the
     * following description: 经营服务单位
     * 
     * @param jyfwdw the value of field 'jyfwdw'.
     */
    public void setJyfwdw(
            final java.lang.String jyfwdw) {
        this._jyfwdw = jyfwdw;
    }

    /**
     * Sets the value of field 'jzbwbDm'. The field 'jzbwbDm' has
     * the following description: 记帐本位币
     * 
     * @param jzbwbDm the value of field 'jzbwbDm'.
     */
    public void setJzbwbDm(
            final java.lang.String jzbwbDm) {
        this._jzbwbDm = jzbwbDm;
    }

    /**
     * Sets the value of field 'jzfs'. The field 'jzfs' has the
     * following description: 记帐方式
     * 
     * @param jzfs the value of field 'jzfs'.
     */
    public void setJzfs(
            final java.lang.String jzfs) {
        this._jzfs = jzfs;
    }

    /**
     * Sets the value of field 'jzlb'. The field 'jzlb' has the
     * following description: 建帐类别
     * 
     * @param jzlb the value of field 'jzlb'.
     */
    public void setJzlb(
            final java.lang.String jzlb) {
        this._jzlb = jzlb;
    }

    /**
     * Sets the value of field 'jzrq'. The field 'jzrq' has the
     * following description: 企业结账日期
     * 
     * @param jzrq the value of field 'jzrq'.
     */
    public void setJzrq(
            final java.lang.String jzrq) {
        this._jzrq = jzrq;
    }

    /**
     * Sets the value of field 'kjnd'. The field 'kjnd' has the
     * following description: 会计年度
     * 
     * @param kjnd the value of field 'kjnd'.
     */
    public void setKjnd(
            final java.lang.String kjnd) {
        this._kjnd = kjnd;
    }

    /**
     * Sets the value of field 'kyslrq'. The field 'kyslrq' has the
     * following description: 开业设立日期
     * 
     * @param kyslrq the value of field 'kyslrq'.
     */
    public void setKyslrq(
            final java.lang.String kyslrq) {
        this._kyslrq = kyslrq;
    }

    /**
     * Sets the value of field 'lghbz'. The field 'lghbz' has the
     * following description: 漏管户标志
     * 
     * @param lghbz the value of field 'lghbz'.
     */
    public void setLghbz(
            final java.lang.String lghbz) {
        this._lghbz = lghbz;
    }

    /**
     * Sets the value of field 'nsrmcYw'. The field 'nsrmcYw' has
     * the following description: 纳税人名称英文
     * 
     * @param nsrmcYw the value of field 'nsrmcYw'.
     */
    public void setNsrmcYw(
            final java.lang.String nsrmcYw) {
        this._nsrmcYw = nsrmcYw;
    }

    /**
     * Sets the value of field 'nsywfsrq'. The field 'nsywfsrq' has
     * the following description: 纳税义务发生日期
     * 
     * @param nsywfsrq the value of field 'nsywfsrq'.
     */
    public void setNsywfsrq(
            final java.lang.String nsywfsrq) {
        this._nsywfsrq = nsywfsrq;
    }

    /**
     * Sets the value of field 'pzjg'. The field 'pzjg' has the
     * following description: 批准机关
     * 
     * @param pzjg the value of field 'pzjg'.
     */
    public void setPzjg(
            final java.lang.String pzjg) {
        this._pzjg = pzjg;
    }

    /**
     * Sets the value of field 'pzrq'. The field 'pzrq' has the
     * following description: 批准日期
     * 
     * @param pzrq the value of field 'pzrq'.
     */
    public void setPzrq(
            final java.lang.String pzrq) {
        this._pzrq = pzrq;
    }

    /**
     * Sets the value of field 'pzwh'. The field 'pzwh' has the
     * following description: 批准文号
     * 
     * @param pzwh the value of field 'pzwh'.
     */
    public void setPzwh(
            final java.lang.String pzwh) {
        this._pzwh = pzwh;
    }

    /**
     * Sets the value of field 'scjydYb'. The field 'scjydYb' has
     * the following description: 生产经营地邮编
     * 
     * @param scjydYb the value of field 'scjydYb'.
     */
    public void setScjydYb(
            final java.lang.String scjydYb) {
        this._scjydYb = scjydYb;
    }

    /**
     * Sets the value of field 'scjyqxQ'. The field 'scjyqxQ' has
     * the following description: 生产经营期限起
     * 
     * @param scjyqxQ the value of field 'scjyqxQ'.
     */
    public void setScjyqxQ(
            final java.lang.String scjyqxQ) {
        this._scjyqxQ = scjyqxQ;
    }

    /**
     * Sets the value of field 'scjyqxZ'. The field 'scjyqxZ' has
     * the following description: 生产经营期限止
     * 
     * @param scjyqxZ the value of field 'scjyqxZ'.
     */
    public void setScjyqxZ(
            final java.lang.String scjyqxZ) {
        this._scjyqxZ = scjyqxZ;
    }

    /**
     * Sets the value of field 'swdlrDhhm'. The field 'swdlrDhhm'
     * has the following description: 税务代理人电话号码
     * 
     * @param swdlrDhhm the value of field 'swdlrDhhm'.
     */
    public void setSwdlrDhhm(
            final java.lang.String swdlrDhhm) {
        this._swdlrDhhm = swdlrDhhm;
    }

    /**
     * Sets the value of field 'swdlrEmail'. The field 'swdlrEmail'
     * has the following description: 税务代理人电子邮箱地址
     * 
     * @param swdlrEmail the value of field 'swdlrEmail'.
     */
    public void setSwdlrEmail(
            final java.lang.String swdlrEmail) {
        this._swdlrEmail = swdlrEmail;
    }

    /**
     * Sets the value of field 'swdlrNsrsbh'. The field
     * 'swdlrNsrsbh' has the following description: 税务代理人纳税人识别号
     * 
     * @param swdlrNsrsbh the value of field 'swdlrNsrsbh'.
     */
    public void setSwdlrNsrsbh(
            final java.lang.String swdlrNsrsbh) {
        this._swdlrNsrsbh = swdlrNsrsbh;
    }

    /**
     * Sets the value of field 'swdlrmc'. The field 'swdlrmc' has
     * the following description: 财务负责人证件号码
     * 
     * @param swdlrmc the value of field 'swdlrmc'.
     */
    public void setSwdlrmc(
            final java.lang.String swdlrmc) {
        this._swdlrmc = swdlrmc;
    }

    /**
     * Sets the value of field 'sykjzdDm'. The field 'sykjzdDm' has
     * the following description: 适用会计制度代码
     * 
     * @param sykjzdDm the value of field 'sykjzdDm'.
     */
    public void setSykjzdDm(
            final java.lang.String sykjzdDm) {
        this._sykjzdDm = sykjzdDm;
    }

    /**
     * Sets the value of field 'tzblGy'. The field 'tzblGy' has the
     * following description: 国有投资比例
     * 
     * @param tzblGy the value of field 'tzblGy'.
     */
    public void setTzblGy(
            final java.lang.String tzblGy) {
        this._tzblGy = tzblGy;
    }

    /**
     * Sets the value of field 'tzblWz'. The field 'tzblWz' has the
     * following description: 外资投资比例
     * 
     * @param tzblWz the value of field 'tzblWz'.
     */
    public void setTzblWz(
            final java.lang.String tzblWz) {
        this._tzblWz = tzblWz;
    }

    /**
     * Sets the value of field 'tzblZrr'. The field 'tzblZrr' has
     * the following description: 自然人投资比例
     * 
     * @param tzblZrr the value of field 'tzblZrr'.
     */
    public void setTzblZrr(
            final java.lang.String tzblZrr) {
        this._tzblZrr = tzblZrr;
    }

    /**
     * Sets the value of field 'tzbzDm'. The field 'tzbzDm' has the
     * following description: 投资币种代码
     * 
     * @param tzbzDm the value of field 'tzbzDm'.
     */
    public void setTzbzDm(
            final java.lang.String tzbzDm) {
        this._tzbzDm = tzbzDm;
    }

    /**
     * Sets the value of field 'tzze'. The field 'tzze' has the
     * following description: 投资总额
     * 
     * @param tzze the value of field 'tzze'.
     */
    public void setTzze(
            final java.lang.String tzze) {
        this._tzze = tzze;
    }

    /**
     * Sets the value of field 'wjrs'. The field 'wjrs' has the
     * following description: 外籍人员人数
     * 
     * @param wjrs the value of field 'wjrs'.
     */
    public void setWjrs(
            final java.lang.String wjrs) {
        this._wjrs = wjrs;
    }

    /**
     * Sets the value of field 'wzwz'. The field 'wzwz' has the
     * following description: 网站网址
     * 
     * @param wzwz the value of field 'wzwz'.
     */
    public void setWzwz(
            final java.lang.String wzwz) {
        this._wzwz = wzwz;
    }

    /**
     * Sets the value of field 'xysbz'. The field 'xysbz' has the
     * following description: 信用社标志
     * 
     * @param xysbz the value of field 'xysbz'.
     */
    public void setXysbz(
            final java.lang.String xysbz) {
        this._xysbz = xysbz;
    }

    /**
     * Sets the value of field 'yhptxff'. The field 'yhptxff' has
     * the following description: 低值易耗品摊销方法
     * 
     * @param yhptxff the value of field 'yhptxff'.
     */
    public void setYhptxff(
            final java.lang.String yhptxff) {
        this._yhptxff = yhptxff;
    }

    /**
     * Sets the value of field 'yxqQ'. The field 'yxqQ' has the
     * following description: 有效期起
     * 
     * @param yxqQ the value of field 'yxqQ'.
     */
    public void setYxqQ(
            final java.lang.String yxqQ) {
        this._yxqQ = yxqQ;
    }

    /**
     * Sets the value of field 'yxqZ'. The field 'yxqZ' has the
     * following description: 有效期止
     * 
     * @param yxqZ the value of field 'yxqZ'.
     */
    public void setYxqZ(
            final java.lang.String yxqZ) {
        this._yxqZ = yxqZ;
    }

    /**
     * Sets the value of field 'yzzz'. The field 'yzzz' has the
     * following description: 业主住址
     * 
     * @param yzzz the value of field 'yzzz'.
     */
    public void setYzzz(
            final java.lang.String yzzz) {
        this._yzzz = yzzz;
    }

    /**
     * Sets the value of field 'zcbzDm'. The field 'zcbzDm' has the
     * following description: 注册资本币种
     * 
     * @param zcbzDm the value of field 'zcbzDm'.
     */
    public void setZcbzDm(
            final java.lang.String zcbzDm) {
        this._zcbzDm = zcbzDm;
    }

    /**
     * Sets the value of field 'zcdDhhm'. The field 'zcdDhhm' has
     * the following description: 电话号码
     * 
     * @param zcdDhhm the value of field 'zcdDhhm'.
     */
    public void setZcdDhhm(
            final java.lang.String zcdDhhm) {
        this._zcdDhhm = zcdDhhm;
    }

    /**
     * Sets the value of field 'zcdYb'. The field 'zcdYb' has the
     * following description: 注册地址邮编
     * 
     * @param zcdYb the value of field 'zcdYb'.
     */
    public void setZcdYb(
            final java.lang.String zcdYb) {
        this._zcdYb = zcdYb;
    }

    /**
     * Sets the value of field 'zcdz'. The field 'zcdz' has the
     * following description: 注册地址
     * 
     * @param zcdz the value of field 'zcdz'.
     */
    public void setZcdz(
            final java.lang.String zcdz) {
        this._zcdz = zcdz;
    }

    /**
     * Sets the value of field 'zczb'. The field 'zczb' has the
     * following description: 注册资本
     * 
     * @param zczb the value of field 'zczb'.
     */
    public void setZczb(
            final java.lang.String zczb) {
        this._zczb = zczb;
    }

    /**
     * Sets the value of field 'zgdw'. The field 'zgdw' has the
     * following description: 主管单位
     * 
     * @param zgdw the value of field 'zgdw'.
     */
    public void setZgdw(
            final java.lang.String zgdw) {
        this._zgdw = zgdw;
    }

    /**
     * Sets the value of field 'zhzcqk'. The field 'zhzcqk' has the
     * following description: 在华资产情况
     * 
     * @param zhzcqk the value of field 'zhzcqk'.
     */
    public void setZhzcqk(
            final java.lang.String zhzcqk) {
        this._zhzcqk = zhzcqk;
    }

    /**
     * Sets the value of field 'zjgyb'. The field 'zjgyb' has the
     * following description: 总机构业别
     * 
     * @param zjgyb the value of field 'zjgyb'.
     */
    public void setZjgyb(
            final java.lang.String zjgyb) {
        this._zjgyb = zjgyb;
    }

    /**
     * Sets the value of field 'zszzsydwbz'. The field 'zszzsydwbz'
     * has the following description: 自收自支事业单位标志
     * 
     * @param zszzsydwbz the value of field 'zszzsydwbz'.
     */
    public void setZszzsydwbz(
            final java.lang.String zszzsydwbz) {
        this._zszzsydwbz = zszzsydwbz;
    }

    /**
     * Sets the value of field 'zy'. The field 'zy' has the
     * following description: 主营
     * 
     * @param zy the value of field 'zy'.
     */
    public void setZy(
            final java.lang.String zy) {
        this._zy = zy;
    }

    /**
     * Sets the value of field 'zzjgdm'. The field 'zzjgdm' has the
     * following description: 技术监督局代码(组织机构代码)
     * 
     * @param zzjgdm the value of field 'zzjgdm'.
     */
    public void setZzjgdm(
            final java.lang.String zzjgdm) {
        this._zzjgdm = zzjgdm;
    }

    /**
     * Sets the value of field 'zzlbDm'. The field 'zzlbDm' has the
     * following description: 执照类别代码
     * 
     * @param zzlbDm the value of field 'zzlbDm'.
     */
    public void setZzlbDm(
            final java.lang.String zzlbDm) {
        this._zzlbDm = zzlbDm;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrKzxx
     */
    public static com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrKzxx unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrKzxx) Unmarshaller.unmarshal(com.ttsoft.bjtax.smsb.sjjh.dao.dwnsr.HzsbdwnsrKzxx.class, reader);
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}

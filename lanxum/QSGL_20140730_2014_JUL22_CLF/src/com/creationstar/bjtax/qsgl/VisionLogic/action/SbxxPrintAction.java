package com.creationstar.bjtax.qsgl.VisionLogic.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.creationstar.bjtax.qsgl.BizLogic.vo.Fgrxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Grxx;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Sbzb;
import com.creationstar.bjtax.qsgl.BizLogic.vo.Tufwxx;
import com.creationstar.bjtax.qsgl.VisionLogic.action.Base.BaseAction;
import com.creationstar.bjtax.qsgl.VisionLogic.form.SbxxForm;
import com.creationstar.bjtax.qsgl.model.ActionType;
import com.creationstar.bjtax.qsgl.model.bo.SbxxBo;
import com.creationstar.bjtax.qsgl.proxy.QsglProxy;
import com.creationstar.bjtax.qsgl.util.Constants;
import com.creationstar.bjtax.qsgl.util.DataConvert;
import com.ttsoft.common.model.UserData;
import com.ttsoft.common.util.CodeConstants;
import com.ttsoft.common.util.Debug;
import com.ttsoft.common.util.RequestKey;
import com.ttsoft.common.util.SessionKey;
import com.ttsoft.framework.exception.BaseException;
import com.ttsoft.framework.util.VOPackage;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class SbxxPrintAction extends BaseAction {
    /**
     *
     * @param mapping ActionMapping
     * @param form ActionForm
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return ActionForward
     */
    public ActionForward doShow(ActionMapping mapping,
                                ActionForm form,
                                HttpServletRequest request,
                                HttpServletResponse response) {
        HttpSession session = request.getSession(false);

        try {
            Debug.out("this is sbxxprintaction hahaha");
            // 将参数中的Form构型为BaseForm。
            SbxxForm aForm = (SbxxForm) form;

            // 从BaseForm中获取查询条件。
            String sbbh = aForm.getSbbh();
            Debug.out("this is sbxxprintaction sbbh" + sbbh);
            UserData userData = (UserData) session.getAttribute(SessionKey.
                    USER_DATA);

            //获取存放在ServletContext中的processor-map.properties的数据
            Properties prop = (Properties) request.getSession(false).
                              getServletContext().getAttribute(Constants.
                    PROCESSOR_MAP_FILE_NAME);

            //构造向后台传输的Vopackage对象
            VOPackage vo = new VOPackage();
            SbxxBo bo = new SbxxBo();
            bo.setSbbh(sbbh);
            vo.setAction(ActionType.PRINT_SBB);
            vo.setUserData(this.getUserData());
            vo.setData(bo);
            vo.setProcessor(prop.getProperty(bo.getClass().getName()));
            bo = (SbxxBo) QsglProxy.getInstance().process(vo);

            aForm.setData(bo);
            //把条形码的输入字符串放入form中
            aForm.setInputStr(getInputStr(aForm, userData));
            // 保存Token;
            saveToken(request);
            // 转向成功后的界面。
            request.setAttribute(Constants.MESSAGE_KEY, "申报信息打印成功！");
            return mapping.findForward("show");
        } catch (BaseException ye) {
            // 保存Token;
            saveToken(request);
            String err = ye.getMessage();
            request.setAttribute(RequestKey.MESSAGE_KEY, err);
            return mapping.findForward("show");
        } catch (Exception ex) {
            // 保存Token;
            saveToken(request);
            ex.printStackTrace();
            request.setAttribute(RequestKey.MESSAGE_KEY,
                                 CodeConstants.MSG_ERROR);
            return mapping.findForward("show");
        }

    }

    /**
     * 根据不同的业务类型返回前台条形码的输入字符串
     * @param form SbxxForm
     * @return String
     */
    private String getInputStr(SbxxForm sbxxForm, UserData userData) {
        //获取申报主表vo
        Sbzb sbzbvo = sbxxForm.getVoSbzb();
        //取得契税申报与不征契税申报的标志字段
        String bljmsbs = sbzbvo.getBljmsbs();
        //取得个人与非个人的标志字段
        String yhbs = sbzbvo.getYhbs();
        //如果bljmsbs和Constants.ZB_BLJMSBS_BZ相等，为不征契税申报;否则为契税申报
        if (bljmsbs.equals(Constants.ZB_BLJMSBS_BZ)) {
            //判断是个人还是非个人
            if (yhbs.equals(Constants.ZB_YHBS_GR)) {
                //取得不征契税申报个人业务的输入参数字符串
                return getTxmxx(sbxxForm, userData, "1");
            } else {
                //取得不征契税申报非个人业务的输入参数字符串
                return getTxmxx(sbxxForm, userData, "2");

            }
        } else {
            if (yhbs.equals(Constants.ZB_YHBS_GR)) {
                //取得契税申报个人业务的输入参数字符串
                return getTxmxx(sbxxForm, userData, "1");

            } else {
                //取得契税申报非个人业务的输入参数字符串
                return getTxmxx(sbxxForm, userData, "2");

            }
        }

    }

//    //获取不征契税申报--个人 业务的输入字符串
//    private String getBZQSSb_Gr(){
//        System.out.println("获取不征契税申报--个人 业务的输入字符串");
//        return "";
//    }
//    //获取不征契税申报--非个人 业务的输入字符串
//    private String getBZQSSb_Fgr(){
//        System.out.println("获取不征契税申报--非个人 业务的输入字符串");
//        return "";
//    }
//    //获取契税申报--个人 业务的输入字符串
//    private String getQSSb_Gr(SbxxForm sbxxForm,UserData userData){
//        System.out.println("获取契税申报--个人 业务的输入字符串");
//        //获取申报主表vo
//        Sbzb sbzbvo = sbxxForm.getVoSbzb();
//        //取得土地、房屋信息表
//        Tufwxx fwxx = sbxxForm.getVoTufwxx();
//        //取得纳税人list
//        List nsrList = sbxxForm.getNsrList();
//        StringBuffer sb = new StringBuffer();
//
//        //增加头标示符
//        sb.append("^object_000002_000001^");
//        //增加合同编号
//        if(null!=sbzbvo.getHtbh()){
//            sb.append(sbzbvo.getHtbh() + "^");
//        }else{
//           sb.append(""+"^");
//        }
//
//        //增加合同网上签约日期,必输项，不需要判断
//        String qyrq = DataConvert.TimeStamp2String(fwxx.getHtqdsj());
//        sb.append(getRq(qyrq)+"^");
//        //sb.append(DataConvert.TimeStamp2String(qyrq,"yyyy-mm-dd")+"^");
//        //增加交易房屋地址区县，取得税务人员所属区县
//        sb.append(getDzqx(userData.getSsdwdm())+"^");
//        //增加交易房屋地址,必输项，不需要判断
//        sb.append(fwxx.getTdfwzldz()+"^");
//        //增加房屋权属转移类型
//        sb.append("162200^");
//        //增加是否为首次上市已购公房
//        sb.append("0^");
//        //增加房屋产权证号
//        sb.append(""+"^");
//        //增加房屋产权证填发日期
//        sb.append(""+"^");
//        //增加房屋建筑面积
//        String jzmj = DataConvert.BigDecimal2String(fwxx.getFwjzmj(),3);
//        System.out.println("jzmj====" + jzmj);
//        if(null!=jzmj && jzmj.equals("0000")){
//            sb.append(""+"^");
//        }else{
//            sb.append(jzmj+"^");
//        }
//        //增加建筑结构
//        sb.append(""+"^");
//        //增加规划用途
//        sb.append(""+"^");
//       // 增加合同总价,优先顺序：评估价格、成交价格、折合价格
//       //取得评估价格
//       String pgjg = DataConvert.BigDecimal2String(fwxx.getPgjgrmb());
//       //取得成交价格
//        String cjjg = DataConvert.BigDecimal2String(fwxx.getCjjgrmb());
//       //取得折合价格
//        String zhjg = DataConvert.BigDecimal2String(fwxx.getZhjgrmb());
//       if(null!=pgjg){
//           sb.append(pgjg+"^");
//       }else if(null!=cjjg){
//           sb.append(cjjg+"^");
//       }else if(null!=zhjg){
//           sb.append(zhjg+"^");
//       }
//
//       //增加楼层/总层数
//       sb.append(""+"^");
//       //增加外币种
//       sb.append(""+"^");
//       //增加外币金额
//       sb.append(""+"^");
//       //增加汇率
//       sb.append(""+"^");
//       //增加买方信息,纳税人信息不可能为空，不需要判断
//       for(int i=0;i<nsrList.size();i++){
//           Grxx grxx = (Grxx)nsrList.get(i);
//           //增加买方人名称
//           sb.append("^"+grxx.getNsrmc()+"~");
//           //增加买方人类别，此处为1，个人
//           sb.append("1~");
//           //增加证件类型
//           sb.append(getZjlxdm(grxx.getSfzjlx())+"~");
//           //增加证件号码
//           sb.append(grxx.getSfzjhm()+"~");
//           //增加权利人份额
//           sb.append(""+"~");
//           //根据是否是最后一条记录来判断末尾的分隔符是否需要
//           if(i==nsrList.size()-1){
//           //增加联系人电话
//           sb.append(grxx.getLxdh());
//           }else{
//               //增加联系人电话
//               sb.append(grxx.getLxdh() + "~");
//           }
//       }
//
//       //增加房地产中介机构名称
//       sb.append(""+"^");
//       System.out.println("契税申报个人inputStr ====" + sb.toString());
//       return sb.toString();
//    }
//    //获取契税申报--非个人 业务的输入字符串
//    private String getQSSb_Fgr(){
//        System.out.println("获取契税申报--非个人 业务的输入字符串");
//        return "";
//    }
    /**
     * 转换交易房屋地址区县的代码
     * @param qxdm String
     * @return String
     */
    private String getDzqx(String qxdm) {
        int dm = Integer.parseInt(qxdm.substring(0, 2));
        System.out.println("房屋所在区域代码===" + dm);
        switch (dm) {
        case (1):
            return "2";

        case (2):
            return "3";

        case (3):
            return "4";

        case (4):
            return "5";

        case (5):
            return "6";

        case (6):
            return "7";

        case (7):
            return "8";

        case (8):
            return "9";

        case (9):
            return "13";

        case (11):
            return "17";

        case (12):
            return "10";

        case (13):
            return "12";

        case (14):
            return "14";

        case (15):
            return "11";

        case (16):
            return "15";

        case (17):
            return "16";

        case (18):
            return "19";

        case (19):
            return "18";

        default:
            return "";

        }
    }

    /**
     * 个人证件类型转换代码
     * @param zjlxdm String
     * @return String
     */
    private String getZjlxdm(String zjlxdm) {
        String zjlx = "";
        if (zjlxdm.equals("02")) {
            zjlx = "1";
        } else if (zjlxdm.equals("03")) {
            zjlx = "4";
        } else if (zjlxdm.equals("04")) {
            zjlx = "3";
        } else if (zjlxdm.equals("06")) {
            zjlx = "7";
        } else if (zjlxdm.equals("11")) {
            zjlx = "10";
        } else if (zjlxdm.equals("90") || zjlxdm.equals("01") ||
                   zjlxdm.equals("05") || zjlxdm.equals("08")) {
            zjlx = "6";
        }
        return zjlx;
    }

    /**
     * 拼接签约日期
     * @param qyrq String
     * @return String
     */
    private String getRq(String qyrq) {
        String sb = qyrq.substring(0, 4);
        if (qyrq.substring(4, 5).equals("0")) {
            sb = sb + "-" + qyrq.substring(5, 6);
        } else {
            sb = sb + "-" + qyrq.substring(4, 6);
        }
        if (qyrq.substring(6, 7).equals("0")) {
            sb = sb + "-" + qyrq.substring(7, 8);
        } else {
            sb = sb + "-" + qyrq.substring(6, 8);
        }
        return sb;
    }

    /**
     * 获取条形码信息
     * @param sbxxForm SbxxForm 申报信息form
     * @param userData UserData 登陆人员信息
     * @param lb String  类别（个人或者非个人）
     * @return String
     */
    private String getTxmxx(SbxxForm sbxxForm, UserData userData, String lb) {
        StringBuffer sb = new StringBuffer();
        //获取申报主表vo
        Sbzb sbzbvo = sbxxForm.getVoSbzb();
        //取得土地、房屋信息表
        Tufwxx fwxx = sbxxForm.getVoTufwxx();
        //定义纳税人list
        List nsrList = new ArrayList();
        //定义非个人vo对象
        Fgrxx fgrxx = new Fgrxx();
        //如果类别为1，是个人业务，取得纳税人list；如果为2为非个人业务，取得非个人信息
        if (lb.equals("1")) {
            //取得纳税人list
            nsrList = sbxxForm.getNsrList();
        } else if (lb.equals("2")) {
            fgrxx = sbxxForm.getVoFgrxx();
        }

        //增加头标示符
		String sfesf = sbxxForm.getVoTufwxx().getSfesf();
		if(sfesf != null && !sfesf.equals("")){
            if(sfesf.equals("00")){
                //增加头标示符
                sb.append("^object_000001_000002^");
            }else{
                //增加头标示符
                sb.append("^object_000002_000002^");
            }
        }
        //sb.append("^object_000002_000002^");

        //增加合同编号
        if (null != sbzbvo.getHtbh()) {
            sb.append(sbzbvo.getHtbh() + "^");
        } else {
            sb.append("" + "^");
        }

        //增加合同网上签约日期,必输项，不需要判断
        String qyrq = DataConvert.TimeStamp2String(fwxx.getHtqdsj());
        sb.append(getRq(qyrq) + "^");
        //sb.append(DataConvert.TimeStamp2String(qyrq,"yyyy-mm-dd")+"^");
        //增加交易房屋地址区县，取得税务人员所属区县
        sb.append(getDzqx(userData.getSsdwdm()) + "^");
        //增加交易房屋地址,必输项，不需要判断
        sb.append(fwxx.getTdfwzldz() + "^");
        //增加房屋权属转移类型
        sb.append("162200^");
        //增加是否为首次上市已购公房
        sb.append("0^");
        //增加房屋产权证号
        sb.append("" + "^");
        //增加房屋产权证填发日期
        sb.append("" + "^");
        //增加房屋建筑面积
        String jzmj = DataConvert.BigDecimal2String(fwxx.getFwjzmj(), 3);
        //System.out.println("jzmj====" + jzmj);
        if (null != jzmj && jzmj.equals("0000")) {
            sb.append("" + "^");
        } else {
            sb.append(jzmj + "^");
        }
        //增加建筑结构
        sb.append("" + "^");
        //增加规划用途，如果房屋权属转移类型为03或者05，房屋土地类别为可选，规划用途进行转码赋值；
        //如果屋权属转移类型为其它的话，规划用途为空值
        if (fwxx.getTdfwqszylx().equals("03") ||
            fwxx.getTdfwqszylx().equals("05")) {
            //增加规划用途,房屋类型为非住宅，转化为写字楼代码值为5
            if (fwxx.getFwlxdm().equals("04")) {
                sb.append("5^");
            } else
            //增加规划用途,房屋类型为其它住宅，转化为普通住宅代码值为1
            if (fwxx.getFwlxdm().equals("03")) {
                sb.append("1^");
            } else {
                sb.append("" + "^");
            }

        } else {
            //增加规划用途
            sb.append("" + "^");
        }

        // 增加合同总价,优先顺序：评估价格、成交价格、折合价格
        //取得评估价格
        String pgjg = DataConvert.BigDecimal2String(fwxx.getPgjgrmb());
        //取得成交价格
        String cjjg = DataConvert.BigDecimal2String(fwxx.getCjjgrmb());
        //取得折合价格
        String zhjg = DataConvert.BigDecimal2String(fwxx.getZhjgrmb());
        if (null != cjjg) {
            sb.append(cjjg + "^");
        } else if (null != pgjg) {
            sb.append(pgjg + "^");
        } else if (null != zhjg) {
            sb.append(zhjg + "^");
        }

        //增加楼层/总层数
        sb.append("" + "^");
        //增加外币种
        sb.append("" + "^");
        //增加外币金额
        sb.append("" + "^");
        //增加汇率，并且空出卖方信息
        sb.append("" + "^^");
        if (lb.equals("1")) {
            //增加买方信息,纳税人信息不可能为空，不需要判断
            for (int i = 0; i < nsrList.size(); i++) {
                Grxx grxx = (Grxx) nsrList.get(i);
                //增加买方人名称
                sb.append(grxx.getNsrmc() + "~");
                //增加买方人类别，此处为1，个人
                sb.append("1~");
                //增加证件类型
                sb.append(getZjlxdm(grxx.getSfzjlx()) + "~");
                //增加证件号码
                sb.append(grxx.getSfzjhm() + "~");
                //增加权利人份额
                sb.append("" + "~");
                //根据是否是最后一条记录来判断末尾的分隔符是否需要
                if (i == nsrList.size() - 1) {
                    //增加联系人电话,如果是最后一个的话，需要加入^符号，连接中介机构名称
                    sb.append(grxx.getLxdh() + "^");
                } else {
                    //增加联系人电话
                    sb.append(grxx.getLxdh() + "~");
                }
            }

            //增加房地产中介机构名称,最后一个字符串没有^符号
            sb.append("");
            System.out.println("个人inputStr ====" + sb.toString());
        } else if (lb.equals("2")) {
            //增加买方人名称(纳税人名称)
            sb.append(fgrxx.getNsrmc() + "~");
            //增加买方人类别，此处为2，非个人
            sb.append("2~");
            //增加证件类型，非个人为其它，代码值为6
            sb.append("6~");
            //增加证件号码（非个人为计算机代码）
            sb.append(fgrxx.getJsjdm() + "~");
            //增加权利人份额
            sb.append("" + "~");
            //增加联系人电话
            if (null == fgrxx.getLxdh()) {
                sb.append("" + "^");
            } else {
                sb.append(fgrxx.getLxdh() + "^");
            }
            //增加房地产中介机构名称,最后一个字符串没有^符号
            sb.append("");
            System.out.println("非个人inputStr ====" + sb.toString());
        }

        return sb.toString();

    }


}

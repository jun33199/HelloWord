package com.ttsoft.bjtax.smsb.zjyjmsb.web;
/**
 * <p>Title: 北京地税核心征管系统－－申报征收</p>
 * <p>Company: 四一安信股份有限公司</p>
 * @author  qinwei
 * @version 1.1
 */

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import com.ttsoft.bjtax.smsb.util.TinyTools;
import com.ttsoft.framework.form.BaseForm;

public class ZjyjmsbForm extends BaseForm{
	public ZjyjmsbForm(){
		}

    //计算机代码,从录入数据中取得

    private String jsjdm;



    //纳税人名称

    private String nsrmc;

    //联系电话

    private String dh;

    //年度

    private String nd;

    //季度(对应表头的下拉列表)

    private String jd;

   //季度（对应表一和表二）

    //private String jd1;
    //企业类型(对应表一)

    private String qylx;

    //企业类型(对应表二)

    private String qylx1;
    //月份

    private int yf;

    //纳税人类型名称
    private String nsrlxmc;

    //纳税人登记注册类型(此处只返回1或2)
    private int djcelx;

    //征期判断
    //private boolean zqpd;

    //季度判断
    //private boolean jdpd;

    //数据判断
    private boolean sjpd;

    //当前季度
    //private String dqjd;

    /**  全年营业收入和全年减免税额
     *   对应再就业减免税申报表（三）
     */
    //全年营业收入

    private String qnyysr;

    //全年减免税额

    private String qnjmse;


    // j1-j14对应再就业减免税申报表（一）序号的1-14项

    private String j1;
    private String j2;
    private String j3;
    private String j4;
    private String j5;
    private String j6;
    private String j7;
    private String j8;
    private String j9;
    private String j10;
    private String j11;
    private String j12;
    private String j13;
    private String j14;

    //j21-j34对应再就业减免税申报表（二）序号的1-14项

    private String j21;
    private String j22;
    private String j23;
    private String j24;
    private String j25;
    private String j26;
    private String j27;
    private String j28;
    private String j29;
    private String j30;
    private String j31;
    private String j32;
    private String j33;
    private String j34;




    public String getJsjdm()
    {
        return jsjdm;
    }
    public String getNsrmc()
    {
        return nsrmc;
    }
    public String getDh()
    {
        return dh;
    }
    public String getNd()
    {
        return nd;
    }
    public String getQnyysr()
    {
        return qnyysr;
    }


    public void setNsrmc(String nsrmc)
    {
        this.nsrmc = nsrmc;
    }
    public void setJsjdm(String jsjdm)
    {
        this.jsjdm = jsjdm;
    }
    public void setDh(String dh)
    {
        this.dh = dh;
    }
    public void setNd(String nd)
    {
        this.nd = nd;
    }
    public void setQnyysr(String qnyysr)
    {
        this.qnyysr = qnyysr;
    }

	public String getJ1() {
		return j1;
	}
	public void setJ1(String j1) {
		this.j1 = j1;
	}
	public String getJ10() {
		return j10;
	}
	public void setJ10(String j10) {
		this.j10 = j10;
	}
	public String getJ11() {
		return j11;
	}
	public void setJ11(String j11) {
		this.j11 = j11;
	}
	public String getJ12() {
		return j12;
	}
	public void setJ12(String j12) {
		this.j12 = j12;
	}
	public String getJ13() {
		return j13;
	}
	public void setJ13(String j13) {
		this.j13 = j13;
	}
	public String getJ14() {
		return j14;
	}
	public void setJ14(String j14) {
		this.j14 = j14;
	}
	public String getJ2() {
		return j2;
	}
	public void setJ2(String j2) {
		this.j2 = j2;
	}
	public String getJ3() {
		return j3;
	}
	public void setJ3(String j3) {
		this.j3 = j3;
	}
	public String getJ4() {
		return j4;
	}
	public void setJ4(String j4) {
		this.j4 = j4;
	}
	public String getJ5() {
		return j5;
	}
	public void setJ5(String j5) {
		this.j5 = j5;
	}
	public String getJ6() {
		return j6;
	}
	public void setJ6(String j6) {
		this.j6 = j6;
	}
	public String getJ7() {
		return j7;
	}
	public void setJ7(String j7) {
		this.j7 = j7;
	}
	public String getJ8() {
		return j8;
	}
	public void setJ8(String j8) {
		this.j8 = j8;
	}
	public String getJ9() {
		return j9;
	}
	public void setJ9(String j9) {
		this.j9 = j9;
	}
	public String getJd() {
		return jd;
	}
	public void setJd(String jd) {
		this.jd = jd;
	}
	public String getQylx() {
		return qylx;
	}
	public void setQylx(String qylx) {
		this.qylx = qylx;
	}
	public int getYf() {
		return yf;
	}
	public void setYf(int yf) {
		this.yf = yf;
	}
	public String getQnjmse() {
		return qnjmse;
	}
	public void setQnjmse(String qnjmse) {
		this.qnjmse = qnjmse;
	}
	public String getJ21() {
		return j21;
	}
	public void setJ21(String j21) {
		this.j21 = j21;
	}
	public String getJ22() {
		return j22;
	}
	public void setJ22(String j22) {
		this.j22 = j22;
	}
	public String getJ23() {
		return j23;
	}
	public void setJ23(String j23) {
		this.j23 = j23;
	}
	public String getJ24() {
		return j24;
	}
	public void setJ24(String j24) {
		this.j24 = j24;
	}
	public String getJ25() {
		return j25;
	}
	public void setJ25(String j25) {
		this.j25 = j25;
	}
	public String getJ26() {
		return j26;
	}
	public void setJ26(String j26) {
		this.j26 = j26;
	}
	public String getJ27() {
		return j27;
	}
	public void setJ27(String j27) {
		this.j27 = j27;
	}
	public String getJ28() {
		return j28;
	}
	public void setJ28(String j28) {
		this.j28 = j28;
	}
	public String getJ29() {
		return j29;
	}
	public void setJ29(String j29) {
		this.j29 = j29;
	}
	public String getJ30() {
		return j30;
	}
	public void setJ30(String j30) {
		this.j30 = j30;
	}
	public String getJ31() {
		return j31;
	}
	public void setJ31(String j31) {
		this.j31 = j31;
	}
	public String getJ32() {
		return j32;
	}
	public void setJ32(String j32) {
		this.j32 = j32;
	}
	public String getJ33() {
		return j33;
	}
	public void setJ33(String j33) {
		this.j33 = j33;
	}
	public String getJ34() {
		return j34;
	}
	public void setJ34(String j34) {
		this.j34 = j34;
	}
	public String getQylx1() {
		return qylx1;
	}
	public void setQylx1(String qylx1) {
		this.qylx1 = qylx1;
	}
	public String getNsrlxmc() {
		return nsrlxmc;
	}
	public void setNsrlxmc(String nsrlxmc) {
		this.nsrlxmc = nsrlxmc;
	}

/*	public String getJd1() {
		return jd1;
	}
	public void setJd1(String jd1) {
		this.jd1 = jd1;
	}
*/	//表单全部初始化
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    this.j1 = null;
    this.j2 = null;
    this.j3 = null;
    this.j4 = null;
    this.j5 = null;
    this.j6 = null;
    this.j7 = null;
    this.j8 = null;
    this.j9 = null;
    this.j10 = null;
    this.j11 = null;
    this.j12 = null;
    this.j13 = null;
    this.j14 = null;
    this.j21 = null;
    this.j22 = null;
    this.j23 = null;
    this.j24 = null;
    this.j25 = null;
    this.j26 = null;
    this.j27 = null;
    this.j28 = null;
    this.j29 = null;
    this.j30 = null;
    this.j31 = null;
    this.j32 = null;
    this.j33 = null;
    this.j34 = null;
    this.qnjmse = null;
    this.qnyysr = null;
    this.jsjdm = null;
    //Map dqrq = this.timenow();
    //this.jd =(String)dqrq.get("Jd");
    //this.jd1=(String)dqrq.get("Jd");
    //this.dqjd=(String)dqrq.get("Jd");
    //this.yf =Integer.parseInt((String)dqrq.get("Yf"));
    //this.nd =(String)dqrq.get("Nd");
    this.qylx ="1";
    this.qylx1 = "1";
    this.djcelx = 0;
   }
	//表单部分初始化
	public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest,int a) {
    this.j1 = null;
    this.j2 = null;
    this.j3 = null;
    this.j4 = null;
    this.j5 = null;
    this.j6 = null;
    this.j7 = null;
    this.j8 = null;
    this.j9 = null;
    this.j10 = null;
    this.j11 = null;
    this.j12 = null;
    this.j13 = null;
    this.j14 = null;
    this.j21 = null;
    this.j22 = null;
    this.j23 = null;
    this.j24 = null;
    this.j25 = null;
    this.j26 = null;
    this.j27 = null;
    this.j28 = null;
    this.j29 = null;
    this.j30 = null;
    this.j31 = null;
    this.j32 = null;
    this.j33 = null;
    this.j34 = null;
    this.qnjmse = null;
    this.qnyysr = null;
    this.qylx ="1";
    this.qylx1 = "1";
    this.djcelx =0;
	}
    //	初始化年度和月份
    public  Map timenow(){
 	   Map timenow = new HashMap();
        //设置年度
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp sbrq = TinyTools.second2Day(now); // 申报日期
        String nd = new SimpleDateFormat("yyyy").format(sbrq); //年度
        String dqyf = new SimpleDateFormat("MM").format(sbrq);;//当前月份
        int yuefen =Integer.parseInt(dqyf);
        int xsjd=0;//根据当前月份系统自动带出的季度
        int xsyf=0;//根据当前季度系统自动带出的月份
        int xsnd=0;//显示年度，1-3月的税款所属期是上年4季度
        switch(yuefen){
        case 1: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1; break;
        case 2: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
        case 3: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
        case 4: xsjd=1; xsyf=1;xsnd=Integer.parseInt(nd);break;
        //case 5: xsjd=1; xsyf=1;xsnd=Integer.parseInt(nd);break;
        case 5: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
        //case 6: xsjd=1; xsyf=1;xsnd=Integer.parseInt(nd);break;
        case 6: xsjd=4; xsyf=10;xsnd=Integer.parseInt(nd)-1;break;
        case 7: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
        case 8: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
        case 9: xsjd=2; xsyf=4;xsnd=Integer.parseInt(nd);break;
        case 10:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
        case 11:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
        case 12:xsjd=3; xsyf=7;xsnd=Integer.parseInt(nd);break;
        }
        timenow.put("Nd",Integer.toString(xsnd));
        timenow.put("Jd",Integer.toString(xsjd));
        timenow.put("Yf",Integer.toString(xsyf));
        return timenow;
    }
	public int getDjcelx() {
		return djcelx;
	}
	public void setDjcelx(int djcelx) {
		this.djcelx = djcelx;
	}
/*	public boolean isJdpd() {
		return jdpd;
	}
	public void setJdpd(boolean jdpd) {
		this.jdpd = jdpd;
	}
*/	public boolean isSjpd() {
		return sjpd;
	}
	public void setSjpd(boolean sjpd) {
		this.sjpd = sjpd;
	}
/*	public boolean isZqpd() {
		return zqpd;
	}
	public void setZqpd(boolean zqpd) {
		this.zqpd = zqpd;
	}
	public String getDqjd() {
		return dqjd;
	}
	public void setDqjd(String dqjd) {
		this.dqjd = dqjd;
	}
*/
}

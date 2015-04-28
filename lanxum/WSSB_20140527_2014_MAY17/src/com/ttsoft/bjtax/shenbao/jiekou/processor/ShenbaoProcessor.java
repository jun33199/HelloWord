package com.ttsoft.bjtax.shenbao.jiekou.processor;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ttsoft.bjtax.jikuai.zwcl.inf.JKAdapter;
import com.ttsoft.bjtax.jikuai.zwcl.vo.page.Yskm;
import com.ttsoft.bjtax.shenbao.jiekou.access.Sb_jl_sbjkmxAccess;
import com.ttsoft.bjtax.shenbao.jiekou.access.Sb_jl_sbjkzbAccess;
import com.ttsoft.bjtax.shenbao.model.client.DeclareInfor;
import com.ttsoft.bjtax.shenbao.model.client.Ysjc;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkmx;
import com.ttsoft.bjtax.shenbao.model.domain.Sbjkzb;
import com.ttsoft.bjtax.shenbao.service.processor.InterFaceProcessor;
import com.ttsoft.bjtax.shenbao.util.TinyTools;

/**
 * 为纳税评估提供的生成缴款书的类
 * <p>Title: 北京地税综合管理系统  申报征收模块</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: TTSOFT</p>
 * @author wanghw
 * @version 1.0
 */
public class ShenbaoProcessor {
  private static final int SESSION_ID = 0;

  //是否返回缴款数据默认为false
  private boolean isReturnPaymentInfo = false;
  //打印标识
  private int printTag;
  /**
   * 申报编号
   */
  private String sbbh;
  /**
   * 票据格式：一票一税
   */
  private final static int PRINT_YPYS = 1;
  /**
   * 已申报
   */
  private final static String CLBJDM_YSB = "11";

  //构造方法
  public ShenbaoProcessor() {
  }

  /**
   * 生成缴款数据并进行数据库插入操作
   * @param declareInfor 申报数据
   * @param _sbbh 申报编号
   * @param conn 数据库连接
   * @return 操作是否成功,且如果要返回缴款数据的话:还会返回缴款数据
   * @throws Exception 操作异常
   */
  public Object createJkInfor(DeclareInfor declareInfor,
                              String _sbbh, Connection conn) throws Exception {
    this.sbbh = _sbbh;
//        isReturnPaymentInfo = declareInfor.isIsReturnPaymentInfo();
//        printTag = declareInfor.getPrintTag();
    Sbjkzb insbjkzb = declareInfor.getSbjkzb();
    List insbjkmxInfo = declareInfor.getSbjkmxInfo();
    try {
      //1.填充每个缴款明细数据的级次,预算科目等在后台才要填写的信息
      fillSbjkmxInfo(insbjkzb, insbjkmxInfo);
      //2.按照不同科目信息创建多个缴款主表并填写对应的缴款凭证号信息
      //并插入数据库按照不同的打印标识返回缴款数据
      List jkdataList =
          createSkjkzbInfor_onetax(insbjkzb, insbjkmxInfo, conn);

      //返回缴款数据
      return jkdataList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--保存数据错误!");
    }
  }

  /**
   * 填充申报明细List中的预算级次和科目信息
   * @param insbjkzb 缴款主表信息
   * @param insbjkmxInfo 缴款明细信息
   * @throws Exception 操作异常
   */
  private void fillSbjkmxInfo(Sbjkzb insbjkzb, List insbjkmxInfo) throws
      Exception {
    Sbjkmx sbjkmxTmp = null;
    for (int i = 0; i < insbjkmxInfo.size(); i++) {
      sbjkmxTmp = (Sbjkmx) insbjkmxInfo.get(i);
      try {
        fillSbjkmx(sbjkmxTmp, insbjkzb);
      }
      catch (Exception ex) {
        throw ex;
      }
    }
  }

  /**
   * 在缴款明细数据中添加预算级次和预算科目信息
   * @param sbjkmx 操作的缴款明细
   * @param insbjkzb 申报缴款主表
   * @throws Exception 操作异常
   */
  private void fillSbjkmx(Sbjkmx sbjkmx, Sbjkzb insbjkzb) throws Exception {
    //3.填写税款所属时期
    if (sbjkmx.getSkssjsrq() == null) {
      throw new Exception("税款所属时期的截止日期不能为空！");
    }
    if (sbjkmx.getYsjcdm() == null) { //如果预算级次尚未赋值，则执行查找预算级次
      //1.查找预算级次并填充
      Ysjc ysjc = getYsjc(insbjkzb.getJsjdm(),
                          sbjkmx.getSzsmdm(), sbjkmx.getSkssjsrq()); //第三个参数为税款所属截止日期
      sbjkmx.setYsjcdm(ysjc.getYsjcdm()); //赋值
    }
    //查找预算科目代码并填充
    //调用计会接口，获取预算科目
    Yskm yskm = null;
    try {
      yskm = JKAdapter.getInstance().getYskm(sbjkmx.getSzsmdm(),
                                             insbjkzb.getDjzclxdm(),
                                             insbjkzb.getGjbzhydm(),
                                             sbjkmx.getYsjcdm());
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "--查询预算科目时发生错误！");
    }
    if (yskm == null) {
      throw new Exception("获取预算科目失败!");
    }

    if (sbjkmx.getYskmdm() == null) { //如果预算科目代码尚未赋值，则将查询结果赋值给明细对象的对应属性
      sbjkmx.setYskmdm(yskm.getYskmdm()); //赋值
    }

    //入库金额
    sbjkmx.setRkje(sbjkmx.getSjse());
    sbjkmx.setSbbh(sbbh); //设置申报编号
    if (sbjkmx.getYsjcdm().equals("21")) { //如果是地方级
      sbjkmx.setSjfc(getFc(sbjkmx.getSjse(), yskm.getSjfcbl())); //设置市局级分成金额
      sbjkmx.setQjfc(getFc(sbjkmx.getSjse(), yskm.getQxfcbl())); //设置区县级分成金额
    }
  }

  /**
   * 把申报主表,申报明细数据插入数据库
   * @param sbjkzbList 申报缴款主表信息List中的值对象为:Sbjkzb
   * @param sbjkmxInfoList 申报明细信息:List中的对象还是List的实例(有相同科目信息的
   * 明细的List):每个subList中的对象才是Sbjkmx
   * @param conn 数据库连接
   * @throws Exception 异常信息
   */
  private void insertSbjkData(List sbjkzbList,
                              List sbjkmxInfoList, Connection conn) throws
      Exception {
    try {
      //插入主表数据
      for (int i = 0; i < sbjkzbList.size(); i++) {
        Sbjkzb zbData = (Sbjkzb) sbjkzbList.get(i);
        Sb_jl_sbjkzbAccess.insertRecord(conn, zbData);
      }
      //插入明细数据
      for (int j = 0; j < sbjkmxInfoList.size(); j++) {
        Sbjkmx mxData = (Sbjkmx) sbjkmxInfoList.get(j);
        Sb_jl_sbjkmxAccess.insertRecord(conn, mxData);
      }
    }
    catch (Exception ex) {
      throw ex;
    }
  }

  //对按照预算科目拆分后的每组明细生成缴款主表数据并填写缴款凭证号一票一税情况
  //返回生成的缴款书信息
  private List createSkjkzbInfor_onetax(Sbjkzb insbjkzb,
                                        List sbjkmxInfo, Connection conn) throws
      Exception {
    String sequence = null; //缴款凭证号中的序列号
    //得到当前计算机在申报缴款主表中的最大流水号.
    try {
      //得到录入日期的年月（6位）
      SimpleDateFormat simpleDataFormats = new SimpleDateFormat("yyyyMM");
      String yyyyMM = simpleDataFormats.format(insbjkzb.getLrrq());
      SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyMM");
      String yyMM = simpleDataFormat.format(insbjkzb.getLrrq());
      //NumberFormat nmbFormat = new DecimalFormat("000");
      //循环中的临时变量
      List sbjkzbList = new ArrayList(); //创建的缴款主表List
      List jkInforList = new ArrayList(); //返回的缴款书数据List
      DeclareInfor jkInfor = null; //一条缴款数据
      //List splitsbjkmxList = null; //按4个科目税款时期拆分后的多组明细数据list

      //Sbjkmx sbjkmxtmp = null;
      Sbjkzb sbjkzbtmp = null;
      String jkpzh = null;
      //List tmpSbjkmxList; //按4个科目税款时期拆分后的一组明细数据list
      //BigDecimal sjjehe; //实缴金额

      //取得当前计算机代码使用的最大序号
//            sequence = nmbFormat.format(
//                Integer.parseInt(getXh(insbjkzb.getJsjdm(), yyyyMM, conn)) + 1);
      sequence = getXh(insbjkzb.getJsjdm(), yyyyMM, conn);
      //自增后的ASCII码
      String sequenceAscii = TinyTools.stringToASCII(TinyTools.
          asciiToString(Integer.parseInt(sequence) + 1));
      //格式化为"000"格式
      sequence = TinyTools.xhFormat(
          TinyTools.asciiToString(Integer.parseInt(sequenceAscii)));

      jkpzh = insbjkzb.getJsjdm() + yyMM + sequence + "0";
      sbjkzbtmp = createSbjkzb(sbjkmxInfo, insbjkzb, jkpzh, CLBJDM_YSB, conn); //已申报
      sbjkzbList.add(sbjkzbtmp);
      jkInfor = new DeclareInfor(sbjkzbtmp, sbjkmxInfo);
      jkInforList.add(jkInfor);

      //把生成的数据插入数据库中
      insertSbjkData(sbjkzbList, sbjkmxInfo, conn);
      return jkInforList;
    }
    catch (Exception ex) {
      throw new Exception(ex.getMessage() + "生成缴款数据失败！");
    }

  }

  //得到明细数据创建一条sbjkzb数据
  private Sbjkzb createSbjkzb(List sbjkmxList, Sbjkzb sbjkzb, String jkpzh,
                              String clbj, Connection conn) throws Exception {
    Sbjkzb cloneSbjkzb = sbjkzb.getCopy();
    cloneSbjkzb.setJkpzh(jkpzh);
    Sbjkmx sbjkmxtmp = null;
    BigDecimal sjjehe = new BigDecimal(0.00);
    for (int j = 0; j < sbjkmxList.size(); j++) {
      sbjkmxtmp = (Sbjkmx) sbjkmxList.get(j);
      sbjkmxtmp.setJkpzh(jkpzh);
      if (sbjkmxtmp.getSjse() != null) {
        sjjehe = sjjehe.add(sbjkmxtmp.getSjse()); //统计实缴金额的合计
      }
    }
    //设置实缴金额等其他字段数据
    cloneSbjkzb.setSjje(sjjehe);
    cloneSbjkzb.setYsjcdm(sbjkmxtmp.getYsjcdm());
    cloneSbjkzb.setYskmdm(sbjkmxtmp.getYskmdm());
    cloneSbjkzb.setSzdm(sbjkmxtmp.getSzsmdm().substring(0, 2)); //明细不存在税种字段，取税目前两位
    cloneSbjkzb.setRkje(sjjehe);
    cloneSbjkzb.setSbbh(sbbh); //设置申报编号
    cloneSbjkzb.setZwbs("00000000000000000000");
    cloneSbjkzb.setClbjdm(clbj);
    //添加申报缴款主表税款所属时期
    cloneSbjkzb.setSkssksrq(sbjkmxtmp.getSkssksrq());
    cloneSbjkzb.setSkssjsrq(sbjkmxtmp.getSkssjsrq());
    //限缴日期
    String smdm = sbjkmxtmp.getSzsmdm(); //税种税目代码
    String djzclx = sbjkzb.getDjzclxdm(); //登记注册类型代码
    SimpleDateFormat simpleDataFormat = new SimpleDateFormat("yyyyMMdd");
    String rq = simpleDataFormat.format(new Date()); //当前年月日:YYYYMMdd
    if (cloneSbjkzb.getXjrq() == null) //如果限缴日期为空
      cloneSbjkzb.setXjrq(getZqzzrq(smdm, djzclx, rq, conn)); //限缴日期
      //得到计算机代码的登记信息并填写主表
    String jsjdm = sbjkmxtmp.getJsjdm(); //计算机代码
    //获取国库交换号
    String gkjhh = getSkgk(jsjdm, cloneSbjkzb.getSwjgzzjgdm(), conn);
    cloneSbjkzb.setGkzzjgdm(gkjhh); //国库组织机构代码

    return cloneSbjkzb;
  }

  /**
   * 根据税种税目代码和当前年月，获取征期日历表中对呀的征期终止日期
   * 如果存在多条满足条件的记录，取第一条，
   * 如果没有满足条件的记录，则返回当前月的10号为限缴日期
   * @param smdm 税种税目代码
   * @param djzclx 登记注册类型代码
   * @param rq  日期
   * @return  Timestamp 征期终止日期
   * @param conn 数据库连接
   * @throws Exception
   */
  private Timestamp getZqzzrq(String smdm, String djzclx, String rq,
                              Connection conn) throws Exception {
    try {
      //定义默认征期截止日期
      long currentTime = System.currentTimeMillis();
      Timestamp time = new Timestamp(currentTime);
      time.setDate(15);

      String sqlWhere = "SELECT ZQZZRQ FROM SBDB.SB_JL_ZQRL WHERE SZSMDM = '"
          + smdm + "' ZQQSRQ <= to_date('"
          + rq + "','yyyymmdd') and ZQZZRQ >= to_date('" + rq
          + "','yyyymmdd') and djzclxdm = '" + djzclx
          + "' ORDER BY ZQQSRQ";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sqlWhere);
      if (rs.next())
        return rs.getTimestamp("zqzzrq");
      else
        return time; //返回默认值，当前月10日
    }
    catch (Exception e) {
      e.printStackTrace();
      throw e;
    }
  }

  /**
   * 查询计算机代码对呀的收款国库
   * @param jsjdm 计算机代码
   * @param swjgdm 税务机关组织机构代码
   * @param conn 数据库连接
   * @return String 国库交换号
   * @throws Exception
   */
  private String getSkgk(String jsjdm, String swjgdm, Connection conn) throws
      Exception {
    try {

      String sqlWhere
          = "SELECT GKJHH FROM DMDB.GY_DM_SWJGZZJG WHERE SWJGZZJGDM = '" +
          swjgdm + "'";
      Statement st = conn.createStatement();
      ResultSet rs = st.executeQuery(sqlWhere);
      if (rs.next())
        return rs.getString("gkjhh");
      else
        return null;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "--获取收款国库出错!");
    }
  }

//    /**
//     * 申报编号的生成,申报编号的生成规则为：计算机代码加上服务器的当前时间的十八位字符串
//     * @param jsjdm 计算机代码
//     * @return sbbh
//     * @throws DeclareException
//     */
//    public String getSbbh(String jsjdm)
//    {
//        //得到当前时间
//        long currentTime = System.currentTimeMillis();
//        String sbbh = jsjdm + Long.toHexString(currentTime);  //标准时间，1970年1月1日0点以来的毫秒数
//        return sbbh;
//    }

  /**
   * 计算分成金额
   * @param je 实缴金额
   * @param bl 分成比例（默认是0。00）
   * @return 分成金额(保留4位小数)
   */
  private BigDecimal getFc(BigDecimal je, BigDecimal bl) {
    BigDecimal fc = je.multiply(bl).setScale(4, BigDecimal.ROUND_HALF_UP);
    return fc;
  }

  /**
   * 通过税种税目代码来确定预算级次
   * @param jsjdm 计算机代码
   * @param szsmdm 税种税目代码
   * @param rq 日期
   * @return Ysjc 预算级次信息
   */
  private Ysjc getYsjc(String jsjdm, String szsmdm, Date rq) {
    Ysjc ysjc = Ysjc.getYsjc(Ysjc.YSJCDM_DF);
    //调用税费管理接口得到预算级次
    try {
      com.ttsoft.bjtax.sfgl.proxy.ServiceProxy sfServiceProxy = new
          com.ttsoft.bjtax.sfgl.proxy.ServiceProxy();
      com.ttsoft.bjtax.sfgl.common.model.Ysjc sfysjc = sfServiceProxy.
          getYsjcInfo(jsjdm, szsmdm, rq);
      if (sfysjc != null) {
        ysjc = new Ysjc(sfysjc.getYsjcdm(), sfysjc.getYsjcmc());
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
    return ysjc;
  }

  /**
   * 处理并发情况，取数据时，锁定表中对应的行，直到更新结束，解锁
   * @param jsjdm 计算机代码8位
   * @param ny 年月yyyyMM 6位
   * @param conn 数据库连接
   * @return String xh
   * @throws Exception
   */
  private String getXh(String jsjdm, String ny, Connection conn) throws
      Exception {
    if (jsjdm == null || jsjdm.equals(""))
      throw new Exception("计算机代码不能为空！");
    if (ny == null || ny.equals(""))
      throw new Exception("录入日期不能为空！");

    String sequence = "0"; //默认
    int xh = 0; //默认为0
    try {
      Statement st = conn.createStatement();
      StringBuffer sqlStringBuffer = new StringBuffer();
      //定义sql语句，批处理，取当前序号
      sqlStringBuffer.append(
          "SELECT XH FROM SBDB.SB_JL_JKPZHKZ WHERE JSJDM = '")
          .append(jsjdm)
          .append("' AND NY='").append(ny).append("'");
      ResultSet rs = st.executeQuery(sqlStringBuffer.toString());
      if (rs.next()) {
        xh = rs.getInt("xh");
      }
      sqlStringBuffer.setLength(0);
      Statement st1 = conn.createStatement();

      //序号自增
      //解析序号
      String xhStr = "";
      if (xh == 0) {
        xhStr = "49"; //从1(ASCII:49)开始
        xh = 48; //返回0(ASCII:48)
      }
      else {
        xhStr = TinyTools.stringToASCII(TinyTools.asciiToString(xh + 1));
      }

      sqlStringBuffer.append("UPDATE SBDB.SB_JL_JKPZHKZ SET XH='")
          .append(xhStr).append("', NY='").append(ny)
          .append("' WHERE JSJDM = '").append(jsjdm).append("'");
      int i = st1.executeUpdate(sqlStringBuffer.toString());
      if (i == 0) {
        sqlStringBuffer.setLength(0);
        Statement stInsert = conn.createStatement();
        sqlStringBuffer.append(
            "INSERT INTO SBDB.SB_JL_JKPZHKZ (JSJDM, XH, NY) VALUES('")
            .append(jsjdm).append("','49','").append(ny).append("')");
        stInsert.addBatch(sqlStringBuffer.toString());
        stInsert.executeBatch();
      }
      sequence = String.valueOf(xh); //当前可用号码是xh+1，此处只返回已用的最大序号
      return sequence;
    }
    catch (Exception e) {
      e.printStackTrace();
      throw new Exception(e.getMessage() + "查询该计算机代码对应的序号失败!");
    }
  }

  /**
   * 申报编号的生成,申报编号的生成规则为：计算机代码加上两位年加上8位序列号
   * @param jsjdm 计算机代码
   * @return sbbh 申报编号
   * @throws DeclareException 异常
   */
  public String getSbbh(String jsjdm) throws Exception {
    return InterFaceProcessor.getSbbh(jsjdm);
  }

  /**
   * 税票号码的生成,税票号码的生成规则为：计算机代码加上两位年加上8位序列号
   * @param jsjdm 计算机代码
   * @return sbbh 申报编号
   * @throws DeclareException 异常
   */
  public String getSphm(String jsjdm) throws Exception {
    return InterFaceProcessor.getSphm(jsjdm);
  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param sbbh 申报编号
   * @return 一票多税缴款书集合
   */
  public List getYpdsJks(String sbbh) throws Exception {
    return InterFaceProcessor.getYpdsJks(sbbh);
  }

  /**
   * 根据申报编号获取一票多税的缴款书
   * @param JksList 一票一税缴款书集合,该集合必须保证属于同一笔申报
   * @return 一票多税缴款书集合
   */
  public List getYpdsJks(List JksList) throws Exception {
    return InterFaceProcessor.getYpdsJks(JksList);
  }

}
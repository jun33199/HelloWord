package com.ttsoft.bjtax.smsb.gghsb.ftp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import com.ttsoft.bjtax.sfgl.common.util.SfStringUtils;
import com.ttsoft.bjtax.sfgl.common.util.SfTimeUtil;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx;
import com.ttsoft.bjtax.shenbao.model.domain.Yhkkxx;
import com.ttsoft.common.util.Debug;
import com.ttsoft.framework.exception.ApplicationException;

/**
 * <p>Title: 个体工商税银行FTP处理</p>
 * <p>Description:
 * 实现
 * (1)文件上传和下载
 * (2)日志文件的读取
 * FTP 服务器配置：用户必须具有读取、删除，改写权限
 * </p>
 * <p>Copyright: Copyright (c) thunis 2004</p>
 * <p>Company: </p>
 * @author zhou jinguang
 * @version 1.0
 */

public class BankFTPMag {

  final String DIR_UP = "up"; //上传目录
  final String DIR_DOWN = "down"; //下载目录
  final String DIR_LOG = "log"; //日志目录

  final String KEY_SUM = "HZWJ"; //汇总文件
  final String KEY_ITEM = "MXWJ"; //明细文件
  final String KEY_FILENAME = "FILENAME"; //文件名
  final String KEY_CONTENT = "CONTENT"; //明细内容
  final String KEY_COUNT = "COUNT"; //总记录数
  final String KEY_TOTALAMOUNT = "TOTALAMOUNT"; //总金额数
  final String KEY_ERRORCOUNT = "ERRORCOUNT"; //错误记录数
  final String KEY_ERRORTOTALAMOUNT = "ERRORTOTALAMOUNT"; //错误总金额数

  HashMap resource = new HashMap();

  FTPClient m_objFtpClient = new FTPClient();

  public BankFTPMag() {
    try {
      Properties properties = new Properties();
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("进入构造方法BankFTPMag():" + "[" + new Date() + "]");
      /* end added by huxiaofeng 2005.8.1*/

      properties.load(getClass().getResourceAsStream(
          "resource.properties"));

      Collection value = properties.values();
      Iterator itevalue = value.iterator();
      Enumeration key = properties.keys();
      while (key.hasMoreElements()) {
        resource.put(key.nextElement(), itevalue.next());
      }
      /* start added by huxiaofeng 2005.8.1*/
      int iPort = Integer.parseInt( (String) resource.get("ftpport"));
      System.out.println("在构造方法BankFTPMag()读取resource.properties完毕");
      System.out.println("ftpip:   " + (String) resource.get("ftpip"));
      System.out.println("iport:   " + iPort);
      System.out.println("ftpuid:  " + (String) resource.get("ftpuid"));
      System.out.println("ftppsw:  " + (String) resource.get("ftppsw"));

      /* end added by huxiaofeng 2005.8.1*/
    }
    catch (Exception ex) {
      /* start added by huxiaofeng 2005.8.1*/
      ex.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      Debug.printException(ex);
    }

  }

  /**
   * 将 扣款数据 生成.txt文件并上传到 ftp Server
   * @param kkDate 扣款数据  HashMap (
   * description of kkDate:
   * 包含两个key:
   * (1)key=HZWJ,对应一个HashMap，该HashMap又包含4个key，对应的对象类型都
   * 为String，分别是
   *          "FILENAME"; //文件名
   *          "COUNT"; //总记录数
   *          "TOTALAMOUNT"; //总金额数
   *(1)key=MXWJ,对应一个HashMap，该HashMap又包含2个key:
   *     key1="FILENAME"; //文件名,对应的对象类型为String
   *     key2="CONTENT";//明细内容,对应的对象类型为List
   *
   * 每次调这个方法会传两个文件，一个为明细信息，一个为汇总信息
   * 需要生成的 .txt描述： 每行一条数据，每条数据各字段间用西文','隔开，行末无', '  字段顺序见与银行接口文档描述
   * @return true 代表上传成功； false 代表上传失败
   */
  public void putKkData(HashMap kkDate) throws Exception {
    try {

      HashMap sum = (HashMap) kkDate.get(KEY_SUM);
      HashMap item = (HashMap) kkDate.get(KEY_ITEM);
      String fileName = (String) sum.get(KEY_FILENAME);
      String dir = getDir(fileName, 0);
      //上传汇总文件
      connect();
      m_objFtpClient.changeWorkingDirectory(dir);
      OutputStream objOutputStream = null;
      String s = null;
      byte[] b = null;
      objOutputStream = m_objFtpClient.storeFileStream(fileName);
      s = (String) sum.get(KEY_COUNT);
      s += ",";
      s += (String) sum.get(KEY_TOTALAMOUNT);
      s += ",";
      b = s.getBytes();
      objOutputStream.write(b, 0, b.length);
      objOutputStream.flush();
      objOutputStream.close();
      if (!m_objFtpClient.completePendingCommand()) {
        throw new ApplicationException("上传汇总文件失败");
      }

      //授权
      connect();
      m_objFtpClient.changeWorkingDirectory(dir);
      /*
                   FTPFile[] ftpfile = m_objFtpClient.listFiles(fileName);
                   if (ftpfile != null) {
          if (ftpfile[0].isFile()) {
              ftpfile[0].setPermission(FTPFile.USER_ACCESS,
                                       FTPFile.WRITE_PERMISSION, true);
              ftpfile[0].setPermission(FTPFile.USER_ACCESS,
                                       FTPFile.READ_PERMISSION, true);
          }
                   }
       */

      //上传明细文件
      try {
        fileName = (String) item.get(KEY_FILENAME);
        objOutputStream = m_objFtpClient.storeFileStream(fileName);
        List list = (List) item.get(KEY_CONTENT);
        StringBuffer sb = null;
        Yhkkhzxx m_objYhkkxx = null;
        String skssksrq = "";
        String skssjsrq = "";
        if (list.size() > 0) {
          m_objYhkkxx = (Yhkkhzxx) list.get(0);
          skssksrq = SfTimeUtil.getDateFromDateTime(m_objYhkkxx.
              getSkssksrq());
          skssjsrq = SfTimeUtil.getDateFromDateTime(m_objYhkkxx.
              getSkssjsrq());
        }
        long iCount = 0;
        for (int i = 0; i < list.size(); i++, iCount++) {
          sb = null;
          m_objYhkkxx = (Yhkkhzxx) list.get(i);
          /*
               发送时所在位置	地税报文字段名
               1	计算机代码
               2	缴款凭证号
               3  银行帐号
               4	纳税人名称
               5  国库组织机关名称
               6	税种税目名称
               7	税务机关组织机构名称
               8
               9	银行代码
               10
               11
               12
               13
               14
               15
               16
               17
               18
               19
               20	实缴金额
               21 扣款不成功原因代码
               22	税种税目代码
               23	税款所属开始日期
               24	税款所属结束日期
               25	预算科目代码
               26	预算级次代码
               27	税务机关组织机构代码
               28	预算级次名称
               29	国库组织机关代码
               30	扣款标志
               31	扣款单位代码
               32 扣款日期
           */
          sb = new StringBuffer(m_objYhkkxx.getJsjdm());
          sb.append(",")
              .append(m_objYhkkxx.getJkpzh())
              .append(",")
              .append(m_objYhkkxx.getZh())
              .append(",")
              .append(m_objYhkkxx.getNsrmc())
              .append(",")
              .append(m_objYhkkxx.getGkzzjgmc())
              .append(",")
              .append(m_objYhkkxx.getSzsmmc())
              .append(",")
              .append(m_objYhkkxx.getSwjgzzjgmc())
              .append(",,")
              .append(m_objYhkkxx.getYhdm())
              .append(",,,,,,,,,,,")
              .append(m_objYhkkxx.getSjje())
              .append(",,")
              .append(m_objYhkkxx.getSzsmdm())
              .append(",")
              .append(skssksrq)
              .append(",")
              .append(skssjsrq)
              .append(",")
              .append(m_objYhkkxx.getYskmdm())
              .append(",")
              .append(m_objYhkkxx.getYsjcdm())
              .append(",")
              .append(m_objYhkkxx.getSwjgzzjgdm())
              .append(",")
              .append(m_objYhkkxx.getYsjcmc())
              .append(",")
              .append(m_objYhkkxx.getGkzzjgdm())
              .append(",00,,,\n"); //,扣款标志,扣款单位代码,扣款日期,\n
          s = sb.toString();
          /* start added by huxiaofeng 2005.8.9*/
          b = s.getBytes("GB2312");
          /* end added by huxiaofeng 2005.8.9*/
          objOutputStream.write(b, 0, b.length);
          if (iCount >= 10000) {
            System.gc();
            iCount = 0;
          }
        }
        objOutputStream.flush();
        objOutputStream.close();
        if (!m_objFtpClient.completePendingCommand()) {
          throw new ApplicationException("上传明细文件失败");
        }

        //授权
        /*
                         connect();
                         m_objFtpClient.changeWorkingDirectory(dir);
                         ftpfile = m_objFtpClient.listFiles(fileName);
                         if (ftpfile != null) {
            if (ftpfile[0].isFile()) {
                ftpfile[0].setPermission(FTPFile.USER_ACCESS,
                                         FTPFile.WRITE_PERMISSION, true);
                ftpfile[0].setPermission(FTPFile.USER_ACCESS,
                                         FTPFile.READ_PERMISSION, true);
            }
                         }
         */
      }
      catch (Exception ex) {
        //如果明细写失败，将汇总删除
        delete( (String) sum.get(KEY_FILENAME), 0);
        if (ex instanceof ApplicationException) {
          throw ex;
        }
        else {
          throw new ApplicationException("扣款数据上传失败", ex);
        }
      }
    }
    finally {
      closeConnection();
    }

  }

  /**
   * 从 ftp Server上获取指定的扣款回执文件，并解析该.txt文件
   *     .txt描述：每行一条数据，每条数据各字段间用西文','隔开，行末无','
   * @param fileName 文件名
   * @return kkhzData HashMap
   *
   * description of kkhzDate:
   * 包含两个key,key值为汇总文件名和明细文件名；
   * (1)key值为汇总文件名的对应的对象为HashMap,该HashMap包含4个key，对应对象类型都为String,
   * 这4个key值为：
   *    "COUNT"; //总记录数
   *    "TOTALAMOUNT"; //总金额数
   *    "ERRORCOUNT"; //错误记录数
   *    "ERRORTOTALAMOUNT"; //错误总金额数
       * (2)key值为明细文件名的对应的对象为List，是com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx的集合
   *
   */
  public HashMap getFtpYhfk(String flag, String[] files) throws Exception {
    try {
      HashMap m_objHashMap = new HashMap();
      String dir = getDir("ab" + flag, 1);
      Vector fileNames = getFileNamesInDir(dir);
      int nn = 0;
      for (Iterator ite = fileNames.iterator(); ite.hasNext(); ) {
        String fileName = (String) ite.next();
        if (fileName.indexOf(flag) == -1) {
          continue;
        }
        boolean find = false;
        for (int i = 0; files != null && i < files.length; i++) {
          if (fileName.equalsIgnoreCase(files[i])) {
            find = true;
            break;
          }
        }
        if (find) {
          continue;
        }
        connect();
        m_objFtpClient.changeWorkingDirectory(dir);
        InputStream objInputStream = m_objFtpClient.retrieveFileStream(
            fileName);
        BufferedReader objBufferedReader = new BufferedReader(new
            InputStreamReader(objInputStream));
        if (fileName.toUpperCase().indexOf("TZ") != -1) {
          String strLine = objBufferedReader.readLine();
          String[] spit = SfStringUtils.split(strLine, ",");
          if (spit.length != 4) {
            throw new ApplicationException("银行回写的汇总文件信息项应为4个");
          }
          HashMap hmSum = new HashMap();
          hmSum.put(KEY_COUNT, spit[0].trim());
          hmSum.put(KEY_TOTALAMOUNT, spit[1].trim());
          hmSum.put(KEY_ERRORCOUNT, spit[2].trim());
          hmSum.put(KEY_ERRORTOTALAMOUNT, spit[3].trim());
          m_objHashMap.put(fileName, hmSum);
        }
        else if (fileName.toUpperCase().indexOf("TM") != -1) {
          ArrayList al = new ArrayList();
          int line = 0;
          while (true) {
            ++line;
            String strLine = objBufferedReader.readLine();
            if (strLine == null) {
              break;
            }
            String[] spit = SfStringUtils.split(strLine, ",");
            if (spit.length != 33) {
              String[] newspit = new String[33];
              for (int i = 0; i < spit.length; i++) {
                newspit[i] = spit[i];
              }
              for (int i = spit.length; i < 33; i++) {
                newspit[i] = "";
              }
              spit = newspit;
              //throw new ApplicationException("银行回写的明细文件" +
              //   fileName + "的第" + line + "行信息项应为33个");
            }
            Yhkkhzxx m_objYhkkhzxx = new Yhkkhzxx();
            /*
             发送时所在位置	地税报文字段名
             1	计算机代码
             2	缴款凭证号
             3  银行帐号
             4	纳税人名称
             5  国库组织机关名称
             6	税种税目名称
             7	税务机关组织机构名称
             8
             9	银行代码
             10
             11
             12
             13
             14
             15
             16
             17
             18
             19
             20	实缴金额
             21 扣款不成功原因代码
             22	税种税目代码
             23	税款所属开始日期
             24	税款所属结束日期
             25	预算科目代码
             26	预算级次代码
             27	税务机关组织机构代码
             28	预算级次名称
             29	国库组织机关代码
             30	扣款标志
             31	扣款单位代码
             32	扣款日期
             */
            m_objYhkkhzxx.setJsjdm(spit[0].trim());
            m_objYhkkhzxx.setJkpzh(spit[1].trim());
            m_objYhkkhzxx.setZh(spit[2].trim());
            m_objYhkkhzxx.setNsrmc(spit[3].trim());
            m_objYhkkhzxx.setGkzzjgmc(spit[4].trim());
            m_objYhkkhzxx.setSzsmmc(spit[5].trim());
            m_objYhkkhzxx.setSwjgzzjgmc(spit[6].trim());
            m_objYhkkhzxx.setYhdm(spit[8].trim());
            m_objYhkkhzxx.setRkje(Double.parseDouble(spit[19].trim()));
            m_objYhkkhzxx.setBcgyydm(spit[20].trim());
            m_objYhkkhzxx.setSzsmdm(spit[21].trim());
            m_objYhkkhzxx.setSkssksrq(SfTimeUtil.getTimestamp(spit[
                22]));
            m_objYhkkhzxx.setSkssjsrq(SfTimeUtil.getTimestamp(spit[
                23]));
            m_objYhkkhzxx.setYskmdm(spit[24].trim());
            m_objYhkkhzxx.setYsjcdm(spit[25].trim());
            m_objYhkkhzxx.setSwjgzzjgdm(spit[26].trim());
            m_objYhkkhzxx.setYsjcmc(spit[27].trim());
            m_objYhkkhzxx.setGkzzjgdm(spit[28].trim());
            m_objYhkkhzxx.setKkbz(spit[29].trim());
            m_objYhkkhzxx.setKkdwdm(spit[30].trim());
            m_objYhkkhzxx.setKkrq(!spit[31].equals("") ?
                                  SfTimeUtil.getTimestamp(spit[31]) : null);
            al.add(m_objYhkkhzxx);
            if (line % 10000 == 0) {
              System.gc();
            }
          }
          m_objHashMap.put(fileName, al);
        }
        objBufferedReader.close();
        objInputStream.close();
        System.gc();
      }
      return m_objHashMap;
    }
    catch (ApplicationException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ApplicationException("下载银行文件失败", ex);
    }
    finally {
      closeConnection();
    }

  }

  /**
   * 检查ftp Server上是否有指定的LS... 日志文件
   * @param fileName 文件名
   * @return true 代表该日志文件存在； false 代表该日志文件不存在
   */
  public boolean checkLsLogFile(String fileName) throws Exception {
    System.out.println("检测是否存在文件:" + fileName);
    String dir = getDir(fileName, 2);
    Vector vector = getFileNamesInDir(dir);
    return vector.contains(fileName);
  }

  /**
   * 从ftp Server上获取指定的LE... 日志文件并解析
   *    日志文件描述：每行一条数据，每条数据各字段间用西文','隔开，行末无','
   * @param fileName 文件名
   * @return leLogFile LE... 日志文件内容(二维数组)
   */
  public String[][] getLeLogFile(String fileName) throws Exception {
    try {
      connect();
      fileName = getDir(fileName, 2) + "/" + fileName;
      InputStream objInputStream = null;
      try {
        objInputStream = m_objFtpClient.retrieveFileStream(
            fileName);
      }
      catch (IOException ex) {
        throw new ApplicationException("不能访问日志文件", ex);
      }
      BufferedReader objBufferedReader = new BufferedReader(new
          InputStreamReader(objInputStream));
      Vector vector = new Vector();
      while (true) {
        String strLine = objBufferedReader.readLine();
        if (strLine == null) {
          break;
        }
        vector.add(strLine);
      }
      String leLogFile[][] = new String[vector.size()][2];
      for (int i = 0; i < vector.size(); i++) {
        String[] array = null;
        array = SfStringUtils.split( ( (String) vector.get(i)), ",");
        if (array.length != 2) {
          throw new ApplicationException("日志文件第" + (i + 1) +
                                         "行信息项应为2个");
        }
        leLogFile[i][0] = array[0];
        leLogFile[i][1] = array[1];
      }
      objBufferedReader.close();
      objInputStream.close();
      return leLogFile;
    }
    catch (ApplicationException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ApplicationException("日志文件读取失败", ex);
    }
    finally {
      closeConnection();
    }

  }

  private void connect() throws Exception {
    try {
      int iPort = Integer.parseInt( (String) resource.get("ftpport"));
      if (m_objFtpClient.isConnected()) {
        m_objFtpClient.logout();
        m_objFtpClient.disconnect();
      }

      m_objFtpClient.connect( (String) resource.get("ftpip"), iPort);
      /* start added by huxiaofeng 2005.8.1*/
     System.out.println("本地地址"+ m_objFtpClient.getLocalAddress().getHostAddress());;
     System.out.println("本地端口"+ m_objFtpClient.getLocalPort());
      System.out.println("connect replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println("connect replyString=" + m_objFtpClient.getReplyString());
      /* end added by huxiaofeng 2005.8.1*/
      m_objFtpClient.login( (String) resource.get("ftpuid"),
                           (String) resource.get("ftppsw"));
      /* start added by huxiaofeng 2005.8.1*/
      m_objFtpClient.setRemoteVerificationEnabled(false);
      System.out.println("login replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println("login replyString=" + m_objFtpClient.getReplyString());
      /* end added by huxiaofeng 2005.8.1*/

      m_objFtpClient.setFileTransferMode(FTPClient.BINARY_FILE_TYPE);
    }
    catch (Exception ex) {
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("执行connect()方法抛出异常");
      System.out.println(" replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println(" replyString=" + m_objFtpClient.getReplyString());
      ex.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      throw new ApplicationException("FTP服务器连接失败", ex);
    }
  }

  private void closeConnection() throws Exception {
    /* start added by huxiaofeng 2005.8.1*/
    System.out.println("closeConnection()执行" + "[" + new Date() + "]");
    /* end added by huxiaofeng 2005.8.1*/
    try {
      if (m_objFtpClient.isConnected()) {
        m_objFtpClient.logout();
        m_objFtpClient.disconnect();
      }
    }
    catch (Exception ex) {
      /* start added by huxiaofeng 2005.8.1*/
      ex.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      throw new ApplicationException("FTP服务器连接关闭失败", ex);
    }
  }

  /**
   * 删除文件
   * @param fileName
   * @param type
   * 0--->删除上传文件
   * 1--->删除下载文件
   * 2--->删除日志文件
   * @throws java.lang.Exception
   */
  public void delete(String fileName, int type) throws Exception {
    try {
      connect();
      String dir = getDir(fileName, type);
      try {
        m_objFtpClient.deleteFile(dir + "/" + fileName);
      }
      catch (IOException ex) {
        throw new ApplicationException("删除文件" + fileName + "失败", ex);
      }
    }
    catch (ApplicationException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ApplicationException("删除文件" + fileName + "失败", ex);
    }
    finally {
      closeConnection();
    }
  }

  /**
   * return file's directory
   * @param fileName
   * @param which 0-->up,1-->down,2-->log
   * @return
   */
  private String getDir(String fileName, int which) {
    String bankCode = fileName.substring(10, 12);
    if (which == 0) {
      return resource.get(bankCode) + "/" + DIR_UP;
    }
    else if (which == 1) {
      return resource.get(bankCode) + "/" + DIR_DOWN;
    }
    else if (which == 2) {
      bankCode = fileName.substring(8, 10);
      return resource.get(bankCode) + "/" + DIR_LOG;
    }
    else {
      return "";
    }
  }

  /**
   * get all file's name in a directory
   * @param dir
   * @return
   * @throws java.lang.Exception
   */
  private Vector getFileNamesInDir(String dir) throws Exception {
    try {
      /* start added by huxiaofeng 2005.8.1*/

      /* end added by huxiaofeng 2005.8.1*/
      connect();

      m_objFtpClient.changeWorkingDirectory(dir);

      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("changeWorkingDirectory replyCode=" +
                         m_objFtpClient.getReplyCode());
      System.out.println("changeWorkingDirectory replyString" +
                         m_objFtpClient.getReplyString());
      /* end added by huxiaofeng 2005.8.1*/
      Vector vector = new Vector();

      FTPFile[] m_objFTPFile = m_objFtpClient.listFiles();

      /* start added by huxiaofeng 2005.8.1*/

      System.out.println("listFiles replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println("listfile replyString" + m_objFtpClient.getReplyString());
      /* end added by huxiaofeng 2005.8.1*/
      for (int i = 0; i < m_objFTPFile.length; i++) {
        vector.add(m_objFTPFile[i].getName());
      }
      return vector;
    }
    catch (ApplicationException ex) {
      /* start added by huxiaofeng 2005.8.1*/
      ex.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      throw ex;
    }
    catch (Exception ex) {
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("执行getFileNamesInDir方法抛出异常");
      System.out.println("last replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println("last replyString" + m_objFtpClient.getReplyString());
      ex.printStackTrace();

      /* end added by huxiaofeng 2005.8.1*/
      throw new ApplicationException("不能获取目录" + dir + "下文件列表", ex);
    }
    finally {
      closeConnection();
    }
  }

  public static void main(String[] args) {
    String[] s = SfStringUtils.split("1,,2,3", ",");
    String[] news = new String[4];
    news[0] = s[0];
    news[1] = s[1];
    news[2] = "3";
    news[3] = "4";
    s = news;
    for (int i = 0; i < s.length; i++) {
      Debug.out(s[i]);
    }
  }
  /* start added by huxiaofeng 2005.8.1*/
  //解决字符乱码
  /* end added by huxiaofeng 2005.8.1*/
  public static String encode(String ss, String from_encoding, String to_encoding)
       {
           if (ss == null)
           {
               return null;
           }

           String returnValue = ss;
           try
           {
               byte[] temp = ss.getBytes(from_encoding);
               returnValue = new String(temp, to_encoding);
           }
           catch (Exception ex)
           {
           }

           return returnValue;
       }

}

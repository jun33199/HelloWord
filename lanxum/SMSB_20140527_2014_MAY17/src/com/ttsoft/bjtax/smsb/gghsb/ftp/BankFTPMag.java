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
 * <p>Title: ���幤��˰����FTP����</p>
 * <p>Description:
 * ʵ��
 * (1)�ļ��ϴ�������
 * (2)��־�ļ��Ķ�ȡ
 * FTP ���������ã��û�������ж�ȡ��ɾ������дȨ��
 * </p>
 * <p>Copyright: Copyright (c) thunis 2004</p>
 * <p>Company: </p>
 * @author zhou jinguang
 * @version 1.0
 */

public class BankFTPMag {

  final String DIR_UP = "up"; //�ϴ�Ŀ¼
  final String DIR_DOWN = "down"; //����Ŀ¼
  final String DIR_LOG = "log"; //��־Ŀ¼

  final String KEY_SUM = "HZWJ"; //�����ļ�
  final String KEY_ITEM = "MXWJ"; //��ϸ�ļ�
  final String KEY_FILENAME = "FILENAME"; //�ļ���
  final String KEY_CONTENT = "CONTENT"; //��ϸ����
  final String KEY_COUNT = "COUNT"; //�ܼ�¼��
  final String KEY_TOTALAMOUNT = "TOTALAMOUNT"; //�ܽ����
  final String KEY_ERRORCOUNT = "ERRORCOUNT"; //�����¼��
  final String KEY_ERRORTOTALAMOUNT = "ERRORTOTALAMOUNT"; //�����ܽ����

  HashMap resource = new HashMap();

  FTPClient m_objFtpClient = new FTPClient();

  public BankFTPMag() {
    try {
      Properties properties = new Properties();
      /* start added by huxiaofeng 2005.8.1*/
      System.out.println("���빹�췽��BankFTPMag():" + "[" + new Date() + "]");
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
      System.out.println("�ڹ��췽��BankFTPMag()��ȡresource.properties���");
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
   * �� �ۿ����� ����.txt�ļ����ϴ��� ftp Server
   * @param kkDate �ۿ�����  HashMap (
   * description of kkDate:
   * ��������key:
   * (1)key=HZWJ,��Ӧһ��HashMap����HashMap�ְ���4��key����Ӧ�Ķ������Ͷ�
   * ΪString���ֱ���
   *          "FILENAME"; //�ļ���
   *          "COUNT"; //�ܼ�¼��
   *          "TOTALAMOUNT"; //�ܽ����
   *(1)key=MXWJ,��Ӧһ��HashMap����HashMap�ְ���2��key:
   *     key1="FILENAME"; //�ļ���,��Ӧ�Ķ�������ΪString
   *     key2="CONTENT";//��ϸ����,��Ӧ�Ķ�������ΪList
   *
   * ÿ�ε���������ᴫ�����ļ���һ��Ϊ��ϸ��Ϣ��һ��Ϊ������Ϣ
   * ��Ҫ���ɵ� .txt������ ÿ��һ�����ݣ�ÿ�����ݸ��ֶμ�������','��������ĩ��', '  �ֶ�˳��������нӿ��ĵ�����
   * @return true �����ϴ��ɹ��� false �����ϴ�ʧ��
   */
  public void putKkData(HashMap kkDate) throws Exception {
    try {

      HashMap sum = (HashMap) kkDate.get(KEY_SUM);
      HashMap item = (HashMap) kkDate.get(KEY_ITEM);
      String fileName = (String) sum.get(KEY_FILENAME);
      String dir = getDir(fileName, 0);
      //�ϴ������ļ�
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
        throw new ApplicationException("�ϴ������ļ�ʧ��");
      }

      //��Ȩ
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

      //�ϴ���ϸ�ļ�
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
               ����ʱ����λ��	��˰�����ֶ���
               1	���������
               2	�ɿ�ƾ֤��
               3  �����ʺ�
               4	��˰������
               5  ������֯��������
               6	˰��˰Ŀ����
               7	˰�������֯��������
               8
               9	���д���
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
               20	ʵ�ɽ��
               21 �ۿ�ɹ�ԭ�����
               22	˰��˰Ŀ����
               23	˰��������ʼ����
               24	˰��������������
               25	Ԥ���Ŀ����
               26	Ԥ�㼶�δ���
               27	˰�������֯��������
               28	Ԥ�㼶������
               29	������֯���ش���
               30	�ۿ��־
               31	�ۿλ����
               32 �ۿ�����
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
              .append(",00,,,\n"); //,�ۿ��־,�ۿλ����,�ۿ�����,\n
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
          throw new ApplicationException("�ϴ���ϸ�ļ�ʧ��");
        }

        //��Ȩ
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
        //�����ϸдʧ�ܣ�������ɾ��
        delete( (String) sum.get(KEY_FILENAME), 0);
        if (ex instanceof ApplicationException) {
          throw ex;
        }
        else {
          throw new ApplicationException("�ۿ������ϴ�ʧ��", ex);
        }
      }
    }
    finally {
      closeConnection();
    }

  }

  /**
   * �� ftp Server�ϻ�ȡָ���Ŀۿ��ִ�ļ�����������.txt�ļ�
   *     .txt������ÿ��һ�����ݣ�ÿ�����ݸ��ֶμ�������','��������ĩ��','
   * @param fileName �ļ���
   * @return kkhzData HashMap
   *
   * description of kkhzDate:
   * ��������key,keyֵΪ�����ļ�������ϸ�ļ�����
   * (1)keyֵΪ�����ļ����Ķ�Ӧ�Ķ���ΪHashMap,��HashMap����4��key����Ӧ�������Ͷ�ΪString,
   * ��4��keyֵΪ��
   *    "COUNT"; //�ܼ�¼��
   *    "TOTALAMOUNT"; //�ܽ����
   *    "ERRORCOUNT"; //�����¼��
   *    "ERRORTOTALAMOUNT"; //�����ܽ����
       * (2)keyֵΪ��ϸ�ļ����Ķ�Ӧ�Ķ���ΪList����com.ttsoft.bjtax.shenbao.model.domain.Yhkkhzxx�ļ���
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
            throw new ApplicationException("���л�д�Ļ����ļ���Ϣ��ӦΪ4��");
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
              //throw new ApplicationException("���л�д����ϸ�ļ�" +
              //   fileName + "�ĵ�" + line + "����Ϣ��ӦΪ33��");
            }
            Yhkkhzxx m_objYhkkhzxx = new Yhkkhzxx();
            /*
             ����ʱ����λ��	��˰�����ֶ���
             1	���������
             2	�ɿ�ƾ֤��
             3  �����ʺ�
             4	��˰������
             5  ������֯��������
             6	˰��˰Ŀ����
             7	˰�������֯��������
             8
             9	���д���
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
             20	ʵ�ɽ��
             21 �ۿ�ɹ�ԭ�����
             22	˰��˰Ŀ����
             23	˰��������ʼ����
             24	˰��������������
             25	Ԥ���Ŀ����
             26	Ԥ�㼶�δ���
             27	˰�������֯��������
             28	Ԥ�㼶������
             29	������֯���ش���
             30	�ۿ��־
             31	�ۿλ����
             32	�ۿ�����
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
      throw new ApplicationException("���������ļ�ʧ��", ex);
    }
    finally {
      closeConnection();
    }

  }

  /**
   * ���ftp Server���Ƿ���ָ����LS... ��־�ļ�
   * @param fileName �ļ���
   * @return true �������־�ļ����ڣ� false �������־�ļ�������
   */
  public boolean checkLsLogFile(String fileName) throws Exception {
    System.out.println("����Ƿ�����ļ�:" + fileName);
    String dir = getDir(fileName, 2);
    Vector vector = getFileNamesInDir(dir);
    return vector.contains(fileName);
  }

  /**
   * ��ftp Server�ϻ�ȡָ����LE... ��־�ļ�������
   *    ��־�ļ�������ÿ��һ�����ݣ�ÿ�����ݸ��ֶμ�������','��������ĩ��','
   * @param fileName �ļ���
   * @return leLogFile LE... ��־�ļ�����(��ά����)
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
        throw new ApplicationException("���ܷ�����־�ļ�", ex);
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
          throw new ApplicationException("��־�ļ���" + (i + 1) +
                                         "����Ϣ��ӦΪ2��");
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
      throw new ApplicationException("��־�ļ���ȡʧ��", ex);
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
     System.out.println("���ص�ַ"+ m_objFtpClient.getLocalAddress().getHostAddress());;
     System.out.println("���ض˿�"+ m_objFtpClient.getLocalPort());
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
      System.out.println("ִ��connect()�����׳��쳣");
      System.out.println(" replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println(" replyString=" + m_objFtpClient.getReplyString());
      ex.printStackTrace();
      /* end added by huxiaofeng 2005.8.1*/
      throw new ApplicationException("FTP����������ʧ��", ex);
    }
  }

  private void closeConnection() throws Exception {
    /* start added by huxiaofeng 2005.8.1*/
    System.out.println("closeConnection()ִ��" + "[" + new Date() + "]");
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
      throw new ApplicationException("FTP���������ӹر�ʧ��", ex);
    }
  }

  /**
   * ɾ���ļ�
   * @param fileName
   * @param type
   * 0--->ɾ���ϴ��ļ�
   * 1--->ɾ�������ļ�
   * 2--->ɾ����־�ļ�
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
        throw new ApplicationException("ɾ���ļ�" + fileName + "ʧ��", ex);
      }
    }
    catch (ApplicationException ex) {
      throw ex;
    }
    catch (Exception ex) {
      throw new ApplicationException("ɾ���ļ�" + fileName + "ʧ��", ex);
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
      System.out.println("ִ��getFileNamesInDir�����׳��쳣");
      System.out.println("last replyCode=" + m_objFtpClient.getReplyCode());
      System.out.println("last replyString" + m_objFtpClient.getReplyString());
      ex.printStackTrace();

      /* end added by huxiaofeng 2005.8.1*/
      throw new ApplicationException("���ܻ�ȡĿ¼" + dir + "���ļ��б�", ex);
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
  //����ַ�����
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

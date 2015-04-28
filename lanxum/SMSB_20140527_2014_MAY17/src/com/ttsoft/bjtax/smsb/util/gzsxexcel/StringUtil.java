package com.ttsoft.bjtax.smsb.util.gzsxexcel;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.regex.Pattern;

/**
 * <p>Title: Epx'Run�ַ��������߰�</p>
 * <p>Description: �����󲿷ݳ������ַ���������</p>
 * <p>Copyright: Copyright (c) 2002 </p>
 * <p>Company: OSACC</p>
 * @author BBC
 * @version 2.1
 */
public class StringUtil
{

    /**
     * ��������������ַ�������
     */
    private static Random randGen = null;

    /**
     * ���ֺ���ĸ����󼯣�������������ַ���
     */
    private static char numbersAndLetters[] = null;

    /**
     * @roseuid 3F39FE460259
     */
    public StringUtil()
    {

    }

    /**
     * ����Zip��ʽѹ���ַ���
     * @param upzipStr ѹ���ַ���
     * @return ѹ���ַ���
     * @roseuid 3F3A00B60256
     */
    public static String compress(String upzipStr)
    {
        String c = "";
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(outBuffer);
            //�����ǣ�upzipStr.getBytes("ISO8859_1")
            gzip.write(upzipStr.getBytes());
            gzip.close();
            c = new String(outBuffer.toByteArray(), "ISO-8859-1");
            outBuffer.reset();
        }
        catch (IOException ex) {
            System.out.println("compress - error:" + ex.getMessage());
        }
        return c;
    }

    /**
     * ����Zip��ʽ��ѹ�ַ���
     * @param zipStr ѹ���ַ���
     * @return ���
     * @roseuid 3F3A00B602C4
     */
    public static String decompress(String zipStr)
    {
        String c = "";
        try {
            ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
            ByteArrayInputStream inBuffer = new ByteArrayInputStream(zipStr.
                    getBytes(
                            "ISO8859_1"));
            GZIPInputStream gunzip = new GZIPInputStream(inBuffer);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                outBuffer.write(buffer, 0, n);
            }
            c = new String(outBuffer.toByteArray(), "ISO-8859-1");
        }
        catch (IOException ex) {
            System.out.println("compress - error:" + ex.getMessage());
        }
        return StringUtil.ISO2GBK(c);
    }

    /**
     * �����س����з�
     * @param str ��������ַ���
     * @return finalStr ����֮����ַ���
     * @roseuid 3F3A00B6033C
     */
    public static String delEnter(String str)
    {
        String finalStr = "";
        if (str == null || str.equals("")) {
            return str;
        }
        for (int ii = 0; ii < str.length(); ii++) {
            if ((str.charAt(ii) != 13) && (str.charAt(ii) != 10)) {
                finalStr += str.charAt(ii);
            }
        } //end for
        return finalStr;
    }

    /**
     * ��̬��������ԭ�ַ�����sourceString���Էָ��־��divideFlag���ָ�����طָ���
     * �ַ������顣����ָ��־�����ڣ���Żص������н�����һ��Ԫ�أ���Ԫ��Ϊԭ�ַ�����
     * sourceString��
     * @param source ԭ�ַ���
     * @param divideFlag �ָ��־
     * @return String[] ���طָ����ַ�������
     * @roseuid 3F3A00B603AB
     */
    public static String[] divideString(String source, String divideFlag)
    {
        StringTokenizer st = new StringTokenizer(source, divideFlag);
//�����鳤��
        int count = st.countTokens();
        String apple[] = new String[count];
//�������
        for (int ii = 0; ii < count; ii++) {
            apple[ii] = st.nextToken();
        } //end for
        return apple;
    }

    /**
     * End method null2Str
     * ���ַ������зָ���Զ���ķָ��headStr,tailStr
     * ��Ϊ��Ǽǣ����Ըñ��Ϊͷβ���ַ������зָ����
     * һ���ַ������顣
     * @param str �������ַ���
     * @param head ͷ�ָ���
     * @param tail β�ָ���
     * @return finalStr �������ַ�������
     * @roseuid 3F3A00B70077
     */
    public static String[] divideString(String str, String head, String tail)
    {
        java.util.Vector vector = new java.util.Vector(1);
        int start = str.indexOf(head);
        int end = str.indexOf(tail);
//��ͷ��־��β��־�ĳ���
        int headLen = head.length();
        int tailLen = tail.length();
        int count = 0;
//����ͷ��־
        while (start >= 0 && end >= 0) {
            String tmpStr = str.substring(start + headLen, end);
            //���Ȳ�Ϊ0��洢
            if (tmpStr != null && !tmpStr.equals("")) {
                vector.add(count++, tmpStr);
                //������һ���ַ���ͷβ��־
            }
            start = str.indexOf(head, end + tailLen);
            end = str.indexOf(tail, start);
        } //end while
//��ƥ�������򷵻ؿ�
        if (count == 0) {
            return null;
        }
        String[] apple = new String[count];
//��vector�е�������ת��Ϊ����
        for (int ii = 0; ii < count; ii++) {
            apple[ii] = (String) vector.get(ii);
        } //end for
        return apple;
    }

    /**
     * ��̬���������ַ��������GB2312ת����ΪIso8859_1��
     * @param strIn ����ǰ���ַ���
     * @return strOut �������ַ���
     * @roseuid 3F3A00B70167
     */
    public static String GB23122ISO(String strIn)
    {
        String strOut = null;
        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("gb2312");
            strOut = new String(b, "ISO8859_1");
        }
        catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return strOut;
    }

    /**
     * ��̬���������ַ��������GBKת����ΪIso8859_1��
     * @param strIn ����ǰ���ַ���
     * @return strOut �������ַ���
     * @roseuid 3F3A00B701DF
     */
    public static String GBK2ISO(String strIn)
    {
        String strOut = null;
        if (TypeChecker.isEmpty(strIn)) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        }
        catch (java.io.UnsupportedEncodingException e) {
            return "";
        } //end try - catch
        return strOut;
    }

    /**
     * �ж���Ŀ���ַ����е�ÿһ���ַ��Ƿ���Դ�ַ����д���
     * @param sourceString Դ�ַ���
     * @param targetString ��Ŀ���ַ���ɵ�Ŀ���ַ���
     * @return true ��Ŀ���ַ�����  false ��Ŀ���ַ�����
     * @roseuid 3F3A00B70258
     */
    public static boolean getCharInString(String sourceString,
                                          String targetString)
    {
//�ж��ַ����Ƿ�Ϸ�������Ϸ������� false
        if (TypeChecker.isEmpty(targetString) ||
            TypeChecker.isEmpty(sourceString)) {
            return false;
        }
//��Ŀ���ַ���ת���ַ�����
        char[] charArray = targetString.toCharArray();
        int length = charArray.length;
        for (int i = 0; i < length; i++) {
            if (sourceString.indexOf(charArray[i]) >= 0) {
                return true;
            }
        } //end for
        return false;
    }

    /**
     * ��ָ�����ַ���ת��Ϊ���Ҹ�ʽ ��12,333,333.00
     * @param data ָ���������ַ���������Ϊ��
     * @return ���ظ�ʽ�ַ���
     * @roseuid 3F3A00B7030C
     */
    public static String getCurrency(String data)
    {
//if(!TypeChk.checkFloat(data)) return "";
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        return nf.format(Double.parseDouble(data));
    }

    /**
     * ���ַ���ת��Ϊ�����������ֵΪ���򷵻�Ĭ��ֵ��
     * @param value ��ת�����ַ���
     * @param defaultValue Ĭ��ֵ
     * @return ������
     * @roseuid 3F3A00B7038E
     */
    public static double getDouble(String value, double defaultValue)
    {
        if (TypeChecker.isEmpty(value) || !TypeChecker.isFloat(value)) {
            return defaultValue;
        }
        return Double.parseDouble(value);
    }

    /**
     * ��ָ�����ַ���ת��Ϊ��ʽ 12,333,333.00
     * @param data ָ���������ַ���������Ϊ��
     * @return ���ظ�ʽ�ַ���
     * @roseuid 3F3A00B8005A
     */
    public static String getFormatData(String data)
    {
        return getCurrency(data).substring(1);
    }

    /**
     * ��̬�������Ȱ��ַ��������Iso8859_1ת����ΪGBK����
     * �����ַ�������ʵ���ȡ�
     * @param strIn ��������ַ���
     * @return length �������ַ������� -1ʧ��
     * @roseuid 3F3A00B800DC
     */
    public static int getGBKLength(String strIn)
    {
        int length = 0;
        byte buff[];
        if (strIn == null || (strIn.trim()).equals("")) {
            return length;
        }
        try {
            buff = strIn.getBytes("GBK");
            length = buff.length;
        }
        catch (java.io.UnsupportedEncodingException e) {
            return -1;
        } //end try - catch
        return length;
    }

    /**
     * ��Ŀ���ַ���ת����ʮ�������ַ���
     * @param  sourceString  ��Ҫת����Ŀ���ַ���
     * @return  ʮ�������ַ���
     * @roseuid 3F3A00B8015F
     */
    public static String getHexString(String sourceString)
    {
//��Ŀ���ַ���ת�����ֽ�����
        byte[] hash = sourceString.getBytes();
        StringBuffer buf = new StringBuffer(hash.length * 2);
        for (int i = 0; i < hash.length; i++) {
            if (((int) hash[i] & 0xff) < 0x10) {
                buf.append("0");
            } //end if
            buf.append(Long.toString((int) hash[i] & 0xff, 16));
        } //end for
        return buf.toString();
    }

    /**
     * ���ַ���ת��Ϊ���֣����ֵΪ���򷵻�Ĭ��ֵ��
     * @param value ��ת�����ַ���
     * @param defaultValue Ĭ��ֵ
     * @return ������
     * @roseuid 3F3A00B801E1
     */
    public static int getInt(String value, int defaultValue)
    {
        if (TypeChecker.isEmpty(value) || !TypeChecker.isInteger(value)) {
            return defaultValue;
        }
        return Integer.parseInt(value);
    }


    /**
     * ���ַ���ת��Ϊ���֣����ֵΪ���򷵻�Ĭ��ֵ��
     * @param value ��ת�����ַ���
     * @param defaultValue Ĭ��ֵ
     * @return ������
     * @roseuid 3F3A00B801E1
     */
    public static String getThreeStr(String value)
    {
        System.out.println(value);
        int number =Integer.parseInt(value)+1;
        String str;
        str = String.valueOf(number);
        if (str.length() == 1) {
            str = "00" + str;
        } else if (str.length() == 2) {
            str = "0" + str;
        }
        return str;
    }

    /**
     * ���ַ���ת��Ϊ���������֣����ֵΪ���򷵻�Ĭ��ֵ��
     * @param value ��ת�����ַ���
     * @param defaultValue Ĭ��ֵ
     * @return ��������
     * @roseuid 3F3A00B8029F
     */
    public static final long getLong(String value, long defaultValue)
    {
        if (TypeChecker.isEmpty(value) || !TypeChecker.isLong(value)) {
            return defaultValue;
        }
        return Long.parseLong(value);
    }

    /**
     * ��̬����������滻����ַ��������ø÷���ʱ�����StrPro
     * ����������������г�ʼ����
     * @param sourceString ԭ�ַ���
     * @param targetString ���滻���ַ���
     * @param replaceString �滻�ַ���
     * @return finalString �������ַ���
     * @roseuid 3F3A00B8035D
     */
    public static String getReplaceString(String sourceString,
                                          String targetString,
                                          String replaceString)
    {
// ��sourceString�ַ����У������s��ʼ������targetString
// ��ͬ���Ӵ�ʱ��e>=0��������result����Ӹ��Ӵ�֮ǰ���ַ�����
// Ȼ������滻�ַ�������s��ֵ�����ѭ����
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = sourceString.indexOf(targetString, s)) >= 0) {
            result.append(sourceString.substring(s, e));
            result.append(replaceString);
            s = e + targetString.length();
        } // End while
        result.append(sourceString.substring(s));
        String finalString = result.toString();
        return finalString;
    }

    /**
     * ��̬����������滻����ַ���(�滻ʱ���Դ�Сд)�����ø÷���ʱ�����StrPro
     * ����������������г�ʼ����
     * @param sourceString ԭ�ַ���
     * @param targetString ���滻���ַ���
     * @param replaceString �滻�ַ���
     * @return finalString �������ַ���
     * @roseuid 3F3A00B9008E
     */
    public static String getReplaceStringIgnoreCase(String sourceString,
            String targetString, String replaceString)
    {
        if (sourceString == null) {
            return null;
        }
//ת��Сд�ַ���
        String lcLine = sourceString.toLowerCase();
        String lcOldString = targetString.toLowerCase();
        int i = 0;
        if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
            char[] line2 = sourceString.toCharArray();
            char[] newString2 = replaceString.toCharArray();
            int oLength = targetString.length();
            StringBuffer buf = new StringBuffer(line2.length);
            buf.append(line2, 0, i).append(newString2);
            i += oLength;
            int j = i;
            while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
                buf.append(line2, j, i - j).append(newString2);
                i += oLength;
                j = i;
            } //end while
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        } //end if
        return sourceString;
    }

    /**
     * �õ�ָ����Ŀ�Ŀո�
     * @param num - ָ����Ŀ
     * @return java.lang.String
     * @roseuid 3F3A00B9019C
     */
    public static String getSpace(int num)
    {
        return getString(num, " ");
    }

    /**
     * end method getCurrency
     * ��ָ�����ַ���ǰ�����ǰ���ַ���ʹ�ܳ���Ϊָ��ֵ
     * @param str Դ�ַ���
     * @param len �ܳ���
     * @param head ǰ���ַ�
     * @return ����ӷ���
     * @roseuid 3F3A00B90228
     */
    public static String getStrAddHead(String str, int len, char head)
    {
        if (TypeChecker.isEmpty(str)) {
            str = "";
        }
        int strLen = str.length();
        for (int ii = 0; ii < (len - strLen); ii++) {
            str = String.valueOf(head) + str;
        }
        return str;
    }

    /**
     * ��̬����������һ��ͷ�ַ�����headString����һ��β�ַ�����tailString����
     * ԭ�ַ�����sourceString���а���Ŀ���ַ�����targetString���Ĵ�ͷ��β��
     * �ַ����������ء�
     * @param source ԭ�ַ���
     * @param target Ŀ���ַ���
     * @param head ͷ�ַ���
     * @param tail β�ַ���
     * @return finalString ����֮����ַ���
     * @roseuid 3F3A00B90341
     */
    public static String getStrFromTo(String source, String target, String head,
                                      String tail)
    {
//Ϊ���򷵻�
        if (source == null || source.equals("")) {
            return "";
        }

        String currStr = source;
        int index = 0, s = 0, e = 0;
//����head
        index = currStr.indexOf(head);
        if (index >= 0) {
            s = index + head.length();
            e = s;
            currStr = currStr.substring(e);
            //����target
            index = currStr.indexOf(target);
        }
        if (index >= 0) {
            e += index + target.length();
            currStr = currStr.substring(e - s);
            //����tail
            index = currStr.indexOf(tail);
        }
//�Ƿ���Ŀ���ִ�
        if (index >= 0) {
            e += index;
        }
        else {
            s = 0;
            e = 0;
        } //end if

        return source.substring(s, e);
    }

    /**
     * ��̬����������һ��ͷ�ַ�����headString����һ��β
     * �ַ�����tailString����ԭ�ַ�����sourceString����
     * �Ĵ�ͷ��β���ַ����������ء�
     * @param source ԭ�ַ���
     * @param head ͷ�ַ���
     * @param tail β�ַ���
     * @return finalString ����֮����ַ���
     * @roseuid 3F3A00BA00C1
     */
    public static String getStrFromTo(String source, String head, String tail)
    {
        String finalString = "";
        if (source == null || source.equals("")) {
            return finalString;
        }
        int e = 0, s = 0;
        e = source.indexOf(head);
        s = source.indexOf(tail, e + head.length());
//��һ���Ҳ����򷵻ؿ�
        if (e >= 0 && s > 0 && s > e) {
            finalString = source.substring(e + head.length(), s);
        } //end if
        return finalString;
    }

    /**
     * �õ�ָ����Ŀ���ַ���
     * @param num - �ַ���Ŀ
     * @param str - �ظ��ַ���
     * @return java.lang.String
     * @roseuid 3F3A00BA01DA
     */
    public static String getString(int num, String str)
    {
        String tmpStr = "";
        for (int ii = 0; ii < num; ii++) {
            str += tmpStr;
        } //end for
        return tmpStr;
    }

    /**
     * �õ�ĳһ���ַ�����ָ���ӷ�������
     * @param srcStr
     * @param subStr
     * @return int
     * @roseuid 3F3A00BA02B6
     */
    public static int getSubStrNum(String srcStr, String subStr)
    {
        int count = 0;
//�ж��ַ����Ƿ�Ϸ�
        if (TypeChecker.isEmpty(srcStr)) {
            return count;
        }
        if (subStr == null || subStr.equals("")) {
            return count;
        }
        int pos = 0;
        while ((pos = srcStr.indexOf(subStr)) >= 0) {
            count++;
            srcStr = srcStr.substring(pos + 1);
        } //end while
        return count;
    }

    /**
     * ����ָ���������йؼ��ֵ�ֵ���ؼ��ֻ�ֵ�������򷵻ؿա��η�����Ҫ�������ֶ��ж���
     * ʱ������null����
     * @param Row ���������
     * @param name �ؼ�������
     * @return �ؼ���ֵ���ؼ��ֻ�ֵ�������򷵻ؿա�
     * @roseuid 3F3A00BA0388
     */
    public static String getValue(HashMap Row, String name)
    {
        if (Row == null || TypeChecker.isEmpty(name)) {
            return "";
        }
        return (Row.get(name) == null) ? "" : (String) Row.get(name);
    }

    /**
     * ��̬���������ַ��������Iso8859_1ת����ΪGBK��
     * @param strIn ����ǰ���ַ���
     * @return strOut �������ַ���
     * @roseuid 3F3A00BB0091
     */
    public static String ISO2GB2312(String strIn)
    {
        String strOut = null;
        if (TypeChecker.isEmpty(strIn)) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "gb2312");
        }
        catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
        return strOut;
    }

    /**
     * ��̬���������ַ��������Iso8859_1ת����ΪGBK��
     * @param strIn ����ǰ���ַ���
     * @return strOut �������ַ���
     * @roseuid 3F3A00BB0127
     */
    public static String ISO2GBK(String strIn)
    {
        String strOut = null;
        if (TypeChecker.isEmpty(strIn)) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        }
        catch (java.io.UnsupportedEncodingException e) {
            return "";
        } //end try_catch
        return strOut;
    }

    /**
     * �ж�targetString�Ƿ���sourceString���Ӵ�
     * @param sourceString ԭ�ַ���
     * @param targetString Ŀ���ַ���
     * @return boolean �ǣ�����true�����򷵻�false
     * @roseuid 3F3A00BB01B3
     */
    public static boolean isSubString(String sourceString, String targetString)
    {
        int e = 0;
        e = sourceString.indexOf(targetString);
        if (e >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * ����ַ�����str��Ϊnull������ת����Ϊ���ַ���""��
     * @param str ��������ַ���
     * @return str �������ַ���
     * @roseuid 3F3A00BB02A3
     */
    public static String null2Str(String str)
    {
        return (str == null) ? "" : str;
    }

    /**
     * End method null2Str
     * ����ַ�����str��Ϊnull������ת����Ϊ�����ַ�����otherStr����
     * @param str �������ַ���
     * @param otherStr ת������ַ���
     * @return str �������ַ���
     * @roseuid 3F3A00BB033A
     */
    public static String null2Str(String str, String otherStr)
    {
        return (str == null) ? otherStr : str;
    }

    /**
     * ����һ�����峤�ȵ�����ַ���
     * @param length  ���ص�����ַ����ĳ���
     * @return ��������ַ���
     * @roseuid 3F3A00BC0038
     */
    public static String randomString(int length)
    {
        if (length < 1) {
            return null;
        }
        if (randGen == null) {
            randGen = new Random();
            //���ֺ���ĸ�ļ���
            numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz" +
                                 "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").
                                toCharArray();
        } //end if
//��������ַ���
        char[] randBuffer = new char[length];
        for (int i = 0; i < randBuffer.length; i++) {
            randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
        } //end for
        return new String(randBuffer);
    }

    /**
     * �滻�����õ�HTMLҳ�����ַ��������е�\n,\t,�ո����
     * @param str ��Ҫ������ַ���
     * @return str ����֮����ַ���
     * @roseuid 3F3A00BC00D8
     */
    public static String replaceAllHtmlEnter(String str)
    {
        if (str == null || str.equals("")) {
            return str;
        }
        str = getReplaceString(str, "\n", "<br>");
        str = getReplaceString(str, "\t", "&nbsp;&nbsp;&nbsp;&nbsp;");
        str = getReplaceString(str, "  ", "&nbsp;&nbsp;");
        return str;
    }

    /**
     * �滻�ַ��������е�<,>,'����
     * @param str ��Ҫ������ַ���
     * @return str ����֮����ַ���
     * @roseuid 3F3A00BC0178
     */
    public static String replaceAllHtmlStr(String str)
    {
        if (str == null || str.equals("")) {
            return "";
        }
        str = getReplaceString(str, "<", "&lt");
        str = getReplaceString(str, ">", "&gt");
        str = getReplaceString(str, "\"", "&quot;");
        return str;
    }

    /**
     * �ѻس����з��滻Ϊ�ض����ַ���
     * @param str ��������ַ���
     * @param repStr �滻��Ŀ���ַ���
     * @return str ����֮����ַ���
     * @roseuid 3F3A00BC020F
     */
    public static String replaceEnter(String str, String repStr)
    {

        String strLeft = "";
        String strRight = "";
        if (str == null || str.equals("")) {
            return str;
        }

        int length = str.length();
        char a;
        for (int ii = 0; ii < str.length(); ii++) {
            a = str.charAt(ii);
            if (a == 10) {
                strLeft = str.substring(0, ii);
                strRight = str.substring(ii + 1);
                str = strLeft + repStr + strRight;
            } //end if
            else if (a == 13) {
                strLeft = str.substring(0, ii);
                strRight = str.substring(ii + 1);
                str = strLeft + strRight;
            } //end if
        } //end for
        return str;
    }

    /**
     * ʹ��ָ���Ĺ��������е��ַ��滻Դ�ַ�����ʹ��"#"����Ĳ��֣����������
     * ����������û�ж��壬���滻Ϊ""��
     * @param str Դ�ַ���
     * @param ht  ����������
     * @return �滻���
     * @roseuid 3F3A00BC02FF
     */
    public static String repStr4List(String str, java.util.HashMap ht)
    {
        String tmpStr, value;
        while (!TypeChecker.isEmpty(tmpStr = getStrFromTo(str, "#", "#"))) {
            value = (String) ht.get(tmpStr);
            if (TypeChecker.isEmpty(value)) {
                value = "";
            }
            str = getReplaceString(str, "#" + tmpStr + "#", value);
        } //end while
        return str;
    }

    /**
     * �ַ����������Ƿ����ָ�����ַ�����
     * @param strings �ַ�������
     * @param string �ַ���
     * @param caseSensitive �Ƿ��Сд����
     * @return ����ʱ����true�����򷵻�false
     * @since  0.4
     * @roseuid 3F3A00BD0007
     */
    public static boolean contains(String[] strings, String string,
                                   boolean caseSensitive)
    {
        for (int i = 0; i < strings.length; i++) {
            if (caseSensitive == true) {
                if (strings[i].equals(string)) {
                    return true;
                }
            }
            else {
                if (strings[i].equalsIgnoreCase(string)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * �ַ����������Ƿ����ָ�����ַ�������Сд���С�
     * @param strings �ַ�������
     * @param string �ַ���
     * @return ����ʱ����true�����򷵻�false
     * @since  0.4
     * @roseuid 3F3A00BD0152
     */
    public static boolean contains(String[] strings, String string)
    {
        return contains(strings, string, true);
    }

    /**
     * �����ִ�Сд�ж��ַ����������Ƿ����ָ�����ַ�����
     * @param strings �ַ�������
     * @param string �ַ���
     * @return ����ʱ����true�����򷵻�false
     * @since  0.4
     * @roseuid 3F3A00BD0242
     */
    public static boolean containsIgnoreCase(String[] strings, String string)
    {
        return contains(strings, string, false);
    }

    /**************************************************
     *
     * ���´����ɲ̴����������
     *
     **************************************************/

    /**
     * Ĭ�������ַ�����ʽ��
     */
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Ĭ�������ַ�����ʽ��
     */
    public static final SimpleDateFormat DEFAULT_TIMESTAMP_FORMAT =
            new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");


    /**
     * ȡ��longֵ
     *
     * @param oo �ַ�����ֵ
     * @return long���͵���ֵ
     * @see
     */
    public static long getLongValue(String oo)
    {
        try {
            return Long.parseLong(oo);
        }
        catch (Exception ex) {
        }

        return 0;
    }

    /**
     * ȡ��intֵ
     *
     * @param oo �ַ�����ֵ
     * @return int���͵���ֵ
     * @see
     */
    public static int getIntValue(String oo)
    {
        try {
            return Integer.parseInt(oo);
        }
        catch (Exception ex) {
        }

        return 0;
    }

    /**
     * ȡ��floatֵ
     *
     * @param oo �ַ�����ֵ
     * @return float���͵���ֵ
     * @see
     */
    public static float getFloatValue(String oo)
    {
        try {
            return Float.parseFloat(oo);
        }
        catch (Exception ex) {
        }

        return 0;
    }

    /**
     * ȡ��doubleֵ
     *
     * @param oo �ַ�����ֵ��
     * @return double���͵���ֵ
     * @see
     */
    public static double getDoubleValue(String oo)
    {
        try {
            return Double.parseDouble(oo);
        }
        catch (Exception ex) {
        }

        return 0;
    }

    /**
     * �����������ַ�����ȡTimestamp����
     *
     * @param year �꣨yyyy����
     * @param month �£�MM����
     * @param day �գ�dd����
     * @return Timestamp �����������ʱ�����null����������д���
     */
    public static Timestamp getTimestampByYMD(String year,
                                              String month, String day)
    {

        try {
            String strDate = year + "-";
            if (month.length() < 2) {
                strDate += "0";
            }
            strDate += month;
            if (day.length() < 2) {
                strDate += "0";
            }
            strDate += day;
            return new Timestamp(DEFAULT_DATE_FORMAT.parse(
                    year + "-" + month + "-" + day).getTime());
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * ȡ��dateֵ,��Ĭ�ϵĲ�����
     *
     * @param oo �ַ�������ֵ��
     * @return java.util.Date ��������ֵ��
     * @see
     */
    public static java.util.Date getDateValue(String oo)
    {
        try {
            return DEFAULT_DATE_FORMAT.parse(oo);
        }
        catch (Exception ex) {
        }

        return new java.util.Date();
    }

    /**
     * ȡ��dateֵ,�������Ĳ���
     *
     * @param oo �ַ���ֵ
     * @param dateString �����ַ�����ʽ
     * @return java.util.Date��������ֵ
     * @see
     */
    public static java.util.Date getDateValue(String oo, String dateString)
    {
        java.util.Date a = null;
        try {
            java.text.SimpleDateFormat smf = new java.text.SimpleDateFormat(
                    dateString);
            a = smf.parse(oo);

            return a;
        }
        catch (Exception ex) {
        }

        return new java.util.Date();
    }

    /**
     * ��ָ���ַ������ַ���ת��Ϊ��һ���ַ������ַ�����
     * ���ϵͳ��֧��GBK�ַ�����������������ַ������������Ӧ�ò�����֣�
     *
     * @param ss ��Ҫ����ת�����ַ�����
     * @return ת�������ַ�����null�����������ַ�����null��
     * @see
     * @author caicai_dev@yahoo.com.cn
     */
    public static String encode(String ss, String from_encoding,
                                String to_encoding)
    {
        if (ss == null) {
            return null;
        }

        String returnValue = ss;
        try {
            byte[] temp = ss.getBytes(from_encoding);
            returnValue = new String(temp, to_encoding);
        }
        catch (Exception ex) {
        }

        return returnValue;
    }

    /**
     * ���ַ����еĻس�����0x0A0D,0x0A,0x0D����ת��Ϊ�ո�
     *
     * @param value ��Ҫ������ַ�����
     * @return �������س������з����ַ���
     * @author caicai_dev@yahoo.com.cn
     */
    public static String trimEnterCode(String value)
    {
        if (value == null) {
            return value;
        }

        String returnValue = value.replace((char) 0x0D, ' ');
        returnValue = returnValue.replace((char) 0x0A, ' ');

        return returnValue;
    }

    /**
     * ΪHTML����ת���ַ�����
     * '&'-->'&amp;'
     * '>'-->'&gt;'
     * '<'-->'&lt;'
     * '"'-->'&quot;'
     * @param value ��Ҫת����ַ���
     * @return ת�����ַ�����
     * @author caicai_dev@yahoo.com.cn
     */
    public static String escapeForHTML(String value)
    {
        if (value == null) {
            return value;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
            case '"': // '"'-->'&quot;' (&#34;)
                buffer.append("&quot;");
                break;
            case '&': // '&'-->'&amp;'  (&#38;)
                buffer.append("&amp;");
                break;
            case '<': // '<'-->'&lt;'   (&#60;)
                buffer.append("&lt;");
                break;
            case '>': // '>'-->'&gt;'   (&#62;)
                buffer.append("&gt;");
                break;
            case (char) 0x0D: //�س�
            case (char) 0x0A: //����
                buffer.append("&#" + (int) ch + ";");
                break;
            default:
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    /**
     * ΪJavaScript���ַ���ת�塰"����\���ַ���
     *
     * @param value ��Ҫת����ַ���
     * @return ת�����ַ�����
     * @author caicai_dev@yahoo.com.cn
     */
    public static String escapeForJavascript(String value)
    {
        if (value == null) {
            return value;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
            case '"': //transfer '"' --> '\"'
                buffer.append("\\\"");
                break;
            case '\'': //transfer "'" --> "\'"
                buffer.append("\\'");
                break;
            case '\\': //transfer '\' --> '\\'
                buffer.append("\\\\");
                break;
            case (char) 0x0D: //�س�
                if (i < value.length() - 1 &&
                    value.charAt(i + 1) != (char) 0x0A) {
                    buffer.append("\\n");
                }
                break;
            case (char) 0x0A: //����
                buffer.append("\\n");
                break;
            default:
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    /**
     * ΪJavaScript���ַ���ת�塰"����\���ַ���
     *
     * @param value ��Ҫת����ַ���
     * @return ת�����ַ�����
     * @author caicai_dev@yahoo.com.cn
     */
    public static String escapeForSQL(String value)
    {
        if (value == null) {
            return value;
        }
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
            case '\'': // transfer "'" --> "''"
                buffer.append("''");
                break;
            default:
                buffer.append(ch);
            }
        }
        return buffer.toString();
    }

    /**
     * �滻�ַ�����ָ�����ַ�����
     *
     * @param originalValue ��Ҫ��������ַ�����
     * @param from ��Ҫ���滻���ַ�����
     * @param to Ҫ�滻���ַ�����
     * @return ת������ַ���
     * @author caicai_dev@yahoo.com.cn
     */
    public static String replaceString(String sourceString, String targetString,
                                       String replaceString)
    {
        int s = 0;
        int e = 0;
        StringBuffer result = new StringBuffer();
        while ((e = sourceString.indexOf(targetString, s)) >= 0) {
            result.append(sourceString.substring(s, e));
            result.append(replaceString);
            s = e + targetString.length();
        } // End while
        result.append(sourceString.substring(s));
        String finalString = result.toString();
        return finalString;
    }

    /**
     * �Ƚ������ַ����Ƿ���ͬ����������ַ�������null�������ַ�����ֵҲ��Ϊ��ͬ��
     *
     * @param str1 �ַ���1
     * @param str2 �ַ���2
     * @return boolean true����ͬ��false����ͬ��
     * @author caicai_dev@yahoo.com.cn
     */
    public static boolean compareStrings(String str1, String str2)
    {
        boolean result = false;
        //��Ϊ��ʱ��ͬ
        if (str1 == null && str2 == null) {
            result = true;
        }
        //����Ϊ�յ�ʱ���������ַ�����ͬ�磺"   "=" "
        else if (str1 != null && str2 != null &&
                 str1.trim().equals(str2.trim())) {
            result = true;
        }
        return result;
    }

    /**
     * ʹ�ø������зַ����з��ַ�����
     *
     * @param source �������ַ�����
     * @param separator ָ�����зַ���
     * @return String[] �зֺ���ַ������顣
     * @author caicai_dev@yahoo.com.cn
     */
    public static String[] splitBySeparator(String source, String separator)
    {
        if (source == null) {
            return null;
        }
        if (separator == null) {
            return new String[] {source};
        }
        int startIndex = 0;
        int endIndex = source.indexOf(separator);
        Vector vector = new Vector();
        while (endIndex >= startIndex) {
            vector.add(source.substring(startIndex, endIndex));
            startIndex = endIndex + separator.length();
            endIndex = source.indexOf(separator, startIndex);
        }
        vector.add(source.substring(startIndex));
        String[] returnValue = new String[vector.size()];
        vector.copyInto(returnValue);
        return returnValue;
    }

    /**
     * �������и���ָ���������ƣ���ȡ��ϸ�б������Դ��
     *
     * @param request ����
     * @param paramName ��������
     * @param required �Ƿ��Ǳ����ṩ�Ĳ���
     * @return String[][] �зֺõ���ϸ�б����ݡ���һά���У��ڶ�ά���С�
     *                    null�����������ַ�����null��
     * @author caicai_dev@yahoo.com.cn
     */
    public static String[][] getTableDataSource(String source)
    {
        if (source == null) {
            return null;
        }
        String[] lineData = splitBySeparator(source, DATASOURCE_LINE_SEPARATOR);
        String[][] dataSource = new String[lineData.length][];
        for (int i = 0; i < dataSource.length; i++) {
            dataSource[i] = StringUtil.splitBySeparator(lineData[i],
                    DATASOURCE_CELL_SEPARATOR);
        }
        return dataSource;
    }

    /**
     * ���ַ�����ָ��λ��������ַ�
     *
     * @param str Ҫ�����ַ���
     * @param filledChar �����ַ�
     * @param pos ���Ŀ�ʼλ��
     * @param totalNum ������ַ�����
     * @return Strng ������ַ���
     *
     * @author caicai_dev@yahoo.com.cn
     */
    public static String fillInChar(String str, char filledChar, int pos,
                                    int totalNum)
    {
        int size = str.length();
        if (pos > size) {
            pos = size;
        }
        if (totalNum <= size) {
            return str;
        }
        char[] chars = str.toCharArray();
        int filledNum = totalNum - str.length();
        char[] destChars = new char[totalNum];
        for (int i = 0; i < pos; i++) {
            destChars[i] = chars[i];
        }
        for (int i = pos; i < filledNum + pos; i++) {
            destChars[i] = filledChar;
        }
        for (int i = filledNum + pos; i < totalNum; i++) {
            destChars[i] = chars[i - filledNum];
        }
        return new String(destChars);
    }

    /**
     * �ύ�б�����ʱ���зָ�����
     */
    public final static String DATASOURCE_LINE_SEPARATOR = "^$$";

    /**
     * �ύ�б�����ʱ��ÿ�еĵ�Ԫ�����ݷָ�����
     */
    public final static String DATASOURCE_CELL_SEPARATOR = "$^^";

    /**
     * ��timestamp����ת��Ϊstring���ͣ�
     * @param timestamp
     * @return
     */
    public static String timestampToString(java.sql.Timestamp timestamp)
    {
        if (timestamp == null) {
            return "";
        }
        return DEFAULT_TIMESTAMP_FORMAT.format(timestamp);
    }

    public static String getZhgxsj()
    {
        return timestampToString(new Timestamp(System.currentTimeMillis()));
    }

    public static Timestamp getTimestamp()
    {
        return new Timestamp(System.currentTimeMillis());
    }

    public static String max(String str1, String str2)
    {
        if (str1 == null) {
            if (str2 != null) {
                return str2;
            }
            return null;
        }
        else if (str2 == null) {
            return str1;
        }
        String maxValue = str2;
        if (str1.compareTo(str2) > 0) {
            maxValue = str1;
        }
        return maxValue;
    }

    public static String maxIgnoreCase(String str1, String str2)
    {
        if (str1 == null) {
            if (str2 != null) {
                return str2;
            }
            return null;
        }
        else if (str2 == null) {
            return str1;
        }
        String maxValue = str2;
        if (str1.compareToIgnoreCase(str2) > 0) {
            maxValue = str1;
        }
        return maxValue;
    }

    /**
     * Method declaration
     *
     * @param args
     * @see
     */
    public static void main(String[] args)
    {
        //���ݼ��������ɾ���Ѿ����뵽��������
        String tbsjk = "@cskh24";
        //�����ѯsql
        String sqlQuery = "SELECT JSJDM "
                          + "FROM DJDB.DJ_JL_JBSJ" + tbsjk + " "
                          + "WHERE 1=1 ";
        String rst = StringUtil.replaceString(sqlQuery, tbsjk, " ");
        System.out.println("rst === " + rst);

    }

       //�ж��ַ��������Ƿ�����ĸ������
       public static boolean verdict(String str)
       {
           if   (str!=null&&str.length()!=0)
           {
               for   (int   i=0;   i<str.length();   i++)
               {
                   if (!Pattern.matches("[0-9a-zA-Z]+",str))
                   {
                       System.out.println("str is false");
                       return false;
                   }

               }
           }
           else
           {
               System.out.println("str is null!");
               return   false;
           }

           return   true;
       }
       public static boolean moreThanEight(String str)
       {
           if (str.length()>8)
               return false;
           else
               return true;
       }
}


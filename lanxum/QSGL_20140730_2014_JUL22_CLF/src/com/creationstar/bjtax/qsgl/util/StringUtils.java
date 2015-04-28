/**
 *
 */
package com.creationstar.bjtax.qsgl.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @author ������
 * @version 1.0
 */
public class StringUtils {

    /**
     * ɱ�պ�������"null"��null����ת��Ϊ""
     *
     * @param str
     *            �����ַ���
     * @return ����ַ���
     */
    public static String killNull(String str) {
        String returnStr = null;
        if (str == null || "null".equals(str.toLowerCase())) {
            returnStr = "";
        } else {
            returnStr = str;
        }
        return returnStr;
    }

    /**
     * ɱ�պ�������"null"��""����ת��Ϊnull
     *
     * @param str
     *            �����ַ���
     * @return ����ַ���
     */
    public static String killNull2(String str) {
        String returnStr = null;
        if ("".equals(str.toLowerCase()) || "null".equals(str.toLowerCase())) {
            returnStr = null;
        } else {
            returnStr = str;
        }
        return returnStr;
    }

    /**
     * ȥ���ַ������ߵĿո񲢴�����ַ���
     *
     * @param str
     *            �����ַ���
     * @return ����ַ���
     */
    public static String trim(String str) {
        String returnStr = null;
        returnStr = StringUtils.killNull(str);
        returnStr = returnStr.trim();
        return returnStr;
    }

    /**
     * ��һ�������ַ�����������
     *
     * @param iterator
     *            ����������Ѱ��
     * @param type
     *            0-�������У�1-��������
     * @return �������list
     */
    public static List sortKeys(Iterator iterator, int type) {
        // 1.�������
        List list = new ArrayList();
        String key0;
        String key1;
        // 2.���ɻ�׼List
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        // 3.��������
        String[] keys = {};
        keys = (String[]) list.toArray(keys);
        // 4.����
        String tmpKey;
        for (int i = 0; i < keys.length; i++) {
            for (int j = keys.length - 1; j > i; j--) {
                if (type == 0) {
                    if (Integer.parseInt(keys[i]) > Integer.parseInt(keys[j])) {
                        tmpKey = keys[i];
                        keys[i] = keys[j];
                        keys[j] = tmpKey;
                    }
                } else if (type == 1) {
                    if (Integer.parseInt(keys[i]) < Integer.parseInt(keys[j])) {
                        tmpKey = keys[i];
                        keys[i] = keys[j];
                        keys[j] = tmpKey;
                    }
                }
            }
        }
        // 5.���ɷ�������
        List tmpList = new ArrayList();
        for (int i = 0; i < keys.length; i++) {
            tmpList.add(keys[i]);
        }
        // 6.����
        return tmpList;
    }

    /**
     * ���ַ������շָ���ŷֿ����Ϊ�ַ�������
     *
     * @param str
     *            Դ�ַ���
     * @param separators
     *            �ָ����
     * @return �ַ�������
     */
    public static final String[] stringToArray(String str, String separators) {
        StringTokenizer tokenizer;
        String[] array = null;
        int count = 0;

        if (str == null) {
            return array;
        }

        if (separators == null) {
            separators = ",";

        }
        tokenizer = new StringTokenizer(str, separators);
        if ((count = tokenizer.countTokens()) <= 0) {
            return array;
        }

        array = new String[count];

        int ix = 0;
        while (tokenizer.hasMoreTokens()) {
            array[ix] = tokenizer.nextToken();
            ix++;
        }

        return array;
    }

    /**
     * ת���ַ���ΪGB23122ISO
     *
     * @param strIn
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public final static String GB23122ISO(String strIn) {
        String strOut = null;
        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("gb2312");
            strOut = new String(b, "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return strOut;
    }

    /**
     * ת���ַ���GBK2ISO
     *
     * @param strIn
     *            Դ�ַ���
     * @return Ŀ���ַ���
     */
    public final static String GBK2ISO(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try - catch
        return strOut;
    }

    /**
     * �滻�ַ���
     *
     * @param original
     *            Դ�ַ���
     * @param find
     *            �����ַ���
     * @param replacement
     *            �滻�ַ���
     * @return �滻����ַ���
     */
    public final static String replaceAll(String original, String find,
                                          String replacement) {
        StringBuffer buffer = new StringBuffer(original);

        int idx = original.length();
        int offset = find.length();

        while ((idx = original.lastIndexOf(find, idx - 1)) > -1) {
            buffer.replace(idx, idx + offset, replacement);
        }

        return buffer.toString();
    }

    /**
     * �滻�ַ���
     *
     * @param buffer
     *            Դ�ַ���
     * @param find
     *            �����ַ���
     * @param replacement
     *            �滻�ַ���
     * @return �滻����ַ���
     */
    public final static StringBuffer replaceAll(StringBuffer buffer,
                                                String find, String replacement) {

        int bufidx = buffer.length() - 1;
        int offset = find.length();
        while (bufidx > -1) {
            int findidx = offset - 1;
            while (findidx > -1) {
                if (bufidx == -1) {
                    // Done
                    return buffer;
                }
                if (buffer.charAt(bufidx) == find.charAt(findidx)) {
                    findidx--; // Look for next char
                    bufidx--;
                } else {
                    findidx = offset - 1; // Start looking again
                    bufidx--;
                    if (bufidx == -1) {
                        // Done
                        return buffer;
                    }
                    continue;
                }
            }
            // Found
            // Debug.out( "replacing from " + (bufidx + 1) +
            // " to " + (bufidx + 1 + offset ) +
            // " with '" + replacement + "'" );
            buffer.replace(bufidx + 1, bufidx + 1 + offset, replacement);
            // start looking again
        }
        // No more matches
        return buffer;

    }

    /**
     * ��arrayת��Ϊ�ַ���
     *
     * @param array
     *            array
     * @param separators
     *            ���ӷ�
     * @return ת�����ַ���
     */
    public static final String arrayToString(String[] array, String separators) {
        StringBuffer sb = new StringBuffer("");
        String empty = "";

        if (array == null) {
            return empty;
        }

        if (separators == null) {
            separators = ",";

        }
        for (int ix = 0; ix < array.length; ix++) {
            if (array[ix] != null && !array[ix].equals("")) {
                sb.append(array[ix] + separators);
            }
        }
        String str = sb.toString();
        if (!str.equals("")) {
            str = str.substring(0, (str.length() - separators.length()));
        }
        return str;
    }

    /**
     * ���ܴ�Сд�滻�ַ���
     *
     * @param sourceString
     *            Դ�ַ���
     * @param targetString
     *            �����ַ���
     * @param replaceString
     *            �滻�ַ���
     * @return �滻����ַ���
     */
    public final static String getReplaceStringIgnoreCase(String sourceString,
            String targetString, String replaceString) {
        if (sourceString == null) {
            return null;
        }
        // ת��Сд�ַ���
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
            } // end while
            buf.append(line2, j, line2.length - j);
            return buf.toString();
        } // end if
        return sourceString;
    }

    /**
     * �õ�ĳ�ַ�����Դ�ַ����г��ֵ�Ƶ��
     *
     * @param srcStr
     *            Դ�ַ���
     * @param subStr
     *            Ŀ���ַ���
     * @return ����Ƶ��
     */
    public final static int getSubStrNum(String srcStr, String subStr) {
        int count = 0;
        // �ж��ַ����Ƿ�Ϸ�
        if (srcStr == null) {
            return count;
        }
        if (subStr == null || subStr.equals("")) {
            return count;
        }
        int pos = 0;
        while ((pos = srcStr.indexOf(subStr)) >= 0) {
            count++;
            srcStr = srcStr.substring(pos + 1);
        } // end while
        return count;
    }

    /**
     * ת���ַ���ΪISO2GB2312
     *
     * @param strIn
     *            Դ�ַ���
     * @return ת������ַ���
     */
    public final static String ISO2GB2312(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "gb2312");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
        return strOut;
    }

    /**
     * ת���ַ�����ISO2GBK
     *
     * @param strIn
     *            Դ�ַ���
     * @return ת������ַ���
     */
    public final static String ISO2GBK(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try_catch
        return strOut;
    }

    /**
     * ת���ַ���ΪStr2ISO
     *
     * @param strIn
     *            Դ�ַ���
     * @return ת������ַ���
     */
    public final static String Str2ISO(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // ���ַ���������ת��
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b);
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try_catch
        return strOut;
    }

    /**
     * ת��String��Long
     *
     * @param strLong
     *            ԴString
     * @param defLong
     *            Ĭ��longֵ
     * @return ת��ֵ ������Ĭ��ֵ
     */
    public final static long getLong(String strLong, long defLong) {
        long value = defLong;
        try {
            value = Long.parseLong(strLong);
        } catch (Exception ex) {
            return defLong;
        }
        return value;
    }

    /**
     * ת��String��Int
     *
     * @param strInt
     *            ԴString
     * @param defInt
     *            Ĭ��Intֵ
     * @return ת��ֵ ������Ĭ��ֵ
     */
    public final static int getInt(String strInt, int defInt) {
        int value = defInt;
        try {
            value = Integer.parseInt(strInt);
        } catch (Exception ex) {
            return defInt;
        }
        return value;
    }

    /**
     * ת��String��Double
     *
     * @param strDouble
     *            ԴString
     * @param defDouble
     *            Ĭ��Doubleֵ
     * @return ת��ֵ ������Ĭ��ֵ
     */
    public final static double getDouble(String strDouble, double defDouble) {
        double value = defDouble;
        try {
            value = Double.parseDouble(strDouble);
        } catch (Exception ex) {
            return defDouble;
        }
        return value;
    }

    /**
     * ת��String��Float
     *
     * @param strFloat
     *            ԴString
     * @param defFloat
     *            Ĭ��ֵ
     * @return ת��ֵ ������Ĭ��ֵ
     */
    public final static float getFloat(String strFloat, float defFloat) {
        float value = defFloat;
        try {
            value = Float.parseFloat(strFloat);
        } catch (Exception ex) {
            return defFloat;
        }
        return value;
    }

    /**
     * ��Ŀ���ַ�����ȡ������ͷβ�������ַ���
     *
     * @param source
     *            Դ�ַ���
     * @param head
     *            ͷ
     * @param tail
     *            β
     * @return �����������ַ���
     */
    public static String getStrFromTo(String source, String head, String tail) {
        String finalString = "";
        if (source == null || source.equals("")) {
            return finalString;
        }
        int e = 0, s = 0;
        e = source.indexOf(head);
        s = source.indexOf(tail, e + head.length());
        // ��һ���Ҳ����򷵻ؿ�
        if (e >= 0 && s > 0 && s > e) {
            finalString = source.substring(e + head.length(), s);
        } // end if
        return finalString;
    }

    /**
     * ȥ���ַ�
     *
     * @param data
     *            ����
     * @param removeChars
     *            Ҫȥ�����ַ�
     * @return ȥ�����String
     */
    public static String removeChars(String data, String removeChars) {
        String temp = null;
        StringBuffer out = new StringBuffer();
        temp = data;

        StringTokenizer st = new StringTokenizer(temp, removeChars);
        while (st.hasMoreTokens()) {
            String element = (String) st.nextElement();
            out.append(element);
        }
        return out.toString();
    }

    /**
     * Performs variable substitution for a string. String is scanned for
     * ${variable_name} and if one is found, it is replaced with corresponding
     * value from the vars hashtable.
     *
     * @param origString
     *            unmodified string
     * @param vars
     *            Hashtable of replacement values
     * @return modified string
     * @exception Exception
     */
    public static String replaceVars(String origString, Map vars) {
        // Debug.out("----------------"+origString+"--------------------------");
        StringBuffer finalString = new StringBuffer();
        int index = 0;
        int i = 0;
        String key = null;
        String value = null;
        while ((index = origString.indexOf("${", i)) > -1) {
            key = origString.substring(index + 2, origString.indexOf("}",
                    index + 3));
            value = (String) vars.get(key);
            // Debug.out(key+"--------------------------"+value+"------------------------------");
            finalString.append(origString.substring(i, index));
            if (value != null) {
                finalString.append(value.trim());
            } else {
                finalString.append("${" + key + "}");
            }
            i = index + 3 + key.length();
        }
        finalString.append(origString.substring(i));
        // Debug.out("----------------"+finalString.toString()+"--------------------------");
        return finalString.toString();
    }

    /**
     * ���ַ����ָ�Ϊ�ַ�������
     *
     * @param source
     *            Դ�ַ���
     * @param separator
     *            �ָ��
     * @return �ַ�������
     */
    public final static String[] split(String source, String separator) {
        ArrayList arr = new ArrayList();
        if (source.indexOf(separator) < 0) {
            arr.add(source);
        } else {
            int start = 0, end = 0;
            while ((end = source.indexOf(separator, start)) > 0) {
                arr.add(source.substring(start, end));
                start = end + separator.length();
            }
        }
        String array[] = new String[arr.size()];
        arr.toArray(array);
        return array;
    }

    /**
     * ��һ���ַ�������ָ���ָ����ָ�Ϊһ���������
     *
     * @param source
     *            String
     * @param separator
     *            String
     * @return String[]
     */
    public static String[] split2(String source, String separator) {
        ArrayList arr = new ArrayList();
        if (source.indexOf(separator) < 0) {
            arr.add(source);
        } else {
            int start = 0, end = 0;
            while ((end = source.indexOf(separator, start)) > 0) {
                arr.add(source.substring(start, end));
                start = end + separator.length();
            }
            if (start < source.length()) {
                arr.add(source.substring(start, source.length()));
            }

        }
        String array[] = new String[arr.size()];
        arr.toArray(array);
        return array;
    }

    /**
     * �滻�ַ���
     *
     * @param srcStr
     *            Դ�ַ���
     * @param replace
     *            Ҫ�滻���ַ���
     * @return �滻����ַ���
     */
    public static String replaceStartSpace(String srcStr, String replace) {
        if (srcStr.trim().equals("")) {
            return StringUtils.replaceAll(srcStr, " ", replace);
        }
        if (!srcStr.startsWith(" ")) {
            return srcStr;
        }
        StringBuffer buffer = new StringBuffer();
        int idx = srcStr.indexOf(" ");
        int pos = 0;
        while (true) {
            if (idx < 0 || (idx - pos > 1)) {
                buffer.append(srcStr.substring(pos + 1));
                return buffer.toString();
            }
            buffer.append(replace);
            pos = idx;
            idx = srcStr.indexOf(" ", idx + 1);
        }
    }

    /**
     * ��ȡ��ǰӦ�ó�������·����ͬʱ��·���е�\�滻��/��ʽ���
     *
     * @return String
     */
    public String getCurrentDir() {
        String curDir = System.getProperty("user.dir").replace('\\', '/');
        return curDir;
    }

    /**
     * �������InputStreamת����String�������
     *
     * @param is
     *            �����InputStream������
     * @return ת�����String����
     * @throws IOException
     */
    public String readToBuffer(InputStream is) throws IOException {
        // ���ص�String����
        String returnStr = new String();
        // ����ƴ�Ӷ�ȡ���ݵ�StringBuffer
        StringBuffer buffer = new StringBuffer();
        // ��������ÿ�ж�ȡ������
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // ��ȡ��һ��
        line = reader.readLine();
        // ��� line Ϊ��˵��������
        while (line != null) {
            // ��������������ӵ� buffer ��
            buffer.append(line);
            // ��ӻ��з�
            buffer.append("\n");
            // ��ȡ��һ��
            line = reader.readLine();
        }
        returnStr = buffer.toString();
        return returnStr;
    }

    /**
     * ������Map��keyֵ��������
     *
     * @param declareMap
     *            �����Map
     * @return ������List
     */
    public ArrayList arrangeMapKey(HashMap declareMap) {
        // ����keyList
        ArrayList keyList = new ArrayList();

        Iterator it = declareMap.keySet().iterator();
        while (it.hasNext()) {
            // ��ȡ��ǰkey
            String key = (String) it.next();
            keyList.add(key);
        }

        int size = keyList.size();
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                double curKey = Double.parseDouble((String) keyList.get(j));
                double nextKey = Double
                                 .parseDouble((String) keyList.get(j + 1));
                if (curKey > nextKey) {
                    double tmp = curKey;
                    keyList.set(j, convertStr(nextKey));
                    keyList.set((j + 1), convertStr(tmp));
                }
            }
        }
        return keyList;
    }

    /**
     * �������doubleֵת�����ַ������ ���doubleΪ*.0����ֻҪ����λ
     *
     * @param d
     *            ��Ҫת����doubleֵ
     * @return ת������ַ���
     */
    public String convertStr(double d) {
        String str = String.valueOf(d);
        if (str.endsWith(".0")) {
            str = str.substring(0, (str.indexOf(".")));
        }
        return str;
    }

    /**
     * ��nullֵת��Ϊ""
     *
     * @param str
     *            ��Ҫת�����ַ���
     * @return ת������ַ���
     */
    public String changeStr(String str) {
        if (null == str) {
            str = "";
        }
        return str;
    }

    /**
     * �ж�List��ÿ���ַ����Ƿ��������ַ���������򷵻�true�����򷵻�false
     *
     * @param source
     *            Դ����List
     * @param specialChar
     *            �õȺ�"="�ָ��������ַ�
     * @return ���List�������Ӧ���ַ����������ַ��򷵻�true�����򷵻�false
     */
    public static List checkSpecialChar(List source, String specialChar) {
        List back = new ArrayList();
        if (source == null || source.size() <= 0) {
            back.add(new Boolean(false));
            return back;
        }
        //
        Boolean _true = new Boolean(true);
        Boolean _false = new Boolean(false);
        // �����ַ�
        String[] strs = specialChar.split("=");
        System.out.println(strs.length);
        // �Ƿ���ڱ�־
        boolean flag = false;
        // Դ�ַ���
        String tmp;
        long start = System.currentTimeMillis();
        long end;
        // ѭ����ȡList������
        for (int i = 0, j = source.size(); i < j; i++) {
            tmp = source.get(i).toString();
            System.out.println(i + " = " + tmp);
            // �ж��Ƿ���������ַ�
            for (int m = 0, n = strs.length; m < n; m++) {
                System.out.println(strs[m]);
                if (tmp.indexOf(strs[m]) >= 0) {
                    flag = true;
                    break;
                } else {
                    flag = false;
                }
                System.out.println("flag = " + flag);
            }
            end = System.currentTimeMillis();
            System.out.println(end - start);
            // ������������ַ����򷵻�List�м�¼Ϊtrue
            if (flag) {
                back.add(i, _true);
            } else {
                back.add(i, _false);
            }
        }
        return back;
    }

    /**
     * ���Է���
     *
     * @param args
     *            String[] ����
     */
    public static void main(String[] args) {
        // String tmp = "0123456789";
        // String[] tmps = tmp.split("");
        // System.out.println(tmp);
        // for (int i = 0; i < tmps.length; i++) {
        // System.out.print("[" + i + "]" + tmps[i] + "|");
        // }
        String s = "�й�A09Z̨���Ұ��л����Ұ��л�������������������̨��ع飻������";
        List sl = new ArrayList();
        sl.add(s);
        String checks =
                "\"=%=_=*='=&=\\=] ]>=&lt=&gt=&amp=&apos=&quot=<=>=,=?=/=|=]=[=}={=;=:=)=(";
        List l = StringUtils.checkSpecialChar(sl, checks);
        for (int i = 0, j = l.size(); i < j; i++) {
            System.out.println(sl.get(i) + " = " + l.get(i));
        }
    }

}

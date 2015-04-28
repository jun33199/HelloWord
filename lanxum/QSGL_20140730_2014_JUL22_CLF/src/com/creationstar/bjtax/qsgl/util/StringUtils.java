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
 * @author 哈正则
 * @version 1.0
 */
public class StringUtils {

    /**
     * 杀空函数，将"null"和null对象转换为""
     *
     * @param str
     *            输入字符串
     * @return 输出字符串
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
     * 杀空函数，将"null"和""对象转换为null
     *
     * @param str
     *            输入字符串
     * @return 输出字符串
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
     * 去除字符串两边的空格并处理空字符串
     *
     * @param str
     *            输入字符串
     * @return 输出字符串
     */
    public static String trim(String str) {
        String returnStr = null;
        returnStr = StringUtils.killNull(str);
        returnStr = returnStr.trim();
        return returnStr;
    }

    /**
     * 对一组数字字符串进行排序
     *
     * @param iterator
     *            输入数字轮寻器
     * @param type
     *            0-升序排列，1-降序排列
     * @return 排序完的list
     */
    public static List sortKeys(Iterator iterator, int type) {
        // 1.句柄申明
        List list = new ArrayList();
        String key0;
        String key1;
        // 2.生成基准List
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        // 3.生成数组
        String[] keys = {};
        keys = (String[]) list.toArray(keys);
        // 4.排序
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
        // 5.生成返回容器
        List tmpList = new ArrayList();
        for (int i = 0; i < keys.length; i++) {
            tmpList.add(keys[i]);
        }
        // 6.返回
        return tmpList;
    }

    /**
     * 把字符串按照分割符号分开阻隔为字符串数组
     *
     * @param str
     *            源字符串
     * @param separators
     *            分割符号
     * @return 字符串数组
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
     * 转换字符集为GB23122ISO
     *
     * @param strIn
     *            源字符串
     * @return 目标字符串
     */
    public final static String GB23122ISO(String strIn) {
        String strOut = null;
        if (strIn == null || (strIn.trim()).equals("")) {
            return strIn;
        }
        try {
            // 将字符串作内码转换
            byte[] b = strIn.getBytes("gb2312");
            strOut = new String(b, "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            return null;
        }
        return strOut;
    }

    /**
     * 转换字符集GBK2ISO
     *
     * @param strIn
     *            源字符串
     * @return 目标字符串
     */
    public final static String GBK2ISO(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // 将字符串作内码转换
            byte[] b = strIn.getBytes("GBK");
            strOut = new String(b, "ISO8859_1");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try - catch
        return strOut;
    }

    /**
     * 替换字符串
     *
     * @param original
     *            源字符串
     * @param find
     *            查找字符串
     * @param replacement
     *            替换字符串
     * @return 替换后的字符串
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
     * 替换字符串
     *
     * @param buffer
     *            源字符串
     * @param find
     *            查找字符串
     * @param replacement
     *            替换字符串
     * @return 替换后的字符串
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
     * 把array转换为字符串
     *
     * @param array
     *            array
     * @param separators
     *            连接符
     * @return 转换的字符串
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
     * 不管大小写替换字符串
     *
     * @param sourceString
     *            源字符串
     * @param targetString
     *            查找字符串
     * @param replaceString
     *            替换字符串
     * @return 替换后的字符串
     */
    public final static String getReplaceStringIgnoreCase(String sourceString,
            String targetString, String replaceString) {
        if (sourceString == null) {
            return null;
        }
        // 转成小写字符串
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
     * 得到某字符串在源字符串中出现的频率
     *
     * @param srcStr
     *            源字符串
     * @param subStr
     *            目标字符串
     * @return 出现频率
     */
    public final static int getSubStrNum(String srcStr, String subStr) {
        int count = 0;
        // 判断字符串是否合法
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
     * 转换字符集为ISO2GB2312
     *
     * @param strIn
     *            源字符串
     * @return 转换后的字符串
     */
    public final static String ISO2GB2312(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // 将字符串作内码转换
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "gb2312");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        }
        return strOut;
    }

    /**
     * 转换字符串到ISO2GBK
     *
     * @param strIn
     *            源字符串
     * @return 转换后的字符串
     */
    public final static String ISO2GBK(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // 将字符串作内码转换
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b, "GBK");
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try_catch
        return strOut;
    }

    /**
     * 转换字符串为Str2ISO
     *
     * @param strIn
     *            源字符串
     * @return 转换后的字符串
     */
    public final static String Str2ISO(String strIn) {
        String strOut = null;
        if (strIn == null || strIn.trim().equals("")) {
            return strIn;
        }
        try {
            // 将字符串作内码转换
            byte[] b = strIn.getBytes("ISO8859_1");
            strOut = new String(b);
        } catch (java.io.UnsupportedEncodingException e) {
            return "";
        } // end try_catch
        return strOut;
    }

    /**
     * 转换String到Long
     *
     * @param strLong
     *            源String
     * @param defLong
     *            默认long值
     * @return 转换值 出错返回默认值
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
     * 转换String到Int
     *
     * @param strInt
     *            源String
     * @param defInt
     *            默认Int值
     * @return 转换值 出错返回默认值
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
     * 转换String到Double
     *
     * @param strDouble
     *            源String
     * @param defDouble
     *            默认Double值
     * @return 转换值 出错返回默认值
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
     * 转换String到Float
     *
     * @param strFloat
     *            源String
     * @param defFloat
     *            默认值
     * @return 转化值 出错返回默认值
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
     * 从目标字符串中取得满足头尾条件的字符串
     *
     * @param source
     *            源字符串
     * @param head
     *            头
     * @param tail
     *            尾
     * @return 满足条件的字符串
     */
    public static String getStrFromTo(String source, String head, String tail) {
        String finalString = "";
        if (source == null || source.equals("")) {
            return finalString;
        }
        int e = 0, s = 0;
        e = source.indexOf(head);
        s = source.indexOf(tail, e + head.length());
        // 有一个找不到则返回空
        if (e >= 0 && s > 0 && s > e) {
            finalString = source.substring(e + head.length(), s);
        } // end if
        return finalString;
    }

    /**
     * 去除字符
     *
     * @param data
     *            数据
     * @param removeChars
     *            要去除的字符
     * @return 去除完的String
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
     * 将字符串分割为字符串数组
     *
     * @param source
     *            源字符串
     * @param separator
     *            分割符
     * @return 字符串数组
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
     * 将一个字符串按照指定分隔符分隔为一个数组对象
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
     * 替换字符串
     *
     * @param srcStr
     *            源字符串
     * @param replace
     *            要替换的字符串
     * @return 替换后的字符串
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
     * 获取当前应用程序运行路径，同时将路径中的\替换成/格式输出
     *
     * @return String
     */
    public String getCurrentDir() {
        String curDir = System.getProperty("user.dir").replace('\\', '/');
        return curDir;
    }

    /**
     * 将读入的InputStream转换成String对象输出
     *
     * @param is
     *            读入的InputStream流对象
     * @return 转换后的String对象
     * @throws IOException
     */
    public String readToBuffer(InputStream is) throws IOException {
        // 返回的String对象
        String returnStr = new String();
        // 用来拼接读取内容的StringBuffer
        StringBuffer buffer = new StringBuffer();
        // 用来保存每行读取的内容
        String line;
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        // 读取第一行
        line = reader.readLine();
        // 如果 line 为空说明读完了
        while (line != null) {
            // 将读到的内容添加到 buffer 中
            buffer.append(line);
            // 添加换行符
            buffer.append("\n");
            // 读取下一行
            line = reader.readLine();
        }
        returnStr = buffer.toString();
        return returnStr;
    }

    /**
     * 将传入Map的key值进行排序
     *
     * @param declareMap
     *            传入的Map
     * @return 排序后的List
     */
    public ArrayList arrangeMapKey(HashMap declareMap) {
        // 定义keyList
        ArrayList keyList = new ArrayList();

        Iterator it = declareMap.keySet().iterator();
        while (it.hasNext()) {
            // 获取当前key
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
     * 将传入的double值转换成字符串输出 如果double为*.0，则只要整数位
     *
     * @param d
     *            需要转换的double值
     * @return 转换后的字符串
     */
    public String convertStr(double d) {
        String str = String.valueOf(d);
        if (str.endsWith(".0")) {
            str = str.substring(0, (str.indexOf(".")));
        }
        return str;
    }

    /**
     * 将null值转换为""
     *
     * @param str
     *            需要转换的字符串
     * @return 转换后的字符串
     */
    public String changeStr(String str) {
        if (null == str) {
            str = "";
        }
        return str;
    }

    /**
     * 判断List中每个字符串是否含有特殊字符，如果有则返回true；否则返回false
     *
     * @param source
     *            源数据List
     * @param specialChar
     *            用等号"="分隔的特殊字符
     * @return 结果List，如果对应的字符串有特殊字符则返回true；否则返回false
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
        // 特殊字符
        String[] strs = specialChar.split("=");
        System.out.println(strs.length);
        // 是否存在标志
        boolean flag = false;
        // 源字符串
        String tmp;
        long start = System.currentTimeMillis();
        long end;
        // 循环获取List中数据
        for (int i = 0, j = source.size(); i < j; i++) {
            tmp = source.get(i).toString();
            System.out.println(i + " = " + tmp);
            // 判断是否存在特殊字符
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
            // 如果含有特殊字符，则返回List中记录为true
            if (flag) {
                back.add(i, _true);
            } else {
                back.add(i, _false);
            }
        }
        return back;
    }

    /**
     * 测试方法
     *
     * @param args
     *            String[] 参数
     */
    public static void main(String[] args) {
        // String tmp = "0123456789";
        // String[] tmps = tmp.split("");
        // System.out.println(tmp);
        // for (int i = 0; i < tmps.length; i++) {
        // System.out.print("[" + i + "]" + tmps[i] + "|");
        // }
        String s = "中国A09Z台湾我爱中华，我爱中华；；；；；；；；；台湾回归；；；；";
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

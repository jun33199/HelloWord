package com.creationstar.bjtax.qsgl.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {

    }

    /**
     * @param patternStr
     *            ģ��
     * @param regexStr
     *            ƥ����ַ���
     * @return
     */
    public static boolean isRegex(String patternStr, String regexStr) {

        Pattern regex = Pattern.compile(patternStr);
        Matcher matcher = regex.matcher(regexStr);
        boolean isMatched = matcher.matches();

        return isMatched;
    }

}

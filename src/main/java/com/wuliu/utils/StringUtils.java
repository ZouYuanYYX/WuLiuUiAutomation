package com.wuliu.utils;

/**
 * 字符串处理工具
 * @author joy
 *
 */

public class StringUtils {
	
	/**
     * 判断字符串是否为Null
     *
     * @param str
     * @return
     */
    public static boolean isNull(String str) {
        if (str == null) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串为空或Null
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return org.apache.commons.lang.StringUtils.isEmpty(str);
    }

    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * 判断字符串为空，或者空白字符或者Null
     *
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        return org.apache.commons.lang.StringUtils.isBlank(str);
    }

    /**
     * 判断字符串不为空，且不是空白字符，且不为Null
     *
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtils.isBlank(str);
    }
    
    /**
     * 判断2个字符串是否相等
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equals(str1, str2);
    }

    /**
     * 忽略字符串大小写，判断2个字符串是否相等，
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreCase(String str1, String str2) {
        return org.apache.commons.lang.StringUtils.equalsIgnoreCase(str1, str2);
    }
    
    /**
     * <p>
     * Splits the provided text into an array, separators specified. This is an alternative to using StringTokenizer.
     * </p>
     * <p/>
     * <p/>
     * <p/>
     * <pre>
     * StringUtils.split(null, *)         = null
     * StringUtils.split("", *)           = []
     * StringUtils.split("abc def", null) = ["abc", "def"]
     * StringUtils.split("abc def", " ")  = ["abc", "def"]
     * StringUtils.split("abc  def", " ") = ["abc", "def"]
     * StringUtils.split("ab:cd:ef", ":") = ["ab", "cd", "ef"]
     * </pre>
     *
     * @param str
     * @param separatorChar
     * @return
     * @throws Exception
     */
    public static String[] split(String str, String separatorChar) {
        return org.apache.commons.lang.StringUtils.split(str, separatorChar);
    }

    /**
     * 将字符串转换成Boolean
     *
     * @param str
     * @return
     */
    public static boolean toBoolean(String str) {
        return "TRUE".equalsIgnoreCase(str) || "Y".equalsIgnoreCase(str) || "YES".equalsIgnoreCase(str);
    }

    /**
     * <p>
     * Checks if String contains a search String, handling <code>null</code>. This method uses
     * {@link String#indexOf(String)}.
     * </p>
     * <p/>
     * <p/>
     * <p/>
     * <pre>
     * StringUtils.contains(null, *)     = false
     * StringUtils.contains(*, null)     = false
     * StringUtils.contains("", "")      = true
     * StringUtils.contains("abc", "")   = true
     * StringUtils.contains("abc", "a")  = true
     * StringUtils.contains("abc", "z")  = false
     * </pre>
     *
     * @param str
     * @param subStr
     * @return
     */
    public static boolean contains(String str, String subStr) {
        return org.apache.commons.lang.StringUtils.contains(str, subStr);
    }

}

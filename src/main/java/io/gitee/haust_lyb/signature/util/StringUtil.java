package io.gitee.haust_lyb.signature.util;

import org.thymeleaf.util.StringUtils;

public class StringUtil {

    public static boolean isNullOrEmpty(String str){
        return str!=null&&!StringUtils.isEmpty(str)&&!"null".equals(str);
    }
}

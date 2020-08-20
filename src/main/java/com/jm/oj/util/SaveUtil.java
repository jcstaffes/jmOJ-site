package com.jm.oj.util;

public class SaveUtil {
    public static String saveDir="D:\\warehouse\\";
    /**
     * 判断文件扩展名是否符合
     * */
    public static boolean isFileAllowed(String fileExt){
        if (fileExt.equals("java"))
            return true;
        else return false;
    }
}

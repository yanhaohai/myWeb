package xin.yanhaohai.myweb.utils;


import xin.yanhaohai.myweb.config.PathConfig;


public class PathUtil {

    private static final PathConfig pathConfig = new PathConfig();

    public static String getPath(String url) {
        //通过相对路径获取绝对路径
        if (url != null && url != "") {
            return pathConfig + url;
        }
        return null;
    }

}

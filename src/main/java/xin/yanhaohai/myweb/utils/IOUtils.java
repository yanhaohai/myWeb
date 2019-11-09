package xin.yanhaohai.myweb.utils;

import xin.yanhaohai.myweb.domain.Blog;

import java.io.*;

public class IOUtils {
    //读写文件工具类
    private static InputStream in;

    private static OutputStream out;

    public static String readFile(String path){
        //根据文件路径读取文件
        try {
            in = new FileInputStream(path);
            byte[] file = new byte[1024*1024];
            int len = 0;
            String fileStr = "";
            while ((len=in.read(file))>-1){
                String str = new String(file, 0, len);
                fileStr += str;
            }
            return fileStr;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static boolean writeFile(Blog blog){
        //根据博客对象写出文件
        try {
            out = new BufferedOutputStream(new FileOutputStream(blog.getBlogUrl()));
            //获得博客内容的字节数组
            byte[] blogPaper = blog.getBlogPaper().getBytes("UTF-8");
            out.write(blogPaper);
            out.flush();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}

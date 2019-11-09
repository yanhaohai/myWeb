package xin.yanhaohai.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import xin.yanhaohai.myweb.domain.Image;
import xin.yanhaohai.myweb.service.ImageService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;


@Controller
public class ImageController {
    @Autowired
    private ImageService imageService;
    //获得图片存放路径,获取本地系统的路径分割符
    //private String path = PathUtil.getPath("static"+ File.separator+"images"+File.separator+"image");
    @Value("${imagePath}")
    private String path;


    @RequestMapping("/myimage")
    public String myimage(Model images){
        //获得图片信息
        List<Image> allImages = imageService.getAllImages();
        images.addAttribute("images",allImages);
        return "myimage";
    }
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("des")String des) throws IOException {
        //long start = System.currentTimeMillis();
        if (file != null){
            //String path = "/usr/image/";
            //获得图片文件字节数组
            byte[] bytes = file.getBytes();
            System.out.println(path + file.getOriginalFilename());
            FileOutputStream out = new FileOutputStream(path + file.getOriginalFilename());
            //写入数据
            out.write(bytes);
            //关闭资源
            out.close();
            //创建一个image对象用来更新数据库
            Image image = new Image();
            image.setImageName(file.getOriginalFilename());
            //图片介绍
            image.setDes(des);
            //上传日期
            image.setUploadDate(new Date());
            //图片地址
            image.setUrl(file.getOriginalFilename());
            //将照片信息写入数据库
            imageService.saveImage(image);
            //long time = System.currentTimeMillis() - start;
            //System.out.println("耗费时间:"+time);

            return "redirect:/myimage";
        }
        return "redirect:/main.html";
    }
}

package xin.yanhaohai.myweb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xin.yanhaohai.myweb.domain.Blog;
import xin.yanhaohai.myweb.domain.BlogLimit;
import xin.yanhaohai.myweb.service.BlogService;

import java.util.Date;
import java.util.List;

@Controller
public class FileController {
    @Autowired
    private BlogService blogService;
    //博客文件的上传和下载
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(Blog blog){
        blog.setCreateTime(new Date());
        return blogService.writeBlog(blog);
    }
    //博客下载
    @RequestMapping("/downBlog")
    public String downBlog(@RequestParam("blogName") String blogName, Model model){
        //long start = System.currentTimeMillis();
        Blog blog = blogService.getBlogByName(blogName);
        model.addAttribute("blog",blog);
        //long end = System.currentTimeMillis();
        //System.out.println("博客下载用时:"+(end-start));
        return "info";
    }
    //个人博客数据准备与跳转
    @RequestMapping("/blogList")
    public String blogList(Model model){
        //获取所有博客数据
        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        //获取点击量排名前十的博客
        List<Blog> top10_blogs = blogService.getTop10_BlogsByClick();
        model.addAttribute("top10_blogs",top10_blogs);
        return "list";
    }
    @ApiOperation("分页查询博客数据")
    @RequestMapping(value = "/blogLimitList",method = RequestMethod.GET)
    public String blogLimitList(Model model,@ApiParam("当前页数") @RequestParam("currentPage") Integer currentPage){
        //获取博客总数
        int blogCount = blogService.getBlogCount();
        BlogLimit<Blog> blogLimit = new BlogLimit<>(currentPage,blogCount);
        //分页查询
        blogService.getBlogsLimit(blogLimit);

        model.addAttribute("blogLimit",blogLimit);
        //获取点击量排名前十的博客
        List<Blog> top10_blogs = blogService.getTop10_BlogsByClick();
        model.addAttribute("top10_blogs",top10_blogs);
        return "list";
    }
}

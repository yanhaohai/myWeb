package xin.yanhaohai.myweb.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import xin.yanhaohai.myweb.domain.News;
import xin.yanhaohai.myweb.service.NewsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 新闻获取
 * */
@Controller
@Api(tags = {"NewsController"},description = "新闻获取")
public class NewsController {
    @Autowired
    private NewsService newsService;


    @RequestMapping(value = "/news/{id}",method = RequestMethod.GET)
    public String newsList(@PathVariable("id") String id, Model model, HttpServletResponse response){
        //System.out.println(id);
        News news = null;
        try {
            news = newsService.getNewsById(Integer.valueOf(id));
        }catch (ClassCastException e){
            try {
                response.getWriter().write("请输出数字，错误信息:"+e.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        model.addAttribute("news",news);
        return "news";
    }
}

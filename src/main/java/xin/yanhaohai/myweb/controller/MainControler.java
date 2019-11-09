package xin.yanhaohai.myweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import xin.yanhaohai.myweb.domain.News;
import xin.yanhaohai.myweb.service.NewsService;

import java.util.List;

@Controller
public class MainControler {
    @Autowired
    private NewsService newsService;

    @RequestMapping("/loginTest")
    public String index(Model model){
        List<News> allNews = newsService.getAllNews();
//        for (News news:allNews){
//            System.out.println(news.getCreateTime());
//        }
        model.addAttribute("allNews",allNews);
        return "main";
    }
}

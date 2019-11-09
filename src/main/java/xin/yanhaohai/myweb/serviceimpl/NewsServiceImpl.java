package xin.yanhaohai.myweb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xin.yanhaohai.myweb.dao.NewsMapper;
import xin.yanhaohai.myweb.domain.News;
import xin.yanhaohai.myweb.service.NewsService;
import xin.yanhaohai.myweb.utils.IOUtils;

import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {
    @Autowired
    private NewsMapper newsMapper;
    @Override
    public List<News> getAllNews() {
        List<News> allNews = newsMapper.getAllNews();
        for (News news : allNews){
            String newPaper = IOUtils.readFile(news.getPath());
            news.setNewPaper(newPaper);
        }

        return allNews;
    }

    @Override
    public News getNewsById(Integer id) {
        News news = newsMapper.getNewsById(id);
        String newPaper = IOUtils.readFile(news.getPath());
        news.setNewPaper(newPaper);
        return news;
    }
}

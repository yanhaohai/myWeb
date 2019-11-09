package xin.yanhaohai.myweb.service;

import xin.yanhaohai.myweb.domain.News;

import java.util.List;

public interface NewsService {
    List<News> getAllNews();

    News getNewsById(Integer id);
}

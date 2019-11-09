package xin.yanhaohai.myweb.service;

import xin.yanhaohai.myweb.domain.Blog;
import xin.yanhaohai.myweb.domain.BlogLimit;

import java.util.List;

public interface BlogService {
    String writeBlog(Blog blog);
    Blog getBlogByName(String blogName);
    List<Blog> getAllBlog();

    List<Blog> getTop10_BlogsByClick();

    int getBlogCount();

    void getBlogsLimit(BlogLimit<Blog> blogBlogLimit);
}

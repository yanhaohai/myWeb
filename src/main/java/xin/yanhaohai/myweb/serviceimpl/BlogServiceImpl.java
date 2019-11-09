package xin.yanhaohai.myweb.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xin.yanhaohai.myweb.dao.BlogMapper;
import xin.yanhaohai.myweb.domain.Blog;
import xin.yanhaohai.myweb.domain.BlogLimit;
import xin.yanhaohai.myweb.service.BlogService;
import xin.yanhaohai.myweb.utils.IOUtils;

import java.io.Writer;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper dao;
    @Value("${blogPath}")
    private String path;
    private Writer out;

    @Override
    public String writeBlog(Blog blog) {
        //用博客名从数据库查找是否存在
        Blog oldBlog = dao.getBlogByName(blog.getBlogName());
        if (oldBlog != null) {
            //数据库中已有该博客数据
            if (blog.getBlogPaper().equals(IOUtils.readFile(oldBlog.getBlogUrl()))) {
                //判断博客是否有修改,如果没有直接返回已保存
                //System.out.println("未保存");
                return "success";
            }else {
                //将新的博客内容写入磁盘
                blog.setBlogUrl(oldBlog.getBlogUrl());
                IOUtils.writeFile(blog);
            }

        }else {
            //数据库中无数据
            blog.setBlogUrl(path + blog.getBlogName());//添加文件地址
            IOUtils.writeFile(blog);//将博客写出磁盘
            dao.saveBlog(blog);//将博客数据存入数据库
        }
        return "success";
    }

    @Override
    public Blog getBlogByName(String blogName) {
        //根据博客名从数据库查找数据
        Blog blog = dao.getBlogByName(blogName);
        if (blog == null){
            //如果为空则返回null
            return null;
        }
        //点击数加一
        dao.updateClickByName(blogName);
        String file = IOUtils.readFile(blog.getBlogUrl());
        //System.out.println(file);
        blog.setBlogPaper(file);
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        List<Blog> blogs = dao.getAllBlog();
        for (Blog blog : blogs) {
            //读取博客并为其paper属性赋值
            blog.setBlogPaper(IOUtils.readFile(blog.getBlogUrl()));
            //System.out.println(blog+":"+blog.getBlogPaper());
        }

        return blogs;
    }

    @Override
    public List<Blog> getTop10_BlogsByClick() {
        return dao.getTop10_BlogsByClick();
    }

    @Override
    public int getBlogCount() {
        return dao.getBlogCount();
    }

    @Override
    public void getBlogsLimit(BlogLimit<Blog> blogBlogLimit) {
        //当前页数
        Integer currentPage = blogBlogLimit.getCurrentPage();
        //每页数据条数
        Integer number = blogBlogLimit.getNumber();
        //计算总页数
        blogBlogLimit.setPageCount();

        List<Blog> blogsLimit = dao.getBlogsLimit(currentPage - 1, number);

        for (Blog blog : blogsLimit){
            //读取磁盘中的博客内容
            String file = IOUtils.readFile(blog.getBlogUrl());
            blog.setBlogPaper(file);
        }

        blogBlogLimit.setBlogList(blogsLimit);
    }

}

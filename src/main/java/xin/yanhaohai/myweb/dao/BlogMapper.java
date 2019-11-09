package xin.yanhaohai.myweb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import xin.yanhaohai.myweb.domain.Blog;

import java.util.List;

@Mapper
public interface BlogMapper {

    @Insert("INSERT INTO blog(blogName,blogUrl,createTime) VALUES(#{blogName},#{blogUrl},#{createTime})")
    void saveBlog(Blog blog);

    @Select("select * from blog where blogName=#{blogName}")
    Blog getBlogByName(String blogName);

    @Select("select * from blog")
    List<Blog> getAllBlog();

    /**
     * 获取博客总数
     *
     * */
    @Select("SELECT COUNT(*) FROM blog")
    int getBlogCount();

    /**
     *点击数加一
     * */
    @Update("UPDATE blog SET click=click+1 WHERE blogName=#{blogName}")
    int updateClickByName(String blogName);

    /**
     * 获取点击量前十的博客
     * */
    @Select("SELECT * FROM blog ORDER BY click DESC LIMIT 0,10")
    List<Blog> getTop10_BlogsByClick();
    /**
     * 分页查询博客数据
     * */
    @Select("SELECT * FROM blog LIMIT #{currentPage},#{number}")
    List<Blog> getBlogsLimit(int currentPage,int number);
}

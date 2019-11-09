package xin.yanhaohai.myweb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xin.yanhaohai.myweb.domain.News;

import java.util.List;

@Mapper
public interface NewsMapper {
    @Insert("INSERT INTO news(title,path,creatTiem) VALUES(#{title},#{path},#{createTime})")
    int insertNews(News news);

    @Select("SELECT id,title,path,DATE_FORMAT(createTime,'%Y-%m-%d') AS createTime FROM news")
    List<News> getAllNews();

    @Select("SELECT * FROM news WHERE id=#{id}")
    News getNewsById(Integer id);
}

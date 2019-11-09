package xin.yanhaohai.myweb.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import xin.yanhaohai.myweb.domain.Image;
import xin.yanhaohai.myweb.domain.User;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("select * from user where username=#{username} and password=#{password}")
    User getUser(String username,String password);

    @Select("select * from image")
    List<Image> getAllImages();
    @Insert("INSERT INTO image(imageName,des,uploadDate,url) VALUES(#{imageName},#{des},#{uploadDate},#{url})")
    void saveImage(Image image);

}

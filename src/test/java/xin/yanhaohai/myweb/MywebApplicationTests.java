package xin.yanhaohai.myweb;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import xin.yanhaohai.myweb.config.PathConfig;
import xin.yanhaohai.myweb.dao.UserDao;
import xin.yanhaohai.myweb.domain.Blog;
import xin.yanhaohai.myweb.domain.BlogLimit;
import xin.yanhaohai.myweb.service.BlogService;
import xin.yanhaohai.myweb.utils.CodeUtils;
import xin.yanhaohai.myweb.utils.PathUtil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MywebApplicationTests {
    @Autowired
    private UserDao dao;
    @Autowired
    private PathConfig pathConfig;
    @Value("${imagePath}")
    private String imagePath;
    @Autowired
    private BlogService blogService;

    @Autowired
    private StringRedisTemplate template;
    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate template2;
    @Test
    public void pathUtis(){
//        InputStream resource = Object.class.getResourceAsStream("classpath:path.properties");
//        System.out.println(resource);
//        Properties properties = new Properties();
//        try {
//            properties.load(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        //String imagePath = properties.getProperty("imagePath");

        System.out.println(imagePath);
        FileReader reader = null;
        try {
            reader = new FileReader(imagePath+"王亮视图");
            char[] a = new char[1024];
            int i = reader.read(a);
            System.out.println(String.valueOf(a,0,i));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void contextLoads() {
        SimpleDateFormat sim = new SimpleDateFormat("yyyy/MM/dd");
        String format = sim.format(new Date());
        System.out.println(format);

        String format2 = sim.format(new Date());
        System.out.println(format2);
    }
    @Test
    public void testinput() throws FileNotFoundException {

        System.out.println(PathUtil.getPath("static/images/image"));
        String path = ResourceUtils.getURL("classpath:static/images/image").getPath().substring(1);
        System.out.println(path);
    }
    @Test
    public void testIO(){
        try {
            FileReader reader = new FileReader("c:/Test/王亮视图");
            char[] chars = new char[10];
            String blogPaper = "";
            int len = 0;
            while((len=reader.read(chars))>-1){
                blogPaper += String.valueOf(chars,0,len);
            }
            System.out.println(blogPaper);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testBlogService(){
        BlogLimit<Blog> blogBlogLimit = new BlogLimit<>(1, blogService.getBlogCount());
        blogService.getBlogsLimit(blogBlogLimit);
        System.out.println(blogBlogLimit);
    }
    @Test
    public void testRedis(){
        //template.opsForValue().set("time","sfdsd");

//        String key = "127.0.0.1";
//        Map<String,String> map = new HashMap<>();
//        map.put("Success_count","0");
//        map.put("Failure_count","0");
//        map.put("Is_close","0");
//        template2.opsForHash().putAll(key,map);
//        template2.opsForHash().put(key,"Success_count","3");
//        String success_count = (String) template2.opsForHash().get(key, "Success_count");

//        System.out.println(success_count);
//        Map<String,Integer> entries = template2.opsForHash().entries(key);
//        for (String m : entries.keySet()){
//            System.out.println(m+":"+entries.get(m));
//        }

//        String key = "2154654";
//        Object sdf = template2.opsForHash().get(key, "sdf");
//        System.out.println(sdf);
        String phone = "15083521043";
        CodeUtils.getCode(phone);

    }
}

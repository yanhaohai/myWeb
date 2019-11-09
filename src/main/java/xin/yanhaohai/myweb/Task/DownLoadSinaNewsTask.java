package xin.yanhaohai.myweb.Task;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import xin.yanhaohai.myweb.dao.NewsMapper;
import xin.yanhaohai.myweb.domain.News;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 定时扫描并删除无效订单
 * */
@Component
public class DownLoadSinaNewsTask {
    @Autowired
    private NewsMapper newsMapper;

    @Value("${newsPath}")
    private String newsPath;

    private Writer writer;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每天凌晨零点扫描数据
     *
     *
     */
    //0 0 0 * * ?每天零点执行一次
    //0 */1 * * * ?每分钟执行一次
    @Scheduled(cron = "0 0 0 * * ?")
    public void downLoadSinaNews() throws IOException {
        News news = null;
        int i = 1;
        //使用jsou爬取sina网科技新闻并存入数据库
        Document doc = Jsoup.connect("https://tech.sina.com.cn/").get();
        Elements consTi = doc.getElementsByClass("tech-news");
        List<String> newsUrl = getNewsUrl(consTi);
        for (String url : newsUrl){
            //通过url地址获取新闻标题和内容
            Document newDocument = Jsoup.connect(url).get();
            //获得标题
            String title = newDocument.getElementsByClass("main-title").html();

            //获得文章内容
            Elements newsText = newDocument.select("#article_content > div.article-content-left p");


            String date = simpleDateFormat.format(new Date());
            //拼接文件路径
            String path = newsPath+date+File.separator;
            //创建文件夹
            mkdir(path);
            path = path+(i++);
            //将新闻写入磁盘
            writer = new FileWriter(path);
            writer.write(String.valueOf(newsText));
            writer.flush();
            writer.close();
            writer = null;

            //将新闻写入数据库
            news = new News(title,path,date);
            newsMapper.insertNews(news);

            news = null;


        }
        //System.out.println("执行完毕");

    }
    /**
     * 根据传入的elments筛选出shtml后缀的新闻网页
     * */
    public List<String> getNewsUrl(Elements elements){
        List<String> urlList = new LinkedList<>();
        for (Element element : elements){
            Elements aElements = element.getElementsByTag("a");
            for (Element a : aElements){
                String url = a.attr("href");
                if (url.endsWith("shtml")){
                    urlList.add(url);
                }
            }

        }
        return urlList;
    }
    public boolean mkdir(String dir){
        File file = new File(dir);
        return file.mkdir();
    }
}

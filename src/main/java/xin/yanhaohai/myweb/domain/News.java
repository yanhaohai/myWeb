package xin.yanhaohai.myweb.domain;

/**
 * 新闻实体类
 * */
public class News {
    private Integer id;
    private String title;
    private String path;
    private String newPaper;
    //简略信息
    private String simpleNews;
    private String createTime;

    public News() {
    }

    public News(String title, String path, String createTime) {
        this.title = title;
        this.path = path;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getNewPaper() {
        return newPaper;
    }

    public String getSimpleNews() {
        return simpleNews;
    }

    public void setNewPaper(String newPaper) {
        this.newPaper = newPaper;
        this.simpleNews = newPaper.substring(0,100);
    }
}

package xin.yanhaohai.myweb.domain;

import java.util.Date;

public class Blog {
    private Integer id;
    private String blogName;
    private String blogUrl;
    private Date createTime;
    private String blogPaper;
    private Integer click;//点击量

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getBlogPaper() {
        return blogPaper;
    }

    public void setBlogPaper(String blogPaper) {
        this.blogPaper = blogPaper;
    }

    public Integer getClick() {
        return click;
    }

    public void setClick(Integer click) {
        this.click = click;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", blogName='" + blogName + '\'' +
                ", blogUrl='" + blogUrl + '\'' +
                ", createTime=" + createTime +
                ", blogPaper='" + blogPaper + '\'' +
                ", click=" + click +
                '}';
    }
}

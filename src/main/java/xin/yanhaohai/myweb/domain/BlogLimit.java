package xin.yanhaohai.myweb.domain;

import java.util.List;

/**
 * 分页查询实体类
 *
 */

public class BlogLimit<T> {
    //当前页数
    private Integer currentPage;
    //总页数
    private Integer pageCount;
    //数据总数
    private Integer blogCount;
    //每页数据条数
    private Integer number = 6;
    //数据
    private List<T> blogList;

    public BlogLimit(){}
    public BlogLimit(Integer currentPage,Integer blogCount) {
        this.currentPage = currentPage;
        this.blogCount = blogCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
    public void setPageCount() {
        //计算总页数
        if (this.blogCount!=null){
            this.pageCount = (blogCount%number==0? blogCount/number:(blogCount/number)+1);
        }
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public List<T> getBlogList() {
        return blogList;
    }

    public void setBlogList(List<T> blogList) {
        this.blogList = blogList;
    }

    @Override
    public String toString() {
        return "BlogLimit{" +
                "currentPage=" + currentPage +
                ", pageCount=" + pageCount +
                ", blogCount=" + blogCount +
                ", number=" + number +
                ", blogList=" + blogList +
                '}';
    }
}

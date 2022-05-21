package com.xuyuchao.pojo;

import java.util.List;

/**
 * 分页模型对象Page
 * @auther xuyuchao
 * @create 2022-04-06-13:23
 *
 * 泛型类
 */
public class Page<T> {
    //设置每页显示的数量为4条
    public static final int PAGE_SIZE = 4;

    private int pageNo;//当前页码数
    private int pageTotal;//总页数
    private int booksCount;//总记录数
    private int pageSize = PAGE_SIZE;//每页的显示图书数目
    //当前页的数据是由List容器储存
    private List<T> items;
    //分页条的请求地址
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Page() {
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public void setBooksCount(int booksCount) {
        this.booksCount = booksCount;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public Page(int pageNo, int pageTotal, int booksCount, int pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        this.booksCount = booksCount;
        this.pageSize = pageSize;
        this.items = items;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", booksCount=" + booksCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}

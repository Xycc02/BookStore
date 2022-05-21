package com.xuyuchao.service;

import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Page;

import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-04-02-14:46
 */
public interface BookService {
    //添加图书
    public void addBook(Book book);
    //修改图书信息
    public void updateBook(Book book);
    //删除图书
    public void deleteBookById(int id);
    //通过id查询图书,并返回Book对象
    public Book queryBookById(int id);
    //查询所有图书，并返回List列表
    public List<Book> queryBooks();
    //返回所有图书的数目
    public int queryBooksCount();
    //返回当前页的信息(Page<Book>)
    public Page<Book> page(int pageNo,int pageSize);
    //查询在minPrice~maxPrice价位的所有图书
    public Page<Book> queryPrice(int minPrice,int maxPrice,int pageNo,int pageSize);
}

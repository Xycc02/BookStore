package com.xuyuchao.dao;

import com.xuyuchao.pojo.Book;

import java.util.List;

/**
 * BookDao接口
 * @auther xuyuchao
 * @create 2022-04-01-23:55
 */
public interface BookDao {
    //添加图书
    public int addBook(Book book);
    //删除图书
    public int deleteBookById(int id);
    //修改图书
    public int updateBook(Book book);
    //通过id查询图书，并返回book对象
    public Book queryBookById(int id);
    //查询图书，并返回一个List列表
    public List<Book> queryBooks();
    //返回所有图书的记录数
    public int queryBooksCount();
    //查询当前页数据
    public List<Book> queryForItems(int begin,int pageSize);
    //查询图书价格范围区间的数据，并以List返回
    public List<Book> queryPrice(int minPrice,int maxPrice,int pageNo,int pageSize);
    //查询在价格区间内图书的数目
    public int queryPriceCount(int minPrice,int maxPrice);
}

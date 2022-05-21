package com.xuyuchao.dao.impl;

import com.xuyuchao.dao.BaseDAO;
import com.xuyuchao.dao.BookDao;
import com.xuyuchao.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-04-01-23:59
 */
public class BookDaoImpl extends BaseDAO implements BookDao {
    /**
     * 添加图书
     * @param book
     * @return
     */
    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(name,author,price,sales,stock,img_path) values(?,?,?,?,?,?)";
        int date = update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
        return date;
    }

    /**
     * 通过id删除图书
     * @param id
     * @return
     */
    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        int date = update(sql, id);
        return date;
    }

    /**
     * 修改图书
     * @param book
     * @return
     */
    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?,author = ?,price = ?,sales = ?,stock = ? where id = ?";
        int date = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getId());
        return date;
    }

    /**
     * 通过id查询图书
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(int id) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book where id = ?";
        Book book = queryForOne(Book.class, sql, id);
        return book;
    }

    /**
     * 查询所有图书，并以List列表返回
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book";
        List<Book> books = queryForList(Book.class, sql);
        return books;
    }

    /**
     * 查询并返回所有图书的数目
     * @return
     */
    @Override
    public int queryBooksCount() {
        String sql = "select count(*) from t_book";
        Object obj = queryForSingleValue(sql);
        int count = Integer.parseInt(obj.toString());
        return count;
    }

    /**
     * 分页，返回当前页的数据
     * @return List<Book>
     */
    @Override
    public List<Book> queryForItems(int begin,int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book limit ?,?";
        List<Book> books = queryForList(Book.class, sql, begin, pageSize);
        return books;
    }

    /**
     * 查询minPrice~maxPrice价格范围的所有图书，并以List返回
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public List<Book> queryPrice(int minPrice, int maxPrice,int begin,int pageSize) {
        String sql = "select id,name,author,price,sales,stock,img_path from t_book where price between ? and ? limit ?,?";
        List<Book> books = queryForList(Book.class, sql, new BigDecimal(minPrice), new BigDecimal(maxPrice),begin,pageSize);
        return books;
    }

    @Override
    public int queryPriceCount(int minPrice, int maxPrice) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Object obj = queryForSingleValue(sql, new BigDecimal(minPrice), new BigDecimal(maxPrice));
        return Integer.parseInt(obj.toString());
    }
}

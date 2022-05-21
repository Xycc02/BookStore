package com.xuyuchao.service.impl;

import com.xuyuchao.dao.UserDao;
import com.xuyuchao.dao.impl.BookDaoImpl;
import com.xuyuchao.dao.impl.UserDaoImpl;
import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Page;
import com.xuyuchao.service.BookService;

import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-04-02-14:52
 */
public class BookServiceImpl implements BookService {

    //创建BookDaoImpl类的对象
    private BookDaoImpl bookDao = new BookDaoImpl();

    /**
     *添加图书
     * @param book
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 通过id修改图书信息
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 通过id删除图书
     * @param id
     */
    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    /**
     * 通过id查询图书
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(int id) {
        Book book = bookDao.queryBookById(id);
        return book;
    }

    /**
     * 查询所有图书
     * @return 返回List
     */
    @Override
    public List<Book> queryBooks() {
        List<Book> books = bookDao.queryBooks();
        return books;
    }

    /**
     * 返回所有图书的数目
     * @return
     */
    @Override
    public int queryBooksCount() {
        int count = bookDao.queryBooksCount();
        return count;
    }

    /**
     * 负责处理当前页的全部数据，并返回一个page对象传给web层
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        //创建一个page<Book>对象
        Page<Book> page = new Page<Book>();
        //设置该对象中的当前页码(pageNo)
        page.setPageNo(pageNo);
        //设置该对象的每页显示数量(pageSize)
        page.setPageSize(pageSize);
        //求总记录数,并设置该对象的总记录数(booksCount)
        int count = bookDao.queryBooksCount();
        page.setBooksCount(count);
        //求总页数 = 总记录数 / 每页显示的数目
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;
        page.setPageTotal(pageTotal);
        //求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForItems(begin, pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    /**
     * //查询图书价格范围区间的数据，并以List返回
     * @param minPrice
     * @param maxPrice
     * @return
     */
    @Override
    public Page<Book> queryPrice(int minPrice, int maxPrice,int pageNo,int pageSize) {
        Page<Book> page = new Page<>();
        //设置该对象中的当前页码(pageNo)
        page.setPageNo(pageNo);
        //设置该对象的每页显示数量(pageSize)
        page.setPageSize(pageSize);
        //求该价格区间的总记录数,并设置该对象的总记录数(booksCount)
        int count = bookDao.queryPriceCount(minPrice,maxPrice);
        page.setBooksCount(count);
        //求总页数 = 总记录数 / 每页显示的数目
        int pageTotal = count % pageSize == 0 ? (count / pageSize) : (count / pageSize) + 1;
        page.setPageTotal(pageTotal);
        //求当前页数据的开始索引
        int begin = (pageNo - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryPrice(minPrice,maxPrice,begin,pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}

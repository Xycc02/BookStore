package com.xuyuchao.test;

import com.xuyuchao.dao.impl.BookDaoImpl;
import com.xuyuchao.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-04-02-13:57
 */
public class BookDaoImplTest {

    BookDaoImpl bookDaoImpl = new BookDaoImpl();

    @Test
    public void addBook() {
        Book book = new Book(1, "大话数据结构", "程杰", new BigDecimal(30.50), 100, 500, null);
        bookDaoImpl.addBook(book);
    }

    @Test
    public void deleteBookById() {
        bookDaoImpl.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        Book book = new Book(20, "人月神话", "超哥", new BigDecimal(90.00), 30, 100, null);
        bookDaoImpl.updateBook(book);
    }

    @Test
    public void queryBookById() {
        Book book = bookDaoImpl.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDaoImpl.queryBooks();

        //遍历List方式一
        //books.forEach(System.out::println);

        //遍历List方式二
        Iterator<Book> iterator = books.iterator();
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    /**
     * 分页Test
     */
    @Test
    public void queryForItems() {
        List<Book> books = bookDaoImpl.queryForItems(4, 5);
        books.forEach(System.out::println);
    }

    @Test
    public void queryPriceCount() {
        int count = bookDaoImpl.queryPriceCount(10, 200);
        System.out.println(count);
    }
    @Test
    public void queryPrice() {
        List<Book> books = bookDaoImpl.queryPrice(10, 200, 0, 4);
        books.forEach(System.out::println);
    }
}
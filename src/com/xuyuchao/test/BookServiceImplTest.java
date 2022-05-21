package com.xuyuchao.test;

import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Page;
import com.xuyuchao.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @auther xuyuchao
 * @create 2022-04-02-15:25
 */
public class BookServiceImplTest {

    BookServiceImpl bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(1, "大话数据结构", "程杰", new BigDecimal(30.50), 50, 100, "static/img/default.jpg"));
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(1, "大话数据结构", "程杰", new BigDecimal(30.50), 50, 150, "static/img/default.jpg"));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        books.forEach(System.out::println);
    }

    @Test
    public void queryBooksCount() {
        int count = bookService.queryBooksCount();
        System.out.println("共查询" + count + "条记录!");
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(4, 1);
        System.out.println(page);
    }

}
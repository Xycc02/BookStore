package com.xuyuchao.web;

import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Page;
import com.xuyuchao.service.BookService;
import com.xuyuchao.service.impl.BookServiceImpl;
import com.xuyuchao.utils.WebUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-04-02-15:45
 */
public class BookServlet extends BaseServlet{

    BookService bookService = new BookServiceImpl();


    /**
     * 查询所有图书，并将图书信息通过List返回，请求转发至book_manager.jsp页面
     * 并用jstl在前端遍历输出
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void queryAllBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //查询所有图书记录数
        int count = bookService.queryBooksCount();
        //将其存入request域中
        req.setAttribute("count",count);
        //把全部图书保存在Request域中
        req.setAttribute("books",books);
        //请求转发到 /pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);

        //此处不用重定向的原因:重定向不能共享数据，因此前端得不到books
        //resp.sendRedirect("/pages/manager/book_manager.jsp");
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //用beanUtils工具包将信息注入到bean中
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        //请求重定向，因为此处不需要共享数据，只有地址的变化，共享数据由queryAllBooks的请求转发去做
        resp.sendRedirect("/admin/bookServlet?action=page&pageNo=" + req.getParameter("addCurrentPage"));
    }

    /**
     * 删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBookById(id);
        resp.sendRedirect("/admin/bookServlet?action=page&pageNo=" + req.getParameter("delCurrentPage"));
    }

    /**
     * （修改图书）根据id在数据库中获取book对象,以便回显到前端表单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取当前需要修改的图书id
        int id = Integer.parseInt(req.getParameter("id"));
        //通过id在数据库中找到图书信息，并返回book对象
        Book book = bookService.queryBookById(id);
        //将该图书对象存放到request域中,是为了在用户修改图书的时候在input中回显book的正确信息
        req.setAttribute("book",book);
        //请求转发到book_edit.jsp页面(请求转发能享有同一个request的数据，即能知道id的图书信息)，jsp能通过requestScope.book.属性拿到具体的图书信息
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }

    /**
     * 修改图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        bookService.updateBook(book);
        resp.sendRedirect("/admin/bookServlet?action=page&pageNo=" + req.getParameter("updateCurrentPage"));
    }

    /**
     * 分页处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //查询所有图书记录数
        int count = bookService.queryBooksCount();
        //将其存入request域中
        req.setAttribute("count",count);
        //获取客户端请求的参数pageNo,PageSize
        int pageNo = Integer.parseInt(req.getParameter("pageNo") == null ? "1" : req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize") == null ? "4" : req.getParameter("pageSize"));
        //调用service层的queryForItems(int begin,int pageSize)得到当前页的图书信息，以List<Book>存储
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置page的url属性
        page.setUrl("/admin/bookServlet?action=page");
        //将该books保存到request域中
        req.setAttribute("page",page);
        //请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}

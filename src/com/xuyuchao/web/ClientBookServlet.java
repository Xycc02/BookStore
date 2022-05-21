package com.xuyuchao.web;

import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Page;
import com.xuyuchao.service.BookService;
import com.xuyuchao.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-04-08-14:22
 */
public class ClientBookServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();

    /**
     * 分页处理
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        //查询所有图书记录数
//        int count = bookService.queryBooksCount();
//        //将其存入request域中
//        req.setAttribute("count",count);

        //获取客户端请求的参数pageNo,PageSize
        int pageNo = Integer.parseInt(req.getParameter("pageNo") == null ? "1" : req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize") == null ? "4" : req.getParameter("pageSize"));
        //调用service层的page()得到当前页的图书信息，以List<Book>存储
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置page的url属性
        page.setUrl("/client/clientBookServlet?action=page");
        //将该page保存到request域中
        req.setAttribute("page",page);
        //请求转发到/pages/client/index.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }


    protected void queryPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取查询价格的minPrice和maxPrice
        int minPrice = Integer.parseInt(req.getParameter("minPrice") == "" ? "0" : req.getParameter("minPrice"));
        int maxPrice = Integer.parseInt(req.getParameter("maxPrice") == "" ? "99999999" : req.getParameter("maxPrice"));
        //获取客户端请求的参数pageNo,PageSize
        int pageNo = Integer.parseInt(req.getParameter("pageNo") == null ? "1" : req.getParameter("pageNo"));
        int pageSize = Integer.parseInt(req.getParameter("pageSize") == null ? "4" : req.getParameter("pageSize"));
        //调用service层的queryPrice()查询价格区间的全部图书
        Page<Book> page = bookService.queryPrice(minPrice, maxPrice, pageNo, pageSize);
        page.setUrl("/client/clientBookServlet?action=queryPrice&minPrice=" + minPrice + "&maxPrice=" + maxPrice);
        //将page储存在request域中
        req.setAttribute("page",page);
        //请求转发
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }

}

package com.xuyuchao.web;

import com.google.gson.Gson;
import com.xuyuchao.pojo.Book;
import com.xuyuchao.pojo.Cart;
import com.xuyuchao.pojo.CartItem;
import com.xuyuchao.service.BookService;
import com.xuyuchao.service.impl.BookServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @auther xuyuchao
 * @create 2022-04-12-16:15
 */
public class CartServlet extends BaseServlet{

    private BookService bookService = new BookServiceImpl();
    /**
     * 添加商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute("loginUser") != null) {
            //获取请求的参数(商品id)
            int id = Integer.parseInt(req.getParameter("id"));
            //在数据库中查找id的商品,并返回Book对象
            Book book = bookService.queryBookById(id);
            //将book存到session域中,用于首页添加购物车之后的回显
            req.getSession().setAttribute("book",book);
            //把图书信息转化成CartItem商品项
            CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice().multiply(new BigDecimal(1)));
            //添加商品,把购物车从Session中存取
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if(cart == null) {
                //客户端没有Cart的Session信息
                cart = new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            cart.addItem(item);
            //得到用户在首页点击添加购物车时,商品所在页面的地址(通过请求头Referer获取)
            String referer = req.getHeader("Referer");
            //重定向
            resp.sendRedirect(referer);
        }else{
            resp.sendRedirect("/pages/user/login.jsp");
        }
    }

    /**
     * Ajax添加购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxAddItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

            //获取请求的参数(商品id)
            int id = Integer.parseInt(req.getParameter("id"));
            //在数据库中查找id的商品,并返回Book对象
            Book book = bookService.queryBookById(id);
            //把图书信息转化成CartItem商品项
            CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice().multiply(new BigDecimal(1)));
            //添加商品,把购物车从Session中存取
            Cart cart = (Cart) req.getSession().getAttribute("cart");
            if(cart == null) {
                //客户端没有Cart的Session信息
                cart = new Cart();
                req.getSession().setAttribute("cart",cart);
            }
            cart.addItem(item);

            Map<String,Object> resultMap =  new HashMap<>();
            //购物车商品总数量
            resultMap.put("totalCount",cart.getTotalCount());
            //当前商品项的名称
            resultMap.put("lastName",item.getName());

            Gson gson = new Gson();
            //将map转化为json字符串
            String res = gson.toJson(resultMap);
            //以响应流的形式回传客户端
            resp.getWriter().write(res);
    }

    /**
     * 删除商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从Session中取出购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        int id = Integer.parseInt(req.getParameter("id"));
        cart.deleItem(id);
        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer);
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clear();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取修改图书的id,和修改商品的数量
        int id = Integer.parseInt(req.getParameter("id"));
        int count = Integer.parseInt(req.getParameter("count"));
        //在Session中取出购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateCount(id,count);
        //重定向
        resp.sendRedirect(req.getHeader("Referer"));
    }
}

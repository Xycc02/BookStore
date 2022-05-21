package com.xuyuchao.web; /**
 * @auther xuyuchao
 * @create 2022-03-30-18:44
 */

import com.google.gson.Gson;
import com.xuyuchao.pojo.User;
import com.xuyuchao.service.UserService;
import com.xuyuchao.service.impl.UserServiceImpl;
import com.xuyuchao.utils.WebUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;
public class UserServlet extends BaseServlet {

    private UserService userService= new UserServiceImpl();

    /**
     * 登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //登录
        //获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User loginUser = userService.login(new User(1, username, password, null));
        //将用户登录信息存在Session域中
        req.getSession().setAttribute("loginUser",loginUser);
        //检查用户名密码是否正确
        if( loginUser != null) {
            //登录成功，保存用户登录信息到Session域中
            req.getSession().setAttribute("username",username);
            req.getSession().setAttribute("loginUser",loginUser);
            //用户名密码正确,请求转发到登录成功页面
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }else{
            //用req域数据保存信息，并回显
            req.setAttribute("errorMsg","用户名或密码错误,请重新输入!");
            req.setAttribute("username",username);
            //用户名密码不正确，请求转发到登录页面，重新登录
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }

    /**
     * 注册
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //注册

        //获取Session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除Session中的验证码(过河拆桥),防止用户重复请求
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);

        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

        //
        //2.检查验证码是否正确
        if(token != null && token.equalsIgnoreCase(code)){
            //验证码正确
            //3.检查用户名是否可用
            if(userService.existUsername(username)){
                //用户名存在，设置回显信息
                req.setAttribute("errorMsg","用户名已存在！改一改吧!");
                req.setAttribute("username",username);
                req.setAttribute("password",password);
                req.setAttribute("email",email);
                //用户名已存在，不可用，跳转回注册页面
                System.out.println("用户名已存在，不可用");
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //用户名不存在，可用
                //调用Service保存到数据库中
                //一行代码，将客户端提交信息注入到user对象中
                User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
                userService.registUser(user);
                //跳转到注册成功页面regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //验证码错误
            req.setAttribute("username",username);
            req.setAttribute("password",password);
            req.setAttribute("email",email);
            //回显
            req.setAttribute("errorMsg","验证码错误!请重新输入!");
            //验证码错误,跳转回注册页面
            System.out.println("验证码错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session
        req.getSession().invalidate();
        //重定向到登陆页面(或首页)
        resp.sendRedirect("/pages/user/login.jsp");
    }

    /**
     * ajax实现判断用户名是否存在
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void ajaxExistUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //获取username
        String username = req.getParameter("username");
        //调用userService.existUsername()判断用户名是否存在
        boolean flag = userService.existUsername(username);
        //把结果封装成Map对象,转化为JSON返回客户端
        Map<String,Object> res = new HashMap<>();
        res.put("flag",flag);

        Gson gson = new Gson();
        String json = gson.toJson(res);
        resp.getWriter().write(json);
    }

}

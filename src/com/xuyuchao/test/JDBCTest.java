package com.xuyuchao.test;

import com.xuyuchao.pojo.User;
import com.xuyuchao.utils.JDBCUtils;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;

/**
 * @auther xuyuchao
 * @create 2022-03-17-12:23
 */
public class JDBCTest {

    @Test
    public void Test01() {
        try {
            System.out.println(JDBCUtils.getDruidConnection());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void Test02() throws Exception {
        for(int i = 1;i <= 100;i++ ){
            Connection con = JDBCUtils.getDruidConnection();
            System.out.println(con);

        }
    }
}

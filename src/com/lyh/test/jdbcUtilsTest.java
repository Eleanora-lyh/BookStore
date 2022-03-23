package com.lyh.test;

import com.lyh.utils.JdbcUtils;
import org.junit.Test;

import java.io.InputStream;

public class jdbcUtilsTest {
    @Test
    public void testJdbcUtils() {
        InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
        System.out.println(is);
    }
}

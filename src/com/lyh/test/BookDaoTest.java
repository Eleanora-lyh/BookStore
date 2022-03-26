package com.lyh.test;

import com.lyh.dao.BookDao;
import com.lyh.dao.impl.BookDaoImpl;
import com.lyh.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    private BookDao  bookDao= new BookDaoImpl();
    @Test
    public void addBook() {
        bookDao.addBook(new Book(null,"qwert","213456",
                new BigDecimal(99),100,0,null));
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(22,"@@@","213456",
                new BigDecimal(99),100,0,null));
    }

    @Test
    public void deleteById() {
        bookDao.deleteById(22);
    }

    @Test
    public void queryBookByID() {
        System.out.println( bookDao.queryBookByID(22) );
    }

    @Test
    public void queryBooks() {
        for (Book queryBook : bookDao.queryBooks()) {
            System.out.println(queryBook);
        }
    }
/**
 * @Description 测试新添加的BookDaoImpl关于查询总记录数、获取某页数据方法是否正确
 **/
    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        System.out.println(bookDao.queryForPageTotalCountByPrice(10,40));
    }

    @Test
    public void queryForPageItems() {
        for (Book book : bookDao.queryForPageItems(8, 4)) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageItemsByPrice() {
        for (Book book : bookDao.queryForPageItemsByPrice(0, 4,10,40)) {
            System.out.println(book);
        }
    }
}
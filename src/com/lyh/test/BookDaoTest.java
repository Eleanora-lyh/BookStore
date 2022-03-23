package com.lyh.test;

import com.lyh.dao.BookDao;
import com.lyh.dao.impl.BookDaoImpl;
import com.lyh.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;

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
}
package com.lyh.test;

import com.lyh.pojo.Book;
import com.lyh.pojo.Page;
import com.lyh.service.BookService;
import com.lyh.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;


public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"****","213456",
                new BigDecimal(99),100,0,null));
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(23,"**1**","213456",
                new BigDecimal(99),100,0,null));
    }

    @Test
    public void deleteById() {
        bookService.deleteById(23);
    }

    @Test
    public void queryBookByID() {
        System.out.println(bookService.queryBookByID(2));
    }

    @Test
    public void queryBooks() {
        System.out.println(bookService.queryBooks());
        for (Book queryBook : bookService.queryBooks()) {
            System.out.println(queryBook);
        }
    }

    @Test
    public void page(){
        System.out.println(bookService.page(6,Page.PAGE_SIZE));
    }
    @Test
    public void pageByPrice(){
        System.out.println(bookService.pageByPrice(1,Page.PAGE_SIZE,10,40));
    }

}
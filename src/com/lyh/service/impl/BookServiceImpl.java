package com.lyh.service.impl;

import com.lyh.dao.BookDao;
import com.lyh.dao.impl.BookDaoImpl;
import com.lyh.pojo.Book;
import com.lyh.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public void deleteById(Integer id) {
        bookDao.deleteById(id);
    }

    @Override
    public Book queryBookByID(Integer id) {
        return bookDao.queryBookByID(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }
}

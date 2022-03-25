package com.lyh.service;

import com.lyh.pojo.Book;
import com.lyh.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void updateBook(Book book);
    public void deleteById(Integer id);
    public Book queryBookByID(Integer id);
    public List<Book> queryBooks();
    Page<Book> page(int pageNo, int pageSize);
}

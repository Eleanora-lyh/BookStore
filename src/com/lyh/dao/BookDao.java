package com.lyh.dao;

import com.lyh.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int updateBook(Book book);
    public int deleteById(Integer id);
    public Book queryBookByID(Integer id);
    public List<Book> queryBooks();

    Integer queryForPageTotalCount();

    List<Book> queryForPageItems(int begin, int pageSize);
}

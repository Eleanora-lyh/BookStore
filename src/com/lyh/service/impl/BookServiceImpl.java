package com.lyh.service.impl;

import com.lyh.dao.BookDao;
import com.lyh.dao.impl.BookDaoImpl;
import com.lyh.pojo.Book;
import com.lyh.pojo.Page;
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

    /**
     * @return com.lyh.pojo.Page<com.lyh.pojo.Book>
     * @Description 设置页码下的page对象
     * @Param [pageNo, pageSize]
     **/
    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<Book>();


        //2、设置每页显示的数量
        page.setPageSize(pageSize);

        //3、设置page总记录数:pageTotalCount
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        page.setPageTotalCount(pageTotalCount);

        //3、设置page的总页码:pageTotal
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize > 0) pageTotal += 1;
        page.setPageTotal(pageTotal);


        //1、设置page当前页码:pageNo
        page.setPageNo(pageNo);

        //5、设置page数据:items
        int begin = (page.getPageNo() - 1) * pageSize;
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        page.setItems(items);

        return page;
    }
}

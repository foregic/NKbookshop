package com.test.NKbookshop.service.Impl;

import com.test.NKbookshop.dao.BookDao;
import com.test.NKbookshop.dao.jdbcImpl.BookDaoImpl;
import com.test.NKbookshop.domain.po.Book;
import com.test.NKbookshop.service.BookService;

import java.util.List;


public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    @Override
    public boolean register(Book book) {
        boolean res = false;
        int result = -1;
        Book existBook = bookDao.findByName(book.getBookName());
        if (existBook != null) {
            return false;
        }
        result = bookDao.insert(book);
        if (result != -1 && result != 0) {
            res = true;
        }
        return res;

    }

    @Override
    //书籍已经在仓库里了
    public boolean isValidate(String bkn) {
        boolean res = false;
        Book book = bookDao.findByName(bkn);
        if (book != null && bkn.equals(book.getBookName())) {
            res = true;
        }
        return res;
    }


    @Override
    public boolean modify(Book book) {
        boolean res = false;
        int result = -1;
        result = bookDao.update(book);
        if (result != -1 && result != 0) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean remove(Book book) {
        boolean res = false;
        int result = -1;
        result = bookDao.delete(book);
        if (result != -1 && result != 0) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean removeByBookName(String bookName) {
        boolean res = false;
        int result = -1;
        result = bookDao.delete(bookDao.findByName(bookName));
        if (result != -1 && result != 0) {
            res = true;
        }
        return res;
    }

    @Override
    public boolean removeByBookId(Integer id) {
        boolean res = false;
        int result = -1;
        result = bookDao.delete(bookDao.findById(id));
        if (result != -1 && result != 0) {
            res = true;
        }
        return res;
    }


    @Override
    public List<Book> findAll() {
        return bookDao.findAll();

    }
}
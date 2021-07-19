package com.test.NKbookshop.service;


import com.test.NKbookshop.domain.po.Book;

import java.util.List;

public interface BookService {
    boolean register(Book book) ;
    boolean isValidate(String bkn);
    boolean modify(Book book);
    boolean remove(Book book);

    boolean removeByBookName(String bookName);
    boolean removeByBookId(Integer id);


    List<Book>findAll();

}

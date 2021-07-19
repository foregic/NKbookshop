package com.test.NKbookshop.dao;


import com.test.NKbookshop.domain.po.Book;

import java.util.List;

public interface BookDao extends GenericDAO<Book, Integer> {
    Book findByName(String bookName);
    List<Book> findBySQL(String sql, Object... params);
}

package com.test.NKbookshop.dao;

import com.test.NKbookshop.domain.po.Author;

public interface AuthorDao extends GenericDAO<Author, Integer> {
    //    Set<Book> findByName(String authorName);
    Author findByName(String authorName);
}

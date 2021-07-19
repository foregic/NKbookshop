package com.test.NKbookshop.web.controller;

import com.test.NKbookshop.domain.po.Book;
import com.test.NKbookshop.service.BookService;
import com.test.NKbookshop.service.Impl.BookServiceImpl;

import java.util.List;

public class BookController {


    private Book book;
    private BookService bookService = new BookServiceImpl();

    public String load() {
        boolean res = bookService.isValidate(book.getBookName());
        if (res) {
            return "success";
        } else {
            return "cannot load books";
        }
    }

    public String register() {
        boolean res = bookService.register(book);
        if (res) {
            return "registered successfully";
        } else {
            return "The book has already been in the warehouse";
        }

    }

    public List<Book> showAllBooks() {
        return bookService.findAll();
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}

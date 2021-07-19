package com.test.NKbookshop.view;

import com.test.NKbookshop.domain.po.Author;
import com.test.NKbookshop.domain.po.Book;
import com.test.NKbookshop.domain.po.Press;
import com.test.NKbookshop.web.controller.BookController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookView {
    public static void main(String[] args) {
//        register();
        showAllBooks();

        Set<Author> authors1 = new HashSet<Author>();
        Set<Author> authors2=new HashSet<Author>();
        Author author1=new Author("飞龙在天","男");
        Author author2=new Author("女性","女");
        authors1.add(author1);
        authors2.add(author1);
        authors2.add(author2);
        Press press1=new Press("人民出版社");
        Press press2=new Press("机械出版社");
        Book book1=new Book("计网",50,authors1,press1);
        Book book2=new Book("天工开物",29,authors2,press2);

        BookController bookController = new BookController();
        bookController.setBook(book1);
        String result = bookController.register();
        System.out.println(result);
        System.out.println();


        bookController.setBook(book2);
        result = bookController.register();
        System.out.println(result);
        System.out.println();

        showAllBooks();
    }

//    public static void load() {
//        BookController bookController = new BookController();
//
//    }
//
//    public static void register() {
//        BookController bookController = new BookController();
//
//        String bookName = "三体1";
//        int price = 50;
//        String author = "刘慈欣";
//
//        Book book = new Book(bookName, price, author);
//
//        bookController.setBook(book);
//
//        String result = bookController.register();
//        System.out.println(result);
//    }
//
    public static void showAllBooks() {
        BookController bookController = new BookController();
        List<Book> books = bookController.showAllBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

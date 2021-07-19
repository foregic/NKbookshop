
package com.test.NKbookshop.domain.po;

import java.io.Serializable;
import java.util.Set;

public class Book implements Serializable {
    private Integer id;
    private String bookName;//书名
    private int price;
    private Set<Author> authors =null;
    private Press press;

    public Book() {
    }

    public Book(Integer id, String bookName, int price, Press press) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.press = press;
    }

    public Book(Integer id, String bookName, int price, Set<Author> authors, Press press) {
        this.id = id;
        this.bookName = bookName;
        this.price = price;
        this.authors = authors;
        this.press = press;
    }

    public Book(String bookName, int price, Set<Author> authors, Press press) {
        this.bookName = bookName;
        this.price = price;
        this.authors = authors;
        this.press = press;
    }

    public Integer getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public int getPrice() {
        return price;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public Press getPress() {
        return press;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", price=" + price +
                ", authors=" + authors +
                ", press=" + press +
                '}';
    }
}

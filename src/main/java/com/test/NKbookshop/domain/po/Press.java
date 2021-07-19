package com.test.NKbookshop.domain.po;

import java.io.Serializable;
import java.util.Set;

public class Press implements Serializable {
    private Integer id;
    private String pressName;
    private Set<Book> books;

    public Press() {
    }

    public Press(String pressName) {
        this.pressName = pressName;
    }



    public Press(Integer id, String pressName, Set<Book> books) {
        this.id = id;
        this.pressName = pressName;
        this.books = books;
    }

    public Press(String pressName, Set<Book> books) {
        this.pressName = pressName;
        this.books = books;
    }

    public Press(Integer id, String pressname) {
        this.id = id;
        this.pressName = pressName;
    }

    public Integer getId() {
        return id;
    }

    public String getPressName() {
        return pressName;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Press{" +
                "id=" + id +
                ", pressName='" + pressName + '\'' +
                ", books=" + books +
                '}';
    }
}

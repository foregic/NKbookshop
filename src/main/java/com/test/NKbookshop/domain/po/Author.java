package com.test.NKbookshop.domain.po;

import java.io.Serializable;
import java.util.Set;

public class Author implements Serializable {
    private Integer id;
    private String authorName;
    String gender;
    private Set<Book> books;

    public Author() {
    }



    public Author(Integer id, String authorName, String  gender) {
        this.id = id;
        this.authorName = authorName;
        this.gender = gender;
    }
    public Author( String authorName, String  gender) {
        this.id = id;
        this.authorName = authorName;
        this.gender = gender;
    }

    public Author(Integer id, String authorName, String gender, Set<Book> books) {
        this.id = id;
        this.authorName = authorName;
        this.gender = gender;
        this.books = books;
    }

    public Author(String authorName, String gender, Set<Book> books) {
        this.authorName = authorName;
        this.gender = gender;
        this.books = books;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getGender() {
        return gender;
    }

    public Set<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", gender='" + gender + '\'' +
                ", books=" + books +
                '}';
    }
}

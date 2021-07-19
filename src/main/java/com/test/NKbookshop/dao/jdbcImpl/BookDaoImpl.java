package com.test.NKbookshop.dao.jdbcImpl;

import com.test.NKbookshop.dao.BookDao;
import com.test.NKbookshop.domain.po.Author;
import com.test.NKbookshop.domain.po.Book;
import com.test.NKbookshop.domain.po.Press;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BookDaoImpl extends GenericBaseDao implements BookDao {
    @Override
    public Book findByName(String bookName) {
        Book book = null;
        try {
            this.getConnection();
            String sql = "select * from books left join presses on books.pid=presses.id where bkn = ? ";
            this.executeQuery(sql, bookName);
            Set<Author> authors=new HashSet<Author>();
            if (this.rs != null && this.rs.next()) {
                Integer id = rs.getInt("id");

                GenericBaseDao baseDao=new GenericBaseDao();
                baseDao.getConnection();
                String a_sql ="select * from bookAuthor left join authors on bookAuthor.authorId=authors.id where bookAuthor.bookId=?";
                baseDao.executeQuery(a_sql,id);
                if(baseDao.rs!=null){
                    while (baseDao.rs.next()){
                        Author author=new Author(baseDao.rs.getInt("id"),baseDao.rs.getString("authorname"),
                                baseDao.rs.getString("sex"));
                        authors.add(author);
                    }
                }
                baseDao.closeALL();

                Press press = new Press(rs.getString("pressname"));
                book = new Book(rs.getInt("id"), rs.getString("bkn"),
                        rs.getInt("price"),authors, press);
            }
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public Book findById(Integer id) {
        Book book = null;
        try {
            this.getConnection();
            String sql = "select * from books left join presses on books.pid=presses.id where books.id = ? ";
            this.executeQuery(sql, id);
            Set<Author> authors=new HashSet<Author>();
            if (this.rs != null && this.rs.next()) {

                GenericBaseDao baseDao=new GenericBaseDao();
                baseDao.getConnection();
                String a_sql ="select * from bookAuthor left join authors on bookAuthor.authorId=authors.id where bookAuthor.bookId=?";
                baseDao.executeQuery(a_sql,id);
                if(baseDao.rs!=null){
                    while (baseDao.rs.next()){
                        Author author=new Author(baseDao.rs.getInt("id"),baseDao.rs.getString("authorname"),
                                baseDao.rs.getString("sex"));
                        authors.add(author);
                    }
                }
                baseDao.closeALL();
                Press press = new Press(rs.getString("pressname"));
                book = new Book(rs.getInt("id"), rs.getString("bkn"),
                        rs.getInt("price"),authors, press);
            }
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = null;
        try {
            this.getConnection();
            String sql = "select * from books";
            this.executeQuery(sql);
            if (rs != null) {
                books = new ArrayList<Book>();
                while (rs.next()) {
                    BookDaoImpl bookDaoImpl=new BookDaoImpl();
                    Book book=bookDaoImpl.findById(rs.getInt("id"));
//                    Book book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                            rs.getInt("price"), rs.getString("bka"));
                    books.add(book);
                }
            }
            this.closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public List<Book> findBySQL(String sql, Object... params) {
        List<Book> books = null;
        try {
            getConnection();
            executeQuery(sql, params);
            if (rs != null) {
                books = new ArrayList<Book>();
                while (rs.next()) {
                    BookDaoImpl bookDaoImpl=new BookDaoImpl();
                    Book book = bookDaoImpl.findById(rs.getInt("id"));
//                    Book book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                            rs.getInt("price"), rs.getString("bka"));
                    books.add(book);
                }
            }
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public int insert(Book book) {
        int result = -1;
        try {
            getConnection();

            AuthorDaoImpl authorDaoImpl=new AuthorDaoImpl();
            for(Author author:book.getAuthors()){
                authorDaoImpl.insert(author);
            }


            PressDaoImpl pressDao=new PressDaoImpl();
            if(pressDao.insert(book.getPress())==-1){
                System.out.println("出版社已存在");
            }

            String sql = "insert into books values(null,?,?,?)";
            executeUpdate(sql, book.getBookName(), book.getPrice(),pressDao.findByName(book.getPress().getPressName()).getId());

            GenericBaseDao genericBaseDao =new GenericBaseDao();
            for (Author author:book.getAuthors()){
                String baSQL="insert into bookAuthor values(?,?)";
                genericBaseDao.executeUpdate(baSQL,findByName(book.getBookName()).getId(),(authorDaoImpl.findByName(author.getAuthorName())).getId());
            }

            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Book book) {
        int result = -1;
        try {
            getConnection();
            delete(book);
            insert(book);
            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Integer id) {
        int result = -1;
        try {
            getConnection();
            String sql = "delete from books where id=?";
            executeUpdate(sql, id);
            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int delete(Book book) {
        int result = -1;
        try {
            getConnection();
            String sql = "delete from books where id=?";
            executeUpdate(sql, book.getId());
            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


//    @Override
//    public Book findByName(String bookName) {
//        Book book = null;
//        try {
//            this.getConnection();
//            String sql = "select * from books where bkn = ? ";
//            this.executeQuery(sql, bookName);
//            if (this.rs != null && this.rs.next()) {
//                book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                        rs.getInt("price"), rs.getString("bka"));
//            }
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return book;
//    }
//
//    @Override
//    public Book findById(Integer id) {
//        Book book = null;
//        try {
//            this.getConnection();
//            String sql = "select * from books where id = ? ";
//            this.executeQuery(sql, id);
//            if (this.rs != null && this.rs.next()) {
//                book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                        rs.getInt("price"), rs.getString("bka"));
//            }
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return book;
//    }
//
//    @Override
//    public List<Book> findAll() {
//        List<Book> books = null;
//        try {
//            this.getConnection();
//            String sql = "select * from books";
//            this.executeQuery(sql);
//            if (rs != null) {
//                books = new ArrayList<Book>();
//                while (rs.next()) {
//                    Book book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                            rs.getInt("price"), rs.getString("bka"));
//                    books.add(book);
//                }
//            }
//            this.closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    @Override
//    public List<Book> findBySQL(String sql, Object... params) {
//        List<Book> books = null;
//        try {
//            getConnection();
//            executeQuery(sql, params);
//            if (rs != null) {
//                books = new ArrayList<>();
//                while (rs.next()) {
//                    Book book = new Book(rs.getInt("id"), rs.getString("bkn"),
//                            rs.getInt("price"), rs.getString("bka"));
//                    books.add(book);
//                }
//            }
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return books;
//    }
//
//    @Override
//    public int insert(Book book) {
//        int result = -1;
//        try {
//            getConnection();
//            String sql = "insert into books values(null,?,?,?)";
//            executeUpdate(sql, book.getBookName(), book.getPrice(), book.getAuthor());
//            result = this.result;
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//
//    }
//
//    @Override
//    public int update(Book book) {
//        int result = -1;
//        try {
//            getConnection();
//            String sql = "update books set bkn=?,price=?,bka=? while id=?";
//            executeUpdate(sql, book.getBookName(), book.getPrice(), book.getAuthor(), book.getId());
//            result = this.result;
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public int delete(Integer id) {
//        int result = -1;
//        try {
//            getConnection();
//            String sql = "delete from books while id=?";
//            executeUpdate(sql, id);
//            result = this.result;
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public int delete(Book book) {
//        int result = -1;
//        try {
//            getConnection();
//            String sql = "delete from books while id=?";
//            executeUpdate(sql, book.getId());
//            result = this.result;
//            closeALL();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

}

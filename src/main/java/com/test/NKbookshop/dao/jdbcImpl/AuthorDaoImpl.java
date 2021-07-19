package com.test.NKbookshop.dao.jdbcImpl;

import com.test.NKbookshop.dao.AuthorDao;
import com.test.NKbookshop.domain.po.Author;

import java.sql.SQLException;
import java.util.List;

public class AuthorDaoImpl extends GenericBaseDao implements AuthorDao {
//    @Override
//    public Set<Book> findByName(String authorName) {
//        return null;
//    }

    @Override
    public Author findByName(String authorName){
        Author author = null;
        try {
            this.getConnection();
            String sql = "select * from authors  where authorname = ? ";
            this.executeQuery(sql, authorName);
            if (this.rs != null && this.rs.next()) {
                author = new Author(rs.getInt("id"),authorName,rs.getString("sex"));
            }
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return author;

    }
    @Override
    public Author findById(Integer integer) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public List<Author> findBySQL(String sql, Object... params) {
        return null;
    }

    @Override
    public int insert(Author author) {
        int result = -1;
        try {
            getConnection();
            String sql = "insert into authors values (null,?,?)";
            if (author.getGender() == null) {
                executeUpdate(sql, author.getAuthorName(), "null");
            } else {
                executeUpdate(sql, author.getAuthorName(), author.getGender());
            }
            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Author author) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public int delete(Author author) {
        return 0;
    }
}

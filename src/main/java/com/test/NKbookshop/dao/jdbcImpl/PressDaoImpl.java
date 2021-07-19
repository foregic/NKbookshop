package com.test.NKbookshop.dao.jdbcImpl;

import com.test.NKbookshop.dao.PressDao;
import com.test.NKbookshop.domain.po.Press;

import java.sql.SQLException;
import java.util.List;

public class PressDaoImpl extends GenericBaseDao implements PressDao {

    @Override
    public Press findByName(String pressName) {
        Press press = null;
        try {
            this.getConnection();
            String sql = "select * from presses  where pressname = ? ";
            this.executeQuery(sql, pressName);
            if (this.rs != null && this.rs.next()) {

                press = new Press(rs.getInt("id"), rs.getString("pressname"));
            }
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return press;
    }

    @Override
    public Press findById(Integer integer) {
        return null;
    }

    @Override
    public List<Press> findAll() {
        return null;
    }

    @Override
    public List<Press> findBySQL(String sql, Object... params) {
        return null;
    }

    @Override
    public int insert(Press press) {
        int result = -1;
        try {
            getConnection();
            String sql = "insert into presses values (null,?)";
            executeUpdate(sql,press.getPressName());
            result = this.result;
            closeALL();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(Press press) {
        return 0;
    }

    @Override
    public int delete(Integer integer) {
        return 0;
    }

    @Override
    public int delete(Press press) {
        return 0;
    }


}

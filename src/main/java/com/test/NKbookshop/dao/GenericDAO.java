package com.test.NKbookshop.dao;


import java.io.Serializable;
import java.util.List;


public interface GenericDAO<Entity extends Serializable,ID extends Object> {
    Entity findById(ID id);
    List<Entity> findAll();
    List<Entity> findBySQL(String sql, Object... params);
    int insert(Entity entity);
    int update(Entity entity);
    int delete(ID id);
    int delete(Entity entity);

}

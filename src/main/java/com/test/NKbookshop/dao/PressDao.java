package com.test.NKbookshop.dao;

import com.test.NKbookshop.domain.po.Press;

public  interface PressDao extends GenericDAO<Press, Integer> {
        Press findByName(String pressName);
}

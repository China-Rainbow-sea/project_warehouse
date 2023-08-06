package com.rainbowsea.mhl.dao;

import com.rainbowsea.mhl.dao.impl.DiningDao;
import com.rainbowsea.mhl.javaBean.Dining;
import com.rainbowsea.mhl.utils.JDBCUtilsByDruid;

import java.util.List;

public class DiningDaoImpl extends JDBCUtilsByDruid<Dining> implements DiningDao<Dining> {
    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public Dining selectSingle(String sql, Class<Dining> clazz, Object... args) {
        return super.selectSingle(sql, clazz, args);
    }

    @Override
    public List<Dining> selectMany(String sql, Class<Dining> clazz, Object... args) {
        return super.selectMany(sql, clazz, args);
    }

    @Override
    public Object selectSum(String sql, Object... args) {
        return super.selectSum(sql, args);
    }
}

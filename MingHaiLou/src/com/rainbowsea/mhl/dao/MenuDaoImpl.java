package com.rainbowsea.mhl.dao;

import com.rainbowsea.mhl.dao.impl.MenuDao;
import com.rainbowsea.mhl.javaBean.Menu;
import com.rainbowsea.mhl.utils.JDBCUtilsByDruid;

import java.util.List;

public class MenuDaoImpl extends JDBCUtilsByDruid<Menu> implements MenuDao<Menu> {

    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public Menu selectSingle(String sql, Class<Menu> clazz, Object... args) {
        return super.selectSingle(sql, clazz, args);
    }

    @Override
    public List<Menu> selectMany(String sql, Class<Menu> clazz, Object... args) {
        return super.selectMany(sql, clazz, args);
    }

    @Override
    public Object selectSum(String sql, Object... args) {
        return super.selectSum(sql, args);
    }
}

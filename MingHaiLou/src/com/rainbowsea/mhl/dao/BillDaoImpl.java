package com.rainbowsea.mhl.dao;

import com.rainbowsea.mhl.dao.impl.BillDao;
import com.rainbowsea.mhl.javaBean.Bill;
import com.rainbowsea.mhl.utils.JDBCUtilsByDruid;

import java.util.List;


public class BillDaoImpl extends JDBCUtilsByDruid<Bill> implements BillDao<Bill>  {

    @Override
    public int update(String sql, Object... args) {
        return super.update(sql, args);
    }

    @Override
    public Bill selectSingle(String sql, Class<Bill> clazz, Object... args) {
        return super.selectSingle(sql, clazz, args);
    }

    @Override
    public List<Bill> selectMany(String sql, Class<Bill> clazz, Object... args) {
        return super.selectMany(sql, clazz, args);
    }

    @Override
    public Object selectSum(String sql, Object... args) {
        return super.selectSum(sql, args);
    }
}

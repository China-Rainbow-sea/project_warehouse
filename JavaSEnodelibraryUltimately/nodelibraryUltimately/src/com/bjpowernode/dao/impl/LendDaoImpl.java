package com.bjpowernode.dao.impl;

import com.bjpowernode.bean.Lend;
import com.bjpowernode.bean.PathConstant;
import com.bjpowernode.bean.User;
import com.bjpowernode.dao.LendDao;
import com.bjpowernode.util.BeanUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LendDaoImpl implements LendDao {

    /**
     *  查询
     * @param lend
     * @return
     */
    @Override
    public List<Lend> select(Lend lend) {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH))) {

            List<Lend> list = (List<Lend>)ois.readObject();
            if (lend == null || "".equals(lend.getId())) {
                return list;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return new ArrayList<>();
    }

    /**
     *  添加借阅数据
     * @param lend
     */
    @Override
    public void add(Lend lend) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>)ois.readObject();
            list.add(lend);
            //写出
            oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
            oos.writeObject(list);
        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }finally {
            try {
                if (ois != null)
                    ois.close();
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void delete(String id) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>) ois.readObject();
            if (list != null) {
                Lend originLend = list.stream().filter(r -> Objects.equals(id, r.getId())).findFirst().get();
                list.remove(originLend);
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            //向上层抛出异常信息
            throw new RuntimeException("删除借阅数据出问题了");
        }
    }

    @Override
    public void update(Lend lend) {
        //将list数据从文件中查出来
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(PathConstant.LEND_PATH));
            List<Lend> list = (List<Lend>) ois.readObject();
            if (list != null) {
                //从list中查找要修改的数据
                Lend originLend = list.stream().filter(u -> u.getId().equals(lend.getId()) ).findFirst().get();
                //修改数据
                BeanUtil.populate(originLend,lend);
                //将数据持久化到文件中
                oos = new ObjectOutputStream(new FileOutputStream(PathConstant.LEND_PATH));
                oos.writeObject(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

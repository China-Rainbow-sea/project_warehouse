package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * 对书中进行一个增删改操作
 */
public interface BookDao {
    List<Book> select(Book book);

    void add(Book book);

    void delete(int id);

    void update(Book book);
}

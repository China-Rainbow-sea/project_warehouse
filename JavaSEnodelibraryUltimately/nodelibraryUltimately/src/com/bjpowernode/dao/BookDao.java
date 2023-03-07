package com.bjpowernode.dao;

import com.bjpowernode.bean.Book;

import java.util.List;

/**
 * �����н���һ����ɾ�Ĳ���
 */
public interface BookDao {
    List<Book> select(Book book);

    void add(Book book);

    void delete(int id);

    void update(Book book);
}

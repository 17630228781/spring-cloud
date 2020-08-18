package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;

import java.util.List;

public interface UserService {
    List<BookBean> findBookList();

    List<TreeBean> findTreeList();

    void deleteBook(Integer ids);

    List<TypeBean> findTypeList();

    void saveBook(BookBean bookBean);

    void updateBook(BookBean bookBean);

    BookBean findEditBookPage(Integer id);

    List<TypeBean> findtypeBeans();
}

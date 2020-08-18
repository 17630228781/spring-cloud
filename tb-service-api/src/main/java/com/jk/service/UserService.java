package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface UserService {

    @RequestMapping("/findBookList")
    List<BookBean> findBookList();

    @RequestMapping("/findTreeList")
    List<TreeBean> findTreeList();

    @RequestMapping("/deleteBook")
    void deleteBook(@RequestParam Integer ids);

    @RequestMapping("/findTypeList")
    List<TypeBean> findTypeList();

    @RequestMapping("/saveBook")
    void saveBook(BookBean bookBean);

    @RequestMapping("/updateBook")
    void updateBook(BookBean bookBean);

    @RequestMapping("/findEditBookPage")
    BookBean findEditBookPage(@RequestParam Integer id);

    @RequestMapping("/findtypeBeans")
    List<TypeBean> findtypeBeans();

    @RequestMapping("/hello")
    String hello(@RequestParam String name);

    @RequestMapping("/saveOrder")
    Object saveOrder(@RequestParam Integer userId,@RequestParam Integer productId);
}

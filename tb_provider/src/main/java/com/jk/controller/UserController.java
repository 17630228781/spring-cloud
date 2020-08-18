package com.jk.controller;


import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import com.jk.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class UserController implements UserService {

    @Resource
    private UserService userService;


    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:22
     *@commentName 本方法用来干嘛的(查询图书)
     */
    @Override
    @RequestMapping("/findBookList")
    public List<BookBean> findBookList() {
        return userService.findBookList();
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:22
     *@commentName 本方法用来干嘛的(查树)
     */
    @Override
    @RequestMapping("/findTreeList")
    public List<TreeBean> findTreeList() {
        return userService.findTreeList();
    }

    @Override
    @RequestMapping("/deleteBook")
    public void deleteBook(Integer ids) {
        userService.deleteBook(ids);
    }

    @Override
    @RequestMapping("/findTypeList")
    public List<TypeBean> findTypeList() {
        return userService.findTypeList();
    }

    @Override
    @RequestMapping("/saveBook")
    public void saveBook(@RequestBody BookBean bookBean) {
        userService.saveBook(bookBean);
    }

    @Override
    @RequestMapping("/updateBook")
    public void updateBook(@RequestBody BookBean bookBean) {
        userService.updateBook(bookBean);
    }

    @Override
    @RequestMapping("/findEditBookPage")
    public BookBean findEditBookPage(Integer id) {
        return userService.findEditBookPage(id);
    }

    @Override
    @RequestMapping("/findtypeBeans")
    public List<TypeBean> findtypeBeans() {
        return userService.findtypeBeans();
    }
}

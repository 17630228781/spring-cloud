package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
@RequestMapping("/error")
@Component
public class UserServiceFallback implements UserServiceFeign{


    @Override
    public List<BookBean> findBookList() {
        System.out.println("熔断处理,方法查询用户集合");
        //熔断处理 。。。TODO
        return null;
    }

    @Override
    public List<TreeBean> findTreeList() {
        return null;
    }

    @Override
    public void deleteBook(Integer ids) {

    }

    @Override
    public List<TypeBean> findTypeList() {
        return null;
    }

    @Override
    public void saveBook(BookBean bookBean) {

    }

    @Override
    public void updateBook(BookBean bookBean) {

    }

    @Override
    public BookBean findEditBookPage(Integer id) {
        return null;
    }

    @Override
    public List<TypeBean> findtypeBeans() {
        return null;
    }

    @Override
    public String hello(String name) {
        System.out.println("进入了，熔断器");
        return "请求失败,请检查网络";
    }

    @Override
    public Object saveOrder(Integer userId, Integer productId) {
        System.out.println("进入了，保存订单，熔断器类");
        return "保存订单失败";
    }
}

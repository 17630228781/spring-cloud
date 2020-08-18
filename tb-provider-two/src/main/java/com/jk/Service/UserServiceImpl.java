package com.jk.Service;

import com.jk.mapper.UserMapper;
import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import com.jk.service.UserService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    @RequestMapping("/findBookList")
    public List<BookBean> findBookList() {
        return userMapper.selectUserList();
    }

    @Override
    @RequestMapping("/findTreeList")
    public List<TreeBean> findTreeList() {
        // 递归树
        Integer id = 0;
        return treeWhere(id);
    }

    @Override
    @RequestMapping("/deleteBook")
    public void deleteBook(Integer ids) {
        userMapper.deleteBook(ids);
    }

    @Override
    @RequestMapping("/findTypeList")
    public List<TypeBean> findTypeList() {
        return userMapper.findTreeList();
    }

    @Override
    @RequestMapping("/saveBook")
    public void saveBook(@RequestBody BookBean bookBean) {
        userMapper.saveBook(bookBean);
    }

    @Override
    @RequestMapping("/updateBook")
    public void updateBook(@RequestBody BookBean bookBean) {
        userMapper.updateBook(bookBean);
    }

    @Override
    @RequestMapping("/findEditBookPage")
    public BookBean findEditBookPage(Integer id) {
        return userMapper.findEditBookPage(id);
    }

    @Override
    @RequestMapping("/findtypeBeans")
    public List<TypeBean> findtypeBeans() {
        return userMapper.findtypeBeans();
    }

    @Override
    @RequestMapping("/hello")
    public String hello(@RequestParam String name) {
        return "hi,你好"+name;
    }

    @Override
    @RequestMapping("/saveOrder")
    public Object saveOrder(@RequestParam Integer userId,@RequestParam Integer productId) {
        HashMap<String, Object> orderMap = new HashMap<>();
        orderMap.put("orderId",11111);
        orderMap.put("userId",userId);
        orderMap.put("productId",productId);
        orderMap.put("orderNo","20122121510221");
        orderMap.put("orderName","宜家料地区");
        orderMap.put("orderPrice",100000);
        orderMap.put("createTime","2020-08-15 10:37");
        return orderMap;
    }

    private List<TreeBean> treeWhere(Integer id) {
        List<TreeBean> list = userMapper.findTreeChildren(id);
        for (TreeBean treeBean : list) {
            List<TreeBean> findNodes = treeWhere(treeBean.getId());
            if (findNodes==null||findNodes.size()<=0) {
                treeBean.setSelectable("true");
            }else{
                treeBean.setNodes(findNodes);
            }
        }
        return list;
    }
}

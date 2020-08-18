package com.jk.service;

import com.jk.mapper.UserMapper;
import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public List<BookBean> findBookList() {
        return userMapper.findBookList();
    }

    @Override
    public List<TreeBean> findTreeList() {
        // 递归树
        Integer id = 0;
        return treeWhere(id);
    }

    @Override
    public void deleteBook(Integer ids) {
        userMapper.deleteBook(ids);
    }

    @Override
    public List<TypeBean> findTypeList() {
        return userMapper.findTypeList();
    }

    @Override
    public void saveBook(BookBean bookBean) {
        userMapper.saveBook(bookBean);
    }

    @Override
    public void updateBook(BookBean bookBean) {
        userMapper.updateBook(bookBean);
    }

    @Override
    public BookBean findEditBookPage(Integer id) {
        return userMapper.findEditBookPage(id);
    }

    @Override
    public List<TypeBean> findtypeBeans() {
        return userMapper.findtypeBeans();
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

package com.jk.controller;

import com.jk.model.BookBean;
import com.jk.model.TypeBean;
import com.jk.service.UserServiceFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class PageController  {

    @Resource
    private UserServiceFeign userServiceFeign;
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/14 9:33
     *@commentName 本方法用来干嘛的(修改回显)
     */
    @RequestMapping("/toEditBookPage")
    public String toEditBookPage(Integer id, ModelMap modelMap){
        BookBean bookBean = userServiceFeign.findEditBookPage(id);
        modelMap.put("bookBean",bookBean);
        List<TypeBean> typeBean = userServiceFeign.findtypeBeans();
        modelMap.put("typeBean",typeBean);
        return "editBook";
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 21:38
     *@commentName 本方法用来干嘛的(新增)
     */
    @RequestMapping("/toBookAdd")
    public String toBookAdd(){
        return "bookAdd";
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:54
     *@commentName 本方法用来干嘛的(查询页面)
     */
    @RequestMapping("/toBookList")
    public String toBookList(){
        return "bookList";
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:16
     *@commentName 本方法用来干嘛的(跳转树)
     */
    @RequestMapping("/toMain")
    public String toMain(){
        return "main";
    }

}

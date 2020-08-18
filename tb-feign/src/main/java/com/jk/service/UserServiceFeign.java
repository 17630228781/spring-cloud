package com.jk.service;

import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "tb-provider-two",fallback = UserServiceFallback.class)//tb-provider-two或tb-provider更换项目后台连接数据库方法
public interface UserServiceFeign  extends UserService{

}

package com.jk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jk.model.BookBean;
import com.jk.model.TreeBean;
import com.jk.model.TypeBean;
import com.jk.service.UserServiceFeign;
import com.jk.utils.CheckSumBuilder;
import com.jk.utils.Constant;
import com.jk.utils.HttpClientUtil;
import com.jk.utils.RedisUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
@RestController
public class UserController {

    @Resource
    private UserServiceFeign userServiceFeign;

    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/saveOrder")
    @HystrixCommand(fallbackMethod = "saveOrderFail")
    public Object saveOrder(Integer userId, Integer productId, HttpServletRequest request){
        return userServiceFeign.saveOrder(userId,productId);
    }
    //注意方法签名一定要和api方法一致自定义降级方法
    public Object saveOrderFail(Integer userId,Integer productId, HttpServletRequest request){
        System.out.println("controller 保存订单降级方法");
        String sendValue = (String) redisUtil.get(Constant.SAVE_ORDER_WARNING_KEY);
        String ipAddr = request.getRemoteAddr();
        //新启动一个线程进行业务逻辑处理
        HashMap<String, Object> result = new HashMap<String,Object>();
        // 开启一个独立线程，进行发送警报，给开发人员，处理问题
        new Thread(()->{
                String  phoneNumber = "15809267009";//管理人
                if (sendValue==null||sendValue.length()<=0||sendValue.isEmpty()){
                //发送一个http请求，调用短信服务 TODO
                // 发送手机验证码
                HashMap<String, Object> headers = new HashMap<String,Object>();
                headers.put("Appkey", Constant.APP_KEY);
                String nonce = UUID.randomUUID().toString().replaceAll("-", "");
                headers.put("Nonce",nonce);
                String curTime = System.currentTimeMillis()+"";
                headers.put("CurTime",curTime);
                headers.put("CheckSum", CheckSumBuilder.getCheckSum(Constant.APP_SECRET, nonce, curTime));
                HashMap<String, Object> params = new HashMap<String,Object>();
                params.put("mobile", phoneNumber);
                params.put("templateid", Constant.TEMPLATEID);
                //String authCode = "紧急短信，用户下单失败，请离开查找原因,ip地址是="+ipAddr;
                String authCode = "";
                    Random random = new Random();
                    for (int i=0;i<5;i++)
                    {
                        authCode+=random.nextInt(10);
                    }
                    System.out.println("-----------1验证码--------"+authCode);
                params.put("authCode",   authCode);
            String post = null;
            try {
                post = HttpClientUtil.post(Constant.SMS_URL, params, headers);
            } catch (Exception e) {
                e.printStackTrace();
            }
            JSONObject parseObject = JSON.parseObject(post);
                int code = parseObject.getIntValue("code");
                System.out.println(code+"--------------------------验证码错");
                if (code != 200) {
                    System.out.println("发送失败");
                }
                // 写发送短信代码，带有参数发送 userId  productId
                redisUtil.set(Constant.SAVE_ORDER_WARNING_KEY,"用户保存订单失败", 60);
            }else {
                System.out.println("已经发送过短信，1分钟内不重复发送");
            }
        }).start();
        // 反馈给用户看的
        result.put("code",-1);
        result.put("message","抢购排队人数过多,请你稍后重试...");
        return result;
    }

    @RequestMapping("/hello")
    public String hello(String name){
        return userServiceFeign.hello(name);
    }

    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/14 9:04
     *@commentName 本方法用来干嘛的(新增+修改)
     */
    @RequestMapping("/saveBook")
    public Boolean saveBook(BookBean bookBean){
        try{
            String cacheKeyPowerUrl = Constant.SELECT_USER_LIST;
            redisTemplate.delete(cacheKeyPowerUrl);
            if (bookBean.getId()==null){
                userServiceFeign.saveBook(bookBean);
            }else {
                userServiceFeign.updateBook(bookBean);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 21:41
     *@commentName 本方法用来干嘛的(回显下拉)
     */
    @RequestMapping("/findTypeList")
    public List<TypeBean> findTypeList(){
       return userServiceFeign.findTypeList();
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 21:03
     *@commentName 本方法用来干嘛的(删除)
     */
    @RequestMapping("/deleteBook")
    public void deleteBook(Integer ids){
        String cacheKeyPowerUrl = Constant.SELECT_USER_LIST;
        redisTemplate.delete(cacheKeyPowerUrl);
        userServiceFeign.deleteBook(ids);
    }
    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:16
     *@commentName 本方法用来干嘛的(查询图书不分页)
     */
    @RequestMapping("/findBookAllList")
    public List<BookBean> findBookList(){
        List<BookBean> bookBeans = (List<BookBean>)redisUtil.get(Constant.SELECT_USER_LIST);
        if (bookBeans==null||bookBeans.size()<=0||bookBeans.isEmpty()){
            bookBeans = userServiceFeign.findBookList();
            redisUtil.set(Constant.SELECT_USER_LIST,bookBeans,30);
        }
        return bookBeans;
    }

    /**
     * @author  XieMT
     * @desire  BG远离我吧!
     * @date 2020/8/13 20:19
     *@commentName 本方法用来干嘛的(树)
     */
    @RequestMapping("/findTreeList")
    public List<TreeBean> findTreeList(){
        return userServiceFeign.findTreeList();
    }

}

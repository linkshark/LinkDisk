package com.linkjb.controller;

import com.linkjb.base.BaseResult;
import com.linkjb.base.ConstantSrting;
import com.linkjb.entity.User;
import com.linkjb.service.UserService;
import com.linkjb.utils.MD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author sharkshen
 * @data 2019/1/16 16:22
 * @Useage balabala
 */
//@CrossOrigin(origins = "localhost:8078", maxAge = 3600)
@RestController
@RequestMapping("/User")
public class UserController {
    //日志记录
    private static Logger Log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    private static String salt = "sharkshen";
    /*
     * @Author sharkshen
     * @Description  通过用户名获取用户
     * @Date 2019/1/17
     * @Param [userName]
     * @return com.linkjb.base.BaseResult<java.lang.Boolean>
     **/
    @PostMapping("/Login/checkUserName")
    public BaseResult<Boolean> checkUserNameUinque(@RequestParam("userName") String userName){
        //System.out.println(userName);
        //Log.info("接近目标中...");
        BaseResult<Boolean> result = new BaseResult<>();

       try{
           User user = userService.getUserByUserName(userName);
           if(user==null||user.equals(null)){
               result.setEntity(true);
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
               return result;
           }else{
               result.setEntity(false);
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
               //throw new Exception("主动报错!牛逼坏了");

           }
       }catch (Exception e){
           result.setStatus(ConstantSrting.STATUS_FAIL);
           //Log.error("啦啦啦啦啦报错了====="+e.getMessage()+"=======");
       }
        return result;
    }
        /*
         * @Author sharkshen
         * @Description  注册用户
         * @Date  2019/1/17
         * @Param [user]
         * @return com.linkjb.base.BaseResult<com.linkjb.entity.User>
         **/
    @PostMapping("/Login/Regist")
    public BaseResult<User> Regist(User user){
        System.out.println(user);
        BaseResult<User> result = new BaseResult<>();
       try{
           user.setPassWord(MD5.encryptPassword(user.getPassWord(),salt));
           Integer a = userService.RegistUser(user); //a的值为sql影响的行数,一开始理解错误,是直接将id返回到对象中,所以可以直接返回对象
           if(a.equals(1)){
               result.setEntity(user);
               result.setStatus(ConstantSrting.STATUS_SUCCESS);
               return result;
           }else{
               result.setStatus(ConstantSrting.STATUS_FAIL);
           }
       }catch (Exception e){
           e.printStackTrace();
       }
        return result;

    }

    /*
     * @Author sharkshen
     * @Description  用户登录验证
     * @Date  2019/1/17
     * @Param [userName,passWord]
     * @return com.linkjb.base.BaseResult<com.linkjb.entity.User>
     **/
    @PostMapping("/Login/Login")
    public BaseResult<User> Login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        BaseResult<User> result = new BaseResult<>();
        try{
            User user = userService.getUserByUserName(userName);
            if(user!=null){
                String checkPass = user.getPassWord();
                if(MD5.encryptPassword(passWord,salt).equals(checkPass)){
                    result.setStatus(ConstantSrting.STATUS_SUCCESS);
                    result.setEntity(user);
                    return result;
                }else{
                    result.setStatus(ConstantSrting.STATUS_FAIL);
                    result.setMessage("密码错误");
                    return result;
                }
            }else{
                result.setStatus(ConstantSrting.STATUS_FAIL);
                result.setMessage("账号错误");
                return result;
            }
        }catch (Exception e){
            Log.error(e.getMessage());
            result.setStatus(ConstantSrting.STATUS_SUCCESS);
            result.setMessage(e.getMessage());
        }
        return result;
    }


}

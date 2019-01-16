package com.linkjb.Login;

import com.linkjb.base.BaseResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sharkshen
 * @data 2019/1/16 16:22
 * @Useage balabala
 */
@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping("/checkUserName")
    public BaseResult<Boolean> checkUserNameUinque(String userName){
        System.out.println(userName);
        BaseResult<Boolean> result = new BaseResult<>();
        result.setEntity(true);
        return result;
    }
}

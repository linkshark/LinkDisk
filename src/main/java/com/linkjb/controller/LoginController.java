package com.linkjb.controller;

import com.linkjb.base.BaseResult;
import com.linkjb.base.ConstantSrting;
import org.springframework.web.bind.annotation.*;

/**
 * @author sharkshen
 * @data 2019/1/16 16:22
 * @Useage balabala
 */
//@CrossOrigin(origins = "localhost:8078", maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping("/checkUserName")
    public BaseResult<Boolean> checkUserNameUinque(@RequestParam("userName") String userName){
        System.out.println(userName);
        BaseResult<Boolean> result = new BaseResult<>();
        result.setEntity(true);
        result.setStatus(ConstantSrting.STATUS_SUCCESS);
        return result;
    }
}
